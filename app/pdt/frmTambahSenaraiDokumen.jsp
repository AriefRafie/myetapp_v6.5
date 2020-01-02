<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PDT.css">
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
	<fieldset>
			<legend onclick="javascript:hideShow('$result.id')">
				<strong>$title</strong>
			</legend>
			<table align="center" width="100%" > 
		            <tr>
		              <td colspan="5" scope="row">
		              	<input name="cmdTambah" type="button" value="Tambah" onclick="javascript:tambah('$id')"/>
		              </td>
		            </tr>
		            <tr>
		              	<td colspan="5" scope="row">
		        		#parse("app/utils/record_paging.jsp")
		            	</td>
		            </tr>
		            <tr class="table_header">
				        <td width="3%"><b>Bil</b></td>
				        <td width="20%"><b>Tajuk Dokumen</b></td>
				        <td width="23%"></td>
		            </tr>
	          		#set ($list = "")
		          		#if ($snriRujLam.size() > 0)
		          			#foreach ($list in $snriRujLam)
					            #if ($list.bil == '')
					                #set( $row = "row1" )
					            #elseif (($list.bil % 2) != 0)
					                #set( $row = "row1" )
					            #else 
					                #set( $row = "row2" )
					            #end
					          	<tr>
					            	<td class="$row" >$list.bil.</td>
					            	<td class="$row" >$list.perihal</td>
					            	<td class="$row" >
					            	<input name="cmdPapar" type="button" value="Papar" onclick="javascript:papar('$list.id')"/>
					            	<input name="cmdHapus" type="button" value="Hapus" onclick="javascript:hapus('$list.id')"/>
					            	</td>
					          	</tr>
	          				#end
	          			#else
		          	<tr>
		            	<td class="row1" >&nbsp;</td>
		            	<td class="row1" align="left" colspan="4">Tiada Rekod</td>
		          	</tr>
          		#end
        		</table>
        		<input name="backButton" type="button" id="backButton" value="Kembali" onclick="javascript:kembali()"/>
		</fieldset>
		<input name="id" type="hidden" id="id" value="$!id"/>
		<input name="idTblRujLamp" type="hidden" id="idTblRujLamp" value="$!idTblRujLamp"/>
		<input name="hitButton" type="hidden" id="hitButton" value="$!hitButton"/>
		<input name="txtNamaDokumenHide" type="hidden" id="txtNamaDokumenHide" value="$!txtNamaDokumenHide"/>
		<script>

function search() {
	document.${formName}.hitButton.value = "cari";
	document.${formName}.submit();
}

function papar(id){
	var url = "../servlet/ekptg.view.pdt.FrmTmbhSnriDokDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hapus(id){
	document.${formName}.hitButton.value = "hapus";
	document.${formName}.idTblRujLamp.value = id;
	var x = create_request_string(document.${formName});
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmSenaraiDokumen&"+x;
	document.${formName}.submit();
}

function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmSenaraiDokumen";
	document.${formName}.submit();
}

function tambah(id) {
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmSenaraiDokumen?hitButton=saveRujLamp&id="+id+"";
    var hWnd = window.open(url,'Cetak','width=800,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function tmbhSnriDok(id) {
	document.${formName}.hitButton.value = "viewList";
	document.${formName}.id.value = id;
	var x = create_request_string(document.${formName});
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmSenaraiDokumen&"+x;
	document.${formName}.submit();
}
</script>