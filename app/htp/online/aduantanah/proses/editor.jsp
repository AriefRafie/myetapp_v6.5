<script>
function toggle_div(id) {
    var e = document.getElementById(id);
    e.style.display = ((e.style.display!='none') ? 'none' : 'block');
}
</script>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" id="flag_buka_upload" name="flag_buka_upload" value="$!flag_buka_upload"  />
<input name="mode" type="hidden" id="mode" value="$mode"/>
<input value="$!nama_status" type="hidden" name="nama_status" id="nama_status"  />
<input type="hidden" name="userid" id="userid" value='$!{session.getAttribute("_ekptg_user_id")}'/>
<input type="hidden" name="idRespon" value="$!response.id">
<input type="hidden" name="ID_ADUANPUBLIC" id="ID_ADUANPUBLIC" value="$!ID_ADUANPUBLIC">
<input type="hidden" name="id_phphakmilikaduan" id="id_phphakmilikaduan" value="$!id_phphakmilikaduan">
<input type="hidden" name="idJenisAduan" id="idJenisAduan" value="16101">


<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT ADUAN</strong></legend>
  <table width="100%" border="0" cellspacing="2" cellpadding="2">

        <script type="text/javascript" >
		document.getElementById('tr_user_id').style.display = "";
		</script>
          <tr>
            <td width="1%">&nbsp;</td>
            <td align="left" width="15%">Nama</td>
            <td width="1%">:</td>
            <td width="73%"><input TABINDEX="1" type="hidden" id="name" name="name"  onBlur="this.value=this.value.toUpperCase();" value="$!user.name.toUpperCase()">
            <font color="blue">$!user.name.toUpperCase()</font>            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Jawatan</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="nama_jawatan" name="nama_jawatan"  onBlur="this.value=this.value.toUpperCase();" value="$!user.jawatan">
            <font color="blue">$!user.jawatan</font>            </td>
          </tr>
        <tr>
          <td align="right"><font color="red">*</font></td>
          <td align="left">Emel</td>
          <td>:</td>
          <td>
          <input TABINDEX="1" onClick="" value="$!emel" type="hidden" name="email" id="email" autocomplete="off" />
             <a href="mailto:$!user.email"><font color="blue"><u>$!user.email</u></font></a>

			</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td align="left">No. Tel</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="phone" name="phone"  onBlur="this.value=this.value.toUpperCase();" value="$!user.phone">
           <font color="blue">$!user.phone</font>            </td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td align="left">Seksyen</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="seksyen" name="seksyen"  onBlur="this.value=this.value.toUpperCase();" value="$!user.seksyen">
            <font color="blue">$!user.seksyen</font>            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Pejabat</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="pejabat" name="pejabat"  onBlur="this.value=this.value.toUpperCase();" value="$!user.pejabat">
            <font color="blue">$!user.pejabat</font>            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Negeri</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="negeri" name="negeri"  onBlur="this.value=this.value.toUpperCase();" value="$!user.negeri">
            <font color="blue">$!user.negeri</font>            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Daerah</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="daerah" name="daerah"  onBlur="this.value=this.value.toUpperCase();" value="$!user.daerah">
            <font color="blue">$!user.daerah</font>            </td>
          </tr>
        <tr>
          <td valign="top" align="right"> <span id="notifyPPK2" style="display:none"><font color="red">*</font></span></td>
          <td align="left">No. Fail</td>
          <td>:</td>
          <td><input value="$!no_fail" type="text" style="text-transform:uppercase;" name="no_fail" id="no_fail" size="40" maxlength="40" onBlur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" /> </td>
        </tr>
		<tr>
		    <td colspan="4">
				<fieldset>
				  <legend><strong>MAKLUMAT TANAH</strong></legend>
					  <table width="100%" border="0" cellspacing="2" cellpadding="2">
							<tr>
							  <td width="1%">&nbsp;</td>
							  <td width="15%">Negeri</td>
							  <td width="1%">:</td>
							  <td width="73%">$selectNegeri
								<input type="text" name="idNegeriTanah" id="idNegeriTanah" value="$idNegeriTanah" />
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Daerah</td>
							  <td>:</td>
							  <td>$selectDaerah
								<input type="text" name="idDaerahTanah" id="idDaerahTanah" value="$idDaerahTanah" />
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Mukim</td>
							  <td>:</td>
							  <td>$selectMukim
								<input type="text" name="idMukimTanah" id="idMukimTanah" value="$idMukimTanah" /></td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Seksyen</td>
							  <td>:</td>
							  <td>$selectSeksyen
								<input type="hidden" name="idSeksyenTanah" id="idSeksyenTanah" value="$idSeksyenTanah" /></td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Jenis Hakmilik</td>
							  <td>:</td>
							  <td>$selectJenisHakmilik
							  	<input type="text" name="idJenisHakmilikTanah" id="idJenisHakmilikTanah" value="$idJenisHakmilikTanah" />
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Hakmilik</td>
							  <td>:</td>
							  <td>
							  	<input type="text" class="$inputTextClass" name="nohakmilikTanah" id="nohakmilikTanah" value="$nohakmilikTanah" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Jenis PT/ LOT</td>
							  <td>:</td>
							  <td>$selectJenisLot
								<input type="hidden" name="idJenisLotTanah" id="idJenisLotTanah" value="$idJenisLotTanah" />
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. PT/ LOT</td>
							  <td>:</td>
							  <td>
								<input type="text" class="$inputTextClass" name="nolotTanah" id="nolotTanah" value="$nolotTanah" size="43" maxlength="80" $readonly/>
							   </td>
							</tr>
					</table>
				</fieldset>
			</td>
		</tr>
         <tr>
          <td width="2%" align="right"  valign="top"><font color="red">*</font></td>
          <td align="left" valign="top">Keterangan Aduan</td>
          <td valign="top">:</td>
          <td>
	        <textarea name="ulasanBalas" id="ulasanBalas" cols="80"   rows="8"  placeholder="Sila Masukkan Keterangan Aduan..." onblur="this.value=this.value.toUpperCase();">
	        	$!keteranganAduan
	        </textarea>
         </td>
        </tr>
       <tr>
			<td rowspan="3" width="1%">&nbsp;</td>
			<td valign="middle" rowspan="3" align="right" valign="top">Lampiran</td>
			<td valign="middle" rowspan="3" valign="top">:</td>
			<td>
				<table border="0">
					<tr>
						<td><img src="../img/attachment-icon.png" alt="" border="0"/></td>
						<td><a href="#" onclick="toggle_div('toggleDiv');"><font color="blue"><i>Muatnaik Dokumen</i></font></a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="toggleDiv" style="display:none;">
					<input name="lampiran1" type="file" id="txtLampiran" size="50" />
				</div>
			</td>
		</tr>
