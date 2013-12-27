<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3><spring:message code="label.title"/></h3>

<span style="float: right">
    <a href="?lang=en">en</a> 
    | 
    <a href="?lang=es">es</a>
</span>

<span style="float: left">
    <a href="?theme=default">default</a> 
    | 
    <a href="?theme=black">black</a>
    | 
    <a href="?theme=blue">blue</a>
</span>