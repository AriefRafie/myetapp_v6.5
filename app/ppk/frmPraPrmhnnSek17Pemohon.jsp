


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

.style38 {font-size: 10px}

.style40 {color: #0000FF}
.style44 {color: #FF0000}
.style43 {font-size: 10px; color: #FF0000; }
.style47 {
	font-size: 9px;
	font-style: italic;
}
.style50 {color: #0000FF; font-size: 9px; font-style: italic; }
.style51 {
	color: #0000FF;
	font-size: 9;
}
.style52 {color: #000000}
-->
</style>
</head>

<body onload="pilih_taraf();submitForm()" >
<form id="form1" name="f1" method="post" action="">
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
 <input type="hidden" name="v_tab" id="v_tab" value="" />
 #foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#set($id_Status = $list.id_Status)
#end
 #parse("app/ppk/paging_sek8.jsp")
 <input name="eventStatus" id="eventStatus" type="hidden" />
 


#foreach($PermohonanSebelum in $listGetPermohonanSebelum)
#set ($id_Permohonan_terdahulu = $PermohonanSebelum.id_Permohonan)
#set ($no_subjaket = $PermohonanSebelum.no_subjaket)
#end
<input type="hidden" name="dah_daftar_ke" id="dah_daftar_ke" value="sudah" />
<input name="id_Permohonan_terdahulu" type="hidden"  value="$id_Permohonan_terdahulu"/>
<input name="no_subjaket" type="hidden"  value="$no_subjaket"/>
#if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
#parse("app/ppk/bil_fail.jsp") 
#end
<table width="100%" border="0">


 <input type="hidden" name="command" value="">
 <input type="hidden" name="mode" value="">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
 <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>

 



#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    <input name="idpermohonan_baru" type="hidden" value="$list.idPermohonan" />
    
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
       <input name="idpermohonan" type="hidden"  value="$id"/>
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
        <input name="id_Fail" type="hidden"  value="$list.idFail"/>
        <input name="bandar_event" id="bandar_event" type="hidden" />
       
       
      
        

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
 
 
 
 #foreach($listFail in $ViewFail)
<input name="id_Suburusanstatus" type="hidden"  value="$listFail.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$listFail.id_Suburusanstatusfail"/>
#end
<tr>
<td>


#parse("app/ppk/maklumat_sek8.jsp")

  #set($md=$listtarikhMohon)
                    <input type="hidden" name="txdTarikhMohon" value="$listtarikhMohon" readonly="true"/>
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
       #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" >NILAIAN HARTA</li>
      #else
   
       #if($!hideTabPengesahan == "show")
       <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" > 
       PENGESAHAN PERMOHONAN</li>
       #end
       
      #end
    </ul>
    
    
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
     
        <div id="TabbedPanels2" class="TabbedPanelsContentVisible">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()">SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,1,0,0);PemohonView()">PEMOHON</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,2,0,0);WarisView()">WARIS</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,3,0,0);PentingView()">ORANG BERKEPENTINGAN</li>
             #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,5,0,0);PemiutangView()">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
            #end
          </ul>
          
          <div class="TabbedPanelsContentGroup">
          

         
            <div>
            </div>
            
            <div class="TabbedPanelsContent">
            
              <div id="TabbedPanels3" class="TabbedPanelsContentVisible">
                <ul class="TabbedPanelsTabGroup">
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,1,0,0);PemohonView()" id="tab_Pemohon">PEMOHON</li>
          
          #if($show_peguam_tab=="yes") 
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,1,1,0);PeguamView()">PEGUAM</li>
                   #end
                </ul>
                <div class="TabbedPanelsContentGroup">
                
                
                
                
                
                
                  <div class="TabbedPanelsContent" > 
                  
                  #parse("app/ppk/info_berjaya_disimpan.jsp")    
                     
                     <table width="100%" border="0" >
                      <tr>
                
               #foreach($listpemohon in $listPemohon)
                      #set($tpemohon = $listpemohon.idTarafkptg)
                      #end
              #if($tpemohon == 6 || $tpemohon == 8)
              
              #set($readmode = "disabled")
              #set($readmodeR = "readonly") 
              
              #else
              
              #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                
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
              
              #end        
                    
                        <td><p>#foreach($listpemohon in $listPemohon) </p>
                            <fieldset>
                            <legend id="maklumat_pemohon">MAKLUMAT PEMOHON</legend>
                            
                            
                            
                            <table width="100%">
                              <tr>
                                <td width="50%" valign="top"><input type="hidden" name="idPermohonan" value="$listpemohon.idPermohonan" />
                                    <input type="hidden" name="idPemohon" value="$listpemohon.idPemohon" />
                                     #set($status_pemohon = $!listpemohon.status_pemohon)
                                      <input type="hidden" name="status_pemohon" value="$!status_pemohon" />
                               
                                    <table width="100%" border="0">
                                      <tr id="individu_kp1">
                                        <td width="1%">&nbsp;</td>
                                        <td width="28%"><div align="right" class="style38">
                                          <div align="left"><span class="style38">MyID Baru</span></div>
                                        </div></td>
                                        <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td width="70%" class="style36">
                                        
                                          
                                           <input name="txtnoKpBaru1Pemohon" type="text" id="txtnoKpBaru1Pemohon" style="width: 50px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtnoKpBaru2Pemohon')" value="$listpemohon.noKpBaru1" size="7" maxlength="6"  onBlur="getAgeByIC(this,this.value,'txtUmurPemohon');getDOB(this.value)" $readmodeR class="$readmode" />
                                     - 
                                     <input name="txtnoKpBaru2Pemohon" type="text" id="txtnoKpBaru2Pemohon" style="width: 20px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtnoKpBaru3Pemohon')" value="$listpemohon.noKpBaru2" size="1" maxlength="2" $readmodeR class="$readmode" />
                                     -
                                     <input name="txtnoKpBaru3Pemohon" type="text" id="txtnoKpBaru3Pemohon"  style="width: 40px;" onkeyup="javascript:validateIC(event,this,this.value,'txtnoKpBaru3Pemohon')" onblur="jantinaic();getSaudara('txtNoKPLamaPemohon')"  value="$listpemohon.noKpBaru3" size="5" maxlength="4" $readmodeR class="$readmode" />     
                                     <input name="txdTarikhLahirPemohon" id="txdTarikhLahirPemohon" value="" type="hidden" />                                     #if($readmode != "disabled")
   #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
  <a href="http://www.jpn.gov.my" target="_blank" class="style51" >  www.jpn.gov.my</a>
  #end
  #end                                     </td>
                                      </tr>
                                         <tr id="individu_kp2">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">MyID Lama</span></div>
                                        </div></td>
                                        <td class="style36">:</td>
                                        <td class="style36"><label>
                                          <input name="txtNoKPLamaPemohon" type="text" id="txtNoKPLamaPemohon"  value="$listpemohon.noKpLama" size="12" maxlength="15" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
                                        </label></td>
                                      </tr>
                                      
                                 
                                      <tr id="individu_kp3">
                                        <td >&nbsp;</td>
                                        <td ><div align="right" class="style38">
                                          <div align="left"><span class="style38">MyID Lain</span></div>
                                        </div></td>
                                        <td width="1%" class="style36" ><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"> #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisKp=="5")
                                          #set($pkp="Tentera")
                                          
                                          #elseif($listpemohon.jenisKp=="6")
                                          #set($pkp="Polis")
                                          
                                          #elseif($listpemohon.jenisKp=="4")
                                          #set($pkp="Pasport")
                                          
                                          #elseif($listpemohon.jenisKp=="7")
                                          #set($pkp="Lain-lain")
                                          
                                          #elseif($listpemohon.jenisKp=="0")
                                          #set($pkp="")
                                          
                                          #else
                                          #set($pkp="")
                                          #end
                                          
                                          #if($pkp=="")
                                          <input name="textfield4" type="text" id="textfield"  size="12" $readmodeR class="$readmode"  onblur="this.value=this.value.toUpperCase()" />
                                          #else
                                          <input name="textfield4" type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="12" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socJenisKPLainPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="15" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socJenisKPLainPemohon" id="select" class="mediumselect" style="text-transform:uppercase;" onchange="kplain(this.value)"  onblur="kplainX(this.value)" />
                                            
                                            
                                      
									
								   #if($listpemohon.jenisKp=="5")
	                                 
                                      
                                            
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                           <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                         
                                             
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                            
                                            
                                      
	                              
	                               #elseif($listpemohon.jenisKp=="6")
	                                
                                      
                                            
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                            <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                            
                                            
                                      
	                              
								   #elseif($listpemohon.jenisKp=="4")
	                               
                                      
                                            
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                         
                                         
                                           #elseif($listpemohon.jenisKp=="7")
	                               
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
                                          #end
                                          
                                          <label></label>                                        </td>
                                      </tr>
                                      
                                      
                                #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($listpemohon.jenisKp != 0 && $listpemohon.jenisKp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
                              
                                     <tr id="individu_kp4">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">No. MyID Lain</span></div>
                                        </div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36">
                                        
                                        #if($readmode == "disabled")
                                          <input name="txtNoKPLainPemohon" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.noKpLain" size="20" maxlength="25"  $readmodeR class="$readmode"  />
                                        #else
                                       
                                      <input name="txtNoKPLainPemohon" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.noKpLain" size="20" maxlength="25" $readmodekp />
                                        #end                                        </td>
                                      </tr>
                                      
                                      <tr>
                                        <td valign="top">#if($readmode != "disabled" ) <span class="style38 style44">*</span>
                                            #end</td>
                                        <td><div align="right" class="style38">
                                          <div align="left">#if($readmode != "disabled" ) Nama Pemohon
                                            #else
                                            Nama Pemohon
                                            #end                                          </div>
                                        </div></td>
                                       <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label>
                                          <input name="txtNamaPemohonPemohon" type="text" id="textfield13" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.namaPemohon" size="45" maxlength="150" $readmodeR class="$readmode" />
                                        </label></td>
                                      </tr>
                                       <tr id="individu_jantina">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">Jantina</span></div>
                                        </div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label> 
                                        #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jantina=="1")
                                          #set($sexpemohon="Lelaki")
                                          
                                          
                                          #elseif($listpemohon.jantina=="2")
                                          #set($sexpemohon="Perempuan")
                                          
                                          #else
                                          #set($sexpemohon="")
                                          #end
                                          
                                          #if($sexpemohon=="")
                                          <input name="textfield" type="text" id="textfield2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" $readmodeR class="$readmode" />
                                          #else
                                          <input name="textfield" type="text" id="textfield2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$sexpemohon"  size="15" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socJantinaPemohon" type="hidden" id="textfield2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.jantina"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socJantinaPemohon" id="socJantinaPemohon" class="mediumselect" style="text-transform:uppercase;" onblur="uppercase()"   onchange="getSaudara('socTalianPersaudaraanPemohon')" >
                                            
                                         
                                   #if($listpemohon.jantina=="1")
	                               
                                      
                                            
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            
                                            
                                      
	                               #elseif($listpemohon.jantina=="2")
	                               
                                      
                                            
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            
                                            
                                      
	                               #else
	                               
                                      
                                            
                                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jantina</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            
                                            
                                      
	                               #end
                                     
                                    
                                    
                                          
                                          </select>
                                          #end </label></td>
                                      </tr>
                                     <tr id="individu_saudara">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">Talian Persaudaraan</span></div>
                                        </div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td>
                                        
                                          #if($listpemohon.idsaudara=="" || $listpemohon.idsaudara=="0" )
                                          
                                          #set($kodsaudara="")
                                          #else
                                          #foreach($listsau in $listsaudara)
                                           
                                          #if($listpemohon.idsaudara==$listsau.id_Saudara)
                                          
                                          #set($kodsaudara="$listsau.kod - $listsau.keterangan")
                                       
                                          #end    
                                          #end
                                          
                                          #end
                                          
                                          #if($readmode=="disabled")
                                          <input name="socTalianPersaudaraan" type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$kodsaudara"  size="45" $readmodeR class="$readmode" />
                                               <input name="socTalianPersaudaraanPemohon" type="hidden" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="textfield" value="$listpemohon.idsaudara"  size="34" $readmodeR class="$readmode" />
                                          #else
                                          
                                          
                                          #if($listpemohon.idsaudara!="")
                                          
                                          <select name="socTalianPersaudaraanPemohon" id="socTalianPersaudaraanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()">
                                          <option value="$listpemohon.idsaudara" style="text-transform:uppercase;" onblur="uppercase()">$kodsaudara</option>                                         
                                          
                                          #foreach($listsau in $listsaudara)
                                          #if($listpemohon.jantina=="1")
                                          #set($jan="01")
                                         
                                          #elseif($listpemohon.jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                          #if($listpemohon.idsaudara!=$listsau.id_Saudara)
                                          #if($listsau.jantina==$jan)
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>     
                                          #end
                                            #if($listsau.id_Saudara=="27" )
                                
	                               
                                              <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                              
                                   #end
                                          #end    
	                                      #end    
                                         </select>
                                         
                                          #end
                                          
                                           #if($listpemohon.idsaudara=="")
                                          <select name="socTalianPersaudaraanPemohon" id="socTalianPersaudaraanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()" onfocus="insertsaudara()">
                                          <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Talian Persaudaraan</option>
                                          
                                          #foreach($listsau in $listsaudara)
                                          
                                          #if($listpemohon.jantina=="1")
                                          #set($jan="01")
                                          
                                          #elseif($listpemohon.jantina=="2")
                                          #set($jan="02")
                                          
                                          #else
                                          
                                          #set($jan="")
                                        
                                          #end
                                          
                                          #if($jan!="")
                                          #if($listsau.jantina==$jan)
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>                                         
                                          #end
                                            #if($listsau.id_Saudara=="27" )
                                
	                               
                                              <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                              
                                   #end
                                          #else
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>                                 
                                          #end
                                          
                                            #end
                                          </select>
                                          
                                          #end
                                          
                                          
                                          #end                                          </td>
                                      </tr>
                                      <tr>
                                        <td valign="top">#if($readmode != "disabled" )#end</td>
                                        <td><div align="right" class="style38">
                                          <div align="left">#if($readmode != "disabled" ) Taraf Kepentingan
                                            #else 
                                            Taraf Kepentingan
                                            #end                                          </div>
                                        </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td>
                                          #if($listpemohon.idTarafkptg=="")                                          
                                          #set($tarafkePemohonan="")                                                          
                                          
                                          
                                          #else
                                          #foreach($listtar in $listtaraf)
                                          
                                          #if($listpemohon.idTarafkptg==$listtar.id_Tarafkptg)
                                          
                                          #set($tarafkePemohonan="$listtar.kod - $listtar.keterangan")
                                          #set($tarafkePemohonanid="$listtar.id_Tarafkptg")
                                          
                                          
                                          
                                          #end  
                                          
                                          
                                          
                                          #end
                                          #end
                                          
                                          #if($listpemohon.idTarafkptg!="" && $listpemohon.idTarafkptg!=0 && $listpemohon.idTarafkptg!="null" )
                                          #set($dahada="ada")
                                          #else
                                          #set($dahada="Xada")
                                          #end
                                          
                                          <!--
                                          ::::::: ID TARAF :$listpemohon.idTarafkptg   
                                          ::::::: ADA TARAF
                                          -->
                                          
                                        <!--  listPemohonOB size:::: $listPemohonOB.size() -->
                                          #if( $listPemohonOB.size()>0)
                                          #set($tmpp="ada")
                                          #else
                                          #set($tmpp="Xada")
                                          #end
                                        <!-- listPemohonOB size:::: --> <input name="adaob" type="hidden"  value="$tmpp" />
                                          
                                          
                                          
                                          <input name="adataraf" type="hidden"  value="$dahada" />
                                          #if($readmode=="disabled")
                                          <input name="textfield2" type="text" id="textfield3" style="text-transform:uppercase;" onblur="uppercase()" value="$tarafkePemohonan"  size="45" $readmodeR class="$readmode" />
                                          <input name="textfield2" type="hidden" id="textfield3" style="text-transform:uppercase;"  onblur="uppercase()" value="$listpemohon.idTarafkptg"  size="34" $readmodeR class="$readmode" />
                                          #else
                                          
                                          #if($listpemohon.idTarafkptg!="")
                                          <input type="hidden" name="socTarafKePemohonanpp2" value="$tarafkePemohonanid" />
                                           <input name="textfield2" type="text" id="textfield3" style="text-transform:uppercase;" onblur="uppercase()" value="$tarafkePemohonan"  size="45" readonly class="disabled" />
                                            <input type="hidden" name="socTarafKePemohonanPemohon" value="$tarafkePemohonanid" />
                                         
                                          #else
                                           <input name="textfield2" type="text" id="textfield3" style="text-transform:uppercase;" onblur="uppercase()" value=""  size="45" readonly class="disabled" />
                                            <input type="hidden" name="socTarafKePemohonanPemohon" value="$tarafkePemohonanid" />
                                          
                                          <!--
                                          <select name="socTarafKePemohonanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_taraf()" >
                                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Taraf Kepentingan</option>
                                            
                                            
                                        #foreach($listtar in $listtaraf)
                                        
                                            <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                            
                                        #end
                                       
                                          </select>
                                           -->
                                          #end
                                         
                                          #end </td>
                                      </tr>
                                      <tr id="individu_agama">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">Agama</span></div>
                                        </div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label> #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisAgama=="1")
                                          
                                          #set($agp="Islam")
                                          
                                          #elseif($listpemohon.jenisAgama=="2")
                                          
                                          #set($agp="Bukan Islam")
                                          
                                          #else
                                          
                                          #set($agp="")
                                          
                                          #end
                                          
                                          #if($agp=="")
                                          <input name="socAgamaPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #else
                                          <input name="socAgamaPe" type="text" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$agp"  size="34" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socAgamaPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.jenisAgama"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socAgamaPemohon" id="select3" class="mediumselect" style="text-transform:uppercase;" onblur="uppercase()">
                                            
                                      
                                   #if($listpemohon.jenisAgama=="1")
	                               
	                               
                                      
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            <option value="" style="text-transform:uppercase;" >Sila Pilih</option>
                                      
	                               #elseif($listpemohon.jenisAgama=="2")
	                               
                                      
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            <option value="" style="text-transform:uppercase;" >Sila Pilih</option>
                                      
	                               #else
	                               
                                      		<option value="" style="text-transform:uppercase;" >Sila Pilih</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            
                                      
	                               #end
                                      
                                    
                                    
                                          </select>
                                          #end </label></td>
                                      </tr>
                                     <tr id="individu_warga">
                                        <td valign="top">&nbsp;</td>
                                        <td valign="top"><div align="right" class="style38">
                                          <div align="left"><span class="style38">Warganegara</span></div>
                                        </div></td>
                                         <td width="1%" class="style36" valign="top"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36" valign="top"><label> #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisWarga=="1")
                                          
                                          #set($wrp="Warganegara")
                                          
                                          #elseif($listpemohon.jenisWarga=="2")
                                          
                                          #set($wrp="Bukan Warganegara")
                                          
                                          #elseif($listpemohon.jenisWarga=="3")
                                          
                                          #set($wrp="Pemastautin Tetap")
                                          
                                          #else
                                          #set($wrp="")
                                          
                                          #end
                                          
                                          #if($wrp=="")
                                          <input name="socWarganegaraPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #else
                                          <input name="socWarganegaraPe" type="text" id="textfield" value="$wrp" style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socWarganegaraPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.jenisWarga"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socWarganegaraPemohon" id="select4" class="autoselect" style="text-transform:uppercase;"  onchange="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" onblur="uppercase();alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" >
                                            
                                      
                                   #if($listpemohon.jenisWarga=="1")
	                               
                                      
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #elseif($listpemohon.jenisWarga=="2")
	                               
                                      
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #elseif($listpemohon.jenisWarga=="3")
	                               
                                      
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            
                                      
                                   #else
                                   
                                      
                                           <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #end
                                  
                                    
                                    
                                          </select>
                                          <div id="alamatwarga"></div>
                                          #end </label>
                                           
                                          </td>
                                      </tr>
                                       <tr id="individu_umur">
                                        <td valign="top" >#if($readmode != "disabled" ) <span class="style43">*</span>
                                            
                                            #end</td>
                                        <td><div align="right" class="style38">
                                          <div align="left">#if($readmode != "disabled" ) Umur
                                            
                                            #else
                                            Umur
                                            #end                                          </div>
                                        </div></td>
                                        <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label>
                                         <input name="txtUmurPemohon" type="text" style="text-transform:uppercase;" onblur="Checkumur()" id="txtUmurPemohon" value="$listpemohon.umur" size="2" maxlength="3" $readmodeR class="$readmode" onkeyup="javascript:validateIC(this,this.value,'txtUmurPemohon')" />
                                        
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38 style38" valign="top">#if($readmode != "disabled" ) 
                                          <span class="style38 style44">
                                             *
                                          
                                          </span>
                                          #end
                                          </td>
                                        <td class="style38 style38"><div align="left">#if($readmode != "disabled" ) </div>
                                          <div align="right" class="style38 style44">
                                              <div align="left" class="style52">Alamat Tetap</div>
                                          </div>
                                          <div align="left">#else
                                            Alamat Tetap
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                        <input name="txtAlamatTerakhir1Pemohon" type="text" class="$readmode" id="txtAlamatTerakhir"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.alamat1" size="45" maxlength="100"  $readmodeR />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">&nbsp;</td>
                                        <td class="style38"><div align="left"><span class="style3"><span class="style38"></span></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><label>
                                        <input name="txtAlamatTerakhir2Pemohon" type="text" class="$readmode"id="txtAlamatTerakhir2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.alamat2" size="45" maxlength="100" $readmodeR />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">&nbsp;</td>
                                        <td class="style38"><div align="left"><span class="style3"><span class="style38"></span></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><input name="txtAlamatTerakhir3Pemohon" type="text" class="$readmode" id="txtAlamatTerakhir3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.alamat3" size="45" maxlength="100" $readmodeR /></td>
                                      </tr>
                                      <tr>
                                        <td class="style38 style38" valign="top">#if($readmode != "disabled" )<span class="style44">*</span> #end</td>
                                        <td class="style38 style38"><div align="left">#if($readmode != "disabled" ) </div>
                                          <div align="right" class="style43">
                                              <div align="left" class="style52">Poskod</div>
                                          </div>
                                          <div align="left">#else
                                            Poskod
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td ><label>
                                        <input name="txtPoskodPemohon" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtPoskodPemohon" value="$listpemohon.poskod" size="5" maxlength="5" $readmodeR class="$readmode" onkeyup="javascript:validatePoskod(this,this.value)" />
                                        </label></td>
                                      </tr>
                                 
                                     <tr>
                                       <td class="style38 style38" valign="top" >#if($readmode != "disabled" ) <span class="style44">*</span> #end</td>
                                        <td class="style38 style38"><div align="left">#if($readmode != "disabled" ) </div>
                                          <div align="right" class="style43">
                                              <div align="left" class="style52">Negeri</div>
                                          </div>
                                          <div align="left">#else
                                            Negeri
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        #if($listpemohon.idnegeri=="" || $listpemohon.idnegeri=="0" )
                                        #set($kod="")
                                        
                                        #else
                                        
                                        #foreach($listneg in $listnegeri)      
                                        
                                        
                                        #if($listpemohon.idnegeri==$listneg.id_Negeri)
                                        
                                        #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
                                        
                                        
                                        #end
                                        #end
                                        
                                        #end
                                        <td>#if($readmode=="disabled")
                                          <input name="socNegeri" type="text" class="$readmode" id="textfield7" style="text-transform:uppercase;" onblur="uppercase()" value="$kod"  size="45" maxlength="100" $readmodeR />
#else                                     
                                        
                                        #if($listpemohon.idnegeri!="" && $listpemohon.idnegeri!=0)
                                        <select name="socNegeriPemohon" class="autoselect" id="socNegeriPemohon"  onchange="getBandarTetap('txtBandarPemohon')" >
                                          <option value="$listpemohon.idnegeri" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                          
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listpemohon.idnegeri!=$listneg.id_Negeri)                                  
                                  
                       
                                          <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                          
                                     
                                  #end    
	                              #end
                                        
                                        </select>
#else
<select name="socNegeriPemohon" class="autoselect"  onchange="getBandarTetap('txtBandarPemohon')">
  <option value="0" >SILA PILIH NEGERI</option>
  
                                  #foreach($listneg in $listnegeri)
                   
  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
  
                                    
	                               #end
                                        
</select>
#end   
                                                                             
                                        #end </td>
                                      </tr>
                                      
                                      
                                      
                                      
                                      
                                      
                                      
                                      
                                                            
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
                                    <td>Negara (Alamat)</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' class="$readmode" $readmodeR list = 'datalist'  value="$listpemohon.nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    </td>
        </tr>
                                      
                                      
                                      
                                      
                                      
                                      
                                      #set($bandartetap = $listpemohon.bandartetap)
                                      
                                      <tr>
                                        <td class="style38 style38" valign="top" >#if($readmode != "disabled" )#end</td>
                                        <td class="style38 style38"><div align="left">#if($readmode != "disabled" ) </div>
                                          <div align="right" class="style43">
                                              <div align="left" class="style52">Bandar</div>
                                          </div>
                                          <div align="left">#else
                                            Bandar
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                        
                                              
                             
                                    #if($bandartetap == "" || $bandartetap=="0")
                             #set($kodbx="")
                             
                             #else
                             
                             #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($bandartetap==$listneg.id)                                      
                                      #set($kodbx="$listneg.kod - $listneg.nama")
                                                                      
                                      #end
                             #end
                             
                             #end
                                
                                        
                                        
                                        #if($readmode=="disabled")
                      <input name="socNegeri12" type="text" class="$readmode" id="textfield7"  style="text-transform:uppercase;" onblur="uppercase()" value="$kodbx"  size="45" maxlength="100" $readmodeR />        
                      #else                
                                        
                                        #foreach($listdaerah in $listBandarTetapbyNegeri)
            
            #if($bandartetap==$listdaerah.id)
            
            #set($listDaerahbyNegeriK=$listdaerah.kod)
            #set($listDaerahbyNegeriN=$listdaerah.nama)
            
            
            
            #end 
            #end
            
            
            #if($disabled=="disabled")
            #set($readmodedaerah="disabled")
            #end
            #if($bandartetap!="" && $bandartetap!="0" )
            <select name="txtBandarPemohon" id="txtBandarPemohon" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
              <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            </select>
#else
<select name="txtBandarPemohon" id="txtBandarPemohon" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
  <option value="">Sila Pilih Bandar</option>
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            
</select>
#end
#end
 </label></td>
                                      </tr>
                                    </table></td>
                                <td valign="top"><table width="100%">
#if($readmode != "disabled")
<tr>
  <td width="1%" class="style38">&nbsp;</td>
                                      <td width="28%" class="style38"><div align="left"></div></td>
                                      <td class="style36">&nbsp;</td>
                                      <td width="70%"><label>
                                    #if($check_copy == "on")
                                    #set($ch_copy = "checked")
                                    #else
                                    #set($ch_copy = "")
                                    #end
                                      
                                        <input type="checkbox" name="copy" id="copy" $ch_copy onclick="copycopyX('maklumat_pemohon')"  />
                                        <span class="style50" >Alamat surat menyurat adalah alamat tetap</span></label></td>
                                    </tr>

                                      #end
                                    
                                      <tr>
                                        <td class="style38" valign="top">#if($readmode != "disabled" ) <span class="style44">*</span> #end</td>
                                        <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                                        <div align="right" class="style38 style44">
                                              <div align="left" class="style52">Alamat Surat</div>
                                        </div>
                                          <div align="left">#else
                                            Alamat Surat
                                          #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                      
                                          <input name="txtAlamatTerakhir1PemohonSurat" type="text" class="$readmode" id="txtAlamatTerakhir1PemohonSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.alamat1Surat" size="45" maxlength="100"   $readmodeR />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">&nbsp;</td>
                                        <td class="style38"><div align="left"><span class="style3"></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><label>
                                          <input name="txtAlamatTerakhir2PemohonSurat" type="text" class="$readmode" id="txtAlamatTerakhir2PemohonSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.alamat2Surat" size="45" maxlength="100"  $readmodeR />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">&nbsp;</td>
                                        <td class="style38"><div align="left"><span class="style3"></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><input name="txtAlamatTerakhir3PemohonSurat" type="text" class="$readmode" id="txtAlamatTerakhir3PemohonSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.alamat3Surat" size="45" maxlength="100" $readmodeR /></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">#if($readmode != "disabled" ) <span class="style44">*</span> #end</td>
                                        <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                                        <div align="right" class="style43">
                                              <div align="left" class="style52">Poskod</div>
                                        </div>
                                          <div align="left">#else
                                            Poskod
                                          #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td ><label>
                                          <input name="txtPoskodPemohonSurat" type="text" style="text-transform:uppercase;" onblur="uppercase()" id="txtPoskodPemohonSurat" value="$listpemohon.poskodSurat" size="5" maxlength="5" $readmodeR class="$readmode" onKeyUp="javascript:validatePoskod(this,this.value)" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38" valign="top">#if($readmode != "disabled" ) <span class="style44">*</span> #end</td>
                                        <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                                        <div align="right" class="style43">
                                              <div align="left" class="style52">Negeri</div>
                                        </div>
                                          <div align="left">#else
                                            Negeri
                                          #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        #if($listpemohon.idnegeriSurat=="" || $listpemohon.idnegeriSurat=="0" )
                                        #set($kod="")
                                        
                                        #else
                                        
                                        #foreach($listneg in $listnegeri)      
                                        
                                        
                                        #if($listpemohon.idnegeriSurat==$listneg.id_Negeri)
                                        
                                        #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
                                        
                                        
                                        #end
                                        #end
                                        
                                        #end
                                        <td> #if($readmode=="disabled")
                                          <input name="textfield5" type="text" class="$readmode" id="textfield6" style="text-transform:uppercase;" onblur="uppercase()" value="$kod"  size="45" maxlength="100" $readmodeR />
                                          #else   
                                          
                                          
                                          #if($listpemohon.idnegeriSurat!="" && $listpemohon.idnegeriSurat!=0)
                                          <select name="socNegeriPemohonSurat" class="autoselect" id="socNegeriPemohonSurat" onchange="getBandarSurat('txtBandarPemohonSurat')" >
                                            <option value="$listpemohon.idnegeriSurat" style="text-transform:uppercase;" onblur="uppercase()" >$kod</option>
                                            
                                            
                                            
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listpemohon.idnegeriSurat!=$listneg.id_Negeri)
                                  
                                  
                       
                                            
                                            
                                            <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                            
                                            
                                            
                                     
                                  #end    
	                              #end
                                        
                                          
                                          
                                          </select>
                                          #else
                                          <select name="socNegeriPemohonSurat" id="socNegeriPemohonSurat" class="autoselect" onchange="getBandarSurat('txtBandarPemohonSurat')" >
                                            <option value="0" >SILA PILIH NEGERI</option>
                                            
                                            
                                            
                                  #foreach($listneg in $listnegeri)
                   
                                            
                                            
                                            <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                            
                                            
                                            
                                    
	                               #end
                                        
                                          
                                          
                                          </select>
                                          #end   
                                          
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
                                    <input type = 'text' id = 'nama_pelbagainegara_surat' name = 'nama_pelbagainegara_surat' size='30' maxlength='200' class="$readmode" $readmodeR list = 'datalist'  value="$listpemohon.nama_pelbagainegara_surat"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    </td>
        </tr>
        
                                      
                                      
                                      
                                      
                                      
                                      <tr>
                                        <td class="style38" valign="top">#if($readmode != "disabled" )#end</td>
                                        <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                                        <div align="right" class="style43">
                                              <div align="left" class="style52">Bandar</div>
                                        </div>
                                          <div align="left">#else
                                            Bandar
                                          #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                       
                                        
                                         #set($bandarsurat = $listpemohon.bandarsurat)
                                     
                                          #if($bandarsurat == "" || $bandarsurat=="0")
                             #set($kodbs="")
                             
                             #else
                             
                             #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($bandarsurat==$listneg.id)                                      
                                      #set($kodbs="$listneg.kod - $listneg.nama")
                                                                      
                                      #end
                             #end
                             
                             #end
                                   
                                   
                                   
                                    #if($readmode=="disabled" )
                      <input name="socBandarS" type="text" class="$readmode" id="socBandar"   style="text-transform:uppercase;" onblur="uppercase()" value="$kodbs"  size="45" maxlength="100" $readmodeR />                                        
                                        #else   
                                      
                                        
                                        #foreach($listdaerah in $listBandarSuratbyNegeri)
            
            #if($bandarsurat==$listdaerah.id)
            
            #set($listDaerahbyNegeriK=$listdaerah.kod)
            #set($listDaerahbyNegeriN=$listdaerah.nama)
            
            
            
            #end 
            #end
            
            
            #if($disabled=="disabled")
            #set($readmodedaerah="disabled")
            #end
            #if($bandarsurat!="" && $bandarsurat!="0" )
            <select name="txtBandarPemohonSurat" id="txtBandarPemohonSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
              <option value="$bandarsurat">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
              
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                 
                                  #if($bandarsurat!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              
              <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
              
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
            </select>
