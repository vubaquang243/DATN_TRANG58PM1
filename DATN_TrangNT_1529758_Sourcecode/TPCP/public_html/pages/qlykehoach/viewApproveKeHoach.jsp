<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.tp.resource.QuanLyKeHoachQuy"/>
<%@ include file="/includes/tpcp_header.inc"%>
<script type="text/javascript">
  //jQuery.noConflict();
  jQuery(document).ready(function () {
  });

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      var ly_do_tu_choi = $("#ly_do_tu_choi").val();
      if (type == 'pheduyet') {
          if (confirm('Bạn có muốn phê duyệt kế hoạch quý này không ?')) {
              f.action = 'PheDuyetKHAction.do';
              f.submit();
          }
      }
      if (type == 'tuchoi') {
          
          if ( ly_do_tu_choi == "") {
              alert('Bạn phải nhập lý do từ chối!');
              $("#ly_do_tu_choi").focus();
              return false;
          }
          f.action = 'TuChoiKHQAction.do';
          f.submit();
      }
      if(type== 'close'){
        f.action='ListQuanLyKeHoachPDAction.do';
        f.submit();
      }

  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="QuanLyKeHoachQuyViewAction" method="post">
  <div class="panel-heading border-bottom">
    <h1 class="panel-title">
      <strong>
        PHÊ DUYỆT KẾ HOẠCH NĂM</strong>
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
        <html:hidden property="guid" name="QuanLyKeHoachForm"/>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.nam_phat_hanh"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="nam_ph"
                           name="QuanLyKeHoachForm" readonly="true"/>
              </div>
            </div>
          </div>
            <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.ngay_thong_bao"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="ngay_tbao_kh"
                           name="QuanLyKeHoachForm" readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <logic:equal value="VND" name="QuanLyKeHoachForm" property="loai_tien">
                <fmt:message key="tpcp.tong_khoi_luong_vnd"/>
                </logic:equal>
                <logic:equal value="USD" name="QuanLyKeHoachForm" property="loai_tien">
                <fmt:message key="tpcp.tong_khoi_luong_usd"/>
                </logic:equal>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="tong_klph"
                           name="QuanLyKeHoachForm" readonly="true"/>
              </div>
            </div>
          </div>
          
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.loai_tien"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="loai_tien"
                           name="QuanLyKeHoachForm" readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
        <logic:notEqual value="" name="QuanLyKeHoachForm" property="kh_goc">
         
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="qlykehoach.ngay_hieu_luc_tu_ngay"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="ngay_hieu_luc"
                           name="QuanLyKeHoachForm" readonly="true"/>
              </div>
            </div>
          </div>
        </logic:notEqual>
        </div>
        <!--Row 4-->
        <div class="row">
          
        
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.mo_ta"/>
              </label>
              <div class="col-sm-7">
                <html:textarea styleClass="form-control" property="mo_ta" rows="2"
                               style="margin: 0px; width: 770px; height: 50px;"
                               name="QuanLyKeHoachForm" readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6" id="div_lydotuchoi">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.ly_do_tu_choi"/>
              </label>
              <div class="col-sm-7">
              <logic:equal value="01" name="QuanLyKeHoachForm" property="trang_thai">
                <html:textarea rows="2" styleClass="form-control"
                                 style="margin: 0px; width: 770px; height: 50px;"
                               onkeypress="return blockKeySpecial001(event)"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               
                               onblur="textlostfocus(this);" tabindex="3"
                               styleId="ly_do_tu_choi"
                               property="ly_do_tu_choi" />
               </logic:equal>
                <logic:notEqual value="01" name="QuanLyKeHoachForm" property="trang_thai">
                <html:textarea rows="2" styleClass="form-control"
                                 style="margin: 0px; width: 770px; height: 50px;"
                               styleId="ly_do_tu_choi"
                               property="ly_do_tu_choi" readonly="true" />
               </logic:notEqual>
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
            <logic:equal value="VND" name="QuanLyKeHoachForm" property="loai_tien">
                <fmt:message key="tpcp.tong_khoi_luong_vnd"/>
                </logic:equal>
                <logic:equal value="USD" name="QuanLyKeHoachForm" property="loai_tien">
                <fmt:message key="tpcp.tong_khoi_luong_usd"/>
                </logic:equal>
        </th>
      </thead>
       
      <tbody>
        <logic:empty name="QuanLyKeHoachForm" property="lisKH">
          <tr>
            <td colspan="9" align="center">
              <fmt:message key="qlykehoach.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="QuanLyKeHoachForm" property="lisKH">
          <logic:iterate name="QuanLyKeHoachForm" property="lisKH" id="lisId"
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
                <bean:write name="lisId" property="so_tien"/>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
        <tr>
          <td></td>
          <td style="text-align:center;font-weight: bold;">Tổng số</td>
          <td style="font-weight: bold;" class="center">
            <bean:write name="QuanLyKeHoachForm" property="tong_chi_tiet"/>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <html:hidden property="guid" styleId="guid"/>
  <div class="center">
    <logic:equal value="01" name="QuanLyKeHoachForm" property="trang_thai">
    
    <button type="button" class="btn btn-default" onclick="check('pheduyet')"
            accesskey="d" tabindex="7" id="bfind">
      <span class="sortKey">D</span>uyệt
    </button>
     
    <button type="button" class="btn btn-default" onclick="check('tuchoi')"
            accesskey="c" tabindex="7" id="bfind">
      Từ <span class="sortKey">c</span>hối
    </button>
     </logic:equal>
     <button type="button" class="btn btn-default" onclick="check('close')"
            accesskey="o" tabindex="7" id="bfind">
      Th<span class="sortKey">o</span>át 
    </button>
    <!--<a class="btn btn-default"
       href='<html:rewrite page="/ListQuanLyKeHoachQuyPDAction.do"/>'
       accesskey="t"> -->
      
  </div>
  
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>