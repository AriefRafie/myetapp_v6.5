  #set($SS1 = 0)
  #set($SS2 = 0)
  #set($SS3 = 0)
  #set($SS4 = 0)
  #set($SS5 = 0)
  #set($SS6 = 0)
  #set($SS7 = 0)
  #set($SS8 = 0)
  
  
  #set($SST1 = 0)
  #set($SST2 = 0)
  #set($SST3 = 0)
  #set($SST4 = 0)
  
  #set($id_kpisasaran = "")
  
  
  
  #foreach($list in $senarai_kpisasaran) 
  
  
  #set($SST1 = $list.F5+1)
  #set($SST2 = $list.F6+1)
  #set($SST3 = $list.F7+1)
  #set($SST4 = $list.F8+1)
   
  #set($SS1 = $list.F1)
  #set($SS2 = $list.F2)
  #set($SS3 = $list.F3)
  #set($SS4 = $list.F4)
  #set($SS5 = $list.F5)
  #set($SS6 = $list.F6)
  #set($SS7 = $list.F7)
  #set($SS8 = $list.F8)
  #set($id_kpisasaran = $list.ID_KPISASARAN)
  #end









#set($B1 = 0)
#set($B2 = 0)
#set($B3 = 0)
#set($B4 = 0)

#set($C1 = 0)
#set($C2 = 0)
#set($C3 = 0)
#set($C4 = 0)

#set($D1 = 0)
#set($D2 = 0)
#set($D3 = 0)
#set($D4 = 0)


#set($DITERIMA = 0)
#set($LOT_DITERIMA = 0)
#set($DISELESAI = 0)
#set($LOT_DISELESAI = 0)


#set($M1 = 0)
#set($M2 = 0)
#set($M3 = 0)

#set($N1 = 0)
#set($N2 = 0)
#set($N3 = 0)

#foreach($list in $list_KPI_Menunggu_Bantahan_Perintah)
#if($list.HARI_TUNGGU_BANTAHAN_PERINTAH <= $SS5 && $list.HARI_TUNGGU_BANTAHAN_PERINTAH > 0)
#set($M1 = $M1 + 1)
#end
#if($list.HARI_TUNGGU_BANTAHAN_PERINTAH <= $SS6 && $list.HARI_TUNGGU_BANTAHAN_PERINTAH >= $SST1 && $list.HARI_TUNGGU_BANTAHAN_PERINTAH > 0)
#set($M2 = $M2 + 1)
#end
#if($list.HARI_TUNGGU_BANTAHAN_PERINTAH >= $SST2)
#set($M3 = $M3 + 1)
#end
#end



#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $SS7 && $list.HARI_TUNGGU_BAYAR > 0)
#set($N1 = $N1 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $SS8 && $list.HARI_TUNGGU_BAYAR >= $SST3 && $list.HARI_TUNGGU_BAYAR > 0)
#set($N2 = $N2 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $SST4)
#set($N3 = $N3 + 1)
#end
#end


#foreach($list in $list_KPI_Bantahan)
#set($B1 = $list.B1)
#set($B2 = $list.B2)
#set($B3 = $list.B3)
#set($B4 = $list.B4)

#set($C1 = $list.C1)
#set($C2 = $list.C2)
#set($C3 = $list.C3)
#set($C4 = $list.C4)

#set($D1 = $list.D1)
#set($D2 = $list.D2)
#set($D3 = $list.D3)
#set($D4 = $list.D4)


#set($DITERIMA = $list.DITERIMA)
#set($LOT_DITERIMA = $list.LOT_DITERIMA)
#set($DISELESAI = $list.DISELESAI)
#set($LOT_DISELESAI = $list.LOT_DISELESAI)
#end






 #set($nama_pejabat = "") 
     
     #foreach($list_pejabat1 in $list_pejabat)
     #if($!socPejabat == $list_pejabat1.ID_PEJABATJKPTG)     
     #set($nama_pejabat = $list_pejabat1.NAMA_PEJABAT +", "+ $list_pejabat1.JENIS_PEJABAT) </option>
     #end     
     #end
     
     
     
  
  
