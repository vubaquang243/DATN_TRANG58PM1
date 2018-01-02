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
          f.action = 'UpdateLenhTraNoUsdPDAction.do';
          $("#trang_thai").val('02');
          f.submit();
      }
      else if (type == 'tuchoi') {
          f.action = 'UpdateLenhTraNoUsdPDAction.do';
          if($("#ly_do_tu_choi").val() ==''){
            alert('Bạn phải nhập lý do từ chối.');
            $("#ly_do_tu_choi").focus();
            return false;
          }
          $("#trang_thai").val('03');
          f.submit();
      }else if (type == 'thoat') {
          f.action = 'ListLenhTraNoUsdPDAction.do';
          f.submit();
      }
  }  
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/ListLenhTraNoAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">      
      <logic:notEqual value="VND" name="LenhTraNoForm" property="loai_tien">
      <strong>Phê duyệt lệnh chi trả nợ trong nước bằng ngoại tệ</strong>
      </logic:notEqual>
      <logic:equal value="VND" name="LenhTraNoForm" property="loai_tien">
      <strong>Phê duyệt lệnh chi trả nợ trong nước bằng VND</strong>
      </logic:equal>
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
                <html:select property="dvi_nhan" styleId="dvi_nhan" 
                            disabled="true"
                             styleClass="form-control selectpicker" tabindex="3"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="listDonViTT">
                    <html:optionsCollection name="listDonViTT" value="ma"
                                            label="ten"/>
                  </logic:notEmpty>
                </html:select>
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
                <html:text styleClass="form-control" 
                           styleId="nam_ns" 
                           tabindex="3" property="nam_ns" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Tài khoản số</label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" 
                           styleId="nam_ns" 
                           tabindex="3" property="so_tk_nhan" readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Căn cứ
              </label>
              <div class="col-sm-8">
                <html:textarea styleClass="form-control"
                               onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="5"
                               styleId="can_cu" property="can_cu" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Nơi mở tài khoản</label>
              <div class="col-sm-8">
                <html:textarea styleClass="form-control"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="9"
                               styleId="nh_nhan" property="nh_nhan"
                               readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ngày đến hạn thanh toán</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="8"
                             styleId="ngay_den_han_tt" property="ngay_den_han_tt"
                             maxlength="10" readonly="true"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>                
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ngày chuyển tiền</label>
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
        <logic:notEqual value="VND" name="LenhTraNoForm" property="loai_tien">      
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Loại ngoại tệ</label>
              <div class="col-sm-8">
                <html:text styleClass="form-control"
                               onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="9"
                               styleId="loai_tien" property="loai_tien" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Tỷ giá</label>
              <div class="col-sm-8">
                <logic:present name="LenhTraNoForm"
                             property="ty_gia">
                <bean:define id="ty_gia" name="LenhTraNoForm"
                             property="ty_gia"/>
                 
                <%String gia=StringUtil.convertNumberToString(ty_gia.toString(),"VND");%>
                <html:text styleClass="form-control"
                               onfocus="textfocus(this);" value="<%=gia%>"
                               onblur="textlostfocus(this);" tabindex="9"
                               styleId="ty_gia" property="ty_gia" readonly="true"/>
                </logic:present>
              </div>
            </div>
          </div>
          </div>          
          </logic:notEqual>
          <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Lý do từ chối</label>
              <div class="col-sm-8">
              <logic:notEqual value="01" name="LenhTraNoForm" property="trang_thai">    
                <html:textarea styleClass="form-control"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="9"
                               styleId="ly_do_tu_choi" property="ly_do_tu_choi" readonly="true"/>
              </logic:notEqual>
              <logic:equal value="01" name="LenhTraNoForm" property="trang_thai">    
                <html:textarea styleClass="form-control"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="9"
                               styleId="ly_do_tu_choi" property="ly_do_tu_choi"/>
              </logic:equal>    
              </div>
            </div>
          </div>
          </div>
      </div>
    </div>
  </div>
  <html:hidden property="so_tien_ngte" />
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
        <logic:notEqual value="VND" name="LenhTraNoForm" property="loai_tien">     
        <th>Số tiền ngoại tệ</th>
        </logic:notEqual>
        <th>Số tiền VND <br> (đồng)</th>
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
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' >
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
              <logic:notEqual value="VND" name="LenhTraNoForm" property="loai_tien">  
              <td align="right">
                <bean:define id="so_tien_ngte" name="objTPCP"
                             property="so_tien_ngte"/>
                 
                <%=StringUtil.convertNumberToString(so_tien_ngte.toString(),"VND")%>
              </td>
              </logic:notEqual>
              <td align="right">
                <bean:write name="objTPCP" property="so_tien_vnd"/>
              </td>
            </tr>
          </logic:iterate>
          <tr>
              <td colspan="6" class="center"><b>Tổng cộng</b></td>
              <logic:notEqual value="VND" name="LenhTraNoForm" property="loai_tien">  
              <td align="right">
                <bean:define id="tong_tien_usd" name="LenhTraNoForm"
                             property="tong_tien_usd"/>
                 
                <b><%=StringUtil.convertNumberToString(tong_tien_usd.toString(),"VND")%></b>
              </td>
              </logic:notEqual>
              <td class="right">
                  <b><bean:write name="LenhTraNoForm" property="tong_tien"/></b>
              </td>
          </tr>
        </logic:notEmpty>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </div>
  <div class="button-group center">
    <logic:equal value="01" property="trang_thai" name="LenhTraNoForm">
      <button id="tracuu" type="button" onclick="check('pheduyet')"
              class="btn btn-default" accesskey="d">
        <span class="sortKey">D</span>uyệt
      </button>
      <button id="tracuu" type="button" onclick="check('tuchoi')"
              class="btn btn-default" accesskey="c">
        Từ <span class="sortKey">c</span>hối
      </button>
    </logic:equal>
    
    <button id="tracuu" type="button" onclick="check('thoat')"
            class="btn btn-default" accesskey="o">
      Th<span class="sortKey">o</span>át
    </button>
    
  </div>
  <html:hidden property="trang_thai" name="LenhTraNoForm" styleId="trang_thai"/>
  <html:hidden property="guid" name="LenhTraNoForm"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>