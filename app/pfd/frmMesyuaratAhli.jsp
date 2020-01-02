<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
  .hidden { visibility:collapse }
  .visible { visibility:visible }
-->


</style>
<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="SOC_UNIT" name="SOC_UNIT" value="$SOC_UNIT" />
<input type="hidden" id="Mesyuarat_Unit" name="Mesyuarat_Unit" value="$Mesyuarat_Unit" />

#if($check_pengerusi == "no")
<script>
alert("Maklumat Pengerusi telah wujud!");
</script>
#end


#if($check_setiausaha == "no")
<script>
alert("Maklumat Setiausaha telah wujud!");
</script>
#end

<fieldset>
  <legend><strong>MAKLUMAT MESYUARAT</strong></legend>
  <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" valign="top" nowrap="nowrap"><div align="left"><strong>No Fail / No Rujukan Mesyuarat</strong></div></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="70%" align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_NoFail </td>
    </tr>
    <tr>
      <td  align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Tajuk Mesyuarat</strong></div></td>
      <td  align="center" valign="top" nowrap="nowrap">:</td>
      <td  align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Tajuk      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Kategori Mesyuarat</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
      #if($Kategori_Mesyuarat == '1')
      AD-HOC
      #else
      RUTIN
      #end</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Tarikh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$!Mesyuarat_Tarikh</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Masa</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$!Mesyuarat_Masa</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Lokasi</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_Lokasi</td>
    </tr>
    #if($idNegeri == '16')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Urusetia / Seksyen</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_Seksyen</td>
    </tr>
    #else
        <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Urusetia / Unit</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_Unit</td>
    </tr>
    #end
    <tr>
      <td colspan="3"><div align="left"></div></td>
    </tr>
  </table>  
</fieldset>
<br />
<fieldset>
  <legend><strong>DAFTAR AHLI MESYUARAT</strong></legend>
 
   
   <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" valign="top" nowrap="nowrap"><div align="left">Kategori Ahli</div></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="70%" align="left" valign="top" >
      
      <span>  <input name="Ahli_Kategori" type="radio" id="Ahli_Kategori" value="1" onclick="toggleKategori('changeKategori1')" $Ahli_Kategori1 $RO_MaklumatMesyuarat />
        Pegawai Dalaman</span><span>
        <input name="Ahli_Kategori" type="radio" id="Ahli_Kategori" value="2" onclick="toggleKategori('changeKategori2')" $Ahli_Kategori2 $RO_MaklumatMesyuarat />
       Wakil Agensi Luar</span><span> 
        <input name="Ahli_Kategori" type="radio" id="Ahli_Kategori" value="3" onclick="toggleKategori('changeKategori3')" $Ahli_Kategori3 $RO_MaklumatMesyuarat />
        Urusetia</span></td>
    </tr>
#if ($Ahli_Kategori == '2')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Agensi</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Ahli_Agensi" type="text" id="Ahli_Agensi" value="$!Ahli_Agensi" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat />      </td>
    </tr>
#end
#if ($Ahli_Kategori == '1')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Seksyen </div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Ahli_Seksyen&nbsp;&nbsp;&nbsp;<span style="font-style:italic"><strong>atau</strong></span></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Negeri </div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"> $Ahli_Negeri</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Nama Pegawai </div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Ahli_Pegawai</td>
    </tr>
#end
#if ($Ahli_Kategori == '3')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Seksyen</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Ahli_Seksyen3&nbsp;&nbsp;&nbsp;<span style="font-style:italic"><strong>atau</strong></span></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Negeri</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"> $Ahli_Negeri3</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Nama Pegawai </div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Ahli_Pegawai</td>
      
    </tr>

#end
#if ($Ahli_Kategori == '2')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Nama Ahli</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="Ahli_Nama" type="text" id="Ahli_Nama" value="$!Ahli_Nama" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat /></td>
    </tr>
        
            <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Alamat</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <textarea name="txtAlamatAhli" cols="41" id="txtAlamatAhli" onblur="this.value=this.value.toUpperCase()">$!txtAlamatAhli</textarea>
            </td>
    </tr>
