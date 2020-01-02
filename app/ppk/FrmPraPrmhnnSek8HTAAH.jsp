
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
.style41 {color: #FF0000}
.style42 {color: #0000FF}
.style43 {font-size: 9px}
.style44 {color: #000000}
.style45 {font-size: 10px; color: #FF0000; }
-->
</style>
</head>

<body onload="submitForm();no_lot1();checklot();daerah_harta();check_harta()" >
<form id="form1" name="f1" method="post" action="">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input type="hidden" name="v_tab" id="v_tab" value="" />
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>

 #if($add_new_harta == "yes")
  
  
                #set($idhta="")	
    		    #set($noHakmilik="")		    
    		    #set($idSimati="")
    		    #set($nopt="LOT")
    		    #set($nilai_Hta_memohon="")
    		    #set($nilai_Hta_mati="")
    		    #set($kategori="")
    		    #set($jenishakmilik="")
    		    #set($pemilikan="1")		    
    		    #set($negeri="")
    		    #set($daerah="")	
    	     	#set($mukim="")	
    	        #set($luashmp="")
    		    #set($luasasal="") 
    			#set($nocagaran="")			    
    		    #set($nopajakan="")			   	
    		    #set($jenistanah="")	    
    		    #set($basimati="")
    		    #set($bbsimati="")
    		    #set($jenishta="")
    		    #set($tanggungan="")
    		    #set($jenisluas="")		    
    		    #set($catatan="")	  	
    	        #set($noperserahan="")    		   	 
    		    #set($nopt="LOT")
    		    #set($nilai_Hta_memohon="")
    		    #set($nilai_Hta_mati="")
    		    #set($kategori="")
    		    #set($jenishakmilik="")
    		    #set($pemilikan="1")	 
    	        #set($luashmp="")
    		    #set($luasasal="") 				    
    		    #set($nopajakan="")			   	
    		    #set($jenistanah="")	    
    		    #set($basimati="")
    		    #set($bbsimati="")
    		    #set($tanggungan="")
    		    #set($jenisluas="")	
    		    #set($catatan="")		  	
    	        #set($noperserahan="")
                
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

 



#foreach($listFail in $ViewFail)
<input name="id_Suburusanstatus" type="hidden"  value="$listFail.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$listFail.id_Suburusanstatusfail"/>
#end

#foreach($list in $View)
   #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    <input name="jpphlepas" type="hidden"  value="$list.jpphlepas"/>
    
       
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
     <input name="idpermohonan" type="hidden"  value="$id"/>
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
       
      
<input name="id_Permohonansimati" type="hidden"  value="$list.id_Permohonansimati"/>

<input name="id_Fail" type="hidden" value="$list.idFail" />
 
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


 #set($md=$listtarikhMohon)
          <input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
                <input type="hidden" name="txdTarikhMohon" id="txdTarikhMohon" value="$listtarikhMohon" />
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
      
          <div class="TabbedPanelsContentGroup">
            <div></div>
            
            <div>
                  <div id="TabbedPanels3">              
                   <div class="TabbedPanelsContentGroup">
                     <div ></div>
                     <div ></div>
                   </div>
                </div>
                
            </div>
            
            <div> 
            </div>
            
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContentVisible">
        <div id="TabbedPanels4" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(1,0,0,0);HtaamView()">HARTA TAK ALIH (ADA HAKMILIK)</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(1,0,0,1);HtaamViewX()">HARTA TAK ALIH (TIADA HAKMILIK)</li>
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent">
            
            
              <table width="100%" border="0" align="center">
          <tr>
                                    <td width="50%">
                                   
                                      #if($show_htaa_add_table=="yes")
                                      <fieldset>
                                      <legend>HARTA TAK ALIH (ADA HAKMILIK) </legend>
                                      
                                      <table width="100%" border="0">
                                        <tr>
                                          <td width="50%" valign="top"><table width="100%" border="0">
                                            
                                            
                                            <tr>
                                              <td valign="top" class="style45">*</td>
                                              <td width="29%" class="style38"><div align="right" class="style44">
                                                <div align="left">Negeri</div>
                                              </div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td width="70%"> #foreach($listnegpomo in $listnegeri)
                                                
                                                #if($negeri==$listnegpomo.id_Negeri)
                                                
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($negeri!="")
                                                <select name="socNegeriHtaam" class="autoselect" $readmodenegeri onchange="setSelected(1,0,0,0);negerichange('socDaerahHtaam')" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                                  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                                </select>
                                                #else
  <select name="socNegeriHtaam" class="autoselect" $readmodenegeri onchange="setSelected(1,0,0,0);negerichange('socDaerahHtaam')" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Negeri</option>
    
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
    <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
    
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td valign="top" class="style45" >*</td>
                                              <td class="style38" ><div align="right" class="style44">
                                                <div align="left">Daerah</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listdaerah in $listDaerahbyNegeri)
                                                
                                                #if($daerah==$listdaerah.id)
                                                
                                                #set($listDaerahbyNegeriK=$listdaerah.kod)
                                                #set($listDaerahbyNegeriN=$listdaerah.nama)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($daerah!="")
                                                <select name="socDaerahHtaam" id="socDaerahHtaam" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchange('socMukimHtaam');daerah_harta();check_harta()" style="text-transform:uppercase;" onblur="uppercase()"  onclick="CheckDaerah()" >
                                              <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                                </select>
                                                #else
  <select name="socDaerahHtaam" id="socDaerahHtaam" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchange('socMukimHtaam');daerah_harta();check_harta()" style="text-transform:uppercase;" onblur="uppercase()"  onclick="CheckDaerah()" >
    <option value="">Sila Pilih Daerah</option>
    
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
    <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  </select>
                                                #end 
                                                
                                                <span id="check_daerah_harta" style="color:red" ></span>                                                </td>
                                            </tr>
                                            <tr>
                                              <td valign="top" class="style45" >*</td>
                                              <td class="style38" ><div align="right" class="style44">
                                                <div align="left">Mukim</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listmukim in $listMukimbyDaerah)
                                                
                                                #if($mukim==$listmukim.id)
                                                
                                                #set($listMukimbyDaerahK=$listmukim.kod)
                                                #set($listMukimbyDaerahN=$listmukim.nama)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($mukim!="")
                                                <select name="socMukimHtaam" class="autoselect" $readmodemukim id="socMukimHtaam" style="text-transform:uppercase;" onblur="uppercase()" onfocus="CheckMukim()" >
                                              <option value="$mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                                  
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($mukim!=$listmukim.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                                  <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>
                                                  
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socMukimHtaam" id="socMukimHtaam"  class="autoselect" $readmodemukim style="text-transform:uppercase;" onblur="uppercase()" onfocus="CheckMukim()" >
    <option value="">Sila Pilih Mukim</option>
    
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
    <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
    
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td valign="top" class="style45" >*</td>
                                              <td class="style38" ><div align="right" class="style44">
                                                <div align="left">Jenis Hakmilik</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                                                
                                                #if($jenishakmilik==$listjenishakmilik.id)
                                                
                                                #set($listjenishakmilikK=$listjenishakmilik.kod)
                                                #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($jenishakmilik!="")
                                                <select name="socJenisHakmilikHtaam" class="autoselect" $readmode id="socJenisHakmilikHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$jenishakmilik">$listjenishakmilikK - $listjenishakmilikN</option>
                                                  
                                            
                                            
                                            
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                  
                                  #if($jenishakmilik!=$listjenishakmilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                                  <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
                                                  
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socJenisHakmilikHtaam" class="autoselect" $readmode id="socJenisHakmilikHtaam" style="text-transform:uppercase;" onblur="uppercase()">

 
    <option value="">Sila Pilih Jenis HakMilik</option>
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                #set($listjenishakmilikkod = $listjenishakmilik.kod)
	                          
                              
    <option value="$listjenishakmilik.id">$listjenishakmilikkod - $listjenishakmilik.keterangan</option>
	                               #end
                                   
                                   
  </select>
                                                #end                                                </td>
                                            </tr>
                                            
                                            
                                            <tr>
                                              <td valign="top" class="style45">*</td>
                                              <td class="style38"><div align="right" class="style44">
                                                <div align="left">No Hakmilik</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNoHakmilikHtaam" type="text" id="txtNoHakmilikHtaam" value="$noHakmilik"  size="50" maxlength="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td valign="top" class="style45">*</td>
                                              <td class="style38"><div align="right" class="style44">
                                                <div align="left">No PT / No Lot</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNoPTHtaam" type="text" id="txtNoPTHtaam" value="$nopt" size="15"  maxlength="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  onkeyup="no_lot1();checklot()" />
                                              </label>
                                              <span id="checklot" style="color:red" ></span>                                              </td>
                                            </tr>
                                            <tr>
                                              <td valign="top" class="style45">*</td>
                                              <td class="style38"><div align="right" class="style44">
                                                <div align="left">Bahagian Simati</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtBahagianSimati1" type="text" id="txtBahagianSimati1" onKeyUp="javascript:validateIC(event,this,this.value,'txtBahagianSimati1')" value="$basimati" style="text-align:right;text-transform:uppercase;" size="14" maxlength="14" onblur="bahagiansimati()"/>
                                                /
                                                <input name="txtBahagianSimati2" type="text" id="txtBahagianSimati2" onKeyUp="javascript:validateIC(event,this,this.value,'txtBahagianSimati2')" value="$bbsimati" style="text-align:left;text-transform:uppercase;" size="14" maxlength="14" onblur="bahagiansimati()"/>                                              </td>
                                            </tr>
                                            <tr>
                                              <td class="style45">&nbsp;</td>
                                              <td class="style38"><div align="right" class="style44">
                                                <div align="left">No Pajakan</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNoPajakan" type="text" id="txtNoPajakan" value="$nopajakan" size="15" maxlength="12" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td valign="top" class="style45">&nbsp;</td>
                                              <td class="style38" valign="top"><div align="right" class="style44">
                                                <div align="left">No Perserahan</div>
                                              </div></td>
                                              <td valign="top"><div align="right">:</div></td>
                                              <td valign="top"><label>
                                                <input name="txtNoPersHtaam" type="text" id="txtNoPersHtaam" value="$noperserahan" size="15" maxlength="12" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
                                              </label></td>
                                            </tr>
                                          </table></td>
                                          <td width="50%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td width="29%" class="style38"><div align="left">Kategori Tanah</div></td>
                                              <td><div align="right">:</div></td>
                                              <td width="70%">
                                              
                                               #if($kategori == "2")
                            #set($meterhektar = "Hektar")
                            #elseif($kategori == "1" || $kategori == "3" || $kategori == "4" || $kategori == "5" || $kategori == "6")
                             #set($meterhektar = "Meter Persegi")
                             
                             #else
                             #set($meterhektar = "")
                             
                            #end
                                              
                                              #foreach($listkate in $listkategori)
                                                
                                                #if($kategori==$listkate.id)
                                                
                                                #set($listkategoriK=$listkate.kod)
                                                #set($listkategoriN=$listkate.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($kategori!="")
                                                <select name="socKategoriTanahHtaam" class="autoselect" $readmode id="socKategoriTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()"  onchange="pilih_jenis_luas('socJenisLuasHtaam','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaam','txtLuasHMpHtaam','meterhektar')" >
                                              <option value="$kategori">$listkategoriK - $listkategoriN</option>
                                                  
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                                  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socKategoriTanahHtaam" class="autoselect" $readmode id="socKategoriTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaam','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaam','txtLuasHMpHtaam','meterhektar')" >
    <option value="">Sila Pilih Kategori Tanah</option>
    
  
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
  
    <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
    
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  

                                            





  </select>
                                              #end </td>
                                            </tr>
                                            
                                            <tr>
                                              <td class="style38"> <div align="left">Luas Asal</div></td>
                                              <td><div align="right">:</div></td>
                                              <td> #foreach($listluashta in $listluas)
                                                
                                                #if($jenisluas==$listluashta.id)
                                                
                                                #set($listluasK=$listluashta.kod)
                                                #set($listluasN=$listluashta.nama)
                                                #end 
                                                #end
                                                #if($jenisluas!="")
                                                
                                            <input name="txtLuasAsalHtaam" type="text" id="txtLuasAsalHtaam" value="$luasasal" size="15" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam')" readonly="readonly"  class="disabled"  />
                                            
                                            
                                                #else
  
  <input name="txtLuasAsalHtaam" type="text" id="txtLuasAsalHtaam" value="$luasasal" size="15" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam')" readonly="readonly"  class="disabled" />
  
                                              #end </td>
                                            </tr>
                                            
                                            <tr>
                                              <td class="style38"> <div align="left">Jenis Luas</div></td>
                                              <td><div align="right">:</div></td>
                                              <td> #foreach($listluashta in $listluas)
                                                
                                                #if($jenisluas==$listluashta.id)
                                                
                                                #set($listluasK=$listluashta.kod)
                                                #set($listluasN=$listluashta.nama)
                                                #end 
                                                #end
                                                #if($jenisluas!="")
                                                
                                            
                                            
                                            <select name="socJenisLuasHtaam" class="autoselect" $readmode id="socJenisLuasHtaam" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaam','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaam','txtLuasHMpHtaam','meterhektar')"   >
                                              <option value="$jenisluas">$listluasK - $listluasN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
  
  
  
  <select name="socJenisLuasHtaam" class="autoselect" $readmode id="socJenisLuasHtaam" style="text-transform:uppercase;" onblur="uppercase()"  onchange="pilih_jenis_luas('socJenisLuasHtaam','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaam','txtLuasHMpHtaam','meterhektar')"  >
    <option value="">Sila Pilih Jenis Luas </option>
    
  
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
  
    <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
    
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



  </select>
                                                #end </td>
                                            </tr>
                                            
                                            <tr id="tr_luasharta" style="display:none;">
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td>
                                              <span id="luas1" style="display:none;">
                                              <input name="txtLuasAsalHtaam1" type="text" id="txtLuasAsalHtaam1" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam1')"   />
                                              </span>
                                              <span id="luas2" style="display:none;">
                                               <input name="txtLuasAsalHtaam2" type="text" id="txtLuasAsalHtaam2" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam2')"   />
                                               </span>
                                               <span id="luas3" style="display:none;">
                                                <input name="txtLuasAsalHtaam3" type="text" id="txtLuasAsalHtaam3" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam3')"  />
                                                </span>                                              </td>
                                            </tr>
                                            <tr id="tr_luasharta_b" style="display:none;">
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                              <input type="button" name="button2" id="button2" value="Convert" onclick="ConvertLuasHarta('socJenisLuasHtaam','txtLuasAsalHtaam','txtLuasHMpHtaam','meterhektar','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','socKategoriTanahHtaam')" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="left">Luas (Hektar/MP)</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
 <input name="txtLuasHMpHtaam" type="text" id="txtLuasHMpHtaam" value="$luashmp" size="15" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasHMpHtaam')" />
                                              <input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" />
                                              </label></td>
                                            </tr>
                                            
                                            
                                            <tr>
                                              <td class="style38"><div align="left">Nilai Tarikh Mohon (RM)</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>
                                              
                                             
                                               #if($nilai_Hta_memohon=="")
                                               #set($n4="")
                                               #elseif($nilai_Hta_memohon==0 || $nilai_Hta_memohon==0.0)
                                               #set($n4="0.00")
                                               #else
                                               #set($n4=$nilai_Hta_memohon)                                             
                                               #end
                                            
                                               <input name="txtNilaiTarikhMohonHtaa" type="text" id="txtNilaiTarikhMohonHtaa" $readmode value="$n4"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaa')"   onblur="validateModal(this,this.value,'$n4');samakan1()" />
                                            
                                            <!--
                                             #if($nilai_Hta_memohon=="" || $nilai_Hta_memohon=="0")
                                             #set($nhtam="0.00")
                                            
                                             #elseif($nilai_Hta_memohon!="" || $nilai_Hta_memohon!="0")
                                             #set($nhtam=$nilai_Hta_memohon)
                                             #else
                                             
                                              #set($nhtam="0.00")
                                             #end
                                              <label>
                                              <input name="txtNilaiTarikhMohonHtaa" type="text" id="txtNilaiTarikhMohonHtaa" value="$nhtam" size="15" maxlength="8" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaa')"  />
                                              </label>
                                              -->                                              </td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="left">Nilai Tarikh Mati(RM) </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>
                                           
                                             
                                              #if($nilai_Hta_mati=="") 
                                              #set($n3="")
                                              #elseif($nilai_Hta_mati==0 || $nilai_Hta_mati==0.0)
                                              #set($n3="0.00")  
                                              #else
                                              #set($n3=$nilai_Hta_mati)
                                              #end
                                           
                                               <input name="txtNilaiTarikhMatiHtaam" type="text" id="txtNilaiTarikhMatiHtaam" $readmode value="$n3"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaam')"   onblur="validateModal(this,this.value,'$n3');" />                                            </td>
                                            </tr>
                                            
                                            <tr>
                                              <td class="style38"><div align="left">Status Pemilikan </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listpemilik in $listpemilikan)
                                                
                                                #if($pemilikan==$listpemilik.id)
                                                
                                                #set($listpemilikK=$listpemilik.kod)
                                                #set($listpemilikN=$listpemilik.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($pemilikan!="")
                                                <select name="socStatusPemilikanHtaam" class="autoselect" $readmode id="socStatusPemilikanHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$pemilikan">$listpemilikK - $listpemilikN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                  #if($pemilikan!=$listpemilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socStatusPemilikanHtaam" class="autoselect" $readmode id="socStatusPemilikanHtaamUp" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Status Pemilikan</option>
    
    
  
  
  
  
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                
	                               
                                              
  
  
  
  
    
    <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
    
    
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




  
  </select>
                                                #end </td>
                                            </tr>
                                            <tr>

                                              <td class="style38"><div align="left">Tanggungan</div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td><input name="txtTanggunganHtaam" type="text" id="txtTanggunganHtaam" value="$tanggungan" size="15" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" /></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="left">Jenis Tanah</div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td><label>
                                                
                                               
                                                #if($jenistanah!="" && $jenistanah!="0")
                                              <select name="socJenisTanahHtaam" class="autoselect" $readmode id="socJenisTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              
                                              #if($jenistanah=="1")
                                                  <option value="1">TANAH RIZAB</option>
                                                  <option value="2">TANAH ADAT</option>
                                                  <option value="3">TANAH GSA</option>
                                                 <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                               #if($jenistanah=="2")
                                                <option value="2">TANAH ADAT</option>
                                <option value="1">TANAH RIZAB</option>
                                                 
                                                  <option value="3">TANAH GSA</option>
                                                  <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                              
                                               #if($jenistanah=="3")
                                                 <option value="3">TANAH GSA</option>
                                               <option value="1">TANAH RIZAB</option>
                                                 
                                                <option value="2">TANAH ADAT</option>
                                
                                                
                                                 <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socJenisTanahHtaam" class="autoselect" $readmode id="socJenisTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="0">Sila Pilih Jenis Tanah</option>
    
    
                                                  <option value="1">TANAH RIZAB</option>
                                                  <option value="2">TANAH ADAT</option>
                                                  <option value="3">TANAH GSA</option>
                                                  <option value="0">TIDAK DINYATAKAN</option>
  </select>
                                                #end </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38" valign="top"><div align="left">Catatan</div></td>
                                              <td width="1%" valign="top"><div align="right">:</div></td>
                                              <td><textarea name="txtCatatanHtaam" id="txtCatatanHtaam" cols="31" rows="5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()">$catatan</textarea></td>
                                            </tr>
                                          </table></td>
                                        </tr>
                                      </table>
                                      </fieldset>
                                      <table width="100%">
                                <tr>
                                  <td><span class="style44 style69 style47 style5 style43"><em><span class="style41">Perhatian</span> : Sila  pastikan label bertanda <span class="style41">*</span> diisi.</em></span></td>
                              </tr>
</table>
                                      
                                    #end</td>
                      </tr>
                                  
                                  
                                  #foreach($listamid in $listHTAid)

                                  <tr>
                                    <td>
                                    
                                  
                    

                    #if($show_htaa_update_table=="yes") 
                  
                    <fieldset>
                    <legend>HARTA TAK ALIH (ADA HAKMILIK) </legend>
                    #if($readmode == "disabled") #set($readmodeR = "readonly") #else #set($readmodeR = "") #end 
                    <table width="100%" border="0">
                      <tr>
                      <input type="hidden" name="id_htaam" value="$listamid.idhta" />
                      #set($idhta = $listamid.idhta)
                      
                      
                     
                        <td valign="top" width="50%"><table width="100%" border="0">
                          
                          
                          <tr>
                            <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                            <td width="29%" class="style38"><div align="right" class="style44">
                              <div align="left">#if($readmodenegeri != "disabled")Negeri#else Negeri#end</div>
                            </div></td>
                            <td width="1%">:</td>
                            <td width="70%"> #foreach($listnegpomo in $listnegeri)
                              
                              #if($listamid.negeri==$listnegpomo.id_Negeri)
                              
                              #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                              #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                              
                              
                              
                              #end 
                              #end
                              
                              
                              #if($readmode == "disabled")
                              
                               #if($listamid.negeri!="" && $listamid.negeri!="0" )
                                     <input name="n" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="n" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />

                                     #end
                              
                              #else
                              #if($listamid.negeri!="")
                              <select name="socNegeriHtaamUp" class="autoselect" $readmodenegeri  onchange="negerichangeup('socDaerahHtaamUp')"  style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listamid.negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            
                                <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                              </select>
                              #else
  <select name="socNegeriHtaamUp" class="autoselect" $readmodenegeri onchange="negerichangeup('socDaerahHtaamUp')" style="text-transform:uppercase;" onblur="uppercase()" >
    <option value="">Sila Pilih Negeri</option>
    
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                            
    <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
    
                                            
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
  </select>
                    #end          #end </td>
                          </tr>
                          <tr>
                          <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                            <td class="style38" ><div align="right" class="style44">
                              <div align="left">#if($readmodedaerah != "disabled")Daerah#else Daerah#end</div>
                            </div></td>
                            <td>:</td>
                            <td>#foreach($listdaerah in $listdaerah)
                              
                              #if($listamid.daerah==$listdaerah.id)
                              
                              #set($listDaerahbyNegeriK=$listdaerah.kod)
                              #set($listDaerahbyNegeriN=$listdaerah.nama)
                              
                              
                              
                              #end 
                              #end
                              
                                 #if($readmode == "disabled")
                              
                               #if($listamid.daerah!="" && $listamid.daerah!="0" )
                                     <input name="d" value="$listDaerahbyNegeriK - $listDaerahbyNegeriN" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="d" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                              
                              
                              #if($listamid.daerah!="")
                              <select name="socDaerahHtaamUp" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchangeup('socMukimHtaamUp');daerah_harta();check_harta()" id="socDaerahHtaamUp" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($listamid.daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            
                                <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                              </select>
                              #else
  <select name="socDaerahHtaamUp" id="socDaerahHtaamUp" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchangeup('socMukimHtaamUp');daerah_harta();check_harta()" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Daerah</option>
    
                                            
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
                                            
    <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
    
                                            
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

                                          
  </select>
                   #end           #end 
                   
                   #if($readmode != "disabled")
                     <span id="check_daerah_harta" style="color:red" ></span>
                   #end                   </td>
                          </tr>
                          <tr>
                           <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                            <td class="style38" ><div align="right" class="style44">
                              <div align="left">#if($readmodemukim != "disabled")Mukim#else Mukim#end</div>
                            </div></td>
                            <td>:</td>
                            <td>#foreach($listmukim in $listmukim)
                              
                              
                              #if($listamid.mukim==$listmukim.id)
                              
                              #set($listMukimbyDaerahK=$listmukim.kod)
                              #set($listMukimbyDaerahN=$listmukim.nama)
                              
                              
                              
                              #end 
                              #end
                              
                              #if($readmode == "disabled")
                              
                               #if($listamid.mukim!="" && $listamid.mukim!="0" )
                                     <input name="m" value="$listMukimbyDaerahK - $listMukimbyDaerahN" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="m" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                              
                              
                              
                              #if($listamid.mukim!="")
                              
                              <select name="socMukimHtaamUp" class="autoselect" $readmodemukim id="socMukimHtaamUp" style="text-transform:uppercase;" onblur="uppercase()" onfocus="CheckMukimU()" >
                                              <option value="$listamid.mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($listamid.mukim!=$listmukim.id)
                                            
                                <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                              </select>
                              #else
  
                              <select name="socMukimHtaamUp" class="autoselect" $readmodemukim id="socMukimHtaamUp" style="text-transform:uppercase;" onblur="uppercase()" onfocus="CheckMukimU()" >
    <option value="">Sila Pilih Mukim</option>
    
                                            
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
                                            
    <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
    
                                            
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                          
  </select>
              #end                #end </td>
                          </tr>
                          <tr>
                          <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                            <td class="style38" ><div align="right" class="style44">
                              <div align="left">#if($readmode != "disabled")Jenis Hakmilik#else Jenis Hakmilik#end </div>
                            </div></td>
                            <td>:</td>
                            <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                              
                              #if($listamid.jenishakmilik==$listjenishakmilik.id)
                              
                              #set($listjenishakmilikK=$listjenishakmilik.kod)
                              #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                              
                              
                         


                              #end 
                              #end
                           
                              #if($readmode == "disabled")
                              
                               #if($listamid.jenishakmilik!="" && $listamid.jenishakmilik!="0" )
                                     <input name="jh" value="$listjenishakmilikK - $listjenishakmilikN" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="jh" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                              
                              
                              
                              #if($listamid.jenishakmilik!="")
                              <select name="socJenisHakmilikHtaamUp" class="autoselect" $readmode id="socJenisHakmilikHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.jenishakmilik">$listjenishakmilikK - $listjenishakmilikN</option>
                                
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                  
                                  #if($listamid.jenishakmilik!=$listjenishakmilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
                                
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                              </select>
                              #else
  <select name="socJenisHakmilikHtaamUp" class="autoselect" $readmode id="socJenisHakmilikHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Jenis HakMilik</option>
    
                                            
  
  
  
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                 
                                
	                          
                                            
    <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
    
                                            
                                                 
                                              
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



                                          
  </select>
          #end                    #end </td>
                          </tr>
                          
                          
                          <tr>
                           <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                            <td class="style38 style44"><div align="right">
                              <div align="left">#if($readmode != "disabled")No Hakmilik#else No Hakmilik#end </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoHakmilikHtaamUp" type="text" id="txtNoHakmilikHtaam2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listamid.noHakmilik" size="50" maxlength="50" $readmodeR class="$readmode"  />
                            </label></td>
                          </tr>
                          <tr>
                          <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                            <td class="style38"><div align="right" class="style44"> 
                              <div align="left">#if($readmode != "disabled")No PT / No Lot#else No PT / No Lot #end </div>
                            </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoPTHtaamUp" type="text" id="txtNoPTHtaamUp" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listamid.nopt" size="15" maxlength="50" $readmodeR class="$readmode" onkeyup="no_lot1();checklot()"   />
                            </label>
                            #if($readmode != "disabled")
                               <span id="checklot" style="color:red" ></span>
                            #end                            </td>
                          </tr>
                          <tr>
                            <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                            <td class="style38"><div align="right" class="style44">
                              <div align="left">#if($readmode != "disabled") Bahagian Simati #else Bahagian Simati #end</div>
                            </div></td>
                            <td>:</td>
                            <td><input name="txtBahagianSimati1Up" type="text" id="txtBahagianSimati3" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati1Up')" style="text-align:right;text-transform:uppercase;" value="$listamid.basimati" size="14" maxlength="14" $readmodeR class="$readmode"   onblur="bahagiansamaUp()" />
                              /
                              <input name="txtBahagianSimati2Up" type="text" id="txtBahagianSimati4" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati2Up')" style="text-align:left;text-transform:uppercase;" value="$listamid.bbsimati" size="14" maxlength="14" $readmodeR class="$readmode"  onblur="bahagiansamaUp()" />                            </td>
                          </tr>
                          <tr>
                            <td class="style38">&nbsp;</td>
                            <td class="style38"><div align="right" class="style44">
                              <div align="left">No Pajakan</div>
                            </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoPajakanUp" type="text" id="txtNoPajakan2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listamid.nopajakan" size="15" maxlength="12" $readmodeR class="$readmode"   />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38" valign="top">&nbsp;</td>
                            <td class="style38" valign="top"><div align="right" class="style44">
                              <div align="left">No Perserahan</div>
                            </div></td>
                            <td>:</td>
                            <td valign="top"><label>
                              <input name="txtNoPersHtaamUp" type="text" id="txtNoPersHtaam2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listamid.noperserahan" size="15" maxlength="12" $readmodeR class="$readmode"   />
                            </label></td>
                          </tr>
                        </table></td>
                        <td valign="top" width="50%"><table width="100%" border="0">
                          <tr>
                            <td width="29%" class="style38"><div align="left">Kategori Tanah</div></td>

                            <td width="1%">:</td>
                            <td width="70%">
                            
                            #if($listamid.kategori == "2")
                            
                            #set($meterhektar = "Hektar")
                            #elseif($listamid.kategori == "1" || $listamid.kategori == "3" || $listamid.kategori == "4" || $listamid.kategori == "5" || $listamid.kategori == "6")
                             #set($meterhektar = "Meter Persegi")
                             
                             #else
                             #set($meterhektar = "")
                             
                            #end
                            
                            
                           
                            
                            
                            #foreach($listkate in $listkategori)
                              
                              #if($listamid.kategori==$listkate.id)
                              
                              #set($listkategoriK=$listkate.kod)
                              #set($listkategoriN=$listkate.keterangan)
                              
                              
                              
                              #end 
                              #end
                              
                              
                             #if($readmode == "disabled")
                             
                             
                               #if($listamid.kategori!="" && $$listamid.kategori!="0" )
                                     <input name="ktt" value="$listkategoriK - $listkategoriN" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="ktt" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                            
                            #else
                              #if($listamid.kategori!="")
                              <select name="socKategoriTanahHtaamUp" class="autoselect" $readmode id="socKategoriTanahHtaamUp" style="text-transform:uppercase;" onblur=""  onchange="pilih_jenis_luas('socJenisLuasHtaamUpd','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamUpd','txtLuasHMpHtaamUpd','meterhektar')"  >
                                              <option value="$listamid.kategori">$listkategoriK - $listkategoriN</option>
                                
                                            
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($listamid.kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                            
                                <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                          
                              </select>
                              #else
  <select name="socKategoriTanahHtaamUp" class="autoselect" $readmode id="socKategoriTanahHtaamUp" style="text-transform:uppercase;" onblur="uppercase()"  onchange="pilih_jenis_luas('socJenisLuasHtaamUpd','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamUpd','txtLuasHMpHtaamUpd','meterhektar')"   >
    <option value="">Sila Pilih Kategori Tanah</option>
    
  
                                            
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
                                            
  
    <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
    
  
                                            
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




                                          

  </select>
                      #end        #end </td>
                          </tr>
                          <tr>
                            <td class="style38"> <div align="left">Luas Asal</div></td>
                            <td>:</td>
                            <td>#foreach($listluashta in $listluas)
                              
                              #if($listamid.jenisluas==$listluashta.id)
                              
                              #set($listluasK=$listluashta.kod)
                              #set($listluasN=$listluashta.nama)
                              #end 
                              #end
                              
                              #if($readmode == "disabled")
                                             <input name="txtLuasAsalHtaamUpd" type="text" id="txtLuasAsalHtaamUpd" value="$listamid.luasasal" size="15" maxlength="15" $readmodeR class="$readmode" onblur="getConversionU()" />
                                 
                                   #if($listamid.jenisluas!="" && $listamid.jenisluas!="0")
                    
                   #else
                     
                   #end 
                                            
                              #else
                              #if($listamid.jenisluas!="" && $listamid.jenisluas!="0")
                             
                              
                                            <input name="txtLuasAsalHtaamUpd" type="text" id="txtLuasAsalHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamUpd')" value="$listamid.luasasal" size="15" maxlength="15" readonly  class = "disabled"  />
                                            
                                            
                              #else
 
  <input name="txtLuasAsalHtaamUpd" id="txtLuasAsalHtaamUpd" type="text" onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamUpd')" value="$listamid.luasasal" size="15" maxlength="15"  readonly  class = "disabled"  />
  
                 #end             #end </td>
                          </tr>
                          <tr>
                            <td class="style38"> <div align="left">Jenis Luas </div></td>
                            <td>:</td>
                            <td>#foreach($listluashta in $listluas)
                              
                              #if($listamid.jenisluas==$listluashta.id)
                              
                              #set($listluasK=$listluashta.kod)
                              #set($listluasN=$listluashta.nama)
                              #end 
                              #end
                              
                              #if($readmode == "disabled")
                                             
                                 
                                   #if($listamid.jenisluas!="" && $listamid.jenisluas!="0")
                   <input name="txtLuas" type="text" id="txtLuas" value="$listluasK - $listluasN" size="25"  $readmodeR class="$readmode"  /> 
                   #else
                    <input name="txtLuas" type="text" id="txtLuas" value="" size="25"  $readmodeR class="$readmode"  /> 
                   #end 
                                            
                              #else
                              #if($listamid.jenisluas!="" && $listamid.jenisluas!="0")
                             
                              
                                            
                                            
                                            <select name="socJenisLuasHtaamUpd" id="socJenisLuasHtaamUpd" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaamUpd','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamUpd','txtLuasHMpHtaamUpd','meterhektar')"  >
                                              <option value="$listamid.jenisluas">$listluasK - $listluasN</option>
                                              
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($listamid.jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                
                                              <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                              
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                              
                                            </select>
                              #else
 
  
  
  <select name="socJenisLuasHtaamUpd" class="autoselect" $readmode id="socJenisLuasHtaamUpd" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaamUpd','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamUpd','txtLuasHMpHtaamUpd','meterhektar')" >
    <option value="">Sila Pilih Jenis Luas</option>
    
  
                                            
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
                                            
  
    <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
    
  
                                            
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                          

  </select>
                 #end             #end </td>
                          </tr>
                          <tr id="tr_luasharta" style="display:none;">
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td>
                                              <span id="luas1" style="display:none;">
                                              <input name="txtLuasAsalHtaam1" type="text" id="txtLuasAsalHtaam1" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam1')"   />
                                              </span>
                                              <span id="luas2" style="display:none;">
                                               <input name="txtLuasAsalHtaam2" type="text" id="txtLuasAsalHtaam2" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam2')"   />
                                               </span>
                                               <span id="luas3" style="display:none;">
                                                <input name="txtLuasAsalHtaam3" type="text" id="txtLuasAsalHtaam3" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam3')"  />
                                                </span>                                              </td>
                                            </tr>
                                            <tr id="tr_luasharta_b" style="display:none;">
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input type="button" name="button2" id="button2" value="Convert" onclick="ConvertLuasHarta('socJenisLuasHtaamUpd','txtLuasAsalHtaamUpd','txtLuasHMpHtaamUpd','meterhektar','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','socKategoriTanahHtaamUp')" />
                                              </label></td>
                                            </tr>
                          <tr>
                            <td class="style38"><div align="left">Luas (Hektar/MP) </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtLuasHMpHtaamUpd" type="text" class="$readmode" id="txtLuasHMpHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtLuasHMpHtaamUpd')" value="$listamid.luashmp" size="15" maxlength="15" $readmodeR />
                              <input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="left">Nilai Tarikh Mohon (RM)</div></td>
                            <td>:</td>
                            <td><label>
                             #if($readmode == "disabled")
                             #if($listamid.nilai_Hta_memohon!="")
                              <input name="txtNilaiTarikhMohonHt" type="text" value="$Util.formatDecimal($listamid.nilai_Hta_memohon)" size="15"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHt')" $readmodeR class="$readmode" />
                            #else
                               <input name="txtNilaiTarikhMohonHt" type="text" value="" size="15"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHt')" $readmodeR class="$readmode" />
                             #end
                             #else
                             #set($listamidnilai_Hta_memohon=$listamid.nilai_Hta_memohon)
                              <input name="txtNilaiTarikhMohonHt" type="text" value="$listamidnilai_Hta_memohon" size="15"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHt')" $readmodeR class="$readmode" onblur="validateModal(this,this.value,'$listamidnilai_Hta_memohon');samakan2()" />
                              
                              #end
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="left">Nilai Tarikh Mati (RM)</div></td>
                            <td>:</td>
                            <td><label>
                           
                            #if($readmode == "disabled")
                            #if($listamid.nilai_Hta_mati!="")
                              <input name="txtNilaiTarikhMatiHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamUpd')" type="text" id="txtNilaiTarikhMatiHtaam2" value="$Util.formatDecimal($listamid.nilai_Hta_mati)" size="15" $readmodeR class="$readmode" />
                              #else
                               <input name="txtNilaiTarikhMatiHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamUpd')" type="text" id="txtNilaiTarikhMatiHtaam2" value="" size="15" $readmodeR class="$readmode" /> 
                              #end
                            
                            #else
                           #set($listamidnilai_Hta_mati=$listamid.nilai_Hta_mati)
                              <input name="txtNilaiTarikhMatiHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamUpd')" type="text" id="txtNilaiTarikhMatiHtaam2" value="$listamidnilai_Hta_mati" size="15" $readmodeR class="$readmode" onblur="validateModal(this,this.value,'$listamidnilai_Hta_mati');" />
                              #end
                            </label></td>
                          </tr>
                         
                          
                          <tr>
                            <td class="style38"><div align="left">Status Pemilikan</div></td>
                            <td>:</td>
                            <td>#foreach($listpemilik in $listpemilikan)
                              
                              #if($listamid.pemilikan==$listpemilik.id)
                              
                              #set($listpemilikK=$listpemilik.kod)
                              #set($listpemilikN=$listpemilik.keterangan)
                              
                              
                              
                              #end 
                              #end
                              
                              
                              
                              #if($readmode == "disabled")
                              
                               #if($listamid.pemilikan!="" && $listamid.pemilikan!="0" )
                                     <input name="jstm" value="$listpemilikK - $listpemilikN" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="jstm" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                              
                              
                              
                              #if($listamid.pemilikan!="")
                              <select name="socStatusPemilikanHtaamUpd" class="autoselect" $readmode id="socStatusPemilikanHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.pemilikan">$listpemilikK - $listpemilikN</option>
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                  #if($listamid.pemilikan!=$listpemilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                              </select>
                              #else
  <select name="socStatusPemilikanHtaamUpd" class="autoselect" $readmode id="socStatusPemilikanHtaamUpd" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Status Pemilikan </option>
    
                                            
    
  
  
  
  
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                
	                               
                                              
  
  
  
  
    
                                            
    <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
    
                                            
    
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




  
                                          
  </select>
     #end                         #end </td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="left">Tanggungan </div></td>
                            <td>:</td>
                            <td><input name="txtTanggunganHtaamUp" type="text" id="txtTanggunganHtaam2" value="$listamid.tanggungan" size="15"$readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" /></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="left">Jenis Tanah</div></td>
                            <td>:</td>
                            <td><label>#foreach($listtan in $listtanah)
                              
                              #if($listamid.jenistanah==$listtan.id)
                              
                              #set($listtanK=$listtan.kod)
                              #set($listtanN=$listtan.keterangan)
                              
                              
                              
                              #end 
                              #end
                              
                              #if($readmode == "disabled")
                              
                               #if($listamid.jenistanah!="" && $listamid.jenistanah!="0" )
                                     <input name="jt" value="$listtanK - $listtanN" size="25" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="jt" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                              
                              #if($listamid.jenistanah!="" && $listamid.jenistanah!="0")
                              <select name="socJenisTanahHtaamUpd" class="autoselect" $readmode id="socJenisTanahHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
                                             
                                  
                                  
                                  
                                             #if($listamid.jenistanah=="1")
                                                  <option value="1">TANAH RIZAB</option>
                                                  <option value="2">TANAH ADAT</option>
                                                  <option value="3">TANAH GSA</option>
                                                  <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                               #if($listamid.jenistanah=="2")
                                                <option value="2">TANAH ADAT</option>
                                <option value="1">TANAH RIZAB</option>
                                                 
                                                  <option value="3">TANAH GSA</option>
                                                  <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                              
                                               #if($listamid.jenistanah=="3")
                                                 <option value="3">TANAH GSA</option>
                                               <option value="1">TANAH RIZAB</option>
                                                 
                                                <option value="2">TANAH ADAT</option>
                                
                                                
                                                 <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                  
                                  
                                  
                                            
                                           
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                              </select>
                              #else
  <select name="socJenisTanahHtaamUpd" class="autoselect" $readmode id="socJenisTanahHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="0">TIDAK DINYATAKAN</option>
    
                                                  <option value="1">TANAH RIZAB</option>
                                                  <option value="2">TANAH ADAT</option>
                                                  <option value="3">TANAH GSA</option>
  </select>
                  #end            #end </label></td>
                          </tr>
                          <tr>
                            <td class="style38" valign="top"><div align="left">Catatan</div></td>
                            <td valign="top">:</td>
                            <td><textarea name="txtCatatanHt" id="txtCatatanHt" $readmodeR class="$readmode" value="$listamid.catatan" cols="31" rows="5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" >$listamid.catatan</textarea>                            </td>
                          </tr>
                        </table></td>
                      </tr>
                    </table>
                    </fieldset>
                     #if($readmode != "disabled")
  
                              <table width="100%">
                                <tr>
                                  <td><span class="style45 style69 style5 style43"><em>Perhatian <span class="style44">: Sila pastikan label bertanda</span> * <span class="style44">diisi.</span></em></span></td>
                                </tr>
</table>
 
#end
                                  
                 
#end                                    </td>
                      </tr>
                      
                                  #end
                                  
                                  #if($show_button=="yes")
                                  <tr>
                                    <td><table width="100%" border="0" align="center">
                                      <tr>
                                      
                    
                                        <td align="center">
                                        
                                        #if($show_simpan_add_htaam=="yes")
                                          <input type="button" name="tambahpenghutang2" id="tambahpenghutang2" $readmode value="Simpan " onclick="setSelected(1,0,0,0);add_Htaam()"/>
                                        #end
                                        
                                        #if($show_batal_add_htaam=="yes")
                                          <input type="reset" name="cmdSimpan6" id="cmdSimpan6" value="Batal" onclick="cancelwaris()"/>
                                       
                                        #end
                                         #if($show_kemaskini_htaam=="yes")
                                         
               #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")              <!--   <input type="submit" name="button8" id="button8" value="Kemaskini"  onclick="setSelected(1,0,0,0);edit_Htaam()" /> -->
                    #end
                 #if($boleh_kemaskini == "yes")<input type="submit" name="button8" id="button8" value="Kemaskini"  onclick="setSelected(1,0,0,0);edit_Htaam()" />#end   
                    
                    
                                            #end
                                         #if($show_save_update_htaam=="yes")
                                          <input type="button" name="button9" id="button9" value="Simpan"  onclick="setSelected(1,0,0,0);save_Htaam()"/>
                                       #end
                                        #if($show_batal_update_htaam=="yes")
                                          <input type="button" name="button10" id="button10" value="Batal"  onclick="setSelected(1,0,0,0);batal_Htaam()" />
                                       #end
                                       #if($show_hapus_htaam=="yes" && $show_kemaskini_htaam=="yes" )
                    #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")

                 <!--                         <input type="button" name="cmdCetak7" id="cmdCetak7" value="Hapus"  onclick="setSelected(1,0,0,0);hapus_Htaam()"/> -->
                                          <!-- 
                                          <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onclick="javascript:cetak('$NO_FAIL')"/>
                                          -->
					 #end
                       #if($boleh_kemaskini == "yes")  <input type="button" name="cmdCetak7" id="cmdCetak7" value="Hapus"  onclick="setSelected(1,0,0,0);hapus_Htaam()"/> #end                 
                                          
                                          
                                     #end
                                     
                                        <input type="button" name="cmdKembali7" id="cmdKembali7" value="Kembali" onclick="setSelected(1,0,0,0);HtaamView()" />
                                        <input type=hidden value=$NO_FAIL>                                        </td>
                                      </tr>
                                    </table>
                                    
                                    
                  
                                    
                                    </td>
                                  </tr>
                                  
                                  #end
                                 
                                  
                                  
                                  <tr>
                                    <td>
                                    
                                  
                                    <fieldset><legend>SENARAI HARTA TAK ALIH (ADA HAKMILIK)</legend>
                                      <table width="100%">
                                      <tr>
                                     
                                        <td align="left">
                                        <div align="left">
                                         #if($tambahharta == "yes")
                                         
                                         #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
<!--
                                        <input type="submit" name="button" id="button" value="Tambah" onclick="nktambah()"/>   -->
                                          #end
                                          
                                          #if($boleh_kemaskini == "yes") <input type="submit" name="button" id="button" value="Tambah" onclick="nktambah()"/>#end
                                          
                                          
                                          
                                          #end
                                           #if($kembaliharta == "yes")
                                            <!--
                                            <input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali"  onclick="kembali_simati()" />
                                        --> 
                                          #end      
                                             
                                            
                                                                                
                                            #if($listHTA.size()>0 && $show_htaa_add_table=="" && $show_htaa_update_table=="" ) 
                                          <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onclick="javascript:setTable('tableReport')"/>  
                                          #end
                                          </div>
                                          
                                        
                                          
                                        </td>
                                      </tr>
                                    </table>
                                    
                                                                        <fieldset  id="tableReport" style="display:none;" >
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr> 
        <td >
       
        #if($flagFromSenaraiFailSek8 != "yes" && $flagForView != "yes")
        <a href="#" class="style2 style42" onClick="javascript:tukarstatus();cetak('$NO_FAIL','$idhta')">Surat ke JPPH</a>
        #else
         <a href="#" class="style2 style42" onClick="javascript:cetak('$NO_FAIL','$idhta')">Surat ke JPPH</a>
        #end
        
        </td>
      </tr>
      <tr>
        <td ><a href="#" class="style2 style42" onClick="javascript:cetakSuratPeringatan('$NO_FAIL','$idhta')">Surat Peringatan ke JPPH</a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2 style42" onClick="javascript:cetakNilaiHarta('$NO_FAIL')">Nilaian Harta oleh PPSPP</a></td>
      </tr>
     
      
      
    </table>
</fieldset> 
                  
                                    
                                    #if($listHTA.size()!=0 )    
                                    <table width="100%">
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <!--
                                        <td width="10%"><div align="center">ID HTA</div></td>
                                        -->
                                        <td width="20%"><div align="left">NEGERI</div></td>
                                        <td width="20%"><div align="left">DAERAH</div></td>
                                        <td width="20%"><div align="left">MUKIM</div></td>
                                        <td width="10%"><div align="left">NO HAKMILIK</div></td>
                                        <td width="10%"><div align="left">NO PT/NO LOT</div></td>
                                        <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      
                                      
                                      
                                      #set($plko=0)
                                      
                                      #foreach($listam in $listHTA)
                                      
                                    
                                      #set($plko=$plko+1)
                                      #if($plko%2!=0)
                                      
  <tr bgcolor="white">
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
    <!--
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$listam.idhta </a></div></td>
    -->
    #if($listam.negeri=="")
    
    	#set($nama_negeri="")
    #else
    	#foreach($ln in $listnegeri)                                                                      
    		#if($ln.id_Negeri==$listam.negeri)
    			#set($nama_negeri=$ln.nama_Negeri)
    
    		#end
    	#end
    
    #end
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$nama_negeri</a></div></td>
    #if($listam.daerah=="")
    
    #set($nama_daerah="")
    #else
    #foreach($ld in $listdaerah)                                                                      
    #if($ld.id==$listam.daerah)
    #set($nama_daerah=$ld.nama)
    
    #end
    #end
    
    #end
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$nama_daerah</div></td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
     
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$nama_mukim</div></td>
    
    
    #if($listam.noHakmilik != "" && $listam.kod_hakmilik != "")    
    #set($Z =  "$listam.kod_hakmilik${listam.noHakmilik}")    
    #else
    #set($Z =  $listam.noHakmilik)
    #end
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$Z</div></td>
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
    #else
    <td class="row1"> </td>
    #end </tr>
                                      #else
  <tr class="table_header">
    <td class="row2"><div align="center">$plko</div></td>
    <!--
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$listam.idhta</a></div></td>
    -->
    #if($listam.negeri=="")
    
    #set($nama_negeri="")
    #else
    #foreach($ln in $listnegeri)                                                                      
    #if($ln.id_Negeri==$listam.negeri)
    #set($nama_negeri=$ln.nama_Negeri)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$nama_negeri</a></div></td>
    #if($listam.daerah=="")
    
    #set($nama_daerah="")
    #else
    #foreach($ld in $listdaerah)                                                                      
    #if($ld.id==$listam.daerah)
    #set($nama_daerah=$ld.nama)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$nama_daerah</div></td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$nama_mukim</div></td>
    #if($listam.noHakmilik != "" && $listam.kod_hakmilik != "")    
    #set($Z =  "$listam.kod_hakmilik${listam.noHakmilik}")    
    #else
    #set($Z =  $listam.noHakmilik)
    #end
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$Z</div></td>
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
    #else
    <td class="row2"></td>
    #end </tr>
                                      #end
                                      #end
                                    </table>
                                    #else
                                      <table width="100%">
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <!--
                                        <td width="10%"><div align="center">ID HTA</div></td>
                                        -->
                                        <td width="20%"><div align="center">NEGERI</div></td>
                                        <td width="20%"><div align="center">DAERAH</div></td>
                                        <td width="20%"><div align="center">MUKIM</div></td>
                                        <td width="10%"><div align="center">NO HAK MILIK</div></td>
                                        <td width="10%"><div align="center">NO PT/NO LOT</div></td>
                                        <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      </table>
                                      <table width="100%">
                                      <tr bgcolor="white">
                                        <td align="left">Tiada Rekod
                                        </td>
                                      </tr>
                                      </table>
                                      
                                    
                                    
                                    #end
                                    
                                    
                                    </fieldset>
                                    </td>
                                    <input type="hidden" name="idhtaam" value="$listamid.idhta" />
                                   
                                    <input type="hidden" name="idhtaamid" value="$idhtaam" />
                                  </tr>
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                           
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                           <tr>
                <td>
                <p align="right">&nbsp;</p>
                </td>
                </tr>  
                                </table>
            
            </div>
           
            <div class="TabbedPanelsContent"></div>
            
            
          </div>
        </div>
      </div>
      
      <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
      
    
    </div>
  </div>    </td>
  
<input name="no_lot_hta" id="no_lot_hta" type="hidden"  />
<input name="id_harta" id="id_harta" type="hidden" value="$!idhta"  />
<input name="id_daerah_harta" id="id_daerah_harta" type="hidden" />
<input name="save_harta" id="save_harta" type="hidden" />
 
 
  </tr>
</table>
#parse("app/ppk/paging_sek8.jsp") 
</form>

<script>
<!-- TAB -->
function HtaamViewX() {
	document.f1.action = "";
	document.f1.mode.value = "HtaamviewX";
	document.f1.command.value = "HtaamX";
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

<!-- HTAAH -->
function addcancelhtaam() {
document.f1.reset();
document.f1.command.value = "Htaam";
document.f1.mode.value = "Htaamview";
document.f1.submit();
document.f1.socNegeriHtaam.focus();
}

function nktambah() {
document.f1.reset();
document.f1.command.value = "Htaam";
document.f1.mode.value = "add_new";
document.f1.submit();

}


function get_htaam(idhtaam)
{
    document.f1.command.value = "Htaam";
	document.f1.idhtaam.value = idhtaam;
	document.f1.mode.value = "getHtaam";
	document.f1.action = "";
	document.f1.submit();
}
function negerichange(v_t){
		document.f1.command.value="Htaam";
		document.f1.mode.value="negerichange";
		document.f1.action="";
        document.f1.v_tab.value = v_t;
		document.f1.submit();
}
function negerichangeup(v_t){
		
		document.f1.command.value="Htaam";
		document.f1.mode.value="negerichangeup";
		document.f1.action="";
        document.f1.v_tab.value = v_t;
		document.f1.submit();
}
function daerahchange(v_t){
		document.f1.command.value="Htaam";
		document.f1.mode.value="daerahchange";
		
		document.f1.action="";
        document.f1.v_tab.value = v_t;
		document.f1.submit();
}
function daerahchangeup(v_t){
		document.f1.command.value="Htaam";
		document.f1.mode.value="daerahchangeup";
		
		document.f1.action="";
        document.f1.v_tab.value = v_t;
		document.f1.submit();
}

function tambah_simpan_penting(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {if(document.f1.txtNamaOBPenting.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBPenting.focus(); 
			return; 
            }
		}
	document.f1.mode.value = "tambah_penghutang";
	document.f1.command.value = "Penghutang";
	document.f1.action = "";
	document.f1.submit();
		
	}


function add_Htaam(){
             var b1=parseInt(document.f1.txtBahagianSimati1.value);
             var b2=parseInt(document.f1.txtBahagianSimati2.value);

            if(document.f1.socNegeriHtaam.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		document.f1.socNegeriHtaam.focus(); 
			return; 
            }
            
            else if(document.f1.socDaerahHtaam.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.f1.socDaerahHtaam.focus(); 
			return; 
            }
            else if(document.f1.socMukimHtaam.value == ""){
			alert('Sila pilih " Mukim " terlebih dahulu.');
	  		document.f1.socMukimHtaam.focus(); 
			return; 
            }
            else if(document.f1.socJenisHakmilikHtaam.value == ""){
			alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
	  		document.f1.socJenisHakmilikHtaam.focus(); 
			return; 
            }
            else if(document.f1.txtNoHakmilikHtaam.value == ""){
			alert('Sila masukkan no hakmilik');
	  		document.f1.txtNoHakmilikHtaam.focus(); 
			return; 
            }
            else if(document.f1.txtNoPTHtaam.value == ""){
			alert('Sila masukkan no PT ataupun no Lot');
	  		document.f1.txtNoPTHtaam.focus(); 
			return; 
            }
            
             else if(document.f1.txtBahagianSimati1.value == "" && document.f1.txtBahagianSimati2.value == "" )
            {
            alert('Sila masuk bahagian simati');
	  		document.f1.txtBahagianSimati1.focus(); 
			return; 
            }
            
            else if(document.f1.txtBahagianSimati1.value != "" && document.f1.txtBahagianSimati2.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati2.focus(); 
			return; 
            }
            
            else if(document.f1.txtBahagianSimati2.value != "" && document.f1.txtBahagianSimati1.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati1.focus(); 
			return; 
            }
                        
            else if(b1>b2)
            {
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBahagianSimati1.focus(); 
			return; 
            }
            
          
		  else if(document.f1.save_harta.value == "yes" )
            {
            alert('Sila masukkan maklumat hta dibawah jagaan unit terlebih dahulu!');
	  	//	document.f1.txtBahagianSimati1.focus(); 
			return; 
            }
    
       
            
            else
            {
            input_box = confirm("Adakah anda pasti?");
	        if (input_box == true) 
            {
           
            document.f1.command.value="Htaam";
		    document.f1.mode.value="masukkan";		
		    document.f1.action="";
		    document.f1.submit();
		    }
            else
            {
            return;
            }
            
            }


		
}
function edit_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="kemaskiniHtaam";
		
		document.f1.action="";
		document.f1.submit();
}

function save_Htaam(){

var b1=parseInt(document.f1.txtBahagianSimati1Up.value);
             var b2=parseInt(document.f1.txtBahagianSimati2Up.value);



	        if(document.f1.socNegeriHtaamUp.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		
			return; 
            } 
                    
            else if(document.f1.socDaerahHtaamUp.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.f1.socDaerahHtaamUp.focus(); 
			return; 
            }
           else if(document.f1.socMukimHtaamUp.value == ""){
			alert('Sila pilih " Mukim " terlebih dahulu.');
	  		document.f1.socMukimHtaamUp.focus(); 
			return; 
            }
            else if(document.f1.socJenisHakmilikHtaamUp.value == "")
            {
			alert('Sila pilih " Jenis hakmilik" terlebih dahulu.');
	  		document.f1.socJenisHakmilikHtaamUp.focus(); 
			return; 
            }
             else if(document.f1.txtNoHakmilikHtaamUp.value == ""){
			alert('Sila masukkan no hakmilik');
	  		document.f1.txtNoHakmilikHtaamUp.focus(); 
			return; 
            }
            else if(document.f1.txtNoPTHtaamUp.value == ""){
			alert('Sila masukkan no PT ataupun no Lot');
	  		document.f1.txtNoPTHtaamUp.focus(); 
			return; 
            }
            
			  else if(document.f1.txtBahagianSimati1Up.value == "" && document.f1.txtBahagianSimati2Up.value == "" )
            {
            alert('Sila masuk bahagian simati');
	  		document.f1.txtBahagianSimati1Up.focus(); 
			return; 
            }
            else if(document.f1.txtBahagianSimati1Up.value != "" && document.f1.txtBahagianSimati2Up.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati2Up.focus(); 
			return; 
            }
            
            else if(document.f1.txtBahagianSimati2Up.value != "" && document.f1.txtBahagianSimati1Up.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati1Up.focus(); 
			return; 
            }
                        
            else if(b1>b2)
            {
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBahagianSimati1Up.focus(); 
			return; 
            }
            
			  else if(document.f1.save_harta.value == "yes" )
            {
            alert('Sila masukkan maklumat hta dibawah jagaan unit terlebih dahulu!');
	  	//	document.f1.txtBahagianSimati1.focus(); 
			return; 
            }
           
           else
           { 
           
        input_box = confirm("Adakah anda pasti?");   
        if (input_box == true) 
        {
          
        document.f1.command.value="Htaam";
		document.f1.mode.value="simpanHtaam";		
		document.f1.action="";
		document.f1.submit();
		}
        
        else
        {return;}
	
        }
}


function hapus_Htaam(){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="Htaam";
		document.f1.mode.value="hapusHtaam";		
		document.f1.action="";
		document.f1.submit();
        }
}
function kembali_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="kembaliHtaam";
		
		document.f1.action="";
		document.f1.submit();
}
function batal_Htaam(){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="Htaam";
		document.f1.mode.value="batalHtaam";
		
		document.f1.action="";

		document.f1.submit();
        }
}


function CheckMukimU()
{
if(document.f1.socDaerahHtaamUp.value == "" || document.f1.socDaerahHtaamUp.value == "0")
{
  alert("Sila pilih daerah terlebih dahulu.");
  document.f1.socDaerahHtaamUp.focus();
  return;
	  		
}


}





function CheckMukim()
{
if(document.f1.socDaerahHtaam.value == "" || document.f1.socDaerahHtaam.value == "0")
{
  alert("Sila pilih daerah terlebih dahulu.");
  document.f1.socDaerahHtaam.focus();
  return;
	  		
}


}

function CheckDaerah()
{
if(document.f1.socNegeriHtaam.value == "" || document.f1.socNegeriHtaam.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriHtaam.focus();
  return;
	  	
}

}

function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != "")
	{
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	}
	else
	{
	elmnt.value ="";
	return;
	}
	
	
	
}
//txtLuasHMpHtaam
//socKategoriTanahHtaam
//socJenisLuasHtaam
//txtLuasAsalHtaam

