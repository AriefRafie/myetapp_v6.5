#if ($idCukaiTerperinci == "")
#foreach ( $pelarasan in $cukaipelarasan )
	#set ($idNegeri = $pelarasan.idNegeri)
	#set ($idDaerah = $pelarasan.idDaerah)
	#set ($idMukim = $pelarasan.idMukim)
	#set ($idJenisHakmilik = $pelarasan.idJenisHakmilik)
	#set ($noHakmilik = $pelarasan.noHakmilik)
	#set ($amaunResit = $pelarasan.amaunResit)
	#set ($noResit = $pelarasan.noResit)
	#set ($tarikhResit = $pelarasan.tarikhResit)    
	#set ($cukaiTahunan = $pelarasan.cukaiTahunan)
	#set ($cukaiParit = $pelarasan.cukaiParit)
	#set ($cukaiTaliAir = $pelarasan.cukaiTaliAir)
	#set ($pengurangan = $pelarasan.pengurangan)
    #set ($pengecualian = $pelarasan.pengecualian)
	#set ($catatan = $pelarasan.catatan)
	#set ($tahun = $pelarasan.tahun)
	#set ($denda = $pelarasan.denda)
	#set ($bayaranLain = $pelarasan.bayaranLain)
	#set ($tunggakan = $pelarasan.tunggakan)
	#set ($jumlahBayaran = $pelarasan.jumlahBayaran)
    #set ($bezaBayaran = $pelarasan.bezaBayaran)
#end  

<fieldset>
<legend>PELARASAN CUKAI</legend>
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
                <td width="40%" valign="top"><div align="right"><strong>Negeri </strong></div></td>
                <td width="1%" valign="top">:</td>
            	<td width="59%" valign="top">$selectNegeri
   		    <input type="hidden" name="idNegeri" value="$idNegeri" /></td>
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
            	<td valign="top" width="69%">$selectJenisHakmilik 
              <input type="hidden" name="idJenisHakmilik" value="$idJenisHakmilik" /></td>
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
      	<tbody>
    <tr>
      <td align="center" valign="top" width="50%">
        <table width="100%" border="0">
          <tr>
          <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Amaun Resit</strong></div></td>
          <td width="1%" valign="top">:</td>
          <td width="59%" valign="top">RM 
            <label>
            <input type="text" name="txtAmaunResit" id="txtAmaunResit" maxlength="16" size="17" onfocus="clearText(this);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$amaunResit"$mode>
            </label></td>      
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>No. Resit</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">
            <label>
            <input type="text" name="txtNoResit" id="txtNoResit" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$noResit" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Tarikh Resit</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top"><input type="text" name="txdTarikhResit" id="txdTarikhResit" size="15" value="$tarikhResit" readonly="readonly">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhResit',false,'dmy');" style="display"></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Cukai Tahunan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiTahunan" id="txtCukaiTahunan" maxlength="16" size="17" onfocus="clearText(this);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$cukaiTahunan" $mode>
            </label></td>
          </tr>
          <tr>
          <td width="40%" valign="top"><div align="right"><strong>Cukai Parit</strong></div></td>
          <td width="1%" valign="top">:</td>
          <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiParit" id="txtCukaiParit" maxlength="16" size="17" onfocus="clearText(this);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$cukaiParit" $mode>
            </label></td>  
          </tr>
          <tr>
           <td width="40%" valign="top"><div align="right"><strong>Cukai Tali Air</strong></div></td>
           <td width="1%" valign="top">:</td>
           <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiTaliAir" id="txtCukaiTaliAir" maxlength="16" size="17" onfocus="clearText(this);" onblur="Jumlah(this,this.value);validateCurrency(this,this.value);addText(this);" value="$cukaiTaliAir" $mode>
            </label></td> 
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Pengurangan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM<label>
              <input type="text" name="txtPengurangan" id="txtPengurangan" maxlength="60" onblur="validateCurrency(this,this.value);" value="$pengurangan" readonly="readonly" style="color:#CCCCCC" $mode>
            </label></td>
          </tr>
           <tr>
            <td width="40%" valign="top"><div align="right"><strong>Pengecualian</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM<label>
              <input type="text" name="txtPengecualian" id="txtPengecualian" maxlength="60" onblur="validateCurrency(this,this.value);" value="$pengecualian" readonly="readonly" style="color:#CCCCCC" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Catatan Perubahan</strong></div></td>
            <td width="1%" valign="top">:</td>
           	<td width="59%" valign="top"><label>
              <textarea name="txtCatatan" id="txtCatatan" cols="45" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc>$catatan</textarea>
            </label></td>
          </tr>
        </table></td>
        
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
            <tr>
            <td width="31%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Tahun</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top"><label>
              <input type="text" name="txtTahun" id="txtTahun" maxlength="60" value="$tahun" $mode>
            </label></td>
          </tr>
          <tr>
       	 	<td width="31%" valign="top"><div align="right"><strong>Perbezaan Bayaran</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM 
              <label>
              <input type="text" name="txtBezaBayaran" id="txtBezaBayaran" maxlength="16" size="19" onblur="validateCurrency(this,this.value);" value="$bezaBayaran"$mode readonly="readonly" style="color:#CCCCCC" />
            </label></td>    
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Denda</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtDenda" id="txtDenda" maxlength="60" onfocus="clearText(this);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$denda" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Bayaran Lain</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtBayaranLain" id="txtBayaranLain" maxlength="60" onfocus="clearText(this);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$bayaranLain" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Tunggakan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtTunggakan" id="txtTunggakan" maxlength="60" onblur="validateCurrency(this,this.value);" value="$tunggakan" readonly="readonly" style="color:#CCCCCC" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Jumlah Bayaran</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM 
              <label>
              <input type="text" name="txtJumlahBayaran" id="txtJumlahBayaran" maxlength="16" size="19" onchange="validateCurrency(this,this.value);" value="$jumlahBayaran" readonly="readonly" style="color:#CCCCCC" $mode>
            </label></td>
            
          </tr>
          <tr>
            <td height="27" valign="top">&nbsp;</td>
            <td><label></label></td>
            </tr>
        </table></td>
     </tr>
    </tbody>
      </tr>
      <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input class=button type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Simpan()" style="display:none">&nbsp;<input class=button type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;<input class=button type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()" style="display:none">&nbsp;<input class=button type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()" >&nbsp;<input class=button type="reset" value="Kosongkan" name="cmdBatal" id="cmdBatal" onclick="Batal()" >&nbsp;<input class=button type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()" style="display:none"></td>
      </tr>
    </tbody>
  </table> 
  <input type="hidden" name="idCukaiTerperinci" value="$idCukaiTerperinci" />
  <input type="hidden" name="idKementerian" value="$idKementerian" />
  <input type="hidden" name="idjenishakmilik" value="$idjenishakmilik">
  <input type="hidden" name="idHakmilikCukai" value="$idHakmilikCukai" />
  <input type="hidden" name="idHakmilik" value="$idHakmilik">
  <input type="hidden" name="noHakmilik" value="$noHakmilik">
  <input type="hidden" name="command1" value="$command1">
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

