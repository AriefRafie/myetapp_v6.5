
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

.style42 {color: #0000FF}
.style43 {font-size: 10px; }
.style45 {color: #FF0000}
.style50 {font-size: 16px; color: #0000FF;}
.style53 {font-size: 9px}
.style54 {color: #000000}
.style55 {font-size: 10px; color: #FF0000; }
.style56 {font-size: 10}
.style57 {font-size: 10px; color: #000000; }
-->
</style>
</head>

<body onload="submitForm();daerah_harta();check_harta()" >
<form id="form1" name="f1" method="post" action="">
<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>


        

#if($add_new=="yes")

        #set($radio2="yes")		
		#set($checked1="checked")        
        #set($radio3="")
		#set($checked2="")
		#set($checked3="")
		#set($idhta="")	
	    #set($noHakmilik="")		    
	    #set($idSimati="")
	    #set($nopt="")
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
	    #set($namapemaju="")
	    #set($alamatpemaju1="")
	    #set($alamatpemaju2="")
	    #set($alamatpemaju3="")	    
	    #set($poskodpemaju="")
	    #set($bandarpemaju="")	    
	    #set($negeripemaju="")
	    #set($alamathta1="")
	    #set($alamathta2="")
	    #set($alamathta3="")	    
	    #set($poskodhta="")
	    #set($bandarhta="")
        #set($negerihta="")
        
        
        
	    #set($noperjanjian="")
	    #set($tarikhperjanjian="")
	    #set($namarancangan="")
	    #set($noroh="ROH")
	    #set($nolot="")	   
	    #set($nocagaran="")
        
        
        #set($listBandarTetapbyNegeri="")
        #set($listBandarSuratbyNegeri="")
        #set($listMukimbyDaerah="")
        #set($listDaerahbyNegeri="")
        
    	    	
  
#end

#if($HtaamviewX1=="yes")
    			#set($idhta="")
    		    #set($noHakmilik="")	    
    		    #set($idSimati="")
    		    #set($nopt="")
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
    	        #set($namapemaju="")
    	        #set($alamatpemaju1="")
    	        #set($alamatpemaju2="")
    	        #set($alamatpemaju3="")    	        
    	        #set($poskodpemaju="")
    	        #set($bandarpemaju="")    	        
    	        #set($negeripemaju="")
    	        #set($alamathta1="")
    	        #set($alamathta2="")
    	        #set($alamathta3="")    	        
    	        #set($poskodhta="")
    	        #set($bandarhta="")
                #set($negerihta="")
    	        #set($noperjanjian="")
    	        #set($tarikhperjanjian="")
    	        #set($namarancangan="")
    	        #set($noroh="ROH")
    	        #set($nolot="")    	        
    	        #set($nolot="")    	        
    	        #set($nocagaran="")
                
                 #set($listBandarTetapbyNegeri="")
        #set($listBandarSuratbyNegeri="")
        #set($listMukimbyDaerah="")
        #set($listDaerahbyNegeri="")
#end

#if($HtaamviewX2 == "yes")
                #set($idhta="")	
    		    #set($noHakmilik="")		    
    		    #set($idSimati="")
    		    #set($nopt="")
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
    	        #set($namapemaju="")
    	        #set($alamatpemaju1="")
    	        #set($alamatpemaju2="")
    	        #set($alamatpemaju3="")
    	        #set($poskodpemaju="")
    	        #set($bandarpemaju="")
    	        #set($negeripemaju="")
    	        #set($alamathta1="")
    	        #set($alamathta2="")
    	        #set($alamathta3="")
    	        #set($poskodhta="")
                #set($negerihta="")
    	        #set($bandarhta="")
    	        #set($noperjanjian="")
    	        #set($tarikhperjanjian="")
    	        #set($namarancangan="")
    	        #set($noroh="ROH")
    	        #set($nolot="")
    	        #set($nocagaran="")
                
                 #set($listBandarTetapbyNegeri="")
        #set($listBandarSuratbyNegeri="")
        #set($listMukimbyDaerah="")
        #set($listDaerahbyNegeri="")
#end

#if($HtaamviewX3 == "yes")

        #set($idhta="")	
    	#set($noHakmilik="")		    
    	#set($idSimati="")
    	#set($nopt="")
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
    	#set($namapemaju="")
    	#set($alamatpemaju1="")
    	#set($alamatpemaju2="")
    	#set($alamatpemaju3="")
    	#set($poskodpemaju="")
    	#set($bandarpemaju="")
    	#set($negeripemaju="")
    	#set($alamathta1="")
    	#set($alamathta2="")
    	#set($alamathta3="")
    	#set($poskodhta="")
    	#set($bandarhta="")
        #set($negerihta="")
    	#set($noperjanjian="")
    	#set($tarikhperjanjian="")
    	#set($namarancangan="")
    	#set($noroh="ROH")
    	#set($nolot="")
    	#set($nolot="")
    	#set($nocagaran="")
    	#set($jeniskepentingan="")
        
        
         #set($listBandarTetapbyNegeri="")
        #set($listBandarSuratbyNegeri="")
        #set($listMukimbyDaerah="")
        #set($listDaerahbyNegeri="")

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


 


#foreach($listFail in $ViewFail)
<input name="id_Suburusanstatus" type="hidden"  value="$listFail.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$listFail.id_Suburusanstatusfail"/>
#end

#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
    <input name="idpermohonan" type="hidden"  value="$id"/>
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
       
        <input name="jpphlepas" type="hidden"  value="$list.jpphlepas"/>
       
       
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
<!--
<fieldset>
<legend>MAKLUMAT PERMOHONAN</legend>


<table width="100%" border="0" align="center">
  <tr>
    <td valign="top" width="55%"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">No Fail</div></td>
          <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
          <td width="64%" style="text-transform:uppercase;"><div align="left" class="style40 style42">$listnoFail</div></td>
          #set($NO_FAIL = $listnoFail)
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Negeri</div></td>
          <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
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
          <td valign="top" style="text-transform:uppercase;"><div align="right">Daerah / Jajahan</div></td>
          <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listnamadaerah</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Unit</div></td>
          <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listnamaPejabat</span></div></td>
        </tr>
      </table>
    </div></td>
    <td width="45%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Status Fail</div></td>
          <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
          <td width="64%" style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listketerangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen</div></td>
          <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listseksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Tarikh Mohon</div></td>
           <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listtarikhMohon</span><span class="style42">
          #set($md=$listtarikhMohon)</span>
                <input type="hidden" name="txdTarikhMohon" id="txdTarikhMohon" value="$listtarikhMohon" />
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon</div></td>
           <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40 style42">$listnamaPemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati</div></td>
           <td width="1%" style="text-transform:uppercase;"><div align="center" >:</div></td>
          <td style="text-transform:uppercase;"><span class="style40 style42">$listnamaSimati</span>
            <input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/></td>
        </tr>
      </table>
    </div></td>
  </tr>
</table>

</fieldset>
-->
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
            <div ></div>
            
            <div >
              <div id="TabbedPanels3" >
              
                <div class="TabbedPanelsContentGroup">
                  <div ></div>
                   <div  ></div>
                </div>
              </div>
            </div>
            
            <div >    
            
            
            
            
            
            </div>
            <div ></div>
            <div></div>
            <div ></div>
            <div ></div>
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
            <div >
            
            
                          
            </div>
            <div class="TabbedPanelsContent"> 
            
            <table width="100%"  border="0">
                                  #if($show_htaa_add_table=="yes")
          <tr>
                                    <td><table width="100%" border="0">
                                        <tr>
                                                                                   
                                            <td>
                                            <fieldset><legend>HARTA TAK ALIH (TIADA HAKMILIK)</legend>
                                            <input name="noradio" type="hidden"  />
                                            <table width="70%" align="center">
                                              <tr>
                                                <td><span class="style36">
                                                  <input name="radioHtaamViewX" id="radioHtaamViewX1" type="radio" onclick="setSelected(1,0,0,1);HtaamViewX1()"   $checked1 value="1" />
                                                   
                                                  Perjanjian Jual Beli</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                        <input type="radio" name="radioHtaamViewX"  $checked2 id="radioHtaamViewX2" onclick="setSelected(1,0,0,1);HtaamViewX2()" value="2" />
                                   
                                                  Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                              <input type="radio" name="radioHtaamViewX" id="radioHtaamViewX3" onclick= "setSelected(1,0,0,1);HtaamViewX3()" $checked3 value="3" />
                                              
                                                  Kepentingan Lain- lain</span></td>
                                              </tr>
                                            </table>
                                            </fieldset>                                          </td>
                                        </tr>
                                    </table></td>
                      </tr>
                                  
                                  
                                  
                                  <tr>
                                    <td>
                                    
                                   <fieldset>
                                   
                                   <table width="100%" border="0">
                                      <tr>
                                        <td width="50%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td valign="top"><span class="style55">*</span></td>
                                              <td width="29%"><div align="right" class="style43 style54">
                                                <div align="left">Negeri </div>
                                              </div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td width="70%">#foreach($listnegpomo in $listnegeri)
                                                
                                                #if($negeri==$listnegpomo.id_Negeri)
                                                
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($negeri!="")
                                                
                                               
                                                
                                                
                                                <select name="socNegeriHtaamX" class="autoselect" $readmodenegeri onchange="setSelected(1,0,0,1);negerichangeX('socDaerahHtaamX')" style="text-transform:uppercase;" onblur="uppercase()">
                                                  <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                                  
                                                  
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            
                                                  
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                                                </select>
                                                #else
                                                <select name="socNegeriHtaamX" class="autoselect" $readmodenegeri onchange="setSelected(1,0,0,1);negerichangeX('socDaerahHtaamX')" style="text-transform:uppercase;" onblur="uppercase()">
                                                  <option value="">Sila Pilih Negeri</option>
                                                  

  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

                                                </select>
                                                #end </td>
                                          </tr>
                                            <tr>
                                              <td valign="top"><span class="style55">*</span></td>
                                              <td><div align="right" class="style43" >
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
                                               
                                                <select name="socDaerahHtaamX" id="socDaerahHtaamX" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,1);daerahchangeX('socMukimHtaamX');daerah_harta();check_harta()" style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckDaerah()" >
                                                        <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                                  
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            
                                                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                                  
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socDaerahHtaamX" id="socDaerahHtaamX" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,1);daerahchangeX('socMukimHtaamX');daerah_harta();check_harta()" style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckDaerah()" >
                                                  <option value="">Sila Pilih Daerah</option>
                                                  
  
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
  
                                                  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                                  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                                </select>
                                                #end 
                                                  <span id="check_daerah_harta" style="color:red" ></span>
                                                
                                                </td>
                                            </tr>
                                            <tr>
                                              <td valign="top"><span class="style55">*</span></td>
                                              <td><div align="right" class="style43" >
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
                                               
                                                <select name="socMukimHtaamX" id="socMukimHtaamX" class="autoselect" $readmodemukim style="text-transform:uppercase;" onblur="uppercase()" onfocus="CheckMukim()">
                                                  <option value="$mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                                  
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($mukim!=$listmukim.id)
                                    
                                                  <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socMukimHtaamX" class="autoselect" $readmodemukim id="socMukimHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onfocus="CheckMukim()" >
                                                  <option value="">Sila Pilih Mukim</option>
                                                  
  
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
  
                                                  <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
                                                  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



                                                </select>
                                                #end </td>
                                            </tr>
                                            
                                               #if($radio2=="yes")
                                            
                                            <tr>
                                            <td>&nbsp;</td>
                                            <td><div align="right" class="style43">
                                              <div align="left">Alamat Harta</div>
                                            </div></td>
                                            <td><div align="right">:</div></td>
                                            <td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield57" value="$alamathta1" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td><div align="left"><span class="style43"></span></div></td>
                                            <td>&nbsp;</td>
                                            <td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield58" size="34" $readmode value="$alamathta2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" /></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td><div align="left"><span class="style43"></span></div></td>
                                            <td>&nbsp;</td>
                                            <td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield59" value="$alamathta3" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td><div align="right" class="style43">
                                              <div align="left">Poskod</div>
                                            </div></td>
                                            <td><div align="right">:</div></td>
                                            
                                            <td><input name="txtPoskodHartaHtaamX" type="text" id="textfield60" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodHartaHtaamX')" value="$poskodhta" size="5" maxlength="5" $readmode/></td>
                                          </tr>
                                          <tr>
                                            <td class="style38 style43 style45">&nbsp;</td>
                                    <td class="style38"><div align="right" class="style38 style43">
                                      <div align="left"><span class="style40"> Bandar</span></div>
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
                #if($bandarhta==$listdaerah.id)                                
                #set($listDaerahbyNegeriK=$listdaerah.kod)
                #set($listDaerahbyNegeriN=$listdaerah.nama)
                #end 
                #end
                
                
                #if($disabled=="disabled")
                #set($readmodedaerah="disabled")
                #end
                #if($bandarhta!="" && $bandarhta!="0" )
                <select name="txtBandarHartaHtaamX" id="txtBandarHartaHtaamX" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                  <option value="$bandarhta">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                  #if($bandarhta!=$listdaerah.id)
                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                #end    
	            #end
                                  
                </select>
#else
<select name="txtBandarHartaHtaamX" id="txtBandarHartaHtaamX" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
  <option value="">Sila Pilih Bandar</option>
  
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                 
	                               #end
</select>
#end </label></td>
                                  </tr>
                                            
                                            #end
                                            
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td><div align="right" class="style43">
                                                <div align="left">
                                                #if($radio3 == "yes")
                                               Agensi
                                                #else
                                                 Nama Pemaju
                                                #end
                                                
                                                </div>
                                              </div></td>
                                             <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" value="$namapemaju" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td><div align="right" class="style43">
                                                <div align="left">
                                           
                                                
                                                  #if($radio3 == "yes")
                                           Alamat Agensi
                                                #else
                                                  Alamat Pemaju
                                                #end
                                              
                                                
                                                
                                                </div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtAlamatPemaju1HtaamX" type="text" id="textfield20" value="$alamatpemaju1" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td><div align="left"><span class="style43"></span></div></td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield53" value="$alamatpemaju2" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td><div align="left"><span class="style43"></span></div></td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield54" value="$alamatpemaju3" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td><div align="right" class="style43">
                                                <div align="left">Poskod</div>
                                              </div></td>
                                             <td><div align="right">:</div></td>
                                            
                                              <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield55" value="$poskodpemaju" size="5" maxlength="5" $readmode style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPemaju1HtaamX')"/></td>
                                            </tr>
                                           <tr>
                                             <td>&nbsp;</td>
                                              <td><div align="right" class="style43">
                                                <div align="left">Negeri</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listnegpomo in $listnegeri)
                                                
                                                #if($negeripemaju==$listnegpomo.id_Negeri)
                                                
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($negeripemaju!="")
                                                <select name="socNegeriPemajuHtaamX" class="autoselect" $readmode id="socNegeriPemajuHtaamX" style="text-transform:uppercase;" onblur="uppercase()"  onchange="setSelected(1,0,0,1);negerichangepemajuX('txtBandarPemaju1HtaamX')" >
                                                  <option value="$negeripemaju">$negerikodpemoP - $negeriketeranganpemoP</option>
                                                  
                                            
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeripemaju!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            
                                            
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                      
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socNegeriPemajuHtaamX" class="autoselect" $readmode id="socNegeriPemajuHtaamX" style="text-transform:uppercase;" onblur="uppercase()"  onchange="setSelected(1,0,0,1);negerichangepemajuX('txtBandarPemaju1HtaamX')">
                                                  <option value="">Sila Pilih Negeri</option>
                                                  
  
  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  
  
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                                </select>
                                                #end</td>
                                            </tr>
                                            <tr>
                                              <td class="style38 style43 style45">&nbsp;</td>
                                    <td class="style38"><div align="right" class="style43">
                                      <div align="left">Bandar</div>
                                    </div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>#if($bandarpemaju == "" || $bandarpemaju=="0")
                #set($kodb="")
                
                #else
                
                #foreach($listneg in $listBandarTetapbyNegeri)                 
                
                #if($bandarpemaju==$listneg.id)                                      
                #set($kodb="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
                #end    
              
                
                #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                #if($bandarpemaju==$listdaerah.id)                                
                #set($listDaerahbyNegeriK=$listdaerah.kod)
                #set($listDaerahbyNegeriN=$listdaerah.nama)
                #end 
                #end
                
                
                #if($disabled=="disabled")
                #set($readmodedaerah="disabled")
                #end
                #if($bandarpemaju!="" && $bandarpemaju!="0" )
                <select name="txtBandarPemaju1HtaamX" id="txtBandarPemaju1HtaamX" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                  <option value="$bandarpemaju">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)                                 
                                  #if($bandarpemaju!=$listdaerah.id)
                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                  #end    
	                               #end
                </select>
#else
<select name="txtBandarPemaju1HtaamX" id="txtBandarPemaju1HtaamX" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
  <option value="">Sila Pilih Bandar</option>  
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>                                 
	                               #end
</select>
#end </label></td>
                                  </tr>
                                            
                                            
                                            <!--
                                            <tr>
                                              <td><div align="right" class="style44">Bandar</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtBandarPemaju1HtaamX" type="text" id="textfield56" value="$bandarpemaju" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                            </tr>
                                            -->
                                            
                                          #if($radio2=="yes")
                                          
                                          <!--
                                          <tr>
                                            <td><div align="right" class="style44">Bandar</div></td>
                                           <td><div align="right">:</div></td>
                                            <td><input name="txtBandarHartaHtaamX" type="text" id="textfield61" value="$bandarhta" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                          </tr>
                                          -->
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td><div align="right" class="style43">
                                              <div align="left">No Perjanjian</div>
                                            </div></td>
                                           <td><div align="right">:</div></td>
                                            <td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield62" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$noperjanjian" size="34" maxlength="40" $readmode/></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td><div align="right" class="style43">
                                              <div align="left">Tarikh Perjanjian</div>
                                            </div></td>
                                           <td><div align="right">:</div></td>
                                            <td><span class="style36">
                                              <input name="txtTarikhPerjanjianHtaamX" id="txtTarikhPerjanjianHtaamX" type="text" $readmode value="$tarikhperjanjian" size="10"  onblur="trans_date(this.value)" />
                                              <a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');"><img border="0" src="../img/calendar.gif"/>                                            </td>
                                          </tr>
                                          #end
                                          
                                          #if($radio3=="yes")
                                          <tr>
                                            <td>&nbsp;</td>
                                                        <td><div align="right" class="style43">
                                                          <div align="left">Nama Rancangan</div>
                                                        </div></td>
                                                        <td><div align="right">:</div></td>
                                            <td><label>
                                                          <input name="txtNamaRancanganHtaamX" type="text" id="textfield63" value="$namarancangan" size="34" $readmodevalue="$namarancangan" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                                        </label></td>
                                          </tr>
                                                      <tr>
                                                        <td>&nbsp;</td>
                                                        <td><div align="right" class="style43">
                                                          <div align="left">No ROH</div>
                                                        </div></td>
                                                        <td><div align="right">:</div></td>
                                                        <td><input name="txtNoRohHtaamX" type="text" id="txtNoRohHtaamX" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$noroh" size="34" maxlength="50" $readmode/></td>
                                                      </tr>
                                                      <tr>
                                                        <td>&nbsp;</td>
                                                        <td><div align="right" class="style43">
                                                          <div align="left">Lot ID</div>
                                                        </div></td>
                                                        <td><div align="right">:</div></td>
                                                        <td><input name="txtLotIdHtaamX" type="text" id="txtLotIdHtaamX" $readmode value="$nolot" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                                      </tr>
                                                       #end
                                                       
                                                     #if($radio3 != "yes" && $radio2 != "yes")  
                                                      <tr>
                                                        <td valign="top">&nbsp;</td>
                                                      <td valign="top"><div align="right" class="style43">
                                                        <div align="left">Jenis Kepentingan</div>
                                                      </div></td>
                                                      <td valign="top"><div align="right">:</div></td>
                                                      <td>
                                                      <textarea name="txtJenisKepentinganX" id="txtJenisKepentinganX" cols="31" rows="5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()">$jeniskepentingan</textarea>                                                      </td>                                                      
                                                      </tr>
                                                      
                                                      #end
                                                      
                                                      
                                         
                                        </table></td>
                                        <td width="50%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td valign="top"><span class="style55">*</span></td>
                                              <td width="29%"><div align="right" class="style57">
                                                <div align="left">Bahagian Simati</div>
                                              </div></td>
                                              
                                             <td width="1%"><div align="right">:</div></td>
                                              <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati7" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati1X')" style="text-align:right;text-transform:uppercase;" value="$basimati" size="14" maxlength="14" $readmode  onblur="bahagiansimati()" />
                                                /
                                                <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati8" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati2X')" style="text-align:left;text-transform:uppercase;" value="$bbsimati" size="14" maxlength="14" $readmodevalue="$bbsimati" onblur="bahagiansimati()" /></td>
                                            </tr>
                                            
                                            
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Kategori Tanah</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>
                                              
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
                                                <select name="socKategoriTanahHtaamX" class="autoselect" $readmode id="socKategoriTanahHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaamX','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')"  >
                                                  <option value="$kategori">$listkategoriK - $listkategoriN</option>
                                                  
                                            
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socKategoriTanahHtaamX" class="autoselect" $readmode id="socKategoriTanahHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaamX','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')" >
                                                  <option value="">Sila Pilih Kategori Tanah</option>
                                                  
  
  
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
  
  
                                                  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                                  
  
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            






                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57"> 
                                                <div align="left">Luas Asal</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listluashta in $listluas)
                                                
                                                #if($jenisluas==$listluashta.id)
                                                
                                                #set($listluasK=$listluashta.kod)
                                                #set($listluasN=$listluashta.nama)
                                                #end 
                                                #end
                                                #if($jenisluas!="")
                                                
                                                <input name="txtLuasAsalHtaamX" type="text"  class="disabled" id="txtLuasAsalHtaamX" style="text-transform:uppercase;"  onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamX')" value="$luasasal" size="15" maxlength="15" readonly="readonly"  />
                                                
                                                #else
                                                <input name="txtLuasAsalHtaamX" type="text"  class="disabled" id="txtLuasAsalHtaamX" style="text-transform:uppercase;"  onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamX')"  value="$luasasal" size="15" maxlength="15" readonly="readonly" />
                                                #end </td>
                                          </tr>
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57"> 
                                                <div align="left">Jenis Luas</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listluashta in $listluas)
                                                
                                                #if($jenisluas==$listluashta.id)
                                                
                                                #set($listluasK=$listluashta.kod)
                                                #set($listluasN=$listluashta.nama)
                                                #end 
                                                #end
                                                #if($jenisluas!="")
                                                
                                                
                                                <select name="socJenisLuasHtaamX" class="autoselect" $readmode id="socJenisLuasHtaamX" style="text-transform:uppercase;" onblur="uppercase()"  onchange="pilih_jenis_luas('socJenisLuasHtaamX','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')" >
                                                  <option value="$jenisluas">$listluasK - $listluasN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                      
                                                #else
                                                
                                                <select name="socJenisLuasHtaamX" class="autoselect" $readmode id="socJenisLuasHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaamX','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')" >
                                                  <option value="">Sila Pilih Jenis Luas</option>
                                                  
  
  
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
  

  
                                                  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                                  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                                </select>
                                              #end </td>
                                          </tr>
                                            <tr id="tr_luasharta" style="display:none;">
                                             <td>&nbsp;</td>
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
                                              <td>&nbsp;</td>
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input type="button" name="button2" id="button2" value="Convert" onclick="ConvertLuasHarta('socJenisLuasHtaamX','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','socKategoriTanahHtaamX')" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Luas (Hektar/MP)</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtLuasHMpHtaamX" type="text" id="txtLuasHMpHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtLuasHMpHtaamX')" value="$luashmp" size="15" maxlength="15" $readmode  />
                                              <input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" /></td>
                                            </tr>
                                            
                                            
                                            
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Nilai Tarikh Mohon (RM)</div>
                                              </div></td>
                                            <td><div align="right">:</div></td>
                                              <td>
                                              
                                              #if($nilai_Hta_memohon=="") 
                                              
                                              	#set($n2="")
                                              
                                              #elseif(($nilai_Hta_memohon==0) || (""+$nilai_Hta_memohon.equals("0.0")))
                                              
                                              	#set($n2="0.00")
                                                                                    
                                              #else
                                              	
                                              	#set($n2=$nilai_Hta_memohon)
                                             
                                              #end
                                              
                                             
                                              
                                                 <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaaX" $readmode value="$n2" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaaX')" onblur="validateModal(this,this.value,'$n2');samakan1()" />                                              </td>
                                            </tr>
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Nilai Tarikh Mati (RM)</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>
                                              #if($nilai_Hta_mati=="")
                                              	#set($n1="")
                                              #elseif(($nilai_Hta_mati==0) || (""+$nilai_Hta_mati.equals("0.0")))
                                              	#set($n1="0.00")
                                              #else
                                             	 #set($n1=$nilai_Hta_mati)                                             
                                              #end
                                            
                                           
                                            
                                               <input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamX" $readmode value="$n1"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamX')"   onblur="validateModal(this,this.value,'$n1');" />                                            </td>
                                            </tr>
                                            
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">No Cagaran</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtNoCagaranX" type="text" id="txtNoPajakan5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$nocagaran" size="15" maxlength="12" $readmode/></td>
                                            </tr>
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">No Pajakan</div>
                                              </div></td>
                                             <td><div align="right">:</div></td>
                                              <td><input name="txtNoPajakanX" type="text" id="txtNoPajakan6" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$nopajakan" size="15" maxlength="12" $readmode/></td>
                                            </tr>
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Status Pemilikan</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listpemilik in $listpemilikan)
                                                
                                                #if($pemilikan==$listpemilik.id)
                                                
                                                #set($listpemilikK=$listpemilik.kod)
                                                #set($listpemilikN=$listpemilik.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($pemilikan!="")
                                              
                                                <select name="socStatusPemilikanHtaamX" class="autoselect" $readmode id="socStatusPemilikanHtaamX" style="text-transform:uppercase;" onblur="uppercase()">
                                                  <option value="$pemilikan">$listpemilikK - $listpemilikN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                  #if($pemilikan!=$listpemilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socStatusPemilikanHtaamX" class="autoselect" $readmode id="socStatusPemilikanHtaamX" style="text-transform:uppercase;" onblur="uppercase()">
                                                  <option value="">Sila Pilih Status Pemilikan</option>
                                                  
  
    
  
  
  
  
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                
	                               
                                              
  
  
  
  
    
  
                                                  <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                                  
  
    
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




  

                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Tanggungan</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtTanggunganHtaamX" type="text" id="txtTanggunganHtaam4" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$tanggungan" size="15" maxlength="15"/></td>
                                            </tr>
                                            <tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Jenis Tanah</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listtan in $listtanah)
                                                
                                                #if($jenistanah==$listtan.id)
                                                
                                                #set($listtanK=$listtan.kod)
                                                #set($listtanN=$listtan.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($jenistanah!="" && $jenistanah!="0")
                                                  <select name="socJenisTanahHtaamX" class="autoselect" $readmode id="socJenisTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              
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
                                                <select name="socJenisTanahHtaamX" class="autoselect" $readmode id="socJenisTanahHtaamX" style="text-transform:uppercase;" onblur="uppercase()">
                                                  <option value="0">Sila Pilih Jenis Tanah</option>
                                                  
     
   												 <option value="1">TANAH RIZAB</option>
                                                  <option value="2">TANAH ADAT</option>
                                                  <option value="3">TANAH GSA</option>
                                                     <option value="0">TIDAK DINYATAKAN</option>
                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td valign="top"><span class="style56"></span></td>
                                              <td valign="top"><div align="right" class="style57">
                                                <div align="left">Catatan</div>
                                              </div></td>
                                              <td valign="top"><div align="right">:</div></td>
                                              <td><textarea name="txtCatatanHtaamX" id="txtCatatanHtaamX" cols="31" rows="5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" >$catatan</textarea></td>
                                            </tr>
                                        </table></td>
                                      </tr>
                                   </table>
                                   </fieldset>       <table width="100%">
                                <tr>
                                  <td><span class="style69 style47 style5 style53"><em><span class="style45">Perhatian</span> : Sila  pastikan label bertanda <span class="style45">*</span> diisi.</em></span></td>
                              </tr>
