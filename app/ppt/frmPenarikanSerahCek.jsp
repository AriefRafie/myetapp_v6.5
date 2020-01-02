



#parse("app/ppt/paging_penarikanbalik.jsp")
 
         
            #set($txtwakil = $NAMA_PB)
            #set($txtNoKP = $NO_PB)
            #set($txdTarikhSerahCek = "")
            #set($socStatusPenyerahan = "")
            #set($id_bayaran = "") 
            
            #set($txdTarikhTerimaCek = "")
            #set($txtpenamacek = "")
            #set($txtnocek = "")
            #set($txdTarikhCek = "")
            #set($txtAmaunCek = "")
            #set($txdTarikhAkhirCek = "")      
            #set($jeniswaktu = "")
            #set($txtMasaSiasatan = "")
            #set($txdTarikhSerahCek = "")
            #set($txtTempat = "")   
           
                    
           
            
            #foreach ( $senarai in $maklumat_details_bayaran )
            
            #if($senarai.NAMA_WAKIL == "" )
            #set($readmode = "edit")
            #end
            
            #if($senarai.NAMA_WAKIL == "" && $readmode == "edit")
            #set($txtwakil = $NAMA_PB)
            #else
            #set($txtwakil = $senarai.NAMA_WAKIL)
            #end
            
            #if($senarai.NO_WAKIL == "" && $readmode == "edit")
            #set($txtNoKP = $NO_PB)
            #else
            #set($txtNoKP = $senarai.NO_WAKIL)
            #end
                
            #set($txdTarikhSerahCek = $senarai.TARIKH_SERAH_CEK)
            #set($socStatusPenyerahan = $senarai.FLAG_SERAH_CEK)     
                     
            #set($txdTarikhTerimaCek = $senarai.TARIKH_TERIMA)
            #set($txtpenamacek = $senarai.PENERIMA_CEK)
            #set($txtnocek = $senarai.NO_BAYARAN)
            #set($txdTarikhCek = $senarai.TARIKH_CEK)
            #set($txtAmaunCek = $senarai.AMAUN_BAYARAN)
            #set($txdTarikhAkhirCek = $senarai.TARIKH_AKHIR_CEK)         
                  
            #set($jeniswaktu = $senarai.JENIS_WAKTU_AMBIL_CEK)
            #set($txtMasaSiasatan = $senarai.MASA_AMBIL_CEK)            
            #set($txtTempat = $senarai.TEMPAT_AMBIL)
            
           
               
            #set($cara_bayar = $senarai.CARA_BAYAR) 
             #set($id_bayaran = $senarai.ID_BAYARAN)          
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
 #if($close_nilaian != "yes")         <li class="TabbedPanelsTab" tabindex="0" style="display:none"  id="Nilaian"  onClick="Nilaian('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Nilaian / Maklumat Pampasan</li>   #end   
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
        <div class="TabbedPanelsContent">
        
        
        <table width="100%">
  #if($screen_kemasukan == "yes")
  <tr>
  <td colspan="2" >
  <fieldset>
  <legend>Maklumat Penyerahan Cek</legend>
  <table width="100%">
  <tr>
    <td width="100%" valign="top">
    <table width="100%">
  <tr>
    <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td width="28%">Penerima Cek / Wakil</td>
    <td width="1%">:</td>
    <td width="70%">
    #if($readmode == "view")
    $!txtwakil
      <input name="txtwakil" type="hidden" id="txtwakil" size="50"   value="$!txtwakil" onblur="checking_validation(this,'txtwakil_check','yes','penerima cek/wakil ','normal');" onKeyUp="checking_validation(this,'txtwakil_check','yes','penerima cek/wakil','normal');" $readonlymode class = "$disabledmode"  />    
       <span id="txtwakil_check"  class="alert_msg" ></span>  
    
    #else
        <input name="txtwakil" type="text" id="txtwakil" size="50"   value="$!txtwakil" onblur="checking_validation(this,'txtwakil_check','yes','penerima cek/wakil ','normal');" onKeyUp="checking_validation(this,'txtwakil_check','yes','penerima cek/wakil','normal');" $readonlymode class = "$disabledmode"  />    
       <span id="txtwakil_check"  class="alert_msg" ></span>   
       
       #end </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>No. K/P</td>
    <td>:</td>
    <td>
    
     <input name="txtNoKP" type="text" id="txtNoKP" size="50" maxlength="50"   value="$!txtNoKP" onblur="checking_validation(this,'txtNoKP_check','yes','no KP','normal');" onKeyUp="checking_validation(this,'txtNoKP_check','yes','no KP','normal');" $readonlymode class = "$disabledmode" />    
       <span id="txtNoKP_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td width="28%">Tempat Penyerahan</td>
    <td width="1%">:</td>
    <td width="70%"> <input name="txtTempat" type="text" id="txtSerah" size="50" maxlength="100"   value="$!txtTempat" onblur="checking_validation(this,'txtTempat_check','yes','tempat penyerahan','normal');" onKeyUp="checking_validation(this,'txtTempat_check','yes','tempat penyerahan','normal');" $readonlymode class = "$disabledmode" />    
       <span id="txtTempat_check"  class="alert_msg" ></span> </td>
  </tr>
    <tr>
    <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td width="28%">Tarikh Ambil Cek</td>
    <td width="1%">:</td>
    <td width="70%">
    
     <input name="txdTarikhSerahCek" type="text" id="txdTarikhSerahCek" size="10" maxlength="10"   value="$!txdTarikhSerahCek" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhSerahCek_check','yes','serah cek','tarikh2');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
      #if($readmode == "edit")      
     <a href="javascript:displayDatePicker('txdTarikhSerahCek',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>    
         <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     
      #end 
       <span id="txdTarikhSerahCek_check" class="alert_msg"></span>    </td>
  </tr>
    <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Masa Ambil Cek</td>
    <td>:</td>
    <td><input name="txtMasaSiasatan" type="text" id="txtMasaSiasatan" value="$!txtMasaSiasatan"  size="4" maxlength="4"   $readonlymode class = "$disabledmode"  onBlur="checking_validation(this,'txtMasaSiasatan_check','yes','masa penyerahan','waktu');jeniswaktu1(this,'jeniswaktu')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtMasaSiasatan_check','yes','masa penyerahan','waktu');" /> 
                                  <label>
                                  #if($readmode=="view")
                                  #set($waktu = "")
                                  #if( $jeniswaktu == 1)
                                  #set($waktu = "PAGI")  
                                  #elseif($jeniswaktu == 2)
                                  #set($waktu = "TENGAHARI")                                   
                                  #elseif($jeniswaktu == 3)
                                  #set($waktu = "PETANG")      
                                  #elseif($jeniswaktu == 4)
                                  #set($waktu = "MALAM")                                   
                                  #else
                                  #set($waktu = "")
                                  #end             
                                  
                  <input name="waktu" type="text" id="waktu"    value="$waktu" size="4" maxlength="3" style="width:100"   readonly class = "disabled" />
                  <input name="jeniswaktu" type="hidden" id="jeniswaktu" value="$!jeniswaktu" />
                                  #else
                                  <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2('txtMasaSiasatan')" >                                  
                                  #if( $jeniswaktu == 1)                                  
                                  <option value="1" id="op_pagi" >PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="4" id="op_malam">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>                                    
                                  #elseif($jeniswaktu == 2)
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="1" id="op_pagi">PAGI</option>                                 
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="4" id="op_malam">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>
                                  #elseif($jeniswaktu == 3)
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="1" id="op_pagi">PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>                                   
                                  <option value="4" id="op_malam">MALAM</option>
                                  <option value="">SILA PILIH</option>
                                  #elseif($jeniswaktu == 4)
                                  <option value="4" id="op_malam">MALAM</option>
                                  <option value="1" id="op_pagi">PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="3" id="op_petang">PETANG</option>  
                                  <option value="">SILA PILIH</option>
                                  #else
                                  <option value="">SILA PILIH</option>                                 
                                  <option value="1" id="op_pagi" >PAGI</option>                                 
                                  <option value="2" id="op_tengahari" >TENGAHARI</option>
                                  <option value="3"  id="op_petang" >PETANG</option>
                                  <option value="4" id="op_malam" >MALAM</option>                                    
                                  #end 
                                  </select>
                                  #end                                  </label>
                                  #if($readmode == "edit" )
                                   <span style="font-size:9px;color:blue;font-style:italic">format 12 jam (HHMM)</span>       
                                 
                                  #end   
                                  <span id="txtMasaSiasatan_check" class="alert_msg" ></span>  
                                  
                                  </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Status Penyerahan</td>
    <td>:</td>
    <td>
    #if($readmode == "view" )
        
        
              #if($socStatusPenyerahan == "1")
              #set($keputusan = "DISERAHKAN")            
              #elseif($socStatusPenyerahan == "2")
              #set($keputusan = "TIDAK DISERAHKAN")
              #else
              #set($keputusan = "")
              #end
               <input name="keputusan" type="text" id="keputusan" value="$keputusan" $check1  readonly class = "disabled">
             
                  
                  #else
                  
                  
                  
                  <table width="100%">
                  <tr>
                    <td width="30%" >
                    
              #if($socStatusPenyerahan == "1")
              #set($check1 = "checked")
              #set($check2 = "")
            
              #elseif($socStatusPenyerahan == "2")
              #set($check2 = "checked")
              #set($check1 = "")              
             
              #else
              #set($check1 = "")
              #set($check2 = "")
             
              #end
           
                    
