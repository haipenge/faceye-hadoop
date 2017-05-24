<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/@component.name@/@entity.config.name@/@entity.config.name@.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/@component.name@/@entity.config.name@/@entity.config.name@.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="@component.name@.@entity.config.name@.detail"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
			<fieldset>
				<!--@generate-entity-jsp-property-detail@-->
			</fieldset>
	</div>
</div>
