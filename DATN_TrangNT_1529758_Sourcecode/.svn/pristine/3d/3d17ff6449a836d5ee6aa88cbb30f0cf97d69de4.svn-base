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
    <TITLE>TPCP-HỆ THỐNG QUẢN LÝ TRÁI PHIẾU CHÍNH PHỦ</TITLE>  
    <META http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <META content="text/html; charset=UTF-8" http-equiv="Content-Type"></META>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/jquery-ui-1.11.3/jquery-ui.css" media="screen"/>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/khobac.css" media="screen"/>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css" media="screen"/>    
    <SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/respond.src.js"></SCRIPT>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/oracle.css" media="screen"/>    
    <link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/blue.css"/>
    
    <META content="no-cache" http-equiv="Pragma"></META>
    <META content="-1" http-equiv="Expires"></META>
    <META name="GENERATOR" content="MSHTML 8.00.7600.16821"></META>    
     <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/jquery-1.11.3.min.js"></script>    
    <!-- Include all compiled plugins (below), or include individual files as needed --> 
    <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/bootstrap.js"></script>
    <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/bootstrap-select.js"></script>
    <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/jquery-ui-1.11.3/jquery-ui.js"></script>
    <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tableutils.js"></script>
    <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/ckeditor/ckeditor.js"></script>
    <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/ckeditor/samples/js/sample.js"></script>
   <!--[if (gte IE 6)&(lte IE 8)]>
  <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/selectivizr.js"></script>
  <noscript><link rel="stylesheet" href="[fallback css]" /></noscript>
  <![endif]-->
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
		if (confirm("Bạn có chắc chắn muốn thoát khỏi hệ thống?"))
		{
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/logout.do","_parent",sFeatures);	
		}
	}
  function textfocus(obj){
      obj.style.backgroundColor='#ffffb5';
  }
  function textlostfocus(obj){
      obj.style.backgroundColor='#ffffff';
  }    
  function onkeydown(e){
      if (!e) e = window.event;
      if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190, 188]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
  }  
  function onkeydownnodot(e){
      if (!e) e = window.event;
      if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 188]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
  }  
  function toNumber(strNumber){
    var numStr = strNumber;
    if (null == strNumber || '' == strNumber) {
        return 0;
    }
    
    while(numStr.indexOf('.') != -1) {
        numStr = numStr.replace('.', '');
    }
    while(numStr.indexOf(',') != -1) {
        numStr = numStr.replace(',', '.');
    }
    if(numStr.indexOf('(') != -1) {
        numStr = numStr.replace('(', '');
        numStr = numStr.replace(')', '');
        numStr = '-' + numStr;
    }
    return parseFloat(numStr);
}
  function testDecimals(currentVal) {
    var count;
    currentVal.match(/\,/g) === null ? count = 0 : count = currentVal.match(/\,/g);
    return count;
  }
  function testCommas(currentVal) {
    var count;
    currentVal.match(/\./g) === null ? count = 0 : count = currentVal.match(/\./g).length;
    return count;
  }
  
  function replaceCommas(yourNumber) {
    var components = yourNumber.toString().split(",");
    if (components.length === 1)
        components[0] = yourNumber;
    components[0] = components[0].toString().replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    if (components.length === 2)
        components[1] = components[1].toString().replace(/\D/g, "");
    return components.join(",");
  }
  // Get the start position of the caret in the object
function getCaretStart(obj){
	if(typeof obj.selectionStart != "undefined"){
		return obj.selectionStart;
	}else if(document.selection&&document.selection.createRange){
		var M=document.selection.createRange();
		try{
			var Lp = M.duplicate();
			Lp.moveToElementText(obj);
		}catch(e){
			var Lp=obj.createTextRange();
		}
		Lp.setEndPoint("EndToStart",M);
		var rb=Lp.text.length;
		if(rb>obj.value.length){
			return -1;
		}
		return rb;
	}
}
function isNumberKey(evt)
    {
       var charCode = (evt.which) ? evt.which : event.keyCode;
       if(charCode == 59 || charCode == 46)
        return true;
       if (charCode > 31 && (charCode < 48 || charCode > 57))
          return false;
       return true;
    }
