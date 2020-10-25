<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>

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

 #foreach($tt in $tarikh_akhir_tampal)
 #set($txtTkhAkhirNotis = $tt.TARIKH_TAMPAL)
 #end

#if($nk_add == "yes")
#set($txtNoKes = "")
#set($txtNoSiasatan = "")
#set($txtTempatSiasatan = "")
#set($txtPoskod = "")
#set($txtAlamat1 = "")
#set($txtAlamat2 = "")
#set($txtAlamat3 = "")
#set($socNegeriSiasatan = "")
#set($socBandarSiasatan = "")
#set($socStatusSiasatan = "")
#set($txdTarikhSiasatan = "")
#set($txtMasaSiasatan = "")
#set($txtAlasan = "")
#set($jeniswaktu = "")
#set($id_siasatan = "")
#set($bil_siasatan = $list_siasatan.size()+1)
#end

 


#foreach($list_siasatan in $maklumat_siasatan)
#set($txtNoKes = $list_siasatan.NO_KES)
#set($txtNoSiasatan = $list_siasatan.NO_SIASATAN)
#set($txtTempatSiasatan = $list_siasatan.TEMPAT)
#set($txtPoskod = $list_siasatan.POSKOD)
#set($txtAlamat1 = $list_siasatan.ALAMAT1)
#set($txtAlamat2 = $list_siasatan.ALAMAT2)
#set($txtAlamat3 = $list_siasatan.ALAMAT3)
#set($socNegeriSiasatan = $list_siasatan.ID_NEGERI)
#set($socBandarSiasatan = $list_siasatan.ID_BANDAR)
#set($socStatusSiasatan = $list_siasatan.STATUS_SIASATAN)
#set($txdTarikhSiasatan = $list_siasatan.TARIKH_SIASATAN)
#set($txtMasaSiasatan = $list_siasatan.MASA_SIASATAN)
#set($txtTkhAkhirNotis = $list_siasatan.TARIKH_AKHIR_TAMPAL)
#set($txtAlasan = $list_siasatan.ALASAN_TANGGUH)
#set($jeniswaktu = $list_siasatan.JENIS_WAKTU_SIASATAN)
#set($id_siasatan = $list_siasatan.ID_SIASATAN)
#set($bil_siasatan = $list_siasatan.BIL_SIASATAN)
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
#parse("app/ppt/paging_penarikanbalik.jsp")
<table width="100%">
  <tr>
    <td> #parse("app/ppt/header_ppt.jsp")</td>
  </tr>
  
  <tr>
  <td>
  <fieldset>
  <legend>MAKLUMAT HAKMILIK</legend>
   <table width="100%" id="$list.BIL"  > 
 
 #foreach($list in $maklumat_hakmilik)

  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No Lot/PT</td>
    <td width="1%">:</td>
    <td width="70%">$list.NO_PT</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Negeri</td>
    <td width="1%">:</td>
    <td width="70%">$list.NAMA_NEGERI</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah</td>
    <td>:</td>
    <td>$list.NAMA_DAERAH</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Mukim</td>
    <td>:</td>
    <td>$list.NAMA_MUKIM</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jenis Hakmilik</td>
    <td>:</td>
    <td>$list.JENIS_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Kod Hakmilik</td>
    <td>:</td>
    <td>$list.KOD_JENIS_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No Hakmilik</td>
    <td>:</td>
    <td>$list.NO_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas Lot</td>
    <td>:</td>
    <td>$list.LUAS_LOT</td>
  </tr>
  <tr  style="display:none">
    <td>&nbsp;</td>
    <td>Luas Diambil</td>
    <td>:</td>
    <td>$list.LUAS_AMBIL</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas Ditarik Balik</td>
    <td>:</td>
    <td>$list.LUAS_LOT_TARIK</td>
  </tr>
  <tr>
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
                      
                      
                      
                      <select name=""  class="autoselect" >
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
                       </td>
  </tr>
  
  #end
</table>          
  </fieldset>
  </td>
  </tr>
  
  #if($record_siasatan == "yes")
  <tr>
