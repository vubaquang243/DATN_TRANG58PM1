<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0034)http://tcs.kbnn.vn/Load_ChungTu.do -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page  import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds//fmt.tld" prefix="fmt" %>

<HTML>
<HEAD>
  <TITLE>TTSP-Dang nhap he thong</TITLE>
  <META content="text/html; charset=UTF-8" http-equiv="Content-Type"></META>
  <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/jquery-ui-1.11.3/jquery-ui.css" media="screen"/>
  <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/khobac.css" media="screen"/>
  <SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/respond.src.js"></SCRIPT>
   <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/jquery-1.11.3.min.js"></script>
      <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/bootstrap.js"></script>
    <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/bootstrap-select.js"></script>
   
    <!--[if (gte IE 6)&(lte IE 8)]>
  <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/selectivizr.js"></script>
  <noscript><link rel="stylesheet" href="[fallback css]" /></noscript>
  <![endif]-->
  <META content=no-cache http-equiv=Pragma>
  <META content=-1 http-equiv=Expires>
  <META name=GENERATOR content="MSHTML 8.00.7600.16821">
  
  
   <script type="text/javascript">
    function sbLogin() {
      var frm = document.forms[0];
      //set cookie
      setValueToCookie('domain', frm.domain.value);
      setValueToCookie('user_name_ttsp', frm.ma_nsd.value);

      frm.action = 'loginAction.do';
      frm.submit();
  }
   function changekey(event){
     event.keyCode=9;
     return event.keyCode;
   }
   function focu(){
     $("#password").focus();
   }
$("input").on("keydown", function(event) {
    if (event.which === 13 && !$(this).is("textarea, :button, :submit")) {
        event.stopPropagation();
        event.preventDefault();
        
        $(this)
            .nextAll("input:not(:disabled, [readonly='readonly'])")
            .first()
            .focus();
    }
});
   </script>
</HEAD>
<BODY class="body">
    <%
//      if(request.getParameter("action") == null){
//        response.sendRedirect("main.jsp");
//      }
      if(request.getSession().getAttribute("ma_nsd") != null){
        response.sendRedirect(request.getContextPath()+"/loginAction.do");
      }
      String ipAddress  = request.getHeader("X-FORWARDED-FOR");  
      if(ipAddress == null)  
      {  
        ipAddress = request.getRemoteAddr();  
      }
    %>
  <!--<div class="banner">&nbsp; </div>-->

 
   
  <div id="loginbox" class="mainbox">
   
    <div class="loginbox">
      <div class="login-heading">
        <div class="login-title">Đăng nhập<span class="hethong">hệ thống</span></div>
      </div>
      <div class="login-body">
      
        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
        <!-- <form id="loginform" class="form-horizontal col-sm-6" role="form"> -->
          <html:form action="loginAction">
          
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-compressed"></i></span>
            <html:select title="Domain" property="domain" styleId="domain_id" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleClass="form-control">
              <html:option value="TW">TW</html:option>
              <html:option value="MB">MB</html:option>
              <html:option value="MN">MN</html:option>
            </html:select>
          </div>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <label for="username_id">Mã nhân viên</label>
            <input type="text" name="ma_nsd"  class="form-control" onkeydown="if(event.keyCode==13){ focu(); };" 
            onkeypress="return blockKeySpecial1(event);"  id="username_id" value="" title="Mã nhân viên"/>
          </div>
         
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <label for="password">Mật khẩu</label>
            <input type="password" onkeydown="if(event.keyCode==13){ sbLogin(); };" 
                   class="form-control" onkeypress="capLock(event)" 
                   maxLength="20"  name="mat_khau" id="password" value="" title="Mật khẩu"/>
          </div>
          
         
              <!--<div class="btn-dangnhap" accessKey="d" onclick="sbLogin();"><span class=sortKey>&#272;</span>&#259;ng nh&#7853;p
              </div >-->
              
              <div class="btn-dangnhap"  onclick="sbLogin();">
                <span class=sortKey>&#272;</span>&#259;ng nh&#7853;p
              </div >
          <html:hidden property="ip_truycap" value="<%=ipAddress%>"/>
          <html:hidden property="mac_address" styleId="mac_address_id" value=""/>
          <html:hidden property="user_may_truycap" styleId="user_may_truycap_id" value=""/>
          <html:hidden property="ten_may_truycap" styleId="ten_may_truycap_id" value=""/>
        </html:form>
      </div>
    </div>
     <div class="app_error" style="margin-top:40px;">
   <html:errors/>
   </div>
  </div>
  <script type="text/javascript">
  getValueFromCookie();
 // getClientInfo();

  //alert(domainCookie);
 

  function blockKeySpecial1(e) {
      var key;
      if (window.event)// IE
      {
          key = e.keyCode;
      }
      else if (e.which)// Netscape/Firefox/Opera
      {
          key = e.which;
      }
      if (key == 37 || key == 38 || key == 39 || key == 40 || key == 16 || key==13//arrow key
 || key == 35 || key == 36 || key == 9//home,end,tab
 || key == 46 || key == 8//del,insert,backspace
 || (key > 31 && key < 44) || (key > 57 && key < 65) || (key > 90 && key < 97) || (key > 123 && key < 127))//Cac ki tu dac biet
          event.returnValue = false;
      else {
          return textToUpper();
      }
  }
