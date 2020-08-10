
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Address Book | Edit Address</title>
</head>
<body>
    <%@ include file="/WEB-INF/nav.jsp" %>
<div class="container col-md-5" style="margin-top: 50px;">
		<div class="card" >
			<div class="card-body"">
			<h2 class="text-center"><c:out value='${contact.fullName}'/>'s Address</h2>
			<form id="addressForm" onsubmit="return validateAddressInsert();">
					<input type="hidden" name="addressId" value="<c:out value='${address.addressId}' />" />
					<input type="hidden" name="fkAddressContactId" value="<c:out value='${address.fkAddressContactId}' />" />

				<fieldset class="form-group">
					<label>Address Line1</label> <input type="text" id="addressLine1"
						value="<c:out value='${address.addressLine1}' />" class="form-control"
						name="addressLine1">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Address Line2</label> <input type="text" id="addressLine2"
						value="<c:out value='${address.addressLine2}' />" class="form-control"
						name="addressLine2">
				</fieldset>
				
				<fieldset class="form-group">
					<label>City</label> <input type="text" id="city"
						value="<c:out value='${address.city}' />" class="form-control"
						name="city">
				</fieldset>
				
				<fieldset class="form-group">
					<label>State</label> <input type="text" id="state"
						value="<c:out value='${address.state}' />" class="form-control"
						name="state">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Postal Code</label> <input type="text" id="postalCode"
						value="<c:out value='${address.postalCode}' />" class="form-control"
						name="postalCode">
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
    let addressForm = document.getElementById('addressForm');
    let addressLine1 = document.getElementById('addressLine1');
    let city = document.getElementById('city');
    let state = document.getElementById('state');
    const errTextContainer = document.getElementById('errText-container');
    const errText = document.getElementById('errText');
    
    function validateAddressInsert() {
    
      if (addressLine1.value == "") {
        setError("Please enter an address line 1 value");
        clearError();
        
        return false;
      }
      
      if (city.value == "") {
          setError("Please enter a city");
          clearError();
          
          return false;
        }

      
      if (state.value == "") {
          setError("Please enter a state");
          clearError();
          
          return false;
        }

      
      if (postalCode.value == "") {
          setError("Please enter a postal code");
          clearError();
          
          return false;
        }
      
      if (!(/(^\d{5}$)|(^\d{5}-\d{4}$)/).test(postalCode.value)) {
    	    setError("Postal code should be numeric and in one of the following formats: xxxxx or xxxxx-xxxx.");
    	    clearError();
    	    return false;
    	  }
      
      addressForm.action = "update-address"
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