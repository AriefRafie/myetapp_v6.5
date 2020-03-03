


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
.style3 {font-size: 12px}

.style36 {font-size: 12}
.style38 {font-size: 10px}
.style40 {color: #FF0000}
.style41 {color: #000000}
.style42 {color: #0000FF}
.style44 {
	font-size: 9px;
	font-style: italic;
}
.style45 {
	font-size: 9px;
	color: #0000FF;
}
.style50 {color: #0000FF; font-size: 9px; font-style: italic; }
.style52 {font-size: 10px; color: #000000; }
.style53 {font-size: 10px; color: #FF0000; }
-->
</style>
</head>

<body onload="submitForm();pilih_taraf();alamat_raya_up();alamat_raya()" >
<form id="f1" name="f1" method="post" action="">
<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input type="hidden" name="bandar" id="bandar"/>
<input type="hidden" name="simpanmode" id="simpanmode"/>



#if($insertbaru == "yes")
#set($txtNoKPBaru1Penting = "")
#set($txtNoKPBaru2Penting = "")
#set($txtNoKPBaru3Penting = "")
#set($txtNoKPLamaPenting = "")
#set($socJenisKPLainPenting = "")
#set($txtNoKPLainPenting = "")
#set($txtNamaOBPenting = "")
#set($socStatusOB = "")
#set($socTarafKepentinganPenting = "")
#set($socJantinaPenting = "")
#set($socAgamaPenting = "")
#set($socWarganegaraPenting = "")
#set($txdTarikhLahirPenting = "")
#set($txtUmurPenting = "")
#set($txtNoSuratBeranakPenting = "")
#set($txtAlamatTerakhir1Penting = "")
#set($txtAlamatTerakhir2Penting = "")
#set($txtAlamatTerakhir3Penting = "")
#set($txtPoskodPenting = "")
#set($socNegeriPenting = "")
#set($txtBandarPenting = "")
#set($txtAlamatTerakhir1WarisSurat = "")
#set($txtAlamatTerakhir2WarisSurat = "")
#set($txtAlamatTerakhir3WarisSurat = "")
#set($txtPoskodWarisSurat = "")
#set($socNegeriWarisSurat = "")
#set($txtBandarWarisSurat = "")
#set($txtCatatanPenting = "")
#set($FLAG_DAFTAR = "")
#set($txtNoTeleponPenting = "")
#set($txtNoHpPenting = "")
#set($jenis_pej = "")
#set($jenis_pemohon = "")
#set($txtNoFaxPenting = "")


#end



#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#end
  #parse("app/ppk/paging_sek8.jsp") 
  <input name="eventStatus" id="eventStatus" type="hidden" />
#parse("app/ppk/bil_fail.jsp") 

<table width="100%" border="0">


 <input type="hidden" name="command" value="">
 <input type="hidden" name="mode" value="">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
 
 <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
 
 

 #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                                          #if($readmode == "disabled")
              #set($readmodeR = "readonly")
              #else
              #set($readmodeR = "")
              #end
              #else
              #if($open_button_online == "no")
#set($readonly = "readonly")
#set($readmode = "disabled")
#else
#set($readonly = "")
#set($readmode = "")
#end
              #end

 


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
       <input name="idtemp" type="hidden"  value="$id"/>
       
       
        
 <input name="id_Suburusanstatus" type="hidden"  value="$list.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$list.id_Suburusanstatusfail"/>
<input name="id_Permohonansimati" type="hidden"  value="$list.id_Permohonansimati"/>
 
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
<td>
#parse("app/ppk/syarat_kemaskini.jsp")

#parse("app/ppk/maklumat_sek8.jsp")

#set($md=$listtarikhMohon)
                <input type="hidden" name="txdTarikhMohon" id="txdTarikhMohon" value="$listtarikhMohon" />
                 <input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
                    <input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
            <input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/>

</td>
</tr>




  <tr>
    <td>
   <div id="TabbedPanels1" class="TabbedPanels">
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
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,1,0,0);PemohonView()">PEMOHON</li>
            
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,2,0,0);WarisView()">WARIS</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,3,0,0);PentingView()" id="maklumat_pemohon" >ORANG BERKEPENTINGAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,5,0,0);PemiutangView()">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
          </ul>
          
          
          <div class="TabbedPanelsContentGroup">
            <div>
            </div>
            
            <div>            
            </div>
            
            <div>           
            </div>
            <div class="TabbedPanelsContentVisible">
            #parse("app/ppk/info_berjaya_disimpan.jsp")
           <table width="100%" border="0">
             <input type="hidden" name="read_mode" id="read_mode" value="$!readmode" > 
              
                         #if($listPentingbyIDOB.size() > 0)
                                       #foreach($listob in $listPentingbyIDOB)
                                     
                                     
                                       #set($id_Pemohon = $listob.id_Pemohon)
                                       #set($txtIdSimatiPenting = $listob.idSimati)
                                       #set($txtIdOb = $listob.idOb)
                                       
                                       #set($txtNoKPBaru1PentingU = $listob.nokpbaru1)
                                       #set($txtNoKPBaru2PentingU = $listob.nokpbaru2)
                                       #set($txtNoKPBaru3PentingU = $listob.nokpbaru3)
                                       
                                       #set($txtNoKPLamaPentingU = $listob.nokplama)
                                       #set($socJenisKPLainPentingU = $listob.jeniskp)
                                       #set($txtNoKPLainPentingU = $listob.nokplain)
                                       #set($txtNamaOBPentingU = $listob.nama_Ob)
                                       #set($socStatusOBU = $listob.status_Ob)
                                       #set($socTarafKepentinganPentingU = $listob.taraf)
                                       #set($socJantinaPentingU = $listob.jantina)
                                       #set($socAgamaPentingU = $listob.agama)
                                       #set($socWarganegaraPentingU = $listob.warga)
                                       #set($txdTarikhLahirPentingU = $listob.dob)
                                       #set($txtUmurPentingU = $listob.umur)
                                       #set($txtNoSuratBeranakPentingU = $listob.noberanak)
                                       #set($txtAlamatTerakhir1PentingU = $listob.alamat1)
                                       #set($txtAlamatTerakhir2PentingU = $listob.alamat2)
                                       #set($txtAlamatTerakhir3PentingU = $listob.alamat3)
                                       #set($txtPoskodPentingU = $listob.poskod)
                                       #set($socNegeriPentingU = $listob.idnegeri)
                                       #set($socBandarPentingU = $listob.idbandar)
                                       #set($txtAlamatTerakhir1WarisSurat = $listob.alamat1Surat)
                                       #set($txtAlamatTerakhir2WarisSurat = $listob.alamat2Surat)
                                       #set($txtAlamatTerakhir3WarisSurat = $listob.alamat3Surat)
                                       #set($txtPoskodWarisSurat = $listob.poskodSurat)
                                       #set($socNegeriWarisSurat = $listob.idnegeriSurat)
                                       #set($socBandarWarisSurat = $listob.idbandarSurat)
                                       #set($txtCatatanPentingU = $listob.catatan)
                                       #set($FLAG_DAFTAR = $listob.FLAG_DAFTAR)
                                       #set($txtNoTeleponPentingU = $listob.noTel)
                                       
                                        #set($txtNoFaxPenting = $listob.no_fax)
                                        #set($txtNoHpPenting = $listob.no_hp)
                                        #set($jenis_pej = $listob.jenis_pej)
                                        
                                         #set($jenis_pemohon = $listob.jenishutang)
                                       
                                        
                                       
                                       #end
                                       
                                      
                                       #end
                                       
                                       
                    <input type="hidden" name="txtIdOb" value="$txtIdOb" > 
                        
                                     #if($nk_update_penting=="yes")
                                     
                                     
                                  
                                       
                                       
                                       <input type="hidden" name="id_Pemohon" value="$id_Pemohon" >      
     <tr>
                                         <td>
                                          <fieldset>
                            

              
            
   
    
                                          <legend>MAKLUMAT ORANG BERKEPENTINGAN</legend>
                                          
                                          <table width="100%" border="0">
                              <tr>
                               <td width="50%" valign="top"><table width="100%">
                                  <input type="hidden" name="txtIdSimatiPenting" value="$txtIdSimatiPenting" >
                                    <input type="hidden" name="socTarafKepentinganPenting" value="$socTarafKepentinganPenting" >
                                  <tr>
                                      <td valign="top" class="style53">#if($readmode != "disabled")*#end</td>
                                          <td><div align="left" class="style41"><span class="style38"> #if($readmode != "disabled") Taraf Kepentingan #else
                                            Taraf Kepentingan
                                     #end </span></div></td>
                                         
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                        
                                         
                                      
	                                 <td>
                                    #if($readmode == "disabled") 
                                 <input type = "hidden"  name="socTarafKepentinganPentingU" value="$socTarafKepentinganPentingU" />
                                      #foreach($listtar in $listtaraf)
                                 
                                 #if($socTarafKepentinganPentingU==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
	                              
	                         
                                   
                                 #end    
	                               #end   
                                   
                                   #if($socTarafKepentinganPentingU!="" && $socTarafKepentinganPentingU!="0" )
                                     <input name="tar" value="$tarafkepentinganP - $tarafkepentinganketeranganP" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="tar" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                                     
                                     #else
                                  #foreach($listtar in $listtaraf)
                                 
                                 #if($socTarafKepentinganPentingU==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
	                              
	                         
                                   
                                 #end    
	                               #end   
	                               
	                              #if($socTarafKepentinganPentingU!="" && $socTarafKepentinganPentingU!="0")
                                  <select name="socTarafKepentinganPentingU" class="largeselect" $readmode id="socTarafKepentinganPentingU" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_taraf();pilih_amanahU();default_amanah()">
                                   <option value="$socTarafKepentinganPentingU" style="text-transform:uppercase;" onblur="uppercase()">$tarafkepentinganP - $tarafkepentinganketeranganP</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                  #if($socTarafKepentinganPentingU!=$listtar.id_Tarafkptg)
                                   #if($listtar.id_Tarafkptg!=1 && $listtar.id_Tarafkptg!=2 && $listtar.id_Tarafkptg!=14)
	                               <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                   #end
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  
                                  #else
                                  <select name="socTarafKepentinganPentingU" class="largeselect" $readmode id="socTarafKepentinganPentingU" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_taraf();pilih_amanahU();default_amanah()">
                                   <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Taraf Kepentingan</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                    #if($listtar.id_Tarafkptg!=1 && $listtar.id_Tarafkptg!=2 && $listtar.id_Tarafkptg!=14)
	                               <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                   #end
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                   
                                  #end                                   </td>
                                  #end                                      </tr>
                                  
                                  
                                     <input name="jenis_pej" id="jenis_pej" type="hidden"  value="$!jenis_pej" />        
                                  
                                  <tr id="amanah" >       
          <td >&nbsp;</td>
          <td ><span class="style38">Amanah raya</span></td>
          <td >:</td>
          <td >      
     
          <select name="jenis_pej1" id="jenis_pej1" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya_up()" >         
          #if($!jenis_pej == "" || $!jenis_pej == "0") 
          <option value="">SILA PILIH</option>   
                 
           #else
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                  #foreach($listJ in $listMaklumatMahkamahJ)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '9' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                  <!-- <option value="">SILA PILIH</option> -->
                                  #end                                       
          </select>          </td>
        </tr>     
        
        <tr id="Insolvensi" >       
          <td >&nbsp;</td>
          <td ><span class="style38">Jabatan Insolvensi</span></td>
          <td >:</td>
          <td >      
     
          <select name="jenis_pej3" id="jenis_pej3" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya_up()" >         
          #if($!jenis_pej == "" || $!jenis_pej == "0") 
          <option value="">SILA PILIH</option>   
                 
           #else
           #foreach($listJ in $listMaklumatInsolvensi)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                  #foreach($listJ in $listMaklumatInsolvensi)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '141782' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                 <!-- <option value="">SILA PILIH </option> --> 
                                  #end                                       
          </select>          </td>
        </tr>     
 
 
 
 
 <tr id="baitulmal" >
    
        
          <td >&nbsp;</td>
          <td ><span class="style38">Baitulmal</span></td>
          <td >:</td>
          <td >      
     <!--
          <select name="jenis_pej2" id="jenis_pej2" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya_up()" >         
          #if($!jenis_pej == "" || $!jenis_pej == "0") 
          <option value="">SILA PILIH </option>   
                 
           #else
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                  #foreach($listJ in $listMaklumatMahkamahJ)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '8' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                  <option value="">SILA PILIH </option>
                                  #end                                       
          </select>       
          
          -->
          
          
          
          <select name="jenis_pej2" id="jenis_pej2" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya_up()" >         
          #if($!jenis_pej == "" || $!jenis_pej == "0") 
          <option value="">SILA PILIH</option>   
                 
           #else
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                  #foreach($listJ in $listMaklumatMahkamahJ)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '61' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                  <!-- <option value="">SILA PILIH</option> -->
                                  #end                                       
          </select>       
          
          
          
          
             </td>
        </tr>     
 
          <tr id="j_pemohon"  >
            <td >&nbsp;</td>
            <td class="style38" >Jenis Pemohon</td>
            <td >:</td>
            <td >
            
           #if($readmode == "disabled")
                                      #if($jenis_pemohon == "1")
                                      #set($jh = "01-AGENSI")
                                      #elseif($jenis_pemohon == "2")
                                       #set($jh = "02-INDIVIDU")
                                      #else
                                       #set($jh = "")
                                      #end
                                      
                                      #if($jenis_pemohon != "0" && $jenis_pemohon != "")
                                      <input type="text" name="jenis_pemohonx2"  id="jenis_pemohonx2" value="$jh" size = "34" $readmodeR class="$readmode" />    
                                     #else
                              <input type="text" name="jenis_pemohonx"  id="jenis_pemohonx" value="" size = "34" $readmodeR class="$readmode" >    
                                     #end    
                                      
                                     <input type="hidden" name="jenis_pemohon"  id="jenis_pemohon" value="$jenis_pemohon" size = "34" >    
                                     #else
            
            
            <span id="jenis_pemohon_drop"  >
           
            
<select name="jenis_pemohon"  class="autoselect" $readmode id="jenis_pemohon" style="text-transform:uppercase;" onBlur="uppercase()" onChange="pilih_taraf()" >        
								   #if($jenis_pemohon=="1")  
                                      <option value="1">01-Agensi</option>
                                      <option value="2">02-Individu</option>
                                   #elseif($jenis_pemohon=="2")
                                      <option value="2">02-Individu</option>
                                      <option value="1">01-Agensi</option>
	                               #else 
                                        <option value="2">02-Individu</option>
                                        <option value="1">01-Agensi</option>
	                               #end
                                    
                                  
                                    
                                     
                                      
                                    </select>
                                    </span>
                                    
                                    <span id="jenis_pemohon_dis">
                                    <input type="text" name="jenis_pemohon_display"  id="jenis_pemohon_display" readonly class="disabled" >    
                                    </span>
                                    
                                       
                                
            #end            </td>
          </tr>
                                  
                                  
                                  
                                  <tr id="kp_baru">
                                    <td width="1%" class="style53" >&nbsp;</td>
                                  <td width="28%" ><div align="left" class="style41"><span class="style38">MyID Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><label>
                                   
                                     <input name="txtNoKPBaru1PentingU" type="text" id="txtNoKPBaru1PentingU" style="width: 50px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2PentingU')" value="$txtNoKPBaru1PentingU" size="7" maxlength="6" $readmodeR class="$readmode" onBlur="calculateTarikhLahirOBU();"/> 
<!-- onBlur="getAgeByIC(this,this.value,'txtUmurPentingU');getStatusByIC(this,this.value,'socStatusOBU');getDOBU(this.value)"/-->
                                     - 
                                     <input name="txtNoKPBaru2PentingU" type="text" id="txtNoKPBaru2PentingU" style="width: 20px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$txtNoKPBaru2PentingU" size="1" maxlength="2" $readmodeR class="$readmode" />
                                     -
                                     <input name="txtNoKPBaru3PentingU" type="text" id="txtNoKPBaru3PentingU"  style="width: 40px;" onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$txtNoKPBaru3PentingU" size="5" maxlength="4" $readmodeR class="$readmode" />
                                  
                                  </label>
                                  
                                  #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                                  
                                  #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style45" >  www.jpn.gov.my</a>
  
  #end
  #end                                  </td>
                                </tr>
                                <tr id="kp_lama">
                                  <td class="style53" >&nbsp;</td>
                                  <td ><div align="left" class="style41"><span class="style38">MyID Lama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPentingU" type="text" id="textfield4" value="$txtNoKPLamaPentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" maxlength="15"  $readmodeR class="$readmode"/>
                                  </label></td>
                                </tr>
                                <tr id="kp_jenis">
                                  <td class="style53" >&nbsp;</td>
                                  <td ><div align="left" class="style41"><span class="style38">Jenis MyID Lain</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                                   #if($readmode=="disabled")
                                          
                                          #if($socJenisKPLainPentingU=="5")
                                          #set($pkp="Tentera")
                                          
                                          #elseif($socJenisKPLainPentingU=="6")
                                          #set($pkp="Polis")
                                          
                                          #elseif($socJenisKPLainPentingU=="4")
                                          #set($pkp="Pasport")
                                          
                                          #elseif($socJenisKPLainPentingU=="7")
                                          #set($pkp="Lain-lain")
                                          
                                          #elseif($socJenisKPLainPentingU=="0")
                                          #set($pkp="")
                                          
                                          #else
                                          #set($pkp="")
                                          #end
                                          
                                           <input name="textfield4" type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="12" $readmodeR class="$readmode" />
                                          
                                          #else
                                   
									
									 <select name="socJenisKPLainPentingU"  class="mediumselect" $readmode id="socJenisKPLainPentingU" style="text-transform:uppercase;" onblur="kplain2X(this.value)" onchange="kplain2(this.value)">
								   #if($socJenisKPLainPentingU=="4")
	                                 
                                       <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
									   <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                     
                                      
	                              
	                               #elseif($socJenisKPLainPentingU=="6")
	                                
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                       <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                       <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                     
                                      
	                              
								   #elseif($socJenisKPLainPentingU=="5")
	                               		<option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                       <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                      #elseif($socJenisKPLainPentingU=="7")
                                        <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
	                               		<option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                     
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                      
	                               #else
	                                 
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                       <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      
	                               #end
                                    
                                  
                                    </select>
                                    #end
									 <label></label></td>
                                </tr>
                                 #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($socJenisKPLainPentingU != 0 && $socJenisKPLainPentingU != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
                                  <tr id="kp_lain">
                                    <td class="style53">&nbsp;</td>
                                    <td><div align="left" class="style41"><span class="style38"> MyID Lain</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><span class="style36">
                                    
                                   
                                    
                                    #if($readmode == "disabled")
                                     <input name="txtNoKPLainPentingU" type="text" id="txtNoKPLainPentingU"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoKPLainPentingU" size="15" maxlength="25" $readmodeR class="$readmode" />
                                    
                                    #else
                                      <input name="txtNoKPLainPentingU" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoKPLainPentingU" size="15" maxlength="25" $readmodekp    />
                                      #end
                                    </span></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style53">#if($readmode != "disabled")*#end</td>
                                    <td><div align="left" class="style41"><span class="style38"> #if($readmode != "disabled") Nama OB #else
                                      Nama OB
                                    #end </span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                  
                                     <span id="nama_1" >
                                      <input name="txtNamaOBPentingU" type="text" class="$readmode" id="txtNamaOBPentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNamaOBPentingU" size="45" maxlength="150"  $readmodeR />
                                      </span>
                                      
                                        <span id="nama_2" >
                                        #if($readmode != "disabled")
                                      <input name="txtNamaOBPenting_D" type="text" id="txtNamaOBPenting_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtNamaOBPentingU" size="45" maxlength="150" readonly class="disabled" />
                                      #end
                                      </span>
                                              <span id="add_alamat_raya_up" > </span>    
                                    </label></td>
                                  </tr>
                                  
                                   
                                  <tr id="jantina" >
                                    <td class="style53">&nbsp;</td>
                                  <td><div align="left" class="style41"><span class="style38">Jantina</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                  
                                  
                                     #if($readmode=="disabled")
                                          
                                          #if($socJantinaPentingU=="1")
                                          #set($sexpemohon="Lelaki")
                                          
                                          
                                          #elseif($socJantinaPentingU=="2")
                                          #set($sexpemohon="Perempuan")
                                          
                                          #else
                                          #set($sexpemohon="")
                                          #end
                                          
                                         
                                          <input name="textfield" type="text" id="textfield2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" $readmodeR class="$readmode" value="$sexpemohon" />
                                          #else
                                    <select name="socJantinaPentingU" id="select2" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($socJantinaPentingU=="1")
	                               
                                      
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                      
	                               #elseif($socJantinaPentingU=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                      
	                               #else
	                               
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jantina</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                      
	                               #end
                                     
                                    
                                    </select>
                                    #end
                                    </label></td>
                                </tr> 
                                
                                 <tr id="agama" >
                                   <td class="style53">&nbsp;</td>
                                  <td><div align="left" class="style41"><span class="style38">Agama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                    #if($readmode=="disabled")
                                          
                                          #if($socAgamaPentingU=="1")
                                          
                                          #set($agp="Islam")
                                          
                                          #elseif($socAgamaPentingU=="2")
                                          
                                          #set($agp="Bukan Islam")
                                          
                                          #else
                                          
                                          #set($agp="")
                                          
                                          #end
                                          
                                          
                                          <input name="socAgamaPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="15"  $readmodeR class="$readmode"  value="$agp" />
                                          #else
                                          
                                    
                                    <select name="socAgamaPentingU" id="select3" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($socAgamaPentingU=="1")
	                               
	                               
                                      
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      
	                               #elseif($socAgamaPentingU=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      
	                               #else
	                               
                                    
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      
	                               #end
                                      
                                    
                                    </select>
                                    #end
                                     </label></td>
                                </tr>
                                      
                                         <tr id="warga">
                                           <td class="style53">&nbsp;</td>
                                  <td><div align="left" class="style41"><span class="style38">Warganegara</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                         #if($readmode=="disabled")
                                          
                                          #if($socWarganegaraPentingU=="1")
                                          
                                          #set($wrp="Warganegara")
                                          
                                          #elseif($socWarganegaraPentingU=="2")
                                          
                                          #set($wrp="Bukan Warganegara")
                                          
                                          #elseif($socWarganegaraPentingU=="3")
                                          
                                          #set($wrp="Pemastautin Tetap")
                                          
                                          #else
                                          #set($wrp="")
                                          
                                          #end
                                          
                                          
                                          <input name="socWarganegaraPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="25" $readmodeR class="$readmode" value="$wrp" />
                                          #else
                                   
                                    <select name="socWarganegaraPentingU" id="select4" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($socWarganegaraPentingU=="1")
	                               
                                      
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #elseif($socWarganegaraPentingU=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #elseif($socWarganegaraPentingU=="3")
	                               
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      
                                   #else
                                   
                                    
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #end
                                  
                                    
                                    </select>
                                    #end
                                     </label></td>
                                </tr>
                                <tr id="dob" >
                                  <td class="style53">&nbsp;</td>
                                  <td><div align="right" class="style38 style41">
                                    <div align="left">Tarikh Lahir</div>
                                  </div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                      <input name="txdTarikhLahirPentingU" type="text" style="text-transform:uppercase;"  id="txdTarikhLahirPentingU" size="10" maxlength="10" $readmodeR class="$readmode" value="$txdTarikhLahirPentingU" onblur="trans_date1(this.value);getAgebyDob(this,this.value,'txtUmurPentingU');defineStatusWarisByUmurOBU();"/>
                                      #if($readmode != "disabled") 
                                    <a href="javascript:displayDatePicker('txdTarikhLahirPentingU',false,'dmy');">#parse("app/ppk/ppk_calender.jsp") </a></label>
                                    #end                                    </td>
                                </tr> 
                                <tr id="umur" >
                                  <td class="style53" >&nbsp;</td>
                                  <td ><div align="left" class="style41"><span class="style38">Umur</span></div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><span class="style36">
                                    <input name="txtUmurPentingU" type="text" id="txtUmurPentingU"  value="$txtUmurPentingU" size="3" maxlength="3" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="CheckumurU();getStatusByAge(this,this.value,'socStatusOBU')"  onkeyup="javascript:validateIC(event,this,this.value,'txtUmurPentingU')"/>
                                  </span></td>
                                </tr>
                                <tr id="status_ob" >
                                    <td class="style53" >&nbsp;</td>
                                  <td ><div align="left" class="style41"><span class="style38">Status OB</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                                #if($readmode == "disabled")
                                          
                                          #if($socStatusOBU=="1")
                                          #set($stat = "Dewasa/Waras")
                                          #elseif($socStatusOBU=="2")
                                          #set($stat = "Belum Dewasa")
                                          #elseif($socStatusOBU=="3")
                                          #set($stat = "Hilang")
                                          #elseif($socStatusOBU=="4")
                                          #set($stat = "Tidak Sempurna Akal")
                                          #else
                                          #set($stat = "")
                                          #end
                                          
                                          <input type="text" name="stat" id="stat" value="$stat" size="25"  style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                          
                                          #else
                                    
                                       <select name="socStatusOBU" id="socStatusOBU"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()"  onchange="status_obU()">
									
								   #if($socStatusOBU=="1")
	                                 
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
                                        <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                      
	                              
	                               #elseif($socStatusOBU=="2")
	                                


	                                  <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                     <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                    
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
                                        <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                      
	                              
								   #elseif($socStatusOBU=="3")
	                               
	                                 <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                    
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                        <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                     
                                   #elseif($socStatusOBU=="4")
	                                    <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                        
	                               #else
	                                 
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                               #end
                                   </select>                           
                                   
                                   #end       </td>
                                </tr>
                                <tr id="no_surat">
                                  <td class="style53">&nbsp;</td>
                                    <td><div align="left" class="style41"><span class="style38">No Surat Beranak</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNoSuratBeranakPentingU" type="text" id="txtNoSuratBeranakPentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoSuratBeranakPentingU" size="15" maxlength="10" $readmodeR class="$readmode" />
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td width="1%" valign="top" class="style53" >#if($readmode != "disabled")*#end</td>
                                    <td class="style38" width="28%"><div align="right" class="style52">
                                      <div align="left">#if($readmode != "disabled") Alamat Tetap #else <span class="style72">Alamat Tetap </span>#end</div>
                                    </div></td>
                                    <td width="1%"><div align="right" class="style38">:</div></td>
                                    <td width="70%"><label>
                                    
                                      <span id="alamattetap1_a">
                                      <input name="txtAlamatTerakhir1PentingU" type="text" class="$readmode" id="txtAlamatTerakhir1PentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1PentingU" size="45" maxlength="150"  $readmodeR />
                                      </span>
                                       <span id="alamattetap1_b">
                                        #if($readmode != "disabled")
                                      <input name="txtAlamatTerakhir1Penting_D" type="text" id="txtAlamatTerakhir1Penting_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1PentingU" size="45" maxlength="150" readonly class="disabled" />
                                      #end
                                      </span>
                                      
                                      
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style53">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style41"></span></div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                     <span id="alamattetap2_a">
                                      <input name="txtAlamatTerakhir2PentingU" type="text" class="$readmode" id="txtAlamatTerakhir2PentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir2PentingU" size="45" maxlength="150" $readmodeR  />
                                      </span>
                                      <span id="alamattetap2_b">
                                       #if($readmode != "disabled")
                                      <input name="txtAlamatTerakhir2Penting_D" type="text" id="txtAlamatTerakhir2Penting_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir2PentingU" size="45" maxlength="150" readonly class="disabled"/>
                                      #end
                                      </span>
                                      
                                      
                                      
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style53">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style41"></span></div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td>
                                    
                                     <span id="alamattetap3_a">
                                    <input name="txtAlamatTerakhir3PentingU" type="text" class="$readmode" id="txtAlamatTerakhir3PentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir3PentingU" size="45" maxlength="150" $readmodeR />
                                    </span>
                                    
                                     <span id="alamattetap3_b">
                                      #if($readmode != "disabled")
                                    <input name="txtAlamatTerakhir3Penting_D" type="text" id="txtAlamatTerakhir3Penting_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir3PentingU" size="45" maxlength="150" readonly class="disabled"/>
                                    #end
                                    </span>
                                    
                                    
                                    </td>
                                  </tr>
                                  <tr>
                                    <td width="1%" valign="top" class="style53" >#if($readmode != "disabled")*#end</td>
                                    <td class="style38"><div align="right" class="style52">
                                      <div align="left">#if($readmode != "disabled") Poskod #else <span class="style72">Poskod</span> #end</div>
                                    </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                    
                                     <span id="poskodtetap_a" >
                                      <input name="txtPoskodPentingU" type="text" id="txtPoskodPentingU" value="$txtPoskodPentingU" size="5" maxlength="5" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPentingU')" />
                                      </span>
                                      
                                      
                                       <span id="poskodtetap_b" >
                                        #if($readmode != "disabled")
                                      <input name="txtPoskodPenting_D" type="text" id="txtPoskodPenting_D" style="text-transform:uppercase;" onblur="uppercase()" size="5" maxlength="5" readonly class="disabled" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPenting')" value="$txtPoskodPentingU"/>
                                      #end
                                      </span>
                                      
                                    </label></td>
                                  </tr>
                                  
                                  <tr>
                                    <td width="1%" valign="top" class="style53" >#if($readmode != "disabled")*#end</td>
                                    <td class="style38"><div align="right" class="style52">
                                      <div align="left">#if($readmode != "disabled") Negeri #else <span class="style72">Negeri</span> #end</div>
                                    </div></td>
                                  
                                    <td><div align="right" class="style38">:</div></td>
                                     <td>
                                     #if($readmode == "disabled")
                                   
                                    #foreach($listnegpomo in $listnegeri)                                    
                                    #if($socNegeriPentingU==$listnegpomo.id_Negeri)                                    
                                    #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                    #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                    #end 
                                    #end
                                    
                                     #if($socNegeriPentingU!="" && $socNegeriPentingU!="0" )
                                     <input name="nt" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="nt" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                                    
                                   
                                   #else
                                   
                                      <span id="negeritetap_a"   > 
                                    #foreach($listnegpomo in $listnegeri)
                                    
                                    #if($socNegeriPentingU==$listnegpomo.id_Negeri)
                                    
                                    #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                    #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                    
                                    
                                    
                                    #end 
                                    #end
                                   #if($socNegeriPentingU!="" && $socNegeriPentingU!="0" )
                                      <select name="socNegeriPentingU" id="socNegeriPentingU" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="getBandarTetapU('socBandarPentingU')">
                                        <option value="$socNegeriPentingU" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                        
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($socNegeriPentingU!=$listnegpomo.id_Negeri)
                                    
	                               
        
                                        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
                                        
        
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
      
                                      
                                      </select>
#else
<select name="socNegeriPentingU" id="socNegeriPentingU" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="getBandarTetapU('socBandarPentingU')">
  <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
  
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
        
                                        
  <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
  
                                        
        
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
      
                                      
</select>
#end
</span>

 
                                    


 <span id="negeritetap_b" >   
                         #if($readmode != "disabled")
                          #if($socNegeriPentingU!="" && $socNegeriPentingU!="0" )       
             <input name="socNegeriPenting_D" size="45" style="text-transform:uppercase;" readonly class="disabled" value="$negerikodpemoP - $negeriketeranganpemoP" />
             #else
              <input name="socNegeriPenting_D" size="45" style="text-transform:uppercase;" readonly class="disabled" value="" />
             #end
  
                        #end  
                              

</span>

#end</td>
                                  </tr>
                                  <tr>
                                    <td width="1%" valign="top" class="style53" >#if($readmode != "disabled")#end</td>
                                    <td class="style38"><div align="right" class="style52">
                                      <div align="left">#if($readmode != "disabled") Bandar #else <span class="style72">Bandar</span> #end</div>
                                    </div></td>
                                   
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                   
                                     #if($readmode == "disabled")
                                   
                           
                #foreach($listneg in $listBandarTetapbyNegeri)      
                
                
                #if($socBandarPentingU==$listneg.id)                                      
                #set($kodbbb="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
            
                                    
                                     #if($socBandarPentingU!="" && $socBandarPentingU!="0" )
                                     <input name="ntbb" value="$kodbbb" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="ntbb" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                                    
                                   
                                   #else
                                                 
               <span id="bandartetap_a" >
                         
                                    #if($socBandarPentingU == "" || $socBandarPentingU=="0")
                #set($kodb="")
                
                #else
                
                #foreach($listneg in $listBandarTetapbyNegeri)      
                
                
                #if($socBandarPentingU==$listneg.id)                                      
                #set($kodb="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
                #end
                
                
                
              
                
                #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                #if($socBandarPentingU==$listdaerah.id)                                
                #set($listDaerahbyNegeriK=$listdaerah.kod)
                #set($listDaerahbyNegeriN=$listdaerah.nama)
                #end 
                #end
                
                
                #if($disabled=="disabled")
                #set($readmodedaerah="disabled")
                #end
                #if($socBandarPentingU!="" && $socBandarPentingU!="0" )
                
                
              
                
                <select name="socBandarPentingU" id="socBandarPentingU" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetapU()" >
                  <option value="$socBandarPentingU">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                  
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($socBandarPentingU!=$listdaerah.id)
              
                                  
                  
                  
                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                  
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                </select>
#else
<select name="socBandarPentingU" id="socBandarPentingU" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetapU()" >
  <option value="">Sila Pilih Bandar</option>
  
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                

</select>
#end 

</span>


<span id="bandartetap_b"   >   
                      #if($readmode != "disabled") 
                       #if($socBandarPentingU!="" && $socBandarPentingU!="0" )         
             <input name="txtBandarPenting_D" size="45" style="text-transform:uppercase;" readonly class="disabled" value="$kodbbb" />
             #else
            <input name="txtBandarPenting_D" size="45" style="text-transform:uppercase;" readonly class="disabled" value="" />
             #end
             
             #end
                               </span>

</label>



#end</label></td>
                                  </tr>
                                  
                                </table></td>
                                <td width="50%" valign="top"><table width="100%" border="0">

                                  
                                  #if($readmode != "disabled")
                                  <tr id="chk_tr">
                                    <td class="style53" >&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style41"></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>#if($check_copy == "on")
                                    #set($ch_copy = "checked")
                                    #else
                                    #set($ch_copy = "")
                                    #end
                                    <input type="checkbox" name="copyAlamat" id="copyAlamat" $ch_copy onclick="getBandarTetapCopyU('maklumat_pemohon')"  />
                                    <span class="style50" >Alamat surat menyurat adalah alamat tetap</span></label></td>
                                  </tr>
                                  #end
                                  <tr>
                                   <td width="1%" valign="top" class="style53" >#if($readmode != "disabled")*#end</td>
                                    <td width="29%" class="style38" ><div align="right" class="style52">
                                      <div align="left">#if($readmode != "disabled") Alamat Surat #else <span class="style72">Alamat Surat</span>#end</div>
                                    </div></td>
                                    <td width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%"><label>
                                     <span id="alamatsurat1_a">
                                      <input name="txtAlamatTerakhir1WarisSurat" type="text" class="$readmode" id="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1WarisSurat" size="45" maxlength="150"  $readmodeR/>
                                      </span>
                                      
                                      
                                        <span id="alamatsurat1_b">
                                         #if($readmode != "disabled")
                                      <input name="txtAlamatTerakhir1WarisSurat_D" type="text" id="txtAlamatTerakhir1WarisSurat_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1WarisSurat" size="45" maxlength="150"  readonly class="disabled"/>
                                      #end
                                      </span>
                                      
                                      
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style53">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <span id="alamatsurat2_a">
                                      <input name="txtAlamatTerakhir2WarisSurat" type="text" class="$readmode" id="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir2WarisSurat" size="45" maxlength="150" $readmodeR />
                                      </span>
                                      
                                        <span id="alamatsurat2_b">
                                         #if($readmode != "disabled")
                                      <input name="txtAlamatTerakhir2WarisSurat_D" type="text" id="txtAlamatTerakhir2WarisSurat_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir2WarisSurat"   size="45" maxlength="150" readonly class="disabled"/>
                                      #end
                                   </span>
                                      
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style53">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td>
                                     <span id="alamatsurat3_a">
                                    <input name="txtAlamatTerakhir3WarisSurat" type="text" class="$readmode" id="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir3WarisSurat" size="45" maxlength="150" $readmodeR />
                                    </span>
                                    
                                     <span id="alamatsurat3_b">
                                      #if($readmode != "disabled")
                                    <input name="txtAlamatTerakhir3WarisSurat_D" type="text" id="txtAlamatTerakhir3WarisSurat_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir3WarisSurat"  size="45" maxlength="150" readonly class="disabled"/>
                                    #end
                                     </span>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td width="1%" valign="top" class="style53" >#if($readmode != "disabled")*#end</td>
                                    <td class="style38"><div align="right" class="style52">
                                      <div align="left">#if($readmode != "disabled") Poskod #else <span class="style72">Poskod</span> #end</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                     <span id="poskodsurat_a">
                                      <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$txtPoskodWarisSurat" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmodeR class="$readmode"/>
                                      </span>
                                       <span id="poskodsurat_b">
                                        #if($readmode != "disabled")
                                      <input name="txtPoskodWarisSurat_D" type="text" id="txtPoskodWarisSurat_D" style="text-transform:uppercase;" onblur="uppercase()"  size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" readonly class="disabled" value="$txtPoskodWarisSurat"/>
                                    #end
                                      </span>
                                      
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td width="1%" valign="top" class="style53" >#if($readmode != "disabled")*#end</td>
                                    <td class="style38"><div align="right" class="style52">
                                      <div align="left">#if($readmode != "disabled") Negeri #else <span class="style72">Negeri</span> #end</div>
                                    </div></td>
                                  
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                  
                                    <td>
                                     #if($readmode == "disabled")
                                   
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
                                     <span id="negerisurat_a"   >  
                                    
                                    #foreach($listnegpomo in $listnegeri)
                                    
                                    #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                    
                                    #set($kod = "$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri")
                                    
                                    
                                    
                                    
                                    #end 
                                    #end
                                    #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!=0)
                                    <select name="socNegeriWarisSurat" class="autoselect" id="socNegeriWarisSurat" $readmode onchange="getBandarSuratU('socBandarWarisSurat')" >
                                      <option value="$socNegeriWarisSurat" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                      
                                        
                  
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($socNegeriWarisSurat!=$listneg.id_Negeri)
                       
                  
                                        
                                      <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                      
                                        
                  
                                     
                                  #end    
	                              #end
                                        
                
                                      
                                    </select>
#else
<select name="socNegeriWarisSurat" class="autoselect" onchange="getBandarSuratU('socBandarWarisSurat')" $readmode >
  <option value="" >SILA PILIH NEGERI</option>
  
  
                  
                                  #foreach($listneg in $listnegeri)
                   
                  
  
  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
  
  
                  
                                    
	                               #end
                                        
                

</select>
#end
</span>

</span>
  
             
 <span id="negerisurat_b"   >  
 
  
 
  
  #if($readmode != "disabled")
  #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!="0" )
 <input name="socNegeriWarisSurat_D" size="45"  readonly class="disabled"  style="text-transform:uppercase;" value="$negerikodpemoPs - $negeriketeranganpemoPs" />
 #else
 <input name="socNegeriWarisSurat_D" size="45" style="text-transform:uppercase;" readonly class="disabled"   value="" />
 #end
 #end
 </span>





#end </td>
                                  </tr>
                                  <tr>
                                    <td width="1%" valign="top" class="style53" >#if($readmode != "disabled")#end</td>
                                    <td class="style38"><div align="right" class="style52">
                                      <div align="left">#if($readmode != "disabled") Bandar #else <span class="style72">Bandar</span> #end</div>
                                    </div></td>
                                   
                                    
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                                  #if($readmode == "disabled")
                                   
                           
                #foreach($listneg in $listBandarSuratbyNegeri)      
                
                
                #if($socBandarWarisSurat==$listneg.id)                                      
                #set($kodss="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
            
                                    
                                     #if($socBandarWarisSurat!="" && $socBandarWarisSurat!="0" )
                                     <input name="ntbb" value="$kodss" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="ntbb" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                                    
                                   
                                   #else
                                     <span id="bandarsurat_a"   >  
                                    
                                    #if($socBandarWarisSurat == "" || $socBandarWarisSurat=="0")
                #set($kodbx="")
                
                #else
                
                #foreach($listneg in $listBandarSuratbyNegeri)      
                
                
                #if($socBandarWarisSurat==$listneg.id)                                      
                #set($kodbx="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
                #end
                
                
                
              
                
                #foreach($listdaerah in $listBandarSuratbyNegeri)                                
                #if($socBandarWarisSurat==$listdaerah.id)                                
                #set($listDaerahbyNegeriK=$listdaerah.kod)
                #set($listDaerahbyNegeriN=$listdaerah.nama)
                #end 
                #end
                
                
                #if($disabled=="disabled")
                #set($readmodedaerah="disabled")
                #end
                #if($socBandarWarisSurat!="" && $socBandarWarisSurat!="0" )
                <select name="socBandarWarisSurat" id="socBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSuratU()" >
                  <option value="$socBandarWarisSurat">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                  
                  
                  
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                 
                                  #if($socBandarWarisSurat!=$listdaerah.id)
              
                                  
                  
                  
                  
                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                  
                  
                  
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                
                
                
                </select>
#else
<select name="socBandarWarisSurat" id="socBandarWarisSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSuratU()" >
  <option value="">Sila Pilih Bandar</option>
  
  
  
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  
  
  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
  
  
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                


</select>
#end

</span>

 

<span id="bandarsurat_b" >
 #if($readmode != "disabled")
 #if($socBandarWarisSurat!="" && $socBandarWarisSurat!="0" )
 <input name="txtBandarWarisSurat_D" size="45" style="text-transform:uppercase;" readonly class="disabled" value="$kodss" />
 #else
 <input name="txtBandarWarisSurat_D" size="45" style="text-transform:uppercase;" readonly class="disabled" value="" />
 #end
 
 
            #end

</span>
#end
 </label></td>
                                  </tr>
                                  
                                  
                                        <tr>
                                          <td class="style53" >&nbsp;</td>
                                          <td class="style38" ><div align="right" class="style52">
                                            <div align="left">No Tel (R/P)</div>
                                          </div></td>
                                          <td >:</td>
                                          <td>
                                           <span id="notel_a">
                                          
                                          <input name="txtNoTeleponPentingU" type="text" id="txtNoTeleponPentingU" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponPentingU')" value="$txtNoTeleponPentingU" size="14" maxlength="12" $readmodeR class="$readmode" />
                                          
                                          </span>
                                            <span id="notel_b">
                                             #if($readmode != "disabled")
                                          <input name="txtNoTeleponPenting_D" type="text" id="txtNoTeleponPenting_D" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponPenting')" size="14" maxlength="12" readonly class="disabled" value="$txtNoTeleponPentingU"  />
                                          #end
                                          </span>
                                          
                                          
                                          </td>
                                        </tr>
                                        #if($readmode != "disabled")
                                        <tr id="no_tel_info" >
                                          <td valign="top" class="style53">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"><span class="style41"></span></div></td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top" ><span class="style42 style44"><em><span class="style50">cth: 031234567</span></em></span></td>
                                        </tr>
                                        #end
                                        
                                        
                                        
                                        
                                        
                                        <tr id="no_hp">
                                          <td>&nbsp;</td>
                                          <td class="style38" ><div align="left" class="style52">No Hp</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoHpPenting" type="text" id="txtNoHpPenting" style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoHpPenting" size="14" maxlength="12" $readmodeR class="$readmode"  onkeyup="javascript:validateIC(event,this,this.value,'txtNoHpPenting')"/></td>
                                        </tr >
                                         #if($readmode != "disabled" )
                                        <tr id="no_hp_info" >
                                          <td valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"><span class="style72"></span></div></td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top"><span class="style50">cth: 0121234567</span></td>
                                    </tr>
                                        #end
                                        
                                        
                                        <tr id="no_fax" >
                                          <td>&nbsp;</td>
                                          <td class="style38" ><div align="left" class="style52">No Faks</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td>
                                          <span id="nofax_a">
                                          <input name="txtNoFaxPenting" type="text" id="txtNoFaxPenting" style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoFaxPenting" size="14" maxlength="12" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoFaxPenting')"/>
                                          </span><span id="nofax_b">
                                           #if($readmode != "disabled")
                                          <input name="txtNoFaxPenting_D" type="text" id="txtNoFaxPenting_D" style="text-transform:uppercase;" onblur="text-transform:uppercase;" size="14" maxlength="12" readonly class="disabled" onkeyup="javascript:validateIC(event,this,this.value,'txtNoFaxPenting')"/>
                                          #end
                                          </span>
                                          
                                          </td>
                                        </tr>
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        <tr>
                                          <td valign="top" class="style53">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="right" class="style52">
                                            <div align="left">Catatan</div>
                                          </div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtCatatanPentingU" cols="31"  rows="3" $readmodeR class="$readmode" id="txtCatatanPentingU"  >$txtCatatanPentingU</textarea>
                                     
                                         
                                          <input type="hidden" name ="txtBandarPentingU_X" id ="txtBandarPentingU_X"  />  
					                      <input type="hidden" name ="txtBandarWarisSurat_X" id ="txtBandarWarisSurat_X"  />  
					
                                          </td>
                                      </tr>
                                      
                                     <tr id="tr_flag_daftar"  style="display:none">
                                          <td valign="top"></td>
                                          <td  valign="top">Urusan Kemasukkan Maklumat OB
                                          </td>
                                          <td valign="top">:</td>
                                          <td valign="top">
                                          
                                          
                                          #if($readmode != "disabled" )
                                          
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
                                          value="1" /> Pendaftaran
                                          <br />                                          
                                          <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar2 id="FLAG_DAFTAR" 
                                          value="2" /> Perbicaraan
                                          
                                          
                                          #else
                                          
                                          #set($text_daftar = "")
                                          #if($FLAG_DAFTAR == '1')
                                          #set($text_daftar = "PENDAFTARAN")
                                          #elseif($FLAG_DAFTAR == '2') 
                                          #set($text_daftar = "PERBICARAAN")                                         
                                          #end
                                          
                                          <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />                                          
                                          <input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
                                           
                                          #end
                                          
                                          
                                          </td>
                                          </tr>
                                          
                                          #if($!skrin_online != "yes")                         
                                                    <script>
                                                    document.getElementById('tr_flag_daftar').style.display = "";
                                                    </script>
                                          #end 
                                </table>
                                 
                                </td>
                              </tr>
                               
                            </table>
                                         
                                          </fieldset>
                                          
                                          #if($readmode != "disabled")
                                          <table>
                           

 <tr>
  <td><span class="style5"><span class="style2 style44 style40">Perhatian</span><span class="style44"> : Sila masukkan salah satu nombor MyID (kecuali pada taraf kepentingan adalah Amanah Raya Berhad dan Baitumal) dan pastikan label bertanda <span class="style40">*</span> diisi</span>.</span></td>
  
  </tr>
  
                           </table>
                            #end
                                         
                                         
                                         </td>
                                       </tr>
                                       #end
                                       
                                    
                                       
                                       
                                 #if($nk_tambah_penting=="yes")
                                       <tr>
                            <td width="100%">
                            
                            <fieldset>
                            <legend>MAKLUMAT ORANG BERKEPENTINGAN</legend>
                            
                            <table width="100%" border="0">
                              <tr>
                                <td width="50%" valign="top"><table width="100%">
                               <input type="hidden" name="txtIdSimatiPenting" value="$id_Simati" >        
                                  <tr>
                                     <td class="style38" valign="top" ><div align="center" class="style40">*</div></td>
                                          <td class="style38"><div align="right" class="style41 style38">
                                            <div align="left"><span class="style38">Taraf Kepentingan</span></div>
                                    </div></td>
                                         
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                         #foreach($listtar in $listtaraf)
                                 
                                 #if($socTarafKepentinganPenting==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
	                              
	                         
                                   
                                 #end    
	                               #end
                                         
                                       
	                                 <td>
	                            
	                              #if($socTarafKepentinganPenting!="" && $socTarafKepentinganPenting!="0")
                                 
	                              <select name="socTarafKepentinganPenting" class="largeselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_taraf();pilih_amanah();default_amanah()">
                                    <option value="$socTarafKepentinganPenting" style="text-transform:uppercase;" onblur="uppercase()">$tarafkepentinganP - $tarafkepentinganketeranganP</option>
	                                
                                  #foreach($listtar in $listtaraf)
                                 
                                  #if($socTarafKepentinganPenting!=$listtar.id_Tarafkptg)
                                  #if($listtar.id_Tarafkptg!=1 && $listtar.id_Tarafkptg!=2 && $listtar.id_Tarafkptg!=14)
                                    
	                               
	                                <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
	                                
                                   
                                   #end
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
	                                </select>
                                  #else
                                  <select name="socTarafKepentinganPenting" class="largeselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_taraf();pilih_amanah();default_amanah()">
                                   <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Taraf Kepentingan</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                #if($listtar.id_Tarafkptg!=1 && $listtar.id_Tarafkptg!=2 && $listtar.id_Tarafkptg!=14)
	                               <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                   
                                 #end
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end                                      </tr>
                                  
                                  
                              
                              
                                  
                      <input name="jenis_pej" id="jenis_pej" type="hidden"  value="$!jenis_pej" />                                
         <tr id="amanah" >       
          <td >&nbsp;</td>
          <td ><span class="style38">Amanah raya</span></td>
          <td >:</td>
          <td >      
     
          <select name="jenis_pej1" id="jenis_pej1" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya()" >         
          #if($!jenis_pej == "" || $!jenis_pej == "0") 
          <option value="">SILA PILIH</option>   
                 
           #else
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                  #foreach($listJ in $listMaklumatMahkamahJ)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '9' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                  <!--<option value="">SILA PILIH </option>  -->
                                  #end                                       
          </select>          </td>
        </tr>     
 
 
 
 
 <tr id="baitulmal" >
    
        
          <td >&nbsp;</td>
          <td ><span class="style38">Baitulmal</span></td>
          <td >:</td>
          <td >      
     
          <select name="jenis_pej2" id="jenis_pej2" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya()" >         
          #if($!jenis_pej == "" || $!jenis_pej == "0") 
          <option value="">SILA PILIH</option>   
                 
           #else
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                  #foreach($listJ in $listMaklumatMahkamahJ)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '61' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                  <!-- <option value="">SILA PILIH</option> -->
                                  #end                                       
          </select>          </td>
        </tr>    
        
        <tr id="Insolvensi">
        <td >&nbsp;</td>
            <td class="style38 style38" >Jabatan Insolvensi</td>
            <td >:</td>
            <td >
            <select name="jenis_pej3" id="jenis_pej3" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya()" >         
          #if($!jenis_pej == "" || $!jenis_pej == "0") 
          <option value="">SILA PILIH</option>   
                 
           #else
           #foreach($listJ in $listMaklumatInsolvensi)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                  #foreach($listJ in $listMaklumatInsolvensi)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '141782' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                  <!--<option value="">SILA PILIH </option>  -->
                                  #end                                       
          </select>          </td>
        </tr>
        
        
          <tr id="j_pemohon"  >
            <td >&nbsp;</td>
            <td class="style38 style38" >Jenis Pemohon</td>
            <td >:</td>
            <td >
       
       
            <span id="jenis_pemohon_drop"  >
           
            
<select name="jenis_pemohon"  class="autoselect" $readmode id="jenis_pemohon" style="text-transform:uppercase;" onBlur="uppercase()" onChange="pilih_taraf()" >        
								   #if($jenis_pemohon=="1")  
                                      <option value="1">01-Agensi</option>
                                      <option value="2">02-Individu</option>
                                   #elseif($jenis_pemohon=="2")
                                      <option value="2">02-Individu</option>
                                      <option value="1">01-Agensi</option>
	                               #else 
                                        <option value="2">02-Individu</option>
                                        <option value="1">01-Agensi</option>
	                               #end
                                    
                                  
                                    
                                     
                                      
                                    </select>
                                    </span>
                                    
                                    <span id="jenis_pemohon_dis" >
                                    <input type="text" name="jenis_pemohon_display"  id="jenis_pemohon_display" readonly class="disabled" >    
                                    </span>                </td>
          </tr>
                                  <tr id="kp_baru">
                                  <td valign="top" class="style53">#if($readmode != "disabled")*#end</td>
                                  <td width="28%" class="style38" ><div align="left" class="style41 style38"><span class="style38">MyID Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><label>
                                   
                                     <input name="txtNoKPBaru1Penting" id="txtNoKPBaru1Penting" style="width: 50px;" type="text" $readmode size="7" maxlength="6" value="$txtNoKPBaru1Penting" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Penting')" onBlur="calculateTarikhLahirOB();"/> <!--onBlur="getAgeByIC(this,this.value,'txtUmurPenting');getStatusByIC(this,this.value,'socStatusOB');getDOB(this.value)"/-->
                                     - 
                                     <input name="txtNoKPBaru2Penting" id="txtNoKPBaru2Penting" style="width: 20px;" type="text" $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')" value="$txtNoKPBaru2Penting"/>
                                     - 
                                     <input name="txtNoKPBaru3Penting" id="txtNoKPBaru3Penting"  style="width: 40px;" type="text" $readmodesize="5" maxlength="4"  onblur="jantinaic1()" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')" value="$txtNoKPBaru3Penting"/>
                                    #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                                    
                                     <a href="http://www.jpn.gov.my" target="_blank" class="style45" > www.jpn.gov.my</a>
                                     
                                     #end
                                      </label></td>
                                </tr>
                                <tr id="kp_lama">
                                  <td ><div align="center"><span class="style40"></span></div></td>
                                  <td class="style38" ><div align="left" class="style41 style38"><span class="style38">MyID Lama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting" type="text" id="textfield4" size="15" maxlength="15" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoKPLamaPenting"/>
                                  </label></td>
                                </tr>
                                <tr id="kp_jenis">
                                  <td ><div align="center"><span class="style40"></span></div></td>
                                  <td class="style38" ><div align="left" class="style41 style38">Jenis MyID Lain</div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                                  
                                   
									
									 <select name="socJenisKPLainPenting"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="kplain1X(this.value)" onchange="kplain1(this.value)" >
								   #if($socJenisKPLainPenting=="5")
	                                 
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                       <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                       <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                      
	                              
	                               #elseif($socJenisKPLainPenting=="6")
	                                
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                       <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                      
	                              
								   #elseif($socJenisKPLainPenting=="4")
	                               
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                       <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                      
                                      #elseif($socJenisKPLainPenting=="7")
	                                <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                      
	                               #else
	                                 
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                       <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      
	                               #end
                                    
                                  
                                    </select>
									 <label></label></td>
                                </tr>
                                
                                #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($socJenisKPLainPenting != 0 && $socJenisKPLainPenting != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
                                
                                
                                  <tr id="kp_lain">
                                    <td><div align="center"><span class="style40"></span></div></td>
                                    <td class="style38"><div align="left" class="style41 style38"><span class="style38"> MyID Lain</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><span class="style36">
                                      <input name="txtNoKPLainPenting" type="text" id="textfield5" size="15" maxlength="25" $readmodekp style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoKPLainPenting" />
                                    </span></td>
                                  </tr>
                                  <tr>
                                    <td class="style38" valign="top" ><div align="center" class="style40">*</div></td>
                                    <td class="style38"><div align="left" class="style41 style38"><span class="style38">Nama OB</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                    <span id="nama_1" >
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtNamaOBPenting" size="45" maxlength="150" $readmode />
                                      </span>
                                       <span id="nama_2" >
                                      <input name="txtNamaOBPenting_D" type="text" id="txtNamaOBPenting_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtNamaOBPenting" size="45" maxlength="150" readonly class="disabled" />
                                      </span>
                                    </label>
                                                                    <span id="add_alamat_raya" > </span>  </td>
                                  </tr>
                                  
                                   
                                  <tr id="jantina">
                                    <td><div align="center"><span class="style40"></span></div></td>
                                  <td class="style38"><div align="left" class="style41 style38"><span class="style38">Jantina</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                    <select name="socJantinaPenting" id="select2" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                    
                                   #if($socJantinaPenting=="1")
	                               
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                      
	                               #elseif($socJantinaPenting=="1")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                      
	                               #else
	                               
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jantina</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                      
	                               #end
                                     
                                    
                                    </select>
                                    </label></td>
                                </tr> 
                                
                                 <tr id="agama">
                                   <td><div align="center"><span class="style40"></span></div></td>
                                  <td class="style38"><div align="left" class="style41 style38"><span class="style38">Agama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                    
                                    
                                    <select name="socAgamaPenting" id="select3" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($socAgamaPenting=="1")
	                               
	                               
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      
	                               #elseif($socAgamaPenting=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      
	                               #else
	                               
                                      
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      
	                               #end
                                      
                                    
                                    </select>
                                     </label></td>
                                </tr>
                                      
                                         <tr id="warga">
                                           <td><div align="center"><span class="style40"></span></div></td>
                                  <td class="style38"><div align="left" class="style41 style38"><span class="style38">Warganegara</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    
                                   
                                    <select name="socWarganegaraPenting" id="select4" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($socWarganegaraPenting=="1")
	                               
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #elseif($socWarganegaraPenting=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #elseif($socWarganegaraPenting=="3")
	                               
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      
                                   #else
                                   
                                      
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #end
                                  
                                    
                                    </select>
                                     </label></td>
                                </tr> 
                                <tr id="dob">
                                  <td ><div align="center"><span class="style40"></span></div></td>
                                  <td class="style38" ><div align="right" class="style52 style38">
                                    <div align="left">Tarikh Lahir</div>
                                  </div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td>
                                  <input name="txdTarikhLahirPenting" type="text" id="txdTarikhLahirPenting" size="10" maxlength="10" $readmode  onblur="trans_date(this.value);getAgebyDob(this,this.value,'txtUmurPenting');defineStatusWarisByUmurOB();" value="$txdTarikhLahirPenting" />
                                   <a href="javascript:displayDatePicker('txdTarikhLahirPenting',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> </td>
                                </tr>
                                <tr id="umur">
                                  <td><div align="center"><span class="style40"></span></div></td>
                                  <td class="style38"><div align="left" class="style41 style38"><span class="style38">Umur</span></div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><span class="style36">
                                    <input name="txtUmurPenting" type="text" id="txtUmurPenting"  size="3" maxlength="3" $readmode style="text-transform:uppercase;" onblur="Checkumur();getStatusByAge(this,this.value,'socStatusOB')" onKeyUp="javascript:validateIC(event,this,this.value,'txtUmurPenting')"  value="$txtUmurPenting"/>
                                  </span></td>
                                </tr>
                                <tr id="status_ob">
                                    <td valign="top"><div align="center"><span class="style40"></span></div></td>
                                  <td valign="top" class="style38"><div align="left" class="style41 style38"><span class="style38">Status OB</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                            
                                    
                                       <select name="socStatusOB" id="socStatusOB"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="status_ob()">
									
								   #if($socStatusOB=="1")
	                                 
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>       
	                              
	                               #elseif($socStatusOB=="2")
	                                
	                                  <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>                             
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                          <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>     
								   #elseif($socStatusOB=="3")
	                               
	                                 <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                    
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                              <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>        
                                   #elseif($socStatusOB=="4")
	                                    <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                     <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                        
	                               #else
	                                 
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                               #end
                                   </select>                                  </td>
                                </tr>
                               
                                <tr id="no_surat">
                                  <td><div align="center"><span class="style40"></span></div></td>
                                    <td class="style38"><div align="left" class="style41 style38"><span class="style38">No Surat Beranak</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNoSuratBeranakPenting" type="text" id="txtNoSuratBeranakPenting" size="15" maxlength="10" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoSuratBeranakPenting" />
                                    </label></td>
                                  </tr>
                               <tr>
                                    <td width="1%" class="style38" valign="top" ><div align="center" class="style40">*</div></td>
                                    <td class="style38" width="28%"><div align="right" class="style38 style41 style38">
                                      <div align="left">Alamat Tetap</div>
                                    </div></td>
                                    <td width="1%"><div align="right" class="style38">:</div></td>
                                    <td width="70%"><label>
                                    <span id="alamattetap1_a">
                                      <input name="txtAlamatTerakhir1Penting" type="text" id="txtAlamatTerakhir1Penting" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1Penting" size="45" maxlength="150"  $readmode />                                      
                                    </span>
                                       <span id="alamattetap1_b">
                                      <input name="txtAlamatTerakhir1Penting_D" type="text" id="txtAlamatTerakhir1Penting_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1Penting" size="45" maxlength="150" readonly class="disabled" />
                                      </span>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td><div align="center"><span class="style40"></span></div></td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style41"><span class="style38"></span></span></span></div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                      <span id="alamattetap2_a">
                                      <input name="txtAlamatTerakhir2Penting" type="text" id="txtAlamatTerakhir2Penting" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir2Penting" size="45" maxlength="150" $readmode/>
                                      </span>
                                      <span id="alamattetap2_b">
                                      <input name="txtAlamatTerakhir2Penting_D" type="text" id="txtAlamatTerakhir2Penting_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir2Penting" size="45" maxlength="150" readonly class="disabled"/>
                                      </span>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td><div align="center"><span class="style40"></span></div></td>
                                    <td class="style38"><div align="left"><span class="style7"><span class="style41"><span class="style38"></span></span></span></div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td>
                                    <span id="alamattetap3_a">
                                    <input name="txtAlamatTerakhir3Penting" type="text" id="txtAlamatTerakhir3Penting" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir3Penting" size="45" maxlength="150" $readmode/>
                                    </span>
                                     <span id="alamattetap3_b">
                                    <input name="txtAlamatTerakhir3Penting_D" type="text" id="txtAlamatTerakhir3Penting_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir3Penting" size="45" maxlength="150" readonly class="disabled"/>
                                    </span>                                    </td>
                                  </tr>
                                  <tr>
                                    <td valign="top"><div align="right" class="style38 style40">
                                      <div align="center">*</div>
                                    </div></td>
                                    <td class="style38"><div align="right" class="style52 style38">
                                      <div align="left">Poskod</div>
                                    </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                    <span id="poskodtetap_a" >
                                      <input name="txtPoskodPenting" type="text" id="txtPoskodPenting" style="text-transform:uppercase;" onblur="uppercase()" size="5" maxlength="5" $readmode onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPenting')" value="$txtPoskodPenting"/>
                                      </span>
                                      <span id="poskodtetap_b" >
                                      <input name="txtPoskodPenting_D" type="text" id="txtPoskodPenting_D" style="text-transform:uppercase;" onblur="uppercase()" size="5" maxlength="5" readonly class="disabled" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPenting')" value="$txtPoskodPenting"/>
                                      </span>
                                    </label></td>
                                  </tr>
                                  
                                  <tr>
                                    <td valign="top" ><div align="right" class="style38 style40">
                                      <div align="center">*</div>
                                    </div></td>
                                    <td class="style38"><div align="right" class="style52 style38">
                                      <div align="left">Negeri</div>
                                    </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                   
                                    #foreach($listnegpomo in $listnegeri)
                                    
                                    #if($socNegeriPenting==$listnegpomo.id_Negeri)
                                    
                                    #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                    #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)                    
                                    
                                    
                                    #end 
                                    #end
                                    <td>
                                    
                               <span id="negeritetap_a"   >   
                                 #if($socNegeriPenting!="" && $socNegeriPenting!="0" )
    <select name="socNegeriPenting" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase();getBandarTetap('txtBandarPenting')" onchange="getBandarTetap('txtBandarPenting')">
                           <option value="$socNegeriPenting" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)                                 
                                  #if($socNegeriPenting!=$listnegpomo.id_Negeri)
      <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                  #end    
	                              #end
                                 </select>
                                      #else
    <select name="socNegeriPenting" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase();getBandarTetap('txtBandarPenting')" onchange="getBandarTetap('txtBandarPenting')">
                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option> 
                            #foreach($listnegpomo in $listnegeri)                                 
        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
	                        #end
                            </select>                                  
                               #end                               </span>
                               
                                   <span id="negeritetap_b"   >   
                               
             <input name="socNegeriPenting_D" size="45" style="text-transform:uppercase;" readonly class="disabled" />
                               </span>                                </td>
                                     </tr>
                                  <tr>
                                    <td class="style38" valign="top" >&nbsp;</td>
                                    <td class="style38"><div align="right" class="style52 style38">
                                      <div align="left">Bandar</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                    <span id="bandartetap_a" >
                                    #if($txtBandarPenting == "" || $txtBandarPenting=="0")
                #set($kodb="")
                
                #else
                
                #foreach($listneg in $listBandarTetapbyNegeri)      
                
                
                #if($txtBandarPenting==$listneg.id)                                      
                #set($kodb="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
                #end
                
                
                
              
                
                #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                #if($txtBandarPenting==$listdaerah.id)                                
                #set($listDaerahbyNegeriK=$listdaerah.kod)
                #set($listDaerahbyNegeriN=$listdaerah.nama)
                #end 
                #end
                
                
                #if($disabled=="disabled")
                #set($readmodedaerah="disabled")
                #end
                #if($txtBandarPenting!="" && $txtBandarPenting!="0" )
                
                
                <select name="txtBandarPenting" id="txtBandarPenting" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                  <option value="$txtBandarPenting">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($txtBandarPenting!=$listdaerah.id)
                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                  
                                  #end    
	                               #end
                                  
                
                </select>
#else
<select name="txtBandarPenting" id="txtBandarPenting" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
  <option value="">Sila Pilih Bandar</option>
  
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
	                               #end
                                  
                
</select>
#end</span>

<span id="bandartetap_b"   >   
                               
             <input name="txtBandarPenting_D" size="45" style="text-transform:uppercase;" readonly class="disabled" />
                               </span>

</label></td>
                                  </tr>
                                  
                                </table></td>
                                <td width="50%" valign="top"><table width="100%" border="0">
                                  
                                  <tr id="chk_tr">
                                    <td><div align="center"><span class="style40"></span></div></td>
                                    <td class="style52" ><div align="left"></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                     #if($check_copy == "on")
                                    #set($ch_copy = "checked")
                                    #else
                                    #set($ch_copy = "")
                                    #end
                                        <input type="checkbox" name="copyAlamat" id="copyAlamat" $ch_copy onclick="getBandarTetapCopy('maklumat_pemohon')"  />
                                        
                                        
                                      <span class="style50" >Alamat surat menyurat adalah alamat tetap</span></label></td>
                                  </tr>
                                  <tr>
                                    <td width="1%" class="style38" valign="top" ><div align="center" class="style40">*</div></td>
                                    <td width="29%" class="style38" ><div align="right" class="style52">
                                      <div align="left">Alamat Surat</div>
                                    </div></td>
                                    <td width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%"><label>
                                      <span id="alamatsurat1_a">
                                      <input name="txtAlamatTerakhir1WarisSurat" type="text" id="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1WarisSurat" size="45" maxlength="150"  $readmode/>
                                      </span>
                                        <span id="alamatsurat1_b">
                                      <input name="txtAlamatTerakhir1WarisSurat_D" type="text" id="txtAlamatTerakhir1WarisSurat_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1WarisSurat" size="45" maxlength="150"  readonly class="disabled"/>
                                      </span>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td><div align="center"><span class="style40"></span></div></td>
                                    <td class="style38"><div align="left"><span class="style38"><span class="style38"><span class="style41"></span></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td>
                                     
                                    <label>
                                     <span id="alamatsurat2_a">
                                      <input name="txtAlamatTerakhir2WarisSurat" type="text" id="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir2WarisSurat"   size="45" maxlength="150" $readmode/>
                                   </span>
                                    <span id="alamatsurat2_b">
                                      <input name="txtAlamatTerakhir2WarisSurat_D" type="text" id="txtAlamatTerakhir2WarisSurat_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir2WarisSurat"   size="45" maxlength="150" readonly class="disabled"/>
                                   </span>
                                   
                                    </label>
                                    
                                    </td>
                                  </tr>
                                  <tr>
                                    <td><div align="center"><span class="style40"></span></div></td>
                                    <td class="style38"><div align="left"><span class="style38"><span class="style38"><span class="style41"></span></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td>
                                    
                                     <span id="alamatsurat3_a">
                                    <input name="txtAlamatTerakhir3WarisSurat" type="text" id="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir3WarisSurat"  size="45" maxlength="150" $readmode/>
                                     </span>
                                     
                                     <span id="alamatsurat3_b">
                                    <input name="txtAlamatTerakhir3WarisSurat_D" type="text" id="txtAlamatTerakhir3WarisSurat_D" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir3WarisSurat"  size="45" maxlength="150" readonly class="disabled"/>
                                     </span>
                                     
                                    
                                    </td>
                                  </tr>
                                  <tr>
                                    <td valign="top" ><div align="center" class="style40"><span class="style38">*</span></div></td>
                                    <td class="style38"><div align="right" class="style52">
                                      <div align="left">Poskod</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                     <span id="poskodsurat_a">
                                      <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()"  size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmode value="$txtPoskodWarisSurat"/>
                                      </span>
                                      <span id="poskodsurat_b">
                                      <input name="txtPoskodWarisSurat_D" type="text" id="txtPoskodWarisSurat_D" style="text-transform:uppercase;" onblur="uppercase()"  size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" readonly class="disabled" value="$txtPoskodWarisSurat"/>
                                      </span>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" ><div align="center" class="style40"><span class="style38">*</span></div></td>
                                    <td class="style38"><div align="right" class="style52">
                                      <div align="left">Negeri</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td>
                                     <span id="negerisurat_a"   >   
                                      #foreach($listnegpomo in $listnegeri)
                                    
                                    #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                    
                                    #set($kod = "$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri")
                                    
                                    
                                    
                                    
                                    #end 
                                    #end
                                    #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!=0)
                                      <select name="socNegeriWarisSurat" class="autoselect" id="socNegeriWarisSurat"  onchange="getBandarSurat('txtBandarWarisSurat')" >
                                        <option value="$socNegeriWarisSurat" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                        
                  
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($socNegeriWarisSurat!=$listneg.id_Negeri)
                       
                  
                                        <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                        
                  
                                     
                                  #end    
	                              #end
                                        
                
                                      </select>
#else
<select name="socNegeriWarisSurat" class="autoselect" onchange="getBandarSurat('txtBandarWarisSurat')">
  <option value="" >SILA PILIH NEGERI</option>
  
                  
                                  #foreach($listneg in $listnegeri)
                   
                  
  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
  
                  
                                    
	                               #end
                                        
                
</select>
#end 
</span>
  
             
 <span id="negerisurat_b"   >   
 
 <input name="socNegeriWarisSurat_D" size="45" style="text-transform:uppercase;" readonly class="disabled" />

 </span>

</td>
                                  </tr>
                                  <tr>
                                    <td valign="top" ><div align="center" class="style40"></div></td>
                                    <td class="style38"><div align="right" class="style52"> 
                                      <div align="left">Bandar</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                    
                                    <span id="bandarsurat_a"   >   
                                    
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

</span>
<span id="bandarsurat_b" >
 <input name="txtBandarWarisSurat_D" size="45" style="text-transform:uppercase;" readonly class="disabled" />
            

</span>

 </label></td>
                                  </tr>
                                        
                                        <tr>
                                          <td ><div align="center"><span class="style40"></span></div></td>
                                          <td class="style38" ><div align="right" class="style52">
                                            <div align="left">No Tel (R/P)</div>
                                          </div></td>
                                          <td >:</td>
                                          <td>
                                          <span id="notel_a">
                                          <input name="txtNoTeleponPenting" type="text" id="txtNoTeleponPenting" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponPenting')" size="14" maxlength="12" $readmode value="$txtNoTeleponPenting"  />
                                          </span>
                                          
                                          <span id="notel_b">
                                          <input name="txtNoTeleponPenting_D" type="text" id="txtNoTeleponPenting_D" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponPenting')" size="14" maxlength="12" readonly class="disabled"  />
                                          </span>
                                          
                                          </td>
                                        </tr>
                                        <tr id="no_tel_info" >
                                          <td valign="top"><div align="center"><span class="style40"></span></div></td>
                                          <td valign="top" class="style52"><div align="left"></div></td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top" ><em><span class="style50">cth: 031234567</span></em></td>
                                        </tr>
                                        
                                        <tr id="no_hp">
                                          <td>&nbsp;</td>
                                          <td class="style38" ><div align="left" class="style52">No Hp</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoHpPenting" type="text" id="txtNoHpPenting" style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoHpPenting" size="14" maxlength="12" $readmode  onkeyup="javascript:validateIC(event,this,this.value,'txtNoHpPenting')"/></td>
                                        </tr >
                                         #if($readmode != "disabled" )
                                        <tr id="no_hp_info">
                                          <td valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"><span class="style72"></span></div></td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top"><span class="style50">cth: 0121234567</span></td>
                                    </tr>
                                        #end
                                        <tr id="no_fax">
                                          <td>&nbsp;</td>
                                          <td class="style38" ><div align="left" class="style52">No Faks</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td>
                                          <span id="nofax_a">
                                          <input name="txtNoFaxPenting" type="text" id="txtNoFaxPenting" style="text-transform:uppercase;" onblur="text-transform:uppercase;" value="$txtNoFaxPenting" size="14" maxlength="12" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoFaxPenting')"/>
                                          </span>
                                             <span id="nofax_b">
                                          <input name="txtNoFaxPenting_D" type="text" id="txtNoFaxPenting_D" style="text-transform:uppercase;" onblur="text-transform:uppercase;" size="14" maxlength="12" readonly class="disabled" onkeyup="javascript:validateIC(event,this,this.value,'txtNoFaxPenting')"/>
                                          </span>
                                          
                                          </td>
                                        </tr>
                                        <tr>
                                          <td valign="top"><div align="right" class="style38"><span class="style40"></span></div></td>
                                          <td class="style38" valign="top"><div align="right" class="style52">
                                            <div align="left">Catatan</div>
                                          </div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtCatatanPenting" cols="31"  rows="3"  $readmode >$txtCatatanPenting</textarea></td>
                                      </tr>
                                      
                                      <tr id="tr_flag_daftar"  style="display:none">
                                          <td valign="top"></td>
                                          <td  valign="top">Urusan Kemasukkan Maklumat OB
                                          </td>
                                          <td valign="top">:</td>
                                          <td valign="top">
                                          
                                          
                                          #if($readmode != "disabled" )
                                          
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
                                          value="1" /> Pendaftaran
                                          <br />                                          
                                          <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar2 id="FLAG_DAFTAR" 
                                          value="2" /> Perbicaraan
                                          
                                          
                                          #else
                                          
                                          #set($text_daftar = "")
                                          #if($FLAG_DAFTAR == '1')
                                          #set($text_daftar = "PENDAFTARAN")
                                          #elseif($FLAG_DAFTAR == '2') 
                                          #set($text_daftar = "PERBICARAAN")                                         
                                          #end
                                          
                                          <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />                                          
                                          <input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
                                           
                                          #end
                                          
                                          
                                          </td>
                                          </tr>
                                          
                                          #if($!skrin_online != "yes")                         
                                                    <script>
                                                    document.getElementById('tr_flag_daftar').style.display = "";
                                                    </script>
                                          #end 
                                </table></td>
                              </tr>
                            
                              
                            </table>
                            </fieldset>   
                            <table>
                           

 <tr>
  <td><span class="style5"><span class="style2 style44 style40">Perhatian</span><span class="style44"> : Sila masukkan salah satu nombor MyID  (kecuali pada taraf kepentingan adalah Amanah Raya Berhad dan Baitumal) dan pastikan label bertanda <span class="style40">*</span> diisi</span>.</span> </td>
  
  </tr>
  
                           </table>
                            
                            
                                                     </td>
                          </tr>
                                       
                               #end        
                                       
            #if($nk_button_penting=="yes") 
                         
                          <tr>
                            <td>  <table width="100%" border="0" align="center">
                                  <tr>
                                  <td align="center">
                                   #if($open_button_online == "yes") 
                                  #if($buttonpenting=="tambah")
                              
                                  <input type="button" name="tambahpenting" id="tambahpenting2" value="Simpan" onclick="setSelected(0,3,0,0);tambah_simpan_penting()"/>								
                              <input type="button" name="batalpenting" id="cmdSimpan3" value="Batal" onclick="setSelected(0,3,0,0);cancelwaris()"/>                                 
                       #else 
                       
             #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
          <!--  <input type="button" name="tambahpenting" id="tambahpenting3" value="$buttonpenting" onclick="setSelected(0,3,0,0);tambah_penting()"/> -->
             #end
              #if($boleh_kemaskini == "yes")
              #end
                  #if($!skrin_online != "yes")
                 
                   <input type="button" name="tambahpenting" id="tambahpenting" value="$buttonpenting" onclick="setSelected(0,3,0,0);tambah_penting()"/> 
                                                        #if($flag_kemaskini_selesai != "yes")
                                                        <script>
                                                        document.getElementById('tambahpenting').style.display = "none";
                                                        </script>
                                                        #end  
                   #else
                   <input type="button" name="tambahpenting" id="tambahpenting3" value="Simpan" onclick="setSelected(0,3,0,0);tambah_penting()"/> 
                   
                   #end
                                  
                                  
                                  
                                  
                                    #if($buttonpenting=="Simpan")
                                     #if($!skrin_online != "yes") 
                                    <input type="button" name="batalpentingupdate" id="cmdSimpan3" value="Batal" onclick="setSelected(0,3,0,0);batalpenting()"/>
                                    #end
                                      #end
                                      
#if($buttonpenting == "Kemaskini" )

#if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
<!-- <input type="button" name="hapuspenting" id="hapuspenting" value="Hapus"  onclick="setSelected(0,3,0,0);hapus_penting()" /> -->
#end
#if($boleh_kemaskini == "yes")
#end
<input type="button" name="hapuspenting" id="hapuspenting" value="Hapus"  onclick="setSelected(0,3,0,0);hapus_penting()" />
 #if($flag_kemaskini_selesai != "yes")
                                                        <script>
                                                        document.getElementById('hapuspenting').style.display = "none";
                                                        </script>
                                                        #end  



                                    #end
                                 #end
                                     
                                    <input type="submit" name="cmdKembali3" id="cmdKembali3" value="Kembali"  onclick="setSelected(0,3,0,0);PentingView()" />
                                   #end 
                                    </td>
                                  </tr>
                                  
                                </table></td>
                          </tr>
                          
                           
                            
                            
                        #end 
                            
                            
                          <tr>
                            <td>
                  <input type="hidden" name="idOb" value="" />
                            
                            <fieldset>
                              <legend>SENARAI ORANG BERKEPENTINGAN</legend>
                            
                         
                              <table width="100%" >
                                <tr>
                                  <td width="100%">
                                 
                                  #if($tambah_ob_button == "yes")
                                 
                              #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
							<!--	 <label>		
                                  <input type="submit" name="cmdPapar" id="cmdPapar" value="Tambah"  onclick="setSelected(0,3,0,0);tambah_penting_baru()"/>
                                  </label>
                                  -->
                                  #end
                                  #if($boleh_kemaskini == "yes")
                                  #end
                                   #if($open_button_online == "yes") 
                                 		
                                    
                                  <input type="submit" name="cmdPapar" id="cmdPapar" value="Tambah"  onclick="setSelected(0,3,0,0);tambah_penting_baru()"/>
                                   
								   #if($flag_kemaskini_selesai != "yes")
								   <script>
                                                        document.getElementById('cmdPapar').style.display = "none";
                                                        </script>
                                                        #end  
                                  #end
                                  
                                  
                                  #end
                                  #if($kembali_ob_button == "yes")
                                    <label>
                                    <!--
                                    <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali"  onclick="kembali_simati()" />
                                    -->
                                  </label>
                                  #end
                                  </td>
                                </tr>
                                
                               
          
      
                                
                                
                                <tr>
                                  <td>
                                 
                                  #if($listPenting.size()!=0)
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="40%"><div align="left">NAMA OB</div></td>
                                      <td width="25%"><div align="center">MyID BARU</div></td>
                                      <td width="30%"><div align="center">STATUS OB</div></td>
                                    </tr>
                                    #set($peno=0)
                                  
                                      #foreach($listpenting in $listPenting)
                                      
                                      #if($listpenting.taraf!=1 && $listpenting.taraf!=2 && $listpenting.taraf!=14)
                                     
                                        #set($peno=$peno+1)
          
         							 #if($peno%2!=0)
                                    <tr bgcolor="white">
                                    <td class="row1"><div align="center" class="style41" style="text-transform:uppercase;" onblur="uppercase()">$peno</div></td>
                                      <td class="row1" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div align="left" class="style42" style="text-transform:uppercase;" onblur="uppercase()"> $listpenting.nama_Ob</div>   
                                    <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row1" style="text-transform:uppercase;" onblur="uppercase()" ><div align="center" >$listpenting.nokpbaru</div></td>
                                      
                                         #if($listpenting.status_Ob=="1")
                                         #set($stat="Dewasa/Waras")
                                         #end
                                         #if($listpenting.status_Ob=="2")
                                         #set($stat="Belum Dewasa")
                                         #end
                                         #if($listpenting.status_Ob=="3")
                                         #set($stat="Hilang")
                                         #end
                                         #if($listpenting.status_Ob=="4")
                                         #set($stat="Tidak Sempurna Akal")
                                         #end
                                         #if($listpenting.status_Ob=="" || $listpenting.status_Ob=="0")
                                         #set($stat="")
                                         #end
                                         
	                                 
                                    
                                      
                                      
                                      <td class="row1" style="text-transform:uppercase;" onblur="uppercase()"><div align="center">$stat</div></td>
                                    </tr>
                                    #else
                                        <tr >
                                  
                                   <td class="row2"><div align="center" class="style41" style="text-transform:uppercase;" onblur="uppercase()">$peno</div></td>
                                      <td class="row2" style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div align="left" class="style42" style="text-transform:uppercase;" onblur="uppercase()"> $listpenting.nama_Ob</div>   
                                       <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row2" style="text-transform:uppercase;" onblur="uppercase()"><div align="center">$listpenting.nokpbaru</div></td>
                                        #if($listpenting.status_Ob=="1")
                                         #set($statu="Dewasa/Waras")
                                         #end
                                         #if($listpenting.status_Ob=="2")
                                         #set($statu="Belum Dewasa")
                                         #end
                                         #if($listpenting.status_Ob=="3")
                                         #set($statu="Hilang")
                                         #end
                                         #if($listpenting.status_Ob=="4")
                                         #set($statu="Tidak Sempurna Akal")
                                         #end
                                         #if($listpenting.status_Ob=="" || $listpenting.status_Ob=="0")
                                         #set($statu="")
                                         #end
                                         
                                      <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$statu</div></td>
                                    </tr>
                                    
                                    #end
                                    
                                   
                                    
                                    
                                    #end
                                    
                                   
                                    
                                    #end
                                    
                                  </table>
                                   #if($peno==0)
                                     <table width="100%">
                                    <tr>
                                     <div align="left" >Tiada Rekod</div>
                                    </tr>
                                      </table>
                                    #end
                                  #else
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="40%"><div align="center">NAMA OB</div></td>
                                      <td width="25%"><div align="center">MyID BARU</div></td>
                                      <td width="30%"><div align="center">STATUS OB</div></td>
                                    </tr>
                                   </table> 
                                    <table width="100%" bgcolor="#FFFFFF">
                                    <tr>
                                      <td width="100%"><div align="left">Tiada Rekod</div>
                                      </td>
                                      </tr>
                                   </table> 
                                     
                                  #end                                 </td>
                                </tr>
                              </table>  
                              </fieldset>                            </td>
                          </tr>
                            <tr>
                <td>
                <p align="right">
                #if($!skrin_online != "yes")CL - 01 - 72#end</p>
                </td>
                </tr>
                    </table>
            
            </div>
            
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
          
          </div>
        </div>
      </div>
      
     
      <div class="TabbedPanelsContentVisible">
        <div id="TabbedPanels4" class="TabbedPanels">
         
          <div class="TabbedPanelsContentGroup">          
          </div>
          
        </div>
      </div>
      <div class="TabbedPanelsContentVisible"></div>
      <div class="TabbedPanelsContentVisible"></div>
      
      
    </div>
  </div>    </td>
  </tr>
</table>
#parse("app/ppk/paging_sek8.jsp") 
#parse("app/ppk/headerppk_script.jsp")
</form>

<script>
<!-- ORNG KEPENTINGAN -->
function kemaskini_penting(){
	document.f1.mode.value = "kemaskini_penting";
	document.f1.command.value = "Penting";
	document.f1.action = "";
	document.f1.submit();
}


function simpan_penting(){
	document.f1.mode.value = "simpan_penting";
	document.f1.command.value = "Penting";
	document.f1.action = "";	
	document.f1.submit();	
}

function tambah_penting_baru(){

    document.f1.mode.value = "tambah_penting_baru";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.submit();

}

function hapus_penting(){
if( document.f1.id_Pemohon.value != "" && document.f1.id_Pemohon.value != "0")
{
alert("Orang berkepenting ini adalah seorang pemohon, maklumat orang berkepenting ini tidak dapat dihapuskan. Sebarang perubahan hendaklah dilakukan di tab pemohon!");
		//txtNoKPLainPenting.focus();
		return;
		
}
else
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.f1.mode.value = "hapus_penting";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.submit();
	}
	else
	{
	return;
	}
	
	
}	
}
function batalpenting(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.f1.mode.value = "GetPenting";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.simpanmode.value = "kemaskinipenting";
	document.f1.submit();
	}
}
function tambah_simpan_penting(){


    var negeri_code = document.f1.txtNoKPBaru2Penting.value;
var dob_code = document.f1.txtNoKPBaru1Penting.value;
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



    var dat1=document.f1.txdTarikhLahirPenting
    var str1  = document.getElementById("txdTarikhLahirPenting").value;
    var str2  = document.getElementById("txdTarikhMohon").value;
   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10)-1;
    var yr2   = parseInt(str2.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);
    var date2 = new Date(yr2, mon2, dt2);



	
	 if (document.f1.socTarafKepentinganPenting.value == "6" && document.f1.jenis_pej.value == "") {
		alert("Sila pilih Amanah Raya");
		document.f1.jenis_pej.focus();
	}
	
	else if (document.f1.txtNamaOBPenting.value=="")
 {
		alert("Sila masukkan nama orang bekepentingan");
		document.f1.txtNamaOBPenting.focus();		 
	}
	
	 
	else if (document.f1.socTarafKepentinganPenting.value=="" || document.f1.socTarafKepentinganPenting.value=="0")
    {
		alert("Sila pilih taraf kepentingan");
		document.f1.socTarafKepentinganPenting.focus();		 
	} 
	
	
	else if ( document.f1.jenis_pemohon.value == "2"  &&( document.f1.socTarafKepentinganPenting.value!="6" && document.f1.socTarafKepentinganPenting.value!="8" )  && document.f1.txtNoKPBaru1Penting.value=="" && document.f1.txtNoKPBaru2Penting.value=="" && document.f1.txtNoKPBaru3Penting.value=="" && document.f1.txtNoKPLamaPenting.value=="" && (document.f1.socJenisKPLainPenting.value=="" || document.f1.socJenisKPLainPenting.value=="0") && document.f1.txtNoKPLainPenting.value=="") {
		alert("Sila masukkan salah satu nombor MyID orang bekepentingan ");
		document.f1.txtNoKPBaru1Penting.focus();
		
		}
		
		else if(document.f1.txtNoKPLamaPenting.value != "" && document.f1.txtNoKPLamaPenting.value != "TDK" && document.f1.txtNoKPLamaPenting.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama orang bekepentingan");
		document.f1.txtNoKPLamaPenting.focus();	
	}
		
		
		
	else if ((document.f1.socJenisKPLainPenting.value!="" && document.f1.socJenisKPLainPenting.value!="0")  && document.f1.txtNoKPLainPenting.value=="" && document.f1.jenis_pemohon.value == "2" )
	 {
	 	alert("Sila masukkan nombor MyID lain orang bekepentingan ");
		document.f1.txtNoKPLainPenting.focus();
		return; 
	 }
	 else if (document.f1.jenis_pemohon.value == "2"  && (document.f1.socJenisKPLainPenting.value=="" && document.f1.txtNoKPLainPenting.value!=""))
	 {
	 	alert("Sila pilih jenis MyID lain orang bekepentingan ");
		document.f1.socJenisKPLainPenting.focus();
		return; 
	 }
	 
	 else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru1Penting.value=="")&& document.f1.jenis_pemohon.value == "2" )
	 {
	 	
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru1Penting.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru2Penting.value=="")&& document.f1.jenis_pemohon.value == "2" )
	 {
	 	
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru2Penting.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru3Penting.value=="") && document.f1.jenis_pemohon.value == "2" )
	 {
	 	alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru3Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru1Penting.value!=""  && document.f1.txtNoKPBaru1Penting.value.length < 6 && document.f1.jenis_pemohon.value == "2"  ) {
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru1Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Penting.value!="" && document.f1.txtNoKPBaru2Penting.value.length < 2 && document.f1.jenis_pemohon.value == "2" ) {
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru2Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Penting.value!="" && document.f1.txtNoKPBaru3Penting.value.length < 4 && document.f1.jenis_pemohon.value == "2" ) {
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru3Penting.focus();
		return; 
	}
	
	else if (document.f1.txtPoskodPenting.value != "" && document.f1.txtPoskodPenting.value.length < 5  && document.f1.jenis_pemohon.value == "2" ) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodPenting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru1Penting.value != "" && isIc(tt)==false  && document.f1.jenis_pemohon.value == "2" ){
		document.f1.txtNoKPBaru1Penting.focus()
		return false
	}