<input type="radio" name="socStatusPenyerahan" id="socStatusPenyerahan" value="1" $check1 onClick="checking_validation(this,'socStatusPenyerahan_check','yes','status penyerahan','radio');">
Diserahkan</td>
                    <td width="70%">
 <input type="radio" name="socStatusPenyerahan" id="socStatusPenyerahan" value="2" $check2 onClick="checking_validation(this,'socStatusPenyerahan_check','yes','status penyerahan','radio');">
Tidak Diserahkan</td>
                    
                  </tr>
                </table>
          #end
                 <div id="socStatusPenyerahan_check" class="alert_msg" ></div> 
        
        
    
    
    
    
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
      <!--
      <label>
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      </label>
      -->
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan()">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()">
      </label>
    #end  
     
       #if($id_pembatalan != "" && $!id_bayaran != "")
        #if($readmode == "view")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
      </label>   
      #end 
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
   <legend>Senarai Penerimaan Cek</legend>
   <table width="100%">
  #if($maklumat_bayaran_bp.size() > 0)
   <tr>
  <td width="left"  colspan="7">
   
     <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport2')" />  
      </label>   
 
  </tr>
  #end
   
  <tr class="table_header">
    <td width="3%">Bil</td>
    <td width="18%">No. Cek</td>
    <td width="20%">Penama Cek</td>
    <td width="20%">Penerima/Wakil Cek</td>
    <td width="16%"><div align="right">Amaun Cek (RM)</div></td>
  
    <td width="20%"><div align="center">Status Penyerahan</div></td>
    
      <!--
     #if($maklumat_bayaran_bp.size()>0)
    <td width="3%">
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1();" title="Semak untuk pilih semua" />
      </div>
    </td>
    #end
    -->
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
    <a href="javascript:papar('$list.ID_BAYARAN')" title="Maklumat Penerimaan Cek"><font color="blue">$list.NO_BAYARAN</font></a>   
    
    </td>
    <td>$list.PENERIMA_CEK</td>
    <td>$list.NAMA_WAKIL</td>
    <td align="right">
 
    #if($list.AMAUN_BAYARAN != "")
       $Util.formatDecimal($list.AMAUN_BAYARAN)
    #end
    </td>
    <td><div align="center">
   #set($status_serah = "")
    #if($list.FLAG_SERAH_CEK == "1")
    #set($status_serah = "DISERAHKAN")
    #elseif($list.FLAG_SERAH_CEK == "2")
    #set($status_serah = "TIDAK DISERAHKAN")
    #else
    #set($status_serah = "")
    #end
    
    $status_serah</div></td>
    
    <!--
    <td>
    <div align="center">
       <input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list.ID_BAYARAN" >
     </div>
    </td>
    -->
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
          
        <div class="TabbedPanelsContent"></div>
      </div>
    </div>
    </td>
    </tr>
    </table>
    </fieldset>
    </td>
  </tr>
  
  
