<style type="text/css">

#if ($!modul == 'htp')
	<!--
	#parse("css/eTapp_HTP.css") .style1 {
	color: #0000FF
	}
	.style2 {
		color: #FF0000
	}
	-->
#elseif($!modul == 'ppt')
	<!--
	#parse("css/eTapp_PPT.css") .style1 {
	color: #0000FF
	}
	.style2 {
		color: #FF0000
	}
	-->
#elseif($!modul == 'ppk')
	<!--
	#parse("css/eTapp_PPK.css") .style1 {
	color: #0000FF
	}
	.style2 {
		color: #FF0000
	}
	-->
#else       
#end
</style>
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="hitButt"/>
<input type="hidden" name="modul" value="$!modul"/>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagMsg == 'Y')
  <tr>
    <td colspan="3">&nbsp;
      <div class="success">HAKMILIK BERJAYA DIDAFTARKAN</div></td>
  </tr>
  #end
  #if ($flagMsg == 'N')
  <tr>
    <td colspan="3">&nbsp;
      <div class="error">HAKMILIK TIDAK BERJAYA DIDAFTARKAN : $outputMsg</div></td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT HAKMILIK</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              #foreach ($beanMaklumatHakmilik in $BeanMaklumatHakmilik)
              <input type="hidden" name="idPPKHTA" value="$beanMaklumatHakmilik.idMT"/>
              <input type="hidden" name="idHakmilik" value="$beanMaklumatHakmilik.idHakmilik"/>
              <input type="hidden" name="noResit" value="$beanMaklumatHakmilik.noResit"/>
              <input type="hidden" name="idPermohonanSimati" value="$beanMaklumatHakmilik.idPermohonanSimati"/>
              <tr>
                <td width="37%" align="right">NO. RESIT :</td>
                <td width="63%"><span class="style1">$beanMaklumatHakmilik.noResit</span></td>
              </tr>
              <tr>
                <td align="right">ID HAKMILIK :</td>
                <td><span class="style1">$beanMaklumatHakmilik.idHakmilik</span></td>
              </tr>
              <tr>
                <td align="right">NO. HAKMILIK :</td>
                <td><span class="style1">$beanMaklumatHakmilik.hakmilik</span></td>
              </tr>
              <tr>
                <td align="right">NO. LOT :</td>
                <td><span class="style1">$beanMaklumatHakmilik.lot</span></td>
              </tr>
              <tr>
                <td align="right">LUAS  :</td>
                <td><span class="style1">$beanMaklumatHakmilik.luas</span></td>
              </tr>
              <tr>
                <td align="right">MUKIM :</td>
                <td><span class="style1">$beanMaklumatHakmilik.mukim</span></td>
              </tr>
              <tr>
                <td align="right">DAERAH :</td>
                <td><span class="style1">$beanMaklumatHakmilik.daerah</span></td>
              </tr>
              <tr>
                <td align="right">NEGERI :</td>
                <td><span class="style1">$beanMaklumatHakmilik.negeri</span></td>
              </tr>
              #if($!modul == 'ppk')
              
              <tr>
                <td align="right">BAHAGIAN SIMATI :</td>
                <td><span class="style1">$beanMaklumatHakmilik.ba / $beanMaklumatHakmilik.bb</span></td>
              </tr>
              ##end
              #elseif($!modul == 'ppt')
              <tr>
                <td align="right">SYER :</td>
                <td><span class="style1">$beanMaklumatHakmilik.ba / $beanMaklumatHakmilik.bb</span></td>
              </tr>
              #end
              <tr>
                <td align="right"> PAJAKAN :</td>
                <td><span class="style1">$beanMaklumatHakmilik.pajakan</span></td>
              </tr>
              <tr>
                <td align="right">NO. PERSERAHAN PAJAKAN:</td>
                <td><span class="style1">$beanMaklumatHakmilik.noPerserahan</span></td>
              </tr>
              <tr>
                <td align="right">GADAIAN :</td>
                <td><span class="style1">$beanMaklumatHakmilik.gadaian</span></td>
              </tr>
              <tr>
                <td align="right">NO. PERSERAHAN GADAIAN:</td>
                <td><span class="style1">$beanMaklumatHakmilik.noPerserahanGadaian</span></td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">KAVEAT :</td>
                <td width="63%" valign="top"><span class="style1">$beanMaklumatHakmilik.kaveat</span></td>
              </tr>
              <tr>
                <td align="right" valign="top">NO. PERSERAHAN KAVEAT:</td>
                <td><span class="style1">$beanMaklumatHakmilik.noPerserahanKaveat</span></td>
              </tr>
              <tr>
                <td width="37%" align="right" valign="top">KATEGORI TANAH :</td>
                <td width="63%" valign="top"><span class="style1">$beanMaklumatHakmilik.kategoriTanah</span></td>
              </tr>
              <tr>
                <td align="right" valign="top">STATUS PEMILIKAN:</td>
                <td><span class="style1">$beanMaklumatHakmilik.statusPemilikan</span></td>
              </tr>
              <tr>
                <td width="37%" align="right" valign="top">SYARAT NYATA :</td>
                <td width="63%" rowspan="3" valign="top"><span class="style1">$beanMaklumatHakmilik.syarat</span></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">SEKATAN KEPENTINGAN :</td>
                <td rowspan="3" valign="top"><span class="style1">$beanMaklumatHakmilik.sekatan</span></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">CATATAN :</td>
                <td rowspan="3" valign="top"><span class="style1">$beanMaklumatHakmilik.catatan</span></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"><input type="button" name="cmdDaftar" id="cmdDaftar" value="Daftar Hakmilik" onClick="daftar()">
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
    </td>
  </tr>
</table>
<script>
	function kembali() {	
		document.${formName}.actionPopup.value = "";
		document.${formName}.submit();
	}
	function daftar() {	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		
		document.${formName}.hitButt.value = "daftar";
		document.${formName}.submit();
		
	}
</script>
