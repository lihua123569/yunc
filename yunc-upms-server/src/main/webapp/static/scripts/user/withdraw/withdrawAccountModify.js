$(function(){
	
	// 提交验证
	$(".expressive-form").Validform({
		btnSubmit:".sub-btn",
		tiptype:3,
		beforeCheck:function(){
			var flag = 0;
			$(".expressive-form").find(".form-item-r").each(function(){
				if($(this).children().hasClass("not-set")){
					flag = 1;
					return false;
				};
			});
			if(flag !== 0){
				return false;
			}
		},
		beforeSubmit:function(curform){
			
		},
		ajaxPost:true,
		callback:function(data){
			if(data.responseCode=="0000"){
				self.location=document.referrer;
			}else{
				Alert("提醒",data.responseMsg,false);
				$(".sub-btn").text("立即提交");
				$(".codeimg").trigger('click');
				$("#smsCode_msg").text(data.responseMsg);
			}
		}
	});
	
	$(".btn-yzm").click(function(){
		var arrParams = ['/ajax/captcha/image?ctoken='+$("#ctoken").val(),$("#ctoken").val(),"withdrawModify"];
		ValidateCaptcha(validateCapLoginStatus,arrParams);
	});
});