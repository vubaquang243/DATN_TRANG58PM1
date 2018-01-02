<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyTPResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
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
          f.action = 'AddDVSHListAction.do';
          f.submit();
      }
      if (type == 'find') {
          f.action = 'SearchDVSHListAction.do';
          f.submit();
      }
      if (type == 'close') {
          f.action = 'mainAction.do';
          f.submit();
      }    
  }
  
  $(function () {
      $("#ma_chu_so_huu").focus();     
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/SearchDVSHListAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>Quản lý danh mục Đơn vị sở hữu</strong>
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
        Thông tin tìm kiếm
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Mã chủ sở hữu
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           maxlength="10" styleId="ma_chu_so_huu"
                           onblur="textlostfocus(this);upperCase(this);" tabindex="1"
                           property="ma_chu_so_huu"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Tên đơn vị sở hữu
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" onfocus="textfocus(this);"
                           maxlength="300" styleId="ten_dvi_so_huu"
                           onblur="textlostfocus(this);upperCase(this);" tabindex="2"
                           property="ten_dvi_so_huu"/>
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
        Kết quả tìm kiếm
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th>
          Mã TV
        </th>
        <th>
          Mã chủ sở hữu
        </th>
        <th>
          Tên đơn vị sở hữu
        </th>
        <th>
          Loại hình
        </th>
        <th>
          Cơ cấu
        </th>
        <th>
           Bán lẻ
        </th>
        <th>
          Sửa
        </th>
        <th>
          Xóa
        </th>
      </thead>
       
      <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
            int stt =(pagingBean.getCurrentPage() -1) * 15;
        %>
       
      <tbody>
        <logic:empty name="listDVSH">
          <tr>
            <td colspan="9" align="center" class="color_red">
              Không tìm thấy bản ghi
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="listDVSH">
          <logic:iterate id="objDVSH" name="listDVSH" >
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td class="center">
                <%=stt += 1%>
              </td>
              <td>
                <bean:write name="objDVSH" property="ma_tv"/>
              </td>
              <td>
                <bean:write name="objDVSH" property="ma_chu_so_huu"/>
              </td>
              <td>
                <bean:write name="objDVSH" property="ten_dvi_so_huu"/>
              </td>
              <td>
                <bean:write name="objDVSH" property="loai_hinh"/>
              </td>
              <td>
                <bean:write name="objDVSH" property="co_cau"/>
              </td>
              <td class="icon">
                <logic:equal value="1" name="objDVSH" property="ban_le">
                    Bán lẻ 
                </logic:equal>
              </td>
              <td class="icon">         
                    <a href='<html:rewrite page="/UpdateDVSHListAction.do"/>?longid=<bean:write name="objDVSH" property="guid"/>'>
                      <span class="glyphicon glyphicon-pencil success"></span></a>
              </td>
              <td class="icon">
                    <a href='<html:rewrite page="/DeleteDVSHListAction.do"/>?longid=<bean:write name="objDVSH" property="guid"/>'
                       onclick="return confirm('Bạn có muốn xóa bản ghi này ?')">
                      <span class="glyphicon glyphicon-trash warning"></span></a>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
        <tr>
          <td colspan="9" align="center">
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