

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
.style51 {font-size: 10px; color: #000000; }
-->
</style>
</head>

<body onload="submitForm();pilih_taraf();" >
<form id="f1" name="f1" method="post" action="">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input type="hidden" name="v_tab" id="v_tab" value="" />
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
#set($socJenisHutangPenting = "2")
#set($txtButiranHutangPenting = "")

#set($txtNoAkaunPenting = "")

 #set($txtNilaiHutangPenting="")
  
                                    


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
<!--
<fieldset>
<legend>MAKLUMAT PERMOHONAN</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style40 style42">$listnoFail</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Negeri :</div></td>
           #foreach($listnegori in $listnegeri)      
                              #if($listidnegeri==$listnegori.id_Negeri)
                              
                              #set($kodlistnegori="$listnegori.nama_Negeri")
                                       
                              
                              #end
                              
                              #if($listidnegeri=="" || $listidnegeri=="0")
                              
                              #set($kodlistnegori="")
                              
                              #end
                              #end
          <td style="text-transform:uppercase;"><div align="left" class="style40 style42">$kodlistnegori</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Daerah / Jajahan :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listnamadaerah</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Unit :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listnamaPejabat</span></div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Status Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listketerangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listseksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listtarikhMohon</span><span class="style42">
          #set($md=$listtarikhMohon)</span>
                    <input type="hidden" name="txdTarikhMohon" value="$listtarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listnamaPemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style40 style42">$listnamaSimati</span>
            <input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/></td>
        </tr>
      </table>
    </div></td>
  </tr>
</table>



</fieldset>
-->
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
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,3,0,0);PentingView()" >ORANG BERKEPENTINGAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,4,0,0);SaksiView()"   >SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,5,0,0);PemiutangView()" id="maklumat_pemohon" >PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
          </ul>
          
          
          <div class="TabbedPanelsContentGroup">
            <div>
            </div>
            
            <div>            
            </div>
            
            <div>           
            </div>
            <div>           
            </div>
             <div>           
            </div>
            <div class="TabbedPanelsContentVisible">
           #parse("app/ppk/info_berjaya_disimpan.jsp") 
           <table width="100%" border="0">
                        
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
                                       #set($socJenisHutangPentingU = $$listob.jenishutang)
                                       
                                       #set($txtButiranHutangPentingU = $listob.butiranhutang)
                                       
                                       #set($txtNilaiHutangPentingU = $listob.nilaihutang)
                                       #set($txtNoAkaunPentingU = $listob.noakaun)
                                       
                                      
                            
                                       
                                       #end
                                       
                                      
                                       #end
                    <input type="hidden" name="txtIdOb" value="$txtIdOb" > 
                        
                        
                   
                  
                        
                                     #if($nk_update_penting=="yes")
                                     
                                     
                                  
                                       
                                       
                                       <input type="hidden" name="id_Pemohon" value="$id_Pemohon" >      
     <tr>
                                         <td>
                                          <fieldset>
                                          <legend>MAKLUMAT PEMIUTANG (Pihak yang memberi hutang kepada Simati)</legend>
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
                                          <table width="100%" border="0">
                              <tr>
                               <td width="50%" valign="top"><table width="100%">
                                  <input type="hidden" name="txtIdSimatiPenting" value="$txtIdSimatiPenting" >   
                            
                                  <tr>
                                    <td valign="top" class="style38" width="1%"  ><span class="style40">#if($readmode != "disabled")*</span>#end</span></td>
                                    <td width="28%" ><div align="left" class="style41 style38"><span class="style38">#if($readmode != "disabled")Jenis Pemiutang</span>#else Jenis Pemiutang #end</div></td>
                                    <td class="style36" width="1%">:</td>
                                    <td class="style36" width="70%">
                                     #if($readmode == "disabled")
                                     <input name="socJenisHutangPentingU" id="socJenisHutangPentingU" type="hidden" value="$socJenisHutangPentingU" />
                                      #if($socJenisHutangPentingU == "1")
                                      #set($jh = "AGENSI")
                                      #elseif($socJenisHutangPentingU == "2")
                                       #set($jh = "INDIVIDU")
                                      #else
                                       #set($jh = "")
                                      #end
                                      
                                      #if($socJenisHutangPentingU != "0" && $socJenisHutangPentingU != "")
                                      <input type="text" name="jh" value="$jh" size = "34" $readmodeR class="$readmode">    
                                     #else
                                      <input type="text" name="jh" value="" size = "34" $readmodeR class="$readmode">    
                                     #end    
                                      
                                     
                                     #else
                                       
                                    
                                    #if($readmode=="disabled")
                                      
                                      #set($readmodesy="disabled")
                                      #set($readmodekp="disabled")
                                      #set($readmodeln="disabled")
                                      #end
                                    
                                          #if($readmode!="disabled")
                                      
                                      #if($socJenisHutangPentingU=="1")
                                      #set($readmodesy="")
                                      #set($readmodekp="disabled")
                                      #set($readmodeln="disabled")
                                      
                                      #set($txtNoKPBaru1PentingU = "")
                                      #set($txtNoKPBaru2PentingU = "")
                                      #set($txtNoKPBaru3PentingU = "")
                                      #set($txtNoKPLainPentingU = "")
                                      
                                      
                                      
                                      #elseif($socJenisHutangPentingU=="2")
                                      #set($readmodesy="disabled")
                                      #set($readmodekp="")
                                      #set($readmodeln="")
                                      #set($txtNoKPLamaPentingU = "")
                                      
                                      #else
                                      #set($readmodesy="disabled")
                                      #set($readmodekp="disabled")
                                      #set($readmodeln="disabled")
                                      
                                      #end                                    
                                      
                                      #end 
                                  
                                    
                                    
                                    <select name="socJenisHutangPentingU"  class="autoselect" $readmode id="socJenisHutangPentingU" style="text-transform:uppercase;" onblur="uppercase()" onchange="jenis_hutangU(this.value);pilih_taraf()" >
                                      
                                  
                                       
								   #if($socJenisHutangPentingU=="1")
	                                   
                                        
                                      <option value="1">01-Agensi</option>
                                        <option value="2">02-Individu</option>
                                        
	                               #elseif($socJenisHutangPentingU=="2")
	                                
                                       
                                        
                                      <option value="2">02-Individu</option>
                                        <option value="1">01-Agensi</option>
                                       
                                        
                                       
	                               #else 
                                       
                                        
                                    
                                        <option value="1">01-Agensi</option>
                                        <option value="2">02-Individu</option>
                                      
                                        
	                               #end
                                    
                                  
                                    
                                     
                                      
                                    </select>                               
                                    
                                    #end     </td>
                                  </tr>
                                  <tr id="kp1">
                                    <td class="style38" ><span class="style40"></span></td>
                                  <td width="29%" ><div align="left" class="style41 style38"><span class="style38">MyID Barusssss</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><label>
                                     #if($readmode == "disabled")
                                     <input name="txtNoKPBaru1PentingU" id="txtNoKPBaru1PentingU" style="width: 50px;" type="text"  size="7" maxlength="6" onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2PentingU')" onBlur="calculateTarikhLahirOBU();"  value="$txtNoKPBaru1PentingU" $readmoder class="$readmode" /> <!--onblur="getAgeByIC(this,this.value,'txtUmurPentingU');getDOB(this.value)"-->
                                     - 
                                     <input name="txtNoKPBaru2PentingU" id="txtNoKPBaru2PentingU" style="width: 20px;" type="text" size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$txtNoKPBaru2PentingU" $readmodeR class="$readmode" />
                                     - 
                                     <input name="txtNoKPBaru3PentingU" id="txtNoKPBaru3PentingU"  style="width: 40px;" type="text" $readmodesize="5" maxlength="4"  onblur="jantinaic1()" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$txtNoKPBaru3PentingU" $readmodeR class="$readmode" />
                                   #else
                                     <input name="txtNoKPBaru1PentingU" type="text" id="txtNoKPBaru1PentingU" style="width: 50px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2PentingU')" value="$txtNoKPBaru1PentingU" size="7" maxlength="6" $readmodekp onBlur="calculateTarikhLahirOBU();" /> <!--onBlur="getAgeByIC(this,this.value,'txtUmurPentingU');getDOBU(this.value)" /-->
                                     - 
                                     <input name="txtNoKPBaru2PentingU" type="text" id="txtNoKPBaru2PentingU" style="width: 20px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$txtNoKPBaru2PentingU" size="1" maxlength="2" $readmodekp />
                                     -
                                     <input name="txtNoKPBaru3PentingU" type="text" id="txtNoKPBaru3PentingU"  style="width: 40px;" onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$txtNoKPBaru3PentingU" size="5" maxlength="4" $readmodekp />
                                  #end
                                  </label>
                              #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")    
                                  #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style45" >  www.jpn.gov.my</a>
  #end                      #end            </td>
                                </tr>
                                <tr id="kp2">
                                  <td class="style38" ><span class="style40"></span></td>
                                  <td ><div align="left" class="style41 style38"><span class="style38">No Pendaftaran</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    #if($readmode == "disabled")
                                    <input name="txtNoKPLamaPentingU" type="text" id="textfield4" value="$txtNoKPLamaPentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" maxlength="15" $readmodeR class="$readmode" />
                                    
                                    #else
                                    <input name="txtNoKPLamaPentingU" type="text" id="textfield4" value="$txtNoKPLamaPentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" maxlength="15"  $readmodesy />
                                    
                                    #end
                                  </label></td>
                                </tr>
                                <tr id="kp3">
                                  <td class="style38" ><span class="style40"></span></td>
                                  <td ><div align="left" class="style41 style38"><span class="style38">Jenis MyID Lain</span></div></td>
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
									
									 <select name="socJenisKPLainPentingU"  class="mediumselect" $readmodekp id="socJenisKPLainPentingU" style="text-transform:uppercase;" onblur="kplain2X(this.value)" onchange="kplain2(this.value)">
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
                                  <tr id="kp4">
                                    <td class="style38"><span class="style40"></span></td>
                                    <td><div align="left" class="style41 style38"><span class="style38"> MyID Lain</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><span class="style36">
                                      #if($readmode == "disabled")
                                      <input name="txtNoKPLainPentingU" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoKPLainPentingU" size="15" maxlength="25" $readmodeR class="$readmode"  />
                                      #else
                                      <input name="txtNoKPLainPentingU" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoKPLainPentingU" size="15" maxlength="25" $readmodekp  />
                                      #end
                                    </span></td>
                                  </tr>
                                  <tr>
                                     <td valign="top" class="style38"><span class="style40">#if($readmode != "disabled")*</span>#end</span></td>
                                    <td><div align="left" class="style41 style38"><span class="style38"> #if($readmode != "disabled") Nama Pemiutang #else
                                      Nama Pemiutang
                                    #end </span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPentingU" type="text" id="txtNamaOBPentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNamaOBPentingU" size="34"  $readmodeR class="$readmode" />
                                    </label></td>
                                  </tr>
                                  <input type="hidden" name="socStatusOBU" id="socStatusOBU" value="" />
                                  
                                  <!--
                                  <tr>
                                  <td valign="top"><div align="right"><span class="style38">Status OB</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                              
                                    
                                       <select name="socStatusOBU"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
									
								   #if($socStatusOBU=="1")
	                                 
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                              
	                               #elseif($socStatusOBU=="2")
	                                


	                                  <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                     <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                    
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                              
								   #elseif($socStatusOBU=="3")
	                               
	                                 <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                    
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                     
                                   #elseif($socStatusOBU=="4")
	                                    <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                    
                                        
	                               #else
	                                 
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                               #end
                                   </select>                                  </td>
                                </tr>
                                  -->
                                
                                 <input type="hidden" name="socTarafKepentinganPentingU" id="socTarafKepentinganPentingU" value="$socTarafKepentinganPentingU" />
                                   <!--
                                   <tr>
                                          <td><div align="right"><span class="style38">
                                          
                                           #if($readmode != "disabled")
                                           <span class="style40">Taraf Kepentingan</span>                                    #else
                                       Taraf Kepentingan
                                    #end
                                          
                                       
                                          
                                     </span></div></td>
                                         
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                         #foreach($listtar in $listtaraf)
                                 
                                 #if($socTarafKepentinganPentingU==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
	                              
	                         
                                   
                                 #end    
	                               #end
                                         
                                       
	                                 <td>
	                               
	                              #if($socTarafKepentinganPentingU!="" && $socTarafKepentinganPentingU!="0")
                                  <select name="socTarafKepentinganPentingU" class="largeselect" $readmode id="socTarafKepentinganPentingU" style="text-transform:uppercase;" onblur="uppercase()">
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
                                  <select name="socTarafKepentinganPentingU" class="largeselect" $readmode id="socTarafKepentinganPentingU" style="text-transform:uppercase;" onblur="uppercase()">
                                   <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Taraf Kepentingan</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                    #if($listtar.id_Tarafkptg!=1 && $listtar.id_Tarafkptg!=2 && $listtar.id_Tarafkptg!=14)
	                               <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                   #end
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end                                      </tr>
                                   -->
                                  <tr id="jantina">
                                    <td class="style38"><span class="style40"></span></td>
                                  <td><div align="left" class="style41 style38"><span class="style38">Jantina</span></div></td>
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
                                
                                 <tr id="agama">
                                   <td class="style38"><span class="style40"></span></td>
                                  <td><div align="left" class="style41 style38"><span class="style38">Agama</span></div></td>
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
                                     #end</label></td>
                                </tr>
                                      
                                         <tr id="warga">
                                           <td class="style38"><span class="style40"></span></td>
                                  <td><div align="left" class="style41 style38"><span class="style38">Warganegara</span></div></td>
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
                                <tr id="dob">
                                  <td class="style38"><span class="style40"></span></td>
                                  <td><div align="right" class="style51 style38">
                                    <div align="left">Tarikh Lahir</div>
                                  </div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                      <input name="txdTarikhLahirPentingU" type="text" style="text-transform:uppercase;"  id="txdTarikhLahirPentingU" size="10" maxlength="10" $readmodeR class="$readmode" value="$txdTarikhLahirPentingU" onblur="trans_date1(this.value);getAgebyDob(this,this.value,'txtUmurPentingU');"/>
                                      #if($readmode != "disabled") 
                                    <a href="javascript:displayDatePicker('txdTarikhLahirPentingU',false,'dmy');">#parse("app/ppk/ppk_calender.jsp") </a></label>
                                    #end                                    </td>
                                </tr> 
                                <tr id="umur">
                                  <td class="style38" ><span class="style40"></span></td>
                                  <td ><div align="left" class="style41 style38"><span class="style38">Umur</span></div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><span class="style36">
                                    <input name="txtUmurPentingU" type="text" id="txtUmurPentingU"  value="$txtUmurPentingU" size="3" maxlength="3" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="CheckumurU()"  onkeyup="javascript:validateIC(event,this,this.value,'txtUmurPentingU')"/>
                                  </span></td>
                                </tr>
                                <tr style="display:none;" >
                                  <td class="style38"><span class="style40"></span></td>
                                    <td><div align="left" class="style41 style38"><span class="style38">No Surat Beranak</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNoSuratBeranakPentingU" type="text" id="txtNoSuratBeranakPentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoSuratBeranakPentingU" size="15" maxlength="10" $readmodeR class="$readmode" />
                                    </label></td>
                                  </tr>
                                <tr>
                                  <td valign="top" class="style38"><span class="style40">#if($readmode != "disabled")*</span>#end</span></td>
                                  <td><div align="left" class="style41 style38"><span class="style38">#if($readmode != "disabled")Nilai Hutang (RM)#else Nilai Hutang #end</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                                  
                                
                                  #if($txtNilaiHutangPentingU!="" || $txtNilaiHutangPentingU!="0.0")                            
                                    #set($txtNilaiHutangPentingU=$txtNilaiHutangPentingU)
                                    #end
                                 
                                    #if($txtNilaiHutangPentingU == "0.0")
                                    #set($txtNilaiHutangPentingU="0.00")
                                    #end
                                    
                                     #if($txtNilaiHutangPentingU=="" )
                                    #set($txtNilaiHutangPentingU="")
                                    #end
                                    
                                   
                                    #if($txtNilaiHutangPentingU!="")
                                    
                                    #if($readmode=="disabled")
                                    #set($txtNilaiHutangPentingU=$Util.formatDecimal($txtNilaiHutangPentingU))
                                    #else
                                    #set($txtNilaiHutangPentingU=$txtNilaiHutangPentingU)
                                    #end
                                    
                                    #else
                                   
                                    #set($txtNilaiHutangPentingU="")
                                    #end
                                    <label> #if($readmode=="disabled")
                                      <input name="txtNilaiHutangPentingU" type="text" id="txtNilaiHutangPentingU" style="text-transform:uppercase;" value="$txtNilaiHutangPentingU" size="34" maxlength="10"  $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiHutangPentingU')" />
                                      #else
                                      <input name="txtNilaiHutangPentingU" type="text" id="txtNilaiHutangPentingU" style="text-transform:uppercase;" value="$txtNilaiHutangPentingU" size="34" maxlength="10"  $readmode onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiHutangPentingU')" onblur="validateModal(this,this.value,'$txtNilaiHutangPentingU');" />
                                      #end </label>                                      </td>
                                </tr>
                               
                                <tr>
                                  <td class="style38"><span class="style40"></span></td>
                                  <td><div align="left" class="style41 style38"><span class="style38">No Akaun</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoAkaunPentingU" type="text" id="txtNoAkaunPentingU" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtNoAkaunPentingU" size="34" maxlength="15"  $readmodeR class="$readmode" />
                                  </label></td>
                                </tr>
                                <tr>
                                   <td width="1%" valign="top" class="style38 style40">#if($readmode != "disabled")*</span>#end</td>
                                    <td class="style38" width="28%"><div align="right" class="style51">
                                      <div align="left">#if($readmode != "disabled") Alamat Tetap #else <span class="style72">Alamat Tetap </span>#end</div>
                                    </div></td>
                                    <td width="1%"><div align="right" class="style38">:</div></td>
                                    <td width="70%"><label>
                                      <input name="txtAlamatTerakhir1PentingU" type="text" id="txtAlamatTerakhir1PentingU" value="$txtAlamatTerakhir1PentingU" size="34"  $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38 style40">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style41"></span></div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2PentingU" type="text" id="txtAlamatTerakhir2PentingU" value="$txtAlamatTerakhir2PentingU" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  />
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38 style40">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style41"></span></div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><input name="txtAlamatTerakhir3PentingU" type="text" id="txtAlamatTerakhir3PentingU" value="$txtAlamatTerakhir3PentingU" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" /></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style38 style40">#if($readmode != "disabled")*</span>#end</td>
                                    <td class="style38"><div align="right" class="style51">
                                      <div align="left">#if($readmode != "disabled") Poskod #else <span class="style72">Poskod</span> #end</div>
                                    </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                      <input name="txtPoskodPentingU" type="text" id="txtPoskodPentingU" value="$txtPoskodPentingU" size="5" maxlength="5" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPentingU')" />
                                    </label></td>
                                  </tr>
                                  
                                  <tr>
                                    <td valign="top" class="style38 style40">#if($readmode != "disabled")*</span>#end</td>
                                    <td class="style38"><div align="right" class="style51">
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

