<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


<script src="/resources/js/reservation.js?ver=3"></script>
<link href='/resources/packages/core/main.css' rel='stylesheet' />
<link href='/resources/packages/daygrid/main.css' rel='stylesheet' />

<script src='/resources/packages/core/main.js'></script>
<script src='/resources/packages/daygrid/main.js'></script>
<script src='/resources/packages/interaction/main.js'></script>

<style>
option:disabled {
  background: #C9C6C6;
}
</style>

<script>	
  document.addEventListener('DOMContentLoaded', function() {
	var loginId = '<c:out value="${memId}"/>';
	var event = getEvents(loginId);
    var calendarEl = document.getElementById('calendar');
	
    var calendar = new FullCalendar.Calendar(calendarEl, {
    	
     plugins: [ 'dayGrid', 'interaction' ],
     header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      titleFormat: function(date){
    	  return date.date.year + "년 " + (date.date.month + 1) + "월";
      },
      defaultDate: new Date(),
      eventColor: '#F7D358',
      eventBorderColor: '#2E2E2E',
      navLinks: false,
      selectable: true,
      selectMirror: true,
      editable: false,
      eventLimit: true, 
	  events : event
    });
    calendar.render(); 

    var modalbody = $(".modal-body");
    
	calendar.on('dateClick', function(info) {
		$(".endTag").children().removeAttr("disabled");
		$(".startTag").children().removeAttr("disabled");
		
		$(".startTag").val("default-empty");
		$(".endTag").val("default-empty");
		
		var clickedDate_ = info.dateStr;
		var clickedDate = new Date(clickedDate_);
		var today = new Date();

		if(clickedDate.getFullYear() < today.getFullYear()) {
			alert("예약 불가능한 날짜입니다");
			return false;
		} 
		else if(clickedDate.getMonth() < today.getMonth()){
			alert("예약 불가능한 날짜입니다");
			return false;
		} 
		else if(clickedDate.getDate() < today.getDate()){
			alert("예약 불가능한 날짜입니다");
			return false;
		} 
		else if(clickedDate.toLocaleDateString() === today.toLocaleDateString()){
			if(today.getHours() >= 17){
				alert("예약 가능한 시간이 지났습니다");
				return false;
			}
		}
		

		$("#registerModal").modal("show");
		modalbody.find("input[name='rvDate']").val(rvService.dateConversion(clickedDate));
		
		rvOkByroomId();
		rvOkByToday();
    });  
	
	$(".roomTag").change(function(e){
		$(".startTag").children().removeAttr("disabled");
		$(".endTag").children().removeAttr("disabled");
		$(".startTag").val("default-empty");
		$(".endTag").val("default-empty");
		
		rvOkByroomId();
		rvOkByToday();
		rvOkByTime();
	});	
	
	$(".startTag").change(function(e){
		$(".endTag").children().removeAttr("disabled");
		$(".endTag").val("default-empty");
		
		rvOkByroomId();
		rvOkByToday();
		rvOkByTime();
		
	});
	
	calendar.on('eventClick', function(info) {
		$("#getModal").modal("show");

		rvService.get(info.event.id, function(result){
			$("#getModal .modal-title").html(rvService.dateConversion(result.rvDate));
			getContent(info.event.id);
		});
    });  

	$('#modalRegisterBtn').on("click",function(e) {
		e.preventDefault();
		var modalbody = $(".modal-body");
		var roomId = modalbody.find("select[name='roomId']").val();
		var memId = modalbody.find("input[name='memId']").val();
		var rvDate = modalbody.find("input[name='rvDate']").val();
		var rvStart = modalbody.find("select[name='rvStart']").val();
		var rvEnd = modalbody.find("select[name='rvEnd']").val();

		var newEvent = {
			"roomId" : roomId, "memId" : memId, "rvDate" : rvDate, "rvStart" : rvStart, "rvEnd" : rvEnd
		};
		console.log(newEvent);
		rvService.add(newEvent, function(){
			calendar.addEvent({
				title: '예약', date: rvDate
			});
 		});
		
		
		console.log(event);
		$('#registerModal').modal('hide');
		return false;	
	});
	
	$("#getModalBody").on("click", ".delete-btn", function(e){
		var deleteId = $(this).data("id");
		var rvDate_ = $(this).data("date");
		var rvDate = new Date(rvDate_);
		var today = new Date();
		var deleteEvent = calendar.getEventById(deleteId); 
		var deleteTime = $(this).data("start");
		
		
		if(rvDate.getFullYear() < today.getFullYear()) {
			alert("지난 날짜는 취소할 수 없습니다.");
			return false;
		} 
		else if(rvDate.getMonth() < today.getMonth()){
			alert("지난 날짜는 취소할 수 없습니다.");
			return false;
		} 
		else if(rvDate.getDate() < today.getDate()){
			alert("지난 날짜는 취소할 수 없습니다.");
			return false;
		} 
		else if(rvDate.toLocaleDateString() === today.toLocaleDateString()){
			if(today.getHours() >= new Date(deleteTime)){
				alert("지난 시간은 취소할 수 없습니다.");
				return false;
			}
		}
		alert("예약 가능한 시간이 지났습니다" + deleteTime);
		if(confirm("예약을 취소하시겠습니까?")){
			rvService.remove(deleteId, function(){
				
				deleteEvent.remove();
				$('#getModalBody').modal('hide');
				alert("삭제가 완료되었습니다.");
			})
		}
	});
	
	function getEvents(param){
		var arr= [];
		$.ajax({
			type : 'get',
			url : '/reservation/myrv/'+param,
			async : false,
			dataType : 'json',
			success : function(data){
				$.each(data, function(index, item){
					arr.push({
						
						id:item.rvId,
						title:'예약',
						date:item.rvDate,
						
						
					});
				});
				
			},
			error : function(xhr, status, er){
				if(er){
					error(er);
				}
			}
		});
		return arr;
	}
	
	function getContent(rvId){
		var rName = [];
		
		$(".roomlist").each(function(index, item){
 			rName.push($(this).text());
		});
		
		rvService.get(rvId, function(data){
			var roomId = data.roomId;
			var rvStart = data.rvStart;
			var rvEnd = data.rvEnd;
			var rvId = data.rvId;
			var rvDate = data.rvDate;
			var getModalBody= $("#getModalBody");
			
			var str= "";

	        str += '<div class="row justify-content-center">';
	        str += '	<div class="col-md-1">';
	        str += '		<img src="/resources/imgs/S2.jpg" alt="이미지">';
	        str += '	</div>';
	        str += '	<div class="col-md-4">'+ rName[roomId-1] +'</div>';
	        str += ' 	<div class="col-md-5">'+ rvStart +'~'+ rvEnd +'</div>';    
	        str += '	<div class="col-md-2">';
	        str += '		<button class="delete-btn btn btn-xs" data-id="'+rvId+'" data-date="'+rvDate+'" data-start="'+rvStart+'">삭제</button>';
	        str += '	</div>';		        	   		        		        	
	        str += '</div>';	      	
	       
	        getModalBody.html(str);
		})
	}
	
	function rvOkByToday(){
		var clickedDate_ = modalbody.find("input[name='rvDate']").val();
		var clickedDate = new Date(clickedDate_);
		var today = new Date();

		if(clickedDate.toLocaleDateString() === today.toLocaleDateString()){
			var time = [];
			var today = new Date();
			var currentTime = today.getHours();
			for(var i=9; i<=currentTime; i++){
				time.push(i);
			}
			console.log(time);
			$.each(time, function(index, item){
				$("."+item).attr('disabled', 'true');
			});
		} else return;
	}
	
	function rvOkByroomId(){
		var roomId = modalbody.find("select[name='roomId']").val();
		var clickedDate = modalbody.find("input[name='rvDate']").val();
		var param = {rvDate : rvService.dateConversion(clickedDate) , roomId : roomId};
		rvService.getRvOk(param, function(data){
			$.each(data, function(index, item){
				$("."+item).attr('disabled', 'true');
			});
		});
	}
	
	function rvOkByTime(){
		var rvStart = modalbody.find("select[name='rvStart']");
		var selectedTime = rvStart.find(":selected").attr("class");
		var time = [];
		for(var i=selectedTime; i> 9; i--){
			time.push(i-1);
		}
		$.each(time, function(index, item){
			$(".endTag ."+item).attr('disabled', 'true');
		});
	}
  });