#else
<select name="txtBandarPemohonSurat" id="txtBandarPemohonSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
  <option value="">Sila Pilih Bandar</option>
  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

</select>
#end 
#end
</label></td>
                                      </tr>
                                    
                                     <tr>
                                       <td class="style38" >&nbsp;</td>
                                          <td class="style38" ><div align="left">No Telefon</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoTelefonPemohon" type="text" id="txtNoTelefonPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noTel" size="14" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" $readmodeR class="$readmode" /></td>
                                        </tr>
                                        #if($readmode != "disabled" )
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"></div></td>
                                          <td class="style36">&nbsp;</td>
                                          <td valign="top"><span class="style50">cth: 031234567</span></td>
                                        </tr>
                                        #end
                                        <tr id="no_hp">
                                          <td class="style38" >&nbsp;</td>
                                       
                                          <td class="style38" ><div align="left">No Telefon Bimbit</div></td>
                                           <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoTelefonBimbitPemohon" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTelefonBimbitPemohon')" type="text" id="txtNoTelefonBimbitPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noHp" size="14" maxlength="14" $readmodeR class="$readmode" /></td>
                                        </tr>
                                           #if($readmode != "disabled" )
                                        <tr id="no_hp_info">
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"></div></td>
                                          <td class="style36">&nbsp;</td>
                                          <td valign="top"><span class="style50">cth: 0121234567</span></td>
                                        </tr>
                                        #end
                                        <tr id="no_fax">
                                          <td class="style38" >&nbsp;</td>
                                          <td class="style38" ><div align="left">No Faks</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoFaksPemohon" type="text" id="txtNoFaksPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noFax" size="14" maxlength="12" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoFaksPemohon')" /></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"> <span class="style44">*</span> </td>
                                          <td class="style38" ><div align="left">Email</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>                                        
                                          <td>
                                            
                                        <input name="txtEmelPemohon" type="text" id="txtEmelPemohon"  value="$listpemohon.emel" size="34" maxlength="50" $readmodeR class="$readmode" />                                        </td>
                                          </tr>
                                        #if($readmode != "disabled" )
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"></div></td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top" height="1"><span class="style50">cth: abc@email.com </span></td>
                                        </tr>
                                        #end
                                        
                                          #if($!skrin_online_17 == "yes")
                                        
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left">Diwakili Peguam</div></td>
                                          <td valign="top">:</td>
                                          <td valign="top" height="1"> 
                                          
                                           #if($listpemohon.status_peguam=="Y")
                                            #set($chq="checked")
                                            #else
                                            #set($chq="")
                                            #end
                                          
                                          <input name="status_peguam" type="checkbox" value="Y" $readmode $chq >
                                        <input name="peguam" type="hidden" value=""> </td>
                                        </tr>
                                        #else
                                          <input name="status_peguam" type="hidden" value="$listpemohon.status_peguam">
                                        #end
                                        
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="right" class="style38">
                                            <div align="left">Catatan</div>
                                          </div></td>
                                          <td width="1%" class="style36" valign="top"><div align="right"><span class="style38">:</span></div></td>
                                          <td><textarea name="txtCatatanPemohon"  cols="31" rows="3" id="txtCatatanPemohon" $readmodeR class="$readmode" >$listpemohon.catatan</textarea></td>
                                      </tr>
                                </table></td>
                              </tr>
                            </table>
                            
                              <!--
                              // 17 yg asal sebelum ubah *
                              
                              <table width="100%">
                              <tr>
                                <td width="50%" valign="top"><input type="hidden" name="idPermohonan" value="$listpemohon.idPermohonan" />
                                    <input type="hidden" name="idPemohon" value="$listpemohon.idPemohon" />
                               
                                    <table width="100%" border="0">
                                      <tr>
                                        <td width="29%"><div align="right"><span class="style38">MyID Baru</span></div></td>
                                        <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td width="70%" class="style36">
                                        
                                          
                                           <input name="txtnoKpBaru1Pemohon" type="text" id="txtnoKpBaru1Pemohon" style="width: 50px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtnoKpBaru2Pemohon')" value="$listpemohon.noKpBaru1" size="7" maxlength="6"  onBlur="getAgeByIC(this,this.value,'txtUmurPemohon');getDOB(this.value)" $readmodeR class="$readmode" />
                                     - 
                                     <input name="txtnoKpBaru2Pemohon" type="text" id="txtnoKpBaru2Pemohon" style="width: 20px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtnoKpBaru3Pemohon')" value="$listpemohon.noKpBaru2" size="1" maxlength="2" $readmodeR class="$readmode" />
                                     -
                                     <input name="txtnoKpBaru3Pemohon" type="text" id="txtnoKpBaru3Pemohon"  style="width: 40px;" onkeyup="javascript:validateIC(event,this,this.value,'txtnoKpBaru3Pemohon')" onblur="jantinaic();getSaudara('txtNoKPLamaPemohon')"  value="$listpemohon.noKpBaru3" size="5" maxlength="4" $readmodeR class="$readmode" />     
                                     <input name="txdTarikhLahirPemohon" id="txdTarikhLahirPemohon" value="" type="hidden" />                                     #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style51" >  www.jpn.gov.my</a>
  #end                                     </td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">MyID Lama</span></div></td>
                                        <td class="style36">:</td>
                                        <td class="style36"><label>
                                          <input name="txtNoKPLamaPemohon" type="text" id="txtNoKPLamaPemohon"  value="$listpemohon.noKpLama" size="12" maxlength="8" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
                                        </label></td>
                                      </tr>
                                      
                                 
                                      <tr>
                                        <td ><div align="right"><span class="style38">MyID Lain</span></div></td>
                                        <td width="1%" class="style36" ><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"> #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisKp=="5")
                                          #set($pkp="Tentera")
                                          
                                          #elseif($listpemohon.jenisKp=="6")
                                          #set($pkp="Polis")
                                          
                                          #elseif($listpemohon.jenisKp=="4")
                                          #set($pkp="Pasport")
                                          
                                          #elseif($listpemohon.jenisKp=="0")
                                          #set($pkp="")
                                          
                                          #else
                                          #set($pkp="")
                                          #end
                                          
                                          #if($pkp=="")
                                          <input name="textfield4" type="text" id="textfield"  size="12" $readmodeR class="$readmode"  onblur="this.value=this.value.toUpperCase()" />
                                          #else
                                          <input name="textfield4" type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="12" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socJenisKPLainPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="15" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socJenisKPLainPemohon" id="select" class="mediumselect" style="text-transform:uppercase;" onchange="kplain(this.value)"  onblur="kplainX(this.value)" />
                                            
                                            
                                      
									
								   #if($listpemohon.jenisKp=="5")
	                                 
                                      
                                            
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                            
                                            
                                      
	                              
	                               #elseif($listpemohon.jenisKp=="6")
	                                
                                      
                                            
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                            
                                            
                                      
	                              
								   #elseif($listpemohon.jenisKp=="4")
	                               
                                      
                                            
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                            
                                            
                                      
	                               #else
	                                 
                                      
                                            
                                            <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                            
                                            
                                      
	                               #end
                                    
                                  
                                    
                                          
                                          </select>
                                          #end
                                          
                                          <label></label>                                        </td>
                                      </tr>
                                      
                                      
                                #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($listpemohon.jenisKp != 0 && $listpemohon.jenisKp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
                              
                                      <tr>
                                        <td><div align="right"><span class="style38">MyID Lain</span></div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36">
                                        
                                        #if($readmode == "disabled")
                                          <input name="txtNoKPLainPemohon" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.noKpLain" size="12" maxlength="12"  $readmodeR class="$readmode"  />
                                        #else
                                       
                                      <input name="txtNoKPLainPemohon" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.noKpLain" size="12" maxlength="12" $readmodekp />
                                        #end
                                        </td>
                                      </tr>
                                      
                                      <tr>
                                        <td><div align="right">#if($readmode != "disabled" ) <span class="style38 style44">Nama Pemohon</span>
                                          #else
                                          Nama Pemohon
                                          #end
                                        </div></td>
                                       <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label>
                                          <input name="txtNamaPemohonPemohon" type="text" id="textfield13" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.namaPemohon" size="34" maxlength="40" $readmodeR class="$readmode" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Jantina</span></div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label> 
                                        #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jantina=="1")
                                          #set($sexpemohon="Lelaki")
                                          
                                          
                                          #elseif($listpemohon.jantina=="2")
                                          #set($sexpemohon="Perempuan")
                                          
                                          #else
                                          #set($sexpemohon="")
                                          #end
                                          
                                          #if($sexpemohon=="")
                                          <input name="textfield" type="text" id="textfield2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" $readmodeR class="$readmode" />
                                          #else
                                          <input name="textfield" type="text" id="textfield2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$sexpemohon"  size="15" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socJantinaPemohon" type="hidden" id="textfield2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.jantina"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socJantinaPemohon" id="socJantinaPemohon" class="mediumselect" style="text-transform:uppercase;" onblur="uppercase()"   onchange="getSaudara('socTalianPersaudaraanPemohon')" >
                                            
                                         
                                   #if($listpemohon.jantina=="1")
	                               
                                      
                                            
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            
                                            
                                      
	                               #elseif($listpemohon.jantina=="2")
	                               
                                      
                                            
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            
                                            
                                      
	                               #else
	                               
                                      
                                            
                                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jantina</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            
                                            
                                      
	                               #end
                                     
                                    
                                    
                                          
                                          </select>
                                          #end </label></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Talian Persaudaraan</span></div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td>
                                        
                                          #if($listpemohon.idsaudara=="" || $listpemohon.idsaudara=="0" )
                                          
                                          #set($kodsaudara="")
                                          #else
                                          #foreach($listsau in $listsaudara)
                                           
                                          #if($listpemohon.idsaudara==$listsau.id_Saudara)
                                          
                                          #set($kodsaudara="$listsau.kod - $listsau.keterangan")
                                       
                                          #end    
                                          #end
                                          
                                          #end
                                          
                                          #if($readmode=="disabled")
                                          <input name="socTalianPersaudaraan" type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$kodsaudara"  size="34" $readmodeR class="$readmode" />
                                               <input name="socTalianPersaudaraanPemohon" type="hidden" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="textfield" value="$listpemohon.idsaudara"  size="34" $readmodeR class="$readmode" />
                                          #else
                                          
                                          
                                          #if($listpemohon.idsaudara!="")
                                          
                                          <select name="socTalianPersaudaraanPemohon" id="socTalianPersaudaraanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()">
                                          <option value="$listpemohon.idsaudara" style="text-transform:uppercase;" onblur="uppercase()">$kodsaudara</option>                                         
                                          
                                          #foreach($listsau in $listsaudara)
                                          #if($listpemohon.jantina=="1")
                                          #set($jan="01")
                                         
                                          #elseif($listpemohon.jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                          #if($listpemohon.idsaudara!=$listsau.id_Saudara)
                                          #if($listsau.jantina==$jan)
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>     
                                          #end
                                          #end    
	                                      #end    
                                         </select>
                                         
                                          #end
                                          
                                           #if($listpemohon.idsaudara=="")
                                          <select name="socTalianPersaudaraanPemohon" id="socTalianPersaudaraanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()" onfocus="insertsaudara()">
                                          <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Talian Persaudaraan</option>
                                          
                                          #foreach($listsau in $listsaudara)
                                          
                                          #if($listpemohon.jantina=="1")
                                          #set($jan="01")
                                          
                                          #elseif($listpemohon.jantina=="2")
                                          #set($jan="02")
                                          
                                          #else
                                          
                                          #set($jan="")
                                        
                                          #end
                                          
                                          #if($jan!="")
                                          #if($listsau.jantina==$jan)
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>                                         
                                          #end
                                          #else
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>                                 
                                          #end
                                          
                                            #end
                                          </select>
                                          
                                          #end
                                          
                                          
                                          #end                                          </td>
                                      </tr>
                                      <tr>
                                        <td><div align="right">
                                        #if($readmode != "disabled" ) 
                                        <span class="style38 style44">Taraf Kepentingan</span>
                                       #else 
                                        Taraf Kepentingan
                                        #end
                                        
                                        </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td> #if($listpemohon.idTarafkptg=="")                                          
                                          #set($tarafkePemohonan="")                                                          
                                          
                                          
                                          #else
                                          #foreach($listtar in $listtaraf)
                                          
                                          #if($listpemohon.idTarafkptg==$listtar.id_Tarafkptg)

                                          
                                          #set($tarafkePemohonan="$listtar.kod - $listtar.keterangan")
                                          #set($tarafkePemohonanid="$listtar.id_Tarafkptg")
                                          
                                          
                                          
                                          #end  
                                          
                                          
                                          
                                          #end
                                          #end
                                          
                                          #if($listpemohon.idTarafkptg!="" && $listpemohon.idTarafkptg!=0 && $listpemohon.idTarafkptg!="null" )
                                          #set($dahada="ada")
                                          #else
                                          #set($dahada="Xada")
                                          #end
                                          
                                         
                                          #if( $listPemohonOB.size()>0)
                                          #set($tmpp="ada")
                                          #else
                                          #set($tmpp="Xada")
                                          #end
                                      <input name="adaob" type="hidden"  value="$tmpp" />
                                          
                                          
                                          
                                          <input name="adataraf" type="hidden"  value="$dahada" />
                                          #if($readmode=="disabled")
                                          <input name="textfield2" type="text" id="textfield3" style="text-transform:uppercase;" onblur="uppercase()" value="$tarafkePemohonan"  size="34" $readmodeR class="$readmode" />
                                          <input name="textfield2" type="hidden" id="textfield3" style="text-transform:uppercase;"  onblur="uppercase()" value="$listpemohon.idTarafkptg"  size="34" $readmodeR class="$readmode" />
                                          #else
                                          
                                          #if($listpemohon.idTarafkptg!="")
                                          <input type="hidden" name="socTarafKePemohonanpp2" value="$tarafkePemohonanid" />
                                              <select name="socTarafKePemohonanPemohon" class="largeselect;disabled" style="text-transform:uppercase;"  onblur="uppercase()" >
                                                <option value="$tarafkePemohonanid" style="text-transform:uppercase;"  onblur="uppercase()">$tarafkePemohonan</option>
                                                                                       
                                          #foreach($listtar in $listtaraf)
                                          
                                                <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                                
                                          #end  
                                          
                                              </select>
                                          #else
                                          <select name="socTarafKePemohonanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()" >
                                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Taraf Kepentingan</option>
                                            
                                            
                                        #foreach($listtar in $listtaraf)
                                        
                                            <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                            
                                        #end
                                       
                                          </select>
                                          #end
                                          
                                          #end </td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Agama</span></div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label> #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisAgama=="1")
                                          
                                          #set($agp="Islam")
                                          
                                          #elseif($listpemohon.jenisAgama=="2")
                                          
                                          #set($agp="Bukan Islam")
                                          
                                          #else
                                          
                                          #set($agp="")
                                          
                                          #end
                                          
                                          #if($agp=="")
                                          <input name="socAgamaPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #else
                                          <input name="socAgamaPe" type="text" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$agp"  size="34" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socAgamaPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.jenisAgama"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socAgamaPemohon" id="select3" class="mediumselect" style="text-transform:uppercase;" onblur="uppercase()">
                                            
                                      
                                   #if($listpemohon.jenisAgama=="1")
	                               
	                               
                                      
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            
                                      
	                               #elseif($listpemohon.jenisAgama=="2")
	                               
                                      
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            
                                      
	                               #else
	                               
                                      
                                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Agama</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            
                                      
	                               #end
                                      
                                    
                                    
                                          </select>
                                          #end </label></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Warganegara</span></div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label> #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisWarga=="1")
                                          
                                          #set($wrp="Warganegara")
                                          
                                          #elseif($listpemohon.jenisWarga=="2")
                                          
                                          #set($wrp="Bukan Warganegara")
                                          
                                          #elseif($listpemohon.jenisWarga=="3")
                                          
                                          #set($wrp="Pemastautin Tetap")
                                          
                                          #else
                                          #set($wrp="")
                                          
                                          #end
                                          
                                          #if($wrp=="")
                                          <input name="socWarganegaraPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #else
                                          <input name="socWarganegaraPe" type="text" id="textfield" value="$wrp" style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socWarganegaraPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.jenisWarga"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socWarganegaraPemohon" id="select4" class="autoselect" style="text-transform:uppercase;" onblur="uppercase()">
                                            
                                      
                                   #if($listpemohon.jenisWarga=="1")
	                               
                                      
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #elseif($listpemohon.jenisWarga=="2")
	                               
                                      
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #elseif($listpemohon.jenisWarga=="3")
	                               
                                      
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            
                                      
                                   #else
                                   
                                      
                                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Warganegara</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #end
                                  
                                    
                                    
                                          </select>
                                          #end </label></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right">#if($readmode != "disabled" ) <span class="style43">Umur</span>
                                          
                                          #else
                                          Umur
                                          #end
                                        </div></td>
                                        <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label>
                                         <input name="txtUmurPemohon" type="text" style="text-transform:uppercase;" onblur="Checkumur()" id="txtUmurPemohon" value="$listpemohon.umur" size="2" maxlength="3" $readmodeR class="$readmode" onkeyup="javascript:validateIC(this,this.value,'txtUmurPemohon')" />
                                        
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">#if($readmode != "disabled" ) </div>
                                            <div align="right" class="style38 style44">
                                              <div align="right">Alamat Tetap</div>
                                            </div>
                                          <div align="right">#else
                                            Alamat Tetap
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                        <input name="txtAlamatTerakhir1Pemohon"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir" value="$listpemohon.alamat1" size="34"  $readmodeR class="$readmode" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><label>
                                        <input name="txtAlamatTerakhir2Pemohon" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"id="txtAlamatTerakhir2"  value="$listpemohon.alamat2" size="34" $readmodeR class="$readmode" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><input name="txtAlamatTerakhir3Pemohon" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtAlamatTerakhir3" value="$listpemohon.alamat3" size="34" $readmodeR class="$readmode" /></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">#if($readmode != "disabled" ) </div>
                                            <div align="right" class="style43">
                                              <div align="right">Poskod</div>
                                            </div>
                                          <div align="right">#else
                                            Poskod
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td ><label>
                                        <input name="txtPoskodPemohon" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtPoskodPemohon" value="$listpemohon.poskod" size="5" maxlength="5" $readmodeR class="$readmode" onkeyup="javascript:validatePoskod(this,this.value)" />
                                        </label></td>
                                      </tr>
                                 
                                     <tr>
                                        <td class="style38"><div align="right">#if($readmode != "disabled" ) </div>
                                            <div align="right" class="style43">
                                              <div align="right">Negeri</div>
                                            </div>
                                          <div align="right">#else
                                            Negeri
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        #if($listpemohon.idnegeri=="" || $listpemohon.idnegeri=="0" )
                                        #set($kod="")
                                        
                                        #else
                                        
                                        #foreach($listneg in $listnegeri)      
                                        
                                        
                                        #if($listpemohon.idnegeri==$listneg.id_Negeri)
                                        
                                        #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
                                        
                                        
                                        #end
                                        #end
                                        
                                        #end
                                        <td>#if($readmode=="disabled")
                                          <input name="socNegeri" type="text" id="textfield7" style="width: 265px; text-transform:uppercase;" onblur="uppercase()" value="$kod"  size="34" $readmodeR class="$readmode" />
