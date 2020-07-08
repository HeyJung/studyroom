console.log("cmt module....");

var cmtService = (function(){
	function add(cmt, callback, error){
		console.log("add cmt...");
		
		$.ajax({
			type : 'post',
			url : '/noticecmt/new',
			data : JSON.stringify(cmt),
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
	
	function remove(cmtId, callback, error){
		$.ajax({
			type : 'delete',
			url : '/noticecmt/' + cmtId,
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
	
	function update(cmt, callback, error){
		$.ajax({
			type : 'put',
			url : '/noticecmt/'+cmt.cmtId,
			data : JSON.stringify(cmt),
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
	
	function get(cmtId, callback, error){
		$.get("/noticecmt/"+cmtId+".json", function(result){
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
		var cmtNoticeId = param.cmtNoticeId;
		var page = param.page || 1;
		$.getJSON("/noticecmt/pages/"+cmtNoticeId+ "/" + page + ".json",
			function(data){
			if(callback){
				callback(data);
				}
			}).fail(function(xhr, status, err){
				if(error){
					error();
				}
			});
	
	}
	
	function displayTime(timeValue){
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		var str = "";
		
		if(gap < (1000*60*60*24)){
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();	
			
			return [(hh > 9? '': '0')+hh, ':', (mi > 9?'':'0')+mi, ':', (ss>9?'':'0')+ss].join('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() +1;
			var dd = dateObj.getDate();
			
			return [yy,'/',(mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');
		}
	}
	
	return{
		add : add,
		remove : remove,
		update : update,
		get : get,
		getList : getList,
		displayTime : displayTime
	};
})();