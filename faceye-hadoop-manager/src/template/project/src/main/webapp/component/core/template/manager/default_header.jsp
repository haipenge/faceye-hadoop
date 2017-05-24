<%@ include file="/component/core/taglib/taglib.jsp"%>
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="<c:url value="/"/>"><s:text name="faceye.nms" /></a>
</div>

<div class="navbar-collapse collapse">
	<ul class="nav navbar-nav">
		<li class="active"><a href="<c:url value="/nms/nmsHomeAction!home.do"/>"><s:text name="faceye.nms.manage" /></a></li>
		<li><a href="#">Link</a></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
				<li><a href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
				<li class="divider"></li>
				<li><a href="#">One more separated link</a></li>
			</ul></li>
	</ul>
	<c:choose>
		<c:when test="${empty pageContext.request.userPrincipal}">
			<form class="navbar-form navbar-right" role="form" action="<c:url value="/j_spring_security_check"/>" method="post">
				<div class="form-group">
					<input type="text" name="j_username" placeholder="Email" class="form-control">
				</div>
				<div class="form-group">
					<input type="password" name="j_password" placeholder="Password" class="form-control">
				</div>
				<button type="submit" class="btn btn-success">
					<fmt:message key="security.user.login" ></fmt:message>
				</button>
				<a href="<c:url value="/register"/>" class="btn btn-warning" role="button">
				<fmt:message key="security.user.register"></fmt:message></a>
			</form>
		</c:when>
		<c:otherwise>
			<p class="navbar-text navbar-right">
				Welcome
				<sec:authentication property="principal.username" />
				<!-- 
				&nbsp;&nbsp; <a href="<c:url value="/security/defaultManagerAction!home.do"/>"><s:text name="faceye.security" /></a>  -->
				<a href="<c:url value="/nms/nmsHomeAction!manage.do"/>"><s:text name="faceye.global.manager"/></a>&nbsp;&nbsp;
				<a
					href="<c:url value="/j_spring_security_logout"/>"><fmt:message
						key="security.user.logout"></fmt:message></a>
			</p>
		</c:otherwise>
	</c:choose>
</div>
