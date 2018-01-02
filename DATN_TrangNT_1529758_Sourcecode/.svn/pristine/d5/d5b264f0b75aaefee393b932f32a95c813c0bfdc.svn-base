  
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
  %>
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      f.action = 'PheDuyetExcTTinDThauAction.do';
      if (type == 'pheduyet') {
          document.getElementById("trang_thai").value = '02';
          f.submit();
      }
      else if (type == 'tuchoi'){
          
              if($("#ly_do_tu_choi")==null || $("#ly_do_tu_choi").val()==''){
                alert('Vui lòng nhập lý do từ chối !');
                $("#ly_do_tu_choi").focus();
                return false;
              }else{
                document.getElementById("trang_thai").value = '03';
                f.submit();
              }
      }
      if(type=='close'){
        f.action='PheDuyetTTinDThauAction.do';
        f.submit();
        }
  }
  $(function () {
      var ky_tra_lai = '<bean:write name="ThongTinDauThauForm" property="ky_tra_lai"/>';
      var trang_thai = '<bean:write name="ThongTinDauThauForm" property="trang_thai"/>';
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
//      $("#ly_do").hide();
      var ls_danh_nghia = '<bean:write name="ThongTinDauThauForm" property="ls_danh_nghia"/>';
      if (ls_danh_nghia != null && ls_danh_nghia != '') {
          $("#div_ls").show();
      }
      if('02'!=trang_thai){
        $("#ly_do").css("display","block");
      }
      document.getElementById("ky_tra_lai").value = '<bean:write name="ThongTinDauThauForm" property="ky_tra_lai"/>';
      document.getElementById("hinh_thuc_dt").value = '<bean:write name="ThongTinDauThauForm" property="hinh_thuc_dt"/>';
      document.getElementById("pthuc_xacdinh_kqdt").value = '<bean:write name="ThongTinDauThauForm" property="pthuc_xacdinh_kqdt"/>';
      document.getElementById("loai_tien").value = '<bean:write name="ThongTinDauThauForm" property="loai_tien"/>';
      document.getElementById("ky_han").value = '<bean:write name="ThongTinDauThauForm" property="ky_han"/>';
      document.getElementById("ma_tpcp").value = '<bean:write name="ThongTinDauThauForm" property="ma_tpcp"/>';
      $('.selectpicker').selectpicker('refresh');
  });
