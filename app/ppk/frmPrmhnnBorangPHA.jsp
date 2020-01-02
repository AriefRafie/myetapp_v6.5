<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
}
.style41 {color: #000000}
.style42 {color: #0000FF}
.style44 {color: #FF0000}
-->
</style>
<body>
<fieldset>
<legend>PEMBAHAGIAN PUSAKA KECIL</legend>


<table width="100%" border="0">
    <tr>
        <td>
        <input type="hidden" name="hitButt">
        <input type="hidden" name="mode">
        <input type="hidden" name="eventStatus">
        <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
        <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
        <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
        <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
        <input type="hidden" name="idpermohonansimati" value="$idpermohonansimati">
        #foreach($list in $View)
            #set ($id = $list.idPermohonan)
            #set ($idPemohon = $list.idPemohon)
            #set ($idSimatix = $list.idSimati)
        <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
        <input name="idPermohonan" type="hidden"  value="$id"/>
        <input name="idPemohon" type="hidden"  value="$idPemohon"/>
        <input name="idtemp" type="hidden"  value="$id"/>
        #end
        <input type="hidden" name="idSimati" value="$idSimati">
        </td>
    </tr>
<tr>
<td>
#foreach ($fail in $DetailPemohon)
	#set ($nofail1 = $fail.nofaillama)
    #set ($idfail2 = $fail.idfail)
    #set ($nofail2 = $fail.nofail)
	#set ($keterangan = $fail.keterangan)
    #set ($seksyen = $fail.seksyen)
	#set ($tarikhMohon = $fail.tarikhmohon)
	#set ($pemohon = $fail.namaPemohon)
    #set ($namasimati = $fail.namasimati)
#end
    <fieldset>
<legend>MAKLUMAT UNTUK BORANG P</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
      #if ($idStatus == "0" || $idStatus == "160" || $idStatus == "171")  
       <tr>
          <td width="35%" style="text-transform:uppercase; "><div align="right">No Rujukan Lama :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42">$!nofail1</div></td>
        </tr>
      #else
        <tr>
          <td width="35%" style="text-transform:uppercase; "><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42">$!nofail2
            <input type="hidden" name="idfail2" value="$idfail2" />
          </div></td>
        </tr>
      #end   
      <tr>
          <td style="text-transform:uppercase;" valign="top"><div align="right">Status Fail :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$!keterangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">17</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style42">$!tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style42">$!namasimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$!pemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
		
      </table>
    </div></td>
  </tr>
</table>
</fieldset>  
#set ($disabled = "")
		#if ($new_data!="yes")
      #if ($EventStatus == 2 || $EventStatus == 3)
          #foreach($Data in $DataHa)
          #set ($idJenisHa = $Data.id_Jenisha)
          #set ($bhgnmati1 = $Data.basimati)
          #set ($bhgnmati2 = $Data.bbsimati)
          #set ($norujukan = $Data.noDaftar)
          #set ($nilaitarikhmati = $Data.nilaitarikhmati)
          #set ($nosijil = $Data.nosijil)
          #set ($nilaitarikhmohon = $Data.nilaitarikhmohon)
          #set ($bilunit = $Data.bilunit)
          #set ($nilaiseunit = $Data.nilaiseunit)
          #set ($agensi = $Data.jenama)
          #set ($catatan = $Data.catatan)
          #set ($add1 = $Data.alamatha1)
          #set ($add2 = $Data.alamatha2)
          #set ($add3 = $Data.alamatha3)
          #set ($poskod = $Data.poskod)
          #set ($negeri = $Data.idnegeri)
          #set ($daerah = $Data.iddaerah)
          #end
      #end
      #end  
      #if ($EventStatus == 2)
      #set ($disabled = "disabled readonly class=disabled")
      #end 
</td>
</tr>
<tr>
<td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
   <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView()">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HAview()">HARTA ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="PengesahanView()">PENGESAHAN PERMOHONAN</li>
  </ul>
   <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    #if ($EventStatus=="0" || $EventStatus == "2" || $EventStatus == "3") 
    <fieldset><legend>MAKLUMAT HARTA ALIH </legend>
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
      <td><input name="txtNoSijil" id="txtNoSijil" type="text" style="width: 225px; text-transform:uppercase;" maxlength="50" value="$!nosijil" $disabled/></td>
    </tr>
    #end
    <tr>
    <tr>
      <td scope="row" align="right">Bahagian Simati :</td>
       <td>
         <input name="txtBahagianSimatiHA1" type="text" id="txtBahagianSimatiHA1" value="$!bhgnmati1" size="5" maxlength="5"  $disabled />
         / <input name="txtBahagianSimatiHA2" type="text" id="txtBahagianSimatiHA2" value="$!bhgnmati2" size="5" maxlength="5" $disabled />       </td>
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
      <td valign="top" align="right">
      #if ($socJenisHa == "7" || $socJenisHa == 8 || $socJenisHa == 9 || $socJenisHa == 10 || $socJenisHa == 11 || $socJenisHa == 12 || $socJenisHa == 98 ||$idJenisHa == 7|| $idJenisHa == 8 || $idJenisHa == 9 || $idJenisHa == 10 || $idJenisHa == 11 || $idJenisHa == 12 || $idJenisHa == 98) 
      <span class="style44">*</span>Catatan :
      #else
      Catatan :
      #end</td>
      <td valign="top"><textarea name="txtCatatan" id="txtCatatan" cols="25" rows="4" style="width: 225px; text-transform:uppercase;" $disabled>$!catatan</textarea>      </td>
       <td scope="row" align="right">&nbsp;</td>
       <td>&nbsp;</td>
    </tr>   
  </tbody>
</table>
#end
					<table>
                    <tr>
                    <td><i><font color="red" style="font-size:11px">Perhatian</font> <font style="font-size:11px">: Sila pastikan label bertanda <span class="style44">*</span></font> <font style="font-size:11px">diisi.</font></i></td>
                    </tr>
                    <tr>
                    <td><i><font color=red style=font-size:11px>Perhatian</font> <font style=font-size:11px>: Sila pastikan membuat pengesahan penghantaran di bahagian PENGESAHAN PERMOHONAN</font></i></td>
                    </tr>
                    </table>

</fieldset>
#if ($idStatus == "160" || $idStatus == "171" || $idStatus == "21" )
  #if ($EventStatus == 1)
   #elseif ($EventStatus == 2)

<table width="100%"><tr><td align="center">
<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="getKemaskini('2','0','0','0')">
<input type="button" name="cmdHapus" value="Hapus" onClick="getHapus('2','0','0','0')">
<input type="button" name="cmdTambah" value="Tambah" onClick="HAview('2','0','0','0')">
</td></tr></table>
#elseif ($EventStatus == 3)
<table width="100%"><tr><td align="center">
<input type="button" name="cmdSimpan" value="Simpan" onClick="getUpdate('2','0','0','0')">
<input type="button" name="cmdBatal" value="Batal" onClick="HAview('2','0','0','0')"></td>
</tr></table>
#else
<table width="100%"><tr><td align="center">
<input type="button" name="cmdSimpan" value="Simpan" onClick="getSimpan('2','0','0','0')">
<input type="reset" name="cmdKosong" value="Batal">
</td></tr></table>
#end
#end
<fieldset><legend>Senarai Harta Alih</legend>
<table width="100%" bordercolor="#333333">
<tr class="table_header">
<td style="text-transform:uppercase;">Bil</td>
<td style="text-transform:uppercase;">Jenis Harta Alih</td>
<td style="text-transform:uppercase;">No Rujukan UPT / No Daftar / No Akaun / No Ahli</td>
<td align="center"><input type="checkbox" name="chk" disabled="disabled"></td>
</tr>
#set ($cnt = 0)
#set ($keterangan = "")
#set ($noDaftar = "")
#set ($nilai_tarikhmohon = "")
#set ($simati_ba = "")
#set ($simati_bb = "")
#set ($totalup = 0.00)
 #set($plko=0)

#foreach($list in $listHaBaru)
 #set($plko=$plko+1)
#set ($cnt = $list.bil)
#set ($id3 = $list.id_Ha)
#set ($keterangan = $list.Keterangan)
#set ($noDaftar = $list.noDaftar)
#set ($nilai_tarikhmohon = $list.nilai_tarikhmohon)
#set ($simati_ba = $list.basimati)
#set ($simati_bb = $list.bbsimati)

#if ($idpermohonansimati == $idpermohonansimati)
   	#set ($i = $velocityCount)
      #if (($i % 2) == 0)
     		#set ($row = "row4")
      #else
     		#set ($row = "row4")
      #end
      #set ($y = "disabled")
#else
	#set ($y = "")
 	#set ($row = "row3")
#end
#set ($totalup = $totalup + $list.nilai_tarikhmohon)
<input type="hidden" name="idha" value="$id3">
<tr bgcolor="white">
<td class="$row">$!plko</td>
<td class="$row" style="text-transform:uppercase;"><a href="javascript:edit_hartaalih('$id3')" class="style4 style42">$!keterangan </a></td>
<td class="$row" style="text-transform:uppercase;">$!noDaftar</td>
<td class="$row" align="center"><span class="style41"><input type="checkbox" name="chkbox" value="$id3" $y ></span></td>
</tr>
#end

#if($listHa.size != 0)

#foreach($list in $listHa)
#if ($list.pilih != 0)
	#set($y="checked")
#else
    #set($y="")
#end
#set($plko=$plko+1)
#set ($cnt = $list.bil)
#set ($id3 = $list.id_Ha)
#set ($keterangan = $list.Keterangan)
#set ($noDaftar = $list.noDaftar)
#set ($nilai_tarikhmohon = $list.nilai_tarikhmohon)
#set ($simati_ba = $list.basimati)
#set ($simati_bb = $list.bbsimati)
 #if($plko%2!=0)
#if ($idpermohonansimati == $idpermohonansimati)
   	#set ($i = $velocityCount)
      #if (($i % 2) == 0)
     		#set ($row = "row3")
      #else
     		#set ($row = "row3")
      #end
     
#else
	
 	#set ($row = "row3")
#end
#set ($totalup = $totalup + $list.nilai_tarikhmohon)
<input type="hidden" name="idha" value="$id3">
<tr bgcolor="white">
<td class="$row">$!plko</td>
<td class="$row" style="text-transform:uppercase;"><a href="javascript:edit_hartaalih('$id3')" class="style4 style42">$!keterangan </a></td>
<td class="$row" style="text-transform:uppercase;">$!noDaftar</td>
<td class="$row" align="center"><span class="style41"><input type="checkbox" name="chkbox" value="$id3" $y ></span></td>
</tr>
#end
#end
#end
#if ($listHa.size() > 0)
    #if ($y != "disabled")
    <tr>
        <td height="40px" align="right" colspan="7"><input type="button" name="flag_btn" value="Simpan" onClick="javascript:simpanhakmilik()"></td>
    </tr>
    #end
#end


<tr>
    <td colspan="3"></td>
</tr>
</table>
<br>      
<table width="50%">
    <tr>
    <td colspan="2">NOTA :</td>
    </tr>
    <tr>
    <td width="3%" bgcolor="#FAFAFA">&nbsp;</td>
    <td width="47%">&nbsp;Rekod Terkini</td>
    </tr>
    <tr>
    <td width="3%" bgcolor="#E2E2E2">&nbsp;</td>
    <td width="47%">&nbsp;Rekod Terdahulu</td>
    </tr>
</table>
</fieldset>	
    </div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
</td>
</tr>
</table>

</fieldset>
<script type="text/javascript">
function HtaamView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}('Htaam');
	document.${formName}.submit();
}

function HAview() {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}('harta_alih');
	document.${formName}.submit();
}

