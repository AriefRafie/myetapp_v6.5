



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
.style40 {color: #0000FF}
.style44 {
	font-size: 9px;
	font-style: italic;
	color: #FF0000;
}
.style45 {font-size: 10px; color: #FF0000; }
.style49 {color: #FF0000}
.style50 {
	font-size: 9px;
	font-style: italic;
}
.style51 {color: #0000FF; font-size: 9px; }
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
</head>

<!-- onLoad="self.focus();document.f1.socWaktuKematianSimati.focus()" -->


<body  onload="submitForm()" >
<form name="f1" method="post" action=""  >
 
<input type="hidden" name="v_tab" id="v_tab" value="" />
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
 <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
 
 #parse("app/ppk/paging_sek8.jsp")
 <input name="eventStatus" id="eventStatus" type="hidden" />
 
#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#set($id_Status = $list.id_Status)
#end
 
<!--
<table width="100%">
<tr>
<td width="100%">
 <div align="right">
 
#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#set($id_Status = $list.id_Status)
#end

         #if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $pendaftaran == "")
		 <a href="javascript:javascript:Kembali()" class="style40"><img src="../img/senarai_permohonan_available.png" alt="" border="0"/></a>
         <img src="../img/arrow_pendaftaran.png" alt="" border="0"/>
         #end
         #if ($flagFromSenaraiFailSek8 == 'yes')        
         <a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style40"><img src="../img/senarai_permohonan_available.png" alt="" border="0"/></a>
         <img src="../img/arrow_pendaftaran.png" alt="" border="0"/>        
         #end         
         #if ($flagFromSenaraiPermohonanSek8 == 'yes')           
         <a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style40"><img src="../img/senarai_permohonan_available.png" alt="" border="0"/></a>
         <img src="../img/arrow_pendaftaran.png" alt="" border="0"/>
   		 #end 
 
  #if($pendaftaran == "yes")
  <a href="javascript:kembalidaftar()" class="style40" ><img src="../img/senarai_semak_available.png" alt="" border="0"/></a>
  #else
  <a href="javascript:kembalix()" class="style40" ><img src="../img/senarai_semak_available.png" alt="" border="0"/></a>
  #end
  <img src="../img/arrow_pendaftaran.png" alt="" border="0"/>
  <a href="javascript:kembali_simati()" class="style40" ><img src="../img/pendaftaran_per_available.png" alt="" border="0"/></a>
  <img src="../img/arrow_pendaftaran.png" alt="" border="0"/>  
 <img src="../img/maklumat_permohonan_current.png" alt="" border="0"/> </div></td>
</tr>
<input name="eventStatus" id="eventStatus" type="hidden" />
</table>
-->
#if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
#parse("app/ppk/bil_fail.jsp") 
#end
<table width="100%" border="0">

#foreach($listFail in $ViewFail)
<input name="id_Suburusanstatus" type="hidden"  value="$listFail.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$listFail.id_Suburusanstatusfail"/>
#end

 <input type="hidden" name="command" value="">
 <input type="hidden" name="mode" value="">
 
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>

 #foreach($PermohonanSebelum in $listGetPermohonanSebelum)
#set ($id_Permohonan_terdahulu = $PermohonanSebelum.id_Permohonan)
#set ($no_subjaket = $PermohonanSebelum.no_subjaket)
#end
 <input type="hidden" name="dah_daftar_ke" id="dah_daftar_ke" value="sudah" />
<input name="id_Permohonan_terdahulu" type="hidden"  value="$id_Permohonan_terdahulu"/>
<input name="no_subjaket" type="hidden"  value="$no_subjaket"/>


#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    
     #set ($idSimati = $list.idSimati)
    #set ($tarikhMohon = $list.tarikhMohon)
    <input name="tarikh_daftar" type="hidden"  value="$tarikhMohon"/>
    <input name="" type="hidden"  value="$id"/>
    
  <input name="idpermohonan_baru" type="hidden" value="$list.idPermohonan" />
    
      <input name="id_Fail" type="hidden"  value="$list.idFail"/>
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
     <input name="idpermohonan" type="hidden"  value="$id"/>
    
    <input name="idPermohonan" type="hidden"  value="$id"/>
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idSimati" type="hidden"  value="$idSimati"/>
      
       <input name="idtemp" type="hidden"  value="$id"/>
       
        

<input name="id_Permohonansimati" type="hidden"  value="$list.id_Permohonansimati"/>

#if($readmode == "disabled")
#set($readonly = "readonly")
#else
#set($readonly = "")
#end


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

#parse("app/ppk/maklumat_sek8.jsp")
 <input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/>
             <input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
                       #set($md=$listtarikhMohon)
                    <input type="hidden" name="txdTarikhMohon" value="$listtarikhMohon" readonly="true"/>
                    <input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>


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
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" >      
      NILAIAN HARTA</li>
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
            <li class="TabbedPanelsTab style1 style3" tabindex="0"  id="tab_Simati" onclick="setSelected(0,0,0,0);SimatiView()" >SIMATI</li>
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
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" >
                           
              #if($readmode == "disabled")
              #set($readmodeR = "readonly")
              #else
              #set($readmodeR = "")
              #end
             
              
                <tr>
                  <td>
                    <p>#foreach($listmati in $listSimati)                    </p>
                    <fieldset>
                    <legend>MAKLUMAT SIMATI</legend>
                    <table width="100%">
                      <tr>
                        <td width="50%">
                       
                            <table width="100%" border="0">
                              <tr>
                                <td width="29%"><div align="right" class="style38">
                                  <div align="left"><span class="style38">No KP Baru</span></div>
                                </div></td>
                                <td width="1%" class="style36" >:</td>
                                
                                <td width="70%" class="style36" >
                               
                                  
                              <!--    
                                     <input name="txtNoKPBaru1Simati" type="text" id="txtNoKPBaru1Simati" style="width: 50px;" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru2Simati')" value="$listmati.noKpBaru1" size="7" maxlength="6" $readmode onBlur="getAgeByIC(this,this.value,'txtUmurSimati')" />
                                     -
                                     <input name="txtNoKPBaru2Simati" type="text" id="txtNoKPBaru2Simati" style="width: 20px;" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru3Simati')" value="$listmati.noKpBaru2" size="1" maxlength="2" $readmode />
                                     -
                                     <input name="txtNoKPBaru3Simati" type="text" id="txtNoKPBaru3Simati"  style="width: 40px;" onkeyup="javascript:validateIC(this,this.value,'txtNoKPBaru3Simati')" onblur="jantinaic()" value="$listmati.noKpBaru3" size="5" maxlength="4" $readmode />
                                     
                               -->   
                                     <input name="txtNoKPBaru1Simati" id="txtNoKPBaru1Simati" style="width: 50px;" type="text" value="$listmati.noKpBaru1" size="7" maxlength="6" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Simati')"  onblur="qryHowOld()" />
      <!--   getAgeByIC(this,this.value,'txtUmurSimati');  -->                        
                -
                
  <input name="txtNoKPBaru2Simati" id="txtNoKPBaru2Simati" style="width: 20px;" type="text" value="$listmati.noKpBaru2" size="3" maxlength="2" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Simati')"  />
 
                -
               
  <input name="txtNoKPBaru3Simati" id="txtNoKPBaru3Simati"  style="width: 40px;" type="text" value="$listmati.noKpBaru3" $readmodeR class="$readmode" size="5" maxlength="4"  onblur="jantinaic();" />
  
  <input type="hidden" name="txdTarikhLahirSimati" id="txdTarikhLahirSimati" value=""  />
  #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style51">  www.jpn.gov.my</a>
  #end  </td>
                              </tr>
                              <tr>
                                <td ><div align="right" class="style38">
                                  <div align="left"><span class="style38">No KP Lama</span></div>
                                </div></td>
                                <td class="style36" >:</td>
                                <td class="style36" ><label>
                                  <input name="txtNoKPLamaSimati" type="text" id="textfield4" style="text-transform:uppercase;" value="$listmati.noKpLama" size="15" maxlength="25" $readmodeR class="$readmode" onblur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td ><div align="right" class="style38">
                                  <div align="left"><span class="style38">Lain-lain KP</span></div>
                                </div></td>
                                <td class="style36">:</td>
                                <td class="style36"> #if($readmode=="disabled")
                                  
                                  #if($listmati.jenisKp=="5")
                                  #set($jkp="Tentera")
                                  
                                  #elseif($listmati.jenisKp=="6")
                                  #set($jkp="Polis")
                                  
                                  #elseif($listmati.jenisKp=="4")
                                  #set($jkp="Pasport")
                                  
                                  #elseif($listmati.jenisKp=="7")
                                  #set($jkp="Lain-lain")
                                  
                                  #else
                                  #set($jkp="")
                                  #end
                                  
                                  #if($jkp=="")
                                  <input name="textfield4" type="text" id="textfield" value="" size="12" maxlength="9"  $readmodeR class="$readmode" onblur="this.value=this.value.toUpperCase()"      $readmode  />
                                  #else
                                  <input name="textfield4"  type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$jkp"  size="12" maxlength="9" $readmodeR class="$readmode" />
                                  #end
                                  <input name="socJenisKPLainSimati" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$jkp"  size="15" $readmodeR class="$readmode" />
                                  #else
                                  <select name="socJenisKPLainSimati" id="select" style="width: 180px;" onchange="kplain(this.value)" onblur="kplainX(this.value)" />
	 								   #if($listmati.jenisKp=="4")
	                                    <option value="4" selected>PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5" >TENTERA</option>
                                          <option value="7" selected>LAIN-LAIN</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #elseif($listmati.jenisKp=="5")
                                         <option value="5" selected>TENTERA</option>
										<option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
                                           <option value="7" selected>LAIN-LAIN</option>
                                       <option value="0" >-KOSONGKAN-</option>
	                                  
	 								   #elseif($listmati.jenisKp=="7")
                                       <option value="7" selected>LAIN-LAIN</option>
                                       <option value="6" selected>POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
                                           
                                         <option value="0" >-KOSONGKAN-</option>
                                          #elseif($listmati.jenisKp=="6")
                                       <option value="6" selected>POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
                                           <option value="7" selected>LAIN-LAIN</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #else
	                                    <option value="0">SILA PILIH JENIS KP</option>
	                                    <option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5">TENTERA</option>
                                           <option value="7" selected>LAIN-LAIN</option>
		                               #end
                                  </select>
                                  #end
                                  <label></label></td>
                              </tr>
                              
                              #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($listmati.jenisKp != 0 && $listmati.jenisKp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
                              
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">No KP Lain</span></div>
                                </div></td>
                                <td class="style36" >:</td>
                                <td class="style36" >
                                #if($readmode == "disabled")
                                 <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="12" maxlength="25"  $readmodeR class="$readmode" />
                                 #else
                               
                                <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="12" maxlength="25" $readmodekp />
                                #end</td>
                              </tr>
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left">#if($readmode == "disabled")
                                    Nama Simati
                                    #else <span class="style45">Nama Simati</span>
                                    #end                                  </div>
                                </div></td>
                                <td class="style36" >:</td>
                                <td class="style36" ><label>
                                  <input name="txtNamaSimati" type="text" id="textfield6" style="text-transform:uppercase;" value="$listmati.namaSimati" size="34" maxlength="200" $readmodeR class="$readmode"  onblur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">Nama Lain</span></div>
                                </div></td>
                                <td class="style36" style="text-transform:uppercase;">:</td>
                                <td class="style36" style="text-transform:uppercase;"><label>
                                  <input name="txtNamaLainSimati" type="text" id="textfield" style="text-transform:uppercase;" value="$listmati.namaLain"  size="34" maxlength="30" $readmodeR class="$readmode" onblur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">Jantina</span></div>
                                </div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label> #if($readmode=="disabled")
                                  
                                  #if($listmati.jantina=="1")
                                  #set($sex="LELAKI")
                                  
                                  
                                  #elseif($listmati.jantina=="2")
                                  #set($sex="PEREMPUAN")
                                  
                                  #else
                                  #set($sex="")
                                  #end
                                  
                                  #if($sex=="")
                                  <input name="socJantinaSi" type="text" id="textfield" value=""  size="12" maxlength="9" $readmodeR class="$readmode" />
                                  #else
                                  <input name="socJantinaSi" type="text" id="textfield" style="text-transform:uppercase;" value="$sex" $readmodeR class="$readmode" size="12" maxlength="9"  />
                                  #end
                                  <input name="socJantinaSimati" type="hidden" id="textfield" value="$listmati.jantina"  size="30" $readmodeR class="$readmode" />
                                  #else
                                 
                              
                                      
                                   #if($listmati.jantina=="1")
	                               
                                       <select name="socJantinaSimati" id="socJantinaSimati7" style="width: 180px;" >
                                    
                              
                                    <option value="1">LELAKI</option>
                                    <option value="2">PEREMPUAN</option>
                                </select>
                                      
	                               #elseif($listmati.jantina=="2")
	                                <select name="socJantinaSimati" id="socJantinaSimati8" style="width: 180px;" >
                                    
                                      
                              
                                    <option value="2">PEREMPUAN</option>
                                    <option value="1">LELAKI</option>
                                </select>
                                      
	                               #else
	                               
                                       <select name="socJantinaSimati" id="socJantinaSimati9" style="width: 180px;" >
                                    
                              
                                    <option value="">SILA PILIH JANTINA</option>
                                    <option value="1">LELAKI</option>
                                    <option value="2">PEREMPUAN</option>
                                </select>
                                      
	                               #end
                                     
                                    
                                    
                            
                                
                                  #end </label></td>
                              </tr>
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">Agama</span></div>
                                </div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label> #if($readmode=="disabled")
                                  
                                  #if($listmati.jenisAgama=="1")
                                  
                                  #set($ag="ISLAM")
                                  
                                  #elseif($listmati.jenisAgama=="2")
                                  
                                  #set($ag="BUKAN ISLAM")
                                  
                                  #else
                                  
                                  #set($ag="")
                                  
                                  #end
                                  
                                  #if($ag=="")
                                  <input name="socAgamaSi" type="text" id="textfield" value=""  size="34" $readmodeR class="$readmode" />
                                  #else
                                  <input name="socAgamaSi" type="text" id="textfield" style="text-transform:uppercase;" value="$ag"  size="34" $readmodeR class="$readmode" />
                                  #end
                                  <input name="socAgamaSimati" type="hidden" id="textfield" value="$listmati.jenisAgama"  size="30" $readmodeR class="$readmode" />
                                  #else
                                  <select name="socAgamaSimati" id="select3" style="width: 180px;" >
                                    
                              
                                      
                                   #if($listmati.jenisAgama=="1")
	                               
	                               
                                      
                              
                                    <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">ISLAM</option>
                                    <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    
                              
                                      
	                               #elseif($listmati.jenisAgama=="2")
	                               
                                      
                              
                                    <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">ISLAM</option>
                                    
                              
                                      
	                               #else
	                               
                                      
                              
                                    <option value="1" style="text-transform:uppercase;" onblur="text-transform:uppercase;">ISLAM</option>
                                    <option value="2" style="text-transform:uppercase;" onblur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    
                              
                                      
	                               #end
                                      
                                    
                                    
                            
                                  </select>
                                  #end </label></td>
                              </tr>
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">Warganegara</span></div>
                                </div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label> #if($readmode=="disabled")
                                  
                                  #if($listmati.jenisWarga=="1")
                                  
                                  #set($wr="Warganegara")
                                  
                                  #elseif($listmati.jenisWarga=="2")
                                  
                                  #set($wr="Bukan Warganegara")
                                  
                                  #elseif($listmati.jenisWarga=="3")
                                  
                                  #set($wr="Pemastautin Tetap")
                                  
                                  #else
                                  #set($wr="")
                                  
                                  #end
                                  
                                  #if($wr=="")
                                  <input name="socWarganegaraSi" type="text" style="text-transform:uppercase;" onblur="uppercase()"  id="textfield" value=""  size="34" $readmodeR class="$readmode" />
                                  #else
                                  <input name="socWarganegaraSi" type="text" style="text-transform:uppercase;" onblur="uppercase()"  id="textfield" value="$wr"  size="34" $readmodeR class="$readmode" />
                                  #end
                                  <input name="socWarganegaraSimati" style="text-transform:uppercase;" onblur="uppercase()"  type="hidden" id="textfield" value="$wr"  size="30" $readmode $readmodeR class="$readmode" />
                                  #else
                                  <select name="socWarganegaraSimati" id="select4"  class="autoselect" >
                                    
                              
                                      
                                   #if($listmati.jenisWarga=="1")
	                               
                                      
                              
                                    <option value="1">WARGANEGARA</option>
                                    <option value="2">BUKAN WARGANEGARA</option>
                                    <option value="3">PENDUDUK TETAP</option>
                                    
                              
                                      
	                               #elseif($listmati.jenisWarga=="2")
	                               
                                      
                              
                                    <option value="2">BUKAN WARGANEGARA</option>
                                    <option value="1">WARGANEGARA</option>
                                    <option value="3">PENDUDUK TETAP</option>
                                    
                                      
	                               #elseif($listmati.jenisWarga=="3")
	                               
                                      
                              
                                    <option value="3">PENDUDUK TETAP</option>
                                    <option value="1">WARGANEGARA</option>
                                    <option value="2">BUKAN WARGANEGARA</option>
                                   
                              
                                      
                                   #else
                                   
                                      
                              
                                    
                                    <option value="1">WARGANEGARA</option>
                                    <option value="2">BUKAN WARGANEGARA</option>
                                    <option value="3">PENDUDUK TETAP</option>
                                    
                              
                                      
	                               #end
                                  
                                    
                                    
                            
                                  </select>
                                  #end </label></td>
                              </tr>
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">Bukti Kematian</span></div>
                                </div></td>
                                <td>:</td>
                                <td> #if($readmode=="disabled")
                                
                                #foreach($listbuk in $listbuktimati)
                             
                                #if($listmati.idBuktimati==$listbuk.id_Buktimati)
                                
                                #set($bukti="$listbuk.kod - $listbuk.keterangan")
                                
                                
                                #end
                                #if($listmati.idBuktimati=="")
                                
                                #set($bukti="")                           
                                
                                #end
                                
                                
                                #end
                                
                                  <input name="txtBuktiKematian" type="text" id="textfield" value="$bukti"  $readmodeR class="$readmode" size="34" onblur="this.value=this.value.toUpperCase()"  />
                                  <input name="socBuktiKematianSimati" type="hidden" onblur="this.value=this.value.toUpperCase()" id="textfield" value="$listmati.idbuktikematian"  size="30" $readmodeR class="$readmode" />
                                  
                                  #else
                                  
                                  
                                  #if($listmati.idBuktimati!="")
                                   <select name="socBuktiKematianSimati" class="autoselect" onchange="jenis_hutangU(this.value)" >
                                      <option value="$listmati.idBuktimati">$bukti</option>                         
                              
                                        
                                  #foreach($listbuk in $listbuktimati)                                 
                                  #if($listmati.idBuktimati!=$listbuk.id_Buktimati)
                                    <option value="$listbuk.id_Buktimati">$listbuk.kod -  $listbuk.keterangan</option>
                                  #end    
	                               #end
                                  
                                  </select>
                                  #else
                                  
                                  <select name="socBuktiKematianSimati" class="autoselect" onchange="jenis_hutangU(this.value)" >
                                    <option value="">SILA PILIH BUKTI MATI</option>
                                    
                              
                                    
                                  #foreach($listbuk in $listbuktimati)
                                 
                                 
	                               
                                      
                              
                                    <option value="$listbuk.id_Buktimati">$listbuk.keterangan</option>
                                    
                              
                                      
                                   
                                    
	                               #end
                                  
                                  
                                  
                                  
                                    
                            
                                  </select>
                                  #end
                                  
                                  #end </td>
                              </tr>
                              
                              
                              
                              
                              
                              
                               
                                     #if($readmode=="disabled")
                                     
                                     #set($readmodesy="disabled")
                                    
                                     #end
                                     
                                     #if($readmode!="disabled")
                                     
                                      #if($listmati.idBuktimati=="1" || $listmati.idBuktimati=="4")
                                      #set($readmodesy="")
                                    
                                     
                                     #else
                                      #set($readmodesy="disabled")
                                     
                                     
                                   
                                     #end                                    
                                     
                                     #end
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">No. Sijil Mati / Perintah Mahkamah (Kematian)</span></div>
                                </div></td>
                                <td class="style36" style="text-transform:uppercase;">:</td>
                                <td class="style36" style="text-transform:uppercase;"><label>
                              
                                #if($readmode == "disabled")
                                 <input name="txtNoSijilMatiSimati" onblur="this.value=this.value.toUpperCase()" type="text" id="txtNoSijilMatiSimati" style="text-transform:uppercase;" value="$listmati.noSijilMati" size="12" maxlength="25" $readmodeR class="$readmode"/>
                                #else
                                  <input name="txtNoSijilMatiSimati" onblur="this.value=this.value.toUpperCase()" type="text" id="txtNoSijilMatiSimati" style="text-transform:uppercase;" value="$listmati.noSijilMati" size="12" maxlength="25" $readmodesy />
                                #end
                                </label></td>
                              </tr>
                             
                              <tr>
                                <td ><div align="right" class="style38">
                                  <div align="left">#if($readmode == "disabled")
                                    Tarikh Mati
                                    #else <span class="style45">
                                      Tarikh Mati</span>
                                    
                                    #end                                  </div>
                                </div></td>
                                <td>:</td>
                                <td> #if($readmode=="disabled")
                                  <input name="txdTarikhMati" type="text" id="textfield" value="$listmati.tarikhMati"  size="10" maxlength="10"   $readmodeR class="$readmode" />
                                    <input name="txdTarikhMati" type="hidden" id="textfield" value="$listmati.tarikhMatidb"  size="9"   $readmodeR class="$readmode" />
                                  #else
                                  <input name="txdTarikhMatiSimati" type="text" id="txdTarikhMatiSimati" value="$listmati.tarikhMati" size="10" maxlength="10"   $readmodeR class="$readmode"  onfocus="qryHowOld()" onblur="trans_date(this.value);qryHowOld()"  />
                                  <a href="javascript:displayDatePicker('txdTarikhMatiSimati',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> #end 
                                  
                                  <input name="mohondate" type="hidden" id="mohondate" value="$md" size="10" maxlength="10" $readmode  />
                                  #if($readmode != "disabled" )<span class="style52">dd/mm/yyyy</span>#end                                  </td>
                              </tr>
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">Umur Ketika Mati</span></div>
                                </div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label>
                                  <input name="txtUmurSimati" type="text" id="txtUmurSimati" onkeyup="javascript:validateIC(event,this,this.value,'txtUmurSimati')"  onblur="Checkumur()" value="$listmati.umur" size="4" maxlength="3"   $readmodeR class="$readmode" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><div align="right" class="style38">
                                  <div align="left"><span class="style38">Waktu Kematian</span></div>
                                </div></td>
                                <td class="style36">:</td>
                                <td class="style36"><input name="socWaktuKematianSimati" type="text" id="socWaktuKematianSimati" value="$listmati.masamati"  onKeyUp="javascript:validateIC(event,this,this.value,'socWaktuKematianSimati')" size="4" maxlength="4"   $readmodeR class="$readmode" /> 
                                
                                
                                  <label>
                                  #if($readmode=="disabled")
                                  #set($waktu = "")
                                  #if( $listmati.jeniswaktu == 1)
                                     #set($waktu = "PAGI")  
                                  #elseif($listmati.jeniswaktu == 2)
                                     #set($waktu = "TENGAHARI")                                   
                                  #elseif($listmati.jeniswaktu == 3)
                                     #set($waktu = "PETANG")      
                                     #elseif($listmati.jeniswaktu == 4)
                                     #set($waktu = "MALAM")                                   
                                  #else
                                     #set($waktu = "")
                                  #end             
                                  
                                   <input name="jeniswaktu" type="text" id="jeniswaktu"    value="$waktu" size="4" maxlength="3" style="width:100"  $readmodeR class="$readmode" />
                                  #else
                                  <select name="jeniswaktu" id="jeniswaktu" style="width:100">
                                  #if( $listmati.jeniswaktu == 1)
                                  <option value="1">PAGI</option>
                                  <option value="2">TENGAHARI</option>
                                  <option value="3">PETANG</option>
                                  <option value="4">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>                                    
                                    #elseif($listmati.jeniswaktu == 2)
                                      <option value="2">TENGAHARI</option>
                                     <option value="1">PAGI</option>                                 
                                    <option value="3">PETANG</option>
                                    <option value="4">MALAM</option>
                                  
                                      <option value="">SILA PILIH</option>
                                    #elseif($listmati.jeniswaktu == 3)
                                     <option value="3">PETANG</option>
                                  <option value="1">PAGI</option>
                                   <option value="2">TENGAHARI</option>                                   
                                    <option value="4">MALAM</option>
                                      <option value="">SILA PILIH</option>
                                       #elseif($listmati.jeniswaktu == 4)
                                        <option value="4">MALAM</option>
                                        <option value="1">PAGI</option>
                                   <option value="2">TENGAHARI</option>
                                    <option value="3">PETANG</option>  
                                      <option value="">SILA PILIH</option>
                                    #else
                                      <option value="">SILA PILIH</option>
                                        <option value="1">PAGI</option>
                                   <option value="2">TENGAHARI</option>
                                    <option value="3">PETANG</option>
                                    <option value="4">MALAM</option>
                                    #end                                
                                    
                                 
                                  </select>
                                  #end
                                  </label>
                                
                                 #if($readmode != "disabled" )
                                  <span class="style52">format 12 jam (HHMM)</span>
                                  #end                                 </td>
                              </tr>
                          </table></td>
                        <td valign="top"><table width="100%">
                            <tr valign="top">
                              <td width="29%" class="style38"><div align="right" class="style38">
                                <div align="left">Tempat Mati</div>
                              </div></td>
                              <td width="1%" style="text-transform:uppercase;">:</td>
                              <td width="70%" style="text-transform:uppercase;"><label>
                                 <textarea name="txtTempatMatiSimati" id="patMatiSimati"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" cols="31" rows="3"  $readmodeR class="$readmode" >$listmati.tempatMati</textarea>
                              </label></td>
                            </tr>
                            <tr>
                              <td class="style38" valign="top">
                                <div align="left">#if($readmode == "disabled")
                                  Sebab Kematian
                                  #else </div>
                                <div align="right" class="style45">
                                  <div align="left">Sebab Kematian</div>
                                </div>
                                <div align="left">#end                              </div></td>
                              <td valign="top">:</td>
                              <td><textarea name="txtSebabKematianSimati" cols="31" rows="3" id="txtSebabKematian" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"   $readmodeR class="$readmode" >$listmati.sebabMati</textarea></td>
                            </tr>
                            <tr>
                              <td class="style38"><div align="right" class="style38">
                                <div align="left">Alamat Terakhir</div>
                              </div></td>
                              <td >:</td>
                              <td ><label>
                                <input name="txtAlamatTerakhir1Simati" type="text" id="txtAlamatTerakhir" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listmati.alamat1" size="34" maxlength="50"    $readmodeR class="$readmode"  />
                              </label></td>
                            </tr>
                            <tr>
                              <td class="style38"><div align="left"><span class="style3"></span></div></td>
                              <td >:</td>
                              <td ><label>
                                <input name="txtAlamatTerakhir2Simati" type="text" id="txtAlamatTerakhir2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listmati.alamat2" size="34" maxlength="50"   $readmodeR class="$readmode" />
                              </label></td>
                            </tr>
                            <tr>
                              <td class="style38"><div align="left"><span class="style3"></span></div></td>
                              <td >:</td>
                              <td ><input name="txtAlamatTerakhir3Simati" type="text" id="txtAlamatTerakhir3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listmati.alamat3" size="34" maxlength="50"   $readmodeR class="$readmode" /></td>
                            </tr>
                            <tr>
                              <td class="style38"><div align="right" class="style38">
                                <div align="left">Poskod </div>
                              </div></td>
                              <td style="text-transform:uppercase;">:</td>
                              <td style="text-transform:uppercase;"><label>
                                <input name="txtPoskodSimati" type="text" id="txtPoskod " value="$listmati.poskod" size="5" maxlength="5"   $readmodeR class="$readmode" style="text-transform:uppercase;" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodSimati')"/>
                              </label></td>
                            </tr>
                            <tr>
                              <td class="style38"><div align="right" class="style38">
                                <div align="left">Negeri</div>
                              </div></td>
                              
                              
                              <td>:                              </td>
                              
                                
                            #if($listmati.idnegeri=="" || $listmati.idnegeri=="0" )
                             #set($kod="")
                             
                             #else
                             
                             #foreach($listneg in $listnegeri)      
                                      
                                      
                                      #if($listmati.idnegeri==$listneg.id_Negeri)
                                      
                                      #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
                                      

                                      
                                      #end
                             #end
                             
                             #end
                                
                                
                                      
                                      <td>
                                      
                                       #if($readmode=="disabled")
                      <input name="socNegeri" type="text" id="textfield7"  style="text-transform:uppercase;" onblur="uppercase()" value="$kod"  size="34"   $readmodeR class="$readmode" />                                        
                                        #else                                     
                                        
                                        #if($listmati.idnegeri!="" && $listmati.idnegeri!=0)
                                        <select name="socNegeriSimati" class="autoselect" id="socNegeriPemohon"  onchange="getBandarTetap('txtBandarSimati')" >
                                        <option value="$listmati.idnegeri" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listmati.idnegeri!=$listneg.id_Negeri)
                       <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                     
                                  #end    
	                              #end
                                        </select>
                                        #else
                                        <select name="socNegeriSimati" class="autoselect" onchange="getBandarTetap('txtBandarSimati')">
                                          <option value="" >SILA PILIH NEGERI</option>
                                  #foreach($listneg in $listnegeri)
                   <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                    
	                               #end
                                        </select>
                                        #end   
                                                                             
                                        #end                                        </td>
                            </tr>
                             <tr id="tr_mesej_pelbagainegara">
       
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                   
                                    <td>Negara</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' $readmodeR class="$readmode"  list = 'datalist'  value="$listmati.nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    </td>
        </tr>
                            <tr>
                               #set($bandartetap = $listmati.bandartetap)
                              <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                              <div align="right" class="style43">
                                    <div align="left">Bandar</div>
                              </div>
                                <div align="left">#else
                                  Bandar
                                  #end </div></td>
                              <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                              <td><label> 
                              
                              
                              
                           
                             
                             #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($bandartetap == $listneg.id)                                      
                                      #set($kodb = "$listneg.kod - $listneg.nama")                                                                      
                                      #end
                             #end
                             
                           
                              
                              
                              #if($readmode=="disabled" )
                    
                               #if($bandartetap != "" && $bandartetap != "0")
                      <input name="socBandar" type="text" id="socBandar"   style="text-transform:uppercase;" onblur="uppercase()" value="$kodb"  size="34"   $readmodeR class="$readmode" />   
                      #else
                      <input name="socBandar" type="text" id="socBandar"  style="text-transform:uppercase;" onblur="uppercase()" value=""  size="34"   $readmodeR class="$readmode" />  
                      
                      #end    
                                                       
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
                                <select name="txtBandarSimati" id="txtBandarSimati" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                  <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                  
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              
                                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                  
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                </select>
                                #else
                                <select name="txtBandarSimati" id="txtBandarSimati" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                  <option value="">Sila Pilih Bandar</option>
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                </select>
                                #end 
                                #end</label></td>
                            </tr>
                            <tr>
                              <td class="style38" valign="top"><div  align="right" class="style38">
                                <div align="left">Catatan</div>
                              </div></td>
                              <td valign="top">:</td>
                              <td><textarea name="txtCatatanSimati" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" cols="31" rows="3" id="Catatan"   $readmodeR class="$readmode" >$listmati.catatan</textarea></td>
                       </tr>
                        </table></td>
                      </tr>
                      
                     
                      
                      
                    </table>
                                   
                     #if($readmode != "disabled") 
                    <table width="100%">
  <tr>
    <td><span class="style44">Perhatian</span><span class="style50"> : Sila masukkan salah satu nombor kad pengenalan  dan pastikan label berwarna <span class="style49">merah</span> diisi.</span></td>
  </tr>
</table>
#end
                    
                    </fieldset>
                    <p> #end </p>                </tr>
                
                
                 <tr>
                     <td align="center">
                     
                      
                       
                                    #if($show_kemaskini_button=="yes")
                                    
                                     
                                     
                                     
                                    
                                     
                 #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
          
                                     
                                     <!--
                                  
                                      <input type="button" name="cmdKemaskin2" id="cmdKemaskin2" value="Kemaskini" onclick="setSelected(0,0,0,0);kemaskini_simati()" />
                                      -->
                                      #end
                                      
                                      
                               
                                   #end
						           #if($show_simpan_button=="yes")
                                 
      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick="setSelected(0,0,0,0);SimpanSimati()" />
                                   
                                    #end
                                   
                                     
                                     #if($show_kemaskini_button!="yes")
                                   #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                                      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onclick="setSelected(0,0,0,0);BatalSimati()"/>
                                  #end
                                    #end
                                  <!--
                                      <input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali_simati()" />                                   
                                      -->
                                       </td>
                </tr>
                <tr>
                <td>
                #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                <p align="right">CL - 01 - 65</p>    
                #end            </td>
                </tr>
              </table>
 
            </div>
            
            <div class="TabbedPanelsContent">
          
              <div id="TabbedPanels3" class="TabbedPanelsContentVisible">              </div>
            
            </div>
            
           
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            
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
  </div>    </td>
  </tr>
</table>
              #parse("app/ppk/paging_sek8.jsp")
              #parse("app/ppk/headerppk_script.jsp")
</form>

<script>
selectPelbagaiNegara(document.f1.socNegeriSimati.value,'div_mesejpelbagagainegara','tr_pelbagainegara','nama_pelbagainegara');

<!-- TAB -->
function HtaamViewX() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}

	document.f1.mode.value = "HtaamviewX";
	document.f1.command.value = "HtaamX";
	document.f1.submit();
}
function HtaamView() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "Htaamview";
	document.f1.command.value = "Htaam";
	document.f1.submit();
}
function HAview() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "view_harta_alih";
	document.f1.command.value = "harta_alih";
	document.f1.submit();
}

