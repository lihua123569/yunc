	
	var params={};
 
 
 
	//删除
	$("body").on("click",".setb-btn",function(){
		var _dataid= $(this).attr("data-value");
		Alert("提醒","您确定要删除此评论吗？",true,a);
		function a(){
			$.ajax({
				type: 'POST',
				url:"/userComment/json/deleteById?id="+_dataid,
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
	 
	 
	
	
	function query(conf,dtd){
		params["type"] = $("#type_form").val();
		params["title"] = $("#title_form").val();
		params["pageNum"] = conf ? conf.curr:$("#laypage_curr").text();
		params["pageSize"] = 10;
		$.ajax({
			type: 'POST',
			url:"/userComment/json/getUserCommentPageByForumId",
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
	
 
