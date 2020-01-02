
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
.style46 {
	font-size: 9px;
	color: #FF0000;
	font-style: italic;
}
.style49 {
	font-size: 9px;
	font-style: italic;
}
.style50 {font-size: 9px; font-style: italic; color: #0000FF; }
.style51 {font-size: 10px; color: #FF0000; }
.style52 {color: #000000}
.style53 {font-size: 10px; color: #000000; }
-->

</style>


</head>

<body onload="submitForm()" >



<!-- <form id="f1" name="f1" action="" method="POST" > -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>

<input type="hidden" name="v_tab" id="v_tab" value="" />


#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#set($id_Status = $list.id_Status)
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

 

#foreach($PermohonanSebelum in $listGetPermohonanSebelum)
#set ($id_Permohonan_terdahulu = $PermohonanSebelum.id_Permohonan)
#set ($no_subjaket = $PermohonanSebelum.no_subjaket)
#end
<input name="id_Permohonan_terdahulu" type="hidden"  value="$id_Permohonan_terdahulu"/>
<input name="no_subjaket" type="hidden"  value="$no_subjaket"/>

#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
<input name="tarikh_daftar" type="hidden"  value="$tarikhMohon"/>
    
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
   <input name="idPermohonan" type="hidden"  value="$id"/>
   <input name="idpermohonan" type="hidden"  value="$id"/>
    
  
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
 
  
<input name="id_Suburusanstatus" type="hidden"  value="$list.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$list.id_Suburusanstatusfail"/>
<input name="id_Permohonansimati" type="hidden"  value="$list.id_Permohonansimati"/>
<input name="idpeguam" type="hidden"  value=""/>

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
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style40">$list.noFail</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Negeri :</div></td>
           #foreach($listnegori in $listnegeri)      
                              
                              
                              #if($list.idnegeri==$listnegori.id_Negeri)
                              
                              #set($kodlistnegori="$listnegori.nama_Negeri")
                                       
                              
                              #end
                              
                              #if($list.idnegeri=="")
                              
                              #set($kodlistnegori="")
                              
                              #end
                              #end
          <td style="text-transform:uppercase;"><div align="left" class="style40">$kodlistnegori</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Daerah / Jajahan :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$list.namadaerah</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Unit :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$list.namaPejabat</span></div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Status Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style40">$list.keterangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$list.seksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$list.tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$list.namaPemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style40">$list.namaSimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
      </table>
    </div></td>
  </tr>
</table>


</fieldset>
-->
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
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" >NILAIAN HARTA</li>
    </ul>
    
    
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
     
        <div id="TabbedPanels2" class="TabbedPanelsContentVisible">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()">SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,1,0,0);PemohonView()">PEMOHON</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,2,0,0);WarisView()">WARIS</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,3,0,0);PentingView()">ORANG BERKEPENTINGAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,5,0,0);PemiutangView()">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
          </ul>
          
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent">
            
            
            </div>
            
            <div class="TabbedPanelsContent">
              <div id="TabbedPanels3" class="TabbedPanelsContentVisible">
                <ul class="TabbedPanelsTabGroup">
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,1,0,0);PemohonView()">PEMOHON</li>
         
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,1,1,0);PeguamView()">PEGUAM</li>
                  
                </ul>
                <div class="TabbedPanelsContentGroup">
                
                
                
                  <div class="TabbedPanelsContent" >      
                  </div>
                  
                  
                   <div class="TabbedPanelsContent" >
                   
                     
                   
                   <table width="100%" border="0">
                 
                   
                   
                    #if($add_new_peguam=="yes")
                    
                    #if($insertbaru == "yes")
                    
                    #set($txtNamaFirmaPeguam2 = "")
                    #set($txtNoRujukanFirma2 = "")
                    #set($txtAlamat1Peguam2 = "")
                    #set($txtAlamat2Peguam2 = "")
                    #set($txtAlamat3Peguam2 = "")
                    #set($txtPoskodPeguam2 = "")
                    #set($socNegeriPeguam2 = "")
                    #set($txtBandarPeguam2 = "")
                    #set($txtNomborTelefonPeguam2 = "")
                    #set($txtNomborFaksPeguam2 = "")
                    #set($txtEmelPeguam2 = "")
                    
                    #end
                    
                    
                    
                    
  <tr>
    <td><table width="100%">
     
       
      <tr>
        <td width="50%" valign="top"><table width="100%">
            <tr>
              <td width="1%" valign="top"><span class="style44">*</span></td>
              <td width="28%"><div align="right" class="style53">
                <div align="left">Nama Firma</div>
              </div>                </td>
              <td width="1%"><div align="center" class="style38">:</div></td>
              <td width="70%"><label>
                <input name="txtNamaFirmaPeguam2" type="text" id="txtNamaFirmaPeguam2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="34" $readmode  value="$txtNamaFirmaPeguam2"/>
              </label></td>
            </tr>
            <tr>
                <td width="1%" valign="top"><span class="style44">*</span></td>
              <td><div align="right" class="style53">
                <div align="left">No Rujukan </div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtNoRujukanFirma2" type="text" id="txtNoRujukanFirma2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="34" $readmode value="$txtNoRujukanFirma2" />
              </label></td>
            </tr>
            <tr>
               <td width="1%" valign="top"><span class="style44">*</span></td>
              <td class="style38"><div align="right" class="style53">
                 <div align="left">Alamat </div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtAlamat1Peguam2" type="text" id="txtAlamat1Peguam2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="34"  $readmode  value="$txtAlamat1Peguam2"/>
              </label></td>
            </tr>
            <tr>
                <td width="1%" valign="top">&nbsp;</td>
              <td class="style38"><div align="left"><span class="style3"><span class="style52"></span></span></div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtAlamat2Peguam2" type="text" id="txtAlamat2Peguam2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="34" $readmode  value="$txtAlamat2Peguam2"/>
              </label></td>
            </tr>
            <tr>
              <td class="style51">&nbsp;</td>
              <td class="style38"><div align="left"><span class="style3"><span class="style52"></span></span></div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><input name="txtAlamat3Peguam2" type="text" id="txtAlamat3Peguam2"   style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="34" $readmode  value="$txtAlamat3Peguam2"/></td>
            </tr>
            <tr>
                <td width="1%" valign="top"><span class="style44">*</span></td>
              <td><div align="right" class="style53">
                 <div align="left">Poskod</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtPoskodPeguam2" type="text" id="txtPoskodPeguam2" size="5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPeguam2')" maxlength="5" $readmode  value="$txtPoskodPeguam2"/>
              </label></td>
            </tr>
        </table></td>
        
        <td width="50%"><table width="100%" border="0">
        
            <tr>
               <td width="1%" valign="top"><span class="style44">*</span></td>
              <td width="28%" class="style38"><div align="right" class="style51 style52">
                <div align="left">Negeri</div>
              </div></td>
              <td width="1%">: </td>
              #if($socNegeriPeguam2=="" || $socNegeriPeguam2=="0" )
              #set($kod="")
              
              #else
              
              #foreach($listneg in $listnegeri)      
              
              
              #if($socNegeriPeguam2==$listneg.id_Negeri)
              
              #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
              
              
              #end
              #end
              
              #end
              <td width="70%">                #if($socNegeriPeguam2!="" && $socNegeriPeguam2!=0)
                <select name="socNegeriPeguam2" class="autoselect" id="socNegeriPeguam2"  onchange="getBandarTetap2('txtBandarPeguam2')" >
                                              <option value="$socNegeriPeguam2" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                  
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($socNegeriPeguam2!=$listneg.id_Negeri)
                       
                  <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                  
                                     
                                  #end    
	                              #end
                                        
                </select>
                #else
                <select name="socNegeriPeguam2" class="autoselect" onchange="getBandarTetap2('txtBandarPeguam2')">
                  <option value="" >SILA PILIH NEGERI</option>
                  
                                  #foreach($listneg in $listnegeri)
                   
                  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                  
                                    
	                               #end
                                        
                </select>
                #end                </td>
            </tr>
            <tr>
                <td width="1%" valign="top">&nbsp;</td>
              <td class="style38"><div align="right" class="style51 style52">
                <div align="left">Bandar</div>
              </div></td>
              <td class="style36"><div align="right"><span class="style38">:</span></div></td>
              <td><label> #if($txtBandarPeguam2 == "" || $txtBandarPeguam2=="0")
                #set($kodb="")
                
                #else
                
                #foreach($listneg in $listBandarTetapbyNegeri)      
                
                
                #if($txtBandarPeguam2==$listneg.id)                                      
                #set($kodb="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
                #end
                
                
                
              
                
                #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                #if($txtBandarPeguam2==$listdaerah.id)                                
                #set($listDaerahbyNegeriK=$listdaerah.kod)
                #set($listDaerahbyNegeriN=$listdaerah.nama)
                #end 
                #end
                
                
                #if($disabled=="disabled")
                #set($readmodedaerah="disabled")
                #end
                #if($txtBandarPeguam2!="" && $txtBandarPeguam2!="0" )
                <select name="txtBandarPeguam2" id="txtBandarPeguam2" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap2()" >
                                      <option value="$txtBandarPeguam2">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                  
                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($txtBandarPeguam2!=$listdaerah.id)
              
                                  
                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                
                </select>
                #else
                <select name="txtBandarPeguam2" id="txtBandarPeguam2" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap2()" >
                  <option value="">Sila Pilih Bandar</option>
                  
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                  
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
                </select>
                #end 
                </label></td>
            </tr>
            <tr>
               <td width="1%" valign="top"><span class="style44">*</span></td>
              <td><div align="right" class="style51 style52">
                <div align="left">No Telefon</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtNomborTelefonPeguam2" type="text" id="txtNomborTelefonPeguam2" onkeyup="javascript:validateIC(event,this,this.value,'txtNomborTelefonPeguam2')" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  size="14" maxlength="14" $readmode value="$txtNomborTelefonPeguam2"/>
              </label></td>
            </tr>
            #if($readmode != "disabled")
            <tr>
              <td><span class="style44"></span></td>
              <td><div align="left"><span class="style52"></span></div></td>
              <td>&nbsp;</td>
              <td valign="top"><span class="style50">cth: 031234567</span></td>
            </tr>
            #end
            <tr>
              <td><span class="style44"></span></td>
              <td><div align="right" class="style38 style52">
                <div align="left">No Faks</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtNomborFaksPeguam2" type="text" onkeyup="javascript:validateIC(event,this,this.value,'txtNomborFaksPeguam2')" id="txtNomborFaksPeguam2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  size="14" maxlength="12" $readmode value="$txtNomborFaksPeguam2" />
              </label></td>
            </tr>
            <tr>
              <td><span class="style44"></span></td>
              <td><div align="right" class="style38 style52">
                  <div align="center" class="style38">
                    <div align="left">Emel</div>
                  </div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtEmelPeguam2" type="text" id="txtEmelPeguam2" size="34" $readmode value="$txtEmelPeguam2" />
              </label></td>
            </tr>
            #if($readmode != "disabled")
            <tr>
              <td><span class="style44"></span></td>
              <td><div align="left"><span class="style52"></span></div></td>
              <td>&nbsp;</td>
              <td valign="top"><span class="style50">cth: abc@email.com </span></td>
            </tr>
            #end
            
        </table></td>
      </tr>
     
                   
  <tr>
    <td><span class="style46">Perhatian</span><span class="style49"> : Sila  pastikan label bertanda <span class="style44">*</span> diisi.</span></td>
  </tr>

      
    </table></td>
  </tr>
  #end
  #if($add_new_peguam=="update")
    #foreach($listpeguam in $listpeguamX)
  
  <tr>
    <td>
    
    <table width="100%">

    <tr>
      <td width="50%" valign="top"><table width="100%">
      
              #if($readmode == "disabled")
              #set($readmodeR = "readonly")
              #else
              #set($readmodeR = "")
              #end
      
          <tr>
            <td width="1%" valign="top" >#if($readmode != "disabled")<span class="style44">*</span> #end</td>
            <td width="28%"><div align="right" class="style53">
              <div align="right" class="style38">
                <div align="left">#if($readmode != "disabled")Nama Firma #else Nama Firma</div>
              </div>
              <div align="left">#end</div>
            </div></td>
            <td width="1%"><div align="center" class="style38">:</div></td>
            <td width="70%">
             <input name="idPeguam" type="hidden" id="txtNamaFirmaPeguam" value="$listpeguam.idPeguam" />
            <label>
           
             
              
              <input name="txtNamaFirmaPeguam" type="text" id="txtNamaFirmaPeguam" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpeguam.namaFirma" size="34" $readmodeR class="$readmode" />
            </label></td>
          </tr>
          <tr>
              <td width="1%" valign="top" >#if($readmode != "disabled")<span class="style44">*</span> #end</td>
            <td>
            
            
            <div align="right" class="style53">
              <div align="left"><span class="style38">#if($readmode != "disabled")</span> No Rujukan #else No Rujukan #end</div>
            </div></td>
            <td><div align="center" class="style38">:</div></td>
            <td><label>
              <input name="txtNoRujukanFirma" type="text" id="txtNoRujukanFirma" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpeguam.noRujukanFirma" size="34" $readmodeR class="$readmode" />
            </label></td>
          </tr>
          <tr>
              <td width="1%" valign="top" >#if($readmode != "disabled")<span class="style44">*</span> #end</td>
            <td class="style38"><div align="right" class="style53">
              <div align="left">#if($readmode != "disabled") Alamat #else Alamat #end</div>
            </div></td>
            <td><div align="center" class="style38">:</div></td>
            <td><label>
              <input name="txtAlamat1Peguam" type="text" id="txtAlamat1Peguam" value="$listpeguam.alamat1" size="34"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  $readmodeR class="$readmode" />
            </label></td>
          </tr>
          <tr>
            <td class="style38">&nbsp;</td>
            <td class="style38"><div align="left"><span class="style3"><span class="style52"></span></span></div></td>
            <td><div align="center" class="style38">:</div></td>
            <td><label>
              <input name="txtAlamat2Peguam" type="text" id="txtAlamat2Peguam"  value="$listpeguam.alamat2" style="text-transform:uppercase;" onblur="uppercase()this.value=this.value.toUpperCase()"  size="34" $readmodeR class="$readmode" />
            </label></td>
          </tr>
          <tr>
            <td class="style38">&nbsp;</td>
            <td class="style38"><div align="left"><span class="style3"><span class="style52"></span></span></div></td>
            <td><div align="center" class="style38">:</div></td>
            <td><input name="txtAlamat3Peguam" type="text" id="txtAlamat3Peguam"  style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$listpeguam.alamat3" size="34" $readmodeR class="$readmode" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><div align="right" class="style53">
              <div align="left">#if($readmode != "disabled") Poskod #else Poskod #end</div>
            </div></td>
            <td><div align="center" class="style38">:</div></td>
            <td><label>
              <input name="txtPoskodPeguam" type="text" id="txtPoskodPeguam" value="$listpeguam.poskod" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  size="5" maxlength="5" $readmodeR class="$readmode"  onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPeguam')"  />
            </label></td>
          </tr>
      </table></td>
      <td width="50%" valign="top"><table width="100%" border="0">
          <tr>
              <td width="1%" valign="top" >#if($readmode != "disabled")<span class="style44">*</span> #end</td>
            <td width="28%" class="style38"><div align="right" class="style51 style52">
              <div align="left">#if($readmode != "disabled")  Negeri #else <span class="style52">Negeri</span> #end</div>
            </div></td>
            <td width="1%"><div align="center" class="style38">:</div></td>
            <td width="70%">
            #if($readmode == "disabled")
             #if($listpeguam.idnegeri==""||$listpeguam.idnegeri==0)
                                      
            #set($negerikodpeguam="")
                                      
            #else
            #foreach($listnegpeg in $listnegeri)           
            #if($listpeguam.idnegeri==$listnegpeg.id_Negeri)
            #set($negerikodpeguam="$listnegpeg.kod_Negeri - $listnegpeg.nama_Negeri")
            #end 
            
            #end 
            #end      
            <input name="socNegeriPeguam" id="socNegeriPeguam" type="text" value="$negerikodpeguam" style="width: 265px; text-transform:uppercase;" $readmodeR class="$readmode" />
            #else
        
            #if($listpeguam.idnegeri==""||$listpeguam.idnegeri==0)
                                      
            #set($negerikodpeguam="")
                                      
            #else
            #foreach($listnegpeg in $listnegeri)           
            #if($listpeguam.idnegeri==$listnegpeg.id_Negeri)
            #set($negerikodpeguam="$listnegpeg.kod_Negeri - $listnegpeg.nama_Negeri")
            #end 
            
            #end 
            #end      
                                 
                                  #if(($listpeguam.idnegeri=="")||($listpeguam.idnegeri=="0"))
                                  <select name="socNegeriPeguam" class="autoselect" style="text-transform:uppercase;" onblur="uppercase()" onchange="getBandarTetap('txtBandarPeguam')" $readmode >
                                  <option value="" style="text-transform:uppercase;" onblur="uppercase()" >Sila Pilih Negeri</option>
                                  #foreach($listneg in $listnegeri)
                                  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()" >$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                  #end
                                  </select>                                  
                                  #else
                                  <select name="socNegeriPeguam" class="autoselect" id="socNegeriPemohon" style="text-transform:uppercase;" onblur="uppercase()" onchange="getBandarTetap('txtBandarPeguam')" $readmode >
                                  <option value="$listpeguam.idnegeri" style="text-transform:uppercase;" onblur="uppercase()" >$negerikodpeguam</option>
                                  #foreach($listneg in $listnegeri)
                                  #if($listpeguam.idnegeri!=$listneg.id_Negeri)
                                  <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                  #end    
	                              #end
                                  
                                  </select>

                                  #end
                                  
                                    #end        </td>
             </tr>
             
              #set($bandartetap = $listpeguam.bandar)
          <tr>
             <td width="1%" valign="top" >#if($readmode != "disabled")#end</td>
            <td class="style38"><div align="right" class="style51 style52">
              <div align="left">#if($readmode != "disabled")  Bandar #else <span class="style52">Bandar</span> #end</div>
            </div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td><label>             
              #if($readmode == "disabled")
              #if($bandartetap == ""||$bandartetap == 0)                             
              #set($bandarkodpeguam = "")                                      
              #else
              #foreach($listdaerah in $listBandarTetapbyNegeri)                                
              #if($bandartetap == $listdaerah.id) 
              #set($bandarkodpeguam = "$listdaerah.kod - $listdaerah.nama")                               
              
              #end 
              #end
            
            
            #end      
            <input name="txtBandarPeguam" id="txtBandarPeguam" type="text" value="$bandarkodpeguam" style="width: 265px; text-transform:uppercase;" $readmodeR class="$readmode" />
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
              <select name="txtBandarPeguam" id="txtBandarPeguam" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                                      <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                    
                <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                             
                                   
                                  #end    
	                               #end
                                 
              </select>
              #else
              <select name="txtBandarPeguam" id="txtBandarPeguam" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                <option value="">Sila Pilih Bandar</option>
                
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  
                <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                
              </select>
              #end 
              #end
              </label></td>
          </tr>
          <tr>
             <td width="1%" valign="top" >#if($readmode != "disabled")<span class="style44">*</span> #end</td>
            <td><div align="right" class="style51 style52">
              <div align="left">#if($readmode != "disabled")  No Telefon #else<span class="style52"> No Telefon</span> #end</div>
            </div></td>
            <td><div align="center" class="style38">:</div></td>
            <td>
              <input name="txtNomborTelefonPeguam"  type="text" id="txtNomborTelefonPeguam" style="text-transform:uppercase;" onblur="uppercase()" value="$listpeguam.noTel" size="14" maxlength="14"  onkeyup="javascript:validateIC(event,this,this.value,'txtNomborTelefonPeguam')" $readmodeR class="$readmode" />           </td>
          </tr>
          #if($readmode != "disabled")
          <tr>
            <td>&nbsp;</td>
            <td><div align="left"><span class="style52"></span></div></td>
            <td>&nbsp;</td>
            <td valign="top"><span class="style50">cth: 031234567</span></td>
          </tr>
          #end
          <tr>
            <td>&nbsp;</td>
            <td><div align="right" class="style38 style52">
              <div align="left">No Faks</div>
            </div></td>
            <td><div align="center" class="style38">:</div></td>
            <td><label>
              <input name="txtNomborFaksPeguam" type="text" id="txtNomborFaksPeguam"  style="text-transform:uppercase;" onblur="uppercase()"  value="$listpeguam.noFax" size="14" maxlength="12" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNomborFaksPeguam')" />
            </label></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><div align="right" class="style38 style52"><div align="center" class="style38">
              <div align="left">Email</div>
            </div>
            </div></td>
            <td><div align="center" class="style38">:</div></td>
            <td><label>
              <input name="txtEmelPeguam" type="text" id="txtEmelPeguam" value="$listpeguam.emel"  size="34" $readmodeR class="$readmode" />
            </label></td>
          </tr>
          #if($readmode != "disabled")
          <tr>
            <td>&nbsp;</td>
            <td><div align="left"><span class="style52"></span></div></td>
            <td>&nbsp;</td>
            <td valign="top"><span class="style50">cth: abc@email.com </span></td>
          </tr>
          #end
      </table></td>
    </tr>
