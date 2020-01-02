#parse("app/ppt/HadAksesOnlinePPT.jsp")

<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {color: #FF0000}
.style3 {
	color: #FFFFFF;
	font-weight: bold;
}
.style5 {color: #FFFFFF; font-weight: bold; font-style: italic; }
-->
</style>





#set($txtSebabPembatalan = "")
#set($sorJenisPembatalan = "")
#set($txtNoRujSurat = "")
#set($txdTarikhTerimaSurat = "")
#set($txdTarikhSurat = "")
#set($txdTarikhPembatalan = "")
#set($txtNoPembatalan = "")
#set($id_pembatalan = "")
#set($flag_online = "")



#foreach ( $mp in $maklumat_pembatalan )
#set($txtSebabPembatalan = $mp.SEBAB_PEMBATALAN)
#set($flag_online = $mp.FLAG_ONLINE)

#if($mp.JENIS_PEMBATALAN == 1)
#set($sorJenisPembatalan = "sebahagian")
#elseif($mp.JENIS_PEMBATALAN == 2)
#set($sorJenisPembatalan = "keseluruhan")
#else
#set($sorJenisPembatalan = "")
#end




#set($txtNoRujSurat = $mp.NO_RUJUKAN_SURAT)
#set($txdTarikhTerimaSurat = $mp.TARIKH_TERIMA_SURAT)
#set($txdTarikhSurat = $mp.TARIKH_SURAT)
#set($txdTarikhPembatalan = $mp.TARIKH_PEMBATALAN)
#set($txtNoPembatalan = $mp.NO_PEMBATALAN)
#set($id_pembatalan = $mp.ID_PEMBATALAN)
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

#if($flag_online != "1" && $flag_online != "2" && $flag_online != "4" )
#parse("app/ppt/paging_penarikanbalik.jsp")
#end


<table width="100%">
  <tr>
    <td>#parse("app/ppt/header_ppt.jsp")</td>
  </tr>
  <tr>
    <td><fieldset id="maklumat_tambahan">
    <legend>
    
      #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
   MAKLUMAT PENARIKAN BALIK PENGAMBILAN TANAH
   #end  
   #if($jenis_permohonan == "2" || $jenis_permohonan == "1")
     MAKLUMAT PEMBATALAN PENGAMBILAN TANAH
   #end
    
    
    </legend>
 
   
    <table width="100%">
    
 
  #if($jenis_permohonan == "4" || $jenis_permohonan == "3" || $jenis_permohonan == "2" || $jenis_permohonan == "1")
 
  <tr>
    <td width="100%" valign="top"><table width="100%">
    #if($id_pembatalan != "")
    
    
    
    
    
    
    
      <tr>
        <td width="1%">&nbsp;    </td>
        <td width="28%">
        
        #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
        Bil. Penarikan Balik
        #end 
        #if($jenis_permohonan == "2" || $jenis_permohonan == "1") 
        Bil. Penarikan Balik
        #end 
        
        
        </td>
        <td width="1%">:</td>
        <td width="70%" colspan="2">
          <label>
         $txtNoPembatalan
          <input name="txtNoPembatalan" type="hidden" id="txtNoPembatalan" size="50" maxlength="50" value="$txtNoPembatalan" style="" onBlur="this.value=this.value.toUpperCase()" readonly class = "disabled"   >
          </label>        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Kemasukan</td>
        <td>:</td>
        <td colspan="2">
      $txdTarikhPembatalan
        <input name="txdTarikhPembatalan" type="hidden" id="txdTarikhPembatalan" size="10" maxlength="10"  value="$txdTarikhPembatalan" onBlur="validateTarikh(this,this.value);check_date(this)" onKeyUp="validateTarikh(this,this.value);" readonly class = "disabled" />
    <!--
      #if($readmode == "edit")
      <a href="javascript:displayDatePicker('txdTarikhPembatalan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/> 
      #end
      -->      </td>
      </tr>
      #else
       <input name="txtNoPembatalan" type="hidden" id="txtNoPembatalan" size="50" maxlength="50" value="$txtNoPembatalan" style="" onBlur="this.value=this.value.toUpperCase()" $readonlymode class = "$disabledmode" >
       <input name="txdTarikhPembatalan" type="hidden" id="txdTarikhPembatalan" size="10" maxlength="10"  value="$txdTarikhPembatalan" onBlur="validateTarikh(this,this.value);check_date(this)" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #end
     
      
      #if(($jenis_permohonan == "4" || $jenis_permohonan == "2")  && ($flag_online != "1" && $flag_online != "2" && $flag_online != "3" && $flag_online != "4") )
      <tr>
        <td width="1%" >#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%">Tarikh Surat</td>
        <td width="1%">:</td>
        <td width="70%">
        
         #if(($jenis_permohonan == "4" || $jenis_permohonan == "3")  && ($flag_online != "1" && $flag_online != "2" && $flag_online != "3" && $flag_online != "4")) 
      <input name="txdTarikhSurat" type="text" id="txdTarikhSurat" size="10" maxlength="10"   value="$txdTarikhSurat" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhSurat_check','yes','surat penarikan balik','tarikh')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #if($readmode == "edit")  
      <a href="javascript:displayDatePicker('txdTarikhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
      #end
      <span id="txdTarikhSurat_check" class="alert_msg" ></span>  
        #end 
        #if(($jenis_permohonan == "2" || $jenis_permohonan == "1")  && ($flag_online != "1" && $flag_online != "2" && $flag_online != "3" && $flag_online != "4")) 
       <input name="txdTarikhSurat" type="text" id="txdTarikhSurat" size="10" maxlength="10"   value="$txdTarikhSurat" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhSurat_check','yes','surat pembatalan','tarikh')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #if($readmode == "edit")  
      <a href="javascript:displayDatePicker('txdTarikhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
      #end
      <span id="txdTarikhSurat_check" class="alert_msg" ></span>  
        #end 
            </td>
     
      </tr>
    
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Tarikh Terima Surat</td>
        <td>:</td>
        <td colspan="2">
      
           
             <input name="txdTarikhTerimaSurat" type="text" id="txdTarikhTerimaSurat" size="10" maxlength="10"   value="$txdTarikhTerimaSurat" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhTerimaSurat_check','yes','surat penarikan balik','tarikh')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #if($readmode == "edit")  
      <a href="javascript:displayDatePicker('txdTarikhTerimaSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
      #end
       <span id="txdTarikhTerimaSurat_check" class="alert_msg" ></span>            </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>
        
           
        #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
         No. Ruj Surat Tarik Balik
        #end 
        #if($jenis_permohonan == "2" || $jenis_permohonan == "1") 
        No. Ruj Pembatalan
        #end 
        
       
        
        
        </td>
        <td>:</td>
        <td colspan="2"><input name="txtNoRujSurat" type="text" id="txtNoRujSurat" size="40" maxlength="30" value="$txtNoRujSurat" style="" onBlur="checking_validation(this,'txtNoRujSurat_check','yes','no rujukan surat','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="checking_validation(this,'txtNoRujSurat_check','yes','no rujukan surat','normal')" >
        <span id="txtNoRujSurat_check" class="alert_msg" ></span>        </td>
      </tr>
      
      
      
      #end
      
      
      <tr>
        <td width="1%" >#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%"   >Pilihan Penarikan Balik</td>
        <td width="1%"  >:</td>
        <td width="70%" colspan="2" >
       
     <!-- $hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()' -->
        #if($readmode == "view" )       
              #if($sorJenisPembatalan == "sebahagian")
              #set($JenisPembatalan = "SEBAHAGIAN LOT ("+$senarai_hakmilik_batal.size()+" daripada "+$hakmilikoverall+")")
              #set($check1 = "checked")
              #set($check2 = "")
              #elseif($sorJenisPembatalan == "keseluruhan")
              #set($JenisPembatalan = "KESELURUHAN LOT")
              #set($check1 = "")
              #set($check2 = "checked")
              #else
              #set($JenisPembatalan = "")
              #set($check1 = "")
              #set($check2 = "")
              #end
               <span style="color:#0000FF">$JenisPembatalan</span>
                
             <span id="sorJenisPembatalan_check" class="alert_msg" style=" display:none; width:80%"  ></span>  
               <input name="JenisPembatalan" type="hidden" id="JenisPembatalan" value="$JenisPembatalan" $check1  readonly class = "disabled" onClick="doCheckAll_P()">
           
                <label id="divCheckbox"  style="visibility: hidden;">
                <input name="sorJenisPembatalan" type="radio" id="sorJenisPembatalan" value="sebahagian" $check1 $disabledmode  onclick="doCheckAll_P()">
                <input type="radio" name="sorJenisPembatalan" id="sorJenisPembatalan" value="keseluruhan" $check2 $disabledmode onClick="doCheckAll_P()">
                </label>
             
        
        #end
        
        #if($readmode == "edit" )
        
        
          <table width="100%">
            <tr>
              <td width="30%">
          
              #if($sorJenisPembatalan == "sebahagian")
              #set($check1 = "checked")
              #set($check2 = "")
              #elseif($sorJenisPembatalan == "keseluruhan")
              #set($check2 = "checked")
              #set($check1 = "")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #end
               
               
              <span style="color:#0000FF" id="field_jenis_batal" >$JenisPembatalan</span>
              <span id="sorJenisPembatalan_check" class="alert_msg" style="width:80%"  ></span>  
               
               <span style="display:none">
               
               <label style="visibility: hidden;">                         
              <input name="sorJenisPembatalanD" type="radio" id="sorJenisPembatalanD" value="sebahagian" $check1 disabled  onclick="return false;" >
              Sebahagian     </label>
             
               <label id="divCheckbox" style="visibility: hidden;" >
              <input name="sorJenisPembatalan" type="radio" id="sorJenisPembatalan" value="sebahagian" $check1 $disabledmode   onclick="return false;" >
              </label>   
              
                     
              <label style="visibility: hidden;" >
     <input type="radio" name="sorJenisPembatalanD" id="sorJenisPembatalanD" value="keseluruhan" $check2 disabled onClick="return false;" >
              Keseluruhan</label>
               <label id="divCheckbox"  style="visibility: hidden;" >
              <input type="radio" name="sorJenisPembatalan" id="sorJenisPembatalan" value="keseluruhan" $check2 $disabledmode onClick="return false;" >
              </label>            
              
              </span>  </td>
            </tr>
          </table>
           #end
                </td>
      </tr>
      #if($flag_online != "")
      <tr>
        <td>&nbsp;</td>
        <td>
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3")
		 Status Permohononan Online Penarikan Balik
         #end
  
        #if($jenis_permohonan == "2" || $jenis_permohonan == "1")
        Status Permohononan Online Pembatalan
        #end 
        
        
        </td>
        <td>:</td>
        <td colspan="2">
      
          <span style="color:#0000FF">
         #if($flag_online == "1")
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
		 PENDAFTARAN PERMOHONAN ONLINE (PENARIKAN BALIK)
         #else
         PENDAFTARAN PERMOHONAN ONLINE (PEMBATALAN)
         #end
         #elseif($flag_online == "2")
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
		 PERMOHONAN ONLINE (PENARIKAN BALIK) TELAH DIHANTAR KE JKPTG      
         #else
         PERMOHONAN ONLINE (PEMBATALAN) TELAH DIHANTAR KE JKPTG      
         #end            
         #elseif($flag_online == "3")
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
		 PERMOHONAN ONLINE (PENARIKAN BALIK) TELAH DISAHKAN OLEH JKPTG    
         #else
         PERMOHONAN ONLINE (PEMBATALAN) TELAH DISAHKAN OLEH JKPTG    
         #end 
         #elseif($flag_online == "4")
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
		 PERMOHONAN ONLINE (PENARIKAN BALIK) DITOLAK   
         #else
         PERMOHONAN ONLINE (PEMBATALAN) DITOLAK       
         #end 
         #end </span></td>
      </tr>
      #end
      <tr>
        <td valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td valign="top">Alasan/Catatan</td>
        <td valign="top">:</td>
        <td colspan="2">
        
        #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
     
          <textarea name="txtSebabPembatalan" id="txtSebabPembatalan" cols="80"   rows="8"         
         onBlur="check_length(this,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan penarikan');"  
         onKeyup="check_length(this,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan penarikan');" 
         onKeydown="check_length(this,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan penarikan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtSebabPembatalan</textarea>
       #if($readmode == "edit")           
        <div><span id="txtSebabPembatalan_num" style="color:blue;" ></span><span> Baki Aksara</span>
      
         </div>
         #else
         <input name="txtSebabPembatalan_num" id="txtSebabPembatalan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtSebabPembatalan_check" class="alert_msg" ></div> 
        #end 
        #if($jenis_permohonan == "2" || $jenis_permohonan == "1") 
         <textarea name="txtSebabPembatalan" id="txtSebabPembatalan" cols="80"   rows="8"         
         onBlur="check_length(this,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan pembatalan');"  
         onKeyup="check_length(this,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan pembatalan');" 
         onKeydown="check_length(this,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan pembatalan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtSebabPembatalan</textarea>
       #if($readmode == "edit")           
        <div><span id="txtSebabPembatalan_num" style="color:blue;" ></span><span> Baki Aksara</span>
      
         </div>
         #else
         <input name="txtSebabPembatalan_num" id="txtSebabPembatalan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtSebabPembatalan_check" class="alert_msg" ></div> 
        #end 
        
        
      
                </td>
      </tr>
      
      
    </table></td>
  
  </tr>
  #end
  #if($jenis_permohonan == "1")
  #end
   #if($readmode == "edit")
  <tr>
    <td>#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td></td>
  </tr>  
  #end
  
</table>

    </fieldset>    </td>
  </tr>

<!-- #if($maklumat_pembatalan.size() > 0)
  
  <tr>
    <td><fieldset id="senarai_dokumen">
   
    #if($jenis_permohonan == "3" || $jenis_permohonan == "1")
    <legend>MUAT NAIK DOKUMEN</legend>
    #else
    <legend>SENARAI DOKUMEN YANG DISERTAKAN</legend>
    #end
    
    #if($portal_role == "online_kjp")
    	#if($flag_online != "2" && $flag_online != "3")
		    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" >
		    #if($listDokumen_size > 0)
		    <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumen()">
		    #end
    	#end
    #else
    	#if($flag_online != "1" && $flag_online != "4")
		    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" >
		    #if($listDokumen_size > 0)
		    <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumen()">
		    #end
    	#end
    #end
    
    
    
    <table width="100%">
  <tr class="table_header">
    <td width="5%" align="center"><b>Bil</b></td>
    <td width="30%"><b>Nama Dokumen</b></td>
    <td width="30%"><b>Keterangan</b></td>
    <td width="30%"><b>Muat Turun</b></td>
     #if($listDokumen_size > 0 )
     	#if($portal_role == "online_kjp")
     	  #if($flag_online != "2" && $flag_online != "3")
	      <td width="5%">
		      <div align="center">
		      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1()" title="Semak untuk pilih semua" />
		      </div>     
	      </td>
	      #end
	    #else
	      #if($flag_online != "1" && $flag_online != "4")
	      <td width="5%">
		      <div align="center">
		      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1()" title="Semak untuk pilih semua" />
		      </div>     
	      </td>
	      #end
      	#end
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
    <td class="$row" align="center">$list1.BIL</td>
    #if($jenis_permohonan == "3" || $jenis_permohonan == "1")
    <td class="$row" >$list1.TAJUK</td>
    #else
    <td class="$row" ><a href="javascript:view_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.TAJUK</font></a></td>
    #end
    <td class="$row" >$list1.KETERANGAN</td>
    <td class="$row"><a href="javascript:papar_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.NAMA_FAIL</font></a></td>  
     
     
     	#if($portal_role == "online_kjp")
     	  #if($flag_online != "2" && $flag_online != "3")
	      <td class="$row" >
    			<div align="center">
       			<input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
     			</div>
    	</td>
	      #end
	    #else
	      #if($flag_online != "1" && $flag_online != "4")
	      <td class="$row" >
    		<div align="center">
       		<input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
     		</div>
    		</td>
	      #end
      	#end
    
     
     
  </tr>
  #end
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
</table>

    </fieldset>    </td>
  </tr>
  
  #end -->
   
  
  <tr>
    <td><fieldset>
    <table width="100%">
      <tr>
        <td><div id="TabbedPanels1" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
             <li class="TabbedPanelsTab" tabindex="0" onClick="papar('$id_permohonan','$id_pembatalan')" id="senarai_penarikan" >
             
             
           
                  #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
     SENARAI PENARIKAN BALIK
   #end  
   #if($jenis_permohonan == "2" || $jenis_permohonan == "1")
    SENARAI PEMBATALAN
   #end
             
             </li>
            <li class="TabbedPanelsTab" tabindex="0" onClick="PembatalanAmbilTanahPT()" id="senarai_lot" >SENARAI LOT</li>
             #if($maklumat_pembatalan.size() > 0)
            <li class="TabbedPanelsTab" tabindex="0" onClick="PembatalanAmbilTanahLotUnit()" id="lot_dibatal" >
            
               #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
   LOT PENARIKAN BALIK
   #end  
   #if($jenis_permohonan == "2" || $jenis_permohonan == "1")
  LOT PEMBATALAN
   #end
            
            </li>
            #end
            
            #if($maklumat_pembatalan.size() > 0)
	            <li class="TabbedPanelsTab" tabindex="0" id="senarai_dokumen">
	            #if($jenis_permohonan == "3" || $jenis_permohonan == "1")
	            	MUAT NAIK DOKUMEN
	            #else
	            	SENARAI DOKUMEN YANG DISERTAKAN
	            #end
	            </li>
            #end
            
          </ul>
        
          <div class="TabbedPanelsContentGroup">
           <div class="TabbedPanelsContent">
           </div>
            <div class="TabbedPanelsContent">
              <table width="100%">
              <tr>              
              <td colspan="8">
              
              <table width="100%" >
              <tr>
              <td width="1%">
              </td>
              <td align="left" width="59%" valign="top">
              DAERAH : $!nama_daerah
              
              
              
              </td>
              <td width="40%" align="right" valign="top">
              #if($senarai_hakmilik.size()!=0)
                No. Lot/PT : 
              
                    <label>
                    <input type="text" name="CariLot" id="CariLot"  />
                    </label>
                 
                      <label>
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Cari" onClick="cari_lot(CariLot.value,'$hakmilik_belumbatal')" />
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Kosongkan" onClick="cari_lot1(CariLot.value)"  />
                      </label>
                      #if($readmode == "edit")
                      <div id="jumlahlot" ></div>
                      #end
                      
            #end      
                  </td>
              </tr>
              </table>
              
              
             </td>
             </tr>
                <tr>
                  <td>
                  #if($maklumat_pembatalan.size() > 0)
                  #if($senarai_hakmilik.size()!=0)
                  <!--
                  <input name="cmdPilihPembatalanLot" id="cmdPilihPembatalanLot" type="button" value="Pilih Pembatalan Lot" onClick="batalkan_lot()">
                  -->
                  #end
                  #end
                  <table width="100%">
                    <tr class="table_header">
                      <td width="3%" align="center"><b>Bil</b></td>
                      <td width="15%"><b>No. Lot/PT</b></td>
                      <td width="15%"><b>Hakmilik atau Pendudukan</b></td>
                      <!--
                      <td width="19%">Tuan Punya Berdaftar atau Penduduk yang Direkodkan</td>
                      -->
                      <td width="28%"><b>Bandar/Pekan/Mukim</b></td>
                <!--      <td width="15%">Luas Lot</td> -->
                      <td width="15%"><b>Lebih Kurang Luas yang Hendak Diambil</b></td>
               <!--     <td width="15%">Lebih Kurang Luas yang Hendak Ditarik Balik</td>  -->
                      
                      
                       
                       #if($senarai_hakmilik.size()!=0)
                       
                       #if($readmode == "edit")
                      <td width="3%">
                      
                      <div align="center">
                      
                        <input type="checkbox" name="all" id="all" onClick="doCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');pilih_lot_tarik_all();check_lot_tarik()" title="Semak untuk pilih semua" $disabledmode />
                        
                       
                              </div>   
                                    
                
                                                       </td>
                      #end
                          #if($readmode == "edit")               
                       <td width="2%">
                      
                       <div align="center" class="style5"><font style="cursor:default"  onClick="open_new_window('1');this.style.cursor='help'" >i</font>                                 </div>                      </td>
                       
                       #end
                       #end 
                        </tr>
                   
                 
           #if($senarai_hakmilik.size()!=0)
           #set($tab_lot = 0)
           #foreach($list in $senarai_hakmilik)
           #set($tab_lot_tarik = $tab_lot+"TAB")
           
            #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
              
                <tr >  
                <td colspan="7"> 
              
<fieldset id="$list.BIL" style="visibility:collapse; display:none;" > 
 <table width="100%"  class="$row"  > 
  <tr  >
    <td width="1%">&nbsp;</td>
    <td width="28%">No Lot/PT</td>
    <td width="1%">:</td>
    <td width="70%">$list.NO_PT</td>
  </tr>
  <tr >
    <td width="1%">&nbsp;</td>
    <td width="28%">Negeri</td>
    <td width="1%">:</td>
    <td width="70%">$list.NAMA_NEGERI</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Daerah</td>
    <td>:</td>
    <td>$list.NAMA_DAERAH</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Mukim</td>
    <td>:</td>
    <td>$list.NAMA_MUKIM</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Jenis Hakmilik</td>
    <td>:</td>
    <td>$list.JENIS_HAKMILIK</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Kod Hakmilik</td>
    <td>:</td>
    <td>$list.KOD_JENIS_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Hakmilik</td>
    <td>:</td>
    <td> $list.NO_HAKMILIK</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Luas Lot</td>
    <td>:</td>
    <td>$list.LUAS_LOT</td>
  </tr>
  <tr  >
    <td>&nbsp;</td>
    <td>Luas Diambil</td>
    <td>:</td>
    <td>$list.LUAS_AMBIL</td>
  </tr>
  <!--
  <tr >
    <td>&nbsp;</td>
    <td>Pihak Kepentingan</td>
    <td>:</td>
    <td>
        #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )     
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #set($count_total = 0) 
                      
                      #if($count == 1)
                      #foreach($list1 in $senarai_pihak_penting)
                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      $list1.NAMA_PB
                      #end
                      #end
                      #elseif($count > 1)
                      
                      
                      
                      <select name=""   class="autoselect" >
                      #foreach($list1 in $senarai_pihak_penting)                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )  
                      #set( $ix = $velocityCount )
                      #if ( ($ix % 2) != 1 )
              		  #set( $rowx = "row2" )
         		      #else
               		  #set( $rowx = "row1" )
         		      #end                        
                      <option  >
                      $list1.NAMA_PB  
                      </option>
                      #end
                      #end
                      </select>
                      #else
                      
                      #end    </td>
  </tr>
  -->
</table>     
</fieldset>
</td>
</tr>

<tr style="display:none;" >
<td colspan="7" >

<fieldset id='$tab_lot_tarik' style="display:none;" >
<table style="background-color:#FFFFFF"  width="100%" >




<tr>
<td width="1%"></td>
<td width="28%">Jenis Luas</td>
<td width="1%"><div align="center">:</div></td>
<td width="70%">      

    <select name="socJenisLuas" class="autoselect" id="socJenisLuas"  onchange="pilih_jenis_luas(this.value,'$tab_lot','$senarai_hakmilik.size()');check_lot_tarik()">    
    <option value="">SILA PILIH JENIS LUAS</option>        
    #foreach($ln in $list_jenisluas)   
    <option value="$ln.ID_LUAS">$ln.KOD_LUAS - $ln.KETERANGAN</option>
    #end     
    </select>  &nbsp; <span align="center" class="style3"><em><font style="cursor:default; color:#0000FF"  onClick="open_new_window('3');this.style.cursor='help'" >i</font>                                 </em></span>
    #set($id_jenisluas = "$list.ID_HAKMILIK"+"JENISLUAS")
 <div id='$id_jenisluas' class="alert_msg" style="background:#FFFFFF; " >
 
 </div>
            
</td>
</tr>

#set($tr_luasharta = $tab_lot+"tr_luasharta")
#set($luas1 = $tab_lot+"luas1")
#set($luas2 = $tab_lot+"luas2")
#set($luas3 = $tab_lot+"luas3")
#set($tr_luasharta_b = $tab_lot+"tr_luasharta_b")
     
                              
                                              <tr id='$tr_luasharta' style="display:none;">
                                              <td>&nbsp;</td>
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td>
                                              <span id='$luas1' style="display:none;">
                                              <input name="txtLuasAsalHtaam1" type="text" id="txtLuasAsalHtaam1"  size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam1');ConvertLuasHartaPenarikan('$tab_lot','$senarai_hakmilik.size()','$list.ID_KATEGORITANAH');check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');" 
                                              onblur="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam1');ConvertLuasHartaPenarikan('$tab_lot','$senarai_hakmilik.size()','$list.ID_KATEGORITANAH');check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');" 
                                                />
                                              </span>
                                              <span id='$luas2' style="display:none;">
                                               <input name="txtLuasAsalHtaam2" type="text" id="txtLuasAsalHtaam2"  size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam2');ConvertLuasHartaPenarikan('$tab_lot','$senarai_hakmilik.size()','$list.ID_KATEGORITANAH');check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');" 
                                              onblur="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam2');ConvertLuasHartaPenarikan('$tab_lot','$senarai_hakmilik.size()','$list.ID_KATEGORITANAH');check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');"  
                                                 />
                                               </span>
                                               <span id='$luas3' style="display:none;">
                                                <input name="txtLuasAsalHtaam3" type="text" id="txtLuasAsalHtaam3"  size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam3');ConvertLuasHartaPenarikan('$tab_lot','$senarai_hakmilik.size()','$list.ID_KATEGORITANAH');check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');"  
                                               onblur="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam3');ConvertLuasHartaPenarikan('$tab_lot','$senarai_hakmilik.size()','$list.ID_KATEGORITANAH');check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');"  
                                                />
                                                </span>
                                                <span style="display:none" >                                            
                                             <span id='$tr_luasharta_b' style="display:none;">
                                              #set($id_convertbutton = "$list.ID_HAKMILIK"+"BUTTON")
                                                <input type="button" name='$id_convertbutton' id='$id_convertbutton' style="display:none"  value="Convert" onclick="ConvertLuasHartaPenarikan('$tab_lot','$senarai_hakmilik.size()','$list.ID_KATEGORITANAH');check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');" />
                                             </span>
                                             </span>
                                             #set($id_luasasal = "$list.ID_HAKMILIK"+"LUASASAL")
 												<div id='$id_luasasal' class="alert_msg" style="background:#FFFFFF; " ></div>
                                            
                                             </td>
                                            </tr>