/*

else if (document.f1.jenis_pemohon.value == "2"  && document.f1.txtNoKPBaru2Penting.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaru2Penting.focus()
	return;
	
	}
	*/
	
	else if (dat1.value!="" && isDate(dat1.value)==false && document.f1.jenis_pemohon.value == "2" )
	{
		dat1.focus()
		return false
	}
	
	else if (document.f1.txtPoskodWarisSurat.value != "" && document.f1.txtPoskodWarisSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod untuk alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodWarisSurat.focus();
		return; 
	}
	

	
	 else if ((document.f1.txtAlamatTerakhir1Penting.value == "" || document.f1.txtPoskodPenting.value == "" || document.f1.socNegeriPenting.value == "" || document.f1.socNegeriPenting.value == "0"  ))
	{
	alert("Sila lengkapkan alamat tetap orang berkepentingan");
	document.f1.txtAlamatTerakhir1Penting.focus()
	return;
	}
	 else if ((document.f1.txtAlamatTerakhir1WarisSurat.value == "" || document.f1.txtPoskodWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"  ))
	{
	alert("Sila lengkapkan alamat surat menyurat orang berkepentingan");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	else if(date2 < date1 && document.f1.jenis_pemohon.value == "2" )
   {
      alert("Sila pastikan tarikh lahir orang berkepentingan tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirPenting.focus();
   } 
	
	else{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true)
		 {
		
	document.f1.mode.value = "tambah_penting";
	document.f1.command.value = "Penting";
	document.f1.action = "";
	document.f1.submit();
		}
	}
	

		
}

function tambah_penting(){


var negeri_code = document.f1.txtNoKPBaru2PentingU.value;
var dob_code = document.f1.txtNoKPBaru1PentingU.value;
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



    var dat1=document.f1.txdTarikhLahirPentingU

    var str1  = document.getElementById("txdTarikhLahirPentingU").value;
    var str2  = document.getElementById("txdTarikhMohon").value;
   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10);
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10);
    var yr2   = parseInt(str2.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);
    var date2 = new Date(yr2, mon2, dt2);


	if( document.f1.tambahpenting.value == "Simpan" ) 
	{
   
	


	 if (document.f1.socTarafKepentinganPentingU.value == "6" && document.f1.jenis_pej.value == "") {
		alert("Sila pilih Amanah Raya");
		document.f1.jenis_pej.focus();
	}
	else if (document.f1.txtNamaOBPentingU.value=="")
 {
		alert("Sila masukkan nama orang bekepentingan");
		document.f1.txtNamaOBPentingU.focus();		 
	} 
	
	else if (document.f1.socTarafKepentinganPentingU.value=="" || document.f1.socTarafKepentinganPentingU.value=="0")
    {
		alert("Sila pilih taraf kepentingan");
		document.f1.socTarafKepentinganPentingU.focus();		 
	} 
	
	else if (document.f1.jenis_pemohon.value == "2"  && ( document.f1.socTarafKepentinganPentingU.value!="6" && document.f1.socTarafKepentinganPentingU.value!="8" )  && document.f1.txtNoKPBaru1PentingU.value=="" && document.f1.txtNoKPBaru2PentingU.value=="" && document.f1.txtNoKPBaru3PentingU.value=="" && document.f1.txtNoKPLamaPentingU.value=="" && (document.f1.socJenisKPLainPentingU.value=="" || document.f1.socJenisKPLainPentingU.value=="0") && document.f1.txtNoKPLainPentingU.value=="") {
		alert("Sila masukkan salah satu nombor MyID orang bekepentingan ");
		document.f1.txtNoKPBaru1PentingU.focus();
		
		}
		
		else if(document.f1.txtNoKPLamaPentingU.value != "" && document.f1.txtNoKPLamaPentingU.value != "TDK" && document.f1.txtNoKPLamaPentingU.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama orang bekepentingan");
		document.f1.txtNoKPLamaPentingU.focus();	
	}
		
		
	else if (document.f1.jenis_pemohon.value == "2" && (document.f1.socJenisKPLainPentingU.value!="" && document.f1.socJenisKPLainPentingU.value!="0" && document.f1.txtNoKPLainPentingU.value==""))
	 {
	 	alert("Sila masukkan nombor MyID lain orang bekepentingan ");
		document.f1.txtNoKPLainPentingU.focus();
		return; 
	 }
	 else if (document.f1.jenis_pemohon.value == "2"  &&(document.f1.socJenisKPLainPentingU.value=="" || document.f1.socJenisKPLainPentingU.value=="0") && document.f1.txtNoKPLainPentingU.value!="")
	 {
	 	alert("Sila pilih jenis MyID lain orang bekepentingan ");
		document.f1.socJenisKPLainPentingU.focus();
		return; 
	 }
	 
	 else if (document.f1.jenis_pemohon.value == "2"  && (document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru1PentingU.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru1PentingU.focus();
		return; 
	}
	else if (document.f1.jenis_pemohon.value == "2"  && (document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru2PentingU.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru2PentingU.focus();
		return; 
	}
	
	
	
	
	else if (document.f1.jenis_pemohon.value == "2"  && (document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru3PentingU.value==""))
	 {
	 	alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru3PentingU.focus();
		return; 
	}
	else if (document.f1.jenis_pemohon.value == "2"  && document.f1.txtNoKPBaru1PentingU.value!=""  && document.f1.txtNoKPBaru1PentingU.value.length < 6 ) {
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru1PentingU.focus();
		return; 
	}
	else if ( document.f1.jenis_pemohon.value == "2"  && document.f1.txtNoKPBaru2PentingU.value!="" && document.f1.txtNoKPBaru2PentingU.value.length < 2 ) {
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru2PentingU.focus();
		return; 
	}
	else if (document.f1.jenis_pemohon.value == "2"  && document.f1.txtNoKPBaru3PentingU.value!="" && document.f1.txtNoKPBaru3PentingU.value.length < 4) {
		alert("Sila masukkan nombor MyID orang bekepentingan sepenuhnya");
		document.f1.txtNoKPBaru3PentingU.focus();
		return; 
	}
	
	else if (document.f1.txtPoskodPentingU.value != "" && document.f1.txtPoskodPentingU.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodPentingU.focus();
		return; 
	}
	
	else if (document.f1.jenis_pemohon.value == "2"  && document.f1.txtNoKPBaru1PentingU .value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1PentingU .focus()
		return false
	}

/*

else if (document.f1.jenis_pemohon.value == "2"  && document.f1.txtNoKPBaru2PentingU.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaru2PentingU .focus()
	return;
	
	}
	*/
	
	else if (dat1.value!="" && isDate(dat1.value)==false && document.f1.jenis_pemohon.value == "2" )
	{
		document.f1.txdTarikhLahirPentingU.focus()
		return false
	}
	
	 else if(date2 < date1 && document.f1.jenis_pemohon.value == "2" )
   {
      alert("Sila pastikan tarikh lahir orang berkepentingan tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhLahirPentingU.focus();
   } 
	
	else if (document.f1.txtPoskodWarisSurat.value != "" && document.f1.txtPoskodWarisSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod untuk alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodWarisSurat.focus();
		return; 
	}
	

	
	
	else if ((document.f1.txtAlamatTerakhir1PentingU.value == "" || document.f1.txtPoskodPentingU.value == "" || document.f1.socNegeriPentingU.value == "" || document.f1.socNegeriPentingU.value == "0"))
	{
	alert("Sila lengkapkan alamat tetap orang berkepentingan");
	document.f1.txtAlamatTerakhir1PentingU.focus()
	return;
	}
	
	
	 else if ((document.f1.txtAlamatTerakhir1WarisSurat.value == "" || document.f1.txtPoskodWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))
	{
	alert("Sila lengkapkan alamat surat menyurat orang berkepentingan");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	
	else{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true)
		 {
		
	
   	document.f1.mode.value = "simpan_penting";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.submit();
		}
		else
		{return;}
	}
	

   
   
	
	}

	if( document.f1.tambahpenting.value == "Kemaskini" ) 
	{
	document.f1.mode.value = "GetPenting";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.simpanmode.value = "kemaskinipenting";
	document.f1.submit();
	
	}
}

function edit_item(idOb) 
{

document.f1.action = "";
	document.f1.mode.value = "GetPenting";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "getpenting";
	document.f1.txtIdOb.value = idOb;
	document.f1.submit();
}


<!-- TABS -->
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
function CheckumurU() 
{
	if (document.f1.txtUmurPentingU.value != "" && document.f1.txtUmurPentingU.value>100) {
		alert("Adakah anda pasti orang berkepentingan simati adalah "+document.f1.txtUmurPentingU.value+" tahun?");
		txtUmurPentingU.focus();
		return; 
	}
}
function Checkumur() 
{
	if (document.f1.txtUmurPenting.value != "" && document.f1.txtUmurPenting.value>100) {
		alert("Adakah anda pasti orang berkepentingan simati adalah "+document.f1.txtUmurPenting.value+" tahun?");
		txtUmurPenting.focus();
		return; 
	}
}
	
function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
 document.f1.mode.value = "tambah_penting_baru";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.submit();
}
}


function jantinaic1()
{
var ch=document.f1.txtNoKPBaru3Penting.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaPenting.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaPenting.value = 1 ;

}

return;
}

