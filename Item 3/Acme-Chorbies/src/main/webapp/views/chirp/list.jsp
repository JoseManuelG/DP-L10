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

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="chirps" requestURI="${requestURI}" id="row" excludedParams="*">

	<acme:column sorteable="true" code="chirp.subject" path="subject"/>
	
	<acme:column sorteable="true" code="chirp.from" path="senderName"/>
	
	<acme:column sorteable="true" code="chirp.for" path="recipientName"/>
	
	<display:column>
		<a href="chirp/chorbi/view.do?chirpId=${row.id}">
			<spring:message code="chirp.view"/>
		</a>
	</display:column>

</display:table>

<a href="chirp/chorbi/write.do">
	<spring:message code="chirp.new"/>
</a>