<td>

  <fieldset id="maklumat_siasatan">
  <legend>MAKLUMAT SIASATAN</legend>
  <table width="100%">
  <tr>
    <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td width="28%">No. Kes</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoKes" type="text" id="txtNoKes" value="$txtNoKes" size="25" maxlength="25"  onBlur="checking_validation(this,'txtNoKes_check','yes','no kes','normal')" onKeyUp="checking_validation(this,'txtNoKes_check','yes','no kes','normal')"  $readonlymode class = "$disabledmode">
     <span id="txtNoKes_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>No. Siasatan</td>
    <td>:</td>
    <td><input name="txtNoSiasatan" type="text" id="txtNoSiasatan" value="$txtNoSiasatan" size="25" maxlength="25"  onBlur="checking_validation(this,'txtNoSiasatan_check','yes','no siasatan','normal')" onKeyUp="checking_validation(this,'txtNoSiasatan_check','yes','no siasatan','normal')"  $readonlymode class = "$disabledmode">
     <span id="txtNoSiasatan_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Tempat Siasatan</td>
    <td>:</td>
    <td><input name="txtTempatSiasatan" type="text" id="txtTempatSiasatan" value="$txtTempatSiasatan" size="50" onBlur="checking_validation(this,'txtTempatSiasatan_check','yes','tempat siasatan','normal')" onKeyUp="checking_validation(this,'txtTempatSiasatan_check','yes','tempat siasatan','normal')"  $readonlymode class = "$disabledmode">
    <span id="txtTempatSiasatan_check" class="alert_msg"></span>    </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Alamat</td>
    <td>:</td>
    <td><input name="txtAlamat1" type="text" id="txtAlamat1" value="$txtAlamat1" size="50" onBlur="checking_validation(this,'txtAlamat1_check','yes','alamat','normal')" onKeyUp="checking_validation(this,'txtAlamat1_check','yes','alamat','normal')"  $readonlymode class = "$disabledmode">
    <span id="txtAlamat1_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td><input name="txtAlamat2" type="text" id="txtAlamat2" value="$txtAlamat2" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td><input name="txtAlamat3" type="text" id="txtAlamat3" value="$txtAlamat3" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode"></td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Poskod</td>
    <td>:</td>
    <td><input name="txtPoskod" type="text" id="txtPoskod" onBlur="checking_validation(this,'txtPoskod_check','yes','','poskod');validateTarikh(this,this.value)" onKeyUp="checking_validation(this,'txtPoskod_check','yes','','poskod');validateTarikh(this,this.value)" value="$txtPoskod" size="6" maxlength="5"  $readonlymode class = "$disabledmode">
     <span id="txtPoskod_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Negeri</td>
    <td>:</td>
    <td>
   
     #if($readmode == "view" ) 
     #if($socNegeriSiasatan=="")
     #set($NegeriSiasatan="")                             
     #else                             
     #foreach($ln in $list_negeri)      
     #if($socNegeriSiasatan==$ln.ID_NEGERI)                                      
     #set($NegeriSiasatan="$ln.KOD_NEGERI - $ln.NAMA_NEGERI")
     #end
     #end                            
     #end      
     <input name="NegeriSiasatan" type="text" id="NegeriSiasatan" value="$NegeriSiasatan" size="50" readonly class="disabled">
     <input name="socNegeriSiasatan"  id="socNegeriSiasatan"  type="hidden" value="$socNegeriSiasatan" >                
    #else    
    <select name="socNegeriSiasatan" class="autoselect" id="socNegeriSiasatan"  onchange="checking_validation(this,'socNegeriSiasatan_check','yes','negeri siasatan','normal');getBandarSiasatan('$id_hakmilik','$!id_pembatalan')" >  
    #if($socNegeriSiasatan != "")
    
    #foreach($ln in $list_negeri) 
    #if($socNegeriSiasatan==$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_negeri)
    #if($socNegeriSiasatan!=$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end      
    #end
    <option value="">SILA PILIH NEGERI</option> 
    
    #else
   
    <option value="">SILA PILIH NEGERI</option>        
    #foreach($ln in $list_negeri)   
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socNegeriSiasatan_check" class="alert_msg" ></span>          </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Bandar</td>
    <td>:</td>
    <td>
    
     #if($readmode == "view" )
    
     #if($socBandarSiasatan=="")
     #set($BandarSiasatan="")                             
     #else                             
     #foreach($lb in $list_bandar)      
     #if($socBandarSiasatan==$lb.ID_BANDAR)                                      
     #set($BandarSiasatan="$lb.KOD_BANDAR - $lb.NAMA_BANDAR")
     #end
     #end                            
     #end      
    <input name="BandarSiasatan" type="text" id="BandarSiasatan" value="$BandarSiasatan" size="50" readonly class="disabled">
    <input name="socBandarSiasatan"  id="socBandarSiasatan"  type="hidden" value="$socBandarSiasatan" >                     
    #else    
    <select name="socBandarSiasatan" class="autoselect" id="socBandarSiasatan"  onchange="checking_validation(this,'socBandarSiasatan_check','yes','bandar siasatan','normal');" >  
    #if($socBandarSiasatan != "")
    
    #foreach($lb in $list_bandar) 
    #if($socBandarSiasatan==$lb.ID_BANDAR)
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>                                     
    #end 
    #end 
   
    #foreach($lb in $list_bandar)
    #if($socBandarSiasatan!=$lb.ID_BANDAR)
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>                                     
    #end      
    #end
    <option value="">SILA PILIH BANDAR</option> 
    
    #else
   
    <option value="">SILA PILIH BANDAR</option>        
    #foreach($lb in $list_bandar)   
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socBandarSiasatan_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Status Siasatan</td>
    <td>:</td>
    <td>
    
    #if($readmode == "view" ) 
     #if($socStatusSiasatan=="")
     #set($StatusSiasatan="")                             
     #else
     #if($socStatusSiasatan=="1")
     #set($StatusSiasatan="SIASATAN PERMULAAN")    
     #elseif($socStatusSiasatan=="2") 
     #set($StatusSiasatan="DITUNDA SEBELUM SIASATAN")    
     #elseif($socStatusSiasatan=="3") 
     #set($StatusSiasatan="DIBATALKAN")    
     #elseif($socStatusSiasatan=="4") 
     #set($StatusSiasatan="SIASATAN ULANGAN")    
     #elseif($socStatusSiasatan=="5") 
     #set($StatusSiasatan="SIASATAN SAMBUNGAN") 
     #elseif($socStatusSiasatan=="7")                             
     #set($StatusSiasatan="TANGGUH SIASATAN")   
     #elseif($socStatusSiasatan=="6")                             
     #set($StatusSiasatan="SELESAI")                
     #end 
     #end
        <input name="StatusSiasatan" type="text" id="StatusSiasatan" value="$StatusSiasatan" size="50" readonly class="disabled" />   
                  
    #else    
    <select name="socStatusSiasatan" class="autoselect" id="socStatusSiasatan"  onchange="checking_validation(this,'socStatusSiasatan_check','yes','status siasatan','normal');" >  
    #if($socStatusSiasatan != "")
     
   #if($socStatusSiasatan == "1")
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatan == "2")
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatan == "3")
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatan == "4")
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option>     
    <option value="3">DIBATALKAN</option>
     <option value="">SILA PILIH STATUS SIASATAN</option>  
    #elseif($socStatusSiasatan == "5")
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option> 
    #elseif($socStatusSiasatan == "7")
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>   
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    
    #elseif($socStatusSiasatan == "6")
    <option value="6">SELESAI</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #end
    #else
   
    <option value="">SILA PILIH STATUS SIASATAN</option> 
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>       
   
    
    #end
    
    </select>  
             
    #end
    
    <span id="socStatusSiasatan_check" class="alert_msg" ></span>    </td>
  </tr>
   <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Bil. Siasatan</td>
    <td>:</td>
    <td>
    
     <input name="bil_siasatan" type="text" id="bil_siasatan" size="10" maxlength="10"   value="$!bil_siasatan" onblur="validateTarikh(this,this.value);checking_validation(this,'bil_siasatan_check','yes','bilangan siasatan','normal');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'bil_siasatan_check','yes','bilangan siasatan','normal');" $readonlymode class = "$disabledmode" />   
 
       <span id="bil_siasatan_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Tarikh Siasatan</td>
    <td>:</td>
    <td>
    
     <input name="txdTarikhSiasatan" type="text" id="txdTarikhSiasatan" size="10" maxlength="10"   value="$!txdTarikhSiasatan" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhSiasatan_check','yes','siasatan','tarikh2');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />   
    #if($readmode == "edit")    
      <a href="javascript:displayDatePicker('txdTarikhSiasatan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>        
      #end
       <span id="txdTarikhSiasatan_check" class="alert_msg" ></span>   </td>
  </tr>
  <tr>
    <td></td>
    <td>Masa Siasatan</td>
    <td>:</td>
    <td><input name="txtMasaSiasatan" type="text" id="txtMasaSiasatan" value="$!txtMasaSiasatan"  size="4" maxlength="4"   $readonlymode class = "$disabledmode"  onBlur="checking_validation(this,'txtMasaSiasatan_check','no','siasatan','waktu');jeniswaktu1(this,'jeniswaktu')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtMasaSiasatan_check','no','siasatan','waktu');" /> 
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
                                  <span id="txtMasaSiasatan_check" class="alert_msg" ></span>                                  </td>
  </tr>
  <tr style="display:none">
    <td valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Tarikh Akhir Tampal Notis Awam</td>
    <td valign="top">:</td>
    <td>    
     <input name="txtTkhAkhirNotis" type="text" id="txtTkhAkhirNotis" size="10" maxlength="10"   value="$!txtTkhAkhirNotis" onblur="validateTarikh(this,this.value);checking_validation(this,'txtTkhAkhirNotis_check','no','akhir tampal notis awam','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />   
    #if($readmode == "edit")    
      <a href="javascript:displayDatePicker('txtTkhAkhirNotis',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> 
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>       
      #end
       <span id="txtTkhAkhirNotis_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td valign="top"></td>
    <td valign="top">
    <!--
    Alasan Tangguh / Batal
    -->
    Catatan    </td>
    <td valign="top">:</td>
    <td>
    
    <!-- <textarea name="txtAlasan" id="txtAlasan" cols="50" rows="6"  onBlur="checking_validation(this,'txtAlasan_check','yes','alasan tangguh / batal','normal')"  onKeyUp="checking_validation(this,'txtAlasan_check','yes','lokasi tanah','normal')"  $readonlymode class = "$disabledmode">$txtAlasan</textarea>
      <div id="txtAlasan_check" class="alert_msg" ></div> 
      -->
      
       <textarea name="txtAlasan" id="txtAlasan" cols="60"   rows="6" style=""         
         onBlur="check_length(this,'4000','txtAlasan_check','txtAlasan_num','normal','no','catatan');"  
         onKeyup="check_length(this,'4000','txtAlasan_check','txtAlasan_num','normal','no','catatan');" 
         onKeydown="check_length(this,'4000','txtAlasan_check','txtAlasan_num','normal','no','catatan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtAlasan</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtAlasan_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtAlasan_num" id="txtAlasan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtAlasan_check" class="alert_msg" ></div>     
  
  
    </td>
  </tr>
   
  <tr>
    <td colspan="4">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini('$id_siasatan')">
      </label>
      <label>
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      </label>
      
      <!-- Tambah BUtang Cetak -->
      <label>
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')">
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan('$id_siasatan')">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal"  onClick="batal()">
      </label>
    #end  
     
       #if($!id_pembatalan != "")
     <!-- <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="" >  
      </label>   --> 
      #end     
      #if($socStatusSiasatan=="2" && $readmode == "view" && $list_siasatan.size() == $bil_siasatan) 
      <!-- <input name="" type="button" value="Ulang Siasatan" onClick="ulang('$id_hakmilik','$!id_pembatalan')"> -->
      #end
      #if($readmode == "view" && $list_siasatan.size() == $bil_siasatan)
     
     #end      </div>  
     <div class="TabbedPanelsContent">        </div>         </td>
  </tr>
  
    #if($readmode == "edit")
  <tr>
    <td colspan="4">#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td colspan="4"></td>
  </tr>  
  #end