function jantinaic2()
{
var ch=document.f1.txtNoKPBaru3PentingU.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaPentingU.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaPentingU.value = 1 ;

}

return;
}

function kplain1(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainPenting.disabled = "";
document.f1.txtNoKPLainPenting.value = "";
//document.f1.txtNoKPLainPenting.focus();
return;
}
else
{
document.f1.txtNoKPLainPenting.disabled = "disabled";
document.f1.txtNoKPLainPenting.value = "";
return;
}
}

function kplain1X(val)
{
if(val!="0" && val!="")
{

document.f1.txtNoKPLainPenting.focus();
return;
}

}

function kplain2(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainPentingU.disabled = "";
document.f1.txtNoKPLainPentingU.value = "";
//document.f1.txtNoKPLainPentingU.focus();
return;
}
else
{
document.f1.txtNoKPLainPentingU.disabled = "disabled";
document.f1.txtNoKPLainPentingU.value = "";
return;
}
}

function kplain2X(val)
{
if(val!="0" && val!="")
{

document.f1.txtNoKPLainPentingU.focus();
return;
}

}


</script>
<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
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

function ValidateForm1(){
	var dt=document.f1.txdTarikhLahirPentingU
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }


</script>

<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
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

function ValidateForm(){
	var dt=document.f1.txdTarikhLahirPenting
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
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

document.f1.txdTarikhLahirPenting.value=dob;
}


}



