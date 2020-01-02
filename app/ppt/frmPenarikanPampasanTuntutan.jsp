<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>



#set($txtTuntutanTuanTanah = "")
#set($txtLainTuntutan = "")
#set($txtPBDaftar = "")
#set($txtPBXDaftar = "")
#set($txtStatus = "")


#foreach($list_siasatan in $maklumat_siasatan_pb)
#set($id_siasatan = $list_siasatan.ID_SIASATAN)
#set($txtTuntutanTuanTanah = $list_siasatan.TUNTUTAN_TUANTNH)
#set($txtLainTuntutan = $list_siasatan.TUNTUTAN_PB_LAIN)
#set($txtPBDaftar = $list_siasatan.TUNTUTAN_PB_BEBANAN)
#set($txtPBXDaftar = $list_siasatan.TUNTUTAN_PB_TDKDAFTAR)
#set($txtStatus = $list_siasatan.STATUS_TUNTUTAN)
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
#parse("app/ppt/paging_penarikanbalik.jsp")
<table width="100%">
  
   <tr>
    <td> #parse("app/ppt/header_pampasan_penarikanbalik.jsp")</td>
  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
     
       <li class="TabbedPanelsTab" tabindex="0" id="senarai" onClick="kembali_senarai('$id_permohonan',' $id_pembatalan')">Senarai PB</li>
          <li class="TabbedPanelsTab" tabindex="0" id="tuntutan" onClick="Tuntutan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')">Tuntutan</li>
       <li class="TabbedPanelsTab" tabindex="0"  id="Nilaian"  onClick="Nilaian('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Nilaian / Maklumat Pampasan</li> 
        <li class="TabbedPanelsTab" tabindex="0" id="Penerimaan_Cek" onClick="Penerimaan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Penerimaan Cek</li>
        <li class="TabbedPanelsTab" tabindex="0"  id="Penyerahan_Cek" onClick="Penyerahan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Penyerahan Cek</li>
        <li class="TabbedPanelsTab" tabindex="0"  id="Bayaran_Melalui_EFT" onClick="EFT('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Bayaran Melalui EFT</li>
      
      </ul>
      
      <div class="TabbedPanelsContentGroup">
      <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent">
   <fieldset>
   <table width="100%">
  <tr>
    <td  valign="top"><table width="100%">
      <tr>
        <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%" valign="top">Tuntutan Tuan Tanah</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtTuntutanTuanTanah" id="txtTuntutanTuanTanah" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtTuntutanTuanTanah_check','yes','tuntutan tuan tanah','normal')"  onKeyUp="checking_validation(this,'txtTuntutanTuanTanah_check','yes','tuntutan tuan tanah','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtTuntutanTuanTanah</textarea>     
         <div id="txtTuntutanTuanTanah_check" style="color:red" > </div>    
         -->
         
         
           <textarea name="txtTuntutanTuanTanah" id="txtTuntutanTuanTanah" cols="60"   rows="6" style="text-transform:uppercase;"         
         onBlur="this.value=this.value.toUpperCase();check_length(this,'400','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','yes','tuntutan tuan tanah');"  
         onKeyup="check_length(this,'400','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','yes','tuntutan tuan tanah');" 
         onKeydown="check_length(this,'400','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','yes','tuntutan tuan tanah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtTuntutanTuanTanah</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtTuntutanTuanTanah_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtTuntutanTuanTanah_num" id="txtTuntutanTuanTanah_num" size="3" value="400"  style=" display:none" > 
         #end
  <div id="txtTuntutanTuanTanah_check" class="alert_msg" ></div> 
         
            </td>
      </tr>
      <tr>
        <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%" valign="top">Lain-Lain Tuntutan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtLainTuntutan" id="txtLainTuntutan" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtLainTuntutan_check','yes','lain-lain tuntutan','normal')"  onKeyUp="checking_validation(this,'txtLainTuntutan_check','yes','lain-lain tuntutan','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtLainTuntutan</textarea>     
         <div id="txtLainTuntutan_check" style="color:red" > </div>     
         
         -->
         
          <textarea name="txtLainTuntutan" id="txtLainTuntutan" cols="60"   rows="6" style="text-transform:uppercase;"         
         onBlur="this.value=this.value.toUpperCase();check_length(this,'400','txtLainTuntutan_check','txtLainTuntutan_num','normal','yes','lain-lain tuntutan');"  
         onKeyup="check_length(this,'400','txtLainTuntutan_check','txtLainTuntutan_num','normal','yes','lain-lain tuntutan');" 
         onKeydown="check_length(this,'400','txtLainTuntutan_check','txtLainTuntutan_num','normal','yes','lain-lain tuntutan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtLainTuntutan</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtLainTuntutan_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtLainTuntutan_num" id="txtLainTuntutan_num" size="3" value="400"  style=" display:none" > 
         #end
  <div id="txtLainTuntutan_check" class="alert_msg" ></div> 
         
           </td>
      </tr>
      
      
      
      <tr>
        <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%" valign="top">Pihak Berkepentingan Berdaftar</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtPBDaftar" id="txtPBDaftar" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtPBDaftar_check','yes','pihak berkepentingan berdaftar','normal')"  onKeyUp="checking_validation(this,'txtKeteranganAgensi_check','yes','pihak berkepentingan berdaftar','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtPBDaftar</textarea>     
         <div id="txtPBDaftar_check" style="color:red" > </div>   -->
         
              <textarea name="txtPBDaftar" id="txtPBDaftar" cols="60"   rows="6" style="text-transform:uppercase;"         
         onBlur="this.value=this.value.toUpperCase();check_length(this,'400','txtPBDaftar_check','txtPBDaftar_num','normal','yes','pihak berkepentingan berdaftar');"  
         onKeyup="check_length(this,'400','txtPBDaftar_check','txtPBDaftar_num','normal','yes','pihak berkepentingan berdaftar');" 
         onKeydown="check_length(this,'400','txtPBDaftar_check','txtPBDaftar_num','normal','yes','pihak berkepentingan berdaftar');"                    
          $readonlymode class = "$disabledmode" 
        >$txtPBDaftar</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtPBDaftar_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtPBDaftar_num" id="txtPBDaftar_num" size="3" value="400"  style=" display:none" > 
         #end
  <div id="txtPBDaftar_check" class="alert_msg" ></div> 
         
             </td>
      </tr>
      <tr>
        <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%" valign="top">Pihak Berkepentingan Tidak Berdaftar</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtPBXDaftar" id="txtPBXDaftar" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtPBXDaftar_check','yes','pihak berkepentingan tidak berdaftar','normal')"  onKeyUp="checking_validation(this,'txtPBXDaftar_check','yes','pihak berkepentingan tidak berdaftar','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtPBXDaftar</textarea>     
         <div id="txtPBXDaftar_check" style="color:red" > </div> -->
         
         
            <textarea name="txtPBXDaftar" id="txtPBXDaftar" cols="60"   rows="6" style="text-transform:uppercase;"         
         onBlur="this.value=this.value.toUpperCase();check_length(this,'400','txtPBXDaftar_check','txtPBXDaftar_num','normal','yes','pihak berkepentingan tidak berdaftar');"  
         onKeyup="check_length(this,'400','txtPBXDaftar_check','txtPBXDaftar_num','normal','yes','pihak berkepentingan tidak berdaftar');" 
         onKeydown="check_length(this,'400','txtPBXDaftar_check','txtPBXDaftar_num','normal','yes','pihak berkepentingan tidak berdaftar');"                    
          $readonlymode class = "$disabledmode" 
        >$txtPBXDaftar</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtPBXDaftar_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtPBXDaftar_num" id="txtPBXDaftar_num" size="3" value="400"  style=" display:none" > 
         #end
  <div id="txtPBXDaftar_check" class="alert_msg" ></div> 
         
               </td>
      </tr>
      <tr>
              <td >#parse("app/ppt/mandatory_pembatalan.jsp")</td>
              <td >Status Tuntutan</td>
              <td >:</td>
              <td >
              
              #if($readmode == "view" )       
              #if($txtStatus == "1")
              #set($keputusan = "DITERIMA")            
              #elseif($txtStatus == "2")
              #set($keputusan = "DITOLAK")              
              #else
              #set($keputusan = "")
              #end
              <input name="keputusan" type="text" id="keputusan" value="$keputusan" $check1  readonly class = "disabled">              
              #else
              <table width="100%">
              <tr>
              <td width="30%" >                    
              #if($txtStatus == "1")
              #set($check1 = "checked")
              #set($check2 = "")             
              #elseif($txtStatus == "2")
              #set($check2 = "checked")
              #set($check1 = "")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #end 
