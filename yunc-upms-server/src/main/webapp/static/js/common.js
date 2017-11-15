function AlertReason(tit,txt,qxflag,fun1,fun2,btn1txt,btn2txt){
	var _btn_box1;
	var _btn_box2;
	if(typeof(btn1txt)=="undefined"){
		_btn_box1 ='<p class="popup-btn"><a href="javascript:void(0)" class="com-btn-03 color01 confirm-btn">确 认</a></p>';
	}else{
		_btn_box1 ='<p class="popup-btn"><a href="javascript:void(0)" class="com-btn-03 color01 confirm-btn">'+btn1txt+'</a></p>';
	};
	if(typeof(btn2txt)=="undefined"){
		var _btn_box2 = '<p class="popup-btn"><a href="javascript:void(0)" class="com-btn-03 color01 confirm-btn">确 认</a><a href="javascript:void(0)" class="com-btn-03 color03 cancel-btn">取 消</a></p>';
	}else{
		var _btn_box2 = '<p class="popup-btn"><a href="javascript:void(0)" class="com-btn-03 color01 confirm-btn">'+btn1txt+'</a><a href="javascript:void(0)" class="com-btn-03 color03 cancel-btn">'+btn2txt+'</a></p>';
	};
	if(qxflag){
		btn_box = _btn_box2
	}else{
		btn_box =_btn_box1
	};
	layer.open({
		type: 1,
		skin: 'com-popup',
		area: '400px',
		title:tit,
		shadeClose: true,
		content: '<div class="compop-box">'+
						'<input name="" type="text" value="" id="alert_id" class="form-control" style="width: 240px; text-align: center; margin-left: 40px; margin-bottom: 25px;"></input>'+
						btn_box+
					'</div>',
		success:function(){
			$(".confirm-btn").click(function(){
				try{
					var reason = $("#alert_id").val();
					fun1(reason);
				}catch(e){
					
				}
				if(reason!=''){
					layer.closeAll();
				}
			});
			$(".cancel-btn").click(function(){
				try{
					fun2();
				}catch(e){
					
				}
				layer.closeAll()
			});
		}
	});
};