</table>
  
  </fieldset>
  </td>
  </tr>
  #end
 
<tr>
<td>

<fieldset id="senarai_siasatan">
<legend>SENARAI SIASATAN</legend>
<input type="button" value="Kembali" onClick="screen5('$id_permohonan','$!id_pembatalan')">
#if($list_siasatan.size() == 0 && $record_siasatan != "yes")
  <input name="" type="button" value="Daftar Siasatan" onClick="tambah('$id_hakmilik','$!id_pembatalan')">
#end

#if($list_siasatan.size() > 0)
<input name="" type="button" value="Hapus" onClick="hapus_beramai()">
#end


<table width="100%" >
  <tr class="table_header">
  	<td width="4%">Bil</td>
    <td width="14%">No. Kes </td>
    <td width="14%">No. Siasatan</td>
    <td width="10%"><div align="center">Bil Siasatan</div></td>
    <td  width="20%"><div align="center">Tarikh/Masa Siasatan</div></td>
    <td  width="25%">Tempat Siasatan</td>
    <td  width="15%">Status</td>
  
      #if($list_siasatan.size() > 0)
    <td  width="5%"> 
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1();" title="Semak untuk pilih semua" />
      </div></td>
      #end  </tr>
  
   #if($list_siasatan.size() > 0)
  #foreach($list in $list_siasatan)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
 

 
 
 <tr >
 <td colspan="8">
 
