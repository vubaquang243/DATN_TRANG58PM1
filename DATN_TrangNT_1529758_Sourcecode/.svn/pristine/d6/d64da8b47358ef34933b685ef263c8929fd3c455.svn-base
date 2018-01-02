<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/tpcp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.tp.resource.TTLaiGocVndResource"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/MoneyConvert_vn.js"></script>
<% 
    
%>
<script type="text/javascript">
  //jQuery.noConflict();
  jQuery(document).ready(function () {
  });

  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
  }

  function validateForm() {
      var ngay_thanh_toan_tu_ngay = $("#ngay_thanh_toan_tu_ngay").val();
      var ngay_thanh_toan_den_ngay = $("#ngay_thanh_toan_den_ngay").val();

      if (ngay_thanh_toan_tu_ngay == null && ngay_thanh_toan_tu_ngay == '') {
          alert('Vui lòng nhập Ngày thanh toán từ ngày !');
          $("#ngay_thanh_toan_tu_ngay").focus();
          return false;
      }
      if (ngay_thanh_toan_den_ngay == null && ngay_thanh_toan_den_ngay == '') {
          alert('Vui lòng nhập Ngày thanh toán đến ngày !');
          $("#ngay_thanh_toan_den_ngay").focus();
          return false;
      }
      if ((ngay_thanh_toan_tu_ngay != null && ngay_thanh_toan_tu_ngay != "") && (ngay_thanh_toan_den_ngay != null && ngay_thanh_toan_den_ngay != "")) {
          var startDate = parseDate(ngay_thanh_toan_tu_ngay).getTime();
          var endDate = parseDate(ngay_thanh_toan_den_ngay).getTime();
          if (startDate > endDate) {
              alert('Ngày thanh toán đến ngày phải lớn hơn Ngày thanh toán từ ngày !');
              $("#ngay_thanh_toan_tu_ngay").focus();
              return false;
          }
      }
      return true;
  }

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'ghi') {
          f.action = 'UpdateLenhTraNoAction.do';
          f.submit();
      }else if (type == 'ghivstrinh') {
          f.action = 'UpdateLenhTraNoAction.do';
          $("#trang_thai").val('01');
          f.submit();
      }else if (type == 'huy') {
          f.action = 'DeleteLenhTraNoAction.do';
          f.submit();
      }else if (type == 'print') {
          if (validateForm()) {
              f.action = 'PrintLenhChiTraNoAction.do';
              var ten_dvi_nhan= $("#dvi_nhan option:selected").text();
              $("#ten_dvi_nhan").val(ten_dvi_nhan);
              var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
              newDialog = window.open('', '_form', params);
              f.target = '_form';
              f.submit();
          }
      }else{
        f.action="ListLenhTraNoAction.do";
        f.submit();
      }
  }

  function view(id_lenh) {
      var f = document.forms[0];
      f.action = 'SearchLenhTraNoAction.do'
      f.submit();
  }
  function changeDVTT(obj){
     var dvtt=obj.value.trim();
     $.ajax( {
          type : "post", url : "GetAjaxDonViNhanAction.do", data :  {
              longid : dvtt
          },
          async : true, cache : false, success : function (data) {
              var obj = JSON.parse(data);
              $("#so_tk_nhan").val(obj.so_tk);
              $("#nh_nhan").val(obj.ten_nh);
          }
      });
  }
  $(function () {
      
      $("#ngay_chuyen").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_den_han_tt").datepicker( {
          dateFormat : "dd/mm/yy"
      });
      $("#ngay_chuyen").on("change",function(){
        var id=$(this).attr("id");        
       });
      $("#ngay_den_han_tt").on("change",function(){
        var id=$(this).attr("id");        
       });
       $("#nam_ns").keyup(function (event) {
          onkeydownnodot(event);
      });
      var tong_tien ='<bean:write name="LenhTraNoForm" property="tong_tien"/>';
      $("#tong_tien_chu").val(toEnglishCash(toNumber(tong_tien)));
      document.getElementById("dvi_nhan").value = '<bean:write name="LenhTraNoForm" property="dvi_nhan"/>';
      $('.selectpicker').selectpicker('refresh');
      var trang_thai='<bean:write name="LenhTraNoForm" property="trang_thai"/>';
      if(trang_thai=='01'||trang_thai=='02'){
          $('#dvi_nhan').attr('disabled',true);
          $('#can_cu').attr('readonly',true);
          $('#nh_nhan').attr('readonly',true);
          $('#dvi_nhan1').val('<bean:write name="LenhTraNoForm" property="dvi_nhan"/>');
          $("input.notedit").attr("disabled", true);
          $('.selectpicker').selectpicker('refresh');
      }else{
          $("#nam_ns").focus();
      }
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/ListLenhTraNoAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>Ho&agrave;n thiện lệnh chi trả nợ trong nước bằng VNĐ</strong>
    </h1>
  </div>
  <div class="app_error">
    <logic:messagesPresent message="true">
      <html:messages id="message" message="true">
        <logic:present name="message">
          <!-- Messages <bean:write name='message' filter='false'/> -->
          <div class="messages">
            <fmt:message key="<%=message%>"/>
          </div>
        </logic:present>
      </html:messages>
    </logic:messagesPresent>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">Th&ocirc;ng tin chung</h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="thanhtoanlaigoc.so_lenh"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control notedit" onfocus="textfocus(this);"
                           styleId="so_lenh" onblur="textlostfocus(this);"
                           tabindex="1" property="so_lenh" readonly="true"/>
                <html:hidden property="so_lenh"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="thanhtoanlaigoc.dvi_nhan"/>
              </label>
              <div class="col-sm-8">
                <html:select property="dvi_nhan" styleId="dvi_nhan" onchange="changeDVTT(this)"
                             styleClass="form-control selectpicker notedit" tabindex="3"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <logic:notEmpty name="listDonViTT">
                    <html:optionsCollection name="listDonViTT" value="ma"
                                            label="ten"/>
                  </logic:notEmpty>
                </html:select>
                <html:hidden property="dvi_nhan" styleId="dvi_nhan1"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="thanhtoanlaigoc.nam_ns"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control notedit" onfocus="textfocus(this);"
                           styleId="nam_ns" onblur="textlostfocus(this);"
                           tabindex="3" property="nam_ns"/>
                           <html:hidden property="nam_ns"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">T&agrave;i khoản
                                                                số</label>
              <div class="col-sm-8">
                <html:text styleClass="form-control notedit" onfocus="textfocus(this);"
                           styleId="so_tk_nhan" onblur="textlostfocus(this);"
                           tabindex="4" property="so_tk_nhan"/>
                           <html:hidden property="so_tk_nhan"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Căn cứ</label>
              <div class="col-sm-8">
                <html:textarea styleClass="form-control"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="5"
                               styleId="can_cu" property="can_cu"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Nơi mở
                                                                t&agrave;i khoản</label>
              <div class="col-sm-8">
                <html:textarea styleClass="form-control"
                               onkeyup="doFormat(event)"
                               onfocus="textfocus(this);"
                               onblur="textlostfocus(this);" tabindex="6"
                               styleId="nh_nhan" property="nh_nhan"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y đến
                                                                hạn TT</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control notedit" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="7"
                             styleId="ngay_den_han_tt"
                             property="ngay_den_han_tt" maxlength="10"/>
                   <html:hidden property="ngay_den_han_tt"/>
                   <label class="input-group-addon" for="ngay_den_han_tt"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">Ng&agrave;y
                                                                chuyển tiền</label>
              <div class="col-sm-8">
                <div class="input-group date">
                  <html:text styleClass="form-control notedit" onkeyup="doFormat(event)"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="8"
                             styleId="ngay_chuyen" property="ngay_chuyen"
                             maxlength="10"/>
                   <html:hidden property="ngay_chuyen"/>
                  <label class="input-group-addon" for="ngay_chuyen"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
        
      </div>
    </div>
  </div>
   <html:hidden styleId="so_tien_vnd" property="so_tien_vnd"/>
  <html:hidden styleId="tong_tien_chu" property="tong_tien_chu" value=""/>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2 class="panel-title">Th&ocirc;ng tin chi tiết</h2>
    </div>
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">Nội dung thanh to&aacute;n</th>
        <th>M&atilde; NDKT</th>
        <th>M&atilde; chương</th>
        <th>M&atilde; ng&agrave;nh KT</th>
        <th>M&atilde; CTMT, DA v&agrave; HTCT</th>
        <th>M&atilde; nguồn NSNN</th>
        <th>Số tiền (đồng)</th>
      </thead>
       
      <tbody>
        <logic:empty property="list_Lenh_tra_no_ct" name="LenhTraNoForm">
          <tr>
            <td colspan="16" align="center">
              <fmt:message key="thanhtoanlaigoc.list.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty property="list_Lenh_tra_no_ct" name="LenhTraNoForm">
          <logic:iterate id="objTPCP" property="list_Lenh_tra_no_ct"
                         name="LenhTraNoForm" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
              <td>
                <bean:write name="objTPCP" property="noi_dung"/>
              </td>
              
              <td align="center">
                <html:text name="objTPCP" styleClass="form-control notedit" styleId="ndkt"  property="ndkt" />
                <html:hidden name="objTPCP"  property="guid_ct"/>
              </td>
              <td align="center">
                <html:text name="objTPCP" styleClass="form-control notedit" property="chuong"/>
              </td>
              <td align="center">
                <html:text name="objTPCP" styleClass="form-control notedit" property="nganh_kt"/>
              </td>
              <td align="center">
                <html:text name="objTPCP" styleClass="form-control notedit" property="ctmt"/>
              </td>
              <td align="center">
                <html:text name="objTPCP" styleClass="form-control notedit" property="nguon"/>
              </td>
              
              <td align="center">
                <bean:write name="objTPCP" property="so_tien_vnd"/>
              </td>
            </tr>
            
          </logic:iterate>
        </logic:notEmpty>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </div>
  <div class="button-group center">
    <logic:equal value="00" property="trang_thai" name="LenhTraNoForm">
    <button id="tracuu" type="button" onclick="check('ghi')"
            class="btn btn-default" accesskey="g">
      <span class="sortKey">G</span>
      hi 
    </button>
    <button id="tracuu" type="button" onclick="check('ghivstrinh')"
            class="btn btn-default" accesskey="v">
      Ghi <span class="sortKey">v</span>à tr&igrave;nh
    </button>
     
    <button id="tracuu" type="button" onclick="check('huy')"
            class="btn btn-default" accesskey="h">
      <span class="sortKey">H</span>ủy
    </button>
     </logic:equal>
     <logic:equal value="03" property="trang_thai" name="LenhTraNoForm">
    <button id="tracuu" type="button" onclick="check('ghi')"
            class="btn btn-default" accesskey="g">
      <span class="sortKey">G</span>
      hi 
    </button>
    <button id="tracuu" type="button" onclick="check('ghivstrinh')"
            class="btn btn-default" accesskey="v">
      Ghi <span class="sortKey">v</span>à tr&igrave;nh
    </button>
     
    <button id="tracuu" type="button" onclick="check('huy')"
            class="btn btn-default" accesskey="h">
      <span class="sortKey">H</span>ủy
    </button>
     </logic:equal>
      <button type="button" onclick="check('print')" class="btn btn-default"
              accesskey="i">
        <span class="sortKey">I</span>n
      </button>
      <button type="button" onclick="check('close')" class="btn btn-default"
              accesskey="o">Th<span class="sortKey">o</span>át
      </button>
  </div>
  <html:hidden property="trang_thai" name="LenhTraNoForm" styleId="trang_thai"/>
  <html:hidden property="guid" name="LenhTraNoForm"/>
  <html:hidden property="loai_tt" name="LenhTraNoForm"/>
  <html:hidden property="ten_dvi_nhan" styleId="ten_dvi_nhan"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>