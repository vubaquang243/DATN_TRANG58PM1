  

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<fmt:setBundle basename="com.seatech.tp.resource.TPTTTIENMUAResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<script type="text/javascript">
  function getDMTPCP(){
    var f = document.forms[0];
      f.target = '';  
      f.action = "GetMaTPCPTTTMAction.do";
      f.submit();
  }
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'update') {          
          if (validateForm()) {
              f.action = "UpdateExcTTinTienMuaAction.do";
              if(!checkTTinTToan()){
                  if(confirm('Tổng số tiền nhập phải bằng tổng số tiền phải thanh toán')){
                    var ma_dvi = document.getElementsByName("id_dvi_sohuu");
                    if(ma_dvi != null && ma_dvi.length > 0){
                        for(var i = 0; i < ma_dvi.length;i++){
                          if(ma_dvi[i].disabled == false){
                              ma_dvi[i].checked = false;
                          }
                        }
                    }
                    f.submit();
                  }
              }else{
                f.submit();
              }              
          }
      }
      if (type == 'save') {     
          f.action = "AddExcTTinTienMuaAction.do";
          f.submit();
      }
      if (type == 'tracuu') {       
          f.action = "SearchTTinTienMuaAction.do";
          if($("#ngay_ph").val() ==""){
             alert("Bạn phải chọn Ngày phát hành.");
             $("#ngay_ph").focus();
             return false;
          }
          if($("#ma_tpcp").val() ==""){
             alert("Bạn phải chọn Mã TPCP.");
             $("#ma_tpcp").focus();
             return false;
          }
          f.submit();
      }    
      if (type == 'close') {
          f.action = "mainAction.do";
          f.submit();
      }      
  }
  function validateForm() {
      var so_gd =  document.getElementsByName("so_gd");
      var so_tien =  document.getElementsByName("so_tien");
      var ngay_tt =  document.getElementsByName("ngay_tt");
      var ngay_ph = document.getElementById("ngay_ph");
      if (so_gd != null && so_gd.length > 0) {
          for(var i = 0; i < so_gd.length;i++){
            if(so_tien[i].value != ""){
                if(so_tien[i].value == "0"){
                    alert("Bạn phải nhập Số tiền thanh toán");
                   so_tien[i].focus();
                   return false;   
                }                           
            }
            if (ngay_tt != null && ngay_tt[i].value != "") {
                  if(!validatetime(ngay_tt[i].value)){
                    alert('Bạn phải nhập theo định dạng DD/MM/YYYY hoặc DD/MM/YYYY HH:MM');
                    ngay_tt[i].focus();
                    return false;
                  }
                  var startDate = parseDate(ngay_tt[i].value).getTime();
                  var endDate = parseDate(ngay_ph.value).getTime();
                  if (startDate < endDate) {
                      alert('Ngày giờ chuyển tiền phải lớn hơn hoặc bằng Ngày phát hành!');
                      ngay_tt[i].focus();
                      return false;
                  }
              }
          }
      }
      return true;
  }
  function parseDate(str) {
      var mdy = str.split("/");
      var m = toNumber(mdy[1]) -1;
      return new Date(mdy[2], m, mdy[0]);
  }
  function checkTTinTToan(){
      var tien_tt_mua = document.getElementsByName("tien_tt_mua");
      var ma_dvi = document.getElementsByName("id_dvi_sohuu");
      var tongTienPhaiTT = 0;
      var tongSoTienTT = 0;
      var so_tien = document.getElementsByName("so_tien");
      if(ma_dvi != null && ma_dvi.length > 0){
        for(var i = 0; i < ma_dvi.length;i++){
            if(ma_dvi[i].checked == true){
               tongTienPhaiTT = tongTienPhaiTT + toNumber(tien_tt_mua[i].value);
            }
        }
      }
      if(so_tien != null && so_tien.length > 0){
        for(var j = 0; j < so_tien.length;j++){
            tongSoTienTT = tongSoTienTT + toNumber(so_tien[j].value);
        }
      }
      if(tongTienPhaiTT > tongSoTienTT){
        return false;
        //alert('Tổng số tiền nhập phải bằng tổng số tiền phải thanh toán');
      }
      return true;
  }
  $(function () {
      $("#ngay_ph").datepicker( {
          dateFormat : "dd/mm/yy"
      });      
  });

  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.current_page.value = page;
      f.submit();
  }

  function viewChiTiet(ma_dvi_so_huu, ma_tpcp) {
      var f = document.forms[0];
      f.ma_nguoi_so_huu.value = ma_dvi_so_huu;
      f.submit();
  }