#else                                     
                                        
                                        #if($listpemohon.idnegeri!="" && $listpemohon.idnegeri!=0)
                                        <select name="socNegeriPemohon" class="autoselect" id="socNegeriPemohon"  onchange="getBandarTetap('txtBandarPemohon')" >
                                          <option value="$listpemohon.idnegeri" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                          
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listpemohon.idnegeri!=$listneg.id_Negeri)                                  
                                  
                       
                                          <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                          
                                     
                                  #end    
	                              #end
                                        
                                        </select>
#else
<select name="socNegeriPemohon" class="autoselect"  onchange="getBandarTetap('txtBandarPemohon')">
  <option value="" >SILA PILIH NEGERI</option>
  
                                  #foreach($listneg in $listnegeri)
                   
  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
  
                                    
	                               #end
                                        
</select>
#end   
                                                                             
                                        #end </td>
                                      </tr>
                                      
                                      #set($bandartetap = $listpemohon.bandartetap)
                                      
                                      <tr>
                                        <td class="style38"><div align="right">#if($readmode != "disabled" ) </div>
                                            <div align="right" class="style43">
                                              <div align="right">Bandar</div>
                                            </div>
                                          <div align="right">#else
                                            Bandar
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                        
                                              
                             
                                    #if($bandartetap == "" || $bandartetap=="0")
                             #set($kodbx="")
                             
                             #else
                          
                             #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($bandartetap==$listneg.id)                                      
                                      #set($kodbx="$listneg.kod - $listneg.nama")
                                                                      
                                      #end
                             #end
                             
                             #end
                                
                                        
                                        
                                        #if($readmode=="disabled")
                      <input name="socNegeri12" type="text" id="textfield7"  style="width: 265px; text-transform:uppercase;" onblur="uppercase()" value="$kodbx"  size="34" $readmodeR class="$readmode" />        
                      #else                
                                        
                                        #foreach($listdaerah in $listBandarTetapbyNegeri)
            
            #if($bandartetap==$listdaerah.id)
            
            #set($listDaerahbyNegeriK=$listdaerah.kod)
            #set($listDaerahbyNegeriN=$listdaerah.nama)
            
            
            
            #end 
            #end
            
            
            #if($disabled=="disabled")
            #set($readmodedaerah="disabled")
            #end
            #if($bandartetap!="" && $bandartetap!="0" )
            <select name="txtBandarPemohon" id="txtBandarPemohon" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
              <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            </select>