function getDOBU(kp){


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

document.f1.txdTarikhLahirPentingU.value=dob;
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


document.f1.txdTarikhLahirPenting.value = trans;

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


document.f1.txdTarikhLahirPentingU.value = trans;

}
else
{
return;
}

}




function copycopyAdd()
{

 

var a1 = document.f1.txtAlamatTerakhir1WarisSurat.value;
var a2 = document.f1.txtAlamatTerakhir2WarisSurat.value;
var a3 = document.f1.txtAlamatTerakhir3WarisSurat.value;
var p1 = document.f1.txtPoskodWarisSurat.value;
var b1 = document.f1.txtBandarWarisSurat.value;
var n1 = document.f1.socNegeriWarisSurat.value;

if(document.f1.copy.checked == true)
{


document.f1.txtAlamatTerakhir1Penting.value = a1;
document.f1.txtAlamatTerakhir2Penting.value = a2;
document.f1.txtAlamatTerakhir3Penting.value = a3;
document.f1.txtPoskodPenting.value = p1;
document.f1.txtBandarPenting.value = b1;
document.f1.socNegeriPenting.value = n1;

}

if(document.f1.copy.checked == false)
{


document.f1.txtAlamatTerakhir1Penting.value = "";
document.f1.txtAlamatTerakhir2Penting.value = "";
document.f1.txtAlamatTerakhir3Penting.value = "";
document.f1.txtPoskodPenting.value = "";
document.f1.txtBandarPenting.value = "";
document.f1.socNegeriPenting.value = "0";

}

}








