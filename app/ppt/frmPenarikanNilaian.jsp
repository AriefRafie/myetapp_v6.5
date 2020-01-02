<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>


#set($txtHargaSeunitSO = "")
#set($txtUnitHargaSO = "")
#set($txtHargaPasaranSO = "")
#set($txtPenjejasanSO = "")
#set($txtPecahPisahSO = "")
#set($txtNaikNilaiSO = "")
#set($txtHargaSeunitJPPH = "")
#set($txtUnitHargaJPPH = "")
#set($txtHargaPasaranJPPH = "")
#set($txtPenjejasanJPPH = "")
#set($txtPecahPisahJPPH = "")
#set($txtNaikNilaiJPPH = "")
#set($id_tanah = "")

#set($id_award = "") 
#set($txtFee = "") 
#set($txtBangunan = "") 
#set($txtTanah = "") 
#set($txtPenjejasan = "") 
#set($txtGantiRugi = "") 
#set($txtNaik = "") 
#set($txtLainPampasan = "") 
#set($txtJumlah = "") 
#set($txtPecahpisah = "") 


#foreach($list_siasatan in $maklumat_siasatan_pb)
#set($id_siasatan = $list_siasatan.ID_SIASATAN)
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
#set($id_hakmilik = $list_siasatan.ID_HAKMILIK) 
#set($id_tanah = $list_siasatan.ID_TANAH) 

#set($id_award = $list_siasatan.ID_AWARD) 
#set($txtFee = $list_siasatan.BAYAR_FEE) 
#set($txtBangunan = $list_siasatan.BAYAR_BANGUNAN) 
#set($txtTanah = $list_siasatan.BAYAR_TANAH) 
#set($txtPenjejasan = $list_siasatan.BAYAR_PENJEJASAN_P) 
#set($txtGantiRugi = $list_siasatan.NILAI_RUGI) 
#set($txtNaik = $list_siasatan.NAIK_NILAI) 
#set($txtJumlah = $list_siasatan.BAYAR_PAMPASAN) 
#set($txtPecahpisah = $list_siasatan.BAYAR_PECAHPISAH_P) 
#set($txtLainPampasan = $list_siasatan.BAYAR_LAIN2) 


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
    <td> #parse("app/ppt/header_pampasan_penarikanbalik.jsp")</td>
  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
     
       <li class="TabbedPanelsTab" tabindex="0" id="senarai" onClick="kembali_senarai('$id_permohonan',' $id_pembatalan')">Senarai PB</li>
      <li class="TabbedPanelsTab" tabindex="0" id="tuntutan" onClick="Tuntutan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0"  id="Nilaian"  onClick="Nilaian('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Nilaian / Maklumat Pampasan</li>  
        <li class="TabbedPanelsTab" tabindex="0" id="Penerimaan_Cek" onClick="Penerimaan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Penerimaan Cek</li>
        <li class="TabbedPanelsTab" tabindex="0"  id="Penyerahan_Cek" onClick="Penyerahan('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Penyerahan Cek</li>
        <li class="TabbedPanelsTab" tabindex="0"  id="Bayaran_Melalui_EFT" onClick="EFT('$id_permohonan','$id_pembatalan','$id_hakmilikpb')" >Bayaran Melalui EFT</li>
      </ul>
      
      <div class="TabbedPanelsContentGroup">
      <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent">
   <fieldset>
   <table width="100%">
  <tr>
    <td  valign="top" >
    
    <fieldset>
    <legend>Anggaran Nilaian Pegawai Tempatan</legend>
    <table width="100%">
     
      <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="27%">Harga Seunit </td>
        <td width="1%"></td>
        <td width="1%">:</td>
        <td width="70%"> 
        RM 
        
    #if($readmode == "view")
    #if($txtHargaSeunitSO != "")
    #set($txtHargaSeunitSO = $Util.formatDecimal($txtHargaSeunitSO))
    #else
    #set($txtHargaSeunitSO = "")
    #end
    #else
    #set($txtHargaSeunitSO = $txtHargaSeunitSO) 
    #end
        
         <input name="txtHargaSeunitSO" type="text" id="txtHargaSeunitSO" size="15"   value="$!txtHargaSeunitSO" onblur="validateTarikh(this,this.value);checking_validation(this,'txtHargaSeunitSO_check','yes','harga seunit','normal');validateModal(this,this.value,'$txtHargaSeunitSO');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtHargaSeunitSO_check','yes','harga seunit','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtHargaSeunitSO_check" class = "alert_msg" ></span>  </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td colspan="2">Unit Harga</td>
        <td>:</td>
        <td>
       
         #if($readmode == "view" )       
              #if($txtUnitHargaSO == "1")
              #set($UnitHargaSO = "METER PERSEGI")            
              #elseif($txtUnitHargaSO == "2")
              #set($UnitHargaSO = "KAKI PERSEGI")              
              #else
              #set($UnitHargaSO = "")
              #end
              $!UnitHargaSO
              <input name="UnitHargaSO" type="hidden" class = "disabled" id="UnitHargaSO" value="$UnitHargaSO" size="15"  readonly $check1>              
              #else
              <table width="100%">
              <tr>
              <td width="30%" >                    
              #if($txtUnitHargaSO == "1")
              #set($check1 = "checked")
              #set($check2 = "")             
              #elseif($txtUnitHargaSO == "2")
              #set($check2 = "checked")
              #set($check1 = "")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #end 