#end</td>
                                  </tr>
                                  <tr>
                                   <td valign="top" class="style38 style40">#if($readmode != "disabled")#end</td>
                                    <td class="style38"><div align="right" class="style51">
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
#end</label></td>
                                  </tr>
                                  
                                  
                                </table></td>
                                <td width="50%" valign="top">
                                
                                <table width="100%"  border="0" >
                                
                                  #if($readmode != "disabled")
                                  <tr>
                                    <td class="style38 style40" >&nbsp;</td>
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
                                    <td valign="top" class="style38 style40">#if($readmode != "disabled")*</span>#end</td>
                                    <td width="29%" class="style38" ><div align="right" class="style51">
                                      <div align="left">#if($readmode != "disabled") Alamat Surat #else <span class="style72">Alamat Surat </span>#end</div>
                                    </div></td>
                                    <td width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%"><label>
                                      <input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1WarisSurat" value="$txtAlamatTerakhir1WarisSurat" size="34"  $readmodeR class="$readmode"/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38 style40">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2WarisSurat"  value="$txtAlamatTerakhir2WarisSurat" size="34" $readmodeR class="$readmode" />
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38 style40">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3WarisSurat" value="$txtAlamatTerakhir3WarisSurat" size="34" $readmodeR class="$readmode" /></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style38 style40">#if($readmode != "disabled")*</span>#end</td>
                                    <td class="style38"><div align="right" class="style51">
                                      <div align="left">#if($readmode != "disabled") Poskod #else <span class="style72">Poskod</span> #end</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$txtPoskodWarisSurat" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmodeR class="$readmode"/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style38 style40">#if($readmode != "disabled")*</span>#end</td>
                                    <td class="style38"><div align="right" class="style51">
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
#end </td>
                                  </tr>
                                  <tr>
                                   <td valign="top" class="style38 style40">#if($readmode != "disabled")#end</td>
                                    <td class="style38"><div align="right" class="style51">
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
#end
 </label></td>
                                  </tr>
                                  
                                  
                                        <tr>
                                          <td class="style38 style40" >&nbsp;</td>
                                          <td class="style38" ><div align="right" class="style51">
                                            <div align="left">No Telefon</div>
                                          </div></td>
                                          <td >:</td>
                                          <td><input name="txtNoTeleponPentingU" type="text" id="txtNoTeleponPentingU" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponPentingU')" value="$txtNoTeleponPentingU" size="14" maxlength="12" $readmodeR class="$readmode" /></td>
                                        </tr>
                                        #if($readmode != "disabled")
                                        <tr>
                                          <td valign="top" class="style38 style40">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"><span class="style41"></span></div></td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top" ><span class="style42 style44"><em><span class="style50">cth: 031234567</span></em></span></td>
                                        </tr>
                                        #end
                                        <tr>
                                          <td valign="top" class="style38 style40">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="right" class="style51">
                                            <div align="left">Butiran Hutang</div>
                                          </div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtButiranHutangPentingU" cols="31"  rows="3" $readmodeR class="$readmode" >$txtButiranHutangPentingU</textarea></td>
                                      </tr>
                                      
                                     <tr id="tr_flag_daftar"  style="display:none">
                                          <td valign="top"></td>
                                          <td  valign="top">Urusan Kemasukkan Maklumat Pemiutang
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
  <td><span class="style5"><span class="style2 style44 style40">Perhatian</span><span class="style44"> : Sila masukkan salah satu nombor MyID atau no syarikat pemiutang dan pastikan label bertanda <span class="style40">*</span> diisi</span>.</span></td>
  
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
                            <legend>MAKLUMAT PEMIUTANG (Pihak yang memberi hutang kepada Simati)</legend>
                            
                            <table width="100%" border="0">
                              <tr>
                                <td width="50%" valign="top"><table width="100%">
                               <input type="hidden" name="txtIdSimatiPenting" value="$id_Simati" >        
                                  <tr>
                                    <td valign="top" class="style38" width="1%"  ><span class="style40">*</span></span></td>
                                    <td width="28%" ><div align="left" class="style51">#if($readmode != "disabled")Jenis Pemiutang#else Jenis Pemiutang #end</div></td>
                                    <td width="1%" class="style36">:</td>
                                     <td width="70%" class="style36"><select name="socJenisHutangPenting"  class="autoselect" $readmode id="socJenisHutangPenting" style="text-transform:uppercase;" onblur="uppercase()" onchange="jenis_hutang(this.value);pilih_taraf()">
                                      
                                      #if($socJenisHutangPenting == "1")
                                         <option value="1">01-Agensi</option>
                                         <option value="2">02-Individu</option>
                                      
                                      
                                      #elseif($socJenisHutangPenting == "2")
                                       <option value="2">02-Individu</option>
                                       <option value="1">01-Agensi</option>                                         
                                     
                                      
                                      #else
                                      
                                       
                                         <option value="1">01-Agensi</option>
                                         <option value="2">02-Individu</option>
                                         
                                      #end 
                                       </select></td>
                                   </tr>
                                  <tr id="kp1">
                                    <td class="style38"><span class="style40">#if($readmode != "disabled")*</span>#end</span></td>
                                    <td width="29%" ><div align="left" class="style51">MyID Baru</div></td>
                                    #if($readmode!="disabled")
                                      
                                      #if($socJenisHutangPenting=="1")
                                      #set($readmodesy="")
                                      #set($readmodekp="disabled")
                                      #set($readmodeln="disabled")
                                      
                                      #set($txtNoKPBaru1Penting = "")
                                      #set($txtNoKPBaru2Penting = "")
                                      #set($txtNoKPBaru3Penting = "")
                                      #set($socJenisKPLainPenting = "")
                                      #set($txtNoKPLainPenting = "")
                                      
                                      
                                      
                                      #elseif($socJenisHutangPenting=="2")
                                      #set($readmodesy="disabled")
                                      #set($readmodekp="")
                                      #set($readmodeln="")
                                      #set($txtNoKPLamaPenting = "")
                                      
                                      #else
                                      #set($readmodesy="disabled")
                                      #set($readmodekp="disabled")
                                      #set($readmodeln="disabled")
                                      
                                      #end                                    
                                      
                                      #end 
                                  
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><label>
                                  <input name="txtNoKPBaru1Penting" id="txtNoKPBaru1Penting" style="width: 50px;" type="text" $readmode size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Penting')" value="$txtNoKPBaru1Penting" $readmodekp onBlur="calculateTarikhLahirOB();"/> <!--onBlur="getAgeByIC(this,this.value,'txtUmurPenting');getDOB(this.value)" -->
                                     - 
                                     <input name="txtNoKPBaru2Penting" id="txtNoKPBaru2Penting" style="width: 20px;" type="text" $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')" value="$txtNoKPBaru2Penting" $readmodekp />
                                     - 
                                     <input name="txtNoKPBaru3Penting" id="txtNoKPBaru3Penting"  style="width: 40px;" type="text" $readmodesize="5" maxlength="4"  onblur="jantinaic1()" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')" value="$txtNoKPBaru3Penting" $readmodekp />
                           #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                                     <a href="http://www.jpn.gov.my" target="_blank" class="style45" > www.jpn.gov.my</a> 
                                     
                                     #end
                                     </label></td>
                                </tr>
                                <tr id="kp2">
                                  <td class="style38" ><span class="style40"></span></td>
                                  <td ><div align="left" class="style51">No Pendaftaran</div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting" type="text" id="textfield4" size="15" maxlength="15" $readmodesy style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoKPLamaPenting"/>
                                  </label></td>
                                </tr>
                                <tr id="kp3">
                                  <td class="style38" ><span class="style40"></span></td>
                                  <td ><div align="left" class="style51">Jenis MyID Lain</div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                                  
                                   
									
									 <select name="socJenisKPLainPenting"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="kplain1X(this.value)" onchange="kplain1(this.value)" $readmodekp >
								   #if($socJenisKPLainPenting=="5")
	                                 
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                       <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                      
	                              
	                               #elseif($socJenisKPLainPenting=="6")
	                                
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                        <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                      
	                              
								   #elseif($socJenisKPLainPenting=="4")
	                               
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                        <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                      
                                      #elseif($socJenisKPLainPenting=="7")
	                               
                                    <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                       
                                      <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                      
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
                                
                          
                              
                              
                              #if($readmodekp=="disabled")
                              #set($readmodekp1="disabled")
                              #end
                              
                              #if($readmodekp=="")
                              #if($socJenisKPLainPenting != 0 && $socJenisKPLainPenting != "")
                               #set($readmodekp1="")
                              #else
                              #set($readmodekp1="disabled")
                              #set($txtNoKPLainPenting="")
                              #end
                               
                               
                              #end
                              
                                
                                
                                  <tr  id="kp4">
                                    <td class="style38"><span class="style40"></span></td>
                                    <td><div align="left" class="style51"> MyID Lain</div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><span class="style36">
                                      <input name="txtNoKPLainPenting" type="text" id="textfield5" size="15" maxlength="25" $readmodekp1 style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoKPLainPenting" />
                                    </span></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style38"><span class="style40">*</span></span></td>
                                    <td><div align="left" class="style51"> #if($readmode != "disabled") Nama Pemiutang #else
                                      Nama Pemiutang
                                      #end </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtNamaOBPenting" />
                                    </label></td>
                                  </tr>
                                  <input type = "hidden" name="socStatusOB" id="socStatusOB" value="" /> 
                                  <!--
                                  <tr>
                                  <td valign="top"><div align="right"><span class="style38">Status OB</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                              
                                    
                                       <select name="socStatusOB"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
									
								   #if($socStatusOB=="1")
	                                 
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                              
	                               #elseif($socStatusOB=="2")
	                                
	                                  <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>                             
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                              
								   #elseif($socStatusOB=="3")
	                               
	                                 <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                    
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                     
                                   #elseif($socStatusOB=="4")
	                                    <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                    
                                        
	                               #else
	                                 
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                               #end
                                   </select>                                  </td>
                                </tr>
                                  -->
                               
                     <input type="hidden" name="socTarafKepentinganPenting" id="socTarafKepentinganPenting" value="2" />
                                
                                   <!--
                                   <tr>
                                          <td><div align="right" class="style40"><span class="style38">Taraf Kepentingan</span></div></td>
                                         
                                     <td><div align="right"><span class="style38">:</span></div></td>
                                         #foreach($listtar in $listtaraf)
                                 
                                 #if($socTarafKepentinganPenting==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
	                              
	                         
                                   
                                 #end    
	                               #end
                                         
                                       
	                                 <td>
	                            
	                              #if($socTarafKepentinganPenting!="" && $socTarafKepentinganPenting!="0")
                                 
	                              <select name="socTarafKepentinganPenting" class="largeselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
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
                                  <select name="socTarafKepentinganPenting" class="largeselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                   <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Taraf Kepentingan</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                #if($listtar.id_Tarafkptg!=1 && $listtar.id_Tarafkptg!=2 && $listtar.id_Tarafkptg!=14)
	                               <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                   
                                 #end
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end                                      </tr>
                                   -->
                                  <tr id="jantina">
                                    <td class="style38"><span class="style40"></span></td>
                                    <td><div align="left" class="style51">Jantina</div></td>
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
                                   <td class="style38"><span class="style40"></span></td>
                                   <td><div align="left" class="style51">Agama</div></td>
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
                                           <td class="style38"><span class="style40"></span></td>
                                           <td><div align="left" class="style51">Warganegara</div></td>
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
                                  <td class="style38"><span class="style40"></span></td>
                                  <td><div align="right" class="style51">
                                      <div align="left">Tarikh Lahir</div>
                                  </div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td>
                                  <input name="txdTarikhLahirPenting" type="text" id="txdTarikhLahirPenting" size="10" maxlength="10" $readmode  onblur="trans_date(this.value);getAgebyDob(this,this.value,'txtUmurPenting');" value="$txdTarikhLahirPenting" />
                                   <a href="javascript:displayDatePicker('txdTarikhLahirPenting',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> </td>
                                </tr>
                                <tr id="umur">
                                  <td class="style38" ><span class="style40"></span></td>
                                  <td ><div align="left" class="style51">Umur</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><span class="style36">
                                    <input name="txtUmurPenting" type="text" id="txtUmurPenting"  size="3" maxlength="3" $readmode style="text-transform:uppercase;" onblur="Checkumur()" onKeyUp="javascript:validateIC(event,this,this.value,'txtUmurPenting')"  value="$txtUmurPenting"/>
                                  </span></td>
                                </tr>
                                <tr style="display:none;">
                                  <td class="style38"><span class="style40"></span></td>
                                  <td><div align="left" class="style51">No Surat Beranak</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNoSuratBeranakPenting" type="text" id="txtNoSuratBeranakPenting" size="15" maxlength="10" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtNoSuratBeranakPenting" />
                                    </label></td>
                                  </tr>
                                <tr>
                                  <td valign="top" class="style38"><span class="style40">*</span></td>
                                  <td><div align="left" class="style51">#if($readmode != "disabled")Nilai Hutang (RM)#else Nilai Hutang #end</div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36">
                                    <label>
                                      <input name="txtNilaiHutangPenting" type="text" id="txtNilaiHutangPenting" style="text-transform:uppercase;"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiHutangPenting')" value="$txtNilaiHutangPenting" size="34" maxlength="10"  $readmode onblur="validateModal(this,this.value,'$txtNilaiHutangPenting');" />
                                    </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><span class="style40"></span></td>
                                  <td><div align="left" class="style51">No Akaun</div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoAkaunPenting" type="text" id="txtNoAkaunPenting"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  size="34" maxlength="15" value="$txtNoAkaunPenting"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                    <td width="1%" valign="top" class="style38 style40">*</span></td>
                                    <td class="style38" width="28%"><div align="right" class="style51">
                                        <div align="left">#if($readmode != "disabled") Alamat Tetap #else <span class="style72">Alamat Tetap </span>#end</div>
                                    </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir1Penting" type="text" id="txtAlamatTerakhir1Penting" size="34"  $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir1Penting" />
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38 style40">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style41"></span></div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2Penting" type="text" id="txtAlamatTerakhir2Penting" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$txtAlamatTerakhir2Penting"/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38 style40">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style41"></span></div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><input name="txtAlamatTerakhir3Penting" type="text" id="txtAlamatTerakhir3Penting" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$txtAlamatTerakhir3Penting"/></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style38 style40">*</span></td>
                                    <td class="style38"><div align="right" class="style51">
                                        <div align="left">#if($readmode != "disabled") Poskod #else <span class="style72">Poskod</span> #end</div>
                                    </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td><label>
                                      <input name="txtPoskodPenting" type="text" id="txtPoskodPenting" style="text-transform:uppercase;" onblur="uppercase()" size="5" maxlength="5" $readmode onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPenting')" value="$txtPoskodPenting"/>
                                    </label></td>
                                  </tr>
                                  
                                  <tr>
                                    <td valign="top" class="style38 style40">*</span></td>
                                    <td class="style38"><div align="right" class="style51">
                                        <div align="left">#if($readmode != "disabled") Negeri #else <span class="style72">Negeri</span> #end</div>
                                    </div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                   
                                    #foreach($listnegpomo in $listnegeri)
                                    
                                    #if($socNegeriPenting==$listnegpomo.id_Negeri)
                                    
                                    #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                    #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                    
                                    
                                    
                                    #end 
                                    #end
                                    <td> #if($socNegeriPenting!="" && $socNegeriPenting!="0" )
                                <select name="socNegeriPenting" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="getBandarTetap('txtBandarPenting')">
                                          <option value="$socNegeriPenting" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($socNegeriPenting!=$listnegpomo.id_Negeri)
                                    
	                               
        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
        
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
      
                                      </select>
                                      #else
                                      <select name="socNegeriPenting" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="getBandarTetap('txtBandarPenting')">
                                        <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                                        
        
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
        
                                        <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                        
        
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
      
                                      </select>                                    </td>
                                    #end </tr>
                                  <tr>
                                    <td valign="top" class="style38 style40"></span></td>
                                    <td class="style38"><div align="right" class="style51">
                                        <div align="left">#if($readmode != "disabled") Bandar #else <span class="style72">Bandar</span> #end</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>#if($txtBandarPenting == "" || $txtBandarPenting=="0")
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
#end </label></td>
                                  </tr>
                                
                                  
                                </table></td>
                                <td width="50%" valign="top"><table width="100%" border="0">
                                  
                                  <tr>
                                    <td class="style38 style40" >&nbsp;</td>
                                    <td class="style38" ><div align="left"><span class="style41"></span></div></td>
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
                                    <td valign="top" class="style38 style40">*</td>
                                    <td width="29%" class="style38" ><div align="right" class="style51">
                                        <div align="left">#if($readmode != "disabled") Alamat Surat #else <span class="style72">Alamat Surat </span>#end</div>
                                    </div></td>
                                    <td width="1%"><div align="right"><span class="style38">:</span></div></td>
                                    <td width="70%"><label>
                                   
                                    
                                      <input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir1WarisSurat" size="34"  $readmode value="$txtAlamatTerakhir1WarisSurat"/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38 style40">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir2WarisSurat"   size="34" $readmode  value="$txtAlamatTerakhir2WarisSurat"/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38 style40">&nbsp;</td>
                                    <td class="style38"><div align="left"><span class="style38"><span class="style41"></span></span></div></td>
                                    <td>&nbsp;</td>
                                    <td><input name="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" type="text" id="txtAlamatTerakhir3WarisSurat"  size="34" $readmode  value="$txtAlamatTerakhir3WarisSurat"/></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style38 style40">*</td>
                                    <td class="style38"><div align="right" class="style51">
                                        <div align="left">#if($readmode != "disabled") Poskod #else <span class="style72">Poskod</span> #end</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()"  size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmode value="$txtPoskodWarisSurat"/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style38 style40">*</td>
                                    <td class="style38"><div align="right" class="style51">
                                        <div align="left">#if($readmode != "disabled") Negeri #else <span class="style72">Negeri</span> #end</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td>
                                    
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
#end </td>
                                  </tr>
                                  <tr>
                                    <td valign="top" class="style38 style40"></span></td>
                                    <td class="style38"><div align="right" class="style51">
                                        <div align="left">#if($readmode != "disabled") Bandar #else <span class="style72">Bandar</span> #end</div>
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
                                          <td class="style38 style40" >&nbsp;</td>
                                          <td class="style38" ><div align="right" class="style51">
                                              <div align="left">No Telefon</div>
                                          </div></td>
                                          <td >:</td>
                                          <td><input name="txtNoTeleponPenting" type="text" id="txtNoTeleponPenting" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponPenting')" size="14" maxlength="12" $readmode value="$txtNoTeleponPenting"  /></td>
                                        </tr>
                                        
                                        <tr>
                                          <td valign="top" class="style38 style40">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"><span class="style41"></span></div></td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top" ><em><span class="style50">cth: 031234567</span></em></td>
                                        </tr>
                                        
                                        <tr>
                                          <!-- <td valign="top" class="style38 style40">&nbsp;</td> -->
                                          <td valign="top" class="style38 style40">*</td>
                                          <td class="style38" valign="top"><div align="right" class="style51">
                                              <div align="left">Butiran Hutang</div>
                                          </div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtButiranHutangPenting" cols="31"  rows="3"  $readmode  >$txtButiranHutangPenting</textarea></td>
                                      </tr>
                                      
                                      <tr id="tr_flag_daftar"  style="display:none">
                                          <td valign="top"></td>
                                          <td  valign="top">Urusan Kemasukkan Maklumat Pemiutang
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
  <td><span class="style5"><span class="style2 style44 style40">Perhatian</span><span class="style44"> : Sila masukkan salah satu nombor MyID atau no syarikat pemiutang dan pastikan label bertanda <span class="style40">*</span> diisi</span>.</span> </td>
  
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
                                   #if($open_button_online == "yes") #if($buttonpenting=="tambah")
                              
                                   <input type="button" name="tambahpenting" id="tambahpenting2" value="Simpan" onclick="setSelected(0,3,0,0);tambah_simpan_penting()"/>
                                   #if($!skrin_online != "yes") 								
                              <input type="button" name="batalpenting" id="cmdSimpan3" value="Batal" onclick="setSelected(0,3,0,0);cancelwaris()"/>		
                              #end                                 
                                  #else
                     #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                    <!-- <input type="button" name="tambahpenting" id="tambahpenting3" value="$buttonpenting" onclick="setSelected(0,3,0,0);tambah_penting()"/>-->
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
                     <input type="button" name="tambahpenting" id="tambahpenting" value="Simpan" onclick="setSelected(0,3,0,0);tambah_penting()"/> 
                     #end
                    
                     
                                    #if($buttonpenting=="Simpan")
                                    #if($!skrin_online != "yes") 
                                    <input type="button" name="batalpentingupdate" id="cmdSimpan3" value="Batal" onclick="setSelected(0,3,0,0);batalpenting()"/>
                                    #end
                                      #end
                                      
                                      #if($buttonpenting == "Kemaskini" )
                                        
                #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
             <!--   <input type="button" name="hapuspenting" id="hapuspenting" value="Hapus"  onclick="setSelected(0,3,0,0);hapus_penting()" />  -->
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
                                     
                                    <input type="submit" name="cmdKembali3" id="cmdKembali3" value="Kembali"  onclick="setSelected(0,3,0,0);PemiutangView()" /> 
                                    #end                                    </td>
                              </tr>
                                  
                                </table></td>
                          </tr>
                          
                           
                            
                            
                        #end 
                            
                            
                          <tr>
                            <td>
                  <input type="hidden" name="idOb" value="" />
                            
                            <fieldset>
                            <legend>SENARAI PEMIUTANG</legend>
                            
                         
                              <table width="100%" >
                                <tr>
                                  <td width="100%">#if($tambah_ob_button == "yes")
                   #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                              <!--      <label>
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
#end </td>
                                </tr>
                                <tr>
                                  <td> #set($saksic=0)
                                    #foreach($listpenting in $listPenting)
                                    #if($listpenting.taraf==2)
                                    #set($saksic=$saksic+1)
                                    #end
                                    #end
                                    
                                    #if($listPenting.size()!=0 && $saksic!=0)
                                    <table width="100%">
                                          <tr  class="table_header">
                                            <td width="2%"><div align="center">NO</div></td>
                                            <td width="25%"><div align="left">NAMA PEMIUTANG</div></td>
                                            <td width="15%"><div align="center">MyID BARU</div></td>
                                            <td width="12%"><div align="left">NO SYARIKAT</div></td>
                                            <td width="12%"><div align="left">NO AKAUN</div></td>
                                            <td width="10%"><div align="center">NILAI HUTANG(RM)</div></td>
                                            <td width="24%"><div align="left">BUTIRAN HUTANG</div></td>
                                          </tr>
                                      #set($peno=0)
                                      #foreach($listpenting in $listPenting)
                                      
                                      #if($listpenting.taraf==2)
                                      
                                      #set($peno=$peno+1)
                                      
                                      #if($peno%2!=0)
                                      <tr bgcolor="white">
                                        <td class="row1"><div align="center" class="style41">$peno</div></td>
                                        <td class="row1" style="text-transform:uppercase;"><a href="javascript:edit_item('$listpenting.idOb')">
                                          <div align="left" class="style42" > $listpenting.nama_Ob</div>
                                          <input type="hidden" name="idob" value="$listpenting.idOb" />
                                        </a> </td>
                                        <td class="row1" style="text-transform:uppercase;"><div align="center" >$listpenting.nokpbaru</div></td>
                                        <td class="row1" style="text-transform:uppercase;"><div align="left" >$listpenting.nokplama</div></td>
                                        <td class="row1" style="text-transform:uppercase;"><div align="left" >$listpenting.noakaun</div></td>
                                        <td class="row1" style="text-transform:uppercase;"><div align="right" > #if($listpenting.nilaihutang!="" && $listpenting.nilaihutang > 0)
                                          $Util.formatDecimal($listpenting.nilaihutang)
                                          #else
                                          0.00
                                          #end </div></td>
                                        <td class="row1" style="text-transform:uppercase;"><div align="left" >$listpenting.butiranhutang</div></td>
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
                                        #end </tr>
                                      #else
                                      <tr >
                                        <td class="row2"><div align="center" class="style41">$peno</div></td>
                                        <td class="row2" style="text-transform:uppercase;"><a href="javascript:edit_item('$listpenting.idOb')">
                                          <div align="left" class="style42"> $listpenting.nama_Ob</div>
                                          <input type="hidden" name="idob" value="$listpenting.idOb" />
                                        </a> </td>
                                        <td class="row2" style="text-transform:uppercase;"><div align="center">$listpenting.nokpbaru</div></td>
                                        <td class="row2" style="text-transform:uppercase;"><div align="left" >$listpenting.nokplama</div></td>
                                        <td class="row2" style="text-transform:uppercase;"><div align="left" >$listpenting.noakaun</div></td>
                                        <td class="row2" style="text-transform:uppercase;"><div align="right" >
                                        #if($listpenting.nilaihutang!="" && $listpenting.nilaihutang > 0)
                                          $Util.formatDecimal($listpenting.nilaihutang)
                                          #else
                                          0.00
                                          #end
                                        
                                        </div></td>
                                        <td class="row2" style="text-transform:uppercase;"><div align="left" >$listpenting.butiranhutang</div></td>
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
                                        #end </tr>
                                      #end
                                      #end
                                      #end
                                    </table>
                                    #else
                                    <table width="100%">
                                          <tr  class="table_header">
                                            <td><div align="center">NO</div></td>
                                            <td><div align="center">NAMA PEMIUTANG</div></td>
                                            <td><div align="center">MyID BARU</div></td>
                                            <td><div align="center">NO SYARIKAT</div></td>
                                            <td><div align="center">NO AKAUN</div></td>
                                            <td><div align="center">NILAI HUTANG(RM)</div></td>
                                            <td><div align="center">BUTIRAN HUTANG</div></td>
                                          </tr>
                                        </table>
                                    <table width="100%" bgcolor="#FFFFFF">
                                          <tr>
                                            <td width="100%"><div align="left">Tiada Rekod</div></td>
                                          </tr>
                                        </table>
                                    #end </td>
                                </tr>
                              </table>
                            </fieldset>                            </td>
                          </tr>
                            <tr>
                <td>#if($!skrin_online != "yes")
                <p align="right">
                
                CL - 01 - 74</p>#end
                </td>
                </tr>
                    </table>
            
            </div>
            
            
          
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
	document.f1.command.value = "Pemiutang";
	document.f1.action = "";
	document.f1.submit();
}


