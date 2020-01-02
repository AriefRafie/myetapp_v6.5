

#foreach ( $senarai in $maklumat_pampasan_pb )
	#set ($NAMA_PB=$senarai.NAMA_PB)
    #set ($NO_PB=$senarai.NO_PB)
    #set ($NO_LOT=$senarai.NO_LOT)
    #set ($NAMA_MUKIM=$senarai.NAMA_MUKIM)
    #set ($SYER_ATAS=$senarai.SYER_ATAS)
    #set ($SYER_BAWAH=$senarai.SYER_BAWAH)
    #set ($NO_PT=$senarai.NO_PT)
    #set ($LUAS_AMBIL=$senarai.LUAS_AMBIL)
    #set ($BAYAR_PAMPASAN=$senarai.BAYAR_PAMPASAN)   
    #set ($NAMA_KEMENTERIAN = $senarai.NAMA_KEMENTERIAN)
    #set ($NO_AKAUN = $senarai.NO_AKAUN)
    #set ($NAMA_BANK = $senarai.NAMA_BANK) 
    #set ($id_hakmilik = $senarai.ID_HAKMILIK) 
#end 

#set ($JUMLAH_SUBAWARD=0)   

#foreach ( $senarai in $maklumat_subpampasan_pb )
    #set ($JUMLAH_SUBAWARD=$JUMLAH_SUBAWARD+$senarai.JUMLAH_SUBAWARD)   
#end 

#foreach ( $senarai in $header )	
    #set ($id_permohonan=$senarai.id_permohonan) 
#end 

<fieldset id="header">
	<legend>$!tajuk_header</legend>

<!------------------------------------------- HEADER ---------------------------------------------->
#if($maklumat_pampasan_pb.size() > 0)
     <table width="100%" border="0">
           <tr>	
             <td width="1%"></td>
             <td width="28%" style="text-transform:uppercase">NO. PB</td>
             <td width="1%">:</td>
             <td width="70%">$!NO_PB</td>
           </tr>
           <tr>
             <td ></td>
             <td style="text-transform:uppercase">NAMA PB</td>
             <td>:</td>
             <td>$!NAMA_PB</td>
           </tr>
           <tr>
             <td ></td>
             <td style="text-transform:uppercase">No. LOT/PT</td>
             <td>:</td>
             <td>$!NO_LOT</td>
           </tr>
           <tr style="display:none">
             <td ></td>
             <td style="text-transform:uppercase">NO. PT</td>
             <td>:</td>
             <td>$!NO_PT</td>           
           </tr>
           <tr>
             <td ></td>
             <td style="text-transform:uppercase">MUKIM</td>
             <td>:</td>
             <td>$!NAMA_MUKIM</td>           
           </tr>
           <tr>
               <td ></td>
             <td style="text-transform:uppercase">BAHAGIAN</td>
             <td>:</td>
             <td>
              #if($SYER_ATAS!="" && $SYER_BAWAH!="")
                      $SYER_ATAS / $SYER_BAWAH
                      #else
                      
                      #end    
             </td>            
           </tr>
           
            <tr style="display:none">
               <td ></td>
             <td style="text-transform:uppercase">LUAS YANG DIAMBIL</td>
             <td>:</td>
             <td>$!LUAS_AMBIL</td>            
           </tr>
                   
          <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">JUMLAH PAMPASAN </td>
        <td width="1%">:</td>
        <td width="70%">
      RM
        #if($readmode == "view")
    #if($BAYAR_PAMPASAN != "")
    #set($BAYAR_PAMPASANX = $Util.formatDecimal($BAYAR_PAMPASAN))
    #else
    #set($BAYAR_PAMPASANX = 0)
    #end
    #else
    #set($BAYAR_PAMPASANX = $Util.formatDecimal($BAYAR_PAMPASAN)) 
    #end
        $!BAYAR_PAMPASANX
        
        
        <input name="txtJumlahPampasan" type="hidden" id="txtJumlahPampasan" size="15" maxlength="150"   value="$BAYAR_PAMPASAN"  readonly class="disabled" />
 </td>
      </tr>
           
           
           
           <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">JUMLAH LAIN-LAIN KOS</td>
        <td width="1%">:</td>
        <td width="70%">
      RM
        #if($readmode == "view")
    #if($JUMLAH_SUBAWARD != "")
    #set($JUMLAH_SUBAWARDX = $Util.formatDecimal($JUMLAH_SUBAWARD))
    #else
    #set($JUMLAH_SUBAWARDX = 0)
    #end
    #else
    #set($JUMLAH_SUBAWARDX = $Util.formatDecimal($JUMLAH_SUBAWARD)) 
    #end
        $!JUMLAH_SUBAWARDX
        
        
        <input name="txtJumlahSubPampasan" type="hidden" id="txtJumlahSubPampasan" size="15" maxlength="150"   value="$JUMLAH_SUBAWARD"  readonly class="disabled" />
 </td>
      </tr>
           
             <tr>
             <td ></td>
             <td style="text-transform:uppercase">NAMA BANK PB</td>
             <td>:</td>
             <td>$!NAMA_BANK</td>           
           </tr>
             <tr>
             <td ></td>
             <td style="text-transform:uppercase">NO. AKAUN</td>
             <td>:</td>
             <td>$!NO_AKAUN</td>           
           </tr>
 	 </table>
     #else
     
     #end
</fieldset>
     <input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" >
     <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<!------------------------------------------- END HEADER ---------------------------------------------->  

