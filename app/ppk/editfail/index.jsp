


<br />
<br />




<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="id_esaduan" type="hidden" id="id_esaduan" value="$!id_esaduan"/>
<input type="hidden" id="flag_buka_upload" name="flag_buka_upload" value="$!flag_buka_upload"  />
<input name="mode" type="hidden" id="mode" value="$mode"/>

<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />




#if($view_skrin == "aduan")

#if($!open_aduancarian=="yes")
#parse("app/ppk/editfail/frmCarian.jsp")
#end


#if($!open_maklumat_aduan=="yes")
#parse("app/ppk/editfail/frmMaklumatAduan.jsp")
#end

#end



<br />


#if($!flag_popup_alert_success == "1")
<script type="text/javascript" >
alert("Maklumat Aduan Berjaya Disimpan");
</script>
#end

#if($!flag_popup_alert_success == "2")
<script type="text/javascript" >
alert("Maklumat Agihan Tugas Berjaya Disimpan");
</script>
#end

#if($!flag_popup_alert_success == "3")
<script type="text/javascript" >
alert("Aduan anda telah Berjaya Dihantar.");
</script>
#end


#if($!flag_popup_alert_success == "4")
<script type="text/javascript" >
alert("Rekod Aduan Berjaya Dihapus.");
</script>
#end


<script type="text/javascript" >

function bbb()
{
alert("XXXXXXXXXXXXXXXXX");	
}

function uploadDokumen(id_esaduan,id_esdokumen)
{
  if(document.${formName}.tajuk.value == "")
   {
   alert("Sila Masukkan Nama Dokumen");
  }
  else if(document.${formName}.fileupload.value == "")
   {
   alert("Sila Masukkan Dokumen");
  
   }	 		
	else
	{ 	
	document.${formName}.action = "?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=simpanDokumen&id_esaduan="+id_esaduan+"&id_esdokumen="+id_esdokumen+"&tajuk="+  document.${formName}.tajuk.value;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();
	}
}


function paparAduan(id){
            document.${formName}.id_esaduan.value=id;		    
   	   		//doAjaxCall${formName}("paparAduan");
			document.${formName}.command.value = "paparAduan";
			document.${formName}.submit();
}





