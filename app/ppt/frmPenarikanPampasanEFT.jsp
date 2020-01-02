  

#parse("app/ppt/paging_penarikanbalik.jsp")
 <!-- id_pembatalan = id_penarikanbalik -->
                                 
                
         
            #set($id_bayaran = "")
            #set($txtNoRujSurat = "")
            #set($txdTarikhSurat = "")
            #set($txdTarikhTerimaSurat = "")
            #set($txtNamaKementerian = "")
            #set($txtNoEFT = "")
            #set($txtAmaunEFT = "")
            #set($txdTarikh = "")      
            #set($txtUlasanBayaran = "")   
            
            
            #foreach ( $senarai in $maklumat_details_bayaran )
            
            
            #set($id_bayaran = $senarai.ID_BAYARAN)
            #set($txtNoRujSurat = $senarai.NO_RUJUKAN_SURATEFT)
            #set($txdTarikhSurat = $senarai.TARIKH_SURAT_EFT)
            #set($txdTarikhTerimaSurat = $senarai.TARIKH_TERIMA_EFT)
            #set($txtNamaKementerian = $senarai.NAMA_KEMENTERIAN)
            #set($txtNoEFT = $senarai.NO_EFT)
            #set($txtAmaunEFT = $senarai.AMAUN_BAYARAN)
            #set($txdTarikh = $senarai.TARIKH_BAYARAN_EFT)   
            #set($txtUlasanBayaran = $senarai.ULUSAN)     
            
                  
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
    <td> #parse("app/ppt/header_pampasan_penarikanbalik.jsp")</td>
  </tr>
 
  <tr>
    <td>
    <fieldset>
    <table width="100%">
    <tr>
    <td>
    
    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab" tabindex="0" id="senarai" onClick="kembali_senarai('$id_permohonan',' $id_pembatalan')">Senarai PB</li>
       #if($close_tuntutan != "yes")     <li class="TabbedPanelsTab" tabindex="0" id="tuntutan" style="display:none" onClick="Tuntutan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')">Tuntutan</li>#end
 #if($close_nilaian != "yes")         <li class="TabbedPanelsTab" tabindex="0"  id="Nilaian" style="display:none"  onClick="Nilaian('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Nilaian / Maklumat Pampasan</li>   #end   
        <li class="TabbedPanelsTab" tabindex="0" id="Penerimaan_Cek" onClick="Penerimaan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Penerimaan Cek</li>
        <li class="TabbedPanelsTab" tabindex="0"  id="Penyerahan_Cek" onClick="Penyerahan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Penyerahan Cek</li>
        <li class="TabbedPanelsTab" tabindex="0"  id="Bayaran_Melalui_EFT" onClick="EFT('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Bayaran Melalui EFT</li>
      </ul>
      
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">       
        </div>
          #if($close_tuntutan != "yes")    <div class="TabbedPanelsContent"></div> #end
    #if($close_nilaian != "yes")     <div class="TabbedPanelsContent"></div> #end
         <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent">
        
        
        <table width="100%">
  #if($screen_kemasukan == "yes")
  <tr>
  <td colspan="2" >
  <fieldset>
  <legend>Maklumat Penerimaan Cek</legend>
  <table width="100%">
  <tr>
    <td width="100%" valign="top">
    <table width="100%">
    
    <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Nama Kementerian</td>
    <td width="1%">:</td>
    <td width="50%">
    $NAMA_KEMENTERIAN
    
       <input name="txtNamaKementerian" type="hidden" id="txtNamaKementerian" size="50" maxlength="50"   value=" $NAMA_KEMENTERIAN" onblur="validateTarikh(this,this.value);checking_validation(this,'txtNamaKementerian_check','yes','nama kementerian','normal');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNamaKementerian_check','yes','nama kementerian','normal');" $readonly class = "disabled" />    
