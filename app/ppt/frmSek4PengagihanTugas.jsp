<form name="f1" method="POST">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_hakmilik" value="$id_hakmilik">
<input type="hidden" name="id_pihakberkepentingan" value="$id_pihakberkepentingan">
<input type="hidden" name="command">
<fieldset>
<!---------------------------------------------------- default mode -------------------------------------------------->
#if($default=="yes")
        <fieldset>
          <legend>Maklumat Fail [default mode]</legend>
          #foreach ( $senarai in $PermohonanList )
        <table width="100%" border="0">
          <tr>
            <td width="26%">No. Fail</td>
            <td width="74%">:&nbsp;<input type="text" size="40" onkeyup="validatetxtNoHM(this,this.value);" name="txtNoFail" id="txtNoFail" style="text-transform:uppercase;" value="$senarai.no_fail" readonly="true" /></td>
          </tr>
          <tr>
            <td>No. Permohonan</td>
            <td>:&nbsp;<input type="text" size="40" name="txtNoPermohonan" id="txtNoPermohonan" style="text-transform:uppercase;" value="$senarai.no_permohonan" readonly="true" /></td>
          </tr>
          <tr>
            <td>No. Rujukan Seksyen</td>
            <td>:&nbsp;<input type="text" size="40" name="txtRujSek" id="txtRujSek" style="text-transform:uppercase;" value="NULL" readonly="true" /></td>
          </tr>
        </table>
          #end
  </fieldset> 
#end  

<!---------------------------------------------------- add mode -------------------------------------------------->
#if($add=="yes")
		<p>
<fieldset>
          <legend>Maklumat Pengagihan / Penyerahan Tugas [add mode]</legend>
          <table width="100%" border="0">
          <tr>
            <td width="26%">Tarikh Serah Tugas</td>
            <td width="74%">:&nbsp;<input name="txdTarikhSerahTugas" type="text" value="" />
            <a href="javascript:displayDatePicker('txdTarikhSerahTugas',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
          </tr>            
          <tr>
            <td>ID Kerani (Buka Fail)</td>
            <td>:&nbsp;<input type="text" size="40" name="txtIDKerani" id="txtIDKerani" value=""  style="text-transform:uppercase"/></td>
          </tr>
          <tr>
            <td>ID Pegawai Petempatan</td>
            <td>:&nbsp;<input type="text" size="40" name="txtIDPegawai" id="txtIDPegawai" value=""  style="text-transform:uppercase" /></td>
          </tr>
    </table>
  
  </fieldset> 
      <p>
      <fieldset>
      <legend>Maklumat Terperinci Fail</legend>
          <table width="100%" border="0">           
          <tr>
            <td width="26%">Daerah</td>
            <td width="1%">:</td>
            <td width="73%"><input type="text" size="40" name="txtDaerah" id="txtDaerah" value="NULL"  style="text-transform:uppercase" readonly="true"/></td>
          </tr>
          <tr>
            <td valign="top">Tujuan Pengambilan</td>
            <td valign="top">:</td>
            <td><textarea name="txtMaksudPengambilan" cols="37%" rows="3" id="txtMaksudPengambilan" readonly="true">NULL</textarea></td>
          </tr>
          <tr>
            <td valign="top">Kementerian</td>
            <td valign="top">:</td>
            <td width="73%"><textarea name="txtKementerian" cols="37%" rows="3" id="txtKementerian" readonly="true">NULL</textarea></td>
          </tr>
          <tr>
            <td valign="top">Agensi</td>
            <td valign="top">:</td>
            <td width="73%"><textarea name="txtAgensi2" cols="37%" rows="3" id="txtAgensi2" readonly="true">NULL</textarea></td>
          </tr>
          <tr>
            <td width="26%">Tarikh Terima (HQ)</td>
            <td width="1%">:</td>
            <td width="73%"><input type="text" size="10" name="txtTarikhTerima" id="txtTarikhTerima" value="NULL" readonly="true"/></td>
          </tr>  
          <tr>
            <td width="26%">Tarikh Dikehendaki</td>
            <td width="1%">:</td>
            <td width="73%"><input type="text" size="10" name="txtTarikhDikehendaki" id="txtTarikhDikehendaki" value="NULL" readonly="true"/></td>
          </tr> 
          <tr>
            <td valign="top">Di Bawah Seksyen</td>
            <td valign="top">:</td>
            <td width="73%"><textarea name="txtUrusan" cols="37%" rows="3" id="txtUrusan" readonly="true">NULL</textarea></td>
          </tr>
          <tr>
            <td valign="top">Pengambilan Segera?</td>
            <td valign="top">:</td>
            <td width="73%"><input type="text" name="txtPengambilanSegera" id="txtPengambilanSegera" value="YA" size="6" readonly="true" /></td>
          </tr>                                     
    </table>
	  	</table>
  </fieldset>