<fieldset id="$list.BILTEMP" class="$row" style="visibility:collapse; display:none;">
<table width="100%" >
<tr>
<td width="1%"></td>
<td colspan="3">
 <div align="left"><a href="javascript:papar('$list.ID_SIASATAN','$list.ID_HAKMILIK')" title="Memaparkan secara lengkap maklumat set siasatan"><font color="blue">MAKLUMAT SIASATAN</font></a></div>
</td>
</tr>

<tr>
<td width="1%"></td>
<td colspan="3">
 <div align="left"><a href="javascript:kehadiran('$list.ID_SIASATAN')" title="Memaparkan secara lengkap maklumat kehadiran"><font color="blue">MAKLUMAT KEHADIRAN</font></a></div>
</td>
</tr>


<tr>
<td width="1%"></td>
<td colspan="3">
 <div align="left"><a href="javascript:maklumatsiasatan('$list.ID_SIASATAN')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">NOTA SIASATAN TARIK BALIK</font></a></div>
</td>
</tr>
<tr>
    <td width="1%"></td>
    <td width="28%">No. Kes</td>
    <td width="1%">:</td>
    <td width="70%">$!list.NO_KES</td>
  </tr>
  <tr>
    <td></td>
    <td>No. Siasatan</td>
    <td>:</td>
    <td>$!list.NO_SIASATAN</td>
  </tr>
   <tr>
    <td></td>
    <td>Tempat Siasatan</td>
    <td>:</td>
    <td>$!list.TEMPAT
    </td>
  </tr>
 
 <tr>
    <td></td>
    <td>Alamat</td>
    <td>:</td>
    <td>
    $!list.ALAMAT1
    </td>
  </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td>$!list.ALAMAT2</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td>$list.ALAMAT3</td>
  </tr>
  <tr>
    <td></td>
    <td>Poskod</td>
    <td>:</td>
    <td>$!list.POSKOD
    
    </td>
  </tr>
  <tr>
    <td></td>
    <td>Bandar</td>
    <td>:</td>
    <td>
    
     #if($list.ID_BANDAR=="")
     #set($BandarSiasatanB="")                             
     #else                             
     #foreach($lb in $list_bandar)      
     #if($list.ID_BANDAR==$lb.ID_BANDAR)                                      
     #set($BandarSiasatanB="$lb.KOD_BANDAR - $lb.NAMA_BANDAR")
     #end
     #end                            
     #end      
    $BandarSiasatanB
 
    
    </td>
  </tr>
  <tr>
    <td></td>
    <td>Negeri</td>
    <td>:</td>
    <td>
    
     
     #if($list.ID_NEGERI=="")
     #set($NegeriSiasatanN="")                             
     #else                             
     #foreach($ln in $list_negeri)      
     #if($list.ID_NEGERI==$ln.ID_NEGERI)                                      
     #set($NegeriSiasatanN="$ln.KOD_NEGERI - $ln.NAMA_NEGERI")
     #end
     #end                            
     #end      
    $NegeriSiasatanN      
  
          
          </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Status Siasatan</td>
    <td>:</td>
    <td>
    
  
     #if($list.STATUS_SIASATAN=="")
     #set($StatusSiasatanS="")                             
     #else
     #if($list.STATUS_SIASATAN=="1")
     #set($StatusSiasatanS="SIASATAN PERMULAAN")    
     #elseif($list.STATUS_SIASATAN=="2") 
     #set($StatusSiasatanS="DITUNDA SEBELUM SIASATAN")    
     #elseif($list.STATUS_SIASATAN=="3") 
     #set($StatusSiasatanS="DIBATALKAN")    
     #elseif($list.STATUS_SIASATAN=="4") 
     #set($StatusSiasatanS="SIASATAN ULANGAN") 
     #elseif($list.STATUS_SIASATAN=="7")                             
     #set($StatusSiasatanS="TANGGUH SIASATAN")   
     #elseif($list.STATUS_SIASATAN=="5") 
     #set($StatusSiasatanS="SIASATAN SAMBUNGAN")    
     #elseif($list.STATUS_SIASATAN=="6")                             
     #set($StatusSiasatanS="SELESAI")                
     #end 
     #end
          $!StatusSiasatanS

    
    
    </td>
  </tr>
  <tr>
    <td></td>
    <td>Bil. Siasatan</td>
    <td>:</td>
    <td>
    $!list.BIL_SIASATAN
    
        </td>
  </tr>
  <tr>
    <td></td>
    <td>Tarikh Siasatan</td>
    <td>:</td>
    <td>
    $!list.TARIKH_SIASATAN
    
        </td>
  </tr>
  <tr>
    <td></td>
    <td>Masa Siasatan</td>
    <td>:</td>
    <td>
                               
                                  #set($waktuW = "")
                                  #if( $list.JENIS_WAKTU_SIASATAN == 1)
                                  #set($waktuW = "PAGI")  
                                  #elseif($list.JENIS_WAKTU_SIASATAN == 2)
                                  #set($waktuW = "TENGAHARI")                                   
                                  #elseif($list.JENIS_WAKTU_SIASATAN == 3)
                                  #set($waktuW = "PETANG")      
                                  #elseif($list.JENIS_WAKTU_SIASATAN == 4)
                                  #set($waktuW = "MALAM")                                   
                                  #else
                                  #set($waktu = "")
                                  #end             
                                  
                               $list.MASA_SIASATAN  $waktuW                                  </td>
  </tr>
  <tr>
    <td valign="top"></td>
    <td>Tarikh Akhir Tampal Notis Awam</td>
    <td valign="top">:</td>
    <td>    
   
