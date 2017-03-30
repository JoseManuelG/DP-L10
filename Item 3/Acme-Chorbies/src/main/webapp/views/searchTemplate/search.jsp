
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
	<form:form action="searchTemplate/chorbi/search.do" modelAttribute="searchTemplate">
		<form:hidden path="id" />

		<acme:textbox code="searchTemplate.desiredRelationship" path="desiredRelationship"/>
		<acme:textbox code="searchTemplate.age" path="age"/>
		<acme:textbox code="searchTemplate.genre" path="genre"/>
		<acme:textbox code="searchTemplate.keyword" path="keyword"/>
		<acme:textbox code="searchTemplate.coordinates.city" path="city"/>
		<acme:textbox code="searchTemplate.coordinates.province" path="province"/>
		
		
		
		<acme:submit name="save" code="finder.search"/>
	</form:form>
</fieldset>

<br/>

<jstl:if test="${!results.isEmpty()}">
	<display:table excludedParams="*" pagesize="5" class="displaytag" name="results" requestURI="${requestURI}" id="row">
		
		<!-- Action links -->
		
		<display:column>
			<a href="book/tenant/book.do?propertyId=${row.id}">
				<spring:message	code="finder.book" />
			</a>
		</display:column>
		
		<!-- Attributes -->
		
		<acme:column sorteable="true" code="finder.property.name" path="name"/>
		
		<acme:column sorteable="true" code="finder.property.rate" path="rate"/>
		
		<acme:column sorteable="false" code="finder.property.description" path="description"/>
		
		<acme:column sorteable="false" code="finder.property.address" path="address"/>
		
		<spring:message code="property.number" var="numberHeader" />
		<display:column title="${numberHeader}" sortable="false">
			<jstl:out value="${row.books.size()}"/>
		</display:column>
		
		<display:column>
			<a href="property/view.do?propertyId=${row.id}">
				<spring:message	code="finder.display" />
			</a>
		</display:column>
		
	</display:table>
</jstl:if>