function copycopyUpdate()
{

 

var a1 = document.f1.txtAlamatTerakhir1WarisSurat.value;
var a2 = document.f1.txtAlamatTerakhir2WarisSurat.value;
var a3 = document.f1.txtAlamatTerakhir3WarisSurat.value;
var p1 = document.f1.txtPoskodWarisSurat.value;
var b1 = document.f1.txtBandarWarisSurat.value;
var n1 = document.f1.socNegeriWarisSurat.value;

if(document.f1.copy.checked == true)
{


document.f1.txtAlamatTerakhir1PentingU.value = a1;
document.f1.txtAlamatTerakhir2PentingU.value = a2;
document.f1.txtAlamatTerakhir3PentingU.value = a3;
document.f1.txtPoskodPentingU.value = p1;
document.f1.txtBandarPentingU.value = b1;
document.f1.socNegeriPentingU.value = n1;

}

if(document.f1.copy.checked == false)
{


document.f1.txtAlamatTerakhir1PentingU.value = "";
document.f1.txtAlamatTerakhir2PentingU.value = "";
document.f1.txtAlamatTerakhir3PentingU.value = "";
document.f1.txtPoskodPentingU.value = "";
document.f1.txtBandarPentingU.value = "";
document.f1.socNegeriPentingU.value = "0";

}

}


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
/*
function submitForm() {    
  
	goTo('$val_tab');
	Effect.ScrollTo('$val_tab').focus(); return false;
	

} */


