<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<fieldset>
<legend>CARIAN</legend>
<table border="0" width="100%">
    <tbody>
    	<tr>			  
			<td colspan="3" align="center">&nbsp;</td>
      </tr>
      <tr >
        <td align="right" width="40%"><div align="right">Negeri</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%">$selectNegeri</td>
      </tr>    
      <tr>
        <td align="right" width="40%"><div align="right">Daerah</div></td>
        <td><div align="center">:</div></td>
        <td>$selectDaerah</td>
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right">Bandar/Pekan/Mukim</div></td>
        <td><div align="center">:</div></td>
        <td>$selectMukim</td>
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right">Jenis Hakmilik</div></td>
        <td><div align="center">:</div></td>
        <td>$selectJenisHakmilik</td>
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right">No. Hakmilik</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input name="txtNoHakmilik" type="text" id="txtNoHakmilik" value="$txtNoHakmilik" />
        </label></td>
      </tr>
      <tr>
        <td></td>
        <td>&nbsp;</td>
        <td>
        <input type="button" name="btnCari" id="btnCari" value="Cari" onclick="cari()"/>
        <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongCarian()" />
        </td>
      </tr>
      <!-- <tr>			  
			<td colspan="3" align="center">&nbsp;</td>
		</tr> -->
    </tbody>
  </table>  

</fieldset>
<fieldset>
<legend>SENARAI FAIL</legend>
<table border="0" width="100%">
    <tr>
    	<td colspan="6">#parse("app/utils/record_paging.jsp") </td>
    </tr>
	<tr class="table_header">
	  <td width="4%">BIL.</td>
   	  <td width="20%">NO FAIL</td>
  	  <td width="25%">NO HAKMILIK</td>
   	  <td width="25%">NO WARTA</td>
  	  <td width="16%">NO LOT</td>
  	  <!--<td width="10%">TARIKH TERIMA</td> -->
</tr>
#set ($count = 0)
#foreach ($senaraiHakmilik in $SenaraiHakmilik)
	#set ($count = $count+1)
  #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end
   <tr class="$row">
   	<td height="20">$count</td> 
      #if($senaraiHakmilik.flagTanah == '1' || $senaraiHakmilik.flagTanah == '2' || $senaraiHakmilik.flagTanah == '5' )  
   			<td><a href="javascript:hakmilik_detail('$senaraiHakmilik.IdHakmilikurusan');" class="style1">$senaraiHakmilik.NoFail</a></td>
   	  #elseif($senaraiHakmilik.flagTanah == '10')
   			<td><a href="javascript:rizab_detail('$senaraiHakmilik.IdHakmilikurusan');" class="style1">$senaraiHakmilik.NoFail</a></td>
 	  #else  
 	  	<td>$senaraiHakmilik.NoFail</td>
   	  #end
   	   
      #if($senaraiHakmilik.flagTanah == '1' || $senaraiHakmilik.flagTanah == '2' || $senaraiHakmilik.flagTanah == '5' )  
    	<td>$senaraiHakmilik.labelNohakmilik</td>
   	  #elseif($senaraiHakmilik.flagTanah == '10')
    	<td></td>
 	  #end  
    <td>$senaraiHakmilik.noWarta</td>
    <td>$senaraiHakmilik.labelNolot</td>
    <!--<td>$senaraiHakmilik.tarikhTerima</td> -->
</tr> 
  #end
</table>
</fieldset>
<script>

//function untuk frmRekodSenaraiHakmilik
function cari(){   
	doAjaxCall${formName}("carianHakmilikRizab");
}
function hakmilik_detail(id){
	doAjaxCall${formName}("paparDetailHakmilik","idHakmilik="+id);
}
function rizab_detail(id){
	doAjaxCall${formName}("paparDetailRizab","idHakmilik="+id);
}
function kosongCarian(){
	document.${formName}.socNegeri.value = "";
	document.${formName}.socDaerah.value = "";
	document.${formName}.socMukim.value = "";
	document.${formName}.txtNoHakmilik.value = "";
	doAjaxCall${formName}("");
}
function doChangeState() {
  doAjaxCall${formName}("","action=doChangeState");
}
function doChangeDaerah() {
	doAjaxCall${formName}("","action=doChangeDaerah");
}
function doChangeMukim() {
	doAjaxCall${formName}("","action=doChangeMukim");
}

function doChangeTaraf() {
	//doAjaxCall${formName}("kemaskiniDetailHakmilik","action=doChangeTaraf");
	document.${formName}.action.value="doChangeTaraf";
	doAjaxCall${formName}("paparDetailHakmilik");
}

