
<style type="text/css">
#marquee_replacement_td {
    border-bottom: 1px solid #C7A317;
    overflow: auto;
    width: 100%;
	margin-bottom: 10px;
	
}
</style>

<marquee onmouseover='this.stop();' onmouseout='this.start();' height="280"  direction="up"  scrollamount="2">


#foreach ( $fail in $list_memo_aktif )


<div id="marquee_replacement_td" valign="top" style="width: auto;" >

<span>$fail.mesej</span>
#if($fail.submesej != "")
#set($div_memo = "div_memo"+$fail.BIL)
<span style="display:none" >
<span id="$div_memo">
$fail.submesej
</span>
</span>

<a href="javascript:open_new_window('$!div_memo')">
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