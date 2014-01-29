<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style type="text/css">
    .error { color: red ; font-size: 0.9em; font-weight: bold; }
	.errorblock{ width:80%;	color: #000;	background-color: #ffEEEE;	border: 3px solid #ff0000;
					padding:8px;	margin:16px;}
	
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- <title>Spring 3 MVC Series - <spring:message code="label.title" /></title> -->

</head>
<body>
	
	
<h3> ${resultado}</h3></br>
<!--<c:if test="${enSession != null}"> -->
	<p>${usuario}</p>
	<p>${companyid}</p>
	<p><b>${modelandviewVar}</b></p>
<!--</c:if>	-->
<%=session.getAttribute("usuario")%>
<%=session.getAttribute("companyid")%>
<%=session.getAttribute("user")%>
<table>
	<c:if test="${ContactInfo != null}">
		<c:forEach items="${ContactInfo}" var="info">
			<tr>
			   <td><c:out value="${info.contactId}" /></td>
			   <td><c:out value="${info.contactName}" /></td>
			   <td><c:out value="${info.categoryName}" /></td>
			</tr>
		</c:forEach>
	</c:if>	
</table>
	
	



</body>
</html>