<style type="text/css">
<!--
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
.style53 {
	color: #000000
}
-->
</style>
#set($portal_role = "${session.getAttribute('_portal_role')}")
<fieldset>
<strong>
<legend>Daftar Permohonan</legend>
</strong>
<table width="100%">
  <tr>
    <td><fieldset>
      <strong>
        <legend>Maklumat Asas Permohonan</legend>
      </strong>
      <table width="100%">
        <tr>
          <td align="left">&nbsp;</td>
          <td align="left">&nbsp;</td>
          <td>&nbsp;</td>
          <td><div align="right" class="style53">PERMOHONAN KAUNTER</div></td>
        </tr>
        <tr>
          <td align="left">&nbsp;</td>
          <td align="left">Bil Permohonan</td>
          <td>:</td>
          <td>#if($action == 'baru') <input name="txtBilPermohonan" type="text" id="txtBilPermohonan" size="15" class="disabled" readonly/>#else $noPermohonan #end</td>
        </tr>
        <tr>
          <td width="1%" align="left">&nbsp;</td>
          <td width="28%" align="left">No. Fail Permohonan</td>
          <td width="1%">:</td>
          <td width="70%">#if($action == 'sah') $no_fail #else
            <input name="no_permohonan" type="text" class="disabled" id="no_permohonan" size="15" readonly="readonly"/>            
            #end</td>
        </tr>
        <tr>
          <td align="left">&nbsp;</td>
          <td align="left">No. Rujukan PTG</td>
          <td>:</td>
          <td>
            <input name="noRujPTG" type="text" class="$disabled" id="noRujPTG" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$noRujPTG" size="37" $readonly />
            <div id="noRujPTG_check" style="color:red" ></div>          </td>
        </tr>
        <tr>
          <td align="left">&nbsp;</td>
          <td align="left">No. Rujukan PTD</td>
          <td>:</td>
          <td><input name="noRujPTD" type="text" class="$disabled" id="noRujPTD" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$noRujPTD" size="37" $readonly />
          <div id="noRujPTD_check" style="color:red"></div>          </td>
        </tr>
        <tr>
          <td width="1%" align="left">&nbsp;</td>
          <td width="28%" align="left">Urusan</td>
          <td width="1%">:</td>
          <td width="70%">PENGAMBILAN SECARA PENDUDUKAN SEMENTARA</td>
        </tr>
        <!--<tr>
          <td width="1%" align="left">&nbsp;</td>
          <td width="28%" align="left">Jenis Permohonan</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtJenisPermohonan" type="text" class="disabled" value="PERMOHONAN KAUNTER" size="25" readonly="readonly" /></td>
        </tr>-->
        <tr>
          <td width="1%" align="left">&nbsp;</td>
          <td width="28%" align="left">Tarikh Permohonan</td>
          <td width="1%">:</td>
          <td width="70%">$tarikhPohon</td>
        </tr>
        <!--<tr>
          <td width="1%" align="left">&nbsp;</td>
          <td width="28%" align="left">Status Permohonan</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="status" value="$status" size="28px" class="disabled" readonly="readonly" />          </td>
        </tr>-->
      </table>
    </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <strong>
        <legend>Maklumat Agensi / KJP</legend>
      </strong>
      <table width="100%">
        <tr>
          <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
          <td width="28%">Kementerian</td>
          <td width="1%">:</td>
          <td>$SelectKementerian</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Nama Agensi</td>
          <td width="1%">:</td>
          <td>$SelectAgensi</td>
        </tr>
        <tr>
          <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
          <td width="28%">Alamat</td>
          <td width="1%">:</td>
          <td><input type="text" name="alamat1" value="$addAlamat1" id="Alamat1" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" size="60px" $readonly class="$disabled"/></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
          <td><input type="text" name="alamat2" value="$addAlamat2" id="alamat2" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" size="60px"  $readonly class="$disabled"/></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
          <td><input type="text" name="alamat3" value="$addAlamat3" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" id="alamat3" size="60px"  $readonly class="$disabled"/></td>
        </tr>
        <tr>
          <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
          <td width="28%">Poskod</td>
          <td width="1%">&nbsp;</td>
          <td><input type="text" name="poskod" size="4px" onkeyup="validatePoskod(this,this.value);"  value="$addPoskod" maxlength="5" id="poskod"  $readonly class="$disabled"/></td>
        </tr>
        <tr>
          <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
          <td width="28%">Negeri</td>
          <td width="1%">&nbsp;</td>
          <td>$SelectNegeri</td>
        </tr>
      </table>
    </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <strong>
        <legend>Maklumat Lengkap Projek</legend>
      </strong>
      <table width="100%">
        <tr>
          <td width="1%" valign="top"><font color="red">#if($readonly != 'readonly')*#end</font></td>
          <td width="28%" valign="top">Amaun Peruntukan</td>
          <td width="1%" valign="top">:</td>
          <td valign="top"><input name="new_flag_peruntukan" type="radio" value="1" $TEMPcheckedAda #if ($mode=='papar') disabled="disabled" #end />
            Ada
            <input name="new_flag_peruntukan" type="radio" value="2" $TEMPcheckedTiada #if ($mode=='papar') disabled="disabled" #end/>
            Tiada</td>
        </tr>
        <!--<tr>
          <td width="1%" valign="top"><font color="red">*</font></td>
          <td width="28%" valign="top">Pengambilan Segera?</td>
          <td width="1%" valign="top">&nbsp;</td>
          <td valign="top"><input name="new_flag_segera" type="radio" value="1" $TEMPcheckedYa #if ($mode=='papar') disabled="disabled" #end/>
            Ya
            &nbsp;&nbsp;
                          <input name="new_flag_segera" type="radio" value="2" $TEMPcheckedTidak #if ($mode=='papar') disabled="disabled" #end/>
            Tidak</td>
        </tr>-->
        <tr>
          <td width="1%" valign="top"><font color="red">#if($readonly != 'readonly')*#end</font></td>
          <td width="28%" valign="top">Projek Negeri</td>
          <td width="1%" valign="top">:</td>
          <td valign="top">$project_negeri</td>
        </tr>
        <!--<tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">Jajahan</td>
        <td valign="top">:</td>
        <td valign="top">&nbsp;</td>
      </tr>-->
        <tr>
          <td width="1%" valign="top"><font color="red">#if($readonly != 'readonly')*#end</font></td>
          <td width="28%" valign="top">Daerah</td>
          <td width="1%" valign="top">:</td>
          <td valign="top">$SelectDaerah</td>
        </tr>
       <!-- <tr>
          <td width="1%" valign="top"><font color="red">*</font></td>
          <td width="28%" valign="top">Bandar/Mukim/Pekan</td>
          <td width="1%" valign="top">:</td>
          <td valign="top">$SelectBandar</td>
        </tr>-->
        <!--<tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">Seksyen</td>
        <td valign="top">:</td>
        <td valign="top">&nbsp;</td>
      </tr>-->
        <tr>
          <td width="1%" valign="top"><font color="red">#if($readonly != 'readonly')*#end</font></td>
          <td width="28%" valign="top">Tujuan</td>
          <td width="1%" valign="top">:</td>
          <td valign="top"><textarea name="tujuan"  id="tujuan"  cols="40%" maxlength="400" rows="4" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();checking_validation(this,'tujuan_check','yes','tujuan','normal');" onkeyup="this.value=this.value.toUpperCase();checking_validation(this,'tujuan_check','yes','tujuan','normal');" $readonly class="$disabled">$addTujuan</textarea>
            <div id="tujuan_check" style="color:red" ></div>
          </td>
        </tr>
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">No. Ruj. Surat KJP</td>
          <td width="1%" valign="top">:</td>
          <td valign="top"><input type="text" name="rujukan_kementerian" id="rujukan_kementerian" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" maxlength="50" value="$addRujukan_kementerian" size="23px"  $readonly class="$disabled"/></td>
        </tr>
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">Tarikh Surat</td>
          <td width="1%" valign="top">:</td>
          <td valign="top"><input name="tarikh_surat" id="tarikh_surat" value="$addTarikhSurat" size="11px"  type="text"  $readonly class="$disabled" onblur="checking_validation(this,'tarikh_surat_check','no','surat','tarikh');"/>
            #if ($readonly != 'readonly') <img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_surat',false,'dmy');" /> #end
             <span class="style52">dd/mm/yyyy</span><div id="tarikh_surat_check" style="color:red" ></div>
            
            </td>
        </tr>
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">Tarikh Dikehendaki</td>
          <td width="1%" valign="top">:</td>
          <td valign="top"><input name="tarikh_kehendaki" value="$addTarikhHendak" size="11px" id="tarikh_kehendaki" type="text"  $readonly class="$disabled" onblur="checking_validation(this,'tarikh_kehendaki','no','tarikh kehendaki','tarikh');"/>
            #if ($readonly != 'readonly') <img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_kehendaki',false,'dmy');" /> #end
            <span class="style52">dd/mm/yyyy</span>   
           </td>
        </tr>
      </table>
    </fieldset></td>
  </tr>