</table>
                                   
                                    </td>
                                  </tr>
                                  #end
                                    #if($show_htaa_update_table=="yes")
                                      #foreach($listam in $listHTAXid)
                                   <tr>
                                   <td>
                                   <fieldset><legend>MAKLUMAT HARTA TAK ALIH (TIADA HAKMILIK)</legend>
                                   #if($readmode == "disabled") #set($readmodeR = "readonly") #else #set($readmodeR = "") #end
                                   
                                   <table width="90%" align="center">
                                   <input type="hidden" name="flag" value="$listam.flag">
                                
                                     #set($idhta = $listam.idhta)
                                       #if($listam.flag=="1")
                                      
                                    
                                       <tr>                                       
                                         <td><div align="center" class="style50"><span  style="text-transform:uppercase;"  >
                                        PERJANJIAN JUAL BELI                                     </span></div></td>
                                       </tr>
                                       #end
                                       #if($listam.flag=="2")
                                       <tr>
                                         <td><div align="center" class="style42"  style="text-transform:uppercase;; font-size: 16px;">Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</div></td>
                                       </tr>
                                       #end
                                       #if($listam.flag=="3")
                                       <tr>
                                         <td><div align="center" class="style42"  style="text-transform:uppercase;; font-size: 16px;">
                                         Kepentingan Lain- lain                                           </div></td>
                                       </tr>
                                       #end
                                   </table>
                                   </fieldset>                                     </td>
                                  </tr>  
                                    
                                  <tr>
                                  
                                    <td>
                                  <fieldset>
                                  <table width="100%">
                                        <tr>
                                          <td width="50%" valign="top"><table width="100%">
                                              <tr>
                                                <td valign="top"><span class="style55">#if($readmode != "disabled")*#end</span></td>
                                                <td width="29%"><div align="right" class="style57">
                                                  <div align="left">#if($readmodenegeri != "disabled")Negeri#else Negeri#end</div>
                                                </div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td width="70%">#foreach($listnegpomo in $listnegeri)
                                          
                                          #if($listam.negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          
                                            #if($readmode == "disabled")
                              
                               #if($listam.negeri!="" && $listam.negeri!="0" )
                                     <input name="n" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="n" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                                          
                                          
                                          #if($listam.negeri!="")
                                         <input type="hidden" name="idhtaamid" value="$listam.idhta" />
                                          #set($idhta = $listam.idhta)
                                         
                                          <select name="socNegeriHtaamX" class="autoselect" $readmodenegeri onchange="setSelected(1,0,0,1);negerichangeupX('socDaerahHtaamX')" style="text-transform:uppercase;" onblur="uppercase()">
                                            <option value="$listam.negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listam.negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          </select>
