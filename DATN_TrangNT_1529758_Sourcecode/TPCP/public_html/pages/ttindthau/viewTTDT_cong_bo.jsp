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
      var guid = $("#guid").val();
      var checkbosung = '<bean:write name="ThongTinDauThauForm" property="checkBoSung"/>';
      document.getElementById("trang_thai").value="05";
      if ('landau' == checkbosung) {
          $("#noi_dung_mail").val($("#editor").html());
    
      }
      else {
      $("#noi_dung_mail").val($("#bosung").html());
      }
      f.action = 'SendCongBoTTinDThauAction.do';
      f.submit();
      }
  }
  
  $(function(){
  
  });
 
</script>
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
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
        <logic:equal value="landau" name="ThongTinDauThauForm"
                     property="checkBoSung">
                     
          <div class="adjoined-bottom">
            <div class="grid-container">
              <div class="grid-width-100">
              
                
                  <h4 style="text-align:center">
                    <strong>
                      <fmt:message key="ttindthau.congbo.thongbao1"/><bean:write name="ThongTinDauThauForm" property="dot_dau_thau"/></strong>
                  </h4>
                  <div style="text-align:center">
                    (<fmt:message key="ttindthau.congbo.thongbao2"/>)
                  </div>
                  <br/>
                  <div id="landau">
                    <div>
                      1.&nbsp;<fmt:message key="ttindthau.congbo.tplandau"/>
                    </div>
                    <div>
                      2.&nbsp;<fmt:message key="ttindthau.congbo.matp"/>&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                          property="ma_tpcp"/>
                    </div>
                    <div>
                      3.&nbsp;<fmt:message key="ttindthau.congbo.kltp"/>&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                  property="khoi_luong_tp"/>&nbsp;
                                                                                  <logic:equal value="VND" name="ThongTinDauThauForm"
                                                                                                                              property="loai_tien">
                                                                                              đồng
                                                                                  </logic:equal>
                                                                                  <logic:equal value="USD" name="ThongTinDauThauForm"
                                                                                                                              property="loai_tien">
                                                                                              đô
                                                                                  </logic:equal> (<bean:write name="ThongTinDauThauForm"
                                                                                                                              property="khoi_luong_tp_chu"/>)
                    </div>
                    <div>
                      4.&nbsp;<fmt:message key="ttindthau.congbo.kyhan"/>&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                 property="ky_han"/>
                    </div>
                    <div>
                      5.&nbsp;<fmt:message key="ttindthau.congbo.ngaytochuc"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                            property="ngay_to_chuc_ph"/>
                    </div>
                    <div>
                      6.&nbsp;<fmt:message key="ttindthau.congbo.ngayph"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                        property="ngay_ph"/>
                    </div>
                    <div>
                      7.&nbsp;<fmt:message key="ttindthau.congbo.ngaytttienmua"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                               property="ngay_tt_tien_mua"/>
                    </div>
                    <div>
                      8.&nbsp;<fmt:message key="ttindthau.congbo.ngaydenhantttp"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                                property="ngay_dao_han"/>
                    </div>
                    <div>
                      9.&nbsp;<fmt:message key="ttindthau.congbo.hinhthucdttp"/>&nbsp; 
                      <logic:equal value="01" name="ThongTinDauThauForm"
                                   property="hinh_thuc_dt">
                        <fmt:message key="ttindthau.congbo.hinhthucdttp1"/>
                      </logic:equal>
                      <logic:equal value="02" name="ThongTinDauThauForm"
                                   property="hinh_thuc_dt">
                        <fmt:message key="ttindthau.congbo.hinhthucdttp2"/>
                      </logic:equal>
                       <fmt:message key="ttindthau.congbo.hinhthucdttp3"/>&nbsp;30%&nbsp;<fmt:message key="ttindthau.congbo.hinhthucdttp4"/>.
                    </div>
                    <div>
                      10.&nbsp;<fmt:message key="ttindthau.congbo.phuongthucxd"/>&nbsp;<fmt:message key="ttindthau.congbo.dauthau"/>
                       
                      <logic:equal value="01" name="ThongTinDauThauForm"
                                   property="pthuc_xacdinh_kqdt">
                        <fmt:message key="ttindthau.congbo.dongia"/>
                      </logic:equal>
                       
                      <logic:equal value="02" name="ThongTinDauThauForm"
                                   property="pthuc_xacdinh_kqdt">
                        <fmt:message key="ttindthau.congbo.dagia"/>
                      </logic:equal>
                    </div>
                    <div>
                      11.&nbsp;<fmt:message key="ttindthau.congbo.tpban"/>
                    </div>
                    <div>
                      12.&nbsp;Phương thức thanh toán gốc, lãi:  Tiền gốc trái phiếu được thanh toán 1 lần khi đến hạn; 
                      <logic:equal value="0" name="ThongTinDauThauForm"
                                   property="ky_tra_lai"></logic:equal>
                       
                      <logic:notEqual value="0" name="ThongTinDauThauForm" property="ky_tra_lai">
                      <logic:equal value="1" name="ThongTinDauThauForm" property="ky_tra_lai">
                      Tiền lãi trái phiếu được thanh toán định kỳ hằng năm vào ngày <%= request.getAttribute("ngay_tt_lai_1")%> từ năm 
                      <%= request.getAttribute("namph") %> đến năm đáo hạn
                      </logic:equal>
                       <logic:equal value="2" name="ThongTinDauThauForm" property="ky_tra_lai">
                      Tiền lãi trái phiếu được thanh toán định kỳ hằng năm vào ngày <%= request.getAttribute("ngay_tt_lai_1")%> và ngày <%= request.getAttribute("ngay_tt_lai_2")%> từ năm 
                      <%= request.getAttribute("namph") %> đến năm đáo hạn
                      </logic:equal>
                      </logic:notEqual>
                    </div>
                    <div>
                      13.&nbsp;<fmt:message key="ttindthau.congbo.13"/>
                    </div>
                    <div>
                      14.&nbsp;<fmt:message key="ttindthau.congbo.14"/>&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                         property="so_tk_nhan"/>&nbsp;<fmt:message key="ttindthau.congbo.14.2"/>
                    </div>
                  </div>
                </div>
              
              </div>
            </div>
        
        </logic:equal>
         
        <!--end cong bo lan dau  -->
         
        <!--start cong bo bo sung  -->
         
        <logic:equal value="bosung" name="ThongTinDauThauForm"
                     property="checkBoSung">
                         
          <div class="adjoined-bottom">
            <div class="grid-container">
              <div class="grid-width-200">
                <div id="editor">
                  <h4 style="text-align:center">
                    <strong>
                      <fmt:message key="ttindthau.congbo.thongbao1"/><bean:write name="ThongTinDauThauForm"
                                                                                  property="dot_dau_thau"/></strong>
                  </h4>
                  <div style="text-align:center">
                    (<fmt:message key="ttindthau.congbo.thongbao2"/>)
                  </div>
                  <br/>
                  <div id="bosung">
                    <div>
                      <strong>1.&nbsp;<fmt:message key="ttindthau.noidungtp"/>&nbsp;<bean:write name="ThongTinDotDTCu"
                                                                                                property="dot_dau_thau"/></strong>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.matp"/>&nbsp;<bean:write name="ThongTinDotDTCu"
                                                                                  property="ma_tpcp"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.kltp"/>&nbsp;<bean:write name="ThongTinDotDTCu"
                                                                                  property="khoi_luong_tp"/>&nbsp;
                                                                                  <logic:equal value="VND" name="ThongTinDotDTCu"
                                                                                                                              property="loai_tien">
                                                                                              đồng
                                                                                  </logic:equal>
                                                                                  <logic:equal value="USD" name="ThongTinDotDTCu"
                                                                                                                              property="loai_tien">
                                                                                              đô
                                                                                  </logic:equal> (<bean:write name="ThongTinDotDTCu"
                                                                                                                              property="khoi_luong_tp_chu"/>)
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.kyhan"/>&nbsp;&nbsp;<bean:write name="ThongTinDotDTCu"
                                                                                         property="ky_han"/>
                    </div>
                     <div>
                      -&nbsp;Lãi suất danh nghĩa:&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                       property="ls_danh_nghia"/>&nbsp;%/năm
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.ngayph"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDotDTCu"
                                                                                                       property="ngay_ph"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.ngaydenhantttp"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDotDTCu"
                                                                                                               property="ngay_dao_han"/>
                    </div>
                    <div>
                      -&nbsp;Phương thức thanh toán gốc, lãi: Tiền gốc trái phiếu được thanh toán 1 lần khi đến hạn; 
                        <logic:notEqual value="0" name="ThongTinDauThauForm" property="ky_tra_lai">
                      <logic:equal value="1" name="ThongTinDauThauForm" property="ky_tra_lai">
                      Tiền lãi trái phiếu được thanh toán định kỳ hằng năm vào ngày <%= request.getAttribute("ngay_tt_lai_1")%> từ năm 
                      <%= request.getAttribute("namph") %> đến năm đáo hạn
                      </logic:equal>
                       <logic:equal value="2" name="ThongTinDauThauForm" property="ky_tra_lai">
                       Tiền lãi trái phiếu được thanh toán định kỳ hằng năm vào ngày <%= request.getAttribute("ngay_tt_lai_1")%> và ngày <%= request.getAttribute("ngay_tt_lai_2")%> từ năm 
                      <%= request.getAttribute("namph") %> đến năm đáo hạn
                      </logic:equal>
                      </logic:notEqual>
                       
                     <!--  <fmt:message key="ttindthau.congbo.phuongthucgoclai"/>
                      <logic:equal value="0" name="ThongTinDotDTCu"
                                   property="ky_tra_lai">cuối kỳ.</logic:equal>
                       
                      <logic:notEqual value="0" name="ThongTinDotDTCu"
                                      property="ky_tra_lai">định kỳ hằng năm vào ngày <bean:write name="ThongTinDotDTCu"
                                                                                                               property="ngay_tt_lai_1"/> năm <%= request.getAttribute("namph") %> đáo hạn</logic:notEqual>-->
                      
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.13"/>
                    </div>
                    <div>
                      <strong>2.&nbsp;<fmt:message key="ttindthau.noidungphbs"/></strong>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.matp"/>&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                  property="ma_tpcp"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.kltp"/>&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                  property="khoi_luong_tp"/>&nbsp;<logic:equal value="VND" name="ThongTinDotDTCu"
                                                                                                                              property="loai_tien">
                                                                                              đồng
                                                                                  </logic:equal>
                                                                                  <logic:equal value="USD" name="ThongTinDotDTCu"
                                                                                                                              property="loai_tien">
                                                                                              đô
                                                                                  </logic:equal>
                         (<bean:write name="ThongTinDauThauForm"  property="khoi_luong_tp_chu"/>)                                                         
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.kyhan"/>&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                         property="ky_han"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.ngaytochuc"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                           property="ngay_to_chuc_ph"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.ngayph"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                       property="ngay_ph"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.ngaytttienmua"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                              property="ngay_tt_tien_mua"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.ngaydenhantttp"/>&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                               property="ngay_dao_han"/>
                    </div>
                    <div>
                      -&nbsp;Lãi suất danh nghĩa:&nbsp;&nbsp;&nbsp;<bean:write name="ThongTinDauThauForm"
                                                                                                       property="ls_danh_nghia"/>&nbsp;%/năm
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.tpbangmenhgia"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.phuongthucgoclai333"/>
                      </div>
                      -&nbsp;Phương thức xác định kết quả đấu thầu :
                      <logic:equal value="01"  name="ThongTinDauThauForm" property="pthuc_xacdinh_kqdt">Đấu thầu đơn giá</logic:equal>
                       <logic:equal value="02"  name="ThongTinDauThauForm" property="pthuc_xacdinh_kqdt">Đấu thầu đa giá</logic:equal>
                   <!--   <logic:equal value="0" name="ThongTinDauThauForm"
                                   property="ky_tra_lai">cuối kỳ.</logic:equal>&nbsp;<bean:write name="ThongTinDotDTCu"
                                                                                                               property="ngay_tt_lai_1"/>
                       
                      <logic:notEqual value="0" name="ThongTinDauThauForm"
                                      property="ky_tra_lai">định kỳ.</logic:notEqual>
                       &nbsp;<fmt:message key="ttindthau.congbo.phuongthucgoclai2"/>&nbsp;<bean:write name="ThongTinDotDTCu"
                                                                                                               property="ngay_tt_lai_1"/>
                    </div>
                    <div>
                      -&nbsp;<fmt:message key="ttindthau.congbo.phuongthucxd"/>&nbsp;<fmt:message key="ttindthau.congbo.dauthau"/>
                       
                      <logic:equal value="01" name="ThongTinDauThauForm"
                                   property="pthuc_xacdinh_kqdt">
                        <fmt:message key="ttindthau.congbo.dongia"/>
                      </logic:equal>
                       
                      <logic:equal value="02" name="ThongTinDauThauForm"
                                   property="pthuc_xacdinh_kqdt">
                        <fmt:message key="ttindthau.congbo.dagia"/>
                      </logic:equal>
                    </div>-->
                    <div>
                      -&nbsp;Tr&aacute;i phiếu ph&aacute;t h&agrave;nh dưới
                      h&igrave;nh thức ghi sổ, được ni&ecirc;m yết bổ sung
                      v&agrave;o m&atilde; tr&aacute;i phiếu&nbsp; 
                      <bean:write name="ThongTinDotDTCu" property="ma_tpcp"/>,
                                                                             kỳ
                                                                             hạn 
                      <bean:write name="ThongTinDotDTCu" property="ky_han"/>,&nbsp;
                                                                            ph&aacute;t
                                                                            h&agrave;nh
                                                                            ng&agrave;y
                      <bean:write name="ThongTinDotDTCu" property="ngay_ph"/>
                        tại Sở Giao dịch Chứng khoán Hà Nội.
                    </div>
                    <div>
                      -&nbsp;C&aacute;c đơn vị tr&uacute;ng thầu chuyển tiền mua
                      tr&aacute;i phiếu v&agrave;o t&agrave;i khoản tiền đồng
                      Việt Nam, t&agrave;i khoản số&nbsp; 
                      <bean:write name="ThongTinDauThauForm"
                                  property="so_tk_nhan"/>
                       của Cục Kế toán Nhà nước mở tại Sở giao dịch Ngân hàng Nhà nước theo quy định./
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
           
        </logic:equal>
   </textarea>
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
  <html:hidden property="trang_thai" styleId="trang_thai" value=""/>
  <html:hidden property="guid" styleId="guid"/>
  <script type="text/javascript">
    $(function () {
        $("#noi_dung_mail").val($("#bosung").text());
    });
  </script>
</html:form>
<%@ include file="/includes/tpcp_bottom.inc"%>