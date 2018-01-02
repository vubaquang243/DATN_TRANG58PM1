<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.user.UserVO"%>
<%@ page import="com.seatech.tp.chucnang.ChucNangVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.PhanQuyenResource"/>
<%@ include file="/includes/tpcp_header.inc"%>


<script type="text/javascript">
  // Popup window code  
  //jQuery.noConflict();
  jQuery(document).ready(function () {
    $("#tree1").checkboxTree();
    $("#tree2").checkboxTree();
  });
  
  function AddOrRemoveFun(tree_id, type) {
      arrFun = new Array();
      treeFun = document.getElementById(tree_id);
      arrCheck = treeFun != null ? treeFun.getElementsByTagName("INPUT") : new Array();
      counter = 0;
      var frm = document.forms[0];
      for (i = 0;i < arrCheck.length;++i) {
          if (arrCheck[i].checked) {
              arrFun[counter] = arrCheck[i].value;
              counter++;
          }
      }
      if (counter == 0) {
          alert('Bạn chưa chọn chức năng nào.');
          return;
      }
      frm.arrAddFun.value = arrFun;
      frm.actionPQ.value = type;
      frm.action = "phanQuyenExcAction.do";
      frm.submit();
  }

  function phanquyenSubmit(type) {
      var frm = document.forms[0];
      if (type == 'tracuu') {
          if (frm.nhom_id.value == '' || frm.nhom_id.value == 'null') {
              alert('Bạn phải chọn nhóm NSD.');
              document.getElementById('nhom_id_id').fucus();
              return;
          }
      }
      else if (type == 'thoat') {
          frm.action = "mainAction.do";
      }
      frm.submit();
  }
</script>
<html:form action="/phanQuyenAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="phanquyen.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-5">
            <div class="form-group">
              <label for="hoten" class="col-sm-5 control-label">
                <fmt:message key="phanquyen.label.nhom"/>
              </label>
              <div class="col-sm-7">
                <html:select property="nhom_id" styleId="nhom_id_id"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Lựa chọn</html:option>
                  <logic:notEmpty name="colNhomNSD">
                    <html:optionsCollection name="colNhomNSD" value="id"
                                            label="ten_nhom"/>
                  </logic:notEmpty>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="phanquyen.label.cnang"/>
              </label>
              <div class="col-sm-6">
                <html:select property="cnang_id" styleId="cnang_id_id"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Lựa chọn</html:option>
                  <logic:notEmpty name="colCNangCha">
                    <html:optionsCollection name="colCNangCha"
                                            value="ky_hieu_cnang"
                                            label="ten_cnang"/>
                  </logic:notEmpty>
                </html:select>
              </div>
              <div class="col-sm-2">
                <button type="button" onclick="phanquyenSubmit('tracuu')"
                        class="ButtonCommon" style="width:100" accesskey="t">
                  <span class="sortKey">T</span>ra cứu
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="row row-height">
          <div class="col-md-6 col-height">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2 class="panel-title">
                  <fmt:message key="phanquyen.title.ds_chucnang_khong_quyen"/>
                </h2>
              </div>
              <div class="panel-body">
              <ul id="tree1">
<%
if(request.getAttribute("colCnangKoQuyen") != null){
  Collection colCnangKoQuyen = (Collection)request.getAttribute("colCnangKoQuyen");
  ChucNangVO cnangvo = null;
  Iterator iterCNang =  colCnangKoQuyen.iterator();
  int nCap1= 0; 
  int nCap2 = 0;
  int nCap3 = 0;
  int nCap4 = 0;
  int nMaCN = 0;
  
  while (iterCNang.hasNext()) {
    cnangvo = (ChucNangVO)iterCNang.next();
    nMaCN = Integer.parseInt(cnangvo.getMa_cnang());
    //Xu ly menu cap 1
    if (nMaCN % 10000 == 0) {      
      //Check xem co phai chuc nang dau tien cua menu ko?
      if (nCap2 > 0) {
      %>
        </ul>
      <%
      }
      if (nCap3 > 0) {
      %>
        </ul>
      <%
      }
      if (nCap4 > 0) {
      %>
        </ul>
      <%
      }
      %>
        <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
      <%
      nCap1++;
      nCap2 = 0;
      nCap3 = 0;
      nCap4 = 0;
      //Xu ly menu cap 2
    }else if (nMaCN % 100 == 0) {      
      //Kiem tra co phai menu cap 2  dau tien hay ko?
      if(nCap3 > 0){
      %>
          </ul>
      <%
      }
      if (nCap4 > 0) {
      %>
        </ul>
      <%
      }
      if(nCap2 == 0){
%>        
        <ul>
<%
      }
%> 
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
<%    
      nCap2++;
      nCap3 = 0;
      nCap4 = 0;
    }else if (nMaCN % 10 == 0) {
      if(nCap4 > 0){
      %>
          </ul>
      <%
      } 
      if(nCap3 == 0){
%>
        <ul>
<%
      }
%>     
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
<%
      nCap3++;
      nCap4 = 0;
    }else{
      if(nCap4 == 0)
      {
%>
        <ul>
<%
      }
%>        
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
      
<%
      nCap4++;
    }
  }
%>

        </ul>
<%}%>
              </div>
            </div>
          </div>
          <div class="col-md-1 add-remove col-height center middle">
                  <span class="glyphicon glyphicon-forward" onclick="AddOrRemoveFun('tree1','add')"></span>
                  <span class="glyphicon glyphicon-backward" onclick="AddOrRemoveFun('tree2','remove')"></span>
          </div>
          <div class="col-md-6 col-height">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2 class="panel-title">
                  <fmt:message key="phanquyen.title.ds_chucnang_co_quyen"/>
                </h2>
              </div>
              <div class="panel-body">
              <ul id="tree2">

