<fieldset>
<legend>TUKARGUNA/ PENAWARAN/ PELEPASAN</legend>
<table width="100%" border="0">
  <tr>
    <td colspan="2"><div align="right">No . Fail Seksyen : </div></td>
    <td colspan="3"><input name="txtNoFail" type="text" id="txtNoFail" value="$!txtNoFail" size="30"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="right"></div></td>
    <td colspan="3"><input class="stylobutton"  type="submit" name="btnFind" id="btnFind" value="Carian" /></td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend>Senarai Fail</legend>
<table width="100%">
	<tr>
		      <td colspan="6">#parse("app/utils/record_paging.jsp")</td>
		
	</tr>
  <tr class="table_header">
    <td width="4%"><div align="center"Bil.</div></td>
    <td width="20%"><div align="center">No. Fail Seksyen</div></td>
    <td width="10%"><div align="center">Tarikh Buka Fail</div></td>
    <td width="15%"><div align="center">Urusan</div></td>
    <td width="36%"><div align="center">Maklumat Tanah</div></td>
    <td width="15%"><div align="center">Status Fail</div></td>
  </tr>
	#set ($list = "")
    #set ( $cnt = 0 )	
    #if ($SenaraiFail.size() > 0)
	    #foreach ($senarai in $SenaraiFail)
	    #set ( $cnt = $cnt + 1 )
	    
	    #set( $i = $velocityCount )
	    #if ( ($i % 2) != 1 )
	    	#set( $row = "row2" )
	    #else
	    	#set( $row = "row1" )
	    #end
	    #set($s=$!senarai.bil)
	    #set($a=$!senarai.permohonan.getNamaNegeri())
	  <tr>
	    <td class="$row">$!cnt.</td>
	    <td class="$row">$!senarai.permohonan.pfdFail.getNoFail()</td>
	    <td class="$row">$!senarai.permohonan.pfdFail.getTarikhDaftarFail()</td>
	    <td class="$row">$!senarai.permohonan.pfdFail.getNamaSuburusan()</td>
	    <td class="$row">&nbsp;</td>
		<td class="$row">$!senarai.statusPermohonan</td>
	  </tr>
	      #end
    #else
  <tr>
    <td colspan="5" class="row1">Tiada rekod.</td>
  </tr>
    #end
</table>
</fieldset>
