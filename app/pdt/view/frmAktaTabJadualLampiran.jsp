

        
	#if ($action == "doUpdate" || $action == "view")

     <fieldset>
      <legend>SENARAI LAMPIRAN</legend>
      <table>
     <tr>

      <td width="29%" align="right" valign="top"></td>
      <td align="center" valign="top"></td>
      <td valign="top">

      #set ($xx = 0)
      #foreach ($listlampiran in $List_LampiranFail)
      #set ($xx = $xx + 1)
      $xx )&nbsp;
      <a onClick="javascript:viewJadualLampiranBlob('$listlampiran.IDJadualLampiranFail')" 
      href="#" style="color:#0000FF">$listlampiran.NamaLampiran</a>
      &nbsp;&nbsp;
      <a href="#" onClick="javascript:deleteJadualLampiranBlob('$listlampiran.IDJadualLampiran','$listlampiran.IDJadualLampiranFail')">
	       <img border="0" src="../img/delete.gif" />
	       </a>
      <br/>

      #end
      </td>
     </tr>
     #if ($xx == 0)
     <tr><td></td><td></td><td>Tiada Lampiran</td></tr>
     #end
     </table>
      </fieldset>

	#end
        
        
   
        <br />
        <fieldset>
          <legend>SENARAI JADUAL(PARA) </legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="17%" scope="row"><strong>Jadual</strong></td>
              <td width="15%" scope="row"><strong>Tajuk</strong></td>
              <td width="45%" scope="row"><strong>Catatan</strong></td>
              <!--
              <td width="16%" scope="row"><strong>Lampiran</strong></td>
              -->
              <td width="7%" scope="row">&nbsp;</td>
            </tr>
            #set ($fail = '')
            #foreach ($fail in $List_CarianJadualLampiran)
            #if ($fail.No == '') 
            #set ($row = 'row1')
            #elseif ($fail.No % 2 != 0)
            #set ($row = 'row1')
            #else 
            #set ($row = 'row2')
            #end
  <tr>
    <td class="$row">
    <a href="javascript:viewJadualLampiran('$fail.IDJadualLampiran')" style="color:#0000FF">$fail.NamaJadualLampiran</a></td>
    
      <td class="$row">$fail.Tajuk</td>
  
    <td class="$row">$fail.Catatan</td>
    <!--
    <td class="$row"><a href="javascript:viewJadualLampiranBlob('$fail.IDJadualLampiran')" style="color:#0000FF">$fail.NamaLampiran</a></td>
    -->
 <td class="$row"><input type="button" value="Hapus" onclick="doHapusJadualLampiran('$fail.IDJadualLampiran');" /></td>
  </tr>
            #end
          </table>
</fieldset>