// sets the caret position to l in the object
function setCaret(obj,l){
	obj.focus();
	if (obj.setSelectionRange){
		obj.setSelectionRange(l,l);
	}else if(obj.createTextRange){
		m = obj.createTextRange();		
		m.moveStart('character',l);
		m.collapse();
		m.select();
	}
}
  function formatNum(elt){
        var position = getCaretStart(elt);
        var currentVal = elt.value;
        var s1 = testCommas(currentVal);
        var testDecimal = testDecimals(currentVal);
        if (testDecimal.length > 1) {
            currentVal = currentVal.slice(0, -1);
        }
        if(currentVal=="") currentVal = "";
        elt.value = replaceCommas(currentVal); 
        
        var s2 = testCommas(elt.value);
        setCaret(elt, position + (s2 - s1));
  }
    
  function blockKeySpecial001(e) {
      //      e.keyCode
      var code;
      if (!e)
          var e = window.event;
      if (e.keyCode)
          code = e.keyCode;
      else if (e.which)
          code = e.which;
      var character = String.fromCharCode(code);
      var pattern = /[!@#$%^&*()_+='"\[\]\:\;\{\}\<\>\?]/;
      if (pattern.test(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }
  function blockKeyDT(e) {
      //      e.keyCode
      var code;
      if (!e)
          var e = window.event;
      if (e.keyCode)
          code = e.keyCode;
      else if (e.which)
          code = e.which;
      var character = String.fromCharCode(code);
      var pattern = /[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?a-zA-Za-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]/;
      if (pattern.test(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }
  
  function blockKeyNumber(e) {
      //      e.keyCode
      var code;
      if (!e)
          var e = window.event;
      if (e.keyCode)
          code = e.keyCode;
      else if (e.which)
          code = e.which;
      var character = String.fromCharCode(code);
      var pattern = /[!@#$%^&*()_+='"\[\]\.\:\;\{\}\<\>\?\ \/\|\;\'a-zA-Za-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]/;
      if (pattern.test(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }
  function format(elt){
    var currentVal = elt.value;
    if(currentVal.length ==3){
      elt.value = elt.value +"/";
    }
  }
    function validateFloatKeyPress(el, evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        
        if (charCode != 44 && charCode > 31 && (charCode < 48 || charCode > 57)&& charCode !=47) {
            return false;
        }   
        if(charCode ==47){
          var str = el.value;
          if(str.length==1){
            str="0"+"0"+str;
            el.value=str;
          }else if(str.length==2){
            str="0"+str;
            el.value=str;
          }else if(str.length >= 4){
            
            return false;
          }
        }
        return true;
  }
  function upperCase(obj){
     obj.value = obj.value.toUpperCase()
  }
  var format = "mm/dd/yyyy";
  var match = new RegExp(format
    .replace(/(\w+)\W(\w+)\W(\w+)/, "^\\s*($1)\\W*($2)?\\W*($3)?([0-9]*).*")
    .replace(/m|d|y/g, "\\d"));
  var replace = "$1/$2/$3$4".replace(/\//g, format.match(/\W/));
  function doFormat(e) {
      if (!e) e = window.event;
      if(!e.ctrlKey && !e.metaKey && (e.keyCode == 32 || e.keyCode > 46))
      var target = e.target || e.srcElement;
      if(target!=null){
        target.value = target.value.replace(/(^|\W)(?=\d\W)/g, "$10").replace(match, replace).replace(/(\W)+/g, "$1");      
      }
  }
    function validatedate(inputText)  
      {  
      var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;  
      // Match the date format through regular expression  
      if(inputText.value.match(dateformat))  
      {  
      inputText.focus();  
      //Test which seperator is used '/' or '-'  
      var opera1 = inputText.value.split('/');  
      var opera2 = inputText.value.split('-');  
      lopera1 = opera1.length;  
      lopera2 = opera2.length;  
      // Extract the string into month, date and year  
      if (lopera1>1)  
      {  
      var pdate = inputText.value.split('/');  
      }  
      else if (lopera2>1)  
      {  
      var pdate = inputText.value.split('-');  
      }  
      var dd = parseInt(pdate[0]);  
      var mm  = parseInt(pdate[1]);  
      var yy = parseInt(pdate[2]);  
      // Create list of days of a month [assume there is no leap year by default]  
      var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];  
      if (mm==1 || mm>2)  
      {  
      if (dd>ListofDays[mm-1])  
      {  
      alert('Sai định dạng ngày tháng !');  
      return false;  
      }  
      }  
      if (mm==2)  
      {  
      var lyear = false;  
      if ( (!(yy % 4) && yy % 100) || !(yy % 400))   
      {  
      lyear = true;  
      }  
      if ((lyear==false) && (dd>=29))  
      {  
      alert('Sai định dạng ngày tháng !');  
      return false;  
      }  
      if ((lyear==true) && (dd>29))  
      {  
      alert('Sai định dạng ngày tháng !');  
      return false;  
      }  
      }  
      }  
      else  
      {  
      alert("Sai định dạng ngày tháng !");  
      inputText.focus();  
      return false;  
      }  
      return true;
      }  
      function validatetime(inputText) {
        var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
        var dateformat2 = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4} (00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9])$/;
        
        // Match the date format through regular expression  
        if (inputText.match(dateformat) || inputText.match(dateformat2)) {
            //Test which seperator is used '/' or '-'  
            var opera1 = inputText.split('/');
            var opera2 = inputText.split('-');
            lopera1 = opera1.length;
            lopera2 = opera2.length;
            // Extract the string into month, date and year  
            if (lopera1 > 1) {
                var pdate = inputText.split('/');
            }
            else if (lopera2 > 1) {
                var pdate = inputText.split('-');
            }
            var dd = parseInt(pdate[0]);
            var mm = parseInt(pdate[1]);
            var yy = parseInt(pdate[2].substring(0,4));
            // Create list of days of a month [assume there is no leap year by default]  
            var ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            if (mm == 1 || mm > 2) {
                if (dd > ListofDays[mm - 1]) {
                    return false;
                }
            }
            if (mm == 2) {
                var lyear = false;
                if ((!(yy % 4) && yy % 100) || !(yy % 400)) {
                    lyear = true;
                }
                if ((lyear == false) && (dd >= 29)) {
                    return false;
                }
                if ((lyear == true) && (dd > 29)) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
</SCRIPT>

<TABLE border=0 cellSpacing=0 cellPadding=0 width=985 align=center>
  <TBODY>    
  <TR>
    <TD height=70><a href="loginAction.do">
      <IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/Untitled-1.png" width=985 
            height=70></a>
    </TD> 
  </TR>

  <TR>
    <TD>    
    <ul id="css3menu1" class="topmenu" style="width:100%">
<%
  Collection colMenu = (Collection)request.getSession().getAttribute(AppConstants.APP_MENU_LIST_SESSION);
  ChucNangVO cnvo = null;
  Iterator iter = colMenu.iterator();
  int nCap1Counter = 0;
  int nCap2Counter = 0;
  int nCap3Counter = 0;
  int nMaCNang = 0;
  boolean nFlagStart = true;
  boolean nFlagStart2 = true;  
  String strActionName = "#";
  String strModuleName = "";
  
  while (iter.hasNext()) {
    strActionName = "#";
    cnvo = (ChucNangVO)iter.next();
    nMaCNang = Integer.parseInt(cnvo.getMa_cnang());
    if(cnvo.getTen_action() != null && !"".equals(cnvo.getTen_action()) && !"#".equals(cnvo.getTen_action())){
      strActionName = cnvo.getTen_action().trim();
      if(strActionName.lastIndexOf(".do") == -1){
        strActionName = strActionName + ".do";
      }
    }
    //Xu ly menu cap 1
    if (nMaCNang % 10000 == 0) {           
      if (nCap1Counter == 0) {
        %>
        <li class="topfirst"><a href="<%=strActionName%>"><span><%=cnvo.getTen_cnang()%></span></a>
        <%
      }else{      
          if(nCap3Counter > 0){
          %> 
          </ul></li>
          <%}else{
%>
          </li>
<%
          }
          if(nCap2Counter > 0){
          %>
          </ul></li>          
          <%
          }  
          if(nCap2Counter == 0){%>
          </li>
          <%}%>
        <li class="topmenu"><a href="<%=strActionName%>"><span><%=cnvo.getTen_cnang()%></span></a>
        <%
      }
      nCap1Counter++;
      nCap2Counter = 0;
      nCap3Counter = 0;
      nFlagStart = true;
      //Xu ly menu cap 2
    }else if (nMaCNang % 100 == 0) {
      strModuleName = ((nCap2Counter+1)>=10?(nCap2Counter+1):"&nbsp;" + (nCap2Counter+1)) + "." + cnvo.getTen_cnang();
      if(strActionName.lastIndexOf("#") > -1){
        strModuleName = "<span>" + strModuleName + "</span>";
      }
      //Kiem tra co phai menu cap 2  dau tien hay ko?
      if(nFlagStart && nCap1Counter >= 1)
      {
%>       
        <ul>
          <li class="subfirst"><a href="<%=strActionName%>"><%=strModuleName%></a>
<%
        nFlagStart = false;      
      }else if(!nFlagStart){
        if(nCap3Counter > 0){
%>
          </ul></li>
<%
        }else{
%>
          </li>
<%
        }
%>
        <li><a href="<%=strActionName%>"><%=strModuleName%></a>
<%
      }    
      nCap2Counter++;
      nCap3Counter = 0;
      nFlagStart2 = true;
    }else if (nMaCNang % 10 == 0) {           
      if(nFlagStart2)
      {
        nFlagStart2 = false;
%>
       <ul> <li class="subfirst"><a href="<%=strActionName%>"><%=nCap3Counter+1%>. <%=cnvo.getTen_cnang()%></a></li>  
<%
      }else{
%>      
        <li><a href="<%=strActionName%>"><%=nCap3Counter+1%>. <%=cnvo.getTen_cnang()%></a></li>
<%
      }
      nCap3Counter++;
    }   
  }  
  if(nCap3Counter > 0){
%> 
          </ul></li>
<%
  }else{
%>
          </li>
<%
  }
  if(nCap2Counter > 0){
%>
          </ul></li>          
<%
 }  
 if(nCap2Counter == 0){
%>
          </li>
<%
  }
%>
  </ul>
  
  </TD>
  </TR>
    
      
  </TBODY>
</TABLE>
<div class="container padding0">
    <ul class="top-ttcn">
    <li>
    <span class="glyphicon glyphicon-time" aria-hidden="true"></span>
    <%=com.seatech.framework.utils.StringUtil.getCurrentDate()%>
    </li>
    <li>
    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
    <%=request.getSession().getAttribute("ten_nsd")%>
    </li>
    <li>
    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
    <a href="logoutAction.do">
     Tho&#225;t
    </a>
    </li>
    </ul>
</div>
<SCRIPT> 
	//if(window.history.forward()!=null) window.history.forward(1); 
	//document.attachEvent("onkeydown", my_onkeydown_handler_refresh); 
  
</SCRIPT>


<script language="JavaScript">

  window.onbeforeunload=function(){
    var x = window.event.clientX;
    var y = window.event.clientY;
  
    if (window.event.clientY < 0 && (window.event.clientX > (document.documentElement.clientWidth - 5) || window.event.clientX < 15) ) {
      if (confirm("Ban co chac chan muon thoat khoi he thong?"))
      {
        window.open('main.jsp?action=logoutAction.do', '', 'fullscreen=yes, scrollbars=auto');     
      }else{      //parent.location       
        window.open('main.jsp?action='+parent.location, '', 'fullscreen=yes, scrollbars=auto');        
      }
    }
  }
</script>
<div class="container padding0 main">