#else
<select name="socNegeriHtaamX" class="autoselect" $readmodenegeri onchange="setSelected(1,0,0,1);negerichangeupX('socDaerahHtaamX')" style="text-transform:uppercase;" onblur="uppercase()">
  <option value="">Sila Pilih Negeri</option>
  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
</select>
#end 
#end</td>
                                              </tr>
                                              <tr>
                                                 <td valign="top"><span class="style55">#if($readmode != "disabled")*#end</span></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">#if($readmodedaerah != "disabled")Daerah#else Daerah#end</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listdaerah in $listdaerah)
                                          
                                          #if($listam.daerah==$listdaerah.id)
                                          
                                          #set($listDaerahbyNegeriK=$listdaerah.kod)
                                          #set($listDaerahbyNegeriN=$listdaerah.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          
                                          
                                 #if($readmode == "disabled")
                              
                               #if($listam.daerah!="" && $listam.daerah!="0" )
                                     <input name="d" value="$listDaerahbyNegeriK - $listDaerahbyNegeriN" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="d" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                                          
                                          
                                          #if($listam.daerah!="")
                                          <select name="socDaerahHtaamX" id="socDaerahHtaamX" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,1);daerahchangeupX('socMukimHtaamX');daerah_harta();check_harta()" style="text-transform:uppercase;" onblur="uppercase()">
                                            <option value="$listam.daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($listam.daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          </select>
