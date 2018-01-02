<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQPHCTIETTPCPResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<% 
      List<String> lstAllDotDT = (List<String>)request.getAttribute("lstAllDotDT");
  %>
<script type="text/javascript">
  function submitForm(type) {
      var frm = document.forms[0];
      if (type == 'close') {
          frm.action = "ListKQphathanhAction.do";
          frm.submit();
      }
      if(type == 'upload'){
        frm.action = "ViewUploadKQPhatHanhAction.do";
        if (validateForm()) {
          frm.submit();
        }
      }      
  }

  function validateForm() {
      var dot_ph = $("#dot_ph").val();
      if (dot_ph == null || dot_ph == "") {
          alert('Bạn phải nhập đợt phát hành !');
          $("#dot_ph").focus();
          return false;
      }
      return true;
  }
   function format(elt){
    var currentVal = elt.value;
    if(currentVal.length ==3){
      elt.value = elt.value +"/";
    }
  }
  function validateFloatKeyPress(el, evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        var number = el.value.split(',');
        if (charCode != 44 && charCode > 31 && (charCode < 48 || charCode > 57)&& charCode !=47) {
            return false;
        }
        if(charCode ==47){
          var str = el.value;
          if(str.length==1){
            str="0"+"0"+str;
            el.value=str;
          }else if(str.length==2) {
            str="0"+str;
            el.value=str;
          }
        }
        return true;
  }

  $(function () {
     var jsArray = [ 
        <% for (int i = 0;i < lstAllDotDT.size();i++) {
        %> "<%= lstAllDotDT.get(i) %>" <%= i + 1 < lstAllDotDT.size() ? "," : "" %>  <% 
          }
        %>];  
      $("#dot_ph").autocomplete( {
          source : jsArray
      });
       $("#dot_ph").focus();
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
<html:form action="/ViewUploadKQPhatHanhAction.do" method="post"
           enctype="multipart/form-data">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqphathanh.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        Tải tệp tin kết quả phát hành trái phiếu
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.dot_ph"/>
                 <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" maxlength="8"   
                           onkeypress="return validateFloatKeyPress(this,event);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" onkeyup="format(this)"
                           onblur="textlostfocus(this);" tabindex="1"
                           styleId="dot_ph" property="dot_ph"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.choosefile"/>
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
            Tải
            <span class="sortKey">f</span>ile
          </button>
          <button type="button" onclick="submitForm('close')"
                  class="btn btn-default" accesskey="o">
            Th<span class="sortKey">o</span>át
          </button>          
        </div>
      </div>
    </div>
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>