<input type="hidden" name="open_penetapan" id="open_penetapan" value="$!open_penetapan" /> 
 
 
#if($portal_role == "(PPT)PengarahUnit" || $portal_role == "(PPT)PenolongPengarahUnit" )
<a href="javascript:javascript:setP()"><font color="blue">Maklumat Penetapan Sasaran KPI</font>
#end

#if(($portal_role == "(PPT)PengarahUnit" || $portal_role == "(PPT)PenolongPengarahUnit") && $!open_penetapan == "yes")
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
  
    
<table width="100%"  id="table_sasaran" >
<tr>
  <td colspan="4">
  <fieldset>
  <legend>MAKLUMAT PENETAPAN SASARAN KPI</legend>
  
  
  
  
  <input type="hidden" name="id_kpisasaran" id="id_kpisasaran" value="$!id_kpisasaran" />
  <table width="80%" align="center">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="55%">Pejabat/Unit</td>
    <td width="3%"><div align="center"><strong>:</strong></div></td>
    <td width="40%">$nama_pejabat</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jenis Urusan</td>
    <td><div align="center"><strong>:</strong></div></td>
    <td>BANTAHAN KE MAHKAMAH</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><div align="center"></div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><strong>1)</strong></td>
    <td><strong>Sasaran Aktiviti :</strong></td>
    <td><div align="center"></div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">a)</td>
    <td valign="top">Terima Borang N <strong>SEHINGGA</strong> Borang O dihantar ke Mahkamah</td>
    <td valign="top"><div align="center"><strong>:</strong></div></td>
    <td valign="top"> <input name="SS1" type="text" id="SS1" size="5" maxlength="5" value="$SS1"  style="text-align:center"  onblur="validateTarikh(this,this.value);" onkeyup="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" ></td>
  </tr>
  <tr>
    <td valign="top">b)</td>
    <td valign="top" >Terima Perintah Dari Mahkamah <strong>SEHINGGA </strong>Pembayaran Di hantar kepada Orang Berkepentingan dalam tempoh yang telah ditetapkan (oleh Mahkamah)</td>
    <td valign="top"><div align="center"><strong>:</strong></div></td>
    <td valign="top"><input name="SS2" type="text" id="SS2" size="5" maxlength="5" value="$SS2"  style="text-align:center"  onblur="validateTarikh(this,this.value);" onkeyup="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" ></td>
  </tr>
  <tr>
    
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><div align="center"></div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><strong>2)</strong></td>
    <td><strong>Permohonan masih dalam proses :</strong></td>
    <td><div align="center"></div></td>
    <td>&nbsp;</td>
  </tr>
   <tr>
    <td>a)</td>
    <td>Menunggu Mahkamah Mengeluarkan Perintah</td>
    <td bgcolor="#33FF99" ><div align="center"><strong>:</strong></div></td>
    <td >
    <table width="100%">
    <tr>
    <td width="25%">
      <div align="center">&lt;        </div></td>
     <td width="20%">
       <div align="center">
         <input name="SS5" type="text" id="SS5" size="4" maxlength="5" value="$SS5"  style="text-align:center"  onblur="validateTarikh(this,this.value);tetap();" onkeyup="validateTarikh(this,this.value);tetap();" $readonlymode class = "$disabledmode"  >    
       </div></td>
     <td width="55%"><strong>
     hari    </strong></td  >
    </tr>    
    </table>       
    
         </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td bgcolor="yellow"><div align="center"><strong>:</strong></div></td>
    <td >
      <table width="100%">
    <tr>
    <td width="25%">
    
      <div align="center"><span id="SX1"> </span>
        -      </div></td>
     <td width="20%">
       <div align="center">
         <input name="SS6" type="text" id="SS6" size="4" maxlength="5" value="$SS6"  style="text-align:center"  onblur="validateTarikh(this,this.value);tetap();" onkeyup="validateTarikh(this,this.value);tetap();" $readonlymode class = "$disabledmode" >    
       </div></td>
     <td width="55%"><strong>
     hari
     </strong></td  >
    </tr>    
    </table>     
    
    
    
    
      
      </td>
  </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td bgcolor="red"><div align="center"><strong>:</strong></div></td>
    <td > 
    
        <table width="100%">
    <tr>
    <td width="25%">
    
      <div align="center">&gt;        </div></td>
    
     <td width="20%">
       <div align="center"><span id="SX2"  > </span> </div></td>
     <td width="55%"><strong>
     hari    </strong></td  >
    </tr>    
    </table>  
    
      
    
      </td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><div align="center"></div></td>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <td colspan="4">
   
  
    <div align="center">#if($readmode == "edit")
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpan()">
      #if($id_kpisiasatan == "")
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()">
      #end
      #if($id_kpisiasatan != "")
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal1()">
      #end
      
      
      #end  
      
      #if($readmode == "view")
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()">
      
      
      #end   
      
      
    </div></td>  
  </tr>