function simpan_penting(){
	document.f1.mode.value = "simpan_penting";
	document.f1.command.value = "Pemiutang";
	document.f1.action = "";	
	document.f1.submit();	
}

function tambah_penting_baru(){

    document.f1.mode.value = "tambah_penting_baru";
	document.f1.command.value = "Pemiutang";	
	document.f1.action = "";
	document.f1.submit();

}

function hapus_penting(){
if( document.f1.id_Pemohon.value != "" && document.f1.id_Pemohon.value != "0")
{
alert("Saksi ini adalah seorang pemohon, maklumat orang berkepenting ini tidak dapat dihapuskan. Sebarang perubahan hendaklah dilakukan di tab pemohon!");
		//txtNoKPLainPenting.focus();
		return;
		
}
else
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.f1.mode.value = "hapus_penting";
	document.f1.command.value = "Pemiutang";	
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
	document.f1.command.value = "Pemiutang";	
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


if (document.f1.socJenisHutangPenting.value=="" || document.f1.socJenisHutangPenting.value=="0")
 {
		alert("Sila pilih jenis pemiutang");
		document.f1.socJenisHutangPenting.focus();		 
	} 

else if (document.f1.txtNamaOBPenting.value=="")
 {
		alert("Sila masukkan nama pemiutang");
		document.f1.txtNamaOBPenting.focus();		 
	} 
	/*
	else if (document.f1.socTarafKepentinganPenting.value=="" || document.f1.socTarafKepentinganPenting.value=="0")
    {
		alert("Sila pilih taraf kepentingan");
		document.f1.socTarafKepentinganPenting.focus();		 
	} 
	*/
	
	else if (document.f1.txtNoKPBaru1Penting.value=="" && document.f1.txtNoKPBaru2Penting.value=="" && document.f1.txtNoKPBaru3Penting.value=="" && document.f1.txtNoKPLamaPenting.value=="" && (document.f1.socJenisKPLainPenting.value=="" || document.f1.socJenisKPLainPenting.value=="0" )&& document.f1.txtNoKPLainPenting.value=="" && document.f1.socJenisHutangPenting.value != '1') {
	/*
	    if(document.f1.socJenisHutangPenting.value == '1')
		{
		alert("Sila masukkan no syarikat pemiutang ");
		document.f1.txtNoKPLamaPenting.focus();
		return;
	
		}
		else
		{*/
		alert("Sila masukkan salah satu nombor MyID pemiutang");
		document.f1.txtNoKPBaru1Penting.focus();
		return;
		
		//}
		}
		
		
		
	else if ((document.f1.socJenisKPLainPenting.value!="" && document.f1.socJenisKPLainPenting.value!="0")  && document.f1.txtNoKPLainPenting.value=="")
	 {
	 	alert("Sila masukkan nombor MyID lain pemiutang");
		document.f1.txtNoKPLainPenting.focus();
		return; 
	 }
	 else if ((document.f1.socJenisKPLainPenting.value=="" && document.f1.txtNoKPLainPenting.value!=""))
	 {
	 	alert("Sila pilih jenis MyID lain pemiutang");
		document.f1.socJenisKPLainPenting.focus();
		return; 
	 }
	 
	 else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru1Penting.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru1Penting.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru2Penting.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru2Penting.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru3Penting.value==""))
	 {
	 	alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru3Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru1Penting.value!=""  && document.f1.txtNoKPBaru1Penting.value.length < 6 ) {
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru1Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Penting.value!="" && document.f1.txtNoKPBaru2Penting.value.length < 2 ) {
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru2Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Penting.value!="" && document.f1.txtNoKPBaru3Penting.value.length < 4) {
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru3Penting.focus();
		return; 
	}
	
	else if (document.f1.txtPoskodPenting.value != "" && document.f1.txtPoskodPenting.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodPenting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru1Penting.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1Penting.focus()
		return false
	}

