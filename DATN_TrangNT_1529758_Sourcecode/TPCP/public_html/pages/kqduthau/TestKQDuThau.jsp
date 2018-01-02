<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQDUTHAUResource"/>

<%@ include file="/includes/tpcp_header.inc"%>
<script type="text/javascript">
  function submitForm(type) {
      var frm = document.forms[0];
      if (type == 'close') {
          frm.action = "mainAction.do";
      }
      frm.submit();
  }
</script>
<html:form action="/UploadKQDuThauAction.do" method="post"
           enctype="multipart/form-data">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqduthau.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="kqduthau.add.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
       
        <table class="table table-bordered" id="addTableTemp">
            <thead>
            <tr class="header">
              <th rowspan="2" class="center">
                <fmt:message key="kqduthau.stt"/>
              </th>
              <th rowspan="2" class="center">
                <fmt:message key="kqduthau.ten_nha_dau_tu"/>
              </th>
              <th rowspan="2" class="center">
                <fmt:message key="kqduthau.ma_so"/>
              </th>              
              <th rowspan="2" class="center">
                <fmt:message key="kqduthau.kl_dk_khong_ctls"/>
              </th>
              <th colspan="3" class="center">
                <fmt:message key="kqduthau.dauthau_canhtranh"/>
              </th>
              <th rowspan="2" class="center">
                <fmt:message key="kqduthau.kl_cong_don"/>
              </th>
            </tr>
            <tr class="header">
                <th>
                <fmt:message key="kqduthau.lai_suat"/>
              </th>
              <th>
                <fmt:message key="kqduthau.kl_ctls"/>
              </th>
              <th>
                <fmt:message key="kqduthau.kl_cong_don_ctls"/>
              </th>            
            </tr>
            </thead>
          <tbody class='trDanhSachChan'>    
          
        <logic:notEmpty name="QLyKQDuThauForm" property="lstKQDT_CTiet" >          
          <logic:iterate id="objKQDT" name="QLyKQDuThauForm" property="lstKQDT_CTiet" indexId="stt">
            <tr>
              <td class="center">
                <bean:write name="objKQDT" property="stt"/>
              </td>
              <td>
                <html:text name="objKQDT" property="ten_nha_dau_tu" />
              </td>
              <td class="center">
                <html:text name="objKQDT" property="ma_so" />
              </td>
              <td class="right">
                <html:text name="objKQDT" property="kl_dk_khong_ctls" /> 
              </td>
              <td class="right">
                <html:text name="objKQDT" property="lai_suat" />
              </td>
              <td class="right">
                <html:text name="objKQDT" property="kl_dtct" />
              </td>    
              <td class="right">
                <html:text name="objKQDT" property="kl_cong_don_ctls" />
              </td>  
              <td class="right">
                <html:text name="objKQDT" property="kl_cong_don" />
              </td>  
            </tr>
          </logic:iterate>
        </logic:notEmpty>
        
      </tbody>
      
        </table>
      </div>
    </div>
  </div>
  <div class="left">
            <input class="btn_type1" id="themDong" type="button" value="Th&#234;m d&#242;ng" onclick="addRow('addTableTemp')">
            &nbsp;<input class="btn_type1" id="xoaDong" type="button" value="X&#243;a d&#242;ng" onclick="deleteRow('addTableTemp')">
  </div> 
  <div class="center">
    <button type="button" class="btn btn-default" onclick="submitForm('save')"
            accesskey="g" tabindex="7" id="bfind">
      <span class="sortKey">G</span>
      hi
    </button>
     
    <button type="button" class="btn btn-default" onclick="submitForm('close')"
            accesskey="o" tabindex="8">
      Th
      <span class="sortKey">o</span>
      &aacute;t
    </button>
  </div>
</html:form>
<script type="text/javascript">    
    initTable('addTableTemp');
    function setTabIndex(){
      
    }
    function addRow(tableId) {
        addRowTable(tableId);
        assignSTT(tableId);
    }
    function deleteRow(tableId) {
        deleteRowTable(tableId);
        assignSTT(tableId);
    }
</script>
<%@ include file="/includes/tpcp_bottom.inc"%>