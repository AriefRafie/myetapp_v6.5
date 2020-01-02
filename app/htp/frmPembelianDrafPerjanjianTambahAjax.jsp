<style type="text/css">
<!--
.style1 {color: #0033FF}
.tiadarekod {
	color: #F00;
}
-->
</style>

<table width="100%" border="0">
#if($addmode == "newDraf" || $addmode == "viewDraf" || $addmode == "updateDraf")
  <tr>
    <td>
    #foreach ( $draf in $Draf )
      <fieldset>
      <legend><strong>DRAF PERJANJIAN</strong></legend>
      <table width="100%" border="0" cellpadding="0">
      
        <tr>
          <td><div align="right">Tarikh Terima :</div></td>
          <td><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="15" value="$draf.tarikhTerima" onblur="check_date(this)" $mode $classDis />
          
          
          #if($addmode == "updateDraf" || $addmode == "newDraf" )
            <img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');" />
           #end 
            
            </td>
        </tr>
        <tr>
          <td width="30%"><div align="right">Tarikh Hantar :</div></td>
          <td width="70%"><input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="15" value="$draf.tarikhHantar" onblur="check_date(this)" $mode $classDis  />
          
           #if($addmode == "updateDraf" || $addmode == "newDraf" )
            <img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');" />
            #end
            
            </td>
        </tr>
        <tr>
          <td><div align="right">Ulasan :</div></td>
          <td><textarea name="txtcatatan" id="txtcatatan" cols="45" rows="5" onkeyup="this.value=this.value.toUpperCase();" $mode $classDis  >$draf.ulasan</textarea></td>
        </tr>
        <tr>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          
          #if($addmode == "viewDraf")
          <input type="button" name="cmdkemaskini" id="cmdkemaskini" value="Kemaskini" onclick="fPDPTA_Kemaskini($draf.idDraf)" />
          #end

		  #if($addmode == "newDraf")
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="fPDPTA_Simpan()" />
          #end  
            
          #if($addmode == "updateDraf")
            <input type="button" name="cmdSimpanUpdate" id="cmdSimpanUpdate" value="Simpan" onclick="fPDPTA_SimpanUpdate($draf.idDraf)" />
            
            &nbsp;&nbsp;
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal"  onclick="fPDPTA_Batal()"/>
            &nbsp;&nbsp;
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali"  onclick="fPDPTA_Kembali()"/>
            &nbsp;&nbsp;
            #end
            
            
            
            </td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
          
        </table>
    </fieldset>
  #end
  
    </td>
  </tr>
    #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI DRAF</strong></legend>
      <table align="center" width="100%">
       
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruDraf()"/></td>
        </tr>
 
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>Tarikh Terima Draf</strong></td>
          <td width="15%" align="center"><strong>Tarikh Hantar Draf</strong></td>
          <td width="35%" ><strong>Ulasan</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiDraf.size() > 0)
        #foreach ($list in $SenaraiDraf)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparDraf('$list.idDraf')" class="style1">$list.tarikhTerima</a></td>
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row" >$list.ulasan</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1">&nbsp;</td>
          <td class="row1"><span class="tiadarekod">Tiada Rekod</span></td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
    </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
</p>