function submitForm() {    
//alert('$val_tab')
if('$!val_tab' != "" && '$!val_tab' != null)
{

   window.location.hash='$!val_tab';
   //goTo('$!val_tab');
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
	
} 




function CheckBandarTetap()
{
if(document.f1.socNegeriPenting.value == "" || document.f1.socNegeriPenting.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPenting.focus();
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

function getBandarTetap(v_t)
{

	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "tetap";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "insert";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	
	
	
	document.f1.submit();
	
	
	if(document.f1.jenis_pej.value == '6')
	{
	alamat_raya()
	}
}

function getBandarTetapCopy(v_t)
{
if(document.f1.copyAlamat.checked == true)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "copy";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "insert";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
	}
if(document.f1.copyAlamat.checked == false)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "copyfalse";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "insert";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
	}
}

function getBandarSurat(v_t)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "surat";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "insert";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
}






//UPDATE


function CheckBandarTetapU()
{
if(document.f1.socNegeriPenting.value == "" || document.f1.socNegeriPenting.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPenting.focus();
  return;
	  	
}

}
function CheckBandarSuratU()
{
if(document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriWarisSurat.focus();
  return;
	  	
}

}

function getBandarTetapU(v_t)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "tetap";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "update";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
}

function getBandarTetapCopyU(v_t)
{
if(document.f1.copyAlamat.checked == true)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "copy";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "update";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
	}
	if(document.f1.copyAlamat.checked == false)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "copyfalse";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "update";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
	}
}

function getBandarSuratU(v_t)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "surat";
	document.f1.command.value = "Penting";
	document.f1.simpanmode.value = "update";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
}











function pilih_taraf()
{
	

if(document.f1.txtNamaOBPenting != null   )
{
if(document.f1.socTarafKepentinganPenting.value == '6')
{
	
		
		
		if(document.f1.read_mode.value != "disabled")
		{
		document.getElementById("amanah").style.display="";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		}
		else
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		}
		
		document.getElementById("j_pemohon").style.display="";	
		
		
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="none";	
		document.getElementById("no_fax").style.display="";
		document.getElementById("chk_tr").style.display="none";
		
		
		
		document.getElementById("nama_1").style.display="none";
		document.getElementById("nama_2").style.display="";
		
		document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "01-AGENSI";
		
		document.getElementById("alamattetap1_a").style.display="none";
		document.getElementById("alamattetap2_a").style.display="none";
		document.getElementById("alamattetap3_a").style.display="none";
		document.getElementById("alamattetap1_b").style.display="";
		document.getElementById("alamattetap2_b").style.display="";
		document.getElementById("alamattetap3_b").style.display="";
		document.getElementById("poskodtetap_a").style.display="none";
		document.getElementById("poskodtetap_b").style.display="";
		document.getElementById("negeritetap_a").style.display="none";
		document.getElementById("negeritetap_b").style.display="";
		document.getElementById("bandartetap_a").style.display="none";
		document.getElementById("bandartetap_b").style.display="";
		
		
		document.getElementById("alamatsurat1_a").style.display="none";
		document.getElementById("alamatsurat2_a").style.display="none";
		document.getElementById("alamatsurat3_a").style.display="none";
		document.getElementById("alamatsurat1_b").style.display="";
		document.getElementById("alamatsurat2_b").style.display="";
		document.getElementById("alamatsurat3_b").style.display="";	
		document.getElementById("poskodsurat_a").style.display="none";
		document.getElementById("poskodsurat_b").style.display="";
		document.getElementById("negerisurat_a").style.display="none";
		document.getElementById("negerisurat_b").style.display="";
		document.getElementById("bandarsurat_a").style.display="none";
		document.getElementById("bandarsurat_b").style.display="";
		
		
		document.getElementById("notel_a").style.display="none";
		document.getElementById("notel_b").style.display="";
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="";
		

}
//alert("d")

else if(document.f1.socTarafKepentinganPenting.value == '4')
{
//alert(document.f1.jenis_pemohon.value)
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		document.getElementById("j_pemohon").style.display="";	
		
		if(document.f1.jenis_pemohon.value == '1')
		{
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="";	
		document.getElementById("no_fax").style.display="";
		
		document.getElementById("nofax_a").style.display="";
		document.getElementById("nofax_b").style.display="none";
		
		}
		else
		{
		
		document.getElementById("kp_baru").style.display="";	
		document.getElementById("kp_lama").style.display="";	
		document.getElementById("kp_jenis").style.display="";	
		document.getElementById("kp_lain").style.display="";	
		document.getElementById("jantina").style.display="";	
		document.getElementById("agama").style.display="";	
		document.getElementById("umur").style.display="";	
		document.getElementById("warga").style.display="";	
		document.getElementById("dob").style.display="";	
		document.getElementById("status_ob").style.display="";	
		document.getElementById("no_surat").style.display="";
		document.getElementById("no_hp").style.display="";	
		document.getElementById("no_hp_info").style.display="";	
		document.getElementById("no_tel_info").style.display="";	
		document.getElementById("no_fax").style.display="none";
		
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="none";
		
		}
		
		
		
		document.getElementById("chk_tr").style.display="";
		
		document.getElementById("nama_1").style.display="";
		document.getElementById("nama_2").style.display="none";
		
		document.getElementById("jenis_pemohon_dis").style.display="none";
		document.getElementById("jenis_pemohon_drop").style.display="";	
		//document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "";
		
		document.getElementById("alamattetap1_a").style.display="";
		document.getElementById("alamattetap2_a").style.display="";
		document.getElementById("alamattetap3_a").style.display="";
		document.getElementById("alamattetap1_b").style.display="none";
		document.getElementById("alamattetap2_b").style.display="none";
		document.getElementById("alamattetap3_b").style.display="none";
		document.getElementById("poskodtetap_a").style.display="";
		document.getElementById("poskodtetap_b").style.display="none";
		document.getElementById("negeritetap_a").style.display="";
		document.getElementById("negeritetap_b").style.display="none";
		document.getElementById("bandartetap_a").style.display="";
		document.getElementById("bandartetap_b").style.display="none";
		
		
		document.getElementById("alamatsurat1_a").style.display="";
		document.getElementById("alamatsurat2_a").style.display="";
		document.getElementById("alamatsurat3_a").style.display="";
		document.getElementById("alamatsurat1_b").style.display="none";
		document.getElementById("alamatsurat2_b").style.display="none";
		document.getElementById("alamatsurat3_b").style.display="none";	
		document.getElementById("poskodsurat_a").style.display="";
		document.getElementById("poskodsurat_b").style.display="none";
		document.getElementById("negerisurat_a").style.display="";
		document.getElementById("negerisurat_b").style.display="none";
		document.getElementById("bandarsurat_a").style.display="";
		document.getElementById("bandarsurat_b").style.display="none";
		
		
		document.getElementById("notel_a").style.display="";
		document.getElementById("notel_b").style.display="none";
		
		
		
		
		

	
}



else if(document.f1.socTarafKepentinganPenting.value == '8' )
{



        if(document.f1.read_mode.value != "disabled")
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="";
		document.getElementById('Insolvensi').style.display="none";
		}
		else
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		}


	
		document.getElementById("j_pemohon").style.display="";	
		
		
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="none";	
		document.getElementById("no_fax").style.display="";
		document.getElementById("chk_tr").style.display="none";
		document.getElementById("nama_1").style.display="none";
		document.getElementById("nama_2").style.display="";		
		document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "01-AGENSI";		
		document.getElementById("alamattetap1_a").style.display="none";
		document.getElementById("alamattetap2_a").style.display="none";
		document.getElementById("alamattetap3_a").style.display="none";
		document.getElementById("alamattetap1_b").style.display="";
		document.getElementById("alamattetap2_b").style.display="";
		document.getElementById("alamattetap3_b").style.display="";
		document.getElementById("poskodtetap_a").style.display="none";
		document.getElementById("poskodtetap_b").style.display="";
		document.getElementById("negeritetap_a").style.display="none";
		document.getElementById("negeritetap_b").style.display="";
		document.getElementById("bandartetap_a").style.display="none";
		document.getElementById("bandartetap_b").style.display="";
		document.getElementById("alamatsurat1_a").style.display="none";
		document.getElementById("alamatsurat2_a").style.display="none";
		document.getElementById("alamatsurat3_a").style.display="none";
		document.getElementById("alamatsurat1_b").style.display="";
		document.getElementById("alamatsurat2_b").style.display="";
		document.getElementById("alamatsurat3_b").style.display="";	
		document.getElementById("poskodsurat_a").style.display="none";
		document.getElementById("poskodsurat_b").style.display="";
		document.getElementById("negerisurat_a").style.display="none";
		document.getElementById("negerisurat_b").style.display="";
		document.getElementById("bandarsurat_a").style.display="none";
		document.getElementById("bandarsurat_b").style.display="";
		document.getElementById("notel_a").style.display="none";
		document.getElementById("notel_b").style.display="";
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="";
}

//arief add Majlis Agama Islam Negeri sebagai Pemegang Amanah 
else if(document.f1.socTarafKepentinganPenting.value == '10')
{
//alert(document.f1.jenis_pemohon.value)
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		document.getElementById("j_pemohon").style.display="";	
		
		if(document.f1.jenis_pemohon.value == '1') //01. AGENSI
		{
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="";	
		document.getElementById("no_fax").style.display="";
		
		document.getElementById("nofax_a").style.display="";
		document.getElementById("nofax_b").style.display="none";
		
		}
		else
		{
		
		document.getElementById("kp_baru").style.display="";	
		document.getElementById("kp_lama").style.display="";	
		document.getElementById("kp_jenis").style.display="";	
		document.getElementById("kp_lain").style.display="";	
		document.getElementById("jantina").style.display="";	
		document.getElementById("agama").style.display="";	
		document.getElementById("umur").style.display="";	
		document.getElementById("warga").style.display="";	
		document.getElementById("dob").style.display="";	
		document.getElementById("status_ob").style.display="";	
		document.getElementById("no_surat").style.display="";
		document.getElementById("no_hp").style.display="";	
		document.getElementById("no_hp_info").style.display="";	
		document.getElementById("no_tel_info").style.display="";	
		document.getElementById("no_fax").style.display="none";
		
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="none";
		}
		document.getElementById("chk_tr").style.display="";
		document.getElementById("nama_1").style.display="";
		document.getElementById("nama_2").style.display="none";
		document.getElementById("jenis_pemohon_dis").style.display="none";
		document.getElementById("jenis_pemohon_drop").style.display="";	
		//document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "";
		
		document.getElementById("alamattetap1_a").style.display="";
		document.getElementById("alamattetap2_a").style.display="";
		document.getElementById("alamattetap3_a").style.display="";
		document.getElementById("alamattetap1_b").style.display="none";
		document.getElementById("alamattetap2_b").style.display="none";
		document.getElementById("alamattetap3_b").style.display="none";
		document.getElementById("poskodtetap_a").style.display="";
		document.getElementById("poskodtetap_b").style.display="none";
		document.getElementById("negeritetap_a").style.display="";
		document.getElementById("negeritetap_b").style.display="none";
		document.getElementById("bandartetap_a").style.display="";
		document.getElementById("bandartetap_b").style.display="none";
		
		
		document.getElementById("alamatsurat1_a").style.display="";
		document.getElementById("alamatsurat2_a").style.display="";
		document.getElementById("alamatsurat3_a").style.display="";
		document.getElementById("alamatsurat1_b").style.display="none";
		document.getElementById("alamatsurat2_b").style.display="none";
		document.getElementById("alamatsurat3_b").style.display="none";	
		document.getElementById("poskodsurat_a").style.display="";
		document.getElementById("poskodsurat_b").style.display="none";
		document.getElementById("negerisurat_a").style.display="";
		document.getElementById("negerisurat_b").style.display="none";
		document.getElementById("bandarsurat_a").style.display="";
		document.getElementById("bandarsurat_b").style.display="none";
		
		
		document.getElementById("notel_a").style.display="";
		document.getElementById("notel_b").style.display="none";	
}

else if(document.f1.socTarafKepentinganPenting.value == '16' )
{



        if(document.f1.read_mode.value != "disabled")
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="";
		}
		else
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";		
		}


	
		document.getElementById("j_pemohon").style.display="";	
		
		
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="none";	
		document.getElementById("no_fax").style.display="";
		document.getElementById("chk_tr").style.display="none";
		document.getElementById("nama_1").style.display="none";
		document.getElementById("nama_2").style.display="";		
		document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "01-AGENSI";		
		document.getElementById("alamattetap1_a").style.display="none";
		document.getElementById("alamattetap2_a").style.display="none";
		document.getElementById("alamattetap3_a").style.display="none";
		document.getElementById("alamattetap1_b").style.display="";
		document.getElementById("alamattetap2_b").style.display="";
		document.getElementById("alamattetap3_b").style.display="";
		document.getElementById("poskodtetap_a").style.display="none";
		document.getElementById("poskodtetap_b").style.display="";
		document.getElementById("negeritetap_a").style.display="none";
		document.getElementById("negeritetap_b").style.display="";
		document.getElementById("bandartetap_a").style.display="none";
		document.getElementById("bandartetap_b").style.display="";
		
		
		document.getElementById("alamatsurat1_a").style.display="none";
		document.getElementById("alamatsurat2_a").style.display="none";
		document.getElementById("alamatsurat3_a").style.display="none";
		document.getElementById("alamatsurat1_b").style.display="";
		document.getElementById("alamatsurat2_b").style.display="";
		document.getElementById("alamatsurat3_b").style.display="";	
		document.getElementById("poskodsurat_a").style.display="none";
		document.getElementById("poskodsurat_b").style.display="";
		document.getElementById("negerisurat_a").style.display="none";
		document.getElementById("negerisurat_b").style.display="";
		document.getElementById("bandarsurat_a").style.display="none";
		document.getElementById("bandarsurat_b").style.display="";
		
		
		document.getElementById("notel_a").style.display="none";
		document.getElementById("notel_b").style.display="";
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="";
}

