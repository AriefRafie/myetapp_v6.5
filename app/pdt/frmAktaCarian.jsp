
<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>AKTA</strong> </td>
</tr>
</table>

<fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No. Akta</td>
      <td width="1%" scope="row">:</td>
      <td width="70%">
        <input name="txtNoAkta" type="text" id="txtNoAkta" value="$txtNoAkta" />
      </td>
    </tr>
    
    <tr>
      <td align="right" scope="row">Nama Akta</td>
      <td scope="row">:</td>
      <td>
        <input name="txtNamaAkta" type="text" id="txtNamaAkta" value="$txtNamaAkta" />
      </td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Kuatkuasa</td>
      <td scope="row">:</td>
      <td>
        <input name="txdTarikhKuatkuasa" type="text" id="txdTarikhKuatkuasa" value="$txdTarikhKuatkuasa" size="10" />
        <a href="javascript:displayDatePicker('txdTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      
      </td>
    </tr>
     <tr>
      <td align="right" valign="top" scope="row"><i>Tag</i> Dokumen</td>
      <td scope="row" valign="top">:</td>
      <td>
      	<textarea name="tag_dokumen" cols="41" rows="3" onblur="this.value=this.value.toUpperCase()" >$!tag_Dokumen</textarea>
        <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/>
      </td>
    </tr>
    <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td><label>
        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="" /></label>
        <label>
        <input type="submit" name="cmdKosongkanAkta" value="Kosongkan" onclick="kosongCarianAkta()" /></label>
      </td>
    </tr>
  </table>
</fieldset>
<fieldset>
  <legend><strong>Senarai Akta</strong></legend>
  <p>
  <label>
  <input type="button" value="Tambah" onclick="tambahAkta()"/>
  </label>
  #parse("app/utils/record_paging.jsp")
  </p>
  <table width="100%">
     
    <tr class="table_header">
      <td width="3%" scope="row"><strong>Bil.</strong></td>
      <td width="20%"><strong>No. Akta</strong></td>
      <td width="60%"><strong>Nama Akta</strong></td>
      <td width="12%"><strong>Tarikh Kuatkuasa</strong></td>
      <td width="5%">&nbsp;</td>
    </tr>
#set ($fail = '')

#foreach ($fail in $SenaraiFail)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
    	<tr>
      <td height="20" class="$row">
      $fail.No
      </td>
      <td class="$row">
    #if ($fail.No != '') 
      <a href="javascript:viewAkta('$fail.IDAkta')" style="color:#0000FF">$fail.NoAkta</a>
    #else
      <div align="left">$fail.NoAkta</div>
    #end
    </td>
      <td class="$row">$fail.NamaAkta</td>
      <td class="$row">$fail.TarikhKuatkuasa</td>
      <td align=right class="$row">
      <a href = "javascript:viewAktaBlob('$fail.IDAkta')">
      <img border="0" src="../img/pdf-small.png" /></a>
       #if($current_role=="(PDT)HQPengguna")
          <a alt="Hapus Akta" href = "javascript:deleteAkta('$fail.IDAkta')">
      &nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a> 
      #else
      &nbsp;&nbsp; 
       #end
      </td>
    </tr>
#end
  </table>
</fieldset>





<!--PENAMBAHAN PAPARAN  -->

<fieldset>
  <legend><strong>Senarai Dokumen Berkaitan Tanah Rizab Melayu</strong></legend>
    </p>
	<a href="javascript:senaraiDokumenBerkaitanTRM()"  class="help" title="Dokumen Berkaitan Tanah Rizab Melayu"><font color="blue">
	</p>Dokumen Berkaitan Tanah Rizab Melayu</font></a>
</fieldset> 

  
<script>
	function senaraiDokumenBerkaitanTRM() {
		var urlTemp = "$EkptgUtil.getTabID("Tanah Rizab Melayu",$portal_role)?_portal_module=ekptg.view.pdt.FrmDokBerkaitanTanahRizabMelayu";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
		
	}
	
	
	</script>
  
<input type="hidden" id="Akta_IDAkta" name="Akta_IDAkta" />  

<script type="text/javascript" src="../app/pdt/akta.js"></script>
<script>
	function paparAkta(idAkta) {
	    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
	    var hWnd = window.open(url,'Cetak','width=900,height=700, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function edit_item(id){
		//alert("test");
		//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "papar";
		//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value = "";
		//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.idDokumen.value = id;
		//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();
		doAjaxCallFekptg_view_pdt_FrmViewAkta("papar","action=papar&idDokumen="+id);

	}
</script>
<script>
//alert("ssssss");
//alert("sssssss"+'$current_role');

$jquery(document).ready(function () {

	//if('$current_role'=="(PDT)HQPengguna" || '$current_role'=="(PDT) Pengguna Bahagian Strata")
	if('$current_role'=="(PDT)HQPengguna")
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		//$jquery("input[type=button]").hide();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		$jquery("input[type=button]").show();
	}
	else
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		//$jquery("input[type=button]").show();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		$jquery("input[type=button]").hide();
	}
});



</script>
