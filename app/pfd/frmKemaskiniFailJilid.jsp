<style type="text/css">
<!--
.style1 {font-size: 10px}
.style2 {color: #0000FF}
-->
</style>
<!--form id="f1" name="f1" method="post" action1=""-->
&nbsp;
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<table width="100%">
#foreach ($fail in $MaklumatFail)
<input name="idFail" type="hidden" value="$fail.idFail" />
<input name="command" type="hidden" value="" />
<input name="flagFail" type="hidden" value="$fail.flag_Fail" />
<input name="txtNoFailInduk" type="hidden" value="$fail.noFailRoot.toUpperCase()" />
<input name="id_Negeri" type="hidden" value="$fail.id_Negeri" />
<input name="id_Seksyen" type="hidden" value="$fail.id_Seksyen" />
<input name="id_Urusan" type="hidden" value="$fail.id_Urusan" />
<input name="id_Suburusan" type="hidden" value="$fail.id_Suburusan" />
<input name="id_Tarafkeselamatan" type="hidden" value="$fail.id_Tarafkeselamatan" />
<input name="id_Status" type="hidden" value="$fail.id_Status" />


  <tr>
    <td width="29%" scope="row"><div align="right" class="style1">
      <div align="left">No Fail Induk</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%"><span class="style2">$fail.noFailRoot.toUpperCase()</span></td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">No Fail Semasa</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td><span class="style2">
      <label>
      $fail.noFail.toUpperCase()      </label>
    </span></td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="right" class="style1">
      <div align="left">Tajuk Fail</div>
    </div></td>
    <td width="1%" scope="row" valign="top"><div align="right">:</div></td>
    <td><span class="style2">$fail.tajukFail.toUpperCase()</span></td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">Status Fail</div>
    </div></td>
   <td width="1%" scope="row"><div align="right">:</div></td>
    <td><span class="style2">$selectStatus    </span></td>
  </tr>
</table>
</fieldset>
&nbsp;
<fieldset>
<legend>MAKLUMAT FAIL JILID</legend>

<div align="center">
  <table width="100%">
    <tr>
      <td width="29%" scope="row"><div align="right" class="style1">
        <div align="left">Negeri</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td width="70%">$selectNegeri</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1">
        <div align="left">Seksyen</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>$selectSeksyen</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1">
        <div align="left">Urusan</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>$selectUrusan</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1">
        <div align="left">Sub Urusan</div>
      </div></td>
     <td width="1%" scope="row"><div align="right">:</div></td>
      <td>$selectSubUrusan</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1">
        <div align="left">Taraf Keselamatan</div>
      </div></td>
     <td width="1%" scope="row"><div align="right">:</div></td>
      <td>$selectTaraf</td>
    </tr>
    <tr>
      <td scope="row" valign="top"><div align="right" class="style1">
        <div align="left">Tajuk Fail</div>
      </div></td>
      <td width="1%" scope="row" valign="top"><div align="right">:</div></td>
      <td>
        <label></label>
        <textarea name="txtTajukFailJilid" cols="41" readonly="readonly" >$fail.tajukFail</textarea></td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1">
        <div align="left">Status Fail</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>$selectStatus</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1">
        <div align="left">Lokasi Simpanan Fail</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label></label>
      $selectLokasi</td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1">
        <div align="left">Kabinet</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label></label>
        <textarea name="faharasat" cols="41" readonly="readonly" >$fail.faharasat</textarea></td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1">
        <div align="left">Tarikh Daftar Fail</div>
      </div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td>
        <label></label>
        <input name="txtTarikhDaftar" type="text" value="$fail.tarikh_Daftar_Fail" size="10" readonly="readonly" /></td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style1"></div></td>
      <td width="1%" scope="row">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" align="center" scope="row">
       
      
          <div align="center">
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick ="simpan()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
          </div></td>
    </tr>
    #end
  </table>
</div>
</fieldset>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-15</strong></td>
  </tr>
</table>
<!--/form-->
<script>

function simpan(){
if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFailJilid&action1=simpan";
	document.${formName}.value = "simpan";
//	document.${formName}.action1  = "";
	document.${formName}.submit();
	

}
function kembali(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFailJilid&action1=SenaraiFail";
	document.${formName}.submit();
}

</script>