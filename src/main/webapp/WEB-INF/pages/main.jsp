<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

		<div id="center" class="panel-body">
			<div class="barsearch col-md-7">
				<form>
				  <div class="input-group input-group-lg">
				    <input type="text" class="form-control" placeholder="<spring:message code="label.search" />">
				    <div class="input-group-btn">
				      <button class="btn btn-default btn-lg" type="submit">
				        <i class="glyphicon glyphicon-search"></i>
				      </button>
				    </div>
				  </div>
				</form>
			</div>
				<div class="informe col-md-8" style="border: 1px black solid; height: 650px;">
					informe
				</div>
				<div class="col-md-4" style="border: 1px black solid; height: 500px; ">
					Alertas
				</div>
		</div>
		<div class="recommender row" style="border: 1px black solid; height: 133.5px;">
			<span style="margin-left: 50%">Recomendações</span>
		</div>
   	
<jsp:include page="footer.jsp" />