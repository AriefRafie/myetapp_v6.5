<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
}
.style42 {color: #0000FF}
.style44 {font-size: 10; }
.style45 {color: #FF0000}

-->
</style>
<body>
<fieldset>
<legend>Pembahagian Pusaka Kecil</legend>
<fieldset>
<legend>Maklumat untuk Borang A</legend>
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
  <li class="TabbedPanelsTab" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
   <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HAview('2','0','0','0')">HARTA ALIH</li>
     #if($hideTabPengesahan != "hide")
    <li class="TabbedPanelsTab" tabindex="0" onClick="PengesahanView('3','0','0','0')">PENGESAHAN PERMOHONAN</li>
    #end
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
          
              #set ($nilaitarikhmohon = $Data.nilaitarikhmohon)
              #set ($nilaitarikh_mati = $Data.nilaitarikhmati) 
		      #set ($idJenisHa = $Data.id_Jenisha)
		      #set ($basimati = $Data.basimati)
		      #set ($bbsimati = $Data.bbsimati)
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
      #end      </td>
	  </tr>
	  <tr>
	  <td>    
#if ($EventStatus=="0" || $EventStatus == "2" || $EventStatus == "3") 
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
			        	<option value="$listJenis.idjenisha" $selected1>$listJenis.kod - $listJenis.keterangan</option>
			        	#end
			        #end
			        <option value="">SILA PILIH</option>
			        #foreach($listJenis1 in $ViewJenisHa)
			        	<option value="$listJenis1.idjenisha">$listJenis1.kod - $listJenis1.keterangan</option>
			        #end
	        	#elseif ($EventStatus == 2 || $EventStatus == 3 )
		        	#set ($selected2 = "")
		        	#foreach($listJenis in $ViewJenisHa)
			        	#if ($listJenis.idjenisha == $idJenisHa)
			        		#set ($selected2 = "selected")
			        	<option value="$listJenis.idjenisha" $selected2>$listJenis.kod - $listJenis.keterangan</option>
						#end
		        	#end
		        	 <option value="">SILA PILIH</option>
		        	#foreach($listJenis1 in $ViewJenisHa)
		        		#if ($listJenis1.idjenisha != $idJenisHa)
		        	<option value="$listJenis1.idjenisha">$listJenis1.kod - $listJenis1.keterangan</option>
		        		#end
		        	#end
		        #else
		         	<option value="">SILA PILIH</option>
		        	#foreach($listJenis1 in $ViewJenisHa)
		        	<option value="$listJenis1.idjenisha">$listJenis1.kod - $listJenis1.keterangan</option>
		        	#end
	        	#end
			      
		        	
      </select><input type="hidden" name="valueSocHa" value="$socJenisHa"><input type="hidden" name="valueJenisHa" value="$idJenisHa"></td>
    </tr>
    #if ($socJenisHa == "1" || $socJenisHa == "4" || $socJenisHa == "2" || $socJenisHa == "5" || $socJenisHa == "3" 
    || $idJenisHa == 1 || $idJenisHa == 4  || $idJenisHa == 5 || $idJenisHa == 3 || $socJenisHa == "6" || $idJenisHa == 6 || $idJenisHa == 2)
     <tr> 
      <td align="right">
      #if ($socJenisHa == "2" || $idJenisHa == 2 || $socJenisHa == "1" || $idJenisHa == 1 || $socJenisHa == "5" || $idJenisHa == 5 || $socJenisHa == "6" || $idJenisHa == 6)
      <font class="mandatory" color="#FF0000">*</font>Agensi:
      #elseif ($socJenisHa == "3" || $idJenisHa == 3)
      <font class="mandatory" color="#FF0000">*</font>Jenama:
      #elseif ($socJenisHa == "4" || $idJenisHa == 4)
      <font class="mandatory" color="#FF0000">*</font>No. Lot:
      #end      </td>
      #if ($socJenisHa == "1" || $socJenisHa == "4" || $socJenisHa == "2" || $socJenisHa == "5" || $socJenisHa == "3" 
      || $idJenisHa == 4 || $idJenisHa == 2 || $idJenisHa == 5 || $idJenisHa == 3 || $socJenisHa == "6" || $idJenisHa == 6 || $idJenisHa == 1)
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
      <td scope="row" align="right"><font class="mandatory" color="#FF0000">*</font>No. Sijil :</td>
      <td><input name="txtNoSijil" id="txtNoSijil" type="text" style="width: 225px; text-transform:uppercase;" maxlength="50" value="$!nosijil" $disabled /></td>
    #end    </tr>
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
         <input name="txtNilaiTarikhMohon" id="txtNilaiTarikhMohon" type="text" style="width: 225px; "  $disabled value="$nilaitarikhmohon" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value);samakan()"/>
       #end
          </td>
       <td scope="row" align="right">&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
     <tr>
       <td scope="row" align="right"><font class="mandatory" color="#FF0000">*</font>Anggaran Nilai Tarikh Mati :</td>
       <td>
       #if($EventStatus == "2")
       <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 225px; " $disabled value="$Util.formatDecimal($nilaitarikh_mati)" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>
       #end
       #if($EventStatus == "3")
       <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 225px; " $disabled value="$nilaitarikh_mati" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>
       #end
        #if($EventStatus == "0")
        <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 225px; " $disabled value="$nilaitarikhmohon" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)"/>
        #end
       </td>
       <td scope="row" align="right">&nbsp;</td>
       <td>&nbsp;</td>
     </tr><tr>
      <td valign="top" align="right">
      #if ($socJenisHa == "6" || $socJenisHa == "7" || $socJenisHa == "8" || $socJenisHa == "9" || $socJenisHa == "10" 
      || $socJenisHa == "11" || $socJenisHa == "12" || $socJenisHa == "0" || $socJenisHa == "98") 
      <font color="#FF0000">*</font> Catatan :
      #else
      Catatan :
      #end</td>
      <td valign="top"><textarea name="txtCatatan" id="txtCatatan" cols="25" rows="4" style="width: 225px; text-transform:uppercase;" $disabled>$!catatan</textarea>      </td>
       <td scope="row" align="right">&nbsp;</td>
       <td>&nbsp;</td>
    </tr>   
	<tr>
	<td colspan="2" valign="bottom" align="left">
	<i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></i>					</td>
	</tr>
  </tbody>
