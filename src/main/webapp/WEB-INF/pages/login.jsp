<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<link href="<c:url value='/resources/static/css/bootstrap.min.css' />"  rel="stylesheet"></link>
		<link href="<c:url value='/resources/static/css/smartlibrary.css' />" rel="stylesheet"></link>
		<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	</head>

	<body>
		<div id="containerLogin">
			<div id="mainWrapper">
				<div class="login-container">
					<div class="login-card">
						<div class="login-form">
							<c:url var="loginUrl" value="/login" />
							<form action="${loginUrl}" method="post" class="form-horizontal">
								<c:if test="${param.error != null}">
									<div class="alert alert-danger">
										<p><spring:message code="msg.invalid.user" /></p>
									</div>
								</c:if>
								<c:if test="${param.logout != null}">
									<div class="alert alert-success">
										<p><spring:message code="msg.success.logout"/></p>
									</div>
								</c:if>
								<div class="input-group input-sm">
									<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
									<input type="text" class="form-control" id="username" name="username" placeholder="<spring:message code="msg.input.user" />" required>
								</div>
								<div class="input-group input-sm">
									<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
									<input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="msg.input.password" />" required>
								</div>
								<div class="input-group input-sm">
	                              <div class="checkbox">
	                                <label><input type="checkbox" id="rememberme" name="remember-me"> <spring:message code="label.remember" /></label>  
	                              </div>
	                            </div>
								<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
									
								<div class="form-actions">
									<input type="submit"
										class="btn btn-block btn-primary btn-default" value="<spring:message code="label.button.login" />">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>