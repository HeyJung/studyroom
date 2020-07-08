<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

	<div class="container">
	    <!-- Begin Page Content -->
        <div>
        	<h1 class="h3 mb2 text-gray-800">공지사항</h1>
        	<div class="card mb-4">
        		<div class="card-body">
        			<div class="table">
        				<div class="row" style="margin-bottom:10px;">
        					<div class="col-md-6">
        						<form id="controlForm" action="list" method="get" class="form-inline">
        							<input type="hidden" name='page' value="${pageMaker.cri.page }">
        							<div class="form-group">
										<select class="selectpicker form-control" name='field'>
											<option ${(param.field == "noticeTitle")?"selected":"" } value="noticeTitle" >제목</option>
											<option ${(param.field == "noticeWriter")?"selected":"" } value="noticeWriter" >작성자</option>
											<option ${(param.field == "noticeContent")?"selected":"" } value="noticeContent" >내용</option>
										</select>        							
        							</div>
        							<div class="form-group">
        								<c:set var="keyword" value="${empty pageMaker.cri.keyword ? '' : pageMaker.cri.keyword}"/>
         								<input name="keyword" type="search" class="form-control" placeholder="검색어를 입력하세요" value="${keyword }">
        							</div>
        							<button id="searchBtn" class="btn btn-default">검색</button>
        						</form>
        					</div>
        				</div>
        				<div class="row">
        					<div class="col-md-12">
        						<table class="table table-bordered" style="text-align:center;" role="grid">
        							<thead>
        								<tr role="row">
        									<th class="col-md-1" style="text-align:center;">번호</th>
        									<th class="col-md-6" style="text-align:center;">제목</th>
        									<th class="col-md-2" style="text-align:center;">작성자</th>
        									<th class="col-md-2" style="text-align:center;">작성일</th>
        									<th class="col-md-2" style="text-align:center;">조회수</th>	
        								</tr>
        							</thead>
        							<tbody>
        								<c:choose>
											<c:when test="${empty list }">
												<tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
											</c:when>
											<c:when test="${!empty list }">
												<c:forEach var="n" items="${list }">
													<tr>
														<td>${n.noticeId}</td>
														<td><a class="move" href="${n.noticeId}">${n.noticeTitle} </a></td>
														<td>${n.noticeWriter}</td>
														<td>${n.noticeDate}</td>
														<td>${n.noticeView}</td>
													</tr>		
												</c:forEach>
											</c:when>	
										</c:choose>								
        							</tbody>
        						</table>
        					</div>
        				</div>
        				<div class="row">
        					<div class="col-md-10">
        						<div>
        							<ul class="pagination">
        								<c:if test="${pageMaker.prev }">
        									<li class="paginate_button page-item previous"><a href="${pageMaker.startPage -1 }" class="page-link">이전</a></li>
        								</c:if>
        								
        								<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
        									<li class="paginate_button" ${pageMaker.cri.page == num ? "active":"" }><a href="${num }">${num }</a></li>
        								</c:forEach>
        								
        								<c:if test="${pageMaker.next }">
        									<li class="paginate_button page-item next"><a href="${pageMaker.endPage +1 }" class="page-link">다음</a></li>
        								</c:if>
        							</ul>
        						</div>
        					</div>
        					<div class="col-md-2">
        						<button id="insertBtn" class="btn btn-default pull-right">글쓰기</button>
        					</div>
        				</div>
        			</div>
        		</div>
        	</div>
        </div>
        <!-- /.container-fluid -->
	</div>
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
	
	$("#searchBtn").on("click", function(e){
		e.preventDefault();
		form.find("input[name='page']").val("1");
		form.submit();
	});
	
	$(".move").on("click", function(e){
		e.preventDefault();
		form.append("<input type='hidden' name='noticeId' value='"+$(this).attr("href")+"'>");
		form.attr("action", "/notice/detail");
		form.submit();
	});
	
	$(".paginate_button a").on("click", function(e){
		e.preventDefault();
		form.find("input[name='page']").val($(this).attr("href"));
		form.submit();
	})
	
	$("#insertBtn").on("click", function(){
		self.location="/notice/insert";
	});
})
</script>
</body>
</html>