</fieldset>
#elseif ($pagemode == "editCukai")
#foreach ( $pelarasan in $cukaipelarasan )
	#set ($idNegeri = $pelarasan.idNegeri)
	#set ($idDaerah = $pelarasan.idDaerah)
	#set ($idMukim = $pelarasan.idMukim)
	#set ($idJenisHakmilik = $pelarasan.idJenisHakmilik)
	#set ($noHakmilik = $pelarasan.noHakmilik)
	#set ($amaunResit = $pelarasan.amaunResit)
	#set ($noResit = $pelarasan.noResit)
	#set ($tarikhResit = $pelarasan.tarikhResit)    
	#set ($cukaiTahunan = $pelarasan.cukaiTahunan)
	#set ($cukaiParit = $pelarasan.cukaiParit)
	#set ($cukaiTaliAir = $pelarasan.cukaiTaliAir)
	#set ($pengurangan = $pelarasan.pengurangan)
    #set ($pengecualian = $pelarasan.pengecualian)
	#set ($catatan = $pelarasan.catatan)
	#set ($tahun = $pelarasan.tahun)
	#set ($denda = $pelarasan.denda)
	#set ($bayaranLain = $pelarasan.bayaranLain)
	#set ($tunggakan = $pelarasan.tunggakan)
	#set ($jumlahBayaran = $pelarasan.jumlahBayaran)
    #set ($bezaBayaran = $pelarasan.bezaBayaran)
#end  

