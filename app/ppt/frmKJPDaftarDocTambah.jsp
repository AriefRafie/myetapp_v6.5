
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>


#set($txtnamadokumen = "")
#set($txtketerangandokumen = "")
#set($txtdokumensokongan = "")
#set($id_dokumen = "")

#foreach($view in $view_details_dokumen)

#set($txtnamadokumen = $view.TAJUK)
#set($txtketerangandokumen = $view.KETERANGAN)
#set($txtdokumensokongan = $view.NAMA_FAIL)
#set($id_dokumen = $view.ID_DOKUMEN)
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
    <td><fieldset id="maklumat_dokumen"><legend>MAKLUMAT DOKUMEN</legend>
    <table width="100%">
  <tr>
    <td width="1%"><span class="style1">*</span></td>
    <td width="28%">Nama Dokumen</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtnamadokumen" type="text" id="txtnamadokumen" size="50" maxlength="200" value="$txtnamadokumen" $readonlymode class = "$disabledmode" onblur="checking_validation(this,'txtnamadokumen_check','yes','nama dokumen','normal');" onkeyup="checking_validation(this,'txtnamadokumen_check','yes','nama dokumen','normal');" >
    </label>
    <span id="txtnamadokumen_check"  class="alert_msg" ></span>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Keterangan</td>
    <td valign="top">:</td>
    <td>
    <!--
    <textarea name="txtketerangandokumen" id="txtketerangandokumen" cols="40"  rows="5" $readonlymode class = "$disabledmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" >$txtketerangandokumen</textarea>
    -->
    
      <textarea name="txtketerangandokumen" id="txtketerangandokumen" cols="60"   rows="6" style="text-transform:uppercase;"         
         onBlur="this.value=this.value.toUpperCase();check_length(this,'400','txtketerangandokumen_check','txtketerangandokumen_num','normal','no','keterangan dokumen');"  
         onKeyup="check_length(this,'400','txtketerangandokumen_check','txtketerangandokumen_num','normal','no','keterangan dokumen');" 
         onKeydown="check_length(this,'400','txtSebabPembatalan_check','txtketerangandokumen_num','normal','no','keterangan dokumen');"                    
          $readonlymode class = "$disabledmode" 
        >$txtketerangandokumen</textarea>       
       #if($readmode == "edit")           
       <div><span id="txtketerangandokumen_num" style="color:blue;" ></span><span> Baki Aksara</span>        
       </div>
         #else
         <input name="txtketerangandokumen_num" id="txtketerangandokumen_num" size="3" value="400"  style=" display:none" > 
         #end
  <div id="txtketerangandokumen_check" class="alert_msg" ></div> 
    </td>
  </tr>
  <tr>
    <td valign="top"><span class="style1">*</span></td>
    <td valign="top">Lampiran Dokumen</td>
    <td valign="top">:</td>
      <td>
      #if($id_dokumen == "")
      
      #if($jenis_permohonan == "3" || $jenis_permohonan == "1")
      
      #foreach( $i in [1..1] ) 
  	<input id="fileupload" name="fileupload" type=file size=50 value="$!txtdokumensokongan"   onmouseout="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');"  onkeyup="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');"  onblur="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');" />
  	<br/>
  	   #end
  	 
  	  #else
  	  
  	  #foreach( $i in [1..$num_files] ) 
  	<input id="fileupload" name="fileupload" type=file size=50 value="$!txtdokumensokongan"   onmouseout="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');"  onkeyup="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');"  onblur="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');" />
  	<br/>
  	   #end
  	  
  	  #end  
  	   
      #else
   $!txtdokumensokongan      
      #end
      
      
       <div id="fileupload_check"  class="alert_msg" ></div>
      </td>   
    
  </tr>
 
   <tr>
    <td colspan="4"></td>
  </tr>  
 
    
 
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
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
      <label>
      <input type="button" name="cmdKembali" id="cmdKembali"  value="Kembali" onclick="kembali('$!screen_name')">
   
      </label></td>
  </tr>
    #if($readmode == "edit")
    #if($jenis_permohonan == "3")
  <tr>
    <td colspan="4"><i><font color=red style=font-size:10px>Perhatian</font><font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>diisi.</font></i></td>
  </tr>
  #else
  <tr>
    <td colspan="4">#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #end
  
  #else
   <tr>
    <td colspan="4"></td>
  </tr>
  #end
  
  <tr>
    <td colspan="4">
    <fieldset id="senarai_dokumen" >
   
    <legend>SENARAI DOKUMEN YANG DISERTAKAN</legend>
    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" >    
    #if($listDokumen_size > 0)
     <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumen('$!readmode')">
    #end
    <table width="100%">
  <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="30%">Nama Dokumen</td>
    <td width="30%">Keterangan</td>
    <td width="30%">Muat Turun</td>
     #if($listDokumen_size > 0)
      <td width="5%">
     
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onclick="doCheckAll1()" title="Semak untuk pilih semua" />
      </div>
      
      </td>
      #end
  </tr>
 
  
 #if($listDokumen_size > 0)
  #foreach($list1 in $listDokumen)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
  <tr>  
    <td class="$row" >$list1.BIL</td>
    #if($jenis_permohonan == "3" || $jenis_permohonan == "1")
    <td class="$row" >$list1.TAJUK</td>
    #else
    <td class="$row" ><a href="javascript:view_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.TAJUK</font></a></td>
    #end
    <td class="$row" >$list1.KETERANGAN</td>
    <td class="$row"><a href="javascript:papar_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.NAMA_FAIL</font></a></td>   
    <td class="$row" ><div align="center">
       <input type="checkbox" name="ids1" id="ids1" onclick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
     </div></td>
  </tr>
  #end
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
</table>

    </fieldset> 
    </td>  
  </tr>
  
