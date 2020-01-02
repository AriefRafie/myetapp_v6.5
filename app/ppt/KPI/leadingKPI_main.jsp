 <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
             <!-- shaz -->
            
             <!-- elly -->
             <input type="hidden" name="id_fail" />
             
             <!-- razman -->
			 <input type="hidden" name="id_permohonan" />			
             <input type="hidden" name="flagAdvSearch" value="$flagAdvSearch"/>
             <input type="hidden" name="flag_MyInfoPPT"/>
<table width="100%"  >

<tr>
    <td colspan="8">
    </td>
  </tr>
  </table>
  <fieldset id="pilihan">
  <legend>PILIHAN</legend>
  <table width="100%">
  <tr>
    <td width="10%">&nbsp;</td>
    <td width="25%">Jenis Urusan</td>
    <td width="1%">:</td>
    <td width="64%">
    <select name="jenisUrusan" id="jenisUrusan" class="autoselect" >     
    #if($!jenisUrusan != "")     
    #if($!jenisUrusan == "1")
    <option value="1">PERMOHONAN SEKSYEN 8</option>     
 <!--   <option value="2">PERMOHONAN SEKSYEN 4</option> -->  
 <!--   <option value="3">PERMOHONAN PENDUDUKAN DAN PENGAMBILAN SEMENTARA</option> -->
    <option value="4">BANTAHAN KE MAHKAMAH</option>     
    <option value="5">PENARIKAN BALIK PENGAMBILAN TANAH</option>
    <option value="">SILA PILIH SUBURUSAN </option>      
    #elseif($!jenisUrusan == "2")
  <!--  <option value="2">PERMOHONAN SEKSYEN 4</option> -->
    <option value="1">PERMOHONAN SEKSYEN 8</option>   
 <!--   <option value="3">PERMOHONAN PENDUDUKAN DAN PENGAMBILAN SEMENTARA</option> -->
    <option value="4">BANTAHAN KE MAHKAMAH</option>     
    <option value="5">PENARIKAN BALIK PENGAMBILAN TANAH</option>
    <option value="">SILA PILIH SUBURUSAN </option>
    #elseif($!jenisUrusan == "3")
 <!--   <option value="3">PERMOHONAN PENDUDUKAN DAN PENGAMBILAN SEMENTARA</option> -->
    <option value="1">PERMOHONAN SEKSYEN 8</option> 
 <!--   <option value="2">PERMOHONAN SEKSYEN 4</option>       -->
    <option value="4">BANTAHAN KE MAHKAMAH</option>     
    <option value="5">PENARIKAN BALIK PENGAMBILAN TANAH</option>
    <option value="">SILA PILIH SUBURUSAN </option>
    #elseif($!jenisUrusan == "4")
    <option value="4">BANTAHAN KE MAHKAMAH</option>
    <option value="1">PERMOHONAN SEKSYEN 8</option>     
 <!--   <option value="2">PERMOHONAN SEKSYEN 4</option>   -->
 <!--   <option value="3">PERMOHONAN PENDUDUKAN DAN PENGAMBILAN SEMENTARA</option>    -->      
    <option value="5">PENARIKAN BALIK PENGAMBILAN TANAH</option>
    <option value="">SILA PILIH SUBURUSAN </option>
    #elseif($!jenisUrusan == "5")
    <option value="5">PENARIKAN BALIK PENGAMBILAN TANAH</option>
    <option value="4">BANTAHAN KE MAHKAMAH</option>
    <option value="1">PERMOHONAN SEKSYEN 8</option>     
 <!--   <option value="2">PERMOHONAN SEKSYEN 4</option>   -->
 <!--   <option value="3">PERMOHONAN PENDUDUKAN DAN PENGAMBILAN SEMENTARA</option>       -->   
    <option value="">SILA PILIH SUBURUSAN </option> 
    #else
    <option value="">SILA PILIH SUBURUSAN </option> 
    <option value="1">PERMOHONAN SEKSYEN 8</option>     
   <!-- <option value="2">PERMOHONAN SEKSYEN 4</option>   -->
