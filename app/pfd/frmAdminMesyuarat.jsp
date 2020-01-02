<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
<input name="idNegeri" type="hidden" value="$idNegeri" />
<input name="idUnit" type="hidden" value="$idUnit" />
<input name="action1" type="hidden" value="$action1" />
<fieldset>
  <legend><strong>DAFTAR BILIK MESYUARAT DAN BILIK FAIL</strong></legend>
  <br />
  <table width="100%"  align="center">
    <tr> 
      <td width="30%" align="right" valign="top" nowrap="nowrap"><div align="left"> Nama Bilik Mesyuarat</div></td>
      <td width="2%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="68%" align="left" valign="top" nowrap="nowrap"><input name="txtnamabilik" type="text" id="txtnamabilik" value="$!txtnamabilik" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;"  size="50" maxlength="255"  />
        <em><strong>atau</strong></em></td>
    </tr>
        <tr> 
      <td width="30%" align="right" valign="top" nowrap="nowrap"><div align="left"> Nama Bilik Fail</div></td>
      <td width="2%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="68%" align="left" valign="top" nowrap="nowrap"><input name="txtnamabilikFail" type="text" id="txtnamabilikFail" value="$!txtnamabilikFail" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;"  size="50" maxlength="255"  /></td>
    </tr>
    #if($idNegeri == '16')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Seksyen</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Ahli_Seksyen</td>
    </tr>
    #else
             <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Negeri</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Ahli_Negeri </td>
    </tr>
    #end
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="button" width="30" id="cmdCariAhli2" name="cmdCariAhli2" value="Semak" onclick="caribilik();" /><input type="button" width="30" value="Kosongkan" name="cmdReset"  id="cmdReset" onclick="resetx();" />
        
         <input type="button" width="30" id="cmdSenaraiBilikMesyuarat" name="cmdSenaraiBilikMesyuarat" value="Senarai Bilik Mesyuarat" onclick="senaraiBilikMesyuarat();" /><input type="button" width="30" id="cmdSenaraiBilikFail" name="cmdSenaraiBilikFail" value="Senarai Bilik Fail" onclick="senaraiBilikFail();" /></td>
    </tr>
  </table>
</fieldset>

#if ($modeT == 'bilik')  
 <br />
   <br /><fieldset>
   <legend><strong>DAFTAR BILIK MESYUARAT BARU</strong></legend>
 <table width="100%"  align="center">
 <tr>      
 <td width="30%" align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span>Nama Bilik </div></td>
 <td width="2%"  align="center" valign="top" nowrap="nowrap">:</td>
 <td width="68%" align="left" valign="top" nowrap="nowrap">
   <input name="txtnamabilikTBilik" type="text" id="txtnamabilikTBilik"  size="50" maxlength="255" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;"/></td>
 </tr>   
 <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left">Alamat 1</div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap"> <input name="txtAlamat1TBilik" type="text" id="txtAlamat1TBilik"  size="50" maxlength="255" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;"/></td>
 </tr>   
 <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left">Alamat 2</div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap"> <input name="txtAlamat2TBilik" type="text" id="txtAlamat2TBilik"  size="50" maxlength="255" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;"/> </td>
 </tr>   
 <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left">Alamat 3</div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap"><input name="txtAlamat3TBilik" type="text" id="txtAlamat3TBilik"  size="50" maxlength="255" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;"/></td>
 </tr>   
 <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left">Poskod</div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap"><input name="txtPoskodTBilik" type="text" id="txtPoskodTBilik"  size="50" maxlength="255" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;"/> </td>
 </tr> 
    <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left"><div align="left"><span class="mandatori">*</span>Negeri</div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap">$Ahli_NegeriTBilik  </td>
 </tr> 
 #if($idNegeri == '16') 
  <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left"><div align="left"><span class="mandatori">*</span>Seksyen </div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap">$Ahli_SeksyenTBilik </td>
 </tr> 
 #else
  <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left"><div align="left">Unit </div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap">$Ahli_UnitTBilik </td>
 </tr>  
  #end
   <tr>      
 <td align="right" valign="top" nowrap="nowrap"> </td>
 <td align="center" valign="top" nowrap="nowrap"> </td>
 <td align="left" valign="top" nowrap="nowrap"><input type="button" width="30" id="cmdSimpanBilik" name="cmdSimpanBilik" value="Simpan Bilik" onclick="SimpanBilik();" /></td> 
 </table>
 </fieldset>
   <p>#end</p>
   <p> 
   
 #if ($modeT == 'lokasifail') <br />
<fieldset>
   <legend><strong>DAFTAR BILIK FAIL BARU</strong></legend> 
