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
.style49 {color: #FF0000} 
.style50 {
	font-size: 9px;
	font-style: italic;
}
.style51 {color: #0000FF; font-size: 9px; }
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
<!--
<script>
$jquery(document).ready(function(){
$jquery('#socWaktuKematianSimati').timepickr({
format24: '{h:02.d}{m:02.d}',
convention: 24,
resetOnBlur:false
});
});
</script>
-->
</head>

<!-- onLoad="self.focus();document.f1.socWaktuKematianSimati.focus()" -->
#set ($namaDoC = "")

#foreach($listSupportingDoc in $ViewSupportingDoc)
#set($namaDoC = $listSupportingDoc.NAMA_DOKUMEN)
#end

<body  onload="submitForm();jeniswaktu2();qryHowOld();calculateTarikhLahirSimati()" >
<form name="f1" method="post" action=""  >
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="v_tab" id="v_tab" value="" />
	<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 	<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>

#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
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

#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    #set($id_Status = $list.id_Status)
    
    	<input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
     	<input name="idpermohonan" type="hidden"  value="$id"/>
    
    	<input name="idPermohonan" type="hidden"  value="$id"/>
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

#if($!skrin_online != "yes") 

#if($readmode == "disabled")
#set($readmodeR = "readonly")

#else
#set($readmodeR = "")

#end


#else


#if($open_button_online == 'no')
#set($readmodeR = "readonly")
#set($readmode = "disabled")

#else
#set($readmodeR = "")
#set($readmode = "")

#end

#end





#set($md=$listtarikhMohon)
              	<input type="hidden" name="txdTarikhMohon" value="$listtarikhMohon" readonly="true"/>
          	 	<input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
             	<input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
            	<input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/>

			</td>
		</tr>

  		<tr>
    		<td>
    			<div id="div_warning" class="TabbedPanels_">
    			
				</div>
			</td>	
		</tr>		
				
  		<tr>
    		<td>
    			<div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(0,0,0,0);SimatiView()" >PERMOHONAN</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(1,0,0,0);HtaamView()">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(2,0,0,0);HAview()" >HARTA ALIH</li>
        #if($!skrin_online != "yes")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(3,0,0,0);NAview()" >NILAIAN HARTA</li>
      #else
      #if($!hideTabPengesahan == "show")
      <li class="TabbedPanelsTab style1 style3" tabindex="0"      onClick="setSelected(3,0,0,0);NAview()" >PENGESAHAN PERMOHONAN</li>
      #end
      #end
      
      
    
      
      
      
    </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
     
        <div id="TabbedPanels2" class="TabbedPanelsContentVisible">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0"  id="tab_Simati" onClick="setSelected(0,0,0,0);SimatiView()" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(0,1,0,0);PemohonView()">PEMOHON</li>
            
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(0,2,0,0);WarisView()">WARIS</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(0,3,0,0);PentingView()">ORANG BERKEPENTINGAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(0,5,0,0);PemiutangView()">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
          </ul>
          
          
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent">
            
            #parse("app/ppk/info_berjaya_disimpan.jsp")
            
            
            
           
              <table width="100%" border="0" >
            
              
                <tr>
                  <td>
                    <p>#foreach($listmati in $listSimati)                    </p>
                    <fieldset>
                    <legend>MAKLUMAT SIMATI</legend>

					#if ($setmode != "disabled")
					<table width="100%">
					 <tr>
					    <td><span class="style44"><em>Perhatian</em></span><span class="style50"><em> : Sila masukkan salah satu nombor MyID dan pastikan label bertanda <span class="style49">*</span> diisi.</em></span></td>
					  </tr>
					  </table>
					#end
                    
                    <table width="100%">
                      <tr>
                        <td width="50%">
                       
                            <table width="100%" border="0">
                             #if($flagForView == 'yes')
                             
                             <!--
                             
                             <tr> 
                                <td width="1%"><span class="style38"></span></td>
                                <td width="28%"><div align="left" class="style38">MyID Baru</div></td>
                                <td width="1%" class="style36" >:</td>
                                
                                <td width="70%" class="style36" >
                               
                                  
                             
                                     <input name="txtNoKPBaru1Simati" id="txtNoKPBaru1Simati" style="width: 50px;" type="text" value="$listmati.noKpBaru1" size="7" maxlength="6" readonly   class="disabled"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Simati')"  onblur="qryHowOld()" />
                        
                -
                
  <input name="txtNoKPBaru2Simati" id="txtNoKPBaru2Simati" style="width: 20px;" type="text" value="$listmati.noKpBaru2" size="3" maxlength="2" readonly   class="disabled"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Simati')"  />
 
                -
               
  <input name="txtNoKPBaru3Simati" id="txtNoKPBaru3Simati"  style="width: 40px;" type="text" value="$listmati.noKpBaru3" readonly   class="disabled"  size="5" maxlength="4"  onblur="jantinaic();" />
 
  
  <input type="hidden" name="txdTarikhLahirSimati" id="txdTarikhLahirSimati" value=""  />
  #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style51">  www.jpn.gov.my</a>
  #end  
  <div id="checkkp_result"> </div></td>
                              </tr>
                              <tr>
                                <td ><span class="style38"></span></td>
                                <td ><div align="left" class="style38">MyID Lama</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" ><label>
                                  <input name="txtNoKPLamaSimati" type="text" id="textfield4" style="text-transform:uppercase;" value="$listmati.noKpLama" size="10" maxlength="9" readonly   class="disabled"  onBlur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td ><span class="style38"></span></td>
                                <td ><div align="left" class="style38">Lain-lain KP</div></td>
                                <td class="style36">:</td>
                                <td class="style36"> 
                                
                                 #if($flagForView == 'yes')
                                  
                                  #if($listmati.jenisKp=="5")
                                  #set($jkp="Tentera")
                                  
                                  #elseif($listmati.jenisKp=="6")
                                  #set($jkp="Polis")
                                  
                                  #elseif($listmati.jenisKp=="4")
                                  #set($jkp="Pasport")
                                  
                                  #else
                                  #set($jkp="")
                                  #end
                                  
                                  #if($jkp=="")
                                  <input name="textfield4" type="text" id="textfield" value="" size="10" maxlength="9"  readonly   class="disabled" onBlur="this.value=this.value.toUpperCase()"        />
                                  #else
                                  <input name="textfield4"  type="text" id="textfield" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$jkp"  size="10" maxlength="9" readonly   class="disabled" />
                                  #end
                                  <input name="socJenisKPLainSimati" type="hidden" id="socJenisKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$listmati.jenisKp"  />
                                  #else
                                  <select name="socJenisKPLainSimati" id="socJenisKPLainSimati" style="width: 180px;" onChange="kplain(this.value)" onBlur="kplainX(this.value)" />
	 								   #if($listmati.jenisKp=="4")
	                                    <option value="4" selected>PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5" >TENTERA</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #elseif($listmati.jenisKp=="5")
                                         <option value="5" selected>TENTERA</option>
										<option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
                                       <option value="0" >-KOSONGKAN-</option>
	                                  
	 								   #elseif($listmati.jenisKp=="6")
                                       <option value="6" selected>POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #else
	                                    <option value="0">SILA PILIH JENIS KP</option>
	                                    <option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5">TENTERA</option>
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
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">MyID Lain</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" >
                                #if($readmode == "disabled")
                                 <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="10" maxlength="9"  readonly   class="disabled" />
                                 #else
                               
                                <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="10" maxlength="9" readonly   class="disabled" />
                                #end</td>
                              </tr>
                             -->
                             
                             
                             #else
                             
                               #end
                             
                             <tr>
                                <td><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                                <td width="28%"><div align="left" class="style38">MyID Baru</div></td>
                                <td width="1%" class="style36" >:</td>
                                
                                <td width="70%" class="style36" >
                               
                           
                                     <input name="txtNoKPBaru1Simati" id="txtNoKPBaru1Simati" style="width: 50px;" type="text" value="$listmati.noKpBaru1" size="7" maxlength="6" $readmodeR class="$readmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Simati')" />
                         
                -
                
  <input name="txtNoKPBaru2Simati" id="txtNoKPBaru2Simati" style="width: 20px;" type="text" value="$listmati.noKpBaru2" size="3" maxlength="2" $readmodeR class="$readmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Simati')"  />
 
                -
               
  <input name="txtNoKPBaru3Simati" id="txtNoKPBaru3Simati"  style="width: 40px;" type="text" value="$listmati.noKpBaru3" $readmodeR class="$readmode" size="5" maxlength="4"  onblur="calculateTarikhLahirSimati();jantinaic();" />
  
  
  <input type="hidden" name="txdTarikhLahirSimati" id="txdTarikhLahirSimati" value=""  />
  #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
  #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style51">  www.jpn.gov.my</a>
  #end 
  #end 
  <div id="checkkp_result"> </div></td>
                              </tr>
                              <tr>
                                <td ><span class="style38"></span></td>
                                <td ><div align="left" class="style38">MyID Lama</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" ><label>
                                  <input name="txtNoKPLamaSimati" type="text" id="textfield4" style="text-transform:uppercase;" value="$listmati.noKpLama" size="15" maxlength="15" $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td ><span class="style38"></span></td>
                                <td ><div align="left" class="style38">Jenis MyID Lain</div></td>
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
                                  
                                  #elseif($listmati.jenisKp=="13")
                                  #set($jkp="Perintah Mahkamah Tinggi")
                                  
                                  
                                  #else
                                  #set($jkp="")
                                  #end
                                  
                                  #if($jkp=="")
                                  <input name="textfield4" type="text" id="textfield" value="" size="15" maxlength="15"  $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()"      $readmode  />
                                  #else
                                  <input name="textfield4"  type="text" id="textfield" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$jkp"  size="15" maxlength="15" $readmodeR class="$readmode" />
                                  #end
                                  <input name="socJenisKPLainSimati" type="hidden" id="textfield" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$jkp"  size="15" $readmodeR class="$readmode" />
                                  #else
                                  <select name="socJenisKPLainSimati" id="select" style="width: 180px;" onChange="kplain(this.value)" onBlur="kplainX(this.value)" />
	 								   #if($listmati.jenisKp=="4")
	                                    <option value="4" selected>PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5" >TENTERA</option>
	                                    <option value="13" >PERINTAH MAHKAMAH TINGGI</option>
                                          <option value="7" >LAIN-LAIN</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #elseif($listmati.jenisKp=="5")
                                         <option value="5" selected>TENTERA</option>
										<option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="13" >PERINTAH MAHKAMAH TINGGI</option>
                                         <option value="7" >LAIN-LAIN</option>
                                       <option value="0" >-KOSONGKAN-</option>
	                                  
	 								   #elseif($listmati.jenisKp=="6")
                                       <option value="6" selected>POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
	                                    <option value="13" >PERINTAH MAHKAMAH TINGGI</option>
                                         <option value="7" >LAIN-LAIN</option>
                                         
                                         <option value="0" >-KOSONGKAN-</option>
                                          #elseif($listmati.jenisKp=="7")
                                           <option value="7" selected>LAIN-LAIN</option>
                                       <option value="6" >POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
                                        <option value="13" >PERINTAH MAHKAMAH TINGGI</option>
                                         <option value="0" >-KOSONGKAN-</option>
                                         
                                         #elseif($listmati.jenisKp=="13")
                                           <option value="13" selected>PERINTAH MAHKAMAH TINGGI</option>
                                       <option value="6" >POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
                                        <option value="7" >LAIN-LAIN</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #else
	                                    <option value="0">SILA PILIH JENIS KP</option>
	                                    <option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5">TENTERA</option>
	                                    <option value="13" >PERINTAH MAHKAMAH TINGGI</option>
                                         <option value="7" >LAIN-LAIN</option>
                                         
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
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">MyID Lain</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" >
                                #if($readmode == "disabled")
                                 <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="30" maxlength="35"  $readmodeR class="$readmode" />
                                 #else
                               
                                <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="30" maxlength="35" $readmodekp />
                                #end</td>
                              </tr>
                              
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Tarikh Lahir Simati</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" >
                                
                                <input name="tarikhLahirSimati" type="text" id="tarikhLahirSimati" style="text-transform:uppercase;" value="$listmati.tarikhLahirSimati" size="15" maxlength="15" $readmodeR class="$readmode" onBlur="calculateUmurSimati();" />
                                #if($readmode!="disabled")
                                <a href="javascript:displayDatePicker('tarikhLahirSimati',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
                                </td>
                              </tr>
                             
                             
                           
                              
                              <tr>
                                <td valign="top"><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                                <td><div align="left" class="style38">#if($readmode == "disabled")Nama Simati                               #else
                                 Nama Simati
                                  #end
                                </div></td>
                                <td class="style36" >:</td>
                                <td class="style36" ><label>
                                  <input name="txtNamaSimati" type="text" id="textfield6" style="text-transform:uppercase;" value="$listmati.namaSimati" size="34" maxlength="200" $readmodeR class="$readmode"  onblur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Nama Lain</div></td>
                                <td class="style36" style="text-transform:uppercase;">:</td>
                                <td class="style36" style="text-transform:uppercase;"><label>
                                  <input name="txtNamaLainSimati" type="text" id="textfield" style="text-transform:uppercase;" value="$listmati.namaLain"  size="34" maxlength="30" $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Jantina</div></td>
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
                                  <input name="socJantinaSi" type="text" id="textfield" value=""  size="10" maxlength="9" $readmodeR class="$readmode" />
                                  #else
                                  <input name="socJantinaSi" type="text" id="textfield" style="text-transform:uppercase;" value="$sex" $readmodeR class="$readmode" size="10" maxlength="9"  />
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
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Agama</div></td>
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
	                               
	                               
                                      
                              
                                    <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">ISLAM</option>
                                    <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    
                              
                                      
	                               #elseif($listmati.jenisAgama=="2")
	                               
                                      
                              
                                    <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">ISLAM</option>
                                    
                              
                                      
	                               #else
	                               
                                      
                              
                                    
                                    <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">ISLAM</option>
                                    <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    
                              
                                      
	                               #end
                                      
                                    
                                    
                            
                                  </select>
                                  #end </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                                <td><div align="left" class="style38">Warganegara</div></td>
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
                                  <input name="socWarganegaraSi" type="text" style="text-transform:uppercase;" onBlur="uppercase()"  id="textfield" value=""  size="34" $readmodeR class="$readmode" />
                                  #else
                                  <input name="socWarganegaraSi" type="text" style="text-transform:uppercase;" onBlur="uppercase()"  id="textfield" value="$wr"  size="34" $readmodeR class="$readmode" />
                                  #end
                                  <input name="socWarganegaraSimati" style="text-transform:uppercase;" onBlur="uppercase()"  type="hidden" id="textfield" value="$wr"  size="30" $readmode $readmodeR class="$readmode" />
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
                                <td valign="top"><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></span></td>
                                <td><div align="left" class="style38">Bukti Kematian</div></td>
                                <td>:</td>
                                <td>
                                
                                #if($listmati.idBuktimati=="")
                                #set($bukti="") 
                                #end
                                
                                #foreach($listbuk in $listbuktimati)  
                                                           
                                #if($listmati.idBuktimati==$listbuk.id_Buktimati)                                
                                #set($bukti="$listbuk.kod - $listbuk.keterangan")  
                                #end
                                
                                #end
                 
                                #if($readmode=="disabled")  
                                
                                  <input name="txtBuktiKematian" type="text" id="textfield" value="$bukti"  $readmodeR class="$readmode" size="34" onBlur="this.value=this.value.toUpperCase()"  />
                                  <input name="socBuktiKematianSimati" type="hidden" onBlur="this.value=this.value.toUpperCase()" id="textfield" value="$listmati.idbuktikematian"  size="30" $readmodeR class="$readmode" />
                                  
                                #else
                                  
                                #if($listmati.idBuktimati!="")
                                  <select name="socBuktiKematianSimati" class="autoselect" onChange="jenis_hutangU(this.value)" >
                                      <option value="$listmati.idBuktimati">$bukti</option>                         
                                     
                                #foreach($listbuk in $listbuktimati)                                 
                                #if($listmati.idBuktimati!=$listbuk.id_Buktimati)
                                    <option value="$listbuk.id_Buktimati">$listbuk.kod -  $listbuk.keterangan</option>
                                #end    
	                              #end
                                  </select>
                                #else
                                  
                                  <select name="socBuktiKematianSimati" class="autoselect" onChange="jenis_hutangU(this.value)" >
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
	                                     #if($listmati.idBuktimati=="1" || $listmati.idBuktimati=="4"  || $listmati.idBuktimati=="3")
	                                      	#set($readmodesy="")       
	                                     #else
	                                      	#set($readmodesy="disabled")
	                                     #end       
	                                     
	                                     #if($listmati.idBuktimati=="2")   
	                                      	#set($readmodeDateAkuan="")   
	                                     #else
	                                      	#set($readmodeDateAkuan="disabled")
	                                     #end                                                               
                                     #end
                          
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">No Sijil Mati / Permit Menguburkan</div></td>
                                <td class="style36" style="text-transform:uppercase;">:</td>
                                <td class="style36" style="text-transform:uppercase;"><label>
                              
                                #if($readmode == "disabled")
                                 <input name="txtNoSijilMatiSimati" onBlur="this.value=this.value.toUpperCase()" type="text" id="txtNoSijilMatiSimati" style="text-transform:uppercase;" value="$listmati.noSijilMati" size="30" maxlength="100" $readmodeR class="$readmode"/>
																	#else
                                  <input name="txtNoSijilMatiSimati" onBlur="this.value=this.value.toUpperCase()" type="text" id="txtNoSijilMatiSimati" style="text-transform:uppercase;" value="$listmati.noSijilMati" size="30" maxlength="15" $readmodesy />
                                #end
                                <a href="javascript:info('sijil')" class="help" title="info">					
																	<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
																</a>
                                </label></td>
                              </tr>
                              
                              <tr>
                               <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Tarikh Surat Akuan</div></td>
                                <td class="style36" style="text-transform:uppercase;">:</td>
                                <td class="style36" style="text-transform:uppercase;"><label>
                              
                                #if($readmode=="disabled")
                                  <input name="txdTarikhSuratAkuan" type="text" id="textfield" value="$!listmati.txdTarikhSuratAkuan"  size="10" maxlength="25"   $readmodeR class="$readmode" />                             
                                #else
                                  <input name="txdTarikhSuratAkuan" type="text" id="txdTarikhSuratAkuan" value="$!listmati.tarikhSuratAkuan" size="10" maxlength="25"  $readmodeDateAkuan   onBlur="trans_dateAkuan(this.value);"  />
                                  <a href="javascript:displayDatePicker('txdTarikhSuratAkuan',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> 
                                #end 
                                </label>
                              </tr>
                             
                              <tr>
                                <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                                <td ><div align="left" class="style38">
                                #if($readmode == "disabled") 
                                	Tarikh Mati                               
                                #else
                                	Tarikh Mati
                                #end
                                </div></td>
                                <td>:</td>
                                <td> 
                                	#if($readmode=="disabled")
	                                  	<input name="txdTarikhMati" type="text" id="textfield" value="$!listmati.tarikhMati"  size="10" maxlength="25"   $readmodeR class="$readmode" />
	                                    <input name="txdTarikhMati" type="hidden" id="textfield" value="$!listmati.tarikhMatidb"  size="9"   $readmodeR class="$readmode" />
                                  	#else
	                                  <!-- <input name="txdTarikhMatiSimati" type="text" id="txdTarikhMatiSimati" value="$!listmati.tarikhMati" size="10" maxlength="10"   $readmodeR class="$readmode"  onfocus="qryHowOld()" onBlur="trans_date(this.value);qryHowOld()"  /> -->
	                                  <input name="txdTarikhMatiSimati" type="text" id="txdTarikhMatiSimati" value="$!listmati.tarikhMati" size="10" maxlength="10"   $readmodeR class="$readmode"  onBlur="trans_date(this.value)"  /> <!-- addby aishah untuk onload terus umur dari tarikh mati date 7/10/2017 -->
	                                  <a href="javascript:displayDatePicker('txdTarikhMatiSimati',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> #end 
	                                  
	                                  <input name="mohondate" type="hidden" id="mohondate" value="$md" size="10" maxlength="10" $readmode  />
                                  	#if($readmode != "disabled" )
                                  		<span class="style52">dd/mm/yyyy</span>
                                  	#end
                                  	<a href="javascript:info('tarikh')" class="help" title="info">					
									                        <b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
									                  </a>                                   
								</td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Umur Ketika Mati</div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label>
                                  <input name="txtUmurSimati" type="text" id="txtUmurSimati" onKeyUp="javascript:validateIC(event,this,this.value,'txtUmurSimati')"  onblur="Checkumur()" value="$listmati.umur" size="4" maxlength="3"   $readmodeR class="$readmode" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Waktu Kematian</div></td>
                                <td class="style36">:</td>
                                <td class="style36"><input name="socWaktuKematianSimati" type="text" id="socWaktuKematianSimati" value="$listmati.masamati"  onKeyUp="javascript:validateIC(event,this,this.value,'socWaktuKematianSimati')" size="4" maxlength="4"   $readmodeR class="$readmode" onBlur="jeniswaktu1()" /> 
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
                                  
                                   <input name="jeniswaktu_d" type="text" id="jeniswaktu_d"    value="$waktu" size="4" maxlength="3" style="width:100"  $readmodeR class="$readmode" />
                                     <input name="jeniswaktu" type="hidden" id="jeniswaktu"    value="$listmati.jeniswaktu" />
                                  #else
                                  <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2()" >
                                  
                                  #if( $listmati.jeniswaktu == 1)
                                  
                                  <option value="1" id="op_pagi" >PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="4" id="op_malam">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>                                    
                                    #elseif($listmati.jeniswaktu == 2)
                                      <option value="2" id="op_tengahari">TENGAHARI</option>
                                     <option value="1" id="op_pagi">PAGI</option>                                 
                                    <option value="3" id="op_petang">PETANG</option>
                                    <option value="4" id="op_malam">MALAM</option>
                                  
                                      <option value="">SILA PILIH</option>
                                    #elseif($listmati.jeniswaktu == 3)
                                     <option value="3" id="op_petang">PETANG</option>
                                  <option value="1" id="op_pagi">PAGI</option>
                                   <option value="2" id="op_tengahari">TENGAHARI</option>                                   
                                    <option value="4" id="op_malam">MALAM</option>
                                      <option value="">SILA PILIH</option>
                                       #elseif($listmati.jeniswaktu == 4)
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                   <option value="2" id="op_tengahari">TENGAHARI</option>
                                    <option value="3" id="op_petang">PETANG</option>  
                                      <option value="">SILA PILIH</option>
                                    #else
                                  <option value="">SILA PILIH</option>
                                 
                                  <option value="1" id="op_pagi" >PAGI</option>                                 
                                  <option value="2" id="op_tengahari" >TENGAHARI</option>
                                  <option value="3"  id="op_petang" >PETANG</option>
                                  <option value="4" id="op_malam" >MALAM</option>            
                                     
                                    #end                                
                                    
                                 
                                  </select>
                                  #end
                                  </label>
                                  #if($readmode != "disabled" )
                                  <span class="style52">format 12 jam (HHMM)</span>
                                  #end                             
                                  
                                      </td>
                              </tr>
                          </table></td>
                        <td valign="top"><table width="100%">
                            <tr valign="top">
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                              <!-- <td width="1%" class="style38">&nbsp;</td> -->
                              <td width="28%" class="style38"><div align="right" class="style38">
                                <div align="left">Tempat Mati</div>
                              </div></td>
                              <td width="1%" style="text-transform:uppercase;">:</td>
                              <td width="70%" style="text-transform:uppercase;"><label>
                                                   
                              <textarea name="txtTempatMatiSimati" id="patMatiSimati"   cols="31" rows="3"    $readmodeR class="$readmode" >$listmati.tempatMati</textarea>
                              </label>
                              <a href="javascript:info('tempat')" class="help" title="info">					
									                <b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
									            </a> </td>
                            </tr>
                            <tr>
                              <!-- <td class="style38" valign="top" > -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                                 </td>
                                 
                              <td class="style38" valign="top">
                                <div align="left">#if($readmode == "disabled")
                                  Sebab Kematian
                                  #else </div>
                                <div align="right" >
                                  <div align="left">Sebab Kematian</div>
                                </div>
                                <div align="left">#end                              </div></td>
                              <td valign="top">:</td>
                              <td><textarea name="txtSebabKematianSimati" cols="31" rows="3" id="txtSebabKematian"   $readmodeR class="$readmode" >$listmati.sebabMati</textarea>
                              <a href="javascript:info('sebab')" class="help" title="info">					
									                <b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
									            </a>
                              </td>
                            </tr>
                            <tr>
                              <!-- <td class="style38">&nbsp;</td> -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                              <td class="style38"><div align="right" class="style38">
                                <div align="left">Alamat Terakhir</div>
                              </div></td>
                              <td >:</td>
                              <td ><label>
                                <input name="txtAlamatTerakhir1Simati" type="text" id="txtAlamatTerakhir" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$listmati.alamat1" size="34" maxlength="50"    $readmodeR class="$readmode"  />
                              </label>
                              <a href="javascript:info('alamat')" class="help" title="info">					
									                <b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
									            </a>
                              </td>
                            </tr>
                            <tr>
                              <td class="style38">&nbsp;</td>
                              <td class="style38"><div align="left"><span class="style3"></span></div></td>
                              <td >:</td>
                              <td ><label>
                                <input name="txtAlamatTerakhir2Simati" type="text" id="txtAlamatTerakhir2" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$listmati.alamat2" size="34" maxlength="50"   $readmodeR class="$readmode" />
                              </label></td>
                            </tr>
                            <tr>
                              <td class="style38">&nbsp;</td>
                              <td class="style38"><div align="left"><span class="style3"></span></div></td>
                              <td >:</td>
                              <td ><input name="txtAlamatTerakhir3Simati" type="text" id="txtAlamatTerakhir3" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$listmati.alamat3" size="34" maxlength="50"   $readmodeR class="$readmode" /></td>
                            </tr>
                            <tr>
                              <!-- <td class="style38">&nbsp;</td> -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                              <td class="style38"><div align="right" class="style38">
                                <div align="left">Poskod </div>
                              </div></td>
                              <td style="text-transform:uppercase;">:</td>
                              <td style="text-transform:uppercase;"><label>
                                <input name="txtPoskodSimati" type="text" id="txtPoskod " value="$listmati.poskod" size="5" maxlength="5"   $readmodeR class="$readmode" style="text-transform:uppercase;" onKeyUp="javascript:validateIC(event,this,this.value,'txtPoskodSimati')"/>
                              </label></td>
                            </tr>
                            <tr>
                              <!-- <td class="style38">&nbsp;</td> -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
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
                      <input name="socNegeri" type="text" id="textfield7"  style="width: 265px; text-transform:uppercase;" onBlur="uppercase()" value="$kod"  size="34"   $readmodeR class="$readmode" />                                        
                                        #else                                     
                                        
                                        #if($listmati.idnegeri!="" && $listmati.idnegeri!=0)
                                        <select name="socNegeriSimati" class="autoselect" id="socNegeriPemohon"  onchange="getBandarTetap('txtBandarSimati')" >
                                        <option value="$listmati.idnegeri" style="text-transform:uppercase;" onBlur="uppercase()">$kod</option>
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listmati.idnegeri!=$listneg.id_Negeri)
                       <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                     
                                  #end    
	                              #end
                                        </select>
                                        #else
                                        <select name="socNegeriSimati" class="autoselect" onChange="getBandarTetap('txtBandarSimati')">
                                          <option value="" >SILA PILIH NEGERI</option>
                                  #foreach($listneg in $listnegeri)
                   <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onBlur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                    
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
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                    <td valign="top" ></td>
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
                              <!-- <td class="style38">&nbsp;</td> -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
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
                      <input name="socBandar" type="text" id="socBandar"   style="width: 265px; text-transform:uppercase;" onBlur="uppercase()" value="$kodb"  size="34"   $readmodeR class="$readmode" />   
                      #else
                      <input name="socBandar" type="text" id="socBandar"  onblur="uppercase()" value=""  size="34"   $readmodeR class="$readmode" />  
                      
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
                                <select name="txtBandarSimati" id="txtBandarSimati" class="autoselect" $readmode   style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandarTetap()" >
                  <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                  
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              
                                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                  
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                </select>
                                #else
                                <select name="txtBandarSimati" id="txtBandarSimati" class="autoselect" $readmode   style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandarTetap()" >
                                  <option value="">Sila Pilih Bandar</option>
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                </select>
                                #end 
                                #end</label></td>
                            </tr>
                            <tr>
                              <td class="style38" valign="top">&nbsp;</td>
                              <td class="style38" valign="top"><div  align="right" class="style38">
                                <div align="left">Catatan</div>
                              </div></td>
                              <td valign="top">:</td>
                              <td><textarea name="txtCatatanSimati"  cols="31" rows="3" id="Catatan"   $readmodeR class="$readmode" >$listmati.catatan</textarea></td>
                       </tr>
                        </table></td>
                      </tr>
                      
                     
                      
                      
                    </table>
                                   
                     <!-- #if($readmode != "disabled") 
                    <table width="100%">
					  <tr>
					    <td><span class="style44">Perhatian</span><span class="style50"> : Sila masukkan salah satu nombor MyID  dan pastikan label bertanda <span class="style49">*</span> diisi.</span></td>
					  </tr>
					</table>
					#end -->
                    
                    </fieldset>
                    <p> #end </p>                </tr>
                    
                    
<tr>
            <td><fieldset>
              <legend>DOKUMEN SOKONGAN</legend>
              <table width="100%" border="0">
                #if ($Errormsg == "Error1")
                <tr>
                <td colspan="4" ><b><font color="red">Sila muatnaik dokumen sokongan terlebih dahulu.</font></b></td>
                </tr>
                #end
                <tr>
                  <td width="1%" valign="top"><!-- #if($setmode!="disabled")<span class="style49">*</span>#end --></td>
                  <td width="14%">#if($readmode!="disabled") Muatnaik Dokumen Sokongan #else
                    Dokumen Sokongan
                    #end </td>
                  <td width="1%">:</td>
                  <td width="84%"> 
                  #if($readmode!="disabled" &&  $namaDoC != "")
                  <input type="text" disabled value=$!namaDoC> &nbsp;&nbsp; <input name="deleteSuppDoc1" type="button" value="Padam" onclick="deleteSuppDoc()" />
                  #end
                  #if($readmode=="disabled" && $namaDoC != "")
                  <input name="cetak" type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />
                  <!-- <input type="text" disabled value=$!namaDoC> -->	
                  #end
                  
                  #if($readmode!="disabled" && $namaDoC == "") 
                  <input name="cetak" type="button" value="Tambah Dokumen Sokongan" onclick="uploadSuppDoc('$id','$idSimati')" />
                  <!-- <input id="fileupload" name="fileupload" type="file" size="40" onchange="uploadSuppDoc()">  -->
                  
                  #end
                    </td>
                </tr>
                 </table>
                 </fieldset></td>
            </tr>
                
                
                 <tr>
                     <td align="center">
                     
                     #if($open_button_online == "yes") 
                       
                                    #if($show_kemaskini_button=="yes")
                                    
               #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")                   
         <!--     <input type="button" name="cmdKemaskin2" id="cmdKemaskin2" value="Kemaskini" onClick="setSelected(0,0,0,0);kemaskini_simati()" /> -->
              #end   
              
              #parse("app/ppk/syarat_kemaskini.jsp")
             
                #if($boleh_kemaskini == "yes")
                #end  
                                                                                                                                                                                                                                                #if($!skrin_online != "yes") 
                <input type="button" name="cmdKemaskini1" id="cmdKemaskini1" value="Kemaskini" onClick="setSelected(0,0,0,0);kemaskini_simati()" />
                 #if($flag_kemaskini_selesai != "yes")
                <script>
                document.getElementById('cmdKemaskini1').style.display = "none";
                </script>
                #end   
                #else
                 <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onClick="setSelected(0,0,0,0);SimpanSimati()" />
               
                #end
                           
                   #end
                   
                   
						           #if($show_simpan_button=="yes")
                                   <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onClick="setSelected(0,0,0,0);SimpanSimati()" />                                  
                                    #end                                  
                                     
                                     #if($show_kemaskini_button!="yes")
                                   #if($!skrin_online != "yes")
                                      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onClick="setSelected(0,0,0,0);BatalSimati()"/>
                                  #end
                                    #end
                                    
                                    #end
                                    
                                    
                                    
                                  <!--
                                      <input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali_simati()" />                                   
                                      -->
                                       </td>
                </tr>
                <tr>
                <td>
                #if($!skrin_online != "yes")
                <p align="right">CL - 01 - 65</p> 
                #end               </td>
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
#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#end


#parse("app/ppk/paging_sek8.jsp") 
#parse("app/ppk/headerppk_script.jsp")

</form>

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

selectPelbagaiNegara(document.f1.socNegeriSimati.value,'div_mesejpelbagagainegara','tr_pelbagainegara','nama_pelbagainegara');
<!-- TAB -->

function testing()
{
alert('$!skrin_online');
}



function HtaamViewX() {

if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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
	
	document.f1.command.value = "kembali_simati";
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
}
else
{
document.f1.action = "";
}
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
	document.f1.mode.value = "kemaskini_simati";
	document.f1.command.value = "Simati";
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
}
else
{
document.f1.action = "";
}
	
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
   
   var checkBandar = false;
  
   if(document.f1.socNegeriSimati.value=="17"){
	   checkBandar = false;
   }else{
	   if(document.f1.txtBandarSimati.value == ""){
		   checkBandar = true;
	   }
   }
  // alert(document.f1.socNegeriSimati.value);
  // alert(warganegara);

   
	if (document.f1.txtNamaSimati.value=="") {
		alert("Sila masukkan nama simati.");
		document.f1.txtNamaSimati.focus();
		return; 
	} 
	else if (document.f1.txtSebabKematianSimati.value=="" && '$!skrin_online' != "yes") {
		alert("Sila masukkan sebab kematian.");
		document.f1.txtSebabKematianSimati.focus();
		return; 
	}
	else if(document.f1.txdTarikhMatiSimati.value == ""){
		alert('Sila masukkan " Tarikh mati " terlebih dahulu.');
  		document.f1.txdTarikhMatiSimati.focus(); 
		return; 
	}
	
	else if(document.f1.patMatiSimati.value == ""){
		alert('Sila masukkan " Tempat mati " terlebih dahulu.');
  		document.f1.patMatiSimati.focus(); 
		return; 
	}
	
	else if(document.f1.txtSebabKematian.value == ""){
		alert('Sila masukkan " Sebab kematian " terlebih dahulu.');
  		document.f1.txtSebabKematian.focus(); 
		return; 
	}
	else if(document.f1.txtAlamatTerakhir.value == ""){
		alert('Sila masukkan " Alamat terakhir " terlebih dahulu.');
  		document.f1.txtAlamatTerakhir.focus(); 
		return; 
	}
	else if(document.f1.txtPoskodSimati.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		document.f1.txtPoskodSimati.focus(); 
		return; 
	}
	else if(document.f1.socNegeriSimati.value == ""){
		alert('Sila masukkan " Negeri " terlebih dahulu.');
  		document.f1.socNegeriSimati.focus(); 
		return; 
	}
	
	else if(checkBandar == true){
		 
			alert('Sila masukkan " Bandar " terlebih dahulu.');
	  		document.f1.txtBandarSimati.focus(); 
			return; 
		
	}
	
	else if (document.f1.txtNoKPBaru1Simati.value =="" && document.f1.txtNoKPBaru2Simati.value=="" && document.f1.txtNoKPBaru3Simati.value=="" && document.f1.txtNoKPLamaSimati.value=="" && document.f1.socJenisKPLainSimati.value=="0" && document.f1.txtNoKPLainSimati.value=="") {
		alert("Sila masukkan salah satu MyID Simati");
	}
	
	else if(document.f1.txtNoKPLamaSimati.value != "" && document.f1.txtNoKPLamaSimati.value != "TDK" && document.f1.txtNoKPLamaSimati.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Simati");
		document.f1.txtNoKPLamaSimati.focus();	
	}
	
	else if ((document.f1.socJenisKPLainSimati.value!="0"  && document.f1.socJenisKPLainSimati.value!="") && document.f1.txtNoKPLainSimati.value=="") {
		alert("Sila masukkan MyID Lain Simati");
		document.f1.txtNoKPLainSimati.focus();
	}
	
	else if (document.f1.txtNoKPLainSimati.value!="" && document.f1.socJenisKPLainSimati.value=="0") {
		alert("Sila pilih jenis MyID Lain Simati");
	}
	
	else if (document.f1.txtNoKPBaru1Simati.value !="" && document.f1.txtNoKPBaru1Simati.value.length<6) {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru1Simati.focus();
	}
	else if (document.f1.txtNoKPBaru2Simati.value !="" && document.f1.txtNoKPBaru2Simati.value.length<2) {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru2Simati.focus();
	}
	else if (document.f1.txtNoKPBaru3Simati.value !="" && document.f1.txtNoKPBaru3Simati.value.length<4) {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru3Simati.focus();
	}
	
	else if (document.f1.txtNoKPBaru1Simati.value !="" && document.f1.txtNoKPBaru2Simati.value=="" && document.f1.txtNoKPBaru3Simati.value=="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru2Simati.focus();
	}
	else if (document.f1.txtNoKPBaru1Simati.value !="" && document.f1.txtNoKPBaru2Simati.value!="" && document.f1.txtNoKPBaru3Simati.value=="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru3Simati.focus();
	}
	else if (document.f1.txtNoKPBaru1Simati.value !="" && document.f1.txtNoKPBaru2Simati.value=="" && document.f1.txtNoKPBaru3Simati.value!="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru2Simati.focus();
	}
	else if (document.f1.txtNoKPBaru2Simati.value!="" && document.f1.txtNoKPBaru1Simati.value=="" && document.f1.txtNoKPBaru3Simati.value!="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru1Simati.focus();
	}
	else if (document.f1.txtNoKPBaru2Simati.value!="" && document.f1.txtNoKPBaru1Simati.value=="" &&  document.f1.txtNoKPBaru3Simati.value=="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru1Simati.focus();
	}	
	else if (document.f1.txtNoKPBaru1Simati.value=="" &&  document.f1.txtNoKPBaru2Simati.value=="" && document.f1.txtNoKPBaru3Simati.value!="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaru1Simati.focus();
	}
	
	/* else if (document.f1.txtNoKPBaru1Simati.value=="" && document.f1.txtNoKPBaru2Simati.value=="" && document.f1.txtNoKPBaru3Simati.value=="" && document.f1.txtNoKPLamaSimati.value=="" && document.f1.socJenisKPLainSimati.value=="" && document.f1.txtNoKPLainSimati.value=="") {
		alert("Sila masukkan salah satu nombor MyID simati");
		document.f1.txtNoKPBaru1Simati.focus();
		return; 
		}
	else if (((document.f1.socJenisKPLainSimati.value!="" && document.f1.socJenisKPLainSimati.value!="0") && document.f1.txtNoKPLainSimati.value==""))
	 {
	 	alert("Sila masukkan nombor MyID lain simati");
		document.f1.txtNoKPLainSimati.focus();
		return; 
	 }
	 else if (((document.f1.socJenisKPLainSimati.value=="" || document.f1.socJenisKPLainSimati.value=="0") && document.f1.txtNoKPLainSimati.value!=""))
	 {
	 	alert("Sila pilih jenis MyID lain simati");
		document.f1.socJenisKPLainSimati.focus();
		return; 
	 }
	 else if ((document.f1.txtNoKPBaru1Simati.value!="" || document.f1.txtNoKPBaru2Simati.value!="" || document.f1.txtNoKPBaru3Simati.value!="") && (document.f1.txtNoKPBaru1Simati.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID simati sepenuhnya");
		document.f1.txtNoKPBaru1Simati.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Simati.value!="" || document.f1.txtNoKPBaru2Simati.value!="" || document.f1.txtNoKPBaru3Simati.value!="") && (document.f1.txtNoKPBaru2Simati.value==""))
	 {
	 	
		alert("Sila masukkan nombor MyID simati sepenuhnya");
		document.f1.txtNoKPBaru2Simati.focus();
		return; 
	}
	else if ((document.f1.txtNoKPBaru1Simati.value!="" || document.f1.txtNoKPBaru2Simati.value!="" || document.f1.txtNoKPBaru3Simati.value!="") && (document.f1.txtNoKPBaru3Simati.value==""))
	 {
	 	alert("Sila masukkan nombor MyID simati sepenuhnya");
		document.f1.txtNoKPBaru3Simati.focus();
		return; 
	}	
	else if (document.f1.txtNoKPBaru1Simati.value!=""  && document.f1.txtNoKPBaru1Simati.value.length < 6 ) {
		alert("Sila masukkan nombor MyID simati sepenuhnya");
		document.f1.txtNoKPBaru1Simati.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru2Simati.value!="" && document.f1.txtNoKPBaru2Simati.value.length < 2 ) {
		alert("Sila masukkan nombor MyID simati sepenuhnya");
		document.f1.txtNoKPBaru2Simati.focus();
		return; 
	}
	else if (document.f1.txtNoKPBaru3Simati.value!="" && document.f1.txtNoKPBaru3Simati.value.length < 4) {
		alert("Sila masukkan nombor MyID simati sepenuhnya");
		document.f1.txtNoKPBaru3Simati.focus();
		return; 
	}
	///
	else if(document.f1.txtNoKPLamaSimati.value != "" && document.f1.txtNoKPLamaSimati.value != "TDK" && document.f1.txtNoKPLamaSimati.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Simati");
		document.f1.txtNoKPLamaSimati.focus();	
	} */
	
	else if (document.f1.txtPoskodSimati.value != "" && document.f1.txtPoskodSimati.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskodSimati.focus();
		return; 
	}
	
	else if (document.f1.socBuktiKematianSimati.value == "" || document.f1.socBuktiKematianSimati.value == "") {
		alert("Sila masukkan bukti kematian");
		document.f1.socBuktiKematianSimati.focus();
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

	else if (document.f1.socWaktuKematianSimati.value != "" && (document.f1.socWaktuKematianSimati.value.charAt(0) > 1) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}	
	else if (document.f1.socWaktuKematianSimati.value != "" && (document.f1.socWaktuKematianSimati.value.charAt(0) == 1) && (document.f1.socWaktuKematianSimati.value.charAt(1) >2 ) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}
	/*	
	else if (document.f1.socWaktuKematianSimati.value != "" && (document.f1.socWaktuKematianSimati.value.charAt(0) == 2) && (document.f1.socWaktuKematianSimati.value.charAt(1) > 3) ) {
		alert("Sila masukkan waktu kematian dengan format yang betul");
		document.f1.socWaktuKematianSimati.focus();
		return; 
	}*/
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
   else if( (date1 > date2) && (document.f1.socBuktiKematianSimati.value != "2") )
   {
   
    
      alert("Sila pastikan tarikh mati tidak melebihi dari tarikh mohon.");	 
      document.f1.txdTarikhMatiSimati.focus();
	  return;
	  
	  
	  
   } 
   else if (document.f1.txtNoKPBaru1Simati.value != "" && isIc(tt)==false){
  	
	document.f1.txtNoKPBaru1Simati.focus();
		return false
	}
	/*
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
	*/
	else{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
		document.f1.mode.value = "simpan_simati";
		document.f1.command.value = "Simati";
		//document.f1.method="post";
		
if('$!skrin_online' == 'yes')
{

document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
//document.f1.action = ""

}
else
{
document.f1.action = "";
}
	
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
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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

document.f1.txdTarikhSuratAkuan.disabled = "disabled";
document.f1.txdTarikhSuratAkuan.value = "";
}

else if( val=="4" )
{

document.f1.txtNoSijilMatiSimati.disabled = "";
document.f1.txtNoSijilMatiSimati.value = "";
document.f1.socBuktiKematianSimati.value="4";

document.f1.txdTarikhSuratAkuan.disabled = "disabled";
document.f1.txdTarikhSuratAkuan.value = "";

}

else if( val=="3" )
{

document.f1.txtNoSijilMatiSimati.disabled = "";
document.f1.txtNoSijilMatiSimati.value = "";
document.f1.socBuktiKematianSimati.value="3";

document.f1.txdTarikhSuratAkuan.disabled = "disabled";
document.f1.txdTarikhSuratAkuan.value = "";

}
else if( val=="2" )
{	
	document.f1.txtNoSijilMatiSimati.disabled = "disabled";
	document.f1.txtNoSijilMatiSimati.value = "";
	
	document.f1.txdTarikhSuratAkuan.disabled = "";
	document.f1.txdTarikhSuratAkuan.value = "";
	document.f1.socBuktiKematianSimati.value="2";
}
else
{
document.f1.txtNoSijilMatiSimati.disabled = "disabled";
document.f1.txtNoSijilMatiSimati.value = "";
document.f1.socBuktiKematianSimati.value = val;

document.f1.txdTarikhSuratAkuan.disabled = "disabled";
document.f1.txdTarikhSuratAkuan.value = "";
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
 
//	goTo('$val_tab');
//	Effect.ScrollTo('$val_tab').focus(); return false;

window.location.hash='$val_tab';
	


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
window.location.hash='tab_Simati';
//goTo('tab_Simati');
var nextFieldID = 'tab_Simati';
   document.getElementById(nextFieldID).focus();
}
	
} 