<input type="radio" name="txtStatus" id="txtStatus" value="1" $check1 onClick="checking_validation(this,'txtStatus_check','yes','status tuntutan','radio');">
Diterima</td>
<td width="70%">
 <input type="radio" name="txtStatus" id="txtStatus" value="2" $check2 onClick="checking_validation(this,'txtStatus_check','yes','status tuntutan','radio');">
 Ditolak   &nbsp;  <span id="txtStatus_check"  class="alert_msg" ></span> </td>                 
                </tr>
                </table>
          #end
        
                   
               </td>
            </tr>
      
    </table></td>
  </tr>
  
  
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini('$id_siasatan')">
      </label>
      <label>
      <!--
       <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus('$id_siasatan')">
      -->
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan('$id_siasatan')">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_siasatan')">
      </label>
    #end  
     
       #if($id_pembatalan != "")
        <label></label>
       <!--
        <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
      </label> 
      -->  
      #end
       <label></label>
     </div>      </td>
  </tr>
</table>

   </fieldset>
   </div>
         <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent"></div>
      </div>
    </div></td>
</tr>
</table>


<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue">Surat Kepada Agensi Pemohon - Tuntutan Penarikan Balik</font></a></td>
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
  <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" />
  <input type="hidden" name="id_tanahumum" id="id_tanahumum" value="$!id_tanahumum" />
  <input type="hidden" name="id_siasatan" id="id_siasatan" value="$!id_siasatan" />
   <input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb" />
  
  
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
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
window.location.hash='paging';
goTo('paging');
}


