
#set ($NamaSimati = "")
#set ($setmode = "")
#set ($setbutton = "")

#if ($EventStatus == 1)
	#set ($setmode = "disabled class=disabled")
#else
	#set ($setmode = "")
#end

#if ($sorAdaHTA=="1")
	#set ($checked1 = "checked")
	#set ($checked2 = "")
	#set ($setbutton = "")

#elseif ($sorAdaHTA=="2")
	#set ($checked1 = "")
	#set ($checked2 = "checked")
	#set ($setbutton = "disabled")
#end

#if ($submission == 1)
	#set ($setbutton = "disabled")
#end

#if ($sorAdaHTA == 1 && $TempDb == 1)
	#set ($noKpBaru1 = $noKPBaru1)
	#set ($noKpBaru2 = $noKPBaru2)
	#set ($noKpBaru3 = $noKPBaru3)
	#set ($noKpLama = $noKPLama)
	#set ($JenisKpLain = $jenisKPLain)
	#set ($noKpLain = $noKPLain)
	#set ($NamaPemohon = $namaPemohon)
	#set ($SocWaris = $socWaris)
	#set ($SocOb = $socOb)
	#set ($checked1 = "checked")
#end

#if ($sorWaris == 1)
	#set ($checked3 = "checked")
#elseif ($sorWaris == 2)
	#set ($checked4 = "checked")
#end

#if ($IdAlert == 1)
<script type="text/javascript">
	window.location="?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
	alert("Maaf. Maklumat Simati telah wujud.");
</script>
#elseif ($IdAlert == 2)
<script type="text/javascript">
	window.location="?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
	alert("Maaf. Permohonan Seksyen 17 masih lagi dalam proses.");
</script>
#end


<body onLoad="submitForm()">
<input type="hidden" name="v_tab" id="v_tab" value="" />
#if($hartaYa != 'Ya')
<fieldset>
Sila pastikan anda mempunyai harta tak alih. Sekiranya tiada, permohonan tidak dapat diteruskan.
<br>
<br>
<input name="cmdHartaYa" type="button" value="Ada Harta" onClick="hartaYes()">
<input type="button" name="cmdTiadaHarta" id="cmdTiadaHarta" value="Tiada Harta" onClick="hartaTiada()">

</fieldset>
#end
#if($hartaYa == 'Ya')
<p></p>
#if ($submission == 0)
<fieldset>
<legend>Maklumat Pemohon</legend>
<table border="0" align="center" width="100%">
    <tbody>
      <tr> 
        <td width="20%" height="24" scope="row" align="right">&nbsp;No. KP Baru</td>
        <td width="80%"> : <input name="txtNoKPBaru1" id="txtNoKPBaru1" style="width: 50px;" type="text" value="$!noKpBaru1" size="7" maxlength="6" $setmode onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2')" onBlur="javascript:checkkp(this,this.value)"/>-<input name="txtNoKPBaru2" id="txtNoKPBaru2" style="width: 20px;" type="text" value="$!noKpBaru2" size="3" maxlength="2" $setmode onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')"/>-<input name="txtNoKPBaru3" id="txtNoKPBaru3"  style="width: 40px;" type="text" value="$!noKpBaru3" $setmode size="5" maxlength="4" onBlur="genderByIC(this,this.value,'txtNoKPBaru3')"/></td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;No. KP Lama</td>
        <td > : <input name="txtNoKPLama" type="textbox" id="txtNoKPLama" maxlength="8" size="10" value="$!noKpLama" style="width: 120px; text-transform:uppercase;" $setmode>&nbsp;&nbsp;<i>(Sila kosongkan ruangan ini jika tidak berkaitan)</i></td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;Lain-lain KP</td>
        <td > : <select name="socJenisKPLain" style="width:120px;" $setmode>
            #set ($id = "")
            #set ($keterangan = "")
            #set ($selected = "")
	            #foreach($Listkp in $listkp)
	            #set ($id = $Listkp.id)
	            #set ($keterangan = $Listkp.keterangan)
	            	#if ($id == $JenisKpLain)
	            		#set ($selected = "selected")
	            	<option value="$id" $selected />$keterangan.toUpperCase()</option>
	            	#end
	            #end
            	<option value="0"/>SILA PILIH KP</option>
            	#foreach($Listkp in $listkp)
            	#set ($id = $Listkp.id)
           		#set ($keterangan = $Listkp.keterangan)
	            	<option value="$id"/>$keterangan.toUpperCase()</option>
            	#end
          </select> <input name="txtNoKPLain" id="txtNoKPLain" style="width:97px; text-transform:uppercase;" type="text" value="$noKpLain" maxlength="9" $setmode />&nbsp;&nbsp;<i>(Sila kosongkan ruangan ini jika tidak berkaitan)</i></td>
      </tr>
      <tr> 
        <td scope="row" align="right"><font color="#ff0000">*</font>&nbsp;Nama</td>
        <td > : <input name="txtNamaPemohon" type="text" id="txtNamaPemohon" value="$!NamaPemohon" size="50" maxlength="50" style="width: 250px; text-transform:uppercase;" $setmode></td>
      </tr>
	  <tr>
	  <td colspan="2" height="50px" valign="bottom"><p><i>*&nbsp;Sila pastikan salah 
	    satu No Pengenalan Pemohon diisi.
	    <br>&nbsp;&nbsp;&nbsp;Sila kemaskini No KP dan Nama sekiranya pemohon bukan pengguna daftar online.</i></p></td>
	  </tr>
      </tbody>
      </table>
