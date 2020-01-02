#if ($pagemode == "viewCukai" || $ResultSimpan == "kemaskini" )
#foreach ( $kemaskini in $cukaikemaskini )
	#set ($idNegeri = $kemaskini.idNegeri)
	#set ($idDaerah = $kemaskini.idDaerah)
	#set ($idMukim = $kemaskini.idMukim)
	#set ($idJenisHakmilik = $kemaskini.idJenisHakmilik)
	#set ($noHakmilik = $kemaskini.noHakmilik)
	#set ($noFail = $kemaskini.noFail)
	#set ($noFailPTG = $kemaskini.noFailPTG)
	#set ($kegunaanTanah = $kemaskini.kegunaanTanah)
	#set ($caraPerolehi = $kemaskini.caraPerolehi)
	#set ($noFailPTD = $kemaskini.noFailPTD)
	#set ($pembayarCukai = $kemaskini.pembayarCukai)
	#set ($tarikhDaftar = $kemaskini.tarikhDaftar)
	#set ($Luas = $kemaskini.Luas)
	#set ($cukaiTerkini = $kemaskini.cukaiTerkini)
	#set ($cukaiTaliAir = $kemaskini.cukaiTaliAir)
	#set ($cukaiParit = $kemaskini.cukaiParit)
	#set ($Denda = $kemaskini.Denda)
	#set ($bayaranLain = $kemaskini.bayaranLain)
	#set ($NoLot = $kemaskini.NoLot)
	#set ($Cukai = $kemaskini.Cukai)
	#set ($pengecualian = $kemaskini.pengecualian)
	#set ($pengurangan = $kemaskini.pengurangan)
	#set ($tunggakkan = $kemaskini.tunggakkan)
	#set ($lebihan = $kemaskini.lebihan)
	#set ($statusBayaran = $kemaskini.statusBayaran)
    #set ($tMasuk = $kemaskini.tMasuk)
#end
<fieldset>
<legend>KEMASKINI CUKAI</legend>
<br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Fail anda telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Fail anda telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>
#end
<br>
<!--<form name="f1" method="post"> -->
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="36%">
        <table width="100%" border="0">
<tr>
                <td width="40%" valign="top"><div align="right"><strong>1Negeri </strong></div></td>
                <td width="1%" valign="top">:</td>
            	<td width="59%" valign="top">$selectNegeri<input type="hidden" name="idNegeri" value="$idNegeri" /></td>
       	  </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Daerah </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">$selectDaerah <input type="hidden" name="idDaerah" value="$idDaerah" /></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Bandar/Pekan/Mukim </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">$selectMukim <input type="hidden" name="idMukim" value="$idMukim" /></td>
              </tr>
        </table></td>
