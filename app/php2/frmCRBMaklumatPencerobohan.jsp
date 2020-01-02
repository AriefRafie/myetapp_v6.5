
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupPenceroboh')
  <tr>
    <td> #parse("app/php2/frmCRBMaklumatPencerobohanDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PENCEROBOH</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="3" scope="row"><input name="cmdDaftarPenceroboh" type="button" value="Tambah" onClick="javascript:doDaftarBaruPenceroboh()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>Nama</strong></td>
          <td width="30%"><strong>Alamat</strong></td>
          <td width="30%"><strong>Jenis Pencerobohan</strong></td>
        </tr>
        #set ($senaraiPenceroboh = "")
        #if ($SenaraiPenceroboh.size() > 0)
        #foreach ($senaraiPenceroboh in $SenaraiPenceroboh)
        #if ($senaraiPenceroboh.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiPenceroboh.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiPenceroboh.bil</td>
          <td class="$row"><a href="javascript:doPaparPenceroboh('$senaraiPenceroboh.idPenceroboh')" class="style2">$senaraiPenceroboh.namaPenceroboh</a></td>
          <td class="$row">$senaraiPenceroboh.alamatPenceroboh1&nbsp;$senaraiPenceroboh.alamatPenceroboh2&nbsp;$senaraiPenceroboh.alamatPenceroboh3 &nbsp; </td>
          <td class="$row">$senaraiPenceroboh.jenisPencerobohan </td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
        <tr>
          <td colspan="7" align="center">&nbsp;
          </td>
        </tr>
        <tr>
          <td colspan="7" align="center">
           #if ($flagPopup == '')
          <input name="cmdBatalLT1" id="cmdBatalLT1" type="button" value="Kembali" onClick="batalLawatanTapak()" />
          #end</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
