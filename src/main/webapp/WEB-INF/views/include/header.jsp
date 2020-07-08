<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정스 스터디룸</title>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->

<style>
/* #logo img{ */
/* 	margin: 0px 30px; */
/* 	width:300px; */
/* 	height:40px; */
/* } */
#header{
	display : flex;
	padding : 5px;

}
.logo-img{
	width : 200px;
	margin-left:50px;
}
nav{
	display : flex;
	align-items:center;
	
	
}
nav >ul{
	display : flex;
	align-items:center;
	margin : 0px;
	
}
nav >ul >li{
	list-style:none;
	position: relative
}
nav >ul:first-child{
	margin-left: 100px;
	
}
nav >ul > .main-menu{
	margin-right: 100px;
	
}
nav >ul > .dropdown{
	position: absolute;
	right: 30px;

	
	
}
nav >ul >li> a{
	color: black;
	text-decoration: none;
	font-weight: bold;
}
nav >ul >li> a:hover{
	color: blue;
	text-decoration: none;
}
.visual-img{
	width: 100%;
}
</style>
</head>

<body>
	<div id="header">
		<div class="logo">
			<a href="/"><img class="logo-img" src="/resources/imgs/mainLogo.jpg" alt="main-img"></a>
		</div>
		<nav>
			<ul>
				<li class="main-menu"><a href="/notice/list">공지사항</a></li>
				<li class="main-menu"><a href="/rvpage/calendar">예약하기</a></li>				
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						회원관리<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<c:if test="${memId == null}">
							<li><a href="/member/register">회원가입</a></li>
							<li><a href="/member/login">로그인</a></li>
						</c:if>
						<c:if test="${memId != null}">
							
							<li><a href="/member/logout">로그아웃</a></li>
							<li><a href="/member/mypage/home">마이페이지</a></li>
						</c:if>
					</ul>
				</li>
			</ul>		
		</nav>
		
	</div>
	<div id="visual">
		<img class="visual-img" src="/resources/imgs/mainBack.jpg" alt="main-img">
	</div>

	
	
	
	
	
	
	
	
	