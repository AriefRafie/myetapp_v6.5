<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
<fieldset><legend><strong>Maklumat Penampalan Notis Awam</strong></legend>
    <table width="100%">
  <tr>
    <td align="left" valign="top"><span class="style1">#if($mode!='viewNotis')*#end</span></td>
    <td align="left" valign="top">Tarikh Tampal</td>
    <td valign="top">:</td>
    <td>
    <label>
      <input name="txdTarikhTampal" type="text" id="txdTarikhTampal" value="$TARIKH_TAMPAL" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_tampal_check','yes','tampal','tarikh');">

      #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTarikhTampal',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end <span class="style52">dd/mm/yyyy</span>    </label>    </td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($disabledJenisTampal !='disabled')*#end</span></td>
    <td width="29%" align="left" valign="top">Tempat Tampal</td>
    <td width="1%" valign="top">:</td>
    <td width="70%"><label>
      <select name="socTempatTampal" id="socTempatTampal" $readonly="$readonly" class="$disabledJenisTampal">
      #if($JENIS_TEMPAT_TAMPAL == '0')
        <option value="0" selected>SILA PILIH</option>
           	#if($PTG == '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH != '3')
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH != '3')
			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH == '3')
			<option value="0"></option>
            
            #elseif($PTG != '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH != '3')
            <option value="1">PTG/PTD</option>
			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG != '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH == '3')
            <option value="1">PTG/PTD</option>
			
            #elseif($PTG != '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH == '3')
            <option value="1">PTG/PTD</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>

             #elseif($PTG != '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH != '3')
            <option value="1">PTG/PTD</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            <option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH == '3')
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            #end
           
      #end
      #if($JENIS_TEMPAT_TAMPAL == '1')
      
       	#if($PTG == '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH != '3')
            <option value="0">SILA PILIH</option>
            <option value="1" selected>PTG/PTD</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH != '3')
            <option value="0">SILA PILIH</option>
            <option value="1" selected>PTG/PTD</option>
			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH == '3')
			<option value="0">SILA PILIH</option>
            <option value="1" selected>PTG/PTD</option>
            
            #elseif($PTG != '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH != '3')
            <option value="0">SILA PILIH</option>
            <option value="1" selected>PTG/PTD</option>
			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG != '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH == '3')
            <option value="0">SILA PILIH</option>
            <option value="1" selected>PTG/PTD</option>
			
            #elseif($PTG != '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH == '3')
            <option value="0">SILA PILIH</option>
            <option value="1" selected>PTG/PTD</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>

             #elseif($PTG != '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH != '3')
            <option value="0">SILA PILIH</option>
            <option value="1" selected>PTG/PTD</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            <option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH == '3')
            <option value="0">SILA PILIH</option>
            <option value="1" selected>PTG/PTD</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            #end
           
      #end
      #if($JENIS_TEMPAT_TAMPAL == '2')
      
       	#if($PTG == '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH != '3')
            <option value="0">SILA PILIH</option>
            <option value="2" selected>PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH != '3')
			 <option value="0">SILA PILIH</option>
             <option value="2" selected>PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            <option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH == '3')
			 <option value="0">SILA PILIH</option>
			 <option value="2" selected>PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>            
            #elseif($PTG != '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH != '3')
             <option value="0">SILA PILIH</option>
            <option value="1">PTG/PTD</option>
            <option value="2" selected>PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG != '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH == '3')
 			<option value="0">SILA PILIH</option>
            <option value="1">PTG/PTD</option>
            <option value="2" selected>PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
			
            #elseif($PTG != '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH == '3')
             <option value="0">SILA PILIH</option>
            <option value="1">PTG/PTD</option>
            <option value="2" selected>PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>

             #elseif($PTG != '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH != '3')
             <option value="0">SILA PILIH</option>
            <option value="1">PTG/PTD</option>
            <option value="2" selected>PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            <option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH == '3')
             <option value="0">SILA PILIH</option>
            <option value="2" selected>PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            #end
           
      #end
      #if($JENIS_TEMPAT_TAMPAL == '3')
        
        	#if($PTG == '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH != '3')
            <option value="0">SILA PILIH</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
			<option value="3" selected>TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH != '3')
			<option value="0">SILA PILIH</option>
            <option value="3" selected>TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH == '3')
			<option value="0">SILA PILIH</option>
            <option value="3" selected>TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG != '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH != '3')
            <option value="0">SILA PILIH</option>
            <option value="1">PTG/PTD</option>
			<option value="3" selected>TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG != '1' && $PAPAN_KENYATAAN == '2' && $ATAS_TANAH == '3')
            <option value="0">SILA PILIH</option>
            <option value="1">PTG/PTD</option>
            <option value="3" selected>TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
			
            #elseif($PTG != '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH == '3')
            <option value="0">SILA PILIH</option>
            <option value="1">PTG/PTD</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            <option value="3" selected>TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>

             #elseif($PTG != '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH != '3')
            <option value="0">SILA PILIH</option>
            <option value="1">PTG/PTD</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
            <option value="3" selected>TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            
            #elseif($PTG == '1' && $PAPAN_KENYATAAN != '2' && $ATAS_TANAH == '3')
            <option value="0">SILA PILIH</option>
            <option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
        <option value="3" selected>TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
            #end
           
      #end 
      
      </select>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($mode!='viewNotis')*#end</span></td>
    <td align="left">Tempat</td>
    <td>:</td>
    <td><textarea name="txtTempat" cols="40" class="$disabled" id="txtTempat" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonly="$readonly">$TEMPAT</textarea></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>
    #if($mode == 'newNotis')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick = "simpan('$idPermohonan')" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick = "batal()"/>
    #end
    #if($mode == 'viewNotis')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()"/>
    <!--  <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="hapus()"/> -->
    #end    
    #if($mode == 'updateNotis')
      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onClick="simpan_update('$ID_NOTISAWAM')"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batalupdate()"/>
     #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>    </td>
  </tr>