<!--       <span id="txtNamaKementerian_check" style="color:red" ></span>  -->      </td>
    <td width="20%">&nbsp;</td>
  </tr>
    
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Rujukan Surat Kementerian</td>
    <td width="1%">:</td>
    <td width="70%" colspan="2">
      <input name="txtNoRujSurat" type="text" id="txtNoRujSurat" size="50" maxlength="50"   value="$!txtNoRujSurat" onblur="checking_validation(this,'txtNoRujSurat_check','yes','no rujukan surat EFT','normal');" onKeyUp="checking_validation(this,'txtNoRujSurat_check','yes','no rujukan surat EFT','normal');" $readonlymode class = "$disabledmode" />    
       <!-- <span id="txtNoRujSurat_check" class = "alert_msg" ></span> --> </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Surat Kementerian</td>
    <td>:</td>
    <td colspan="2">
    
      
    <input name="txdTarikhSurat" type="text" id="txdTarikhSurat" size="10" maxlength="10"   value="$!txdTarikhSurat" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhSurat_check','yes','surat EFT','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
      #if($readmode == "edit")      
     <a href="javascript:displayDatePicker('txdTarikhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
        <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>         
      #end 
       <!-- <span id="txdTarikhSurat_check" class = "alert_msg" ></span> -->    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Terima Surat Kementerian</td>
    <td>:</td>
    <td colspan="2">
    
      
    <input name="txdTarikhTerimaSurat" type="text" id="txdTarikhTerimaSurat" size="10" maxlength="10"   value="$!txdTarikhTerimaSurat" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhTerimaSurat_check','yes','terima surat EFT','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
      #if($readmode == "edit")      
     <a href="javascript:displayDatePicker('txdTarikhTerimaSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> 
        <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>        
      #end 
       <!-- <span id="txdTarikhTerimaSurat_check" class = "alert_msg" ></span> --></td>
  </tr>

  
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>No. EFT</td>
    <td>:</td>
    <td colspan="2">
    
       <input name="txtNoEFT" type="text" id="txtNoEFT" size="50" maxlength="50"   value="$!txtNoEFT" onblur="checking_validation(this,'txtNoEFT_check','yes','no EFT','normal');" onKeyUp="checking_validation(this,'txtNoEFT_check','yes','no EFT','normal');" $readonlymode class = "$disabledmode" />    
       <span id="txtNoEFT_check" class = "alert_msg" ></span>    </td>
    
    <!--
  
                      $Util.formatDecimal($BAYAR_PAMPASAN)
                      
    -->
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Amaun (RM)</td>
    <td>:</td>
    <td colspan="2">
    
     
    #if($readmode == "view")
    #if($txtAmaunEFT != "")
    #set($txtAmaunEFT = $Util.formatDecimal($txtAmaunEFT))
    #else
    #set($txtAmaunEFT = "")
    #end
    #else
    #set($txtAmaunEFT = $txtAmaunEFT) 
    #end
   
   
   
   
 
    <input name="txtAmaunEFT" type="text" id="txtAmaunEFT" size="15" maxlength="150"   value="$!txtAmaunEFT" onblur="validateTarikh(this,this.value);checking_validation_nilai(this,'txtAmaunEFT_check','yes','amaun EFT','amaun','$id_bayaran');validateModal(this,this.value,'$txtAmaunEFT');" onKeyUp="validateTarikh(this,this.value);checking_validation_nilai(this,'txtAmaunEFT_check','yes','amaun EFT','amaun','$id_bayaran');" $readonlymode class = "$disabledmode" />
  <span id="txtAmaunEFT_check" class = "alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh</td>
    <td>:</td>
    <td colspan="2">
    
      
    <input name="txdTarikh" type="text" id="txdTarikh" size="10" maxlength="10"   value="$!txdTarikh" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikh_check','no','','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
      #if($readmode == "edit")      
     <a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>  
        <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>       
      #end 
       <span id="txdTarikh_check" class = "alert_msg" ></span>    </td>
  </tr>
  <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Ulasan Bayaran</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
               
          
           <textarea name="txtUlasanBayaran" id="txtUlasanBayaran" cols="60"   rows="6"         
         onBlur="check_length(this,'400','txtUlasanBayaran_check','txtUlasanBayaran_num','normal','no','ulasan bayaran');"  
         onKeyup="check_length(this,'400','txtUlasanBayaran_check','txtUlasanBayaran_num','normal','no','ulasan bayaran');" 
         onKeydown="check_length(this,'400','txtUlasanBayaran_check','txtUlasanBayaran_num','normal','no','ulasan bayaran');"                    
          $readonlymode class = "$disabledmode" 
        >$txtUlasanBayaran</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtUlasanBayaran_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtUlasanBayaran_num" id="txtUlasanBayaran_num" size="3" value="400"  style=" display:none" > 
         #end
  <div id="txtUlasanBayaran_check" class="alert_msg" ></div> 
                </td>
      </tr>
