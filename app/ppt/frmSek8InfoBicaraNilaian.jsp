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

<table width="100%">
  
   <tr>
<td>#parse("app/ppt/header_1_ppt.jsp") </td>
  </tr>
  <tr>
<td>#parse("app/ppt/header_siasatan.jsp") </td>



  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <!-- <li class="TabbedPanelsTab" tabindex="0"  onClick="screen5('$id_permohonan')">Kembali</li> -->
  	<!-- PPT-19 --> 
     <li class="TabbedPanelsTab" tabindex="0"  onClick="popupCarianHakmilik('$id_permohonan','senarai_siasatan')">Kembali</li>   
     
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuantanah('$id_siasatan')" id="tuan_tanah" >Keterangan Tuan Tanah</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="agensi('$id_siasatan')" id="Agensi" >Agensi / Jurunilai</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuntutan('$id_siasatan')">Tuntutan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="bantahan('$id_siasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="nilaian('$id_siasatan')"   >Nilaian JPPH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="PB('$id_siasatan')"  >Pihak Berkepentingan
    
    <!-- <font style="cursor:help" title="info"  align="center" class="font2" onMouseOut="close_window()"   onMouseOver ="open_new_window('3');this.style.cursor='help'" >
       <blink>new</blink>
       </font> -->
     
    
    </li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="status('$id_siasatan')" >Status Siasatan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="keputusan('$id_siasatan')" id="perintah_keputusan">Perintah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
 
   
    <div class="TabbedPanelsContent"></div>
   <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
   <fieldset>