#end
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Jawatan</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Ahli_Jawatan" type="text" id="Ahli_Jawatan" value="$!Ahli_Jawatan" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">E-mail</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Ahli_Email" type="text" id="Ahli_Email" value="$!Ahli_Email" size="50" maxlength="255" $RO_MaklumatMesyuarat />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">No Telefon</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="Ahli_NoTelefon" type="text" id="Ahli_NoTelefon" value="$!Ahli_NoTelefon" size="50" maxlength="14" $RO_MaklumatMesyuarat /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">No Faks</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="Ahli_NoFaks" type="text" id="Ahli_NoFaks" value="$!Ahli_NoFaks" size="50" maxlength="14" $RO_MaklumatMesyuarat /></td>
    </tr>
#if ($Ahli_Kategori != '3')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Peranan</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        $!Ahli_Peranan      </td>
    </tr>
#end
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">
#if ($hideSaveButton != 'true')      
        <input type="button" id="cmdTambahAhli" name="cmdTambahAhli" value="Simpan" onclick="tambahAhli('$Ahli_Kategori');" />
#end        
        <input type="button" id="cmdKosongkanAhli" name="cmdKosongkanAhli" value="Kosongkan" onclick="kosongkanAhli();" />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><span style="font-style:italic">Sila pastikan medan bertanda <span class="mandatori">*</span> diisi</span></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input type="button" id="cmdPrev" name="cmdPrev" value=" Kembali" onclick="daftarMesyuarat();" />
        <input type="button" id="cmdNext" name="cmdNext" value="Seterusnya" onclick="daftarMinit();" />      </td>
    </tr>
    <tr>
      <td colspan="3" nowrap="nowrap"><div align="left"></div></td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI AHLI MESYUARAT</strong></legend>
  <br />
  <table width="100%" align="center">
  <tr>
  <td colspan="5">
  <input type="button" id="cmdKosongkanAhli" name="cmdKosongkanAhli" value="Tambah Ahli" onclick="kosongkanAhli();" />
  </td>
  </tr>
    <tr class="table_header">
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="15%" valign="top" nowrap="nowrap">Peranan</td>
      <td width="40%" valign="top" nowrap="nowrap">Nama Ahli</td>
      <td width="30%" valign="top" nowrap="nowrap">Seksyen / Unit / Agensi</td>
      <td width="10%" align="center" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    #if($List_CarianAhliMesyuarat.size()>0)
#set ($fail = '')
#foreach ($fail in $List_CarianAhliMesyuarat)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
    <tr>
      <td class="$row" style="text-align:center">$fail.No</td>
      <td class="$row">$fail.Peranan</td>
       <td class="$row">
    #if ($fail.No != '')
    <a href="javascript:viewAhli('$fail.IDAhli')" style="color:#0000FF">$fail.NamaAhli</a>
    
    #else
     <a href="javascript:viewAhli('$fail.IDAhli')" style="color:#0000FF">$fail.NamaAhli</a>
  
    #end
    </td>
      <td class="$row">$fail.SeksyenUnitAgensi</td>
      <td class="$row" style="text-align:center">
	#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdHapusAhli" name="cmdHapusAhli" value="Hapus" onclick="hapusAhli('$fail.IDAhli');" />
	#end        
      </td>
    </tr>
#end

#else
    <tr>
      <td colspan="5" nowrap="nowrap">Tiada Rekod</td>
    </tr>
    
    #end
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI URUSETIA</strong></legend>
  <br />
  <table width="100%" align="center">
    <tr class="table_header">
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
    
      <td width="40%" valign="top" nowrap="nowrap">Nama Ahli</td>
      <td width="30%" valign="top" nowrap="nowrap">Seksyen / Unit</td>
      <td width="10%" align="center" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    #if($List_CarianUrusetia.size()>0)
#set ($fail = '')
#foreach ($fail in $List_CarianUrusetia)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
    <tr>
      <td class="$row" style="text-align:center">$fail.No</td>
   <td class="$row">
    #if ($fail.No != '')
      
      <a href="javascript:viewAhli('$fail.IDAhli')" style="color:#0000FF">$fail.NamaAhli</a>
     
    #else
      <a href="javascript:viewAhli('$fail.IDAhli')" style="color:#0000FF">$fail.NamaAhli</a>
    #end
    </td>
      <td class="$row">$fail.SeksyenUnitAgensi</td>
      <td class="$row" style="text-align:center">
	#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdHapusAhli" name="cmdHapusAhli" value="Hapus" onclick="hapusAhli('$fail.IDAhli');" />
	#end        
      </td>
    </tr>