#else
<select name="socDaerahHtaamX" id="socDaerahHtaamX" class="autoselect" $readmodedaerah onchange="setSelected(1,0,0,1);daerahchangeupX('socMukimHtaamX');daerah_harta();check_harta()" style="text-transform:uppercase;" onblur="uppercase()">
  <option value="">Sila Pilih Daerah</option>
  
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

</select>
#end

#end 

 #if($readmode != "disabled")
                     <span id="check_daerah_harta" style="color:red" ></span>
                   #end   

</td>
                                              </tr>
                                              <tr>
                                                 <td valign="top"><span class="style55">#if($readmode != "disabled")*#end</span></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">#if($readmodemukim != "disabled")Mukim#else Mukim#end</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listmukim in $listMukimbyDaerah)
                                          
                                          #if($listam.mukim==$listmukim.id) 
                                          
                                          #set($listMukimbyDaerahK=$listmukim.kod)
                                          #set($listMukimbyDaerahN=$listmukim.nama)
                                          #end 
                                          #end
                                          
                                           #if($readmode == "disabled")
                              
                               #if($listam.mukim!="" && $listam.mukim!="0" )
                                     <input name="m" value="$listMukimbyDaerahK - $listMukimbyDaerahN" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="m" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                                          
                                          
                                          #if($listam.mukim!="")
                                            <select name="socMukimHtaamX" id="socMukimHtaamX" class="autoselect" $readmodemukim  style="text-transform:uppercase;" onblur="uppercase()" onfocus="CheckMukimU()" >
                                            <option value="$listam.mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>    
                                           #foreach($listmukim in $listMukimbyDaerah)                                 
                                           #if($listam.mukim!=$listmukim.id)                                            
                                            <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
                                           #end    
	                                       #end
                                  
                                          </select>
                                  #else
                                  <select name="socMukimHtaamX" id="socMukimHtaamX" class="autoselect" $readmodemukim style="text-transform:uppercase;" onblur="uppercase()" onfocus="CheckMukimU()" >
                                  <option value="">Sila Pilih Mukim</option>
                                  #foreach($listmukim in $listMukimbyDaerah)
                                  <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
	                              #end
                                  </select>
                                  #end 
                                  #end                                  </td>
                                            </tr>
                                            
                                            #if($listam.flag=="1")
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Alamat Harta</div>
                                                </div></td>
                                               <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield44" value="$listam.alamathta1" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="left"></div></td>
                                                <td>&nbsp;</td>
                                                <td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield45" value="$listam.alamathta2" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="left"></div></td>
                                                <td>&nbsp;</td>

                                                <td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield46" value="$listam.alamathta3" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Poskod</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtPoskodHartaHtaamX" type="text" id="textfield47" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodHartaHtaamX')" value="$listam.poskodhta" size="5" maxlength="5" $readmodeR class="$readmode" /></td>
                                              </tr>
                                              <tr>
                                                <td class="style38 style43 style45">&nbsp;</td>
                                    <td class="style38"><div align="right" class="style57">
                                      <div align="left">Bandar</div>
                                    </div></td>
                                   
                                    
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                                  #if($readmode == "disabled")
                                    #foreach($listneg in $listBandarSuratbyNegeri)      
                
                
                #if($listam.bandarhta==$listneg.id)                                      
                #set($kodss="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
            
                                    
                                     #if($listam.bandarhta!="" && $listam.bandarhta!="0" )
                                     <input name="ntbb" value="$kodss" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="ntbb" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                                    
                                   
                                   #else
                                    
                                    
                                    #if($listam.bandarhta == "" || $listam.bandarhta=="0")
                #set($kodbx="")
                
                #else
                
                #foreach($listneg in $listBandarSuratbyNegeri)      
                
                
                #if($listam.bandarhta==$listneg.id)                                      
                #set($kodbx="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
                #end
                
                
                
              
                
                #foreach($listdaerah in $listBandarSuratbyNegeri)                                
                #if($listam.bandarhta==$listdaerah.id)                                
                #set($listDaerahbyNegeriK=$listdaerah.kod)
                #set($listDaerahbyNegeriN=$listdaerah.nama)
                #end 
                #end
                
                
                #if($disabled=="disabled")
                #set($readmodedaerah="disabled")
                #end
                #if($listam.bandarhta!="" && $listam.bandarhta!="0" )
                <select name="txtBandarHartaHtaamX" id="txtBandarHartaHtaamX" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
                  <option value="$listam.bandarhta">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)                                 
                                  #if($listam.bandarhta!=$listdaerah.id)
                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                  #end    
	                               #end
                                  
                </select>
#else
<select name="txtBandarHartaHtaamX" id="txtBandarHartaHtaamX" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
  <option value="">Sila Pilih Bandar</option>
  
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
	                               #end
</select>
#end
#end
 </label></td>
                                  </tr>
                                              
                                              #end
                                              <tr>

                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">
                                                  
                                                   #if($listam.flag=="2")
                                                    Nama Agensi
                                                   #else
                                                    Nama Pemaju
                                                   #end
                                                 </div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><label>
                                                  <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" style="text-transform:uppercase;" onblur="uppercase()" value="$listam.namapemaju" size="34" $readmodeR class="$readmode" />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">
                                                 
                                                   #if($listam.flag=="2")
                                                    Alamat Agensi
                                                   #else
                                                    Alamat Pemaju
                                                   #end
                                                 
                                                  </div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju1HtaamX" type="text" id="textfield21" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.alamatpemaju1" size="34" $readmodeR class="$readmode" />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="left"></div></td>
                                                <td>&nbsp;</td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield22" value="$listam.alamatpemaju2" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="left"></div></td>
                                                <td>&nbsp;</td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield23" value="$listam.alamatpemaju3" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Poskod</div>
                                                </div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield24" value="$listam.poskodpemaju" size="5" maxlength="5"  $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPemaju1HtaamX')"/></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Negeri</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listnegpomo in $listnegeri)
                                          
                                          #if($listam.negeripemaju==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          
                                          
                                          
                                           #if($readmode == "disabled")
                              
                               #if($listam.negeripemaju!="" && $listam.negeripemaju!="0" )
                                     <input name="nnn" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="nnn" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                                          #if($listam.negeripemaju!="")
                                          <select name="socNegeriPemajuHtaamX" class="autoselect" $readmode id="socNegeriPemajuHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onchange="setSelected(1,0,0,1);negerichangepemajuupX('txtBandarPemaju1HtaamX')" >
                                            <option value="$listam.negeripemaju">$negerikodpemoP - $negeriketeranganpemoP</option>
                                            
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listam.negeripemaju!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            
                                            <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          </select>
#else
<select name="socNegeriPemajuHtaamX" class="autoselect" $readmode id="socNegeriPemajuHtaamX" style="text-transform:uppercase;" onblur="uppercase()"  onchange="setSelected(1,0,0,1);negerichangepemajuupX('txtBandarPemaju1HtaamX')" >
  <option value="">Sila Pilih Negeri</option>
  
  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  
  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

</select>
#end

#end</td>
                                              </tr>
                                              <tr>
                                                <td class="style38 style43 style45">&nbsp;</td>
                                    <td class="style38"><div align="right" class="style57">
                                      <div align="left">Bandar</div>
                                    </div></td>
                                   
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                  
                                     #if($readmode == "disabled")
                                   
                           
                #foreach($listneg in $listBandarTetapbyNegeri)      
                
                
                #if($listam.bandarpemaju==$listneg.id)                                      
                #set($kodbbb="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
            
                                    
                                     #if($listam.bandarpemaju!="" && $listam.bandarpemaju!="0" )
                                     <input name="ntbb" value="$kodbbb" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="ntbb" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                                    
                                   
                                   #else
                                                 
               
                                    
                                    #if($listam.bandarpemaju == "" || $listam.bandarpemaju=="0")
                #set($kodb="")
                
                #else
                
                #foreach($listneg in $listBandarTetapbyNegeri)      
                
                
                #if($listam.bandarpemaju==$listneg.id)                                      
                #set($kodb="$listneg.kod - $listneg.nama")
                
                #end
                #end
                
                #end
                
                
                
              
                
                #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                #if($listam.bandarpemaju==$listdaerah.id)                                
                #set($listDaerahbyNegeriK=$listdaerah.kod)
                #set($listDaerahbyNegeriN=$listdaerah.nama)
                #end 
                #end
                
                
                #if($disabled=="disabled")
                #set($readmodedaerah="disabled")
                #end
                #if($listam.bandarpemaju!="" && $listam.bandarpemaju!="0" )
                <select name="txtBandarPemaju1HtaamX" id="txtBandarPemaju1HtaamX" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
                  <option value="$listam.bandarpemaju">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)                                 
                                  #if($listam.bandarpemaju!=$listdaerah.id)              
                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                  
                                  #end    
	                               #end
                                  
                </select>
#else
<select name="txtBandarPemaju1HtaamX" id="txtBandarPemaju1HtaamX" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
  <option value="">Sila Pilih Bandar</option>
  
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>                                  
                                 	                               #end

</select>
#end 
#end</label></td>
                                  </tr>
                                              <!--
                                              <tr>
                                                <td><div align="right" class="style43">Bandar</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtBandarPemaju1HtaamX" type="text" id="textfield43" value="$listam.bandarpemaju" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" /></td>
                                              </tr>
                                              -->
                                              
                                              #if($listam.flag=="1")
                                              
                                              <!--
                                              <tr>
                                                <td><div align="right" class="style43">Bandar</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtBandarHartaHtaamX" type="text" id="textfield48" value="$listam.bandarhta" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                              </tr>
                                              -->
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">No Perjanjian</div>
                                                </div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield49" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.noperjanjian" size="34" maxlength="40" $readmodeR class="$readmode" /></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Tarikh Perjanjian</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><span class="style36">
               <input name="txtTarikhPerjanjianHtaamX" id="txtTarikhPerjanjianHtaamX2" type="text" $readmodeR class="$readmode" value="$listam.tarikhperjanjian" size="15"  onblur="trans_date(this.value)" />
               #if($readmode != "disabled" )
               
                                                <a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                                                #end                                                </td>
                                              </tr>
                                              #end
                                              
                                              #if( $listam.flag=="2")
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Nama Rancangan</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><label>
                                                  <input name="txtNamaRancanganHtaamX" type="text" id="textfield50" value="$listam.namarancangan" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">No ROH</div>
                                                </div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtNoRohHtaamX" type="text" id="textfield51" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.noroh" size="34" maxlength="50" $readmodeR class="$readmode" /></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Lot ID</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtLotIdHtaamX" type="text" id="textfield52" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.nolot" size="15" maxlength="15" $readmodeR class="$readmode" /></td>
                                              </tr>
                                              
                                              #end
                                              #if($listam.flag=="3") 
                                              <tr>
                                                <td valign="top">&nbsp;</td>
                                                <td valign="top"><div align="right" class="style57">
                                                  <div align="left">Jenis Kepentingan</div>
                                                </div></td>
                                                <td valign="top"><div align="right">:</div></td>
                                                <td><textarea name="txtJenisKepentinganX" id="txtJenisKepentinganX2" cols="31" rows="5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  $readmodeR class="$readmode" >$listam.jeniskepentingan</textarea></td>
                                              </tr>
                                              
                                              #end
                                          </table></td>
                                          <td width="50%" valign="top"><table width="100%">
                                              <tr>
                                                 <td valign="top"><div align="left"><span class="style43">#if($readmode != "disabled")<span class="style45">*</span>#end</span></div></td>
                                                <td width="29%"><div align="right" class="style43 style54">
                                                  <div align="left">#if($readmodedaerah != "disabled") Bahagian Simati #else Bahagian Simati #end</div>
                                                </div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati5" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati1X')" style="text-align:right;text-transform:uppercase;" value="$listam.basimati" size="14" maxlength="14" $readmodeR class="$readmode"  onblur="bahagiansimati()"/>