</script>
<html:form action="SearchTTinTienMuaAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="tttmua.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label for="hoten" class="col-sm-2 control-label">
                <fmt:message key="tttmua.ngay_ph"/> <span style="color:red"> (*)</span>
              </label>
              <div class="col-sm-3">
                <html:text styleClass="form-control" styleId="ngay_ph"
                           onfocus="textfocus(this);" onkeyup="doFormat(event)" 
                           onblur="textlostfocus(this);" tabindex="1" onchange="getDMTPCP();"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           property="ngay_ph"/>
              </div>
              <label for="hoten" class="col-sm-2 control-label">
                Mã TPCP<span style="color:red"> (*)</span>
              </label>
              <div class="col-sm-3">
                <html:select property="ma_tpcp" styleId="ma_tpcp"
                             styleClass="form-control selectpicker" tabindex="2"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                             <option value="">Vui lòng chọn</option>
                  <logic:notEmpty name="lstDMTPCP">
                    <html:optionsCollection name="lstDMTPCP" value="ma_tp"
                                            label="ma_tp"/>
                  </logic:notEmpty>
                </html:select> 
              </div>
              <div class="col-sm-2">
                <button type="button" onclick="check('tracuu')"
                        class="btn btn-default" style="width:100" accesskey="t" tabindex="3">
                  <span class="sortKey">T</span>ra cứu
                </button>
                 
                <button type="button" class="btn btn-default"
                        onclick="check('close')" accesskey="o" tabindex="4"
                        id="bfind">
                  Th<span class="sortKey">o</span>át
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="row col-md-12" align="right" style="margin-bottom:10px;">
          <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g" tabindex="7" id="bfind">
          <span class="sortKey">G</span>hi TTTT
        </button>
        </div>
        <div class="row col-md-12" style="margin-bottom:10px;">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h2 class="panel-title">
                <fmt:message key="tttmua.dvisohuu.title"/>
              </h2>
            </div>
            <div class="panel-body">
              <table class="table table-bordered">
                <thead>
                  <th>STT</th>
                  <th>
                    <fmt:message key="tttmua.dvisohuu.name"/>
                  </th>
                  <th>
                    Tổng số tiền phải thanh toán 
                    <logic:equal value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                      (usd)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual>
                  </th>
                  <th>
                    Tổng số tiền đã thanh toán 
                    <logic:equal value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                      (usd)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual>
                  </th>                  
                  <th>
                    Tổng số tiền đã lập BKXN  
                    <logic:equal value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                      (usd)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual>
                  </th>                 
                  <th>
                    
                     <input type="checkbox" name="lapTTinTToan" id="checkAllParent" onclick="checkAllBox(this,'dvi_sohuu');"/>
                  </th>
                </thead>                 
                <tbody>
                  <logic:notEmpty name="lstDviSoHuu">
                    <bean:define id="maDvi" name="QLyTToanTienMuaForm" property="ma_nguoi_so_huu"/>
                    <logic:iterate id="objDViSoHuu" name="lstDviSoHuu"
                                   indexId="stt">
                                   <% 
                                      boolean abc = false; 
                                      boolean checkTT = false;
                                      boolean checkBke = false;
                                   %>
                      <logic:present name="objDViSoHuu" property="ma_nguoi_so_huu">
                      <bean:define id="maSoHuu" name="objDViSoHuu" property="ma_nguoi_so_huu"/>
                      <%
                        abc = maDvi.equals(maSoHuu);
                      %>
                      </logic:present>
                      <tr ondblclick="viewChiTiet('<bean:write name="objDViSoHuu" property="ma_nguoi_so_huu"/>')" class='<%=(abc == true ? "trSelect" : "")%>'>
                      
                        <td class="center">
                          <%= stt + 1%>
                        </td>                             
                        <td class="left">
                          <bean:write name="objDViSoHuu" property="ten_nguoi_so_huu"/>
                          <input type="hidden" name="dot_phat_hanh" value="<bean:write name="objDViSoHuu" property="dot_ph"/>"/>
                          <input type="hidden" name="dot_ph" value="<bean:write name="objDViSoHuu" property="dot_ph"/>"/>
                        </td>
                        <td class="right">
                          <%--<bean:write name="objDViSoHuu" property="tong_tien_phai_tt"/>--%>
                          <bean:define id="tong_tien_phai_tt" name="objDViSoHuu" property="tong_tien_phai_tt"/>
                          <input type="hidden" name="tong_tien_phai_tt" value="<bean:write name="objDViSoHuu" property="tong_tien_phai_tt"/>"/>
                          <%=StringUtil.convertNumberToString(tong_tien_phai_tt.toString(),"VND")%>
                        </td>
                        <td class="right">
                          <%--<bean:write name="objDViSoHuu" property="tong_tien_da_tt"/>--%>
                          <bean:define id="tong_tien_da_tt" name="objDViSoHuu" property="tong_tien_da_tt"/>
                          <%=StringUtil.convertNumberToString(tong_tien_da_tt.toString(),"VND")%>   
                          <% checkTT = StringUtil.convertCurrencyToNumber(tong_tien_da_tt.toString(),"VND").compareTo(new java.math.BigDecimal("0")) >0? true:false;%>
                        </td>
                        <td class="right">
                          <%--<bean:write name="objDViSoHuu" property="tong_tien_da_lap_bke"/>--%>
                          <bean:define id="tong_tien_da_lap_bke" name="objDViSoHuu" property="tong_tien_da_lap_bke"/>
                          <%=StringUtil.convertNumberToString(tong_tien_da_lap_bke.toString(),"VND")%>
                          <% checkBke = StringUtil.convertCurrencyToNumber(tong_tien_da_lap_bke.toString(),"VND").compareTo(new java.math.BigDecimal("0")) == 1? true:false;%>
                        </td>                                              
                        <td class="icon">
                            <input type="hidden" name="dvi_sohuu_hidden" value='<bean:write name="objDViSoHuu" property="ma_nguoi_so_huu"/>'/>
                            <%
                              if(!checkBke){
                            %>
                              <%
                              if(!checkTT){
                              %>
                            <input type="checkbox" onclick="checkOnBox('checkAllParent','dvi_sohuu');" 
                            name="dvi_sohuu"  value='<bean:write name="objDViSoHuu" property="ma_nguoi_so_huu"/>'/>
                            <%}else{%>
                            <input type="checkbox" checked="checked" onclick="checkOnBox('checkAllParent','dvi_sohuu');" 
                            name="dvi_sohuu"  value='<bean:write name="objDViSoHuu" property="ma_nguoi_so_huu"/>'/>  
                            <input type="hidden" name="dvi_sohuu_bk" value='<bean:write name="objDViSoHuu" property="ma_nguoi_so_huu"/>'/>
                            <%}%>
                            <%}else{%>
                              <input type="checkbox" checked="checked" disabled="disabled"/>
                            <%}%>
                            
                        </td>
                      </tr>                      
                    </logic:iterate>                      
                  </logic:notEmpty>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <!--thông tin thanh toán -->
        <div class="row row-height">
          <logic:present name="lstTTinTThau">
            <div class="col-md-5 col-height">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h2 class="panel-title">
                    <fmt:message key="tttmua.ttindauthau.title"/>
                  </h2>
                </div>
                <div class="panel-body">
                  <table class="table table-bordered">
                    <thead>
                      <th>STT</th>
                      <th>
                        <fmt:message key="tttmua.ttindauthau.ma_tp"/>
                      </th>
                      <th>
                        <%--<fmt:message key="tttmua.ttindauthau.tienphaitt"/>--%>
                        Tiền phải <br/>thanh toán 
                        <logic:equal value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                          (usd)
                        </logic:equal>
                        <logic:notEqual value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                          (đồng)
                        </logic:notEqual>
                      </th>
                      <th width="40px">
                         <input type="checkbox" name="lapTTinTToan" id="checkAll" onclick="checkAllBox2(this,'id_dvi_sohuu');"/>
                      </th>
                      <th width="40px">
                      </th>
                    </thead>                     
                    <tbody>
                      <logic:notEmpty name="lstTTinTThau">
                        <logic:iterate id="objTTinThau" name="lstTTinTThau"
                                       indexId="stt">
                          <tr>
                            <td class="center">
                              <%=stt + 1%>
                            </td>
                            <td class="left">
                              <bean:write name="objTTinThau" property="ma_tpcp"/>
                              <input type="hidden" name="ma_tpcp" value='<bean:write name="objTTinThau" property="ma_tpcp"/>'/>
                            </td>
                            <td class="right">                              
                                <bean:define id="tien_tt_mua" name="objTTinThau" property="tien_tt_mua"/>
                                <input type="hidden" name="tien_tt_mua" value='<bean:write name="objTTinThau" property="tien_tt_mua"/>'/>
                                <%=StringUtil.convertNumberToString(tien_tt_mua.toString(),"VND")%>
                            </td>
                            <td class="center">            
                            <input type="hidden" name="id_dvi_sohuu_bk" value='<bean:write name="objTTinThau" property="guid"/>'/>
                            <input type="hidden" name="dthau_type_<bean:write name="objTTinThau" property="ma_tpcp"/>_<bean:write name="objTTinThau" property="guid"/>" value='<bean:write name="objTTinThau" property="dthau_type"/>'/>
                            <logic:equal value="00" name="objTTinThau" property="trang_thai_tt">
                            <input type="checkbox" onclick="checkOnBox2('checkAll','id_dvi_sohuu');" 
                            name="id_dvi_sohuu"  value='<bean:write name="objTTinThau" property="guid"/>'/>
                            </logic:equal>
                            <logic:equal value="02" name="objTTinThau" property="trang_thai_tt">
                            <input type="checkbox" onclick="checkOnBox2('checkAll','id_dvi_sohuu');" 
                            name="id_dvi_sohuu" checked="checked" value='<bean:write name="objTTinThau" property="guid"/>'/>
                            </logic:equal>
                            <logic:equal value="03" name="objTTinThau" property="trang_thai_tt">
                            <input type="checkbox" checked="checked" onclick="checkOnBox2('checkAll','id_dvi_sohuu');" disabled="true"
                            name="id_dvi_sohuu" value='<bean:write name="objTTinThau" property="guid"/>'/>
                            </logic:equal>
                            </td>
                            <td class="center add-remove">
                              <span class="glyphicon glyphicon-forward" id="id_number" onclick="copyNumber('<bean:write name="objTTinThau" property="tien_tt_mua"/>');"></span>
                            </td>
                          </tr>
                        </logic:iterate>
                        <tr>
                          <td colspan="2" class="center">
                             <b> Tổng cộng </b>
                          </td>
                          <td class="right">
                            <b><bean:write name="totalTien"/></b>
                          </td>
                          <td></td>
                          <td class="center">
                              <span class="glyphicon glyphicon-forward" id="id_number" onclick="copyNumber('<bean:write name="totalTien"/>');"></span>
                          </td>
                        </tr>
                      </logic:notEmpty>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </logic:present>
          <logic:present name="lstTTinTToanBKe">
          <div class="col-md-7 col-height">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2 class="panel-title">
                  <fmt:message key="tttmua.add.title"/>
                </h2>
              </div>
              <div class="panel-body">
                <table class="table table-bordered" id="tblTToanTMua">
                  <thead>
                    <th>STT</th>
                    <th>
                      <fmt:message key="tttmua.so_gd"/>
                    </th>
                    <th>
                      Số tiền đã thanh toán 
                      <logic:equal value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                      (usd)
                    </logic:equal>
                    <logic:notEqual value="USD" name="QLyTToanTienMuaForm" property="loai_tien">
                      (đồng)
                    </logic:notEqual><span style="color:red"> (*)</span>
                    </th>
                    <th>
                      <fmt:message key="tttmua.ngay_tt"/>
                    </th>
                    <th width="90px">
                      <fmt:message key="tttmua.tthai_tt"/>
                    </th>
                  </thead>                   
                  <tbody>
                  <% int sizeBKe = 0;%>
                  <logic:notEmpty name="lstTTinTToanBKe">
                      <logic:iterate id="objTMuaCTiet"
                                     name="lstTTinTToanBKe" indexId="stt">
                        <tr>
                          <td class="center">
                           <%sizeBKe = sizeBKe + 1;%>
                            <%=stt + 1%>
                          </td>                                                   
                          <td class="left">    
                          <input type="hidden" name="so_gd" value='<bean:write name="objTMuaCTiet" property="so_gd"/>'/>
                            <bean:write name="objTMuaCTiet" property="so_gd"/>
                          </td>
                          <td class="right">
                          <input type="hidden" name="so_tien" value='<bean:write name="objTMuaCTiet" property="so_tien"/>'/>
                          <bean:write name="objTMuaCTiet" property="so_tien"/>
                          </td>
                          <td class="center">
                          <input type="hidden" name="ngay_tt" value='<bean:write name="objTMuaCTiet" property="ngay_tt"/>'/>
                          <bean:write name="objTMuaCTiet" property="ngay_tt"/>
                          </td>
                          <td class="center">        
                          <input type="hidden" name="nop_cham" value='<bean:write name="objTMuaCTiet" property="nop_cham"/>'/>
                          <input type="hidden" name="trang_thai_tt" value='<bean:write name="objTMuaCTiet" property="trang_thai_tt"/>'/>
                          
                          <logic:equal value="0" name="objTMuaCTiet" property="nop_cham">
                            Có
                          </logic:equal>
                           <logic:equal value="1" name="objTMuaCTiet" property="nop_cham">
                            Không
                           </logic:equal>
                          </td>              
                        </tr>
                      </logic:iterate>
                    </logic:notEmpty>
                    
                    <logic:notEmpty name="lstTTinTToan">
                      
                      <logic:iterate id="objTMuaCTiet2"
                                     name="lstTTinTToan" indexId="stt2">
                        <tr>
                          <td class="center">                            
                            <%=stt2 + sizeBKe + 1%>
                          </td>                    
                          <td class="left">                            
                            <html:text name="objTMuaCTiet2" property="so_gd"
                                    onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                          </td>
                          <td class="left">
                            <html:text name="objTMuaCTiet2" property="so_tien" styleClass="number"
                                    onkeyup="formatNum(this);"
                                    onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                          </td>
                          <td class="center">
                            <html:text name="objTMuaCTiet2" styleId="ngay_tt" property="ngay_tt"
                                    
                                    maxlength="16"
                                    onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                          </td>
                          <td class="center">           
                            <input type="hidden" name="trang_thai_tt" value="00"/>
                            <html:select property="nop_cham" name="objTMuaCTiet2" 
                                     styleId="nop_cham"
                                     onkeydown="if(event.keyCode==13) event.keyCode=9;">
                                     <html:option value="1">Không</html:option>
                                    <html:option value="0">Có</html:option>                                    
                            </html:select>
                          </td>                      
                        </tr>
                      </logic:iterate>
                    </logic:notEmpty>
                  </tbody>                  
                </table>
                <div>
                  <div class="col-md-3">
                    <input class="btn_type1" id="themDong" type="button"
                           value="Thêm dòng" onclick="addRow('tblTToanTMua')"></input>
                  </div>
                  <div class="col-md-3">
                    <input class="btn_type1" id="xoaDong" type="button"
                           value="Xóa dòng" onclick="deleteRow('tblTToanTMua')"></input>
                  </div>
                </div>
              </div>
            </div>
          </div>
          </logic:present>
        </div>
      </div>
    </div>
  </div>    
  <html:hidden name="QLyTToanTienMuaForm" property="pageNumber" value="1"/>  
  <html:hidden name="QLyTToanTienMuaForm" property="current_page"/>
  <html:hidden name="QLyTToanTienMuaForm" property="loai_tien"/>
  <html:hidden name="QLyTToanTienMuaForm" property="ma_nguoi_so_huu"/>
  <html:hidden name="QLyTToanTienMuaForm" property="phuong_thuc_ph"/>
  
  <div class="center">  
  <logic:present name="lstTTinTThau">
    <logic:equal name="allowGhiTungBanGhi" value="true">
    <button type="button" class="btn btn-default" onclick="check('update')"
            accesskey="c" tabindex="7" id="bfind">
      <span class="sortKey">C</span>ập nhật từng bản ghi
    </button>  
    </logic:equal>
    </logic:present>
  </div>
