<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPTTTIENMUAResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';   
      
       if (type == 'close') {
          f.action = "mainAction.do";
          f.submit();
      }
      if (type == 'tracuu') {
          f.action = "SearchLapBangKeAction.do";
          f.submit();
      }
      if (type == 'add') {
          f.action = "PreLapBangKeAction.do";
          if(!validateForm()){
             alert('Bạn phải chọn ít nhất 1 đơn vị để lập bảng kê!');
             return false;
          }else{
            f.submit();
          }
      }
      
  }
  function validateForm(){
    var ma_nguoi_so_huu = document.getElementsByName("ma_nguoi_so_huu");
    if(ma_nguoi_so_huu!=null && ma_nguoi_so_huu.length>0){
        for(var i=0;i<ma_nguoi_so_huu.length;i++){
           if(ma_nguoi_so_huu[i].checked){
             return true;
           }
        }
    }
    return false;
  }
  $(function () {
       
  });
  function checkAllBox(obj,objName){
     var arrObj = document.getElementsByName(objName);
     if(arrObj!=null && arrObj.length > 0){
        for(var i =0;i< arrObj.length;i++){
          if(arrObj[i].disabled == false){
            arrObj[i].checked = obj.checked;
          }          
        }
      }
  }
  function checkOnBox(objAll, objName){
      var obj = document.getElementById(objAll);
      var arrObj = document.getElementsByName(objName);
      var checkAll = true;
      if(arrObj!=null && arrObj.length > 0){
        for(var i =0;i< arrObj.length;i++){
          if(arrObj[i].checked ==false){
              checkAll = false;
              break;
          }
        }
      }
      obj.checked = checkAll;
  }
  function getDMTPCP(){
    var f = document.forms[0];
      f.target = '';  
      f.action = "GetMaTPCPAction.do";
      f.submit();
  }
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.current_page.value = page;
      f.submit();
  }