<!--    <option value="3">PERMOHONAN PENDUDUKAN DAN PENGAMBILAN SEMENTARA</option> -->
    <option value="4">BANTAHAN KE MAHKAMAH</option>     
    <option value="5">PENARIKAN BALIK PENGAMBILAN TANAH</option>    
    #end
    #else
    <option value="">SILA PILIH SUBURUSAN </option> 
    <option value="1">PERMOHONAN SEKSYEN 8</option>     
 <!--   <option value="2">PERMOHONAN SEKSYEN 4</option>  --> 
 <!--   <option value="3">PERMOHONAN PENDUDUKAN DAN PENGAMBILAN SEMENTARA</option> -->
    <option value="4">BANTAHAN KE MAHKAMAH</option>     
    <option value="5">PENARIKAN BALIK PENGAMBILAN TANAH</option>    
    #end
      
   
      </select>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Nama Pejabat JKPTG</td>
    <td>:</td>
   <td >
    
      <label>
      
      <select name="socPejabat" id="socPejabat" class="autoselect"  onchange="checking_validation(this,'socPejabat_check','yes','pejabat JKPTG','drop');showDaerah()" onblur="checking_validation(this,'socPejabat_check','yes','pejabat JKPTG','drop')">
      
      #if($!socPejabat == "")
      <option value="">SILA PILIH PEJABAT</option>
      #else
      #foreach($list_pejabat in $list_pejabat)
      #if($!socPejabat == $list_pejabat.ID_PEJABATJKPTG)    
      <option value="$list_pejabat.ID_PEJABATJKPTG">$list_pejabat.NAMA_PEJABAT - $list_pejabat.JENIS_PEJABAT</option>
      #end
      #end
      #end
        
     #foreach($list_pejabat in $list_pejabat)
     #if($!socPejabat != $list_pejabat.ID_PEJABATJKPTG)     
     <option value="$list_pejabat.ID_PEJABATJKPTG">$list_pejabat.NAMA_PEJABAT - $list_pejabat.JENIS_PEJABAT</option>
     #end     
     #end
       
       #if($!socPejabat!="")
        <option value="">SILA PILIH PEJABAT</option>
        #end
      </select>
      <span id="socPejabat_check" class="alert_msg" ></span>      </label>       </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah</td>
    <td>:</td>
      <td>
      <select name="socDaerah" id="socDaerah"  onchange="checking_validation(this,'socDaerah_check','no','daerah','drop');" >
  
  
    
    
    
      #if($!socDaerah == "")
      
      <option value="000">KESELURUHAN NEGERI</option>
      
      #elseif($!socDaerah == "000")
      
      <option value="000">KESELURUHAN NEGERI</option>
      
      #else
      
      #foreach($lb in $list_daerahpejabat) 
      #if($!socDaerah == $lb.ID_DAERAH)    
      <option value="$lb.ID_DAERAH">$lb.KOD_DAERAH - $lb.NAMA_DAERAH</option>
      #end
      #end
      
      #end
        
      #foreach($lb in $list_daerahpejabat) 
      #if($!socDaerah != $lb.ID_DAERAH)     
      <option value="$lb.ID_DAERAH">$lb.KOD_DAERAH - $lb.NAMA_DAERAH</option>
      #end     
      #end
       
      #if($!socDaerah!="")
      <option value="000">KESELURUHAN NEGERI</option>
      #end
    
    
    
    
    </select>
    <span id="socDaerah_check" class="alert_msg" ></span>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Mula (Permohonan)
    <span align="center" class="font2"><font style="cursor:help" title="info"  onMouseOut="close_window()"   onMouseOver="open_new_window('1');this.style.cursor='help'" >
       #parse("app/ppt/infoblink_biru.jsp")
       </font>                                 </span>  
    
    </td>
    <td>:</td>
   <td >
    
     <input name="txdTarikhMula" type="text" id="txdTarikhMula" size="10" maxlength="10" value="$!txdTarikhMula"    onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhMula_check','yes','mula','tarikh');" onKeyUp="validateTarikh(this,this.value);"  />  
          
     <a href="javascript:displayDatePicker('txdTarikhMula',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>    
         <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     
      
       <span id="txdTarikhMula_check" class="alert_msg"></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Akhir (Permohonan)</td>
    <td>:</td>
    <td> 
     <input name="txdTarikhAkhir" type="text" id="txdTarikhAkhir" size="10" maxlength="10"  value="$!txdTarikhAkhir"   onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhAkhir_check','yes','akhir','tarikh');" onKeyUp="validateTarikh(this,this.value);"  />  
          
     <a href="javascript:displayDatePicker('txdTarikhAkhir',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>    
         <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     
      
       <span id="txdTarikhAkhir_check" class="alert_msg"></span> </td>
  </tr>
 
  <tr>
    <td colspan="4">  <div align="center">
      <input type="button" name="cmdCetak" id="cmdCetak" value="Papar" onClick="showUrusan()">
      <input type="reset" name="cmdIsiSemula" id="cmdIsiSemula" value="Kosongkan" onClick="kosongkan()"> 
      </div></td>
 
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
</table>

  </fieldset>
  
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  
  #if($!open_KPI == "yes")
  #if($!jenisUrusan == "1")
  #parse("app/ppt/KPI/KPISek8.jsp")
  #elseif($!jenisUrusan == "2")
  #parse("app/ppt/KPI/KPISek4.jsp")
  #elseif($!jenisUrusan == "3")
  #parse("app/ppt/KPI/KPISementara.jsp")
  #elseif($!jenisUrusan == "4")
  #parse("app/ppt/KPI/KPIBantahan.jsp")
  #elseif($!jenisUrusan == "5")
  #parse("app/ppt/KPI/KPIPenarikanBalik.jsp")
  #end
  
  #end
  #if($!open_KPI == "yes")
  <table width="100%">
  <tr>
  <td>
    <div align="center">
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan KPI" onclick="javascript:setTable('tableReport1')" />  
      </div></td>  
  </tr>  
  </table>
  #end
  
   <fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    
      
   #if($!jenisUrusan == "1")
  <tr>      
        <td><a href="#" class="style2" onClick="cetakSek8('$nama_pejabat','$txdTarikhMula','$txdTarikhAkhir','$SX1','$SST1','$SX2','$SST2','$M1','$M2','$M3','$SX3','$SST3','$SX4','$SST4','$N1','$N2','$N3','$SX5','$SST5','$SX6','$SST6','$O1','$O2','$O3','$SX7','$SST7','$SX8','$SST8','$P1','$P2','$P3','$SX9','$SST9','$SX10','$SST10','$Q1','$Q2','$Q3','$SX11','$SST11','$SX12','$SST12','$R1','$R2','$R3')"><font color="blue">Laporan KPI Seksyen 8</font></a></td>
      </tr>   
  #elseif($!jenisUrusan == "4")
  <tr>
        <td><a href="#" class="style2" onClick="cetakBantahan('$nama_pejabat','$txdTarikhMula','$txdTarikhAkhir','$SX1','$SST1','$SX2','$SST2','$M1','$M2','$M3')"><font color="blue">Laporan KPI Bantahan Ke Mahkamah</font></a></td>
      </tr>
  #elseif($!jenisUrusan == "5")
 <tr>
        <td><a href="#" class="style2" onClick="cetakPenarikan('$nama_pejabat','$txdTarikhMula','$txdTarikhAkhir','$SX1','$SST1','$SX2','$SST2','$M1','$M2','$M3','$SX3','$SST3','$SX4','$SST4','$N1','$N2','$N3')"><font color="blue">Laporan KPI Penarikan Balik</font></a></td>
      </tr>
  #end
     
         
      
         
       
           
       
    </table>