#else
<select name="txtBandarPemohon" id="txtBandarPemohon" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
  <option value="">Sila Pilih Bandar</option>
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            
</select>
#end
#end
 </label></td>
                                      </tr>
                                      
                                    </table></td>
                                <td valign="top"><table width="100%">
#if($readmode != "disabled")
<tr>
                                      <td width="29%" class="style38">&nbsp;</td>
                                      <td class="style36">&nbsp;</td>
                                      <td width="70%"><label>
                                    #if($check_copy == "on")
                                    #set($ch_copy = "checked")
                                    #else
                                    #set($ch_copy = "")
                                    #end
                                      
                                        <input type="checkbox" name="copy" id="copy" $ch_copy onclick="copycopyX('maklumat_pemohon')"  />
                                        <span class="style50" >Alamat surat menyurat adalah alamat tetap</span></label></td>
                                    </tr>

                                      #end
                                    
                                      <tr>
                                        <td class="style38"><div align="right">#if($readmode != "disabled" ) </div>
                                            <div align="right" class="style38 style44">
                                              <div align="right">Alamat Surat Menyurat</div>
                                            </div>
                                          <div align="right">#else
                                            Alamat Surat Menyurat
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                          <input name="txtAlamatTerakhir1PemohonSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1PemohonSurat" value="$listpemohon.alamat1Surat" size="34"   $readmodeR class="$readmode" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><label>
                                          <input name="txtAlamatTerakhir2PemohonSurat" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtAlamatTerakhir2PemohonSurat"  value="$listpemohon.alamat2Surat" size="34"  $readmodeR class="$readmode" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><input name="txtAlamatTerakhir3PemohonSurat" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtAlamatTerakhir3PemohonSurat" value="$listpemohon.alamat3Surat" size="34" $readmodeR class="$readmode" /></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">#if($readmode != "disabled" ) </div>
                                            <div align="right" class="style43">
                                              <div align="right">Poskod</div>
                                            </div>
                                          <div align="right">#else
                                            Poskod
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td ><label>
                                          <input name="txtPoskodPemohonSurat" type="text" style="text-transform:uppercase;" onblur="uppercase()" id="txtPoskodPemohonSurat" value="$listpemohon.poskodSurat" size="5" maxlength="5" $readmodeR class="$readmode" onKeyUp="javascript:validatePoskod(this,this.value)" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">#if($readmode != "disabled" ) </div>
                                            <div align="right" class="style43">
                                              <div align="right">Negeri</div>
                                            </div>
                                          <div align="right">#else
                                            Negeri
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        #if($listpemohon.idnegeriSurat=="" || $listpemohon.idnegeriSurat=="0" )
                                        #set($kod="")
                                        
                                        #else
                                        
                                        #foreach($listneg in $listnegeri)      
                                        
                                        
                                        #if($listpemohon.idnegeriSurat==$listneg.id_Negeri)
                                        
                                        #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
                                        
                                        
                                        #end
                                        #end
                                        
                                        #end
                                        <td> #if($readmode=="disabled")
                                          <input name="textfield5" type="text" id="textfield6" style="width: 265px; text-transform:uppercase;" onblur="uppercase()" value="$kod"  size="34" $readmodeR class="$readmode" />
                                          #else   
                                          
                                          
                                          #if($listpemohon.idnegeriSurat!="" && $listpemohon.idnegeriSurat!=0)
                                          <select name="socNegeriPemohonSurat" class="autoselect" id="socNegeriPemohonSurat" onchange="getBandarSurat('txtBandarPemohonSurat')" >
                                            <option value="$listpemohon.idnegeriSurat" style="text-transform:uppercase;" onblur="uppercase()" >$kod</option>
                                            
                                            
                                            
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listpemohon.idnegeriSurat!=$listneg.id_Negeri)
                                  
                                  
                       
                                            
                                            
                                            <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                            
                                            
                                            
                                     
                                  #end    
	                              #end
                                        
                                          
                                          
                                          </select>
                                          #else
                                          <select name="socNegeriPemohonSurat" id="socNegeriPemohonSurat" class="autoselect" onchange="getBandarSurat('txtBandarPemohonSurat')" >
                                            <option value="" >SILA PILIH NEGERI</option>
                                            
                                            
                                            
                                  #foreach($listneg in $listnegeri)
                   
                                            
                                            
                                            <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                            
                                            
                                            
                                    
	                               #end
                                        
                                          
                                          
                                          </select>
                                          #end   
                                          
                                          #end </td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">#if($readmode != "disabled" ) </div>
                                            <div align="right" class="style43">
                                              <div align="right">Bandar</div>
                                            </div>
                                          <div align="right">#else
                                            Bandar
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                       
                                        
                                         #set($bandarsurat = $listpemohon.bandarsurat)
                                     
                                          #if($bandarsurat == "" || $bandarsurat=="0")
                             #set($kodbs="")
                             
                             #else
                             
                             #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($bandarsurat==$listneg.id)                                      
                                      #set($kodbs="$listneg.kod - $listneg.nama")
                                                                      
                                      #end
                             #end
                             
                             #end
                                   
                                   
                                   
                                    #if($readmode=="disabled" )
                      <input name="socBandarS" type="text" id="socBandar"   style="width: 265px; text-transform:uppercase;" onblur="uppercase()" value="$kodbs"  size="34" $readmodeR class="$readmode" />                                        
                                        #else   
                                      

                                        
                                        #foreach($listdaerah in $listBandarSuratbyNegeri)
            
            #if($bandarsurat==$listdaerah.id)
            
            #set($listDaerahbyNegeriK=$listdaerah.kod)
            #set($listDaerahbyNegeriN=$listdaerah.nama)
            
            
            
            #end 
            #end
            
            
            #if($disabled=="disabled")
            #set($readmodedaerah="disabled")
            #end
            #if($bandarsurat!="" && $bandarsurat!="0" )
            <select name="txtBandarPemohonSurat" id="txtBandarPemohonSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
              <option value="$bandarsurat">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
              
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                 
                                  #if($bandarsurat!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              
              <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
              
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
            </select>
