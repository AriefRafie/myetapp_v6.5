<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
#if ($ID_PERMOHONAN == '' && $ID_PB == '')
<div class="warning">Sila pilih Pihak Berkepentingan yang ingin disemak terlebih dahulu di menu Hakmilik dan Pihak Berkepentingan dalam tab Seksyen 8.</div>
<br />
#else
#if ($deleteMaklumatKebangkrapan == 'true')
<div class="success">Permohonan semakan kebangkrapan telah berjaya dihapuskan.</div>
#else
#if ($sendMaklumatKebangkrapan == 'true')
    #if ($isJIMUser == 'true')
<div class="success">Semakan kebangkrapan telah berjaya dihantar ke JKPTG.</div>
<br />
    #else
<div class="success">Permohonan bagi mendapatkan semakan kebangkrapan telah berjaya dihantar ke MdI.</div>
<br />
    #end
#elseif ($saveMaklumatKebangkrapan == 'true')
<div class="success">Permohonan telah berjaya disimpan.</div>
<br />
#elseif ($action2 == 'deleteMaklumatKebangkrapan' && $deleteMaklumatKebangkrapan == 'false')
<div class="error">Permohonan tidak berjaya untuk dihapuskan. Sila cuba sebentar lagi.</div>
<br />
#end
#if ($READONLY_JIM != '')
    #set ($READONLY_JIM = 'readonly="readonly" class="disabled"')
#end
#if ($sendMaklumatKebangkrapan == 'true' && $isJIMUser == 'true')
	#set ($READONLY_JIM = 'readonly="readonly" class="disabled"')
	#set ($DISABLE_JIM = 'disabled="disabled" class="disabled"')
#end
<fieldset>
  <legend><strong>MAKLUMAT SEMAKAN</strong></legend>
  <br />
  <table width="70%" border="0" align="center">
    <tr>
      <td width="38%" align="right" valign="top" nowrap="nowrap"><strong>NAMA</strong></td>
      <td width="2%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="60%" align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMAPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO KAD PENGENALAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOKPPEMOHON</td>
    </tr>
    
    <tr>
      <td colspan="3" align="right" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3"  valign="top" nowrap="nowrap">
        <fieldset>
          <legend><strong>MALAYSIA DEPARTMENT OF INSOLVENCY</strong></legend>
          <table width="100%" border="0" align="center">
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong><span class="mandatori">*</span> STATUS KEBANGKRAPAN</strong></td>
              <td align="center" valign="top" nowrap="nowrap">:</td>
              <td align="left" valign="top" nowrap="nowrap">
#if ($MP_STATUSKEBANGKRAPAN == '1')              
                <input type="radio" id="MP_STATUSKEBANGKRAPAN1" name="MP_STATUSKEBANGKRAPAN" value="1" checked="checked" $DISABLE_JIM />Ya
#else
                <input type="radio" id="MP_STATUSKEBANGKRAPAN1" name="MP_STATUSKEBANGKRAPAN" value="1" $DISABLE_JIM />Ya
#end              </td>
            </tr>
            <tr>
              <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
              <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
              <td align="left" valign="top" nowrap="nowrap">
#if ($MP_STATUSKEBANGKRAPAN == '1')                            
                <input type="radio" id="MP_STATUSKEBANGKRAPAN0" name="MP_STATUSKEBANGKRAPAN" value="0" $DISABLE_JIM />Tidak
#else
                <input type="radio" id="MP_STATUSKEBANGKRAPAN0" name="MP_STATUSKEBANGKRAPAN" value="0" checked="checked" $DISABLE_JIM />Tidak
#end              </td>
            </tr>
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH SEBUTAN MAHKAMAH</strong></td>
              <td align="center" valign="top" nowrap="nowrap">:</td>
              <td align="left" valign="top" nowrap="nowrap"><input name="MP_TARIKHSEBUTAN" type="text" id="MP_TARIKHSEBUTAN" style="text-transform:uppercase" value="$!MP_TARIKHSEBUTAN" size="15" maxlength="10" $READONLY_JIM />&nbsp;<a href="javascript:displayDatePicker('MP_TARIKHSEBUTAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
            </tr>
#if ($MP_SEMAKINDIVIDU == 'true')            
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH PERINTAH PENERIMAAN &amp; PENGHUKUMAN</strong></td>
              <td align="center" valign="top" nowrap="nowrap">:</td>
              <td align="left" valign="top" nowrap="nowrap"><input name="MP_TARIKHHUKUM" type="text" id="MP_TARIKHHUKUM" style="text-transform:uppercase" value="$!MP_TARIKHHUKUM" size="15" maxlength="10" $READONLY_JIM />&nbsp;<a href="javascript:displayDatePicker('MP_TARIKHHUKUM',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
            </tr>
#else            
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH PERINTAH PENERIMAAN &amp; PENGGULUNGAN</strong></td>
              <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
              <td align="left" valign="top" nowrap="nowrap"><input name="MP_TARIKHGULUNG" type="text" id="MP_TARIKHGULUNG" style="text-transform:uppercase" value="$!MP_TARIKHGULUNG" size="15" maxlength="10" $READONLY_JIM />&nbsp;<a href="javascript:displayDatePicker('MP_TARIKHGULUNG',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
            </tr>
