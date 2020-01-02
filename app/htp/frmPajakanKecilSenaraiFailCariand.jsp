  #set ( $nofail = "" )			
  #set ( $namafail = "" )
  #if($noFail != "")
  	  #set ( $nofail = $noFail)			
  #end

  #if($namaFail != "")
	#set ( $namafail = $namaFail)			
  
  #end
  
  
<fieldset>
  <legend>MAKLUMAT CARIAN</legend>
  <table border="0" cellpadding="2" cellspacing="2" width="100%">
  	<tbody>
				  <table width="100%" border="0">
				<tr >			  
					<td colspan="2" align="center">&nbsp;</td>
				</tr>
				  <tr >
					<td align="right" width="40%">No Fail&nbsp;&nbsp;:&nbsp;&nbsp;</td>
					<td><input type=text  size="43" name=nofail value="$nofail"></td>
				  </tr>
				  <tr >
					<td align="right" width="40%">Nama Fail&nbsp;&nbsp;:&nbsp;&nbsp;</td>
					<td><input name="namafail" type="text" size="43" value="$namafail" maxlength="300"  onkeyup="this.value=this.value.toUpperCase();"></td>
				  </tr>
				  <tr >
					  <td align="right" width="40%">Negeri&nbsp;&nbsp;:&nbsp;&nbsp;</td>
					  <td>$socNegeri</td>
				  </tr>
				<tr >
				  
					<td  align="right" width="40%">Status&nbsp;&nbsp;:&nbsp;&nbsp;</td>
					<td >$socStatus&nbsp;</td>
				</tr>
				<!----><tr >			  
					<td colspan="2" align="center">&nbsp;</td>
				</tr> 
				<tr>
					<td colspan="2" width="100%" align="center" valign="top"> 
						<input class="stylobutton" type=button value="Cari" onClick="javascript:carian();">
						<input class="stylobutton" type=button value = "Kosongkan" onClick="javascript:cancel();">
					</td>     		

				</tr>				  
			
				  </table>

    	</tbody>
  </table>
</fieldset>

    <input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >

<script>
	
function cancel() {
	document.${formName}.reset();
}

function carian() {
	document.${formName}.command.value = "pksenaraifailcari";
	document.${formName}.langkah.value = '0->0';
	document.${formName}.action = "";
	document.${formName}.submit();
}

function senaraiPermohonan(id) {
	document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.fail.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function tambahPermohonan() {
	document.${formName}.command.value = "pkfailbaru";
	document.${formName}.langkah.value = '0->1';
	document.${formName}.pagemode.value = "0";
	document.${formName}.action = "";
	document.${formName}.submit();
	
}
</script>