</html:form>
<script type="text/javascript">
  initTable('tblTToanTMua');
  function copyNumber(obj){
      var so_tien_tt = document.getElementsByName("so_tien");
      if(so_tien_tt!=null && so_tien_tt.length>0){
        for(var i=0;i<so_tien_tt.length;i++){
           if(so_tien_tt[i].value == '' || so_tien_tt[i].value =='0'){
              so_tien_tt[i].value = replaceCommas(obj);
              break;
           }
        }
      }
  }
  function checkAllBox2(obj,objName){
     var arrObj = document.getElementsByName(objName);
     if(arrObj!=null && arrObj.length > 0){
        for(var i =0;i< arrObj.length;i++){
          if(arrObj[i].disabled == false){
            arrObj[i].checked = obj.checked;
          }          
        }
      }
  }
  function checkOnBox2(objAll, objName){
      var obj = document.getElementById(objAll);
      var arrObj = document.getElementsByName(objName);
      var checkAll = true;
      if(arrObj!=null && arrObj.length > 0){
        for(var i =0;i< arrObj.length;i++){
          if(arrObj[i].checked ==false){
              checkAll = false;
              break;
          }
        }
      }
      obj.checked = checkAll;
  }
  checkOnBox2('checkAll','id_dvi_sohuu');
  checkOnBox('checkAllParent','dvi_sohuu');
  function checkAllBox(obj,objName){
     var arrObj = document.getElementsByName(objName);
     if(arrObj!=null && arrObj.length > 0){
        for(var i =0;i< arrObj.length;i++){
          if(arrObj[i].disabled == false){
            arrObj[i].checked = obj.checked;
          }         
        }
      }
  }
  function checkOnBox(objAll, objName){
      var obj = document.getElementById(objAll);
      var arrObj = document.getElementsByName(objName);
      var checkAll = true;
      if(arrObj!=null && arrObj.length > 0){
        for(var i =0;i< arrObj.length;i++){
          if(arrObj[i].checked ==false){
              checkAll = false;
              break;
          }
        }
      }
      obj.checked = checkAll;
  }
  function setTabIndex() {
  }

  function addRow(tableId) {
      addRowTable(tableId);
      assignSTT(tableId);
  }

      function deleteRow(tableId) {
          deleteRowTable(tableId);
          assignSTT(tableId);
      }
</script>
<%@ include file="/includes/tpcp_bottom.inc"%>