</script>
<html:form action="AddExeTTinDThauAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>PHÊ DUYỆT THÔNG TIN ĐẤU THẦU</strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
  <div class="panel-heading">
      <h2 class="panel-title">
        Thông tin đấu thầu
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
            </label>
            <div class="col-sm-7">
              <html:text styleClass="form-control"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="1"
                         styleId="dot_dau_thau" property="dot_dau_thau"
                         maxlength="10" readonly="true"/>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-5 control-label">
              <fmt:message key="ttindthau.dot_bo_sung"/>
              &nbsp;
            </label>
            <div class="col-sm-7">
              <html:text styleClass="form-control"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="2"
                         styleId="dot_bo_sung" property="dot_bo_sung"
                         maxlength="10" readonly="true"/>
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
            </label>
            <div class="col-sm-7">
              <html:select property="ma_tpcp" styleId="ma_tpcp"
                           styleClass="form-control selectpicker" tabindex="3"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onchange="change_ma_tp(this)" disabled="true">
                <logic:notEmpty name="lstAllTPCP">
                  <html:optionsCollection name="lstAllTPCP" value="ma_tp"
                                          label="ma_tp"/>
                </logic:notEmpty>
              </html:select>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-5 control-label">
              Khối lượng gọi thầu
              &nbsp;
            </label>
            <div class="col-sm-7">
              <html:text styleClass="form-control"
                         onkeypress="return blockKeySpecial001(event)"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="4"
                         styleId="khoi_luong_tp" property="khoi_luong_tp"
                         readonly="true"/>
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
            </label>
            <div class="col-sm-7">
              <html:select property="loai_tien" styleId="loai_tien"
                           disabled="true"
                           styleClass="form-control selectpicker" tabindex="5"
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
              <fmt:message key="ttindthau.ky_han"/>
              &nbsp;
            </label>
            <div class="col-sm-7">
              <html:select property="ky_han" styleId="ky_han"
                           styleClass="form-control selectpicker" tabindex="6"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           disabled="true">
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
              <fmt:message key="ttindthau.ngay_to_chuc_phanh"/>
              &nbsp;
            </label>
            <div class="col-sm-7">
              <div class="input-group date">
                <html:text styleClass="form-control" styleId="ngay_to_chuc_ph"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           tabindex="7" readonly="true"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);"
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
            </label>
            <div class="col-sm-7">
              <div class="input-group date">
                <html:text styleClass="form-control" styleId="ngay_ph"
                           readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
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
            </label>
            <div class="col-sm-7">
              <div class="input-group date">
                <html:text styleClass="form-control" styleId="ngay_tt_tien_mua"
                           readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           tabindex="9" onfocus="textfocus(this);"
                           onblur="textlostfocus(this);"
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
            </label>
            <div class="col-sm-7">
              <html:select property="ky_tra_lai" styleId="ky_tra_lai"
                           tabindex="10" disabled="true"
                           styleClass="form-control selectpicker"
                           onchange="change_ky_tra_lai(this)">
                <html:option value="0">Không trả lãi định kỳ</html:option>
                <html:option value="1">Trả lãi định kỳ 12 tháng 1 lần</html:option>
                <html:option value="2">Trả lãi định kỳ 6 tháng 1 lần</html:option>
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
              <fmt:message key="ttindthau.ngay_tt_lai_1"/>
              &nbsp;
            </label>
            <div class="col-sm-7">
              <div class="input-group date">
                <html:text styleClass="form-control" styleId="ngay_tt_lai_1"
                           readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           tabindex="11" onfocus="textfocus(this);"
                           onblur="textlostfocus(this);"
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
            </label>
            <div class="col-sm-7">
              <div class="input-group date">
                <html:text styleClass="form-control" styleId="ngay_tt_lai_2"
                           readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           tabindex="12" onfocus="textfocus(this);"
                           onblur="textlostfocus(this);"
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
            </label>
            <div class="col-sm-7">
              <div class="input-group date">
                <html:text styleClass="form-control" styleId="ngay_dao_han"
                           readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           tabindex="12" onfocus="textfocus(this);"
                           onblur="textlostfocus(this);"
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
            </label>
            <div class="col-sm-7">
              <html:text styleClass="form-control"
                         onkeypress="return blockKeySpecial001(event)"
                         onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="14"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         property="menh_gia" styleId="menh_gia"
                         readonly="true"/>
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
            </label>
            <div class="col-sm-7">
              <html:select property="hinh_thuc_dt" styleId="hinh_thuc_dt"
                           disabled="true"
                           styleClass="form-control selectpicker" tabindex="15"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;">
                <html:option value="">All</html:option>
                <html:option value="01">Đấu thầu cạnh tranh l&atilde;i suất</html:option>
                <html:option value="02">Đấu thầu kết hợp cạnh tranh l&atilde;i
                                        suất v&agrave; kh&ocirc;ng cạnh tranh
                                        l&atilde;i suất</html:option>
              </html:select>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
            <label for="" class="col-sm-5 control-label">
              Phương thức xác định KQĐT
              &nbsp;
            </label>
            <div class="col-sm-7">
              <html:select property="pthuc_xacdinh_kqdt" disabled="true"
                           styleId="pthuc_xacdinh_kqdt" tabindex="16"
                           styleClass="form-control selectpicker"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;">
                <html:option value="01">Đơn gi&aacute;</html:option>
                <html:option value="02">Đa gi&aacute;</html:option>
              </html:select>
            </div>
          </div>
        </div>
      </div>
      <!--Row 8-->
      <div class="row">
        <div class="col-md-6">
          <div class="form-group">
            <label for="lai_suat" class="col-sm-5 control-label">
              TK nhận tiền mua TP
            </label>
            <div class="col-sm-7">
              <html:text styleClass="form-control"
                         onkeypress="return blockKeySpecial001(event)"
                         readonly="true" onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="17"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         property="so_tk_nhan" styleId="so_tk_nhan"/>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-5 control-label">
              <fmt:message key="ttindthau.ngay_tbao_goi_thau"/>&nbsp;
               
            </label>
            <div class="col-sm-7">
              <div class="input-group date">
                <html:text styleClass="form-control"
                           styleId="ngay_tbao_goi_thau" readonly="true"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           tabindex="18" onfocus="textfocus(this);"
                           onblur="textlostfocus(this);"
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
       <div class="col-md-6" id="div_ls">
          <div class="form-group" id="div_ls_danh_nghia">
            <label for="hoten" class="col-sm-5 control-label">
                <logic:notEmpty name="ThongTinDauThauForm" property="khoi_luong_them">
                <logic:empty name="ThongTinDauThauForm" property="dot_bo_sung">
                  Lãi suất phát hành
                  </logic:empty>
                </logic:notEmpty>
                <logic:notEmpty name="ThongTinDauThauForm" property="khoi_luong_them">
                <logic:notEmpty name="ThongTinDauThauForm" property="dot_bo_sung">
                  Lãi suất danh nghĩa
                  </logic:notEmpty>
                </logic:notEmpty>
                 <logic:empty name="ThongTinDauThauForm" property="khoi_luong_them">
                  Lãi suất danh nghĩa
                </logic:empty>
            </label>
            <div class="col-sm-7">
              <html:text styleClass="form-control" styleId="ls_danh_nghia"
                         readonly="true" onfocus="textfocus(this);"
                         onblur="textlostfocus(this);" tabindex="21"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         property="ls_danh_nghia"/>
            </div>
          </div>
        </div>
        
          <logic:notEqual value="0" name="ThongTinDauThauForm" property="khoi_luong_them">
        <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                Khối lượng phát hành thêm
                &nbsp;
                
              </label>
              
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" maxlength="20" readonly="true"
                           onblur="textlostfocus(this);" tabindex="4"
                           styleId="khoi_luong_them" property="khoi_luong_them"/>
              </div>
            </div>
          </div>
          </logic:notEqual>
      </div>
      <logic:notEmpty name="kl_goi_thau_lan_dau">
      <div class="row">
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
      </div>
      </logic:notEmpty>
      <div class="row">
      <div class="col-md-6">
          <div class="form-group">
            <label for="hoten" class="col-sm-5 control-label">
              <fmt:message key="ttindthau.ghi_chu"/>
            </label>
            <div class="col-sm-7">
              <html:textarea styleClass="form-control" readonly="true"
                             onfocus="textfocus(this);" style="width:736px;"
                             onblur="textlostfocus(this);" tabindex="19"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             property="ghi_chu"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6" id="ly_do" style="display:none;">
          <div class="form-group">
            <label for="hoten" class="col-sm-5 control-label">
              <fmt:message key="ttindthau.ly_do_tu_choi"/>
            </label>
            <div class="col-sm-7">
              <html:textarea styleClass="form-control" 
                             onfocus="textfocus(this);" style="width:736px;"
                             onblur="textlostfocus(this);" tabindex="22"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             styleId="ly_do_tu_choi" property="ly_do_tu_choi"/>
            </div>
          </div>
        </div>
       
      </div>
    </div>
  </div>
  </div>
  <div class="center">
  <logic:equal value="01" name="ThongTinDauThauForm" property="trang_thai">
    <button id="tracuu" type="button" onclick="check('pheduyet')"
            class="btn btn-default" accesskey="d">
      <span class="sortKey">D</span>uyệt
    </button>
     
    <button id="tracuu" type="button" onclick="check('tuchoi')"
            class="btn btn-default" accesskey="c">
      Từ <span class="sortKey">c</span>hối
    </button>
    
    </logic:equal>
     <button id="thoat" type="button" onclick="check('close')"
            class="btn btn-default" accesskey="c">
      Th<span class="sortKey">o</span>át
    </button>
  
  </div>
  <html:hidden property="trang_thai" styleId="trang_thai" value="2"/>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>