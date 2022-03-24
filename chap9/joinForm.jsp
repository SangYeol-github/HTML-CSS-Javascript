<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<title>회원가입 폼</title>
	<style>
		h3 { width: fit-content; margin: 0 auto; }
		form { width: fit-content; margin: 1em auto; }
		form > div { magin-bottom : 0.5em; }
		form > div > label { font-weight: bolder; margin-right: 2em; width : 5em;
			display:inLine-block; text-align: right;
		 }
		 form > div :last-child { margin-top : 2em; text-align: center; }
		 form input[type=image] : hover{}
	</style>
	<script>
		 $(function () {
			$('#range_out').val(5);
		}); 
		function showRangeVal(num) {
			$('#range_out').val(num);
		}
	</script>
</head>
<body>
	<h3>회원 가입</h3>
	<form action = "/demo/join" method="get">
		<div><label>취미</label>
			<input type ="checkbox" name = "hobby" value = "game" checked>게임		
			<input type ="checkbox" name = "hobby" value = "fishing">낚시		
			<input type ="checkbox" name = "hobby" value = "movie">영화		
			<input type ="checkbox" name = "hobby" value = "travel">여행		
		</div>
		<div><label>성별</label>
			<input type ="radio" name = "gender" value="m" checked>남
			<input type ="radio" name = "gender" value="f">여
		</div>
		<div><label>과목선택</label>
			<select name = "subject">
				<option value="HTML">HTML</option>
				<option selected>JAVA</option>
				<option disabled>Python</option>
				<option>MySQL</option>
			</select>
		</div>
		<div><label>암호</label>
			<input type = "password" name = "pass" required>
		</div>
		<div><label>색상 선택</label>
			<input type = "color" name = "mycolor">
		</div>
		<div><label>생년월일</label>
			<input type = "date" name = "birth">
		</div>
		<div><label>이메일</label>
			<input type = "email" name = "email">
		</div>
		<div><label>이력서</label>
			<input type = "file" name = "resume">
		</div>
		<div><label>이미지</label>
			<input type = "image" name = "image" width = "100" height="100" src="../images/image.jpg">
		</div>
		<div><label>지원횟수</label>
			<input type = "number" name = "num" min="0" max = "10">
		</div>
		<div><label>게임레벨</label>
			<input type = "range" name = "level" min="0" max = "10" oninput="showRangeVal(this.value);">
				<output id="range_out" ></output>
		</div>
		
		<div>
			<input type ="submit" value="저장">
			<input type ="reset" value="취소">
		</div>
	</form>
</body>
</html>