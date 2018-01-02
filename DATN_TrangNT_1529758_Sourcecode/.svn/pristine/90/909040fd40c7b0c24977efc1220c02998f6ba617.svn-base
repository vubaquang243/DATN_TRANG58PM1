<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.tp.qlytp.vo.QuanLyTPCPVO"%>
<%@ page import="com.seatech.tp.ttindthau.vo.ThongTinDauThauVO"%>
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
        ThongTinDauThauVO thongTinDotDTCu = (ThongTinDauThauVO)request.getAttribute("ThongTinDotDTCu");
  %>
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if(type=='close'){
        f.action='ListTTinDThauAction.do';
        f.submit();
      }else{
    document.getElementById("trang_thai").value="05";
     $("#noi_dung_mail").val($("#landau").html());
     
      f.action = 'SendCongBoTTinDThauAction.do';
      f.submit();
      }
  }
  
    $(function () {
       
       
    });
</script>
<html:form action="AddExeTTinDThauAction" method="post">
  <html:hidden property="noi_dung_mail" name="ThongTinDauThauForm"
               styleId="noi_dung_mail" value=""/>
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
        <fmt:message key="ttindthau.congbo.title"/>
      </h2>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <!--start cong bo lan dau  -->
        <textarea cols="80" rows="50" id="editor" name="valuetext">
          
                
               <%-- <logic:empty name="ThongTinDauThauForm" property="dot_bo_sung">--%>
                <div class="adjoined-bottom">
            <div class="grid-container">
              <div class="grid-width-100">
                  <h4 style="text-align:center">
                    <strong>
                      THÔNG BÁO PHÁT HÀNH THÊM TRÁI PHIẾU CHÍNH PHỦ ĐỢT <bean:write name="ThongTinDauThauForm" property="dot_dau_thau"/></strong>
                  </h4>
                  <div style="text-align:center">
                    (<fmt:message key="ttindthau.congbo.thongbao2"/>)
                  </div>
                  <br/>
                  <div id="landau">
                    <div>
                      &nbsp;Thực hiện nhiệm vụ huy động vốn năm 2017 căn cứ kết quả đấu thầu đợt <bean:write name="ThongTinDauThauForm" property="dot_dau_thau"/> , 
                      tổ chức ngày <bean:write name="ThongTinDauThauForm" property="ngay_to_chuc_ph"/> , 
                      Kho bạc Nhà nước đề nghị Sở Giao dịch Chứng khoán Hà Nội và Trung tâm Lưu ký Chứng khoán Việt Nam phối hợp tổ chức đấu thầu phát hành thêm trái phiếu Chính phủ cho đợt <bean:write name="ThongTinDauThauForm" property="dot_dau_thau"/>, cụ thể:
                    </div>
                    <div>
                      -.&nbsp;<fmt:message key="ttindthau.congbo.matp"/>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                 property="ma_tpcp"/>
                      
                    </div>
                    <div>
                      -.&nbsp;<fmt:message key="ttindthau.congbo.kltp.them"/>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                          property="khoi_luong_them"/>&nbsp;&nbsp;<logic:equal value="VND" name="ThongTinDauThauForm"
                                                                                                                              property="loai_tien">
                                                                                              đồng
                                                                                  </logic:equal>
                                                                                  <logic:equal value="USD" name="ThongTinDauThauForm"
                                                                                                                              property="loai_tien">
                                                                                              đô
                                                                                  </logic:equal>
                         (<bean:write name="ThongTinDauThauForm"  property="khoi_luong_them_chu"/>) 
                    </div>
                    <div>

                       -.&nbsp;<fmt:message key="ttindthau.congbo.kyhan"/>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                 property="ky_han"/>                                                                   
                    </div>
                    <div>
                       -.&nbsp;Lãi suất phát hành : &nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                          property="ls_danh_nghia"/>&nbsp;%/năm
                    </div>
                    <div>
                      -.&nbsp;Thời gian đăng ký mua thêm&nbsp;&nbsp;&nbsp;Từ 13h30 đến trước 15h00 ngày <bean:write name="ThongTinDauThauForm" property="ngay_to_chuc_ph"/>
                    </div>
                    <div>
                     Đề nghị Sở Giao dịch Chứng khoán Hà Nội và Trung tâm Lưu ký Chứng khoán Việt Nam tổ chức đấu thầu và niêm yết khối lượng, đăng ký lưu ký trái phiếu trúng thầu theo đúng quy định hiện hành./.
                    </div>
                  </div>
                  </div>
                  </div>
                  </div>

          </textarea>
         
        <!--end cong bo lan dau  -->
         
        <!--start cong bo bo sung  -->
         
       
         
        <!--end cong bo bo sung -->
      </div>
    </div>
  </div>
  <script>
    initSample();
  </script>
  <div class="center">
    <button id="tracuu" type="button" onclick="check('pheduyet')"
            class="btn btn-default" accesskey="g">
      <span class="sortKey">G</span>ửi
    </button>
      <button id="thoat" type="button" onclick="check('close')"
              class="btn btn-default" accesskey="o">
        Th<span class="sortKey">o</span>át</a> 
      </button>
  
  </div>
  <html:hidden property="trang_thai" styleId="trang_thai" value="05"/>
  <html:hidden property="guid" styleId="guid"/>
  
  <script type="text/javascript">
    $(function () {
        $("#noi_dung_mail").val($("#bosung").text());
    });
  </script>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>