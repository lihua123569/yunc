//主营业务选择
$(".input-select-box").on("click",".input-select",function(){
	$(this).toggleClass("on");		

	if($(this).hasClass("on")){
		var _startTips= $(".input-select-box").parent().find(".form-item-tips").attr("data-start");
		$(".input-select-box").parent().find(".form-item-tips").removeClass("Validform_wrong").html(_startTips).show();
	}

});

// 获取短信验证码验证
$("#getCode").click(function(){
	$("#smsCode_msg").attr("data-start","");
	$("#smsCode_msg").text("");
	
	var flag = true;
	$(".p-must").each(function(){
		$(this).focus().blur();
		if($(this).hasClass("Validform_error")){
			flag = false;
			return false;
		}
	});
	
	// 验证手机号是否注册、图形验证码是否正确
	if(flag){
		var arrParams = ['/ajax/captcha/image?ctoken='+$("#ctoken").val(),$("#ctoken").val(),"userRealAuthMerchant"];
		ValidateCaptcha(validateCapLoginStatus,arrParams);
	}
});

$(".modify-RealAuthMerchant-form").Validform({
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
	},beforeCheck:function(){
		var flag = 0;

		if($(".input-select-box").find(".input-select.on").length<1){
			$(".input-select-box").parent().find(".form-item-tips").addClass("Validform_wrong").html(_msg).show();
			flag = 1;
			return false;
		}

		$(".upload-item").each(function(){
			if($(this).find(".uploader-url").val()==""){
				if($(this).index()==0){
					$(this).find(".upload-tips").show().text("请上传身份证正面照片").addClass("error");
				}else if($(this).index()==1){
					$(this).find(".upload-tips").show().text("请上传身份证反面照片").addClass("error");
				}else{
					$(this).find(".upload-tips").show().text("请上传手持身份证照片").addClass("error");
				}
				flag = 1;
				return false;
			}
		});
		if(flag != 0){
			return false;
		}
		
	},
	datatype: {
        "zh2-20": /^[\u4E00-\u9FA5\uf900-\ufa2d-·]{2,20}$/
    },
	beforeSubmit:function(){
		var s = [];
		$(".input-select-box").find(".input-select.on").each(function(){
			s.push($(this).text())
		});
		$("#mainBusiness").val(s.join(','));
	},
	ajaxPost:true,
	callback:function(data){
		if(data.responseCode=="0000"){
			post("/realAuth/realAuthPersonal",null);
		}else{
			Alert("提醒",data.responseMsg,false);
			$(".btn-submit").text("确认提交");
			$("#smsCode_msg").text(data.responseMsg);
		}
	}
});

//交互
$(".identity-auth").find(".form-item-r").children("input").focus(function(){
	if($(this).hasClass("Validform_error")){
		var _startTips= $(this).parent().find(".form-item-tips").attr("data-start");
		$(this).parent().find(".form-item-tips").removeClass("Validform_wrong").html(_startTips).show();
	}
});

$(".identity-auth").find(".form-item-r").children("input").blur(function(){
	var that= $(this);
	if(that.hasClass("identitycard")){
		if(that.val()==""){
		that.addClass("Validform_error");
	}else{
		var checkFlag = new clsIDCard($(".identitycard").val());
			if (!checkFlag.IsValid()) {
				that.addClass("Validform_error");
				/*("身份信息有问题");*/
			}else{
				that.removeClass("Validform_error");
			}
		}
	
	}
	
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
	
function layer_open(img_url){
	$("#big-img").find("img").attr("src",img_url);
	layer.open({
	  type: 1,
	  title: false,
	  area: ['790px','600px'],
	  shadeClose: true,
	  content: $("#big-img")
	});
};