<tr>
<td colspan="4">
   <input type="button"  name="cmdHantarAduan" id="cmdHantarAduan" value="Hantar Aduan" onClick="javascript:simpan()" />
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembali()"/>
   </td>
  </tr>
     </table>
     </fieldset>
    </td>
  </tr>

</table>

<!--penambahbaikan jangkamasa - wani -->
<script>


function doChangeNegeri() {
	//doAjaxCall${formName}("doChangeNegeri");
	doAjaxCall${formName}("daftarBaru");
}

function doChangeDaerahTanah() {
  	//doAjaxCall${formName}("doChangeNegeri");
  	doAjaxCall${formName}("daftarBaru");
  }

function doChangeJenisHakmilik() {
  	//doAjaxCall${formName}("doChangeNegeri");
  	doAjaxCall${formName}("daftarBaru");
  }

function daftarAduan_hantar() {
	doAjaxCall${formName}("simpanAduan_hantar");
	document.${formName}.cmdHantarAduan.value = "Sila Tunggu....";

	}

function simpan() {

	//doAjaxCall${formName}("simpanDraf");
	document.${formName}.enctype= "multipart/form-data";
	document.${formName}.encoding = "multipart/form-data";
	document.${formName}.action='?_portal_module=ekptg.view.htp.online.aduan.AduanTanah&command=daftarBaru';
	document.${formName}.submit();


	}

function hantar() {

	//doAjaxCall${formName}("simpanComplaint");
	document.${formName}.enctype= "multipart/form-data";
	document.${formName}.encoding = "multipart/form-data";
	document.${formName}.action='?_portal_module=ekptg.view.htp.online.aduan.AduanTanah&command=simpanComplaint';
	document.${formName}.submit();

	}

var get_id_jenisaduan = document.${formName}.id_jenisaduan.value;
var get_txtThpKesukaran = document.${formName}.txtThpKesukaran.value;
if(get_id_jenisaduan=="" && get_txtThpKesukaran == "1")
{
	document.${formName}.txtDari.value = 1;
	document.${formName}.txtHingga.value = 3;
}

else if(document.${formName}.txtThpKesukaran.value == "1"){
		document.${formName}.txtDari.value = "1";
		document.${formName}.txtHingga.value = "3";
	}
else if(document.${formName}.txtThpKesukaran.value == "2"){
		document.${formName}.txtDari.value = "4";
		document.${formName}.txtHingga.value = "7";
	}
else{
		document.${formName}.txtDari.value = "8";
		document.${formName}.txtHingga.value = "12";
	}
</script>



#if($!flag_simpan_doc != "yes")
<script type="text/javascript" >

check_length(document.${formName}.aduan,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');

if(document.${formName}.open_maklumat_teknikal_temp.value == "yes")
{
check_length(document.${formName}.ulasan_teknikal,'4000','ulasan_teknikal_check','ulasan_teknikal_num','normal','no','ulasan teknikal');
}

if(document.${formName}.nama_status.value == 'SELESAI')
	document.getElementById('tarikhSelesai').style.display="";
else
	document.getElementById('tarikhSelesai').style.display="none";
</script>
#else
<script>
check_length1(document.${formName}.aduan,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');
check_length1(document.${formName}.ulasan_teknikal,'4000','ulasan_teknikal_check','ulasan_teknikal_num','normal','no','ulasan teknikal');
function check_length1(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   {
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }
	   if(lepas_or_xlepas == "2")
	   {
	   //$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {
	   if (my_form.value.length >= maxLen)
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }
	   }


	   }

$jquery("#"+text_num).html(maxLen+"");



}

</script>
#end