</table>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
       
        <td><a href="#" class="style2" onClick="SuratMaklumanSerahPampasanAP_PB('$id_fail','$id_pembatalan','$id_bayaran','')"><font color="blue">Surat Makluman Kepada Agensi Pemohon - Penyerahan Cek</font></a></td>
      </tr> 
      
      
      
       <tr>
       
        <td><a href="#" class="style2" onClick="AkuanPenerimaan('$id_bayaran')">
        <font color="blue">Akuan Penerimaan Cek</font></a></td>
      </tr>  
         
   
     
      
    </table>
</fieldset>




<fieldset id="tableReport2" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
 <tr>
       
        <td>
      
        
        <a href="#" class="style2" onClick="AkuanPenerimaanKeseluruhan('$id_hakmilikpb','$id_pembatalan')">
        <font color="blue">Akuan Penerimaan Cek (Keseluruhan)</font></a></td>
      </tr>     
      
    </table>
</fieldset>


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
  <input type="hidden" name="cara_bayar" id="cara_bayar" value="1" />
  
  
  <input name="txdTarikhTerimaCek" type="hidden" id="txdTarikhTerimaCek" value="$!txdTarikhTerimaCek" />
  <input name="txdTarikhAkhirCek" type="hidden" id="txdTarikhAkhirCek" value="$!txdTarikhAkhirCek" />
  <input name="txtpenamacek" type="hidden" id="txtpenamacek" value="$!txtpenamacek" />
  <input name="txtnocek" type="hidden" id="txtnocek" value="$!txtnocek" />
  <input name="txdTarikhCek" type="hidden" id="txdTarikhCek" value="$!txdTarikhCek" />
  <input name="txtAmaunCek" type="hidden" id="txtAmaunCek" value="$!txtAmaunCek" />
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