</table>
#if ($mode != 'baru')
<table width="100%">
  <tr>
    <td><fieldset>
      <div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="0" >Maklumat Tanah Terlibat</li>
          <li class="TabbedPanelsTab" tabindex="0">Senarai Dokumen</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
            <fieldset>
              <legend><strong>Maklumat Tanah Terlibat</strong></legend>
              <table width="100%">
                <tr> #if($currentStatus=="11")
                  <td colspan="5"><input type="submit" name="cmdTambahTanah" id="cmdTambahTanah" value="Tambah" onclick="javascript:tambah();" />
                  </td>
                  #else
                  <td>&nbsp;</td>
                  #end </tr>
                <tr class="table_header">
                  <td><strong>No</strong></td>
                  <td><strong>No. Hakmilik</strong></td>
                  <td><strong>No. Lot / No. PT</strong></td>
                  <td><strong>Mukim / Pekan / Bandar</strong></td>
                  <td><strong>Luas Lot</strong></td>
                </tr>
                #if($saiz_listTanah!=0)
                #foreach($listTanah in $listMaklumatTanah)
                #if ($listTanah.bil == '') 
                #set ($row = 'row1')
                #elseif ($listTanah.bil % 2 != 0)
                #set ($row = 'row1')
                #else 
                #set ($row = 'row2')
                #end 
                #set($idMaklumat=$listTanah.id_hakmilik)
                #set($bilHakmilik=$listTanah.bil)
                <tr>
                  <td class="$row">$listTanah.bil</td>
                  #if ($listTanah.bil != '')
                  <td class="$row"><a href="javascript:edit_maklumat('$idMaklumat')"><font color="blue">$listTanah.no_hakmilik</font></a></td>
                  #else
                  <td class="$row">$listTanah.no_hakmilik</td>
                  #end
                  <td class="$row">$listTanah.kod_lot $listTanah.no_lot</td>
                  <td class="$row">$listTanah.nama_mukim</td>
                  <td class="$row">$listTanah.luas_lot&nbsp;$listTanah.unitluaslot</td>
                </tr>
                #end    
                #end
              </table>
            </fieldset>
          </div>
          <div class="TabbedPanelsContent">
            <fieldset>
              <legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
              <table width="100%">
                <tr> #if($currentStatus=="11")
                  <td colspan="4"><input type="button" name="cmdTambah2" value ="Tambah" onclick="javascript:tambahDokumen('$id_permohonan');" /></td>
                  #else
                  <td>&nbsp;</td>
                  #end </tr>
                <tr class="table_header">
                  <td><b>No</b></td>
                  <td width="30%"><b>Nama Dokumen</b></td>
                  <td width="40%"><b>Keterangan</b></td>
                  <td width="30%"><b>Dokumen Sokongan</b></td>
                </tr>
                #if($listD_size!=0)
                
                #foreach($listD in $listDokumen)  
                #set($idDokumen=$listD.id_Dokumen)    
                
                #if ($listD.bil == '') 
                #set ($row = 'row1')
                #elseif ($listD.bil % 2 != 0)
                #set ($row = 'row1')
                #else 
                #set ($row = 'row2')
                #end
                <tr>
                  <td class="$row" align="center">$listD.bil</td>
                  <td class="$row">$listD.tajuk</td>
                  <td class="$row">$listD.keterangan</td>
                  #if($listD.bil != '')
                  <td class="$row"><a href="javascript:papar_Lampiran('$idDokumen')"><font color="blue">$listD.nama_Fail</font></a></td>
                  #else
                  <td class="$row">$listD.nama_Fail</td>
                  #end </tr>
                <tr> #end 
                  #end </tr>
              </table>
            </fieldset>
          </div>
        </div>
      </div>
    </fieldset></td>
  </tr>
