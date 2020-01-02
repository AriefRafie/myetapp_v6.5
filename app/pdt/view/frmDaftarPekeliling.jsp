<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

<input name="action" type="hidden" value="" />
<input name="mode" type="hidden" value="" />
<input name="idPekeliling" type="hidden" value="" />
&nbsp;
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">Kategori Pekeliling</td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>$selectKategoriPekeliling</label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Perkara Pekeliling</td>
      <td scope="row">:</td>
      <td><label>$selectPerkaraPekeliling</label></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">Tajuk Pekeliling</td>
      <td valign="top" scope="row">:</td>
  <td><label>
        <textarea name="txtTajukPekeliling" cols="41" id="txtTajukPekeliling" onblur="this.value=this.value.toUpperCase();">$tajukPekeliling</textarea>
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Tahun</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtTahun" type="text" id="txtTahun" value="$tahun" size="5" maxlength="4" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Kuatkuasa</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtTarikhKuatkuasa" type="text" id="txtTarikhKuatkuasa" value="$tarikhKuatkuasa" size="10" />
      <a href="javascript:displayDatePicker('txtTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
    </tr>
    <tr>
      <td colspan="2" align="right" scope="row">&nbsp;</td>
      <td><label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"  />
        </label>
          <label>
          <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label></td>
    </tr>
  </table>
</fieldset>
  <fieldset>
  <legend><strong>Senarai Pekeliling</strong></legend>
  <p>
    <label>
     
      #parse("app/utils/record_paging.jsp") 
    </label></p>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="1%" scope="row"><strong>No</strong></td>
      <td width="10%"><strong>Bil Pekeliling</strong></td>
      <td width="15%"><strong>Kategori Pekeliling</strong></td>
      <td width="28%"><strong>Perkara Pekeliling</strong></td>
      <td width="28%"><strong>Tajuk Pekeliling</strong></td>
      <td width="10%"><strong>Tarikh Kuatkuasa</strong></td>
      <td width="5%"><strong>Status Pekeliling</strong></td>
      <td></td>
    </tr>
    #foreach ($pekeliling in $SenaraiFail)
    #if ($pekeliling.bil == '') 
  		#set ($row = 'row1')
    #elseif ($pekeliling.bil % 2 != 0)
  	   	#set ($row = 'row1')
  	#else 
  		#set ($row = 'row2')
  	#end 
    <tr>
      <td class="$row">$pekeliling.bil</td>
      #if ($pekeliling.bil != '')
      <td class="$row"><a href="javascript:edit_item('$pekeliling.id_Pekeliling')" class="style1">$pekeliling.bil_Pekeliling</a></td>
      #else
       <td class="$row">$pekeliling.bil_Pekeliling</td>
      #end
      <td class="$row">$pekeliling.kategori_Pekeliling</td>
      <td class="$row">$pekeliling.perkara_Pekeliling</td>
      <td class="$row">$pekeliling.tajuk_Pekeliling</td>
      <td class="$row">$pekeliling.tarikh_Kuatkuasa</td>
      <td class="$row">$pekeliling.status</td>
      #if ($pekeliling.bil != '')
      <td><a href = "javascript:view_lampiran('$pekeliling.id_Pekeliling')">
      <img border="0" src="../img/pdf-small.png" /></a></td>
      #else
      <td>&nbsp;</td>
      #end
      
    </tr>
   #end
  </table>
</fieldset>
 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-10</strong></td>
  	</tr>
  </table>
<script>
function tambah(){
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=tambahDataBaru";
	document.${formName}.submit();

}
function carian(){
	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txtTajukPekeliling.value = "";
	document.${formName}.txtTahun.value = "";
	document.${formName}.txtTarikhKuatkuasa.value = "";
	document.${formName}.socKategoriPekeliling.value = "";
	document.${formName}.socPerkaraPekeliling.value = "";	
}
function edit_item(id){
	/*
	document.${formName}.method = "POST";
	document.${formName}.action.value = "papar";
	document.${formName}.mode.value = "";
	document.${formName}.idPekeliling.value = id;
	*/
	document.${formName}.method="POST";
	document.${formName}.idPekeliling.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.readonly.PendaftaranPekelilingReadonly&action=papar";
	
	document.${formName}.submit();

}
function view_lampiran(id) {
	    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=pekeliling&id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
</script>