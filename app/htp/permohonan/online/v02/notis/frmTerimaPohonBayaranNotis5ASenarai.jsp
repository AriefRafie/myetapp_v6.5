
<style type="text/css">
</style>
   	#if($!idNegeriNotis=="13")
	<fieldset><legend>SENARAI BAYARAN L & S 80</legend>
	#elseif($!idNegeriNotis=="12")
	<fieldset><legend>SENARAI SURAT TAWARAN KELULUSAN</legend>
	#else
	<fieldset><legend>SENARAI NOTIS 5A</legend>
	#end
 	<table width="100%" cellspacing="2" cellpadding="1" border="0">
  	<tr >
    	<td colspan="4">
    		<input type="button" class="stylobutton100"  name="TambahNotis5A" id="TambahNotis5A" value="Tambah" onclick="TambahNotis()">
    	</td>

    </tr>

    <tr class="table_header">
	  	<td width="3%">Bil.</td>
	  	<td width="50%">Tarikh Pengeluaran 
	  	#if($!idNegeriNotis=="13")
		Bayaran L & S 80
		#elseif($!idNegeriNotis=="12")
		Surat Tawaran Kelulusan
		#else
		Notis 5A
		#end	
	  	</td>
	  	<td width="47%">Tarikh Luput</td>
	  	<!--<td width="3%">&nbsp;</td> -->
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
		  <td class="$row"><a href="javascript:Notis5AView('$fd.idnotis5a')" class="">$cnt.</a></td>
		  <td class="$row">
		  	<a href="javascript:Notis5AView('$fd.idnotis5a')" class="pautanms">
			$fd.tarikhnotis5a
			</a>
		  </td>
		  <td class="$row">$fd.tarikhluput1</td>
		  <!--<td class="$row">&nbsp;</td>-->
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