else if(document.f1.socTarafKepentinganPenting.value == '17')
{
	

		
        if(document.f1.read_mode.value != "disabled")
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		
		}
		else
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		
		}


	
		document.getElementById("j_pemohon").style.display="";	
		
		
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="none";	
		document.getElementById("no_fax").style.display="";
		document.getElementById("chk_tr").style.display="none";
		document.getElementById("nama_1").style.display="";
		document.getElementById("nama_2").style.display="none";		
		document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "01-AGENSI";		
		document.getElementById("alamattetap1_a").style.display="";
		document.getElementById("alamattetap2_a").style.display="";
		document.getElementById("alamattetap3_a").style.display="";
		document.getElementById("alamattetap1_b").style.display="none";
		document.getElementById("alamattetap2_b").style.display="none";
		document.getElementById("alamattetap3_b").style.display="none";
		document.getElementById("poskodtetap_a").style.display="";
		document.getElementById("poskodtetap_b").style.display="none";
		document.getElementById("negeritetap_a").style.display="";
		document.getElementById("negeritetap_b").style.display="none";
		document.getElementById("bandartetap_a").style.display="";
		document.getElementById("bandartetap_b").style.display="none";
		document.getElementById("alamatsurat1_a").style.display="";
		document.getElementById("alamatsurat2_a").style.display="";
		document.getElementById("alamatsurat3_a").style.display="";
		document.getElementById("alamatsurat1_b").style.display="none";
		document.getElementById("alamatsurat2_b").style.display="none";
		document.getElementById("alamatsurat3_b").style.display="none";	
		document.getElementById("poskodsurat_a").style.display="";
		document.getElementById("poskodsurat_b").style.display="none";
		document.getElementById("negerisurat_a").style.display="";
		document.getElementById("negerisurat_b").style.display="none";
		document.getElementById("bandarsurat_a").style.display="";
		document.getElementById("bandarsurat_b").style.display="none";
		document.getElementById("notel_a").style.display="";
		document.getElementById("notel_b").style.display="none";
		document.getElementById("nofax_a").style.display="";
		document.getElementById("nofax_b").style.display="none";
}
//arief add
else if (document.f1.socTarafKepentinganPenting.value == '18' || document.f1.socTarafKepentinganPenting.value == '20' || document.f1.socTarafKepentinganPenting.value == '21'){
	document.getElementById("j_pemohon").style.display="";	
	document.getElementById("kp_baru").style.display="none";	
	document.getElementById("kp_lama").style.display="none";	
	document.getElementById("kp_jenis").style.display="none";	
	document.getElementById("kp_lain").style.display="none";	
	document.getElementById("jantina").style.display="none";	
	document.getElementById("agama").style.display="none";	
	document.getElementById("umur").style.display="none";	
	document.getElementById("warga").style.display="none";	
	document.getElementById("dob").style.display="none";	
	document.getElementById("status_ob").style.display="none";	
	document.getElementById("no_surat").style.display="none";
	document.getElementById("no_hp").style.display="none";	
	document.getElementById("no_hp_info").style.display="none";	
	document.getElementById("no_tel_info").style.display="none";	
	document.getElementById("no_fax").style.display="";
	document.getElementById("chk_tr").style.display="none";
	document.getElementById("nama_1").style.display="";
	document.getElementById("nama_2").style.display="none";		
	document.getElementById("jenis_pemohon_dis").style.display="";
	document.getElementById("jenis_pemohon_drop").style.display="none";	
	document.f1.jenis_pemohon.value = "1";
	document.f1.jenis_pemohon_display.value = "01-AGENSI";		
	document.getElementById("alamattetap1_a").style.display="";
	document.getElementById("alamattetap2_a").style.display="";
	document.getElementById("alamattetap3_a").style.display="";
	document.getElementById("alamattetap1_b").style.display="none";
	document.getElementById("alamattetap2_b").style.display="none";
	document.getElementById("alamattetap3_b").style.display="none";
	document.getElementById("poskodtetap_a").style.display="";
	document.getElementById("poskodtetap_b").style.display="none";
	document.getElementById("negeritetap_a").style.display="";
	document.getElementById("negeritetap_b").style.display="none";
	document.getElementById("bandartetap_a").style.display="";
	document.getElementById("bandartetap_b").style.display="none";
	document.getElementById("alamatsurat1_a").style.display="";
	document.getElementById("alamatsurat2_a").style.display="";
	document.getElementById("alamatsurat3_a").style.display="";
	document.getElementById("alamatsurat1_b").style.display="none";
	document.getElementById("alamatsurat2_b").style.display="none";
	document.getElementById("alamatsurat3_b").style.display="none";	
	document.getElementById("poskodsurat_a").style.display="";
	document.getElementById("poskodsurat_b").style.display="none";
	document.getElementById("negerisurat_a").style.display="";
	document.getElementById("negerisurat_b").style.display="none";
	document.getElementById("bandarsurat_a").style.display="";
	document.getElementById("bandarsurat_b").style.display="none";
	document.getElementById("notel_a").style.display="";
	document.getElementById("notel_b").style.display="none";
	document.getElementById("nofax_a").style.display="";
	document.getElementById("nofax_b").style.display="none";
}
else
{


		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		document.getElementById("j_pemohon").style.display="";
		document.getElementById("kp_baru").style.display="";	
		document.getElementById("kp_lama").style.display="";	
		document.getElementById("kp_jenis").style.display="";	
		document.getElementById("kp_lain").style.display="";	
		document.getElementById("jantina").style.display="";	
		document.getElementById("agama").style.display="";	
		document.getElementById("umur").style.display="";	
		document.getElementById("warga").style.display="";	
		document.getElementById("dob").style.display="";	
		document.getElementById("status_ob").style.display="";	
		document.getElementById("no_surat").style.display="";
		document.getElementById("no_hp").style.display="";	
		document.getElementById("no_hp_info").style.display="";	
		document.getElementById("no_tel_info").style.display="";	
		document.getElementById("no_fax").style.display="none";		
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="none";
		
		
	
		
		
		document.getElementById("chk_tr").style.display="";
		
			
		
		document.getElementById("nama_1").style.display="";
		document.getElementById("nama_2").style.display="none";
		
	
	//alert("d:")
		
	    document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		
			
	
		
		document.f1.jenis_pemohon.value = "2";
		document.f1.jenis_pemohon_display.value = "02-INDIVIDU";
		
		document.getElementById("alamattetap1_a").style.display="";
		document.getElementById("alamattetap2_a").style.display="";
		document.getElementById("alamattetap3_a").style.display="";
		document.getElementById("alamattetap1_b").style.display="none";
		document.getElementById("alamattetap2_b").style.display="none";
		document.getElementById("alamattetap3_b").style.display="none";
		document.getElementById("poskodtetap_a").style.display="";
		document.getElementById("poskodtetap_b").style.display="none";
		document.getElementById("negeritetap_a").style.display="";
		document.getElementById("negeritetap_b").style.display="none";
		document.getElementById("bandartetap_a").style.display="";
		document.getElementById("bandartetap_b").style.display="none";
		
		
		document.getElementById("alamatsurat1_a").style.display="";
		document.getElementById("alamatsurat2_a").style.display="";
		document.getElementById("alamatsurat3_a").style.display="";
		document.getElementById("alamatsurat1_b").style.display="none";
		document.getElementById("alamatsurat2_b").style.display="none";
		document.getElementById("alamatsurat3_b").style.display="none";	
		document.getElementById("poskodsurat_a").style.display="";
		document.getElementById("poskodsurat_b").style.display="none";
		document.getElementById("negerisurat_a").style.display="";
		document.getElementById("negerisurat_b").style.display="none";
		document.getElementById("bandarsurat_a").style.display="";
		document.getElementById("bandarsurat_b").style.display="none";
		
		
		document.getElementById("notel_a").style.display="";
		document.getElementById("notel_b").style.display="none";
		
	
}


}






///OB update


if(document.f1.txtNamaOBPentingU!= null )
{


if(document.f1.socTarafKepentinganPentingU.value == '6')
{

        if(document.f1.read_mode.value != "disabled")
		{
		document.getElementById("amanah").style.display="";	
		document.getElementById('baitulmal').style.display="none";		
		document.getElementById('Insolvensi').style.display="none";	
		}
		else
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";	
		}
		
		document.getElementById("j_pemohon").style.display="";	
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		if(document.f1.read_mode.value != "disabled"){
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="none";}	
		document.getElementById("no_fax").style.display="";
		document.getElementById("chk_tr").style.display="none";
		
	
		
		document.getElementById("nama_1").style.display="none";
		document.getElementById("nama_2").style.display="";
		
		document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "01-AGENSI";
		
		document.getElementById("alamattetap1_a").style.display="none";
		document.getElementById("alamattetap2_a").style.display="none";
		document.getElementById("alamattetap3_a").style.display="none";
		document.getElementById("alamattetap1_b").style.display="";
		document.getElementById("alamattetap2_b").style.display="";
		document.getElementById("alamattetap3_b").style.display="";
		document.getElementById("poskodtetap_a").style.display="none";
		document.getElementById("poskodtetap_b").style.display="";
		document.getElementById("negeritetap_a").style.display="none";
		document.getElementById("negeritetap_b").style.display="";
		document.getElementById("bandartetap_a").style.display="none";
		document.getElementById("bandartetap_b").style.display="";
		
		
		document.getElementById("alamatsurat1_a").style.display="none";
		document.getElementById("alamatsurat2_a").style.display="none";
		document.getElementById("alamatsurat3_a").style.display="none";
		document.getElementById("alamatsurat1_b").style.display="";
		document.getElementById("alamatsurat2_b").style.display="";
		document.getElementById("alamatsurat3_b").style.display="";	
		document.getElementById("poskodsurat_a").style.display="none";
		document.getElementById("poskodsurat_b").style.display="";
		document.getElementById("negerisurat_a").style.display="none";
		document.getElementById("negerisurat_b").style.display="";
		document.getElementById("bandarsurat_a").style.display="none";
		document.getElementById("bandarsurat_b").style.display="";
		
		
		document.getElementById("notel_a").style.display="none";
		document.getElementById("notel_b").style.display="";
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="";
		

}

else if(document.f1.socTarafKepentinganPentingU.value == '4' || document.f1.socTarafKepentinganPenting.value == '10')
{
//alert(document.f1.jenis_pemohon.value)
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById("j_pemohon").style.display="";	
		
		if(document.f1.jenis_pemohon.value == '1')
		{
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";
		if(document.f1.read_mode.value != "disabled"){	
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="";	
		}
		document.getElementById("no_fax").style.display="";
		
		document.getElementById("nofax_a").style.display="";
		document.getElementById("nofax_b").style.display="none";
		
		}
		if(document.f1.jenis_pemohon.value == '2')
		{
		
		document.getElementById("kp_baru").style.display="";	
		document.getElementById("kp_lama").style.display="";	
		document.getElementById("kp_jenis").style.display="";	
		document.getElementById("kp_lain").style.display="";	
		document.getElementById("jantina").style.display="";	
		document.getElementById("agama").style.display="";	
		document.getElementById("umur").style.display="";	
		document.getElementById("warga").style.display="";	
		document.getElementById("dob").style.display="";	
		
		document.getElementById("status_ob").style.display="";	
		document.getElementById("no_surat").style.display="";
		document.getElementById("no_hp").style.display="";	
		if(document.f1.read_mode.value != "disabled"){
		document.getElementById("no_hp_info").style.display="";			
		document.getElementById("no_tel_info").style.display="";
		}	
		
		document.getElementById("no_fax").style.display="none";
		
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="none";
		
		
		
		}
		
		
		
		document.getElementById("chk_tr").style.display="";
		
		document.getElementById("nama_1").style.display="";
		document.getElementById("nama_2").style.display="none";
		
		document.getElementById("jenis_pemohon_dis").style.display="none";
		document.getElementById("jenis_pemohon_drop").style.display="";	
		//document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "";
		
		document.getElementById("alamattetap1_a").style.display="";
		document.getElementById("alamattetap2_a").style.display="";
		document.getElementById("alamattetap3_a").style.display="";
		document.getElementById("alamattetap1_b").style.display="none";
		document.getElementById("alamattetap2_b").style.display="none";
		document.getElementById("alamattetap3_b").style.display="none";
		document.getElementById("poskodtetap_a").style.display="";
		document.getElementById("poskodtetap_b").style.display="none";
		document.getElementById("negeritetap_a").style.display="";
		document.getElementById("negeritetap_b").style.display="none";
		document.getElementById("bandartetap_a").style.display="";
		document.getElementById("bandartetap_b").style.display="none";
		
		
		document.getElementById("alamatsurat1_a").style.display="";
		document.getElementById("alamatsurat2_a").style.display="";
		document.getElementById("alamatsurat3_a").style.display="";
		document.getElementById("alamatsurat1_b").style.display="none";
		document.getElementById("alamatsurat2_b").style.display="none";
		document.getElementById("alamatsurat3_b").style.display="none";	
		document.getElementById("poskodsurat_a").style.display="";
		document.getElementById("poskodsurat_b").style.display="none";
		document.getElementById("negerisurat_a").style.display="";
		document.getElementById("negerisurat_b").style.display="none";
		document.getElementById("bandarsurat_a").style.display="";
		document.getElementById("bandarsurat_b").style.display="none";
		
		
		document.getElementById("notel_a").style.display="";
		document.getElementById("notel_b").style.display="none";	
}

//arief add Majlis Agama Islam Negeri sebagai Pemegang Amanah 
else if(document.f1.socTarafKepentinganPenting.value == '10')
{
//alert(document.f1.jenis_pemohon.value)
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		document.getElementById("j_pemohon").style.display="";	
		
		if(document.f1.jenis_pemohon.value == '1') //01. AGENSI
		{
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="";	
		document.getElementById("no_fax").style.display="";
		
		document.getElementById("nofax_a").style.display="";
		document.getElementById("nofax_b").style.display="none";
		
		}
		else
		{
		
		document.getElementById("kp_baru").style.display="";	
		document.getElementById("kp_lama").style.display="";	
		document.getElementById("kp_jenis").style.display="";	
		document.getElementById("kp_lain").style.display="";	
		document.getElementById("jantina").style.display="";	
		document.getElementById("agama").style.display="";	
		document.getElementById("umur").style.display="";	
		document.getElementById("warga").style.display="";	
		document.getElementById("dob").style.display="";	
		document.getElementById("status_ob").style.display="";	
		document.getElementById("no_surat").style.display="";
		document.getElementById("no_hp").style.display="";	
		document.getElementById("no_hp_info").style.display="";	
		document.getElementById("no_tel_info").style.display="";	
		document.getElementById("no_fax").style.display="none";
		
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="none";
		}
		document.getElementById("chk_tr").style.display="";
		document.getElementById("nama_1").style.display="";
		document.getElementById("nama_2").style.display="none";
		document.getElementById("jenis_pemohon_dis").style.display="none";
		document.getElementById("jenis_pemohon_drop").style.display="";	
		//document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "";
		
		document.getElementById("alamattetap1_a").style.display="";
		document.getElementById("alamattetap2_a").style.display="";
		document.getElementById("alamattetap3_a").style.display="";
		document.getElementById("alamattetap1_b").style.display="none";
		document.getElementById("alamattetap2_b").style.display="none";
		document.getElementById("alamattetap3_b").style.display="none";
		document.getElementById("poskodtetap_a").style.display="";
		document.getElementById("poskodtetap_b").style.display="none";
		document.getElementById("negeritetap_a").style.display="";
		document.getElementById("negeritetap_b").style.display="none";
		document.getElementById("bandartetap_a").style.display="";
		document.getElementById("bandartetap_b").style.display="none";
		
		
		document.getElementById("alamatsurat1_a").style.display="";
		document.getElementById("alamatsurat2_a").style.display="";
		document.getElementById("alamatsurat3_a").style.display="";
		document.getElementById("alamatsurat1_b").style.display="none";
		document.getElementById("alamatsurat2_b").style.display="none";
		document.getElementById("alamatsurat3_b").style.display="none";	
		document.getElementById("poskodsurat_a").style.display="";
		document.getElementById("poskodsurat_b").style.display="none";
		document.getElementById("negerisurat_a").style.display="";
		document.getElementById("negerisurat_b").style.display="none";
		document.getElementById("bandarsurat_a").style.display="";
		document.getElementById("bandarsurat_b").style.display="none";
		
		
		document.getElementById("notel_a").style.display="";
		document.getElementById("notel_b").style.display="none";	
}


else if(document.f1.socTarafKepentinganPenting.value == '17' )
{

		
        if(document.f1.read_mode.value != "disabled")
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		}
		else
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		}
		
		document.getElementById("j_pemohon").style.display="";	
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		if(document.f1.read_mode.value != "disabled"){
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="none";}	
		document.getElementById("no_fax").style.display="";
		document.getElementById("chk_tr").style.display="none";
		
	
		
		document.getElementById("nama_1").style.display="none";
		document.getElementById("nama_2").style.display="";
		
		document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "01-AGENSI";
		
		document.getElementById("alamattetap1_a").style.display="none";
		document.getElementById("alamattetap2_a").style.display="none";
		document.getElementById("alamattetap3_a").style.display="none";
		document.getElementById("alamattetap1_b").style.display="";
		document.getElementById("alamattetap2_b").style.display="";
		document.getElementById("alamattetap3_b").style.display="";
		document.getElementById("poskodtetap_a").style.display="none";
		document.getElementById("poskodtetap_b").style.display="";
		document.getElementById("negeritetap_a").style.display="none";
		document.getElementById("negeritetap_b").style.display="";
		document.getElementById("bandartetap_a").style.display="none";
		document.getElementById("bandartetap_b").style.display="";
		
		
		document.getElementById("alamatsurat1_a").style.display="none";
		document.getElementById("alamatsurat2_a").style.display="none";
		document.getElementById("alamatsurat3_a").style.display="none";
		document.getElementById("alamatsurat1_b").style.display="";
		document.getElementById("alamatsurat2_b").style.display="";
		document.getElementById("alamatsurat3_b").style.display="";	
		document.getElementById("poskodsurat_a").style.display="none";
		document.getElementById("poskodsurat_b").style.display="";
		document.getElementById("negerisurat_a").style.display="none";
		document.getElementById("negerisurat_b").style.display="";
		document.getElementById("bandarsurat_a").style.display="none";
		document.getElementById("bandarsurat_b").style.display="";
		
		
		document.getElementById("notel_a").style.display="none";
		document.getElementById("notel_b").style.display="";
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="";
		

}

