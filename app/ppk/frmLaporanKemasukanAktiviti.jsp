<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<input name="idNegeri" type="hidden" value="$idNegeri" />
<fieldset>
  <legend>Maklumat Kemasukan Aktiviti</legend>
  <fieldset>
    <table border="0" align="center" width="80%" >
      <tbody>
      
      #set ($txdBayar = "") 
      #set ($txdBayarTamat = "") 
      <!-- <tr>
      	<td scope="row" align="right">Negeri</td>
        <td>: </td>
        <td>$selectNegeri        </td>
      </tr>
       <tr>
      	<td scope="row" align="right">Unit</td>
        <td>: </td>
        <td>$selectUnit
        
        </td>
      </tr>
           <tr>
      	<td scope="row" align="right">Daerah</td>
        <td>: </td>
        <td>$selectDaerah        </td>
      </tr> 
      <tr>
      	<td scope="row" align="right">Pegawai</td>
        <td>: </td>
        <td>$selectPegawai        </td>
      </tr> -->
      
      <tr>
        <td width="23%" scope="row" align="right">&nbsp;Tarikh Mula Aktiviti</td>
        <td>:&nbsp;</td>
        <td><label>
            <input name="txdBayar" type="text" id="txdBayar" value="$txdBayar" size="10" />
          </label>
          <a href="javascript:displayDatePicker('txdBayar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
      </tr>
      <tr>
        <td width="23%" scope="row" align="right">&nbsp;Tarikh Tamat Aktiviti</td>
        <td>:&nbsp;</td>
        <td><label>
            <input name="txdBayarTamat" type="text" id="txdBayarTamat" value="$txdBayarTamat" size="10" />
          </label>
          <a href="javascript:displayDatePicker('txdBayarTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
      </tr>
      <tr>
        <td scope="row" align="right">&nbsp;Aktiviti</td>
        <td>:&nbsp;</td>
        <td width="81%"><textarea name="eventText" cols="90%" rows="4" id="eventText" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" onKeyDown="textCounter(this.form.eventText,this.form.remLen4,250);" onKeyUp="textCounter(this.form.eventText,this.form.remLen4,250);" ></textarea></td>
      </tr>
      <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
        <td valign="top"><input type="text" readonly class="disabled" name="remLen4" size="3" maxlength="3" value="250">
          Baki Aksara</td>
      </tr>
      
        <td> #if ( $session.getAttribute("_portal_role") == "root" || $session.getAttribute("_portal_role") == "admin" )
          Scope:</td>
        <td><select name="viewScope">
            <option value="public">Public</option>
            <option value="private">Private</option>
          </select>
          #else
          <input type="hidden" name="viewScope" value="private">
          #end
          <input name="eventId" type="hidden" value="0" size="30"></td>
      </tr>
      <tr>
        <td valign="top" align="center" colspan="3"><input name="btnsubmit" type="button" value="Simpan" onclick="tambah()">
          <input type="reset" value="Batal" >
          <input name="cmdKembali" type="button" value="Kembali" onclick = "kembali()"/></td>
      </tr>
        </tbody>
      
    </table>
  </fieldset>
  <fieldset>
    <legend>Senarai Kemasukan </legend>
    <table border="0" align="center" width="100%" >
      #set ($cnt = 0)
      <tr class="table_header">
        <td width="2%" align="center"><strong>##</strong></td>
        <td width="80%" align="center"><strong>Aktiviti</strong></td>
        <td width="12%" align="center"><strong>Tarikh Mula</strong></td>
        <td width="12%" align="center"><strong>Tarikh Tamat</strong></td>
      </tr>
      #foreach ( $senarai in $senaraiRekod )
      #if ($senarai.bil == '') 
      #set ($row = 'row1')
      #elseif ($senarai.bil % 2 != 0)
      #set ($row = 'row1')
      #else 
      #set ($row = 'row2')
      #end
      <tr>
        <td class="$row">$senarai.bil</td>
        #if($senarai.bil != '')
        <td class="$row" align="left">$senarai.event_text</td>
        #else
        <td class="$row" align="left">$senarai.event_text</td>
        #end
        <td class="$row" align="center">$senarai.event_datef</td>
        <td class="$row" align="center">$senarai.event_datef_end</td>
      </tr>
      #end
    </table>
  </fieldset>
</fieldset>
<input type="hidden" name="idlaporanbilfail" value="0">
<input type="hidden" name="command3">
<input name="userId" type="hidden" value="$userId" />

<!--</form>	--> 

<script>
function doPilihFail(id,fail) {
	document.${formName}.txtNoFail.value = fail;
	document.${formName}.idPermohonan.value = id;
	doAjaxCall${formName}("PilihFail");
}	

function searchData() {
	if(document.${formName}.txtNoFail.value==""){
		alert('Sila isi No Fail Fail terlebih dahulu');
		return;
		}
	doAjaxCall${formName}("Carian");
}	
	
function doEdit(id,event,tarikh,ruj) {
	document.${formName}.idlaporanbilfail.value = id;
	document.${formName}.eventText.value = event;
	document.${formName}.txdBayar.value = tarikh;
	document.${formName}.eventId.value = ruj;
	document.${formName}.btnsubmit.value = "Kemaskini";
}
		
function doDelete(id) {
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.idlaporanbilfail.value = id;
	doAjaxCall${formName}("Delete");
}
	
function doJenisBayaran() {
	if(document.${formName}.socJenisbayaran.value=="0")
		return;
	doAjaxCall${formName}("SelectJenisBayaran");
}

function tambah() {
	if(document.${formName}.txdBayar.value==""){
		alert("Sila pilih tarikh aktiviti terlebih dahulu");
		return;
	}	
	if(document.${formName}.eventText.value==""){
		alert("Sila masukkan maklumat aktiviti terlebih dahulu");
		return ; 	
	}
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmLaporanKemasukanAktiviti&action=simpan";
	document.${formName}.submit();
}
function kembali() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmLaporanKemasukanAktiviti&action=kembali";
	document.${formName}.submit();
}



//add by rosli 2009/12/14
<!-- Begin
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}

function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
// End -->
function doChangeUnit() {
	
	doAjaxCall${formName}("doChangeUnit");
}
function doChangePegawai() {
	
	doAjaxCall${formName}("doChangePegawai");
}
</script> 
