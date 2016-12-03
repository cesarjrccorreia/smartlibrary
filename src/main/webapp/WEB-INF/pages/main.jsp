<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp" />

<div class="panel-body">
		<div id="center">
			<div class="barsearch col-md-7">
				<form:form  method="POST" >
				  <div class="input-group input-group-lg">
				    <input type="text" class="form-control" name="search" placeholder="<spring:message code="label.search" />" />
				    <div class="input-group-btn">
				      <button class="btn btn-default btn-lg" type="submit">
				        <i class="glyphicon glyphicon-search"></i>
				      </button>
				    </div>
				  </div>
				  </form:form>
			</div>
			
			<div class="informe col-md-8 list-group">
				<c:forEach var="informe" items="${informes}">
					<div class="list-group-item list-group-item-action">
						<h3 class="list-group-item-heading">${informe.titulo}</h3>
						<p class="list-group-item-text" >${informe.text}</p>
					</div>
				</c:forEach>
			</div>
			
			<div class="area-alertas col-md-4">
				<div class="panel">
					<h4 class="title"><spring:message code="title.disciplina.matriculada" /></h4>
					<c:set var="isEmpty" value="true" />
					
					<c:forEach var="disciplina" items="${disciplinas }" >
						<div class="list-group-item list-group-item-action">
							<p class="list-group-item-text" >${disciplina.name }</p>
						</div>
						<c:set var="isEmpty" value="false" />
					</c:forEach>
					
					<c:if test="${isEmpty }">
						<div class="list-group-item list-group-item-action">
							<p class="list-group-item-text" ><spring:message code="msg.disciplinas.empty" /></p>
						</div>
					</c:if>
				</div>
				
				<div class="panel">
					<h4 class="title"><spring:message code="title.emprestimo.efetuado" /></h4>
					
					<c:set var="isEmpty" value="true" />
					<c:forEach var="emprestimo" items="${emprestimos}"  >
					
						<div class="list-group-item list-group-item-action">
							<label class="list-group-item-text" >
								<c:out value="${emprestimo.book.name}" />
							</label>
							-
							<label><spring:message code="label.date.limit" />: <c:out value="${emprestimo.limitDateString}" /></label>
						</div>
						<c:set var="isEmpty" value="false" />
					</c:forEach>
					
					<c:if test="${isEmpty }">
						<div class="list-group-item list-group-item-action">
							<p class="list-group-item-text" ><spring:message code="msg.emprestimo.empty" /></p>
						</div>
					</c:if>
				</div>
				
				<div class="panel">
					<h4 class="title"><spring:message code="title.reserva.efetuada" /></h4>
					
					<c:set var="isEmpty" value="true" />
					<c:forEach var="reserva" items="${reservas}"  >
					
						<div class="list-group-item list-group-item-action">
							<p class="list-group-item-text" >${reserva.book.name}</p>
						</div>
						<c:set var="isEmpty" value="false" />
					</c:forEach>
					
					<c:if test="${isEmpty }">
						<div class="list-group-item list-group-item-action">
							<p class="list-group-item-text" ><spring:message code="msg.reserva.empty" /></p>
						</div>
					</c:if>
				</div>
				
			</div>
		</div>
</div>
		
		<sec:authorize access="hasRole('Aluno') or hasRole('Professor')">
			<div id="recommender" class="panel" >
				<c:forEach var="book" items="${books}">
					<div class="col-xs-12 col-sm-3 col-md-2 col-lg-2">
						<div class="db-wrapper">
							<div class="db-book">
								<ul>
									<li class="book-title">
										<i class="glyphicon glyphicon-qrcode"></i> ${book.name}
									</li>
								</ul>
								<div class="book-footer">
									<a href="<c:url value='/books/reservar-${book.id}' />" class="btn btn-info">
										<spring:message code="label.button.reservar" />
									</a>
									<a href="<c:url value='/books/detail-${book.id}' />" class="btn btn-default">
										<spring:message code="label.button.details" />
									</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</sec:authorize>
   	
<jsp:include page="footer.jsp" />