#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())

<input name="checkedRadio" type="hidden" value="$checkedRadio">
<input name="action" type="hidden" value="$action" />
<input name="semakan" type="hidden" value="$semakan" />
<fieldset>
<legend><strong>::Carian::</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><label>
      <select name="socSeksyen" id="socSeksyen"  onChange="change()">
        <option value $selected0 "0">Sila Pilih Seksyen</option>
        <option $selected1 value="1">Seksyen Pembahagian Pusaka Kecil</option>
        <option $selected2 value="2">Seksyen Pengambilan Tanah</option>
        <option $selected3 value="3">Seksyen Penguatkuasa Dan Hasil Persekutuan </option>
        <option $selected4 value="4">Seksyen Harta Tanah Persekutuan</option>
      </select>
    </label></td>
  </tr>
  #if($hide != 0)
  	#if($hide == 1)
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
    <input type="radio" name="radio" id="sorNoFail" value="sorNoFail" onClick="checked1()" $radioChecked1/>
    No Fail 
    <input type="radio" name="radio" id="sorNoRujOnline" value="sorNoRujOnline" onClick="checked2()" $radioChecked2/>
    No Permohonan Online 
    <input type="radio" name="radio" id="sorNoKPPemohon" value="sorNoKPPemohon" onClick="checked3()" $radioChecked3/>
    No KP Pemohon 
    <input type="radio" name="radio" id="sorNoSijilMati" value="sorNoSijilMati" onClick="checked4()" $radioChecked4/>
    No Sijil Mati</label>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input name="txtCarianPPK" type="text" id="txtCarianPPK" value="$txtCarianPPK" size="44" onblur="this.value=this.value.toUpperCase()"/>
    </label></td>
  </tr>
  #end
  #if($hide == 2)
  <tr>
    <td align="right">No Permohonan Online</td>
    <td>:</td>
    <td><label>
      <input name="txtNoPermohonanOnline" type="text" id="txtNoPermohonanOnline" value="$txtNoPermohonanOnline" size="44"  onblur="this.value=this.value.toUpperCase()"/>
    </label></td>
  </tr>
  #end
   #if($hide == 2 || $hide == 3 || $hide == 4)
  <tr>
    <td align="right">No Fail</td>
    <td>:</td>
    <td><label>
      <input name="txtNoFail" type="text" id="txtNoFail" value="$txtNoFail" size="44"  onblur="this.value=this.value.toUpperCase()" />
    </label></td>
  </tr>
  #end
  #if($hide == 3)
  <tr>
    <td align="right">Nama Pemohon</td>
    <td>:</td>
    <td><label>
      <input name="txtNamaPemohon" type="text" id="txtNamaPemohon" size="44"  onblur="this.value=this.value.toUpperCase()" />
    </label></td>
  </tr>
  <tr>
    <td align="right">No KP Pemohon</td>
    <td>:</td>
    <td><label>
      <input name="txtNoKP" type="text" id="txtNoKP" size="44"  onblur="this.value=this.value.toUpperCase()"/>
    </label></td>
  </tr>
  #end
  #if($hide == 4)
  <tr>
    <td align="right">No Hakmilik</td>
    <td>:</td>
    <td><label>
      <input name="txtNoHakmilik" type="text" id="txtNoHakmilik"  onblur="this.value=this.value.toUpperCase()" value="$txtNoHakmilik" size="44"/>
    </label></td>
  </tr>
  #end
  #if($hide == 2)
  <tr>
    <td align="right">Tarikh Permohonan</td>
    <td>:</td>
    <td><label>
      <input name="txdTarikhPermohonan" type="text" id="txdTarikhPermohonan" value="$txdTarikhPermohonan" size="10" />
    </label><a href="javascript:displayDatePicker('txdTarikhPermohonan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  #end
  #if($hide != 1)
  <tr>
    <td align="right">Status Fail</td>
    <td>:</td>
    <td>$selectStatus</td>
  </tr>
  #end
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
    </label>
      <label>
      <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
    </label></td>
  </tr>
</table>
</fieldset>
#if($hide == 1)
<fieldset>
<legend><strong>::Senarai Permohonan bagi Seksyen Pembahagian Pusaka Kecil::</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td>&nbsp;</td>
    <td colspan="5" align="right">
     #if ( $i >= $Senarai.size())
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
      #else
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" disabled="disabled" align="right" />
      #end
 	  #if (($i < $total && $Senarai.size() != $total))
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
      #else
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" align="right" />
      #end    </td>
  </tr>
  <tr class="table_header">
    <td width="1%"><strong>No</strong></td>
    <td width="18%"><strong>No Fail</strong></td>
    <td width="21%"><strong>No Permohonan Online</strong></td>
    <td width="21%"><strong>No KP Pemohon</strong></td>
    <td width="19%"><strong>No Sijil Mati</strong></td>
    <td width="14%"><strong>Status Fail</strong></td>
  </tr>
  
  #foreach ($failPPK in $Senarai)
  #if ($failPPK.bil == '') 
  	#set ($row = 'row1')
  #elseif ($failPPK.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$failPPK.bil</td>
    <td class="$row">$failPPK.noFail</td>
     <td class="$row">$failPPK.noPermohonanOnline</td>
     <td class="$row">$failPPK.noKPPemohon</td>
     <td class="$row">$failPPK.noSijilMati</td>
    <td class="$row">$failPPK.keterangan</td>
  </tr>
  #end
</table>

</fieldset>
#end
#if($hide == 2)
<fieldset>
<legend><strong>::Senarai Permohonan bagi Seksyen Pengambilan Tanah::</strong></legend>

