<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<fieldset><legend class="dashLegend"><b><spring:message code="admin.dashboard.level.c"/></b></legend>
	<h3><spring:message code="admin.dashboard.c1"/></h3>
		<display:table name="numberChorbiPerCountryAndCity" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
			
			<acme:column code="admin.dashboard.coordinate.country" property="[0]"/>
			<acme:column code="admin.dashboard.coordinate.city" property="[1]"/>
			<acme:column code="admin.dashboard.chorbies.number" property="[2]"/>
			
		</display:table>
		
	<h3><spring:message code="admin.dashboard.c2"/></h3>
		<ul>
			<li>
				<b><spring:message code="admin.dashboard.min"/>:</b>
				<jstl:out value="${minMaxAvgAgeOfChorbi2[0]}"/>
			</li>
			<li>
				<b><spring:message code="admin.dashboard.avg"/>:</b>
				<jstl:out value="${minMaxAvgAgeOfChorbi2[1]}"/>
			</li>
			<li>
				<b><spring:message code="admin.dashboard.max"/>:</b>
				<jstl:out value="${minMaxAvgAgeOfChorbi2[2]}"/>
			</li>
		</ul>
		
	<h3><spring:message code="admin.dashboard.c3"/></h3>
		<br/>
		<jstl:out value="${ratioChorbiesWithNullOrInvalidCreditcard}" />
		<br/><br/>
	</fieldset>