/
  <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati6" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati2X')" value="$listam.bbsimati" size="14" maxlength="14" $readmodeR class="$readmode" style="text-align:left;text-transform:uppercase;" onblur="bahagiansimati()" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Kategori Tanah </div>
                                                </div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>
                                                      #if($listam.kategori == "2")
                            #set($meterhektar = "Hektar")
                            #elseif($listam.kategori == "1" || $listam.kategori == "3" || $listam.kategori == "4" || $listam.kategori == "5" || $listam.kategori == "6")
                             #set($meterhektar = "Meter Persegi")
                             
                             #else
                             #set($meterhektar = "")
                             
                            #end
                                                
                                                
                                                #foreach($listkate in $listkategori)
                                          
                                          #if($listam.kategori==$listkate.id)
                                          
                                          #set($listkategoriK=$listkate.kod)
                                          #set($listkategoriN=$listkate.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          
                                            
                             #if($readmode == "disabled")
                             
                             
                               #if($listam.kategori!="" && $$listam.kategori!="0" )
                                     <input name="ktt" value="$listkategoriK - $listkategoriN" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="ktt" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                            
                            #else
                                          
                                          
                                          
                                          #if($listam.kategori!="" && $listam.kategori!="0"  )
                                          <select name="socKategoriTanahHtaamX" class="autoselect" $readmode id="socKategoriTanahHtaamX" style="text-transform:uppercase;" onchange="pilih_jenis_luas('socJenisLuasHtaamX','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')"  >
                                            <option value="$listam.kategori">$listkategoriK - $listkategoriN</option>
                                            
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($listam.kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                            <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                          </select>
#else
<select name="socKategoriTanahHtaamX" class="autoselect" $readmode id="socKategoriTanahHtaamX" style="text-transform:uppercase;" onchange="pilih_jenis_luas('socJenisLuasHtaamX','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')"   >
  <option value="">Sila Pilih Kategori Tanah</option>
  
  
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
  
  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
  
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





</select>
#end

#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57"> 
                                                  <div align="left">Luas Asal</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>
                                                
                                                
                                                #foreach($listluashta in $listluas)
                                          
                                          #if($listam.jenisluas==$listluashta.id)
                                          
                                          #set($listluasK=$listluashta.kod)
                                          #set($listluasN=$listluashta.nama)
                                          #end 
                                          #end
                                          
                                          
                                            #if($readmode == "disabled")
                                             <input name="txtLuasAsalHtaamX" type="text" id="txtLuasAsalHtaamX" value="$listam.luasasal" size="15" maxlength="15" $readmodeR class="$readmode"  />
                                 
                                   #if($listam.jenisluas!="" && $listam.jenisluas!="0")
                    
                   #else
                     
                   #end 
                                            
                              #else
                                          
                                          
                                          #if($listam.jenisluas!="" && $listam.jenisluas!="0")
                                         <input name="txtLuasAsalHtaamX" type="text"  class = "disabled" id="txtLuasAsalHtaamX"  style="text-transform:uppercase;"   onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamX')" value="$listam.luasasal" size="15" maxlength="15" readonly />
                                          
 #else

<input name="txtLuasAsalHtaamX" type="text"  class = "disabled" id="txtLuasAsalHtaamX"  onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamX')" value="$listam.luasasal" size="15" maxlength="15" readonly />
 #end 
 
 #end</td>
                                            </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57"> 
                                                  <div align="left">Jenis Luas</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>
                                                
                                                
                                                #foreach($listluashta in $listluas)
                                          
                                          #if($listam.jenisluas==$listluashta.id)
                                          
                                          #set($listluasK=$listluashta.kod)
                                          #set($listluasN=$listluashta.nama)
                                          #end 
                                          #end
                                          
                                          
                                            #if($readmode == "disabled")
                                             
                                 
                                   #if($listam.jenisluas!="" && $listam.jenisluas!="0")
                   <input name="txtLuas" type="text" id="txtLuas" value="$listluasK - $listluasN" size="25"  $readmodeR class="$readmode"  /> 
                   #else
                    <input name="txtLuas" type="text" id="txtLuas" value="" size="25"  $readmodeR class="$readmode"  /> 
                   #end 
                                            
                              #else
                                          
                                          
                                          #if($listam.jenisluas!="" && $listam.jenisluas!="0")
                                         
                                          <select name="socJenisLuasHtaamX" class="autoselect" $readmode id="socJenisLuasHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaamX','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')"  >
                                            <option value="$listam.jenisluas">$listluasK - $listluasN</option>
                                            
                                         
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($listam.jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listluashta.id" style="text-transform:uppercase;" onblur="uppercase()">$listluashta.kod - $listluashta.nama</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          </select>
                                          
#else


<select name="socJenisLuasHtaamX" class="autoselect" $readmode id="socJenisLuasHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_jenis_luas('socJenisLuasHtaamX','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')"    >
  <option value="">Sila Pilih Jenis</option>

  
  
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
  
  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



</select>   
 #end 
 
 #end</td>
                                            </tr>
                                          <tr id="tr_luasharta" style="display:none;">
                                             <td>&nbsp;</td>
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
                                              <td>&nbsp;</td>
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input type="button" name="button2" id="button2" value="Convert" onclick="ConvertLuasHarta('socJenisLuasHtaamX','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','socKategoriTanahHtaamX')" />
                                              </label></td>
                                            </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Luas (Hektar/MP)</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtLuasHMpHtaamX" type="text" class="$readmode" id="txtLuasHMpHtaamX" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasHMpHtaamX')" value="$listam.luashmp" size="15" maxlength="15" $readmodeR/>
                                                <input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" /></td>
                                              </tr>
                                             <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Nilai Tarikh Mohon (RM)</div>
                                                </div></td>
                                                
                                                #if($listam.nilai_Hta_memohon=="")
                                                #set($ntp="")                                                
                                                #elseif($listam.nilai_Hta_memohon == 0 )
                                                #set($ntp="0.00")                                                
                                                #else                                                
                                                #set($ntp="$listam.nilai_Hta_memohon")                                                
                                                #end
                                                
                                               
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>
                                                 #if($readmode=="disabled")
                                                 #if($listam.nilai_Hta_memohon!="")
                                                 <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaaX" $readmodeR class="$readmode" value="$Util.formatDecimal($listam.nilai_Hta_memohon)"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaaX')" onblur="samakan2()"/>
                                                 #else
                                                  <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaaX" $readmodeR class="$readmode" value=""  onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaaX')" onblur="samakan2()"/>
                                                 #end
                                                 #else
                                                 
                                                 
                                                <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaaX" $readmodeR class="$readmode" value="$ntp"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaaX')"  onblur="validateModal(this,this.value,'$ntp');samakan2()" />
                                                #end</td>
                                              </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Nilai Tarikh Mati (RM)</div>
                                                </div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                
                                                #if($listam.nilai_Hta_mati=="")
                                                	#set($ntm="")                                                
                                                #elseif($listam.nilai_Hta_mati == 0)
                                                	#set($ntm="0.00")                                                
                                                #else                                                
                                                	#set($ntm="$listam.nilai_Hta_mati")
                                                #end
                                                
                                                <td>
                                                
                                              
                                                
                                                #if($readmode=="disabled")
                                                #if($listam.nilai_Hta_mati!="")
                                                <input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamX" $readmodeR class="$readmode" value="$Util.formatDecimal($listam.nilai_Hta_mati)" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamX')"/>
                                                #else
                                                <input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamX" $readmodeR class="$readmode" value="" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamX')"/>
                                                #end
                                                #else
                                                
                                                <input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamX" $readmodeR class="$readmode" value="$ntm" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamX')" onblur="validateModal(this,this.value,'$ntm');" />
                                                #end</td>
                                              </tr>
                                              
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">No Cagaran</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtNoCagaranX" type="text" id="txtNoPajakan4" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.nocagaran" size="15" maxlength="12" $readmodeR class="$readmode" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">No Pajakan</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtNoPajakanX" type="text" id="txtNoPajakan3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.nopajakan" size="15" maxlength="12" $readmodeR class="$readmode" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Status Pemilikan</div>
                                                </div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listpemilik in $listpemilikan)
                                          
                                          #if($listam.pemilikan==$listpemilik.id)
                                          
                                          #set($listpemilikK=$listpemilik.kod)
                                          #set($listpemilikN=$listpemilik.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          
                                           #if($readmode == "disabled")
                              
                               #if($listam.pemilikan!="" && $listam.pemilikan!="0" )
                                     <input name="jstm" value="$listpemilikK - $listpemilikN" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="jstm" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                              
                                          
                                          
                                          
                                          #if($listam.pemilikan!="")
                                          <select name="socStatusPemilikanHtaamX" class="autoselect" $readmode id="socStatusPemilikanHtaamUp2" style="text-transform:uppercase;" onblur="uppercase()">
                                            <option value="$listam.pemilikan">$listpemilikK - $listpemilikN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                  #if($listam.pemilikan!=$listpemilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          </select>
#else
<select name="socStatusPemilikanHtaamX" class="autoselect" $readmode id="socStatusPemilikanHtaamUp2" style="text-transform:uppercase;" onblur="uppercase()">
  <option value="">Sila Pilih Status Pemilikan</option>
  
    
  
  
  
  
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                
	                               
                                              
  
  
  
  
    
  <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
  
    
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




  
</select>
#end 
#end</td>
                                              </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Tanggungan</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtTanggunganHtaamX" type="text" id="txtTanggunganHtaam3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.tanggungan" size="15" maxlength="15" $readmodeR class="$readmode" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="left"><span class="style43"></span></div></td>
                                                <td><div align="right" class="style57">
                                                  <div align="left">Jenis Tanah</div>
                                                </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listtan in $listtanah)
                                          
                                          #if($listam.jenistanah==$listtan.id)
                                          
                                          #set($listtanK=$listtan.kod)
                                          #set($listtanN=$listtan.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          
                                          #if($readmode == "disabled")
                              
                               #if($listam.jenistanah!="" && $listam.jenistanah!="0" )
                                     <input name="jt" value="$listtanK - $listtanN" size="25" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #else
                                     <input name="jt" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     #end
                              
                              #else
                              
                                          
                                          
                                         #if($listam.jenistanah!="" && $listam.jenistanah!="0")
                                          <select name="socJenisTanahHtaamX" class="autoselect" $readmode id="socJenisTanahHtaam3" style="text-transform:uppercase;" onblur="uppercase()">
                                            
                                            
                                             #if($listam.jenistanah=="1")
                                                  <option value="1">TANAH RIZAB</option>
                                                  <option value="2">TANAH ADAT</option>
                                                  <option value="3">TANAH GSA</option>
                                                     <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                               #if($listam.jenistanah=="2")
                                                <option value="2">TANAH ADAT</option>
                                <option value="1">TANAH RIZAB</option>
                                                 
                                                  <option value="3">TANAH GSA</option>
                                                    <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                              
                                               #if($listam.jenistanah=="3")
                                                 <option value="3">TANAH GSA</option>
                                               <option value="1">TANAH RIZAB</option>
                                                 
                                                <option value="2">TANAH ADAT</option>
                                
                                                
                                                     <option value="0">TIDAK DINYATAKAN</option>
                                                  
                                              #end
                                  
                                  
                                  
                                            
                                           
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                              </select>
                                            
