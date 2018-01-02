
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
      var ly_do_tu_choi = $("#ly_do_tu_choi").val();
      var guid = $("#guid").val();
      if (type == 'pheduyet') {   
//          if(ly_do_tu_choi !="" || ly_do_tu_choi.trim()!="" ){
//            alert("Bạn phải xóa lý do từ chối hợp đồng!");
//            $("#ly_do_tu_choi").focus();
//            return false;
//          }
          
          if(confirm('Bạn có chắc chắn muốn duyệt HD Tín phiếu này không?')){
            document.getElementById("trang_thai").value = '02';
       //     $("#ly_do_tu_choi").val(" ");
            f.action = 'PheDuyetHDBanTinPhieuPheDuyetAction.do';
          }else{
           f.action ='ListHDBanTinPhieuPheDuyetAction.do';
          }
      }else if(type=='tuchoi'){    
          if(ly_do_tu_choi == "" || ly_do_tu_choi.trim()==''  ){
            alert('Bạn phải nhập lý do từ chối ');
            $("#ly_do_tu_choi").focus();
            return false;
          }else{
          document.getElementById("trang_thai").value = '03';
          f.action = 'PheDuyetHDBanTinPhieuPheDuyetAction.do';
          }
      }
      if(type=='close'){
       
        f.action='ListHDBanTinPhieuPheDuyetAction.do';
        
      }
      
      f.submit();
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
        <fmt:message key="hdbantinphieu.pd_hd_ban_tp"/>
      </strong>
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
                           styleId="ma_tp" property="ma_tp" maxlength="10"
                           readonly="true"/>
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
                             onblur="textlostfocus(this);" property="ngay_hd"
                             readonly="true"/>
                   
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
                           styleId="lai_suat" property="lai_suat"
                           readonly="true"/>
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
                <fmt:message key="hdbantinphieu.gia_ban"/>
              </label>
              <div class="col-sm-7">
                <html:text styleClass="form-control" styleId="gia_ban"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="7"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           readonly="true" property="gia_ban"/>
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
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             style="width:226px";
                             readonly="true" tabindex="8"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" property="ngay_ph"/>
                   
                  <span class="input-group-addon" style="width: 33px; height: 25px;"> 
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
                  <html:text styleClass="form-control"
                             styleId="ngay_tt_tien_mua"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             readonly="true" tabindex="9"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);"
                             property="ngay_tt_tien_mua"/>
                   
                  <span class="input-group-addon" > 
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
                             style="width:226px;";
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             tabindex="10" onfocus="textfocus(this);"
                             readonly="true" onblur="textlostfocus(this);"
                             property="ngay_dao_han"/>
                   
                  <span class="input-group-addon" style="width: 33px; height: 25px;"> 
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
                           style="margin: 0px;height: 44px;"
                           onblur="textlostfocus(this);" tabindex="4"
                           readonly="true"
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
              <logic:equal value="01" name="HDBanTinPhieuForm" property="trang_thai">
              <div class="col-sm-7">
                <html:textarea styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="ly_do_tu_choi" property="ly_do_tu_choi"                         
                           />
              </div>
              </logic:equal>
               <logic:notEqual value="01" name="HDBanTinPhieuForm" property="trang_thai">
              <div class="col-sm-7">
                <html:textarea styleClass="form-control"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="ly_do_tu_choi" property="ly_do_tu_choi"
                           readonly="true"
                           />
              </div>
              </logic:notEqual>
            </div>
        </div>
        
        <!--Row 6-->
        <!--Row 7-->
        <!--Row 8-->
        <!--Row 9-->
      </div>
    </div>
  </div>
  <div class="center" style="margin-bottom:15px;">
    <logic:equal value="01" name="HDBanTinPhieuForm" property="trang_thai">
      <button id="tracuu" type="button" onclick="check('pheduyet')"
             accesskey="d"  class="btn btn-default" >
        <span class="sortKey">D</span>uyệt
      </button>
      <button id="tracuu" type="button" onclick="check('tuchoi')"
              class="btn btn-default" accesskey="c">
        Từ <span class="sortKey">c</span>hối
      </button>
    </logic:equal>
     
     <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="8">
      Th<span class="sortKey">o</span>át
    </button>
  </div>
  <html:hidden property="guid" styleId="guid"/>
  <html:hidden property="trang_thai" styleId="trang_thai" />
</div></html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>