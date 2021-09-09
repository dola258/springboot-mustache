<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container"> <!-- 70%차지 -->

	<!-- for(Board board : boardsEntity) -->
	<!-- var은 apsgeScope에 저장이 된다 -->
	<c:forEach var="board" items="${boardsEntity.content}">
	<!-- 카드 글 시작 -->
	<div class="card">
		<div class="card-body">
			<!-- EL표현식은 변수명을 적으면 자동으로 get함수를 호출해준다 -->
			<h4 class="card-title">${board.title}</h4>
			<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
		</div>
	</div>
	<br>
	<!-- 카드 글 끝 -->
	</c:forEach>
	
	${boardsEntity.last }<br>
	${boardsEntity.first }<br>
	
	<!-- 목차 시작 -->
	<ul class="pagination d-flex justify-content-center">
		<c:choose>
			<c:when test="${boardsEntity.first}">
				<!-- 페이지 객체에 있는 현재페이지(number)를 가지고 와서 1감소 -->
				<li class="page-item disabled"><a class="page-link"	href="/board?page=${boardsEntity.number-1}">Prev</a></li>
				<!-- param 주소에 있는 page를 가지고 와서 1증가 -->
				<li class="page-item"><a class="page-link" href="/board?page=${param.page+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${boardsEntity.last}">
						<!-- 페이지 객체에 있는 현재페이지(number)를 가지고 와서 1감소 -->
						<li class="page-item "><a class="page-link"	href="/board?page=${boardsEntity.number-1}">Prev</a></li>
						<!-- param 주소에 있는 page를 가지고 와서 1증가 -->
						<li class="page-item disabled"><a class="page-link" href="/board?page=${param.page+1}">Next</a></li>
					</c:when>
					<c:otherwise>
						<!-- 페이지 객체에 있는 현재페이지(number)를 가지고 와서 1감소 -->
						<li class="page-item "><a class="page-link"	href="/board?page=${boardsEntity.number-1}">Prev</a></li>
						<!-- param 주소에 있는 page를 가지고 와서 1증가 -->
						<li class="page-item "><a class="page-link"	href="/board?page=${param.page+1}">Next</a></li>
					</c:otherwise>
				</c:choose>
			</c:otherwise>	
		</c:choose>
	</ul>
	<!-- 목차 끝 -->
	
</div>



<%@ include file="../layout/footer.jsp" %>