function HtaamViewX() {
	document.${formName}.method="post";
	document.${formName}.mode.value="HtaamviewX";
	doAjaxCall${formName}('HtaamX');
	document.${formName}.submit();
}

function PengesahanView() {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}

function setSelected()
 {
    document.${formName}.tabIdatas.value = tabIdatas;
    document.${formName}.tabIdtengah.value = tabIdtengah;
    document.${formName}.tabIdbawah.value = tabIdbawah;	
	document.${formName}.tabIdtepi.value = tabIdtepi;	
}

function getFormHa(){

	document.${formName}.mode.value="tambah_harta";
	document.${formName}.eventStatus.value="0";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function getJenisHa(){
	document.${formName}.mode.value="tambah_harta";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function getSimpan(){

	
	if (document.${formName}.socJenisHartaAlih.value=="0"){
		alert("Sila pilih Jenis Harta Alih");
		document.${formName}.socJenisHartaAlih.focus();
		return;
	}
	
	else if ((document.${formName}.socJenisHartaAlih.value == "7" || document.${formName}.socJenisHartaAlih.value == 8 
			|| document.${formName}.socJenisHartaAlih.value == 9 || document.${formName}.socJenisHartaAlih.value == 10 
			|| document.${formName}.socJenisHartaAlih.value == 11 || document.${formName}.socJenisHartaAlih.value == 12 
			|| document.${formName}.socJenisHartaAlih.value == 98) && document.${formName}.txtCatatan.value == ""){
	
			alert("Sila masukkan Catatan");
			document.${formName}.txtCatatan.focus();
			return;
			
	} 
	
	else if ((document.${formName}.socJenisHartaAlih.value == "1" || document.${formName}.socJenisHartaAlih.value == 2 || document.${formName}.socJenisHartaAlih.value == 3 
			|| document.${formName}.socJenisHartaAlih.value == 4 || document.${formName}.socJenisHartaAlih.value == 5) && document.${formName}.txtAgensi.value == ""){
	
			alert("Sila masukkan Agensi");
			document.${formName}.socPegawai.focus(); 
			return;
	} 
	
	else if (document.${formName}.socJenisHartaAlih.value == "1" && document.${formName}.txtNoRujukan.value == ""){
	
			alert("Sila masukkan No Ahli");
			document.${formName}.txtNoRujukan.focus();
			return;
			
	}
	
    else if (document.${formName}.socJenisHartaAlih.value == "2" && document.${formName}.txtNoRujukan.value == ""){
	
			alert("Sila masukkan No Akaun");
			document.${formName}.txtNoRujukan.focus();
			return;
			
	} 
	
	else if (document.${formName}.socJenisHartaAlih.value == "3" && document.${formName}.txtNoRujukan.value == ""){
	
			alert("Sila masukkan No Daftar");
			document.${formName}.txtNoRujukan.focus();
			return;
	
	}
	
	else if (document.${formName}.socJenisHartaAlih.value == "4" && document.${formName}.txtNoRujukan.value == ""){
	
			alert("Sila masukkan No Rujukan UPT");
			document.${formName}.txtNoRujukan.focus();
			return;
	
	}
	else if (document.${formName}.socJenisHartaAlih.value == "5" && document.${formName}.txtNoRujukan.value == ""){
	
			alert("Sila masukkan No Polisi");
			document.${formName}.txtNoRujukan.focus();
			return;
	
	}
	else{
		input_box = confirm("Adakah anda pasti?");
		if (input_box==true) {
			document.${formName}.method="post";
			doAjaxCall${formName}("harta_alih");
			document.${formName}.eventStatus.value="1";
			document.${formName}.mode.value="simpan_harta";
			document.${formName}.submit();
		}
	}
}

function edit_hartaalih(id3){
	document.${formName}.method="post";
	document.${formName}.eventStatus.value="2";
	document.${formName}.idha.value=id3;
	document.${formName}.mode.value="edit_harta";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function getKemaskini(){
	document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_harta";
	document.${formName}.eventStatus.value="3";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function getHapus(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("harta_alih");
		document.${formName}.mode.value="hapus_harta";
		document.${formName}.eventStatus.value="1";
		document.${formName}.submit();
	}
}

function getUpdate(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("harta_alih");
		document.${formName}.mode.value="update_harta";
		document.${formName}.eventStatus.value="2";
		document.${formName}.submit();
	}
}
function getBatal(){
		document.${formName}.method="post";
		document.${formName}.mode.value="batal_harta";
		document.${formName}.eventStatus.value="1";
		doAjaxCall${formName}("harta_alih");
		document.${formName}.submit();
}

function getDaerah(){
	document.${formName}.method="post";
	document.${formName}.mode.value="get_daerah";
	document.${formName}.eventStatus.value="0";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function simpanhakmilik(){ 
	/*var total = 0;
	var max = ${formName}.chkbox.length;
	for (var idx = 0; idx < max; idx++) {
	if (eval("document.${formName}.chkbox[" + idx + "].checked") == true) {
		total += 1;
	   }		
	}
	if (total == 0){
			alert("Sila pilih 'Harta Tak Alih' terlebih dahulu");
		}else{*/
			document.${formName}.method="post";
			document.${formName}.mode.value="simpanflagAlih";
			doAjaxCall${formName}("harta_alih");
			document.${formName}.submit();
		//}
	
}

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
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:0});
//-->
</script>
</body>

