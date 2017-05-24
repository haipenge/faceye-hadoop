<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/component/security/menu/menu.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/menu/menu.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.menu.manager"></fmt:message>
			<a class="btn btn-success btn-sm pull-right" href="<c:url value="/security/menu/input"/>"> <fmt:message
					key="security.menu.add"></fmt:message>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<div class="content">
			<form action="<c:url value="/security/menu/home"/>" method="post" role="form" class="form-horizontal"></form>
		</div>
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key='security.menu.name'></fmt:message></td>
					<td><fmt:message key='security.menu.url'></fmt:message></td>
					<td><fmt:message key='security.menu.type'></fmt:message></td>
					<!--@generate-entity-jsp-property-desc@-->
					<td><fmt:message key="global.edit"></fmt:message></td>
					<td><fmt:message key="global.remove"></fmt:message></td>
				</tr>
				<c:forEach items="${page.content}" var="menu">
					<tr>
						<td>${menu.name}</td>
						<td>${menu.resource.url}</td>
						<td><c:if test="${menu.type eq '1'}">URL</c:if><c:if test="${menu.type eq '2' }">Ajax</c:if></td>
						<!--@generate-entity-jsp-property-value@-->
						<td><a href="<c:url value="/security/menu/edit/${menu.id}"/>"> <fmt:message key="global.edit"></fmt:message>
						</a></td>
						<td><a href="<c:url value="/security/menu/remove/${menu.id}"/>"> <fmt:message key="global.remove"></fmt:message>
						</a></td>
					<tr>
				</c:forEach>
			</table>
		</div>
		<f:page page="${page}" url="/security/menu/home" params="<%=request.getParameterMap()%>" />
	</div>
</div>
