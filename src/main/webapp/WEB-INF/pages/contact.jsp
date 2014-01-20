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
	<!--URLS Definidas -->
	<spring:url var="addUrl" value="/contact/save"/>
	<spring:url var="deleteUrl" value="/contact/delete"/>
	<spring:url var="updateUrl" value="/contact/update"/>
	
	<form:form method="post" action="${addUrl}"  commandName="contact">
		<form:errors path="*" cssClass="errorblock" element="div"/>
		<table >
			<tr>
				<td><form:label path="firstname">
						<spring:message code="label.firstname" />
					</form:label>
				</td>
				<td>
				<form:hidden path="id" />
				<form:input path="firstname" />
				</td>
				<td class="error"><form:errors path="firstname" cssclass="error"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="lastname">
						<spring:message code="label.lastname" />
					</form:label>
				</td>
				<td><form:input path="lastname" />
				</td>
				<td class="error"><form:errors path="lastname" cssclass="error"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="email">
						<spring:message code="label.email" />
					</form:label>
				</td>
				<td><form:input path="email" />
				</td>
				<td class="error"><form:errors path="email" cssclass="error"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="telephone">
						<spring:message code="label.telephone" />
					</form:label>
				</td>
				<td><form:input path="telephone" />
				</td>
				<td class="error"><form:errors path="telephone" cssclass="error"></form:errors></td>
			</tr>
			<tr>
				<td></td>
				<td>
				<form:select path="category.id" >
					<form:option value="0" label="--- Select ---" />
					 <form:options items="${categoryList}" itemValue="id" itemLabel="name" />
				</form:select>
				</td>
				<td class="error"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message code="label.addcontact"/>" /></td>
			</tr>
		</table>
	</form:form>


	
	<c:if test="${!empty contactList}">
	<h3><spring:message code="label.list"/></h3>
		<table class="data" cellpadding="3" cellspacing="10">
			<tr>
				<th><spring:message code="label.completename"/></th>
				<th><spring:message code="label.email"/></th>
				<th><spring:message code="label.telephone"/></th>
				<th><spring:message code="label.category"/></th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${contactList}" var="contact">
				<tr>
					<td>${contact.lastname}, ${contact.firstname}</td>
					<td>${contact.email}</td>
					<td>${contact.telephone}</td>
					<td>${contact.category.name}</td>
					<td><a href="${deleteUrl}/${contact.id}"><spring:message code="label.delete"/></a>
					</td>
					<td><a href="${updateUrl}/${contact.id}"><spring:message code="label.update"/></a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>