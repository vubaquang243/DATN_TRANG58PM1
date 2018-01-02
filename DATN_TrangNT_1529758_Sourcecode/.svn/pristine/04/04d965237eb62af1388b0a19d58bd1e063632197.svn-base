<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPTHONGTINDAUTHAUResource"/>

<%@ include file="/includes/tpcp_header.inc"%>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/MoneyConvert_vn.js"></script>
<!--  show mess error -->
<% 
        List<QuanLyTPCPVO> lstAllTPCP = (List<QuanLyTPCPVO>)request.getAttribute("lstAllTPCP");
        String ky_tra_lai = (String)request.getAttribute("ky_tra_lai");
        String ky_han = (String)request.getAttribute("ky_han");
%>
<script type="text/javascript">
  function check(type) {
      
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      if (type == 'save') {   
         f.action = 'PrintThongTinDauThauAction.do';
      
          if (validateForm()) {
           $("#ma_tpcp").removeAttr("disabled");
           $("#loai_tien").removeAttr("disabled");
           $("#ky_han").removeAttr("disabled");
           $("#hinh_thuc_dt").removeAttr("disabled");
           $("#ky_tra_lai").removeAttr("disabled");
           $("#pthuc_xacdinh_kqdt").removeAttr("disabled");
           $("#ngay_dao_han").datepicker('enable');
           $("#ngay_tt_lai_1").datepicker('enable');
           $("#ngay_tt_lai_2").datepicker('enable');
           var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
            newDialog = window.open('', '_form', params);
            f.target = '_form';
           f.submit();
          }
      }
      if(type=='saveandsub'){     
//        var khoi_luong_tp = replaceall($("#khoi_luong_tp").val(),'.','');
//        var khoi_luong_them = replaceall($("#khoi_luong_tp").val(),'.','');
//        var tong=parseInt(khoi_luong_tp) + parseInt(khoi_luong_them);
//        document.getElementById("khoi_luong_tp").value=tong;
         f.action = 'ViewCongBoTTinDThauThemAction.do';
          if (validateForm()) {
           $("#ma_tpcp").removeAttr("disabled");
           $("#loai_tien").removeAttr("disabled");
           $("#ky_han").removeAttr("disabled");
           $("#hinh_thuc_dt").removeAttr("disabled");
           $("#ky_tra_lai").removeAttr("disabled");
           $("#pthuc_xacdinh_kqdt").removeAttr("disabled");
           $("#ngay_dao_han").datepicker('enable');
           $("#ngay_tt_lai_1").datepicker('enable');
           $("#ngay_tt_lai_2").datepicker('enable');
              f.submit();
          }
      }
      if (type == 'close') {
          f.action="ListTTinDThauAction.do";
          f.submit();
      }
  }

  function parseString(str){
      var str1 = str.trim();
      if(str1==null){
        return true;
      }
      var mystr = str1.split('/');
      var ms1=mystr[0];
      var ms2=mystr[1];
      if(ms1== null || ms1.trim()==''){
        return true;
      }
      if(!isNaN(parseInt(ms1,10)) &&  !isNaN(parseInt(ms2,10))){
        return true;
      }
      return false;
    }


  function validateForm() {
    var dot_dau_thau = $("#ls_danh_nghia").val().trim();
      if (dot_dau_thau == null || dot_dau_thau == "") {
          alert('Bạn phải nhập lãi suất trúng thầu');
          $("#ls_danh_nghia").focus();
          return false;
      }
      if(dot_dau_thau==0){
        alert('Bạn phải nhập lãi suất trúng thầu lớn hơn 0');
          $("#ls_danh_nghia").focus();
          return false;
      }
     var khoi_luong_them = $("#khoi_luong_them").val().trim();
      if (khoi_luong_them == null || khoi_luong_them == "") {
          alert('Bạn phải nhập khối lượng thêm');
          $("#khoi_luong_them").focus();
          return false;
      }
      var khoi_luong_them_float = toNumber(khoi_luong_them);
      var khoi_luong_tp_float = toNumber($("#khoi_luong_tp").val());
      var khoi_luong_them_max= khoi_luong_tp_float*0.3;
      if(khoi_luong_them_float > khoi_luong_them_max){
        alert("Bạn phải nhập khối lượng gọi thầu thêm nhỏ hơn hoặc bằng 30% khối lượng đã phát hành :"+khoi_luong_tp_float);
        $("#khoi_luong_them").focus();
        return false;
      }
      if(khoi_luong_them==0){
        alert('Bạn phải nhập khối lượng thêm lớn hơn 0');
          $("#khoi_luong_them").focus();
          return false;
      }
      return true;
  }
  
  function parseDate(str) {
      var mdy = str.split('/');
      var m = toNumber(mdy[1]) - 1;
      return new Date(mdy[2],m, mdy[0]);
  }
 
    

  
  $(function () {
      $("#ma_tpcp").attr("readonly",true);
     $("#khoi_luong_them").keyup(function (event) {
          formatNumnew(event.target);
      });
      $("#ls_danh_nghia").focus();
      $("#ngay_tt_tien_mua").attr("readonly",true);
      $("#ngay_dao_han").attr("readonly",true);
      $("#ngay_tt_lai_1").attr("readonly",true);
      $("#ngay_tt_lai_2").attr("readonly",true);
      $("#ngay_den_han_thanh_toan").attr("readonly",true);
      $("#ngay_tbao_goi_thau").attr("readonly",true);
      $("#ngay_ph").attr("readonly",true);
      $("#ngay_to_chuc_ph").attr("readonly",true);
      $("#so_tk_nhan").attr("readonly",true);
      $("#dot_dau_thau").attr("readonly",true);
      $("#dot_bo_sung ").attr("readonly",true);
      $("#khoi_luong_tp").attr("readonly",true);
      $("#loai_tien").attr("disabled",true);
      $("#ma_tpcp").attr("disabled",true);
      $("#ky_tra_lai").attr("disabled",true);
      $("#hinh_thuc_dt").attr("disabled",true);
      $("#pthuc_xacdinh_kqdt").attr("disabled",true);
      
      var ky_tra_lai = '<bean:write name="ThongTinDauThauForm" property="ky_tra_lai"/>';
      if (ky_tra_lai == 1) {
          $("#div_ngay_tt_lai_1").show();
          $("#div_ngay_tt_lai_2").hide();
      }
      else if (ky_tra_lai == 2) {
          $("#div_ngay_tt_lai_1").show();
          $("#div_ngay_tt_lai_2").show();
      }
      else {
          $("#div_ngay_tt_lai_1").hide();
          $("#div_ngay_tt_lai_2").hide();
      }
 
      $("#khoi_luong_tp_chu").val(toEnglishCash(toNumber('<bean:write name="ThongTinDauThauForm" property="khoi_luong_tp"/>')).toLowerCase());
      $("#menh_gia").attr("readonly",true);
  });

  function format(elt){
    var currentVal = elt.value;
    if(currentVal.length ==3){
      elt.value = elt.value +"/";
    }

  }
   function validateFloatKeyPress(el, evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        var number = el.value.split(',');
        if (charCode != 44 && charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        //just one dot
        if(number.length>1 && charCode == 44){
             return false;
        }
        //get the carat position
        var caratPos = getSelectionStart(el);
        var dotPos = el.value.indexOf(",");
        if( caratPos > dotPos && dotPos>-1 && (number[1].length > 1)){
            return false;
        }
        return true;
    }
    function changemoney(){
       var khoi_luong_tp_them_chu =$("#khoi_luong_them").val();
       document.getElementById("khoi_luong_them_chu").value=toEnglishCash(toNumber(khoi_luong_tp_them_chu)).toLowerCase();
     //  $("#khoi_luong_them_chu").val(toEnglishCash(toNumber(khoi_luong_tp_them_chu)).toLowerCase());
    }
    // 2 so sau dau ","
      function getSelectionStart(o) {
        if (o.createTextRange) {
         var r = document.selection.createRange().duplicate();
          r.moveEnd('character', o.value.length)
          if (r.text == '') return o.value.length
          return o.value.lastIndexOf(r.text)
      } else return o.selectionStart
    }

 
      function formatNumnew(elt){
        var position = getCaretStart(elt);
        var currentVal = elt.value;
        var s1 = testCommas(currentVal);
        var testDecimal = testDecimals(currentVal);
        if (testDecimal.length > 1) {
            currentVal = currentVal.slice(0, -1);
        }
        elt.value = replaceCommas(currentVal); 
        
        var s2 = testCommas(elt.value);
        setCaret(elt, position + (s2 - s1));
  }
</script>
<html:form action="AddExeTTinDThauAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="ttindthau.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <logic:equal value="" name="ThongTinDauThauForm" property="guid">
          <fmt:message key="ttindthau.add.title"/>
        </logic:equal>
        <logic:notEqual value="" name="ThongTinDauThauForm" property="guid">
          <fmt:message key="ttindthau.update.title"/>
        </logic:notEqual>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.dot_dau_thau"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" onkeyup="format(this)"
                           onblur="textlostfocus(this);" tabindex="1"
                           styleId="dot_dau_thau" property="dot_dau_thau"
                           maxlength="8"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.dot_bo_sung"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="dot_bo_sung" property="dot_bo_sung"
                           maxlength="10"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ma_tpcp"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="ma_tpcp" styleId="ma_tpcp" style="color:red;"
                             styleClass="form-control selectpicker" tabindex="3"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             >
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="lstAllTPCP">
                    <html:optionsCollection name="lstAllTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select>
                <html:hidden property="ma_tpcp" styleId="ma_tpcp2"/>
              </div>
            </div>
          </div>
          
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.khoi_luong_tp"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" maxlength="20"
                           onblur="textlostfocus(this);" tabindex="4"
                           styleId="khoi_luong_tp" property="khoi_luong_tp"/>
              <html:hidden property="khoi_luong_tp_chu" styleId="khoi_luong_tp_chu"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2 a -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.loai_tien"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>  
              <div class="col-sm-7">
                <html:select property="loai_tien" styleId="loai_tien" 
                             styleClass="form-control selectpicker" tabindex="5"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="VND">VND</html:option>
                  <html:option value="USD">USD</html:option>
                </html:select>
                <html:hidden property="loai_tien" styleId="loai_tien2" />
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ky_han"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="ky_han" styleId="ky_han"   
                             styleClass="form-control selectpicker" tabindex="6"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" disabled="true">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="listKyHan">
                    <html:optionsCollection name="listKyHan" value="guid"
                                            label="name_ky_han"/>
                  </logic:notEmpty>
                </html:select>
                <html:hidden property="ky_han" styleId="ky_han2"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 3-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ngay_to_chuc_phanh"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_to_chuc_ph"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="7" onfocus="textfocus(this);" 
                             onblur="textlostfocus(this);" maxlength="10" onkeyup="doFormat(event)"
                             property="ngay_to_chuc_ph"/>
                   
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
                <fmt:message key="ttindthau.ngay_phat_hanh"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_ph"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);" maxlength="10" onkeyup="doFormat(event)"
                             onblur="textlostfocus(this);" tabindex="8"
                             property="ngay_ph"/>
                   
                  <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--Row 4-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ngay_thanh_toan_mua"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control"
                             styleId="ngay_tt_tien_mua"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="9" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" maxlength="10" onkeyup="doFormat(event)"
                             property="ngay_tt_tien_mua"/>
                   
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
                <fmt:message key="ttindthau.ky_tra_lai"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="ky_tra_lai" styleId="ky_tra_lai"
                             tabindex="10"
                             styleClass="form-control selectpicker"
                             >
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="0">Không trả lãi định kỳ</html:option>
                  <html:option value="1">Trả lãi định kỳ 12 tháng 1 lần</html:option>
                  <html:option value="2">Trả lãi định kỳ 6 tháng 1 lần</html:option>
                </html:select>
                <html:hidden property="ky_tra_lai" styleId="ky_tra_lai2" />
              </div>
            </div>
          </div>
        </div>
        <!--Row 5-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group" id="div_ngay_tt_lai_1">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ngay_tt_lai_1"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tt_lai_1"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="11" onfocus="textfocus(this);" 
                             onblur="textlostfocus(this);" maxlength="10" onkeyup="doFormat(event)"
                             property="ngay_tt_lai_1"/>
                   
                  <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group" id="div_ngay_tt_lai_2">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ngay_tt_lai_2"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tt_lai_2"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="12" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" maxlength="10" onkeyup="doFormat(event)"
                             property="ngay_tt_lai_2"/>
                   
                  <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--Row 6-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="lai_suat" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ngay_dao_han"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_dao_han"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="12" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" maxlength="10" onkeyup="doFormat(event)"
                             property="ngay_dao_han"/>
                   
                  <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="lai_suat" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.menh_gia"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onfocus="textfocus(this);" 
                           onblur="textlostfocus(this);" tabindex="14" maxlength="20"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="menh_gia" styleId="menh_gia"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 7-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="" class="col-sm-5 control-label">
                Hình thức ĐT trái phiếu
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="hinh_thuc_dt" styleId="hinh_thuc_dt"
                             styleClass="form-control selectpicker"
                             tabindex="15"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="01">Đấu thầu cạnh tranh l&atilde;i suất</html:option>
                  <html:option value="02">Đấu thầu kết hợp cạnh tranh l&atilde;i
                                          suất v&agrave; kh&ocirc;ng cạnh tranh
                                          l&atilde;i suất</html:option>
                </html:select>
                <html:hidden property="hinh_thuc_dt" styleId="hinh_thuc_dt2" />
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="" class="col-sm-5 control-label">
                Phương thức xác định KQĐT
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="pthuc_xacdinh_kqdt"
                             styleId="pthuc_xacdinh_kqdt" tabindex="16"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="01">Đơn gi&aacute;</html:option>
                  <html:option value="02">Đa gi&aacute;</html:option>
                </html:select>
                <html:hidden property="pthuc_xacdinh_kqdt" styleId="pthuc_xacdinh_kqdt2" />
              </div>
            </div>
          </div>
        </div>
        <!--Row 8-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="lai_suat" class="col-sm-5 control-label">
                TK nhận tiền mua TP<span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="17"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="20"
                           property="so_tk_nhan" styleId="so_tk_nhan"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ngay_tbao_goi_thau"/>&nbsp;    
                
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control"
                             styleId="ngay_tbao_goi_thau"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="18" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" maxlength="10" onkeyup="doFormat(event)"
                             property="ngay_tbao_goi_thau"/>
                   
                  <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--Row 9-->
        
        <div class="row">
        <logic:notEmpty name="ThongTinDauThauForm" property="dot_bo_sung">
         <logic:notEmpty name="kl_goi_thau_lan_dau">
     
        <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-5 control-label">
                Tổng KL đã công bố
            </label>
            <div class="col-sm-7">
                 <input type="text" id="kl_goi_thau_lan_dau" class="form-control" readonly="true" value="<%= request.getAttribute("kl_goi_thau_lan_dau")%>"/>
            </div>
          </div>
        </div>

      </logic:notEmpty>
         </logic:notEmpty>
         <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Khối lượng gọi thầu thêm
                &nbsp;
                <span class="red">(*)</span>
              </label>
              
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeyDT(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" maxlength="20" onchange="changemoney()"
                           onblur="textlostfocus(this);" 
                           styleId="khoi_luong_them" property="khoi_luong_them"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
         <div class="col-md-6">
            <div class="form-group" >
              <label for="hoten" class="col-sm-5 control-label">
                <logic:notEmpty name="ThongTinDauThauForm" property="dot_bo_sung">
                Lãi suất danh nghĩa
                </logic:notEmpty>
                <logic:empty name="ThongTinDauThauForm" property="dot_bo_sung">
                Lãi suất phát hành
                </logic:empty>
                            <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
              <logic:empty name="ThongTinDauThauForm" property="dot_bo_sung">
                <html:text styleClass="form-control" styleId="ls_danh_nghia"
                           onfocus="textfocus(this);" 
                           onkeypress="return validateFloatKeyPress(this,event);"
                           onblur="textlostfocus(this);" 
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_danh_nghia"/>
                </logic:empty>
                <logic:notEmpty name="ThongTinDauThauForm" property="dot_bo_sung">
                <html:text styleClass="form-control" styleId="ls_danh_nghia"
                           onfocus="textfocus(this);" readonly="true"
                           onkeypress="return validateFloatKeyPress(this,event);"
                           onblur="textlostfocus(this);" 
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_danh_nghia"/>
                </logic:notEmpty>
              </div>
              
            </div>
          </div>
        </div>
        <div class="row">
               <div class="col-md-6" >
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ghi_chu"/>
              </label>
              <div class="col-sm-7" >
                <html:textarea styleClass="form-control" style="width:736px;"
                
                               onfocus="textfocus(this);" readonly="true"
                               onblur="textlostfocus(this);" tabindex="19" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ghi_chu"/>
              </div>
            </div>
          </div>
          
        </div>
        
      </div>
    </div>
  </div>
  <div class="center">
   <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="i" tabindex="7" id="bfind">
     <span class="sortKey">I</span>n
    </button>
    <button type="button" class="btn btn-default" onclick="check('saveandsub')"
            accesskey="b" tabindex="7" id="bfind">
      Công <span class="sortKey">b</span>ố
    </button>

  
    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="7" id="bfind">
      Th<span class="sortKey">o</span>át
    </button>
  </div>
   
  <html:hidden styleId="khoi_luong_them_chu" property="khoi_luong_them_chu" value="" />
  <html:hidden property="trang_thai" styleId="trang_thai"/>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>