/*

else if (document.f1.txtNoKPBaru2Penting.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
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
	
	else if (dat1.value!="" && isDate(dat1.value)==false)
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
	alert("Sila lengkapkan alamat tetap pemiutang");
	document.f1.txtAlamatTerakhir1Penting.focus()
	return;
	}
	
	
	 else if ((document.f1.txtAlamatTerakhir1WarisSurat.value == "" || document.f1.txtPoskodWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"  ))
	{
	alert("Sila lengkapkan alamat surat menyurat pemiutang");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	
	else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir pemiutang tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirPenting.focus();
   } 
   	else if (document.f1.txtNilaiHutangPenting.value < 0 || document.f1.txtNilaiHutangPenting.value == 0) {
		alert("Sila masukkan nilai hutang");
		document.f1.txtNilaiHutangPenting.focus();
		return; 
	}
   	else if(document.f1.txtButiranHutangPenting.value == ""){
		alert('Sila masukkan Butiran Hutang terlebih dahulu.');
  		document.f1.txtButiranHutangPenting.focus(); 
		return; 
	}
	
	else{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true)
		 {
		
	document.f1.mode.value = "tambah_penting";
	document.f1.command.value = "Pemiutang";
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

//alert("Test2");	
var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	 var date_dob = new Date(yr_dob, mon_dob, dt_dob);


	 //alert("Test3");
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
   
	
if(document.f1.socJenisHutangPentingU.value == "" || document.f1.socJenisHutangPentingU.value == "0")
{
alert("Sila pilih jenis pemiutang");
		document.f1.socJenisHutangPentingU.focus();	
		return;	 
}
else if (document.f1.txtNamaOBPentingU.value=="")
 {
		alert("Sila masukkan nama pemiutang");
		document.f1.txtNamaOBPentingU.focus();	
		return;	 
	} 
	
	
	
	else if (document.f1.txtNoKPBaru1PentingU.value=="" && document.f1.txtNoKPBaru2PentingU.value=="" && document.f1.txtNoKPBaru3PentingU.value=="" && document.f1.txtNoKPLamaPentingU.value=="" && (document.f1.socJenisKPLainPentingU.value=="" || document.f1.socJenisKPLainPentingU.value=="0") && document.f1.txtNoKPLainPentingU.value=="" && document.f1.socJenisHutangPentingU.value != '1') {
		
		/*
		if(document.f1.socJenisHutangPentingU.value == '1')
		{
		alert("Sila masukkan no syarikat pemiutang ");
		document.f1.txtNoKPLamaPentingU.focus();
		return;
		}
		else
		{*/
		alert("Sila masukkan salah satu nombor MyID pemiutang");
		document.f1.txtNoKPBaru1PentingU.focus();
		return;
		
		//}
		
		}
		
		
		
		
	else if ((document.f1.socJenisKPLainPentingU.value!="" && document.f1.socJenisKPLainPentingU.value!="0" && document.f1.txtNoKPLainPentingU.value==""))
	 {
	 	alert("Sila masukkan nombor MyID lain pemiutang");
		document.f1.txtNoKPLainPentingU.focus();
		return; 
	 }
	 else if ((document.f1.socJenisKPLainPentingU.value=="" || document.f1.socJenisKPLainPentingU.value=="0") && document.f1.txtNoKPLainPentingU.value!="")
	 {
	 	alert("Sila pilih jenis MyID lain pemiutang");
		document.f1.socJenisKPLainPentingU.focus();
		return; 
	 }
	 
	 else if ((document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru1PentingU.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru1PentingU.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru2PentingU.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru2PentingU.focus();
		return; 
	}
	
	
	
	
	else if ((document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru3PentingU.value==""))
	 {
	 	alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru3PentingU.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru1PentingU.value!=""  && document.f1.txtNoKPBaru1PentingU.value.length < 6 ) {
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru1PentingU.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2PentingU.value!="" && document.f1.txtNoKPBaru2PentingU.value.length < 2 ) {
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru2PentingU.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3PentingU.value!="" && document.f1.txtNoKPBaru3PentingU.value.length < 4) {
		alert("Sila masukkan nombor MyID pemiutang sepenuhnya");
		document.f1.txtNoKPBaru3PentingU.focus();
		return; 
	}
	
	else if (document.f1.txtPoskodPentingU.value != "" && document.f1.txtPoskodPentingU.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodPentingU.focus();
		return; 
	}
	
	else if (document.f1.txtNoKPBaru1PentingU .value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1PentingU .focus()
		return false
	}

/*

else if (document.f1.txtNoKPBaru2PentingU.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
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
	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		document.f1.txdTarikhLahirPentingU.focus()
		return false
	}
	
	 else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir pemiutangtidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhLahirPentingU.focus();
   } 
	
	else if (document.f1.txtPoskodWarisSurat.value != "" && document.f1.txtPoskodWarisSurat.value.length < 5) {
		alert("Sila masukkan nombor poskod untuk alamat surat menyurat dengan lengkapnya");
		document.f1.txtPoskodWarisSurat.focus();
		return; 
	}
	

	
	
	
	else if ((document.f1.txtAlamatTerakhir1PentingU.value == "" || document.f1.txtPoskodPentingU.value == ""  || document.f1.socNegeriPentingU.value == "" || document.f1.socNegeriPentingU.value == "0"))
	{
	alert("Sila lengkapkan alamat tetap pemiutang");
	document.f1.txtAlamatTerakhir1PentingU.focus()
	return;
	}
	
	 else if ((document.f1.txtAlamatTerakhir1WarisSurat.value == "" || document.f1.txtPoskodWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))
	{
	
	alert("Sila lengkapkan alamat surat menyurat pemiutang");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	
		else if (document.f1.txtNilaiHutangPentingU.value < 0 || document.f1.txtNilaiHutangPentingU.value == 0) {
		alert("Sila masukkan nilai hutang");
		document.f1.txtNilaiHutangPentingU.focus();
		return; 
	}
	
	else{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true)
		 {
		
	
   	document.f1.mode.value = "simpan_penting";
	document.f1.command.value = "Pemiutang";	
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
	document.f1.command.value = "Pemiutang";	
	document.f1.action = "";
	document.f1.simpanmode.value = "kemaskinipenting";
	document.f1.submit();
	
	}
}

function edit_item(idOb) 
{

document.f1.action = "";
	document.f1.mode.value = "GetPenting";
	document.f1.command.value = "Pemiutang";
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
		alert("Adakah anda pasti pemiutang berumur "+document.f1.txtUmurPentingU.value+" tahun?");
		txtUmurPentingU.focus();
		return; 
	}
}
function Checkumur() 
{
	if (document.f1.txtUmurPenting.value != "" && document.f1.txtUmurPenting.value>100) {
		alert("Adakah anda pasti pemiutang berumur "+document.f1.txtUmurPenting.value+" tahun?");
		txtUmurPenting.focus();
		return; 
	}
}
	
function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
 document.f1.mode.value = "tambah_penting_baru";
	document.f1.command.value = "Pemiutang";	
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
	

} 
*/
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
	document.f1.command.value = "Pemiutang";
	document.f1.simpanmode.value = "insert";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
}