#else
<select name="txtBandarPemohonSurat" id="txtBandarPemohonSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
  <option value="">Sila Pilih Bandar</option>
  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

</select>
#end 
#end
</label></td>
                                      </tr>
                                    
                                     <tr>
                                          <td class="style38" ><div align="right">No Telefon</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoTelefonPemohon" type="text" id="txtNoTelefonPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noTel" size="14" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" $readmodeR class="$readmode" /></td>
                                        </tr>
                                        #if($readmode != "disabled" )
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style36">&nbsp;</td>
                                          <td valign="top"><span class="style50">cth: 031234567</span></td>
                                        </tr>
                                        #end
                                        <tr>
                                       
                                          <td class="style38" ><div align="right">No Telefon Bimbit</div></td>
                                           <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoTelefonBimbitPemohon" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTelefonBimbitPemohon')" type="text" id="txtNoTelefonBimbitPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noHp" size="14" maxlength="14" $readmodeR class="$readmode" /></td>
                                        </tr>
                                           #if($readmode != "disabled" )
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style36">&nbsp;</td>
                                          <td valign="top"><span class="style50">cth: 0121234567</span></td>
                                        </tr>
                                        #end
                                        <tr>
                                          <td class="style38" ><div align="right">No Faks</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoFaksPemohon" type="text" id="txtNoFaksPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noFax" size="14" maxlength="12" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoFaksPemohon')" /></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" ><div align="right">Email</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>                                        
                                          <td>
                                            
                                        <input name="txtEmelPemohon" type="text" id="txtEmelPemohon"  value="$listpemohon.emel" size="34" maxlength="50" $readmodeR class="$readmode" />                                        </td>
                                          </tr>
                                        #if($readmode != "disabled" )
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top" height="1"><span class="style50">cth: abc@email.com </span></td>
                                        </tr>
                                        #end
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan</div></td>
                                          <td width="1%" class="style36" valign="top"><div align="right"><span class="style38">:</span></div></td>
                                          <td><textarea name="txtCatatanPemohon" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" cols="31" rows="3" id="txtCatatanPemohon" $readmodeR class="$readmode" >$listpemohon.catatan</textarea></td>
                                      </tr>
                                </table></td>
                              </tr>
                            </table>
                              -->
                              </fieldset>
                              #if($readmode != "disabled")
                              <table width="100%">
                                <tr>
                                  <td><span class="style5 style47"><span class="style44">Perhatian </span>: Sila masukkan salah satu nombor MyID  dan pastikan label bertanda <span class="style44">*</span> diisi.</span></td>
                                </tr>
