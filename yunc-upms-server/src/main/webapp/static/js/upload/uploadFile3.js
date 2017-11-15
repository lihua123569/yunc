/*********************************WebUpload 单文件上传 begin*****************************************/
$(function(){
    var $list = $("#thelist");
    var  uploader ;// 实例化   
    uploader = WebUploader.create({ 
           auto:false, //是否自动上传
            pick: {
                id: '#attach',
                name:"file",  //这个地方 name 没什么用，虽然打开调试器，input的名字确实改过来了。但是提交到后台取不到文件。如果想自定义file的name属性，还是要和fileVal 配合使用。
                label: '点击选择图片',
                multiple:false            //默认为true，true表示可以多选文件，HTML5的属性
            },
            swf: '/static/js/upload/expressInstall.swf',  //在这里必需要引入swf文件，webuploader初始化要用
            //fileVal:'multiFile',  //自定义file的name属性，我用的版本是0.1.5 ,打开客户端调试器发现生成的input 的name 没改过来。
                                             //名字还是默认的file,但不是没用哦。虽然客户端名字没改变，但是提交到到后台，是要用multiFile 这个对象来取文件的，用file 是取不到文件的
                                             // 建议作者有时间把这个地方改改啊，搞死人了。。
         
    		// 文件接收服务端。
    		server : '/upload3/json/uploadFile3',
    		
            duplicate:true,//是否可重复选择同一文件
            resize: false,
            formData: {
                "status":"file",
                "contentsDto.contentsId":"0000004730",
                "uploadNum":"0000004730",
                "existFlg":'false'
            },  
            compress: null,//图片不压缩
            chunked: true,  //分片处理
            chunkSize: 5 * 1024 * 1024, //每片5M
            chunkRetry:false,//如果失败，则不重试
            threads:1,//上传并发数。允许同时最大上传进程数。
            // runtimeOrder: 'flash',  
            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。  
            disableGlobalDnd: true
        });  

        // 当有文件添加进来的时候
       uploader.on( "fileQueued", function( file ) {
           console.log("fileQueued:");
           $list.append( "<div id='"+  file.id + "' class='item'>" +
               "<h4 class='info'>" + file.name + "</h4>" +
               "<p class='state'>等待上传...</p>" +
           "</div>" );
       });

       //当所有文件上传结束时触发
       uploader.on("uploadFinished",function(){
           console.log("uploadFinished:");
       })

        //当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。
        uploader.on("uploadAccept",function(object,ret){
            //服务器响应了
            //ret._raw  类似于 data
            var data =JSON.parse(ret._raw);
            if(data.resultCode != "1" && data.resultCode != "3"){
                if(data.resultCode == "9"){
                    uploader.reset();
                    alert("error");
                    return false;
                }
            }else{
                //E05017
                uploader.reset();
                alert("error");
                return false;
            }
           })

       //当文件上传成功时触发。
         uploader.on( "uploadSuccess", function( file ) {
           $( "#"+file.id ).find("p.state").text("已上传");
       });

       uploader.on( "uploadError", function( file ) {
           $( "#"+file.id ).find("p.state").text("上传出错");
           uploader.cancelFile(file);
           uploader.removeFile(file,true);
           uploader.reset();
       });


       $("#upload").on("click", function() {
           uploader.upload();
       })

});
/*********************************WebUpload 单文件上传 end*******************************************/

/************************************webuploader的自带参数提交到后台的参数列表*************************
 * {

//web uploader 的自带参数 
lastModifiedDate=[Wed Apr 27 2016 16:45:01 GMT+0800 (中国标准时间)], 
chunks=[3], chunk=[0], 
type=[audio/wav], uid=[yangl],  id=[WU_FILE_0], 
size=[268620636], name=[3.wav],

//formData的参数
contentsDto.contentsId=[0000004730], existFlg=[false], 
status=[file], uploadNum=[0000004730]
}
*********************************************************************************************/
