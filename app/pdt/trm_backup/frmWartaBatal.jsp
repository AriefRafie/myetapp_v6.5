<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="mode" value="$!mode">
	<input type="hidden" name="step" value="$!step">
	<input type="hidden" id="pageDoChange" name="pageDoChange" value="$!pageDoChange">
	<input type="hidden" id="uploadMethod" name="uploadMethod">
	<input type="hidden" name="idWartaBatal" value="$!idWartaBatal">
	<input type="hidden" name="idPelanBatal" value="$!idPelanBatal">
	<input type="hidden" name="idTnhRzbMlyBatal" value="$!idTnhRzbMlyBatal">
</p>
	<table width="100%">
	<tr>
		<td width="29%" align="right" scope="row">No. Warta</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
		<td id="noWarta">$noWarta</td>
		#end #if ($mode != 'view')
		<td>
			<input name="noWarta" type="text" id="noWarta" value="$noWarta" size="10" />
		</td>
		#end
	</tr>
	<tr>
		#if ($mode == 'view')
		<td width="10" valign="top">
			<div align="right"></div>
		</td>
		<td width="1%" scope="row"></td>
		<td id="paparWarta">
			#if($kandunganWarta == 'true')
				<a href="javascript:onClick=paparWarta('$idWartaBatal');" style="color: #0000FF">[Papar Warta]</a>
			#end 
		</td>
		</td>
		#end 
		#if ($mode == 'upload')
		<td width="29%" align="right" scope="row">Muatnaik Warta</td>
		<td width="1%" scope="row">:</td>
		<td id="uploadWarta">
			<a href="javascript:uploadImej('warta')" style="color: #0000FF" >[Muatnaik Warta]</a>
		</td>
		#end
		#if ($mode == 'tambahBaru')
			#if($kandunganWarta == 'true')
				#if($idWartaBatal != '')
				<td width="29%" align="right" scope="row">Muatnaik Warta</td>
				<td width="1%" scope="row">:</td>
				<td id="uploadWarta">
					<a href="javascript:onClick=paparWarta('$idWartaBatal');" style="color: #0000FF">[Papar Warta]</a>
				</td>
				#end
			#else
				<td width="29%" align="right" scope="row">Muatnaik Warta</td>
				<td width="1%" scope="row">:</td>
				<td id="uploadWarta">
					<a href="javascript:uploadImej('warta')" style="color: #0000FF" >[Muatnaik Warta]</a>
				</td>		 
			#end 
		#end
	</tr>
	<tr>
		<td width="29%" align="right" scope="row">Tarikh</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
		<td>
			$tarikhWarta<a href="javascript:displayDatePicker('paramTarikh',false,'dmy');"></a>
		</td>
		#end #if ($mode != 'view')
		<td>
			<input name="tarikhWarta" type="text" id="tarikhWarta" value="$tarikhWarta" size="10" />
			<a href="javascript:displayDatePicker('tarikhWarta',false,'dmy');"><img border="0"
				src="../img/calendar.gif" /></a>
		</td>
		#end
	</tr>
	<tr>
		<td width="29%" align="right" scope="row">No. Pelan</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
		<td id="noPelan">$noPelan</td>
		#end #if ($mode != 'view')
		<td>
			<input name="noPelan" type="text" id="noPelan" value="$noPelan" size="10" />
		</td>
		#end
	</tr>
		<tr>
		#if ($mode == 'view')
		<td width="10" valign="top">
			<div align="right"></div>
		</td>
		<td width="1%" scope="row"></td>
		<td id="paparPelan">
			#if($kandunganPelan == 'true')
				<a href="javascript:onClick=paparPelan('$idPelanBatal');" style="color: #0000FF">[Papar Pelan]</a>
			#end
		</td>
		#end 
		#if ($mode == 'upload')
		<td width="29%" align="right" scope="row">Muatnaik Pelan</td>
		<td width="1%" scope="row">:</td>
		<td id="uploadPelan">
			<a href="javascript:onClick=uploadImej('pelan');" style="color: #0000FF">[Muatnaik Pelan]</a>
		</td>
		#end
		#if ($mode == 'tambahBaru')
			#if($kandunganPelan == 'true')
				#if($idPelanBatal != '')
				<td width="29%" align="right" scope="row">Muatnaik Pelan</td>
				<td width="1%" scope="row">:</td>
				<td id="uploadPelan">
					<a href="javascript:onClick=paparPelan('$idPelanBatal');" style="color: #0000FF">[Papar Pelan]</a>
				</td>
				#end
			#else
					<td width="29%" align="right" scope="row">Muatnaik Pelan</td>
					<td width="1%" scope="row">:</td>
					<td id="uploadPelan">
						<a href="javascript:onClick=uploadImej('pelan');" style="color: #0000FF">[Muatnaik Pelan]</a>
					</td>		
			#end
		#end
	</tr>
	<tr>
		<td width="29%" align="right" scope="row">Luas(hektar)</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
			<td id="luas">$luas</td>
		#end 
		#if ($mode != 'view')
			<td>
				<input name="luas" type="text" id="luas" value="$luas" size="10" />
			</td>
		#end
	</tr>
		<td align="right" scope="row">&nbsp;</td>
		<td scope="row">&nbsp;</td>
		<td>
			#if ($mode =='view')
			<input type="button" name="cmdKemaskini" id="cmdKemaskini" style="float: center;" value="Kemaskini" onclick="javascript:kemaskinibatal()" />
			#end #if ($mode != 'view')
			<input type="button" name="cmdSimpan" value="Simpan" onClick="javascript:simpan()" />
			#end
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
		</td>
	</tr>
	</table>
<div style="visibility: hidden;">
	<!-- 	<input type="hidden" name="mode" /> <input type="hidden" name="action" /> -->
</div>

<script type="text/javascript">

function uploadImej(step){
	document.${formName}.uploadMethod.value="uploadBatal";
	document.${formName}.step.value=step;
	document.${formName}.hitButton.value="openPageUpload";
	var x = create_request_string(document.${formName});
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM&"+x;
	document.${formName}.submit();
}

function paparWarta(id){
	var url = "../servlet/ekptg.view.pdt.TblPdtWartaViewBlob?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function paparPelan(id){
	var url = "../servlet/ekptg.view.pdt.TblPdtPelanViewBlob?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function simpan(){
	document.${formName}.method.value="post";
	document.${formName}.hitButton.value="saveWartaBatal";
	document.${formName}.submit();
}


function isisemula() {
	document.${formName}.reset();
	document.${formName}.paramNegeri.value = "";
	document.${formName}.paramDaerah.value = "";
	document.${formName}.paramMukim.value = "";

	document.${formName}.paramLuas.value = "";
	document.${formName}.paramStatus.value = "";
	document.${formName}.paramNoWartaWujud.value = "";
	document.${formName}.paramTarikhWujud.value = "";
	document.${formName}.paramNoPelanWujud.value = "";
	
	document.${formName}.paramNoWartaBatal.value = "";
	document.${formName}.paramTarikhBatal.value = "";
	document.${formName}.paramNoPelanBatal.value = "";
	document.${formName}.paramKawasan.value = "";
	document.${formName}.hitButton.value = "Isi Semula";
	doAjaxCall${formName}("doIsiSemula");
}


/* function kembali() {	
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM&action=kembali";
	document.${formName}.method="POST";
	document.${formName}.hitButton.value = "kembali";
	document.${formName}.submit();
}
 */
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
</script>

