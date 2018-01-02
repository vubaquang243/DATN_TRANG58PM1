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
<fmt:setBundle basename="com.seatech.tp.resource.TPBKECHUYENTIENResource"/>
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
      if (type == 'add') {
          f.action = 'ListLapBangKeAction.do';
      }
      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      f.submit();
  }
  function view(guid) {
      var f = document.forms[0];
      f.target = '';
      if (guid != null && guid != '') {
          f.action = 'ViewLapBangKeAction.do?longid=' + guid;
          f.submit();
      }
  }
  $(function () {
      $("#tu_ngay_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#den_ngay_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ma_tpcp").attr("data-live-search",true);
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchQLyBangKeAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="tpbkechuyentien.title"/></strong>
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
        <fmt:message key="tpbkechuyentien.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpbkechuyentien.dot_ph"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control"                           
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="1"
                           property="dot_ph"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpbkechuyentien.ma_tpcp"/>
              </label>
              <div class="col-sm-8">
                <html:select property="ma_tpcp" styleId="ma_tpcp" tabindex="2"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="lstAllTPCP">
                    <html:optionsCollection name="lstAllTPCP" value="ma_tp"
                                            label="ma_tp"/>
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
                <fmt:message key="tpbkechuyentien.ky_han"/>
              </label>
              <div class="col-sm-8">
                <html:select property="ky_han" styleId="ky_han" tabindex="3"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="listKyHan">
                    <html:optionsCollection name="listKyHan" value="guid"
                                            label="name_ky_han"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpbkechuyentien.ls_danh_nghia"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control"                           
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onkeyup="formatNum(this);"
                           onblur="textlostfocus(this);" tabindex="4"
                           property="ls_danh_nghia"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpbkechuyentien.ngay_ph"/>
                <fmt:message key="tpbkechuyentien.ngay_ph_from"/>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                        <html:text styleClass="form-control" styleId="tu_ngay_ph"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="5"
                           property="tu_ngay_ph"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpbkechuyentien.ngay_ph_to"/>
              </label>
              <div class="col-sm-8">                  
                    <div class="input-group date">
                        <html:text styleClass="form-control" styleId="den_ngay_ph"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="6"
                           property="den_ngay_ph"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpbkechuyentien.trang_thai"/>
              </label>
              <div class="col-sm-8">
              <html:select property="trang_thai" styleId="trang_thai" tabindex="7"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="00">Dự thảo</html:option>
                  <html:option value="01">Chờ duyệt</html:option>
                  <html:option value="02">Đã duyệt</html:option>
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
       <button id="tracuu" type="button" onclick="check('add')"
              class="btn btn-default" accesskey="l">
        <span class="sortKey">L</span>ập bảng kê
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
        <fmt:message key="tpbkechuyentien.result"/>
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th class="center">
          <fmt:message key="tpbkechuyentien.so_bke"/>
        </th>
        <th class="center">
          <fmt:message key="tpbkechuyentien.dot_ph"/>
        </th>
        <th class="center">
          <fmt:message key="tpbkechuyentien.ma_tpcp"/>
        </th>
        <th class="center">
          <fmt:message key="tpbkechuyentien.ngay_dt"/>
        </th>
        <th class="center">
          <fmt:message key="tpbkechuyentien.ngay_ph"/>
        </th>
        <th class="center">
          <fmt:message key="tpbkechuyentien.ky_han"/>
        </th>
        <th class="center">
          Lãi suất danh nghĩa <br/> (%/năm)
        </th>
        <th class="center">
          <fmt:message key="tpbkechuyentien.trang_thai"/>
        </th>       
        <th class="center">
          <fmt:message key="tpbkechuyentien.sua"/>
        </th>
        <th class="center">
          <fmt:message key="tpbkechuyentien.xoa"/>
        </th>
      </thead>
       
      <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
       
      <tbody>
        <logic:empty name="lstBKeXNhan">
          <tr>
            <td colspan="12" align="center" class="color_red">
              <fmt:message key="tpbkechuyentien.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstBKeXNhan">
          <logic:iterate id="objBKe" name="lstBKeXNhan" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' ondblclick="view('<bean:write name="objBKe" property="guid"/>');">
              <td align="center">
                <%=rowBegin + stt + 1%>
              </td>
              <td>
                <bean:write name="objBKe" property="so_bke"/>
              </td>
              <td>
                <bean:write name="objBKe" property="dot_ph"/>
              </td>
              <td>
                <bean:write name="objBKe" property="ma_tpcp"/>
              </td>
              <td align="center">
                <logic:equal value="TD" name="objBKe" property="phuong_thuc_ph"> 
                <bean:write name="objBKe" property="ngay_dt"/>
                </logic:equal>
                <logic:equal value="TPKB" name="objBKe" property="phuong_thuc_ph"> 
                <bean:write name="objBKe" property="ngay_dt"/>
                </logic:equal>
              </td>
              <td align="center">
                <bean:write name="objBKe" property="ngay_ph"/>
              </td>
              <td>
                <bean:write name="objBKe" property="name_ky_han"/>
              </td>
              <td align="right">
                <logic:present name="objBKe" property="ls_danh_nghia">
                <bean:define id="tong_tien_phai_tt" name="objBKe"
                             property="ls_danh_nghia"/>
                 
                <%=StringUtil.convertNumberToString(tong_tien_phai_tt.toString(),"VND")%>
                </logic:present>
              </td>
              <td>
                <bean:write name="objBKe" property="ten_trang_thai"/>
              </td>              
              <td class="icon">
                <logic:equal value="00" name="objBKe" property="trang_thai">
                  <a href='<html:rewrite page="/UpdateLapBangKeAction.do"/>?longid=<bean:write name="objBKe" property="guid"/>'>
                    <span class="glyphicon glyphicon-pencil success"></span></a>
                </logic:equal>
                 <logic:equal value="03" name="objBKe" property="trang_thai">
                  <a href='<html:rewrite page="/UpdateLapBangKeAction.do"/>?longid=<bean:write name="objBKe" property="guid"/>'>
                    <span class="glyphicon glyphicon-pencil success"></span></a>
                </logic:equal>
              </td>
              <td class="icon">
                <logic:equal value="00" name="objBKe" property="trang_thai">
                  <a href='<html:rewrite page="/DeleteLapBangKeAction.do"/>?longid=<bean:write name="objBKe" property="guid"/>'
                     onclick="return confirm('Bạn có muốn xóa bản ghi này ?')">
                    <span class="glyphicon glyphicon-trash warning"></span></a>
                </logic:equal>
                 <logic:equal value="03" name="objBKe" property="trang_thai">
                  <a href='<html:rewrite page="/DeleteLapBangKeAction.do"/>?longid=<bean:write name="objBKe" property="guid"/>'
                     onclick="return confirm('Bạn có muốn xóa bản ghi này ?')">
                    <span class="glyphicon glyphicon-trash warning"></span></a>
                </logic:equal>
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
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </div>
  <%-- ************************************--%>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>