function NAview() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "view_nilai_harta";
	document.f1.command.value = "nilai_harta";
	document.f1.submit();
}

function PenghutangView() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "Penghutangview";
	document.f1.command.value = "Penghutang";
	document.f1.submit();
}
function PemiutangView() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "Pemiutangview";
	document.f1.command.value = "Pemiutang";
	document.f1.submit();
}
function SaksiView() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "Saksiview";
	document.f1.command.value = "Saksi";
	document.f1.submit();
}

function PentingView() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
	document.f1.submit();
}

function WarisView() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "Warisview";
	document.f1.command.value = "Waris";
	document.f1.submit();
}
function SimatiView() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.submit();
	
}

function PemohonView() {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
	document.f1.submit();
}
function kembali_simati(){
if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	
	document.f1.command.value = "kembali_simati";
	
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


<!-- SIMATI -->


function kemaskini_simati(){

if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}

	document.f1.mode.value = "kemaskini_simati";
	document.f1.command.value = "Simati";

	
	document.f1.submit();
}
function jantina()
{

var a=document.f1.txtNoKPBaru3Simati.value;
var m=2;
/*
if (a%2==0) 
{
m=1;
}
else
{
m=2;
}
*/
for (i=0;i<document.getElementById('socJantinaSimati9').length;i++)
{

if (m == document.getElementById('socJantinaSimati9').options(i).value)
{
document.getElementById('socJantinaSimati9').options(i).selected = true;
return;
}
}

}





