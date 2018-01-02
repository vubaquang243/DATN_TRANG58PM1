<!doctype html>
<html lang="en">
  <%@ include file="/includes/tpcp_header.inc"%>
  <script>
    function validatedate(inputText) {
        var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
        var dateformat2 = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4} (00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9]):([0-9]|[0-5][0-9])$/;
        
        // Match the date format through regular expression  
        if (inputText.value.match(dateformat) || inputText.value.match(dateformat2)) {
            //Test which seperator is used '/' or '-'  
            var opera1 = inputText.value.split('/');
            var opera2 = inputText.value.split('-');
            lopera1 = opera1.length;
            lopera2 = opera2.length;
            // Extract the string into month, date and year  
            if (lopera1 > 1) {
                var pdate = inputText.value.split('/');
            }
            else if (lopera2 > 1) {
                var pdate = inputText.value.split('-');
            }
            var dd = parseInt(pdate[0]);
            var mm = parseInt(pdate[1]);
            var yy = parseInt(pdate[2]).substring(4);
            // Create list of days of a month [assume there is no leap year by default]  
            var ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            if (mm == 1 || mm > 2) {
                if (dd > ListofDays[mm - 1]) {
                    return false;
                }
            }
            if (mm == 2) {
                var lyear = false;
                if ((!(yy % 4) && yy % 100) || !(yy % 400)) {
                    lyear = true;
                }
                if ((lyear == false) && (dd >= 29)) {
                    return false;
                }
                if ((lyear == true) && (dd > 29)) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }
  </script>
  <body>
    <div class="ui-widget">
      <label for="tags">Tags:</label>
       
      <input id="tags"></input>
    </div>
    <p>
      Date: 
      <input type="text" id="datepicker"></input>
    </p>
  </body>
  <%@ include file="/includes/tpcp_bottom.inc"%>
</html>