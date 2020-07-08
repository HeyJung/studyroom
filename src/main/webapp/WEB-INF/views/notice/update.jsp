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
					<form id="controlForm" action="update" method="post">
						<input type="hidden" name="noticeId" value="${n.noticeId }">
						<input type="hidden" name="page" value="${cri.page }">
						<input type="hidden" name="field" value="${cri.field }">
						<input type="hidden" name="keyword" value="${cri.keyword }">					
						<div class="panel-body">
							<table class="table">
								<tbody>
									<tr>
										<td colspan="12" style="background-color:#eeeeee; text-align:center;"><input class="form-control" type="text" name="noticeTitle" value="${n.noticeTitle }"></td>
									</tr>						
									<tr>
										<th class="col-xs-1" style="text-align:center;">작성자  </th>
										<td class="col-xs-11" >${n.noticeWriter}</td>
									</tr>
									<tr>
										<th class="col-xs-1" style="text-align:center;">작성일자 </th>
										<td class="col-xs-11"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.noticeDate }"/></td>
									</tr>
									<tr>
										<th class="col-xs-1" style="text-align:center;">첨부파일 </th>
										<td class="col-xs-11">
											<div class="uploadDiv">
												<input type="file" name="uploadFile" multiple="multiple" >
												
											</div>
											<div class="uploadResult">
												<ul></ul>
											</div>
										</td>
									</tr>																							
								</tbody>
							</table>					
							<div style="min-height:350px; border: 1px solid #eee; padding : 10px;">
								<textarea class="form-control" name="noticeContent" style="height:350px;">${n.noticeContent}</textarea>
							</div>
						</div>
					</form>
					<div class="panel-footer">
						<button data-oper='update' class="btn btn-default btn-arraw-left">수정</button>
						<button data-oper='cancel' class="btn btn-default btn-arraw-left">취소</button>
						<button data-oper='list' class="btn btn-default btn-arraw-left">목록</button>
					</div>
				</div>
			</div>
		</div>	
	</div>
	

	

<script type="text/javascript">
$(document).ready(function(){
	var form = $("#controlForm");
	
	$("button[data-oper='list']").on("click", function(e){
		e.preventDefault();
		var pageTag = $("input[name='page']").clone();
		form.empty();
		form.append(pageTag);
		form.attr("action", "/notice/list").attr("method", "get");
		form.submit();		
	});
	$("button[data-oper='cancel']").on("click", function(e){
		e.preventDefault();
		
		var pageTag = $("input[name='page']").clone();
		var idTag = $("input[name='noticeId']").clone();
		form.empty();
		form.append(pageTag);
		form.append(idTag);
		form.attr("action", "/notice/detail").attr("method", "get").submit();			
	});
	
	$("button[data-oper='update']").on("click", function(e){
		e.preventDefault();
		
		var str = "";
		$(".uploadResult ul li").each(function(i, obj){
			var jobj = $(obj);
			
			str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"' >";
			str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"' >";
			str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"' >";
			str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"' >";
		});
		form.append(str);		
		form.attr("action", "/notice/update").submit();
	});
	
	var noticeId = "<c:out value='${n.noticeId}' />";
	
	$.getJSON("/notice/getAttachList", {noticeId : noticeId}, function(arr){
		console.log(arr);
		var str = "";
		$(arr).each(function(i, attach){
			if(attach.fileType){
				var fileCallPath = encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"-"+attach.fileName);
				str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid
					+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
				str += "<span>"+attach.fileName+"</span>";
				str += "<button type='button' class='btn btn-warning btn-xs' data-file=\'"+fileCallPath+"\' data-type='image'>";
				str += "<i class='glyphicon glyphicon-remove'></i></button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'>";
				str += "</div></li>";
			}
			else{
				str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid
				+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
				str += "<span>"+attach.fileName+"</span>";
				str += "<button type='button' class='btn btn-warning btn-xs' data-file=\'"+fileCallPath+"\' data-type='file'>";
				str += "<i class='glyphicon glyphicon-remove'></i></button><br>";	
				str += "<img src='/resources/img/attach.attach.jpg'>";
				str += "</div></li>";				
			}
		});
		$(".uploadResult ul").html(str);
	});
	
	$(".uploadResult").on("click", "button", function(e){
		console.log("delete file");
		if(confirm("퍄일을 삭제하시겠습까?")){
			var targetLi = $(this).closest("li");
			targetLi.remove();
		}
	});
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;
	
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