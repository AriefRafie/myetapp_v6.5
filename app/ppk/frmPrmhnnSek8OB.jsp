

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
.style36 {font-size: 12}
.style38 {font-size: 10px}
.style40 {color: #FF0000}
.style41 {color: #000000}
.style42 {color: #0000FF}
-->
</style>
</head>

<body>
<form id="f1" name="f1" method="post" action="">
  


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
<td><fieldset>
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
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$list.keterangan</span></div></td>
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
     
        <div id="TabbedPanels2" class="TabbedPanels">
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
            <div class="TabbedPanelsContentVisible"></div>
            
            <div class="TabbedPanelsContentVisible">
              <div id="TabbedPanels3" class="TabbedPanels">
               
             
              </div>
            </div>
            
            <div class="TabbedPanelsContentVisible">
            
            
            </div>
            <div class="TabbedPanelsContentVisible">
            
           <table width="100%" border="0">
                        
                                     #if($nk_update_penting=="yes")
                                     
                                       #foreach($listob in $listPentingbyIDOB)
     <tr>
                                         <td>
                                          <fieldset>
                                          <legend>MAKLUMAT ORANG BERKEPENTINGAN</legend>
                                          
                                          <table width="100%" border="0">
                              <tr>
                               <td width="50%"><table width="100%">
                                  <input type="hidden" name="txtIdSimatiPenting" value="$listob.idSimati" >   
                                  <input type="hidden" name="txtIdOb" value="$listob.idOb" > 
                                  id pemohon:::::<input type="text" name="id_Pemohon" value="$listob.id_Pemohon" >      
                                  <tr>
                                  <td width="29%" ><div align="right"><span class="style38">No KP Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><label>
                                   
                                     <input name="txtNoKPBaru1PentingU" type="text" id="txtNoKPBaru1PentingU" style="width: 50px;" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru2PentingU')" value="$listob.nokpbaru1" size="7" maxlength="6" $readmode/>
                                     -<input name="txtNoKPBaru2PentingU" type="text" id="txtNoKPBaru2PentingU" style="width: 20px;" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru3PentingU')" value="$listob.nokpbaru2" size="1" maxlength="2" $readmode/>
                                     -
                                     <input name="txtNoKPBaru3PentingU" type="text" id="txtNoKPBaru3PentingU"  style="width: 40px;" onkeyup="javascript:validateIC(this,this.value,'txtNoKPBaru3PentingU')" value="$listob.nokpbaru3" size="5" maxlength="4" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td ><div align="right"><span class="style38">No KP Lama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPentingU" type="text" id="textfield4" value="$listob.nokplama" style="text-transform:uppercase;" onblur="uppercase()" size="34" maxlength="8"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td valign="top"><div align="right"><span class="style38">Lain-lain KP</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                                  
                                   
									
									 <select name="socJenisKPLainPentingU"  class="mediumselect" $readmode id="socJenisKPLainPentingU" style="text-transform:uppercase;" onblur="uppercase()">
								   #if($listob.jeniskp=="4")
	                                 
                                       <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
									   <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                     
                                      
	                              
	                               #elseif($listob.jeniskp=="6")
	                                
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                       <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                     
                                      
	                              
								   #elseif($listob.jeniskp=="5")
	                               		<option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      
	                               #else
	                                 
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      
	                               #end
                                    
                                  
                                    </select>
									 <label></label></td>
                                </tr>
                                  <tr>
                                    <td><div align="right"><span class="style38"> No KP Lain</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><span class="style36">
                                      <input name="txtNoKPLainPentingU" type="text" id="textfield5" style="text-transform:uppercase;" onblur="uppercase()" value="$listob.nokplain" size="34" maxlength="9" $readmode    />
                                    </span></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><span class="style40">*</span><span class="style38">Nama OB</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPentingU" type="text" id="txtNamaOBPentingU" style="text-transform:uppercase;" onblur="uppercase()" value="$listob.nama_Ob" size="34"  $readmode/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                  <td valign="top"><div align="right"><span class="style38">Status OB</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                              
                                    
                                       <select name="socStatusOBU"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
									
								   #if($listob.status_Ob=="1")
	                                 
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                              
	                               #elseif($listob.status_Ob=="2")
	                                
	                                  <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                     <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                    
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                              
								   #elseif($listob.status_Ob=="3")
	                               
	                                 <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                    
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                     
                                   #elseif($listob.status_Ob=="4")
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
                                   <tr>
                                          <td><div align="right"><span class="style38">Taraf Kepentingan</span></div></td>
                                         
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                         #foreach($listtar in $listtaraf)
                                 
                                 #if($listob.taraf==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
	                              
	                         
                                   
                                 #end    
	                               #end
                                         
                                       
	                                 <td>
	                               
	                              #if($listob.taraf!="")
                                  <select name="socTarafKepentinganPentingU" class="largeselect" $readmode id="socTarafKepentinganPentingU" style="text-transform:uppercase;" onblur="uppercase()">
                                   <option value="$listob.taraf" style="text-transform:uppercase;" onblur="uppercase()">$tarafkepentinganP - $tarafkepentinganketeranganP</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                  #if($listob.taraf!=$listtar.id_Tarafkptg)
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
                                  <tr>
                                  <td><div align="right"><span class="style38">Jantina</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                    <select name="socJantinaPentingU" id="select2" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($listob.jantina=="1")
	                               
                                      
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                      
	                               #elseif($listob.jantina=="2")
	                               
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
                                
                                 <tr>
                                  <td><div align="right"><span class="style38">Agama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                    
                                    
                                    <select name="socAgamaPentingU" id="select3" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($listob.agama=="1")
	                               
	                               
                                      
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      
	                               #elseif($listob.agama=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      
	                               #else
	                               
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Agama</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      
	                               #end
                                      
                                    
                                    </select>
                                     </label></td>
                                </tr>
                                      
                                         <tr>
                                  <td><div align="right"><span class="style38">Warganegara</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    
                                   
                                    <select name="socWarganegaraPentingU" id="select4" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($listob.warga=="1")
	                               
                                      
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #elseif($listob.warga=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #elseif($listob.warga=="3")
	                               
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
                                     </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right" class="style38">Tarikh Lahir</div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txdTarikhLahirPentingU" type="text" style="text-transform:uppercase;"  onblur="return ValidateForm1()" id="txdTarikhLahirPentingU" size="15" maxlength="10" $readmode value="$listob.dob"/>
                                    <a href="javascript:displayDatePicker('txdTarikhLahirPentingU',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/> </a></label></td>
                                </tr> 
                                <tr>
                                  <td ><div align="right"><span class="style38">Umur</span></div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><span class="style36">
                                    <input name="txtUmurPentingU" type="text" id="textfield"  value="$listob.umur" size="3" maxlength="3" $readmode style="text-transform:uppercase;" onblur="CheckumurU()"  onkeyup="javascript:validateIC(this,this.value,'txtUmurPentingU')"/>
                                  </span></td>
                                </tr>
                                <tr>
                                    <td><div align="right"><span class="style38">No Surat Beranak</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNoSuratBeranakPentingU" type="text" id="txtNoSuratBeranakPenting" style="text-transform:uppercase;" onblur="uppercase()" value="$listob.noberanak" size="34" maxlength="8" $readmode />
                                    </label></td>
                                  </tr>
                                  
                                </table></td>
                                <td width="50%" valign="top"><table width="100%" border="0">
                                  <tr>
                                  <td class="style38" width="29%"><div align="right" class="style38">Alamat </div></td>
                                  <td width="1%"><div align="right" class="style38">:</div></td>
                                  <td width="70%"><label>
 									
                                    <input name="txtAlamatTerakhir1PentingU" type="text" id="txtAlamatTerakhir1PentingU" value="$listob.alamat1" size="34"  $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2PentingU" type="text" id="txtAlamatTerakhir2PentingU" value="$listob.alamat2" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><input name="txtAlamatTerakhir3PentingU" type="text" id="txtAlamatTerakhir3PentingU" value="$listob.alamat3" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()" /></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38">Poskod</div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtPoskodPentingU" type="text" id="txtPoskodPentingU" value="$listob.poskod" size="15" maxlength="5" $readmode style="text-transform:uppercase;" onblur="uppercase()" onKeyUp="javascript:validateIC(this,this.value,'txtPoskodPentingU')" />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Bandar</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><label>
                                    <input name="txtBandarPentingU" type="text" id="txtBandarPentingU" value="$listob.bandar" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                                  </label></td>
                                </tr>
                                        <tr>
                                  <td class="style38"><div align="right" class="style38">Negeri</div></td>
                                   
                                    <td><div align="right" class="style38">:</div></td>
                                    #foreach($listnegpomo in $listnegeri)
                                 
                                    #if($listob.idnegeri==$listnegpomo.id_Negeri)
                                    
	                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
	                                 #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                   
                                   
                                   
                                    #end 
                                    #end  
                                  
	                               <td>
	                               
	                              #if($listob.idnegeri!="")
                                  <select name="socNegeriPentingU" class="autoselect" $readmode id="socNegeriPentingU" style="text-transform:uppercase;" onblur="uppercase()">
                                   <option value="$listob.idnegeri" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listob.idnegeri!=$listnegpomo.id_Negeri)
                                    
	                               <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socNegeriPentingU" class="autoselect" $readmode id="socNegeriPentingU" style="text-transform:uppercase;" onblur="uppercase()">
                                   <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end                                    </tr>
                                        
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan</div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtCatatanPentingU" cols="31"  rows="3"  $readmode id="txtCatatanPentingU" style="text-transform:uppercase;" onblur="uppercase()" >$listob.catatan</textarea></td>
                                      </tr>
                                </table></td>
                              </tr>
                            </table>
                                         
                                          </fieldset>
                                         
                                         
                                         </td>
                                       </tr>
                                       #end
                                       
                                  #end     
                                       
                                       
                                 #if($nk_tambah_penting=="yes")
                                       <tr>
                            <td width="100%">
                            
                            <fieldset>
                            <legend>MAKLUMAT ORANG BERKEPENTINGAN</legend>
                            
                            <table width="100%" border="0">
                              <tr>
                                <td width="50%"><table width="100%">
                               <input type="hidden" name="txtIdSimatiPenting" value="$id_Simati" >        
                                  <tr>
                                  <td width="29%" ><div align="right"><span class="style38">No KP Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><label>
                                   
                                     <input name="txtNoKPBaru1Penting" id="txtNoKPBaru1Penting" style="width: 50px;" type="text" $readmode size="7" maxlength="6" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru2Penting')"/>
                                     -<input name="txtNoKPBaru2Penting" id="txtNoKPBaru2Penting" style="width: 20px;" type="text" $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru3Penting')"/>
                                     -<input name="txtNoKPBaru3Penting" id="txtNoKPBaru3Penting"  style="width: 40px;" type="text" $readmodesize="5" maxlength="4" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru3Penting')"/>
                                    
                                    
                                  </label></td>
                                </tr>
                                <tr>
                                  <td ><div align="right"><span class="style38">No KP Lama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting" type="text" id="textfield4" size="34" maxlength="8" $readmode style="text-transform:uppercase;" onblur="uppercase()"/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td valign="top"><div align="right"><span class="style38">Lain-lain KP</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                                  
                                   
									
									 <select name="socJenisKPLainPenting"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
								   #if($jenisKp=="5")
	                                 
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      
	                              
	                               #elseif($jenisKp=="6")
	                                
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      
	                              
								   #elseif($jenisKp=="4")
	                               
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      
	                               #else
	                                 
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                      <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                      <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                      
	                               #end
                                    
                                  
                                    </select>
									 <label></label></td>
                                </tr>
                                  <tr>
                                    <td><div align="right"><span class="style38"> No KP Lain</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><span class="style36">
                                      <input name="txtNoKPLainPenting" type="text" id="textfield5" size="34" maxlength="8" $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                                    </span></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><span class="style40">*</span><span class="style38">Nama OB</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                                    </label></td>
                                  </tr>
                                  <tr>
                                  <td valign="top"><div align="right"><span class="style38">Status OB</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
                              
                                    
                                       <select name="socStatusOB"  class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
									
								   #if($statusOB=="1")
	                                 
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                              
	                               #elseif($statusOB=="2")
	                                
	                                  <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>                             
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                      
	                              
								   #elseif($statusOB=="3")
	                               
	                                 <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                                    
                                      <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                                     
                                   #elseif($statusOB=="4")
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
                                   <tr>
                                          <td><div align="right"><span class="style38">Taraf Kepentingan</span></div></td>
                                         
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                         #foreach($listtar in $listtaraf)
                                 
                                 #if($taraf==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
	                              
	                         
                                   
                                 #end    
	                               #end
                                         
                                       
	                                 <td>
	                               
	                              #if($taraf!="")
                                  <select name="socTarafKepentinganPenting" class="largeselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                   <option value="$taraf" style="text-transform:uppercase;" onblur="uppercase()">$tarafkepentinganP - $tarafkepentinganketeranganP</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                  #if($taraf!=$listtar.id_Tarafkptg)
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
                                  <tr>
                                  <td><div align="right"><span class="style38">Jantina</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                    <select name="socJantinaPenting" id="select2" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($jantina=="1")
	                               
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                      
	                               #elseif($jantina=="1")
	                               
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
                                
                                 <tr>
                                  <td><div align="right"><span class="style38">Agama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                    
                                    
                                    <select name="socAgamaPenting" id="select3" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($agama=="1")
	                               
	                               
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      
	                               #elseif($agama=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      
	                               #else
	                               
                                      <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Agama</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                      
	                               #end
                                      
                                    
                                    </select>
                                     </label></td>
                                </tr>
                                      
                                         <tr>
                                  <td><div align="right"><span class="style38">Warganegara</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    
                                   
                                    <select name="socWarganegaraPenting" id="select4" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                      
                                   #if($warga=="1")
	                               
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #elseif($warga=="2")
	                               
                                      <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                      <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                      <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                      
	                               #elseif($warga=="3")
	                               
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
                                     </label></td>
                                </tr> 
                                <tr>
                                  <td ><div align="right" class="style38">Tarikh Lahir</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td>
                                  <input name="txdTarikhLahirPenting" type="text" id="txdTarikhMatiSimati3" size="15" maxlength="10" $readmode  /> <a href="javascript:displayDatePicker('txdTarikhLahirPenting',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/> </td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Umur</span></div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><span class="style36">
                                    <input name="txtUmurPenting" type="text" id="textfield2"  size="3" maxlength="3" $readmode style="text-transform:uppercase;" onblur="Checkumur()" onKeyUp="javascript:validateIC(this,this.value,'txtUmurPenting')"/>
                                  </span></td>
                                </tr>
                                <tr>
                                    <td><div align="right"><span class="style38">No Surat Beranak</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNoSuratBeranakPenting" type="text" id="txtNoSuratBeranakPenting" size="34" maxlength="8" $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                                    </label></td>
                                  </tr>
                                  
                                </table></td>
                                <td width="50%" valign="top"><table width="100%" border="0">
                                  <tr>
                                  <td class="style38" width="29%"><div align="right" class="style38">Alamat</div></td>
                                  <td width="1%"><div align="right" class="style38">:</div></td>
                                  <td width="70%"><label>
 									
                                    <input name="txtAlamatTerakhir1Penting" type="text" id="txtAlamatTerakhir1Penting" size="34"  $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2Penting" type="text" id="txtAlamatTerakhir2Penting" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><input name="txtAlamatTerakhir3Penting" type="text" id="txtAlamatTerakhir3Penting" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()"/></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38">Poskod</div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtPoskodPenting" type="text" id="txtPoskodPenting" style="text-transform:uppercase;" onblur="uppercase()" size="15" maxlength="5" $readmode onKeyUp="javascript:validateIC(this,this.value,'txtPoskodPenting')"/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Bandar</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><label>
                                    <input name="txtBandarPenting" type="text" id="txtBandarPenting" size="34" $readmode style="text-transform:uppercase;" onblur="uppercase()" />
                                  </label></td>
                                </tr>
                                        <tr>
                                  <td class="style38"><div align="right" class="style38">Negeri</div></td>
                                   
                                    <td><div align="right" class="style38">:</div></td>
                                    #foreach($listnegpomo in $listnegeri)
                                 
                                    #if($negeri==$listnegpomo.id_Negeri)
                                    
	                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
	                                 #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                   
                                   
                                   
                                    #end 
                                    #end  
                                  
	                               <td>
	                               
	                              #if($negeri!="")
                                  <select name="socNegeriPenting" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                   <option value="$negeri" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socNegeriPenting" class="autoselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
                                   <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end                                    </tr>
                                        
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan</div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtCatatanPenting" cols="31"  rows="3"  $readmode style="text-transform:uppercase;" onblur="uppercase()" ></textarea></td>
                                      </tr>
                                </table></td>
                              </tr>
                            </table>
                            </fieldset>                            </td>
                          </tr>
                                       
                               #end        
                                       
            #if($nk_button_penting=="yes") 
                         
                          <tr>
                            <td>  <table width="100%" border="0" align="center">
                                  <tr>
                                  <td align="center">
                                  #if($buttonpenting=="tambah")
                              
                                  <input type="button" name="tambahpenting" id="tambahpenting2" value="Simpan" onclick="setSelected(0,3,0,0);tambah_simpan_penting()"/>								
                              <input type="button" name="batalpenting" id="cmdSimpan3" value="Batal" onclick="setSelected(0,3,0,0);cancelwaris()"/>                                 
                                  #else 
                                  <input type="button" name="tambahpenting" id="tambahpenting3" value="$buttonpenting" onclick="setSelected(0,3,0,0);tambah_penting()"/>
                                    #if($buttonpenting=="Simpan")
                                      <input type="button" name="batalpentingupdate" id="cmdSimpan3" value="Batal" onclick="setSelected(0,3,0,0);batalpenting()"/>
                                      #end
                                     <input type="submit" name="hapuspenting" id="hapuspenting" value="Hapus"  onclick="setSelected(0,3,0,0);hapus_penting()" />
                                     
                                  #end
                                     
                                      <input type="submit" name="cmdKembali3" id="cmdKembali3" value="Kembali"  onclick="kembali_simati()" />
                                    
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
                                  <td width="647"><label>
                                    <input type="submit" name="cmdPapar" id="cmdPapar" value="Tambah"  onclick="setSelected(0,3,0,0);tambah_penting_baru()"/>
                                  </label>
                                    <label></label></td>
                                </tr>
                                
                               
          
      
                                
                                
                                <tr>
                                  <td>
                                 
                                  #if($listPenting.size()!=0)
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="40%"><div align="center">NAMA OB</div></td>
                                      <td width="25%"><div align="center">NO KP BARU</div></td>
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
                                       <div align="center" class="style42" style="text-transform:uppercase;" onblur="uppercase()"> $listpenting.nama_Ob</div>   
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
                                       <div align="center" class="style42" style="text-transform:uppercase;" onblur="uppercase()"> $listpenting.nama_Ob</div>   
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
                                      <td width="25%"><div align="center">NO KP BARU</div></td>
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
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.f1.mode.value = "hapus_penting";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.submit();
	}
}
function batalpenting(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.f1.mode.value = "batal_update";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.submit();
	}
}
function tambah_simpan_penting(){


if (document.f1.txtNamaOBPenting.value=="")
 {
		alert("Sila masukkan nama orang bekepentingan");
		txtNamaOBPenting.focus();		 
	} 
	
	else if (document.f1.txtNoKPBaru1Penting.value=="" && document.f1.txtNoKPBaru2Penting.value=="" && document.f1.txtNoKPBaru3Penting.value=="" && document.f1.txtNoKPLamaPenting.value=="" && document.f1.socJenisKPLainPenting.value=="" && document.f1.txtNoKPLainPenting.value=="") {
		alert("Sila masukkan salah satu nombor kad pengenalan orang bekepentingan ");
		txtNoKPBaru1Penting.focus();
		
		}
		
		
	else if ((document.f1.socJenisKPLainPenting.value!="" && document.f1.txtNoKPLainPenting.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan lain orang bekepentingan ");
		txtNoKPLainPenting.focus();
		return; 
	 }
	 else if ((document.f1.socJenisKPLainPenting.value=="" && document.f1.txtNoKPLainPenting.value!=""))
	 {
	 	alert("Sila pilih jenis kad pengenalan lain orang bekepentingan ");
		socJenisKPLainPenting.focus();
		return; 
	 }
	 
	 else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru1Penting.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru1Penting.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru2Penting.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru2Penting.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Penting.value!="" || document.f1.txtNoKPBaru2Penting.value!="" || document.f1.txtNoKPBaru3Penting.value!="") && (document.f1.txtNoKPBaru3Penting.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru3Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru1Penting.value!=""  && document.f1.txtNoKPBaru1Penting.value.length < 6 ) {
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru1Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Penting.value!="" && document.f1.txtNoKPBaru2Penting.value.length < 2 ) {
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru2Penting.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Penting.value!="" && document.f1.txtNoKPBaru3Penting.value.length < 4) {
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru3Penting.focus();
		return; 
	}
	
	else if (document.f1.txtPoskodPenting.value != "" && document.f1.txtPoskodPenting.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		txtPoskodPenting.focus();
		return; 
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
	if( document.f1.tambahpenting.value == "Simpan" ) 
	{
   
	

if (document.f1.txtNamaOBPentingU.value=="")
 {
		alert("Sila masukkan nama orang bekepentingan");
		txtNamaOBPentingU.focus();		 
	} 
	
	else if (document.f1.txtNoKPBaru1PentingU.value=="" && document.f1.txtNoKPBaru2PentingU.value=="" && document.f1.txtNoKPBaru3PentingU.value=="" && document.f1.txtNoKPLamaPentingU.value=="" && document.f1.socJenisKPLainPentingU.value=="" && document.f1.txtNoKPLainPentingU.value=="") {
		alert("Sila masukkan salah satu nombor kad pengenalan orang bekepentingan ");
		txtNoKPBaru1PentingU.focus();
		
		}
		
		
	else if ((document.f1.socJenisKPLainPentingU.value!="" && document.f1.txtNoKPLainPentingU.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan lain orang bekepentingan ");
		txtNoKPLainPentingU.focus();
		return; 
	 }
	 else if ((document.f1.socJenisKPLainPentingU.value=="" && document.f1.txtNoKPLainPentingU.value!=""))
	 {
	 	alert("Sila pilih jenis kad pengenalan lain orang bekepentingan ");
		socJenisKPLainPentingU.focus();
		return; 
	 }
	 
	 else if ((document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru1PentingU.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru1PentingU.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru2PentingU.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru2PentingU.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1PentingU.value!="" || document.f1.txtNoKPBaru2PentingU.value!="" || document.f1.txtNoKPBaru3PentingU.value!="") && (document.f1.txtNoKPBaru3PentingU.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru3PentingU.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru1PentingU.value!=""  && document.f1.txtNoKPBaru1PentingU.value.length < 6 ) {
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru1PentingU.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2PentingU.value!="" && document.f1.txtNoKPBaru2PentingU.value.length < 2 ) {
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru2PentingU.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3PentingU.value!="" && document.f1.txtNoKPBaru3PentingU.value.length < 4) {
		alert("Sila masukkan nombor kad pengenalan orang bekepentingan sepenuhnya");
		txtNoKPBaru3PentingU.focus();
		return; 
	}
	
	else if (document.f1.txtPoskodPentingU.value != "" && document.f1.txtPoskodPentingU.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		txtPoskodPentingU.focus();
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
	}
	

   
   
	
	}

	if( document.f1.tambahpenting.value == "Kemaskini" ) 
	{
	document.f1.mode.value = "KemaskiniPenting";
	document.f1.command.value = "Penting";	
	document.f1.action = "";
	document.f1.submit();
	
	}
}

function edit_item(idOb) 
{

document.f1.action = "";
	document.f1.mode.value = "GetPenting";
	document.f1.command.value = "Penting";
	document.f1.idOb.value = idOb;
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
document.f1.reset();
document.f1.txtNoKPBaru1Waris.focus();
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


</script>



<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>



</body>
</html>
