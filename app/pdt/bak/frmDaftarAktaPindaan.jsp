
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())

<input name="action" type="hidden" value="$action" />
<input name="idAkta" type="hidden" id="idAkta" value="$idAkta"/>
&nbsp;
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No Akta</td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <input type="text" name="txtNoAkta" id="txtNoAkta" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Nama Akta</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtNamaAkta" type="text" id="txtNamaAkta" value="" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Kuatkuasa</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtTarikhKuatkuasa" type="text" id="txtTarikhKuatkuasa" size="10" />
      </label>
      <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      </td>
    </tr>
    <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td><label>
        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" />
        </label>
          <label>
          <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" />
        </label></td>
    </tr>
  </table>
  </fieldset>
  <fieldset>
  <legend><strong>Senarai Akta</strong></legend>

  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="3%" height="25" scope="row">No</td>
      <td width="22%">No Akta</td>
      <td width="24%">Nama Akta</td>
      <td width="21%">Tarikh Kuatkuasa</td>
      <td width="30%">&nbsp;</td>
    </tr>
    #set ($akta = '')
    #foreach ($akta in $Senarai)
    #if ($akta.no == '') 
    #set ($row = 'row1')
    #elseif ($akta.no % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
    <tr>
    <td height="20" class="$row">$akta.no</td>
    <td class="$row">
     #if ($akta.no != '') 
    <a href="javascript:edit_item('$akta.id_Akta')" class="style1">$akta.no_Akta</a>
    #else
    <div align="left">$akta.no_Akta</div>
    #end
    </td>
    <td class="$row">$akta.nama_Akta</td>
    <td class="$row">$akta.tarikh_kuatkuasa</td>
    <td><a href = "javascript:viewAktaBlob('$akta.id_Akta')">
    <img src="../img/pdf-small.png" alt="" border="0" /></a></td>
    </tr>
    #end
  </table>
  </fieldset>
    <fieldset>
  <legend><strong>Senarai Pindaan Akta</strong></legend>

  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="3%" height="23" scope="row">No</td>
      <td width="22%">No Akta Asal</td>
      <td width="22%">No Akta Pindaan</td>
      <td width="24%">Nama Akta</td>
      <td width="21%">Tarikh Kuatkuasa</td>
      <td width="30%">&nbsp;</td>
    </tr>
    #set ($aktaP = '')
    #foreach ($aktaP in $AktaPindaan)
    #if ($aktaP.no == '') 
    #set ($row = 'row1')
    #elseif ($aktaP.no % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
    <tr>
    <td height="20" class="$row">$aktaP.no</td>
    #if ($aktaP.no != '') 
    <td class="$row"><a href="javascript:edit_item('$aktaP.id_Akta')" class="style1">$aktaP.no_Akta</a></td>
    #else 
    <td class="$row" align="center"><div align="left">$aktaP.no_Akta</div></td>
    #end
    <td class="$row"><a href="javascript:edit_item('$aktaP.id_Akta')" class="style1">$aktaP.no_Akta</a></td>
    <td class="$row">$aktaP.nama_Akta</td>
    <td class="$row">$aktaP.tarikh_kuatkuasa</td>
    </tr>
    #end
  </table>
</fieldset>
   <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-04</strong></td>
  	</tr>
  </table>
  
  
<script>
function edit_item(id){
	document.${formName}.action.value="papar";
	document.${formName}.idAkta.value = id;
	document.${formName}.submit();
}
function viewAktaBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>