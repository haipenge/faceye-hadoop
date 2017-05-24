<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/@component.name@/@entity.config.name@/@entity.config.name@.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/@component.name@/@entity.config.name@/@entity.config.name@.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="@component.name@.@entity.config.name@.manager"></fmt:message>
			<a class="btn btn-success btn-sm pull-right" href="<c:url value="/@component.name@/@entity.config.name@/input"/>">
			  <fmt:message key="@component.name@.@entity.config.name@.add"></fmt:message>
			</a>
		</div>
	</div>
	<div class="panel-body">
	    <div class="content">
	      	<form action="<c:url value="/@component.name@/@entity.config.name@/home"/>" method="post" role="form" class="form-horizontal">
	      	   <fieldset>
	      	    <div class="form-group">
	      	     <!--@generate-entity-jsp-query-detail@-->
	      	     <div class="col-md-1">
	      	        <button type="submit" class="btn btn-sm btn-default">Search</button>
	      	     </div>
	      	    </div>
	      	   </fieldset>
	      	</form>
	    </div>
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<!--@generate-entity-jsp-property-desc@-->
					<td><fmt:message key="global.edit"></fmt:message></td>
					<td><fmt:message key="global.remove"></fmt:message></td>
				</tr>
				<c:forEach items="${page.content}" var="@entity.config.name@">
					<tr>
						<!--@generate-entity-jsp-property-value@-->
						<td>
						   <a href="<c:url value="/@component.name@/@entity.config.name@/edit/${@entity.config.name@.id}"/>">
						     <fmt:message key="global.edit"></fmt:message>
						   </a>
						 </td>
						<td>
						   <a href="<c:url value="/@component.name@/@entity.config.name@/remove/${@entity.config.name@.id}"/>">
						       <fmt:message key="global.remove"></fmt:message>
						   </a>
						</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		<f:page page="${page}" url="/@component.name@/@entity.config.name@/home" params="<%=request.getParameterMap()%>" />
	</div>
</div>
