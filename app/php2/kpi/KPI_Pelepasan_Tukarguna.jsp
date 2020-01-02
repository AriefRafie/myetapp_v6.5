
#set($SS1 = 0)
#set($SS2 = 0)
#set($SS3 = 0)
#set($SS4 = 0)
#set($SS5 = 0)
#set($SS6 = 0)
#set($SS7 = 0)
#set($SS8 = 0)
#set($SS9 = 0)
#set($SS10 = 0)  
  
#set($SST1 = 0)
#set($SST2 = 0)
#set($SST3 = 0)
#set($SST4 = 0)
#set($SST5 = 0)
#set($SST6 = 0)
#set($SST7 = 0)
#set($SST8 = 0)
#set($SST9 = 0)
#set($SST10 = 0) 
#set($SST11 = 0)
#set($SST12 = 0)
#set($SST13 = 0)
#set($SST14 = 0)
#set($SST15 = 0)
#set($SST16 = 0)  
  
  
#set($SX1 = 0)
#set($SX2 = 0)
#set($SX3 = 0)
#set($SX4 = 0)
#set($SX5 = 0)
#set($SX6 = 0)
#set($SX7 = 0)
#set($SX8 = 0)
#set($SX9 = 0)
#set($SX10 = 0) 
#set($SX11 = 0)
#set($SX12 = 0)
#set($SX13 = 0)
#set($SX14 = 0)
#set($SX15 = 0)
#set($SX16 = 0) 
   
  
#set($id_kpisasaran = "") 
  #foreach($list in $senarai_kpisasaran)   

  #set($SS1 = $list.F1)
  #set($SS2 = $list.F2)
  #set($SS3 = $list.F3)
  #set($SS4 = $list.F4)
  #set($SS5 = $list.F5)
  #set($SS6 = $list.F6)
  #set($SS7 = $list.F7)
  #set($SS8 = $list.F8)
  #set($SS9 = $list.F9)
  #set($SS10 = $list.F10)
  
  #set($SX1 = $list.F11)
  #set($SX2 = $list.F12)
  #set($SX3 = $list.F13)
  #set($SX4 = $list.F14)
  #set($SX5 = $list.F15)
  #set($SX6 = $list.F16)
  #set($SX7 = $list.F17)
  #set($SX8 = $list.F18)
  #set($SX9 = $list.F19)
  #set($SX10 = $list.F20)
  #set($SX11 = $list.F21)
  #set($SX12 = $list.F22)
  #set($SX13 = $list.F23)
  #set($SX14 = $list.F24)
  #set($SX15 = $list.F25)
  #set($SX16 = $list.F26)
  
  #set($SST1 = $list.F11+1)
  #set($SST2 = $list.F12+1)
  #set($SST3 = $list.F13+1)
  #set($SST4 = $list.F14+1)
  #set($SST5 = $list.F15+1)
  #set($SST6 = $list.F16+1)
  #set($SST7 = $list.F17+1)
  #set($SST8 = $list.F18+1)
  #set($SST9 = $list.F19+1)
  #set($SST10 = $list.F20+1)
  #set($SST11 = $list.F21+1)
  #set($SST12 = $list.F22+1)
  #set($SST13 = $list.F23+1)
  #set($SST14 = $list.F24+1)
  #set($SST15 = $list.F25+1)
  #set($SST16 = $list.F26+1)
  
  #set($id_kpisasaran = $list.ID_KPISASARAN)  
  #end









#set($B1 = 0)
#set($B2 = 0)
#set($B3 = 0)
#set($B4 = 0)
#set($B5 = 0)
#set($B6 = 0)
#set($B7 = 0)
#set($B8 = 0)
#set($B9 = 0)
#set($B10 = 0)


#set($C1 = 0)
#set($C2 = 0)
#set($C3 = 0)
#set($C4 = 0)
#set($C5 = 0)
#set($C6 = 0)
#set($C7 = 0)
#set($C8 = 0)
#set($C9 = 0)
#set($C10 = 0)

#set($D1 = 0)
#set($D2 = 0)
#set($D3 = 0)
#set($D4 = 0)
#set($D5 = 0)
#set($D6 = 0)
#set($D7 = 0)
#set($D8 = 0)
#set($D9 = 0)
#set($D10 = 0)


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

#set($O1 = 0)
#set($O2 = 0)
#set($O3 = 0)

#set($P1 = 0)
#set($P2 = 0)
#set($P3 = 0)

#set($Q1 = 0)
#set($Q2 = 0)
#set($Q3 = 0)

#set($R1 = 0)
#set($R2 = 0)
#set($R3 = 0)

#set($S1 = 0)
#set($S2 = 0)
#set($S3 = 0)

#set($T1 = 0)
#set($T2 = 0)
#set($T3 = 0)


