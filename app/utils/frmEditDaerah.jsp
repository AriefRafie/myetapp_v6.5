#if ($result == "success")
$id_negeri
<div class=info>Kemaskini Berjaya</div>
#else
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Daerah</legend>
<table border="0" cellpadding="2" cellspacing="2">
       <tr>
             <td >Negeri</td>
             <td>             
	     $!selectNegeri
             </td>
    	</tr>
  
        </tr>
	   <td >Kod Daerah</td>
	   <td><input size=3 maxlength=2 name="Form_kod_daerah" type=text value="$!details.kod_daerah"></td>
        <tr>
	<tr>
	   <td >Nama Daerah</td>
	   <td><input size=45 maxlength=80 name="Form_nama_daerah" type=text value="$!details.nama_daerah"></td>
        <tr>

</table>
</fieldset>
</table>
#end
#if ($mode == "edit")
	<input type=hidden name=idDaerah value="$!idDaerah">
	<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
#else
	<input type=button value=Tambah onClick="tambah();">
#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