#foreach($list_siasatan in $maklumat_siasatan_history)
#set($txtHargaSeunitSO = $list_siasatan.HARGA_SEUNIT_SO)
#set($txtUnitHargaSO = $list_siasatan.UNIT_HARGA_SO)
#set($txtHargaPasaranSO = $list_siasatan.HARGA_PASARAN_SO)
#set($txtPenjejasanSO = $list_siasatan.BAYAR_PENJEJASAN)
#set($txtPecahPisahSO = $list_siasatan.BAYAR_PECAHPISAH)
#set($txtNaikNilaiSO = $list_siasatan.BAYAR_NAIK_NILAISO)
#set($txtHargaSeunitJPPH = $list_siasatan.HARGA_SEUNIT_JPPH)
#set($txtUnitHargaJPPH = $list_siasatan.UNIT_HARGA)
#set($txtHargaPasaranJPPH = $list_siasatan.HARGA_PASARAN)
#set($txtPenjejasanJPPH = $list_siasatan.AMAUN_PENJEJASAN_JPPH)
#set($txtPecahPisahJPPH = $list_siasatan.AMAUN_PECAHPISAH_JPPH)
#set($txtNaikNilaiJPPH = $list_siasatan.NAIK_NILAI_JPPH)
#set($txtStrukturBangunan = $list_siasatan.STRUKTUR_BANGUNAN)
#set($txtFlagTugasan = $list_siasatan.FLAG_TUGASAN)
#end
   <table width="100%">
  <tr>
  <td>
   <fieldset>
    <legend>Nilaian JPPH</legend>
    <table width="100%">
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="38%">Harga Seunit (RM)</td>
        <td width="1%">:</td>
        <td width="70%">
		    #if($readmode == "view")
			    #if($txtHargaSeunitJPPH != "")
			    	#set($txtHargaSeunitJPPH = $Util.formatDecimal($txtHargaSeunitJPPH))
			    #else
			    	#set($txtHargaSeunitJPPH = "")
			    #end
		    #else
		    	#set($txtHargaSeunitJPPH = $txtHargaSeunitJPPH) 
		    #end
         	<input name="txtHargaSeunitJPPH" type="text" id="txtHargaSeunitJPPH" size="15" maxlength="150" style="text-align:right"  value="$!txtHargaSeunitJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtHargaSeunitJPPH_check','no','harga seunit','normal');validateModal(this,this.value,'$txtHargaSeunitJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtHargaSeunitJPPH_check','no','harga seunit','normal');" $readonlymode class = "$disabledmode" />
  			<span id="txtHargaSeunitJPPH_check" style="color:red" ></span>  
  
		  	per #if($readmode == "view" ) 
		     #if($txtUnitHargaJPPH=="")
		     	#set($UnitHargaJPPH="")                             
		     #else
		     	#if($txtUnitHargaJPPH=="2")
		     		#set($UnitHargaJPPH="METER PERSEGI")    
		     	#elseif($txtUnitHargaJPPH=="1") 
		     		#set($UnitHargaJPPH="HEKTAR")    
		     	#elseif($txtUnitHargaJPPH=="3") 
		     		#set($UnitHargaJPPH="EKAR")    
		     	#elseif($txtUnitHargaJPPH=="4") 
		     		#set($UnitHargaJPPH="KAKI PERSEGI")                  
		     	#end 
		     #end
     		<input name="txtUnitHargaJPPH" type="text" id="txtUnitHargaJPPH" value="$UnitHargaJPPH" size="15" readonly class="disabled" >              
    		#else    
				<select $disOtherId1 $disOtherIdx name="txtUnitHargaJPPH" style="width:150px">
				     #if($txtUnitHargaJPPH=="2")
				     <option value="2">METER PERSEGI</option>		
				      					<option value="1">HEKTAR</option>	
				      					<option value="3">EKAR</option>
				      					<option value="4">KAKI PERSEGI</option>  
				                         <option value="">SILA PILIH</option>             
				     #elseif($txtUnitHargaJPPH=="1") 
				     <option value="1">HEKTAR</option>	
				      					<option value="3">EKAR</option>
				      					<option value="4">KAKI PERSEGI</option>
				                        <option value="2">METER PERSEGI</option>
				                         <option value="">SILA PILIH</option> 
				     #elseif($txtUnitHargaJPPH=="3") 
				     <option value="3">EKAR</option>
				      					<option value="4">KAKI PERSEGI</option> 
				                        <option value="2">METER PERSEGI</option>		
				      					<option value="1">HEKTAR</option>
				                        <option value="">SILA PILIH</option>	   
				     #elseif($txtUnitHargaJPPH=="4") 
				      					<option value="4">KAKI PERSEGI</option> 
				                        <option value="3">EKAR</option>
				                        <option value="2">METER PERSEGI</option>		
				      					<option value="1">HEKTAR</option>
				                        <option value="">SILA PILIH</option>	   
				     #else
				       <option value="">SILA PILIH</option>    			
				      					<option value="2">METER PERSEGI</option>		
				      					<option value="1">HEKTAR</option>	
				      					<option value="3">EKAR</option>
				      					<option value="4">KAKI PERSEGI</option>             
				     #end 
				</select>
			#end  
			</td>
      </tr>
      
      <tr>
        <td>&nbsp;</td>
        <td>Harga Pasaran  (RM)</td>
        <td>:</td>
        <td>
        
             #if($readmode == "view")
    #if($txtHargaPasaranJPPH != "")
    #set($txtHargaPasaranJPPH = $Util.formatDecimal($txtHargaPasaranJPPH))
    #else
    #set($txtHargaPasaranJPPH = "")
    #end
    #else
    #set($txtHargaPasaranJPPH = $txtHargaPasaranJPPH) 
    #end
        
        <input name="txtHargaPasaranJPPH" type="text" id="txtHargaPasaranJPPH" size="15" maxlength="150" style="text-align:right"  value="$!txtHargaPasaranJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtHargaPasaranJPPH_check','no','harga pasaran','normal');validateModal(this,this.value,'$txtHargaPasaranJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtHargaPasaranJPPH_check','no','harga pasaran','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtHargaPasaranJPPH_check" style="color:red" ></span> </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Penjejasan Terbabit  (RM)</td>
        <td>:</td>
        <td>
                #if($readmode == "view")
    #if($txtPenjejasanJPPH != "")
    #set($txtPenjejasanJPPH = $Util.formatDecimal($txtPenjejasanJPPH))
    #else
    #set($txtPenjejasanJPPH = "")
    #end
    #else
    #set($txtPenjejasanJPPH = $txtPenjejasanJPPH) 
    #end
        
        <input name="txtPenjejasanJPPH" type="text" id="txtPenjejasanJPPH" size="15" maxlength="150"  style="text-align:right" value="$!txtPenjejasanJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtPenjejasanJPPH_check','no','penjejasan terbabit','normal');validateModal(this,this.value,'$txtPenjejasanJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtPenjejasanJPPH_check','no','penjejasan terbabit','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtPenjejasanJPPH_check" style="color:red" ></span> </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Pecahpisah  (RM)</td>
        <td>:</td>
        <td>
        
                      #if($readmode == "view")
    #if($txtPecahPisahJPPH != "")
    #set($txtPecahPisahJPPH = $Util.formatDecimal($txtPecahPisahJPPH))
    #else
    #set($txtPecahPisahJPPH = "")
    #end
    #else
    #set($txtPecahPisahJPPH = $txtPecahPisahJPPH) 
    #end
        <input name="txtPecahPisahJPPH" type="text" id="txtPecahPisahJPPH" size="15" maxlength="150"  style="text-align:right" value="$!txtPecahPisahJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtPecahPisahJPPH_check','no','pecahpisah','normal');validateModal(this,this.value,'$txtPecahPisahJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtPecahPisahJPPH_check','no','pecahpisah','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtPecahPisahJPPH_check" style="color:red" ></span> </td>
      </tr>
       <tr>
        <td width="1%">&nbsp;</td>
        <td width="38%">Kenaikan Nilai  (RM)</td>
        <td width="1%">:</td>
        <td width="70%">
      
        #if($readmode == "view")
    #if($txtNaikNilaiJPPH != "")
    #set($txtNaikNilaiJPPH = $Util.formatDecimal($txtNaikNilaiJPPH))
    #else
    #set($txtNaikNilaiJPPH = "")
    #end
    #else
    #set($txtNaikNilaiJPPH = $txtNaikNilaiJPPH) 
    #end
        
        <input name="txtNaikNilaiJPPH" type="text" id="txtNaikNilaiJPPH" size="15" maxlength="150" style="text-align:right"  value="$!txtNaikNilaiJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtNaikNilaiJPPH_check','no','kenaikan nilai','normal');validateModal(this,this.value,'$txtNaikNilaiJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNaikNilaiJPPH_check','no','kenaikan nilai','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtNaikNilaiJPPH_check" style="color:red" ></span> </td>
      </tr>
      
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="38%">Struktur Bangunan  (RM)</td>
        <td width="1%">:</td>
        <td width="70%">
      
        #if($readmode == "view")
    #if($txtStrukturBangunan != "")
    #set($txtStrukturBangunan = $Util.formatDecimal($txtStrukturBangunan))
    #else
    #set($txtStrukturBangunan = "")
    #end
    #else
    #set($txtStrukturBangunan = $txtStrukturBangunan) 
    #end
        
        <input name="txtStrukturBangunan" type="text" id="txtStrukturBangunan" size="15" maxlength="150" style="text-align:right"  value="$!txtStrukturBangunan" onblur="validateTarikh(this,this.value);checking_validation(this,'txtStrukturBangunan_check','no','kenaikan nilai','normal');validateModal(this,this.value,'$txtStrukturBangunan');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtStrukturBangunan_check','no','kenaikan nilai','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtStrukturBangunan_check" style="color:red" ></span> </td>
      </tr>
      
    </table>
    </fieldset>
  </td>
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
      	#if($txtFlagTugasan == '')
		<label>
      		<input type="button" name=cmdHantar id="cmdHantar" value="Hantar ke JPPH"  onClick="hantarJPPH('$id_siasatan')">
    	</label>  
    	#elseif($txtFlagTugasan == 'H' || $txtFlagTugasan == 'K')
		<label>
      		<input type="button" name=cmdHantar id="cmdHantar" value="Tunggu Maklumbalas JPPH"  onClick="maklumbalasJPPH('$id_siasatan')" disabled="disabled">
    	</label>  
    	#end
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
   <!--   <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  -->
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
  </div>
