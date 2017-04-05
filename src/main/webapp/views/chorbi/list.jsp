<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h3><spring:message code="chorbi.list" /></h3>

<display:table name="${chorbies}" id="chorbi" class="displaytag" pagesize="5" keepStatus="true" requestURI="${requestURI}">

	<acme:column code="chorbi.name" property="name" sortable="true"/>
	<acme:column code="chorbi.surname" property="surname" sortable="true"/>
	<acme:column code="chorbi.description" property="description"/>
	<acme:column code="chorbi.relationship" property="relationship" sortable="true"/>
	<acme:column code="chorbi.birthDate" property="birthDate" format="{0,date,dd-MM-yyyy}" sortable="true"/>
	<security:authorize access="hasRole('ADMIN')">
		<acme:column code="chorbi.ban" property="ban" sortable="true"/>
	</security:authorize>
	
</display:table>
