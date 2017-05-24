<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/component/security/resource/resource.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/resource/resource.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="security.resource.edit"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
		<form action="<c:url value="/security/resource/save"/>" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="id" value="${resource.id}" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-4 control-label" for="name"> <fmt:message key="security.resource.name"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="name" value="${resource.name}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="url"> <fmt:message key="security.resource.url"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="url" value="${resource.url}" class="form-control">
					</div>
				</div>
				<!-- 
				<div class="form-group">
					<label class="col-md-4 control-label" for="roles"> <fmt:message key="security.resource.roles"></fmt:message>
					</label>
					<div class="col-md-4">
						<input type="text" name="roles" class="form-control">
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