function qryHowOld(){
   
   	var dob_code = document.f1.txtNoKPBaru1Simati.value;
   
	if(dob_code.charAt(0)<2){
		 var dum = "20";
	}
	else{
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
	//alert("year1 ===" + year1 + "year2 === " + year2)	 
	
	if(dob_code == ""){
		document.f1.txtUmurSimati.value ="";
	}else{
		if((year1 - year2)>0){
			document.f1.txtUmurSimati.value = year1 - year2 ;
		}else{
			document.f1.txtUmurSimati.value = 0 ;
		}
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

function trans_date(t_d){

	if(t_d.length == 8){
		var a = t_d.charAt(0);
		var b = t_d.charAt(1);
		var c = t_d.charAt(2);
		var d = t_d.charAt(3);
		var e = t_d.charAt(4);
		var f = t_d.charAt(5);
		var g = t_d.charAt(6);
		var h = t_d.charAt(7);
		
		var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
		alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)
		
		document.f1.txdTarikhMatiSimati.value = trans;
	
	}else{
		alert("trans_date return");
		return;
	}

}

function deleteSuppDoc()
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.method = "POST";
	document.f1.mode.value="deleteSuppDocMode";
	document.f1.command.value = "Simati";
	document.f1.eventStatus.value="1";
	document.f1.action = "";
	document.f1.submit();
	}
	else
		{
		return
		}
	
	
}