</table>
#end
</fieldset>
  #if ($idstatus=="150" || $idstatus=="171")
  #if ($EventStatus == 1)
   #elseif ($EventStatus == 2)

<p align="center">
<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="getKemaskini('2','0','0','0')">
<input type="button" name="cmdHapus" value="Hapus" onClick="getHapus('2','0','0','0')">
<input type="button" name="cmdTambah" value="Tambah" onClick="HAview('2','0','0','0')">
</p>
#elseif ($EventStatus == 3)
<p align="center">
<input type="button" name="cmdSimpan" value="Simpan" onClick="getUpdate('2','0','0','0')">
<input type="button" name="cmdBatal" value="Batal" onClick="HAview('2','0','0','0')">

</p>
#else
<p align="center">
  <input type="button" name="cmdSimpan2" value="Simpan" onClick="getSimpan('2','0','0','0')">
  <input type="reset" name="cmdBatal" value="Batal">
</p>
#end
#end</td>
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
<td style="text-transform:uppercase;"><a href="javascript:edit_hartaalih('$id3')"><div class="style42">$keterangan</div></a></td>
<td style="text-transform:uppercase;">$noDaftar</td>
</tr>
#end
</table>
</fieldset>    </td>
  </tr>
</table>
	
	</div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
</td>
</tr>
</table>
</fieldset>
</fieldset>
<script>
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content2);
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	if (elmnt.value == "0.00"){
		elmnt.value = "";
		return;
	}else{
		elmnt.value = num.toFixed(2);
		return;
	}
}
function samakan()
{
document.${formName}.txtNilaiTarikhMati.value=document.${formName}.txtNilaiTarikhMohon.value              
}
function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	doAjaxCall${formName}("Waris");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}("Htaam");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.socJenisHartaAlih.value="";
	document.${formName}.submit();
}
function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="view_nilai_harta";
	document.${formName}.hitButt.value="nilai_harta";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Penghutangview";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Pemiutangview";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}