#foreach($list in $list_KPI_MENUNGGU_TUKARGUNA_A)
#if($list.HARI_TUNGGU <= $SX1 && $list.HARI_TUNGGU > 0)
#set($M1 = $M1 + 1)
#end
#if($list.HARI_TUNGGU <= $SX2 && $list.HARI_TUNGGU >= $SST1 && $list.HARI_TUNGGU > 0)
#set($M2 = $M2 + 1)
#end
#if($list.HARI_TUNGGU >= $SST2)
#set($M3 = $M3 + 1)
#end
#end


#foreach($list in $list_KPI_MENUNGGU_TUKARGUNA_B)
#if($list.HARI_TUNGGU <= $SX3 && $list.HARI_TUNGGU > 0)
#set($N1 = $N1 + 1)
#end
#if($list.HARI_TUNGGU <= $SX4 && $list.HARI_TUNGGU >= $SST3 && $list.HARI_TUNGGU > 0)
#set($N2 = $N2 + 1)
#end
#if($list.HARI_TUNGGU >= $SST4)
#set($N3 = $N3 + 1)
#end
#end



#foreach($list in $listMaklumatKPITukarguna)
#set($B1 = $list.B1)
#set($B2 = $list.B2)
#set($B3 = $list.B3)
#set($B4 = $list.B4)
#set($B5 = $list.B5)
#set($B6 = $list.B6)
#set($B7 = $list.B7)
#set($B8 = $list.B8)
#set($B9 = $list.B9)
#set($B10 = $list.B10)

#set($C1 = $list.C1)
#set($C2 = $list.C2)
#set($C3 = $list.C3)
#set($C4 = $list.C4)
#set($C5 = $list.C5)
#set($C6 = $list.C6)
#set($C7 = $list.C7)
#set($C8 = $list.C8)
#set($C9 = $list.C9)
#set($C10 = $list.C10)


#set($D1 = $list.D1)
#set($D2 = $list.D2)
#set($D3 = $list.D3)
#set($D4 = $list.D4)
#set($D5 = $list.D5)
#set($D6 = $list.D6)
#set($D7 = $list.D7)
#set($D8 = $list.D8)
#set($D9 = $list.D9)
#set($D10 = $list.D10)

#set($DITERIMA = $list.DITERIMA)
#set($LOT_DITERIMA = $list.LOT_DITERIMA)
#set($DISELESAI = $list.DISELESAI)
#set($LOT_DISELESAI = $list.LOT_DISELESAI)
#end






     #set($nama_pejabat = "")      
     #foreach($list_pejabat1 in $list_pejabat)
     #if($!socPejabat == $list_pejabat1.ID_PEJABATJKPTG)     
     #set($nama_pejabat = $list_pejabat1.NAMA_PEJABAT +", "+ $list_pejabat1.JENIS_PEJABAT)
     </option>
     #end     
     #end
     
     
     
  
  <input type="hidden" name="open_penetapan" id="open_penetapan" value="$!open_penetapan" /> 

  <a href="javascript:javascript:setP()"><font color="blue">Maklumat Penetapan Sasaran KPI</font></a>
  
   #if($portal_role == "(PHP)AdminPHP")

#end

