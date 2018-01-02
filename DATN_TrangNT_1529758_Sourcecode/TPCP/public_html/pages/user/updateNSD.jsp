<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyNSDResource"/>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="QuanLyNSDUpdateExcAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="quanlynsd.listnsd.title.update"/></strong>
    </h1>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="quanlynsd.listnsd.title.update"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-horizontal">
              <!--<div class="form-group">
                <label for="" class="col-sm-4 control-label">TIEUDE</label>
                <div class="col-sm-8">
                  <div class="row">
                    <div class="col-xs-4">
                      <html:text styleClass="form-control" property="ma_kb"
                                 styleId="ma_kb" maxlength="4"
                                 onblur="getTenKhoBac('ma_kb','ten_kb','kb_id','addNSDLoadDMKBAction.do');textlostfocus(this);"
                                 disabled="true" onfocus="textfocus(this);"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                    </div>
                    <div class="col-xs-8">
                      <html:text property="ten_kb" styleId="ten_kb"
                                 disabled="true" styleClass="form-control"/>
                    </div>
                    <html:hidden property="kb_id" styleId="kb_id"/>
                     
                    <html:hidden property="ten_kb" styleId="ten_kb"/>
                  </div>
                </div>
              </div>-->
              <div class="form-group">
                <label for="" class="col-sm-4 control-label">
                  <fmt:message key="quanlynsd.listnsd.lable.list.manv"/>
                </label>
                <div class="col-sm-8">
                  <html:text property="ma_nsd" disabled="true"
                             styleClass="form-control"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </div>
              </div>
         
              <div class="form-group">
                <label for="" class="col-sm-4 control-label">
                  <fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>
                </label>
                <div class="col-sm-8">
                  <html:select styleClass="form-control selectpicker"
                               property="trang_thai" styleId="abc"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="01">
                      <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.action"/>
                    </html:option>
                    <html:option value="02">
                      Ngừng hoạt động
                    </html:option>
                   
                  </html:select>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-horizontal">
              <div class="form-group">
                <label for="" class="col-sm-4 control-label">
                  <fmt:message key="quanlynsd.listnsd.lable.list.tennv"/>
                </label>
                <div class="col-sm-8">
                  <html:text property="ten_nsd"
                             onkeypress="return blockKeySpecial001(event)"
                             styleClass="form-control" readonly="true"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </div>
              </div>
              <div class="form-group">
                <label for="" class="col-sm-4 control-label">
                  <fmt:message key="quanlynsd.listnsd.lable.list.chucdanh"/>
                </label>
                <div class="col-sm-8">
                  <html:text property="chuc_danh" readonly="true"
                             onkeypress="return blockKeySpecial001(event)"
                             styleClass="form-control"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </div>
              </div>
              <!--<div class="form-group">
                <label for="" class="col-sm-4 control-label">
                  <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.Mac"/>
                </label>
                <div class="col-sm-8">
                  <html:text property="mac_address"
                             onkeypress="return blockKeySpecial001(event)"
                             styleClass="form-control"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </div>
              </div>-->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="panel-footer center">
    <button type="button" class="btn btn-default" onclick="sbbut('save')"
            accesskey="g">
      <span class="sortKey">G</span>hi
    </button>
     
    <button class="btn btn-default" type="button" onclick="sbbut('close')"
            accesskey="o">
      Th<span class="sortKey">o</span>&aacute;t
    </button>
     
    <%-- lay ten va ma kho bac--%>
     
    <html:hidden property="id"/>
     
    <html:hidden property="ma_nsd" styleId="ma_nsd_hd"/>
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>
<script type="text/javascript">
  document.getElementById("ten_nsd").focus();

  function sbbut(type) {
      var f = document.forms[0];
      if (type == 'save') {
//          if (f.ma_tabmis.value.length >= 20) {
//              alert('mã tabmis không quá 20 kí tự');
//              document.getElementById("ma_tabmis").focus();
//              return;
//          }
          if (f.ten_nsd.value.length >= 100) {
              alert('Tên nhân viên không được quá 100 kí tự');
              document.getElementById("chuc_danh").focus();
              return;
          }
          if (f.chuc_danh.value.length >= 100) {
              alert('Mã chức danh không quá 100 kí tự');
              document.getElementById("chuc_danh").focus();
              return;
          }
          if (f.chuc_danh.value == null || f.chuc_danh.value == "") {
              alert('Bạn phải nhập chức danh');
              document.getElementById("chuc_danh").focus();
              return;
          }
          f.action = 'QuanLyNSDUpdateExcAction.do';
      }
      if (type == 'close') {
          //alert('asasa');
          f.trang_thai.value = "";
          f.ma_nsd[0].value = "";
          f.ma_nsd[1].value = "";
          f.ten_nsd.value = "";
//          f.ten_kb[0].value = "";
//          f.ten_kb[1].value = "";
//          f.ma_kb.value = "";

          f.action = 'QuanLyNSDListAction.do';
      }
      f.submit();
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
          var pattern = /[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?\\]/;
          if (pattern.match(character)) {
              character.replace(character, "");
              return false;
          }
          else {
              return true;
          }
      }
</script>