<fieldset>
<legend>PELARASAN CUKAI </legend>
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
                <td width="40%" valign="top"><div align="right"><strong>Negeri </strong></div></td>
                <td width="1%" valign="top">:</td>
            	<td width="59%" valign="top">$selectNegeri
   		    <input type="hidden" name="idNegeri" value="$idNegeri" /></td>
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
            	<td valign="top" width="69%">$selectJenisHakmilik 
              <input type="hidden" name="idJenisHakmilik" value="$idJenisHakmilik" /></td>
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
      	<tbody>
    <tr>
      <td align="center" valign="top" width="50%">
        <table width="100%" border="0">
          <tr>
          <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Amaun Resit</strong></div></td>
          <td width="1%" valign="top">:</td>
          <td width="59%" valign="top">RM 
            <label>
            <input type="text" name="txtAmaunResit" id="txtAmaunResit" maxlength="16" size="17" onkeyup="validateNumber(this,this.value);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$amaunResit" $mode>
            </label></td>      
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>No. Resit</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">
            <label>
            <input type="text" name="txtNoResit" id="txtNoResit" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$noResit" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Tarikh Resit</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top"><input type="text" name="txdTarikhResit" id="txdTarikhResit" size="15" value="$tarikhResit" readonly="readonly">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhResit',false,'dmy');" style="display"></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Cukai Tahunan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiTahunan" id="txtCukaiTahunan" maxlength="16" size="17" onkeyup="validateNumber(this,this.value);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$cukaiTahunan" $mode>
            </label></td>
          </tr>
          <tr>
          <td width="40%" valign="top"><div align="right"><strong>Cukai Parit</strong></div></td>
          <td width="1%" valign="top">:</td>
          <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiParit" id="txtCukaiParit" maxlength="16" size="17" onkeyup="validateNumber(this,this.value);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$cukaiParit" $mode>
            </label></td>  
          </tr>
          <tr>
           <td width="40%" valign="top"><div align="right"><strong>Cukai Tali Air</strong></div></td>
           <td width="1%" valign="top">:</td>
           <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiTaliAir" id="txtCukaiTaliAir" maxlength="16" size="17" onkeyup="validateNumber(this,this.value);" onblur="Jumlah(this,this.value);validateCurrency(this,this.value);addText(this);" value="$cukaiTaliAir" $mode>
            </label></td> 
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Pengurangan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM<label>
              <input type="text" name="txtPengurangan" id="txtPengurangan" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);" value="$pengurangan" readonly="readonly" style="color:#CCCCCC" $mode>
            </label></td>
          </tr>
           <tr>
            <td width="40%" valign="top"><div align="right"><strong>Pengecualian</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM<label>
              <input type="text" name="txtPengecualian" id="txtPengecualian" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);" value="$pengecualian" readonly="readonly" style="color:#CCCCCC" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Catatan Perubahan</strong></div></td>
            <td width="1%" valign="top">:</td>
           	<td width="59%" valign="top"><label>
              <textarea name="txtCatatan" id="txtCatatan" cols="45" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc>$catatan</textarea>
            </label></td>
          </tr>
        </table></td>
        
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
            <tr>
            <td width="31%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Tahun</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top"><label>
              <input type="text" name="txtTahun" id="txtTahun" maxlength="60" value="$tahun" $mode>
            </label></td>
          </tr>
          <tr>
       	 	<td width="31%" valign="top"><div align="right"><strong>Perbezaan Bayaran</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM 
              <label>
              <input type="text" name="txtBezaBayaran" id="txtBezaBayaran" maxlength="16" size="19" onblur="validateCurrency(this,this.value);" value="$bezaBayaran"$mode readonly="readonly" style="color:#CCCCCC" />
            </label></td>    
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Denda</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtDenda" id="txtDenda" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$denda" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Bayaran Lain</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtBayaranLain" id="txtBayaranLain" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$bayaranLain" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Tunggakan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtTunggakan" id="txtTunggakan" maxlength="60" onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);" value="$tunggakan" readonly="readonly" style="color:#CCCCCC" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Jumlah Bayaran</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM 
              <label>
              <input type="text" name="txtJumlahBayaran" id="txtJumlahBayaran" maxlength="16" size="19" onblur="validateCurrency(this,this.value);" value="$jumlahBayaran" readonly="readonly" style="color:#CCCCCC" $mode>
            </label></td>
            
          </tr>
          <tr>
            <td height="27" valign="top">&nbsp;</td>
            <td><label></label></td>
            </tr>
        </table></td>
     </tr>
    </tbody>
      </tr>
      <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input class=button type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Simpan()" style="display:none">&nbsp;<input class=button type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;<input class=button type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()" style="display:none">&nbsp;<input class=button type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali1()" >&nbsp;<input class=button type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()" style="display:none"></td>
      </tr>
    </tbody>
  </table> 
  <input type="hidden" name="idCukaiTerperinci" value="$idCukaiTerperinci" />
  <input type="hidden" name="idKementerian" value="$idKementerian" />
  <input type="hidden" name="idjenishakmilik" value="$idjenishakmilik">
  <input type="hidden" name="idHakmilikCukai" value="$idHakmilikCukai" />
  <input type="hidden" name="idHakmilik" value="$idHakmilik">
  <input type="hidden" name="noHakmilik" value="$noHakmilik">
  <input type="hidden" name="command1" value="$command1">
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