</fieldset>
  
<script type="text/javascript">
  window.onload = submitForm;
  

function submitForm() 
{

if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');
}
else
{
window.location.hash='paging';
goTo('paging');
}
 } 
  
  
  function checking_validation(field,point,mandatory,value_field,jenis_field){	



   	var lepas_or_xlepas = 1;
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   	
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
	/*	  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   */
	   
	   
	   if(tarikh_valid == "valid")
	   {
       /*	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {
	  
	   /*
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;

	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName); */
	//   DateField.focus();
    
     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");	
	 
	   
	   }
	   
	   }
	   
	// 
	   if(jenis_field == "normal")
	   {
	   
	  
		
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
	 		
	   }
	   
	   	   
	   }
	   
       
       
       
       
       if(jenis_field == "normal_kp")
	   {
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   if(lepas_or_xlepas == "2")
	   {      
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	 	
	   }
	   else
	   {
        
       if(document.${formName}.socJenisNOPB.value == "1" )
       {
       
        field.value = field.value.substring(0,12);	       
        if (isNaN(field.value)) {
            field.value = RemoveNonNumeric2(field.value);
            
        }  
             
       if(field.value.length != 12 )
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila lengkapkan kad pengenalan baru pb");         
       }
       else
       {
       
       
    if(field.value.charAt(0)<2)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = field.value.charAt(4)+""+field.value.charAt(5)+"/"+field.value.charAt(2)+""+field.value.charAt(3)+"/"+dum+field.value.charAt(0)+""+field.value.charAt(1);
       
       
    var checkstr = "0123456789";
	var DateField = tt;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
        err = 0;
	
	
	   DateValue = tt;	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField = day + seperator + month + seperator + year;
		   
	   }
	  
	   else {	   	
		  lepas_or_xlepas = "3";	   
	   }
	  



	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = tt;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
       
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {	  
	  
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh lahir "+value_field+" tidak melebihi dari tarikh hari ini");
	   }
	   
	   
	   }   
	   else if(lepas_or_xlepas == "3")
	   {    
     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh lahir "+value_field+" dengan format betul");	
	 	   
	   }
	   else
	{
      $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
    }
     	
       }
       
       }
       else
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
       }      
        		
	   }
	   
	   	   
	   }
	   
       
       if(jenis_field == "normal_j")
	   {
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   if(lepas_or_xlepas == "2")
	   {      
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	 	
	   }
	   else
	   {
        
       if(field.value == "1" )
       {
       
        document.${formName}.txtNoPB.value = document.${formName}.txtNoPB.value.substring(0,12);	       
        if (isNaN(document.${formName}.txtNoPB.value)) {
            document.${formName}.txtNoPB.value = RemoveNonNumeric2(document.${formName}.txtNoPB.value);
          
        }  
         
         
       if(document.${formName}.txtNoPB.value.length != 12 )
       {
       $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila lengkapkan kad pengenalan baru pb");         
       }
       else
       {
       
   		
  if(document.${formName}.txtNoPB.value.charAt(0)<2)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = document.${formName}.txtNoPB.value.charAt(4)+""+document.${formName}.txtNoPB.value.charAt(5)+"/"+document.${formName}.txtNoPB.value.charAt(2)+""+document.${formName}.txtNoPB.value.charAt(3)+"/"+dum+document.${formName}.txtNoPB.value.charAt(0)+""+document.${formName}.txtNoPB.value.charAt(1);
       
       
    var checkstr = "0123456789";
	var DateField = tt;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
        err = 0;
	
	
	   DateValue = tt;	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField = day + seperator + month + seperator + year;
		   
	   }
	  
	   else {	   	
		  lepas_or_xlepas = "3";	   
	   }
	  
       
		
        
        
	   if(lepas_or_xlepas == "1")
	   {
	      
          
           var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = tt;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
       
        $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {	  
	  
      $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh lahir "+value_field+" tidak melebihi dari tarikh hari ini");
	   }          
	   } 
        else if(lepas_or_xlepas == "3")
	   {    
     $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh lahir "+value_field+" dengan format betul");	
	 	   
	   }
	   else
	{
      $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
    }
     	
        


     
   
       }
       }
       else
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
       }      
        		
	   }
	   
	   	   
	   }
	   
	   
	   	   if(jenis_field == "radio")
	   {
	  
	  
	    var r = 0;
		if(field.length > 1)
		{     
			  for (i = 0; i < field.length; i++)
			  {
			  if (field[i].checked == true)
			  {	 
			  r++
			  }
			  }  
		}
		else
		{
		if (field.checked == true)
		{	 
		r++;
		}	 	
		}  
	   
	     
	   if((r == 0) && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	 
	   }
	   
	   	   
	   }
	   
	   
	   
	    if(jenis_field == "file") 
	    {
	   	var allBlank = true;
		if(document.${formName}.fileupload!=null)
		{
		
		for( var i=0,e=document.${formName}.fileupload;i<e.length; i++ )
		{			
		if( e[i].type=='file' )
		{	
		if( e[i].value.length  )
		{
		
		allBlank=false;	
		}	
		}	
		}
		
		}
		else
		{
		allBlank=false;	
		}
		
		
		if(allBlank == true)
	    {
        /*
		   document.${formName}.alert_message.value  = "Sila masukkan dokumen terlebih dahulu";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);*/
           $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");	
	 	
	    }
		else
		{
		  /* document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		
		*/
         $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	 	
		}
		
		
	   
	   }
	   
	   
	   
	   if(jenis_field == "waktu")
	   {
	   
		
	if(field.value == "" && mandatory == "yes")
	{
	lepas_or_xlepas = 2;
    }  
	else if(field.value != "" && (mandatory == "yes" || mandatory == "no"))
	{	   
	if (field.value.length < 4) {
	lepas_or_xlepas = 3;
	}
	else if (field.value.charAt(0) > 1)  {
	lepas_or_xlepas = 3;
	}	
	else if ((field.value.charAt(0) == 1) && (field.value.charAt(1) >2 )) {
	lepas_or_xlepas = 3;
	}
	else if ((field.value.charAt(2) > 5) ) {
	lepas_or_xlepas = 3;
	}
	else if ((field.value.charAt(3) > 9) ) {
	lepas_or_xlepas = 3;
	}
	}  
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");	
	 		
	   }
	   else if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan waktu "+value_field+" dengan format yang betul");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
         $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   
	   	    
	   }
	   
	   
	   
	   
	   if(jenis_field == "poskod")
	   {
	   
		
	if(field.value == "" && mandatory == "yes")
	{
	lepas_or_xlepas = 2;
    }  
	else if(field.value != "" && (mandatory == "yes" || mandatory == "no"))
	{	   
	if (field.value.length < 5) {
	lepas_or_xlepas = 3;
	}	
	}  
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");		
	   }
	   else if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan poskod "+value_field+" dengan format yang betul");		
		
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   
	   	    
	   }
	   
	 
	   
	
}


