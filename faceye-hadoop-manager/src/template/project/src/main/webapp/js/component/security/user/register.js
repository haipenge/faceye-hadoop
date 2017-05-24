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
