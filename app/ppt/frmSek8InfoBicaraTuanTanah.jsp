<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>




#set($txdMilikTanah = "")
#set($txtCaraMilikTanah = "")
#set($txtHargaTanah = "")
#set($txtBebananTanah = "")
#set($txtKeteranganTuanTanah = "")
#set($txtJenisTanaman = "")
#set($socJenisBangunan = "")
#set($sorPecahSempadan = "")
#set($txdPecahSempadan = "")
#set($sorTukarSyarat = "")
#set($txdTukarSyarat = "")


 






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

:$readmode
<table width="100%">
   
    #foreach($list_siasatan in $maklumat_siasatan)
#set($txdMilikTanah = $list_siasatan.TEMPOH_MILIK_TANAH)
#set($txtCaraMilikTanah = $list_siasatan.CARA_MILIK)
#set($txtHargaTanah = $list_siasatan.HARGA_BELI)
#set($txtBebananTanah = $list_siasatan.BEBANAN)
#set($txtKeteranganTuanTanah = $list_siasatan.KETERANGAN_TUAN_TANAH)
#set($txtJenisTanaman = $list_siasatan.JENIS_TANAMAN)
#set($socJenisBangunan = $list_siasatan.JENIS_BANGUNAN)
#set($socBangunan = $list_siasatan.JENIS_BANGUNAN)
#set($keteranganBangunan = $list_siasatan.KETERANGAN_TANAH)
#set($sorPecahSempadan = $list_siasatan.FLAG_PECAH_SEMPADAN)
#set($txdPecahSempadan = $list_siasatan.TARIKH_PECAH_SEMPADAN)
#set($sorTukarSyarat = $list_siasatan.FLAG_TUKAR_SYARAT)
#set($txdTukarSyarat = $list_siasatan.TARIKH_TUKAR_SYARAT)
#end

#if($readmode == "view")
	#set ($selectstyle = "disabled class=disabled" )
	#set( $row = "row2" )
#else
	#set ($selectstyle = "" )
	#set( $row = "row1" )
#end
  
   <tr>
<td>#parse("app/ppt/header_1_ppt.jsp") </td>
  </tr>
  <tr>