</table>
#end
<table width="100%">
  <tr align="center">
    <td> #if($mode == 'baru')
      <input name="cmdSimpan" type="button" value="Simpan" onkeypress="add_item()" onclick="add_item()" />
        <input name="cmdKembali" type="button" value="Kembali" onclick="batal()" />
      #elseif($mode == 'papar')
                <input name="cmdKemaskini" type="button" value="Kemaskini" onclick="kemaskini()" />
      	#if($currentStatus == '11')
      		#if(($bilHakmilik!='') && ($portal_role == "(PPT)PengarahUnit" || $portal_role == "(PPT)PenolongPengarahUnit"))
      			<input name="cmdSahkan" type="button" value="Sahkan" onclick="sah('$id_permohonan')" />
            #end
        #end
      <!--#if($currentStatus == '127')
      <input name="cmdLuluskan" type="button" value="Luluskan" onclick="lulus('$id_permohonan')" />
      #end-->
      <input name="cmdKembali" type="button" value="Kembali" onclick="kembali()" />
      #elseif($mode == 'kemaskini')
      <input name="cmdSimpan" type="button" value="Simpan" onclick="update_item('$id_permohonan')" />
      <input name="cmdKembali" type="button" value="Kembali" onclick="batal_update('$id_permohonan')" />
      #end </td>
  </tr>
