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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/MoneyConvert_vn.js"></script>
<% 
    
%>
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
      var ngay_thanh_toan_tu_ngay = $("#ngay_thanh_toan_tu_ngay").val();
      var ngay_thanh_toan_den_ngay = $("#ngay_thanh_toan_den_ngay").val();

      if (ngay_thanh_toan_tu_ngay == null && ngay_thanh_toan_tu_ngay == '') {
          alert('Vui lòng nhập Ngày thanh toán từ ngày !');
          $("#ngay_thanh_toan_tu_ngay").focus();
          return false;
      }
      if (ngay_thanh_toan_den_ngay == null && ngay_thanh_toan_den_ngay == '') {
          alert('Vui lòng nhập Ngày thanh toán đến ngày !');
          $("#ngay_thanh_toan_den_ngay").focus();
          return false;
      }
      if ((ngay_thanh_toan_tu_ngay != null && ngay_thanh_toan_tu_ngay != "") && (ngay_thanh_toan_den_ngay != null && ngay_thanh_toan_den_ngay != "")) {
          var startDate = parseDate(ngay_thanh_toan_tu_ngay).getTime();
          var endDate = parseDate(ngay_thanh_toan_den_ngay).getTime();
          if (startDate > endDate) {
              alert('Ngày thanh toán đến ngày phải lớn hơn Ngày thanh toán từ ngày !');
              $("#ngay_thanh_toan_tu_ngay").focus();
              return false;
          }
      }
      return true;
  }

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'pheduyet') {
          f.action = 'UpdateLenhTraNoPDAction.do';
          $("#trang_thai").val('02');
      }
      else if (type == 'tuchoi') {
          f.action = 'UpdateLenhTraNoPDAction.do';
          $("#trang_thai").val('03');
      }
      f.submit();
  }

  function view(id_lenh) {
      var f = document.forms[0];
      f.action = 'SearchLenhTraNoAction.do'
      f.submit();
  }
  $(function () {
      var trang_thai ='<bean:write name="LenhTraNoForm" property="trang_thai"/>';
      if('02'==trang_thai||'03'==trang_thai){
          $('#ly_do_tu_choi').attr('readonly',true);
      }
      var tong_tien ='<bean:write name="LenhTraNoForm" property="tong_tien"/>';
      $("#tong_tien_chu").val(toEnglishCash(toNumber(tong_tien)).toUpperCase());
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/ListLenhTraNoAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>Phê duyệt lệnh chi trả nợ trong nước bằng VNĐ</strong>
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
      <h2 class="panel-title">Th&ocirc;ng tin chung</h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="thanhtoanlaigoc.so_lenh"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           styleId="so_lenh" onblur="textlostfocus(this);"
                           tabindex="1" property="so_lenh" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="thanhtoanlaigoc.dvi_nhan"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onkeyup="doFormat(event)"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="3"
                           styleId="dvi_nhan" property="dvi_nhan"
                           readonly="true"/>
              </div>
            </div>
          </div>
          
        </div>
        <div class="row">
        <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="thanhtoanlaigoc.nam_ns"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           styleId="nam_ns" onblur="textlostfocus(this);"
                           tabindex="3" property="nam_ns" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">T&agrave;i khoản
                                                                số</label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           styleId="so_tk_nhan" onblur="textlostfocus(this);"
                           tabindex="4" property="so_tk_nhan" readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Căn cứ</label>
              <div class="col-sm-8">
                <html:textarea styleClass="form-control"
                               onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="5"
                               styleId="can_cu" property="can_cu"
                               readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Nơi mở
                                                                t&agrave;i khoản</label>
              <div class="col-sm-8">
                <html:textarea styleClass="form-control"
                               onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="6"
                               styleId="nh_nhan" property="nh_nhan"
                               readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y đến
                                                                hạn TT</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="7"
                             styleId="ngay_den_han_tt"
                             property="ngay_den_han_tt" maxlength="10"
                             readonly="true"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y
                                                                chuyển tiền</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="8"
                             styleId="ngay_chuyen" property="ngay_chuyen"
                             maxlength="10" readonly="true"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
        </div>
          
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label for="hoten" class="col-sm-2 control-label">Lý do từ chối</label>
              <div class="col-sm-10">
                <html:textarea styleClass="form-control" onfocus="textfocus(this);"
                           styleId="ly_do_tu_choi" onblur="textlostfocus(this);"
                           tabindex="7" property="ly_do_tu_choi"/>
              </div>
            </div>
          </div>
        </div>
        
          <html:hidden styleId="tong_tien" property="tong_tien"/>
          <html:hidden styleId="tong_tien_chu" property="tong_tien_chu"/>
        
      </div>
     </div>
  </div>
  <html:hidden styleId="so_tien_vnd" property="so_tien_vnd" />
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">Th&ocirc;ng tin chi tiết</h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">Nội dung thanh to&aacute;n</th>
        <th>M&atilde; NDKT</th>
        <th>M&atilde; chương</th>
        <th>M&atilde; ng&agrave;nh KT</th>
        <th>M&atilde; CTMT, DA v&agrave; HTCT</th>
        <th>M&atilde; nguồn NSNN</th>
        <th>Số tiền (đồng)</th>
      </thead>
       
      <tbody>
        <logic:empty property="list_Lenh_tra_no_ct" name="LenhTraNoForm">
          <tr>
            <td colspan="16" align="center">
              <fmt:message key="thanhtoanlaigoc.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty property="list_Lenh_tra_no_ct" name="LenhTraNoForm">
          <logic:iterate id="objTPCP" property="list_Lenh_tra_no_ct"
                         name="LenhTraNoForm" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td>
                <bean:write name="objTPCP" property="noi_dung"/>
              </td>
              <td align="center">
                <bean:write name="objTPCP" property="ndkt"/>
              </td>
              <td align="center">
                <bean:write name="objTPCP" property="chuong"/>
              </td>
              <td align="center">
                <bean:write name="objTPCP" property="nganh_kt"/>
              </td>
              <td align="center">
                <bean:write name="objTPCP" property="ctmt"/>
              </td>
              <td align="center">
                <bean:write name="objTPCP" property="nguon"/>
              </td>
              <td align="center">
                <bean:write name="objTPCP" property="so_tien_vnd"/>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </div>
  <div class="button-group center">
    <logic:equal value="01" property="trang_thai" name="LenhTraNoForm">
      <button id="tracuu" type="button" onclick="check('pheduyet')"
              class="btn btn-default" accesskey="t">
        <span class="sortKey">P</span>
        hê duyệt
      </button>
      <button id="tracuu" type="button" onclick="check('tuchoi')"
              class="btn btn-default" accesskey="t">
        <span class="sortKey">T</span>
        ừ chối
      </button>
    </logic:equal>
     
    <a class="btn btn-default"  accesskey="o"
       href='<html:rewrite page="/ListLenhTraNoPDAction.do"/>'> 
       Th<span class="sortKey">o</span>át</a>
  </div>
  <html:hidden property="trang_thai" name="LenhTraNoForm" styleId="trang_thai"/>
  <html:hidden property="guid" name="LenhTraNoForm"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>