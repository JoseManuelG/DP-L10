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

<spring:message code="likes.from"/>:
<jstl:out value="${likes.liker}"/><br/>


<spring:message code="likes.sendingMoment"/>:
<jstl:out value="${likes.moment}"/><br/>


<fieldset>
	<legend><spring:message code="likes.comment"/></legend>
	<jstl:out value="${likes.comment}"/>
</fieldset><br/>

<a href="likes/actor/delete.do?likesId=${likes.id}" onclick="return confirm('<spring:message code="confirm.delete" />')">
	<spring:message code="likes.delete"/>
</a>