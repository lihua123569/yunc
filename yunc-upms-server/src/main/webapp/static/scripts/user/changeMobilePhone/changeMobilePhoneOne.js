	 
	// 获取短信验证码验证
	$("#getCode").click(function(){
	 	var arrParams = ['/ajax/captcha/image?ctoken='+$("#ctoken").val(),$("#ctoken").val(),"changeMobilePhoneOld"];
		ValidateCaptcha(validateCapLoginStatus,arrParams);
 	});
			
	$(".modify-phone-form").Validform({
		btnSubmit:".btn-next",
		tiptype:function(msg,o,cssctl){
			tpy = o.type;
			var objtip=o.obj.siblings(".Validform_checktip");
			cssctl(objtip,o.type);
			if(o.type == 1){
				$(".btn-next").text("正在提交...");
			}else if (o.type == 3){
				objtip.text(msg);
			}
		},
		beforeSubmit:function(){
			
		},
		ajaxPost:true,
		callback:function(data){
			if(data.responseCode=="0000"){
				post("/userBase/changeMobilePhoneTwo",null);
			}else{
				Alert("提醒",data.responseMsg,false);
				$(".btn-next").text("下一步");
				$(".codeimg").trigger('click');
			}
		}
	});
	
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
					var _msg = that.attr("errormsg");
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