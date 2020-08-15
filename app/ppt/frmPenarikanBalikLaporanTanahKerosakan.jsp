<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {color: #FF0000}
.style3 {
	color: #FFFFFF;
	font-weight: bold;
}
.style5 {color: #0000FF; font-weight: bold; font-style: italic; }
-->
</style>




#parse("app/ppt/paging_penarikanbalik.jsp")


#set($id_tanahumum = "")
#set($txtKerosakanTanah = "")
#set($txtKeteranganKerosakan = "")
#set($txtKerosakanTanaman = "")
#set($txtKerosakanBangunan = "")
#set($txtKosDitanggung = "")


#set($txtnorujukan = "")
#set($txtnamanegeri = "")
#set($txtnamadaerah = "")
#set($txtnamajajahan = "")
#set($STATUS_LAPORAN = "")

#foreach($listmaklumat in $maklumat_hakmilik)
#set($id_hakmilik = $listmaklumat.ID_HAKMILIK)
#set($txtnorujukan = $listmaklumat.NO_PT)
#set($txtnamanegeri = $listmaklumat.NAMA_NEGERI)
#set($txtnamadaerah = $listmaklumat.NAMA_DAERAH)
#set($txtnamajajahan = $listmaklumat.NAMA_MUKIM)
#set($STATUS_LAPORAN = $listmaklumat.STATUS_LAPORAN)
#end



#foreach($maklumatam in $maklumat_am_tanah_permohonan)
#set($txtKerosakanTanah = $maklumatam.KEROSAKAN_TANAH)
#set($txtKeteranganKerosakan = $maklumatam.KETERANGAN_KEROSAKAN)
#set($txtKerosakanTanaman = $maklumatam.KEROSAKAN_TANAMAN)
#set($txtKerosakanBangunan = $maklumatam.KEROSAKAN_BANGUNAN)
#set($txtKosDitanggung = $maklumatam.KOS_DITANGGUNG)
#end



#foreach($maklumatam in $maklumat_am_tanah)
#set($id_tanahumum = $maklumatam.ID_TANAHUMUM)
#set($txtKerosakanTanah = $maklumatam.KEROSAKAN_TANAH)
#set($txtKeteranganKerosakan = $maklumatam.KETERANGAN_KEROSAKAN)
#set($txtKerosakanTanaman = $maklumatam.KEROSAKAN_TANAMAN)
#set($txtKerosakanBangunan = $maklumatam.KEROSAKAN_BANGUNAN)
#set($txtKosDitanggung = $maklumatam.KOS_DITANGGUNG)
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
    
     <fieldset>
    
    
    <table width="100%">
    
    <tr>
    <td>
   
    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0" onClick="screen2('$id_permohonan','$id_pembatalan')" >Senarai Lot Penarikan</li>
        <li class="TabbedPanelsTab" tabindex="0" id="maklumat_am" onClick="LaporanTanah('$!id_hakmilik','$!id_pembatalan')">Maklumat Am Tanah</li>
        <li class="TabbedPanelsTab" tabindex="0" onClick="PerihalTanah('$!id_hakmilik','$!id_pembatalan')">Perihal Tanah</li>
        <li class="TabbedPanelsTab" tabindex="0" onClick="LaporanKerosakan('$!id_hakmilik','$!id_pembatalan')">Laporan Kerosakan</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
       <div class="TabbedPanelsContent">
       </div>
       <div class="TabbedPanelsContent">
       </div>
        <div class="TabbedPanelsContent">     
        </div>
        <div class="TabbedPanelsContent"> 
        
        
        <table width="100%">
  <tr>
    <td>
    <fieldset>
    <table width="100%" >
    <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Lot</td>
    <td width="1%">:</td>
    <td width="70%">$txtnorujukan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$txtnamanegeri</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah/Jajahan</td>
    <td>:</td>
    <td>$txtnamadaerah</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bandar/Pekan/Mukim</td>
    <td>:</td>
    <td>$txtnamajajahan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Status Laporan Tanah</td>
    <td>:</td>
    <td>$STATUS_LAPORAN</td>
  </tr>
    <tr>
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Kerosakan Tanah</td>
              <td width="1%" valign="top">:</td>
              <td width="70%">   
              <!--           
               <textarea name="txtKerosakanTanah" id="txtKerosakanTanah" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKerosakanTanah_check','yes','lokasi tanah','normal')"  onKeyUp="checking_validation(this,'txtKerosakanTanah_check','yes','lokasi tanah','normal')" $readonlymode class = "$disabledmode" >$txtKerosakanTanah</textarea>     
         <div id="txtKerosakanTanah_check" style="color:red" ></div>     
         -->
         
           <textarea name="txtKerosakanTanah" id="txtKerosakanTanah" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKerosakanTanah_check','txtKerosakanTanah_num','normal','no','lokasi tanah');"  
         onKeyup="check_length(this,'4000','txtKerosakanTanah_check','txtKerosakanTanah_num','normal','no','lokasi tanah');" 
         onKeydown="check_length(this,'4000','txtKerosakanTanah_check','txtKerosakanTanah_num','normal','no','lokasi tanah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKerosakanTanah</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKerosakanTanah_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKerosakanTanah_num" id="txtSebabPembatalan_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKerosakanTanah_check" class="alert_msg" ></div>                 </td>
            </tr>
          
          <!-- TAMBAH MEDAN KETERANGAN KEROSAKAN --> 
          <tr>
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Keterangan Kerosakan</td>
              <td width="1%" valign="top">:</td>
              <td width="70%">   
              <!--           
               <textarea name="txtKeteranganKerosakan" id="txtKeteranganKerosakan" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKeteranganKerosakan_check','yes','Keterangan Kerosakan','normal')"  onKeyUp="checking_validation(this,'txtKeteranganKerosakan_check','yes','lokasi tanah','normal')" $readonlymode class = "$disabledmode" >$txtKeteranganKerosakan</textarea>     
         <div id="txtKerosakanTanah_check" style="color:red" ></div>     
         -->
         
           <textarea name="txtKeteranganKerosakan" id="txtKeteranganKerosakan" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKeteranganKerosakan_check','txtKeteranganKerosakan_num','normal','no','Keterangan Kerosakan');"  
         onKeyup="check_length(this,'4000','txtKeteranganKerosakan_check','txtKeteranganKerosakan_num','normal','no','Keterangan Kerosakan');" 
         onKeydown="check_length(this,'4000','txtKeteranganKerosakan_check','txtKeteranganKerosakan_num','normal','no','Keterangan Kerosakan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganKerosakan</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKeteranganKerosakan_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeteranganKerosakan_num" id="txtKeteranganKerosakan_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKeteranganKerosakan_check" class="alert_msg" ></div>                 </td>
            </tr>
                       
            <tr>
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Kerosakan Tanaman</td>
              <td width="1%" valign="top">:</td>
              <td width="70%"> 
              <!--             
               <textarea name="txtKerosakanTanaman" id="txtKerosakanTanaman" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKerosakanTanaman_check','yes','keadaan lot','normal')"  onKeyUp="checking_validation(this,'txtKerosakanTanaman_check','yes','keadaan lot','normal')" $readonlymode class = "$disabledmode" >$txtKerosakanTanaman</textarea>     
         <div id="txtKerosakanTanaman_check" style="color:red" ></div>       
         -->
         
            <textarea name="txtKerosakanTanaman" id="txtKerosakanTanaman" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKerosakanTanaman_check','txtKerosakanTanaman_num','normal','no','keadaan lot');"  
         onKeyup="check_length(this,'4000','txtKerosakanTanaman_check','txtKerosakanTanaman_num','normal','no','keadaan lot');" 
         onKeydown="check_length(this,'4000','txtKerosakanTanaman_check','txtKerosakanTanaman_num','normal','no','keadaan lot');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKerosakanTanaman</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKerosakanTanaman_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKerosakanTanaman_num" id="txtKerosakanTanaman_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKerosakanTanaman_check" class="alert_msg" ></div>                </td>
            </tr>
            <tr>
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Kerosakan Bangunan</td>
              <td width="1%" valign="top">:</td>
              <td width="70%">  
              
              <!--            
               <textarea name="txtKerosakanBangunan" id="txtKerosakanBangunan" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKerosakanBangunan_check','yes','jenis tanaman','normal')"  onKeyUp="checking_validation(this,'txtKerosakanBangunan_check','yes','jenis tanaman','normal')" $readonlymode class = "$disabledmode" >$txtKerosakanBangunan</textarea>     
         <div id="txtKerosakanBangunan_check" style="color:red" ></div>      
         -->
         
          <textarea name="txtKerosakanBangunan" id="txtKerosakanBangunan" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKerosakanBangunan_check','txtKerosakanBangunan_num','normal','no','jenis tanaman');"  
         onKeyup="check_length(this,'4000','txtKerosakanBangunan_check','txtKerosakanBangunan_num','normal','no','jenis tanaman');" 
         onKeydown="check_length(this,'4000','txtKerosakanBangunan_check','txtKerosakanBangunan_num','normal','no','jenis tanaman');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKerosakanBangunan</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKerosakanBangunan_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKerosakanBangunan_num" id="txtKerosakanBangunan_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKerosakanBangunan_check" class="alert_msg" ></div>                 </td>
            </tr>
            <tr>
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Kos - Kos Yang Ditanggung Beban</td>
              <td width="1%" valign="top">:</td>
              <td width="70%"> 
              <!--             
               <textarea name="txtKosDitanggung" id="txtKosDitanggung" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKosDitanggung_check','yes','maklumat berhampiran','normal')"  onKeyUp="checking_validation(this,'txtKosDitanggung_check','yes','maklumat berhampiran','normal')" $readonlymode class = "$disabledmode" >$txtKosDitanggung</textarea>     
         <div id="txtKosDitanggung_check" style="color:red" ></div>   -->
         
          <textarea name="txtKosDitanggung" id="txtKosDitanggung" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKosDitanggung_check','txtKosDitanggung_num','normal','no','maklumat berhampiran');"  
         onKeyup="check_length(this,'4000','txtKosDitanggung_check','txtKosDitanggung_num','normal','no','maklumat berhampiran');" 
         onKeydown="check_length(this,'4000','txtKosDitanggung_check','txtKosDitanggung_num','normal','no','maklumat berhampiran');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKosDitanggung</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKosDitanggung_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKosDitanggung_num" id="txtKosDitanggung_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKosDitanggung_check" class="alert_msg" ></div>                    </td>
            </tr>
    </table>
    </fieldset>    </td>
  </tr>
  <tr>
    <td>    </td>
  </tr>
</table>

        
        
</div>
        <div class="TabbedPanelsContent"></div>
      </div>
    </div>
    
  
    </td>
    </tr>
    <tr>
    <td colspan="2">
    <div align="center" >
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
         #if($readmode == "view")
           #if($STATUS_LAPORAN != "SELESAI")
          <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Selesai Laporan Tanah"  onClick="selesai()">
      </label> 
      #end    
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport1')" />  
      </label>   
      #end 
      #end    
        </div>
        
              </td>
  </tr>
  
  #if($readmode == "edit")
  <tr>
    <td>#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td></td>
  </tr>  
  #end
  
  
    </table>
    </fieldset>
    </td>
  </tr>
  
</table>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="laporantanah('$id_tanahumum','$user_name')"><font color="blue">Laporan Tanah</font></a></td>
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
 
  <script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:3});

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