<td>#parse("app/ppt/header_siasatan.jsp") </td>

 <input type="hidden" name="id_bangunan" id="id_bangunan" value="$!socJenisBangunan" />

  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <!--  <li class="TabbedPanelsTab" tabindex="0"  onClick="screen5('$id_permohonan')">Kembali</li>  -->
 	<!-- PPT-19 --> 
     <li class="TabbedPanelsTab" tabindex="0"  onClick="popupCarianHakmilik('$id_permohonan','senarai_siasatan')">Kembali</li>
     
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuantanah('$id_siasatan')" >Keterangan Tuan Tanah</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="agensi('$id_siasatan')" >Agensi / Jurunilai</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuntutan('$id_siasatan')">Tuntutan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="bantahan('$id_siasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="nilaian('$id_siasatan')"  >Nilaian JPPH</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="PB('$id_siasatan')"  >Pihak Berkepentingan
     
     <!-- <font style="cursor:help" title="info"  align="center" class="font2" onMouseOut="close_window()"   onMouseOver ="open_new_window('3');this.style.cursor='help'" >
       #parse("app/ppt/infoblink_biru.jsp")
       </font> -->
      
     
     </li>
      <li class="TabbedPanelsTab" tabindex="0" onClick="status('$id_siasatan')" >Status Siasatan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="keputusan('$id_siasatan')" id="perintah_keputusan">Perintah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
   <fieldset>	<legend>NOTA SIASATAN</legend>
   <table width="100%">
  

  <tr>
    <td  valign="top"><table width="100%" border="0">
      <tr   style="display:none">
   <td width="1%">&nbsp;</td>
        <td width="28%">Tempoh Milik Tanah (Tahun)</td>
        <td width="1%">:</td>
        <td width="70%">
        <input name="txdMilikTanah" type="text" id="txdMilikTanah" size="5" maxlength="2"   value="$txdMilikTanah" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdMilikTanah_check','no','tempoh milik tanah','normal')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
        <span id="txdMilikTanah_check" class = "alert_msg" ></span>        </td>
      </tr>
      
      <tr>
        <td>&nbsp;</td>
        <td valign="top">Tempoh Dan Cara Milik Tanah</td>
        <td valign="top">:</td>
        <td><!-- <input name="txtCaraMilikTanah" type="text" id="txtCaraMilikTanah" size="50" maxlength="100" value="$txtCaraMilikTanah"  onBlur="checking_validation(this,'txtCaraMilikTanah_check','no','cara milik tanah','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="checking_validation(this,'txtCaraMilikTanah_check','no','cara milik tanah','normal')" >
        <span id="txtCaraMilikTanah_check" class = "alert_msg"></span>  -->          
       <textarea name="txtCaraMilikTanah" id="txtCaraMilikTanah" cols="60"   rows="6" 
         onBlur="check_length(this,'4000','txtCaraMilikTanah_check','txtCaraMilikTanah_num','normal','no','cara milik tanah');"  
         onKeyup="check_length(this,'4000','txtCaraMilikTanah_check','txtCaraMilikTanah_num','normal','no','cara milik tanah');" 
         onKeydown="check_length(this,'4000','txtCaraMilikTanah_check','txtCaraMilikTanah_num','normal','no','cara milik tanah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtCaraMilikTanah</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtCaraMilikTanah_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtCaraMilikTanah_num" id="txtKeteranganTuanTanah_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtCaraMilikTanah_check" class="alert_msg" ></div>    
        
        
        
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Harga Tanah (RM)</td>
        <td>:</td>
        <td>
    #if($readmode == "view")
    #if($txtHargaTanah != "")
    #set($txtHargaTanah = $Util.formatDecimal($txtHargaTanah))
    #else
    #set($txtHargaTanah = "")
    #end
    #else
    #set($txtHargaTanah = $txtHargaTanah) 
    #end
    <input name="txtHargaTanah" type="text" id="txtHargaTanah" size="15" maxlength="150"   value="$!txtHargaTanah" onblur="validateTarikh(this,this.value);checking_validation(this,'txtHargaTanah_check','no','harga tanah','normal');validateModal(this,this.value,'$txtHargaTanah');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtHargaTanah_check','no','harga tanah','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtHargaTanah_check" class = "alert_msg" ></span>   </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Bebanan Tanah</td>
        <td>:</td>
        <td><input name="txtBebananTanah" type="text" id="txtBebananTanah" size="50" maxlength="50" value="$txtBebananTanah"  onBlur="checking_validation(this,'txtBebananTanah_check','no','bebanan tanah','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="checking_validation(this,'txtBebananTanah_check','no','bebanan tanah','normal')" >
        <span id="txtBebananTanah_check" class = "alert_msg"></span> </td>
      </tr>
      
     
      <tr>
        <td>&nbsp;</td>
        <td>Keterangan Tuan Tanah / Wakil</td>
        <td>:</td>
        <td>
        	<table id="jenispemilikan">
        	<tr>
        		 <!-- PPT-25 (iii) Jenis Pemilikan -->
				    #foreach ($semak in $senaraiSemakan)
				    	<td class="$row" width="10">
                   
					#if ($semakanclass.isSemakan("$id_siasatan", "$semak.id" ))
				  		#set ( $checked = "checked" ) 
				  	#else
				    	#set ( $checked = "" )
				  	#end
				        	 <input type="checkbox" name="jenispemilikan" class="jenispemilikan" id="checkbox" value="$semak.id"  $checked $selectstyle>
				        	 
				  		</td>
			    		<td class="$row">
				        	$semak.keterangan
				        </td>
			    	#end
				 
			    </tr>
				<!-- xxx -->    
			
				
	        	<!-- tr>
					<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Pembelian</td>
					<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Pusaka</td>
					<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Perletakhakan Mahkamah</td>
					<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Pemberimilikan</td>
					<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Lain-lain</td>
				</tr-->
				<!-- PPT-25 (ii) -->
			
			</table>
		</td>
      </tr>
      
      
      <tr id="lainlain">
        <td valign="top">&nbsp;</td>
        <td valign="top">Lain-lain</td>
        <td valign="top">:</td>
        <td>

         
       <textarea name="txtKeteranganTuanTanah" id="txtKeteranganTuanTanah" cols="60"   rows="6" 
         onBlur="check_length(this,'4000','txtKeteranganTuanTanah_check','txtKeteranganTuanTanah_num','normal','no','keterangan tuan tanah');"  
         onKeyup="check_length(this,'4000','txtKeteranganTuanTanah_check','txtKeteranganTuanTanah_num','normal','no','keterangan tuan tanah');" 
         onKeydown="check_length(this,'4000','txtKeteranganTuanTanah_check','txtKeteranganTuanTanah_num','normal','no','keterangan tuan tanah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganTuanTanah</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganTuanTanah_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeteranganTuanTanah_num" id="txtKeteranganTuanTanah_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganTuanTanah_check" class="alert_msg" ></div>               </td>
      </tr>
      
            <tr id="pembelian">
        <td valign="top">&nbsp;</td>
        <td valign="top">Pembelian</td>
        <td valign="top">:</td>
        <td>

         
       <textarea name="txtKeteranganPembelian" id="txtKeteranganPembelian" cols="60"   rows="6" 
         onBlur="check_length(this,'4000','txtKeteranganPembelian_check','txtKeteranganPembelian_num','normal','no','keterangan Pembelian');"  
         onKeyup="check_length(this,'4000','txtKeteranganPembelian_check','txtKeteranganPembelian_num','normal','no','keterangan Pembelian');" 
         onKeydown="check_length(this,'4000','txtKeteranganPembelian_check','txtKeteranganPembelian_num','normal','no','keterangan Pembelian');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganPembelian</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganPusaka_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeteranganPusaka_num" id="txtKeteranganPusaka_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganPusaka_check" class="alert_msg" ></div>               </td>
      </tr>
      
      
            <tr id="perletakhakan ">
        <td valign="top">&nbsp;</td>
        <td valign="top">Keterangan Perletakhakan Mahkamah</td>
        <td valign="top">:</td>
        <td>

         
       <textarea name="txtKeteranganPerletakhakan" id="txtKeteranganPerletakhakan" cols="60"   rows="6" 
         onBlur="check_length(this,'4000','txtKeteranganPerletakhakan_check','txtKeteranganPerletakhakan_num','normal','no','keterangan Perletakhakan');"  
         onKeyup="check_length(this,'4000','txtKeteranganPerletakhakan_check','txtKeteranganPerletakhakan_num','normal','no','keterangan Perletakhakan');" 
         onKeydown="check_length(this,'4000','txtKeteranganPerletakhakan_check','txtKeteranganPerletakhakan_num','normal','no','keterangan Perletakhakan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganPerletakhakan</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganPerletakhakan_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeteranganPerletakhakan_num" id="txtKeteranganPerletakhakan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganPerletakhakan_check" class="alert_msg" ></div>               </td>
      </tr>
      
          <tr id="pusaka">
        <td valign="top">&nbsp;</td>
        <td valign="top">Pusaka</td>
        <td valign="top">:</td>
        <td>

         
       <textarea name="txtKeteranganPusaka" id="txtKeteranganPusaka" cols="60"   rows="6" 
         onBlur="check_length(this,'4000','txtKeteranganPusaka_check','txtKeteranganPusaka_num','normal','no','keterangan Pusaka');"  
         onKeyup="check_length(this,'4000','txtKeteranganPusaka_check','txtKeteranganPusaka_num','normal','no','keterangan Pusaka');" 
         onKeydown="check_length(this,'4000','txtKeteranganPusaka_check','txtKeteranganPusaka_num','normal','no','keterangan Pusaka');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganPusaka</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganPusaka_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeteranganPusaka_num" id="txtKeteranganPusaka_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganPusaka_check" class="alert_msg" ></div>               </td>
      </tr>
      
      <tr id="pemberimilikan">
        <td valign="top">&nbsp;</td>
        <td valign="top">Pemberimilikan</td>
        <td valign="top">:</td>
        <td>

         
       <textarea name="txtKeteranganPemberimilikan" id="txtKeteranganPemberimilikan" cols="60"   rows="6" 
         onBlur="check_length(this,'4000','txtKeteranganPemberimilikan_check','txtKeteranganPemberimilikan_num','normal','no','keterangan Pemberimilikan');"  
         onKeyup="check_length(this,'4000','txtKeteranganPemberimilikan_check','txtKeteranganPemberimilikan_num','normal','no','keterangan Pemberimilikan');" 
         onKeydown="check_length(this,'4000','txtKeteranganPemberimilikan_check','txtKeteranganPemberimilikan_num','normal','no','keterangan Pemberimilikan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganPemberimilikan</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtKeteranganPemberimilikan_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeteranganPemberimilikan_num" id="txtKeteranganPemberimilikan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtKeteranganPemberimilikan_check" class="alert_msg" ></div>               </td>
      </tr>
  <!-- 
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">Jenis Tanaman </td>
        <td width="1%">:</td>
        <td width="70%"><input name="txtJenisTanaman" type="text" id="txtJenisTanaman" size="50" maxlength="50" value="$txtJenisTanaman"  onBlur="checking_validation(this,'txtJenisTanaman_check','no','jenis tanaman','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="checking_validation(this,'txtJenisTanaman_check','no','jenis tanaman','normal')" >
        <span id="txtJenisTanaman_check" class = "alert_msg" ></span> </td>
      </tr>
   -->
   
      <!-- PPT-25 ii -->
      <tr>
        <td>&nbsp;</td>
        <td>Jenis Tanaman</td>
        <td>:</td>
        <td>
        	<table id="jenistanaman">
        	<tr>
        		 <!-- PPT-25 (iii) Jenis Tanaman -->
				    #foreach ($semakan in $senaraiSemakan2)
				    	<td class="$row" width="10">
                   
					#if ($semakanclass.isSemakan("$id_siasatan", "$semakan.id" ))
				  		#set ( $checked_ = "checked" ) 
				  	#else
				    	#set ( $checked_ = "" )
				  	#end
				        	 <input type="checkbox" name="jenistanaman" class="jenistanaman" id="checkbox2" value="$semakan.id"  $checked_ $selectstyle required="required">
				        	 
				  		</td>
			    		<td class="$row">
				        	$semakan.keterangan
				        </td>
			    	#end
			    </tr>
				<!-- xxx -->    
        	<!-- input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 /
				<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Getah</td>
				<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Sawit</td>
				<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Padi</td>
				<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Tanaman Kontan</td>
				<td align="left"><input type="checkbox" $disability1 $checkedCB name="cbsemaks" id="cbsemaks" onclick="" value="$!listTanah.id_hakmilik">Lain-lain</td>
			</tr-->
			</table>
		</td>
      </tr>
      <!-- PPT-25 ii -->
      
     <!--  <tr>
        <td>&nbsp;</td>
        <td>Jenis Bangunan</td>
        <td>:</td>
        <td><input name="txtJenisBangunan" type="text" id="txtJenisBangunan" size="50" maxlength="50" value="$txtJenisBangunan"  onBlur="checking_validation(this,'txtJenisBangunan_check','no','jenis bangunan','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="checking_validation(this,'txtJenisBangunan_check','no','jenis bangunan','normal')" >
        <span id="txtJenisBangunan_check" class = "alert_msg" ></span> </td>
      </tr>
      
      -->
     #if($readmode == "edit" ) 
      <tr>
    			<td>&nbsp;</td>
    			<td>Jenis Bangunan</td>
    			<td>:</td>
    			<td>$!selectBangunan</td>
    		</tr>
    		#end
    
    		#if($readmode == "view" ) 
    		 <tr>
    			<td>&nbsp;</td>
    			<td>Jenis Bangunan</td>
    			<td>:</td>
    			<td> <input name="keteranganBangunan" type="text" id="keteranganBangunan" value="$keteranganBangunan" size="50" class="disabled" />   </td>
    		</tr>
    		#end
      <tr>
        <td>&nbsp;</td>
        <td>Status Pecah Sempadan</td>
        <td>:</td>
        <td>  
     #if($readmode == "view" )       
              #if($sorPecahSempadan == "1")
              #set($PecahSempadan = "DIPOHON")            
              #elseif($sorPecahSempadan == "2")
              #set($PecahSempadan = "TIDAK DIPOHON")                                     
              #else
              #set($PecahSempadan = "")
              #end
              <input name="PecahSempadan" type="text" class = "disabled" id="PecahSempadan" value="$PecahSempadan" size="50"  readonly $check1>              
              #else
              <table width="100%" border="0">
              <tr>
              <td width="15%" >                    
              #if($sorPecahSempadan == "1")
              #set($check1 = "checked")
              #set($check2 = "") 
                      
              #elseif($sorPecahSempadan == "2")
              #set($check2 = "checked")
              #set($check1 = "")
                       
              #else
              #set($check1 = "")
              #set($check2 = "")
              
              #end 
