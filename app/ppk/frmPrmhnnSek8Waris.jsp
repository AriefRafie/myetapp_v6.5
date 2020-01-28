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
</style>
</head>
<!-- onload="submitForm()" -->
<!--
<body onload="submitForm();jeniswaktu2();check_kp();check_kp_lama();check_kp_lain();check_pengenalan_simati_1_onload();check_pengenalan_simati_2_onload();check_pengenalan_simati_3_onload();check_pengenalan_simati_4_onload()" >
-->
<body onload="submitForm();calculateTarikhLahirWaris()" >
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
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    #set($id_Status = $list.id_Status)
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
    <input name="idpermohonan" type="hidden"  value="$id"/>
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
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()" >PERMOHONAN</li>
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
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,2,0,0);WarisView()" id="maklumat_pemohon" >WARIS</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,3,0,0);PentingView()">ORANG BERKEPENTINGAN</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,5,0,0);PemiutangView()">PEMIUTANG</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
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
                    
                    
                    
                    #if($Tambah_lapisan_berikut == "yes")
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
                    #set($catatan="")			            
                    #set($hp="")
                    #set($tarikhmati="")
                    #set($nokpwaris="")
                    #set($checkmati="0")		            
                    #set($waktumatiwaris="")
                    #set($saudara="")
                    #set($jeniswaktu="")
                    
                    #set($alamat1Surat="")
                    #set($alamat2Surat="")
                    #set($alamat3Surat="")
                    #set($poskodSurat="")				    
                    #set($negeriSurat="")
                    #set($bandarSurat="")	
                    
                    #set($flag_dup_1 = "")
                    #set($flag_dup_2 = "")
                    #set($flag_dup_3 = "")
                    #set($flag_dup_4 = "")
                    #set($id_Orangberkepentingan = "")
                    
                    #end
                    
                    
                    
                    #if($addnew=="yes")
                    
                    #set($listpenting = "")
                    
                    #set($txtNoKPBaru1Waris = "")
                    #set($txtNoKPBaru2Waris = "")
                    #set($txtNoKPBaru3Waris = "")
                    #set($txtNoKPLamaWaris = "")
                    #set($socJenisKPLainWaris = "")
                    #set($txtNoKPLainWaris = "")
                    #set($txtNamaOBWaris = "")
                    #set($txtNamaLainWaris = "")
                    #set($socJantinaWaris = "")
                    #set($socAgamaWaris = "")
                    #set($socWarganegaraWaris = "")
                    #set($nama_warga = "")
                    #set($txdTarikhLahirWaris = "")
                    #set($txtNoSuratBeranakWaris = "")
                    #set($txtUmurWaris = "")
                    #set($socStatusOBWaris = "")
                    #set($socSaudaraWaris = "")
                    #set($txdTarikhMatiWaris = "")
                    #set($txtWaktuKematianWaris = "")
                    #set($txtAlamatTerakhir1WarisSurat = "")
                    #set($txtAlamatTerakhir2WarisSurat = "")
                    #set($txtAlamatTerakhir3WarisSurat = "")
                    #set($txtPoskodWarisSurat = "")
                    #set($txtBandarWarisSurat = "")
                    #set($socNegeriWarisSurat = "")
                    #set($txtAlamatTerakhir1Waris = "")
                    #set($txtAlamatTerakhir2Waris = "")
                    #set($txtAlamatTerakhir3Waris = "")
                    #set($txtPoskodWaris = "")
                    #set($txtBandarWaris = "")
                    #set($socNegeriWaris = "")
                    #set($txtNoTeleponWaris = "")
                    #set($txtNoTeleponBimbitWaris = "")
                    #set($txtEmel = "")
                    #set($txtCatatanWaris = "")
                    #set($FLAG_DAFTAR = "")
                    #set($checkHidupWaris = "")
                    
                    #set($jeniswaktu = "")
                    
                    #set($FLAG_DAFTAR = "")
                    
                    #set($nama_pelbagainegara_surat = "")
                    #set($nama_pelbagainegara = "")
                    
                    
                    
                    
                    #set($checkmati="")
                    
                    
                    
                    #set($flag_dup_1 = "")
                    #set($flag_dup_2 = "")
                    #set($flag_dup_3 = "")
                    #set($flag_dup_4 = "")
                    #set($id_Orangberkepentingan = "")
                    
                    
                    
                    #end
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
                      <tr>
                        <td width="100%"> #if($!skrin_online != "yes") 
                          #if($readmode == "disabled")
                          #set($readmodeR = "readonly")
                          #else
                          #set($readmodeR = "")
                          #end
                          #else
                          #if($open_button_online == "no")
                          #set($readmodeR = "readonly")
                          #set($readmode = "disabled")
                          #else
                          #set($readmodeR = "")
                          #set($readmode = "")
                          #end
                          #end
                          <fieldset>
                          <legend>MAKLUMAT WARIS</legend>
                          <!--  id_Pemohon  :::: $id_Pemohon  
    txtIdSimatiWaris :::: $txtIdSimatiWaris
    list_getListOBUpdate :::: $list_getListOBUpdate
    -->
                          <input type="hidden" name="id_Pemohon" value="$id_Pemohon" />
                          <input type="hidden" name="idwarisup" value="$idwarisup" />
                          #set($idwarisup=$idwarisup)
                          <table width="100%" border="0">
                            <tr>
                              <td width="50%" valign="top"><table width="100%">
                                  #if($id_Pemohon != "" && $boleh_kemaskini_parent == "yes")
                                  <tr>
                                    <td class="style36" width="1%">&nbsp;</td>
                                    <td width="28%"><div align="left" class="style72"><span class="style38"> Waris Yang Meninggal</span></div></td>
                                    <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36" width="70%"> #if($readmode == "disabled")                                   
                                      #foreach($ls in $list_getListOBUpdate)                                    
                                      #if($id_ob_list_getListOBUpdate==$ls.ID_OB)                                    
                                      #set($nama_ob_getListOBUpdate=$ls.NAMA_OB)                                    
                                      #end 
                                      #end
                                      
                                      #if($id_ob_list_getListOBUpdate!="" && $id_ob_list_getListOBUpdate!="0" ) <font color="blue"> $nama_ob_getListOBUpdate</font> #else
                                      Tiada (Pemohon adalah Waris Lapisan Pertama)
                                      #end                                   
                                      #else                                   
                                      #foreach($ls in $list_getListOBUpdate)                                    
                                      #if($id_ob_list_getListOBUpdate==$ls.ID_OB)                                    
                                      #set($nama_ob_getListOBUpdate=$ls.NAMA_OB)                                   
                                      #end 
                                      #end                                     
                                      
                                      #if($id_ob_list_getListOBUpdate!="" && $id_ob_list_getListOBUpdate!="0" )
                                      <select name="id_ob_list_getListOBUpdate" id="id_ob_list_getListOBUpdate" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" >
                                        <option value="$id_ob_list_getListOBUpdate" style="text-transform:uppercase;" onblur="uppercase()">$nama_ob_getListOBUpdate</option>
                                        
                                            #foreach($ls in $list_getListOBUpdate)                                 
                                              #if($id_ob_list_getListOBUpdate!=$ls.ID_OB)
                                        
                                        <option value="$ls.ID_OB" style="text-transform:uppercase;" onblur="uppercase()">$ls.NAMA_OB</option>
                                                                           
                                              #end    
	                                        #end
                                            
                                        <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Waris</option>
                                      </select>
                                      <font  color="blue"  onMouseOver="this.style.cursor='help'" onClick="open_new_window();" ><img src="../img/info.png"  align="center" /></font> #else
                                      <select name="id_ob_list_getListOBUpdate" id="id_ob_list_getListOBUpdate" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" >
                                        <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Waris</option>
                                        
                                           #foreach($ls in $list_getListOBUpdate)
                                                
                                        <option value="$ls.ID_OB" style="text-transform:uppercase;" onblur="uppercase()">$ls.NAMA_OB</option>
                                        
                                           #end
                                      
                                      </select>
                                      <font  color="blue"  onMouseOver="this.style.cursor='help'" onClick="open_new_window();" ><img src="../img/info.png"  align="center" /></font> #end 
                                      #end </td>
                                  </tr>
                                  #end
                                  <input type="hidden" name="txtIdSimatiWaris" value="$idSimati" />
                                  <tr>
                                    <td width="1%">&nbsp;</td>
                                    <td width="28%"><div align="left" class="style75"><span class="style38">MyID Baru</span></div></td>
                                    <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%" class="style36"><input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Waris" style="width: 50px;" type="text" value="$txtNoKPBaru1Waris" $readmodeR class="$readmode" size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Waris');check_kp();check_pengenalan_simati_1()"/>
                                      <!--onBlur="getAgeByIC(this,this.value,'txtUmurWaris');getStatusByIC(this,this.value,'socStatusOBWaris');getDOB(this.value)"/-->
                                      -
                                      <input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Waris" style="width: 20px;" type="text" value="$txtNoKPBaru2Waris" $readmodeR class="$readmode" size="3" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris');check_kp();check_pengenalan_simati_1()" onblur=""/>
                                      -
                                      <input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Waris"  style="width: 40px;" type="text" value="$txtNoKPBaru3Waris" $readmodeR class="$readmode" size="5" maxlength="4" 
                                          onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris');
                                          check_kp();check_pengenalan_simati_1()" 
                                          onblur="jantinaic2();setSelected(0,2,0,0);"
                                           />
                                      <span id="check_kp_1" style="color:red" ></span> #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")                            
                                      #if($readmode != "disabled") <a href="http://www.jpn.gov.my" target="_blank" class="style51 style74 style42"> www.jpn.gov.my</a> #end     
                                      #end </td>
                                  </tr>
                                  <tr>
                                    <td >&nbsp;</td>
                                    <td ><div align="left" class="style75"><span class="style38">MyID Lama</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label>
                                      <input name="txtNoKPLamaWaris" id="txtNoKPLamaWaris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" onkeyup="check_kp_lama();check_pengenalan_simati_2()" type="text"  value="$txtNoKPLamaWaris" size="15" maxlength="15" $readmodeR class="$readmode" />
                                      </label>
                                      <span id="check_kp_2" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td >&nbsp;</td>
                                    <td ><div align="left" class="style75"><span class="style38">Jenis MyID Lain</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"> 
										#if($readmode=="disabled")
										
											#if($socJenisKPLainWaris=="5")
												#set($pkp="Tentera")
											#elseif($socJenisKPLainWaris=="6")
												#set($pkp="Polis")
											#elseif($socJenisKPLainWaris=="4")
												#set($pkp="Pasport")
											#elseif($socJenisKPLainWaris=="7")
												#set($pkp="Lain-lain")
											#elseif($socJenisKPLainWaris=="0")
												#set($pkp="")
											#else
												#set($pkp="")
											#end
										
											<input name="textfield4" type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="12" $readmodeR class="$readmode" />
										#else
											<select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode onchange="kplain1(this.value)"  style="text-transform:uppercase;" onblur="kplain1X(this.value)" />
											#if($socJenisKPLainWaris=="5")
												<option value="5" style="text-transform:uppercase;" >Tentera</option>
												<option value="6" style="text-transform:uppercase;" >Polis</option>
												<option value="4" style="text-transform:uppercase;" >Pasport</option>
												<option value="7" style="text-transform:uppercase;" >Lain-lain</option>
											#elseif($socJenisKPLainWaris=="6")
												<option value="6" style="text-transform:uppercase;" >Polis</option>
												<option value="5" style="text-transform:uppercase;" >Tentera</option>
												<option value="4" style="text-transform:uppercase;" >Pasport</option>
												<option value="7" style="text-transform:uppercase;" >Lain-lain</option>
											#elseif($socJenisKPLainWaris=="4")
												<option value="4" style="text-transform:uppercase;" >Pasport</option>
												<option value="5" style="text-transform:uppercase;" >Tentera</option>
												<option value="6" style="text-transform:uppercase;" >Polis</option>
												<option value="7" style="text-transform:uppercase;" >Lain-lain</option>
											#elseif($socJenisKPLainWaris=="7")
												<option value="7" style="text-transform:uppercase;" >Lain-lain</option>
												<option value="4" style="text-transform:uppercase;" >Pasport</option>
												<option value="5" style="text-transform:uppercase;" >Tentera</option>
												<option value="6" style="text-transform:uppercase;" >Polis</option>
											#else
												<option value="0" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Jenis KP</option>
												<option value="5" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Tentera</option>
												<option value="6" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Polis</option>
												<option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pasport</option>
												<option value="7" style="text-transform:uppercase;" >Lain-lain</option>
											#end
											</select>
										#end
									<label></label></td>
                                  </tr>
                                  #if($readmode=="disabled")
                                  #set($readmodekp="disabled")
                                  #end
                                  
                                  #if($readmode=="")
                                  #if($socJenisKPLainWaris != 0 && $socJenisKPLainWaris != "")
                                  #set($readmodekp="")
                                  #else
                                  #set($readmodekp="disabled")
                                  #set($txtNoKPLainWaris = "")
                                  #end
                                  
                                  
                                  #end
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style75"><span class="style38">MyID Lain</span></div></td>
                                    <td>:</td>
                                    <td><span class="style36"> #if($readmode == "disabled")
                                      <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" onkeyup="check_kp_lain();check_pengenalan_simati_3()" value="$txtNoKPLainWaris" size="15" maxlength="25" $readmodeR class="$readmode" />
                                      #else
                                      <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" onkeyup="check_kp_lain();check_pengenalan_simati_3()" value="$txtNoKPLainWaris" size="15" maxlength="25" $readmodekp />
                                      #end </span> <span id="check_kp_3" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style75"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Nama Waris #else Nama Waris #end</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBWaris" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtNamaOBWaris" value="$txtNamaOBWaris" size="34" $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"></td>
                                    <td><div align="left" class="style75"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Nama Lain #else Nama Lain #end</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaLainWaris" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtNamaLainWaris" value="$txtNamaLainWaris" size="34" $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style75"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Jantina #else Jantina #end</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label> #if($readmode=="disabled")
                                      
                                      #if($socJantinaWaris=="1")
                                      #set($sexpemohon="Lelaki")
                                      
                                      
                                      #elseif($socJantinaWaris=="2")
                                      #set($sexpemohon="Perempuan")
                                      
                                      #else
                                      #set($sexpemohon="")
                                      #end
                                      <input name="textfield" type="text" id="textfield2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" $readmodeR class="$readmode" value="$sexpemohon" />
                                      #else
                                      <select name="socJantinaWaris" id="socJantinaWaris" onchange="setSelected(0,2,0,0);
                                            tarikh_waris_saudara('socAgamaWaris','up')" class="mediumselect" $readmode style="text-transform:uppercase;" 
                                            onblur="text-transform:uppercase;">
                                        
                                              
                                             
                                              
                                      
                                   #if($socJantinaWaris=="1")
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socJantinaWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Jantina</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style75"><span class="style38">Agama</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label> #if($readmode=="disabled")
                                      
                                      #if($socAgamaWaris=="1")
                                      
                                      #set($agp="Islam")
                                      
                                      #elseif($socAgamaWaris=="2")
                                      
                                      #set($agp="Bukan Islam")
                                      
                                      #else
                                      
                                      #set($agp="")
                                      
                                      #end
                                      <input name="socAgamaPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="15"  $readmodeR class="$readmode"  value="$agp" />
                                      #else
                                      <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;">
                                        
                                              
                                             
                                              
                                      
                                   #if($socAgamaWaris=="1")
	                               
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option >
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socAgamaWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                         
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        
                                              
                                             
                                              
                                      

	                               #end
                                      
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td valign="top">&nbsp;</td>
                                    <td valign="top"><div align="left" class="style75"><span class="style38">Warganegara</span></div></td>
                                    <td valign="top" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td valign="top" class="style36"><label>
                                      #if($readmode=="disabled")
                                      
                                      #if($socWarganegaraWaris=="1")
                                      
                                      #set($wrp="Warganegara")
                                      
                                      #elseif($socWarganegaraWaris=="2")
                                      
                                      #set($wrp="Bukan Warganegara")
                                      
                                      #elseif($socWarganegaraWaris=="3")
                                      
                                      #set($wrp="Pemastautin Tetap")
                                      
                                      #else
                                      #set($wrp="")
                                      
                                      #end
                                      <input name="socWarganegaraPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="25" $readmodeR class="$readmode" value="$wrp" />
                                      #else
                                      <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="autoselect" $readmode style="text-transform:uppercase;"   onchange="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" onblur="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" >
                                        
                                              
                                             
                                              
                                      
                                   #if($socWarganegaraWaris=="1")
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socWarganegaraWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socWarganegaraWaris=="3")
	                               
                                      
                                              
                                             
                                              
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                              
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                         
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      <div id="alamatwarga"></div>
                                      #end
                                      </label>
                                    </td>
                                  </tr>
                                  <tr id="tr_nama_warga">
                                    <td valign="top" ></td>
                                    <td><div align="left" class="style38"> Negara </div></td>
                                    <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input type = 'text' id = 'nama_warga' name = 'nama_warga' size='30' maxlength='200' $readmodeR class="$readmode"  list = 'datalist'  value="$nama_warga"    />
                                      <datalist id = 'datalist'>#foreach($ja in $kenegaraan)
                                        <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                        #end </datalist>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style75"><span class="style38">Tarikh Lahir</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txdTarikhLahirWaris" type="text" id="txdTarikhLahirWaris" value="$txdTarikhLahirWaris" size="10" maxlength="10" $readmodeR class="$readmode"  onblur="trans_date(this.value);getAgebyDob(this,this.value,'txtUmurWaris');defineStatusWarisByUmur();"/>
                                      #if($readmode != "disabled") <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a><span class="style73"> dd/mm/yyyy</span> #end </td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style75"><span class="style38">No Surat Beranak</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtNoSuratBeranakWaris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  onkeyup="check_pengenalan_simati_4()" type="text" id="txtNoSuratBeranakWaris" value="$txtNoSuratBeranakWaris" size="15" maxlength="10" $readmodeR class="$readmode" />
                                      <span id="check_kp_4" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style75"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Umur   #else Umur  #end</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtUmurWaris" style="text-transform:uppercase;" onblur="Checkumur();getStatusByAge(this,this.value,'socStatusOBWaris')" type="text" id="txtUmurWaris" value="$txtUmurWaris" size="3" maxlength="3" onkeyup="javascript:validateIC(event,this,this.value,'txtUmurWaris')" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style75"><span class="style38">Status Waris</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"> #if($readmode == "disabled")
                                      
                                      #if($socStatusOBWaris=="1")
                                      #set($stat = "Dewasa/Waras")
                                      #elseif($socStatusOBWaris=="2")
                                      #set($stat = "Belum Dewasa")
                                      #elseif($socStatusOBWaris=="3")
                                      #set($stat = "Hilang/Meninggal Dunia/Tidak Dapat Dikesan")
                                      #elseif($socStatusOBWaris=="4")
                                      #set($stat = "Tidak Sempurna Akal")
                                      #else
                                      #set($stat = "")
                                      #end
                                      <input type="text" name="stat" id="stat" value="$stat" size="25"  style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <select name="socStatusOBWaris"  style="text-transform:uppercase;" onblur="text-transform:uppercase;" class="autoselect" $readmode id="socStatusOBWaris" onchange="status_ob()">
                                        
                                            
                                           
									
								   #if($socStatusOBWaris=="1")
	                                 
                                      
                                           
                                           
                                            
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                             
                                      
	                              
	                               #elseif($socStatusOBWaris=="2")
	                                
	                                  
                                           
                                            
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                               
                                      
	                              
								   #elseif($socStatusOBWaris=="3")
	                               
	                                 
                                           
                                            
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                       
                                   #elseif($socStatusOBWaris=="4")
	                                    
                                           
                                            
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                             
                                    
                                        
	                               #else
	                                 
                                      
                                           
                                            
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        
                                            
                                           
                                      
	                               #end
                                   
                                         
                                          
                                      </select>
                                      #end </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style75"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Talian Persaudaraan</span> #else Talian Persaudaraan #end</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td> #if($readmode == "disabled")
                                      
                                      #foreach($listsau in $listsaudara)                                          
                                      #if($socSaudaraWaris==$listsau.id_Saudara)                                          
                                      #set($kodsaudaraketerangan=$listsau.keterangan)
                                      
                                      #end    
                                      #end
                                      
                                      #if($socSaudaraWaris!="" && $socSaudaraWaris!="0")
                                      <input  name="sau" id"sau" value="$kodsaudaraketerangan"   size="50" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input  name="sau" id"sau" value="" style="text-transform:uppercase;" size="15" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      
                                      #foreach($listsau in $listsaudara)
                                      
                                      #if($socSaudaraWaris==$listsau.id_Saudara)                                          
                                      #set($kodsaudara=$listsau.kod)
                                      #set($kodsaudaraketerangan=$listsau.keterangan)                                          
                                      #end    
                                      #end
                                      #if($socSaudaraWaris!="")
                                      <select name="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;" >
                                        <option value="$socSaudaraWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$kodsaudara - $kodsaudaraketerangan</option>
                                                      
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($socSaudaraWaris!=$listsau.id_Saudara)
                                     #if($socJantinaWaris=="1")
                                          #set($jan="01")
                                      
                                          #elseif($socJantinaWaris=="2")
                                          #set($jan="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jan )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                              
                                   #end
                                   
                                   #if($listsau.id_Saudara=="27" )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                              
                                   #end
                                   
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
                                            
                                      </select>
                                      #else
                                      <select name="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;" onfocus="insertsaudara()" >
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Talian Persaudaraan</option>
                                        
                                              
                                  #foreach($listsau in $listsaudara)
                                         #if($socJantinaWaris=="1")
                                          #set($jantt="01")
                                          
                                          #elseif($socJantinaWaris=="2")
                                          #set($jantt="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jantt )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                 #end         
                                 
                                 
                                    #if($listsau.id_Saudara=="27" )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                 #end             
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
                                            
                                      </select>
                                      #end </td>
                                    #end </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style75"><span class="style38">Sudah Meninggal Dunia</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label> #if($checkHidupWaris=="1")
                                      #set($chq="checked")
                                      #else
                                      #set($chq="")
                                      #end
                                      <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq $readmode value="$checkHidupWaris"  onclick="setSelected(0,2,0,0);tarikh_waris('checkHidupWaris','up')" />
                                      </label></td>
                                  </tr>
                                  #if($checkHidupWaris=="1")
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style38 style72">
                                        <div align="left"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Tarikh Mati #else Tarikh Mati #end</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txdTarikhMatiWaris" type="text" id="txdTarikhMatiWaris" value="$txdTarikhMatiWaris" size="10" maxlength="10" $readmodeR class="$readmode"  onblur="trans_date1(this.value)" />
                                      #if($readmode != "disabled") <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a><span class="style73"> dd/mm/yyyy</span> #end </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled")  #end</span></td>
                                    <td><div align="left" class="style75"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Waktu Kematian</span> #else Waktu Mati #end</div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" style="text-transform:uppercase;" onblur="jeniswaktu1()" value="$txtWaktuKematianWaris" size="4" maxlength="4" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtWaktuKematianWaris')"/>
                                      <label> #if($readmode=="disabled")
                                      #set($waktu = "")
                                      #if( $jeniswaktu == 1)
                                      #set($waktu = "PAGI")  
                                      #elseif($jeniswaktu == 2)
                                      #set($waktu = "TENGAHARI")                                   
                                      #elseif($jeniswaktu == 3)
                                      #set($waktu = "PETANG")      
                                      #elseif($jeniswaktu == 4)
                                      #set($waktu = "MALAM")                                   
                                      #else
                                      #set($waktu = "")
                                      #end
                                      <input name="jeniswaktu_d" type="text" id="jeniswaktu_d"    value="$waktu" size="4" maxlength="3" style="width:100"  $readmodeR class="$readmode" />
                                      <input name="jeniswaktu" type="hidden" id="jeniswaktu"    value="$jeniswaktu" />
                                      #else
                                      <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2()" >
                                        
                                  
                                  #if( $jeniswaktu == 1)
                                  
                                  
                                        <option value="1" id="op_pagi" >PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                                                            
                                    #elseif($jeniswaktu == 2)
                                      
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                        
                                    #elseif($jeniswaktu == 3)
                                     
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                        
                                       #elseif($jeniswaktu == 4)
                                        
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="">SILA PILIH</option>
                                        
                                    #else
                                  
                                        <option value="">SILA PILIH</option>
                                        <option value="1" id="op_pagi" >PAGI</option>
                                        <option value="2" id="op_tengahari" >TENGAHARI</option>
                                        <option value="3"  id="op_petang" >PETANG</option>
                                        <option value="4" id="op_malam" >MALAM</option>
                                                    
                                     
                                    #end                                
                                    
                                 
                                  
                                      </select>
                                      #end </label>
                                      #if($readmode != "disabled") <span class="style73">format 12 jam (HHMM)</span> #end </td>
                                  </tr>
                                  #end
                                </table></td>
                              <td width="50%" valign="top"><table width="100%" border="0">
                                  #if($readmode != "disabled")
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label> #if($check_copyP == "on")
                                      #set($ch_copyP = "checked")
                                      #else
                                      #set($ch_copyP = "")
                                      #end
                                      <input type="checkbox" name="copyAlamatP" id="copyAlamatP" $ch_copyP onclick="tarikh_waris_saudara_copyP('maklumat_pemohon','up')"  />
                                      <span class="style74 style42 style50" ><em>Alamat waris adalah alamat pemohon</em></span></label></td>
                                  </tr>
                                  #end
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris != 1) * #end</span></td>
                                    <td class="style38" width="29%" ><div align="right" class="style75">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Alamat Tetap #else Alamat Tetap #end</div>
                                      </div></td>
                                    <td width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%"><label>
                                      <input name="txtAlamatTerakhir1Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1Waris" value="$txtAlamatTerakhir1Waris" size="34" maxlength="40"  $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2Waris"  value="$txtAlamatTerakhir2Waris" size="34" maxlength="40" $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3Waris" value="$txtAlamatTerakhir3Waris" size="34" maxlength="40" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris != 1) * #end</span></td>
                                    <td class="style38"><div align="right" class="style75">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Poskod #else Poskod #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWaris" type="text" style="text-transform:uppercase;" onblur="text-transform:uppercase;" id="txtPoskodWaris" value="$txtPoskodWaris" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWaris')" $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris != 1) * #end</span></td>
                                    <td class="style38"><div align="right" class="style75">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Negeri #else Negeri #end</div>
                                      </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td> #if($readmode == "disabled")
                                      
                                      #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWaris==$listnegpomo.id_Negeri)
                                      
                                      #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                      #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                      
                                      
                                      
                                      #end 
                                      #end
                                      
                                      #if($socNegeriWaris!="" && $socNegeriWaris!="0" )
                                      <input name="nt" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="nt" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      
                                      #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWaris==$listnegpomo.id_Negeri)
                                      
                                      #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                      #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                      
                                      
                                      
                                      #end 
                                      #end
                                      #if($socNegeriWaris!="" && $socNegeriWaris!="0" )
                                      <select name="socNegeriWaris" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="tarikh_waris_saudara_negeritetap('txtBandarWaris','up')">
                                        <option value="$socNegeriWaris" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($socNegeriWaris!=$listnegpomo.id_Negeri)
                                    
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
                                      #else
                                      <select name="socNegeriWaris" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="tarikh_waris_saudara_negeritetap('txtBandarWaris','up')">
                                        <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
                                      #end </td>
                                    #end </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
         <tr id="tr_mesej_pelbagainegara">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat)1</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' $readmodeR class="$readmode" list = 'datalist'  value="$nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    
                                    
                                    
                                    
                                  
                                    
                                    
                                    
                                    
                                    
                                    </td>
        </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris != 1)  #end</span></td>
                                    <td class="style38"><div align="right" class="style75">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Bandar #else Bandar #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label> #if($readmode == "disabled")
                                      
                                      
                                      #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($txtBandarWaris==$listneg.id)                                      
                                      #set($kodbbb="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      
                                      
                                      #if($txtBandarWaris!="" && $txtBandarWaris!="0" )
                                      <input name="ntbb" value="$kodbbb" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="ntbb" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      
                                      #if($txtBandarWaris == "" || $txtBandarWaris=="0")
                                      #set($kodb="")
                                      
                                      #else
                                      
                                      #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($txtBandarWaris==$listneg.id)                                      
                                      #set($kodb="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      #end
                                      
                                      
                                      
                                      
                                      
                                      #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                                      #if($txtBandarWaris==$listdaerah.id)                                
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                      #end
                                      
                                      
                                      #if($disabled=="disabled")
                                      #set($readmodedaerah="disabled")
                                      #end
                                      #if($txtBandarWaris!="" && $txtBandarWaris!="0" )
                                      <select name="txtBandarWaris" id="txtBandarWaris" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                        <option value="$txtBandarWaris">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                        
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($txtBandarWaris!=$listdaerah.id)
              
                                  
                  
                  
                                        <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                        
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                                      </select>
                                      #else
                                      <select name="txtBandarWaris" id="txtBandarWaris" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                        <option value="">Sila Pilih Bandar</option>
                                        
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
                                        <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                        
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                

                                      </select>
                                      #end 
                                      #end </label></td>
                                  </tr>
                                  #if($readmode != "disabled")
                                  <tr>
                                    <td width="1%">&nbsp;</td>
                                    <td width="29%" class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td width="1%">&nbsp;</td>
                                    <td width="70%"><label> #if($check_copy == "on")
                                      #set($ch_copy = "checked")
                                      #else
                                      #set($ch_copy = "")
                                      #end
                                      <input type="checkbox" name="copyAlamat" id="copyAlamat" $ch_copy onclick="tarikh_waris_saudara_copy('maklumat_pemohon','up')"  />
                                      <span class="style74 style42 style50" ><em>Alamat surat menyurat adalah alamat tetap</em></span><span class="style74 style50" ></span><span class="style50" ></span></label></td>
                                  </tr>
                                  #end
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris != 1) * #end</span></td>
                                    <td class="style38" ><div align="right" class="style75">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Alamat Surat Menyurat #else Alamat Surat Menyurat #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1WarisSurat2" value="$txtAlamatTerakhir1WarisSurat" size="34"  $readmodeR class="$readmode"  />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2WarisSurat"  value="$txtAlamatTerakhir2WarisSurat" size="34" $readmodeR class="$readmode"  />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3WarisSurat" value="$txtAlamatTerakhir3WarisSurat" size="34" $readmodeR class="$readmode"  /></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris != 1) * #end</span></td>
                                    <td class="style38"><div align="right" class="style75">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Poskod #else Poskod #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$txtPoskodWarisSurat" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmodeR class="$readmode"  />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris != 1) * #end</span></td>
                                    <td class="style38"><div align="right" class="style75">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Negeri #else Negeri #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td> #if($readmode == "disabled")
                                      
                                      #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                      
                                      #set($negerikodpemoPs=$listnegpomo.kod_Negeri)
                                      #set($negeriketeranganpemoPs=$listnegpomo.nama_Negeri)
                                      
                                      
                                      
                                      #end 
                                      #end
                                      
                                      #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!="0" )
                                      <input name="ns" value="$negerikodpemoPs - $negeriketeranganpemoPs" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="ns" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                      
                                      #set($kod = "$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri")
                                      
                                      
                                      
                                      
                                      #end 
                                      #end
                                      #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!=0)
                                      <select name="socNegeriWarisSurat" class="autoselect" id="socNegeriWarisSurat" $readmode   onchange="tarikh_waris_saudara_negerisurat('txtBandarWarisSurat','up')" >
                                        <option value="$socNegeriWarisSurat" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                        
                                              
                                        
                  
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($socNegeriWarisSurat!=$listneg.id_Negeri)
                       
                  
                                        
                                              
                                        <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                                              
                                        
                  
                                     
                                  #end    
	                              #end
                                        
                
                                      
                                            
                                      </select>
                                      #else
                                      <select name="socNegeriWarisSurat" class="autoselect" $readmode  onchange="tarikh_waris_saudara_negerisurat('txtBandarWarisSurat','up')">
                                        <option value="0" >SILA PILIH NEGERI</option>
                                        
                                              
  
                  
                                  #foreach($listneg in $listnegeri)
                   
                  
  
                                              
                                        <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                                              
  
                  
                                    
	                               #end
                                        
                

                                            
                                      </select>
                                      #end 
                                      
                                      #end</td>
                                  </tr>
                                  
                                  
                                  
               <tr id="tr_mesej_pelbagainegara_surat">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara_surat"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara_surat">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat Surat)</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara_surat' name = 'nama_pelbagainegara_surat' size='30' maxlength='200' $readmodeR class="$readmode" list = 'datalist'  value="$nama_pelbagainegara_surat"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    
                                    
                                    
                                    
                                    
                                   
                                    
                                    
                                    </td>
        </tr>
                                  
                                  
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris != 1)  #end</span></td>
                                    <td class="style38"><div align="right" class="style75">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Bandar #else Bandar #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label> #if($readmode == "disabled")
                                      
                                      
                                      #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($txtBandarWarisSurat==$listneg.id)                                      
                                      #set($kodss="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      
                                      
                                      #if($txtBandarWarisSurat!="" && $txtBandarWarisSurat!="0" )
                                      <input name="ntbb" value="$kodss" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="ntbb" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      
                                      
                                      #if($txtBandarWarisSurat == "" || $txtBandarWarisSurat=="0")
                                      #set($kodbx="")
                                      
                                      #else
                                      
                                      #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($txtBandarWarisSurat==$listneg.id)                                      
                                      #set($kodbx="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      #end
                                      
                                      
                                      
                                      
                                      
                                      #foreach($listdaerah in $listBandarSuratbyNegeri)                                
                                      #if($txtBandarWarisSurat==$listdaerah.id)                                
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                      #end
                                      
                                      
                                      #if($disabled=="disabled")
                                      #set($readmodedaerah="disabled")
                                      #end
                                      #if($txtBandarWarisSurat!="" && $txtBandarWarisSurat!="0" )
                                      <select name="txtBandarWarisSurat" id="txtBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                                        <option value="$txtBandarWarisSurat">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                        
                                              
                  
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                 
                                  #if($txtBandarWarisSurat!=$listdaerah.id)
              
                                  
                  
                  
                  
                                              
                                        <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                        
                                              
                  
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                
                                            
                                      </select>
                                      #else
                                      <select name="txtBandarWarisSurat" id="txtBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                                        <option value="">Sila Pilih Bandar</option>
                                        
                                              
  
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
  
                                              
                                        <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                        
                                              
  
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                


                                            
                                      </select>
                                      #end 
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td class="style38" ><div align="right" class="style75">
                                        <div align="left">No Telefon Bimbit</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponWaris')"  style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoTeleponWaris" size="14" maxlength="12" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  #if($readmode != "disabled" )
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td>&nbsp;</td>
                                    <td valign="top"><span class="style73">cth: 031234567</span></td>
                                  </tr>
                                  #end
                                  <tr>
                                    <td valign="top">#if($readmode != "disabled" ) <span class="style38 style44">*</span>
   									 								#end</td>
                                    <td class="style38" ><div align="left" class="style72">No Telefon</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoTeleponBimbitWaris" size="14" maxlength="12" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponBimbitWaris')"/></td>
                                  </tr>
                                  #if($readmode != "disabled" )
                                  <tr>
                                    <td valign="top">&nbsp;</td>
                                    <td class="style38" valign="top"><div align="left"><span class="style72"></span></div></td>
                                    <td valign="top">&nbsp;</td>
                                    <td valign="top"><span class="style73">cth: 0121234567</span></td>
                                  </tr>
                                  #end
                                  <tr>
                                    <td valign="top">#if($readmode != "disabled" ) <span class="style38 style44">*</span>
   																	#end</td>
                                    <td class="style38" ><div align="left" class="style72">Emel</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtEmel" type="text" id="txtEmel" value="$txtEmel" size="30" maxlength="30" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td valign="top"><div align="right"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td class="style38" valign="top"><div align="right" class="style75">
                                        <div align="left">Catatan</div>
                                      </div></td>
                                    <td valign="top"><div align="right"><span class="style38">:</span></div></td>
                                    <td><textarea name="txtCatatanWaris" cols="31"  rows="3"  $readmodeR class="$readmode" >$txtCatatanWaris</textarea></td>
                                  </tr>
                                  <tr id="tr_flag_daftar"  style="display:none">
                                    <td valign="top"></td>
                                    <td  valign="top">Urusan Kemasukkan Maklumat Waris </td>
                                    <td valign="top">:</td>
                                    <td valign="top"> #if($readmode != "disabled" )
                                      
                                      #if($FLAG_DAFTAR == '1')
                                      #set($checked_flag_daftar1 = "checked")
                                      #set($checked_flag_daftar2 = "")   
                                      #elseif($FLAG_DAFTAR == '2') 
                                      #set($checked_flag_daftar2 = "checked")    
                                      #set($checked_flag_daftar1 = "") 
                                      #else
                                      #set($checked_flag_daftar1 = "checked")
                                      #set($checked_flag_daftar2 = "")                                   
                                      #end
                                      <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar1 id="FLAG_DAFTAR" 
                                          value="1" />
                                      Pendaftaran <br />
                                      <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar2 id="FLAG_DAFTAR" 
                                          value="2" />
                                      Perbicaraan
                                      
                                      
                                      #else
                                      
                                      #set($text_daftar = "")
                                      #if($FLAG_DAFTAR == '1')
                                      #set($text_daftar = "PENDAFTARAN")
                                      #elseif($FLAG_DAFTAR == '2') 
                                      #set($text_daftar = "PERBICARAAN")                                         
                                      #end
                                      <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />
                                      <input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
                                      #end </td>
                                  </tr>
                                  #if($!skrin_online != "yes")
                                  <script>
                                                    document.getElementById('tr_flag_daftar').style.display = "";
                                                    </script>
                                  #end
                                </table></td>
                            </tr>
                          </table>
                          </fieldset></td>
                      </tr>
                      #if($readmode != "disabled")
                      <tr>
                        <td><table width="100%">
                            <tr>
                              <td><span class="style5 style47 style69"><span class="style41">Perhatian </span>: Sila masukkan salah satu nombor MyID dan pastikan label bertanda <span class="style41">*</span> diisi.</span></td>
                            </tr>
                          </table></td>
                      </tr>
                      #end
                      <tr>
                        <td><table width="100%" border="0" align="center">
                            <tr>
                              <td> #if($open_button_online == "yes")
                                <div align="center"> #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                                  <!-- <input type="button" name="tambahwaris" id="tambahwaris" value="$buttonwaris"  onclick="setSelected(0,2,0,0);tambah_waris()"/> -->
                                  #end
                                  
                                  #if($boleh_kemaskini == "yes")
                                  #end
                                  
                                  #if($!skrin_online != "yes")
                                  <input type="button" name="tambahwaris" id="tambahwaris" value="$buttonwaris"  onclick="setSelected(0,2,0,0);tambah_waris()"/>
                                  #if($flag_kemaskini_selesai != "yes")
                                  <script>
                                                    document.getElementById('tambahwaris').style.display = "none";
                                                    </script>
                                  #end   
                                  
                                  #else
                                  <input type="button" name="tambahwaris" id="tambahwaris" value="Simpan"  onclick="setSelected(0,2,0,0);tambah_waris()"/>
                                  #end
                                  
                                  
                                  
                                  #if($buttonwaris=="Simpan" || $buttonwaris=="Kemaskini" )
                                  
                                  #if($listWarisLapisanIdMatiDelete.size()==0 && $buttonwaris=="Kemaskini")
                                  
                                  #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                                  <!-- <input type="button" name="tambahwarishapus" id="tambahwarishapus" value="Hapus" onclick="setSelected(0,2,0,0);hapus_waris()"/> -->
                                  #end
                                  
                                  #if($boleh_kemaskini == "yes")
                                  #end
                                  <input type="button" name="tambahwarishapus" id="tambahwarishapus" value="Hapus" onclick="setSelected(0,2,0,0);hapus_waris()"/>
                                  #if($flag_kemaskini_selesai != "yes")
                                  <script>
                                                    document.getElementById('tambahwarishapus').style.display = "none";
                                                    </script>
                                  #end  
                                  
                                  
                                  #end
                                  
                                  #end
                                  #if(($chq=="checked" ) && $buttonwaris=="Kemaskini")
                                  <input type="button" name="lapisanberikut" id="lapisanberikut" value="Lapisan Berikut"  onclick="setSelected(0,2,0,0);lapisan_waris()"/>
                                  #end
                                  
                                  
                                  
                                  #if($show_batal_waris=="yes")
                                  #if($!skrin_online != "yes")
                                  <input type="button" name="cmdSimpan7" id="cmdSimpan7" value="Batal"  onclick="warisbatalupdate()"/>
                                  #end
                                  #end
                                  <input type="submit" name="cmdKembali8" id="cmdKembali8" value="Kembali"  onclick="setSelected(0,2,0,0);WarisView()"/>
                                </div>
                                #end </td>
                            </tr>
                          </table></td>
                      </tr>
                      #end
                      #if($show_table_waris=="yes")
                      <tr>
                        <td width="100%"><fieldset>
                          <legend>MAKLUMAT WARIS </legend>
                          <table width="100%" border="0">
                            <tr>
                              <td width="50%" valign="top"><table width="100%">
                                  <input type="hidden" name="txtIdSimatiWaris" value="$idSimati" />
                                  <tr>
                                    <td width="1%"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td width="28%"><div align="left" class="style72"><span class="style38">MyID Baru</span></div></td>
                                    <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%" class="style36"><input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Waris" style="width: 50px;" type="text" value="$txtNoKPBaru1Waris" $readmode size="7" maxlength="6" 
                                          onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Waris');
                                          check_kp();
                                          check_pengenalan_simati_1()" 
                                          onBlur="calculateTarikhLahirWaris();"/>
                                          
                                          <!--onBlur="getAgeByIC(this,this.value,'txtUmurWaris');
                                          getStatusByIC(this,this.value,'socStatusOBWaris');
                                          getDOB(this.value)"/-->
                                      -
                                      <input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Waris" style="width: 20px;" type="text" value="$txtNoKPBaru2Waris" $readmode size="3" maxlength="2" 
                                          onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris');
                                          check_kp();
                                          check_pengenalan_simati_1()" onblur=""/>
                                      -
                                      <input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Waris"  style="width: 40px;" type="text" value="$txtNoKPBaru3Waris" $readmode size="5" maxlength="4" 
                                          onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris');check_kp();
                                          check_pengenalan_simati_1();" 
                                          
                                          onblur="jantinaic2();tarikh_waris_saudara('maklumat_pemohon','in')"
                                          />
                                      <span id="check_kp_1" style="color:red" ></span> #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") <a href="http://www.jpn.gov.my" target="_blank" class="style51 style74 style42"> www.jpn.gov.my</a> #end </td>
                                  </tr>
                                  <tr>
                                    <td >&nbsp;</td>
                                    <td ><div align="left" class="style72"><span class="style38">MyID Lama</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label>
                                      <input name="txtNoKPLamaWaris" id="txtNoKPLamaWaris" style="text-transform:uppercase;" onkeyup="check_kp_lama();check_pengenalan_simati_2()"  onblur="this.value=this.value.toUpperCase();suma_kp_lama()" type="text"  value="$txtNoKPLamaWaris" size="15" maxlength="15" $readmode/>
                                      </label>
                                      <span id="check_kp_2" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td >&nbsp;</td>
                                    <td ><div align="left" class="style72"><span class="style38">Jenis MyID Lain</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode onchange="kplain1(this.value)"  style="text-transform:uppercase;" onblur="kplain1X(this.value)" />
                                      
                                      #if($socJenisKPLainWaris=="5")
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="6")
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="4")
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="7")
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      #else
                                      <option value="0" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Jenis KP</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #end
                                      </select>
                                      <label></label></td>
                                  </tr>
                                  #if($readmode=="disabled")
                                  #set($readmodekp="disabled")
                                  #end
                                  
                                  #if($readmode=="")
                                  #if($socJenisKPLainWaris != 0 && $socJenisKPLainWaris != "")
                                  #set($readmodekp="")
                                  #else
                                  #set($readmodekp="disabled")
                                  #set($txtNoKPLainWaris = "")
                                  #end
                                  
                                  
                                  #end
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">MyID Lain</span></div></td>
                                    <td>:</td>
                                    <td><span class="style36">
                                      <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris2"  style="text-transform:uppercase;" onkeyup="check_kp_lain();check_pengenalan_simati_3()" onblur="this.value=this.value.toUpperCase();suma_kp_lain();" value="$txtNoKPLainWaris" size="15" maxlength="25" $readmodekp  />
                                      </span> <span id="check_kp_3" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38 ">Nama Waris</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBWaris" id="txtNamaOBWaris" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtNamaOBWaris" size="34" $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
									<td>
										<span class="style38"></span>
									</td>
									<td>
										<div align="left" class="style38">Nama Lain</div>
									</td>
										<td class="style36" style="text-transform: uppercase;">:</td>
											<td class="style36" style="text-transform: uppercase;">
												<label> <input name="txtNamaLainWaris" type="text" id="txtNamaLainWaris"
														style="text-transform: uppercase;" value="$txtNamaLainWaris" size="34" maxlength="30"
														$readmode  onBlur="this.value=this.value.toUpperCase()" />
											</label>
										</td>
								</tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Jantina</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label>
                                      <select name="socJantinaWaris" id="socJantinaWaris" onchange="setSelected(0,2,0,0);tarikh_waris_saudara('socAgamaWaris','in')" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;">
                                        
                                              
                                             
                                              
                                      
                                   #if($socJantinaWaris=="1")
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socJantinaWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Jantina</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">Agama</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label>
                                      <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;">
                                        
                                              
                                             
                                              
                                      
                                   #if($socAgamaWaris=="1")
	                               
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option >
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socAgamaWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                         
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                      
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" >&nbsp;</td>
                                    <td valign="top" ><div align="left" class="style72"><span class="style38">Warganegara</span></div></td>
                                    <td valign="top" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td valign="top" class="style36"><label>
                                      <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="autoselect" $readmode style="text-transform:uppercase;" onchange="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" onblur="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" >
                                        
                                              
                                             
                                              
                                      
                                   #if($socWarganegaraWaris=="1")
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        
                                           #if($txtNoKPBaru1Waris != "")
                                           
                                        <option value="2" style="text-transform:uppercase;display:none" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #else
                                           
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #end   
                                          
                                              
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socWarganegaraWaris=="2")
	                               
                                      
                                              
                                             
                                             #if($txtNoKPBaru1Waris != "")
                                           
                                        <option value="2" style="text-transform:uppercase;display:none" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #else
                                           
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #end   
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socWarganegaraWaris=="3")
	                               
                                      
                                              
                                             
                                              
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        
                                               #if($txtNoKPBaru1Waris != "")
                                           
                                        <option value="2" style="text-transform:uppercase;display:none" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #else
                                           
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #end   
                                              
                                      
                                   #else
                                   
                                 
                                              
                                             
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        
                                              
                                          #if($txtNoKPBaru1Waris != "")
                                           
                                        <option value="2" style="text-transform:uppercase;display:none" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #else
                                           
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #end      
                                             
                                              
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      <div id="alamatwarga"></div>
                                      </label>
                                    </td>
                                  </tr>
                                  <tr id="tr_nama_warga">
                                    <td valign="top" ></td>
                                    <td><div align="left" class="style38"> Negara </div></td>
                                    <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td ><input type="text" id="nama_warga" name="nama_warga" size="30" maxlength="200" $readmode  list="datalist"  value="$nama_warga"    />
                                      <datalist id = 'datalist'>#foreach($ja in $kenegaraan)
                                        <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                        #end </datalist>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">Tarikh Lahir</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txdTarikhLahirWaris" type="text" id="txdTarikhLahirWaris" value="$txdTarikhLahirWaris" size="10" maxlength="10" $readmode  onblur="trans_date(this.value);getAgebyDob(this,this.value,'txtUmurWaris');defineStatusWarisByUmur();"/>
                                      <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a><span class="style73"> dd/mm/yyyy</span></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">No Surat Beranak</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtNoSuratBeranakWaris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();suma_kp_beranak()"  onkeyup="check_pengenalan_simati_4()" type="text" id="txtNoSuratBeranakWaris" value="$txtNoSuratBeranakWaris" size="15" maxlength="10" $readmode/>
                                      <span id="check_kp_4" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Umur </span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtUmurWaris" style="text-transform:uppercase;" onblur="Checkumur();getStatusByAge(this,this.value,'socStatusOBWaris')" type="text" id="txtUmurWaris" value="$txtUmurWaris" size="3" maxlength="3" onkeyup="javascript:validateIC(event,this,this.value,'txtUmurWaris')" $readmode/></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Status Waris</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><select name="socStatusOBWaris"  style="text-transform:uppercase;" onblur="text-transform:uppercase;" class="mediumselect" $readmode id="socStatusOBWaris" onchange="status_ob()">
                                        
                                            
                                           
									
								   #if($socStatusOBWaris=="1")
	                                 
                                      
                                           
                                           
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                           
                                      
	                              
	                               #elseif($socStatusOBWaris=="2")
	                                
	                                  
                                           
                                            
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                           
                                      
	                              
								   #elseif($socStatusOBWaris=="3")
	                               
	                                 
                                           
                                            
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                    
                                     
                                   #elseif($socStatusOBWaris=="4")
	                                    
                                           
                                            
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                        
                                    
                                        
	                               #else
	                                 
                                      
                                           
                                            
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        
                                            
                                           
                                      
	                               #end
                                   
                                         
                                          
                                      </select>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Talian Persaudaraan</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    #foreach($listsau in $listsaudara)
                                    
                                    #if($socSaudaraWaris==$listsau.id_Saudara)
                                    
                                    #set($kodsaudara=$listsau.kod)
                                    #set($kodsaudaraketerangan=$listsau.keterangan)
                                    
                                    
                                    
                                    #end    
                                    #end
                                    <td> #if($socSaudaraWaris!="")
                                      <select name="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;" >
                                        <option value="$socSaudaraWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$kodsaudara - $kodsaudaraketerangan</option>
                                        
                                              
                                             
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($socSaudaraWaris!=$listsau.id_Saudara)
                                     #if($socJantinaWaris=="1")
                                          #set($jan="01")
                                      
                                          #elseif($socJantinaWaris=="2")
                                          #set($jan="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jan )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                              
                                   #end
                                   
                                   
                                    #if($listsau.id_Saudara=="27" )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                              
                                   #end
                                   
                                   
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
                                            
                                      </select>
                                      #else
                                      <select name="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;" onfocus="insertsaudara()" >
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Talian Persaudaraan</option>
                                        
                                              
                                  #foreach($listsau in $listsaudara)
                                         #if($socJantinaWaris=="1")
                                          #set($jantt="01")
                                          
                                          #elseif($socJantinaWaris=="2")
                                          #set($jantt="02")
                                          #end                                  
                                         
                                 #if($listsau.jantina==$jantt  )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                #end          
                                 
                                  #if($listsau.id_Saudara=="27"  )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                 #end             
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
                                            
                                      </select>
                                    </td>
                                    #end </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">Sudah Meninggal Dunia</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label> #if($checkHidupWaris=="1")
                                      #set($chq="checked")
                                      #else
                                      #set($chq="")
                                      #end
                                      <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq $readmode  onclick="setSelected(0,2,0,0);tarikh_waris('checkHidupWaris','in')" />
                                      </label></td>
                                  </tr>
                                  #if($show_tarikh=="yes")
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style72">
                                        <div align="left"><span class="style38">Tarikh Mati</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txdTarikhMatiWaris" type="text" id="txdTarikhMatiWaris" value="$txdTarikhMatiWaris" size="10" maxlength="10" $readmode  onblur="trans_date1(this.value)" />
                                      <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a><span class="style73"> dd/mm/yyyy</span></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled")  #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Waktu Kematian</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" style="text-transform:uppercase;" onblur="jeniswaktu1()" value="$txtWaktuKematianWaris" size="4" maxlength="4" $readmode onkeyup="javascript:validateIC(event,this,this.value,'txtWaktuKematianWaris')"/>
                                      <label> #if($readmode=="disabled")
                                      #set($waktu = "")
                                      #if( $jeniswaktu == 1)
                                      #set($waktu = "PAGI")  
                                      #elseif($jeniswaktu == 2)
                                      #set($waktu = "TENGAHARI")                                   
                                      #elseif($jeniswaktu == 3)
                                      #set($waktu = "PETANG")      
                                      #elseif($jeniswaktu == 4)
                                      #set($waktu = "MALAM")                                   
                                      #else
                                      #set($waktu = "")
                                      #end
                                      <input name="jeniswaktu_d" type="text" id="jeniswaktu_d"    value="$waktu" size="4" maxlength="3" style="width:100"  $readmodeR class="$readmode" />
                                      <input name="jeniswaktu" type="hidden" id="jeniswaktu"    value="$jeniswaktu" />
                                      #else
                                      <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2()" >
                                        
                                  
                                  #if( $jeniswaktu == 1)
                                  
                                  
                                        <option value="1" id="op_pagi" >PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                                                            
                                    #elseif($jeniswaktu == 2)
                                      
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                        
                                    #elseif($jeniswaktu == 3)
                                     
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                        
                                       #elseif($jeniswaktu == 4)
                                        
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="">SILA PILIH</option>
                                        
                                    #else
                                  
                                        <option value="">SILA PILIH</option>
                                        <option value="1" id="op_pagi" >PAGI</option>
                                        <option value="2" id="op_tengahari" >TENGAHARI</option>
                                        <option value="3"  id="op_petang" >PETANG</option>
                                        <option value="4" id="op_malam" >MALAM</option>
                                                    
                                     
                                    #end                                
                                    
                                 
                                  
                                      </select>
                                      #end </label>
                                      <span class="style73">format 12 jam (HHMM)</span></td>
                                  </tr>
                                  #end
                                </table></td>
                              <td width="40%" valign="top"><table width="100%" border="0">
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label> #if($check_copyP == "on")
                                      #set($ch_copyP = "checked")
                                      #else
                                      #set($ch_copyP = "")
                                      #end
                                      <input type="checkbox" name="copyAlamatP" id="copyAlamatP" $ch_copyP onclick="tarikh_waris_saudara_copyP('maklumat_pemohon','in')"  />
                                      <span class="style74 style42 style50" ><em>Alamat waris adalah alamat pemohon</em></span></label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38" width="28%" ><div align="right" class="style44 style72">
                                        <div align="left">Alamat Tetap</div>
                                      </div></td>
                                    <td width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%"><label>
                                      <input name="txtAlamatTerakhir1Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1Waris" value="$txtAlamatTerakhir1Waris" size="34" maxlength="40"  $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2Waris"  value="$txtAlamatTerakhir2Waris" size="34" maxlength="40" $readmode />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3Waris" value="$txtAlamatTerakhir3Waris" size="34" maxlength="40" $readmode/></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Poskod</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWaris" type="text" style="text-transform:uppercase;" onblur="text-transform:uppercase;" id="txtPoskodWaris" value="$txtPoskodWaris" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWaris')" $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Negeri</div>
                                      </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    #foreach($listnegpomo in $listnegeri)
                                    
                                    #if($socNegeriWaris==$listnegpomo.id_Negeri)
                                    
                                    #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                    #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                    
                                    
                                    
                                    #end 
                                    #end
                                    <td> #if($socNegeriWaris!="" && $socNegeriWaris!="0" )
                                      <select name="socNegeriWaris" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="tarikh_waris_saudara_negeritetap('txtBandarWaris','in')">
                                        <option value="$socNegeriWaris" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($socNegeriWaris!=$listnegpomo.id_Negeri)
                                    
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
                                      #else
                                      <select name="socNegeriWaris" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="tarikh_waris_saudara_negeritetap('txtBandarWaris','in')">
                                        <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
                                    </td>
                                    #end </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                       <tr id="tr_mesej_pelbagainegara">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat)4</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' $readmodeR class="$readmode" list = 'datalist'  value="$nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    
                                  
                                    
                                    </td>
        </tr>   
                                    
                                    
                                    
                                    
                                    
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1")  #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Bandar</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>#if($txtBandarWaris == "" || $txtBandarWaris=="0")
                                      #set($kodb="")
                                      
                                      #else
                                      
                                      #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($txtBandarWaris==$listneg.id)                                      
                                      #set($kodb="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      #end
                                      
                                      
                                      
                                      
                                      
                                      #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                                      #if($txtBandarWaris==$listdaerah.id)                                
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                      #end
                                      
                                      
                                      #if($disabled=="disabled")
                                      #set($readmodedaerah="disabled")
                                      #end
                                      #if($txtBandarWaris!="" && $txtBandarWaris!="0" )
                                      <select name="txtBandarWaris" id="txtBandarWaris" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                        <option value="$txtBandarWaris">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                        
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($txtBandarWaris!=$listdaerah.id)
              
                                  
                  
                  
                                        <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                        
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                                      </select>
                                      #else
                                      <select name="txtBandarWaris" id="txtBandarWaris" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                        <option value="">Sila Pilih Bandar</option>
                                        
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
                                        <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                        
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                

                                      </select>
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td width="1%">&nbsp;</td>
                                    <td width="29%" class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td width="1%">&nbsp;</td>
                                    <td width="70%"><label> #if($check_copy == "on")
                                      #set($ch_copy = "checked")
                                      #else
                                      #set($ch_copy = "")
                                      #end
                                      <input type="checkbox" name="copyAlamat" id="copyAlamat" $ch_copy onclick="tarikh_waris_saudara_copy('maklumat_pemohon','in')"  />
                                      <span class="style74 style42 style50" ><em>Alamat surat menyurat adalah alamat tetap</em></span><span class="style74 style50" ></span><span class="style50" ></span></label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38" ><div align="right" class="style44 style72">
                                        <div align="left">Alamat Surat Menyurat</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1WarisSurat2" value="$txtAlamatTerakhir1WarisSurat" size="34"  $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2WarisSurat"  value="$txtAlamatTerakhir2WarisSurat" size="34" $readmode />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3WarisSurat" value="$txtAlamatTerakhir3WarisSurat" size="34" $readmode/></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Poskod</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$txtPoskodWarisSurat" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Negeri</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td> #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                      
                                      #set($kod = "$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri")
                                      
                                      
                                      
                                      
                                      #end 
                                      #end
                                      
                                      
                                      #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!=0)
                                      <select name="socNegeriWarisSurat" class="autoselect" id="socNegeriWarisSurat"  onchange="tarikh_waris_saudara_negerisurat('txtBandarWarisSurat','in')" >
                                        <option value="$socNegeriWarisSurat" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                        
                                              
                                        
                  
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($socNegeriWarisSurat!=$listneg.id_Negeri)
                       
                  
                                        
                                              
                                        <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                                              
                                        
                  
                                     
                                  #end    
	                              #end
                                        
                
                                      
                                            
                                      </select>
                                      #else
                                      <select name="socNegeriWarisSurat" class="autoselect" onchange="tarikh_waris_saudara_negerisurat('txtBandarWarisSurat','in')">
                                        <option value="0" >SILA PILIH NEGERI</option>
                                        
                                              
  
                  
                                  #foreach($listneg in $listnegeri)
                   
                  
  
                                              
                                        <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                                              
  
                  
                                    
	                               #end
                                        
                

                                            
                                      </select>
                                      #end </td>
                                  </tr>
                                  
                                  
                                  
                                  
                                  
                                  
                                   <tr id="tr_mesej_pelbagainegara_surat">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara_surat"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara_surat">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat Surat)</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara_surat' name = 'nama_pelbagainegara_surat' size='30' maxlength='200' $readmodeR class="$readmode" list = 'datalist'  value="$nama_pelbagainegara_surat"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    
                                    </td>
        </tr>
                
                                  
                                  
                                  
                                  
                                  
                                  
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1")  #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Bandar</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>#if($txtBandarWarisSurat == "" || $txtBandarWarisSurat=="0")
                                      #set($kodbx="")
                                      
                                      #else
                                      
                                      #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($txtBandarWarisSurat==$listneg.id)                                      
                                      #set($kodbx="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      #end
                                      
                                      
                                      
                                      
                                      
                                      #foreach($listdaerah in $listBandarSuratbyNegeri)                                
                                      #if($txtBandarWarisSurat==$listdaerah.id)                                
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                      #end
                                      
                                      
                                      #if($disabled=="disabled")
                                      #set($readmodedaerah="disabled")
                                      #end
                                      #if($txtBandarWarisSurat!="" && $txtBandarWarisSurat!="0" )
                                      <select name="txtBandarWarisSurat" id="txtBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                                        <option value="$txtBandarWarisSurat">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                        
                                              
                  
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                 
                                  #if($txtBandarWarisSurat!=$listdaerah.id)
              
                                  
                  
                  
                  
                                              
                                        <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                        
                                              
                  
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                
                                            
                                      </select>
                                      #else
                                      <select name="txtBandarWarisSurat" id="txtBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                                        <option value="">Sila Pilih Bandar</option>
                                        
                                              
  
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
  
                                              
                                        <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                        
                                              
  
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                


                                            
                                      </select>
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td class="style38" ><div align="right" class="style38 style72">
                                        <div align="left">No Telefon</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponWaris')"  style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoTeleponWaris" size="14" maxlength="12" $readmode /></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td>&nbsp;</td>
                                    <td valign="top"><span class="style73">cth: 031234567</span></td>
                                  </tr>
                                  <tr>
                                     <td valign="top">#if($readmode != "disabled" ) <span class="style38 style44">*</span>
   									 #end</td>
                                    <td class="style38" ><div align="left" class="style72">No Telefon Bimbit</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoTeleponBimbitWaris" size="14" maxlength="12" $readmode onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponBimbitWaris')"/></td>
                                  </tr>
                                  <tr>
                                    <td valign="top">&nbsp;</td>
                                    <td class="style38" valign="top"><div align="left"><span class="style72"></span></div></td>
                                    <td valign="top">&nbsp;</td>
                                    <td valign="top"><span class="style73">cth: 0121234567</span></td>
                                  </tr>
                                  <tr>
                                     <td valign="top">#if($readmode != "disabled" ) <span class="style38 style44">*</span>
   										#end</td>
                                    <td class="style38" ><div align="left" class="style72">Emel</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtEmel" type="text" id="txtEmel" value="$txtEmel" size="30" maxlength="30" $readmode class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td valign="top"><div align="right"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td class="style38" valign="top"><div align="right" class="style38 style72">
                                        <div align="left">Catatan</div>
                                      </div></td>
                                    <td valign="top"><div align="right"><span class="style38">:</span></div></td>
                                    <td><textarea name="txtCatatanWaris" cols="31"  rows="3"  $readmode >$txtCatatanWaris</textarea></td>
                                  </tr>
                                  <tr id="tr_flag_daftar"  style="display:none">
                                    <td valign="top"></td>
                                    <td  valign="top">Urusan Kemasukkan Maklumat Waris</td>
                                    <td valign="top">:</td>
                                    <td valign="top"> #if($readmode != "disabled" )
                                      
                                      #if($FLAG_DAFTAR == '1')
                                      #set($checked_flag_daftar1 = "checked")
                                      #set($checked_flag_daftar2 = "")   
                                      #elseif($FLAG_DAFTAR == '2') 
                                      #set($checked_flag_daftar2 = "checked")    
                                      #set($checked_flag_daftar1 = "") 
                                      #else
                                      #set($checked_flag_daftar1 = "checked")
                                      #set($checked_flag_daftar2 = "")                                   
                                      #end
                                      <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar1 id="FLAG_DAFTAR" 
                                          value="1" />
                                      Pendaftaran <br />
                                      <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar2 id="FLAG_DAFTAR" 
                                          value="2" />
                                      Perbicaraan
                                      
                                      
                                      #else
                                      
                                      #set($text_daftar = "")
                                      #if($FLAG_DAFTAR == '1')
                                      #set($text_daftar = "PENDAFTARAN")
                                      #elseif($FLAG_DAFTAR == '2') 
                                      #set($text_daftar = "PERBICARAAN")                                         
                                      #end
                                      <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />
                                      <input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
                                      #end </td>
                                      
                                      
                                  </tr>
                                  #if($!skrin_online != "yes")
                                  <script>
                                                    document.getElementById('tr_flag_daftar').style.display = "";
                                                    </script>
                                  #end
                                </table></td>
                            </tr>
                            
                            
                          </table>
                          </fieldset></td>
                      </tr>
                      #if($readmode != "disabled")
                      <tr>
                        <td><table width="100%">
                            <tr>
                              <td><span class="style5 style47 style69"><span class="style41">Perhatian </span>: Sila masukkan salah satu nombor MyID dan pastikan label bertanda <span class="style41">*</span> diisi.</span></td>
                            </tr>
                          </table></td>
                      </tr>
                      #end
                      <tr>
                        <td><table width="100%" border="0" align="center">
                            <tr>
                              <td><label>
                                #if($open_button_online == "yes")
                                <div align="center"> #if($buttonwarisSimpan!="")
                                  #if($!skrin_online != "yes")
                                  <input type="button" name="tambahwarisSimpan" id="tambahwarisSimpan" value="$buttonwarisSimpan"  onclick="setSelected(0,2,0,0);tambah_waris_Simpan()"/>
                                  #if($flag_kemaskini_selesai != "yes")
                                  <script>
                                                    document.getElementById('tambahwarisSimpan').style.display = "none";
                                                    </script>
                                  #end   
                                  
                                  
                                  #else
                                  <input type="button" name="tambahwarisSimpan" id="tambahwarisSimpan" value="Simpan"  onclick="setSelected(0,2,0,0);tambah_waris_Simpan()"/>
                                  #end
                                  
                                  #end 
                                  #if($buttonwarisSimpan=="")
                                  #if($!skrin_online != "yes")
                                  <input type="button" name="tambahwaris2" id="tambahwaris2" value="$buttonwaris"  onclick="setSelected(0,2,0,0);tambah_waris()"/>
                                  #if($flag_kemaskini_selesai != "yes")
                                  <script>
                                                    document.getElementById('tambahwaris2').style.display = "none";
                                                    </script>
                                  #end  
                                  #else
                                  <input type="button" name="tambahwaris2" id="tambahwaris2" value="Simpan"  onclick="setSelected(0,2,0,0);tambah_waris()"/>
                                  #end
                                  #end
                                  
                                  
                                  
                                  #if($show_batal_waris=="yes")
                                  #if($!skrin_online != "yes")
                                  <input type="reset" name="cmdSimpan7" id="cmdSimpan7" value="Batal" onclick="setSelected(0,2,0,0);Cancel_Baru()" />
                                  #end
                                  
                                  #end
                                  <input type="submit" name="cmdKembali8" id="cmdKembali8" value="Kembali"  onclick="setSelected(0,2,0,0);WarisView()"/>
                                </div>
                                #end
                                </label>
                              </td>
                            </tr>
                          </table></td>
                      </tr>
                      #end
                      #if($show_senarai_lapis_pertama == "yes")
                      <tr>
                        <td><fieldset>
                          <legend>SENARAI WARIS LAPISAN PERTAMA</legend>
                          <table width="100%">
                            <tr>
                              <td> #if($show_tambah_waris1=="yes")
                                #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                                <!--   <input type="button" name="naktambah" id="naktambah" value="Tambah" onclick="setSelected(0,2,0,0);new_waris()"/> -->
                                #end
                                #if($open_button_online == "yes") 
                                #if($boleh_kemaskini == "yes")
                                #end
                                <input type="button" name="naktambah" id="naktambah" value="Tambah" onclick="setSelected(0,2,0,0);new_waris()"/>
                                #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('naktambah').style.display = "none";
                                </script>
                                #end   
                                
                                
                                #end
                                
                                #parse("app/ppk/ButtonJanaFaraid.jsp")
                                <!--
        <input type="button" name="button2" id="button2" value="Jana Perakuan Faraid" onClick = "janaFaraid('$id','$idSimati')" /> 
        --->
                                #end
                                #if($button_Kembali1=="yes")
                                <!--
          <input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali"  onclick="kembali_simati()" />
          -->
                                #end </td>
                            </tr>
                          </table>
                          <table width="100%" >
                            <tr >
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
                                      <td width="20%"><div align="center">STATUS</div></td>
                                      <td width="15%"><div align="center">LAPISAN</div></td>
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
                                      <td><div align="center">STATUS</div></td>
                                      <td><div align="center">LAPISAN</div></td>
                                    </tr>
                                    #set($nowa=0)
                                    #foreach($listwaris in $listWaris)
                                    
                                    #set($nowa=$nowa+1)
                                    #if($nowa%2!=0)
                                    <tr >
                                      <td width="5%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
                                      <td width="20%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris('$listwaris.idwaris')" class="style42"> $listwaris.nama_Ob</a></div></td>
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
                                      <td width="20%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                                      <td width="15%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.lapis</div></td>
                                    </tr>
                                    #else
                                    <tr class="table_header">
                                      <!-- 
                                             <td><div align="center"><a href="javascript:edit_item_waris('$listwaris.idwaris', '$listwaris.nama_Ob', '$listwaris.nokpbaru1','$listwaris.nokpbaru2','$listwaris.nokpbaru3','$listwaris.idSimati','$listwaris.nokplama','$listwaris.jeniskp','$listwaris.nokplain','$listwaris.idnegeri','$listwaris.noTel','$listwaris.jantina','$listwaris.alamat1','$listwaris.alamat2','$listwaris.alamat3','$listwaris.bandar','$listwaris.agama','$listwaris.catatan','$listwaris.warga','$listwaris.poskod','$listwaris.statushidup','$listwaris.tarikhmati','$listwaris.waktumati','$listwaris.nohp','$listwaris.status_Ob','$listwaris.dob','$listwaris.saudara','$listwaris.umur','$show_table_waris')"> $listwaris.nama_Ob</a></div>
                                             -->
                                      <td width="5%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
                                      <td width="20%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris('$listwaris.idwaris')" class="style43"> $listwaris.nama_Ob</a></div></td>
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
                                      <td width="20%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                                      <td width="15%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.lapis</div></td>
                                    </tr>
                                    #end
                                    
                                    
                                    
                                    
                                    #end
                                  </table>
                                  #end </div></td>
                            </tr>
                          </table>
                          </fieldset>
                          #end </td>
                      </tr>
                      #end     
                      #if($show_lapisan_bawah=="yes")
                      <tr>
                        <td><fieldset>
                          <legend>SENARAI WARIS LAPISAN BERIKUT</legend>
                          <table width="100%">
                            #set($idwww=$idparent)
                            <input type="hidden" name="idwarislapisX" value="$idwww" />
                            <input type="hidden" name="idparentlapisX" value="" />
                          </table>
                          <div align="center"> #if($listWarisLapisanIdMati.size()==0)
                            <table width="100%">
                              <tr class="table_header">
                                <td width="5%"><div align="center">NO</div></td>
                                <td width="20%"><div align="left">NAMA WARIS</div></td>
                                <td width="15%"><div align="center">MyID BARU</div></td>
                                <td width="5%"><div align="center">UMUR</div></td>
                                <td width="15%"><div align="left">TALIAN PERSAUDARAAN</div></td>
                                <td width="20%"><div align="left">NAMA WARIS YANG MENINGGAL</div></td>
                                <td width="10%"><div align="center">STATUS</div></td>
                                <td width="10%"><div align="center">LAPISAN</div></td>
                              </tr>
                              <!--   <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" /> -->
                            </table>
                            <table width="100%">
                              <tr bgcolor="white">
                                <td align="left"> Tiada Rekod </td>
                              </tr>
                            </table>
                            #else
                            <table width="100%">
                              <tr class="table_header">
                                <td width="5%"><div align="center">NO</div></td>
                                <td width="20%"><div align="left">NAMA WARIS</div></td>
                                <td width="15%"><div align="center" >MyID BARU</div></td>
                                <td width="5%"><div align="center" >UMUR</div></td>
                                <td width="15%"><div align="left" >TALIAN PERSAUDARAAN</div></td>
                                <td width="20%"><div align="left" >NAMA WARIS YANG MENINGGAL</div></td>
                                <td width="10%"><div align="center" >STATUS</div></td>
                                <td width="10%"><div align="center" >LAPISAN</div></td>
                              </tr>
                              #set($nowar=0)
                              #foreach($listwarislapisan in $listWarisLapisanIdMati)
                              
                              #set($nowar=$nowar+1)
                              #if($nowar%2!=0)
                              <tr >
                                <td width="5%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$nowar</div></td>
                                <td width="20%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan_X('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                                <td width="15%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                                <td width="5%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.umur</div></td>
                                #foreach($listsaudaralist in $listsaudara)
                                
                                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                                
                                #set($wariskodsaudara=$listsaudaralist.kod)
                                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                                
                                
                                #end    
                                #end
                                <td width="15%" class="row1"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                                <td width="20%" class="row1"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nama_parent</div></td>
                                #if($listwarislapisan.statushidup=="0")
                                #set($hidup="Masih Hidup")
                                #end
                                #if($listwarislapisan.statushidup=="1")
                                #set($hidup="Sudah Meninggal")
                                #end
                                #if($listwarislapisan.statushidup=="")
                                #set($hidup="")
                                #end
                                <td width="10%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                                <td width="10%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
                              </tr>
                              #else
                              <tr class="table_header">
                                <td width="5%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$nowar</div></td>
                                <td width="20%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan_X('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                                <td width="15%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                                <td width="5%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.umur</div></td>
                                #foreach($listsaudaralist in $listsaudara)
                                
                                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                                
                                #set($wariskodsaudara=$listsaudaralist.kod)
                                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                                
                                
                                #end    
                                #end
                                <td width="15%" class="row2"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                                <td width="20%" class="row2"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nama_parent</div></td>
                                #if($listwarislapisan.statushidup=="0")
                                #set($hidup="Masih Hidup")
                                #end
                                #if($listwarislapisan.statushidup=="1")
                                #set($hidup="Sudah Meninggal")
                                #end
                                #if($listwarislapisan.statushidup=="")
                                #set($hidup="")
                                #end
                                <td width="10%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                                <td width="10%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
                              </tr>
                              #end
                              #end
                            </table>
                            #end </div>
                          </fieldset></td>
                      </tr>
                      #end 
                      
                      
                      
                      
                      
                      
                      #if($show_lapisan_berikut=="yes")
                      
                      #if($show_lapisan_berikut_tambah=="yes")
                      <tr>
                        <td><fieldset>
                          <legend>MAKLUMAT WARIS LAPISAN BERIKUT </legend>
                          <table width="100%" border="0">
                            <tr>
                              <td width="50%" valign="top"><table width="100%">
                                  <input type="hidden" name="txtIdSimatiWaris" value="$idSimati" />
                                  <tr>
                                    <td class="style36" width="1%">&nbsp;</td>
                                    <td width="28%"><div align="left" class="style72"><span class="style38"> Waris Yang Meninggal</span></div></td>
                                    <input type="hidden" name="txtIdSimatiWaris" value="$id_Simati" />
                                    <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    #foreach($listWarisParentlist in $listWarisParent)
                                    <td class="style36" width="70%"><span class="style42" style="text-transform:uppercase;" onblur="uppercase()">$listWarisParentlist.nama_Ob</span>
                                      <input name="txtIdParent" type="hidden"  id="textfield31" size="15" value="$listWarisParentlist.idwaris" maxlength="2"  $readmode/>
                                      #set($ip=$listWarisParentlist.idwaris)
                                      #set($lp=$listWarisParentlist.lapis)
                                      <input name="txtLapisParent" type="hidden"  id="textfield31" size="15" value="$listWarisParentlist.lapis" maxlength="2"  $readmode/>
                                    </td>
                                    #end </tr>
                                  <tr>
                                    <td width="1%" class="style36">&nbsp;</td>
                                    <td width="29%"><div align="left" class="style72"><span class="style38">MyID Baru</span></div></td>
                                    <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%" class="style36"><input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Waris" style="width: 50px;" type="text" value="$txtNoKPBaru1Waris" $readmode size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Waris');check_kp()" onBlur="calculateTarikhLahirWaris();"/><!-- onBlur="getAgeByIC(this,this.value,'txtUmurWaris');getStatusByIC(this,this.value,'socStatusOBWaris');getDOB(this.value)"/-->
                                      -
                                      <input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Waris" style="width: 20px;" type="text" value="$txtNoKPBaru2Waris" $readmode size="3" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris');check_kp()" />
                                      -
                                      <input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Waris"  style="width: 40px;" type="text" value="$txtNoKPBaru3Waris" $readmode size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris');check_kp()" onblur="suma_kp_baru_lap()" />
                                      <span id="check_kp_1" style="color:red" ></span> #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") <a href="http://www.jpn.gov.my" target="_blank" class="style51 style74 style42"> www.jpn.gov.my</a> #end </td>
                                  	<!-- ;check_pengenalan_simati_1() -->
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td ><div align="left" class="style72"><span class="style38">MyID Lama</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label>
                                      <input name="txtNoKPLamaWaris" id="txtNoKPLamaWaris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();suma_kp_lama_lap()" type="text"  value="$txtNoKPLamaWaris" size="15" maxlength="8" $readmode onkeyup="check_kp_lama();check_pengenalan_simati_2()"/>
                                      </label>
                                      <span id="check_kp_2" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td ><div align="left" class="style72"><span class="style38">Jenis MyID Lain</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode onchange="kplain1(this.value)"  style="text-transform:uppercase;" onblur="kplain1X(this.value)" />
                                      
                                      #if($socJenisKPLainWaris=="5")
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="6")
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="4")
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="7")
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      #else
                                      <option value="0" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Jenis KP</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #end
                                      </select>
                                      <label></label></td>
                                  </tr>
                                  #if($readmode=="disabled")
                                  #set($readmodekp="disabled")
                                  #end
                                  
                                  #if($readmode=="")
                                  #if($socJenisKPLainWaris != 0 && $socJenisKPLainWaris != "")
                                  #set($readmodekp="")
                                  #else
                                  #set($readmodekp="disabled")
                                  #set($txtNoKPLainWaris = "")
                                  #end
                                  
                                  
                                  #end
                                  <tr>
                                    <td></td>
                                    <td><div align="left" class="style72"><span class="style38">MyID Lain</span></div></td>
                                    <td>:</td>
                                    <td><span class="style36">
                                      <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();suma_kp_lain_lap()" onkeyup=";check_kp_lain();check_pengenalan_simati_3()" value="$txtNoKPLainWaris" size="15" maxlength="25" $readmodekp />
                                      </span> <span id="check_kp_3" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38 ">Nama Waris</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBWaris" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtNamaOBWaris" value="$txtNamaOBWaris" size="34" $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44"></span></td>
                                    <td><div align="left" class="style72"><span class="style38 ">Nama Lain</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaLainWaris" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtNamaLainWaris" value="$txtNamaLainWaris" size="34" $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Jantina</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label>
                                      <select name="socJantinaWaris" id="socJantinaWaris" onchange="setSelected(0,2,0,0);tarikh_waris_saudara('socAgamaWaris','in_lap')" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;">
                                        
                                              
                                             
                                              
                                      
                                   #if($socJantinaWaris=="1")
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socJantinaWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Jantina</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">Agama</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label>
                                      <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;">
                                        
                                              
                                             
                                              
                                      
                                   #if($socAgamaWaris=="1")
	                               
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option >
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socAgamaWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                          
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                      
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style36" valign="top">&nbsp;</td>
                                    <td valign="top" ><div align="left" class="style72"><span class="style38">Warganegara</span></div></td>
                                    <td valign="top" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td valign="top" class="style36"><label>
                                      <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="autoselect" $readmode style="text-transform:uppercase;" onchange="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" onblur="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" >
                                        
                                              
                                             
                                              
                                      
                                   #if($socWarganegaraWaris=="1")
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        
                                               #if($txtNoKPBaru1Waris != "")
                                           
                                        <option value="2" style="text-transform:uppercase;display:none" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #else
                                           
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #end   
                                              
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socWarganegaraWaris=="2")
	                               
                                      
                                              
                                             
                                             #if($txtNoKPBaru1Waris != "")
                                           
                                        <option value="2" style="text-transform:uppercase;display:none" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #else
                                           
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #end   
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socWarganegaraWaris=="3")
	                               
                                      
                                              
                                             
                                              
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        
                                              #if($txtNoKPBaru1Waris != "")
                                           
                                        <option value="2" style="text-transform:uppercase;display:none" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #else
                                           
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #end   
                                              
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                             
                                              
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        
                                               #if($txtNoKPBaru1Waris != "")
                                           
                                        <option value="2" style="text-transform:uppercase;display:none" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #else
                                           
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                          #end   
                                              
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      <div id="alamatwarga"></div>
                                      </label>
                                    </td>
                                  </tr>
                                  <tr id="tr_nama_warga">
                                    <td valign="top" ></td>
                                    <td><div align="left" class="style38"> Negara </div></td>
                                    <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input type = 'text' id = 'nama_warga' name = 'nama_warga' size='30' maxlength='200' $readmode  list = 'datalist'  value="$nama_warga"    />
                                      <datalist id = 'datalist'>#foreach($ja in $kenegaraan)
                                        <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                        #end </datalist>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">Tarikh Lahir</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txdTarikhLahirWaris" type="text" id="txdTarikhLahirWaris" value="$txdTarikhLahirWaris" size="10" maxlength="10" $readmode  onblur="trans_date(this.value);getAgebyDob(this,this.value,'txtUmurWaris');defineStatusWarisByUmur();"/>
                                      <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a><span class="style73"> dd/mm/yyyy</span></td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">No Surat Beranak</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtNoSuratBeranakWaris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();suma_kp_beranak_lap()"  onkeyup="check_pengenalan_simati_4()" type="text" id="txtNoSuratBeranakWaris" value="$txtNoSuratBeranakWaris" size="15" maxlength="10" $readmode/>
                                      <span id="check_kp_4" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Umur</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtUmurWaris" style="text-transform:uppercase;" onblur="Checkumur();getStatusByAge(this,this.value,'socStatusOBWaris')" type="text" id="txtUmurWaris" value="$txtUmurWaris" size="3" maxlength="3" onkeyup="javascript:validateIC(event,this,this.value,'txtUmurWaris')" $readmode/></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Status Waris</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><select name="socStatusOBWaris"  style="text-transform:uppercase;" onblur="text-transform:uppercase;" class="mediumselect" $readmode id="socStatusOBWaris" onchange="status_ob()">
                                        
                                            
                                           
									
								   #if($socStatusOBWaris=="1")
	                                 
                                      
                                           
                                           
                                            
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                            
                                      
	                              
	                               #elseif($socStatusOBWaris=="2")
	                                
	                                  
                                           
                                            
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                      
                                      
	                              
								   #elseif($socStatusOBWaris=="3")
	                               
	                                 
                                           
                                            
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                      
                                     
                                   #elseif($socStatusOBWaris=="4")
	                                    
                                           
                                            
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                        
                                    
                                        
	                               #else
	                                 
                                      
                                           
                                            
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        
                                            
                                           
                                      
	                               #end
                                   
                                         
                                          
                                      </select>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Talian Persaudaraan</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    #foreach($listsau in $listsaudara)
                                    
                                    #if($socSaudaraWaris==$listsau.id_Saudara)
                                    
                                    #set($kodsaudara=$listsau.kod)
                                    #set($kodsaudaraketerangan=$listsau.keterangan)
                                    
                                    
                                    
                                    #end    
                                    #end
                                    <td> #if($socSaudaraWaris!="")
                                      <select name="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;" >
                                        <option value="$socSaudaraWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$kodsaudara - $kodsaudaraketerangan</option>
                                        
                                              
                                             
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($socSaudaraWaris!=$listsau.id_Saudara)
                                     #if($socJantinaWaris=="1")
                                          #set($jan="01")
                                      
                                          #elseif($socJantinaWaris=="2")
                                          #set($jan="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jan )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                              
                                   #end
                                   
                                   
                                     #if($listsau.id_Saudara=="27" )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                              
                                   #end
                                   
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
                                            
                                      </select>
                                      #else
                                      <select name="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;" onfocus="insertsaudara()" >
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Talian Persaudaraan</option>
                                        
                                              
                                  #foreach($listsau in $listsaudara)
                                         #if($socJantinaWaris=="1")
                                          #set($jantt="01")
                                          
                                          #elseif($socJantinaWaris=="2")
                                          #set($jantt="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jantt )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                 #end            
                                 
                                    #if($listsau.id_Saudara=="27" )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                 #end             
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
                                            
                                      </select>
                                    </td>
                                    #end </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td><div align="left" class="style72"><span class="style38">Sudah Meninggal Dunia</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label> #if($checkHidupWaris=="1")
                                      #set($chq="checked")
                                      #else
                                      #set($chq="")
                                      #end
                                      <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq  $readmode  onclick="setSelected(0,2,0,0);tarikh_waris('checkHidupWaris','in_lap')" />
                                      </label></td>
                                  </tr>
                                  #if($show_tarikh=="yes")
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style72">
                                        <div align="left"><span class="style38">Tarikh Mati</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txdTarikhMatiWaris" type="text" id="txdTarikhMatiWaris" value="$txdTarikhMatiWaris" size="10" maxlength="10" $readmode  onblur="trans_date1(this.value)" />
                                      <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> <span class="style73">dd/mm/yyyy</span></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled")  #end</span></td>
                                    <td><div align="left" class="style72"><span class="style38">Waktu Kematian</span></div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" style="text-transform:uppercase;" onblur="jeniswaktu1()" value="$txtWaktuKematianWaris" size="4" maxlength="4" $readmode onkeyup="javascript:validateIC(event,this,this.value,'txtWaktuKematianWaris')"/>
                                      <label> #if($readmode=="disabled")
                                      #set($waktu = "")
                                      #if( $jeniswaktu == 1)
                                      #set($waktu = "PAGI")  
                                      #elseif($jeniswaktu == 2)
                                      #set($waktu = "TENGAHARI")                                   
                                      #elseif($jeniswaktu == 3)
                                      #set($waktu = "PETANG")      
                                      #elseif($jeniswaktu == 4)
                                      #set($waktu = "MALAM")                                   
                                      #else
                                      #set($waktu = "")
                                      #end
                                      <input name="jeniswaktu_d" type="text" id="jeniswaktu_d"    value="$waktu" size="4" maxlength="3" style="width:100"  $readmodeR class="$readmode" />
                                      <input name="jeniswaktu" type="hidden" id="jeniswaktu"    value="$jeniswaktu" />
                                      #else
                                      <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2()" >
                                        
                                  
                                  #if( $jeniswaktu == 1)
                                  
                                  
                                        <option value="1" id="op_pagi" >PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                                                            
                                    #elseif($jeniswaktu == 2)
                                      
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                        
                                    #elseif($jeniswaktu == 3)
                                     
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                        
                                       #elseif($jeniswaktu == 4)
                                        
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="">SILA PILIH</option>
                                        
                                    #else
                                  
                                        <option value="">SILA PILIH</option>
                                        <option value="1" id="op_pagi" >PAGI</option>
                                        <option value="2" id="op_tengahari" >TENGAHARI</option>
                                        <option value="3"  id="op_petang" >PETANG</option>
                                        <option value="4" id="op_malam" >MALAM</option>
                                                    
                                     
                                    #end                                
                                    
                                 
                                  
                                      </select>
                                      #end </label>
                                      <span class="style73">format 12 jam (HHMM)</span></td>
                                  </tr>
                                  #end
                                </table></td>
                              <td width="40%" valign="top"><table width="100%" border="0">
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label> #if($check_copyP == "on")
                                      #set($ch_copyP = "checked")
                                      #else
                                      #set($ch_copyP = "")
                                      #end
                                      <input type="checkbox" name="copyAlamatP" id="copyAlamatP" $ch_copyP onclick="tarikh_waris_saudara_copyP('maklumat_pemohon','in_lap')"  />
                                      <span class="style74 style42 style50" ><em>Alamat waris adalah alamat pemohon</em></span></label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38" ><div align="right" class="style44 style72">
                                        <div align="left">Alamat Tetap</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir1Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1Waris" value="$txtAlamatTerakhir1Waris" size="34" maxlength="40"  $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2Waris"  value="$txtAlamatTerakhir2Waris" size="34" maxlength="40" $readmode />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3Waris" value="$txtAlamatTerakhir3Waris" size="34" maxlength="40" $readmode/></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Poskod</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWaris" type="text" style="text-transform:uppercase;" onblur="text-transform:uppercase;" id="txtPoskodWaris" value="$txtPoskodWaris" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWaris')" $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Negeri</div>
                                      </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    #foreach($listnegpomo in $listnegeri)
                                    
                                    #if($socNegeriWaris==$listnegpomo.id_Negeri)
                                    
                                    #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                    #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                    
                                    
                                    
                                    #end 
                                    #end
                                    <td> #if($socNegeriWaris!="" && $socNegeriWaris!="0" )
                                      <select name="socNegeriWaris" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="tarikh_waris_saudara_negeritetap('txtBandarWaris','in_lap')">
                                        <option value="$socNegeriWaris" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($socNegeriWaris!=$listnegpomo.id_Negeri)
                                    
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
                                      #else
                                      <select name="socNegeriWaris" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="tarikh_waris_saudara_negeritetap('txtBandarWaris','in_lap')">
                                        <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
                                    </td>
                                    #end </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                          <tr id="tr_mesej_pelbagainegara">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat)2</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' $readmodeR class="$readmode" list = 'datalist'  value="$nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    
                                 
                                    
                                    
                                    </td>
        </tr>
              
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1")  #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Bandar</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>#if($txtBandarWaris == "" || $txtBandarWaris=="0")
                                      #set($kodb="")
                                      
                                      #else
                                      
                                      #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($txtBandarWaris==$listneg.id)                                      
                                      #set($kodb="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      #end
                                      
                                      
                                      
                                      
                                      
                                      #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                                      #if($txtBandarWaris==$listdaerah.id)                                
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                      #end
                                      
                                      
                                      #if($disabled=="disabled")
                                      #set($readmodedaerah="disabled")
                                      #end
                                      #if($txtBandarWaris!="" && $txtBandarWaris!="0" )
                                      <select name="txtBandarWaris" id="txtBandarWaris" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                        <option value="$txtBandarWaris">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                        
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($txtBandarWaris!=$listdaerah.id)
              
                                  
                  
                  
                                        <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                        
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                                      </select>
                                      #else
                                      <select name="txtBandarWaris" id="txtBandarWaris" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                        <option value="">Sila Pilih Bandar</option>
                                        
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
                                        <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                        
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                

                                      </select>
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td width="1%">&nbsp;</td>
                                    <td width="29%" class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td width="1%">&nbsp;</td>
                                    <td width="70%"><label> #if($check_copy == "on")
                                      #set($ch_copy = "checked")
                                      #else
                                      #set($ch_copy = "")
                                      #end
                                      <input type="checkbox" name="copyAlamat" id="copyAlamat" $ch_copy onclick="tarikh_waris_saudara_copy('maklumat_pemohon','in_lap')"  />
                                      <span class="style74 style42 style50" ><em>Alamat surat menyurat adalah alamat tetap</em></span><span class="style74 style50" ></span><span class="style50" ></span></label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38" ><div align="right" class="style44 style72">
                                        <div align="left">Alamat Surat Menyurat</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1WarisSurat2" value="$txtAlamatTerakhir1WarisSurat" size="34"  $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2WarisSurat"  value="$txtAlamatTerakhir2WarisSurat" size="34" $readmode />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3WarisSurat" value="$txtAlamatTerakhir3WarisSurat" size="34" $readmode/></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Poskod</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$txtPoskodWarisSurat" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmode/>
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Negeri</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td> #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                      
                                      #set($kod = "$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri")
                                      
                                      
                                      
                                      
                                      #end 
                                      #end
                                      #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!=0)
                                      <select name="socNegeriWarisSurat" class="autoselect" id="socNegeriWarisSurat"  onchange="tarikh_waris_saudara_negerisurat('txtBandarWarisSurat','in_lap')" >
                                        <option value="$socNegeriWarisSurat" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                        
                                              
                                        
                  
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($socNegeriWarisSurat!=$listneg.id_Negeri)
                       
                  
                                        
                                              
                                        <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                                              
                                        
                  
                                     
                                  #end    
	                              #end
                                        
                
                                      
                                            
                                      </select>
                                      #else
                                      <select name="socNegeriWarisSurat" class="autoselect" onchange="tarikh_waris_saudara_negerisurat('txtBandarWarisSurat','in_lap')">
                                        <option value="0" >SILA PILIH NEGERI</option>
                                        
                                              
  
                  
                                  #foreach($listneg in $listnegeri)
                   
                  
  
                                              
                                        <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                                              
  
                  
                                    
	                               #end
                                        
                

                                            
                                      </select>
                                      #end </td>
                                  </tr>
                                  
                                  
                                  
                                  
                                  
                                  
                                   
        <tr id="tr_mesej_pelbagainegara_surat">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara_surat"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara_surat">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat Surat)</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara_surat' name = 'nama_pelbagainegara_surat' size='30' maxlength='200' $readmodeR class="$readmode" list = 'datalist'  value="$nama_pelbagainegara_surat"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    
                                    
                                  
                                    </td>
        </tr>
                
                                  
                                  
                                  
                                  
                                  
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1")  #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left">Bandar</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>#if($txtBandarWarisSurat == "" || $txtBandarWarisSurat=="0")
                                      #set($kodbx="")
                                      
                                      #else
                                      
                                      #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($txtBandarWarisSurat==$listneg.id)                                      
                                      #set($kodbx="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      #end
                                      
                                      
                                      
                                      
                                      
                                      #foreach($listdaerah in $listBandarSuratbyNegeri)                                
                                      #if($txtBandarWarisSurat==$listdaerah.id)                                
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                      #end
                                      
                                      
                                      #if($disabled=="disabled")
                                      #set($readmodedaerah="disabled")
                                      #end
                                      #if($txtBandarWarisSurat!="" && $txtBandarWarisSurat!="0" )
                                      <select name="txtBandarWarisSurat" id="txtBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                                        <option value="$txtBandarWarisSurat">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                        
                                              
                  
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                 
                                  #if($txtBandarWarisSurat!=$listdaerah.id)
              
                                  
                  
                  
                  
                                              
                                        <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                        
                                              
                  
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                
                                            
                                      </select>
                                      #else
                                      <select name="txtBandarWarisSurat" id="txtBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                                        <option value="">Sila Pilih Bandar</option>
                                        
                                              
  
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
  
                                              
                                        <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                        
                                              
  
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                


                                            
                                      </select>
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td class="style38" ><div align="right" class="style38 style72">
                                        <div align="left">No Telefon</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponWaris')"  style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoTeleponWaris" size="14" maxlength="12" $readmode /></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td>&nbsp;</td>
                                    <td valign="top"><span class="style73">cth: 031234567</span></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left" class="style72">No Telefon Bimbit</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoTeleponBimbitWaris" size="14" maxlength="12" $readmode onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponBimbitWaris')"/></td>
                                  </tr>
                                  <tr>
                                    <td valign="top">&nbsp;</td>
                                    <td class="style38" valign="top"><div align="left"><span class="style72"></span></div></td>
                                    <td valign="top">&nbsp;</td>
                                    <td valign="top"><span class="style73">cth: 0121234567</span></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left" class="style72">Emel</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtEmel" type="text" id="txtEmel" value="$txtEmel" size="30" maxlength="30" $readmode class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td valign="top"><div align="right"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td class="style38" valign="top"><div align="right" class="style38 style72">
                                        <div align="left">Catatan</div>
                                      </div></td>
                                    <td valign="top"><div align="right"><span class="style38">:</span></div></td>
                                    <td><textarea name="txtCatatanWaris" cols="31"  rows="3"  $readmode >$txtCatatanWaris</textarea></td>
                                  </tr>
                                  <tr id="tr_flag_daftar"  style="display:none">
                                    <td valign="top"></td>
                                    <td  valign="top">Urusan Kemasukkan Maklumat Waris </td>
                                    <td valign="top">:</td>
                                    <td valign="top"> #if($readmode != "disabled" )
                                      
                                      #if($FLAG_DAFTAR == '1')
                                      #set($checked_flag_daftar1 = "checked")
                                      #set($checked_flag_daftar2 = "")   
                                      #elseif($FLAG_DAFTAR == '2') 
                                      #set($checked_flag_daftar2 = "checked")    
                                      #set($checked_flag_daftar1 = "") 
                                      #else
                                      #set($checked_flag_daftar1 = "checked")
                                      #set($checked_flag_daftar2 = "")                                   
                                      #end
                                      <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar1 id="FLAG_DAFTAR" 
                                          value="1" />
                                      Pendaftaran <br />
                                      <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar2 id="FLAG_DAFTAR" 
                                          value="2" />
                                      Perbicaraan
                                      
                                      
                                      #else
                                      
                                      #set($text_daftar = "")
                                      #if($FLAG_DAFTAR == '1')
                                      #set($text_daftar = "PENDAFTARAN")
                                      #elseif($FLAG_DAFTAR == '2') 
                                      #set($text_daftar = "PERBICARAAN")                                         
                                      #end
                                      <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />
                                      <input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
                                      #end </td>
                                  </tr>
                                  #if($!skrin_online != "yes")
                                  <script>
                                                    document.getElementById('tr_flag_daftar').style.display = "";
                                                    </script>
                                  #end
                                </table></td>
                            </tr>
                          </table>
                          </fieldset></td>
                      </tr>
                      #if($readmode != "disabled")
                      <tr>
                        <td><table width="100%">
                            <tr>
                              <td><span class="style5 style47 style69"><span class="style41">Perhatian </span>: Sila masukkan salah satu nombor MyID dan pastikan label bertanda <span class="style41">*</span> diisi.</span></td>
                            </tr>
                          </table></td>
                      </tr>
                      #end
                      
                      #end
                      
                      
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
                      #set($txtBandarWarisSurat = $lwu.bandarSurat) #set($socNegeriWarisSurat = $lwu.idnegeriSurat)
                      
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
                      <tr>
                        <td><fieldset>
                          #if($!skrin_online != "yes") 
                          #if($readmode == "disabled")
                          #set($readmodeR = "readonly")
                          #else
                          #set($readmodeR = "")
                          #end
                          #else
                          #if($open_button_online == "no")
                          #set($readmodeR = "readonly")
                          #set($readmode = "disabled")
                          #else
                          #set($readmodeR = "")
                          #set($readmode = "")
                          #end
                          #end
                          <legend>MAKLUMAT WARIS LAPISAN BERIKUT </legend>
                          <table width="100%" border="0">
                            <tr>
                              <td width="50%" valign="top"><table width="100%">
                                  <input type="hidden" name="txtIdSimatiWaris" value="$idSimati" />
                                  <tr id="tr_waris" >
                                    <td class="style36" width="1%">&nbsp;</td>
                                    #set($idwarisup=$idwarisup)
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38">Waris Yang Meninggal</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    #foreach($listWarisParentlist in $listWarisParent)
                                    <td class="style36"><span class="style42" style="text-transform:uppercase;" onblur="uppercase()">$listWarisParentlist.nama_Ob</span>
                                      <input name="txtIdParent" type="hidden"  id="txtIdParent" size="15" value="$listWarisParentlist.idwaris" maxlength="2"  $readmode/>
                                      <input name="txtLapisParent" type="hidden"  id="txtLapisParent" size="15" value="$listWarisParentlist.lapis" maxlength="2"  $readmode/>
                                    </td>
                                    #set($ip=$listWarisParentlist.idwaris)
                                    #set($lp=$listWarisParentlist.lapis)
                                    #end </tr>
                                  <tr>
                                  <tr id="tr_pemohonwaris" >
                                    <td class="style36" width="1%">&nbsp;</td>
                                    <td width="28%"><div align="left" class="style72"><span class="style38"> Waris Yang Meninggal</span></div></td>
                                    <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36" width="70%"> #if($readmode == "disabled")                                   
                                      #foreach($ls in $list_getListOBUpdate)                                    
                                      #if($id_ob_list_getListOBUpdate==$ls.ID_OB)                                    
                                      #set($nama_ob_getListOBUpdate=$ls.NAMA_OB)                                    
                                      #end 
                                      #end
                                      
                                      #if($id_ob_list_getListOBUpdate!="" && $id_ob_list_getListOBUpdate!="0" ) <font color="blue"> $nama_ob_getListOBUpdate </font> #else
                                      Tiada
                                      #end                                   
                                      #else                                   
                                      #foreach($ls in $list_getListOBUpdate)                                    
                                      #if($id_ob_list_getListOBUpdate==$ls.ID_OB)                                    
                                      #set($nama_ob_getListOBUpdate=$ls.NAMA_OB)                                   
                                      #end 
                                      #end                                     
                                      
                                      #if($id_ob_list_getListOBUpdate!="" && $id_ob_list_getListOBUpdate!="0" )
                                      <select name="id_ob_list_getListOBUpdate" id="id_ob_list_getListOBUpdate" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" >
                                        <option value="$id_ob_list_getListOBUpdate" style="text-transform:uppercase;" onblur="uppercase()">$nama_ob_getListOBUpdate</option>
                                        
                                            #foreach($ls in $list_getListOBUpdate)                                 
                                              #if($id_ob_list_getListOBUpdate != $ls.ID_OB)
                                        
                                        <option value="$ls.ID_OB" style="text-transform:uppercase;" onblur="uppercase()">$ls.NAMA_OB</option>
                                                                           
                                              #end    
	                                        #end
                                            
                                        <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Waris</option>
                                      </select>
                                      <font  color="blue"  onMouseOver="this.style.cursor='help'" onClick="open_new_window();" ><img src="../img/info.png"  align="center" /></font> #else
                                      <select name="id_ob_list_getListOBUpdate" id="id_ob_list_getListOBUpdate" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" >
                                        <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Waris</option>
                                        
                                           #foreach($ls in $list_getListOBUpdate)
                                                
                                        <option value="$ls.ID_OB" style="text-transform:uppercase;" onblur="uppercase()">$ls.NAMA_OB</option>
                                        
                                           #end
                                      
                                      </select>
                                      <font  color="blue"  onMouseOver="this.style.cursor='help'" onClick="open_new_window();" ><img src="../img/info.png"  align="center" /></font> #end 
                                      #end </td>
                                  </tr>
                                  #if($id_Pemohon!="")
                                  <script>
	document.getElementById('tr_pemohonwaris').style.display="";
	document.getElementById('tr_waris').style.display="none";
	</script>
                                  #else
                                  <script>
	document.getElementById('tr_pemohonwaris').style.display="none";
	document.getElementById('tr_waris').style.display="";
	</script>
                                  #end
                                  <tr>
                                    <td width="1%" class="style36">&nbsp;</td>
                                    <td width="29%"><div align="right" class="style75">
                                        <div align="left"><span class="style38">MyID Baru</span></div>
                                      </div></td>
                                    <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%" class="style36"><input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Waris" style="width: 50px;" type="text" value="$txtNoKPBaru1Waris" $readmodeR class="$readmode" size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Waris');check_kp();check_pengenalan_simati_1()" 
