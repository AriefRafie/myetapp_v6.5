<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<!--
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
-->
<style type="text/css">
<!--
.pautan {color: #0000FF}
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {
}
.style38 {
}

.style41 {
	color: #FF0000;
	font-style:italic
}
.style42 {
	color: #0000FF;
}
.style43 {
	color: #0000FF;
}
.style44 {
	color: #FF0000;
}
.style69 {
}
.style72 {
	color: #000000
}
.style73 {
	color: #0000FF;
}
.style74 {
}
.style75 {
	color: #000000;
}
-->
</style>

</head> 
<!-- onload="submitForm()" -->
<!--
<body onload="submitForm();jeniswaktu2();check_kp();check_kp_lama();check_kp_lain();check_pengenalan_simati_1_onload();check_pengenalan_simati_2_onload();check_pengenalan_simati_3_onload();check_pengenalan_simati_4_onload()" >
-->
<body onload="submitForm();check_pilih();checkSebab();" >
<form id="form1" name="f1" method="post" action="">
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
  <!--
<a href="#" onclick="Effect.ScrollTo('txtNamaOBWaris').focus(); return false;" >
Click me</a>
-->
  <input type="hidden" name="v_tab" id="v_tab" value="" />
  <input type="hidden" name="bandar" id="bandar"/>
  <input type="hidden" name="simpanmode" id="simpanmode"/>
  <input type="hidden" name="up_in" id="up_in"/>
  #if($showdefaulttarikh=="yes")
  #set($txdTarikhMatiWaris = "")
  #set($txtWaktuKematianWaris = "")
  #end
  
  #if($Newwaris=="yes")
  #set($show_tambah_waris="")	
  #set($listpenting="")	
  #set($namaOB="")		    
  #set($nokpbaru1="")
  #set($nokpbaru2="")
  #set($nokpbaru3="")
  #set($nokpsaksi="")
  #set($jenisKp="")
  #set($nokplain="")
  #set($statusOB="")
  #set($notel="")
  #set($taraf="")	
  #set($bandar="")	
  #set($statusWaris="")
  #set($jantina="")
  #set($agama="") 
  #set($warga="")			    
  #set($umur="")					   	
  #set($dob="")	    
  #set($noberanak="")
  #set($alamat1="")
  #set($alamat2="")
  #set($alamat3="")
  #set($poskod="")				    
  #set($negeri="")
  
  #set($alamat1Surat="")
  #set($alamat2Surat="")
  #set($alamat3Surat="")
  #set($poskodSurat="")				    
  #set($negeriSurat="")
  #set($bandarSurat="")	
  
  #set($catatan="")			            
  #set($hp="")
  #set($tarikhmati="")
  #set($nokpwaris="")
  #set($checkmati="0")		            
  #set($waktumatiwaris="")
  #set($saudara="")
  
  #set($jeniswaktu="")
  
  
  #set($flag_dup_1 = "")
  #set($flag_dup_2 = "")
  #set($flag_dup_3 = "")
  #set($flag_dup_4 = "")
  #set($id_Orangberkepentingan = "")
  
  #end
  
  #foreach($list in $View)
  #set($noFail = $list.noFail)
  #set($idPemohon = $list.idPemohon)
  #end
  
  
  
  #parse("app/ppk/paging_sek8.jsp") 
  
  #parse("app/ppk/bil_fail.jsp")
  <table width="100%" border="0">
    <input type="hidden" name="command" value="">
    <input type="hidden" name="mode" value="">
    <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
    <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
    <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
    <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
    #foreach($list in $View)
    #set ($idPermohonan = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    #set ($id_Status = $list.id_Status)
    #set ($id_fail_carian = $list.idFail)
    #set ($noFail = $list.noFail)
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$idPermohonan"/>
    <input name="idPemohon" type="hidden"  value="$idPemohon"/>
    <input name="idSimati" type="hidden"  value="$idSimati"/>
    #set($id_Simati=$idSimati)
    <input name="idtemp" type="hidden"  value="$id"/>
    <input name="id_Suburusanstatus" type="hidden"  value="$list.id_Suburusanstatus"/>
    <input name="id_Suburusanstatusfail" type="hidden"  value="$list.id_Suburusanstatusfail"/>
    <input name="id_Permohonansimati" type="hidden"  value="$list.id_Permohonansimati"/>
    #set($id_Permohonansimati = $list.id_Permohonansimati)
    
    #set($listnoFail = $list.noFail)
    #set($listidnegeri = $list.idnegeri)
    #set($listnamadaerah = $list.namadaerah)
    #set($listnamaPejabat = $list.namaPejabat)
    #set($listketerangan = $list.keterangan)
    #set($listseksyen = $list.seksyen)
    #set($listtarikhMohon = $list.tarikhMohon)
    #set($listnamaSimati = $list.namaSimati )
    #set($listnamaPemohon = $list.namaPemohon)
    #set($listtarikhMohon = $list.tarikhMohon)
    #set($listidSimati = $list.idSimati)
    
    #end
    <tr>
      <td> #parse("app/ppk/syarat_kemaskini.jsp")
        #parse("app/ppk/maklumat_sek8.jsp") 
        
        
        #set($md=$listtarikhMohon)
        <input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
        <input type="hidden" name="txdTarikhMohon" id="txdTarikhMohon" value="$listtarikhMohon" />
        <input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
        <input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/>
      </td>
    </tr>
    <tr>
      <td><div id="TabbedPanels1" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3 TabbedPanelsTabSelected" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()" >PERMOHONAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(1,0,0,0);HtaamView()">HARTA TAK ALIH</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(2,0,0,0);HAview()" >HARTA ALIH</li>
            #if($!skrin_online != "yes")
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" >NILAIAN HARTA</li>
            #else
            #if($!hideTabPengesahan == "show")
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" >PENGESAHAN PERMOHONAN</li>
            #end
            #end
          </ul>
          <div class="TabbedPanelsContent">
            <div class="TabbedPanelsContentGroup">
              <div id="TabbedPanels2" class="TabbedPanelsContentVisible">
                <ul class="TabbedPanelsTabGroup">
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()">SIMATI</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,1,0,0);PemohonView()">PEMOHON</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,2,0,0);WarisView()" >WARIS</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,3,0,0);PentingView()">ORANG BERKEPENTINGAN</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,5,0,0);PemiutangView()">PEMIUTANG</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
                  #if($!skrin_online == "yes")
                  <li class="TabbedPanelsTab style1 style3 TabbedPanelsTabSelected" tabindex="0" onclick="setSelected(0,7,0,0);TukarPemohonView()" id="maklumat_pemohon">PERTUKARAN PEMOHON</li>
                   #end
                </ul>
                <div class="TabbedPanelsContentGroup">
                  <div class="TabbedPanelsContentVisible"></div>
                  <div class="TabbedPanelsContentVisible">
                    <div id="TabbedPanels3" class="TabbedPanelsContentVisible">
                      <div class="TabbedPanelsContentGroup">
                        <div class="TabbedPanelsContentVisible" ></div>
                        <div class="TabbedPanelsContentVisible" ></div>
                      </div>
                    </div>
                  </div>
                  <div class="TabbedPanelsContentVisible"> #parse("app/ppk/info_berjaya_disimpan.jsp")
                    
                    
                    <table width="100%">
                      #if($show_lapisan_berikut=="")                             
                      #if($show_waris_update=="yes")
                      
                      
                      
                      #foreach($lwu in $listWarisUpdate)
                      
                      #set($id_Pemohon = $lwu.id_Pemohon)
                      #set($idwarisup = $lwu.idwaris)
                      #set($txtIdSimatiWaris = $lwu.idSimati)
                      #set($txtNoKPBaru1Waris = $lwu.nokpbaru1)
                      #set($txtNoKPBaru2Waris = $lwu.nokpbaru2)
                      #set($txtNoKPBaru3Waris = $lwu.nokpbaru3)
                      #set($txtNoKPLamaWaris = $lwu.nokplama)
                      #set($socJenisKPLainWaris = $lwu.jeniskp)
                      #set($txtNoKPLainWaris = $lwu.nokplain)
                      #set($txtNamaOBWaris = $lwu.nama_Ob)
                      #set($txtNamaLainWaris = $lwu.nama_Lain)
                      #set($socJantinaWaris = $lwu.jantina)
                      #set($socAgamaWaris = $lwu.agama)
                      #set($socWarganegaraWaris = $lwu.warga)
                      #set($nama_warga = $lwu.nama_warga)
                      #set($txdTarikhLahirWaris = $lwu.dob)
                      #set($txtNoSuratBeranakWaris = $lwu.noberanak)
                      #set($txtUmurWaris = $lwu.umur)
                      #set($socStatusOBWaris = $lwu.status_Ob)
                      #set($socSaudaraWaris = $lwu.saudara)
                      #set($checkHidupWaris = $lwu.statushidup)
                      #set($txdTarikhMatiWaris = $lwu.tarikhmati)
                      #set($txtWaktuKematianWaris = $lwu.waktumati)
                      #set($txtAlamatTerakhir1WarisSurat = $lwu.alamat1Surat)
                      #set($txtAlamatTerakhir2WarisSurat = $lwu.alamat2Surat)
                      #set($txtAlamatTerakhir3WarisSurat = $lwu.alamat3Surat) #set($txtPoskodWarisSurat = $lwu.poskodSurat)
                      #set($txtBandarWarisSurat = $lwu.bandarSurat)
                      
                      #set($socNegeriWarisSurat = $lwu.idnegeriSurat)
                      #set($txtAlamatTerakhir1Waris = $lwu.alamat1)
                      #set($txtAlamatTerakhir2Waris = $lwu.alamat2)
                      #set($txtAlamatTerakhir3Waris = $lwu.alamat3)
                      #set($txtPoskodWaris = $lwu.poskod)
                      #set($txtBandarWaris = $lwu.bandar)
                      
                      #set($socNegeriWaris = $lwu.idnegeri)
                      #set($txtNoTeleponWaris = $lwu.noTel)
                      #set($txtNoTeleponBimbitWaris = $lwu.nohp)
                      #set($txtEmel = $lwu.emel)
                      #set($txtCatatanWaris = $lwu.catatan)
                      
                      #set($FLAG_DAFTAR = $lwu.FLAG_DAFTAR)
                      
                      #set($jeniswaktu = $lwu.jeniswaktu)
                      #set($id_Orangberkepentingan = $lwu.idwaris)
                      #set($id_ob_list_getListOBUpdate = $lwu.id_ob_list_getListOBUpdate)
                      
                      #set($nama_pelbagainegara_surat = $lwu.nama_pelbagainegara_surat)
                      #set($nama_pelbagainegara = $lwu.nama_pelbagainegara)
                      
                      
                      #end
                     
                      #end
                     
                      #if($show_hantar_btn == "yes")
		                      #foreach($list in $listCheckPertukaran)
			                      #set($id_Pemohonbaru = $list.id_pemohonbaru)
			                      #set($sebabTukar = $list.sebab_tukar)
			                      #set($tarikhMati = $list.tarikh_mati)
			                      
			                      #set ($check0 = "checked")
			                      
			                      #set ($setMode0 = "readonly") 
			                      #set ($setMode3 = "readonly") 
			                      
			                      #set ($setMode0 = "disabled") 
			                      #set ($setMode1 = "disabled") 
			                      #set ($setMode2 = "disabled")
			                      #set ($setMode3 = "disabled")
			                      
			                      #if($sebabTukar == "kematian")
			                      	#set ($check1 = "checked")
			                      #else
			                      	#set ($check2 = "checked")
			                      #end
		                      #end	
		                   #end
                      
                      #if($show_senarai_lapis_pertama == "yes")
                        <input name="id_Pemohonbaru" id="id_Pemohonbaru" type="hidden"  value="$!id_Pemohonbaru"/>
                        <input name="" id="" type="hidden"  value="$!tarikhMati"/>
	                      
                      <tr>
                      	<td>
                      		<fieldset>
								<legend>SENARAI WARIS</legend>
								
								<table width="100%" >
								 <tr>
								   <td width="100%"><div align="center">
								       <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" />
								       #if($listWaris.size()==0)
								       <table width="100%">
								         <tr class="table_header">
								           <td width="5%"><div align="center" >NO</div></td>
								           <td width="20%"><div align="left">NAMA WARIS</div></td>
								           <td width="15%"><div align="center">MyID BARU</div></td>
								           <td width="5%"><div align="center">UMUR</div></td>
								           <td width="20%"><div align="left">TALIAN PERSAUDARAAN</div></td>
