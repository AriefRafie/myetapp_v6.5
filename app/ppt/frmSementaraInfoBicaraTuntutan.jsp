<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>




#set($txtTuntutanTuanTanah = "")
#set($txtLainTuntutan = "")
#set($txtPBDaftar = "")
#set($txtPBXDaftar = "")

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
<td>#parse("app/ppt/header_1_ppt.jsp") </td>
  </tr>
  <tr>
<td>#parse("app/ppt/header_rundingan.jsp") </td>
  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0"  onClick="screen5('$id_permohonan','$id_pembatalan')">Kembali</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuantanah('$id_siasatan')" id="tuan_tanah"  >Keterangan Tuan Tanah</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="agensi('$id_siasatan')" id="Agensi" style="display:none" >Agensi / Jurunilai</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuntutan('$id_siasatan')">Tuntutan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="bantahan('$id_siasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="nilaian('$id_siasatan')"  >Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="status('$id_siasatan')" >Status Perundingan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="keputusan('$id_siasatan')" id="perintah_keputusan">Perintah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
   <fieldset>
   <table width="100%">
   #foreach($list_siasatan in $maklumat_siasatan_history)
#set($txtTuntutanTuanTanah = $list_siasatan.TUNTUTAN_TUANTNH)
#set($txtLainTuntutan = $list_siasatan.TUNTUTAN_PB_LAIN)
#set($txtPBDaftar = $list_siasatan.TUNTUTAN_PB_BEBANAN)
#set($txtPBXDaftar = $list_siasatan.TUNTUTAN_PB_TDKDAFTAR)
#end
  <tr>
    <td  valign="top"><table width="100%">
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Tuntutan Tuan Tanah</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtTuntutanTuanTanah" id="txtTuntutanTuanTanah" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtTuntutanTuanTanah_check','yes','tuntutan tuan tanah','normal')"  onKeyUp="checking_validation(this,'txtTuntutanTuanTanah_check','yes','tuntutan tuan tanah','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtTuntutanTuanTanah</textarea>     
         <div id="txtTuntutanTuanTanah_check" style="color:red" > </div>    
         -->
         
         
           <textarea name="txtTuntutanTuanTanah" id="txtTuntutanTuanTanah" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','no','tuntutan tuan tanah');"  
         onKeyup="check_length(this,'4000','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','no','tuntutan tuan tanah');" 
         onKeydown="check_length(this,'4000','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','no','tuntutan tuan tanah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtTuntutanTuanTanah</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtTuntutanTuanTanah_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtTuntutanTuanTanah_num" id="txtTuntutanTuanTanah_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtTuntutanTuanTanah_check" class="alert_msg" ></div>            </td>
      </tr>
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Lain-Lain Tuntutan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtLainTuntutan" id="txtLainTuntutan" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtLainTuntutan_check','yes','lain-lain tuntutan','normal')"  onKeyUp="checking_validation(this,'txtLainTuntutan_check','yes','lain-lain tuntutan','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtLainTuntutan</textarea>     
         <div id="txtLainTuntutan_check" style="color:red" > </div>     
         
         -->
         
          <textarea name="txtLainTuntutan" id="txtLainTuntutan" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtLainTuntutan_check','txtLainTuntutan_num','normal','no','lain-lain tuntutan');"  
         onKeyup="check_length(this,'4000','txtLainTuntutan_check','txtLainTuntutan_num','normal','no','lain-lain tuntutan');" 
         onKeydown="check_length(this,'4000','txtLainTuntutan_check','txtLainTuntutan_num','normal','no','lain-lain tuntutan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtLainTuntutan</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtLainTuntutan_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtLainTuntutan_num" id="txtLainTuntutan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtLainTuntutan_check" class="alert_msg" ></div>           </td>
      </tr>
      
      
      
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Penduduk, Penyewa dan Pemajakan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtPBDaftar" id="txtPBDaftar" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtPBDaftar_check','yes','pihak berkepentingan berdaftar','normal')"  onKeyUp="checking_validation(this,'txtKeteranganAgensi_check','yes','pihak berkepentingan berdaftar','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtPBDaftar</textarea>     
         <div id="txtPBDaftar_check" style="color:red" > </div>   -->
         
              <textarea name="txtPBDaftar" id="txtPBDaftar" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtPBDaftar_check','txtPBDaftar_num','normal','no','pihak berkepentingan berdaftar');"  
         onKeyup="check_length(this,'4000','txtPBDaftar_check','txtPBDaftar_num','normal','no','pihak berkepentingan berdaftar');" 
         onKeydown="check_length(this,'4000','txtPBDaftar_check','txtPBDaftar_num','normal','no','pihak berkepentingan berdaftar');"                    
          $readonlymode class = "$disabledmode" 
        >$txtPBDaftar</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtPBDaftar_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtPBDaftar_num" id="txtPBDaftar_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtPBDaftar_check" class="alert_msg" ></div>             </td>
      </tr>
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Pemegang Gadaian,  Pemasuk Kaveat dan Pemegang Lien</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtPBXDaftar" id="txtPBXDaftar" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtPBXDaftar_check','yes','pihak berkepentingan tidak berdaftar','normal')"  onKeyUp="checking_validation(this,'txtPBXDaftar_check','yes','pihak berkepentingan tidak berdaftar','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtPBXDaftar</textarea>     
         <div id="txtPBXDaftar_check" style="color:red" > </div> -->
         
         
            <textarea name="txtPBXDaftar" id="txtPBXDaftar" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtPBXDaftar_check','txtPBXDaftar_num','normal','no','pihak berkepentingan tidak berdaftar');"  
         onKeyup="check_length(this,'4000','txtPBXDaftar_check','txtPBXDaftar_num','normal','no','pihak berkepentingan tidak berdaftar');" 
         onKeydown="check_length(this,'4000','txtPBXDaftar_check','txtPBXDaftar_num','normal','no','pihak berkepentingan tidak berdaftar');"                    
          $readonlymode class = "$disabledmode" 
        >$txtPBXDaftar</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtPBXDaftar_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtPBXDaftar_num" id="txtPBXDaftar_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtPBXDaftar_check" class="alert_msg" ></div>               </td>
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
    <!--  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  --> 
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
  </div>
</div></td>
</tr>
<tr>
  <td>&nbsp;</td>
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
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:3});
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


