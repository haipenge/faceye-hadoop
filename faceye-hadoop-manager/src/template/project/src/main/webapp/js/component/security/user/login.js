/**
 * Login JS
 * 
 */
var Login = {
	init : function() {

	},
	window : function() {
		var modal = new Modal();
		modal.setTitle('用户登录');
		modal.setBody(Login.builder());
		modal.setId('login-window');
		modal.show();
	},
	login : function(callback) {
		var username = $('#modal-login-form').find('input[name="j_username"]').val();
		var password = $('#modal-login-form').find('input[name="j_password"]').val();
		var rememberme = $('#modal-login-form').find('input[name="_spring_security_remember_me"]').is(':checked');
		$.ajax({
			url : '/ajax_j_spring_security_check',
			type : 'post',
			data : {
				j_username : username,
				j_password : password,
				_spring_security_remember_me : rememberme
			},
			dataType : 'json',
			success : function(data, textStatus, xhr) {
				if (data.allow) {
					if (callback) {
						callback();
					}
					$('#login-window').modal('hide');
				} else {
					Login.failure();
				}
			},
			failure : function() {
				Login.failure();
			}
		});
	},
	builder : function() {
		var sb = new StringBuffer();
		sb.append('<form action="#" method="POST" role="form" id="modal-login-form">');
		sb.append('<div class="form-group">');
		sb.append('<input type="text" name="j_username" class="form-control" class="security" placeholder="邮箱或用户名" />');
		sb.append('</div>');
		sb.append('<div class="form-group">');
		sb.append('<input type="password" name="j_password" class="form-control" placeholder="输入密码" />');
		sb.append('</div>');
		sb.append('<div class="from-group">');
		sb.append('<input type="checkbox" name="_spring_security_remember_me" />');
		sb.append('记住我,两周内不用再登录');
		sb.append('</div>');
		sb.append('<div class="form-group" id="login-failure-msg">');
		sb.append('</div>')
		sb.append('<div class="form-group">');
		sb.append('<button id="modal-login-btn" onclick="Login.login();return false;" type="button" class="btn btn-primary">');
		sb.append('登录');
		sb.append('</button>');
		sb.append('</div>');
		sb.append('</form>');
		return sb.toString();
	},
	failure : function() {
		$('#login-failure-msg').empty();
		var sb = new StringBuffer();
		sb.append('<div class="alert alert-warning">');
		sb.append('用户名或密码错误,登录失败');
		sb.append('</div>');
		$('#login-failure-msg').append(sb.toString());
	},
	isUserLogin:function(callback){
		$.ajax({
			url:'/security/userAction!isUserLogin.do',
			type:'post',
			dataType:'json',
			success:function(data,textStatus,xhr){
				if(!data.isUserLogin){
					Login.window();
				}
			}
		})
	}
};

$(document).ready(function() {
	// $('#modal-login-btn').click(function(){
	// alert(2);
	// Login.login();
	// });
});
