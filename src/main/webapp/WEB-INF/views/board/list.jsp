<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	 	<title>게시판</title>
	</head>
	<body>
		<div id="container">
			<header>
				<h1> 게시판</h1>
				<style type="text/css">
					li {list-style: none; float: left; padding: 6px;}
				</style>
			</header>
			<hr />
			 
			<div>
				<%@include file="nav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form role="form" method="get">
					<table class="table table-hover">
						<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th><th>조회수</th></tr>
						
						<c:forEach items="${list}" var = "list">
							<tr>
								<td><c:out value="${list.bno}" /></td>
								<td><a href="/board/readView?bno=${list.bno}&page=${s.page}&perPageNum=${s.perPageNum}&searchType=${s.searchType}&keyword=${s.keyword}"><c:out value="${list.title}" /></a></td>
								<td><c:out value="${list.writer}" /></td>
								<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
								<td><c:out value="${list.viewcnt}" /></td>
							</tr>
						</c:forEach>
						
					</table>
					<div class="search row">
						<div class="col-xs-2 col-sm-2">
    						<select name="searchType" class="form-control">
     							<option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
      							<option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
      							<option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
     							<option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
     							<option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
    						</select>
						</div>
    					<div class="col-xs-10 col-sm-10">
							<div class="input-group">
								<input type="text" name="keyword" id="keywordInput" value="${s.keyword}" class="form-control"/>
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-default">검색</button> 	
								</span>
							</div>
						</div>
    					<script>
      						$(function(){
       							$('#searchBtn').click(function() {
          							self.location = "list" + '${BoardListPageView.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
        						});
      						});   
    					</script>
  					</div>
					<div class="col-md-offset-3">
  						<ul class="pagination">
    						<c:if test="${BoardListPageView.prev}">
    							<li><a href="list${BoardListPageView.makeSearch(BoardListPageView.startPage - 1)}">이전</a></li>
   							</c:if> 

    						<c:forEach begin="${BoardListPageView.startPage}" end="${BoardListPageView.endPage}" var="idx">
								<li <c:out value="${BoardListPageView.p.page == idx ? 'class=info' : ''}" />>
								<a href="list${BoardListPageView.makeSearch(idx)}">${idx}</a></li>
							</c:forEach>

    						<c:if test="${BoardListPageView.next && BoardListPageView.endPage > 0}">
    							<li><a href="list${BoardListPageView.makeSearch(BoardListPageView.endPage + 1)}">다음</a></li>
   							</c:if> 
 						</ul>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>