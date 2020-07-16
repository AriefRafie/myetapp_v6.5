<fieldset>
<legend>SENARAI SEMAK</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2">
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
       #if ($SenaraiSemak.size() > 0)
        #set ($list = "")
        #foreach ($list in $SenaraiSemak)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        
        #if($list.flag == 'Y')
        #set($checked = 'checked')
        #set($disabled = 'disabled')
        #else
        #set($checked = '')
        #end
        
        #if ($mode == 'update')
	        <tr>
	          <td class="$row" width="5%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsSenaraiSemak" $checked /></td>
	          <td class="$row" width="95%">$list.keterangan</td>
	        </tr>
	      #end
	      #if ($mode == 'view')
	      	<tr>
	          <td class="$row" width="5%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsSenaraiSemak" $checked $disabled /></td>
	          <td class="$row" width="95%">$list.keterangan</td>
	        </tr>
	      #end
	      
        #end
        #else
        <tr>
          <td class="$row" width="5%">&nbsp;</td>
          <td class="$row" width="95%">Tiada Rekod</td>
        </tr>
        #end
      </table></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">#if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniSenaraiSemak()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalProjek()"/>
      #end
      #if ($mode == 'view')
      #if ($idStatus == '')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniPermohonan()"/>
      <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
      #end
      <!--<input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/> -->
      #end
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
      #end
</table>
</fieldset>