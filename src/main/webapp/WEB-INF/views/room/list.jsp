<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<c:forEach var="room" items="${list }">
			<sapn>${room.rName }</sapn>
		</c:forEach>
	</div>

</body>
</html>