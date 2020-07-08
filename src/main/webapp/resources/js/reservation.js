console.log("reservation module....");

var rvService = (function(){
	function add(reservation, callback, error){
		console.log("add reservation...");
		
		$.ajax({
			type : 'post',
			url : '/reservation/new',
			data : JSON.stringify(reservation),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	function remove(rvId, callback, error){
		$.ajax({
			type : 'delete',
			url : '/reservation/' + rvId,
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	function update(reservation, callback, error){
		$.ajax({
			type : 'put',
			url : '/reservation/'+reservation.rvId,
			data : JSON.stringify(reservation),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function get(rvId, callback, error){
		$.get("/reservation/"+rvId+".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function getList(param, callback, error){
		$.getJSON("/reservation/myrv/"+param+".json",function(data){
			
			if(callback){
				callback(data);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
		
	}
	
	function getRvOk(param, callback, error){
		$.getJSON("/reservation/"+param.rvDate + "/" + param.roomId +".json",function(data){
			
			if(callback){
				callback(data);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
		
	}
	
	function dateConversion(dateValue){
		var dateObj = new Date(dateValue);
		var str = "";
		
		var yy = dateObj.getFullYear();
		var mm = dateObj.getMonth() + 1;
		var dd = dateObj.getDate();
		
		return [yy,'-',(mm>9?'':'0')+mm,'-',(dd>9?'':'0')+dd].join('');
	}
	
	return{
		add : add,
		remove : remove,
		update : update,
		get : get,
		getList : getList,
		getRvOk : getRvOk,
		dateConversion : dateConversion,
	};
})();