#else
<select name="socJenisTanahHtaamX" class="autoselect" $readmode id="socJenisTanahHtaam3" style="text-transform:uppercase;" onblur="uppercase()">
     <option value="0">TIDAK DINYATAKAN</option>
  
       <option value="1">TANAH RIZAB</option>
                                                  <option value="2">TANAH ADAT</option>
                                                  <option value="3">TANAH GSA</option>
</select>
#end 
#end</td>
                                              </tr>
                                              <tr>
                                                <td valign="top"><div align="left"><span class="style43"></span></div></td>
                                                <td valign="top"><div align="right" class="style57">
                                                  <div align="left">Catatan</div>
                                                </div></td>
                                                 <td width="1%" valign="top"><div align="right" class="style43">:</div></td>
                                                <td><textarea name="txtCatatanHtaamX" id="txtCatatanHtaam2" cols="31" rows="5" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()">$listam.catatan</textarea></td>
                                              </tr>
                                                <input type="hidden" name="idhtaamX" value="$listam.idhta" />
                                   

                                          </table></td>
                                        </tr>
                                  </table>
                                  </fieldset>
                                  #if($readmode != "disabled")
  
                              <table width="100%">
                                <tr>
                                  <td><span class="style69 style5 style43 style54"><em><span class="style45">Perhatian</span> : Sila pastikan label bertanda <span class="style45">*</span> diisi.</em></span></td>
                                </tr>
</table>
 
#end                          
                                    </td>
                                  </tr>
                                  #end
                                  
                                 
                                  #end
                                <input type="hidden" name="idhtaamXid" value="$idhtaam" />
                                    
                                    
                                    
                                    
                                    #if($show_button=="yes")
          <tr>
                                  
                                    <td align="center"> #if($show_simpan_add_htaam=="yes")
                                      <input type="button" name="tambahpenghutang3" id="tambahpenghutang3" $readmode value="Simpan"  onclick="setSelected(1,0,0,1);add_HtaamX()"/>
                                      #end
                                      
                                      #if($show_batal_add_htaam=="yes")
                                      <input type="button" name="cmdSimpan9" id="cmdSimpan8" value="Batal" onclick="cancelwaris()"/>
                                      #end
                                      #if($show_kemaskini_htaam=="yes")
                        #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
                     <!--   <input type="button" name="button7" id="button11" value="Kemaskini"  onclick="setSelected(1,0,0,1);edit_HtaamX()" />  -->
                         #end
                      #if($boleh_kemaskini == "yes") <input type="button" name="button7" id="button11" value="Kemaskini"  onclick="setSelected(1,0,0,1);edit_HtaamX()" />#end   
                         
                         
                         
                          #end
                                      #if($show_save_update_htaam=="yes")
                                      <input type="button" name="button13" id="button12" value="Simpan"  onclick="setSelected(1,0,0,1);save_HtaamX()"/>
                                      #end
                                      #if($show_batal_update_htaam=="yes")
                                      <input type="button" name="button7" id="button13" value="Batal"  onclick="setSelected(1,0,0,1);batal_HtaamX()" />
                                      #end
                                      #if($show_hapus_htaam=="yes" && $show_kemaskini_htaam=="yes") 
                           #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
<!--
                                      <input type="button" name="cmdCetak10" id="cmdCetak9" value="Hapus"  onclick="setSelected(1,0,0,1);hapus_HtaamX()"/>  -->
                                      <!--
                                      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onclick="javascript:cetak('$NO_FAIL')"/> -->               
                                      #end 
                       #if($boleh_kemaskini == "yes")   <input type="button" name="cmdCetak10" id="cmdCetak9" value="Hapus"  onclick="setSelected(1,0,0,1);hapus_HtaamX()"/>#end               
                                      
                                      
                                      
                                      <!--
                                      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onclick="javascript:setTable('tableReport')"/>
                                      -->
                                      #end
                                      #if($show_kembali_htaam=="yes")
                                      
                                      #end 
                        <input type="button" name="cmdKembali10" id="cmdKembali9" value="Kembali" onclick="setSelected(1,0,0,1);HtaamViewX()"/></td>
                      </tr>
                                  
                                   #end
                                   
                                   
                                    
                                    
                                   
                                  <tr>
                                  <td>
                                  
                                
                              
                                  
                                  
                                 
                                  
                                   
                                  <fieldset><legend>SENARAI HARTA TAK ALIH (TIADA HAKMILK)</legend>
                                      
                                <table width="100%">
                                      <tr>
                                        <td align="left"><div align="center">
                                         
                                          <div align="left">
                                          
                                         
                                          #if($tambahbutton == "yes")
                                          
                                          #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")

                                       <!--     <input type="submit" name="button" id="button" value="Tambah" onclick="nktambah()"/>  -->
                                            #end
                                            
                              #if($boleh_kemaskini == "yes")<input type="submit" name="button" id="button" value="Tambah" onclick="nktambah()"/>#end             
                                            
                                            #end
                                            #if($kembalibutton == "yes")
                                            <!--
                                            <input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali"  onclick="kembali_simati()" />
                                            -->
                                          #end                                     
                                          
                                            #if(($listHTAX.size()>0) && $show_htaa_add_table=="" && $show_htaa_update_table=="" )    
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
                                    
                                    
                                    
                                    #set($xjumpa_lain = "")
                                    #set($xjumpa_kelompok = "")
                                    #set($xjumpa_beli = "")
                                    
                                  
                                  #if($listHTAX.size()!=0 )    
                                  <fieldset>
                                  <legend>PERJANJIAN JUAL BELI</legend>
                                  <table width="100%">
                                  
                                  
                                    #foreach($listam in $listHTAX)                                     
                                    #if($listam.ketegori_hta == 1)
                                    #set($xjumpa_beli = "no")
                                    #end                                     
                                    #end
                                    
                                    #if($xjumpa_beli =="no")
                             
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <!--
                                        <td width="10%"><div align="center">ID HTA</div></td>
                                        -->
                                        
                                        <td width="15%"><div align="left">NEGERI</div></td>
                                        <td width="15%"><div align="left">DAERAH</div></td>
                                        <td width="15%"><div align="left">MUKIM</div></td>
                                        <td width="15%"><div align="left">ALAMAT HARTA</div></td>
                                        <!--
                                        <td width="10%"><div align="left">NO HAKMILIK</div></td>
                                        <td width="10%"><div align="left">NO PT/NO LOT</div></td>
                                        -->
                                        <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      #end
                                      
                                      
                                      #set($plko=0)
                                      
                                      #foreach($listam in $listHTAX)
                                      
                                      #if($listam.ketegori_hta == 1)
                                      
                        <!--
                            roh::$listam.noroh ;
                            alamat1::$listam.alamat1 ;
                            alamat2::$listam.alamat2 ;
                            alamat3::$listam.alamat3 ;
                            poskod::$listam.poskod ;
                            jenis penting::$listam.jenis_penting ;
                            kategory::$listam.ketegori_hta ;
                                      
                        -->              
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
    <!--
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    -->
    <td class="row1">
    
    #set($al_harta = "") 
    #set($al_harta = "$listam.alamat1  $listam.alamat2  $listam.alamat3 $listam.poskod")
    
    
   
                           
                         <!--  
                            alamat1::$listam.alamat1 ;
                            alamat2::$listam.alamat2 ;
                            alamat3::$listam.alamat3 ;
                            poskod::$listam.poskod ;
                            -->
                             <div align="left" style="text-transform:uppercase;" onblur="uppercase()">$al_harta</div>    </td>
    <td class="row1">
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div>
    #else
  
    #end    </td>
    </tr>
                                      #else
  <tr class="table_header">
    <td class="row2"><div align="center" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
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
    <td class="row2"><div align="left" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$nama_daerah</div></td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="left" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$nama_mukim</div></td>
    <!--
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    -->
     <td class="row2 style54">
    #set($al_harta = "") 
    #set($al_harta = "$listam.alamat1  $listam.alamat2  $listam.alamat3 $listam.poskod")
     <div align="left" style="text-transform:uppercase;" onblur="uppercase()">$al_harta</div>    </td>
    <td class="row2">
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div>
    #else
   
    #end    </td> </tr>
                                      #end
                                      #set($xjumpa_beli = "no")
                                    
                                      #end
                                      #end
                                      
                                      #if($xjumpa_beli == "" )
                                
                                      <tr bgcolor="white">  
                                      <td colspan="6" ><div align="left"  onblur="uppercase()">Tiada Rekod</div></td>	  
   									  </tr>
                                      
                                      #end 
                                  </table>
                                  
                                  
                                  </fieldset>
                                  
                                  <fieldset>
                                  <legend> PEGANGAN DIBAWAH AKTA TANAH</legend>
                                  <table width="100%">
                                  #foreach($listam in $listHTAX)                                     
                                     #if($listam.ketegori_hta == 2 && $listam.ketegori_hta != 1 && $listam.ketegori_hta != 3)
                                    #set($xjumpa_kelompok = "no")
                                    #end                                     
                                    #end
                                    
                                      #if($xjumpa_kelompok == "no")
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <!--
                                        <td width="10%"><div align="center">ID HTA</div></td>
                                        -->
                                        
                                        <td width="15%"><div align="left">NEGERI</div></td>
                                        <td width="15%"><div align="left">DAERAH</div></td>
                                        <td width="15%"><div align="left">MUKIM</div></td>
                                         <td width="15%"><div align="left">NO ROH</div></td>
                                        <!--
                                        <td width="10%"><div align="left">NO HAKMILIK</div></td>
                                        <td width="10%"><div align="left">NO PT/NO LOT</div></td>
                                        -->
                                        <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      
                                      #end
                                      
                                      #set($plko=0)
                                      
                                      #foreach($listam in $listHTAX)
                                      #if($listam.ketegori_hta == 2 && $listam.ketegori_hta != 1 && $listam.ketegori_hta != 3)
                                      
                        <!--
                            roh::$listam.noroh ;
                            alamat1::$listam.alamat1 ;
                            alamat2::$listam.alamat2 ;
                            alamat3::$listam.alamat3 ;
                            poskod::$listam.poskod ;
                            jenis penting::$listam.jenis_penting ;
                            kategory::$listam.ketegori_hta ;
                           -->           
                                      
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
    <!--
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    -->
     <td class="row1">
      <div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noroh</div>    </td>
     <td class="row1">
    #if($listam.basimati!="" && $listam.bbsimati!="")
   <div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div>
    #else
  
    #end    </td>
    </tr>
                                      #else
  <tr class="table_header">
    <td class="row2"><div align="center" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
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
    <td class="row2"><div align="left" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$nama_daerah</div></td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="left" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$nama_mukim</div></td>
    <!--
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    -->
     <td class="row2">
   <div align="left" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$listam.noroh</div>    </td>
     <td class="row2">
    #if($listam.basimati!="" && $listam.bbsimati!="")
   <div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div>
    #else
   
    #end     </td>
     </tr>
    
    
                                      #end
                                      
                                      #set($xjumpa_kelompok = "no")
                                   
                                      
                                      #end
                                      #end
                                      
                                      #if($xjumpa_kelompok == "")
                                
                                      <tr bgcolor="white">  
                                      <td colspan="6" ><div align="left"  onblur="uppercase()">Tiada Rekod</div></td>	  
   									  </tr>
                                      
                                      #end 
                                  </table>
                                  </fieldset>
                                  
                                  <fieldset>
                                  <legend>KEPENTINGAN LAIN-LAIN</legend>
                                  <table width="100%">
                             
                             #foreach($listam in $listHTAX)                                     
                                    #if($listam.ketegori_hta == 3 && $listam.ketegori_hta != 2 && $listam.ketegori_hta != 1  && $listam.negeri != "" )
                                    #set($xjumpa_lain = "no")
                                    #end                                     
                                    #end
                                    
                                     #if($xjumpa_lain == "no")
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <!--
                                        <td width="10%"><div align="center">ID HTA</div></td>
                                        -->
                                        
                                        <td width="15%"><div align="left">NEGERI</div></td>
                                        <td width="15%"><div align="left">DAERAH</div></td>
                                        <td width="15%"><div align="left">MUKIM</div></td>
                                        <td width="15%"><div align="left">JENIS KEPENTINGAN</div></td>
                                        <!--

                                        <td width="10%"><div align="left">NO HAKMILIK</div></td>
                                        <td width="10%"><div align="left">NO PT/NO LOT</div></td>
                                        -->
                                        <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      
                                     #end 
                                      
                                      #set($plko=0)
                                      
                                      #foreach($listam in $listHTAX)
                                      
                                      #if($listam.ketegori_hta == 3 && $listam.ketegori_hta != 2 && $listam.ketegori_hta != 1  && $listam.negeri != "" )
                                      
                        <!--
                            roh::$listam.noroh ;
                            alamat1::$listam.alamat1 ;
                            alamat2::$listam.alamat2 ;
                            alamat3::$listam.alamat3 ;
                            poskod::$listam.poskod ;
                            jenis penting::$listam.jenis_penting ;
                            kategory::$listam.ketegori_hta ;
                          -->            
                                      
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
    <!--
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    -->
     <td class="row1">
 
    <div align="left" style="text-transform:uppercase;" onblur="uppercase()"> $listam.jenis_penting </div>    </td>
     <td class="row1">
    #if($listam.basimati!="" && $listam.bbsimati!="")
   <div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div>
    #else

    #end    </td>
    </tr>
                                      #else
  <tr class="table_header">
    <td class="row2"><div align="center" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
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
    <td class="row2"><div align="left" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$nama_daerah</div></td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td class="row2"><div align="left" class="style54" style="text-transform:uppercase;" onblur="uppercase()">$nama_mukim</div></td>
    <!--
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    -->
     <td class="row2">
   <div align="left" class="style54" style="text-transform:uppercase;" onblur="uppercase()"> $listam.jenis_penting </div>    </td>
     <td class="row2 style54">
    #if($listam.basimati!="" && $listam.bbsimati!="")
   <div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div>
    #else
  
    #end    </td>
  </tr>
                                      #end
                                      #set($xjumpa_lain = "no")
                                      
                                      #end
                                     
                                     
                                      #end
                                   
                                 
                                  
                                  #if($xjumpa_lain == "")
                                
                                      <tr bgcolor="white">  
                                      <td colspan="6" ><div align="left"  onblur="uppercase()">Tiada Rekod</div></td>	  
   									  </tr>
                                      
                                  #end    
                                  </table>
                                  
                                  </fieldset>
                                  
                                  
                                  
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
                                        <!--
                                        <td width="10%"><div align="center">NO HAKMILIK</div></td>
                                       
                                        <td width="10%"><div align="center">NO PT/NO LOT</div></td>
                                         -->
                                        <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      </table>
                                      <table width="100%">
                                      <tr bgcolor="white">
                                        <td align="left">Tiada Rekod                                        </td>
                                      </tr>
                                      </table>
                                    
                                    
                                    #end
                                    
                                  
                                  
                                  
                                  
                                  
                                  
                                  
             
                                  
                                 
                                  <input type="hidden" name="idhtaam" value="$listamid.idhta" />
                    </fieldset>
                        <tr>
                <td>
                <p align="right">&nbsp;</p>                </td>
                </tr>  
                    </table>
            
            </div>
          </div>
        </div>
      </div>
       <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
    </div>
  </div>    
  </td>
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