checking_validation(document.${formName}.txtwakil,'txtwakil_check','yes','penerima cek/wakil ','normal');
checking_validation(document.${formName}.txtNoKP,'txtNoKP_check','yes','no. KP','normal');
checking_validation(document.${formName}.txdTarikhSerahCek,'txdTarikhSerahCek_check','yes','serah cek','tarikh2');
checking_validation(document.${formName}.socStatusPenyerahan,'socStatusPenyerahan_check','yes','status penyerahan','radio');
checking_validation(document.${formName}.txtMasaSiasatan,'txtMasaSiasatan_check','yes','masa penyerahan','waktu');
checking_validation(document.${formName}.txtTempat,'txtTempat_check','yes','tempat penyerahan','normal');


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
	document.${formName}.sub_command.value = "Penyerahan_Check";
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
	document.${formName}.sub_command.value = "Penyerahan_Check";
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
	document.${formName}.sub_command.value = "Penyerahan_Check";
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
	document.${formName}.sub_command.value = "Penyerahan_Check";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "txtwakil";
	document.${formName}.submit();

	}
}

function kemaskini()
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Penyerahan_Check";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "txtwakil";
	document.${formName}.submit();


}


function checking_validation(field,point,mandatory,value_field,jenis_field){	



   
   	var lepas_or_xlepas = 1;
	if(jenis_field == "tarikh" || jenis_field == "tarikh1" || jenis_field == "tarikh2")
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
		   var date1 = new Date(year, month-1, day);
		 
		 
		 if(jenis_field == "tarikh")
		 {
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	    }
		
		if(jenis_field == "tarikh1")
		 {
		 
	
		  if (date < date1)
		  {			  
		  tarikh_valid = "xvalid1";			
		  }
		  else
		  {
		  tarikh_valid = "valid";	
		  }
		 
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
	   else if(tarikh_valid == "xvalid1")
	   {/*	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" melebihi dari tarikh hari ini");	
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
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
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	 
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
	document.${formName}.sub_command.value = "Penyerahan_Check";
	document.${formName}.subminor_command.value = "Tambah";
	document.${formName}.location.value = "Penerimaan_Cek";
	document.${formName}.point.value = "txtwakil";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.id_pb.value = id_pb;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function papar(id_bayaran)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Penyerahan_Check";
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

function jeniswaktu1(time_field,jenis_time)
{
document.getElementById(jenis_time).value = "";
var vm = time_field.value;
if(vm != "" && vm.length == 4)
{
var vm_m = 0;

if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}


}
else
{

document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}




}





function jeniswaktu2(time_field)
{


var vm = document.getElementById(time_field).value;


if(vm != "" && vm.length == 4)
{
var vm_m = 0;

if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}



}
else
{


document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}




}

function SuratMaklumanSerahPampasanAP_PB(id_fail,id_penarikan,id_bayaran,nama_pegawai)
    {
	/*
    var url = "../servlet/ekptg.report.ppt.SuratMaklumanSerahPampasanAP_PB?id_fail="+id_fail+"&nama_pegawai="+nama_pegawai;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	*/
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_fail="+id_fail+"&id_penarikan="+id_penarikan+"&id_bayaran="+id_bayaran+"&nama_pegawai="+nama_pegawai+"&report=surat_serah_bayar_AP"; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

	}
	
	
	
	function AkuanPenerimaan(id_bayaran)
    {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_bayaran="+id_bayaran+"&report=akuan_penerimaan"; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

	}
	
	function AkuanPenerimaanKeseluruhan(id_hakmilikpb,id_penarikan)
    {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_hakmilikpb="+id_hakmilikpb+"&id_penarikan="+id_penarikan+"&report=akuan_penerimaan_all"; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

	}
	
	


function PangilTerimaPampasanPB_PB(id_fail,id_penarikan,id_bayaran,nama_pegawai)
{
   
   /* var url = "../servlet/ekptg.report.ppt.PangilTerimaPampasanPB_PB?id_fail="+id_fail+"&id_penarikan="+id_penarikan+"&id_bayaran="+id_bayaran+"&nama_pegawai="+nama_pegawai;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
*/
var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_fail="+id_fail+"&id_penarikan="+id_penarikan+"&id_bayaran="+id_bayaran+"&nama_pegawai="+nama_pegawai+"&report=surat_pangil_bayar_PB"; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();


}
  </script>
