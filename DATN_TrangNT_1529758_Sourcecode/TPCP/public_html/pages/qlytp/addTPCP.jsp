<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyTPResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      f.action = '';
      var guid = document.getElementById("guid").value;
      if (type == 'save') {
          if (guid != null && guid != "") {
              f.action = 'QuanLyTPUpdateExecuteAction.do';
          }
          else {
              f.action = 'QuanLyTPExecuteAction.do';
          }
          if (validateForm()) {
              f.submit();
          }
      }
      if (type == 'close') {
          f.action = 'QuanLyTPListAction.do';
          f.submit();
      }
  }
  function changeLoaiTP(obj){     
      $('.selectpicker').selectpicker('refresh');
  }
  function luachon() {
      var phuong_thuc_ph = document.getElementById("phuong_thuc_ph").value;
      if (phuong_thuc_ph != null && phuong_thuc_ph == "PHTT") {
          document.getElementById("ma_tp").value = "TPKB";
      }else if (phuong_thuc_ph != null && phuong_thuc_ph == "TL") {
          var dvi_mua = $("#dvi_mua").val();
          var noi_cap_x = '';
          if(dvi_mua!=null&&dvi_mua!=''){
            if(dvi_mua == 'SCIC'){
            dvi_mua = 'TWSC';
            noi_cap_x = 'KBNN';
            }else if(dvi_mua == 'BH'){
              dvi_mua = 'TWBH';
              noi_cap_x = 'KBNN';
            }
          }else {
            dvi_mua = 'TL';
            noi_cap_x = 'VSD';
            $("#dvi_mua").val('');
          }
            document.getElementById("ma_tp").value = dvi_mua;
            document.getElementById("noi_cap").value = noi_cap_x;                      
      }else{
        document.getElementById("ma_tp").value = phuong_thuc_ph;
      }
      $('.selectpicker').selectpicker('refresh');
  }

  function validateForm() {
      var ma_TP = document.getElementById("ma_tp").value;
      var noi_cap = document.getElementById("noi_cap").value;
      var ky_han = document.getElementById("ky_han").value;
      var phuong_thuc_ph = document.getElementById("phuong_thuc_ph").value;
      //      alert('ma_tp');
      if (phuong_thuc_ph == null || phuong_thuc_ph == "") {
          alert('Bạn phải nhập phương thức phát hành');
          $("#phuong_thuc_ph").focus();
          return false;
      }
      if (ma_TP == null || ma_TP == "") {
          alert('Bạn phải nhập mã TPCP');
          $("#ma_tp").focus();
          return false;
      }
      else {
          if (ma_TP.length <9) {
              alert('Bạn phải nhập đúng cấu trúc mã TPCP');
              $("#ma_tp").focus();
              return false;
          }
      }
      if (noi_cap == null || noi_cap == "") {
          alert('Bạn phải nhập Nơi cấp');
          $("#noi_cap").focus();
          return false;
      }
      else {
          if(ma_TP!=null && ma_TP.length > 2){
             var ma_TP_2 = ma_TP.substring(0,2);
             if(ma_TP_2  == "TW" && phuong_thuc_ph == "TL"){
               if (noi_cap == "VSD") {
                  alert('Sai nơi cấp');
                  $("#noi_cap").focus();
                  return false;
                }
             }else{
                if (phuong_thuc_ph == "TD" || phuong_thuc_ph == "TL" || phuong_thuc_ph == "PHTT" || phuong_thuc_ph == "TPKB") {
                  if (noi_cap == "KBNN") {
                      alert('Sai nơi cấp');
                      $("#noi_cap").focus();
                      return false;
                  }
                }
             }
          }
          
      }
      if (ky_han == null || ky_han == "") {
          alert('Bạn phải nhập Kỳ hạn');
          $("#ky_han").focus();
          return false;
      }
      if (phuong_thuc_ph == "TD" || phuong_thuc_ph == "TL" || phuong_thuc_ph == "TB") {
          if (phuong_thuc_ph != ma_TP.substr(0, 2) && ma_TP.substr(0, 2)!="TW") {
              alert('Cấu trúc mã không phù hợp');
              $("#phuong_thuc_ph").focus();
              return false;
          }
      }else if (phuong_thuc_ph == "PHTT") {
          if (ma_TP.substr(0, 4) != "TPKB") {
              alert('Cấu trúc mã không phù hợp');
              $("#phuong_thuc_ph").focus();
              return false;
          }
      }

      return true;
  }

  $(function () {
      $("#phuong_thuc_ph").focus();
      changePTPH($("#loai_tpcp").val());
      changeBanLe($("#phuong_thuc_ph").val());
      $("#loai_tpcp").change(
                     function () { 
            changePTPH($("#loai_tpcp").val());
            changeBanLe($("#phuong_thuc_ph").val());
        });   
        $("#phuong_thuc_ph").change(
                     function () { 
            changeBanLe($("#phuong_thuc_ph").val());
        });
  });
  function changeBanLe(typePTPH){
    $("#div_dvi_mua").val('');
    if(typePTPH=="TL"){
       $("#div_dvi_mua").css("display", "block");
    }else{
      $("#div_dvi_mua").css("display", "none");
    }
    $('.selectpicker').selectpicker('refresh');
  }
  function changePTPH(typeTP){
    $('#phuong_thuc_ph').html('');
    $('#phuong_thuc_ph').append('<option value="">Vui lòng chọn<\/option>');
    if(typeTP == '0'){
      $('#phuong_thuc_ph').append('<option value="TD">Đấu thầu<\/option>');
      $('#phuong_thuc_ph').append('<option value="TL">Bán lẻ<\/option>');
    }else if(typeTP == '1'){
      $('#phuong_thuc_ph').append('<option value="PHTT">Phát hành trực tiếp<\/option>');
      $('#phuong_thuc_ph').append('<option value="TPKB">Đấu thầu tín phiếu<\/option>');
    }    
    var phuong_thuc_ph = '<bean:write name="QuanLyTPCPForm" property="phuong_thuc_ph"/>';
    if(phuong_thuc_ph!=null && phuong_thuc_ph!=''){
      $('#phuong_thuc_ph').val(phuong_thuc_ph);
    }
    $('.selectpicker').selectpicker('refresh');
  }
