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
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      if (type == 'save') {
          if (guid != null && guid != "") {
              f.action = 'UpdateExcKetQuaDThauAction.do';
          }else {
              f.action = 'AddExcKQduthauAction.do';
          }
          if(validateForm()){
          f.submit();
          }
      }else if (type == 'trinh') {
        f.action = 'TrinhKQDuThauAction.do';
        if(validateForm()){
        f.submit();
        }
      }else if (type == 'thoat') {
        f.action = 'ListKQduthauAction.do';
        f.submit();
      }
  }
  function validateForm(){
    var i, k,m;
    var ma_nguoi_so_huu = document.getElementsByName('ma_nha_dau_tu');
    var ten_nguoi_so_huu = document.getElementsByName('ten_nha_dau_tu');
    if(ma_nguoi_so_huu!=null && ma_nguoi_so_huu.length > 0){
       for( i = 0; i < ma_nguoi_so_huu.length;i++){
           if(ma_nguoi_so_huu[i].value == ''){
              alert('Bạn phải nhập Mã đơn vị sở hữu');
              ma_nguoi_so_huu[i].focus();
              return false;

           }
       }
    }
    //check trung ma
    if(ma_nguoi_so_huu!=null && ma_nguoi_so_huu.length > 0){
        for( k = 0; k < ma_nguoi_so_huu.length;k++){
            for( m=0; m < ma_nguoi_so_huu.length;m++){
              if(ma_nguoi_so_huu[k].value == ma_nguoi_so_huu[m].value){
                 if(ten_nguoi_so_huu[k].value != ten_nguoi_so_huu[m].value){
                    alert('Một đơn vị sở hửu chỉ duy nhất một mã.');
                    ma_nguoi_so_huu[k].focus();
                    return false;
                 }
              }
              if(ten_nguoi_so_huu[k].value == ten_nguoi_so_huu[m].value){
                 if(ma_nguoi_so_huu[k].value != ma_nguoi_so_huu[m].value){
                    alert('Một đơn vị sở hửu chỉ duy nhất một mã.');
                    ma_nguoi_so_huu[k].focus();
                    return false;
                 }
              }
            }
        }
    }
    var value_ma_nha_dau_tu_them = $("#ma_nha_dau_tu_them").val();
    if(value_ma_nha_dau_tu_them!="undefined"&&value_ma_nha_dau_tu_them!=null){
        var ma_nguoi_so_huu_them = document.getElementsByName('ma_nha_dau_tu_them');
        var ten_nguoi_so_huu_them = document.getElementsByName('ten_nha_dau_tu_them');
        if(ma_nguoi_so_huu_them!=null && ma_nguoi_so_huu_them.length > 0){
           for( i = 0; i < ma_nguoi_so_huu_them.length;i++){
               if(ma_nguoi_so_huu_them[i].value == ''){
                  alert('Bạn phải nhập Mã đơn vị sở hữu');
                  ma_nguoi_so_huu_them[i].focus();
                  return false;
    
               }
           }
        }
        //check trung ma
        if(ma_nguoi_so_huu_them!=null && ma_nguoi_so_huu_them.length > 0){
            for( k = 0; k < ma_nguoi_so_huu_them.length;k++){
                for( m=0; m < ma_nguoi_so_huu_them.length;m++){
                  if(ma_nguoi_so_huu_them[k].value == ma_nguoi_so_huu_them[m].value){
                     if(ten_nguoi_so_huu_them[k].value != ten_nguoi_so_huu_them[m].value){
                        alert('Một đơn vị sở hửu chỉ duy nhất một mã.');
                        ma_nguoi_so_huu_them[k].focus();
                        return false;
                     }
                  }
                  if(ten_nguoi_so_huu_them[k].value == ten_nguoi_so_huu_them[m].value){
                     if(ma_nguoi_so_huu_them[k].value != ma_nguoi_so_huu_them[m].value){
                        alert('Một đơn vị sở hửu chỉ duy nhất một mã.');
                        ma_nguoi_so_huu_them[k].focus();
                        return false;
                     }
                  }
                }
            }
        }
    }
    return true;
  }
