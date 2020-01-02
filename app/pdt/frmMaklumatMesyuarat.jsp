<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PDT.css">
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="idTblPdtRujMesyuarat" id="idTblPdtRujMesyuarat" value="$!idTblPdtRujMesyuarat">
	<input type="hidden" name="idTblPdtMesyuarat" id="idTblPdtMesyuarat" value="$!idTblPdtMesyuarat">
</p>
<fieldset>
	<legend>
		<strong>Maklumat Mesyuarat</strong>
	</legend>
	<table width="100%">
		<tr>
			<td width="29%" align="right" scope="row">Tajuk Mesyuarat</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
			$paramTajuk
			</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Tahun</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">$selectTahun</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Bil Mesyuarat</td>
			<td width="1%" scope="row">:</td>
			<td width="70%"><input name="paramBilMesyuarat" type="text" id="paramBilMesyuarat" value="$!paramBilMesyuarat" /></td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td>
			#if($mode == 'addNew')
				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
			#end
			#if($mode == 'edit')
				<input type="button" name="cmdEdit" id="cmdEdit" value="Kemaskini" onclick="kemaskini('$!idTblPdtRujMesyuarat','$!idTblPdtMesyuarat')"/>
			#end		
			</td>
		</tr>
	</table>
</fieldset>
<fieldset>
	<legend>
		<strong>Senarai Lampiran</strong>
	</legend>
	#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFail")) #set ($startno =
	$startnoInt.intValue()) #set ($i = $startno) #set ($total = $totalInt.intValue())
	<table width="100%">
		<tr>
			<td>
			#parse("app/utils/record_paging.jsp") 
			#if($mode == 'edit')
				<input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="javascript:tambahLampiran('$!idTblPdtMesyuarat')" /> 
			#end	
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr class="table_header">
						<td width="3%" align="center" scope="row">
							<strong>Bil.</strong>
						</td>
						<td width="13%" align="center">
							<strong>Tajuk Dokumen </strong>
						</td>
						<td width="15%" align="center">
							<strong>Jenis dokumen </strong>
						</td>
							<td width="10%" align="center"></td>
					</tr>
					#if($SenaraiFail.size() > 0) 
					#foreach ($result in $SenaraiFail ) 
						#set($counter = $velocityCount ) 
					#if ( ($counter %
					2) == 0 ) 
						#set( $row ="row2" ) 
					#else 
						#set( $row = "row1" ) 
					#end
					<tr>
						<td height="20" align="center" class="$row">$result.bil</td>
						<td class="$row" align="center">
						<a  href="javascript:paparLampiran('$result.idLampiran')" style="color: #0000FF">$result.namaFail</a></td>
						<td class="$row" align="center">$result.jenisDokumen</td>
						<td class="$row">
							<a  href="javascript:hapusLampiran('$result.idLampiran')" style="color: #0000FF">[Hapus]</a>
							<a  href="javascript:cetakLampiran('$result.idLampiran')" style="color: #0000FF">[Cetak]</a>
							<a  href="javascript:muatTurunLampiran('$result.idLampiran')" style="color: #0000FF">[Muatturun]</a>
						</td>
					</tr>
					#end #else
					<tr>
						<td colspan="6">Tiada Rekod</td>
					</tr>
					#end
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali()" /> 
			</td>
		</tr>
	</table>
</fieldset>
<script>
function carianMesyuarat(){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=carian");
}

function tambah(){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=tambahMesyuarat");
}

function papar(id){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=edit&idTblPdtMesyuarat="+id);
}

function tambahLampiran(id) {
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=openRujLampp&idTblPdtMesyuarat="+id);
}

function simpan(){
	 if ( !window.confirm("Mesyuarat akan disimpan.\nAdakah Anda Pasti?") ) return;
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=saveBaru");
}

function kemaskini(idPdtMesy,idRujMesy){
	if ( !window.confirm("Mesyuarat akan dikemaskini.\nAdakah Anda Pasti?") ) return;
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=kemaskini&idTblPdtMesyuarat="+idPdtMesy+"&idRujMesyuarat="+idRujMesy);
}


function paparLampiran(id){
	var url = "../servlet/ekptg.view.pdt.FrmTmbhSnriDokDisplayImage?id="+id;
	
	var hWnd = window.open(url,'view','width=800,height=500, resizable=yes,scrollbars=yes,button=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hapusMesyuarat(id,idRuj){
	 if ( !window.confirm("Mesyuarat akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	    doAjaxCall${formName}("","command2=deleteMesy&idTblPdtMesyuarat="+id+"&idRujMesyuarat="+idRuj);
}

function hapusLampiran(id){
	 if ( !window.confirm("Lampiran akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	    doAjaxCall${formName}("","command2=deleteLamp&idLampiran="+id);
}

function kembali(){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","");
}


function simpanLampiran() {
		document.${formName}.method.value="post";
		var x = create_request_string(document.${formName});
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmMesyuarat&save=saveRujLamp&"+x;
		document.${formName}.submit();
}

function kembaliMesyuarat(id){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=edit&idTblPdtMesyuarat="+id);
}
</script>
