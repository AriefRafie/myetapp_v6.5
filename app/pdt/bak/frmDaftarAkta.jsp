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
<input name="tabId" type="hidden" id="tabId" value="$tabId"/>
<input name="hitbutton" type="hidden" value="$hitbutton" />

&nbsp;
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No Akta</td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <input name="txtNoAktaC" type="text" id="txtNoAktaC" value="$txtNoAktaC" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Nama Akta</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtNamaAktaC" type="text" id="txtNamaAktaC" value="$txtNamaAktaC" />
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
        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="cari()" />
        </label>
          <label>
          <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosong()" />
        </label></td>
    </tr>
  </table>
</fieldset>
  <fieldset>
  <legend><strong>Senarai Akta</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr>
    <td colspan="2"><input type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambahAkta()"/></td>
      <td colspan="2" align="right">&nbsp;</td>
    </tr>
    <tr class="table_header">
      <td width="7%" scope="row"><strong>No</strong></td>
      <td width="19%"><strong>No Akta</strong></td>
      <td width="34%">Nama Akta</td>
      <td width="40%">Tarikh Kuatkuasa</td>
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
     #if ($akta.no != '') 
    <td class="$row"><a href="javascript:edit_item('$akta.id_Akta')" class="style1">$akta.no_Akta</a></td>
    #else
    <td class="$row" align="center"><div align="left">$akta.no_Akta</div></td>
    #end
    <td class="$row">$akta.nama_Akta</td>
    <td class="$row">$akta.tarikh_kuatkuasa</td>
    </tr>
    #end
  </table>
</fieldset>
   <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-06</strong></td>
  	</tr>
  </table>
  
<script>
function cari(){    	
	document.${formName}.action.value ="carian";
	document.${formName}.submit();
}
function seterusnya(){    	
	document.${formName}.action.value = "papar";
	document.${formName}.hitbutton.value = "next";
	document.${formName}.submit();
}
function sebelumnya(){    	
	document.${formName}.action.value = "papar";
	document.${formName}.hitbutton.value = "previous";
	document.${formName}.submit();
}
function tambahAkta(){
	document.${formName}.action.value="tambah";
	document.${formName}.submit();
}
function edit_item(id){
	document.${formName}.action.value="papar";
	document.${formName}.idAkta.value = id;
	document.${formName}.submit();
}
function kosong()
{
	document.${formName}.reset();
	document.${formName}.txtNoAktaC.value = "";
	document.${formName}.txtNamaAktaC.value = "";
	document.${formName}.txtTarikhKuatkuasaC.value = "";
}
</script>