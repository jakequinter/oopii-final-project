
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Address Book | New Email</title>
</head>
<body>
    <%@ include file="/WEB-INF/nav.jsp" %>
<div class="container col-md-5" style="margin-top: 50px;">
		<div class="card" >
			<div class="card-body"">
			<h2 class="text-center">New Email</h2>
			<form id="emailForm" onsubmit="return validateEmail();">

				<input type="hidden" name="contactId" value="<c:out value='${contact.contactId}' />" />

				<fieldset class="form-group">
					<label>Email</label> <input type="email" id="email"
						value="<c:out value='${email.email}' />" class="form-control"
						name="email">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Type</label> 
					<%-- <input type="text" id="name"
						value="<c:out value='${phoneNumber.type}' />" class="form-control"
						name="type"> --%>
						 <select class="form-control" id="exampleFormControlSelect1" name="type">
					      <option>Primary</option>
					      <option>Secondary</option>
					      <option>Work</option>
					      <option>Other</option>
					    </select>
				</fieldset>
				
				<button type="submit" style="display: block; margin-top: 5; width: 100%;" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
		<div id='errText-container' class='errText-container' style="margin-top: 10px;">
        <div id="errText"></div>
      </div>
	</div>	
	
	<!-- javascript -->
	<script>
    let emailForm = document.getElementById('emailForm');
    let email = document.getElementById('email');
    const errTextContainer = document.getElementById('errText-container');
    const errText = document.getElementById('errText');
    
    function validateEmail() {
      
      if (email.value == "") {
          setError("Please enter an email address");
          clearError();
          
          return false;
        }
      
      emailForm.action = "insert-email"
      return true;
    }
    
    
    
    function setError(msg) {
      errTextContainer.setAttribute("class", "alert alert-danger");
      errText.innerText = msg;
    }
    
    function clearError() {
      setTimeout(function() {
        errTextContainer.setAttribute("class", "");
        errText.innerText = ''
      }, 3000)
    }
  </script>	
</body>
</html>