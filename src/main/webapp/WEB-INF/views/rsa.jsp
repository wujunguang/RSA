<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RSA</title>
<script src="static/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/RSA.css" />
<script>
	$(document).ready(
			function() {
				$("#button1").click(
						function() {
							var data1 = $("#Prime_P").next().html();
							var data2 = $("#Prime_Q").next().html();
							var data3 = $("#Integer_E").next().html();
							var data4 = $("#PlainText").next().html();
							if (data1 == "√" && data2 == "√" && data3 == "√"
									&& data4 == "√") {
								var p = $("#Prime_P").val();
								var q = $("#Prime_Q").val();
								var e = $("#Integer_E").val();
								var plainText = $("#PlainText").val();
								$.ajax({
									url : "encryption.action",
									type : "post",
									data : {
										"p" : p,
										"q" : q,
										"e" : e,
										"plainText" : plainText
									},
									success : function(result) {
										$("#CipherText").val(
												result.extend.result);
									}
								});
								return true;
							} else
								return false;
						});
				$("#button2").click(
						function() {
							var data1 = $("#Prime_P").next().html();
							var data2 = $("#Prime_Q").next().html();
							var data3 = $("#Integer_E").next().html();
							var data4 = $("#PlainText").next().html();
							if (data1 == "√" && data2 == "√" && data3 == "√"
									&& data4 == "√") {
								var p = $("#Prime_P").val();
								var q = $("#Prime_Q").val();
								var e = $("#Integer_E").val();
								$.ajax({
									url : "decrypt.action",
									type : "post",
									data : {
										"p" : p,
										"q" : q,
										"e" : e
									},
									success : function(result) {
										console.log(result);
										$("#PlainText_1").val(
												result.extend.result);
									}
								});
								return true;
							} else
								return false;
						});
				$("#button3").click(function() {
					$.ajax({
						url : "generate.action",
						type : "post",
						success : function(result) {
							console.log(result);
							$("#Prime_P").val(result.extend.resultP);
							$("#Prime_P").next().html("√").css("color", "blue");
							$("#Prime_Q").val(result.extend.resultQ);
							$("#Prime_Q").next().html("√").css("color", "blue");
							$("#Integer_E").val(result.extend.resultE);
							$("#Integer_E").next().html("√").css("color", "blue");
						}
					});
				});
				$("#Prime_P").blur(
						function() {
							var p = $("#Prime_P").val();
							var reg = /^\+?[1-9][0-9]*$/;
							if (!reg.test(p)) {
								$("#Prime_P").next().html("&#10006").css(
										"color", "red");
							} else {
								$.ajax({
									url : "verification1.action",
									type : "post",
									data : {
										"p" : p
									},
									success : function(result) {
										if (result.trim() == "false") {
											$("#Prime_P").next()
													.html("&#10006").css(
															"color", "red");
										} else {
											$("#Prime_P").next().html("√").css(
													"color", "blue");
										}
									}
								});
							}
						});
				$("#Prime_Q").blur(
						function() {
							var q = $("#Prime_Q").val();
							var reg = /^\+?[1-9][0-9]*$/;
							if (!reg.test(q)) {
								$("#Prime_Q").next().html("&#10006").css(
										"color", "red");
							} else {
								$.ajax({
									url : "verification2.action",
									type : "post",
									data : {
										"q" : q
									},
									success : function(result) {
										if (result.trim() == "false") {
											$("#Prime_Q").next()
													.html("&#10006").css(
															"color", "red");
										} else {
											$("#Prime_Q").next().html("√").css(
													"color", "blue");
										}
									}
								});
							}
						});
				$("#Integer_E").blur(
						function() {
							var p = $("#Prime_P").val();
							var q = $("#Prime_Q").val();
							var e = $("#Integer_E").val();
							var reg = /^\+?[1-9][0-9]*$/;
							if (!reg.test(e)) {
								$("#Integer_E").next().html("&#10006").css(
										"color", "red");
							} else {
								$.ajax({
									url : "verification3.action",
									type : "post",
									data : {
										"p" : p,
										"q" : q,
										"e" : e
									},
									success : function(result) {
										if (result.trim() == "false") {
											$("#Integer_E").next().html(
													"&#10006").css("color",
													"red");
										} else {
											$("#Integer_E").next().html("√")
													.css("color", "blue");
										}
									}
								});
							}
						});
				$("#PlainText").blur(
						function() {
							var PlainText = $("#PlainText").val();
							if (PlainText == "" || PlainText == null) {
								$("#PlainText").next().html("&#10006").css(
										"color", "red");
							} else {
								$("#PlainText").next().html("√").css("color",
										"blue");
							}
						});
			});
</script>

</head>
<body>
	<h1>RSA</h1>
	<div class="content">
		<br>
		<div class="data_1">
			<label class="label_1">素数P:</label> <input type="text" id="Prime_P"><span
				style="color: red">*</span>
		</div>
		<div class="data_2">
			<label class="label_2">素数Q:</label> <input type="text" id="Prime_Q"><span
				style="color: red">*</span>
		</div>
		<div class="data_3">
			<label class="label_3">整数E:</label> <input type="text" id="Integer_E"><span
				style="color: red">*</span>
		</div>
		<div class="data_4">
			<label class="label_4">明&nbsp;文:</label> <input type="text"
				id="PlainText"><span style="color: red">*</span>
		</div>
		<label class="label_7">密&nbsp;文:</label>
		<div class="data_5">
			<textarea id="CipherText" class="CipherText" rows="2"
				disabled="disabled"></textarea>
		</div>
		<label class="label_8">明&nbsp;文:</label>
		<div class="data_6">
			<textarea id="PlainText_1" class="CipherText" rows="2"
				disabled="disabled"></textarea>
		</div>
		<br />
		<div class="btn">
			<button id="button1" type="button" class="button1">加密</button>
			<button id="button2" type="button" class="button2">解密</button>
			<button id="button3" type="button" class="button3">一键生成</button>
		</div>
	</div>
</body>
</html>