<!-- HTATH -->
function nktambah() {
document.f1.reset();
document.f1.command.value = "HtaamX";
document.f1.mode.value = "add_new";
document.f1.submit();




}

function CheckMukimU()
{
if(document.f1.socDaerahHtaamX.value == "" || document.f1.socDaerahHtaamX.value == "0")
{
  alert("Sila pilih daerah terlebih dahulu.");
  document.f1.socDaerahHtaamX.focus();
  return;
	  		
}


}

function CheckMukim()
{
if(document.f1.socDaerahHtaamX.value == "" || document.f1.socDaerahHtaamX.value == "0")
{
  alert("Sila pilih daerah terlebih dahulu.");
  document.f1.socDaerahHtaamX.focus();
  return;
	  		
}


}

function CheckDaerah()
{
if(document.f1.socNegeriHtaamX.value == "" || document.f1.socNegeriHtaamX.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriHtaamX.focus();
  return;
	  	
}

}


function get_htaam(idhtaam)
{
    document.f1.command.value = "HtaamX";
	document.f1.idhtaamXid.value = idhtaam;
	document.f1.mode.value = "getHtaamX";
	document.f1.action = "";
	document.f1.submit();
}

function get_X(idhtaam)
{
  
	document.f1.command.value = "HtaamX";
	document.f1.idhtaamXid.value = idhtaam;
	document.f1.mode.value = "getHtaamX";
	document.f1.action = "";
	document.f1.submit();
	
}

function HtaamViewX1() {
	document.f1.action = "";
	document.f1.mode.value = "HtaamviewX1";
	document.f1.command.value = "HtaamX";
	document.f1.submit();
}
function HtaamViewX2() {
	document.f1.action = "";
	document.f1.mode.value = "HtaamviewX2";
	document.f1.command.value = "HtaamX";
	document.f1.submit();
}
function HtaamViewX3() {
	document.f1.action = "";
	document.f1.mode.value = "HtaamviewX3";
	document.f1.command.value = "HtaamX";
	document.f1.submit();
}

function negerichangeX(v_t){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangeX";
		document.f1.action="";
		
		
		if(document.f1.radioHtaamViewX[0].checked == true)
		{
		document.f1.noradio.value="1";
		}
		else if(document.f1.radioHtaamViewX[1].checked == true)
		{
		document.f1.noradio.value="2";
		}
		else if(document.f1.radioHtaamViewX[2].checked == true)
		{
		document.f1.noradio.value="3";
		}
		document.f1.v_tab.value = v_t;
		document.f1.submit();
}

function negerichangepemajuX(v_t){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangepemajuX";
		document.f1.action="";
		
		
		if(document.f1.radioHtaamViewX[0].checked == true)
		{
		document.f1.noradio.value="1";
		}
		else if(document.f1.radioHtaamViewX[1].checked == true)
		{
		document.f1.noradio.value="2";
		}
		else if(document.f1.radioHtaamViewX[2].checked == true)
		{
		document.f1.noradio.value="3";
		}
		document.f1.v_tab.value = v_t;
		document.f1.submit();
}

function daerahchangeX(v_t){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="daerahchangeX";
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
		
}

function negerichangeupX(v_t){		
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangeupX";
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
	
}
function negerichangepemajuupX(v_t){		
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangepemajuupX";
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
	
}


function daerahchangeupX(v_t){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="daerahchangeupX";		
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
		
}