</script>
<html:form action="/AddExcKQduthauAction.do" method="post"
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
        <fmt:message key="kqduthau.change"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <div class="row">
          <!--Đợt đấu thầu-->
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-6 control-label">
                <fmt:message key="kqduthau.dot_dt"/>
              </label>
              <div class="col-sm-6">
                     <html:text styleClass="form-control" property="dot_dt"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="dot_dt"/>
              </div>
            </div>
          </div>
          <!--Mã TPCP-->
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-6 control-label">
                <fmt:message key="kqduthau.ma_tpcp"/>
              </label>
              <div class="col-sm-6">
                <html:text styleClass="form-control" property="ma_tpcp"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="ma_tpcp"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="ten_tpcp"/>
                
              </div>
            </div>
          </div>
        </div>
        <!--Row 2 -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-6 control-label">
                <fmt:message key="kqduthau.ngay_to_chuc_ph"/>
              </label>
              <div class="col-sm-6">
                  <html:text styleClass="form-control" property="ngay_to_chuc_ph"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="ngay_to_chuc_ph"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-6 control-label">
                <fmt:message key="kqduthau.ngay_ph"/>
              </label>
              <div class="col-sm-6">
                <html:text styleClass="form-control" property="ngay_ph"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="ngay_ph"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 3 -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-6 control-label">
                <fmt:message key="kqduthau.ngay_tt_tien_mua"/>
              </label>
              <div class="col-sm-6">
                 <html:text styleClass="form-control" property="ngay_tt_tien_mua"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="ngay_tt_tien_mua"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-6 control-label">
                <fmt:message key="kqduthau.ngay_dao_han"/>
              </label>
              <div class="col-sm-6">
                 <html:text styleClass="form-control" property="ngay_dao_han"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="ngay_dao_han"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 4 -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-6 control-label">
                <fmt:message key="kqduthau.ky_han"/>
              </label>
              <div class="col-sm-6">
                  <html:text styleClass="form-control" property="ky_han"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="ky_han"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-6 control-label">
                <fmt:message key="kqduthau.kl_goi_thau"/>
              </label>
              <div class="col-sm-6">
                 <html:text styleClass="form-control" property="kl_goi_thau"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="kl_goi_thau"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 5 -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-6 control-label">
                <fmt:message key="kqduthau.kl_goi_thau_them"/>
              </label>
              <div class="col-sm-6">
                <html:text styleClass="form-control" property="kl_goi_thau_them"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="kl_goi_thau_them"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-6 control-label">
                Loại tiền
              </label>
              <div class="col-sm-6">
                <html:text styleClass="form-control"
                           property="loai_tien"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="loai_tien"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 6 -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-6 control-label">
                <fmt:message key="kqduthau.lai_du_thau_cao_nhat"/>
              </label>
              <div class="col-sm-6">
               <html:text styleClass="form-control" property="lai_du_thau_cao_nhat"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="lai_du_thau_cao_nhat"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-6 control-label">
                <fmt:message key="kqduthau.lai_du_thau_thap_nhat"/>
              </label>
              <div class="col-sm-6">
                     <html:text styleClass="form-control" property="lai_du_thau_thap_nhat"
                           name="QLyKQDuThauForm" readonly="true"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="lai_du_thau_thap_nhat"/>
              </div>
            </div>
          </div>
        </div>
        <logic:notEmpty name="QLyKQDuThauForm" property="ly_do_tu_choi">
        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="col-sm-3 control-label">
                Lý do từ chối
              </label>
              <div class="col-sm-9">
                  
               <html:textarea styleClass="form-control" property="ly_do_tu_choi" rows="3"
                           name="QLyKQDuThauForm" readonly="true"/>
                  
              </div>
            </div>
          </div>
        </div>
        </logic:notEmpty>
      <div style="padding-right:20px;margin-bottom: 10px; text-align:right;">
      <strong>Đơn vị t&iacute;nh: 
        <bean:write name="QLyKQDuThauForm" property="loai_tien"/></strong>
  </div>
        <table class="table table-bordered">
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
            <th class="center">
              <fmt:message key="kqduthau.lai_suat"/>
            </th >
            <th class="center">
              <fmt:message key="kqduthau.kl_ctls"/>
            </th>
            <th class="center">
              <fmt:message key="kqduthau.kl_cong_don_ctls"/>
            </th>
          </tr>
           
          <tbody>
             <tr>
              <td></td>
              <td colspan="7" style="text-align:left;font-weight: bold;">1. Đăng
                                                                         k&yacute;
                                                                         thầu
                                                                         phi&ecirc;n
                                                                         buổi
                                                                         s&aacute;ng</td>
            </tr>
            <logic:notEmpty property="lstKQDT_CTiet" name="QLyKQDuThauForm">
              <logic:iterate id="objKQDT" name="QLyKQDuThauForm"
                             property="lstKQDT_CTiet" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td class="center">
                    <bean:write name="objKQDT" property="stt"/>
                     
                    <html:hidden name="objKQDT" property="stt"/>
                  </td>
                  <td>
                    <bean:write name="objKQDT" property="ten_nha_dau_tu"/>
                     
                    <html:hidden name="objKQDT" property="ten_nha_dau_tu"/>
                    
                     <html:hidden name="objKQDT" property="phien_dt"/>
                  </td>
                  <td class="center">
                    <html:text styleClass="form-control" styleId="ma_nha_dau_tu"
                               tabindex="1" name="objKQDT"
                               onfocus="textfocus(this);"
                               maxlength="10"
                               onblur="textlostfocus(this);"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ma_nha_dau_tu"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_dk_khong_ctls"/>
                     
                    <html:hidden name="objKQDT" property="kl_dk_khong_ctls"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="lai_suat"/>
                     
                    <html:hidden name="objKQDT" property="lai_suat"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_dtct"/>
                     
                    <html:hidden name="objKQDT" property="kl_dtct"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_cong_don_ctls"/>
                     
                    <html:hidden name="objKQDT" property="kl_cong_don_ctls"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_cong_don"/>
                     
                    <html:hidden name="objKQDT" property="kl_cong_don"/>
                  </td>
                </tr>
              </logic:iterate>
              <tr></tr>
                 <tr>
              <td colspan="3" style="text-align:center;font-weight: bold;">Tổng</td>
              <td style="text-align:right;font-weight: bold;">
                <bean:write name="QLyKQDuThauForm"
                            property="tong_kldk_ko_ctls_bs"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="tong_kldk_ko_ctls_bs"/>
              </td>
              <td></td>
              <td style="text-align:right;font-weight: bold;">
                <bean:write name="QLyKQDuThauForm"
                            property="tong_kldk_ctls_bs"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="tong_kldk_ctls_bs"/>
              </td>
              <td></td>
              <td></td>
            </tr>
            </logic:notEmpty>
         
             <logic:notEmpty property="lstKQDT_CTiet_Them" name="QLyKQDuThauForm">
              <tr>
              <td></td>
              <td colspan="7" style="text-align:left;font-weight: bold;">2.  Đăng ký mua thêm trái phiếu ngay sau phiên đấu thầu</td>
            </tr>
              <logic:iterate id="objKQDT" name="QLyKQDuThauForm"
                             property="lstKQDT_CTiet_Them" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td class="center">
                    <bean:write name="objKQDT" property="stt_them"/>
                     
                    <html:hidden name="objKQDT" property="stt_them"/>
                  </td>
                  <td>
                    <bean:write name="objKQDT" property="ten_nha_dau_tu_them"/>
                     
                    <html:hidden name="objKQDT" property="ten_nha_dau_tu_them"/>
                    <html:hidden name="objKQDT" property="phien_dt_them"/>
                  </td>
                  <td class="center">
                    <html:text styleClass="form-control" styleId="ma_nha_dau_tu_them" maxlength="10"
                               tabindex="1" name="objKQDT"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ma_nha_dau_tu_them"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_dk_khong_ctls_them"/>
                     
                    <html:hidden name="objKQDT" property="kl_dk_khong_ctls_them"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="lai_suat_them"/>
                     
                    <html:hidden name="objKQDT" property="lai_suat_them"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_dtct_them"/>
                     
                    <html:hidden name="objKQDT" property="kl_dtct_them"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_cong_don_ctls_them"/>
                     
                    <html:hidden name="objKQDT" property="kl_cong_don_ctls_them"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_cong_don_them"/>
                     
                    <html:hidden name="objKQDT" property="kl_cong_don_them"/>
                  </td>
                </tr>
              </logic:iterate>
               <tr>
              <td colspan="3" style="text-align:center;font-weight: bold;">Tổng</td>
              <td style="text-align:right;font-weight: bold;">
                <bean:write name="QLyKQDuThauForm"
                            property="tong_kldk_ko_ctls_them"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="tong_kldk_ko_ctls_them"/>
              </td>
              <td></td>
              <td style="text-align:right;font-weight: bold;">
                <bean:write name="QLyKQDuThauForm"
                            property="tong_kldk_ctls_them"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="tong_kldk_ctls_them"/>
              </td>
              <td></td>
              <td></td>
            </tr>
              <tr></tr>
            </logic:notEmpty>
            <tr>
              <td colspan="3" style="text-align:center;font-weight: bold;">Tổng
                                                                           cộng</td>
              <td style="text-align:right;font-weight: bold;">
                <bean:write name="QLyKQDuThauForm"
                            property="tong_kldk_ko_ctls"/>
                 
                <html:hidden name="QLyKQDuThauForm"
                             property="tong_kldk_ko_ctls"/>
              </td>
              <td></td>
              <td style="text-align:right;font-weight: bold;">
                <bean:write name="QLyKQDuThauForm" property="tong_kldk_ctls"/>
                 
                <html:hidden name="QLyKQDuThauForm" property="tong_kldk_ctls"/>
              </td>
              <td></td>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <div class="center">
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g">
      <span class="sortKey">G</span>hi
    </button>
     <button type="button" class="btn btn-default" onclick="check('trinh')"
            accesskey="v">
      Ghi <span class="sortKey">v</span>à trình
    </button>
    <button type="button" class="btn btn-default" onclick="check('thoat')"
            accesskey="o">
      Th<span class="sortKey">o</span>át
    </button>    
  </div>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>