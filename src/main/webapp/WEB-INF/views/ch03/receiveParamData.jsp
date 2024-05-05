<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Insert title here</title>
		
		<!-- Bootstrap 5 외부 라이브러리 설정 -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>	
		
		<!-- jQuery 외부 라이브러리 설정 -->
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
		
		<!-- 사용자 정의 자바스크립트 -->
		
					
			
			
	</head>

	<body>
		<div class="d-flex flex-column vh-100">
			<%@ include file="/WEB-INF/views/common/header.jsp"%>
			<div class="flex-grow-1 m-2">
				<div class="d-flex row">
					<div class="col-md-4">
						<%@ include file="/WEB-INF/views/common/menu.jsp"%>
					</div>
					
					<div class="col-md-8">
						<!-- ###################################### -->
						<div class="card">
							<div class="card-header">receiveParamData</div>
							<div class="card-body">								
								<p>param1: ${param1}</p> <!-- special language -->
								<p>param2: ${param2}</p>
								<p>param3: ${param3}</p>
								<p>param4: ${param4}</p>
								<p>param5: ${param5}</p>
								<p>param6: ${param6}</p> <!--  null 이 출력될 경우 페이지에선 아무것도 나오지않음 -->						
								</div>
							</div>
						</div>
						<!-- ###################################### -->
					</div>
				</div>
			</div>
	 </body>
</html>