// function untuk frmRekodPendaftaranHakmilik
function kemaskini_detailHakmilik(id){
	doAjaxCall${formName}("kemaskiniDetailHakmilik","idHakmilik="+id);
}
function update_detailHakmilik(id){
	/*if(document.${formName}.socKategori==""){
				alert("Sila pilih \"Kategori\" terlebih dahulu.");
				document.${formName}.socKategori.focus(); 
				return;
	}*/
	//socNegeri
	if(document.${formName}.socNegeri.value == ""){ 
		alert('Sila pilih " Negeri " terlebih dahulu.'); 
		document.${formName}.socNegeri.focus();
		return; 
 	}
	if(document.${formName}.socDaerah.value == ""){ 
		alert('Sila pilih " Daerah " terlebih dahulu.'); 
		document.${formName}.socDaerah.focus();
		return; 
 	}
 	if(document.${formName}.socMukim.value == ""){ 
		alert('Sila pilih " Mukim " terlebih dahulu.'); 
		document.${formName}.socMukim.focus();
		return; 
 	} 
	if(document.${formName}.socJenisHakmilik.value == ""){ 
		alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.'); 
		document.${formName}.socJenisHakmilik.focus();
		return; 
 	}
 	if(document.${formName}.txtNoHakmilik.value == ""){ 
		alert('Sila masukkan " No Hakmilik " terlebih dahulu.'); 
		document.${formName}.txtNoHakmilik.focus();
		return; 
 	}
	if(document.${formName}.noLot.value == ""){ 
		alert('Sila pilih " Kod " terlebih dahulu.'); 
		document.${formName}.noLot.focus();
		return; 
 	}
	if(document.${formName}.txtNoLot.value == ""){ 
		alert('Sila pilih " No Lot " terlebih dahulu.'); 
		document.${formName}.txtNoLot.focus();
		return; 
 	}
 	if(document.${formName}.txdTarikhTerima.value == ""){ 
		alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
		document.${formName}.txdTarikhTerima.focus();
		return; 
 	}
 	if(document.${formName}.txdTarikhDaftar.value == ""){ 
		alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
		document.${formName}.txdTarikhDaftar.focus();
		return; 
 	}
 	if(document.${formName}.socTaraf.value == ""){ 
		alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
		document.${formName}.socTaraf.focus(); 
		return; 
	}	
 	if(document.${formName}.socTaraf.value == "P"){
 		if(document.${formName}.txtTempoh.value == ""){
			alert('Sila masukkan " Tempoh " terlebih dahulu.'); 
			document.${formName}.txtTempoh.focus(); 
			return;
		} 
 	}
 	if(document.${formName}.txtCukaiTahun.value == ""){ 
		alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
		document.${formName}.txtCukaiTahun.focus();
		return; 
 	}
 	if(document.${formName}.txtLokasi.value == ""){ 
		alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
		document.${formName}.txtLokasi.focus();
		return; 
 	}
 	if(document.${formName}.txtKegunaanTanah.value == ""){ 
		alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
		document.${formName}.txtKegunaanTanah.focus();
		return; 
 	}			
 	if(document.${formName}.socLuas.value == ""){ 
		alert('Sila masukkan " Unit Luas " terlebih dahulu.'); 
		document.${formName}.socLuas.focus();
		return; 
 	}   
 	if(document.${formName}.txtLuas.value == ""){ 
		alert('Sila masukkan " Luas " terlebih dahulu.'); 
		document.${formName}.txtLuas.focus();
		return; 
 	} 	
 	
 	if(document.${formName}.statusRizab.value == ""){ 
		alert('Sila pilih " Rizab " terlebih dahulu.'); 
		document.${formName}.statusRizab.focus();
		return; 
	 }
	 
	 if(document.${formName}.statusRizab.value == "Y"){
	 	if(document.${formName}.txtNoWarta.value == ""){
	 		alert('Sila Masukkan " No. Warta " terlebih dahulu.'); 
			document.${formName}.txtNoWarta.focus();
			return;
		}
		if(document.${formName}.txdTarikhWarta.value == ""){
	 		alert('Sila Masukkan " Tarikh Warta " terlebih dahulu.'); 
			document.${formName}.txdTarikhWarta.focus();
			return;
		}
	 } 
	 

 if(document.${formName}.socKategori.value == ""){ 
	alert('Sila masukkan " Kategori " terlebih dahulu.'); 
	document.${formName}.socKategori.focus();
	return; 
 }
 /*if(document.${formName}.txtSyarat.value == ""){ 
	alert('Sila masukkan " Syarat Nyata " terlebih dahulu.'); 
	document.${formName}.txtSyarat.focus();
	return; 
 }
 if(document.${formName}.txtSekatan.value == ""){ 
	alert('Sila masukkan " Sekatan Kepentingan " terlebih dahulu.'); 
	document.${formName}.txtSekatan.focus();
	return; 
 }
 if(document.${formName}.socStatus.value == ""){ 
	alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
	document.${formName}.socStatus.focus();
	return; 
 }        
 if ( !window.confirm("Adakah Anda Pasti ?") ){
	   return;
 }	*/
	

	doAjaxCall${formName}("updateDetailHakmilik","idHakmilik="+id);
}