<input type="radio" name="sorPecahSempadan" id="sorPecahSempadan" value="1" $check1 onClick="checking_validation(this,'sorPecahSempadan_check','no','status pecah sempadan','radio'); showRow('trPecahSempadan');">Dipohon</td>
<td width="85%"><input type="radio" name="sorPecahSempadan" id="sorPecahSempadan" value="2" $check2 onClick="checking_validation(this,'sorPecahSempadan_check','no','status pecah sempadan','radio'); hideRow('trPecahSempadan');">Tidak Dipohon   &nbsp; &nbsp;    <span id="sorPecahSempadan_check" class = "alert_msg"></span> </td>  
                </tr>
                </table>
          #end                   </td>
  </tr>
<!-- //PPT-25(iv) -->
      <tr id="trPecahSempadan">
        <td>&nbsp;</td>
        <td>Tarikh Pecah Sempadan</td>
        <td>:</td>
        <td>
        <input name="txdPecahSempadan" type="text" id="txdPecahSempadan" size="10" maxlength="10"   value="$txdPecahSempadan" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdPecahSempadan_check','no','pecah sempadan','tarikh')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #if($readmode == "edit")  
      <a href="javascript:displayDatePicker('txdPecahSempadan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     
      #end
       <span id="txdPecahSempadan_check" class = "alert_msg" ></span>        </td>
      </tr>
   
      <tr>
        <td>&nbsp;</td>
        <td>Status Tukar Syarat</td>
        <td>:</td>
        <td>
  
  #if($readmode == "view" )       
              #if($sorTukarSyarat == "1")
              #set($TukarSyarat = "DIPOHON")            
              #elseif($sorTukarSyarat == "2")
              #set($TukarSyarat = "TIDAK DIPOHON")                                     
              #else
              #set($TukarSyarat = "")
              #end
              <input name="TukarSyarat" type="text" class = "disabled" id="TukarSyarat" value="$TukarSyarat" size="50"  readonly $check1>              
              #else
              <table width="100%">
              <tr>
              <td width="15%" >                    
              #if($sorTukarSyarat == "1")
              #set($check1 = "checked")
              #set($check2 = "") 
                      
              #elseif($sorTukarSyarat == "2")
              #set($check2 = "checked")
              #set($check1 = "")
                       
              #else
              #set($check1 = "")
              #set($check2 = "")
              
              #end 
