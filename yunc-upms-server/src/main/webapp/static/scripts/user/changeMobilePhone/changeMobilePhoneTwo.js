//获取短信验证码验证
	$("#getCode").click(function(){
		var isLegalMobilePhone = checkphone("#mobilePhone");
		if(isLegalMobilePhone){
			$.ajax({
				type:'POST',
				url: "/userBase/ajax/isExsitMobilePhone",//手机号是否存在
				data: {phone:$("#mobilePhone").val()},
				dataType: 'json',
				success: function(data){
					if(data.responseCode=="0000"){
						var arrParams = ['/ajax/captcha/image?ctoken='+$("#ctoken").val(),$("#ctoken").val(),$("#mobilePhone").val(),"changeMobilePhoneNew"];
						ValidateCaptcha(validateCap,arrParams);
					}else{
						$("#phone_error").removeClass("Validform_right");
						$("#phone_error").addClass("Validform_wrong");
						if(data.responseCode=='4017'){
							$("#phone_error").text('手机号已存在');
						}else{
							$("#phone_error").text(data.responseMsg);
						}
					}
				}
			 });
		}
		
	});
	
	$(".modify-phone-form").Validform({
		btnSubmit:".btn-submit",
		tiptype:function(msg,o,cssctl){
			tpy = o.type;
			var objtip=o.obj.siblings(".Validform_checktip");
			cssctl(objtip,o.type);
			if(o.type == 1){
				$(".btn-submit").text("正在提交...");
			}else if (o.type == 3){
				objtip.text(msg);
			}
		},
		datatype:{
			"m":function(){
				return checkphone("#mobilePhone");
			},
			"mobilePhone":function(){
				return checkMobilePhone();
			}
		},
		beforeSubmit:function(){
			
		},ajaxPost:true,
		callback:function(data){
			if(data.responseCode=="0000"){
				post("/userBase/index",null);
			}else{
				Alert("提醒",data.responseMsg,false);
				$(".btn-submit").text("下一步");
				$(".codeimg").trigger('click');
				$("#smsCode_msg").text(data.responseMsg);
			}
		}
	});
	
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
	
	//交互
	$(".modify-phone").find(".form-item-r").children("input").focus(function(){
		if($(this).hasClass("Validform_error")){
			var _startTips= $(this).parent().find(".form-item-tips").attr("data-start");
			$(this).parent().find(".form-item-tips").removeClass("Validform_wrong").html(_startTips).show();
		}
	});
	
	$(".modify-phone").find(".form-item-r").children("input").blur(function(){
		var that= $(this);
		setTimeout(function(){
		
			if(that.hasClass("Validform_error")){
				if(that.val() == ""){
					var _msg = that.attr("nullmsg");
				}else{
					
					if(that.parents(".form-item-r").find(".Validform_checktip").attr("data-error") == ""){
						var _msg = that.attr("errormsg");
					}else{
						var _msg = that.parents(".form-item-r").find(".Validform_checktip").attr("data-error");
					}
				}
				that.parent().find(".form-item-tips").addClass("Validform_wrong").html(_msg).show();
				that.parent().find(".right-icon").hide();
			}else{
				var _startTips= that.parent().find(".form-item-tips").attr("data-start");
				that.parent().find(".form-item-tips").html(_startTips).show();
				that.parent().find(".right-icon").show();
			}
		
		}, 100);
	});
	
	