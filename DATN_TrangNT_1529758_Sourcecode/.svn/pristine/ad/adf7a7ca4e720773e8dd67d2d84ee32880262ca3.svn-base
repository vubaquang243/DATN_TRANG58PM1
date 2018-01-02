<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.nhomnsd.NhomNSDVO"%>
<fmt:setBundle basename="com.seatech.tp.resource.NhomNSDResoure"/>
<%@ include file="/includes/tpcp_header.inc"%>
  <html:form action="QuanLyNhomNSDUpdateAction" method="post">
<div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="qlynhomnsd.listphannhomnsd.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="qlynhomnsd.updatephannhomnsd.title.update"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="col-md-8">
          <div class="form-group">
            <!--<label class="col-sm-5 control-label">
              <fmt:message key="qlynhomnsd.addphannhomnsd.lable.tennsd"/> 
            </label>-->
            <div class="col-sm-7">
           
                         <html:hidden property="id"/>
            </div>
          </div>
        </div>
        <div class="col-md-8">
          <div class="form-group">
            <label class="col-sm-5 control-label">
              <fmt:message key="qlynhomnsd.addphannhomnsd.lable.tennsd"/> 
            </label>
            <div class="col-sm-7">
              <html:text styleClass="form-control selectpicker"
                         onkeypress="return blockKeySpecial001(event)"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" 
                         styleId="ten_nhom" property="ten_nhom"/>
            </div>
          </div>
        </div>
        <div class="col-md-8">
          <div class="form-group">
            <label class="col-sm-5 control-label">
              <fmt:message key="qlynhomnsd.addphannhomnsd.lable.loainhom"/> 
            </label>
            <div class="col-sm-7">
              <html:select property="loai_nhom"
                           styleId="loai_nhom"
                           styleClass="form-control selectpicker"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="">Vui lòng chọn</html:option>
                    <html:option value="CB-QLNQ">CB-QLNQ</html:option>
                    <html:option value="LD-QLNQ">LD-QLNQ</html:option>
                    <html:option value="QTHT">QTHT</html:option>

              </html:select>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="center">
        <button type="button" onclick="sbbut('save')" accesskey="g"
                        class="btn btn-default">
                  <span class="sortKey">G</span>hi
        </button>
        <button type="button" onclick="sbbut('close')" accesskey="o"
                        class="btn btn-default">
                  Th<span class="sortKey">o</span>&aacute;t
        </button>
  </div>
    
  </html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
document.getElementById("ten_nhom").focus();
  function sbcheck() {
      var f = document.forms[0];
      if (f.trangthai.value == "tontai") {
          alert('<fmt:message key="ktvtabmis.listktvtabmis.warning.sua.tontai"/>');
      }
  }

  function sbbut(type) {
      var f = document.forms[0];

      if (type == 'save') {
          if (f.ten_nhom.value.length >= 50) {
              alert('Tên nhóm không được nhập quá 50 kí tự');
              document.getElementById("ten_nhom").focus();
              return;
          }
          if (f.ten_nhom.value == null || f.ten_nhom.value == "") {
              alert('Không được để trống tên nhóm');
              document.getElementById("ten_nhom").focus();
              return;
          }
          f.action = 'QuanLyNhomNSDUpdateExcAction.do';
      }
      if (type == 'close') {
          f.action = 'QuanLyNhomNSDListAction.do';
      }
      f.submit();
  }  
</script>