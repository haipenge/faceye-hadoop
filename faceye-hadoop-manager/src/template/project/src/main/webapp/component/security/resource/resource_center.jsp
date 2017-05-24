<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/component/security/resource/resource.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/resource/resource.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.resource.manager"></fmt:message>
			<a class="btn btn-success btn-sm pull-right" href="<c:url value="/security/resource/input"/>"> <fmt:message
					key="security.resource.add"></fmt:message>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<div class="content">
			<form action="<c:url value="/security/resource/home"/>" method="post" role="form" class="form-horizontal"></form>
		</div>
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key='security.resource.name'></fmt:message></td>
					<td><fmt:message key='security.resource.url'></fmt:message></td>
					<td><fmt:message key='security.resource.roles'></fmt:message></td>
					<!--@generate-entity-jsp-property-desc@-->
					<td><fmt:message key="global.edit"></fmt:message></td>
					<td><fmt:message key="global.remove"></fmt:message></td>
				</tr>
				<c:forEach items="${page.content}" var="resource">
					<tr>
						<td>${resource.name}</td>
						<td>${resource.url}</td>
						<td>--</td>
						<!--@generate-entity-jsp-property-value@-->
						<td><a href="<c:url value="/security/resource/edit/${resource.id}"/>"> <fmt:message
									key="global.edit"></fmt:message>
						</a></td>
						<td><a href="<c:url value="/security/resource/remove/${resource.id}"/>"> <fmt:message
									key="global.remove"></fmt:message>
						</a></td>
					<tr>
				</c:forEach>
			</table>
		</div>
		<f:page page="${page}" url="/security/resource/home" params="<%=request.getParameterMap()%>" />
	</div>
</div>
