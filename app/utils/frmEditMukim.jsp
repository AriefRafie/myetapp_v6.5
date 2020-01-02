<div id = "frmEditMukim" name="frmEditMukim">
#if ($result == "success")
<div class=info>Kemaskini Berjaya</div>
#end
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Mukim</legend>
<table border="0" cellpadding="2" cellspacing="2">
       <tr>
             <td >Negeri</td>
             <td>             
	     $!selectNegeri
             </td>
    	</tr>
   
       <tr>
           <td >Daerah</td>
           <td>             
		$!selectDaerah
           </td>
           


        </tr>
 	   <td >Kod Mukim</td>
 	   <td><input size=3 maxlength=2 name="Form_kod_mukim" type=text value="$!details.kod_mukim"></td>
         <tr>
 	<tr>
 	   <td >Nama Mukim</td>
 	   <td><input size=45 maxlength=80 name="Form_nama_mukim" type=text value="$!details.nama_mukim"></td>
        <tr>
</table>
</fieldset>
</table>
	#if ($mode == "edit")
		<input type=hidden name=idMukim value="$!idMukim">
		<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
	#else
		<input type=button value=Tambah onClick="tambah();">
	#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

##parse("vtl/ajax/tool_button.vm")

</div>