<tr>
<td width="1%"></td>
<td width="28%" valign="top">Luas Penarikan Lot</td>
<td width="1%"  valign="top"><div align="center">:</div></td>
<td width="70%"  valign="top"><input name="lot_tarik" id="lot_tarik" type="text" onBlur="validateTarikh(this,this.value);check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');" onKeyUp="validateTarikh(this,this.value);check_lot_tarik();doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');"   size="30" maxlength="10"  >


#if($list.ID_UNITLUASAMBIL_CONVERT != "")
#if($list.ID_UNITLUASAMBIL_CONVERT == "2")
#set($meterhektar = "HEKTAR")
<input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" />
#else
#set($meterhektar = "METERPERSEGI")
<input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" />
#end
#end

<input name="unitluas" type="hidden" id="unitluas" value="$!list.ID_UNITLUASAMBIL_CONVERT" />


 #set($id_alertlot = "$list.ID_HAKMILIK"+"TARIK")
 <div id='$id_alertlot' class="alert_msg" style="background:#FFFFFF; " > </div></td>
</tr>
</table>
</fieldset>

           </td> 
                </tr>

                
                    <tr id="$list.BIL_DUM" class="$row" >
                      <td align="center">$list.BIL</td>
                      <td id="$list.NO_PT">
                      
                      <a class="style1" id="hoverover" style="cursor:default; color:#0000FF" onClick="ShowPopup(this,$list.BIL);" onMouseOut="HidePopup();" title="Klik untuk maklumat lengkap">$list.NO_PT</a>                      </td>
                      <td >$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
                      
                      <!--
                      <td >
                      #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )     
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #set($count_total = 0) 
                      
                      #if($count == 1)
                      #foreach($list1 in $senarai_pihak_penting)
                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      $list1.NAMA_PB
                      #end
                      #end
                      #elseif($count > 1)
                      
                      
                      
                      <select name=""  style="width:100%" >
                      #foreach($list1 in $senarai_pihak_penting)                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )  

                      #set( $ix = $velocityCount )
                      #if ( ($ix % 2) != 1 )
              		  #set( $rowx = "row2" )
         		      #else
               		  #set( $rowx = "row1" )
         		      #end                        
                      <option  >
                      $list1.NAMA_PB  
                      </option>
                      #end
                      #end
                      </select>
                      #else
                      
                      #end
                      -----
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      #set($count_total=$count_total + 1)
                      
                      #if($count > 1 && $count != $count_total) 
                      $list1.NAMA_PB,
                      #elseif($count > 1 && $count == $count_total)
                      $list1.NAMA_PB
                      #elseif($count == 1)
                      $list1.NAMA_PB
                      #end
                                          
                      #end
                      #end  
                     -----                     </td>
                      -->
                      <td >$list.NAMA_MUKIM</td>
                <!--      <td >$list.LUAS_LOT</td>  -->
                      <td >$list.LUAS_AMBIL
                      <input name="lot_ambil" id="lot_ambil"  style="width:100%" value="$list.LUAS_AMBIL_EDIT" type="hidden" >
                      <input name="luas_lot" id="luas_lot"  style="width:100%" value="$list.LUAS_LOT_EDIT" type="hidden" >
                      <input name="id_lot_tarik" id="id_lot_tarik"  style="width:100%" value="$list.ID_HAKMILIK" type="hidden" >
                       <input name="id_kat" id="id_kat"  style="width:100%" value="$list.ID_KATEGORITANAH" type="hidden" >
                      
                      
                      </td>
                       <!--
                      <td >
                      
                      #if($readmode == "view")
                      $list.LUAS_LOT_TARIK
                        <input name="lot_tarik" id="lot_tarik"  style="width:100%;display:none;" value="$list.LUAS_LOT_TARIK_EDIT" type="hidden" onBlur="validateTarikh(this,this.value);check_lot_tarik()" onKeyUp="validateTarikh(this,this.value);check_lot_tarik()"  >                  
                       <input name="id_lot_tarik" id="id_lot_tarik"  style="width:100%" value="$list.ID_HAKMILIK" type="hidden" >
                      #else
                   
                               
                       <input name="id_lot_tarik" id="id_lot_tarik"  style="width:100%" value="$list.ID_HAKMILIK" type="hidden" >
                      
                    
                      #end
                      
                      
                      </td>
                    -->
                      
                        #if($readmode == "edit")
                      <td >
                        <div align="center">
                        <label>
                       <input type="checkbox" name="ids" id="ids" onClick="doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');pilih_lot_tarik('$tab_lot');check_lot_tarik();" value="$list.ID_HAKMILIK" $disabledmode  title="Semak untuk pilih lot ini" >
                        </label>
                      
                        </div>                      </td>
                        #end   
                         #set($tab_lot = $tab_lot + 1)  
                         #if($readmode == "edit")
                          <td width="2%">
                            <div align="center" class="style3"><em><font style="cursor:default; color:#0000FF"  onClick="open_new_window('2');this.style.cursor='help'" >i</font>                                 </em></div></td>  
                            #end            
                         </tr>
                     
                        #end
              
              #else
              <tr>
              <td colspan="8">
              Tiada Rekod              </td>
              </tr>
              #end
                  </table></td>
                </tr>
              </table>
            </div>
            <div class="TabbedPanelsContent">
            
            
            
            </div>
            
            <div class="TabbedPanelsContent">
            
               #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
               <a href="javascript:popupCarianDokumen('$id_pembatalan','penarikan_balik')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
               #end  
               #if($jenis_permohonan == "2" || $jenis_permohonan == "1")
                <a href="javascript:popupCarianDokumen_batal('$id_pembatalan','pembatalan')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
               #end
             
            
            
            
            
            
            <!--
    #if($roleAgensi=="no")        
    #if($portal_role == "online_kjp")
    	#if($flag_online != "2" && $flag_online != "3")
    		#if(($id_jawatan_user == $layer1 && $flag_semakan_online=="") || 
							($id_jawatan_user == $layer2 && ($flag_semakan_online=="" || $flag_semakan_online=="1" )) ||
							($id_jawatan_user == $layer3 && ($flag_semakan_online=="" || $flag_semakan_online=="1" || $flag_semakan_online=="2")))
			    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" >
			    #if($listDokumen_size > 0)
			    <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumen()">
			    #end
		    #end
    	#end
    #else
    	#if($flag_online != "1" && $flag_online != "4")
		    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" >
		    #if($listDokumen_size > 0)
		    <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumen()">
		    #end
    	#end
    #end
    #end
    
    
	    <table width="100%">
	  <tr class="table_header">
	    <td width="5%" align="center"><b>Bil</b></td>
	    <td width="30%"><b>Nama Dokumen</b></td>
	    <td width="30%"><b>Keterangan</b></td>
	    <td width="30%"><b>Muat Turun</b></td>
	    #if($roleAgensi=="no")
	     #if($listDokumen_size > 0 )
	     	#if($portal_role == "online_kjp")
	     	  #if($flag_online != "2" && $flag_online != "3")
		      <td width="5%">
			      <div align="center">
			      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1()" title="Semak untuk pilih semua" />
			      </div>     
		      </td>
		      #end
		    #else
		      #if($flag_online != "1" && $flag_online != "4")
		      <td width="5%">
			      <div align="center">
			      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1()" title="Semak untuk pilih semua" />
			      </div>     
		      </td>
		      #end
	      	#end
	      #end
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
	    <td class="$row" align="center">$list1.BIL</td>
	    #if($jenis_permohonan == "3" || $jenis_permohonan == "1")
	    <td class="$row" >$list1.TAJUK</td>
	    #else
	    <td class="$row" ><a href="javascript:view_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.TAJUK</font></a></td>
	    #end
	    <td class="$row" >$list1.KETERANGAN</td>
	    <td class="$row"><a href="javascript:papar_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.NAMA_FAIL</font></a></td>  
	     
	     #if($roleAgensi=="no")
	     	#if($portal_role == "online_kjp")
	     	  #if($flag_online != "2" && $flag_online != "3")
		      <td class="$row" >
	    			<div align="center">
	       			<input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
	     			</div>
	    	</td>
		      #end
		    #else
		      #if($flag_online != "1" && $flag_online != "4")
		      <td class="$row" >
	    		<div align="center">
	       		<input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
	     		</div>
	    		</td>
		      #end
	      	#end
	     #end
	     
	     
	  </tr>
	  #end
	  #else
	  <tr>  
	    <td colspan="5">Tiada Rekod</td>    
	  </tr>
	  #end
	</table>
            -->
           </div>
           
          </div>
        </div></td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>
  <tr>
    <td colspan="2">
    <div align="center" >
    
    #if($roleAgensi=="no")
    
    #if($readmode == "view")
     
     
      #if($portal_role == "online_kjp")
      
	      #if($flag_online == "1" || $flag_online == "4")
		      #if(($id_jawatan_user == $layer1 && $flag_semakan_online=="") || 
							($id_jawatan_user == $layer2 && ($flag_semakan_online=="" || $flag_semakan_online=="1" )) ||
							($id_jawatan_user == $layer3 && ($flag_semakan_online=="" || $flag_semakan_online=="1" || $flag_semakan_online=="2")))
		      <label>
		      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini()">
		      </label>
		      
		      <label>
		      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">     
		      </label>
		      #end
	      #end
      
      #else
	      #if($flag_online != "1" && $flag_online != "4")
	      <label>
	      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini()">
	      </label>
	      <label>
	      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">     
	      </label>
	      #end
      #end
      
      
       
      
      #if($jenis_permohonan == "3" || $jenis_permohonan == "1")
	      #if($flag_online == "1" || $flag_online == "4")
	      
			  <!-- Pembantu Tadbir Hantar Untuk Semakan -->
          	  #if(($id_jawatan_user == $layer1) && ($flag_semakan_online==""))
          	  <input type="button" name="cmdHantarSemakan" value ="Hantar Untuk Semakan" onClick="javascript:hantarSemakan('$!id_pembatalan','$!jenis_permohonan')">
          	  #end
          				
          	  <!-- Penolong Pengarah Membuat Semakan -->
          	  #if($id_jawatan_user == $layer2 && $flag_semakan_online=="1")
          	  <input type="button" name="cmdSahSemakan" value ="Sahkan Semakan" onClick="javascript:sahSemakan('$!id_pembatalan','$!jenis_permohonan')" >
          	  #end
          				
			  <!-- Permohonan meluluskan permohonan -->
          	  #if($id_jawatan_user == $layer3 && $flag_semakan_online=="2")
	          <input type="button" name="cmdLulusPermohonan" value ="Luluskan Permohonan" onClick="javascript:lulusPermohonan('$!id_pembatalan','$!jenis_permohonan')" >	
	          #end	      	  
	      
		      #if($id_jawatan_user == $layer3 && $flag_semakan_online=="3")
		      <label>
		      <input type="button" name="cmdHantar " id="cmdHantar " value="Hantar Permohonan" onClick="hantar(1)">
		      </label>
		      #end
		      
	      #end
      #end

	#if($jenis_permohonan == "1" && $flag_online == "2")
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Pengesahan Permohonan" onclick="javascript:cetakPembatalan('$id_pembatalan')" /> 
      #end
      
      #if($jenis_permohonan == "3" && $flag_online == "2")
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Pengesahan Permohonan" onclick="javascript:cetakPenarikan('$id_pembatalan')" /> 
      #end
      
      #if($jenis_permohonan == "2" || $jenis_permohonan == "4")
	      #if($flag_online == "2")
	      <input type="button" name="cmdHantar1" id="cmdHantar1" value="Sahkan Permohonan" onClick="hantar(1)">
	      <input type="button" name="cmdHantar2" id="cmdHantar2" value="Permohonan Ditolak" onClick="hantar(2)">      
	      #end
      
      <!-- #if($flag_online == "3")
      <input type="button" name="cmdHantar2" id="cmdHantar2" value="Permohonan Ditolak" onClick="hantar(2)">      
      #end -->
      
      <!-- #if($flag_online == "4")
     <input type="button" name="cmdHantar1" id="cmdHantar1" value="Sahkan Permohonan" onClick="hantar(1)">    
      #end -->
      
      #end
    #end
    
    
    #if($readmode == "edit")  
      <label>
      #if(($jenis_permohonan == "4" || $jenis_permohonan == "2")  && ($flag_online != "1" && $flag_online != "2" && $flag_online != "3" && $flag_online != "4") )     
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan('$!senarai_hakmilik.size()')">
      #else
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan_reverse('$!senarai_hakmilik.size()')">
      #end
      
      
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()">
      </label>
    #end  
    <!--
     <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />-->
        #if($id_pembatalan != "")
        <!--  
       <label>
      <input type="button" name="cmdJanaSurat" id="cmdJanaSurat" value="Jana Surat"  onClick="jana_surat()"   />
      </label>
      -->
       <label>
          
      </label>
     
      #end
      
      #end
   
     </div>      </td>
  </tr>
 
  
  