function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function showDaerah()
{

 document.${formName}.point.value = "socDaerah";
 document.${formName}.location.value = "pilihan";
 document.${formName}.command.value = "daerah";
 document.${formName}.submit();

}

function showUrusan()
{

var c = 0;

var date_validation_mula = "";

if(document.${formName}.txdTarikhMula.value != null)
{   

 var str1 = document.getElementById("txdTarikhMula").value;
     var str2 = "01/08/2010";
     var dt1  = parseInt(str1.substring(0,2),10);
     var mon1 = parseInt(str1.substring(3,5),10)-1;
     var yr1  = parseInt(str1.substring(6,10),10);
     var dt2  = parseInt(str2.substring(0,2),10);
     var mon2 = parseInt(str2.substring(3,5),10)-1;
     var yr2  = parseInt(str2.substring(6,10),10);
     var date1 = new Date(yr1, mon1, dt1);
     var date2 = new Date(yr2, mon2, dt2);
     if(date1 < date2)
     {
	 date_validation_mula = "on";
      /*   alert("Leading KPI hanya mengandungi maklumat permohonan baru yang bermula dari 01/08/2010");
		 document.${formName}.txdTarikhMula.focus();
         return false;*/
     }
	 
}


var date_validation_akhir = "";

if(document.${formName}.txdTarikhAkhir.value != null)
{   

 var str1 = document.getElementById("txdTarikhAkhir").value;
     var str2 = "01/08/2010";
     var dt1  = parseInt(str1.substring(0,2),10);
     var mon1 = parseInt(str1.substring(3,5),10)-1;
     var yr1  = parseInt(str1.substring(6,10),10);
     var dt2  = parseInt(str2.substring(0,2),10);
     var mon2 = parseInt(str2.substring(3,5),10)-1;
     var yr2  = parseInt(str2.substring(6,10),10);
     var date1 = new Date(yr1, mon1, dt1);
     var date2 = new Date(yr2, mon2, dt2);
     if(date1 < date2)
     {
	 date_validation_akhir = "on";
      /*   alert("Leading KPI hanya mengandungi maklumat permohonan baru yang bermula dari 01/08/2010");
		 document.${formName}.txdTarikhMula.focus();
         return false;*/
     }
	 
}


var date_validation_compare = "";


if(document.${formName}.txdTarikhAkhir.value != null && document.${formName}.txdTarikhMula.value != null)
{   

 var str1 = document.getElementById("txdTarikhAkhir").value;
 var str2 = document.getElementById("txdTarikhMula").value;
     var dt1  = parseInt(str1.substring(0,2),10);
     var mon1 = parseInt(str1.substring(3,5),10)-1;
     var yr1  = parseInt(str1.substring(6,10),10);
     var dt2  = parseInt(str2.substring(0,2),10);
     var mon2 = parseInt(str2.substring(3,5),10)-1;
     var yr2  = parseInt(str2.substring(6,10),10);
     var date1 = new Date(yr1, mon1, dt1);
     var date2 = new Date(yr2, mon2, dt2);
     if(date1 < date2)
     {
	 date_validation_compare = "on";
      /*   alert("Leading KPI hanya mengandungi maklumat permohonan baru yang bermula dari 01/08/2010");
		 document.${formName}.txdTarikhMula.focus();
         return false;*/
     }
	 
}



if(document.${formName}.validation_field != null  )
{

   if (document.${formName}.validation_field.length == null)
   {	
    
   if(document.${formName}.validation_field.value == "invalid")
   {  
   
   c++;	
   } 
   	
   } 
   
   else 
   {
   
        for (i = 0; i < document.${formName}.validation_field.length; i++)
		{		
			if(document.${formName}.validation_field[i].value == "invalid")
			{
			
               c++;	 
			}  	
        }
    }	
}

if(document.${formName}.jenisUrusan.value == "")
{
alert("Sila pilih jenis urusan Pengambilan Tanah");
document.${formName}.jenisUrusan.focus();
return;
}
else if(document.${formName}.socPejabat.value == "")
{
alert("Sila pilih jenis Pejabat JKPTG");
document.${formName}.socPejabat.focus();
return;
}
else if(document.${formName}.socDaerah.value == "")
{
alert("Sila pilih daerah");
document.${formName}.socDaerah.focus();
return;
}
else if(document.${formName}.txdTarikhMula.value == "")
{
alert("Sila masukkan tarikh mula");
document.${formName}.txdTarikhMula.focus();
return;
}
else if(document.${formName}.txdTarikhAkhir.value == "")
{
alert("Sila masukkan tarikh akhir");
document.${formName}.txdTarikhAkhir.focus();
return;
}

else if(date_validation_mula == "on")
{
 
         alert("Leading KPI hanya mengandungi maklumat permohonan baru yang bermula dari 01/08/2010");
		 document.${formName}.txdTarikhMula.focus();
         return false;
		 
}
else if(date_validation_akhir == "on")
{
 
         alert("Leading KPI hanya mengandungi maklumat permohonan baru yang bermula dari 01/08/2010");
		 document.${formName}.txdTarikhAkhir.focus();
         return false;
		 
}

else if(date_validation_compare== "on")
{
 
         alert("Pastikan tarikh mula tidak melebihi tarikh akhir");
		 document.${formName}.txdTarikhAkhir.focus();
         return false;
		 
}

else if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}
else
{
 document.${formName}.point.value = "table_kpi";
 document.${formName}.location.value = "table_kpi";
 document.${formName}.command.value = "urusan";
 document.${formName}.submit();
}


}

