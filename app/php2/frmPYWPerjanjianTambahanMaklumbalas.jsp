<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach($beanMaklumatMaklumbalas in $BeanMaklumatMaklumbalas)
  <tr>
    <td colspan="2"><fieldset>
      <legend>PENERIMAAN CAGARAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%">Tarikh Terima</td>
          <td width="70%">:
            <input name="txtTarikhTerimaCagaran" type="text" class="$inputTextClass" id="txtTarikhTerimaCagaran" onBlur="check_date(this);" value="$beanMaklumatMaklumbalas.tarikhTerimaCagaran" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTerimaCagaran',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>No Rujukan</td>
          <td>:
            <input type="text" name="txtNoRujukanCagaran" id="txtNoRujukanCagaran" value="$beanMaklumatMaklumbalas.noRujukanCagaran" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/></td>
        </tr>
        <tr>
          <td>Status</td>
          <td>:
            <select name="socCagaran" id="socCagaran" style="width:140px;" $readonly class="$disabled" $disabled>
              
                        #if ($beanMaklumatMaklumbalas.flagCagaran == 'L')
          				  
              <option>SILA PILIH</option>
              <option value="L" selected="selected">TERIMA</option>
              <option value="T">TOLAK</option>
              
                        #elseif ($beanMaklumatMaklumbalas.flagCagaran == 'T')
          				  
              <option>SILA PILIH</option>
              <option value="L">TERIMA</option>
              <option value="T" selected="selected">TOLAK</option>
              
                        #else
          				  
              <option selected="selected">SILA PILIH</option>
              <option value="L">TERIMA</option>
              <option value="T">TOLAK</option>
              
                       	#end
          				
            </select></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>TANDATANGAN PERJANJIAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%">Tarikh Terima</td>
          <td width="70%">:
            <input name="txtTarikhTerimaTandatangan" type="text" class="$inputTextClass" id="txtTarikhTerimaTandatangan" onBlur="check_date(this);" value="$beanMaklumatMaklumbalas.tarikhTerimaTandatangan" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTerimaTandatangan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>No Rujukan</td>
          <td>:
            <input type="text" name="txtNoRujukanTandatangan" id="txtNoRujukanTandatangan" value="$beanMaklumatMaklumbalas.noRujukanTandatangan" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/></td>
        </tr>
        <tr>
          <td>Status</td>
          <td>:
            <select name="socTandatangan" id="socTandatangan" style="width:140px;" $readonly class="$disabled" $disabled>
              
                        #if ($beanMaklumatMaklumbalas.flagTandatangan == 'L')
          				  
              <option>SILA PILIH</option>
              <option value="L" selected="selected">TERIMA</option>
              <option value="T">TOLAK</option>
              
                        #elseif ($beanMaklumatMaklumbalas.flagTandatangan == 'T')
          				  
              <option>SILA PILIH</option>
              <option value="L">TERIMA</option>
              <option value="T" selected="selected">TOLAK</option>
              
                        #else
          				  
              <option selected="selected">SILA PILIH</option>
              <option value="L">TERIMA</option>
              <option value="T">TOLAK</option>
              
                        #end
          				
            </select></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>SETEM HASIL</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%">Tarikh Terima</td>
          <td width="70%">:
            <input name="txtTarikhTerimaMatiSetem" type="text" class="$inputTextClass" id="txtTarikhTerimaMatiSetem" onBlur="check_date(this);" value="$beanMaklumatMaklumbalas.tarikhTerimaMatiSetem" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTerimaMatiSetem',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>No Rujukan</td>
          <td>:
            <input type="text" name="txtNoRujukanMatiSetem" id="txtNoRujukanMatiSetem" value="$beanMaklumatMaklumbalas.noRujukanMatiSetem" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/></td>
        </tr>
        <tr>
          <td>Status</td>
          <td>:
            <select name="socMatiSetem" id="socMatiSetem" style="width:140px;" $readonly class="$disabled" $disabled>
              
                        #if ($beanMaklumatMaklumbalas.flagMatiSetem == 'L')
          				  
              <option>SILA PILIH</option>
              <option value="L" selected="selected">TERIMA</option>
              <option value="T">TOLAK</option>
              
                        #elseif ($beanMaklumatMaklumbalas.flagMatiSetem == 'T')
          				  
              <option>SILA PILIH</option>
              <option value="L">TERIMA</option>
              <option value="T" selected="selected">TOLAK</option>
              
                        #else
          				  
              <option selected="selected">SILA PILIH</option>
              <option value="L">TERIMA</option>
              <option value="T">TOLAK</option>
              
                        #end
          				
            </select></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()"/>
      #if ($idStatusPerjanjianTambahan == '1610214')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onClick="seterusnyaPA()"/>
      #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniMaklumbalas()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
      #end </td>
  </tr>
</table>

<script>
function simpanKemaskiniMaklumbalas() {
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumbalas";
	doAjaxCall${formName}("");
}
function seterusnyaPA(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnyaPA";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
</script>