function textToUpper()
{
	var keycharUppercase;
	var keychar;
	if(window.event){ // IE
		var keynum = event.keyCode;
		keychar = String.fromCharCode(keynum);
		keycharUppercase = keychar.toUpperCase();
		event.keyCode = keycharUppercase.charCodeAt(0);
	}	
	return true;
}
  // Use this function to retrieve a cookie.
  function getCookie(name) {
      var cname = name + "=";
      var dc = document.cookie;
      if (dc.length > 0) {
          begin = dc.indexOf(cname);
          if (begin !=  - 1) {
              begin += cname.length;
              end = dc.indexOf(";", begin);
              if (end ==  - 1)
                  end = dc.length;
              return unescape(dc.substring(begin, end));
          }
      }
      return null;
  }

  // Use this function to save a cookie.
  function setCookie(name, value, expires) {
      document.cookie = name + "=" + escape(value) + "; path=/" + ((expires == null) ? "" : "; " + "expires=" + expires.toGMTString());
  }

  // Use this function to delete a cookie.
  function delCookie(name) {
      document.cookie = name + "=; expires=Thu, 01-Jan-70 00:00:01 GMT" + "; path=/";
  }

  // Function to retrieve form element's value.
  function getValueFromCookie() {
      var value = getCookie('domain');
      var user_value = getCookie('user_name_ttsp');

      if (user_value != null && user_value != 'null' && user_value != '' && value != null && value != 'null' && value != ''){
          document.getElementById('domain_id').value = value;
          document.getElementById('username_id').value = user_value;
          document.getElementById('password').focus();
      }else{
        if (user_value != null && user_value != 'null' && user_value != ''){
            document.getElementById('username_id').value = user_value;
            document.getElementById('domain_id').focus();
        }
        if (value != null && value != 'null' && value != ''){
            document.getElementById('domain_id').value = value;
            document.getElementById('username_id').focus();
        }
      }
  }

  // Function to save form element's value.
  function setValueToCookie(name, value) {
      setCookie(name, value, null);
  }
  //get MAC, OS USER, COMPUTER NAME
  function getClientInfo() {
      var wshell = new ActiveXObject("WScript.Shell");
      var user_may_truycap_value = wshell.ExpandEnvironmentStrings("%USERNAME%");
      document.getElementById('user_may_truycap_id').value = user_may_truycap_value;

      var network = new ActiveXObject('WScript.Network');
      var ten_may_truycap_value = network.computerName;
      document.getElementById('ten_may_truycap_id').value = ten_may_truycap_value;

      var locator = new ActiveXObject ("WbemScripting.SWbemLocator");
      var service = locator.ConnectServer(".");
      var properties = service.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration WHERE IPEnabled = True");
      var e = new Enumerator (properties);
      var mac_address_value = '|';
      for (;!e.atEnd();e.moveNext ())
      {
            var p = e.item ();
            mac_address_value = mac_address_value + '|' + p.MACAddress;
      }
      mac_address_value = mac_address_value + '|'
      document.getElementById('mac_address_id').value = mac_address_value;

  }

    
</script>
 <script>
$("input").each(function(){
   if($.trim($(this).val()) != '') {
      $(this).prev('label').css('opacity', '0');
  }
});
$('input')
    .focus(function() {
        $(this).prev('label').css('opacity', '0');
    })
    .blur(function() {
        if($.trim($(this).val()) == '') {
            $(this).prev('label').css('opacity', '1');
        }
    });
  $(document).ready(function() {
  $('.app_error').filter(function () {
    return $.trim($(this).html()).length == 0;
}).hide();
$('.app_error').filter(function () {
    return $.trim($(this).html()).length != 0;
}).show();
});
 </script>
 </body>
 </html>
