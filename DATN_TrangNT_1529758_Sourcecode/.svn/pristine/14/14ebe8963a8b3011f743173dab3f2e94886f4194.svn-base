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
<% 
    List<String> lstAllDotDT = (List<String>)request.getAttribute("lstAllDotDT");
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
      
      if(ngay_thanh_toan_tu_ngay == null || ngay_thanh_toan_tu_ngay == ''){
          alert('Vui lòng nhập Ngày thanh toán từ ngày !');
          $("#ngay_thanh_toan_tu_ngay").focus();
          return false;
      }
      if(ngay_thanh_toan_den_ngay == null || ngay_thanh_toan_den_ngay == ''){
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
          if(parseDate(ngay_thanh_toan_tu_ngay).getFullYear() != parseDate(ngay_thanh_toan_den_ngay).getFullYear()){
              alert('Hệ thống chỉ tính lãi, gốc trong cùng 1 năm!');
              $("#ngay_thanh_toan_tu_ngay").focus();
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
      
      $("#ma_tpcp").focus();
      $("#ngay_thanh_toan_tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_thanh_toan_tu_ngay").on("change",function(){
        var id= $(this).attr("id");
      });
      $("#ngay_thanh_toan_den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_thanh_toan_den_ngay").on("change",function(){
        var id= $(this).attr("id");
      });
      var jsArray = [ 
        <% 
        if(lstAllDotDT!=null){
        for (int i = 0;i < lstAllDotDT.size();i++) {
        %> "<%= lstAllDotDT.get(i) %>" <%= i + 1 < lstAllDotDT.size() ? "," : "" %>  <% 
          }
        }%>];  
      $("#ma_tpcp").autocomplete( {
          source : jsArray
      });
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchTTLaiGocVndAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        Tính lãi, gốc trái phiếu có lãi suất danh nghĩa cố định (VNĐ)</strong>
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
        <fmt:message key="thanhtoanlaigoc.list.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Mã TPCP</label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           styleId="ma_tpcp" onblur="textlostfocus(this);upperCase(this);"
                           tabindex="1" property="ma_tpcp"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-4 control-label">Phương thức phát hành</label>
            <div class="col-sm-8">
              <html:select styleClass="form-control selectpicker"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="phuong_thuc_ph" property="phuong_thuc_ph">
                <html:option value="">Vui l&ograve;ng chọn</html:option>
                <html:option value="TD">Đấu thầu</html:option>
                <html:option value="2">Bảo lãnh</html:option>
                <html:option value="3">Đại lý</html:option>
                <html:option value="TL">Bán lẻ</html:option>
              </html:select>
            </div>
          </div>
        </div>
        </div>
        
        <div class="row"></div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y
                                                                thanh
                                                                to&aacute;n: từ <span class="red">(*)</span></label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="3"
                             styleId="ngay_thanh_toan_tu_ngay"
                             property="ngay_thanh_toan_tu_ngay" maxlength="10"/>
                   
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
                                                                thanh
                                                                to&aacute;n: đến <span class="red">(*)</span></label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="4"
                             styleId="ngay_thanh_toan_den_ngay"
                             property="ngay_thanh_toan_den_ngay" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_thanh_toan_den_ngay"> 
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
      Tí<span class="sortKey">n</span>h
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
        <th colspan="2" class="center">STT</th>
        <th colspan="2">
          <fmt:message key="thanhtoanlaigoc.so_lenh"/>
        </th>
        <th colspan="2">
          <fmt:message key="thanhtoanlaigoc.nam_ns"/>
        </th>        
        <th colspan="2">
          <fmt:message key="thanhtoanlaigoc.ma_tpcp"/>
        </th>
        <th colspan="2">
          Ngày đến hạn TT
        </th>
        <th colspan="2">
          Nội dung thanh toán
        </th>
        <th colspan="2">
          Số tiền
        </th>
      </thead>
       
      <tbody>
      <logic:notEmpty name="TTLaiGocForm" property="ngay_thanh_toan_tu_ngay">
        <logic:empty name="listLenhTraNo">
          <tr>
            <td colspan="16" align="center" style="color:red">
              Không có mã trái phiếu cần thanh toán trong thời gian đã chọn
            </td>
          </tr>
        </logic:empty>
        </logic:notEmpty>
        <logic:notEmpty name="listLenhTraNo">
          <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
          <logic:iterate id="objTPCP" name="listLenhTraNo" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td colspan="2" rowspan="<bean:write name="objTPCP" property="rows"/>" align="center">
                <%=stt + 1%>
              </td>
              <td colspan="2" rowspan="<bean:write name="objTPCP" property="rows"/>" align="center">
                <bean:write name="objTPCP" property="so_lenh"/>
              </td>
              <td colspan="2" rowspan="<bean:write name="objTPCP" property="rows"/>" align="center">
                <bean:write name="objTPCP" property="nam_ns"/>
              </td>              
              <td colspan="2" rowspan="<bean:write name="objTPCP" property="rows"/>" align="center">
                <bean:write name="objTPCP" property="ma_tpcp"/>
              </td>
              <td colspan="2" rowspan="<bean:write name="objTPCP" property="rows"/>" align="center">
                <bean:write name="objTPCP" property="ngay_den_han_tt"/>
              </td>                 
            </tr>
              
            <logic:iterate id="objLenhCT" name="objTPCP" property="list_Lenh_tra_no_ct" indexId="stt">
              
                <tr class="table table-bordered">                  
                  <td class="center" colspan="2"><bean:write name="objLenhCT" property="noi_dung"/></td>
                  <td class="center"  colspan="2"><bean:write name="objLenhCT" property="so_tien_vnd"/></td>
                </tr>
              
              </logic:iterate>
              
<!--           111-->
               
       
            <!--<tr class="header">
              <td class="center">STT</td>
              <td class="center">Loại thanh toán</td>
              <td class="center">Số tiền ngoại tệ</td>
              <td class="center">Số tiền VND</td>
              <td class="center">NDKT</td>
              <td class="center">Chương</td>
              <td class="center">Ngành KT</td>
            </tr>-->
           
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
    <%-- ************************************--%>
  </div>
 
  <html:hidden property="loai_tien" value="VND"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>