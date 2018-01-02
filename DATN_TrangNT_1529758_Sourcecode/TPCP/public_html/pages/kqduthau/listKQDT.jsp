<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQDUTHAUResource"/>
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
          f.action = 'PreUploadKQDuThauAction.do';
          f.submit();
      }
      if (type == 'close') {
          f.action = 'mainAction.do';
          f.submit();
      }
      if(type == 'find'){
        f.action = 'SearchKQduthauAction.do';
        if (validateForm()) {
            f.submit();
        }
      }
      
  };
  function view(id) {
      var f = document.forms[0];
      f.target = '';
      if(id != null && id != ""){
      f.action = 'ViewKetQuaDThauAction.do?longid=' + id;
      f.submit();
      }
  }
  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
  }

  function validateForm() {
      var ngay_phat_hanh_tu_ngay = $("#ngay_to_chuc_ph_tu_ngay").val();
      var ngay_phat_hanh_den_ngay = $("#ngay_to_chuc_ph_den_ngay").val();
      if ((ngay_phat_hanh_tu_ngay != null || ngay_phat_hanh_tu_ngay != "") && (ngay_phat_hanh_den_ngay != null || ngay_phat_hanh_den_ngay != "")) {
          if (ngay_phat_hanh_tu_ngay != "") {
              var checkDate_TuNgay = validatedate(document.getElementById("ngay_to_chuc_ph_tu_ngay"));
              if (!checkDate_TuNgay) {
                  return false;
              }
          }
            if ( ngay_phat_hanh_den_ngay != "") {
              var checkDate_DenNgay = validatedate(document.getElementById("ngay_to_chuc_ph_den_ngay"));
              if (!checkDate_DenNgay) {
                  return false;
              }
          }
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
      $("#dot_dt").focus();
      $("#ngay_to_chuc_ph_tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_to_chuc_ph_den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
  });
</script>
<html:form action="/SearchKQduthauAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqduthau.title"/></strong>
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
        <fmt:message key="kqduthau.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqduthau.dot_dt"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" styleId="dot_dt"
                           maxlength="8"
                           onkeypress="return validateFloatKeyPress(this,event);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" onkeyup="format(this)" 
                           onblur="textlostfocus(this);" tabindex="1"
                           property="dot_dt"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqduthau.ma_tpcp"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onkeypress="return
                     blockKeySpecial001(event)" onkeydown="if(event.keyCode==13)
                     event.keyCode=9;" onfocus="textfocus(this);" maxlength="10"
                           onblur="textlostfocus(this);upperCase(this);" tabindex="2"
                           property="ma_tpcp"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y tổ
                                                                chức PH: từ</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control"
                             onkeypress="return blockKeySpecial001(event)"
                             maxlength="10" onkeyup="doFormat(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="3"
                             styleId="ngay_to_chuc_ph_tu_ngay"
                             property="ngay_to_chuc_ph_tu_ngay"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y tổ
                                                                chức PH: đến</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control"
                             onkeypress="return blockKeySpecial001(event)"
                             maxlength="10" onkeyup="doFormat(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="4"
                             styleId="ngay_to_chuc_ph_den_ngay"
                             property="ngay_to_chuc_ph_den_ngay"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Trạng th&aacute;i</label>
              <div class="col-sm-8">
                <html:select property="trang_thai" styleId="trang_thai"
                             tabindex="5" styleClass="form-control selectpicker"
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
       
      <button id="them" type="button" onclick="check('add')"
              class="btn btn-default" accesskey="p">
        Tải tệ<span class="sortKey">p</span> tin
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
        <fmt:message key="kqduthau.ttct"/>
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th class="center">
          <fmt:message key="kqduthau.dot_dt"/>
        </th>
        <th class="center">
          <fmt:message key="kqduthau.ma_tpcp"/>
        </th>
        <th class="center">
          <fmt:message key="kqduthau.ngay_to_chuc_ph"/>
        </th>
        <th class="center">
          <fmt:message key="kqduthau.ngay_ph"/>
        </th>
        <th class="center">
          Kỳ hạn
        </th>
        <th class="center">
          <fmt:message key="kqduthau.kl_goi_thau"/>
        </th>
        <th class="center">
          Loại tiền
        </th>
        <th class="center">
          <fmt:message key="kqduthau.trang_thai"/>
        </th>
        <th class="center">
          <fmt:message key="kqduthau.sua"/>
        </th>
        <th class="center">
          <fmt:message key="kqduthau.xoa"/>
        </th>
      </thead>
       
      <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
        %>
       
      <tbody>
        <logic:empty name="listKQDT">
          <tr>
            <td colspan="11" align="center" class="color_red">Kh&ocirc;ng c&oacute;
                                                              dữ liệu</td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="listKQDT">
          <logic:iterate id="objTTDT" name="listKQDT" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' ondblclick="view('<bean:write name="objTTDT" property="guid"/>')">
              <td align="center">
                <%=stt + 1%>
              </td>
              <td class="left">
                <bean:write name="objTTDT" property="dot_dt"/>
              </td>
              <td class="left">
                <bean:write name="objTTDT" property="ma_tpcp"/>
              </td>
              <td class="center">
                <bean:write name="objTTDT" property="ngay_to_chuc_ph"/>
              </td>
              <td class="center">
                <bean:write name="objTTDT" property="ngay_ph"/>
              </td>
              <td class="left">
                <bean:write name="objTTDT" property="ky_han"/>
              </td>
              <td class="right">
                <bean:write name="objTTDT" property="kl_goi_thau"/>
              </td>
              <td class="center">
                <bean:write name="objTTDT" property="loai_tien"/>
              </td>
              <td class="left">
                <bean:write name="objTTDT" property="ten_trang_thai"/>
              </td>
              <!--Sửa-->
              <td class="icon">
                <logic:notEqual value="1" name="objTTDT" property="trang_thai">
                  <logic:notEqual value="2" name="objTTDT"
                                  property="trang_thai">
                    <a href='<html:rewrite page="/UpdateKetQuaDThauAction.do"/>?longid=<bean:write name="objTTDT" property="guid"/>'>
                      <span class="glyphicon glyphicon-pencil success"></span></a>
                  </logic:notEqual>
                </logic:notEqual>
              </td>
              <td class="icon">
                <logic:notEqual value="1" name="objTTDT" property="trang_thai">
                  <logic:notEqual value="2" name="objTTDT"
                                  property="trang_thai">
                    <a href='<html:rewrite page="/DeleteKQDuThauAction.do"/>?longid=<bean:write name="objTTDT" property="guid"/>'
                       onclick="return confirm('Bạn có muốn xóa bản ghi này ?')">
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
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>