onBlur="calculateTarikhLahirWaris();"/>

<!--onBlur="getAgeByIC(this,this.value,'txtUmurWaris');getStatusByIC(this,this.value,'socStatusOBWaris');getDOB(this.value)"/-->
                                      -
                                      <input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Waris" style="width: 20px;" type="text" value="$txtNoKPBaru2Waris" $readmodeR class="$readmode" size="3" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris');check_kp();check_pengenalan_simati_1()" />
                                      -
                                      <input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Waris"  style="width: 40px;" type="text" value="$txtNoKPBaru3Waris" $readmodeR class="$readmode" size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris');check_kp();check_pengenalan_simati_1()" onblur="jantinaic2();setSelected(0,2,0,0);tarikh_waris_saudara('txtNoKPLamaWaris','up_lap')" />
                                      <span id="check_kp_1" style="color:red" ></span> #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")                            
                                      #if($readmode != "disabled") <a href="http://www.jpn.gov.my" target="_blank" class="style51 style74 style42"> www.jpn.gov.my</a> #end     #end </td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td ><div align="right" class="style75">
                                        <div align="left"><span class="style38">MyID Lama</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label>
                                      <input name="txtNoKPLamaWaris" id="txtNoKPLamaWaris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text"  value="$txtNoKPLamaWaris" size="15" maxlength="8" $readmodeR class="$readmode" onkeyup="check_kp_lama();check_pengenalan_simati_2()" />
                                      </label>
                                      <span id="check_kp_2" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td ><div align="right" class="style75">
                                        <div align="left"><span class="style38">Jenis MyID Lain</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"> #if($readmode=="disabled")
                                      
                                      #if($socJenisKPLainWaris=="5")
                                      #set($pkp="Tentera")
                                      
                                      #elseif($socJenisKPLainWaris=="6")
                                      #set($pkp="Polis")
                                      
                                      #elseif($socJenisKPLainWaris=="4")
                                      #set($pkp="Pasport")
                                      
                                      #elseif($socJenisKPLainWaris=="7")
                                      #set($pkp="Lain-lain")
                                      
                                      #elseif($socJenisKPLainWaris=="0")
                                      #set($pkp="")
                                      
                                      #else
                                      #set($pkp="")
                                      #end
                                      <input name="textfield4" type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="12" $readmodeR class="$readmode" />
                                      #else
                                      <select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode onchange="kplain1(this.value)"  style="text-transform:uppercase;" onblur="kplain1X(this.value)" />
                                      
                                      #if($socJenisKPLainWaris=="5")
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="6")
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="4")
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #elseif($socJenisKPLainWaris=="7")
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      <option value="4" style="text-transform:uppercase;" >Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" >Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" >Polis</option>
                                      #else
                                      <option value="0" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Jenis KP</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" >Lain-lain</option>
                                      #end
                                      </select>
                                      #end
                                      <label></label></td>
                                  </tr>
                                  #if($readmode=="disabled")
                                  #set($readmodekp="disabled")
                                  #end
                                  
                                  #if($readmode=="")
                                  #if($socJenisKPLainWaris != 0 && $socJenisKPLainWaris != "")
                                  #set($readmodekp="")
                                  #else
                                  #set($readmodekp="disabled")
                                  #set($txtNoKPLainWaris = "")
                                  #end
                                  
                                  
                                  #end
                                  <tr>
                                    <td></td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38">MyID Lain</span></div>
                                      </div></td>
                                    <td>:</td>
                                    <td><span class="style36"> #if($readmode == "disabled")
                                      <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" onkeyup="check_kp_lain();check_pengenalan_simati_3()" value="$txtNoKPLainWaris" size="15" maxlength="25" $readmodeR class="$readmode" />
                                      #else
                                      <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" onkeyup="check_kp_lain();check_pengenalan_simati_3()" value="$txtNoKPLainWaris" size="15" maxlength="25" $readmodekp />
                                      #end </span> <span id="check_kp_3" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Nama Waris #else Nama Waris #end</span></div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBWaris" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtNamaOBWaris" value="$txtNamaOBWaris" size="34" $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44"></span></td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Nama Lain #else Nama Lain #end</span></div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaLainWaris" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtNamaLainWaris" value="$txtNamaLainWaris" size="34" $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Jantina #else Jantina #end</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label> #if($readmode=="disabled")
                                      
                                      #if($socJantinaWaris=="1")
                                      #set($sexpemohon="Lelaki")
                                      
                                      
                                      #elseif($socJantinaWaris=="2")
                                      #set($sexpemohon="Perempuan")
                                      
                                      #else
                                      #set($sexpemohon="")
                                      #end
                                      <input name="textfield" type="text" id="textfield2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" $readmodeR class="$readmode" value="$sexpemohon" />
                                      #else
                                      <select name="socJantinaWaris" id="socJantinaWaris" onchange="setSelected(0,2,0,0);tarikh_waris_saudara('socAgamaWaris','up_lap')" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;">
                                        
                                              
                                             
                                              
                                      
                                   #if($socJantinaWaris=="1")
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socJantinaWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Jantina</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Lelaki</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Perempuan</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38">Agama</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label> #if($readmode=="disabled")
                                      
                                      #if($socAgamaWaris=="1")
                                      
                                      #set($agp="Islam")
                                      
                                      #elseif($socAgamaWaris=="2")
                                      
                                      #set($agp="Bukan Islam")
                                      
                                      #else
                                      
                                      #set($agp="")
                                      
                                      #end
                                      <input name="socAgamaPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="15"  $readmodeR class="$readmode"  value="$agp" />
                                      #else
                                      <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;">
                                        
                                              
                                             
                                              
                                      
                                   #if($socAgamaWaris=="1")
	                               
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option >
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socAgamaWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option>
                                        
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Islam</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Islam</option>
                                        
                                              
                                             
                                              
                                      

	                               #end
                                      
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style36" valign="top">&nbsp;</td>
                                    <td valign="top"><div align="right" class="style75">
                                        <div align="left"><span class="style38">Warganegara</span></div>
                                      </div></td>
                                    <td class="style36" valign="top"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36" valign="top"><label>
                                      #if($readmode=="disabled")
                                      
                                      #if($socWarganegaraWaris=="1")
                                      
                                      #set($wrp="Warganegara")
                                      
                                      #elseif($socWarganegaraWaris=="2")
                                      
                                      #set($wrp="Bukan Warganegara")
                                      
                                      #elseif($socWarganegaraWaris=="3")
                                      
                                      #set($wrp="Pemastautin Tetap")
                                      
                                      #else
                                      #set($wrp="")
                                      
                                      #end
                                      <input name="socWarganegaraPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="25" $readmodeR class="$readmode" value="$wrp" />
                                      #else
                                      <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="autoselect" $readmode style="text-transform:uppercase;" onchange="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" onblur="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" >
                                        
                                              
                                             
                                              
                                      
                                   #if($socWarganegaraWaris=="1")
	                               
                                      
                                              
                                             
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socWarganegaraWaris=="2")
	                               
                                      
                                              
                                             
                                              
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #elseif($socWarganegaraWaris=="3")
	                               
                                      
                                              
                                             
                                              
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        
                                              
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                             
                                              
                                              
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Warganegara</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Bukan Warganegara</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Pemastautin Tetap</option>
                                        
                                              
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
                                            
                                      </select>
                                      <div id="alamatwarga"></div>
                                      #end
                                      </label>
                                    </td>
                                  </tr>
                                  <tr id="tr_nama_warga">
                                    <td valign="top" ></td>
                                    <td><div align="left" class="style38"> Negara </div></td>
                                    <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input type = 'text' id = 'nama_warga' name = 'nama_warga' size='30' maxlength='200' $readmodeR class="$readmode"  list = 'datalist'  value="$nama_warga"    />
                                      <datalist id = 'datalist'>#foreach($ja in $kenegaraan)
                                        <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                        #end </datalist>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38">Tarikh Lahir</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txdTarikhLahirWaris" type="text" id="txdTarikhLahirWaris" value="$txdTarikhLahirWaris" size="10" maxlength="10" $readmodeR class="$readmode"  onblur="trans_date(this.value);getAgebyDob(this,this.value,'txtUmurWaris');defineStatusWarisByUmur();"/>
                                      #if($readmode != "disabled") <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a><span class="style73"> dd/mm/yyyy</span> #end </td>
                                  </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38">No Surat Beranak</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtNoSuratBeranakWaris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  onkeyup="check_pengenalan_simati_4()" type="text" id="txtNoSuratBeranakWaris" value="$txtNoSuratBeranakWaris" size="15" maxlength="10" $readmodeR class="$readmode" />
                                      <span id="check_kp_4" style="color:red" ></span> </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Umur #else Umur  #end</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtUmurWaris" style="text-transform:uppercase;" onblur="Checkumur();getStatusByAge(this,this.value,'socStatusOBWaris')" type="text" id="txtUmurWaris" value="$txtUmurWaris" size="3" maxlength="3" onkeyup="javascript:validateIC(event,this,this.value,'txtUmurWaris')" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38">Status Waris</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"> #if($readmode == "disabled")
                                      
                                      #if($socStatusOBWaris=="1")
                                      #set($stat = "Dewasa/Waras")
                                      #elseif($socStatusOBWaris=="2")
                                      #set($stat = "Belum Dewasa")
                                      #elseif($socStatusOBWaris=="3")
                                      #set($stat = "Hilang/Meninggal Dunia/Tidak Dapat Dikesan")
                                      #elseif($socStatusOBWaris=="4")
                                      #set($stat = "Tidak Sempurna Akal")
                                      #else
                                      #set($stat = "")
                                      #end
                                      <input type="text" name="stat" id="stat" value="$stat" size="25"  style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <select name="socStatusOBWaris"  style="text-transform:uppercase;" onblur="text-transform:uppercase;" class="autoselect" $readmode id="socStatusOBWaris" onchange="status_ob()">
                                        
                                            
                                           
									
								   #if($socStatusOBWaris=="1")
	                                 
                                      
                                           
                                           
                                            
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                          
	                               #elseif($socStatusOBWaris=="2")
	                                
	                                  
                                           
                                            
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                        
	                              
								   #elseif($socStatusOBWaris=="3")
	                               
	                                 
                                           
                                            
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                      
                                     
                                   #elseif($socStatusOBWaris=="4")
	                                    
                                           
                                            
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                                                          
                                    
                                        
	                               #else
	                                 
                                      
                                           
                                            
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Status</option>
                                        <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">01 - Dewasa/Waras</option>
                                        <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">02 - Belum Dewasa</option>
                                        <option value="3" style="text-transform:uppercase;" onblur="text-transform:uppercase;">03 - Hilang/Meninggal Dunia/Tidak Dapat Dikesan</option>
                                        <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;">04 - Tidak Sempurna Akal</option>
                                        
                                            
                                           
                                      
	                               #end
                                   
                                         
                                          
                                      </select>
                                      #end </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Talian Persaudaraan</span> #else Talian Persaudaraan #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td> #if($readmode == "disabled")
                                      
                                      #foreach($listsau in $listsaudara)                                          
                                      #if($socSaudaraWaris==$listsau.id_Saudara)                                          
                                      #set($kodsaudaraketerangan=$listsau.keterangan)
                                      
                                      #end    
                                      #end
                                      
                                      #if($socSaudaraWaris!="" && $socSaudaraWaris!="0")
                                      <input  name="sau" id"sau" value="$kodsaudaraketerangan"   size="50" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input  name="sau" id"sau" value="" style="text-transform:uppercase;" size="15" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      
                                      #foreach($listsau in $listsaudara)
                                      
                                      #if($socSaudaraWaris==$listsau.id_Saudara)
                                      
                                      #set($kodsaudara=$listsau.kod)
                                      #set($kodsaudaraketerangan=$listsau.keterangan)
                                      
                                      
                                      
                                      #end    
                                      #end
                                      #if($socSaudaraWaris!="")
                                      <select name="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;" >
                                        <option value="$socSaudaraWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$kodsaudara - $kodsaudaraketerangan</option>
                                        
                                              
                                             
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($socSaudaraWaris!=$listsau.id_Saudara)
                                     #if($socJantinaWaris=="1")
                                          #set($jan="01")
                                      
                                          #elseif($socJantinaWaris=="2")
                                          #set($jan="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jan )
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                              
                                   #end
                                   
                                   
                                     #if($listsau.id_Saudara=="27")
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                              
                                   #end
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            

                                           
                                            
                                      </select>
                                      #else
                                      <select name="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onblur="text-transform:uppercase;" onfocus="insertsaudara()" >
                                        <option value="" style="text-transform:uppercase;" onblur="text-transform:uppercase;">Sila Pilih Talian Persaudaraan</option>
                                        
                                              
                                  #foreach($listsau in $listsaudara)
                                         #if($socJantinaWaris=="1")
                                          #set($jantt="01")
                                          
                                          #elseif($socJantinaWaris=="2")
                                          #set($jantt="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jantt || $listsau.jantina=="03")
                                
	                               
                                              
                                        <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                        
                                 #end             
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
                                            
                                      </select>
                                      #end </td>
                                    #end </tr>
                                  <tr>
                                    <td class="style36">&nbsp;</td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38">Sudah Meninggal Dunia</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><label> #if($checkHidupWaris=="1")
                                      #set($chq="checked")
                                      #else
                                      #set($chq="")
                                      #end
                                      <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq $readmode value="$checkHidupWaris"  onclick="setSelected(0,2,0,0);tarikh_waris('checkHidupWaris','up_lap')" />
                                      </label></td>
                                  </tr>
                                  #if($checkHidupWaris=="1")
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled") * #end</span></td>
                                    <td><div align="right" class="style38 style72">
                                        <div align="left"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Tarikh Mati #else Tarikh Mati #end</span></div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txdTarikhMatiWaris" type="text" id="txdTarikhMatiWaris" value="$txdTarikhMatiWaris" size="10" maxlength="10" $readmodeR class="$readmode"  onblur="trans_date1(this.value)" />
                                      #if($readmode != "disabled") <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a><span class="style73"> dd/mm/yyyy</span> #end </td>
                                  </tr>
                                  <tr>
                                    <td  valign="top"><span class="style44">#if($readmode != "disabled")  #end</span></td>
                                    <td><div align="right" class="style75">
                                        <div align="left"><span class="style38"><span class="style38 ">#if($readmode != "disabled")</span> Waktu Kematian</span> #else Waktu Mati #end</div>
                                      </div></td>
                                    <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                    <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" style="text-transform:uppercase;" onblur="jeniswaktu1();" value="$txtWaktuKematianWaris" size="4" maxlength="4" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtWaktuKematianWaris')"/>
                                      <label> #if($readmode=="disabled")
                                      #set($waktu = "")
                                      #if( $jeniswaktu == 1)
                                      #set($waktu = "PAGI")  
                                      #elseif($jeniswaktu == 2)
                                      #set($waktu = "TENGAHARI")                                   
                                      #elseif($jeniswaktu == 3)
                                      #set($waktu = "PETANG")      
                                      #elseif($jeniswaktu == 4)
                                      #set($waktu = "MALAM")                                   
                                      #else
                                      #set($waktu = "")
                                      #end
                                      <input name="jeniswaktu_d" type="text" id="jeniswaktu_d"    value="$waktu" size="4" maxlength="3" style="width:100"  $readmodeR class="$readmode" />
                                      <input name="jeniswaktu" type="hidden" id="jeniswaktu"    value="$jeniswaktu" />
                                      #else
                                      <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2()" >
                                        
                                  
                                  #if( $jeniswaktu == 1)
                                  
                                  
                                        <option value="1" id="op_pagi" >PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                                                            
                                    #elseif($jeniswaktu == 2)
                                      
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                        
                                    #elseif($jeniswaktu == 3)
                                     
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="">SILA PILIH</option>
                                        
                                       #elseif($jeniswaktu == 4)
                                        
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                        <option value="2" id="op_tengahari">TENGAHARI</option>
                                        <option value="3" id="op_petang">PETANG</option>
                                        <option value="">SILA PILIH</option>
                                        
                                    #else
                                  
                                        <option value="">SILA PILIH</option>
                                        <option value="1" id="op_pagi" >PAGI</option>
                                        <option value="2" id="op_tengahari" >TENGAHARI</option>
                                        <option value="3"  id="op_petang" >PETANG</option>
                                        <option value="4" id="op_malam" >MALAM</option>
                                                    
                                     
                                    #end                                
                                    
                                 
                                  
                                      </select>
                                      #end </label>
                                      #if($readmode != "disabled") <span class="style73">format 12 jam (HHMM)</span> #end </td>
                                  </tr>
                                  #end
                                </table></td>
                              <td width="40%" valign="top"><table width="100%" border="0">
                                  #if($readmode != "disabled")
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style72"></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label> #if($check_copyP == "on")
                                      #set($ch_copyP = "checked")
                                      #else
                                      #set($ch_copyP = "")
                                      #end
                                      <input type="checkbox" name="copyAlamatP" id="copyAlamatP" $ch_copyP onclick="tarikh_waris_saudara_copyP('maklumat_pemohon','up_lap')"  />
                                      <span class="style74 style42 style50" ><em>Alamat waris adalah alamat pemohon</em></span></label></td>
                                  </tr>
                                  #end
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38" ><div align="right" class="style44 style72">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Alamat Tetap #else Alamat Tetap #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir1Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1Waris" value="$txtAlamatTerakhir1Waris" size="34" maxlength="40"  $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"><span class="style72"></span></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2Waris"  value="$txtAlamatTerakhir2Waris" size="34" maxlength="40" $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"><span class="style72"></span></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3Waris" value="$txtAlamatTerakhir3Waris" size="34" maxlength="40" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Poskod #else Poskod #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWaris" type="text" style="text-transform:uppercase;" onblur="text-transform:uppercase;" id="txtPoskodWaris" value="$txtPoskodWaris" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWaris')" $readmodeR class="$readmode" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Negeri #else Negeri #end</div>
                                      </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td> #if($readmode == "disabled")
                                      
                                      #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWaris==$listnegpomo.id_Negeri)
                                      
                                      #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                      #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                      
                                      
                                      
                                      #end 
                                      #end
                                      
                                      #if($socNegeriWaris!="" && $socNegeriWaris!="0" )
                                      <input name="nt" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="nt" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      
                                      #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWaris==$listnegpomo.id_Negeri)
                                      
                                      #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                      #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                      
                                      
                                      
                                      #end 
                                      #end
                                      #if($socNegeriWaris!="" && $socNegeriWaris!="0" )
                                      <select name="socNegeriWaris" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="tarikh_waris_saudara_negeritetap('txtBandarWaris','up_lap')">
                                        <option value="$socNegeriWaris" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($socNegeriWaris!=$listnegpomo.id_Negeri)
                                    
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
                                      #else
                                      <select name="socNegeriWaris" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="tarikh_waris_saudara_negeritetap('txtBandarWaris','up_lap')">
                                        <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
                                      #end </td>
                                    #end </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                          <tr id="tr_mesej_pelbagainegara">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat)3</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' $readmodeR class="$readmode" list = 'datalist'  value="$nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    
                                    
                                  
                                    </td>
        </tr>
              
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Bandar #else Bandar #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label> #if($readmode == "disabled")
                                      
                                      
                                      #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($txtBandarWaris==$listneg.id)                                      
                                      #set($kodbbb="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      
                                      
                                      #if($txtBandarWaris!="" && $txtBandarWaris!="0" )
                                      <input name="ntbb" value="$kodbbb" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="ntbb" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      
                                      #if($txtBandarWaris == "" || $txtBandarWaris=="0")
                                      #set($kodb="")
                                      
                                      #else
                                      
                                      #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($txtBandarWaris==$listneg.id)                                      
                                      #set($kodb="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      #end
                                      
                                      
                                      
                                      
                                      
                                      #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                                      #if($txtBandarWaris==$listdaerah.id)                                
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                      #end
                                      
                                      
                                      #if($disabled=="disabled")
                                      #set($readmodedaerah="disabled")
                                      #end
                                      #if($txtBandarWaris!="" && $txtBandarWaris!="0" )
                                      <select name="txtBandarWaris" id="txtBandarWaris" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                        <option value="$txtBandarWaris">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                        
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($txtBandarWaris!=$listdaerah.id)
              
                                  
                  
                  
                                        <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                        
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                                      </select>
                                      #else
                                      <select name="txtBandarWaris" id="txtBandarWaris" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                        <option value="">Sila Pilih Bandar</option>
                                        
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
                                        <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                        
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                

                                      </select>
                                      #end 
                                      #end </label></td>
                                  </tr>
                                  #if($readmode != "disabled")
                                  <tr>
                                    <td width="1%">&nbsp;</td>
                                    <td width="29%" class="style38 style72" ><div align="left"></div></td>
                                    <td width="1%">&nbsp;</td>
                                    <td width="70%"><label> #if($check_copy == "on")
                                      #set($ch_copy = "checked")
                                      #else
                                      #set($ch_copy = "")
                                      #end
                                      <input type="checkbox" name="copyAlamat" id="copyAlamat" $ch_copy onclick="tarikh_waris_saudara_copy('maklumat_pemohon','up_lap')"  />
                                      <span class="style74 style42 style50" ><em>Alamat surat menyurat adalah alamat tetap</em></span><span class="style74 style50" ></span><span class="style50" ></span></label></td>
                                  </tr>
                                  #end
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38" ><div align="right" class="style44 style72">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Alamat Surat Menyurat #else Alamat Surat Menyurat #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1WarisSurat2" value="$txtAlamatTerakhir1WarisSurat" size="34"  $readmodeR class="$readmode"  />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"><span class="style72"></span></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2WarisSurat"  value="$txtAlamatTerakhir2WarisSurat" size="34" $readmodeR class="$readmode"  />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style72"><span class="style72"></span></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3WarisSurat" value="$txtAlamatTerakhir3WarisSurat" size="34" $readmodeR class="$readmode"  /></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Poskod #else Poskod #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$txtPoskodWarisSurat" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmodeR class="$readmode"  />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1") * #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Negeri #else Negeri #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td> #if($readmode == "disabled")
                                      
                                      #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                      
                                      #set($negerikodpemoPs=$listnegpomo.kod_Negeri)
                                      #set($negeriketeranganpemoPs=$listnegpomo.nama_Negeri)
                                      
                                      
                                      
                                      #end 
                                      #end
                                      
                                      #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!="0" )
                                      <input name="ns" value="$negerikodpemoPs - $negeriketeranganpemoPs" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="ns" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      #foreach($listnegpomo in $listnegeri)
                                      
                                      #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                      
                                      #set($kod = "$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri")
                                      
                                      
                                      
                                      
                                      #end 
                                      #end
                                      #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!=0)
                                      <select name="socNegeriWarisSurat" class="autoselect" id="socNegeriWarisSurat" $readmode   onchange="tarikh_waris_saudara_negerisurat('txtBandarWarisSurat','up_lap')" >
                                        <option value="$socNegeriWarisSurat" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                        
                                              
                                        
                  
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($socNegeriWarisSurat!=$listneg.id_Negeri)
                       
                  
                                        
                                              
                                        <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                                              
                                        
                  
                                     
                                  #end    
	                              #end
                                        
                
                                      
                                            
                                      </select>
                                      #else
                                      <select name="socNegeriWarisSurat" class="autoselect" $readmode  onchange="tarikh_waris_saudara_negerisurat('txtBandarWarisSurat','up_lap')">
                                        <option value="0" >SILA PILIH NEGERI</option>
                                        
                                              
  
                  
                                  #foreach($listneg in $listnegeri)
                   
                  
  
                                              
                                        <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                                              
  
                  
                                    
	                               #end
                                        
                

                                            
                                      </select>
                                      #end 
                                      
                                      #end</td>
                                  </tr>
                                  
                                  
                                  
                                  
                                  <tr id="tr_mesej_pelbagainegara_surat">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara_surat"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara_surat">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat Surat)</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara_surat' name = 'nama_pelbagainegara_surat' size='30' maxlength='200' $readmodeR class="$readmode" list = 'datalist'  value="$nama_pelbagainegara_surat"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    
                                   
                                    </td>
        </tr>
                
                                  
                                  
                                  
                                  
                                  
                                  <tr>
                                    <td  valign="top" width="1%"><span class="style44">#if($readmode != "disabled" && $checkHidupWaris!="1")  #end</span></td>
                                    <td class="style38"><div align="right" class="style44 style72">
                                        <div align="left"><span class="style38 ">#if($readmode != "disabled")</span> Bandar #else Bandar #end</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label> #if($readmode == "disabled")
                                      
                                      
                                      #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($txtBandarWarisSurat==$listneg.id)                                      
                                      #set($kodss="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      
                                      
                                      #if($txtBandarWarisSurat!="" && $txtBandarWarisSurat!="0" )
                                      <input name="ntbb" value="$kodss" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="ntbb" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      
                                      #else
                                      
                                      
                                      #if($txtBandarWarisSurat == "" || $txtBandarWarisSurat=="0")
                                      #set($kodbx="")
                                      
                                      #else
                                      
                                      #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($txtBandarWarisSurat==$listneg.id)                                      
                                      #set($kodbx="$listneg.kod - $listneg.nama")
                                      
                                      #end
                                      #end
                                      
                                      #end
                                      
                                      
                                      
                                      
                                      
                                      #foreach($listdaerah in $listBandarSuratbyNegeri)                                
                                      #if($txtBandarWarisSurat==$listdaerah.id)                                
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                      #end
                                      
                                      
                                      #if($disabled=="disabled")
                                      #set($readmodedaerah="disabled")
                                      #end
                                      #if($txtBandarWarisSurat!="" && $txtBandarWarisSurat!="0" )
                                      <select name="txtBandarWarisSurat" id="txtBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                                        <option value="$txtBandarWarisSurat">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                        
                                              
                  
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                 
                                  #if($txtBandarWarisSurat!=$listdaerah.id)
              
                                  
                  
                  
                  
                                              
                                        <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                        
                                              
                  
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                
                                            
                                      </select>
                                      #else
                                      <select name="txtBandarWarisSurat" id="txtBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                                        <option value="">Sila Pilih Bandar</option>
                                        
                                              
  
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
  
                                              
                                        <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                        
                                              
  
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                


                                            
                                      </select>
                                      #end 
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td class="style38" ><div align="right" class="style38 style72">
                                        <div align="left">No Telefon</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponWaris')"  style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoTeleponWaris" size="14" maxlength="12" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  #if($readmode != "disabled" )
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38 style72" ><div align="left"></div></td>
                                    <td>&nbsp;</td>
                                    <td valign="top"><span class="style73">cth: 031234567</span></td>
                                  </tr>
                                  #end
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="right" class="style72">
                                        <div align="left">No Telefon Bimbit</div>
                                      </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoTeleponBimbitWaris" size="14" maxlength="12" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponBimbitWaris')"/></td>
                                  </tr>
                                  #if($readmode != "disabled" )
                                  <tr>
                                    <td valign="top">&nbsp;</td>
                                    <td valign="top" class="style38 style72"><div align="left"></div></td>
                                    <td valign="top">&nbsp;</td>
                                    <td valign="top"><span class="style73">cth: 0121234567</span></td>
                                  </tr>
                                  #end
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td class="style38" ><div align="left" class="style72">Emel</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><input name="txtEmel" type="text" id="txtEmel" value="$txtEmel" size="30" maxlength="30" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td valign="top"><div align="right"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td class="style38" valign="top"><div align="right" class="style38 style72">
                                        <div align="left">Catatan</div>
                                      </div></td>
                                    <td valign="top"><div align="right"><span class="style38">:</span></div></td>
                                    <td><textarea name="txtCatatanWaris" cols="31"  rows="3"  $readmodeR class="$readmode" >$txtCatatanWaris</textarea></td>
                                  </tr>
                                  <tr id="tr_flag_daftar"  style="display:none">
                                    <td valign="top"></td>
                                    <td  valign="top">Urusan Kemasukkan Maklumat Waris </td>
                                    <td valign="top">:</td>
                                    <td valign="top"> #if($readmode != "disabled" )
                                      
                                      #if($FLAG_DAFTAR == '1')
                                      #set($checked_flag_daftar1 = "checked")
                                      #set($checked_flag_daftar2 = "")   
                                      #elseif($FLAG_DAFTAR == '2') 
                                      #set($checked_flag_daftar2 = "checked")    
                                      #set($checked_flag_daftar1 = "") 
                                      #else
                                      #set($checked_flag_daftar1 = "checked")
                                      #set($checked_flag_daftar2 = "")                                   
                                      #end
                                      <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar1 id="FLAG_DAFTAR" 
                                          value="1" />
                                      Pendaftaran <br />
                                      <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar2 id="FLAG_DAFTAR" 
                                          value="2" />
                                      Perbicaraan
                                      
                                      
                                      #else
                                      
                                      #set($text_daftar = "")
                                      #if($FLAG_DAFTAR == '1')
                                      #set($text_daftar = "PENDAFTARAN")
                                      #elseif($FLAG_DAFTAR == '2') 
                                      #set($text_daftar = "PERBICARAAN")                                         
                                      #end
                                      <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />
                                      <input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
                                      #end </td>
                                  </tr>
                                  #if($!skrin_online != "yes")
                                  <script>
                                                    document.getElementById('tr_flag_daftar').style.display = "";
                                                    </script>
                                  #end
                                </table></td>
                            </tr>
                          </table>
                          </fieldset></td>
                      </tr>
                      #if($readmode != "disabled")
                      <tr>
                        <td><table width="100%">
                            <tr>
                              <td><span class="style5 style47 style69"><span class="style41">Perhatian </span>: Sila masukkan salah satu nombor MyID dan pastikan label bertanda <span class="style41">*</span> diisi.</span></td>
                            </tr>
                          </table></td>
                      </tr>
                      #end
                      
                      #end
                      
                      
                      
                      
                      #if($show_button_lapisan=="yes")
                      
                      #foreach($lwu in $listWarisLapisanUpdate)
                      #end
                      <tr>
                        <td><table width="100%" border="0" align="center">
                            <tr>
                              <td align="center"> #if($open_button_online == "yes") 
                                
                                
                                
                                #if($buttonwarislapisanSimpan!="")
                                #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                                <!--   <input type="button" name="tambahwarislapisanSimpan" id="tambahwarisSimpan" value="$buttonwarislapisanSimpan"  onclick="setSelected(0,2,0,0);tambah_waris_lapisan_Simpan()"/> -->
                                #end
                                
                                #if($boleh_kemaskini == "yes")
                                #if($!skrin_online != "yes")
                                <input type="button" name="tambahwarislapisanSimpan" id="tambahwarislapisanSimpan" value="$buttonwarislapisanSimpan"  onclick="setSelected(0,2,0,0);tambah_waris_lapisan_Simpan()"/>
                                #if($flag_kemaskini_selesai != "yes")
                                <script>
                                                    document.getElementById('tambahwarislapisanSimpan').style.display = "none";
                                                    </script>
                                #end  
                                #else
                                <input type="button" name="tambahwarislapisanSimpan" id="tambahwarisSimpan" value="Simpan"  onclick="setSelected(0,2,0,0);tambah_waris_lapisan_Simpan()"/>
                                #end
                                #end
                                
                                #end  
                                
                                #if($buttonwarislapisanSimpan=="") 
                                
                                #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                                <!--
            <input type="button" name="tambahwarislapisan" id="tambahwaris4" value="$buttonwarislapisan"  onclick="setSelected(0,2,0,0);tambah_waris_lapisan()"/>
              #if($buttonwarislapisan=="Simpan" || $buttonwarislapisan=="Kemaskini" )              
              #if($listWarisLapisanIdMatiDelete.size()==0 && $buttonwarislapisan=="Kemaskini") 
                <input name="txtIdParent" type="hidden" value="$idp">
               <input type="button" name="tambahwarishapuslapisan" id="tambahwarishapuslapisan" value="Hapus" onclick="setSelected(0,2,0,0);hapus_waris_lapisan()"/>              #end
              #end
              -->
                                #end
                                
                                #if($boleh_kemaskini == "yes")
                                #end
                                #if($!skrin_online != "yes")
                                <input type="button" name="tambahwarislapisan" id="tambahwarislapisan" value="$buttonwarislapisan"  onclick="setSelected(0,2,0,0);tambah_waris_lapisan()"/>
                                #if($flag_kemaskini_selesai != "yes")
                                <script>
                                                    document.getElementById('tambahwarislapisan').style.display = "none";
                                                    </script>
                                #end  
                                #else
                                <input type="button" name="tambahwarislapisan" id="tambahwaris4" value="Simpan"  onclick="setSelected(0,2,0,0);tambah_waris_lapisan()"/>
                                #end
                                
                                #if($buttonwarislapisan=="Simpan" || $buttonwarislapisan=="Kemaskini" )              
                                #if($listWarisLapisanIdMatiDelete.size()==0 && $buttonwarislapisan=="Kemaskini")
                                <input name="txtIdParent" type="hidden" value="$idp">
                                <input type="button" name="tambahwarishapuslapisan" id="tambahwarishapuslapisan" value="Hapus" onclick="setSelected(0,2,0,0);hapus_waris_lapisan()"/>
                                #if($flag_kemaskini_selesai != "yes")
                                <script>
                                                    document.getElementById('tambahwarishapuslapisan').style.display = "none";
                                                    </script>
                                #end 
                                
                                #end
                                #end
                                
                                
                                
                                #end
                                
                                
                                #if($buttonwarislapisan == "Kemaskini")
                                <input type="button" name="lapisanberikut3" id="lapisanberikut3" value="Lapisan Sebelum"  onclick="setSelected(0,2,0,0);WarisView()"/>
                                #end
                                
                                
                                #if($checkHidupWaris==1 && $buttonwarislapisan=="Kemaskini")
                                <input type="button" name="lapisanberikut" id="lapisanberikut" value="Lapisan Berikut"  onclick="setSelected(0,2,0,0);lapisan_waris_lapisan()"  />
                                #end
                                #if($buttonwarislapisanSimpan=="Simpan")
                                #if($!skrin_online != "yes")
                                <input type="button" name="cmdSimpan8" id="cmdSimpan10" value="Batal" onclick="setSelected(0,2,0,0);cancelwaris()"/>
                                #end
                                #end
                                #if($buttonwarislapisan=="Simpan")
                                
                                #if($!skrin_online != "yes")
                                <input type="button" name="cmdSimpan8" id="cmdSimpan10" value="Batal" onclick="setSelected(0,2,0,0);batal_waris_lapisan_Simpan()"/>
                                #end
                                
                                #end
                                <input type="submit" name="cmdKembali9" id="cmdKembali11" value="Kembali" onclick="setSelected(0,2,0,0);WarisView()"/>
                                #end </td>
                            </tr>
                          </table></td>
                      </tr>
                      #end
                      <tr>
                        <td> #foreach($lis_wariss in $listWarisLapisan)
                          
                          
                          #foreach($lw in $listWarisParent)              
                          #if($lw.idwaris==$lis_wariss.idparent)
                          #set($nama_pewaris = $lw.nama_Ob)
                          
                          #else
                          #set($nama_pewaris = "" )
                          #end             
                          #end
                          
                          #end
                          <fieldset>
                          <legend>SENARAI WARIS LAPISAN BERIKUT</legend>
                          <table width="100%">
                            #set($idwww=$idparent)
                            <input type="hidden" name="idwarislapis" value="$idwww">
                            <input type="hidden" name="idparentlapis" value="$ip">
                            <input type="hidden" name="idwarisup" value="$idwarisup" />
                            <tr>
                              <td> #if($nk_tambah_lapisan=="yes")
                                #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                                <!-- <input type="button" name="naktambahlapisan" id="naktambahlapisan" value="Tambah"  onclick="setSelected(0,2,0,0);tambah_lapisan_berikut()"/> -->
                                #end
                                #if($open_button_online == "yes") 
                                #if($boleh_kemaskini == "yes")
                                #end
                                <input type="button" name="naktambahlapisan" id="naktambahlapisan" value="Tambah"  onclick="setSelected(0,2,0,0);tambah_lapisan_berikut()"/>
                                #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('naktambahlapisan').style.display = "none";
                                </script>
                                #end   
                                
                                #end
                                <!--
      <input type="button" name="button" id="button" value="Jana Perakuan Faraid" />
      
      -->
                                <!--
      <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="setSelected(0,2,0,0);WarisView()"/>
      -->
                                #end
                                #if($show_button_lapisan!="yes" )
                                <!--     
        <label>
        <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="setSelected(0,2,0,0);WarisView()"/>
      </label>
      -->
                                #end </td>
                            </tr>
                          </table>
                          <div align="center"> #if($listWarisLapisan.size()==0)
                            <table width="100%">
                              <tr class="table_header">
                                <td width="5%"><div align="center">NO</div></td>
                                <td width="20%"><div align="left">NAMA WARIS</div></td>
                                <td width="15%"><div align="center">MyID BARU</div></td>
                                <td width="5%"><div align="center">UMUR</div></td>
                                <td width="15%"><div align="left">TALIAN PERSAUDARAAN</div></td>
                                <td width="20%"><div align="left">NAMA WARIS YANG MENINGGAL</div></td>
                                <td width="10%"><div align="center">STATUS</div></td>
                                <td width="10%"><div align="center">LAPISAN</div></td>
                              </tr>
                              <!--   <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" /> -->
                            </table>
                            <table width="100%">
                              <tr bgcolor="white">
                                <td align="left"> Tiada Rekod </td>
                              </tr>
                            </table>
                            #else
                            <table width="100%">
                              <tr class="table_header">
                                <td width="5%"><div align="center" >NO</div></td>
                                <td width="20%"><div align="left">NAMA WARIS</div></td>
                                <td width="15%"><div align="center" >MyID BARU</div></td>
                                <td width="5%"><div align="center" >UMUR</div></td>
                                <td width="15%"><div align="left" >TALIAN PERSAUDARAAN</div></td>
                                <td width="20%"><div align="left" >NAMA WARIS YANG MENINGGAL</div></td>
                                <td width="10%"><div align="center" >STATUS</div></td>
                                <td width="10%"><div align="center" >LAPISAN</div></td>
                              </tr>
                              #set($bu=0)
                              
                              
                              #foreach($listwarislapisan in $listWarisLapisan)
                              
                              #set($bu=$bu+1)
                              
                              #if($bu%2!=0)
                              <tr>
                                <td width="5%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$bu</div></td>
                                <td width="20%" class="row1"><div align="left"  style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                                <td width="15%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                                <td width="5%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.umur</div></td>
                                #foreach($listsaudaralist in $listsaudara)
                                
                                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                                
                                #set($wariskodsaudara=$listsaudaralist.kod)
                                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                                
                                
                                #end    
                                #end
                                <td width="15%" class="row1"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                                <td width="20%" class="row1"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nama_parent</div></td>
                                #if($listwarislapisan.statushidup=="0")
                                #set($hidup="Masih Hidup")
                                #end
                                #if($listwarislapisan.statushidup=="1")
                                #set($hidup="Sudah Meninggal")
                                #end
                                #if($listwarislapisan.statushidup=="")
                                #set($hidup="")
                                #end
                                <td width="10%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                                <td width="10%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
                              </tr>
                              #else
                              <tr class="table_header">
                                <td width="5%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$bu</div></td>
                                <td width="20%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                                <td width="15%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                                <td width="5%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.umur</div></td>
                                #foreach($listsaudaralist in $listsaudara)
                                
                                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                                
                                #set($wariskodsaudara=$listsaudaralist.kod)
                                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                                
                                
                                #end    
                                #end
                                <td width="15%" class="row2"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                                <td width="20%" class="row2"><div align="left" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nama_parent</div></td>
                                #if($listwarislapisan.statushidup=="0")
                                #set($hidup="Masih Hidup")
                                #end
                                #if($listwarislapisan.statushidup=="1")
                                #set($hidup="Sudah Meninggal")
                                #end
                                #if($listwarislapisan.statushidup=="")
                                #set($hidup="")
                                #end
                                <td width="10%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                                <td width="10%" class="row2"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
                              </tr>
                              #end
                              #end
                            </table>
                            #end </div>
                          </fieldset></td>
                      </tr>
                      #end
                      <tr>
                        <td>#if($!skrin_online != "yes")
                          <p align="right">CL - 01 - 69</p>
                          #end </td>
                      </tr>
                    </table>
                  </div>
                  <div class="TabbedPanelsContent"></div>
                  <div class="TabbedPanelsContent"></div>
                  <div class="TabbedPanelsContent"></div>
                  <div class="TabbedPanelsContent"></div>
                </div>
              </div>
            </div>
            <div class="TabbedPanelsContent">
              <div id="TabbedPanels4" class="TabbedPanelsContentVisible">
                <div class="TabbedPanelsContentGroup">
                  <div class="TabbedPanelsContent"></div>
                  <div class="TabbedPanelsContent"></div>
                </div>
              </div>
            </div>
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
		setSelected(0,2,0,0);
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
setSelected(0,2,0,0);
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
setSelected(0,2,0,0);
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
