<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>

<style type="text/css">
	.dataRow:hover{
		background: #eee;
		cursor: pointer;
	}
</style>

<script type="text/javascript">
	<c:if test="${msg != null && msg != ''}">
		alert("${msg}");
	</c:if>
	$(function(){
		$(".dataRow").click(function(){
			let no = $(this).find(".no").text();
			location = "view.do?no="+no+"&inc=1"
			 	+ "&page=${pageObject.page}"
			 	+ "&perPageNum=${pageObject.perPageNum}"
			 	+ "&key=${pageObject.key}"
			 	+ "&word=${pageObject.word}";
		});
	});
	
</script>

</head>
<body>
<!-- SB Admin2의 내용 출력 구조 -->
<div class="card shodow md-4" >
	<div class="card-header py-3">
		게시판 리스트
	</div>
	<div class="card-body">
		<table class="table" >
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="vo">
					<tr class="dataRow">
						<td class="no">${vo.no }</td>
						<td>${vo.title }</td>
						<td>${vo.writer }</td>
						<td><fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatNumber value="${vo.hit }" pattern="#,###" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="write.do?perPageNum=${pageObject.perPageNum }" class="btn btn-default">글쓰기</a>
		<div>
			<pageNav:pageNav listURI="list.do" pageObject="${pateObject }"></pageNav:pageNav>
		</div>
	</div>
</div>
</body>
</html>