function uploadSuppDoc(id,IdSimati)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppk.SkrinPopupUploadDokumen?&id_Permohonan="+id+"&IdSimati="+IdSimati+"&id_jenisDoc=99201";
	var hWnd = window.open(url,'printuser','width=350,height=200, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
	
	
}

function doOpen(id) {
	//alert('id : '+id);
    var url = "../servlet/ekptg.view.ppk.DisplayBuktiKematian?id="+id+"&jenisDoc=99201";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function trans_dateAkuan(t_d){
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
		document.f1.txdTarikhAkuan.value = trans;
	
	}else{
		return;
	}

}

function kembalix() {
	document.f1.method = "POST";
	document.f1.command.value="papar";
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
}
else
{
document.f1.action = "";
}
	document.f1.submit();
}
function kembalidaftar()
{
        document.f1.command.value="kembali_daftar_pemohon";
		document.f1.eventStatus.value="1";
		if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
}
else
{
document.f1.action = "";
}
		document.f1.submit();
}

function kembaliSenaraiFail(noFail) {

if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
}
else
{
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
}

	
	document.f1.submit();
}
function kembaliSenaraiPermohonan(noFail) {

if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
}
else
{
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
}
	
	document.f1.method="POST";
	document.f1.submit();
}
function Kembali(){
	document.f1.method="POST";
	document.f1.command.value="xxx";
	
	
	if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
}
else
{
document.f1.action = "";}
	document.f1.submit();
}

