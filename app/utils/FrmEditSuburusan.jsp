#if ($result == "success")

<div class=info>Kemaskini Berjaya</div>
#else
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Suburusan</legend>
<table border="0" cellpadding="2" cellspacing="2">
       <tr>
             <td >Seksyen</td>
             <td>             
	     $!selectSeksyen
             </td>
    	</tr>
       <tr>
             <td >Urusan</td>
             <td>             
	     $!selectUrusan
             </td>
    	</tr>
  
        </tr>
	   <td >Kod Suburusan</td>
	   <td><input size=6 maxlength=6 name="Form_kod_suburusan" type=text value="$!details.kod_suburusan"></td>
        <tr>
	<tr>
	   <td >Nama Suburusan</td>
	   <td><input size=45 maxlength=80 name="Form_nama_suburusan" type=text value="$!details.nama_suburusan"></td>
        <tr>

</table>
</fieldset>
</table>
#end
#if ($mode == "edit")
	<input type=hidden name=idSuburusan value="$!idSuburusan">
	<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
#else
	<input type=button value=Tambah onClick="tambah();">
#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