<!-- 								           <td width="20%"><div align="center">STATUS</div></td> -->
								           <td width="15%"><div align="center"></div></td>
								         </tr>
								       </table>
								       <table width="100%">
								         <tr bgcolor="white">
								           <td align="left"> Tiada Rekod </td>
								         </tr>
								       </table>
								       #else
								       <table width="100%">
								         <tr class="table_header">
								           <td><div align="center" >NO</div></td>
								           <td><div align="left">NAMA WARIS</div></td>
								           <td><div align="center">MyID BARU</div></td>
								           <td><div align="center">UMUR</div></td>
								           <td><div align="left">TALIAN PERSAUDARAAN</div></td>
<!-- 								           <td><div align="center">STATUS</div></td> -->
								           <td><div align="center"></div></td>
								         </tr>
								         #set($nowa=0)
								         #foreach($listwaris in $listWaris)
								         <input type="hidden" name="id_permohonansimati" id="id_permohonansimati" value="$listwaris.idpermohonansimati"/>
								         #set($nowa=$nowa+1)
								         #if($nowa%2!=0)
								         <tr>
								           <td width="5%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
<!-- 								           <td width="20%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris('$listwaris.idwaris')" class="style42"> $listwaris.nama_Ob</a></div></td> -->

										   <td width="20%" class="row1"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.nama_Ob</div></td>
								           <td width="15%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.nokpbaru</div></td>
								           <td width="5%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.umur</div></td>
								           #if($listwaris.saudara=="")
								           #set($wariskodsaudara="")
								           #set($warissaudaraketerangan="" )
								           #else
								           
								           
								           #foreach($listsaudaralist in $listsaudara)
								           
								           #if($listwaris.saudara==$listsaudaralist.id_Saudara)
								           
								           #set($wariskodsaudara=$listsaudaralist.kod)
								           #set($warissaudaraketerangan=$listsaudaralist.keterangan)
								           
								           
								           
								           #end    
								           #end
								           #end
								           <td width="20%" class="row1"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
								           #if($listwaris.statushidup=="0")
								           #set($hidup="Masih Hidup")
								           #end
								           #if($listwaris.statushidup=="1")
								           #set($hidup="Sudah Meninggal")
								           #end
								           #if($listwaris.statushidup=="")
								           #set($hidup="")
								           #end
<!-- 								           <td width="20%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td> -->
								           <td width="15%" class="row1"><div align="center" class="style72"><input type="radio" id="id_ob_pemohon" name="id_ob_pemohon" value="$listwaris.idwaris" $check0 $setMode0>
								           																	<input name="setMode0" id="setMode0" type="hidden" value="$setMode0" ></div></td>
								         </tr>
								         #else
								         <tr class="table_header">
								           <!-- 
								                  <td><div align="center"><a href="javascript:edit_item_waris('$listwaris.idwaris', '$listwaris.nama_Ob', '$listwaris.nokpbaru1','$listwaris.nokpbaru2','$listwaris.nokpbaru3','$listwaris.idSimati','$listwaris.nokplama','$listwaris.jeniskp','$listwaris.nokplain','$listwaris.idnegeri','$listwaris.noTel','$listwaris.jantina','$listwaris.alamat1','$listwaris.alamat2','$listwaris.alamat3','$listwaris.bandar','$listwaris.agama','$listwaris.catatan','$listwaris.warga','$listwaris.poskod','$listwaris.statushidup','$listwaris.tarikhmati','$listwaris.waktumati','$listwaris.nohp','$listwaris.status_Ob','$listwaris.dob','$listwaris.saudara','$listwaris.umur','$show_table_waris')"> $listwaris.nama_Ob</a></div>
								                  -->
								           <td width="5%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
<!-- 								           <td width="20%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris('$listwaris.idwaris')" class="style43"> $listwaris.nama_Ob</a></div></td> -->
										   <td width="20%" class="row2"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.nama_Ob</div></td>
								           <td width="15%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.nokpbaru</div></td>
								           <td width="5%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.umur</div></td>
								           #foreach($listsaudaralist in $listsaudara)
								           
								           #if($listwaris.saudara==$listsaudaralist.id_Saudara)
								           
								           #set($wariskodsaudara=$listsaudaralist.kod)
								           #set($warissaudaraketerangan=$listsaudaralist.keterangan)
								           
								           
								           #end    
								           #end
								           <td width="20%" class="row2"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
								           #if($listwaris.statushidup=="0")
								           #set($hidup="Masih Hidup")
								           #end
								           #if($listwaris.statushidup=="1")
								           #set($hidup="Sudah Meninggal")
								           #end
								           #if($listwaris.statushidup=="")
								           #set($hidup="")
								           #end
<!-- 								           <td width="20%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td> -->
								           <td width="15%" class="row2"><div align="center" class="style72"><input type="radio" id="id_ob_pemohon" name="id_ob_pemohon" value="$listwaris.idwaris" $check0 $setMode0>
								           																	<input name="setMode0" id="setMode0" type="hidden" value="$setMode0" ></div></td>
								          </tr>
								          #end
								          
								          
								          
								          
								          #end
								        </table>
								        #end </div></td>
								  </tr>
								</table>
							</fieldset>
                          #end 
                      	</td>
                      </tr>
                      
                      <!--  syafiqah add -->
<!--                       <tr> -->
<!--                       	<td> -->
<!--                       		<fieldset> -->
<!--                       			<legend>MAKLUMAT PENGGANTIAN</legend> -->
<!--                       		</fieldset> -->
<!--                       	</td> -->
<!--                       </tr> -->
                      
                      <tr>
                      	<td>
                      	<fieldset>
                      		<legend>SEBAB PENGGANTIAN</legend>
                      		 <table width="100%"  cellpadding="1" cellspacing="2" border="0">
                      		 	<input type="hidden" name="noFail" id="noFail" value="$noFail"></input>
                      		 	<input type="hidden" name="idSimati" id="idSimati" value="$idSimati"></input>
                      		 	<input type="hidden" name="idPermohonansimati" id="idPermohonansimati" value="$id_Permohonansimati"></input>
                      		 	<input name="idPermohonan" type="hidden"  value="$idPermohonan"/>
                      		 	#if($sebabTukar == "kematian")
			                    	#set ($check1 = "checked")
			                    #elseif($sebabTukar == "kesihatan")
			                    	#set ($check2 = "checked")
			                    #else
			                    	#set ($check1 = "")
			                    	#set ($check2 = "")
			                    #end
                      		 	<tr>
     								<td width="4%"><input type="radio" name="sebab" id="kematian" value="kematian" $check1 $setMode1 onchange="toggleDisplay('ckematian')">
     									</td>
     								<td>KEMATIAN</td>
     								<td></td>
     							</tr>
     							#if($show_hantar_btn == "")
     							<tr id="ftarikhmati" style="display:none;">
     								<td></td>
     								<td><span class="style44">*</span> TARIKH KEMATIAN :&nbsp;<input type="text" name="tarikh_mati" id="tarikh_mati" value="$!tarikhMati"> <a href="javascript:displayDatePicker('tarikh_mati',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a></td>
     								<td></td>
     							</tr>
     							<tr id="buktikematian" style="display:none;">
								     <td></td>
								     <td><span class="style44">*</span> DOKUMEN SOKONGAN : <input id="fileupload" name="fileupload" type="file" value="Lampiran" size="40" onClick="lampiran('$!idPermohonan','dokumenSokongan')" />	
								     #if($lampirans != "")
									 	<input type="text" name="namaDoc1" value="1" />
									 #else
									 	<input type="text" name="namaDoc1" value="0" />
									 #end
								     $!lampirans
								     </td>
								     <td></td>
      							</tr>
      							#elseif($show_hantar_btn == "yes" && $sebabTukar == "kematian")
      							<tr>
     								<td></td>
     								<td>TARIKH KEMATIAN :&nbsp;<input type="text" value="$!tarikhMati" $setMode3> <a href="javascript:displayDatePicker('tarikh_mati',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a></td>
     								<td></td>
     							</tr>
     							<tr>
								     <td></td>
								     <td>DOKUMEN SOKONGAN : $!lampirans
								     </td>
								     <td></td>
      							</tr>
      							#end
     							<tr>
	     							<td><input type="radio" name="sebab" id="kesihatan" value="kesihatan" $check2 $setMode2 onchange="toggleDisplay('ckesihatan')"></td>
	     							<td>MASALAH KESIHATAN</td>
	     							<td></td>
    							 </tr>
    							 #if($show_hantar_btn == "")
    							 <tr id="buktikesihatan" style="display:none;">
								     <td></td>
								     <td><span class="style44">*</span> DOKUMEN SOKONGAN : <input id="fileupload" name="fileupload" type="file" value="Lampiran" size="40" onClick="lampiran('$!idPermohonan','dokumenSokongan')" />
								     #if($lampirans != "")
									 	<input type="text" name="namaDoc2" value="1" />
									 #else
									 	<input type="text" name="namaDoc2" value="0" />
									 #end
								     $!lampirans
								     </td>
								     <td></td>
      							</tr>
      							#elseif($show_hantar_btn == "yes" && $sebabTukar == "kesihatan")
    							 <tr>
								     <td></td>
								     <td>DOKUMEN SOKONGAN : $!lampirans
								     </td>
								     <td></td>
      							</tr>
      							#end
      							
      						 #if($show_hantar_btn == "yes")
      						 	<tr align="center">
									<td></td>
									<td>  
									      <a href="#" onClick="javascript:cetakBorangAA('$!idPermohonan','$!id_fail_carian')">
									       	<input type="button" value="Cetak Borang AA" />
									      </a>
									      <a href="#" onClick="javascript:cetakSuratPengesahan('$!idPermohonan','$!id_fail_carian')">
									       	<input type="button" value="Cetak Surat Pengesahan" />
									      </a>
