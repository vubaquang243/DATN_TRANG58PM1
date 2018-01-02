  <%@ page contentType="text/html;charset=UTF-8"%>
  <%@ page import="java.util.*"%>
  <%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
  <%@ page import="com.seatech.framework.AppConstants"%>
  <%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
  <%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
  <%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPKHOACHPHANHNAMResource"/>
<%@ include file="/includes/tpcp_header.inc"%>

<!--  show mess error -->
<script>
//  jQuery.noConflict();
//  jQuery(document).ready(function () {
//  });
  function check(type) {
      
      var f = document.forms[0];
      f.target = '';
      var guid= $("#guid").val();
//      var guid = document.getElementById("guid").value;
 //     alert(guid);
      //var guid = document.getElementById("guid").value;
      if (type == 'save') {
          if(guid!=null && guid!=""){
            f.action = 'QuanLyKeHoachUpdateExecuteAction.do';
               if(validateForm()){
                  $("#loai_tien").removeAttr("disabled");
                  f.submit();
                }
          }else{
            f.action = 'QuanLyKeHoachExecuteAddAction.do';
               if(validateForm()){
                f.submit();
              }
          }
          
       
      }
      if(type=='saveandsub'){
        if(guid!=null && guid!=""){
            f.action = 'QuanLyKeHoachUpdateExecuteAction.do';
               if(validateForm()){
                  document.getElementById("trang_thai").value="01";
                  $("#loai_tien").removeAttr("disabled");
                  f.submit();
                }
          }else{
            f.action = 'QuanLyKeHoachExecuteAddAction.do';
               if(validateForm()){
               document.getElementById("trang_thai").value="01";
                f.submit();
              }
          }
      }
      if (type == 'close') {
      f.action='ListQuanLyKeHoachAction.do';
        f.submit();
      }
  }
  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
    }
  function validateForm(){
     var nam_ph = document.getElementById("nam_ph").value;
     if(nam_ph == null || nam_ph == ""){
       alert('Bạn phải nhập năm phát hành');
       $("#nam_ph").focus();
       return false;
     }
    var nam_tb = $("#ngay_tbao_kh").val().substring(6, 10);
      if (nam_tb > nam_ph) {
          alert('Năm của ngày thông báo kế hoạch nhỏ hơn hoặc bằng năm phát hành !');
          $("#ngay_tbao_kh").focus();
          return false;
      }
     var tong_khoi_luong = document.getElementById("tong_klph").value;
     if(tong_khoi_luong == null || tong_khoi_luong == ""){
       alert('Bạn phải nhập tổng khối lượng phát hành năm');
       $("#tong_klph").focus();
       return false;
     }
     if(tong_khoi_luong ==0){
       alert("Bạn phải nhập tổng khối lượng lớn hơn 0");
       $("#tong_klph").focus();
       return false;
     }
//     var ky_han = document.getElementById("ky_han").value;
//     if(ky_han == null || ky_han == ""){
//       alert('Bạn phải nhập Kỳ hạn');
//       return false;
//     }
     var ngay_tbao_kh = $("#ngay_tbao_kh").val();
        if (ngay_tbao_kh == null || ngay_tbao_kh == "") {
            alert('Bạn phải nhập ngày thông báo kế hoạch !');
            $("#ngay_tbao_kh").focus();
            return false;
        }
        else {
            var checkDate = validatedate(document.getElementById("ngay_tbao_kh"));
            if (!checkDate) {
                return false;
            }
        }
     var ngay_hieu_luc= $("#ngay_hieu_luc").val();
     if(ngay_hieu_luc != null && ngay_hieu_luc!=""&&ngay_hieu_luc!= "undefined"){
      var startDate, enddate,startTime,endTime,ngay_tbao_time;
      var ngay_tbao_kh1 = $("#ngay_tbao_kh").val();
      var max_date = "31/12/"+nam_ph;
      var min_date ="01/01/"+nam_ph;
      var max_date_time = parseDate(max_date).getTime();
      var min_date_time = parseDate(min_date).getTime();
      var ngay_hieu_luc_time= parseDate(ngay_hieu_luc).getTime();
      if(ngay_hieu_luc_time >= max_date_time){
        alert("Bạn phải nhập ngày hiệu lực nhỏ hơn ngày :"+max_date);
        $("#ngay_hieu_luc").focus();
        return false;
      }
      if(ngay_hieu_luc_time <= min_date_time){
        alert("Bạn phải nhập ngày hiệu lực lớn hơn ngày :"+min_date);
        $("#ngay_hieu_luc").focus();
        return false;
      }
     }
     var kh_cu= '<bean:write name="QuanLyKeHoachForm" property="kh_goc"/>';
     if(kh_cu!=""){
       var ngay_hieu_luc1= $("#ngay_hieu_luc").val();
       if(ngay_hieu_luc1==""){
         alert("Bạn phải nhập ngày hiệu lực");
         $("#ngay_hieu_luc").focus();
         return false;
       }
     }
     var loai_tien = document.getElementById("loai_tien").value;
     if(loai_tien == null || loai_tien == ""){
       alert('Bạn phải nhập loại tiền');
       return false;
     }
//     var ngay_hieu_luc = document.getElementById("ngay_hieu_luc").value;
//     if(ngay_hieu_luc == null || ngay_hieu_luc == ""){
//       alert('Bạn phải nhập Ngày hiệu lực');
//       return false;
//     }
//     var ngay_het_han = document.getElementById("ngay_het_han").value;
//     if(ngay_het_han == null || ngay_het_han == ""){
//       alert('Bạn phải nhập Ngày hết hạn');
//       return false;
//     }
    var fTong_chi_tiet = toNumber($("#tong_chi_tiet").val());
      var fTong_klph = toNumber($("#tong_klph").val());
      if (fTong_klph != fTong_chi_tiet) {
          alert('Bạn phải nhập tổng khối lượng phát hành quý bằng tổng số tiền !');
          $("#tong_klph").focus();
          return false;
      }
    return true;
  }
    function changeTypeMonney(){
    var str = $("#loai_tien").val();
    if(str == 'VND'){
        document.getElementById("loai_tien_usd").style.display = "none";
        document.getElementById("loai_tien_vnd").style.display = "";
        document.getElementById("so_tien_usd").style.display = "none";
        document.getElementById("so_tien_vnd").style.display = "";
    }else if(str == 'USD'){
        document.getElementById("loai_tien_usd").style.display = "";
        document.getElementById("loai_tien_vnd").style.display = "none";
        document.getElementById("so_tien_usd").style.display = "";
        document.getElementById("so_tien_vnd").style.display = "none";
    }else{
        document.getElementById("loai_tien_usd").style.display = "none";
        document.getElementById("loai_tien_vnd").style.display = "";
         document.getElementById("so_tien_usd").style.display = "none";
        document.getElementById("so_tien_vnd").style.display = "";
    }
  }
  $(function () {
      $("#nam_ph").focus();
   
      $("#ngay_tbao_kh").datepicker( {
          dateFormat : "dd/mm/yy"
      });
       $("#ngay_hieu_luc").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      var Loai_tien ='<bean:write name="QuanLyKeHoachForm" property="loai_tien"/>';
      if(Loai_tien!=""){
        if(Loai_tien == 'VND'){
        document.getElementById("loai_tien_usd").style.display = "none";
        document.getElementById("loai_tien_vnd").style.display = "";
        document.getElementById("so_tien_usd").style.display = "none";
        document.getElementById("so_tien_vnd").style.display = "";
    }else if(Loai_tien == 'USD'){
        document.getElementById("loai_tien_usd").style.display = "";
        document.getElementById("loai_tien_vnd").style.display = "none";
        document.getElementById("so_tien_usd").style.display = "";
        document.getElementById("so_tien_vnd").style.display = "none";
    }
      }
      $("#tong_klph").keyup(function (event) {
          formatNumnew(event.target);
      });
      $("#ngay_tbao_kh").on("change",function(){
        var id= $(this).attr("id");
      });
    
  });
