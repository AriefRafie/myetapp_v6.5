<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
   		<td>&nbsp;</td>
	</tr>

	<tr>
    	<td>

<fieldset>
<legend>CARIAN</legend>
<table border="0" width="100%">
    <tbody>
       <tr >
        <td align="right" width="40%"><div align="right">Negeri</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%">$selectNegeri</td>
      </tr> 
			      	<tr>
				        <td align="right"><div align="right">
				        	#if ($idnegeri == '13' )
				        		Bahagian
				        	#else
				        		Daerah
				        	#end
				        	</div></td>
				        <td><div align="center">:</div></td>
				        <td>$selectDaerah</td>
			      	</tr>
      <tr>
        <td align="right" width="40%"><div align="right">Bandar/Pekan/Mukim</div></td>
        <td><div align="center">:</div></td>
        <td>$selectMukim</td>
      </tr>

       <tr >
        <td align="right" width="40%"><div align="right">Kementerian</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%">$socKementerian</td>
      </tr> 
      <tr >
        <td align="right" width="40%"><div align="right">Agensi</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%">$socAgensi</td>
      </tr>
      <tr >
        <td align="right" width="40%"><div align="right">No. Fail</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!txtNoFail" size="45" maxlength="50" style="text-transform:uppercase;" > </td>
      </tr>
      <tr >
        <td align="right" ><div align="right">No. Fail Lain</div></td>
        <td ><div align="center">:</div></td>
        <td ><input name="txtNoFaillain" type="text" value="$!txtNoFailLain" size="45" maxlength="50" style="text-transform:uppercase;" > </td>
      </tr>          
      <tr >
        <td align="right" width="40%"><div align="right">Tajuk Fail</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%"><input name="txtTajukFail" id="txtTajukFail" type="text" value="$!txtTajukFail" size="45" maxlength="50" style="text-transform:uppercase;" > </td>
      </tr> 
      <!--<tr>
        <td align="right" width="40%"><div align="right">Jenis Hakmilik</div></td>
        <td><div align="center">:</div></td>
        <td>$selectJenisHakmilik</td>
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right">No. Hakmilik</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input name="txtNoHakmilik" type="text" id="txtNoHakmilik" value="$txtNoHakmilik" />
        </label></td>
      </tr>-->
      <tr>
        <td></td>
        <td>&nbsp;</td>
        <td>
        <input type="button" class="stylobutton100" name="btnCari" id="btnCari" value="Cari" onclick="cari()"/>
        <input type="button" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongCarian()" />
        </td>
      </tr>
      <!-- <tr>			  
			<td colspan="3" align="center">&nbsp;</td>
		</tr> -->
    </tbody>
  </table>  

</fieldset>

		</td>
	<tr/>
	
	<tr>
		<td>
			

<fieldset>
<legend>SENARAI FAIL</legend>
<table border="0" width="100%">
    <tr>
    	<td colspan="6">#parse("app/utils/record_paging.jsp") </td>
    </tr>
	<tr class="table_header">
	  <td width="3%"><b>BIL.</b></td>
   	  <td width="20%"><b>NO. FAIL</b></td>
  	  <td width="47%"><b>TAJUK FAIL</b></td>
   	  <td width="15%"><b>NO. FAIL PTD</b></td>
  	  <td width="15%"><b>NO. FAIL PTG</b></td>
  	  <!--<td width="10%">TARIKH TERIMA</td> -->
</tr>
#set ($count = 0)
#foreach ($senaraiHakmilik in $SenaraiHakmilik)
	#set ($count = $count+1)
  #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end
   <tr class="$row">
   	<td height="20">$count.</td> 
      #if($senaraiHakmilik.flagTanah == '1' || $senaraiHakmilik.flagTanah == '2' || $senaraiHakmilik.flagTanah == '5' )  
   			<td><a href="javascript:hakmilik_detail2('$senaraiHakmilik.idpermohonan');" class="style1">$!senaraiHakmilik.no</a></td>
   	  #elseif($senaraiHakmilik.flagTanah == '10')
   			<td><a href="javascript:rizab_detail2('$senaraiHakmilik.idpermohonan');" class="style1">$!senaraiHakmilik.no</a></td>
 	  #else  
 	  	<td>$!senaraiHakmilik.no</td>
   	  #end
   	   
      #if($senaraiHakmilik.flagTanah == '1' || $senaraiHakmilik.flagTanah == '2' || $senaraiHakmilik.flagTanah == '5' )  
    	<td>$!senaraiHakmilik.tajuk</td>
   	  #elseif($senaraiHakmilik.flagTanah == '10')
    	<td>$!senaraiHakmilik.tajuk</td>
 	  #end  
    <td>$!senaraiHakmilik.ptd</td>
    <td>$!senaraiHakmilik.ptg</td>
    <!--<td>$senaraiHakmilik.tarikhTerima</td> -->
</tr> 
  #end
</table>
</fieldset>
<input type=hidden name="command100">
<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">

		</td>
	<tr/>
</table>

<script>

