<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.ThamSoHTResource"/>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function()
      {  
        jQuery('#ten_ts').focus();
      });
 function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }
  function check(type) {
      var f = document.forms[0];
      f.target='';
     if (type == 'close') {
          f.action = 'mainAction.do';
      }else if (type == 'find') {
          f.action = 'ThamSoHTLSuListAction.do';
      }else if (type == 'print') {
          f.action = 'ThamSoHTLSuPrintAction.do';
          var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
          newDialog = window.open('', '_form', params);             
          f.target='_form';
      }
      
     f.submit();
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/ThamSoHTLSuListAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="QuanLyTSHT.listQLTSHT.title"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr> 
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
         <fmt:message key="QuanLyTSHT.listQLTSHT.title.dieukientimkiem"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.mats"/></td>
              <td width="15%">
                <html:text property="ten_ts" 
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"
                           size="30%"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.motats"/></td>
              <td width="15%" align="left" bordercolor="#e1e1e1">
                <html:text property="mo_ta" 
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"
                           size="30%"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              </tr>
                <tr>
                <td   align="right">Từ ngày sửa</td>
                <td   align="left" valign="middle">
                <html:text property="tu_ngay" styleId="tu_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:30%"
                           value='<%=request.getParameter("sysdate")%>'/>    
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "tu_ngay", 
                      ifFormat : "%d/%m/%Y", 
                      button : "ngay"
                  });
                </script>
                
              </td>
               <td   align="right">Đến ngày sửa</td>
                <td  align="left" valign="middle">
                <html:text property="den_ngay" styleId="den_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:30%"
                           value='<%=request.getParameter("sysdate")%>'/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay1" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "den_ngay", 
                      ifFormat : "%d/%m/%Y", 
                      button : "ngay1"
                  });
                </script>
               
              </td>
                </tr>
            
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- hiển thị trạng thái thêm sửa xóa KTV--%>
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
    <%-- ************************************--%>
    <%-- ************************************--%>
    <%-- 2 nút tra cứu thoát--%>
    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
      <tr>
        <td align="center">
        <span>
          <button type="button" onclick="check('find')" class="ButtonCommon"
                  accesskey="t">
            <span class="sortKey">T</span>ra cứu
          </button>
          </span>
          <span>
          <button type="button" onclick="check('print')" class="ButtonCommon"
                  accesskey="t">
            <span class="sortKey">I</span>n
          </button> 
          </span>
          <span>
          <button type="button" onclick="check('close')" class="ButtonCommon"
                  accesskey="o">
            Th<span class="sortKey">o</span>át
          </button>
          </span>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <%-- ************************************--%>
    <%-- Hiển thị list KTV--%>
    <table border="2" align="center" width="100%"
           style="BORDER-COLLAPSE: collapse">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="QuanLyTSHT.listQLTSHT.title.ketquatimkiem"/>
            </font>
          </td>
        </tr>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.mats"/> </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.giatricu"/></div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.giatrimoi"/></div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">shkb</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.mansd"/></div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.mota"/></div>
                </th>
                 <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.thoigian"/></div>
                </th>
              </thead>
                 <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="listTS">
                  <logic:iterate id="TSlist" name="listTS" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="left" width="25%">
                        <bean:write name="TSlist" property="ten_ts"/>
                      </td>
                       <td align="center" width="5%">
                        <bean:write name="TSlist" property="giatri_ts"/>
                      </td>
                      <td align="center" width="5%">
                        <bean:write name="TSlist" property="giatri_ts_moi"/>
                      </td>
                      <td align="left" width="20%">
                        <bean:write name="TSlist" property="shkb"/>
                      </td>
                      <td align="center" width="10%">
                        <bean:write name="TSlist" property="ma_nsd"/>
                      </td>
                      <td align="left" width="35%">
                        <bean:write name="TSlist" property="mo_ta"/>
                      </td>
                      <td align="center" width="15%">
                        <bean:write name="TSlist" property="ngay_cap_nhat"/>
                      </td>
                      </tr>
                  </logic:iterate>
                </logic:notEmpty>
               <tr>
                  <td colspan="7">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                  </td>
                </tr>
              </tbody>
               
              <html:hidden property="pageNumber" value="1"/>
               
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <%-- ************************************--%>
  </html:form>
</div>
<%@ include file="/includes/tpcp_bottom.inc"%>