</fieldset>
<p></p>
<fieldset>
<legend>Maklumat Simati</legend>
<table border="0" align="center" width="100%">
    <tbody>
      <tr> 
        <td width="20%" height="24" scope="row" align="right">&nbsp;No. KP Baru</td>
        <td width="80%"> : <input name="txtNoKPBaru1Simati" id="txtNoKPBaru1Simati" style="width: 50px;" type="text" value="$!noKpBaru1Simati" size="7" maxlength="6" $setmode onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Simati')" onBlur="javascript:checkkp(this,this.value)"/>-<input name="txtNoKPBaru2Simati" id="txtNoKPBaru2Simati" style="width: 20px;" type="text" value="$!noKpBaru2Simati" size="3" maxlength="2" $setmode onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Simati')"/>-<input name="txtNoKPBaru3Simati" id="txtNoKPBaru3Simati"  style="width: 40px;" type="text" value="$!noKpBaru3Simati" $setmode size="5" maxlength="4"/></td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;No. KP Lama</td>
        <td > : <input name="txtNoKPLamaSimati" type="textbox" id="txtNoKPLamaSimati" maxlength="8" size="10" value="$!noKpLamaSimati" style="width: 120px; text-transform:uppercase;" $setmode>&nbsp;&nbsp;<i>(Sila kosongkan ruangan ini jika tidak berkaitan)</i></td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;Lain-lain KP</td>
        <td > : <select name="socJenisKPLainSimati" style="width: 120px;" $setmode>
            #set ($id = "")
            #set ($keterangan = "")
            #set ($selected = "")
	            #foreach($Listkp1 in $listkp)
	            #set ($id1 = $Listkp1.id)
	            #set ($keterangan1 = $Listkp1.keterangan)
	            	#if ($id1 == $JenisKpLainSimati)
	            		#set ($selected = "selected")
	            	<option value="$id1" $selected/>$keterangan1.toUpperCase()</option>
	            	#end
	            #end
            	<option value="0"/>SILA PILIH KP</option>
            	#foreach($Listkp in $listkp)
            	#set ($id = $Listkp.id)
           		#set ($keterangan = $Listkp.keterangan)
	            	<option value="$id"/>$keterangan.toUpperCase()</option>
            	#end
          </select> <input name="txtNoKPLainSimati" id="txtNoKPLainSimati" style="width:97px; text-transform:uppercase;" type="text" value="$!noKpLainSimati" maxlength="9" $setmode/>&nbsp;&nbsp;<i>(Sila kosongkan ruangan ini jika tidak berkaitan)</i></td>
      </tr>
	  <tr> 
        <td scope="row" align="right"><font color="#ff0000">*</font>&nbsp;Nama</td>
        <td > : <input name="txtNamaSimati" type="text" id="txtNamaSimati" value="$!namaSimati" size="50" maxlength="200" style="width: 250px; text-transform:uppercase;" $setmode></td>
      </tr>
	<tr>
		<td colspan="5" valign="bottom">        	
        	<i>
            <font color=red style=font-size:10px>Perhatian</font>
            <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
            <font color=red style=font-size:10px>*</font>&nbsp;
            <font style=font-size:10px>diisi.</font>            
            </i>                   
         </td>
	</tr>
      </tbody>
      </table>