function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Saksiview";
	document.${formName}.hitButt.value="Saksi";	
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;	
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
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function getJenisHa(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="tambah_harta";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function getSimpan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){

	if (document.${formName}.socJenisHartaAlih.value==""){
		alert("Sila pilih Jenis Harta Alih");
		document.${formName}.socJenisHartaAlih.focus();
		return;
	}

	else if((document.${formName}.socJenisHartaAlih.value=="1" || document.${formName}.socJenisHartaAlih.value=="2" 
			|| document.${formName}.socJenisHartaAlih.value=="5" || document.${formName}.socJenisHartaAlih.value=="6")
			&& document.${formName}.txtAgensi.value==""){

		alert("Sila masukkan Agensi");
		document.${formName}.txtAgensi.focus();
		return;
		
	}

	else if(document.${formName}.socJenisHartaAlih.value=="4" && document.${formName}.txtAgensi.value==""){
		alert("Sila masukkan No Lot");
		document.${formName}.txtAgensi.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="3" && document.${formName}.txtAgensi.value==""){
		alert("Sila masukkan Jenama");
		document.${formName}.txtAgensi.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="1" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Ahli");
		document.${formName}.txtNoRujukan.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="2" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Akaun");
		document.${formName}.txtNoRujukan.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="3" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Daftar");
		document.${formName}.txtNoRujukan.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="4" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Rujukan UPT");
		document.${formName}.txtNoRujukan.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="5" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Polisi");
		document.${formName}.txtNoRujukan.focus();
		return;
	}
	
	else if(document.${formName}.socJenisHartaAlih.value=="1" && document.${formName}.txtNoSijil.value ==""){	
		alert("Sila masukkan No Sijil");
		document.${formName}.txtNoSijil.focus();
		return;
	}
	
	else if (document.${formName}.txtNilaiTarikhMohon.value=="0.00" || document.${formName}.txtNilaiTarikhMohon.value==""
			|| document.${formName}.txtNilaiTarikhMohon.value=="0"){
		alert("Sila masukkan Anggaran Nilai Tarikh Mohon");
		document.${formName}.txtNilaiTarikhMohon.focus();
		return;
	}
	
	else if (document.${formName}.txtNilaiTarikhMati.value=="0.00" || document.${formName}.txtNilaiTarikhMati.value==""
		 	|| document.${formName}.txtNilaiTarikhMati.value=="0"){
		alert("Sila masukkan Anggaran Nilai Tarikh Mati");
		document.${formName}.txtNilaiTarikhMati.focus();
		return;
	}
	
	else if ((document.${formName}.socJenisHartaAlih.value == "6" || document.${formName}.socJenisHartaAlih.value == "7"
			|| document.${formName}.socJenisHartaAlih.value == "8" || document.${formName}.socJenisHartaAlih.value == "9" 
			|| document.${formName}.socJenisHartaAlih.value == "10" || document.${formName}.socJenisHartaAlih.value == "11" 
			|| document.${formName}.socJenisHartaAlih.value == "12" || document.${formName}.socJenisHartaAlih.value == "0"
			|| document.${formName}.socJenisHartaAlih.value == "98") && document.${formName}.txtCatatan.value == ""){
	
			alert("Sila masukkan Catatan");
			document.${formName}.txtCatatan.focus();
			return;
	} 
	
	else{
		input_box=confirm("Adakah anda pasti?");
		if (input_box==true) {
			document.${formName}.method="post";
			doAjaxCall${formName}("harta_alih");
			document.${formName}.mode.value="simpan_harta";
			document.${formName}.eventStatus.value="1";
			document.${formName}.tabIdatas.value=tabIdatas;
			document.${formName}.tabIdtengah.value=tabIdtengah;
			document.${formName}.tabIdbawah.value=tabIdbawah;
			document.${formName}.tabIdtepi.value=tabIdtepi;
			document.${formName}.action.value="";
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
	document.${formName}.tabIdatas.value="2";
	document.${formName}.tabIdtengah.value="0";
	document.${formName}.tabIdbawah.value="0";
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function getKemaskini(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_harta";
	document.${formName}.eventStatus.value="3";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function getHapus(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("harta_alih");
		document.${formName}.mode.value="hapus_harta";
		document.${formName}.eventStatus.value="1";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
		document.${formName}.submit();
	}
}

function getUpdate(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		
	if (document.${formName}.socJenisHartaAlih.value==""){
		alert("Sila pilih Jenis Harta Alih");
		document.${formName}.socJenisHartaAlih.focus();
		return;
	}

	else if((document.${formName}.socJenisHartaAlih.value=="1" || document.${formName}.socJenisHartaAlih.value=="2" 
			|| document.${formName}.socJenisHartaAlih.value=="5" || document.${formName}.socJenisHartaAlih.value=="6")
			&& document.${formName}.txtAgensi.value==""){

		alert("Sila masukkan Agensi");
		document.${formName}.txtAgensi.focus();
		return;
		
	}

	else if(document.${formName}.socJenisHartaAlih.value=="4" && document.${formName}.txtAgensi.value==""){
		alert("Sila masukkan No Lot");
		document.${formName}.txtAgensi.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="3" && document.${formName}.txtAgensi.value==""){
		alert("Sila masukkan Jenama");
		document.${formName}.txtAgensi.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="1" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Ahli");
		document.${formName}.txtNoRujukan.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="2" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Akaun");
		document.${formName}.txtNoRujukan.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="3" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Daftar");
		document.${formName}.txtNoRujukan.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="4" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Rujukan UPT");
		document.${formName}.txtNoRujukan.focus();
		return;
	}

	else if(document.${formName}.socJenisHartaAlih.value=="5" && document.${formName}.txtNoRujukan.value==""){
		alert("Sila masukkan No Polisi");
		document.${formName}.txtNoRujukan.focus();
		return;
	}
	
	else if(document.${formName}.socJenisHartaAlih.value=="1" && document.${formName}.txtNoSijil.value ==""){	
		alert("Sila masukkan No Sijil");
		document.${formName}.txtNoSijil.focus();
		return;
	}
	
	else if (document.${formName}.txtNilaiTarikhMohon.value=="0.00" || document.${formName}.txtNilaiTarikhMohon.value==""
			|| document.${formName}.txtNilaiTarikhMohon.value=="0"){
		alert("Sila masukkan Anggaran Nilai Tarikh Mohon");
		document.${formName}.txtNilaiTarikhMohon.focus();
		return;
	}
	
	else if (document.${formName}.txtNilaiTarikhMati.value=="0.00" || document.${formName}.txtNilaiTarikhMati.value==""
		 	|| document.${formName}.txtNilaiTarikhMati.value=="0"){
		alert("Sila masukkan Anggaran Nilai Tarikh Mati");
		document.${formName}.txtNilaiTarikhMati.focus();
		return;
	}
	
	else if (document.${formName}.txtCatatan.value==""){
		alert("Sila masukkan Catatan");
		document.${formName}.txtCatatan.focus();
		return;
	}
	
	else if ((document.${formName}.socJenisHartaAlih.value == "6" || document.${formName}.socJenisHartaAlih.value == "7"
			|| document.${formName}.socJenisHartaAlih.value == "8" || document.${formName}.socJenisHartaAlih.value == "9" 
			|| document.${formName}.socJenisHartaAlih.value == "10" || document.${formName}.socJenisHartaAlih.value == "11" 
			|| document.${formName}.socJenisHartaAlih.value == "12" || document.${formName}.socJenisHartaAlih.value == "0"
			|| document.${formName}.socJenisHartaAlih.value == "98") && document.${formName}.txtCatatan.value == ""){
	
			alert("Sila masukkan Catatan");
			document.${formName}.txtCatatan.focus();
			return;
	} 
	
	else{	
	
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			document.${formName}.mode.value="update_harta";
			document.${formName}.eventStatus.value="2";
			doAjaxCall${formName}("harta_alih");
			document.${formName}.action.value="";
			document.${formName}.tabIdatas.value=tabIdatas;
			document.${formName}.tabIdtengah.value=tabIdtengah;
			document.${formName}.tabIdbawah.value=tabIdbawah;
			document.${formName}.tabIdtepi.value=tabIdtepi;
			document.${formName}.submit();
		}
	}
	
}

function getBatal(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="batal_harta";
		document.${formName}.eventStatus.value="1";
		doAjaxCall${formName}("harta_alih");
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
		document.${formName}.submit();
}

function getDaerah(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.mode.value="get_daerah";
	document.${formName}.eventStatus.value="0";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
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