function add_HtaamX(){
if (document.f1.radioHtaamViewX1.checked == true ){
    var dt=document.f1.txtTarikhPerjanjianHtaamX

    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
   var str1  = document.getElementById("txtTarikhPerjanjianHtaamX").value;
    
   var dt1   = parseInt(str1.substring(0,2),10);
   var mon1  = parseInt(str1.substring(3,5),10)-1;
   var yr1   = parseInt(str1.substring(6,10),10);
  
   var date1 = new Date(yr1, mon1, dt1);
 
   }
             var b1=parseInt(document.f1.txtBahagianSimati1X.value);
             var b2=parseInt(document.f1.txtBahagianSimati2X.value);


    if (document.f1.socNegeriHtaamX.value=="") {
		alert("Sila pilih negeri");
		document.f1.socNegeriHtaamX.focus();
		return; 
	} 
	else if (document.f1.socDaerahHtaamX.value=="") {
		alert("Sila pilih daerah");
		document.f1.socDaerahHtaamX.focus();
		return; 
	} 
	else if (document.f1.socMukimHtaamX.value=="") {
		alert("Sila pilih mukim");
		document.f1.socMukimHtaamX.focus();
		return; 
	} 
	
	
	else if ( document.f1.txtPoskodPemaju1HtaamX.value != "" && document.f1.txtPoskodPemaju1HtaamX.value.length < 5 ) {
		alert("Sila masukkan nombor poskod alamat pemaju dengan lengkapnya");
		document.f1.txtPoskodPemaju1HtaamX.focus();
		return; 
	}
	else if(document.f1.txtBahagianSimati1X.value == "" && document.f1.txtBahagianSimati2X.value == "" )
            {
            alert('Sila masuk bahagian simati');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
	
	
	else if(document.f1.txtBahagianSimati1X.value != "" && document.f1.txtBahagianSimati2X.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati2X.focus(); 
			return; 
            }
            
            else if(document.f1.txtBahagianSimati2X.value != "" && document.f1.txtBahagianSimati1X.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
                        
            else if(b1>b2)
            {
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
	
	else if(document.f1.radioHtaamViewX1.checked == true && document.f1.txtPoskodHartaHtaamX.value != "" && document.f1.txtPoskodHartaHtaamX.value.length < 5 )
	{
	
  		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
		document.f1.txtPoskodHartaHtaamX.focus();
		return; 
	
	
	
	}
	else if (document.f1.radioHtaamViewX1.checked == true && dt.value!="" && isDate(dt.value)==false){
		dt.focus()
		return false
	}
	else if (document.f1.radioHtaamViewX1.checked == true &&  date1 > currentTime){
		alert("Sila pastikan tarikh perjanjian tidak melebihi dari tarikh hari ini.");
		//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
		document.f1.txtTarikhPerjanjianHtaamX.focus();
	}
	/*
	else if(document.f1.txtNilaiTarikhMatiHtaamX.value == 0)
	{
	
	alert("Sila pastikan nilai harta pada tarikh mati melebihi RM 0");
		//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
		document.f1.txtNilaiTarikhMatiHtaamX.focus();
	}
	else if(document.f1.txtNilaiTarikhMohonHtaaX.value == 0)
	{
	
	alert("Sila pastikan nilai harta pada tarikh mohon melebihi RM 0");
		//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
		document.f1.txtNilaiTarikhMohonHtaaX.focus();
	}
	*/
	  else if(document.f1.save_harta.value == "yes" )
            {
            alert('Sila masukkan maklumat hta dibawah jagaan unit terlebih dahulu!');
	  	//	document.f1.txtBahagianSimati1.focus(); 
			return; 
            }
    
	
	else{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.f1.command.value="HtaamX";
		document.f1.mode.value="masukkanX";		
		document.f1.action="";
		document.f1.submit(); 
		}
		else
		{
		return;
		}

        }
		
		
		
}

function edit_HtaamX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="kemaskiniHtaamX";		
		document.f1.action="";
		document.f1.submit();
}

function save_HtaamX(){

if(document.f1.flag.value == "1")
{
var dt=document.f1.txtTarikhPerjanjianHtaamX

   var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
   var str1  = document.getElementById("txtTarikhPerjanjianHtaamX2").value;
    
   var dt1   = parseInt(str1.substring(0,2),10);
   var mon1  = parseInt(str1.substring(3,5),10)-1;
   var yr1   = parseInt(str1.substring(6,10),10);
  
   var date1 = new Date(yr1, mon1, dt1);
   
   }
 var b1=parseInt(document.f1.txtBahagianSimati1X.value);
 var b2=parseInt(document.f1.txtBahagianSimati2X.value);
			 
			 
 if (document.f1.socNegeriHtaamX.value=="") {
		alert("Sila pilih negeri");
		document.f1.socNegeriHtaamX.focus();
		return; 
	} 
	else if (document.f1.socDaerahHtaamX.value=="") {
		alert("Sila pilih daerah");
		document.f1.socDaerahHtaamX.focus();
		return; 
	} 
	else if (document.f1.socMukimHtaamX.value=="") {
		alert("Sila pilih mukim");
		document.f1.socMukimHtaamX.focus();
		return; 
	} 
	
	else if ((document.f1.flag.value == "1") && (document.f1.txtPoskodHartaHtaamX.value != "" && document.f1.txtPoskodHartaHtaamX.value.length < 5)) 
	{
   
		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
		document.f1.txtPoskodHartaHtaamX.focus();
		return; 
	
	
	}
	
	else if (document.f1.txtPoskodPemaju1HtaamX.value != "" && document.f1.txtPoskodPemaju1HtaamX.value.length < 5) {
		alert("Sila masukkan nombor poskod alamat pemaju dengan lengkapnya");
		document.f1.txtPoskodPemaju1HtaamX.focus();
		return; 
	}
	
	else if(document.f1.txtBahagianSimati1X.value == "" && document.f1.txtBahagianSimati2X.value == "" )
            {
            alert('Sila masuk bahagian simati');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
	
	
	else if(document.f1.txtBahagianSimati1X.value != "" && document.f1.txtBahagianSimati2X.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati2X.focus(); 
			return; 
            }
            
            else if(document.f1.txtBahagianSimati2X.value != "" && document.f1.txtBahagianSimati1X.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
                        
            else if(b1>b2)
            {
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
			
			
    else if (document.f1.flag.value == "1"  && dt.value!="" && isDate(dt.value)==false){
		dt.focus()
		return false
	}
	else if (document.f1.flag.value == "1" &&  date1 > currentTime){
		alert("Sila pastikan tarikh perjanjian tidak melebihi dari tarikh hari ini.");
		//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
		document.f1.txtTarikhPerjanjianHtaamX.focus();
	}
	/*
	else if(document.f1.txtNilaiTarikhMatiHtaamX.value == 0)
	{
	
	alert("Sila pastikan nilai harta pada tarikh mati melebihi RM 0");
		//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
		document.f1.txtNilaiTarikhMatiHtaamX.focus();
	}
	
	else if(document.f1.txtNilaiTarikhMohonHtaaX.value == 0)
	{
	
	alert("Sila pastikan nilai harta pada tarikh mohon melebihi RM 0");
		//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
		document.f1.txtNilaiTarikhMohonHtaaX.focus();
	}
	
	*/
	
	  else if(document.f1.save_harta.value == "yes" )
            {
            alert('Sila masukkan maklumat hta dibawah jagaan unit terlebih dahulu!');
	  	//	document.f1.txtBahagianSimati1.focus(); 
			return; 
            }
    
	else{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
		
		document.f1.command.value="HtaamX";
		document.f1.mode.value="simpanHtaamX";		
		document.f1.action="";	
		document.f1.submit();
		}
		else
		{
		return;
		}

}

}

function hapus_HtaamX(){
input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.f1.command.value="HtaamX";
		document.f1.mode.value="hapusHtaamX";		
		document.f1.action="";
		document.f1.submit();
		}else
		{
		return;
		}
}

function batal_HtaamX(){
input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.f1.command.value="HtaamX";
		document.f1.mode.value="kemaskiniHtaamX";
		document.f1.action="";
		document.f1.submit();
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
	var dt=document.f1.txtTarikhPerjanjianHtaamX
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }

function ValidateForm1(){
	var dt=document.f1.txtTarikhPerjanjianHtaamX
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }
 


//socJenisLuasHtaam
//txtLuasAsalHtaam
//socKategoriTanahHtaam
//txtLuasHMpHtaam

//socKategoriTanahHtaamX

//txtLuasAsalHtaamX

//socJenisLuasHtaamX


//txtLuasHMpHtaamX


function convertTo(){
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
	
    
}




function getConversion(){
if(document.f1.txtLuasAsalHtaamX.value != "" )
{
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 

}



function getConvert(){
if(document.f1.socKategoriTanahHtaamX.value == 1 ||  document.f1.socKategoriTanahHtaamX.value == 2 || document.f1.socKategoriTanahHtaamX.value == 3 || document.f1.socKategoriTanahHtaamX.value == 4 || document.f1.socKategoriTanahHtaamX.value == 5  || document.f1.socKategoriTanahHtaamX.value == 6  )
{
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 
 else
 {
  document.f1.socKategoriTanahHtaamX.value = "";
  document.f1.txtLuasHMpHtaamX.value = "";
  document.f1.meterhektar.value= "";
  document.f1.txtLuasAsalHtaamX.value = "";
 
 }

}


 //socKategoriTanahHtaamUp
 //txtLuasAsalHtaamUpd
 //socJenisLuasHtaamUpd
 //txtLuasHMpHtaamUpd
 

//socKategoriTanahHtaamX
//txtLuasAsalHtaamX
//socJenisLuasHtaamX
//txtLuasHMpHtaamX
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 function convertToU(){
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
	
    
}




function getConversionU(){
if(document.f1.txtLuasAsalHtaamX.value != "" )
{
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 

}



function getConvertU(){
if(document.f1.socKategoriTanahHtaamX.value == 1 ||  document.f1.socKategoriTanahHtaamX.value == 2 || document.f1.socKategoriTanahHtaamX.value == 3 || document.f1.socKategoriTanahHtaamX.value == 4 || document.f1.socKategoriTanahHtaamX.value == 5  || document.f1.socKategoriTanahHtaamX.value == 6  )
{
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 
 else
 {
  document.f1.socKategoriTanahHtaamX.value = "";
  document.f1.txtLuasHMpHtaamX.value = "";
  document.f1.meterhektar.value= "";
  document.f1.txtLuasAsalHtaamX.value = "";
 
 }

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


function tukarstatus()
{
 document.f1.command.value="HtaamX";
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
    //var url = "../servlet/ekptg.report.ppk.SuratPeringatanJPPH?NO_FAIL="+noFail;
	 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratPeringatan&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
      
    
}


function cetakNilaiHarta(noFail) 
{
  //  var url = "../servlet/ekptg.report.ppk.NilaianHartaPPSPP?nofail="+noFail;
  var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=NilaianHartaPPSPP&flagReport=B";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function setTable(id){
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


document.f1.txtTarikhPerjanjianHtaamX.value = trans;

}
else
{
return;
}

}



function bahagiansimati() { 
if(document.f1.txtBahagianSimati1X.value != "" && document.f1.txtBahagianSimati2X.value != "")
{
if(document.f1.txtBahagianSimati1X.value == document.f1.txtBahagianSimati2X.value )
{
document.f1.txtBahagianSimati1X.value = "1";
document.f1.txtBahagianSimati2X.value = "1";
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

      function CheckBandarTetap()
{
if(document.f1.socNegeriPemajuHtaamX.value == "" || document.f1.socNegeriPemajuHtaamX.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPemajuHtaamX.focus();
  return;
	  	
}

}
    
 function CheckBandarSurat()
{
if(document.f1.socNegeriHtaamX.value == "" || document.f1.socNegeriHtaamX.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriHtaamX.focus();
  return;
	  	
}

}












function samakan1()
{
 
                                            
document.f1.txtNilaiTarikhMatiHtaamX.value=document.f1.txtNilaiTarikhMohonHtaaX.value
 
                 
}
 
function samakan2()
{
 
                                            
document.f1.txtNilaiTarikhMatiHtaamX.value=document.f1.txtNilaiTarikhMohonHtaaX.value
 
                 
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
document.f1.id_daerah_harta.value = document.f1.socDaerahHtaamX.value
}else
{
document.f1.id_daerah_harta.value = document.f1.socDaerahHtaamX.value
}
}


	                              

</script>



</body>
</html>
