#set($saizTxtJabLaut="4000")
#set($saizTxtUlasanJPS="4000")
#set($saizTxtJabGeoSains="4000")
#set($saizTxtUlasanJabPerikanan="4000")
#set($saizTxtUlasanPusatHidrografi="4000")
#set($saizTxtUlasanJabatanAlamSekitar="4000")
#set($saizTxtNotaTambahan="4000")
#set($saizTxtUlasanJUPEM="4000")
#set($saizTxtUlasanPTG="4000")

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>KAWASAN PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatKawasanPermohonan in $BeanMaklumatKawasanPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="29%">Luar Perairan Negeri</td>
          <td width="70%">: 
            $beanMaklumatKawasanPermohonan.negeriPerairan</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="29%">Luas Di Pohon</td>
          <td width="70%">: 
            $beanMaklumatKawasanPermohonan.luas $beanMaklumatKawasanPermohonan.unitLuas</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI KOORDINAT</strong></legend>
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>Label Koordinat</strong></td>
          <td align="center"><strong>Latitud (U)</strong></td>
          <td align="center"><strong>Longitud (T)</strong></td>
        </tr>
        #set ($listKoordinat = "")
        #if ($SenaraiKoordinat.size() > 0)
        #foreach ($listKoordinat in $SenaraiKoordinat)
        #if ($listKoordinat.bil == '')
        #set( $row = "row1" )
        #elseif (($listKoordinat.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$listKoordinat.bil</td>
          <td class="$row">$listKoordinat.labelTitik</td>
          <td align="center" class="$row">$listKoordinat.darjahU&deg;$listKoordinat.minitU&prime;$listKoordinat.saatU&Prime;</td>
          <td align="center" class="$row">$listKoordinat.darjahT&deg;$listKoordinat.minitT&prime;$listKoordinat.saatT&Prime;</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td align="center" class="row1">&nbsp;</td>
          <td align="center" class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PERTINDIHAN</strong></legend>
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>No Fail</strong></td>
          <td width="20%" ><strong>Nama Syarikat</strong></td>
          <td width="20%" ><strong>Jenis Pertindihan</strong></td>
          <td width="20%" ><strong>Status</strong></td>
        </tr>
        #set ($listPertindihan = "")
        #if ($SenaraiPertindihan.size() > 0)
        #foreach ($listPertindihan in $SenaraiPertindihan)
        #if ($listPertindihan.bil == '')
        #set( $row = "row1" )
        #elseif (($listPertindihan.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$listPertindihan.bil</td>
          <td class="$row">$listPertindihan.noFail</td>
          <td class="$row" >$listPertindihan.namaSykt</td>
          <td class="$row" >$listPertindihan.jenisPertindihan</td>
          <td class="$row" >$listPertindihan.status</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #foreach ($beanMaklumatKertasRingkas in $BeanMaklumatKertasRingkas)
  <tr>
    <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td valign="top"></td>
          <td valign="top">Jabatan Ukur dan Pemetaan Malaysia (JUPEM)</td>
          <td valign="top">:</td>
          <td><textarea name="txtUlasanJUPEM" id="txtUlasanJUPEM" rows="5" cols="100" $readonly class="$inputTextClass" onkeyup="textCounter(this.form.txtUlasanJUPEM,this.form.remLen7,$!saizTxtUlasanJUPEM);" onkeydown="textCounter(this.form.txtUlasanJUPEM,this.form.remLen7,$!saizTxtUlasanJUPEM);"  >$beanMaklumatKertasRingkas.ulasanJUPEM</textarea></td>
          #foreach ($beanMaklumatDokumenJUPEM in $BeanMaklumatDokumenJUPEM)
                  	#set($idDokumen = $beanMaklumatDokumenJUPEM.idDokumen)
    				#set($namaFail = $beanMaklumatDokumenJUPEM.namaFail)
          <td valign="top">Lampiran:<br>#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
          #end
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen7" size="3" maxlength="3" value="$!saizTxtUlasanJUPEM" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Jabatan Pengairan dan Saliran (JPS)</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanJPS" id="txtUlasanJPS" rows="5" cols="100" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtUlasanJPS,this.form.remLen2,$!saizTxtUlasanJPS);" onKeyDown="textCounter(this.form.txtUlasanJPS,this.form.remLen2,$!saizTxtUlasanJPS);" >$beanMaklumatKertasRingkas.ulasanJPS</textarea></td>
          #foreach ($beanMaklumatDokumenJPS in $BeanMaklumatDokumenJPS)
                  	#set($idDokumen = $beanMaklumatDokumenJPS.idDokumen)
    				#set($namaFail = $beanMaklumatDokumenJPS.namaFail)
          <td valign="top">Lampiran:<br>#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
          #end
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtUlasanJPS" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Jabatan Mineral dan Geosains Malaysia (JMG)</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanJabGeoSains" id="txtUlasanJabGeoSains" rows="5" cols="100" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtUlasanJabGeoSains,this.form.remLen3,$!saizTxtJabGeoSains);" onKeyDown="textCounter(this.form.txtUlasanJabGeoSains,this.form.remLen3,$!saizTxtJabGeoSains);" >$beanMaklumatKertasRingkas.ulasanJabGeoSains</textarea></td>
          #foreach ($beanMaklumatDokumenJMG in $BeanMaklumatDokumenJMG)
                  	#set($idDokumen = $beanMaklumatDokumenJMG.idDokumen)
    				#set($namaFail = $beanMaklumatDokumenJMG.namaFail)
          <td valign="top">Lampiran:<br>#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
          #end
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen3" size="3" maxlength="3" value="$!saizTxtJabGeoSains" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Pusat Hidrografi Nasional (PHN)</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanPusatHidrografi" id="txtUlasanPusatHidrografi" rows="5" cols="100" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtUlasanPusatHidrografi,this.form.remLen5,$!saizTxtUlasanPusatHidrografi);" onKeyDown="textCounter(this.form.txtUlasanPusatHidrografi,this.form.remLen5,$!saizTxtUlasanPusatHidrografi);"  >$beanMaklumatKertasRingkas.ulasanPusatHidrografi</textarea></td>
          #foreach ($beanMaklumatDokumenPHN in $BeanMaklumatDokumenPHN)
                  	#set($idDokumen = $beanMaklumatDokumenPHN.idDokumen)
    				#set($namaFail = $beanMaklumatDokumenPHN.namaFail)
          <td valign="top">Lampiran:<br>#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
          #end
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen5" size="3" maxlength="3" value="$!saizTxtUlasanPusatHidrografi"/></td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Jabatan Perikanan Malaysia (DOF)</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanJabPerikanan" id="txtUlasanJabPerikanan" rows="5" cols="100" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtUlasanJabPerikanan,this.form.remLen4,$!saizTxtUlasanJabPerikanan);" onKeyDown="textCounter(this.form.txtUlasanJabPerikanan,this.form.remLen4,$!saizTxtUlasanJabPerikanan);" >$beanMaklumatKertasRingkas.ulasanJabPerikanan</textarea></td>
          #foreach ($beanMaklumatDokumenDOF in $BeanMaklumatDokumenDOF)
                  	#set($idDokumen = $beanMaklumatDokumenDOF.idDokumen)
    				#set($namaFail = $beanMaklumatDokumenDOF.namaFail)
    	  
          <td valign="top">Lampiran:<br>#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
          #end
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen4" size="3" maxlength="3" value="$!saizTxtUlasanJabPerikanan" /></td>
        </tr>
        #end
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">Jabatan Laut Semenanjung Malaysia (JLM)</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtJabLaut" id="txtJabLaut" rows="5" cols="100" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtJabLaut,this.form.remLen1,$!saizTxtJabLaut);" onKeyDown="textCounter(this.form.txtJabLaut,this.form.remLen1,$!saizTxtJabLaut);" >$beanMaklumatKertasRingkas.ulasanJabLaut</textarea></td>
          #foreach ($beanMaklumatDokumenJLM in $BeanMaklumatDokumenJLM)
                  	#set($idDokumen = $beanMaklumatDokumenJLM.idDokumen)
    				#set($namaFail = $beanMaklumatDokumenJLM.namaFail)
          <td valign="top">Lampiran:<br>#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
          #end
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtJabLaut" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Jabatan Alam Sekitar (JAS)</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanJabatanAlamSekitar" id="txtUlasanJabatanAlamSekitar" rows="5" cols="100" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtUlasanJabatanAlamSekitar,this.form.remLen9,$!saizTxtUlasanJabatanAlamSekitar);" onKeyDown="textCounter(this.form.txtUlasanJabatanAlamSekitar,this.form.remLen9,$!saizTxtUlasanJabatanAlamSekitar);"  >$beanMaklumatKertasRingkas.ulasanJAS</textarea></td>
          #foreach ($beanMaklumatDokumenJAS in $BeanMaklumatDokumenJAS)
                  	#set($idDokumen = $beanMaklumatDokumenJAS.idDokumen)
    				#set($namaFail = $beanMaklumatDokumenJAS.namaFail)
          <td valign="top">Lampiran:<br>#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
          #end
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen9" size="3" maxlength="3" value="$!saizTxtUlasanJabatanAlamSekitar"/></td>
        </tr>
        #end
        <tr>
          <td valign="top"></td>
          <td valign="top">Ulasan Pengarah Tanah dan Galian (PTG)</td>
          <td valign="top">:</td>
          <td><textarea name="txtUlasanPTG" id="txtUlasanPTG" rows="5" cols="100" $readonly class="$inputTextClass" onkeyup="textCounter(this.form.txtUlasanPTG,this.form.remLen8,$!saizTxtUlasanPTG);" onkeydown="textCounter(this.form.txtUlasanPTG,this.form.remLen8,$!saizTxtUlasanPTG);"  >$beanMaklumatKertasRingkas.ulasanPTG</textarea></td>
          #foreach ($beanMaklumatDokumenPTG in $BeanMaklumatDokumenPTG)
                  	#set($idDokumen = $beanMaklumatDokumenPTG.idDokumen)
    				#set($namaFail = $beanMaklumatDokumenPTG.namaFail)
          <td valign="top">Lampiran:<br>#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
          #end
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen8" size="3" maxlength="3" value="$!saizTxtUlasanPTG" /></td>
        </tr>
        #end
        <tr>
          <td valign="top"></td>
          <td valign="top">Nota Tambahan</td>
          <td valign="top">:</td>
          <td><textarea name="txtNotaTambahan" id="txtNotaTambahan" rows="5" cols="100" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtNotaTambahan,this.form.remLen6,$!saizTxtNotaTambahan);" onKeyDown="textCounter(this.form.txtNotaTambahan,this.form.remLen6,$!saizTxtNotaTambahan);"  >$beanMaklumatKertasRingkas.nota</textarea></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen6" size="3" maxlength="3" value="$!saizTxtNotaTambahan" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
            #if($idStatus =='1610235')
            <input type="button" name="cmdSeterusnya" id="cmdHantar2" value="Seterusnya" onClick="doSeterusnya()"/>
            #end  
            <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>     
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="doSimpanKemaskiniMaklumatKertasRingkas()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
            #end </td>
        </tr>
      </table></td>
  </tr>
  #end
</table>

<script>
function cetakDokumen(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>