<!-- 									      <font color="red"><b>Cetak Borang AA</b></font> -->
									</td>
     							</tr>
     						#else
     						
     						 	#if($id_Status != "21")
     						 		#if($listWaris.size()==1)
	     							<tr align="center">
										<td></td>
										<td>  
										      <font color="red"><b>Anda adalah pemohon tunggal. Pertukaran pemohon tidak dibenarkan.</b></font>
										</td>
	     							</tr>
	     							#else
								    <tr align="center">
										<td></td>
										<td>
											<input name="cmdSimpanKPOb" id="cmdSimpanKPOb" value="Hantar" type="button" onClick="javascript:testSimpan('$idSimati','$id_Permohonansimati','$id_fail_carian','$noFail','cmdSimpanKPOb')">
										</td>
	     							</tr>
	     							#end
     							#else
     							<tr align="center">
									<td></td>
									<td>  
									      <font color="red"><b>Fail ini berstatus selesai. Pertukaran pemohon tidak dibenarkan.</b></font>
									</td>
     							</tr>
     							#end
     						
     						#end	
     							
     						</table>
          				</fieldset> 
                      	</td>
                      </tr>
                      <!-- syafiqah add ends -->
                      #end  
                      
                      
                      #if($show_lapisan_berikut=="yes")
                      
                      
                      
                      #if($show_lapisan_berikut_update=="yes")
                      #foreach($lwu in $listWarisLapisanUpdate)
                      
	                      #set($id_Pemohon = $lwu.id_Pemohon)
	                      #set($idwarisup = $lwu.idwaris)
	                      #set($txtIdSimatiWaris = $lwu.idSimati)
	                      #set($txtNoKPBaru1Waris = $lwu.nokpbaru1)
	                      #set($txtNoKPBaru2Waris = $lwu.nokpbaru2)
	                      #set($txtNoKPBaru3Waris = $lwu.nokpbaru3)
	                      #set($txtNoKPLamaWaris = $lwu.nokplama)
	                      #set($socJenisKPLainWaris = $lwu.jeniskp)
	                      #set($txtNoKPLainWaris = $lwu.nokplain)
	                      #set($txtNamaOBWaris = $lwu.nama_Ob)
	                      #set($txtNamaLainWaris = $lwu.nama_Lain)
	                      #set($socJantinaWaris = $lwu.jantina)
	                      #set($socAgamaWaris = $lwu.agama)
	                      #set($socWarganegaraWaris = $lwu.warga)
	                      #set($nama_warga = $lwu.nama_warga)
	                      #set($txdTarikhLahirWaris = $lwu.dob)
	                      #set($txtNoSuratBeranakWaris = $lwu.noberanak)
	                      #set($txtUmurWaris = $lwu.umur)
	                      #set($socStatusOBWaris = $lwu.status_Ob)
	                      #set($socSaudaraWaris = $lwu.saudara)
	                      #set($checkHidupWaris = $lwu.statushidup)
	                      #set($txdTarikhMatiWaris = $lwu.tarikhmati)
	                      #set($txtWaktuKematianWaris = $lwu.waktumati)
	                      #set($txtAlamatTerakhir1WarisSurat = $lwu.alamat1Surat)
	                      #set($txtAlamatTerakhir2WarisSurat = $lwu.alamat2Surat)
	                      #set($txtAlamatTerakhir3WarisSurat = $lwu.alamat3Surat)
	                      #set($txtPoskodWarisSurat = $lwu.poskodSurat)
	                      #set($txtBandarWarisSurat = $lwu.bandarSurat) 
	                      #set($socNegeriWarisSurat = $lwu.idnegeriSurat)
	                      #set($txtAlamatTerakhir1Waris = $lwu.alamat1)
	                      #set($txtAlamatTerakhir2Waris = $lwu.alamat2)
	                      #set($txtAlamatTerakhir3Waris = $lwu.alamat3)
	                      #set($txtPoskodWaris = $lwu.poskod)
	                      #set($txtBandarWaris = $lwu.bandar)
	                      
	                      #set($socNegeriWaris = $lwu.idnegeri)
	                      #set($txtNoTeleponWaris = $lwu.noTel)
	                      #set($txtNoTeleponBimbitWaris = $lwu.nohp)
	                      #set($txtEmel = $lwu.emel)
	                      #set($txtCatatanWaris = $lwu.catatan)
	                      #set($jeniswaktu = $lwu.jeniswaktu)
	                      
	                      #set($FLAG_DAFTAR = $lwu.FLAG_DAFTAR)
	                      
	                      
	                      
	                      #set($id_Orangberkepentingan = $lwu.idwaris)
	                      
	                      #set($id_ob_list_getListOBUpdate = $lwu.id_ob_list_getListOBUpdate)
	                      
	                      
	                      #set($nama_pelbagainegara_surat = $lwu.nama_pelbagainegara_surat)
	                      #set($nama_pelbagainegara = $lwu.nama_pelbagainegara)
	                      
                      #end
                     
                      #end
                      
                      
                      
                      
                      #if($show_button_lapisan=="yes")
                      
                      #foreach($lwu in $listWarisLapisanUpdate)
                      #end
                      
                      #end
                     
                      #end
                     
                    </table>
                  </div>
<!--                   <div class="TabbedPanelsContent"></div> -->
<!--                   <div class="TabbedPanelsContent"></div> -->
<!--                   <div class="TabbedPanelsContent"></div> -->
<!--                   <div class="TabbedPanelsContent"></div> -->
                </div>
              </div>
            </div>
<!--             <div class="TabbedPanelsContent"> -->
<!--               <div id="TabbedPanels4" class="TabbedPanelsContentVisible"> -->
<!--                 <div class="TabbedPanelsContentGroup"> -->
<!--                   <div class="TabbedPanelsContent"></div> -->
<!--                   <div class="TabbedPanelsContent"></div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
          </div>
        </div></td>
    </tr>
  </table>
  <table width="100%">
    <tr> #parse("app/ppk/paging_sek8.jsp") </tr>
    <input name="eventStatus" id="eventStatus" type="hidden" />
    <input name="flag_dup_1" id="flag_dup_1" value="$!flag_dup_1" type="hidden" />
    <input name="flag_dup_2" id="flag_dup_2" value="$!flag_dup_2" type="hidden" />
    <input name="flag_dup_3" id="flag_dup_3" value="$!flag_dup_3" type="hidden" />
    <input name="flag_dup_4" id="flag_dup_4" value="$!flag_dup_4" type="hidden" />
    <input name="id_Orangberkepentingan" id="id_Orangberkepentingan" type="hidden"  value="$!id_Orangberkepentingan" />
    <input name="jenis_ob" id="jenis_ob" value="waris" type="hidden" />
    <input name="check_no_kp_baru_simati" id="check_no_kp_baru_simati" type="hidden"  />
    <input name="check_no_kp_lama_simati" id="check_no_kp_lama_simati" type="hidden"  />
    <input name="check_no_kp_lain_simati" id="check_no_kp_lain_simati" type="hidden"  />
  </table>
  #parse("app/ppk/headerppk_script.jsp")
</form>
</body>
<script>



function capaianIdentityWaris() {
	document.f1.command.value = "capaianIdentityWaris";
	document.f1.action = "";
	document.f1.submit();
}

selectPelbagaiNegara(document.f1.socNegeriWaris.value,'div_mesejpelbagagainegara','tr_pelbagainegara','nama_pelbagainegara');									
selectPelbagaiNegara(document.f1.socNegeriWarisSurat.value,'div_mesejpelbagagainegara_surat','tr_pelbagainegara_surat','nama_pelbagainegara_surat');

alamatwarga(document.f1.socWarganegaraWaris.value,'alamatwarga','tr_nama_warga','nama_warga');

// function capaianMyIdentityWaris() {
// 	document.f1.command.value = "capaianMyIdentityWaris";
// 	document.f1.action = "";
// 	document.f1.submit();
// }

function open_new_window() 
{
 var width  = 300;
 var height = 100; 
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
//new_window = open("","hoverwindow",params);
new_window = open("","title",params);
new_window.document.open();

new_window.document.write("<html><title>JavaScript New Window</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
new_window.document.write("Jika <b>Pemohon</b> adalah bukan waris lapisan pertama. Sila pilih nama waris kepada pemohon yang sudah meninggal.");



new_window.document.write("<br>");
new_window.document.write("</body></html>");
new_window.document.close(); 
}


function close_window() 
{
new_window.close();
}

<!-- TAB -->
function HtaamView() {
	document.f1.action = "";
	document.f1.mode.value = "Htaamview";
	document.f1.command.value = "Htaam";
	document.f1.submit();
}
function HAview() {
	document.f1.action = "";
	document.f1.mode.value = "view_harta_alih";
	document.f1.command.value = "harta_alih";
	document.f1.submit();
}

function NAview() {
	document.f1.action = "";
	document.f1.mode.value = "view_nilai_harta";
	document.f1.command.value = "nilai_harta";
	document.f1.submit();
}

<!-- syafiqah add -->
function TukarPemohonView(){
	document.f1.action = "";
	document.f1.mode.value = "TukarPemohonview";
	document.f1.command.value = "Tukarpemohon";
	document.f1.submit();
}
<!-- syafiqah add end -->

function PenghutangView() {
	document.f1.action = "";
	document.f1.mode.value = "Penghutangview";
	document.f1.command.value = "Penghutang";
	document.f1.submit();
}
function PemiutangView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemiutangview";
	document.f1.command.value = "Pemiutang";
	document.f1.submit();
}
function SaksiView() {
	document.f1.action = "";
	document.f1.mode.value = "Saksiview";
	document.f1.command.value = "Saksi";
	document.f1.submit();
}

function PentingView() {
	document.f1.action = "";
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
	document.f1.submit();
}

function WarisView() {
	document.f1.action = "";
	document.f1.mode.value = "Warisview";
	document.f1.command.value = "Waris";
	document.f1.submit();
}
function SimatiView() {
	document.f1.action = "";
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.submit();
	
}

function PemohonView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
	document.f1.submit();
}
function kembali_simati(){
	
	document.f1.command.value = "kembali_simati";
	document.f1.action = "";
	document.f1.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.f1.tabIdatas.value = tabIdatas;
    document.f1.tabIdtengah.value = tabIdtengah;
    document.f1.tabIdbawah.value = tabIdbawah;	
	document.f1.tabIdtepi.value = tabIdtepi;	
}
function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
if (input_box == true) {

document.f1.action = "";
	document.f1.mode.value = "Tambah_lapisan_berikut";
	document.f1.command.value = "Waris";
	document.f1.submit();
//document.f1.txtNoKPBaru1Waris.focus();
}
else
{return;}
}

<!-- WARIS -->
function tarikh_waris_lapisan(v_t)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_lapisan";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	document.f1.submit();
}


function tarikh_waris_lapisan_saudara(v_t)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_lapisansaudara";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	document.f1.submit();
}




function tarikh_waris(v_t,md)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
}

function tarikh_waris_lap(v_t,md)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
    document.f1.submit();
}

function tarikh_waris_saudara(v_t,md)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikhsaudara";
	document.f1.command.value = "Waris";
	document.f1.simpanmode.value = "get_saudara";	
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
}
/*
function tarikh_waris_saudara_1up(v_t,md)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikhsaudara";
	document.f1.command.value = "Waris";
	document.f1.simpanmode.value = "get_saudara";	
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
}
*/
function tarikh_waris_saudara_copy(v_t,md)
 {
	document.f1.action = "";
	if(document.f1.copyAlamat.checked == true)
	{
	document.f1.mode.value = "Waristarikhsaudara";
	document.f1.command.value = "Waris";
	document.f1.simpanmode.value = "copy1_true";	
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
	}
	
	if(document.f1.copyAlamat.checked == false)
	{
	document.f1.mode.value = "Waristarikhsaudara";
	document.f1.command.value = "Waris";
	document.f1.simpanmode.value = "copy1_false";	
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
	}
	
}