<input type="radio" name="txtUnitHargaSO" id="txtUnitHargaSO" value="1" $check1 onClick="checking_validation(this,'txtUnitHargaSO_check','yes','unit harga','radio');">
Meter Persegi</td>
              <td width="70%">
 <input type="radio" name="txtUnitHargaSO" id="txtUnitHargaSO" value="2" $check2 onClick="checking_validation(this,'txtUnitHargaSO_check','yes','unit harga','radio');">
 Kaki Persegi 
 
 &nbsp; &nbsp;     <span id="txtUnitHargaSO_check" class = "alert_msg"  ></span>   </td>                 
                </tr>
                </table>
          #end        </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Harga Pasaran </td>
        
        <td></td>
        <td>:</td>
        <td>RM
    #if($readmode == "view")
    #if($txtHargaPasaranSO != "")
    #set($txtHargaPasaranSO = $Util.formatDecimal($txtHargaPasaranSO))
    #else
    #set($txtHargaPasaranSO = "")
    #end
    #else
    #set($txtHargaPasaranSO = $txtHargaPasaranSO) 
    #end
        
        <input name="txtHargaPasaranSO" type="text" id="txtHargaPasaranSO" size="15"   value="$!txtHargaPasaranSO" onblur="validateTarikh(this,this.value);checking_validation(this,'txtHargaPasaranSO_check','yes','harga pasaran','normal');validateModal(this,this.value,'$txtHargaPasaranSO');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtHargaPasaranSO_check','yes','harga pasaran','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtHargaPasaranSO_check" class = "alert_msg"  ></span> </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Penjejasan Terbabit </td>
        <td></td>
        <td>:</td>
        <td>RM
    #if($readmode == "view")
    #if($txtPenjejasanSO != "")
    #set($txtPenjejasanSO = $Util.formatDecimal($txtPenjejasanSO))
    #else
    #set($txtPenjejasanSO = "")
    #end
    #else
    #set($txtPenjejasanSO = $txtPenjejasanSO) 
    #end
        
        <input name="txtPenjejasanSO" type="text" id="txtPenjejasanSO" size="15"   value="$!txtPenjejasanSO" onblur="validateTarikh(this,this.value);checking_validation(this,'txtPenjejasanSO_check','yes','penjejasan terbabit','normal');validateModal(this,this.value,'$txtPenjejasanSO');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtPenjejasanSO_check','yes','penjejasan terbabit','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtPenjejasanSO_check" class = "alert_msg"  ></span> </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Pecahpisah </td>
        <td></td>
        <td>:</td>
        <td>RM
    #if($readmode == "view")
    #if($txtPecahPisahSO != "")
    #set($txtPecahPisahSO = $Util.formatDecimal($txtPecahPisahSO))
    #else
    #set($txtPecahPisahSO = "")
    #end
    #else
    #set($txtPecahPisahSO = $txtPecahPisahSO) 
    #end
        
        <input name="txtPecahPisahSO" type="text" id="txtPecahPisahSO" size="15"   value="$!txtPecahPisahSO" onblur="validateTarikh(this,this.value);checking_validation(this,'txtPecahPisahSO_check','yes','pecahpisah','normal');validateModal(this,this.value,'$txtPecahPisahSO');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtPecahPisahSO_check','yes','pecahpisah','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtPecahPisahSO_check" class = "alert_msg" ></span> </td>
      </tr>
       <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="27%">Kenaikan Nilai </td>
        <td width="1%"></td>
        <td width="1%">:</td>
        <td width="70%">
        RM
         #if($readmode == "view")
    #if($txtNaikNilaiSO != "")
    #set($txtNaikNilaiSO = $Util.formatDecimal($txtNaikNilaiSO))
    #else
    #set($txtNaikNilaiSO = "")
    #end
    #else
    #set($txtNaikNilaiSO = $txtNaikNilaiSO) 
    #end
        
        
        <input name="txtNaikNilaiSO" type="text" id="txtNaikNilaiSO" size="15"   value="$txtNaikNilaiSO" onblur="validateTarikh(this,this.value);checking_validation(this,'txtNaikNilaiSO_check','yes','kenaikan nilai','normal');validateModal(this,this.value,'$txtNaikNilaiSO');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNaikNilaiSO_check','yes','kenaikan nilai','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtNaikNilaiSO_check" class = "alert_msg"  ></span> </td>
      </tr>
    </table>
    </fieldset>
    
   
    </td>
  </tr>
  
  <tr>
  <td>
   <fieldset>
    <legend>Nilaian JPPH</legend>
    <table width="100%">
     
      <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="27%">Harga Seunit </td>
        <td width="1%"></td>
        <td width="1%">:</td>
        <td width="70%">
       RM
     #if($readmode == "view")
    #if($txtHargaSeunitJPPH != "")
    #set($txtHargaSeunitJPPH = $Util.formatDecimal($txtHargaSeunitJPPH))
    #else
    #set($txtHargaSeunitJPPH = "")
    #end
    #else
    #set($txtHargaSeunitJPPH = $txtHargaSeunitJPPH) 
    #end
        
         <input name="txtHargaSeunitJPPH" type="text" id="txtHargaSeunitJPPH" size="15"   value="$!txtHargaSeunitJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtHargaSeunitJPPH_check','yes','harga seunit','normal');validateModal(this,this.value,'$txtHargaSeunitJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtHargaSeunitJPPH_check','yes','harga seunit','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtHargaSeunitJPPH_check" class = "alert_msg"  ></span>  </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td colspan="2">Unit Harga</td>
        <td>:</td>
        <td>
        
         #if($readmode == "view" )       
              #if($txtUnitHargaJPPH == "1")
              #set($UnitHargaJPPH = "METER PERSEGI")            
              #elseif($txtUnitHargaJPPH == "2")
              #set($UnitHargaJPPH = "KAKI PERSEGI")              
              #else
              #set($UnitHargaJPPH = "")
              #end
              $!UnitHargaJPPH
              <input name="UnitHargaJPPH" type="hidden" class = "disabled" id="UnitHargaJPPH" value="$UnitHargaJPPH" size="15"  readonly $check1>              
              #else
              <table width="100%">
              <tr>
              <td width="30%" >                    
              #if($txtUnitHargaJPPH == "1")
              #set($check1 = "checked")
              #set($check2 = "")             
              #elseif($txtUnitHargaJPPH == "2")
              #set($check2 = "checked")
              #set($check1 = "")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #end 
