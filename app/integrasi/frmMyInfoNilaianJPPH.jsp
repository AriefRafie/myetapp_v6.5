<style type="text/css">
<!--
  .link {
    color: #0000FF;
	cursor:pointer;
  }
-->
</style>
<fieldset>
  <legend><strong>CARIAN MAKLUMAT PERMOHONAN</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row">No Fail</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoFail" type="text" id="Carian_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoFail" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No Permohonan</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoPermohonan" type="text" id="Carian_NoPermohonan" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoPermohonan" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row">Tarikh Hantar</td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top">
        <input name="Carian_TarikhHantarDari" type="text" id="Carian_TarikhHantarDari" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_TarikhHantarDari" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('Carian_TarikhHantarDari',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
        hingga
        <input name="Carian_TarikhHantarKe" type="text" id="Carian_TarikhHantarKe" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_TarikhHantarKe" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('Carian_TarikhHantarKe',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>      </td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">Tarikh Selesai</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">
        <input name="Carian_TarikhSelesaiDari" type="text" id="Carian_TarikhSelesaiDari" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_TarikhSelesaiDari" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('Carian_TarikhSelesaiDari',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
        hingga
        <input name="Carian_TarikhSelesaiKe" type="text" id="Carian_TarikhSelesaiKe" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_TarikhSelesaiKe" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('Carian_TarikhSelesaiKe',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>      </td>      
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">Status Fail</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">
        <select name="Carian_Status" id="Carian_Status" style="width:auto">
          <option value="0">-- SILA PILIH --</option>
          <option value="1" $!selectedStatus1>BARU</option>
          <option value="2" $!selectedStatus2>DALAM PROSES JPPH</option>
          <option value="3" $!selectedStatus3>SELESAI</option>
          <option value="4" $!selectedStatus4>DIKEMBALIKAN</option>
        </select>
      </td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row">
        <input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchNilaianHTATerdahulu();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyNilaianHTATerdahulu();" />
#if ($haveList == 'true')        
        <input id="cmdCetakSenarai" name="cmdCetakSenarai" type="button" value="Cetak Senarai" onclick="printList();" />
#end        
      </td>
    </tr>
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI DATA</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="25%"><strong>NAMA PEMOHON</strong></td>
      <td width="20%"><strong>JENIS PERMOHONAN</strong></td>
      <td width="10%"><strong>STATUS</strong></td>
      <td width="10%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="10%" align="center"><strong>TARIKH SELESAI</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaian)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    #if ($list.JenisPermohonan == 'HTA (Ada Hakmilik)')
    	#set ($RT = '1')
    #elseif ($list.JenisPermohonan == 'HTA (Tiada Hakmilik)')
    	#set ($RT = '2')
    #elseif ($list.JenisPermohonan == 'HTA (Ada Hakmilik)')
    	#set ($RT = '3')
    #end
    <tr>
    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top"><span class="link" onclick="javascript:printHarta('$RT', '$list.IDHarta')">$list.NoFail</span></td>
      <td class="$row" valign="top">$list.NamaPemohon</td>
      <td class="$row" valign="top">$list.JenisPermohonan</td>
      <td class="$row" valign="top">$list.Status</td>
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.TarikhSelesai</td>
    #else
      <td colspan="7" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="7">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<input type="hidden" id="ID_PEMOHON" name="ID_PEMOHON" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<br />
