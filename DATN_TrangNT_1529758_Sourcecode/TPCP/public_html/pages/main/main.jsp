<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/tpcp_header.inc"%>

<html:form action="/thongkeTongHop.do">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>Trang chủ</strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div style="border:1px #e1e1e1 solid;min-height:200px;display: table;width:100%;">      
      <logic:notEmpty name="news">
          <div style="width:44%;float:left;padding-right:10px;padding-left:10px;padding-bottom:10px;margin-left:20px;margin-top:10px;margin-bottom:10px;margin-right:20px;border:1px solid #e1e1e1;">
            <h2 style="color:#006699;background-image: url(/TTSP/styles/images/navi22.jpg);line-height:25px" align="center">Thông báo</h2>
            <ul>
              <logic:iterate id="new_item" name="news">
                <li style="color:#006699;"><h2><bean:write name="new_item" property="tieu_de"/></h2></li>
                <bean:define id="temp" name="new_item" property="noi_dung" type="java.lang.String"/>
                <dt style="font-size:10pt"><%=temp%></dt>
              </logic:iterate>
            </ul>
          </div>
      </logic:notEmpty>
     
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>