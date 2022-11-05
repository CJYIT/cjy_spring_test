<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		*{
			margin:0px;
			padding:0px;
		}
		/* 触发弹窗图片的样式 */
		.myImg {
			/*margin: 10px;
			border-radius: 20px;
			cursor: pointer;
			float: left;
			max-height: 130px;*/
			width: 30px;
		}

		/* 弹窗的位置和背景颜色 */
		.modal {
			display: none;
			position: fixed; /* 绝对定位 */
			z-index: 1; /* 元素置前 */
			padding-top: 50px;
			width: 100%;
			height: 100%;
			overflow: auto; /* 溢出出现滚动条 */
		}

		/* 弹窗图片的大小 */
		.content {
			margin: auto;
			display: block;
			min-height:500px;
			max-height:600px;
		}
	</style>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>数据 - AdminLTE2定制版</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">

<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/morris/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/select2/select2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">

	<script>
		$(function () {
			//使用封装的js方法获取rname的参数值
			var currentPage = getParameter("currentPage");//中文直接获取到的是乱码
			//如果获取到raname，将其解码转成汉字
			if (currentPage ==null){
				currentPage=1;
			}
			// alert(cid);
			// alert(rname);

			//当页码加载完成后，调用load方法，发送ajax请求加载数据
			load(currentPage,userList);
		});

		//入口函数中调用这个方法,而js的load()方法完整的是load("url",data，function(){})
		function load(currentPage,userList){
			//发送ajax请求，请求route/pageQuery,传递cid
			$.get("user/pageQuery",{currentPage:currentPage,userList},function (pb) {
				//解析pagebean数据，展示到页面上

				//1.分页工具条数据展示
				//1.1 展示总页码和总记录数
				$("#totalPage").html(pb.totalPage);
				$("#totalCount").html(pb.totalCount);

				/*
                        <li><a href="">首页</a></li>
                        <li class="threeword"><a href="#">上一页</a></li>
                        <li class="curPage"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">6</a></li>
                        <li><a href="#">7</a></li>
                        <li><a href="#">8</a></li>
                        <li><a href="#">9</a></li>
                        <li><a href="#">10</a></li>
                        <li class="threeword"><a href="javascript:;">下一页</a></li>
                        <li class="threeword"><a href="javascript:;">末页</a></li>


                 */
				var lis = "";
				// 后台默认传来首页为1   当前页是后台计算的值传过来的
				// var fristPage = '<li onclick="javascipt:load('+cid+')"><a href="javascript:void(0)">首页</a></li>';
				// 后台默认查询首页,1也可以不传
				var fristPage = '<li onclick="javascipt:load('+1+','+userList+'\')"><a href="javascript:void(0)">首页</a></li>';

				//计算上一页的页码
				var beforeNum =  pb.currentPage - 1;
				if(beforeNum <= 0){
					beforeNum = 1;
				}

				// var beforePage = '<li  onclick="javascipt:load('+cid+','+beforeNum+')" class="threeword"><a href="javascript:void(0)">上一页</a></li>';
				var beforePage = '<li  onclick="javascipt:load('+1+','+beforeNum+',\''+userList+'\')" class="threeword"><a href="javascript:void(0)">上一页</a></li>';

				lis += fristPage;
				lis += beforePage;
				//1.2 展示分页页码
				/*
                    1.一共展示10个页码，能够达到前5后4的效果
                    2.如果前边不够5个，后边补齐10个
                    3.如果后边不足4个，前边补齐10个
                */

				// 定义开始位置begin,结束位置 end
				var begin; // 开始位置
				var end ; //  结束位置


				//1.要显示10个页码
				if(pb.totalPage < 4){
					//总页码不够10页

					begin = 1;
					end = pb.totalPage;
				}else{
					//总页码超过10页

					begin = pb.currentPage - 2 ;
					end = pb.currentPage + 1 ;

					//2.如果前边不够5个，后边补齐10个
					if(begin < 1){
						begin = 1;
						end = begin + 3;
					}

					//3.如果后边不足4个，前边补齐10个
					if(end > pb.totalPage){
						end = pb.totalPage;
						begin = end - 3 ;
					}
				}


				// 判断鼠标点击的码
				for (var i = begin; i <= end ; i++) {
					var li;
					//判断当前页码是否等于i   如过==就给li加上样式class="curPage"
					if(pb.currentPage == i){

						li = '<li class="curPage" onclick="javascipt:load('+i+',\''+userList+'\')"><a href="javascript:void(0)">'+i+'</a></li>';

					}else{
						//创建页码的li
						li = '<li onclick="javascipt:load('+i+',\''+userList+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
					}
					//拼接字符串
					lis += li;
				}
				/* for (var i = 1; i <= pb.totalPage ; i++) {
                     var li;
                     //判断当前页码是否等于i
                     if(pb.currentPage == i){

                         li = '<li class="curPage" onclick="javascipt:load('+cid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>';

                     }else{
                         //创建页码的li
                         li = '<li onclick="javascipt:load('+cid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
                     }
                     //拼接字符串
                     lis += li;
                 }*/
				//计算下一页的页码
				var nextNum =  pb.currentPage + 1;
				if(nextNum >= pb.totalPage){
					nextNum = pb.totalPage;
				}

				var nextPage = '<li  onclick="javascipt:load('+nextNum+',\''+userList+'\')" class="threeword"><a href="javascript:void(0)">下一页</a></li>';
				var lastPage = '<li onclick="javascipt:load('+pb.totalPage+',\''+userList+'\')" class="threeword"><a href="javascript:;">末页</a></li>';

				lis += nextPage;
				lis += lastPage;
				// var lastPage = '<li class="threeword"><a href="javascript:;">末页</a></li>';
				// var nextPage = '<li class="threeword"><a href="javascript:;">下一页</a></li>';

				//将lis内容设置到 ul
				$("#pageNum").html(lis);

			/*	//2.列表数据展示
				var route_lis = "";

				for (var i = 0; i < pb.list.length; i++) {
					//获取{rid:1,rname:"xxx"}
					var route = pb.list[i];

					var li = '<li>\n' +
							'                        <div class="img"><img src="'+route.rimage+'" style="width: 299px;"></div>\n' +
							'                        <div class="text1">\n' +
							'                            <p>'+route.rname+'</p>\n' +
							'                            <br/>\n' +
							'                            <p>'+route.routeIntroduce+'</p>\n' +
							'                        </div>\n' +
							'                        <div class="price">\n' +
							'                            <p class="price_num">\n' +
							'                                <span>&yen;</span>\n' +
							'                                <span>'+route.price+'</span>\n' +
							'                                <span>起</span>\n' +
							'                            </p>\n' +
							'                            <p><a href="route_detail.html?rid='+route.rid+'">查看详情</a></p>\n' +
							'                        </div>\n' +
							'                    </li>';
					route_lis += li;
				}
				$("#route").html(route_lis);*/

				//定位到页面顶部
				// window.scrollTo(0,0);
			});

		}




	/*function editUser(userId) {
    if(confirm("您确认要修改吗")){
        location.href="${pageContext.request.contextPath}/user/edit/"+userId;
		}
	}
*/
	function delUser(userId,img){
		    if(confirm("您确认要删除吗")){
		        location.href="${pageContext.request.contextPath}/user/del?"+"userId="+userId+"&img="+img;
			}
		}
	</script>