</table>

			 #if($senarai_hakmilik_batal.size()!=0)
             #foreach($listb in $senarai_hakmilik_batal) 
              
                       <input name="lot_ambilX" id="lot_ambilX"  value="$listb.LUAS_AMBIL_EDIT" type="hidden" >
                       <input name="lot_ambil_asalX" id="lot_ambil_asalX"  value="$listb.LUAS_AMBIL_ASAL" type="hidden" >
                       <input name="luas_lotX" id="luas_lotX"  value="$listb.LUAS_LOT" type="hidden" >
                       <input name="id_hakmilik_luasX" id="id_hakmilik_luasX"  style="width:100%" value="$listb.ID_HAKMILIK" type="hidden" >
                       <input name="id_lot_tarikX" id="id_lot_tarikX"  value="$listb.ID_PENARIKANHAKMILIK" type="hidden" > 
                       <input name="lot_tarikX" id="lot_tarikX" value="$listb.LUAS_LOT_TARIK_EDIT" type="hidden"  >   
                 
             
             #end
             #end



<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="subminor_command" id="subminor_command" />
<input type="hidden" name="location" id="location" />
<input type="hidden" name="point" id="point" />
<input type="hidden" name="screen_name" id="screen_name" value="s1" />
<input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$id_pembatalan" />
<input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
<input type="hidden" name="alert_message" id="alert_message" />
<input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
<input type="hidden" name="jumlah_semua" id="jumlah_semua" />
<input type="hidden" name="id_permohonan" value="$!id_permohonan" />


