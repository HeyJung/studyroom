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

<script type="text/javascript">

$(document).ready(function(){
	var submitForm = $("form");
	
	$("#submitBtn").on("click", function(e){
		e.preventDefault();
		
		if(submitForm.find("input[name='memPwBefore']").val() === ""){
			alert("현재 비밀번호를 입력해주세요");
			return false;
		}
		if(submitForm.find("input[name='memPw']").val() === ""){
			alert("새로운 비밀번호를 입력해주세요");
			return false;
		}
		if(submitForm.find("input[name='re-memPw']").val() === ""){
			alert("새로운 비밀번호를 두번 입력해주세요");
			return false;
		}
		if(submitForm.find("input[name='memName']").val() === ""){
			alert("이름을 입력해주세요");
			return false;
		}
		if(!(submitForm.find("input[name='memPw']").val() === submitForm.find("input[name='re-memPw']").val())){
			alert("새로운 비밀번호가 일치하지 않습니다.");
			return false;
		}
		
		submitForm.submit();
	});
	
	$("#cancelBtn").on("click", function(e){
		e.preventDefault();
		self.location="/member/mypage/home";	
	});
	
	$("#deleteBtn").on("click", function(e){
		e.preventDefault();
		submitForm.empty();
		
		var idTag = $("input[name='memId']").clone();
		
		form.empty();
		form.append(idTag);

		form.attr("action", "/member/mypage/delete").attr("method", "post").submit();
	});
	
	var result =  '<c:out value="${msg}"/>';
	checkPassword(result);
	
	function checkPassword(result){
		if(result === "" || result === null){
			return;
		} else {
			alert(result);
		};
	}
	
});
</script>
</head>

<body>

	<%@ include file="../../include/header.jsp" %>
	
	<div class="container">
		<div style="margin: 10px auto;text-align: center;">
			<h2>회원 정보 수정</h2>
		</div>	
		<form class="form-horizontal" method="post" action="/member/mypage/update" style="margin: 10px auto;width:60%;padding: 10px;">
			<div class="form-group" >
				<label class="col-md-3 control-label">아이디</label>
				<div class="col-md-7">
					<input class="form-control" type="text" name="memId" readonly value="${info.memId }">
				</div>			
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">현재 비밀번호</label>
				<div class="col-md-7">
					<input class="form-control" type="password" name="memPwBefore">
				</div>			
			</div>			
			<div class="form-group">
				<label class="col-md-3 control-label">새 비밀번호</label>
				<div class="col-md-7">
					<input class="form-control" type="password" name="memPw">
				</div>			
			</div>			
			<div class="form-group">
				<label class="col-md-3 control-label">비밀번호 확인</label>
				<div class="col-md-7">
					<input class="form-control" type="password" name="re-memPw">
				</div>			
			</div>		
			<div class="form-group">
				<label class="col-md-3 control-label">이름</label>
				<div class="col-md-7">
					<input class="form-control" type="text" name="memName" value=${info.memName }>
				</div>			
			</div>	
			<div class="form-group">
				<label class="col-md-3 control-label">이메일</label>
				<div class="col-md-7">
					<input class="form-control" type="Email" name="memEmail" value="${empty info.memEmail? '':info.memEmail }">
				</div>			
			</div>	
			<div class="form-group">
				<label class="col-md-3 control-label">내 계좌</label>
				<div class="col-md-7">
					<input class="form-control" type="text" name="memAccount" readonly value="${info.memAccount}">
				</div>			
			</div>	
		</form>
		<div class="form-horizontal" style="margin:auto;width:60%;">
			<div class="form-group">
				<label class="col-md-3 control-label"></label>
				<div class="col-md-7">
					<button id="deleteBtn" class="pull-right">회원탈퇴</button>
				</div>			
			</div>	
		</div>
		<div>
			<button id="cancelBtn" type="button" class="btn btn-default col-md-offset-5">취소</button>			
			<button id="submitBtn" type="submit" class="btn btn-primary">수정</button>
		</div>					
	</div>

</body>
</html>