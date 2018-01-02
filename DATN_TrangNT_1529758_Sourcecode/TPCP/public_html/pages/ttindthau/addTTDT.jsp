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
          if (guid != null && guid != "") {
              f.action = 'UpdateExeTTinDThauAction.do';
          }
          else {
              f.action = 'AddExeTTinDThauAction.do';
          }
          if (validateForm()) {
           $("#ky_han").removeAttr("disabled");
           $("#ngay_dao_han").datepicker('enable');
           $("#ngay_tt_lai_1").datepicker('enable');
           $("#ngay_tt_lai_2").datepicker('enable');
              f.submit();
          }
      }
      
      
      if(type=='saveandsub'){
      
       if (validateForm()) {
           document.getElementById("trang_thai").value='01';
           $("#ky_han").removeAttr("disabled");
           $("#ngay_dao_han").datepicker('enable');
           $("#ngay_tt_lai_1").datepicker('enable');
           $("#ngay_tt_lai_2").datepicker('enable');
           document.getElementById("trang_thai").value='01';
           if (guid != null && guid != "") {
              document.getElementById("trang_thai").value='01';
              f.action = 'UpdateExeTTinDThauAction.do';
          }
          else {
              document.getElementById("trang_thai").value='01';
              f.action = 'AddExeTTinDThauAction.do';
          }
              f.submit();
          }
    
     
      }
      if (type == 'close') {
          f.action="ListTTinDThauAction.do";
          f.submit();
      }
  }
  var ngay_tcph2 = "";
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
      var ms1_length =ms1.length;
      if( ms1_length< 3 || ms1_length > 3){
        return false;
      }
      var ms2_length = ms2.length;
      if(ms2_length <4 || ms2_length > 4){
        return false;
      }
      if(!isNaN(parseInt(ms1,10)) &&  !isNaN(parseInt(ms2,10))){

        return true;
      }
      
      return false;
    }
  function dateChanged(ev){
    $(this).datepicker('hide');
    if($(this).val()!=""){   
        if(guid == null || guid ==""){
          var date = parseDate($(this).val());
          date.setDate(date.getDate() + 1);
            var date2 = date.getDate() + "/" + (date.getMonth() + 1) +"/" +  date.getFullYear();
            document.getElementById("ngay_tt_tien_mua").value = date2;
            document.getElementById("ngay_ph").value = date2;
        }
      } 
  }  
  function loadFunction(){     
       var guid = $("#dot_bo_sung").val().trim();
       
        if (guid != null && guid != "") {
              $('#ma_tpcp').attr("disabled", true);  
              $('#ky_han').attr("disabled", true);     
              $('#loai_tien').attr("disabled", true);
              $('#ngay_tt_lai_1').attr("disabled", true);  
              $('#ngay_tt_lai_2').attr("disabled", true);  
              $('#ngay_dao_han').attr("disabled", true);  
              $('#ky_tra_lai').attr("disabled", true); 
              $('#hinh_thuc_dt').attr("disabled", true);
              $('#ky_tra_lai').attr("disabled", true);
              $('#ky_tra_lai').attr("disabled", true);
              $('#ky_tra_lai').attr("disabled", true);
              $('#pthuc_xacdinh_kqdt').attr("disabled", true);
              document.getElementById("menh_gia").setAttribute("readonly", true);
              document.getElementById("ls_danh_nghia").setAttribute("readonly", true);
              $("#div_ls_danh_nghia").show();
              $("#div_ls_trung_thau").hide();
              $('#select').css('color','gray');
           $.ajax({
                type : "post", url : "GetAjaxDotBoSungAction.do", data :  {
                      dot_dau_thau : guid
                  },
                  async : true, cache : false, success : function (data) {
                        var obj = JSON.parse(data);
                        $("#kl_goi_thau_lan_dau").val(obj.khoi_luong_tp);
           }})
     }
  }
  
  window.onload = loadFunction;
  // replace all 
  function escapeRegExp(str) {
    return str.replace(/[.*+?^${}()|[\]\\]/g, ""); 
  }
  function validateForm() {
      var dot_dau_thau = $("#dot_dau_thau").val().trim();
      if (dot_dau_thau == null || dot_dau_thau == "") {
          alert('Bạn phải nhập đợt đấu thầu');
          $("#dot_dau_thau").focus();
          return false;
      }
      if(!parseString(dot_dau_thau)){
        alert('Bạn phải nhập đợt đấu thầu đúng định dạng : STT/yyyy');
        $("#dot_dau_thau").focus();
          return false;
      }
      var str_dot_dau_thau= dot_dau_thau.split("/");
      if(str_dot_dau_thau.length==2){
           if(str_dot_dau_thau[0]==0){
         alert("Bạn không được phép nhập : 000/0000 , xxx/0000 ,000/xxxx!");
          $("#dot_dau_thau");
          return false;
        }
       var ngay_to_chuc_ph = $("#ngay_to_chuc_ph").val();
       var checkY= ngay_to_chuc_ph.split("/");
        if(str_dot_dau_thau[1]==0){
            alert("Bạn không được phép nhập : 000/0000 , xxx/0000 ,000/xxxx!");
          $("#dot_dau_thau");
          return false;
        }
      }
      
      if(str_dot_dau_thau[1] != checkY[2]){
        alert("Bạn phải nhập năm đợt đấu thầu trùng với năm phát hành!");
          $("#dot_dau_thau");
          return false;
      }
      var ma_tp = $("#ma_tpcp").val();
      if (ma_tp == null || ma_tp == "") {
          alert('Bạn phải nhập mã TPCP');
          $("#ma_tpcp").focus();
          return false;
      }
      var khoi_luong_tp = $("#khoi_luong_tp").val();
      if (khoi_luong_tp == null || khoi_luong_tp == "") {
          alert('Bạn phải nhập khối lượng trái phiếu');
          $("#khoi_luong_tp").focus();
          return false;
      }
      var khoi_luong_tp_int= parseInt(escapeRegExp(khoi_luong_tp));
      if(khoi_luong_tp_int==0){
        alert('Bạn phải nhập khối lượng trái phiếu lớn hơn 0');
          $("#khoi_luong_tp").focus();
          return false;
      }
      var loai_tien = $("#loai_tien").val();
      if (loai_tien == null || loai_tien == "") {
          alert('Bạn phải chọn Loại tiền');
          $("#loai_tien").focus();
          return false;
      }
      var ky_han = $("#ky_han").val();
      if (ky_han == null || ky_han == "") {
          alert('Bạn phải chọn Kỳ hạn');
          $("#ky_han").focus();
          return false;
      }
     
      if (ngay_to_chuc_ph == null || ngay_to_chuc_ph == "") {
          alert('Bạn phải nhập ngày tổ chức phát hành');
           $("#ngay_to_chuc_ph").focus();
          return false;
      }else{
        var check1 = validatedate(document.getElementById("ngay_to_chuc_ph"));
        if (!check1) {
            return false;
        }
      }
      var ngay_ph = $("#ngay_ph").val();
      if (ngay_ph == null || ngay_ph == "") {
          alert('Bạn phải nhập ngày phát hành');
          $("#ngay_ph").focus();
          return false;
      }else{
        var check2 = validatedate(document.getElementById("ngay_ph"));
        if (!check2) {
            return false;
        }
      }
      var ngay_tt_tien_mua = $("#ngay_tt_tien_mua").val();
      if (ngay_tt_tien_mua == null || ngay_tt_tien_mua == "") {
          alert('Bạn phải nhập ngày thanh toán tiền mua trái phiếu');
          $("#ngay_tt_tien_mua").focus();
          return false;
      }else{
        var check3 = validatedate(document.getElementById("ngay_tt_tien_mua"));
        if (!check3) {
            return false;
        }
      }
   
      var ky_tra_lai = $("#ky_tra_lai").val();
      if (ky_tra_lai != 0) {
          var ngay_tt_lai_1 = $("#ngay_tt_lai_1").val();
          if (ngay_tt_lai_1 == null || ngay_tt_lai_1 == "") {
              alert('Bạn phải nhập ngày thanh toán lãi lần 1');
              $("#ngay_tt_lai_1").focus();
              return false;
          }
      }
      if (ky_tra_lai == 2) {
          var ngay_tt_lai_2 = $("#ngay_tt_lai_2").val();
          if (ngay_tt_lai_2 == null || ngay_tt_lai_2 == "") {
              alert('Bạn phải nhập ngày thanh toán lãi lần 2');
              $("#ngay_tt_lai_2").focus();
              return false;
          }
      }
      var ngay_dao_han = $("#ngay_dao_han").val();
      if (ngay_dao_han == null || ngay_dao_han == "") {
          alert('Bạn phải nhập ngày đáo hạn');
          $("#ngay_dao_han").focus();
          return false;
      }else{
        var check4 = validatedate(document.getElementById("ngay_dao_han"));
        if (!check4) {
            return false;
        }
      }
      var menh_gia = $("#menh_gia").val();
      if (menh_gia == null || menh_gia == "") {
          alert('Bạn phải nhập mệnh giá');
          $("#menh_gia").focus();
          return false;
      }
      var menh_gia_int= parseInt(escapeRegExp(menh_gia));
      if(menh_gia_int==0){
        alert("Bạn phải nhập mệnh giá lớn hơn 0");
        $("#menh_gia").focus();
        return false;
      }
      if (menh_gia.length >20) {
          alert('Độ dài mệnh giá nhỏ hơn 20');
          $("#menh_gia").focus();
          return false;
      }
      var so_tk_nhan = $("#so_tk_nhan").val();
      if(so_tk_nhan == null ||so_tk_nhan == ""){
        alert("Bạn phải nhập số tài khoản nhận !");
        $("#so_tk_nhan").focus();
        return false;
      }
      var hinh_thuc_dt = $("#hinh_thuc_dt").val();
      if (hinh_thuc_dt == null || hinh_thuc_dt == "") {
          alert('Bạn phải nhập hình thức đấu thầu trái phiếu');
          $("#hinh_thuc_dt").focus();
          return false;
      }
      var pthuc_xacdinh_kqdt = $("#pthuc_xacdinh_kqdt").val();
      if (pthuc_xacdinh_kqdt == null || pthuc_xacdinh_kqdt == "") {
          alert('Bạn phải nhập phương thức xác định kết quả đầu thầu');
         $("#pthuc_xacdinh_kqdt").focus();
          return false;
      }
      var ngay_tbao_goi_thau = $("#ngay_tbao_goi_thau").val();
      if (ngay_tbao_goi_thau == null || ngay_tbao_goi_thau == "") {
          alert('Bạn phải nhập ngày thông báo gọi thầu');
          $("#ngay_tbao_goi_thau").focus();
          return false;
      }
      var ngay_tt_lai_12 = $("#ngay_tt_lai_1").val();
      if (ngay_tt_lai_12 != null && ngay_tt_lai_12 != "") {
          var check5 = validatedate(document.getElementById("ngay_tt_lai_1"));
          if (!check5) {
              return false;
          }
          
          
      }
      var ngay_tt_lai_21 = $("#ngay_tt_lai_2").val();
      if (ngay_tt_lai_21 != null && ngay_tt_lai_21 != "") {
          var check6 = validatedate(document.getElementById("ngay_tt_lai_2"));
          if (!check6) {
              return false;
          }
          //
      }
      var startDate,endDate;
    
      if ((ngay_to_chuc_ph != null && ngay_to_chuc_ph != "") && (ngay_tbao_goi_thau != null && ngay_tbao_goi_thau != "")) {
         startDate = parseDate(ngay_tbao_goi_thau).getTime();
         endDate = parseDate(ngay_to_chuc_ph).getTime();
          if (startDate >= endDate) {
              alert('Ngày thông báo gọi thầu phải nhỏ hơn ngày tổ chức phát hành !');
              $("#ngay_tbao_goi_thau").focus();
              return false;
          }
      }
      
      if(ngay_to_chuc_ph != null &&  ngay_tcph2 != ""){
        startDate = parseDate(ngay_to_chuc_ph).getTime();
         endDate = parseDate(ngay_tcph2).getTime();
          if (startDate <= endDate) {
              alert('Ngày tổ chức phát hành phải lớn hơn ngày tổ chức phát hành đợt bổ sung !');
              $("#ngay_to_chuc_ph").focus();
              return false;
          }
      }
      if ((ngay_to_chuc_ph != null && ngay_to_chuc_ph != "") && (ngay_ph != null && ngay_ph != "")) {
         startDate = parseDate(ngay_to_chuc_ph).getTime();
         endDate = parseDate(ngay_ph).getTime();
          if (startDate >= endDate) {
              alert('Ngày tổ chức phát hành phải nhỏ hơn ngày phát hành !');
              $("#ngay_to_chuc_ph").focus();
              return false;
          }
      }
      if ((ngay_tt_tien_mua != null && ngay_tt_tien_mua != "") && (ngay_ph != null && ngay_ph != "")) {
         startDate = parseDate(ngay_ph).getTime();
         endDate = parseDate(ngay_tt_tien_mua).getTime();
          if (startDate != endDate) {
              alert('Ngày thanh toán tiền mua trái phiếu phải bằng ngày phát hành !');
              $("#ngay_ph").focus();
              return false;
          }
      }
      if ((ngay_tt_tien_mua != null && ngay_tt_tien_mua != "") && (ngay_dao_han != null && ngay_dao_han != "")) {
         startDate = parseDate(ngay_tt_tien_mua).getTime();
         endDate = parseDate(ngay_dao_han).getTime();
          if (startDate >= endDate) {
              alert('Ngày thanh toán tiền mua trái phiếu phải nhỏ hơn ngày đáo hạn !');
              $("#ngay_tt_tien_mua").focus();
              return false;
          }
      }
    var dot_bs = $("#dot_bo_sung").val();
    if(dot_bs==""){
    if ((ngay_tt_tien_mua != null && ngay_tt_tien_mua != "") && (ngay_tt_lai_1 != null && ngay_tt_lai_1 != "")) {
         startDate = parseDate(ngay_tt_tien_mua).getTime();
         endDate = parseDate(ngay_tt_lai_1).getTime();
          if (startDate >= endDate) {
              alert('Ngày thanh toán tiền mua trái phiếu phải nhỏ hơn ngày thanh toán lãi đâu tiên !');
              $("#ngay_tt_tien_mua").focus();
              return false;
          }
      }
  
    if ((ngay_tt_lai_1 != null && ngay_tt_lai_1 != "") && (ngay_tt_lai_2 != null && ngay_tt_lai_2 != "")) {
         startDate = parseDate(ngay_tt_lai_1).getTime();
         endDate = parseDate(ngay_tt_lai_2).getTime();
          if (startDate >= endDate) {
              alert('Ngày thanh toán lãi đầu tiên phải nhỏ hơn ngày thanh toán lãi lần 2 !');
              $("#ngay_tt_lai_1").focus();
              return false;
          }
      }
      if ((ngay_dao_han != null && ngay_dao_han != "") && (ngay_tt_lai_2 != null && ngay_tt_lai_2 != "")) {
         startDate = parseDate(ngay_dao_han).getTime();
         endDate = parseDate(ngay_tt_lai_2).getTime();
          if (startDate <= endDate) {
              alert('Ngày thanh toán lãi lần 2 phải nhỏ hơn ngày đáo hạn !');
              $("#ngay_tt_lai_2").focus();
              return false;
          }
      }
    }
//      var so_cv = $("#so_tbao_goi_thau").val();
//      if(so_cv == dot_dau_thau){
//        alert('Bạn phải nhập số công văn khác đợt đấu thầu ');
//        $("#so_tbao_goi_thau").focus();
//        return false;
//      }


      var so_tk_nhan1= $("#so_tk_nhan").val();
      if (so_tk_nhan1 != null && so_tk_nhan1 != "") {
          if (so_tk_nhan1.length >20) {
              alert('Độ dài Số TK nhận nhỏ hơn 20');
              return false;
          }
      }
      return true;
  }
  function parseDate(str) {
      var mdy = str.split('/');
      var m = toNumber(mdy[1]) - 1;
      return new Date(mdy[2],m, mdy[0]);
  }
  
  function change_ma_tp(sel) {
      var guid_ma_tp = sel.value;
      if(guid_ma_tp==""){
        document.getElementById("ky_han").value = "";
        $('.selectpicker').selectpicker('refresh');
      }else{
      $.ajax( {
          type : "post", url : "GetAjaxKyHanAction.do", data :  {
              longid : guid_ma_tp
          },
          async : true, cache : false, success : function (data) {
              var obj = JSON.parse(data);
              document.getElementById("ky_han").value = toNumber(obj.ky_han);
              $('.selectpicker').selectpicker('refresh');
              
               var time1= $("#ngay_to_chuc_ph").val();
               if(time1!= ""){
                var timengay = parseDate(time1).getTime()+86400000;
                var date_ngay = new Date(timengay);
                var day = date_ngay.getDate();
                var month = date_ngay.getMonth();
                var year = date_ngay.getFullYear();     
                var ngay_tt_lai_1 = $("#ngay_tt_lai_1").val();         
               
                if(ngay_tt_lai_1=="" ){
                  var ky_han1 = $('#ky_han').find(":selected").text().split(" ");
                  var xValue =$("#ky_han").val();
                  if(xValue!=''){
                  var value_ky_han1 = parseInt(ky_han1[0]);
                  var strY1 = parseInt(year);
                  var yearLai2 = strY1 + value_ky_han1 ;
                  var new_ngay_dao= new Date(yearLai2,month,day);
                  var str_new_ngay_dao= getFormattedDate(new_ngay_dao);
                  document.getElementById("ngay_dao_han").value = str_new_ngay_dao;
                  }
                }else{
                  var ky_han = $('#ky_han').find(":selected").text().split(" ");
                  var value_ky_han = parseInt(ky_han[0]);
                  var Year_lai_1 = ngay_tt_lai_1.split("/");
                  var strY = parseInt(Year_lai_1[2]);
                  var yearLai ="";
                  if(Year_lai_1[2]==year){
                    yearLai = strY + value_ky_han ;
                  }else{
                   yearLai = strY + value_ky_han -1;
                  }
                  var new_ngay_dao1= new Date(yearLai,month,day);
                  var str_new_ngay_dao1= getFormattedDate(new_ngay_dao1);
                  document.getElementById("ngay_dao_han").value = str_new_ngay_dao1;
                }}
              
          }
      });
      
      }
  }
  
  function change_ky_tra_lai(sel) {
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

  
  $(function () {
      $("#ma_tpcp").attr("data-live-search",true);
      
      $("#ngay_to_chuc_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      }).change(dateChanged).on('changeDate', dateChanged);
      $("#ngay_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tt_tien_mua").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_dao_han").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tt_lai_1").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tt_lai_2").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_den_han_thanh_toan").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tbao_goi_thau").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      var ls1='<bean:write name="ThongTinDauThauForm" property="ls_danh_nghia"/>';
      if(ls1!=null &&ls1!=""){
        $("#div_ls").show();
        $("#div_kl_goi_thau_lan_dau").show();
      }else{
        $("#div_ls").hide();
        $("#div_kl_goi_thau_lan_dau").hide();
      }
      var dot_bo_sung1 ='<bean:write name="ThongTinDauThauForm" property="dot_bo_sung"/>';
      var khoi_luong_them1 ='<bean:write name="ThongTinDauThauForm" property="khoi_luong_them"/>';
      if(khoi_luong_them1!=''){
        if(dot_bo_sung1!= ''){
          $("#div_ls_danh_nghia").show();
          $("#div_ls_trung_thau").hide();
        
        }else{
          $("#div_ls_danh_nghia").hide();
          $("#div_ls_trung_thau").show();
        }
      }else{
        $("#div_ls_danh_nghia").show();
          $("#div_ls_trung_thau").hide();
      }
    
      
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

      $("#div_ls").hide();
      var ls_danh_nghia='<bean:write name="ThongTinDauThauForm" property="ls_danh_nghia"/>';
      if(ls_danh_nghia!=null &&ls_danh_nghia!=''){
          $("#div_ls").show();
      }
      document.getElementById("ky_han").value = '<bean:write name="ThongTinDauThauForm" property="ky_han"/>';
      $('.selectpicker').selectpicker('refresh');

      $("#dot_bo_sung").change(function (e) {
      
              var dot_bo_sung = $("#dot_bo_sung").val();
              $.ajax( {
                  type : "post", url : "GetAjaxDotBoSungAction.do", data :  {
                      dot_dau_thau : dot_bo_sung
                  },
                  async : true, cache : false, success : function (data) {
                      var obj = JSON.parse(data);
                      if(obj!=null){
                        document.getElementById("ky_han").value = toNumber(obj.ky_han);
                        document.getElementById("ky_han2").value = obj.ky_han;
                        document.getElementById("ma_tpcp").value = obj.ma_tpcp;
                        document.getElementById("ma_tpcp2").value = obj.ma_tpcp;
                        document.getElementById("ky_tra_lai").value = obj.ky_tra_lai;
                        document.getElementById("ky_tra_lai2").value = obj.ky_tra_lai;
                        $("#kl_goi_thau_lan_dau").val(obj.khoi_luong_tp);
                        $("#div_kl_goi_thau_lan_dau").show();
                        ngay_tcph2 = obj.ngay_to_chuc_ph;
                        document.getElementById("ngay_dao_han").value = obj.ngay_dao_han;
                         $('.selectpicker').selectpicker('refresh');
                        $("#ngay_dao_han").datepicker('disable');
                        if (obj.ky_tra_lai == '1') {
                            $("#div_ngay_tt_lai_1").show();
                            $("#div_ngay_tt_lai_2").hide();
                            document.getElementById("ngay_tt_lai_1").value = obj.ngay_tt_lai_1;
                            $("#ngay_tt_lai_1").datepicker('disable');
                             $("#div_ls").show();
                             $("#div_ls_trung_thau").hide();
                             $("#div_ls_danh_nghia").show();
                        
                        document.getElementById("menh_gia").value = obj.menh_gia;
                        document.getElementById("loai_tien").value = obj.loai_tien;
                        document.getElementById("loai_tien2").value = obj.loai_tien;
                        document.getElementById("hinh_thuc_dt").value = obj.hinh_thuc_dt;
                        document.getElementById("hinh_thuc_dt2").value = obj.hinh_thuc_dt;
                        document.getElementById("pthuc_xacdinh_kqdt").value = obj.pthuc_xacdinh_kqdt;
                        document.getElementById("pthuc_xacdinh_kqdt2").value = obj.pthuc_xacdinh_kqdt;
                        document.getElementById("ls_danh_nghia").value = obj.ls_danh_nghia;
                        $("#ls_danh_nghia").attr("readonly",true);
                        $('#ky_han').attr('disabled',true);
                        $('#ma_tpcp').attr('disabled',true);
                        $('#ky_tra_lai').attr('disabled',true);
                        $('#ngay_dao_han').attr('readonly',true);
                        $('#ngay_tt_lai_1').attr('readonly',true);
                        $('#ngay_tt_lai_2').attr('readonly',true);
                        $('#menh_gia').attr('readonly',true);
                        $('#loai_tien').attr('disabled',true);
                        $('#hinh_thuc_dt').attr('disabled',true);
                        $('#pthuc_xacdinh_kqdt').attr('disabled',true);
                        $('.selectpicker').selectpicker('refresh');
                        }
                        if (obj.ky_tra_lai == '2') {
                            $("#div_ngay_tt_lai_1").show();
                            document.getElementById("ngay_tt_lai_1").value = obj.ngay_tt_lai_1;
                            $("#div_ngay_tt_lai_2").show();
                            document.getElementById("ngay_tt_lai_2").value = obj.ngay_tt_lai_2;
                            $("#ngay_tt_lai_1").datepicker('disable');
                            $("#ngay_tt_lai_2").datepicker('disable');                 
                            }
                            $("#div_ls_trung_thau").hide();
                             $("#div_ls_danh_nghia").show();
                        $("#div_ls").show();
                        document.getElementById("menh_gia").value = obj.menh_gia;
                        document.getElementById("loai_tien").value = obj.loai_tien;
                        document.getElementById("loai_tien2").value = obj.loai_tien;
                        document.getElementById("hinh_thuc_dt").value = obj.hinh_thuc_dt;
                        document.getElementById("hinh_thuc_dt2").value = obj.hinh_thuc_dt;
                        document.getElementById("pthuc_xacdinh_kqdt").value = obj.pthuc_xacdinh_kqdt;
                        document.getElementById("pthuc_xacdinh_kqdt2").value = obj.pthuc_xacdinh_kqdt;
                        document.getElementById("ls_danh_nghia").value = obj.ls_danh_nghia;
                        $("#ls_danh_nghia").attr("readonly",true);
                        $('#ky_han').attr('disabled',true);
                        $('#ma_tpcp').attr('disabled',true);
                        $('#ky_tra_lai').attr('disabled',true);
                        $('#ngay_dao_han').attr('readonly',true);
                        $('#ngay_tt_lai_1').attr('readonly',true);
                        $('#ngay_tt_lai_2').attr('readonly',true);
                        $('#menh_gia').attr('readonly',true);
                        $('#loai_tien').attr('disabled',true);
                        $('#hinh_thuc_dt').attr('disabled',true);
                        $('#pthuc_xacdinh_kqdt').attr('disabled',true);
                        $('.selectpicker').selectpicker('refresh');
                  }else{
                    if(dot_bo_sung!=""){
                      alert("Chưa đủ điều kiện phát hành bổ sung !");
                    }
                      $("#kl_goi_thau_lan_dau").val("");
                     $("#div_kl_goi_thau_lan_dau").hide();
                      $("#dot_bo_sung").focus();                     
                      $('#div_ls').hide();
                      $('#ma_tpcp').attr('disabled',false);
                      $('#ky_tra_lai').attr('disabled',false);
                      $('#ngay_dao_han').attr('readonly',false);
                      $('#div_ngay_tt_lai_1').hide();
                      $('#div_ngay_tt_lai_2').hide();
                      $('#menh_gia').attr('readonly',false);
                      $('#loai_tien').attr('disabled',false);
                      $('#hinh_thuc_dt').attr('disabled',false);
                      $('#pthuc_xacdinh_kqdt').attr('disabled',false);
                      $('.selectpicker').selectpicker('refresh');
                      document.getElementById("ngay_tt_lai_1").value = "";
                      document.getElementById("ngay_tt_lai_2").value = "";
                      document.getElementById("ma_tpcp").value = "";
                      $("#ngay_dao_han").datepicker('enable');
                      $("#ngay_tt_lai_1").datepicker('enable');
                      $("#ngay_tt_lai_2").datepicker('enable');
                      $("#ngay_tt_lai_1").attr('readonly', false);
                      $("#ngay_tt_lai_2").attr('readonly', false);
                        document.getElementById("ls_danh_nghia").value = "";
                        document.getElementById("ngay_dao_han").value = "";
                        document.getElementById("menh_gia").value = "";
                        document.getElementById("loai_tien").value = "";
                        document.getElementById("loai_tien2").value = "";
                        document.getElementById("hinh_thuc_dt").value = "";
                        document.getElementById("hinh_thuc_dt2").value = "";
                        document.getElementById("pthuc_xacdinh_kqdt").value = "";
                        document.getElementById("pthuc_xacdinh_kqdt2").value ="";
                        document.getElementById("ky_tra_lai").value = "";
                        $('.selectpicker').selectpicker('refresh');
                  }
                  }
              });
          }
      );
      $("#khoi_luong_tp").keyup(function (event) {
          formatNumnew(event.target);
      });
    
       $('#dot_dau_thau').on('keypress', function(e) {
        if (e.which == 32)
            return false;
    });  
      $("#menh_gia").val("100.000");
      $("#menh_gia").keyup(function (event) {
          formatNumnew(event.target);
      });
      var guid='<bean:write name="ThongTinDauThauForm" property="guid"/>';
      if(guid!=null&&guid !=''){
          $('#dot_dau_thau').attr('readonly',true);
          $("#ma_tpcp").focus();
          $('.selectpicker').selectpicker('refresh');
      }else{
          $("#dot_dau_thau").focus();
      }
      
  });

  function format(elt){
    var currentVal = elt.value;
    if(currentVal.length ==3){
      elt.value = elt.value +"/";
    }

  }
  
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
    function getFormattedDate(date) {
    var year = date.getFullYear();
  
    var month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
  
    var day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    
   return  day+ '/' + month + '/' + year;
  }
  function change_date(){
    var time1= $("#ngay_to_chuc_ph").val();
    var timengay = parseDate(time1).getTime()+86400000;
    var date_ngay = new Date(timengay);
    var time =getFormattedDate(date_ngay);
    var timestr= time.split("/");
    var day = date_ngay.getDate();
    var month = date_ngay.getMonth()+1;
    var year = date_ngay.getFullYear();
    $("#ngay_ph").val(timestr[0]+"/"+timestr[1]+"/"+timestr[2]);
    var ngay_ph = timestr[0]+"/"+timestr[1]+"/"+timestr[2];
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
            var ky_tt_lai = $("#ky_tra_lai").val();
            if(ky_tt_lai!=undefined && ky_tt_lai!="") ky_tt_lai = parseInt(ky_tt_lai);
            var thangTTLai2 = 0;
            if(ky_tt_lai == 2){
              thangTTLai2 = parseInt(ngay_tt_lai_1_arr[1]) + 5;
            }else thangTTLai2 = parseInt(ngay_tt_lai_1_arr[1]) - 1 ;
            var dateTTLai2 = new Date(ngay_tt_lai_1_arr[2],thangTTLai2,ngay_tt_lai_1_arr[0]);  
            ngay_dao_han = new Date(parseInt(dateTTLai2.getFullYear() ) + value_ky_han1 - 1,dateTTLai2.getMonth(),dateTTLai2.getDate());
           if(ky_tt_lai == 2){
            document.getElementById("ngay_tt_lai_2").value = getFormattedDate(dateTTLai2);
          }          
          document.getElementById("ngay_dao_han").value = getFormattedDate(ngay_dao_han);
      }  
    }
    }
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
    function validateFloatKeyPress(el, evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if(charCode ==8){
          return true;
        }
        if (charCode != 44 && charCode > 31 && (charCode < 48 || charCode > 57)&& charCode !=47 ) {
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
//  $(document).on('keydown',function(e) {
//    var keyCode = e.keyCode || e.which; 
//    if (keyCode == 9) {       //if the key pressed was 'tab'...
//       //  e.preventDefault();
//         if($(this).nextAll('input').length === 0) {
//              // this is the last input element
//              $('input[type=text]:enabled:first').focus();// first textbox
//         }
//         else
//         {
//              $(this).next('input').focus();
//         }
//    } 
//});
$(".form-control").keydown(function (e) {
    if(e.which==9){
    $(this).next().focus();
    }
});
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
                           onkeypress="return validateFloatKeyPress(this,event);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" onkeyup="format(this)"
                           onblur="textlostfocus(this);" 
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
                           onkeypress="return validateFloatKeyPress(this,event);"
                          
                           onfocus="textfocus(this);" onkeyup="format(this)"
                           onblur="textlostfocus(this);" 
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
                             styleClass="form-control selectpicker" 
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onchange="change_ma_tp(this)">
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
                Khối lượng gọi thầu
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeyDT(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" maxlength="20"
                           onblur="textlostfocus(this);" 
                           styleId="khoi_luong_tp" property="khoi_luong_tp"/>
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
                <html:select property="loai_tien" styleId="loai_tien" onchange="changeMenh_gia()"
                             styleClass="form-control selectpicker" 
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                 
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
                <html:select property="ky_han" styleId="ky_han"   onchange="change_date()"
                             styleClass="form-control selectpicker" 
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
                          
                              onfocus="textfocus(this);" onchange="change_date()"
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
                             
                             onfocus="textfocus(this);" maxlength="10" onkeyup="doFormat(event)"
                             onblur="textlostfocus(this);" 
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
                            onfocus="textfocus(this);"
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
                             
                             styleClass="form-control selectpicker"
                             onchange="change_ky_tra_lai(this)">
                  
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
                              onfocus="textfocus(this);" onchange="change_date()"
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
                             onfocus="textfocus(this);"
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
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ngay_dao_han"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_dao_han"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
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
              <label  for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.menh_gia"/>
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeyDT(event)"
                           onfocus="textfocus(this);" 
                           onblur="textlostfocus(this);"  maxlength="20"
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
              <label for="hoten" class="col-sm-5 control-label">
                Hình thức ĐT trái phiếu
                &nbsp;
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="hinh_thuc_dt" styleId="hinh_thuc_dt"
                             styleClass="form-control selectpicker"
                             
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
              <label for="hoten" class="col-sm-5 control-label">
                Phương thức xác định KQĐT
                <span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:select property="pthuc_xacdinh_kqdt"
                             styleId="pthuc_xacdinh_kqdt" 
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
              <label for="hoten" class="col-sm-5 control-label">
                TK nhận tiền mua TP<span class="red">(*)</span>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" 
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
                              onfocus="textfocus(this);"
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
          <div class="col-md-6" id="div_kl_goi_thau_lan_dau">
          <div class="form-group">
            <label for="hoten" class="col-sm-5 control-label">
                Tổng KL đã công bố
            </label>
            <div class="col-sm-7">
                 <input type="text" id="kl_goi_thau_lan_dau" class="form-control" readonly="true" />
            </div>
          </div>
        </div>
          
          <div class="col-md-6" id="div_ls">
            <div class="form-group">
            <div id="div_ls_danh_nghia">
              <label for="hoten" class="col-sm-5 control-label">
                Lãi suất danh nghĩa<span class="red">(*)</span>
              </label>
           </div>
              <div  id="div_ls_trung_thau">
              <label for="hoten" class="col-sm-5 control-label">
                Lãi suất phát hành<span class="red">(*)</span>
              </label>
             </div>
              <div class="col-sm-7">
                <html:text styleClass="form-control" styleId="ls_danh_nghia"
                           onfocus="textfocus(this);" maxlength="5" 
                           onblur="textlostfocus(this);" 
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ls_danh_nghia"/>
                           
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
              <div class="col-sm-7">
                <html:textarea styleClass="form-control" style="width:736px;"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);"  
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               property="ghi_chu"/>
              </div>
            </div>
          </div>
        
        </div>
        <div class="row">
          <logic:equal value="03" name="ThongTinDauThauForm" property="trang_thai">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ly_do_tu_choi"/>
              </label>
              <div class="col-sm-7">
                <html:textarea styleClass="form-control"  readonly="true"
                               onfocus="textfocus(this);" style="width:736px"
                               onblur="textlostfocus(this);" tabindex="19"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               styleId="ly_do_tu_choi" property="ly_do_tu_choi"/>
              </div>
            </div>
          </div>
          </logic:equal>
        </div>
      </div>
    </div>
  </div>
  <div class="center">
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g"  id="bfind">
      <span class="sortKey">G</span>hi
    </button>
     <button type="button" class="btn btn-default" onclick="check('saveandsub')"
            accesskey="v"  id="bfind">
      Ghi <span class="sortKey">v</span>à trình
    </button>

    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" id="bfind">
      Th<span class="sortKey">o</span>át
    </button>
  </div>
  <html:hidden property="ngay_to_chuc_ph" styleId="ngay_to_chuc_ph2" />
  <html:hidden property="trang_thai" styleId="trang_thai"/>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>