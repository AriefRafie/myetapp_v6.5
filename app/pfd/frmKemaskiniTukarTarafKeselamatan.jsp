<script type="text/javascript" src="../library/js/ekptgTools.js" ></script>
<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {font-size: 10px}
.style4 {
	font-style: italic;
	font-size: 9px;
}
.style5 {font-size: 9px}
.style6 {color: #000000; }
-->
</style>


<!--form id="f1" name="f1" method="post" action1=""-->

<input name="command" type="hidden" value="" />
#foreach ($view in $View)
<input name="idFail" type="hidden" value="$view.idFail"/>
<input name="flagFail" type="hidden" value="$view.flagFail" />
<input name="id_Negeri" type="hidden" value="$view.idNegeri" />
<input name="id_Seksyen" type="hidden" value="$view.idSeksyen" />
<input name="id_Urusan" type="hidden" value="$view.idUrusan" />
<input name="id_Suburusan" type="hidden" value="$view.idSuburusan" />
<input name="id_Status" type="hidden" value="$view.idStatus" />
<input name="noFail" type="hidden" value="$view.noFail" />
<input name="tajukFail" type="hidden" value="$view.tajukFail" />
<input name="idTarafkeselamatan" type="hidden" value="$view.idTarafkeselamatan" />
&nbsp;
<fieldset>
<legend>MAKLUMAT PERTUKARAN TARAF KESELAMATAN</legend><br/><br/><br/>
<table width="100%" align="center">
  <tr>
    <td width="2%" scope="row"><div align="right"></div></td>
    <td width="27%" scope="row"><div align="right" class="style2">
      <div align="left">No Fail Asal</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td width="70%"><div align="left">$view.noFail</div></td>
  </tr>
<!--    <tr>
    <td width="22%" scope="row"><div align="right"></div></td>
    <td width="15%" scope="row"><div align="right" class="style2">
      <div align="left">Tajuk Fail</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td width="50%"><div align="left">$view.tajukfail</div></td>
  </tr>-->
<!--  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right" class="style2">
      <div align="left">Negeri</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">:</div></td>
    <td>$selectNegeri</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row"><div align="right" class="style2">
      <div align="left">Seksyen</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">:</div></td>
    <td>$selectSeksyen</td>
  </tr>-->
  <tr>
    <td scope="row"><div align="right"></div></td>
    <td scope="row"><div align="right" class="style2">
      <div align="left">Urusan</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td><div align="left">$selectUrusan</div></td>
  </tr>
  <tr>
    <td scope="row"><div align="right"></div></td>
    <td scope="row"><div align="right" class="style2">
      <div align="left">Sub Urusan</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td><div align="left">$selectSubUrusan</div></td>
  </tr>
  <tr>
    <td scope="row"><div align="right"><span class="style1">*</span></div></td>
    <td scope="row"><div align="right" class="style2">
      <div align="left">Taraf Keselamatan</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td><div align="left">$selectTaraf</div></td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="right"></div></td>
    <td scope="row" valign="top"><div align="right" class="style6">
      <div align="left">Tajuk Fail</div>
    </div></td>
    <td width="1%" scope="row" valign="top"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td>
      
      <div align="left">
        <textarea name="txtTajukFail" id="txtTajukFail" cols="41"  class = "disabled" readonly="readonly" >$view.tajukFail</textarea>
      </div></td>
  </tr>
  <tr>
    <td scope="row"><div align="right"></div></td>
    <td scope="row"><div align="right" class="style6">
      <div align="left">Status Fail</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td><div align="left">$selectStatus</div></td>
  </tr>
  <tr>
    <td scope="row"><div align="right"></div></td>
    <td scope="row"><div align="right" class="style2">
      <div align="left">Lokasi Simpanan Fail</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td>
      <div align="left">$selectLokasi    </div></td>
  </tr>
  <tr>
    <td scope="row"><div align="right"></div></td>
    <td scope="row"><div align="right" class="style2">
      <div align="left">Kabinet</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td>
      <div align="left">
        <textarea name="faharasat"   class = "disabled" readonly="readonly" cols="41" id="faharasat" onchange="javascript:this.value=ucwords(this.value)">$view.faharasat</textarea>
      </div></td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="right"></div></td>
    <td scope="row" valign="top"><div align="right" class="style2">
      <div align="left">Catatan</div>
    </div></td>
    <td width="1%" scope="row" valign="top"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td><label>
      
      <div align="left">
          <textarea name="txtCatatan"   class = "disabled" readonly="readonly" cols="41" id="txtCatatan" onChange="javascript:this.value=ucwords(this.value)">$view.catatan</textarea>
      </div>
    </label></td>
  </tr>
  <tr>
    <td scope="row"><div align="right"></div></td>
    <td scope="row"><div align="right" class="style2">
        <div align="left">Tarikh Daftar Fail</div>
    </div></td>
    <td scope="row"><div align="right" class="style2">
      <div align="left">:</div>
    </div></td>
    <td> <div align="left">$view.tarikhDaftar </div></td>
  </tr>
  <tr>
    <td scope="row"><div align="right"></div></td>
    <td scope="row"><div align="left"></div></td>
    <td width="1%" scope="row"><div align="left"></div></td>
    <td><div align="left"></div></td>
  </tr>
  <tr>
    <td scope="row"><div align="right"></div></td>
    <td scope="row"><div align="right" class="style2">
    </div></td>
    <td scope="row"><div align="right" class="style2"></div></td>
    <td>
      <div align="left">
        <input name="txtTarikhTukar" type="hidden" id="txtTarikhTukar" value = "$!tarikhTukar" size="10"/>
      </div></td>
  </tr>
  <tr>
    <td colspan="4" class="style5 style47 style69 style45 style4" scope="row"><div align="center"><span class="style1">Perhatian </span>: Pastikan label berwarna <span class="style1">merah</span> wajib diisi.</div></td>
    </tr>
  <tr>
    <td colspan="4" align="center" scope="row">
      <label></label>
      <label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Tukar Status" onclick="simpan()" />
        </label>
      <label></label>
      <label></label>
      <label>	
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
        </label>   </td>
  </tr>
</table>
</fieldset>
#end
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-17</strong></td>
  </tr>
</table>
<!--/form-->
<script>
function kembali(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.TukarTarafKeselamatan&action1=SenaraiFail";
	document.${formName}.submit();
}
function simpan(){
if ( !window.confirm("Anda Pasti?") ) return;
	if (document.${formName}.socTarafA.value == ""){
		alert('Sila masukkan " Taraf Keselamatan " terlebih dahulu.');
		document.${formName}.socTarafA.focus();
		return;
	} 
	if (document.${formName}.txtTajukFail.value == ""){
		alert('Sila masukkan " Tajuk Fail " terlebih dahulu.');
		document.${formName}.txtTajukFail.focus();
		return;
	}
	if (document.${formName}.socStatusA.value == ""){
		alert('Sila masukkan " Status Fail " terlebih dahulu.');
		document.${formName}.socStatusA.focus();
		return;
	} 
		if (document.${formName}.idTarafkeselamatan.value == document.${formName}.socTarafA.value){
		alert('Penukaran Taraf adalah sama seperti yang asal. Sila pilih Taraf Keselamatan yang lain.');
		document.${formName}.socStatusA.focus();
		return;
	} 
	
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.TukarTarafKeselamatan&action1=simpan";
	document.${formName}.command.value = "simpan";
	document.${formName}.submit();

}


</script>