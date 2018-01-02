<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyNSDResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      jQuery('#temp').focus();
      enable();
  });

  var counter = 0;

  function enable() {
      var f = document.forms[0];
      if (f.enable.value == "1") {
          f.ma_kb.disabled = false;
          f.ma_tabmis.disabled = false;
          f.ten_nhom.disabled = false;
          f.abc.disabled = false;
          f.bfind.disable = false;
          f.mac_address.disable = false;
          document.getElementById("displayname").focus();
      }
      if (f.enable.value == "0") {
          f.displayname.value = "";
          f.telephonenumber.value = "";
          f.title.value = "";
          f.department.value = "";
          f.email.value = "";
          f.description.value = "";
          f.temp.focus();
      }
  }

  function subString() {
      var f = document.forms[0];
      var string = f.temp.value;
      f.domain.value = string.substring(0, 2);
      f.ma_nsd.value = string.substring(3);

  }
  //  function sbcheck() {
  //      var f = document.forms[0];
  //      if (f.trangthai.value == "tontai") {
  //          alert('<fmt:message key="ktvtabmis.listktvtabmis.warning.sua.tontai"/>');
  //      }
  //  }
  //
  function blockKeySpecial(e) {
      var key;
      if (window.event)// IE
      {
          key = e.keyCode;
      }
      else if (e.which)// Netscape/Firefox/Opera
      {
          key = e.which;
      }
      if (key == 37 || key == 38 || key == 39 || key == 40 || key == 16//arrow key
 || key == 35 || key == 36 || key == 9//home,end,tab
 || key == 46 || key == 8//del,insert,backspace
 || key == 45 || (key > 31 && key < 44) || (key > 57 && key < 65) || (key > 90 && key < 97) || (key > 123 && key < 127))//Cac ki tu dac biet
          event.returnValue = false;
      else 
          return event.returnValue = true;
  }

  function sbbut(type) {
      var f = document.forms[0];
      if (type == 'find') {
          if (f.temp.value.length >= 50) {
              alert('Mã NSD nhập không quá 50 kí tự');
              f.temp.focus();
              return;
          }
          if (f.temp.value == null || f.temp.value == "") {
              alert('Nhập vào mã nhân viên');
              f.temp.focus();
              return;
          }
          var nsd= f.temp.value.split("/");
          f.ma_nsd.value = nsd[1];
          f.domain.value ="tw";
          f.action = 'QuanLyNSDExecuteAction.do';
          f.submit();
      }
      else if (type == 'save') {
        //  subString();
          if (f.temp.value == null || f.temp.value == "") {
              alert('Bạn phải nhập mã nhân viên và tìm kiếm');
              f.temp.focus();
              return;
          }
          else if ((f.id_nhom.value == 481 || f.id_nhom.value == 482) && f.ma_kb.value != '0003') {
              alert('Chỉ có thể gán cán bộ thuộc SGD cho nhóm này!');
              f.ma_kb.focus();
              return;
          }
          else if (f.id_nhom.value == null || f.id_nhom.value == '') {
              alert('Phải chọn nhóm NSD.');
              return;
        }else if(f.displayname.value == null || f.displayname.value== ''){
                alert('Không có thông tin người sử dụng ! ');
                f.temp.focus();
                return ;
        }
          else {
              if (++counter == 1) {
                  f.bfind.disable = true;
                  f.action = 'QuanLyNSDAddExcAction.do';
                  f.submit();

              }

          }

      }
      if (type == 'close') {
          if(f.ma_nsd.value != null){
          if(confirm('Bạn có chắc chắn muốn thoát không !')){
          f.ma_nsd.value = "";
          f.ten_nsd.value = "";
          f.trang_thai.value = "";      
          f.action = 'QuanLyNSDListAction.do';
          f.submit();
          }
      }
    }
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
      var pattern = /[!@#$%^&*()_+='"\/\[\]\.\,\:\;\{\}\<\>\?]/;
      if (pattern.match(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }
  
  

</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="QuanLyNSDAddExcAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="quanlynsd.listnsd.title"/></strong>
    </h1>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="quanlynsd.listnsd.title.add"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="col-md-8 col-md-offset-2">
          <label for="" class="col-sm-3 control-label">M&atilde; nh&acirc;n vi&ecirc;n</label>
          <div class="col-sm-9">
            <div class="input-group">
              <html:text property="temp" styleId="msdsearch"
                         onkeypress="return blockKeySpecial001(event);"
                         styleClass="form-control" value="tw/"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" onfocus="textfocus(this);"/>
                      
              <span class="input-group-btn"> 
                <button class="btn btn-default" onclick="sbbut('find')"
                        accesskey="t">
                  <span class="sortKey">T</span>ìm kiếm
                </button>
                 </span>
            </div>
          </div>
        </div>
      </div>
      <%
            if(request.getAttribute("status") != null){
            String StrStatus = request.getAttribute("status").toString();
            String id = request.getAttribute("nsdID")==null?"":request.getAttribute("nsdID").toString();
            %>
       
      <!-- chỗ này cần ẩn đi... -->
      <!--<div class="alert alert-danger" role="alert">
        <fmt:message key="<%=StrStatus%>">
          <fmt:param>
            <%=id%>
          </fmt:param>
        </fmt:message>
      </div>-->
      <%}%>
    </div>
  </div>
  <div class="row">
    <div class="col-md-6">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h2 class="panel-title">
            <fmt:message key="quanlynsd.addnsd.lable.thongtin"/>
          </h2>
        </div>
        <div class="panel-body">
          <div class="form-horizontal">
            <div class="form-group">
              <label for="hoten" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.addnsd.lable.thongtin.hoten"/>
              </label>
              <div class="col-sm-9">
                <html:text styleClass="form-control" id="hoten"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           readonly="true"
                           onblur="textlostfocus(this);" tabindex="1"
                           property="displayname"/>
              </div>
            </div>
            <div class="form-group">
              <label for="chucvu" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.addnsd.lable.thongtin.chucvu"/>
              </label>
              <div class="col-sm-9">
                <html:text styleClass="form-control" id="chucvu" readonly="true"
                           property="title"/>
              </div>
            </div>
            <div class="form-group">
              <label for="phongban" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.addnsd.lable.thongtin.phongban"/>
              </label>
              <div class="col-sm-9">
                <html:text styleClass="form-control" id="phongban"
                           readonly="true" property="department"/>
              </div>
            </div>
            <div class="form-group">
              <label for="sodienthoai" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.addnsd.lable.thongtin.sodienthoai"/>
              </label>
              <div class="col-sm-9">
                <html:text styleClass="form-control" id="sodienthoai"
                           readonly="true" property="telephonenumber"/>
              </div>
            </div>
            <div class="form-group">
              <label for="email" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.addnsd.lable.thongtin.email"/>
              </label>
              <div class="col-sm-9">
                <html:text styleClass="form-control" id="email" readonly="true"
                           property="email"/>
              </div>
            </div>
            <div class="form-group">
              <label for="ghichu" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.addnsd.lable.thongtin.ghichu"/>
              </label>
              <div class="col-sm-9">
                <html:text styleClass="form-control" id="ghichu" readonly="true"
                           property="description"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h2 class="panel-title">
            <fmt:message key="quanlynsd.addnsd.lable.thongtin.nhap"/>
          </h2>
        </div>
        <div class="panel-body">
          <div class="form-horizontal" role="form">
          <!--
            <div class="form-group">
              <label for="" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.makb"/>
                <font color="Red">*</font>
                :
              </label>
              <div class="col-sm-9">
                <div class="row">
                  <div class="col-xs-4">
                    <html:text styleClass="form-control" property="ma_kb"
                               styleId="ma_kb"
                               onkeypress="return numberBlockKey(event)"
                               maxlength="4" disabled="true" tabindex="2"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               onfocus="textfocus(this);" onblur="getTenKhoBac('ma_kb', 'ten_kb', 'kb_id','addNSDLoadDMKBAction.do');
                          textlostfocus(this);"/>
                  </div>
                  <div class="col-xs-8">
                    <html:text styleClass="form-control" property="ten_kb"
                               readonly="true" styleId="ten_kb"
                               onmouseout="UnTip()"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.matabmis"/>
                :
              </label>
              <div class="col-sm-9">
                <html:text styleClass="form-control" id="ma_tabmis"
                           disabled="true" property="ma_tabmis" tabindex="3"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </div>
            </div>-->
            <html:hidden styleClass="form-control" property="kb_id" value="1"
                               styleId="kb_id"/>
            <div class="form-group">
              <label for="" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.nhomnsd"/>
                :
              </label>
              <div class="col-sm-9">
                <html:select 
                             styleClass="form-control "
                             property="id_nhom" styleId="ten_nhom" tabindex="4"
                             onblur="getTenNhom()"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                             <html:option value="">Vui lòng chọn</html:option>
                  <logic:notEmpty name="listNNSD">
                    <html:optionsCollection name="listNNSD" value="id"
                                            label="ten_nhom"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
            <div class="form-group">
              <label for="" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>
                :
              </label>
              <div class="col-sm-9">
                <html:select disabled="true"
                             styleClass="form-control " tabindex="4"
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
            <!--
            <div class="form-group">
              <label for="" class="col-sm-3 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.Mac"/>
                :
              </label>
              <div class="col-sm-9">
                <html:text property="mac_address" tabindex="6"
                           onkeypress="return blockKeySpecial001(event)"
                           styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </div>
            </div>
            -->
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="center">
    <button type="button" class="btn btn-default" onclick="sbbut('save')"
            accesskey="g" tabindex="7" id="bfind">
      <span class="sortKey">G</span>hi
    </button>
     
    <button type="button" class="btn btn-default" onclick="sbbut('close')"
            accesskey="o" tabindex="8">
      Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <html:hidden property="domain"/>
  <html:hidden property="ma_nsd"/>
  <html:hidden property="enable"/>
  <html:hidden property="ten_nsd"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>