</fieldset>

<p></p>
<fieldset>
<legend>Maklumat Permohonan</legend>
  <table width="100%" align="center" border="0">
    <tbody>
      <tr> 
        <td width="40%" height="24" scope="row" align="left"><font color="#ff0000">*</font>&nbsp;Hubungan Pemohon Dengan Simati</td>

        <td width="30%"><input type="radio" name="sorWaris" id="sorWaris" value="1" $setmode $checked3 onClick="getOne('sorWaris')">Waris</td>
        <td width="30%"><select name="socWaris" $setmode $waris1 style="width:350px;">
        	#set ($idsaudara = "")
        	#set ($kodsaudara = "")
        	#set ($keterangansaudara = "")
        	#set ($selectedx = "")
	        #if ($SocWaris != "0")
	            #foreach ($list1 in $ListPersaudaraan)
	            #set ($idsaudara1 = $list1.id_Saudara)
	        	#set ($kodsaudara1 = $list1.kod)
	        	#set ($keterangansaudara1 = $list1.keterangan)
	            	#if ($idsaudara1 == $SocWaris)
	            		#set ($selectedx = "selected")
	            	<option value="$idsaudara1" $selectedx>$!keterangansaudara1</option>
	            	#end
	            #end
	        	<option value="0">SILA PILIH</option>
	            #foreach ($list in $ListPersaudaraan)
	            #set ($idsaudara = $list.id_Saudara)
	        	#set ($kodsaudara = $list.kod)
	        	#set ($keterangansaudara = $list.keterangan)
	            	<option value="$idsaudara">$!keterangansaudara</option>
	            #end
	        #else 
	        	<option value="0">SILA PILIH</option>
	            #foreach ($list in $ListPersaudaraan)
	            #set ($idsaudara = $list.id_Saudara)
	        	#set ($kodsaudara = $list.kod)
	        	#set ($keterangansaudara = $list.keterangan)
	            	<option value="$idsaudara">$!keterangansaudara</option>
	            #end
	        #end
            </select></td>
      </tr>
      <tr> 
        <td scope="row"></td>
        <td ><input type="radio" name="sorWaris" value="2" id="sorWaris" $setmode $checked4 onClick="getOne('sorWaris')">Orang Berkepentingan</td>
          <td><select name="socOB" $setmode $waris2 style="width:350px;">	 
		 #if ($socOb=="" || $socOb=="0")
		  	  <option value="0" >SILA PILIH</option>
		      #foreach ($listob in $ListOB) 
			  <option value="$listob.idtarafkptg">$!listob.keterangan</option>
			  #end
		 #else
		      #foreach ($listob in $ListOB) 
			  		#if ($listob.idtarafkptg == $socOb)
			  <option value="$listob.idtarafkptg" selected>$!listob.keterangan</option>
			  		#end
			  #end
		 	  <option value="0">SILA PILIH</option>
		      #foreach ($listob in $ListOB) 
			  <option value="$listob.idtarafkptg">$!listob.keterangan</option>
			  #end			
		 #end
          </select></td>
      </tr>
      <tr> 
        <td scope="row"></td>
        <td >&nbsp;</td>
        <td >&nbsp;</td>
      </tr>
      <tr> 
        <td scope="row" align="left"><font color="#ff0000">*</font>&nbsp;Adakah SiMati Memiliki Harta Tak Alih</td>
        <td valign="top"><input name="sorAdaHTA" type="radio" id="sorAdaHTA" onClick="getYa('sorAdaHTA',this.value)" value="1" checked $checked1 $setmode> Ya </td>
        <td >&nbsp;</td>
      </tr>
      <!--<tr>
        <td scope="row"></td>
        <td valign="top"><input type="radio" name="sorAdaHTA" id="sorAdaHTA" onClick="getTidak('sorAdaHTA',this.value)" value="2" $checked2 $setmode> Tidak </td>
        <td >&nbsp;</td>
      </tr>-->
	<tr>
		<td colspan="5" valign="bottom">        	
        	<i>
            <font color=red style=font-size:10px>Perhatian</font>
            <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
            <font color=red style=font-size:10px>*</font>&nbsp;
            <font style=font-size:10px>diisi.</font>            </i>         </td>
	</tr>      
       <tr>
        <td colspan="3" valign="bottom">
        <div align="center">
          <input type="button" name="cmdSimpan" value="Simpan" $setbutton onClick="getSimpan()">
         <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="getSemula('txtNoKPBaru1')"></div></td>
      </tr>
    </tbody>
  </table>
