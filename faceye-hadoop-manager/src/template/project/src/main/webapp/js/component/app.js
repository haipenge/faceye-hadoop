/**
 * Build StringBuffer.
 */
var StringBuffer = function(arg0) {
	this.data = new Array();
	if ('' != arg0 && null != arg0) {
		this.data.push(arg0);
	}
};
StringBuffer.prototype = {
	append : function(arg0) {
		this.data.push(arg0);
		return this;
	},
	toString : function() {
		return this.data.join("");
	}
};

var Tools = {
	/**
	 * 生成随机整数
	 */
	random : function(min, max) {
		var range = max - min;
		var rand = Math.random();
		return (min + Math.round(rand * range));
	},
	/**
	 * 生成随机节点ID
	 */
	id : function(prefix) {
		var id = '';
		if (prefix != 'undefined' && prefix != null && prefix != '') {
			id = prefix + '-';
		} else {
			id = 'FaceYe-';
		}
		var randNum = Base.Tools.random(100, 999);
		id += randNum;
		id += '-';
		id += ++Base.config.idSeed;
		return id;
	}
};
$(document).ready(function() {

});
$(document).ajaxComplete(function(event, xhr, settings) {
	if (xhr && xhr !== undefined && xhr !== null) {
		var responseText = xhr.responseText;
		if (responseText && responseText !== '' && responseText !== undefined) {
			// loginType
			var res = $.parseJSON(responseText);
			if (res && res != null && res !== undefined) {
				if (res.loginType && res.loginType === 'ajax') {
					Login.window();
				}
			}
		}
	}
});
/**
 * Window,Write with bootstrap.
 */
