<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestão de Posts</title>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>
<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col-md-2"></div>
			
			<div class="col-md-8">
				<h1>Cadastro de Post</h1>
			
				<form action="${pageContext.request.contextPath}/posts/save"
				      method="GET" 
				      class="">
				      
				      <input type="hidden" 
				             id="post_id_id" 
				             name="post_id"
				             value="${post.getId()}">
				      
				      <div class="mb-3">
				      	<label for="post_content_id" 
				      	       class="form-label">
				      	       Conteúdo
				      	</label>
				      	<textarea id="post_content_id"
				      		      name="post_content"
				      		      rows="4"
				      		      class="form-control"
				      		      >${post.getContent()}</textarea>	
				      </div>
				      
				      <div class="mb-3">
				      	<label for="post_user_id_id" 
				      	       class="form-label">
				      	       Usuário
				      	</label>
				      	
				      	<select id="post_user_id_id"
				      			name="post_user_id"
				      			class="form-select">
				      		<c:forEach var="usuario" items="${users_list}">
				      			<option value="${usuario.getId()}"
				      					${post.getUser().getId() == usuario.getId() ? "selected" : ""}>
				      				${usuario.getId()} - ${usuario.getName()}
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
