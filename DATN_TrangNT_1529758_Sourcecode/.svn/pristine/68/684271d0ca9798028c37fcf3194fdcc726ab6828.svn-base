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
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      if (type == 'save') {
          if (guid != null && guid != "") {
              f.action = 'UpdateExeKQPhathanhAction.do';
              $("#trang_thai").val('00');
          }
          else {
              f.action = 'AddExeKQPhathanhAction.do';
              $("#trang_thai").val('00');
          }              
          if(validateForm()){
            f.submit();
          }   
      }
      if (type == 'trinh') {
        if (guid != null && guid != "") {
              f.action = 'UpdateExeKQPhathanhAction.do';
              $("#trang_thai").val('01');
          }
          else {
              f.action = 'AddExeKQPhathanhAction.do';
              $("#trang_thai").val('01');
          }
          if(validateForm()){
            f.submit();
          }   
      }
      if (type == 'close') {
          f.action = 'ListKQphathanhAction.do';
          f.submit();
      }

  }
  function validateForm(){
    var ma_nguoi_so_huu = document.getElementsByName('ma_nguoi_so_huu');
    var ten_nguoi_so_huu = document.getElementsByName('ten_nguoi_so_huu');
    if(ma_nguoi_so_huu!=null && ma_nguoi_so_huu.length > 0){
       for(var i = 0; i < ma_nguoi_so_huu.length;i++){
           if(ma_nguoi_so_huu[i].value == ''){
              alert('Bạn phải nhập Mã đơn vị sở hữu');
              ma_nguoi_so_huu[i].focus();
              return false;

           }
       }
    }
    //check trung ma
    if(ma_nguoi_so_huu!=null && ma_nguoi_so_huu.length > 0){
        for(var k = 0; k < ma_nguoi_so_huu.length;k++){
            for(var m=0; m < ma_nguoi_so_huu.length;m++){
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
    return true;
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="AddExeKQPhathanhAction" method="post">
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
                <html:text styleClass="form-control" property="so_tb_de_nghi_ph"
                           name="QLyKQPhatHanhForm" />
    
                <html:hidden name="QLyKQPhatHanhForm" property="tong_klph"/>
                 
                <html:hidden name="QLyKQPhatHanhForm"
                             property="tong_klph_them"/>
                 
                <html:hidden name="QLyKQPhatHanhForm"
                             property="tong_kl_trung_thau"/>
                 
                <html:hidden name="QLyKQPhatHanhForm" property="ngay_dt"/>
                 <html:hidden name="QLyKQPhatHanhForm" property="ngay_ph"/>
                <html:hidden name="QLyKQPhatHanhForm" property="ma_tpcp"/>
                
                <html:hidden name="QLyKQPhatHanhForm" property="ky_han"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Ngày tổ chức phát hành
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="ngay_dt"
                           name="QLyKQPhatHanhForm" />
                 
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
                           name="QLyKQPhatHanhForm" />
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="kqphathanh.loai_tien"/>
              </label>
              <div class="col-sm-8">
                    <html:text styleClass="form-control"
                           property="loai_tien"
                           name="QLyKQPhatHanhForm" readonly="true"/>
                 
                <html:hidden name="QLyKQPhatHanhForm" property="loai_tien"/>
              </div>
            </div>
          </div>
        </div>
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
    <div class="panel-body">
      <table class="table table-bordered">
        <tr class="header">
          <th rowspan="2" class="center">
            <fmt:message key="kqphathanh.stt"/>
          </th>
          <th rowspan="2" class="center">
            <fmt:message key="kqphathanh.ma_tpcp"/>
          </th>
          <th rowspan="2" style="min-width: 80px; text-align: center;">
            <fmt:message key="kqphathanh.ky_han"/>
          </th>
          <th rowspan="2" class="center">
            <fmt:message key="kqphathanh.ngay_ph"/>
          </th>
          <th rowspan="2" class="center">
            <fmt:message key="kqphathanh.ngay_dh"/>
          </th>
          <th rowspan="2" class="center">
            <fmt:message key="kqphathanh.lai_suat_trung_thau"/>
          </th>
          <th rowspan="2" class="center">
            <fmt:message key="kqphathanh.lai_suat_danh_nghia"/>
          </th>
          <th colspan="3" class="center">
            <fmt:message key="kqphathanh.khoi_luong_phat_hanh"/>
          </th>
        </tr>
         
        <tr class="header">
          <th class="center">
            <fmt:message key="kqphathanh.khoi_luong_phat_hanh_dau_thau"/>
          </th>
          <th class="center">
            <fmt:message key="kqphathanh.khoi_luong_phat_hanh_sau_dau_thau"/>
          </th>
          <th class="center">
            <fmt:message key="kqphathanh.tong_khoi_luong_phat_hanh"/>
          </th>
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
      <strong>M&atilde; tr&aacute;i phiếu: 
        <bean:write name="QLyKQPhatHanhForm" property="ma_tpcp"/></strong>
    </div>
    <div class="col-md-6" align="right"
         style="padding-right:20px;margin-bottom: 10px">
      <strong>Đơn vị t&iacute;nh: 
        <bean:write name="QLyKQPhatHanhForm" property="loai_tien"/></strong>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="kqphathanh.chitiet.sohuu"/>
      </h2>
    </div>
    <div class="panel-body auto-scoll">
      <table class="table table-bordered">
        <thead>
          <th class="center">STT</th>
          <th class="center">
            <fmt:message key="kqphathanh.thanh_vien_dt"/>
          </th>
          <th class="center">
            <fmt:message key="kqphathanh.ten_nguoi_so_huu"/>
          </th>
          <th class="center">
            <fmt:message key="kqphathanh.ma_nha_dau_tu"/>
          </th>
          <th class="center">
            <fmt:message key="kqphathanh.so_tk_tt"/>
          </th>
          <th class="center">
            <fmt:message key="kqphathanh.kl_trung_thau"/>
          </th>
          <th class="center">
            <fmt:message key="kqphathanh.lai_suat_trung_thau"/>
          </th>
          <th class="center">
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
                   
                  <html:hidden name="objKQPhatHanh_CTiet_SoHuu"
                               property="thanh_vien_dau_thau"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="ten_nguoi_so_huu"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_SoHuu"
                               property="ten_nguoi_so_huu"/>
                </td>
                <td class="left">
                <logic:notEmpty name="objKQPhatHanh_CTiet_SoHuu" property="ma_nguoi_so_huu">
                  <html:text styleClass="form-control" styleId="ma_nguoi_so_huu" maxlength="10"
                             tabindex="1" name="objKQPhatHanh_CTiet_SoHuu"
                             onfocus="textfocus(this);" readonly="true"
                             onblur="textlostfocus(this);"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             property="ma_nguoi_so_huu"/>
                  </logic:notEmpty>
                  <logic:empty name="objKQPhatHanh_CTiet_SoHuu" property="ma_nguoi_so_huu">
                  <html:text styleClass="form-control" styleId="ma_nguoi_so_huu" maxlength="10"
                             tabindex="1" name="objKQPhatHanh_CTiet_SoHuu"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             property="ma_nguoi_so_huu"/>
                  </logic:empty>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="so_tk_tt"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_SoHuu"
                               property="so_tk_tt"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="kl_trung_thau"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_SoHuu"
                               property="kl_trung_thau"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="ls_trung_thau"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_SoHuu"
                               property="ls_trung_thau"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_SoHuu"
                              property="tien_tt_mua"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_SoHuu"
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
               
              <html:hidden name="QLyKQPhatHanhForm"
                           property="tong_kl_trung_thau"/>
            </td>
            <td></td>
            <td style="text-align:right;font-weight: bold;">
              <bean:write name="QLyKQPhatHanhForm" property="tong_tien_ban"/>
               
              <html:hidden name="QLyKQPhatHanhForm" property="tong_tien_ban"/>
              <html:hidden name="QLyKQPhatHanhForm" property="trang_thai" styleId="trang_thai"/>
            </td>
          </tr>
        </tbody>
      </table>
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
    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o">
      Th<span class="sortKey">o</span>át
    </button>    
  </div>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>