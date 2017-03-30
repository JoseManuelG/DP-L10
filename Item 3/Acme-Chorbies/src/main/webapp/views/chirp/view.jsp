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

<spring:message code="chirp.from"/>:
<jstl:out value="${res.senderName}"/><br/>

<spring:message code="chirp.for"/>:
<jstl:out value="${res.recipientName}"/><br/>

<spring:message code="chirp.sendingMoment"/>:
<jstl:out value="${res.sendingMoment}"/><br/>

<spring:message code="chirp.subject"/>:
<jstl:out value="${res.subject}"/><br/>

<fieldset>
	<legend><spring:message code="chirp.text"/></legend>
	<jstl:out value="${res.text}"/>
</fieldset><br/>

<jstl:if test="${attachments.size()!=0}">
	<display:table pagesize="5" class="displaytag" keepStatus="false"
		name="attachments" requestURI="${requestURI}" id="row" excludedParams="*">
		
		<spring:message code="chirp.attachments" var="varAttachments" />
		<display:column title="${varAttachments}" sortable="false">
			<a href="${row.url}">
				<jstl:choose>
					<jstl:when test="${row.name.equals('')}">
						${row.url}
					</jstl:when>
					<jstl:otherwise>
						${row.name}
					</jstl:otherwise>
				</jstl:choose>
			</a>
		</display:column>
		
	</display:table>
</jstl:if>

<jstl:if test="${res.sender!=null}">
	<a href="chirp/chorbi/reply.do?chirpId=${res.id}">
		<spring:message code="chirp.reply"/>
	</a> | 
</jstl:if>

<a href="chirp/chorbi/forward.do?chirpId=${res.id}">
	<spring:message code="chirp.forward"/>
</a> | 

<a href="chirp/chorbi/delete.do?chirpId=${res.id}" onclick="return confirm('<spring:chirp code="confirm.delete" />')">
	<spring:message code="chirp.delete"/>
</a>