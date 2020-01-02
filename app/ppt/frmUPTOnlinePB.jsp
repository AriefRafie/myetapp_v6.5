<fieldset id="changePB">
<center>

	#parse("app/ppt/frmPPTHeaderHM.jsp")
	
<br/>

	#if($mode=="new")

	#if($PBexist)
	<script>
		alert("Pihak Berkepentingan Telah Wujud di Hakmilik Yang Sama.");
	</script>
	#end
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Asas Pihak Berkepentingan</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">*</font></td>
			<td width="24%">Jenis PB</td>
			<td width="1%">:</td>
			<td width="74%">$!selectJenisPB</td>
		</tr>
		<tr>
			<td><font color="red">*</font></td>
			<td>Nama</td>
			<td>:</td>
			<td><input type="text" name="txtNama" id="txtNama" value="$!txtNama" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Kod No. PB</td>
			<td>:</td>
			<td>$!selectJenisNoPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. PB</td>
			<td>:</td>
			<td><input type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20" onblur="checkExistPB()"  ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Perihal Jenis PB (lain-lain)</td>
			<td>:</td>
			<td><input type="text" name="txtJenisPB" id="txtJenisPB" value="$!txtJenisPB" size="20" maxlength="100"   ></td>
		</tr>		
		<tr>
			<td>&nbsp;</td>
			<td>Bangsa</td>
			<td>:</td>
			<td>$!selectBangsa</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Warga</td>
			<td>:</td>
			<td>$!selectWarga</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Bahagian / Syer</td>
			<td>:</td>
			<td>#if($checkSizeBahagianPB_size!=0)$!selectBahagianPB&nbsp;#end<input type="text" name="txtSyorAtas" id="txtSyorAtas" value="$!txtSyorAtas" size="11" maxlength="11" onkeyup="validateNumber(this,this.value)" onblur="validateSyer('$!checkSizeBahagianPB_size')" >&nbsp;<b>/</b>&nbsp;<input type="text" name="txtSyorBawah" id="txtSyorBawah" value="$!txtSyorBawah" size="11" maxlength="11" onblur="validateSyer('$!checkSizeBahagianPB_size')" onkeyup="validateNumber(this,this.value)"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Alamat</td>
			<td>:</td>
			<td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Poskod</td>
			<td>:</td>
			<td><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Negeri</td>
			<td>:</td>
			<td>$!selectNegeri</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Bandar</td>
			<td>:</td>
			<td>$!selectBandar</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Telefon</td>
			<td>:</td>
			<td><input type="text" name="txtNoTelefon" id="txtNoTelefon" value="$!txtNoTelefon" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Telefon Bimbit</td>
			<td>:</td>
			<td><input type="text" name="txtNoBimbit" id="txtNoBimbit" value="$!txtNoBimbit" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Faks</td>
			<td>:</td>
			<td><input type="text" name="txtNoFaks" id="txtNoFaks" value="$!txtNoFaks" size="14" maxlength="14" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
	</table>
	
	<br/>
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Akaun</b></td></tr>
		<tr><td><a href="javascript:setTable('showHeader1')"><font color="blue"><b>&raquo; <label id="lblid1">Buka Maklumat</label> </b></font></a></td></tr>
	</table>

	<table id="showHeader1" style="display:none" width="100%" border="0">
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="24%">Nama Bank</td>
			<td width="1%">:</td>
			<td width="74%"><input type="text" name="txtNamaBank" id="txtNamaBank" value="$!txtNamaBank" size="25" maxlength="20" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Akaun</td>
			<td>:</td>
			<td><input type="text" name="txtNoAkaun" id="txtNoAkaun" value="$!txtNoAkaun" size="25" maxlength="20" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" ></td>
		</tr>
	</table>
	
	
	#if($hideAdd=="yes")
	<script>
		alert("Bahagian PB Telah Mencukupi");
	</script>
	#elseif($hideAdd=="notcomplete")
	<script>
		alert("Bahagian PB Masih Tidak Mencukupi");
	</script>
	#end
	
	#end
	
	
	
	#if($mode=="view")
	
