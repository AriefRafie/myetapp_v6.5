<style type="text/css">
<!--
.q {
	color: #F00;
}
-->
</style>
#set ($input = "disabled=disabled" )
#parse("app/htp/permohonan/frmTerimaPohonBayaranNotis5A.jsp")


<fieldset>
  <legend>BUKTI BAYARAN</legend>

   <table width="100%" border="0">
     <tr>
          <td width="15%">No. Baucer</td>
          <td width="1%">:</td>
          <td width="15%"><input name="txtNoBaucer" type="text" id="txtNoBaucer" onkeyup="this.value=this.value.toUpperCase();" value="$!frr" $input/></td>
          <td width="7%">&nbsp;</td>
          <td width="16%">Tarikh Baucer</td>
          <td width="1%">:</td>
          <td width="45%"><input name="txtTarikhBaucer" type="text" id="txtTarikhBaucer" value="$!ftt" $input/>
          <a href="javascript:displayDatePicker('txtTarikhBaucer',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
     </tr>
        <tr>
          <td>No. Resit</td>
          <td>:</td>
          <td><input name="txtNoResit" type="text" id="txtNoResit" onkeyup="this.value=this.value.toUpperCase();" value="$!fii" $input/></td>
          <td>&nbsp;</td>
          <td>Tarikh Resit</td>
          <td>:</td>
          <td><input name="txtTarikhResit" type="text" id="txtTarikhResit" value="$!fyy" $input/>
          <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>Jumlah Baucer</td>
          <td>&nbsp;</td>
          <td><input name="txtJumBaucer" type="text" id="txtJumBaucer"  onblur="validateCurrency(this,this.value,'')" value="$!fuu" $input/></td>
        </tr>
        <tr>
          <td></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
</table></fieldset>
	  <table  width="100%" border="0">
	  <tr>
	    <td >
        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	    	</td>
	  </tr>
	  
	  	  <tr>
	    <td align="center" >
			<input class="stylobutton" type="button" value="Simpan" onclick="BayaranNotis($idNotis)" style="display:none"/>
	    </td>
	  </tr>
	  </table>
  <input type="hidden" name="idNotis5a" value="$idNotis5a" />
  <input type="hidden" name="dipermohonanNotis" id="dipermohonanNotis" value=" $idPermohonan" />

<fieldset><legend>SENARAI BUKTI BAYARAN</legend>
<table width="100%" border="0">

  <tr class="table_header">
    <td width="3%">Bil.</td>
    <td width="18%">No.Baucer</td>
    <td width="18%">No.Resit</td>
    <td width="18%">Tarikh Baucer</td>
    <td width="18%">Tarikh Resit</td>
    <td width="25%">Jumlah Baucer</td>
  </tr>
  #set ( $cnt1 = 0 )			
#foreach ( $Bukti in $BuktiBayaranInfo )
#set ( $cnt1 = $cnt1 + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
    <td class="$row">$cnt1.</td>
    <td class="$row">$Bukti.nobaucer</td>
    <td class="$row">$Bukti.noresit</td>
    <td class="$row">$Bukti.tarikhbaucer</td>
    <td class="$row">$Bukti.tarikhresit</td>
    <td class="$row">$Bukti.jumlahbayaran</td>
  </tr>
  #end
  #if ($cnt == 0)
	<tr> 
		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
        <td colspan="3" scope="row"></td>
	</tr> 
#end
</table>
</fieldset>
