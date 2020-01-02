<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<br />
<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>KEPUTUSAN MAHKAMAH</strong></td>
</tr>
</table>
<input name="mode" type="hidden" value="" />
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td align="right" scope="row">Skop / Perkara</td>
      <td scope="row">:</td>
      <td>
	      <label>
          #if($idLampiran != '')
	      <select id="txtSkop" name="txtSkop">
          <option value="">SILA PILIH</option>
                    
                    #foreach ( $ruj in $list_TBLRUJSKOP )
                    	#set ( $selected_ruj = "" )
                        #if ( $skop == $ruj.ID )
                        #set ( $selected_ruj = "selected" )
                        #end 
                        
                        <option $selected_ruj value ="$ruj.ID">
                        $ruj.KETERANGAN
                        </option>
                     #end
					<!--<option value="">SILA PILIH</option>
					<option value="KANUN TANAH NEGERI - PELUPUSAN">KANUN TANAH NEGERI - PELUPUSAN</option>
					<option value="KANUN TANAH NEGERI - PEMBANGUNAN">KANUN TANAH NEGERI - PEMBANGUNAN</option>
					<option value="KANUN TANAH NEGERI - PERCUKAIAN">KANUN TANAH NEGERI - PERCUKAIAN</option>
					<option value="KANUN TANAH NEGERI - URUSNIAGA / BUKAN URUSNIAGA">KANUN TANAH NEGERI - URUSNIAGA / BUKAN URUSNIAGA</option>
					<option value="KANUN TANAH NEGERI - PELBAGAI">KANUN TANAH NEGERI - PELBAGAI</option>
					<option value="PENGAMBILAN TANAH">PENGAMBILAN TANAH</option>
					<option value="PEMILIKAN STRATA">PEMILIKAN STRATA</option>
					<option value="TANAH PERSEKUTUAN">TANAH PERSEKUTUAN</option>
					<option value="PELANTAR BENUA">PELANTAR BENUA</option>
					<option value="PEMBAHAGIAN PESAKA">PEMBAHAGIAN PESAKA</option>
					<option value="PERIZABAN MELAYU DAN TANAH ADAT">PERIZABAN MELAYU DAN TANAH ADAT</option>
					<option value="TANAH ORANG ASLI">TANAH ORANG ASLI</option>
					<option value="KAWASAN PENEMPATAN BERKELOMPOK">KAWASAN PENEMPATAN BERKELOMPOK</option>
					<option value="TAFSIRAN STATUT">TAFSIRAN STATUT</option>
					<option value="UMUM">UMUM</option>-->
			</select>
			#end
			#if($idLampiran == '')
	        <!--<input name="txtSkop" type="text" id="txtSkop" size="50" maxlength="100"/>-->
            
            <select id="txtSkop" name="txtSkop">
					<option value="">SILA PILIH</option>
                    
                    #foreach ( $ruj in $list_TBLRUJSKOP )
                    	#set ( $selected_ruj = "" )
                        #if ( $skop == $ruj.ID )
                        #set ( $selected_ruj = "selected" )
                        #end 
                        
                        <option $selected_ruj value ="$ruj.ID">
                        $ruj.KETERANGAN
                        </option>
                     #end
            </select>
            
            #end
	      </label>
      </td>
    </tr>
     <tr>
      <td align="right" scope="row">Nama Kes</td>
      <td scope="row">:</td>
      <td>
	      <label>
	        <input name="txtNamaKes" type="text" id="txtNamaKes" size="50" maxlength="100"/>
	      </label>
      </td>
    </tr>
      <tr>
      <td align="right" scope="row">Citation / No. Ruj Mahkamah</td>
      <td scope="row">:</td>
      <td>
	      <label>
	        <input name="txtCitation" type="text" id="txtCitation" />
	      </label>
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
  <legend><strong>Senarai Keputusan Mahkamah</strong></legend>
  <p>
    <label>
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
    </label>
     #parse("app/utils/record_paging.jsp") 
  </p>
  <table width="100%" >
    <tr class="table_header">
      <td width="1%" scope="row"><strong>Bil.</strong></td>
      <td width="20%"><strong>Skop / Perkara</strong></td>
      <td width="34%"><strong>Nama Kes</strong></td>
      <td width="24%"><strong>Citation / No Ruj Mahkamah</strong></td>
      <td width="27%"><strong>Lampiran</strong></td>
      <td></td>
    </tr>
    #if($SenaraiFail.size() > 0)
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
      	<a href="javascript:edit_item('$dokumen.idDokumen')" class="style1">$dokumen.namaSkop</a>
       #end      
      </td>
      <td class="$row">$dokumen.namaKes</td>
      <td class="$row">$dokumen.rujMahkamah</td>
      <td class="$row">
      #if($dokumen.content == 'true')
      	<a href = "javascript:paparDokumen('$dokumen.idLampiran')" class="style1">[Papar]</a>
      #end	
      	<a href = "javascript:deleteDokumen('$dokumen.idDokumen')" class="style1">[Hapus]</a> 
	  </td>
    </tr>
  	#end 
  	#else
	<tr>
		<td colspan="6">Rekod Tidak Dijumpai</td>
	</tr>
  	#end
  </table>
</fieldset>
<script>

function carian(){
	doAjaxCallFekptg_view_pdt_FrmKeputusanMahkamah("","action=carian");
}

function edit_item(id){
	doAjaxCall${formName}("","action=edit&idDokumen="+id);
}

function tambahLampiran(id,sendCommand){
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahLampiran?idDokumen="+id+"&sendCommand="+sendCommand;
	var hWnd = window.open(url,'printuser','width=800,height=400, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
function deleteDokumen(idDokumen) {
    if ( !window.confirm("Dokumen akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
    doAjaxCall${formName}("Delete","action=delete&idDokumen="+idDokumen);
}

function tambah(){
	doAjaxCallFekptg_view_pdt_FrmKeputusanMahkamah("","action=tambahBaru");
}

function simpan(){
	//alert("XXXXXXXXX");
	if (document.Fekptg_view_pdt_FrmKeputusanMahkamah.idDokumen.value == "" || document.Fekptg_view_pdt_FrmKeputusanMahkamah.idDokumen.value == 0){
		document.${formName}.method.value="post";
		document.${formName}.hitButton.value="save";
		var x = create_request_string(document.${formName});
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmKeputusanMahkamah&"+x;
		document.${formName}.submit();
	} else {
		document.${formName}.method.value="post";
		document.${formName}.hitButton.value="saveEdit";
		var x = create_request_string(document.${formName});
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmKeputusanMahkamah&"+x;
		document.${formName}.submit();
	}	
}

function paparDokumen(id){
	var url = "../servlet/ekptg.view.pdt.FrmTmbhSnriDokDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function batal(){
	doAjaxCallFekptg_view_pdt_FrmKeputusanMahkamah("","action=batal");
}
</script>