function deleteDokumen1by1(id){
           //doAjaxCall${formName}("deleteDokumen1by1","id_esdokumen="+id);
		   document.${formName}.id_esdokumen.value = id;
		   document.${formName}.command.value = "deleteDokumen1by1";
		   document.${formName}.submit();
}





		
		function tambahDokumen()
		{
			
			//doAjaxCall${formName}("tambahDokumen");
			//document.${formName}.id_esdokumen.value = id;
		   document.${formName}.command.value = "tambahDokumen";
		   document.${formName}.submit();
		}
		
		function daftarMaklumbalas()
		{
		     if(document.${formName}.maklumbalas.value==""){
   	   			alert('Sila Masukkan Maklumbalas');
	   	   		return;
   	   		}
			else
			{
			//doAjaxCall${formName}("simpanMaklumbalas");
			document.${formName}.command.value = "simpanMaklumbalas";
		     document.${formName}.submit();
			document.${formName}.cmdDaftarMaklumbalas.value = "Sila Tunggu....";
			
			 
			
			
			}
		}
		
		
		
		
		
		
		
		
   		function simpan(){
   	   		if(document.${formName}.idTindakan.value==""){
   	   			alert('Sila Pilih Seksyen / Bahagian');
	   	   		return;
   	   		}
			else if(document.${formName}.ulasan.value==""){
   	   	   		alert('Sila Isi Arahan');
   	   	   		return;
   	   		}
   	   		else{
   	   	   		
   				//doAjaxCall${formName}("simpanAgih");
				document.${formName}.command.value = "simpanAgih";
		     document.${formName}.submit();
   	   		}
   		}
		
		
   		function kembali(){
   			//doAjaxCall${formName}("");
			document.${formName}.command.value = "";
		     document.${formName}.submit();
   		}
		
		function laporanAduan(){
   			//doAjaxCall${formName}("paparLaporan");
			document.${formName}.command.value = "paparLaporan";
		     document.${formName}.submit();
   		}
		
		function cariLaporan(){
   			//doAjaxCall${formName}("cariLaporan");
			document.${formName}.command.value = "cariLaporan";
		     document.${formName}.submit();
			document.${formName}.cariAduanLaporan.value = "Sila Tunggu....";
			
   		}
		
		function resetLaporan(){
   			//doAjaxCall${formName}("paparLaporan");
			document.${formName}.command.value = "paparLaporan";
		     document.${formName}.submit();
			document.${formName}.resetLaporan.value = "Sila Tunggu....";
			
   		}
   		}
		
		function skrinAduan(){
   			//doAjaxCall${formName}("");
			document.${formName}.command.value = "";
		    document.${formName}.submit();
   		}


	   function notAllowed(){
	   alert("Proses tidak dibenarkan. Status aduan telah Selesai");
	   }
	   
	   function daftarBaru() {	   
	   //doAjaxCall${formName}("daftarBaru");	
	   document.${formName}.command.value = "daftarBaru";
		     document.${formName}.submit();
	   document.${formName}.cmdTambahAduan.value = "Sila Tunggu....";
	   
	   }	
	   
	   function hapusDokumen(){
	   input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
   		//doAjaxCall${formName}("hapusDokumen");
		document.${formName}.command.value = "hapusDokumen";
		     document.${formName}.submit();
		
		}
   	   } 
	 
		 
   	   function daftarAgih(){	
	   	
   			//doAjaxCall${formName}("daftarAgih");
			document.${formName}.command.value = "daftarAgih";
		     document.${formName}.submit();
			document.${formName}.cmdAgihanTugas.value = "Sila Tunggu....";
			
   	   }
	   function kembaliAgih(){
	   
   			//doAjaxCall${formName}("viewComplaint");
			document.${formName}.command.value = "viewComplaint";
		     document.${formName}.submit();
   	   } 		
	   function doChangeTabUpper(tabId) {
			document.${formName}.selectedTabUpper.value = tabId;
			
			//doAjaxCall${formName}("viewComplaint");
			document.${formName}.command.value = "viewComplaint";
		     document.${formName}.submit();
	   }
	   function doChangeTabUpper1(tabId) {
			document.${formName}.selectedTabUpper.value = tabId;
			
			//doAjaxCall${formName}("daftarAgih");
			document.${formName}.command.value = "daftarAgih";
		     document.${formName}.submit();
	   }
	   function detailAgihan(id){
	   
   	   		//doAjaxCall${formName}("viewAgihan","idResponse="+id);
			document.${formName}.idResponse.value = id;
			document.${formName}.command.value = "viewAgihan";
		     document.${formName}.submit();
   		}
		function doChangeTindakan() {
		
			//doAjaxCall${formName}("doChangeTindakan");
			document.${formName}.command.value = "doChangeTindakan";
		     document.${formName}.submit();
		}

		function tutupAduan() {
			
			//doAjaxCall${formName}("tutupAduan");
			document.${formName}.command.value = "tutupAduan";
		     document.${formName}.submit();
		}
		
		function aduanPalsu() {
			
			//doAjaxCall${formName}("aduanPalsu");
			document.${formName}.command.value = "aduanPalsu";
		     document.${formName}.submit();
		}
		function updateRespon(){
		
			//doAjaxCall${formName}("updateResponPTG");
			document.${formName}.command.value = "updateResponPTG";
		     document.${formName}.submit();
		}
		
		function cariAduan(){
		
			//doAjaxCall${formName}("cariAduan");
			document.${formName}.command.value = "cariAduan";
		     document.${formName}.submit();
			document.${formName}.cariAduanX.value = "Sila Tunggu....";
			
		}
		
		function cariReset(){
		
			//doAjaxCall${formName}("");
			document.${formName}.command.value = "";
		     document.${formName}.submit();
			document.${formName}.resetAduan.value = "Sila Tunggu....";
		}
		
		
		
		
		function hapusAduan(){		
		 input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {		
			//doAjaxCall${formName}("hapusAduan");
			document.${formName}.command.value = "hapusAduan";
		     document.${formName}.submit();
			document.${formName}.cmdHapusAduan.value = "Sila Tunggu....";			
			}
		}
		
		
		function mainPage(){
		
   			//doAjaxCall${formName}("mainpage");
			document.${formName}.command.value = "mainpage";
		     document.${formName}.submit();
   	   }
	    function pilihUser() {
		
		//doAjaxCall${formName}("pilihUser");	
		document.${formName}.command.value = "pilihUser";
		     document.${formName}.submit();
		}
		
		
		function bukaUpload()
		{
		//doAjaxCall${formName}("bukaUpload");
		document.${formName}.command.value = "bukaUpload";
		     document.${formName}.submit();			
		}
		
		function bukaMaklumbalasAduan()
		{
		//doAjaxCall${formName}("bukaMaklumbalasAduan");
		document.${formName}.command.value = "bukaMaklumbalasAduan";
		     document.${formName}.submit();				
		}
		
		function bukaMaklumbalasTeknikal()
		{
		//doAjaxCall${formName}("bukaMaklumbalasTeknikal");	
		document.${formName}.command.value = "bukaMaklumbalasTeknikal";
		     document.${formName}.submit();		
		}
		
		
		function daftarAduan() {
		
		 var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	     var em = document.${formName}.emel;		
		
		    if(document.${formName}.user_id.value==""){
   	   			alert('Sila Nama Pengadu/User');
	   	   		return;
   	   		}
			else if(document.${formName}.id_jenisaduan.value==""){
   	   	   		alert('Sila Pilih Jenis Aduan');
   	   	   		return;
   	   		}
			else if(document.${formName}.id_jenismodulesaduan.value==""){
   	   	   		alert('Sila Pilih Modul/Urusan');
   	   	   		return;
   	   		}
			else if(document.${formName}.aduan.value==""){
   	   	   		alert('Sila Masukkan Aduan');
   	   	   		return;
   	   		}
			else if((document.${formName}.tarikh_aduan_hantar_date.value=="" || document.${formName}.tarikh_aduan_hantar_hour.value=="" || document.${formName}.tarikh_aduan_hantar_minute.value=="" || document.${formName}.tarikh_aduan_hantar_ampm.value=="") && ('$role' == "admin_es" || '$role' == "developer_es")){
   	   	   		alert('Sila Masukkan Tarikh Aduan Dengan Lengkap');
   	   	   		return;
   	   		}
			
			else if(document.${formName}.id_statusesaduan.value=="" && ('$role' == "admin_es" || '$role' == "developer_es")){
   	   	   		alert('Sila Pilih Status Aduan');
   	   	   		return;
   	   		}
			
			else if(document.${formName}.emel.value==""){
   	   	   		alert('Sila Masukkan Alamat Emel.');
   	   	   		return;
   	   		}
			else if(!em.value.match(emailExp) && em.value!=""){
		
			alert("Alamat email tidak sah!");		
			//em.focus();
			return;
	        }
			
			
			else
			{
			  input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {		
			//doAjaxCall${formName}("simpanAduan");
			document.${formName}.command.value = "simpanAduan";
		     document.${formName}.submit();	
			document.${formName}.cmdSimpanAduan.value = "Sila Tunggu....";		
			}
			}
		}
		
		
		
		function daftarAduan_hantar() {		
		//doAjaxCall${formName}("simpanAduan_hantar");
		document.${formName}.command.value = "simpanAduan_hantar";
		     document.${formName}.submit();	
		document.${formName}.cmdHantarAduan.value = "Sila Tunggu....";
		
		}
	
	
	
		
		

