<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			type : "POST",
			async : false,
			url : "http://www.xxj.com:8081/ajaxB",
			dataType : "json",
			success : function(data) {
				alert(data["errorCode"]);
			},
			error : function() {
				alert('fail');
			}
		});

	});
</script>
</head>
<body>显示 ....
</body>
</html>