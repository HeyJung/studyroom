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


</head>
<body>
<%@ include file="../include/header.jsp" %>
	
	<div class="container">
        <div class="row">
			<div class="col-md-6 col-md-offset-3">
	            <div class="p-5">
					<div class="text-center" style="margin-bottom:30px;">
						<h1 class="h4" style="font-weight:bold; font-size:30px;">로그인</h1>
					</div>
					<form action="/member/login" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="memId" placeholder="아이디" style="border-radius: 10rem;padding: 1.5rem 1rem;">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="memPw" placeholder="비밀번호" style="border-radius: 10rem;padding: 1.5rem 1rem;" >
						</div>						
						<button type="submit" class="btn btn-primary btn-user btn-block" style="border-radius: 10rem;padding: 0.75rem 1rem;" >로그인</button>
					</form>
					<div id="check" class="text-center" style="margin-top:10px;">
						<p></p>
					</div>
					<hr>
					<div class="text-center">
						<a class="small" href="#">비밀번호를 잊으셨나요?</a>
					</div>
					<div class="text-center">
						<a class="small" href="/member/register">아직 회원이 아니신가요?</a>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">

$(document).ready(function(){
	
	
	var submitForm = $("form");
	
	$("form button").on("click", function(e){
		if(submitForm.find("input[name='memId']").val() === ""){
			alert("아이디를 입력해주세요");
			return false;
		}
		if(submitForm.find("input[name='memPw']").val() === ""){
			alert("비밀번호를 입력해주세요");
			return false;
		}
	});
	
	var result =  '<c:out value="${msg}"/>';
	checkLogin(result);
	
	function checkLogin(result){
		if(result === 'false'){
			
			var str = "아이디와 비밀번호를 확인해주세요";
			$("#check").html(str);

		} else {
			return;
		};
	}

});
</script>

</body>
</html>