</script>
<html:form action="QuanLyTPExeListAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="quanlytp.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
  <html:errors/>
</div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="quanlytp.add.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-5 control-label">
                Loại TP
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             onchange="changeLoaiTP(this)";
                             styleId="loai_tpcp" property="loai_tpcp">
                  <html:option value="0">Trái phiếu</html:option>
                  <html:option value="1">Tín phiếu</html:option>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-5 control-label">
                <fmt:message key="quanlytp.add.ptph"/> 
                <span style="color:red">  (*)</span>
              </label>
              <div class="col-sm-7">
                <html:select styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleId="phuong_thuc_ph" property="phuong_thuc_ph"
                             onchange="luachon()">                  
                  <%--<logic:notEmpty name="listPTPH">
                    <html:optionsCollection name="listPTPH"
                                            value="ma_phuong_thuc"
                                            label="ten_phuong_thuc"/>
                  </logic:notEmpty>--%>
                </html:select>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6" id="div_dvi_mua" style="display:none">
            <div class="form-group">
              <label class="col-sm-5 control-label">
                Đơn vị mua
              </label>
              <div class="col-sm-7">
                <html:select styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleId="dvi_mua" property="dvi_mua"
                             onchange="luachon()">
                  <html:option value="">Đơn vị khác</html:option>
                  <logic:notEmpty name="listDviBanLe">
                    <html:optionsCollection name="listDviBanLe"
                                            value="ma_chu_so_huu"
                                            label="ten_dvi_so_huu"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="quanlytp.add.matp"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           maxlength="10"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="ma_tp" property="ma_tp"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2 -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="quanlytp.add.noicap"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="noi_cap" styleId="noi_cap" tabindex="3"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="VSD">VSD</html:option>
                  <html:option value="KBNN">KBNN</html:option>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="quanlytp.add.kyhan"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select styleClass="form-control selectpicker"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="4"
                             styleId="ky_han" property="ky_han">
                    <html:option value="">Vui l&ograve;ng chọn</html:option>                    
                    <optgroup label="TRÁI PHIẾU">
                    <html:optionsCollection name="lstKyHanTPCP" value="guid"
                                            label="name_ky_han"/>
                    
                    </optgroup>
                    <optgroup label="TÍN PHIẾU">
                    <html:optionsCollection name="lstKyHanTPKB" value="guid"
                                            label="name_ky_han"/>
                    </optgroup>
                  
                </html:select>
              </div>
            </div>
          </div>
        </div>
        <!--Row 3  -->
        <!--Row 4 -->
        <div class="row">
          <div class="col-md-6">
            <logic:empty name="QuanLyTPCPForm" property="guid">
              <html:hidden property="trang_thai" styleId="trang_thai"
                           value="01"/>
            </logic:empty>
             
            <logic:notEmpty name="QuanLyTPCPForm" property="guid">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="quanlytp.add.trangthai"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select styleClass="form-control selectpicker" tabindex="5"
                             property="trang_thai" styleId="trang_thai"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="01">M&atilde; mới</html:option>
                  <html:option value="02">Đang hoạt động</html:option>
                  <html:option value="03">Kho&aacute;</html:option>
                </html:select>
              </div>
            </logic:notEmpty>
          </div>
          <!--<div class="col-md-6">            
              <label for="hoten" class="col-sm-5 control-label">Đặc biệt</label>
              <div class="col-sm-7">
                <logic:equal name="QuanLyTPCPForm" property="dac_biet"
                             value="on">
                  <input type="checkbox" class="form-control" tabindex="5"
                         checked="checked" id="dac_biet" name="dac_biet"/>
                </logic:equal>
                 
                <logic:notEqual name="QuanLyTPCPForm" property="dac_biet"
                                value="on">
                  <input type="checkbox" class="form-control" tabindex="5"
                         id="dac_biet" name="dac_biet"/>
                </logic:notEqual>
              </div>
          </div>-->
        </div>
      </div>
    </div>
  </div>
  <div class="center">
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g" tabindex="7" id="bfind">
      <span class="sortKey">G</span>hi
    </button>
     
    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="8">
      Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>