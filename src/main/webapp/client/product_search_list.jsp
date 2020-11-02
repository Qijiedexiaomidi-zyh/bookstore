<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>bookStore列表</title>
	<%--导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
</head>

<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;全部商品&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>
					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>全部商品</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${pageInfo2.total}种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="${pageContext.request.contextPath}/client/images/productlist.gif" width="100%" height="38" />
								</div>

								<table cellspacing="0" class="booklist">
									<tr>
										<c:forEach items="${products2}" var="p" varStatus="vs">
											<td>
												<div class="divbookpic">
													<p>
														<a href="${pageContext.request.contextPath}/client/product/findProductById?id=${p.id}"><img
															src="${pageContext.request.contextPath}${p.imgurl}"
															width="115" height="129" border="0" /> </a>
													</p>
												</div>
												<div class="divlisttitle">
													<a href="${pageContext.request.contextPath}/client/product/findProductById?id=${p.id}">书名： ${p.name}<br />售价：￥${p.price} </a>
												</div>
											</td>
											<%-- <c:if test="${vs.count%4==0}">
											</c:if> --%>
										</c:forEach>
									</tr>
								</table>
								<!-- <table cellspacing="0" class="booklist">
									<tr>
									</tr>
								</table> -->

								<div class="pagination">
									<ul>
										<c:if test="${pageInfo2.isFirstPage==false}">
											<li class="nextPage">
												<a href="${pageContext.request.contextPath}/client/product/MenuSearchSerlvet?name=${name}&pageNum=${pageInfo2.pageNum-1}">&lt;&lt;上一页</a>
											</li>
										</c:if>
										<c:if test="${pageInfo2.isFirstPage==true}">
											<li class="disablepage">&lt;&lt;上一页</li>
										</c:if>

										<c:forEach begin="1" end="${pageInfo2.pages}" var="page">
											<c:if test="${page==pageInfo2.pageNum}">
												<li class="currentpage">${page }</li>
											</c:if>
											<c:if test="${page!=pageInfo2.pageNum}">
												<li><a href="${pageContext.request.contextPath}/client/product/MenuSearchSerlvet?pageNum=${page}&name=${name}">${page}</a>
												</li>
											</c:if>
										</c:forEach>

										<c:if test="${pageInfo2.isLastPage==true}">
											<li class="disablepage">下一页 &gt;&gt;</li>
										</c:if>

										<c:if test="${pageInfo2.isLastPage==false}">
											<li class="nextpage">
												<a href="${pageContext.request.contextPath}/client/product/MenuSearchSerlvet?name=${name}&pageNum=${pageInfo2.pageNum+1}">下一页&gt;&gt;</a>
											</li>
										</c:if>
									</ul>
								</div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