check_length(document.${formName}.txtKerosakanTanah,'4000','txtKerosakanTanah_check','txtKerosakanTanah_num','normal','no','lokasi tanah');
check_length(document.${formName}.txtKeteranganKerosakan,'4000','txtKeteranganKerosakan_check','txtKeteranganKerosakan_num','normal','no','keterangan kerosakan');
check_length(document.${formName}.txtKerosakanTanaman,'4000','txtKerosakanTanaman_check','txtKerosakanTanaman_num','normal','no','keadaan lot');
check_length(document.${formName}.txtKerosakanBangunan,'4000','txtKerosakanBangunan_check','txtKerosakanBangunan_num','normal','no','jenis tanaman');
check_length(document.${formName}.txtKosDitanggung,'4000','txtKosDitanggung_check','txtKosDitanggung_num','normal','no','maklumat berhampiran');

/*
checking_validation(document.${formName}.txtKerosakanTanah,'txtKerosakanTanah_check','yes','lokasi tanah','normal')
checking_validation(document.${formName}.txtKerosakanTanaman,'txtKerosakanTanaman_check','yes','keadaan lot','normal')
checking_validation(document.${formName}.txtKerosakanBangunan,'txtKerosakanBangunan_check','yes','jenis tanaman','normal')
checking_validation(document.${formName}.txtKosDitanggung,'txtKosDitanggung_check','yes','maklumat berhampiran','normal')
checking_validation(document.${formName}.txtUlasanKeseluruhan,'txtUlasanKeseluruhan_check','yes','keadaan tanaman','normal')
checking_validation(document.${formName}.txtUlasan,'txtUlasan_check','yes','ulasan / pandangan','normal')
checking_validation(document.${formName}.txtKawasan,'txtKawasan_check','yes','maklumat kawasan yang terlibat dengan pengambilan','normal')
checking_validation(document.${formName}.txtKeseluruhanLot,'txtKeseluruhanLot_check','yes','maklumat keseluruhan lot','normal')
*/
}