#if($onchange=="no")
	#foreach($dataPB in $dataDetailPB)
		#set($txtNama=$dataPB.nama_pb)
		#set($txtNoPB=$dataPB.no_pb)
		#set($txtJenisPB=$dataPB.jenis_lain2_pb)
		#set($txtSyorAtas=$dataPB.syer_atas)
		#set($txtSyorBawah=$dataPB.syer_bawah)
		#set($txtAlamat1=$dataPB.alamat1)
		#set($txtAlamat2=$dataPB.alamat2)
		#set($txtAlamat3=$dataPB.alamat3)
		#set($txtPoskod=$dataPB.poskod)
		#set($txtNoTelefon=$dataPB.no_tel_rumah)
		#set($txtNoBimbit=$dataPB.no_hp)
		#set($txtNoFaks=$dataPB.no_fax)
		
		#set($txtNamaBank=$dataPB.nama_bank)
		#set($txtNoAkaun=$dataPB.no_akaun)
		
	#end
#end
	
	#if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
		#set($M = "")
	#else
		#set($M = "*")
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Asas Pihak Berkepentingan</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">$!M</font></td>
			<td width="24%">Jenis PB</td>
			<td width="1%">:</td>
			<td width="74%">$!selectJenisPB</td>
		</tr>
		<tr>
			<td><font color="red">$!M</font></td>
			<td>Nama</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtNama" id="txtNama" value="$!txtNama" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Kod No. PB</td>
			<td>:</td>
			<td>$!selectJenisNoPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. PB</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Perihal Jenis PB (lain-lain)</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtJenisPB" id="txtJenisPB" value="$!txtJenisPB" size="20" maxlength="100"   ></td>
		</tr>		
		<tr>
			<td>&nbsp;</td>
			<td>Bangsa</td>
			<td>:</td>
			<td>$!selectBangsa</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Warga</td>
			<td>:</td>
			<td>$!selectWarga</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Bahagian / Syer</td>
			<td>:</td>
			<td>#if($checkSizeBahagianPB_size!=0)$!selectBahagianPB&nbsp;#end<input $disability $disabilityx type="text" name="txtSyorAtas" id="txtSyorAtas" value="$!txtSyorAtas" size="11" maxlength="11" onkeyup="validateNumber(this,this.value);validateSyer('$!checkSizeBahagianPB_size')">&nbsp;<b>/</b>&nbsp;<input $disability $disabilityx type="text" name="txtSyorBawah" id="txtSyorBawah" value="$!txtSyorBawah" size="11" maxlength="11" onblur="validateSyer('$!checkSizeBahagianPB_size')" onkeyup="validateNumber(this,this.value)"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Alamat</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><input $disability $disabilityx type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><input $disability $disabilityx type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Poskod</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Negeri</td>
			<td>:</td>
			<td>$!selectNegeri</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Bandar</td>
			<td>:</td>
			<td>$!selectBandar</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Telefon</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtNoTelefon" id="txtNoTelefon" value="$!txtNoTelefon" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Telefon Bimbit</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtNoBimbit" id="txtNoBimbit" value="$!txtNoBimbit" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Faks</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtNoFaks" id="txtNoFaks" value="$!txtNoFaks" size="14" maxlength="14" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
	</table>
	
	<br/>
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Akaun</b></td></tr>
		<tr><td><a href="javascript:setTable('showHeader1')"><font color="blue"><b>&raquo; <label id="lblid1">Buka Maklumat</label> </b></font></a></td></tr>
	</table>

	<table id="showHeader1" style="display:none" width="100%" border="0">
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="24%">Nama Bank</td>
			<td width="1%">:</td>
			<td width="74%"><input $disability $disabilityx type="text" name="txtNamaBank" id="txtNamaBank" value="$!txtNamaBank" size="25" maxlength="20" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Akaun</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtNoAkaun" id="txtNoAkaun" value="$!txtNoAkaun" size="25" maxlength="20" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" ></td>
		</tr>
	</table>
	
	#end
	
