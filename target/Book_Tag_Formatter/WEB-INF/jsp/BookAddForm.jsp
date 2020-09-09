<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Add Book Form</title>
<link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>



	<spring:url var="addBook" value="/addBook" />

	<div id="global">
		<form:form modelAttribute="newBook" action="addBook" method="post">
			<fieldset>
				<legend>Add a book</legend>
				<p>
					<label for="category"><spring:message code="book.category" />
					</label>
					<form:select id="category" path="category.id" items="${categories}" 
					itemLabel="name" itemValue="id" />
					
					<%--  <form:select id="category" path="category.id">
						 <form:option value="0" label="--Select Category--"/>
						 <form:options items="${categories}" itemLabel="name" itemValue= "id"/> 
					</form:select> --%> 
				</p>
				
				<%--  <p>
					<form:select id="category" path="category.id" items="${countryList}" />
				</p> --%>

				<p>
					<label for="title"><spring:message code="book.title" /> </label>
					<form:input id="title" path="title" />
				</p>
				<p>
					<label for="author">Author: </label>
					<form:input id="author" path="author" />
				</p>
				<p>
					<label for="isbn"><spring:message code="book.ISBN" text="ISBN"/> </label>
					<form:input id="isbn" path="isbn" />
				</p>
				<p>
					<label for="publishDate">Publish Date: </label>
					<form:input path="publishDate" id="publishDate" />
				</p>

				<p id="buttons">
					<input id="submit" type="submit" value="Add Book">
				</p>
			</fieldset>
		</form:form>
	</div>
</body>
</html>
