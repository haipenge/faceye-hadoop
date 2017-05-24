<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/@component.name@/@entity.config.name@/@entity.config.name@.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/@component.name@/@entity.config.name@/@entity.config.name@.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="@component.name@.@entity.config.name@.edit"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
		<form action="<c:url value="/@component.name@/@entity.config.name@/save"/>" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="id" value="${@entity.config.name@.id}" />
			<fieldset>
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
