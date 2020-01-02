 <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
     
             <input type="hidden" name="idFail" />
            <input type="hidden" name="idPermohonan" />
            <input type="hidden" name="idStatus" />
            <input type="hidden" name="actionPelepasan" /> 
            <input type="hidden" name="actionLesen" />
            <input type="hidden" name="actionPenyewaan" /> 
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
    <td width="64%"><!-- $list.NAMA_NEGERI -->
          <select name="socUrusan" class="autoselect" id="socUrusan"  onchange="showSuburusan()"  onblur="showSuburusan()" >
        
    #if($socUrusan != "")
    
    #foreach($ln in $list_urusan) 
    #if($socUrusan==$ln.ID_URUSAN)
    
      <option value="$ln.ID_URUSAN">$ln.NAMA_URUSAN</option>
                                           
    #end 
    #end 
   
    #foreach($ln in $list_urusan)
    #if($socUrusan!=$ln.ID_URUSAN)
    
      <option value="$ln.ID_URUSAN">$ln.NAMA_URUSAN</option>
                                           
    #end      
    #end
    
      <option value="">SILA PILIH URUSAN</option>
       
    
    #else
   
    
      <option value="">SILA PILIH URUSAN</option>
              
    #foreach($ln in $list_urusan)   
    
      <option value="$ln.ID_URUSAN">$ln.NAMA_URUSAN</option>
      
    #end
    
    #end
    
    
    </select></td>
      </tr>
  
  
  <tr>
    <td>&nbsp;</td>
    <td>Jenis Sub Urusan</td>
    <td>:</td>
   <td >
  
      <label>
      <select name="socSuburusan" class="autoselect" id="socSuburusan"  onchange=""  onblur="" >
        
    #if($socSuburusan != "")
    
    #foreach($ln in $list_suburusan_php) 
    #if($socSuburusan==$ln.ID_SUBURUSAN)
    
      <option value="$ln.ID_SUBURUSAN">$ln.NAMA_SUBURUSAN</option>
                                           
    #end 
    #end 
   
    #foreach($ln in $list_suburusan_php)
    #if($socSuburusan!=$ln.ID_SUBURUSAN)
    
      <option value="$ln.ID_SUBURUSAN">$ln.NAMA_SUBURUSAN</option>
                                           
    #end      
    #end
    
      <option value="">SILA PILIH SUBURUSAN</option>
       
    
    #else
   
    
      <option value="">SILA PILIH SUBURUSAN</option>
              
    #foreach($ln in $list_suburusan_php)   
    
      <option value="$ln.ID_SUBURUSAN">$ln.NAMA_SUBURUSAN</option>
      
    #end
    
    #end
    
    
    </select>
     
     
           </label>       </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Mula (Permohonan)
 <!--   <span align="center" class="font2"><font style="cursor:help" title="info"  onMouseOut="close_window()"   onMouseOver="open_new_window('1');this.style.cursor='help'" >
       #parse("app/ppt/infoblink_biru.jsp")
       </font>                                 </span>  -->   </td>
    <td>:</td>
   <td >
    
     <input name="txdTarikhMula" type="text" id="txdTarikhMula" size="10" maxlength="10" value="$!txdTarikhMula"    onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhMula_check','yes','mula','tarikh');" onKeyUp="validateTarikh(this,this.value);"  />  
          
     <a href="javascript:displayDatePicker('txdTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    
         <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     
      
       <span id="txdTarikhMula_check" class="alert_msg"></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Akhir (Permohonan)</td>
    <td>:</td>
    <td> 
     <input name="txdTarikhAkhir" type="text" id="txdTarikhAkhir" size="10" maxlength="10"  value="$!txdTarikhAkhir"   onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhAkhir_check','yes','akhir','tarikh');" onKeyUp="validateTarikh(this,this.value);"  />  
          
     <a href="javascript:displayDatePicker('txdTarikhAkhir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    
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
  
  #if($!socUrusan == "6") 
  
  #if($!socSuburusan == "32") 
  #parse("app/php2/kpi/KPI_Pelepasan_Penawaran.jsp")
  #elseif($!socSuburusan == "33")
  #parse("app/php2/kpi/KPI_Pelepasan_Tukarguna.jsp")
  #elseif($!socSuburusan == "34")
  #parse("app/php2/kpi/KPI_Pelepasan_Pelepasan.jsp")
  #else
  underconstruction
  #end
  
  #elseif($!socUrusan == "7")
  #parse("app/php2/kpi/KPI_Penyewaan_Penyewaan.jsp") 
  #elseif($!socUrusan == "9")
  #parse("app/php2/kpi/KPI_APB.jsp") 
  #else
  underconstruction
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
    
    
          
   #if($!socUrusan == "9")
  <tr>      
        <td><a href="#" class="style2" onClick="cetakAPB('$txdTarikhMula','$txdTarikhAkhir','$SX1','$SST1','$SX2','$SST2','$M1','$M2','$M3','$SX3','$SST3','$SX4','$SST4','$N1','$N2','$N3','$SX5','$SST5','$SX6','$SST6','$O1','$O2','$O3','$SX7','$SST7','$SX8','$SST8','$P1','$P2','$P3','$SX9','$SST9','$SX10','$SST10','$Q1','$Q2','$Q3','$SX11','$SST11','$SX12','$SST12','$R1','$R2','$R3')"><font color="blue">Laporan KPI APB</font></a></td>
      </tr>   
      
      
  #end    
    
  #if($!socUrusan == "7")
  
  <tr>      
        <td><a href="#" class="style2" onClick="cetakPenyewaan('$txdTarikhMula','$txdTarikhAkhir','$SX1','$SST1','$SX2','$SST2','$M1','$M2','$M3','$SX3','$SST3','$SX4','$SST4','$N1','$N2','$N3','$SX5','$SST5','$SX6','$SST6','$O1','$O2','$O3','$socSuburusan')"><font color="blue">Laporan KPI Penyewaan  
      #if($!socSuburusan == "36")
      (Tanah)
      #elseif($!socSuburusan == "35")
      (Banggunan)
      #end</font></a></td>
      </tr> 
  
  
     
     
  #end
  
 
    
    
   #if($!socUrusan == "6")
   #if($!socSuburusan == "32")
  <tr>      
        <td><a href="#" class="style2" onClick="cetakPenawaran('$txdTarikhMula','$txdTarikhAkhir','$SX1','$SST1','$SX2','$SST2','$M1','$M2','$M3','$SX3','$SST3','$SX4','$SST4','$N1','$N2','$N3','$SX5','$SST5','$SX6','$SST6','$O1','$O2','$O3')"><font color="blue">Laporan KPI Penawaran</font></a></td>
      </tr> 
  #end
   #if($!socSuburusan == "33")
  <tr>      
        <td><a href="#" class="style2" onClick="cetakTukarguna('$txdTarikhMula','$txdTarikhAkhir','$SX1','$SST1','$SX2','$SST2','$M1','$M2','$M3','$SX3','$SST3','$SX4','$SST4','$N1','$N2','$N3')"><font color="blue">Laporan KPI Tukarguna Tanah</font></a></td>
      </tr>  
  #end
  #if($!socSuburusan == "34")
  <tr>      
        <td><a href="#" class="style2" onClick="cetakPelepasan('$txdTarikhMula','$txdTarikhAkhir','$SX1','$SST1','$SX2','$SST2','$M1','$M2','$M3','$SX3','$SST3','$SX4','$SST4','$N1','$N2','$N3','$SX5','$SST5','$SX6','$SST6','$O1','$O2','$O3','$SX7','$SST7','$SX8','$SST8','$P1','$P2','$P3')"><font color="blue">Laporan KPI Pelepasan</font></a></td>
      </tr>  
  #end
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