$list.TARIKH_AKHIR_TAMPAL
     </td>
  </tr>
  <tr>
    <td valign="top"></td>
    <td valign="top">Alasan Tangguh / Batal</td>
    <td valign="top">:</td>
    <td><div style="width:60%">$list.ALASAN_TANGGUH</div></td>
  </tr>

  
</table>
</fieldset>

 
 
 </td>
 </tr>

  <tr class="$row">
    <td>$list.BIL</td>
    <td >
     <div align="left"><a href="javascript:ShowPopupSiasatan('$list.BILTEMP');" title="Klik untuk maklumat lengkap"><font color="blue">$list.NO_KES</font></a></div>
   
     </td>
    <td>$list.NO_SIASATAN</td>
    <td><div align="center">$list.BIL_SIASATAN</div></td>
    <td>
        
      <div align="center">#if( $list.JENIS_WAKTU_SIASATAN == 1) 
        #set($waktuS = "PAGI") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 2) 
        #set($waktuS = "TENGAHARI") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 3) 
        #set($waktuS = "PETANG") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 4) 
        #set($waktuS = "MALAM") 
        #else #set($waktuS = "") 
        #end
        
    $list.TARIKH_SIASATAN    $list.MASA_SIASATAN  $waktuS </div></td>
    <td>$list.TEMPAT</td>
    <td>
    
    #if($list.STATUS_SIASATAN =="1") 
    #set($StatusSiasatanS="SIASATAN PERMULAAN") 
    #elseif($list.STATUS_SIASATAN =="2") 
    #set($StatusSiasatanS="DITUNDA SEBELUM SIASATAN") 
    #elseif($list.STATUS_SIASATAN =="3") 
    #set($StatusSiasatanS="DIBATALKAN") 
    #elseif($list.STATUS_SIASATAN =="4") 
    #set($StatusSiasatanS="SIASATAN ULANGAN") 
    #elseif($list.STATUS_SIASATAN =="5")     
    #set($StatusSiasatanS="SIASATAN SAMBUNGAN") 
    #elseif($list.STATUS_SIASATAN=="7")                             
    #set($StatusSiasatanS="TANGGUH SIASATAN")
    #elseif($list.STATUS_SIASATAN =="6") 
    #set($StatusSiasatanS="SELESAI") 
    #else
    #set($StatusSiasatanS="") 
    #end 
    
    
    <!--
     #if($socStatusSiasatan=="1")
     #set($StatusSiasatan="SIASATAN PERMULAAN")    
     #elseif($socStatusSiasatan=="2") 
     #set($StatusSiasatan="DITUNDA SEBELUM SIASATAN")    
     #elseif($socStatusSiasatan=="3") 
     #set($StatusSiasatan="DIBATALKAN")    
     #elseif($socStatusSiasatan=="4") 
     #set($StatusSiasatan="SIASATAN ULANGAN")    
     #elseif($socStatusSiasatan=="5") 
     #set($StatusSiasatan="SIASATAN SAMBUNGAN")
     #elseif($socStatusSiasatan=="7")                             
     #set($StatusSiasatan="TANGGUH SIASATAN")    
     #elseif($socStatusSiasatan=="6")                             
     #set($StatusSiasatan="SELESAI")                
     #end 
    
    -->
    
    
    
    
    
    $!StatusSiasatanS    </td>
    
  
      #if($list_siasatan.size() > 0)
    <td><div align="center">
       <input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list.ID_SIASATAN" >
     </div></td>
     #end  </tr>
   #end
  #else
  <tr>  
    <td colspan="8">Tiada Rekod</td>    
  </tr>
  #end
