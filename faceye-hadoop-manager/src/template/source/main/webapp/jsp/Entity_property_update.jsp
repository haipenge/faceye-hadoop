				<div class="form-group">
					<label class="col-md-2 control-label" for="@property.name@">
					  <fmt:message key="@component.name@.@entity.config.name@.@property.name@"></fmt:message>
					</label>
					<div class="col-md-8">
						<input type="text" name="@property.name@" value="${@entity.config.name@.@property.name@}" class="form-control">
					</div>
				</div>
                <!--@generate-entity-jsp-property-update@-->
                