<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue">Surat Makluman Kepada Agensi Pemohon</font></a></td>
      </tr>  
      <tr>
        <td><a href="#" class="style2" onClick="surat_HQ_negeri('$id_pembatalan')"><font color="blue">Surat Makluman Kepada Unit</font></a></td>
      </tr>         
        
      
      
    </table>
</fieldset>
<!--
hakmilikoverall :: $hakmilikoverall ;
hakmilik_belumbatal :: $hakmilik_belumbatal ;
hakmilik_sudahbatal :: $senarai_hakmilik_batal.size() ;
-->


<script type="text/javascript">

function popupCarianDokumen(id_penarikanbalik,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_penarikanbalik="+id_penarikanbalik+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function popupCarianDokumen_batal(id_pembatalan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_pembatalan="+id_pembatalan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function lulusPermohonan(id_pembatalan,jenisPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "lulusPermohonan";
	if(jenisPermohonan=="1"){
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	}else{
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	}
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "";
	document.${formName}.submit();
}
function sahSemakan(id_pembatalan,jenisPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "sahSemakan";
	if(jenisPermohonan=="1"){
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	}else{
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	}
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "";
	document.${formName}.submit();
}
function hantarSemakan(id_pembatalan,jenisPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hantarSemakan";
	if(jenisPermohonan=="1"){
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	}else{
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	}
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "";
	document.${formName}.submit();
}
function cetakPembatalan(id_pembatalan) {
	var url = "../servlet/ekptg.report.ppt.PengesahanPermohonanPembatalan?idpembatalan="+id_pembatalan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakPenarikan(id_penarikan) {
	var url = "../servlet/ekptg.report.ppt.PengesahanPermohonanPenarikan?idpenarikanbalik="+id_penarikan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
var readmode = '$readmode';
var jumlot = '$senarai_hakmilik.size()';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function submitForm() 
{


if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{

var a = '$location';
var b = '$point';
window.location.hash=a;
goTo(b);

}
else
{
window.location.hash='paging';
goTo('paging');

}




if(readmode == "edit")
{


if('$jenis_permohonan' == "3" ) 
{
check_length(document.${formName}.txtSebabPembatalan,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan penarikan');
}
if('$jenis_permohonan' == "1" ) 
{
check_length(document.${formName}.txtSebabPembatalan,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan pembatalan');
}


if('$jenis_permohonan' == "4" ) 
{
if('$flag_online' == "" ) 
{	
checking_validation(document.${formName}.txdTarikhTerimaSurat,'txdTarikhTerimaSurat_check','yes','terima surat','tarikh');
checking_validation(document.${formName}.txtNoRujSurat,'txtNoRujSurat_check','yes','no rujukan surat','normal');
checking_validation(document.${formName}.txdTarikhSurat,'txdTarikhSurat_check','yes','surat penarikan balik','tarikh');
}
check_length(document.${formName}.txtSebabPembatalan,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan penarikan');
}
if('$jenis_permohonan' == "2" ) 
{
if('$flag_online' == "" ) 
{
checking_validation(document.${formName}.txdTarikhTerimaSurat,'txdTarikhTerimaSurat_check','yes','terima surat','tarikh');
checking_validation(document.${formName}.txtNoRujSurat,'txtNoRujSurat_check','yes','no rujukan surat','normal');
checking_validation(document.${formName}.txdTarikhSurat,'txdTarikhSurat_check','yes','surat pembatalan','tarikh');
}
check_length(document.${formName}.txtSebabPembatalan,'4000','txtSebabPembatalan_check','txtSebabPembatalan_num','normal','yes','alasan pembatalan');
}



doCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');
doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$senarai_hakmilik_batal.size()');
check_lot_tarik();



if(jumlot > 0)
{
}

}


}

</script>







#if($jenis_permohonan == "4")
<script>

function papar(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "senarai";
	document.${formName}.sub_command.value = "papar";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.location.value = "senarai_penarikan";
	document.${formName}.point.value = "senarai_penarikan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}
function jana_surat()
{

    document.${formName}.command.value = "Maklumat_Tambahan";
	document.${formName}.sub_command.value = "view_maklumat_tambahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}



function kembali_senarai_depan()
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}

function PembatalanAmbilTanahPT()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.submit();
}
function viewDokumen()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";
	document.${formName}.submit();
}
function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}

function tambahDokumen()
{

	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "tambah_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_dokumen";
	document.${formName}.point.value = "txtnamadokumen";	
	document.${formName}.submit();
}

function paparDokumen()
{
	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "papar_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}

function hapusDokumen()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";	
	document.${formName}.submit();
		
	}
}

function kemaskini()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
}

function hantar(type)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		
	if(type=="1")
	{	
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	
	if(type=="2")
	{	
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "tolak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	
	
	}
	
}


function simpan_reverse(param)
{
	//alert('param'+param);
	var checkSelected=false;
	if(param>1){
		for(var i=0 ; i < document.${formName}.ids.length; i++) 
		{ 
    		//var total=0;
    		if (document.${formName}.ids[i].checked)
        	{
  				//total++;
  				checkSelected=true; 
  			}
		}
	}else{
		
		//if (document.${formName}.cblistob.checked)
		if (document.${formName}.ids.checked)
    	{
		
			//total++;
			checkSelected=true; 
    	}
	}


	if(document.${formName}.txtSebabPembatalan.value == ""){
		alert("Sila masukkan \"Sebab Pembatalan\" terlebih dahulu.");
  		document.${formName}.txtSebabPembatalan.focus(); 
		return;
	}else if(!checkSelected){
		alert("Sila pilih lot yang hendak ditarik balik terlebih dahulu.");
		return;
	}else{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "PembatalanAmbilTanahPT";
		document.${formName}.sub_command.value = "simpan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
		document.${formName}.location.value = "maklumat_tambahan";
		document.${formName}.point.value = "maklumat_tambahan";
		document.${formName}.submit();	
	}
}


function simpan(param)
{
	var checkSelected=false;
	if(param>1){
		for(var i=0 ; i < document.${formName}.ids.length; i++) 
		{ 
    		//var total=0;
    		if (document.${formName}.ids[i].checked)
        	{
  				//total++;
  				checkSelected=true; 
  			}
		}
	}else{
		
		//if (document.${formName}.cblistob.checked)
		if (document.${formName}.ids.checked)
    	{
		
			//total++;
			checkSelected=true; 
    	}
	}


	if(document.${formName}.txdTarikhSurat.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTarikhSurat.focus(); 
		return;
	}else if(document.${formName}.txdTarikhTerimaSurat.value == ""){
		alert("Sila masukkan \"Tarikh Terima Surat\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerimaSurat.focus(); 
		return;
	}else if(document.${formName}.txtNoRujSurat.value == ""){
		alert("Sila masukkan \"No. Rujukan Surat Tarik Balik\" terlebih dahulu.");
  		document.${formName}.txtNoRujSurat.focus(); 
		return;
	}else if(document.${formName}.txtSebabPembatalan.value == ""){
		alert("Sila masukkan \"Sebab Pembatalan\" terlebih dahulu.");
  		document.${formName}.txtSebabPembatalan.focus(); 
		return;
	}else if(!checkSelected){
		alert("Sila pilih lot yang hendak ditarik balik terlebih dahulu.");
		return;
	}else{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "PembatalanAmbilTanahPT";
		document.${formName}.sub_command.value = "simpan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
		document.${formName}.location.value = "maklumat_tambahan";
		document.${formName}.point.value = "maklumat_tambahan";
		document.${formName}.submit();	
	}

/*	
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
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
	
}	
*/
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
	}
}

function batalkan_lot()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batalkan_lot";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
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

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}




</script>

#end





#if($jenis_permohonan == "3")
<script>

function simpan_reverse(param)
{
	//alert('param'+param);
	var checkSelected=false;
	if(param>1){
		for(var i=0 ; i < document.${formName}.ids.length; i++) 
		{ 
    		//var total=0;
    		if (document.${formName}.ids[i].checked)
        	{
  				//total++;
  				checkSelected=true; 
  			}
		}
	}else{
		
		//if (document.${formName}.cblistob.checked)
		if (document.${formName}.ids.checked)
    	{
		
			//total++;
			checkSelected=true; 
    	}
	}


	if(document.${formName}.txtSebabPembatalan.value == ""){
		alert("Sila masukkan \"Sebab Pembatalan\" terlebih dahulu.");
  		document.${formName}.txtSebabPembatalan.focus(); 
		return;
	}else if(!checkSelected){
		alert("Sila pilih lot yang hendak ditarik balik terlebih dahulu.");
		return;
	}else{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "PembatalanAmbilTanahPT";
		document.${formName}.sub_command.value = "simpan";
		//document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
		document.${formName}.location.value = "maklumat_tambahan";
		document.${formName}.point.value = "maklumat_tambahan";
		document.${formName}.submit();	
	}
}

function papar(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "senarai";
	document.${formName}.sub_command.value = "papar";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.location.value = "senarai_penarikan";
	document.${formName}.point.value = "senarai_penarikan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}
function jana_surat()
{

    document.${formName}.command.value = "Maklumat_Tambahan";
	document.${formName}.sub_command.value = "view_maklumat_tambahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}



function kembali_senarai_depan()
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}

function PembatalanAmbilTanahPT()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.submit();
}
function viewDokumen()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";
	document.${formName}.submit();
}
function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}

