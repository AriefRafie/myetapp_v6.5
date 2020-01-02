
<style type="text/css">
#marquee_replacement_td {
    border-bottom: 1px solid #C7A317;
    overflow: auto;
    width: 100%;
	margin-bottom: 10px;	
}

#mask {
	display: none;
	background: #000; 
	position: fixed; left: 0; top: 0; 
	z-index: 10;
	width: 100%; height: 100%;
	opacity: 0.8;
	z-index: 999;
}

/* You can customize to your needs  */
.memo-popup{
	display:none;
	background: #CCD7B1;
	padding: 10px; 	
	border: 2px solid #ddd;
	float: left;
	width: 350px;
	height: 200px;
	font-size: 1.2em;
	position: fixed;
	top: 50%; left: 50%;
	z-index: 99999;
	box-shadow: 0px 0px 20px #999; /* CSS3 */
        -moz-box-shadow: 0px 0px 20px #999; /* Firefox */
        -webkit-box-shadow: 0px 0px 20px #999; /* Safari, Chrome */
	border-radius:3px 3px 3px 3px;
        -moz-border-radius: 3px; /* Firefox */
        -webkit-border-radius: 3px; /* Safari, Chrome */
}

img.btn_close { /*Position the close button*/
	float: right; 
	margin: -28px -28px 0 0;
}
</style>

<marquee onmouseover='this.stop();' onmouseout='this.start();' height="220"  direction="up"  scrollamount="2">


#foreach ( $fail in $list_memo_aktif )


<div id="marquee_replacement_td" valign="top" style="width: auto;" >

<span>$fail.mesej</span>
#if($fail.submesej != "")
#set($memo-box = "memo-box"+$fail.BIL)
<span style="display:none" >

</span>

<div id="$memo-box" class="memo-popup">
<a href="#" class="close"><img src="../img/online/close-icon.png" class="btn_close" title="Close Window" alt="Close" /></a>
$fail.submesej
</div>


<a href="#$memo-box" class="memo-window">
<font color="blue"  >
<u>Selanjutnya...</u>
</font>
</a>
<br>
<br>
#end
</div>
<br>
#end



</marquee>









<script>

$jquery(document).ready(function() {
	$jquery('a.memo-window').click(function() {
		
                //Getting the variable's value from a link 
		var loginBox = $(this).attr('href');
		//myDiv_label = document.getElementById(memo-box);
		
		//Fade in the Popup
		$jquery(loginBox).fadeIn(300);
		
		//Set the center alignment padding + border see css style
		var popMargTop = ($jquery(loginBox).height() + 24) / 2; 
		var popMargLeft = ($jquery(loginBox).width() + 24) / 2; 
		
		$jquery(loginBox).css({ 
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});
		
		// Add the mask to body
		//$jquery('body').append('<html><title>Paparan Selanjutnya</title>');
		$jquery('body').append('<div id="mask"></div>');
		//$jquery('body').append(myDiv_label.innerHTML);
		//$jquery('body').append('</div>');
		//$jquery('body').append('</html>');
		$jquery('#mask').fadeIn(300);
		
		return false;
	});
	
	// When clicking on the button close or the mask layer the popup closed
	$jquery('a.close, #mask').live('click', function() { 
	  $jquery('#mask , .memo-popup').fadeOut(300 , function() {
		$jquery('#mask').remove();  
	}); 
	return false;
	});
});

function open_new_window(div_memo) 
{
 var width  = 450;
 var height = 300;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 
 myDiv_label = document.getElementById(div_memo);
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();

new_window.document.write("<html><title>Paparan Selanjutnya</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
new_window.document.write(myDiv_label.innerHTML);
new_window.document.write("</body></html>");
new_window.document.close(); 







}


function close_window() 
{
new_window.close();
}

</script>