//   $("#ngay_hieu_luc").on("change",function(){
//        var id= $(this).attr("id");
//      });
  function calculateSum() {
      var sum = 0;
      //iterate through each textboxes and add the values
      $(".so_tien_ky_han").each(function () {
          //add only if the value is number
          sum += toNumber(this.value);
          //$(this).css("background-color", "#FEFFB0");
      });
      var giatri = replaceCommas(sum);
      $("#tong_chi_tiet").val(giatri);
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
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="QuanLyKeHoachAddAction" method="post">
  <div class="panel-heading border-bottom">
  <logic:empty name="QuanLyKeHoachForm" property="guid">
    <h1 class="panel-title">
      <strong>
        THÊM MỚI KẾ HOẠCH NĂM</strong>
    </h1>
    </logic:empty>
    <logic:notEmpty name="QuanLyKeHoachForm" property="guid">
    <h1 class="panel-title">
      <strong>
        SỬA KẾ HOẠCH NĂM</strong>
    </h1>
    </logic:notEmpty>
  </div>  
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="qlykehoach.add.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <div class="row">
        <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-4 control-label">
              <fmt:message key="tpcp.nam_phat_hanh"/>
              <span style="color:red">(*)</span>
            </label>
            <logic:empty name="QuanLyKeHoachForm" property="guid">
            <div class="col-sm-8">
              <html:text styleClass="form-control"                          
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" 
                        styleId="nam_ph" property="nam_ph" maxlength="4"/>
            </div>
            </logic:empty>
            <logic:notEmpty name="QuanLyKeHoachForm" property="guid">
            <div class="col-sm-8">
              <html:text styleClass="form-control"                     
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);" readonly="true"
                         onblur="textlostfocus(this);" 
                        styleId="nam_ph" property="nam_ph" maxlength="4"/>
            </div>
            </logic:notEmpty>
          </div>
        </div>
                <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-4 control-label">
              Loại tiền 
              <span style="color:red">(*)</span>
            </label>
            <div class="col-sm-8">
            <logic:notEmpty name="QuanLyKeHoachForm" property="guid">
              <html:select property="loai_tien" disabled="true"
                           styleId="loai_tien" onchange="changeTypeMonney()"
                           styleClass="form-control selectpicker"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;">
                              <html:option value="VND">VND</html:option>
                              <html:option value="USD">USD</html:option>
                          </html:select>
              </logic:notEmpty>
              <logic:empty name="QuanLyKeHoachForm" property="guid">
              <html:select property="loai_tien" 
                           styleId="loai_tien" onchange="changeTypeMonney()"
                           styleClass="form-control selectpicker"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;">
                              <html:option value="VND">VND</html:option>
                              <html:option value="USD">USD</html:option>
                          </html:select>
              </logic:empty>
            </div>
          </div>
        </div>
        
          </div>
          <div class="row">
        <div class="col-md-6">
          <div class="form-group">
             <div id="loai_tien_vnd" >
              <label for="hoten" class="col-sm-4 control-label">
                Tổng KLPH năm (tỷ đồng)
                <span style="color:red">(*)</span>
              </label>
              </div>
               <div id="loai_tien_usd" style="display:none;">
              <label for="hoten" class="col-sm-4 control-label">
                Tổng KLPH năm (triệu đô)
                <span style="color:red">(*)</span>
              </label>
              </div>
            <div class="col-sm-8">
              <html:text styleClass="form-control"
                          onkeypress="return blockKeyDT(event)"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" 
                         property="tong_klph" styleId="tong_klph"/>
            </div>
          </div>
        </div>
        
        <!--row 2-->
       
       
         <!--Row 3  -->

        <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Ngày thông báo
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tbao_kh"
                             maxlength="10" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" 
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             property="ngay_tbao_kh"/>
                   
                  <label class="input-group-addon" for="ngay_tbao_kh"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
      
        <logic:notEmpty name="QuanLyKeHoachForm" property="kh_goc">
        <div class="row">
        <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Ngày hiệu lực
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_hieu_luc"
                             maxlength="10" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="6"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             property="ngay_hieu_luc"/>
                   
                  <label class="input-group-addon" for="ngay_hieu_luc"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
        </logic:notEmpty>
        <div class="row">
         <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-4 control-label">
              <fmt:message key="tpcp.mo_ta"/>
              
            </label>
            <div class="col-sm-8">
                <html:textarea styleClass="form-control" rows="2"
                               onkeypress="return blockKeySpecial001(event)"
                               style="background-color: rgb(255, 255, 255); margin: 0px; width: 770px; height: 50px;"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" 
                               styleId="mo_ta" property="mo_ta"/>
              </div>
          </div>
        </div>
        </div>
         <!--Row 5  -->
        <logic:notEmpty  name="QuanLyKeHoachForm" property="ly_do_tu_choi">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.ly_do_tu_choi"/>
              </label>
              <div class="col-sm-8">
                <html:textarea styleClass="form-control" rows="2"
                               onkeypress="return blockKeySpecial001(event)"
                               style="background-color: rgb(255, 255, 255); margin: 0px; width: 770px; height: 50px;"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             
                               styleId="ly_do_tu_choi" property="ly_do_tu_choi" readonly="true"/>
              </div>
            </div>
          </div>
        
        </div>
        </logic:notEmpty>
        <!--Row 6-->
        
        
        
        
      </div>
    </div>
  </div>
    <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        Thông tin chi tiết
      </h2>
    </div>
    <table class="table table-bordered">
      <thead>
        <th>STT</th>
        <th>
          <fmt:message key="tpcp.ky_han"/>
        </th>
        <th>
          <div id="so_tien_vnd" >Số tiền (tỷ đồng)</div>
          <div id="so_tien_usd" style="display:none;">Số tiền (triệu đô)</div>
        </th>
      </thead>
       
      <tbody>
        <logic:empty name="QuanLyKeHoachForm" property="lisKH">
          <tr>
            <td colspan="9" align="center">
              <fmt:message key="qlykehoach.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="QuanLyKeHoachForm" property="lisKH">
          <logic:iterate name="QuanLyKeHoachForm" property="lisKH" id="lisId"
                         indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td width="5%" align="center">
                <%=stt + 1%>
              </td>
              <td width="45%" align="center">
                <bean:write name="lisId" property="name_ky_han"/>
                 
                <html:hidden name="lisId" property="id_ky_han"/>
                 
                <html:hidden name="lisId" property="name_ky_han"/>
              </td>
              <td width="50%" class="center">
                <html:text styleClass="form-control  so_tien_ky_han"
                           onkeyup="formatNumnew(event.target);" tabindex="8"
                           onkeypress="return blockKeyDT(event)"
                           name="lisId" styleId="so_tien" property="so_tien"
                           onblur="calculateSum();" maxlength="20"/>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
        <tr>
          <td></td>
          <td style="text-align:center;font-weight: bold;">Tổng số</td>
          <td class="center">
            <html:text styleClass="form-control" readonly="true" 
                       styleId="tong_chi_tiet" property="tong_chi_tiet"/>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden property="trang_thai" styleId="trang_thai"/>
  <div class="center">
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g"  id="bfind">
      <span class="sortKey">G</span>hi
    </button>
    <button type="button" class="btn btn-default" onclick="check('saveandsub')"
            accesskey="v" id="bfind">
    Ghi <span class="sortKey">v</span>à trình
    </button>
      <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o"  id="bfind">
    Th<span class="sortKey">o</span>át
    </button>
  </div>
   
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>