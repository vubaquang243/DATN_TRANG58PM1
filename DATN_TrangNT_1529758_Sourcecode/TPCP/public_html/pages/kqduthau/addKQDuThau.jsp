<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQDUTHAUResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<% 
      List<String> lstAllDotDT = (List<String>)request.getAttribute("lstAllDotDT");
  %>
<script type="text/javascript">
  function submitForm(type) {
      var frm = document.forms[0];
      if (type == 'thoat') {
          frm.action = "ListKQduthauAction.do";
          frm.submit();
      }
      if (type == 'upload') {
          frm.action = "ViewUploadKQDuThauAction.do";
          if (validateForm()) {
              frm.submit();
          }
      }
  }
  
  function validateForm() {
//      var dot_ph = $("#dot_dt").val();
//      if (dot_ph == null || dot_ph == "") {
//          alert('Bạn phải nhập đợt phát hành !');
//          $("#dot_dt").focus();
//          return false;
//      }
      return true;
  }

  $(function () {
     var jsArray = [ 
        <% for (int i = 0;i < lstAllDotDT.size();i++) {
        %> "<%= lstAllDotDT.get(i) %>" <%= i + 1 < lstAllDotDT.size() ? "," : "" %>  <% 
          }
        %>];  
      $("#dot_dt").autocomplete( {
          source : jsArray
      });
        $("#dot_dt").focus();
  });
</script>
<style>
  ::-webkit-file-upload-button {
  background: transparent;
  border: none;
  width: 0px !important;
  overflow: hidden;
  margin: 0;
  padding: 0; 
}
  </style>
<html:form action="/ViewUploadKQDuThauAction.do" method="post"
           enctype="multipart/form-data">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqduthau.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="kqduthau.add.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <!--<div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">Đợt phát hành <span style="color:red">(*)</span></label>   
              <div class="col-sm-7">
                <html:text styleClass="form-control" maxlength="10"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"   onkeypress="return blockKeyDT(event)"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="1"
                           styleId="dot_dt" property="dot_dt"/>
              </div>
            </div>
          </div>
        </div>-->
        <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqduthau.choosefile"/>
                    <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:file property="fileUpload"
                           styleClass="file form-control"/>
              </div>
            </div>
          </div>
        </div>
        <div class="center">
          <button type="button" onclick="submitForm('upload')"
                  class="btn btn-default" accesskey="f">
            Tải <span class="sortKey">f</span>ile
          </button>
           <button type="button" onclick="submitForm('thoat')"
                  class="btn btn-default" accesskey="o">
            Th<span class="sortKey">o</span>át
          </button>          
        </div>
      </div>
    </div>
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>