</table>

    </fieldset></td>
  </tr>
</table>

	
     
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
window.location.hash='maklumat_dokumen';
goTo('maklumat_dokumen');
}

if(readmode == "edit")
{
	   
	   if(document.${formName}.txtnamadokumen.value == "")
	   {
	   
	   $jquery("#txtnamadokumen_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	 		
	   }
	   else
	   {
	 
	  
	   $jquery("#txtnamadokumen_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	 		
	 
	   }
	   

		check_length(document.${formName}.txtketerangandokumen,'400','txtketerangandokumen_check','txtketerangandokumen_num','normal','no','alasan penarikan');



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
		
$jquery("#fileupload_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	 		}
		else
		{
		
		$jquery("#fileupload_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	 		
		
		}
		
		



}

}

</script>







#if($jenis_permohonan == "2")
<script>
function Upload(s){	
   
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	var txtnamadokumen = document.${formName}.txtnamadokumen.value ;
	var txtketerangandokumen = document.${formName}.txtketerangandokumen.value ;	
	var location = document.${formName}.location.value ;
	var point = document.${formName}.point.value ;
	var id_dokumen = document.${formName}.id_dokumen.value ;
	var allBlank = true;
	
	
	
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
	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "upload_dokumen";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";
	document.${formName}.enctype = "multipart/form-data";
	document.${formName}.encoding = "multipart/form-data";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=Dokumen&sub_command=upload_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&txtnamadokumen="+txtnamadokumen+"&location=senarai_dokumen&point=senarai_dokumen&txtketerangandokumen="+txtketerangandokumen+"&location="+location+"&point="+point+"&id_dokumen="+id_dokumen+"&screen_name="+s;
	document.${formName}.submit();	
	}	
	
	}
	
		
}


function kembali(s)
{
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	

if(s == 's1')
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
else if(s == 's2')
{

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=PembatalanAmbilTanahLotUnit&sub_command=view_PembatalanAmbilTanahLotUnit&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
else
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
}




function hapusDokumen(r)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=Dokumen&sub_command=hapus_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
	document.${formName}.submit();
	}
	
}

function tambahDokumen()
{
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=Dokumen&sub_command=tambah_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=txtnamadokumen";	
	document.${formName}.submit();
}

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Kemaskini_Dokumen(id_dokumen)
{
 var id_pembatalan = document.${formName}.id_pembatalan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=Dokumen&sub_command=kemaskini_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Batal(id_dokumen)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
 var id_pembatalan = document.${formName}.id_pembatalan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=Dokumen&sub_command=batal_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
	}
}

