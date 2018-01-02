 
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
  %>
  
<script type="text/javascript">
  function check(type){
          var f = document.forms[0];
          f.target = "";
          if(type == 'println' ){
              $("#ky_han").removeAttr("disabled");
              document.getElementById("ma_tpcp1").value= '<bean:write property="ma_tpcp" name="ThongTinDauThauForm" />';
              f.action = 'PrintThongTinDauThauAction.do';
              var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
              newDialog = window.open('', '_form', params);
              f.target = '_form';
              f.submit();
          }
          if(type=='congbo'){
            f.action ="ViewCongBoTTinDThauAction.do?longid="+'<bean:write property="guid" name="ThongTinDauThauForm" />';
            f.submit();
          }
          if(type=='trinh'){
            
            f.action ="TrinhTTinDThauAction.do?longid="+'<bean:write property="guid" name="ThongTinDauThauForm" />';
            f.submit();
          }
          if(type=='xem'){
          document.getElementById("trang_thai").value= '<bean:write property="trang_thai" name="ThongTinDauThauForm" />'
            f.action="viewNoiDungCB.do?longid=" + '<bean:write property="guid" name="ThongTinDauThauForm" />';
            f.submit();
          }
          if(type=='close'){
            f.action="ListTTinDThauAction.do";
            f.submit();
          }

      }
     
  $(function () {
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
      
      

      var ls_danh_nghia = '<bean:write name="ThongTinDauThauForm" property="ls_danh_nghia"/>';
      if (ls_danh_nghia != null && ls_danh_nghia != '') {
          $("#div_ls").show();
      }else{
        $("#div_ls").hide();
      }
      // đọc số tiền ra chữ
       var khoi_luong_tp ='<bean:write name="ThongTinDauThauForm" property="khoi_luong_tp"/>';
       var so_tien_chu =toEnglishCash(toNumber(khoi_luong_tp)).toLowerCase();
       var str = so_tien_chu.replace("đồng","");
        var loai_tien='<bean:write name="ThongTinDauThauForm" property="loai_tien"/>';
        if(loai_tien=='VND'){
           $("#khoi_luong_tp_chu").val(str+" đồng");
        }else{
          $("#khoi_luong_tp_chu").val(str+" đô");
        }
      var menh_gia ='<bean:write name="ThongTinDauThauForm" property="menh_gia"/>';
      $("#menh_gia_chu").val(toEnglishCash(toNumber(menh_gia)).toLowerCase());
      
      
      
      $('.selectpicker').selectpicker('refresh');
  });
