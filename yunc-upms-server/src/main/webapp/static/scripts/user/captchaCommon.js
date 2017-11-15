/**
 * 校验图片验证码：无登录状态
 * @param arr
 */
function validateCap(arr){
	var datap = {captcha:$(".imgcode-input").val(),ctoken:arr[1]};
	var dataSmsCode = {captcha:$(".imgcode-input").val(),ctoken:arr[1],mobilePhone:arr[2],scenario:arr[3]};
		$.ajax({
			type:'POST',
			url: "/ajax/captcha/checkCaptcha",
			data:  datap,
			dataType: 'json',
			success: function(data){
				//如果不对 flag = false;
				 if(data.responseCode=='0000'){
					 getSmsCode(dataSmsCode);
				 }else if(data.responseCode=='1008'){
					 $(".imgcode-input-box").addClass("code-error").find(".error-txt").text(data.responseMsg);
					 layer.closeAll();
				 }else{
					 $(".imgcode-input-box").addClass("code-error").find(".error-txt").text(data.responseMsg);
				 }
			}
		}); 
	}

/**
 * 校验图片验证码：登录状态
 * @param arr
 */
function validateCapLoginStatus(arr){
	var datap = {captcha:$(".imgcode-input").val(),ctoken:arr[1]};
	var dataSmsCode = {captcha:$(".imgcode-input").val(),ctoken:arr[1],scenario:arr[2]};
	$.ajax({
		type:'POST',
		url: "/ajax/captcha/checkCaptcha",
		data:  datap,
		dataType: 'json',
		success: function(data){
			//如果不对 flag = false;
			if(data.responseCode=='0000'){
				getSmsCodeByUserId(dataSmsCode);
			}else if(data.responseCode=='1008'){
				$(".imgcode-input-box").addClass("code-error").find(".error-txt").text(data.responseMsg);
				layer.closeAll();
			}else{
				$(".imgcode-input-box").addClass("code-error").find(".error-txt").text(data.responseMsg);
			}
		}
	}); 
}
	
	/**
	 * 发送短信：无登录状态
	 * @param dataSmsCode
	 */
	function getSmsCode(dataSmsCode){
		layer.closeAll();
		$.ajax({
				type:'POST',
				url: "/ajax/captcha/getSmsCode",
				data: dataSmsCode,
				dataType: 'json',
				success: function(data){
					//如果不对 flag = false;
					 if(data.responseCode=='0000'){
						 flag = true;
						 send("#getCode");
						 layer.closeAll();
					 }else{
						 $("#smsCode_msg").addClass("Validform_wrong").removeClass("Validform_right");
						 $("#smsCode_msg").text(data.responseMsg);
						 $("#smsCode_msg").attr('data-start',data.responseMsg);
						 flag = false;
					 }
				}
			});
	}
	
	/**
	 * 发送短信：需登录
	 * @param dataSmsCode
	 */
	function getSmsCodeByUserId(dataSmsCode){
		layer.closeAll();
		$.ajax({
			type:'POST',
			url: "/ajax/captcha/getSmsCodeByUserId",
			data: dataSmsCode,
			dataType: 'json',
			success: function(data){
				//如果不对 flag = false;
				if(data.responseCode=='0000'){
					flag = true;
					send("#getCode");
					$("#smsCode_msg").empty();
					$("#smsCode_msg").attr("data-start","");
					layer.closeAll();
				}else{
					$("#smsCode_msg").addClass("Validform_wrong");
					$("#smsCode_msg").text(data.responseMsg);
					$("#smsCode_msg").attr('data-start',data.responseMsg);
					flag = false;
				}
			}
		}); 
	}
	
	function post(URL, PARAMS) {        
	    var temp = document.createElement("form");        
	    temp.action = URL;        
	    temp.method = "post";        
	    temp.style.display = "none";        
	    for (var x in PARAMS) {        
	        var opt = document.createElement("textarea");        
	        opt.name = x;        
	        opt.value = PARAMS[x];        
	        temp.appendChild(opt);        
	    }        
	    document.body.appendChild(temp);        
	    temp.submit();        
	    return temp;        
	}        
	       