//checking_validation(document.${formName}.txtTuntutanTuanTanah,'txtTuntutanTuanTanah_check','yes','tuntutan tuan tanah','normal');
//checking_validation(document.${formName}.txtLainTuntutan,'txtLainTuntutan_check','yes','lain-lain tuntutan','normal');
//checking_validation(document.${formName}.txtPBDaftar,'txtPBDaftar_check','yes','pihak berkepentingan berdaftar','normal');
//checking_validation(document.${formName}.txtPBXDaftar,'txtPBXDaftar_check','yes','pihak berkepentingan tidak berdaftar','normal');

check_length(document.${formName}.txtTuntutanTuanTanah,'4000','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','no','tuntutan tuan tanah');
check_length(document.${formName}.txtLainTuntutan,'4000','txtLainTuntutan_check','txtLainTuntutan_num','normal','no','lain-lain tuntutan');
check_length(document.${formName}.txtPBDaftar,'4000','txtPBDaftar_check','txtPBDaftar_num','normal','no','pihak berkepentingan berdaftar');
check_length(document.${formName}.txtPBXDaftar,'4000','txtPBXDaftar_check','txtPBXDaftar_num','normal','no','pihak berkepentingan tidak berdaftar');

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
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "Agensi";
	document.${formName}.point.value = "Agensi";	
	document.${formName}.submit();
	}
}
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "Agensi";
	document.${formName}.point.value = "txtTuntutanTuanTanah";
	document.${formName}.submit();
	
}


function batal(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "Agensi";
	document.${formName}.point.value = "txtTuntutanTuanTanah";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}


function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "Agensi";
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


function tuantanah(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}


function status(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
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


function nota(id_siasatan)
{
    var url = "../servlet/ekptg.report.ppt.NotaSiasatanPB?id_siasatan="+id_siasatan;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}
function screen5(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.submit();
}

</script>
  