function getBandarTetapCopy(v_t)
{
if(document.f1.copyAlamat.checked == true)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "copy";
	document.f1.command.value = "Pemiutang";
	document.f1.simpanmode.value = "insert";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
	}
	if(document.f1.copyAlamat.checked == false)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "copyfalse";
	document.f1.command.value = "Pemiutang";
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
	document.f1.command.value = "Pemiutang";
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
	document.f1.command.value = "Pemiutang";
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
	document.f1.command.value = "Pemiutang";
	document.f1.simpanmode.value = "update";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
	}
	if(document.f1.copyAlamat.checked == false)
{
	document.f1.mode.value = "getBandar";
	document.f1.bandar.value = "copyfalse";
	document.f1.command.value = "Pemiutang";
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
	document.f1.command.value = "Pemiutang";
	document.f1.simpanmode.value = "update";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
}






function jenis_hutang(val)
{

if(val=="1")
{

document.f1.txtNoKPBaru1Penting.disabled = "disabled";
document.f1.txtNoKPBaru2Penting.disabled = "disabled";
document.f1.txtNoKPBaru3Penting.disabled = "disabled";

document.f1.socJenisKPLainPenting.disabled = "disabled";
document.f1.txtNoKPLainPenting.disabled = "disabled";
document.f1.socJenisKPLainPenting.value = "0";
document.f1.txtNoKPLainPenting.value = "";



document.f1.txtNoKPBaru1Penting.value = "";
document.f1.txtNoKPBaru2Penting.value = "";
document.f1.txtNoKPBaru3Penting.value = "";

document.f1.txtNoKPLamaPenting.disabled = "";
document.f1.txtNoKPLamaPenting.value = "";

document.f1.socJenisHutangPenting.value="1";
}

else if(val=="2")
{

document.f1.txtNoKPLamaPenting.disabled = "disabled";
document.f1.txtNoKPLamaPenting.value = "";

document.f1.txtNoKPBaru1Penting.disabled = "";
document.f1.txtNoKPBaru2Penting.disabled = "";
document.f1.txtNoKPBaru3Penting.disabled = "";

document.f1.socJenisKPLainPenting.disabled = "";
document.f1.txtNoKPLainPenting.disabled = "disabled";

document.f1.txtNoKPBaru1Penting.value = "";
document.f1.txtNoKPBaru2Penting.value = "";
document.f1.txtNoKPBaru3Penting.value = "";

document.f1.socJenisKPLainPenting.value = "0";
document.f1.txtNoKPLainPenting.value = "";


document.f1.socJenisHutangPenting.value="2";
}

else
{

document.f1.txtNoKPBaru1Penting.disabled = "disabled";
document.f1.txtNoKPBaru2Penting.disabled = "disabled";
document.f1.txtNoKPBaru3Penting.disabled = "disabled";
document.f1.txtNoKPBaru1Penting.value = "";
document.f1.txtNoKPBaru2Penting.value = "";
document.f1.txtNoKPBaru3Penting.value = "";

document.f1.txtNoKPLamaPenting.disabled = "disabled";
document.f1.txtNoKPLamaPenting.value = "";
document.f1.socJenisHutangPenting.value = "0";
document.f1.socJenisKPLainPenting.value = "0";

document.f1.socJenisKPLainPenting.disabled = "disabled";
document.f1.txtNoKPLainPenting.disabled = "disabled";

document.f1.txtNoKPLainPenting.value = "";
}


}



