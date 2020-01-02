<br />
<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>PANDANGAN UNDANG-UNDANG</strong></td>
</tr>
</table>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>

<fieldset>
	<legend>
		<strong>Senarai Dokumen</strong>
	</legend>
	<table width="100%">
		<tr>
			<td width="29%" align="right" scope="row">Nama Dokumen</td>
			<td width="1%" scope="row">:</td>
			<td width="70%"><input name="txtNamaDokumen" type="text"
				id="txtNamaDokumen" value="$!txtNamaDokumen" /></td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td>
			<input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="search()" /> 
			<input type="reset" name="cmdKosongkanAkta" value="Kosongkan"/>
			<input type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambahBaru()" />
			</td>
		</tr>
	</table>
</fieldset>
	#foreach ($result in $senaraiDokumenInduk ) 
		<fieldset>
			<legend>
				<a href="javascript:hideShow('$result.id')"><strong>$result.bil. $result.perihal</strong></a>
				<a href="javascript:edit('$result.id','$result.perihal')" style="color: blue">[Edit] </a>
				<a href="javascript:hapus('$result.id')" style="color: blue">[Hapus]</a>
			</legend>
			<div id="$result.id" hidden="true" >
			<table align="center" width="100%" > 
		            <tr>
		              <td colspan="5" scope="row">
		              	<input name="cmdTambah" type="button" value="Tambah" onclick="javascript:tambah('$result.id')"/>
		              </td>
		            </tr>
		            <tr>
<!-- 		              	<td colspan="5" scope="row"> -->
<!-- 		        		#parse("app/utils/record_paging.jsp") -->
<!-- 		            	</td> -->
		            </tr>
		            <tr class="table_header">
				        <td width="3%"><b>Bil</b></td>
				        <td width="20%"><b>Tajuk Dokumen</b></td>
				        <td width="23%"></td>
		            </tr>
		            <!--layer 1 -->
	          		#set ($list = "")
		          		#if ($result.snriChild.size() > 0)
		          			#foreach ($list in $result.snriChild)
					            #if ($list.bil == '')
					                #set( $row = "row1" )
					            #elseif (($list.bil % 2) != 0)
					                #set( $row = "row1" )
					            #else 
					                #set( $row = "row2" )
					            #end
					          	<tr>
					            	<td class="$row" >$list.bil.</td>
					            	<td class="$row"><a href="#" style="color: blue" onclick="javascript:tmbhSnriDok('$list.id')">$list.perihal</a></td>
					            	<td class="$row" ><input name="cmdHapus" type="button" value="Hapus" onclick="javascript:hapus('$list.id')"/>
					            		<input name="cmdTambah" type="button" value="Tambah" onclick="javascript:tambah('$list.id')"/></td>
					          	</tr>
								<!--layer 2 -->
						          		#set ($list1 = "")
						          		#if ($list.snriChild1.size() > 0)
						          			#foreach ($list1 in $list.snriChild1)
									            #if ($list1.bil == '')
									                #set( $row = "row1" )
									            #elseif (($list1.bil % 2) != 0)
									                #set( $row = "row1" )
									            #else 
									                #set( $row = "row2" )
									            #end
									          	<tr>
									            	<td class="$row" ><!-- $list.bil.$list1.bil --></td>
									            	<td class="$row" style="padding-left:2em"><a href="#" style="color: blue"  onfocus="javascript:tmbhSnriDok('$list1.id')" onclick="javascript:tmbhSnriDok('$list1.id')">$list1.perihal</a></td>
									            	<td class="$row" ><input name="cmdHapus" type="button" value="Hapus" onclick="javascript:hapus('$list1.id')"/>
									            		<input name="cmdTambah" type="button" value="Tambah" onclick="javascript:tambah('$list1.id')"/></td>
									          	</tr>
								          		<!--layer 3 -->
										          		#set ($list2 = "")
										          		#if ($list1.snriChild1.size() > 0)
										          			#foreach ($list2 in $list1.snriChild1)
													            #if ($list1.bil == '')
													                #set( $row = "row1" )
													            #elseif (($list1.bil % 2) != 0)
													                #set( $row = "row1" )
													            #else 
													                #set( $row = "row2" )
													            #end
													          	<tr>
													            	<td class="$row" ><!-- $list.bil.$list1.bil.$list2.bil --></td>
													            	<td class="$row" style="padding-left:4em"><a href="#" style="color: blue" onclick="javascript:tmbhSnriDok('$list2.id')">$list2.perihal</a></td>
													            	<td class="$row" ><input name="cmdHapus" type="button" value="Hapus" onclick="javascript:hapus('$list2.id')"/>
<!-- 													            		<input name="cmdTambah" type="button" value="Tambah" onclick="javascript:tambah('$list2.id')"/></td> -->
													          	</tr>
													          		
									          				#end
									          			#else
										          		#end
										        <!--end layer 3 -->  		
					          				#end
					          			#else
						          		#end
						        <!--end layer 2 -->  		
	          				#end
	          			#else
	          			 <!--end layer 1 -->
		          	<tr>
		            	<td class="row1" >&nbsp;</td>
		            	<td class="row1" align="left" colspan="4">Tiada Rekod</td>
		          	</tr>
          		#end
        		</table>
        		</div>
		</fieldset>
	#end
<input name="hitButton" type="hidden" id="hitButton" value="$!hitButton"/>
<input name="txtNamaDokumenHide" type="hidden" id="txtNamaDokumenHide" value="$!txtNamaDokumenHide"/>
<input name="id" type="hidden" id="id" value="$!id"/>
<script>

function search() {
	document.${formName}.hitButton.value = "cari";
	document.${formName}.submit();
}

function tambah(id) {
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahDokumen?hitButton=addChild&id="+id+"";
    var hWnd = window.open(url,'Cetak','width=800,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus(); 
}

function tambahBaru() {
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahDokumen?hitButton=addInduk";
    var hWnd = window.open(url,'Cetak','width=800,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hapus(id) {
	document.${formName}.id.value = id;
	document.${formName}.hitButton.value = "delete";
	document.${formName}.submit();
}

function hideShow(id) {
	var att = document.getElementById(id).getAttribute('hidden');
	if(att == null){
		 var a =  document.getElementById(id);
		 a.setAttribute('hidden', true);
	}else{
		var a =  document.getElementById(id);
		a.removeAttribute('hidden');
	}
}

function edit(id,tajuk) {
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahDokumen?hitButton=editInduk&id="+id+"&tajuk="+tajuk;
    var hWnd = window.open(url,'Cetak','width=800,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteInduk(id) {
	alert(id);
}

function tmbhSnriDok(id) {
	document.${formName}.hitButton.value = "viewList";
	document.${formName}.id.value = id;
	document.${formName}.submit();
}
</script>