function onSize(my_form,size)
{
if(my_form.value.length > size)
{
my_form.value = my_form.value.substring(0, size);
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
	   //$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
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



function validateModal_X(elmnt,content) {
 
	
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
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	
	   }
	   else
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");
	
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
	

	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan tarikh "+value_field+" dengan betul");

	   
	   }
	   
	   }
	   
	 
	   if(jenis_field == "normal")
	   {
	   
	  	
		
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
		
	   }
	   else
	   {
	
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");

	   }
	   else
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");

	   }
	   
	   	   
	   }
	   
	   
	   
	 
	   
	
}



function textarea_maklumbalas(tambahtolak,jenis,index_tolak)
{

var maklumbalas_temp1_length=0;

if(document.${formName}.maklumbalas_temp1 != null)
{

if(document.${formName}.maklumbalas_temp1.length>0)
{
maklumbalas_temp1_length = document.${formName}.maklumbalas_temp1.length;
}
else
{
maklumbalas_temp1_length = 1;
}


}

var code_temp = "";

//alert(document.${formName}.maklumbalas_temp1);

if(document.${formName}.maklumbalas!=null)
{


if(document.${formName}.maklumbalas.length > 1)
{
 for (i = 0; i < document.${formName}.maklumbalas.length; i++)
 {
 code_temp += "<tr><td><input type='hidden' id='temp_temp' name='temp_temp' value= '"+document.${formName}.maklumbalas[i].value+"'></td></tr>";
 code_temp += "<tr><td><input type='hidden' id='temp_temp1' name='temp_temp1' value= '"+document.${formName}.maklumbalasAward[i].value+"'></td></tr>"; 
 } 
}
else
{
code_temp += "<tr><td><input type='hidden' id='temp_temp' name='temp_temp' value= '"+document.${formName}.maklumbalas.value+"'></td></tr>";
code_temp += "<tr><td><input type='hidden' id='temp_temp1' name='temp_temp1' value= '"+document.${formName}.maklumbalasAward.value+"'></td></tr>"; 
}
}

$jquery("#maklumbalas_temp").html(""+code_temp); 
var codes = "";

if(jenis == "umum")
{
if(maklumbalas_temp1_length>0)
{
tt = maklumbalas_temp1_length;
}
else
{
tt = 1;
}
}

//alert("sebelum::"+tt);

if(jenis == "tambah")
{
tt = tt + parseInt(tambahtolak);


}
if(jenis == "tolak")
{
tt = tt + parseInt(tambahtolak);
}



//alert("selepas::"+tt);
  for (i = 0; i < tt; i++)
  {	
  if(tt==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.temp_temp[1].value
	temp_amaunt = document.${formName}.temp_temp1[1].value
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.temp_temp[0].value
	temp_amaunt = document.${formName}.temp_temp1[0].value	
    } 	
	}		
   
	codes += "<table width='100%'><tr>"+
	"<td  >   "+
	 " <textarea name=\"maklumbalas\" id=\"maklumbalas\"  cols=\"40\" rows=\"4\""+          
            " onBlur=\"check_length(this,'400','maklumbalas_check','maklumbalas_num','normal','no','ulasan maklumbalas');textarea_maklumbalas1()\" "+  
         " onKeyup=\"check_length(this,'400','maklumbalas_check','maklumbalas_num','normal','no','ulasan maklumbalas');textarea_maklumbalas1()\" "+
         " onKeydown=\"check_length(this,'400','maklumbalas_check','maklumbalas_num','normal','no','ulasan maklumbalas');textarea_maklumbalas1()\" "+   
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"maklumbalas_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"maklumbalas_num\" id=\"maklumbalas_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"maklumbalas_check\" class=\"alert_msg\" ></div> "+
	    
	" </td>"+
		
	"</tr><tr>"+	
	
	"<td align='left' valign='top' > "+
	     "JUMLAH RM :  <input name=\"maklumbalasAward\" id=\"maklumbalasAward\" style=\"text-align:right\"  size=\"15\" maxlength=\"15\" "+          
    "onBlur=\"validateTarikh(this,this.value);validateModal_X(this,this.value);"+
	"checking_validation(this,'maklumbalasAward_check','no','amaun pampasan maklumbalas','normal');textarea_maklumbalas1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);"+
	"checking_validation(this,'maklumbalasAward_check','no','amaun pampasan maklumbalas','normal');textarea_maklumbalas1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);"+
		 "checking_validation(this,'maklumbalasAward_check','no','amaun pampasan maklumbalas','normal');textarea_maklumbalas1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"maklumbalasAward_check\" class=\"alert_msg\" ></span> ";	 	
		 codes +=  "#if($readmode == 'edit' ) ";
	     codes += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+'  onkeypress=\"textarea_maklumbalas(1,'tambah','');textarea_maklumbalas1()\"  onClick=\"textarea_maklumbalas(1,'tambah','');textarea_maklumbalas1()\" title='Menambah maklumat pampasan maklumbalas'> "+
	      " </span>"; 	
	     if(tt>1) {      
	     codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_maklumbalas(-1,'tolak','');textarea_maklumbalas1()\"  onClick=\"textarea_maklumbalas(-1,'tolak','');textarea_maklumbalas1()\" title='Mengurangkan maklumat pampasan maklumbalas' > "+
	"</span> ";
	}
	codes +="#end";		 
	codes+=" </td>"+	
	"</tr></table>";
	
	
	}
	else
	{
	
	var temp_value = "";
	var temp_amaunt = "";
	
	if(tt==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.temp_temp.value
	temp_amaunt = document.${formName}.temp_temp1.value
	}	
	else if(tt>2 && i!=(tt-1) && jenis == "tambah")
	{
			
	temp_value = document.${formName}.temp_temp[i].value
	temp_amaunt = document.${formName}.temp_temp1[i].value
//	alert("111:::"+tt);
	}	
	else if(tt>1 && i!=(tt+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.temp_temp[parseInt(index_tolak)+1].value
	temp_amaunt = document.${formName}.temp_temp1[parseInt(index_tolak)+1].value
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.temp_temp[i].value
	temp_amaunt = document.${formName}.temp_temp1[i].value	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.temp_temp[i+1].value	
	temp_amaunt = document.${formName}.temp_temp1[i+1].value
   }	

	}
	else if(tt==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.temp_temp[1].value;
	temp_amaunt = document.${formName}.temp_temp1[1].value;
	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.temp_temp[0].value;
	temp_amaunt = document.${formName}.temp_temp1[0].value;
	
   }
   }		
	
	
	
	
	codes += "<table width='100%'><tr>"+
	"<td >"+
	     "<textarea name=\"maklumbalas\" id=\"maklumbalas\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','maklumbalas_check"+i+"','maklumbalas_num"+i+"','normal','no','ulasan maklumbalas');textarea_maklumbalas1()\" "+  
         "onKeyup=\"check_length(this,400,'maklumbalas_check"+i+"','maklumbalas_num"+i+"','normal','no','ulasan maklumbalas');textarea_maklumbalas1()\" "+
         "onKeydown=\"check_length(this,'400','maklumbalas_check"+i+"','maklumbalas_num"+i+"','normal','no','ulasan maklumbalas');textarea_maklumbalas1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"maklumbalas_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"maklumbalas_num"+i+"\" id=\"maklumbalas_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"maklumbalas_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td align='left' valign='top' > "+
	     "JUMLAH RM : <input name=\"maklumbalasAward\" id=\"maklumbalasAward\" size=\"15\" style=\"text-align:right\"  maxlength=\"15\" "+          
         "onBlur=\"validateTarikh(this,this.value);checking_validation(this,'maklumbalasAward_check"+i+"','no','amaun pampasan maklumbalas','normal');validateModal_X(this,this.value);textarea_maklumbalas1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);checking_validation(this,'maklumbalasAward_check"+i+"','no','amaun pampasan maklumbalas','normal');textarea_maklumbalas1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);checking_validation(this,'maklumbalasAward_check"+i+"','no','amaun pampasan maklumbalas','normal');textarea_maklumbalas1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"maklumbalasAward_check"+i+"\" class=\"alert_msg\" ></span> ";		
		 codes +=  "#if($readmode == 'edit' ) ";
		 
    if(tt>1 && tt==(i+1)) {  	
	codes += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_maklumbalas(1,'tambah','');textarea_maklumbalas1()\"  onClick=\"textarea_maklumbalas(1,'tambah','');textarea_maklumbalas1()\" title='Menambah maklumat pampasan maklumbalas'> "+
	" </span>"; 
	}
	if(tt>1) {      
	codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_maklumbalas(-1,'tolak','"+i+"');textarea_maklumbalas1()\" onClick=\"textarea_maklumbalas(-1,'tolak','"+i+"');textarea_maklumbalas1()\" title='Mengurangkan maklumat pampasan maklumbalas' > "+
	"</span> ";
	}
	codes +="#end";		 
	codes +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
