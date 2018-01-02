<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.SoTongHopTpcpResource"/>
<script type="text/javascript">
  //jQuery.noConflict();
  jQuery(document).ready(function () {
         
  });
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'close') {
          f.action = 'mainAction.do';
          f.submit()
      }
      else if (type == 'find') {
          if (validateForm()) {
              f.action = 'SearchSoTongHopTpcpAction.do';
              f.submit();
          }
      }
      else if (type == 'print') {
          if (validateForm()) {
              f.action = 'PrintSoTongHopTpcpAction.do';
              var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
              newDialog = window.open('', '_form', params);
              f.target = '_form';
              f.submit();
          }
      }

  }

  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
  }

  function validateForm() {
      var ngay_phat_hanh_tu_ngay = $("#ngay_phat_hanh_tu_ngay").val();
      if (ngay_phat_hanh_tu_ngay == null || ngay_phat_hanh_tu_ngay == "") {
          alert('Bạn phải nhập ngày phát hành từ ngày !');
          $("#ngay_phat_hanh_tu_ngay").focus();
          return false;
      }
      var ngay_phat_hanh_den_ngay = $("#ngay_phat_hanh_den_ngay").val();
      if (ngay_phat_hanh_den_ngay == null || ngay_phat_hanh_den_ngay == "") {
          alert('Bạn phải nhập ngày phát hành đến ngày !');
          $("#ngay_phat_hanh_den_ngay").focus();
          return false;
      }
      if ((ngay_phat_hanh_tu_ngay != null || ngay_phat_hanh_tu_ngay != "") && (ngay_phat_hanh_den_ngay != null || ngay_phat_hanh_den_ngay != "")) {
          var startDate = parseDate(ngay_phat_hanh_tu_ngay).getTime();
          var endDate = parseDate(ngay_phat_hanh_den_ngay).getTime();
          if (startDate > endDate) {
              alert('Ngày phát hành đến ngày phải lớn hơn từ ngày !');
              $("#ngay_phat_hanh_den_ngay").focus();
              return false;
          }
      }
      return true;
  }

  $(function () {
      $("#dot_ph").focus();
      $("#ngay_phat_hanh_tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_phat_hanh_den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/ListSoTongHopTpcpAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="SoTongHopTpcp.title"/></strong>
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
        <fmt:message key="SoTongHopTpcp.list.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-4 control-label">
                  <fmt:message key="SoTongHopTpcp.list.tungay"/><span style="color:red"> (*)</span>
                </label>
                <div class="col-sm-8">
                  <div class="input-group date">
                    <html:text styleClass="form-control" maxlength="10"
                               onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="3"
                               styleId="ngay_phat_hanh_tu_ngay"
                               property="ngay_phat_hanh_tu_ngay"/>
                     
                    <span class="input-group-addon"> 
                      <span class="glyphicon glyphicon-calendar"></span>
                       </span>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-4 control-label">
                  <fmt:message key="SoTongHopTpcp.list.denngay"/><span style="color:red"> (*)</span>
                </label>
                <div class="col-sm-8">
                  <div class="input-group date">
                    <html:text styleClass="form-control" maxlength="10"
                               onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="4"
                               styleId="ngay_phat_hanh_den_ngay"
                               property="ngay_phat_hanh_den_ngay"/>
                     
                    <span class="input-group-addon"> 
                      <span class="glyphicon glyphicon-calendar"></span>
                       </span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!--<div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-4 control-label">
                  <fmt:message key="SoTongHopTpcp.phuong_thuc"/>
                </label>
                <div class="col-sm-8">
                  <html:select property="phuong_thuc_tong_hop"
                               styleId="phuong_thuc_tong_hop" tabindex="5"
                               styleClass="form-control selectpicker"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="">Vui l&ograve;ng chọn</html:option>
                    <html:option value="TPCP">TPCP</html:option>
                    <html:option value="BHXH">BHXH</html:option>
                  </html:select>
                </div>
              </div>
            </div>
          </div>-->
        </div>
      </div>
      <%-- ************************************--%>
    </div>
    <div class="button-group center">
      <button id="tracuu" type="button" onclick="check('print')"
              class="btn btn-default" accesskey="t">
        <span class="sortKey">T</span>ổng hợp
      </button>
       
      <%--<logic:notEmpty name="lstSoTongHopTpcp">
        <button type="button" onclick="check('print')" class="btn btn-default"
                accesskey="i">
          <span class="sortKey">I</span>
          n
        </button>
      </logic:notEmpty>--%>
       
      <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>&aacute;t
      </button>
    </div>
  </div>
  <!--<div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="SoTongHopTpcp.list.result"/>
      </h2>
    </div>
    --><%-- Hiển thị list--%><!--
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.ngay_dt"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.phuong_thuc"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.ma_tpcp"/>
        </th>
        <th style="center">
          <fmt:message key="SoTongHopTpcp.ngay_ph"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.ngay_dao_han"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.ky_han"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.ls_trung_thau"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.ls_danh_nghia"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.ma_tv"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.nguoi_so_huu"/>
        </th>
        <th class="center">
          <fmt:message key="SoTongHopTpcp.khoi_luong"/>
        </th>
      </thead>
       
      <tbody>
        <logic:empty name="lstSoTongHopTpcp">
          <tr>
            <td colspan="12" align="center" class="color_red">
              <fmt:message key="SoTongHopTpcp.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstSoTongHopTpcp">
          <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
          <logic:iterate id="objSoTongHopTpcp" name="lstSoTongHopTpcp"
                         indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td class="center">
                <%=stt + 1%>
              </td>
              <td class="center">
                <bean:write name="objSoTongHopTpcp" property="ngay_dt"/>
              </td>
              <td class="left">
                <bean:write name="objSoTongHopTpcp" property="phuong_thuc_ph"/>
              </td>
              <td class="left">
                <bean:write name="objSoTongHopTpcp" property="ma_tp"/>
              </td>
              <td class="center">
                <bean:write name="objSoTongHopTpcp" property="ngay_ph"/>
              </td>
              <td class="center">
                <bean:write name="objSoTongHopTpcp" property="ngay_dao_han"/>
              </td>
              <td class="left">
                <bean:write name="objSoTongHopTpcp" property="ky_han"/>
              </td>
              <td class="right">
                <bean:write name="objSoTongHopTpcp" property="ls_trung_thau"/>
              </td>
              <td class="right">
                <bean:write name="objSoTongHopTpcp" property="ls_danh_nghia"/>
              </td>
              <td class="left">
                <bean:write name="objSoTongHopTpcp"
                            property="thanh_vien_dau_thau"/>
              </td>
              <td class="left">
                <bean:write name="objSoTongHopTpcp"
                            property="ten_nguoi_so_huu"/>
              </td>
              <td class="right">
                <bean:define id="kl_trung_thau" name="objSoTongHopTpcp"
                             property="kl_trung_thau"/>
                 
                <%=StringUtil.convertNumberToString(kl_trung_thau.toString(),"VND")%>
              </td>
            </tr>
          </logic:iterate>
          <tr>
            <td colspan="12" align="center">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
      </tbody>
       
      <html:hidden property="ngay_phat_hanh_tu_ngay" styleId="ngay_phat_hanh_tu_ngay"/>
      <html:hidden property="ngay_phat_hanh_den_ngay" styleId="ngay_phat_hanh_den_ngay"/>
      <html:hidden property="phuong_thuc_tong_hop" styleId="phuong_thuc_tong_hop"/>
      <html:hidden property="pageNumber" value="1"/>
    </table>
    --><%-- ************************************--%><!--
  </div>-->
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>