function convertTo(){
	if (document.f1.socJenisLuasHtaam.value=="4" || document.f1.socJenisLuasHtaam.value=="7"){
        var a = document.f1.txtLuasAsalHtaam.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")
        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaam.value=="2"){
        var a = document.f1.txtLuasAsalHtaam.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaam.value=="3"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaam.value=="5"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaam.value=="1"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
	
    
}




function getConversion(){
if(document.f1.txtLuasAsalHtaam.value != "" )
{
	if (document.f1.socJenisLuasHtaam.value=="4" || document.f1.socJenisLuasHtaam.value=="7"){
        var a = document.f1.txtLuasAsalHtaam.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")
        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaam.value=="2"){
        var a = document.f1.txtLuasAsalHtaam.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaam.value=="3"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaam.value=="5"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaam.value=="1"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 

}



function getConvert(){
if(document.f1.socKategoriTanahHtaam.value == 1 ||  document.f1.socKategoriTanahHtaam.value == 2 || document.f1.socKategoriTanahHtaam.value == 3 || document.f1.socKategoriTanahHtaam.value == 4 || document.f1.socKategoriTanahHtaam.value == 5  || document.f1.socKategoriTanahHtaam.value == 6  )
{
	if (document.f1.socJenisLuasHtaam.value=="4" || document.f1.socJenisLuasHtaam.value=="7"){
        var a = document.f1.txtLuasAsalHtaam.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")
        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaam.value=="2"){
        var a = document.f1.txtLuasAsalHtaam.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaam.value=="3"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaam.value=="5"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaam.value=="1"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 
 else
 {
  document.f1.socJenisLuasHtaam.value = "";
  document.f1.txtLuasHMpHtaam.value = "";
  document.f1.meterhektar.value= "";
  document.f1.txtLuasAsalHtaam.value = "";
 
 }

}


 //socKategoriTanahHtaamUp
 //txtLuasAsalHtaamUpd
 //socJenisLuasHtaamUpd
 //txtLuasHMpHtaamUpd
 
 
 
 
 
 
 
 function convertToU(){
	if (document.f1.socJenisLuasHtaamUpd.value=="4" || document.f1.socJenisLuasHtaamUpd.value=="7"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamUpd.value=="2"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamUpd.value=="3"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamUpd.value=="5"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamUpd.value=="1"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
	
    
}




function getConversionU(){
if(document.f1.txtLuasAsalHtaamUpd.value != "" )
{
	if (document.f1.socJenisLuasHtaamUpd.value=="4" || document.f1.socJenisLuasHtaamUpd.value=="7"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamUpd.value=="2"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamUpd.value=="3"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamUpd.value=="5"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamUpd.value=="1"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 

}



function getConvertU(){
if(document.f1.socKategoriTanahHtaamUp.value == 1 ||  document.f1.socKategoriTanahHtaamUp.value == 2 || document.f1.socKategoriTanahHtaamUp.value == 3 || document.f1.socKategoriTanahHtaamUp.value == 4 || document.f1.socKategoriTanahHtaamUp.value == 5  || document.f1.socKategoriTanahHtaamUp.value == 6  )
{
	if (document.f1.socJenisLuasHtaamUpd.value=="4" || document.f1.socJenisLuasHtaamUpd.value=="7"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamUpd.value=="2"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamUpd.value=="3"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamUpd.value=="5"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamUpd.value=="1"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 
 else
 {
  document.f1.socJenisLuasHtaamUpd.value = "";
  document.f1.txtLuasHMpHtaamUpd.value = "";
  document.f1.meterhektar.value= "";
  document.f1.txtLuasAsalHtaamUpd.value = "";
 
 }

}


function tukarstatus()
{

document.f1.command.value="Htaam";
		document.f1.mode.value="getHtaamStatus";		
		document.f1.action="";
		document.f1.submit();
}


function cetak(noFail,idhta) 
{

//alert(document.f1.jpphlepas.value)
if(document.f1.jpphlepas.value == "no")
{

 
  var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratIringanJPPH_TM&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
 
 
}

if(document.f1.jpphlepas.value == "yes")
{	
 
    var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratIringanJPPH&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
     
 }   
}

function cetakSuratPeringatan(noFail,idhta) 
{
 
 
	 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratPeringatan&flagReport=S";
   
    
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
      
    
}

	
	

function cetakNilaiHarta(noFail) 
{
  //  var url = "../servlet/ekptg.report.ppk.NilaianHartaPPSPP?nofail="+noFail;
  var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=NilaianHartaPPSPP&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}



function setTable(id){
//alert("dsdssd")
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
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

	
	window.location.hash='$val_tab';
goTo('$val_tab');
} 

*/

function submitForm() {    
//alert('$val_tab')
if('$val_tab' != "" && '$val_tab' != null)
{
	window.location.hash='$val_tab';
   goTo('$val_tab');
   }
	
} 




function bahagiansamaUp() {  
if(document.f1.txtBahagianSimati1Up.value != "" && document.f1.txtBahagianSimati2Up.value != "")
{
if(document.f1.txtBahagianSimati1Up.value ==document.f1.txtBahagianSimati2Up.value )
{
document.f1.txtBahagianSimati1Up.value = "1";
document.f1.txtBahagianSimati2Up.value = "1";
}
}  
} 

function bahagiansimati() { 
if(document.f1.txtBahagianSimati1.value != "" && document.f1.txtBahagianSimati2.value != "")
{
if(document.f1.txtBahagianSimati1.value == document.f1.txtBahagianSimati2.value )
{
document.f1.txtBahagianSimati1.value = "1";
document.f1.txtBahagianSimati2.value = "1";
}
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

function samakan1()
{
 
                                            
document.f1.txtNilaiTarikhMatiHtaam.value=document.f1.txtNilaiTarikhMohonHtaa.value
 
                 
}


 
                            
function samakan2()
{
 
                                            
document.f1.txtNilaiTarikhMatiHtaamUpd.value=document.f1.txtNilaiTarikhMohonHt.value
 
                 
}


function no_lot1()
{
//alert("ss")
if(document.f1.id_harta.value != "" && document.f1.id_harta.value != null)
{
document.f1.no_lot_hta.value = document.f1.txtNoPTHtaamUp.value
}else
{
document.f1.no_lot_hta.value = document.f1.txtNoPTHtaam.value
}
}

function no_lot2()
{

document.f1.no_lot_hta.value = document.f1.txtNoPTHtaamUp.value
}


function checklot()
{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_no_lot";
	//actionName = "check_harta";
	
	target = "checklot";
	doAjaxUpdater(document.f1, url, target, actionName);
}

function check_harta()
{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_harta";	
	target = "check_daerah_harta";
	doAjaxUpdater(document.f1, url, target, actionName);
}


function daerah_harta()
{
if(document.f1.id_harta.value != "" && document.f1.id_harta.value != null)
{
document.f1.id_daerah_harta.value = document.f1.socDaerahHtaamUp.value
}else
{
document.f1.id_daerah_harta.value = document.f1.socDaerahHtaam.value
}
}

</script>



</body>
</html>
