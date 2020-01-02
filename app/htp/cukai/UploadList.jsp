<style type="text/css">
<!--
.ERROR {
	color: #F00;
}
-->
</style>
<form name="list">
<fieldset>
  <table width="100%" border="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>#if($wrongEXT == 'true')<span class="ERROR">MAAF SILA PASTIKAN FORMAT FAIL &quot;.XLS&quot;</span></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</fieldset>

#else
<fieldset><legend>SENARAI FAIL YANG BERJAYA DI MUAT TURUN BAGI TAHUN $!tahun</legend>
<table width="87%" align="center">
<tr>
	<td width="100%">
		<table>
			<tr>
				<td>
					Negeri :
				</td>
				<td>
					$!negeri
				</td>
				<td>
				</td>
				<td>
				</td>		
			</tr>
			<tr>
				<td>
					Daerah :
				</td>
				<td>
					$!daerah
				</td>
				<td>
				</td>
				<td>
				</td>
			
			</tr>
		</table>
	
	</td>
</tr>
<tr>
	<td>
	<table width="100%" cellspacing="1" cellpadding="2" border="0">
		<tr class="table_header">
			<td> No. </td>
			<td>Mukim</td>
			<!--<td>Jenis Hakmilik</td>-->
			<td>No. Hakmilik</td>
			<!--<td>Jenis Lot</td>-->
			<td>No. Lot/PT</td>
			<td>Kegunaan Tanah</td>
			</tr>
		#set ( $cnt = 0 )		
		#foreach($cukai in $cukaiList)
		#set ( $cnt = $cnt + 1 )
		#set( $i = $velocityCount )
        #if ( ($i % 2) == 0 )
            #set( $row = "row2" )
        #else
            #set( $row = "row1" )
        #end
		<tr>
			<td class="$row">$cnt.</td>
			<td class="$row">$!cukai.rujMukim.namaMukim</td>
			<!--<td class="$row">$!cukai.rujJenisHakmilik.keterangan</td>-->
			<td class="$row">$!cukai.noHakmilik</td>
			<!--<td class="$row">$!cukai.rujLot.keterangan</td>-->
			<td class="$row">$!cukai.rujLot.keterangan $!cukai.noLot</td>
			<td class="$row">$!cukai.kegunaanTanah</td>
			</tr>
		
		#end
	
	
	</table>
	</td>
</tr>
<tr>
	<td align="center">
		 <input class="stylobutton100" name="Batal" type="button" value="Kembali" onclick="kembali()"/>
	</td>


</tr>
</table>
</fieldset>
#end
</form>
<script>
function kembali(){
	//document.f1.action = "?_portal_module=ekptg.view.htp.Upload&command=SenaraiFailUpload";	

	document.list.action = "?command=";	
	document.list.submit();
}

</script>