//	alert("CODES ::"+codes);
	
	$jquery("#maklumbalas").html(codes);
	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(maklumbalas_temp1_length > 1 && document.${formName}.maklumbalas.length > 1 )
	{
	for (t = 0; t < maklumbalas_temp1_length; t++)
    {	
    document.${formName}.maklumbalas[t].value = document.${formName}.maklumbalas_temp1[t].value;
	document.${formName}.maklumbalasAward[t].value = document.${formName}.maklumbalas_temp2[t].value;
    }
	}
	else if(maklumbalas_temp1_length > 1 && document.${formName}.maklumbalas.length < 1 )
	{
	for (t = 0; t < maklumbalas_temp1_length; t++)
    {	
    document.${formName}.maklumbalas.value = document.${formName}.maklumbalas_temp1[0].value;
	document.${formName}.maklumbalasAward.value = document.${formName}.maklumbalas_temp2[0].value;
    }
	}
	else if(maklumbalas_temp1_length == 1)
	{
	document.${formName}.maklumbalas.value = document.${formName}.maklumbalas_temp1.value;
	document.${formName}.maklumbalasAward.value = document.${formName}.maklumbalas_temp2.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(maklumbalas_temp1_length > 1)
	{
	for (t = 0; t < maklumbalas_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.maklumbalas_temp1[index_tolak].value = "";	
	var element = document.${formName}.maklumbalas_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	document.${formName}.maklumbalas_temp2[index_tolak].value = "";	
	var element2 = document.${formName}.maklumbalas_temp2[index_tolak];
    element2.parentNode.removeChild(element2);
	}
    }	
	}
	else if(maklumbalas_temp1_length == 1)
	{	
	 document.${formName}.maklumbalas_temp1.value = "";			
	 var element = document.${formName}.maklumbalas_temp1;
     element.parentNode.removeChild(element);	 
	 document.${formName}.maklumbalas_temp2.value = "";			
	 var element2 = document.${formName}.maklumbalas_temp2;
     element2.parentNode.removeChild(element2);	
	}
	}			
	
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < tt; i++)
    {		
    if(tt==1)
    {	
	check_length(document.${formName}.maklumbalas,'400','maklumbalas_check','maklumbalas_num','normal','no','ulasan maklumbalas');	
	}
	else
	{	
	check_length(document.${formName}.maklumbalas[i],'400','maklumbalas_check'+i,'maklumbalas_num'+i,'normal','no','ulasan maklumbalas');
	}		 
	}	
	}
	
	
}