</fieldset>
#end
#end
#if ($submission == 1 && $IdAlert == 0)
#set ($namePemohon = "")
#set ($icPemohon = "")
#set ($noPemohon = "")
#set ($IdPemohonan = "")
<p></p>
#foreach ($detailPemohon in $DetailPemohon)
	#set ($namePemohon = $detailPemohon.namaPemohon)
	#set ($icPemohon = $detailPemohon.noKpBaruPemohon)
	#set ($noPemohon = $detailPemohon.noPermohonan)
	#set ($IdPemohonan = $detailPemohon.idPermohonan)
	#set ($noKpBaru1 = $detailPemohon.noKpBaru1)
	#set ($noKpBaru2 = $detailPemohon.noKpBaru2)
	#set ($noKpBaru3 = $detailPemohon.noKpBaru3)
	#set ($noKpLama = $detailPemohon.noKpLama)
	#set ($noKpLain = $detailPemohon.noKpLain)
	#set ($nokpbarusimati = $detailPemohon.nokpbarusimati)
	#set ($nokplamasimati = $detailPemohon.nokplamasimati)
	#set ($nokplainsimati = $detailPemohon.nokplainsimati)
<input type="hidden" name="txtNoKPBaru1pemohon" value="$detailPemohon.noKpBaru1">
<input type="hidden" name="txtNoKPBaru2pemohon" value="$detailPemohon.noKpBaru2">
<input type="hidden" name="txtNoKPBaru3pemohon" value="$detailPemohon.noKpBaru3">
<input type="hidden" name="txtNoKPLamapemohon" value="$detailPemohon.noKpLama">
<input type="hidden" name="txtNoKPLainpemohon" value="$detailPemohon.noKpLain">

<input type="hidden" name="txtNoKPBaru1a" value="$detailPemohon.nokpbarusimati1">
<input type="hidden" name="txtNoKPBaru2a" value="$detailPemohon.nokpbarusimati2">
<input type="hidden" name="txtNoKPBaru3a" value="$detailPemohon.nokpbarusimati3">
<input type="hidden" name="txtNoKPLamaa" value="$detailPemohon.nokplamasimati">
<input type="hidden" name="txtNoKPLaina" value="$detailPemohon.nokplainsimati">

<input type="hidden" name="submission" value="$submission">
<input type="hidden" name="IdAlert" value="$IdAlert">

