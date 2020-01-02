<style type="text/css">
<!--
.style2 {font-size: 10px}
.style3 {font-size: 10px; color: #FF0000; }
.style40 {color: #FF0000}
.style4 {
	font-size: 9px;
	font-style: italic;
}
.style5 {font-style: italic}
.style41 {color: #000000}
-->
</style>

<fieldset>
<legend>MAKLUMAT FAIL</legend>
<table width="100%">
  <tr>
    <td width="2%" scope="row"><span class="style3 style2">*</span></td>
    <td width="27%" scope="row"><div align="right">
      <div align="left">No Fail</div>
    </div></td>
    <td width="1%" scope="row">:</td>
    <td width="70%"><label>
    <input name="txtNoFail" type="text" id="txtNoFail" value="$!noFail" size="44" class="$disabledFail" $disabledFail $readonlyFail/>
    </label></td>
  </tr>
  <tr>
    <td scope="row"><span class="style3 style2">*</span></td>
    <td scope="row"><div align="right">
      <div align="left">Negeri</div>
    </div></td>
    <td scope="row">:</td>
    <td><!--<label>
      <select name="socNegeri" id="socNegeri">
      </select>
    </label>--> $selectNegeriC</td>
  </tr>
  <tr>
    <td scope="row"><span class="style3 style2">*</span></td>
    <td scope="row"><div align="right">
      <div align="left">Seksyen</div>
    </div></td>
    <td scope="row">:</td>
    <td><!--<label>
      <select name="socSeksyen" id="socSeksyen">
      </select>
    </label>--> $selectSeksyenC</td>
  </tr>
  <tr>
    <td scope="row"><span class="style3 style2">*</span></td>
    <td scope="row"><div align="right">
      <div align="left">Urusan</div>
    </div></td>
    <td scope="row">:</td>
    <td><!--<label>
      <select name="socUrusan" id="socUrusan">
      </select>
    </label>--> $selectUrusanC</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right">
      <div align="left">Sub Urusan</div>
    </div></td>
    <td scope="row">:</td>
    <td><!--<label>
      <select name="socSuburusan" id="socSuburusan">
      </select>
    </label>--> $selectSubUrusanC</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right">
      <div align="left">Taraf Keselamatan</div>
    </div></td>
   <td scope="row">:</td>
    <td><!--<label>
      <select name="socTaraf" id="socTaraf">
      </select>
    </label>--> $selectTarafC</td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="center"><span class="style3 style2">*</span></div></td>
    <td scope="row" valign="top"><div align="right" class="style40">
      <div align="left" class="style41">Tajuk Fail</div>
    </div></td>
    <td scope="row" valign="top">:</td>
    <td>
      <label>
        <textarea name="txtTajukFail" cols="41" id="txtTajukFail" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly>$!tajukFail</textarea>
        </label>       </td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right">
      <div align="left">Status Fail</div>
    </div></td>
   <td scope="row">:</td>
    <td><!--<label>
      <select name="socStatus" id="socStatus">
      </select>
    </label>--> $selectStatusC</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right">
      <div align="left">Lokasi Simpanan Fail</div>
    </div></td>
    <td scope="row">:</td>
    <td><input name="txtLokasiFail" type="text" id="txtLokasiFail" value="$!lokasiFail" size="44" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabledFail" $disabledFail $readonlyFail/></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right">
      <div align="left">Faharasat</div>
    </div></td>
    <td scope="row">:</td>
    <td><input name="txtFaharasat" type="txtFaharasat" id="txtFaharasat" value="$!faharasat" size="44" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabledFail" $disabledFail $readonlyFail/></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right">
      <div align="left">Didaftar Oleh</div>
    </div></td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtDidaftarOleh" type="text" id="txtDidaftarOleh" size="44"  value="$!user_Name" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly/>
    </label></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right">
      <div align="left">Tarikh Daftar Fail</div>
    </div></td>
  <td scope="row">:</td>
    <td>
      <label></label>
       <label>
       <input name="txtTarikhDaftar" type="text" id="txtTarikhDaftar" value="$!tarikhDaftar" size="10" class="$disabled" $disabled $readonly/>
       </label></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="left"></div></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style4"><span class="style40">Perhatian </span>: Pastikan label bertanda <span class="style40">*</span>  diisi.</span></td>
  </tr>
  <tr>
    <td colspan="4" align="center" scope="row">
    
     	#if($mode == 'baru')
     <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
     <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>

    #elseif($mode == 'papar')
         
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
     <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
           <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
    

    #elseif($mode == 'kemaskini')
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="update2('$!idFail')"/>
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
   	#else
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
    #end </td>
  </tr>
  <input name="action" type="hidden"/>
  <input name="mode" type="hidden"/>
  <input type="hidden" name="idFail"  value="$idFail"/>
</table>
</fieldset>
<table width="100%" border="0" >
  <tr>
    <td align="right"><strong>CL-05-04</strong></td>
  </tr>
</table>
<fieldset id="tableReport1" style="display:none;">

<legend><strong>Senarai Laporan</strong></legend>

            <table width="100%" border="0" cellspacing="2" cellpadding="2">

       <tr>

        <td><a href="#" class="style2" onClick="javascript:cetakDoketFail('$idFail')"><font color="blue">Doket Fail </font></a></td>

      </tr>          

    </table>

</fieldset>



<script>
function setTable(id){

            if(document.getElementById(id).style.display=="none"){

                        document.getElementById(id).style.display="block";

            }

            else if(document.getElementById(id).style.display=="block"){

                        document.getElementById(id).style.display="none";

            }

}

function cetakDoketFail(id_fail) {

            <!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->

            var url = "../servlet/ekptg.report.pfd.NoFailTajukFail?idFail="+id_fail;

    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');

    if ((document.window != null) && (!hWnd.opener))

            hWnd.opener = document.window;

    if (hWnd.focus != null) hWnd.focus();  

}

function kemaskini() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action=kemaskini";
	
	document.${formName}.submit();	
}

function update2(idFail){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action=update";
	//document.${formName}.idFail.value = idFail;
	document.${formName}.submit();	
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action=SenaraiFail";
	document.${formName}.submit();
}
function batal() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action=papar";
	document.${formName}.submit();
}
</script>