</table>
<input type="hidden" name="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_hakmilik" value="$idMaklumat" />
<input type="hidden" name="id_semak" value="$idSemak" />
<input type="hidden" name="id_status" value="$idstatus" />
<input type="hidden" name="suburusan" value="$idSuburusan" />
<input type="hidden" name="id_senaraiSemak" value="$idSenaraiSemak" />
<input type="hidden" name="id_fail" value="$idFail" />
<input type="hidden" name="idKementerianA" value="$idKementerianA" />
<input type="hidden" name="idNegeriA" value="$idNegeriA" />
<input name="projeknegeri" type="hidden" value="$projeknegeri" />
<input name="id_daerah" type="hidden" value="$id_daerah" />
<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="flag_peruntukan" value="$flag_peruntukan" />
<input type="hidden" name="flag_segera" value="$flag_segera" />
<input type="hidden" name="alert_message" id="alert_message" />
<input name="saiz_listTanah" type="hidden" value="$bilHakmilik" />
</fieldset>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");

//-->




</script>
<script>
window.onload = submitForm;

function submitForm() 
{


/*var readmode = '$mode';

if(readmode == "baru")
{
checking_validation(tujuan,'tujuan_check','no','tujuan','normal');
checking_validation(tarikh_surat,'tarikh_surat_check','no','tarikh surat','tarikh');
checking_validation(noRujPTG,'noRujPTG_check','yes','no rujukan ptg','normal');
checking_validation(noRujPTD,'noRujPTD_check','yes','no rujukan ptd','normal');
}*/

}