<br />
<fieldset>
  <legend><strong>PANDUAN</strong></legend>
  <table width="70%" cellpadding="2" cellspacing="0" align="center">
    <tr>
      <td style="width:50%"><span style="color:#000000; font-weight:bold">*** HITAM</span> - Permohonan baru</td>
      <td style="width:50%"><span style="color:#0000FF; font-weight:bold">*** BIRU</span> - Permohonan lebih 5 hari<br /></td>
    </tr>
    <tr>
      <td><span style="color:#009900; font-weight:bold">*** HIJAU</span> - Permohonan lebih 14 hari<br /></td>
      <td><span style="color:#FF0000; font-weight:bold">*** MERAH</span> - Permohonan lebih 30 hari<br /></td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA TAK ALIH (ADA HAKMILIK)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="20%"><strong>MUKIM</strong></td>
      <td width="20%"><strong>NO PT/LOT</strong></td>
      <td width="10%" align="center"><strong>TARIKH KEMASKINI</strong></td>
      <td width="30%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaianHTAAH)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '')
    	#if ($list.Status == 'SELESAI')
            #set ($row_status = 'style="text-align:center; color:#000000"')
        #elseif ($list.Status == 'DIKEMBALIKAN')
            #set ($row_status = 'style="text-align:center; color:#000000; text-decoration:blink; font-weight:bold"')
        #else
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
                #set ($row_status = 'style="text-align:center; color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
                #set ($row_status = 'style="text-align:center; color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
                #set ($row_status = 'style="text-align:center; color:#0000FF"')
            #else
                #set ($row_color = '')
                #set ($row_status = 'style="text-align:center"')
            #end
        #end
        #if ($list.NoFail == '')
        	#set ($list.NoFail = $list.NoPermohonan)
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewNilaianHTA('$list.IDPermohonan', '$list.ID_HTA', 0)" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.Daerah</td>
      <td class="$row" valign="top" $row_color>$list.Mukim</td>
      <td class="$row" valign="top" $row_color>$list.NoPTLot</td>
      <td class="$row" valign="top" $row_color align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" $row_status>$list.Status</td>
    #else
      <td colspan="7" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="7">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA TAK ALIH (TIADA HAKMILIK)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="20%"><strong>MUKIM</strong></td>
      <td width="20%"><strong>NO PT/LOT</strong></td>
      <td width="10%" align="center"><strong>TARIKH KEMASKINI</strong></td>
      <td width="30%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaianHTATH)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
    	#if ($list.Status == 'SELESAI')
            #set ($row_status = 'style="text-align:center; color:#000000"')
        #elseif ($list.Status == 'DIKEMBALIKAN')
            #set ($row_status = 'style="text-align:center; color:#000000; text-decoration:blink; font-weight:bold"')
        #else
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
                #set ($row_status = 'style="text-align:center; color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
                #set ($row_status = 'style="text-align:center; color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
                #set ($row_status = 'style="text-align:center; color:#0000FF"')
            #else
                #set ($row_color = '')
                #set ($row_status = 'style="text-align:center"')
            #end
        #end
        #if ($list.NoFail == '')
        	#set ($list.NoFail = $list.NoPermohonan)
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewNilaianHTA('$list.IDPermohonan', '$list.ID_HTA', 1)" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.Daerah</td>
      <td class="$row" valign="top" $row_color>$list.Mukim</td>
      <td class="$row" valign="top" $row_color>$list.NoPTLot</td>
      <td class="$row" valign="top" $row_color align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" $row_status>$list.Status</td>
    #else
      <td colspan="7" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="7">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA ALIH (KENDERAAN)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NEGERI</strong></td>
      <td width="20%"><strong>DAERAH</strong></td>
      <td width="20%"><strong>NO PENDAFTARAN</strong></td>
      <td width="10%" align="center"><strong>TARIKH KEMASKINI</strong></td>
      <td width="30%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaianHAK)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
    	#if ($list.Status == 'SELESAI')
            #set ($row_status = 'style="text-align:center; color:#000000"')
        #elseif ($list.Status == 'DIKEMBALIKAN')
            #set ($row_status = 'style="text-align:center; color:#000000; text-decoration:blink; font-weight:bold"')
        #else
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
                #set ($row_status = 'style="text-align:center; color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
                #set ($row_status = 'style="text-align:center; color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
                #set ($row_status = 'style="text-align:center; color:#0000FF"')
            #else
                #set ($row_color = '')
                #set ($row_status = 'style="text-align:center"')
            #end
        #end
        #if ($list.NoFail == '')
        	#set ($list.NoFail = $list.NoPermohonan)
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewNilaianHAK('$list.IDPermohonan', '$list.ID_HA')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.Negeri</td>
      <td class="$row" valign="top" $row_color>$list.Daerah</td>
      <td class="$row" valign="top" $row_color>$list.NoPendaftaran</td>
      <td class="$row" valign="top" $row_color align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" $row_status>$list.Status</td>
    #else
      <td colspan="7" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="7">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN SEWAAN TANAH PERSEKUTUAN</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="10%"><strong>NO HAKMILIK</strong></td>
      <td width="10%"><strong>NO PT/LOT</strong></td>
      <td width="15%"><strong>NEGERI</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="15%" align="center"><strong>TARIKH KEMASKINI</strong></td>
      <td width="15%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaianSewaan)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
    	#if ($list.Status == 'SELESAI')
            #set ($row_status = 'style="text-align:center; color:#000000"')
        #elseif ($list.Status == 'DIKEMBALIKAN')
            #set ($row_status = 'style="text-align:center; color:#000000; text-decoration:blink; font-weight:bold"')
        #else
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
                #set ($row_status = 'style="text-align:center; color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
                #set ($row_status = 'style="text-align:center; color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
                #set ($row_status = 'style="text-align:center; color:#0000FF"')
            #else
                #set ($row_color = '')
                #set ($row_status = 'style="text-align:center"')
            #end
        #end
        #if ($list.NoFail == '')
        	#set ($list.NoFail = $list.NoPermohonan)
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewNilaianSewaaan('$list.IDPermohonan', '$list.IDHM')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.NoHakmilik</td>
      <td class="$row" valign="top" $row_color>$list.NoPTLot</td>
      <td class="$row" valign="top" $row_color>$list.Negeri</td>
      <td class="$row" valign="top" $row_color>$list.Daerah</td>
      <td class="$row" valign="top" $row_color align="center">$list.TarikhKemaskini</td>
      <td class="$row" valign="top" $row_status>$list.Status</td>
    #else
      <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="6">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN PAJAKAN TANAH PERSEKUTUAN</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="10%"><strong>NO HAKMILIK</strong></td>
      <td width="10%"><strong>NO PT/LOT</strong></td>
      <td width="15%"><strong>NEGERI</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="15%" align="center"><strong>TARIKH KEMASKINI</strong></td>
      <td width="15%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaianPajakan)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
    	#if ($list.Status == 'SELESAI')
            #set ($row_status = 'style="text-align:center; color:#000000"')
        #elseif ($list.Status == 'DIKEMBALIKAN')
            #set ($row_status = 'style="text-align:center; color:#000000; text-decoration:blink; font-weight:bold"')
        #else
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
                #set ($row_status = 'style="text-align:center; color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
                #set ($row_status = 'style="text-align:center; color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
                #set ($row_status = 'style="text-align:center; color:#0000FF"')
            #else
                #set ($row_color = '')
                #set ($row_status = 'style="text-align:center"')
            #end
        #end
        #if ($list.NoFail == '')
        	#set ($list.NoFail = $list.NoPermohonan)
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewNilaianPajakan('$list.IDPermohonan', '$list.IDHM')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.NoHakmilik</td>
      <td class="$row" valign="top" $row_color>$list.NoPTLot</td>
      <td class="$row" valign="top" $row_color>$list.Negeri</td>
      <td class="$row" valign="top" $row_color>$list.Daerah</td>
      <td class="$row" valign="top" $row_color align="center">$list.TarikhKemaskini</td>
      <td class="$row" valign="top" $row_status>$list.Status</td>
    #else
      <td colspan="8" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="8">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" />