#if( $!open_penetapan == "yes")
#if($readmode == "edit")
#set($disabledmode = "")
#set($readonlymode = "")
#elseif($readmode == "view")
#set($disabledmode = "disabled")
#set($readonlymode = "readonly")
#else
#set($disabledmode = "")
#set($readonlymode = "")
#end </p>
<table width="100%"  id="table_sasaran" >
<tr>
  <td colspan="4">
  <fieldset>
  <legend>MAKLUMAT PENETAPAN SASARAN KPI</legend>
  
  
  
  
  <input type="hidden" name="id_kpisasaran" id="id_kpisasaran" value="$!id_kpisasaran" />
  <table width="80%" align="center">
   <tr>
    <td width="1%"></td>
    <td width="55%"></td>
    <td width="3%"></td>
    <td width="40%"></td>
  </tr>
  <tr>
        <td width="100%" colspan="4" ><div align="center"><strong>SEKSYEN PENGUATKUASAAN DAN HASIL PERSEKUTUAN : PETUNJUK PRESTASI UTAMA (LEADING KPI)</strong></div></td>
  </tr>
  <tr>
      <td width="100%" colspan="4"><div align="center"><strong>URUSAN : PENAWARAN</strong></div></td>
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
    <td >Mendaftar Permohonan <strong>SEHINGGA</strong> hantar surat kepada Jabatan Teknikal untuk mendapat ulasan.</td>
    <td valign="top"><div align="center"><strong>:</strong></div></td>
    <td valign="top"> <input name="SS1" type="text" id="SS1" size="5" maxlength="5" value="$SS1"  style="text-align:center"  onblur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" ></td>
  </tr>
  <tr>
    <td valign="top">b)</td>
    <td >Terima ulasan dari Jabatan Teknikal serta selesai membuat Laporan Lawatan Tapak <strong>SEHINGGA</strong> mengeluarkan surat Tawaran Penawaran kepada semua KJP</td>
    <td valign="top"><div align="center"><strong>:</strong></div></td>
    <td valign="top"><input name="SS2" type="text" id="SS2" size="5" maxlength="5" value="$SS2"  style="text-align:center"  onblur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" ></td>
  </tr>
  <tr>
    <td valign="top">c)</td>
    <td >Terima respon dari KJP <strong>SEHINGGA</strong> selesai menyediakan Kertas Cadangan serta hantar ke mesyuarat J/K Khas EPU</td>
    <td valign="top"><div align="center"><strong>:</strong></div></td>
    <td valign="top"><input name="SS3" type="text" id="SS3" size="5" maxlength="5" value="$SS3"  style="text-align:center" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode"  ></td>
  </tr>
  <tr>
    <td valign="top">d)</td>
    <td ><p>Terima keputusan Mesyuarat <strong>SEHINGGA</strong> surat dihantar ke Pemohon</p></td>
    <td valign="top"><div align="center"><strong>:</strong></div></td>
    <td valign="top"><input name="SS4" type="text" id="SS4" size="5" maxlength="5" value="$SS4"  style="text-align:center" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  $readonlymode class = "$disabledmode" ></td>
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
    <td>Menunggu ulasan Jabatan Teknikal</td>
    <td bgcolor="#33FF99" ><div align="center"><strong>:</strong></div></td>
    <td >
    <table width="100%">
    <tr>
    <td width="25%">
      <div align="center">&lt;        </div></td>
     <td width="20%">
       <div align="center">
         <input name="SX1" type="text" id="SX1" size="4" maxlength="5" value="$SX1"  style="text-align:center"  onblur="validateTarikh(this,this.value);tetap();" onKeyUp="validateTarikh(this,this.value);tetap();" $readonlymode class = "$disabledmode"  >    
       </div></td>
     <td width="55%"><strong>
     hari    </strong></td  >
    </tr>    
    </table>         </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td bgcolor="yellow"><div align="center"><strong>:</strong></div></td>
    <td >
      <table width="100%">
    <tr>
    <td width="25%">
    
      <div align="center"><span id="SST1"> </span>
        -      </div></td>
     <td width="20%">
       <div align="center">
         <input name="SX2" type="text" id="SX2" size="4" maxlength="5" value="$SX2"  style="text-align:center"  onblur="validateTarikh(this,this.value);tetap();" onKeyUp="validateTarikh(this,this.value);tetap();" $readonlymode class = "$disabledmode" >    
       </div></td>
     <td width="55%"><strong>
     hari
     </strong></td  >
    </tr>    
    </table>      </td>
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
       <div align="center"><span id="SST2"  > </span> </div></td>
     <td width="55%"><strong>
     hari    </strong></td  >
    </tr>    
    </table>      </td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><div align="center"></div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>b)</td>
    <td>Menunggu Keputusan Mesyuarat J/K Khas EPU</td>
    <td bgcolor="#33FF99"><div align="center"><strong>:</strong></div></td>
    <td >  
    
    <table width="100%">
    <tr>
    <td width="25%">
      <div align="center">&lt;        </div></td>
     <td width="20%">
       <div align="center">
         <input name="SX3" type="text" id="SX3" size="4" maxlength="5" value="$SX3"  style="text-align:center"  onblur="validateTarikh(this,this.value);tetap();" onKeyUp="validateTarikh(this,this.value);tetap();" $readonlymode class = "$disabledmode"  >    
       </div></td>
     <td width="55%"><strong>
     hari    </strong></td  >
    </tr>    
    </table>         </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td bgcolor="yellow"><div align="center"><strong>:</strong></div></td>
    <td >
      <table width="100%">
    <tr>
    <td width="25%">
    
      <div align="center"><span id="SST3"> </span>
        -      </div></td>
     <td width="20%">
       <div align="center">
         <input name="SX4" type="text" id="SX4" size="4" maxlength="5" value="$SX4"  style="text-align:center"  onblur="validateTarikh(this,this.value);tetap();" onKeyUp="validateTarikh(this,this.value);tetap();" $readonlymode class = "$disabledmode" >    
       </div></td>
     <td width="55%"><strong>
     hari
     </strong></td  >
    </tr>    
    </table>      </td>
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
       <div align="center"><span id="SST4"  > </span> </div></td>
     <td width="55%"><strong>
     hari    </strong></td  >
    </tr>    
    </table>     </td>
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
      
      
      #end    </div></td>  
  </tr>
</table>

 
  </fieldset>
  </td>
  </tr>
</table>
#end


<table width="100%" id="table_kpi">