<td align="center" valign="top" width="64%"><table width="100%" border="0">
              <tr>
                <td valign="top" width="31%"><div align="right"><strong>Jenis Hakmilik </strong></div></td>
                <td valign="top">:</td>
            	<td valign="top" width="69%">$selectJenisHakmilik <input type="hidden" name="idJenisHakmilik" value="$idJenisHakmilik" /></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>No. Hakmilik </strong></div></td>
            <td valign="top">:</td>
            <td><table width="100%" border="0">
              <tr>
                <td width="40%"><label>
                  <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$noHakmilik" readonly="readonly" disabled="disabled" $mode />
                </label></td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td><div align="right"></div></td>
            <td>&nbsp;</td>
              </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"><hr size="2" width="100%"></td>
      </tr>
      <tr>
        <td height="103"><table width="100%" border="0">
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Kementerian</strong></div></td>
                <td valign="top">:</td>
                <td valign="top" width="59%">$selectKementerian</td>
          </tr>
              <tr>
                <td valign="top"><div align="right"><strong>No. Fail Seksyen</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtNoFailSek" size="40" value="$noFail" readonly="readonly" disabled="disabled" /></td>
            </tr>
              <tr>
                <td valign="top"><div align="right"><strong>No. Fail PTG</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtNoFailPTG" size="40" value="$noFailPTG" readonly="readonly" disabled="disabled" /></td>
          </tr>
              <tr>
                <td valign="top"><div align="right"><strong>Kegunaan Tanah</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtKegunaanTanah" size="40" value="$kegunaanTanah" readonly="readonly" disabled="disabled"/></td>
              </tr>
        </table></td>
        <td><table width="100%" border="0">
          <tr>
            <td valign="top"><div align="right"><strong>Agensi</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td valign="top">$selectAgensi</td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Cara Perolehi</strong></div></td>
            <td valign="top">:</td>
            <td valign="top" width="68%"><input type="text" name="txtCaraPerolehi" size="40" value="$caraPerolehi" readonly="readonly" disabled="disabled"/></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><strong>No. Fail PTD</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtNoFailLain" id="txtNoFailPTD" size="40" value="$noFailPTD" readonly="readonly" disabled="disabled"></td>
          </tr>
              <tr>
                <td valign="top"><div align="right"><strong>Pembayar Cukai</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtPembayarCukai" size="40" value="$pembayarCukai" readonly="readonly" disabled="disabled"/></td>
              </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"><hr size="2" width="100%"></td>
      </tr>
      <tr>
      	<td height="221"><table width="100%" border="0">
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Tarikh Daftar </strong></div></td>
            <td width="2%" valign="top">:</td>
            <td width="58%" valign="top"><input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" size="15" value="$tarikhDaftar" readonly="readonly" disabled="disabled">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhDaftar',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Unit / Luas </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">$selectLuas</td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Luas Bersamaan</strong></div></td>
            <td valign="top">:</td>
            <td valign="top"><label>
              <input type="text" name="txtLuas" id="txtLuas" maxlength="40" onkeyup="validateCurrency(this,this.value);" value="$Luas" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Cukai Terkini </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM <label>
      <input type="text" name="txtCukaiTerkini" id="txtCukaiTerkini" maxlength="16" size="17" onblur="validateCurrency(this,this.value);" value="$cukaiTerkini" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Cukai Tali Air </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM <label>
      <input type="text" name="txtCukaiTaliAir" id="txtCukaiTaliAir" maxlength="16" size="17" onblur="validateCurrency(this,this.value);" value="$cukaiTaliAir" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Cukai Parit </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM <label>
      <input type="text" name="txtCukaiParit" id="txtCukaiParit" maxlength="16" size="17" onblur="validateCurrency(this,this.value);" value="$cukaiParit" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Denda</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtDenda" id="txtDenda" maxlength="60" onblur="validateCurrency(this,this.value);" value="$Denda" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Bayaran Lain</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtBayaranLain" id="txtBayaranLain" maxlength="60" onblur="validateCurrency(this,this.value);" value="$bayaranLain" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
        </table>
        </td>
      <td valign="top"><table width="100%" height="220" border="0">
          <tr>
            <td width="31%"><div align="right"><strong>Kod Lot/No. </strong></div></td>
            <td>:</td>
            <td width="69%">
            	<table width="100%" border="0">
              	<tr>
                <td valign="top"><label>
                  <input type="text" name="txtNoLot" id="txtNoLot" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" value="$NoLot" readonly="readonly" disabled="disabled" $mode>
                </label></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;$selectLot</td>
          </tr>
          <tr>
       	 <td valign="top"><div align="right"><strong>Cukai Tahun Pertama </strong></div></td>
         <td valign="top">:</td>
         <td valign="top">RM 
              <label>
              <input type="text" name="txtCukaiPertama" id="txtCukaiPertama" maxlength="9" size="17" onblur="validateCurrency(this,this.value);" value="$Cukai" readonly="readonly" disabled="disabled" $mode />
              </label></td>    
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Pengecualian</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtPengecualian" id="txtPengecualian" maxlength="60" onblur="validateCurrency(this,this.value);" value="$pengecualian" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Pengurangan</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtPengurangan" id="txtPengurangan" maxlength="60" onblur="validateCurrency(this,this.value);" value="$pengurangan" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Tunggakkan</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtTunggakkan" id="txtTunggakkan" maxlength="60" onblur="validateCurrency(this,this.value);" value="$tunggakkan" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Lebihan</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtLebihan" id="txtLebihan" maxlength="60" onblur="validateCurrency(this,this.value);" value="$lebihan" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Status Bayaran</strong></div></td>
            <td valign="top">:</td>
            <td valign="top"><label>
              <input type="text" name="txtStatusBayaran" id="txtStatusBayaran" maxlength="60" onblur="this.value=this.value.toUpperCase();" value="$statusBayaran" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          
        </table>
        </td>
      </tr>
      <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input class=button type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;<input class=button type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()"></td>
      </tr>
      
    </tbody>
  </table>  
  <input type="hidden" name="idKementerian" value="$idKementerian" />
  <input type="hidden" name="idjenishakmilik" value="$idjenishakmilik">
  <input type="hidden" name="tMasuk" value="$tMasuk" />
  <input type="hidden" name="idHakmilikCukai" value="$idHakmilikCukai" />
  <input type="hidden" name="idHakmilik" value="$idHakmilik">
  <input type="hidden" name="noHakmilik" value="$noHakmilik">
  <input type="hidden" name="command1" value="$command1">
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
<!--</form>-->
</fieldset>