/**function untuk frmRekodSenaraiHakmilik
*2010/05/04 nama fungsi sama
*function cari(){   
*	doAjaxCall${formName}("carianHakmilikRizab2","mode=carian");
*}
*/
	// Fungsi carian di skrin senarai fail (1st Skrin)
	// [Simpan]
	function cari(){   
		doAjaxCall${formName}("","mode=carian");	
	}
	
	// [Kosongkan]
	function kosongCarian(idJenisTanah){
		//if (idJenisTanah == '1'){
		//	document.${formName}.txtNoHakmilikC.value = "";
		//} else if (idJenisTanah == '2'){
		//	document.${formName}.txtNoWartaC.value = "";
		//} 
		//document.${formName}.socNegeri.value = "";
		//document.${formName}.socDaerah.value = "";
		//document.${formName}.socMukim.value = "";	
		//document.${formName}.txtNoFail.value = "";
		//document.${formName}.txtTajukFail.value = "";
		document.${formName}.reset();
		document.${formName}.flagAdvSearch.value = "N"
		document.${formName}.action = "?_portal_module=ekptg.view.htp.Rekod.FrmPendaftaranHakmilikRizabRekod&firstAction=reset";
		document.${formName}.submit();
		
	}

	/** [link] No.Fail
	* 1)Jika perolehan hakmilik baru - Pautan ke Skrin Hakmilik 
	* 2)Jika hakmilik tersedia - Pautan ke Skrin Senarai Hakmilik 
	* 3)Jika perolehan rizab baru - Pautan ke Skrin Rizab 	
	* 4)Jika rizab tersedia - Pautan ke Skrin Senarai Rizab 
	*/
	
	// 1)
	function hakmilik_detail2(id){
		document.${formName}.mode = "";
		doAjaxCall${formName}("paparDetailHakmilik2","idPermohonan="+id);
	}

	//Skrin Hakmilik [Simpan]
	function tambah_detailHakmilik(id){
		if (doValidation()) {
			//AZAM TEST
			//document.${formName}.idPermohonan.value = idPermohonan;			
			document.${formName}.mode.value = "doAddHakmilik";
			document.${formName}.INS_UPD.value = "doView";
			doAjaxCall${formName}("paparDetailHakmilik2");
		}
	}
	
	//RIZAB
	
	//[link] No.Fail, 3)Jika perolehan rizab baru - Pautan ke Skrin Rizab
	// 10.6.2.1 Skrin Kemasukan Warta Baru, Masih Tiada Maklumat Warta
	function rizab_detail2(id){
		//doAjaxCall${formName}("paparDetailRizab2","idPermohonan="+id);
		//doAjaxCall${formName}("paparrizabterperinci","idPermohonan="+id);
		doAjaxCall${formName}("baru","idPermohonan="+id);
	
	}
	
	//RIZAB [SelectedItem Kementerian]	10.6.2.1.1, Pilihan Kementerian Skrin Pendaftaran Rizab
	function doChangeKementerianRizab() {
		document.${formName}.mode.value="doChangeKementerianHRizab";
	  	//doAjaxCall${formName}("paparDetailRizab2");
	  	//doAjaxCall${formName}("paparrizabterperinci");
		doAjaxCall${formName}("baru");
	
	}
	
	//RIZAB [SelectedItem Negeri]	10.6.2.1.2, Pilihan Negeri Skrin Pendaftaran Rizab
	function doChangeStateRizab() {
		document.${formName}.mode.value="doChangeStateRizab";
		//doAjaxCall${formName}("paparDetailRizab2");
		//doAjaxCall${formName}("paparrizabterperinci");
		doAjaxCall${formName}("baru");
	
	}

	//RIZAB [SelectedItem Daerah]	10.6.2.1.3, Pilihan Daerah Skrin Pendaftaran Rizab
	function doChangeDaerahRizab() {
		document.${formName}.mode.value="doChangeDaerahRizab";
		//doAjaxCall${formName}("paparDetailRizab2");
		//doAjaxCall${formName}("paparrizabterperinci");
		doAjaxCall${formName}("baru");
	
	}	
	
	//RIZAB [SelectedItem Luas]	10.6.2.1.4, Pilihan Luas Skrin Pendaftaran Rizab
	function doChangeTarafRizab(ct) { //doChangeDaerahRizab
		document.${formName}.mode.value="changeTarafRizab";
		doAjaxCall${formName}(ct,"firstAction=pendaftaranRizab");
	
	}
	
	//[Pautan/Link Kemaskini Warta]	10.6.2.2.2, RIZAB
	function viewRizab(idPermohonan,idHakmilik){
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idHakmilik.value = idHakmilik;
		document.${formName}.mode.value = "updaterizab";
		document.${formName}.INS_UPD.value = "update";
		//doAjaxCall${formName}("kemaskiniDetailRizab");
		//doAjaxCall${formName}("viewdetailrizab");
		doAjaxCall${formName}("viewonlyrizab");
		
	}
	
	



	function praKemaskiniMaklumatRizab(id){
		//doAjaxCall${formName}("kemaskiniMaklumatRizab","idHakmilik="+id);
		document.${formName}.idHakmilik.value = id;
		doAjaxCall${formName}("prakemaskinimaklumatrizab");
		
	}

	//[Selected Item] Pilihan Kementerian Skrin Pendaftaran Rizab
	function doChangeKementerianRizabEdit() {
		document.${formName}.mode.value="doChangeKementerianRizab";
	  	//doAjaxCall${formName}("paparDetailRizab2");
	  	doAjaxCall${formName}("kemaskiniMaklumatRizab");
	  	
	}	
	

	
	
function doChangeState() {
	//if(document.${formName}.socNegeri.value = "" || document.${formName}.socNegeri.value = "0"){ 
		//return; 
	//}
  	doAjaxCall${formName}("","action=doChangeState&mode=carian");
}
function doChangeDaerah() {
	doAjaxCall${formName}("","action=doChangeDaerah&mode=carian");
}
function doChangeMukim() {
	doAjaxCall${formName}("","action=doChangeMukim&mode=carian");
}
function doChangeKementerian() {
	doAjaxCall${formName}("","action=doChangeKementerian&mode=carian");
}

// Pilihan skrin Pendaftaran Hakmilik/Rizab
	function doChangeKementerianHakmilik() {
		document.${formName}.mode.value="doChangeKementerianHak";
	  	doAjaxCall${formName}("paparDetailHakmilik2");
	}

	function doChangeStateHakmilik() {
		document.${formName}.mode.value="doChangeState";
	  	doAjaxCall${formName}("paparDetailHakmilik2");
	}
	
	function doChangeDaerahHakmilik() {
		document.${formName}.mode.value="doChangeDaerah";
		doAjaxCall${formName}("paparDetailHakmilik2");
	}


function rizab_detail(id,status){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
	document.${formName}.submit();
}


function doChangeKementerianRizabTambah() {
	document.${formName}.mode.value="doChangeKementerianRizab";
  	doAjaxCall${formName}("paparDetailRizab2");
  	//doAjaxCall${formName}("kemaskiniMaklumatRizab");
}

function doChangeNegeriRizabEdit(ct) {
	document.${formName}.mode.value="doChangeNegeriRizab";
	doAjaxCall${formName}(ct);
}

	function doChangeStateRizabEdit() {
		document.${formName}.mode.value="doChangeStateRizab";
		//doAjaxCall${formName}(ct);
		doAjaxCall${formName}("kemaskiniMaklumatRizab");
		
	}


