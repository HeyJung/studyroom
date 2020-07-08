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
.attach-tag{
	list-style:none;
	magin-left:0;
	padding: 0px;
	display: flex;
}
.attach-tag li{
	margin-right: 10px;
}
</style>
</head>
<body>
<%@ include file="../include/header.jsp" %>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
									<td colspan="12" style="background-color:#ffff66; text-align:center;">${n.noticeTitle }</td>
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
									<td class="col-xs-11"><ul class="attach-tag"><li>첨부파일이 없습니다</li></ul></td>
								</tr>																							
							</tbody>
						</table>					
						<div style="min-height:350px; border: 1px solid #eee; padding : 10px;">
							${n.noticeContent}
						</div>
					</div>
					<div class="panel-footer">
						<button data-oper='update' class="btn btn-default btn-arraw-left">수정</button>
						<button data-oper='delete' class="btn btn-default btn-arraw-left">삭제</button>
						<button data-oper='list' class="btn btn-default btn-arraw-left">목록</button>
					</div>
				</div>
				<div class="comment">
					<p style="font-weight:bold;">댓글</p>
					<div>
						<form class="cmtform">
							<textarea class="form-control align-self-center" style="height:80px;resize: none;" name="cmtContent"></textarea>
							<input type="hidden" name="cmtWriter" value=${memId }>

							<button id="addCmtBtn" class="btn pull-right" style="margin-top:10px;">댓글 쓰기</button>
						</form>
					</div>
					<div class="commentList" style="margin-top:50px;">
						<ul class="chat" style="list-style:none;">
							<li id="1234">
								<div class="card">
									<div class="card-body">
										<div class="row">
											<div class="col-md-1">
												<img alt="유저" src="/resources/imgs/user1.jpg">
											</div>
											<div class="col-md-10"  style="border:1px solid; padding:10px;">
												<p>
													<span class="float-left"><strong>user01</strong></span>
													<span class="float-right"><small>2020.03.04 09:00:00</small></span>
												</p>
												<div class="clearfix"></div>
												<p>오늘 날씨 정말 화창해요!</p>
												<p>
													<a class="float-right pull-right btn btn-outline-primary btn-xs">삭제/수정</a>
													<a class="float-right pull-right btn btn-outline-primary btn-xs ml-2">답글달기</a>
												</p>
											</div>
										</div>
									</div>
								</div>
							</li>
						</ul>
						<div class="comment-page">
							
						</div>						
					</div>
				</div>
			</div>
		</div>	
	</div>
	
	<form id="controlForm" action="update" method="get">
		<input type="hidden" name="noticeId" value="${n.noticeId }">
		<input type="hidden" name="page" value="${cri.page }">
		<input type="hidden" name="field" value="${cri.field }">
		<input type="hidden" name="keyword" value="${cri.keyword }">
	</form>