</center>	
</fieldset>

		<table width="100%" border="0">
			<tr align="center">
				<td>
				#if($mode=="new")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPB('$!id_hakmilik','$!id_pihakberkepentingan','0','$!checkSizeBahagianPB_size')">
				#end
				

				#if($mode=="view" && ($id_status=="8" || $flagStatusOnline=="1"))
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPB('$!id_pihakberkepentingan')">
					<input type="button" name="cmdHapus" value="Hapus" onClick="hapusPB('$!id_hakmilik','$!id_pihakberkepentingan')" >
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPB('$!id_hakmilik','$!id_pihakberkepentingan','1','$!checkSizeBahagianPB_size')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalPB('$!id_pihakberkepentingan')">
					#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewHM('$!id_hakmilik')">
				</td>
			</tr>
		</table>



	<fieldset id="bottom">
    <legend><strong>Senarai Pihak Berkepentingan</strong></legend>	
    
    	<table width="100%" border="0">
    		<tr>
    			#if($mode=="view" && ($id_status=="8" || $flagStatusOnline=="1"))
    			<td colspan="2"><input type="button" name="cmdTambahPB" value="Tambah" onClick="javascript:tambahPB('$!id_hakmilik')"></td>
    			#end
    			<td width="70%" align="right">Carian Nama PB :&nbsp;<input type="text" name="carianPB2" value="$!carianPB2" maxlength="20" size="20"   ><a href="javascript:cariPB('$!id_hakmilik','$!mode','$!id_pihakberkepentingan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanPB('$!id_hakmilik','$!mode','$!id_pihakberkepentingan')">&nbsp;<u>KOSONGKAN</u></a></td>
    		</tr>
    	</table>
    			
    			
    	#if($saiz_pb > 5)
        <div style="width:100%;height:100;overflow:auto"> 
        #end
            	
    	<table width="100%" border="0">	
    		<tr class="table_header">
    			<td width="4%" align="center"><b>No</b></td>
    			<td width="46%">&nbsp;<b>Nama PB</b></td>
    			<td width="20%">&nbsp;<b>No. PB</b></td>
    			<td width="30%">&nbsp;<b>Bahagian / Syer</b></td>
    		</tr>
    				
    	#if($saiz_pb!=0)
            #foreach($listPB in $listMaklumatPB)
              #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		#set( $row = "row2" )
         		#else
               		#set( $row = "row1" )
         		#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listPB.bil</td>
                   		<td class="$row">&nbsp;<a href="javascript:viewPB('$!listPB.id_pihakberkepentingan')"><font color="blue">$!listPB.nama_pb</font></a></td>
              			<td class="$row">$!listPB.no_pb</td>
                   		#if($listPB.syer_atas != "")
              			<td class="$row">$!listPB.syer_atas / $!listPB.syer_bawah</td>
              			#else
              			<td class="$row">Tiada Bahagian</td>
              			#end
              		<tr>
           #end
        #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
        #end
    				
    			</table>
    			
    	#if($saiz_pb > 5)
        </div>
        #end
    			
</fieldset>


<!-- Id -->
<input type="hidden" name="idExistPB" value="$!idExistPB">

