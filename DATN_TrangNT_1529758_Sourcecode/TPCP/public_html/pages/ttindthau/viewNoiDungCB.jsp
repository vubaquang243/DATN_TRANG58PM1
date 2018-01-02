<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ page import="com.seatech.tp.ttindthau.vo.ThongTinDauThauVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPTHONGTINDAUTHAUResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<% 
        List<QuanLyTPCPVO> lstAllTPCP = (List<QuanLyTPCPVO>)request.getAttribute("lstAllTPCP");
        ThongTinDauThauVO thongTinDotDTCu = (ThongTinDauThauVO)request.getAttribute("ThongTinDotDTCu");
  %>
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';
   if(type=='close'){
     f.action='ListTTinDThauAction.do';
     f.submit();
   }
      
  }
  //  $(function () {
  //      $("#noi_dung_mail").val($("#bosung").text());
  //  });
</script>
<html:form action="AddExeTTinDThauAction" method="post">
  <html:hidden property="noi_dung_mail" name="ThongTinDauThauForm"
               styleId="noi_dung_mail" value=""/>
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="ttindthau.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="ttindthau.congbo.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--start cong bo lan dau  -->
        
          <div class="adjoined-bottom" >
            <div class="grid-container">
              <div class="grid-width-100">
                <textarea cols="80" rows="50" id="editor" readonly="true">
               
                  <logic:notEmpty name="voThongBao" >
                    <bean:write name="voThongBao" property="email_content" filter="false"/>
                  </logic:notEmpty>
                </textarea>
              </div>
            </div>
          </div>
      
         
        <!--end cong bo lan dau  -->
         
        <!--start cong bo bo sung  -->
         
     
         
        <!--end cong bo bo sung -->
      </div>
    </div>
  </div>
  <script>
    initSample();
  </script>
  <div class="center">

     
     <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>át</a> 
      </button>
  </div>
  <html:hidden property="trang_thai" styleId="trang_thai" value="05"/>
  <html:hidden property="guid" styleId="guid"/>
  <script type="text/javascript">
    $(function () {
        $("#noi_dung_mail").val($("#bosung").text());
    });
  </script>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>