</table>
#end

                              
                              
                          <p> #end </p></td>
                      </tr>
                   #if($tpemohon != 6 && $tpemohon != 8)   
                    <tr>
                        <td align="center"> 
                       #if($open_button_online == "yes") 
                       #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")  
                        
                        
                           #if($show_kemaskini_button=="yes")
                                  
                                 
                          #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                       <!-- <input type="submit" name="cmdKemaskin2" id="cmdKemaski11" value="Kemaskini"   onclick="setSelected(0,1,0,0);kemaskini_pemohon()" /> -->
                          #end
                              
                              #parse("app/ppk/syarat_kemaskini.jsp")
                            #if($boleh_kemaskini == "yes")
                            #end
                            <input type="submit" name="cmdKemaskini1" id="cmdKemaskini1" value="Kemaskini"   onclick="setSelected(0,1,0,0);kemaskini_pemohon()" />
                             #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('cmdKemaskini1').style.display = "none";
                                </script>
                                #end   
                                 
                                      
                                      #end
						           #if($show_simpan_button=="yes")
                                 
                        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan"  onclick="setSelected(0,1,0,0);Simpan_Pemohon()"/>
                                   
                                    #end
                                   
                                     
                                     #if($show_kemaskini_button!="yes")
                                   
                                      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onclick="setSelected(0,1,0,0);BatalPemohon()"/>
                                  
                                    #end 
                                  <!--
                                      <input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali"  onclick="kembali_simati()" />
                                      -->
                                      #else
                                      
                                       #if($tpemohon != 6 && $tpemohon != 8)                                      
                                      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan"  onclick="setSelected(0,1,0,0);Simpan_Pemohon()"/>
                                       #end
                                    #end 
                                    
                                    #end
                                      </td>
                      </tr>
                #end    
                <tr>
                    
                <tr>
                <td>
                 #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                <p align="right">CL - 01 - 66</p>
                #end
                </td>
                </tr>
                    </table> 
                  </div>
                  
                 
                   <div class="TabbedPanelsContent" >
                   
                     
                
 
