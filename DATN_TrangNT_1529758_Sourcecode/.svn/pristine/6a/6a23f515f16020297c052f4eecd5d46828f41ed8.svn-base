<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.HDBanTinPhieu"/>
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
      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      else {
          f.action = 'SearchHDBanTinPhieuPheDuyetAction.do';
      }

      f.submit();
  }
  
  $(function () {
      $("#tu_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#den_ngay").datepicker( {
          dateFormat : "dd/mm/yy"
      });
       $("#kl_tp").keyup(function (event) {
          formatNum(event.target);
      });
      $("#ma_tp").focus();
  
      
    });
      
      $("#tu_ngay").on("change",function(){
         var id= $(this).attr("id");
      })
     $("#den_ngay").on("change",function(){
       var id=$(this).attr("id");
     })
   function dbclick(guid){
          var f= document.forms[0];
          f.target='';
        if(guid!=null){
          f.action='ViewHDBanTinPhieuPheDuyetAction.do?longid='+guid;
          f.submit();
        }
   }
   function number(evt) {
      var theEvent = evt || window.event;
      var key = theEvent.keyCode || theEvent.which;
      key = String.fromCharCode( key );
      var regex = /[0-9]|\,/;
      if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
      }
    }
  function UpperCase(x){
    x.value= x.value.toUpperCase();
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/ListHDBanTinPhieuAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="hdbantinphieu.phe_duyet_title"/>
      </strong>
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
        <fmt:message key="hdbantinphieu.list.find"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="hdbantinphieu.ma_tp"/>
              </label>
              <div class="col-sm-8">
                   <html:text property="ma_tp" styleId="ma_tp"
                             styleClass="form-control selectpicker" tabindex="6"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" onkeyup="UpperCase(this)">
               
                </html:text>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="hdbantinphieu.so_hd"/>
              </label>
              <div class="col-sm-8">
                <html:text property="so_hd" styleId="so_hd"
                            styleClass="form-control selectpicker" tabindex="6"
                            onkeydown="if(event.keyCode==13) event.keyCode=9;" onkeyup="UpperCase(this)">
                 
                </html:text>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="hdbantinphieu.ngay_ph"/>
                <fmt:message key="hdbantinphieu.tu"/>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="tu_ngay"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="3" onfocus="textfocus(this);" onkeyup="doFormat(event)"
                             onblur="textlostfocus(this);" property="tu_ngay"/>
                   
                  <label class="input-group-addon" for="tu_ngay"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </label>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="hdbantinphieu.den"/>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="den_ngay"
                             style="width:268px;"; onkeyup="doFormat(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="4" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" property="den_ngay"/>
                   
                  <label class="input-group-addon" for="den_ngay" style="width: 33px; height: 25px;"> 
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
                <fmt:message key="hdbantinphieu.trang_thai"/>
              </label>
              <div class="col-sm-8">
                <html:select property="trang_thai" styleId="trang_thai"
                             styleClass="form-control selectpicker" 
                             tabindex="5"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">                          
                  <html:option value="">Vui lòng chọn</html:option>
                  <html:option value="01">Chờ duyệt</html:option>
                  
                  <html:option value="02">Đ&atilde; duyệt</html:option>
                  <html:option value="03">Từ chối</html:option>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="hdbantinphieu.lai_suat"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control"
                           maxlength="5"
                           
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onkeypress="number(event)"
                           onblur="textlostfocus(this);" tabindex="1"
                           styleId="lai_suat" property="lai_suat"/>
                           <!--onkeyup="blockKeySpace()"-->
              </div>
            </div>
          </div>
          
        </div>
         <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label" style="white-space: nowrap;">
                <fmt:message key="hdbantinphieu.kl_tp"/>                
              </label>
              
              <div class="col-sm-8">
               <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="3"
                           styleId="kl_tp" property="kl_tp"/>
              </div>
            </div>
          </div>
         <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="hdbantinphieu.ky_han"/>
              </label>
              <div class="col-sm-8">
                <html:select property="ky_han" styleId="ky_han"
                             styleClass="form-control selectpicker" tabindex="6"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="listKyHan">
                    <html:optionsCollection name="listKyHan" value="guid"
                                            label="name_ky_han"/>
                  </logic:notEmpty>
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
       
      <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>át
       
      </button>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="hdbantinphieu.list.result"/>
      </h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th class="center">
          <fmt:message key="hdbantinphieu.so_hd"/>
        </th>
        <th class="center">
          <fmt:message key="hdbantinphieu.ma_tp"/>
        </th>
        <th class="center">
          <fmt:message key="hdbantinphieu.ky_han"/>
        </th>
        <th class="right">
          <fmt:message key="hdbantinphieu.kl_tp"/>
        </th>
        <th class="center">
          <fmt:message key="hdbantinphieu.lai_suat"/>
        </th>
        <th class="center">
          <fmt:message key="hdbantinphieu.ngay_ph"/>
        </th>
        <th class="center">
          <fmt:message key="hdbantinphieu.trang_thai"/>
        </th>
        <th class="center">
          <fmt:message key="hdbantinphieu.pheduyet"/>
        </th>
      </thead>
       
      <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
            int stt=(pagingBean.getCurrentPage()-1)*15;
        %>
       
      <tbody>
        <logic:empty name="listHDTinPhieu">
          <tr>
            <td colspan="12" align="center" class="color_red">
              <fmt:message key="hdbantinphieu.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="listHDTinPhieu">
          <logic:iterate id="objTTDT" name="listHDTinPhieu" >
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'
                ondblclick="dbclick('<bean:write name="objTTDT" property="guid" />')"  >
              <td align="center">
                 <%=stt+=1%>
              </td>
              <td>
                <bean:write name="objTTDT" property="so_hd"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="ma_tp"/>
              </td>
              <td align="left">
                <bean:write name="objTTDT" property="ky_han"/>
              </td>
              <td align="right">
                <bean:write name="objTTDT" property="kl_tp"/>
              </td>
              <td align="right">
                <bean:write name="objTTDT" property="lai_suat"/>
              </td>
              <td align="center">
                <bean:write name="objTTDT" property="ngay_ph"/>
              </td>
              <td>
                <bean:write name="objTTDT" property="ten_trang_thai"/>
              </td>
              <td class="icon">
                <logic:equal name="objTTDT" property="trang_thai" value="01">
                  <a href='<html:rewrite page="/ViewHDBanTinPhieuPheDuyetAction.do"/>?longid=<bean:write name="objTTDT" property="guid"/>'>
                     <span class="glyphicon glyphicon glyphicon-check"></span></a>
                </logic:equal>
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
  <%-- ************************************--%>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>