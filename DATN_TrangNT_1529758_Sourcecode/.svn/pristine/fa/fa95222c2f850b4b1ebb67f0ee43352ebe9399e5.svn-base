<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyTPResource"/>
<%@ include file="/includes/tpcp_header.inc"%>
<!--  show mess error -->
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      f.action = '';
      var guid = document.getElementById("guid").value;
      if (type == 'save') {
          if (guid != null && guid != "") {
              f.action = 'UpdateExcDVSHListAction.do';
          }
          else {
              f.action = 'AddExcDVSHListAction.do';
          }
          if (validateForm()) {
              f.submit();
          }
      }
      if (type == 'close') {
          f.action = 'QuanLyDVSHListAction.do';
          f.submit();
      }
  }
  function validateForm() {
      var ma_tv = document.getElementById("ma_tv").value;
      var ma_chu_so_huu = document.getElementById("ma_chu_so_huu").value;
      var ten_dvi_so_huu = document.getElementById("ten_dvi_so_huu").value;
      var loai_hinh = document.getElementById("loai_hinh").value;
//      if (ma_tv == null || ma_tv == "") {
//          alert('Bạn phải nhập mã thành viên');
//          $("#ma_tv").focus();
//          return false;
//      }
      if (ma_chu_so_huu == null || ma_chu_so_huu == "") {
          alert('Bạn phải nhập mã chủ sở hữu');
          $("#ma_chu_so_huu").focus();
          return false;
      }
      if (ten_dvi_so_huu == null || ten_dvi_so_huu == "") {
          alert('Bạn phải nhập tên đơn vị sở hữu');
          $("#ten_dvi_so_huu").focus();
          return false;
      }
      if (loai_hinh == null || loai_hinh == "") {
          alert('Bạn phải nhập loại hình');
          $("#loai_hinh").focus();
          return false;
      }      
      return true;
  }

  $(function () {
      $("#ma_tv").focus();      
  }); 
  function changeDVTT(ten_dvi_so_huu){
     $.ajax( {
          type : "post", url : "GetAjaxDonViSoHuuAction.do", data :  {
              ten_dvi_so_huu : ten_dvi_so_huu
          },
          async : true, cache : false, success : function (data) {
              var objArr = JSON.parse(data);
              if(objArr!=null && objArr.length > 0){
                $('#lstDViSoHuu').html('');
                  for(var i =0; i < objArr.length;i++){
                      var abc = objArr[i].ma_tv==undefined?"":objArr[i].ma_tv;
                      $('#lstDViSoHuu').append('<tr class="trDanhSachChan"><td class = "center">'+ (i + 1) +'</td><td>'+ abc +'</td><td>'+objArr[i].ma_chu_so_huu+'</td><td>'+objArr[i].ten_dvi_so_huu+'</td></tr>');
                  }  
              }  
          }
      });
  }
</script>
<html:form action="AddExcDVSHListAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
        <strong>Quản lý danh mục Đơn vị sở hữu</strong>
    </h1>
  </div>
  <div class="app_error">
  <html:errors/>
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
      <h2 class="panel-title">
        <strong>Thêm mới Đơn vị sở hữu</strong>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--Row 1  -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-4 control-label">
                Mã TV
                <!--<span style="color:red">(*)</span>-->
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           maxlength="10"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="1"
                           styleId="ma_tv" property="ma_tv"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label class="col-sm-4 control-label">
                Mã đơn vị sở hữu
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-8">
                <logic:empty name="DMTraiChuForm" property="guid">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           maxlength="10"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="ma_chu_so_huu" property="ma_chu_so_huu"/>
                </logic:empty>
                <logic:notEmpty name="DMTraiChuForm" property="guid">
                <html:text styleClass="form-control"
                           onkeypress="return blockKeySpecial001(event)"
                           maxlength="10"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           readonly="true"
                           onblur="textlostfocus(this);" tabindex="2"
                           styleId="ma_chu_so_huu" property="ma_chu_so_huu"/>
                </logic:notEmpty>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label class="col-sm-2 control-label">
                Tên đơn vị sở hữu <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-10">
                <html:text styleClass="form-control" onkeyup="changeDVTT(this.value);"
                           onkeypress="return blockKeySpecial001(event)"
                           maxlength="300"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           onfocus="textfocus(this);"
                           onblur="textlostfocus(this);" tabindex="3"
                           styleId="ten_dvi_so_huu" property="ten_dvi_so_huu"/>
              </div>
            </div>
          </div>          
        </div>
        <!--Row 2 -->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Loại hình
                <span style="color:red">(*)</span>
              </label>
              <div class="col-sm-8">
                <html:select property="loai_hinh" styleId="loai_hinh" tabindex="4"
                             styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="NHNN">Ngân hàng nhà nước</html:option>
                  <html:option value="NH">Ngân hàng thương mại</html:option>
                  <html:option value="BHXH">Bảo hiểm xã hội</html:option>
                  <html:option value="BH">Công ty bảo hiểm</html:option>
                  <html:option value="QDT">Quỹ đầu tư</html:option>
                  <html:option value="CK">Công ty chứng khoán</html:option>
                  <html:option value="TC">Công ty tài chính</html:option>
                  <html:option value="KHAC">Khác</html:option>
                </html:select>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                Cơ cấu
                <span style="color:red">(*)</span> 
              </label>
              <div class="col-sm-8">
                <html:select styleClass="form-control selectpicker"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="5"
                             styleId="co_cau" property="co_cau">
                  <html:option value="TN">Trong nước</html:option>
                  <html:option value="NN">Nước ngoài</html:option>
                </html:select>
              </div>
            </div>
          </div>
        </div>
        <!--Row 3  -->
        <!--Row 4 -->
        <div class="row">
          <div class="col-md-6">            
              <html:hidden property="trang_thai" styleId="trang_thai" value="01"/>
              <label for="hoten" class="col-sm-4 control-label">Bán lẻ</label>
              <div class="col-sm-8">
                <logic:equal name="DMTraiChuForm" property="ban_le"
                             value="1">
                  <input type="checkbox" class="form-control" tabindex="6"
                         checked="checked" id="ban_le" name="ban_le"/>
                </logic:equal>
                 
                <logic:notEqual name="DMTraiChuForm" property="ban_le"
                                value="1">
                  <input type="checkbox" class="form-control" tabindex="6"
                         id="ban_le" name="ban_le"/>
                </logic:notEqual>
              </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="center">
    <button type="button" class="btn btn-default" onclick="check('save')"
            accesskey="g" tabindex="7" id="bfind">
      <span class="sortKey">G</span>hi
    </button>
     
    <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="8">
      Th<span class="sortKey">o</span>&aacute;t
    </button>
  </div>
  <br><br>
  <div class="panel panel-default">
    <!--<div class="panel-heading">
      <h2 class="panel-title">
        Kết quả tìm kiếm
      </h2>
    </div>-->
    <%-- Hiển thị list KTV--%>
    <table class="table table-bordered">
      <thead>
        <th class="center">STT</th>
        <th>
          Mã TV
        </th>
        <th>
          Mã chủ sở hữu
        </th>
        <th>
          Tên đơn vị sở hữu
        </th>        
      </thead>
      <tbody id="lstDViSoHuu">
       
      </tbody>
    </table>
    <%-- ************************************--%>
  </div>
  
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>