<script type="text/javascript" src="/resources/js/notice_cmtfn.js?" charset="utf-8"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	var result =  '<c:out value="${result}"/>';
	checkAlert(result);
	
	function checkAlert(result){
		if(result === ""){
			return;
		} else {
			alert(result);
		};
	}
	var form = $("#controlForm");
	
	$("button[data-oper='update']").on("click", function(e){
		e.preventDefault();
		form.append('<input type="hidden" name="noticeWriter" value="${customer.writer_id}">"');
		form.attr("action", "/notice/update").submit();		
	});
	$("button[data-oper='list']").on("click", function(e){
		e.preventDefault();
		form.find("[name='noticeId']").remove();
		form.attr("action", "/notice/list").submit();		
	});
	$("button[data-oper='delete']").on("click", function(e){
		e.preventDefault();
		form.attr("action", "/notice/delete").attr("method", "post").submit();		
	});
	
	/*댓글과 관련된 변수*/
	var cmtul = $(".chat");
	var cmtNoticeId = ${n.noticeId};
	
	var cmtForm = $(".cmtform");
	var cmtContent = cmtForm.find("textarea[name='cmtContent']");
	var cmtWriter = cmtForm.find("input[name='cmtWriter']");
	
	var pageNum = 1;
	var cmtPage = $(".comment-page");

	/*댓글 목록*/
	showList(1);
	
	/*댓글작성*/
	$("#addCmtBtn").on("click", function(e){
		e.preventDefault();
		if(!isLogin()){
			alert("로그인이 필요한 서비스입니다");
			return false;
		}
		var cmt = {
			cmtContent : cmtContent.val(),
			cmtWriter : cmtWriter.val(),
			cmtNoticeId : cmtNoticeId
		};

		cmtService.add(cmt, function(result){
			alert(result);
			
			cmtContent.val("");
			pageNum = 1;
			showList(1);
		});
	});
	
	/*버튼클릭*/
	$(".chat").on("click", "a", function(e){
		
		var memIdSession = "<c:out value='${memId}'/>";
		var cmtWriter = $(this).closest("div").find("span[id='writer']").text();
		
		console.log("memIdSession:"+memIdSession);
		console.log("cmtWriter:"+cmtWriter);
		
		if(!(memIdSession === cmtWriter)){
			alert("댓글 작성자만 수정이 가능합니다");
			return false;
		}

		var cmtId = $(this).closest("li").data("cmtid");
		var oper = $(this).data("oper");
		if(oper === "delete"){
			cmtService.remove(cmtId, function(result){
				showList(pageNum);
				alert(result);
			});
		}
		else if(oper === "changeupdate"){
			var cmtchanged = $(this).closest("#cmtChanged");
			cmtService.get(cmtId, function(cmt){
				var str = "";
				str += '					<p>'
				str += '						<span id="writer" class="float-left"><strong>'+cmt.cmtWriter+'</strong></span>'
				str += '						<span class="float-right"><small>'+cmtService.displayTime(cmt.cmtDate)+'</small></span>'
				str += '					</p>'
				str += '					<div class="clearfix"></div>'
				str += '					<textarea style="width:750px; height:50px;resize: none;">'+cmt.cmtContent+'</textarea>'
				str += '					<p>'
				str += '						<a class="float-right pull-right btn btn-outline-primary btn-xs" data-oper="update">저장</a>'
				str += '						<a class="float-right pull-right btn btn-outline-primary btn-xs" data-oper="cancel">취소</a>'				
				str += '						<a class="float-right pull-right btn btn-outline-primary btn-xs ml-2" disabled="true">답글달기</a>'
				str += '					</p>'
				
				cmtchanged.html(str);
			});
		}
		else if(oper === "cancel"){
			showList(1);
		}
		else if(oper === "update"){

			var cmtContent = $(this).closest("p").siblings("textarea").val();
			var cmt = {cmtId : cmtId, cmtContent : cmtContent};
			console.log(cmtContent)
			cmtService.update(cmt, function(result){
				alert(result);
				showList(pageNum);
			});
		}
	});
	
	/*페이지 클릭*/
	cmtPage.on("click", "li a", function(e){
		e.preventDefault();
		var targetPageNum = $(this).attr("href");
		pageNum = targetPageNum;
		showList(pageNum);
	});
	
	/*페이지함수*/
	function showCmtPage(cmtCount){
		var endNum = Math.ceil(pageNum/10.0)*10;
		var startNum = endNum - 9;
		var prev = startNum != 1;
		var next = false;

		if(endNum * 10 >= cmtCount){
			endNum = Math.ceil(cmtCount/10.0);
		}
		if(endNum * 10 < cmtCount){
			next = true;
		}
		var str = "<ul class='pagination d-flex justify-content-center'>";
		if(prev){
			str += "<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>이전</a></li>";
		}
		for(var i=startNum; i<=endNum; i++){
			var active = pageNum == i? "active" : "";
			str += "<li class='page-item " + active + "'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		if(next){
			str += "<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>다음</a></li>";
		}
		str += "</ul>";
		cmtPage.html(str);
	}
	
	/*댓글목록 함수*/
	function showList(page){
		cmtService.getList({cmtNoticeId:cmtNoticeId, page:page||1}, function(data){
			var cmtCount = data.cmtCount;
			var list = data.list;
			var str="";
			if(page == -1){
				pageNum = Math.ceil(cmtCount/10.0);
				showList(pageNum);
				return;
			}
			if(list == null || list.length == 0){
				cmtul.html("작성된 댓글이 없습니다.");
				return;
			}
			for(var i=0, len=list.length || 0; i<len; i++)	{				
				str += '<li data-cmtid="'+list[i].cmtId+'">'
				str += '	<div class="card">'
				str += '		<div class="card-body">'
				str += '			<div class="row">'
				str += '				<div class="col-md-1">'
				str += '					<img alt="유저" src="/resources/imgs/user1.jpg">'
				str += '				</div>'
				str += '				<div id="cmtChanged" class="col-md-10"  style="border:1px solid #969696; border-radius:20px; padding:10px; margin-bottom:10px">'
				str += '					<p>'
				str += '						<span id="writer" class="float-left"><strong>'+list[i].cmtWriter+'</strong></span>'
				str += '						<span class="float-right"><small>'+cmtService.displayTime(list[i].cmtDate)+'</small></span>'
				str += '					</p>'
				str += '					<div class="clearfix"></div>'
				str += '					<p>'+list[i].cmtContent+'</p>'
				str += '					<p>'
				str += '						<a class="float-right pull-right btn btn-outline-primary btn-xs" data-oper="delete">삭제</a>'
				str += '						<a class="float-right pull-right btn btn-outline-primary btn-xs" data-oper="changeupdate">수정</a>'				
				str += '						<a class="float-right pull-right btn btn-outline-primary btn-xs ml-2">답글달기</a>'
				str += '					</p>'
				str += '				</div>'
				str += '			</div>'
				str += '		</div>'
				str += '	</div>'
				str += '</li>'			
				
			}
			
			cmtul.html(str);
			
			showCmtPage(cmtCount);
		});
	}
	
	function isLogin(){
		var memIdSession = "<c:out value="${memId}"/>";
		if(memIdSession === null || memIdSession === ""){
			return false;
		}
		return true;
	}
	
});
</script>
<script type="text/javascript">
$(document).ready(function(){
	var noticeId = "<c:out value="${n.noticeId}"/>";
	
	$.getJSON("/notice/getAttachList", {noticeId : noticeId}, function(arr){
		console.log(arr);
		if(arr.length < 1) {return;}
		var str = "";
		$(arr).each(function(i, attach){
			str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid
				+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType
				+"'><a>" + attach.fileName + "</a></li>";
		});
		$(".attach-tag").html(str);
	});
	
	$(".attach-tag").on("click", "li", function(e){
		console.log("view img");
		var liObj = $(this);
		var path = encodeURIComponent(liObj.data("path")+"/"+liObj.data("uuid")+"-"+liObj.data("filename"));
		self.location="/download?fileName="+path;
	});
});	
</script>
}
</body>
</html>