</table>

    
    
</fieldset>
    <br>
<fieldset>
    <legend><strong>Senarai Penampalan Notis Awam</strong></legend>
        <table width="100%">
          <tr>
            <td colspan="4"><label>
           #if($mode != 'newNotis' )
              <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()"/>
           #end
            </label></td>
          </tr>
          <tr class="table_header">
            <td width="5%"><strong>Bil.</strong></td>
            <td width="19%"><strong>Tempat Tampal</strong></td>
            <td width="39%"><strong>Tempat</strong></td>
            <td width="37%"><strong>Tarikh Tampal</strong></td>
          </tr>
          #foreach ($notis in $TampalNotisList)
  
            #if ($notis.bil == '') 
            #set ($row = 'row1')
            #elseif ($notis.bil % 2 != 0)
            #set ($row = 'row1')
            #else 
            #set ($row = 'row2')
            #end
          <tr>
            <td class="$row">$notis.bil</td>
            #if ($notis.bil != '')
            <td class="$row"><a href="javascript:viewNotis('$notis.id_notisawam')"><font color="blue">$notis.tempat_tampal</font></a></td>
            #else
              <td class="$row">$notis.tempat_tampal</td>
            #end
            <td class="$row">$notis.tempat</td>          
            <td width="19%" class="$row">$notis.tarikh</td>
          </tr> 
           #end
        </table>
</fieldset>
<input name="id_NotisAwam" type="hidden" id="id_NotisAwam" value="$ID_NOTISAWAM">
<input name="ptg" id="ptg" type="hidden" value="$PTG">
<input name="papanKenyataan" id="papanKenyataan" type="hidden" value="$PAPAN_KENYATAAN">
<input name="atasTanah" id="atasTanah" type="hidden" value="$ATAS_TANAH">
<input type="hidden" name="id_fail" value="$idFail" />
<input type="hidden" name="id_permohonan" value="$idPermohonan" />
<script>
function tambah(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=newNotis";
	document.${formName}.submit();
}
function simpan(id_permohonan){
	 var tampal  = document.${formName}.txdTarikhTampal.value;
	  
	  var dt1Tampal  = parseInt(tampal.substring(0,2),10);
	  var mon1Tampal  = parseInt(tampal.substring(3,5),10)-1;
	  var yr1Tampal   = parseInt(tampal.substring(6,10),10);
		   
	  var dateTampal = new Date(yr1Tampal, mon1Tampal, dt1Tampal);
	  
	  var currentTime = new Date();
	   
	if(dateTampal < currentTime){
		alert("Tarikh Tampal tidak boleh kurang dari tarikh hari ini. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhTampal.focus(); 
		return;
 	}
	if(document.${formName}.txdTarikhTampal.value == ""){
		alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
  		document.${formName}.txdTarikhTampal.focus(); 
		return;
	}
	if(document.${formName}.socTempatTampal.value == ""){
		alert("Sila masukkan \"Tempat Tampal\" terlebih dahulu.");
  		document.${formName}.socTempatTampal.focus(); 
		return;
	}
	
	if(document.${formName}.txtTempat.value == ""){
		alert("Sila masukkan \"Tempat\" terlebih dahulu.");
  		document.${formName}.txtTempat.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=simpanNotis";
	document.${formName}.submit();
}
function simpan_update(id_NotisAwam){
	
	 var tampal  = document.${formName}.txdTarikhTampal.value;
	  
	  var dt1Tampal  = parseInt(tampal.substring(0,2),10);
	  var mon1Tampal  = parseInt(tampal.substring(3,5),10)-1;
	  var yr1Tampal   = parseInt(tampal.substring(6,10),10);
		   
	  var dateTampal = new Date(yr1Tampal, mon1Tampal, dt1Tampal);
	  
	  var currentTime = new Date();
	   
	if(dateTampal < currentTime){
		alert("Tarikh Tampal tidak boleh kurang dari tarikh hari ini. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhTampal.focus(); 
		return;
 	}
	if(document.${formName}.txdTarikhTampal.value == ""){
		alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
  		document.${formName}.txdTarikhTampal.focus(); 
		return;
	}
	if(document.${formName}.txtTempat.value == ""){
		alert("Sila masukkan \"Tempat\" terlebih dahulu.");
  		document.${formName}.txtTempat.focus(); 
		return;
	}
	
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_NotisAwam.value = id_NotisAwam;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=kemaskiniNotis";
	document.${formName}.submit();
}
function kemaskini(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=updateNotis";
	document.${formName}.submit();
}
function viewNotis(id_NotisAwam){
	
	document.${formName}.id_NotisAwam.value = id_NotisAwam;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=viewNotis";
	document.${formName}.submit();
}
function hapus(){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=hapusNotis";
	document.${formName}.submit();
}
function batal(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=newNotis";
	document.${formName}.submit();
}
function kembali(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam";
	document.${formName}.submit();
}
function batalupdate(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=viewNotis";
	document.${formName}.submit();
}
</script>