function kembaliHakmilik(){
	doAjaxCall${formName}("");
}
function cetakHakmilik(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?idHakmilik="+idhakmilik;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cal_tarikh_luput(){
  var tr = document.${formName}.txdTarikhDaftar.value;
  var temp_tr = tr.substring(0,6)
  var year_cur = tr.substring(6,10)
  var tempoh = document.${formName}.txtTempoh.value; 
  var luput = temp_tr+(parseInt(year_cur)+parseInt(tempoh));
  document.${formName}.txdTarikhLuput.value = luput;
}
// function untuk frmRekodPendaftaranRizab
function kemaskini_detailRizab(id){
	doAjaxCall${formName}("kemaskiniDetailRizab","idHakmilik="+id);
}
function update_detailRizab(id){
	doAjaxCall${formName}("updateDetailRizab","idHakmilik="+id);
}
function kembaliRizab(){
	doAjaxCall${formName}("");
}

// function semua kongsi
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
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

function kira_keluasan(jenisluas,ketegori_tanah,field_luas1,field_luas2,field_luas3,field_Jumlahluas){
	//alert(jenisluas.value);
	//var val = document.getElementById(jenisluas).value;
  	//var kategory= document.getElementById(ketegori_tanah).value; // ketegory tanah
  	//var a = document.getElementById(field_luas1).value; // field luas 1
  	//var b = document.getElementById(field_luas2).value; // field luas 2
  	//var c = document.getElementById(field_luas3).value; // field luas 3
  	
	var val = jenisluas.value;
  	var kategory= ketegori_tanah.value; // ketegory tanah
  	var a = field_luas1.value; // field luas 1
  	var b = field_luas2.value; // field luas 2
  	var c = field_luas3.value; // field luas 3
  	if (val=="2"){
	  	if(a==""){		
			return ;
		}else{  
			var num = parseFloat(a) * 10000; // convert to meter persegi
			var num1 = parseFloat(a) * 1;   // convert  to hektar   	   
	        if(kategory=="2") { 
				//document.getElementById(field_Jumlahluas).value =num1;	 
				field_Jumlahluas.value =num1;	 
	        }
	        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
	        	document.getElementById(field_Jumlahluas).value =num;	    
	        }		
		}
		     
		
	}else if (val=="5"){       
	   		
		if(a==""){
			return
		} else{  
			var num = parseFloat(a) * 0.09290304; // kaki persegi to meter persegi
			var num1 = parseFloat(a) * 0.000009290304; // kaki persegi to hektar            
        	if(kategory=="2") {       
				//document.getElementById(field_Jumlahluas).value =num1;	   
 				field_Jumlahluas.value =num1;	 
        	}
        	else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;  
        	} 		 
		}
	}else if (val=="1"){ 
		
	    if(a==""){
			return
		} else{  
			var num = parseFloat(a) * 1000000;  // kilo to meter      
        	var num1 = parseFloat(a) * 100; // kilo meter to hektar        
        	if(kategory=="2") {       
				//document.getElementById(field_Jumlahluas).value =num1;    		
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory =="4" || kategory =="5" || kategory =="6") {
        		document.getElementById(field_Jumlahluas).value =num;	   
        	} 
		}
		     
		
	}else if (val=="3"){
        
		if(a==""){
			return
		}else{ 
			var num = parseFloat(a) * 1; //meter persegi to meter persegi
			var num1 = parseFloat(a) * 0.0001;  //meter persegi to hektar        
        	if(kategory=="2"){       
				//document.getElementById(field_Jumlahluas).value =num1;	    	
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 
		
		}
		     
		
	}

// 9=Batu nautika belum dapat formula
	else if (val=="9"){
       	if(a==""){
			return
		}else{         
			var num = parseFloat(a) * 1; //meter persegi to meter persegi
			var num1 = parseFloat(a) * 1;  //meter persegi to hektar
        	if(kategory=="2") {       
				//document.getElementById(field_Jumlahluas).value =num1;    	
    			field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 
		
		}
		     
		
	}
// 6=Ekar perpuluhan belum dapat formula	
	
		else if (val=="6"){
         
		if(a==""){
			return
		}else{ 
			var num = parseFloat(a) * 1; 
			var num1 = parseFloat(a) * 1;         
        	if(kategory=="2")	{       
				//document.getElementById(field_Jumlahluas).value =num1;    		
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        	document.getElementById(field_Jumlahluas).value =num;	    
        	} 		
		}		
	
	}else if (val=="7"){      
		 
		if(a==""){
			return
		}else if(b==""){			
			return
		}else{   
			var num = (parseFloat(a) + (parseFloat(b)/1000))*4046.86; 
			var num1 =  (parseFloat(a) + (parseFloat(b)/1000))*0.404686;       
        	if(kategory=="2"){       
				//document.getElementById(field_Jumlahluas).value =num1;
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")	{
        		document.getElementById(field_Jumlahluas).value =num;
        	} 		
		}
	}
	
	else if (val=="8"){
                
		if(a==""){
			return
		}else if(b==""){
			return
		}else if(c==""){
			return
		}else{ 
			var num = (parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976))*0.711111*4046.86; 
			var num1 = ((parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976)))*0.711111*0.404686;       
        	if(kategory=="2"){       
				//document.getElementById(field_Jumlahluas).value =num1;	    	
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 		
		}
		     
		
	}
	 
	else if (val=="4"){
      	if(a==""){
			return
		}else if(b==""){
			return
		}else if(c==""){
			return
		}else{
		         //Ekar Rood Pole
		         //1	2	3
				 //0.404685642	0.101171411	0.252928526
		
			var num = (parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160))*4046.86; 
			var num1 = ((parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160)))*0.404686;           
        	if(kategory=="2"){       
				//document.getElementById(field_Jumlahluas).value =num1;	    	
 				field_Jumlahluas.value =num1;	 
        	}else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6"){
        		document.getElementById(field_Jumlahluas).value =num;	    
        	} 		
		}	     
		
	}

}