</table>

</fieldset>
</td>
</tr>
</table>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    
      <tr>
        <td><a href="#" class="style2" onClick="BorangLB('$id_pembatalan')"><font color="blue">Borang LB</font></a></td>
      </tr>
       
    </table>
</fieldset>
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
window.location.hash='paging';
goTo('paging');
}


jeniswaktu2('txtMasaSiasatan');
checking_validation(document.${formName}.txtNoKes,'txtNoKes_check','yes','no kes','normal');
checking_validation(document.${formName}.txtNoSiasatan,'txtNoSiasatan_check','yes','no siasatan','normal');
checking_validation(document.${formName}.txtTempatSiasatan,'txtTempatSiasatan_check','yes','tempat siasatan','normal');
checking_validation(document.${formName}.txtPoskod,'txtPoskod_check','yes','','poskod');
checking_validation(document.${formName}.txtAlamat1,'txtAlamat1_check','yes','alamat','normal');
checking_validation(document.${formName}.socNegeriSiasatan,'socNegeriSiasatan_check','yes','negeri siasatan','normal');
//getBandarSiasatan('$id_hakmilik','$!id_pembatalan');
checking_validation(document.${formName}.socBandarSiasatan,'socBandarSiasatan_check','yes','bandar siasatan','normal');
checking_validation(document.${formName}.socStatusSiasatan,'socStatusSiasatan_check','yes','status siasatan','normal');
checking_validation(document.${formName}.txdTarikhSiasatan,'txdTarikhSiasatan_check','yes','siasatan','tarikh2');
//checking_validation(document.${formName}.txtAlasan,'txtAlasan_check','yes','alasan tangguh / batal','normal');
//checking_validation(document.${formName}.txtTkhAkhirNotis,'txtTkhAkhirNotis_check','no','akhir tampal notis awam','tarikh');
checking_validation(document.${formName}.txtMasaSiasatan,'txtMasaSiasatan_check','no','siasatan','waktu');
//jeniswaktu1(document.${formName}.txtMasaSiasatan,'jeniswaktu');
checking_validation(document.${formName}.bil_siasatan,'bil_siasatan_check','yes','bilangan siasatan','normal');
check_length(document.${formName}.txtAlasan,'4000','txtAlasan_check','txtAlasan_num','normal','no','catatan');   

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
	   {	/*   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
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
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
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
	//   DateField.focus();
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
		
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
		
	   }
	   else
	   {
	   /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
		
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
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
		
	   }
	   else if(lepas_or_xlepas == "3")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan waktu "+value_field+" dengan format yang betul");
		
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
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
	   
	   }
	   else if(lepas_or_xlepas == "3")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


function ulang(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "ulang";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


function simpan(id_siasatan)
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
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	
	if(id_siasatan == "")
	{
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	}
	else
	{
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	}
	
	document.${formName}.submit();
	}
	}
}


function papar(id_siasatan,id_hakmilik)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();
	
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	
}



function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	}
}

function hapus_beramai()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Hapus_beramai";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	}
}


function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtNoKes";
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


function kehadiran(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	document.${formName}.submit();

}


function maklumatsiasatan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Agensi";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

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

function BorangLB(id_pembatalan)
{
    /*var url = "../servlet/ekptg.report.ppt.BorangLB?id_penarikan="+id_pembatalan;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/
    
    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_penarikan="+id_pembatalan+"&report=BorangLB&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

</script>
  
