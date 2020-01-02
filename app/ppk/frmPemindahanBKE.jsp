<style type="text/css">
<!--
.style40 {color: #0000FF}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
<body>
#set ($nofail="")
#set ($namadaerah = "")
#set ($namanegeri = "")
#set ($namaPejabat = "")
#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    #set ($nofail = $list.noFail)
    #set ($idfail = $list.idFail)
    #set ($namadaerah = $list.namadaerah)
    #set ($namanegeri = $list.namanegeri)
    #set ($namaPejabat = $list.nama_pejabat)
	#set ($alamat1 = $list.alamat_1)
    #set ($keterangan = $list.keterangan)
    #set ($seksyen = $list.seksyen)
    #set ($tarikhMohon = $list.tarikhMohon)
    #set ($namaPemohon = $list.namaPemohon)
    #set ($namasimati = $list.namaSimati)
  
#end
<input name="idPermohonan" type="hidden" value="$id"/>
 <input name="idpermohonan" type="hidden" value="$id"/>
<tr>
<td>

#if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<fieldset  style="display:none">
<legend>MAKLUMAT PERMOHONAN</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style40">$!nofail</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Negeri :</div></td>
          <td style="text-transform:uppercase;"><div align="left" class="style40">$!namanegeri</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Daerah / Jajahan :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!namadaerah</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Unit :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!namaPejabat &nbsp; $!alamat1</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!seksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Status Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style40">$!keterangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style40">$!namasimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!namaPemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
		
      </table>
    </div></td>
  </tr>
</table>
</fieldset>
</td>
</tr>


#foreach ($listDataNegeriDaerah in $listNegeriDaerah)
#set ($namanegeri = $listDataNegeriDaerah.namanegeri)
#set ($namadaerah = $listDataNegeriDaerah.namadaerah)
#set ($selidnegeri = $listDataNegeriDaerah.id_negeri)
#set ($seliddaerah = $listDataNegeriDaerah.id_daerah)
#end
 <input type="hidden" name="action">
 <input type="hidden" name="hitButt" >
 <input type="hidden" name="mode" >
  <input type="hidden" name="idPermohonan" value="$idPermohonan">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
#if ($display_info == "yes")
#foreach ($listBke in $listBkeData)
	#set ($idnegeri = $listBke.idnegeri)
	#set ($iddaerah = $listBke.iddaerah)
	#set ($alasan1 = $listBke.alasan1)
	#set ($alasan2 = $listBke.alasan2)
	#set ($alasan3 = $listBke.alasan3)
	#set ($alasan4 = $listBke.alasan4)
	#set ($alasan5 = $listBke.alasan5)
	#set ($alasan6 = $listBke.alasan6)
	#set ($alasan7 = $listBke.alasan7)
	#set ($alasanlain = $listBke.alasanlain)
	#set ($tarikhmohon = $listBke.tarikhmohon)
	#set ($keputusanpegawai = $listBke.keputusanpegawai)
#end
#end
#if ($alasan1!="")
	#set ($checkbx1 = "checked")
#end
#if ($alasan2!="")
	#set ($checkbx2 = "checked")
#end
#if ($alasan3!="")
	#set ($checkbx3 = "checked")
#end
#if ($alasan4!="")
	#set ($checkbx4 = "checked")
#end
#if ($alasan5!="")
	#set ($checkbx5 = "checked")
#end
#if ($alasan6!="")
	#set ($checkbx6 = "checked")
#end
#if ($alasan7!="")
	#set ($checkbx7 = "checked")
#end

#if ($listNegericnt=="0")
	#set ($disabled = "disabled")
    #set ($disabled2 = "readonly class=disabled")
#else
	#set ($disabled = "")
    #set ($disabled2 = "")
#end
  <table width="100%" border="0">
    <tr>
      <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="0" onClick="PemohonBkeView('0','0','0','0')">PERMOHONAN BKE</li>
          #if ($tarikhmohon!="")
          <li class="TabbedPanelsTab" tabindex="0" onClick="KeputusanView('1','0','0','0')">KEBENARAN</li>
          #end
          #if ($keputusanpegawai == "L")
          <li class="TabbedPanelsTab" tabindex="0" onClick="KptgView('2','0','0','0')">KEPUTUSAN KPTG/PTG</li>
          #end
          </ul>
          <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
 		  <input type="hidden" name="namadaerah" value="$seliddaerah">
		  <input type="hidden" name="namanegeri" value="$selidnegeri">
          <table width="100%">
         	<tr>
         	<td align="right" width="30%">Bidang Kuasa Semasa :</td>
         	<td width="70%">$!namadaerah - $!namanegeri </td>
         	</tr>
         	<tr>
         	<td align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri Pindah :</td>
         	<td><select name="socNegeri" style="width: 264px;" onChange="getDaerah()" $readmode $disabled  $disabled2 >
         	#if ($idnegeri != "")
         		#set ($selectNegeri = "")
	         	#foreach ($listDataNegeri in $listNegeri)
	         		#if ($listDataNegeri.id_Negeri == $idnegeri)
	         			#set ($selectNegeri = "selected")
	         		<option value="$listDataNegeri.id_Negeri" $selectNegeri>$!listDataNegeri.kod_Negeri - $!listDataNegeri.nama_Negeri</option>
	         		#end
	         	#end
	         	<option value="0">SILA PILIH</option>
	         	#foreach ($listDataNegeri in $listNegeri)
	         		<option value="$listDataNegeri.id_Negeri">$!listDataNegeri.kod_Negeri - $!listDataNegeri.nama_Negeri</option>
	         	#end
	        #else
            	#if ($listNegericnt==0)
	            	<option value="0" selected>TIADA MAKLUMAT</option>
            	#else
                    <option value="0" selected>SILA PILIH</option>
                    #foreach ($listDataNegeri1 in $listNegeri)
                    <option value="$listDataNegeri1.id_Negeri">$!listDataNegeri1.kod_Negeri - $!listDataNegeri1.nama_Negeri</option>
                    #end
	         	#end	
	        #end
         	</select></td>
         	</tr>
         	<tr>
         	<td align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Daerah Pindah :</td>
         	<td><select name="socDaerah" style="width: 264px;" $daerahmode $readmode>
         	#if ($iddaerah != "")
         			#foreach ($listDaerah in $listDaerah)
         				#if ($listDaerah.iddaerah == $iddaerah)
         					#set ($selectDaerah = "selected")
	         		<option value="$listDaerah.iddaerah" $selectDaerah>$!listDaerah.kod - $!listDaerah.namadaerah</option>
						#end
	         		#end
	         	<option value="0">SILA PILIH</option>
	         	#foreach ($listDataDaerah in $listDaerahbyNegeri)
	         		<option value="$listDataDaerah.id">$!listDataDaerah.kod - $!listDataDaerah.nama</option>
	         	#end
         	#else
         		<option value="0">SILA PILIH</option>
	         	#foreach ($listDataDaerah in $listDaerahbyNegeri)
	         		<option value="$listDataDaerah.id">$!listDataDaerah.kod - $!listDataDaerah.nama</option>
	         	#end
         	#end
         	</select></td>
         	</tr>
         	<tr>
         	<td colspan="2"><table width="100%">
         	<tr>
         	<td align="right" width="30%"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alasan Pindah :</td>
         	<td width="2%"><input type="checkbox" name="alasanpindah" value="100" onClick="pick1()" $readmode $checkbx1 $disabled  $disabled2></td>
         	<td width="68%">Kesulitan Pemohon/Waris untuk hadir</td>
         	</tr>
         	<tr>
         	<td></td>
         	<td>&nbsp;</td>
         	<td><input type="checkbox" name="alasanpindah" value="101" onClick="pick2()" $readmode $checkbx2 $disabled  $disabled2>Masalah Kesihatan</td>
         	</tr>
         	<tr>
         	<td></td>
         	<td></td>
         	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="alasanpindah" value="102" onClick="pick3()" $readmode $checkbx3 $disabled  $disabled2>Perakuan pakar perubatan disertakan</td>
         	</tr>
         	<tr>
         	<td></td>
         	<td>&nbsp;&nbsp;&nbsp;&nbsp; </td>
         	<td><input type="checkbox" name="alasanpindah" value="103" onClick="pick4()" $readmode $checkbx4 $disabled  $disabled2>Masalah perhubungan dan pengangkutan</td>
         	</tr>
         	<tr>
         	<td></td>
         	<td></td>
         	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="alasanpindah" value="104" onClick="pick5()" $readmode $checkbx5 $disabled  $disabled2> Alamat tempat tinggal terbaru disertakan</td>
         	</tr>
         	<tr>
         	<td></td>
         	<td valign="top"><input type="checkbox" name="alasanpindah" value="105" $readmode $checkbx6 $disabled  $disabled2>
                      </td>
         	          <td> Kesemua waris/pihak berkepentingan tidak berada di daerah Pentadbir 
                        Tanah yang mempunyai bidang kuasa eksklusif</td>
         	</tr>
         	</table></td>
         	</tr>
         	<tr>
         	<td></td>
         	<td><input type="checkbox" name="alasanpindah" value="106" onClick="pick7()" $readmode $checkbx7 $disabled  $disabled2> Alasan-alasan lain</td>
         	</tr>
         	<tr>
         	<td></td>
         	<td><textarea cols="31" rows="3" name="txtAlasanLain" style="text-transform:uppercase;" $readmode onKeyup="pick8()" $disabled  $disabled2>$!alasanlain</textarea></td>
         	</tr>
         	<tr>
         	<td align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Tarikh Surat Pemohonan :</td>
         	<td><input name="txdTarikhSurat" type="text" id="txdTarikhSurat" size="10" maxlength="10" value="$!tarikhmohon" $readmode onBlur="javascript:trans_date(this.value)" $disabled  $disabled2/>
             
			 #if ($readmode != "disabled" && $disabled2 != "readonly class=disabled")
			 <a href="javascript:displayDatePicker('txdTarikhSurat',false,'dmy');" $readmode><img src="../img/calendar.gif" alt="" border="0" $setmode/></a>
			 #end
             &nbsp;&nbsp;<span class="style52"><i>format : dd/mm/yyyy</i></span>
			 </td>
         	</tr>
			<tr>
			<td colspan="2" height="50px" valign="bottom"><i><font color="#ff0000">*</font> &nbsp;Sila pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
    		</tr>
         	<tr>
         	<td colspan="2" align="center" height="50px;">
            #if ($tarikhmohon=="")
                #if ($btnKemaskini == "yes")
                <input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskini()">
                #end
                #if ($btnSimpan == "yes")
                <input type="button" name="cmdSimpan" value="Simpan" onClick="simpan()">
                #end
                #if ($btnBatal == "yes")
                <input type="reset" name="cmdBatal" value="Batal">
                #end
            #end
            #if ($btnKembali == "yes")
         	<input type="button" name="cmdKembali" value="Kembali" onClick="kembali_keputusan()">
         	#end
         	</td>
         	</tr>
          </table>
          </div>
          <div class="TabbedPanelsContent"></div>
          #if ($keputusanpegawai == "L")
          <div class="TabbedPanelsContent"></div>
          #end
          </div>
      </div></td>
    </tr>
  </table>
  
 
#parse("app/ppk/headerppk_script.jsp")

<script>
function PemohonBkeView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Permohonanview";
	document.${formName}.hitButt.value="Permohonan";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	//document.${formName}.action.value="";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

function KeputusanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value = "Keputusanview";
	document.${formName}.hitButt.value = "Keputusan";
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	//document.${formName}.action.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

function KptgView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method = "post";
	document.${formName}.mode.value = "Kptgview";
	document.${formName}.hitButt.value = "Kptg";
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	//document.${formName}.action.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

function getDaerah() {
	document.${formName}.method = "post";
	document.${formName}.mode.value = "Permohonandaerah";
	document.${formName}.hitButt.value = "Permohonan";
	document.${formName}.tabIdatas.value = "0";
	document.${formName}.tabIdtengah.value = "0";
	document.${formName}.tabIdbawah.value = "0";
	document.${formName}.tabIdtepi.value = "0";
	//document.${formName}.action.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

function kembali() {
	document.${formName}.method = "post";
	document.${formName}.mode.value = "";
	document.${formName}.hitButt.value = "";
	//document.${formName}.action.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

function pick1() {
	if (document.${formName}.alasanpindah[0].checked == true){
		document.${formName}.alasanpindah[1].checked = true;
	}
	if (document.${formName}.alasanpindah[0].checked == false){
		document.${formName}.alasanpindah[1].checked = false;
		document.${formName}.alasanpindah[2].checked = false;
		document.${formName}.alasanpindah[3].checked = false;
		document.${formName}.alasanpindah[4].checked = false;
	}
}
function pick2() {
	if (document.${formName}.alasanpindah[1].checked == true){
		document.${formName}.alasanpindah[2].checked = true;
		document.${formName}.alasanpindah[0].checked = true;
	}
	if (document.${formName}.alasanpindah[1].checked == false){
		if (document.${formName}.alasanpindah[3].checked == false){
			document.${formName}.alasanpindah[0].checked = false;
		}
		document.${formName}.alasanpindah[2].checked = false;
	}
}
function pick3() {
	if (document.${formName}.alasanpindah[2].checked == true){
		document.${formName}.alasanpindah[0].checked = true;
		document.${formName}.alasanpindah[1].checked = true;
		
	}
	if (document.${formName}.alasanpindah[2].checked == false){
		if (document.${formName}.alasanpindah[3].checked == false){
			document.${formName}.alasanpindah[0].checked = false;
		}
		document.${formName}.alasanpindah[1].checked = false;
	}
}

function pick4() {
	if (document.${formName}.alasanpindah[3].checked == true){
		document.${formName}.alasanpindah[0].checked = true;
		document.${formName}.alasanpindah[4].checked = true;
		
	}
	if (document.${formName}.alasanpindah[3].checked == false){
		if (document.${formName}.alasanpindah[1].checked == false){
			document.${formName}.alasanpindah[0].checked = false;
		}
		document.${formName}.alasanpindah[4].checked = false;
	}
}

function pick5() {
	if (document.${formName}.alasanpindah[4].checked == true){
		document.${formName}.alasanpindah[0].checked = true;
		document.${formName}.alasanpindah[3].checked = true;
		
	}
	if (document.${formName}.alasanpindah[4].checked == false){
		if (document.${formName}.alasanpindah[1].checked == false){
			document.${formName}.alasanpindah[0].checked = false;
		}
		document.${formName}.alasanpindah[3].checked = false;
	}
}

function pick7() {
	if (document.${formName}.alasanpindah[6].checked == false){
		document.${formName}.txtAlasanLain.value = "";
	}
}

function pick8() {
	if (document.${formName}.txtAlasanLain.value != ""){
		document.${formName}.alasanpindah[6].checked = true;
	}
	if (document.${formName}.txtAlasanLain.value == ""){
		document.${formName}.alasanpindah[6].checked = false;
	}
}

function simpan(){
	var currentTime = new Date()

	 var str1  = document.getElementById("txdTarikhSurat").value;
	 var dt1   = parseInt(str1.substring(0,2),10);
     var mon1  = parseInt(str1.substring(3,5),10)-1;
     var yr1   = parseInt(str1.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);

	if (document.${formName}.socNegeri.value == "0"){
		alert("Sila pilih Negeri");
		socNegeri.focus();
	}
	else if (document.${formName}.socDaerah.value == "0"){
		alert("Sila pilih Daerah");
		socDaerah.focus();
	}
	else if (document.${formName}.socNegeri.value == document.${formName}.namanegeri.value && document.${formName}.socDaerah.value == document.${formName}.namadaerah.value){
		alert("Sila pilih Negeri dan Daerah Pindah yang berlainan dari yang telah dipohon");
		socNegeri.focus();
	}
	else if (document.${formName}.alasanpindah[0].checked == false && document.${formName}.alasanpindah[1].checked == false && document.${formName}.alasanpindah[2].checked == false && document.${formName}.alasanpindah[3].checked == false && document.${formName}.alasanpindah[4].checked == false && document.${formName}.alasanpindah[5].checked == false && document.${formName}.alasanpindah[6].checked == false ){
		alert("Sila pilih salah satu alasan yang berkenaan.");
		alasanpindah[0].focus();
	}
	else if (document.${formName}.txdTarikhSurat.value == ""){
		alert("Sila pilih Tarikh Surat Permohonan");
		txdTarikhSurat.focus();
	}
	else if (document.${formName}.txdTarikhSurat.value != "" && isDate(str1)==false){
		txdTarikhSurat.focus();
	}
	else if (document.${formName}.txdTarikhSurat.value != "" && date1 > currentTime){
		alert("Sila pastikan tarikh mohon tidak melebihi dari tarikh hari ini.");
		txdTarikhSurat.clear();
	}
	else{	
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			document.${formName}.mode.value="Permohonansimpan";
			document.${formName}.hitButt.value="Permohonan";
			//document.${formName}.action.value="";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
			document.${formName}.submit();
		}
	}
}

function kemaskini(){
		document.${formName}.method="post";
		document.${formName}.mode.value="Permohonankemaskini";
		document.${formName}.hitButt.value="Permohonan";
		//document.${formName}.action.value="";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
		document.${formName}.submit();
}

function kembali_keputusan() {
	document.${formName}.method="post";
	document.${formName}.hitButt.value="Kembali";
	//document.${formName}.action.value="";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

function trans_date(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.${formName}.txdTarikhSurat.value = trans;

}
else
{
return;
}

}
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
//-->

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetakSuratLulusPrmhnnBKE(noFail) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idfail+"&report=SuratLulusPrmhnnBKE&flagReport=S";
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratTolakPrmhnnBKE(noFail,idfail) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idfail+"&report=SuratTolakPrmhnnBKE&flagReport=S";
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangQ(noFail,idfail) {
    var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idfail+"&report=BorangQ&flagReport=B";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakIringanBorangQ(noFail,idfail) {
    var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idfail+"&report=IringanBorangQ&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangR(noFail,idfail) {
   var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idfail+"&report=BorangR&flagReport=B";
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
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
</script>
</body>

