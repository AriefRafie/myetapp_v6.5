<form name="f1" method="POST"> 
      <input type="hidden" name="command">  
      <input type="hidden" name="id_fail">         
      <input type="hidden" name="id_permohonan">
      <input type="hidden" name="id_status">
      <input type="hidden" name="id_tanahumum">
<!--      <input type="hidden" name="id_suburusan">-->       
<fieldset>
    <fieldset>
    <legend>Carian</legend>
      <table width="100%" cellspacing="2" cellpadding="0" border="0">
      <tr>
        <td width="16%">&nbsp;</td>
        <td width="21%"><div align="right">No Fail&nbsp;</div></td>
        <td width="63%">:&nbsp;<input style="text-transform:uppercase" name="no_fail" type="text" value="$carianFail" size="38"></td>
      </tr>						
      <tr>
        <td width="16%">&nbsp;</td>
        <td width="21%"><div align="right">Tarikh&nbsp;</div></td>
        <td width="63%">:&nbsp;<input name="txdTarikhMohon" type="text" value="$CarianTarikhMohon" />
        <a href="javascript:displayDatePicker('txdTarikhMohon',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="right">Status&nbsp;&nbsp;</div></td>
        <td>:&nbsp;$selectStatusJabatanTeknikal</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="right">Urusan Permohonan</div></td>
        <td>:&nbsp;<input style="text-transform:uppercase" name="txtUrusan" type="text" value="02-PENGAMBILAN TANAH DI BAWAH SEKSYEN 8" size="45" readonly="true" /></td>
      </tr>
      <tr>
        <td></td>
      <td></td><td>&nbsp;&nbsp;<input name="cari" value="Cari" type="button" onclick="javascript:search_data();" />
        <input type="button" value = "Kosongkan" onclick="javascript:cancel();" /></td>
      </tr>
      </table>          
    </fieldset>    
    <p>
    
    <fieldset>
       <legend>Senarai Permohonan UPT</legend>
          <div style="height: 550px; width: 100%; overflow: auto;">
      <table width="100%" border="0" cellspacing="2">
      <thead>
      <tr class="table_header">
      <td width="5%" class="fixedLeft"><div align="center">No</div></td>
      <td width="20%"><div align="center">No Fail</div></td>
      <td width="20%"><div align="center">No Permohonan</div></td>
      <td width="10%"><div align="center">Tarikh</div></td>
      <td width="25%"><div align="center">Kementerian</div></td>
      <td width="15%"><div align="center">Status</div></td>
	  </tr>
      <thead> 
      
      #foreach ( $senarai in $PermohonanList )
           
       #if ($senarai.bil == '')
       #set ($row = 'row1')
       
       #elseif ($senarai.bil % 2 != 0)
       #set ($row = 'row1')
       
       #else 
       #set ($row = 'row2')
       #end      
      
      <tr valign="top">
      <td class="$row">$senarai.bil</td>
      <td class="$row"><a href="javascript:view('$senarai.id_fail','$senarai.id_permohonan','$senarai.id_status')">$senarai.no_fail</a></td>
      <td class="$row">$senarai.no_permohonan</td>
      <td class="$row">$senarai.tarikh_permohonan</td>     
      <td class="$row">$senarai.nama_kementerian</td>
      <td class="$row">$senarai.keterangan</td>
      </tr>
      #end
      </table>
      </div>
    </fieldset>  
</fieldset>
</form>
 
<script>
function search_data(){
	document.f1.method = "POST";
	document.f1.command.value = "";
	document.f1.action = "";
	document.f1.submit();
}
function cancel() {
	document.f1.command.value = "cancel_LTList";
	document.f1.action = "";
	document.f1.submit();	
}
function view(id_fail,id_permohonan,id_status) {
	document.f1.id_fail.value = id_fail;
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.id_status.value = id_status;
	document.f1.command.value = "view";
	document.f1.action = "";
	document.f1.submit();
}
</script>

<script language="JavaScript"> document.forms[0].no_fail.focus(); </script>