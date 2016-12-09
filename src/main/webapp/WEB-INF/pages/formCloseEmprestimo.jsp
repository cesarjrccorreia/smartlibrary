<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

	<div class="panel-body">
		<div class="well lead"><spring:message code="title.close.emprestimo" /></div>
		<form:form  method="POST" >
			<div class="input-group input-group-lg">
			    <input type="text" class="form-control" name="search" placeholder="<spring:message code="label.search.user" />" />
			    <div class="input-group-btn">
			      <button class="btn btn-default" type="submit">
			        <i class="glyphicon glyphicon-search"></i>
			      </button>
			    </div>
			</div>
		</form:form>
		
		<c:if test="${not empty emprestimos }">
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th><spring:message code="label.code"/></th>
				        <th><spring:message code="label.book"/></th>
				        <th><spring:message code="label.user"/></th>
				        <th><spring:message code="label.inicio"/></th>
				        <th><spring:message code="label.date.limit"/></th>
			        	<th width="100"></th>
					</tr>
		    	</thead>
	    		<tbody>
					<c:forEach items="${emprestimos}" var="emprestimo">
						<tr>
							<td>${emprestimo.id}</td>
							<td>${emprestimo.book.name}</td>
							<td>${emprestimo.user.name}</td>
							<td>${emprestimo.inicioDateString}</td>
							<td>${emprestimo.limitDateString}</td>
							<td>
								<c:choose>
									<c:when test="${ renovar == true }">
										<a href="<c:url value='/emprestimo/renovar-${emprestimo.id}' />" class="btn btn-primary custom-width">
											<spring:message code="label.renew" />
										</a>
									</c:when>
									<c:otherwise>
										<a href="<c:url value='/emprestimo/finalizar-${emprestimo.id}' />" class="btn btn-danger custom-width">
											<spring:message code="label.close" />
										</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
				</c:forEach>
    		</tbody>
   		</table>
	</c:if>
	</div>

<jsp:include page="footer.jsp" />