<html>
<head>
<title>Gadaian Hantar</title>
</head>
<body>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
			#parse ("app/htp/gadaian/online/frmKJPGadaianInfoAjax.jsp")
			</fieldset>
		</td>
    </tr>
    <tr>
    	<td> 	
    		
			
			<p>
				<input name="namaPemohon" type="hidden" id="namaPemohon" value="$namaPemohon"/>
			   	<input type="hidden" name="id_kemaskini" />
			   	<input type="hidden" name="idFail" value="$idFail"/>
			   	<input type="hidden" name="langkah" value="0" />
			   	<input type="hidden" name="idPermohonan" value="$idPermohonan" />
			   	
			    
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
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan')" />
						#elseif ($!idjawatan.equals("9") && $!statussemasa.equals("2"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan')" />
						
						#elseif ($!idjawatan.equals("4")&& $!statussemasa.equals("3"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<p><b><font color="blue" size="2"><span class="blink">
   							&nbsp&nbsp&nbsp&nbsp Ambil Perhatian: Sila pastikan maklumat diisi pada permohonan adalah TEPAT dan MUKTAMAD.
   							<br/>&nbsp&nbsp&nbsp&nbsp Permohonan yang telah dihantar TIDAK DIBENARKAN untuk dipinda/dikemaskini.
   							</p></b></span>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan')" />
		    				<input type="button" name="cmdPindaan" id="cmdPindaan" value="Kembalikan kepada penyedia" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
                		
                		#end
                		
                	<input type="button" class="stylobutton100" name="cetakakuan" id="cetakakuan" value="Cetak" onclick="javascript:cetakPengesahan('$!permohonan.idpermohonan');" />
                	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGHA_KembaliHantar()" /> 
		    		#else
                    <input type="button" class="stylobutton100" name="cetakakuan" id="cetakakuan" value="Cetak" onclick="javascript:cetakPengesahan('$!permohonan.idpermohonan');" />
               		<input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGHA_KembaliHantar()">
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
</body>
</html>