<input type="radio" name="sorTukarSyarat" id="sorTukarSyarat" value="1" $check1 onClick="checking_validation(this,'sorTukarSyarat_check','no','status tukar syarat','radio'); showRow('trTukarSyarat');">Dipohon</td>
<td width="85%"><input type="radio" name="sorTukarSyarat" id="sorTukarSyarat" value="2" $check2 onClick="checking_validation(this,'sorTukarSyarat_check','no','status tukar syarat','radio'); hideRow('trTukarSyarat');">Tidak Dipohon  &nbsp; &nbsp;   <span id="sorTukarSyarat_check" class = "alert_msg" ></span> </td>  
                </tr>
                </table>
          #end   </td>
  </tr>
  <!-- //PPT-25(iv) -->
      <tr id="trTukarSyarat">
        <td>&nbsp;</td>
        <td>Tarikh Tukar Syarat</td>
        <td>:</td>
        <td>
        
        <input name="txdTukarSyarat" type="text" id="txdTukarSyarat" size="10" maxlength="10"   value="$txdTukarSyarat" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdTukarSyarat_check','no','tukar syarat','tarikh')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #if($readmode == "edit")  
      <a href="javascript:displayDatePicker('txdTukarSyarat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     
      #end
       <span id="txdTukarSyarat_check" class = "alert_msg" ></span>        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini('$id_siasatan','$socJenisBangunan')">
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
     <!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  -->
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

		<!-- tr>
			<td width="1%"></td>
			<td colspan="3">
				<div align="left"><a href="javascript:maklumatsiasatan('$id_siasatan')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">NOTA SIASATAN </font></a></div>
			</td>
		</tr-->
	</table>
