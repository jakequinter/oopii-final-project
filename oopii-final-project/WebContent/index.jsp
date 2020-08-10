<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> --%>
<html>
<head>
<!-- <meta charset="UTF-8"> -->
<title>Address Book</title>

</head>
<body>
 <%@ include file="/WEB-INF/nav.jsp" %> 
 
 <style>
 p {
  margin-bottom: 5;
 }
   ul { 
     margin-bottom: 20;
   }
   
/*    li {
     list-style-type: none;
   } */
 </style>
<div class="jumbotron">
  <h1 class="display-4">Address Book</h1>
  <p class="lead">Object-Oriented Programming II final project.</p>
  <hr class="my-4">
  <p>Technologies Used:</p>
  <ul>
    <li>Java, JavaScript, CSS, Bootstrap</li>
    <li>MVC architecture</li>
    <li>Apache embedded JDBC database</li>
    <li>MySQL for database CRUD operations</li>
  </ul>
  <a class="btn btn-primary btn-lg" href="contacts" role="button">View Contacts</a>
  <a class="btn btn-secondary btn-lg" href="https://github.com/jakequinter/oopii-final-project" role="button">View Source Code</a>
</div>
</body>
</html>