</div>

                </div>
              </div>
            </div>
        
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
        
          </div>
        </div>
      </div>
       
      <div class="TabbedPanelsContentVisible">
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
  </div>    
  
  </td>
  </tr>
</table>
             #parse("app/ppk/paging_sek8.jsp")
             #parse("app/ppk/headerppk_script.jsp")
</form>






<script>
alamatwarga(document.f1.socWarganegaraPemohon.value,'alamatwarga','tr_nama_warga','nama_warga');
selectPelbagaiNegara(document.f1.socNegeriPemohon.value,'div_mesejpelbagagainegara','tr_pelbagainegara','nama_pelbagainegara');
selectPelbagaiNegara(document.f1.socNegeriPemohonSurat.value,'div_mesejpelbagagainegara_surat','tr_pelbagainegara_surat','nama_pelbagainegara_surat');
<!-- TAB -->
function PeguamView() {
	document.f1.action = "";
	document.f1.mode.value = "Peguamview";
	document.f1.command.value = "Peguam";
	document.f1.submit();
}
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

function PemohonView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
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

function PentingView() {
	document.f1.action = "";
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
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
document.f1.reset();
document.f1.txtNoKPBaru1Waris.focus();
}
}
<!-- PEMOHON -->
function insertsaudara()
{
   if (document.f1.socJantinaPemohon.value == "" || document.f1.socJantinaPemohon.value == "0") {
		alert("Sila pilih jantina terlebih dahulu");
		socJantinaPemohon.focus();
		return; 
	}
}

function kemaskini_pemohon()
{
	document.f1.mode.value = "kemaskini_pemohon";
	document.f1.command.value = "Pemohon";
	document.f1.action = "";
	document.f1.submit();
}

