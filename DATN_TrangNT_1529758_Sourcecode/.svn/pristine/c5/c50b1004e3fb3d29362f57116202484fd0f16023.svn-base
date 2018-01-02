<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.NhomNSDResoure"/>
<script type="text/javascript">
  //document.getElementById("ma_kb").focus();
  jQuery.noConflict();

  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function check(type) {
      var f = document.forms[0];
      if (type == 'add') {
          f.action = 'QuanLyNhomNSDAddAction.do';
      }
      else if (type == 'print') {
          f.action = 'QuanLyNhomNSDPrintAction.do';
          newDialog = window.open('about:blank', "_form");
          f.target = '_form';
      }
      else if (type == 'close') {
          f.action = 'mainAction.do';
      }

      f.submit();
  }

  function blockKeySpecial001(e) {
      //      e.keyCode
      var code;
      if (!e)
          var e = window.event;
      if (e.keyCode)
          code = e.keyCode;
      else if (e.which)
          code = e.which;
      var character = String.fromCharCode(code);
      var pattern = /[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?\\]/;
      if (pattern.match(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/QuanLyNhomNSDListAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="qlynhomnsd.listphannhomnsd.title"/></strong>
    </h1>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="qlynhomnsd.listphannhomnsd.title.danhsachnhom"/>
      </h2>
    </div>
    <table class="table table-bordered">
      <thead>
        <th>
          <div align="center">
            <fmt:message key="qlynhomnsd.listphannhomnsd.lable.manhom"/>
          </div>
        </th>
        <th>
          <div align="center">
            <fmt:message key="qlynhomnsd.listphannhomnsd.lable.tennhom"/>
          </div>
        </th>
        <th>
          <div align="center">
            <fmt:message key="qlynhomnsd.listphannhomnsd.lable.loainhom"/>
          </div>
        </th>
       
        <th>
          <div align="center">
            <fmt:message key="qlynhomnsd.listphannhomnsd.lable.sua"/>
          </div>
        </th>
        <th>
          <div align="center">
            <fmt:message key="qlynhomnsd.listphannhomnsd.lable.xoa"/>
          </div>
        </th>
      </thead>
       
      <tbody>
        <logic:empty name="lstNhomNSD">
          <tr>
            <td colspan="9" align="center" class="color_red">
              Không tìm thấy bản ghi
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstNhomNSD">
          <logic:iterate id="nhomNSDlist" name="lstNhomNSD" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td align="center">
                <bean:write name="nhomNSDlist" property="id"/>
              </td>
              <td align="left" >
                <bean:write name="nhomNSDlist" property="ten_nhom"/>
              </td>
              <td align="left" >
                <bean:write name="nhomNSDlist" property="loai_nhom"/>
              </td>
          
              <td class="icon" style="width:20px">
                <a href='<html:rewrite page="/QuanLyNhomNSDUpdateAction.do"/>?longid=<bean:write name="nhomNSDlist" property="id"/>
            &loainhom=<bean:write name="nhomNSDlist" property="loai_nhom"/>
            &tennhom=<bean:write name="nhomNSDlist" property="ten_nhom"/>
            '>
            <span class="glyphicon glyphicon-pencil success"></span>
                 </a>
              </td>
              <td class="icon" style="width:20px;">
                <a href='<html:rewrite page="/QuanLyNhomNSDDeleteAction.do"/>?longid=<bean:write name="nhomNSDlist" property="id"/>'
                   onclick="return confirm('Bạn có muốn xóa nhóm NSD này ?')">
                    <span class="glyphicon glyphicon-trash warning"></span>
                  </a>
              </td>

              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
      </tbody>
    </table>
  </div>
  <div class="center">
    <button type="button" onclick="check('add')" class="btn btn-default"
            accesskey="m">
      Th&ecirc;m
      <span class="sortKey">m</span>ới
    </button>
     
    <!--<button type="button" onclick="check('print')" class="btn btn-default"
            accesskey="i">
      <span class="sortKey">I</span>n
    </button>-->
     
    <button type="button" onclick="check('close')" class="btn btn-default"
            accesskey="o">
      Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <html:hidden property="trangthai"/>
  <%-- ************************************--%>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>