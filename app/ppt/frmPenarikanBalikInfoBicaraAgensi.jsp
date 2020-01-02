<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>




#set($txtKeteranganAgensi = "")
#set($txtKeteranganJurunilai = "")

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
<td>#parse("app/ppt/header_ppt.jsp") </td>
  </tr>
  <tr>
<td>#parse("app/ppt/header_penarikanbalik_siasatan.jsp") </td>



  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0"  onClick="screen5('$id_permohonan','$id_pembatalan')">Kembali</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuantanah('$id_siasatan')" id="tuan_tanah" style="display:none">Keterangan Tuan Tanah</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="agensi('$id_siasatan')" id="Agensi" >Agensi / Jurunilai</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuntutan('$id_siasatan')">Tuntutan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="bantahan('$id_siasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="nilaian('$id_siasatan')"  >Kerosakan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="status('$id_siasatan')">Status Siasatan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="keputusan('$id_siasatan')" id="perintah_keputusan">Perintah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
   <fieldset>
   <table width="100%">
   
 

#foreach($list_siasatan in $maklumat_siasatan_history)
#set($txtKeteranganAgensi = $list_siasatan.KETERANGAN_AGENSI)
#set($txtKeteranganJurunilai = $list_siasatan.KETERANGAN_JURUNILAI)
#set($txtKeteranganJPPH = $list_siasatan.KETERANGAN_JPPH)
#set($txtKeteranganAkujanji = $list_siasatan.AKUJANJI_AGENSI)
#end


   
  <tr>
    <td  valign="top"><table width="100%">
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Keterangan Agensi</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtKeteranganAgensi" id="txtKeteranganAgensi" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKeteranganAgensi_check','yes','keterangan agensi','normal')"  onKeyUp="checking_validation(this,'txtKeteranganAgensi_check','yes','keterangan agensi','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtKeteranganAgensi</textarea>     
         <div id="txtKeteranganAgensi_check" style="color:red" > </div>    
         -->
         
           <textarea name="txtKeteranganAgensi" id="txtKeteranganAgensi" cols="60"   rows="6"          
         onBlur="check_length(this,'4000','txtKeteranganAgensi_check','txtKeteranganAgensi_num','normal','no','keterangan agensi');"  
         onKeyup="check_length(this,'4000','txtKeteranganAgensi_check','txtKeteranganAgensi_num','normal','no','keterangan agensi');" 
         onKeydown="check_length(this,'4000','txtKeteranganAgensi_check','txtKeteranganAgensi_num','normal','no','keterangan agensi');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganAgensi</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganAgensi_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtKeteranganAgensi_num" id="txtKeteranganAgensi_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganAgensi_check" class="alert_msg" ></div> 
         
            </td>
      </tr>
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Keterangan Jurunilai</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtKeteranganJurunilai" id="txtKeteranganJurunilai" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKeteranganJurunilai_check','yes','keterangan jurunilai','normal')"  onKeyUp="checking_validation(this,'txtKeteranganJurunilai_check','yes','keterangan jurunilai','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtKeteranganJurunilai</textarea>     
         <div id="txtKeteranganJurunilai_check" style="color:red" > </div>-->
         
             <textarea name="txtKeteranganJurunilai" id="txtKeteranganJurunilai" cols="60"   rows="6"      
         onBlur="check_length(this,'4000','txtKeteranganJurunilai_check','txtKeteranganJurunilai_num','normal','no','keterangan jurunilai');"  
         onKeyup="check_length(this,'4000','txtKeteranganJurunilai_check','txtKeteranganJurunilai_num','normal','no','keterangan jurunilai');" 
         onKeydown="check_length(this,'4000','txtKeteranganJurunilai_check','txtKeteranganJurunilai_num','normal','no','keterangan jurunilai');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganJurunilai</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganJurunilai_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtKeteranganJurunilai_num" id="txtKeteranganAgensi_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganJurunilai_check" class="alert_msg" ></div> 
         
         
         
                </td>
      </tr>
      
      
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Keterangan JPPH</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
     
         
           <textarea name="txtKeteranganJPPH" id="txtKeteranganJPPH" cols="60"   rows="6"          
         onBlur="check_length(this,'4000','txtKeteranganJPPH_check','txtKeteranganJPPH_num','normal','no','keterangan JPPH');"  
         onKeyup="check_length(this,'4000','txtKeteranganJPPH_check','txtKeteranganJPPH_num','normal','no','keterangan JPPH');" 
         onKeydown="check_length(this,'4000','txtKeteranganJPPH_check','txtKeteranganJPPH_num','normal','no','keterangan JPPH');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganJPPH</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganJPPH_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtKeteranganJPPH_num" id="txtKeteranganJPPH_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganJPPH_check" class="alert_msg" ></div> 
         
            </td>
      </tr>
      
      
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Keterangan Akujanji Agensi Pemohon (Jika Berkaitan)</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
     
         
           <textarea name="txtKeteranganAkujanji" id="txtKeteranganAkujanji" cols="60"   rows="6"          
         onBlur="check_length(this,'4000','txtKeteranganAkujanji_check','txtKeteranganAkujanji_num','normal','no','keterangan Akujanji');"  
         onKeyup="check_length(this,'4000','txtKeteranganAkujanji_check','txtKeteranganAkujanji_num','normal','no','keterangan Akujanji');" 
         onKeydown="check_length(this,'4000','txtKeteranganAkujanji_check','txtKeteranganAkujanji_num','normal','no','keterangan Akujanji');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganAkujanji</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganAkujanji_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtKeteranganAkujanji_num" id="txtKeteranganAkujanji_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganAkujanji_check" class="alert_msg" ></div> 
         
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
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
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
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
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



//checking_validation(document.${formName}.txtKeteranganAgensi,'txtKeteranganAgensi_check','yes','keterangan agensi','normal');
//checking_validation(document.${formName}.txtKeteranganJurunilai,'txtKeteranganJurunilai_check','yes','keterangan jurunilai','normal');

check_length(document.${formName}.txtKeteranganAgensi,'4000','txtKeteranganJurunilai_check','txtKeteranganJurunilai_num','normal','no','keterangan jurunilai');
check_length(document.${formName}.txtKeteranganJurunilai,'4000','txtKeteranganAgensi_check','txtKeteranganAgensi_num','normal','no','keterangan agensi');

check_length(document.${formName}.txtKeteranganJPPH,'4000','txtKeteranganJPPH_check','txtKeteranganJPPH_num','normal','no','keterangan JPPH');
check_length(document.${formName}.txtKeteranganAkujanji,'4000','txtKeteranganAkujanji_check','txtKeteranganAkujanji_num','normal','no','keterangan Akujanji');

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
	document.${formName}.sub_command.value = "Agensi";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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
	document.${formName}.sub_command.value = "Agensi";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtKeteranganAgensi";
	document.${formName}.submit();
	
}


function batal(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Agensi";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtKeteranganAgensi";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}

function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Agensi";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtKeteranganAgensi";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function nilaian(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kerosakan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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


</script>
  