</table>    </td>
  </tr>
       #if($readmode != "disabled") 
                   
  <tr>
    <td><span class="style46">Perhatian</span><span class="style49"> : Sila  pastikan label bertanda <span class="style44">*</span> diisi.</span></td>
  </tr>

#end
  #end
  #end
  <tr>
    <td align="center">
     <table width="100%" border="0" align="center">
        <tr>
          <td align="center"> #if($add_new_peguam=="yes")
            <input type="button" name="cmdKemaskin" id="cmdKemaskin" value="Simpan" onclick="setSelected(0,1,1,0);tambah_peguam()"/>
              <input type="button" name="cmdKemaskin" id="cmdKemaskin" value="Batal"  onclick="setSelected(0,1,1,0);batal_peguam_reset('txtNamaFirmaPeguam2')"/>
              <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="setSelected(0,1,1,0);PeguamView()" />
              #end
            #if($add_new_peguam=="update")
            #if($show_kemaskini_button=="yes")
            
         #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")

            <input type="button" name="cmdKemaskin" id="cmdKemaskin" value="Kemaskini"  onclick="setSelected(0,1,1,0);kemaskini_peguam()"/>
            <label>
            <input type="button" name="button" id="button" value="Hapus" onclick="setSelected(0,1,1,0);hapus_peguam()" />
            </label>
            #end
            <input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali"  onclick="setSelected(0,1,1,0);PeguamView()" />
            #end
            #if($show_simpan_button=="yes")
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onclick="setSelected(0,1,1,0);simpan_peguam()"/>
            #end
            
            #if($show_kemaskini_button!="yes")
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="setSelected(0,1,1,0);BatalPeguam()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="setSelected(0,1,1,0);PeguamView()" />            
            #end
            #end          </td>
        </tr>
        <tr>
          <td><input type="hidden" name="idOb" value="" />
              <fieldset>
              <legend>SENARAI PEGUAM</legend>
                <table width="100%" >
                
               
                <tr>
                  <td width="100%">
                   #if($button_tambah=="yes")
                   
                  #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")

                  <label>                  
                    <input type="button" name="cmdPapar" id="cmdPapar" value="Tambah" onclick="setSelected(0,1,1,0);tambah_peguam_baru()"/>
                    </label>
                    #end
                    
                    #end
                    #if($button_Kembali=="yes")
                    <!--
                      <label>
                      <input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembali_simati()" />
                      </label>
                      -->
                      #end                      </td>
                </tr>
               
                <tr>
                  <td> #if($listPenting.size()!=0 )
                    <table width="100%">
                        <tr  class="table_header">
                          <td width="5%"><div align="center">NO</div></td>
                          <td width="60%"><div align="left">NAMA FIRMA</div></td>
                          <td width="35%"><div align="center">NO RUJUKAN</div></td>
                          </tr>
                      #set($peno=0)
                      #foreach($listpenting in $listPenting)
                      
                      
                      
                      #set($peno=$peno+1)
                      
                      #if($peno%2!=0)
                      <tr bgcolor="white">
                        <td class="row1"><div align="center" class="style41">$peno</div></td>
                        <td class="row1" style="text-transform:uppercase;"><a href="javascript:edit_item('$listpenting.idPeguam')">
                          <div align="left" class="style42 style40" > $listpenting.namaFirma</div>
                          <input type="hidden" name="idob" value="$listpenting.idOb" />
                        </a> </td>
                        <td class="row1" style="text-transform:uppercase;"><div align="center" >$listpenting.noRujukanFirma</div></td>
                      </tr>
                      #else
                      <tr >
                        <td class="row2"><div align="center" class="style41">$peno</div></td>
                        <td class="row2" style="text-transform:uppercase;"><a href="javascript:edit_item('$listpenting.idPeguam')">
                          <div align="left" class="style42 style40"> $listpenting.namaFirma</div>
                          <input type="hidden" name="idob" value="$listpenting.idOb" />
                        </a> </td>
                        <td class="row2" style="text-transform:uppercase;"><div align="center">$listpenting.noRujukanFirma</div></td>
                      </tr>
                      #end
                      #end
                    </table>
                    #else
                    <table width="100%">
                        <tr  class="table_header">
                          <td width="5%"><div align="center">NO</div></td>
                          <td width="60%"><div align="left">NAMA FIRMA</div></td>
                          <td width="35%"><div align="center">NO RUJUKAN</div></td>
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

              </fieldset></td>
        </tr>
      </table></td>
  </tr>
   <tr>
                <td>
                <p align="right">CL - 01 - 31</p>
                </td>
                </tr>
