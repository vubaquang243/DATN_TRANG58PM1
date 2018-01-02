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
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyKeHoachQuy"/>
<script type="text/javascript">
  //  jQuery.noConflict();
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
          f.action = 'QuanLyKeHoachQuyAddAction.do';
          f.submit();
      }
      else if (type == 'close') {
          f.action = 'mainAction.do';
          f.submit();
      }
      else {
          if (validateForm()) {
              f.submit();
          }
      }
  }
  
   function view(id) {
      var f = document.forms[0];
      f.target = '';
      if(id != null && id != ""){
      f.action = 'QuanLyKeHoachQuyViewAction.do?longid=' + id;
      f.submit();
      }
  }

  function ChangeReplace(ngay_het_han, guid) {
      var f = document.forms[0];
      f.target = '';
      var today = new Date().getTime();
      var str = ngay_het_han.split("/");
      var ngayhet_han= new Date(str[2],str[1],str[0]).getTime();

      if (confirm("Bạn có chắc chắn muốn thay thế kế hoạch không?") == true) {
          if (ngayhet_han < today) {
              alert('Kế hoạch không thay thế được');
              f.action = 'ListQuanLyKeHoachQuyAction.do';
          }
          else {
              f.action = 'QuanLyKeHoachQuyChangeAction.do?longid=' + guid;
          }
      }

      f.submit();
  }

  function validateForm() {
//      var nam_ph = $("#nam_ph").val();
//      if (nam_ph == null || nam_ph == "") {
//          alert('Bạn phải nhập năm phát hành !');
//          $("#nam_ph").focus();
//          return false;
//      }
      return true;
  }

  $(function () {
      $("thoi_gian_ph").focus();
      $("#ngay_hieu_luc").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_het_han").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#quy_phat_hanh").keydown(function (event) {
          return onkeydownnodot(event);
      });
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchQuanLyKeHoachQuyAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="qlykehoach.title.QL"/></strong>
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
        <fmt:message key="tpcp.timkiem"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.quy_phat_hanh"/>
              </label>
              <div class="col-sm-8">
                <html:select property="thoi_gian_ph" styleId="thoi_gian_ph"
                             tabindex="1" styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="1">Qu&yacute; I</html:option>
                  <html:option value="2">Qu&yacute; II</html:option>
                  <html:option value="3">Qu&yacute; III</html:option>
                  <html:option value="4">Qu&yacute; IV</html:option>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpcp.nam_phat_hanh"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"  maxlength="4"
                           onkeypress=" return isNumberKey(event)"                      
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="nam_ph" property="nam_ph"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.trang_thai"/>
              </label>
              <div class="col-sm-8">
                <html:select property="trang_thai" styleId="trang_thai"
                             tabindex="3" styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="00">Dự thảo</html:option>
                  <html:option value="01">Chờ duyệt</html:option>
                  <html:option value="02">Đ&atilde; duyệt</html:option>
                  <html:option value="03">Từ chối</html:option>
                  <html:option value="06">Đã thay thế </html:option>
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
       
      <button id="them" type="button" onclick="check('add')"
              class="btn btn-default" accesskey="m">
        Th&ecirc;<span class="sortKey">m</span> mới
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
        <fmt:message key="qlykehoach.result"/>
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered" id="mytable">
      <thead>
        <th>STT</th>
        <th>
          <fmt:message key="tpcp.quy_phat_hanh"/>
        </th>
        <th>
          <fmt:message key="tpcp.tong_khoi_luong"/>
        </th>
        <th>
          <fmt:message key="tpcp.loai_tien"/>
        </th>
        <th>
          <fmt:message key="tpcp.ngay_hieu_luc"/>
        </th>
        <th>
          <fmt:message key="tpcp.ngay_het_han"/>
        </th>
        <th>
          <fmt:message key="tpcp.nguoi_tao"/>
        </th>
        <th>
          <fmt:message key="tpcp.trang_thai"/>
        </th>
        <th>
          <fmt:message key="tpcp.trang_thai.replace"/>
        </th>
        <th>
          <fmt:message key="tpcp.sua"/>
        </th>
        <th>
          <fmt:message key="tpcp.xoa"/>
        </th>
      </thead>
       
      <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
            int stt=(pagingBean.getCurrentPage()-1)*15;
        %>
       
      <tbody>
        <logic:empty name="lstTTDT">
          <tr>
            <td colspan="10" align="center" class="color_red">
              <fmt:message key="qlykehoach.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstTTDT">
          <logic:iterate id="objTPCP" name="lstTTDT" >
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' ondblclick="view('<bean:write name="objTPCP" property="guid"/>')">
              <td class ="center">
                <%=stt+=1%>
              </td>
              <td>
                <logic:equal name="objTPCP" property="thoi_gian_ph" value="1">Qu&yacute; I</logic:equal>
                 
                <logic:equal name="objTPCP" property="thoi_gian_ph" value="2">Qu&yacute;
                                                                              II</logic:equal>
                 
                <logic:equal name="objTPCP" property="thoi_gian_ph" value="3">Qu&yacute;
                                                                              III</logic:equal>
                 
                <logic:equal name="objTPCP" property="thoi_gian_ph" value="4">Qu&yacute;
                                                                              IV</logic:equal>
                                                                              /<bean:write name="objTPCP" property="nam_ph"/>
              </td>
              <td class="right">
                <bean:write name="objTPCP" property="tong_klph"/>
              </td>
               <td class="left">
                <bean:write name="objTPCP" property="loai_tien"/>
              </td>
              <td class="center">
                <bean:write name="objTPCP" property="ngay_hieu_luc"/>
              </td>
              <td class="center">
                <bean:write name="objTPCP" property="ngay_het_han"/>
              </td>
              <td>
                <bean:write name="objTPCP" property="ten_nsd"/>
              </td>
              <td>
                <bean:write name="objTPCP" property="ten_trang_thai"/>
              </td>
              <!--thay thế-->
              <td class="icon">
                <logic:equal value="true" name="objTPCP" property="isReplaced">
                  <a type="button"
                     onclick="ChangeReplace('<bean:write name="objTPCP" property="ngay_het_han"/>','<bean:write name="objTPCP" property="guid"/>')">
                    <span class="glyphicon glyphicon-transfer success"></span></a>
                </logic:equal>
              </td>
              <td class="icon">
                <logic:equal value="03" name="objTPCP" property="trang_thai">
                  <a href='<html:rewrite page="/QuanLyKeHoachQuyUpdateAction.do"/>?longid=<bean:write name="objTPCP" property="guid"/>'>
                    <span class="glyphicon glyphicon-pencil success"></span></a>
                </logic:equal>
                 
                <logic:equal value="00" name="objTPCP" property="trang_thai">
                  <a href='<html:rewrite page="/QuanLyKeHoachQuyUpdateAction.do"/>?longid=<bean:write name="objTPCP" property="guid"/>'>
                    <span class="glyphicon glyphicon-pencil success"></span></a>
                </logic:equal>
              </td>
              <td class="icon">
                <logic:equal value="03" name="objTPCP" property="trang_thai">
                  <a href='<html:rewrite page="/QuanLyKeHoachDeleteAction.do"/>?longid=<bean:write name="objTPCP" property="guid"/>'
                     onclick="return confirm('Bạn có muốn xóa bản ghi này ?')">
                    <span class="glyphicon glyphicon-trash warning"></span></a>
                </logic:equal>
                 
                <logic:equal value="00" name="objTPCP" property="trang_thai">
                  <a href='<html:rewrite page="/QuanLyKeHoachDeleteAction.do"/>?longid=<bean:write name="objTPCP" property="guid"/>'
                     onclick="return confirm('Bạn có muốn xóa bản ghi này ?')">
                    <span class="glyphicon glyphicon-trash warning"></span></a>
                </logic:equal>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
        <tr>
          <td colspan="10" align="center">
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