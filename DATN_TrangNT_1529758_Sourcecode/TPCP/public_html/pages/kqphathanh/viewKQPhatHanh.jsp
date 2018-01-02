<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQPHCTIETTPCPResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<script type="text/javascript">
 // jQuery.noConflict();
  jQuery(document).ready(function () {
  });

  function check(type) {
      
      var f = document.forms[0];
      f.target = '';
      if (type == 'save') {
      if (confirm('Bạn có muốn trình kết quả phát hành theo phương thức đầu thầu này không ?')){
          f.action = 'TrinhKQPhatHanhAction.do';
          f.submit();
      }
      }
      if (type == 'close') {
          f.action = 'ListKQphathanhAction.do';
          f.submit();
      }
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="viewKQPhatHanhAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqphathanh.title"/></strong>
    </h1>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="kqphathanh.view.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqphathanh.so_tb_de_nghi_ph"/>
              </label>
              <div class="col-sm-8">
                       <html:text styleClass="form-control"
                           property="so_tb_de_nghi_ph"
                           name="QLyKQPhatHanhForm" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Ngày tổ chức phát hành
              </label>
              <div class="col-sm-7">
                 
               <html:text styleClass="form-control"
                           property="ngay_dt"
                           name="QLyKQPhatHanhForm" readonly="true"/>
                
              </div>
            </div>
          </div>
        </div>
        <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqphathanh.ngay_tb_de_nghi_ph"/>
              </label>
              <div class="col-sm-8">
                
                   <html:text styleClass="form-control"
                           property="ngay_tb_de_nghi_ph"
                           name="QLyKQPhatHanhForm" readonly="true"/>
                
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.loai_tien"/>
              </label>
              <div class="col-sm-7">
               
                <html:text styleClass="form-control"
                           property="loai_tien"
                           name="QLyKQPhatHanhForm" readonly="true"/>
               
              </div>
            </div>
          </div>
        </div>
             <!--Row 3  -->
               <logic:notEmpty name="QLyKQPhatHanhForm" property="ly_do_tu_choi">
        <div class="row">
          <div class="col-md-12" id="div_lydotuchoi">
            <div class="form-group">
              <label for="hoten" class="col-sm-2 control-label">
                <fmt:message key="kqphathanh.ly_do_tu_choi"/>
              </label>
              <div class="col-sm-10">
                <html:textarea rows="2" styleClass="form-control" readonly="true"
                               onkeypress="return blockKeySpecial001(event)"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="1"
                               styleId="ly_do_tu_choi"
                               property="ly_do_tu_choi"/>
              </div>
            </div>
          </div>
        </div>
        </logic:notEmpty>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="kqphathanh.chitiet.tpcp"/>
      </h2>
    </div>
    <div class="panel-body auto-scoll">
      <table class="table table-bordered">
        <tr class="header">
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.stt"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.ma_tpcp"/>
          </td>
          <td rowspan="2" style="min-width: 80px; text-align: center;">
            <fmt:message key="kqphathanh.ky_han"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.ngay_ph"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.ngay_dh"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.lai_suat_trung_thau"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.lai_suat_danh_nghia"/>
          </td>
          <td colspan="3" class="center">
            <fmt:message key="kqphathanh.khoi_luong_phat_hanh"/>
          </td>
        </tr>
         
        <tr class="header">
          <td class="center">
            <fmt:message key="kqphathanh.khoi_luong_phat_hanh_dau_thau"/>
          </td>
          <td class="center">
            <fmt:message key="kqphathanh.khoi_luong_phat_hanh_sau_dau_thau"/>
          </td>
          <td class="center">
            <fmt:message key="kqphathanh.tong_khoi_luong_phat_hanh"/>
          </td>
        </tr>
         
        <tbody>
          <logic:notEmpty name="QLyKQPhatHanhForm"
                          property="lstKQDT_CTiet_TPCP">
            <logic:iterate id="objKQPhatHanh_CTiet_TPCP"
                           name="QLyKQPhatHanhForm"
                           property="lstKQDT_CTiet_TPCP" indexId="stt">
              <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td class="center">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP" property="stt"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ma_tpcp"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ky_han"/>
                </td>
                <td class="center">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ngay_ph"/>
                </td>
                <td class="center">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ngay_dao_han"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ls_trung_thau"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ls_danh_nghia"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP" property="klph"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="klph_them"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="tong_klph"/>
                </td>
              </tr>
            </logic:iterate>
          </logic:notEmpty>
        </tbody>
      </table>
    </div>
  </div>
    <div class="row">
    <div class="col-md-6" style="padding-left:20px;margin-bottom: 10px">
      <strong>Mã trái phiếu: <bean:write name="QLyKQPhatHanhForm"
                            property="ma_tpcp"/></strong>
    </div>
    <div class="col-md-6" align="right" style="padding-right:20px;margin-bottom: 10px">
      <strong>Đơn vị t&iacute;nh: <bean:write name="QLyKQPhatHanhForm"
                            property="loai_tien"/></strong>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="kqphathanh.chitiet.sohuu"/>
      </h2>
    </div>
    <div class="panel-body auto-scoll">
      <table class="table table-bordered" id="addTableTemp">
        <thead>
          <th class="center">STT</th>
          <th style="min-width: 100px; text-align: center;">
            <fmt:message key="kqphathanh.thanh_vien_dt"/>
          </th>
          <th style="min-width: 180px; text-align: center;">
            <fmt:message key="kqphathanh.ten_nguoi_so_huu"/>
          </th>
          <th style="min-width: 80px; text-align: center;">
            <fmt:message key="kqphathanh.ma_nha_dau_tu"/>
          </th>
          <th style="min-width: 120px; text-align: center;">
            <fmt:message key="kqphathanh.so_tk_tt"/>
          </th>
          <th style="min-width: 120px; text-align: center;">
            <fmt:message key="kqphathanh.kl_trung_thau"/>
          </th>
          <th style="min-width: 100px; text-align: center;">
            <fmt:message key="kqphathanh.lai_suat_trung_thau"/>
          </th>
          <th style="min-width: 100px; text-align: center;">
            <fmt:message key="kqphathanh.tien_tt_mua"/>
          </th>
        </thead>
         
        <tbody>
          <logic:notEmpty name="QLyKQPhatHanhForm"
                          property="lstKQPH_CTiet_SoHuu">
            <logic:iterate id="objKQPhatHanh_CTiet_SoHuu"
                           name="QLyKQPhatHanhForm"
                           property="lstKQPH_CTiet_SoHuu" indexId="stt">
              <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td class="center">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu" property="stt"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="thanh_vien_dau_thau"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="ten_nguoi_so_huu"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="ma_nguoi_so_huu"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="so_tk_tt"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="kl_trung_thau"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="ls_trung_thau"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="tien_tt_mua"/>
                </td>
              </tr>
            </logic:iterate>
          </logic:notEmpty>
          <tr>
            <td colspan="5" style="text-align:center;font-weight: bold;">Tổng
                                                                         cộng</td>
            <td style="text-align:right;font-weight: bold;">
              <bean:write name="QLyKQPhatHanhForm"
                          property="tong_kl_trung_thau"/>
            </td>
            <td></td>
            <td style="text-align:right;font-weight: bold;">
              <bean:write name="QLyKQPhatHanhForm" property="tong_tien_ban"/>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div class="center">
     <logic:notEqual value="1" name="QLyKQPhatHanhForm"
                                property="trang_thai">
                  <logic:notEqual value="2" name="QLyKQPhatHanhForm"
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
    
  </div>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>