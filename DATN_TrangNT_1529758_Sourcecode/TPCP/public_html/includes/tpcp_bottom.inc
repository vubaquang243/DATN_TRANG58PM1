</div>

<TABLE border=0 cellSpacing=0 cellPadding=0 width=985 align=center>
  <TBODY>
  <TR>
    <TD height=41 background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/footer.jpg>
      <TABLE border=0 cellSpacing=0 cellPadding=0 width="98%" align=center>
        <TBODY>
        <TR>
          <TD vAlign=top align=left>B&#7843;n quy&#7873;n thu&#7897;c v&#7873; Kho B&#7841;c Nh&#224; n&#432;&#7899;c Vi&#7879;t 
            Nam <BR>Phi&#234;n b&#7843;n 1.0 &nbsp;&nbsp;&nbsp;N&#259;m 2017 </TD>
          <TD align=right>&#272;&#7883;a ch&#7881;: 32 C&#225;t Linh, Qu&#7853;n &#272;&#7889;ng &#272;a, H&#224; N&#7897;i, Vi&#7879;t Nam 
            <BR>&#272;i&#7879;n tho&#7841;i: (04)62764300 Fax: (04)62764367 &#8211; (04)62764368 </TD>
          <TD vAlign=top align=left></TD>
          <TD vAlign=top 
align=right></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
  <script>
  $(document).ready(function() {  
   $('#css3menu1 li').find('ul').each(function() {
     var maxHeight = -1;
     $(this).show();
     $('li', this).each(function() {
        a = $(this).outerHeight();
        maxHeight = maxHeight > a ? maxHeight : a;
     });
      $('li',this).height(maxHeight);
     $('li',this).not($('li',this).find('ul').closest("li")).addClass("mot-lv").height('');
     $(this).css("display", "");
   });
 });
 $(document).ready(function () {
          $(".input-group-addon").on("click",function() {
          $(this).prev().focus();
        });
  });
  </script>
  
</BODY></HTML>