<input type="hidden" id="NO_FAIL" name="NO_FAIL" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<input name="uid" id="uid" type="hidden" value="$uid" />
<script type="text/javascript">
  function viewNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA) {	
      document.${formName}.action = "$EkptgUtil.getTabID("JPPH",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&JENIS_HTA=" + JENIS_HTA + "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_HTA=" + ID_HTA;
      document.${formName}.submit();
  }
  function viewNilaianHAK(ID_PERMOHONAN, ID_HA) {	
      document.${formName}.action = "$EkptgUtil.getTabID("JPPH",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_HA=" + ID_HA;
      document.${formName}.submit();
  }
  function viewNilaianSewaaan(ID_PERMOHONAN, ID_HM) {
      document.${formName}.action = "$EkptgUtil.getTabID("JPPH",$portal_role)?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianSewaan&action2=view&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_HM=" + ID_HM;
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function viewNilaianPajakan(ID_PERMOHONAN, ID_HM) {
      document.${formName}.action = "$EkptgUtil.getTabID("JPPH",$portal_role)?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=view&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_HM=" + ID_HM;
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function searchNilaianHTATerdahulu() {
      if (document.${formName}.Carian_NoFail.value == '' && document.${formName}.Carian_NoPermohonan.value == '' && document.${formName}.Carian_TarikhHantarDari.value == '' && document.${formName}.Carian_TarikhSelesaiDari.value == '' && document.${formName}.Carian_Status.value == '0') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.Carian_NoFail.focus();
		  return;
	  }
      document.${formName}.action = "?action2=searchNilaianHTATerdahulu";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyNilaianHTATerdahulu() {
      document.${formName}.action = "?action2=";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function printList() {
      var NO_FAIL = document.${formName}.Carian_NoFail.value;
      var NO_PERMOHONAN = document.${formName}.Carian_NoPermohonan.value;
      var TARIKH_HANTAR_DARI = document.${formName}.Carian_TarikhHantarDari.value;
      var TARIKH_HANTAR_KE = document.${formName}.Carian_TarikhHantarKe.value;
      var TARIKH_SELESAI_DARI = document.${formName}.Carian_TarikhSelesaiDari.value;
      var TARIKH_SELESAI_KE = document.${formName}.Carian_TarikhSelesaiKe.value;
      var STATUS_FAIL = document.${formName}.Carian_Status.value;
      var uid = document.${formName}.uid.value;
      var url = "../servlet/ekptg.report.integrasi.ReportJPPH?reportType=PDF&rt=5&NO_FAIL=" + NO_FAIL + "&NO_PERMOHONAN=" + NO_PERMOHONAN + "&TARIKH_HANTAR_DARI=" + TARIKH_HANTAR_DARI + "&TARIKH_HANTAR_KE=" + TARIKH_HANTAR_KE + "&TARIKH_SELESAI_DARI=" + TARIKH_SELESAI_DARI + "&TARIKH_SELESAI_KE=" + TARIKH_SELESAI_KE + "&STATUS_FAIL=" + STATUS_FAIL + "&uid=" + uid;
      var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
  }
  function printHarta(RT, ID_HARTA) {
      var url = "../servlet/ekptg.report.integrasi.ReportJPPH?reportType=PDF&rt=" + RT + "&ID_HTA=" + ID_HARTA + "&ID_HA=" + ID_HARTA;
      var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
  }
</script>