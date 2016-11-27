<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp" />

		<div id="center" class="panel-body">
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
				<div>
					<h4><spring:message code="title.disciplina.matriculada" /></h4>
					
				</div>
				<div>
					<h4><spring:message code="title.emprestimo" /></h4>
				</div>
			</div>
		</div>
		
		<sec:authorize access="hasRole('Aluno')">
			<div id="recommender" class="row ">
				<c:forEach var="livro" items="${livros}">
					<div class="col-xs-12 col-sm-3 col-md-2 col-lg-2">
						<div class="db-wrapper">
							<div class="db-book">
								<ul>
									<li class="book-title">
										<i class="glyphicon glyphicon-qrcode"></i> ${livro.name}
									</li>
								</ul>
								
								<div class="book-footer">
									<a href="#" class="btn btn-info btn-lg"><spring:message code="label.button.reservar" /></a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</sec:authorize>
   	
<jsp:include page="footer.jsp" />