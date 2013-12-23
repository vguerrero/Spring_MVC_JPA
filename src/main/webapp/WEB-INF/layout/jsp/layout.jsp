<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- el title es uno solo para toda la app y no cambia por page -->
<c:set var="titleKey"><tiles:getAsString name="titleKey"/></c:set>
<title><spring:message code="label.title"></spring:message> </title>

<!-- <title><tiles:insertAttribute name="title" ignore="true" /></title> -->
</head>
<body>
<table border="1" cellpadding="2" cellspacing="2" align="center" width="100%">
    <tr>
        <td height="30" colspan="2"><tiles:insertAttribute name="header" />
        </td>
    </tr>
    <tr>
        <td height="400" width="10%"><tiles:insertAttribute name="menu" /></td>
        <td width="90%"><tiles:insertAttribute name="body" /></td>
    </tr>
    <tr>
        <td height="30" colspan="2"><tiles:insertAttribute name="footer" />
        </td>
    </tr>
</table>
</body>
</html>