</fieldset>
<!-- PPT-24 END -->

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
 
  <input type="hidden" name="socJenisBangunan" id="socJenisBangunan" value="$!socJenisBangunan" />
  
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
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



checking_validation(document.${formName}.txtBebananTanah,'txtBebananTanah_check','no','bebanan tanah','normal')
checking_validation(document.${formName}.txdMilikTanah,'txdMilikTanah_check','no','tempoh milik tanah','normal')
//checking_validation(document.${formName}.txtCaraMilikTanah,'txtCaraMilikTanah_check','no','cara milik tanah','normal')
checking_validation(document.${formName}.txtHargaTanah,'txtHargaTanah_check','no','harga tanah','normal');
//checking_validation(document.${formName}.txtKeteranganTuanTanah,'txtKeteranganTuanTanah_check','yes','keterangan tuan tanah','normal')
checking_validation(document.${formName}.txdPecahSempadan,'txdPecahSempadan_check','no','pecah sempadan','tarikh')
checking_validation(document.${formName}.sorPecahSempadan,'sorPecahSempadan_check','no','status pecah sempadan','radio');
//checking_validation(document.${formName}.txtJenisBangunan,'txtJenisBangunan_check','no','jenis bangunan','normal')
checking_validation(document.${formName}.txtJenisTanaman,'txtJenisTanaman_check','no','jenis tanaman','normal')
checking_validation(document.${formName}.txdTukarSyarat,'txdTukarSyarat_check','no','tukar syarat','tarikh')
checking_validation(document.${formName}.sorTukarSyarat,'sorTukarSyarat_check','no','status tukar syarat','radio');
check_length(document.${formName}.txtKeteranganTuanTanah,'4000','txtKeteranganTuanTanah_check','txtKeteranganTuanTanah_num','normal','no','keterangan tuan tanah');
check_length(document.${formName}.txtCaraMilikTanah,'4000','txtCaraMilikTanah_check','txtCaraMilikTanah_num','normal','no','cara milik tanah');




}


