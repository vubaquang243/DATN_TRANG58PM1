<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuCTSoHuuVo"%>
<%@ page import="java.util.Collection"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKQPHTINPHIEUResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();

      if (type == 'save') {
          if (guid != null && guid != "") {
              f.action = 'UpdateExeKQPhatHanhTinPhieuAction.do';
          }else {
              f.action = 'AddExeKQPhatHanhTinPhieuAction.do';
          }
          if (validate()) {
              f.submit();
          }
      }else if (type == 'close') {
          f.action = 'ListKQPhatHanhTinPhieuAction.do';
          f.submit();
      }

      if (type == 'saveandsub') {
          if(guid != null && guid!=""){
                  f.action = 'UpdateExeKQPhatHanhTinPhieuAction.do';
              if (validate()) {
                  document.getElementById('trang_thai').value = "01";
                  f.submit();
              }
          }else
          if (confirm("Bạn có muốn lưu và trình KQPH Tín Phiếu này không ?")) {
          
             f.action ='AddExeKQPhatHanhTinPhieuAction.do';
              if (validate()) {
                  document.getElementById('trang_thai').value = "01";
                  f.submit();
              }
          }
          else {
              f.action = 'ListKQPhatHanhTinPhieuAction.do';
              f.submit();

          }

      }
  }

  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
  }
 
  function validate() {
      var ngay_tb_de_nghi_ph = $("#ngay_tb_de_nghi_ph").val();

      var ngay_to_chuc_ph = $("#ngay_to_chuc_ph").val();
      var ngay_tb = parseDate(ngay_tb_de_nghi_ph).getTime();
      var ngay_tc = parseDate(ngay_to_chuc_ph).getTime();
      if (ngay_tb >= ngay_tc) {
          alert("Ngày thông báo phải nhỏ hơn ngày tổ chức phát hành ! ");
          $("#ngay_to_chuc_ph").focus();
          return false;
      }
      var ngay_dao_han = '<bean:write name="KQPHTinPhieuForm"
                              property="ngay_dao_han"/>';
      var ngay_dh = parseDate(ngay_dao_han).getTime();
      if(ngay_tb_de_nghi_ph!=""){
        if(ngay_tb >= ngay_dh){
          alert("Bạn phải nhập ngày thông báo đề nghị phát hành nhỏ hơn ngày đáo hạn");
          return false;
        }
      }
      if(ngay_to_chuc_ph!= ""){
         if(ngay_tc >= ngay_dh){
          alert("Bạn phải nhập ngày thông báo nhỏ hơn ngày đáo hạn");
          return false;
        }
      }
     if(ngay_tb_de_nghi_ph ==""){
       alert("Bạn phải nhập ngày thông báo ");
       $("#ngay_tb_de_nghi_ph").focus();
       return false;
     }
     var so_tb_de_nghi_ph= $("#so_tb_de_nghi_ph").val();
     if(so_tb_de_nghi_ph==""){
       alert("Bạn phải nhập số thông báo");
       $("#so_tb_de_nghi_ph").focus();
       return false;
     }
     
    var ma_nguoi_so_huu = document.getElementsByName('ma_chu_so_huu');
    var ten_nguoi_so_huu = document.getElementsByName('ten_chu_so_huu');
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

  function isDate(txtDate) {

      var currVal = txtDate;
      if (currVal == '') {
          return false;
      }
      var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;//Declare Regex
      var dtArray = currVal.match(rxDatePattern);// is format OK?
      if (dtArray == null) {
          return false;
      }

      dtDay = dtArray[1];
      dtMonth = dtArray[3];
      dtYear = dtArray[5];
      if (dtMonth < 1 || dtMonth > 12) {
          return false;
      }
      else if (dtDay < 1 || dtDay > 31) {
          return false;
      }
      else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31) {
          return false;
      }
      else if (dtMonth == 2) {

          var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
          if (dtDay > 29 || (dtDay == 29 && !isleap)) {
              return false;
          }
      }
      return true;
  }
