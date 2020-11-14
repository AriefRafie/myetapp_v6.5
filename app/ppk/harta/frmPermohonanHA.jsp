
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

.pautan {color: #0000FF}
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}
.style4 {color: #0000FF}
.style6 {color: #FF0000}
.style7 {
	font-size: 9px;
	color: #000000;
}
.style8 {font-size: 10px}
.style9 {font-size: 10px; color: #000000; }
.style10 {color: #000000}
.style11 {font-size: 10px; color: #FF0000; }
-->
</style>
</head>

<body onload="submitForm()" >
	<form id="form1" name="f1" method="post" action="">
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

	<input type="hidden" name="v_tab" id="v_tab" value="" />
   	<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 	<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
 
 	#parse("app/ppk/paging_sek8.jsp") 

	#parse("app/ppk/bil_fail.jsp") 
 
	<table width="100%" border="0" >

		<input type="hidden" name="command" value="">
 		<input type="hidden" name="mode" value="">
 		<input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 		<input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 		<input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 		<input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
      	<input type="hidden" name="eventStatus">
      	<input type="hidden" name="idTemp" value="$id">
     
      	<input type="hidden" name="id" value="$id">
      	<input type="hidden" name="id1" value="$id1">
      	<input type="hidden" name="id2" value="$id2">
      	<input type="hidden" name="id3" value="$id3">
     	<input type="hidden" name="idha" value="$idha">
      	<input type="hidden" name="bil" value="$jumList">
    
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
	      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(2,0,0,0);HAview()" id="maklumat_pemohon" >HARTA ALIH</li>
	      
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
	            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,3,0,0);PentingView()">ORANG BERKEPENTINGAN</li>
	            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
	            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,5,0,0);PemiutangView()">PEMIUTANG</li>
	            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
	          </ul>
	          
	            <div >            
	            <div >              
	            </div> 
	                       
	            <div >             
	            </div>
	            
	            <div ></div>
	            <div ></div>
	            <div ></div>
	            <div ></div>
	            <div ></div>
	            </div>
	        </div>
	      </div>
	      <div class="TabbedPanelsContent">
	        <div id="TabbedPanels4" class="TabbedPanels">
	         
	          <div class="TabbedPanelsContentGroup">
	          
	          </div>
	        </div>
	      </div>
	      <div class="TabbedPanelsContent" >
	
	      <table width="100%">
	      <tr>
	      <td>
           
		<fieldset >    
          #parse("app/ppk/info_berjaya_disimpan.jsp")
 		<table width="100%" border="0">
  		<tr>
    		<td>
				<p>
			   
      #set ($bhgnmati1 = "$bhgnmati1")
      
      #set ($bhgnmati2 = "$bhgnmati2")
      #set ($norujukan = "$norujukan")
      #set ($nilaitarikhmati = "$nilaitarikhmati")
      #set ($nosijil = "$nosijil")
      #set ($nilaitarikhmohon = "$nilaitarikhmohon")
      #set ($bilunit = "$bilunit")
      #set ($nilaiseunit = "$nilaiseunit")
      #set ($agensi = "$agensi")
      #set ($catatan = "$catatan")
      #set ($FLAG_DAFTAR = "$FLAG_DAFTAR")
      
      #set ($txtAlamat1 = "$txtAlamat1")
      #set ($txtAlamat2 = "$txtAlamat2")
      #set ($txtAlamat3 = "$txtAlamat3")
      #set ($txtPoskod = "$txtPoskod")
      #set ($nama_saham = "$nama_saham")
    
      #set ($disabled = "")

	#if ($EventStatus == 2 || $EventStatus == 3)
      	#foreach($Data in $DataHa)
	      	#set ($idJenisHa = $Data.id_Jenisha)
	      	#set ($socJenisHa = $Data.id_Jenisha)
	      	#set ($bhgnmati1 = $Data.basimati)
	      	#set ($bhgnmati2 = $Data.bbsimati)
	      	#set ($norujukan = $Data.noDaftar)     
	      	#set ($nosijil = $Data.nosijil)
	      	#set ($nama_saham = $Data.nama_saham)
	          
	      	#set ($txtAlamat1 = $Data.alamat1)
	      	#set ($txtAlamat2 = $Data.alamat2)
	      	#set ($txtAlamat3 = $Data.alamat3)
	      	#set ($txtPoskod = $Data.poskod)
	      	#set ($daerah = $Data.daerah)
	      	#set ($negeri = $Data.negeri)  
	       	#set ($butiran = $Data.butiran)   
	    
	      	#if($EventStatus == 2)
	      
	      		#if($Data.nilaiha_tarikhmati != "")
	      
	      			#if($!skrin_online == "yes" && $disabled == "" && $disabledR == "")
	      				#set ($nilaitarikhmati = $Data.nilaiha_tarikhmati)
	      			#else
	      				#set ($nilaitarikhmati = $Util.formatDecimal($Data.nilaiha_tarikhmati))
	      			#end
	      
	      		#else
	      			#set ($nilaitarikhmati = "")
	      		#end
	      
	      		#if($Data.nilaiha_tarikhmohon != "")
	      
	      			#if($!skrin_online == "yes" && $disabled == "" && $disabledR == "")
	      				#set ($nilaitarikhmohon =  $Data.nilaiha_tarikhmohon)
	      			#else
	      				#set ($nilaitarikhmohon =  $Util.formatDecimal($Data.nilaiha_tarikhmohon))
	      			#end
	      
				#else
	      			#set ($nilaitarikhmohon = "")
	      		#end
	      
	      		#if($Data.nilaiseunit != "")
	      
	      			#if($!skrin_online == "yes" && $disabled == "" && $disabledR == "")
	      				#set ($nilaiseunit = $Data.nilaiseunit)
				   	#else
				      #set ($nilaiseunit = $Util.formatDecimal($Data.nilaiseunit))
					#end
	      
	      		#else
	      			#set ($nilaiseunit = "" )
	      		#end
	      
	      	#end
	      
	       	#if($EventStatus == 3)
	       
	      		#set ($nilaitarikhmati = $Data.nilaiha_tarikhmati)
	      		#set ($nilaitarikhmohon =  $Data.nilaiha_tarikhmohon)
	      		#set ($nilaiseunit = $Data.nilaiseunit)
	      	#end
	      
	      	#set ($bilunit = $Data.bilunit)      
	      	#set ($agensi = $Data.jenama)
	      	#set ($catatan = $Data.catatan)
	      	#set ($FLAG_DAFTAR = $Data.FLAG_DAFTAR)
	      
	   	#end
	
	#end
      
	#if ($EventStatus == 2)
    	#set ($disabled = "disabled")
	#end
      
	#if ($EventStatus == 0 || $EventStatus == 2 || $EventStatus == 3) 
		
		</p>
  		
  		#if($!skrin_online != "yes")
              
      		#if($disabled == "disabled") 
      			#set($disabledR = "readonly") 
      		#else 
      			#set($disabledR = "") 
        		##2018
        		##set($disabled = "disabled")
      		#end 
              
     	#else
              
	      	#if($open_button_online == "no")
				#set($disabledR = "readonly")
				#set($disabled = "disabled")
				#set($readmodenegeri = "disabled")
				#set($readmodedaerah = "disabled")
				#set($readmodemukim = "disabled")
			#else
				#set($disabledR = "")
				#set($disabled = "")
				#set($readmodenegeri = "")
				#set($readmodedaerah = "")
				#set($readmodemukim = "")
			#end
               
	#end 

			<fieldset><legend>MAKLUMAT HARTA ALIH</legend>
      		<table width="100%" border="0">
        		<tr>
          			<td>
          				<table width="100%" border="0">
            				<tr>
              					<td width="100%" valign="top">
              						<table width="100%" border="0">
	#if($tutup=="")
    	#set($x="$disabled")
  	#end
  	
  	#if($tutup=="yes")
       <!--
      #set($x="readonly")
      -->
      #set($x="disabled")
 	#end
					   	 				<tr>
						                	<td valign="top" width="1%">
						               	#if($x!="disabled")<span class="style11">  * </span>#end</td>
						                  	<td width="30%"><div align="right" class="style9">
						                    	<div align="left">Jenis Harta Alih</div>
						                  	</div></td>
						                  	<td width="1%">:</td>
           
	#if($x == "disabled" && !$negeriARB.equals("") )  
     	<!-- <input name="socJenisHartaAlih" type="hidden" id="socJenisHartaAlih" value="$idJenisHa" /> -->
		#set($xR = "readonly")
	#else
        #set($xR = "")
	#end
      
							       			<td width="68%">
							       
								#foreach($listJenis in $ViewJenisHa)
							   		#if($idJenisHa == $listJenis.idjenisha)							        							     
							      		#set($pbt = "")
								       	#if($idJenisHa == "4")
								       		#set($pbt = "(Pengambilan Balik Tanah)")
								      	#end
								        
								        #set($jj = "$listJenis.kod - $listJenis.keterangan $pbt")
								        #set($kod2 = "$listJenis.kod")
								        #set($idJenisHA = "$listJenis.idjenisha")
							    
							        #end
								
								#end
     
       
	#if($x == "disabled")
    	
    	#if($idJenisHa !="" && $idJenisHa !="0" )
                                     		<!--  <input name="socJenisHartaAlih2" value="$jj" size="50" style="text-transform:uppercase;" $xR class="$x" />
                                     		--><input type="hidden" name="socJenisHartaAlih" value="$idJenisHa"  /> 
        	$!ViewJenisHa
                                     		
      	#else
                                     		<input name="socJenisHartaAlih2" value="" size="50" style="text-transform:uppercase;" $xR class="$x"/>
                                     		<input  type="hidden" name="socJenisHartaAlih" value="" />
     	#end
                                    
	#else
		<!--
       										<select name="socJenisHartaAlih" id="socJenisHartaAlih" class="autoselect" onChange="setSelected(2,0,0,0);getJenisHa(this.value)" $x >
          -->
    	#if ($barula == "yes" && $negeriARB.equals(""))     
       	<!--             
								         	<option value="0" >SILA PILIH JENIS HARTA</option>   
								         
								         #foreach($listJenis in $ViewJenisHa)
									         #set($pbt = "")
									         #if($listJenis.idjenisha == "4")
									         	#set($pbt = "(Pengambilan Balik Tanah)")
									         #end
								         
								         	<option value="$listJenis.idjenisha" >$listJenis.kod - $listJenis.keterangan  $!pbt </option>
								         #end  
      	-->         
       		$!ViewJenisHa
        
        #elseif (!$negeriARB.equals("") && ($!skrin_online != "yes") )
	        #if ($negeriARB == "JOHOR")
	        	#set ($val = "1")
	        	#set ($kod_Negeri = "01 - ")
	        #end
	        #if ($negeriARB == "KEDAH")
	        	#set ($val = "2")
	        	#set ($kod_Negeri = "02 - ")
	        #end
	        #if ($negeriARB == "KELANTAN")
	        	#set ($val = "3")
	        	#set ($kod_Negeri = "03 - ")
	        #end
	        #if ($negeriARB == "MELAKA")
	        	#set ($val = "4")
	        	#set ($kod_Negeri = "04 - ")
	        #end
	        #if ($negeriARB == "NEGERI SEMBILAN")
	        	#set ($val = "5")
	        	#set ($kod_Negeri = "05 - ")
	        #end   
	        #if ($negeriARB == "PAHANG")
	        	#set ($val = "6")
	        	#set ($kod_Negeri = "06 - ")
	        #end  
	        #if ($negeriARB == "PULAU PINANG")
	        	#set ($val = "7")
	        	#set ($kod_Negeri = "07 - ")
	        #end
	        #if ($negeriARB == "PERAK")
	        	#set ($val = "8")
	        	#set ($kod_Negeri = "08 - ")
	        #end        
	        #if ($negeriARB == "PERLIS")
	        	#set ($val = "9")
	        	#set ($kod_Negeri = "09 - ")
	        #end 
	        #if ($negeriARB == "SELANGOR")
	        	#set ($val = "10")
	        	#set ($kod_Negeri = "10 - ")
	        #end
	        #if ($negeriARB == "TERENGGANU")
	        	#set ($val = "11")
	        	#set ($kod_Negeri = "11 - ")
	        #end 
	        #if ($negeriARB == "SABAH")
	        	#set ($val = "12")
	        	#set ($kod_Negeri = "12 - ")
	        #end
	        #if ($negeriARB == "SARAWAK")
	        	#set ($val = "13")
	        	#set ($kod_Negeri = "13 - ")
	        #end
	        #if ($negeriARB == "WILAYAH PERSEKUTUAN KUALA LUMPUR")
	        	#set ($val = "14")
	        	#set ($kod_Negeri = "14 - ")
	        #end                     
	        #if ($negeriARB == "WILAYAH PERSEKUTUAN LABUAN")
	        	#set ($val = "15")
	        	#set ($kod_Negeri = "15 - ")
	        #end                
	        #if ($negeriARB == "WILAYAH PERSEKUTUAN PUTRAJAYA")
	        	#set ($val = "16")
	        	#set ($kod_Negeri = "16 - ")
	        #end  
	        #if ($negeriARB == "PELBAGAI NEGARA")
	        	#set ($val = "17")
	        	#set ($kod_Negeri = "99 - ")
	        #end                           
        								<!-- 2018	<option value="98" >HARTA YANG DISELESAIKAN OLEH AMANAH RAYA BERHAD</option>  --> 
			$!ViewJenisHa			
         <!-- <option value="0" selected>$negeriARB</option> -->

        #else
        	#set ($selected = "")
        	#if ($EventStatus == 0)               
                 							<!--  <option value="0" >SILA PILIH JENIS HARTA</option>  -->                               
          	#end
          	<!--  
	        #foreach($listJenis in $ViewJenisHa)
           		#set($pbt = "")
             	#if($listJenis.idjenisha == "4")
             		#set($pbt = "(Pengambilan Balik Tanah)")
             	#end
            
	        	#if ($EventStatus == 0)
               		
               		#if ($listJenis.idjenisha == $socJenisHa)
			        	#set ($selected = "selected")
			        						<option value="$listJenis.idjenisha" $selected>$listJenis.kod - $listJenis.keterangan $!pbt</option>
			       	#else
			        						<option value="$listJenis.idjenisha">$listJenis.kod - $listJenis.keterangan $!pbt</option>
			      	#end
                                      
	        	#end
	        					
	        	#if ($EventStatus == 2 || $EventStatus == 3 )
					#if ($listJenis.idjenisha == $idJenisHa)
						#set ($selected = "selected")
					        				<option value="$listJenis.idjenisha" $selected>$listJenis.kod - $listJenis.keterangan</option>
					#else
					        				<option value="$listJenis.idjenisha">$listJenis.kod - $listJenis.keterangan</option>
					#end
	        	
	        	#end
	        
	        #end
     -->
       		$!ViewJenisHa								
    	#end
       	<!--									</select>
       	-->	
	#end
                 						</td>
                					</tr>
                
			#if($EventStatus!=3)
				#if (!$negeriARB.equals("") && ($!skrin_online != "yes") )
            	<tr>
                	<td class="style38 style8 style6">&nbsp;</td>
                  	<td class="style38"><div align="right" class="style9">
                    	<div align="left">Negeri</div>
                  	</div></td>
                  	<td>:</td>
                  	<td>  
              		#if($disabled != "disabled")
	                  	<!-- <select name="socNegeriHtaam"  class="autoselect" $readmodenegeri onchange="setSelected(2,0,0,0);negerichange('socDaerahHtaam')" style="text-transform:uppercase;" onblur="uppercase()">
	                    	<option value="$val">$kod_Negeri $negeriARB</option>
	                                                  
	            		</select> -->
	            		$!socNegeri
            		#else
            			#foreach($listnegpomo in $n)
                    
                    		#if($negeri==$listnegpomo.id_Negeri)                   
                    			#set($negerikodpemoP=$listnegpomo.kod_Negeri)
                    			#set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                    		
                    		#end 
                		#end
                		
                		#if($negeri!="" && $negeri!="0" )
                    	<!--  <input name="n" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $disabledR class="$disabled" /> -->
							$!socNegeri
            		   	#else
                     	<input name="n" value="" size="34" style="text-transform:uppercase;" $disabledR class="$disabled" />
                		#end
                    
            		#end
            	#if($disabled != "disabled")
            	<!-- 2018 <input type="button" value="Semak Daerah" onclick="setSelected(2,0,0,0);negerichange('socDaerahHtaam')"></input> -->
            	<input type="hidden" name="valNegeri" value="$!val"></input>
            	<input type="hidden" name="valKodNegeri" value="$!kod_Negeri"></input>
            	<input type="hidden" name="valNegeriARB" value="$!negeriARB"></input>
            	#end
            	</td>
            	</tr>
            	#else
                <tr>
                  <td class="style38 style8 style6">&nbsp;</td>
                  <td class="style38"><div align="right" class="style9">
                    <div align="left">Negeri</div>
                  </div></td>
                  <td>:</td>
                  <td> 
                  
                  #foreach($listnegpomo in $listnegeri)
                    
                    #if($negeri==$listnegpomo.id_Negeri)
                    
                    #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                    #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                    
                    
                    
                    #end 
                    #end
                    
                       #if($disabled=="disabled")
                    #set($readmodenegeri="disabled")
                    #else
                    #set($readmodenegeri="")
                    #end
                    
                     #if($disabled == "disabled")
                              
                               #if($negeri!="" && $negeri!="0" )
                               
                                     <input name="n" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $disabledR class="$disabled" />
                                     #else
                                     <input name="n" value="" size="34" style="text-transform:uppercase;" $disabledR class="$disabled" />
                                     #end
                              
                              #else
                    
                    
                    #if($negeri!="" && $negeri!="0"  )
                    
                 
                    <select name="socNegeriHtaam" class="autoselect" $readmodenegeri onchange="setSelected(2,0,0,0);negerichange('socDaerahHtaam')" style="text-transform:uppercase;" onblur="uppercase()">
                                                    <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                      
                                                  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                                  
                      <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                      
                                                  
                                              
                                   
                                  #end    
	                               #end
                                  
                                   <option value="">Sila Pilih Negeri</option>
                    </select>
                    #else
  <select name="socNegeriHtaam" class="autoselect" $readmodenegeri onchange="setSelected(2,0,0,0);negerichange('socDaerahHtaam')" style="text-transform:uppercase;" onblur="uppercase()">
    <option value="">Sila Pilih Negeri</option>
    
    
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
    
    <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
    
    
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  </select>
                #end    #end </td>
                  </tr>
                  
                #end
                <tr>
                
                  <td class="style38 style8 style6" >&nbsp;</td>
                  <td class="style38" ><div align="right" class="style9">
                    <div align="left">Daerah</div>
                  </div></td>
                  <td>:</td>
                  <td>#foreach($listdaerah in $listDaerahbyNegeri)
                    
                    #if($daerah==$listdaerah.id)
                    
                    #set($listDaerahbyNegeriK=$listdaerah.kod)
                    #set($listDaerahbyNegeriN=$listdaerah.nama)
                    
                    
                    
                    #end 
                    #end
                    
                    
                    #if($disabled=="disabled")
                    #set($readmodedaerah="disabled")
                    #end
                    
                     #if($disabled == "disabled")
                              
                               #if($daerah!="" && $daerah!="0" )
                                     <input name="d" value="$listDaerahbyNegeriK - $listDaerahbyNegeriN" size="45" style="text-transform:uppercase;" $disabledR class="$disabled" />
                                     #else
                                     <input name="d" value="" size="34" style="text-transform:uppercase;" $disabledR class="$disabled"/>
                                     #end
                              
                              #else
                    
                    
                    
                    #if($daerah!="" && $daerah!="0" )
                    <select name="socDaerahHtaam" id="socDaerahHtaam" class="autoselect" $readmodedaerah  style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckDaerah()" >
                                                    <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                      
                                                  
                                            
                                              

                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    </select>
                    #else
  <select name="socDaerahHtaam" id="socDaerahHtaam" class="autoselect" $readmodedaerah  style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckDaerah()" >
    <option value="">Sila Pilih Daerah</option>
                                               
                                  #foreach($listDaerah in $listDaerahbyNegeri)
   <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  </select>
            #end          #end </td>
                </tr>
              
                
                #end
                
                #if($EventStatus == 3)
          
                <tr>
                  <td class="style38 style8 style6">&nbsp;</td>
                  <td class="style38"><div align="right" class="style9">
                    <div align="left">Negeri</div>
                  </div></td>
                  <td>:</td>
                  <td> 
         	#foreach($listnegpomo in $listnegeri)
           		#if($negeri==$listnegpomo.id_Negeri)
                   	#set($negerikodpemoP=$listnegpomo.kod_Negeri)
                    #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
              	
              	#end 
        	#end
                    
      		#if($disabled=="disabled")
         		#set($readmodenegeri="disabled")
         	#else
           		#set($readmodenegeri="")
         	#end
                    
			#if($disabled == "disabled")
           		
           		#if($negeri!="" && $negeri!="0" )
                <!--  <input name="n" value="$negerikodpemoP - $negeriketeranganpemoP" size="45" style="text-transform:uppercase;" $disabledR class="$disabled" /> -->
           		$!socNegeri
           		#else
            	<input name="n" value="" size="34" style="text-transform:uppercase;" $disabledR class="$disabled" />
     			#end
                              
      		#else
            	
            	#if($negeri!="" && $negeri!="0"  )
                   
                   <select name="socNegeriHtaam" class="autoselect" $readmodenegeri onchange="setSelected(2,0,0,0);negerichangeU('socDaerahHtaam')" style="text-transform:uppercase;" onblur="setSelected(2,0,0,0);negerichangeU('socDaerahHtaam')" >
                                                    <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                      
                                                  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                                  
                      <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                      
                                                  
                                              
                                   
                                  #end    
	                               #end
                                  
                                   <option value="">Sila Pilih Negeri</option>
                    </select>
                    #else
  <select name="socNegeriHtaam" class="autoselect" $readmodenegeri onchange="setSelected(2,0,0,0);negerichangeU('socDaerahHtaam')" style="text-transform:uppercase;" onblur="setSelected(2,0,0,0);negerichangeU('socDaerahHtaam')" >
    <option value="">Sila Pilih Negeri</option>
    
    
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
    
    <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
    
    
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
  
  </select>
    #end                #end </td>
                  </tr>
                <tr>
                  <td class="style38 style8 style6" >&nbsp;</td>
                  <td class="style38" ><div align="right" class="style9">
                    <div align="left">Daerah  </div>
                  </div></td>
                  <td>:</td>
                  <td>#foreach($listdaerah in $listDaerahbyNegeri)
                    
                    #if($daerah==$listdaerah.id)
                    
                    #set($listDaerahbyNegeriK=$listdaerah.kod)
                    #set($listDaerahbyNegeriN=$listdaerah.nama)
                    #end 
                    #end
                    
                    
                    #if($disabled=="disabled")
                    #set($readmodedaerah="disabled")
                   
                    #end
                    
                    
                      #if($disabled == "disabled")
                              
                               #if($daerah!="" && $daerah!="0" )
                                     <input name="d" value="$listDaerahbyNegeriK - $listDaerahbyNegeriN" size="45" style="text-transform:uppercase;" $disabledR class="$disabled" />
                                     #else
                                     <input name="d" value="" size="34" style="text-transform:uppercase;" $disabledR class="$disabled"/>

                                     #end
                              
                              #else
                    
                    
                    
                    #if($daerah!="" && $daerah!="0" )
                    
                  
                 
                    
                    
                   
                    <select name="socDaerahHtaam" id="socDaerahHtaam" class="autoselect" $readmodedaerah  style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckDaerah()" >
                                                    <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                     
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                    
                                  #end    
	                               #end
                                                 
                    </select>
                    
                     
                    #else
 
                    <select name="socDaerahHtaam" id="socDaerahHtaam" class="autoselect" $readmodedaerah  style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckDaerah()" >
    <option value="0">Sila Pilih Daerah</option>
    
    
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
    
    <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  </select>
         #end           #end </td>
                  </tr>
               
              
                
                #end
              
                #if($showadd=="1")
                
                  
                <tr id="tr_butitan">
                 <td valign="top">
                 #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                 
                 #else
                   #if ($socJenisHa == "6")#elseif($socJenisHa == "7")
                    <span class="style11">*</span>
                    #elseif($socJenisHa == "8")
                    <span class="style11">*</span>
                    #elseif($socJenisHa == "9")
                     <span class="style11">*</span>
                    #elseif($socJenisHa == "10")
                    <span class="style11">*</span>
                    #elseif($socJenisHa == "11")
                    <span class="style11">*</span>
                    #elseif($socJenisHa == "12")
                    <span class="style11">*</span>
                    #elseif($socJenisHa == "98")#end
                 #end                 </td>
                  <td valign="top"><div align="right" class="style8">
                    <div align="left">
                    #if ($socJenisHa == "6")                    
					Butiran Peti Keselamatan
                    #elseif($socJenisHa == "7")
                    Lokasi Harta
                    #elseif($socJenisHa == "8")
                    Butiran Tuntutan
                    #elseif($socJenisHa == "9")
                    Butiran Senjata Api
                    #elseif($socJenisHa == "10")
                    Butiran Barang Berharga
                    #elseif($socJenisHa == "11")
                    Butiran Ternakan
                    #elseif($socJenisHa == "12")
                    Butiran Harta
                    #elseif($socJenisHa == "98")
                    Butiran Nilaian Harta
                    #end                   
                     </div>
                  </div></td>
                  <td valign="top">:</td>
                 <td><textarea name="butiran" id="butiran" cols="50" rows="6"  style="width: 300px;"  $disabledr class="$disabled"  >$butiran</textarea></td>
                </tr>
                   
                    #if ($socJenisHa == "6" || $socJenisHa == "7" || $socJenisHa == "8" || $socJenisHa == "9" || $socJenisHa == "10" || $socJenisHa == "11" || $socJenisHa == "12" || $socJenisHa == "98" )   
                   
                   <script>
				   document.getElementById('tr_butitan').style.display="";
				   </script>
                   #else
                     <script>
				   document.getElementById('tr_butitan').style.display="none";
				   </script>
                   #end
                   
                   
                   #if ($socJenisHa == "2" || $socJenisHa == "4" || $socJenisHa == "5" || $socJenisHa == "1" || $socJenisHa == "3" || $socJenisHa == "6" )   
           
                   
                   <tr>
                  <td>
                  #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                  
                  #else 
                     #if ($socJenisHa == "1")#elseif ($socJenisHa == "2")
                     <span class="style11">*</span>
                      #elseif ($socJenisHa == "3")
                     <span class="style11">*</span>                     
                      #elseif ($socJenisHa == "4")
                     <span class="style11">*</span>
                      #elseif ($socJenisHa == "5")
                     <span class="style11">*</span>
                      #elseif ($socJenisHa == "6")
                     <span class="style11">*</span>
                      #else
                  
                      #end                         
                  #end
                  </td>
                  <td><div align="right" class="style9">
                    <div align="left">
                    
                      #if ($socJenisHa == "1")
                      Agensi
                      #elseif ($socJenisHa == "2")
                      Agensi
                      #elseif ($socJenisHa == "3")
                      Jenis dan Jenama                 
                      #elseif ($socJenisHa == "4")
                      No. Hakmilik, No. Lot dan Mukim
                      #elseif ($socJenisHa == "5")
                      Agensi
                      #elseif ($socJenisHa == "6")
                      Agensi
                      #else
                      Lain
                      #end
 
                        </div>
                  </div></td>
                  <td>:</td>
                  <td>
                  #if ($socJenisHa == "1" || $socJenisHa == "2" || $socJenisHa == "3" || $socJenisHa == "4" || $socJenisHa == "5" || $socJenisHa == "6" )
      <input  name="txtAgensi" type="text" class="$disabled" id="txtAgensi" style="text-transform:uppercase; text-align: left;" onblur="this.value=this.value.toUpperCase()" value="$agensi" size="50" maxlength="150" $disabledR  /> 
 	  	#if($!skrin_online == "yes")
 	  	#if($socJenisHa == "3")
 	  	<a href="javascript:info('geran_kereta')" class="help" title="info">					
					<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
			</a>#end 
			
			#if($socJenisHa == "2")
 	  	<a href="javascript:info('agensi')" class="help" title="info">					
					<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
			</a>#end
			#end
			
 	  	#else
      <input  name="txtAgensi" type="text" class="$disabled" id="txtAgensi" style="text-transform:uppercase; text-align: left;" onblur="this.value=this.value.toUpperCase()" value="$agensi" size="50" maxlength="150" $disabledR  />	  
      #end 	</td> 
        </tr>
      #end
                
             
             #if ($socJenisHa == "2" || $socJenisHa == "4" || $socJenisHa == "5" || $socJenisHa == "1" || $socJenisHa == "3" || $socJenisHa == "6" || $socJenisHa == "98" )   
                
                #if ($socJenisHa == "1")
                <tr>
                  <td>
                  
                   #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") 
                      
                      #else
                      <span class="style11">*</span>
                      #end 
                  
                  </td>
                   <td>
                    <div align="right" class="style9">
                      <div align="left">
                  Nama Saham
                   
                        <input type="hidden" name="socJenisHa" value="$socJenisHa"  />
                        
                        
                      </div>
                    </div></td>
                  <td>:</td>
                  <td>  <input  name="nama_saham" type="text" id="nama_saham" onblur="this.value=this.value.toUpperCase()" style="text-transform:uppercase; text-align: left;" value="$nama_saham" size="40" $disabledR class="$disabled"  /> </td>
                </tr>
                #end
                
                
                <tr>
                  <td>
                  
                  #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") 
                  
                      #else
                        #if ($socJenisHa == "2")
                        <span class="style11">*</span>
                        #elseif ($socJenisHa == "4")#elseif ($socJenisHa == "5")	
                        <span class="style11">*</span>
                        #elseif ($socJenisHa == "1")#elseif ($socJenisHa == "3")	
                        <span class="style11">*</span>
                         #elseif ($socJenisHa == "6")	
                     <span class="style11">*</span>
                        #elseif ($socJenisHa == "98")	
                      
                        #else
                       
                        #end
                      #end                  </td>
              	<td>
                    <div  class="style9">
                  
                        #if ($socJenisHa == "2")
                        No. Akaun
                        #elseif ($socJenisHa == "4")
                        No. Rujukan UPT
                        #elseif ($socJenisHa == "5")	
                        No. Polisi
                        #elseif ($socJenisHa == "1")	
                        No. Ahli
                        #elseif ($socJenisHa == "3")	
                        No. Daftar
                         #elseif ($socJenisHa == "6")	
                        No. Peti
                        #elseif ($socJenisHa == "98")	
                        No. Rujukan
                        #else
                        No. Rujukan UPT
                        #end
                    	<input type="hidden" name="socJenisHa" value="$socJenisHa"  />
                    
               		</div></td>
     
             	<td>:</td>
             	<td>
        	#if ($socJenisHa == 1 || $socJenisHa == 4 || $socJenisHa == 3 || $socJenisHa == 5 || $socJenisHa == 6) 
      				<input name="txtNoRujukan" type="text" id="txtNoRujukan" style="width: 150px; text-transform:uppercase; text-align: left;" value="$norujukan" size="20" maxlength="30" $disabledR class="$disabled"  onblur="this.value=this.value.toUpperCase()" />  
      		#if($!skrin_online == "yes")
      		#if ($socJenisHa == "3")
      		<a href="javascript:info('daftar')" class="help" title="info">					
								<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
					</a> #end #end
      		#elseif ($socJenisHa == 2)
      				<input name="txtNoRujukan" type="text" id="txtNoRujukan" style="width: 150px; text-transform:uppercase; text-align: left;"  value="$norujukan" size="20" maxlength="30" onblur="this.value=this.value.toUpperCase()" $disabledR class="$disabled"  /> 
      		#if($!skrin_online == "yes")
      		<a href="javascript:info('akaun')" class="help" title="info">					
								<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
					</a> #end
      		#else
      
          			<input name="txtNoRujukan" type="text" id="txtNoRujukan" style="width: 150px; text-transform:uppercase; text-align: left;" value="$norujukan" size="20" maxlength="30" $disabledR class="$disabled"  onblur="this.value=this.value.toUpperCase()" />      
      #end   
      			</td>
        	</tr>
       
      #end
      
       	#if ($socJenisHa == "1" )   
  			<tr>
           		<td>
             	##2018/10/05
             	##if($!skrin_online != "yes" && $!skrin_online_17 != "yes")                                
               	##else
               	##end
                  
          		</td>
      			<td><div  class="style9">
             No. Sijil
           		</div></td>
      			<td>:</td>
      			<td>
           	#if ($socJenisHa == 1) 
    
     				<input name="txtNoSijil" type="text" id="txtNoSijil" style="text-transform:uppercase; text-align: left;" value="$nosijil" size="50" maxlength="100" $disabledR class="$disabled"  onblur="this.value=this.value.toUpperCase()" /> 
    		#else
     				<input name="txtNoSijil" type="text" id="txtNoSijil" style="text-transform:uppercase; text-align: left;" value="$nosijil" size="50" maxlength="100" $disabledR class="$disabled"  onblur="this.value=this.value.toUpperCase()" />      
    		#end    
    			</td>
      		</tr>
      
    #end
    
     #if ($socJenisHa == "1" )   
       		<tr>
          		<td></td>
           		<td><div align="right" class="style9">
               		<div align="left">Bil. Unit</div>
              	</div></td>
              	<td>:</td>
              	<td >
         	#if ($socJenisHa == 1)
     
     				<input name="txtBilUnit" type="text" id="txtBilUnit" style="width: 150px; text-transform:uppercase; text-align: left;" onKeyUp="javascript:validatePoskod(this,this.value);saham()" onblur="saham()" value="$bilunit" size="20" maxlength="10" $disabledR class="$disabled"  />
    		#else
    
     
          			<input name="txtBilUnit" type="text" id="txtBilUnit" style="width: 150px; text-transform:uppercase; text-align: left;" onKeyUp="javascript:validatePoskod(this,this.value);saham()"  onblur="saham()" value="$bilunit" size="20" maxlength="10" $disabledR class="$disabled"  />     
    		#end     	
    			</td>
    		</tr>
    
    #end
    
      	#if ($socJenisHa == "1" )   
     		<tr>
            	<td></td>
              	<td><div align="right" class="style9">
                    <div align="left">Nilai Seunit</div>
               	</div></td>
              	<td>:</td>
               	<td>
            	#if ($socJenisHa == 1)  
      				<input name="txtNilaiSeunit" type="text" id="txtNilaiSeunit" style="width: 150px; text-align: right; text-transform:uppercase;" onblur="validateModal(this,this.value,'$nilaiseunit');saham()" onKeyUp="javascript:validatePoskod(this,this.value);saham()" value="$nilaiseunit" size="20" maxlength="10" align="right" $disabledR class="$disabled"  /> 
      			#else
     				<input name="txtNilaiSeunit" type="text" id="txtNilaiSeunit" style="width: 150px; text-align: right; text-transform:uppercase;" onblur="validateModal(this,this.value,'$nilaiseunit');saham()" onKeyUp="javascript:validatePoskod(this,this.value);saham()" value="$nilaiseunit" size="20" maxlength="10" $disabledR class="$disabled"  align="right"/>    
    			#end   	 		
    			</td>
    		</tr>    
    	#end    
   <!--   
      #if ($socJenisHa == "2" || $socJenisHa == "4" || $socJenisHa == "5" || $socJenisHa == "1" || $socJenisHa == "3" )   
           
                <tr>
                  <td>&nbsp;</td>
                  <td><div align="right" class="style9">
                    <div align="left">#if ($socJenisHa == "1")
                      Agensi
                      #elseif ($socJenisHa == "2")
                      Agensi
                      #elseif ($socJenisHa == "3")
                      Jenama
                      
                      #elseif ($socJenisHa == "4")
                      No Lot#elseif ($socJenisHa == "5")
                      Agensi
                      #else
                      Lain
                      #end </div>
                  </div></td>
                  <td>:</td>
                  #if ($socJenisHa == "1" || $socJenisHa == "2" || $socJenisHa == "3" || $socJenisHa == "4" || $socJenisHa == "5" )
      <td><input  name="txtAgensi" type="text" id="txtAgensi" onblur="this.value=this.value.toUpperCase()" style="width: 150px; text-transform:uppercase; text-align: left;" value="$agensi" size="40" $disabledR class="$disabled"  /> </td>
 	  #else
      <td><input  name="txtAgensi" type="text" id="txtAgensi" onblur="this.value=this.value.toUpperCase()" style="width: 150px; text-transform:uppercase; text-align: left;" value="$agensi" size="40" $disabledR class="$disabled"  /></td> 	  
      #end                </tr>
      #end
   -->
      #end
      
      
<!--      
<input name="socDaerahHtaam" type="hidden" id="socDaerahHtaam" class="autoselect" $readmodedaerah  style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckDaerah()" >
<input name="socNegeriHtaam" type="hidden" class="autoselect" $readmodenegeri onchange="setSelected(2,0,0,0);negerichangeU('socDaerahHtaam')" style="text-transform:uppercase;" onblur="setSelected(2,0,0,0);negerichangeU('socDaerahHtaam')" >
-->


<input  name="txtPoskod" type="hidden" id="txtPoskod" style="text-transform:uppercase; text-align: left;" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskod')" value="$txtPoskod" size="5" maxlength="5" $disabledR class="$disabled"  />
<input  name="txtAlamat3" id="txtAlamat3" type="hidden" style="text-transform:uppercase; text-align: left;" value="$txtAlamat3" size="40" maxlength="100" $disabledR class="$disabled"  onblur="this.value=this.value.toUpperCase()" />
<input  name="txtAlamat2" id="txtAlamat2" type="hidden" style="text-transform:uppercase; text-align: left;" value="$txtAlamat2" size="40" maxlength="100" $disabledR class="$disabled"  onblur="this.value=this.value.toUpperCase()" />
<input  name="txtAlamat1" type="hidden" id="txtAlamat1" style="text-transform:uppercase; text-align: left;" value="$txtAlamat1" size="40" maxlength="100" $disabledR class="$disabled"   onblur="this.value=this.value.toUpperCase()" />
      
      
                
                <!--
                
                <tr>
                  <td>&nbsp;</td>
                  <td><div align="right" class="style9">
                    <div align="left">Alamat Harta</div>
                  </div></td>
                  <td>:</td>
                
      <td><input  name="txtAlamat1" type="text" id="txtAlamat1" style="text-transform:uppercase; text-align: left;" value="$txtAlamat1" size="40" maxlength="100" $disabledR class="$disabled"   onblur="this.value=this.value.toUpperCase()" /> </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td><div align="left"><span class="style8"><span class="style10"></span></span></div></td>
                  <td>&nbsp;</td>
                 
      <td><input  name="txtAlamat2" id="txtAlamat2" type="text" style="text-transform:uppercase; text-align: left;" value="$txtAlamat2" size="40" maxlength="100" $disabledR class="$disabled"  onblur="this.value=this.value.toUpperCase()" /></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td><div align="left"><span class="style8"><span class="style10"></span></span></div></td>
                  <td>&nbsp;</td>
                 
      <td><input  name="txtAlamat3" id="txtAlamat3" type="text" style="text-transform:uppercase; text-align: left;" value="$txtAlamat3" size="40" maxlength="100" $disabledR class="$disabled"  onblur="this.value=this.value.toUpperCase()" /></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td><div align="right" class="style9">
                    <div align="left">Poskod</div>
                  </div></td>
                  <td>:</td>
                
      <td><input  name="txtPoskod" type="text" id="txtPoskod" style="text-transform:uppercase; text-align: left;" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskod')" value="$txtPoskod" size="5" maxlength="5" $disabledR class="$disabled"  /></td>
                </tr>
                -->
                
                
               
              <!--  
              </table></td>
              <td width="50%" valign="top">
              <table width="100%" border="0">
              -->
              #if( $socJenisHa != 2 && $socJenisHa != 3  && $socJenisHa != 98 )
              <!-- buang dulu filter untuk bahagian -->
              #end
                <tr>
                
                 <td>&nbsp;</td>
                  <td><div align="right" class="style8">
                    <div align="left">Bahagian Simati</div>
                  </div></td>
                  <td width="1%">:</td>
               
                   <td ><input name="txtBhgnSimati1" type="text" id="txtBhgnSimati1" style="text-align:right;text-transform:uppercase;" onKeyUp="javascript:validatePoskod(this,this.value);saham()" value="$bhgnmati1"   size="14" maxlength="14"  onblur="bahagiansimati();saham()" $disabledR class="$disabled"  /> 
        / 
        <input  name="txtBhgnSimati2" type="text" id="txtBhgnSimati2" style="text-align:left" onKeyUp="javascript:validatePoskod(this,this.value);saham()" value="$bhgnmati2" size="14" maxlength="14" $disabledR class="$disabled"  onblur="bahagiansimati();saham()" />             </td>
                </tr>
                
                
                <tr>
                 <td>
                 <span class="style11">*</span>
                  #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") 
                                     
                    #else                                         
                         #if( $socJenisHa != 98 )#else
                         <span class="style11">*</span>
                         #end 
                    #end 
                 
                 </td>
                  <td><div align="right" class="style8">
                    <div align="left">
                    
                   #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") 
                        #if( $socJenisHa != 98 )
                        
                        Nilai Tarikh Mohon (RM)
                        #else
                        Nilaian (RM)
                        #end                     
                    #else                                         
                         #if( $socJenisHa != 98 )
                         Anggaran Nilai Tarikh Mohon (RM)
                         #else
                         Anggaran Nilaian (RM)
                         #end 
                    #end 
                    
                                      </div>
                  </div></td>
                  <td>:</td>
                 
                  #if($nilaitarikhmohon=="")
                   #set($nilaitarikhmohon="")
                  #end
                  
                   <td>
                   <input  name="txtNilaiTarikhMohon" id="txtNilaiTarikhMohon" type="text" style="width: 150px; text-align: right; text-transform:uppercase;" value="0.00" $disabledR class="$disabled"  onblur="validateModal(this,this.value,'$nilaitarikhmohon')"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohon')" />
                   </td>
                </tr>
                #if( $socJenisHa != 98 )
                <tr>
                 <td>
                   <!-- <span class="style11">*</span> -->
                   <span class="style11"></span>
                    #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") 
                       
                                            
                    #else#end 
                 </td>
                  <td><div  class="style8">
                    
                    
                    
                    #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") 
                       
                        Nilai Tarikh Mati (RM)
                                            
                    #else                                         
                       
                        Anggaran Nilai Tarikh Mati (RM)
                         
                    #end 
                    
                    
                    
                  </div></td>
                  <td>:</td>
                  #if($nilaitarikhmati=="")
                  #set($nilaitarikhmati="")
                  #end
                  
                  <td><input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 150px; text-align: right; text-transform:uppercase;" value="0.00" $disabledR class="$disabled"  onblur="validateModal(this,this.value,'$nilaitarikhmati');" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMati')"/></td>
                </tr>
                #else
                <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="hidden" style="width: 150px; text-align: right; text-transform:uppercase;" value="0.00" $disabledR class="$disabled"  onblur="validateModal(this,this.value,'$nilaitarikhmati');" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMati')"/>
                
                #end
               
                
                <tr>
                 <td>&nbsp;</td>
                  <td valign="top"><div align="right" class="style8">
                    <div align="left">Catatan</div>
                  </div></td>
                  <td valign="top">:</td>
                 <td>
                 
                 <textarea name="txtCatatan" id="txtCatatan" cols="50" rows="6" style="width: 300px;"  $disabledR class="$disabled"  >$catatan</textarea>                </td>
                </tr>
                
                 <tr id="tr_flag_daftar"  style="display:none">
                                        <td></td>
                                          <td  valign="top">Urusan Kemasukkan Maklumat Harta
                                          </td>
                                          <td valign="top">:</td>
                                          <td valign="top">
                                          
                                          
                                          #if($disabled != "disabled" )
                                          
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
                                          value="2" /> 
                                          Perbicaraan
                                          
                                          
                                          #else
                                          
                                          #set($text_daftar = "")
                                          #if($FLAG_DAFTAR == '1')
                                          #set($text_daftar = "PENDAFTARAN")
                                          #elseif($FLAG_DAFTAR == '2') 
                                          #set($text_daftar = "PERBICARAAN")                                         
                                          #end
                                          
                                          <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$disabled" />                                          
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
          </table></td>
        </tr>
      </table>
       #if($x!="disabled")
          <table width="100%">
                                <tr>
                                  <td><span class="style69 style5 style43 style54 style7"><em><span class="style6">Perhatian</span> : Sila pastikan label bertanda <span class="style6">*</span> diisi.</em></span></td>
                                </tr>
</table>
       #end
      
      </fieldset>
    <!--  
      <fieldset><legend>MAKLUMAT HARTA ALIH</legend>
    
<table width="100%" border="0">
  <tbody>
    <tr>
      <td width="200px" scope="row">Jenis Harta Alih <font color="red">*</font></td>
      #if($tutup=="")
      #set($x=$disabled)
     
      #end
       #if($tutup=="yes")
      #set($x="readonly")
      
      #end
       <td width="380px">: <select name="socJenisHartaAlih" id="socJenisHartaAlih" class="autoselect" onChange="setSelected(2,0,0,0);getJenisHa()" $x >
       	#if ($EventStatus == 0)
        <option value="0">SILA PILIH JENIS HARTA</option>
        #end
        #set ($selected = "")
	        #foreach($listJenis in $ViewJenisHa)
	        	#if ($EventStatus == 0)
		        	#if ($listJenis.idjenisha == $socJenisHa)
		        		#set ($selected = "selected")
		        	<option value="$listJenis.idjenisha" $selected>$listJenis.kod - $listJenis.keterangan</option>
		        	#else
		        	<option value="$listJenis.idjenisha">$listJenis.kod - $listJenis.keterangan</option>
		        	#end
	        	#end
	        	#if ($EventStatus == 2 || $EventStatus == 3 )
		        	#if ($listJenis.idjenisha == $idJenisHa)
		        		#set ($selected = "selected")
		        	<option value="$listJenis.idjenisha" $selected>$listJenis.kod - $listJenis.keterangan</option>
		        	#else
		        	<option value="$listJenis.idjenisha">$listJenis.kod - $listJenis.keterangan</option>
		        	#end
	        	#end
	        #end
 
      </select></td>
      <td width="330px" >Bahagian Simati</td>
      <td width="250px" >: 
          <input name="txtBhgnSimati1" type="text" id="txtBhgnSimati1" style="width: 30px; text-transform:uppercase;" onKeyUp="javascript:validatePoskod(this,this.value)" value="$bhgnmati1" size="4" maxlength="3" $disabled/> 
        / 
        <input  name="txtBhgnSimati2" type="text" id="txtBhgnSimati2" style="width: 30px;" onKeyUp="javascript:validatePoskod(this,this.value)" value="$bhgnmati2" size="4" maxlength="3" $disabled/>      </td>
    </tr>
    <tr>
      	<td scope="row">
     #if ($socJenisHa == "2")
     	No. Akaun
     #elseif ($socJenisHa == "4")
     	No. Rujukan UPT
     #elseif ($socJenisHa == "5")	
     	No. Ahli
     #elseif ($socJenisHa == "1")	
     	No. Ahli
     #elseif ($socJenisHa == "3")	
     	No. Daftar
     #else
     	No. Rujukan UPT
     #end      	</td>
      #if ($socJenisHa == 1 || $socJenisHa == 4 || $socJenisHa == 3 || $socJenisHa == 5) 
      	<td>: <input name="txtNoRujukan" id="txtNoRujukan" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" maxlength="25" value="$norujukan" $disabled/></td> 
      #elseif ($socJenisHa == 2)
      <td>: <input name="txtNoRujukan" id="txtNoRujukan" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" maxlength="25" value="$norujukan" $disabled onKeyUp="javascript:validatePoskod(this,this.value)"/></td> 
      #else
      <td>: 
          <input name="txtNoRujukan" id="txtNoRujukan" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" maxlength="25" value="$norujukan" $disabled readonly/>      </td>
      #end 
       <td>Nilai Tarikh Mati (RM)</td>
      <td>: <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 150px; text-align: right; text-transform:uppercase;" value="$nilaitarikhmati" $disabled onblur="validateModal(this,this.value,$nilaitarikhmati);" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMati')"/></td>
     </tr>
    <tr>
    #if ($socJenisHa == 1) 
      <td scope="row">No. Sijil</td>
      <td>: <input name="txtNoSijil" id="txtNoSijil" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" maxlength="50" value="$nosijil" $disabled/></td>
    #else
      <td scope="row">No. Sijil</td>
      <td>: 
          <input name="txtNoSijil" id="txtNoSijil" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" maxlength="50" value="$nosijil" readonly/>      </td>
    #end  
      <td>Nilai Tarikh Mohon (RM)</td>
      <td>: <input  name="txtNilaiTarikhMohon" id="txtNilaiTarikhMohon" type="text" style="width: 150px; text-align: right; text-transform:uppercase;" value="$nilaitarikhmohon" $disabled onblur="validateModal(this,this.value,$nilaitarikhmohon);"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohon')" /></td>
    </tr>
    <tr>
    #if ($socJenisHa == 1)
      <td scope="row">Bil. Unit</td>
      <td >: <input name="txtBilUnit" id="txtBilUnit" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" maxlength="10" value="$bilunit" $disabled onKeyUp="javascript:validatePoskod(this,this.value)"/></td>
    #else
      <td scope="row">Bil. Unit</td>
      <td>: 
          <input name="txtBilUnit" id="txtBilUnit" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" maxlength="10" value="$bilunit" readonly onKeyUp="javascript:validatePoskod(this,this.value)"/>      </td>
    #end  
      <td></td>
      <td></td>
    </tr>
    <tr>
     #if ($socJenisHa == 1)
      <td>Nilai Seunit</td>
      <td>: <input name="txtNilaiSeunit" id="txtNilaiSeunit" align="right" type="text" maxlength="10" style="width: 150px; text-align: right; text-transform:uppercase;" value="$nilaiseunit" $disabled onKeyUp="javascript:validatePoskod(this,this.value)" onblur="validateModal(this,this.value,$nilaiseunit);"/></td>
      #else
      <td>Nilai Seunit</td>
      <td>: 
          <input name="txtNilaiSeunit" id="txtNilaiSeunit" align="right" type="text" maxlength="10" style="width: 150px; text-align: right; text-transform:uppercase;" value="$nilaiseunit" readonly onKeyUp="javascript:validatePoskod(this,this.value)" onblur="validateModal(this,this.value,$nilaiseunit);"/>      </td>
    #end
      <td valign="top"></td>
      <td></td>
    </tr>
    <tr> 
      <td>
        <p>#if ($socJenisHa == "1")
          Lain
          #elseif ($socJenisHa == "2")
          Agensi
          #elseif ($socJenisHa == "3")
          Jenama          </p>
        <p>#elseif ($socJenisHa == "4")
      No Lot #elseif ($socJenisHa == "5")
          Agensi
          #else
          Lain
          #end </p></td>
      #if ($socJenisHa == "1" || $socJenisHa == "2" || $socJenisHa == "3" || $socJenisHa == "4" || $socJenisHa == "5" )
      <td>: <input  name="txtAgensi" id="txtAgensi" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" value="$agensi" $disabled/></td>
 	  #else
      <td>: <input  name="txtAgensi" id="txtAgensi" type="text" style="width: 150px; text-transform:uppercase; text-align: right;" value="$agensi" readonly/></td> 	  
      #end
      <td valign="top">&nbsp;</td>
      <td></td>
    </tr>
    <tr>
      <td valign="top" >&nbsp;</td>
      <td valign="top">&nbsp;</td>
	  <td valign="top">Catatan</td>
      <td><textarea name="txtCatatan" id="txtCatatan" cols="25" rows="4" style="width: 150px; text-transform:uppercase;" $disabled>$catatan</textarea>      </td>
    </tr>
    
  </tbody>
</table>

</fieldset>
-->
<p>
#end
  ##ONLINE $!skrin_online
  #if ($EventStatus == 1)
  
  #elseif ($EventStatus == 2)</p>
  
<p align="center"> 

	#if($id_Status != "169" 
		&& $id_Status != "21" 
		&& $id_Status != "64" 
		&& $id_Status != "163" 
		&& $id_Status != "164" 
		&& $id_Status != "165")
<!--  <input type="button" name="cmdKemaskini" value="Kemaskini" onClick="setSelected(2,0,0,0);getKemaskini()">
<input type="button" name="cmdHapus" value="Hapus" onClick="setSelected(2,0,0,0);getHapus()"> -->
	#end
	#if($boleh_kemaskini == "yes")
	#end
	
    #if ($socJenisHa == "3")
        #if($!skrin_online != "yes")
        <input type="button" name="cmdINTJPPH" id="cmdINTJPPH" value="Nilaian JPPH" onclick="javascript:intNilaianJPPH('$id','$idha')" />
        #end
    #end

    #if($open_button_online == "yes")
        #if($!skrin_online != "yes")
        <input type="button" name="cmdKemaskini1" id="cmdKemaskini1" value="Kemaskini" onClick="setSelected(2,0,0,0);getKemaskini()">
         	#if($flag_kemaskini_selesai != "yes")
         	<script>
         		document.getElementById('cmdKemaskini1').style.display = "none";
         	</script>
         	#end   
        #else 
        <input type="button" name="cmdSimpan" value="Simpan" onClick="setSelected(2,0,0,0);getSimpan_online('update')">
        #end
    <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="setSelected(2,0,0,0);getHapus()">
    	#if($flag_kemaskini_selesai != "yes")
        <script>
        	document.getElementById('cmdHapus1').style.display = "none";
     	</script>
        #end
        
    #end


#if($open_button_online == "yes")
<!-- #if($!skrin_online != "yes")
<input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onclick="cetakNilaiHarta('$NO_FAIL')"/>
#end -->
<input type="submit" name="cmdKembali3" id="cmdKembali3" value="Kembali"  onclick="setSelected(2,0,0,0);HAview()" />
#end
</p>
	#elseif ($EventStatus == 3)
	<p align="center">
		#if($open_button_online == "yes")
  
			#if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
  			<input type="button" name="cmdSimpan" value="Simpan" onClick="setSelected(2,0,0,0);getUpdate()">
  			#else
   			<input type="button" name="cmdSimpan" value="Simpan" onClick="setSelected(2,0,0,0);getSimpan_online('update')">  
   			<input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="setSelected(2,0,0,0);getHapus()">
  			#end
  
  			#if($!skrin_online != "yes")
			<input type="button" name="cmdBatal" value="Batal" onClick="setSelected(2,0,0,0);getBatal()">
			#end
			<input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali"  onclick="setSelected(2,0,0,0);HAview()" />
			#end
	</p>
		#else
	<p align="center">
		#if($open_button_online == "yes")
			#if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
		<input type="button" name="cmdSimpan" value="Simpan" onClick="setSelected(2,0,0,0);getSimpan()">
	  		#else
	   	<input type="button" name="cmdSimpan" value="Simpan" onClick="setSelected(2,0,0,0);getSimpan_online('insert')">
	  		#end
	  		
	  		#if($!skrin_online != "yes")
			<input type="button" name="cmdKosong" value="Batal" onClick="getCancel()" >
			#end
		<input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali"  onclick="setSelected(2,0,0,0);HAview()" />
		#end
	</p>
	#end

	<fieldset><legend>SENARAI HARTA ALIH</legend>
	#if($!skrin_online == "yes")
			<div id="info_skrin_daftar_sek8"></div>
     		<script>
 						parent.document.getElementById("info_skrin_daftar_sek8").innerHTML="<div class=\"warning_online_ppk\"><font color=\"black\"><b>* Wang tunai, saham, simpanan bank, KWSP, insurans, kenderaan, senjata api, barang kemas dan barang-barang berharga.</b></font></div>";
 			</script> #end
<!-- <table width="100%" bordercolor="#333333"> -->
	<table width="100%" >
		<tr>

	#if($showbuttontambah == "yes")
		#if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
	<!--
	<input type="button" name="cmdTambah" value="Tambah" onClick="setSelected(2,0,0,0);getFormHaBaru()" >
	-->
	#end

	#if($boleh_kemaskini == "yes")
	#end

	#if($open_button_online == "yes")
		<input type="button" name="cmdTambah1" id="cmdTambah1" value="Tambah" 
			onClick="setSelected(2,0,0,0);getFormHaBaru()" >
		#if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('cmdTambah1').style.display = "none";
                                </script>
    	#end
	
	#end

#end



#if($showbuttonkembali == "yes")
<!--
<input type="button" name="cmdTambah" value="Kembali" onclick="kembali_simati()" >
-->
#end
												</tr>
												
												<tr class="table_header">
													<td width="3%"><div align="center">Bil</div></td>
													<td width="40%">Jenis Harta Alih</td>
													<td width="25%">No. Rujukan UPT / No. Daftar / No. Akaun / No. Ahli</td>
								                   	<td width="32%"><div align="left">Dokumen</div></td>
												</tr>

								#if($listHa.size() > 0)
									#set ($cnt = 0)
									#set($peno=0)

									#foreach($list in $listHa)
										#set ($cnt = $cnt + 1)
								 		#set($peno=$peno+1)
										#set ($id3 = $list.id_Ha)
								
										#set($cat="")
										#if($list.id_Jenisha == 1)
											#if($list.nama_saham != "")
												#set($cat = "- $list.nama_saham ")
											#else
												#set($cat = "")
											#end
										
										#end

										#if($list.id_Jenisha == 2)
											#if($list.jenama != "")
												#set($cat = "- $list.jenama ")
											#else
												#set($cat = "")
											#end
										
										#end

										#if($list.id_Jenisha == 3)
											#if($list.jenama != "")
												#set($cat = "- $list.jenama ")
											#else
												#set($cat = "")
											#end
										
										#end

										#if($list.id_Jenisha == 4)
										#if($list.jenama != "")
										#set($cat = "- $list.jenama ")
										#else
										#set($cat = "")
										#end
										#end

										#if($list.id_Jenisha == 5)
										#if($list.jenama != "")
										#set($cat = "- $list.jenama ")
										#else
										#set($cat = "")
										#end
										#end
										
										#if($list.id_Jenisha == 6)
										#if($list.jenama != "")
										#set($cat = "- $list.jenama ")
										#else
										#set($cat = "")
										#end
										#end										
										
										#if($list.id_Jenisha == 7)
										#if($list.butiran != "")
										#set($cat = "- $list.butiran ")
										#else
										#set($cat = "")
										#end
										#end
										
										#if($list.id_Jenisha == 8)
										#if($list.butiran != "")
										#set($cat = "- $list.butiran ")
										#else
										#set($cat = "")
										#end
										#end

										#if($list.id_Jenisha == 9)
										#if($list.butiran != "")
										#set($cat = "- $list.butiran ")
										#else
										#set($cat = "")
										#end
										#end
										
										#if($list.id_Jenisha == 10)
										#if($list.butiran != "")
										#set($cat = "- $list.butiran ")
										#else
										#set($cat = "")
										#end
										#end

										#if($list.id_Jenisha == 11)
										#if($list.butiran != "")
										#set($cat = "- $list.butiran ")
										#else
										#set($cat = "")
										#end
										#end
										
										#if($list.id_Jenisha == 12)
										#if($list.butiran != "")
										#set($cat = "- $list.butiran ")
										#else
										#set($cat = "")
										#end
										#end
										
										#if($list.id_Jenisha == 98)
										#if($list.nilai_tm != "")
										#set($nt = $Util.formatDecimal($list.nilai_tm) )
										#set($cat = "- RM $nt")
										#else
										#set($cat = "")
										#end
										#end

											#if($peno%2!=0)											
												<tr bgcolor="white">
													<td class="row1"><div align="center">$cnt</div></td>
													<td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:edit_hartaalih('$list.idha')" class="style4"> $list.keterangan <span class="style10">$cat</span></a></div> </td>
													<td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$list.noDaftar</div></td>
													<td class="row1">
				                          				#if($id_Status != "169" 
				                          					&& $id_Status != "21" 
				                          					&& $id_Status != "64" 
				                          					&& $id_Status != "163" 
				                          					&& $id_Status != "164" 
				                          					&& $id_Status != "165")	
				                          					#if($open_button_online == "yes")  
										                   		<a href = "javascript:lampiranHartaHA('$list.idha','$!paramOnline','$!id');">
																	<img border="0" src="../img/plus.gif" width="20" height="15"/>
																</a><br>
															#end
														#end		
															 	$list.lampirans
									             	</td>
												</tr>
											#else
												<tr bgcolor="white">
													<td class="row2"><div align="center">$cnt</div></td>
													<td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:edit_hartaalih('$list.idha')" class="style4">$list.keterangan  <span class="style10">$cat</span></a></div>  </td>
													<td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$list.noDaftar</div></td>
													<td class="row2">
														#if($id_Status != "169" 
				                          					&& $id_Status != "21" 
				                          					&& $id_Status != "64" 
				                          					&& $id_Status != "163" 
				                          					&& $id_Status != "164" 
				                          					&& $id_Status != "165")	  
				                          					#if($open_button_online == "yes")   
										                   		<a href = "javascript:lampiranHartaHA('$list.idha','$!paramOnline','$!id');">
																	<img border="0" src="../img/plus.gif" width="20" height="15"/>
																</a><br>
															#end
														#end	
															 	$list.lampirans
									             	</td>
												</tr>
											
											#end

									#end
												<tr>
													<td colspan="4"></td>
												</tr>
											</table>
								#else

											<table width="100%">
                                      			<tr bgcolor="white">
													<td>
														<div align="left">Tiada Rekod</div>
													</td>
												</tr>
                                      		</table>

								#end

										#if($!skrin_online != "yes" && $source == "KeputusanPermohonan")
											<table width="100%" >
												<tr align="center">
													<td align="center">
														<input type="button" id="butonkeHartaAlih" value="Ke Keputusan Permohonan" onClick="javascript:jumptoKeputusan('$jenis_permohonan','$idPermohonan','$idPemohon','$idSimati','$idpermohonan_simati','$al_negeri')"/> 
													</td>
												</tr>
											</table>
										#else
										#end

											</fieldset>	
    
    										</td>
  										</tr>
									</table>
									
								  	#if($!skrin_online != "yes")
								 	<p align="right">CL - 01 - 81</p>
								 	#end 
      									</fieldset>
      								</td>
      							</tr>
      
        						<tr>
                					<td>
               
                					</td>
                				</tr>  
      						</table>
    
      					</div>
           
        				<div class="TabbedPanelsContent"></div>
    				</div>
  				</div>  
  			</td>
  		</tr>
	</table>
 #parse("app/ppk/paging_sek8.jsp") 
 #parse("app/ppk/headerppk_script.jsp")

