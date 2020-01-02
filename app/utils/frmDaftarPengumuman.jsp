<style type="text/css">

#marquee_replacement_td {
    border-bottom: 1px solid #C7A317;
    overflow: auto;
    width: 100%;
	margin-bottom: 10px;
	
}

.row:hover {
	background-color:#F3F781;
}

.row{

}

.rowMiddle{
	border-bottom:dotted 1px #669999;
	border-top:none;
}

.rowMiddle:hover {
	background-color:#F3F781;
}

nav{
	border-top:3px solid #C7A317;
	border-left:none;
	border-right:none;
	border-bottom:3px solid #C7A317;
}

table.paparan{
	border-top:none;
	border-left:none;
	border-right:none;
	border-bottom:none;
	height: 350;
}

.tooltip {
	position: absolute!important;
	overflow:hidden;
	font-size: 12px;
	z-index: 10000!important;
}

.tooltip .xtop, .tooltip .xbottom { background: transparent; font-size: 0px; }
.tooltip .xb1, .tooltip .xb2, .tooltip .xb3, .tooltip .xb4 { display: block; overflow: hidden; }
.tooltip .xb1, .tooltip .xb2, .tooltip .xb3 { height: 0px; }
.tooltip .xb2, .tooltip .xb3, .tooltip .xb4 { background: #666;}
.tooltip .xbottom .xb2, .tooltip .xbottom .xb3, .tooltip .xbottom .xb4 { background: #666; }
.tooltip .xb1 { margin: 0 0px; }
.tooltip .xb2 { margin: 0 0px; }
.tooltip .xb3 { margin: 0 0px; }
.tooltip .xb4 { height: 0px;}

.tooltip .xboxcontent {
	padding: 0 .5em;
	margin: 0;
	color: #000;
	word-wrap:break-word;
	border: none;
	background-color:white;
	font-family: Verdana;
}
	
	

a.nav:hover {
	color: black;
	background-color: white;
	text-decoration: underline overline black;
}

a.nav:link{
	link:#333333;
}

a.nav:visited {color: #333333; text-decoration: none; }	

#marquee_replacement {
    border: 3px solid #C7A317;
    height: 300px;
    margin-bottom: 10px;
    overflow: auto;
    width: 100%;
}

.marquee_replacement_td {
    border-bottom: 1px solid #C7A317;
    overflow: auto;
    width: 100%;
	margin-bottom: 10px;
}
</style>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_memo" value="$!id_memo" id="id_memo" />

<table id="main_table" width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td colspan="2">
			<fieldset>
			<legend><strong>DAFTAR MEMO</strong></legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td width="2%" align="right" valign="top"><font color="red">*</font></td>
						<td align="left" width="20%" valign="top">Memo</td>
						<td width="1%" valign="top">:</td>
						<td width="44%" valign="top">
							<textarea name="mesej" id="mesej">$!mesej</textarea>
							<script>
								var area = new FCKeditor("mesej");
								area.BasePath = '/${appname}/library/fck/' ;
								area.ToolbarSet = 'Pengumuman';
								area.Height = 300;
								area.Width = 450;
								area.ReplaceTextarea();            	
							</script>
							<div id='word_count'></div> 
							<br>
							<font color="blue">Sila Masukkan Maklumat Sambungan di Ruangan Ini</font>
							<textarea name="submesej" id="submesej">$!submesej</textarea>
							<script>
								var area = new FCKeditor("submesej");
								area.BasePath = '/${appname}/library/fck/' ;
								area.ToolbarSet = 'Pengumuman';
								area.Height = 300;
								area.Width = 450;
								area.ReplaceTextarea();            	
							</script>
							<div id='sub_word_count'></div> 
						</td>
						<td width="33%" valign="top">
							<fieldset>
							<legend><i>Preview</i></legend>
								<table width="100%"  class="paparan"  >
									<tr>
										<td width="12%" align="center" valign="top"><img width="50" height="50" src="../img/online/kblogger.png" align="center"/></td>
										<td width="88%" valign="top">
											<table width="100%">
												<tr>
													<td valign="middle"><b>Memo</b></td>
												</tr>
												<tr>
													<td valign="top" >
													#if($submesej != "" || $mesej != "")
														<marquee onmouseover='this.stop();' onmouseout='this.start();' height="220"  direction="up"  scrollamount="2">
														<div id="marquee_replacement_td" valign="top" style="width: auto">
															<span>$mesej</span>
															#if($submesej != "")
															#set($div_memo = "div_memo")
															<span style="display:none" >
																<span id="$div_memo">
																	$submesej
																</span>
															</span>
														<a href="javascript:open_new_window('$!div_memo')">
															<font color="blue">
																<u>Selanjutnya...</u>
															</font>
														</a>
														<br>
														<br>
															#end
														</div>
														<br>
														</marquee>
													#end
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td valign="top" align="right"></td>
						<td align="left" valign="top"></td>
						<td valign="top"></td>
						<td valign="top">
							<i><font color="red" size="">Perhatian</font> : Sila pastikan mesej yang dimasukkan tidak melebih <b>30000</b> aksara (termasuk 'HTML codes').<br />
								Dilarang 'copy paste' maklumat atau imej dari web site luar untuk mengelakkan ralat berlaku.
							</i>
						</td>
						<td></td>
					</tr>
					<tr>
						<td valign="top" align="right"><font color="red">*</font></td>
						<td align="left" valign="top">Status Memo</td>
						<td valign="top">:</td>
						<td valign="top" colspan="2">
							#set($array_aktiftidak = ["Aktif","Tidak Aktif"])            
							<select class="autoselect" name="status_mesej" id="status_mesej">
							<option value="" $selected_aktiftidak >Sila Pilih Status</option>
							#foreach ( $y in $array_aktiftidak )
								#if( $y == $!status_mesej )
									#set ( $selected_aktiftidak = "selected" )
								#else
									#set ( $selected_aktiftidak = "" )
								#end                       
								<option value="$y" $selected_aktiftidak >$y</option>
							#end
							</select>
						</td>
					</tr>
					<tr>
						<td valign="top" align="right"><font color="red">*</font></td>
						<td align="left" valign="top">Turutan Memo</td>
						<td valign="top">:</td>
						<td valign="top" colspan="2">
							#if($list_memo_aktif.size() == 0)
								#set($size = 1)
							#else
								#set($size = $list_memo_aktif.size()+1)
							#end 
							#set($start = 1)
							#set($range = [$start..$size])
								<select  class="autoselect" name="turutan_mesej" id="turutan_mesej">
								<option value="" $selected_turutan >Sila Pilih Turutan</option>
							#foreach ( $y in $range)
								#if( $y == $!turutan_mesej )
									#set ( $selected_turutan = "selected" )
								#else
									#set ( $selected_turutan = "" )
								#end                       
								<option value="$y" $selected_turutan >$y</option>
							#end
						</select>
						</td>
					</tr>
					<tr>
						<td valign="top" align="right"><font color="red">*</font></td>	
						<td align="left" valign="top">
							Pilihan bagi pengguna yang boleh melihat pengumuman
						</td>
						<td valign="top">:</td>
						<td colspan="2">
							<table>
								<tr>
									<td>
									#if ( $flag_role_internal == "1" ) 
										#set ( $checkbox = "checked")
									#else
										#set ( $checkbox = "")
									#end
										<input type="checkbox" name="cbinternalInt" value="1" $checkbox>
									</td>
									<td>Pengguna Dalaman</td>
                                    </tr>
                                    <tr>
                                    <td colspan="2">Penerangan : Pengguna JKPTG HQ dan Negeri</td>
									</tr>
								<tr>
									<td>
									#if ( $flag_role_internalHQ == "1" ) 
										#set ( $checkbox = "checked")
									#else
										#set ( $checkbox = "")
									#end
										<input type="checkbox" name="cbinternalHQ" value="1" $checkbox>		
									</td>
									<td>Pengguna HQ</td>
                                     </tr>
                                    <tr>
                                    <td colspan="2">Penerangan : Pengguna JKPTG HQ sahaja</td>
								</tr>
                                
                                <tr>
									<td>
									#if ( $flag_role_internalNeg == "1" ) 
										#set ( $checkbox = "checked")
									#else
										#set ( $checkbox = "")
									#end
										<input type="checkbox" name="cbinternalNeg" value="1" $checkbox>		
									</td>
									<td>Pengguna Negeri</td>
                                    </tr>
                                    <tr>
                                    <td colspan="2">Penerangan : Pengguna JKPTG Negeri sahaja</td>
								</tr>
                                
                                <tr>
									<td>
									#if ( $flag_role_internalKJP == "1" ) 
										#set ( $checkbox = "checked")
									#else
										#set ( $checkbox = "")
									#end
										<input type="checkbox" name="cbinternalKJP" value="1" $checkbox>		
									</td>
									<td>Pengguna KJP</td>
                                    </tr>
                                    <tr>
                                    <td colspan="2">Penerangan : Pengguna dari Kementerian dan Jabatan/ Agensi</td>
								</tr>
                                
                                <tr>
									<td>
									#if ( $flag_role_internalInteg == "1" ) 
										#set ( $checkbox = "checked")
									#else
										#set ( $checkbox = "")
									#end
										<input type="checkbox" name="cbinternalInteg" value="1" $checkbox>		
									</td>
									<td>Pengguna Integrasi Dalaman</td>
                                     </tr>
                                    <tr>
                                    <td colspan="2">Penerangan : Pengguna luar yang menggunakan sistem MyeTaPP sama ada agensi kerajaan atau swasta</td>
								</tr>
                                
                                <tr>
									<td>
									#if ( $flag_role_internalOnline == "1" ) 
										#set ( $checkbox = "checked")
									#else
										#set ( $checkbox = "")
									#end
										<input type="checkbox" name="cbinternalOnline" value="1" $checkbox>		
									</td>
									<td>Pengguna JKPTG Online</td>
                                     </tr>
                                    <tr>
                                    <td colspan="2">Penerangan : Pengguna Awam yang menggunakan perkhidmatan online JKPTG</td>
								</tr>  
							</table>
						</td>
					</tr>
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td align="center">
			<input type="button" name="cmdSimpanMesej"  id="cmdSimpanMesej" value="Simpan" onClick="javascript:daftarMemo()" />
			#if($!id_memo != "")
				<input type="button" name="cmdhapusMesej"  id="cmdhapusMesej" value="Hapus" onClick="javascript:hapusMemo()" />
			#end
			<input type="button" name="cmdReset"  id="cmdReset" value="Kosongkan" onClick="javascript:kosongkanMemo()" />
		</td>
	</tr>
</table>

<!------------------------------------------Part 1 Senarai Memo (AKTIF)------------------------------------------>

#if($list_memo_aktif.size()>0)
	<br>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td>
				<fieldset>
					<legend><b>SENARAI MEMO (<font color="blue"><blink>AKTIF</blink></font>)</b></legend>
					<table cellpadding="2" cellspacing="1" border="0" width="100%"  align="center">
						<tr class="table_header">
							<td scope="row" width="3%" align="center"><strong>No</strong></td>
							<td width="69%"><strong>Mesej</strong></td>
							<td width="15%"><strong>Status Memo</strong></td>          
							<td width="10%"  align="center"><strong>Turutan Memo</strong></td>         
						</tr>
						#if($list_memo_aktif.size()>0)
							#set ($count = 0)
							#foreach ( $fail in $list_memo_aktif )
								#set ($count = $count+1)
								#set( $i = $velocityCount )
								#if ( ($i % 2) != 1 )
									#set( $row = "row2" )
								#else
									#set( $row = "row1" )
								#end
								<tr>
									<td align="center" class="$row" valign="top">$!count</td>
									<td valign="top" class="$row" >
										<a href="javascript:paparMemo('$fail.id_memo')" class="style1" ><font color="BLUE">Papar Mesej</font></a>
										<br />
										<br />
										<div style="height:100px; overflow-y:scroll; overflow-x:hidden">$fail.mesej</div>
										&nbsp;
									</td>
									<td valign="top" class="$row">$fail.status_mesej</td>         
									<td valign="top" class="$row">$fail.turutan_mesej</td>
									<td valign="top" class="$row">$fail.turutan_mesej</td>
								</tr>
							#end
						#else
							<tr>  
								<td colspan="8">Tiada Rekod</td>    
							</tr>
						#end
					</table>
				</fieldset>
			</td>
		</tr>
	</table>
#end

<!------------------------------------------Part 2 Senarai Memo (Tidak Aktif)------------------------------------>

#if($list_memo_xaktif.size()>0)
	<br>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td>
				<fieldset>
					<legend><b>SENARAI MEMO (<font color="red"><blink>TIDAK AKTIF</blink></font>)</b></legend>
					<table cellpadding="2" cellspacing="1" border="0" width="100%"  align="center">
						<tr class="table_header">
							<td scope="row" width="3%" align="center"><strong>No</strong></td>
							<td width="69%"><strong>Mesej</strong></td>
							<td width="15%"><strong>Status Memo</strong></td>          
							<td width="10%"  align="center"><strong>Turutan Memo</strong></td>         
						</tr>
						#if($list_memo_xaktif.size()>0)
							#set ($count = 0)
							#foreach ( $fail in $list_memo_xaktif )
								#set ($count = $count+1)
								#set( $i = $velocityCount )
								#if ( ($i % 2) != 1 )
									#set( $row = "row2" )
								#else
									#set( $row = "row1" )
								#end
								<tr>
									<td align="center" class="$row" valign="top">$!count</td>
									<td valign="top" class="$row" >
										<a href="javascript:paparMemo('$fail.id_memo')" class="style1" ><font color="BLUE">Papar Mesej</font></a>
										<br />
										<br />
										<div style="height:100px; overflow-y:scroll; overflow-x:hidden">$fail.mesej</div>
										&nbsp;
									</td>
									<td valign="top" class="$row">$fail.status_mesej</td>         
									<td valign="top" class="$row">$fail.turutan_mesej</td>
								</tr>
							#end
						#else
							<tr>  
								<td colspan="8">Tiada Rekod</td>    
							</tr>
						#end
					</table>
				</fieldset>
			</td>
		</tr>
	</table>
#end

<!----------------------------------------------------Script-------------------------------------------------->
<script>
window.location.hash='main_table';

function open_new_window(div_memo) {
	var width  = 450;
	var height = 300;
	var left   = (screen.width  - width)/2;
	var top    = (screen.height - height)/2;
	
	myDiv_label = document.getElementById(div_memo);
	var params = 'width='+width+', height='+height;
	params += ', top='+top+', left='+left;
	params += ', directories=no';
	params += ', location=front';
	params += ', menubar=no';
	params += ', resizable=no';
	//params += ', scrollbars=no';
	params += ', status=no';
	params += ', toolbar=no'; 
	new_window = open("","title",params);
	new_window.document.open();

	new_window.document.write("<html><title>Paparan Selanjutnya</title>");
	new_window.document.write("<body bgcolor=\"#FFFFFF\">");
	new_window.document.write(myDiv_label.innerHTML);
	new_window.document.write("<br>");
	new_window.document.write("</body></html>");
	new_window.document.close(); 
}

function close_window() {
	new_window.close();
}

function paparMemo(id_memo) {
	document.${formName}.id_memo.value = id_memo;
	document.${formName}.command.value = "paparMemo";
	document.${formName}.action = "?_portal_module=ekptg.view.admin.Pengumuman";
	document.${formName}.submit();		
}
		
function daftarMemo() {
	var editorInstance = FCKeditorAPI.GetInstance('mesej');	
	var text = editorInstance.GetHTML(true);
	var textlength = text.length;
			
	if(textlength==0) {
		alert("Sila Masukkan Mesej");
		oEditor.EditorDocument.body.focus();
		return;
	}
	else if(document.${formName}.status_mesej.value==""){
		alert('Sila Pilih Status Mesej.');
		return;
	}
	else if(document.${formName}.turutan_mesej.value==""){
		alert('Sila Pilih Turutan Mesej.');
		return;
	}
	else
	{
		input_box = confirm("Adakah anda pasti?");
	
		if (input_box == true) {
			document.${formName}.command.value = "simpanMemo";
			document.${formName}.action = "?_portal_module=ekptg.view.admin.Pengumuman";
			document.${formName}.cmdSimpanMesej.value = "Sila Tunggu....";	
			document.${formName}.submit();
		}
	}
}
	
function kosongkanMemo() {
	document.${formName}.command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.admin.Pengumuman";		
	document.${formName}.submit();
}
	
function hapusMemo() {
	input_box = confirm("Adakah anda pasti?");
	
	if (input_box == true) {
		document.${formName}.command.value = "hapusMemo";
		document.${formName}.action = "?_portal_module=ekptg.view.admin.Pengumuman";
		document.${formName}.cmdhapusMesej.value = "Sila Tunggu....";			
		document.${formName}.submit();
	}
}
	
function FCKeditor_OnComplete( editorInstance ){
	if (editorInstance.Name == "mesej" ) {		
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count);			
	}
	
	if (editorInstance.Name == "submesej" ) {		
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_sub_word_count);			
	}	

}
		
function fckeditor_word_count() {
	var editorInstance = FCKeditorAPI.GetInstance('mesej');	
	var count = editorInstance.GetHTML().length;
	
	document.getElementById('word_count').innerHTML = (30000-count) + " <font color = 'blue'>Baki Aksara</font>";
	  
	if (count > 30000) {
		editorInstance.EditorDocument.body.contentEditable='false';
		editorInstance.EditorDocument.designMode='off';
	}
}

function fckeditor_sub_word_count() {
	var editorInstance = FCKeditorAPI.GetInstance('submesej');	
	var count = editorInstance.GetHTML().length;
	
	document.getElementById('sub_word_count').innerHTML = (30000-count) + " <font color = 'blue'>Baki Aksara</font>";
  
	if (count > 30000) {
		editorInstance.EditorDocument.body.contentEditable='false';
		editorInstance.EditorDocument.designMode='off';
	}
}
</script>