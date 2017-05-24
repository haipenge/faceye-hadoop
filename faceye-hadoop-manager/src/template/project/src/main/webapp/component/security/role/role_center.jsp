<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/component/security/role/role.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/role/role.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.role.manager"></fmt:message>
			<a class="btn btn-success btn-sm pull-right" href="<c:url value="/security/role/input"/>">
			  <fmt:message key="security.role.add"></fmt:message>
			</a>
		</div>
	</div>
	<div class="panel-body">
	    <div class="content">
	      	<form action="<c:url value="/security/role/home"/>" method="post" role="form" class="form-horizontal">
	      	   
	      	</form>
	    </div>
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key='security.role.name'></fmt:message></td>
					<td><fmt:message key="security.role.auth"></fmt:message></td>  
					<td><fmt:message key="security.menu.auth"></fmt:message></td>
 <!--@generate-entity-jsp-property-desc@-->
					<td><fmt:message key="global.edit"></fmt:message></td>
					<td><fmt:message key="global.remove"></fmt:message></td>
				</tr>
				<c:forEach items="${page.content}" var="role">
					<tr>
						<td>${role.name}</td>
						<td><a href="/security/role/prepareAuthResources/${role.id}"><fmt:message key="security.role.auth"></fmt:message></a></td>
						<td><a href="/security/menu/prepareAuthMenus/${role.id}"><fmt:message key="security.menu.auth"></fmt:message></a></td>
 <!--@generate-entity-jsp-property-value@-->
						<td>
						   <a href="<c:url value="/security/role/edit/${role.id}"/>">
						     <fmt:message key="global.edit"></fmt:message>
						   </a>
						 </td>
						<td>
						   <a href="<c:url value="/security/role/remove/${role.id}"/>">
						       <fmt:message key="global.remove"></fmt:message>
						   </a>
						</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		<f:page page="${page}" url="/security/role/home" params="<%=request.getParameterMap()%>" />
	</div>
</div>