</form>
</body>

<script>
function info(jenis) {
    //
	var url = "../x/${securityToken}/ekptg.view.utils.FormInfo?jenis="+jenis;
    var hWnd = window.open(url,'printuser','width=400,height=200, resizable=no,scrollbars=no');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus(); /**/
    //
    var title = 'Info';
	var w =1024;
	var h = 800;
    var left = (screen.width/2)-(w/2);
    //var top = (screen.height/2)-(h/2);
    //return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);

}

	<!-- TAB -->

function jumptoKeputusan(jenis_permohonan,idPermohonan,idPemohon,idSimati,id_Permohonansimati,negeriARB)
{
	document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&idpermohonan="+idPermohonan+"&idPemohon="+idPemohon+"&idSimati="+idSimati+"&idpermohonansimati="+id_Permohonansimati+"&source=KeputusanPermohonan&negeriARB="+negeriARB+"&jenisHA=98&tarikh_mohon=03/04/2017";
	document.f1.command.value="paparKeputusan";
	//document.f1.mode.value="view_harta_alih";
	document.f1.method="POST";
	document.f1.submit();
	/*
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnSek8Internal#maklumat_pemohon";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	*/
}
function validateModal(elmnt,content,content2) {
 //   alert(content)	
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

function HtaamView() {
	document.f1.method="post";
	document.f1.mode.value = "Htaamview";
	document.f1.command.value = "Htaam";
	document.f1.action = "";
	document.f1.submit();
}
function HAview() {
	document.f1.method="post";
	document.f1.mode.value = "view_harta_alih";
	document.f1.command.value = "harta_alih";
	document.f1.action = "";
	document.f1.submit();
}

function NAview() {
	document.f1.method="post";
	document.f1.mode.value = "view_nilai_harta";
	document.f1.command.value = "nilai_harta";
	document.f1.action = "";
	document.f1.submit();
}

function PenghutangView() {
	document.f1.method="post";
	document.f1.mode.value = "Penghutangview";
	document.f1.command.value = "Penghutang";
	document.f1.action = "";
	document.f1.submit();
}
function PemiutangView() {
	document.f1.method="post";
	document.f1.mode.value = "Pemiutangview";
	document.f1.command.value = "Pemiutang";
	document.f1.action = "";
	document.f1.submit();
}
function SaksiView() {
	document.f1.method="post";
	document.f1.mode.value = "Saksiview";
	document.f1.command.value = "Saksi";
	document.f1.action = "";
	document.f1.submit();
}

function PentingView() {
	document.f1.method="post";
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
	document.f1.action = "";
	document.f1.submit();
}

function WarisView() {
	document.f1.method="post";
	document.f1.mode.value = "Warisview";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
}
function SimatiView() {
	document.f1.method="post";
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.action = "";
	document.f1.submit();
}

function PemohonView() {
	document.f1.method="post";
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
	document.f1.action = "";
	document.f1.submit();
}
function kembali_simati(){
	document.f1.method="post";
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

<!-- HARTA ALIH -->
	function getFormHa(){
		document.f1.method="post";
		document.f1.command.value="harta_alih";
		document.f1.mode.value="tambah_harta";
		document.f1.eventStatus.value="0";
		document.f1.action="";		
		document.f1.v_tab.value = "txtAlamat1";
		document.f1.submit();
		
	}

	function getFormHaBaru(){		
		document.f1.command.value="harta_alih";
		document.f1.mode.value="tambah_harta_baru";
		document.f1.eventStatus.value="0";
		document.f1.action="";
		document.f1.method="post";
		document.f1.submit();
		
	}

	function getJenisHa(con){

		if(document.f1.socJenisHartaAlih.value !="0"){
			document.f1.method="post";
			document.f1.command.value="harta_alih";
			document.f1.mode.value="tambah_harta";
			document.f1.eventStatus.value="0";
			document.f1.action="";
	
			if(con== "1"
				||con== "2"
				||con== "3"
				||con== "4"
				||con== "5"){
				document.f1.v_tab.value = "txtAgensi";
/*			}else if(con== "2")
			{
			document.f1.v_tab.value = "txtAgensi";
			}
			else if(con== "3")
			{
			document.f1.v_tab.value = "txtAgensi";
			}
			else if(con== "4")
			{
			document.f1.v_tab.value = "txtAgensi";
			}
			if(con== "5")
			{
			document.f1.v_tab.value = "txtAgensi"; */
			
			}else if(con== "6"
				||con== "7"
				||con== "8"
				||con== "9"
				||con== "10"
				||con== "11"
				||con== "12"
				||con== "13"
				||con== "98"
				||con== "99"){
				document.f1.v_tab.value = "butiran";
 			}
/*			else if(con== "7")
			{
			document.f1.v_tab.value = "butiran";
			}
			else if(con== "8")
			{
			document.f1.v_tab.value = "butiran";
			}
			else if(con== "9")
			{
			document.f1.v_tab.value = "butiran";
			}
			else if(con== "10")
			{
			document.f1.v_tab.value = "butiran";
			}
			else if(con== "11")
			{
			document.f1.v_tab.value = "butiran";
			}
			else if(con== "12")
			{
			document.f1.v_tab.value = "butiran";
			}
			else if(con== "13")
			{
			document.f1.v_tab.value = "butiran";
			}
			else if(con== "98")
			{
			document.f1.v_tab.value = "butiran";
			}
			else if(con== "99")
			{
			document.f1.v_tab.value = "butiran";
			} */			
			document.f1.submit();
		
		}else{
			getFormHa();
		}

	}

function getSimpan(){
/*
if(document.f1.socJenisHartaAlih.value != 2 && document.f1.socJenisHartaAlih.value != 3 && document.f1.socJenisHartaAlih.value != 98 )
{
 var b1=parseInt(document.f1.txtBhgnSimati1.value);
 var b2=parseInt(document.f1.txtBhgnSimati2.value);
}
 if (document.f1.socJenisHartaAlih.value=="0"){
	alert("Sila pilih Jenis Harta Alih")
	document.f1.socJenisHartaAlih.focus(); 
	return; 
	}
	
else if((document.f1.socJenisHartaAlih.value != 2 && document.f1.socJenisHartaAlih.value != 3 && document.f1.socJenisHartaAlih.value != 98) && (document.f1.txtBhgnSimati1.value != "" && document.f1.txtBhgnSimati2.value == "") )
      {
       alert('Sila masuk bahagian simati selengkapnya');
	   document.f1.txtBhgnSimati2.focus(); 
	   return; 
       }
            
else if((document.f1.socJenisHartaAlih.value != 2 && document.f1.socJenisHartaAlih.value != 3 && document.f1.socJenisHartaAlih.value != 98) && (document.f1.txtBhgnSimati2.value != "" && document.f1.txtBhgnSimati1.value == "" ))
       {
        alert('Sila masuk bahagian simati selengkapnya');
	    document.f1.txtBhgnSimati1.focus(); 
	    return; 
        }
else if((document.f1.socJenisHartaAlih.value != 2 && document.f1.socJenisHartaAlih.value != 3 && document.f1.socJenisHartaAlih.value != 98) && (b1>b2) )
            {
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBhgnSimati1.focus(); 
			return; 
            }
			/*
	else if (document.f1.txtNilaiTarikhMati.value=="0.00" || document.f1.txtNilaiTarikhMati.value=="" ){
		alert("Sila masukkan nilai tarikh mati")
		document.f1.txtNilaiTarikhMati.focus(); 
	}
	else if (document.f1.txtNilaiTarikhMohon.value=="0.00" || document.f1.txtNilaiTarikhMohon.value==""){
		alert("Sila masukkan nilai tarikh mohon")
		document.f1.txtNilaiTarikhMohon.focus(); 
	}
	*/
	/*
	else if (document.f1.txtPoskod.value != "" && document.f1.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
		document.f1.txtPoskod.focus();
		return; 
	}
	
	*/
	
	//else{
		//alert('xxx ' + document.f1.socJenisHartaAlih.value);
		//if(document.f1.txtNilaiTarikhMohon.value == "" || document.f1.txtNilaiTarikhMati.value == "")
		//	{
		//	alert('Sila masukkan Nilaian Harta');
		//	return;
		//	}
		//else{
		input_box = confirm("Adakah anda pasti?");
		if (input_box==true) {
			document.f1.method="post";
			document.f1.command.value="harta_alih";
			document.f1.mode.value="simpan_harta";
			document.f1.eventStatus.value="1";
			document.f1.action="";
			document.f1.submit();
		}
		else
		{
		return;
		}
		
	//}
}

	function getSimpan_online(mode){
		var mod = mode;

		if (document.f1.socJenisHartaAlih.value=="0"){
			alert("Sila pilih Jenis Harta Alih")
			document.f1.socJenisHartaAlih.focus(); 
			return; 
		
		}else if (document.f1.socJenisHartaAlih.value=="1"){	
		/*if (document.f1.txtAgensi.value=="")
		{   alert("Sila masukkan maklumat Agensi")
			document.f1.txtAgensi.focus(); 
			return; 
		}
			else*/ 
			if (document.f1.nama_saham.value==""){   
				alert("Sila masukkan nama saham")
				document.f1.nama_saham.focus(); 
				return;
			
			/*}
			else if (document.f1.txtNoRujukan.value=="")
			{   alert("Sila masukkan No. Ahli")
				document.f1.txtNoRujukan.focus(); 
				return;
			}
			else if (document.f1.txtNoSijil.value=="")
			{   alert("Sila masukkan No. Sijil")
				document.f1.txtNoSijil.focus(); 
				return;
			}
			else if (document.f1.txtNilaiTarikhMohon.value=="")
			{   alert("Sila masukkan nilai anggaran tarikh mohon")
				document.f1.txtNilaiTarikhMohon.focus(); 
				return;
			}
			else if (document.f1.txtNilaiTarikhMati.value=="")
			{   alert("Sila masukkan nilai anggaran tarikh mati")
				document.f1.txtNilaiTarikhMati.focus(); 
				return;
			*/
			}else{
				simpan_ha_online(mod);
			}
		
		 }else if (document.f1.socJenisHartaAlih.value=="2"){	
			if (document.f1.txtAgensi.value==""){   
				alert("Sila masukkan maklumat agensi")
				document.f1.txtAgensi.focus(); 
				return; 
			
			}else if (document.f1.txtNoRujukan.value==""){   
				alert("Sila masukkan no. akaun")
				document.f1.txtNoRujukan.focus(); 
				return;
			/*}		
			else if (document.f1.txtNilaiTarikhMohon.value=="")
			{   alert("Sila masukkan nilai anggaran tarikh mohon")
				document.f1.txtNilaiTarikhMohon.focus(); 
				return;
			}
			else if (document.f1.txtNilaiTarikhMati.value=="")
			{   alert("Sila masukkan nilai anggaran tarikh mati")
				document.f1.txtNilaiTarikhMati.focus(); 
				return;
			*/}else{
				simpan_ha_online(mod);
			}
							
		 }else if (document.f1.socJenisHartaAlih.value=="3"){	
			if (document.f1.txtAgensi.value==""){   
				alert("Sila masukkan maklumat jenis dan nama kenderaan")
				document.f1.txtAgensi.focus(); 
				return; 
			
			}else if (document.f1.txtNoRujukan.value==""){   
				alert("Sila masukkan No. Daftar kenderaan")
				document.f1.txtNoRujukan.focus(); 
				return; 
			}else if (document.f1.txtNilaiTarikhMohon.value==""){   
				alert("Sila masukkan nilai anggaran tarikh mohon")
				document.f1.txtNilaiTarikhMohon.focus(); 
				return;
			}
			/* else if (document.f1.txtNilaiTarikhMati.value=="")
			{   alert("Sila masukkan nilai anggaran tarikh mati")
				document.f1.txtNilaiTarikhMati.focus(); 
				return;
			} */
			else{
				simpan_ha_online(mod);
			}	
			
		}else if (document.f1.socJenisHartaAlih.value=="4"){	
			if (document.f1.txtAgensi.value==""){
				alert("Sila masukkan maklumat no. hakmilik atau no. lot atau mukim ")
				document.f1.txtAgensi.focus(); 
				return; 
			}
		/*
		else if (document.f1.txtNoRujukan.value=="")
		{   alert("Sila masukkan no. rujukan UPT")
			document.f1.txtNoRujukan.focus(); 
			return;
		}		
		else if (document.f1.txtNilaiTarikhMohon.value=="")
		{   alert("Sila masukkan nilai anggaran tarikh mohon")
			document.f1.txtNilaiTarikhMohon.focus(); 
			return;
		}
		else if (document.f1.txtNilaiTarikhMati.value=="")
		{   alert("Sila masukkan nilai anggaran tarikh mati")
			document.f1.txtNilaiTarikhMati.focus(); 
			return;
		}*/
		else
		{
		simpan_ha_online(mod);
		}	
	 }
	 
	  else if (document.f1.socJenisHartaAlih.value=="5"){	
		if (document.f1.txtAgensi.value=="")
		{   alert("Sila masukkan maklumat agensi ")
			document.f1.txtAgensi.focus(); 
			return; 
		}
		
		else if (document.f1.txtNoRujukan.value=="")
		{   alert("Sila masukkan no. polisi")
			document.f1.txtNoRujukan.focus(); 
			return;
		}/*		
		else if (document.f1.txtNilaiTarikhMohon.value=="")
		{   alert("Sila masukkan nilai anggaran tarikh mohon")
			document.f1.txtNilaiTarikhMohon.focus(); 
			return;
		}
		else if (document.f1.txtNilaiTarikhMati.value=="")
		{   alert("Sila masukkan nilai anggaran tarikh mati")
			document.f1.txtNilaiTarikhMati.focus(); 
			return;
		}*/
		else
		{
		simpan_ha_online(mod);
		}	
	 }
	 
	 else if (document.f1.socJenisHartaAlih.value=="6"){	
		
		/*if (document.f1.butiran.value=="")
		{   alert("Sila masukkan butiran peti keselamatan")
			document.f1.butiran.focus(); 
			return;
		}
		else*/ if (document.f1.txtAgensi.value=="")
		{   alert("Sila masukkan maklumat agensi ")
			document.f1.txtAgensi.focus(); 
			return; 
		}		
		else if (document.f1.txtNoRujukan.value=="")
		{   alert("Sila masukkan no. peti")
			document.f1.txtNoRujukan.focus(); 
			return;
		}/*
		else if (document.f1.txtNilaiTarikhMati.value=="")
		{   alert("Sila masukkan nilai anggaran tarikh mati")
			document.f1.txtNilaiTarikhMati.focus(); 
			return;
		}*/
		else
		{
		simpan_ha_online(mod);
		}	
	 }
	 
	  else if (document.f1.socJenisHartaAlih.value=="7" || document.f1.socJenisHartaAlih.value=="8" || document.f1.socJenisHartaAlih.value=="9" || document.f1.socJenisHartaAlih.value=="10" || document.f1.socJenisHartaAlih.value=="11" || document.f1.socJenisHartaAlih.value=="12" || document.f1.socJenisHartaAlih.value=="98"){	
		
		if (document.f1.butiran.value=="")
		{ 
		if (document.f1.socJenisHartaAlih.value=="7")
		{  alert("Sila masukkan maklumat lokasi harta") }
		if (document.f1.socJenisHartaAlih.value=="8")
		{  alert("Sila masukkan butiran tuntutan") }
		if (document.f1.socJenisHartaAlih.value=="9")
		{  alert("Sila masukkan butiran senjata api") }
		if (document.f1.socJenisHartaAlih.value=="10")
		{  alert("Sila masukkan butiran barang berharga") }
		if (document.f1.socJenisHartaAlih.value=="11")
		{  alert("Sila masukkan butiran ternakan") }
		if (document.f1.socJenisHartaAlih.value=="12")
		{  alert("Sila masukkan butiran harta") }
		if (document.f1.socJenisHartaAlih.value=="98")
		{  alert("Sila masukkan butiran nilaian harta") }
			
		document.f1.butiran.focus(); 
		return;
		}
			
		/* else if (document.f1.txtNilaiTarikhMohon.value=="" && document.f1.socJenisHartaAlih.value=="98")
		{ 
		alert("Sila masukkan anggaran nilaian")
			document.f1.txtNilaiTarikhMohon.focus(); 
			return;	
		} */
		
			else{
				simpan_ha_online(mod);
			}	
	 	}
	
	}

	function simpan_ha_online(mode){
		var b1=parseInt(document.f1.txtBhgnSimati1.value);
		var b2=parseInt(document.f1.txtBhgnSimati2.value);
		
		if(document.f1.txtBhgnSimati1.value != "" && document.f1.txtBhgnSimati2.value == "" ){
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBhgnSimati2.focus(); 
			return; 
            
		}else if(document.f1.txtBhgnSimati2.value != "" && document.f1.txtBhgnSimati1.value == "" ){
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBhgnSimati1.focus(); 
			return; 
            
		} else if(b1>b2 ){
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBhgnSimati1.focus(); 
			return; 
            
		}else{ 
			
			input_box = confirm("Adakah anda pasti?");
			if (input_box==true) {
		
				document.f1.method="post";
				document.f1.command.value="harta_alih";
				if(mode == "insert"){
					document.f1.mode.value="simpan_harta";
					document.f1.eventStatus.value="1";
					
				}else{
					document.f1.mode.value="update_harta";
					document.f1.eventStatus.value="2";
					
				}					
				document.f1.action="";
				document.f1.submit();
				
			}else{
				return;
			}
			
		}	
				
	}

	function edit_hartaalih(id3){
		//internal/online
		document.f1.method="post";
		document.f1.command.value="harta_alih";
		document.f1.mode.value="edit_harta";
		document.f1.eventStatus.value="2";
		document.f1.idha.value=id3;
		document.f1.action="";
		document.f1.submit();
				
	}
	
	function getKemaskini(){
		document.f1.method="post";
		document.f1.command.value="harta_alih";
		document.f1.mode.value="kemaskini_harta";
		document.f1.eventStatus.value="3";
		document.f1.action="";
		document.f1.submit();
		
	}
	
	function getHapus(){
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.f1.method="post";
			document.f1.command.value="harta_alih";
			document.f1.mode.value="hapus_harta";
			document.f1.eventStatus.value="1";
			document.f1.action="";
			document.f1.submit();
			
		}
		
	}

	function getCancel(){
	   	input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.f1.method="post";
			document.f1.command.value="harta_alih";
			document.f1.mode.value="tambah_harta_baru";
			document.f1.eventStatus.value="0";
			document.f1.action="";
			document.f1.submit();
			
		}else{return;}
	
	}