</head>

<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">

		<!-- 页面头部 -->
		<jsp:include page="header.jsp"></jsp:include>
		<!-- 页面头部 /-->

		<!-- 导航侧栏 -->
		<jsp:include page="aside.jsp"></jsp:include>
		<!-- 导航侧栏 /-->

		<!-- 内容区域 -->
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
			<h1>
				用户管理 <small>全部用户</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/index.jsp"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/user/findAll.do">用户管理</a></li>

				<li class="active">全部用户</li>
			</ol>
			</section>
			<!-- 内容头部 /-->

				<!-- 正文区域 -->
				<section class="content"> <!-- .box-body -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">列表</h3>
					</div>

					<div class="box-body">

						<!-- 数据表格 -->
						<div class="table-box">

							<!--工具栏-->
							<div class="pull-left">
								<div class="form-group form-inline">
									<div class="btn-group">
										<button type="button" class="btn btn-default" title="新建" onclick="location.href='${pageContext.request.contextPath}/user/saveUI'">
											<i class="fa fa-file-o"></i> 新建
										</button>
										
										<button type="button" class="btn btn-default" title="刷新">
											<i class="fa fa-refresh"></i> 刷新
										</button>
									</div>
								</div>
							</div>
							<div class="box-tools pull-right">
								<div class="has-feedback">
									<input type="text" class="form-control input-sm"
										placeholder="搜索"> <span
										class="glyphicon glyphicon-search form-control-feedback"></span>
								</div>
							</div>
							<!--工具栏/-->

							<!--数据列表-->
							<table id="dataList"
								class="table table-bordered table-striped table-hover dataTable">
								<thead>
									<tr>
										<th class="" style="padding-right: 0px"><input
											id="selall" type="checkbox" class="icheckbox_square-blue">
										</th>
										<th class="sorting_asc">ID</th>
										<th class="sorting_desc" >照片</th>
										<th class="sorting_desc">用户名</th>
										<th class="sorting_asc sorting_asc_disabled">邮箱</th>
										<th class="sorting_desc sorting_desc_disabled">联系电话</th>
										<th class="sorting">具有角色</th>
										<th class="sorting">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${userList}" var="user">
										<tr title="点击照片可以放大查看" >
											<td><input name="ids" type="checkbox"></td>
											<td>${user.id}</td>
											<td class="img-sm" style="cursor: pointer">
												<img id="${user.id}" src='${pageContext.request.contextPath}/imges/${user.img}'
												style="width: 30px;" onclick="demo('${user.id}')" title="点击图片预览" >
											</td>

											<td>${user.username}</td>
											<td>${user.email}</td>
											<td>${user.phoneNum}</td>
											<td class="text-center">
												<c:forEach items="${user.roles}" var="role">
													&nbsp;&nbsp;${role.roleName}
												</c:forEach>
											</td>
											<td class="text-center">
										        <a href="javascript:void(0);" onclick="location.href='${pageContext.request.contextPath}/user/editUI'" class="btn bg-olive btn-xs">修改</a>
												<a href="javascript:void(0);" onclick="delUser('${user.id}','${user.img}')" class="btn bg-olive btn-xs">删除</a>
											</td>
										</tr>

										<!-- 弹窗 -->
										<div id="myModal" class="modal" onclick="document.getElementById('myModal').style.display='none'">
											<!-- 弹窗内容 -->
											<img class="content" id="show">
										</div>
										<!--相应图片点击操作，imgId是参数，上面调用传过来-->
										<script>
											function demo(imgId) {
												document.getElementById('myModal').style.display = "block";
												// console.log(imgId)
												document.getElementById("show").src = document.getElementById(imgId).src;
												// console.log( document.getElementById(imgId).src)
											}
										</script>

									</c:forEach>
								</tbody>
							</table>
							<!--数据列表/-->

						</div>
						<!-- 数据表格 /-->

						<!-- .box-footer-->
						<div class="box-footer">
							<div class="pull-left">
								<div class="form-group form-inline">
									总共2 页，共14 条数据。 每页 <select class="form-control">
									<option>2</option>
									<option>10</option>
									<option>15</option>
									<option>20</option>
									<option>50</option>
									<option>80</option>
								</select> 条
								</div>
							</div>

							<div class="box-tools pull-right">
								<ul class="pagination">
									<li><a href="#" aria-label="Previous">首页</a></li>
									<li><a href="#">上一页</a></li>
									<li><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">下一页</a></li>
									<li><a href="#" aria-label="Next">尾页</a></li>
								</ul>
							</div>

						</div>
						<!-- /.box-footer-->


					</div>
					<!-- /.box-body -->



				</div>

				</section>
				<!-- 正文区域 /-->

			</div>
			<!-- @@close -->
			<!-- 内容区域 /-->

			<!-- 底部导航 -->
			<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.8
			</div>
			<strong>Copyright &copy; 2018-2020 <a
				href="http://www.itcast.cn">研究院研发部</a>.
			</strong> All rights reserved. </footer>
			<!-- 底部导航 /-->

		</div>

		<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
		<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
		<script>
			$.widget.bridge('uibutton', $.ui.button);
		</script>
		<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
		<script src="../plugins/raphael/raphael-min.js"></script>
		<script src="../plugins/morris/morris.min.js"></script>
		<script src="../plugins/sparkline/jquery.sparkline.min.js"></script>
		<script src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
		<script src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
		<script src="../plugins/knob/jquery.knob.js"></script>
		<script src="../plugins/daterangepicker/moment.min.js"></script>
		<script src="../plugins/daterangepicker/daterangepicker.js"></script>
		<script src="../plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
		<script src="../plugins/datepicker/bootstrap-datepicker.js"></script>
		<script
			src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
		<script
			src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
		<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
		<script src="../plugins/fastclick/fastclick.js"></script>
		<script src="../plugins/iCheck/icheck.min.js"></script>
		<script src="../plugins/adminLTE/js/app.js"></script>
		<script src="../plugins/treeTable/jquery.treetable.js"></script>
		<script src="../plugins/select2/select2.full.min.js"></script>
		<script src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
		<script
			src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
		<script src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
		<script
			src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
		<script src="../plugins/bootstrap-markdown/js/markdown.js"></script>
		<script src="../plugins/bootstrap-markdown/js/to-markdown.js"></script>
		<script src="../plugins/ckeditor/ckeditor.js"></script>
		<script src="../plugins/input-mask/jquery.inputmask.js"></script>
		<script
			src="../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
		<script src="../plugins/input-mask/jquery.inputmask.extensions.js"></script>
		<script src="../plugins/datatables/jquery.dataTables.min.js"></script>
		<script src="../plugins/datatables/dataTables.bootstrap.min.js"></script>
		<script src="../plugins/chartjs/Chart.min.js"></script>
		<script src="../plugins/flot/jquery.flot.min.js"></script>
		<script src="../plugins/flot/jquery.flot.resize.min.js"></script>
		<script src="../plugins/flot/jquery.flot.pie.min.js"></script>
		<script src="../plugins/flot/jquery.flot.categories.min.js"></script>
		<script src="../plugins/ionslider/ion.rangeSlider.min.js"></script>
		<script src="../plugins/bootstrap-slider/bootstrap-slider.js"></script>
		<script>
			$(document).ready(function() {
				// 选择框
				$(".select2").select2();

				// WYSIHTML5编辑器
				$(".textarea").wysihtml5({
					locale : 'zh-CN'
				});
			});

			// 设置激活菜单
			function setSidebarActive(tagUri) {
				var liObj = $("#" + tagUri);
				if (liObj.length > 0) {
					liObj.parent().parent().addClass("active");
					liObj.addClass("active");
				}
			}

			$(document)
					.ready(
							function() {

								// 激活导航位置
								setSidebarActive("admin-datalist");

								// 列表按钮 
								$("#dataList td input[type='checkbox']")
										.iCheck(
												{
													checkboxClass : 'icheckbox_square-blue',
													increaseArea : '20%'
												});
								// 全选操作 
								$("#selall")
										.click(
												function() {
													var clicks = $(this).is(
															':checked');
													if (!clicks) {
														$(
																"#dataList td input[type='checkbox']")
																.iCheck(
																		"uncheck");
													} else {
														$(
																"#dataList td input[type='checkbox']")
																.iCheck("check");
													}
													$(this).data("clicks",
															!clicks);
												});
							});
		</script>
</body>

</html>