#elseif ($pagemode == "editCukai")
#foreach ( $kemaskini in $cukaikemaskini )
	#set ($idNegeri = $kemaskini.idNegeri)
	#set ($idDaerah = $kemaskini.idDaerah)
	#set ($idMukim = $kemaskini.idMukim)
	#set ($idJenisHakmilik = $kemaskini.idJenisHakmilik)
	#set ($noHakmilik = $kemaskini.noHakmilik)
	#set ($noFail = $kemaskini.noFail)
	#set ($noFailPTG = $kemaskini.noFailPTG)
	#set ($kegunaanTanah = $kemaskini.kegunaanTanah)
	#set ($caraPerolehi = $kemaskini.caraPerolehi)
	#set ($noFailPTD = $kemaskini.noFailPTD)
	#set ($pembayarCukai = $kemaskini.pembayarCukai)
	#set ($tarikhDaftar = $kemaskini.tarikhDaftar)
	#set ($Luas = $kemaskini.Luas)
	#set ($cukaiTerkini = $kemaskini.cukaiTerkini)
	#set ($cukaiTaliAir = $kemaskini.cukaiTaliAir)
	#set ($cukaiParit = $kemaskini.cukaiParit)
	#set ($Denda = $kemaskini.Denda)
	#set ($bayaranLain = $kemaskini.bayaranLain)
	#set ($NoLot = $kemaskini.NoLot)
	#set ($Cukai = $kemaskini.Cukai)
	#set ($pengecualian = $kemaskini.pengecualian)
	#set ($pengurangan = $kemaskini.pengurangan)
	#set ($tunggakkan = $kemaskini.tunggakkan)
	#set ($lebihan = $kemaskini.lebihan)
	#set ($statusBayaran = $kemaskini.statusBayaran)
    #set ($tMasuk = $kemaskini.tMasuk)
#end
<fieldset>
<legend>KEMASKINI CUKAI</legend>
<br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Fail anda telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Fail anda telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>
#end
<br>
<!--<form name="f1" method="post"> -->
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="36%">
        <table width="100%" border="0">
<tr>
                <td width="40%" valign="top"><div align="right"><strong>1Negeri </strong></div></td>
                <td width="1%" valign="top">:</td>
            	<td width="59%" valign="top">$selectNegeri<input type="hidden" name="idNegeri" value="$idNegeri" /></td>
       	  </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Daerah </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">$selectDaerah <input type="hidden" name="idDaerah" value="$idDaerah" /></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Bandar/Pekan/Mukim </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">$selectMukim <input type="hidden" name="idMukim" value="$idMukim" /></td>
              </tr>
        </table></td>