function getCancel3()
{
	document.f1.command.value="harta_alih";
	document.f1.mode.value="negerichange";
	document.f1.action="";
	document.f1.submit();

	}
	
	function getUpdate(){
	//alert("document.f1.socJenisHartaAlih.value = "+document.f1.socJenisHartaAlih.value);
		if(document.f1.socJenisHartaAlih.value != 2 
			&& document.f1.socJenisHartaAlih.value != 3 
			&& document.f1.socJenisHartaAlih.value != 98 ){
 			var b1=parseInt(document.f1.txtBhgnSimati1.value);
 			var b2=parseInt(document.f1.txtBhgnSimati2.value);
 		}
	
		if (document.f1.socJenisHartaAlih.value=="0"){
			alert("Sila pilih Jenis Harta Alih")
			document.f1.socJenisHartaAlih.focus(); 
			return; 
		
		}else if((document.f1.socJenisHartaAlih.value != 2 
			&& document.f1.socJenisHartaAlih.value != 3 
			&& document.f1.socJenisHartaAlih.value != 98) 
			&& (document.f1.txtBhgnSimati1.value != "" 
			&& document.f1.txtBhgnSimati2.value == "") ){
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBhgnSimati2.focus(); 
			return; 
            
		}else if((document.f1.socJenisHartaAlih.value != 2 
			&& document.f1.socJenisHartaAlih.value != 3 
			&& document.f1.socJenisHartaAlih.value != 98) 
			&& (document.f1.txtBhgnSimati2.value != "" 
			&& document.f1.txtBhgnSimati1.value == "" )){
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBhgnSimati1.focus(); 
			return; 
            
		}else if((document.f1.socJenisHartaAlih.value != 2 
			&& document.f1.socJenisHartaAlih.value != 3 
			&& document.f1.socJenisHartaAlih.value != 98) && (b1>b2) ){
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBhgnSimati1.focus(); 
			return; 
			/*}			
	else if (document.f1.txtNilaiTarikhMati.value=="0.00" || document.f1.txtNilaiTarikhMati.value=="" ){
		alert("Sila masukkan nilai tarikh mati")
		document.f1.txtNilaiTarikhMati.focus(); 
	}else if (document.f1.txtNilaiTarikhMohon.value=="0.00" || document.f1.txtNilaiTarikhMohon.value==""){
		alert("Sila masukkan nilai tarikh mohon")
		document.f1.txtNilaiTarikhMohon.focus(); 
	}else if (document.f1.txtPoskod.value != "" && document.f1.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
		document.f1.txtPoskod.focus();
		return; */
	
		}else{
			input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {
				document.f1.method="post";
				document.f1.command.value="harta_alih";
				document.f1.mode.value="update_harta";
				document.f1.eventStatus.value="2";
				document.f1.action="";
				document.f1.submit();
		
			}else{
				return;
			}
		
		}
		
	}

