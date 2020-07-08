<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
.red{
	color:red;
}
.blue{
	color:blue;
}
</style>

</head>
<body>
<%@ include file="../include/header.jsp" %>

	<div class="container">
        <div class="row">
			<div class="col-md-6 col-md-offset-3">
	            <div class="p-5">
					<div class="text-center" style="margin-bottom:30px;">
						<h1 class="h4" style="font-weight:bold; font-size:30px;">회원가입</h1>
					</div>
					<form:form class="form-horizontal" action="/member/register" method="post"  modelAttribute="memberVO">
						<div class="form-group">
							<div class="col-md-12">
								<form:input type="text" class="form-control" path="memId" placeholder="* 아이디" style="border-radius: 10rem;padding: 1.5rem 1rem;"/>
							</div>	
							<div class="col-md-12">
								<small><form:errors path="memId" cssClass="err"/></small>
							</div>	
							
							<div class="col-md-3">
								<button id="idChkBtn" style="margin-top: 10px;">중복체크</button>		
							</div>
							<div class="col-md-9">
								<p id="idChkTag" style="margin-top: 15px;"><small></small></p>		
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<form:input type="password" class="form-control" path="memPw" placeholder="* 비밀번호" style="border-radius: 10rem;padding: 1.5rem 1rem;" />
							</div>
							
							<div class="col-md-6">
								<input type="password" class="form-control" name="re-memPw" placeholder="* 비밀번호 확인" style="border-radius: 10rem;padding: 1.5rem 1rem;" >
							</div>
							<div class="col-md-12">
								<small><form:errors path="memPw" cssClass="err"/></small>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<form:input type="text" class="form-control" path="memName" placeholder="* 이름" style="border-radius: 10rem;padding: 1.5rem 1rem;" />
							</div>
							<div class="col-md-12">
								<small><form:errors path="memName" cssClass="err"/></small>
							</div>
						</div>		
						<div class="form-group">
							<div class="col-md-12">
								<form:input type="text" class="form-control" path="memEmail" placeholder="이메일 주소" style="border-radius: 10rem;padding: 1.5rem 1rem;" />
							</div>
							<div class="col-md-12">
								<small><form:errors path="memEmail" cssClass="err"/></small>
							</div>
						</div>		
											
						<form:button id="registerBtn" type="submit" class="btn btn-primary btn-user btn-block" style="border-radius: 10rem;padding: 0.75rem 1rem;" >회원가입</form:button>
					</form:form>
					<hr>
					<div class="text-center">
						<a class="small" href="/member/login">이미 회원이신가요?</a>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">

$(document).ready(function(){
	var submitForm = $("form");
	var idOk = false;
	$("#registerBtn").on("click", function(e){
		e.preventDefault();
		
		submitForm.submit();
		
	});
	
	$("#idChkBtn").on("click", function(e){
		e.preventDefault();
		if(submitForm.find("input[name='memId']").val() === ""){
			alert("아이디를 입력해주세요");
			return false;
		}
		var inputId = $("input[name='memId']").val();
		
		idChk(inputId, function(result){						
			var idChkTag = $("#idChkTag");
			if(result > 0){
				idChkTag.html("<small class='red'>사용할 수 없는 아이디입니다</small>");
				idOk = false;
			} 
			else {
				idChkTag.html("<small class='blue'>사용할 수 있는 아이디입니다</small>");
				idOk = true;
			}
		});
		
	});
	function idChk(inputId, callback, error){
		
		$.ajax({
			type : 'get',
			url : '/member/register/idChk',
			dataType : 'json',
			data : {"inputId" : inputId},
			success : function(data){
				if(callback){
					callback(data);
					}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
		
	}


});
</script>

</body>
</html>