<td align="center" valign="top" width="64%"><table width="100%" border="0">
              <tr>
                <td valign="top" width="31%"><div align="right"><strong>Jenis Hakmilik </strong></div></td>
                <td valign="top">:</td>
            	<td valign="top" width="69%">$selectJenisHakmilik <input type="hidden" name="idJenisHakmilik" value="$idJenisHakmilik" /></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>No. Hakmilik </strong></div></td>
            <td valign="top">:</td>
            <td><table width="100%" border="0">
              <tr>
                <td width="40%"><label>
                  <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$noHakmilik" readonly="readonly" disabled="disabled" $mode />
                </label></td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td><div align="right"></div></td>
            <td>&nbsp;</td>
              </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"><hr size="2" width="100%"></td>
      </tr>
      <tr>
        <td height="103"><table width="100%" border="0">
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Kementerian</strong></div></td>
                <td valign="top">:</td>
                <td valign="top" width="59%">$selectKementerian</td>
          </tr>
              <tr>
                <td valign="top"><div align="right"><strong>No. Fail Seksyen</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtNoFailSek" size="40" value="$noFail" readonly="readonly" disabled="disabled" /></td>
            </tr>
              <tr>
                <td valign="top"><div align="right"><strong>No. Fail PTG</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtNoFailPTG" size="40" value="$noFailPTG" readonly="readonly" disabled="disabled" /></td>
          </tr>
              <tr>
                <td valign="top"><div align="right"><strong>Kegunaan Tanah</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtKegunaanTanah" size="40" value="$kegunaanTanah" readonly="readonly" disabled="disabled"/></td>
              </tr>
        </table></td>
        <td><table width="100%" border="0">
          <tr>
            <td valign="top"><div align="right"><strong>Agensi</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td valign="top">$selectAgensi</td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Cara Perolehi</strong></div></td>
            <td valign="top">:</td>
            <td valign="top" width="68%"><input type="text" name="txtCaraPerolehi" size="40" value="$caraPerolehi" readonly="readonly" disabled="disabled"/></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><strong>No. Fail PTD</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtNoFailLain" id="txtNoFailPTD" size="40" value="$noFailPTD" readonly="readonly" disabled="disabled"></td>
          </tr>
              <tr>
                <td valign="top"><div align="right"><strong>Pembayar Cukai</strong></div></td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtPembayarCukai" size="40" value="$pembayarCukai" readonly="readonly" disabled="disabled"/></td>
              </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"><hr size="2" width="100%"></td>
      </tr>
      <tr>
      	<td height="221"><table width="100%" border="0">
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Tarikh Daftar </strong></div></td>
            <td width="2%" valign="top">:</td>
            <td width="58%" valign="top"><input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" size="15" value="$tarikhDaftar" readonly="readonly" disabled="disabled">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhDaftar',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Unit / Luas </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">$selectLuas</td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Luas Bersamaan</strong></div></td>
            <td valign="top">:</td>
            <td valign="top"><label>
              <input type="text" name="txtLuas" id="txtLuas" maxlength="40" onkeyup="validateCurrency(this,this.value);" value="$Luas" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong><font color="#FF0000">*</font>Cukai Terkini </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM <label>
      <input type="text" name="txtCukaiTerkini" id="txtCukaiTerkini" maxlength="16" size="17" onkeyup="validateNumber(this,this.value);" onblur="Check();validateCurrency(this,this.value);addText(this);" value="$cukaiTerkini" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Cukai Tali Air </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM <label>
      <input type="text" name="txtCukaiTaliAir" id="txtCukaiTaliAir" maxlength="16" size="17" onkeyup="validateNumber(this,this.value);" onblur="Check();validateCurrency(this,this.value);addText(this);" value="$cukaiTaliAir" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Cukai Parit </strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM <label>
      <input type="text" name="txtCukaiParit" id="txtCukaiParit" maxlength="16" size="17" onkeyup="validateNumber(this,this.value);" onblur="Check();validateCurrency(this,this.value);addText(this);" value="$cukaiParit" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Denda</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtDenda" id="txtDenda" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);addText(this);" value="$Denda" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Bayaran Lain</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtBayaranLain" id="txtBayaranLain" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);addText(this);" value="$bayaranLain" $mode>
            </label></td>
          </tr>
        </table>
        </td>
      <td valign="top"><table width="100%" height="220" border="0">
          <tr>
            <td width="31%"><div align="right"><strong>Kod Lot/No. </strong></div></td>
            <td>:</td>
            <td width="69%">
            	<table width="100%" border="0">
              	<tr>
                <td valign="top"><label>
                  <input type="text" name="txtNoLot" id="txtNoLot" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" value="$NoLot" readonly="readonly" disabled="disabled" $mode>
                </label></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;$selectLot</td>
          </tr>
          <tr>
       	 <td valign="top"><div align="right"><strong>Cukai Tahun Pertama </strong></div></td>
         <td valign="top">:</td>
         <td valign="top">RM 
              <label>
              <input type="text" name="txtCukaiPertama" id="txtCukaiPertama" maxlength="9" size="17" onblur="validateCurrency(this,this.value);" value="$Cukai" readonly="readonly" style="color:#CCCCCC" $mode />
              </label></td>    
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Pengecualian</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtPengecualian" id="txtPengecualian" maxlength="60" onblur="validateCurrency(this,this.value);" value="$pengecualian" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Pengurangan</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtPengurangan" id="txtPengurangan" maxlength="60" onblur="validateCurrency(this,this.value);" value="$pengurangan" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Tunggakkan</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtTunggakkan" id="txtTunggakkan" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);addText(this);" value="$tunggakkan" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Lebihan</strong></div></td>
            <td valign="top">:</td>
            <td valign="top">RM<label>
              <input type="text" name="txtLebihan" id="txtLebihan" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);addText(this);" value="$lebihan" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Status Bayaran</strong></div></td>
            <td valign="top">:</td>
            <td valign="top"><label>
              <input type="text" name="txtStatusBayaran" id="txtStatusBayaran" maxlength="60" onblur="this.value=this.value.toUpperCase();" value="$statusBayaran" $mode>
            </label></td>
          </tr>
          
        </table>
        </td>
      </tr>
      <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center">
			<input class=button type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;
			<input class=button type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="Batal()">&nbsp;
			<input class=button type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali1('$idHakmilikCukai')"></td>
      </tr>
      
    </tbody>
  </table>
  <input type="hidden" name="cukaiTerkini" value="$cukaiTerkini" />
  <input type="hidden" name="cukaiTaliAir" value="$cukaiTaliAir" />
  <input type="hidden" name="cukaiParit" value="$cukaiParit" />
  <input type="hidden" name="idHakmilikCukai" value="$idHakmilikCukai" />
  <input type="hidden" name="idHC" value="$idHakmilikCukai" />
  <input type="hidden" name="tMasuk" value="$tMasuk" />
  <input type="hidden" name="idKementerian" value="$idKementerian" />
  <input type="hidden" name="idjenishakmilik" value="$idjenishakmilik">
  <input type="hidden" name="idHakmilik" value="$idHakmilik">
  <input type="hidden" name="noHakmilik" value="$noHakmilik">
  <input type="hidden" name="command1" value="$command1">
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
<!--</form>-->
</fieldset>
#end
<script>
function validateNumber(elmnt,content) {
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function validateCurrency(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function addText(field) {
	if(field.value==""){
		field.defaultValue="";}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890.";
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
function Kembali1() {
	document.${formName}.action = "";
	document.${formName}.command1.value = "kemaskiniCukai";
	document.${formName}.pagemode.value = "viewCukai";
	document.${formName}.submit();
}
function Kembali() {
	document.${formName}.action = "";
	document.${formName}.command1.value = "";
	document.${formName}.submit();
}
function Batal() {
	document.${formName}.action = "";
	document.${formName}.command1.value = "";
	document.${formName}.submit();
}
function Kemaskini() {
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "editCukai";
	document.${formName}.command1.value = "kemaskiniCukai";
	document.${formName}.submit();
}
function Check() {
	var a = parseFloat(document.${formName}.txtCukaiTerkini.value);
	var b = parseFloat(document.${formName}.txtCukaiTaliAir.value);
	var c = parseFloat(document.${formName}.txtCukaiParit.value);
	var x = parseFloat(document.${formName}.cukaiTerkini.value);
	var y = parseFloat(document.${formName}.cukaiTaliAir.value);
	var z = parseFloat(document.${formName}.cukaiParit.value);
	
	if(a > x || a < x ){
  		document.${formName}.idHakmilikCukai.value = "0";
	}
	if(b > y || b < y ){
  		document.${formName}.idHakmilikCukai.value = "0";
	}
	if(c > z || c < z ){
  		document.${formName}.idHakmilikCukai.value = "0";
	}
}
function Simpan() {

	if(document.${formName}.txtCukaiTerkini.value == "0.00"){
		alert('Sila masukkan " Jumlah Cukai Terkini " terlebih dahulu.');
  		document.${formName}.txtCukaiTerkini.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.command1.value = "kemaskiniCukai";
	document.${formName}.pagemode.value = "simpan";
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>