function getSimpanXX(){
if(document.f1.socJenisHartaAlih.value != 2 && document.f1.socJenisHartaAlih.value != 3 && document.f1.socJenisHartaAlih.value != 98 )
{
 var b1=parseInt(document.f1.txtBhgnSimati1.value);
 var b2=parseInt(document.f1.txtBhgnSimati2.value);
 }
 
	if (document.f1.socJenisHartaAlih.value=="0"){
		alert("Sila pilih Jenis Harta Alih")
		document.f1.socJenisHartaAlih.focus(); 
			return; 
	}
	
	        else if((document.f1.socJenisHartaAlih.value != 2 && document.f1.socJenisHartaAlih.value != 3 && document.f1.socJenisHartaAlih.value != 98) && (document.f1.txtBhgnSimati1.value != "" && document.f1.txtBhgnSimati2.value == "") )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBhgnSimati2.focus(); 
			return; 
            }
            
            else if((document.f1.socJenisHartaAlih.value != 2 && document.f1.socJenisHartaAlih.value != 3 && document.f1.socJenisHartaAlih.value != 98) && (document.f1.txtBhgnSimati2.value != "" && document.f1.txtBhgnSimati1.value == "" ))
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBhgnSimati1.focus(); 
			return; 
            }
                        
            else if((document.f1.socJenisHartaAlih.value != 2 && document.f1.socJenisHartaAlih.value != 3 && document.f1.socJenisHartaAlih.value != 98) && (b1>b2) )
            {
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBhgnSimati1.focus(); 
			return; 
            }
			/*
	else if (document.f1.txtNilaiTarikhMati.value=="0.00" || document.f1.txtNilaiTarikhMati.value=="" ){
		alert("Sila masukkan nilai tarikh mati")
		document.f1.txtNilaiTarikhMati.focus(); 
	}
	else if (document.f1.txtNilaiTarikhMohon.value=="0.00" || document.f1.txtNilaiTarikhMohon.value==""){
		alert("Sila masukkan nilai tarikh mohon")
		document.f1.txtNilaiTarikhMohon.focus(); 
	}
	*/
	/*
	else if (document.f1.txtPoskod.value != "" && document.f1.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
		document.f1.txtPoskod.focus();
		return; 
	}
	
	*/
	
	else{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.method="post";
		document.f1.command.value="harta_alih";
		document.f1.mode.value="update_harta";
		document.f1.eventStatus.value="2";
		document.f1.action="";
		document.f1.submit();
	
	}
	else
		{return;}
		
		}
}
function getBatal(){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.method="post";
		document.f1.command.value="harta_alih";
		document.f1.mode.value="kemaskini_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
		}else
		{return;}
}