function getBandarTetap(v_t)
{
 if('$!skrin_online' == 'yes')
{
document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
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

function checkkp()
{
	url = "../servlet/ekptg.view.online.Portal";
	actionName = "doCheckUsername";
	target = "checkkp_result";
	doAjaxUpdater(document.f1, url, target, actionName);
}

function jeniswaktu()
{
alert("shdvduhys")



}

function jeniswaktu1()
{

document.f1.jeniswaktu.value = "";
var vm = document.f1.socWaktuKematianSimati.value;


if(vm != "" && vm.length == 4)
{
var vm_m = 0;

if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

//alert("jam :"+vm_m)


if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}


}
else
{
//alert("2")

document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}




}



function jeniswaktu2()
{

//document.f1.jeniswaktu.value = "";
var vm = document.f1.socWaktuKematianSimati.value;


if(vm != "" && vm.length == 4)
{
var vm_m = 0;

if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

//alert("jam :"+vm_m)


if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}



}
else
{
//alert("2")

document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}




}


</script>

<!-- ADD BY PEJE -->
<script>
function calculateTarikhLahirSimati(){

	if (document.f1.txtNoKPBaru1Simati.value != ""){
		var currentTime = new Date();
		
		var noKPSimati = document.f1.txtNoKPBaru1Simati.value;		
		if(noKPSimati.length == 6){
			var a = noKPSimati.charAt(0);
			var b = noKPSimati.charAt(1);
			var c = noKPSimati.charAt(2);
			var d = noKPSimati.charAt(3);
			var e = noKPSimati.charAt(4);
			var f = noKPSimati.charAt(5);
			
			var currentYear = currentTime.getFullYear();
			var birthYear = currentYear + "";
			birthYear = birthYear.charAt(0) + "" + birthYear.charAt(1);
			var fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			
			if (fullBirthYear > currentYear ){
				birthYear = birthYear*1 - 1;
				birthYear = birthYear + "";
				fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			}
			document.f1.tarikhLahirSimati.value = e + f + "/" + c + d + "/" + fullBirthYear;
			calculateUmurSimati();
		}
	}	
}
function calculateUmurSimati(){
	if (document.f1.txdTarikhMatiSimati.value != "" && document.f1.tarikhLahirSimati.value != ""){
		
		var str1  = document.getElementById("tarikhLahirSimati").value;
		var str2  = document.getElementById("txdTarikhMatiSimati").value;

		var yr1   = parseInt(str1.substring(6,10),10);
		var yr2   = parseInt(str2.substring(6,10),10);
		
		var age = (yr2*1) - (yr1*1);
		if (age < 0 )
			age = 0;
		document.f1.txtUmurSimati.value = age ;
	}
}
</script>
<!-- END ADD BY PEJE -->


</body>
</html>