function doChangeDaerahRizabEdit(ct) {
	document.${formName}.mode.value="doChangeDaerahRizab";
	doAjaxCall${formName}(ct);
}

function doChangeTaraf() {
	//doAjaxCall${formName}("kemaskiniDetailHakmilik","action=doChangeTaraf");
	document.${formName}.action.value="doChangeTaraf";
	doAjaxCall${formName}("paparDetailHakmilik");
}


	// function untuk frmRekodPendaftaranRizab
	function kemaskiniMaklumatRizab(id){
		//doAjaxCall${formName}("kemaskiniMaklumatRizab","idHakmilik="+id);
		document.${formName}.idHakmilik.value = id;
		doAjaxCall${formName}("kemaskiniMaklumatRizab");
		
	}
	

function update_detailRizab(id){
	doAjaxCall${formName}("updateDetailRizab","idHakmilik="+id);
}

	function simpanRizab(id){
		//VALIDATAION
		if(document.${formName}.txtIdKementerian.value == ""){ 
			alert('Sila pilih " Kementerian " terlebih dahulu.'); 
			document.${formName}.txtIdKementerian.focus();
			return false; 
	 	}	
	 	if(document.${formName}.txtIdAgensi.value == ""){ 
			alert('Sila pilih " Agensi " terlebih dahulu.'); 
			document.${formName}.txtIdAgensi.focus();
			return false; 
	 	}	
		
		 if(document.${formName}.socNegeri.value == 99999 || document.${formName}.socNegeri.value==""){ 
			alert('Sila masukkan " Negeri " terlebih dahulu.'); 
			document.${formName}.socNegeri.focus();
			return; 
		 }
		 if(document.${formName}.socDaerah.value == 99999 || document.${formName}.socDaerah.value==""){ 
			alert('Sila masukkan " Daerah " terlebih dahulu.'); 
			document.${formName}.socDaerah.focus();
			return; 
		 }
		  if(document.${formName}.socMukim.value == 99999 || document.${formName}.socMukim.value==""){ 
			alert('Sila masukkan " Mukim " terlebih dahulu.'); 
			document.${formName}.socMukim.focus();
			return; 
		 }
		 if(document.${formName}.txtNoWarta.value == ""){ 
			alert('Sila masukkan " No Warta " terlebih dahulu.'); 
			document.${formName}.txtNoWarta.focus();
			return; 
		 }	 
		 if(document.${formName}.socLot.value == ""){ 
			alert('Sila pilih " Kod " terlebih dahulu.'); 
			document.${formName}.socLot.focus();
			return false; 
	 	}
		if(document.${formName}.txtNoLot.value == ""){ 
			alert('Sila pilih " No Lot " terlebih dahulu.'); 
			document.${formName}.txtNoLot.focus();
			return false; 
	 	}
		if(document.${formName}.txdTarikhWarta.value == ""){ 
			alert('Sila masukkan " Tarikh Warta " terlebih dahulu.'); 
			document.${formName}.txdTarikhWarta.focus();
			return; 
		}	 
		if(document.${formName}.txdTarikhTerima.value == ""){ 
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
			document.${formName}.txdTarikhTerima.focus();
			return; 
		}
		if(document.${formName}.txtLokasi.value == ""){ 
			alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
			document.${formName}.txtLokasi.focus();
			return; 
		 }
		 if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
		 }			
		 if(document.${formName}.socLuas.value == ""){ 
			alert('Sila masukkan " Unit Luas " terlebih dahulu.'); 
			document.${formName}.socLuas.focus();
			return; 
		 }   
		 /*if(document.${formName}.txtLuas.value == "" && document.${formName}.txtLuasGabung.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas.focus();
			return; 
		 }*/
		 if(document.${formName}.socLuas.value == '7'){
	 		if(document.${formName}.txtLuas5.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas5.focus();
				return false; 
	 		}
			if(document.${formName}.txtLuas6.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas6.focus();
				return false; 
	 		} 
	 	}else if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
	
	 		if(document.${formName}.txtLuas2.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas2.focus();
				return false; 
	 		}
			if(document.${formName}.txtLuas3.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas3.focus();
				return false; 
	 		}
	 		if(document.${formName}.txtLuas3.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas3.focus();
				return false; 
	 		}
	 	
	 	}else{
	 		//if(document.${formName}.txtLuas.value == "" && document.${formName}.txtLuasGabung.value == ""){ 
	 		if(document.${formName}.txtLuas1.value == "" ){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas1.focus();
				return false;
			} else{
				if (isNaN(document.${formName}.txtLuas1.value)) {
					alert('Sila pastikan format "Luas" adalah nombor.'); 
					document.${formName}.txtLuas1.focus();
					return;
				}
			}
	 	} 	
		 
		 if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
			if(document.${formName}.socLuas.value == "4"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
			}else if(document.${formName}.socLuas.value == "7"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
			}else if(document.${formName}.socLuas.value == "8"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
			}
		 }else{
		 	if(document.${formName}.socLuas.value == "1"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"KM";
		 	}else if(document.${formName}.socLuas.value == "2"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"H";
		 	}else if(document.${formName}.socLuas.value == "3"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"MP";
		 	}else{
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
		 	}
		 }
	    
		 if ( !window.confirm("Adakah Anda Pasti ?") ){
			   return;
		 }
		 
		 //doAjaxCall${formName}("simpanRizab","idPermohonan="+id);
		 doAjaxCall${formName}("simpanRizab");
			 
	}

function update_detailHakmilik(id){
	alert("DANGER:METHOD IS ABOUT TO BE DELETED");
	return;
	//INSERT NEW
	if (doValidation()) {
		doAjaxCall${formName}("updateDetailHakmilik2","idPermohonan="+id);
	}
}

function kembaliHakmilik(){
	doAjaxCall${formName}("");
}