function tambahDokumen()
{

	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "tambah_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_dokumen";
	document.${formName}.point.value = "txtnamadokumen";	
	document.${formName}.submit();
}

function paparDokumen()
{
	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "papar_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}

function hapusDokumen()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";	
	document.${formName}.submit();
		
	}
}

function kemaskini()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
}

function hantar(type)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		
	if(type=="1")
	{	
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	
	if(type=="2")
	{	
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "tolak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	
	
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
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
	
}	
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
	}
}

function batalkan_lot()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batalkan_lot";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
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

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}




</script>

#end











#if($jenis_permohonan == "2")
<script>

function papar(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "senarai";
	document.${formName}.sub_command.value = "papar";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.location.value = "senarai_penarikan";
	document.${formName}.point.value = "senarai_penarikan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.submit();
}
function jana_surat()
{

    document.${formName}.command.value = "Maklumat_Tambahan";
	document.${formName}.sub_command.value = "view_maklumat_tambahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.submit();
}



function kembali_senarai_depan()
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.submit();
}

function PembatalanAmbilTanahPT()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.submit();
}
function viewDokumen()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";
	document.${formName}.submit();
}
function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}

function tambahDokumen()
{

	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "tambah_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "maklumat_dokumen";
	document.${formName}.point.value = "txtnamadokumen";	
	document.${formName}.submit();
}

function paparDokumen()
{
	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "papar_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.submit();
}

function hapusDokumen()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";	
	document.${formName}.submit();
		
	}
}

function kemaskini()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
}

function hantar(type)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		
	if(type=="1")
	{	
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	
	if(type=="2")
	{	
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "tolak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	
	
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
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
	
}	
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
	}
}

function batalkan_lot()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batalkan_lot";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
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

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}




</script>

#end






#if($jenis_permohonan == "1")
<script>

function simpan_reverse(param)
{
	//alert('param'+param);
	var checkSelected=false;
	if(param>1){
		for(var i=0 ; i < document.${formName}.ids.length; i++) 
		{ 
    		//var total=0;
    		if (document.${formName}.ids[i].checked)
        	{
  				//total++;
  				checkSelected=true; 
  			}
		}
	}else{
		
		//if (document.${formName}.cblistob.checked)
		if (document.${formName}.ids.checked)
    	{
		
			//total++;
			checkSelected=true; 
    	}
	}


	if(document.${formName}.txtSebabPembatalan.value == ""){
		alert("Sila masukkan \"Sebab Pembatalan\" terlebih dahulu.");
  		document.${formName}.txtSebabPembatalan.focus(); 
		return;
	}else if(!checkSelected){
		alert("Sila pilih lot yang hendak dibatalkan terlebih dahulu.");
		return;
	}else{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "PembatalanAmbilTanahPT";
		document.${formName}.sub_command.value = "simpan";
		//document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
		document.${formName}.location.value = "maklumat_tambahan";
		document.${formName}.point.value = "maklumat_tambahan";
		document.${formName}.submit();	
	}
}

function papar(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "senarai";
	document.${formName}.sub_command.value = "papar";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.location.value = "senarai_penarikan";
	document.${formName}.point.value = "senarai_penarikan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.submit();
}
function jana_surat()
{

    document.${formName}.command.value = "Maklumat_Tambahan";
	document.${formName}.sub_command.value = "view_maklumat_tambahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.submit();
}



function kembali_senarai_depan()
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.submit();
}

function PembatalanAmbilTanahPT()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.submit();
}
function viewDokumen()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";
	document.${formName}.submit();
}
function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}

function tambahDokumen()
{

	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "tambah_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "maklumat_dokumen";
	document.${formName}.point.value = "txtnamadokumen";	
	document.${formName}.submit();
}

function paparDokumen()
{
	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "papar_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.submit();
}

function hapusDokumen()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";	
	document.${formName}.submit();
		
	}
}

function kemaskini()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
}

function hantar(type)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		
	if(type=="1")
	{	
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	
	if(type=="2")
	{	
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "tolak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	
	
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
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
	
}	
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
	}
}

function batalkan_lot()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batalkan_lot";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
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

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}


/* function simpan_reverse(param)
{
	//alert('param'+param);
	var checkSelected=false;
	if(param>1){
		for(var i=0 ; i < document.${formName}.ids.length; i++) 
		{ 
    		//var total=0;
    		if (document.${formName}.ids[i].checked)
        	{
  				//total++;
  				checkSelected=true; 
  			}
		}
	}else{
		
		//if (document.${formName}.cblistob.checked)
		if (document.${formName}.ids.checked)
    	{
		
			//total++;
			checkSelected=true; 
    	}
	}


	if(document.${formName}.txtSebabPembatalan.value == ""){
		alert("Sila masukkan \"Sebab Pembatalan\" terlebih dahulu.");
  		document.${formName}.txtSebabPembatalan.focus(); 
		return;
	}else if(!checkSelected){
		alert("Sila pilih lot yang hendak ditarik balik terlebih dahulu.");
		return;
	}else{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "PembatalanAmbilTanahPT";
		document.${formName}.sub_command.value = "simpan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
		document.${formName}.location.value = "maklumat_tambahan";
		document.${formName}.point.value = "maklumat_tambahan";
		document.${formName}.submit();	
	}
} */

</script>

#end








<script>


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


function doCheckAll_P(){    
    if (document.${formName}.sorJenisPembatalan[1].checked == true){
        if (document.${formName}.ids.length == null)
		{
            document.${formName}.ids.checked = true;
        } else 
		{
            for (i = 0; i < document.${formName}.ids.length; i++)
			{
                document.${formName}.ids[i].checked = true;
            }
        }
    } 
		
	 else if (document.${formName}.sorJenisPembatalan[0].checked == true)	
	 {
        if (document.${formName}.ids.length == null){
            document.${formName}.ids.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids.length; i++){
                document.${formName}.ids[i].checked = false;
            }
        }
    }
	
	
	
}




function ReadOnly_CheckBox(v)
{
return false;
}



