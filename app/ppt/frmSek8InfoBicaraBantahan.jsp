<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>



#set($txtBantahanLain = "")
#set($txtBantahanAgensi = "")
#set($txtBantahanTuanTanah = "")


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
     #foreach($list_siasatan in $maklumat_siasatan_history)
#set($txtBantahanLain = $list_siasatan.BANTAHAN_LAIN)
#set($txtBantahanAgensi = $list_siasatan.BANTAHAN_AGENSI)
#set($txtBantahanTuanTanah = $list_siasatan.BANTAHAN_TUANTNH)
#end
  <tr>
<td>#parse("app/ppt/header_1_ppt.jsp") </td>
  </tr>
  <tr>
<td>#parse("app/ppt/header_siasatan.jsp") </td>



  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <!--  <li class="TabbedPanelsTab" tabindex="0"  onClick="screen5('$id_permohonan')">Kembali</li> -->
 	<!-- PPT-19 --> 
     <li class="TabbedPanelsTab" tabindex="0"  onClick="popupCarianHakmilik('$id_permohonan','senarai_siasatan')">Kembali</li>
    
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuantanah('$id_siasatan')" id="tuan_tanah" >Keterangan Tuan Tanah</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="agensi('$id_siasatan')"  id="Agensi" >Agensi / Jurunilai</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuntutan('$id_siasatan')">Tuntutan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="bantahan('$id_siasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="nilaian('$id_siasatan')"  >Nilaian JPPH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="PB('$id_siasatan')"  >Pihak Berkepentingan
    
    <!-- <font style="cursor:help" title="info"  align="center" class="font2" onMouseOut="close_window()"   onMouseOver ="open_new_window('3');this.style.cursor='help'" >
  #parse("app/ppt/infoblink_biru.jsp")
       </font> -->
     
       
    </li>
      <li class="TabbedPanelsTab" tabindex="0" onClick="status('$id_siasatan')"  >Status Siasatan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="keputusan('$id_siasatan')" id="perintah_keputusan">Perintah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
   <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
     
    <div class="TabbedPanelsContent">
   <fieldset>
   <table width="100%">
  <tr>
    <td  valign="top"><table width="100%">
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Tuan Tanah</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtBantahanTuanTanah" id="txtBantahanTuanTanah" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtBantahanTuanTanah_check','yes','bantahan tuan tanah','normal')"  onKeyUp="checking_validation(this,'txtBantahanTuanTanah_check','yes','bantahan tuan tanah','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtBantahanTuanTanah</textarea>     
         <div id="txtBantahanTuanTanah_check" style="color:red" > </div>-->
         
          
           <textarea name="txtBantahanTuanTanah" id="txtBantahanTuanTanah" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtBantahanTuanTanah_check','txtBantahanTuanTanah_num','normal','no','bantahan tuan tanah');"  
         onKeyup="check_length(this,'4000','txtBantahanTuanTanah_check','txtBantahanTuanTanah_num','normal','no','bantahan tuan tanah');" 
         onKeydown="check_length(this,'4000','txtBantahanTuanTanah_check','txtBantahanTuanTanah_num','normal','no','bantahan tuan tanah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtBantahanTuanTanah</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtBantahanTuanTanah_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtBantahanTuanTanah_num" id="txtBantahanTuanTanah_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtBantahanTuanTanah_check" class="alert_msg" ></div> 
                </td>
      </tr>
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Agensi Pemohon</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtBantahanAgensi" id="txtBantahanAgensi" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtBantahanAgensi_check','yes','bantahan agensi','normal')"  onKeyUp="checking_validation(this,'txtBantahanAgensi_check','yes','bantahan agensi','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtBantahanAgensi</textarea>     
         <div id="txtBantahanAgensi_check" style="color:red" > </div> -->
         
          
           <textarea name="txtBantahanAgensi" id="txtBantahanAgensi" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtBantahanAgensi_check','txtBantahanAgensi_num','normal','no','bantahan agensi');"  
         onKeyup="check_length(this,'4000','txtBantahanAgensi_check','txtBantahanAgensi_num','normal','no','bantahan agensi');" 
         onKeydown="check_length(this,'4000','txtBantahanAgensi_check','txtBantahanAgensi_num','normal','no','bantahan agensi');"                    
          $readonlymode class = "$disabledmode" 
        >$txtBantahanAgensi</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtBantahanAgensi_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtBantahanAgensi_num" id="txtBantahanAgensi_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtBantahanAgensi_check" class="alert_msg" ></div> 
               </td>
      </tr>
      
      
      
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Lain-Lain Bantahan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtBantahanLain" id="txtBantahanLain" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtBantahanLain_check','yes','lain-lain bantahan','normal')"  onKeyUp="checking_validation(this,'txtBantahanLain_check','yes','lain-lain bantahan','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtBantahanLain</textarea>     
         <div id="txtBantahanLain_check" style="color:red" > </div> -->
         
         <textarea name="txtBantahanLain" id="txtBantahanLain" cols="60"   rows="6"          
         onBlur="check_length(this,'4000','txtBantahanLain_check','txtBantahanLain_num','normal','no','lain-lain bantahan');"  
         onKeyup="check_length(this,'4000','txtBantahanLain_check','txtBantahanLain_num','normal','no','lain-lain bantahan');" 
         onKeydown="check_length(this,'4000','txtBantahanLain_check','txtBantahanLain_num','normal','no','lain-lain bantahan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtBantahanLain</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtBantahanLain_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtBantahanLain_num" id="txtBantahanLain_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtBantahanLain_check" class="alert_msg" ></div> 
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
      
       <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus('$id_siasatan')">
      
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
        #if($readmode == "view")
        <label></label>
       
        <label> 
     <!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  -->
      </label>    
      #end
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
        <td><a href="#" class="style2" onClick="nota('$id_siasatan')"><font color="blue">Nota Siasatan Tarik Balik</font></a></td>
      </tr>  
       
    </table>