<input type="radio" name="txtUnitHargaJPPH" id="txtUnitHargaJPPH" value="1" $check1 onClick="checking_validation(this,'txtUnitHargaJPPH_check','yes','unit harga','radio');">
Meter Persegi</td>
              <td width="70%">
 <input type="radio" name="txtUnitHargaJPPH" id="txtUnitHargaJPPH" value="2" $check2 onClick="checking_validation(this,'txtUnitHargaJPPH_check','yes','unit harga','radio');">
 Kaki Persegi  
 
  &nbsp; &nbsp;      <span id="txtUnitHargaJPPH_check" class = "alert_msg"  ></span> </td>                 
                </tr>
                </table>
          #end        </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Harga Pasaran </td>
        <td></td>
        <td>:</td>
        <td>RM
        
             #if($readmode == "view")
    #if($txtHargaPasaranJPPH != "")
    #set($txtHargaPasaranJPPH = $Util.formatDecimal($txtHargaPasaranJPPH))
    #else
    #set($txtHargaPasaranJPPH = "")
    #end
    #else
    #set($txtHargaPasaranJPPH = $txtHargaPasaranJPPH) 
    #end
        
        <input name="txtHargaPasaranJPPH" type="text" id="txtHargaPasaranJPPH" size="15" maxlength="150"   value="$!txtHargaPasaranJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtHargaPasaranJPPH_check','yes','harga pasaran','normal');validateModal(this,this.value,'$txtHargaPasaranJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtHargaPasaranJPPH_check','yes','harga pasaran','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtHargaPasaranJPPH_check" class = "alert_msg"  ></span> </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Penjejasan Terbabit </td>
        <td></td>
        <td>:</td>
        <td>RM
                #if($readmode == "view")
    #if($txtPenjejasanJPPH != "")
    #set($txtPenjejasanJPPH = $Util.formatDecimal($txtPenjejasanJPPH))
    #else
    #set($txtPenjejasanJPPH = "")
    #end
    #else
    #set($txtPenjejasanJPPH = $txtPenjejasanJPPH) 
    #end
        
        <input name="txtPenjejasanJPPH" type="text" id="txtPenjejasanJPPH" size="15" maxlength="150"   value="$!txtPenjejasanJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtPenjejasanJPPH_check','yes','penjejasan terbabit','normal');validateModal(this,this.value,'$txtPenjejasanJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtPenjejasanJPPH_check','yes','penjejasan terbabit','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtPenjejasanJPPH_check" class = "alert_msg"  ></span> </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Pecahpisah </td>
        <td></td>
        <td>:</td>
        <td>RM
      
                      #if($readmode == "view")
    #if($txtPecahPisahJPPH != "")
    #set($txtPecahPisahJPPH = $Util.formatDecimal($txtPecahPisahJPPH))
    #else
    #set($txtPecahPisahJPPH = "")
    #end
    #else
    #set($txtPecahPisahJPPH = $txtPecahPisahJPPH) 
    #end
        <input name="txtPecahPisahJPPH" type="text" id="txtPecahPisahJPPH" size="15" maxlength="150"   value="$!txtPecahPisahJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtPecahPisahJPPH_check','yes','pecahpisah','normal');validateModal(this,this.value,'$txtPecahPisahJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtPecahPisahJPPH_check','yes','pecahpisah','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtPecahPisahJPPH_check" class = "alert_msg" ></span> </td>
      </tr>
       <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="27%">Kenaikan Nilai (RM)</td>
        <td width="1%"></td>
        <td width="1%">:</td>
        <td width="70%">RM
    
        #if($readmode == "view")
    #if($txtNaikNilaiJPPH != "")
    #set($txtNaikNilaiJPPH = $Util.formatDecimal($txtNaikNilaiJPPH))
    #else
    #set($txtNaikNilaiJPPH = "")
    #end
    #else
    #set($txtNaikNilaiJPPH = $txtNaikNilaiJPPH) 
    #end
        
        <input name="txtNaikNilaiJPPH" type="text" id="txtNaikNilaiJPPH" size="15" maxlength="150"   value="$!txtNaikNilaiJPPH" onblur="validateTarikh(this,this.value);checking_validation(this,'txtNaikNilaiJPPH_check','yes','kenaikan nilai','normal');validateModal(this,this.value,'$txtNaikNilaiJPPH');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNaikNilaiJPPH_check','yes','kenaikan nilai','normal');" $readonlymode class = "$disabledmode" />
  <span id="txtNaikNilaiJPPH_check" class="alert_msg" ></span> </td>
      </tr>
    </table>
    </fieldset>
  </td>
  </tr>
 
 
 <tr>
  <td>
   <fieldset>
    <legend>Maklumat Pampasan</legend>
    <table width="100%">
     
      <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="27%">Fee</td>
        <td width="1%"></td>
        <td width="1%">:</td>
        <td width="70%">RM
       
     #if($readmode == "view")
    #if($txtFee != "")
    #set($txtFee = $Util.formatDecimal($txtFee))
    #else
    #set($txtFee = "")
    #end
    #else
    #set($txtFee = $txtFee) 
    #end
        
         <input name="txtFee" type="text" id="txtFee" size="15" maxlength="150"   value="$!txtFee" onblur="validateTarikh(this,this.value);checking_validation(this,'txtFee_check','yes','fee','normal');validateModal(this,this.value,'$txtFee');kira();" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtFee_check','yes','fee','normal');kira();" $readonlymode class = "$disabledmode" />
  <span id="txtFee_check" class="alert_msg" ></span>  </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Tanah</td>
        <td></td>
        <td>:</td>
        <td>RM
        
             #if($readmode == "view")
    #if($txtTanah != "")
    #set($txtTanah = $Util.formatDecimal($txtTanah))
    #else
    #set($txtTanah = "")
    #end
    #else
    #set($txtTanah = $txtTanah) 
    #end
        
        <input name="txtTanah" type="text" id="txtTanah" size="15" maxlength="150"   value="$!txtTanah" onblur="validateTarikh(this,this.value);checking_validation(this,'txtTanah_check','yes','harga tanah','normal');validateModal(this,this.value,'$txtTanah');kira();" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtTanah_check','yes','harga tanah','normal');kira();" $readonlymode class = "$disabledmode" />
  <span id="txtTanah_check" class="alert_msg"></span> </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Banggunan</td>
        <td></td>
        <td>:</td>
        <td>RM
                #if($readmode == "view")
    #if($txtBangunan != "")
    #set($txtBangunan = $Util.formatDecimal($txtBangunan))
    #else
    #set($txtBangunan = "")
    #end
    #else
    #set($txtBangunan = $txtBangunan) 
    #end
        
        <input name="txtBangunan" type="text" id="txtBangunan" size="15" maxlength="150"   value="$!txtBangunan" onblur="validateTarikh(this,this.value);checking_validation(this,'txtBangunan_check','yes','harga bangunan','normal');validateModal(this,this.value,'$txtBangunan');kira();" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtBangunan_check','yes','harga bangunan','normal');kira();" $readonlymode class = "$disabledmode" />
  <span id="txtBangunan_check" class="alert_msg"></span> </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Pecahpisah </td>
        <td></td>
        <td>:</td>
        <td>RM
        
                      #if($readmode == "view")
    #if($txtPecahpisah != "")
    #set($txtPecahpisah = $Util.formatDecimal($txtPecahpisah))
    #else
    #set($txtPecahpisah = "")
    #end
    #else
    #set($txtPecahpisah = $txtPecahpisah) 
    #end
        <input name="txtPecahpisah" type="text" id="txtPecahpisah" size="15" maxlength="150"   value="$!txtPecahpisah" onblur="validateTarikh(this,this.value);checking_validation(this,'txtPecahpisah_check','yes','harga pecahpisah','normal');validateModal(this,this.value,'$txtPecahpisah');kira();" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtPecahpisah_check','yes','harga pecahpisah','normal');kira();" $readonlymode class = "$disabledmode" />
  <span id="txtPecahpisah_check" class="alert_msg" ></span> </td>
      </tr>
       <tr>
        <td >#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td >Penjejasan Terbabit</td>
        <td ></td>
        <td >:</td>
        <td >RM
      
        #if($readmode == "view")
    #if($txtPenjejasan != "")
    #set($txtPenjejasan = $Util.formatDecimal($txtPenjejasan))
    #else
    #set($txtPenjejasan = "")
    #end
    #else
    #set($txtPenjejasan = $txtPenjejasan) 
    #end
        
        <input name="txtPenjejasan" type="text" id="txtPenjejasan" size="15" maxlength="150"   value="$!txtPenjejasan" onblur="validateTarikh(this,this.value);checking_validation(this,'txtPenjejasan_check','yes','harga penjejasan','normal');validateModal(this,this.value,'$txtPenjejasan');kira();" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtPenjejasan_check','yes','harga penjejasan','normal');kira();" $readonlymode class = "$disabledmode" />
  <span id="txtPenjejasan_check" class="alert_msg" ></span> </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Kenaikan Nilai</td>
        <td></td>
        <td>:</td>
        <td>RM
                #if($readmode == "view")
    #if($txtNaik != "")
    #set($txtNaik = $Util.formatDecimal($txtNaik))
    #else
    #set($txtNaik = "")
    #end
    #else
    #set($txtNaik = $txtNaik) 
    #end
        
        <input name="txtNaik" type="text" id="txtNaik" size="15" maxlength="150"   value="$!txtNaik" onblur="validateTarikh(this,this.value);checking_validation(this,'txtNaik_check','yes','harga naik nilai','normal');validateModal(this,this.value,'$txtNaik');kira();" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNaik_check','yes','harga naik nilai','normal');kira();" $readonlymode class = "$disabledmode" />
  <span id="txtNaik_check" class="alert_msg"></span> </td>
      </tr>
      
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Nilai Ganti Rugi</td>
        <td></td>
        <td>:</td>
        <td>RM
        
                      #if($readmode == "view")
    #if($txtGantiRugi != "")
    #set($txtGantiRugi = $Util.formatDecimal($txtGantiRugi))
    #else
    #set($txtGantiRugi = "")
    #end
    #else
    #set($txtGantiRugi = $txtGantiRugi) 
    #end
        <input name="txtGantiRugi" type="text" id="txtGantiRugi" size="15" maxlength="150"   value="$!txtGantiRugi" onblur="validateTarikh(this,this.value);checking_validation(this,'txtGantiRugi_check','yes','harga ganti rugi','normal');validateModal(this,this.value,'$txtGantiRugi');kira();" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtGantiRugi_check','yes','harga ganti rugi','normal');kira();" $readonlymode class = "$disabledmode" />
  <span id="txtGantiRugi_check" class="alert_msg" ></span> </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Lain - lain Pampasan</td>
        <td></td>
        <td>:</td>
        <td>RM
        
                      #if($readmode == "view")
    #if($txtLainPampasan != "")
    #set($txtLainPampasan = $Util.formatDecimal($txtLainPampasan))
    #else
    #set($txtLainPampasan = "")
    #end
    #else
    #set($txtLainPampasan = $txtLainPampasan) 
    #end
        <input name="txtLainPampasan" type="text" id="txtLainPampasan" size="15" maxlength="150"   value="$!txtLainPampasan" onblur="validateTarikh(this,this.value);checking_validation(this,'txtLainPampasan_check','yes','lain-lain pampasan','normal');validateModal(this,this.value,'$txtLainPampasan');kira();" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtLainPampasan_check','yes','lain-lain pampasan','normal');kira();" $readonlymode class = "$disabledmode" />
  <span id="txtLainPampasan_check" class="alert_msg" ></span> </td>
      </tr>
       <tr>
        <td >#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td >Jumlah Keseluruhan</td>
        <td ></td>
        <td >:</td>
        <td >RM
      
        #if($readmode == "view")
    #if($txtJumlah != "")
    #set($txtJumlah = $Util.formatDecimal($txtJumlah))
    #else
    #set($txtJumlah = "")
    #end
    #else
    #set($txtJumlah = $txtJumlah) 
    #end
        
        <input name="txtJumlah" type="text" id="txtJumlah" size="15" maxlength="150"   value="$!txtJumlah" onblur="validateTarikh(this,this.value);checking_validation(this,'txtJumlah_check','yes','jumlah keseluruhan','normal');validateModal(this,this.value,'$txtJumlah');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtJumlah_check','yes','jumlah keseluruhan','normal');" readonly class = "disabled" />
  <span id="txtJumlah_check" class="alert_msg" ></span> </td>
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
        <label></label>
       <!--
        <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
      </label>  
      -->  
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
      </div>
    </div></td>