function tarikh_waris_saudara_copyP(v_t,md)
 {
	document.f1.action = "";
	if(document.f1.copyAlamatP.checked == true)
	{
	document.f1.mode.value = "WaristarikhsaudaraP";
	document.f1.command.value = "Waris";
	document.f1.simpanmode.value = "copy1_true";	
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
	}
	
	if(document.f1.copyAlamatP.checked == false)
	{
	document.f1.mode.value = "WaristarikhsaudaraP";
	document.f1.command.value = "Waris";
	document.f1.simpanmode.value = "copy1_false";	
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
	}
	
}



function tarikh_waris_saudara_negeritetap(v_t,md)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikhsaudara";
	document.f1.command.value = "Waris";
	document.f1.simpanmode.value = "get_negeritetap";	
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
}

function tarikh_waris_saudara_negerisurat(v_t,md)
 {
   
	document.f1.action = "";
	document.f1.mode.value = "Waristarikhsaudara";
	document.f1.command.value = "Waris";
	document.f1.simpanmode.value = "get_negerisurat";	
	document.f1.v_tab.value = v_t;
	document.f1.up_in.value = md;
	document.f1.submit();
}

function tarikh_waris_update(v_t)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_update";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	
	document.f1.submit();
}
function tarikh_waris_update_saudara(v_t)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_updatesaudara";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	document.f1.submit();
}
function tarikh_waris_lapisan_update(v_t)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_lapisan_update";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	document.f1.submit();
}
function tarikh_waris_lapisan_update_saudara(v_t)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_lapisan_updatesaudara";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	document.f1.submit();
}
function new_waris() {
	document.f1.action = "";
	document.f1.mode.value = "Newwaris";
	document.f1.command.value = "Waris";
	document.f1.submit();
}
function get_waris(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Getwaris";
	document.f1.command.value = "Waris";
	document.f1.idwaris.value = idw;
	document.f1.submit();
}



function lapisan_sebelum() {
	document.f1.action = "";
	document.f1.mode.value = "Lapisan_sebelum";
	document.f1.command.value = "Waris";
	
	document.f1.submit();
}
function get_waris_lapisan(idw,idp) {
	document.f1.action = "";
	document.f1.mode.value = "GetwarisLapisan";
	document.f1.command.value = "Waris";
	document.f1.idwarislapis.value = idw;
    document.f1.idparentlapis.value = idp;
	document.f1.submit();
}
function get_waris_lapisan_X(idw,idp) {
	document.f1.action = "";
	document.f1.mode.value = "GetwarisLapisanX";
	document.f1.command.value = "Waris";
	document.f1.idwarislapisX.value = idw;
    document.f1.idparentlapisX.value = idp;
	document.f1.submit();
}
function lapisan_waris() {
	document.f1.action = "";
	document.f1.mode.value = "Lapisan_berikut";
	document.f1.command.value = "Waris";
	document.f1.submit();
}

function lapisan_waris_lapisan() {
	document.f1.action = "";
	document.f1.mode.value = "Lapisan_berikut_lapisan";
	document.f1.command.value = "Waris";
	
	document.f1.submit();
}


function lapisan_waris_sebelum(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Lapisan_sebelum_lapisan";
	document.f1.command.value = "Waris";
	document.f1.txtIdParent.value = idw;
	document.f1.submit();
}

function tambah_lapisan_berikut() {
	document.f1.action = "";
	document.f1.mode.value = "Tambah_lapisan_berikut";
	document.f1.command.value = "Waris";
	document.f1.submit();
}

function Cancel_Baru() {
input_box = confirm("Adakah anda pasti?");
if (input_box == true) {

	document.f1.action = "";
	document.f1.mode.value = "Newwaris";
	document.f1.command.value = "Waris";
	document.f1.submit();
	
	}
	else
	{return;}
}



function tambah_lapisan(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Tambah_lapisan";
	document.f1.command.value = "Waris";
	document.f1.idwaris.value = idw;
	document.f1.submit();
}
function batal_waris_lapisan_Simpan() {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	 document.f1.mode.value = "kemaskini_waris_lapisan";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	}
}

function tambah_waris_lapisan_Simpan() {



    	
var negeri_code = document.f1.txtNoKPBaru2Waris.value;
var dob_code = document.f1.txtNoKPBaru1Waris.value;
if(dob_code.charAt(0)<3)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	 var date_dob = new Date(yr_dob, mon_dob, dt_dob);










    var ch="";	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;	
	
	
	if(document.f1.checkHidupWaris.checked == true && document.f1.txdTarikhMatiWaris.value != "" )
	{
	var str3  = document.getElementById("txdTarikhMatiWaris").value;
	var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10)-1;
    var yr3   = parseInt(str3.substring(6,10),10);	
	var date3 = new Date(yr3, mon3, dt3);	
	ch="yes";	
	}
	
	
	var dat1=document.f1.txdTarikhLahirWaris
    var str1  = document.getElementById("txdTarikhLahirWaris").value;
    var str2  = document.getElementById("txdTarikhMohon").value;
   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10)-1;
    var yr2   = parseInt(str2.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);
    var date2 = new Date(yr2, mon2, dt2);
   
   
   
	
	if( document.f1.tambahwarislapisanSimpan.value == "Simpan" ) 
	{
	if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}	
		else if(document.f1.socJantinaWaris.value == ""){
			alert('Sila plih " Jantina " terlebih dahulu.');

	  		document.f1.socJantinaWaris.focus(); 
			return; 
		}		
		else if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');

	  		document.f1.socSaudaraWaris.focus(); 
			return; 
		}
		
		
		else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoSuratBeranakWaris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0")  && document.f1.txtNoKPLainWaris.value=="")
		 {
		alert("Sila masukkan salah satu nombor MyID atau nombor surat beranak waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		
		else if(document.f1.txtNoKPLamaWaris.value != "" && document.f1.txtNoKPLamaWaris.value != "TDK" && document.f1.txtNoKPLamaWaris.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Waris");
		document.f1.txtNoKPLamaWaris.focus();	
	}
		
		
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID lain simati");
		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis MyID lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}
	
	
	else if (document.f1.txtPoskodWaris.value != "" && document.f1.txtPoskodWaris.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodWaris.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txdTarikhMatiWaris.value == "") {
		alert("Sila masukkan tarikh mati");
		document.f1.txdTarikhMatiWaris.focus();
		return; 
	}
	/*
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && document.f1.txtWaktuKematianWaris.value.length < 4) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 1) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >2 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}/*	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(2) > 5) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(3) > 9) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	//txdTarikhMatiWaris
	//txdTarikhLahirWaris	
	else if (document.f1.txtNoKPBaru1Waris.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1Waris.focus()
		return false
	}


/*
else if (document.f1.txtNoKPBaru2Waris.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaru2Waris.focus()
	return;
	
	}
	*/
	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return false
	}
	
	else if (document.f1.checkHidupWaris.checked == true  && document.f1.txdTarikhMatiWaris.value!="" && isDate(document.f1.txdTarikhMatiWaris.value)==false)
	{
	
    document.f1.txdTarikhMatiWaris.focus();
	return false;
	
	}
/*	 else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirWaris.focus();
   } */
   /*
   else if(ch=="yes" && date3 > date2 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   */
   else if(ch=="yes" && date3 < date1 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh lahir waris.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   
    else if (document.f1.txtUmurWaris.value == "")
	{
	alert("Sila masukkan umur waris");
	document.f1.txtUmurWaris.focus()
	return;
	}
	
	else if (document.f1.txtPoskodWarisSurat.value != "" && document.f1.txtPoskodWarisSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod untuk alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodWarisSurat.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1Waris.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWaris.value == ""  || document.f1.socNegeriWaris.value == "" || document.f1.socNegeriWaris.value == "0"))))
	{
	alert("Sila lengkapkan alamat tetap waris");
	document.f1.txtAlamatTerakhir1Waris.focus()
	return;
	}
	
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}

		else if (document.f1.checkHidupWaris.checked == false 
				&& (document.f1.txtNoTeleponBimbitWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "") )
	{
	
	alert("Sila pastikan no telefon bimbit diisi");
	document.f1.txtNoTeleponBimbitWaris.focus()
	return;
	
	}
   
		else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtEmel.value == "" && document.f1.txtEmel.value == "") )
		{
		
		alert("Sila pastikan emel diisi");
		document.f1.txtEmel.focus()
		return;
		}
	
   else if (document.f1.socStatusOBWaris.value == 0 || document.f1.socStatusOBWaris.value == "")
	{
	
	alert("Sila pastikan salah satu status waris dipilih");
	document.f1.socStatusOBWaris.focus()
	return;
	
	}
	
	/* else if (document.f1.flag_dup_1.value == 'yes' )
	{	
	alert("No MyID baru waris telah wujud!");
	document.f1.txtNoKPBaru1Waris.focus()
	return;	
	} */
	else if (document.f1.flag_dup_2.value == 'yes' )
	{	
	alert("No MyID lama waris telah wujud!");
	document.f1.txtNoKPLamaWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_3.value == 'yes' )
	{	
	alert("MyID Lain waris telah wujud!");
	document.f1.txtNoKPLainWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_4.value == 'yes' )
	{	
	alert("No surat beranak waris telah wujud!");
	document.f1.txtNoSuratBeranakWaris.focus()
	return;	
	}
	else{
	//	alert(document.f1.flag_dup_1.value+""+document.f1.flag_dup_2.value+""+document.f1.flag_dup_3.value)
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
    document.f1.mode.value = "tambah_waris_lapisan";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	}
	else
	{return;}  
	
	
	}
	
	
	
	
	
	
	}
	
	

}

