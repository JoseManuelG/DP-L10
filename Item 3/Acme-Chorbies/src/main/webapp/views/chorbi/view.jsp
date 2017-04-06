<%--
 * index.jsp
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<fieldset>
<spring:message code="chorbi.name"/>:
<acme:mask text="${chorbi.name}"/><br/>

<spring:message code="chorbi.surname"/>:
<acme:mask text="${chorbi.surname}"/><br/>

<spring:message code="chorbi.email"/>:
<acme:mask text="${chorbi.email}"/><br/>

<spring:message code="chorbi.phone"/>:
<acme:mask text="${chorbi.phone}"/><br/>

<spring:message code="chorbi.picture"/>:<br/>
<acme:image url="${chorbi.picture}"/><br/>

<spring:message code="chorbi.birthDate"/>:
<acme:mask text="${chorbi.birthDate}"/><br/>

<spring:message code="chorbi.genre"/>:
<acme:mask text="${chorbi.genre}"/><br/>

<spring:message code="chorbi.desiredRelationship"/>:
<acme:mask text="${chorbi.desiredRelationship}"/><br/>


<spring:message code="chorbi.description"/>:
<acme:mask text="${chorbi.description}"/>
</fieldset><br/>

<fieldset>
	<legend><spring:message code="chorbi.coordinates"/></legend>
	<spring:message code="chorbi.coordinates.city"/>
	<jstl:out value="${chorbi.coordinates.city}"/> <br/>
	
	<spring:message code="chorbi.coordinates.state"/>
	<jstl:out value="${chorbi.coordinates.state}"/> <br/>
	
	<spring:message code="chorbi.coordinates.country"/>
	<jstl:out value="${chorbi.coordinates.country}"/> <br/>
	
	
	<spring:message code="chorbi.coordinates.province"/>
	<jstl:out value="${chorbi.coordinates.province}"/> <br/>
	
</fieldset><br/>
<jstl:if test="${aux}">
<security:authorize access="hasRole('CHORBI')">
		<a href="likes/chorbi/create.do?chorbiId=${chorbi.id}">
			      <spring:message  code="chorbi.likes" />
		</a>
</security:authorize>
</jstl:if>

<jstl:if test="${myPrincipal}">
	<a href="security/edit.do?chorbiId=${chorbi.id}">
			      <spring:message  code="chorbi.edit.profile" />
		</a>
</jstl:if>
