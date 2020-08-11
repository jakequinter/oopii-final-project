
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Address Book | Edit Phone</title>
</head>
<body>
    <%@ include file="/WEB-INF/nav.jsp" %>
	<div class="container col-md-5" style="margin-top: 50px;">
		<div class="card" >
			<div class="card-body">
				<h2 class="text-center"><c:out value='${contact.fullName}'/>'s Phone Number</h2>
				<form id="phoneNumberForm" onsubmit="return validatePhoneNumber();">
					<input type="hidden" name="phoneNumberId" value="<c:out value='${phoneNumber.phoneNumberId}' />" />
					<input type="hidden" name="fkPhoneNumberContactId" value="<c:out value='${phoneNumber.fkPhoneNumberContactId}' />" />

					<fieldset class="form-group">
						<label>Phone Number</label> 
						<input type="text" id="phoneNumber" value="<c:out value='${phoneNumber.phoneNumber}' />" class="form-control" name="phoneNumber">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Type: <c:out value='${phoneNumber.type}' /></label> 
		   				<select class="form-control" id="exampleFormControlSelect1" name="type">
							<option>Mobile</option>
						    <option>Home</option>
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
    let phoneNumberForm = document.getElementById('phoneNumberForm');
    let phoneNumber = document.getElementById('phoneNumber');
    const errTextContainer = document.getElementById('errText-container');
    const errText = document.getElementById('errText');
    
    function validatePhoneNumber() {
      
      if (phoneNumber.value == "") {
          setError("Please enter a phone number");
          clearError();
          
          return false;
        }
      
      if (!(/^(?:\(\d{3}\)|\d{3}-)\d{3}-\d{4}$/).test(phoneNumber.value)) {
    	    setError("Phone number should be numeric and in one of the following formats: (xxx)xxx-xxxx or xxx-xxx-xxxx.");
    	    clearError();
    	    return false;
    	  }
      
      phoneNumberForm.action = "update-phonenumber"
      return true;
    }
    
    
    function hasNumber(myString) {
      return /\d/.test(myString);
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