</fieldset>
#elseif ($pagemode == "viewCukai" || $SimpanStatus == "success" )
#foreach ( $pelarasan in $cukaipelarasan )
	#set ($idNegeri = $pelarasan.idNegeri)
	#set ($idDaerah = $pelarasan.idDaerah)
	#set ($idMukim = $pelarasan.idMukim)
	#set ($idJenisHakmilik = $pelarasan.idJenisHakmilik)
	#set ($noHakmilik = $pelarasan.noHakmilik)
	#set ($amaunResit = $pelarasan.amaunResit)
	#set ($noResit = $pelarasan.noResit)
	#set ($tarikhResit = $pelarasan.tarikhResit)    
	#set ($cukaiTahunan = $pelarasan.cukaiTahunan)
	#set ($cukaiParit = $pelarasan.cukaiParit)
	#set ($cukaiTaliAir = $pelarasan.cukaiTaliAir)
	#set ($pengurangan = $pelarasan.pengurangan)
    #set ($pengecualian = $pelarasan.pengecualian)
	#set ($catatan = $pelarasan.catatan)
	#set ($tahun = $pelarasan.tahun)
	#set ($denda = $pelarasan.denda)
	#set ($bayaranLain = $pelarasan.bayaranLain)
	#set ($tunggakan = $pelarasan.tunggakan)
	#set ($jumlahBayaran = $pelarasan.jumlahBayaran)
    #set ($bezaBayaran = $pelarasan.bezaBayaran)
#end  

