
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<body>
<%@ include file="nav.jsp" %>
<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${e != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${e == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${e != null}">
            			Edit User
            		</c:if>
						<c:if test="${e == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${e != null}">
					<input type="hidden" name="id" value="<c:out value='${e.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out value='${e.firstName}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value="<c:out value='${e.lastName}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>User Country</label> <input type="text"
						value="<c:out value='${e.address}' />" class="form-control"
						name="country">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>