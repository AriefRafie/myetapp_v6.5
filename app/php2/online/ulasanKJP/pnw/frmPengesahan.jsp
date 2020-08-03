<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanHeader in $BeanHeader)
    #set($idFail = $beanHeader.idFail)
    #set($subUrusan = $beanHeader.subUrusan)
    #set($idPermohonan = $beanHeader.idPermohonan)
    #set($noPermohonan = $beanHeader.noPermohonan)
    #set($tarikhTerima = $beanHeader.tarikhTerima)
    #set($idStatus = $beanHeader.idStatus) 
    #set($status = $beanHeader.status)    
    #set($idHakmilik = $beanHeader.idHakmilik)
    #set($noFail = $beanHeader.noFail) 
    #set($flagLayerKJP = $beanHeader.flagLayerKJP)           
    #end

		<td width="50%" valign="top" ><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="36%" align="right">No Rujukan <em>Online</em></td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noPermohonan</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">No Fail </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noFail</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Suburusan </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$subUrusan</font></td>
        </tr>
        <tr>
          <td align="right">Tarikh Permohonan </td>
          <td>:</td>
          <td><font color="blue">$tarikhTerima</font></td>
        </tr>
        <tr>
          <td align="right">Status Permohonan </td>
          <td>:</td>
          <td><font color="green">$keterangan</font></td>
        </tr>
        <tr>
          <td align="right">Perkara </td>
          <td>:</td>
          <td><font color="blue">$perkara</font></td>
        </tr>
       </table>
      </fieldset></td>
      	  <td><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"></td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">$idKategoriPemohon</td>
        </tr>
        <tr>
          <td></td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$namaKementerian</td>
        </tr>
        <tr>
          <td></td>
          <td>Agensi</td>
          <td>:</td>
          <td>$namaAgensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$namaAgensi
            
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$negeri</td>
        </tr>
        
      </table>
      </fieldset>
      </td>
  </tr>
</table>
		<input type="hidden" name="id_kemaskini" />
		<input type="hidden" name="idFail" value="$idFail" />
		<input type="hidden" name="langkah" value="0" />
		<input type="hidden" name="idPermohonan" value="$idPermohonan" />
		<input type="hidden" name="idjawatan" value="$idjawatan" />
		<input type="hidden" name="statussemasa" value="$statussemasa" />
		<input type="hidden" name="namaPemohon" value=$namaPemohon />
		
		<input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
		
		
<table width="100%" border="0" cellspacing="2" cellpadding="2">
			
      		<tr>
			<td align="center" colspan="4">
						#if($semakMode == "update")
		    			#if(($!idjawatan.equals("20")||$!idjawatan.equals("24"))&& $!statussemasa.equals("1")) 
		    				<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
						#elseif ($!idjawatan.equals("9") && $!statussemasa.equals("2"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
						
						#elseif ($!idjawatan.equals("4")&& $!statussemasa.equals("3"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
                		
                		#end
                		
                	<input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                	
		    		#else
		    		<input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/>
           			<input type="button" name="cdmCetak" id="cdmCetakPengesahan" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"/>
               		<input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
		    		#end
		    </td>
		    </tr>
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
