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
      if (type == 'save') {
          f.action = 'TrinhKQQuanLyKHQuyAction.do';
          f.submit();
      }
      if (type == 'close') {
          f.action='ListQuanLyKeHoachQuyAction.do';
          f.submit();
      }
      if(type == 'print'){
          
           f.action = 'PrintKQQuanLyKHQuyAction.do';
           var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
           newDialog = window.open('', '_form', params);
           f.target = '_form';
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
        <fmt:message key="qlykehoach.title.QL"/></strong>
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
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.nam_phat_hanh"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="nam_ph"
                           name="QuanLyKeHoachQuyForm" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.quy_phat_hanh"/>
              </label>
              <div class="col-sm-8">
                <html:hidden property="thoi_gian_ph"/>
                <html:select property="thoi_gian_ph" styleId="thoi_gian_ph"
                             name="QuanLyKeHoachQuyForm" disabled="true"
                             tabindex="2" styleClass="form-control selectpicker"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Vui l&ograve;ng chọn</html:option>
                  <html:option value="1">Qu&yacute; I</html:option>
                  <html:option value="2">Qu&yacute; II</html:option>
                  <html:option value="3">Qu&yacute; III</html:option>
                  <html:option value="4">Qu&yacute; IV</html:option>
                </html:select>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
               
                <logic:equal value="VND" name="QuanLyKeHoachQuyForm" property="loai_tien">
                <fmt:message key="tpcp.tong_khoi_luong_vnd"/>
                </logic:equal>
                <logic:equal value="USD" name="QuanLyKeHoachQuyForm" property="loai_tien">
                <fmt:message key="tpcp.tong_khoi_luong_usd"/>
                </logic:equal>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="tong_klph"
                           name="QuanLyKeHoachQuyForm" readonly="true"/>
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
                           name="QuanLyKeHoachQuyForm" readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        <!--Row 4-->
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.so_thong_bao"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="so_tbao_kh"
                           name="QuanLyKeHoachQuyForm" readonly="true"/>
              </div>
            </div>
          </div>
          <logic:notEqual value="" name="QuanLyKeHoachQuyForm" property="so_tbao_kh_cu">
             <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.ngay_hieu_luc"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="ngay_hieu_luc"
                           name="QuanLyKeHoachQuyForm" readonly="true"/>
              </div>
            </div>
          </div>
        </logic:notEqual>
          
          
        </div>
        
        <div class="row">
        <logic:notEqual value="" name="QuanLyKeHoachQuyForm" property="so_tbao_kh_cu">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.so_thong_bao_cu"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="so_tbao_kh_cu"
                           name="QuanLyKeHoachQuyForm" readonly="true"/>
              </div>
            </div>
          </div>
        </logic:notEqual>
        <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.ngay_thong_bao"/>
              </label>
              <div class="col-sm-8">
                <html:text styleClass="form-control" property="ngay_tbao_kh"
                           name="QuanLyKeHoachQuyForm" readonly="true"/>
              </div>
            </div>
          </div>
        
        
        </div>
        
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <label for="hoten" class="col-sm-4 control-label">
                <fmt:message key="tpcp.mo_ta"/>
              </label>
              <div class="col-sm-8">
                <html:textarea styleClass="form-control" property="mo_ta"
                               style="margin: 0px; width: 770px; height: 50px;"
                               name="QuanLyKeHoachQuyForm" readonly="true"/>
              </div>
            </div>
          </div>
        </div>
        
          <div class="row">
        
          <logic:equal  value="03" name="QuanLyKeHoachQuyForm" property="trang_thai">
            <div class="col-md-6" id="div_lydotuchoi">
              <div class="form-group">
                <label for="hoten" class="col-sm-4 control-label">
                  <fmt:message key="tpcp.ly_do_tu_choi"/>
                </label>
                <div class="col-sm-7">
                  <html:textarea rows="2" styleClass="form-control" readonly="true"
                                 style="margin: 0px; width: 770px; height: 50px;"
                                 tabindex="3"
                                 styleId="ly_do_tu_choi"
                                 property="ly_do_tu_choi"/>
                </div>   
              </div>
            </div>
            </logic:equal>
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
            <logic:equal value="VND" name="QuanLyKeHoachQuyForm" property="loai_tien">
                <fmt:message key="tpcp.tong_khoi_luong_vnd"/>
                </logic:equal>
                <logic:equal value="USD" name="QuanLyKeHoachQuyForm" property="loai_tien">
                <fmt:message key="tpcp.tong_khoi_luong_usd"/>
                </logic:equal>
        </th>
      </thead>
       
      <tbody>
        <logic:empty name="QuanLyKeHoachQuyForm" property="lisKH">
          <tr>
            <td colspan="9" align="center">
              <fmt:message key="qlykehoach.norecord"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="QuanLyKeHoachQuyForm" property="lisKH">
          <logic:iterate name="QuanLyKeHoachQuyForm" property="lisKH" id="lisId"
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
                <html:hidden name="lisId" property="so_tien"/>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
        <tr>
          <td></td>
          <td style="text-align:center;font-weight: bold;">Tổng số</td>
          <td style="font-weight: bold;" class="center">
            <bean:write name="QuanLyKeHoachQuyForm" property="tong_chi_tiet"/>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <html:hidden property="guid" styleId="guid"/>
  <div class="center">
    <logic:notEqual value="1" name="QuanLyKeHoachQuyForm" property="trang_thai">
      <logic:notEqual value="2" name="QuanLyKeHoachQuyForm"
                      property="trang_thai">
            <logic:notEqual value="6" name="QuanLyKeHoachQuyForm"
                      property="trang_thai">
        <button type="button" class="btn btn-default" onclick="check('save')"
                accesskey="r">
          Đệ t<span class="sortKey">r</span>ình
        </button>
        </logic:notEqual>
      </logic:notEqual>
    </logic:notEqual>
     <logic:equal value="01" name="QuanLyKeHoachQuyForm" property="trang_thai">
        <button type="button" class="btn btn-default" onclick="check('print')" accesskey="i">
                <span class="sortKey">I</span>n
        </button>
     </logic:equal>
      <button type="button" class="btn btn-default" onclick="check('close')" accesskey="o">
               Th<span class="sortKey">o</span>át
        </button>
    <!--<a class="btn btn-default"
       href='<html:rewrite page="/SearchQuanLyKeHoachQuyAction.do"/>'> 
      Th<span class="sortKey">o</span>át </a>-->
  </div>
  <html:hidden property="guid" styleId="guid"/>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>