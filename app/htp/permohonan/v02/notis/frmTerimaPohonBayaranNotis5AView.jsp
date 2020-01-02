<style type="text/css">
<!--
.q {
	color: #F00;
}
-->
</style>

##parse("app/htp/frmTerimaPohonBayaranNotis5A.jsp")
#parse("app/htp/permohonan/notis/frmTerimaPohonBayaranNotis5A.jsp")


<fieldset>
  <legend>Bukti Pembayaran</legend>

   <table width="100%" border="0">
     <tr>
          <td width="15%">No. Baucer</td>
          <td width="1%">:</td>
          <td width="15%"><input name="txtNoBaucer" type="text" id="txtNoBaucer" onkeyup="this.value=this.value.toUpperCase();" value="$!frr"/></td>
          <td width="7%">&nbsp;</td>
          <td width="16%">Tarikh Baucer</td>
          <td width="1%">:</td>
          <td width="45%"><input name="txtTarikhBaucer" type="text" id="txtTarikhBaucer" value="$!ftt" />
          <a href="javascript:displayDatePicker('txtTarikhBaucer',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
     </tr>
        <tr>
          <td>No. Resit</td>
          <td>:</td>
          <td><input name="txtNoResit" type="text" id="txtNoResit" onkeyup="this.value=this.value.toUpperCase();" value="$!fii" /></td>
          <td>&nbsp;</td>
          <td>Tarikh Resit</td>
          <td>:</td>
          <td><input name="txtTarikhResit" type="text" id="txtTarikhResit" value="$!fyy" />
          <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>Jumlah Baucer</td>
          <td>&nbsp;</td>
          <td><input name="txtJumBaucer" type="text" id="txtJumBaucer"  onblur="validateCurrency(this,this.value,'')" value="$!fuu" /></td>
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
			<input class="stylobutton" type="button" value="Simpan" onclick="BayaranNotis($idNotis)" />
	    </td>
	  </tr>
	  </table>
  <input type="hidden" name="idNotis5a" value="$idNotis5a" />
  <input type="hidden" name="dipermohonanNotis" id="dipermohonanNotis" value=" $idPermohonan" />

<fieldset><legend>Senarai Bukti Pembayaran</legend>
<table width="100%" border="0">

  <tr class="table_header">
    <td width="3%">Bil.</td>
    <td width="18%">No.Baucer</td>
    <td width="18%">No.Resit</td>
    <td width="18%">Tarikh Baucer</td>
    <td width="18%">Tarikh Resit</td>
    <td width="20%">Jumlah Baucer</td>
    <td width="5"></td>
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
    <td class="$row"><input class="stylobutton" type="button" value="Hapus" onclick="BayaranNotisHapus($!Bukti.idBayaran)" /></td>
  </tr>
  #end
  #if ($cnt == 0)
	<tr> 
		<td colspan="4" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
        <td colspan="3" scope="row"></td>
	</tr> 
#end
</table>
</fieldset>
