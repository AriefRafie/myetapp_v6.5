<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>




 #set($open_role = "")  
   
      
      	#if($_portal_role == "(PPT)KetuaPenolongPengarah" 
        || $_portal_role == "(PPT)KetuaPenolongPengarahUnit"
        || $_portal_role == "(PPT)PelukisPelan"
        || $_portal_role == "(PPT)PembantuTadbirSekyenPengambilanTanah"
        || $_portal_role == "(PPT)PembantuTadbirUnitPengambilanTanah"
        || $_portal_role == "(PPT)Pengarah"
        || $_portal_role == "(PPT)PengarahTanahdanGalian"
        || $_portal_role == "(PPT)PengarahUnit"
        || $_portal_role == "(PPT)PenghantarNotis"
        || $_portal_role == "(PPT)PenolongPegawai TanahSeksyenPengambilanTanah"
        || $_portal_role == "(PPT)PenolongPegawaiTanahUnitPengambilanTanah"
        || $_portal_role == "(PPT)PenolongPegawaiTanahUnitPengambilanTanah(NT17)"
        || $_portal_role == "(PPT)PenolongPegawaiTanahUnitPengambilanTanah(NT27)"
        || $_portal_role == "(PPT)PenolongPengarah"
        || $_portal_role == "(PPT)PenolongPengarahUnit"
        || $_portal_role == "(PPT)PentadbirTanahdanDaerah"
        || $_portal_role == "adminppt")
        #set($open_role = "PPT_ROLE")
        #end
        
        #if($_portal_role == "adminppk" || $_portal_role == "user_ppk")
        #set($open_role = "PPK_ROLE")
        #end
        
        #if($_portal_role == "sptb")
        #set($open_role = "SPTB_ROLE")
        #end
        
        #if($_portal_role == "adminint")
        #set($open_role = "INT_ROLE")
        #end











#if ($sendSPTB == 'true' )
<div class="success">Permohonan telah berjaya dihantar ke SPTB.</div>
<br />
#end

#if ($sendJKPTG == 'true')
<div class="success">Permohonan telah berjaya dihantar ke JKPTG.</div>
<br />
#end

#if ($saveSPTB == 'true')
<!--<div class="success">Data telah berjaya disimpan.</div>
<br />-->
#end
#if ($action2 == 'deleteSPTB' && $deleteSPTB == 'false')
<div class="error">Data tidak berjaya untuk dihapuskan. Sila cuba sebentar lagi.</div>
<br />
#end

 #if($ID_SEKSYEN == "1")
	
		#foreach($data in $dataMaklumatTanah)
		
		#set($nama_negeriprojek=$data.nama_negeri)
		#set($nama_daerah=$data.nama_daerah)
        #set($nama_mukim=$data.nama_mukim)
        #set($kategoritanah=$data.kategori_tanah)

		#set($txtSeksyen=$data.seksyen)
		#set($txtNoHakmilik=$data.no_hakmilik)	
        #set($jenisNoHakmilik=$data.kod_jenis_hakmilik)				
		#set($txdTarikhLuput=$data.tarikh_luput)		
		#set($txdTarikhDaftar=$data.tarikh_daftar)				
		#set($txtBakiTempoh=$data.tempoh_luput)
		#set($txtNoSyit=$data.no_syit)
		#set($txtNoLot=$data.no_lotpt)
		#set($txtNoPT=$data.no_pt)	
		#set($txtLuasLotAmbil=$data.luas_ambil)
		#set($txtLuasLotAsal=$data.luas_lot)		
		#set($txtCatatan=$data.catatan)		
		#set($txtNoWartaRizab=$data.no_warta_rizab)		
		#set($txdTarikhWarta=$data.tarikh_warta_rizab)		
		#set($txtLain=$data.nama_lain_rizab)		
		#set($sorJenisRizab=$data.flag_jenis_rizab)
		#set($sorDropdownUnitAsal=$data.id_unitluaslot_convert)
		#set($sorDropdownUnitAmbil=$data.id_unitluasambil_convert)
		#set($txtLuasLotAsalSebelumConvert=$data.nama_luas_asal)
		#set($txtLuasLotAmbilSebelumConvert=$data.nama_luas_ambil)
		
		#set($txtLokasi=$data.lokasi)
		#set($txtSyaratNyata=$data.syarat_nyata)
		#set($txtSyaratKhas=$data.syarat_khas)		
		#set($txtSekatanKepentingan=$data.sekatan_kepentingan)
		#set($txtSekatanHak=$data.sekatan_hak)	
		
		#end
