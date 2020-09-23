<style type="text/css">
	<!--
	.pautanms {color: #0000FF}
	-->
</style> 
<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
    	<td> 
			<br/>
			  #parse('app/htp/frmPajakanKecilPaging.jsp')
			<br/>
			#parse ("app/htp/frmJRPInfo.jsp")			
			  
			  #set ($pagemode = $pageMode)	
			  #set ($cbFlag = '')	
			  #if($pagemode == "2" )
			  #set ($cbFlag = 'disabled')	
			  #end 

		<td>		
	</tr>
	
	<tr>
    	<td> 	
    		
			
			<p>
			   	<input type="hidden" name="id_kemaskini" />
			   	<input type="hidden" name="fail" value="$fail"/>
			   	<input type="hidden" name="langkah" value="0" />
			   	<input type="hidden" name="idpermohonan" value="$idpermohonan" />
			   	<input name="namaPemohon" type="hidden" id="namaPemohon" value="$namaPemohon"/>
			    
			 </p>
			
	
		<td>		
	</tr>

	
		
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
			<td align="center" colspan="4">
						#if($semakMode == "update")
		    			#if(($!idjawatan.equals("20")||$!idjawatan.equals("24"))&& $!statussemasa.equals("1")) 
		    				<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
						#elseif ($!idjawatan.equals("9") && $!statussemasa.equals("2"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
						
						#elseif ($!idjawatan.equals("4")&& $!statussemasa.equals("3"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
                		
                		#end
                		
                	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step2Online($!permohonan.get(idpermohonan))" /> 
		    		#else
                    <!-- <input type="button" class="stylobutton100" name="cetakakuan" id="cetakakuan" value="Cetak" onclick="javascript:cetakPengesahan('$!permohonan.idpermohonan');" /> -->
               		<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step2Online($!permohonan.idpermohonan)" />
		    		#end
		    </td>
</table>
<script>
var checker = document.getElementById('checkme');
var sendbtn = document.getElementById('cmdSimpan');
// when unchecked or checked, run the function
checker.onchange = function(){
sendbtn.disabled = true;

if(this.checked){
   sendbtn.disabled = false;
} else {
   sendbtn.disabled = true;
}

}
</script>