function tambah_waris_lapisan() {
    var negeri_code = document.f1.txtNoKPBaru2Waris.value;
var dob_code = document.f1.txtNoKPBaru1Waris.value;
if(dob_code.charAt(0)<3)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}
	
	var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	 var date_dob = new Date(yr_dob, mon_dob, dt_dob);




    var ch="";	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;	
	if(document.f1.checkHidupWaris.checked == true && document.f1.txdTarikhMatiWaris.value != "" )
	{
	var str3  = document.getElementById("txdTarikhMatiWaris").value;
	var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10)-1;
    var yr3   = parseInt(str3.substring(6,10),10);	
	var date3 = new Date(yr3, mon3, dt3);	
	ch="yes";	
	}
	var dat1=document.f1.txdTarikhLahirWaris
    var str1  = document.getElementById("txdTarikhLahirWaris").value;
    var str2  = document.getElementById("txdTarikhMohon").value;
   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10)-1;
    var yr2   = parseInt(str2.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);
    var date2 = new Date(yr2, mon2, dt2);
	
	
	if( document.f1.tambahwarislapisan.value == "Simpan" ) 
	{
	if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}	
		else if(document.f1.socJantinaWaris.value == ""){
			alert('Sila plih " Jantina " terlebih dahulu.');

	  		document.f1.socJantinaWaris.focus(); 
			return; 
		}		
		else if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');

	  		document.f1.socSaudaraWaris.focus(); 
			return; 
		}
		
		
		else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoSuratBeranakWaris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value=="")
		 {
	alert("Sila masukkan salah satu nombor MyID atau nombor surat beranak waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		
		else if(document.f1.txtNoKPLamaWaris.value != "" && document.f1.txtNoKPLamaWaris.value != "TDK" && document.f1.txtNoKPLamaWaris.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Waris");
		document.f1.txtNoKPLamaWaris.focus();	
	}
		
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID lain simati");

		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis MyID lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}
	
	
	else if (document.f1.txtPoskodWaris.value != "" && document.f1.txtPoskodWaris.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodWaris.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txdTarikhMatiWaris.value == "") {
		alert("Sila masukkan tarikh mati");
		document.f1.txdTarikhMatiWaris.focus();
		return; 
	}
	/*
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && document.f1.txtWaktuKematianWaris.value.length < 4) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 1) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >2 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	/*
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(2) > 5) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(3) > 9) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
else if (document.f1.txtNoKPBaru1Waris.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1Waris.focus()
		return false
	}

/*

else if (document.f1.txtNoKPBaru2Waris.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaru2Waris.focus()
	return;
	
	}
	*/	
	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return false
	}
	
	else if (document.f1.checkHidupWaris.checked == true  && document.f1.txdTarikhMatiWaris.value!="" && isDate(document.f1.txdTarikhMatiWaris.value)==false)
	{
	
    document.f1.txdTarikhMatiWaris.focus();
	return false;
	
	}
	/* else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirWaris.focus();
   } */
   /*
   else if(ch=="yes" && date3 > date2 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   */
   else if(ch=="yes" && date3 < date1 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh lahir waris.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   else if (document.f1.txtUmurWaris.value == "")
	{
	alert("Sila masukkan umur waris");
	document.f1.txtUmurWaris.focus()
	return;
	}
	
	else if (document.f1.txtPoskodWarisSurat.value != "" && document.f1.txtPoskodWarisSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod untuk alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodWarisSurat.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1Waris.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWaris.value == ""  || document.f1.socNegeriWaris.value == "" || document.f1.socNegeriWaris.value == "0"))))
	{
	alert("Sila lengkapkan alamat tetap waris");
	document.f1.txtAlamatTerakhir1Waris.focus()
	return;
	}
	
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWarisSurat.value == ""  || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	
	else if (document.f1.checkHidupWaris.checked == false 
			&& (document.f1.txtNoTeleponBimbitWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "") )
	{
	
	alert("Sila pastikan no telefon bimbit diisi");
	document.f1.txtNoTeleponBimbitWaris.focus()
	return;
	}
	
	else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtEmel.value == "" && document.f1.txtEmel.value == "") )
	{
	
	alert("Sila pastikan emel diisi");
	document.f1.txtEmel.focus()
	return;
	}
	
	 else if (document.f1.socStatusOBWaris.value == 0 || document.f1.socStatusOBWaris.value == "")
	{
	
	alert("Sila pastikan salah satu status waris dipilih");
	document.f1.socStatusOBWaris.focus()
	return;
	
	}
	
	else if (document.f1.flag_dup_1.value == 'yes' )
	{	
	alert("No MyID baru waris telah wujud!");
	document.f1.txtNoKPBaru1Waris.focus()
	return;	
	}
	else if (document.f1.flag_dup_2.value == 'yes' )
	{	
	alert("No MyID lama waris telah wujud!");
	document.f1.txtNoKPLamaWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_3.value == 'yes' )
	{	
	alert("MyID Lain waris telah wujud!");
	document.f1.txtNoKPLainWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_4.value == 'yes' )
	{	
	alert("No surat beranak waris telah wujud!");
	document.f1.txtNoSuratBeranakWaris.focus()
	return;	
	}
	
	else
	{
//	alert(document.f1.flag_dup_1.value+""+document.f1.flag_dup_2.value+""+document.f1.flag_dup_3.value)
	
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.mode.value = "simpan_waris_lapisan";
	document.f1.command.value = "Waris";	
	document.f1.action = "";
	document.f1.submit();
	}
	else
	{return;}

	}
	}
   
    if( document.f1.tambahwarislapisan.value == "Kemaskini" ) 
	{

    document.f1.mode.value = "kemaskini_waris_lapisan";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	
	}
	


	
	
	
	
	
}



    function warisbatalupdate()
    {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
     document.f1.mode.value = "kemaskini_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
    }
}

function tambah_waris_SimpanX(){


var negeri_code = document.f1.txtNoKPBaru2Waris.value;
var dob_code = document.f1.txtNoKPBaru1Waris.value;
if(dob_code.charAt(0)<3)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	 var date_dob = new Date(yr_dob, mon_dob, dt_dob);


 var ch="";	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;	
	if(document.f1.checkHidupWaris.checked == true && document.f1.txdTarikhMatiWaris.value != "" )
	{
	var str3  = document.getElementById("txdTarikhMatiWaris").value;
	var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10)-1;
    var yr3   = parseInt(str3.substring(6,10),10);	
	var date3 = new Date(yr3, mon3, dt3);	
	ch="yes";	
	}
	var dat1=document.f1.txdTarikhLahirWaris
    var str1  = document.getElementById("txdTarikhLahirWaris").value;
    var str2  = document.getElementById("txdTarikhMohon").value;
   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10)-1;
    var yr2   = parseInt(str2.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);
    var date2 = new Date(yr2, mon2, dt2);
	
	

	if( document.f1.tambahwarisSimpan.value == "Simpan" ) 
	{
		if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}	
		else if(document.f1.socJantinaWaris.value == ""){
			alert('Sila plih " Jantina " terlebih dahulu.');

	  		document.f1.socJantinaWaris.focus(); 
			return; 
		}		
		else if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');

	  		document.f1.socSaudaraWaris.focus(); 
			return; 
		}
		
		
		else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoSuratBeranakWaris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0" )  && document.f1.txtNoKPLainWaris.value=="")
		 {
		alert("Sila masukkan salah satu nombor MyID atau nombor surat beranak waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		
		else if(document.f1.txtNoKPLamaWaris.value != ""&& document.f1.txtNoKPLamaWaris.value != "TDK" && document.f1.txtNoKPLamaWaris.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Waris");
		document.f1.txtNoKPLamaWaris.focus();	
	}
		
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID lain simati");
		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis MyID lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}
	
	
	else if (document.f1.txtPoskodWaris.value != "" && document.f1.txtPoskodWaris.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodWaris.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txdTarikhMatiWaris.value == "") {
		alert("Sila masukkan tarikh mati");
		document.f1.txdTarikhMatiWaris.focus();
		return; 
	}
	/*
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && document.f1.txtWaktuKematianWaris.value.length < 4) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 1) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >2 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	/*
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(2) > 5) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(3) > 9) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
else if (document.f1.txtNoKPBaru1Waris.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1Waris.focus()
		return false
	}

/*

else if (document.f1.txtNoKPBaru2Waris.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaru2Waris.focus()
	return;
	
	}
	*/
	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return false
	}
	
	else if (document.f1.checkHidupWaris.checked == true  && document.f1.txdTarikhMatiWaris.value!="" && isDate(document.f1.txdTarikhMatiWaris.value)==false)
	{
	
    document.f1.txdTarikhMatiWaris.focus();
	return false;
	
	}
/*	 else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirWaris.focus();
   }*/ 
   /*
   else if(ch=="yes" && date3 > date2 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   */
   else if(ch=="yes" && date3 < date1 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh lahir waris.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   
	else if (document.f1.txtUmurWaris.value == "")
	{
	alert("Sila masukkan umur waris");
	document.f1.txtUmurWaris.focus()
	return;
	}	
	else if (document.f1.txtPoskodWarisSurat.value != "" && document.f1.txtPoskodWarisSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod untuk alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodWarisSurat.focus();
		return; 
	}
	
	
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWarisSurat.value == ""  || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	
	
	else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1Waris.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWaris.value == ""  || document.f1.socNegeriWaris.value == "" || document.f1.socNegeriWaris.value == "0"))))
	{
	alert("Sila lengkapkan alamat tetap waris");
	document.f1.txtAlamatTerakhir1Waris.focus()
	return;
	}
	
	else if (document.f1.checkHidupWaris.checked == false 
			&& (document.f1.txtNoTeleponBimbitWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "") )
	{
	
	alert("Sila pastikan no telefon bimbit diisi");
	document.f1.txtNoTeleponBimbitWaris.focus()
	return;
	}
		
	else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtEmel.value == "" && document.f1.txtEmel.value == "") )
	{
	
	alert("Sila pastikan emel diisi");
	document.f1.txtEmel.focus()
	return;
	}
		
	 else if (document.f1.socStatusOBWaris.value == 0 || document.f1.socStatusOBWaris.value == "")
	{
	
	alert("Sila pastikan salah satu status waris dipilih");
	document.f1.socStatusOBWaris.focus()
	return;
	
	}
	
	else if (document.f1.flag_dup_1.value == 'yes' )
	{	
	alert("No MyID baru waris telah wujud!");
	document.f1.txtNoKPBaru1Waris.focus()
	return;	
	}
	else if (document.f1.flag_dup_2.value == 'yes' )
	{	
	alert("No MyID lama waris telah wujud!");
	document.f1.txtNoKPLamaWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_3.value == 'yes' )
	{	
	alert("MyID Lain waris telah wujud!");
	document.f1.txtNoKPLainWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_4.value == 'yes' )
	{	
	alert("No surat beranak waris telah wujud!");
	document.f1.txtNoSuratBeranakWaris.focus()
	return;	
	}
   	
		else
		{
	//	alert(document.f1.flag_dup_1.value+""+document.f1.flag_dup_2.value+""+document.f1.flag_dup_3.value)
		input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.f1.mode.value = "tambah_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	}
	else
	{
	return;
	
	}

	
	}
	
	}
}

function tambah_waris_Simpan()
{
//alert("asdashbdbasdsa")
//alert(document.f1.flag_dup_1.value+""+document.f1.flag_dup_2.value+""+document.f1.flag_dup_3.value);



var negeri_code = document.f1.txtNoKPBaru2Waris.value;
var dob_code = document.f1.txtNoKPBaru1Waris.value;
if(dob_code.charAt(0)<3)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	 var date_dob = new Date(yr_dob, mon_dob, dt_dob);


 var ch="";	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;	
	
	if(document.f1.checkHidupWaris.checked == true && document.f1.txdTarikhMatiWaris.value != "" )
	{
	var str3  = document.getElementById("txdTarikhMatiWaris").value;
	var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10)-1;
    var yr3   = parseInt(str3.substring(6,10),10);	
	var date3 = new Date(yr3, mon3, dt3);	
	ch="yes";	
	}
	
	var dat1=document.f1.txdTarikhLahirWaris
	
    var str1  = document.getElementById("txdTarikhLahirWaris").value;
    var str2  = document.getElementById("txdTarikhMohon").value;
   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10)-1;
    var yr2   = parseInt(str2.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);
    var date2 = new Date(yr2, mon2, dt2);
	
	

if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}	
		else if(document.f1.socJantinaWaris.value == ""){
			alert('Sila plih " Jantina " terlebih dahulu.');

	  		document.f1.socJantinaWaris.focus(); 
			return; 
		}		
		else if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');

	  		document.f1.socSaudaraWaris.focus(); 
			return; 
		}
			else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoSuratBeranakWaris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0" )  && document.f1.txtNoKPLainWaris.value=="")
		 {
		alert("Sila masukkan salah satu nombor MyID atau nombor surat beranak waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		
		else if(document.f1.txtNoKPLamaWaris.value != ""&& document.f1.txtNoKPLamaWaris.value != "TDK" && document.f1.txtNoKPLamaWaris.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Waris");
		document.f1.txtNoKPLamaWaris.focus();	
	}
		
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID lain simati");
		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis MyID lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}
	
	
	else if (document.f1.txtPoskodWaris.value != "" && document.f1.txtPoskodWaris.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodWaris.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txdTarikhMatiWaris.value == "") {
		alert("Sila masukkan tarikh mati");
		document.f1.txdTarikhMatiWaris.focus();
		return; 
	}
	/*
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && document.f1.txtWaktuKematianWaris.value.length < 4) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 1) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >2 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	/*
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(2) > 5) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(3) > 9) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
else if (document.f1.txtNoKPBaru1Waris.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1Waris.focus()
		return false
	}

/*

else if (document.f1.txtNoKPBaru2Waris.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaru2Waris.focus()
	return;
	
	}
	*/
	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return false
	}
	
	else if (document.f1.checkHidupWaris.checked == true  && document.f1.txdTarikhMatiWaris.value!="" && isDate(document.f1.txdTarikhMatiWaris.value)==false)
	{
	
    document.f1.txdTarikhMatiWaris.focus();
	return false;
	
	}
