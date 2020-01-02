<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="mode" value="$!mode">
	<input type="hidden" name="step" value="$!step">
	<input type="hidden" id="pageDoChange" name="pageDoChange" value="$!pageDoChange">
	<input type="hidden" id="uploadMethod" name="uploadMethod">
	<input type="hidden" name="idWartaGanti" value="$!idWartaGanti">
	<input type="hidden" name="idPelanGanti" value="$!idPelanGanti">
	<input type="hidden" name="idTnhRzbMlyGanti" value="$!idTnhRzbMlyGanti">
</p>
<table width="100%">
	<tr>
		<td width="10" valign="top">
			<div align="right">Negeri</div>
		</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
		<td id="namaNegeri">$namaNegeri</td>
		#end #if ($mode != 'view')
		<td id="idNegeri">$selectNegeri</td>
		#end
	</tr>
	<tr>
		<td width="29%" align="right" scope="row">Daerah/Jajahan</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
		<td width="70%" id="namaDaerah">$namaDaerah</td>
		#end #if ($mode != 'view')
		<td id="namaDaerah">$selectDaerah</td>
		#end
	</tr>
	<tr>
		<td width="29%" align="right" scope="row">Mukim</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
		<td id="namaMukim ">$namaMukim</td>
		#end #if ($mode != 'view')
		<td width="70%" id="idMukim">$selectMukim</td>
		#end
	</tr>
	<tr>
		<td width="29%" align="right" scope="row">Kawasan</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
			<td id="kawasan">$kawasan</td>
		#end 
		#if ($mode != 'view')
		<td>
			<input name="kawasan" type="text" id="kawasan" value="$!kawasan" size="37" />
		</td>
		#end
	</tr>
	<tr>
		<td width="29%" align="right" scope="row">No. Warta</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
		<td id="noWarta">$noWarta</td>
		#end #if ($mode != 'view')
		<td>
			<input name="noWarta" type="text" id="noWarta" value="$!noWarta" size="10" />
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
				<a href="javascript:onClick=paparWarta('$idWartaWujud');" style="color: #0000FF">[Papar Warta]</a>
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
				#if($idWartaGanti != '')
					<td width="29%" align="right" scope="row">Muatnaik Warta</td>
					<td width="1%" scope="row">:</td>
					<td id="uploadWarta">
						<a href="javascript:onClick=paparWarta('$idWartaWujud');" style="color: #0000FF">[Papar Warta]</a>
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
			<input name="tarikhWarta" type="text" id="tarikhWarta" value="$!tarikhWarta" size="10" />
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
			<input name="noPelan" type="text" id="noPelan" value="$!noPelan" size="10" />
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
				<a href="javascript:onClick=paparPelan('$idPelanWujud');" style="color: #0000FF">[Papar Pelan]</a>
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
				#if($idPelanGanti != '')
				<td width="29%" align="right" scope="row">Muatnaik Pelan</td>
				<td width="1%" scope="row">:</td>
				<td id="uploadPelan">
					<a href="javascript:onClick=paparPelan('$idPelanWujud');" style="color: #0000FF">[Papar Pelan]</a>
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
				<input name="luas" type="text" id="luas" value="$!luas" size="10" />
			</td>
		#end
	</tr>
	<tr>
		<td width="29%" align="right" scope="row">Status</td>
		<td width="1%" scope="row">:</td>
		#if ($mode == 'view')
			#if ($flagStatusWarta == 'T')
				<td width="20%">Tidak Kuatkuasa
				<input type="hidden" name="paramStatus" value="$!flagStatusWarta">
				</td>
			#end 
			#if ($flagStatusWarta == 'K')
				<td width="20%">Kuatkuasa
				<input type="hidden" name="paramStatus" value="$!flagStatusWarta">
				</td>
				
			#end		
		#end	
			
		#if ($mode != 'view')
			<td>$paramStatus</td>
		#end 


	</tr>
	
	
	<tr>
		<td align="right" scope="row">&nbsp;</td>
		<td scope="row">&nbsp;</td>
		<td>
			#if ($mode == 'view')
				<input type="button" name="cmdKemaskini" id="cmdKemaskini" style="float: center;" value="Kemaskini" onclick="kemaskiniganti()" />
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

function uploadImej(step){
	document.${formName}.uploadMethod.value="uploadGanti";
	document.${formName}.step.value=step;
	document.${formName}.hitButton.value="openPageUpload";
	var x = create_request_string(document.${formName});
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM&"+x;
	document.${formName}.submit();
}

function simpan(){
	document.${formName}.method.value="post";
	document.${formName}.hitButton.value="saveWartaGanti";
	document.${formName}.submit();
}

function doChangeNegeri(){
	document.${formName}.pageDoChange.value = "wartaWujud";
	doAjaxCall${formName}("doFilter");	
}

function doChangeDaerah(){
	document.${formName}.pageDoChange.value = "wartaWujud";
	doAjaxCall${formName}("doFilter");
}

</script>