</tr>
</table>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue">Surat Kepada Agensi Pemohon - Tuntutan Penarikan Balik</font></a></td>
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
  <input type="hidden" name="id_tanah" id="id_tanah" value="$!id_tanah" />
  <input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb" />
  <input type="hidden" name="id_award" id="id_award" value="$!id_award" />
  
  
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});var readmode = '$readmode';
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

checking_validation(document.${formName}.txtFee,'txtFee_check','yes','fee','normal');
checking_validation(document.${formName}.txtBangunan,'txtBangunan_check','yes','harga bangunan','normal');
checking_validation(document.${formName}.txtTanah,'txtTanah_check','yes','harga tanah','normal');
checking_validation(document.${formName}.txtPenjejasan,'txtPenjejasan_check','yes','harga penjejasan','normal');
checking_validation(document.${formName}.txtGantiRugi,'txtGantiRugi_check','yes','harga ganti rugi','normal');
checking_validation(document.${formName}.txtNaik,'txtNaik_check','yes','harga naik nilai','normal');
checking_validation(document.${formName}.txtLainPampasan,'txtLainPampasan_check','yes','lain-lain pampasan','normal');
checking_validation(document.${formName}.txtJumlah,'txtJumlah_check','yes','jumlah keseluruhan','normal');
checking_validation(document.${formName}.txtPecahpisah,'txtPecahpisah_check','yes','harga pecahpisah','normal');
checking_validation(document.${formName}.txtLainPampasan,'txtLainPampasan_check','yes','lain-lain pampasan','normal');
kira();


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
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "Nilaian";
	document.${formName}.point.value = "Nilaian";	
	document.${formName}.submit();
	}
}
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "Nilaian";
	document.${formName}.point.value = "txtHargaSeunitSO";
	document.${formName}.submit();
	
}


