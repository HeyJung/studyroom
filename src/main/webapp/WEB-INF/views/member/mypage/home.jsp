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
.payment-tag button{
	border:0px;
	color: blue;
}
.payment-tag button:hover{
	color:red;
}
</style>
</head>
<body>

	<%@ include file="../../include/header.jsp" %>
	
	<div class="body" >
		<div style="margin: 10px auto;text-align: center;">
			<h2>마이페이지</h2>
		</div>	
		<div class="row" style="margin: 10px auto;">
			<div class="col-md-12">
				<div class="row">
					<div class="col align-self-center" style="margin-left:auto;background: #fafafa;text-align: center; padding: 10px; border-top: solid 1px;">
						<ul style="list-style: none;">
							<li><strong>${info.memName }</strong></li>
							<li>(${info.memId })</li>
							<li>${info.memEmail }</li>
							<li><small>내 계좌 : ${info.memAccount }</small></li>
						</ul>
					</div>				
				</div>
				<div class="row">
					<div class="col-md-4" style=" text-align: center;padding:20px;">
						<img src="/resources/imgs/user.png" alt="계정" />
						<hr>
						<a href="/member/mypage/update">내 정보 수정</a>
					</div>
					<div class="col-md-4" style="text-align: center;padding:20px;">
						<img src="/resources/imgs/payment.png" alt="결제" />
						<hr>
						<a href="#">결제내역</a>
					</div>
					<div class="col-md-4" style="text-align: center;padding:20px;">
						<img src="/resources/imgs/pen.png" alt="글" />
						<hr>
						<a href="#" onclick="alert('준비중인 서비스입니다.')">내가 쓴 글</a>
					</div>
				</div>	
				<div class="row" style="margin-left: 10px;">
					<div class="col" style="margin-left:auto;padding: 10px; border-top:2px solid #848484">
						<h4><strong>예약현황</strong></h4>	
						<ul class="list-unstyled">
							<c:choose>
								<c:when test="${empty myrv }">
									<li><p>예약 내역이 없습니다</p></li>
								</c:when>
								<c:when test="${!empty myrv  }">
									<c:forEach var="n" items="${myrv }">
										<li class="payment-tag" style="background:#f2f2f2; border-radius:20px; width: 400px; padding: 8px;margin-bottom:3px;">
											<p class="reservation-box">${n.rvDate }  | ${n.rName} ▶ ${n.rvStart } - ${n.rvEnd } <button class="pull-right" data-payment="${n.payment }"  data-rvid="${n.rvId }" data-rvdate="${n.rvDate}">${n.deposit == true ? "취소하기" : "결제하기"}</button></p>
										</li>
									</c:forEach>
								</c:when>
							</c:choose>
						</ul>
					</div>				
				</div>
			</div>
		</div>
	</div>
<form action="payment" method="post">
	<input type="hidden" name="memId" value="${info.memId }">
	<input type="hidden" name="rvId">
	<input type="hidden" name="payment">
</form>
<script type="text/javascript">

$(document).ready(function(){
	var updateRs =  '<c:out value="${update}"/>';
	checkUpdate(updateRs);
	
	function checkUpdate(updateRs){
		if(updateRs === ""){
			return;
		} else {
			alert(updateRs);
		};
	}
	
	var paymentRs =  '<c:out value="${paymentResult}"/>';
	
	checkPayment(paymentRs);
	function checkPayment(paymentRs){
		if(!(paymentRs === "")){
			alert(paymentRs);
			return;
		} 
	}	
	
	$(".payment-tag").on("click", "button", function(e){
		e.preventDefault();
		var rvDate_ = $(this).data("rvdate");
		var rvDate = new Date(rvDate_);
		var today = new Date();
		
		if(rvDate.getFullYear() < today.getFullYear()) {
			alert("이미 지난 날짜는 취소/결제가 불가능합니다.");
			return false;
		} 
		else if(rvDate.getMonth() < today.getMonth()){
			alert("이미 지난 날짜는 취소/결제가 불가능합니다.");
			return false;
		} 
		else if(rvDate.getDate() < today.getDate()){
			alert("이미 지난 날짜는 취소/결제가 불가능합니다.");
			return false;
		} 
		
		var rvId = $(this).data("rvid");
		var payment = $(this).data("payment");
		if(confirm(payment.toLocaleString()+"원을 결제하시겠습니까?")){
			$("form").find("input[name='rvId']").val(rvId);
			$("form").find("input[name='payment']").val(payment);
			$("form").submit();
		}
	});
	

});
</script>

</body>
</html>