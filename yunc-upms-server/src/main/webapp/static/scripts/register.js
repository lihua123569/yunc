$(function() {
	$(".code-change-a").click(function() {
		$(".codeimg").trigger('click');
	});
	
	// 获取短信验证码验证
	$("#getCode").removeAttr("disabled").click(function(){
		var flag = true;
		$(".p-must").each(function(){
			$(this).focus().blur();
			if($(this).hasClass("Validform_error")){
				flag = false;
				return false;
			}
		});
		// 验证手机号是否注册、图形验证码是否正确,发送短信
		if(flag){
			$.ajax({
				type:'POST',
				url: "/userBase/ajax/isExsitUserName",//用户名是否存在
				data: {userName:$("#userName").val()},
				dataType: 'json',
				success: function(data){
					 if(data.responseCode=='0000'){
						 $.ajax({
								type:'POST',
								url: "/userBase/ajax/isExsitMobilePhone",//手机号是否存在
								data: {phone:$("#mobilePhone").val()},
								dataType: 'json',
								success: function(data){
									 if(data.responseCode=='0000'){
											$.ajax({
												type:'POST',
												url: "/ajax/captcha/checkCaptcha",//验证码是否正确
												data: {captcha:$("#captcha").val(),ctoken:$("#ctoken").val(),userName:$("#userName").val()},
												dataType: 'json',
												success: function(data){
													//如果不对 flag = false;
													 if(data.responseCode=='0000'){
														 $.ajax({
																type:'POST',
																url: "/ajax/captcha/getSmsCode",//发送短信验证码
																data: {captcha:$("#captcha").val(),ctoken:$("#ctoken").val(),mobilePhone:$("#mobilePhone").val(),scenario:"register",clearCache:"false"},
																dataType: 'json',
																success: function(data){
																	//如果不对 flag = false;
																	 if(data.responseCode=='0000'){
																		 flag = true;
																		 send("#getCode");
																	 }else{
																		 $("#smsCode_msg").addClass("Validform_wrong");
																		 $("#smsCode_msg").text(data.responseMsg);
																		 flag = false;
																	 }
																}
															}); 
													 }else{
														 $("#captcha").addClass("Validform_error");
															$("#captcha_msg").addClass("Validform_wrong");
															$("#captcha_msg").text(data.responseMsg);
														 flag = false;
													 }
													 
												}
											}); 
									 }else{
											$("#mobilePhone").addClass("Validform_error");
											$("#mobilePhone_msg").addClass("Validform_wrong");
											if(data.responseCode=='4017'){
												$("#mobilePhone_msg").html('手机号已存在');
											}else{
												$("#mobilePhone_msg").html(data.responseMsg);
											}
											flag = false;
											
										}
								}
						 });
				}else{
					$("#userName").addClass("Validform_error");
					$("#userName_msg").addClass("Validform_wrong");
					$("#userName_msg").html(data.responseMsg);
					flag = false;
					
				}
				}
				
			});
		};
		
	});
	
	// 立即注册验证
	var demo=$(".reg-form").Validform({
		tiptype:function(msg,o,cssctl){
			tpy = o.type;
			var objtip=o.obj.siblings(".Validform_checktip");
			cssctl(objtip,o.type);
			if(o.type == 1){
				$(".reg-btn-a").text("正在提交...");
			}else if (o.type == 3){
				objtip.text(msg);
				objtip.attr("data-error",msg);
			}
		},
		datatype:{
			"username":function(gets,obj,curform,regxp){
				//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
				return checkUserName();
				//注意return可以返回true 或 false 或 字符串文字，true表示验证通过，返回字符串表示验证失败，字符串作为错误提示显示，返回false则用errmsg或默认的错误提示;
			},
			"mobilePhone":function(){
				   var str = $("#mobilePhone").val();
				    var reg = new RegExp(/^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/);
				    if (reg.test(str) && str.length==11){
				    	return checkMobilePhone();
				    }else{
				    	return false;
				    }
			},
			"confirmPwd":function(){
				 //密码必须包含数字和字母
			    var str = $("#password").val();
			    var reg = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$).{6,20}$/);
			    if (reg.test(str)){
			    	$("#password2").removeClass("disab");
			    	$("#password2").attr("readonly", false);
			    	return true;
			    }else{
			    	//密码规则错误，确认密码设置不能填写
			    	$("#password2").addClass("disab");
					$("#password2").attr("readonly",true);
					$("#password2").val("");
					return false;
			    }
			}
		},
		ajaxPost:true
	});
	
	
	
	$(".reg-btn-a").click(function(){
		var check = demo.check();
		if(check){
			submit("/user/doRegist");
		}
		return false;
	});
	
	function checkUserName(){
		var responeObj = "";
		$.ajax({
			type:'POST',
			url: "/userBase/ajax/isExsitUserName",//用户名是否存在
			data: {userName:$("#userName").val()},
			dataType: 'json',
			async:false,
			success: function(data){
				 if(data.responseCode=='0000'){
					 responeObj = true;
				 }else{
					 responeObj =  data.responseMsg;
					 $("#userName").parents(".reg-form-item").find(".Validform_checktip").attr("data-error",responeObj);
				 }
			}
		})
		return responeObj;
	}
	
	function checkMobilePhone(){
		var responeObj = "";
		 $.ajax({
				type:'POST',
				url: "/userBase/ajax/isExsitMobilePhone",//手机号是否存在
				data: {phone:$("#mobilePhone").val()},
				dataType: 'json',
				async:false,
				success: function(data){
				if(data.responseCode=='0000'){
					responeObj = true;
				}else{
					if(data.responseCode=='4017'){
						responeObj='手机号已存在';
					}else{
						responeObj=data.responseMsg;
					}
					flag = false;
				}
			}
		})
		return responeObj;
	}
	
	
	
	 //表单提交
    function submit(url){
        // 创建Form  
        var form = $("<form></form>");  
    	var params= [];
    	$(".commit-form").each(function(){
    		params.push($('<input>', {name: $(this).attr("name"), type:'hidden', value: $(this).val()}))
    	});
    	params.push($('<input>', {name: 'password', type:'hidden',value: hex_md5($("#password").val())}));
    	form.append(params); 
    	
		$.ajax({
			type:'POST',
			url: url,//手机号是否存在
			data: form.serialize(),
			dataType: 'json',
			success: function(data){
				if(data.responseCode=="0000"){
					window.location.href="/user/registSuccess/"+$("#userName").val()+"/"+$("#mobilePhone").val();
				}else{
					$(".login-error").removeClass("Validform_right");
					$(".reg-btn-a").text("立即注册");
					$(".codeimg").trigger('click');
					$(".login-error").text(data.responseMsg);
				}
			}
		 });  
    }
	
	//交互
 	$(".reg-con").find(".reg-form-item").children("input").focus(function(){
		if($(this).hasClass("Validform_error")){
			var _startTips= $(this).parent().find(".reg-tips").attr("data-start");
			$(this).parent().find(".reg-tips").removeClass("Validform_wrong").html(_startTips).show();
		}
	});
 	
	
	$(".reg-con").find(".reg-form-item").children("input").blur(function(){
		var that= $(this);
		setTimeout(function(){
		
			if(that.hasClass("Validform_error")){
				if(that.val() == ""){
					var _msg = that.attr("nullmsg");
				}else{
					if(that.parents(".reg-form-item").find(".Validform_checktip").attr("data-error") == ""){
						var _msg = that.attr("errormsg");
					}else{
						var _msg = that.parents(".reg-form-item").find(".Validform_checktip").attr("data-error");
					}
				}
				that.parent().find(".reg-tips").addClass("Validform_wrong").html(_msg).show();
				that.parent().find(".right-icon").hide();
			}else{
				var _startTips= that.parent().find(".reg-tips").attr("data-start");
				that.parent().find(".reg-tips").html(_startTips).show();
				that.parent().find(".right-icon").show();
			}
		
		}, 100);
	});
	
});