function cetakHakmilik(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?idHakmilik="+idhakmilik;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cal_tarikh_luput(){
	if(document.${formName}.socTaraf.value == 'P'){
		if(document.${formName}.txdTarikhDaftar.value == ''){
			alert('Sila masukkan Tarikh Daftar Hakmilik');
			document.${formName}.txdTarikhLuput.focus();
			return;
		
		}else{
  			var tr = document.${formName}.txdTarikhDaftar.value;
  			var temp_tr = tr.substring(0,6)
  			var year_cur = tr.substring(6,10)
  			var tempoh = document.${formName}.txtTempoh.value; 
  			var luput = temp_tr+(parseInt(year_cur)+parseInt(tempoh));
  			document.${formName}.txdTarikhLuput.value = luput;
  			
  		}
	}
}


function kembaliRizab(){
	doAjaxCall${formName}("");
}

// function semua kongsi
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function kira_keluasan(jenisluas,ketegori_tanah,field_luas1,field_luas2,field_luas3,field_Jumlahluas){
	//alert(jenisluas.value);
	//var val = document.getElementById(jenisluas).value;
  	//var kategory= document.getElementById(ketegori_tanah).value; // ketegory tanah
  	//var a = document.getElementById(field_luas1).value; // field luas 1
  	//var b = document.getElementById(field_luas2).value; // field luas 2
  	//var c = document.getElementById(field_luas3).value; // field luas 3
  	
	var val = jenisluas.value;
  	var kategory= ketegori_tanah.value; // ketegory tanah
  	var a = field_luas1.value; // field luas 1
  	var b = field_luas2.value; // field luas 2
  	var c = field_luas3.value; // field luas 3
  	if (val=="2"){
	  	if(a==""){		
			return ;
		}else{  
			var num = parseFloat(a) * 10000; // convert to meter persegi
			var num1 = parseFloat(a) * 1;   // convert  to hektar   	   
	        if(kategory=="2") { 
				//document.getElementById(field_Jumlahluas).value =num1;	 
				field_Jumlahluas.value =num1;	 
	        }
	        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
	        	document.getElementById(field_Jumlahluas).value =num;	    
	        }		
		}
		     
		
	}else if (val=="5"){       
	   		
		if(a==""){
			return
		} else{  
			var num = parseFloat(a) * 0.09290304; // kaki persegi to meter persegi
			var num1 = parseFloat(a) * 0.000009290304; // kaki persegi to hektar            
        	if(kategory=="2") {       
				//document.getElementById(field_Jumlahluas).value =num1;	   
 				field_Jumlahluas.value =num1;	 
        	}
        	else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;  
        	} 		 
		}
	}else if (val=="1"){ 
		
	    if(a==""){
			return
		} else{  
			var num = parseFloat(a) * 1000000;  // kilo to meter      
        	var num1 = parseFloat(a) * 100; // kilo meter to hektar        
        	if(kategory=="2") {       
				//document.getElementById(field_Jumlahluas).value =num1;    		
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory =="4" || kategory =="5" || kategory =="6") {
        		document.getElementById(field_Jumlahluas).value =num;	   
        	} 
		}
		     
		
	}else if (val=="3"){
        
		if(a==""){
			return
		}else{ 
			var num = parseFloat(a) * 1; //meter persegi to meter persegi
			var num1 = parseFloat(a) * 0.0001;  //meter persegi to hektar        
        	if(kategory=="2"){       
				//document.getElementById(field_Jumlahluas).value =num1;	    	
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 
		
		}
		     
		
	}

// 9=Batu nautika belum dapat formula
	else if (val=="9"){
       	if(a==""){
			return
		}else{         
			var num = parseFloat(a) * 1; //meter persegi to meter persegi
			var num1 = parseFloat(a) * 1;  //meter persegi to hektar
        	if(kategory=="2") {       
				//document.getElementById(field_Jumlahluas).value =num1;    	
    			field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 
		
		}
		     
		
	}
// 6=Ekar perpuluhan belum dapat formula	
	
		else if (val=="6"){
         
		if(a==""){
			return
		}else{ 
			var num = parseFloat(a) * 1; 
			var num1 = (parseFloat(a) * 4046.86)/1000;  //Ekar perpuluhan to Hektar       
        	if(kategory=="2")	{       
				//document.getElementById(field_Jumlahluas).value =num1;    		
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 		
		}		
	
	}else if (val=="7"){      
		 
		if(a==""){
			return
		}else if(b==""){			
			return
		}else{   
			var num = (parseFloat(a) + (parseFloat(b)/1000))*4046.86; 
			var num1 =  (parseFloat(a) + (parseFloat(b)/1000))*0.404686;       
        	if(kategory=="2"){       
				//document.getElementById(field_Jumlahluas).value =num1;
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")	{
        		document.getElementById(field_Jumlahluas).value =num;
        	} 		
		}
	}
	
	else if (val=="8"){
                
		if(a==""){
			return
		}else if(b==""){
			return
		}else if(c==""){
			return
		}else{ 
			var num = (parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976))*0.711111*4046.86; 
			var num1 = ((parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976)))*0.711111*0.404686;       
        	if(kategory=="2"){       
				//document.getElementById(field_Jumlahluas).value =num1;	    	
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 		
		}
		     
		
	}
	 
	else if (val=="4"){
      	if(a==""){
			return
		}else if(b==""){
			return
		}else if(c==""){
			return
		}else{
		         //Ekar Rood Pole
		         //1	2	3
				 //0.404685642	0.101171411	0.252928526
		
			var num = (parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160))*4046.86; 
			var num1 = ((parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160)))*0.404686;           
        	if(kategory=="2"){       
				//document.getElementById(field_Jumlahluas).value =num1;	    	
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 		
		}	     
		
	}

}

function pilihLuas(){
	alert(document.${formName}.socLuas.value);
	if(document.${formName}.socLuas.value==4)
		displayFieldLuasEkar('ekarpole');
		//onclick="javascript:setTable('tableReport1')"
	//doAjaxCall${formName}("");
}