function ShowPopup(hoveritem,tab)
{
//alert("hp.style.display :"+hp.style.display+";hp.style.visibility :"+hp.style.visibility);
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
		 // DateField.focus();
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
	  // alert("#"+point)
	   
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	  
	   /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
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
	  // DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	 //  $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan tarikh "+value_field);
	 
	 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
	 
	 
	 
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila masukkan tarikh "+value_field+" dengan betul");
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
	  // $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field);
	  
	  $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
	  
	  
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
	   
	   
	    if(jenis_field == "drop")
	   {
	  
	   if((field.value == "" || field.value == "0") && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	 //  $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pilih "+value_field);
	 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
	   /*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
	   }
	   else
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	   }
	   
	   	   
	   }
	   
	   
	   
	   
	   
	
}



function cari_lot(field,jl)
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

function doCheckAll(jumlah_lot,belumbatal,sudahbatal){ 
    var valid = "yes"
	var c = 0; 
	var p = 0; 	 
	var x1 = 0; 	 
	var x2 = 0; 	 
	var jl = jumlah_lot;
	var bb = belumbatal;
	var sb = sudahbatal;
	
	// ************************** //
	
        if (document.${formName}.lot_ambilX != null){		
        if (document.${formName}.lot_ambilX.length == null){
		var xx = 0;
        if( document.${formName}.lot_ambilX.value != "") 
	    {
	    xx = parseFloat(document.${formName}.lot_ambilX.value);
		}		
		if(xx == 0)
		{
		x1++;
		}
		else
		{
		x2++;
		}			  	 	
        } else {
        for (x = 0; x < document.${formName}.lot_ambilX.length; x++){		
        var xx = 0;
        if(  document.${formName}.lot_ambilX[x].value != "") 
	    {
	    xx = parseFloat(document.${formName}.lot_ambilX[x].value);
		}
		if(xx == 0)
		{
		x1++;
		}
		else
		{
		x2++;
		}		   	
        }
		}
		}	
	
	
	
	if(jl == 0 && belumbatal == 0)
	{
	
	//alert("jumlah_lot"+jl+"belum_batal"+belumbatal)
	
	$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan pasti permohonan mempunyai senarai lot sebelum pembatalan sebahagian atau keseluruhan dibuat");	
	 $jquery("#field_jenis_batal").html("");	
	}
	else
	{	
    if (document.${formName}.all.checked == true){
		
        if (document.${formName}.ids.length == null){	
              document.${formName}.ids.checked = true;
			  document.getElementById(0+"TAB").style.display="block";          
			  c++;			  	 	
        } else {
        for (i = 0; i < document.${formName}.ids.length; i++){		
               document.${formName}.ids[i].checked = true;
			    document.getElementById(i+"TAB").style.display="block";	 	       		
			   c++;	   	
        }
		}	
	
	   document.${formName}.jumlah_dipilih.value  = c;	
	   document.${formName}.jumlah_semua.value  = belumbatal;	
	   $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+c+"</span> / <span  style='color:#0000FF'>"+belumbatal+"</span>");
	   
	  if(c == jl)
	  {
	  var x1a = 0;
	  var x2a = 0;
	  
	    if (document.${formName}.lot_ambil != null){		
        if (document.${formName}.lot_ambil.length == null){
		var xxa = 0;
        if( document.${formName}.lot_tarik.value != "" && document.${formName}.lot_ambil.value != "") 
	    {
	    xxa = (parseFloat(document.${formName}.lot_tarik.value) - parseFloat(document.${formName}.lot_ambil.value))
		}		
		if(xxa == 0)
		{
		x1a++;
		}
		else
		{
		x2a++;
		}			  	 	
        } else {
        for (x = 0; x < document.${formName}.lot_ambil.length; x++){		
        var xxa = 0;
        if( document.${formName}.lot_tarik[x].value != "" && document.${formName}.lot_ambil[x].value != "") 
	    {
	    xxa = (parseFloat(document.${formName}.lot_tarik[x].value) - parseFloat(document.${formName}.lot_ambil[x].value))
		}
		if(xxa == 0)
		{
		x1a++;
		}
		else
		{
		x2a++;
		}		   	
        }
		}
		}
					
	  if(x2a > 0)
	  {
	  
	 // alert("::1");	   
	  document.${formName}.sorJenisPembatalan[0].checked = true;
	  document.${formName}.sorJenisPembatalan[1].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[0].checked = true;
	  document.${formName}.sorJenisPembatalanD[1].checked = false; 
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT");
	  }
	  else
	  {
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  $jquery("#field_jenis_batal").html("KESELURUHAN LOT");
	  }
	

	  }
	  else if((parseInt(c)+parseInt(sb)) == jl )
	  {	
	 
	  	var x1a = 0;
	    var x2a = 0;
	  
	    if (document.${formName}.lot_ambil != null){		
        if (document.${formName}.lot_ambil.length == null){
		var xxa = 0;
        if( document.${formName}.lot_tarik.value != "" && document.${formName}.lot_ambil.value != "") 
	    {
	    xxa = (parseFloat(document.${formName}.lot_tarik.value) - parseFloat(document.${formName}.lot_ambil.value))
		}
		if(xxa == 0)
		{
		x1a++;
		}
		else
		{
		x2a++;
		}			  	 	
        } else {
        for (x = 0; x < document.${formName}.lot_ambil.length; x++){		
        var xx = 0;
        if( document.${formName}.lot_tarik[x].value != "" && document.${formName}.lot_ambil[x].value != "") 
	    {
	    xx = (parseFloat(document.${formName}.lot_tarik[x].value) - parseFloat(document.${formName}.lot_ambil[x].value))
		}
		if(xx == 0)
		{
		x1a++;
		}
		else
		{
		x2a++;
		}		   	
        }
		}
		}
					
	  if(x2a > 0 || x2 > 0 )
	  {	
	//  alert("::2");	  		  
	  document.${formName}.sorJenisPembatalan[0].checked = true;
	  document.${formName}.sorJenisPembatalan[1].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[0].checked = true;
	  document.${formName}.sorJenisPembatalanD[1].checked = false; 
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT");
	  }
	  else
	  {
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  $jquery("#field_jenis_batal").html("KESELURUHAN LOT");
	  }
	
	
	  }
	  else if(c < jl && c != 0)
	  {
	//   alert("::3");	 	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true;	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 	  
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT ("+c+" daripada "+jl+")");
	
	  }
	  
	  else if(c == 0 && sb == 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false;  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  valid = "no";	  
	  $jquery("#field_jenis_batal").html("");	  
	  }
	  
	  else if(c == 0 && sb > 0)
	  {				
	  if(x2 > 0 )
	  {	 
	//   alert("::3");	 	 
	  document.${formName}.sorJenisPembatalan[0].checked = true;
	  document.${formName}.sorJenisPembatalan[1].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[0].checked = true;
	  document.${formName}.sorJenisPembatalanD[1].checked = false; 
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT");
	  }
	  else
	  {
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  $jquery("#field_jenis_batal").html("KESELURUHAN LOT");
	  }
	
	  }
	  	
    } else {
        if (document.${formName}.ids.length == null){		
            document.${formName}.ids.checked = false;
			   document.getElementById(0+"TAB").style.display="none";	 	 
	         document.${formName}.lot_tarik.value="";		
			p++;			
        } else {
            for (i = 0; i < document.${formName}.ids.length; i++){			
                document.${formName}.ids[i].checked = false;
			    document.getElementById(i+"TAB").style.display="none";	 	 
	            document.${formName}.lot_tarik[i].value="";			
				p++;			
            }
			
		}	
	  document.${formName}.jumlah_dipilih.value  = 0;	
	  document.${formName}.jumlah_semua.value  = belumbatal;	  	
	  $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+0+"</span> / <span  style='color:#0000FF'>"+belumbatal+"</span>");
	  if(p == jl)
	  {		 
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 	  
	  $jquery("#field_jenis_batal").html("");	  
	  valid = "no";	
	  }
	  else if(p < jl && p != 0)
	  {
	//  alert("p4"+p+";jl4"+jl);
	//  alert("::4");	 	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true; 	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 	  
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT ("+p+" daripada "+jl+")");	
	  }  
	  
	   
	 
	        
    }
	if(valid == "no")
	{
	
	
	if('$jenis_permohonan' == "3" || '$jenis_permohonan' == "4" )
{
$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan pilihan penarikan balik adalah sebahagian atau keseluruhan");	
}


	if('$jenis_permohonan' == "1" || '$jenis_permohonan' == "2" )
{
$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan pilihan pembatalan adalah sebahagian atau keseluruhan");	
}
	
		
	}
	else
	{
	$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
	}
	
	
	}
	
}





function doUpdateCheckAll(jumlah_lot,belumbatal,sudahbatal){ 

var valid = "yes";
var c = 0;
var x1 = 0;
var x2 = 0;
var semua = 0;
var jl = jumlah_lot;
var bb = belumbatal;
var sb = sudahbatal;



 		if (document.${formName}.lot_ambilX != null){		
        if (document.${formName}.lot_ambilX.length == null){
		var xx = 0;
        if( document.${formName}.lot_ambilX.value != "") 
	    {
	    xx = parseFloat(document.${formName}.lot_ambilX.value);
		}		
		if(xx == 0)
		{
		x1++;
		}
		else
		{
		x2++;
		}			  	 	
        } else {
        for (x = 0; x < document.${formName}.lot_ambilX.length; x++){		
        var xx = 0;
        if( document.${formName}.lot_ambilX[x].value != "") 
	    {
	    xx =  parseFloat(document.${formName}.lot_ambilX[x].value);
		}
		if(xx == 0)
		{
		x1++;
		}
		else
		{
		x2++;
		}		   	
        }
		}
		}	
	
 



if(jl == 0 && bb == 0)
	{
	//alert("jumlah_lot"+jl+"belum_batal"+bb)
	$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan pasti permohonan mempunyai senarai lot sebelum pembatalan sebahagian atau keseluruhan dibuat");
	$jquery("#field_jenis_batal").html("");	
	
	}
	else
	{


if(document.${formName}.ids.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids.length; i++)
	  {
	  semua++
      if (document.${formName}.ids[i].checked == true)
	  {	
	
	  document.getElementById(i+"TAB").style.display="block";	
	  
	  c++
	  
      }
	  else
	  {	 
	  document.getElementById(i+"TAB").style.display="none";	 	 
	  document.${formName}.lot_tarik[i].value="";		 
      }
	  }  
}
else
{

	semua = 1
	if (document.${formName}.ids.checked == true)
	{
	document.getElementById(0+"TAB").style.display="block";	
	 
	  c++
	  
	}	
	else
	{	 
	  document.getElementById(0+"TAB").style.display="none";	 
	document.${formName}.lot_tarik.value="";	 
    } 	
}	 
 
	   document.${formName}.jumlah_dipilih.value  = c;	
	   document.${formName}.jumlah_semua.value  = belumbatal;
	   
	   $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+c+"</span> / <span  style='color:#0000FF'>"+belumbatal+"</span>");	
/*	   
	 if( document.${formName}.lot_ambil[x].value == 0) 
	    {
	    x1a++;
		}
		else{
		var xxa = 0;
		if (document.${formName}.lot_ambil != null){		
        if (document.${formName}.lot_ambil.length == null){		
        if(document.${formName}.lot_ambil.value = 0) 
	    {
	 
		}
		}
		else
		{
		 for (x = 0; x < document.${formName}.lot_ambil.length; x++){		
       
        if( document.${formName}.lot_tarik[x].value != "" && document.${formName}.lot_ambil[x].value != "") 
	    {
	    xx = (parseFloat(document.${formName}.lot_tarik[x].value) - parseFloat(document.${formName}.lot_ambil[x].value))
		}
		}
		}
		}
		
		
		
		
		
		
		
		
		
		
		
		*/
	
	  if(c==semua)
	  {	  
	  document.${formName}.all.checked = true;	
	  }
	  else
	  {
	  document.${formName}.all.checked = false;	
	  }
	  
	   
	   if(c == jl)
	  {	  
	    var x1a = 0;
	    var x2a = 0;	  
	    if (document.${formName}.lot_ambil != null){		
        if (document.${formName}.lot_ambil.length == null){
		var xxa = 0;
		
        if( document.${formName}.lot_tarik.value != "" && document.${formName}.lot_ambil.value != "") 
	    {
	    xxa = (parseFloat(document.${formName}.lot_tarik.value) - parseFloat(document.${formName}.lot_ambil.value))
		}
		
		
		
		if(xxa == 0)
		{
		x1a++;
		}
		else
		{
	
		x2a++;
		
		}			  	 	
        } else {
        for (x = 0; x < document.${formName}.lot_ambil.length; x++){		
        var xx = 0;
        if( document.${formName}.lot_tarik[x].value != "" && document.${formName}.lot_ambil[x].value != "") 
	    {
	    xx = (parseFloat(document.${formName}.lot_tarik[x].value) - parseFloat(document.${formName}.lot_ambil[x].value))
		}
		
		if(xx == 0)
		{
		x1a++;
		}
		else
		{
		
		x2a++;
		
		}		   	
        }
		}
		}
					
	  if(x2a > 0)
	  {	
	 //  alert("::5");	  		  
	  document.${formName}.sorJenisPembatalan[0].checked = true;
	  document.${formName}.sorJenisPembatalan[1].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[0].checked = true;
	  document.${formName}.sorJenisPembatalanD[1].checked = false; 
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT");
	  }
	  else
	  {
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  $jquery("#field_jenis_batal").html("KESELURUHAN LOT");
	  }
	
	  }
	  
	  else if((parseInt(c)+parseInt(sb)) == jl )
	  {	
	
//	alert("A");
	    var x1a = 0;
	    var x2a = 0;	  
	    if (document.${formName}.lot_ambil != null){		
        if (document.${formName}.lot_ambil.length == null){
		var xxa = 0;
        if( document.${formName}.lot_tarik.value != "" && document.${formName}.lot_ambil.value != "") 
	    {
	    xxa = (parseFloat(document.${formName}.lot_tarik.value) - parseFloat(document.${formName}.lot_ambil.value))
		}
		if(xxa == 0)
		{
		x1a++;
		}
		else
		{
		x2a++;
		}			  	 	
        } else {
        for (x = 0; x < document.${formName}.lot_ambil.length; x++){		
        var xx = 0;
        if( document.${formName}.lot_tarik[x].value != "" && document.${formName}.lot_ambil[x].value != "") 
	    {
	    xx = (parseFloat(document.${formName}.lot_tarik[x].value) - parseFloat(document.${formName}.lot_ambil[x].value))
		}
		if(xx == 0)
		{
		x1a++;
		}
		else
		{
		x2a++;
		}		   	
        }
		}
		}
		
	 // alert("TTT"+x2a);
					
	  if(x2a > 0 || x2 > 0)
	  {	
	 //  alert("::6");	  		  
	  document.${formName}.sorJenisPembatalan[0].checked = true;
	  document.${formName}.sorJenisPembatalan[1].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[0].checked = true;
	  document.${formName}.sorJenisPembatalanD[1].checked = false; 
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT");
	  }
	  else
	  {
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false;	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  $jquery("#field_jenis_batal").html("KESELURUHAN LOT");
	  }
	  
	  }
	  
	//  else if(c < jl && c != 0)
	 else if(((parseInt(c)+parseInt(sb)) != jl) && (c != 0 || sb != 0))	
	  {
	//  alert("B");	
	//  alert("c_sb"+(parseInt(c)+parseInt(sb))+";jl4"+jl);
	//  alert("::7");	 
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true;	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true;	  
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT ("+(parseInt(c)+parseInt(sb))+" daripada "+jl+")");	
	  }
	  
	  else if(c == 0 && sb == 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 	  
	  $jquery("#field_jenis_batal").html("");
	  valid = "no";
	  }	  
	  else if(c == 0 && sb > 0)
	  {	
	  // alert("::8");	 
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true;	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 	  
	  $jquery("#field_jenis_batal").html("SEBAHAGIAN LOT ("+c+" daripada "+jl+")");	
	  }
	  
	if(valid == "no")
	{
	
	if('$jenis_permohonan' == "3" || '$jenis_permohonan' == "4" )
	{	
	$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan pilihan penarikan balik adalah sebahagian atau keseluruhan");		
	}
	
	if('$jenis_permohonan' == "1" || '$jenis_permohonan' == "2" )
	{	
	$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan pilihan pembatalan adalah sebahagian atau keseluruhan");		
	}
	
	
	}
	else
	{   
	$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");		
	}
	   
	}   
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




function check_lot_tarik()
{

	  if(document.${formName}.id_lot_tarik.length > 1)
      {     
	  for (i = 0; i < document.${formName}.id_lot_tarik.length; i++)
	  {
	  
      if (document.${formName}.lot_tarik[i].value == "" && document.${formName}.ids[i].checked == true)
	  {	 
	  $jquery("#"+document.${formName}.id_lot_tarik[i].value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan luas lot");	  
	 /*	   document.${formName}.alert_message.value  = "Sila masukkan luas lot";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName); */
		   	  
      }
	  else if((parseFloat(document.${formName}.lot_tarik[i].value) > parseFloat(document.${formName}.lot_ambil[i].value)) && document.${formName}.ids[i].checked == true)
	  {	
	   $jquery("#"+document.${formName}.id_lot_tarik[i].value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan luas penarikan tidak melebihi daripada luas ambil");	  
	 /*
	       document.${formName}.alert_message.value  = "Sila pastikan luas lot kurang daripada luas ambil";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   	 
      }
	  
	  
	  else if((parseFloat(document.${formName}.lot_tarik[i].value) <= 0) && document.${formName}.ids[i].checked == true)
	  {	
	   $jquery("#"+document.${formName}.id_lot_tarik[i].value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan luas penarikan melebihi daripada 0");	
	   /*
	 	   document.${formName}.alert_message.value  = "Sila pastikan luas lot lebih daripada 0";	 
	       url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		    
      }
	  else 
	  {	 
	  $jquery("#"+document.${formName}.id_lot_tarik[i].value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	  /*
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";
		   */	 
      }  
	  
	  }  
}
else
{

	if (document.${formName}.lot_tarik.value == "" && document.${formName}.ids.checked == true)
	  {	
	  $jquery("#"+document.${formName}.id_lot_tarik.value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan luas lot"); 
	  /*
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   	  
      }
	  else if((parseFloat(document.${formName}.lot_tarik.value) > parseFloat(document.${formName}.lot_ambil.value)) && document.${formName}.ids.checked == true)
	  {	 
	   $jquery("#"+document.${formName}.id_lot_tarik.value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan luas penarikan tidak melebihi daripada luas ambil"); 
	   /*
	       document.${formName}.alert_message.value  = "Sila pastikan luas lot kurang daripada luas ambil";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   	 
      }
	  
	  else if((parseFloat(document.${formName}.lot_tarik.value) <= 0) && document.${formName}.ids.checked == true)
	  {
	   $jquery("#"+document.${formName}.id_lot_tarik.value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila pastikan luas penarikan melebihi daripada 0");
	   /* 	
	 	   document.${formName}.alert_message.value  = "Sila pastikan luas lot lebih daripada 0";	 
	       url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		    
      }
	  else 
	  {	 
	  $jquery("#"+document.${formName}.id_lot_tarik.value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	  /*
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";	 
		   */
      } 	
	  
}

   
	  if(document.${formName}.id_lot_tarik.length > 1)
      {	
	
      var c_alert = 0;	  
	  for (i = 0; i < document.${formName}.socJenisLuas.length; i++)
	  {	
	  var val = document.${formName}.socJenisLuas[i].value;	
	 
      if ((val != "" && val != "0") && document.${formName}.ids[i].checked == true)
	  {	 
	 	   
	    if(val == 9)
		{
			
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
			{
			
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";  
			}	
			else
			{
			
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block"; 
		    	 
			}	
		}
		else if(val == 7)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "" || document.${formName}.txtLuasAsalHtaam2[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}
		}
		else if(val == 4)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "" || document.${formName}.txtLuasAsalHtaam2[i].value == "" || document.${formName}.txtLuasAsalHtaam3[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			} 
		}
		else if(val == 2)
		{
			
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}  
		}
		else if(val == 5)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}    
		}
		else if(val == 3)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			} 
		}
		else if(val == 6)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
					{
					c_alert ++;
					document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}
		}
		else if(val == 1)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
					{
					c_alert ++;
					document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}  
		}
		
		else if(val == 8)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "" || document.${formName}.txtLuasAsalHtaam2[i].value == "" || document.${formName}.txtLuasAsalHtaam3[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}
		} 		
      }
	  if (c_alert > 0)
	  {	
	  $jquery("#"+document.${formName}.id_lot_tarik[i].value+"LUASASAL").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila masukkan luas lot awal");	
	    
	  /* 
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot awal";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"LUASASAL";
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */	   	  
      }
	   else 
	  {	
	   $jquery("#"+document.${formName}.id_lot_tarik[i].value+"LUASASAL").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   /* 	 
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"LUASASAL";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";	
		   */ 
      }
	   	
	  }
	  }
	  

	// else if(document.${formName}.socJenisLuas.length == 1)
	  else
      {	
	 
	  var c_alert = 0;
	
	  var val = document.${formName}.socJenisLuas.value;	
	 
      if ((val != "" && val != "0") && document.${formName}.ids.checked == true)
	  {	 
	 	   
	    if(val == 9)
		{		
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}		
		}
		else if(val == 7)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "" || document.${formName}.txtLuasAsalHtaam2.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}
		}
		else if(val == 4)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "" || document.${formName}.txtLuasAsalHtaam2.value == "" || document.${formName}.txtLuasAsalHtaam3.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}  
		}
		else if(val == 2)
		{
			
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}   
		}
		else if(val == 5)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}    
		}
		else if(val == 3)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}  
		}
		else if(val == 6)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
					{
					c_alert ++;
					document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}
		}
		else if(val == 1)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
					{
					c_alert ++;
					document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}  
		}
		
		else if(val == 8)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "" || document.${formName}.txtLuasAsalHtaam2.value == "" || document.${formName}.txtLuasAsalHtaam3.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			} 
		}
		
  
      }
	  
	 
	  if (c_alert > 0)
	  {
	   $jquery("#"+document.${formName}.id_lot_tarik.value+"LUASASAL").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila masukkan luas lot awal");	
	   /*	 
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot awal";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"LUASASAL";
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */	   	  
      }
	   else 
	  {	
	  $jquery("#"+document.${formName}.id_lot_tarik.value+"LUASASAL").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	  /* 	 
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"LUASASAL";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";	 */
      } 	
	  
	  }
	  
	  	   
	
}


