<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())
<input name="idEnakmen" type="hidden" id="idEnakmen" value="$idEnakmen"/>
<input name="tabId" type="hidden" id="tabId" value="$tabId"/>

&nbsp;
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No Enakmen</td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <input name="txtNoEnakmenC" type="text" id="txtNoEnakmenC" value="$txtNoEnakmenC" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Nama Enakmen</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtNamaEnakmenC" type="text" id="txtNamaEnakmenC" value="$txtNamaEnakmenC" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Kuatkuasa</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtTarikhKuatkuasaC" type="text" id="txtTarikhKuatkuasaC" value="$txtTarikhKuatkuasaC" size="10" />
      </label>
      <a href="javascript:displayDatePicker('txtTarikhKuatkuasaC',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      </td>
    </tr>
    <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td><label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="cari()" />
        </label>
          <label>
          <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosong()" />
        </label></td>
    </tr>
  </table>
</fieldset>
  <fieldset>
  <legend><strong>Senarai Enakmen</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr>
      <td colspan="3">
          <input type="button" value="Tambah" onclick="tambahEnakmen()"/></td>
       <td colspan="3" align="right">&nbsp;</td>
    </tr>
    <tr class="table_header">
      <td width="4%" scope="row"><strong>No</strong></td>
      <td width="20%"><strong>No Enakmen</strong></td>
      <td width="34%">Nama Enakmen</td>
      <td width="24%">Tarikh Kuatkuasa</td>
      <td width="27%">&nbsp;</td>
    </tr>
    #set ($enakmaen = '')
    #foreach ($enakmen in $Senarai)
    #if ($enakmen.no == '') 
    #set ($row = 'row1')
    #elseif ($enakmen.no % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
  <tr>
    <td height="20" class="$row">$enakmen.no</td>
    <td class="$row">
     #if ($enakmen.no != '') 
    <a href="javascript:edit_item('$enakmen.id_enakmen')" class="style1">$enakmen.no_enakmen</a>
    #else
    $enakmen.no_enakmen
    #end
    </td>
    <td class="$row">$enakmen.nama_enakmen</td>
    <td class="$row">$enakmen.tarikh_kuatkuasa</td>
    <td class="$row"><a href = "javascript:viewEnakmenBlob('$enakmen.id_enakmen')">
    <img src="../img/pdf-small.png" alt="" border="0" /></a></td>
  </tr>
    #end
  </table>
</fieldset>
   <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-06</strong></td>
  	</tr>
  </table>
<script type="text/javascript" src="../app/pdt/enakmen.js"></script>
  