/*	 else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirWaris.focus();
   } */
   /*
   else if(ch=="yes" && date3 > date2 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   */
   else if(ch=="yes" && date3 < date1 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh lahir waris.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   
	else if (document.f1.txtUmurWaris.value == "")
	{
	alert("Sila masukkan umur waris");
	document.f1.txtUmurWaris.focus()
	return;
	}	
	else if (document.f1.txtPoskodWarisSurat.value != "" && document.f1.txtPoskodWarisSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod untuk alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodWarisSurat.focus();
		return; 
	}
	
	
	
	
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1Waris.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWaris.value == "" ||document.f1.socNegeriWaris.value == "" || document.f1.socNegeriWaris.value == "0"))))
	{
	alert("Sila lengkapkan alamat tetap waris");
	document.f1.txtAlamatTerakhir1Waris.focus()
	return;
	}
	
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWarisSurat.value == ""  || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	
	else if (document.f1.checkHidupWaris.checked == false 
			&& (document.f1.txtNoTeleponBimbitWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "") )
	{
	
	alert("Sila pastikan no telefon bimbit diisi");
	document.f1.txtNoTeleponBimbitWaris.focus()
	return;
	
	}
	
	else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtEmel.value == "" && document.f1.txtEmel.value == "") )
	{
	
	alert("Sila pastikan emel diisi");
	document.f1.txtEmel.focus()
	return;
	}
	
	 else if (document.f1.socStatusOBWaris.value == 0 || document.f1.socStatusOBWaris.value == "")
	{
	
	alert("Sila pastikan salah satu status waris dipilih");
	document.f1.socStatusOBWaris.focus()
	return;
	
	}
	
	else if (document.f1.flag_dup_1.value == 'yes' )
	{	
	alert("No MyID baru waris telah wujud!");
	document.f1.txtNoKPBaru1Waris.focus()
	return;	
	}
	else if (document.f1.flag_dup_2.value == 'yes' )
	{	
	alert("No MyID lama waris telah wujud!");
	document.f1.txtNoKPLamaWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_3.value == 'yes' )
	{	
	alert("MyID Lain waris telah wujud!");
	document.f1.txtNoKPLainWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_4.value == 'yes' )
	{	
	alert("No surat beranak waris telah wujud!");
	document.f1.txtNoSuratBeranakWaris.focus()
	return;	
	}
	
		else
		{
		
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.f1.mode.value = "tambah_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	}
	else
	{
	return;
	
	}
	}
   
}


function Checkumur() 
{
	if (document.f1.txtUmurWaris.value != "" && document.f1.txtUmurWaris.value>100) {
		alert("Adakah anda pasti umur anda adalah "+document.f1.txtUmurWaris.value+" tahun?");
		txtUmurWaris.focus();
		return; 
	}
	}
	
function insertsaudara()
{
   if (document.f1.socJantinaWaris.value == "") {
		alert("Sila pilih jantina terlebih dahulu");
		socJantinaWaris.focus();
		return; 
	}
}
function insertsaudara1()
{
   if (document.f1.socJantinaWaris.value == "") {
		alert("Sila pilih jantina terlebih dahulu");
		socJantinaWaris.focus();
		return; 
	}
}

function check_pilih(){
	if( document.f1.id_Pemohonbaru.value != "" && document.f1.id_Pemohonbaru.value != "0"){
		document.f1.id_ob_pemohon.value = document.f1.id_Pemohonbaru.value;
		document.f1.setMode0.value = "disabled";
		// alert('id adalah : '+document.f1.id_Pemohonbaru.value);
	}
}

function hapus_waris(){

if( document.f1.id_Pemohon.value != "" && document.f1.id_Pemohon.value != "0")
{
alert("Waris ini adalah seorang pemohon, maklumat waris ini tidak dapat dihapuskan. Sebarang perubahan hendaklah dilakukan di tab pemohon!");
		//txtNoKPLainPenting.focus();
		return;
		
}
else
{

input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.f1.mode.value = "hapus_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	
	
	}
	else
	{
	return;
	}
	}
    }
    function hapus_waris_lapisan(){

    input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.f1.mode.value = "hapus_waris_lapisan";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	
	
	}
	}



function tambah_waris(){


	
	if( document.f1.tambahwaris.value == "Tambah" ) 
	{
		if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		else if(document.f1.socJantinaWaris.value == ""){
			alert('Sila plih " Jantina " terlebih dahulu.');

	  		document.f1.socJantinaWaris.focus(); 
			return; 
		}	
		
		if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
	document.f1.mode.value = "tambah_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	}



	if( document.f1.tambahwaris.value == "Simpan" ) 
	{
	
	
var negeri_code = document.f1.txtNoKPBaru2Waris.value;
var dob_code = document.f1.txtNoKPBaru1Waris.value;
if(dob_code.charAt(0)<3)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	 var date_dob = new Date(yr_dob, mon_dob, dt_dob);

	
	
	
	
	var ch="";	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;	
	
	
	if(document.f1.checkHidupWaris.checked == true && document.f1.txdTarikhMatiWaris.value != "" )
	{
	var str3  = document.getElementById("txdTarikhMatiWaris").value;
	var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10)-1;
    var yr3   = parseInt(str3.substring(6,10),10);	
	var date3 = new Date(yr3, mon3, dt3);	
	ch="yes";	
	}
	
	
	var dat1=document.f1.txdTarikhLahirWaris
	var str1  = document.getElementById("txdTarikhLahirWaris").value;
	
   
    var str2  = document.getElementById("txdTarikhMohon").value;
   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10)-1;
    var yr2   = parseInt(str2.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);
    var date2 = new Date(yr2, mon2, dt2);
	
	 
	
	
	
	

	
	if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		else if(document.f1.socJantinaWaris.value == ""){
			alert('Sila plih " Jantina " terlebih dahulu.');

	  		document.f1.socJantinaWaris.focus(); 
			return; 
		}		
		else if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');

	  		document.f1.socSaudaraWaris.focus(); 
			return; 
		}
		
		
		else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoSuratBeranakWaris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value=="")
		 {
		alert("Sila masukkan salah satu nombor MyID atau nombor surat beranak waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		else if(document.f1.txtNoKPLamaWaris.value != "" && document.f1.txtNoKPLamaWaris.value != "TDK" && document.f1.txtNoKPLamaWaris.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Waris");
		document.f1.txtNoKPLamaWaris.focus();	
	}
		
		
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID lain simati");
		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis MyID lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor MyID waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}
	else if (document.f1.txtPoskodWaris.value != "" && document.f1.txtPoskodWaris.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodWaris.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txdTarikhMatiWaris.value == "") {
		alert("Sila masukkan tarikh mati");
		document.f1.txdTarikhMatiWaris.focus();
		return; 
	}
	/*
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.length < 4) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 1) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >2 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	/*	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}*/
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(2) > 5) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(3) > 9) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
else if (document.f1.txtNoKPBaru1Waris.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1Waris.focus()
		return false
	}

/*

else if (document.f1.txtNoKPBaru2Waris.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaru2Waris.focus()
	return;
	
	}
	*/
	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return false
	}
	
	else if (document.f1.checkHidupWaris.checked == true  && document.f1.txdTarikhMatiWaris.value!="" && isDate(document.f1.txdTarikhMatiWaris.value)==false)
	{
	
    document.f1.txdTarikhMatiWaris.focus();
	return false;
	
	}
	/* else if(date2 < date1 )
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhLahirWaris.focus();
   } */
/*
  else if(ch=="yes" && date3 > date2 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh mohon");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
 */  
   else if(ch=="yes" && date3 < date1 )
   {
      alert("Sila pastikan tarikh mati waris tidak melebihi dari tarikh lahir waris.");	 
      document.f1.txdTarikhMatiWaris.focus();
   } 
   else if (document.f1.txtUmurWaris.value == "")
	{
	alert("Sila masukkan umur waris");
	document.f1.txtUmurWaris.focus()
	return;
	}
	
	else if (document.f1.txtPoskodWarisSurat.value != "" && document.f1.txtPoskodWarisSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod untuk alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodWarisSurat.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1Waris.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWaris.value == ""  || document.f1.socNegeriWaris.value == "" || document.f1.socNegeriWaris.value == "0"))))
	{
	alert("Sila lengkapkan alamat tetap waris");
	document.f1.txtAlamatTerakhir1Waris.focus()
	return;
	}
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || (document.f1.socWarganegaraWaris.value != "2" && (document.f1.txtPoskodWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
		
	else if (document.f1.checkHidupWaris.checked == false 
			&& (document.f1.txtNoTeleponBimbitWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "" ))
	{
	
	alert("Sila pastikan no telefon bimbit diisi");
	document.f1.txtNoTeleponBimbitWaris.focus()
	return;
	}
	
	else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtEmel.value == "" && document.f1.txtEmel.value == "") )
	{
	
	alert("Sila pastikan emel diisi");
	document.f1.txtEmel.focus()
	return;
	}
	
	else if (document.f1.socStatusOBWaris.value == 0 || document.f1.socStatusOBWaris.value == "")
	{
	
	alert("Sila pastikan salah satu status waris dipilih");
	document.f1.socStatusOBWaris.focus()
	return;
	
	}
	
	else if (document.f1.flag_dup_1.value == 'yes' )
	{	
	alert("No MyID baru waris telah wujud!");
	document.f1.txtNoKPBaru1Waris.focus()
	return;	
	}
	else if (document.f1.flag_dup_2.value == 'yes' )
	{	
	alert("No MyID lama waris telah wujud!");
	document.f1.txtNoKPLamaWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_3.value == 'yes' )
	{	
	alert("MyID Lain waris telah wujud!");
	document.f1.txtNoKPLainWaris.focus()
	return;	
	}
	else if (document.f1.flag_dup_4.value == 'yes' )
	{	
	alert("No surat beranak waris telah wujud!");
	document.f1.txtNoSuratBeranakWaris.focus()
	return;	
	}
   	
	
		else
		{
		//alert(document.f1.flag_dup_1.value+""+document.f1.flag_dup_2.value+""+document.f1.flag_dup_3.value)
		
		
		
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.f1.mode.value = "simpan_waris";
	document.f1.command.value = "Waris";	
	document.f1.action = "";
	document.f1.submit();
	}
	
	else
	{return;}
	
	
   }
    }
   

	if( document.f1.tambahwaris.value == "Kemaskini" ) 
	{

    document.f1.mode.value = "kemaskini_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	
	}
	
	
}