function jenis_hutangU(val)
{

if(val=="1")
{

document.f1.txtNoKPBaru1PentingU.disabled = "disabled";
document.f1.txtNoKPBaru2PentingU.disabled = "disabled";
document.f1.txtNoKPBaru3PentingU.disabled = "disabled";

document.f1.socJenisKPLainPentingU.disabled = "disabled";
document.f1.txtNoKPLainPentingU.disabled = "disabled";
document.f1.socJenisKPLainPentingU.value = "0";
document.f1.txtNoKPLainPentingU.value = "";



document.f1.txtNoKPBaru1PentingU.value = "";
document.f1.txtNoKPBaru2PentingU.value = "";
document.f1.txtNoKPBaru3PentingU.value = "";

document.f1.txtNoKPLamaPentingU.disabled = "";
document.f1.txtNoKPLamaPentingU.value = "";

document.f1.socJenisHutangPentingU.value="1";
}

else if(val=="2")
{

document.f1.txtNoKPLamaPentingU.disabled = "disabled";
document.f1.txtNoKPLamaPentingU.value = "";

document.f1.txtNoKPBaru1PentingU.disabled = "";
document.f1.txtNoKPBaru2PentingU.disabled = "";
document.f1.txtNoKPBaru3PentingU.disabled = "";

document.f1.socJenisKPLainPentingU.disabled = "";
document.f1.txtNoKPLainPentingU.disabled = "disabled";

document.f1.txtNoKPBaru1PentingU.value = "";
document.f1.txtNoKPBaru2PentingU.value = "";
document.f1.txtNoKPBaru3PentingU.value = "";

document.f1.socJenisKPLainPentingU.value = "0";
document.f1.txtNoKPLainPentingU.value = "";


document.f1.socJenisHutangPentingU.value="2";
}

else
{
document.f1.txtNoKPBaru1PentingU.disabled = "disabled";
document.f1.txtNoKPBaru2PentingU.disabled = "disabled";
document.f1.txtNoKPBaru3PentingU.disabled = "disabled";
document.f1.txtNoKPBaru1PentingU.value = "";
document.f1.txtNoKPBaru2PentingU.value = "";
document.f1.txtNoKPBaru3PentingU.value = "";

document.f1.txtNoKPLamaPentingU.disabled = "disabled";
document.f1.txtNoKPLamaPentingU.value = "";
document.f1.socJenisHutangPentingU.value = "0";
document.f1.socJenisKPLainPentingU.value = "1";

document.f1.socJenisKPLainPentingU.disabled = "disabled";
document.f1.txtNoKPLainPentingU.disabled = "disabled";

document.f1.txtNoKPLainPentingU.value = "";
}


}


