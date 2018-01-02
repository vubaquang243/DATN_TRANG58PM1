<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQPHTINPHIEUResource"/>
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
      if (type == 'find') {
          f.action = 'SearchKQPhatHanhTinPhieuPDAction.do';
      }
      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      if (validateForm()) {
          f.submit();
      }

  }
  function validateFloatKeyPress(el, evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        var number = el.value.split(',');
        if (charCode != 44 && charCode > 31 && (charCode < 48 || charCode > 57)&& charCode !=47) {
            return false;
        }
        if(charCode ==47){
          var str = el.value;
          if(str.length==1){
            str="0"+"0"+str;
            document.getElementById("dot_ph").value=str;
          }else {
            str="0"+str;
            document.getElementById("dot_ph").value=str;
          }
        }
        return true;
  }
  function UpperCase() {
      var x = document.getElementById("ma_tpcp");
      x.value = x.value.toUpperCase();
  }

  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
  }

  function validateForm() {
      var ngay_phat_hanh_tu_ngay = $("#ngay_phat_hanh_tu_ngay").val();
      var ngay_phat_hanh_den_ngay = $("#ngay_phat_hanh_den_ngay").val();
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

  $("#ngay_phat_hanh_tu_ngay").on("change", function () {
      var id = $(this).attr("id");
  })
  $("#ngay_phat_hanh_den_ngay").on("change", function () {
      var id = $(this).attr("id");
  })
  $(function () {
      $("#dot_dt").focus();
      $("#ngay_phat_hanh_tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_phat_hanh_den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
    
  });
function format(elt){
    var currentVal = elt.value;
    if(currentVal.length ==3){
      elt.value = elt.value +"/";
    }
  }
  function validateFloatKeyPress(el, evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        var number = el.value.split(',');
        if (charCode != 44 && charCode > 31 && (charCode < 48 || charCode > 57)&& charCode !=47) {
            return false;
        }
        if(charCode ==47){
          var str = el.value;
          if(str.length==1){
            str="0"+"0"+str;
            el.value=str;
          }else if(str.length==2) {
            str="0"+str;
            el.value=str;
          }
        }
        return true;
  }
  function view(guid) {
      var f = document.forms[0];
      f.target = '';
      if (guid != null) {
          f.action = "ViewKQPHTinPhieuPDAction.do?longid=" + guid;
          f.submit();
      }
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchKQPhatHanhTinPhieuPDAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqphathanh.phe_duyet_title"/></strong>
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
        <fmt:message key="kqphathanh.list.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqphathanh.dot_ph"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);" onkeyup="format(this)"
                           styleId="dot_dt" onblur="textlostfocus(this);" onkeypress="return validateFloatKeyPress(this,event);"
                           maxlength="10" tabindex="1" property="dot_ph"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqphathanh.ma_tpcp"/>
              </label>
              <div class="col-sm-8">
               <html:text  property="ma_tpcp" styleId="ma_tpcp"
                             styleClass="form-control selectpicker" tabindex="3"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" onkeyup="UpperCase()" maxlength="10">    
                </html:text>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqphathanh.list.tungay"/>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="3"
                             styleId="ngay_phat_hanh_tu_ngay"
                             property="ngay_phat_hanh_tu_ngay" maxlength="10"/>
                   
                  <label for="ngay_phat_hanh_tu_ngay" class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqphathanh.list.denngay"/>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="4"
                             styleId="ngay_phat_hanh_den_ngay"
                             property="ngay_phat_hanh_den_ngay" maxlength="10"/>
                   
                  <label for="ngay_phat_hanh_den_ngay"
                         class="input-group-addon">
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
                <fmt:message key="kqphathanh.trang_thai"/>
              </label>
              <div class="col-sm-8">
                <html:select property="trang_thai" styleId="trang_thai"
                             tabindex="5" styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
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
    <%-- ************************************--%>
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
        <fmt:message key="kqphathanh.list.result"/>
      </h2>
    </div>
    <%-- Hiển thị list--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th class="center">
          <fmt:message key="kqphathanh.dot_ph"/>
        </th>
        <th class="center">
          <fmt:message key="kqphathanh.ma_tpcp"/>
        </th>
        <th class="center">
          <fmt:message key="kqphathanh.ky_han"/>
        </th>
        <th class="center">
          <fmt:message key="kqphathanh.ngay_ph"/>
        </th>
        <th class="center">
          <fmt:message key="kqphathanh.ngay_dao_han"/>
        </th>
        <th class="center">
          Khối lượng trúng thầu
        </th>
        <th class="left" >
          <fmt:message key="kqphathanh.loai_tien" />
        </th>
        <th class="center">
          <fmt:message key="kqphathanh.trang_thai"/>
        </th>
        <th class="center">
          <fmt:message key="kqphathanh.pheduyet"/>
        </th>
      </thead>
       
      <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
            int stt=(pagingBean.getCurrentPage()-1)*15;
        %>
       
      <tbody>
        <logic:empty name="listKQphathanh">
          <tr>
            <td colspan="13" align="center" class="color_red">
              <fmt:message key="kqphathanh.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="listKQphathanh">
          <logic:iterate id="objKQphathanh" name="listKQphathanh" >
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'
                ondblclick="view('<bean:write name="objKQphathanh" property="guid"/>')">
              <td class="center">
                 <%=stt+=1%>
              </td>
              <td class="left">
                <bean:write name="objKQphathanh" property="dot_ph"/>
              </td>
              <td class="left">
                <bean:write name="objKQphathanh" property="ma_tpcp"/>
              </td>
              <td class="left">
                <bean:write name="objKQphathanh" property="ky_han"/>
              </td>
              <td class="right">
                <bean:write name="objKQphathanh" property="ngay_ph"/>
              </td>
              <td class="right">
                <bean:write name="objKQphathanh" property="ngay_dao_han"/>
              </td>
              <td class="center">
                <bean:write name="objKQphathanh" property="tong_kl_trung_thau"/>
              </td>
              <td class="left">
                <bean:write name="objKQphathanh" property="loai_tien"/>
              </td>
              <td class="left">
                <bean:write name="objKQphathanh" property="ten_trang_thai"/>
              </td>
              <td class="icon">
                <logic:equal name="objKQphathanh" property="trang_thai"
                             value="01">
                  <a href='<html:rewrite page="/ViewKQPHTinPhieuPDAction.do"/>?longid=<bean:write name="objKQphathanh" property="guid"/>'>
                    <span class="glyphicon glyphicon glyphicon-check"></span></a>
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
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>