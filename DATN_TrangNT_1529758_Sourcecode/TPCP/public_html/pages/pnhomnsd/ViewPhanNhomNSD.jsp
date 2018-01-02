<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyNSDResource"/>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/QuanLyNhomNSDViewAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="qlynhomnsd.listphannhomnsd.title"/></strong>
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
        <fmt:message key="quanlynsd.listnsd.title.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.manhom"/>
              </label>
              <div class="col-sm-6">
                <html:text styleClass="form-control" property="ten_nhom"
                           disabled="true"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="1"/>
                 
                <html:hidden property="ten_nhom"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.loainhom"/>
              </label>
              <div class="col-sm-6">
                <html:text styleClass="form-control" property="loai_nhom"
                           disabled="true"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="1"/>
                 
                <html:hidden property="loai_nhom"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.manv"/>
              </label>
              <div class="col-sm-6">
                <html:text styleClass="form-control" property="ma_nsd"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="1"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>
              </label>
              <div class="col-sm-6">
                <html:select property="trang_thai" styleId="trang_thai"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.all"/>
                  </html:option>
                  <html:option value="01">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.action"/>
                  </html:option>
                  <html:option value="02">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.block"/>
                  </html:option>
                  <html:option value="03">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.disable"/>
                  </html:option>
                </html:select>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="button-group center">
          <button type="button" onclick="check('find')" class="btn btn-default"
                  accesskey="t">
            <span class="sortKey">T</span>ra cứu
          </button>          
          <button type="button" onclick="check('close')" class="btn btn-default"
                  accesskey="o">
            Th<span class="sortKey">o</span>&aacute;t
          </button>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="quanlynsd.listnsd.title.findexc"/>
      </h2>
    </div>
          <table class="table table-bordered">
              <thead>
                <th>
                  <div align="center">Domain</div>
                </th>
                <th>
                    <fmt:message key="quanlynsd.listnsd.lable.list.manv"/>
                </th>
                <th>
                    <fmt:message key="quanlynsd.listnsd.lable.list.tennv"/>
                </th>
                <th>
                    <fmt:message key="quanlynsd.listnsd.lable.list.chucdanh"/>
                </th>
                <th>
                    <fmt:message key="quanlynsd.listnsd.lable.list.makb"/>
                </th>
                <th>
                    <fmt:message key="quanlynsd.listnsd.lable.list.matabmis"/>
                </th>
                
                <th>
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>
                </th>
              </thead>
               
              <%
               com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
              %>
               
              <tbody>
                <logic:notEmpty name="lstNhomNSD">
                  <logic:iterate id="NSDlist" name="lstNhomNSD">
                    <tr>
                      <td align="left" width="3%">
                        <bean:write name="NSDlist" property="domain"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="NSDlist" property="ma_nsd"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="NSDlist" property="ten_nsd"/>
                      </td>
                      <td align="left" width="22%">
                        <bean:write name="NSDlist" property="chuc_danh"/>
                        </td>
                        <td align="left" width="8%">
                        <bean:write name="NSDlist" property="ma"/>
                      </td>
                      
                      <td align="left" width="10%">
                        <bean:write name="NSDlist" property="ma_tabmis"/>
                      </td>
                      
                      <td align="left" width="8%">
                        <logic:equal value="01" name="NSDlist"
                                     property="trang_thai">
                          <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.action"/>
                        </logic:equal>
                         
                        <logic:equal value="02" name="NSDlist"
                                     property="trang_thai">
                          <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.block"/>
                        </logic:equal>
                         
                        <logic:equal value="03" name="NSDlist"
                                     property="trang_thai">
                          <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.disable"/>
                        </logic:equal>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <tr>
                  <td colspan="7">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
                  </td>
                </tr>
              </tbody>
              
              <html:hidden property="pageNumber" value="1"/>
              <html:hidden property="id_nhom" />
            </table>
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
  f.ma_kb.focus();
  document.getElementById("ma_kb").focus();

  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

      function check(type) {
          var f = document.forms[0];
          if (type == 'close') {
              f.action = 'QuanLyNhomNSDListAction.do';
          }
          if (type == 'find') {
              if (f.ma_nsd!=null && f.ma_nsd.value!="" && f.ma_nsd.value.length >= 50) {
                  alert('mã người sử dụng không quá 50');
                  document.getElementById("ma_nsd").focus();
                  return;
              }              
              else {
                  f.action = 'QuanLyNhomNSDViewAction.do';
              }
          }
          f.submit();
      }
</script>