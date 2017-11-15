
//文件上传
jQuery(function() {
	var $ = jQuery, $list = $('#fileList'), $btn = $('#ctlBtn2'), state = 'pending', uploader2;

	uploader2 = WebUploader.create({

		// 不压缩image
		resize : false,

		// swf文件路径
		swf : '/static/js/upload/expressInstall.swf',
		// 文件接收服务端。
		server : '/upload/json/uploadFile',
		timeout: 0, //todo 超时的话，只能认为该文件不曾上传过  
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#filePicker'
	});

	// 当有文件添加进来的时候
	uploader2.on('fileQueued', function(file) {
		$list.append('<div id="' + file.id + '" class="item">'
				+ '<h4 class="info">' + file.name + '</h4>'
				+ '<p class="state">等待上传...</p>' + '</div>');
	});

	// 文件上传过程中创建进度条实时显示。
	uploader2
			.on(
					'uploadProgress',
					function(file, percentage) {
						var $li = $('#' + file.id), $percent = $li
								.find('.progress .progress-bar');

						// 避免重复创建
						if (!$percent.length) {
							$percent = $(
									'<div class="progress progress-striped active">'
											+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
											+ '</div>' + '</div>')
									.appendTo($li).find('.progress-bar');
						}

						$li.find('p.state').text('上传中');

						$percent.css('width', percentage * 100 + '%');
					});

	uploader2.on('uploadSuccess', function(file,response) {
		$('#' + file.id).find('p.state').text('已上传');
		var url = eval("("+response+")");
		$("#fileList").empty();
		if($("#video-url").val()!=''){
			$("#video-url").val($("#video-url").val()+";"+url.info[0].filePath);
		}else{
			$("#video-url").val(url.info[0].filePath);
		}
		
	});

	uploader2.on('uploadError', function(file, reason) {
		$('#' + file.id).find('p.state').text('上传出错');
	});

	uploader2.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').fadeOut();
	});

	uploader2.on('all', function(type) {
		if (type === 'startUpload') {
			state = 'uploading';
		} else if (type === 'stopUpload') {
			state = 'paused';
		} else if (type === 'uploadFinished') {
			state = 'done';
		}

		if (state === 'uploading') {
			$btn.text('暂停上传');
		} else {
			$btn.text('开始上传');
		}
	});

	uploader2.on("uploadAccept", function(file, data) {
		if (data.success == "0") { // 通过return false来告诉组件，此文件上传有错。
			return false;
		} else if (data.success == "1") {
			alert(data.url);
			return true;
		}
	});

	$btn.on('click', function() {
		if (state === 'uploading') {
			uploader2.stop();
		} else {
			uploader2.upload();
		}
	});
});


// 文件上传
jQuery(function() {
	var $ = jQuery, $list = $('#thelist'), $btn = $('#ctlBtn'), state = 'pending', uploaderFile;

	uploaderFile = WebUploader.create({

		// 不压缩image
		resize : false,

		// swf文件路径
		swf : '/static/js/upload/expressInstall.swf',

		// 文件接收服务端。
		server : '/upload/json/uploadFile',
		formData: {
			"fileType":$("#fileType").val()
		},
		timeout: 0, //todo 超时的话，只能认为该文件不曾上传过  
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#picker'
	});

	// 当有文件添加进来的时候
	uploaderFile.on('fileQueued', function(file) {
		$list.append('<div id="' + file.id + '" class="item">'
				+ '<h4 class="info">' + file.name + '</h4>'
				+ '<p class="state">等待上传...</p>' + '</div>');
	});

	// 文件上传过程中创建进度条实时显示。
	uploaderFile
			.on(
					'uploadProgress',
					function(file, percentage) {
						var $li = $('#' + file.id), $percent = $li
								.find('.progress .progress-bar');

						// 避免重复创建
						if (!$percent.length) {
							$percent = $(
									'<div class="progress progress-striped active">'
											+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
											+ '</div>' + '</div>')
									.appendTo($li).find('.progress-bar');
						}

						$li.find('p.state').text('上传中');

						$percent.css('width', percentage * 100 + '%');
					});

	uploaderFile.on('uploadSuccess', function(file,response) {
		$('#' + file.id).find('p.state').text('已上传');
		var url = eval("("+response+")");
		$("#thelist").empty();
		if($("#waibu-url").val()!=''){
			$("#waibu-url").val($("#waibu-url").val()+";"+url.info[0].filePath);
		}else{
			$("#waibu-url").val(url.info[0].filePath);
		}
		
		/*if(url.info[0].filePic!=''){
			$("#video-url").val(url.info[0].filePic);
		}*/
		
		
		
	});

	uploaderFile.on('uploadError', function(file, reason) {
		$('#' + file.id).find('p.state').text('上传出错');
	});

	uploaderFile.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').fadeOut();
	});

	uploaderFile.on('all', function(type) {
		if (type === 'startUpload') {
			state = 'uploading';
		} else if (type === 'stopUpload') {
			state = 'paused';
		} else if (type === 'uploadFinished') {
			state = 'done';
		}

		if (state === 'uploading') {
			$btn.text('暂停上传');
		} else {
			$btn.text('开始上传');
		}
	});

	uploaderFile.on("uploadAccept", function(file, data) {
		if (data.success == "0") { // 通过return false来告诉组件，此文件上传有错。
			return false;
		} else if (data.success == "1") {
			alert(data.url);
			return true;
		}
	});

	$btn.on('click', function() {
		if (state === 'uploading') {
			uploaderFile.stop();
		} else {
			uploaderFile.upload();
		}
	});
});

 