#end
#else
    <tr>
      <td colspan="4" nowrap="nowrap">Tiada Rekod</td>
    </tr>
    #end
  </table>
</fieldset>
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="ID_AHLI" name="ID_AHLI" value="$ID_AHLI" />
<input type="hidden" id="PrevPage" name="PrevPage" value="2" />
<input type="hidden" id="actionx" name="actionx" value="$actionx" />
<script type="text/javascript">
  function tambahAhli(Ahli_Kategori) {
 // alert("Ahli_Kategori" + Ahli_Kategori);
//Ahli_Kategori
	if (Ahli_Kategori == '1') {
//			alert("toggle 1");
//			alert("from toggle 3");
			if (document.${formName}.SOC_NEGERI.value == '' &&  document.${formName}.SOC_SEKSYEN.value == '') {
				alert('Sila Seksyen atau Negeri.');
				document.${formName}.Ahli_Nama.focus();
				return;
			}
			if (document.${formName}.SOC_PEGAWAI.value == '') {
				alert('Sila pilih nama pegawai dalaman.');
				document.${formName}.SOC_PEGAWAI.focus();
				return;
			}
			if (document.${formName}.SOC_PERANAN.value == '') {
				alert('Sila pilih peranan.');
				document.${formName}.Ahli_Nama.focus();
				return;
			 }
	}else if (Ahli_Kategori == '2') {
//			alert(" toggle 2 from toggle 1");

			if (document.${formName}.Ahli_Agensi.value == '') {
				alert('Sila masukkan Agensi bagi ahli luaran ini.');
				document.${formName}.Ahli_Agensi.focus();
				return;
			}

			if (document.${formName}.Ahli_Nama.value == '') {
				alert('Sila masukkan nama ahli luaran ini.');
				document.${formName}.Ahli_Nama.focus();
				return;
			}

			if (document.${formName}.SOC_PERANAN.value == '') {
				alert('Sila pilih peranan.');
				document.${formName}.Ahli_Nama.focus();
				return;
			}

	}else if (Ahli_Kategori == '3') {
//			alert("toggle 3 from toggle 1");
			if (document.${formName}.SOC_SEKSYEN.value == '') {
   	
				if (document.${formName}.SOC_NEGERI.value == '') {
					alert('Sila pilih seksyen bagi ahli dalaman ini.');
					document.${formName}.SOC_SEKSYEN.focus();
					return;
   	    	}

			} else {
				
				if (document.${formName}.SOC_NEGERI.value != '') {
					alert('Sila pilih sama ada seksyen atau negeri bagi ahli dalaman ini.');
					document.${formName}.SOC_SEKSYEN.focus();
				return;
				}
			}

			if (document.${formName}.SOC_PEGAWAI.value == '') {
				alert('Sila pilih nama pegawai dalaman.');
				document.${formName}.SOC_PEGAWAI.focus();
				return;
			}

	}
                  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=tambahAhli";
                  document.${formName}.submit();
//
//} 
  }

  function kosongkanAhli() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarAhli";
	  document.${formName}.submit();
  }
  function viewAhli(ID_Ahli) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=viewAhli&ID_AHLI=" + ID_Ahli;
	  document.${formName}.submit();
  }
  function daftarMesyuarat() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarMesyuarat";
	  document.${formName}.submit();
  }
  function daftarMinit() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarMinit";
	  document.${formName}.submit();
  }
  function hapusAhli(ID_Ahli) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=hapusAhli&ID_AHLI=" + ID_Ahli;
	  document.${formName}.submit();
  }
  function cetakMesyuarat() {
		var url = "../servlet/ekptg.report.pfd.PFDReport?reportType=PDF&TYPE=4&MINITMESYUARAT_ID=$ID_MESYUARAT";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
  }
  function toggleKategori(submitType) {
      document.${formName}.action.value = "$actionx";
      doAjaxCall${formName}(submitType);
  }
  function doChangeSOC(submitType) {
	// alert(submitType)
      document.${formName}.action.value = "$actionx";
      doAjaxCall${formName}(submitType);
  }
</script>