function Delete(id_dokumen)
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=Dokumen&sub_command=hapus_dokumen_papar&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&id_dokumen="+id_dokumen;	
	document.${formName}.submit();
	}
	
}

</script>

#end









#if($jenis_permohonan == "1")
<script>
function Upload(s){	
   
	if(document.${formName}.txtnamadokumen.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.txtnamadokumen.focus(); 
		return;
	}
	if(document.${formName}.fileupload.value == ""){
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?")) return;

		//data
		var txtnamadokumen = document.${formName}.txtnamadokumen.value ;
		var txtketerangandokumen = document.${formName}.txtketerangandokumen.value ;
		var id_pembatalan = document.${formName}.id_pembatalan.value ;
		var id_permohonan = document.${formName}.id_permohonan.value ;	
		var location = document.${formName}.location.value ;
		var point = document.${formName}.point.value ;
		var id_dokumen = document.${formName}.id_dokumen.value ;
	
		document.${formName}.command.value = "Dokumen";
		document.${formName}.sub_command.value = "upload_dokumen";
		document.${formName}.location.value = "senarai_dokumen";
		document.${formName}.point.value = "senarai_dokumen";
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=Dokumen&sub_command=upload_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&txtnamadokumen="+txtnamadokumen+"&location=senarai_dokumen&point=senarai_dokumen&txtketerangandokumen="+txtketerangandokumen+"&location="+location+"&point="+point+"&id_dokumen="+id_dokumen+"&screen_name="+s;
		document.${formName}.submit();	
	}
		
}


function kembali(s)
{
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	

if(s == 's1')
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
else if(s == 's2')
{

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=PembatalanAmbilTanahLotUnit&sub_command=view_PembatalanAmbilTanahLotUnit&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
else
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
}

function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hapusDokumen(r)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=Dokumen&sub_command=hapus_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
	document.${formName}.submit();
	}
	
}

function tambahDokumen()
{
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=Dokumen&sub_command=tambah_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=txtnamadokumen";	
	document.${formName}.submit();
}

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Kemaskini_Dokumen(id_dokumen)
{
 var id_pembatalan = document.${formName}.id_pembatalan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=Dokumen&sub_command=kemaskini_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Batal(id_dokumen)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
 var id_pembatalan = document.${formName}.id_pembatalan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=Dokumen&sub_command=batal_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
	}
}

function Delete(id_dokumen)
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=Dokumen&sub_command=hapus_dokumen_papar&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&id_dokumen="+id_dokumen;	
	document.${formName}.submit();
	}
	
}



</script>

#end





#if($jenis_permohonan == "3")
<script>
function Upload(s){	

	if(document.${formName}.txtnamadokumen.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.txtnamadokumen.focus(); 
		return;
	}
	if(document.${formName}.fileupload.value == ""){
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?")) return;

		//data
		var txtnamadokumen = document.${formName}.txtnamadokumen.value ;
		var txtketerangandokumen = document.${formName}.txtketerangandokumen.value ;
		var id_pembatalan = document.${formName}.id_pembatalan.value ;
		var id_permohonan = document.${formName}.id_permohonan.value ;	
		var location = document.${formName}.location.value ;
		var point = document.${formName}.point.value ;
		var id_dokumen = document.${formName}.id_dokumen.value ;
	
		document.${formName}.command.value = "Dokumen";
		document.${formName}.sub_command.value = "upload_dokumen";
		document.${formName}.location.value = "senarai_dokumen";
		document.${formName}.point.value = "senarai_dokumen";
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=upload_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&txtnamadokumen="+txtnamadokumen+"&location=senarai_dokumen&point=senarai_dokumen&txtketerangandokumen="+txtketerangandokumen+"&location="+location+"&point="+point+"&id_dokumen="+id_dokumen+"&screen_name="+s;
		document.${formName}.submit();	
	}
		
}