<table width="100%" border="0" cellpadding="2">
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3" align="right">
     #if ( $i >= $Senarai.size())
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
      #else
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" disabled="disabled" align="right" />
      #end
 	  #if (($i < $total && $Senarai.size() != $total))
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
      #else
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" align="right" />
      #end
    </td>
  </tr>
  <tr class="table_header">
    <td width="2%"><strong>No</strong></td>
     <td width="27%"><strong>No Fail</strong></td>
    <td width="20%"><strong>No Permohonan Online</strong></td>
    <td width="17%"><strong>Tarikh Permohonan</strong></td>
    <td width="12%"><strong>Status Fail</strong></td>
  </tr>
   #foreach ($failPPT in $Senarai)
  #if ($failPPT.bil == '') 
  	#set ($row = 'row1')
  #elseif ($failPPT.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$failPPT.bil</td>
    <td class="$row">$failPPT.noFail</td>
    <td class="$row">$failPPT.noPermohonanOnline</td>
    <td class="$row">$failPPT.tkhPermohonan</td>
    <td class="$row">$failPPT.keterangan</td>
  </tr>
  #end
</table>
</fieldset>
#end
#if($hide == 3)
<fieldset>
<legend><strong>::Senarai Permohonan bagi Seksyen Penguatkuasa Dan Hasil Persekutuan::</strong></legend>

<table width="100%" border="0" cellpadding="2">
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3" align="right">
     #if ( $i >= $Senarai.size())
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
      #else
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" disabled="disabled" align="right" />
      #end
 	  #if (($i < $total && $Senarai.size() != $total))
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
      #else
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" align="right" />
      #end    </td>
  </tr>
  <tr class="table_header">
    <td width="1%"><strong>No</strong></td>
    <td width="35%"><strong>No Fail</strong></td>
    <td width="21%"><strong>Nama Pemohon</strong></td>
    <td width="26%"><strong>No KP Pemohon</strong></td>
    <td width="16%"><strong>Status Fail</strong></td>
  </tr>
   #foreach ($failPHP in $Senarai)
  #if ($failPHP.bil == '') 
  	#set ($row = 'row1')
  #elseif ($failPHP.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$failPHP.bil</td>
    <td class="$row">$failPHP.noFail</td>
    <td class="$row">$failPHP.namaPemohon</td>
    <td class="$row">$failPHP.noKP</td>
    <td class="$row">$failPHP.keterangan</td>
  </tr>
  #end
</table>
</fieldset>
#end
#if($hide == 4)
<fieldset>
<legend><strong>::Senarai Permohonan bagi Seksyen Harta Tanah Persekutuan::</strong></legend>

<table width="100%" border="0" cellpadding="2">
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="2" align="right">
    #if ( $i >= $Senarai.size())
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
      #else
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" disabled="disabled" align="right" />
      #end
 	  #if (($i < $total && $Senarai.size() != $total))
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
      #else
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" align="right" />
      #end
    </td>
  </tr>
  <tr class="table_header">
    <td width="1%"><strong>No</strong></td>
    <td width="29%"><strong>No Fail</strong></td>
    <td width="38%"><strong>No Hakmilik</strong></td>
    <td width="22%"><strong>Status Fail</strong></td>
  </tr>
  #foreach ($failHTP in $Senarai)
  #if ($failHTP.bil == '') 
  	#set ($row = 'row1')
  #elseif ($failHTP.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$failHTP.bil</td>
    <td class="$row">$failHTP.noFail</td>
    <td class="$row">$failHTP.noHakmilik</td>
    <td class="$row">$failHTP.keterangan</td>
  </tr>
  #end
</table>

</fieldset>
#end
#end
<script>
function change(){
	document.${formName}.submit();
}
function checked1(){
	document.${formName}.checkedRadio.value = "noFail";
	document.${formName}.submit();
}
function checked2(){
	document.${formName}.checkedRadio.value = "noPermohonanOnline";
	document.${formName}.submit();
}
function checked3(){
	document.${formName}.checkedRadio.value = "noKPPemohon";
	document.${formName}.submit();
}
function checked4(){
	document.${formName}.checkedRadio.value = "noSijilMati";
	document.${formName}.submit();
}
function carian(){
	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function kosongkan(){

	if (document.${formName}.semakan.value == "1"){
		document.${formName}.reset();
		document.${formName}.txtCarianPPK.value = "";
	}
	else if(document.${formName}.semakan.value == "2"){
		document.${formName}.reset();
		document.${formName}.txtNoPermohonanOnline.value = "";
		document.${formName}.txtNoFail.value = "";
		document.${formName}.txdTarikhPermohonan.value = "";
		document.${formName}.socStatus.value = "";
	
	}
	else if (document.${formName}.semakan.value == "3"){
		document.${formName}.reset();
		document.${formName}.txtNoPermohonanOnline.value = "";
		document.${formName}.txtNoFail.value = "";
		document.${formName}.txtNamaPemohon.value = "";
		document.${formName}.txtNoKP.value = "";
		document.${formName}.socStatus.value = "";
	
	}
	else if (document.${formName}.semakan.value == "4"){
		document.${formName}.reset();
		document.${formName}.txtNoPermohonanOnline.value = "";
		document.${formName}.txtNoFail.value = "";
		document.${formName}.txtNoHakmilik.value = "";
		document.${formName}.socStatus.value = "";
	}
}
function seterusnya(){   
	document.${formName}.action.value = "next";
	document.${formName}.submit();
}
function sebelumnya(){  
	document.${formName}.action.value = "previous";
	document.${formName}.submit();
}
</script>