<html>
<head>
<title>Untitled Document</title>
<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
}
.style42 {color: #0000FF}

-->
</style>
</head>
<body>
<table width="97%" border="0">
    <tr>
        <td>
#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idsimati)
   <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
   <input name="idPermohonan" type="hidden"  value="$id"/>
   <input name="idPemohon" type="hidden"  value="$idPemohon"/>
   <input name="idSimati" type="hidden"  value="$idSimati"/>
   <input name="idtemp" type="hidden"  value="$id"/>
 #end
 <input name="idpermohonansimati" type="hidden"  value="$idpermohonansimati"/>
 <input type="hidden" name="hitButt">
 <input type="hidden" name="mode">
  <input type="hidden" name="action">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
  <input type="hidden" name="eventStatus">
  <input type="hidden" name="idTemp" value="$id">
  <input type="hidden" name="id" value="$id">
  <input type="hidden" name="id1" value="$id1">
  <input type="hidden" name="id2" value="$id2">
  <input type="hidden" name="id3" value="$id3">
  <input type="hidden" name="idha" value="$idha">
  <input type="hidden" name="bil" value="$jumList">
        </td>
    </tr>
<tr>
<td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  <li class="TabbedPanelsTab" tabindex="0" onClick="SimatiView()">PERMOHONAN</li>
   <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView()">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HAview()">HARTA ALIH</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
	<div class="TabbedPanelsContent">
<br>
	  <table width="97%" border="0">
  <tr>
    <td>
	     #if ($EventStatus == 2 || $EventStatus == 3)
	      #foreach($Data in $DataHa)
		      #set ($idJenisHa = $Data.id_Jenisha)
		      #set ($basimati = $Data.basimati)
		      #set ($bbsimati = $Data.bbsimati)
              #set ($nilaitarikhmohon = $Data.nilaitarikhmohon)
              #set ($nilaitarikhmati = $Data.nilaitarikhmati) 
		      #set ($norujukan = $Data.noDaftar)
		      #set ($nosijil = $Data.nosijil)
		      #set ($bilunit = $Data.bilunit)
		      #set ($nilaiseunit = $Data.nilaiseunit)
		      #set ($agensi = $Data.jenama)
		      #set ($catatan = $Data.catatan)
		      #set ($add1 = $Data.alamatha1)
		      #set ($add2 = $Data.alamatha2)
		      #set ($add3 = $Data.alamatha3)
		      #set ($poskod = $Data.poskod)
		      #set ($daerah = $Data.iddaerah)
		      #set ($negeri = $Data.idnegeri)
	      #end
      #end
      
      #if ($EventStatus == "2")
      	#set ($disabled = "disabled readonly class=disabled")
      #elseif ($EventStatus == "3" || $EventStatus == "0")
      	#set ($disabled = "")
      #end
      </td>
	  </tr>
	  <tr>
	  <td>    
#if ($EventStatus=="0" || $EventStatus == "2" || $EventStatus == "3") </p>
    <fieldset><legend>MAKLUMAT HARTA ALIH</legend>
    <table width="100%" border="0">
  <tbody>
    <tr>
      <td width="30%" scope="row" align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Jenis Harta Alih :</td>
      #if($tutup=="")
	      #set($x=$disabled)
      #end
       #if($tutup=="yes")
	      #set($x="disabled readonly class=disabled")
      #end
       <td width="70%" ><select name="socJenisHartaAlih" id="socJenisHartaAlih" style="width: 225px;" onChange="getJenisHa('2','0','0','0')" $x >
       
	        	#if ($EventStatus == 0)
	        	 	#set ($selected1 = "")
	        		#foreach($listJenis in $ViewJenisHa)
			        	#if ($listJenis.idjenisha == $socJenisHa)
			        		#set ($selected1 = "selected")
			        	<option value="$listJenis.idjenisha" $selected1>$listJenis.keterangan</option>
			        	#end
			        #end
			        <option value="0">SILA PILIH</option>
			        #foreach($listJenis1 in $ViewJenisHa)
			        	<option value="$listJenis1.idjenisha">$listJenis1.keterangan</option>
			        #end
	        	#elseif ($EventStatus == 2 || $EventStatus == 3 )
		        	#set ($selected2 = "")
		        	#foreach($listJenis in $ViewJenisHa)
			        	#if ($listJenis.idjenisha == $idJenisHa)
			        		#set ($selected2 = "selected")
			        	<option value="$listJenis.idjenisha" $selected2>$listJenis.keterangan</option>
						#end
		        	#end
		        	 <option value="0">SILA PILIH</option>
		        	#foreach($listJenis1 in $ViewJenisHa)
		        		#if ($listJenis1.idjenisha != $idJenisHa)
		        	<option value="$listJenis1.idjenisha">$listJenis1.keterangan</option>
		        		#end
		        	#end
		        #else
		         	<option value="0">SILA PILIH</option>
		        	#foreach($listJenis1 in $ViewJenisHa)
		        	<option value="$listJenis1.idjenisha">$listJenis1.keterangan</option>
		        	#end
	        	#end
			      
		        	
      </select><input type="hidden" name="valueSocHa" value="$socJenisHa"><input type="hidden" name="valueJenisHa" value="$idJenisHa"></td>
    </tr>
    #if ($socJenisHa == "1" || $socJenisHa == "4" || $socJenisHa == "2" || $socJenisHa == "5" || $socJenisHa == "3" || $idJenisHa == 4  || $idJenisHa == 5 || $idJenisHa == 3 || $socJenisHa == "6" || $idJenisHa == 6 || $idJenisHa == 2)
     <tr> 
      <td align="right">
      #if ($socJenisHa == "2" || $idJenisHa == 2 || $socJenisHa == "1" || $idJenisHa == 1 || $socJenisHa == "5" || $idJenisHa == 5 || $socJenisHa == "6" || $idJenisHa == 6)
      <font class="mandatory" color="#FF0000">*</font>Agensi:
      #elseif ($socJenisHa == "3" || $idJenisHa == 3)
      <font class="mandatory" color="#FF0000">*</font>Jenama:
      #elseif ($socJenisHa == "4" || $idJenisHa == 4)
      <font class="mandatory" color="#FF0000">*</font>No. Lot:
      #end      </td>
      #if ($socJenisHa == "1" || $socJenisHa == "4" || $socJenisHa == "2" || $socJenisHa == "5" || $socJenisHa == "3" || $idJenisHa == 4 || $idJenisHa == 2 || $idJenisHa == 5 || $idJenisHa == 3 || $socJenisHa == "6" || $idJenisHa == 6)
      <td><input  name="txtAgensi" id="txtAgensi" type="text" style="width: 225px; text-transform:uppercase;" $disabled value="$!agensi" /></td>
      #end    </tr>
    #end
     #if ($socJenisHa == "2" || $socJenisHa == 1 || $socJenisHa == 4 || $socJenisHa == 3 || $socJenisHa == 5 || $idJenisHa == 1|| $idJenisHa == 3 || $idJenisHa == 2 || $idJenisHa == 4) 
    <tr>
      	<td scope="row" align="right">
     #if ($socJenisHa == "2" || $idJenisHa == 2)
     	<font class="mandatory" color="#FF0000">*</font>No. Akaun:
     #elseif ($socJenisHa == "4" || $idJenisHa == 4)
     	<font class="mandatory" color="#FF0000">*</font>No. Rujukan UPT:
     #elseif ($socJenisHa == "1" || $idJenisHa == 1)	
     	<font class="mandatory" color="#FF0000">*</font>No. Ahli:
     #elseif ($socJenisHa == "3" || $idJenisHa == 3)	
     	<font class="mandatory" color="#FF0000">*</font>No. Daftar:
      #elseif ($socJenisHa == "5" || $idJenisHa == 5)	
      <font class="mandatory" color="#FF0000">*</font>No. Polisi:
     #end     </td>
      #if ($socJenisHa == 1 || $socJenisHa == 4 || $socJenisHa == 3 || $socJenisHa == 5 || $idJenisHa == 1|| $idJenisHa == 3|| $idJenisHa == 4) 
      	<td><input name="txtNoRujukan" id="txtNoRujukan" type="text" style="width: 225px; text-transform:uppercase;"  $disabled maxlength="25" value="$!norujukan"/></td> 
      #elseif ($socJenisHa == 2 || $idJenisHa == "2")
      <td><input name="txtNoRujukan" id="txtNoRujukan" type="text" style="width: 225px; text-transform:uppercase;" $disabled maxlength="25" value="$!norujukan"  /></td> 
      #else
      <td> 
          <input name="txtNoRujukan" id="txtNoRujukan" type="text" style="width: 225px; text-transform:uppercase;" $disabled readonly maxlength="25" value="$!norujukan" />      </td>
      #end     </tr>
      #end
    #if ($socJenisHa == 1 || $idJenisHa == 1) 
    <tr>
      <td scope="row" align="right">No. Sijil :</td>
      <td> 
          <input name="txtNoSijil" id="txtNoSijil" type="text" style="width: 225px; text-transform:uppercase;" maxlength="50" value="$!nosijil" readonly class="disabled"/>      </td>
    #end  
    </tr>
     <tr>
      <td scope="row" align="right">Bahagian Simati :</td>
       <td>
         <input name="txtBahagianSimatiHA1" type="text" id="txtBahagianSimatiHA1" value="$!basimati" size="5" maxlength="5"  $disabled />
         / <input name="txtBahagianSimatiHA2" type="text" id="txtBahagianSimatiHA2" value="$!bbsimati" size="5" maxlength="5" $disabled />       </td>
       <td scope="row" align="right">&nbsp;</td>
       <td>&nbsp;</td>
       </tr>
     <tr>
       <td scope="row" align="right"><font class="mandatory" color="#FF0000">*</font>Anggaran Nilai Tarikh Mohon :</td>
       <td>
       <input name="EventStatus" type="hidden" value="$EventStatus">
       #if($EventStatus == "2")
       		
         <input name="txtNilaiTarikhMohon" id="txtNilaiTarikhMohon" type="text" style="width: 225px; "  $disabled value="$Util.formatDecimal($nilaitarikhmohon)" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>
       #end  
       #if($EventStatus == "3")
        <input name="txtNilaiTarikhMohon" id="txtNilaiTarikhMohon" type="text" style="width: 225px; "  $disabled value="$nilaitarikhmohon" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>#end
       #if($EventStatus=="0")
         <input name="txtNilaiTarikhMohon" id="txtNilaiTarikhMohon" type="text" style="width: 225px; "  $disabled value="$nilaitarikhmohon" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>
       #end
          </td>
       <td scope="row" align="right">&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
     <tr>
       <td scope="row" align="right"><font class="mandatory" color="#FF0000">*</font>Anggaran Nilai Tarikh Mati :</td>
       <td>
       #if($EventStatus == "2")
       <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 225px; " $disabled value="$Util.formatDecimal($nilaitarikhmati)" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>
       #end
       #if($EventStatus == "3")
       <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 225px; " $disabled value="$nilaitarikhmati" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>
       #end
        #if($EventStatus == "0")
        <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 225px; " $disabled value="$nilaitarikhmohon" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>
        #end
       </td>
       <td scope="row" align="right">&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
    <tr>
     <tr>
      <td valign="top" align="right">Catatan :</td>
      <td valign="top"><textarea name="txtCatatan" id="txtCatatan" cols="25" rows="4" style="width: 225px; text-transform:uppercase;" $disabled>$!catatan</textarea>      </td>
    </tr>   
	<tr>
	<td colspan="2" valign="bottom" align="left" height="50px">
					<table>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan label bertanda <font color="#ff0000">*</font> 
                      diisi.</i></td>
                    </tr>
                   
                    </table>
	</td>
	</tr>
  </tbody>
</table>
#end

</fieldset>
  #if ($idstatus=="150" || $idstatus=="171")
  #if ($EventStatus == 1)
   #elseif ($EventStatus == 2)</p>

<p align="center">
<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="getKemaskini('2','0','0','0')">
<input type="button" name="cmdHapus" value="Hapus" onClick="getHapus('2','0','0','0')">
<input type="button" name="cmdTambah" value="Tambah" onClick="HAview('2','0','0','0')">
</p>
#elseif ($EventStatus == 3)
<p align="center">
<input type="button" name="cmdSimpan" value="Simpan" onClick="getUpdate('2','0','0','0')">
<input type="button" name="cmdBatal" value="Batal" onClick="HAview('2','0','0','0')">
<input type="button" name="cmdTambah" value="Tambah" onClick="HAview('2','0','0','0')">
</p>
#else
<p align="center">
<input type="button" name="cmdSimpan" value="Simpan" onClick="getSimpan('2','0','0','0')">
<input type="reset" name="cmdBatal" value="Batal">
</p>
#end
#end
</td>
</tr>
<tr>
<td>
<fieldset><legend>Senarai Harta Alih</legend>
<table width="100%" bordercolor="#333333">
<tr class="table_header">
<td style="text-transform:uppercase;">Bil</td>
<td style="text-transform:uppercase;">Jenis Harta Alih</td>
<td style="text-transform:uppercase;">No Rujukan UPT / No Daftar / No Akaun / No Ahli</td>
</tr>
#set ($cnt = 0)
#set ($keterangan = "")
#set ($noDaftar = "")
#set ($nilai_tarikhmohon = "")
#set ($simati_ba = "")
#set ($simati_bb = "")
#set ($totalup = 0.00)
#foreach($list in $listHa)
#set ($cnt = $list.bil)
#set ($id3 = $list.id_Ha)
#set ($keterangan = $list.Keterangan)
#set ($noDaftar = $list.noDaftar)
#set ($nilai_tarikhmohon = $list.nilai_tarikhmohon)
#set ($simati_ba = $list.basimati)
#set ($simati_bb = $list.bbsimati)

#set ($totalup = $totalup + $list.nilai_tarikhmohon)

<tr bgcolor="white">
<td>$cnt</td>
<td style="text-transform:uppercase;"><a href="javascript:edit_hartaalih('$id3')" class="style4 style42">$keterangan </a></td>
<td style="text-transform:uppercase;">$noDaftar</td>
</tr>
#end
</table>
</fieldset>	
    
    </td>
  </tr>
</table>
	
	</div>
  </div>
</div>
</td>
</tr>
</table>
<script>
<!-- TAB -->
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}
function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.submit();
}
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}
function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.socJenisHartaAlih.value="0";
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Penghutangview";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemiutangview";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.submit();
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}


