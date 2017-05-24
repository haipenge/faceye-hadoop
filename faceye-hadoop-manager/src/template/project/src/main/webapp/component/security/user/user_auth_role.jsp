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
		<div class="content">Auth ${user.username}</div>
		<form action="<c:url value="/security/user/authRoles"/>" method="post" role="form" class="form-horizontal">
			<div classs="table-responsive">

				<input type="hidden" name="userId" value="${user.id}">
				<table class="table table-bordered table-hover">
					<tr>
						<td></td>
						<td><fmt:message key='security.role.name'></fmt:message></td>
					</tr>
					<c:forEach items="${roles}" var="role">
						<c:set var="isChecked" value="false"></c:set>
						<c:forEach items="${existRoles}" var="exist">
							<c:if test="${role.id == exist.id}">
								<c:set var="isChecked" value="true"></c:set>
							</c:if>
						</c:forEach>
						<tr>
							<td><input type="checkbox" name="roleIds" value="${role.id}" <c:if test="${isChecked=='true'}">checked="true"</c:if></td>
							<td>${role.name}</td>
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
