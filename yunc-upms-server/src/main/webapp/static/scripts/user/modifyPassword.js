//验证提交
	var demo=$(".modify-password").Validform({
		tiptype:function(msg,o,cssctl){
			tpy = o.type;
			var objtip=o.obj.siblings(".Validform_checktip");
			cssctl(objtip,o.type);
			if(o.type == 1){
				$(".btn-sure-modify").text("正在提交...");
			}else if (o.type == 3){
				objtip.text(msg);
			}
		},
		datatype:{
			"confirmPwd":function(){
				 //密码必须包含数字和字母
			    var str = $("#newLoginPassword").val();
			    var reg = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$).{6,20}$/);
			    if (reg.test(str)){
			    	$("#newLoginPasswordConfirm").removeClass("disab");
			    	$("#newLoginPasswordConfirm").attr("readonly", false);

			    	/*清除确认密码样式*/
			    	$("#confirmpwd_p").removeClass("Validform_wrong");
			    	$("#confirmpwd_icon").attr("style", "display:none");
			    	return true;
			    }else{
			    	//密码规则错误，确认密码设置不能填写
			    	$("#newLoginPasswordConfirm").addClass("disab");
					$("#newLoginPasswordConfirm").attr("readonly", true);
					$("#newLoginPasswordConfirm").val("");

					/*清除确认密码样式*/
					$("#newLoginPasswordConfirm").removeClass("Validform_error");
					$("#confirmpwd_p").removeClass("Validform_wrong");
					$("#confirmpwd_icon").attr("style", "display:none");
					return false;
			    }
			}
		},
		ajaxPost:true
	});
	
	
	$(".btn-sure-modify").click(function(){
		var check = demo.check();
		if(check){
			submit("/userPassword/ajax/modifyPassword");
		}
		return false;
	});

	
	
	//表单提交
    function submit(url){
        // 创建Form  
        var form = $("<form></form>");  
    	var params= [];
    	params.push($('<input>', {name: 'loginPassword', type:'hidden',value: hex_md5($("#loginPassword").val())}));
    	params.push($('<input>', {name: 'newLoginPassword', type:'hidden',value: hex_md5($("#newLoginPassword").val())}));
    	form.append(params); 
    	
		$.ajax({
			type:'POST',
			url: url,//手机号是否存在
			data: form.serialize(),
			dataType: 'json',
			success: function(data){
				if(data.responseCode=="0000"){
					post("/userBase/index",null);
				}else{
					Alert("提醒",data.responseMsg,false);
					$(".btn-sure-modify").text("确认修改");
					$(".codeimg").trigger('click');
					$("#smsCode_msg").text(data.responseMsg);
					return false ;  
				}
			}
		 });  
    }
	
	//交互
	$(".modify-password").find(".form-item-r").children("input").focus(function(){
		if($(this).hasClass("Validform_error")){
			var _startTips= $(this).parent().find(".form-item-tips").attr("data-start");
			$(this).parent().find(".form-item-tips").removeClass("Validform_wrong").html(_startTips).show();
		}
	});
	
	$(".modify-password").find(".form-item-r").children("input").blur(function(){
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