<fieldset>
<legend>PELARASAN CUKAI</legend>
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
                <td width="40%" valign="top"><div align="right"><strong>Negeri </strong></div></td>
                <td width="1%" valign="top">:</td>
            	<td width="59%" valign="top">$selectNegeri
   		    <input type="hidden" name="idNegeri" value="$idNegeri" /></td>
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
            	<td valign="top" width="69%">$selectJenisHakmilik 
              <input type="hidden" name="idJenisHakmilik" value="$idJenisHakmilik" /></td>
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
      	<tbody>
    <tr>
      <td align="center" valign="top" width="50%">
        <table width="100%" border="0">
          <tr>
          <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Amaun Resit</strong></div></td>
          <td width="1%" valign="top">:</td>
          <td width="59%" valign="top">RM 
            <label>
            <input type="text" name="txtAmaunResit" id="txtAmaunResit" maxlength="16" size="17" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$amaunResit" disabled="disabled" $mode>
            </label></td>      
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>No. Resit</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">
            <label>
            <input type="text" name="txtNoResit" id="txtNoResit" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$noResit" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Tarikh Resit</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top"><input type="text" name="txdTarikhResit" id="txdTarikhResit" size="15" value="$tarikhResit" readonly="readonly" disabled="disabled">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhResit',false,'dmy');" style="display:none"></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Cukai Tahunan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiTahunan" id="txtCukaiTahunan" maxlength="16" size="17" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$cukaiTahunan" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
          <td width="40%" valign="top"><div align="right"><strong>Cukai Parit</strong></div></td>
          <td width="1%" valign="top">:</td>
          <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiParit" id="txtCukaiParit" maxlength="16" size="17" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$cukaiParit" disabled="disabled" $mode>
            </label></td>  
          </tr>
          <tr>
           <td width="40%" valign="top"><div align="right"><strong>Cukai Tali Air</strong></div></td>
           <td width="1%" valign="top">:</td>
           <td width="59%" valign="top">RM 
              <label>
              <input type="text" name="txtCukaiTaliAir" id="txtCukaiTaliAir" maxlength="16" size="17" onblur="Jumlah(this,this.value);validateCurrency(this,this.value);addText(this);" value="$cukaiTaliAir" disabled="disabled" $mode>
            </label></td> 
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Pengurangan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM<label>
              <input type="text" name="txtPengurangan" id="txtPengurangan" maxlength="60" onblur="validateCurrency(this,this.value);" value="$pengurangan" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
           <tr>
            <td width="40%" valign="top"><div align="right"><strong>Pengecualian</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="59%" valign="top">RM<label>
              <input type="text" name="txtPengecualian" id="txtPengecualian" maxlength="60" onblur="validateCurrency(this,this.value);" value="$pengecualian" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="40%" valign="top"><div align="right"><strong>Catatan Perubahan</strong></div></td>
            <td width="1%" valign="top">:</td>
           	<td width="59%" valign="top"><label>
              <textarea name="txtCatatan" id="txtCatatan" cols="45" rows="3" onkeyup="this.value=this.value.toUpperCase();" disabled="disabled" $modeSoc>$catatan</textarea>
            </label></td>
          </tr>
        </table></td>
        
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
            <tr>
            <td width="31%" valign="top"><div align="right"><strong><font color="#FF0000">*</font>Tahun</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top"><label>
              <input type="text" name="txtTahun" id="txtTahun" maxlength="60" value="$tahun" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
       	 	<td width="31%" valign="top"><div align="right"><strong>Perbezaan Bayaran</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM 
              <label>
              <input type="text" name="txtBezaBayaran" id="txtBezaBayaran" maxlength="16" size="19" onblur="validateCurrency(this,this.value);" value="$bezaBayaran"$mode readonly="readonly" disabled="disabled" />
            </label></td>    
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Denda</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtDenda" id="txtDenda" maxlength="60" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$denda" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Bayaran Lain</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtBayaranLain" id="txtBayaranLain" maxlength="60" onblur="Jumlah();validateCurrency(this,this.value);addText(this);" value="$bayaranLain" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Tunggakan</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM<label>
              <input type="text" name="txtTunggakan" id="txtTunggakan" maxlength="60" onblur="validateCurrency(this,this.value);" value="$tunggakan" readonly="readonly" disabled="disabled" $mode>
            </label></td>
          </tr>
          <tr>
            <td width="31%" valign="top"><div align="right"><strong>Jumlah Bayaran</strong></div></td>
            <td width="1%" valign="top">:</td>
            <td width="68%" valign="top">RM 
              <label>
              <input type="text" name="txtJumlahBayaran" id="txtJumlahBayaran" maxlength="16" size="19" onblur="validateCurrency(this,this.value);" value="$jumlahBayaran" readonly="readonly" disabled="disabled" $mode>
            </label></td>
            
          </tr>
          <tr>
            <td height="27" valign="top">&nbsp;</td>
            <td><label></label></td>
            </tr>
        </table></td>
     </tr>
    </tbody>
      </tr>
      <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input class=button type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;<input class=button type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()" style="display:none">&nbsp;<input class=button type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()" style="display:none">&nbsp;<input class=button type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()" >&nbsp;<input class=button type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()" style="display:none"></td>
      </tr>
    </tbody>
  </table> 
  <input type="hidden" name="idCukaiTerperinci" value="$idCukaiTerperinci" />
  <input type="hidden" name="idKementerian" value="$idKementerian" />
  <input type="hidden" name="idjenishakmilik" value="$idjenishakmilik">
  <input type="hidden" name="idHakmilikCukai" value="$idHakmilikCukai" />
  <input type="hidden" name="idHakmilik" value="$idHakmilik">
  <input type="hidden" name="noHakmilik" value="$noHakmilik">
  <input type="hidden" name="command1" value="$command1">
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

</fieldset>
#end


