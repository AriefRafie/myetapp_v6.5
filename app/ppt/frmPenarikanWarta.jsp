
 #if($senarai_hakmilik_batal.size()!=0)
             #foreach($listb in $senarai_hakmilik_batal) 
              
                       <input name="lot_ambilX" id="lot_ambilX"  value="$listb.LUAS_AMBIL_EDIT" type="hidden" >
                       <input name="lot_ambil_asalX" id="lot_ambil_asalX"  value="$listb.LUAS_AMBIL_ASAL" type="hidden" >
                       <input name="luas_lotX" id="luas_lotX"  value="$listb.LUAS_LOT" type="hidden" >
                       <input name="id_hakmilik_luasX" id="id_hakmilik_luasX"  style="width:100%" value="$listb.ID_HAKMILIK" type="hidden" >
                       <input name="id_lot_tarikX" id="id_lot_tarikX"  value="$listb.ID_PENARIKANHAKMILIK" type="hidden" > 
                       <input name="lot_tarikX" id="lot_tarikX" value="$listb.LUAS_LOT_TARIK_EDIT" type="hidden"  >   
                 
             
             #end
             #end


#parse("app/ppt/paging_penarikanbalik.jsp")


#set($txtNoWarta = "") 
#set($socProcesWarta = "") 
#set($STATUS_SEMAKAN = "")
#set($id_warta = "")
#set($txdTarikhWarta = "")
#set($ProcesWarta = "")



#foreach($view in $maklumat_penyediaan)
#set($id_mmk = $view.ID_MMK)
#set($id_pembatalan = $view.ID_PEMBATALAN)
#set($STATUS_SEMAKAN = $view.STATUS_SEMAKAN)
#end



#foreach($wr in $maklumat_warta)
#set($txtNoWarta = $wr.NO_WARTA) 
#set($socProcesWarta = $wr.PROSES_WARTA) 
#set($txdTarikhWarta = $wr.TARIKH_WARTA)
#set($id_warta = $wr.ID_WARTA)
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
    <td> #parse("app/ppt/header_ppt.jsp")</td>
  </tr>
 
  <tr>
    <td>
    
    <fieldset><legend>MAKLUMAT WARTA</legend>
    </p>
    <table width="100%">
    <tr>
    <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td width="28%">Proses Warta</td>
    <td width="1%">:</td>
    <td width="70%">
    
    #if($readmode == "view")
    
    #if($socProcesWarta == "1")
    #set($ProcesWarta = "PTD")
    #elseif($socProcesWarta == "2")
    #set($ProcesWarta = "PTG")
    #else
    #set($ProcesWarta = "")
    #end
    <input name="ProcesWarta" type="text" class="disabled" id="ProcesWarta" value="$ProcesWarta" size="20" maxlength="20" readonly  />
    #else
    
<select name="socProcesWarta" id="socProcesWarta" onChange="checking_validation(this,'socProcesWarta_check','yes','proses warta','drop')" onFocus="checking_validation(this,'socProcesWarta_check','yes','proses warta','drop')" class="mediumselect" > 

    #if($socProcesWarta == "1")
    			<option value="1">PTD</option>
				<option value="2">PTG</option>
                <option value="">SILA PILIH</option>
    #elseif($socProcesWarta == "2")
    			<option value="2">PTG</option>  
                <option value="1">PTD</option> 
                <option value="">SILA PILIH</option>			 
    #else
     		    <option value="">SILA PILIH</option>
      			<option value="1">PTD</option>
				<option value="2">PTG</option>    
    #end

     		
      			  			
	</select>
    
    #end
            <span id="socProcesWarta_check" class="alert_msg" ></span>
            </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>No. Warta</td>
    <td>:</td>
    <td>
    <input name="txtNoWarta" type="text" id="txtNoWarta" size="30" maxlength="50" value="$txtNoWarta" style="" onBlur="checking_validation(this,'txtNoWarta_check','yes','no warta','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="checking_validation(this,'txtNoWarta_check','yes','no warta','normal')" >
        <span id="txtNoWarta_check" class="alert_msg" ></span>
    </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Tarikh Warta</td>
    <td>:</td>
    <td><input name="txdTarikhWarta" type="text" id="txdTarikhWarta" size="10" maxlength="10"   value="$!txdTarikhWarta" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhWarta_check','yes','warta','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
      #if($readmode == "edit")      
     <a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> 
      <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>       
      #end 
       <span id="txdTarikhWarta_check" class="alert_msg" ></span>   </td>
  </tr>
 
  
