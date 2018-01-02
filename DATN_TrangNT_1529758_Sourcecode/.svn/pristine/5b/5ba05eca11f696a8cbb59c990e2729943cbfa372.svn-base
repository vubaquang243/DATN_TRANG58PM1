<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQPHTINPHIEUResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      var ly_do_tu_choi = $("#ly_do_tu_choi").val();

      if (type == 'pheduyet') {
       

          if (confirm('Bạn có chắc chắn muốn duyệt KQPH này không ?')) {
              document.getElementById("trang_thai").value = '02';
              f.action = "PheDuyetKQPHTinPhieuPheDuyetAction.do";
          }else{
              f.action = "ViewKQPHTinPhieuPDAction.do?longid=" + guid;
          }
          
      }
      if (type == 'tuchoi') {
          if (ly_do_tu_choi == "" || ly_do_tu_choi.trim() == '') {
              alert("Bạn phải nhập lý do từ chối !");
              $("#ly_do_tu_choi").focus();
              return false
          }
          else {
              document.getElementById("trang_thai").value = '03';
              f.action = "PheDuyetKQPHTinPhieuPheDuyetAction.do";
          }
      }
      if (type == 'close') {
          document.getElementById('trang_thai').value = "";
          document.getElementById('ma_tpcp').value = "";
          f.action = 'SearchKQPhatHanhTinPhieuPDAction.do';
      }
      f.submit();
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="ViewKQPHTinPhieuAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqphathanh.phe_duyet_title"/></strong>
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
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.so_tb_de_nghi_ph"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" property="so_tb_de_nghi_ph"
                           name="KQPHTinPhieuForm" readonly="true"/>
                 
                <html:hidden name="KQPHTinPhieuForm"
                             property="so_tb_de_nghi_ph"/>
                 
                <html:hidden name="KQPHTinPhieuForm" property="tong_klph"/>
                 
                <html:hidden name="KQPHTinPhieuForm" property="tong_klph_them"/>
                 
                <html:hidden name="KQPHTinPhieuForm"
                             property="tong_kl_trung_thau"/>
                 
                <html:hidden name="KQPHTinPhieuForm" property="ngay_to_chuc_ph"/>
                 
                <html:hidden name="KQPHTinPhieuForm" property="ngay_dt"/>
                 
                <html:hidden name="KQPHTinPhieuForm" property="ma_tpcp"
                             styleId="ma_tpcp"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.ngay_to_chuc_ph"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" property="ngay_to_chuc_ph"
                           name="KQPHTinPhieuForm" readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.ngay_tb_de_nghi_ph"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           property="ngay_tb_de_nghi_ph" name="KQPHTinPhieuForm"
                           readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.loai_tien"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" property="loai_tien"
                           readonly="true" name="KQPHTinPhieuForm"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 3-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.ly_do_tu_choi"/>
              </label>
               
              <logic:equal value="01" name="KQPHTinPhieuForm"
                           property="trang_thai">
                <div class="col-sm-7">
                  <html:textarea styleClass="form-control"
                                 property="ly_do_tu_choi"
                                 styleId="ly_do_tu_choi"
                                 style="width:734px;"
                                 name="KQPHTinPhieuForm" />
                </div>
              </logic:equal>
               
              <logic:notEqual value="01" name="KQPHTinPhieuForm"
                              property="trang_thai">
                <div class="col-sm-7">
                  <html:textarea styleClass="form-control"
                                 property="ly_do_tu_choi"
                                 style="width:734px;"
                                 styleId="ly_do_tu_choi" name="KQPHTinPhieuForm"
                                 readonly="true"/>
                </div>
              </logic:notEqual>
            </div>
          </div>
        </div>
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
            <fmt:message key="kqphathanh.ls_binh_quan"/>
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
          <logic:notEmpty name="KQPHTinPhieuForm" property="lstCTPH_TPCP">
            <logic:iterate id="objKQPhatHanh_CTiet_TPCP" name="KQPHTinPhieuForm"
                           property="lstCTPH_TPCP" indexId="stt">
              <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td class="center">
                  <%=stt + 1%>
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
                              property="ls_binh_quan"/>
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
    <div class="col-md-6" style="padding-left:20px">
      <strong>M&atilde; t&iacute;n phiếu:<bean:write property="ma_tpcp"
                                                     name="KQPHTinPhieuForm"/></strong>
    </div>
    <div class="col-md-6" align="right" style="padding-right:20px">
      <strong>Đơn vị t&iacute;nh:<bean:write property="loai_tien"
                                             name="KQPHTinPhieuForm"/></strong>
    </div>
  </div>
  <br/>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="kqphathanh.chitiet.tpcp2"/>
      </h2>
    </div>
    <div class="panel-body">
      <table class="table table-bordered">
        <tr class="header">
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.stt"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.thanh_vien_dt"/>
          </td>
          <td rowspan="2" style="min-width: 80px; text-align: center;">
            <fmt:message key="kqphathanh.ten_nguoi_so_huu"/>
          </td>
          <td rowspan="2" style="min-width: 80px; text-align: center;">
            <fmt:message key="kqphathanh.ma_chu_so_huu"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.so_tk_tt"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.kl_trung_thau"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.ls_trung_thau"/>
          </td>
          <td rowspan="2" class="center">
            <fmt:message key="kqphathanh.tien_tt_mua"/>
          </td>
        </tr>
         
        <tbody>
          <logic:notEmpty name="KQPHTinPhieuForm" property="listCTSHTinPhieu">
            <logic:iterate id="objKQPHTinPhieu" name="KQPHTinPhieuForm"
                           property="listCTSHTinPhieu" indexId="stt">
              <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td class="center">
                  <bean:write name="objKQPHTinPhieu" property="stt"/>
                   
                  <html:hidden name="objKQPHTinPhieu" property="stt"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPHTinPhieu" property="thanh_vien_dt"/>
                   
                  <html:hidden name="objKQPHTinPhieu" property="thanh_vien_dt"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPHTinPhieu" property="ten_chu_so_huu"/>
                   
                  <html:hidden name="objKQPHTinPhieu"
                               property="ten_chu_so_huu"/>
                </td>
                <td class="left">
                  <html:text styleClass="form-control" property="ma_chu_so_huu"
                             name="objKQPHTinPhieu" readonly="true"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPHTinPhieu" property="so_tk_tt"/>
                   
                  <html:hidden name="objKQPHTinPhieu" property="so_tk_tt"/>
                </td>
                <td class="center">
                  <bean:write name="objKQPHTinPhieu" property="kl_trung_thau"/>
                   
                  <html:hidden name="objKQPHTinPhieu" property="kl_trung_thau"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPHTinPhieu" property="ls_trung_thau"/>
                   
                  <html:hidden name="objKQPHTinPhieu" property="ls_trung_thau"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPHTinPhieu" property="tien_tt_mua"/>
                   
                  <html:hidden name="objKQPHTinPhieu" property="tien_tt_mua"/>
                </td>
              </tr>
            </logic:iterate>
            <tr class="header">
              <td class="center" colspan="4">Tổng cộng</td>
              <td class="center">&nbsp;</td>
              <td class="center">
                <bean:write name="KQPHTinPhieuForm"
                            property="tong_kl_trung_thau"/>
                 
                <html:hidden name="KQPHTinPhieuForm"
                             property="tong_kl_trung_thau"/>
              </td>
              <td class="center">&nbsp;</td>
              <td class="center">
                <bean:write name="KQPHTinPhieuForm" property="tong_tien_ban"/>
                 
                <html:hidden name="KQPHTinPhieuForm" property="tong_tien_ban"/>
              </td>
            </tr>
          </logic:notEmpty>
        </tbody>
      </table>
    </div>
  </div>
  <div class="center">
    <logic:equal value="01" name="KQPHTinPhieuForm" property="trang_thai">
      <button type="button" class="btn btn-default" onclick="check('pheduyet')"
              accesskey="d">
        <span class="sortKey">D</span>uyệt
      </button>
      <button type="button" class="btn btn-default" onclick="check('tuchoi')"
              accesskey="c">
        Từ <span class="sortKey">c</span>hối
      </button>
    </logic:equal>
     
    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="8">
      Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden property="trang_thai" styleId="trang_thai"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>