<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*"%>
<%@page  import="com.seatech.framework.AppConstants"%>
<%@page  import="com.seatech.framework.utils.TPCPUtils"%>
<%@page  import="com.seatech.tp.chucnang.ChucNangVO"%>
<%@page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<HTML>
  <HEAD>
    <META http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <META content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/khobac.css" media="screen"/>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css" media="screen"/>
    <META content="no-cache" http-equiv="Pragma">
    <META content="-1" http-equiv="Expires">
    <META name="GENERATOR" content="MSHTML 8.00.7600.16821">
    <SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/respond.src.js"></SCRIPT>
</HEAD>
<BODY>

<%
  if(request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION) == null){
    response.sendRedirect(request.getContextPath()+"/loginAction.do");
  }
%>

<SCRIPT type="text/javascript">
  function reLog()
	{

		if(showConfirm("SYS-0011")==1){
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/reLog.do?reLog=true","_parent",sFeatures);
		}
	}
	/**
	  *  Log ra trang màn hình chính
	  */
	function closeApp(){
		if (confirm("Bạn có thực sự muốn thoát ra kh�?i hệ thống?"))
		{
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/logout.do?reLog=false","_parent",sFeatures);
		}
	}
	/**
	  *  Thoát hẳn ra kh�?i ứng dụng
	  */
	function exitApp(){
		if (confirm("Bạn có chắc chắn muốn thoát kh�?i hệ thống?"))
		{
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/logout.do","_parent",sFeatures);
		}
	}
</SCRIPT>
<div class="container-fluid">
  <div class="container bglogo">
    <a href="loginAction.do">
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/Untitled-1.png"/>
    </a>
  </div>
</div>
<div class="navbar navbar-default">
    <div class="container bglogo">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#defaultNavbar1" aria-expanded="false"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
        <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a></div>
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="defaultNavbar1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">Link<span class="sr-only">(current)</span></a></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Quản lý TPCP<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/QuanLyTPListAction.do">List TPCP</a></li>
              <li><a href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/QuanLyTPAddAction.do">Add TPCP</a></li>             
            </ul>
          </li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Quản lý NSD<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/QuanLyNSDListAction.do">List User</a></li>
              <li><a href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/QuanLyNSDAddAction.do">Add User</a></li>             
            </ul>
          </li>
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
  </div>
<div class="container">
<div class="ngsd"> 
<span class="ngsd-title">Ng&#432;&#7901;i s&#7917; d&#7909;ng:</span> 
<span class="label label-primary"> <%=request.getSession().getAttribute("ten_nsd")%></span>
<span class="ngsd-title">Ng&#224;y l&#224;m vi&#7879;c:</span> 
<span class="label label-primary"> <%=com.seatech.framework.utils.StringUtil.getCurrentDate()%></span>
<a href="logoutAction.do" class="label label-warning">
		<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Tho&#225;t
	</a>
</div>
</div>
<div class="container padding0">