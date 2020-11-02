<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>电子书城</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
	<script>
		function checkPnum(id,pnum) {
			pnum = parseInt(pnum);
			if (pnum <= 0){
				alert("商品库存不足");
			}else {
				location.href = "${pageContext.request.contextPath}/client/cart/addCart?id="+id;
			}
		}
	</script>
</head>

<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=${category}">&nbsp;${category}</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${product.name}
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="${pageContext.request.contextPath }/client/ad/page_ad.jpg" width="900" height="84" />
								<table width="100%%" border="0" cellspacing="0">
									<tr>
										<td width="30%">
											<div class="divbookcover">
												<p>
													<img src="${pageContext.request.contextPath}${product.imgurl}"
														width="213" height="269" border="0" />
												</p>
											</div>
											<div style="text-align:center; margin-top:25px">
												<a href="#" onclick="checkPnum('${product.id}','${product.pnum}');">
													<img src="${pageContext.request.contextPath }/client/images/buybutton.gif" border="0" /> 
												</a>
											</div>
										</td>
										<td style="padding:20px 5px 5px 5px">
											<img src="${pageContext.request.contextPath }/client/images/miniicon3.gif" width="20" height="13" />
											<font class="bookname">&nbsp;&nbsp;${product.name}</font>
											<hr />售价：<font color="#FF0000">￥${product.price}</font>
											<hr /> 类别：${product.category }
											<hr />
											<p>
												<b>内容简介：</b>
											</p> ${product.description}
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