function displayFieldLuasEkar(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function semakTarikhSemasa(){
 	if(document.${formName}.txdTarikhTerima.value == ""){ 
		alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
		document.${formName}.txdTarikhTerima.focus();
		return; 
 	}
 	if(document.${formName}.txdTarikhDaftar.value == ""){ 
		alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
		document.${formName}.txdTarikhDaftar.focus();
		return; 
 	}
 		/*var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
	  
	  	if(akhir<mula){
	    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
	    	return;
	  	}
	  	if(akhir>tarikhsemasa){
	    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
	    	return;
	  	}*/
 
 }
 
 function checking_validation(field,point,mandatory,value_field,jenis_field){	

   	var lepas_or_xlepas = 1;
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	     // DateField.select();		
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	      
	   
	   
	   if(tarikh_valid == "valid")
	   {
	   
	      var t_semakan  = document.${formName}.txdTarikhSemakanMMK.value;
		   if(t_semakan!="")
		   {
		   var dtx   = parseInt(t_semakan.substring(0,2),10);
		   var monx  = parseInt(t_semakan.substring(3,5),10)-1;
		   var yrx   = parseInt(t_semakan.substring(6,10),10);	   
		   var datex = new Date(yrx, monx, dtx);	 
			  if (datex > date)
			  {
			 /* 
			   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh semakan "+t_semakan+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh semakan "+t_semakan+"");		  
			  
			  }	
			  else
			  {
			  /*
			     document.${formName}.alert_message.value  = "";	  
			   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
			   actionName = "checking_validation";
			   target = point;
			   doAjaxUpdater(document.${formName}, url, target, actionName);
			   */
			   
			    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
			  }   
		   }
		   else
		   {
	   	/*   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
	         }
	   }
	   else
	   {
	  
	   /*
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");	
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+"");	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");	
	 
	//   DateField.focus();
	   
	   }
	   
	   }
	   
	// 
	   if(jenis_field == "normal")
	   {
	    
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");	
		
	   }
	   else
	   {
	   /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
			
	   }
	   
	   	   
	   }
	  
	   
	   if(jenis_field == "drop")
	   {
	  
	   if((field.value == "" || field.value == "0") && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila pilih "+value_field+"");	
		
	   }
	   else
	   {
	   /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
			
	   }
	   
	   	   
	   }
	   
	   
	   
	   if(jenis_field == "radio")
	   {
	  
	  
	    var r = 0;
		if(field.length > 1)
		{     
			  for (i = 0; i < field.length; i++)
			  {
			  if (field[i].checked == true)
			  {	 
			  r++
			  }
			  }  
		}
		else
		{
		if (field.checked == true)
		{	 
		r++;
		}	 	
		}  
	   
	     
	   if((r == 0) && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	  
	   if(lepas_or_xlepas == "2")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila pilih "+value_field+"");	
		
	   }
	   else
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
		/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
	   }
	   
	   	   
	   }
	   
	   
	   
	    if(jenis_field == "file") 
	    {
	   	var allBlank = true;
		if(document.${formName}.fileupload!=null)
		{
		
		for( var i=0,e=document.${formName}.fileupload;i<e.length; i++ )
		{			
		if( e[i].type=='file' )
		{	
		if( e[i].value.length  )
		{
		
		allBlank=false;	
		}	
		}	
		}
		
		}
		else
		{
		allBlank=false;	
		}
		
		
		if(allBlank == true)
	    {
		/*
		   document.${formName}.alert_message.value  = "Sila masukkan dokumen terlebih dahulu";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */
		   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan dokumen terlebih dahulu");	
		
	    }
		else
		{
		/*
		   document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   
		    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
			
		
		
		}
		
		
	   
	   }
	   
	   
	   
	
}

function semakTarikhHariIni(txdTarikhSuratKJP) {
	var today = new Date();
	dari_bulan	= txdTarikhSuratKJP.value.substring(3,5);
	dari_hari 	= txdTarikhSuratKJP.value.substring(0,2);
	dari_tahun 	= txdTarikhSuratKJP.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		txdTarikhSuratKJP.value="";	 
  		txdTarikhSuratKJP.focus();	 
  		
 		return;
 	}

}

function semakTarikhAkhirMula(txdMula,txdAkhir,msg) {

		akhir_bulan = txdAkhir.value.substring(3,5);
  		akhir_hari = txdAkhir.value.substring(0,2);
  		akhir_tahun = txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
  		mula_bulan = txdMula.value.substring(3,5);
 		mula_hari = txdMula.value.substring(0,2);
  		mula_tahun = txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
	
		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
	  
	  	if(akhir<mula){
	    	alert("Sila pastikan "+msg);
	    	txdAkhir.value="";	 
  			txdAkhir.focus();	 
	    	return;
	  	}
 }
 
function hakmilikTerperinci(id){
	doAjaxCall${formName}("tambahHakmilik","firstAction=tambahHakmilikBaru&idHakmilik="+id);
}
 
function hakmilikTerperinci(id,status,idpermohonan){
	doAjaxCall${formName}("tambahHakmilik","firstAction=tambahHakmilikBaru&idHakmilik="+id+"&idpermohonan="+idpermohonan);
}
 //function hakmilikTerperinci(id,status){
//	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod&firstAction=PendaftaranHakmilikBaru&nextAction=paparDetailHakmilikBaru&idHakmilik="+id+"&statusSah="+status;
//	document.${formName}.submit();
//}




/*function doChangeState() {
  doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeState");
}
function doChangeDaerah() {
	doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeDaerah");
}
function doChangeMukim() {
	doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeMukim");
}
function doChangeKementerian() {
	doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeKementerian");
}*/

//tambah Hakmilik di frmPendaftaranTerimaHakmilikBaru
function hakmilikBaru(id){
	doAjaxCall${formName}("tambahHakmilik","&firstAction=daftarHakmilik&idHakmilik="+id);
}

function kiraLuas(idLuas){
  var jenisLuas = idLuas;
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){
  	var luasK = (document.${formName}.txtLuas1.value);
	var luasH = luasK*100;
  	document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
   else
   //HEKTAR
    if(jenisLuas == "2"){
  	var luasH = (document.${formName}.txtLuas1.value);
  	document.${formName}.txtLuas.value = luasH;
   }
   else
   // METER PERSEGI
   if(jenisLuas == "3"){
  	  var luasM = document.${formName}.txtLuas1.value;
  	  var luasH = (luasM*0.0001);
	  document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
   else
   //EKAR, ROOD, POLE
   if(jenisLuas == "4"){
  	  var luasE = document.${formName}.txtLuas2.value;
	  var luasR = document.${formName}.txtLuas3.value;
	  var luasP = document.${formName}.txtLuas4.value;
	  var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
   else
   //KAKI PERSEGI
   if(jenisLuas == "5"){
  	  var luasAsal = document.${formName}.txtLuas1.value;
	  var luasK = luasAsal*0.0000092;
  	  document.${formName}.txtLuas.value = luasK.toFixed(5);
  	  
   }else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  var luasAsal = document.${formName}.txtLuas1.value;
	  /* AZAM */
	  var luasK = luasAsal*0.405;
  	  document.${formName}.txtLuas.value = luasK.toFixed(5);
	  /*
	  var luasK = luasAsal*0.0000092;
  	  document.${formName}.txtLuas.value = luasK.toFixed(5);
  	  var num1 = (parseFloat(a) * 4046.86)/1000;  //Ekar perpuluhan to Hektar       
	  */
  	  
   }
   else
   //EKAR,DEPA
   if(jenisLuas == "7"){
  	  var luasE = document.${formName}.txtLuas5.value;
	  var luasD = document.${formName}.txtLuas6.value;
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
    else
   //RELONG,JEMBA,KAKI PERSEGI
   if(jenisLuas == "8"){
  	  var luasR = document.${formName}.txtLuas2.value;
	  var luasJ = document.${formName}.txtLuas3.value;
	  var luasK = document.${formName}.txtLuas4.value;
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
   //by Rosli 2010/05/10
   	 if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
		if(document.${formName}.socLuas.value == "4"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
		}else if(document.${formName}.socLuas.value == "7"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
		}else if(document.${formName}.socLuas.value == "8"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
		}
	 }else{
		document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
	 }
}

	// function untuk frmRekodPendaftaranHakmilik
	function kemaskini_detailHakmilik(id){
		if (doValidation()) {
			document.${formName}.mode.value = "update";
			doAjaxCall${formName}("kemaskiniDetailHakmilik","mode=update&idHakmilik="+id);
		}
	}

function doKemaskiniView(id) {
	document.${formName}.INS_UPD.value = "update";
	doAjaxCall${formName}("kemaskiniDetailHakmilik","mode=exit&idHakmilik="+id);
}


function simpan_detailHakmilik(id){
	if (doValidation()) {
		//doAjaxCall${formName}("updateDetailHakmilik2","mode=simpan&idPermohonan="+id);
		//document.${formName}.mode.value = "doAddHakmilik";
		//doAjaxCall${formName}("paparDetailHakmilik2","mode=doAddHakmilik&idPermohonan="+id);
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}("kemaskiniDetailHakmilik","idPermohonan="+id);
	}
}

	
	function semakHakmilikWarta(){
		//alert(1);
		url = "../servlet/ekptg.view.htp.FrmHTPSemakan";
		actionName = "semakanhakmilikrizab";
		target = "div_isexist";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		
	}

	function TambahHakmilikByIdPermohonan(idPermohonan) {	
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.mode.value = "addHakmilik";
		document.${formName}.INS_UPD.value = "INSERT";
		doAjaxCall${formName}("paparDetailHakmilik2");
	
	}
	
function UpdateHakmilik(idPermohonan,idHakmilik){
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.idHakmilik.value = idHakmilik;
	document.${formName}.mode.value = "updateHakmilik";
	document.${formName}.INS_UPD.value = "update";
	doAjaxCall${formName}("paparDetailHakmilik2");
}

	function doValidation() {
		if(document.${formName}.txtIdKementerian.value == ""){ 
			alert('Sila pilih " Kementerian " terlebih dahulu.'); 
			document.${formName}.txtIdKementerian.focus();
			return false; 
	 	}	
	 	if(document.${formName}.txtIdAgensi.value == ""){ 
			alert('Sila pilih " Agensi " terlebih dahulu.'); 
			document.${formName}.txtIdAgensi.focus();
			return false; 
	 	}
		if(document.${formName}.socNegeri.value == ""){ 
			alert('Sila pilih " Negeri " terlebih dahulu.'); 
			document.${formName}.socNegeri.focus();
			return false; 
	 	}
		if(document.${formName}.socDaerah.value == ""){ 
			alert('Sila pilih " Daerah " terlebih dahulu.'); 
			document.${formName}.socDaerah.focus();
			return false; 
	 	}
	 	if(document.${formName}.socMukim.value == ""){ 
			alert('Sila pilih " Mukim " terlebih dahulu.'); 
			document.${formName}.socMukim.focus();
			return false; 
	 	} 
		if(document.${formName}.socJenisHakmilik.value == ""){ 
			alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.'); 
			document.${formName}.socJenisHakmilik.focus();
			return false; 
	 	}
	 	if(document.${formName}.txtNoHakmilik.value == ""){ 
			alert('Sila masukkan " No Hakmilik " terlebih dahulu.'); 
			document.${formName}.txtNoHakmilik.focus();
			return false; 
	 	}
		if(document.${formName}.noLot.value == ""){ 
			alert('Sila pilih " Kod " terlebih dahulu.'); 
			document.${formName}.noLot.focus();
			return false; 
	 	}
		if(document.${formName}.txtNoLot.value == ""){ 
			alert('Sila pilih " No Lot " terlebih dahulu.'); 
			document.${formName}.txtNoLot.focus();
			return false; 
	 	}
	 	if(document.${formName}.txdTarikhTerima.value == ""){ 
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
			document.${formName}.txdTarikhTerima.focus();
			return false; 
	 	}
	 	if(document.${formName}.txdTarikhDaftar.value == ""){ 
			alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
			document.${formName}.txdTarikhDaftar.focus();
			return false; 
	 	}
	 	if(document.${formName}.socTaraf.value == ""){ 
			alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
			document.${formName}.socTaraf.focus(); 
			return false; 
		}	
	 	if(document.${formName}.socTaraf.value == "P"){
	 		if(document.${formName}.txtTempoh.value == ""){
				alert('Sila masukkan " Tempoh " terlebih dahulu.'); 
				document.${formName}.txtTempoh.focus(); 
				return false; 
			} 
	 	}
	 	if(document.${formName}.socTaraf.value == "S"){
	 		if(document.${formName}.txdTarikhLuput.value != '' || document.${formName}.txtTempoh.value != ''){
	 	
				if (!window.confirm("Maklumat Tarikh Luput dan Tempoh Akan dikosongkan ?") ){
		   			return false;
	 			} 	   			
	 			document.${formName}.txdTarikhLuput.value = "";
		   		document.${formName}.txtTempoh.value = "";
	 		}
	 	}
	 	if(document.${formName}.txtCukaiTahun.value == ""){ 
			alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
			document.${formName}.txtCukaiTahun.focus();
			return false; 
	 	}
	 	if(document.${formName}.txtLokasi.value == ""){ 
			alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
			document.${formName}.txtLokasi.focus();
			return false; 
	 	}
	 	if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return false; 
	 	}			
	 	if(document.${formName}.socLuas.value == "" || document.${formName}.socLuas.value == "0"){ 
			alert('Sila masukkan " Unit Luas " terlebih dahulu.'); 
			document.${formName}.socLuas.focus();
			return false; 
	 	}   
	 	if(document.${formName}.socLuas.value == '7'){
	 		if(document.${formName}.txtLuas5.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas5.focus();
				return false; 
	 		}
			if(document.${formName}.txtLuas6.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas6.focus();
				return false; 
	 		} 
	 	}else if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
	
	 		if(document.${formName}.txtLuas2.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas2.focus();
				return false; 
	 		}
			if(document.${formName}.txtLuas3.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas3.focus();
				return false; 
	 		}
	 		if(document.${formName}.txtLuas3.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas3.focus();
				return false; 
	 		}
	 	
	 	}else{
	 		if(document.${formName}.txtLuas1.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas1.focus();
				return false;
			}
			 
	 	} 	
	 	
	 	/*if(document.${formName}.socJenisRizab.value == ""){ 
			alert('Sila pilih " Rizab " terlebih dahulu.'); 
			document.${formName}.socJenisRizab.focus();	
			return false; 
		 }*/
		 
		 if(document.${formName}.socJenisRizab.value == "Y"){
		 	if(document.${formName}.txtNoWarta.value == ""){
		 		alert('Sila Masukkan " No. Warta " terlebih dahulu.'); 
				document.${formName}.txtNoWarta.focus();
				return false; 
			}
			if(document.${formName}.txdTarikhWarta.value == ""){
		 		alert('Sila Masukkan " Tarikh Warta " terlebih dahulu.'); 
				document.${formName}.txdTarikhWarta.focus();
				return false; 
			}
		 } 
		 
	
		 if(document.${formName}.socKategori.value == ""){ 
			alert('Sila masukkan " Kategori " terlebih dahulu.'); 
			document.${formName}.socKategori.focus();
			return false; 
		 }
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
		   return;
	 	}
	
	 	return true;
	 
	}

function validateTarikhWarta(){

		akhir_bulan = document.${formName}.txdTarikhTerima.value.substring(3,5);
  		akhir_hari = document.${formName}.txdTarikhTerima.value.substring(0,2);
  		akhir_tahun = document.${formName}.txdTarikhTerima.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
  		mula_bulan = document.${formName}.txdTarikhWarta.value.substring(3,5);
 		mula_hari = document.${formName}.txdTarikhWarta.value.substring(0,2);
  		mula_tahun = document.${formName}.txdTarikhWarta.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
	
		var mula = Date.parse(mulatemp);
		var txdTerima = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
	  	
	  	if(mula>tarikhsemasa){
	    	alert("Sila pastikan Tarikh Warta tidak melebihi dari Tarikh Semasa.");
	    	document.${formName}.txdTarikhWarta.value = "";
	    	return;
	  	}
	  	
	  	if(txdTerima>tarikhsemasa){
	    	alert("Sila pastikan Tarikh Terima tidak melebihi dari Tarikh Semasa.");
	    	document.${formName}.txdTarikhTerima.value = "";
	    	return;
	  	}	  
	  	
	  	if(txdTerima<mula){
	    	alert("Sila pastikan Tarikh Warta tidak melebihi dari Tarikh Terima.");
	    	return;
	  	}

}

function validateTarikhHakmilik(){

		akhir_bulan = document.${formName}.txdTarikhTerima.value.substring(3,5);
  		akhir_hari = document.${formName}.txdTarikhTerima.value.substring(0,2);
  		akhir_tahun = document.${formName}.txdTarikhTerima.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
  		mula_bulan = document.${formName}.txdTarikhDaftar.value.substring(3,5);
 		mula_hari = document.${formName}.txdTarikhDaftar.value.substring(0,2);
  		mula_tahun = document.${formName}.txdTarikhDaftar.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
	
		var mula = Date.parse(mulatemp);
		var txdTerima = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
	  	
	  	if(mula>tarikhsemasa){
	    	alert("Sila pastikan Tarikh Daftar tidak melebihi dari Tarikh Semasa.");
	    	document.${formName}.txdTarikhWarta.value = "";
	    	return;
	  	}
	  	
	  	if(txdTerima>tarikhsemasa){
	    	alert("Sila pastikan Tarikh Terima tidak melebihi dari Tarikh Semasa.");
	    	document.${formName}.txdTarikhTerima.value = "";
	    	return;
	  	}	  
	  	
	  	if(txdTerima<mula){
	    	alert("Sila pastikan Tarikh Daftar tidak melebihi dari Tarikh Terima.");
	    	return;
	  	}

}

function doChangeKodLuas(val) {
	document.${formName}.mode.value = "doChangeKodLuas";
	doAjaxCall${formName}("paparDetailHakmilik2");
}

function formatCurrencyPertama(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	document.${formName}.txtCukaiTahun.value = num + '.' + cents;
}

function formatCurrencySemasa(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	document.${formName}.txtCukaiTerkini.value = num + '.' + cents;
}

	function doHakmilikPage2() {
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}('paparDetailHakmilik2','idPermohonan=$idPermohonan')
	}
	
	function doRizabPage2() {
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}('paparrizabterperinci','idPermohonan=$idPermohonan')
	}