</div>

</td>
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

		<!--tr>
			<td width="1%"></td>
			<td colspan="3">
				<div align="left"><a href="javascript:maklumatsiasatan('$id_siasatan')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">NOTA SIASATAN </font></a></div>
			</td>
		</tr-->
	</table>
</fieldset>
<!-- END PPT-24   -->


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
  <input type="hidden" name="id_tanah" id="id_tanah" value="$!id_tanah" />
  
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:5});
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

check_length(document.${formName}.txtKeteranganAgensi,'4000','txtKeteranganJurunilai_check','txtKeteranganJurunilai_num','normal','no','keterangan kos-kos yang ditanggung');
check_length(document.${formName}.txtKeteranganJurunilai,'4000','txtKeteranganAgensi_check','txtKeteranganAgensi_num','normal','no','keterangan kerosakan ke atas tanah');


/*
checking_validation(document.${formName}.txtHargaSeunitSO,'txtHargaSeunitSO_check','yes','harga seunit','normal');
checking_validation(document.${formName}.txtUnitHargaSO,'txtUnitHargaSO_check','yes','unit harga','radio');
checking_validation(document.${formName}.txtHargaPasaranSO,'txtHargaPasaranSO_check','yes','harga pasaran','normal');
checking_validation(document.${formName}.txtPecahPisahSO,'txtPecahPisahSO_check','yes','pecahpisah','normal')
checking_validation(document.${formName}.txtPenjejasanSO,'txtPenjejasanSO_check','yes','penjejasan terbabit','normal');
checking_validation(document.${formName}.txtNaikNilaiSO,'txtNaikNilaiSO_check','yes','kenaikan nilai','normal');
checking_validation(document.${formName}.txtHargaSeunitJPPH,'txtHargaSeunitJPPH_check','yes','harga seunit','normal');
checking_validation(document.${formName}.txtUnitHargaJPPH,'txtUnitHargaJPPH_check','yes','unit harga','radio');
checking_validation(document.${formName}.txtHargaPasaranJPPH,'txtHargaPasaranJPPH_check','yes','harga pasaran','normal');
checking_validation(document.${formName}.txtPecahPisahJPPH,'txtPecahPisahJPPH_check','yes','pecahpisah','normal')
checking_validation(document.${formName}.txtPenjejasanJPPH,'txtPenjejasanJPPH_check','yes','penjejasan terbabit','normal');
checking_validation(document.${formName}.txtNaikNilaiJPPH,'txtNaikNilaiJPPH_check','yes','kenaikan nilai','normal');
*/
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
	   
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
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
	   {
	   /*
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
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
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");		
	
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
		   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
		   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan dokumen terlebih dahulu");		
	
	    }
		else
		{/*
		   document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		
		*/
		$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");		
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

function simpan(id_siasatan){
	var c = 0;
	
	if(document.${formName}.validation_field != null  ){
	   if (document.${formName}.validation_field.length == null){	
		   if(document.${formName}.validation_field.value == "invalid") {  
		   	c++;	
		   } 
	   } else {
	        for (i = 0; i < document.${formName}.validation_field.length; i++){		
				if(document.${formName}.validation_field[i].value == "invalid") {
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
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "Agensi";	
	document.${formName}.submit();
	}
}
}

function hantarJPPH(id_siasatan) {
	
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "hantarJPPH";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
// 	document.${formName}.point.value = "maklumat_siasatan";	
	document.${formName}.submit();
}

function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
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
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtHargaSeunitSO";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}

function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtHargaSeunitSO";
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

function maklumbalasJPPH(id_siasatan)
{
		alert('Tunggu Maklumbalas JPPH ye. Hehehe.');
//     document.${formName}.command.value = "Siasatan";
// 	document.${formName}.sub_command.value = "Senarai";
// 	document.${formName}.subminor_command.value = "View";
// 	document.${formName}.location.value = "paging";
// 	document.${formName}.point.value = "paging";	
// 	document.${formName}.id_permohonan.value = id_permohonan;
// 	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
// 	document.${formName}.submit();
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
  