function textarea_maklumbalas1()
{

	var maklumbalas_temp1_length=0;
	
	if(document.${formName}.maklumbalas_temp1 != null)
	{
	if(document.${formName}.maklumbalas_temp1.length>0)
	{
	maklumbalas_temp1_length = document.${formName}.maklumbalas_temp1.length;
	}
	else
	{
	maklumbalas_temp1_length = 1;
	}
	}

    if(maklumbalas_temp1_length > 1 && document.${formName}.maklumbalas.length > 1 )
	{
	for (t = 0; t < maklumbalas_temp1_length; t++)
    {	
    document.${formName}.maklumbalas_temp1[t].value = document.${formName}.maklumbalas[t].value;
	document.${formName}.maklumbalas_temp2[t].value = document.${formName}.maklumbalasAward[t].value;
    }
	}
	else if(maklumbalas_temp1_length > 1 && document.${formName}.maklumbalas.length < 1 )
	{
	for (t = 0; t < maklumbalas_temp1_length; t++)
    {	
    document.${formName}.maklumbalas_temp1[0].value = document.${formName}.maklumbalas.value;
	document.${formName}.maklumbalas_temp2[0].value = document.${formName}.maklumbalasAward.value;
    }
	}
	else if(maklumbalas_temp1_length == 1)
	{
	document.${formName}.maklumbalas_temp1.value = document.${formName}.maklumbalas.value;
	document.${formName}.maklumbalas_temp2.value = document.${formName}.maklumbalasAward.value;
	}
	
	
	
	
	
		
	    var total_nilai = 0;
        if (document.${formName}.maklumbalasAward.length == null)
		{
				if(document.${formName}.maklumbalasAward.value != "")
				{				   
				   total_nilai = total_nilai + parseFloat(document.${formName}.maklumbalasAward.value) ;					   	   
				}
				else
				{
				   total_nilai = total_nilai+0;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.maklumbalasAward.length; i++)
			{
               if(document.${formName}.maklumbalasAward[i].value != "")
				{			  
				   total_nilai = total_nilai + parseFloat(document.${formName}.maklumbalasAward[i].value) ;			   	   	   
				}
				else
				{
				   total_nilai = total_nilai + 0;
				}
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


function set_temp_catatan(i,jumlah,value)
{
//alert("xxxx:"+i);
if(jumlah>0)
{
document.${formName}.nama_span_hidden[i].value = value;
}
else
{
document.${formName}.nama_span_hidden.value = value;
}

}

function ReadOnly_CheckBox(v)
{
return false;
}

function autoExpand(event,field_id){



if(event.keyCode == "13" || event.keyCode == "8")
{
var therows=0
var thetext = document.getElementById(field_id).value;
var newtext = thetext.split("\n");
therows+=newtext.length
var i;
document.getElementById(field_id).rows = therows;
return false;
}


}
	

function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.model.esaduan.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function onbuttonsimpan()
{
   if(document.getElementById('maklumbalas').value != "")
   {
     document.getElementById('cmdDaftarMaklumbalas').style.display = "";
   }
   else
   {
    document.getElementById('cmdDaftarMaklumbalas').style.display = "none";
   }
}

function onbuttonsimpan_tech()
{
   if(document.getElementById('maklumbalas_teknikal').value != "")
   {
     document.getElementById('cmdDaftarMaklumbalasTeknikal').style.display = "";
   }
   else
   {
    document.getElementById('cmdDaftarMaklumbalasTeknikal').style.display = "none";
   }
}
	
	
	

function kemaskiniProfil(){
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
	document.${formName}.submit();
}



function doCheckall1_delete(){    
    if (document.${formName}.all1_delete.checked == true){
	document.getElementById('cmdHapusAduan').style.display = "";
        if (document.${formName}.ids1_delete.length == null){
            document.${formName}.ids1_delete.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete.length; i++){
                document.${formName}.ids1_delete[i].checked = true;
            }


        }
    } else {
	document.getElementById('cmdHapusAduan').style.display = "none";
        if (document.${formName}.ids1_delete.length == null){
            document.${formName}.ids1_delete.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete.length; i++){
                document.${formName}.ids1_delete[i].checked = false;
            }
        }
    }
}


function doUpdateCheckall1_delete(){  
var c = 0;
var x = 0;

if(document.${formName}.ids1_delete.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1_delete.length; i++)
	  {
      if (document.${formName}.ids1_delete[i].checked == false)
	  {	 
	  c++
      }else
	  {	 
	  x++
      }
	  }  
}
else
{
if (document.${formName}.ids1_delete.checked == false)
{	 
c++;
}
else
{
x++
}
	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1_delete.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1_delete.checked = true;
	  }
	  
	  if(x>0) 
	  {
	  document.getElementById('cmdHapusAduan').style.display = "";
	  }
	  else
	  {
	  document.getElementById('cmdHapusAduan').style.display = "none";
	  }
	  
}


