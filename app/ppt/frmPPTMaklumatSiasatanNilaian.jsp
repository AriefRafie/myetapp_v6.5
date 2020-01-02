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

#set($flagJPPH = 'yes')
<table width="100%">
	 <tr>
		<td>#parse("app/ppt/header_1_ppt.jsp") </td>
	 </tr>
	 <tr>
		<td>#parse("app/ppt/frmPPTMaklumatJPBD.jsp") </td>
	 </tr>	 
  <tr>
		<td>#parse("app/ppt/header_siasatan.jsp") </td>
  </tr>
<tr>
<td>
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
#end
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
		    <input name="txtUnitHargaJPPH" type="text" id="txtUnitHargaJPPH" value="$!UnitHargaJPPH" size="15" readonly class="disabled" >              
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
	  		<span id="txtHargaPasaranJPPH_check" style="color:red" ></span> 
  		</td>
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
		  		<span id="txtPenjejasanJPPH_check" style="color:red" ></span> 
		</td>
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
	  		<span id="txtNaikNilaiJPPH_check" style="color:red" ></span> 
  		</td>
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
	  		<span id="txtStrukturBangunan_check" style="color:red" ></span>
  		</td>
      </tr>
    </table>
    </fieldset>
  
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      	<input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini('$id_siasatan')">
      </label>
   	<label>
      	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Hantar ke JKPTG"  onClick="hantarJKPTG('$id_siasatan')">
    </label>          
	<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">    
      <label>
      	<input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan('$id_siasatan')">
      </label>
      	<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembaliList('$id_siasatan')">  
<!--       <label> -->
<!--       	<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_siasatan')"> -->
<!--       </label> -->
    #end  
<!--    	<label> -->
<!--       	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan('$id_siasatan')"> -->
<!--     </label> -->

       #if($id_pembatalan != "")
       #if($readmode == "view")
        <label></label>
        <label> 
      </label>   
      #end 
      #end
       <label></label>
     </div>      
</td>
</tr>
</table>

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
  <input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
  <input type="hidden" name="actionOnline" id="actionOnline" /> 
  <input type="hidden" name="nextPage" id="nextPage" value="$nextPage"/>
  <input type="hidden" name="idHakmilik" id="idHakmilik" value="$!idHakmilik"/>
  
  
<script type="text/javascript">
var readmode = '$readmode';

function simpan(id_siasatan){
	var c = 0;
	
	if(document.${formName}.validation_field != null  ){
	   if (document.${formName}.validation_field.length == null){	
		   if(document.${formName}.validation_field.value == "invalid"){  
		   		c++;	
		   } 
	   } else  {
	        for (i = 0; i < document.${formName}.validation_field.length; i++) {		
				if(document.${formName}.validation_field[i].value == "invalid") {
	               	c++;	 
				}  	
	        }
	    }	
	}

	if(c>0){
		alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
		return;
	} else {
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.id_siasatan.value = id_siasatan;	
			document.${formName}.readmode.value = 'view';
			document.${formName}.actionOnline.value = 'simpan';
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiTugasanOnline";
			document.${formName}.submit();
		}
	}
}

function kemaskini(id_siasatan) {
	document.${formName}.readmode.value = 'edit';
	document.${formName}.actionOnline.value = "kemaskini";	
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiTugasanOnline";
	document.${formName}.submit();
}

function hantarJKPTG(id_siasatan) {
	document.${formName}.actionOnline.value = "hantarJKPTG";	
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiTugasanOnline";
	document.${formName}.submit();
}

function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiTugasanOnline";
	document.${formName}.submit();
}

function kembaliList(id_siasatan) {
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.readmode.value = 'view';
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiTugasanOnline";
	document.${formName}.submit();
}

function hapus() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiTugasanOnline";
	document.${formName}.submit();
}
</script>