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

<display:table name="${chorbies}" id="chorbi" class="displaytag" pagesize="5" keepStatus="true" requestURI="${requestURI}">

	<acme:column code="chorbi.name" property="name" sortable="true"/>
	<acme:column code="chorbi.surname" property="surname" sortable="true"/>
	<acme:column code="chorbi.description" property="description"/>
	<acme:column code="chorbi.relationship" property="relationship" sortable="true"/>
	<acme:column code="chorbi.birthDate" property="birthDate" format="{0,date,dd-MM-yyyy}" sortable="true"/>
	<security:authorize access="hasRole('ADMIN')">
		<acme:column code="chorbi.banned" property="banned" sortable="true"/>
	</security:authorize>
	<spring:message code="chorbi.profile" var="profileHeader" />
	<display:column title="${profileHeader}">
		<a href="profile/display.do?actorId=${chorbi.id}"><spring:message code="chorbi.show"/></a>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<jstl:if test="${chorbi.banned == false}">
				<a href="chorbi/ban.do?chorbiId=${chorbi.id}"><spring:message code="chorbi.ban"/></a>
			</jstl:if>
			<jstl:if test="${chorbi.banned == true}">
				<a href="chorbi/unban.do?chorbiId=${chorbi.id}"><spring:message code="chorbi.unban"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('CHORBI')">
		<display:column>
			<jstl:set var="haveLike" value="${false}"/>
			<jstl:forEach var="sense" items="${sensesSent}">
				<jstl:if test="${sense.recipient.id == chorbi.id}">
					<jstl:set var="haveLike" value="${true}"/>
				</jstl:if>
			</jstl:forEach>
			<jstl:choose>
				<jstl:when test="${haveLike==false}">
					<a href="sense/chorbi/like.do?chorbiId=${chorbi.id}"><img src="http://observatorioecommerce.com/wp-content/uploads/2015/04/logo-me-gusta-facebook.png" alt="<spring:message code="chorbi.like"/>" width="30" height="30"/></a>
				</jstl:when>
				<jstl:otherwise>
					<a href="sense/chorbi/dislike.do?chorbiId=${chorbi.id}"><img src="http://oyadance.com/genre/commonfiles/images/jw_dislike_disable.png" alt="<spring:message code="chorbi.dislike"/>" width="30" height="30"/></a>
				</jstl:otherwise>
			</jstl:choose>
		</display:column>
		
		<spring:message code="sense.comment" var="commentHeader" />
		<display:column title="${commentHeader}">
			<jstl:if test="${haveLike==true}">
				<a href="sense/chorbi/comment.do?chorbiId=${chorbi.id}"><spring:message code="sense.comment"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>
