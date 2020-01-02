<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<p>
<a href="#" class="style1" onclick="semak()">Kembali</a></p>
<!--
<fieldset><legend>Jenis SMS</legend>
<table width="100%" border="0">
  <tr>
    <td width="29%"><div align="right">Jenis SMS</div></td>
    <td width="1%">:</td>
    <td><select name="socJenisSMSHistory" id="socJenisSMSHistory">
     #if($jenisSMSHistory == "0")
      <option value="0" selected>Sila Pilih</option>
      <option value="1">Pendaftaran</option>
      <option value="2">Perbicaraan</option>
      <option value="3">Perintah</option>
      #elseif ($jenisSMSHistory == "1")
      <option value="0">Sila Pilih</option>
      <option value="1" selected>Pendaftaran</option>
      <option value="2">Perbicaraan</option>
      <option value="3">Perintah</option>
      #elseif ($jenisSMSHistory == "2")
      <option value="0">Sila Pilih</option>
      <option value="1">Pendaftaran</option>
      <option value="2" selected="selected">Perbicaraan</option>
      <option value="3">Perintah</option>
      #elseif ($jenisSMSHistory == "3")
      <option value="0">Sila Pilih</option>
      <option value="1">Pendaftaran</option>
      <option value="2">Perbicaraan</option>
      <option value="3" selected>Perintah</option>
      #else
      <option value="0" selected>Sila Pilih</option>
      <option value="1">Pendaftaran</option>
      <option value="2">Perbicaraan</option>
      <option value="3">Perintah</option>
      #end
      
    
    </select></td>
  </tr>
  <tr>
    <td width="29%"><div align="right"></div></td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right"></div></td>
    <td>&nbsp;</td>
    <td><label>
      <input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="cari()" />
    </label></td>
  </tr>
</table>

</fieldset>
-->
<p>
<fieldset id="pendaftaran"><legend>Senarai Fail Excel</legend>
#parse("app/utils/record_paging.jsp") 
<table width="100%" border="0">
  <tr class="table_header">
    <td><strong>Bil.</strong></td>
    <td><strong>Nama Fail</strong></td>
    <td><strong>Muat Naik Fail</strong></td>
   
  </tr>
   #foreach ($daftar in $SenaraiHistory)
  
  #if ($daftar.bil == '') 
  	#set ($row = 'row1')
  #elseif ($daftar.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$daftar.bil.</td>
    <td class="$row">$daftar.FILENAME</td>
     #if ($daftar.bil != '') 
    <td class="$row"><a href="../mySMS/$daftar.FOLDER_NAME/$daftar.FILENAME" class="style1">Simpan fail</a></td>
    #else
    <td>&nbsp;</td>
    #end
   
  </tr>
  #end
</table>
</fieldset>
<script>
/*
	function KembaliMYSMS(){
		document.${formName}.action = "?_portal_module=ekptg.view.sms.mySMS&action_=";
		document.${formName}.submit();
		
	}
	
	function cari(){
		document.${formName}.action = "?_portal_module=ekptg.view.sms.mySMS&action_=cari";
		document.${formName}.submit();
		
	}
*/	
</script>