function getDOB(kp){


var nokp = kp;
if(nokp.length == 6)
{
var a = nokp.charAt(0);
var b = nokp.charAt(1);
var c = nokp.charAt(2);
var d = nokp.charAt(3);
var e = nokp.charAt(4);
var f = nokp.charAt(5);


var k = a+""+b;

if(k < 11)
{
var th = 20;
}
else
{
var th = 19;
}
//var dob = th+""+a+""+b+"/"+c+""+d+"/"+e+""+f; 

var dob = e+""+f+"/"+c+""+d+"/"+th+""+a+""+b;

document.f1.txdTarikhLahirWaris.value=dob;
}


}



<!-- PEGUAM -->


<!-- PEMOHON -->

</script>
<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1800;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yg sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yg sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yg sah")
		return false
	}
return true
}


function isIc(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format MyID baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada MyID baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada MyID baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan MyID yang sah")
		return false
	}
return true
}



function kplain1(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainWaris.disabled = "";
document.f1.txtNoKPLainWaris.value = "";
document.f1.txtNoKPLainWaris.focus();
return;
}
else
{
document.f1.txtNoKPLainWaris.disabled = "disabled";
document.f1.txtNoKPLainWaris.value = "";
return;
}
}

function kplain1X(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainWaris.focus();
return;
}
}

function kplain2(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainWaris.disabled = "";
document.f1.txtNoKPLainWaris.value = "";
//document.f1.txtNoKPLainWaris.focus();
return;
}
else
{
document.f1.txtNoKPLainWaris.disabled = "disabled";
document.f1.txtNoKPLainWaris.value = "";
return;
}
}
function kplain2X(val)
{
if(val!="0" && val!="")
{

document.f1.txtNoKPLainWaris.focus();
return;
}

}

function kplain3(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainWaris.disabled = "";
document.f1.txtNoKPLainWaris.value = "";
//document.f1.txtNoKPLainWaris.focus();
return;
}
else
{
document.f1.txtNoKPLainWaris.disabled = "disabled";
document.f1.txtNoKPLainWaris.value = "";
return;
}
}

function kplain3X(val)
{
if(val!="0" && val!="")
{

document.f1.txtNoKPLainWaris.focus();
return;
}

}

function kplain4(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainWaris.disabled = "";
document.f1.txtNoKPLainWaris.value = "";
//document.f1.txtNoKPLainWaris.focus();
return;
}
else
{
document.f1.txtNoKPLainWaris.disabled = "disabled";
document.f1.txtNoKPLainWaris.value = "";
return;
}
}

function kplain4X(val)
{
if(val!="0" && val!="")
{

document.f1.txtNoKPLainWaris.focus();
return;
}

}

function jantinaic1()
{
var ch=document.f1.txtNoKPBaru3Waris.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaWaris.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaWaris.value = 1 ;

}

return;
}

function jantinaic2()
{
var ch=document.f1.txtNoKPBaru3Waris.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaWaris.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaWaris.value = 1 ;

}

return;
}

function jantinaic3()
{
var ch=document.f1.txtNoKPBaru3Waris.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaWaris.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaWaris.value = 1 ;

}

return;
}

function jantinaic4()
{
var ch=document.f1.txtNoKPBaru3Waris.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaWaris.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaWaris.value = 1 ;

}

return;
}



function ValidateForm(){
	var dt=document.f1.txdTarikhMatiWaris
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }
 /*function submitForm() {    
 
	window.location.hash='$val_tab';
goTo('$val_tab');
	

} */

function submitForm() {    


if('$val_tab' != "" && '$val_tab' != null)
{
window.location.hash='$val_tab';
//goTo('$val_tab');
var nextFieldID = '$!val_tab';
   document.getElementById(nextFieldID).focus();
}
else
{
window.location.hash='maklumat_pemohon';
//goTo('maklumat_pemohon');
var nextFieldID = 'maklumat_pemohon';
   document.getElementById(nextFieldID).focus();
}
//	alert('$val_tab')
} 




function submitFormScroll() {    
  Effect.ScrollTo('$val_tab').focus(); return false;
} 

function copycopy()
{

 

var a1 = document.f1.txtAlamatTerakhir1WarisSurat.value;
var a2 = document.f1.txtAlamatTerakhir2WarisSurat.value;
var a3 = document.f1.txtAlamatTerakhir3WarisSurat.value;
var p1 = document.f1.txtPoskodWarisSurat.value;
var b1 = document.f1.txtBandarWarisSurat.value;
var n1 = document.f1.socNegeriWarisSurat.value;

if(document.f1.copy.checked == true)
{


document.f1.txtAlamatTerakhir1Waris.value = a1;
document.f1.txtAlamatTerakhir2Waris.value = a2;
document.f1.txtAlamatTerakhir3Waris.value = a3;
document.f1.txtPoskodWaris.value = p1;
document.f1.txtBandarWaris.value = b1;
document.f1.socNegeriWaris.value = n1;

}

if(document.f1.copy.checked == false)
{


document.f1.txtAlamatTerakhir1Waris.value = "";
document.f1.txtAlamatTerakhir2Waris.value = "";
document.f1.txtAlamatTerakhir3Waris.value = "";
document.f1.txtPoskodWaris.value = "";
document.f1.txtBandarWaris.value = "";
document.f1.socNegeriWaris.value = "0";

}

}


   function trans_date(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhLahirWaris.value = trans;

}
else
{
return;
}

}

   function trans_date1(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhMatiWaris.value = trans;

}
else
{
return;
}

}

   function trans_date2(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhMatiWaris.value = trans;

}
else
{
return;
}

}


/*
function janaFaraid(id,id_mati){
	  
	var url = "../x/${securityToken}/ekptg.faraid.FrmFaraid?id_simati="+id_mati+"&id_permohonan="+id;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
*/

</script>
<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

function kembalix() {
	document.f1.method = "POST";
	document.f1.command.value="papar";
	document.f1.action = "";
	document.f1.submit();
}
function kembalidaftar()
{
        document.f1.command.value="kembali_daftar_pemohon";
		document.f1.eventStatus.value="1";
		document.f1.action = "";
		document.f1.submit();
}

function kembaliSenaraiFail(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.f1.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.f1.method="POST";
	document.f1.submit();
}
function Kembali(){
	document.f1.method="POST";
	document.f1.command.value="xxx";
	document.f1.action = "";
	document.f1.submit();
}


function CheckBandarTetap()
{
if(document.f1.socNegeriWaris.value == "" || document.f1.socNegeriWaris.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriWaris.focus();
  return;
	  	
}

}
function CheckBandarSurat()
{
if(document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriWarisSurat.focus();
  return;
	  	
}

}




	
function check_kp() 
{
var s = document.f1.txtNoKPBaru1Waris.value + document.f1.txtNoKPBaru2Waris.value + document.f1.txtNoKPBaru3Waris.value;
document.f1.check_no_kp_baru_simati.value = s;

}

function check_kp_lama()
{
document.f1.check_no_kp_lama_simati.value = document.f1.txtNoKPLamaWaris.value;

}

function check_kp_lain()
{
document.f1.check_no_kp_lain_simati.value = document.f1.txtNoKPLainWaris.value;

}




function check_pengenalan_simati_1()
{
if('$!skrin_online_popup' == "yes")
{
	url = "../../servlet/ekptg.view.ppk.PendaftaranCheck?list=true";
	}else
	{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck?list=true";
	}
	actionName = "check_ob_kp_baru";
	target = "check_kp_1";
	doAjaxUpdater(document.f1, url, target, actionName);
}


function check_pengenalan_simati_2()
{
   // alert("hasgahghas");

	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_ob_kp_lama";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);
}
function check_pengenalan_simati_3()
{
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_ob_kp_lain";
	target = "check_kp_3";
	doAjaxUpdater(document.f1, url, target, actionName);
}







function check_pengenalan_simati_1_onload()
{

	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_ob_kp_baru_onload";
	target = "check_kp_1";
	doAjaxUpdater(document.f1, url, target, actionName);
}


function check_pengenalan_simati_2_onload()
{
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_ob_kp_lama_onload";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);
}
function check_pengenalan_simati_3_onload()
{
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_ob_kp_lain_onload";
	target = "check_kp_3";
	doAjaxUpdater(document.f1, url, target, actionName);
}


function suma_kp_baru()
{
	var s = document.f1.txtNoKPBaru1Waris.value + document.f1.txtNoKPBaru2Waris.value + document.f1.txtNoKPBaru3Waris.value;
	if(s.length == '12' )
	{
		jantinaic2();
		setSelected(0,7,0,0);
		tarikh_waris_saudara('txtNoKPLamaWaris','in');
		tarikh_waris_saudara_negeritetap('txtNoKPLamaWaris','in');
		tarikh_waris_saudara_negerisurat('txtNoKPLamaWaris','in');
	}

}

function suma_kp_baru_X()
{
var s = document.f1.txtNoKPBaru1Waris.value + document.f1.txtNoKPBaru2Waris.value + document.f1.txtNoKPBaru3Waris.value;
if(s.length == '12' )
{
jantinaic2();
setSelected(0,7,0,0);
tarikh_waris_saudara('txtNoKPLamaWaris','in');
tarikh_waris_saudara_negeritetap('txtNoKPLamaWaris','in');
tarikh_waris_saudara_negerisurat('txtNoKPLamaWaris','in');
}

}

function suma_kp_lama()
{
if(document.f1.check_no_kp_lama_simati.value != "" && document.f1.check_no_kp_lama_simati.value.length >= 7)
{
tarikh_waris_saudara_negeritetap('socJenisKPLainWaris','in');
tarikh_waris_saudara_negerisurat('socJenisKPLainWaris','in')
}

}


function suma_kp_lain()
{
if(document.f1.check_no_kp_lain_simati.value != "")
{
tarikh_waris_saudara_negeritetap('txtNamaOBWaris','in');
tarikh_waris_saudara_negerisurat('txtNamaOBWaris','in')
}

}




function suma_kp_baru_lap()
{
var s = document.f1.txtNoKPBaru1Waris.value + document.f1.txtNoKPBaru2Waris.value + document.f1.txtNoKPBaru3Waris.value;
if(s.length == '12' )
{
jantinaic2();
setSelected(0,7,0,0);
tarikh_waris_saudara('txtNoKPLamaWaris','in_lap');
tarikh_waris_saudara_negeritetap('txtNoKPLamaWaris','in_lap');
tarikh_waris_saudara_negerisurat('txtNoKPLamaWaris','in_lap');
}

}

function suma_kp_lama_lap()
{
if(document.f1.check_no_kp_lama_simati.value != ""  && document.f1.check_no_kp_lama_simati.value.length >= 7)
{
tarikh_waris_saudara_negeritetap('socJenisKPLainWaris','in_lap');
tarikh_waris_saudara_negerisurat('socJenisKPLainWaris','in_lap')
}

}


function suma_kp_lain_lap()
{
if(document.f1.check_no_kp_lain_simati.value != "")
{
tarikh_waris_saudara_negeritetap('txtNamaOBWaris','in_lap');
tarikh_waris_saudara_negerisurat('txtNamaOBWaris','in_lap')
}

}




function status_ob()
{
if(document.f1.socStatusOBWaris.value == "3")
{
document.f1.txtNoKPLamaWaris.value = "TDK";
}

}




function jeniswaktu1()
{

if(document.f1.txtWaktuKematianWaris != null)
{
document.f1.jeniswaktu.value = "";
var vm = document.f1.txtWaktuKematianWaris.value;


if(vm != "" && vm.length == 4)
{
var vm_m = 0;

if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

//alert("jam :"+vm_m)


if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}


}
else
{
//alert("2")

document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}



}
}