else if(document.f1.socTarafKepentinganPentingU.value == '8')
{
//alert(document.f1.jenis_pemohon.value)
	//	document.getElementById("amanah").style.display="none";	
	
	
    	if(document.f1.read_mode.value != "disabled")
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="";	
		document.getElementById('Insolvensi').style.display="none";	
		}
		else
		{
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";	
		}
	
	document.getElementById("j_pemohon").style.display="";	
		document.getElementById("kp_baru").style.display="none";	
		document.getElementById("kp_lama").style.display="none";	
		document.getElementById("kp_jenis").style.display="none";	
		document.getElementById("kp_lain").style.display="none";	
		document.getElementById("jantina").style.display="none";	
		document.getElementById("agama").style.display="none";	
		document.getElementById("umur").style.display="none";	
		document.getElementById("warga").style.display="none";	
		document.getElementById("dob").style.display="none";	
		document.getElementById("status_ob").style.display="none";	
		document.getElementById("no_surat").style.display="none";
		document.getElementById("no_hp").style.display="none";	
		if(document.f1.read_mode.value != "disabled"){
		document.getElementById("no_hp_info").style.display="none";	
		document.getElementById("no_tel_info").style.display="none";}	
		document.getElementById("no_fax").style.display="";
		document.getElementById("chk_tr").style.display="none";
		
	
		
		document.getElementById("nama_1").style.display="none";
		document.getElementById("nama_2").style.display="";
		
		document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		document.f1.jenis_pemohon.value = "1";
		document.f1.jenis_pemohon_display.value = "01-AGENSI";
		
		document.getElementById("alamattetap1_a").style.display="none";
		document.getElementById("alamattetap2_a").style.display="none";
		document.getElementById("alamattetap3_a").style.display="none";
		document.getElementById("alamattetap1_b").style.display="";
		document.getElementById("alamattetap2_b").style.display="";
		document.getElementById("alamattetap3_b").style.display="";
		document.getElementById("poskodtetap_a").style.display="none";
		document.getElementById("poskodtetap_b").style.display="";
		document.getElementById("negeritetap_a").style.display="none";
		document.getElementById("negeritetap_b").style.display="";
		document.getElementById("bandartetap_a").style.display="none";
		document.getElementById("bandartetap_b").style.display="";
		
		
		document.getElementById("alamatsurat1_a").style.display="none";
		document.getElementById("alamatsurat2_a").style.display="none";
		document.getElementById("alamatsurat3_a").style.display="none";
		document.getElementById("alamatsurat1_b").style.display="";
		document.getElementById("alamatsurat2_b").style.display="";
		document.getElementById("alamatsurat3_b").style.display="";	
		document.getElementById("poskodsurat_a").style.display="none";
		document.getElementById("poskodsurat_b").style.display="";
		document.getElementById("negerisurat_a").style.display="none";
		document.getElementById("negerisurat_b").style.display="";
		document.getElementById("bandarsurat_a").style.display="none";
		document.getElementById("bandarsurat_b").style.display="";
		
		
		document.getElementById("notel_a").style.display="none";
		document.getElementById("notel_b").style.display="";
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="";
		
		
		

	
}
//arief add
else if (document.f1.socTarafKepentinganPenting.value == '18' || document.f1.socTarafKepentinganPenting.value == '20' || document.f1.socTarafKepentinganPenting.value == '21'){
	document.getElementById("j_pemohon").style.display="";	
	document.getElementById("kp_baru").style.display="none";	
	document.getElementById("kp_lama").style.display="none";	
	document.getElementById("kp_jenis").style.display="none";	
	document.getElementById("kp_lain").style.display="none";	
	document.getElementById("jantina").style.display="none";	
	document.getElementById("agama").style.display="none";	
	document.getElementById("umur").style.display="none";	
	document.getElementById("warga").style.display="none";	
	document.getElementById("dob").style.display="none";	
	document.getElementById("status_ob").style.display="none";	
	document.getElementById("no_surat").style.display="none";
	document.getElementById("no_hp").style.display="none";	
	document.getElementById("no_hp_info").style.display="none";	
	document.getElementById("no_tel_info").style.display="none";	
	document.getElementById("no_fax").style.display="";
	document.getElementById("chk_tr").style.display="none";
	document.getElementById("nama_1").style.display="";
	document.getElementById("nama_2").style.display="none";		
	document.getElementById("jenis_pemohon_dis").style.display="";
	document.getElementById("jenis_pemohon_drop").style.display="none";	
	document.f1.jenis_pemohon.value = "1";
	document.f1.jenis_pemohon_display.value = "01-AGENSI";		
	document.getElementById("alamattetap1_a").style.display="";
	document.getElementById("alamattetap2_a").style.display="";
	document.getElementById("alamattetap3_a").style.display="";
	document.getElementById("alamattetap1_b").style.display="none";
	document.getElementById("alamattetap2_b").style.display="none";
	document.getElementById("alamattetap3_b").style.display="none";
	document.getElementById("poskodtetap_a").style.display="";
	document.getElementById("poskodtetap_b").style.display="none";
	document.getElementById("negeritetap_a").style.display="";
	document.getElementById("negeritetap_b").style.display="none";
	document.getElementById("bandartetap_a").style.display="";
	document.getElementById("bandartetap_b").style.display="none";
	document.getElementById("alamatsurat1_a").style.display="";
	document.getElementById("alamatsurat2_a").style.display="";
	document.getElementById("alamatsurat3_a").style.display="";
	document.getElementById("alamatsurat1_b").style.display="none";
	document.getElementById("alamatsurat2_b").style.display="none";
	document.getElementById("alamatsurat3_b").style.display="none";	
	document.getElementById("poskodsurat_a").style.display="";
	document.getElementById("poskodsurat_b").style.display="none";
	document.getElementById("negerisurat_a").style.display="";
	document.getElementById("negerisurat_b").style.display="none";
	document.getElementById("bandarsurat_a").style.display="";
	document.getElementById("bandarsurat_b").style.display="none";
	document.getElementById("notel_a").style.display="";
	document.getElementById("notel_b").style.display="none";
	document.getElementById("nofax_a").style.display="";
	document.getElementById("nofax_b").style.display="none";
}


else
{


//alert(document.f1.jenis_pemohon.value)
		document.getElementById("amanah").style.display="none";	
		document.getElementById('baitulmal').style.display="none";
		document.getElementById('Insolvensi').style.display="none";
		document.getElementById("j_pemohon").style.display="";	

		
		document.getElementById("kp_baru").style.display="";	
		document.getElementById("kp_lama").style.display="";	
		document.getElementById("kp_jenis").style.display="";	
		document.getElementById("kp_lain").style.display="";	
		document.getElementById("jantina").style.display="";	
		document.getElementById("agama").style.display="";	
		document.getElementById("umur").style.display="";	
		document.getElementById("warga").style.display="";	
		document.getElementById("dob").style.display="";	
		document.getElementById("status_ob").style.display="";	
		document.getElementById("no_surat").style.display="";
		document.getElementById("no_hp").style.display="";	
		
			if(document.f1.read_mode.value != "disabled"){	
		document.getElementById("no_hp_info").style.display="";	
		document.getElementById("no_tel_info").style.display="";	
		}
		document.getElementById("no_fax").style.display="none";
		
		document.getElementById("nofax_a").style.display="none";
		document.getElementById("nofax_b").style.display="none";
		
		
		
		
		document.getElementById("chk_tr").style.display="";
		
		document.getElementById("nama_1").style.display="";
		document.getElementById("nama_2").style.display="none";
		
		document.getElementById("jenis_pemohon_dis").style.display="";
		document.getElementById("jenis_pemohon_drop").style.display="none";	
		document.f1.jenis_pemohon.value = "2";
		document.f1.jenis_pemohon_display.value = "02-INDIVIDU";
		
		document.getElementById("alamattetap1_a").style.display="";
		document.getElementById("alamattetap2_a").style.display="";
		document.getElementById("alamattetap3_a").style.display="";
		document.getElementById("alamattetap1_b").style.display="none";
		document.getElementById("alamattetap2_b").style.display="none";
		document.getElementById("alamattetap3_b").style.display="none";
		document.getElementById("poskodtetap_a").style.display="";
		document.getElementById("poskodtetap_b").style.display="none";
		document.getElementById("negeritetap_a").style.display="";
		document.getElementById("negeritetap_b").style.display="none";
		document.getElementById("bandartetap_a").style.display="";
		document.getElementById("bandartetap_b").style.display="none";
		
		
		document.getElementById("alamatsurat1_a").style.display="";
		document.getElementById("alamatsurat2_a").style.display="";
		document.getElementById("alamatsurat3_a").style.display="";
		document.getElementById("alamatsurat1_b").style.display="none";
		document.getElementById("alamatsurat2_b").style.display="none";
		document.getElementById("alamatsurat3_b").style.display="none";	
		document.getElementById("poskodsurat_a").style.display="";
		document.getElementById("poskodsurat_b").style.display="none";
		document.getElementById("negerisurat_a").style.display="";
		document.getElementById("negerisurat_b").style.display="none";
		document.getElementById("bandarsurat_a").style.display="";
		document.getElementById("bandarsurat_b").style.display="none";
		
		
		document.getElementById("notel_a").style.display="";
		document.getElementById("notel_b").style.display="none";
	
}

}


}





function pilih_amanahU()
{
if((document.f1.socTarafKepentinganPentingU.value == "6" || document.f1.socTarafKepentinganPentingU.value == "16" || document.f1.socTarafKepentinganPentingU.value == "8") && document.f1.txtNamaOBPentingU!= null){
		
		 	//alert(document.f1.socBandarWarisSurat.value)
							document.f1.socNegeriPentingU.value = '';
							
					 		document.f1.socNegeriPenting_D.value = '';
							
					 	//	document.f1.txtBandarWarisSurat.value = ""; 
								
					 		document.f1.txtBandarWarisSurat_D.value = '';
							
					 	    document.f1.txtNamaOBPentingU.value = '';	 		
					 		document.f1.txtNamaOBPenting_D.value = "";			 		
					 		document.f1.txtAlamatTerakhir1PentingU.value = '';
					 		document.f1.txtAlamatTerakhir2PentingU.value = ''; 
					 		document.f1.txtAlamatTerakhir3PentingU.value = '';
					 		document.f1.txtAlamatTerakhir1Penting_D.value = '';
					 		document.f1.txtAlamatTerakhir2Penting_D.value = '';
					 		document.f1.txtAlamatTerakhir3Penting_D.value = '';
					 		document.f1.txtPoskodPentingU.value = '';
					 		document.f1.txtPoskodPenting_D.value = '';
					 		document.f1.txtBandarPenting_D.value = '';							
					 		document.f1.txtAlamatTerakhir1WarisSurat.value = '';
					 		document.f1.txtAlamatTerakhir2WarisSurat.value = '';
					 		document.f1.txtAlamatTerakhir3WarisSurat.value = ''; 
					 		document.f1.txtAlamatTerakhir1WarisSurat_D.value = '';
					 		document.f1.txtAlamatTerakhir2WarisSurat_D.value = '';
					 		document.f1.txtAlamatTerakhir3WarisSurat_D.value = '';
					 	    document.f1.txtPoskodWarisSurat.value = '';
					 		document.f1.txtPoskodWarisSurat_D.value = '';
					 		document.f1.socNegeriWarisSurat.value = '';
					 		document.f1.socNegeriWarisSurat_D.value = '';
					 		
					 		
					 		document.f1.txtNoTeleponPentingU.value = '';
					 		document.f1.txtNoTeleponPenting_D.value = '';
					 		document.f1.txtNoFaxPenting.value = '';
					 		document.f1.txtNoFaxPenting_D.value = '';
							document.f1.txtBandarWarisSurat.value = ''; 
					        document.f1.txtBandarPentingU.value = ''; 
					 		
		
		}
		
}






function pilih_amanah()
{



if((document.f1.socTarafKepentinganPenting.value == "6" || document.f1.socTarafKepentinganPenting.value == "16" || document.f1.socTarafKepentinganPenting.value == "8" || document.f1.socTarafKepentinganPenting.value == "17" || document.f1.socTarafKepentinganPenting.value == "19" || document.f1.socTarafKepentinganPenting.value == "20") && document.f1.txtNamaOBPenting!= null){
	
							document.f1.socNegeriPenting.value = '';
					 		document.f1.socNegeriPenting_D.value = '';
					 		document.f1.txtBandarWarisSurat.value = ''; 
					 		document.f1.txtBandarWarisSurat_D.value = '';
					 	    document.f1.txtNamaOBPenting.value = '';	 		
					 		document.f1.txtNamaOBPenting_D.value = '';			 		
					 		document.f1.txtAlamatTerakhir1Penting.value = '';
					 		document.f1.txtAlamatTerakhir2Penting.value = ''; 
					 		document.f1.txtAlamatTerakhir3Penting.value = '';
					 		document.f1.txtAlamatTerakhir1Penting_D.value = '';
					 		document.f1.txtAlamatTerakhir2Penting_D.value = '';
					 		document.f1.txtAlamatTerakhir3Penting_D.value = '';
					 		document.f1.txtPoskodPenting.value = '';
					 		document.f1.txtPoskodPenting_D.value = '';
					 		document.f1.txtBandarPenting_D.value = '';							
					 		document.f1.txtAlamatTerakhir1WarisSurat.value = '';
					 		document.f1.txtAlamatTerakhir2WarisSurat.value = '';
					 		document.f1.txtAlamatTerakhir3WarisSurat.value = ''; 
					 		document.f1.txtAlamatTerakhir1WarisSurat_D.value = '';
					 		document.f1.txtAlamatTerakhir2WarisSurat_D.value = '';
					 		document.f1.txtAlamatTerakhir3WarisSurat_D.value = '';
					 	    document.f1.txtPoskodWarisSurat.value = '';
					 		document.f1.txtPoskodWarisSurat_D.value = '';
					 		document.f1.socNegeriWarisSurat.value = '';
					 		document.f1.socNegeriWarisSurat_D.value = '';
					 		
					 		
					 		document.f1.txtNoTeleponPenting.value = '';
					 		document.f1.txtNoTeleponPenting_D.value = '';
					 		document.f1.txtNoFaxPenting.value = '';
					 		document.f1.txtNoFaxPenting_D.value = '';
							document.f1.txtBandarWarisSurat.value = ''; 
					        document.f1.txtBandarPenting.value = ''; 					 		
		
		}
		
		
		
}



function alamat_raya()
{


if(document.f1.socTarafKepentinganPenting.value == '6')
{
document.f1.jenis_pej.value = document.f1.jenis_pej1.value;
}
if(document.f1.socTarafKepentinganPenting.value == '8')
{
document.f1.jenis_pej.value = document.f1.jenis_pej2.value;
}
if(document.f1.socTarafKepentinganPenting.value == '16')
{
document.f1.jenis_pej.value = document.f1.jenis_pej3.value;
}


if(document.f1.jenis_pej.value != "" && document.f1.read_mode.value != "disabled" && document.f1.txtNamaOBPenting != null && (document.f1.socTarafKepentinganPenting.value == "6"  || document.f1.socTarafKepentinganPenting.value == "8" || document.f1.socTarafKepentinganPenting.value == "16") )
{
//alert("alamat_raya 1")
if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck?command=getalamat_raya_OB";
}else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck?command=getalamat_raya_OB";
}

	actionName = "getalamat_raya_OB";
	target = "add_alamat_raya";
	doAjaxUpdater(document.f1, url, target, actionName);
}


}


function alamat_raya_up()
{

if(document.f1.socTarafKepentinganPentingU.value == '6')
{
document.f1.jenis_pej.value = document.f1.jenis_pej1.value;
}
if(document.f1.socTarafKepentinganPentingU.value == '8')
{
document.f1.jenis_pej.value = document.f1.jenis_pej2.value;
}
if(document.f1.socTarafKepentinganPenting.value == '16')
{
document.f1.jenis_pej.value = document.f1.jenis_pej3.value;
} 


if(document.f1.jenis_pej.value != "" && document.f1.read_mode.value != "disabled"  && document.f1.txtNamaOBPentingU != null && (document.f1.socTarafKepentinganPentingU.value == "6" || document.f1.socTarafKepentinganPentingU.value == "8" || document.f1.socTarafKepentinganPentingU.value == "16") )
{
if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "getalamat_raya_OBUP";
	target = "add_alamat_raya_up";
	doAjaxUpdater(document.f1, url, target, actionName);
}
}




function default_amanah()
{
document.f1.jenis_pej1.value = "";
document.f1.jenis_pej2.value = "";
document.f1.jenis_pej.value = "";
document.f1.jenis_pej3.value = "";
}



function status_obU()
{
if(document.f1.socStatusOBU.value == "3")
{
document.f1.txtNoKPLamaPentingU.value = "TDK";
}

}

function status_ob()
{
if(document.f1.socStatusOB.value == "3")
{
document.f1.txtNoKPLamaPenting.value = "TDK";
}

}



</script>

<!-- ADD BY PEJE -->
<script>
function calculateTarikhLahirOBU(){

	if (document.f1.txtNoKPBaru1PentingU.value != ""){
		var currentTime = new Date();
		
		var noKP = document.f1.txtNoKPBaru1PentingU.value;		
		if(noKP.length == 6){
			var a = noKP.charAt(0);
			var b = noKP.charAt(1);
			var c = noKP.charAt(2);
			var d = noKP.charAt(3);
			var e = noKP.charAt(4);
			var f = noKP.charAt(5);
			
			var currentYear = currentTime.getFullYear();
			var birthYear = currentYear + "";
			birthYear = birthYear.charAt(0) + "" + birthYear.charAt(1);
			var fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			
			if (fullBirthYear > currentYear ){
				birthYear = birthYear*1 - 1;
				birthYear = birthYear + "";
				fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			}
			document.f1.txdTarikhLahirPentingU.value = e + f + "/" + c + d + "/" + fullBirthYear;
			calculateUmurOBU();
			defineStatusWarisByUmurOBU();
		}
	}	
}
function calculateUmurOBU(){

	if (document.f1.txdTarikhLahirPentingU.value != ""){
		
		var str1  = document.getElementById("txdTarikhLahirPentingU").value;
		var currentTime = new Date();
		var currentYear = currentTime.getFullYear();

		var yr1   = parseInt(str1.substring(6,10),10);
		
		var age = (currentYear*1) - (yr1*1);
		if (age < 0 )
			age = 0;
		document.f1.txtUmurPentingU.value = age ;		
	}
}
function defineStatusWarisByUmurOBU(){

	if (document.f1.txtUmurPentingU.value != ""){
		
		var age  = document.getElementById("txtUmurPentingU").value;
		if (age > 18) {
			document.f1.socStatusOBU.value = "1" ;
		} else {
			document.f1.socStatusOBU.value = "2" ;
		}		
	}
}
</script>
<!-- END ADD BY PEJE -->

<!-- ADD BY PEJE -->
<script>
function calculateTarikhLahirOB(){

	if (document.f1.txtNoKPBaru1Penting.value != ""){
		var currentTime = new Date();
		
		var noKP = document.f1.txtNoKPBaru1Penting.value;		
		if(noKP.length == 6){
			var a = noKP.charAt(0);
			var b = noKP.charAt(1);
			var c = noKP.charAt(2);
			var d = noKP.charAt(3);
			var e = noKP.charAt(4);
			var f = noKP.charAt(5);
			
			var currentYear = currentTime.getFullYear();
			var birthYear = currentYear + "";
			birthYear = birthYear.charAt(0) + "" + birthYear.charAt(1);
			var fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			
			if (fullBirthYear > currentYear ){
				birthYear = birthYear*1 - 1;
				birthYear = birthYear + "";
				fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			}
			document.f1.txdTarikhLahirPenting.value = e + f + "/" + c + d + "/" + fullBirthYear;
			calculateUmurOB();
			defineStatusWarisByUmurOB();
		}
	}	
}
function calculateUmurOB(){

	if (document.f1.txdTarikhLahirPenting.value != ""){
		
		var str1  = document.getElementById("txdTarikhLahirPenting").value;
		var currentTime = new Date();
		var currentYear = currentTime.getFullYear();

		var yr1   = parseInt(str1.substring(6,10),10);
		
		var age = (currentYear*1) - (yr1*1);
		if (age < 0 )
			age = 0;
		document.f1.txtUmurPenting.value = age ;		
	}
}
function defineStatusWarisByUmurOB(){

	if (document.f1.txtUmurPenting.value != ""){
		
		var age  = document.getElementById("txtUmurPenting").value;
		if (age > 18) {
			document.f1.socStatusOB.value = "1" ;
		} else {
			document.f1.socStatusOB.value = "2" ;
		}		
	}
}
</script>
<!-- END ADD BY PEJE -->

</body>
</html>
