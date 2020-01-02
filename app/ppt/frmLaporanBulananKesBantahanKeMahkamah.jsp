<style type="text/css">
<!--
.style36 {font-size: 12}
-->
</style>
<fieldset>
<legend>LAPORAN KES BANTAHAN KE MAHKAMAH</legend>
<table width="100%" border="0" >
 
  <!--
  <tr>
    <td><div align="right">Tahun</div></td>
    <td><div align="center">:</div></td>
    <td><select name="socTahun" id="socTahun" >
    <option value="">SILA PILIH TAHUN</option>
    <option value="2009">2009</option>
    <option value="2010">2010</option>
    </select>
    </td>
  </tr>
  <tr>
    <td><div align="right">Bulan</div></td>
    <td><div align="center">:</div></td>
    <td><select name="socBulan" id="socBulan">
    <option value="">SILA PILIH BULAN</option>
    <option value="01">JANUARI</option>
    <option value="02">FEBRUARI</option>
    <option value="03">MAC</option>
    <option value="04">APRIL</option>
    <option value="05">MEI</option>
    <option value="06">JUN</option>
    <option value="07">JULAI</option>
    <option value="08">OGOS</option>
    <option value="09">SEPTEMBER</option>
    <option value="10">OKTOBER</option>
    <option value="11">NOVEMBER</option>
    <option value="12">DISEMBER</option>
    </select>
    </td>
  </tr>
  -->
  <tr>
    <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td width="28%"><div align="left">Nama Pejabat JKPTG</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="70%">
    
      <label>
      
      <select name="socPejabat" id="socPejabat" class="autoselect"  onchange="checking_validation(this,'socPejabat_check','yes','pejabat JKPTG','drop');" onblur="checking_validation(this,'socPejabat_check','yes','pejabat JKPTG','drop')">
      
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
       <span id="socPejabat_check" class="alert_msg" ></span> 
      </label>       </td>
  </tr>
  <tr>
   <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td><div align="left">Tempoh</div></td>
    <td><div align="center">:</div></td>
    <td><span class="style36">
      <label>
      <input name="txtTarikhMula" type="text" style="text-transform:uppercase;"  id="txdTarikhLahirPentingU" size="10" maxlength="10"  value="" />
      <a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/> </a></label>hingga 
<label>
<input name="txtTarikhHingga" type="text" style="text-transform:uppercase;"  id="txdTarikhLahirPentingU2" size="10" maxlength="10" value="" />
<a href="javascript:displayDatePicker('txtTarikhHingga',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/> </a></label>
    </span></td>
  </tr>
  <tr>
  <td colspan="4">
    <div align="center">
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetakLaporan1('02','2010','26')">
      <input type="button" name="cmdIsiSemula" id="cmdIsiSemula" value="Kosongkan"> 
      </div></td>
  </tr>
</table>
</fieldset>

<script>





window.onload = submitForm;

function submitForm() 
{



checking_validation(document.${formName}.socPejabat,'socPejabat_check','yes','pejabat JKPTG','drop');
//checking_validation(document.${formName}.txdTarikhTerimaSurat,'txdTarikhTerimaSurat_check','yes','terima surat','tarikh');


}









function cetakLaporan1(bulan,tahun,id_pejabat) {
	 var url = "../servlet/ekptg.report.ppt.LaporanKesBantahanBulanTahun?BULAN="+bulan+"&TAHUN="+tahun+"&ID_PEJABAT="+id_pejabat+"";
 //   var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
 var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
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
	   
		  
	      DateField.select();		
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
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {/*	   
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+"");	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");	
	 
	//   DateField.focus();
	   
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");	
		
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
	   
	   
	   
	   if(jenis_field == "drop")
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pilih "+value_field+"");	
		
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
	   {/*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila pilih "+value_field+"");	
			
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
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */
		    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan dokumen terlebih dahulu");	
		
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
	   
	   
	   
	
}




</script>





 

