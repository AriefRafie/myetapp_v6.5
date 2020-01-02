<style type="text/css">
<!--
.style1 {
	color: #0000FF;
	font-weight: bold;
}
-->
</style>
<input type="hidden" name="actionHakmilik"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT HAKMILIK</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              #foreach ($beanMaklumatHakmilik in $BeanMaklumatHakmilik)
              <input type="hidden" name="idHakmilik" value="$beanMaklumatHakmilik.idHakmilik"/>
              <input type="hidden" name="noResit" value="$beanMaklumatHakmilik.noResit"/>
              <input type="hidden" name="idPermohonanSimati" value="$beanMaklumatHakmilik.idPermohonanSimati"/>
              <tr>
                <td width="37%" align="right">ID HAKMILIK :</td>
                <td width="63%"><span class="style1">$beanMaklumatHakmilik.idHakmilik</span></td>
              </tr>
              <tr>
                <td align="right">ID JENISHAKMILIK :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idJenisHakmilik</span></td>
              </tr>
              <tr>
                <td align="right">NO. HAKMILIK :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noHakmilik</span></td>
              </tr>
              <tr>
                <td align="right">NO WARTA :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noWarta</span></td>
              </tr>
              <tr>
                <td align="right">TARIKH WARTA :</td>
                <td><span class="style1">$beanMaklumatHakmilik.tarikhWarta</span></td>
              </tr>
              <tr>
                <td align="right">ID LUAS :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idLuas</span></td>
              </tr>
              <tr>
                <td align="right">LUAS :</td>
                <td><span class="style1">$beanMaklumatHakmilik.luas</span></td>
              </tr>
              <tr>
                <td align="right">ID NEGERI :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idNegeri</span></td>
              </tr>
              <tr>
                <td align="right">ID DAERAH :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idDaerah</span></td>
              </tr>
              <tr>
                <td align="right">ID MUKIM :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idMukim</span></td>
              </tr>
              <tr>
                <td align="right">ID LOT :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idLot</span></td>
              </tr>
              <tr>
                <td align="right">NO LOT :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noLot</span></td>
              </tr>
              <tr>
                <td align="right">NO SYIT :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noSyit</span></td>
              </tr>
              <tr>
                <td align="right">NO PELAN :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noPelan</span></td>
              </tr>
              <tr>
                <td align="right">SYARAT NYATA :</td>
                <td><span class="style1">$beanMaklumatHakmilik.syarat</span></td>
              </tr>
              <tr>
                <td align="right">SEKATAN :</td>
                <td><span class="style1">$beanMaklumatHakmilik.sekatan</span></td>
              </tr>
              <tr>
                <td align="right">KEGUNAAN TANAH :</td>
                <td><span class="style1">$beanMaklumatHakmilik.kegunaanTanah</span></td>
              </tr>
              <tr>
                <td align="right">ID KATEGORI :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idKategori</span></td>
              </tr>
              <tr>
                <td align="right">ID SUBKATEGORI :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idSubKategori</span></td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td align="right">LOKASI :</td>
                <td><span class="style1">$beanMaklumatHakmilik.lokasi</span></td>
              </tr>
              <tr>
                <td align="right">NO BANGUNAN :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noBangunan</span></td>
              </tr>
              <tr>
                <td align="right">NO TINGKAT :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noTingkat</span></td>
              </tr>
              <tr>
                <td align="right">NO. PETAK :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noPetak</span></td>
              </tr>
              <tr>
                <td align="right">CUKAI :</td>
                <td><span class="style1">$beanMaklumatHakmilik.cukai</span></td>
              </tr>
              <tr>
                <td align="right">TARIKH DAFTAR :</td>
                <td><span class="style1">$beanMaklumatHakmilik.tarikhDaftar</span></td>
              </tr>
              <tr>
                <td align="right">TARAF HAKMILIK :</td>
                <td><span class="style1">$beanMaklumatHakmilik.tarafHakmilik</span></td>
              </tr>
              <tr>
                <td align="right">TEMPOH :</td>
                <td><span class="style1">$beanMaklumatHakmilik.tempoh</span></td>
              </tr>
              <tr>
                <td align="right">TARIKH LUPUT :</td>
                <td><span class="style1">$beanMaklumatHakmilik.tarikhLuput</span></td>
              </tr>
              <tr>
                <td align="right">NO PU :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noPU</span></td>
              </tr>
              <tr>
                <td align="right">STATUS PAJAKAN:</td>
                <td><span class="style1">$beanMaklumatHakmilik.statusPajakan</span></td>
              </tr>
              <tr>
                <td align="right">TARIKH TAMAT PAJAKAN :</td>
                <td><span class="style1">$beanMaklumatHakmilik.tarikhTamatPajakan</span></td>
              </tr>
              <tr>
                <td align="right">STATUS RIZAB :</td>
                <td><span class="style1">$beanMaklumatHakmilik.statusRizab</span></td>
              </tr>
              <tr>
                <td align="right">ID JENIS RIZAB :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idJenisRizab</span></td>
              </tr>
              <tr>
                <td align="right">KAWASAN RIZAB :</td>
                <td><span class="style1">$beanMaklumatHakmilik.kawasanRizab</span></td>
              </tr>
              <tr>
                <td align="right">NO FAIL PTD :</td>
                <td><span class="style1">$beanMaklumatHakmilik.noFailPTD</span></td>
              </tr>
              <tr>
                <td align="right">NO FAIL PTG:</td>
                <td><span class="style1">$beanMaklumatHakmilik.noFailPTG</span></td>
              </tr>
              <tr>
                <td align="right">STATUS HAKMILIK:</td>
                <td><span class="style1">$beanMaklumatHakmilik.statusHakmilik</span></td>
              </tr>
              <tr>
                <td align="right">TARIKH TERIMA :</td>
                <td><span class="style1">$beanMaklumatHakmilik.tarikhTerima</span></td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"><input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
    </td>
  </tr>
</table>
<script>
function kembali() {	
	document.${formName}.actionHakmilik.value = "";
	document.${formName}.submit();
}
</script>
