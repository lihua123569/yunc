	
	var params={};
 
	//切换类型
	$(".per-tab-top").find("li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var i = $(this).attr("data-value");
		layobj.render();
	});
	
	
	//no pass
	$("body").on("click",".nopass-btn",function(){
		var _dataid= $(this).attr("data-value");
		AlertReason("提醒","审核失败原因",true,a);
		function a(value){
			$.ajax({
				type: 'POST',
				url:"/forum/json/nopass",
				data:{"reason":value,"tid":_dataid,"invisible":2},
				async: false,
				success:function(result){
					result = eval("("+result+")");
					if(result.responseCode =="0000"){
						layobj.render();
					}else{
						Alert("提醒",result.responseMsg,false);
					}
					
				}});
		}
	});
	
	//pass
	$("body").on("click",".pass-btn",function(){
		var _dataid= $(this).attr("data-value");
			$.ajax({
				type: 'POST',
				url:"/forum/json/pass",
				async: false,
				data:{"tid":_dataid,"invisible":1},
				success:function(result){
					result = eval("("+result+")");
					if(result.responseCode =="0000"){
						layobj.render();
					}else{
						Alert("提醒",result.responseMsg,false);
					}
					
				}});
	});
	
	//删除
	$("body").on("click",".setb-btn",function(){
		var _dataid= $(this).attr("data-value");
		Alert("提醒","您确定要关闭此帖子吗？",true,a);
		function a(){
			$.ajax({
				type: 'POST',
				url:"/forum/json/delete?tid="+_dataid,
				async: false,
				success:function(result){
					result = eval("("+result+")");
					if(result.responseCode =="0000"){
						layobj.render();
					}else{
						Alert("提醒",result.responseMsg,false);
					}
					
		    }});
		}
	});
	


	
	$("#searchBtn").click(function(){
		layobj.render();
	});
	$("#add_forum").click(function(){
		window.location.href="/forum/addForum";
	});
	
	 
	
	
	function query(conf,dtd){
		params["type"] = $("#type_form").val();
		params["subject"] = $("#title_form").val();
		params["pageNum"] = conf ? conf.curr:$("#laypage_curr").text();
		params["pageSize"] = 10;
		$.ajax({
			type: 'POST',
			url:"/forum/json/getForumList",
			async: false,
			data:params,
			success:function(result){
				result = eval("("+result+")");
				if(result.responseCode =="0000"){
					conf.pages = Math.ceil(result.totalCount/10);
					var tableHtml = template('details_template', result);
					$('.videolist-list').html(tableHtml);
					var pageHtml = template('details_page', result);
					$('.com-page-l').html(pageHtml);
					dtd&&dtd.resolve();
				}else{
					Alert("提醒",result.responseMsg,false);
				}
				
	    }});
	}
	
	var layobj = laypage({
	    cont: 'seller-per-page',
	    pages: 0,
	    skip: true,
	    curr:1,
	    pageChange:function(conf,dtd){
	    	query(conf,dtd);
	    }
	});
	
 

//触发模态框的同时调用此方法  
function editInfo(obj) {
     
    
    //获取表格中的一行数据  
	$.ajax({
		type: 'POST',
		url:"/user/api/getUser",
		data:{"id":obj},
		async: false,
		success:function(result){
			result = eval("("+result+")");
			if(result.responseCode =="0000"){
				//向模态框中传值  
				$('#id').val(result.user.id);  
				$('#name').val(result.user.name);  
				$('#phone').val(result.user.phone);  
			}
			
    }});
	
//    $('#update').modal('show');  
    $('#exampleModal').modal('show');  
}  
//提交更改  
function update() {
	
	//获取模态框数据  
	$.ajax({
		type: 'POST',
		url:"/user/api/updateUser",
		data: {"id":$('#id').val(),"name": $('#name').val(),"phone":$('#phone').val()},
		async: false,
		success:function(result){
			result = eval("("+result+")");
			if(result.responseCode =="0000"){
				Alert("提醒",result.responseMsg,false);
				$('#exampleModal').modal('hide');
				
				$('#id').val("");  
				$('#name').val("");  
				$('#phone').val("");  
				var cr = $(".laypage_curr").text();
				var layobj = laypage({
				    cont: 'seller-per-page',
				    pages: 0,
				    skip: true,
				    curr:cr,
				    pageChange:function(conf,dtd){
				    	query(conf,dtd);
				    }
				});
			}else{
				Alert("提醒",result.responseMsg,false);
			}
			
    }});
	
   
}  

