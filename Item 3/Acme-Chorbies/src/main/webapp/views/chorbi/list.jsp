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
	name="chorbi" requestURI="${requestURI}" id="row" excludedParams="*">

	<acme:column sorteable="true" code="chorbi.name" path="name"/>
	
	<acme:column sorteable="true" code="chorbi.surname" path="surname"/>
	
	<acme:column sorteable="true" code="chorbi.picture" path="picture"/>
	
	<acme:column sorteable="true" code="chorbi.description" path="description"/>
	+
	<acme:column sorteable="true" code="chorbi.desiredRelationship" path="desiredRelationship"/>

	<acme:column sorteable="true" code="chorbi.birthDate" path="birthDate"/>

	<acme:column sorteable="true" code="chorbi.genre" path="genre"/>

	
	<display:column>
		<a href="chorbi/view.do?chorbiId=${row.id}">
			<spring:message code="chorbi.view"/>
		</a>
	</display:column>
	
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<jstl:choose>
		    <jstl:when test="${{chorbi.banned == true}">
		        <display:column>
					<a href="chorbi/administrator/unban.do?chorbiId=${row.id}">
						<spring:message code="chorbi.view"/>
					</a>
				</display:column>
		    </jstl:when>    
		    <jstl:otherwise>
		        <display:column>
					<a href="chorbi/administrator/ban.do?chorbiId=${row.id}">
						<spring:message code="chorbi.view"/>
					</a>
				</display:column>
		    </jstl:otherwise>
		</jstl:choose>
	</security:authorize>

</display:table>

