<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.tp.user.UserVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.PhanNhomNSDResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<script type="text/javascript">
    var arrFunAllow = new Array();
    var arrFunDeny = new Array();       
   <%        
        Collection collNSDTrongNhomBanDau = (Collection) request.getAttribute("colNSDThuocNhom");
        Collection collNSDNgoaiNhomBanDau = (Collection) request.getAttribute("colNSDNgoaiNhom");         
        UserVO userVO = null;        
        Iterator ir = null;
        try{
            ir = collNSDTrongNhomBanDau.iterator(); 
            int countTrongNhom = 0;
            while (ir.hasNext()){
                userVO = (UserVO)ir.next();
                out.println("arrFunAllow['"+countTrongNhom+"'] = '"+ userVO.getId()+"'");
                ++countTrongNhom;
            }
        }catch(Exception ex){
        }
        try{
            ir = collNSDNgoaiNhomBanDau.iterator();
            int countNgoaiNhom = 0;
            while (ir.hasNext()){
                userVO = (UserVO)ir.next();
                out.println("arrFunDeny['"+countNgoaiNhom+"'] = '"+ userVO.getId()+"'");
                ++countNgoaiNhom;
            }            
        }catch(Exception ex){
        }
    %>
</script> 
<script type="text/javascript">
//  jQuery.noConflict();
  jQuery(document).ready(function () {
    arrAddFun = new Array();
    arrRemoveFun = new Array();
    tbdListAddFunAlow = document.getElementById("tbdListAddFunAllow");
    tbdListAddFunDeny = document.getElementById("tbdListAddFun");
    arrButtonAlow = tbdListAddFunAlow!=null?tbdListAddFunAlow.getElementsByTagName("INPUT"):new Array();
    arrButtonDeny = tbdListAddFunDeny!=null?tbdListAddFunDeny.getElementsByTagName("INPUT"):new Array();   
  });
    function getArrAddorRemove(){
        arrRemoveFun = arrButtonAlow.length<=arrFunAllow.length?getArray(arrFunAllow, getArrDnAddorRemove(arrButtonDeny)):getArray(getArrDnAddorRemove(arrButtonDeny), arrFunAllow);
        arrAddFun = arrButtonDeny.length<=arrFunDeny.length?getArray(arrFunDeny, getArrDnAddorRemove(arrButtonAlow)):getArray(getArrDnAddorRemove(arrButtonAlow), arrFunDeny);
    }
    
    function getArray(arr1, arr2){
        arrTemp = new Array();
        for(i=0; i<arr1.length; ++i){
            flag = false;
            for(j=0; j<arr2.length; ++j){
                if(arr1[i]==arr2[j]){
                    flag = true;
                    break;
                }
            }
            if(flag){
                arrTemp[arrTemp.length] = "~"+arr1[i];
            }
        }
        return arrTemp;
    }
    
    
    function getArrDnAddorRemove(arr){
        arrTemp = new Array();
        for(i=0; i<arr.length; ++i){
            arrTemp[i] = arr[i].parentNode.parentNode.getElementsByTagName("TD")[3].getElementsByTagName("INPUT")[0].value;
        }
        return arrTemp;
    }
  function phannhomSubmit(type) {
      var frm = document.forms[0];
      if (type == "tracuu") {
          if (frm.nhom_id.value == '' || frm.nhom_id.value == 'null') {
              alert('Bạn phải chọn nhóm NSD.');
              document.getElementById('nhom_id_id').focus();
              return;
          }
          frm.action = "phanNhomAction.do";
      }else if( type == "ghi"){            
        getArrAddorRemove();
        var arrAddF = document.getElementById("arrAddF");
        var arrRemoveF = document.getElementById("arrRemoveF");
        frm = document.forms[0];        
        if(0<arrAddFun.length){
          arrAddFun[0] = arrAddFun[0].replace("~","");
        }
        if(0<arrRemoveFun.length){
          arrRemoveFun[0] = arrRemoveFun[0].replace("~","");
        }    
        arrAddF.value = arrAddFun;
        arrRemoveF.value = arrRemoveFun;
        
        frm.action = "phanNhomExcAction.do";
      }else if( type == "thoat"){      
        frm.action = "mainAction.do";
      }
      frm.submit();
  }
  function checkAllF(tbdId, checkAllId){
            var checkAll = document.getElementById(checkAllId);
            var tbdListAddFun = document.getElementById(tbdId);
            arrButton = tbdListAddFun.getElementsByTagName("input");
            if(checkAll.checked==true){
                for(i=0; i<arrButton.length; ++i){                    
                    arrButton[i].checked = true;
                    arrButton[i].parentNode.parentNode.style.backgroundColor = "#ffffb5";
                }
            }else{
                for(i=0; i<arrButton.length; ++i){                    
                    arrButton[i].checked = false;
                    arrButton[i].parentNode.parentNode.style.backgroundColor = "";
                }
            }
  }
  function ktCheckAllFun(check, tbdId, checkAllId) {
      if (check.checked == true) {
          check.parentNode.parentNode.style.backgroundColor = "#ffffb5";
      }
      else {
          check.parentNode.parentNode.style.backgroundColor = "";
      }
      var tbdListAddFun = document.getElementById(tbdId);
      arrButton = tbdListAddFun.getElementsByTagName("input");
      var checkAllFun = document.getElementById(checkAllId);
      var flag = true;
      for (i = 0;i < arrButton.length;++i) {
          if (arrButton[i].checked == false) {
              flag = false;
          }
      }
      if (flag) {
          checkAllFun.checked = true;
      }
      else 
          checkAllFun.checked = false;
  }
    function ktCheckAllFunAdd(){  
        ktCheckAllFun(this,'tbdListAddFunAllow','checkAllFunAllow');
    }
    function ktCheckAllFunRemove(){
        ktCheckAllFun(this,'tbdListAddFun','checkAllFun');
    }
  function addorRemove(tbdIdAlow, tbdIdDeny, action) {
      try {
          frm = document.forms[0];
          checkAllFun = document.getElementById("checkAllFun");
          checkAllFunAllow = document.getElementById("checkAllFunAllow");

          arrInput = new Array();
          flagAdd = true;
          flagRemove = true;
          if (arrButtonDeny != null) {
              for (i = 0;i < arrButtonDeny.length;++i) {
                  arrInput[arrInput.length] = arrButtonDeny[i];
                  if (action == "remove") {
                      arrButtonDeny[i].checked = false;
                      arrButtonDeny[i].parentNode.parentNode.style.backgroundColor = "";
                      checkAllFunAllow.checked = false;
                      checkAllFun.checked = false;
                  }
                  else if (arrButtonDeny[i].checked == true) {
                      flagAdd = false;
                  }
              }
          }

          if (arrButtonAlow != null) {
              for (i = 0;i < arrButtonAlow.length;++i) {
                  arrInput[arrInput.length] = arrButtonAlow[i];
                  if (action == "add") {
                      arrButtonAlow[i].checked = false;
                      arrButtonAlow[i].parentNode.parentNode.style.backgroundColor = "";
                      checkAllFun.checked = false;
                      checkAllFunAllow.checked = false;
                  }
                  else if (arrButtonAlow[i].checked == true) {
                      flagRemove = false;
                  }
              }
          }
          //
          for (i = 0;i < arrInput.length;++i) {
              if (action == "add") {
                  if (flagAdd) {
                      alert("Bạn chưa chọn user nào!");
                      if (null != arrButtonDeny[0]) {
                          setTimeout("arrButtonDeny[0].focus()", 1);
                      }
                      return;
                  }
                  checkAllFun.checked = false;
                  try {
                      if (arrInput[i].checked == true) {
                          flag = false;
                          arrInput[i].name = "dnRemoveFun";
                          arrInput[i].checked = false;
                          arrInput[i].onclick = ktCheckAllFunAdd;
                          arrInput[i].parentNode.parentNode.style.backgroundColor = "";
                          tbdListAddFunAlow.appendChild(arrInput[i].parentNode.parentNode);
                      }
                  }
                  catch (ex) {

                  }
              }
              else if (action == "remove") {              
                  if (flagRemove) {
                      alert("Bạn chưa chọn user nào!");
                      if (null != arrButtonAlow[0]) {
                          setTimeout("arrButtonAlow[0].focus()", 1);
                      }
                      return;
                  }
                  try {
                      checkAllFunAllow.checked = false;
                      if (arrInput[i].checked == true) {
                          arrInput[i].name = "dnAddFun";
                          arrInput[i].checked = false;
                          arrInput[i].onclick = ktCheckAllFunRemove;
                          arrInput[i].parentNode.parentNode.style.backgroundColor = "";
                          tbdListAddFunDeny.appendChild(arrInput[i].parentNode.parentNode);
                      }
                  }
                  catch (ex) {

                  }
              }
          }
          assignSTT(arrButtonAlow);
          assignSTT(arrButtonDeny);

      }
      catch (ex) {
          alert("Danh sách chức năng trống!");
      }
  }

  function assignSTT(arrInput) {
      if (0 < arrInput.length) {
          for (i = 0;i < arrInput.length;++i) {
              trPanrent = arrInput[i].parentNode;
              while (trPanrent.nodeName != "TR") {
                  trPanrent = trPanrent.parentNode;
              }
              tdChildNode = trPanrent.getElementsByTagName("TD")[0];
              j = i;
              tdChildNode.innerHTML = "<b>" + ++j + "<\/b>";
          }
      }
  }