function showSuburusan()
{

 document.${formName}.point.value = "socSubrusan";
 document.${formName}.location.value = "pilihan";
 document.${formName}.command.value = "Suburusan";
 document.${formName}.submit();

}

function showUrusan()
{

var c = 0;



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

if(document.${formName}.socUrusan.value == "")
{
alert("Sila pilih Jenis Urusan");
document.${formName}.socUrusan.focus();
return;
}
else if(document.${formName}.socSuburusan.value == "")
{
alert("Sila pilih jenis Jenis Suburusan");
document.${formName}.socSuburusan.focus();
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

    var url = "../x/${securityToken}/ekptg.view.php2.kpi.FrmPopupKPIPHP?tempat="+tempat+"&txdTarikhMula="+document.${formName}.txdTarikhMula.value+"&txdTarikhAkhir="+document.${formName}.txdTarikhAkhir.value+"&range1="+range1+"&range2="+range2+"&kategori="+kategori+"&tajuk="+tajuk+"&id_urusan="+document.${formName}.socUrusan.value+"&id_suburusan="+document.${formName}.socSuburusan.value;
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
 
 
function papar_screen_php(idFail,idStatus,idSuburusan) {

alert("idFail :"+idFail);
alert("idStatus :"+idStatus);
alert("idSuburusan :"+idSuburusan);

    document.${formName}.idFail.value = idFail;

	url = "../servlet/ekptg.view.php2.FrmPLPServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	
	var dikesan = "x";
	
	if(idSuburusan == '34'){
		if (idStatus == '1610198'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPMaklumatPermohonanView";
			dikesan = "y";
		} else if (idStatus == '1610199'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPJabatanTeknikalView";
			dikesan = "y";
		}else if (idStatus == '1610200'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPLawatanTapakView";
			dikesan = "y";
		}else if (idStatus == '1610201'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPMesyuaratView";
			dikesan = "y";
		}else if (idStatus == '1610202'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPCetakanMinitKewanganView";
			dikesan = "y";
		}else if (idStatus == '1610203'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPKemasukanMinitKewanganView";
			dikesan = "y";
		}else if (idStatus == '1610204'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPCetakanMinitCeraianView";
			dikesan = "y";
		}else if (idStatus == '1610205'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPKelulusanMenteriView";
			dikesan = "y";
		}else if (idStatus == '1610206'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPKeputusanView";
			dikesan = "y";
		} else if (idStatus == '1610207' || idStatus == '1610208'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPKeputusanView";
			dikesan = "y";
		}
	}
	else if(idSuburusan == '33'){
		if (idStatus == '1610198'){
		document.${formName}.action = "$EkptgUtil.getTabID("Tukar Guna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRMaklumatPermohonanView";
		dikesan = "y";
		} else if (idStatus == '1610199'){
		document.${formName}.action = "$EkptgUtil.getTabID("Tukar Guna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRJabatanTeknikalView";
		dikesan = "y";
		}else if (idStatus == '1610200'){
		document.${formName}.action = "$EkptgUtil.getTabID("Tukar Guna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRLawatanTapakView";
		dikesan = "y";
		}else if (idStatus == '1610201'){
		document.${formName}.action = "$EkptgUtil.getTabID("Tukar Guna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRMesyuaratView";
		dikesan = "y";
		}else if (idStatus == '1610206'){
		document.${formName}.action = "$EkptgUtil.getTabID("Tukar Guna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRKeputusanView";
		dikesan = "y";
		}else if (idStatus == '1610209'){
		document.${formName}.action = "$EkptgUtil.getTabID("Tukar Guna",$portal_role)?_portal_module=ekptg.view.php2.FrmRAYUANTKRLawatanTapakView";
		dikesan = "y";
		}
		else if (idStatus == '1610207' || idStatus == '1610208'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukar Guna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRKeputusanView";
			dikesan = "y";
		}
	}
	else if(idSuburusan == '32'){
		if (idStatus == '1610198'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView";
		dikesan = "y";
	} else if (idStatus == '1610200'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWLawatanTapakView";
		dikesan = "y";
	} else if (idStatus == '1610199'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWJabatanTeknikalView";
		dikesan = "y";
	} else if (idStatus == '1610210'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWSenaraiTawaranKementerianView";
		dikesan = "y";
	} else if (idStatus == '1610201'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWMesyuaratView";
		dikesan = "y";
		} else if (idStatus == '1610211'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWButiranMaklumBalasKJPView";
		dikesan = "y";
		} else if (idStatus == '1610206'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWKeputusanView";
		dikesan = "y";
		}
		else if (idStatus == '1610207' || idStatus == '1610208'){
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWKeputusanView";
			dikesan = "y";
		}
	}
	else if(idSuburusan == '35' || idSuburusan == '36'){//penyewaan
	
	if (idStatus == '1610198'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMaklumatPermohonanView";
		dikesan = "y";
	} else if (idStatus == '1610199'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWJabatanTeknikalView";
		dikesan = "y";
	} else if (idStatus == '1610200'){
		document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWLawatanTapakView";
		dikesan = "y";
	} else if (idStatus == '1610213'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWKertasRingkasanView";
		dikesan = "y";
	} else if (idStatus == '1610201'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMesyuaratView";
		dikesan = "y";
	} else if (idStatus == '1610206'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWKeputusanView";
		dikesan = "y";
	} else if (idStatus == '1610214'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWPerjanjianView";
		dikesan = "y";
	} else {
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMaklumatPermohonanView";
		dikesan = "y";
	} 
	
	}
	else if(idSuburusan == '57'){//APB
	
	if (idStatus == '94'){
		document.${formName}.idFail.value = idFail;
		document.${formName}.idStatus.value = idStatus;
		document.${formName}.actionLesen.value = "papar";
	} else if (idStatus == '1610198'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBMaklumatPermohonanView";
	} else if (idStatus == '1610199'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	}else if (idStatus == '1610235'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBKertasKerjaJawatankuasaView";
	}else if (idStatus == '1610201'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBMesyuaratView";
	}else if (idStatus == '1610213'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBKertasRingkasanPermohonan";
	}else if (idStatus == '1610205'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBPerakuanKSUView";
	}else if (idStatus == '1610206'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBCetakanSuratKeputusan";
	}else if (idStatus == '1610236'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBMaklumatKelulusanHidrografi";
	}else if (idStatus == '1610237'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBCetakSuratMohonByrSblmLesenKeluar";
	} else if (idStatus == '1610238'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBPenyediaanLesenDanMemoKepadaMenteri";
	}else if (idStatus == '1610239'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBCetakSuratKelulusanLesenKepadaPemohon";
	}else if (idStatus == '1610207'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBCetakSuratKelulusanLesenKepadaPemohon";
	}else if (idStatus == '1610244'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBTamatLesen";
	}		
	else if (idStatus == '1610208'){
		document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmABPRayuanPenolakan";
	}
	
	
	}
	
	
	
	
	if(dikesan == "y")
	{
	document.${formName}.submit();
	}
	else
	{
	alert("Lokasi fail tidak dapat dikesan");
	}
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		window.location.hash=id;
        goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


function cetakPenawaran(txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7,ENAMA1,ENAMA2,ENAMA3,ENAMA4,ENAMA5,ENAMA6,ENAMA7,TUJUHA1,TUJUHA2,TUJUHA3,TUJUHA4,TUJUHA5,TUJUHA6,TUJUHA7)
{

    var url = "../servlet/ekptg.report.php2.KPICetakPenawaran?TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&B3="+document.${formName}.B3.value+"&B4="+document.${formName}.B4.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&C3="+document.${formName}.C3.value+"&C4="+document.${formName}.C4.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&D3="+document.${formName}.D3.value+"&D4="+document.${formName}.D4.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&E3="+document.${formName}.E3T.value+"&E4="+document.${formName}.E4T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&F3="+document.${formName}.F3T.value+"&F4="+document.${formName}.F4T.value+"&A1="+document.${formName}.DITERIMA.value+"&A3="+document.${formName}.DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7+"&5B1="+ENAMA1+"&5B2="+ENAMA2+"&5B3="+ENAMA3+"&5B4="+ENAMA4+"&5B5="+ENAMA5+"&5B6="+ENAMA6+"&5B7="+ENAMA7+"&5C1="+TUJUHA1+"&5C2="+TUJUHA2+"&5C3="+TUJUHA3+"&5C4="+TUJUHA4+"&5C5="+TUJUHA5+"&5C6="+TUJUHA6+"&5C7="+TUJUHA7; 
	
	
	 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function cetakTukarguna(txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7,ENAMA1,ENAMA2,ENAMA3,ENAMA4,ENAMA5,ENAMA6,ENAMA7)
{

    var url = "../servlet/ekptg.report.php2.KPICetakTukarguna?TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&B3="+document.${formName}.B3.value+"&B4="+document.${formName}.B4.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&C3="+document.${formName}.C3.value+"&C4="+document.${formName}.C4.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&D3="+document.${formName}.D3.value+"&D4="+document.${formName}.D4.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&E3="+document.${formName}.E3T.value+"&E4="+document.${formName}.E4T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&F3="+document.${formName}.F3T.value+"&F4="+document.${formName}.F4T.value+"&A1="+document.${formName}.DITERIMA.value+"&A3="+document.${formName}.DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7+"&5B1="+ENAMA1+"&5B2="+ENAMA2+"&5B3="+ENAMA3+"&5B4="+ENAMA4+"&5B5="+ENAMA5+"&5B6="+ENAMA6+"&5B7="+ENAMA7; 
	
	
	 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function cetakAPB(txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7,ENAMA1,ENAMA2,ENAMA3,ENAMA4,ENAMA5,ENAMA6,ENAMA7,TUJUHA1,TUJUHA2,TUJUHA3,TUJUHA4,TUJUHA5,TUJUHA6,TUJUHA7,LAPANA1,LAPANA2,LAPANA3,LAPANA4,LAPANA5,LAPANA6,LAPANA7,SEMBILANA1,SEMBILANA2,SEMBILANA3,SEMBILANA4,SEMBILANA5,SEMBILANA6,SEMBILANA7,SEPULOHA1,SEPULOHA2,SEPULOHA3,SEPULOHA4,SEPULOHA5,SEPULOHA6,SEPULOHA7)
{

    var url = "../servlet/ekptg.report.php2.KPICetakAPB?TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&B3="+document.${formName}.B3.value+"&B4="+document.${formName}.B4.value+"&B5="+document.${formName}.B5.value+"&B6="+document.${formName}.B6.value+"&B7="+document.${formName}.B7.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&C3="+document.${formName}.C3.value+"&C4="+document.${formName}.C4.value+"&C5="+document.${formName}.C5.value+"&C6="+document.${formName}.C6.value+"&C7="+document.${formName}.C7.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&D3="+document.${formName}.D3.value+"&D4="+document.${formName}.D4.value+"&D5="+document.${formName}.D5.value+"&D6="+document.${formName}.D6.value+"&D7="+document.${formName}.D7.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&E3="+document.${formName}.E3T.value+"&E4="+document.${formName}.E4T.value+"&E5="+document.${formName}.E5T.value+"&E6="+document.${formName}.E6T.value+"&E7="+document.${formName}.E7T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&F3="+document.${formName}.F3T.value+"&F4="+document.${formName}.F4T.value+"&F5="+document.${formName}.F5T.value+"&F6="+document.${formName}.F6T.value+"&F7="+document.${formName}.F7T.value+"&A1="+document.${formName}.DITERIMA.value+"&A3="+document.${formName}.DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7+"&5B1="+ENAMA1+"&5B2="+ENAMA2+"&5B3="+ENAMA3+"&5B4="+ENAMA4+"&5B5="+ENAMA5+"&5B6="+ENAMA6+"&5B7="+ENAMA7+"&5C1="+TUJUHA1+"&5C2="+TUJUHA2+"&5C3="+TUJUHA3+"&5C4="+TUJUHA4+"&5C5="+TUJUHA5+"&5C6="+TUJUHA6+"&5C7="+TUJUHA7+"&5D1="+LAPANA1+"&5D2="+LAPANA2+"&5D3="+LAPANA3+"&5D4="+LAPANA4+"&5D5="+LAPANA5+"&5D6="+LAPANA6+"&5D7="+LAPANA7+"&5E1="+SEMBILANA1+"&5E2="+SEMBILANA2+"&5E3="+SEMBILANA3+"&5E4="+SEMBILANA4+"&5E5="+SEMBILANA5+"&5E6="+SEMBILANA6+"&5E7="+SEMBILANA7+"&5F1="+SEPULOHA1+"&5F2="+SEPULOHA2+"&5F3="+SEPULOHA3+"&5F4="+SEPULOHA4+"&5F5="+SEPULOHA5+"&5F6="+SEPULOHA6+"&5F7="+SEPULOHA7; 
	
	 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}




function cetakTEST(txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7,ENAMA1,ENAMA2,ENAMA3,ENAMA4,ENAMA5,ENAMA6,ENAMA7,TUJUHA1,TUJUHA2,TUJUHA3,TUJUHA4,TUJUHA5,TUJUHA6,TUJUHA7,LAPANA1,LAPANA2,LAPANA3,LAPANA4,LAPANA5,LAPANA6,LAPANA7,SEMBILANA1,SEMBILANA2,SEMBILANA3,SEMBILANA4,SEMBILANA5,SEMBILANA6,SEMBILANA7,SEPULOHA1,SEPULOHA2,SEPULOHA3,SEPULOHA4,SEPULOHA5,SEPULOHA6,SEPULOHA7)
{

alert("test");

    var url = "../servlet/ekptg.report.php2.KPICetakPenyewaan?TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&B3="+document.${formName}.B3.value+"&B4="+document.${formName}.B4.value+"&B5="+document.${formName}.B5.value+"&B6="+document.${formName}.B6.value+"&B7="+document.${formName}.B7.value+"&B8="+document.${formName}.B8.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&C3="+document.${formName}.C3.value+"&C4="+document.${formName}.C4.value+"&C5="+document.${formName}.C5.value+"&C6="+document.${formName}.C6.value+"&C7="+document.${formName}.C7.value+"&C8="+document.${formName}.C8.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&D3="+document.${formName}.D3.value+"&D4="+document.${formName}.D4.value+"&D5="+document.${formName}.D5.value+"&D6="+document.${formName}.D6.value+"&D7="+document.${formName}.D7.value+"&D8="+document.${formName}.D8.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&E3="+document.${formName}.E3T.value+"&E4="+document.${formName}.E4T.value+"&E5="+document.${formName}.E5T.value+"&E6="+document.${formName}.E6T.value+"&E7="+document.${formName}.E7T.value+"&E8="+document.${formName}.E8T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&F3="+document.${formName}.F3T.value+"&F4="+document.${formName}.F4T.value+"&F5="+document.${formName}.F5T.value+"&F6="+document.${formName}.F6T.value+"&F7="+document.${formName}.F7T.value+"&F8="+document.${formName}.F8T.value+"&A1="+document.${formName}.DITERIMA.value+"&A3="+document.${formName}.DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7+"&5B1="+ENAMA1+"&5B2="+ENAMA2+"&5B3="+ENAMA3+"&5B4="+ENAMA4+"&5B5="+ENAMA5+"&5B6="+ENAMA6+"&5B7="+ENAMA7+"&5C1="+TUJUHA1+"&5C2="+TUJUHA2+"&5C3="+TUJUHA3+"&5C4="+TUJUHA4+"&5C5="+TUJUHA5+"&5C6="+TUJUHA6+"&5C7="+TUJUHA7+"&5D1="+LAPANA1+"&5D2="+LAPANA2+"&5D3="+LAPANA3+"&5D4="+LAPANA4+"&5D5="+LAPANA5+"&5D6="+LAPANA6+"&5D7="+LAPANA7+"&5E1="+SEMBILANA1+"&5E2="+SEMBILANA2+"&5E3="+SEMBILANA3+"&5E4="+SEMBILANA4+"&5E5="+SEMBILANA5+"&5E6="+SEMBILANA6+"&5E7="+SEMBILANA7+"&5F1="+SEPULOHA1+"&5F2="+SEPULOHA2+"&5F3="+SEPULOHA3+"&5F4="+SEPULOHA4+"&5F5="+SEPULOHA5+"&5F6="+SEPULOHA6+"&5F7="+SEPULOHA7; 
	
	 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}



function cetakPenyewaan(txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7,ENAMA1,ENAMA2,ENAMA3,ENAMA4,ENAMA5,ENAMA6,ENAMA7,TUJUHA1,TUJUHA2,TUJUHA3,TUJUHA4,TUJUHA5,TUJUHA6,TUJUHA7,id_suburusan)
{
var suburusan = "";
if(id_suburusan == "35")
{
suburusan = "(BANGUNAN)";
}
if(id_suburusan == "36")
{
suburusan = "(TANAH)";
}

    var url = "../servlet/ekptg.report.php2.KPICetakPenyewaan?TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&B3="+document.${formName}.B3.value+"&B4="+document.${formName}.B4.value+"&B5="+document.${formName}.B5.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&C3="+document.${formName}.C3.value+"&C4="+document.${formName}.C4.value+"&C5="+document.${formName}.C5.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&D3="+document.${formName}.D3.value+"&D4="+document.${formName}.D4.value+"&D5="+document.${formName}.D5.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&E3="+document.${formName}.E3T.value+"&E4="+document.${formName}.E4T.value+"&E5="+document.${formName}.E5T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&F3="+document.${formName}.F3T.value+"&F4="+document.${formName}.F4T.value+"&F5="+document.${formName}.F5T.value+"&A1="+document.${formName}.DITERIMA.value+"&A3="+document.${formName}.DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7+"&5B1="+ENAMA1+"&5B2="+ENAMA2+"&5B3="+ENAMA3+"&5B4="+ENAMA4+"&5B5="+ENAMA5+"&5B6="+ENAMA6+"&5B7="+ENAMA7+"&5C1="+TUJUHA1+"&5C2="+TUJUHA2+"&5C3="+TUJUHA3+"&5C4="+TUJUHA4+"&5C5="+TUJUHA5+"&5C6="+TUJUHA6+"&5C7="+TUJUHA7+"&SUBURUSAN="+suburusan; 
	
	 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function cetakPelepasan(txdTarikhMula,txdTarikhAkhir,LIMAA1,LIMAA2,LIMAA3,LIMAA4,LIMAA5,LIMAA6,LIMAA7,ENAMA1,ENAMA2,ENAMA3,ENAMA4,ENAMA5,ENAMA6,ENAMA7,TUJUHA1,TUJUHA2,TUJUHA3,TUJUHA4,TUJUHA5,TUJUHA6,TUJUHA7,LAPANA1,LAPANA2,LAPANA3,LAPANA4,LAPANA5,LAPANA6,LAPANA7)
{

    var url = "../servlet/ekptg.report.php2.KPICetakPelepasan?TARIKHMULA="+txdTarikhMula+"&TARIKHAKHIR="+txdTarikhAkhir+"&B1="+document.${formName}.B1.value+"&B2="+document.${formName}.B2.value+"&B3="+document.${formName}.B3.value+"&B4="+document.${formName}.B4.value+"&B5="+document.${formName}.B5.value+"&C1="+document.${formName}.C1.value+"&C2="+document.${formName}.C2.value+"&C3="+document.${formName}.C3.value+"&C4="+document.${formName}.C4.value+"&C5="+document.${formName}.C5.value+"&D1="+document.${formName}.D1.value+"&D2="+document.${formName}.D2.value+"&D3="+document.${formName}.D3.value+"&D4="+document.${formName}.D4.value+"&D5="+document.${formName}.D5.value+"&E1="+document.${formName}.E1T.value+"&E2="+document.${formName}.E2T.value+"&E3="+document.${formName}.E3T.value+"&E4="+document.${formName}.E4T.value+"&E5="+document.${formName}.E5T.value+"&F1="+document.${formName}.F1T.value+"&F2="+document.${formName}.F2T.value+"&F3="+document.${formName}.F3T.value+"&F4="+document.${formName}.F4T.value+"&F5="+document.${formName}.F5T.value+"&A1="+document.${formName}.DITERIMA.value+"&A3="+document.${formName}.DISELESAI.value+"&5A1="+LIMAA1+"&5A2="+LIMAA2+"&5A3="+LIMAA3+"&5A4="+LIMAA4+"&5A5="+LIMAA5+"&5A6="+LIMAA6+"&5A7="+LIMAA7+"&5B1="+ENAMA1+"&5B2="+ENAMA2+"&5B3="+ENAMA3+"&5B4="+ENAMA4+"&5B5="+ENAMA5+"&5B6="+ENAMA6+"&5B7="+ENAMA7+"&5C1="+TUJUHA1+"&5C2="+TUJUHA2+"&5C3="+TUJUHA3+"&5C4="+TUJUHA4+"&5C5="+TUJUHA5+"&5C6="+TUJUHA6+"&5C7="+TUJUHA7+"&5D1="+LAPANA1+"&5D2="+LAPANA2+"&5D3="+LAPANA3+"&5D4="+LAPANA4+"&5D5="+LAPANA5+"&5D6="+LAPANA6+"&5D7="+LAPANA7; 
	
	
	 
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
