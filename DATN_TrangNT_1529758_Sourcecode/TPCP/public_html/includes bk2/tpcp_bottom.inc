 </div>
  <div class="footer">

    <p>Phi&#234;n b&#7843;n: 
    <span class="label label-primary">1.0
    </span>
    N&#259;m 
    <span class="label label-primary">2017
    </span>
    B&#7843;n quy&#7873;n thu&#7897;c:
   <span class="label label-primary">
   Kho B&#7841;c Nh&#224; n&#432;&#7899;c Vi&#7879;t Nam
   </span>
</p>
<p>
      &#272;i&#7879;n tho&#7841;i: <strong>(04)62764300</strong>
      | Fax: <strong>(04)62764367 &#8211; 
      (04)62764368 </strong>
</p>
<p> &#272;&#7883;a ch&#7881;:
      <strong>32 C&#225;t Linh, Qu&#7853;n &#272;&#7889;ng &#272;a, H&#224; N&#7897;i, Vi&#7879;t Nam<strong></p>
  </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/jquery-1.11.3.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed --> 
	<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/bootstrap.js"></script>
  <script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/bootstrap-select.js"></script>
  <script>
  var device = (/android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i.test(navigator.userAgent.toLowerCase()));
  var dropdown = $(".dropdown-toggle");
  $(".dropdown").hover(function() {  
    if(!device){
      $(this).find(dropdown).dropdown('toggle')  
    }
});
  </script>
  </BODY></HTML>