<table width="100%"  align="center">
 <tr>      
 <td width="30%" align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span>Nama Bilik </div></td>
 <td width="2%"  align="center" valign="top" nowrap="nowrap">:</td>
 <td width="68%" align="left" valign="top" nowrap="nowrap">
   <input name="txtnamabilikTBilikFail" type="text" id="txtnamabilikTBilikFail"  onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" size="70" maxlength="255" /></td>
 </tr> 
    <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left"><div align="left"><span class="mandatori">*</span>Negeri</div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap"> $Ahli_NegeriTBilik  </td>
 </tr> 
 #if($idNegeri == '16') 
  <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left"><div align="left"><span class="mandatori">*</span>Seksyen </div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap">$Ahli_SeksyenTBilik </td>
 </tr> 
 #else
  <tr>      
 <td align="right" valign="top" nowrap="nowrap"><div align="left"><div align="left">Unit </div></td>
 <td align="center" valign="top" nowrap="nowrap">:</td>
 <td align="left" valign="top" nowrap="nowrap">$Ahli_UnitTBilik</td>
 </tr>  
  #end
   <tr>      
 <td align="right" valign="top" nowrap="nowrap"> </td>
 <td align="center" valign="top" nowrap="nowrap"> </td>
 <td align="left" valign="top" nowrap="nowrap"><input type="button" width="30" id="cmdSimpanBilik" name="cmdSimpanBilik" value="Simpan Bilik" onclick="SimpanBilikFail();" /></td> 
</table>
</fieldset>
 #end
 
 #if ($mode == 'lokasifail')  
  
   #if ($idNegeri == '16') </p>
   <fieldset>
    <legend><strong>SENARAI BILIK FAIL</strong></legend>
    <input type="button" width="30" id="cmdTambahBilikFail" name="cmdTambahBilikFail" value="Tambah Bilik Fail" onclick="tambahBilikFail();" />
 <table width="100%"  align="center">
<tr class="table_header">
<td>Nama Bilik</td>
<td>Negeri</td>
<td>Seksyen</td>
<td></td>
</tr>
  #set ($listBilik = '')
    #foreach ($listBilik in $ListMesyuaratBilikFail)
<tr>
<td>$!listBilik.lokasi</td>
<td>$!listBilik.nama_negeri</td>
<td>$!listBilik.nama_seksyen</td>
<td align="center"><input type="button" width="30" id="cmdHapusBilik" name="cmdHapusBilik" value="Hapus" onclick="HapusBilikFail('$!listBilik.id_lokasi');" /></td>
</tr>
	#end
</table>   
</fieldset>
	#else
       <fieldset>
    <legend><strong>SENARAI BILIK FAIL</strong></legend>
    <input type="button" width="30" id="cmdTambahBilikFail" name="cmdTambahBilikFail" value="Tambah Bilik Fail" onclick="tambahBilikFail();" />
<table width="100%"  align="center">
<tr class="table_header">
<td>Nama Bilik</td>
<td>Negeri</td>
<td>Unit</td><td></td></tr>
  #set ($listBilik = '')
    #foreach ($listBilik in $ListMesyuaratBilikFail)
<tr>
<td>$!listBilik.lokasi</td>
<td>$!listBilik.nama_negeri</td>
<td>$!listBilik.nama_unit</td>
<td align="center"><input type="button" width="30" id="cmdHapusBilik" name="cmdHapusBilik" value="Hapus" onclick="HapusBilikFail('$!listBilik.id_lokasi');" /></td>
</tr>
	#end
</table>
</fieldset>
	#end
#end
<br>
#if ($mode == 'bilik')  
   #if ($idNegeri == '16') </p>
    <fieldset>
    <legend><strong>SENARAI BILIK MESYUARAT</strong></legend>
    <input type="button" width="30" id="cmdTambahBilik" name="cmdTambahBilik" value="Tambah Bilik Mesyuarat" onclick="tambahBilikMesyuarat();" />
 <table width="100%"  align="center">
<tr class="table_header"><td>Nama Bilik</td><td>Alamat 1</td><td>Alamat 2</td><td>Alamat 3</td><td>Poskod</td><td>Negeri</td><td>Seksyen</td><td></td></tr>
  #set ($listBilik = '')
    #foreach ($listBilik in $ListMesyuaratBilik)
<tr>
<td>$!listBilik.lokasi</td>
<td>$!listBilik.alamat1</td>
<td>$!listBilik.alamat2</td>
<td>$!listBilik.alamat2</td>
<td>$!listBilik.poskod</td>
<td>$!listBilik.nama_negeri</td>
<td>$!listBilik.nama_seksyen</td>
<td align="center"><input type="button" width="30" id="cmdHapusBilik" name="cmdHapusBilik" value="Hapus" onclick="HapusBilik('$!listBilik.id_lokasi');" /></td>
</tr>
	#end
</table>
</fieldset>
	#else
     <fieldset>
    <legend><strong>SENARAI BILIK MESYUARAT</strong></legend>
    <input type="button" width="30" id="cmdTambahBilik" name="cmdTambahBilik" value="Tambah Bilik Mesyuarat" onclick="tambahBilikMesyuarat();" />
