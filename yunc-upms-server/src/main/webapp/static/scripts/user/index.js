	
	var params={};
 
	//切换类型
	$(".per-tab-top").find("li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var i = $(this).attr("data-value");
		layobj.render();
	});
	
	//修改
	$("body").on("click",".recovery-btn",function(){
		var _dataid= $(this).attr("data-value");
		/*Alert("提醒","您确定要修改此用户吗？",true);
		*/
		
		editInfo(_dataid);
		/*function a(){
			$.ajax({
				type: 'POST',
				url:"/user/modify/"+_dataid,
				async: false,
				success:function(result){
					if(result.responseCode =="0000"){
						layobj.render();
					}else{
						Alert("提醒",result.responseMsg,false);
					}
					
		    }});
			
		}*/
	});
	
	//删除
	$("body").on("click",".setb-btn",function(){
		var _dataid= $(this).attr("data-value");
		Alert("提醒","您确定要删除此用户吗？",true,aa);
		function aa(){
			$.ajax({
				type: 'POST',
				url:"/user/json/delete?id="+_dataid,
				async: false,
				success:function(result){
					result = eval("("+result+")");
					if(result.responseCode =="0000"){
				/*		var cr = $(".laypage_curr").text();
						var layobj = laypage({
						    cont: 'seller-per-page',
						    pages: 0,
						    skip: true,
						    curr:cr,
						    pageChange:function(conf,dtd){
						    	query(conf,dtd);
						    }
						});*/
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
	
	 
	
	
	function query(conf,dtd){
		params["username"] = $("#name_form").val();
		params["phone"] = $("#phone_form").val();
		params["sex"] = $("#type_form").val();
		params["pageNum"] = conf ? conf.curr:$("#laypage_curr").text();
		if(params["pageNum"]==''){
			params["pageNum"]=1;
		}
		params["pageSize"] = 10;
		$.ajax({
			type: 'POST',
			url:"/upmsUser/json/list",
			async: false,
			data:params,
			success:function(result){
				if(result.responseCode =="0000"){
					conf.pages = Math.ceil(result.totalCount/10);
					var tableHtml = template('details_template', result);
					$('.blacklist-list').html(tableHtml);
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
	$.get(
		    "/upmsUser/json/update?userId="+obj,
		    function(result,state){
		        //这里显示从服务器返回的数据
/*		        alert(data);
		        //这里显示返回的状态
		        alert(state);
*/		        if(result.responseCode =="0000"){
					//向模态框中传值  
					$('#id').val(result.info.userId);  
					$('#name').val(result.info.username);  
					$('#phone').val(result.info.phone);  
				}
		    }
		)
//    $('#update').modal('show');  
    $('#exampleModal').modal('show');  
}  
//提交更改  
function update() {
	
	//获取模态框数据  
	$.ajax({
		type: 'POST',
		url:"/upmsUser/json/update",
		data: {"userId":$('#id').val(),"username": $('#name').val(),"phone":$('#phone').val()},
		async: false,
		success:function(result){
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

