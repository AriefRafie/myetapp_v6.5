#set($txtCatatan="500")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanDredge in $BeanDredge)
  <tr>
    <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Beroperasi </td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><select name="socOperasi" id="socOperasi" style="width:100px;" $readonly class="$inputTextClass" $inputTextClass onChange="doChangeBertindih()">
        
        
                
        	     #if($idOperasi=='1')
                   
        
        
        <option value="">SILA PILIH</option>
        <option value="1" selected="selected">1 - YA</option>
        <option value="2">2 - TIDAK</option>
        
        
        
                #elseif ($idOperasi=='2')
                   
        
        
        <option value="">SILA PILIH</option>
        <option value="1">1 - YA</option>
        <option value="2" selected="selected">2 - TIDAK</option>
        
        
                      
                #else ($idOperasi=='')
                   
        
        
        <option value="" selected="selected">SILA PILIH</option>
        <option value="1">1 - YA</option>
        <option value="2">2 - TIDAK</option>
        
        
        
                #end                    
             
      
      
      </select>
    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Tarikh Terima Laporan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><input name="txtTarikhTerimaLaporan" type="text" class="$inputTextClass" id="txtTarikhTerimaLaporan" onBlur="check_date(this);" value="$beanDredge.tarikhDredge" size="9" maxlength="10" $readonly />
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTerimaLaporan',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">No Rujukan</td>
    <td valign="top">:</td>
    <td valign="top"><input type="text" name="txtNoRujukan" id="txtNoRujukan" value="$!beanDredge.txtNoRujukan" size="43" class="$inputTextClass" $readonly  /></td>
  </tr>
  <tr>
    <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtCatatan" class="$inputTextClass"  $readonly cols="40" rows="5" id="txtCatatan" onkeyup="textCounter(this.form.txtCatatan,this.form.remLen3,$!txtCatatan);" onkeydown="textCounter(this.form.txtCatatan,this.form.remLen3,$!txtCatatan);">$!beanDredge.txtCatatan</textarea></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">Baki Aksara :&nbsp;
      <input type="text" readonly="readonly" class="disabled" name="remLen3" size="3" maxlength="3" value="$!txtCatatan" /></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
      <!--<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>-->
      #if($idStatus =='1610239')
      <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      #end       
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniDredge()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end </td>
  </tr>
  #end
</table>
<script>
function doKemaskini(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doChangeBertindih() {
	doAjaxCall${formName}("doChangeBertindih");	
}
function doSimpanKemaskiniDredge(){
	if(document.${formName}.txtTarikhTerimaLaporan.value == ""){
		alert('Sila masukkan Tarikh Terima Laporan.');
  		document.${formName}.txtTarikhTerimaLaporan.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniDredge";
	document.${formName}.submit();
}

function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
</script>