<table width="100%"  align="center">
<tr class="table_header"><td>Nama Bilik</td><td>Alamat 1</td><td>Alamat 2</td><td>Alamat 3</td><td>Poskod</td><td>Negeri</td><td>Unit</td><td></td></tr>
  #set ($listBilik = '')
    #foreach ($listBilik in $ListMesyuaratBilik)
<tr>
<td>$!listBilik.lokasi</td>
<td>$!listBilik.alamat1</td>
<td>$!listBilik.alamat2</td>
<td>$!listBilik.alamat2</td>
<td>$!listBilik.poskod</td>
<td>$!listBilik.nama_negeri</td>
<td>$!listBilik.nama_unit</td>
<td align="center"><input type="button" width="30" id="cmdHapusBilik" name="cmdHapusBilik" value="Hapus" onclick="HapusBilik('$!listBilik.id_lokasi');" /></td>
</tr>
	#end
</table>
</fieldset>
	#end
#end

<script type="text/javascript">

   function caribilik(){
   
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=caribilik";
	  document.${formName}.submit();   
   }
   
      function senaraiBilikMesyuarat(){
   
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=senaraiBilikMesyuarat";
	  document.${formName}.submit();   
   }
   
      function senaraiBilikFail(){
   
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=senaraiBilikFail";
	  document.${formName}.submit();   
   }
   
     function resetx() {
  	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=resetx";
	  document.${formName}.submit();  
  }
  function tambahAhli() {
  	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=tambahahli";
	  document.${formName}.submit();  
  }
  
  function tambahBilikFail() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=tambahbilikFail";
	  document.${formName}.submit();  
  }
  
    function tambahBilikMesyuarat() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=tambahbilikMesyuarat";
	  document.${formName}.submit();  
  }
  
    function SimpanAhli() {
		 
		  if (document.${formName}.txtnamaahliTAhli.value == '' ) {
		  alert('Sila masukkan Nama Pegawai.');
		  document.${formName}.txtnamaahliTAhli.focus();
		  return;
	  	  }	
		  if (document.${formName}.SOC_JAWATAN.value == '' ) {
		  alert('Sila Pilih Jawatan.');
		  document.${formName}.SOC_JAWATAN.focus();
		  return;
	  	  }	
	   	  if (document.${formName}.SOC_SEKSYEN_AHLI.value == '' ) {
		  alert('Sila Pilih Seksyen.');
		  document.${formName}.SOC_SEKSYEN_AHLI.focus();
		  return;
	  	  }	
	   	  if (document.${formName}.SOC_NEGERI_AHLI.value == '' ) {
		  alert('Sila Pilih Negeri.');
		  document.${formName}.SOC_NEGERI_AHLI.focus();
		  return;
	  	  }	
	
	if (!window.confirm("Data disimpan didalam sistem, Adakah anda pasti?")) return;
  	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=simpanahli";
	  document.${formName}.submit();  
  }

    function HapusBilik(id){
		  if (!window.confirm("Data akan dipadam sepenuhnya didalam sistem, Adakah anda pasti?")) return;
  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=removebilik&idBilikMesyurat="+id+"";
	  document.${formName}.submit();  
  }
      function HapusBilikFail(id){
		  if (!window.confirm("Data akan dipadam sepenuhnya didalam sistem, Adakah anda pasti?")) return;
  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=removebilikFail&idBilikFail="+id+"";
	  document.${formName}.submit();  
  }

  
    function SimpanBilik() {
	var txtPoskod = document.${formName}.txtPoskodTBilik.value;
	var len = txtPoskod.length;

	   	  if (document.${formName}.txtnamabilikTBilik.value == '' ) {
		  alert('Sila masukkan Nama Bilik.');
		  document.${formName}.txtnamabilikTBilik.focus();
		  return;
	  	  }
	   	  if (document.${formName}.SOC_NEGERI_BILIK.value == '' ) {
		  alert('Sila Pilih Negeri.');
		  document.${formName}.SOC_NEGERI_BILIK.focus();
		  return;
	  	  }
		  if (len > 5 ) {
		  alert('Poskod Melebihi Aksara.');
		  document.${formName}.txtPoskodTBilik.focus();
		  return;
	  	  }
	  	if (!window.confirm("Data disimpan didalam sistem, Adakah anda pasti?")) return;
  	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=simpanbilikMesyuarat";
	  document.${formName}.submit();  
  }
  
   function SimpanBilikFail() {
	
	   	  if (document.${formName}.txtnamabilikTBilikFail.value == '' ) 
		  {
		  alert('Sila masukkan Nama Bilik.');
		  document.${formName}.txtnamabilikTBilikFail.focus();
		  return;
	  	  }
	   	  if (document.${formName}.SOC_NEGERI_BILIK.value == '' ) 
		  {
		  alert('Sila Pilih Negeri.');
		  document.${formName}.SOC_NEGERI_BILIK.focus();
		  return;
	  	  }
	  
	  if (!window.confirm("Data disimpan didalam sistem, Adakah anda pasti?")) return;
  	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmAdminMesyuarat&action=simpanbilikFail";
	  document.${formName}.submit();  
  }
 
</script>