</fieldset>

<!-- PPT-24 -->
<fieldset>
	<legend>SIASATAN</legend>
	 <table width="100%" border="0"> 
		<tr>
			<td width="1%"></td>
			<td colspan="3">
				<div align="left"><a href="javascript:papar('$id_siasatan','$id_hakmilik')" title="Memaparkan secara lengkap maklumat set siasatan"><font color="blue">MAKLUMAT SIASATAN</font></a></div>
			</td>
		</tr>
		
		<tr>
			<td width="1%"></td>
			<td colspan="3">
				<div align="left"><a href="javascript:kehadiran('$id_siasatan')" title="Memaparkan secara lengkap maklumat kehadiran"><font color="blue">MAKLUMAT KEHADIRAN</font></a></div>
			</td>
		</tr>

		<!-- tr>
			<td width="1%"></td>
			<td colspan="3">
				<div align="left"><a href="javascript:maklumatsiasatan('$id_siasatan')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">NOTA SIASATAN </font></a></div>
			</td>
		</tr-->
	</table>
</fieldset>
<!-- PPT-24 END -->


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
  
  
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:4});
var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function submitForm() 
{


if('$socStatusSiasatan' == "6" )
{
document.getElementById('perintah_keputusan').style.display="";  
}
else
{
document.getElementById('perintah_keputusan').style.display="none"; 
}

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

//checking_validation(document.${formName}.txtBantahanLain,'txtBantahanLain_check','yes','lain-lain bantahan','normal')
//checking_validation(document.${formName}.txtBantahanAgensi,'txtBantahanAgensi_check','yes','bantahan agensi','normal')
//checking_validation(document.${formName}.txtBantahanTuanTanah,'txtBantahanTuanTanah_check','yes','bantahan tuan tanah','normal');

check_length(document.${formName}.txtBantahanLain,'4000','txtBantahanTuanTanah_check','txtBantahanTuanTanah_num','normal','no','bantahan tuan tanah');
check_length(document.${formName}.txtBantahanAgensi,'4000','txtBantahanAgensi_check','txtBantahanAgensi_num','normal','no','bantahan agensi');
check_length(document.${formName}.txtBantahanTuanTanah,'4000','txtBantahanLain_check','txtBantahanLain_num','normal','no','lain-lain bantahan');

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
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Bantahan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "Agensi";	
	document.${formName}.submit();
	}
}
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Bantahan";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtBantahanTuanTanah";
	document.${formName}.submit();
	
}


function batal(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Bantahan";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtBantahanTuanTanah";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}


function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Bantahan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtBantahanTuanTanah";
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


function tuantanah(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function agensi(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Agensi";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function tuntutan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}


function bantahan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Bantahan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function nilaian(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function keputusan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
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

function status(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function nota(id_siasatan)
{
    var url = "../servlet/ekptg.report.ppt.NotaSiasatanPB?id_siasatan="+id_siasatan;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function screen5(id_permohonan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
}

function PB(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "PB";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function popupCarianHakmilik(id_permohonan,flag_skrin)
{  //PPT-19
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
	
	screen5(id_permohonan);
	
}

function maklumatsiasatan(id_siasatan)
{ //PPT-24

	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function kehadiran(id_siasatan)
{	//PPT-24 
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	document.${formName}.submit();
}

function papar(id_siasatan)
{ //PPT-24 
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();
	
}

</script>
  
