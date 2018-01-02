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
      if (type == 'find') {
          f.action = 'SearchLenhTraNoUsdAction.do';
      }
      f.submit();
  }

  function view(id_lenh) {
      var f = document.forms[0];
      f.action = 'ViewLenhTraNoUsdAction.do?longid=' + id_lenh;
      f.submit();
  }
  $(function () {
      $("#so_lenh").focus();
      $("#ngay_chuyen_tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
       $("#ngay_chuyen_tu_ngay").on("change",function(){
        var id= $(this).attr("id");
      });
      $("#ngay_chuyen_den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
       $("#ngay_chuyen_den_ngay").on("change",function(){
        var id= $(this).attr("id");
      });
      document.getElementById("dvi_nhan").value = '<bean:write name="LenhTraNoForm" property="dvi_nhan"/>';
      $('.selectpicker').selectpicker('refresh');
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/ListLenhTraNoUsdAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>Quản lý thông tin lệnh chi trả nợ trong nước</strong>
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
      <h2 class="panel-title">Th&ocirc;ng tin lệnh chi trả nợ</h2>
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
                           tabindex="1" property="so_lenh"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Mã TPCP
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           styleId="nam_ns" onblur="textlostfocus(this);"
                           tabindex="2" property="ma_tpcp"/>
              </div>
            </div>
          </div>
        </div>
        <!--<div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="thanhtoanlaigoc.dvi_nhan"/>
              </label>
              <div class="col-sm-8">
                <html:select property="dvi_nhan" styleId="dvi_nhan"
                             styleClass="form-control selectpicker" tabindex="3"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onchange="change_ma_tp(this)">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="listDonViTT">
                    <html:optionsCollection name="listDonViTT" value="ma"
                                            label="ten"/>
                  </logic:notEmpty>
                </html:select>
               
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Đợt phát hành 
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           styleId="so_lenh" onblur="textlostfocus(this);"
                           tabindex="4" property="dot_ph"/>
              </div>
            </div>
          </div>
        </div>-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Ngày đến hạn TT từ
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="5"
                             styleId="ngay_chuyen_tu_ngay"
                             property="ngay_chuyen_tu_ngay" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_chuyen_tu_ngay"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </label>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label  class="col-sm-4 control-label">
                Ngày đến hạn TT đến
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="6"
                             styleId="ngay_chuyen_den_ngay"
                             property="ngay_chuyen_den_ngay" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_chuyen_den_ngay"> 
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
              <label for="hoten" class="col-sm-4 control-label">
                Loại tiền
              </label>
              <div class="col-sm-8">
                <html:select property="loai_tien" styleId="loai_tien"
                             styleClass="form-control selectpicker" tabindex="7"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="VND">VND</html:option>
                  <html:option value="USD">USD</html:option>
                </html:select>                
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="thanhtoanlaigoc.trang_thai"/>
              </label>
              <div class="col-sm-8">
                <html:select property="trang_thai" styleId="trang_thai"
                             styleClass="form-control selectpicker" tabindex="8"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="00">Dự thảo</html:option>
                  <html:option value="01">Chờ duyệt</html:option>
                  <html:option value="02">Đ&atilde; duyệt</html:option>
                  <html:option value="03">Từ chối</html:option>
                </html:select>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="button-group center">
      <button id="tracuu" type="button" onclick="check('find')"
              class="btn btn-default" accesskey="t">
        <span class="sortKey">T</span>ra cứu
      </button>
      <a class="btn btn-default" accesskey="o" href='<html:rewrite page="/mainAction.do"/>'>
        Th<span class="sortKey">o</span>át</a> 
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
        <th>
          <fmt:message key="thanhtoanlaigoc.so_lenh"/>
        </th>
        <th>
          <fmt:message key="thanhtoanlaigoc.nam_ns"/>
        </th>
       
        <th>
          Mã TPCP
        </th>
        <th>
          Nội dung thanh toán
        </th>
        <th>
          Ngày đến hạn TT
        </th>
        <th>
          Tổng tiền VND <br>(đồng)
        </th>
        <th>
          Loại tiền
        </th>
        <th>
          <fmt:message key="thanhtoanlaigoc.trang_thai"/>
        </th>
        <th>
          <fmt:message key="thanhtoanlaigoc.edit"/>
        </th>
        <th>
          <fmt:message key="thanhtoanlaigoc.delete"/>
        </th>
      </thead>
       
      <tbody>
        <logic:empty name="listLenhTriTraNo">
          <tr>
            <td colspan="16" align="center">
              <fmt:message key="thanhtoanlaigoc.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="listLenhTriTraNo">
          <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
          <logic:iterate id="objTPCP" name="listLenhTriTraNo" indexId="stt">
              <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'
                  ondblclick="view('<bean:write name="objTPCP" property="guid"/>')">
                <td align="center">
                  <%=rowBegin + stt + 1%>
                </td>
                <td align="center">
                  <bean:write name="objTPCP" property="so_lenh"/>
                </td>
                <td align="center">
                  <bean:write name="objTPCP" property="nam_ns"/>
                </td>
                
                <td align="center">
                  <bean:write name="objTPCP" property="ma_tpcp"/>
                </td>
                <td align="left">
                  <logic:equal value="TT_LAI_LAN1" name="objTPCP" property="loai_thanh_toan">
                        Thanh toán lãi, gốc trái phiếu
                  </logic:equal>
                  <logic:equal value="TT_LAI_LAN2" name="objTPCP" property="loai_thanh_toan">
                        Thanh toán lãi, gốc trái phiếu
                  </logic:equal>
                  <logic:equal value="TT_LAI_GOC" name="objTPCP" property="loai_thanh_toan">
                        Thanh toán lãi, gốc trái phiếu
                  </logic:equal>                  
                  <logic:equal value="GOCTINPHIEU" name="objTPCP" property="loai_thanh_toan">
                        Thanh toán gốc tín phiếu
                  </logic:equal>
                  <logic:equal value="PHITPKB" name="objTPCP" property="loai_thanh_toan">
                        Thanh toán phí đấu thầu tín phiếu
                  </logic:equal>
                  <logic:equal value="PHIHNX" name="objTPCP" property="loai_thanh_toan">
                        Thanh toán phí đấu thầu trái phiếu
                  </logic:equal>
                </td>
                <td align="center">
                  <bean:write name="objTPCP" property="ngay_den_han_tt"/>
                </td>
                <td align="right">
                <logic:present name="objTPCP" property="tong_tien">
                  <bean:define id="tong_tien" name="objTPCP"
                             property="tong_tien"/>
                 
                <%=StringUtil.convertNumberToString(tong_tien.toString(),"VND")%>
                </logic:present>
                </td>
                <td align="center">
                  <bean:write name="objTPCP" property="loai_tien"/>
                </td>
                <td align="left">
                  <bean:write name="objTPCP" property="ten_trang_thai"/>
                </td>
                <td class="icon">                     
                 <logic:notEqual value="01" name="objTPCP" property="trang_thai">
                    <logic:notEqual value="02" name="objTPCP" property="trang_thai">
                      <a href='<html:rewrite page="/ViewLenhTraNoUsdAction.do"/>?longid=<bean:write name="objTPCP" property="guid"/>'>
                    <span class="glyphicon glyphicon-pencil success"></span></a>
                    </logic:notEqual>                  
                </logic:notEqual>
              </td>
                       
              <td class="icon">
                <logic:notEqual value="01" name="objTPCP" property="trang_thai">
                    <logic:notEqual value="02" name="objTPCP" property="trang_thai">
                     <a href='<html:rewrite page="/DeleteLenhTraNoUsdAction.do"/>?longid=<bean:write name="objTPCP" property="guid"/>&loaitt=<bean:write name="objTPCP" property="loai_thanh_toan"/>&solenh=<bean:write name="objTPCP" property="so_lenh"/>'
                     onclick="return confirm('Bạn có chắc chắn muốn xóa lệnh CTN này?')" >
                    <span class="glyphicon glyphicon-trash warning"></span></a>
                    </logic:notEqual>
                </logic:notEqual>
                 
              </td>
              </tr>
          </logic:iterate>
          <tr>
            <td colspan="11" align="center">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%--************************************--%>
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>