//    function getFormattedDate(date) {
//      var year = date.getFullYear();
//    
//      var month = (1 + date.getMonth()).toString();
//      month = month.length > 1 ? month : '0' + month;
//    
//      var day = date.getDate().toString();
//      day = day.length > 1 ? day : '0' + day;
//      
//     return  day+ '/' + month + '/' + year;
//    }
//     function parseDate(str) {
//      var mdy = str.split('/');
//      var m = toNumber(mdy[1]) - 1;
//      return new Date(mdy[2],m, mdy[0]);
//  }
//    var ngay_ph= '<bean:write name="KQPHTinPhieuForm" property="ngay_ph"/>';
//      var mili_ngay_to_chuc =parseDate(ngay_ph).getTime()-86400000;
//      var date_ngay_to_chuc =new Date(mili_ngay_to_chuc);
//      var time = getFormattedDate(date_ngay_to_chuc);
//      document.getElementById("ngay_tb_de_nghi_ph").value=time;
  function checkSTB(elt){
    var so_tb_de_nghi_ph = $("#so_tb_de_nghi_ph").val();
     $.ajax( {
          type : "post",
          url : "getAjaxSoTBAction.do", 
          data :  { so_tb_de_nghi_ph : so_tb_de_nghi_ph },
          async : true, cache : false, success : function (data) {
              var obj = JSON.parse(data);
              if(obj!=null&& obj!=""){
                alert("Số thông báo :+"+so_tb_de_nghi_ph+" đã tồn tại ")
                $("#so_tb_de_nghi_ph").focus();
             
              }
          }});
  }
  $(function () {
      $("#ngay_to_chuc_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
          var guid ='<bean:write name="KQPHTinPhieuForm" property="guid"/>';
      if(guid!=null && guid!=""){
        
        $("#so_tb_de_nghi_ph").attr("readonly", true);
      }
      $("#ngay_tb_de_nghi_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      });
  
      $("#ngay_to_chuc_ph").on("change", function () {
          var idAdd = $(this).attr("id");
      });
      $("#ngay_tb_de_nghi_ph").on("change", function () {
          var idAdd = $(this).attr("id");
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="ViewKQPHTinPhieuAction" method="post">
  <div class="panel-heading border-bottom">
  <logic:equal value="" name="KQPHTinPhieuForm" property="guid">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqphathanh.title"/></strong>
    </h1>
    </logic:equal>
    <logic:notEqual value="" name="KQPHTinPhieuForm" property="guid">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="kqphathanh.title"/></strong>
    </h1>
    </logic:notEqual>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
    <logic:equal value="" name="KQPHTinPhieuForm" property="guid" >
      <h2 class="panel-title">
        <fmt:message key="kqphathanh.add.title.exc"/>
      </h2>
      </logic:equal>
      <logic:notEqual value="" name="KQPHTinPhieuForm" property="guid" >
      <h2 class="panel-title">
        <fmt:message key="kqphathanh.edit.title"/>
      </h2>
      </logic:notEqual>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <html:hidden name="KQPHTinPhieuForm" property="dot_ph"/>
         
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.so_tb_de_nghi_ph"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" styleId="so_tb_de_nghi_ph"
                           property="so_tb_de_nghi_ph" name="KQPHTinPhieuForm" onchange="checkSTB(this)"/>
                 
                <%--<html:hidden name="KQPHTinPhieuForm"
                             property="so_tb_de_nghi_ph"/>--%>
                 
                <%-- <html:hidden name="KQPHTinPhieuForm" property="tong_klph"/>--%>
                 
                <html:hidden name="KQPHTinPhieuForm" property="tong_klph_them"/>

                <html:hidden name="KQPHTinPhieuForm" property="ngay_dt"/>
                 
                <html:hidden name="KQPHTinPhieuForm" property="ma_tpcp"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.ngay_to_chuc_ph"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_to_chuc_ph"
                             name="KQPHTinPhieuForm" onkeyup="doFormat(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="3" onfocus="textfocus(this);" readonly="true"
                             onblur="textlostfocus(this);" property="ngay_to_chuc_ph"
                             maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_to_chuc_ph">
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
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
                <div class="input-group date">
                  <html:text styleClass="form-control"
                             styleId="ngay_tb_de_nghi_ph" onkeyup="doFormat(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="3" onfocus="textfocus(this);"
                             property="ngay_tb_de_nghi_ph"
                             name="KQPHTinPhieuForm" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_tb_de_nghi_ph">
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
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
                           name="KQPHTinPhieuForm" readonly="true"/>
                <html:hidden property="ky_han"
                           name="KQPHTinPhieuForm"/>
              </div>
            </div>
          </div>
        </div>
        
        <div class="row">
          <logic:equal value="03" name="KQPHTinPhieuForm" property="trang_thai">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="kqphathanh.ly_do_tu_choi"/>
              </label>
              
              <div class="col-sm-7">
              
                <html:textarea styleClass="form-control"
                               property="ly_do_tu_choi" styleId="ly_do_tu_choi"
                               style="width:734px;" readonly="true"
                                name="KQPHTinPhieuForm" />
             
              </div>
            </div>
          </div>
            </logic:equal>
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
                   
                  <html:hidden name="objKQPhatHanh_CTiet_TPCP"
                               property="ma_tpcp"/>
                </td>
                <td class="left">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ky_han"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_TPCP"
                               property="ky_han"/>
                </td>
                <td class="center">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ngay_ph"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_TPCP"
                               property="ngay_ph"/>
                </td>
                <td class="center">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ngay_dao_han"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_TPCP"
                               property="ngay_dao_han" />
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="ls_binh_quan"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_TPCP"
                               property="ls_binh_quan"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP" property="klph"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_TPCP" property="klph"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="klph_them"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_TPCP"
                               property="klph_them"/>
                </td>
                <td class="right">
                  <bean:write name="objKQPhatHanh_CTiet_TPCP"
                              property="tong_klph"/>
                   
                  <html:hidden name="objKQPhatHanh_CTiet_TPCP"
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
                  <bean:write  name="objKQPHTinPhieu" property="ten_chu_so_huu"/>
                   
                  <html:hidden name="objKQPHTinPhieu"
                               property="ten_chu_so_huu"/>
                </td>
                <td class="left">
                 <logic:empty name="objKQPHTinPhieu" property="ma_chu_so_huu">
                 <html:text  styleClass="form-control" name="objKQPHTinPhieu" property="ma_chu_so_huu"/>  
                 </logic:empty>
                 <logic:notEmpty name="objKQPHTinPhieu" property="ma_chu_so_huu">
                 <html:text  styleClass="form-control" name="objKQPHTinPhieu" property="ma_chu_so_huu" readonly="true"/>  
                 </logic:notEmpty>
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
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g" id="bfind">
      <span class="sortKey">G</span>hi
    </button>
     
    <button type="button" class="btn btn-default" onclick="check('saveandsub')"
            accesskey="v" id="bfind">
      Ghi <span class="sortKey">v</span>à tr&igrave;nh
    </button>
     
    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="8">
      Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <html:hidden  property="ngay_ph" styleId="ngay_ph1" />
  <html:hidden  property="ngay_dao_han" styleId="ngay_dao_han1"/>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden property="trang_thai" styleId="trang_thai"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>