function doCheckAll1(){    
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
}
function doUpdateCheckAll1(){  
var c = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
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




function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}



function LaporanTanah(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}


function PerihalTanah(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Perihal_Tanah";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
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
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Laporan_Kerosakan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
	}
	}
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Laporan_Kerosakan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
	}
}


function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Laporan_Kerosakan";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();

	}
}

function kemaskini()
{
    document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Laporan_Kerosakan";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "txtKerosakanTanah";
	document.${formName}.submit();


}


function ShowPopup(hoveritem,tab)
{
//alert("hp.style.display :"+hp.style.display+";hp.style.visibility :"+hp.style.visibility);
hp = document.getElementById(tab);
//hp.style.display=="none" &&
	if( hp.style.visibility=="collapse" && hp.style.display=="none"){
		hp.style.display = "block";
		hp.style.visibility = "visible";
	}
	//hp.style.display=="block" &&
	else if( hp.style.visibility=="visible" && hp.style.display=="block"){
		hp.style.display = "none";
		hp.style.visibility = "collapse";
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
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
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




function selesai()
{
   document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Laporan_Kerosakan";
	document.${formName}.subminor_command.value = "UpdateSuburusan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
}

	


function laporantanah(id_tanah,nama_pegawai)
{


var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_tanah="+id_tanah+"&report=laporan_tanah&id_permohonan="+document.${formName}.id_permohonan.value;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}

function open_new_window(jenis_popup) 
{
var width  = 300;
 var height = 200;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
//new_window = open("","hoverwindow",params);
new_window = open("","title",params);

new_window.document.open();

new_window.document.write("<html><title>JavaScript New Window</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
if(jenis_popup == "1")
{
new_window.document.write("Sila masukkan 'Tiada' jika tiada maklumat.");
}/*
if(jenis_popup == "2")
{
new_window.document.write("Sila semak untuk memilih penarikan balik bagi lot ini,");
new_window.document.write("dan sila masukkan jumlah luas lust lot yang hendak ");
new_window.document.write("ditarik balik.");

}

if(jenis_popup == "3")
{
new_window.document.write("Sila pilih jenis luas yang lain jika unit luas yang dikehendaki adalah tidak sama dengan unit luas yang ditetapkan.");
new_window.document.write("Seterusnya sila masukkan luas lot awal, pengiraan luas pertukaran akan berlaku secara automatik selepas kotak terakhir selesai diisi.");


}*/

new_window.document.write("<br>");
new_window.document.write("</body></html>");
new_window.document.close(); 







}

function LaporanKerosakan(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Laporan_Kerosakan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}


function close_window() 
{
new_window.close();
}
  </script>