</table>
 </p>
    </fieldset>

<br />

 <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK DOKUMEN BERKAITAN</strong></legend>
                
               
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumen('$id_pembatalan','warta_penarikan')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    </fieldset>
    </td>
  </tr>
   #if($readmode == "edit")
  <tr>
    <td colspan="4">#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td colspan="4"></td>
  </tr>  
  #end
  <tr>
    <td colspan="2">
    <div align="center" >
    <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini()">
      </label>
    
      <label>
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan()">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()">
      </label>
    #end  
     
       #if($id_pembatalan != "")
       <!--
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="" >  
      </label> 
      -->   
      #end
       <label></label>
       
      <!-- 
         #if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5")
      <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Maklumat Hakmilik Penarikan Balik)"  onClick="popupEtanahTarik('$id_fail','$id_permohonan','$!id_pembatalan','TarikBalik','')">
      #end
      -->
     </div>      </td>
  </tr>
</table>


<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr >
		 <td><a href="#" onClick="javascript:cetakBATAL_ENDOS_PTG('$!id_permohonan')"><font color="blue">Surat Batal Endorsan PTG</font></a></td>
	  </tr>
    
	 
         
          <tr >
		 <td><a href="#" onClick="javascript:cetakBATAL_ENDOS_PTD('$!id_permohonan')"><font color="blue">Surat Batal Endorsan PTD</font></a></td>
	  
	  </tr>
	 

	  
	
    </table>
</fieldset>		



  <input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  <input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$!id_pembatalan" />
  <input type="hidden" name="screen_name" id="screen_name" value="s2" />
  <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
  <input type="hidden" name="alert_message" id="alert_message" />
  <input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
  <input type="hidden" name="jumlah_semua" id="jumlah_semua" />
  <input type="hidden" name="id_mmk" id="id_mmk" value="$!id_mmk" />
  <input type="hidden" name="id_mmkkeputusan" id="id_mmkkeputusan" value="$!id_mmkkeputusan" />
  <input type="hidden" name="id_warta" id="id_warta" value="$!id_warta" />

<script type="text/javascript">

var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function cetakBATAL_ENDOS_PTG(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BatalEndosDPTG&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBATAL_ENDOS_PTD(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BatalEndosDPTD&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function popupEtanahTarik(id_fail,id_permohonan,id_penarikan,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&id_penarikan="+id_penarikan+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

function popupCarianDokumen(id_penarikanbalik,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_penarikanbalik="+id_penarikanbalik+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

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

checking_validation(document.${formName}.txtNoWarta,'txtNoWarta_check','yes','no warta','normal');
checking_validation(document.${formName}.txdTarikhWarta,'txdTarikhWarta_check','yes','warta','tarikh');
checking_validation(document.${formName}.socProcesWarta,'socProcesWarta_check','yes','proses warta','drop');

}



function simpan()
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
else
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "Warta";
	document.${formName}.sub_command.value = "Warta";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.submit();
	}
	}
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "Warta";
	document.${formName}.sub_command.value = "Warta";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.submit();
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "Warta";
	document.${formName}.sub_command.value = "Warta";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.submit();
	}
}

function kemaskini()
{
	document.${formName}.command.value = "Warta";
	document.${formName}.sub_command.value = "Warta";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
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
	   else
	   {
	  
	   /*
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");	
		
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");		
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
	//   DateField.focus();
	$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan tarikh "+value_field+" dengan betul");		
	 
	   
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");		
		
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
	  
	   
	   if(jenis_field == "drop")
	   {
	  
	   if((field.value == "" || field.value == "0") && mandatory == "yes")
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");		
		
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");		
			
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
	    {/*
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


function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
  </script>
