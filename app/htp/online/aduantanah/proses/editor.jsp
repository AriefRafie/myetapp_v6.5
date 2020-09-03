
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="text" id="flag_buka_upload" name="flag_buka_upload" value="$!flag_buka_upload"  />
<input name="mode" type="hidden" id="mode" value="$mode"/>
<input value="$!nama_status" type="hidden" name="nama_status" id="nama_status"  />
<input type="hidden" name="userid" id="userid" value='$!{session.getAttribute("_ekptg_user_id")}'/>
<input type="text" name="idRespon" value="$!response.id">
<input type="text" name="ID_ADUANPUBLIC" id="ID_ADUANPUBLIC" value="$!ID_ADUANPUBLIC">
<input type="text" name="id_phphakmilikaduan" id="id_phphakmilikaduan" value="$!id_phphakmilikaduan">


<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT ADUAN</strong></legend>
  <table width="100%" border="0" cellspacing="2" cellpadding="2">

        <script type="text/javascript" >
		document.getElementById('tr_user_id').style.display = "";
		</script>

        #if($!nama_pengadu!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Nama</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="nama_pengadu" name="nama_pengadu"  onBlur="this.value=this.value.toUpperCase();" value="$!nama_pengadu">
            <font color="blue">$!nama_pengadu</font>            </td>
          </tr>
        #end
        #if($!nama_jawatan!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Jawatan</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="nama_jawatan" name="nama_jawatan"  onBlur="this.value=this.value.toUpperCase();" value="$!nama_jawatan">
            <font color="blue">$!nama_jawatan</font>            </td>
          </tr>
        #end

        <tr>
          <td align="right"><font color="red">*</font></td>
          <td align="left">Emel</td>
          <td>:</td>
          <td> #if($!emel!="")
          <input TABINDEX="1" onClick="" value="$!emel" type="hidden" name="emel" id="emel" autocomplete="off" />
             <a href="mailto:$!emel"><font color="blue"><u>$!emel</u></font></a>
             #else
           <input  onClick="" value="$!emel" type="email" name="emel" id="emel" size="40" maxlength="100"  />
           <font color="blue"><i>cth: abc@email.com</i></font>

             #end
			</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td align="left">No. Tel</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="no_tel" name="no_tel"  onBlur="this.value=this.value.toUpperCase();" value="$!no_telx">
           <font color="blue">$!no_telefon</font>            </td>
          </tr>

         #if($!seksyen!="")
         <tr>
            <td>&nbsp;</td>
            <td align="left">Seksyen</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="seksyen" name="seksyen"  onBlur="this.value=this.value.toUpperCase();" value="$!seksyen">
            <font color="blue">$!seksyen</font>            </td>
          </tr>
         #end
          #if($!pejabat!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Pejabat</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="pejabat" name="pejabat"  onBlur="this.value=this.value.toUpperCase();" value="$!pejabat">
            <font color="blue">$!pejabat</font>            </td>
          </tr>
         #end
         #if($!negeri!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Negeri</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="negeri" name="negeri"  onBlur="this.value=this.value.toUpperCase();" value="$!negeri">
            <font color="blue">$!negeri</font>            </td>
          </tr>
          #end
          #if($!daerah!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Daerah</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="daerah" name="daerah"  onBlur="this.value=this.value.toUpperCase();" value="$!daerah">
            <font color="blue">$!daerah</font>            </td>
          </tr>
          #end

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
							<!-- <tr>
							  <td>&nbsp;</td>
							  <td>Pegangan Hakmilik</td>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
							  <td>:</td>
							  <td><input type="text" class="$inputTextClass" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$txtPeganganHakmilik" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/></td>
							</tr> -->
							<tr>
							  <td>&nbsp;</td>
							  <td>Negeri</td>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
							  <td>:</td>
							  <td>
								$selectNegeri
								<input type="text" name="idNegeri" id="idNegeri" value="$idNegeri" />
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Daerah</td>
							  <td colspan="6">&nbsp;</td>
							  <td>:</td>
							  <td>$selectDaerahTanah
								<input type="text" name="idDaerahTanah" id="idDaerahTanah" value="$idDaerah" /></td>
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Mukim</td>
							  <td colspan="6">&nbsp;</td>
							  <td>:</td>
							  <td>$selectMukimTanah
								<input type="hidden" name="idMukimTanah" id="idMukimTanah" value="$idMukim" /></td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Hakmilik</td>
							  <td colspan="6">&nbsp;</td>
							  <td>:</td>
							  <td>$selectJenisHakmilik
								<input type="text" class="$inputTextClass" name="txtNoHakmilikTanah" id="txtNoHakmilikTanah" value="$txtNoHakmilikTanah" size="43" maxlength="80" $readonly/>
							  	<input type="text" name="idJenisHakmilik" id="idJenisHakmilik" value="$idJenisHakmilik" />
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Warta</td>
							  <td colspan="6">&nbsp;</td>
							  <td>:</td>
							  <td>
								<input type="text" class="$inputTextClass" name="noWarta" id="noWarta" value="$noWarta" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Tarikh Warta</td>
							  <td colspan="6">&nbsp;</td>
							  <td>:</td>
							  <td>
								<input type="text" name="tarikhWarta" id="tarikhWarta" onBlur="check_date(this);cekTarikhWarta(this)" value="$tarikhWarta" size="9" $readonly class="$inputTextClass"/>
									<a href="javascript:displayDatePicker('tarikhWarta',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</a>
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Lot</td>
							  <td colspan="6">&nbsp;</td>
							  <td>:</td>
							  <td>$selectJenisLot
								<input type="hidden" name="idJenisLot" id="idJenisLot" value="$idJenisLot" />
								<input type="text" class="$inputTextClass" name="txtNoLot" id="txtNoLot" value="$txtNoLot" size="43" maxlength="80" $readonly/>
							  </td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Luas Lot</td>
							  <td colspan="6">&nbsp;</td>
							  <td>:</td>
							  <td>
								<input type="text" class="$inputTextClass" name="txtLuas" id="txtLuas" value="$txtLuas" size="43" maxlength="80" $readonly/>
								#parse("app/php2/unit_luas.jsp")
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
        <textarea name="aduan" id="aduan" cols="80"   rows="8"  placeholder="Sila Masukkan Keterangan Aduan..."
         onBlur="check_length(this,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');"
         onKeyup="check_length(this,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');"
         onKeydown="check_length(this,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');"
           >$!keteranganAduan</textarea>

         <div><span id="aduan_num" style="color:blue;" ></span><span> Baki Aksara</span></div>

         <div id="aduan_check" class="alert_msg" ></div>


          #if($!id_aduan == "")
           <div><span style="color:blue;" >Fungsi upload akan dipaparkan selepas maklumat log disimpan didalam bentuk 'Deraf'</span></div>
          #end

         </td>
        </tr>
        #if($!id_aduan != "")
         <tr>
          <td width="2%" align="right"  valign="top"></td>
          <td align="left" valign="top">Dokumen Sokongan</td>
          <td valign="top">:</td>
          <td valign="top">
          #if($listDokumen_aduan.size() > 0)
          #foreach($list1 in $listDokumen_aduan)
          $list1.tajuk - <a href="javascript:papar_Lampiran('$list1.id_esdokumen')"><font color="blue"><u>$list1.nama_fail</u></font></a><a href="javascript:deleteDokumen1by1('$list1.id_esdokumen')" title="Hapus" ><font color="blue">&nbsp;<img   src="../img/validno.png"  height="10" width="10" alt="" border="0"/></font></a><br />
          #end
          #end
          <a href="javascript:bukaUpload()"><img src="../img/attachment-icon.png" alt="" border="0"/><font color="blue"><i>Muatnaik Dokumen</i></font></a>
                </td>
        </tr>
		#end
        <tr>
        <td colspan="4">
#if($!open_upload=="yes")
	#parse("app/esaduan/frmUpload.jsp")
#end
		</td>
        </tr>
<tr>
<td colspan="4">
   <input type="button"  name="cmdHantarAduan" id="cmdHantarAduan" value="Simpan" onClick="javascript:simpan()" />
   <input type="button"  name="cmdHantarAduan" id="cmdHantarAduan" value="Hantar Aduan" onClick="javascript:hantar()" />
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

function daftarAduan_hantar() {
	doAjaxCall${formName}("simpanAduan_hantar");
	document.${formName}.cmdHantarAduan.value = "Sila Tunggu....";

	}

function simpan() {

	doAjaxCall${formName}("simpanDraf");

	}

function hantar() {

	doAjaxCall${formName}("simpanComplaint");

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