</table>
    
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
      <label></label>    
      #end
      
       
      
     </div>      </td>
  </tr>
  </table>
  </fieldset>
  
  </td>
  </tr>
  #end
   #if($readmode == "edit")
  <tr>
    <td colspan="2">#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td colspan="2"></td>
  </tr>  
  #end
  
  <tr>
   <td colspan="2">
   <fieldset>
   <legend>Senarai Penerimaan Bayaran Melalui EFT</legend>
   <table width="100%">
   
   <tr>
  <td width="left"  colspan="7">
   
    <label>      
        <input type="button" name="cmdCetak" id="cmdCetak" value="Tambah" onClick="tambah('$id_permohonan',' $id_pembatalan','$id_pb')" >        
   </label>
   #if($maklumat_bayaran_bp.size()>0)
   <label>
        <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus_beramai()">
   </label>
   #end
   </td>
  </tr>
   
   
  <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="50%">No. EFT</td>    
    <td width="20%"><div align="center">Tarikh</div></td>
    <td width="20%"><div align="right">Amaun (RM)</div></td>
  
     #if($maklumat_bayaran_bp.size()>0)
    <td width="5%">
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1();" title="Semak untuk pilih semua" />
      </div>
    </td>
    #end
  </tr>
  
  
  #if($maklumat_bayaran_bp.size()!=0)
  #foreach($list in $maklumat_bayaran_bp)            
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
              
  
  
  <tr class="$row">
    <td>$list.BIL</td>
    <td>
    <a href="javascript:papar('$list.ID_BAYARAN')" title="Maklumat Penerimaan Cek"><font color="blue">$list.NO_EFT</font></a>   
    
    </td>
    
    <td><div align="center">$list.TARIKH_BAYARAN_EFT</div></td>
    
    <td align="right">
    #if($list.AMAUN_BAYARAN != "")
       $Util.formatDecimal($list.AMAUN_BAYARAN)
    #end
    </td>
   <td>
    <div align="center">
       <input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list.ID_BAYARAN" >
     </div>
    </td>
  </tr>
    #end
  
  #else
   <tr>
    <td colspan="7">  Tiada Rekod  </td>
    
  </tr>
  
  #end
  
</table>
   </fieldset>

   </td>
  </tr>
  
  
</table>
        
        
        </div>
         
      </div>
    </div>
    </td>
    </tr>
    </table>
    </fieldset>
    </td>
  </tr>
  
  
</table>

#if($maklumat_bayaran_semua.size()!=0)
  #foreach($list in $maklumat_bayaran_semua)
  <input type="hidden" name="id_nilai" id="id_nilai" value="$list.ID_BAYARAN" > 
  <input type="hidden" name="nilai" id="nilai" value="$list.AMAUN_BAYARAN" />
  #end
 #end 

  <input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  <input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$id_pembatalan" />
  <input type="hidden" name="screen_name" id="screen_name" value="s2" />
  <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
  <input type="hidden" name="alert_message" id="alert_message" />
  <input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
  <input type="hidden" name="jumlah_semua" id="jumlah_semua" />
  <input type="hidden" name="id_mmk" id="id_mmk" value="$!id_mmk" />
  <input type="hidden" name="id_pb" id="id_pb" value="$!id_pb" />
  <input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb" />
  <input type="hidden" name="id_bayaran" id="id_bayaran" value="$!id_bayaran" />
  <input type="hidden" name="cara_bayar" id="cara_bayar" value="2" />
  <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" />


