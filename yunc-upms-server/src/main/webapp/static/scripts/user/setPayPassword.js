// 获取短信验证码验证
$("#getCode").click(function(){
 	var arrParams = ['/ajax/captcha/image?ctoken='+$("#ctoken").val(),$("#ctoken").val(),"setPayPassword"];
	ValidateCaptcha(validateCapLoginStatus,arrParams);
});

// 提交设置支付密码
var demo=$(".modify-phone-form").Validform({
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
	ajaxPost:true
});


$(".btn-submit").click(function(){
	var check = demo.check();
	if(check){
		submit("/userPassword/ajax/setPayPassword");
	}
	return false;
});


//表单提交
function submit(url){
    // 创建Form  
    var form = $("<form></form>");  
	var params= [];
	$(".commit-form").each(function(){
		params.push($('<input>', {name: $(this).attr("name"), type:'hidden', value: $(this).val()}))
	});
	params.push($('<input>', {name: 'payPassword', type:'hidden',value: hex_md5($("#payPassword").val())}));
	form.append(params); 
	
	$.ajax({
		type:'POST',
		url: url,//手机号是否存在
		data: form.serialize(),
		dataType: 'json',
		success: function(data){
			if(data.responseCode=="0000"){
				post("/userPassword/setPayPasswordSuccess",null);
			}else{
				Alert("提醒",data.responseMsg,false);
				$(".btn-submit").text("确认提交");
				$(".codeimg").trigger('click');
				$("#smsCode_msg").text(data.responseMsg);
			}
		}
	 });  
}

//交互
$(".set-pay-pwd").find(".form-item-r").children("input").focus(function(){
	if($(this).hasClass("Validform_error")){
		var _startTips= $(this).parent().find(".form-item-tips").attr("data-start");
		$(this).parent().find(".form-item-tips").removeClass("Validform_wrong").html(_startTips).show();
	}
});

$(".set-pay-pwd").find(".form-item-r").children("input").blur(function(){
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