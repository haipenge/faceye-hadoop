<%@ include file="/components/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/component/security/role/role.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/role/role.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.role.detail"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
			<fieldset>
								<div class="form-group">
					<label class="col-md-4 control-label" for="role">
					  <fmt:message key="security.role.name"></fmt:message>
					 </label>
					<div class="col-md-4">
						${role.name}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="role">
					  <fmt:message key="security.role.users"></fmt:message>
					 </label>
					<div class="col-md-4">
						${role.users}
					</div>
				</div>
				<!--@generate-entity-jsp-property-detail@-->
				
				
			</fieldset>
	</div>
</div>
