<%--
 * index.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="chirp/chorbi/write.do" modelAttribute="chirpForm">
		<form:hidden path="action" />

	<!-- Poder modificar titulo y texto-->
	<jstl:if test="${chirpForm.action ne 2 }">
		<acme:textbox code="chirp.subject" path="subject" />
		<acme:textbox code="chirp.text" path="text" />
	</jstl:if>

	<!-- No poder modificar titulo y texto-->
	<jstl:if test="${chirpForm.action eq 2 }">
		<form:hidden path="subject" />
		<form:hidden path="text" />
		<spring:message code="chirp.subject" />:
	<acme:mask text="${chirpForm.subject}" />
		<br />

		<fieldset>
			<legend>
				<spring:message code="chirp.text" />
			</legend>
			<acme:mask text="${chirpForm.text}" />
		</fieldset>
		<br />
	</jstl:if>

	<!--Poder modificar destinatario-->
	<jstl:if test="${chirpForm.action ne 1 }">
		<form:label path="recipient">
			<spring:message code="chirp.for" />
		</form:label>
		<form:select path="recipient">
			<jstl:forEach items="${chorbis}" var="chorbi">
				<form:option value="${chorbi.id}">
					<acme:mask text="${chorbi.name} ${chorbi.surname} (${chorbi.userAccount.username})" />
				</form:option>
			</jstl:forEach>
		</form:select>
		<form:errors path="recipient" cssClass="error" />
	</jstl:if>

	<!-- No poder modificar destinatario-->
	<jstl:if test="${chirpForm.action eq 1 }">
		<form:hidden path="recipient" />
		<spring:message code="chirp.for" />:
	<acme:mask text="${chirpForm.recipient.name} ${chirpForm.recipient.surname} (${chirpForm.recipient.userAccount.username})" />
		<br />
	</jstl:if>


	<br />
	<jstl:forEach items="${chirpForm.attachments}" var="attachment"
		varStatus="i">
		<acme:textbox code="chirp.attachment.name"
			path="attachments[${i.index}].name" />
		<acme:textbox code="chirp.attachment.url"
			path="attachments[${i.index}].url" />
	</jstl:forEach>

	<acme:submit code="chirp.save" name="save" />

	<acme:submit code="chirp.addAttachment" name="addAttachment" />

	<jstl:if test="${chirpForm.attachments.size()>0}">
		<acme:submit code="chirp.removeAttachment" name="removeAttachment" />
	</jstl:if>

	<acme:cancel code="chirp.cancel" url="" />
</form:form>