#end

 #if($ID_SEKSYEN == "2")

         #foreach($listamid in $listHTAid)
         #set($jenis_tanah=$listamid.JENIS_TANAH)
         #set($jenis_luas=$listamid.JENIS_LUAS)
         #set($no_cagaran=$listamid.nocagaran)
         #set($no_pajakan=$listamid.nopajakan)
         #set($luasmp = $listamid.luashmp)
         #set($no_perserahan = $listamid.noperserahan)         
         #end	
         
  #end


#if ($READONLY_SPTB != '')
	#set ($READONLY_SPTB = 'readonly="readonly" class="disabled"')
#end

<fieldset>
  <legend><strong>MAKLUMAT GERAN</strong></legend>
  <br />
  <table width="70%" border="0" align="center">
  
  #if($ID_SEKSYEN == "1")
  
    <tr>
      <td width="25%" align="right" valign="top" nowrap="nowrap"><strong>JENIS &amp; NO. HAKMILIK</strong></td>
      <td width="2%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="73%" align="left" valign="top" nowrap="nowrap" class="link">$MP_KOD_JENIS_HAKMILIK $MP_NO_HAKMILIK </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO. LOT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NO_LOT</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH DAFTAR</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_HARI  $MP_BULAN $MP_TAHUN </td>
    </tr>
     <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NEGERI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMA_NEGERI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>DAERAH</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMA_DAERAH</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>BANDAR/PEKAN/MUKIM</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMA_MUKIM</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>KELUASAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMA_LUAS_ASAL</td>
    </tr>
    
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO. SYIT PIAWAI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NO_SYIT</td>
    </tr>
    
     <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>KAWASAN RIZAB</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_JENIS_RIZAB</td>
    </tr>
    
     <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>KETEGORI TANAH</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_KATEGORI_TANAH</td>
    </tr>
    
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NAMA PEMILIK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" class="link">$MP_NAMA_PEMILIK</td>
    </tr>
    
     <tr>
				
				<td><div align="right"><strong>LOKASI</strong></div></td>
				<td><div align="center">:</div></td>
				<td class="link" style=" text-transform:uppercase">$!txtLokasi</td>
	</tr>
			<tr>
				
				<td valign="top"><div align="right"><strong>SYARAT NYATA</strong></div></td>
				<td valign="top"><div align="center">:</div></td>
			
				<td class="link" style=" text-transform:uppercase">$!txtSyaratNyata</td>
			</tr>
			<tr>
				
				<td><div align="right"><strong>SYARAT KHAS</strong></div></td>
				<td><div align="center">:</div></td>
				<td class="link" style=" text-transform:uppercase">$!txtSyaratKhas</td>
			</tr>
			<tr>
				
				<td><div align="right"><strong>SEKATAN KEPENTINGAN</strong></div></td>
				<td><div align="center">:</div></td>
				<td class="link" style=" text-transform:uppercase">$!txtSekatanKepentingan</td>
			</tr>
			<tr>
				
				<td><div align="right"><strong>SEKATAN HAK</strong></div></td>
				<td><div align="center">:</div></td>
				<td class="link" style=" text-transform:uppercase">$!txtSekatanHak</td>
			</tr>
    
    #end
    
    
    
     #if($ID_SEKSYEN == "2")
  
    <tr>
      <td width="25%" align="right" valign="top" nowrap="nowrap"><strong>JENIS &amp; NO. HAKMILIK</strong></td>
      <td width="2%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="73%" align="left" valign="top" nowrap="nowrap" class="link">$MP_KOD_JENIS_HAKMILIK $MP_NO_HAKMILIK </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO. LOT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NO_LOT</td>
    </tr>
     <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NEGERI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMA_NEGERI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>DAERAH</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMA_DAERAH</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>BANDAR/PEKAN/MUKIM</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMA_MUKIM</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>KELUASAN (HEKTAR/METER PERSEGI)</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$luasmp $jenis_luas </td>
    </tr>
    
    
     <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>KAWASAN RIZAB</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link" style=" text-transform:uppercase" >$jenis_tanah</td>
    </tr>
    
     <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>KETEGORI TANAH</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link" style=" text-transform:uppercase">$MP_KATEGORI_TANAH</td>
    </tr>
    
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO. PERSERAHAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link" style=" text-transform:uppercase" >$no_perserahan</td>
    </tr>
    
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO. PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link" style=" text-transform:uppercase" >$no_pajakan</td>
    </tr>
    
     <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO. CAGARAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link" style=" text-transform:uppercase" >$no_cagaran</td>
    </tr>
    
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NAMA PEMILIK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" class="link">$MP_NAMA_PEMILIK</td>
    </tr>
    
     
    
   #end
 
    
    
    <tr>
      <td colspan="3" align="right" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3"  valign="top" nowrap="nowrap">
        <fieldset>
          <legend><strong>SISTEM PENDAFTARAN TANAH BERKOMPUTER</strong></legend>
          <table width="70%" border="0" align="center">
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong>STATUS PENGESAHAN MAKLUMAT GERAN</strong></td>
              <td align="center" valign="top" nowrap="nowrap">:</td>
              <td align="left" valign="top" nowrap="nowrap"><input type="radio" id="MP_STATUSKEBANGKRAPAN1" name="MP_STATUSKEBANGKRAPAN" value="1" $MP_STATUSKEBANGKRAPAN1 $DISABLE_SPTB />Sah</td>
            </tr>
            <tr>
              <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
              <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
              <td align="left" valign="top" nowrap="nowrap"><input type="radio" id="MP_STATUSKEBANGKRAPAN0" name="MP_STATUSKEBANGKRAPAN" value="0" $MP_STATUSKEBANGKRAPAN0 $DISABLE_SPTB />Tidak Sah</td>
            </tr>
            <tr>
              <td align="right" valign="top" nowrap="nowrap"><strong>CATATAN</strong></td>
              <td align="center" valign="top" nowrap="nowrap">:</td>
              <td align="left" valign="top" nowrap="nowrap"><textarea name="MP_CATATAN" cols="50" rows="3" id="MP_CATATAN" $DISABLE_SPTB $READONLY_SPTB onkeyup="this.value=this.value.toUpperCase();">$!MP_CATATAN</textarea></td>
            </tr>
            <tr style="display:none">
              <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
              <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
              <td align="left" valign="top" nowrap="nowrap"></td>
            </tr>
          </table>
        </fieldset>      </td>
    </tr>
  </table>