</script>
<html:form action="/phanNhomAction.do" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        <fmt:message key="phannhom.title"/></strong>
    </h1>
  </div>
  <div class="app_error">
    <html:errors/>
  </div>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="phannhom.label.nhom"/>
              </label>
              <div class="col-sm-6">
                <html:select property="nhom_id" styleId="nhom_id_id"
                             styleClass="form-control selectpicker" onchange="phannhomSubmit('tracuu')"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Lựa chọn</html:option>
                  <logic:notEmpty name="colNhomNSD">
                    <html:optionsCollection name="colNhomNSD" value="id"
                                            label="ten_nhom"/>
                  </logic:notEmpty>
                </html:select>
              </div>
              <!--<div class="col-sm-2">
                <button type="button" onclick="phannhomSubmit('tracuu')"
                        class="ButtonCommon" style="width:100" accesskey="t">
                  <span class="sortKey">T</span>
                  ra cứu
                </button>
              </div>-->
            </div>
          </div>
        </div>
        <div class="row row-height">
          <div class="col-md-6 col-height">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2 class="panel-title">
                  <fmt:message key="phannhom.title.ds_nsd_ngoai_nhom"/>
                </h2>
              </div>
              <div class="panel-body">
                <table class="table table-bordered">
                  <thead>
                    <th>STT</th>
                    <th>
                      <fmt:message key="phannhom.label.mansd"/>
                    </th>
                    <th>
                      <fmt:message key="phannhom.label.tennsd"/>
                    </th>
                    <th>
                      <input type="checkbox" id="checkAllFun"
                             onclick="checkAllF('tbdListAddFun','checkAllFun')"/>
                    </th>
                  </thead>
                   
                  <tbody id="tbdListAddFun">
                    <logic:notEmpty name="colNSDNgoaiNhom">
                      <logic:iterate id="NSDNgoaiNhom" name="colNSDNgoaiNhom"
                                     indexId="stt">
                        <tr>
                          <td class="center">
                            <%=stt + 1%>
                          </td>
                          <td class="left">
                            <bean:write name="NSDNgoaiNhom" property="ma_nsd"/>
                          </td>
                          <td class="left">
                            <bean:write name="NSDNgoaiNhom" property="ten_nsd"/>
                          </td>
                          <td class="center">
                            <input type="checkbox" value="<bean:write name="NSDNgoaiNhom" property="id"/>" 
                            name="dnAddFun" onclick="ktCheckAllFun(this,'tbdListAddFun','checkAllFun')"/>
                          </td>
                        </tr>
                      </logic:iterate>
                    </logic:notEmpty>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="col-md-1 add-remove col-height center middle">
                  <span class="glyphicon glyphicon-forward" onclick="addorRemove('tbdListAddFunAllow', 'tbdListAddFun','add')"></span>
                  <span class="glyphicon glyphicon-backward" onclick="addorRemove('tbdListAddFunAllow', 'tbdListAddFun','remove')"></span>
          </div>

          <div class="col-md-6 col-height">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2 class="panel-title">
                  <fmt:message key="phannhom.title.ds_nsd_trong_nhom"/>
                </h2>
              </div>
              <div class="panel-body">
                <table class="table table-bordered">
                  <thead>
                    <th>STT</th>
                    <th>
                      <fmt:message key="phannhom.label.mansd"/>
                    </th>
                    <th>
                      <fmt:message key="phannhom.label.tennsd"/>
                    </th>
                    <th>
                      <input type="checkbox" id="checkAllFunAllow"
                             onclick="checkAllF('tbdListAddFunAllow','checkAllFunAllow')"/>
                    </th>
                  </thead>
                   
                  <tbody id="tbdListAddFunAllow">
                    <logic:notEmpty name="colNSDThuocNhom">
                      <logic:iterate id="NSDThuocNhom" name="colNSDThuocNhom"
                                     indexId="stt">
                        <tr>
                          <td class="center">
                            <%=stt + 1%>
                          </td>
                          <td class="left">
                            <bean:write name="NSDThuocNhom" property="ma_nsd"/>
                          </td>
                          <td class="left">
                            <bean:write name="NSDThuocNhom" property="ten_nsd"/>
                          </td>
                          <td class="center">
                            <input type="checkbox"
                                   value='<bean:write name="NSDThuocNhom" property="id"/>'
                                   name="dnAddFun"
                                   onclick="ktCheckAllFun(this,'tbdListAddFunAllow','checkAllFunAllow')"/>
                          </td>
                        </tr>
                      </logic:iterate>
                    </logic:notEmpty>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="center">
          <button type="button" onclick="phannhomSubmit('ghi')" class="btn btn-default" style="width:50" accesskey="g">
            <span class="sortKey">G</span>hi
          </button>
          <button type="button" onclick="phannhomSubmit('thoat')" class="btn btn-default" accesskey="o">
            Th<span class="sortKey">o</span>&#225;t
          </button>
  </div>
  <input type="hidden" name="arrAddF" id="arrAddF" />
  <input type="hidden" name="arrRemoveF" id="arrRemoveF" />
</html:form>
<script>
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
  
  $(".add-remove span").click(setTimeout( panelBody, 900));
</script>
<%@ include file="/includes/tpcp_bottom.inc"%>