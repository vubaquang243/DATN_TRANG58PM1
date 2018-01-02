<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyTPResource"/>
<script type="text/javascript">
  //jQuery.noConflict();
  jQuery(document).ready(function () {
  });

  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
  }

  function validateForm() {
      var ngay_tao_tu_ngay = $("#ngay_tao_tu_ngay").val();
      var ngay_tao_den_ngay = $("#ngay_tao_den_ngay").val();
      if ((ngay_tao_tu_ngay != null || ngay_tao_tu_ngay != "") && (ngay_tao_den_ngay != null || ngay_tao_den_ngay != "")) {
          var startDate = parseDate(ngay_tao_tu_ngay).getTime();
          var endDate = parseDate(ngay_tao_den_ngay).getTime();
          if (startDate > endDate) {
              alert('Ngày tạo đến ngày phải lớn hơn từ ngày !');
              $("#ngay_tao_den_ngay").focus();
              return false;
          }
      }
      return true;
  }

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'add') {
          f.action = 'QuanLyTPAddAction.do';
      }
      if (type == 'close') {
          f.action = 'mainAction.do';
      }
//      if (validateForm()) {
          f.submit();
//      }
  }
  
  $(function () {
      $("#ma_tp").focus();
      $("#ngay_tao_tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tao_den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchTPCPAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="quanlytp.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <logic:messagesPresent message="true">
      <html:messages id="message" message="true">
        <logic:present name="message">
          <!-- Messages <bean:write name='message' filter='false'/> -->
          <div class="messages">
            <fmt:message key="<%=message%>"/>
          </div>
        </logic:present>
      </html:messages>
    </logic:messagesPresent>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="quanlytp.list.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="quanlytp.add.matp"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           maxlength="10" styleId="ma_tp"
                           onblur="textlostfocus(this);upperCase(this);" tabindex="1"
                           property="ma_tp"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="quanlytp.add.noicap"/>
              </label>
              <div class="col-sm-8">
                <html:select property="noi_cap" styleId="noi_cap" tabindex="2"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="VSD">VSD</html:option>
                  <html:option value="KBNN">KBNN</html:option>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="quanlytp.add.kyhan"/>
              </label>
              <div class="col-sm-8">
                <html:select styleClass="form-control selectpicker"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="3"
                             styleId="ky_han" property="ky_han">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="lstKyHan">
                    <html:optionsCollection name="lstKyHan" value="guid"
                                            label="name_ky_han"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
        </div>
        <!--<div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y tạo:
                                                                từ</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="4"
                             maxlength="10" styleId="ngay_tao_tu_ngay"
                             property="ngay_tao_tu_ngay"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y tạo:
                                                                đến</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);" maxlength="10"
                             onblur="textlostfocus(this);" tabindex="5"
                             styleId="ngay_tao_den_ngay"
                             property="ngay_tao_den_ngay"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
        </div>-->
      </div>
    </div>
    <div class="button-group center">
      <button id="tracuu" type="button" onclick="check('find')"
              class="btn btn-default" accesskey="t">
        <span class="sortKey">T</span>ra cứu
      </button>
       
      <button id="them" type="button" onclick="check('add')"
              class="btn btn-default" accesskey="m">
        Thêm <span class="sortKey">m</span>ới
      </button>
       
      <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>&aacute;t
      </button>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="quanlytp.list.result"/>
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th>
          <fmt:message key="quanlytp.add.ptph"/>
        </th>
        <th>
          <fmt:message key="quanlytp.list.matp"/>
        </th>
        <th>
          <fmt:message key="quanlytp.add.noicap"/>
        </th>
        <th>
          <fmt:message key="quanlytp.add.kyhan"/>
        </th>
        <th>
          <fmt:message key="quanlytp.add.trangthai"/>
        </th>       
        <th>
          <fmt:message key="quanlytp.list.sua"/>
        </th>
        <th>
          <fmt:message key="quanlytp.list.xoa"/>
        </th>
      </thead>
       
      <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
       
      <tbody>
        <logic:empty name="lstTPCP">
          <tr>
            <td colspan="9" align="center" class="color_red">
              <fmt:message key="quanlytp.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstTPCP">
          <logic:iterate id="objTPCP" name="lstTPCP" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td class="center">
                <%=stt + 1%>
              </td>
              <td>
                <bean:write name="objTPCP" property="ten_phuong_thuc_ph"/>
              </td>
              <td>
                <bean:write name="objTPCP" property="ma_tp"/>
              </td>
              <td>
                <bean:write name="objTPCP" property="noi_cap"/>
              </td>
              <td>
                <bean:write name="objTPCP" property="ky_han"/>
              </td>
              <td>
                <logic:equal name="objTPCP" property="trang_thai" value="1">M&atilde;
                                                                            mới</logic:equal>
                 
                <logic:equal name="objTPCP" property="trang_thai" value="2">Đang
                                                                            hoạt
                                                                            động</logic:equal>
                 
                <logic:equal name="objTPCP" property="trang_thai" value="3">Kho&aacute;</logic:equal>
              </td>              
              <td class="icon">
                <logic:notEqual value="02" name="objTPCP" property="trang_thai">
                  <logic:notEqual value="03" name="objTPCP"
                                  property="trang_thai">
                    <a href='<html:rewrite page="/QuanLyTPCPUpdateAction.do"/>?longid=<bean:write name="objTPCP" property="guid"/>'>
                      <span class="glyphicon glyphicon-pencil success"></span></a>
                  </logic:notEqual>
                </logic:notEqual>
              </td>
              <td class="icon">
                <logic:notEqual value="02" name="objTPCP" property="trang_thai">
                  <logic:notEqual value="03" name="objTPCP"
                                  property="trang_thai">
                    <a href='<html:rewrite page="/QuanLyTPCPDeleteAction.do"/>?longid=<bean:write name="objTPCP" property="guid"/>'
                       onclick="return confirm('Bạn có muốn xóa bản ghi này ?')">
                      <span class="glyphicon glyphicon-trash warning"></span></a>
                  </logic:notEqual>
                </logic:notEqual>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
        <tr>
          <td colspan="9" align="center">
            <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
          </td>
        </tr>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>