</table>
 
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
  </div>    </td>
  </tr>
</table>

#parse("app/ppk/paging_sek8.jsp")

</form>

<script>
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
document.f1.reset();
document.f1.txtNoKPBaru1Waris.focus();
}
}
<!-- PEGUAM -->
function kemaskini_peguam(){
	document.f1.mode.value = "kemaskini_peguam";
	document.f1.command.value = "Peguam";
	document.f1.action = "";
	document.f1.submit();
}

function BatalPeguam() {
	input_box = confirm("Adakah anda pasti?");
if (input_box == true) {
	document.f1.mode.value = "kemaskini_peguam";
	document.f1.command.value = "Peguam";
	document.f1.action = "";
	document.f1.submit();
	}
	
}
function hapus_peguam() {
	input_box = confirm("Adakah anda pasti?");
if (input_box == true) {
	document.f1.mode.value = "hapus_peguam";
	document.f1.command.value = "Peguam";
	document.f1.action = "";
	document.f1.submit();
	}
	
}

function simpan_peguam() {
    var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.f1.txtEmelPeguam;
		
    if(document.f1.txtNamaFirmaPeguam.value == ""){
		alert('Sila masukkan nama firma peguam terlebih dahulu.');
  		document.f1.txtNamaFirmaPeguam.focus(); 
		return; 
	}
	else if(document.f1.txtNoRujukanFirma.value == ""){
		alert('Sila masukkan no rujukan firma peguam terlebih dahulu.');
  		document.f1.txtNoRujukanFirma.focus(); 
		return; 
	}
	
	else if(document.f1.txtAlamat1Peguam.value == ""){
		alert('Sila masukkan alamat firma peguam terlebih dahulu.');
  		document.f1.txtAlamat1Peguam.focus(); 
		return; 
	}
	else if(document.f1.txtPoskodPeguam.value == ""){
		alert('Sila masukkan poskod firma peguam terlebih dahulu.');
  		document.f1.txtPoskodPeguam.focus(); 
		return; 
	}
	
	else if(document.f1.socNegeriPeguam.value == "" || document.f1.socNegeriPeguam.value == "0"){
		alert('Sila masukkan negeri firma peguam terlebih dahulu.');
  		document.f1.socNegeriPeguam.focus(); 
		return; 
	}
	else if(document.f1.txtNomborTelefonPeguam.value == ""){
		alert('Sila masukkan telefon firma peguam terlebih dahulu.');
  		document.f1.txtNomborTelefonPeguam.focus(); 
		return; 
	}
	
	else if (document.f1.txtPoskodPeguam.value!="" && document.f1.txtPoskodPeguam.value.length < 5) {
		alert("Sila masukkan nombor poskod peguam sepenuhnya");
		document.f1.txtPoskodPeguam.focus();
		return; 
	}
	else if(!em.value.match(emailExp) && em.value!=""){
		
		alert("Alamat email tidak sah!");		
		em.focus();
		return false;
	}
	else{
	
	input_box = confirm("Adakah anda pasti?");
    if (input_box == true) {
	document.f1.mode.value = "simpan_peguam";
	document.f1.command.value = "Peguam";
	document.f1.action = "";
	document.f1.submit();
	}
	else
	{return;}
	
	}
	
}
function tambah_peguam_baru()
{

    document.f1.mode.value = "peguam_baru";
	document.f1.command.value = "Peguam";
	document.f1.action = "";
	document.f1.submit();
}

