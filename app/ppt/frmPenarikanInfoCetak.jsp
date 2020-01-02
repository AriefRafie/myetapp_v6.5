
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>


#set($!no_fail = "")
#set($!no_penarikan_balik = "")
#set($!nama_kementerian = "")
#set($!no_rujukan_kementerian = "")
#set($!txdTarikh = "")
#set($!txtKandungan = "")



#foreach($mt in $maklumat_tambahan)

#set($no_fail = $mt.NO_FAIL)
#set($no_penarikan_balik = $mt.NO_PENARIKANBALIK)
#set($nama_kementerian = $mt.NAMA_KEMENTERIAN)
#set($no_rujukan_kementerian = $mt.NO_RUJUKAN_SURAT)
#set($txdTarikh = $mt.TARIKH)
#set($txtKandungan = "")

#end


#if($readmode == "edit")
#set($disabledmode = "")
#set($readonlymode = "")
#elseif($readmode == "view")
#set($disabledmode = "disabled")
#set($readonlymode = "readonly")
#else
#set($disabledmode = "")
#set($readonlymode = "")
#end

<table width="100%">
  <tr>
    <td>
    <fieldset id="header">
	<legend>SURAT MINTA MAKLUMAT TAMBAHAN BAGI PENARIKAN BALIK</legend>
   <table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%"><span >No. Rujukan JKPTG Negeri</span></td>
    <td width="1%">:</td>
    <td width="70%">$!no_fail</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Penarikan Balik</td>
    <td>:</td>
    <td>$!no_penarikan_balik</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Kementerian</td>
    <td>:</td>
    <td>$!nama_kementerian</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Rujukan Kementerian</td>
    <td>:</td>
    <td>$!no_rujukan_kementerian</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Surat</td>
    <td>:</td>
    <td>
    <input name="txdTarikh" type="text" id="txdTarikh" size="10" maxlength="10"   value="$!txdTarikh" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikh_check','no','surat','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />     
      <a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> 
        <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>   
       <span id="txdTarikh_check" style="color:red" ></span>    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Kandungan Surat</td>
    <td valign="top">:</td>
    <td><textarea name="txtKandungan" id="txtKandungan" cols="80"  rows="6" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" $readonlymode class = "$disabledmode" >$!txtKandungan</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
    
    <!--
    #if($readmode == "view")
    <label>
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini_Dokumen('$id_dokumen')">
    </label>
    <label>
      <input type="button" name="cmdHapus_dokumen" id="cmdHapus_dokumen" value="Hapus" onclick="Delete('$!id_dokumen')" >
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Upload('$!screen_name')" >
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="Batal('$!id_dokumen')">
      </label>
      #end      
      -->
      
      
      <label>
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />    
      </label>
      <label> 
      <input type="button" name="cmdKembali" id="cmdKembali"  value="Kembali" onclick="kembali('$!screen_name')" />   
      </label></td>
  </tr>
  
</table>

    </fieldset>
    
   </td>
  </tr>
</table>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue">Surat Minta Maklumat Tambahan</font></a></td>
      </tr>           
    </table>
</fieldset>

	
     
      <input type="hidden" name="sub_command" id="sub_command" />
	  <input type="hidden" name="location" id="location" />
      <input type="hidden" name="point" id="point" />
      <input type="hidden" name="id_permohonan" value="$id_permohonan">
      <input type="hidden" name="id_pembatalan" value="$!id_pembatalan">
      <input type="hidden" name="screen_name" id="screen_name" value="$!screen_name" />
      <input type="hidden" name="id_dokumen" id="id_dokumen" value="$!id_dokumen"  />
      <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
      <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
      <input type="hidden" name="alert_message" id="alert_message" />

<script type="text/javascript">
var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
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
window.location.hash='header';
goTo('header');
}
checking_validation(document.${formName}.txdTarikh,'txdTarikh_check','no','surat','tarikh');
}

</script>

<script>


function kembali(s)
{


	var id_pembatalan = document.${formName}.id_pembatalan.value;
	var id_permohonan = document.${formName}.id_permohonan.value;	

if(s == 's1')
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_tambahan&point=maklumat_tambahan&screen_name="+s;	
	document.${formName}.submit();
}
else if(s == 's2')
{

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=PembatalanAmbilTanahLotUnit&sub_command=view_PembatalanAmbilTanahLotUnit&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_tambahan&point=maklumat_tambahan&screen_name="+s;	
	document.${formName}.submit();
}
else
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_tambahan&point=maklumat_tambahan&screen_name="+s;	
	document.${formName}.submit();
}


}



function Cetak()
{	


var c = 0;

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


if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
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
	   
		  
	      DateField.select();
		//  DateField.focus();
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
	   {	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	//   DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	//   DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
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
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   else
	   {
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
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
		   document.${formName}.alert_message.value  = "Sila masukkan dokumen terlebih dahulu";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
	    }
		else
		{
		   document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		
		
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

</script>