<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$tab_index});

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

if(readmode == "edit")
{
//checking_validation(document.${formName}.txtNoRujSurat,'txtNoRujSurat_check','yes','no rujukan surat EFT','normal');
//checking_validation(document.${formName}.txdTarikhSurat,'txdTarikhSurat_check','yes','surat EFT','tarikh');
//checking_validation(document.${formName}.txdTarikhTerimaSurat,'txdTarikhTerimaSurat_check','yes','terima surat EFT','tarikh');
checking_validation(document.${formName}.txtNoEFT,'txtNoEFT_check','yes','no EFT','normal');
//checking_validation(document.${formName}.txtAmaunEFT,'txtAmaunEFT_check','yes','amaun EFT','amaun');
checking_validation(document.${formName}.txdTarikh,'txdTarikh_check','no','','tarikh');
checking_validation_nilai(document.${formName}.txtAmaunEFT,'txtAmaunEFT_check','yes','amaun EFT','amaun','$id_bayaran');
check_length(document.${formName}.txtUlasanBayaran,'400','txtUlasanBayaran_check','txtUlasanBayaran_num','normal','no','ulasan bayaran')
}

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
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "Penerimaan_Cek";
	document.${formName}.submit();
	}
	}
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "Penerimaan_Cek";
	document.${formName}.submit();
	}
}

function hapus_beramai()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "Hapus_beramai";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "Penerimaan_Cek";
	document.${formName}.submit();
	}
}



function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "txtNoRujSurat";
	document.${formName}.submit();

	}
}

function kemaskini()
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "txtNoRujSurat";
	document.${formName}.submit();


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
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >"); 
	   }
	   else
	   {	   
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	 
	   }
	   else
	   {/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
	 
	   }
	   
	   	   
	   }
	   
	   
	   
	    
	   
	   
	
}


			
function kembali_senarai(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "SenaraiPB";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}



function tambah(id_permohonan,id_pembatalan,id_pb)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "Tambah";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "txtNoRujSurat";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_pb.value = id_pb;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function papar(id_bayaran)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "papar";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "Penerimaan_Cek";	
	document.${formName}.id_bayaran.value = id_bayaran;
	
	document.${formName}.action = "";
	document.${formName}.submit();
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



