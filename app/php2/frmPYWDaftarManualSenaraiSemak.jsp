<fieldset>
<legend>SENARAI SEMAK</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><table width="100%" border="0" cellspacing="2" cellpadding="2">
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
        #else
        #set($checked = '')
        #end
        <tr>
          <td class="$row" width="5%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsSenaraiSemak" $checked $disabled /></td>
          <td class="$row" width="95%">$list.keterangan</td>
        </tr>
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
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end
      #if ($mode == 'view')
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
      #if($idStatus == '1610198')
      <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Ke Jabatan Teknikal" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end
      #end
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
      #end </td>
  </tr>
</table>
</fieldset>
