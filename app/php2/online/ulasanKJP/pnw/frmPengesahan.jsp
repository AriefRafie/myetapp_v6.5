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
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">$idKategoriPemohon</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$namaKementerian</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
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
		<input type="text" name="idFail" value="$idFail" />
		<input type="hidden" name="langkah" value="0" />
		<input type="text" name="idPermohonan" value="$idPermohonan" />
		<input type="text" name="idjawatan" value="$idjawatan" />
		<input type="text" name="statussemasa" value="$statussemasa" />
		<input name="submit" type="text" id="submit" value="$submit"/>
		<input name="actionOnline" type="text" id="actionOnline" value="$actionOnline"/>
		
		
<table width="100%" border="0" cellspacing="2" cellpadding="2">
			<td align="center" colspan="4">
			semakMode = $semakMode
			idjawatan = $!idjawatan
			statussemasa = $!statussemasa
						#if($semakMode == "update")
		    			#if(($!idjawatan.equals("20")||$!idjawatan.equals("24"))&& $!statussemasa.equals("-1")) 
		    				<p><input type="checkbox" id="checkme"/><a>&nbsp;Pastikan maklumat yang dimasukkan adalah betul.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
						#elseif ($!idjawatan.equals("9") && $!statussemasa.equals("-2"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Pastikan maklumat yang dimasukkan adalah betul.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
						
						#elseif ($!idjawatan.equals("4")&& $!statussemasa.equals("-3"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Pastikan maklumat yang dimasukkan adalah betul.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
                		
                		#end
                		
                	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step2Online($!permohonan.get(idpermohonan))" /> 
		    		#else
                    <input type="button" class="stylobutton100" name="cetakakuan" id="cetakakuan" value="Cetak" onclick="javascript:cetakPengesahan('$!permohonan.idpermohonan');" />
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

function simpanpengesahan2() {
	alert('baca idFail=='+document.${formName}.idFail.value+" idPermohonan=="+idPermohonan);
	//doAjaxCall${formName}("pkmaklumatseterus","id_kemaskini="+id);	
	//document.${formName}.pagemode.value = 0;
	document.${formName}.actionOnline.value = "simpanpengesahan2";
	//document.${formName}.semakMode.value = 'update';
	doAjaxCall${formName}("simpanpengesahan2",'idpermohonan='+idPermohonan);
	//doDivAjaxCall$formname('divMainForm','paparFail','&idFail='+ idFail +'&idUlasanTeknikal=' + idUlasanTeknikal);
}
</script>
