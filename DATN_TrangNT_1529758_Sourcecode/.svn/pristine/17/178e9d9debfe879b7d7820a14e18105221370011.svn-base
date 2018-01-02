  
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
        
        List<QuanLyTPCPVO> lstAllTPCP = (List<QuanLyTPCPVO>)request.getAttribute("lstAllTPCP");
%>
<script type="text/javascript">

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var guid = $("#guid").val();
      if (type == 'save') {
        
         if(confirm('Bạn có chắc chắn muốn đệ trình hợp đồng này?')==true){
            if (guid != null && guid != "") {
              $("#trang_thai").val("01");
              f.action = 'TrinhHDBanTinPhieuAction.do?longid=<bean:write name="HDBanTinPhieuForm" property="guid"/>';
              f.submit();
          }
         }else if(type == 'close'){
           f.action='ListHDBanTinPhieuAction.do';  //   khong thuc hien dc 
       
         }
       
      }else if(type=='close'){
         f.action='ListHDBanTinPhieuAction.do'; 
         f.submit();
      }
  }
  $(function () {
      
      document.getElementById("ky_han").value = '<bean:write name="HDBanTinPhieuForm" property="ky_han"/>';
      $('.selectpicker').selectpicker('refresh');
  });
</script>
<html:form action="ViewHDBanTinPhieuAction" method="post">
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
        <fmt:message key="hdbantinphieu.view.title"/>
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
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="1"
                           styleId="so_hd" property="so_hd" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.ma_tp"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="ma_tp" property="ma_tp" maxlength="10" readonly="true"/> 
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
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_hd"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="3" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" property="ngay_hd" readonly="true"/>
                   
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
                <fmt:message key="hdbantinphieu.kl_tp"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="4"
                           styleId="kl_tp" property="kl_tp" readonly="true"/>
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
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);" 
                           onblur="textlostfocus(this);" tabindex="5"
                           styleId="lai_suat" property="lai_suat" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.ky_han"/>
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
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" styleId="gia_ban"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="7"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"
                           property="gia_ban"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.ngay_ph"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_ph"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"
                             tabindex="8" onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" property="ngay_ph"/>
                   
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
                <fmt:message key="hdbantinphieu.ngay_tt_tien_mua"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_tt_tien_mua"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"
                             tabindex="9" onfocus="textfocus(this);" 
                             onblur="textlostfocus(this);" property="ngay_tt_tien_mua"/>
                   
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
                <fmt:message key="hdbantinphieu.ngay_dao_han"/>
              </label>
              <div class="col-sm-7">
                <div class="input-group date">
                  <html:text styleClass="form-control" styleId="ngay_dao_han"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="10" onfocus="textfocus(this);" readonly="true"
                             onblur="textlostfocus(this);" property="ngay_dao_han"/>
                   
                  <span class="input-group-addon"> 
                    <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                </div>
                
              </div>
            </div>
          </div>
        </div>
        <!--Row 5-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.tt_dky_lky"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" styleId="tt_dky_lky"
                           onfocus="textfocus(this);"
                           style="height: 44px;"
                           onblur="textlostfocus(this);" tabindex="4" readonly="true"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="tt_dky_lky"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="hdbantinphieu.ly_do_tu_choi"/>
              </label>
              <div class="col-sm-7">
                <html:textarea styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="ly_do_tu_choi" property="ly_do_tu_choi" 
                           readonly="true"
                           />
              </div>
            </div>
        </div>
        </div>
        <!--Row 6-->
        <!--Row 7-->
        <!--Row 8-->
        <!--Row 9-->
      </div>
    </div>
  </div>
  <div class="center">
    <logic:equal value="00" name="HDBanTinPhieuForm" property="trang_thai">
      
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="r" tabindex="7" id="bfind">
      Đệ T<span class="sortKey">r</span>ình     
    </button>
    </logic:equal>
    <logic:equal value="03" name="HDBanTinPhieuForm" property="trang_thai">
      
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="r" tabindex="7" id="bfind">
      Đệ T<span class="sortKey">r</span>ình     
    </button>
    </logic:equal>
   <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="7" id="bfind">
      Th<span class="sortKey">o</span>át
    </button>
  </div>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden property="trang_thai" styleId="trang_thai"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>