function tambah_peguam() {
var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.f1.txtEmelPeguam2;	
	
if(document.f1.txtNamaFirmaPeguam2.value == ""){
		alert('Sila masukkan nama firma peguam terlebih dahulu.');
  		document.f1.txtNamaFirmaPeguam2.focus(); 
		return; 
	}
	else if(document.f1.txtNoRujukanFirma2.value == ""){
		alert('Sila masukkan no rujukan firma peguam terlebih dahulu.');
  		document.f1.txtNoRujukanFirma2.focus(); 
		return; 
	}
	else if(document.f1.txtAlamat1Peguam2.value == ""){
		alert('Sila masukkan alamat firma peguam terlebih dahulu.');
  		document.f1.txtAlamat1Peguam2.focus(); 
		return; 
	}
	else if(document.f1.txtPoskodPeguam2.value == ""){
		alert('Sila masukkan poskod firma peguam terlebih dahulu.');
  		document.f1.txtPoskodPeguam2.focus(); 
		return; 
	}
	
	else if(document.f1.socNegeriPeguam2.value == "" || document.f1.socNegeriPeguam2.value == "0"){
		alert('Sila masukkan negeri firma peguam terlebih dahulu.');
  		document.f1.socNegeriPeguam2.focus(); 
		return; 
	}
	else if(document.f1.txtNomborTelefonPeguam2.value == ""){
		alert('Sila masukkan telefon firma peguam terlebih dahulu.');
  		document.f1.txtNomborTelefonPeguam2.focus(); 
		return; 
	}
	
	
	
	else if (document.f1.txtPoskodPeguam2.value!="" && document.f1.txtPoskodPeguam2.value.length < 5) {
		alert("Sila masukkan nombor poskod peguam sepenuhnya");
		document.f1.txtPoskodPeguam2.focus();
		return; 
	}
	else if(!em.value.match(emailExp) && em.value!=""){
		
		alert("Alamat email tidak sah!");		
		em.focus();
		return false;
	}
	else
	{
	
	input_box = confirm("Adakah anda pasti?");
if (input_box == true) {
	document.f1.mode.value = "tambah_peguam";
	document.f1.command.value = "Peguam";
	document.f1.method = "POST";
	document.f1.action = "";
	document.f1.submit();
	
	}
	}
	
}

