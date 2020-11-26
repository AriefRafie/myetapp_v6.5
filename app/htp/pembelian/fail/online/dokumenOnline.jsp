<style type="text/css">
<!--
.r {
	color: #00C;
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
		<td>

<fieldset>
<legend><strong>SENARAI DOKUMEN</strong></legend>
<table width="100%">
	#set($portal_role = "${session.getAttribute('myrole')}")
	
	#if ($portal_role!='online_kjpagensi')
	
		<TR>
			<TD colspan="4">
				 <input type="button" class="stylobutton100" name="cmdtambah" value="Tambah" onclick="tambahLampiran()">
            </TD>
        </TR>
     #end
<tr>
	<tr class="table_header">
		<td scope="col" width="3%">Bil.</td>
		<td scope="col" width="27%">Nama Dokumen</td>
		<td scope="col" width="40%">Keterangan</td>
		<td scope="col" width="25%">Muat Turun</td>
		 <td scope="col" width="5%"></td> <!---->
	</tr>
	
	#set ( $cnt = 0 )
    #foreach($mo in $senaraidokumen)
    #set ( $cnt = $cnt + 1 )
    #if ($senarai.bil == '')
    #set( $row = "row1" )
    #elseif (($senarai.bil % 2) != 0)
    #set( $row = "row1" )
    #else 
    #set( $row = "row2" )
    #end
		<tr>
		  <td class="$row"><a href="javascript:XdeleteDetailImej($mo.idDokumen,$mo.idLampiran)">$cnt.</a></td>
		  <td class="$row"><a href="javascript:viewDetailImej($mo.idLampiran)" class="stylelabel">$!mo.perihal</a></td>
		  <td class="$row">$!mo.keterangan</td>
		  <td class="$row"><a href="javascript:cetakImej($mo.idLampiran)" class="stylelabel">$!mo.namaFail</a></td>
 		  <td class="$row">
 		  	<input type="button" name="cmdHapusDoc" value ="Hapus" onClick="deleteDetailImej($mo.idDokumen,$mo.idLampiran)">
 		  </td>
    </tr>
     #end


</table>
</fieldset>
 		  </td>
    </tr>
   	 	<tr>
    
		    	<td >

        	<table width="100%" border="0">
          		<tr>
          			<td width="8%"><font color=red style=font-size:10px>Perhatian :</font></td>
          			<td width="92%">$!perhatian7</td>
          		</tr>
				<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian8</td>
        		</tr>
        		<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian9</td>
        		</tr>        		
			</table>    	
		    	</td>
		    
	 </tr>   
</table>
<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>