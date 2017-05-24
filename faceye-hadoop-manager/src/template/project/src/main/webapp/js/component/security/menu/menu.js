/**
 * 说明:Menu js 脚本 作者:@haipenge
 */
var Menu = {
	init : function() {
	},
	/**
	 * 菜单的选择
	 */
	check : function() {
		$('form input[name="menuIds"]').click(function() {
			var status = this.checked;
			$(this).parent().parent().parent().find('table input[name="menuIds"]').each(function() {
				$(this).removeAttr('checked');
				$(this).attr('checked', status);
			});
		});
	},
	load:function(url){
		$('#default-center-right').load(url, function(responseText, textStatus) {
			if (textStatus === 'error') {
				$(this).empty().append('Load '+url+  ' error now.');
			}
		});
		return false;
	}
};
$(document).ready(function() {
	//Menu.check();
});