function getBandarTetap2(v_t)
{
	document.f1.mode.value = "getBandar2";
	document.f1.command.value = "Peguam";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
}
function getBandarTetap(v_t)
{
	document.f1.mode.value = "getBandar";
	document.f1.command.value = "Peguam";
	document.f1.v_tab.value = v_t;
	document.f1.action = "";
	document.f1.submit();
}
/*
function submitForm() {    
   // document.val.focus();
	
	//new Effect.ScrollTo('$val_tab');
	goTo('$val_tab');
	Effect.ScrollTo('$val_tab').focus(); return false;
	

} */

function submitForm() {    
//alert('$val_tab')
if('$val_tab' != "" && '$val_tab' != null)
{
	window.location.hash='$val_tab';
   goTo('$val_tab');
   }
	
} 


function edit_item(idpeguam) 
{

document.f1.action = "";
	document.f1.mode.value = "GetPeguam";
	document.f1.command.value = "Peguam";
	document.f1.idpeguam.value = idpeguam;
	document.f1.submit();
}
function batal_peguam_reset(v_t)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {


    document.f1.action = "";
	document.f1.mode.value = "peguam_baru";
	document.f1.command.value = "Peguam";
	document.f1.v_tab.value = v_t;
	document.f1.submit();



}
}

function CheckBandarTetap2()
{
if(document.f1.socNegeriPeguam2.value == "" || document.f1.socNegeriPeguam2.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPeguam2.focus();
  return;
	  	
}

}
function CheckBandarTetap()
{
if(document.f1.socNegeriPeguam.value == "" || document.f1.socNegeriPeguam.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPeguam.focus();
  return;
	  	
}


}

<!-- PEMOHON -->

</script>

<script type="text/javascript">

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


</script>



</body>
</html>
