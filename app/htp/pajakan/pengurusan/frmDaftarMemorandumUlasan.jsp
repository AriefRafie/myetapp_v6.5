<style type="text/css">
<!--
.style1 {color: #FF0000}
tr.three {
    border-style: solid;
    border-width: 1px;
}
-->
</style>
<p>&nbsp;</p>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
</p>
<table width="100%" border="0" >
	<tr>
		<td>
			&nbsp;
		</td>
    </tr>
	<tr>
		<td>
    		<fieldset><legend><b>MAKLUMAT MEMORANDUM JEMAAH MENTERI</b></legend>
	        	<table width="50%" align="left" border="0">
				  	<tr>
		            	<td width="29%" scope="row" align="right">No. Memorandum</td>
		            	<td width="1%">:</td>	            	
		              	<td width="70%"><input name="txtNoMemo" id="txtNoMemo" type="text" value="$!txtNoMemo" size="43" maxlength="50" onBlur="this.value=this.value.toUpperCase();" > 
	           		</tr>
	            	<tr>
		            	<td width="29%" scope="row" align="right">No. Fail Seksyen</td>
		            	<td width="1%">:</td>	            	
		              	<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!txtNoFail" size="43" maxlength="50" onBlur="this.value=this.value.toUpperCase();" > 
		            </tr>
		             <tr>
			            <td width="29%" scope="row" align="right">Tajuk</td>
			            <td>:</td>
			            <td width="70%"><input name="txtCatatan" id="txtCatatan" type="text" value="$!txtCatatan" size="43" maxlength="50" onBlur="this.value=this.value.toUpperCase();" >
			         </tr>		         
			         
			         <tr>
			            <td width="29%" scope="row" align="right">Status</td>
			            <td>:</td>
			            <td width="70%">
			            <input type="hidden" name="statusTemp" id="statusTemp" value="$!socStatus"/>
			            
			            <select name="socStatus" id="socStatus">
                                  <option value="">SILA PILIH</option>
                                  <option value="Dalam Proses">Dalam Proses</option>
                                  <option value="Belum Lulus">Belum Lulus</option>
                                  <option value="Lulus">Lulus</option>
                                </select>
			            
			            </td>
			         </tr>            
            		<tr>
              			<td width="29%" scope="row" align="right">Tarikh</td>
              			<td width="1%">:</td>
              			<td width="70%">
              				<input type="text" name="txdTarikh" id="txdTarikh" value="$!txdTarikh" onblur="check_date(this)" size="11"/>
      						<a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
      					</td>
            		</tr>
        		</table>
	  		</fieldset>
    	</td>
	</tr>
	<tr>
    	<td>
    		<fieldset><legend><b>IMEJ MEMORANDUM JEMAAH MENTERI</b></legend>
        		<table align="center" width="100%" id="table1"> 
		            <tr>
		              <td colspan="5" scope="row">
		              	<input name="cmdUpload" type="button" value="Tambah" onclick="javascript:tambahImej()"/>
<!-- 		              	<input name="cmdHapus" type="button" value="Hapus" onclick="javascript:hapusImej()"/> -->
		              </td>
		            </tr>
		             <tr id="imejFieldset" hidden="hidden" class="three">
		              <td>
		              	<td width="100%" scope="row" align="right">Nama Dokumen</td>
		            	<td width="1%">:</td>	            	
		              	<td width="70%"><input name="txtNamaDokumen" id="txtNamaDokumen" type="text" value="$!txtNamaDokumen" size="43" maxlength="100"></td> 
		              	<td width="100%" scope="row" align="right"><input id="fileupload" name="fileupload" type="file" size="40" /></td>
		              	<td width="100%" scope="row" align="right"><input name="cmdSimpanImej" type="button" value="Muat Naik" onclick="javascript:simpanImej()"/></td>
		              	<td width="100%" scope="row" align="right"><input name="cmdCancelImej" type="button" value="Batal" onclick="javascript:batalImej()"/></td>
		              </td>
		            </tr>
		            <tr>
		              	<td colspan="5" scope="row">
		        		#parse("app/utils/record_paging.jsp")
		            	</td>
		            </tr>
		            <tr class="table_header">
				        <td width="3%"><b>Bil.</b></td>
				        <td width="37%"><b>Nama Dokumen</b></td>
				        <td width="17%"><b>Nama Fail</b></td>
				        <td width="23%"></td>
		            </tr>
	          		#set ($list = "")
	          		#if ($SenaraiFail.size() > 0)
	          			#foreach ($list in $SenaraiFail)
				            #if ($list.bil == '')
				                #set( $row = "row1" )
				            #elseif (($list.bil % 2) != 0)
				                #set( $row = "row1" )
				            #else 
				                #set( $row = "row2" )
				            #end
			          	<tr>
			            	<td class="$row" width="3%">$list.bil.</td>
			            	<td class="$row" >$list.namaDokumen</td>
			            	<td class="$row" >$list.namaFail</td>
			            	<td class="$row" >
			            	<input name="cmdPapar" type="button" value="Papar Imej" onclick="javascript:cetakImej('$list.id')"/>
			            	<input name="cmdHapus" type="button" value="Hapus" onclick="javascript:hapusImej('$list.id')"/>
			            	</td>
			          	</tr>
	          			#end
	          		#else
			          	<tr>
			            	<td class="row1" >Tiada Rekod&nbsp;</td>
			          	</tr>
	          		#end
        		</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td>
    		<fieldset>
	        	<table width="100%" align="center" border="0">
				  	<tr>
						<td width="29%">&nbsp;</td>
				    	<td width="1%">&nbsp;</td>
				    	<td width="70%">
						<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpan()">
						<input type="reset" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="javascript:kosongkan()">
						<input type="reset" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembali()">
				   		</td>
					</tr>
        		</table>
	  		</fieldset>
    	</td>
	</tr>
</table>
	<input name="idTblMemoMenteri" type="hidden" id="idTblMemoMenteri" value="$!idTblMemoMenteri"/>
	<input name="idTblDocMemo" type="hidden" id="idTblDocMemo" value="$!idTblDocMemo"/>
	<input name="hitButton" type="hidden" id="hitButton" value="$!hitButton"/>
	<input name="method" type="hidden" id="method" value="$!method"/>
	<input name="saveNoti" type="hidden" id="saveNoti" value="$!saveNoti"/>
	
<script>

if(document.${formName}.saveNoti.value == 'true'){
	alert("Maklumat berjaya disimpan.");
}

function simpan() {
	document.${formName}.hitButton.value="simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPengurusanUlasanMJM";
	document.${formName}.submit();
}

function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPengurusanUlasanMJM";
	document.${formName}.submit();
}

function tambahImej() {
	document.getElementById("imejFieldset").style.display = 'block';
}

function batalImej() {
	document.getElementById("imejFieldset").style.display = 'none';
}

function simpanImej() {
	document.${formName}.method.value="post";
	document.${formName}.hitButton.value="addDoc";
	var x = create_request_string(document.${formName});
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPengurusanUlasanMJM&"+x;
	document.${formName}.submit();
	document.getElementById("imejFieldset").style.display = 'none';
}


function cetakImej(id){
	var url = "../servlet/ekptg.model.htp.pajakan.FrmPengurusanMJMDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hapusImej(id) {
	document.${formName}.idTblMemoMenteri.value;
	document.${formName}.idTblDocMemo.value=id;
	document.${formName}.hitButton.value = "hapus";
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPengurusanUlasanMJM";
	document.${formName}.submit();
}

var test = document.${formName}.statusTemp.value;
if(test!=null){
	document.${formName}.socStatus.value = test;
}
	
</script>