function kosongkan()
{

 document.${formName}.point.value = "jenisUrusan";
 document.${formName}.location.value = "pilihan";
 document.${formName}.command.value = "kosongkan";
 document.${formName}.submit();

}



function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function popup(tempat,range1,range2,kategori,tajuk)
{

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupKPIPPT?tempat="+tempat+"&jenisUrusan="+document.${formName}.jenisUrusan.value+"&socDaerah="+document.${formName}.socDaerah.value+"&socPejabat="+document.${formName}.socPejabat.value+"&txdTarikhMula="+document.${formName}.txdTarikhMula.value+"&txdTarikhAkhir="+document.${formName}.txdTarikhAkhir.value+"&range1="+range1+"&range2="+range2+"&kategori="+kategori+"&tajuk="+tajuk+"";
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}
 
function paparx()
{
alert("PAPARX");
}
 
 
function papar_screen(ID_PERMOHONAN,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA,USER_ROLE) {

//**START SEKSYEN 4 DAN 8
	
	//menu : pendaftaran
	//status : permohonan cawangan
	if (ID_STATUS == '11'){	
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=semak&paging=1";
		}else if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8&command=semakPendaftaran&ScreenLocation=top&paging=1";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan&command=semakPendaftaran&ScreenLocation=top&paging=1";				
		}else{
			alert("Sila Hubungi Admin");
		}	
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close permohonan cawangan

	
	//menu : agihan tugas(new form)
	//status : disahkan pengarah
	else if (ID_STATUS == '127'){	
		
		if (ID_SUBURUSAN == '51'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PenolongPengarahUnit" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4&command=tambahAgihan&ScreenLocation=top&paging=2";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah / Penolong Pengarah");
			}
		}else if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik&command=semakHM&ScreenLocation=top&paging=2";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik&command=semakHM&ScreenLocation=top&paging=2";			
		}else{
			alert("Sila Hubungi Admin");
		}	
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close agihan tugas(new form)

	
	//menu : Laporan awal tanah
	//status : Tindakan pegawai
	else if (ID_STATUS == '148'){
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai&command=tambahLaporan&paging=3";
		}else if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah&command=tambahLaporan&paging=4";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah&command=tambahLaporan&paging=4";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Laporan awal tanah

	
	//menu : Penyediaan Kertas MMK
	//status : Penyediaan laporan awal
	else if (ID_STATUS == '147' || ID_STATUS == '26'){
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai&command=viewSenaraiMMK&ScreenLocation=top&paging=4";
		}else if (ID_SUBURUSAN == '52'){
			if(ID_STATUS == '147'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&command=viewListHM&ScreenLocation=top&paging=5";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Warta&command=viewSenaraiWarta&ScreenLocation=top&paging=10";
			}
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraMMK&command=viewMMK&paging=8";						
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Penyediaan Kertas MMK

	
	//menu : Pewartaan / notis awam / Endorsan
	//status : Pewartaan
	else if (ID_STATUS == '31' || ID_STATUS == '52'){
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam&command=viewListNotis&ScreenLocation=top&paging=5";
		}else if (ID_SUBURUSAN == '52'){
			if(ID_STATUS == '31'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK&command=viewEndosan&ScreenLocation=top&paging=11";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyampaianNotis&command=viewListHM&ScreenLocation=top&paging=14";
			}
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraNotisAwam&command=viewListNotis&paging=9";						
		}else{
			alert("Sila Hubungi Admin");
		}	
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close pewartaan dan notis awam

	
	//menu : Hakmilik dan Pihak Berkepentingan
	//status : Maklumat hakmilik
	else if (ID_STATUS == '16'){
		
		if (ID_SUBURUSAN == '52'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PenolongPengarahUnit" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8&command=tambahAgihan&ScreenLocation=top&paging=3";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah / Penolong Pengarah");
			}	
		}else if (ID_SUBURUSAN == '53'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PenolongPengarahUnit" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas&command=tambahAgihan&ScreenLocation=top&paging=3";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah / Penolong Pengarah");
			}						
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close hakmilik dan pihak berkepentingan

	
	//menu : Penyediaan Kertas MMK Sek 8
	//status : JPBD / JPPH dan Maklumat Jabatan Teknikal dan status2 mmk
	else if (ID_STATUS == '43' || ID_STATUS == '22' || ID_STATUS == '132' || ID_STATUS == '133' || ID_STATUS == '134'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmMMKSek8&command=viewMMK&ScreenLocation=top&paging=9";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH&command=viewListHM&ScreenLocation=top&paging=11";		
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Penyediaan Kertas MMK Sek 8


	//menu : Borang E dan F
	//status : memorial/endosan dhdk
	else if (ID_STATUS == '35'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF&command=viewListHM&ScreenLocation=top&paging=12";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Borang E dan F

	
	//menu : Notis Awam
	//status : Borang F
	else if (ID_STATUS == '54'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam&command=viewListNotis&ScreenLocation=top&paging=13";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Notis Awam

	//menu : Penandaan Kawasan
	//status : Penyampaian Notis
	else if (ID_STATUS == '58'){
		
		if (ID_SUBURUSAN == '52'){
			if(FLAG_SEGERA == '1'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera&command=viewSegera&ScreenLocation=top&paging=22";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=15";
			}
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPenandaanKawasan&command=viewListHM&ScreenLocation=top&paging=12";				
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Penandaan Kawasan

	
	//menu : Siasatan dan Perintah
	//status : Tanda kawasan
	else if (ID_STATUS == '38'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Siasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=16";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=13";		
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Siasatan dan Perintah


	//menu : Pampasan
	//status : Bicara
	else if (ID_STATUS == '62' || ID_STATUS == '68'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&command=viewlistHM&ScreenLocation=top&paging=17";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraPampasanPB&command=viewlistHM&ScreenLocation=top&paging=14";		
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Pampasan


	//menu : Borang K
	//status : Bayaran Pampasan
	else if (ID_STATUS == '72' || ID_STATUS == '59'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8BorangK&command=viewListHM&ScreenLocation=top&paging=18";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraPampasanPB&command=viewListHM&ScreenLocation=top&paging=14";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Borang K


	//menu : Endorsan Borang K
	//status : Borang K
	else if (ID_STATUS == '76'){
		
		if (ID_SUBURUSAN == '52'){
			if(FLAG_SEGERA == '1'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=15";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK&command=viewEndosan&ScreenLocation=top&paging=20";
			}
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Endorsan Borang K


	//menu : Permintaan ukur
	//status : Permintaan ukur
	else if (ID_STATUS == '82'){		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur&command=viewListHM&ScreenLocation=top&paging=23";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Permintaan ukur
	
//**END SEKSYEN 4 DAN 8
	
	
// PENDUDUKAN/PENGGUNAAN SEMENTARA

	//menu : Rujukan Ke Mahkamah (Pendudukan/Penggunaan Sementara)
	//status : Rujukan Ke Mahkamah
	else if (ID_STATUS == '1610193'){		
		if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraBorangM&command=baruBorangM&ScreenLocation=top&paging=15";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//Rujukan Ke Mahkamah	
	
	
	//menu : Perundingan
	//status : Set Perundingan dan Perundingan
	else if (ID_STATUS == '1610192' || ID_STATUS == '1610194'){		
		if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=13";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//Rujukan Ke Mahkamah		
	
//** END PENDUDUKAN/PENGGUNAAN SEMENTARA	
	
	
//penarikan balik - razman

	else if  (ID_STATUS == '74'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=senarai&sub_command=papar";
			}
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
	}
	
//pembatalan - razman	
	else if (ID_STATUS == '235'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=senarai&sub_command=papar";
			}
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
	}
	
//bantahan - elly 

	// DALAM PROSES
	else if (ID_STATUS == '181'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// URUSAN DEPOSIT
	else if (ID_STATUS == '183'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}

	// URUSAN MAHKAMAH
	else if (ID_STATUS == '184'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// TARIK BALIK BANTAHAN
	else if (ID_STATUS == '185'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// URUSAN BAYARAN
	else if (ID_STATUS == '186'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// SELESAI
	else if (ID_STATUS == '187'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// PEMBATALAN OLEH MT
	else if (ID_STATUS == '220'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// BORANG O
	else if (ID_STATUS == '1610248'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// DALAM PROSES[AGENSI]
	else if (ID_STATUS == '199'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}		
	
	// URUSAN DEPOSIT[AGENSI]
	else if (ID_STATUS == '200'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}

	// URUSAN MAHKAMAH[AGENSI]
	else if (ID_STATUS == '201'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// BORANG O [AGENSI]
	else if (ID_STATUS == '1610249'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// TARIK BALIK[AGENSI]
	else if (ID_STATUS == '1610249'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		

	}else{
		alert("Sila Hubungi Admin");
		return;
	}	
	
	
	document.${formName}.flag_MyInfoPPT.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();
	
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	//	window.location.hash=id;
    //    goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


function cetakSek8(nama_pejabat,txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7,ENAMA1,ENAMA2,ENAMA3,ENAMA4,ENAMA5,ENAMA6,ENAMA7,TUJUHA1,TUJUHA2,TUJUHA3,TUJUHA4,TUJUHA5,TUJUHA6,TUJUHA7,LAPANA1,LAPANA2,LAPANA3,LAPANA4,LAPANA5,LAPANA6,LAPANA7,SEMBILANA1,SEMBILANA2,SEMBILANA3,SEMBILANA4,SEMBILANA5,SEMBILANA6,SEMBILANA7,SEPULOHA1,SEPULOHA2,SEPULOHA3,SEPULOHA4,SEPULOHA5,SEPULOHA6,SEPULOHA7)
{

    var url = "../servlet/ekptg.report.ppt.CetakKPISek8?PEJABAT="+nama_pejabat+"&TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&B3="+document.${formName}.B3.value+"&B4="+document.${formName}.B4.value+"&B5="+document.${formName}.B5.value+"&B6="+document.${formName}.B6.value+"&B7="+document.${formName}.B7.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&C3="+document.${formName}.C3.value+"&C4="+document.${formName}.C4.value+"&C5="+document.${formName}.C5.value+"&C6="+document.${formName}.C6.value+"&C7="+document.${formName}.C7.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&D3="+document.${formName}.D3.value+"&D4="+document.${formName}.D4.value+"&D5="+document.${formName}.D5.value+"&D6="+document.${formName}.D6.value+"&D7="+document.${formName}.D7.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&E3="+document.${formName}.E3T.value+"&E4="+document.${formName}.E4T.value+"&E5="+document.${formName}.E5T.value+"&E6="+document.${formName}.E6T.value+"&E7="+document.${formName}.E7T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&F3="+document.${formName}.F3T.value+"&F4="+document.${formName}.F4T.value+"&F5="+document.${formName}.F5T.value+"&F6="+document.${formName}.F6T.value+"&F7="+document.${formName}.F7T.value+"&A1="+document.${formName}.DITERIMA.value+"&A2="+document.${formName}.LOT_DITERIMA.value+"&A3="+document.${formName}.DISELESAI.value+"&A4="+document.${formName}.LOT_DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7+"&5B1="+ENAMA1+"&5B2="+ENAMA2+"&5B3="+ENAMA3+"&5B4="+ENAMA4+"&5B5="+ENAMA5+"&5B6="+ENAMA6+"&5B7="+ENAMA7+"&5C1="+TUJUHA1+"&5C2="+TUJUHA2+"&5C3="+TUJUHA3+"&5C4="+TUJUHA4+"&5C5="+TUJUHA5+"&5C6="+TUJUHA6+"&5C7="+TUJUHA7+"&5D1="+LAPANA1+"&5D2="+LAPANA2+"&5D3="+LAPANA3+"&5D4="+LAPANA4+"&5D5="+LAPANA5+"&5D6="+LAPANA6+"&5D7="+LAPANA7+"&5E1="+SEMBILANA1+"&5E2="+SEMBILANA2+"&5E3="+SEMBILANA3+"&5E4="+SEMBILANA4+"&5E5="+SEMBILANA5+"&5E6="+SEMBILANA6+"&5E7="+SEMBILANA7+"&5F1="+SEPULOHA1+"&5F2="+SEPULOHA2+"&5F3="+SEPULOHA3+"&5F4="+SEPULOHA4+"&5F5="+SEPULOHA5+"&5F6="+SEPULOHA6+"&5F7="+SEPULOHA7; 
	
	 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}



function cetakPenarikan(nama_pejabat,txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7,ENAMA1,ENAMA2,ENAMA3,ENAMA4,ENAMA5,ENAMA6,ENAMA7)
{

    var url = "../servlet/ekptg.report.ppt.CetakKPIPenarikan?PEJABAT="+nama_pejabat+"&TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&B3="+document.${formName}.B3.value+"&B4="+document.${formName}.B4.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&C3="+document.${formName}.C3.value+"&C4="+document.${formName}.C4.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&D3="+document.${formName}.D3.value+"&D4="+document.${formName}.D4.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&E3="+document.${formName}.E3T.value+"&E4="+document.${formName}.E4T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&F3="+document.${formName}.F3T.value+"&F4="+document.${formName}.F4T.value+"&A1="+document.${formName}.DITERIMA.value+"&A2="+document.${formName}.LOT_DITERIMA.value+"&A3="+document.${formName}.DISELESAI.value+"&A4="+document.${formName}.LOT_DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7+"&5B1="+ENAMA1+"&5B2="+ENAMA2+"&5B3="+ENAMA3+"&5B4="+ENAMA4+"&5B5="+ENAMA5+"&5B6="+ENAMA6+"&5B7="+ENAMA7; 
	
	 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function cetakBantahan(nama_pejabat,txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7)
{

    var url = "../servlet/ekptg.report.ppt.CetakKPIBantahan?PEJABAT="+nama_pejabat+"&TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&A1="+document.${formName}.DITERIMA.value+"&A2="+document.${formName}.DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7; 
	
	 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function open_new_window(jenis_popup) 
{
 var width  = 0;
 var height = 0;
if(jenis_popup == "1")
{
  width  = 300;
  height = 100;
}
if(jenis_popup == "3" || jenis_popup == "4")
{
  width  = 300;
  height = 200;
}
if(jenis_popup == "2")
{
 var width  = 300;
 var height = 300;
}

 var left   = '';
 var top    = '';
 var params = '';

if(jenis_popup == "1")
{
 left   = (screen.width  - width)/8;
 top    = (screen.height - height)/8;
 params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();
 new_window.document.write("<html><title>JavaScript New Window</title>");
 new_window.document.write("<body bgcolor=\"#FFFFFF\">");
}
else
{
 left   = (screen.width  - width)/2;
 top    = (screen.height - height)/2;
 params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();
 new_window.document.write("<html><title>JavaScript New Window</title>");
 new_window.document.write("<body bgcolor=\"#FFFFFF\">");
} 

if(jenis_popup == "1")
{
new_window.document.write("Leading KPI hanya mengandungi maklumat permohonan baru yang bermula dari 01/08/2010");
}

if(jenis_popup == "4")
{
new_window.document.write("Fungsi 'Pengiraan Jumlah Pampasan Tanah' bertujuan untuk membuat pengiraan pampasan tanah secara automatik merujuk kepada bahagian/syer pihak berkepentingan, harga seunit dan luas tanah yang dikehendaki.");
}


if(jenis_popup == "2")
{
new_window.document.write("<table width = '100%' >")
new_window.document.write("<tr>")
new_window.document.write("<td colspan = '3'>")
new_window.document.write("Tujuan medan 'Keterangan Jenis PB' adalah untuk memudahkan pengguna untuk menyatakan sebarang keterangan merujuk kepada jenis pihak berkepentingan yang telah dipilih. Contoh : ");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("</table>")
new_window.document.write("<table width = '100%'  >")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Jenis Pihak Berkepentingan");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Keterangan Jenis PB");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah Kepada Ali bin Abu");
new_window.document.write("</td>")
new_window.document.write("</tr>")

new_window.document.write("</table>")
}

if(jenis_popup == "3")
{
new_window.document.write("Skrin 'Pihak Berkepentingan' adalah skrin baru yang ditempatkan di Urusan Siasatan & Perintah Seksyen 8. Tujuan skrin ini adalah untuk memudahkan pengguna untuk menambah, menghapus, mengemaskini dan menetapkan pilihan borang pada mana-mana  pihak bekepentingan.");
}

new_window.document.write("<br>");

new_window.document.write("</body></html>");
new_window.document.close(); 

}

function close_window() 
{
new_window.close();
}



  </script>
