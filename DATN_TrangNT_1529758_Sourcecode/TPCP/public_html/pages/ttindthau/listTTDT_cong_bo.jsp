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
          f.action = 'AddTTinDThauAction.do';
      }
      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      f.submit();
  }
   $(function () {
        $("#tu_ngay").datepicker({dateFormat: "dd/mm/yy"});
        $("#den_ngay").datepicker({dateFormat: "dd/mm/yy"});  
    });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchTTinDThauAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="ttindthau.title"/></strong>
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
              <html:text styleClass="form-control"
                         
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="1"
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
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="1"
                         property="ma_tpcp"/>
            </div>
          </div>
        </div>
      </div>
      
      <div class="row">
      <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-4 control-label">
              Ngày tổ chức PH từ ngày
            </label>
            <div class="col-sm-8">
              <html:text styleClass="form-control"
                         onkeypress="return blockKeySpecial001(event)"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="1"
                         styleId="tu_ngay" property="tu_ngay"/>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-4 control-label">
              Ngày tổ chức PH đến ngày
            </label>
            <div class="col-sm-8">
              <html:text styleClass="form-control"
                         onkeypress="return blockKeySpecial001(event)"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="1"
                        styleId="den_ngay"  property="den_ngay"/>
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
          khối lượng gọi thầu
        </th>
        <th>
          <fmt:message key="ttindthau.loai_tien"/>
        </th>
        <th>
          <fmt:message key="ttindthau.ky_han"/>
        </th>
        <th>
          <fmt:message key="ttindthau.ngay_to_chuc_ph"/>
        </th>
        <th>
          <fmt:message key="ttindthau.trang_thai"/>
        </th>
        <th>
          <fmt:message key="ttindthau.cong_bo"/>
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
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
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
              <td>
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
                <logic:equal name="objTTDT" property="trang_thai" value="2">
                    Đang chờ duyệt
                </logic:equal>
                <logic:equal name="objTTDT" property="trang_thai" value="3">
                    Đã duyệt
                </logic:equal>
                <logic:equal name="objTTDT" property="trang_thai" value="4">
                    Bị từ chối
                </logic:equal>
              </td>
              <td class="icon">
                  <a href='<html:rewrite page="/ViewCongBoTTinDThauAction.do"/>?longid=<bean:write name="objTTDT" property="guid"/>'> 
                    <span class="glyphicon glyphicon-search success"></span>
                   </a>
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