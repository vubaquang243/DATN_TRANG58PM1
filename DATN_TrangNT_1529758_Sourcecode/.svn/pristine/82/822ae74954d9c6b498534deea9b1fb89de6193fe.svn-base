<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.TTLaiGocVndResource"/>
<%@ page import="com.seatech.framework.utils.StringUtil"%>

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
      var ngay_to_chuc_ph_tu_ngay = $("#ngay_to_chuc_ph_tu_ngay").val();
      var ngay_to_chuc_ph_den_ngay = $("#ngay_to_chuc_ph_den_ngay").val();
      var ngay_ph_tu_ngay = $("#ngay_ph_tu_ngay").val();
      var ngay_ph_den_ngay = $("#ngay_ph_den_ngay").val();
      
      if((ngay_to_chuc_ph_tu_ngay == null || ngay_to_chuc_ph_tu_ngay == '')
       && (ngay_to_chuc_ph_den_ngay == null || ngay_to_chuc_ph_den_ngay == '')
       && (ngay_ph_tu_ngay == null || ngay_ph_tu_ngay == '')
       && (ngay_ph_den_ngay == null || ngay_ph_den_ngay == '')){
          alert('Vui lòng nhập Ngày tổ chức phát hành hoặc ngày phát hành !');
          $("#ngay_to_chuc_ph_tu_ngay").focus();
          return false;
      }  
      if(ngay_to_chuc_ph_tu_ngay != null && ngay_to_chuc_ph_tu_ngay != '' && 
      (ngay_to_chuc_ph_den_ngay == null || ngay_to_chuc_ph_den_ngay == '')){
          alert('Vui lòng nhập Ngày tổ chức phát hành đến ngày !');
          $("#ngay_to_chuc_ph_den_ngay").focus();
          return false;
      }
      if(ngay_to_chuc_ph_den_ngay != null && ngay_to_chuc_ph_den_ngay != '' && 
      (ngay_to_chuc_ph_tu_ngay == null || ngay_to_chuc_ph_tu_ngay == '')){
          alert('Vui lòng nhập Ngày tổ chức phát hành Từ ngày !');
          $("#ngay_to_chuc_ph_tu_ngay").focus();
          return false;
      }
      if(ngay_ph_tu_ngay != null && ngay_ph_tu_ngay != '' && 
      (ngay_ph_den_ngay == null || ngay_ph_den_ngay == '')){
          alert('Vui lòng nhập Ngày phát hành đến ngày !');
          $("#ngay_ph_den_ngay").focus();
          return false;
      }
      if(ngay_ph_den_ngay != null && ngay_ph_den_ngay != '' && 
      (ngay_ph_tu_ngay == null || ngay_ph_tu_ngay == '')){
          alert('Vui lòng nhập Ngày phát hành Từ ngày !');
          $("#ngay_ph_tu_ngay").focus();
          return false;
      }
      if ((ngay_to_chuc_ph_tu_ngay != null && ngay_to_chuc_ph_tu_ngay != "") && (ngay_to_chuc_ph_den_ngay != null && ngay_to_chuc_ph_den_ngay != "")) {
          var startDate = parseDate(ngay_to_chuc_ph_tu_ngay).getTime();
          var endDate = parseDate(ngay_to_chuc_ph_den_ngay).getTime();
          if (startDate > endDate) {
              alert('Ngày Tổ chức phát hành đến ngày phải lớn hơn Ngày TCPH từ ngày !');
              $("#ngay_to_chuc_ph_den_ngay").focus();
              return false;
          }
      }
      if ((ngay_ph_tu_ngay != null && ngay_ph_tu_ngay != "") && (ngay_ph_den_ngay != null && ngay_ph_den_ngay != "")) {
          var startDate2 = parseDate(ngay_ph_tu_ngay).getTime();
          var endDate2 = parseDate(ngay_ph_den_ngay).getTime();
          if (startDate2 > endDate2) {
              alert('Ngày Phát hành đến ngày phải lớn hơn Ngày Phát hành từ ngày !');
              $("#ngay_ph_den_ngay").focus();
              return false;
          }
      }
      return true;
  }

  function check(type) {
      var f = document.forms[0];
       f.target = '';
      if (validateForm()) {
          f.submit();
      }
  }
  $(function () {
      $("#ngay_to_chuc_ph_den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_to_chuc_ph_tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_ph_den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_ph_tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_to_chuc_ph_den_ngay").on("change",function(){
        var id= $(this).attr("id");
      });
      $("#ngay_to_chuc_ph_tu_ngay").on("change",function(){
        var id= $(this).attr("id");
      });
      $("#ngay_ph_den_ngay").on("change",function(){
        var id= $(this).attr("id");
      });
      $("#ngay_ph_tu_ngay").on("change",function(){
        var id= $(this).attr("id");
      });
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchTTPhiTinPhieuAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>Thanh toán phí đấu thầu phát hành tín phiếu (VNĐ)</strong>
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
        Thông tin tính phí
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y
                                                                TCPH: từ</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="3"
                             styleId="ngay_to_chuc_ph_tu_ngay"
                             property="ngay_to_chuc_ph_tu_ngay" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_thanh_toan_tu_ngay"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </label>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y
                                                                TCPH: đến</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="4"
                             styleId="ngay_to_chuc_ph_den_ngay"
                             property="ngay_to_chuc_ph_den_ngay" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_thanh_toan_den_ngay"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </label>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y
                                                                PH: từ</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="3"
                             styleId="ngay_ph_tu_ngay"
                             property="ngay_ph_tu_ngay" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_ph_tu_ngay"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y
                                                                PH: đến</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="4"
                             styleId="ngay_ph_den_ngay"
                             property="ngay_ph_den_ngay" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_ph_den_ngay"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </label>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="button-group center">
    <button id="tracuu" type="button" onclick="check('find')"
            class="btn btn-default" accesskey="t">
      <span class="sortKey">T</span>ính
    </button>
  </div>
  </div>
 
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="thanhtoanlaigoc.list.result"/>
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th >
          Số lệnh
        </th>
        <th >
          Năm ngân sách
        </th>
        <th>
          Nội dung thanh toán
        </th>
        <th>
          Số tiền phí <br> (đồng)
        </th>        
      </thead>       
      <tbody>
      <logic:notEmpty name="TTLaiGocForm" property="ngay_to_chuc_ph_tu_ngay">
        <logic:empty name="listLenhTraNo">
          <tr>
            <td colspan="16" align="center" style="color:red">
               Không có mã tín phiếu cần thanh toán phí đấu thầu
            </td>
          </tr>
        </logic:empty>
        </logic:notEmpty>
        <logic:empty name="TTLaiGocForm" property="ngay_to_chuc_ph_tu_ngay">
        <logic:notEmpty name="TTLaiGocForm" property="ngay_ph_tu_ngay">
        <logic:empty name="listLenhTraNo">
          <tr>
            <td colspan="16" align="center" style="color:red">
              Không có mã tín phiếu cần thanh toán phí đấu thầu
            </td>
          </tr>
        </logic:empty>
        </logic:notEmpty>
        </logic:empty>
        
        <logic:notEmpty name="listLenhTraNo">
          <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
          <logic:iterate id="objTPCP" name="listLenhTraNo" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td  align="center">
                <%=stt + 1%>
              </td>
              <td >
                <bean:write name="objTPCP" property="so_lenh"/>
              </td>
              <td align="center">
                <bean:write name="objTPCP" property="nam_ns"/>
              </td>
              <td align="center">
                Thanh toán phí đấu thầu tín phiếu qua NHNN
              </td>               
              <td align="right">
                <bean:define id="so_tien_vnd" name="objTPCP"
                             property="so_tien_vnd"/>
                 
                <%=StringUtil.convertNumberToString(so_tien_vnd.toString(),"VND")%>
              </td>
            </tr>

          </logic:iterate>
          <tr>
            <td colspan="5" align="center">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
        
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </div>
 
  <html:hidden property="loai_tien" value="VND"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>