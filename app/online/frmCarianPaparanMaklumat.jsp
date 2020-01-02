<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())
<input name="action" type="hidden" value="$action" />
<input name="semakan" type="hidden" value="$semakan" />
<input name="idHakmilik" type="hidden" value="" />
<input name="idPermohonan" type="hidden" value="" />
<input name="noHakmilik" type="hidden" value="" />
<input name="noWarta" type="hidden" value="" />
<input name="noSiriPerjanjian" type="hidden" value="" />
<input name="noSiriLesen" type="hidden" value="" />
<fieldset>
<legend><strong>::Carian::</strong></legend>
<table width="100%" border="0" cellpadding="0">
  <tr>
    <td width="29%" align="right">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><label>
      <select name="socSeksyen" id="socSeksyen" onChange="change()">
        <option $selected0>Sila Pilih Seksyen</option>
        <option $selected1 value="1">Seksyen Penguatkuasa Dan Hasil Persekutuan</option>
        <option $selected2 value="2">Seksyen Harta Tanah Persekutuan</option>
      </select>
    </label></td>
  </tr>
   #if($hide != 0)
  <tr>
    <td align="right">Negeri</td>
    <td>:</td>
    <td>$selectNegeri</td>
  </tr>
  <tr>
    <td align="right">Daerah</td>
    <td>:</td>
    <td>$selectDaerah</td>
  </tr>
  <tr>
    <td align="right">Bandar / Pekan / Mukim</td>
    <td>:</td>
    <td>$selectMukim</td>
  </tr>
  	#if($hide == 2)
  <tr>
    <td align="right">No Hakmilik</td>
    <td>:</td>
    <td><label>
      <input name="txtNoHakmilik" type="text" id="txtNoHakmilik" value="$txtNoHakmilik">
    </label></td>
  </tr>
  <tr>
    <td align="right">No Warta Rizab</td>
    <td>:</td>
    <td><label>
      <input name="txtNoWartaRizab" type="text" id="txtNoWartaRizab" value="$txtNoWarta">
    </label></td>
  </tr>
  #end
  #if($hide == 1)
  <tr>
    <td align="right">No Siri Perjanjian</td>
    <td>:</td>
    <td><label>
      <input name="txtNoSiriPerjanjian" type="text" id="txtNoSiriPerjanjian" value="$txtNoSiriPerjanjian">
    </label></td>
  </tr>
  <tr>
    <td align="right">No Siri Lesen</td>
    <td>:</td>
    <td><label>
      <input name="txtNoLesen" type="text" id="txtNoLesen" value="$txtNoLesen">
    </label></td>
  </tr>
  	#end
  
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input type="submit" name="cmdCari" id="cmdCari" value="Cari">
    </label>
      <label>
      <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()">
    </label></td>
  </tr>
</table>
</fieldset>
#if($hide == 2)
<fieldset>
<legend><strong>::Senarai Maklumat::</strong></legend>
<table width="100%" border="0" cellpadding="0">
  <tr>
    <td colspan="3">&nbsp;</td>
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
  <tr class = "table_header">
    <td width="1%"><strong>No</strong></td>
    <td width="15%"><strong>Negeri</strong></td>
    <td width="15%"><strong>Daerah</strong></td>
    <td width="39%"><strong>Bandar / Pekan / Mukim</strong></td>
    <td width="15%"><strong>No Hakmilik</strong></td>
    <td width="15%"><strong>No Warta Rizab</strong></td>
  </tr>
   #foreach ($htp in $Senarai)
  #if ($htp.bil == '') 
  	#set ($row = 'row1')
  #elseif ($htp.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td>$htp.bil</td>
    #if ($htp.bil != '')
    <td><a href="javascript:paparMaklumatHTP('$htp.idHakmilik','$semakan')" class="style1 style1">$htp.namaNegeri</a></td>
    #else
    <td>$htp.namaNegeri</td>
    #end
    <td>$htp.namaDaerah</td>
    <td>$htp.namaMukim</td>
    <td>$htp.noHakmilik</td>
    <td>$htp.noWarta</td>
  </tr>
  #end
</table>
</fieldset>
#end
#if($hide == 1)
<fieldset>
<legend><strong>::Senarai Maklumat::</strong></legend>
<table width="100%" border="0" cellpadding="0">
  <tr>
    <td colspan="3">&nbsp;</td>
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
    <td width="1%"><strong>No</strong></td>
    <td width="15%"><strong>Negeri</strong></td>
    <td width="15%"><strong>Daerah</strong></td>
    <td width="39%"><strong>Bandar / Pekan / Mukim</strong></td>
    <td width="15%"><strong>No Siri Perjanjian</strong></td>
    <td width="15%"><strong>No Siri Lesen</strong></td>
  </tr>
   #foreach ($php in $Senarai)
  #if ($php.bil == '') 
  	#set ($row = 'row1')
  #elseif ($php.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td>$php.bil</td>
    <td><a href="javascript:paparMaklumatPHP('$php.id_Permohonan','$semakan')" class="style1 style1">$php.namaNegeri</a></td>
    <td>$php.namaDaerah</td>
    <td>$php.namaMukim</td>
    <td>$php.noSiriPerjanjian</td>
    <td>$php.noSiriLesen</td>
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
function paparMaklumatHTP(id,semakan){
	
	document.${formName}.action.value = "paparHTP";
	document.${formName}.idHakmilik.value = id;
	document.${formName}.semakan.value = semakan;
	
	if (document.${formName}.txtNoHakmilik.value != ''){
		document.${formName}.noHakmilik.value = document.${formName}.txtNoHakmilik.value;
	}
	else if (document.${formName}.txtNoWartaRizab.value != ''){
		document.${formName}.noWarta.value = document.${formName}.txtNoWartaRizab.value;
	}
	document.${formName}.submit();
	

}
function paparMaklumatPHP(id,semakan){
	
	document.${formName}.action.value = "paparPHP";
	document.${formName}.idPermohonan.value = id;
	document.${formName}.semakan.value = semakan;
	
	if (document.${formName}.txtNoSiriPerjanjian.value != ''){
		document.${formName}.noSiriPerjanjian.value = document.${formName}.txtNoSiriPerjanjian.value;
	}
	else if (document.${formName}.txtNoLesen.value != ''){
		document.${formName}.noSiriLesen.value = document.${formName}.txtNoLesen.value;
	}
	document.${formName}.submit();
	

}
function seterusnya(){    	
	document.${formName}.action.value = "next";
	document.${formName}.submit();
}
function sebelumnya(){    	
	document.${formName}.action.value = "previous";
	document.${formName}.submit();
}
function kosongkan(){
	
	document.${formName}.socNegeri.value == "";
	document.${formName}.socDaerah.value == "";
	document.${formName}.socMukim.value == "";
	
	if (document.${formName}.semakan.value == "1"){
		document.${formName}.reset();
		document.${formName}.txtNoSiriPerjanjian.value = "";
		document.${formName}.txtNoLesen.value = "";
	}
	else if(document.${formName}.semakan.value == "2"){
		document.${formName}.reset();
		document.${formName}.txtNoHakmilik.value = "";
		document.${formName}.txtNoWartaRizab.value = "";
		
	
	}

}

</script>
