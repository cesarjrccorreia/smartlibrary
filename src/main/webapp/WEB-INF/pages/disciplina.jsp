<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

	<div class="generic-container">
	
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead"><spring:message code="label.list.disciplina"/></span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th><spring:message code="label.name"/></th>
				        <th><spring:message code="label.ementa"/></th>
				        <sec:authorize access="hasRole('Bibliotecaria')">
				        	<th width="100"></th>
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${disciplinas}" var="disciplina">
					<tr>
						<td>${disciplina.name}</td>
						<td>${disciplina.ementa}</td>
					    <sec:authorize access="hasRole('Bibliotecaria')">
							<td>
								<a href="<c:url value='/disciplina/edit-${disciplina.name}' />" class="btn btn-success custom-width">
									<spring:message code="label.edit" />
								</a>
							</td>
							<td>
								<a href="<c:url value='/disciplina/delete-${disciplina.name}' />" class="btn btn-danger custom-width">
									<spring:message code="label.delete" />
								</a>
							</td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('Bibliotecaria')">
		 	<div class="well">
		 		<a href="<c:url value='/disciplina/new' />"><spring:message code="msg.add.element" arguments="disciplina"/></a>
		 	</div>
	 	</sec:authorize>
   	</div>
   	
<jsp:include page="footer.jsp" />