<input type="checkbox" id="MP_SELESAI" name="MP_SELESAI" value="1" style="display:none" />
</fieldset>


<div style="text-align:center">
  <br />&nbsp;
  

 
  
  #if($open_role == "PPK_ROLE" || $open_role == "PPT_ROLE")
  <input type="button" id="cmdSend" name="cmdSend" value="Hantar ke SPTB" onclick="sendSPTB();" />
  #end
  
  
  &nbsp;
 
#if ($haveINTData == 'true' && $isSPTBUser == 'true')  
  <input type="button" id="cmdSend" name="cmdSend" value="Hantar ke JKPTG" onclick="saveSPTB('true');" />
  &nbsp;
#end 

#if($open_role != "PPK_ROLE" && $open_role != "PPT_ROLE") 
 <input type="button" id="cmdSave" name="cmdSave" value="Simpan" onclick="saveSPTB('false');" />&nbsp;  
#end

  <input type="button" id="cmdBack" name="cmdBack" value="Kembali" onclick="backSPTB('$!open_role');" />&nbsp;
#if ($haveINTData == 'true' && $isSPTBUser != 'true')  
  <input type="button" id="cmdDelete" name="cmdDelete" value="Hapus" onclick="deleteSPTB();" />&nbsp;
#end  
</div>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_PEMOHON" name="ID_PEMOHON" value="$ID_PEMOHON" />


<input type="hidden" id="ID_HAKMILIK" name="ID_HAKMILIK" value="$ID_HAKMILIK" />
<input type="hidden" id="ID_FAIL" name="ID_FAIL" value="$ID_FAIL" />
<input type="hidden" id="ID_SEKSYEN" name="ID_SEKSYEN" value="$ID_SEKSYEN" />

<input type="hidden" id="action2" name="action2" value="$action2" />
<script type="text/javascript">
#if ($saveSPTB == 'true')
  var ans = confirm();
#end
  function saveSPTB(status) {
  
 // alert("::::::: STATUS :"+status);
 // alert("::::::: STATUS CHECKED :"+document.${formName}.MP_SELESAI.checked);
  
  
  if(status == "true")
  {
  document.${formName}.MP_SELESAI.checked = true;
  }  
  else if(status == "false")
  {
  document.${formName}.MP_SELESAI.checked = false;
  }
  else
  {
  document.${formName}.MP_SELESAI.checked = false;
  }
      
  
	  if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.action = "?action2=saveSPTB";
	  document.${formName}.method = "POST";
	  
	 document.${formName}.submit();
  }
  function sendSPTB() {
	  if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.action = "?action2=sendSPTB";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function deleteSPTB() {
	  if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.action = "?action2=deleteSPTB";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function backSPTB(role) {
  
  //alert(role);
	//  document.${formName}.action = "?action2=";
	
	if(role=="PPT_ROLE" || role=="PPK_ROLE" )
	{
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMyInfoSPTB&action2=";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
	}
	else if(role=="SPTB_ROLE" || role=="INT_ROLE" )
	{
	  document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMyInfoSPTB&action2=";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
	
	}
	
	
  }
</script>