function Tuntutan(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function Nilaian(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function Penerimaan(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Penerimaan_Check";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}


function Penyerahan(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Penyerahan_Check";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function EFT(id_permohonan,id_pembatalan,id_hakmilikpb)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "EFT";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "";
	document.${formName}.submit();

}


function check_nilai(jumlah_terima){  
var jumlah_pampasan = document.${formName}.txtJumlahPampasan.value;
var jumlahsub_pampasan = document.${formName}.txtJumlahSubPampasan.value;
var txtAmaunCek = document.${formName}.txtAmaunEFT.value;
var jumlah = 0;
if(jumlah_pampasan == "" || jumlah_pampasan == null)
{
jumlah = 0;
}
else
{
jumlah = parseFloat(jumlah_pampasan);
}


var jumlahsub = 0;
if(jumlahsub_pampasan == "" || jumlahsub_pampasan == null)
{
jumlahsub = 0;
}
else
{
jumlahsub = parseFloat(jumlahsub_pampasan);
}


var amaunt = 0;
if(txtAmaunCek == "" || txtAmaunCek == null)
{
amaunt = 0;
}
else
{
amaunt = parseFloat(txtAmaunCek);
}

if(jumlah_terima > 0)
{
var total_nilai = 0;
        if (document.${formName}.nilai.length == null)
		{
				if(document.${formName}.nilai.value != "")
				{
				   total_nilai = total_nilai + parseFloat(document.${formName}.nilai.value) ;		   
				}
				else
				{
				   total_nilai = total_nilai;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.nilai.length; i++)
			{
               if(document.${formName}.nilai[i].value != "")
				{
				   total_nilai = total_nilai + parseFloat(document.${formName}.nilai[i].value) ;		   
				}
				else
				{
				   total_nilai = total_nilai;
				}
            }
        }
		
		total_nilai = total_nilai + amaunt;
		
		if(total_nilai > (jumlah+jumlahsub))
		{
		 $jquery("#txtAmaunEFT_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan amaun tidak melebihi jumlah pampasan ");
		}
		else
		{
		 $jquery("#txtAmaunEFT_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
		
		}
		
		
}  
else
{

if(amaunt > (jumlah+jumlahsub))
		{
		 $jquery("#txtAmaunEFT_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan amaun tidak melebihi jumlah pampasan");
		}
		else
		{
		 $jquery("#txtAmaunEFT_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
		
		}
}
}










function checking_validation_nilai(field,point,mandatory,value_field,jenis_field,id_bayaran){	


	   if(jenis_field == "amaun")
	   {
	   	
	   if(field.value == "" && mandatory == "yes")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > "); 	 
	   }  	  
	   else
	   {	   
	
	
var jumlah_terima = '$maklumat_bayaran_semua.size()';	
var jumlah_pampasan = document.${formName}.txtJumlahPampasan.value;
var jumlah_subpampasan =  document.${formName}.txtJumlahSubPampasan.value;
var txtAmaunCek = document.${formName}.txtAmaunEFT.value;
var jumlah = 0;
var jumlahsub = 0;




if(jumlah_pampasan == "" || jumlah_pampasan == null)
{
jumlah = 0;
}
else
{
jumlah = parseFloat(jumlah_pampasan);
}

if(jumlah_subpampasan == "" || jumlah_subpampasan == null)
{
jumlahsub = 0;
}
else
{
jumlahsub = parseFloat(jumlah_subpampasan);
}

var amaunt = 0;
if(txtAmaunCek == "" || txtAmaunCek == null)
{
amaunt = 0;
}
else
{
amaunt = parseFloat(txtAmaunCek);
}

if(jumlah_terima > 0)
{

var total_nilai = 0;
        if (document.${formName}.nilai.length == null)
		{
				if(document.${formName}.nilai.value != "")
				{
				   if(id_bayaran != "" && id_bayaran == document.${formName}.id_nilai.value)
				   {
				   total_nilai = total_nilai + 0 ;	
				   }
				   else
				   {
				   total_nilai = total_nilai + parseFloat(document.${formName}.nilai.value) ;	
				   }	   
				}
				else
				{
				   total_nilai = total_nilai;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.nilai.length; i++)
			{
               if(document.${formName}.nilai[i].value != "")
				{
				  
				   if(id_bayaran != "" && id_bayaran == document.${formName}.id_nilai[i].value)
				   {
				   total_nilai = total_nilai + 0 ;	
				   }
				   else
				   {
				   total_nilai = total_nilai + parseFloat(document.${formName}.nilai[i].value) ;	
				   }	   	   
				}
				else
				{
				   total_nilai = total_nilai;
				}
            }
        }
		
		total_nilai = total_nilai + amaunt;
		
		if(total_nilai > (jumlah + jumlahsub))
		{
		 $jquery("#txtAmaunEFT_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan amaun tidak melebihi jumlah pampasan ");
		}
		else
		{
		 $jquery("#txtAmaunEFT_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
		
		}
		
		
}  
else
{

if(amaunt > (jumlah + jumlahsub))
		{
		 $jquery("#txtAmaunEFT_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan amaun tidak melebihi jumlah pampasan");
		}
		else
		{
		if(field.value == "" && mandatory == "yes")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+""); 	  
	   } 	   
	   else
	   {	   
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");		 
	   }
		
		}
        }	 
	  
	    
	   }
	   	   
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

  </script>