<input type="hidden" name="hitButt" >
#end
<fieldset>
<table width="100%">
<tr>
<td colspan="2" align="center"><font color="red"><b>PERHATIAN</b></font><br><br>Sila gunakan nombor Kad Pengenalan baru dan No. Permohonan untuk mengemaskini permohonan anda.</td>
</tr>
<tr>
<td height="20px" colspan="2">&nbsp;</td>
</tr>
<tr>
<td align="right" width="30%">Nama : </td>
<td width="70%"><b>$!namePemohon.toUpperCase()</b></td>
</tr>
<tr>
<td align="right">No. KP Pemohon : </td>
<td><b>$!icPemohon</b></td>
</tr>
<tr>
<td align="right">No. KP Simati : </td>
<td><b>
#if ($!nokpbarusimati!="")
$!nokpbarusimati 
#elseif ($!nokplamasimati!="")
$!nokplamasimati.toUpperCase()
#elseif ($!nokplamasimati!="")
$!nokplainsimati.toUpperCase()
#end 
</b></td>
</tr>
<tr>
<td align="right">No Rujukan : </td>
<td><b>$!noPemohon.toUpperCase()</b></td>
</tr>
<tr>
<td align="right" height="50px" colspan="2"><input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSlip('$noPemohon')"><input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="getSeterusnya()"></td>
</tr>
</table>
</fieldset>
#end
	<input type="hidden" name="hitButt"/>
	<input type="hidden" name="command"/>
	<input type="hidden" name="idPermohonan" value="$IdPemohonan"/>
    <input name="hartaYa" type="hidden" value="$hartaYa">
	
<script>
/*function hartaYes(){
	
	
	//alert("no");document.${formName}.method="post";
	document.${formName}.hartaYa.value = "Ya";
	document.${formName}.action="";
	document.${formName}.submit();
	
}*/

