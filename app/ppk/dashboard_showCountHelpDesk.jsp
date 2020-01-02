<a href="javascript:gotoHelpDesk()" class="help" title="Help Desk">
							<font color="blue"><li>
                            #if($!totalHelpDesk > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE" class="blink"><blink>$!totalHelpDesk</blink></font></b>
                             </label>&nbsp;
                             #end
                            
                            Help Desk</li></font>						
				  </a>
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('Carta_Main','showMAIN_stats','');	
		
});
</script>




