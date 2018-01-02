<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQDUTHAUResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
  });

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'save') {
        if (confirm('Bạn có muốn trình kết quả dự thầu này không ?')){
          f.action = 'TrinhKQDuThauAction.do';
          f.submit();
        }
      }
      if (type == 'close') {
          f.action = 'ListKQduthauAction.do';
          f.submit();
      }
  }
</script>
<html:form action="/ViewKetQuaDThauAction.do" method="post"
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
        Thông tin chi tiết KQDT
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
              <logic:notEmpty name="QLyKQDuThauForm" property="ly_do_tu_choi">
        <div class="row">
          <div class="col-md-12" id="div_lydotuchoi">
            <div class="form-group">
              <label for="hoten" class="col-sm-3 control-label">
                <fmt:message key="kqduthau.ly_do_tu_choi"/>
              </label>
              <div class="col-sm-9">
                <html:textarea rows="2" styleClass="form-control" readonly="true"
                              tabindex="1"
                               styleId="ly_do_tu_choi"
                               property="ly_do_tu_choi"/>
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
            <td rowspan="2" class="center">
              <fmt:message key="kqduthau.stt"/>
            </td>
            <td rowspan="2" class="center">
              <fmt:message key="kqduthau.ten_nha_dau_tu"/>
            </td>
            <td rowspan="2" class="center">
              <fmt:message key="kqduthau.ma_so"/>
            </td>
            <td rowspan="2" class="center">
              <fmt:message key="kqduthau.kl_dk_khong_ctls"/>
            </td>
            <td colspan="3" class="center">
              <fmt:message key="kqduthau.dauthau_canhtranh"/>
            </td>
            <td rowspan="2" class="center">
              <fmt:message key="kqduthau.kl_cong_don"/>
            </td>
          </tr>
           
          <tr class="header">
            <td class="center">
              <fmt:message key="kqduthau.lai_suat"/>
            </td >
            <td class="center">
              <fmt:message key="kqduthau.kl_ctls"/>
            </td>
            <td class="center">
              <fmt:message key="kqduthau.kl_cong_don_ctls"/>
            </td>
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
                  </td>
                  <td class="left">
                    <bean:write name="objKQDT" property="ma_nha_dau_tu"/>
                    <html:hidden name="objKQDT" property="ma_nha_dau_tu"/>
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
                </td>
                <td></td>
                <td style="text-align:right;font-weight: bold;">
                  <bean:write name="QLyKQDuThauForm"
                              property="tong_kldk_ctls_bs"/>
                </td>
                <td></td>
                <td></td>
              </tr>
            </logic:notEmpty>
            <logic:notEmpty property="lstKQDT_CTiet_Them"
                            name="QLyKQDuThauForm">
              <tr>
                <td></td>
                <td colspan="7" style="text-align:left;font-weight: bold;">2.
                                                                           Đăng
                                                                           k&yacute;
                                                                           mua
                                                                           th&ecirc;m
                                                                           tr&aacute;i
                                                                           phiếu
                                                                           ngay
                                                                           sau
                                                                           phi&ecirc;n
                                                                           đấu
                                                                           thầu</td>
              </tr>
              <logic:iterate id="objKQDT" name="QLyKQDuThauForm"
                             property="lstKQDT_CTiet_Them" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td class="center">
                    <bean:write name="objKQDT" property="stt"/>
                    <input type="hidden" name="stt" value="<bean:write name="objKQDT" property="stt"/>"/>
                  </td>
                  <td>
                    <bean:write name="objKQDT" property="ten_nha_dau_tu"/>
                    <input type="hidden" name="ten_nha_dau_tu_them" value="<bean:write name="objKQDT" property="ten_nha_dau_tu"/>"/>
                  </td>
                  <td class="left">
                    <bean:write name="objKQDT" property="ma_nha_dau_tu"/>                    
                    <input type="hidden" name="ma_nha_dau_tu_them" value="<bean:write name="objKQDT" property="ma_nha_dau_tu"/>"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_dk_khong_ctls"/>
                    <input type="hidden" name="kl_dk_khong_ctls_them" value="<bean:write name="objKQDT" property="kl_dk_khong_ctls"/>"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="lai_suat"/>                    
                    <input type="hidden" name="lai_suat_them" value="<bean:write name="objKQDT" property="lai_suat"/>"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_dtct"/>                    
                    <input type="hidden" name="kl_dtct_them" value="<bean:write name="objKQDT" property="kl_dtct"/>"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_cong_don_ctls"/>                    
                    <input type="hidden" name="kl_cong_don_ctls_them" value="<bean:write name="objKQDT" property="kl_cong_don_ctls"/>"/>
                  </td>
                  <td class="right">
                    <bean:write name="objKQDT" property="kl_cong_don"/>                    
                    <input type="hidden" name="kl_cong_don_them" value="<bean:write name="objKQDT" property="kl_cong_don"/>"/>
                  </td>
                </tr>
              </logic:iterate>
              <tr>
                <td colspan="3" style="text-align:center;font-weight: bold;">Tổng</td>
                <td style="text-align:right;font-weight: bold;">
                <logic:present name="QLyKQDuThauForm"
                              property="tong_kldk_ko_ctls_them">
                               <bean:define id="tong_kldk_ko_ctls_them" name="QLyKQDuThauForm" property="tong_kldk_ko_ctls_them"/>
                          <%=StringUtil.convertNumberToString(tong_kldk_ko_ctls_them.toString(),"VND")%>   
                </logic:present>
                              <html:hidden name="QLyKQDuThauForm"
                             property="tong_kldk_ko_ctls_them"/>
                </td>
                <td></td>
                <td style="text-align:right;font-weight: bold;">
                 
                              
                <logic:present name="QLyKQDuThauForm"
                              property="tong_kldk_ctls_them">
                               <bean:define id="tong_kldk_ctls_them" name="QLyKQDuThauForm" property="tong_kldk_ctls_them"/>
                          <%=StringUtil.convertNumberToString(tong_kldk_ctls_them.toString(),"VND")%>   
                </logic:present>
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
                             property="tong_kldk_ko_ctls_them"/>
              </td>
              <td></td>
              <td style="text-align:right;font-weight: bold;">
                <bean:write name="QLyKQDuThauForm" property="tong_kldk_ctls"/>
              </td>
              <td></td>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
 
  </div>
    <div class="center">
    <logic:notEqual value="1" name="QLyKQDuThauForm" property="trang_thai">
      <logic:notEqual value="2" name="QLyKQDuThauForm" property="trang_thai">
        <button type="button" class="btn btn-default" onclick="check('save')"
                accesskey="i">
          Đệ tr<span class="sortKey">ì</span>nh
        </button>
      </logic:notEqual>
    </logic:notEqual>
     <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>&aacute;t
      </button>    
  </div>
   <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>