</script>
<html:form action="SearchLapBangKeAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="tttmua.bke.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="app_error">
    <logic:messagesPresent message="true">
      <html:messages id="message" message="true">
        <logic:present name="message">
          <div class="messages">
            <fmt:message key="<%=message%>"/>
          </div>
        </logic:present>
      </html:messages>
    </logic:messagesPresent>
  </div>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label for="hoten" class="col-sm-2 control-label">
                <fmt:message key="tttmua.dot_ph"/><span class="red"> (*)</span>
              </label>
              <div class="col-sm-3">
                <html:text styleClass="form-control" styleId="dot_ph"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="3"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="dot_ph" onchange="getDMTPCP(this);"/>
                <html:hidden property="ptph_tpcp"/>                
              </div>
              <label for="hoten" class="col-sm-2 control-label">
                <fmt:message key="tttmua.ma_tttmua"/> <span class="red"> (*)</span>
              </label>
              <div class="col-sm-3">
                <html:select property="ma_tpcp" styleId="ma_tpcp"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                             <option value="">Vui lòng chọn</option>
                  <logic:notEmpty name="lstDMTPCP">
                    <html:optionsCollection name="lstDMTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select>                            
              </div>              
            </div>
          </div>
        </div>
        <div class="row center">
          <div class="col-md-12 ">
            <div class="form-group">
            <div class="col-sm-12">
                <button type="button" onclick="check('tracuu')"
                        class="btn btn-default" style="width:100" accesskey="t">
                  <span class="sortKey">T</span>ra cứu
                </button>
                 
                <button type="button" class="btn btn-default"
                        onclick="check('close')" accesskey="o" tabindex="7"
                        id="bfind">
                  Th<span class="sortKey">o</span>át
                </button>
              </div>
            </div>
            </div>
            </div>
        <div class="row col-md-12" style="margin-bottom:10px;">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h2 class="panel-title">
                <fmt:message key="tttmua.dvisohuu.ctiet"/>
              </h2>
            </div>
            <div class="panel-body">
              <table class="table table-bordered">
                <thead>
                  <th>STT</th>
                  <th>
                    <fmt:message key="tttmua.dot_ph"/>
                  </th>
                  <th>
                    <fmt:message key="tttmua.ma_tttmua"/>
                  </th>
                  <th>
                    <fmt:message key="tttmua.dvisohuu.name"/>
                  </th>
                  <th>
                    Tổng số tiền phải thanh toán
                    <logic:equal value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (USD)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual>
                  </th>                    
                  <th>
                    <fmt:message key="tttmua.dvisohuu.trangthai"/>
                  </th>
                  <th>
                      <html:hidden name="QLyLapBangKeForm" property="loai_tien"/>
                     <input type="checkbox" name="lapBangKe" id="checkAll" onclick="checkAllBox(this,'ma_nguoi_so_huu');"/>
                  </th>
                </thead>                 
                <tbody>
                  <logic:notEmpty name="lstDviSoHuu">                    
                    <logic:iterate id="objDViSoHuu" name="lstDviSoHuu" indexId="stt">                     
                      <tr class="">
                        <td class="center">
                          <%= stt + 1%>
                        </td>     
                        <td class="left">
                          <bean:write name="objDViSoHuu" property="dot_ph"/>
                        </td>
                        <td class="left">
                          <bean:write name="objDViSoHuu" property="ma_tpcp"/>
                        </td>
                        <td class="left">
                          <bean:write name="objDViSoHuu" property="ten_nguoi_so_huu"/>
                        </td>
                        <td class="right">
                          <%--<bean:write name="objDViSoHuu" property="tong_tien_phai_tt"/>--%>
                          <bean:define id="tong_tien_phai_tt" name="objDViSoHuu" property="tien_tt_mua"/>
                          <%=StringUtil.convertNumberToString(tong_tien_phai_tt.toString(),"VND")%>
                        </td>                                              
                        <td>
                            <logic:equal value="00" name="objDViSoHuu" property="trang_thai_tt">
                                Chưa đủ điều kiện lập bảng kê
                            </logic:equal>
                            <logic:equal value="02" name="objDViSoHuu" property="trang_thai_tt">
                                Đủ điều kiện lập bảng kê
                            </logic:equal>
                            <logic:equal value="03" name="objDViSoHuu" property="trang_thai_tt">
                                Đã lập bảng kê
                            </logic:equal>
                        </td>
                        <td class="icon" width="40px">
                          <logic:equal value="00" name="objDViSoHuu" property="trang_thai_tt">
                                <input type="checkbox" disabled="disabled" onclick="checkOnBox('checkAll','ma_nguoi_so_huu');" name="ma_nguoi_so_huu" value='<bean:write name="objDViSoHuu" property="guid"/>'/>
                            </logic:equal>
                            <logic:equal value="02" name="objDViSoHuu" property="trang_thai_tt">
                                <input type="checkbox" onclick="checkOnBox('checkAll','ma_nguoi_so_huu');" name="ma_nguoi_so_huu" value='<bean:write name="objDViSoHuu" property="guid"/>'/>
                            </logic:equal>
                            <logic:equal value="03" name="objDViSoHuu" property="trang_thai_tt">
                                <input type="checkbox" disabled="disabled" onclick="checkOnBox('checkAll','ma_nguoi_so_huu');" name="ma_nguoi_so_huu" value='<bean:write name="objDViSoHuu" property="guid"/>'/>
                            </logic:equal>
                            
                        </td>
                      </tr>                     
                    </logic:iterate>                     
                  </logic:notEmpty>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      
      </div>
    </div>
  </div>    
  <html:hidden name="QLyLapBangKeForm" property="pageNumber" value="1"/>  
  <div class="center">
    <button type="button" class="btn btn-default" onclick="check('add')"
            accesskey="l" tabindex="7" id="bfind">
      <span class="sortKey">L</span>ập bảng kê
    </button>    
  </div>
</html:form>
<script type="text/javascript">
  
</script>
<%@ include file="/includes/tpcp_bottom.inc"%>