#end            
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong>CAWANGAN</strong></td>
              <td align="center" valign="top" nowrap="nowrap">:</td>
              <td align="left" valign="top" nowrap="nowrap"><input name="MP_CAWANGAN" type="text" id="MP_CAWANGAN" style="text-transform:uppercase" value="$!MP_CAWANGAN" size="50" maxlength="255" $READONLY_JIM /></td>
            </tr>
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong>NO ESTET</strong></td>
              <td align="center" valign="top" nowrap="nowrap">:</td>
              <td align="left" valign="top" nowrap="nowrap"><input name="MP_NOESTET" type="text" id="MP_NOESTET" style="text-transform:uppercase" value="$!MP_NOESTET" size="50" maxlength="255" $READONLY_JIM /></td>
            </tr>
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong>CATATAN</strong></td>
              <td align="center" valign="top" nowrap="nowrap">:</td>
              <td align="left" valign="top" nowrap="nowrap"><textarea name="MP_CATATAN" cols="50" rows="3" id="MP_CATATAN" $READONLY_JIM style="text-transform:uppercase">$!MP_CATATAN</textarea></td>
            </tr>
            <tr>
              <td colspan="3" valign="top" nowrap="nowrap">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="3" valign="top" nowrap="nowrap"><em>Sila pastikan bahagian bertanda <span class="mandatori">*</span> diisi.</em></td>
            </tr>
          </table>
        </fieldset>
        <br />
      </td>
    </tr>
  </table>
  <table width="100%" border="0" align="center">
    <tr>
      <td>
        <fieldset>
          <legend><strong>MAKLUMAT TANAH</strong></legend>
          <table width="100%" border="0" align="center">
            <tr class="table_header">
              <td width="5%" align="center"><strong>NO</strong></td>
              <td width="10%"><strong>NO LOT</strong></td>
              <td width="10%"><strong>NO HAKMILIK</strong></td>
              <td width="10%"><strong>JENIS HAKMILIK</strong></td>
              <td width="10%"><strong>LUAS ASAL</strong></td>
              <td width="10%"><strong>LUAS DIAMBIL</strong></td>
              <td width="10%"><strong>SYER</strong></td>
              <td width="10%"><strong>NEGERI</strong></td>
              <td width="10%"><strong>DAERAH</strong></td>
              <td width="10%"><strong>MUKIM</strong></td>
            </tr>
#set ($list = '')
#foreach ($list in $ListHTA)
    #if ($list.No == '') 
        #set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
        #set ($row = 'row1')
    #else 
        #set ($row = 'row2')
    #end
            <tr>
    #if ($list.No != '') 
              <td class="$row" valign="top" align="center">$!list.No</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.NoLot</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.NoHakmilik</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.JenisHakmilik</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.LuasAsal</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.LuasDiambil</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.BA / $!list.BB</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.Negeri</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.Daerah</td>
              <td class="$row" valign="top" style="text-transform:uppercase">$!list.Mukim</td>
    #else
              <td colspan="10" class="$row" style="text-align:center">Tiada Rekod</td>
    #end            </tr>
#end
            <tr>
              <td colspan="10">&nbsp;</td>
            </tr>
          </table>
        </fieldset>      
      </td>
    </tr>
  </table>
</fieldset>
<div style="text-align:center">
  <br />
  <input id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onclick="backMaklumatKebangkrapan();" />&nbsp;
#if ($isJIMUser == 'true')
#if ($sendMaklumatKebangkrapan != 'true')
#if ($isPermohonanSelesai != 'true')
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Simpan" onclick="saveMaklumatKebangkrapan();" />&nbsp;
  <input id="cmdHantarJKPTG" name="cmdHantarJKPTG" type="button" value="Hantar ke JKPTG" onclick="sendMaklumatKebangkrapan();" />&nbsp;
#end
#end
#else
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Hantar ke MdI" onclick="sendMaklumatKebangkrapan();" />&nbsp;
#end
#if ($isJIMUser != 'true')
#if ($haveINTData == 'true')
  <input type="button" id="cmdDelete" name="cmdDelete" value="Batalkan Permohonan Semakan ke MdI" onclick="deleteMaklumatKebangkrapan();" />
#end
#end
</div>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_PB" name="ID_PB" value="$ID_PB" />
<input type="hidden" id="MP_SEMAKINDIVIDU" name="MP_SEMAKINDIVIDU" value="$MP_SEMAKINDIVIDU" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<script type="text/javascript">
  function saveMaklumatKebangkrapan() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMaklumatKebangkrapan&action2=saveMaklumatKebangkrapan";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function sendMaklumatKebangkrapan() {
#if ($isJIMUser == 'true')
    #if ($MP_SEMAKINDIVIDU == 'true')
      if (document.getElementById('MP_STATUSKEBANGKRAPAN0').checked == 'true' && document.getElementById('MP_TARIKHHUKUM').value == '') {
          alert('Sila pastikan tarikh perintah penerimaan & penghukuman diisi.');
          return;
      }
    #else
      if (document.getElementById('MP_STATUSKEBANGKRAPAN0').checked == 'true' && document.getElementById('MP_TARIKHGULUNG').value == '') {
          alert('Sila pastikan tarikh perintah penerimaan & penggulungan diisi.');
          return;
      }
    #end  
#end
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMaklumatKebangkrapan&action2=sendMaklumatKebangkrapan";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function deleteMaklumatKebangkrapan() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMaklumatKebangkrapan&action2=deleteMaklumatKebangkrapan";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function backMaklumatKebangkrapan() {
#if ($isJIMUser == 'true')
      document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMyInfoMaklumatKebangkrapan";
#else
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMaklumatKebangkrapan&action2=";
#end  
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>
#end
#end