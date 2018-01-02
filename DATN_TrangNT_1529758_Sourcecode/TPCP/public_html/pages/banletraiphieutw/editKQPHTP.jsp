<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.BanLeTraiPhieuTwResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<% 
      String sKytralai = (String)request.getAttribute("kh");
  %>
<script type="text/javascript">
   var check1="No";
   function changeMenh_gia(){
    var loai_tien = $("#loai_tien").val();
    if(loai_tien == 'VND'){
      document.getElementById("menh_gia").value= "100.000";
    }else if(loai_tien =='USD'){
      document.getElementById("menh_gia").value= "100";
    }else {
      document.getElementById("menh_gia").value= "";
    }
  }
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      
      if (type == 'save') {
          if (guid != null && guid != "") {
              f.action = 'UpdateExeBanLeTraiPhieuTwAction.do';
          }
          else {
            
              f.action = 'AddExeBanLeTraiPhieuTwAction.do';
          }
          if (validateForm()) {
             $("#ky_han").removeAttr("disabled");
             $("#loai_tien").removeAttr("disabled");
             $("#ky_tt_lai").removeAttr("disabled");
              $("#ngay_tt_lai_1 ").datepicker('enable');
              $("#ngay_tt_lai_2 ").datepicker('enable');
              $("#ngay_dao_han ").datepicker('enable');
              $("#ma_tpcp").removeAttr("disabled");
              $('#extenstion').show();
               $('#ma_dvi_so_huu_1').attr("disabled", false);
              f.submit();
          }
      }else if(type=='close'){
        f.action='ListBanLeTraiPhieuTwAction.do';
        f.submit();
      }
      if(type=='saveandsubmit'){
          if (guid != null && guid != "") {
            if (validateForm()) {
             document.getElementById('trang_thai').value= '01';
             $("#ky_han").removeAttr("disabled");
             $("#loai_tien").removeAttr("disabled");
             $("#ky_tt_lai").removeAttr("disabled");
             $("#ngay_tt_lai_1 ").datepicker('enable');
             $("#ngay_tt_lai_2 ").datepicker('enable');
             $("#ngay_dao_han ").datepicker('enable');
             $("#ma_tpcp").removeAttr("disabled");
             $('#extenstion').show();
             $('#ma_dvi_so_huu_1').attr("disabled", false);
              if(confirm("Bạn có muốn ghi và trình kết quả bán lẻ trái phiếu này không ?")){
              f.action = 'UpdateExeBanLeTraiPhieuTwAction.do';
               f.submit();
           }
          }
         
          }
          else {
              
              f.action = 'AddExeBanLeTraiPhieuTwAction.do';
          }
      }

  }
 function format(elt){
    var currentVal = elt.value;
    if(currentVal.length ==3){
      elt.value = elt.value +"/";
    }
  }
    function validateFloatKeyPress(el, evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        
        if (charCode != 44 && charCode > 31 && (charCode < 48 || charCode > 57)&& charCode !=47) {
            return false;
        }   
        if(charCode ==47){
          var str = el.value;
          if(str.length==1){
            str="0"+"0"+str;
            el.value=str;
          }else if(str.length==2){
            str="0"+str;
            el.value=str;
          }else if(str.length >= 4){
            
            return false;
          }
        }
        return true;
  }
  var ngay_ph_cu="";
   function loadFunction(){     
       var guid123 = $("#ma_tpcp").val();
       var dot_bs =$("#dot_bo_sung").val();
        if (guid123 != null && guid123 != "") {
           if(guid123.substr(0,4)=='TWBH'){
          $('#ma_dvi_so_huu_1').val('BH'); 
          $('#ma_dvi_so_huu_1').change();
          $('#ma_dvi_so_huu_1').attr("disabled", true);
         $('#extenstion').hide();
        }else if(guid123.substr(0,4)=='TWSC'){
           $('#extenstion').hide();
          $('#ma_dvi_so_huu_1').val('SCIC'); 
          $('#ma_dvi_so_huu_1').change();
          $('#ma_dvi_so_huu_1').attr("disabled", true);
        }else{
          $('#extenstion').show();     // hiển thị các nút thêm xóa dòng
          $('#ma_dvi_so_huu_1').attr("disabled", false);
        }  
        if(dot_bs!=""){
        check1="yes";
          $("#loai_tien").attr("disabled",true);
          $("#ma_tpcp").attr("disabled",true);
          $("#ky_tt_lai").attr("disabled",true);
          $("#menh_gia").attr("readonly",true);
          $("#ngay_tt_lai_1").datepicker('disable');
          $("#ngay_tt_lai_2").datepicker('disable');
          $("#ngay_dao_han").datepicker('disable');
          $("#ngay_tt_lai_1").attr("readonly",true);
          $("#ngay_tt_lai_2").attr("readonly",true);
          $("#ngay_dao_han").attr("readonly",true);
          $("#lai_suat").attr("readonly",true);
            $.ajax( {
          type : "post", url : "GetAjaxKyHanBLAction.do", data :  {
              longid : dot_bs
          },
          async : true, cache : false, success : function (data) {
              var obj = JSON.parse(data);
              ngay_ph_cu= obj.ngay_ph;
              
              
              
          }
      });
        }
//        if(dot_bs!= ""){                                                                      
//        $.ajax( {
//        
//          type : "post",
//          url : "GetAjaxKyHanBLAction.do", 
//          data :  { longid : dot_bs },
//          async : true, cache : false, success : function (data) {
//              var obj = JSON.parse(data);
//              if(obj.allowEdit=="false"){
//               if(obj.ky_tt_lai== "1"){
//                check1="Yes";
//                $("#div_ngay_tt_lai_1").show();
//                $("#div_ngay_tt_lai_2").hide();
//                $("#menh_gia").val(obj.menh_gia);
//                $("#lai_suat").val(obj.lai_suat);
//                $("#menh_gia").attr("readonly", true);
//                $("#lai_suat").attr("readonly", true);
//                $("#ngay_dao_han").attr("readonly",true);
//                $("#loai_tien").attr("disabled",true);
//                $("#ngay_dao_han ").datepicker('disable');
//                $("#ngay_tt_lai_1 ").datepicker('disable');
//                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
//                document.getElementById("loai_tien").value=obj.loai_tien;
//                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
//                $('.selectpicker').selectpicker('refresh');
//                $("#ky_tt_lai").attr("disabled",true);
//              }else if(obj.ky_tt_lai== 2){
//              check1="Yes";
//               $("#div_ngay_tt_lai_1").show();
//                $("#div_ngay_tt_lai_2").show();   
//                $("#menh_gia").val(obj.menh_gia);
//                $("#lai_suat").val(obj.lai_suat);
//                $("#menh_gia").attr("readonly", true);
//                $("#lai_suat").attr("readonly", true);
//                $("#ngay_tt_lai_1 ").datepicker('disable');
//                $("#ngay_dao_han ").datepicker('disable');
//                $("#ngay_tt_lai_2 ").datepicker('disable');
//                $("#ngay_dao_han").attr("readonly",true);
//                $("#loai_tien").attr("disabled",true);
//                $("#ky_tt_lai").attr("disabled",true);
//                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
//                document.getElementById("ngay_tt_lai_2").value=obj.ngay_tt_lai_2;
//                document.getElementById("loai_tien").value=obj.loai_tien;
//                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
//                $('.selectpicker').selectpicker('refresh');
//
//              }else if(obj.ky_tt_lai== "0"){
//              check1="Yes";
//              $("#menh_gia").val(obj.menh_gia);
//                $("#lai_suat").val(obj.lai_suat);
//                $("#menh_gia").attr("readonly", true);
//                $("#lai_suat").attr("readonly", true);
//               $("#div_ngay_tt_lai_1").hide();
//                $("#div_ngay_tt_lai_2").hide();   
//                $("#ngay_tt_lai_1 ").datepicker('enable');
//                 $("#ngay_tt_lai_2 ").datepicker('enable');
//                 $("#ngay_dao_han ").datepicker('enable');
//                $("#ngay_dao_han").attr("readonly",true);
//                $("#loai_tien").attr("disabled",true);
//                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
//                document.getElementById("ngay_tt_lai_2").value=obj.ngay_tt_lai_2;
//                document.getElementById("loai_tien").value=obj.loai_tien;
//                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
//                $('.selectpicker').selectpicker('refresh');
//                $("#ky_tt_lai").attr("disabled",true);
//              }else {
//                
//                $("#menh_gia").attr("readonly", false);
//                $("#lai_suat").attr("readonly", false);
//                
//                $("#ngay_tt_lai_1 ").datepicker('enable');
//                $("#ngay_tt_lai_1 ").attr("readonly", false);
//                $("#ngay_tt_lai_2 ").datepicker('enable');
//                $("#ngay_dao_han ").datepicker('enable');
//                $("#ngay_dao_han").attr("readonly",false);
//                
//            
//                    
//                $('.selectpicker').selectpicker('refresh');
//                $("#ky_tt_lai").removeAttr("disabled",true);
//                $('#ky_tt_lai').selectpicker('setStyle', 'disabled', 'remove');
//                $("#ky_tt_lai").selectpicker();
//                
//                $("#loai_tien").removeAttr("disabled",true);
//                $('#loai_tien').selectpicker('setStyle', 'disabled', 'remove');
//                $("#loai_tien").selectpicker();
//              }
//              
//              }else{
//              // allow is true
//                if(obj.ky_tt_lai== "1"){
//                check1="Yes";
//                $("#div_ngay_tt_lai_1").show();
//                $("#div_ngay_tt_lai_2").hide();
//                $("#ngay_dao_han").attr("readonly",false);
//                $("#loai_tien").attr("disabled",false);
//                $("#ngay_dao_han ").datepicker('enable');
//                $("#ngay_tt_lai_1 ").datepicker('enable');
//                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
//                document.getElementById("loai_tien").value=obj.loai_tien;
//                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
//                $('.selectpicker').selectpicker('refresh');
//                $("#ky_tt_lai").attr("disabled",false);
//                $("#menh_gia").val(obj.menh_gia);
//                $("#lai_suat").val(obj.lai_suat);
//                $("#menh_gia").attr("readonly", false);
//                $("#lai_suat").attr("readonly", false);
//              }else if(obj.ky_tt_lai== 2){
//              check1="Yes";
//               $("#div_ngay_tt_lai_1").show();
//                $("#div_ngay_tt_lai_2").show();   
//                $("#ngay_tt_lai_1 ").datepicker('enable');
//                $("#ngay_dao_han ").datepicker('enable');
//                $("#ngay_tt_lai_2 ").datepicker('enable');
//                $("#ngay_dao_han").attr("readonly",false);
//                $("#loai_tien").attr("disabled",false);
//                $("#ky_tt_lai").attr("disabled",false);
//                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
//                document.getElementById("ngay_tt_lai_2").value=obj.ngay_tt_lai_2;
//                document.getElementById("loai_tien").value=obj.loai_tien;
//                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
//                $('.selectpicker').selectpicker('refresh');
//                $("#menh_gia").val(obj.menh_gia);
//                $("#lai_suat").val(obj.lai_suat);
//                $("#menh_gia").attr("readonly", false);
//                $("#lai_suat").attr("readonly", false);
//              }else if(obj.ky_tt_lai== "0"){
//              check1="Yes";
//                $("#menh_gia").val(obj.menh_gia);
//                $("#lai_suat").val(obj.lai_suat);
//                $("#menh_gia").attr("readonly", true);
//                $("#lai_suat").attr("readonly", true);
//                $("#div_ngay_tt_lai_1").hide();
//                $("#div_ngay_tt_lai_2").hide();   
//                $("#ngay_tt_lai_1 ").datepicker('enable');
//                $("#ngay_tt_lai_2 ").datepicker('enable');
//                $("#ngay_dao_han ").datepicker('enable');
//                $("#ngay_dao_han").attr("readonly",false);
//                $("#loai_tien").attr("disabled",false);
//                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
//                document.getElementById("ngay_tt_lai_2").value=obj.ngay_tt_lai_2;
//                document.getElementById("loai_tien").value=obj.loai_tien;
//                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
//                $('.selectpicker').selectpicker('refresh');
//                $("#ky_tt_lai").attr("disabled", false);
//              }else {
//              
//        
//                $("#ngay_tt_lai_1 ").datepicker('enable');
//                $("#ngay_tt_lai_1 ").attr("readonly", false);
//                $("#ngay_tt_lai_2 ").datepicker('enable');
//                $("#ngay_dao_han ").datepicker('enable');
//                $("#ngay_dao_han").attr("readonly",false);
//                $("#menh_gia").attr("readonly", false);
//                $("#lai_suat").attr("readonly",false);
//                $('.selectpicker').selectpicker('refresh');
//                $("#ky_tt_lai").removeAttr("disabled",true);
//                $('#ky_tt_lai').selectpicker('setStyle', 'disabled', 'remove');
//                $("#ky_tt_lai").selectpicker();
//                
//                $("#loai_tien").removeAttr("disabled",true);
//                $('#loai_tien').selectpicker('setStyle', 'disabled', 'remove');
//                $("#loai_tien").selectpicker();
//              }
//              }
//              
//          }
//        } );}
         }
  }
  
  window.onload = loadFunction;
  function validateForm() {
      var dot_ph = $("#dot_ph").val();
      if (dot_ph.trim() == null || dot_ph.trim() == "") {
          alert('Bạn phải nhập đợt phát hành !');
          $("#dot_ph").focus();
          return false;
      }
      var str_dot_ph = dot_ph.split("/");
    if(str_dot_ph.length==2){
      if(str_dot_ph[0]==0){
         alert("Bạn không được phép nhập : 000/0000 , xxx/0000 ,000/xxxx!");
          $("#dot_ph").focus();
          return false;
        }
        if(str_dot_ph[1]==0){
            alert("Bạn không được phép nhập : 000/0000 , xxx/0000 ,000/xxxx!");
          $("#dot_ph").focus();
          
          return false;
        }
      }
      var ma_tpcp = $("#ma_tpcp").val();
      if (ma_tpcp == null || ma_tpcp == "") {
          alert('Bạn phải chọn mã TPCP !');
          $("#ma_tpcp").focus();
          return false;
      }
      // check dot_ph 
      var numberPattern = /\d+/g;
      var a_ma_tpcp = ma_tpcp.match(numberPattern); // get number from ma_tpcp   
      var str =dot_ph.split("/"); // get str from dot_ph
//      var numberYear = a_ma_tpcp[0].substring(0,2);
//      var strElement= str[1].substring(2,4);
      if(str.length  > 2 || str.length < 2){
        alert("Bạn nhập đợt phát hành không đúng định dạng !");
        $("#dot_ph").focus();
        return false;
      }
//      else if(strElement != numberYear){
//          alert("Bạn đã nhập đợt phát hành không trùng với năm phát hành tín phiếu !");
//          $("#dot_ph").focus();
//          return false;
//      }
      
      var menh_gia = $("#menh_gia").val();
      if (menh_gia == null || menh_gia == "") {
          alert('Bạn phải nhập mệnh giá !');
          $("#menh_gia").focus();
          return false;
      }
      if(menh_gia==0){
        alert('Bạn phải nhập mệnh giá  lớn hơn không!');
          $("#menh_gia").focus();
          return false;
      }
      var menh_gia_int= toNumber(menh_gia);
      if(menh_gia_int==0){
        alert("Bạn phải nhập mệnh giá lớn hơn 0");
        $("#menh_gia").focus();
        return false;
      }
      var ky_han = $("#ky_han").val();
      if (ky_han == null || ky_han == "") {
          alert('Bạn phải chọn kỳ hạn !');
          
          return false;
      }
      var ngay_ph = $("#ngay_ph").val();
      if (ngay_ph == null || ngay_ph == "") {
          alert('Bạn phải nhập ngày phát hành !');
          $("#ngay_ph").focus();
          return false;
      }
      else {
          var checkDate = validatedate(document.getElementById("ngay_ph"));
          if (!checkDate) {
              return false;
          }
      }
      
      var ngay_dao_han = $("#ngay_dao_han").val();
      if (ngay_dao_han == null || ngay_dao_han == "") {
          alert('Bạn phải nhập ngày đáo hạn !');
          $("#ngay_dao_han").focus();
          return false;
      }
      else {
          var startDate = parseDate(ngay_ph).getTime();
          var endDate = parseDate(ngay_dao_han).getTime();
          if (startDate > endDate) {
              alert('Ngày đáo hạn phải lớn hơn ngày phát hành !');
              $("#ngay_dao_han").focus();
              return false;
          }
      }
      
      var checkDate_ngay_dao_han = validatedate(document.getElementById("ngay_dao_han"));
      if (!checkDate_ngay_dao_han) {
          return false;
      }
      
      var ngay_thanh_toan = $("#ngay_tt_tien_mua").val();
      if (ngay_thanh_toan == null || ngay_thanh_toan == "") {
          alert('Bạn phải nhập ngày thanh toán tiền mua trái phiếu !');
          $("#ngay_tt_tien_mua").focus();
          return false;
      }
      else if (ngay_ph != ngay_thanh_toan) {
          alert('Ngày phát hành phải bằng ngày thanh toán tiền mua TP !');
          $("#ngay_tt_tien_mua").focus();
          return false;
      }
      var checkDate_ngay_tt_tien_mua = validatedate(document.getElementById("ngay_tt_tien_mua"));
      if (!checkDate_ngay_tt_tien_mua) {
          return false;
      }
      var lai_suat = $("#lai_suat").val();
      if (lai_suat == null || lai_suat == "") {
          alert('Bạn phải nhập lãi suất !');
          $("#lai_suat").focus();
          return false;
      }
      else if (lai_suat.length > 5){
          alert('Lãi suất nhập tối đa 4 ký tự !');
          $("#lai_suat").focus();
          return false;
      }
      var lai_suat_int= toNumber(lai_suat);
        if(lai_suat_int==0){
          alert('Bạn phải nhập lãi suất lớn hơn 0');
            $("#lai_suat").focus();
            return false;
        }
        if(lai_suat==0){
          alert('Lãi suất lớn hơn 0 !');
            $("#lai_suat").focus();
            return false;
        }
          var so_luong_tp = $("#so_luong").val();
    
    if(so_luong_tp==0){
       alert("Bạn phải nhập số lượng trái phiếu lớn hơn 0");
       $("#sl_dky_mua_1").focus();
       return false;
    }
     var so_luong_tp_int= toNumber(so_luong_tp);
      if(so_luong_tp_int==0){
        alert('Bạn phải nhập số lượng trái phiếu lớn hơn 0');
          $("#sl_dky_mua_1").focus();
             return false;
      }
      var v_ngay_tt_lai_1 = $("#ngay_tt_lai_1").val();
      var v_ngay_tt_lai_2 = $("#ngay_tt_lai_2").val();
      var ky_tra_lai1 = $("#ky_tt_lai").val();
      if (ky_tra_lai1 != 0) {
          var ngay_tt_lai_1 = $("#ngay_tt_lai_1").val();
          if (ngay_tt_lai_1 == null || ngay_tt_lai_1 == "") {
              alert('Bạn phải nhập ngày thanh toán lãi lần 1');
              $("#ngay_tt_lai_1").focus();
              return false;
          }
      }
      if (ky_tra_lai1 == 2) {
          var ngay_tt_lai_2 = $("#ngay_tt_lai_2").val();
          if (ngay_tt_lai_2 == null || ngay_tt_lai_2 == "") {
              alert('Bạn phải nhập ngày thanh toán lãi lần 2');
              $("#ngay_tt_lai_2").focus();
              return false;
          }
      }
          if(ngay_ph_cu!=""){
          var time_ngay_tt_lai_1= parseDate(ngay_ph_cu).getTime();
          var time_ngay_tt= parseDate(ngay_ph).getTime();
          if(time_ngay_tt_lai_1 >time_ngay_tt ){
            alert("Bạn phải nhập ngày phát hành lớn hơn ngày phát hành của đợt bổ sung");
             $("#ngay_ph").focus();
             return false;
          }
      }
      if (v_ngay_tt_lai_1 != "" && v_ngay_tt_lai_1 != null && v_ngay_tt_lai_1 != "undefined") {
          var checkDate_ngay_tt_lai_1 = validatedate(document.getElementById("ngay_tt_lai_1"));
          if (!checkDate_ngay_tt_lai_1) {
              return false;
          }
          var time_ngay_tt_lai_1_2=parseDate(v_ngay_tt_lai_1).getTime();
          var time_ngay_tt_12= parseDate(ngay_thanh_toan).getTime();
          if(check1=="No"){
          if(time_ngay_tt_12 >= time_ngay_tt_lai_1_2){
            alert("Bạn phải nhập ngày thanh toán tiền mua nhỏ hơn ngày trả lãi lần 1");
            $("#ngay_tt_tien_mua").focus();
            return false;
          }
          var time_ngay_dao_han = parseDate(ngay_dao_han).getTime();
            if(time_ngay_dao_han <= time_ngay_tt_lai_1){
            alert("Bạn phải nhập ngày đáo hạn lớn hơn ngày trả lãi lần 1");
            $("#ngay_dao_han").focus();
            return false;
          }
          }
          
      }
      
      if (v_ngay_tt_lai_2 != "" && v_ngay_tt_lai_2 != null) {
          var checkDate_ngay_tt_lai_2 = validatedate(document.getElementById("ngay_tt_lai_2"));
          if (!checkDate_ngay_tt_lai_2) {
              return false;
          }
      }
      if ((v_ngay_tt_lai_1 != null && v_ngay_tt_lai_1 != "") && (v_ngay_tt_lai_2 != null && v_ngay_tt_lai_2 != "")) {
          var d_ngay_tt_lai_1 = parseDate(v_ngay_tt_lai_1).getTime();
          var d_ngay_tt_lai_2 = parseDate(v_ngay_tt_lai_2).getTime();
          if(check1=="No"){
          if (d_ngay_tt_lai_1 > d_ngay_tt_lai_2) {
              alert('Ngày thanh toán lần 1 phải nhỏ hơn ngày thanh toán lãi lần 2. !');
              $("#ngay_tt_lai_2").focus();
              return false;
          }
           var time_ngay_dao_han1 = parseDate(ngay_dao_han).getTime();
            if(time_ngay_dao_han1 <= d_ngay_tt_lai_2){
            alert("Bạn phải nhập ngày đáo hạn hơn ngày trả lãi lần 2");
            $("#ngay_dao_han").focus();
            return false;
          }
          }
      }    
      var ky_tt_lai = $("#ky_tt_lai").val();
      if (ky_tt_lai == null || ky_tt_lai == "") {
          alert('Bạn phải nhập kỳ trả lãi !');
          $("#ky_tt_lai").focus();
          return false;
      }
      var ma_dvi_so_huu = document.getElementsByName("ma_dvi_so_huu");
      var sl_dky_mua = document.getElementsByName("sl_dky_mua");
      var so_tien_tt = document.getElementsByName("so_tien_tt");
      if (ma_dvi_so_huu != null && ma_dvi_so_huu.length > 0) {
          for (var i = 0;i < ma_dvi_so_huu.length;i++) {
              if (sl_dky_mua[i].value == "") {
                  alert('Bạn phải nhập số lượng trái phiếu !');
                  sl_dky_mua[i].focus();
                  return false;
              }
              if (so_tien_tt[i].value == "") {
                  alert('Bạn phải nhập số tiền thanh toán !');
                  so_tien_tt[i].focus();
                  return false;
              }

          }
      }
      var time_ngay_ph = parseDate(ngay_ph).getTime();
      
     if( v_ngay_tt_lai_1 != null && v_ngay_tt_lai_1 != "" ){
     if(check1=="No"){
      var time_ngay_tt_lai_12 =parseDate(v_ngay_tt_lai_1).getTime();
      if(time_ngay_tt_lai_12 <= time_ngay_ph){
          alert("Bạn phải nhập ngày thanh toán lãi 1 lớn hơn ngày phát hành !");
          $("#ngay_tt_lai_1").focus();
          return false;
      }
     }
     }

    
      
      return true;
  }
  var ngay_to_chuc_ph_cu="";
 function parseDate(str) {
          var mdy = str.split("/");
          var m = toNumber(mdy[1]) -1;
          return new Date(mdy[2], m, mdy[0]);
      }
     function getFormattedDate(date) {
      var year = date.getFullYear();
    
      var month = (1 + date.getMonth()).toString();
      month = month.length > 1 ? month : '0' + month;
    
      var day = date.getDate().toString();
      day = day.length > 1 ? day : '0' + day;
      
     return  day+ '/' + month + '/' + year;
}
  function calculateSum() {      
      var sum_sl_dky_mua = 0;
      var sum_kl_dky_mua = 0;
      var sum_so_tien_tt = 0;
      var menh_gia = $("#menh_gia");
      var menh_gia_val = 0;
      if(menh_gia!=null || menh_gia!= undefined){
        menh_gia_val = toNumber(menh_gia.val());
      }
      var sl_dky_mua = document.getElementsByName("sl_dky_mua");
      var kl_dky_mua = document.getElementsByName("kl_dky_mua");
      var so_tien_tt = document.getElementsByName("so_tien_tt");      
      
      if (sl_dky_mua != null && sl_dky_mua.length > 0) {
          //iterate through each textboxes and add the values
          for (var i = 0;i < sl_dky_mua.length;i++) {
              //add only if the value is number
              sum_sl_dky_mua += toNumber(sl_dky_mua[i].value);
              var kl_dky_mua_val = toNumber(sl_dky_mua[i].value) * menh_gia_val;
              so_tien_tt[i].value = replaceCommas(kl_dky_mua_val);
              kl_dky_mua[i].value = replaceCommas(kl_dky_mua_val);              
          }
      };
      var giatri_sl_dky_mua = replaceCommas(sum_sl_dky_mua);
      $("#so_luong").val(giatri_sl_dky_mua);
      calculateSum_KhoiLuong();
      calculateSum_SoTien();
  }

  function calculateSum_KhoiLuong() {
      var sum_kl_dky_mua = 0;
      var col_kl_dky_mua = document.getElementsByName("kl_dky_mua");
      if (col_kl_dky_mua != null && col_kl_dky_mua.length > 0) {
          //iterate through each textboxes and add the values
          for (var i = 0;i < col_kl_dky_mua.length;i++) {
              //add only if the value is number
              sum_kl_dky_mua += toNumber(col_kl_dky_mua[i].value);
          }
      };
      $("#khoi_luong").val(replaceCommas(sum_kl_dky_mua));
  }

  function calculateSum_SoTien() {
      var sum_so_tien_tt = 0;
      var so_tien_tt = document.getElementsByName("so_tien_tt");
      if (so_tien_tt != null && so_tien_tt.length > 0) {
          //iterate through each textboxes and add the values
          for (var i = 0;i < so_tien_tt.length;i++) {
              //add only if the value is number
              sum_so_tien_tt += toNumber(so_tien_tt[i].value);
              // $(so_tien_tt).css("background-color", "#FEFFB0");
          }
      };
      var giatri_so_tien_tt = replaceCommas(sum_so_tien_tt);
      $("#tong_so_tt").val(giatri_so_tien_tt);
  }

  function getval(sel){
      var giatri = sel.value;
      if (giatri == 2) {
          $("#div_ngay_tt_lai_1").show();
          $("#div_ngay_tt_lai_2").show();
      }
      else if (giatri == 1) {
          $("#div_ngay_tt_lai_1").show();
          $("#div_ngay_tt_lai_2").hide();
      }
      else {
          $("#div_ngay_tt_lai_1").hide();
          $("#div_ngay_tt_lai_2").hide();
      }
  }
  function change_date(){
    var ngay_ph= $("#ngay_ph").val();
    if(ngay_ph!=undefined && ngay_ph!=""){
        var arrayNgay_ph= ngay_ph.split("/");
        var dateNgayPH = new Date(arrayNgay_ph[2],parseInt(arrayNgay_ph[1]) - 1,arrayNgay_ph[0]);  
        document.getElementById("ngay_tt_tien_mua").value = ngay_ph;
        var ngay_tt_lai_1= $("#ngay_tt_lai_1").val();
        var ky_han1 = $('#ky_han').find(":selected").text().split(" ");
        var xValue =$("#ky_han").val();
        var value_ky_han1 = 0;
         if(xValue!=''){
            value_ky_han1 = parseInt(ky_han1[0]);
         }
        var ngay_dao_han = null;
        if(ngay_tt_lai_1== null || ngay_tt_lai_1==""){  
            ngay_dao_han = new Date(parseInt(dateNgayPH.getFullYear()) + value_ky_han1,dateNgayPH.getMonth(),dateNgayPH.getDate());
            document.getElementById("ngay_dao_han").value = ngay_dao_han.getDate() +"/"+ (ngay_dao_han.getMonth()+1)+"/"+ ngay_dao_han.getFullYear();
        }else{
            var ngay_tt_lai_1_arr = ngay_tt_lai_1.split("/");
            var ky_tt_lai = $("#ky_tt_lai").val();
            if(ky_tt_lai!=undefined && ky_tt_lai!="") ky_tt_lai = parseInt(ky_tt_lai);
            var thangTTLai2 = 0;
            if(ky_tt_lai == 2){
              thangTTLai2 = parseInt(ngay_tt_lai_1_arr[1]) + 5;
            }else thangTTLai2 = parseInt(ngay_tt_lai_1_arr[1]) - 1 ;
            var dateTTLai2 = new Date(ngay_tt_lai_1_arr[2],thangTTLai2,ngay_tt_lai_1_arr[0]);  
            ngay_dao_han = new Date(parseInt(dateTTLai2.getFullYear()) + value_ky_han1 - 1,dateTTLai2.getMonth(),dateTTLai2.getDate());
          if(ky_tt_lai == 2){
            document.getElementById("ngay_tt_lai_2").value = dateTTLai2.getDate() +"/"+ (dateTTLai2.getMonth() + 1) +"/"+ dateTTLai2.getFullYear();
          }          
          document.getElementById("ngay_dao_han").value = ngay_dao_han.getDate() +"/"+ (ngay_dao_han.getMonth() + 1) +"/"+ ngay_dao_han.getFullYear();
      }  
    }
  }
  $(function () {
      $("#dot_ph").focus();
      $("#ma_dvi_so_huu").attr("data-live-search",true);
      $("#ma_dvi_so_huu").focus();
      $("#ma_tpcp").attr("data-live-search",true);
      $("#ma_tpcp").focus();
      $("#ngay_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tt_tien_mua").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_dao_han").datepicker( {
          dateFormat : "dd/mm/yy"
      });
     $("#dot_bo_sung").change(function (e) {
          
              var dot_bo_sung = $("#dot_bo_sung").val();
           $.ajax( {
      
          type : "post",
          url : "GetAjaxKyHanBLAction.do", 
          data :  { longid : dot_bo_sung },
          async : true, cache : false, success : function (data) {
              var obj = JSON.parse(data);

              
              if(obj!= null){
              ngay_to_chuc_ph_cu=obj.ngay_ph;
              document.getElementById("ma_tpcp").value = obj.ma_tpcp;
               var guid_ma_tp = obj.ma_tpcp;
              if(guid_ma_tp.substr(0,4)=='TWBH'){
              $('#ma_dvi_so_huu_1').val('BH'); 
              $('#ma_dvi_so_huu_1').change();
              $('#ma_dvi_so_huu_1').attr("disabled", true);
             $('#extenstion').hide();
            }else if(guid_ma_tp.substr(0,4)=='TWSC'){
               $('#extenstion').hide();
              $('#ma_dvi_so_huu_1').val('SCIC'); 
              $('#ma_dvi_so_huu_1').change();
              $('#ma_dvi_so_huu_1').attr("disabled", true);
            }else{
              $('#extenstion').show();
              $('#ma_dvi_so_huu_1').attr("disabled", false);
            }
              document.getElementById("ky_han").value = toNumber(obj.ky_han);
              document.getElementById("ky_tt_lai").value = toNumber(obj.ky_tt_lai);
              $("#ky_tt_lai").attr("disabled",true);
              $("#ma_tpcp").attr("disabled",true);
              $('.selectpicker').selectpicker('refresh');
              change_date();
              if(obj.ky_tt_lai== "1"){
                check1="Yes";
                $("#div_ngay_tt_lai_1").show();
                $("#div_ngay_tt_lai_2").hide();
                $("#menh_gia").val(obj.menh_gia);
                $("#lai_suat").val(obj.lai_suat);
                $("#menh_gia").attr("readonly", true);
                $("#lai_suat").attr("readonly", true);
                $("#ngay_dao_han").attr("readonly",true);
                $("#loai_tien").attr("disabled",true);
                $("#ngay_dao_han ").datepicker('disable');
                $("#ngay_tt_lai_1 ").datepicker('disable');
                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
                document.getElementById("loai_tien").value=obj.loai_tien;
                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
                $('.selectpicker').selectpicker('refresh');

              }else if(obj.ky_tt_lai== 2){
              check1="Yes";
                $("#div_ngay_tt_lai_1").show();
                $("#div_ngay_tt_lai_2").show();   
                $("#menh_gia").val(obj.menh_gia);
                $("#lai_suat").val(obj.lai_suat);
                $("#menh_gia").attr("readonly", true);
                $("#lai_suat").attr("readonly", true);
                $("#ngay_tt_lai_1 ").datepicker('disable');
                $("#ngay_dao_han ").datepicker('disable');
                $("#ngay_tt_lai_2 ").datepicker('disable');
                $("#ngay_dao_han").attr("readonly",true);
                $("#loai_tien").attr("disabled",true);
                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
                document.getElementById("ngay_tt_lai_2").value=obj.ngay_tt_lai_2;
                document.getElementById("loai_tien").value=obj.loai_tien;
                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
                $('.selectpicker').selectpicker('refresh');

              }else if(obj.ky_tt_lai== "0"){
              check1="Yes";
               $("#div_ngay_tt_lai_1").hide();
                $("#div_ngay_tt_lai_2").hide();   
                $("#menh_gia").val(obj.menh_gia);
                $("#lai_suat").val(obj.lai_suat);
                $("#menh_gia").attr("readonly", true);
                $("#lai_suat").attr("readonly", true);
                $("#ngay_tt_lai_1 ").datepicker('enable');
                $("#ngay_tt_lai_2 ").datepicker('enable');
                $("#ngay_dao_han ").datepicker('enable');
                $("#ngay_dao_han").attr("readonly",true);
                $("#loai_tien").attr("disabled",true);
                document.getElementById("ngay_tt_lai_1").value=obj.ngay_tt_lai_1;
                document.getElementById("ngay_tt_lai_2").value=obj.ngay_tt_lai_2;
                document.getElementById("loai_tien").value=obj.loai_tien;
                document.getElementById("ngay_dao_han").value=obj.ngay_dao_han;
                $('.selectpicker').selectpicker('refresh');
              }else if(obj.ky_tt_lai=="") {
                $("#menh_gia").val("");
                $("#lai_suat").val("");
                $("#menh_gia").attr("readonly", false);
                $("#lai_suat").attr("readonly", false);
                $("#ngay_dao_han").val("");
                $("#div_ngay_tt_lai_1").hide();
                $("#div_ngay_tt_lai_2").hide(); 
                $("#ngay_tt_lai_1 ").datepicker('enable');
                $("#ngay_tt_lai_1 ").attr("readonly", false);
                $("#ngay_tt_lai_2 ").datepicker('enable');
                $("#ngay_dao_han ").datepicker('enable');
                $("#ngay_dao_han").attr("readonly",false);
                document.getElementById("ngay_tt_lai_1").value="";
                document.getElementById("ngay_tt_lai_2").value="";           
                $('.selectpicker').selectpicker('refresh');
                $("#ky_tt_lai").removeAttr("disabled",true);
                $('#ky_tt_lai').selectpicker('setStyle', 'disabled', 'remove');
                $("#ky_tt_lai").selectpicker();
                $("#loai_tien").removeAttr("disabled",true);
                $('#loai_tien').selectpicker('setStyle', 'disabled', 'remove');
                $("#loai_tien").selectpicker();
              }}else{
              alert("Chưa đủ điều kiện phát hành bổ sung !");
                ngay_to_chuc_ph_cu=""
                 document.getElementById("ky_han").value = "";
                document.getElementById("ngay_tt_lai_1").value="";
                document.getElementById("ngay_dao_han").value="";
                document.getElementById("ngay_tt_lai_2").value="";
                document.getElementById("ky_tt_lai").value="";
                document.getElementById("loai_tien").value="";  
                $("#ma_tpcp").val("");
                $("#ma_tpcp").attr("disabled",false);
                $('.selectpicker').selectpicker('refresh');
                $("#div_ngay_tt_lai_1").hide();
                $("#div_ngay_tt_lai_2").hide();
                $("#ngay_tt_lai_1 ").datepicker('enable');
                $("#ngay_tt_lai_2 ").datepicker('enable');
                $("#ngay_dao_han ").datepicker('enable');
                $("#ngay_tt_lai_1").removeAttr("readonly",true);
                $("#ky_tt_lai").removeAttr("disabled",true);
                $('#ky_tt_lai').selectpicker('setStyle', 'disabled', 'remove');
                $("#ky_tt_lai").selectpicker();
                $("#menh_gia").val("");
                $("#lai_suat").val("");
                $("#menh_gia").attr("readonly", false);
                $("#lai_suat").attr("readonly", false);
                $("#loai_tien").removeAttr("disabled",true);
                 $('#loai_tien').selectpicker('setStyle', 'disabled', 'remove');
                $("#loai_tien").selectpicker();
                $("#ngay_dao_han").attr("readonly",false);
              }
          }
          }
      ); }
      );
      $("#ngay_tt_lai_1").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tt_lai_2").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      
      
      document.getElementById("ky_han").value = '<bean:write name="BanLeTraiPhieuTwForm" property="ky_han"/>';
      $('.selectpicker').selectpicker('refresh');
      var ky_tra_lai = '<bean:write name="BanLeTraiPhieuTwForm" property="ky_tt_lai"/>';
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
      $("#menh_gia").keyup(function (event) {
          formatNumnew(event.target);
      });

      $("#lai_suat").keyup(function (event) {
          formatNumnew(event.target);
      });

  

  });
   function change_ma_tp(sel) {
  
      var guid_ma_tp = sel.value;
      if(guid_ma_tp.trim()==""){
       
        check1="No";
      }else{    
      if(guid_ma_tp.substr(0,4)=='TWBH'){
          $('#ma_dvi_so_huu_1').val('BH'); 
          $('#ma_dvi_so_huu_1').change();
          $('#ma_dvi_so_huu_1').attr("disabled", true);
         $('#extenstion').hide();
        }else if(guid_ma_tp.substr(0,4)=='TWSC'){
           $('#extenstion').hide();
          $('#ma_dvi_so_huu_1').val('SCIC'); 
          $('#ma_dvi_so_huu_1').change();
          $('#ma_dvi_so_huu_1').attr("disabled", true);
        }else{
          $('#extenstion').show();
          $('#ma_dvi_so_huu_1').attr("disabled", false);
        }
          $.ajax( {
          type : "post", url : "GetAjaxKyHanAction.do", data :  {
              longid : guid_ma_tp
          },
          async : true, cache : false, success : function (data) {
              var obj = JSON.parse(data);
              document.getElementById("ky_han").value = toNumber(obj.ky_han);
              $('.selectpicker').selectpicker('refresh');
              change_date();
          }
      });}}