function pilih_jenis_luas(jenisluas,index,jumlah_lot)
{

if(jumlah_lot>1)
{


  var val = jenisluas;
  var l1 = index+"luas1";
  var l2 = index+"luas2";
  var l3 = index+"luas3";
  var tr_luas = index+"tr_luasharta";
  var tr_luas_b = index+"tr_luasharta_b";
 
    document.${formName}.txtLuasAsalHtaam1[index].value= "";
    document.${formName}.txtLuasAsalHtaam2[index].value= "";
    document.${formName}.txtLuasAsalHtaam3[index].value= "";
    document.${formName}.lot_tarik[index].value= "";
  	document.getElementById(tr_luas).style.display="none";	
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";
	
	if(val == 9)
{

	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
	

}

else if(val == 7)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 4)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}

else if(val == 2)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 5)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 3)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 6)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 1)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 8)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}


else
{
    document.getElementById(tr_luas).style.display="none";
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

	
	
	
}
else if(jumlah_lot==1)	
{
  var val = jenisluas;
  var l1 = 0+"luas1";
  var l2 = 0+"luas2";
  var l3 = 0+"luas3";
  var tr_luas = 0+"tr_luasharta";
  var tr_luas_b = 0+"tr_luasharta_b";
    document.${formName}.txtLuasAsalHtaam1.value= "";
    document.${formName}.txtLuasAsalHtaam2.value= "";
    document.${formName}.txtLuasAsalHtaam3.value= "";
    document.${formName}.lot_tarik.value= "";
  	document.getElementById(tr_luas).style.display="none";
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
	
	
	if(val == 9)
{

	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
	

}

else if(val == 7)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 4)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}

else if(val == 2)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 5)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 3)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 6)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 1)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 8)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}


else
{
    document.getElementById(tr_luas).style.display="none";
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

	
}



}






function ConvertLuasHartaPenarikan(index,jumlah_lot,kategory)
{
if(jumlah_lot>1)
{


  var val = document.${formName}.socJenisLuas[index].value;
  var unitluas = document.${formName}.unitluas[index].value;
  
  var l1 = index+"luas1";
  var l2 = index+"luas2";
  var l3 = index+"luas3";
  var tr_luas = index+"tr_luasharta";
  var tr_luas_b = index+"tr_luasharta_b";
	
    if (val=="2"){
	
	//alert(kategory);

        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
              
		if(a=="")
		{
		
		return
		} 
		else
		{
	    var num = parseFloat(a) * 10000; // hektar to meter persegi
		var num1 = parseFloat(a) * 1;   //hektar to hektar         	   
        if(kategory=="2") 
        {       
	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}
		
			 
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
       
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}
		
			    
        }		
		}
		     
		
	}
	
	  else if (val=="5"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;   			
		if(a=="")
		{
	
		return
		} 
		else
		{ 
		var num = parseFloat(a) * 0.09290304; // kaki persegi to meter persegi
		var num1 = parseFloat(a) * 0.000009290304; // kaki persegi to hektar             
        if(kategory=="2") 
        {       
	//	document.${formName}.lot_tarik[index].value=num1;	
		
		
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}
		
		   
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
      //  document.${formName}.lot_tarik[index].value=num;	  
	  
	  if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}
	  
	    
        } 		 
		}
	}
	
	
	 else if (val=="1"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
			
        if(a=="")
		{
		
		return
		} 
		else
		{  
		var num = parseFloat(a) * 1000000;  // kilo to meter      
        var num1 = parseFloat(a) * 100; // kilo meter to hektar        
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik[index].value=num1;	    	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}	
        }
        else if(kategory=="1" || kategory=="3" || kategory =="4" || kategory =="5" || kategory =="6")        
        {
       // document.${formName}.lot_tarik[index].value=num;	 
	   
	   if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}  
        } 
		
		}
		     
		
	}
	
	else if (val=="3"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
        
        
		if(a=="")
		{
		
		return
		} 
		else
		{
		var num = parseFloat(a) * 1; //meter persegi to meter persegi
		var num1 = parseFloat(a) * 0.0001;  //meter persegi to hektar         
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik[index].value=num1;	
		
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}
		    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
       // document.${formName}.lot_tarik[index].value=num;
	   if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}	    
        } 
		
		}
		     
		
	}



