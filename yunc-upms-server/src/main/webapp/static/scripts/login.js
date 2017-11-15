$(function() {
	$(".code-change-a").click(function() {
		$(".codeimg").trigger('click');
	});
	// 登录验证
	var demo=$(".login-form").Validform({
		tiptype:function(msg,o,cssctl){
			var objtip=$(".login-error");
			cssctl(objtip,o.type);
			
			if(o.type == 1){
				$(".login-btn-a").text("正在提交...");
			}
		},
		beforeSubmit:function(){
			$(".login-error").text("");
		},
		ajaxPost:true
	});
	
	
	$(".login-btn-a").click(function(){
		var check = demo.check();
		if(check){
			submit("/user/doLogin");
		}
		return false;
	});
	
	//--------回车提交事件开始---------------------//
    $("body").keydown(function(e) {
    	var curKey = e.which; 
        if (curKey == "13") {//curKey=13是回车键
        		var check = demo.check();
        		if(check){
        			$(".login-btn-a").text("正在提交...");
        			submit("/user/doLogin");
        		}
        		return false;
        }
    });
    //--------回车提交事件完毕---------------------//
	
	
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
					window.location.href=data.object;
				}else{
					$(".login-error").removeClass("Validform_right");
					if(data.responseCode=="1000" ){
						$(".login-error").text("账号或密码错误");
					}else{
						$(".login-error").text(data.responseMsg);
					}
					$(".codeimg").trigger("click");
					$(".login-btn-a").text("立即登录");
				}
			}
		 });  
	}
	
	//remember me
	$("#input-checkbox").click(function(){
		var ch = $("#input-checkbox").prop("checked");
		 if(ch==true){
			 $("#input-checkbox").val("on");
		 }else{
			 $("#input-checkbox").val("off");
		 }
	});
});