check_length(document.${formName}.txtTuntutanTuanTanah,'400','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','yes','tuntutan tuan tanah');
check_length(document.${formName}.txtLainTuntutan,'400','txtLainTuntutan_check','txtLainTuntutan_num','normal','yes','lain-lain tuntutan');
check_length(document.${formName}.txtPBDaftar,'400','txtPBDaftar_check','txtPBDaftar_num','normal','yes','pihak berkepentingan berdaftar');
check_length(document.${formName}.txtPBXDaftar,'400','txtPBXDaftar_check','txtPBXDaftar_num','normal','yes','pihak berkepentingan tidak berdaftar');
checking_validation(document.${formName}.txtStatus,'txtStatus_check','yes','status tuntutan','radio');

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
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+"");	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
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
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");		
	   }
	   else
	   {/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pilih "+value_field+"");		
	   	
	   }
	   else
	   {
/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
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




function simpan(id_siasatan)
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
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "tuntutan";
	document.${formName}.point.value = "tuan_tanah";	
	document.${formName}.submit();
	}
}
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "tuntutan";
	document.${formName}.point.value = "txtTuntutanTuanTanah";
	document.${formName}.submit();
	
}


function batal(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "tuntutan";
	document.${formName}.point.value = "txtTuntutanTuanTanah";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}


function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "tuntutan";
	document.${formName}.point.value = "txtTuntutanTuanTanah";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
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

function Tuntutan(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function Nilaian(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function Penerimaan(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Penerimaan_Check";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}


function Penyerahan(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Penyerahan_Check";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function EFT(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function kembali_senarai(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
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

function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");
}
</script>
