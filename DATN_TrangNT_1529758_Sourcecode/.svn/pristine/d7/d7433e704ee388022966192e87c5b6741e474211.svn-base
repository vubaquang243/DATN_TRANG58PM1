<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<%@ page import="java.math.BigDecimal"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPBKECHUYENTIENResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      if (type == 'pduyet') {
          f.action = 'PDuyetBangKeAction.do';
          f.submit();
      }
      if (type == 'tchoi') {
          var ly_do_tu_choi = $("#ly_do_tu_choi").val();
          if(ly_do_tu_choi==null || ly_do_tu_choi==""){
            alert('Bạn phải nhập lý do từ chối.');
            $("#ly_do_tu_choi").focus();
             return false;
          }
          f.action = 'TuChoiPDuyetBangKeAction.do';
          f.submit();
      }
      if (type == 'close') {
          f.action = 'ListPDuyetBangKeAction.do';
          f.submit();
      }
  }

  

  $(function () {      
  });
</script> 
<html:form action="SearchPDuyetBangKeAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>Phê duyệt bảng k&ecirc; x&aacute;c nhận chuyển tiền</strong>
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
  <%String actionName = (String)request.getAttribute("actionName");%>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">Th&ocirc;ng tin chung</h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <logic:equal value="TD" name="QLyLapBangKeForm" property="ptph_tpcp">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.dot_ph"/>
              </label>
              <div class="col-sm-7">
                <html:hidden property="so_bke"/>
                 <html:hidden property="ptph_tpcp"/>
                <html:text styleClass="form-control" readonly="true"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           tabindex="2" styleId="dot_ph" property="dot_ph"
                           maxlength="10"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ma_tpcp"/>
              </label>
              <div class="col-sm-7">
                <html:hidden property="ma_tpcp"/>
                 
                <html:select property="ma_tpcp" styleId="ma_tpcp"
                             disabled="true"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onchange="change_ma_tp(this)">
                  <logic:notEmpty name="lstAllTPCP">
                    <html:optionsCollection name="lstAllTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ten_tpcp"/>
              </label>
              <div class="col-sm-7">
                <logic:equal value="VSD" name="QLyLapBangKeForm"
                             property="noi_cap">
                  <input type="text" class="form-control"
                         value="Trái phiếu Chính phủ" readonly="readonly"/>
                </logic:equal>
                 
                <logic:equal value="KBNN" name="QLyLapBangKeForm"
                             property="noi_cap">
                  <input type="text" class="form-control"
                         value="Trái phiếu kho bạc" readonly="readonly"/>
                </logic:equal>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ky_han"/>
              </label>
              <div class="col-sm-7">
                <html:hidden property="ky_han"/>
                <html:hidden property="ngay_tt_lai_1"/>
                <html:hidden property="ngay_tt_lai_2"/>
                <html:hidden property="ngay_tt_tien_mua"/>
                 <html:hidden property="loai_tien"/>
                <html:select property="ky_han" styleId="ky_han" disabled="true"
                             styleClass="form-control selectpicker"
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
        <!--Row 2 a -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ngay_dt"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_dt"
                             readonly="true"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_dt"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.kl_trung_thau"/> 
                <logic:equal value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (USD)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="kl_trung_thau" styleId="kl_trung_thau"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 3-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ngay_ph"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_ph"
                             readonly="true"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_ph"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ls_trung_thau"/> (%/năm)
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_trung_thau" styleId="ls_trung_thau"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 4-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ngay_tt"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tt"
                             readonly="true"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_tt"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ls_danh_nghia"/> (%/năm)
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_danh_nghia" styleId="ls_danh_nghia"/>
              </div>
            </div>
          </div>
        </div>
         </logic:equal>
         <logic:equal value="TL" name="QLyLapBangKeForm" property="ptph_tpcp">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
               Ngày phát hành
              </label>
              <div class="col-sm-7">
                <html:hidden property="so_bke"/>
                 <html:hidden property="ptph_tpcp"/>
                 <html:hidden property="dot_ph"/>
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_ph"
                             readonly="true"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_ph"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>              
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ma_tpcp"/>
              </label>
              <div class="col-sm-7">
                <html:hidden property="ma_tpcp"/>
                 
                <html:select property="ma_tpcp" styleId="ma_tpcp"
                             disabled="true"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onchange="change_ma_tp(this)">
                  <logic:notEmpty name="lstAllTPCP">
                    <html:optionsCollection name="lstAllTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
        </div>
         <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Ngày phát hành bổ sung
              </label>
              <div class="col-sm-7">
                <input type="text" class="form-control"
                         value="" readonly="readonly"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ky_han"/>
              </label>
              <div class="col-sm-7">
                <html:hidden property="ky_han"/>
                <html:hidden property="ngay_tt_lai_1"/>
                <html:hidden property="ngay_tt_lai_2"/>
                <html:hidden property="ngay_tt_tien_mua"/>
                 <html:hidden property="loai_tien"/>
                <html:select property="ky_han" styleId="ky_han" disabled="true"
                             styleClass="form-control selectpicker"
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
        <!--Row 2 a -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Tên trái phiếu
              </label>
              <div class="col-sm-7">                
              <logic:equal value="VSD" name="QLyLapBangKeForm"
                             property="noi_cap">
                  <input type="text" class="form-control"
                         value="Trái phiếu Chính phủ" readonly="readonly"/>
                </logic:equal>
                 
                <logic:equal value="KBNN" name="QLyLapBangKeForm"
                             property="noi_cap">
                  <input type="text" class="form-control"
                         value="Trái phiếu kho bạc" readonly="readonly"/>
                </logic:equal>
            </div>
          </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Lãi suất phát hành (%/năm)
              </label>
              <div class="col-sm-7">
                <html:hidden property="kl_trung_thau"/>
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_trung_thau" styleId="ls_trung_thau"/>
              </div>
            </div>
          </div>
        </div> 
        <!--Row 4-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ngay_tt"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tt"
                             readonly="true"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_tt"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ls_danh_nghia"/> (%/năm)
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_danh_nghia" styleId="ls_danh_nghia"/>
              </div>
            </div>
          </div>
        </div>
        </logic:equal>
         <logic:equal value="TPKB" name="QLyLapBangKeForm" property="ptph_tpcp">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
               Đợt phát hành
              </label>
              <div class="col-sm-7">
                <html:hidden property="so_bke"/>
                 <html:hidden property="ptph_tpcp"/>
                <html:hidden property="kl_trung_thau"/>
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="dot_ph" styleId="dot_ph"/>
              </div>                         
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Mã tín phiếu
              </label>
              <div class="col-sm-7">
                <html:hidden property="ma_tpcp"/>                 
                <html:select property="ma_tpcp" styleId="ma_tpcp"
                             disabled="true"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onchange="change_ma_tp(this)">
                  <logic:notEmpty name="lstAllTPCP">
                    <html:optionsCollection name="lstAllTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
        </div>
         <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Ngày đấu thầu
              </label>
                <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tt"
                             readonly="true"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_dt"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ky_han"/>
              </label>
              <div class="col-sm-7">
                <html:hidden property="ky_han"/>
                <html:hidden property="ngay_tt_lai_1"/>
                <html:hidden property="ngay_tt_lai_2"/>
                <html:hidden property="ngay_tt_tien_mua"/>
                 <html:hidden property="loai_tien"/>                
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_ph"
                             readonly="true"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_ph"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2 a -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Khối lượng trúng thầu
              </label>
              <div class="col-sm-7">                
              <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="kl_trung_thau" styleId="kl_trung_thau"/>
            </div>
          </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Kỳ hạn tín phiếu
              </label>
              <div class="col-sm-7">   
                <html:select property="ky_han" styleId="ky_han" disabled="true"
                             styleClass="form-control selectpicker"
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
        <!--Row 4-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Lãi suất trúng thầu (%/năm)
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_trung_thau" styleId="ls_trung_thau"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ls_danh_nghia"/> (%/năm)
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_danh_nghia" styleId="ls_danh_nghia"/>
              </div>
            </div>
          </div>
        </div>
        </logic:equal>      
        <logic:equal value="PHTT" name="QLyLapBangKeForm" property="ptph_tpcp">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
               Số hợp đồng
              </label>
              <div class="col-sm-7">
                <html:hidden property="so_bke"/>
                 <html:hidden property="ptph_tpcp"/>
                <html:hidden property="kl_trung_thau"/>
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="dot_ph" styleId="dot_ph"/>
              </div>                         
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Mã tín phiếu
              </label>
              <div class="col-sm-7">
                <html:hidden property="ma_tpcp"/>                 
                <html:select property="ma_tpcp" styleId="ma_tpcp"
                             disabled="true"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onchange="change_ma_tp(this)">
                  <logic:notEmpty name="lstAllTPCP">
                    <html:optionsCollection name="lstAllTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
        </div>
         <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Ngày hợp đồng
              </label>
                <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tt"
                             readonly="true"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_dt"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="tpbkechuyentien.ky_han"/>
              </label>
              <div class="col-sm-7">
                <html:hidden property="ky_han"/>
                <html:hidden property="ngay_tt_lai_1"/>
                <html:hidden property="ngay_tt_lai_2"/>
                <html:hidden property="ngay_tt_tien_mua"/>
                 <html:hidden property="loai_tien"/>                
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_ph"
                             readonly="true"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="1" property="ngay_ph"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2 a -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Khối lượng trúng thầu
              </label>
              <div class="col-sm-7">                
              <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="kl_trung_thau" styleId="kl_trung_thau"/>
            </div>
          </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Kỳ hạn tín phiếu
              </label>
              <div class="col-sm-7">   
                <html:select property="ky_han" styleId="ky_han" disabled="true"
                             styleClass="form-control selectpicker"
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
        <!--Row 4-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Lãi suất phát hành (%/năm)
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_trung_thau" styleId="ls_trung_thau"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                
              </label>
              <div class="col-sm-7">
                <html:hidden styleClass="form-control" 
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_danh_nghia" styleId="ls_danh_nghia"/>
              </div>
            </div>
          </div>
        </div>
        </logic:equal>     
         <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-5 control-label">
                Lý do từ chối
              </label>
              <div class="col-sm-7">
              <logic:equal value="01" name="QLyLapBangKeForm" property="trang_thai">
                <html:textarea styleClass="form-control" 
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5" 
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ly_do_tu_choi" styleId="ly_do_tu_choi"/>
              </logic:equal>
              <logic:notEqual value="01" name="QLyLapBangKeForm" property="trang_thai">
                <html:textarea styleClass="form-control" 
                           onkeypress="return blockKeySpecial001(event)"
                           tabindex="5" readonly="true"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ly_do_tu_choi" styleId="ly_do_tu_choi"/>
              </logic:notEqual>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">Th&ocirc;ng tin chi tiết</h2>
    </div>
    <div class="panel-body">
      <table class="table table-bordered">
        <thead>
          <th>STT</th>
          <th>Chủ sở hữu tr&aacute;i phiếu</th>
          <th>
            <logic:equal value="TL" name="QLyLapBangKeForm" property="ptph_tpcp">
            Giá trị trái phiếu mua 
            </logic:equal>
            <logic:equal value="PHTT" name="QLyLapBangKeForm" property="ptph_tpcp">
            Giá trị tín phiếu mua 
            </logic:equal>
            <logic:equal value="TD" name="QLyLapBangKeForm" property="ptph_tpcp">
            Khối lượng
            <br/>
            tr&uacute;ng thầu 
            </logic:equal>
            <logic:equal value="TPKB" name="QLyLapBangKeForm" property="ptph_tpcp">
            Khối lượng
            <br/>
            tr&uacute;ng thầu 
            </logic:equal>
                    <logic:equal value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (USD)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual>
          </th>
          <th>
            Số tiền
            <br/>
            phải thanh to&aacute;n 
                    <logic:equal value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (USD)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual>
          </th>
          <th>
            Số tiền
            <br/>
            đã thanh to&aacute;n 
                    <logic:equal value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (USD)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyLapBangKeForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual>
          </th>
          <th>Ng&agrave;y giờ chuyển tiền</th>
          <th>Ghi ch&uacute;</th>
        </thead>
         <% 
              BigDecimal sumKLTT = new BigDecimal("0");
              BigDecimal sumSTTT = new BigDecimal("0");
              BigDecimal sumSTDaTT = new BigDecimal("0");
         %>
        <tbody>
          <logic:notEmpty name="lstDViSoHuuBKe">
            <logic:iterate id="objDViSoHuu" name="lstDViSoHuuBKe" indexId="stt">
              <tr class="">
                <td class="center">
                  <%=stt + 1%>
                </td>
                <td class="left">
                  <input type="hidden" name="ma_trai_chu" value='<bean:write name="objDViSoHuu" property="ma_nguoi_so_huu"/>'/>
                  <input type="hidden" name="ten_trai_chu" value='<bean:write name="objDViSoHuu" property="ten_nguoi_so_huu"/>'/>
                  <bean:write name="objDViSoHuu" property="ten_nguoi_so_huu"/>
                </td>
                <td class="right">
                  <input type="hidden" name="kl_trung_thau_ctiet" value='<bean:write name="objDViSoHuu" property="kl_trung_thau"/>'/>
                  <logic:present name="objDViSoHuu" property="kl_trung_thau">
                  <bean:define id="kl_trung_thau" name="objDViSoHuu"
                               property="kl_trung_thau"/>                   
                  <%=StringUtil.convertNumberToString(kl_trung_thau.toString(),"VND")%>
                  <%sumKLTT = sumKLTT.add(new BigDecimal(kl_trung_thau.toString()));%>
                  </logic:present>
                </td>
                <td class="right">
                  <input type="hidden" name="so_tien_phai_tt" value='<bean:write name="objDViSoHuu" property="tien_tt_mua"/>'/>
                  <logic:present name="objDViSoHuu" property="tien_tt_mua">
                  <bean:define id="tien_tt_mua" name="objDViSoHuu"
                               property="tien_tt_mua" type="String"/>                   
                  <%=StringUtil.convertNumberToString(tien_tt_mua,"VND")%>
                  <%sumSTTT = sumSTTT.add(new BigDecimal(tien_tt_mua.toString()));%>
                  </logic:present>
                </td>
                <td class="right">
                  <input type="hidden" name="so_tien_da_tt" value='<bean:write name="objDViSoHuu" property="so_tien_da_tt"/>'/>
                  <logic:present name="objDViSoHuu" property="so_tien_da_tt">
                  <bean:define id="so_tien_da_tt" name="objDViSoHuu"
                               property="so_tien_da_tt" type="String"/>                   
                  <%=StringUtil.convertNumberToString(so_tien_da_tt,"VND")%>
                  <%sumSTDaTT = sumSTDaTT.add(new BigDecimal(so_tien_da_tt.toString()));%>
                  </logic:present>
                </td>
                <td class="icon">
                    <input type="hidden" name="ngay_chuyen_tien" value='<bean:write name="objDViSoHuu" property="ngay_chuyen_tien"/>'/>
                    <bean:write name="objDViSoHuu" property="ngay_chuyen_tien"/>
                </td>
                <td class="icon">
                <bean:write name="objDViSoHuu" property="ghi_chu"/>
                  
                </td>
              </tr>
            </logic:iterate>
          </logic:notEmpty>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="2" class="center">
                    <b>Tổng cộng</b>
                </td>
                <td class="right">
                   <b> <%= StringUtil.convertNumberToString(sumKLTT.toString(),"VND") %></b>
                </td>
                <td class="right">
                    <b><%=StringUtil.convertNumberToString(sumSTTT.toString(),"VND") %></b>
                </td>
                <td class="right">
                    <b><%=StringUtil.convertNumberToString(sumSTDaTT.toString(),"VND") %></b>
                </td>
                <td></td>
                <td></td>
            </tr>
        </tfoot>
      </table>
    </div>
  </div>
  <div class="center">    
    <logic:equal value="01" name="QLyLapBangKeForm" property="trang_thai">
    <button type="button" class="btn btn-default" onclick="check('pduyet')"
            accesskey="d" tabindex="7" id="bfind">
      <span class="sortKey">D</span>uyệt
    </button>
    <button type="button" class="btn btn-default" onclick="check('tchoi')"
            accesskey="t" tabindex="7" id="bfind">
      Từ <span class="sortKey">c</span>hối
    </button>
    </logic:equal>
    <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden property="listIdSoHuu" styleId="listIdSoHuu"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>