<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPTTTIENMUAResource"/>
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
      }
      f.submit();
  }
  $(function () {
      $("#tu_ngay_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#den_ngay_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ma_tpcp").attr("data-live-search", true);
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchTTinTMuaAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>Tra cứu thông tin thanh toán tiền mua</strong>
    </h1>
  </div>
  <div class="app_error">
    <logic:messagesPresent message="true">
      <html:messages id="message" message="true">
        <logic:present name="message">
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
        <fmt:message key="tttmua.find"/>
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
                           styleId="ma_tpcp" onblur="textlostfocus(this);"
                           tabindex="1" property="ma_tpcp"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-4 control-label">Trạng thái</label>
            <div class="col-sm-8">
              <html:select styleClass="form-control selectpicker"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="nop_cham" property="nop_cham">
                <html:option value="">Vui l&ograve;ng chọn</html:option>
                <html:option value="0">Đã thanh toán</html:option>
                <html:option value="1">Chưa thanh toán</html:option>
                <html:option value="2">Nộp chậm</html:option>
              </html:select>
            </div>
          </div>
        </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tttmua.ngay_ph"/>
                <fmt:message key="tttmua.ngay_ph_from"/>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="tu_ngay_ph"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);" onkeyup="doFormat(event)"
                             onblur="textlostfocus(this);" tabindex="5"
                             property="tu_ngay_ph"/>
                   
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
                <fmt:message key="tttmua.ngay_ph_to"/>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="den_ngay_ph"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);" onkeyup="doFormat(event)"
                             onblur="textlostfocus(this);" tabindex="6"
                             property="den_ngay_ph"/>
                   
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
              <label for="hoten" class="col-sm-2 control-label">
                <fmt:message key="tttmua.dvisohuu.name"/>
              </label>
              <div class="col-sm-10">
                <html:text styleClass="form-control" styleId="ma_nguoi_so_huu"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="3"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ma_nguoi_so_huu"/>
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
       
      <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>&aacute;t
      </button>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="tttmua.result"/>
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th>STT</th>
        <th>
          <fmt:message key="tttmua.ngay_ph"/>
        </th>
        <th>
          Mã TPCP
        </th>
        <th>
          <fmt:message key="tttmua.dvisohuu.name"/>
        </th>
        <th>
          Tổng số tiền phải thanh toán
        </th>
        <th>
          Tổng số tiền đã thanh toán
        </th>
        <th>
          Tổng số tiền đã lập BKXN
        </th>  
        <th>
          Đơn vị tính
        </th>  
      </thead>       
      <tbody>
        <logic:empty name="lstDviSoHuu">
          <tr>
            <td colspan="12" align="center" class="color_red">
              <fmt:message key="tttmua.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstDviSoHuu">
        <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
          <logic:iterate id="objDViSoHuu" name="lstDviSoHuu" indexId="stt">
            
            <tr>
              <td class="center">
                <%=rowBegin + stt + 1%>
              </td>
              <td class="left">
                <bean:write name="objDViSoHuu" property="ngay_ph"/>
              </td>
              <td class="left">
                <bean:write name="objDViSoHuu" property="ma_tpcp"/>
              </td>
              <td class="left">
                <bean:write name="objDViSoHuu" property="ten_nguoi_so_huu"/>
              </td>
              <td class="right">
                <%-- <bean:write name="objDViSoHuu"
                     property="tong_tien_phai_tt"/>--%>
                 
                <bean:define id="tong_tien_phai_tt" name="objDViSoHuu"
                             property="tong_tien_phai_tt"/>
                 
                <%=StringUtil.convertNumberToString(tong_tien_phai_tt.toString(),"VND")%>
              </td>
              <td class="right">
                <%-- <bean:write name="objDViSoHuu" property="tong_tien_da_tt"/>--%>
                 
                <bean:define id="tong_tien_da_tt" name="objDViSoHuu"
                             property="tong_tien_da_tt"/>
                 
                <%=StringUtil.convertNumberToString(tong_tien_da_tt.toString(),"VND")%>
              </td>
              <td class="right">
                <%-- <bean:write name="objDViSoHuu"
                     property="tong_tien_da_lap_bke"/>--%>
                 
                <bean:define id="tong_tien_da_lap_bke" name="objDViSoHuu"
                             property="tong_tien_da_lap_bke"/>
                 
                <%=StringUtil.convertNumberToString(tong_tien_da_lap_bke.toString(),"VND")%>
              </td>
              <td class="left">
                <bean:write name="objDViSoHuu" property="loai_tien"/>
              </td>
            </tr>
          </logic:iterate>
          <tr>
            <td colspan="9" align="center">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </div>
  <%-- ************************************--%>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>