//semakan Tarikh semasa
function validateTarikhSemasa(inputfield) {
	var today = new Date();	
	dari_bulan = inputfield.value.substring(3,5);
	dari_hari = inputfield.value.substring(0,2);
	dari_tahun = inputfield.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
	var myDate = Date.parse(daritemp);

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		inputfield.value="";
  		inputfield.focus();
 		return;
 	}

}

	// WARTA PTP
	
	function TambahRizabByIdPermohonan(idPermohonan) {
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.mode.value = "addRizab";
		document.${formName}.INS_UPD.value = "INSERT";
		doAjaxCall${formName}("tambahRizab");
		
	}

// Pilihan skrin Pendaftaran Rizab
function doChangeKementerianRizabPer() {
	document.${formName}.mode.value="doChangeKementerianHRizab";
  	doAjaxCall${formName}("tambahRizab");
}

function doChangeStateRizabPer() {
	document.${formName}.mode.value="doChangeStateRizab";
	doAjaxCall${formName}("tambahRizab");
	
}

function doChangeDaerahRizabPer() {
	document.${formName}.mode.value="doChangeDaerahRizab";
	doAjaxCall${formName}("tambahRizab");
}

//Tidak digunakan semasa kemaskini melalui senarai
function simpanRizabPermohonan(id){
	//VALIDATAION
	if(document.${formName}.txtIdKementerian.value == ""){ 
		alert('Sila pilih " Kementerian " terlebih dahulu.'); 
		document.${formName}.txtIdKementerian.focus();
		return false; 
 	}	
 	if(document.${formName}.txtIdAgensi.value == ""){ 
		alert('Sila pilih " Agensi " terlebih dahulu.'); 
		document.${formName}.txtIdAgensi.focus();
		return false; 
 	}	
	
	 if(document.${formName}.socNegeri.value == 99999 || document.${formName}.socNegeri.value==""){ 
		alert('Sila masukkan " Negeri " terlebih dahulu.'); 
		document.${formName}.socNegeri.focus();
		return; 
	 }
	 if(document.${formName}.socDaerah.value == 99999 || document.${formName}.socDaerah.value==""){ 
		alert('Sila masukkan " Daerah " terlebih dahulu.'); 
		document.${formName}.socDaerah.focus();
		return; 
	 }
	  if(document.${formName}.socMukim.value == 99999 || document.${formName}.socMukim.value==""){ 
		alert('Sila masukkan " Mukim " terlebih dahulu.'); 
		document.${formName}.socMukim.focus();
		return; 
	 }
	 if(document.${formName}.txtNoWarta.value == ""){ 
		alert('Sila masukkan " No. Warta " terlebih dahulu.'); 
		document.${formName}.txtNoWarta.focus();
		return; 
	 }	 
	 if(document.${formName}.socLot.value == ""){ 
		alert('Sila pilih " Kod " terlebih dahulu.'); 
		document.${formName}.socLot.focus();
		return false; 
 	}
	if(document.${formName}.txtNoLot.value == ""){ 
		alert('Sila pilih " No Lot " terlebih dahulu.'); 
		document.${formName}.txtNoLot.focus();
		return false; 
 	}
	if(document.${formName}.txdTarikhWarta.value == ""){ 
		alert('Sila masukkan " Tarikh Warta " terlebih dahulu.'); 
		document.${formName}.txdTarikhWarta.focus();
		return; 
	}	 
	if(document.${formName}.txdTarikhTerima.value == ""){ 
		alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
		document.${formName}.txdTarikhTerima.focus();
		return; 
	}
	if(document.${formName}.txtLokasi.value == ""){ 
		alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
		document.${formName}.txtLokasi.focus();
		return; 
	 }
	 if(document.${formName}.txtKegunaanTanah.value == ""){ 
		alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
		document.${formName}.txtKegunaanTanah.focus();
		return; 
	 }			
	 if(document.${formName}.socLuas.value == ""){ 
		alert('Sila masukkan " Unit Luas " terlebih dahulu.'); 
		document.${formName}.socLuas.focus();
		return; 
	 }   
	 /*if(document.${formName}.txtLuas.value == "" && document.${formName}.txtLuasGabung.value == ""){ 
		alert('Sila masukkan " Luas " terlebih dahulu.'); 
		document.${formName}.txtLuas.focus();
		return; 
	 }*/
	if(document.${formName}.socLuas.value == '7'){
 		if(document.${formName}.txtLuas5.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas5.focus();
			return false; 
 		}
		if(document.${formName}.txtLuas6.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas6.focus();
			return false; 
 		} 
 	}else if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "8"){

 		if(document.${formName}.txtLuas1.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas1.focus();
			return false; 
 		}
		if(document.${formName}.txtLuas2.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas2.focus();
			return false; 
 		}
 		if(document.${formName}.txtLuas3.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas3.focus();
			return false; 
 		}
 	
 	}else{
 		//if(document.${formName}.txtLuas.value == "" && document.${formName}.txtLuasGabung.value == ""){ 
 		if(document.${formName}.txtLuas1.value == "" ){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas1.focus();
			return false;
		
		}else{
			if(isNaN(document.${formName}.txtLuas1.value)) {
				alert('Sila pastikan format "Luas" adalah nombor.'); 
				document.${formName}.txtLuas1.focus();
				return;
			}

		}

 	} 

	 if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
		if(document.${formName}.socLuas.value == "4"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
		}else if(document.${formName}.socLuas.value == "7"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
		}else if(document.${formName}.socLuas.value == "8"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
		}
	 }else{
		if(document.${formName}.socLuas.value == "1"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"KM";
	 	}else if(document.${formName}.socLuas.value == "2"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"H";
	 	}else if(document.${formName}.socLuas.value == "3"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"MP";
	 	}else{
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
	 	}
	 }
	     
	 if ( !window.confirm("Adakah Anda Pasti ?") ){
		   return;
	 }
	 
	 //doAjaxCall${formName}("tambahRizabSimpan","idPermohonan="+id);
	 doAjaxCall${formName}("tambahRizabSimpan");

	 
}

	//15/11/2010
	function cetakMaklumatHakmilik(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	//15/11/2010
	function cetakMaklumatRizab(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function semakWarta(){
		url = "../servlet/ekptg.view.htp.FrmHTPSemakan";
		actionName = "semakanrizab";
		target = "div_isexist";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		
	}
	
	function kosongCarian_(){
		document.${formName}.socNegeri.value = "";
		document.${formName}.socDaerah.value = "";
		document.${formName}.socMukim.value = "";
		document.${formName}.txtNoHakmilik.value = "";
		doAjaxCall${formName}("");
	}
	
	

</script>
