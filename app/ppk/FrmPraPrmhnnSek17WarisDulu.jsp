
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
.style43 {font-family: Arial, Helvetica, sans-serif; color: #0000FF; }
.style44 {font-size: 10px; color: #FF0000; }
.style46 {
	color: #FF0000;
	font-size: 9px;
	font-style: italic;
}
.style69 {
	font-size: 9px;
	font-style: italic;
}
.style72 {color: #000000}
.style73 {
	color: #0000FF;
	font-style: italic;
	font-size: 9px;
}
.style74 {font-size: 9px}
-->
</style>
</head>
<!-- onload="submitForm()" -->
<body onload="submitForm();" >
<form id="form1" name="f1" method="post" action="">
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<!--
<a href="#" onclick="Effect.ScrollTo('txtNamaOBWaris').focus(); return false;" >
Click me</a>
-->
<input type="hidden" name="v_tab" id="v_tab" value="" />

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
  #end


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

#foreach($PermohonanSebelum in $listGetPermohonanSebelum)
#set ($id_Permohonan_terdahulu = $PermohonanSebelum.id_Permohonan)
#set ($no_subjaket = $PermohonanSebelum.no_subjaket)
#end

 id_Permohonan_terdahulu::<input name="id_Permohonan_terdahulu" type="text"  value="$id_Permohonan_terdahulu"/>
 no_subjaket::<input name="no_subjaket" type="text"  value="$no_subjaket"/>

#foreach($list in $View)
 #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
    <input name="idPemohon" type="hidden"  value="$idPemohon"/>
    <input name="idSimati" type="hidden"  value="$idSimati"/>
    
    #set($id_Simati=$idSimati)
    <input name="idtemp" type="hidden"  value="$id"/>
   
 
<input name="id_Suburusanstatus" type="hidden"  value="$list.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$list.id_Suburusanstatusfail"/>

id_permohonansimati :: <input name="id_Permohonansimati" type="text"  value="$list.id_Permohonansimati"/>

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
                              
                              #if($listidnegeri=="" || $listidnegeri=="0" )
                              
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
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listtarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" id="txdTarikhMohon" value="$listtarikhMohon" readonly="true"/>
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
            <div class="TabbedPanelsContentVisible"></div>
            
            <div class="TabbedPanelsContentVisible">
             <!--
              <div id="TabbedPanels3" class="TabbedPanelsContentVisible">
              
                <div class="TabbedPanelsContentGroup">
               
                  <div class="TabbedPanelsContentVisible" ></div>
                   <div class="TabbedPanelsContentVisible" ></div>
                  
                </div>
              </div>
               -->
            </div>
            
            <div class="TabbedPanelsContentVisible">
             
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
                        
                          #set($alamat1Surat="")
				    #set($alamat2Surat="")
				    #set($alamat3Surat="")
				    #set($poskodSurat="")				    
		    	    #set($negeriSurat="")
                    #set($bandarSurat="")	
                        
                        #end
            
          
            
            #if($addnew=="yes")
			
		  	    		
					#set($listpenting = "")
				    #set($namaOB = "")	    
				    #set($nokpbaru1 = "")
				    #set($nokpbaru2 = "")
				    #set($nokpbaru3 = "")
				    #set($nokpsaksi = "")
				    #set($jenisKp = "")
				    #set($nokplain = "")
				    #set($statusOB = "")
				    #set($notel = "")
				    #set($taraf ="")	
		         	#set($bandar ="")	
		         	#set($statusWaris = "")
		            #set($jantina = "")
				    #set($agama = "") 
					#set($warga = "")			    
		    	    #set($umur = "")					   	
				    #set($dob = "")	    
				    #set($noberanak = "")
				    #set($alamat1 = "")
				    #set($alamat2 = "")
				    #set($alamat3 = "")
				    #set($poskod = "")					    
		    	    #set($negeri = "")			  	
		            #set($catatan = "")			            
		            #set($hp = "")
		            #set($tarikhmati = "")
		            #set($nokpwaris = "")
		            #set($checkmati = "0")		            
		            #set($waktumatiwaris = "")
		            #set($saudara = "")
                    
                     #set($alamat1Surat="")
				    #set($alamat2Surat="")
				    #set($alamat3Surat="")
				    #set($poskodSurat="")				    
		    	    #set($negeriSurat="")
                    #set($bandarSurat="")	
		     
            #end
            <table width="100%">
            
            
            
            
            
                               #if($show_lapisan_berikut=="")                             
                               #if($show_waris_update=="yes")
                               
                               
                               
                               
                               #foreach($lwu in $listWarisUpdate)
  <tr>
    <td width="100%">
    <fieldset><legend>MAKLUMAT WARIS</legend>
    
    <input type="hidden" name="id_Pemohon" value="$lwu.id_Pemohon" />
    
    <table width="100%" border="0">
      <tr>
        <td width="50%" valign="top">
        
        
        
        <table width="100%">
          <input type="hidden" name="idwarisup" value="$lwu.idwaris" />
       
          #set($idwarisup=$lwu.idwaris)
          <input type="hidden" name="txtIdSimatiWaris" value="$lwu.idSimati" />
          <tr>
            <td width="29%"><div align="right"><span class="style38">No KP Baru</span></div></td>
            <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td width="70%" class="style36">
            
            #if($lwu.nokpbaru1 == "")
            
             #set($nokpbaru_d = "-")
            
            #else
            
            #set($nokpbaru_d = "$lwu.nokpbaru1 - $lwu.nokpbaru2 - $lwu.nokpbaru3")
            
            #end
            <span class="style42">$nokpbaru_d</span>
            <!--
              <input name="txtNoKPBaru1Waris" type="text"  id="textfield26" value="$lwu.nokpbaru1" size="6" maxlength="6"  $readmode"/>
              -
              <input name="txtNoKPBaru2Waris" type="text"  id="textfield27" size="1" value="$lwu.nokpbaru2" maxlength="2"  $readmode/>
              -
              <input name="txtNoKPBaru3Waris" type="text" value="$lwu.nokpbaru3" id="textfield28" size="4" maxlength="4"  $readmode/>
              -->
              
              
              <!--
               <input name="txtNoKPBaru1Waris" id="textfield26" style="width: 50px;" type="text" value="$lwu.nokpbaru1" $readmode size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'textfield27')" onBlur="getAgeByIC(this,this.value,'txtUmurWaris');getDOB(this.value)"/>-<input name="txtNoKPBaru2Waris" id="textfield27" style="width: 20px;" type="text" value="$lwu.nokpbaru2" $readmode size="3" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'textfield28')"/>-<input name="txtNoKPBaru3Waris" id="textfield28"  style="width: 40px;" type="text" value="$lwu.nokpbaru3" $readmode size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'textfield28')" onblur="jantinaic1();setSelected(0,2,0,0);tarikh_waris_update_saudara('txtNoKPLamaWaris')" />
               
               
               -->
               #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style51 style74 style42">  www.jpn.gov.my</a>
  #end            </td>
          </tr>
          <tr>
            <td ><div align="right"><span class="style38">No KP Lama</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label>
            
            #if($lwu.nokplama == "")
            #set($lwunokplama = "-")
            #else
            
             #set($lwunokplama = "$lwu.nokplama")
            #end
            
             <span class="style42" style="text-transform:uppercase;" >$lwunokplama</span>
              <input name="txtNoKPLamaWaris" type="hidden" id="txtNoKPLamaWaris" style="text-transform:uppercase;" onblur="text-transform:uppercase;"  value="$lwu.nokplama" size="15" maxlength="8" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td ><div align="right"><span class="style38">Lain-lain KP</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36">
            
            #if($lwu.jeniskp == "5")
            #set($lwujeniskp="TENTERA")
            #elseif($lwu.jeniskp == "6")
            #set($lwujeniskp="POLIS")
            POLIS
            #elseif($lwu.jeniskp == "4")
            #set($lwujeniskp="PASPORT")  
             #elseif($lwu.jeniskp == "7")
            #set($lwujeniskp="LAIN-LAIN")           
            #else
            #set($lwujeniskp="-") 
            #end
            <span class="style42">$lwujeniskp</span>
            
            
            <!--
            <select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="kplain2X(this.value)" onchange="kplain2(this.value)"  >
              
                                           
                                            
                                      
									
								   #if($lwu.jeniskp == "5")
	                                 
                                      
                                            
                                           
              <option value="5" style="text-transform:uppercase;" onblur="text-transform:uppercase;" >Tentera</option>
              <option value="6" style="text-transform:uppercase;" onblur="text-transform:uppercase;" >Polis</option>
              <option value="4" style="text-transform:uppercase;" onblur="text-transform:uppercase;" >Pasport</option>
               <option value="0" style="text-transform:uppercase;" onblur="text-transform:uppercase;" >-KOSONGKAN-</option>
              
                                           
                                            
                                      
	                              
	                               #elseif($lwu.jeniskp=="6")
	                                
                                      
                                            
                                           
              <option value="6" style="text-transform:uppercase;" onblur="uppercase()" >Polis</option>
              <option value="5" style="text-transform:uppercase;" onblur="uppercase()" >Tentera</option>
              <option value="4" style="text-transform:uppercase;" onblur="uppercase()" >Pasport</option>
               <option value="0" style="text-transform:uppercase;" onblur="text-transform:uppercase;" >-KOSONGKAN-</option>
              
                                           
                                            
                                      
	                              
								   #elseif($lwu.jeniskp=="4")
	                               
                                      
                                            
                                           
              <option value="4" style="text-transform:uppercase;" onblur="uppercase()" >Pasport</option>
              <option value="5" style="text-transform:uppercase;" onblur="uppercase()" >Tentera</option>
              <option value="6" style="text-transform:uppercase;" onblur="uppercase()" >Polis</option>
               <option value="0" style="text-transform:uppercase;" onblur="text-transform:uppercase;" >-KOSONGKAN-</option>
              
                                           
                                            
                                      
	                               #else
	                                 
                                      
                                            
                                           
              <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
              <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
              <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
              <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
              
                                           
                                            
                                      


	                               #end
                                    
                                  
                                           
                                  
                                          
                                         
            </select>
            
            -->
              <label></label></td>
          </tr>
       
          #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($lwu.jeniskp != 0 && $lwu.jeniskp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
          <tr>
            <td><div align="right"><span class="style38" >No Lain-lain KP</span></div></td>
            <td>:</td>
            <td>
            #if($lwu.nokplain == "")
            #set($lwunokplain = "-")
            #else
            #set($lwunokplain = "$lwu.nokplain")
            #end
            
            <span class="style42" style="text-transform:uppercase;" >$lwunokplain</span>            <span class="style36">
              <input name="txtNoKPLainWaris" type="hidden" id="txtNoKPLainWaris3"  style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.nokplain" size="15" maxlength="12" $readmodekp />
            </span></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38 style41"> #if($readmode != "disabled")Nama Waris#else <span class="style72">Nama Waris</span>#end</span></div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><label>
           #if($lwu.nama_Ob == "")
           #set($lwunama_Ob = "-")
           #else
             #set($lwunama_Ob = $lwu.nama_Ob)
           #end
            
            
            <span class="style42" style="text-transform:uppercase;" >$lwunama_Ob</span>
              <input name="txtNamaOBWaris" type="hidden" id="txtNamaOBWaris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.nama_Ob" size="34" maxlength="40" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Jantina #else <span class="style72">Jantina</span> #end</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label>
          
                                              
                                      
                                   <span class="style42">#if($lwu.jantina=="1")
	                               
                                      
                                              
                                             
               LELAKI
                
                                             
                                              
                                      
	                               #elseif($lwu.jantina=="2")
	                               
                                      
                                              
                                             
             PEREMPUAN
             
             #else
             
             -
             #end </span></label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Agama</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label>
            
                            
                                      
                                   <span class="style42">#if($lwu.agama=="1")
	                               
	                               
                                      
                                              
                                             
               ISLAM
                                             
                                              
                                      
	                               #elseif($lwu.agama=="2")
	                               
                                      
                                              
                                             
                BUKAN ISLAM
             
                
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              -
                                             
               
                                              
                                      
	                               #end </span></label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Warganegara</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label>
                                             
                                      
                                   <span class="style42">#if($lwu.warga=="1")
	                               
                                      
                                              
                                             
              WARGANEGARA
                                            
                                      
	                               #elseif($lwu.warga=="2")
	                               
                                      
                                              
                                             
               BUKAN WARGANEGARA
           
                                             
                                              
                                      
	                               #elseif($lwu.warga=="3")
	                               
                                      
                                              
              PENDUDUK TETAP
                
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                             
          -
                
                                             
                                              
                                      
	                               #end </span></label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Tarikh Lahir</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36">
              <span class="style42">$lwu.dob</span>
            <input name="txdTarikhLahirWaris" type="hidden" id="txdTarikhLahirWaris" value="$lwu.dob" size="10" maxlength="10" $readmode  onblur="trans_date(this.value)"/>
         </span>                                      </td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">No Surat Beranak</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36">
            <span class="style42">#if($lwu.noberanak != "")
            $lwu.noberanak
            #else
            -
            #end</span>
            <input name="txtNoSuratBeranakWaris" type="hidden" id="txtNoSuratBeranakWaris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.noberanak" size="15" maxlength="10" $readmode/></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span>  Umur #else <span class="style72">Umur</span> #end</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36">
            <span class="style42">#if($lwu.umur != "")
            $lwu.umur
            #else
            -
            #end</span>
            <input name="txtUmurWaris" style="text-transform:uppercase;" onblur="Checkumur()" type="hidden" id="txtUmurWaris" value="$lwu.umur" size="3" maxlength="3" $readmode onkeyup="javascript:validateIC(event,this,this.value,'txtUmurWaris')" /></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Status Waris</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36 style42">
   
                                         
                                           
                                           
									
								   #if($lwu.status_Ob=="1")
	                                 
                                      
                                           
                                           
             
             
              DEWASA/WARAS
                                           
                                      
	                              
	                               #elseif($lwu.status_Ob=="2")
	                                
	                                  
                                           
            
             
             BELUM DEWASA
              
              
                                           
                                      
	                              
								   #elseif($lwu.status_Ob=="3")
	                               
	                                 
                                           
             
              
               HILANG
            
              
                                           
                                     
                                   #elseif($lwu.status_Ob=="4")
	                                    
                                           
        TIDAK SEMPURNA AKAL
            
                                           
                                    
                                        
	                               #else
	                                 
                                      
                                           
              -
              
                                           
                                      
	                               #end                    </td>
          </tr>
          <tr>
            <td><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Talian Persaudaraan #else <span class="style72">Talian Persaudaraan </span>#end</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            #foreach($listsau in $listsaudara)
            
            #if($lwu.saudara==$listsau.id_Saudara)
            
            #set($kodsaudara=$listsau.kod)
            #set($kodsaudaraketerangan=$listsau.keterangan)
            
            
            
            #end    
            #end
            <td> <span class="style42">#if($lwu.saudara!="")
            $kodsaudara - $kodsaudaraketerangan
               
	          
              #else
              -
               #end</span>                        </td>
             </tr>
          <tr>
            <td><div align="right"><span class="style38">Sudah Meninggal Dunia</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label> 
              <span class="style42">#if($lwu.statushidup=="1")
              SUDAH MENINGGAL
              #else
              MASIH HIDUP
              #end </span></label></td>
          </tr>
          #if($lwu.statushidup=="1")
          <tr>
            <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span>  Tarikh Mati #else <span class="style72">Tarikh Mati</span> #end</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36">
            <span class="style42">#if($lwu.tarikhmati != "")
            $lwu.tarikhmati
            #else
            -
            #end </span>
            <input name="txdTarikhMatiWaris" type="hidden" id="txdTarikhMatiWaris" value="$lwu.tarikhmati" size="10" maxlength="10" $readmode onblur="trans_date1(this.value)" />
                        #if($readmode != "disabled")
                        <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/><span class="style73">dd/mm/yyyy</span>#end                        </td>
          </tr>
          <tr>
            <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Waktu Kematian</span> #else <span class="style72">Waktu Kematian</span> #end</div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36">
            <span class="style42">#if($lwu.waktumati != "")
            $lwu.waktumati
            #else
            -
            #end </span>
            <input name="txtWaktuKematianWaris" type="hidden" id="txtWaktuKematianWaris" onkeyup="javascript:validateIC(event,this,this.value,'txtWaktuKematianWaris')" value="$lwu.waktumati" size="4" maxlength="4" $readmode/>
              <span class="style38 style41">#if($readmode != "disabled")</span>               <span class="style46"><span class="style42">format 24 jam (HHMM)</span> #end</span></td>
          </tr>
          #end
        </table></td>
        <td width="50%" valign="top">
        <table width="100%" border="0">
          <tr>
            <td class="style38" ><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Alamat Surat Menyurat #else <span class="style72">Alamat Surat Menyurat </span>#end</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><span class="style42" style="text-transform:uppercase;" >
              <label>
                #if($lwu.alamat1Surat != "")
                $lwu.alamat1Surat
                
                #else
                -
                
                #end
                <input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onblur="uppercase()" type="hidden" id="txtAlamatTerakhir1WarisSurat" value="$lwu.alamat1Surat" size="34"  $readmode/>
                </label>
            </span></td>
          </tr>
          <tr>
            <td class="style38"><div align="right"><span class="style7"></span></div></td>
            <td>&nbsp;</td>
            <td><span class="style42" style="text-transform:uppercase;" >
              <label> #if($lwu.alamat2Surat != "")
                $lwu.alamat2Surat
                
                #else
                -
                
                #end
                <input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="uppercase()" type="hidden" id="txtAlamatTerakhir2WarisSurat"  value="$lwu.alamat2Surat" size="34" $readmode />
                </label>
            </span></td>
          </tr>
          <tr>
            <td class="style38"><div align="right"><span class="style7"  ></span></div></td>
            <td>&nbsp;</td>
           
            <td>
            <span class="style42" style="text-transform:uppercase;" >#if($lwu.alamat3Surat != "")
            $lwu.alamat3Surat
            #else
            
            -
            #end</span>
            <input name="txtAlamatTerakhir3WarisSurat" type="hidden" class="style42" id="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.alamat3Surat" size="34" $readmode/></td>
          </tr>
          <tr>
           
            <td class="style38"><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Poskod #else <span class="style72">Poskod</span> #end</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><span class="style42">
              <label>
              
              #if($lwu.poskodSurat != "")
              $lwu.poskodSurat
              #else
              -
              #end
              
                <input name="txtPoskodWarisSurat" type="hidden" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.poskodSurat" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" $readmode/>
                </label>
            </span></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Bandar #else <span class="style72">Bandar</span> #end</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><span class="style42">
              <label style="text-transform:uppercase;" >
                #if($lwu.bandarSurat != "")
                $lwu.bandarSurat
                #else
                -
                #end
                <input name="txtBandarWarisSurat" type="hidden" id="txtBandarWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.bandarSurat" size="34" $readmode/>
                </label>
            </span></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Negeri #else <span class="style72">Negeri</span> #end</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            #foreach($listnegpomo in $listnegeri)
            
            #if($lwu.idnegeriSurat==$listnegpomo.id_Negeri)
            
            #set($negerikodpemoP=$listnegpomo.kod_Negeri)
            #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
            
            
            
            
            #end 
            #end
            <td><span class="style42" style="text-transform:uppercase;" > #if($lwu.idnegeriSurat!="" && $lwu.idnegeriSurat!="0")
               $negerikodpemoP - $negeriketeranganpemoP
                
                
                                    
                                             
                                              
                      

                                              
                                             
           
                  
                
                
                                             
                                              
                                   
                                
              #else
         
	         
                                             -
                                              
                                   
                                 
	                               #end
                                  
                                                                     
              
              
             
            </span></td>
          </tr>
             #if($readmode != "disabled")
            <tr>
            <td class="style38" >&nbsp;</td>
            <td>&nbsp;</td>
            <td><span class="style42">
              <label>
                                      <input type="checkbox" name="copy" id="copy" onclick="copycopy()" />
                Alamat tetap adalah alamat surat menyurat                                        </label>
            </span></td>
            </tr>
          
          #end
          <tr>
            <td class="style38" width="29%" ><div align="right" class="style38">Alamat Tetap</div></td>
            <td width="1%"><div align="right"><span class="style38">:</span></div></td>
            <td width="70%"><span class="style42" style="text-transform:uppercase;" >
              <label>
              #if($lwu.alamat1 != "")
              $lwu.alamat1
              #else
              -
              #end
              
              
              
                <input name="txtAlamatTerakhir1Waris" style="text-transform:uppercase;" onblur="uppercase()" type="hidden" id="txtAlamatTerakhir1Waris" value="$lwu.alamat1" size="34"  $readmode/>
                </label>
            </span></td>
          </tr>
          <tr>
            <td class="style38"><div align="right"><span class="style7"></span></div></td>
            <td>&nbsp;</td>
            <td><span class="style42" style="text-transform:uppercase;" >
              <label>
              #if($lwu.alamat2 != "")
              $lwu.alamat2
              #else
              -
              #end
              
                <input name="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onblur="uppercase()" type="hidden" id="txtAlamatTerakhir2Waris"  value="$lwu.alamat2" size="34" $readmode />
                </label>
            </span></td>
          </tr>
          <tr>
            <td class="style38"><div align="right"><span class="style7"></span></div></td>
            <td>&nbsp;</td>
            <td>
           <span class="style42" style="text-transform:uppercase;" >#if( $lwu.alamat3 != "")
           $lwu.alamat3
           #else
           -
           #end </span>
           <input name="txtAlamatTerakhir3Waris" type="hidden" class="style42" id="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.alamat3" size="34" $readmode/></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style38">Poskod</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><span class="style42" style="text-transform:uppercase;" >
              <label>
              #if( $lwu.poskod != "")
              $lwu.poskod
              
              #else
              -
              #end
              
                <input name="txtPoskodWaris" type="hidden" id="txtPoskodWaris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.poskod" size="5" maxlength="5" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWaris')" $readmode/>
                </label>
            </span></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style38">Bandar</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><span class="style42" style="text-transform:uppercase;">
              <label>
              #if($lwu.bandar != "")
              $lwu.bandar
              #else
              -
              #end
              
                <input name="txtBandarWaris" type="hidden" id="txtBandarWaris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.bandar" size="34" $readmode/>
                </label>
            </span></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style38">Negeri</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            
           
            
            #foreach($listnegpomo in $listnegeri)
            
            #if($lwu.idnegeri==$listnegpomo.id_Negeri)
            
            #set($negerikodpemoP=$listnegpomo.kod_Negeri)
            #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
            
             
             
            
            #end 
            #end
            <td><span class="style42" style="text-transform:uppercase;" > #if($lwu.idnegeri!="" && $lwu.idnegeri!="0")
               
                 $negerikodpemoP - $negeriketeranganpemoP
                  
                
                         
              #else
           -
              #end
            </span></td>
             </tr>
          <tr>
            <td class="style38" valign="top"><div align="right" class="style38">No Telefon</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td>
            <span class="style42" style="text-transform:uppercase;" >#if($lwu.noTel != "")
            $lwu.noTel 
            #else
            -
            #end</span>
            <input name="txtNoTeleponWaris" type="hidden" class="style42" id="txtNoTeleponWaris" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponWaris')" value="$lwu.noTel" size="14" maxlength="12" $readmode/></td>
          </tr>
          #if($readmode != "disabled")
          <tr>
            <td class="style38" valign="top">&nbsp;</td>
            <td>&nbsp;</td>
            <td><span class="style46">  <span class="style42 style42">cth: 031234567</span> </span></td>
          </tr>
          #end
          <tr>
            <td class="style38" valign="top"><div align="right">No Telefon Bimbit</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td>
            <span class="style42" style="text-transform:uppercase;" >#if($lwu.nohp != "")
            $lwu.nohp
            #else
            -
            #end</span>
            <input name="txtNoTeleponBimbitWaris" type="hidden" class="style42" id="txtNoTeleponBimbitWaris" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponBimbitWaris')" value="$lwu.nohp" size="14" maxlength="12" $readmode/></td>
          </tr>
          #if($readmode != "disabled")
          <tr>
            <td class="style38" valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td><span class="style46"> <span class="style42 style42">cth: 0121234567 </span></span></td>
          </tr>
          #end
          <tr>
            <td class="style38" valign="top"><div align="right" class="style38">Catatan</div></td>
            <td valign="top"><div align="right"><span class="style38">:</span></div></td>
            <td><span class="style42"  style="text-transform:uppercase;" >
            #if( $lwu.catatan != "")
            $lwu.catatan
            
            #else
            -
            #end
            
            
             
            </span></td>
          </tr>
        </table></td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>
   #if($readmode != "disabled")
   <tr>
    <td>
                              <table width="100%">
                                <tr>
                                  <td><span class="style5 style47 style69"><span class="style41">Perhatian </span>: Sila masukkan salah satu nombor pengenalan sekiranya berbeza dan pastikan label berwarna <span class="style41">merah</span> wajib diisi.</span></td>
                                </tr>
                                  <tr>
                                  <td> <span class="style74"><em>Sila masukkan salah satu nombor telefon sekiranya berbeza.</em></span></td>
                                </tr>
</table> </td>
  </tr>
#end
  <tr>
    <td><table width="100%" border="0" align="center">
      <tr>
        <td>
          <div align="center">
          <!--
          #if ($flagFromSenaraiFailSek8 == '')
            <input type="button" name="tambahwaris" id="tambahwaris" value="$buttonwaris" onkeypress="setSelected(0,2,0,0);tambah_waris()" onclick="setSelected(0,2,0,0);tambah_waris()"/>
            
          
              #if($buttonwaris=="Simpan" || $buttonwaris=="Kemaskini" )
              
              #if($listWarisLapisanIdMatiDelete.size()==0 && $buttonwaris=="Kemaskini")
               <input type="button" name="tambahwarishapus" id="tambahwarishapus" value="Hapus" onclick="setSelected(0,2,0,0);hapus_waris()"/>
               #end
               
               #end
            
            #end
            #if(($ch=="checked" ) && $buttonwaris=="Kemaskini")
            <input type="button" name="lapisanberikut" id="lapisanberikut" value="Lapisan Berikut" onkeypress="setSelected(0,2,0,0);lapisan_waris()" onclick="setSelected(0,2,0,0);lapisan_waris()"/>
            #end
            
            
            #if($show_batal_waris=="yes")
            <input type="button" name="cmdSimpan7" id="cmdSimpan7" value="Batal" onkeypress="warisbatalupdate()" onclick="warisbatalupdate()"/>
            #end
            
            --> 
            <input type="submit" name="cmdKembali8" id="cmdKembali8" value="Kembali"  onclick="setSelected(0,2,0,0);WarisView()"/>
          </div></td>
      </tr>
    </table></td>
  </tr>
                               #end
                               
                               #end
                               #if($show_table_waris=="yes")                                #if($readmode != "disabled")
 #end
                            <tr>
                              <td><table width="100%" border="0" align="center">
                                  <tr>
                                  <td>
                                  <label>
                                  <div align="center">#if($buttonwarisSimpan!="")
                                    <input type="button" name="tambahwarisSimpan" id="tambahwarisSimpan" value="$buttonwarisSimpan"  onclick="setSelected(0,2,0,0);tambah_waris_Simpan()"/>
                                    #end 
                                     #if($buttonwarisSimpan=="")
                                     <input type="button" name="tambahwaris2" id="tambahwaris2" value="$buttonwaris" onkeypress="setSelected(0,2,0,0);tambah_waris()" onclick="setSelected(0,2,0,0);tambah_waris()"/>
                                     #end
                                    
                                    
                                    
                                    #if($show_batal_waris=="yes")
                                    <input type="reset" name="cmdSimpan7" id="cmdSimpan7" value="Batal" onclick="setSelected(0,2,0,0);Cancel_Baru()" />
                                   
                                    #end
                                    
                                      <input type="submit" name="cmdKembali8" id="cmdKembali8" value="Kembali"  onclick="setSelected(0,2,0,0);WarisView()"/>                                   
                                  </div>
                                  </label> </td>
                                  </tr>
                              </table></td>
                            </tr>
                               #end
                                #if($show_senarai_lapis_pertama == "yes")
   
    
                                  
  <tr>
    <td>
    
    
    
      #end      </td>
      </tr>
  
  #end     
   #if($show_lapisan_bawah=="yes")
    <tr>
    
      <td>
      
        <fieldset>
<legend>SENARAI WARIS LAPISAN BERIKUT</legend>
      <table width="100%">
        #set($idwww=$idparent)
       <input type="hidden" name="idwarislapisX" value="$idwww" />
        <input type="hidden" name="idparentlapisX" value="" />
        </table>
          <div align="center">
            <table width="100%">
              <tr class="table_header">
               <td width="5%"><div align="center">NO</div></td>
                <td width="20%"><div align="left">NAMA WARIS</div></td>
                <td width="15%"><div align="center">NO KP BARU</div></td>
                <td width="5%"><div align="center">UMUR</div></td>
                <td width="15%"><div align="left">TALIAN PERSAUDARAAN</div></td>
                <td width="20%"><div align="left">NAMA WARIS YANG MENINGGAL</div></td>
                <td width="10%"><div align="center">STATUS</div></td>
                <td width="10%"><div align="center">LAPISAN</div></td>
              </tr>
              <!--   <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" /> -->
            </table>
            #if($listWarisLapisanIdMati.size()==0)
            <table width="100%">
              <tr bgcolor="white">
                <td align="left"> Tiada Rekod </td>
              </tr>
            </table>
            #else
            <table width="100%">
            #set($nowar=0)
              #foreach($listwarislapisan in $listWarisLapisanIdMati)
               
                 #set($nowar=$nowar+1)
                 #if($nowar%2!=0)
              <tr >
              <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowar</div></td>
            <td width="20%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan_X('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                <td width="15%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.umur</div></td>
                #foreach($listsaudaralist in $listsaudara)
                
                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                
                #set($wariskodsaudara=$listsaudaralist.kod)
                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                
                
                #end    
                #end
                <td width="15%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                 #if($listwarislapisan.idparent=="")
                #set($idpar="")
                #else
                
                #foreach($lw in $listWarisOB)
                
                #if($lw.idwaris==$listwarislapisan.idparent)
                #set($idpar=$lw.nama_Ob)
                
               
                #end
                
                #end
                 #end
                <td width="20%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$idpar</div></td>
               
                #if($listwarislapisan.statushidup=="0")
                #set($hidup="Masih Hidup")
                #end
                #if($listwarislapisan.statushidup=="1")
                #set($hidup="Sudah Meninggal")
                #end
                #if($listwarislapisan.statushidup=="")
                #set($hidup="")
                #end
                <td width="10%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                <td width="10%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
              </tr>
              
              #else
               <tr class="table_header">
              <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowar</div></td>
            <td width="20%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan_X('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                <td width="15%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.umur</div></td>
                #foreach($listsaudaralist in $listsaudara)
                
                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                
                #set($wariskodsaudara=$listsaudaralist.kod)
                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                
                
                #end    
                #end
                <td width="15%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                 #if($listwarislapisan.idparent=="")
                #set($idpar="")
                #else
                
                #foreach($lw in $listWarisOB)
                
                #if($lw.idwaris==$listwarislapisan.idparent)
                #set($idpar=$lw.nama_Ob)
                
               
                #end
                
                #end
                 #end
                <td width="20%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$idpar</div></td>
               
                #if($listwarislapisan.statushidup=="0")
                #set($hidup="Masih Hidup")
                #end
                #if($listwarislapisan.statushidup=="1")
                #set($hidup="Sudah Meninggal")
                #end
                #if($listwarislapisan.statushidup=="")
                #set($hidup="")
                #end
                <td width="10%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                <td width="10%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
              </tr>
              #end
              #end
            </table>
            #end </div>
            </fieldset>            </td>
    </tr>
  
 
     #end 
  
  
  
  
  
  
   #if($show_lapisan_berikut=="yes")
   
    #if($show_lapisan_berikut_tambah=="yes")


   

    
    <tr>
    <td>
        <fieldset>
        <legend>MAKLUMAT WARIS LAPISAN BERIKUT</legend>
        
        <table width="100%" border="0">
        
        <tr>
          <td width="50%" valign="top"><table width="100%">
          <input type="hidden" name="txtIdSimatiWaris" value="$id_Simati" />
              <tr>
                <td width="35%"><div align="right"><span class="style38"> Waris Yang Meninggal</span></div></td>
                
                
                
                  <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                  #foreach($listWarisParentlist in $listWarisParent)
                   <td class="style36" width="70%">
                     <span class="style42" style="text-transform:uppercase;" onblur="uppercase()">$listWarisParentlist.nama_Ob</span>
              
                 <input name="txtIdParent" type="hidden"  id="textfield31" size="15" value="$listWarisParentlist.idwaris" maxlength="2"  $readmode/>
                   #set($ip=$listWarisParentlist.idwaris)
                    #set($lp=$listWarisParentlist.lapis)
               
                  <input name="txtLapisParent" type="hidden"  id="textfield31" size="15" value="$listWarisParentlist.lapis" maxlength="2"  $readmode/>                  </td>
            
                #end                </tr>
              <tr>
                <td><div align="right" class="style44">Nama Waris</div></td>
                <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris2" value="$namaOB" size="34" maxlength="40" $readmode  style="text-transform:uppercase;" onblur="uppercase()" />
                </label></td>
              </tr>
              <tr>
                <td width="35%"><div align="right"><span class="style38">No KP Baru</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td width="70%" class="style36">
                
                 
           <input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Warisx" style="width: 50px;" type="text" value="$nokpbaru1" $readmode size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Warisx')" onBlur="getAgeByIC(this,this.value,'txtUmurWaris');;getDOB(this.value)" />-<input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Warisx" style="width: 20px;" type="text" value="$nokpbaru2" $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Warisx')"/>-<input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Warisx"  style="width: 40px;" type="text" value="$nokpbaru3" $readmode size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Warisx')" onblur="jantinaic1();setSelected(0,2,0,0);tarikh_waris_lapisan_saudara('txtNoKPLamaWaris')" />
           <a href="http://www.jpn.gov.my" target="_blank" class="style51 style74 style42">  www.jpn.gov.my</a></td>
              </tr>
              <tr>
                <td ><div align="right"><span class="style38">No KP Lama</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><label>
                  <input name="txtNoKPLamaWaris" type="text" id="txtNoKPLamaWaris"  style="text-transform:uppercase;" onblur="uppercase()" value="$nokpwaris" size="15" maxlength="8" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><span class="style38">Lain-lain KP</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode  style="text-transform:uppercase;" onblur="kplain3X(this.value)" onchange="kplain3(this.value)">
                  
                                            
                                           
                                            
                                      
									
								   #if($jenisKp=="5")
	                                 
                                      
                                            
                                           
                                            
                  
                  <option value="5"  style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                    <option value="6"  style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                    <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                      <option value="7"  style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                     <option value="0"  style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                  
                                            
                                           
                                            
                                      
	                              
	                               #elseif($jenisKp=="6")
	                                
                                      
                                            
                                           
                                            
                  <option value="6"  style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                    <option value="5"  style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                    <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                     <option value="7"  style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                     <option value="0"  style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                  
                                            
                                           
                                            
                                      
	                              
								   #elseif($jenisKp=="4")
	                               
                                      
                                            
                                           
                                            
                  <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                    <option value="5"  style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                    <option value="6"  style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                     <option value="7"  style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                     <option value="0"  style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                  
                  
                  #elseif($jenisKp=="7")
	                               
                                      
                   <option value="7"  style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                           
                                            
                  <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                    <option value="5"  style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                    <option value="6"  style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                     
                     <option value="0"  style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                  
                                            
                                           
                                            
                                      
	                               #else
	                                 
                                      
                                            
                                           
                                            
                  <option value="0"  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                    <option value="5"  style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                    <option value="6"  style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                    <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                     <option value="7"  style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                  
                                            
                                           
                                            
                                      
	                               #end
                                    
                                  
                                    
                                          
                                         
                                          
                </select>
                  <label></label></td>
              </tr>
              
                 #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($jenisKp != 0 && $jenisKp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
              
              
              <tr>
                <td><div align="right"><span class="style38">No Lain-lain KP</span></div></td>
                <td class="style36">:</td>
                <td class="style36"><input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris"  value="$nokplain" size="15" maxlength="25" $readmodekp  style="text-transform:uppercase;" onblur="uppercase()" /></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style44">Jantina</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><label>
                  <select name="socJantinaWaris" id="socJantinaWaris" class="mediumselect" $readmode  style="text-transform:uppercase;" onblur="uppercase()" onchange="setSelected(0,2,0,0);tarikh_waris_lapisan_saudara('socAgamaWaris')" >
                    
                                              
                                             
                                              
                                      
                                   #if($jantina=="1")
	                               
                                      
                                              
                                             
                                              
                    
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                    
                                              
                                             
                                              
                                      
	                               #elseif($jantina=="2")
	                               
                                      
                                              
                                             
                                              
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                    
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                    <option value=""  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jantina</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                    
                                              
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
                                            
                  </select>
                </label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Agama</span></div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><label>
                  <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode  style="text-transform:uppercase;" onblur="uppercase()">
                    
                                              
                                             
                                              
                                      
                                   #if($agama=="1")
	                               
	                               
                                      
                                              
                                             
                                              
                    
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                    
                                              
                                             
                                              
                                      
	                               #elseif($agama=="2")
	                               
                                      
                                              
                                             
                                              
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                    
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                    <option value=""  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Agama</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                    
                                              
                                             
                                              
                                      
	                               #end
                                      
                                    
                                    
                                            
                                           
                                            
                  </select>
                </label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Warganegara</span></div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><label>
                  <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="mediumselect" $readmode  style="text-transform:uppercase;" onblur="uppercase()">
                    
                                              
                                             
                                              
                                      
                                   #if($warga=="1")
	                               
                                      
                                              
                                             
                                              
                    
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                    <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                    
                                              
                                             
                                              
                                      
	                               #elseif($warga=="2")
	                               
                                      
                                              
                                             
                                              
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                    <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                    
                                              
                                             
                                              
                                      
	                               #elseif($warga=="3")
	                               
                                      
                                              
                                             
                                              
                    <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                    
                                              
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                             
                                              
                    <option value=""  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Warganegara</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                    <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                    
                                              
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
                                            
                  </select>
                </label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Tarikh Lahir</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><input name="txdTarikhLahirWaris" type="text" id="txdTarikhLahirWaris" value="$dob" size="10" maxlength="10" $readmode onblur="trans_date(this.value)" />
                       <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/><span class="style73">dd/mm/yyyy</span></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">No Surat Beranak</span></div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><input name="txtNoSuratBeranakWaris"  style="text-transform:uppercase;" onblur="uppercase()" type="text" id="txtNoSuratBeranakWaris" value="$noberanak" size="15" maxlength="10" $readmode/></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style44">Umur</span></div></td>
                
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                
                <td class="style36"><input name="txtUmurWaris" type="text" id="txtUmurWaris"  onkeyup="javascript:validateIC(event,this,this.value,'txtUmurWaris')" style="text-transform:uppercase;" onblur="Checkumur()" value="$umur" size="3" maxlength="3" $readmode/></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Status Waris</span></div></td>
               <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><select name="socStatusOBWaris"  class="autoselect" $readmode id="socStatusOBWaris"  style="text-transform:uppercase;" onblur="uppercase()">
                  
                                            
                                           
									
								   #if($statusWaris=="1")
	                                 
                                      
                                           
                                           
                                            
                  
                  <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                    <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                    <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                  
                                            
                                           
                                      
	                              
	                               #elseif($statusWaris=="2")
	                                
	                                  
                                           
                                            
                  <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                    <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                    <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                  
                                            
                                           
                                      
	                              
								   #elseif($statusWaris=="3")
	                               
	                                 
                                           
                                            
                  <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                    <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                  
                                            
                                           
                                     
                                   #elseif($statusWaris=="4")
	                                    
                                           
                                            
                  <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                    <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                  
                                            
                                           
                                    
                                        
	                               #else
	                                 
                                      
                                           
                                            
                  <option value=""  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Status</option>
                    <option value="1"  style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
                    <option value="2"  style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
                    <option value="3"  style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
                    <option value="4"  style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
                  
                                            
                                           
                                      
	                               #end
                                   
                                         
                                          
                </select>                </td>
              </tr>
              <tr>
                <td><div align="right"><span class="style44">Talian Persaudaraan</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                #foreach($listsau in $listsaudara)
                
                #if($saudara==$listsau.id_Saudara)
                
                #set($kodsaudara=$listsau.kod)
                #set($kodsaudaraketerangan=$listsau.keterangan)
                
                
                
                #end    
                #end
                <td> #if($saudara!="")
                  <select name="socSaudaraWaris" class="largeselect" $readmode id="socSaudaraWaris"  style="text-transform:uppercase;" onblur="uppercase()">
                      <option value="$saudara"  style="text-transform:uppercase;" onblur="uppercase()">$kodsaudara - $kodsaudaraketerangan</option>
                    
                                              
                                         
                                         
                                           #foreach($listsau in $listsaudara)
                                 
                                  #if($saudara!=$listsau.id_Saudara)
                                     #if($jantina=="1")
                                          #set($jan="01")
                                          #end
                                          #if($jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jan)
                                
                                         
                                         
                                             
                                
	                               
                                              
                    <option value="$listsau.id_Saudara"  style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>
                    
                                     #end         
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>
                  #else
                  <select name="socSaudaraWaris" class="largeselect" $readmode id="socSaudaraWaris"  style="text-transform:uppercase;" onblur="uppercase()" onfocus="insertsaudara1()">
                    <option value=""  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Talian Persaudaraan</option>
                    
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                
	                                 #if($saudara!=$listsau.id_Saudara)
                                          #if($jantina=="1")
                                          #set($jan="01")
                                          #end
                                          #if($jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jan)
                                              
                    <option value="$listsau.id_Saudara"  style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>
                    
                                          #end    
                                   #end
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>                </td>
                #end </tr>
              <tr>
                <td><div align="right"><span class="style38">Sudah Meninggal Dunia</span></div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><label> 

                #if($checkmati=="1")
                  #set($chq="checked")
                  #else
                  #set($chq="")
                  #end
                  <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq value="$checkmati"  onclick="setSelected(0,2,0,0);tarikh_waris_lapisan('checkHidupWaris')" />
                </label></td>
              </tr>
            #if($show_tarikh=="yes")
            <tr>
              <td><div align="right"><span class="style44">Tarikh Mati</span></div></td>
              <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
              <td class="style36"><input name="txdTarikhMatiWaris" type="text" id="txdTarikhMatiWaris" value="$tarikhmati" size="10" maxlength="10" $readmode onblur="trans_date1(this.value)" />
                    <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/> <span class="style73">dd/mm/yyyy</span></td>
            </tr>
            <tr>
              <td><div align="right"><span class="style44">Waktu Kematian</span></div></td>
               <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
              <td class="style36"><input name="txtWaktuKematianWaris" type="text"  style="text-transform:uppercase;" onkeyup="javascript:validateIC(event,this,this.value,'txtWaktuKematianWaris')" onblur="uppercase()" id="txtWaktuKematianWaris" value="$waktumatiwaris" size="4" maxlength="4" $readmode  />
                <span class="style73">format 24 jam (HHMM)</span></td>
            </tr>
            #end
          </table></td>
          <td width="40%" valign="top"><table width="100%" border="0">
              <tr>
                <td class="style38" >&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td class="style38" ><div align="right" class="style44">Alamat Surat Menyurat</div></td>
                <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtAlamatTerakhir1WarisSurat" type="text"  style="text-transform:uppercase;" onblur="uppercase()" id="txtAlamatTerakhir1WarisSurat" value="$alamat1Surat" size="34"  $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td>&nbsp;</td>
                <td><label>
                  <input name="txtAlamatTerakhir2WarisSurat" type="text"  style="text-transform:uppercase;" onblur="uppercase()" id="txtAlamatTerakhir2WarisSurat"  value="$alamat2Surat" size="34" $readmode />
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td>&nbsp;</td>
                <td><input name="txtAlamatTerakhir3WarisSurat" type="text" id="txtAlamatTerakhir3WarisSurat"  style="text-transform:uppercase;" onblur="uppercase()" value="$alamat3Surat" size="34" $readmode/></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style44">Poskod</div></td>
                <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat"  onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" style="text-transform:uppercase;" onblur="uppercase()" value="$poskodSurat" size="5" maxlength="5" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style44">Bandar</div></td>
                <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtBandarWarisSurat" type="text" id="txtBandarWarisSurat" value="$bandarSurat"  style="text-transform:uppercase;" onblur="uppercase()" size="34" maxlength="40" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style44">Negeri</div></td>
                <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                #foreach($listnegpomo in $listnegeri)
                
                #if($negeriSurat==$listnegpomo.id_Negeri)
                
                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                
                
                
                #end 
                #end
                <td> #if($negeriSurat!="")
                  <select name="socNegeriWarisSurat" class="autoselect" $readmode id="socNegeriWarisSurat"  style="text-transform:uppercase;" onblur="uppercase()">
                      <option value="$negeriSurat"  style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                    
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                                              
                    
                    <option value="$listnegpomo.id_Negeri"  style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                    
                    
                                              
                                             
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            
                  
                  </select>
                  #else
                  <select name="socNegeriWarisSurat" class="autoselect" $readmode id="socNegeriWarisSurat"  style="text-transform:uppercase;" onblur="uppercase()">
                    <option value=""  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                    
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                                              
                    
                    <option value="$listnegpomo.id_Negeri"  style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                    
                    
                                              
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            
                  
                  </select>                </td>
                #end </tr>
              
              <tr>
                <td class="style38" >&nbsp;</td>
                <td>&nbsp;</td>
                <td><label>
                  <input type="checkbox" name="copy" id="copy" onclick="copycopy()" />
                  <span class="style73">Alamat tetap adalah alamat surat menyurat </span></label></td>
              </tr>
              <tr>
                <td class="style38" width="29%" ><div align="right" class="style38">Alamat Tetap</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td width="70%"><label>
                  <input name="txtAlamatTerakhir1Waris" type="text"  style="text-transform:uppercase;" onblur="uppercase()" id="txtAlamatTerakhir1Waris" value="$alamat1" size="34"  $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td>&nbsp;</td>
                <td><label>
                  <input name="txtAlamatTerakhir2Waris" type="text"  style="text-transform:uppercase;" onblur="uppercase()" id="txtAlamatTerakhir2Waris"  value="$alamat2" size="34" $readmode />
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td>&nbsp;</td>
                <td><input name="txtAlamatTerakhir3Waris" type="text" id="txtAlamatTerakhir3Waris"  style="text-transform:uppercase;" onblur="uppercase()" value="$alamat3" size="34" $readmode/></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style38">Poskod</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtPoskodWaris" type="text" id="txtPoskodWaris"  onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWaris')" style="text-transform:uppercase;" onblur="uppercase()" value="$poskod" size="5" maxlength="5" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style38">Bandar</div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtBandarWaris" type="text" id="txtBandarWaris" value="$bandar"  style="text-transform:uppercase;" onblur="uppercase()" size="34" maxlength="40" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style38">Negeri</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                #foreach($listnegpomo in $listnegeri)
                
                #if($negeri==$listnegpomo.id_Negeri)
                
                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                
                
                
                #end 
                #end
                <td> #if($negeri!="")
                  <select name="socNegeriWaris" class="autoselect" $readmode id="socNegeriWaris"  style="text-transform:uppercase;" onblur="uppercase()">
                      <option value="$negeri"  style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                                              
                    <option value="$listnegpomo.id_Negeri"  style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                    
                                              
                                             
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>
                  #else
                  <select name="socNegeriWaris" class="autoselect" $readmode id="socNegeriWaris"  style="text-transform:uppercase;" onblur="uppercase()">
                    <option value=""  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                                              
                    <option value="$listnegpomo.id_Negeri"  style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                    
                                              
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>                </td>
                #end </tr>
              <tr>
                <td class="style38" ><div align="right" class="style38">No Telefon</div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><input name="txtNoTeleponWaris"  style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponWaris')"  type="text" id="txtNoTeleponWaris" value="$notel" size="14" maxlength="12" $readmode/></td>
              </tr>
              <tr>
                <td class="style38" >&nbsp;</td>
                <td class="style36">&nbsp;</td>
                <td valign="top"><span class="style73">cth: 031234567</span></td>
              </tr>
              <tr>
                <td class="style38" ><div align="right">No Telefon Bimbit</div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><input name="txtNoTeleponBimbitWaris"  style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponBimbitWaris')" type="text" id="txtNoTeleponBimbitWaris" value="$hp" size="14" maxlength="12" $readmode/></td>
              </tr>
              <tr>
                <td class="style38"  valign="top">&nbsp;</td>
                <td class="style36" valign="top">&nbsp;</td>
                <td valign="top"><span class="style73">cth: 0121234567</span></td>
              </tr>
              <tr>
                <td class="style38"  valign="top"><div align="right" class="style38">Catatan</div></td>
                 <td class="style36" width="1%" valign="top"><div align="right"><span class="style38">:</span></div></td>
                <td><textarea name="txtCatatanWaris" cols="31"   style="text-transform:uppercase;" onblur="uppercase()" rows="3"  $readmode>$catatan</textarea></td>
              </tr>
          </table></td>
        </tr>
        </table>
        </fieldset>    </td>
  </tr>
  #if($readmode != "disabled")
   <tr>
    <td>
                              <table width="100%">
                                <tr>
                                  <td><span class="style5 style47 style69"><span class="style41">Perhatian </span>: Sila masukkan salah satu nombor pengenalan sekiranya berbeza dan pastikan label berwarna <span class="style41">merah</span> wajib diisi.</span></td>
                                </tr>
                                   <tr>
                                  <td> <span class="style74"><em>Sila masukkan salah satu nombor telefon sekiranya berbeza.</em></span></td>
                                </tr>
</table> </td>
  </tr>
#end
  
  #end
  
  
  #if($show_lapisan_berikut_update=="yes")
  #foreach($lwu in $listWarisLapisanUpdate)
  
  
<tr>
  <td>
  <fieldset><legend>MAKLUMAT WARIS LAPISAN BERIKUT</legend>
  
  <table width="100%" border="0">
    <tr>
      <td width="50%" valign="top">
      
      
      
      
      <table width="100%">
      <!--
       idwarisup2 :: <input type="text" name="idwarisup" value="$lwu.idwaris" />
       -->
       #set($idwarisup=$lwu.idwaris)
       
    
        <input type="hidden" name="txtIdSimatiWaris" value="$lwu.idSimati" />
        <tr>
          <td width="35%"><div align="right"><span class="style38">Waris Yang Meninggal</span></div></td>
         
                  <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                  #foreach($listWarisParentlist in $listWarisParent)
                   <td class="style36" width="70%">
                   <span class="style42" style="text-transform:uppercase;" onblur="uppercase()">$listWarisParentlist.nama_Ob</span>
                 
             <input name="txtIdParent" type="hidden"  id="txtIdParent" size="15" value="$listWarisParentlist.idwaris" maxlength="2"  $readmode/>
                  
                  
                
          <input name="txtLapisParent" type="hidden"  id="txtLapisParent" size="15" value="$listWarisParentlist.lapis" maxlength="2"  $readmode/>                  </td>
                    #set($ip=$listWarisParentlist.idwaris)
                    #set($lp=$listWarisParentlist.lapis)
                #end        </tr>
        <tr>
          <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Nama Waris #else <span class="style72">Nama Waris</span> #end</span></div></td>
          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris3" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.nama_Ob" size="34" maxlength="40" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td width="35%"><div align="right"><span class="style38">No KP Baru</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td width="70%" class="style36">
          
          
           <input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Waris" style="width: 50px;" type="text" value="$lwu.nokpbaru1" $readmode size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Waris')" onBlur="getAgeByIC(this,this.value,'txtUmurWaris');;getDOB(this.value)" />-<input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Waris" style="width: 20px;" type="text" value="$lwu.nokpbaru2" $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris')"/>-<input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Waris"  style="width: 40px;" type="text" value="$lwu.nokpbaru3" $readmode size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris')" onblur="jantinaic1();setSelected(0,2,0,0);tarikh_waris_lapisan_update_saudara('txtNoKPLamaWaris')" />
           <span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> </span>
           
            
  <a href="http://www.jpn.gov.my" target="_blank" class="style51 style74 style42">  www.jpn.gov.my</a> #end                   </td>
        </tr>
        <tr>
          <td ><div align="right"><span class="style38">No KP Lama</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><label>
            <input name="txtNoKPLamaWaris" type="text" style="text-transform:uppercase;" onblur="uppercase()" id="txtNoKPLamaWaris" value="$lwu.nokplama" size="15" maxlength="8" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td ><div align="right"><span class="style38">Lain-lain KP</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="kplain4X(this.value)" onchange="kplain4(this.value)" >
            
              
                                           
                                            
                                      
									
								   #if($lwu.jeniskp=="5")
	                                 
                                      
                                            
                                           
              
            
            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
             <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
            <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
            
              
                                           
                                            
                                      
	                              
	                               #elseif($lwu.jeniskp=="6")
	                                
                                      
                                            
                                           
              
            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
            <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
            <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
            
              
                                           
                                            
                                      
	                              
								   #elseif($lwu.jeniskp=="4")
	                               
                                      
                                            
                                           
              
            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
            <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
            <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
            
            
             #elseif($lwu.jeniskp=="7")
	                               
                                      
                                            
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
                             #if($lwu.jeniskp != 0 && $lwu.jeniskp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
        <tr>
          <td><div align="right"><span class="style38">No Lain-lain KP</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><span class="style36">
            <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris4"  style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.nokplain" size="15" maxlength="12" $readmodekp />
          </span></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Jantina #else <span class="style72">Jantina</span> #end</span></div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><label>
            <select name="socJantinaWaris" id="socJantinaWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onchange="setSelected(0,2,0,0);tarikh_waris_lapisan_update_saudara('socAgamaWaris')" onblur="uppercase()">
              
                
                                             
                                              
                                      
                                   #if($lwu.jantina=="1")
	                               
                                      
                                              
                                             
                
              
              <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
              <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.jantina=="2")
	                               
                                      
                                              
                                             
                
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
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><label>
            <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
              
                
                            
                                      
                                   #if($lwu.agama=="1")
	                               
	                               
                                      
                                              
                                             
                
              
              <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
              <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.agama=="2")

	                               
                                      
                                              
                                             
                
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
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><label>
            <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="mediumselect" $readmode style="text-transform:uppercase;" onblur="uppercase()">
              
                
                                             
                                              
                                      
                                   #if($lwu.warga=="1")
	                               
                                      
                                              
                                             
                
              
              <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
              <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
              <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.warga=="2")
	                               
                                      
                                              
                                             
                
              <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
              <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
              <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.warga=="3")
	                               
                                      
                                              
                                             
                
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
          <td><div align="right"><span class="style38">Tarikh Lahir</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><input name="txdTarikhLahirWaris" type="text" id="txdTarikhLahirWaris" value="$lwu.dob" size="10" maxlength="10" $readmode onblur="trans_date(this.value)" />
                         #if($readmode != "disabled")
                         <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/><span class="style73">dd/mm/yyyy</span>#end                         </td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">No Surat Beranak</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><input name="txtNoSuratBeranakWaris" type="text" id="txtNoSuratBeranakWaris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.noberanak" size="15" maxlength="10" $readmode/></td>
        </tr>
        <tr>
          <td><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Umur #else <span class="style72">Umur</span> #end</div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><input name="txtUmurWaris" style="text-transform:uppercase;" onblur="Checkumur()" type="text" id="txtUmurWaris" onkeyup="javascript:validateIC(event,this,this.value,'txtUmurWaris')" value="$lwu.umur" size="3" maxlength="3" $readmode/></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Status Waris</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><select name="socStatusOBWaris"  class="autoselect" $readmode id="socStatusOBWaris" style="text-transform:uppercase;" onblur="uppercase()">
            
              
                                         
                                         
                                           
                                           
									
								   #if($lwu.status_Ob=="1")
	                                 
                                      
                                           
                                           
              
            
            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
            
              
                                           
                                      
	                              
	                               #elseif($lwu.status_Ob=="2")
	                                
	                                  
                                           
              
            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
            
              
                                           
                                      
	                              
								   #elseif($lwu.status_Ob=="3")
	                               
	                                 
                                           
              
            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">03 - Hilang</option>
            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">01 - Dewasa/Waras</option>
            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">02 - Belum Dewasa</option>
            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">04 - Tidak Sempurna Akal</option>
            
              
                                           
                                     
                                   #elseif($lwu.status_Ob=="4")
	                                    
                                           
              
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
                                   
                                         
            
          </select>          </td>
        </tr>
        <tr>
          <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Talian Persaudaraan</span> #else <span class="style72">Talian Persaudaraan</span> #end</div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          #foreach($listsau in $listsaudara)
          
          #if($lwu.saudara==$listsau.id_Saudara)
          
          #set($kodsaudara=$listsau.kod)
          #set($kodsaudaraketerangan=$listsau.keterangan)
          
          
          
          #end    
          #end
          <td> #if($lwu.saudara!="")
            <select name="socSaudaraWaris" class="largeselect" $readmode id="socSaudaraWaris" style="text-transform:uppercase;" onblur="uppercase()">
                    <option value="$lwu.saudara">$kodsaudara - $kodsaudaraketerangan</option>
              
                
                                             
                                              
                                 
                                               
                                  #foreach($listsau in $listsaudara)
                                          #if($lwu.jantina=="1")
                                          #set($jan="01")
                                         
                                          #elseif($lwu.jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                        
                                   
                                  #if($lwu.saudara!=$listsau.id_Saudara && $listsau.jantina==$jan)
                                  
	                               
                
              <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>
              
                                 
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
              
            </select>
            #else
            <select name="socSaudaraWaris" class="largeselect" $readmode id="socSaudaraWaris" style="text-transform:uppercase;" onblur="uppercase()">
              <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Talian Persaudaraan</option>
              
                
                                  #foreach($listsau in $listsaudara)
                                 
                                
                                   #if($lwu.jantina=="1")
                                          #set($jan="01")
                                          #end
                                          #if($lwu.jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                        
                                          #if($listsau.jantina==$jan)
                                 
	                               
                
              <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>
              
                #end
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
              
            </select>          </td>
          #end </tr>
           <tr>
            <td><div align="right"><span class="style38">Sudah Meninggal Dunia</span></div></td>
             <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label> #if($lwu.statushidup=="1")
              #set($ch="checked")
              #else
              #set($ch="")
              #end
              <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $ch $readmode value="$checkmati"  onclick="setSelected(0,2,0,0);tarikh_waris_lapisan_update('checkHidupWaris')" />
            </label></td>
          </tr>
          #if($lwu.statushidup=="1")
          <tr>
            <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span>  Tarikh Mati #else <span class="style72">Tarikh</span> <span class="style72">Mati</span> #end</span></div></td>
             <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><input name="txdTarikhMatiWaris" type="text" id="txdTarikhMatiWaris" value="$lwu.tarikhmati" size="10" maxlength="10" $readmode onblur="trans_date1(this.value)" />
               #if($readmode != "disabled")
                    <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/><span class="style73">dd/mm/yyyy</span>#end                    </td>
          </tr>
          <tr>
            <td><div align="right"><span class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Waktu Kematian</span> #else <span class="style72">Waktu Mati</span> #end</div></td>
              <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" onkeyup="javascript:validateIC(event,this,this.value,'txtWaktuKematianWaris')"  style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.waktumati" size="4" maxlength="4" $readmode/>
              <span class="style38 style41">#if($readmode != "disabled")</span>               <span class="style46"><span class="style42">format 24 jam (HHMM)</span> #end</span></td>
          </tr>
          #end
      </table></td>
      <td width="40%" valign="top"><table width="100%" border="0">
        <tr>
          <td class="style38" >&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td class="style38" ><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Alamat Surat Menyurat #else <span class="style72">Alamat Surat Menyurat</span> #end</div></td>
          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtAlamatTerakhir1WarisSurat" type="text" id="txtAlamatTerakhir1WarisSurat"style="text-transform:uppercase;" onblur="uppercase()"  value="$lwu.alamat1Surat" size="34" maxlength="50"  $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td>&nbsp;</td>
          <td><label>
            <input name="txtAlamatTerakhir2WarisSurat" type="text" id="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.alamat2Surat" size="34" maxlength="50" $readmode />
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td>&nbsp;</td>
          <td><input name="txtAlamatTerakhir3WarisSurat" type="text" id="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.alamat3Surat" size="34" maxlength="50" $readmode/></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Poskod #else <span class="style72">Poskod</span> #end</div></td>
          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWarisSurat')" value="$lwu.poskodSurat" size="5" maxlength="5" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span>  Bandar #else <span class="style72">Bandar</span> #end</div></td>
          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtBandarWarisSurat" type="text" id="txtBandarWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.bandarSurat" size="34" maxlength="40" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style44"><span class="style38 style41">#if($readmode != "disabled")</span> Negeri #else <span class="style72">Negeri</span> #end</div></td>
          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
          #foreach($listnegpomo in $listnegeri)
          
          #if($lwu.idnegeriSurat==$listnegpomo.id_Negeri)
          
          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
          
          
          
          #end 
          #end
          <td> #if($lwu.idnegeriSurat!="" && $lwu.idnegeriSurat!="0")
            <select name="socNegeriWarisSurat" class="autoselect" $readmode id="socNegeriWarisSurat" style="text-transform:uppercase;" onblur="uppercase()">
                <option value="$lwu.idnegeriSurat" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
              
              
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($lwu.idnegeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                
              
              <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
              
              
                
                                             
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
              
            
            </select>
            #else
            <select name="socNegeriWarisSurat" class="autoselect" $readmode id="socNegeriWarisSurat" style="text-transform:uppercase;" onblur="uppercase()">
              <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
              
              
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                
              
              <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
              
              
                
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                     
                                  
                                            
                                           
              
            
            </select>          </td>
          #end </tr>
        #if($readmode != "disabled")
        <tr>
          <td class="style38" >&nbsp;</td>
          <td>&nbsp;</td>
          <td><label>
            <input type="checkbox" name="copy" id="copy" onclick="copycopy()" />
            <span class="style73">Alamat tetap adalah alamat surat menyurat </span></label></td>
        </tr>
        #end
        <tr>
          <td class="style38" width="29%" ><div align="right" class="style38">Alamat Tetap</div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td width="70%"><label>
            <input name="txtAlamatTerakhir1Waris" type="text" id="txtAlamatTerakhir1Waris"style="text-transform:uppercase;" onblur="uppercase()"  value="$lwu.alamat1" size="34" maxlength="50"  $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td>&nbsp;</td>
          <td><label>
            <input name="txtAlamatTerakhir2Waris" type="text" id="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.alamat2" size="34" maxlength="50" $readmode />
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td>&nbsp;</td>
          <td><input name="txtAlamatTerakhir3Waris" type="text" id="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.alamat3" size="34" maxlength="50" $readmode/></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style38">Poskod</div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtPoskodWaris" type="text" id="txtPoskodWaris" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodWaris')" value="$lwu.poskod" size="5" maxlength="5" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style38">Bandar</div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtBandarWaris" type="text" id="txtBandarWaris" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.bandar" size="34" maxlength="40" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style38">Negeri</div></td>
          <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          #foreach($listnegpomo in $listnegeri)
          
          #if($lwu.idnegeri==$listnegpomo.id_Negeri)
          
          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
          
          
          
          #end 
          #end
          <td> #if($lwu.idnegeri!="" && $lwu.idnegeri!="0")
            <select name="socNegeriWaris" class="autoselect" $readmode id="socNegeriWaris" style="text-transform:uppercase;" onblur="uppercase()">
                    <option value="$lwu.idnegeri" style="text-transform:uppercase;" onblur="uppercase()">$negerikodpemoP - $negeriketeranganpemoP</option>
              
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($lwu.idnegeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                
              <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
              
                
                                             
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
              
            </select>
            #else
            <select name="socNegeriWaris" class="autoselect" $readmode id="socNegeriWaris" style="text-transform:uppercase;" onblur="uppercase()">
              <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
              
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                
              <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
              
                
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                     
                                  
                                            
                                           
              
            </select>          </td>
          #end </tr>
        <tr>
          <td class="style38" ><div align="right" class="style38">No Telefon</div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponWaris')" value="$lwu.noTel" size="14" maxlength="12" $readmode/></td>
        </tr>
        #if($readmode != "disabled")
        <tr>
          <td class="style38" valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top"><span class="style73">cth: 031234567</span></td>
        </tr>
        #end
        <tr>
          <td class="style38" ><div align="right">No Telefon Bimbit</div></td>

            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTeleponBimbitWaris')"  value="$lwu.nohp" size="14" maxlength="12" $readmode/></td>
        </tr>
        #if($readmode != "disabled")
        <tr>
          <td class="style38" valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top"><span class="style73">cth: 0121234567</span></td>
        </tr>
        #end
        <tr>
          <td class="style38" valign="top"><div align="right" class="style38">Catatan</div></td>
            <td class="style36" width="1%" valign="top"><div align="right"><span class="style38">:</span></div></td>
          <td><textarea name="txtCatatanWaris" cols="31"  rows="3" style="text-transform:uppercase;" onblur="uppercase()" $readmode>$lwu.catatan</textarea></td>
        </tr>
      </table></td>
    </tr>
  </table>
  </fieldset>  </td>
</tr>
 #if($readmode != "disabled")
   <tr>
    <td>
                              <table width="100%">
                                <tr>
                                  <td><span class="style5 style47 style69"><span class="style41">Perhatian </span>: Sila masukkan salah satu nombor pengenalan sekiranya berbeza dan pastikan label berwarna <span class="style41">merah</span> wajib diisi.</span></td>
                                </tr>
                                   <tr>
                                  <td> <span class="style74"><em>Sila masukkan salah satu nombor telefon sekiranya berbeza.</em></span></td>
                                </tr>
</table> </td>
  </tr>
#end
 #end
 
 #end
 
 
 
 
 #if($show_button_lapisan=="yes")

   #foreach($lwu in $listWarisLapisanUpdate)
  <tr>
 
    <td><table width="100%" border="0" align="center">
        <tr>
        <td align="center"><input type="submit" name="cmdKembali9" id="cmdKembali11" value="Kembali" onclick="setSelected(0,2,0,0);WarisView()"/>        </td>
        </tr>
        #end
    </table></td>
  </tr>



































#end
  #end
  <tr>
    <td>
     <fieldset>
<legend>SENARAI WARIS LAPISAN PERTAMA TERDAHULU</legend>
    
    <table width="100%">
      </table>
        <table width="100%" >
          <tr >
            <td width="100%"><div align="center">
                <table width="100%">
                  <tr class="table_header">
                  <td width="5%"><div align="center" >NO</div></td>
                    <td width="20%"><div align="left">NAMA WARIS</div></td>
                    <td width="15%"><div align="center">NO KP BARU</div></td>
                    <td width="5%"><div align="center">UMUR</div></td>
                    <td width="20%"><div align="left">TALIAN PERSAUDARAAN</div></td>
                    <td width="20%"><div align="center">STATUS</div></td>
                     <td width="15%"><div align="center">LAPISAN</div></td>
                  </tr>
            </table>      
                <input name="idwarisDulu" type="text" id="idwarisDulu" value="$listwaris.idwaris" />
                  
                  #if($listWarisDulu.size()==0)
                  <table width="100%">
                   <tr bgcolor="white">
                   <td align="left">
                   Tiada Rekod                   </td>
                  </tr>
                  </table>
                  #else
                   <table width="100%">
                   
                   #set($nowa=0)
                  #foreach($listwaris in $listWarisDulu)
                 
                 #set($nowa=$nowa+1)
                 #if($nowa%2!=0)
                  <tr >
                  
                    <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
                    <td width="20%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">
                      <div align="left"><a href="javascript:get_waris_Dulu('$listwaris.idwaris')" class="style42"> $listwaris.nama_Ob</a></div>
                    </div></td>
                    <td width="15%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.nokpbaru</div></td>
                    <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.umur</div></td>
                   
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
                    <td width="20%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">
                      <div align="left">$warissaudaraketerangan</div>
                    </div></td>
                    #if($listwaris.statushidup=="0")
                    #set($hidup="Masih Hidup")
                    #end
                    #if($listwaris.statushidup=="1")
                    #set($hidup="Sudah Meningal")
                    #end
                    #if($listwaris.statushidup=="")
                    #set($hidup="")
                    #end
                    <td width="20%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                     <td width="15%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.lapis</div></td>
                  </tr>
                  #else
                  
                   <tr class="table_header">
                    <!-- 
                                             <td><div align="center"><a href="javascript:edit_item_waris('$listwaris.idwaris', '$listwaris.nama_Ob', '$listwaris.nokpbaru1','$listwaris.nokpbaru2','$listwaris.nokpbaru3','$listwaris.idSimati','$listwaris.nokplama','$listwaris.jeniskp','$listwaris.nokplain','$listwaris.idnegeri','$listwaris.noTel','$listwaris.jantina','$listwaris.alamat1','$listwaris.alamat2','$listwaris.alamat3','$listwaris.bandar','$listwaris.agama','$listwaris.catatan','$listwaris.warga','$listwaris.poskod','$listwaris.statushidup','$listwaris.tarikhmati','$listwaris.waktumati','$listwaris.nohp','$listwaris.status_Ob','$listwaris.dob','$listwaris.saudara','$listwaris.umur','$show_table_waris')"> $listwaris.nama_Ob</a></div>
                                             -->
                    <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
                    <td width="20%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">
                      <div align="left"><a href="javascript:get_waris_Dulu('$listwaris.idwaris')" class="style43"> $listwaris.nama_Ob</a></div>
                    </div></td>
                    <td width="15%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.nokpbaru</div></td>
                    <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.umur</div></td>
                    #foreach($listsaudaralist in $listsaudara)
                    
                    #if($listwaris.saudara==$listsaudaralist.id_Saudara)
                    
                    #set($wariskodsaudara=$listsaudaralist.kod)
                    #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                    
                    
                    #end    
                    #end
                    <td width="20%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">
                      <div align="left">$warissaudaraketerangan</div>
                    </div></td>
                    #if($listwaris.statushidup=="0")
                    #set($hidup="Masih Hidup")
                    #end
                    #if($listwaris.statushidup=="1")
                    #set($hidup="Sudah Meningal")
                    #end
                    #if($listwaris.statushidup=="")
                    #set($hidup="")
                    #end
                    <td width="20%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                     <td width="15%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.lapis</div></td>
                  </tr>
                  
                  #end
                  
                  
                  
                  
                   #end
                    </table>
                 
                  #end
              
            </div></td>
          </tr>
      </table>
      </fieldset>      </td>
        </tr>
        
        <tr>
    
      <td>
      
        <fieldset>
<legend>SENARAI WARIS LAPISAN BERIKUT TERDAHULU</legend>
      <table width="100%">
      
        #set($idwww=$idparent)
       <input type="hidden" name="idwarislapisXDulu" value="$idwww" />
        <input type="hidden" name="idparentlapisXDulu" value="" />
        </table>
          <div align="center">
            <table width="100%">
              <tr class="table_header">
               <td width="5%"><div align="center">NO</div></td>
                <td width="20%"><div align="left">NAMA WARIS</div></td>
                <td width="15%"><div align="center">NO KP BARU</div></td>
                <td width="5%"><div align="center">UMUR</div></td>
                <td width="15%"><div align="left">TALIAN PERSAUDARAAN</div></td>
                <td width="20%"><div align="left">NAMA WARIS YANG MENINGGAL</div></td>
                <td width="10%"><div align="center">STATUS</div></td>
                <td width="10%"><div align="center">LAPISAN</div></td>
              </tr>
              <!--   <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" /> -->
            </table>
            #if($listWarisLapisanIdMatiDulu.size()==0)
            <table width="100%">
              <tr bgcolor="white">
                <td align="left"> Tiada Rekod </td>
              </tr>
            </table>
            #else
            <table width="100%">
            
            
            #set($nowar=0)
              #foreach($listwarislapisan in $listWarisLapisanIdMatiDulu)
              
                    ::K $listwarislapisan.idparent
               
                 #set($nowar=$nowar+1)
                 #if($nowar%2!=0)
              <tr >
              <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowar</div></td>
            <td width="20%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan_X_Dulu('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                <td width="15%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.umur</div></td>
                #foreach($listsaudaralist in $listsaudara)
                
                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                
                #set($wariskodsaudara=$listsaudaralist.kod)
                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                
                
                #end    
                #end
                <td width="15%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
               
         
                 #if($listwarislapisan.idparent=="")
                #set($idpar="")
                #else
                
                
                #foreach($lw in $listWarisOB)
                
                
                
                #if($lw.idwaris==$listwarislapisan.idparent)
                #set($idpar=$lw.nama_Ob)
                
               
                #end
                
                #end
                 #end
                <td width="20%" class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$idpar</div></td>
               
                #if($listwarislapisan.statushidup=="0")
                #set($hidup="Masih Hidup")
                #end
                #if($listwarislapisan.statushidup=="1")
                #set($hidup="Sudah Meninggal")
                #end
                #if($listwarislapisan.statushidup=="")
                #set($hidup="")
                #end
                <td width="10%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                <td width="10%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
              </tr>
              
              #else
               <tr class="table_header">
              <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowar</div></td>
            <td width="20%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan_X_Dulu('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                <td width="15%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.umur</div></td>
                #foreach($listsaudaralist in $listsaudara)
                
                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                
                #set($wariskodsaudara=$listsaudaralist.kod)
                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                
                
                #end    
                #end
                <td width="15%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                 #if($listwarislapisan.idparent=="")
                #set($idpar="")
                #else
                
                #foreach($lw in $listWarisOB)
                
                #if($lw.idwaris==$listwarislapisan.idparent)
                #set($idpar=$lw.nama_Ob)
                
               
                #end
                
                #end
                 #end
                <td width="20%" class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$idpar</div></td>
               
                #if($listwarislapisan.statushidup=="0")
                #set($hidup="Masih Hidup")
                #end
                #if($listwarislapisan.statushidup=="1")
                #set($hidup="Sudah Meninggal")
                #end
                #if($listwarislapisan.statushidup=="")
                #set($hidup="")
                #end
                <td width="10%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                <td width="10%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
              </tr>
              #end
              #end
            </table>
            #end </div>
            </fieldset>            </td>
    </tr>
  <tr>
                <td>
                <p align="right">CL - 01 - 51</p>                </td>
                </tr>
                    </table>
            
            
            
            
            
            </div>
            <!--
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            -->
          </div>
        </div>
      </div>
      <!--
      <div class="TabbedPanelsContent">
        <div id="TabbedPanels4" class="TabbedPanelsContentVisible">
         
          <div class="TabbedPanelsContentGroup">
        
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
          
          </div>
        </div>
      </div>
        -->
              <!--
      <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
      -->
    </div>
    
  </div>    </td>
  </tr>
</table>
</form>

<script>
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




function tarikh_waris(v_t)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
	document.f1.submit();
}
function tarikh_waris_saudara(v_t)
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikhsaudara";
	document.f1.command.value = "Waris";
	document.f1.v_tab.value = v_t;
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

function get_waris_Dulu(idw) {
	document.f1.action = "";
	document.f1.mode.value = "GetwarisDulu";
	document.f1.command.value = "Waris";
	document.f1.idwarisDulu.value = idw;
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
function get_waris_lapisan_X_Dulu(idw,idp) {
	document.f1.action = "";
	document.f1.mode.value = "GetwarisLapisanXDulu";
	document.f1.command.value = "Waris";
	document.f1.idwarislapisXDulu.value = idw;
    document.f1.idparentlapisXDulu.value = idp;
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
		
		
		else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0")  && document.f1.txtNoKPLainWaris.value=="")
		 {
		alert("Sila masukkan salah satu nombor kad pengenalan waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		
		
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan lain simati");
		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis kad pengenalan lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
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
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && document.f1.txtWaktuKematianWaris.value.length < 4) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 2) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >9 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
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
	 else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirWaris.focus();
   } 
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
	
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || document.f1.txtPoskodWarisSurat.value == "" || document.f1.txtBandarWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
		
		else if (document.f1.txtNoTeleponWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "" )
	{
	
	alert("Sila pastikan salah satu no telefon diisi");
	document.f1.txtNoTeleponWaris.focus()
	return;
	
	}
   
   	
	else{
		
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
		
		
		else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value=="")
		 {
		alert("Sila masukkan salah satu nombor kad pengenalan waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan lain simati");

		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis kad pengenalan lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
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
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && document.f1.txtWaktuKematianWaris.value.length < 4) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 2) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >9 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
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
	 else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirWaris.focus();
   } 
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
	
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || document.f1.txtPoskodWarisSurat.value == "" || document.f1.txtBandarWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	
	else if (document.f1.txtNoTeleponWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "" )
	{
	
	alert("Sila pastikan salah satu no telefon diisi");
	document.f1.txtNoTeleponWaris.focus()
	return;
	
	}
	
	else
	{
	
	
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


function tambah_waris_Simpan(){


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
		
		
		else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0" )  && document.f1.txtNoKPLainWaris.value=="")
		 {
		alert("Sila masukkan salah satu nombor kad pengenalan waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan lain simati");
		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis kad pengenalan lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
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
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && document.f1.txtWaktuKematianWaris.value.length < 4) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 2) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >9 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
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
	 else if(date2 < date1)
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");
	 
      document.f1.txdTarikhLahirWaris.focus();
   } 
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
	
	
	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || document.f1.txtPoskodWarisSurat.value == "" || document.f1.txtBandarWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
	else if (document.f1.txtNoTeleponWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "" )
	{
	
	alert("Sila pastikan salah satu no telefon diisi");
	document.f1.txtNoTeleponWaris.focus()
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
		
		
		else if (document.f1.txtNoKPBaru1Waris.value=="" && document.f1.txtNoKPBaru2Waris.value=="" && document.f1.txtNoKPBaru3Waris.value=="" && document.f1.txtNoKPLamaWaris.value=="" && (document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value=="")
		 {
		alert("Sila masukkan salah satu nombor kad pengenalan waris");
		document.f1.txtNoKPBaru1Waris.focus();
		
		}
		else if ((document.f1.socJenisKPLainWaris.value!="" && document.f1.socJenisKPLainWaris.value!="0" && document.f1.txtNoKPLainWaris.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan lain simati");
		document.f1.txtNoKPLainWaris.focus();
		return; 
	 }
	  else if ((document.f1.socJenisKPLainWaris.value=="" || document.f1.socJenisKPLainWaris.value=="0") && document.f1.txtNoKPLainWaris.value!="")
	 {
	 	alert("Sila pilih jenis kad pengenalan lain waris");
		document.f1.socJenisKPLainWaris.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru1Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru2Waris.value==""))
	 {
	 	
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Waris.value!="" || document.f1.txtNoKPBaru2Waris.value!="" || document.f1.txtNoKPBaru3Waris.value!="") && (document.f1.txtNoKPBaru3Waris.value==""))
	 {
	 	alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru3Waris.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Waris.value!=""  && document.f1.txtNoKPBaru1Waris.value.length < 6 ) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru1Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Waris.value!="" && document.f1.txtNoKPBaru2Waris.value.length < 2 ) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
		document.f1.txtNoKPBaru2Waris.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Waris.value!="" && document.f1.txtNoKPBaru3Waris.value.length < 4) {
		alert("Sila masukkan nombor kad pengenalan waris sepenuhnya");
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
	else if (document.f1.checkHidupWaris.checked && document.f1.txtWaktuKematianWaris.value == "") {
		alert("Sila masukkan waktu mati");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.length < 4) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) > 2) ) 
	{
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 1) && (document.f1.txtWaktuKematianWaris.value.charAt(1) >9 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}	
	else if (document.f1.checkHidupWaris.checked  && document.f1.txtWaktuKematianWaris.value != "" && (document.f1.txtWaktuKematianWaris.value.charAt(0) == 2) && (document.f1.txtWaktuKematianWaris.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.txtWaktuKematianWaris.focus();
		return; 
	}
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
	 else if(date2 < date1 )
   {
      alert("Sila pastikan tarikh lahir waris tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhLahirWaris.focus();
   } 
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
	

	 else if (document.f1.checkHidupWaris.checked == false && (document.f1.txtAlamatTerakhir1WarisSurat.value == "" || document.f1.txtPoskodWarisSurat.value == "" || document.f1.txtBandarWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0"))
	{
	alert("Sila lengkapkan alamat surat menyurat waris");
	document.f1.txtAlamatTerakhir1WarisSurat.focus()
	return;
	}
		
		else if (document.f1.txtNoTeleponWaris.value == "" && document.f1.txtNoTeleponBimbitWaris.value == "" )
	{
	
	alert("Sila pastikan salah satu no telefon diisi");
	document.f1.txtNoTeleponWaris.focus()
	return;
	
	}
		else
		{
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



function kplain1(val)
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
 function submitForm() {    
   // document.val.focus();
	
	//new Effect.ScrollTo('$val_tab');
	goTo('$val_tab');
	Effect.ScrollTo('$val_tab').focus(); return false;
	

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



function janaFaraid(id){
	  
	var url = "../x/${securityToken}/ekptg.faraid.FrmFaraid?id_permohonan="+id;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

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