</script>
<style>

  body {
/*     margin: 40px 10px; */
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }
  
  .fc-time{
   display : none;
}

</style>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div id='calendar'></div>


	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="registerModalLabel">스터디룸과 시간을 선택하세요</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <div class="form-group">
	        	<label>예약 날짜</label>
				<input readonly="readonly" class="form-control" name="rvDate" value="2020-05-03">  
	        </div>	 
	        <div class="form-group">
	        	<label>예약 아이디</label>
				<input readonly="readonly" class="form-control" name="memId" value="${memId }">      		
	        </div>	      
	        <div class="form-group">
	        	<label>스터디룸</label>
				<select class="roomTag selectpicker form-control" name='roomId'>
					<c:forEach var="room" items="${roomList}">
						<option class="roomlist" value="${room.rId }" >${room.rName }</option>
					</c:forEach>
				</select>	      		
	        </div>
	        <div id="selectDiv" class="form-group">
	        	<label>공부시간</label>
	        	<label><small>시작시간</small></label>
				<select class="startTag selectpicker form-control" name='rvStart'>
					<option value="default-empty" hidden="true" selected disabled></option>
					<c:set var="n" value="9" />
					<c:forEach begin="9" end="17">
						<option class="${n }" value="${n }:00:00">${n<10? '0'+n:n} 시 00 분</option>
						<c:set var="n" value="${n+1}" />
					</c:forEach>
				</select>
				<label><small>마침시간</small></label>    
				<select class="endTag selectpicker form-control" name='rvEnd'>
					<option value="default-empty" hidden="true" selected disabled></option>
					<c:set var="n" value="9" />
					<c:forEach begin="9" end="17">
						<option class="${n }" value="${n }:50:00">${n<10? '0'+n:n} 시 50 분</option>
					<c:set var="n" value="${n+1}" />
					</c:forEach>
				</select> 		
	        </div>	   
	      </div>
	      <div class="modal-footer">
	        <button id="modalCloseBtn" type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <button id="modalRegisterBtn" type="submit" class="btn btn-primary">예약</button>
	      </div>
	    </div>
	  </div>
	 </div>
	<div class="modal" id="getModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Modal title</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div id="getModalBody" class="container-fluid">
		        <div class="row justify-content-center">
		        	<div class="col-md-1">
		        		<img src="/resources/imgs/S2.jpg" alt="이미지">
		        	</div>
		        	<div class="col-md-4">
		        		레몬그랩
		        	</div>
		        	<div class="col-md-5">
		        		9:00 ~ 11:50
		        	</div>
		        	<div class="col-md-1">
		        		<button class="btn btn-xs">삭제</button>
		        	</div>		        	   		        		        	
		        </div>	      	
	      	</div>
	      </div>
	      <div class="modal-footer">
	      
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>