function jeniswaktu2()
{

if(document.f1.txtWaktuKematianWaris != null)
{
//document.f1.jeniswaktu.value = "";
var vm = document.f1.txtWaktuKematianWaris.value;


if(vm != "" && vm.length == 4)
{
var vm_m = 0;

if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

//alert("jam :"+vm_m)


if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}



}
else
{
//alert("2")

document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}


}
//alert("XX");
}





function check_pengenalan_simati_4()
{
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_ob_beranak";
	target = "check_kp_4";
	doAjaxUpdater(document.f1, url, target, actionName);
}

function check_pengenalan_simati_4_onload()
{
	
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_ob_beranak_onload";
	target = "check_kp_4";
	doAjaxUpdater(document.f1, url, target, actionName);
}

function suma_kp_beranak_lap()
{
if(document.f1.txtNoSuratBeranakWaris.value != ""  && document.f1.txtNoSuratBeranakWaris.value.length >= 7)
{
tarikh_waris_saudara_negeritetap('txtUmurWaris','in_lap');
tarikh_waris_saudara_negerisurat('txtUmurWaris','in_lap')
}

}

function suma_kp_beranak()
{
if(document.f1.txtNoSuratBeranakWaris.value != "" && document.f1.txtNoSuratBeranakWaris.value.length >= 7)
{
tarikh_waris_saudara_negeritetap('txtUmurWaris','in');
tarikh_waris_saudara_negerisurat('txtUmurWaris','in')
}

}





</script>
<!-- ADD BY PEJE -->
<script>
function checkExist(){
	var idSimati =  document.getElementById("idSimati").value;
	alert(idSimati);
}


function calculateTarikhLahirWaris(){

	if (document.f1.txtNoKPBaru1Waris.value != ""){
		var currentTime = new Date();
		
		var noKPWaris = document.f1.txtNoKPBaru1Waris.value;		
		if(noKPWaris.length == 6){
			var a = noKPWaris.charAt(0);
			var b = noKPWaris.charAt(1);
			var c = noKPWaris.charAt(2);
			var d = noKPWaris.charAt(3);
			var e = noKPWaris.charAt(4);
			var f = noKPWaris.charAt(5);
			
			var currentYear = currentTime.getFullYear();
			var birthYear = currentYear + "";
			birthYear = birthYear.charAt(0) + "" + birthYear.charAt(1);
			var fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			
			if (fullBirthYear > currentYear ){
				birthYear = birthYear*1 - 1;
				birthYear = birthYear + "";
				fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			}
			document.f1.txdTarikhLahirWaris.value = e + f + "/" + c + d + "/" + fullBirthYear;
			calculateUmurWaris();
			defineStatusWarisByUmur();
		}
	}	
}
function calculateUmurWaris(){

	if (document.f1.txdTarikhLahirWaris.value != ""){
		
		var str1  = document.getElementById("txdTarikhLahirWaris").value;
		var currentTime = new Date();
		var currentYear = currentTime.getFullYear();

		var yr1   = parseInt(str1.substring(6,10),10);
		
		var age = (currentYear*1) - (yr1*1);
		if (age < 0 )
			age = 0;
		document.f1.txtUmurWaris.value = age ;		
	}
}
function defineStatusWarisByUmur(){

	if (document.f1.txtUmurWaris.value != ""){
		
		var age  = document.getElementById("txtUmurWaris").value;
		if (age > 18) {
			document.f1.socStatusOBWaris.value = "1" ;
		} else {
			document.f1.socStatusOBWaris.value = "2" ;
		}		
	}
}

function checkMati() {
	if(document.getElementById('kematian').checked) {
		alert("mati");
	}
}

function lampiran(idPermohonan,jenisUpload) {	
	// console.log("syafiqah :"+idPermohonan);
	jenisUpload = "paparlampiran";
	var url = "../x/${securityToken}/ekptg.view.ppk.util.FrmUploadDokumen?actionrefresh=dokumenSokongan&actionPopup="+jenisUpload+"&rujukan="+idPermohonan+"&flagOnline=$!flagOnline";
    url +="&jenisdokumen=99210";
		
	//
    var hWnd = window.open(url,'printuser','width=400,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus(); /**/
    //
    var title = 'Lampiran';
	var w =1024;
	var h = 800;
    var left = (screen.width/2)-(w/2);

}

function paparLampiran(id_){
  	var url = "../servlet/ekptg.view.ppk.util.LampiranByBlob?iDokumen="+id_+"&tablename=simati";
      var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
      if ((document.window != null) && (!hWnd.opener))
  	hWnd.opener=document.window;
      if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangAA(idpermohonan,idfail) {
    var url = "../servlet/ekptg.report.ppk.BorangAA?idPermohonan="+idpermohonan+"&idfail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratPengesahan(idpermohonan,idfail) {
    var url = "../servlet/ekptg.report.ppk.PengesahanPertukaranPemohon?idpermohonan="+idpermohonan+"&idfail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function testSimpan(idSimati, id_permohonansimati, id_fail, no_fail, nama_butang) {
	// console.log('id_ob: ', document.f1.id_ob_pemohon.value,' no fail: ',no_fail)
	var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
	
	if (document.f1.id_ob_pemohon.value == "") {
		alert("Sila pilih pengganti waris terlebih dahulu."); 
		return;
	}
	
	if (document.f1.sebab.value == "") {
		alert("Sila masukkan sebab penggantian terlebih dahulu."); 
		return;
	}
	
	if (document.f1.kematian.checked  && document.f1.tarikh_mati.value == "") {
		alert("Sila masukkan tarikh kematian terlebih dahulu."); 
		return;
	}
	
	if (document.f1.kematian.checked  && document.f1.namaDoc1.value == "0") {
		alert("Sila masukkan dokumen sokongan terlebih dahulu."); 
		return;
	}
	
	if (document.f1.kesihatan.checked  && document.f1.namaDoc2.value == "0") {
		alert("Sila masukkan Dokumen Sokongan terlebih dahulu."); 
		return;
	}
	
// 	if(document.f1.fileupload.value == "") {
// 		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
// 		document.f1.fileupload.focus(); 
// 		return;
// 	}
	else {
		input_box = confirm("Permohonan Tukar Pempetisyen adalah dibenarkan SEKALI SAHAJA. Adakah anda pasti?");	
		if (input_box == true) {
			
			//baru 6/7/2020
			document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon"; 
			document.f1.method="POST";
			document.f1.command.value="Tukarpemohon";
			document.f1.mode.value="hantarPertukaran";
			document.f1.submit();
			
			
			//lama
// 			var command = "&command=Tukarpemohon"; 
// 			var sebab2 = document.f1.sebab.value;
// 			var id_ob_pemohon = document.f1.id_ob_pemohon.value;
// 			var id_permohonansimati_atheader = id_permohonansimati;
// 			var id_fail_carian = id_fail;
// 			var txtNoFailSub = no_fail;
// 			var data = "&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"&id_ob_pemohon="+id_ob_pemohon+"&id_permohonansimati_atheader="+id_permohonansimati_atheader+"&sebab="+sebab2;
			
// 			alert("Baca sini id_fail_carian --------" + id_fail_carian + "txtNoFailSub = "+txtNoFailSub+" id_ob_pemohon= "+id_ob_pemohon+" id_permohonansimati_atheader= "+id_permohonansimati_atheader+" sebab = "+sebab2);
			
//			var actionItem = (command+data);
	
// 			document.f1.enctype = "multipart/form-data";
// 			document.f1.encoding = "multipart/form-data";
// 			document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon"+actionItem;   
// 			document.f1.submit();
	
		}
		
	}
}

function SaveScrollXY() {
	document.f1.ScrollX.value = document.body.scrollLeft;
	document.f1.ScrollY.value = document.body.scrollTop;
}


function simpanSub(id_fail_carian,nama_butang) {
	var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
	if(document.${formName}.fileupload.value == "") {
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}
	if (document.${formName}.sebab.value == "") {
		alert("Sila masukkan Sebab Penggantian terlebih dahulu."); 
		return;
	}
	else {
		//myLogger.info("id_permohonansimati_atheader = " + id_permohonansimati_atheader);
		input_box = confirm("Adakah anda pasti?");	
		if (input_box == true) {
			//data
			//var name = document.${formName}.NAMA_DOKUMEN_ADD.value;
			//var keterangan = document.${formName}.DETAIL_DOKUMEN_ADD.value;
			//alert("Baca sini 1");
			
			var command = "&command=simpanSub";
			var sebab2 = document.${formName}.sebab.value;
			var id_ob_pemohon = document.${formName}.id_ob_pemohon.value;
			var id_permohonansimati_atheader = document.${formName}.id_permohonansimati.value;
			var data = "&id_fail_carian="+id_fail+"&txtNoFailSub="+txtNoFailSub.value+"&id_ob_pemohon="+id_ob_pemohon+"&id_permohonansimati_atheader="+id_permohonansimati_atheader+"&sebab="+sebab2;
			//myLogger.info("id_fail_carian = " + id_fail);
			
			//alert("Baca sini id_fail_carian --------" + id_fail + "txtNoFailSub = "+txtNoFailSub.value+" id_ob_pemohon= "+id_ob_pemohon+" id_permohonansimati_atheader= "+id_permohonansimati_atheader+" sebab = "+sebab2);
			var actionItem = (command+data);
	
			
			SaveScrollXY();        
			document.${formName}.id_fail_carian.value = id_fail;
			//doAjaxCall${formName}("simpanSub");
			//alert("Baca sini 1" + actionItem);
			document.${formName}.enctype = "multipart/form-data";
			document.${formName}.encoding = "multipart/form-data";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmTukarPemohon"+actionItem;   
			//document.${formName}.command.value = "simpanSub";
			//alert("Baca sini2" + command);
			document.${formName}.submit();
		
			document.getElementById(nama_butang).value = "Sila Tunggu...";
		}
	}
}

function semakLampiran(){
	alert("test");
  	//document.f1.command.value="nilai_harta";
	//document.f1.mode.value="ppkAddressView";
	
	//document.f1.action.value="";	
	//document.f1.submit();
}

function checkSebab(){
	if(document.f1.kematian.checked == true){
		document.getElementById('ftarikhmati').style.display='';
		document.getElementById('buktikematian').style.display='';
		document.getElementById('buktikesihatan').style.display='none';
	}
	else if(document.f1.kesihatan.checked == true){
		document.getElementById('ftarikhmati').style.display='none';
		document.getElementById('buktikematian').style.display='none';
		document.getElementById('buktikesihatan').style.display='';
	}
}

toggleDisplay = function(e){
	if (e=="ckematian") {
		document.getElementById('ftarikhmati').style.display='';
		document.getElementById('buktikematian').style.display='';
		document.getElementById('buktikesihatan').style.display='none';
	}else if (e=="ckesihatan"){
		document.getElementById('ftarikhmati').style.display='none';
		document.getElementById('buktikematian').style.display='none';
		document.getElementById('buktikesihatan').style.display='';
		
	}
}
// function semakanJPN(myIdBaru){
// 	//	var noResit = document.${formName}.txtNomborResitPerintahEDIT.value;
// 	//	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
// 	document.f1.command.value="semakanJPN";
// 	document.f1.action="";
// 	document.f1.submit();
</script>
<!-- END ADD BY PEJE -->
</body>
</html>