function cari_lot(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'bolder';
hp.style.fontStyle = 'italic';
window.location.hash=field;
goTo(field);
}
}
function cari_lot1(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'normal';
hp.style.fontStyle = 'normal';
}
document.${formName}.CariLot.value="";
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
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
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;

	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+"");	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	*/
	$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");	
	
	   
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");	
	
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
	
	   }
	   
	   	   
	   }
	   
	   
	   
	    
	   
	   
	   
	   if(jenis_field == "waktu")
	   {
	   
		
	if(field.value == "" && mandatory == "yes")
	{
	lepas_or_xlepas = 2;
    }  
	else if(field.value != "" && (mandatory == "yes" || mandatory == "no"))
	{	   
	if (field.value.length < 4) {
	lepas_or_xlepas = 3;
	}
	else if (field.value.charAt(0) > 1)  {
	lepas_or_xlepas = 3;
	}	
	else if ((field.value.charAt(0) == 1) && (field.value.charAt(1) >2 )) {
	lepas_or_xlepas = 3;
	}
	else if ((field.value.charAt(2) > 5) ) {
	lepas_or_xlepas = 3;
	}
	else if ((field.value.charAt(3) > 9) ) {
	lepas_or_xlepas = 3;
	}
	}  
	  
	   if(lepas_or_xlepas == "2")
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan waktu "+value_field+"");	
	
	   }
	   else if(lepas_or_xlepas == "3")
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan waktu "+value_field+" dengan format yang betul");	
	
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
	   
	   
	   
	   
	   if(jenis_field == "poskod")
	   {
	   
		
	if(field.value == "" && mandatory == "yes")
	{
	lepas_or_xlepas = 2;
    }  
	else if(field.value != "" && (mandatory == "yes" || mandatory == "no"))
	{	   
	if (field.value.length < 5) {
	lepas_or_xlepas = 3;
	}	
	}  
	  
	   if(lepas_or_xlepas == "2")
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan poskod "+value_field+"");	
	   }
	   else if(lepas_or_xlepas == "3")
	   {/*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan poskod "+value_field+" dengan format yang betul");	
	 	
	   }
	   else
	   {/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
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

function getBandarSiasatan(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "getBandar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "socBandarSiasatan";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


function tambah(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "tambah";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


function simpan(id_siasatan)	{
	var c = 0;
	semakJenisTanaman();
	semakJenisPemilikan();
	
if(document.${formName}.validation_field != null)	{
   if (document.${formName}.validation_field.length == null)  {
		if(document.${formName}.validation_field.value == "invalid")  {
				c++;
		}
   }	else 	{
   for (i = 0; i < document.${formName}.validation_field.length; i++)	{		
			if(document.${formName}.validation_field[i].value == "invalid")	{
               c++;	 
			}	
        }
    }
}


if(c>0)	{
	alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
	return;
}	else	{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;		
	document.${formName}.location.value = "tuan_tanah";
	document.${formName}.point.value = "tuan_tanah";	
	document.${formName}.submit(); 
	}
}
}


function papar(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();
	
}


function kemaskini(id_siasatan,socJenisBangunan)
{
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.socJenisBangunan.value = socJenisBangunan;
	document.${formName}.location.value = "tuan_tanah";
	document.${formName}.point.value = "txdMilikTanah";
	document.${formName}.submit();
	
}





function hapus_beramai()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Hapus_beramai";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	}
}


function batal(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "tuan_tanah";
	document.${formName}.point.value = "txdMilikTanah";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}





function ShowPopupSiasatan(tab)
{

hp = document.getElementById(tab);
//hp.style.display=="none" &&
	if( hp.style.visibility=="collapse" && hp.style.display=="none"){
		hp.style.display = "block";
		hp.style.visibility = "visible";
	}
	//hp.style.display=="block" &&
	else if( hp.style.visibility=="visible" && hp.style.display=="block"){
		hp.style.display = "none";
		hp.style.visibility = "collapse";
	}
	

}


function ShowPopupKehadiran(tab)
{

hp = document.getElementById(tab);
//hp.style.display=="none" &&
	if( hp.style.visibility=="collapse" && hp.style.display=="none"){
		hp.style.display = "block";
		hp.style.visibility = "visible";
	}
	//hp.style.display=="block" &&
	else if( hp.style.visibility=="visible" && hp.style.display=="block"){
		hp.style.display = "none";
		hp.style.visibility = "collapse";
	}
	

}


function cari_lot(field)
{


hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'bolder';
hp.style.fontStyle = 'italic';
window.location.hash=field;
goTo(field);
}
else
{

if(field=="")
{
alert("Pastikan no. pb dimasukkan");
}
else 
{
alert("Tiada rekod untuk pb bernombor "+field);
}
}
}
function cari_lot1(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'normal';
hp.style.fontStyle = 'normal';
}
document.${formName}.CariLot.value="";
}


function Update_kehadiran()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "Update_kehadiran";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
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


function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "tuan_tanah";
	document.${formName}.point.value = "txdMilikTanah";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
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


function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)	{
	var lepas_or_xlepas = 1;
       if(jenis_field == "normal")	{ 
       		if(my_form.value == "" && mandatory == "yes")	{
       			lepas_or_xlepas = 2;
       			}
       		if(my_form.value == "")	{
	   			document.getElementById(text_num).value = maxLen;
	   			}   
	   		if(lepas_or_xlepas == "2")	{
	   			$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   			}	else	{
	   				if (my_form.value.length >= maxLen) {
	   					$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
	   					my_form.value = my_form.value.substring(0, maxLen);
	   					maxLen = 0;
	   					}	else	{
	   						$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   						maxLen = maxLen - my_form.value.length;
	   						}
	   					}
	   				}
	   					$jquery("#"+text_num).html(maxLen+"");
	   					}


function status(id_siasatan)	{
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


function popupCarianHakmilik(id_permohonan,flag_skrin) 
{   //PPT-19
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
	
	//screen5(id_permohonan);
	
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

function hideRow(rowId)
{ //PPT-25(iv)
	 document.getElementById(rowId).style.display = "none";
	 
	 if(rowId == "trPecahSempadan"){  
		 document.getElementById('txdPecahSempadan').value =''; 
	 }
	 if(rowId == "trTukarSyarat"){
		 document.getElementById('txdTukarSyarat').value =''; 
	 }
}

function showRow(rowId) 
{  //PPT-25(iv)
    document.getElementById(rowId).style.display = "";
}


function semakJenisTanaman() {
    var flag = 0;
    for (var i = 0; i < 5; i++) {
      if(document.${formName}["jenistanaman"][i].checked){
      	flag++;
      }
    }
    
    if (flag == 0) {
      alert ("Pastikan 'Jenis Tanaman' dipilih");
      return c++;
    }
    return true;
}

function semakJenisPemilikan() {
    var flag = 0;
    for (var i = 0; i < 5; i++) {
      if(document.${formName}["jenispemilikan"][i].checked){
      	flag++;
      }
    }
    
    if (flag == 0) {
      alert ("Pastikan 'Keterangan Tuan Tanah / Wakil' dipilih");
      return c++;
    }
    return true;
}

  

</script>