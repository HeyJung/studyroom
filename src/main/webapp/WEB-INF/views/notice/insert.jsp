<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
	.uploadResult{
		width:100%;
		background:#f2f2f2;
	}
	.uploadResult ul{
		display:flex;
		flex-flow:row;
		justify-content:center;
		align-items:center;
	}
	.uploadResult ul li{
		list-style:none;
		padding:10px;
		align-content: center;
		text-align: center;
	}
	
	.uploadResult ul li img{
		width: 100px;
		height: 100px;
	}

</style>

</head>
<body>
<%@ include file="../include/header.jsp" %>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 style="font-size:30px;">♣공지사항을 작성해주세요♣</h1>
					</div>
					<div class="panel-body">
						<form id="controlForm" action="insert" method="post">
							<input type="hidden" class="form-control" name="noticeWriter" value="user01" >
							<div class="form-group">
								<label>제목</label>
								<input type="text" class="form-control" name="noticeTitle" placeholder="제목을 입력해 주세요">
							</div>
							<div class="form-group">
								<label>파일</label>
								<input type="file" name="uploadFile" multiple>
							</div>				
							<div class="uploadResult">
								<ul>
								
								</ul>
							</div>											
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" name="noticeContent" style="height:350px;" placeholder="내용을 입력해 주세요"></textarea>
							</div>			
						</form>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<button data-oper='insert' class="btn btn-secondary btn-arraw-left">작성</button>
				<button data-oper='list' class="btn btn-default btn-arraw-left">목록</button>
			</div>
		</div>
	</div>
<script type="text/javascript">

$(document).ready(function(){
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;
	

	var form = $("#controlForm");
	
	$("button[data-oper='insert']").on("click", function(e){
		e.preventDefault();
		
		var str = "";
		$(".uploadResult ul li").each(function(i, obj){
			var obj = $(obj);
			
			str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+obj.data("filename")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+obj.data("uuid")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+obj.data("path")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+obj.data("type")+"'>";
			
		});
		form.append(str).submit();
		
		
	});
	$("button[data-oper='list']").on("click", function(e){
		e.preventDefault();
		self.location="/notice/list";	
	});

	$("input[type='file']").change(function(e){
	
		if($(".uploadResult ul li").length > 0){
			$(".uploadResult ul li").each(function(i, obj){
				
				var targetFile = $(obj).find("button").data("file");
				var type = $(obj).find("button").data("type");
				
	 			$.ajax({
	 				url: '/deleteFile',
	 				data: {fileName: targetFile, type: type},
	 				dataType: 'text',
	 				type: 'post',
	 				success: function(result){
		
	 				}
	 			});
			});

		}
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		for(var i=0;i<files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false,
			data: formData,
			type: 'post',
			dataType: 'json',
			success: function(result){
				console.log(result);
				showUploadResult(result);
			}
		});
	});
	
	$(".uploadResult").on("click", "button", function(e){
		
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log("targetFile : "+targetFile);
		var targetLi = $(this).closest("li");
		
		$.ajax({
			url: '/deleteFile',
			data: {fileName: targetFile, type: type},
			dataType: 'text',
			type: 'post',
			success: function(result){

				targetLi.remove();
			}
		});
	});
	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없습니다");
			return false;
		}
		return true;
	}
	
	function showUploadResult(uploadResultArr){
		if(!uploadResultArr || uploadResultArr.length == 0){return;}
		
		var uploadUL = $(".uploadResult ul");
		uploadUL.empty();
		var str="";
		$(uploadResultArr).each(function(i, obj){
			if(!obj.image){
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"-"+obj.fileName);
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' class='btn btn-warning btn-xs' data-file=\'"+fileCallPath+"\' data-type='file'>";
				str += "<i class='glyphicon glyphicon-remove'></i></button><br>";
				str += "<img src='/resources/img/attach.jpg'></div></li>";
			}
			else {
				
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"-"+obj.fileName);

				var originPath = obj.uploadPath+"\\"+obj.uuid+"-"+obj.fileName;
				originPath = originPath.replace(new RegExp(/\\/g), "/");
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' class='btn btn-warning btn-xs' data-file=\'"+fileCallPath+"\' data-type='image'>";
				str += "<i class='glyphicon glyphicon-remove' ></i></button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'></div></li>";
			}
		});
		
		uploadUL.append(str);
	}
	
});
</script>

</body>
</html>