function kembali(s)
{
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	

if(s == 's1')
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
else if(s == 's2')
{

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=PembatalanAmbilTanahLotUnit&sub_command=view_PembatalanAmbilTanahLotUnit&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
else
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
}


function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hapusDokumen(r)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=hapus_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
	document.${formName}.submit();
	}
	
}

function tambahDokumen()
{
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=tambah_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=txtnamadokumen";	
	document.${formName}.submit();
}

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Kemaskini_Dokumen(id_dokumen)
{
 var id_pembatalan = document.${formName}.id_pembatalan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=kemaskini_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Batal(id_dokumen)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
 var id_pembatalan = document.${formName}.id_pembatalan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=batal_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
	}
}

function Delete(id_dokumen)
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=hapus_dokumen_papar&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&id_dokumen="+id_dokumen;	
	document.${formName}.submit();
	}
	
}




</script>

#end




#if($jenis_permohonan == "4")
<script>
function Upload(s){	
   
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	var txtnamadokumen = document.${formName}.txtnamadokumen.value ;
	var txtketerangandokumen = document.${formName}.txtketerangandokumen.value ;	
	var location = document.${formName}.location.value ;
	var point = document.${formName}.point.value ;
	var id_dokumen = document.${formName}.id_dokumen.value ;
	var allBlank = true;
	
	
	
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
	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "upload_dokumen";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";
	document.${formName}.enctype = "multipart/form-data";
	document.${formName}.encoding = "multipart/form-data";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=upload_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&txtnamadokumen="+txtnamadokumen+"&location=senarai_dokumen&point=senarai_dokumen&txtketerangandokumen="+txtketerangandokumen+"&location="+location+"&point="+point+"&id_dokumen="+id_dokumen+"&screen_name="+s;
	document.${formName}.submit();	
	}	
	
	}
	
		
}


function kembali(s)
{
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	

if(s == 's1')
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
else if(s == 's2')
{

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=PembatalanAmbilTanahLotUnit&sub_command=view_PembatalanAmbilTanahLotUnit&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
else
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=PembatalanAmbilTanahPT&sub_command=view_PembatalanAmbilTanahPT&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&screen_name="+s;	
	document.${formName}.submit();
}
}




function hapusDokumen(r)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=hapus_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
	document.${formName}.submit();
	}
	
}

function tambahDokumen()
{
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=tambah_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=txtnamadokumen";	
	document.${formName}.submit();
}

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Kemaskini_Dokumen(id_dokumen)
{
 var id_pembatalan = document.${formName}.id_pembatalan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=kemaskini_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Batal(id_dokumen)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
 var id_pembatalan = document.${formName}.id_pembatalan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=batal_dokumen&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
	}
}

function Delete(id_dokumen)
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=hapus_dokumen_papar&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&id_dokumen="+id_dokumen;	
	document.${formName}.submit();
	}
	
}

</script>

#end



<script>
function onSubmitForm( f, ext )
{//(C)2006 Stephen Chalmers

var badName="", allBlank=true, rx;

rx=new RegExp("[^\.]\."+ext+"\s*$", "i");

for( var i=0,e=f.elements; i<e.length; i++ )
{
if( e[i].type=='file' )
{
e[i].value=e[i].value.replace(/^\s+/,'').replace(/\s+$/,'');

if( e[i].value.length )
{
allBlank=false;
if( !rx.test(e[i].value) )
badName+='\n\n' + e[i].value;
}
}
}

if( allBlank )
alert("Please click on 'Choose' and select a file to upload.")
else
if( badName )
alert( 'A required .'+ext+' extension was not found in:' + badName );

return (badName || allBlank) ? false : true;
}



function checking_validation(field,point,mandatory,value_field,jenis_field){	
   // 	alert("dssd");
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
	   //alert(lepas_or_xlepas);
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");
	   /*
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
	/*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	 //  DateField.select();
	 //  DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul ");
		/*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	*/
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  ");
		
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
	   }
	   else
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
		
	   /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
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
		 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
		/*
		   document.${formName}.alert_message.value  = "Sila masukkan dokumen terlebih dahulu";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */	
	    }
		else
		{
		 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
		/*
		   document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		*/
		
		}
		
		
	   
	   }
	   
	   
	   
	
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

function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
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


</script>