function hartaYes() {
	document.${formname}.method="post";
	document.${formName}.hartaYa.value = "Ya";
	document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
	//doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}


function getForm() {
document.${formName}.method="post";
document.${formName}.hitButt.value="cetak_report";
document.${formName}.action.value="";
document.${formName}.submit();
}

function cancel() {
document.${formName}.reset();
document.${formName}.txtNoFail.value = "";
document.${formName}.txtNoFail.focus();
}

function Tambah() {
	document.${formName}.hitButt.value = "pemohon_tambah";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function getOne(v_t) {
	document.${formName}.socOB.disabled == true;
	document.${formName}.hitButt.value = "pemohon_waris";
	document.${formName}.action = "";
	document.${formName}.v_tab.value = v_t;
	document.${formName}.socOB.selectedIndex = "0";
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.hitButt.value = "";
	//document.${formName}.nama_fail.value = key;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function edit_item(id,id2){
	document.${formName}.hitButt.value = "pemohon_papar";
	document.${formName}.action = "";
	document.${formName}.idPermohonan.value = id;
	document.${formName}.idSimati.value = id2;
	document.${formName}.submit();
}

function getTidak(v_t,val){
	alert("Maaf, permohonan anda tidak dapat diteruskan kerana simati tidak mempunyai harta tak alih");
	document.${formName}.method="post";
	document.${formName}.hitButt.value = "pemohon_tidak";
	document.${formName}.sorAdaHTA[0].checked=false;
	document.${formName}.sorAdaHTA[1].checked=true;
	document.${formName}.v_tab.value = v_t;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function getSemula(v_t){
	document.${formName}.method="post";
	document.${formName}.hitButt.value="";
	document.${formName}.v_tab.value = v_t;
	document.${formName}.txtNoKPBaru1Simati.value="";
	document.${formName}.txtNoKPBaru2Simati.value="";
	document.${formName}.txtNoKPBaru3Simati.value="";
	document.${formName}.action="";
	document.${formName}.submit();
}

function getYa(v_t,val){
	document.${formName}.method="post";
	document.${formName}.hitButt.value="pemohon_ya";
	document.${formName}.sorAdaHTA[0].checked=true;
	document.${formName}.sorAdaHTA[1].checked=false;
	document.${formName}.v_tab.value = v_t;
	document.${formName}.action="";
	document.${formName}.submit();

	genderByIC();
}

function getSimpan(){
	var negeri_code1= document.${formName}.txtNoKPBaru2Simati.value;
	var dob_code3 = document.${formName}.txtNoKPBaru1Simati.value;
	
	var negeri_code= document.${formName}.txtNoKPBaru2.value;
	var dob_code1 = document.${formName}.txtNoKPBaru1.value;
	if(dob_code1.charAt(0)<3) {
	 	var dum1 = "20";
	}
	else {
		var dum1 = "19";
	}
	
	if(dob_code3.charAt(0)<3) {
	 	var dum2 = "20";
	}
	else {
		var dum2 = "19";
	}

	var tt1 = dob_code1.charAt(4)+""+dob_code1.charAt(5)+"/"+dob_code1.charAt(2)+""+dob_code1.charAt(3)+"/"+dum1+dob_code1.charAt(0)+""+dob_code1.charAt(1);	
	var dt_dob1   = parseInt(tt1.substring(0,2),10);
    var mon_dob1  = parseInt(tt1.substring(3,5),10)-1;
    var yr_dob1   = parseInt(tt1.substring(6,10),10);
	var date_dob1 = new Date(yr_dob1, mon_dob1, dt_dob1);
	
	var tt3 = dob_code3.charAt(4)+""+dob_code3.charAt(5)+"/"+dob_code3.charAt(2)+""+dob_code3.charAt(3)+"/"+dum2+dob_code3.charAt(0)+""+dob_code3.charAt(1);	
	var dt_dob3   = parseInt(tt3.substring(0,2),10);
    var mon_dob3  = parseInt(tt3.substring(3,5),10)-1;
    var yr_dob3   = parseInt(tt3.substring(6,10),10);
	var date_dob3 = new Date(yr_dob3, mon_dob3, dt_dob3);
	
	var currentTime = new Date()
	var minYear = 1900;
	var maxYear = 2100;

	if (isNaN(document.${formName}.txtNoKPBaru1.value)){
		alert("Sila masukkan nombor sahaja");
		document.${formName}.txtNoKPBaru1.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru2.value)){
		alert("Sila masukkan nombor sahaja");
		document.${formName}.txtNoKPBaru2.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru3.value)){
		alert("Sila masukkan nombor sahaja");
		document.${formName}.txtNoKPBaru3.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value == "" && document.${formName}.txtNoKPBaru2.value == "" && document.${formName}.txtNoKPBaru3.value == "" && document.${formName}.txtNoKPLama.value == "" && document.${formName}.txtNoKPLain.value == ""){
		alert("Sila masukkan salah satu No KP");
		document.${formName}.txtNoKPBaru1.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value != "" && document.${formName}.txtNoKPBaru1.value.length < 6){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru1.focus();
	}
	else if (document.${formName}.txtNoKPBaru2.value != "" && document.${formName}.txtNoKPBaru2.value.length < 2){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru2.focus();
	}
	else if (document.${formName}.txtNoKPBaru3.value != "" && document.${formName}.txtNoKPBaru3.value.length < 4){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru3.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value != "" && document.${formName}.txtNoKPBaru2.value == "" && document.${formName}.txtNoKPBaru3.value == ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru2.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value != "" && document.${formName}.txtNoKPBaru2.value != "" && document.${formName}.txtNoKPBaru3.value == ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru3.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value != "" && document.${formName}.txtNoKPBaru2.value == "" && document.${formName}.txtNoKPBaru3.value != ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru2.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value == "" && document.${formName}.txtNoKPBaru2.value != "" && document.${formName}.txtNoKPBaru3.value != ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru1.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value != "" && isIc(tt1)==false){
	  	document.${formName}.txtNoKPBaru1.focus();
	}
	else if (document.${formName}.txtNoKPBaru2.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" && negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" && negeri_code!="15" && negeri_code!="58" && negeri_code!="16" && negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99")) {
		alert("Sila masukkan kod negeri yang sah");
		document.${formName}.txtNoKPBaru2.focus()
	}
	else if (document.${formName}.txtNoKPLama.value != "" && document.${formName}.txtNoKPLama.value.length < 7){
		alert("Sila masukkan NO KP Lama Sepenuhnya");
		document.${formName}.txtNoKPLama.focus();
	}
	else if (document.${formName}.txtNoKPLain.value != "" && document.${formName}.socJenisKPLain.value == "0"){
		alert("Sila pilih jenis No KP");
		document.${formName}.socJenisKPLain.focus();
	}
	else if (document.${formName}.socJenisKPLain.value != "0" && document.${formName}.txtNoKPLain.value == ""){
		alert("Sila masukkan No KP Lain");
		document.${formName}.txtNoKPLain.focus();
	}
	else if (document.${formName}.txtNamaPemohon.value==""){
		alert("Sila masukkan Nama Pemohon");
		document.${formName}.txtNamaPemohon.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value == "" && document.${formName}.txtNoKPBaru2Simati.value == "" && document.${formName}.txtNoKPBaru3Simati.value == "" && document.${formName}.txtNoKPLamaSimati.value == "" && document.${formName}.txtNoKPLainSimati.value == ""){
		alert("Sila masukkan salah satu No KP");
		document.${formName}.txtNoKPBaru1Simati.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru1Simati.value)){
		alert("Sila masukkan nombor sahaja");
		document.${formName}.txtNoKPBaru1Simati.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru2Simati.value)){
		alert("Sila masukkan nombor sahaja");
		document.${formName}.txtNoKPBaru2Simati.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru3Simati.value)){
		alert("Sila masukkan nombor sahaja");
		document.${formName}.txtNoKPBaru3Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && document.${formName}.txtNoKPBaru1Simati.value.length < 6){
		alert("Sila masukkan No KP Baru sepenuhnya 1");
		document.${formName}.txtNoKPBaru1Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru2Simati.value != "" && document.${formName}.txtNoKPBaru2Simati.value.length < 2){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru2Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru3Simati.value != "" && document.${formName}.txtNoKPBaru3Simati.value.length < 4){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru3Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && document.${formName}.txtNoKPBaru2Simati.value == "" && document.${formName}.txtNoKPBaru3Simati.value == ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru2Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && document.${formName}.txtNoKPBaru2Simati.value != "" && document.${formName}.txtNoKPBaru3Simati.value == ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru3Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && document.${formName}.txtNoKPBaru2Simati.value == "" && document.${formName}.txtNoKPBaru3Simati.value != ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru2Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value == "" && document.${formName}.txtNoKPBaru2Simati.value != "" && document.${formName}.txtNoKPBaru3Simati.value != ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.${formName}.txtNoKPBaru1Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && isIc(tt3)==false){
	  	document.${formName}.txtNoKPBaru1Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru2Simati.value != "" &&(negeri_code1!="01" && negeri_code1!="21" && negeri_code1!="22" && negeri_code1!="23" && negeri_code1!="24" && negeri_code1!="02" && negeri_code1!="25" && negeri_code1!="26" && negeri_code1!="27" && negeri_code1!="03" && negeri_code1!="28" && negeri_code1!="29" && negeri_code!="04" && negeri_code1!="30" && negeri_code1!="05" && negeri_code1!="31" && negeri_code1!="59" && negeri_code1!="06" && negeri_code1!="32" && negeri_code1!="33" && negeri_code1!="07" && negeri_code1!="34" && negeri_code1!="35" && negeri_code1!="08" && negeri_code1!="36" && negeri_code1!="37" && negeri_code1!="38" && negeri_code1!="39"  && negeri_code1!="09" && negeri_code1!="40" && negeri_code1!="10" && negeri_code1!="41" && negeri_code1!="42" && negeri_code1!="43" && negeri_code1!="44" && negeri_code1!="11" && negeri_code1!="45" && negeri_code1!="46" && 
		negeri_code1!="12" && negeri_code1!="47" && negeri_code1!="48" && negeri_code1!="49" && negeri_code1!="13" && negeri_code1!="50" && negeri_code1!="51" && negeri_code1!="52" && negeri_code1!="53" && negeri_code1!="14" && negeri_code1!="54" && negeri_code1!="55" && negeri_code1!="56" && negeri_code1!="57" && negeri_code1!="15" && negeri_code!="58" && negeri_code1!="16" && negeri_code1!="82" && negeri_code1!="71" && negeri_code1!="88" && negeri_code1!="99")) {
		alert("Sila masukkan kod negeri yang sah");
		document.${formName}.txtNoKPBaru2Simati.focus()
	}
	else if (document.${formName}.txtNamaSimati.value==""){
		alert("Sila masukkan Nama Simati");
		document.${formName}.txtNamaSimati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value==document.${formName}.txtNoKPBaru1.value && 
document.${formName}.txtNoKPBaru2Simati.value==document.${formName}.txtNoKPBaru2.value &&
document.${formName}.txtNoKPBaru3Simati.value==document.${formName}.txtNoKPBaru3.value &&
document.${formName}.txtNoKPLamaSimati.value==document.${formName}.txtNoKPLama.value &&

document.${formName}.socJenisKPLainSimati.value==document.${formName}.socJenisKPLain.value &&
document.${formName}.txtNoKPLainSimati.value==document.${formName}.txtNoKPLain.value){
		alert("Pastikan maklumat pemohon dan simati tidak sama")
	}
	else if (document.${formName}.sorWaris[0].checked == false && document.${formName}.sorWaris[1].checked == false){
		alert("Sila tanda salah satu hubungan pemohon dengan simati");
	}
	else if (document.${formName}.sorWaris[0].checked == true && document.${formName}.socWaris.value == "0"){
		alert("Sila pilih hubungan waris");
		document.${formName}.socOB.selectedindex=0;
		document.${formName}.socOB.options[0].selected = true;
	}
	else if (document.${formName}.sorWaris[1].checked == true && document.${formName}.socOB.value == "0"){
		alert("Sila pilih hubungan orang berkepentingan");
		document.${formName}.socWaris.selectedindex=0;
		document.${formName}.socWaris.options[0].selected = true;
	}
	//else if (document.${formName}.sorAdaHTA[0].checked == false && document.${formName}.sorAdaHTA[1].checked == false){
		//alert("Sila tanda salah satu 'Adakah SiMati Memiliki Harta Tak Alih'");
	//}
	else{
	input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			document.${formName}.hitButt.value = "pemohon_simpan";
			document.${formName}.action="";
			document.${formName}.submit();
		}
	}
}