function doChangeidNegeri() {
    doAjaxCall${formName}("doChangeidNegeri");
}
function doChangeidProjekNegeriUpdate() {
    doAjaxCall${formName}("doChangeidProjekNegeriUpdate");
}
function doChangeidKementerian() {
    doAjaxCall${formName}("doChangeidKementerian");
}
function doChangeidKementerianUpdate() {
    doAjaxCall${formName}("doChangeidKementerianUpdate");
}
function doChangeidMukim() {
    doAjaxCall${formName}("doChangeidMukim");
}
function doChangeidMukimUpdate() {
    doAjaxCall${formName}("doChangeidMukimUpdate");
}
function doChangeAlamatAgensi() {
    doAjaxCall${formName}("doChangeAlamatAgensi");
}
function tambahDokumen(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=tambahDokumen"; 
	document.${formName}.submit();
}
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function sah(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=sah"; 
	document.${formName}.submit();	
}
function lulus(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=lulus"; 
	document.${formName}.submit();	
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=batal";
	document.${formName}.submit();
}
function edit_maklumat(id_hakmilik) {
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=edit_maklumat";
	document.${formName}.submit();
}
function add_item()
{
	
	var radioSelected = false;
	for (i = 0;  i < ${formName}.new_flag_peruntukan.length;  i++){
	if (${formName}.new_flag_peruntukan[i].checked)
	radioSelected = true;
	}
	
		

	if(document.${formName}.kementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.kementerian.focus(); 
		return;
	}
	else if (!radioSelected){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
		return;
	}
	else if(document.${formName}.projek_negeri.value == ""){
		alert("Sila pilih \"Projek negeri\" terlebih dahulu.");
  		document.${formName}.projek_negeri.focus(); 
		return;
	}
	else if(document.${formName}.daerah.value == ""){
		alert("Sila pilih \"Daerah\" terlebih dahulu.");
  		document.${formName}.daerah.focus(); 
		return;
	}
	else if(document.${formName}.tujuan.value == ""){
		alert("Sila masukkan \"tujuan\" terlebih dahulu.");
  		document.${formName}.tujuan.focus(); 
		return;
	}
	else if(document.${formName}.alamat1.value == ""){
		alert("Sila masukkan \"alamat\" terlebih dahulu.");
  		document.${formName}.alamat1.focus(); 
		return;
	}
	else if(document.${formName}.negeri.value == ""){
		alert("Sila pilih \" Negeri \" terlebih dahulu.");
  		document.${formName}.negeri.focus(); 
		return;
	}
	else if(document.${formName}.poskod.value == ""){
		alert("Sila masukkan \"poskod\" terlebih dahulu.");
  		document.${formName}.poskod.focus(); 
		return;
	}
	else if (document.${formName}.poskod.value != "" && document.${formName}.poskod.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat perayu dengan selengkapnya");
		document.${formName}.poskod.focus();
	}
	
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=simpan";
		document.${formName}.submit();
	}	
}
function update_item(id_permohonan) {

	
	temp1 = parseInt(document.${formName}.poskod.value);

	if (temp1 <10000 || temp1>99999) {
	alert ("Sila masukkan 5 digit poskod");
	document.${formName}.poskod.focus();
	return;
	} 

	if(document.${formName}.kementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.kementerian.focus(); 
		return;
	}
	if(document.${formName}.flag_peruntukan.value == ""){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
  		document.${formName}.flag_peruntukan.focus(); 
		return;
	}
	if(document.${formName}.projek_negeri.value == ""){
		alert("Sila pilih \"Projek negeri\" terlebih dahulu.");
  		document.${formName}.projek_negeri.focus(); 
		return;
	} 
	if(document.${formName}.daerah.value == ""){
		alert("Sila pilih \"Daerah\" terlebih dahulu.");
  		document.${formName}.daerah.focus(); 
		return;
	}
	if(document.${formName}.tujuan.value == ""){
		alert("Sila masukkan \"tujuan\" terlebih dahulu.");
  		document.${formName}.tujuan.focus(); 
		return;
	}
	if(document.${formName}.alamat1.value == ""){
		alert("Sila masukkan \"alamat\" terlebih dahulu.");
  		document.${formName}.alamat1.focus(); 
		return;
	}
	if(document.${formName}.negeri.value == ""){
		alert("Sila pilih \" Negeri \" terlebih dahulu.");
  		document.${formName}.negeri.focus(); 
		return;
	}
	if(document.${formName}.poskod.value == ""){
		alert("Sila masukkan \"poskod\" terlebih dahulu.");
  		document.${formName}.poskod.focus(); 
		return;
	} 
	else
	{  
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=update"; 
		document.${formName}.submit();	
 	}
}
function batal() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=batal";
	document.${formName}.reset();
	document.${formName}.submit();
}
function kemaskini() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=kemaskini"; 
	document.${formName}.submit();
}
function batal_update(id_permohonan)
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=semak";
	document.${formName}.submit();
}
function tambah() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=tambah"; 
	document.${formName}.submit();
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
	   
		  
	      DateField.select();
		 // DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
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
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	//   DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	//   DateField.select();
	//   DateField.focus();
	   
	   }
	   
	   }
	   
	   
	   if(jenis_field == "normal")
	   {
	   
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	
	   }
	   else
	   {
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   	   
	   }
	   
	   
	   
	
	}



</script>
