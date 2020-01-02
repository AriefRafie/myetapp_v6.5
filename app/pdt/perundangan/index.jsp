<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />

&nbsp;
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
  	<tr>
      <td align="right" scope="row">Nama Akta</td>
      <td scope="row">:</td>
      <td>$selectSeksyen</td>
    </tr>
   <tr>
      <td width="29%" align="right" valign="top" scope="row">Maklumat Perundangan</td>
      <td width="1%" scope="row" valign="top" >:</td>
      <td width="70%"><label>
        <textarea name="txtTajukDokumen" cols="41" rows="3" id="txtTajukDokumen">$tajukDokumen</textarea>
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Tempat Bicara</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtNoRujDokuman" type="text" id="txtNoRujDokuman" size="44" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Keputusan</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txdTarikhDokumen" type="text" id="txdTarikhDokumen" value="$tarikhDokumen" size="10" />
      <a href="javascript:displayDatePicker('txtTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
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
      <td colspan="2" align="right" scope="row">&nbsp;</td>
      <td><label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        </label>
          <label>
          <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label></td>
    </tr>
  </table>
</fieldset>
  <fieldset>
  <legend><strong>Senarai Maklumat</strong></legend>
  <p>
    <label>
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
    </label>
     #parse("app/utils/record_paging.jsp") 
  </p>
  <table width="100%" >
    <tr class="table_header">
      <td width="1%" scope="row"><strong>Bil.</strong></td>
      <td width="20%"><strong>Nama Akta</strong></td>
      <td width="34%"><strong>Maklumat Perundangan</strong></td>
      <td width="24%"><strong>Tarikh Keputusan</strong></td>
      <td width="27%"><strong>Tempat Bicara</strong></td>
      <td></td>
    </tr>
    #foreach ($dokumen in $SenaraiFail)
    #if ($dokumen.bil == '') 
  		#set ($row = 'row1')
    #elseif ($dokumen.bil % 2 != 0)
  	   	#set ($row = 'row1')
  	#else 
  		#set ($row = 'row2')
  	#end 
    <tr>
      <td class="$row">$dokumen.bil.</td>
      <td class="$row">
       #if ($dokumen.bil != '') 
      <a href="javascript:edit_item('$dokumen.idPerundangan')" class="style1">$dokumen.namaAkta</a>
      #else
      $dokumen.namaAkta
      #end      </td>
      <td class="$row"><a href="javascript:edit_item('$dokumen.idPerundangan')" class="style1">$dokumen.maklumat</a></td>
      <td class="$row">$dokumen.tarikhKeputusan</td>
      <td class="$row">$dokumen.tempatBicara</td>
      <td class="$row">
      <a alt="Hapus Dokumen" href = "javascript:deleteDokumen('$dokumen.idPerundangan')">
	&nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a> 
	</td>
    </tr>
    #end
  </table>
</fieldset>
 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-14</strong></td>
  	</tr>
  </table>
  <input name="Xaction" type="hidden" value="" />
<script type="text/javascript" src="../app/pdt/perundangan.js"></script>  
<script>
		
	function carian(){
		//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "";
		//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		doAjaxCall${formName}("","action=");
	}
	
	function kosongkan(){
		document.${formName}.reset();
	}
	
	// Pra kemasukan maklumat baru
	function tambah_(){
			//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "tambahDataBaru";
			//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		//doAjaxCall${formName}("","action=tambahDataBaru");
		
		//document.${formName}.action.value = "tambahDataBaru";
		
		document.${formName}.command.value = "tambahDataBaru";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan";	
		document.${formName}.submit();
				
	}
	
	function batal_(){	
		if (document.${formName}.mode.value == "Update"){		
			//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "papar";
			doAjaxCall${formName}("","action=papar");
		
		}else if (document.${formName}.mode.value == "New" || document.${formName}.mode.value == "View"){
			//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "SenaraiDokumen";
			doAjaxCall${formName}("","action=SenaraiDokumen");
		}
		
		//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		
	}
	
	function simpan_() {
		//if (document.Fekptg_view_pdt_PendaftaranDokumen.txtNoRujDokumen.value == ""){
				//alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
				//document.Fekptg_view_pdt_PendaftaranDokumen.txtNoRujDokumen.focus();
				//return;
		//} 
	
		if (document.${formName}.txtTajukDokumen.value == ""){
				alert('Sila masukkan " Maklumat " terlebih dahulu.');
				document.${formName}.txtTajukDokumen.focus();
				return;
		}
		/*
		if (document.Fekptg_view_pdt_PendaftaranDokumen.socJenisDokumen.value == ""){
				alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.');
				document.Fekptg_view_pdt_PendaftaranDokumen.socJenisDokumen.focus();
				return;
		} */
		
		if ( !window.confirm("Anda Pasti?") ) return;	
		if (document.${formName}.idDokumen.value == "" || document.${formName}.idDokumen.value == 0){
			//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "simpan";
			doAjaxCall${formName}("","action=simpan&mode=");
			
		} else {
			//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "editData"; 
			doAjaxCall${formName}("","action=editData&mode=");
			
		}
		
		//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
		//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		
	}
	
	function edit_item_(id){
		//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "papar";
		//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
		//document.Fekptg_view_pdt_PendaftaranDokumen.idDokumen.value = id;
		//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		//alert("idDokumenXX="+id);
		doAjaxCall${formName}("papar","action=papar&idDokumen="+id);
	
	}
	//fungsi kemaskini (menggunakan FCK)
	function kemaskini_(){	
			//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "kemaskini";
			//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
			//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		//doAjaxCall${formName}("","action=kemaskini&mode=");
		
		document.${formName}.mode.value = "";
		document.${formName}.command.value = "kemaskini";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan";	
		document.${formName}.submit();
		
	}
	
	function tambahLampiran_(id,sendCommand){
		var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahLampiranSemua?idDokumen="+id+"&sendCommand="+sendCommand;
		var hWnd = window.open(url,'printuser','width=800,height=400, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
	      if ((document.window != null) && (!hWnd.opener))
	        hWnd.opener = document.window;
	      if (hWnd.focus != null) hWnd.focus();
	
	}
	
function deleteDokumen(idDokumen) {
    if ( !window.confirm("Maklumat Perundangan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	document.${formName}.idDokumen.value = idDokumen;
    doAjaxCall${formName}("Delete","action=Delete");
}

</script>