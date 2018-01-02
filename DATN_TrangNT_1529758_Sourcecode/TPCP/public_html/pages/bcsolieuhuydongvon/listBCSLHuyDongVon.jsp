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
<fmt:setBundle basename="com.seatech.tp.resource.BCSLHuyDongVonResource"/>
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
              f.action = 'SearchBCSLHuyDongVonAction.do';
              f.submit();
          }
      }
      else if (type == 'print') {
          if (validateForm()) {
              f.action = 'PrintBCSLHuyDongVonAction.do';
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
<html:form action="/ListBCSLHuyDongVonAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="BCSLHuyDongVon.title"/></strong>
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
        <fmt:message key="BCSLHuyDongVon.list.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-4 control-label">
                  Ngày phát hành: từ <span style="color:red"> (*)</span>
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
                  Ngày phát hành: đến<span style="color:red"> (*)</span>
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
        </div>
      </div>
      <%-- ************************************--%>
    </div>
    <div class="button-group center">
      <button id="tracuu" type="button" onclick="check('print')"
              class="btn btn-default" accesskey="t">
        <span class="sortKey">T</span>ổng hợp
      </button>
       
      
       
      <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>&aacute;t
      </button>
    </div>
  </div>
  <!--<div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="BCSLHuyDongVon.list.result"/>
      </h2>
    </div>
    --><%-- Hiển thị list--%><!--
    <table class="table table-bordered">
      <thead>
        <tr class="header">
          <th class="center" rowspan="2">STT</th>
          <th class="center" rowspan="2">
            <fmt:message key="BCSLHuyDongVon.chi_tieu"/>
          </th>
          <th class="center">
            <fmt:message key="BCSLHuyDongVon.du_no"/>
          </th>
          <th class="center" rowspan="2">
            <fmt:message key="BCSLHuyDongVon.phat_hanh_trong_ky"/>
          </th>
          <th class="center" colspan="3">
            <fmt:message key="BCSLHuyDongVon.nghia_vu_tra_no"/>
          </th>
          <th class="center" >
            <fmt:message key="BCSLHuyDongVon.du_no"/>
          </th>
        </tr>
        <tr class="header">
         <th style="center">
            <fmt:message key="BCSLHuyDongVon.dau_ky"/>
          </th>
          <th style="center">
            <fmt:message key="BCSLHuyDongVon.tra_goc"/>
          </th>
          <th class="center">
            <fmt:message key="BCSLHuyDongVon.tra_lai"/>
          </th>
          <th class="center">
            <fmt:message key="BCSLHuyDongVon.tong_cong"/>
          </th>
          <th class="center">
            <fmt:message key="BCSLHuyDongVon.cuoi_ky"/>
          </th>
        </tr>
      </thead>
       
      <tbody>
        <logic:empty name="lstBCSLHuyDongVon">
          <tr>
            <td colspan="8" align="center" class="color_red">
              <fmt:message key="BCSLHuyDongVon.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstBCSLHuyDongVon">
          <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
          <logic:iterate id="objBCSLHuyDongVon" name="lstBCSLHuyDongVon"
                         indexId="stt">
            <td class="center">
              <bean:write name="objBCSLHuyDongVon" property="stt"/>
            </td>
            <td class="center">
              <bean:write name="objBCSLHuyDongVon" property="chi_tieu"/>
            </td>
            <td class="left">
              <bean:write name="objBCSLHuyDongVon" property="dau_ky"/>
            </td>
            <td class="left">
              <bean:write name="objBCSLHuyDongVon"
                          property="phat_hanh_trong_ky"/>
            </td>
            <td class="center">
              <bean:write name="objBCSLHuyDongVon" property="tra_goc"/>
            </td>
            <td class="center">
              <bean:write name="objBCSLHuyDongVon" property="tra_lai"/>
            </td>
            <td class="left">
              <bean:write name="objBCSLHuyDongVon" property="tong_cong"/>
            </td>
            <td class="right">
              <bean:write name="objBCSLHuyDongVon" property="cuoi_ky"/>
            </td>
          </logic:iterate>
          <tr>
            <td colspan="8" align="center">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    --><%-- ************************************--%><!--
  </div>-->
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>