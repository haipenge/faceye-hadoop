<%@ include file="/component/core/taglib/taglib.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('input[name="j_username"]').focus();
	});
</script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.user.login"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
		<div class="row">
		   <div class="col-md-6">
			<div class="well">
				<form action="<c:url value="/j_spring_security_check"/>"
					method="POST" role="form">
					<div class="form-group">
						<input type="text" name="j_username" class="form-control"
							value="<c:if test="${not empty param.loginFailure}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>"
							class="security"
							placeholder="<fmt:message key="security.user.name"></fmt:message>" />
					</div>
					<div class="form-group">
						<input type="password" name="j_password" class="form-control"
							placeholder="<fmt:message  key="security.user.password"></fmt:message>" />
					</div>
					<div class="from-group">
						<input type="checkbox" name="_spring_security_remember_me" />
						<fmt:message key="security.user.remember.me"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary">
							<fmt:message  key="security.user.login"></fmt:message>
						</button>
					</div>
				</form>
			</div>
			</div>
			<div class="col-md-6">DESC</div>
		</div>
	</div>
</div>