var Modal = function(config) {$.extend(this,config);};
Modal.prototype = {
	id:'',
	header : true,
	footer:false,
	title : '',
	body:'',
	config : {},
	/**
	 * 配置
	 */
	config : function() {
		this.conf = {};
	},
	init : function(config) {
		$.extend(this.conf, config);
	},
	/**
	 * 显示window
	 */
	show : function() {
		$('body').append(this.build());
		$('#'+this.getId()).modal('show');
	},
	build : function() {
		var sb = new StringBuffer();
		sb.append('<div class="modal fade" id="'+this.getId()+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">');
		sb.append('<div class="modal-dialog">');
		sb.append('<div class="modal-content">');
		sb.append('<div class="modal-header">');
		sb.append('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>');
		sb.append('<h4 class="modal-title" id="myModalLabel">');
		sb.append(this.getTitle());
		sb.append('</h4>');
		sb.append('</div>');
		sb.append('<div class="modal-body">');
		sb.append(this.getBody());
		sb.append('</div>');
		if(this.getFooter()){
		sb.append('<div class="modal-footer">');
		sb.append('<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>');
		sb.append('<button type="button" class="btn btn-primary">保存</button>');
		sb.append('</div>');
		}
		sb.append('</div>');
		sb.append('</div>');
		sb.append('</div>');
		return sb.toString();
	},
	
	setHeader : function(header) {
		this.header = header;
	},
	getHeaer : function() {
		return this.header;
	},
	setTitle : function(title) {
		this.title = title;
	},
	getTitle : function() {
		if(this.title==='' && this.conf && this.conf.title){
			alert(this.conf.title);
			setTitle(config.title);
		}
		return this.title;
	},
	setBody:function(body){
		this.body=body;
	},
	getBody:function(){
		return this.body;
	},
	setId:function(id){
		this.id=id;
	},
	getId:function(){
		if(this.id===''||this.id ===undefined || this.id===null){
			this.id=Tools.id('win-');
		}
		return this.id;
	},
	getFooter:function(){
		return this.footer;
	},
	setFooter:function(footer){
		this.footer=footer;
	}
};

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
/**
*说明:Resource js 脚本
*作者:@haipenge
*/
var Resource={
  init:function(){
  }
};
/**
*说明:Role js 脚本
*作者:@haipenge
*/
var Role={
  init:function(){
  }
};
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
/**
*安全管理js脚本
*/
$(document).ready(function(){
	var form=User.getForm();
	/**
	 * 用户名输入框失去焦点后
	 */
	var usernameEl=form.find('input[name="username"]');
	usernameEl.blur(function(){
		var v=User.usernameValidator();
		if(!v){
			return;
		}
		var username=$(this).val();
		var config={username:username};
		User.isUsernameEmailExist(config);
	});
	usernameEl.focus(function(){
		User.tip(this,'推荐使用个人邮箱进行注册.');
		var msg='用户名长度要介于[3-25]个有效字符之间,只能包含字母和数字';
		User.pop(this,msg);
	});
	var passwordEl=form.find('input[name="password"]');
	passwordEl.blur(function(){
		var v=User.passwordValidator();
	});
	passwordEl.focus(function(){
		var msg='密码由6-32位数字,字母,半角".","_","?"组成.';
		User.pop(this,msg);
	});
	var repasswordEl=form.find('input[name="repassword"]');
	repasswordEl.blur(function(){
		var v=User.repasswordValidator();
	});
	repasswordEl.focus(function(){
		var msg='两次输入的密码要一致.';
		User.pop(this,msg);
	});
	var emailEl=form.find('input[name="email"]');
	emailEl.blur(function(){
		var v =User.emailValidator();
		if(!v){
			return;
		}
		var email=$(this).val();
		var params={email:email};
		User.isUsernameEmailExist(params);
	});
	emailEl.focus(function(){
		var msg='请输入你常用的电子邮箱,如haipenge@gmail.com，之后你不但可以使用用户名登录,还可以使用邮箱登录.';
		User.pop(this,msg);
	});
	form.find('#register-button').click(function(){
		User.doSave();
	});
	
});
/**
 * 用户对像相关操作。
 */
var User={
	/**
	 * 保存用户
	 */
	 
	config:{
		//用户注册form id
		registerFormId:'user-register-form'
	},
	getForm:function(){
		var form=$('#'+User.config.registerFormId);
		return form;
	},
	doSave:function(){
		var validator=User.validator();
		if(!validator){
			return;
		}else{
			var username=User.getForm().find('input[name="username"]').val();
			var email=User.getForm().find('input[name="email"]').val();
			$.ajax({
			url:'/security/user/isUserNameAndEmailExist',
			type:'GET',
			dataType:'json',
			data:{
				username:username,
				email:email
			},
			success:function(data,textStatus){
				var msg='';
				if(!data.isUsernameExist && !data.isEmailExist){
					User.getForm().trigger('submit');
				}
			}
		});
		}
	},
	/**
	 * 对所有字段进行校验
	 */
	validator:function(){
		var validator=true;
		var usernameValidator=User.usernameValidator();
		if(!usernameValidator){
			validator=false;
		}
		var emailValidator=User.emailValidator();
		if(!emailValidator){
			validator=false;
		}
		var passwordValidator=User.passwordValidator();
		if(!passwordValidator){
			validator=false;
		}
		var repasswordValidator=User.repasswordValidator();
		if(!repasswordValidator){
			validator=false;
		}
		return validator;
	},
	usernameValidator:function(){
	   var msg='';
	   var validator=true;
	   var el=User.getForm().find('input[name="username"]');
	   var username=el.val();
	   if(username===null||username===''){
	   	msg='用户名不能为空.';
	   	validator=false;
	   	User.pop(el,msg,validator);
	   	return validator;
	   }
	   var filter = /^[A-Za-z0-9-]{3,25}$/;
	   if(!filter.test(username)){
	   	msg='用户名长度要介于[3-25]个有效字符之间,只能包含字母和数字';
	   	User.pop(username,msg,false);
	   	validator=false;
	   	return validator;
	   }
	   if(validator){
	   }else{
         msg='用户名无效';
	   }
	   return validator;
	},
	emailValidator:function(){
		var validator=true;
		var el=User.getForm().find('input[name="email"]');
		var email=el.val();
		var filter=/^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
		var msg='';
		var form=User.getForm();
		if(email===null||email===''){
			msg='电子邮件地址不能为空.';
			User.pop(el,msg,false)
			validator=false;
		}
		var isEmail=filter.test(email);
		if(!isEmail){
			msg='请输入正确的邮箱地址.';
			User.pop(el,msg,false);
			validator=false;
		}
		if(validator){
		}else{
          
		}
		return validator;
	},
	repasswordValidator:function(){
		var validator=true;
		var form=User.getForm();
		var el=form.find('input[name="password"]');
		var reEl=form.find('input[name="repassword"]');
		var password=el.val();
		var repassword=reEl.val();
		var msg='';
		if(repassword===null||repassword===''){
			msg='重复密码不能为空.';
			User.pop(reEl,msg,false);
			validator=false;
			return validator;
		}
		if(repassword!=null&&repassword!=''&&password!=repassword){
			msg='两次输入密码不一致.';
			User.pop(reEl,msg,false);
			validator=false;
			return validator;
		}
		if(!validator){
		}else{
			msg='输入正确';
			User.pop(reEl,msg,true);
		}
		return validator;
	},
	passwordValidator:function(){
		var form=User.getForm();
		var el=form.find('input[name="password"]');
		var password=el.val();
		var validator=true;
		var msg='';
		if(password===null||password===''){
			msg='密码不能为空.';
			User.pop(el,msg,false);
			validator=false;
			return validator;
		}
		if(password.length<6){
			msg='密码太短了,最少6位.';
			User.pop(el,msg,false);
			validator=false;
			return validator;
		}
		if(password.length>32){
			msg='密码太长了,最多32位.';
			validator=false;
			User.pop(el,msg,false);
			return validator;
		}
		if(validator){
			var msg='输入正确';
			User.pop(el,msg,true);
		}else{
		}
		return validator;
	},
	/**
	 * 用户名,电子邮件是否可用?
	 * params:{username:'username',eamil:'email'}
	 * 本方法可以扩展到其它需要唯一性校验的字段。
	 */
	isUsernameEmailExist:function(params){
		var form=User.getForm();
		var username='';
		var email='';
		var userEl=form.find('input[name="username"]');
		var emailEl=form.find('input[name="email"]');
		
		if(params.username){
			username=params.username;
		}
		if(params.email){
			email=params.email;
		}
		$.ajax({
			url:'/security/user/isUserNameAndEmailExist',
			type:'GET',
			dataType:'json',
			data:{
				username:username,
				email:email
			},
			success:function(data,textStatus){
				var msg='';
				if(data.isUsernameExist){
					msg='用户名:"'+username+'"已经被使用.';
					User.pop(userEl,msg,false);
				}else{
					if(username!=''){
					msg='用户名:'+username+'"可以使用.';
					User.pop(userEl,msg,true);
					}
				}
				if(data.isEmailExist){
					msg='电子邮件"'+email+'"已经被使用过.';
					User.pop(emailEl,msg,false);
				}else{
					if(email!=''){
					   msg='电子邮件"'+email+'"可以使用.';
					   User.pop(emailEl,msg,true);
					}
				}
			}
		});
	},
	tip:function(el,msg){
		$(el).tooltip('destroy');
		$(el).tooltip({
			title:msg
		});
		$(el).tooltip('show');
	},
	pop:function(el,msg,type){
		$(el).popover('destroy');
		var content=msg;
		if(type!==null &&type!==undefined && type!=='' && type==false){
			$(el).parent().parent().addClass('has-error');
		}else if (type!==null &&type!==undefined && type!=='' && type==true){
			$(el).parent().parent().addClass('has-success');
		}else{
			$(el).parent().parent().removeClass('has-error').removeClass('has-success');
		}
		$(el).popover({
			content:content,
			html:'true',
			container:'body'
		});
		$(el).popover('show');
	}
};
/**
*说明:User js 脚本
*作者:@haipenge
*/
var User={
  init:function(){
  }
};