// batu nautika, xjumpa lagi
else if (val=="9"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;      
        
		if(a=="")
		{
		
		return
		} 
		else
		{   
		var num = parseFloat(a) * 1; 
		var num1 = parseFloat(a) * 1;       
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik[index].value=num1;	    
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik[index].value=num;	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}    
        }		
		}
	}
	
	// ekar perpuluhan xjumpa lagi
		else if (val=="6"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
       
        
		if(a=="")
		{
		
		return
		} 
		else
		{
		var num = parseFloat(a) * 1; 
		var num1 = parseFloat(a) * 1;          
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik[index].value=num1;	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}    		
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")               
        {
       // document.${formName}.lot_tarik[index].value=num;	
	   if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}    
        } 		
		}		
	}

	
	
		else if (val=="7"){      
		var a = document.${formName}.txtLuasAsalHtaam1[index].value;
		var b = document.${formName}.txtLuasAsalHtaam2[index].value;
          
		if(a=="")
		{
			
		return
		}  
		else if(b=="")
		{
				
		return
		}         
		else
		{ 
		
		var num = (parseFloat(a) + (parseFloat(b)/1000))*4046.86; 
		var num1 =  (parseFloat(a) + (parseFloat(b)/1000))*0.404686;            
        if(kategory=="2") 
        {  
		     
		//document.${formName}.lot_tarik[index].value=num1;
		
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}
		
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
       // document.${formName}.lot_tarik[index].value=num;
	   if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}
        } 		
		}
	}
	
	
else if (val=="8"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
		var b = document.${formName}.txtLuasAsalHtaam2[index].value;
        var c = document.${formName}.txtLuasAsalHtaam3[index].value;
              
		if(a=="")
		{
			
		return
		} 
		else if(b=="")
		{
			
		return
		} 
		else if(c=="")
		{
		
		return
		}        
		else
		{  
	    var num = (parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976))*0.711111*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976)))*0.711111*0.404686;        
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik[index].value=num1;	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik[index].value=num;	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}    
        } 		
		}
		     
		
	}
	 //formula bawah dah dibetolkan
else if (val=="4"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
		var b = document.${formName}.txtLuasAsalHtaam2[index].value;
        var c = document.${formName}.txtLuasAsalHtaam3[index].value;
       
		
        
		if(a=="")
		{
		
		return
		} 
		else if(b=="")
		{
		
		return
		} 
		else if(c=="")
		{
		
		return
		}        
		else
		{  
		var num = (parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160))*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160)))*0.404686;        
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik[index].value=num1;	  
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}  	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik[index].value=num;
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik[index].value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik[index].value=num.toFixed(4);
		}	    
        } 		
		}	     
		
	}
    

}


if(jumlah_lot==1)
{


  var val = document.${formName}.socJenisLuas.value;
  var l1 = index+"luas1";
  var l2 = index+"luas2";
  var l3 = index+"luas3";
  var tr_luas = index+"tr_luasharta";
  var tr_luas_b = index+"tr_luasharta_b";
	
    if (val=="2"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
                  
		if(a=="")
		{
		
		return
		} 
		else
		{  
		var num = parseFloat(a) * 10000; // hektar to meter persegi
		var num1 = parseFloat(a) * 1;   //hektar to hektar   	   
        if(kategory=="2") 
        {       
	//	document.getElementById(luasconvert).value=num1.toFixed(4);
	//    document.${formName}.lot_tarik.value=num1;
	
	    if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}	 
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik.value=num;	   
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		} 
        }		
		}
		     
		
	}
	
	  else if (val=="5"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;   
	   		
		if(a=="")
		{
		
		return
		} 
		else
		{  
		var num = parseFloat(a) * 0.09290304; // kaki persegi to meter persegi
		var num1 = parseFloat(a) * 0.000009290304; // kaki persegi to hektar            
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik.value=num1;
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}	   
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik.value=num;	  
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}  
        } 		 
		}
	}
	
	
	 else if (val=="1"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
		
		 
		
		
        if(a=="")
		{
		return
		} 
		else
		{  
		var num = parseFloat(a) * 1000000;  // kilo to meter      
        var num1 = parseFloat(a) * 100; // kilo meter to hektar        
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik.value=num1;	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}    		
        }
        else if(kategory=="1" || kategory=="3" || kategory =="4" || kategory =="5" || kategory =="6")        
        {
        //document.${formName}.lot_tarik.value=num;	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}   
        } 
		
		}
		     
		
	}
	
	else if (val=="3"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
       
        
		if(a=="")
		{
		
		return
		} 
		else
		{ 
		var num = parseFloat(a) * 1; //meter persegi to meter persegi
		var num1 = parseFloat(a) * 0.0001;  //meter persegi to hektar        
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik.value=num1;
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik.value=num;	 
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}   
        } 
		
		}
		     
		
	}



// cari formula, bawah ni hentam dulu
	else if (val=="9"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
        
        
		if(a=="")
		{
		
		return
		} 
		else
		{         
		var num = parseFloat(a) * 1; //meter persegi to meter persegi
		var num1 = parseFloat(a) * 1;  //meter persegi to hektar
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik.value=num1;
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik.value=num;	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}    
        } 
		
		}
		     
		
	}
	
	
		else if (val=="6"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
        
        
		if(a=="")
		{
		
		return
		} 
		else
		{ 
		var num = parseFloat(a) * 1; 
		var num1 = parseFloat(a) * 1;         
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik.value=num1;
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}	    		
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")               
        {
        //document.${formName}.lot_tarik.value=num;	 
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}   
        } 		
		}		
	}

	
		else if (val=="7"){      
		var a = document.${formName}.txtLuasAsalHtaam1.value;
		var b = document.${formName}.txtLuasAsalHtaam2.value;
              
		if(a=="")
		{
			
		return
		}  
		else if(b=="")
		{
				
		return
		}         
		else
		{   
		var num = (parseFloat(a) + (parseFloat(b)/1000))*4046.86; 
		var num1 =  (parseFloat(a) + (parseFloat(b)/1000))*0.404686;       
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik.value=num1;
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik.value=num;
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}
        } 		
		}
	}
	
else if (val=="8"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
		var b = document.${formName}.txtLuasAsalHtaam2.value;
        var c = document.${formName}.txtLuasAsalHtaam3.value;
               
		if(a=="")
		{
		
		return
		} 
		else if(b=="")
		{
		
		return
		} 
		else if(c=="")
		{
		
		return
		}        
		else
		{ 
		var num = (parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976))*0.711111*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976)))*0.711111*0.404686;       
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik.value=num1;	  
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}  	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
       // document.${formName}.lot_tarik.value=num;	   
	   if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		} 
        } 		
		}
		     
		
	}
	 //formula bawah ni hentam dulu
else if (val=="4"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
		var b = document.${formName}.txtLuasAsalHtaam2.value;
        var c = document.${formName}.txtLuasAsalHtaam3.value;
        
		if(a=="")
		{
		
		return
		} 
		else if(b=="")
		{
			
		return
		} 
		else if(c=="")
		{
		
		return
		}        
		else
		{
		var num = (parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160))*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160)))*0.404686;           
        if(kategory=="2") 
        {       
		//document.${formName}.lot_tarik.value=num1;	  
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
		}  	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        //document.${formName}.lot_tarik.value=num;	
		if(unitluas=="2")
		{	
	    document.${formName}.lot_tarik.value=num1.toFixed(4);
		}
		else
		{
		document.${formName}.lot_tarik.value=num.toFixed(4);
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
	//   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	 $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
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


function pilih_lot_tarik(index)
{
if(document.${formName}.ids.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids.length; i++)
	  {	 
      if (document.${formName}.ids[index].checked == true)
	  {
	  document.${formName}.lot_tarik[index].focus();	
	  document.${formName}.lot_tarik[index].value = document.${formName}.lot_ambil[index].value;	  
      }
	  }  
}
else
{
	if (document.${formName}.ids.checked == true)
	  {
	  document.${formName}.lot_tarik.focus();	
	  document.${formName}.lot_tarik.value = document.${formName}.lot_ambil.value;	  
      }
}


}



function pilih_lot_tarik_all()
{

if(document.${formName}.all.checked == true)
{

if(document.${formName}.ids.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids.length; i++)
	  {	 
 
	  document.${formName}.lot_tarik[i].value = document.${formName}.lot_ambil[i].value;	  
      
	  }  
}
else
{
	
	
	document.${formName}.lot_tarik.value = document.${formName}.lot_ambil.value;	 
     	
}	 
}
else
{
if(document.${formName}.ids.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids.length; i++)
	  {	 
    
	  document.${formName}.lot_tarik[i].value = "";	  
      
	  }  
}
else
{
	
	
	document.${formName}.lot_tarik.value = "";	 
     	
}
	 
}

}


function surat_HQ_negeri(id_penarikan)
{
    var url = "../servlet/ekptg.report.ppt.SuratPenarikanBalik_keNegeri?id_penarikan="+id_penarikan;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}















function open_new_window(jenis_popup) 
{
var width  = 300;
 var height = 200;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
//new_window = open("","hoverwindow",params);
new_window = open("","title",params);

new_window.document.open();

new_window.document.write("<html><title>JavaScript New Window</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
if(jenis_popup == "1")
{
if('$jenis_permohonan' == "3" || '$jenis_permohonan' == "4" )
{
new_window.document.write("Sila semak untuk memilih penarikan balik bagi kesemua senarai lot.");
}
if('$jenis_permohonan' == "1" || '$jenis_permohonan' == "2" )
{
new_window.document.write("Sila semak untuk memilih pembatalan bagi kesemua senarai lot.");
}
}
if(jenis_popup == "2")
{
if('$jenis_permohonan' == "3" || '$jenis_permohonan' == "4" )
{
new_window.document.write("Sila semak untuk memilih penarikan balik bagi lot ini,");
}
if('$jenis_permohonan' == "1" || '$jenis_permohonan' == "2" )
{
new_window.document.write("Sila semak untuk memilih pembatalan bagi lot ini,");
}

new_window.document.write("dan sila masukkan jumlah luas lust lot yang hendak ");
new_window.document.write("ditarik balik.");

}

if(jenis_popup == "3")
{
new_window.document.write("Sila pilih jenis luas yang lain jika unit luas yang dikehendaki adalah tidak sama dengan unit luas yang ditetapkan.");
new_window.document.write("Seterusnya sila masukkan luas lot awal, pengiraan luas pertukaran akan berlaku secara automatik selepas kotak terakhir selesai diisi.");


}

new_window.document.write("<br>");
new_window.document.write("</body></html>");
new_window.document.close(); 







}


function close_window() 
{
new_window.close();
}

</script>