<tr>
        <td width="100%" ><div align="center"><strong>SEKSYEN PENGUATKUASAAN DAN HASIL PERSEKUTUAN : PETUNJUK PRESTASI UTAMA (LEADING KPI)</strong></div></td>
  </tr>
      <tr>
      <td width="100%"><div align="center"><strong>URUSAN : TUKARGUNA TANAH</strong></div></td>
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
    <td class="row2"><p>Mendaftar Permohonan <strong>SEHINGGA</strong> hantar surat kepada Jabatan Teknikal untuk mendapat ulasan.</p></td>
  <td class="row2">
      
        <div align="center">
          <strong>$SS1</strong>
          <input name="B1" type="hidden" id="B1" size="5" maxlength="5" value="$SS1"  style="text-align:center" onKeyUp="A1()" >
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
       <input name="E1T" type="hidden" id="E1T" size="5" maxlength="5" >
    <div align="center" id="E1" ></div></td>
    <td class="row2">
      <input name="F1T" type="hidden" id="F1T" size="5" maxlength="5" >
    <div align="center" id="F1">     </div></td>
    <td  rowspan="18" colspan="2" valign="top">
    <table width="100%" class="table_headerkpi">
     <tr class="row2">
     <td width="60%"><table width="100%">
       <tr>
         <td width="15%"  valign="top">1.</td>
         <td width="85%">Bil. Permohonan</td>
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
         <td width="85%">Bil. Permohonan diselesaikan</td>
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
         <td width="85%">Menunggu ulasan Jabatan Teknikal</td>
       </tr>
     </table>        </td>
     </tr>
     <tr>
     <td width="60%"><div align="right">&lt; $SX1 hari
       
     </div></td>
      <td bgcolor="#33FF99" width="40%" >
      
        <div align="center">
      
        
        
         <a style="cursor:pointer" onClick="popup('8','$SX1','0','A','Menunggu ulasan Jabatan Teknikal < $SX1 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$M1</font></strong></a>    
        
        <input name="M1" type="hidden" id="M1" size="5" maxlength="5" value="$M1" >
        </div>      </td>
     </tr>
     <tr>
     <td><div align="right">
     
     $SST1 - $SX2 hari
       
     </div></td>
      <td bgcolor="yellow" >
       <div align="center">
       
       <a style="cursor:pointer" onClick="popup('8','$SST1','$SX2','B','Menunggu ulasan Jabatan Teknikal $SST1 - $SX2 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$M2</font></strong></a> 
            
        <input name="M2" type="hidden" id="M2" size="5" maxlength="5" value="$M2" >
        </div>          </td>
     </tr>
     <tr>
     <td><div align="right">&gt; $SST2 hari
       
     </div></td>
      <td  bgcolor="red"> <div align="center">
        <a style="cursor:pointer" onClick="popup('8','$SST2','0','C','Menunggu ulasan Jabatan Teknikal > $SST2 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$M3</font></strong></a> 
        <input name="M3" type="hidden" id="M3" size="5" maxlength="5" value="$M3" >
        </div> </td>
     </tr>
     </table>     </td>
     </tr>
     <tr>
     <td colspan="2"><table width="100%" class="row2">
     <tr>
     <td colspan="2"><table width="100%">
       <tr>
         <td width="10%"></td>
         <td width="5%"  valign="top">b.</td>
         <td width="85%">Menunggu Keputusan Mesyuarat J/K Khas EPU</td>
       </tr>
     </table>       </td>
     </tr>
      <tr>
     <td width="60%"><div align="right">&lt; $SX3
       
       hari</div></td>
      <td  bgcolor="#33FF99" width="40%" > <div align="center">
      
          <a style="cursor:pointer" onClick="popup('9','$SX3','0','A','Menunggu Keputusan Mesyuarat J/K Khas EPU < $SX3 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$N1</font></strong></a>
          
        <input name="N1" type="hidden" id="N1" size="5" maxlength="5" value="$N1" >
        </div>  </td>
     </tr>
      <tr>
     <td><div align="right">$SST3 - $SX4 hari
       
     </div></td>
      <td bgcolor="yellow" >
      <div align="center">
       
       <a style="cursor:pointer" onClick="popup('9','$SST3','$SX4','B','Menunggu Keputusan Mesyuarat J/K Khas EPU $SST3 - $SX4 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$N2</font></strong></a> 
       
        <input name="N2" type="hidden" id="N2" size="5" maxlength="5" value="$N2" >
        </div>      </td>
     </tr>
      <tr>
     <td><div align="right">&gt; $SST4 hari
       
     </div></td>
      <td bgcolor="red" >
      <div align="center">
       <a style="cursor:pointer" onClick="popup('9','$SST4','0','C','Menunggu Keputusan Mesyuarat J/K Khas EPU > $SST4 hari');" title="Klik untuk paparan NO FAIL"> <strong ><font color="#000000">$N3</font></strong></a>
        <input name="N3" type="hidden" id="N3" size="5" maxlength="5" value="$N3" >
        </div>      </td>
     </tr>
     
     
     
     </table>     </td>
     </tr>
     </table>     </td>
  </tr>
 <tr>
      <td colspan="6">Menunggu ulasan Jabatan Teknikal</td>
  </tr>
  
  <tr>
    <td class="row2">Terima ulasan dari Jabatan Teknikal serta selesai membuat Laporan Lawatan Tapak <strong>SEHINGGA</strong> mengeluarkan surat Tawaran Penawaran kepada semua KJP</td>
    <td class="row2"><div align="center">
      <strong>$SS2</strong>
      <input name="B2" type="hidden" id="B2" size="5" maxlength="5" value="$SS2" style="text-align:center" onKeyUp="A2()">
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
      <input name="E2T" type="hidden" id="E2T" size="5" maxlength="5" >
    <div align="center" id="E2" >     </div></td>
    <td class="row2">
     <input name="F2T" type="hidden" id="F2T" size="5" maxlength="5" >
    <div align="center" id="F2" >     </div></td>
  </tr>
  
   <tr>
    <td class="row2"><p>Terima respon dari KJP <strong>SEHINGGA</strong> selesai menyediakan Kertas Cadangan serta hantar ke mesyuarat J/K Khas EPU</p></td>
    <td class="row2"><div align="center">
      <strong>$SS3</strong>
      <input name="B3" type="hidden" id="B3" size="5" maxlength="5" value="$SS3" style="text-align:center" onKeyUp="A3()">
    </div></td>
    <td class="row2"><div align="center">
        <strong>$C3</strong>
        <input name="C3" type="hidden" id="C3" size="5" maxlength="5" value="$C3" >
        </div></td>
    <td class="row2"><div align="center">
        <strong>$D3</strong>
        <input name="D3" type="hidden" id="D3" size="5" maxlength="5" value="$D3">
        </div></td>
    <td class="row2">
    <input name="E3T" type="hidden" id="E3T" size="5" maxlength="5" >
    <div align="center" id="E3">     </div></td>
    <td class="row2">
     <input name="F3T" type="hidden" id="F3T" size="5" maxlength="5" >
    <div align="center" id="F3">     </div></td>
  </tr>
  
   <tr>
   <td colspan="6">Menunggu Keputusan Mesyuarat J/K Khas EPU</td>
  </tr>
  
   <tr>
    <td class="row2"><p>Terima keputusan Mesyuarat <strong>SEHINGGA</strong> surat dihantar ke Pemohon</p></td>
    <td class="row2"><div align="center">
      <strong>$SS4</strong>
      <input name="B4" type="hidden" id="B4" size="5" maxlength="5" value="$SS4" style="text-align:center" onKeyUp="A4()">
    </div></td>
    <td class="row2"><div align="center">
        <strong>$C4</strong>
        <input name="C4" type="hidden" id="C4" size="5" maxlength="5" value="$C4">
        </div></td>
    <td class="row2"><div align="center">
        <strong>$D4</strong>
        <input name="D4" type="hidden" id="D4" size="5" maxlength="5" value="$D4">
        </div></td>
    <td class="row2">
    <input name="E4T" type="hidden" id="E4T" size="5" maxlength="5" >
    <div align="center" id="E4">     </div></td>
    <td class="row2">
     <input name="F4T" type="hidden" id="F4T" size="5" maxlength="5" >
    <div align="center" id="F4">     </div></td>
  </tr> 
