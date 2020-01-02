<a href="javascript:gotoTukarPegawai()" class="help" title="Permohonan Tukar Pegawai Bicara">
							<font color="blue"><li>
                            #if($!TotalTukarPegawai > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE" class="blink"><blink>$!TotalTukarPegawai</blink></font></b>
                             </label>&nbsp;
                             #end
                            
                            Ganti Pegawai Bicara</li></font>						
				  </a>
<script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('Carta_Main','showMAIN_stats','');	
	doDivAjaxCall$formname('showCountBicaraOnline','showCountBicaraOnline','');	
		
});



</script>