</table>

 
  </fieldset>
  </td>
  </tr>
</table>
#end


<table width="100%" id="table_kpi">



<tr>
        <td width="100%" ><div align="center"><strong>SEKSYEN PENGURUSAN PENGAMBILAN TANAH : PETUNJUK PRESTASI UTAMA (<em>LEADING KPI</em>)</strong></div></td>
       
      </tr>
      <tr>
      <td width="100%"><div align="center"><strong>URUSAN : BANTAHAN KE MAHKAMAH</strong></div></td>
      
      </tr>
   
      <tr>
              
        <td width="100%">
    
        
        <div align="center"><strong>PEJABAT/UNIT : $nama_pejabat</strong></div></td>
        
      </tr>
      <tr>
        <td width="100%" ><div align="center"><strong>TARIKH MULA : $!txdTarikhMula</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>TARIKH AKHIR : $!txdTarikhAkhir</strong></div></td>
       
      </tr>
    </table>
<fieldset>
<legend>MAKLUMAT KPI</legend>
<table width="100%">
  <tr class="table_headerkpi">
      <td width="25%"><div align="center"><strong>AKTIVITI <BR>
  (ALIRAN PROSES "TO BE")</strong></div></td>
    <td width="10%"><div align="center"><strong>Sasaran masa menyiapkan satu aktiviti</strong></div></td>
    <td width="10%"><div align="center"><strong>Jumlah sebenar bilangan aktiviti dilakukan</strong></div></td>
    <td width="10%"><div align="center"><strong>Jumlah masa sebenar menyiapkan semua aktiviti</strong></div></td>
    <td width="10%"><div align="center"><strong>Purata masa menyiapkan satu aktiviti</strong></div></td>
    <td width="10%"><div align="center"><strong>Efisensi Aktiviti (Kecekapan)</strong></div></td>
    <td colspan="2" ><div align="center"><strong>KEBERKESANAN - (OUTCOME)</strong></div></td>
  </tr>
  <tr class="table_headerkpi">
    <td><div align="center"><strong>A <br>
        </strong><em>Preset</em></div></td>
     <td><div align="center"><strong>B <br>
        </strong><em>Preset</em></div></td>
     <td><div align="center"><strong>C <br>     
        </strong><em>(direct capture)</em></div></td>
             <td><div align="center"><strong>D<br>     
             </strong><em>(direct capture)</em></div></td>
    <td><div align="center"><strong>E = D/C <br>
        </strong><em>(compute)</em></div></td>
     <td><div align="center"><strong>F = B/E % <br>
        </strong><em>(compute)</em></div></td>
    
    <td  width="15%"  ><div align="center"><strong>(Permohonan menunggu) :</strong></div></td>
   <td width="10%"><div align="center"><strong>Bilangan<br>     
    </strong><em>(direct capture)</em></div></td>
  </tr>
   <tr >
    <td class="row2">Terima Borang N <strong>SEHINGGA</strong> Borang O dihantar ke Mahkamah</td>
    <td class="row2">
      
        <div align="center">
          <strong>$SS1</strong>
          <input name="B1" type="hidden" id="B1" size="5" maxlength="5" value="$SS1"  style="text-align:center" onkeyup="A1()" >
        </div></td>
    <td class="row2"><div align="center">
        <strong>$C1</strong>
        <input name="C1" type="hidden" id="C1" size="5" maxlength="5" value="$C1" >
        </div></td>
    <td class="row2"><div align="center">
        <strong>$D1</strong>
        <input name="D1" type="hidden" id="D1" size="5" maxlength="5" value="$D1">
        </div></td>
    <td class="row2">
     <input name="E1T" type="hidden" id="E1T" >
    <div align="center" id="E1" >      
     </div></td>
    <td class="row2">
     <input name="F1T" type="hidden" id="F1T" >
    <div align="center" id="F1">
         
     </div></td>
    <td  rowspan="7" colspan="2" valign="top">
     
     <table width="100%" class="table_headerkpi">
     <tr class="row2">
     <td width="60%"><table width="100%">
       <tr>
         <td width="15%"  valign="top">1.</td>
         <td width="85%">Bil. Permohonan Bantahan</td>
       </tr>
     </table></td>
      <td width="40%" > <div align="center"><strong>
      $!DITERIMA
            <input name="DITERIMA" type="hidden" id="DITERIMA" size="5" maxlength="5" value="$DITERIMA">
        </strong></div></td>
     </tr>
     <tr class="row2">
     <td>
     <table width="100%">
       <tr>
         <td width="15%"  valign="top">2.</td>
         <td width="85%">Bil. Permohonan Bantahan diselesaikan</td>
       </tr>
     </table>     </td>
      <td > <div align="center"><strong>
            $!DISELESAI
              <input name="DISELESAI" type="hidden" id="DISELESAI" size="5" maxlength="5" value="$DISELESAI">
        </strong></div>   </td>
     </tr>
     <tr class="row2">
     <td colspan="2"><table width="100%">
       <tr>
         <td width="10%"  valign="top">3.</td>
         <td width="90%">Permohonan masih dalam proses :-</td>
       </tr>
     </table>       </td>
     </tr>
     <tr>
     <td colspan="2">
     <table width="100%" class="row1">
     <tr>
     <td colspan="2"><table width="100%">
       <tr>
         <td width="10%"></td>
         <td width="5%" valign="top">a.</td>
         <td width="85%">Menunggu Mahkamah Mengeluarkan Perintah</td>
       </tr>
     </table>        </td>
     </tr>
     <tr>
     <td width="60%"><div align="right">&lt; $SS5 hari
       
     </div></td>
      <td bgcolor="#33FF99" width="40%" >
      
        <div align="center">
         <a style="cursor:pointer" onClick="popup('3','$SS5','0','A','Menunggu Mahkamah Mengeluarkan Perintah < $SS5 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$M1</font></strong></a> 
        <input name="M1" type="hidden" id="M1" size="5" maxlength="5" value="$M1" >
        </div>      </td>
     </tr>
     <tr>
     <td><div align="right">
     
     $SST1 - $SS6 hari
       
     </div></td>
      <td bgcolor="yellow" >
       <div align="center">
        <a style="cursor:pointer" onClick="popup('3','$SST1','$SS6','B','Menunggu Mahkamah Mengeluarkan Perintah $SST1 - $SS6 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$M2</font></strong></a>
        <input name="M2" type="hidden" id="M2" size="5" maxlength="5" value="$M2" >
        </div>          </td>
     </tr>
     <tr>
     <td><div align="right">&gt; $SST2 hari
       
     </div></td>
      <td  bgcolor="red"> <div align="center">
        <a style="cursor:pointer" onClick="popup('3','$SST2','0','C','Menunggu Mahkamah Mengeluarkan Perintah > $SST2 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$M3</font></strong></a>
        <input name="M3" type="hidden" id="M3" size="5" maxlength="5" value="$M3" >
        </div> </td>
     </tr>
     </table>     </td>
     </tr>
     </table>     </td>
  </tr>
  <tr>
    <td colspan="6">Menunggu Mahkamah Mengeluarkan Perintah</td>
  </tr>
   <tr>
    <td class="row2">Terima Perintah Dari Mahkamah <strong>SEHINGGA </strong>Pembayaran Di hantar kepada Orang Berkepentingan dalam tempoh yang telah ditetapkan (oleh Mahkamah)</td>
    <td class="row2"><div align="center">
      <strong>$SS2</strong>
      <input name="B2" type="hidden" id="B2" size="5" maxlength="5" value="$SS2" style="text-align:center" onkeyup="A2()">
    </div></td>
    <td class="row2"><div align="center">
        <strong>$C2</strong>
        <input name="C2" type="hidden" id="C2" size="5" maxlength="5" value="$C2">
        </div></td>
    <td class="row2"><div align="center">
        <strong>$D2</strong>
        <input name="D2" type="hidden" id="D2" size="5" maxlength="5" value="$D2">
        </div></td>
    <td class="row2">
     <input name="E2T" type="hidden" id="E2T" >
    <div align="center" id="E2" >
         
     </div></td>
    <td class="row2">
     <input name="F2T" type="hidden" id="F2T" >
    <div align="center" id="F2" >
          
     </div></td>
  </tr>
    <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