function batal(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Nilaian";
	document.${formName}.point.value = "txtHargaSeunitSO";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}


function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "Nilaian";
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

function kembali_senarai(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
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



function kira()
{
var total = 0.00;


if(document.${formName}.txtFee.value != "")
{
total = total + parseFloat(document.${formName}.txtFee.value);
}
if(document.${formName}.txtBangunan.value != "")
{
total = total + parseFloat(document.${formName}.txtBangunan.value);
}
if(document.${formName}.txtTanah.value != "")
{
total = total + parseFloat(document.${formName}.txtTanah.value);
}
if(document.${formName}.txtPenjejasan.value != "")
{
total = total + parseFloat(document.${formName}.txtPenjejasan.value);
}
if(document.${formName}.txtGantiRugi.value != "")
{
total = total + parseFloat(document.${formName}.txtGantiRugi.value);
}
if(document.${formName}.txtNaik.value != "")
{
total = total + parseFloat(document.${formName}.txtNaik.value);
}
if(document.${formName}.txtLainPampasan.value != "")
{
total = total + parseFloat(document.${formName}.txtLainPampasan.value);
}
if(document.${formName}.txtPecahpisah.value != "")
{
total = total + parseFloat(document.${formName}.txtPecahpisah.value);
}

if(total>0)
{
validateModal(document.${formName}.txtJumlah,total,'$txtJumlah');
}
else
{
document.${formName}.txtJumlah.value = 0.00;
}
checking_validation(document.${formName}.txtJumlah,'txtJumlah_check','yes','jumlah keseluruhan','normal');


}

</script>
  