//  $("#ngay_ph").on("change",function(){
//     var id= $(this).attr("id"); 
//  });
//  $("#ngay_dao_han").on("change",function(){
//     var id= $(this).attr("id"); 
//  });
//  $("#ngay_tt_tien_mua").on("change",function(){
//     var id= $(this).attr("id"); 
//  });
//  $("#ngay_tt_lai_1").on("change",function(){
//     var id= $(this).attr("id"); 
//  });
//  $("#ngay_tt_lai_2").on("change",function(){
//     var id= $(this).attr("id"); 
//  });
    function formatNumnew(elt){
        var position = getCaretStart(elt);
        var currentVal = elt.value;
        var s1 = testCommas(currentVal);
        var testDecimal = testDecimals(currentVal);
        if (testDecimal.length > 1) {
            currentVal = currentVal.slice(0, -1);
        }
//        if(currentVal=="") currentVal = "0";
        elt.value = replaceCommas(currentVal); 
        
        var s2 = testCommas(elt.value);
        setCaret(elt, position + (s2 - s1));
  }
</script>
<html:form action="AddExeBanLeTraiPhieuTwAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="BanLeTraiPhieuTw.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
  <logic:equal value="" name="BanLeTraiPhieuTwForm" property="guid">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="BanLeTraiPhieuTw.add.title"/>
      </h2>
    </div>
  </logic:equal>
  <logic:notEqual value="" name="BanLeTraiPhieuTwForm" property="guid">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="BanLeTraiPhieuTw.edit.title"/>
      </h2>
    </div>
  </logic:notEqual>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.dot_ph"/>
                <span style="color:red">(*)</span>
              </label>
            
              <logic:notEqual value="" name="BanLeTraiPhieuTwForm" property="dot_ph">
              <div class="col-sm-7">
                <html:text styleClass="form-control" maxlength="10"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           
                           styleId="dot_ph" property="dot_ph" readonly="true"/>
              </div>
              </logic:notEqual>
            </div>
          </div>
           <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-5 control-label">
                  Bổ sung đợt
                </label>   
                <div class="col-sm-7">
                  <html:text styleClass="form-control" maxlength="10"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onkeypress="return  validateFloatKeyPress(this, event)" onkeyup="format(this)"
                             onblur="textlostfocus(this);" 
                             styleId="dot_bo_sung" property="dot_bo_sung"/>
                  </div>
              </div>
            </div>
          
        </div>
        <!--Row 2  -->
        <div class="row">
           <div class="col-md-6">
          <div class="form-group">
          <label for="hoten" class="col-sm-5 control-label">
                  <fmt:message key="BanLeTraiPhieuTw.loai_tien"/>
                  <span style="color:red">(*)</span>
                </label>
             <div class="col-sm-7">
                  <html:select property="loai_tien" styleId="loai_tien"
                               styleClass="form-control selectpicker"
                              onchange="changeMenh_gia()"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="VND">VND</html:option>
                    <html:option value="USD">USD</html:option>
                   
                  </html:select>
                </div>
          </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ma_tpcp"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <input type="hidden" name="ma_tpcp_old" value="<bean:write name= "BanLeTraiPhieuTwForm" property="ma_tpcp"/>"/>
                <html:select styleClass="form-control selectpicker"
                             onkeypress="return blockKeyDT(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="2" onchange="change_ma_tp(this);"
                             styleId="ma_tpcp" property="ma_tpcp">
                  <logic:notEmpty name="listTPCP">
                    <html:option value="">Vui l&ograve;ng chọn</html:option>
                    <html:optionsCollection name="listTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
         
          <!--Row 2a-->
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-5 control-label">
                  <fmt:message key="BanLeTraiPhieuTw.ngay_ph"/>
                  <span style="color:red">(*)</span>
                </label>
                <div class="col-sm-7">
                  <div class="input-group date">
                    <html:text styleClass="form-control" styleId="ngay_ph"
                               maxlength="10" onkeyup="doFormat(event)"
                               onfocus="textfocus(this);" onchange="change_date()"
                               onblur="textlostfocus(this);" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ngay_ph"/>
                     
                  <label class="input-group-addon" for="ngay_ph"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                  </div>
                </div>
              </div>
            </div>
             <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="BanLeTraiPhieuTw.ky_han"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select styleClass="form-control selectpicker"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" 
                             styleId="ky_han" property="ky_han" disabled="true">
                  <logic:notEmpty name="lstKyHan">
                    <html:option value="">Vui l&ograve;ng chọn</html:option>
                    <html:optionsCollection name="lstKyHan" value="guid"
                                            label="name_ky_han"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
           
          </div>
          <!--Row 3 -->
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-5 control-label">
                  <fmt:message key="BanLeTraiPhieuTw.ngay_tt_tien_mua"/>
                  <span style="color:red">(*)</span>
                </label>
                <div class="col-sm-7">
                  <div class="input-group date">
                    <html:text styleClass="form-control" maxlength="10"
                               onkeyup="doFormat(event)"
                               styleId="ngay_tt_tien_mua"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ngay_tt_tien_mua"/>
                     
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
                  <fmt:message key="BanLeTraiPhieuTw.ngay_dao_han"/>
                  <span style="color:red">(*)</span>
                </label>
                <div class="col-sm-7">
                  <div class="input-group date">
                    <html:text styleClass="form-control" styleId="ngay_dao_han"
                               maxlength="10" onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ngay_dao_han"/>
                     
                  <label class="input-group-addon" for="ngay_dao_han"> 
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
                <fmt:message key="BanLeTraiPhieuTw.menh_gia"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeyDT(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);calculateSum();" 
                           styleId="menh_gia" property="menh_gia"
                           maxlength="20"/>
              </div>
            </div>
          </div>
            <div class="col-md-6">
              <div class="form-group">
                <label for="hoten" class="col-sm-5 control-label">
                  <fmt:message key="BanLeTraiPhieuTw.ky_tt_lai"/>
                  <span style="color:red">(*)</span>
                </label>
                <div class="col-sm-7">
                  <html:select property="ky_tt_lai" styleId="ky_tt_lai"
                               onchange="getval(this);change_date();"
                               styleClass="form-control selectpicker"
                               
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="0">Không trả lãi định kỳ</html:option>
                    <html:option value="1">Trả lãi định kỳ 12 tháng một lần</html:option>
                    <html:option value="2">Trả lãi định kỳ 6 tháng một lần</html:option>
                  </html:select>
                </div>
              </div>
            </div>
        
           
          
          
          </div>
          <!--Row 5-->
          <div class="row">
           
            <div class="col-md-6">
              <div class="form-group" id="div_ngay_tt_lai_1">
                <label for="hoten" class="col-sm-5 control-label">
                  <fmt:message key="BanLeTraiPhieuTw.ngay_tt_lai_1"/>
                  <span style="color:red">(*)</span>
                </label>
                <div class="col-sm-7">
                  <div class="input-group date">
                    <html:text styleClass="form-control" styleId="ngay_tt_lai_1"
                               maxlength="10" onkeyup="doFormat(event)"
                               onfocus="textfocus(this);" onchange="change_date()"
                               onblur="textlostfocus(this);" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ngay_tt_lai_1"/>
                     
                    <label class="input-group-addon" for="ngay_tt_lai_1"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                    </label>
                  </div>
                </div>
              </div>
            </div>
       
                <div class="col-md-6">
              <div class="form-group" id="div_ngay_tt_lai_2">
                <label for="hoten" class="col-sm-5 control-label">
                  <fmt:message key="BanLeTraiPhieuTw.ngay_tt_lai_2"/>
                  <span style="color:red">(*)</span>
                </label>
                <div class="col-sm-7">
                  <div class="input-group date">
                    <html:text styleClass="form-control" styleId="ngay_tt_lai_2"
                               maxlength="10" onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ngay_tt_lai_2"/>
                     
                    <label class="input-group-addon" for="ngay_tt_lai_2"> 
                        <span class="glyphicon glyphicon-calendar"></span>
                    </label>
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
                  <fmt:message key="BanLeTraiPhieuTw.lai_suat"/>
                  <span style="color:red">(*)</span>
                </label>
                <div class="col-sm-7">
                  <html:text styleClass="form-control" maxlength="4"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" 
                             styleId="lai_suat" property="lai_suat"/>
                </div>
              </div>
            </div>
            </div>
            <div class="row">
        <logic:notEmpty name="BanLeTraiPhieuTwForm" property="ly_do_tu_choi">
            <div class="col-md-6" id="div_lydotuchoi">
              <div class="form-group">
                <label for="hoten" class="col-sm-5 control-label">
                  <fmt:message key="BanLeTraiPhieuTw.ly_do_tu_choi"/>
                </label>
                <div class="col-sm-7">
                  <html:textarea rows="2" styleClass="form-control" 
                                 onkeypress="return blockKeySpecial001(event)"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 onfocus="textfocus(this);" style="width:736px"
                                 onblur="textlostfocus(this);"  readonly="true"
                                 styleId="ly_do_tu_choi"
                                 property="ly_do_tu_choi"/>
                </div>
              </div>
            </div>
        </logic:notEmpty>
        </div>
        </div>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="BanLeTraiPhieuTw.chitiet.title"/>
      </h2>
    </div>
    <div class="panel-body">
    
      <table class="table table-bordered" id="addTableTemp">
        <thead>
          <th class="center">STT</th>
          <th class="center">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.dvi_so_huu"/>
          </th>
           <!--<th class="center">
            <fmt:message key="BanLeTraiPhieuTw.ma_dvi_so_huu"/>
          </th>-->
          <th class="center">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.sl_dky_mua"/>
          </th>
          <th class="center">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.kl_dky_mua"/>
          </th>
          <th class="center">
            <fmt:message key="BanLeTraiPhieuTw_ChiTiet.so_tien_tt"/>
          </th>
        </thead>
         
        <tbody>
          <logic:notEmpty name="BanLeTraiPhieuTwForm"
                          property="lstKQBanLe_CTiet">
            <logic:iterate id="objKQBanLeChiTiet" name="BanLeTraiPhieuTwForm"
                           property="lstKQBanLe_CTiet" indexId="stt">
              <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td class="center">
                  <%=stt + 1%>
                </td>
             <td class="left">
                 <html:select styleClass="form-control search-live"
                               name="objKQBanLeChiTiet" 
                               onkeypress="return blockKeySpecial001(event)"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               styleId="ma_dvi_so_huu"
                               property="ma_dvi_so_huu">
                    <logic:notEmpty name="lstDVSH">
                      <html:optionsCollection name="lstDVSH" value="ma_chu_so_huu"
                                              label="ten_dvi_so_huu"/>
                    </logic:notEmpty>
                  </html:select>
                </td>
    
             
                <td class="center">
                  <html:text styleClass="form-control number" styleId="sl_dky_mua"
                             maxlength="20" onkeyup="formatNumnew(event.target);"
                             onblur="calculateSum();" name="objKQBanLeChiTiet"
                             onkeypress="return isNumberKey(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             property="sl_dky_mua"/>
                </td>
                <td class="center">
                  <html:text styleClass="form-control number" styleId="kl_dky_mua"
                             onkeyup="formatNumnew(event.target);"
                             onblur="calculateSum_KhoiLuong();"
                             name="objKQBanLeChiTiet"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             property="kl_dky_mua"/>
                </td>
                <td class="center">
                  <html:text styleClass="form-control number" styleId="so_tien_tt"
                             onkeyup="formatNumnew(event.target);"
                             onblur="calculateSum_SoTien();"
                             name="objKQBanLeChiTiet" 
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             property="so_tien_tt"/>
                </td>
              </tr>
            </logic:iterate>
          </logic:notEmpty>
        </tbody>
        <tfoot>
          <tr>
            <tr>
              <td colspan="2" style="text-align:center;font-weight: bold;">Tổng số</td>
              <td style="text-align:right;font-weight: bold;">
                <html:text styleClass="numberflat" readonly="true"
                           styleId="so_luong" property="so_luong"/>
              </td>
              <td style="text-align:right;font-weight: bold;">
                <html:text styleClass="numberflat" readonly="true"
                           styleId="khoi_luong" property="khoi_luong"/>
              </td>
              <td style="text-align:right;font-weight: bold;">
                <html:text styleClass="numberflat" readonly="true"
                           styleId="tong_so_tt" property="tong_so_tt"/>
              </td>
            </tr>            
          </tr>
        </tfoot>
      </table>
    </div>
  </div>
  <div style="margin-top: 10px; margin-left: 10px; text-align: left;" id="extenstion">
    <button type="button" class="btn btn-default" accesskey="d"
            onclick="addRow('addTableTemp')">
      Th&ecirc;m <span class="sortKey">d</span>&ograve;ng
    </button>
     
    <button type="button" class="btn btn-default" accesskey="x"
            onclick="deleteRow('addTableTemp')" tabindex="23">
      <span class="sortKey">X</span>&oacute;a d&ograve;ng
    </button>
  </div>
  <div style="margin-bottom: 10px; text-align: center;">
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g" tabindex="24" id="bfind">
      <span class="sortKey">G</span>hi
    </button>
    <button type="button" class="btn btn-default" onclick="check('saveandsubmit')"
            accesskey="g" tabindex="24" id="bfind">
      Ghi <span class="sortKey">v</span>à trình
    </button>
    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="24" id="bfind">
      Th<span class="sortKey">o</span>át
    </button>
 
  </div>
  <html:hidden property="trang_thai" styleId="trang_thai"/>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<script type="text/javascript">
  initTable('addTableTemp');

  $('.search-live').each(function () {
          $(this).attr("data-live-search",true);
          $(this).selectpicker();
      });
  function setTabIndex() {

  }
function isNumberKey(evt)
    {
       var charCode = (evt.which) ? evt.which : event.keyCode;
       if(charCode == 59 || charCode == 46)
        return true;
       if (charCode > 31 && (charCode < 48 || charCode > 57))
          return false;
       return true;
    }
  function addRow(tableId) {
      $('.search-live').each(function () {
          $(this).selectpicker('destroy');
      });
      addRowTable(tableId);
      assignSTT(tableId);
      $('.search-live').each(function () {
          $(this).attr("data-live-search",true);
          $(this).selectpicker();
      });
  }
  function deleteRow(tableId) {
      deleteRowTable(tableId);
      assignSTT(tableId);
      setTabIndex();
      calculateSum();
      calculateSum_SoTien();
      calculateSum_KhoiLuong();
  }
</script>
<%@ include file="/includes/tpcp_bottom.inc"%>