</table>
</fieldset>

  <input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />


<script type="text/javascript">
 A1();
 A2();
 //A3();
 //A4();
 
 if(('$portal_role' == "(PPT)PengarahUnit" || '$portal_role' == "(PPT)PenolongPengarahUnit") && '$open_penetapan' == "yes")
 {
 tetap();
 }

 function A1()
 {
 var B1 = document.${formName}.B1.value;
 var C1 = document.${formName}.C1.value;
 var D1 = document.${formName}.D1.value;
 
 if(D1>0 && B1>0)
 {
 var E1 = D1/C1; 
 document.${formName}.E1T.value = E1.toFixed(2);
 $jquery("#E1").html("<font color='blue'><strong>"+E1.toFixed(2)+"</strong></font>");
 
 var F1 = (B1*100)/E1 ; 
 document.${formName}.F1T.value = F1.toFixed(2);
 $jquery("#F1").html("<font color='blue'><strong>"+F1.toFixed(2)+"%</strong></font>");
 }
 else
 {
 document.${formName}.E1T.value = "0.00";
 document.${formName}.F1T.value = "0.00";
 $jquery("#E1").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F1").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function A2()
 {
 var B2 = document.${formName}.B2.value;
 var C2 = document.${formName}.C2.value;
 var D2 = document.${formName}.D2.value;
 
 if(D2>0 && B2>0)
 {
 var E2 = D2/C2; 
 document.${formName}.E2T.value = E2.toFixed(2);
 $jquery("#E2").html("<font color='blue'><strong>"+E2.toFixed(2)+"</strong></font>");
 
 var F2 = (B2*100)/E2 ; 
 document.${formName}.F2T.value = F2.toFixed(2);
 $jquery("#F2").html("<font color='blue'><strong>"+F2.toFixed(2)+"%</strong></font>");
 }
 else
 {
 document.${formName}.E2T.value = "0.00";
 document.${formName}.F2T.value = "0.00";
 $jquery("#E2").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F2").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function A3()
 {
 var B3 = document.${formName}.B3.value;
 var C3 = document.${formName}.C3.value;
 var D3 = document.${formName}.D3.value;
 
 if(D3>0 && B3>0)
 {
 var E3 = D3/C3; 
 $jquery("#E3").html("<font color='blue'><strong>"+E3.toFixed(2)+"</strong></font>");
 
 var F3 = (B3*100)/E3 ; 
 $jquery("#F3").html("<font color='blue'><strong>"+F3.toFixed(2)+"%</strong></font>");
 }
 else
 {
 $jquery("#E3").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F3").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function A4()
 {
 var B4 = document.${formName}.B4.value;
 var C4 = document.${formName}.C4.value;
 var D4 = document.${formName}.D4.value;
 
 if(D4>0 && B4>0)
 {
 var E4 = D4/C4; 
 $jquery("#E4").html("<font color='blue'><strong>"+E4.toFixed(2)+"</strong></font>");
 
 var F4 = (B4*100)/E4 ; 
 $jquery("#F4").html("<font color='blue'><strong>"+F4.toFixed(2)+"%</strong></font>");
 }
 else
 {
 $jquery("#E4").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F4").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function tetap()
 {
 
 var SS5 = document.${formName}.SS5.value;
 var SS6 = document.${formName}.SS6.value;
 /*var SS7 = document.${formName}.SS7.value;
 var SS8 = document.${formName}.SS8.value;*/
 
 var SX1 = 0;
 var SX2 = 0;
 /*var SX3 = 0;
 var SX4 = 0;*/
 
 if(SS5!="")
 {
 SX1 = parseFloat(SS5)+1;
 }
 
 if(SS6!="")
 {
 SX2 = parseFloat(SS6)+1;
 }
 /*
 if(SS7!="")
 {
 SX3 = parseFloat(SS7)+1;
 }
 
 if(SS8!="")
 {
 SX4 = parseFloat(SS8)+1;
 }*/
 
 $jquery("#SX1").html("<font color='blue' style='text-align:center' ><strong>"+SX1+"</strong></font>");
 $jquery("#SX2").html("<font color='blue' style='text-align:center'><strong>"+SX2+"</strong></font>");
/* $jquery("#SX3").html("<font color='blue' style='text-align:center'><strong>"+SX3+"</strong></font>");
 $jquery("#SX4").html("<font color='blue' style='text-align:center'><strong>"+SX4+"</strong></font>");*/
 
 }
 
function simpan()
{


if(document.${formName}.SS1.value == "" || document.${formName}.SS1.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS1.focus();
return;
}
else if(document.${formName}.SS2.value == "" || document.${formName}.SS2.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS2.focus();
return;
}
/*
else if(document.${formName}.SS3.value == "" || document.${formName}.SS3.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS3.focus();
return;
}
else if(document.${formName}.SS4.value == "" || document.${formName}.SS4.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS4.focus();
return;
}*/
else if(document.${formName}.SS5.value == "" || document.${formName}.SS5.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS5.focus();
return;
}
else if(document.${formName}.SS6.value == "" || document.${formName}.SS6.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS6.focus();
return;
}
/*
else if(document.${formName}.SS7.value == "" || document.${formName}.SS7.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS7.focus();
return;
}
else if(document.${formName}.SS8.value == "" || document.${formName}.SS8.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS6.focus();
return;
}*/
else
{
    input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.Leading_KPI";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.submit();
	}
}
 
 
}
 
 
 
 
 
 
 
 function batal()
 {
    document.${formName}.command.value = "KPI";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.Leading_KPI";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.submit();
 }
 
 function batal1()
 {
    document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.Leading_KPI";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.submit();
 }
 
 function kemaskini()
 {
    document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.Leading_KPI";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.submit();
 }
 
 function setP()
 {
    if(document.${formName}.open_penetapan.value=="yes"){
	document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "close_penetapan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.Leading_KPI";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.open_penetapan.value = "";
	document.${formName}.submit();
	}
	else if(document.${formName}.open_penetapan.value==""){
	document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "open_penetapan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.Leading_KPI";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.open_penetapan.value = "yes";
	document.${formName}.submit();
	}
   
 }
 
 

 </script>