/*function getCetak(value,command){
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnBorangACetak?icPemohon="+value+"&command="+command;
	var hWnd = window.open(url,'printuser','width=600,height=600, resizable=yes,scrollbars=yes');
}*/

function getSeterusnya(){
		document.${formName}.method="post";
		doAjaxCall${formName}("check_kp");	
		//document.${formName}.hitButt.value="check_kp";
		document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAKemaskini";
//alert("?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAKemaskini&hitButt=check_kp&txtNoKPBaru1=$noKpBaru1&txtNoKPBaru2=$noKpBaru2&txtNoKPBaru3=$noKpBaru3&txtNoKPLama=$noKpLama&txtNoKPLain=$noKpLain");
		document.${formName}.submit();
}

function genderByIC(elmnt,content,genderField) {
	if (document.${formName}.sorWaris[0].checked==true){
		document.${formName}.method="post";
		//document.${formName}.socOB.disabled == true;
		document.${formName}.hitButt.value = "pemohon_waris";
		//document.${formName}.v_tab.value = v_t;
		document.${formName}.action="";
		document.${formName}.submit();
	}
}

function cetakSlip(NoPermohonan) {
    var url = "../servlet/ekptg.report.ppk.PermohonanOnline?NoPermohonan="+NoPermohonan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    /*if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/
}

function submitForm() {    
   	// document.val.focus();
	goTo('$val_tab');
	//Effect.ScrollTo('$val_tab').focus(); return false;
	
	//doucument.f1.'$val_tab'.focus();
} 
</script>
<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yg sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yg sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yg sah")
		return false
	}
return true
}


function isIc(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}

function hartaTiada(){
	
	
	//alert("no");document.${formName}.method="post";
	//document.${formName}.hartaYa.value = "Ya";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmOnlineMenuUtama";
	document.${formName}.submit();
	
}


</script>

