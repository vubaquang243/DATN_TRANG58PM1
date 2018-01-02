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
  
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/ListTienTeAction.do" method="post">
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
      </div>
    </div>
    <div class="button-group center">
      <button id="tracuu" type="button" onclick="check('find')"
              class="btn btn-default" accesskey="t">
        <span class="sortKey">T</span>ra cứu
      </button>
       
      <button id="them" type="button" onclick="check('add')"
              class="btn btn-default" accesskey="m">
        Th&ecirc;<span class="sortKey">m</span>
        mới
      </button>
      <a class="btn btn-default" href='<html:rewrite page="/mainAction.do"/>'>
        <span class="sortKey">T</span>hoát</a> 
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
        <th class="center">STT</th>
        <th class="center">
          <fmt:message key="ttindthau.dot_dau_thau"/>
        </th>
        <th class="center">
          <fmt:message key="ttindthau.dot_bo_sung"/>
        </th>
        <th class="center">
          Tên
        </th>
        <th class="center">
          Xóa
        </th>
      </thead>
       
       
      <tbody>
        <logic:empty name="ketqua">
          <tr>
            <td colspan="12" align="center" class="color_red">
              <fmt:message key="ttindthau.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="ketqua">
          <logic:iterate id="objTTDT" name="ketqua" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td align="center">
                <%=stt + 1%>
              </td>
              <td>
                <bean:write name="objTTDT" property="id"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="ma"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="ten"/>
              </td>
              <td>
                <a href='<html:rewrite page="/DeleteTienTeAction.do"/>?longid=<bean:write name="objTTDT" property="id"/>'
                     onclick="return confirm('Bạn có chắc chắn muốn xóa hợp đồng này?')" >
                    <span class="glyphicon glyphicon-trash warning"></span></a>
              </td> 
            </tr>
          </logic:iterate>
        </logic:notEmpty>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </div>
  <%-- ************************************--%>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>