function cancelwaris() {
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.reset();
	document.${formName}.txtNoKPBaru1Waris.focus();
	}
}

<!-- HARTA ALIH -->
function getFormHa(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.mode.value="tambah_harta";
	document.${formName}.eventStatus.value="0";
	document.${formName}.submit();
}

function getJenisHa(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="tambah_harta";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function getSimpan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	if (document.${formName}.socJenisHartaAlih.value=="0"){
		alert("Sila pilih Jenis Harta Alih");
		socJenisHartaAlih.focus();
	}
	else{
		input_box=confirm("Adakah anda pasti?");
		if (input_box==true) {
			document.${formName}.method="post";
			doAjaxCall${formName}("harta_alih");
			document.${formName}.mode.value="simpan_harta";
			document.${formName}.eventStatus.value="1";
			document.${formName}.submit();
		}
	}
}

function edit_hartaalih(id3){
	document.${formName}.method="post";
	document.${formName}.mode.value="edit_harta";
	document.${formName}.eventStatus.value="2";
	document.${formName}.idha.value=id3;
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function getKemaskini(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_harta";
	document.${formName}.eventStatus.value="3";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function getHapus(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("harta_alih");
		document.${formName}.mode.value="hapus_harta";
		document.${formName}.eventStatus.value="1";
		document.${formName}.submit();
	}
}

function getUpdate(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			document.${formName}.mode.value="update_harta";
			document.${formName}.eventStatus.value="2";
			doAjaxCall${formName}("harta_alih");
			document.${formName}.submit();
	}
}

function getBatal(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="batal_harta";
		document.${formName}.eventStatus.value="1";
		doAjaxCall${formName}("harta_alih");
		document.${formName}.submit();
}

function getDaerah(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.mode.value="get_daerah";
	document.${formName}.eventStatus.value="0";
	document.${formName}.submit();
}

function validLength(){
	if (document.${formName}.txtPoskodPemohon.value!="" && document.${formName}.txtPoskodPemohon.value.length < 5) {
		alert("Sila masukkan No Poskod dengan lengkap.");
		document.${formName}.txtPoskodPemohon.focus();
	}
}
<!-- SIMATI -->
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
//-->
</script>
</body>
</html>
