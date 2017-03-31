
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<fieldset>
	<form:form action="searchTemplate/chorbi/search.do" modelAttribute="search">
		<form:hidden path="id" />

		<!-- <acme:textbox code="searchTemplate.desiredRelationship" path="desiredRelationship"/>-->
		<form:label path="desiredRelationship">
		<spring:message code="searchTemplate.desiredRelationship" />
	</form:label>
    <form:select id="desiredRelationship" name="desiredRelationship" path="desiredRelationship">
    	<form:options items="${relation}" />
    </form:select>
		<acme:textbox code="searchTemplate.age" path="age"/>
	<!--  	<acme:textbox code="searchTemplate.genre" path="genre"/>-->
		<form:label path="genre">
		<spring:message code="searchTemplate.genre" />
	</form:label>
	<form:select id="genre" name="genre" path="genre">
    	<form:options items="${genres}" />
    </form:select>
    
		<acme:textbox code="searchTemplate.keyword" path="keyword"/>
		<acme:textbox code="searchTemplate.coordinates.city" path="coordinates.city"/>
		<acme:textbox code="searchTemplate.coordinates.province" path="coordinates.province"/>
		<acme:textbox code="searchTemplate.coordinates.country" path="coordinates.country"/>
		<acme:textbox code="searchTemplate.coordinates.state" path="coordinates.state"/>
		
		
		
		
		
		<acme:submit name="save" code="searchTemplate.search"/>
	</form:form>
</fieldset>

<br/>

<jstl:if test="${!results.isEmpty()}">
	<display:table excludedParams="*" pagesize="5" class="displaytag" name="results" requestURI="${requestURI}" id="row">
		
		<!-- Action links -->
		<!-- TODO- No se Si abrá display de chorbi-->
		<!--<display:column>
			<a href="book/tenant/book.do?propertyId=${row.id}">
			aaaa
			</a>
		</display:column>
		-->
		<!-- Attributes -->
		
		<acme:column sorteable="false" code="searchTemplate.chorbi.name" path="name"/>
		
		<acme:column sorteable="true" code="searchTemplate.chorbi.surname" path="surname"/>
		
		<acme:column sorteable="true" code="searchTemplate.genre" path="genre"/>
		
		<acme:column sorteable="true" code="searchTemplate.chorbi.birthDate" path="birthDate"/>
		
		<acme:column sorteable="true" code="searchTemplate.desiredRelationship" path="desiredRelationship"/>
		
		
	</display:table>
</jstl:if>