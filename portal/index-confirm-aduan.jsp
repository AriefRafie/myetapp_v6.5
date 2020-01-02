<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td colspan="2"></td>
	</tr>
    <tr>
      <td colspan="2" class="success"><p><span>Terima kasih di atas aduan anda. 
        </span></p>
        <p><span>Sila simpan nombor rujukan aduan di bawah untuk semakan anda.</span></p>
        <p><span>No. Rujukan Aduan : <strong>$!complaint.id</strong> </span></p>
        <p><span>Pihak kami akan memproses aduan/cadangan anda secepat mungkin.</span>           
            </p></td>
    </tr>
    <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"><input type="button" name="cmdAduanBaru" id="cmdAduanBaru" value="Tambah Aduan" onclick="javascript:aduanBaru()" /> 
    </td>
  </tr>

</table>
<script type="text/javascript">
	aduanBaru =function(){
		
			doAjaxCall${formName}("doAduanBaru");
		
	}


</script>
