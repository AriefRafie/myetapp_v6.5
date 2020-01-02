<style type="text/css">
<!--
.style1 {color: #0033FF}
.style3 {font-size: 10px}
.style4 {font-size: 10px; color: #FF0000; }
.style5 {
	color: #000000;
	font-style: italic;
}
.style40 {
	color: #000000
}
.style6 {color: #FF0000}
-->
</style>
<!--form id="f1" name="f1" method="post" action=""-->

#foreach ($mesyuarat in $Mesyuarat)



<input name="mode" type="hidden" value="$mode" />
<input name="idMesyuarat" type="hidden" value="$mesyuarat.id_Mesyuarat" />
<input name="idAhliMesyuarat" type="hidden" value="" />
&nbsp;
<fieldset>

<legend>MAKLUMAT MESYUARAT</legend>
<div align="left">
  <table width="100%">
    
    <tr>
      <td width="29%" scope="row"><div align="right" class="style4">
        <div align="left">Bil Mesyuarat</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td width="70%">
        <label>
          <input type="text" name="txtBilMsyrt" id="txtBilMsyrt" value="$mesyuarat.bil_Mesyuarat" $readOnly/>
          </label>       </td>
    </tr>
    <tr>
      <td scope="row" valign="top"><div align="right" class="style4">
        <div align="left">Tajuk Mesyuarat</div>
      </div></td>
      <td width="1%" scope="row" valign="top"><div align="right">:</div></td>
      <td>
        <label>
          <textarea name="txtTajukMsyrt" cols="41" id="txtTajukMsyrt" onkeyup="this.value=this.value.toUpperCase()" $readOnly>$mesyuarat.tajuk_Mesyuarat</textarea>
          </label>        </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3">
        <div align="left">Kategori Mesyuarat</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label>
          <select name="socKategoriMsyrt" id="socKategoriMsyrt" $disable>
        #if ($mesyuarat.kategori_Mesyuarat == '1')
          <option>-Sila Pilih-</option>
            <option value="1" selected="selected">AD-HOC</option>
            <option value="2">RUTIN</option>
        
        #elseif ($mesyuarat.kategori_Mesyuarat == '2')
          <option>-Sila Pilih-</option>
            <option value="1">AD-HOC</option>
            <option value="2" selected="selected">RUTIN</option>
        #elseif ($mesyuarat.kategori_Mesyuarat == '0')
          <option selected="selected">-Sila Pilih-</option>
            <option value="1">AD-HOC</option>
            <option value="2">RUTIN</option>
         #end
        </select>
          </label>       </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3"> 
        <div align="left">Tarikh</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label>
          <input name="txdTarikh" type="text" id="txdTarikh" value="$mesyuarat.tarikh_Mesyuarat" size="10" readonly="readonly"/>
          </label>
        <a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>       </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3">
        <div align="left">Dari Jam</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label>
          <input type="text" name="txtDariJam" id="txtDariJam" value="$mesyuarat.masa_Mesyuarat_Dari" $readOnly/>
          </label>
        <label>
        <select name="socWaktuDariJam" id="socWaktuDariJam" $disable>
      #if ($mesyuarat.waktu_Mesyuarat_Dari == '0')
        <option selected="selected">-Sila Pilih-</option>
          <option value="1">PAGI</option>
          <option value="2">PETANG</option>
          <option value="3">MALAM</option>
      
      #elseif ($mesyuarat.waktu_Mesyuarat_Dari == '1')
        <option>-Sila Pilih-</option>
          <option value="1" selected="selected">PAGI</option>
          <option value="2">PETANG</option>
          <option value="3">MALAM</option>
      
      #elseif ($mesyuarat.waktu_Mesyuarat_Dari == '2')
        <option>-Sila Pilih-</option>
          <option value="1">PAGI</option>
          <option value="2" selected="selected">PETANG</option>
          <option value="3">MALAM</option>
      
      #elseif ($mesyuarat.waktu_Mesyuarat_Dari == '3') 
        <option>-Sila Pilih-</option>
          <option value="1">PAGI</option>
          <option value="2">PETANG</option>
          <option value="3" selected="selected">MALAM</option>
      #end
      </select>
        </label>       </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3">
        <div align="left">Hingga Jam</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label>
          <input type="text" name="txtHinggaJam" id="txtHinggaJam" value="$mesyuarat.masa_Mesyuarat_Hingga" $readOnly/>
          </label>
        <label>
        <select name="socWaktuHinggaJam" id="socWaktuHinggaJam" $disable>
        #if ($mesyuarat.waktu_Mesyuarat_Hingga == '0')
        <option selected="selected">-Sila Pilih-</option>
          <option value="1">PAGI</option>
          <option value="2">PETANG</option>
          <option value="3">MALAM</option>
     
      #elseif ($mesyuarat.waktu_Mesyuarat_Hingga == '1')
        <option>-Sila Pilih-</option>
          <option value="1" selected="selected">PAGI</option>
          <option value="2">PETANG</option>
          <option value="3">MALAM</option>
      
      #elseif ($mesyuarat.waktu_Mesyuarat_Hingga == '2')
        <option>-Sila Pilih-</option>
          <option value="1">PAGI</option>
          <option value="2" selected="selected">PETANG</option>
          <option value="3">MALAM</option>
     
      #elseif ($mesyuarat.waktu_Mesyuarat_Hingga == '3') 
        <option>-Sila Pilih-</option>
          <option value="1">PAGI</option>
          <option value="2">PETANG</option>
          <option value="3" selected="selected">MALAM</option>
      #end
      </select>
        </label>       </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3">
        <div align="left">Lokasi</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label></label>
       $selectLokasi</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3">
        <div align="left">Status Mesyuarat</div>
      </div></td>
     <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label></label>
        $selectStatus</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3">
        <div align="left">Nama Pegawai</div>
      </div></td>
      <td scope="row"><div align="right"></div></td>
      <td>
        <label></label>
        $selectPegawai</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3">
        <div align="left">Urusetia / Seksyen </div>
      </div></td>
      <td scope="row"><div align="right"></div></td>
      <td>
        <label></label>
        $selectSeksyen</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style3">
        <div align="left">No Fail</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label></label>
       $selectFail</td>
    </tr>
    <tr>
      <td scope="row" valign="top"><div align="right" class="style3">
        <div align="left">Catatan</div>
      </div></td>
      <td width="1%" scope="row" valign="top"><div align="right">:</div></td>
      <td>
        <label>
          <textarea name="txtCatatan" cols="41" id="txtCatatan"$readOnly onChange="javascript:this.value=ucwords(this.value)">$mesyuarat.catatan</textarea>
          </label>    </td>
    </tr>
    <tr>
      <td scope="row" valign="top">&nbsp;</td>
      <td scope="row" valign="top">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" valign="top" scope="row"><span class="style5 style47 style69 style45 style4"><span class="style47 style69 style45 style40"><span class="style6">Perhatian </span>: Pastikan label berwarna <span class="style6">merah</span> wajib diisi..</span></span></td>
      </tr>
    <tr>
      <td colspan="3" align="center" scope="row">
        #if ($mode == 'View')
        <label>
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick = "kemaskini()" />
        </label>
        <label>
        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" />
        </label>
        <label>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
        </label>
        #else
        <label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick = "simpan()" />
        </label>
        <label>
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
        </label>
        #end        </td>
    </tr>
  </table>
</div>
</fieldset>
&nbsp;
<fieldset>
  <legend>SENARAI AHLI MESYUARAT</legend>
  #if ($mode == 'View')
  <label>
  <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambahAhli('$mesyuarat.id_Mesyuarat','tambah')"/>
  </label>
  #end
  <label></label>

<table width="100%" >
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="20%">AHLI MESYUARAT DALAMAN</td>
    <td width="10%">SEKSYEN</td>
    <td width="20%">AHLI MESYUARAT LUARAN</td>
    <td width="10%">AGENSI LUAR</td>
  </tr>
  #foreach ($ahli in $SenaraiAhliMesyuarat)
  #if ($ahli.bil == '') 
  	#set ($row = 'row1')
  #elseif ($ahli.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$ahli.bil</td>
    #if ($ahli.bil != '') 
    <td class="$row"><a href="javascript:papar_ahli('$ahli.id_Ahlimesyuarat','papar')" class="style1 style1">$ahli.nama_Pegawai</a></td>
    #else
    <td class="$row">$ahli.nama_Pegawai</td>
    #end
    <td class="$row">$ahli.kod_Seksyen</td>
    <td class="$row"><a href="javascript:papar_ahli('$ahli.id_Ahlimesyuarat','papar')" class="style1 style1">$ahli.nama_Ahlimesyuarat</a></td>
    <td class="$row">$ahli.agensi_Luar</td>
  </tr>
  #end
</table>
</fieldset>
&nbsp;
<fieldset>
<legend>SENARAI AGENDA MESYUARAT<strong></strong></legend>
#if ($mode == 'View')
  <label>
  <input type="button" name="cmdTambah2" id="cmdTambah2" value="Tambah" onclick="tambahAgenda('$mesyuarat.id_Mesyuarat','tambah')" />
  </label>
#end
<table width="100%" >
  <tr class="table_header">
    <td width="5%" scope="row">NO</td>
    <td width="95%">AGENDA MESYUARAT</td>
  </tr>
  #foreach ($agenda in $SenaraiAgendaMesyuarat)
   #if ($agenda.bil == '') 
  	#set ($row = 'row1')
  #elseif ($agenda.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$agenda.bil</td>
    #if ($agenda.bil != '')
    <td class="$row"><a href="javascript:papar_agenda('$agenda.id_Agendamesyuarat','papar')" class="style1">$agenda.agenda_Mesyuarat</a></td>
    #else
     <td class="$row">$agenda.agenda_Mesyuarat</td>
     #end  </tr>
  #end
</table>
</fieldset>

<table width="100%" border="0" >
  <tr>
    <td align="right"><strong>CL-05-19</strong></td>
  </tr>
</table>
#end
<!--/form-->
<script>
function batal(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=SenaraiMesyuarat";
	document.${formName}.submit();
}
function kembali(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=SenaraiMesyuarat";
	document.${formName}.submit();
}
function kemaskini(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=kemaskini";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function simpan(){
	
	if (document.${formName}.txtBilMsyrt.value == ""){
		alert('Sila masukkan " Bil Mesyuarat " terlebih dahulu.');
		document.${formName}.txtBilMsyrt.focus();
		return;
	} 
	if (document.${formName}.txtTajukMsyrt.value == ""){
		alert('Sila masukkan " Tajuk Mesyuarat " terlebih dahulu.');
		document.${formName}.txtTajukMsyrt.focus();
		return;
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=simpan";
	if (document.${formName}.idMesyuarat.value == "" || document.${formName}.idMesyuarat.value == 0){
		
		document.${formName}.mode.value = "tambahBaru";
	}
	else{
		
		document.${formName}.mode.value = "kemaskiniMesyuarat";
	}
	document.${formName}.submit();
}
function tambahAhli(id,command){

	
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAhliMesyuarat?idMesyuarat="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
function papar_ahli(id,command){

	
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAhliMesyuarat?ahliMesyuarat="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}

function tambahAgenda(id,command){

	
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAgendaMesyuarat?idMesyuarat="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
function papar_agenda(id,command){

	
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAgendaMesyuarat?agendaMsyrt="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}

function refresh() {
alert('refresh');
}

</script>