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

#foreach ( $senarai in $getMaklumatBantahan )
    #set ($id_bantahan=$senarai.id_bantahan)
    #set ($id_pihakberkepentingan=$senarai.id_pihakberkepentingan)
    #set ($id_hakmilik=$senarai.id_hakmilik)
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
    <span id="txtnamadokumen_check" style="color:red" ></span>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Keterangan</td>
    <td valign="top">:</td>
    <td>
    <textarea name="txtketerangandokumen" id="txtketerangandokumen" cols="40"  rows="5" $readonlymode class = "$disabledmode" >$txtketerangandokumen</textarea>
    </td>
  </tr>
  <tr>
    <td valign="top"><span class="style1">*</span></td>
    <td valign="top">Dokumen Sokongan</td>
    <td valign="top">:</td>
      <td>
      #if($id_dokumen == "")
      #foreach( $i in [1..$num_files] )
       
  	<input id="fileupload" name="fileupload" type=file size=50 value="$!txtdokumensokongan" onmouseout="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');"  onkeyup="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');"  onblur="checking_validation(this,'fileupload_check','yes','dokumen sokongan','file');" />
  	<br/>
      
  	  #end   
      #else
   $!txtdokumensokongan      
      #end
      
       <div id="fileupload_check" style="color:red" ></div>
      </td>   
    
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
    
 
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
    #if($readmode == "view")
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini_Dokumen('$id_dokumen')">
      <input type="button" name="cmdHapus_dokumen" id="cmdHapus_dokumen" value="Hapus" onclick="Delete('$!id_dokumen')" >
    #end
    
    #if($readmode == "edit")  
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Upload()" >
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="Batal('$!id_dokumen')">
    #end
    
    <!-- :::upload -->
		<input type="hidden" name="nama_skrin" id="nama_skrin" value="$!nama_skrin"  />

   
      
        #if($!nama_skrin == "main")
      <input type="button" name="cmdKembali" id="cmdKembali"  value="Kembali" onclick="Kembali()">
      #end
      
      #if($!nama_skrin == "perintah")
       <input type="button" name="cmdKembali1" id="cmdKembali1"  value="Kembali" onclick="KembaliPerintah('')">
      #end
   
      </label></td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;
    
    </td>
    </tr>
  
  <tr>
    <td colspan="4">
    <fieldset id="senarai_dokumen" >
   
    <legend>SENARAI DOKUMEN YANG DISERTAKAN</legend>
    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" title="Sila klik untuk tambah dokumen" >    
    #if($listDokumen_size > 0)
     <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumen('$!readmode')" title="Sila tick untuk hapus dokumen" >
    #end
    <table width="100%">
  <tr class="table_header">
    <td width="3%">Bil</td>
    <td width="30%">Nama Dokumen</td>
    <td width="30%">Keterangan</td>
    <td width="30%">Dokumen Sokongan (Papar)</td>
     #if($listDokumen_size > 0)
      <td width="7%">
     
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
    <td class="$row" ><a href="javascript:view_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.TAJUK</font></a></td>
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
   
	  <input type="hidden" name="location" id="location" />
      <input type="hidden" name="point" id="point" />
      <input type="hidden" name="id_permohonan" value="$id_permohonan">
      <input type="hidden" name="id_bantahan" value="$!id_bantahan">
      <input type="hidden" name="id_dokumen" id="id_dokumen" value="$!id_dokumen"  />
      <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
      <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
      <input type="hidden" name="alert_message" id="alert_message" />
	  <input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
      <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
      <input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />

<script type="text/javascript">
var readmode = '$readmode';
window.onload = submitForm;

function submitForm() 
{

if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');
}

if(readmode == "edit")
{	   
	   if(document.${formName}.txtnamadokumen.value == "")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan nama dokumen";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "txtnamadokumen_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   else
	   {
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "txtnamadokumen_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }

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
		   target = "fileupload_check";
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
	    }
		else
		{
		   document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = "fileupload_check";
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		
		
		}

}

}

</script>

<script>
//:::upload
function Upload(){	
   
    var nama_skrin = document.${formName}.nama_skrin.value ; 
    var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	var id_hakmilikpb = document.${formName}.id_hakmilikpb.value ;	
	var id_hakmilik = document.${formName}.id_hakmilik.value ;	
	var id_pihakberkepentingan = document.${formName}.id_pihakberkepentingan.value ;	
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
	document.${formName}.command.value = "upload_dokumen";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";
	document.${formName}.enctype = "multipart/form-data";
	document.${formName}.encoding = "multipart/form-data";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=upload_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&id_hakmilikpb="+id_hakmilikpb+"&id_hakmilik="+id_hakmilik+"&id_pihakberkepentingan="+id_pihakberkepentingan+"&txtnamadokumen="+txtnamadokumen+"&location=senarai_dokumen&point=senarai_dokumen&txtketerangandokumen="+txtketerangandokumen+"&location="+location+"&point="+point+"&id_dokumen="+id_dokumen+"&nama_skrin="+nama_skrin;
	document.${formName}.submit();	
	}	
	
	}
	
		
}

function hapusDokumen(r){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=hapus_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
	document.${formName}.submit();
	}
}

	function Kembali(){
		var id_bantahan = document.${formName}.id_bantahan.value ;
		var id_permohonan = document.${formName}.id_permohonan.value ;	
		var senarai_dokumen = document.${formName}.senarai_dokumen.value ;
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=dalamProses&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point="+senarai_dokumen;	
		document.${formName}.submit();

	}
//:::upload
function KembaliPerintah(r){

	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	var senarai_dokumen = document.${formName}.senarai_dokumen.value ;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=susulanBantahan&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point="+senarai_dokumen;	
	document.${formName}.submit();
	
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

function tambahDokumen() {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=tambah_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=txtnamadokumen";	
	document.${formName}.submit();
}

function view_Lampiran(id_dokumen) {
    var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=view_Dokumen_Details&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Kemaskini_Dokumen(id_dokumen)
{
 var id_bantahan = document.${formName}.id_bantahan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=kemaskini_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function Batal(id_dokumen)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
 var id_bantahan = document.${formName}.id_bantahan.value ;
 var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=batal_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
	}
}

function Delete(id_dokumen){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=hapus_dokumen_papar&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&id_dokumen="+id_dokumen;	
	document.${formName}.submit();
	}	
}


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

</script>

<script>
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
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
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
	 //  DateField.focus();
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


</script>