#end   

<!---------------------------------------------------- edit mode -------------------------------------------------->
#if($edit=="yes")
        <fieldset>
          <legend>Maklumat Tanah</legend>
          #foreach ( $senarai in $PermohonanListHM )
          <table width="100%" border="0">
          <tr>
            <td width="14%">Jenis Hakmilik</td>
            <td width="22%">:&nbsp;$selectEditJenisHM</td>
            <td width="32%">Jajahan / Daerah</td>
            <td width="32%">:&nbsp;$selectEditDaerah</td>
            </tr>
          <tr>
            <td width="14%">No. Hakmilik</td>
            <td>:&nbsp;<input type="text" size="20" name="txtNoHM" id="txtNoHM" value="$senarai.no_hakmilik" /></td>
            <td>Bandar / Pekan / Mukim</td>
            <td>:&nbsp;$selectEditMukim</td>
            </tr>
          <tr>
            <td>No. Lot</td>
            <td>:&nbsp;<input type="text" size="20" name="txtNoLot" id="txtNoLot" value="$senarai.no_lot" /></td>
            <td>Luas Lot</td>
            <td>:&nbsp;<input type="text" size="8" name="txtKeluasan" id="txtKeluasan" value="$senarai.luas_lot" />
              $selectLuasLot1</td>
            </tr>
          <tr>
            <td>No. PT</td>
            <td>:&nbsp;<input type="text" size="20" name="txtNoPT" id="txtNoPT" value="$senarai.no_pt" /></td>
            <td>Anggaran Kawasan Yang Hendak Diambil</td>
            <td>:&nbsp;<input type="text" size="8" name="txtLuasAmbil" id="txtLuasAmbil" value="$senarai.luas_ambil" />
              $selectLuasLot</td>
            </tr>
          <tr>
            <td>Negeri</td>
            <td>:&nbsp;$selectEditNegeri</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>  
          </table>
      	#end
      </fieldset> 
#end   
	<p>
    <div align="center">
    #if($add == "yes")
      <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_MT();">          
      <input type="button" name="Batal" id="Batal" value="Batal" onclick="javascript: history.go(-1)">
    #end
    #if($view == "yes")
      <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:KemaskiniHM('$id_hakmilik');" />        
      <!--<input type="button" name="Hapus" id="Hapus" value="Hapus">-->
      <input type="button" name="Batal" id="Batal" value="Batal">
    #end  
    #if($edit == "yes")
      <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Kemaskini_MT();">          
      <input type="button" name="Batal" id="Batal" value="Batal" onclick="javascript: history.go(-1)">
    #end   
    
  </div>
</fieldset>
</form>

<script>
function Simpan_MT(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.f1.method = "POST";
	document.f1.command.value = "Simpan_MT";
	document.f1.action = "";
	document.f1.submit();
}
function Tambah(){
	document.f1.method = "POST";
	document.f1.command.value = "TambahHM";
	document.f1.action = "";
	document.f1.submit();
}
function KemaskiniHM(id_hakmilik){
	document.f1.method = "POST";
	document.f1.id_hakmilik.value = id_hakmilik;
	document.f1.command.value = "KemaskiniHM";
	document.f1.action = "";
	document.f1.submit();
}
function Kemaskini_MT(id_hakmilik){

	document.f1.id_hakmilik.value = id_hakmilik;
	document.f1.command.value = "Kemaskini_MT";
	document.f1.action = "";
	document.f1.submit();
}
function semakPT(id_pihakberkepentingan) {
	document.f1.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.f1.command.value = "semakPT";
	document.f1.action = "";
	document.f1.submit();
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
function validatetxtNoHM(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
</script> 