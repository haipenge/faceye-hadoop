<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/component/security/user/user.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/user/user.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.user.auth.roles"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
		<div class="content">Auth ${role.name}</div>
		<form action="<c:url value="/security/role/authResources"/>" method="post" role="form" class="form-horizontal">
			<div classs="table-responsive">

				<input type="hidden" name="roleId" value="${role.id}">
				<table class="table table-bordered table-hover">
					<tr>
						<td></td>
						<td><fmt:message key='security.resource.name'></fmt:message></td>
						<td><fmt:message key="security.resource.url"></fmt:message>
					</tr>
					<c:forEach items="${resources}" var="resource">
						<c:set var="isChecked" value="false"></c:set>
						<c:forEach items="${role.resources}" var="exist">
							<c:if test="${resource.id == exist.id}">
								<c:set var="isChecked" value="true"></c:set>
							</c:if>
						</c:forEach>
						<tr>
							<td><input type="checkbox" name="resourceIds" value="${resource.id}" <c:if test="${isChecked=='true'}">checked="true"</c:if></td>
							<td>${resource.name}</td>
							<td>${resource.url}</td>
						<tr>
					</c:forEach>
				</table>

			</div>

			<div class="content">
				<button type="submit" class="btn btn-success">
					<fmt:message key="global.submit.save"></fmt:message>
				</button>
			</div>
		</form>
	</div>
</div>
