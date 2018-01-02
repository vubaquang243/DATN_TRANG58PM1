<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyKeHoachQuy"/>
<%@ include file="/includes/tpcp_header.inc"%>
<script>
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      if (type == 'save') {
          if (guid != null && guid != "") {
              f.action = 'QuanLyKeHoachQuyUpdateExecuteAction.do';
          }
          else {
              f.action = 'QuanLyKeHoachQuyChangeExcAction.do';
          }
          if (validateForm()) {
              f.submit();
          }
      }
      if(type== 'saveandsub'){
        if (guid != null && guid != "") {
              
              f.action = 'QuanLyKeHoachQuyUpdateExecuteAction.do';
          }
          else {
             
              f.action = 'QuanLyKeHoachQuyExecuteAddAction.do';
          }
          if (validateForm()) {
           document.getElementById("trang_thai").value="01";
              f.submit();
          }
      }
      if (type == 'close') {
          f.action='ListQuanLyKeHoachQuyAction.do';
          f.submit();
      }
  }

  function validateForm() {
      var nam_ph = $("#nam_ph").val();
      if (nam_ph == null || nam_ph == "") {
          alert('Bạn phải nhập năm phát hành !');
          $("#nam_ph").focus();
          return false;
      }
      
      
      var thoi_gian_ph = $("#thoi_gian_ph").val();
      if (thoi_gian_ph == null || thoi_gian_ph == "") {
          alert('Bạn phải chọn quý phát hành !');
          $("#thoi_gian_ph").focus();
          return false;
      }
      var tong_klph = $("#tong_klph").val();
      if (tong_klph == null || tong_klph == "") {
          alert('Bạn phải nhập tổng mức phát hành quý !');
          $("#tong_klph").focus();
          return false;
      }
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
      var ngay_hieu_luc = $("#ngay_hieu_luc").val();
      if(ngay_hieu_luc==0){
        alert("Bạn phải nhập ngày hiệu lực");
        $("#ngay_hieu_luc").focus();
        return false;
      }
      if(!isDate(ngay_hieu_luc)){
        alert("Bạn nhập sai định dạnh ngày hiệu lực");
        $("#ngay_hieu_luc").focus();
        return false;
      }
      var nam_tb = $("#ngay_tbao_kh").val().substring(6, 10);
      if (nam_tb > nam_ph) {
          alert('Năm ngày thông báo kế hoạch nhỏ hơn hoặc bằng năm phát hành !');
          $("#ngay_tbao_kh").focus();
          return false;
      }

      var fTong_chi_tiet = toNumber($("#tong_chi_tiet").val());
      var fTong_klph = toNumber($("#tong_klph").val());
      if (fTong_klph != fTong_chi_tiet) {
          alert('Bạn phải nhập tổng khối lượng phát hành quý bằng tổng số tiền !');
          $("#tong_klph").focus();
          return false;
      }
           var startDate, enddate,startTime,endTime,ngay_tbao_time;
      var ngay_tbao_kh1 = $("#ngay_tbao_kh").val();
      if(thoi_gian_ph ==1){
        startDate = "01/01/"+nam_ph;
        enddate = "31/03/"+ nam_ph;
         startTime = parseDate(startDate).getTime();
         endTime = parseDate(enddate).getTime();
         ngay_tbao_time = parseDate(ngay_tbao_kh1).getTime();
        if(ngay_tbao_time > startDate){
          alert('Bạn phải nhập ngày thông báo kế hoạch nhỏ hơn hoặc bằng ngày hiệu lực kế hoạch  ');
          $("#ngay_tbao_kh").focus();
          return false;
        }
        
      }
       if(thoi_gian_ph ==2){
        startDate = "01/04/"+nam_ph;
        enddate = "30/06/"+ nam_ph;
         startTime = parseDate(startDate).getTime();
         endTime = parseDate(enddate).getTime();
         ngay_tbao_time = parseDate(ngay_tbao_kh1).getTime();
        if(ngay_tbao_time > startDate){
          alert('Bạn phải nhập ngày thông báo kế hoạch nhỏ hơn hoặc bằng ngày hiệu lực kế hoạch  ');
          $("#ngay_tbao_kh").focus();
          return false;
        }
        
      }
      if(thoi_gian_ph ==3){
        startDate = "01 /07/"+nam_ph;
        enddate = "30/09/"+ nam_ph;
         startTime = parseDate(startDate).getTime();
         endTime = parseDate(enddate).getTime();
        ngay_tbao_time = parseDate(ngay_tbao_kh1).getTime();
        if(ngay_tbao_time > startDate){
          alert('Bạn phải nhập ngày thông báo kế hoạch nhỏ hơn hoặc bằng ngày hiệu lực kế hoạch  ');
          $("#ngay_tbao_kh").focus();
          return false;
        }
        
      }
       if(thoi_gian_ph ==4){
        startDate = "01/10/"+nam_ph;
        enddate = "31/12/"+ nam_ph;
         startTime = parseDate(startDate).getTime();
         endTime = parseDate(enddate).getTime();
         ngay_tbao_time = parseDate(ngay_tbao_kh1).getTime();
        if(ngay_tbao_time > startDate){
          alert('Bạn phải nhập ngày thông báo kế hoạch nhỏ hơn hoặc bằng ngày hiệu lực kế hoạch ');
          $("#ngay_tbao_kh").focus();
          return false;
        }
        
      }
      return true;
  }
    function isDate(txtDate){
    
        var currVal = txtDate;
        if(currVal == ''){
            return false;
        }
        var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
        var dtArray = currVal.match(rxDatePattern); // is format OK?
        
        if (dtArray == null){
            return false;
        }
        dtDay = dtArray[1];
        dtMonth= dtArray[3];
        dtYear = dtArray[5];    
         if (dtMonth < 1 || dtMonth > 12) {
            return false;
        }
        else if (dtDay < 1 || dtDay> 31) {
            return false;
        }
        else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31) {
            return false;
        }
        else if (dtMonth == 2) {

        	var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
            if (dtDay> 29 || (dtDay ==29 && !isleap)) {
            		return false;
            }           
        }   
        return true;  
    }
 function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
    }
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
    
  $(function () {
      $("#nam_ph").focus();
      $("#ngay_het_han").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      
      $("#thoi_gian_ph").val('<bean:write name="QuanLyKeHoachQuyForm" property="thoi_gian_ph"/>').change();
      $("#ngay_hieu_luc").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_tbao_kh").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#tong_klph").keyup(function (event) {
          formatNumnew(event.target);
      });
      $("#ngay_tbao_kh").on("change",function(){
        var id= $(this).attr("id");
      });
      $("#ngay_hieu_luc").on("change",function(){
        var id= $(this).attr("id");
      });
  });
 
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
<html:form action="QuanLyKeHoachQuyAddAction" method="post">
  <div class="panel-heading border-bottom">
  
    <h1 class="panel-title">
      <strong>
        <fmt:message key="tpcp.title.thay.the"/></strong>
    </h1>
    

  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="qlykehoach.add.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.nam_phat_hanh"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-8">
            <html:hidden property="nam_ph" name="QuanLyKeHoachQuyForm"/>
           <logic:notEmpty name="QuanLyKeHoachQuyForm" property="nam_ph">
                <html:text styleClass="form-control" maxlength="4"
                           onkeypress=" return isNumberKey(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                            name="QuanLyKeHoachQuyForm"
                            tabindex="1"
                           styleId="nam_ph" property="nam_ph" readonly="true"/>
         
          </logic:notEmpty>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.quy_phat_hanh"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-8">
                <html:hidden property="thoi_gian_ph" name="QuanLyKeHoachQuyForm" />
                <html:select property="thoi_gian_ph" styleId="thoi_gian_ph" 
                             tabindex="2" styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" disabled="true">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="1">Qu&yacute; I</html:option>
                  <html:option value="2">Qu&yacute; II</html:option>
                  <html:option value="3">Qu&yacute; III</html:option>
                  <html:option value="4">Qu&yacute; IV</html:option>
                </html:select>
                
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
       <logic:equal value="VND" name="QuanLyKeHoachQuyForm" property="loai_tien">
         <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.tong_khoi_luong_vnd"/>
                <span style="color:red">(*)</span>
              </label>
          </logic:equal>
   
          <logic:equal value="USD" name="QuanLyKeHoachQuyForm" property="loai_tien">
         <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.tong_khoi_luong_usd"/>
                <span style="color:red">(*)</span>
              </label>
          </logic:equal>
              <div class="col-sm-8">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="3"
                           styleId="tong_klph" property="tong_klph"
                           maxlength="20"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.loai_tien"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-8">
                
                <html:text property="loai_tien" styleId="loai_tien" name="QuanLyKeHoachQuyForm"
                             tabindex="4" styleClass="form-control "
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true">
                  
                </html:text>
              </div>
            </div>
          </div>
        </div>
        <!--Row 4-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.so_thong_bao"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;" 
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="5"
                           styleId="so_tbao_kh" property="so_tbao_kh"
                           maxlength="20" />
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.ngay_hieu_luc"/>
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
        <!--row 5-->
        <div class="row">
         <logic:notEqual value="" name="QuanLyKeHoachQuyForm" property="so_tbao_kh_cu">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.so_thong_bao_cu"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="so_tbao_kh_cu"
                           name="QuanLyKeHoachQuyForm" readonly="true"/>
              </div>
            </div>
          </div>
        </logic:notEqual>
         <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.ngay_thong_bao"/>
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tbao_kh"
                             maxlength="10" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="6"
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
        <div class="row">
        
        </div>
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
                               onblur="textlostfocus(this);" tabindex="7"
                               styleId="mo_ta" property="mo_ta"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--List thông tin chi tiết-->
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">
        <fmt:message key="qlykehoach.add.list"/>
      </h2>
    </div>
    <table class="table table-bordered">
      <thead>
        <th>STT</th>
        <th>
          <fmt:message key="tpcp.ky_han"/>
        </th>
        <th>
        <logic:equal value="VND" name="QuanLyKeHoachQuyForm" property="loai_tien">
          <fmt:message key="tpcp.so_tien_vnd"/>
          </logic:equal>
          <logic:equal value="USD" name="QuanLyKeHoachQuyForm" property="loai_tien">
          <fmt:message key="tpcp.so_tien_usd"/>
          </logic:equal>
        </th>
      </thead>
       
      <tbody>
        <logic:empty name="QuanLyKeHoachQuyForm" property="lisKH">
          <tr>
            <td colspan="9" align="center">
              <fmt:message key="qlykehoach.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="QuanLyKeHoachQuyForm" property="lisKH">
          <logic:iterate name="QuanLyKeHoachQuyForm" property="lisKH" id="lisId"
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
            <html:text styleClass="form-control" readonly="true" tabindex="8"
                       styleId="tong_chi_tiet" property="tong_chi_tiet"/>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  
  <html:hidden property="kh_goc" name="QuanLyKeHoachQuyForm"/>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden property="trang_thai" styleId="trang_thai"/>
  <div class="center">
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g" id="bfind">
      <span class="sortKey">G</span>hi
    </button>
     <button type="button" class="btn btn-default" onclick="check('saveandsub')"
            accesskey="v" id="bfind">
      Ghi <span class="sortKey">v</span>à trình
    </button>
    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" id="bfind">
      Th<span class="sortKey">o</span>át
    </button>
    
  
  </div>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>