</table>
</fieldset>

  <input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />


<script type="text/javascript">
 A1();
 A2();
 A3();
 A4();
// A5();
// A6();
// A7();
 

 
if('$open_penetapan' == "yes") 
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
 $jquery("#E1").html("<font color='blue'><strong>"+E1.toFixed(2)+"</strong></font>");
 document.${formName}.E1T.value = E1.toFixed(2);
 
 var F1 = (B1*100)/E1 ; 
 $jquery("#F1").html("<font color='blue'><strong>"+F1.toFixed(2)+"%</strong></font>");
 document.${formName}.F1T.value = F1.toFixed(2);
 }
 else
 {
 $jquery("#E1").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F1").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 document.${formName}.E1T.value = "0.00";
 document.${formName}.F1T.value = "0.00";
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
 document.${formName}.E3T.value = E3.toFixed(2); 
 $jquery("#E3").html("<font color='blue'><strong>"+E3.toFixed(2)+"</strong></font>");
 
 var F3 = (B3*100)/E3 ; 
 document.${formName}.F3T.value = F3.toFixed(2); 
 $jquery("#F3").html("<font color='blue'><strong>"+F3.toFixed(2)+"%</strong></font>");
 }
 else
 {
 $jquery("#E3").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F3").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 document.${formName}.E3T.value = "0.00"; 
 document.${formName}.F3T.value = "0.00"; 
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
 document.${formName}.E4T.value = E4.toFixed(2); 
 $jquery("#E4").html("<font color='blue'><strong>"+E4.toFixed(2)+"</strong></font>");
 
 var F4 = (B4*100)/E4 ; 
 document.${formName}.F4T.value = F4.toFixed(2);
 $jquery("#F4").html("<font color='blue'><strong>"+F4.toFixed(2)+"%</strong></font>");
 }
 else
 {
 document.${formName}.E4T.value = 0.00;
 document.${formName}.F4T.value = 0.00; 
 $jquery("#E4").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F4").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function A5()
 {
 var B5 = document.${formName}.B5.value;
 var C5 = document.${formName}.C5.value;
 var D5 = document.${formName}.D5.value;
 
 if(D5>0 && B5>0)
 {
 var E5 = D5/C5; 
 document.${formName}.E5T.value = E5.toFixed(2);
 $jquery("#E5").html("<font color='blue'><strong>"+E5.toFixed(2)+"</strong></font>");
 
 var F5 = (B5*100)/E5 ; 
 document.${formName}.F5T.value = F5.toFixed(2);
 $jquery("#F5").html("<font color='blue'><strong>"+F5.toFixed(2)+"%</strong></font>");
 }
 else
 {
 document.${formName}.E5T.value = "0.00";
 document.${formName}.F5T.value = "0.00";
 $jquery("#E5").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F5").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function A6()
 {
 var B6 = document.${formName}.B6.value;
 var C6 = document.${formName}.C6.value;
 var D6 = document.${formName}.D6.value;
 
 if(D6>0 && B6>0)
 {
 var E6 = D6/C6; 
 document.${formName}.E6T.value = E6.toFixed(2);
 $jquery("#E6").html("<font color='blue'><strong>"+E6.toFixed(2)+"</strong></font>");
 
 var F6 = (B6*100)/E6 ; 
 document.${formName}.F6T.value = F6.toFixed(2);
 $jquery("#F6").html("<font color='blue'><strong>"+F6.toFixed(2)+"%</strong></font>");
 }
 else
 {
 document.${formName}.E6T.value = "0.00";
 document.${formName}.F6T.value = "0.00";
 $jquery("#E6").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F6").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function A7()
 {
 var B7 = document.${formName}.B7.value;
 var C7 = document.${formName}.C7.value;
 var D7 = document.${formName}.D7.value;
 
 if(D7>0 && B7>0)
 {
 var E7 = D7/C7; 
 document.${formName}.E7T.value = E7.toFixed(2);
 $jquery("#E7").html("<font color='blue'><strong>"+E7.toFixed(2)+"</strong></font>");
 
 var F7 = (B7*100)/E7 ; 
 document.${formName}.F7T.value = F7.toFixed(2);
 $jquery("#F7").html("<font color='blue'><strong>"+F7.toFixed(2)+"%</strong></font>");
 }
 else
 {
 document.${formName}.E7T.value = "0.00";
 document.${formName}.F7T.value = "0.00";
 $jquery("#E7").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F7").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 
 
 function A8()
 {
 var B8 = document.${formName}.B8.value;
 var C8 = document.${formName}.C8.value;
 var D8 = document.${formName}.D8.value;
 
 if(D8>0 && B8>0)
 {
 var E8 = D8/C8; 
 $jquery("#E8").html("<font color='blue'><strong>"+E8.toFixed(2)+"</strong></font>");
 
 var F8 = (B8*100)/E8 ; 
 $jquery("#F8").html("<font color='blue'><strong>"+F8.toFixed(2)+"%</strong></font>");
 }
 else
 {
 $jquery("#E8").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F8").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function A9()
 {
 var B9 = document.${formName}.B9.value;
 var C9 = document.${formName}.C9.value;
 var D9 = document.${formName}.D9.value;
 
 if(D9>0 && B9>0)
 {
 var E9 = D9/C9; 
 $jquery("#E9").html("<font color='blue'><strong>"+E9.toFixed(2)+"</strong></font>");
 
 var F9 = (B9*100)/E9 ; 
 $jquery("#F9").html("<font color='blue'><strong>"+F9.toFixed(2)+"%</strong></font>");
 }
 else
 {
 $jquery("#E9").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F9").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 
 function A10()
 {
 var B10 = document.${formName}.B10.value;
 var C10 = document.${formName}.C10.value;
 var D10 = document.${formName}.D10.value;
 
 if(D10>0 && B10>0)
 {
 var E10 = D10/C10; 
 $jquery("#E10").html("<font color='blue'><strong>"+E10.toFixed(2)+"</strong></font>");
 
 var F10 = (B10*100)/E10 ; 
 $jquery("#F10").html("<font color='blue'><strong>"+F10.toFixed(2)+"%</strong></font>");
 }
 else
 {
 $jquery("#E10").html("<font color='blue'><strong>"+0.00+"</strong></font>");
 $jquery("#F10").html("<font color='blue'><strong>"+0.00+"%</strong></font>");
 }
 
 }
 
 function tetap()
 {
 
 var SX1 = document.${formName}.SX1.value;
 var SX2 = document.${formName}.SX2.value;
 var SX3 = document.${formName}.SX3.value;
 var SX4 = document.${formName}.SX4.value; 
 /*var SX5 = document.${formName}.SX5.value;
 var SX6 = document.${formName}.SX6.value;
 var SX7 = document.${formName}.SX7.value;
 var SX8 = document.${formName}.SX8.value; 
 var SX9 = document.${formName}.SX9.value;
 var SX10 = document.${formName}.SX10.value; 
 var SX11 = document.${formName}.SX11.value;
 var SX12 = document.${formName}.SX12.value; 
 var SX13 = document.${formName}.SX13.value;
 var SX14 = document.${formName}.SX14.value;
 var SX15 = document.${formName}.SX15.value;
 var SX16 = document.${formName}.SX16.value;*/
 
 var SST1 = 0;
 var SST2 = 0;
 var SST3 = 0;
 var SST4 = 0; 
 /*var SST5 = 0;
 var SST6 = 0;
 var SST7 = 0;
 var SST8 = 0; 
 var SST9 = 0;
 var SST10 = 0;
 var SST11 = 0;
 var SST12 = 0; 
 var SST13 = 0;
 var SST14 = 0;
 var SST15 = 0;
 var SST16 = 0;*/
 
 if(SX1!="")
 { 
 SST1 = parseFloat(SX1)+1;  
 } 
 if(SX2!="")
 {
 SST2 = parseFloat(SX2)+1;
 }
 if(SX3!="")
 {
 SST3 = parseFloat(SX3)+1;
 }
 if(SX4!="")
 {
 SST4 = parseFloat(SX4)+1;
 }
 /*
 if(SX5!="")
 {
 SST5 = parseFloat(SX5)+1;
 }
 if(SX6!="")
 {
 SST6 = parseFloat(SX6)+1;
 }
 
 
 if(SX7!="")
 {
 SST7 = parseFloat(SX7)+1;
 }
 if(SX8!="")
 {
 SST8 = parseFloat(SX8)+1;
 }
 
 if(SX9!="")
 {
 SST9 = parseFloat(SX9)+1;
 }
 if(SX10!="")
 {
 SST10 = parseFloat(SX10)+1;
 }
 if(SX11!="")
 {
 SST11 = parseFloat(SX11)+1;
 }
 if(SX12!="")
 {
 SST12 = parseFloat(SX12)+1;
 }
 
 if(SX13!="")
 {
 SST13 = parseInt(SX13)+1;
 }
 if(SX14!="")
 {
 SST14 = parseInt(SX14)+1;
 }
 if(SX15!="")
 {
 SST15 = parseInt(SX15)+1;
 }
 if(SX16!="")
 {
 SST16 = parseInt(SX16)+1;
 }
 
 */
 
 $jquery("#SST1").html("<font color='blue' style='text-align:center' ><strong>"+SST1+"</strong></font>");
 $jquery("#SST2").html("<font color='blue' style='text-align:center'><strong>"+SST2+"</strong></font>");
 $jquery("#SST3").html("<font color='blue' style='text-align:center'><strong>"+SST3+"</strong></font>");
 $jquery("#SST4").html("<font color='blue' style='text-align:center'><strong>"+SST4+"</strong></font>");
 /*$jquery("#SST5").html("<font color='blue' style='text-align:center' ><strong>"+SST5+"</strong></font>");
 $jquery("#SST6").html("<font color='blue' style='text-align:center'><strong>"+SST6+"</strong></font>");
 $jquery("#SST7").html("<font color='blue' style='text-align:center'><strong>"+SST7+"</strong></font>");
 $jquery("#SST8").html("<font color='blue' style='text-align:center'><strong>"+SST8+"</strong></font>");
 $jquery("#SST9").html("<font color='blue' style='text-align:center' ><strong>"+SST9+"</strong></font>");
 $jquery("#SST10").html("<font color='blue' style='text-align:center'><strong>"+SST10+"</strong></font>");
 $jquery("#SST11").html("<font color='blue' style='text-align:center'><strong>"+SST11+"</strong></font>");
 $jquery("#SST12").html("<font color='blue' style='text-align:center'><strong>"+SST12+"</strong></font>");
 $jquery("#SST13").html("<font color='blue' style='text-align:center' ><strong>"+SST13+"</strong></font>");
 $jquery("#SST14").html("<font color='blue' style='text-align:center'><strong>"+SST14+"</strong></font>");
 $jquery("#SST15").html("<font color='blue' style='text-align:center'><strong>"+SST15+"</strong></font>");
 $jquery("#SST16").html("<font color='blue' style='text-align:center'><strong>"+SST16+"</strong></font>");*/
 
 }
 
function simpan()
{

var c = 0;



var date_validation_compare = "";


if(document.${formName}.txdTarikhAkhir.value != null && document.${formName}.txdTarikhMula.value != null)
{   

 var str1 = document.getElementById("txdTarikhAkhir").value;
 var str2 = document.getElementById("txdTarikhMula").value;
     var dt1  = parseInt(str1.substring(0,2),10);
     var mon1 = parseInt(str1.substring(3,5),10)-1;
     var yr1  = parseInt(str1.substring(6,10),10);
     var dt2  = parseInt(str2.substring(0,2),10);
     var mon2 = parseInt(str2.substring(3,5),10)-1;
     var yr2  = parseInt(str2.substring(6,10),10);
     var date1 = new Date(yr1, mon1, dt1);
     var date2 = new Date(yr2, mon2, dt2);
     if(date1 < date2)
     {
	 date_validation_compare = "on";
      /*   alert("Leading KPI hanya mengandungi maklumat permohonan baru yang bermula dari 01/08/2010");
		 document.${formName}.txdTarikhMula.focus();
         return false;*/
     }
	 
}



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
else if(document.${formName}.SS3.value == "" || document.${formName}.SS3.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS3.focus();
return;
}
else if(document.${formName}.SS4.value == "" || document.${formName}.SS4.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS4.focus();
return;
}/*
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
else if(document.${formName}.SS7.value == "" || document.${formName}.SS7.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS7.focus();
return;
}
else if(document.${formName}.SS8.value == "" || document.${formName}.SS8.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS6.focus();
return;
}
else if(document.${formName}.SS9.value == "" || document.${formName}.SS9.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS9.focus();
return;
}
else if(document.${formName}.SS10.value == "" || document.${formName}.SS10.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SS10.focus();
return;
}*/
else if(document.${formName}.SX1.value == "" || document.${formName}.SX1.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX1.focus();
return;
}
else if(document.${formName}.SX2.value == "" || document.${formName}.SX2.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX2.focus();
return;
}
else if(document.${formName}.SX3.value == "" || document.${formName}.SX3.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX3.focus();
return;
}
else if(document.${formName}.SX4.value == "" || document.${formName}.SX4.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX4.focus();
return;
}/*
else if(document.${formName}.SX5.value == "" || document.${formName}.SX5.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX5.focus();
return;
}
else if(document.${formName}.SX6.value == "" || document.${formName}.SX6.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX6.focus();
return;
}
else if(document.${formName}.SX7.value == "" || document.${formName}.SX7.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX7.focus();
return;
}
else if(document.${formName}.SX8.value == "" || document.${formName}.SX8.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX6.focus();
return;
}
else if(document.${formName}.SX9.value == "" || document.${formName}.SX9.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX9.focus();
return;
}
else if(document.${formName}.SX10.value == "" || document.${formName}.SX10.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX10.focus();
return;
}
else if(document.${formName}.SX11.value == "" || document.${formName}.SX11.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX11.focus();
return;
}
else if(document.${formName}.SX12.value == "" || document.${formName}.SX12.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX12.focus();
return;
}
else if(document.${formName}.SX13.value == "" || document.${formName}.SX13.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX13.focus();
return;
}
else if(document.${formName}.SX14.value == "" || document.${formName}.SX14.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX14.focus();
return;
}
else if(document.${formName}.SX15.value == "" || document.${formName}.SX15.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX15.focus();
return;
}
else if(document.${formName}.SX16.value == "" || document.${formName}.SX16.value == "0" ){
alert("Sila pastikan sasaran aktiviti melebihi dari sehari");
document.${formName}.SX16.focus();
return;
}*/
else if(document.${formName}.socUrusan.value == "")
{
alert("Sila pilih Jenis Urusan");
document.${formName}.socUrusan.focus();
return;
}
else if(document.${formName}.socSuburusan.value == "")
{
alert("Sila pilih jenis Jenis Suburusan");
document.${formName}.socSuburusan.focus();
return;
}
else if(document.${formName}.txdTarikhMula.value == "")
{
alert("Sila masukkan tarikh mula");
document.${formName}.txdTarikhMula.focus();
return;
}
else if(document.${formName}.txdTarikhAkhir.value == "")
{
alert("Sila masukkan tarikh akhir");
document.${formName}.txdTarikhAkhir.focus();
return;
}

else if(date_validation_compare== "on")
{
 
         alert("Pastikan tarikh mula tidak melebihi tarikh akhir");
		 document.${formName}.txdTarikhAkhir.focus();
         return false;
		 
}

else if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}

else
{
    input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.kpi.Leading_KPI_PHP";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.submit();
	}
}
 
 
}
 
 
 
 
 
 
 
 function batal()
 {
    document.${formName}.command.value = "KPI";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.kpi.Leading_KPI_PHP";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.submit();
 }
 
 function batal1()
 {
    document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.kpi.Leading_KPI_PHP";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.submit();
 }
 
 function kemaskini()
 {
    document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.kpi.Leading_KPI_PHP";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.submit();
 }
 
  function setP()
 {
    if(document.${formName}.open_penetapan.value=="yes"){
	document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "close_penetapan";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.kpi.Leading_KPI_PHP";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.open_penetapan.value = "";
	document.${formName}.submit();
	}
	else if(document.${formName}.open_penetapan.value==""){
	document.${formName}.command.value = "KPI";
	document.${formName}.sub_command.value = "open_penetapan";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.kpi.Leading_KPI_PHP";
    document.${formName}.location.value = "table_sasaran";
	document.${formName}.point.value = "table_sasaran";	
	document.${formName}.open_penetapan.value = "yes";
	document.${formName}.submit();
	}
   
 }
 

 </script>

