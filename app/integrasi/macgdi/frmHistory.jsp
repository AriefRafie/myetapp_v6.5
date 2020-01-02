<script type="text/javascript" src="../../../library/js/report.js" ></script>
<link rel="stylesheet" type="text/css" href="../../css/Integrasi.css" />
<strong><center></center></strong>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="hitButton">
  <input type="hidden" name="id" value="$!id"/>

</p>
<fieldset>
<legend>
<strong>Senarai History</strong>    </legend>
<table width="100%" cellspacing="2" cellpadding="1" border="1">
			    <tr class="table_header">
				  	<td width="10%" align="center">No.</td>
				  	<td width="80%"align="left">Nama Pengguna MACGDI</td>	
				  	<td width="80%"align="center">Tarikh Cetak</td>
				  </tr>
				  #if($SenaraiFail.size() > 0)
					#foreach ($result in $SenaraiFail )
					#set( $counter = $velocityCount )
					#if ( ($counter % 2) == 0 )
						#set( $row = "row2" )
					#else
						#set( $row = "row1" )
                  #end
				<tr>
					  <td class="$row" align="center">$result.No</td>
                      <td class="$row" align="left">$result.NamaPengguna</td>
                      <td class="$row" align="center">$result.tarikhCetak</td>
              </tr>
              #end
				#else
					<tr>
						<td colspan="6">Rekod Tidak Dijumpai</td>
					</tr>
				#end
			</table>
            <table width="100%" border="0">
	<tbody>
    <br />
		<tr align="center">
			<th scope="col">
				<input type="button" id="cmdBatal" name="cmdBatal" value="Kembali" onclick="javascript:kembali()"/>
			</th>
		</tr>
	</tbody>

</table>
<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
	
</fieldset>

<script type="text/javascript">

function kembali(){
window.close();
	
}


</script>