/*

function saham()
{
if(document.f1.socJenisHartaAlih.value="1")
{
var bah=1;
if(document.f1.txtBhgnSimati1.value != "" && document.f1.txtBhgnSimati2.value !="" )
{
var b1 = parseInt(document.f1.txtBhgnSimati1.value);
var b2 = parseInt(document.f1.txtBhgnSimati2.value);
bah = b1/b2;
}
if(document.f1.txtBilUnit.value != "" && document.f1.txtNilaiSeunit.value != "" )
{
var units = document.f1.txtBilUnit.value;
var nilaiseunit = document.f1.txtNilaiSeunit.value;
var jum=units*nilaiseunit*bah;
document.f1.txtNilaiTarikhMati.value=jum.toFixed(2);
document.f1.txtNilaiTarikhMohon.value=jum.toFixed(2);

}
else
{
document.f1.txtNilaiTarikhMati.value="";
document.f1.txtNilaiTarikhMohon.value="";
}
}
}
*/


function saham()
{
//alert(document.f1.socJenisHartaAlih.value)



if(document.f1.socJenisHartaAlih.value=="1")
{
var bah=1;
if(document.f1.txtBhgnSimati1.value != "" && document.f1.txtBhgnSimati2.value !="" )
{
var b1 = parseInt(document.f1.txtBhgnSimati1.value);
var b2 = parseInt(document.f1.txtBhgnSimati2.value);
bah = b1/b2;
}
if(document.f1.txtBilUnit.value != "" && document.f1.txtNilaiSeunit.value != "" )
{
var units = document.f1.txtBilUnit.value;
var nilaiseunit = document.f1.txtNilaiSeunit.value;
var jum=units*nilaiseunit*bah;
document.f1.txtNilaiTarikhMati.value=jum.toFixed(2);
document.f1.txtNilaiTarikhMohon.value=jum.toFixed(2);
}
else
{
document.f1.txtNilaiTarikhMati.value="";
document.f1.txtNilaiTarikhMohon.value="";
}
}

else
{
return false;
}


}

	function negerichange(v_t){
		document.f1.command.value="harta_alih";
		document.f1.mode.value="negerichange";
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
		
	}

	
	function negerichangeU(v_t){
		document.f1.command.value="harta_alih";
		document.f1.mode.value="negerichangeU";
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
	
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

<!-- SIMATI -->

function cetakNilaiHarta(noFail) 
{

 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=NilaianHartaPPSPP&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
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
/*
function submitForm() {    
   // document.val.focus();
	goTo('$val_tab');
	Effect.ScrollTo('$val_tab').focus(); return false;
	//var a = '$val_tab'
	//document.f1.a.focus();
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

function bahagiansimati() { 
if(document.f1.txtBhgnSimati1.value != "" && document.f1.txtBhgnSimati2.value != "")
{
if(document.f1.txtBhgnSimati1.value == document.f1.txtBhgnSimati2.value )
{
document.f1.txtBhgnSimati1.value = "1";
document.f1.txtBhgnSimati2.value = "1";
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
	document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal";
	document.f1.submit();
}


function samakan()
{
document.f1.txtNilaiTarikhMati.value=document.f1.txtNilaiTarikhMohon.value
 
                 
}

	function lampiranHartaHA(idHarta,paramOnline, idPermohonan) {
		// alert(idPermohonan);
		var url = "../x/${securityToken}/ekptg.view.ppk.util.FrmUploadDokumen?actionrefresh=paparHA&actionPopup=paparHA&idHarta="+idHarta+"&flagOnline=$!flagOnline&idPermohonan="+idPermohonan;
		url +="&jenisdokumen=1107";
	    //
	    var hWnd = window.open(url,'printuser','width=400,height=200, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus(); /**/
	    //
	    var title = 'Cetakan';
		var w =1024;
		var h = 800;
	    var left = (screen.width/2)-(w/2);
	    //var top = (screen.height/2)-(h/2);
	    //return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	
	}
	function lampiranHartaPapar(id_){
		var url = "../servlet/ekptg.view.ppk.util.LampiranByBlob?iDokumen="+id_+"&tablename=ha";
		//var url = "../servlet/ekptg.view.ppk.util.DisplayBlobHarta?iDokumen="+id_+"&tablename=ha";
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}	

	<!-- script integrasi -->
	// to cater for integration
	function intNilaianJPPH(ID_PERMOHONAN, ID_HA) {
		document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_HA=" + ID_HA;
		document.f1.method = "POST";
		document.f1.submit();
		
	}
	<!-- end script integrasi -->
	
</script>

<!-- </body> -->
</html>
