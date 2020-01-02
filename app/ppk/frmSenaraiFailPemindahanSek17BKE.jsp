<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<fieldset>
<p></p>
<fieldset><legend><b>Carian</b>
</legend>
<table width="100%" align="center" border="0">
  <tbody>
    <tr>
      <td width="30%" height="24" scope="row" align="right">No Fail : </td>
      <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
      <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">Nama Simati : </td>
      <td width="70%"><input name="txtSimati" id="txtSimati" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">No.KP Simati : </td>
      <td width="70%" style="text-transform:uppercase;"><select name="jenisIc" style="width: 100px;">
      <option value="0">Sila Pilih</option>
      <option value="1">No. KP Baru</option>
      <option value="2">No. KP Lama</option>
      <option value="3">No. KP Lain</option>
      </select>&nbsp;&nbsp;<input name="txtIcSimati" id="txtIcSimati" type="text" value="" size="20" maxlength="14" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td scope="row" height="50px;"></td>
      <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:search_data()">
        <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:cancel()"></td>
    </tr>
  </tbody>
</table>
</fieldset>

<fieldset>
<legend><b>Senarai Fail Pemindahan Bidang Kuasa Eksklusif</b></legend>
<table align="center" width="100%"> 
  <tbody>
    <tr class="table_header">
      <td scope="row" width="66px">Bil</td>
      <td width="350px">No Fail</td>
      <td width="400px">Nama Simati</td>
      <td width="250px">Tarikh Mohon</td>
      <td width="350px">Status Fail</td>
    </tr>
   #set ($noFail = "")
   #set ($tarikhDaftar = "")
   #set ($tarikh_Mohon = "")
   #set ($keterangan = "")
   #set ($noFail1 = "")
   #set ($tarikhDaftar1 = "")
   #set ($tarikh_Mohon1 = "")
   #set ($keterangan1 = "")
    #set ($bil = "")
    #if ($carix == "2")
    #set ($cnt = 0)
	       #foreach($fail in $Senaraifail1)
	           #set ($i = $velocityCount)
		        #if (($i % 2) == 0)
		       		#set ($row = "row2")
		        #else
		       		#set ($row = "row1")
		        #end
				   #set ($noFail = $fail.no_Fail)
				   #set ($bil = $fail.bil)
				   #set ($tarikhDaftar = $fail.tarikhDaftar)
				   #set ($tarikh_Mohon = $fail.tarikh_Mohon)
				   #set ($keterangan = $fail.keterangan)
				   #set ($namasimati = $fail.namasimati)
					   #if ($CountList1 > 0)
					   #set ($cnt = $cnt + 1)
					   <tr >
				          <td class="$row">$bil</td>
				          <td class="$row"><a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_simati')" class="style1">$noFail
				          </a></td>
				          <td class="$row">$namasimati.toUpperCase()</td>
				          <td class="$row">$tarikh_Mohon</td>
				          <td class="$row">$keterangan</td>
				        </tr>
				        #else
				        <tr >
				          <td class="$row" colspan="5" align="center">Tiada Rekod </td>
				         </tr>
				         #end
	        #end
        
   #elseif ($carix == "1")
   		#set ($cnt2 = 0)
	         #foreach($fail1 in $Senaraifail)
	         
	         #set ($i = $velocityCount)
		        #if (($i % 2) == 0)
		       		#set ($row = "row2")
		        #else
		       		#set ($row = "row1")
		        #end
				   #set ($bil = $fail1.bil)
				   #set ($noFail = $fail1.no_Fail)
				   #set ($tarikhDaftar = $fail1.tarikhDaftar)
				   #set ($tarikh_Mohon = $fail1.tarikhmohon)
				   #set ($keterangan = $fail1.keterangan)
				   #set ($namasimati = $fail1.namasimati)
				   			#if ($CountList > 0)
				   			#set ($cnt2 = $cnt2 + 1)
        <tr >
          <td class="$row">$bil</td>
          <td class="$row"><a href="javascript:edit_item('$fail1.id_Permohonan','$fail1.id_simati')" class="style1">$noFail
          </a></td>
          <td class="$row">$namasimati.toUpperCase()</td>
          <td class="$row">$tarikh_Mohon</td>
          <td class="$row">$keterangan</td>
        </tr>
        					#else
        <tr >
          <td class="$row" colspan="5" align="center">Tiada Rekod</td>
         </tr>
        					#end
   			#end
   #end
  </tbody>
</table>
</fieldset>
</fieldset>
	<input type="hidden" name="hitButt" />
	<input type="hidden" name="idpermohonan"/>
    <input type="hidden" name="idSimati" />
    <input type="hidden" name="idFlag" />
    <input type="hidden" name="flagno" />
</form>
<script>
function cancel() {
document.${formName}.reset();
document.${formName}.txtNoFail.value = "";
document.${formName}.txtNoFail.focus();
}
function Tambah() {
	//document.${formName}.hitButt.value = "tambah";
	//document.${formName}.idFlag.value = "1";
	//document.${formName}.flagno.value = "0";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8SenaraiSemak";
	document.${formName}.submit();
}
function search_data(){
	if (document.${formName}.txtNoFail.value == "" && document.${formName}.txtPemohon.value == "" && document.${formName}.txtSimati.value == "" && document.${formName}.txtIcSimati.value == ""){
		alert("Sila masukkan maklumat yang ingin dicari.");
	}
	else {
	document.${formName}.method="post";
	document.${formName}.hitButt.value = "Cari";
	document.${formName}.action.value = "";
	document.${formName}.submit();
	}
}
function cetak() {
	window.print();
}
function edit_item(id,id2){
	document.${formName}.hitButt.value = "papar";
	document.${formName}.action.value = "";
	document.${formName}.idpermohonan.value = id;
	document.${formName}.idSimati.value = id2;
	document.${formName}.submit();
}
</script>

