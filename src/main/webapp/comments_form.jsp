<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestão de Comentários</title>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>
<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col-md-2"></div>
			
			<div class="col-md-8">
				<h1>Cadastro de Comentário</h1>
			
				<form action="${pageContext.request.contextPath}/comments/save"
				      method="GET" 
				      class="">
				      
				      <input type="hidden" 
				             id="comment_id_id" 
				             name="comment_id"
				             value="${comment.getId()}">
				      
				      <div class="mb-3">
				      	<label for="comment_content_id" 
				      	       class="form-label">
				      	       Conteúdo
				      	</label>
				      	<textarea id="comment_content_id"
				      		      name="comment_content"
				      		      rows="4"
				      		      class="form-control"
				      		      >${comment.getContent()}</textarea>	
				      </div>
				      
				      <div class="mb-3">
				      	<label for="comment_post_id_id" 
				      	       class="form-label">
				      	       Post
				      	</label>
				      	
				      	<select id="comment_post_id_id"
				      			name="comment_post_id"
				      			class="form-select">
				      		<c:forEach var="post" items="${posts_list}">
				      			<option value="${post.getId()}"
				      					${comment.getPost().getId() == post.getId() ? "selected" : ""}>
				      				${post.getId()} - ${post.getContent()}
				      			</option>
				      		</c:forEach>
				      	</select>
				      </div>
					
					  <button type="submit" 
					          class="btn btn-primary">
					  	Cadastrar
					  </button>
				</form>
			</div>
			
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>