//      document.getElementById("ky_tra_lai").value = '<bean:write name="ThongTinDauThauForm" property="ky_tra_lai"/>';
//      document.getElementById("hinh_thuc_dt").value = '<bean:write name="ThongTinDauThauForm" property="hinh_thuc_dt"/>';
//      document.getElementById("pthuc_xacdinh_kqdt").value = '<bean:write name="ThongTinDauThauForm" property="pthuc_xacdinh_kqdt"/>';
//      document.getElementById("loai_tien").value = '<bean:write name="ThongTinDauThauForm" property="loai_tien"/>';
//      document.getElementById("ky_han").value = '<bean:write name="ThongTinDauThauForm" property="ky_han"/>';
//      document.getElementById("ma_tpcp").value = '<bean:write name="ThongTinDauThauForm" property="ma_tpcp"/>';
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
        <fmt:message key="ttindthau.view.title"/>
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
                <html:hidden property="ma_tpcp" styleId="ma_tpcp1"/>
                <html:text property="ma_tpcp" styleId="ma_tpcp"
                             styleClass="form-control selectpicker" tabindex="3"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onchange="change_ma_tp(this)" readonly="true">
                  <%--<logic:notEmpty name="lstAllTPCP">
                    <html:optionsCollection name="lstAllTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>--%>
                </html:text>
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
                <html:hidden property="loai_tien"/>
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
              <%--<html:hidden property="ky_han"/>--%>
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
                  <html:text styleClass="form-control"
                             styleId="ngay_tt_tien_mua" readonly="true"
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
              <html:hidden property="ky_tra_lai"/>
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
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="14"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="20"
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
              <html:hidden property="hinh_thuc_dt"/>
                <html:select property="hinh_thuc_dt" styleId="hinh_thuc_dt"
                             disabled="true"
                             styleClass="form-control selectpicker"
                             tabindex="15"
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
              </label>
              <div class="col-sm-7">
              <html:hidden property="pthuc_xacdinh_kqdt"/>
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
                           onblur="textlostfocus(this);" tabindex="17" maxlength="20"
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
        <logic:notEmpty name="ThongTinDauThauForm" property="ls_danh_nghia">
        <div class="col-md-6" id="div_ls">
          <html:hidden property="ls_danh_nghia"/>
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
         </logic:notEmpty>
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
                           onfocus="textfocus(this);" maxlength="20" 
                           onblur="textlostfocus(this);" tabindex="4" readonly="true"
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
        <!--row 10-->
        <div class="row">
         <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="ttindthau.ghi_chu"/>
              </label>
              <div class="col-sm-7">
                <html:textarea styleClass="form-control" readonly="true"
                               onfocus="textfocus(this);" style="width:736px"
                               onblur="textlostfocus(this);" tabindex="19"
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
                <html:textarea styleClass="form-control" readonly="true"
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
      <logic:equal value="02" name="ThongTinDauThauForm"
                      property="trang_thai">
        <!--<a class="btn btn-default"
           href='<html:rewrite page="/ViewCongBoTTinDThauAction.do"/>?longid=<bean:write name="ThongTinDauThauForm" property="guid"/>'>
          <span class="sortKey">C</span>&ocirc;ng bố</a>-->
    <button type="button" class="btn btn-default" onclick="check('congbo')"
            accesskey="b" tabindex="7" id="bfind">
      Công <span class="sortKey">b</span>ố
    </button>
    
      </logic:equal>
  
     <logic:equal value="05" name="ThongTinDauThauForm" property="trang_thai">
     <button type="button" class="btn btn-default" onclick="check('xem')"
            accesskey="x" tabindex="7" id="bfind">
      <span class="sortKey">X</span>em
    </button>
     </logic:equal>
    <logic:equal value="00" name="ThongTinDauThauForm" property="trang_thai">
      <button type="button" class="btn btn-default" onclick="check('trinh')"
            accesskey="r" tabindex="7" id="bfind">
      Đệ t<span class="sortKey">r</span>ình
    </button>
    </logic:equal>
     <logic:equal value="03" name="ThongTinDauThauForm" property="trang_thai">
      <!--<a class="btn btn-default" onclick="return confirm('Bạn có muốn đệ trình bản ghi này ?')"
         href='<html:rewrite page="/TrinhTTinDThauAction.do"/>?longid=<bean:write name="ThongTinDauThauForm" property="guid"/>'>
        <span class="sortKey">Đ</span>ệ trình</a>-->
    <button type="button" class="btn btn-default" onclick="check('trinh')"
            accesskey="r" tabindex="7" id="bfind">
      Đệ t<span class="sortKey">r</span>ình
    </button>
    </logic:equal>
    
    <logic:equal value="01" name="ThongTinDauThauForm" property="trang_thai">
    <button type="button" class="btn btn-default" onclick="check('println')"
            accesskey="i" tabindex="7" id="bfind">
      <span class="sortKey">I</span>n
    </button>
    </logic:equal>
   <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="7" id="bfind">
      Th<span class="sortKey">o</span>át
    </button>
  </div>
 
  <html:hidden property="trang_thai" styleId="trang_thai"/>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden styleId="khoi_luong_tp_chu" property="khoi_luong_tp_chu" value="" />
  <html:hidden styleId="menh_gia_chu" property="menh_gia_chu" value="" />
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>