<script>
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
function validateNumber(elmnt,content) {
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function clearText(field) {
	if(field.defaultValue==field.value){
		field.value="";}
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
function Jumlah() {
	
	var a = parseFloat(document.${formName}.txtCukaiTahunan.value);
	var b = parseFloat(document.${formName}.txtCukaiParit.value);
	var c = parseFloat(document.${formName}.txtCukaiTaliAir.value);
	var d = parseFloat(document.${formName}.txtDenda.value);
	var e = parseFloat(document.${formName}.txtBayaranLain.value);
	
	if (document.${formName}.txtCukaiTahunan.value != ""){
		document.${formName}.txtJumlahBayaran.value = a.toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiParit.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + b).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtCukaiTaliAir.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + b + c).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtDenda.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + b + d).toFixed(2);}	
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + b + e).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtDenda.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + b + c + d).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + b + c + e).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + b + d + e).toFixed(2);}	
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtCukaiParit.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c + b).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtDenda.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c + d).toFixed(2);}	
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c + e).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtDenda.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c + b + d).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c + b + e).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtCukaiParit.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c + d + b).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c + d + e).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + c + b + d + e).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtDenda.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + d).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtCukaiParit.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + d + b).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtCukaiTaliAir.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + d + c).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + d + e).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtCukaiParit.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + d + c + b).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + d + c + e).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + d + b + c + e).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtBayaranLain.value != "" && document.${formName}.txtCukaiTaliAir.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + d + b + e + c).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + e).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtBayaranLain.value != "" && document.${formName}.txtCukaiParit.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + e + b).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtBayaranLain.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtDenda.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + e + b + d).toFixed(2);}
	
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtBayaranLain.value != "" && document.${formName}.txtCukaiTaliAir.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + e + c).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtBayaranLain.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtCukaiParit.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + e + c + b).toFixed(2);}
		
	if (document.${formName}.txtCukaiTahunan.value != "" && document.${formName}.txtCukaiParit.value != "" && document.${formName}.txtCukaiTaliAir.value != "" && document.${formName}.txtDenda.value != "" && document.${formName}.txtBayaranLain.value != ""){
		document.${formName}.txtJumlahBayaran.value = (a + b + c + d + e).toFixed(2);}
		
	var y = parseFloat(document.${formName}.txtAmaunResit.value);
	var z = parseFloat(document.${formName}.txtJumlahBayaran.value);
	
	if (y < z){
		document.${formName}.txtPengurangan.value = (z-y).toFixed(2);
		document.${formName}.txtBezaBayaran.value = (z-y).toFixed(2);
		document.${formName}.txtTunggakan.value = (z-y).toFixed(2);
		document.${formName}.txtPengecualian.value = "0.00";}
	if (z < y){
		document.${formName}.txtPengecualian.value = (y-z).toFixed(2);
		document.${formName}.txtBezaBayaran.value = (y-z).toFixed(2);
		document.${formName}.txtTunggakan.value = "0.00";
		document.${formName}.txtPengurangan.value = "0.00";}
	if (y==z){
		document.${formName}.txtPengecualian.value = "0.00";
		document.${formName}.txtPengurangan.value = "0.00";
		document.${formName}.txtBezaBayaran.value = "0.00";
		document.${formName}.txtTunggakan.value = "0.00";}
		
}
function Kembali() {
	//if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "";
	document.${formName}.command.value = ""
	document.${formName}.submit();
}
function Kembali1() {
	//if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "viewCukai";
	document.${formName}.command.value = "pelarasanCukai"
	document.${formName}.submit();
}
function Kemaskini() {
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "editCukai";
	document.${formName}.command.value = "pelarasanCukai"
	document.${formName}.submit();
}
function Batal() {
	document.${formName}.reset();
	document.${formName}.command.value = ""
	document.${formName}.txtAmaunResit.focus();
	document.${formName}.action = "";
}
function Simpan() {
	if(document.${formName}.txtAmaunResit.value == "0.00"){
		alert('Sila masukkan " Amaun Resit " terlebih dahulu.');
  		document.${formName}.txtAmaunResit.focus(); 
		return; 
	}
	if(document.${formName}.txtNoResit.value == ""){
		alert('Sila masukkan " No Resit " terlebih dahulu.');
  		document.${formName}.txtNoResit.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhResit.value == "DD/MM/YYYY"){
		alert('Sila masukkan " Tarikh Resit " terlebih dahulu.');
  		document.${formName}.txdTarikhResit.focus(); 
		return; 
	}
	if(document.${formName}.txtCukaiTahunan.value == "0.00"){
		alert('Sila masukkan " Amaun Cukai Tahunan " terlebih dahulu.');
  		document.${formName}.txtCukaiTahunan.focus(); 
		return; 
	}
	if(document.${formName}.txtTahun.value == ""){
		alert('Sila masukkan " Tahun Cukai " terlebih dahulu.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "simpanPelarasan";
	document.${formName}.command1.value = "pelarasanCukai"
	document.${formName}.submit();
}
//document.${formName}.cmdKemaskini.style.display = document.${formName}.style2.value;
//document.${formName}.cmdSimpan.style.display = document.${formName}.style1.value;

</script>
