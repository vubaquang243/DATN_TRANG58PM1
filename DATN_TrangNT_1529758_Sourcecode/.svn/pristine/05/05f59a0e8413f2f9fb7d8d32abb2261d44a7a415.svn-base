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
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      jQuery('#ma_kb').focus();
      if (jQuery('#ma_nsd') != null) {
          if (""!=jQuery('#ma_nsd').val()) {
              jQuery('#ma_nsd').focus();
          }
      }
      if (jQuery('#ten_nsd') != null) {
          if (""!=jQuery('#ten_nsd').val()) {
              jQuery('#ten_nsd').focus();
          }
      }
      if (jQuery('#ma_kb') != null) {
          if (""!=jQuery('#ma_kb').val()) {
              jQuery('#ma_kb').focus();
          }
      }
    });
    function goPage(page) {
          var f = document.forms[0];
          f.pageNumber.value = page;
          f.submit();
    }
  
    function check(type) {
        var f = document.forms[0];
        f.target='';
        if (type == 'add') {
            f.abc.value = ""
            f.trang_thai.value = "";
//            f.kb_id.value = "";
//            f.ma_kb.value = "";
            f.ma_nsd.value = "";
            f.ten_nsd.value = "";
//            f.ten_kb.value = "";
            // f.ten_kb[1].value ="";
            f.action = 'QuanLyNSDAddAction.do';
        }
        else if (type == 'close') {
            f.action = 'mainAction.do';
        }
        else if (type == 'find') {
//            if (f.ma_kb.value == null || f.ma_kb.value == "") {
//                f.kb_id.value = "";
//            }
//            if (f.ma_kb.value.length < 4 && f.ma_kb.value.length > 0) {
//                alert('Mã kho bạc là mã 4 kí tự');
//                document.getElementById("ma_kb").focus();
//                return;
//            }
            if (f.ma_nsd.value.length >= 50) {
                alert('mã người sử dụng không quá 50');
                document.getElementById("ma_nsd").focus();
                return;
            }
            if (f.ten_nsd.value.length >= 50) {
                alert('Tên người sử dụng nhập không quá 50');
                document.getElementById("ten_nsd").focus();
  
                return;
            }
  
            f.action = 'QuanLyNSDListAction.do';
        }
        else if (type == 'in') {
          f.action = 'QuanLyNSDPrintAction.do';
          var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
          newDialog = window.open('', '_form', params);      
          f.target='_form';
        }
        f.submit();
    }

    function blockKeySpecial001(e) {
        //      e.keyCode
        var code;
        if (!e)
           // var e = window.event;
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


  <html:form action="/QuanLyNSDListAction.do" method="post">
  <div class="panel-heading border-bottom">
          <h1 class="panel-title"><strong> <fmt:message key="quanlynsd.listnsd.title"/></strong></h1>
  </div>
  <div class="panel panel-default">
  <div class="panel-heading">
    <h2 class="panel-title"><fmt:message key="quanlynsd.listnsd.title.find"/></h2>
  </div>
  <div class="panel-body">
    <div class="form-horizontal">
      <div class="row">
        <div class="col-md-6">
       
              <div class="form-horizontal">
                <!--<div class="form-group">
                  <label for="" class="col-sm-4 control-label"> <fmt:message key="quanlynsd.listnsd.lable.list.makb"/></label>
                  <div class="col-sm-8">
                    <div class="row">
                      <div class="col-xs-4">
                          <html:text property="ma_kb"  styleId="ma_kb" maxlength="4"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           styleClass="form-control" indexed="ma_kb"
                           onblur="getTenKhoBac('ma_kb', 'ten_kb', 'kb_id','addNSDLoadDMKBAction.do');textlostfocus(this);"/>
                      </div>
                      <div class="col-xs-8">
                         <html:text property="ten_kb" readonly="true" styleId="ten_kb"
                           styleClass="form-control" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                          <html:hidden property="kb_id" styleId="kb_id" value=""/>
                      </div>
                    </div>
                  </div>
                </div>-->
                <div class="form-group">
                  <label for="" class="col-sm-4 control-label"><fmt:message key="quanlynsd.listnsd.lable.list.tennv"/></label>
                  <div class="col-sm-8">
                  <html:text property="ten_nsd"
                           onkeypress="return blockKeySpecial001(event);"
                           styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                  </div>
                </div>
              </div>
           
        </div>
        <div class="col-md-6">
         
              <div class="form-horizontal">
              <div class="form-group">
                <label for="" class="col-sm-4 control-label"><fmt:message key="quanlynsd.listnsd.lable.list.manv"/></label>
                <div class="col-sm-8">
                <html:text property="ma_nsd"
                           onkeypress="return blockKeySpecial001(event)"
                           styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </div>
              </div>
              
              </div>
            
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="" class="col-sm-4 control-label"><fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/></label>
                    <div class="col-sm-8">
                    <html:select property="trang_thai" styleId="abc"
                                 styleClass="form-control selectpicker"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;">
                      <html:option value="">
                        <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.all"/>
                      </html:option>
                      <html:option value="01">
                        <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.action"/>
                      </html:option>
                      <html:option value="02">
                        Ngừng hoạt động
                      </html:option>
                      
                    </html:select>
                    </div>
                  </div>
              </div>
        </div>
      </div>
    </div>
  </div>
  <div class="button-group center">
          <button  id="tracuu" type="button" onclick="check('find')" class="btn btn-default"
                  accesskey="t">
            <span class="sortKey">T</span>ra cứu
          </button>
     
          <button id="them" type="button" onclick="check('add')" class="btn btn-default"
                  accesskey="i">
            Th&ecirc;<span class="sortKey">m</span> mới            
          </button>
          <button id="thoat" type="button" onclick="check('close')" class="btn btn-default"
                  accesskey="o">
            Th<span class="sortKey">o</span>&aacute;t
          </button>
</div>
</div>
<div class="panel panel-default">
  <div class="panel-heading">
    <h2 class="panel-title"><fmt:message key="quanlynsd.listnsd.title.findexc"/></h2>
  </div>

    <%-- Hiển thị list KTV--%>
  <table class="table table-bordered">
    <thead>
      <th>
        Domain
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
      <!--<th>
          <fmt:message key="quanlynsd.listnsd.lable.list.makb"/>
      </th>
      <th>
          <fmt:message key="quanlynsd.listnsd.lable.list.matabmis"/>
      </th>
      <th>
          <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.Mac"/>
      </th>-->
      <th>
          <fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>
      </th>
      <th>
          <fmt:message key="quanlynsd.listnsd.lable.list.sua"/>
      </th>
      <!--<th>
          <fmt:message key="quanlynsd.listnsd.lable.list.xoa"/>
      </th>-->
    </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
      <tbody>
        <logic:notEmpty name="lstNSD">
          <logic:iterate id="NSDlist" name="lstNSD" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td>
                <bean:write name="NSDlist" property="domain"/>
              </td>
               <td>
                <bean:write name="NSDlist" property="ma_nsd"/>
              </td>
               <td>
                <bean:write name="NSDlist" property="ten_nsd"/>
              </td>
               <td>
                <bean:write name="NSDlist" property="chuc_danh"/>
              </td>
              <!--
               <td>
                <bean:write name="NSDlist" property="ma"/>
              </td>
               <td>
                <bean:write name="NSDlist" property="ma_tabmis"/>
              </td>
               <td>
                <bean:write name="NSDlist" property="mac_address"/>
              </td>
              -->
               <td>
                <logic:equal value="01" name="NSDlist"
                             property="trang_thai">
                  <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.action"/>
                </logic:equal>
                 
                <logic:equal value="02" name="NSDlist"
                             property="trang_thai">
                  Ngừng hoạt động
                </logic:equal>
                 
            
              </td>
               <td class="icon">
                <logic:equal value="1" name="NSDlist"
                             property="trang_thai">
                  <a href='<html:rewrite page="/QuanLyNSDUpdateAction.do"/>?longid=<bean:write name="NSDlist" property="id"/>
    &makb=<bean:write name="NSDlist" property="kb_id"/>
    &tennsd=<bean:write name="NSDlist" property="ten_nsd"/>
    &chucdanh=<bean:write name="NSDlist" property="chuc_danh"/>
    &trangthai=<bean:write name="NSDlist" property="trang_thai"/>
    &matabims=<bean:write name="NSDlist" property="ma_tabmis"/>
    &macaddress=<bean:write name="NSDlist" property="mac_address"/>
    &tenmaytruycap=<bean:write name="NSDlist" property="ten_may_truycap"/>
    &usermaytruycap=<bean:write name="NSDlist" property="user_may_truycap"/>
    &masnd=<bean:write name="NSDlist" property="ma_nsd"/>
    '>
                   <span class="glyphicon glyphicon-pencil success" aria-hidden="true"></span></a>
                </logic:equal>
                 
                <logic:equal value="2" name="NSDlist"
                             property="trang_thai">
                  <a href='<html:rewrite page="/QuanLyNSDUpdateAction.do"/>?longid=<bean:write name="NSDlist" property="id"/>
    &makb=<bean:write name="NSDlist" property="kb_id"/>
    &tennsd=<bean:write name="NSDlist" property="ten_nsd"/>
    &chucdanh=<bean:write name="NSDlist" property="chuc_danh"/>
    &trangthai=<bean:write name="NSDlist" property="trang_thai"/>
    &matabims=<bean:write name="NSDlist" property="ma_tabmis"/>
    &masnd=<bean:write name="NSDlist" property="ma_nsd"/>
    '>                
                  
                   <span class="glyphicon glyphicon-pencil success" aria-hidden="true"></span></a>
                </logic:equal>
                 
                <logic:equal value="3" name="NSDlist"
                             property="trang_thai">
                  <span class="glyphicon glyphicon-pencil success" aria-hidden="true"></span></a>
                </logic:equal>
              </td>
               <!--<td class="icon">
                <logic:equal value="1" name="NSDlist"
                             property="trang_thai">
                  <a href='<html:rewrite page="/QuanLyNSDDeleteAction.do"/>?longid=<bean:write name="NSDlist" property="id"/>
                &masnd=<bean:write name="NSDlist" property="ma_nsd"/>'
                     onclick="return confirm('Bạn có muốn xóa NSD này ?')">
                     <span class="glyphicon glyphicon-trash warning" aria-hidden="true"></span></a>
                </logic:equal>
                 
                <logic:equal value="2" name="NSDlist"
                             property="trang_thai">
                  <a href='<html:rewrite page="/QuanLyNSDDeleteAction.do"/>?longid=<bean:write name="NSDlist" property="id"/>
                &masnd=<bean:write name="NSDlist" property="ma_nsd"/>'
                     onclick="return confirm('Bạn có muốn xóa NSD này ?')">
                     <span class="glyphicon glyphicon-trash warning" aria-hidden="true"></span></a>
                </logic:equal>
                 
                <logic:equal value="3" name="NSDlist"
                             property="trang_thai"></logic:equal>
              </td>-->
            </tr>
          </logic:iterate>
          <tr>
          <td colspan="10" align="center">
            <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
          </td>
        </tr>
        </logic:notEmpty>
        <logic:empty name="lstNSD">
        <tr>
            <td colspan="10" align="center">
                 <%
    if(request.getAttribute("status") != null){
    String StrStatus = request.getAttribute("status").toString();
    String id = request.getAttribute("nsdID")==null?"":request.getAttribute("nsdID").toString();
    %>
    <font color="Red" dir="ltr">
      <fmt:message key="<%=StrStatus%>">
        <fmt:param>
          <%=id%>
        </fmt:param>
      </fmt:message>
    </font>
    <%}%>
            </td>
        </tr>
        </logic:empty>
        
      </tbody>
               
              <html:hidden property="pageNumber" value="1"/>
            </table>
    <%-- ************************************--%>
 
</div>
</div>

    <%-- hiển thị trạng thái thêm sửa xóa KTV--%>
   
    <%-- ************************************--%>
   
  </html:form>

<%@ include file="/includes/tpcp_bottom.inc"%>
