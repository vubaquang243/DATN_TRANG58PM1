<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPTHONGTINDAUTHAUResource"/>
<script type="text/javascript">
  // jQuery.noConflict();
//  jQuery(document).ready(function () {
//  });

  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'add') {
          f.action = 'AddTTinDThauAction.do';
      }
      if (type == 'close') {
          f.action = 'mainAction.do';
      }
       if(validateForm()){
        f.submit();
      }
  }
   function view(guid) {
      var f = document.forms[0];
      f.target = '';
      if(guid!=null&&guid!=''){
        f.action = 'ViewPheDuyetTTinDThauAction.do?longid='+guid;
        f.submit();
      }
  }

  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
  }
  function validateForm() {
      var tu_ngay = $("#tu_ngay").val();
      var den_ngay = $("#den_ngay").val();
      if (tu_ngay != null && tu_ngay != "") {
          var chk_tu_ngay = validatedate(document.getElementById("tu_ngay"));
          if (!chk_tu_ngay) {
              return false;
          }
      }
      if (den_ngay != null && den_ngay != "") {
          var chk_den_ngay = validatedate(document.getElementById("den_ngay"));
          if (!chk_den_ngay) {
              return false;
          }
          if ((tu_ngay != null && tu_ngay != "") && (den_ngay != null && den_ngay != "")) {
              if ((tu_ngay != null && tu_ngay != "") && (den_ngay != null && den_ngay != "")) {
                  var startDate = parseDate(tu_ngay).getTime();
                  var endDate = parseDate(den_ngay).getTime();
                  if (startDate > endDate) {
                      alert('Ngày tổ chức PH đến ngày phải lớn hơn Ngày tổ chức PH từ ngày !');
                      $("#tu_ngay").focus();
                      return false;
                  }
              }
              
          }
      }
      return true;
  }
function format(elt){
    var currentVal = elt.value;
    if(currentVal.length ==3){
      elt.value = elt.value +"/";
    }

  }
    function validateFloatKeyPress(el, evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        
        if (charCode != 44 && charCode > 31 && (charCode < 48 || charCode > 57)&& charCode !=47) {
            return false;
        }   
        if(charCode ==47){
          var str = el.value;
          if(str.length==1){
            str="0"+"0"+str;
            el.value=str;
          }else if(str.length==2){
            str="0"+str;
            el.value=str;
          }else if(str.length >= 4){
            
            return false;
          }
        }
        return true;
  }
  
  function toUpperCase(){
      var x = document.getElementById("ma_tpcp");
      x.value=x.value.toUpperCase();
    }

    
    
    $(function () {
      $("#dot_dau_thau").focus();
     
      $("#tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchPheDuyetTTinDThauAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        Phê duyệt thông tin đấu thầu</strong>
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
        <fmt:message key="ttindthau.list.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
       
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="ttindthau.dot_dau_thau"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" styleId="dot_dau_thau"
                           maxlength="10" 
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" 
                           onblur="textlostfocus(this);" tabindex="2"
                           property="dot_dau_thau"/>
              </div>
            </div>
          </div>
             <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="ttindthau.ma_tpcp"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control"
                           maxlength="10"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" onkeyup="toUpperCase()"
                           onblur="textlostfocus(this);" tabindex="1"
                           styleId="ma_tpcp" property="ma_tpcp"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Ngày tổ chức PH từ
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="tu_ngay"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="3" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" property="tu_ngay"/>
                   
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
                Ngày tổ chức PH đến
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="den_ngay"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="4" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" property="den_ngay"/>
                   
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
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="ttindthau.trang_thai"/>
              </label>
              <div class="col-sm-8">
                <html:select property="trang_thai" styleId="trang_thai"
                             styleClass="form-control selectpicker" tabindex="5"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="01">Chờ duyệt</html:option>
                  <html:option value="02">Đ&atilde; duyệt</html:option>
                  <html:option value="05"> Đã công bố</html:option>
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
              class="btn btn-default" accesskey="t"><span class="sortKey">T</span>ra cứu
      </button>
      
     <button id="tracuu" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="t">
              Th<span class="sortKey">o</span>át
      </button>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="ttindthau.list.result"/>
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th>STT</th>
        <th>
          <fmt:message key="ttindthau.dot_dau_thau"/>
        </th>
        <th>
          <fmt:message key="ttindthau.dot_bo_sung"/>
        </th>
        <th>
          <fmt:message key="ttindthau.ma_tpcp"/>
        </th>
        <th>
          Khối lượng gọi thầu
        </th>
        <th>
          <fmt:message key="ttindthau.loai_tien"/>
        </th>
        <th>
          <fmt:message key="ttindthau.ky_han"/>
        </th>
        <th>
          Ngày tổ chức PH
        </th>
        <th>
          <fmt:message key="ttindthau.trang_thai"/>
        </th>
        <th class="center">
          Phê duyệt
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
            <td colspan="12" align="center" class="color_red">
              <fmt:message key="ttindthau.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstTTDT">
          <logic:iterate id="objTTDT" name="lstTTDT" >
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' ondblclick="view('<bean:write name="objTTDT" property="guid"/>')">
              <td align="center">
                 <%=stt+=1%>
              </td>
              <td>
                <bean:write name="objTTDT" property="dot_dau_thau"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="dot_bo_sung"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="ma_tpcp"/>
              </td>
              <td align="right">
                <bean:write name="objTTDT" property="khoi_luong_tp"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="loai_tien"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="ky_han"/>
              </td>
              <td align="center">
                <bean:write name="objTTDT" property="ngay_to_chuc_ph"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="ten_trang_thai"/>
              </td>
              <td class="icon">
                <logic:equal value="01" name="objTTDT"
                                property="trang_thai">
                  
                    <a href='<html:rewrite page="/ViewPheDuyetTTinDThauAction.do"/>?longid=<bean:write name="objTTDT" property="guid"/>'>
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
  <%-- ************************************--%>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>