function pilihLuas(){
	alert(document.${formName}.socLuas.value);
	if(document.${formName}.socLuas.value==4)
		displayFieldLuasEkar('ekarpole');
		//onclick="javascript:setTable('tableReport1')"
	//doAjaxCall${formName}("");
}

function displayFieldLuasEkar(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function semakTarikhSemasa(){
 	if(document.${formName}.txdTarikhTerima.value == ""){ 
		alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
		document.${formName}.txdTarikhTerima.focus();
		return; 
 	}
 	if(document.${formName}.txdTarikhDaftar.value == ""){ 
		alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
		document.${formName}.txdTarikhDaftar.focus();
		return; 
 	}
 		/*var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
	  
	  	if(akhir<mula){
	    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
	    	return;
	  	}
	  	if(akhir>tarikhsemasa){
	    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
	    	return;
	  	}*/
 
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
	   
		  
	     // DateField.select();		
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
	   
	      var t_semakan  = document.${formName}.txdTarikhSemakanMMK.value;
		   if(t_semakan!="")
		   {
		   var dtx   = parseInt(t_semakan.substring(0,2),10);
		   var monx  = parseInt(t_semakan.substring(3,5),10)-1;
		   var yrx   = parseInt(t_semakan.substring(6,10),10);	   
		   var datex = new Date(yrx, monx, dtx);	 
			  if (datex > date)
			  {
			 /* 
			   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh semakan "+t_semakan+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh semakan "+t_semakan+"");		  
			  
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila pilih "+value_field+"");	
		
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

function semakTarikhHariIni(txdTarikhSuratKJP) {
	var today = new Date();
	dari_bulan	= txdTarikhSuratKJP.value.substring(3,5);
	dari_hari 	= txdTarikhSuratKJP.value.substring(0,2);
	dari_tahun 	= txdTarikhSuratKJP.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		txdTarikhSuratKJP.value="";	 
  		txdTarikhSuratKJP.focus();	 
  		
 		return;
 	}

}

function semakTarikhAkhirMula(txdMula,txdAkhir,msg) {

		akhir_bulan = txdAkhir.value.substring(3,5);
  		akhir_hari = txdAkhir.value.substring(0,2);
  		akhir_tahun = txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
  		mula_bulan = txdMula.value.substring(3,5);
 		mula_hari = txdMula.value.substring(0,2);
  		mula_tahun = txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
	
		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
	  
	  	if(akhir<mula){
	    	alert("Sila pastikan "+msg);
	    	txdAkhir.value="";	 
  			txdAkhir.focus();	 
	    	return;
	  	}
 }
</script>
