<%@ include file="/component/core/taglib/taglib.jsp"%>
<script type="text/javascript" src="<c:url value="/js/component/security/user/register.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.user.add"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
		<form action="<c:url value="/security/user/doRegister"/>" method="post" class="form-horizontal" role="form"
			id="user-register-form">
			<fieldset>
				<div class="form-group">
					<label class="col-md-4 control-label" for="username"> <fmt:message key="security.user.name"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="username" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="password"> <fmt:message key="security.user.password"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="password" name="password" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label" for="repassword"> <fmt:message key="security.user.repassword"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="password" name="repassword" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="email"> <fmt:message key="security.user.email"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="email" class="form-control"/>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-8 pull-right">
						<button type="button" id="register-button" class="btn btn-primary">
							<fmt:message key="global.submit"></fmt:message>
						</button>
						<button type="button" class="btn btn-warning">
							<fmt:message key="global.cancel"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>