<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_pihakberkepentingan" value="$!id_pihakberkepentingan">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="totalSyer" value="$!totalSyer">
<input type="hidden" name="tabId">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function setTable(id){

	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="";
		document.getElementById("lblid1").innerHTML  = "Tutup Maklumat";
	}
	else if(document.getElementById(id).style.display==""){
		document.getElementById(id).style.display="none";
		document.getElementById("lblid1").innerHTML  = "Buka Maklumat";
	}
}
function validateSyer(sizeBahagian) {

	//syer
	var sA = parseFloat(document.${formName}.txtSyorAtas.value);
	var sB = parseFloat(document.${formName}.txtSyorBawah.value);

	//total syer
	var totalSyer = parseFloat(document.${formName}.totalSyer.value);

	if(sA > sB){

		alert("Sila pastikan \"Syer Atas\" tidak melebihi \"Syer Bawah\"");
		
	}else{

		if(sizeBahagian != "0" && document.${formName}.socBahagianPB.value == ""){	
			if((document.${formName}.txtSyorAtas.value!= "") && (document.${formName}.txtSyorBawah.value!= "")){

				var bhg = sA / sB;
				var newTotal = totalSyer + bhg;

				if(newTotal>1.01){
					alert("Sila pastikan \"Bahagian / Syer PB\" tidak melebihi jumlah keseluruhan");
				}

			}	
		}
	}

	
}
function checkExistPB() {

	if (document.${formName}.socJenisNoPB.value != "" && document.${formName}.txtNoPB.value.length > 3) {
		
		if ((document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length < 12) || (document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length > 12)) {
			alert("Sila pastikan No.Kad Pengenalan baru diisi dengan betul");
			document.${formName}.txtNoPB.focus(); 
			return;
		}else{
			document.${formName}.ScreenLocation.value = "changePB";	
			document.${formName}.command.value = "tambahPB";	
			document.${formName}.command2.value = "checkExistPB";		
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
			document.${formName}.submit();		
			document.${formName}.socJenisPB.focus();
		}
	}
	
}
function onchangeGetBahagianUpdate() {

	document.${formName}.ScreenLocation.value = "changePB";
	
	document.${formName}.command.value = "viewPB";	
	document.${formName}.command2.value = "kemaskiniPB";	
	document.${formName}.command3.value = "onchangeGetBahagianUpdate";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function onchangeNegeriUpdate() {

	document.${formName}.ScreenLocation.value = "changePB";
	
	document.${formName}.command.value = "viewPB";	
	document.${formName}.command2.value = "kemaskiniPB";	
	document.${formName}.command3.value = "onchangeNegeriUpdate";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function onchangeGetBahagian() {
	document.${formName}.ScreenLocation.value = "changePB";	
	document.${formName}.command.value = "tambahPB";	
	document.${formName}.command2.value = "onchangeGetBahagian";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function onchangeNegeri() {

	document.${formName}.ScreenLocation.value = "changePB";
	
	document.${formName}.command.value = "tambahPB";	
	document.${formName}.command2.value = "onchangeNegeri";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function cariPB(idHakmilik,mode,idPB) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	if(mode=="view"){
		document.${formName}.command.value = "viewPB";
		document.${formName}.id_pihakberkepentingan.value = idPB;
	}else{
		document.${formName}.command.value = "tambahPB";
	}
		
	document.${formName}.id_hakmilik.value = idHakmilik;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function kosongkanPB(idHakmilik,mode,idPB) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	if(mode=="view"){
		document.${formName}.command.value = "viewPB";
		document.${formName}.id_pihakberkepentingan.value = idPB;
	}else{
		document.${formName}.command.value = "tambahPB";
	}
	
	document.${formName}.carianPB2.value = "";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function tambahPB(idHakmilik){

	document.${formName}.ScreenLocation.value = "changePB";
	
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function viewPB(idPB){

	document.${formName}.ScreenLocation.value = "changePB";
	document.${formName}.CursorPoint.value = "";
	
	document.${formName}.id_pihakberkepentingan.value = idPB;
	document.${formName}.command.value = "viewPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
	
}
function hapusPB(idHakmilik,idPB) {

	document.${formName}.ScreenLocation.value = "bottom";
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.id_pihakberkepentingan.value = idPB;
	document.${formName}.command.value = "hapusPB";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}

function batalPB(idPB){

	document.${formName}.ScreenLocation.value = "changePB";

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_pihakberkepentingan.value = idPB;
	document.${formName}.command.value = "viewPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
	
}
function kemaskiniPB(idPB){

	document.${formName}.ScreenLocation.value = "changePB";

	document.${formName}.command.value = "viewPB";
	document.${formName}.command2.value = "kemaskiniPB";
	document.${formName}.id_pihakberkepentingan.value = idPB;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function simpanPB(idHakmilik,idPB,mode,sizeBahagian) {

	//syer
	var sA = parseFloat(document.${formName}.txtSyorAtas.value);
	var sB = parseFloat(document.${formName}.txtSyorBawah.value);

	//value syer atas n bawah
	var valA = document.${formName}.txtSyorAtas.value;
	var valB = document.${formName}.txtSyorBawah.value;
	
	//total syer
	var totalSyer = parseFloat(document.${formName}.totalSyer.value);

	var newTotal = 0.00;
	if((document.${formName}.txtSyorAtas.value!= "") && (document.${formName}.txtSyorBawah.value!= "")){

		var bhg = sA / sB;
		var newTotal = totalSyer + bhg;
	}
	

	document.${formName}.ScreenLocation.value = "changePB";

	if(document.${formName}.txtNama.value == ""){
		alert("Sila masukkan \"Nama PB\" terlebih dahulu.");
  		document.${formName}.txtNama.focus(); 
		return;
	}
/*	else
	if(document.${formName}.socJenisNoPB.value == ""){
		alert("Sila pilih \"Jenis No PB\" terlebih dahulu.");
	  	document.${formName}.socJenisNoPB.focus(); 
		return;
	}
	else
	if(document.${formName}.txtNoPB.value == ""){
		alert("Sila masukkan \"No PB\" terlebih dahulu.");
  		document.${formName}.txtNoPB.focus(); 
		return;
	}
*/	else
	if (document.${formName}.socJenisNoPB.value!="" && (document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length < 12) || (document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length > 12)) {
		alert("Sila pastikan No.Kad Pengenalan baru diisi dengan betul");
		document.${formName}.txtNoPB.focus(); 
		return;
	}
	else
	if(document.${formName}.socJenisPB.value == ""){
		alert("Sila pilih \"Jenis PB\" terlebih dahulu.");
	  	document.${formName}.socJenisPB.focus(); 
		return;
	}
/*	else
	if (valA == "") {
		alert("Sila masukkan \"Syer Atas\" terlebih dahulu ");
		document.${formName}.txtSyorAtas.focus();
		return
	}
	else
	if (valB == "") {
		alert("Sila masukkan \"Syer Bawah\" terlebih dahulu ");
		document.${formName}.txtSyorBawah.focus();
		return
	}
*/	
	else
	if (((valA != "") && (valB !="")) && (sA > sB)) {
		alert("Sila pastikan \"Syer Atas\" tidak melebihi \"Syer Bawah\"");
		document.${formName}.txtSyorAtas.focus();
		return
	}
	else
	if(sizeBahagian != "0" && (document.${formName}.socBahagianPB.value == "" && (((valA != "") && (valB != "")) && (newTotal>1.01)))){
		alert("Sila pastikan \"Bahagian / Syer PB\" tidak melebihi jumlah keseluruhan");
		document.${formName}.txtSyorAtas.focus();
		return
	}
	else
	if (document.${formName}.txtPoskod.value != "" && document.${formName}.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtPoskod.focus();
		return
	}
	else{

		if(mode=="0"){
			document.${formName}.CursorPoint.value = "txtNama";
		}else{
			document.${formName}.CursorPoint.value = "";
		}
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.id_hakmilik.value = idHakmilik;

		if(mode=="1"){
			document.${formName}.id_pihakberkepentingan.value = idPB;
			document.${formName}.command.value = "viewPB";
			document.${formName}.command2.value = "kemaskiniPB";
			document.${formName}.command3.value = "updatePB";
		}else{
			document.${formName}.command.value = "tambahPB";
			document.${formName}.command2.value = "simpanPB";
		}	
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
		document.${formName}.submit();
	}
}
function viewHM(idHakmilik){

	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.tabId.value = "0";
	
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
</script>