function validateModal(elmnt,content,content2) {
	
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!="")
	{
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	}
	else
	{
	elmnt.value = "";
	}
	
}


function pilih_taraf()
{

if(document.f1.txtNamaOBPenting != null  )
{
if(document.f1.socJenisHutangPenting.value == '2')//individu
{

document.getElementById("kp1").style.display="";
document.getElementById("kp2").style.display="none";
document.getElementById("kp3").style.display="";
document.getElementById("kp4").style.display="";
document.getElementById("agama").style.display="";
document.getElementById("jantina").style.display="";
document.getElementById("umur").style.display="";
document.getElementById("warga").style.display="";
document.getElementById("dob").style.display="";

}

if(document.f1.socJenisHutangPenting.value == '1')//agensi
{
document.getElementById("kp1").style.display="none";
document.getElementById("kp2").style.display="";
document.getElementById("kp3").style.display="none";
document.getElementById("kp4").style.display="none";
document.getElementById("agama").style.display="none";
document.getElementById("jantina").style.display="none";
document.getElementById("umur").style.display="none";
document.getElementById("warga").style.display="none";
document.getElementById("dob").style.display="none";

}

}

if(document.f1.txtNamaOBPentingU != null )
{

if(document.f1.socJenisHutangPentingU.value == '2')//individu
{

document.getElementById("kp1").style.display="";
document.getElementById("kp2").style.display="none";
document.getElementById("kp3").style.display="";
document.getElementById("kp4").style.display="";
document.getElementById("agama").style.display="";
document.getElementById("jantina").style.display="";
document.getElementById("umur").style.display="";
document.getElementById("warga").style.display="";
document.getElementById("dob").style.display="";

}

if(document.f1.socJenisHutangPentingU.value == '1')//agensi
{

document.getElementById("kp1").style.display="none";
document.getElementById("kp2").style.display="";
document.getElementById("kp3").style.display="none";
document.getElementById("kp4").style.display="none";
document.getElementById("agama").style.display="none";
document.getElementById("jantina").style.display="none";
document.getElementById("umur").style.display="none";
document.getElementById("warga").style.display="none";
document.getElementById("dob").style.display="none";

}

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
</script>
<!-- END ADD BY PEJE -->

</body>
</html>
