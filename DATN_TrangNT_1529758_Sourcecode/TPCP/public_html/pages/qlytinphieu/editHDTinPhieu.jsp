
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.HDBanTinPhieu"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<% 
       
  %>
<script type="text/javascript">
  function check(type) {      
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      if (type == 'save') {
          if (guid != null && guid != "") {
              document.getElementById("guid").value=guid;
              f.action = 'UpdateExeHDBanTinPhieuAction.do';
          }
          else {
              f.action = 'AddExcHDBanTinPhieuAction.do';
          }
          if (validateForm()) {
          $("#ky_han").removeAttr("disabled");
              f.submit();
          }
      }
      if(type=='saveandsub'){
      if(confirm("Bạn có muốn lưu và trình HD bán tín phiếu này không? ")){
         f.action='EditAndSubHDBanTinPhieuAction.do';
         if (validateForm()) {
         document.getElementById('trang_thai').value ="01";
         $("#ky_han").removeAttr("disabled");
              f.submit();
          }}
      }
      if (type == 'close') {
          f.action ='ListHDBanTinPhieuAction.do';
          
          f.submit();
      }
    }
  
    function parseDate(str) {
        var mdy = str.split("/");
        var m = toNumber(mdy[1]) -1;
        return new Date(mdy[2], m, mdy[0]);
    }
    
    function parseString(str){
      var mystr = str.split('/');
      var ms1=mystr[0];
      var ms2=mystr[1];
      if(!isNaN(parseInt(ms1,10)) &&  ms2!="HĐ" && ms1==null){
        return true;
      }
      return false;
    }
   function validateForm() {
      
      var ma_tp = $("#ma_tp").val();
      if (ma_tp == null || ma_tp == "") {
          alert('Bạn phải nhập Mã tín phiếu');
          $(".bs-searchbox").focus();
          return false;
      }
      
      var so_hd = $("#so_hd").val();
      if (so_hd == null || so_hd == "") {
          alert('Bạn phải nhập Số hợp đồng');
          $("#so_hd").focus();
          return false;
          
      }
      if(parseString(so_hd)){
          alert("Bạn phải nhập đúng định dạng : Số/HĐ");
          $("#so_hd").focus();
          return false;
      }
      var ngay_hd = $("#ngay_hd").val();
      if (ngay_hd == null || ngay_hd == "" ) {
          alert('Bạn phải nhập Ngày hợp đồng');
          $("#ngay_hd").focus();
          return false;
       }else if(!isDate(ngay_hd)){
        alert('Bạn đã nhập sai Ngày hợp đồng !');
        $("#ngay_hd").focus();
        return false;
        
      }
      // so sanh với current time
      
      var currentDay= new Date();
      var todayMonth= currentDay.getMonth()+1;
      var todayDay= currentDay.getDate();
      var todayYear= currentDay.getFullYear();
      var todayText= todayDay+"/"+todayMonth+"/"+todayYear;
      
      var todayToDate= parseDate(todayText).getTime();
       var dateNgay_hd = parseDate(ngay_hd).getTime();

      var kl_tp = $("#kl_tp").val();
      if (kl_tp == null || kl_tp == "" || kl_tp==0) {
          alert('Bạn phải nhập Khối lượng tín phiếu');
          $("#kl_tp").focus();
          return false;
      }
      var kl_tp_int =toNumber(kl_tp);
      if(kl_tp_int==0){
        alert('Bạn phải nhập Khối lượng tín phiếu');
          $("#kl_tp").focus();
          return false;
      }
      var lai_suat = $("#lai_suat").val();
      var lai_suat_int =toNumber(lai_suat);
      if(lai_suat_int==0){
         alert('Bạn phải nhập Lãi suất ');
         $("#lai_suat").val('');
          $("#lai_suat").focus();
          return false;
      }
      if (lai_suat == null || lai_suat == "" || lai_suat==0) {
          alert('Bạn phải nhập Lãi suất ');
          $("#lai_suat").focus();
          return false;
      }
        if(lai_suat==0){
        alert("Bạn phải nhập lãi suất lớn hơn 0");
         $("#lai_suat").focus();
         $("#lai_suat").val('');
          return false;
      }
      var ky_han = $("#ky_han").val();
      if (ky_han == null || ky_han == "") {
          alert('Bạn phải nhập Kỳ hạn');
          $(document).ready(function(){
            $("select :first").focus();
          });
          
          return false;
      }
      var gia_ban = $("#gia_ban").val();
      if (gia_ban == null || gia_ban == ""){
          alert('Bạn phải nhập Giá bán tín phiếu');
          $("#gia_ban").focus();
          return false;
      }
      var gia_ban_int =toNumber(gia_ban);
      if(gia_ban_int==0){
        alert('Bạn phải nhập giá bán lớn hơn 0');
          $("#gia_ban").focus();
          return false;
      }
       if(gia_ban ==0){
        alert("Bạn phải nhập giá bán lớn hơn 0");
          $("#gia_ban").focus();
          return false;
      }
      var ngay_ph = $("#ngay_ph").val();
      if (ngay_ph == null || ngay_ph == "") {
          alert('Bạn phải nhập Ngày phát hành');
          $("#ngay_ph").focus();
          return false;
      }
       if(!isDate(ngay_ph)){
        alert('Bạn đã nhập sai Ngày phát hành!');
        $("#ngay_ph").focus();
        return false;
      }

      var ngay_tt_tien_mua=$("#ngay_tt_tien_mua").val();
      if(ngay_tt_tien_mua == null || ngay_tt_tien_mua=="" ){
        alert('Bạn phải nhập Ngày Thanh Toán Tiền Mua');
        $("#ngay_tt_tien_mua").focus();
        return false;
      }
       if(!isDate(ngay_tt_tien_mua) ){
        alert('Bạn đã nhập sai Ngày thanh toán tiền mua !');
        $("#ngay_tt_tien_mua").focus();
        return false;
      }

      var ngay_dao_han=$("#ngay_dao_han").val();
      if(ngay_dao_han == null || ngay_dao_han == "" ){
        alert('Bạn phải nhập Ngày Đáo Hạn Tín Phiếu');
        $("#ngay_dao_han").focus();
        return false;
      }
      if(!isDate(ngay_dao_han)){
        alert('Bạn nhập sai Ngày đáo hạn');
        $("#ngay_dao_han").focus();
        return false;
      }
      // so sanh với current time
      var timeNgay_dao_han= parseDate(ngay_dao_han).getTime();   
      var time_ngay_ph = parseDate(ngay_ph).getTime();   
      if(timeNgay_dao_han < time_ngay_ph){
        alert('Ngày đáo hạn phải sau ngày phát hành!')
        $("#ngay_dao_han").focus();
        return false;
      }
      var tt_dky_lky=$("#tt_dky_lky").val();
      if(tt_dky_lky == null || tt_dky_lky == "" ){
        alert('Bạn phải nhập thông tin đăng ký');
        $("#tt_dky_lky").focus();
        return false;
      }
      if ( $.trim( $('#tt_dky_lky').val() ) == '' ){
         alert('Không được nhập khoảng trắng!');
          $("#tt_dky_lky").focus();
         return false
      }
      
      var dateNgay_ph =parseDate(ngay_ph).getTime();
      var dateNgay_tt_tien_mua=parseDate(ngay_tt_tien_mua).getTime();
      var dateNgay_dao_han = parseDate(ngay_dao_han).getTime();
     
      
      if(dateNgay_hd >dateNgay_dao_han){
        alert('Bạn phải nhập ngày đáo hạn lớn hơn ngày hợp đồng');
        $("#ngay_dao_han").focus();
        return false;
      }

      if(dateNgay_tt_tien_mua != dateNgay_ph){
        alert('Bạn phải nhập ngày thanh toán tiền mua bằng ngày phát hành ');
        $("#ngay_tt_tien_mua").focus();
        return false;
      }
      if(dateNgay_ph != dateNgay_hd){
        alert('Bạn phải nhập ngày phát hành bằng ngày hợp đồng');
        $("#ngay_ph").focus();
        return false;
      }
      
      return true;
  }
     function ChangeLs(elt){
      var ls_length= elt.value.length;
      var ls_value= elt.value;
      var ls_str = ls_value.split(",");
      if(ls_length==1){
        elt.value=elt.value+",00";
      }else if(ls_length==3){
        elt.value=elt.value+"0";
      }else if(ls_length == 2 && ls_str.length==1){
          elt.value=elt.value+",00";
      }else if(ls_length==4){
        elt.value=elt.value+"0";
      }
    }
    function isDate(txtDate)
    {
    var currVal = txtDate;
    if(currVal == '')
        return false;
    
    var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
    var dtArray = currVal.match(rxDatePattern); // is format OK?
    
    if (dtArray == null) 
        return false;
    
    //Checks for mm/dd/yyyy format.
    dtDay = dtArray[1];
    dtMonth= dtArray[3];
    dtYear = dtArray[5];
    var checkday= new Date(dtYear,dtMonth,dtDay).getTime();
    var nowDay = new Date().getTime();
    
//    if(checkday < nowDay){
//      return false;
//    }
    if (dtMonth < 1 || dtMonth > 12) 
        return false;
    else if (dtDay < 1 || dtDay> 31) 
        return false;
    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31) 
        return false;
    else if (dtMonth == 2) 
    {
        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
        if (dtDay> 29 || (dtDay ==29 && !isleap)) 
                return false;
    }
    return true;
    }
  function change_ma_tp(sel) {
      var guid_ma_tp = sel.value;
       if(guid_ma_tp.trim()==""){
        document.getElementById("ky_han").value="";
        $(".selectpicker").selectpicker('refresh');
      }else{
      $.ajax( {
            type : "post", 
            url : "GetAjaxKyHanHDAction.do",
            data :  { longid : guid_ma_tp  },             
               async : true, cache : false,  success : function (data) {
              var obj = JSON.parse(data);
              document.getElementById("ky_han").value = toNumber(obj.ky_han);
              $('.selectpicker').selectpicker('refresh');
              var Ngay_hd= $("#ngay_hd").val();  
              if(Ngay_hd!=null || Ngay_hd!="" || Ngay_hd != "undefined"){
              var Guid_Ky_han = toNumber(obj.ky_han);
              var timeKy_han = null;
              if(Guid_Ky_han == 17){
                timeKy_han = 52*7*86400000;
              }else if(Guid_Ky_han == 16){
                timeKy_han = 26*7*86400000;
              }else if(Guid_Ky_han ==10){
                timeKy_han =13*7*86400000;
              }
              if(timeKy_han != null ){
                  var timeNgay_hd = parseDate(Ngay_hd).getTime() + timeKy_han;
                  var d = new Date(timeNgay_hd);
                  var day = d.getDate();
                  var month = d.getMonth()+1 ;
                  var year = d.getFullYear();
                  var ngay_dao_han= day +"/"+month+"/"+year;
                  document.getElementById("ngay_dao_han").value=ngay_dao_han;
              }
              }
          }
      });}
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
    function dateChanged(ev){
    $(this).datepicker('hide');
    var guid = $("#guid").val();
    if($(this).val()!=""){    
        if(guid == null || guid ==""){
          var date = parseDate($(this).val());
            var date2 = getFormattedDate(date);
            document.getElementById("ngay_tt_tien_mua").value = date2;
            document.getElementById("ngay_ph").value = date2;
        }
      } 
  }
  $(function () {
      $("#lai_suat").keyup(function (event) {
          formatNumnew(event.target);
      });
      $("#ma_tp").attr("data-live-search",true);     
      $("#ma_tp").focus();
      $("#ngay_hd").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_to_chuc_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tt_tien_mua").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_dao_han").datepicker( {
          dateFormat : "dd/mm/yy"
      });

       $("#kl_tp").keyup(function (event) {
          formatNumnew(event.target);
      });
      
      $("#gia_ban").keyup(function (event) {
          formatNumnew(event.target);
      });
      
      document.getElementById("ky_han").value = '<bean:write name="HDBanTinPhieuForm" property="ky_han"/>';
      
      document.getElementById("ma_tp").value = '<bean:write name="HDBanTinPhieuForm" property="ma_tp"/>';
      
      $('.selectpicker').selectpicker('refresh');
      $("#so_hd").focus();
  });
      
      $("#ngay_hd").on("change",function(){
        var id=$(this).attr("id");        
       });
        $("#ngay_hd").datepicker( {
          dateFormat : "dd/mm/yy"
      });
    //  .change(dateChanged).on('changeDate', dateChanged);
      $("#ngay_ph").on("change",function(){
        var id=$(this).attr("id");        
       });
      $("#ngay_tt_tien_mua").on("change",function(){
        var id=$(this).attr("id");        
      });
      $("#ngay_dao_han").on("change",function(){
        var id=$(this).attr("id");        
      });
      
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
    function getSelectionStart(o) {
        if (o.createTextRange) {
         var r = document.selection.createRange().duplicate();
          r.moveEnd('character', o.value.length)
          if (r.text == '') return o.value.length
          return o.value.lastIndexOf(r.text)
      } else return o.selectionStart
    }
    function ngay_dao_hanChange() {
      var Ngay_hd= $("#ngay_hd").val();
      document.getElementById("ngay_ph").value=Ngay_hd;
      document.getElementById("ngay_tt_tien_mua").value=Ngay_hd;
      if(Ngay_hd!=null || Ngay_hd!="" || Ngay_hd != "undefined"){
      var Guid_Ky_han = $("#ky_han").val();
      var timeKy_han = null;
      if(Guid_Ky_han == 17){
        timeKy_han = 52*7*86400000;
      }else if(Guid_Ky_han == 16){
        timeKy_han = 26*7*86400000;
      }else if(Guid_Ky_han ==10){
        timeKy_han =13*7*86400000;
      }
      if(timeKy_han != null ){
          var timeNgay_hd = parseDate(Ngay_hd).getTime() + timeKy_han;
          var d = new Date(timeNgay_hd);
         
          var date2 = getFormattedDate(d);
          var day = d.getDate();
          var month = d.getMonth()+1 ;
          var year = d.getFullYear();
          var ngay_dao_han= day +"/"+month+"/"+year;
          document.getElementById("ngay_dao_han").value=date2;
      }
      }
    }
  function getFormattedDate(date) {
      var year = date.getFullYear();
    
      var month = (1 + date.getMonth()).toString();
      month = month.length > 1 ? month : '0' + month;
    
      var day = date.getDate().toString();
      day = day.length > 1 ? day : '0' + day;
      
      return  day+ '/' + month + '/' + year;
}



     
</script>
<html:form action="AddHDBanTinPhieuAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="hdbantinphieu.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <logic:equal value="" name="HDBanTinPhieuForm" property="guid">
          <fmt:message key="hdbantinphieu.add.title"/>
        </logic:equal>
        <logic:notEqual value="" name="HDBanTinPhieuForm" property="guid">
          <fmt:message key="hdbantinphieu.update.title"/>
          <html:hidden name="HDBanTinPhieuForm" property="guid"/>
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
                <fmt:message key="hdbantinphieu.so_hd"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           tabindex="1"
                           styleId="so_hd" property="so_hd" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.ma_tp"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select  property="ma_tp" styleId="ma_tp"
                             styleClass="form-control selectpicker" tabindex="3"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onchange="change_ma_tp(this)">
                  <html:option value="">Vui Lòng chọn</html:option>
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
                <fmt:message key="hdbantinphieu.ngay_hd"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_hd"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="ngay_dao_hanChange()"
                             tabindex="4" onfocus="textfocus(this);" onkeyup="doFormat(event)"
                             onblur="textlostfocus(this);" property="ngay_hd" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_hd"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.kl_tp"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="5"
                           styleId="kl_tp" property="kl_tp"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 2 a -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.lai_suat"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return validateFloatKeyPress(this,event);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="ChangeLs(this);" tabindex="6"
                           styleId="lai_suat" property="lai_suat"
                           maxlength="5"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.ky_han"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="ky_han" styleId="ky_han"
                             styleClass="form-control selectpicker" tabindex="7"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" disabled="true" >
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
        <!--Row 3-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.gia_ban"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" styleId="gia_ban"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="8"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="gia_ban"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.ngay_ph"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_ph"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             style="background-color: rgb(255, 255, 255); margin: 0px; width: 228px; height: 25px;"
                             tabindex="9" onfocus="textfocus(this);" onkeyup="doFormat(event)"
                             onblur="textlostfocus(this);" property="ngay_ph" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_ph" style="width: 33px; height: 25px;"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
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
                <fmt:message key="hdbantinphieu.ngay_tt_tien_mua"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control"
                             styleId="ngay_tt_tien_mua"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="10" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" onkeyup="doFormat(event)"
                             property="ngay_tt_tien_mua" maxlength="10"/>
                   
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
                <fmt:message key="hdbantinphieu.ngay_dao_han"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_dao_han"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             style="background-color: rgb(255, 255, 255); margin: 0px; width: 228px; height: 25px;"
                             tabindex="10" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" onkeyup="doFormat(event)"
                             property="ngay_dao_han" maxlength="10"/>
                   
                  <label class="input-group-addon" for="ngay_dao_han" style="width: 33px; height: 25px;"> 
                    <span class="glyphicon glyphicon-calendar" ></span>
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
                <fmt:message key="hdbantinphieu.tt_dky_lky"/>
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" styleId="tt_dky_lky"
                           onfocus="textfocus(this);"
                           style="height: 44px;"
                           onblur="textlostfocus(this);" tabindex="4" 
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="tt_dky_lky"/>
              </div>
            </div>
          </div>
          <logic:notEmpty name="HDBanTinPhieuForm" property="ly_do_tu_choi"> 
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.ly_do_tu_choi"/>
              </label>
              <div class="col-sm-7">
                <html:textarea styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" readonly="true"
                           onblur="textlostfocus(this);" tabindex="11"
                           styleId="ly_do_tu_choi" property="ly_do_tu_choi" 
                           
                           />
              </div>
            </div>
        </div>
        </logic:notEmpty>
        </div>
        <!--Row 5-->
        <!--Row 6-->
        <!--Row 7-->
        <!--Row 8-->
        <!--Row 9-->
      </div>
    </div>
  </div>
  <div class="center">

        <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g" tabindex="7" id="bfind">
      <span class="sortKey">G</span>hi
    </button>
    <button type="button" class="btn btn-default" onclick="check('saveandsub')"
            accesskey="v" tabindex="7" id="bfind">
      Ghi <span class="sortKey">v</span>à trình
    </button>
    <button type="button" class="btn btn-default" onclick="check('close')"
              accesskey="o" tabindex="8">
        Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden property="trang_thai" styleId="trang_thai"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>