<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQPHTINPHIEUResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<% 
    List<String> lstAllDotDT = (List<String>)request.getAttribute("lstAllDotDT");
%>
<script type="text/javascript">
  function submitForm() {
      var frm = document.forms[0];
      if (validateForm()) {
          frm.submit();
      }
  }
 function format(elt){
    var currentVal = elt.value;
    if(currentVal.length ==3){
      elt.value = elt.value +"/";
    }

  }
  function validateForm() {
      var dot_ph = $("#dot_ph").val();
      var str = dot_ph.split("/");

      if (str.length >= 3 || str.length <= 1) {
          alert("Bạn đã nhập sai định dạng đợt phát hành : number/yyyy ! ");
          $("#dot_ph").focus();
          return false;
      }
      else if (str[1].length > 5 || str[1].length < 4) {
          alert("Bạn đã nhập sai định dạng đợt phát hành !");
          $("#dot_ph");
          return false;
      }
      if(str.length==2){
        if(str[0]==0){
         alert("Bạn không được phép nhập : 000/0000 , xxx/0000 ,000/xxxx!");
          $("#dot_ph");
          return false;
        }
        if(str[1]==0){
            alert("Bạn không được phép nhập : 000/0000 , xxx/0000 ,000/xxxx!");
          $("#dot_ph");
          return false;
        }
        
      }
      if (str[0].trim() == '' || str[1].trim() == '') {
          alert("Bạn không được nhập ký tự trắng ! ");
          $("#dot_ph").focus();
          return false;
      }
      return true;
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
            document.getElementById("dot_ph").value=str;
          }else if(str.length==2){
            str="0"+str;
            document.getElementById("dot_ph").value=str;
          }else if(str.length >= 4){
            
            return false;
          }
        }
        return true;
  }
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'close') {
          f.action = 'ListKQPhatHanhTinPhieuAction.do';
          f.submit();
      }
  }
  

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
<html:form action="/ViewUploadKQPhatHanhTinPhieuAction.do" method="post"
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
        <fmt:message key="kqphathanh.add.title"/>
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
                <html:text styleClass="form-control" onkeypress="return validateFloatKeyPress(this, event);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" onkeyup="format(this)"
                           onblur="textlostfocus(this);" tabindex="1"
                           styleId="dot_ph" property="dot_ph" maxlength="10"/>
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
          <button type="button" onclick="submitForm()" class="btn btn-default"
                  accesskey="f">
            Tải <span class="sortKey">f</span>ile
          </button>
           
          <!--<a class="btn btn-default"
             href='<html:rewrite page="/ListKQPhatHanhTinPhieuAction.do"/>'> 
            <span class="sortKey">T</span>ho&aacute;t </a>-->
           
          <button type="button" class="btn btn-default" onclick="check('close')"
                  accesskey="o" tabindex="8">
            Th<span class="sortKey">o</span>&aacute;t
          </button>
        </div>
      </div>
    </div>
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>