<%
if(request.getAttribute("colCnangCoQuyen") != null){
  Collection colCnangCoQuyen = (Collection)request.getAttribute("colCnangCoQuyen");
  ChucNangVO cnangvo = null;
  Iterator iterCNang =  colCnangCoQuyen.iterator();
  int nCap1= 0; 
  int nCap2 = 0;
  int nCap3 = 0;
  int nCap4 = 0;
  int nMaCN = 0;
  
  while (iterCNang.hasNext()) {
    cnangvo = (ChucNangVO)iterCNang.next();
    nMaCN = Integer.parseInt(cnangvo.getMa_cnang());
    //Xu ly menu cap 1
    if (nMaCN % 10000 == 0) {      
      //Check xem co phai chuc nang dau tien cua menu ko?
      if (nCap2 > 0) {
      %>
        </ul>
      <%
      }
      if (nCap3 > 0) {
      %>
        </ul>
      <%
      }
      if (nCap4 > 0) {
      %>
        </ul>
      <%
      }
      %>
        <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
      <%
      nCap1++;
      nCap2 = 0;
      nCap3 = 0;
      nCap4 = 0;
      //Xu ly menu cap 2
    }else if (nMaCN % 100 == 0) {      
      //Kiem tra co phai menu cap 2  dau tien hay ko?
      if(nCap3 > 0){
      %>
          </ul>
      <%
      } 
      if (nCap4 > 0) {
      %>
        </ul>
      <%
      }
      if(nCap2 == 0){
%>        
        <ul>
<%
      }
%> 
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
<%    
      nCap2++;
      nCap3 = 0;
      nCap4 = 0;
    }else if (nMaCN % 10 == 0) {
      if(nCap4 > 0){
      %>
          </ul>
      <%
      } 
      if(nCap3 == 0){
%>
        <ul>
<%
      }
%>     
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
<%
      nCap3++;
      nCap4 = 0;
    }else{
      if(nCap4 == 0)
      {
%>
        <ul>
<%
      }
%>        
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
      
<%
      nCap4++;
    }
  }
%>

        </ul>
<%}%>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="center">
    <button type="button" onclick="phanquyenSubmit('thoat')"
            class="btn btn-default" accesskey="o">
      Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <input type="hidden" name="arrAddFun" id="addFun" />
  <input type="hidden" name="actionPQ" id="actionPQ" />
</html:form>
<script type="text/javascript" src="pages/user/phanquyen/library/jquery-1.4.4.js"></script>
    <script type="text/javascript" src="pages/user/phanquyen/library/jquery-ui-1.8.12.custom/js/jquery-ui-1.8.12.custom.min.js"></script>
    <!--<link rel="stylesheet" type="text/css"
          href="pages/user/phanquyen/library/jquery-ui-1.8.12.custom/css/smoothness/jquery-ui-1.8.12.custom.css"/>-->
<!--<link rel="stylesheet" type="text/css" href="pages/user/phanquyen/jquery.checkboxtree.css"/>--> 
<script type="text/javascript" src="pages/user/phanquyen/jquery.checkboxtree.js"></script>
<script>

$( "input" ).change(function() {
if ( $(this).is( ":checked" ) ){
    $(this).prev().addClass("xanh");
} else{
  $(this).prev().removeClass("xanh");
}
}).change();


  //thay đổi độ cao khung
function panelBody(){ 
    var a = $(".add-remove").next().find(".panel-body");
    var a1 = a.outerHeight();
   
    var b = $(".add-remove").prev().find(".panel-body");
    var b1 = b.outerHeight();
   
    if(a1 > b1){

      b.css("min-height", (a.outerHeight() + "px"));
    } else{

      a.css("min-height", (b.outerHeight() + "px"));
    }
  }
  setTimeout( panelBody, 900);
  
  $(".ui-icon").click(setTimeout( panelBody, 900));
</script>
<%@ include file="/includes/tpcp_bottom.inc"%>
