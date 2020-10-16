<fieldset>
<legend><strong>Carian</strong></legend>
<table width="100%" border="0">
$selectNegeri
$selectUnit 
<!-- 
  <tr>
    <td width="29%"><div align="right">Negeri</div></td>
    <td width="1%">:</td>
    <td width="70%">$selectNegeri</td>
  </tr>
  <tr>
    <td><div align="right">Unit</div></td>
    <td>:</td>
    <td>$selectUnit</td>
  </tr> -->
  <tr>
    <td width="30%"><div align="right">Daerah :</div></td>
    
    <td width="70%">&nbsp;&nbsp;$selectDaerah</td>
  </tr>
  <tr>

    <td><div align="right">Tarikh Permohonan Online:</div></td>
    <td>&nbsp;<label>
      			<input name="txdMula" type="text" id="txdMula" value="$!txdMula" size="10"onblur="check_date(this);" />
        	</label>
        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>

        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;
     		<label>
      			<input name="txdAkhir" type="text" id="txdAkhir" value="$!txdAkhir" size="10" onblur="check_date(this);"/>
        	</label>
        	<a href="javascript:displayDatePicker('txdAkhir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    </tr>
    <tr>
    
    <tr>
      <td><div align="right">Seksyen :</div></td>
     
      <td>&nbsp;
      
						     <select  class="autoselect" name="seksyen" id="seksyen" >
						     #if($listseksyen == '0')
						   	 	<option value="0" selected >SILA PILIH</option>
						   	 #else
						   	 	<option value="0" >SILA PILIH</option>
						   	 #end
						   	 
						   	 #if($listseksyen == '8')
						   	  	<option value="8" selected>Seksyen 8</option>
						   	 #else
						   	  	<option value="8">Seksyen 8</option>
						   	 #end
						   	 
						   	 #if($listseksyen == '17')
						   	 	<option value="17" selected >Seksyen 17</option>
						   	 #else
						   	 	<option value="17" >Seksyen 17</option>
						   	 #end
						   	 
						   	 #if($listseksyen == '817')
						   	 	<option value="817" selected>Seksyen 8 dan 17</option>
						   	 #else
						   	 	<option value="817" >Seksyen 8 dan 17</option>
						   	 #end
						   		
							</select>
      </td>
    </tr>

    <td><div align="right">No. Fail Operasi : </div></td>
    #if($nofailoperasi == '1')
    	#set ($nilaicheck = 'checked')
    #else
    	#set ($nilaicheck = '')
  	#end
     
    <td>&nbsp;<input type="checkbox" name="nofailoperasi" value="1" $nilaicheck></td>
  </tr>
  </tr>
  <tr>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
    <tr>

    <td>&nbsp;</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="cari()" >
    <input name="cmdKosongkan" id="cmdKosongkan" value="Reset" type="reset" onClick="javascript:kosongkan()"></td>
  </tr>
</table>
</fieldset>
<br>
<fieldset>
<legend><strong>Senarai Permohonan Online</strong></legend>
#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
  <tr class="table_header">
    <td width="5%"><strong>No</strong></td>
    <td><strong>No Permohonan Online</strong></td>
    <td><strong>Nama Simati</strong></td>
    <td width="15%"><strong>Tarikh Mohon</strong></td>
    <td><strong>No Fail</strong></td>
  </tr>
  #foreach ($fail in $SenaraiPermohonan)
  
  #if ($fail.bil == '') 
  #set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  #set ($row = 'row1')
  #else 
  #set ($row = 'row2')
  #end
  <tr>
    <td class="$row">$fail.bil</td>
    <td class="$row">$fail.NO_PERMOHONAN_ONLINE</td>
    <td class="$row">$fail.NAMA_SIMATI</td>
    <td class="$row">$fail.TARIKH_MOHON</td>
    <td class="$row">$fail.NO_FAIL</td>
  </tr>
  #end
</table>
</fieldset>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeUnit() {
	doAjaxCall${formName}("doChangeUnit");
}
function cari() {

if((document.${formName}.txdMula.value != "") && (document.${formName}.txdAkhir.value == ""))
	{
		alert('Sila masukkan tarikh.');
  		document.${formName}.txdAkhir.focus(); 
		return; 
	}
if((document.${formName}.txdAkhir.value != "") && (document.${formName}.txdMula.value == ""))
		{
		alert('Sila masukkan tarikh.');
  		document.${formName}.txdMula.focus(); 
		return; 
		}

		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanOnlineSek8";
		document.${formName}.submit();

		
	

	
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txdMula.value = "";
	document.${formName}.txdAkhir.value = "";
	document.${formName}.socNegeri.value = "";
	document.${formName}.socUnit.value = "";
	document.${formName}.nofailoperasi.checked = false;
	//socUnit
	//document.${formName}.submit();
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanOnlineSek8";
	document.${formName}.method="POST";
	//	document.${formName}.action.value = "";
	document.${formName}.submit();
}
</script>