function cetakLaporanX_()
{	
//alert("XXXXX");
}


function cetakLaporan(mode){
/*
alert("1:"+document.${formName}.report_nama_modul.value);
alert("2:"+document.${formName}.id_negeri_cari.value);
alert("3:"+document.${formName}.id_jenismodulesaduan_cari.value);
alert("4:"+document.${formName}.jumlah_aduan_total.value);
alert("5:"+document.${formName}.jumlah_aduan_baru_total.value);
alert("6:"+document.${formName}.jumlah_aduan_tindakan_total.value);
alert("7:"+document.${formName}.jumlah_selesai_total.value);
alert("8:"+document.${formName}.peratus_modul_total_hidden.value);
alert("9:"+document.${formName}.report_nama_negeri.value);
alert("10:"+document.${formName}.tarikh_mula.value);
alert("11:"+document.${formName}.tarikh_akhir.value);

	*/
	
		if(mode=="1"){
			var url = "../servlet/ekptg.report.esaduan.LaporanByModule?modul="+document.${formName}.report_nama_modul.value+"&id_negeri="+document.${formName}.id_negeri_cari.value+"&id_modul="+document.${formName}.id_jenismodulesaduan_cari.value+"&total_aduan="+document.${formName}.jumlah_aduan_total.value+"&total_baru="+document.${formName}.jumlah_aduan_baru_total.value+"&total_tindakan="+document.${formName}.jumlah_aduan_tindakan_total.value+"&total_selesai="+document.${formName}.jumlah_selesai_total.value+"&total_peratus="+document.${formName}.peratus_modul_total_hidden.value+"&negeri="+document.${formName}.report_nama_negeri.value+"&tarikh_mula="+document.${formName}.tarikh_mula.value+"&tarikh_akhir="+document.${formName}.tarikh_akhir.value;		    
		}else if(mode=="2"){
			var url = "../servlet/ekptg.report.esaduan.LaporanByModule";		
		}else if(mode=="3"){
			var url = "../servlet/ekptg.report.ppt.Caj_bayaran_lewat_negeri?tahun="+tahun;		
		}
		
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	

}


function bukak_skrin_dashbaord() {
    document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&command=open_dashboard";
	document.${formName}.submit();
}

</script>



#parse("app/ppk/headerppk_script.jsp")