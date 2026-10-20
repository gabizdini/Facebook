package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.ModelException;
import model.Post;
import model.dao.CommentDAO;
import model.dao.DAOFactory;
import model.dao.PostDAO;

@WebServlet(urlPatterns = {"/comments", 
		                   "/comments/save", 
		                   "/comments/create",
		                   "/comments/update",
		                   "/comments/delete"})
public class CommentsController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//TODO: Log - apagar ao final
		String action = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("URL: " + action);
		
		switch (action) {
		case "/comments": {
			loadComments(req);
			
			RequestDispatcher rd = req.getRequestDispatcher("/comments.jsp");
			rd.forward(req, resp);
			break;
		}
		case "/comments/save": {
			saveOrUpdateComment(req);
			
			resp.sendRedirect(req.getContextPath() + "/comments");
			break;
		}
		case "/comments/create": {
			loadPosts(req);
			
			RequestDispatcher rd = req.getRequestDispatcher("/comments_form.jsp");
			rd.forward(req, resp);
			break;
		}
		case "/comments/update": {
			loadComment(req);
			loadPosts(req);
			
			RequestDispatcher rd = req.getRequestDispatcher("/comments_form.jsp");
			rd.forward(req, resp);
			break;
		}
		
		case "/comments/delete": {
			deleteComment(req);
			resp.sendRedirect(req.getContextPath() + "/comments");
			break;
		}
		default:
			String message = String.format("URL não suportada: '%s'.", action);
			
			System.out.println(message);
			throw new IllegalArgumentException(message);
		}
	}
	
	private void deleteComment(HttpServletRequest req) {
		String commentIdStr = req.getParameter("commentId");
		
		int commentId = Integer.parseInt(commentIdStr);
		Comment commentToBeDeleted = new Comment(commentId);
		
		CommentDAO dao = DAOFactory.createDAO(CommentDAO.class);
		try {
			dao.delete(commentToBeDeleted);
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
	}
	
	private void loadComment(HttpServletRequest req) {
		String commentIdStr = req.getParameter("commentId");
		int commentId = Integer.parseInt(commentIdStr);
	
		CommentDAO dao = DAOFactory.createDAO(CommentDAO.class);
		
		Comment commentToBeUpdated = null;
		try {
			commentToBeUpdated = dao.findById(commentId);
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		req.setAttribute("comment", commentToBeUpdated);
	}
	
	private void saveOrUpdateComment(HttpServletRequest req) {
		String commentIdStr = req.getParameter("comment_id");
		boolean isUpdate = false;
		
		Comment comment = null;
		if ((commentIdStr == null) || commentIdStr.isBlank())
			comment = new Comment();
		else { 
			comment = new Comment(Integer.parseInt(commentIdStr));
			isUpdate = true;
		}
		
		// Recuperar os dados da requisição
		String commentContent = req.getParameter("comment_content");
		int commentPostId = Integer.parseInt(req.getParameter("comment_post_id"));
		
		// Criar o objeto do tipo comentário
		comment.setContent(commentContent);
		
		PostDAO postDAO = DAOFactory.createDAO(PostDAO.class);
		
		Post commentPost = null;
		try {
			commentPost = postDAO.findById(commentPostId);
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		comment.setPost(commentPost);
		
		// Criar um DAO de Comentário
		CommentDAO dao = DAOFactory.createDAO(CommentDAO.class);
		
		// Salvar o comentário no Banco
		try {
			if (isUpdate) dao.update(comment);
			else dao.save(comment);
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
	}
	
	private void loadComments(HttpServletRequest req) {
		CommentDAO dao = DAOFactory.createDAO(CommentDAO.class);
		
		List<Comment> comments = List.of();
		try {
			comments = dao.listAll();
		} catch (ModelException me) {
			// TODO Log no servidor
			me.printStackTrace();
		}
		
		req.setAttribute("comments_list", comments);
	}
	
	private void loadPosts(HttpServletRequest req) {
		PostDAO dao = DAOFactory.createDAO(PostDAO.class);
		
		List<Post> posts = List.of();
		try {
			posts = dao.listAll();
		} catch (ModelException me) {
			// TODO Log no servidor
			me.printStackTrace();
		}
		
		req.setAttribute("posts_list", posts);
	}
}