function SimpanSimati() {
   var dt=document.f1.txdTarikhMatiSimati
   var str1  = document.getElementById("txdTarikhMatiSimati").value;
   var str2  = document.getElementById("mohondate").value;
  
   var dob_code = document.f1.txtNoKPBaru1Simati.value;
   var negeri_code = document.f1.txtNoKPBaru2Simati.value;
   
   
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
   
   var dt1   = parseInt(str1.substring(0,2),10);
   var mon1  = parseInt(str1.substring(3,5),10)-1;
   var yr1   = parseInt(str1.substring(6,10),10);
   
   var dt2   = parseInt(str2.substring(0,2),10);
   var mon2  = parseInt(str2.substring(3,5),10)-1;
   var yr2   = parseInt(str2.substring(6,10),10);
   
   var date1 = new Date(yr1, mon1, dt1);
   var date2 = new Date(yr2, mon2, dt2);

   
	if (document.f1.txtNamaSimati.value=="") {
		alert("Sila masukkan nama simati.");
		document.f1.txtNamaSimati.focus();
		return; 
	} 
	else if (document.f1.txtSebabKematianSimati.value=="") {
		alert("Sila masukkan sebab kematian.");
		document.f1.txtSebabKematianSimati.focus();
		return; 
	}
	else if(document.f1.txdTarikhMatiSimati.value == ""){
		alert('Sila masukkan " Tarikh mati " terlebih dahulu.');
  		document.f1.txdTarikhMatiSimati.focus(); 
		return; 
	}
	else if (document.f1.txtNoKPBaru1Simati.value=="" && document.f1.txtNoKPBaru2Simati.value=="" && document.f1.txtNoKPBaru3Simati.value=="" && document.f1.txtNoKPLamaSimati.value=="" && document.f1.socJenisKPLainSimati.value=="" && document.f1.txtNoKPLainSimati.value=="") {
		alert("Sila masukkan salah satu nombor kad pengenalan simati");
		document.f1.txtNoKPBaru1Simati.focus();
		return; 
		}
	else if (((document.f1.socJenisKPLainSimati.value!="" && document.f1.socJenisKPLainSimati.value!="0") && document.f1.txtNoKPLainSimati.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan lain simati");
		document.f1.txtNoKPLainSimati.focus();
		return; 
	 }
	 else if (((document.f1.socJenisKPLainSimati.value=="" || document.f1.socJenisKPLainSimati.value=="0") && document.f1.txtNoKPLainSimati.value!=""))
	 {
	 	alert("Sila pilih jenis kad pengenalan lain simati");
		document.f1.socJenisKPLainSimati.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Simati.value!="" || document.f1.txtNoKPBaru2Simati.value!="" || document.f1.txtNoKPBaru3Simati.value!="") && (document.f1.txtNoKPBaru1Simati.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan simati sepenuhnya");
		document.f1.txtNoKPBaru1Simati.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Simati.value!="" || document.f1.txtNoKPBaru2Simati.value!="" || document.f1.txtNoKPBaru3Simati.value!="") && (document.f1.txtNoKPBaru2Simati.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan simati sepenuhnya");
		document.f1.txtNoKPBaru2Simati.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Simati.value!="" || document.f1.txtNoKPBaru2Simati.value!="" || document.f1.txtNoKPBaru3Simati.value!="") && (document.f1.txtNoKPBaru3Simati.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan simati sepenuhnya");
		document.f1.txtNoKPBaru3Simati.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Simati.value!=""  && document.f1.txtNoKPBaru1Simati.value.length < 6 ) {
		alert("Sila masukkan nombor kad pengenalan simati sepenuhnya");
		document.f1.txtNoKPBaru1Simati.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Simati.value!="" && document.f1.txtNoKPBaru2Simati.value.length < 2 ) {
		alert("Sila masukkan nombor kad pengenalan simati sepenuhnya");
		document.f1.txtNoKPBaru2Simati.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Simati.value!="" && document.f1.txtNoKPBaru3Simati.value.length < 4) {
		alert("Sila masukkan nombor kad pengenalan simati sepenuhnya");
		document.f1.txtNoKPBaru3Simati.focus();
		return; 
	}
	
		else if(document.f1.txtNoKPLamaSimati.value != "" && document.f1.txtNoKPLamaSimati.value.length<7)
	{
		alert("Sila lengkapkan No KP Lama Simati");
		document.f1.txtNoKPLamaSimati.focus();	
	}
	
	else if (document.f1.txtPoskodSimati.value != "" && document.f1.txtPoskodSimati.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodSimati.focus();
		return; 
	}
	else if (document.f1.socBuktiKematianSimati.value == "1" && document.f1.txtNoSijilMatiSimati.value=="") {
		alert("Sila masukkan nombor sijil mati");
		document.f1.txtNoSijilMatiSimati.focus();
		return; 
	}
	

	else if (document.f1.socWaktuKematianSimati.value != "" && document.f1.socWaktuKematianSimati.value.length < 4) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}

	else if (document.f1.socWaktuKematianSimati.value != "" && (document.f1.socWaktuKematianSimati.value.charAt(0) > 2) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}	
	else if (document.f1.socWaktuKematianSimati.value != "" && (document.f1.socWaktuKematianSimati.value.charAt(0) == 1) && (document.f1.socWaktuKematianSimati.value.charAt(1) >9 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}	
	else if (document.f1.socWaktuKematianSimati.value != "" && (document.f1.socWaktuKematianSimati.value.charAt(0) == 2) && (document.f1.socWaktuKematianSimati.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}
	else if (document.f1.socWaktuKematianSimati.value != "" && (document.f1.socWaktuKematianSimati.value.charAt(2) > 5) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}
	else if (document.f1.socWaktuKematianSimati.value != "" && (document.f1.socWaktuKematianSimati.value.charAt(3) > 9) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}	
	
	else if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
   else if( date1 > date2 )
   {
      alert("Sila pastikan tarikh mati tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhMatiSimati.focus();
	  return;
   } 
   else if (document.f1.txtNoKPBaru1Simati.value != "" && isIc(tt)==false){
  	
	document.f1.txtNoKPBaru1Simati.focus();
		return false
	}
	else if (document.f1.txtNoKPBaru2Simati.value != "" && (negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaru2Simati.focus()
	return false
	
	}
	else{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
		
		if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
		document.f1.mode.value = "simpan_simati";
		document.f1.command.value = "Simati";
		
	
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
	if (document.f1.txtUmurSimati.value != "" && document.f1.txtUmurSimati.value>100)
	 {
	
		alert("Adakah anda pasti umur simati adalah "+document.f1.txtUmurSimati.value+" tahun?");
		txtUmurSimati.focus();
		return false
	}
	
		
}
function BatalSimati() {
input_box = confirm("Adakah anda pasti?");
	
	
	if (input_box == true) {
	if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.mode.value = "batal_simati";
	document.f1.command.value = "Simati";
	
	document.f1.submit();
	}
	else
	{
	return;
	}
}
function jenis_hutangU(val)
{


if(val=="1")
{

document.f1.txtNoSijilMatiSimati.disabled = "";
document.f1.txtNoSijilMatiSimati.value = "";
document.f1.socBuktiKematianSimati.value="1";
}

else if( val=="4" )
{

document.f1.txtNoSijilMatiSimati.disabled = "";
document.f1.txtNoSijilMatiSimati.value = "";
document.f1.socBuktiKematianSimati.value="4";
}

else
{
document.f1.txtNoSijilMatiSimati.disabled = "disabled";
document.f1.txtNoSijilMatiSimati.value = "";
document.f1.socBuktiKematianSimati.value = val;
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


function jantinaic()
{
var ch=document.f1.txtNoKPBaru3Simati.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaSimati.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaSimati.value = 1 ;

}

return;
}

function kplain(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainSimati.disabled = "";
document.f1.txtNoKPLainSimati.value = "";
//document.f1.txtNoKPLainSimati.focus();
return;
}
else
{
document.f1.txtNoKPLainSimati.disabled = "disabled";
document.f1.txtNoKPLainSimati.value = "";
return;
}
}


function kplainX(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainSimati.focus();
return;
}
}

function ValidateForm(){
	var dt=document.f1.txdTarikhMatiSimati
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }

</script>
<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});
/*
 function submitForm() {    
   // document.val.focus();
	
	//new Effect.ScrollTo('$val_tab');
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
window.location.hash='tab_Simati';
//goTo('tab_Simati');
var nextFieldID = 'tab_Simati';
   document.getElementById(nextFieldID).focus();
}
	
} 





function qryHowOld()
   {
   
   var dob_code = document.f1.txtNoKPBaru1Simati.value;
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
	
	 
	 
	 
var dob_codeX = document.f1.txdTarikhMatiSimati.value;

	
     var ttX = dob_codeX;	
	 var dt_dobX   = parseInt(ttX.substring(0,2),10);
     var mon_dobX  = parseInt(ttX.substring(3,5),10)-1;
     var yr_dobX   = parseInt(ttX.substring(6,10),10);
	
	 
	 
	 
     var varAsOfDate = new Date(yr_dobX, mon_dobX, dt_dobX);
	 var varBirthDate = new Date(yr_dob, mon_dob, dt_dob);
	 
	 var year1 = varAsOfDate.getFullYear();
	  var year2 = varBirthDate.getFullYear();
	 
	 
	 if(dob_code == "")
	 {
	 document.f1.txtUmurSimati.value ="";
	 }
	 else
	 {
	 document.f1.txtUmurSimati.value = year1 - year2 ;
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

document.f1.txdTarikhLahirSimati.value=dob;
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


document.f1.txdTarikhMatiSimati.value = trans;

}
else
{
return;
}

}

function kembalix() {

if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
	document.f1.method = "POST";
	document.f1.command.value="papar";
	
	document.f1.submit();
}
function kembalidaftar()
{
if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";
}
        document.f1.command.value="kembali_daftar_pemohon";
		document.f1.eventStatus.value="1";
		
		document.f1.submit();
}

function kembaliSenaraiFail(noFail) {

if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	
}
	document.f1.submit();
}


function kembaliSenaraiPermohonan(noFail) {

if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
		
}

	document.f1.method="POST";
	document.f1.submit();
}
function Kembali(){

if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";		
}

	document.f1.method="POST";
	document.f1.command.value="xxx";
	
	document.f1.submit();
}

function getBandarTetap(v_t)
{
    if('$!skrin_online_17' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangPOnline";
}
else
{
document.f1.action = "";		
}
	document.f1.mode.value = "getBandar";
	document.f1.command.value = "Simati";
	document.f1.v_tab.value = v_t;
	//document.f1.bandar_event.value = "yes";	
	document.f1.submit();
	
}

function CheckBandarTetap()
{
if(document.f1.socNegeriSimati.value == "" || document.f1.socNegeriSimati.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriSimati.focus();
  return;
	  	
}


}

</script>


</body>
</html>
