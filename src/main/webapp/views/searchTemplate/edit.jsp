<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form method="post" action="searchTemplate/chorbi/edit.do" modelAttribute="searchTemplate" >
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="results" />
	<form:hidden path="updateMoment" />
	
	<acme:input code="searchTemplate.relationship" path="relationship" />
	<acme:input code="searchTemplate.age" path="age" />
	<acme:input code="searchTemplate.genre" path="genre" />
	<acme:input code="searchTemplate.coordinates.city" path="coordinate.city" />
	<acme:input code="searchTemplate.coordinates.country" path="coordinate.country" />
	<acme:input code="searchTemplate.coordinates.state" path="coordinate.state" />
	<acme:input code="searchTemplate.coordinates.province" path="coordinate.province" />
	<acme:input code="searchTemplate.keyword" path="keyword" />
	
	
	<acme:submit name="save" code="searchTemplate.save" />
	
	<acme:cancel url="searchTemplate/chorbi/display.do" code="searchTemplate.cancel" />
	
</form:form>