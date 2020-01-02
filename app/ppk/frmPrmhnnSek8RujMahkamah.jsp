<html>
<head>
  <title>frmPrmhnnSek8RujMahkamah</title>
</head>
<body>
<form name="f1">
#set ($namapejabat = "")
#set ($alamat1pejabat = "")
#set ($alamat2pejabat = "")
#set ($alamat3pejabat = "")
#set ($poskodpejabat = "")
#set ($notel = "")
#set ($nofax = "")
#if ($idFlag == "1")
	#foreach ($list in $infoMahkamah)
		#set ($namapejabat = $list.namapejabat)
		#set ($alamat1pejabat = $list.alamat1pejabat)
		#set ($alamat2pejabat = $list.alamat2pejabat)
		#set ($alamat3pejabat = $list.alamat3pejabat)
		#set ($poskodpejabat = $list.poskodpejabat)
		#set ($notel = $list.notel)
		#set ($nofax = $list.nofax)
	#end
#end
<fieldset><legend>MAKLUMAT MAHKAMAH</legend>
<table width="100%" border="0">
  <tbody>
    <tr>
      <td width="30%">Negeri</td>
      <td width="70%"><select name="socNegeri" onChange="getDaerah()">
      
      #set ($id = "")
      #set ($namaNegeri = "")
      #set ($selected = "")
      	  #foreach ($list in $ListNegeri) 
	      #set ($id = $list.idNegeri)
	      #set ($namaNegeri = $list.namaNegeri)
		      #if ($id == $SocNegeri)
		      #set ($selected = "selected")
	      		<option value="$id" $selected>$namaNegeri</option>
		  	  #end
	  	  #end
	  	  <option value="0">Sila Pilih</option>
	  	  #foreach ($list in $ListNegeri)
	  	  #set ($id = $list.idNegeri)
	      #set ($namaNegeri = $list.namaNegeri) 
	  	  	<option value="$id">$namaNegeri</option>
	      #end
      </select></td>
    </tr>
    <tr>
      <td>Daerah</td>
      <td><select name="socDaerah" onChange="getAddress()">
      #set ($idDaerah = "")
      #set ($namaDaerah = "")
      #foreach ($listDaerah in $ListDaerah) 
	      #set ($idDaerah = $listDaerah.idDaerah)
	      #set ($namaDaerah = $listDaerah.namaDaerah)
      		#if ($idDaerah == $SocDaerah)
		      #set ($selected = "selected")
      		<option value="$idDaerah" $selected>$namaDaerah</option>
      		#end
	  #end
	  <option value="0">Sila Pilih</option>
	  #foreach ($listDaerah in $ListDaerah) 
	      #set ($idDaerah = $listDaerah.idDaerah)
	      #set ($namaDaerah = $listDaerah.namaDaerah)
      <option value="$idDaerah" >$namaDaerah</option>
	  #end
      </select></td>
    </tr>
    <tr>
      <td>Mahkamah</td>
      <td><input type="text" name="txtNamaMahkamah" value="$namapejabat" size="50" maxlength="50" disabled>
      </td>
    </tr>
    <tr>
      <td>Alamat</td>
      <td><input type="text" name="txtAlamat1" maxlength="50" size="50" value="$alamat1pejabat" disabled>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><input type="text" name="txtAlamat2" maxlength="50" size="50" value="$alamat2pejabat" disabled>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><input type="text" name="txtAlamat3" maxlength="50" size="50" value="$alamat3pejabat" disabled>
      </td>
    </tr>
    <tr>
      <td>Poskod</td>
      <td><input type="text" name="txtPoskod" maxlength="5" size="5" value="$poskodpejabat" disabled>
      </td>
    </tr>
    <tr>
      <td>No Telefon</td>
      <td><input type="text" name="txtTelefon" maxlength="50" size="15" value="$notel" disabled>
      </td>
    </tr>
    <tr>
      <td>No Fax</td>
      <td><input type="text" name="txtfax" maxlength="50" size="15" value="$nofax" disabled>
      </td>
    </tr>
        <tr>
      <td></td>
      <td><input type="button" name="cmdSimpan" value="Simpan" onClick="Simpan()"><input type="button" name="cmdKembali" value="Kembali" onClick="javascript:window.close();"></td>
    </tr>
  </tbody>
</table>
</fieldset>
<input type="hidden" name="command">
<input type="hidden" name="idPermohonan" value="$idPermohonan">
</form> 
<script>
function Kembali(){
	window.close();
}
function Simpan(){
	if (document.f1.socNegeri.value==""){
	}else if (document.f1.socDaerah.value==""){
	}else{
	document.f1.command.value="getSimpanMahkamah";
	document.f1.action="";
	document.f1.submit();
	alert("Maklumat telah disimpan");
	}
}
function getDaerah(){
	document.f1.command.value="getMahkamah";
	document.f1.action="";
	document.f1.submit();	
}
function getAddress(){
	document.f1.command.value="getMahkamahAddress";
	document.f1.action="";
	document.f1.submit();	
}
</script>
</body>
</html>
