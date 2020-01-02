
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}
body {
	background-color: #FFFFFF;
}
.style38 {font-size: 10px}
.style41 {color: #FF0000}
.style42 {color: #0000FF}
-->
</style>
</head>

<body>
<form id="form1" name="f1" method="post" action="">
  


<table width="100%" border="0">

<tr>
<td>
 <input type="hidden" name="command" value="">
 <input type="hidden" name="mode" value="">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>

 
</td>

</tr>

#foreach($list in $View)
   #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
 

<tr>
<td>
<fieldset>
<legend>MAKLUMAT PERMOHONAN</legend>


<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42">$list.noFail</div></td>
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
          <td style="text-transform:uppercase;"><div align="left" class="style42">$kodlistnegori</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Daerah / Jajahan :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$list.namadaerah</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Unit :</div></td>
         <td style="text-transform:uppercase;"><div align="left"><span class="style42">$list.namaPejabat</span></div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Status Fail :</div></td>
         <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$list.namaPejabat</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$list.seksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$list.tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$list.namaPemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style42">$list.namaSimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
      </table>
    </div></td>
  </tr>
</table>

</fieldset>
</td>
</tr>
#end



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
            <li class="TabbedPanelsTab style1 style3" tabindex="0">ORANG BERKEPENTINGAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">PENGHUTANG</li>
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
            
            <div class="TabbedPanelsContentVisible">    
            
            
            
            
            
            </div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
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
                                      <fieldset><legend>HARTA TAK ALIH (ADA HAKMILIK)</legend>
                                      
                                      <table width="100%" border="0">
                                        <tr>
                                          <td width="50%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td width="29%" class="style38"><div align="right"><span class="style41">*</span>Negeri</div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td width="70%"> #foreach($listnegpomo in $listnegeri)
                                                
                                                #if($negeri==$listnegpomo.id_Negeri)
                                                
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($negeri!="")
                                                <select name="socNegeriHtaam" class="autoselect" $readmodenegeri onchange="setSelected(1,0,0,0);negerichange()" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                                  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                                </select>
                                                #else
  <select name="socNegeriHtaam" class="autoselect" $readmodenegeri onchange="setSelected(1,0,0,0);negerichange()" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Negeri</option>
    
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
    <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
    
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38" ><div align="right"><span class="style41">*</span>Daerah</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listdaerah in $listDaerahbyNegeri)
                                                
                                                #if($daerah==$listdaerah.id)
                                                
                                                #set($listDaerahbyNegeriK=$listdaerah.kod)
                                                #set($listDaerahbyNegeriN=$listdaerah.nama)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($daerah!="")
                                                <select name="socDaerahHtaam" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchange()" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                </select>
                                                #else
  <select name="socDaerahHtaam" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchange()" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Daerah</option>
    
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
    <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38" ><div align="right"><span class="style41">*</span>Mukim</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listmukim in $listMukimbyDaerah)
                                                
                                                #if($mukim==$listmukim.id)
                                                
                                                #set($listMukimbyDaerahK=$listmukim.kod)
                                                #set($listMukimbyDaerahN=$listmukim.nama)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($mukim!="")
                                                <select name="socMukimHtaam" class="autoselect" $readmodemukim id="socMukimHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                                  
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($mukim!=$listmukim.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                                  <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>
                                                  
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socMukimHtaam" class="autoselect" $readmodemukim style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Mukim</option>
    
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
    <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
    
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38" ><div align="right"><span class="style41">*</span>Jenis Hakmilik</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                                                
                                                #if($jenishakmilik==$listjenishakmilik.id)
                                                
                                                #set($listjenishakmilikK=$listjenishakmilik.kod)
                                                #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($jenishakmilik!="")
                                                <select name="socJenisHakmilikHtaam" class="autoselect" $readmode id="socJenisHakmilikHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$jenishakmilik">$listjenishakmilikK - $listjenishakmiliN</option>
                                                  
                                            
                                            
                                            
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                  
                                  #if($jenishakmilik!=$listjenishakmilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                                  <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
                                                  
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socJenisHakmilikHtaam" class="autoselect" $readmode id="socJenisHakmilikHtaam" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Jenis Hak Milik</option>
    
  
  
  
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                 
                                
	                          
    <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
    
                                                 
                                              
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">No Hak Milik</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNoHakmilikHtaam" type="text" id="txtNoHakmilikHtaam" value="$noHakmilik"  size="34" maxlength="12" style="text-transform:uppercase;" onblur="uppercase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">No PT / No Lot</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNoPTHtaam" type="text" id="txtNoPTHtaam" value="$nopt" size="34"  maxlength="12" style="text-transform:uppercase;" onblur="uppercase()" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">Bahagian Simati</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtBahagianSimati1" type="text" id="txtBahagianSimati1" value="$basimati" size="5" onKeyUp="javascript:validateIC(this,this.value,'txtBahagianSimati1')"/>
                                                /
                                                <input name="txtBahagianSimati2" type="text" id="txtBahagianSimati2" value="$bbsimati" size="5" onKeyUp="javascript:validateIC(this,this.value,'txtBahagianSimati2')"/>                                              </td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">No Pajakan</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNoPajakan" type="text" id="txtNoPajakan" value="$nopajakan" size="34" maxlength="12" style="text-transform:uppercase;" onblur="uppercase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38" valign="top"><div align="right">No Pers</div></td>
                                              <td valign="top"><div align="right">:</div></td>
                                              <td valign="top"><label>
                                                <input name="txtNoPersHtaam" type="text" id="txtNoPersHtaam" value="$noperserahan" size="34" maxlength="12" style="text-transform:uppercase;" onblur="uppercase()" />
                                              </label></td>
                                            </tr>
                                          </table></td>
                                          <td width="50%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td width="29%" class="style38"><div align="right">Kategory Tanah</div></td>
                                              <td><div align="right">:</div></td>
                                              <td width="70%">#foreach($listkate in $listkategori)
                                                
                                                #if($kategori==$listkate.id)
                                                
                                                #set($listkategoriK=$listkate.kod)
                                                #set($listkategoriN=$listkate.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($kategori!="")
                                                <select name="socKategoriTanahHtaam" class="autoselect" $readmode id="socKategoriTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$kategori">$listkategoriK - $listkategoriN</option>
                                                  
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                                  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socKategoriTanahHtaam" class="autoselect" $readmode id="socKategoriTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Kategori Tanah</option>
    
  
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
  
    <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
    
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





  </select>
                                              #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">Jenis Luas</div></td>
                                              <td><div align="right">:</div></td>
                                              <td> #foreach($listluashta in $listluas)
                                                
                                                #if($jenisluas==$listluashta.id)
                                                
                                                #set($listluasK=$listluashta.kod)
                                                #set($listluasN=$listluashta.nama)
                                                #end 
                                                #end
                                                #if($jenisluas!="")
                                                <select name="socJenisLuasHtaam" class="mediumselect" $readmode id="socJenisLuasHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$jenisluas">$listluasK - $listluasN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                            <input name="txtLuasAsalHtaam" type="text" id="txtLuasAsalHtaam" value="$luasasal" size="15" maxlength="15" onKeyUp="javascript:validateIC(this,this.value,'txtLuasAsalHtaam')"/>
                                                #else
  <select name="socJenisLuasHtaam" class="mediumselect" $readmode id="socJenisLuasHtaam" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Jenis Luas </option>
    
  
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
  
    <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
    
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



  </select>
  <input name="txtLuasAsalHtaam" type="text" id="txtLuasAsalHtaamUp" value="$luasasal" size="15" maxlength="15" onKeyUp="javascript:validateIC(this,this.value,'txtLuasAsalHtaam')" />
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">Luas (Hektar/MP)</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                              <input name="txtLuasHMpHtaam" type="text" id="txtLuasHMpHtaamUp" value="$luashmp" size="15" maxlength="15" onKeyUp="javascript:validateIC(this,this.value,'txtLuasHMpHtaam')" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">Nilai Tarikh Mati(RM) </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                              <input name="txtNilaiTarikhMatiHtaam" type="text" id="txtNilaiTarikhMatiHtaam" value="$nilai_Hta_mati" size="15" maxlength="8" onKeyUp="javascript:validateIC(this,this.value,'txtNilaiTarikhMatiHtaam')" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">Nilai Tarikh Permohonan (RM)</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                              <input name="txtNilaiTarikhMohonHtaa" type="text" id="txtNilaiTarikhMohonHtaa" value="$nilai_Hta_memohon" size="15" maxlength="8" onKeyUp="javascript:validateIC(this,this.value,'txtNilaiTarikhMohonHtaa')"  />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">Status Pemilikan </div></td>
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
                                              <td class="style38"><div align="right">Tanggungan</div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td><input name="txtTanggunganHtaam" type="text" id="txtTanggunganHtaam" value="$tanggungan" size="15" style="text-transform:uppercase;" onblur="uppercase()" /></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right">Jenis Tanah</div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td><label>#foreach($listtan in $listtanah)
                                                
                                                #if($jenistanah==$listtan.id)
                                                
                                                #set($listtanK=$listtan.kod)
                                                #set($listtanN=$listtan.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($jenistanah!="")
                                              <select name="socJenisTanahHtaam" class="autoselect" $readmode id="socJenisTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$jenistanah">$listtanK - $listtanN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                  #if($jenistanah!=$listtan.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
  <select name="socJenisTanahHtaam" class="autoselect" $readmode id="socJenisTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Jenis Tanah</option>
    
    
  
  
  
  
  
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                
	                               
                                              
  
  
  
  
  
    
    <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
    
    
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





  
  </select>
                                                #end </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38" valign="top"><div align="right">Catatan</div></td>
                                              <td width="1%" valign="top"><div align="right">:</div></td>
                                              <td><textarea name="txtCatatanHtaam" id="txtCatatanHtaam" cols="31" rows="5" style="text-transform:uppercase;" onblur="uppercase()">$catatan</textarea></td>
                                            </tr>
                                          </table></td>
                                        </tr>
                                      </table>
                                      </fieldset>
                                      
                                    #end</td>
                      </tr>
                                  
                                  
                                  #foreach($listamid in $listHTAid)

                                  <tr>
                                    <td>
                                    
                                  
                    

                    #if($show_htaa_update_table=="yes") 
                  
                    <fieldset><legend>HARTA TAK ALIH (ADA HAKMILIK)</legend>
                    <table width="100%" border="0">
                      <tr>
                      <input type="hidden" name="id_htaam" value="$listamid.idhta" />
                        <td valign="top" width="50%"><table width="100%" border="0">
                          <tr>
                            <td width="29%" class="style38"><div align="right"><span class="style41">*</span>Negeri</div></td>
                            <td width="1%">:</td>
                            <td width="70%"> #foreach($listnegpomo in $listnegeri)
                              
                              #if($listamid.negeri==$listnegpomo.id_Negeri)
                              
                              #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                              #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                              
                              
                              
                              #end 
                              #end
                              #if($listamid.negeri!="")
                              <select name="socNegeriHtaamUp" class="autoselect" $readmodenegeri  onchange="negerichangeup()"  style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listamid.negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            
                                <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                              </select>
                              #else
  <select name="socNegeriHtaamUp" class="autoselect" $readmodenegeri onchange="negerichangeup()" style="text-transform:uppercase;" onblur="uppercase()" >
    <option value="">Sila Pilih Negeri</option>
    
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                            
    <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
    
                                            
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
  </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38" ><div align="right"><span class="style41">*</span>Daerah</div></td>
                            <td>:</td>
                            <td>#foreach($listdaerah in $listdaerah)
                              
                              #if($listamid.daerah==$listdaerah.id)
                              
                              #set($listDaerahbyNegeriK=$listdaerah.kod)
                              #set($listDaerahbyNegeriN=$listdaerah.nama)
                              
                              
                              
                              #end 
                              #end
                              #if($listamid.daerah!="")
                              <select name="socDaerahHtaamUp" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchange()" id="socDaerahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($listamid.daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            
                                <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                              </select>
                              #else
  <select name="socDaerahHtaamUp" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchangeup()" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Daerah</option>
    
                                            
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
                                            
    <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
    
                                            
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

                                          
  </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38" ><div align="right"><span class="style41">*</span>Mukim</div></td>
                            <td>:</td>
                            <td>#foreach($listmukim in $listmukim)
                              
                              
                              #if($listamid.mukim==$listmukim.id)
                              
                              #set($listMukimbyDaerahK=$listmukim.kod)
                              #set($listMukimbyDaerahN=$listmukim.nama)
                              
                              
                              
                              #end 
                              #end
                              #if($listamid.mukim!="")
                              <select name="socMukimHtaamUp" class="autoselect" $readmodemukim id="socMukimHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listmukim)
                                 
                                  #if($listamid.mukim!=$listmukim.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>
                                
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                              </select>
                              #else
  <select name="socMukimHtaamUp" class="autoselect" $readmodemukim id="socMukimHtaamUp" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Mukim</option>
    
                                            
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
                                            
    <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
    
                                            
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                          
  </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38" ><div align="right"><span class="style41">*</span>Jenis Hakmilik </div></td>
                            <td>:</td>
                            <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                              
                              #if($listamid.jenishakmilik==$listjenishakmilik.id)
                              
                              #set($listjenishakmilikK=$listjenishakmilik.kod)
                              #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                              
                              
                              
                              #end 
                              #end
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
    <option value="">Sila Pilih Jenis Hak Milik</option>
    
                                            
  
  
  
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                 
                                
	                          
                                            
    <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
    
                                            
                                                 
                                              
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



                                          
  </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">No Hak Milik </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoHakmilikHtaamUp" type="text" id="txtNoHakmilikHtaam2" value="$listamid.noHakmilik" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">No PT / No Lot </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoPTHtaamUp" type="text" id="txtNoPTHtaamUp" value="$listamid.nopt" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()"  />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Bahagian Simati</div></td>
                            <td>:</td>
                            <td><input name="txtBahagianSimati1Up" type="text" id="txtBahagianSimati3" onkeyup="javascript:validateIC(this,this.value,'txtBahagianSimati1Up')" $readmode value="$listamid.basimati" size="5" />
                              /
                              <input name="txtBahagianSimati2Up" type="text" id="txtBahagianSimati4" $readmode onkeyup="javascript:validateIC(this,this.value,'txtBahagianSimati2Up')" value="$listamid.bbsimati" size="5" />
                            </td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">No Pajakan</div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoPajakanUp" type="text" id="txtNoPajakan2" value="$listamid.nopajakan" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()"  />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38" valign="top"><div align="right">No Pers</div></td>
                            <td>:</td>
                            <td valign="top"><label>
                              <input name="txtNoPersHtaamUp" type="text" id="txtNoPersHtaam2" value="$listamid.noperserahan" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()"  />
                            </label></td>
                          </tr>
                        </table></td>
                        <td valign="top" width="50%"><table width="100%" border="0">
                          <tr>
                            <td width="29%" class="style38"><div align="right">Kategory Tanah</div></td>
                            <td width="1%">:</td>
                            <td width="70%">#foreach($listkate in $listkategori)
                              
                              #if($listamid.kategori==$listkate.id)
                              
                              #set($listkategoriK=$listkate.kod)
                              #set($listkategoriN=$listkate.keterangan)
                              
                              
                              
                              #end 
                              #end
                              #if($listamid.kategori!="")
                              <select name="socKategoriTanahHtaamUp" class="autoselect" $readmode id="socKategoriTanahHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.kategori">$listkategoriK - $listkategoriN</option>
                                
                                            
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($listamid.kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                            
                                <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                          
                              </select>
                              #else
  <select name="socKategoriTanahHtaamUp" class="autoselect" $readmode id="socKategoriTanahHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Kategori Tanah</option>
    
  
                                            
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
                                            
  
    <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
    
  
                                            
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




                                          

  </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Jenis Luas</div></td>
                            <td>:</td>
                            <td>#foreach($listluashta in $listluas)
                              
                              #if($listamid.jenisluas==$listluashta.id)
                              
                              #set($listluasK=$listluashta.kod)
                              #set($listluasN=$listluashta.nama)
                              #end 
                              #end
                              #if($listamid.jenisluas!="")
                              <select name="socJenisLuasHtaamUpd" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.jenisluas">$listluasK - $listluasN</option>
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($listamid.jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                              </select>
                                            <input name="txtLuasAsalHtaamUpd" type="text" id="txtLuasAsalHtaamUpd" onkeyup="javascript:validateIC(this,this.value,'txtLuasAsalHtaamUpd')" value="$listamid.luasasal" size="15" maxlength="15" $readmode />
                              #else
  <select name="socJenisLuasHtaamUpd" class="mediumselect" $readmode id="socJenisLuasHtaamUpd" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Jenis Luas</option>
    
  
                                            
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
                                            
  
    <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
    
  
                                            
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                          

  </select>
  <input name="txtLuasAsalHtaamUpd" type="text" onkeyup="javascript:validateIC(this,this.value,'txtLuasAsalHtaamUpd')" value="$listamid.luasasal" size="15" maxlength="15"  $readmode/>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Luas (Hektar/MP) </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtLuasHMpHtaamUpd" type="text" id="txtLuasHMpHtaam2" onkeyup="javascript:validateIC(this,this.value,'txtLuasHMpHtaamUpd')" value="$listamid.luashmp" size="15" maxlength="15" $readmode />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Nilai Tarikh Mati</div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNilaiTarikhMatiHtaamUpd" onkeyup="javascript:validateIC(this,this.value,'txtNilaiTarikhMatiHtaamUpd')" type="text" id="txtNilaiTarikhMatiHtaam2" value="$listamid.nilai_Hta_mati" size="15" $readmode />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Nilai Tarikh Permohonan </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNilaiTarikhMohonHt" type="text" value="$listamid.nilai_Hta_memohon" size="15"  onkeyup="javascript:validateIC(this,this.value,'txtNilaiTarikhMohonHt')" $readmode />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Status Pemilikan</div></td>
                            <td>:</td>
                            <td>#foreach($listpemilik in $listpemilikan)
                              
                              #if($listamid.pemilikan==$listpemilik.id)
                              
                              #set($listpemilikK=$listpemilik.kod)
                              #set($listpemilikN=$listpemilik.keterangan)
                              
                              
                              
                              #end 
                              #end
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
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Tanggungan </div></td>
                            <td>:</td>
                            <td><input name="txtTanggunganHtaamUp" type="text" id="txtTanggunganHtaam2" value="$listamid.tanggungan" size="15" $readmode style="text-transform:uppercase;" onblur="uppercase()" /></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Jenis Tanah</div></td>
                            <td>:</td>
                            <td><label>#foreach($listtan in $listtanah)
                              
                              #if($listamid.jenistanah==$listtan.id)
                              
                              #set($listtanK=$listtan.kod)
                              #set($listtanN=$listtan.keterangan)
                              
                              
                              
                              #end 
                              #end
                              #if($listamid.jenistanah!="")
                              <select name="socJenisTanahHtaamUpd" class="autoselect" $readmode id="socJenisTanahHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
                                              <option value="$listamid.jenistanah">$listtanK - $listtanN</option>
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                  #if($listamid.jenistanah!=$listtan.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
                                
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                              </select>
                              #else
  <select name="socJenisTanahHtaamUpd" class="autoselect" $readmode id="socJenisTanahHtaam2" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Jenis Tanah</option>
    
                                            
    
  
  
  
  
  
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                
	                               
                                              
  
  
  
  
  

    
                                            
    <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
    
                                            
    
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





  
                                          
  </select>
                              #end </label></td>
                          </tr>
                          <tr>
                            <td class="style38" valign="top"><div align="right">Catatan</div></td>
                            <td valign="top">:</td>
                            <td><textarea name="txtCatatanHt" id="txtCatatanHt" value="$listamid.catatan" cols="31" rows="5" style="text-transform:uppercase;" onblur="uppercase()" >$listamid.catatan</textarea>
                            </td>
                          </tr>
                        </table></td>
                      </tr>
                    </table>
                    </fieldset>
                 
#end                                    </td>
                      </tr>
                                  
                                  #end
                                  
                                  #if($show_button=="yes")
                                  <tr>
                                    <td><table width="100%" border="0" align="center">
                                      <tr>
                                      
                    
                                        <td align="center">
                                        
                                        #if($show_simpan_add_htaam=="yes")
                                          <input type="button" name="tambahpenghutang2" id="tambahpenghutang2"$readmode value="Simpan" onkeypress="setSelected(1,0,0,0);add_Htaam()" onclick="setSelected(1,0,0,0);add_Htaam()"/>
                                        #end
                                        
                                        #if($show_batal_add_htaam=="yes")
                                          <input type="reset" name="cmdSimpan6" id="cmdSimpan6" value="Kosongkan" onclick="cancelwaris()"/>
                                       
                                        #end
                                         #if($show_kemaskini_htaam=="yes")
                                            <input type="submit" name="button8" id="button8" value="Kemaskini" onkeypress="setSelected(1,0,0,0);edit_Htaam()" onclick="setSelected(1,0,0,0);edit_Htaam()" />
                                            #end
                                         #if($show_save_update_htaam=="yes")
                                            <input type="button" name="button9" id="button9" value="Simpan"  onclick="setSelected(1,0,0,0);save_Htaam()"/>
                                       #end
                                        #if($show_batal_update_htaam=="yes")
                                          <input type="submit" name="button10" id="button10" value="Batal" onkeypress="setSelected(1,0,0,0);batal_Htaam()" onclick="setSelected(1,0,0,0);batal_Htaam()" />
                                       #end
                                       #if($show_hapus_htaam=="yes")
                                          <input type="submit" name="cmdCetak7" id="cmdCetak7" value="Hapus" setSelected(1,0,0,0);hapus_Htaam()" onclick="setSelected(1,0,0,0);hapus_Htaam()"/>
                                     #end
                                     
                                        <input type="submit" name="cmdKembali7" id="cmdKembali7" value="Kembali" onclick="kembali_Simati()" /></td>
                                      </tr>
                                    </table></td>
                                  </tr>
                                  
                                  #end
                                 
                                  
                                  
                                  <tr>
                                    <td>
                                    
                                  
                                    <fieldset><legend>SENARAI HARTA TAK ALIH (ADA HAKMILIK)</legend>
                                      <table width="100%">
                                      <tr>
                                        <td align="left"><div align="center">
                                         
                                          <div align="left">
                                            <input type="submit" name="button" id="button" value="Tambah" onclick="nktambah()"/>
                                            
                                          </div></td>
                                      </tr>
                                    </table>
                                    
                                    
                                    #if($listHTA.size()!=0 )    
                                    <table width="100%">
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <td width="10%"><div align="center">ID HTA</div></td>
                                        <td width="20%"><div align="center">NEGERI</div></td>
                                        <td width="20%"><div align="center">DAERAH</div></td>
                                        <td width="20%"><div align="center">MUKIM</div></td>
                                        <td width="10%"><div align="center">NO HAK MILIK</div></td>
                                        <td width="10%"><div align="center">NO PT/NO LOT</div></td>
                                        <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      
                                      
                                      
                                      #set($plko=0)
                                      
                                      #foreach($listam in $listHTA)
                                      #set($plko=$plko+1)
                                      #if($plko%2!=0)
                                      
  <tr bgcolor="white">
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$listam.idhta </a></div></td>
    #if($listam.negeri=="")
    
    	#set($nama_negeri="")
    #else
    	#foreach($ln in $listnegeri)                                                                      
    		#if($ln.id_Negeri==$listam.negeri)
    			#set($nama_negeri=$ln.nama_Negeri)
    
    		#end
    	#end
    
    #end
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nama_negeri</div></td>
    #if($listam.daerah=="")
    
    #set($nama_daerah="")
    #else
    #foreach($ld in $listdaerah)                                                                      
    #if($ld.id==$listam.daerah)
    #set($nama_daerah=$ld.nama)
    
    #end
    #end
    
    #end
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nama_daerah</div></td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nama_mukim</div></td>
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
    #else
    <td class="row1"> </td>
    #end </tr>
                                      #else
  <tr class="table_header">
    <td class="row2"><div align="center">$plko</div></td>
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$listam.idhta</a></div></td>
    #if($listam.negeri=="")
    
    #set($nama_negeri="")
    #else
    #foreach($ln in $listnegeri)                                                                      
    #if($ln.id_Negeri==$listam.negeri)
    #set($nama_negeri=$ln.nama_Negeri)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nama_negeri</div></td>
    #if($listam.daerah=="")
    
    #set($nama_daerah="")
    #else
    #foreach($ld in $listdaerah)                                                                      
    #if($ld.id==$listam.daerah)
    #set($nama_daerah=$ld.nama)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nama_daerah</div></td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nama_mukim</div></td>
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
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
                                        <td width="10%"><div align="center">ID HTA</div></td>
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
                <p align="right">CL – 01 – 38</p>
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
  </tr>
</table>
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
function negerichange(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="negerichange";
		document.f1.action="";
		document.f1.submit();
}
function negerichangeup(){
		
		document.f1.command.value="Htaam";
		document.f1.mode.value="negerichangeup";
		document.f1.action="";
		document.f1.submit();
}
function daerahchange(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="daerahchange";
		
		document.f1.action="";
		document.f1.submit();
}
function daerahchangeup(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="daerahchangeup";
		
		document.f1.action="";
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
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) 
    {
            if(document.f1.socNegeriHtaam.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		document.f1.socNegeriHtaam.focus(); 
			return; 
            }
            
            if(document.f1.socDaerahHtaam.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.f1.socDaerahHtaam.focus(); 
			return; 
            }
            if(document.f1.socMukimHtaam.value == ""){
			alert('Sila pilih " Mukim " terlebih dahulu.');
	  		document.f1.socMukimHtaam.focus(); 
			return; 
            }
            if(document.f1.socJenisHakmilikHtaam.value == ""){
			alert('Sila pilih " Jenis Hak Milik " terlebih dahulu.');
	  		document.f1.socJenisHakmilikHtaam.focus(); 
			return; 
            }
            document.f1.command.value="Htaam";
		document.f1.mode.value="masukkan";
		
		document.f1.action="";
		document.f1.submit();
		}
		
}
function edit_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="kemaskiniHtaam";
		
		document.f1.action="";
		document.f1.submit();
}

function save_Htaam(){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) 
    {
            if(document.f1.socNegeriHtaamUp.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		
			return; 
            } 
                    
            if(document.f1.socDaerahHtaamUp.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.f1.socDaerahHtaamUp.focus(); 
			return; 
            }
            if(document.f1.socMukimHtaamUp.value == ""){
			alert('Sila pilih " Mukim " terlebih dahulu.');
	  		document.f1.socMukimHtaamUp.focus(); 
			return; 
            }
            if(document.f1.socJenisHakmilikHtaamUp.value == "")
            {
			alert('Sila pilih " Jenis hak milik" terlebih dahulu.');
	  		document.f1.socJenisHakmilikHtaamUp.focus(); 
			return; 
            }
        document.f1.command.value="Htaam";
		document.f1.mode.value="simpanHtaam";		
		document.f1.action="";
		document.f1.submit();
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

</script>

<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>



</body>
</html>
