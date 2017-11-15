// 获取短信验证码验证
$("#getCode").click(function(){
	if($("#mobilePhone").val()==''){
		$(".fill-error").removeClass("Validform_right");
		$(".fill-error").text("请输入正确请输入手机号码");
	}else{
		$.ajax({
			type:'POST',
			url: "/userBase/ajax/isExsitMobilePhone",//手机号是否存在
			data: {phone:$("#mobilePhone").val()},
			dataType: 'json',
			success: function(data){
				if(data.responseCode=="0000"){
					$(".fill-error").removeClass("Validform_right");
					$(".fill-error").text("号码不是注册时的手机号码");
				}else{
					var arrParams = ['/ajax/captcha/image?ctoken='+$("#ctoken").val(),$("#ctoken").val(),$("#mobilePhone").val(),"resetPassword"];
					ValidateCaptcha(validateCap,arrParams);
				}
			}
		 });
	}
 	
});
	
$(".modify-phone-form").Validform({
	btnSubmit:".fill-btn-a",
	tiptype:function(msg,o,cssctl){
		var objtip=$(".fill-error");
		cssctl(objtip,o.type);
		if(o.type == 1){
			$(".fill-btn-a").text("正在提交...");
		}else if (o.type == 3){
			objtip.text(msg);
		}
	},
	beforeSubmit:function(){
		$(".fill-error").text("");
	},
	ajaxPost:true,
	callback:function(data){
		if(data.responseCode=="0000"){
			post("/userPassword/resetPasswordStep2/"+$("#mobilePhone").val(),null);
		}else{
			Alert("提醒",data.responseMsg,false);
			$(".fill-btn-a").text("下一步");
		}
	}
});


//交互
$(".fill-con").find(".fill-form-item").children("input").focus(function(){
	if($(this).hasClass("Validform_error")){
		var _startTips= $(this).parent().find(".fill-tips").attr("data-start");
		$(this).parent().find(".fill-tips").removeClass("Validform_wrong").html(_startTips).show();
	}
});
$(".fill-con").find(".fill-form-item").children("input").blur(function(){
	var that= $(this);
	setTimeout(function(){
		if(that.hasClass("Validform_error")){
			if(that.val() == ""){
				var _msg = that.attr("nullmsg");
			}else{
				var _msg = that.attr("errormsg");
			}
			that.parent().find(".fill-tips").addClass("Validform_wrong").html(_msg).show();
			that.parent().find(".right-icon").hide();
		}else{
			var _startTips= that.parent().find(".fill-tips").attr("data-start");
			that.parent().find(".fill-tips").html(_startTips).show();
			that.parent().find(".right-icon").show();
		}
	}, 100);
});