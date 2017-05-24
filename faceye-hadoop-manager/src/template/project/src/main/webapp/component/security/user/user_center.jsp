<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/component/security/user/user.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/user/user.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.user.manager"></fmt:message>
			<a class="btn btn-success btn-sm pull-right" href="<c:url value="/security/user/input"/>"> <fmt:message
					key="security.user.add"></fmt:message>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<div class="content">
			<form action="<c:url value="/security/user/home"/>" method="post" role="form" class="form-horizontal"></form>
		</div>
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key='security.user.id'></fmt:message></td>
					<td><fmt:message key='security.user.name'></fmt:message></td>
					<td><fmt:message key='security.user.email'></fmt:message></td>
					<td><fmt:message key='security.user.enabled'></fmt:message></td>
					<td><fmt:message key='security.user.accountExpired'></fmt:message></td>
					<td><fmt:message key='security.user.accountLocked'></fmt:message></td>
					<td><fmt:message key='security.user.credentialsExpired'></fmt:message></td>
					<td><fmt:message key='security.user.roles'></fmt:message></td>
					<!--@generate-entity-jsp-property-desc@-->
					<td><fmt:message key="security.user.auth"></fmt:message></td>
					<td><fmt:message key="global.edit"></fmt:message></td>
					<td><fmt:message key="global.remove"></fmt:message></td>
				</tr>
				<c:forEach items="${page.content}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td>${user.enabled}</td>
						<td>${user.accountExpired}</td>
						<td>${user.accountLocked}</td>
						<td>${user.credentialsExpired}</td>
						<td><c:forEach items="${user.roles}"  var="role" varStatus="status">${role.name}<c:if
								test="${not status.last}">,</c:if></c:forEach></td>
						<!--@generate-entity-jsp-property-value@-->
						<td><a href="<c:url value="/security/user/prepareAuthRoles/${user.id}"/>"> <fmt:message
									key="security.user.auth"></fmt:message>
						</a></td>
						<td><a href="<c:url value="/security/user/edit/${user.id}"/>"> <fmt:message key="global.edit"></fmt:message>
						</a></td>
						<td><a href="<c:url value="/security/user/remove/${user.id}"/>"> <fmt:message key="global.remove"></fmt:message>
						</a></td>
					<tr>
				</c:forEach>
			</table>
		</div>
		<f:page page="${page}" url="/security/user/home" params="<%=request.getParameterMap()%>" />
	</div>
</div>
