
<fieldset>
 <legend>SENARAI NOTIS 5A</legend>
 <table width="100%" cellspacing="2" cellpadding="1" border="0">
  <tr >
    <td colspan="4">

    <input class="stylobutton" type="button" name="TambahNotis5A" id="TambahNotis5A" value="Tambah" onclick="TambahNotis()" style="display:none"></td>
    
    </tr>
  <tr class="table_header">
  	<td width="7%">Bil.</td>
  	<td width="24%">Tarikh Pengeluaran Notis 5A</td>
  	<td width="66%">Tarikh Luput</td>
  	<td width="3%">&nbsp;</td>
  </tr>
				#set ( $cnt = 0 )			
				#foreach ( $fd in $SenaraiNotis5A )
				#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
                <input type="hidden" name="senarai_id" value="$senarai.id">
  <tr>
  <td class="$row">$cnt.</td>
  <td class="$row">
  	<a href="javascript:Notis5AView('$fd.idnotis5a')" class="style1">
	$fd.tarikhnotis5a
	</a>
  </td>
  <td class="$row">$fd.tarikhluput1</td>
  <td class="$row">&nbsp;</td>
  </tr>    <input type="hidden" name="tarikh_luput" id="tarikh_luput" value="$fd.tarikhluput1" $selectstyle $inputstyleread/>
#end
#if ($cnt == 0)
	<tr> 
		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
        <td colspan="3" scope="row"></td>
	</tr> 
#end
	<input type="hidden" value="$senarai.id" name="id_senarai">

    </table>
</fieldset>
