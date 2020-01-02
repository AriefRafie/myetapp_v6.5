<fieldset>
<table>
	<tr>
		<td colspan="2" align="CENTER">&nbsp;</td>
		
	</tr>
	<tr>
		<td colspan="2" align="CENTER">
			<b>$!borangKMessage<b>
		</td>
		
	</tr>
	<tr>
		<td>
			No. Fail : 
		</td>
		<td>
			<input type="text" name="noFailPPT" size="40">
		</td>
	
	</tr>
	<tr>
		<td align="right" colspan="2">
			<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Kembali" onclick="selectTab(1,'kemaskinipermohonan','BorangK',0,'$idpermohonan')">
			<input type="button" class="stylobutton100" name="cmdCari"  value="Cari" onclick="cariFailPPT()">
		
		</td>
	
	</tr>
</table>
</fieldset>
<br>
<table>
	<tr class="table_header">
        <td width="3%"><b>Bil.</b></td>
        <td width="23%"><b>No. Fail</b></td>
        <td width="17%"><b>Tarikh Borang K</b></td>
        <!--  <td width="17%"><b>No Warta</b></td>   -->        
        <td width="34%"><b>No. Hakmilik</b></td>
        <td width="17%"><b>No. Lot/PT</b></td>
       	<td width="7%">&nbsp;</td>
    </tr>
    
	#set ($list = "")
    #set ( $cnt = 0 )	
    #if ($borangKList.size() > 0)
    #foreach ($senarai in $borangKList)
    #set ( $cnt = $cnt + 1 )
    
    #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
    	#set( $row = "row2" )
    #else
    	#set( $row = "row1" )
    #end
    <tr>
    	<td class="$row">$!cnt.</td>
    	<td class="$row">$!senarai.noFail</td>
    	<td class="$row">$!senarai.tarikhBorangK</td>
    	<!--  <td class="$row">$!senarai.noWarta</td> -->
    	<td class="$row">$!senarai.noHakmilik</td>
    	<td class="$row">$!senarai.noLot</td>
    	<td class="$row">
    	#if($!senarai.tarikhDaftar=="0")
     		<input type="button" class="stylobutton100"  name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanBorangK($senarai.idHakmilik)">
   		#else
    		Didaftar pada $!senarai.tarikhDaftar
    	#end
    	</td>
    </tr>
    
    #end
    #end


</table>
<input type="hidden" name="idpermohonan" value="$idpermohonan">
<input type="hidden" name="idfail" value="$idfail">
<input type="hidden" name="detailMode" value="">