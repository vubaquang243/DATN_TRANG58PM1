<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.BanLeTraiPhieuTwResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
  });

  function check(type) {
     
      var f = document.forms[0];
      f.target = '';
      if (type == 'save') {
        if(confirm("Bạn có muốn trình KQ bán lẻ trái phiếu không ?")){
        
          f.action = 'TrinhKQBanLeTraiPhieuTwAction.do';
          f.submit();
        }
      }
       if(type == 'close'){
          f.action ='ListBanLeTraiPhieuTwAction.do';
          f.submit();
      }
  }

  
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="viewBanLeTraiPhieuTwAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="BanLeTraiPhieuTw.title"/></strong>
    </h1>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="BanLeTraiPhieuTw.view.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.dot_ph"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" property="dot_ph"
                           name="BanLeTraiPhieuTwForm" readonly="true"/>
              </div>
            </div>
          </div>
           <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-5 control-label">
                  Bổ sung đợt
                </label>   
                <div class="col-sm-7">
                  <html:text styleClass="form-control" maxlength="10"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onkeypress="return  validateFloatKeyPress(this, event)" onkeyup="format(this)"
                             onblur="textlostfocus(this);" tabindex="13" readonly="true"
                             styleId="dot_bo_sung" property="dot_bo_sung"/>
                  </div>
              </div>
            </div>
          
        </div>
        <!--Row 2  -->
        <div class="row">
           <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.loai_tien"/>
                 </label>
              <div class="col-sm-7">
                     <html:select property="loai_tien" styleId="loai_tien" name="BanLeTraiPhieuTwForm" disabled="true"
                               onchange="getval(this);"
                               styleClass="form-control selectpicker"
                               tabindex="11"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="">Vui lòng chọn</html:option>
                    <html:option value="USD">USD</html:option>
                    <html:option value="VND" >VND</html:option>
                  </html:select>
              </div>
            </div>
            </div>
            <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ma_tpcp"/>
              </label>
              <div class="col-sm-7">
                <html:select styleClass="form-control selectpicker"
                             name="BanLeTraiPhieuTwForm" disabled="true"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="2"
                             styleId="ma_tpcp" property="ma_tpcp">
                  <logic:notEmpty name="listTPCP">
                    <html:option value="">Vui l&ograve;ng chọn</html:option>
                    <html:optionsCollection name="listTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
          
        </div>
        <!--Row 2a-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ngay_ph"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" property="ngay_ph"
                             name="BanLeTraiPhieuTwForm" readonly="true"/>
                  <label class="input-group-addon" for="ngay_ph"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
                
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ky_han"/>
              </label>
              <div class="col-sm-7">
                <html:select styleClass="form-control selectpicker"
                             name="BanLeTraiPhieuTwForm" disabled="true"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="4"
                             styleId="ky_han" property="ky_han">
                  <logic:notEmpty name="lstKyHan">
                    <html:option value="">Vui l&ograve;ng chọn</html:option>
                    <html:optionsCollection name="lstKyHan" value="id_ky_han"
                                            label="name_ky_han"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
         
        </div>
        <!--Row 3 -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ngay_tt_tien_mua"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control"
                             property="ngay_tt_tien_mua"
                             name="BanLeTraiPhieuTwForm" readonly="true"/>
                  <label class="input-group-addon" for="ngay_tt_tien_mua"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
           <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ngay_dao_han"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" property="ngay_dao_han"
                             name="BanLeTraiPhieuTwForm" readonly="true"/>
                  <label class="input-group-addon" for="ngay_dao_han"> 
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
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.menh_gia"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" property="menh_gia"
                           name="BanLeTraiPhieuTwForm" readonly="true"/>
              </div>
            </div>
          </div>
         
             <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ky_tt_lai"/>
              </label>
              <div class="col-sm-7">
                <html:select property="ky_tt_lai" styleId="ky_tt_lai"
                             name="BanLeTraiPhieuTwForm" disabled="true"
                             onchange="getval(this);"
                             styleClass="form-control selectpicker"
                             tabindex="11"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="0">Không trả lãi định kỳ</html:option>
                    <html:option value="1">Trả lãi định kỳ 12 tháng một lần</html:option>
                    <html:option value="2">Trả lãi định kỳ 6 tháng một lần</html:option>
                </html:select>
              </div>
            </div>
          </div>
        </div>
        <!--Row 5-->
        <div class="row">    
          <!--<div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.trang_thai"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" property="ten_trang_thai"
                           name="BanLeTraiPhieuTwForm" readonly="true"/>
              </div>
            </div>
          </div>-->
            <logic:notEqual value="0" name="BanLeTraiPhieuTwForm"
                           property="ky_tt_lai">
          <div class="col-md-6">
            <div class="form-group" id="div_ngay_tt_lai_1">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ngay_tt_lai_1"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" property="ngay_tt_lai_1"
                             name="BanLeTraiPhieuTwForm" readonly="true"/>
                  <label class="input-group-addon" for="ngay_tt_lai_1"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
          </logic:notEqual>
          <logic:equal value="2" name="BanLeTraiPhieuTwForm"
                           property="ky_tt_lai">
       
          <div class="col-md-6">             
            <div class="form-group" id="div_ngay_tt_lai_2">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ngay_tt_lai_2"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" property="ngay_tt_lai_2"
                             name="BanLeTraiPhieuTwForm" readonly="true"/>
                <label class="input-group-addon" for="ngay_tt_lai_2"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                </label>
                </div>
              </div>
            </div>           
          </div>
          </logic:equal>
        </div>
        <!--Row 6-->
        <div class="row">
        <div class="col-md-6">
            <div class="form-group">
              <label for="lai_suat" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.lai_suat"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" property="lai_suat"
                           name="BanLeTraiPhieuTwForm" readonly="true"/>
              </div>
            </div>
          </div>
          </div>
          <div class="row">
        <logic:notEmpty name="BanLeTraiPhieuTwForm" property="ly_do_tu_choi">
            <div class="col-md-6" id="div_lydotuchoi">
              <div class="form-group">
                <label for="hoten" class="col-sm-5 control-label">
                  <fmt:message key="BanLeTraiPhieuTw.ly_do_tu_choi"/>
                </label>
                <div class="col-sm-7">
                  <html:textarea rows="2" styleClass="form-control" readonly="true"
                                 onkeypress="return blockKeySpecial001(event)"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 onfocus="textfocus(this);" style="width:736px"
                                 onblur="textlostfocus(this);" tabindex="3"
                                 styleId="ly_do_tu_choi"
                                 property="ly_do_tu_choi"/>
                </div>
              </div>
            </div>
        </logic:notEmpty>
        </div>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="BanLeTraiPhieuTw.chitiet.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <table class="table table-bordered" id="addTableTemp">
        <thead>
          <th class="center">STT</th>
          <th class="left">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.dvi_so_huu"/>
          </th>
          <th class="left">
            <fmt:message key="BanLeTraiPhieuTw.ma_dvi_so_huu"/>
          </th>
          <!--
          <th class="left">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.so_dksh"/>
          </th>
          <th class="center">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.ngay_cap_dksh"/>
          </th>
          <th class="left">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.no_cap_dksh"/>
          </th>
          <th class="left">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.loai_hinh"/>
          </th>
          <th class="left">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.quoc_tich"/>
          </th>
          <th class="left">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.dia_chi"/>
          </th>
          -->
          <th class="right">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.sl_dky_mua"/>
          </th>
          <th class="right">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.kl_dky_mua"/>
          </th>
          <th class="right">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.so_tien_tt"/>
          </th>
        </thead>
         
        <tbody>
          <logic:notEmpty name="BanLeTraiPhieuTwForm"
                          property="lstKQBanLe_CTiet">
            <logic:iterate id="objKQBanLeChiTiet" name="BanLeTraiPhieuTwForm"
                           property="lstKQBanLe_CTiet" indexId="stt">
              <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td class="center">
                   <%=stt + 1%>
                </td>
                <td class="left">
                  <bean:write name="objKQBanLeChiTiet" property="dvi_so_huu"/>
                </td>
                <td class="left">
                  <bean:write name="objKQBanLeChiTiet" property="ma_dvi_so_huu"/>
                </td>
         
                <td class="right">
                  <bean:write name="objKQBanLeChiTiet" property="sl_dky_mua"/>
                </td>
                <td class="right">
                  <bean:write name="objKQBanLeChiTiet" property="kl_dky_mua"/>
                </td>
                <td class="right">
                  <bean:write name="objKQBanLeChiTiet" property="so_tien_tt"/>
                </td>
              </tr>
            </logic:iterate>
          </logic:notEmpty>
        </tbody>
         
        <thead>
          <tr>
            <th colspan="3" style="text-align:center;font-weight: bold;">Tổng số</th>
            <th style="text-align:right;font-weight: bold;">
              <bean:write name="BanLeTraiPhieuTwForm" property="so_luong"/>
            </th>
            <th style="text-align:right;font-weight: bold;">
              <bean:write name="BanLeTraiPhieuTwForm" property="khoi_luong"/>
            </th>
            <th style="text-align:right;font-weight: bold;">
              <bean:write name="BanLeTraiPhieuTwForm" property="tong_so_tt"/>
            </th>
          </tr>
        </thead>
      </table>
    </div>
  </div>
  <div class="center">
    <logic:notEqual value="1" name="BanLeTraiPhieuTwForm" property="trang_thai">
      <logic:notEqual value="2" name="BanLeTraiPhieuTwForm"
                      property="trang_thai">
        <button type="button" class="btn btn-default" onclick="check('save')"
                accesskey="r">
         Đệ t<span class="sortKey">r</span>ình
        </button>
      </logic:notEqual>
    </logic:notEqual>
     <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o">
      Th<span class="sortKey">o</span>át
    </button>
    <!--<a class="btn btn-default"
       href='<html:rewrite page="/ListBanLeTraiPhieuTwAction.do"/>'> 
      <span class="sortKey">T</span>ho&aacute;t </a>-->
  </div>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>