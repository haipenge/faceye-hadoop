<%@ include file="/components/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/component/security/user/user.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/user/user.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.user.detail"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
			<fieldset>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.id"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.id}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.name"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.name}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.password"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.password}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.email"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.email}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.enabled"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.enabled}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.accountExpired"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.accountExpired}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.accountLocked"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.accountLocked}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.credentialsExpired"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.credentialsExpired}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="user">
					  <fmt:message key="security.user.roles"></fmt:message>
					 </label>
					<div class="col-md-4">
						${user.roles}
					</div>
				</div>
				<!--@generate-entity-jsp-property-detail@-->
				
				
				
				
				
				
				
				
				
			</fieldset>
	</div>
</div>