function Simpan_Pemohon(){
   var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.f1.txtEmelPemohon.value;	
	
	
	
var negeri_code = document.f1.txtnoKpBaru2Pemohon.value;
var dob_code = document.f1.txtnoKpBaru1Pemohon.value;
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
	
	
	
	
	
if (document.f1.txtNamaPemohonPemohon.value=="") {
		alert("Sila masukkan nama pemohon.");
		document.f1.txtNamaPemohonPemohon.focus();
		return; 
	}
	/*
		else if (document.f1.status_pemohon.value == "2" && document.f1.txtnoKpBaru1Pemohon.value=="" && document.f1.txtnoKpBaru2Pemohon.value=="" && document.f1.txtnoKpBaru3Pemohon.value==""&&  document.f1.txtNoKPLamaPemohon.value == "" && document.f1.socJenisKPLainPemohon.value=="" && document.f1.txtNoKPLainPemohon.value=="") {
		alert("Sila masukkan salah satu nombor MyID pemohon ");
		document.f1.txtNoKPBaru1Pemohon.focus();
		return;		
		}
		*/
		
		else if (document.f1.status_pemohon.value == "2" && document.f1.txtnoKpBaru1Pemohon.value=="" && document.f1.txtnoKpBaru2Pemohon.value=="" && document.f1.txtnoKpBaru3Pemohon.value=="" && document.f1.txtNoKPLamaPemohon.value == "" && (document.f1.socJenisKPLainPemohon.value=="" || document.f1.socJenisKPLainPemohon.value=="0") && document.f1.txtNoKPLainPemohon.value=="") {
		alert("Sila masukkan salah satu nombor MyID pemohon ");
		document.f1.txtnoKpBaru1Pemohon.focus();
		return;
		
		}
		
		else if (document.f1.socTarafKePemohonanPemohon.value=="") {
		alert("Sila pilih taraf kepentingan pemohon.");
		document.f1.socTarafKePemohonanPemohon.focus();
		return; 
	    }
		
		else if (document.f1.status_pemohon.value == "2" &&  document.f1.socTarafKePemohonanPemohon.value=="1" && (document.f1.socTalianPersaudaraanPemohon.value == "" || document.f1.socTalianPersaudaraanPemohon.value == "0") ) {
		alert("Bagi pemohon yg bertaraf waris simati, sila pastikan jenis talian persaudaraan dipilih.");
		document.f1.socTalianPersaudaraanPemohon.focus();
		return; 
	    }
		
		
		
		else if ((document.f1.status_pemohon.value == "2" && (document.f1.socJenisKPLainPemohon.value!="0" && document.f1.socJenisKPLainPemohon.value!="") && (document.f1.txtNoKPLainPemohon.value=="")))
	 {
	 	alert("Sila masukkan nombor MyID lain pemohon ");
		document.f1.txtNoKPLainPemohon.focus();
		return; 
	 }
	 else if ((document.f1.status_pemohon.value == "2" && (document.f1.socJenisKPLainPemohon.value!="0" && document.f1.socJenisKPLainPemohon.value!="") && (document.f1.txtNoKPLainPemohon.value=="")))
	 {
	 	alert("Sila pilih jenis MyID lain pemohon ");
		document.f1.socJenisKPLainPemohon.focus();
		return; 
	 }
	 
	 else if (document.f1.status_pemohon.value == "2" && (document.f1.txtnoKpBaru1Pemohon.value!="" || document.f1.txtnoKpBaru2Pemohon.value!="" || document.f1.txtnoKpBaru3Pemohon.value!="") && (document.f1.txtnoKpBaru1Pemohon.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID pemohon sepenuhnya");
		document.f1.txtnoKpBaru1Pemohon.focus();
		return; 
	}
	 else if (document.f1.status_pemohon.value == "2" && (document.f1.txtnoKpBaru2Pemohon.value!="" || document.f1.txtnoKpBaru2Pemohon.value!="" || document.f1.txtnoKpBaru3Pemohon.value!="") && (document.f1.txtnoKpBaru2Pemohon.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID pemohon sepenuhnya");
		document.f1.txtnoKpBaru2Pemohon.focus();
		return; 
	}
	 else if (document.f1.status_pemohon.value == "2" && (document.f1.txtnoKpBaru3Pemohon.value!="" || document.f1.txtnoKpBaru2Pemohon.value!="" || document.f1.txtnoKpBaru3Pemohon.value!="") && (document.f1.txtnoKpBaru3Pemohon.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID pemohon sepenuhnya");
		document.f1.txtnoKpBaru3Pemohon.focus();
		return; 
	}
	else if (document.f1.status_pemohon.value == "2" && document.f1.txtnoKpBaru1Pemohon.value!=""  && document.f1.txtnoKpBaru1Pemohon.value.length < 6 ) {
		alert("Sila masukkan nombor MyID pemohon sepenuhnya");
		document.f1.txtnoKpBaru1Pemohon.focus();
		return; 
	}
	else if (document.f1.status_pemohon.value == "2" && document.f1.txtnoKpBaru2Pemohon.value!="" && document.f1.txtnoKpBaru1Pemohon.value.length < 2 ) {
		alert("Sila masukkan nombor MyID pemohon sepenuhnya");
		document.f1.txtnoKpBaru2Pemohon.focus();
		return; 
	}
	else if (document.f1.status_pemohon.value == "2" && document.f1.txtnoKpBaru3Pemohon.value!="" && document.f1.txtnoKpBaru1Pemohon.value.length < 4) {
		alert("Sila masukkan nombor MyID pemohon sepenuhnya");
		document.f1.txtnoKpBaru3Pemohon.focus();
		return; 
	}
		
		else if (document.f1.status_pemohon.value == "2" && document.f1.txtPoskodPemohon.value != "" && document.f1.txtPoskodPemohon.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodPemohon.focus();
		return; 
	}
	
	else if(!em.match(emailExp) && em!=""){
		
		alert("Alamat email tidak sah!");		
		document.f1.txtEmelPemohon.focus();
		return;
	}
	

else if (document.f1.status_pemohon.value == "2" && document.f1.txtnoKpBaru1Pemohon.value != "" && isIc(tt)==false){
		document.f1.txtnoKpBaru1Pemohon.focus()
		return;
	}


/*
else if (document.f1.status_pemohon.value == "2" && document.f1.txtnoKpBaru2Pemohon.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtnoKpBaru2Pemohon.focus()
	return;
	
	}
	*/
	
	else if(document.f1.txtNoKPLamaPemohon.value != "" && document.f1.txtNoKPLamaPemohon.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Pemohon");
		document.f1.txtNoKPLamaPemohon.focus();	
	}
	
	else if (document.f1.status_pemohon.value == "2" && document.f1.txtUmurPemohon.value == "")
	{
	alert("Sila masukkan umur pemohon");
	document.f1.txtUmurPemohon.focus()
	return;
	}
	
	else if (document.f1.txtPoskodPemohonSurat.value != "" && document.f1.txtPoskodPemohonSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodPemohonSurat.focus();
		return; 
	}
	else if (document.f1.txtAlamatTerakhir1Pemohon.value == "" || (document.f1.socWarganegaraPemohon.value != "2" && (document.f1.txtPoskodPemohon.value == ""  || document.f1.socNegeriPemohon.value == "" || document.f1.socNegeriPemohon.value == "0")))
	{
	alert("Sila lengkapkan alamat tetap pemohon");
	document.f1.txtAlamatTerakhir1Pemohon.focus()
	return;
	}	
	else if (document.f1.txtAlamatTerakhir1PemohonSurat.value == "" || (document.f1.socWarganegaraPemohon.value != "2" && (document.f1.txtPoskodPemohonSurat.value == "" || document.f1.socNegeriPemohonSurat.value == "" || document.f1.socNegeriPemohonSurat.value == "0")))
	{
	alert("Sila lengkapkan alamat surat menyurat pemohon");
	document.f1.txtAlamatTerakhir1PemohonSurat.focus()
	return;
	}
	/*
	else if (document.f1.txtNoTelefonPemohon.value == "" && document.f1.txtNoTelefonBimbitPemohon.value == "" )
	{
	
	alert("Sila pastikan salah satu no telefon diisi");
	document.f1.txtNoTelefonPemohon.focus()
	return;
	
	}
	*/
	
	
		
	

		else{
		input_box = confirm("Adakah anda pasti?");		
		if (input_box == true)
		 {
		document.f1.mode.value = "simpan_pemohon_data";
		document.f1.command.value = "Pemohon";
		document.f1.action = "";
		document.f1.submit();
		}
		else
		{
		return;
		}
		}
	
}
function getSaudara(v_t)
{
 if (document.f1.socJantinaPemohon.value == "") {
		alert("Sila pilih jantina terlebih dahulu");
		document.f1.socJantinaPemohon.focus();
		return; 
	}
	else
	{ 
    document.f1.action = "";
	document.f1.mode.value = "getSaudara";
	document.f1.command.value = "Pemohon";
	document.f1.v_tab.value = v_t;
	document.f1.bandar_event.value = "no";	
	document.f1.submit();
	
	}
	
}

function getBandarTetap(v_t)
{
    document.f1.action = "";
	document.f1.mode.value = "getSaudara";
	document.f1.command.value = "Pemohon";
	document.f1.v_tab.value = v_t;
	document.f1.bandar_event.value = "yes";	
	document.f1.submit();
	
}

function getBandarSurat(v_t)
{
    document.f1.action = "";
	document.f1.mode.value = "getSaudara";
	document.f1.command.value = "Pemohon";
	document.f1.v_tab.value = v_t;
	document.f1.bandar_event.value = "yesno";	
	document.f1.submit();
	

	
}


function Checkumur() 
{
	if (document.f1.txtUmurPemohon.value != "" && document.f1.txtUmurPemohon.value>100) {
		alert("Adakah anda pasti umur anda adalah "+document.f1.txtUmurPemohon.value+" tahun?");
		document.f1.txtUmurPemohon.focus();
		return; 
	}
	}


function BatalPemohon() {
input_box = confirm("Adakah anda pasti?");
	
	
	if (input_box == true) {
	document.f1.action = "";
	document.f1.mode.value = "batal_pemohon";
	document.f1.command.value = "Pemohon";
	document.f1.submit();
	}
	else{
	return;
	}
}




</script>




















<script type="text/javascript">


//onblur="emailValidator(document.getElementById('txtEmelPemohon'), 'Alamat email tidak sah!')" 

function emailValidator(elem, helperMsg){
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	
	if(elem.value!=""){
	if(elem.value.match(emailExp)){
		return true;
	}else{
		alert(helperMsg);		
		elem.focus();
		return false;
	}
	}
}

function emailValidator1(elem, helperMsg){
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	
	if(elem.value!=""){
	if(elem.value.match(emailExp)){
		return true;
	}else{
		alert(helperMsg);		
		elem.focus();
		return false;
	}
	}
}


function jantinaic()
{
var ch=document.f1.txtnoKpBaru3Pemohon.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaPemohon.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaPemohon.value = 1 ;

}

return;
}

function kplain(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainPemohon.disabled = false;
document.f1.txtNoKPLainPemohon.value = "";
//document.f1.txtNoKPLainPemohon.focus();
return;
}
else
{
document.f1.txtNoKPLainPemohon.disabled = true;
document.f1.txtNoKPLainPemohon.value = "";
return;
}


}

function kplainX(val)
{

if(val!="0" && val!="")
{
document.f1.txtNoKPLainPemohon.focus();
return;
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

document.f1.txdTarikhLahirPemohon.value=dob;
}


}


var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

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
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}


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
window.location.hash='tab_Pemohon';
//goTo('tab_Pemohon');
var nextFieldID = 'tab_Pemohon';
   document.getElementById(nextFieldID).focus();
}
	
} 

function copycopyX(v_t)
{
if(document.f1.copy.checked == true)
	{
    document.f1.action = "";
	document.f1.mode.value = "getSaudara";
	document.f1.command.value = "Pemohon";
	document.f1.v_tab.value = v_t;
	document.f1.bandar_event.value = "copy";	
	document.f1.submit();
	}
	
	//if(document.f1.copy.checked == false)
	else
	{
	
	document.f1.action = "";
	document.f1.mode.value = "getSaudara";
	document.f1.command.value = "Pemohon";
	document.f1.v_tab.value = v_t;
	document.f1.bandar_event.value = "copycancel";	
	document.f1.submit();
	
	}
}

function copycopy()
{

//

var a1 = document.f1.txtAlamatTerakhir1Pemohon.value;
var a2 = document.f1.txtAlamatTerakhir2Pemohon.value;
var a3 = document.f1.txtAlamatTerakhir3Pemohon.value;
var p1 = document.f1.txtPoskodPemohon.value;
var b1 = document.f1.txtBandarPemohon.value;
var n1 = document.f1.socNegeriPemohon.value;

if(document.f1.copy.checked == true)
{

document.f1.txtAlamatTerakhir1PemohonSurat.value = a1;
document.f1.txtAlamatTerakhir2PemohonSurat.value = a2;
document.f1.txtAlamatTerakhir3PemohonSurat.value = a3;
document.f1.txtPoskodPemohonSurat.value = p1;
document.f1.txtBandarPemohonSurat.value = b1;
document.f1.socNegeriPemohonSurat.value = n1;

}

if(document.f1.copy.checked == false)
{
document.f1.txtAlamatTerakhir1PemohonSurat.value = "";
document.f1.txtAlamatTerakhir2PemohonSurat.value = "";
document.f1.txtAlamatTerakhir3PemohonSurat.value = "";
document.f1.txtPoskodPemohonSurat.value = "";
document.f1.txtBandarPemohonSurat.value = "";
document.f1.socNegeriPemohonSurat.value = "0";

}







}

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
if(document.f1.socNegeriPemohon.value == "" || document.f1.socNegeriPemohon.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPemohon.focus();
  return;
	  	
}


}


function CheckBandarSurat()
{
if(document.f1.socNegeriPemohonSurat.value == "" || document.f1.socNegeriPemohonSurat.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPemohonSurat.focus();
  return;
	  	
}


}




function pilih_taraf()
{
//alert(document.f1.status_pemohon.value)
if(document.f1.status_pemohon.value == "1")
{
document.getElementById('individu_kp1').style.display="none";
document.getElementById('individu_kp2').style.display="none";
document.getElementById('individu_kp3').style.display="none";
document.getElementById('individu_kp4').style.display="none";

document.getElementById('individu_jantina').style.display="none";
document.getElementById('individu_saudara').style.display="none";
document.getElementById('individu_warga').style.display="none";
document.getElementById('individu_umur').style.display="none";
document.getElementById('individu_agama').style.display="none";
document.getElementById('no_hp').style.display="none";
if(document.getElementById('no_hp_info')!=null){
document.getElementById('no_hp_info').style.display="none";
}
document.getElementById('no_fax').style.display="";
}
else
{
document.getElementById('individu_kp1').style.display="";
document.getElementById('individu_kp2').style.display="";
document.getElementById('individu_kp3').style.display="";
document.getElementById('individu_kp4').style.display="";

document.getElementById('individu_jantina').style.display="";
document.getElementById('individu_saudara').style.display="";
document.getElementById('individu_warga').style.display="";
document.getElementById('individu_umur').style.display="";
document.getElementById('individu_agama').style.display="";
document.getElementById('no_hp').style.display="";
if(document.getElementById('no_hp_info') != null)
{
document.getElementById('no_hp_info').style.display="";
}
document.getElementById('no_fax').style.display="none";
}
}


</script>

</body>
</html>
