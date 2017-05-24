<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/component/security/user/user.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/user/user.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.user.edit"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
		<form action="<c:url value="/security/user/save"/>" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="id" value="${user.id}" />
			<fieldset>
				
				<div class="form-group">
					<label class="col-md-4 control-label" for="name"> <fmt:message key="security.user.name"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="username" value="${user.username}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="password"> <fmt:message key="security.user.password"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="password" name="password" value="${user.password}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="email"> <fmt:message key="security.user.email"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="email" value="${user.email}" class="form-control">
					</div>
				</div>
				<!-- 
				<div class="form-group">
					<label class="col-md-4 control-label" for="enabled"> <fmt:message key="security.user.enabled"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="enabled" value="${user.enabled}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="accountExpired"> <fmt:message key="security.user.accountExpired"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="accountExpired" value="${user.accountExpired}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="accountLocked"> <fmt:message key="security.user.accountLocked"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="accountLocked" value="${user.accountLocked}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="credentialsExpired"> <fmt:message
							key="security.user.credentialsExpired"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="credentialsExpired" value="${user.credentialsExpired}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="roles"> <fmt:message key="security.user.roles"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="roles" value="${user.roles}" class="form-control">
					</div>
				</div>
				 -->
				<!--@generate-entity-jsp-property-update@-->









				<div class="form-group">
					<div class="col-md-8 pull-right">
						<button type="submit" class="btn btn-success">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>
