<style type="text/css">
<!--
.style1 {
	color: #000000;
	font-weight: bold;
}
.style2 {color: #000000}
-->
</style>



  #set($SS1 = 0)
  #set($SS2 = 0)
  #set($SS3 = 0)
  #set($SS4 = 0)
  #set($SS5 = "")
  #set($SS6 = "")
  #set($SS7 = "")
  #set($SS8 = "") 
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



#set($A1 = 0)
#set($A2 = 0)
#set($A3 = 0)
#set($A4 = 0)
#set($A5 = 0)
#set($A6 = 0)
#set($A7 = 0)
#set($A8 = 0)
#set($A9 = 0)
#set($A10 = 0)
#set($A11 = 0)

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
#set($B11 = 0)

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
#set($C11 = 0)

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
#set($D11 = 0)

#set($E1 = 0)
#set($E2 = 0)
#set($E3 = 0)
#set($E4 = 0)
#set($E5 = 0)
#set($E6 = 0)
#set($E7 = 0)
#set($E8 = 0)
#set($E9 = 0)
#set($E10 = 0)
#set($E11 = 0)

#set($F1 = 0)
#set($F2 = 0)
#set($F3 = 0)
#set($F4 = 0)
#set($F5 = 0)
#set($F6 = 0)
#set($F7 = 0)
#set($F8 = 0)
#set($F9 = 0)
#set($F10 = 0)
#set($F11 = 0)

#set($G1 = 0)
#set($G2 = 0)
#set($G3 = 0)
#set($G4 = 0)
#set($G5 = 0)
#set($G6 = 0)
#set($G7 = 0)
#set($G8 = 0)
#set($G9 = 0)
#set($G10 = 0)
#set($G11 = 0)

#set($H1 = 0)
#set($H2 = 0)
#set($H3 = 0)
#set($H4 = 0)
#set($H5 = 0)
#set($H6 = 0)
#set($H7 = 0)
#set($H8 = 0)
#set($H9 = 0)
#set($H10 = 0)
#set($H11 = 0)

#set($I1 = 0)
#set($I2 = 0)
#set($I3 = 0)
#set($I4 = 0)
#set($I5 = 0)
#set($I6 = 0)
#set($I7 = 0)
#set($I8 = 0)
#set($I9 = 0)
#set($I10 = 0)
#set($I11 = 0)

#set($J1 = 0)
#set($J2 = 0)
#set($J3 = 0)
#set($J4 = 0)
#set($J5 = 0)
#set($J6 = 0)
#set($J7 = 0)
#set($J8 = 0)
#set($J9 = 0)
#set($J10 = 0)
#set($J11 = 0)

#set($X1 = 40)
#set($X2 = 40)
#set($X3 = 40)
#set($X4 = 40)
#set($X5 = 40)
#set($X6 = 40)
#set($X7 = 40)
#set($X8 = 40)
#set($X9 = 40)
#set($X10 = 40)
#set($X11 = 40)


#set($K1 = 0)
#set($K2 = 0)
#set($K3 = 0)
#set($K4 = 0)
#set($K5 = 0)
#set($K6 = 0)
#set($K7 = 0)
#set($K8 = 0)
#set($K9 = 0)
#set($K10 = 0)
#set($K11 = 0)

#set($L1 = 0)
#set($L2 = 0)
#set($L3 = 0)
#set($L4 = 0)
#set($L5 = 0)
#set($L6 = 0)
#set($L7 = 0)
#set($L8 = 0)
#set($L9 = 0)
#set($L10 = 0)
#set($L11 = 0)


#set($M1 = 0)
#set($M2 = 0)
#set($M3 = 0)
#set($M4 = 0)
#set($M5 = 0)
#set($M6 = 0)
#set($M7 = 0)
#set($M8 = 0)
#set($M9 = 0)
#set($M10 = 0)
#set($M11 = 0)

#set($N1 = 0)
#set($N2 = 0)
#set($N3 = 0)
#set($N4 = 0)
#set($N5 = 0)
#set($N6 = 0)
#set($N7 = 0)
#set($N8 = 0)
#set($N9 = 0)
#set($N10 = 0)
#set($N11 = 0)

#set($O1 = 0)
#set($O2 = 0)
#set($O3 = 0)
#set($O4 = 0)
#set($O5 = 0)
#set($O6 = 0)
#set($O7 = 0)
#set($O8 = 0)
#set($O9 = 0)
#set($O10 = 0)
#set($O11 = 0)

#set($P1 = 0)
#set($P2 = 0)
#set($P3 = 0)
#set($P4 = 0)
#set($P5 = 0)
#set($P6 = 0)
#set($P7 = 0)
#set($P8 = 0)
#set($P9 = 0)
#set($P10 = 0)
#set($P11 = 0)


#set($Q1 = 0)
#set($Q2 = 0)
#set($Q3 = 0)
#set($Q4 = 0)
#set($Q5 = 0)
#set($Q6 = 0)
#set($Q7 = 0)
#set($Q8 = 0)
#set($Q9 = 0)
#set($Q10 = 0)
#set($Q11 = 0)

#set($R1 = 0)
#set($R2 = 0)
#set($R3 = 0)
#set($R4 = 0)
#set($R5 = 0)
#set($R6 = 0)
#set($R7 = 0)
#set($R8 = 0)
#set($R9 = 0)
#set($R10 = 0)
#set($R11 = 0)

#set($S1 = 0)
#set($S2 = 0)
#set($S3 = 0)
#set($S4 = 0)
#set($S5 = 0)
#set($S6 = 0)
#set($S7 = 0)
#set($S8 = 0)
#set($S9 = 0)
#set($S10 = 0)
#set($S11 = 0)

#set($T1 = 0)
#set($T2 = 0)
#set($T3 = 0)
#set($T4 = 0)
#set($T5 = 0)
#set($T6 = 0)
#set($T7 = 0)
#set($T8 = 0)
#set($T9 = 0)
#set($T10 = 0)
#set($T11 = 0)

#set($U1 = 0)
#set($U2 = 0)
#set($U3 = 0)
#set($U4 = 0)
#set($U5 = 0)
#set($U6 = 0)
#set($U7 = 0)
#set($U8 = 0)
#set($U9 = 0)
#set($U10 = 0)
#set($U11 = 0)

#set($V1 = 0)
#set($V2 = 0)
#set($V3 = 0)
#set($V4 = 0)
#set($V5 = 0)
#set($V6 = 0)
#set($V7 = 0)
#set($V8 = 0)
#set($V9 = 0)
#set($V10 = 0)
#set($V11 = 0)

#set($W1 = 0)
#set($W2 = 0)
#set($W3 = 0)
#set($W4 = 0)
#set($W5 = 0)
#set($W6 = 0)
#set($W7 = 0)
#set($W8 = 0)
#set($W9 = 0)
#set($W10 = 0)
#set($W11 = 0)

#set($AB1 = 0)
#set($AB2 = 0)
#set($AB3 = 0)
#set($AB4 = 0)
#set($AB5 = 0)
#set($AB6 = 0)
#set($AB7 = 0)
#set($AB8 = 0)
#set($AB9 = 0)
#set($AB10 = 0)
#set($AB11 = 0)




#foreach($list in $list_KPI_Seksyen8_ByNegeri)

#if($list.ID_NEGERI == "9")
#set($A1 = $list.DITERIMA)
#set($B1 = $list.LOT_DITERIMA)
#set($I1 = $list.DISELESAI)
#set($J1 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "2")
#set($A2 = $list.DITERIMA)
#set($B2 = $list.LOT_DITERIMA)
#set($I2 = $list.DISELESAI)
#set($J2 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "7")
#set($A3 = $list.DITERIMA)
#set($B3 = $list.LOT_DITERIMA)
#set($I3 = $list.DISELESAI)
#set($J3 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "8")
#set($A4 = $list.DITERIMA)
#set($B4 = $list.LOT_DITERIMA)
#set($I4 = $list.DISELESAI)
#set($J4 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "3")
#set($A5 = $list.DITERIMA)
#set($B5 = $list.LOT_DITERIMA)
#set($I5 = $list.DISELESAI)
#set($J5 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "11")
#set($A6 = $list.DITERIMA)
#set($B6 = $list.LOT_DITERIMA)
#set($I6 = $list.DISELESAI)
#set($J6 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "6")
#set($A7 = $list.DITERIMA)
#set($B7 = $list.LOT_DITERIMA)
#set($I7 = $list.DISELESAI)
#set($J7 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "10")
#set($A8 = $list.DITERIMA)
#set($B8 = $list.LOT_DITERIMA)
#set($I8 = $list.DISELESAI)
#set($J8 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "14")
#set($A9 = $list.DITERIMA)
#set($B9 = $list.LOT_DITERIMA)
#set($I9 = $list.DISELESAI)
#set($J9 = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "5")
#set($A10 = $list.DITERIMA)
#set($B10 = $list.LOT_DITERIMA)
#set($I10 = $list.DISELESAI)
#set($J1O = $list.LOT_DISELESAI)
#elseif($list.ID_NEGERI == "4")
#set($A11 = $list.DITERIMA)
#set($B11 = $list.LOT_DITERIMA)
#set($I11 = $list.DISELESAI)
#set($J11 = $list.LOT_DISELESAI)
#end

#end









#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "9")
#set($L1 = $L1 + $list.PURATA)
#set($K1 = $K1 + $list.PURATA)
#end
#end

#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "2")
#set($L2 = $L2 + $list.PURATA)
#set($K2 = $K2 + $list.PURATA)
#end
#end

#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "7")
#set($L3 = $L3 + $list.PURATA)
#set($K3 = $K3 + $list.PURATA)
#end
#end

#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "8")
#set($L4 = $L4 + $list.PURATA)
#set($K4 = $K4 + $list.PURATA)
#end
#end

#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "3")
#set($L5 = $L5 + $list.PURATA)
#set($K5 = $K5 + $list.PURATA)
#end
#end

#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "11")
#set($L6 = $L6 + $list.PURATA)
#set($K6 = $K6 + $list.PURATA)
#end
#end

#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "6")
#set($L7 = $L7 + $list.PURATA)
#set($K7 = $K7 + $list.PURATA)
#end
#end



#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "10")
#set($L8 = $L8 + $list.PURATA)
#set($K8 = $K8 + $list.PURATA)
#end
#end


#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "14")
#set($L9 = $L9 + $list.PURATA)
#set($K9 = $K9 + $list.PURATA)
#end
#end

#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "5")
#set($L10 = $L10 + $list.PURATA)
#set($K10 = $K10 + $list.PURATA)
#end
#end

#foreach($list in $list_KPI_Seksyen8_ByNegeri)
#if($list.ID_NEGERI == "4")
#set($L11 = $L11 + $list.PURATA)
#set($K11 = $K11 + $list.PURATA)
#end
#end










<table width="100%" id="table_kpi">
      <tr>
        <td valign="top" width="100%" ><div align="center"><strong>URUSAN 03.05 : RINGKASAN DI PERINGKAT NEGERI  PENGAMBILAN TANAH SEKSYEN 8</strong></div></td>
      </tr>
      <tr>
        <td valign="top" width="100%" ><div align="center"><strong>TARIKH MULA : $!txdTarikhMula</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>TARIKH AKHIR : $!txdTarikhAkhir</strong></div></td>
      </tr>
    </table>
<fieldset>
<legend>MAKLUMAT KPI</legend>

<table width="100%" class="table_headerkpi"  >
<tr >
    <td valign="top" width="15%" rowspan="3" class="row2" ><strong>NEGERI</strong></td>
    <td valign="top" width="10%" rowspan="2" colspan="2" class="row2"><div align="center"><strong>Jumlah bil. Permohonan diterima &amp; bil. Lot terlibat</strong></div></td>
   
    <td valign="top" colspan="6" rowspan="3"  class="row2" width="45%"><div align="center"><strong>Menunggu Sokongan Agensi Luar</strong></div></td>
       <td valign="top"  colspan="5" class="row2" width="30%"><div align="center"><strong>Jumlah Permohonan Telah Di Selesaikan</strong></div></td>
  </tr>
<tr >
      
    
    
    <td valign="top"  width="10%" colspan="2" class="row2"><div align="center"><strong>Bilangan</strong></div></td>
    
    <td valign="top"  width="6%" rowspan="2" class="row2"><div align="center"><strong>Sasaran </strong></div></td>   
    <td valign="top"  width="8%" rowspan="2" class="row2"><div align="center"><strong>Kecekapan dalaman Unit JKPTG (%)</strong></div></td>
    <td valign="top"  width="6%" rowspan="2" class="row2"><div align="center"><strong>Purata Kitaran Masa (hari)</strong></div></td>
  </tr>
  <tr >
    
    <td valign="top" width="5%"  class="row2"><div align="center"><strong>#Per</strong></div></td>
    <td valign="top"  width="5%" class="row2"><div align="center"><strong>#Lot</strong></div></td>
    
    
    <td valign="top"  class="row2" width="5%"><div align="center"><strong>#Per</strong></div></td>
    <td valign="top" class="row2" width="5%"><div align="center"><strong>#Lot</strong></div></td>
  </tr>
  
  <tr class="row1">
    <td valign="top" ><span class="style1">PERLIS</span></td>
    <td valign="top"  >
    <div align="center" class="style2">
        <strong>$A1</strong>
        <input name="A1" type="hidden" id="A1" size="5" maxlength="5" value="$A1" >
      </div>    </td>
    <td valign="top"  >
     <div align="center" class="style2">
        <strong>$B1</strong>
        <input name="B1" type="hidden" id="B1" size="5" maxlength="5" value="$B1" >
      </div>    </td>
      
    <td valign="top" colspan="6">
   
     
 #set($Perlis1 = 0)
  #set($Perlis2 = 0)
  #set($Perlis3 = 0)
  #set($Perlis4 = 0)
  #set($Perlis5 = 0)
  #set($Perlis6 = 0)
  #set($Perlis7 = 0)
  #set($Perlis8 = 0) 
  #set($Perlis9 = 0)
  #set($Perlis10 = 0)
  #set($Perlis11 = 0)
  #set($Perlis12 = 0) 
  #set($Perlis13 = 0)
  #set($Perlis14 = 0)
  #set($Perlis15 = 0)
  #set($Perlis16 = 0)
  #set($Perlis17 = 0)
  #set($Perlis18 = 0)
  #set($Perlis19 = 0)
  #set($Perlis20 = 0) 
  #set($Perlis21 = 0)
  #set($Perlis22 = 0)
  #set($Perlis23 = 0)
  #set($Perlis24 = 0)
  #set($Perlis25 = 0)
  #set($Perlis26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "9")
  #set($Perlis1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Perlis2 = $list.F11)
  #set($Perlis4 = $list.F12)
  #set($Perlis5 = $list.F13)
  #set($Perlis6 = $list.F14)
  #set($Perlis7 = $list.F15) 
  #set($Perlis8 = $list.F16) 
  #set($Perlis9 = $list.F17) 
  #set($Perlis10 = $list.F18) 
  #set($Perlis11 = $list.F19)  
  #set($Perlis12 = $list.F20)
  #set($Perlis13 = $list.F21)
  #set($Perlis14 = $list.F22)
  
   
  #set($Perlis15 = $list.F11+1)
  #set($Perlis16 = $list.F12+1)
  #set($Perlis17 = $list.F13+1)
  #set($Perlis18 = $list.F14+1)
  #set($Perlis19 = $list.F15+1) 
  #set($Perlis20 = $list.F16+1) 
  #set($Perlis21 = $list.F17+1) 
  #set($Perlis22 = $list.F18+1) 
  #set($Perlis23 = $list.F19+1)  
  #set($Perlis24 = $list.F20+1)
  #set($Perlis25 = $list.F21+1)
  #set($Perlis26 = $list.F22+1)
    
  #end 
  #end


  
#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Perlis2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "9")
#set($C1 = $C1 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Perlis4 && $list.HARI_TUNGGU_JPPH >= $Perlis15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "9")
#set($D1 = $D1 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Perlis16 && $list.ID_NEGERI == "9")
#set($E1 = $E1 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Perlis5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "9")
#set($F1 = $F1 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Perlis6 && $list.HARI_TUNGGU_JT >= $Perlis17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "9")
#set($G1 = $G1 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Perlis18 && $list.ID_NEGERI == "9")
#set($H1 = $H1 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Perlis7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "9")
#set($M1 = $M1 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Perlis8 && $list.HARI_TUNGGU_PBN >= $Perlis19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "9")
#set($N1 = $N1 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Perlis20 && $list.ID_NEGERI == "9")
#set($O1 = $O1 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Perlis9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "9")
#set($P1 = $P1 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Perlis10 && $list.HARI_TUNGGU_DHDK >= $Perlis21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "9")
#set($Q1 = $Q1 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Perlis22 && $list.ID_NEGERI == "9")
#set($R1 = $R1 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Perlis11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "9")
#set($S1 = $S1 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Perlis12 && $list.HARI_TUNGGU_BAYARAN >= $Perlis23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "9")
#set($T1 = $T1 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Perlis24 && $list.ID_NEGERI == "9")
#set($U1 = $U1 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Perlis13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "9")
#set($V1 = $V1 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Perlis14 && $list.HARI_TUNGGU_BORANGK >= $Perlis25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "9")
#set($W1 = $W1 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Perlis26 && $list.ID_NEGERI == "9")
#set($AB1 = $AB1 + 1)
#end
#end



<table width="100%">
  <tr>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Perlis2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C1</strong>
        <input name="C1" type="hidden" id="C1" size="5" maxlength="5" value="$C1" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Perlis15 - $Perlis4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D1</strong>
        <input name="D1" type="hidden" id="D1" size="5" maxlength="5" value="$D1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Perlis16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E1</strong>
        <input name="E1" type="hidden" id="E1" size="5" maxlength="5" value="$E1" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perlis9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P1</strong>
        <input name="P1" type="hidden" id="P1" size="5" maxlength="5" value="$P1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perlis21 - $Perlis10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q1</strong>
        <input name="Q1" type="hidden" id="Q1" size="5" maxlength="5" value="$Q1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perlis22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R1</strong>
        <input name="R1" type="hidden" id="R1" size="5" maxlength="5" value="$R1" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perlis5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F1</strong>
        <input name="F1" type="hidden" id="F1" size="5" maxlength="5" value="$F1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perlis17 - $Perlis6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G1</strong>
        <input name="G1" type="hidden" id="G1" size="5" maxlength="5" value="$G1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perlis18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H1</strong>
        <input name="H1" type="hidden" id="H1" size="5" maxlength="5" value="$H1" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perlis11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S1</strong>
        <input name="S1" type="hidden" id="S1" size="5" maxlength="5" value="$S1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perlis23 - $Perlis12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T1</strong>
        <input name="T1" type="hidden" id="T1" size="5" maxlength="5" value="$T1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perlis24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U1</strong>
        <input name="U1" type="hidden" id="U1" size="5" maxlength="5" value="$U1" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perlis7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M1</strong>
        <input name="M1" type="hidden" id="M1" size="5" maxlength="5" value="$M1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perlis19 - $Perlis8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N1</strong>
        <input name="N1" type="hidden" id="N1" size="5" maxlength="5" value="$N1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perlis20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O1</strong>
        <input name="O1" type="hidden" id="O1" size="5" maxlength="5" value="$O1" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perlis13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V1</strong>
        <input name="V1" type="hidden" id="V1" size="5" maxlength="5" value="$V1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perlis25 - $Perlis14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W1</strong>
        <input name="W1" type="hidden" id="W1" size="5" maxlength="5" value="$W1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perlis26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB1</strong>
        <input name="AB1" type="hidden" id="AB1" size="5" maxlength="5" value="$AB1" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
</table>

   
   </td>
  
    <td valign="top"  ><div align="center" class="style2">
        <strong>$I1</strong>
        <input name="I1" type="hidden" id="I1" size="5" maxlength="5" value="$I1" >
      </div> </td>
    <td valign="top"  ><div align="center" class="style2">
        <strong>$J1</strong>
        <input name="J1" type="hidden" id="J1" size="5" maxlength="5" value="$J1" >
      </div></td>
       <td valign="top"  ><div align="center" class="style2">
         <strong>$Perlis1</strong>
        <input name="X1" type="hidden" id="X1" size="5" maxlength="5" value="$Perlis1"  style="text-align:center">
      </div> </td>
     <td valign="top"  ><div align="center" class="style2">
    <div align="center" id="Y1" >     </div>
        <input name="K1" type="hidden" id="K1" size="5" maxlength="5" value="$K1" >
      </div> </td>
    <td valign="top"  ><div align="center" class="style2">
        <strong>$L1</strong>
        <input name="L1" type="hidden" id="L1" size="5" maxlength="5" value="$L1" >
      </div></td>
  </tr>
  
  <tr class="row2">
    <td valign="top"  ><span class="style1">KEDAH</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A2</strong>
        <input name="A2" type="hidden" id="A2" size="5" maxlength="5" value="$A2" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B2</strong>
        <input name="B2" type="hidden" id="B2" size="5" maxlength="5" value="$B2" >
        </div></td>
   <td valign="top" colspan="6">
   
 
    #set($Kedah1 = 0)
  #set($Kedah2 = 0)
  #set($Kedah3 = 0)
  #set($Kedah4 = 0)
  #set($Kedah5 = 0)
  #set($Kedah6 = 0)
  #set($Kedah7 = 0)
  #set($Kedah8 = 0) 
  #set($Kedah9 = 0)
  #set($Kedah10 = 0)
  #set($Kedah11 = 0)
  #set($Kedah12 = 0) 
  #set($Kedah13 = 0)
  #set($Kedah14 = 0)
  #set($Kedah15 = 0)
  #set($Kedah16 = 0)
  #set($Kedah17 = 0)
  #set($Kedah18 = 0)
  #set($Kedah19 = 0)
  #set($Kedah20 = 0) 
  #set($Kedah21 = 0)
  #set($Kedah22 = 0)
  #set($Kedah23 = 0)
  #set($Kedah24 = 0)
  #set($Kedah25 = 0)
  #set($Kedah26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "2")
  #set($Kedah1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Kedah2 = $list.F11)
  #set($Kedah4 = $list.F12)
  #set($Kedah5 = $list.F13)
  #set($Kedah6 = $list.F14)
  #set($Kedah7 = $list.F15) 
  #set($Kedah8 = $list.F16) 
  #set($Kedah9 = $list.F17) 
  #set($Kedah10 = $list.F18) 
  #set($Kedah11 = $list.F19)  
  #set($Kedah12 = $list.F20)
  #set($Kedah13 = $list.F21)
  #set($Kedah14 = $list.F22)
  
   
  #set($Kedah15 = $list.F11+1)
  #set($Kedah16 = $list.F12+1)
  #set($Kedah17 = $list.F13+1)
  #set($Kedah18 = $list.F14+1)
  #set($Kedah19 = $list.F15+1) 
  #set($Kedah20 = $list.F16+1) 
  #set($Kedah21 = $list.F17+1) 
  #set($Kedah22 = $list.F18+1) 
  #set($Kedah23 = $list.F19+1)  
  #set($Kedah24 = $list.F20+1)
  #set($Kedah25 = $list.F21+1)
  #set($Kedah26 = $list.F22+1)
    
  #end 
  #end




#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Kedah2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "2")
#set($C2 = $C2 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Kedah4 && $list.HARI_TUNGGU_JPPH >= $Kedah15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "2")
#set($D2 = $D2 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Kedah16 && $list.ID_NEGERI == "2")
#set($E2 = $E2 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Kedah5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "2")
#set($F2 = $F2 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Kedah6 && $list.HARI_TUNGGU_JT >= $Kedah17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "2")
#set($G2 = $G2 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Kedah18 && $list.ID_NEGERI == "2")
#set($H2 = $H2 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Kedah7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "2")
#set($M2 = $M2 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Kedah8 && $list.HARI_TUNGGU_PBN >= $Kedah19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "2")
#set($N2 = $N2 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Kedah20 && $list.ID_NEGERI == "2")
#set($O2 = $O2 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Kedah9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "2")
#set($P2 = $P2 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Kedah10 && $list.HARI_TUNGGU_DHDK >= $Kedah21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "2")
#set($Q2 = $Q2 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Kedah22 && $list.ID_NEGERI == "2")
#set($R2 = $R2 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Kedah11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "2")
#set($S2 = $S2 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Kedah12 && $list.HARI_TUNGGU_BAYARAN >= $Kedah23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "2")
#set($T2 = $T2 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Kedah24 && $list.ID_NEGERI == "2")
#set($U2 = $U2 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Kedah13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "2")
#set($V2 = $V2 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Kedah14 && $list.HARI_TUNGGU_BORANGK >= $Kedah25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "2")
#set($W2 = $W2 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Kedah26 && $list.ID_NEGERI == "2")
#set($AB2 = $AB2 + 1)
#end
#end


<table width="100%">
  <tr>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Kedah2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C2</strong>
        <input name="C2" type="hidden" id="C2" size="5" maxlength="5" value="$C2" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Kedah15 - $Kedah4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D2</strong>
        <input name="D2" type="hidden" id="D2" size="5" maxlength="5" value="$D2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Kedah16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E2</strong>
        <input name="E2" type="hidden" id="E2" size="5" maxlength="5" value="$E2" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kedah9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P2</strong>
        <input name="P2" type="hidden" id="P2" size="5" maxlength="5" value="$P2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kedah21 - $Kedah10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q2</strong>
        <input name="Q2" type="hidden" id="Q2" size="5" maxlength="5" value="$Q2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kedah22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R2</strong>
        <input name="R2" type="hidden" id="R2" size="5" maxlength="5" value="$R2" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kedah5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F2</strong>
        <input name="F2" type="hidden" id="F2" size="5" maxlength="5" value="$F2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kedah17 - $Kedah6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G2</strong>
        <input name="G2" type="hidden" id="G2" size="5" maxlength="5" value="$G2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kedah18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H2</strong>
        <input name="H2" type="hidden" id="H2" size="5" maxlength="5" value="$H2" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kedah11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S2</strong>
        <input name="S2" type="hidden" id="S2" size="5" maxlength="5" value="$S2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kedah23 - $Kedah12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T2</strong>
        <input name="T2" type="hidden" id="T2" size="5" maxlength="5" value="$T2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kedah24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U2</strong>
        <input name="U2" type="hidden" id="U2" size="5" maxlength="5" value="$U2" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kedah7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M2</strong>
        <input name="M2" type="hidden" id="M2" size="5" maxlength="5" value="$M2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kedah19 - $Kedah8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N2</strong>
        <input name="N2" type="hidden" id="N2" size="5" maxlength="5" value="$N2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kedah20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O2</strong>
        <input name="O2" type="hidden" id="O2" size="5" maxlength="5" value="$O2" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kedah13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V2</strong>
        <input name="V2" type="hidden" id="V2" size="5" maxlength="5" value="$V2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kedah25 - $Kedah14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W2</strong>
        <input name="W2" type="hidden" id="W2" size="5" maxlength="5" value="$W2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kedah26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB2</strong>
        <input name="AB2" type="hidden" id="AB2" size="5" maxlength="5" value="$AB2" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
</table>
 
 
   </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$I2</strong>
        <input name="I2" type="hidden" id="I2" size="5" maxlength="5" value="$I2" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J2</strong>
        <input name="J2" type="hidden" id="J2" size="5" maxlength="5" value="$J2" >
      </div></td>
       <td valign="top"><div align="center" class="style2">
         <strong>$Kedah1</strong>
        <input name="X2" type="hidden" id="X2" size="5" maxlength="5" value="$Kedah1" style="text-align:center" >
      </div> </td>
   <td valign="top"><div align="center" class="style2">
        <div align="center" id="Y2" >     </div>
        <input name="K2" type="hidden" id="K2" size="5" maxlength="5" value="$K2" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L2</strong>
        <input name="L2" type="hidden" id="L2" size="5" maxlength="5" value="$L2" >
      </div></td>
  </tr>
  <tr class="row1">
    <td valign="top"><span class="style1">PULAU PINANG</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A3</strong>
        <input name="A3" type="hidden" id="A3" size="5" maxlength="5" value="$A3" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B3</strong>
        <input name="B3" type="hidden" id="B3" size="5" maxlength="5" value="$B3" >
        </div></td>
    <td valign="top" colspan="6">
    
      
  #set($Penang1 = 0)
  #set($Penang2 = 0)
  #set($Penang3 = 0)
  #set($Penang4 = 0)
  #set($Penang5 = 0)
  #set($Penang6 = 0)
  #set($Penang7 = 0)
  #set($Penang8 = 0) 
  #set($Penang9 = 0)
  #set($Penang10 = 0)
  #set($Penang11 = 0)
  #set($Penang12 = 0) 
  #set($Penang13 = 0)
  #set($Penang14 = 0)
  #set($Penang15 = 0)
  #set($Penang16 = 0)
  #set($Penang17 = 0)
  #set($Penang18 = 0)
  #set($Penang19 = 0)
  #set($Penang20 = 0) 
  #set($Penang21 = 0)
  #set($Penang22 = 0)
  #set($Penang23 = 0)
  #set($Penang24 = 0)
  #set($Penang25 = 0)
  #set($Penang26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "7")
  #set($Penang1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Penang2 = $list.F11)
  #set($Penang4 = $list.F12)
  #set($Penang5 = $list.F13)
  #set($Penang6 = $list.F14)
  #set($Penang7 = $list.F15) 
  #set($Penang8 = $list.F16) 
  #set($Penang9 = $list.F17) 
  #set($Penang10 = $list.F18) 
  #set($Penang11 = $list.F19)  
  #set($Penang12 = $list.F20)
  #set($Penang13 = $list.F21)
  #set($Penang14 = $list.F22)
  
   
  #set($Penang15 = $list.F11+1)
  #set($Penang16 = $list.F12+1)
  #set($Penang17 = $list.F13+1)
  #set($Penang18 = $list.F14+1)
  #set($Penang19 = $list.F15+1) 
  #set($Penang20 = $list.F16+1) 
  #set($Penang21 = $list.F17+1) 
  #set($Penang22 = $list.F18+1) 
  #set($Penang23 = $list.F19+1)  
  #set($Penang24 = $list.F20+1)
  #set($Penang25 = $list.F21+1)
  #set($Penang26 = $list.F22+1)
    
  #end 
  #end



#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Penang2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "7")
#set($C3 = $C3 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Penang4 && $list.HARI_TUNGGU_JPPH >= $Penang15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "7")
#set($D3 = $D3 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Penang16 && $list.ID_NEGERI == "7")
#set($E3 = $E3 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Penang5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "7")
#set($F3 = $F3 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Penang6 && $list.HARI_TUNGGU_JT >= $Penang17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "7")
#set($G3 = $G3 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Penang18 && $list.ID_NEGERI == "7")
#set($H3 = $H3 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Penang7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "7")
#set($M3 = $M3 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Penang8 && $list.HARI_TUNGGU_PBN >= $Penang19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "7")
#set($N3 = $N3 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Penang20 && $list.ID_NEGERI == "7")
#set($O3 = $O3 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Penang9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "7")
#set($P3 = $P3 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Penang10 && $list.HARI_TUNGGU_DHDK >= $Penang21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "7")
#set($Q3 = $Q3 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Penang22 && $list.ID_NEGERI == "7")
#set($R3 = $R3 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Penang11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "7")
#set($S3 = $S3 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Penang12 && $list.HARI_TUNGGU_BAYARAN >= $Penang23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "7")
#set($T3 = $T3 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Penang24 && $list.ID_NEGERI == "7")
#set($U3 = $U3 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Penang13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "7")
#set($V3 = $V3 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Penang14 && $list.HARI_TUNGGU_BORANGK >= $Penang25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "7")
#set($W3 = $W3 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Penang26 && $list.ID_NEGERI == "7")
#set($AB3 = $AB3 + 1)
#end
#end


    
  

<table width="100%">
  <tr>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Penang2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C3</strong>
        <input name="C3" type="hidden" id="C3" size="5" maxlength="5" value="$C3" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Penang15 - $Penang4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D3</strong>
        <input name="D3" type="hidden" id="D3" size="5" maxlength="5" value="$D3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Penang16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E3</strong>
        <input name="E3" type="hidden" id="E3" size="5" maxlength="5" value="$E3" >
      </div>  </td>
          </tr>
        </table>
    
    </td>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Penang9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P3</strong>
        <input name="P3" type="hidden" id="P3" size="5" maxlength="5" value="$P3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Penang21 - $Penang10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q3</strong>
        <input name="Q3" type="hidden" id="Q3" size="5" maxlength="5" value="$Q3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Penang22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R3</strong>
        <input name="R3" type="hidden" id="R3" size="5" maxlength="5" value="$R3" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Penang5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F3</strong>
        <input name="F3" type="hidden" id="F3" size="5" maxlength="5" value="$F3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Penang17 - $Penang6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G3</strong>
        <input name="G3" type="hidden" id="G3" size="5" maxlength="5" value="$G3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Penang18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H3</strong>
        <input name="H3" type="hidden" id="H3" size="5" maxlength="5" value="$H3" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Penang11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S3</strong>
        <input name="S3" type="hidden" id="S3" size="5" maxlength="5" value="$S3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Penang23 - $Penang12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T3</strong>
        <input name="T3" type="hidden" id="T3" size="5" maxlength="5" value="$T3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Penang24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U3</strong>
        <input name="U3" type="hidden" id="U3" size="5" maxlength="5" value="$U3" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Penang7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M3</strong>
        <input name="M3" type="hidden" id="M3" size="5" maxlength="5" value="$M3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Penang19 - $Penang8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N3</strong>
        <input name="N3" type="hidden" id="N3" size="5" maxlength="5" value="$N3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Penang20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O3</strong>
        <input name="O3" type="hidden" id="O3" size="5" maxlength="5" value="$O3" >
      </div>  </td>
          </tr>
        </table>
    
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>

            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Penang13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V3</strong>
        <input name="V3" type="hidden" id="V3" size="5" maxlength="5" value="$V3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Penang25 - $Penang14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W3</strong>
        <input name="W3" type="hidden" id="W3" size="5" maxlength="5" value="$W3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Penang26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB3</strong>
        <input name="AB3" type="hidden" id="AB3" size="5" maxlength="5" value="$AB3" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
</table>
    
   </td>
     <td valign="top"><div align="center" class="style2">
        <strong>$I3</strong>
        <input name="I3" type="hidden" id="I3" size="5" maxlength="5" value="$I3" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J3</strong>
        <input name="J3" type="hidden" id="J3" size="5" maxlength="5" value="$J3" >
      </div></td>
       <td valign="top"><div align="center" class="style2">
         <strong>$Penang1</strong>
        <input name="X3" type="hidden" id="X3" size="5" maxlength="5" value="$Penang1" style="text-align:center" >
      </div> </td>
      
    <td valign="top"><div align="center" class="style2">
         <div align="center" id="Y3" >     </div>
        <input name="K3" type="hidden" id="K3" size="5" maxlength="5" value="$K3" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L3</strong>
        <input name="L3" type="hidden" id="L3" size="5" maxlength="5" value="$L3" >
      </div></td>
  </tr>
  <tr class="row2">
    <td valign="top"><span class="style1">PERAK</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A4</strong>
        <input name="A4" type="hidden" id="A4" size="5" maxlength="5" value="$A4" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B4</strong>
        <input name="B4" type="hidden" id="B4" size="5" maxlength="5" value="$B4" >
        </div></td>
    <td valign="top" colspan="6">
      
        #set($Perak1 = 0)
  #set($Perak2 = 0)
  #set($Perak3 = 0)
  #set($Perak4 = 0)
  #set($Perak5 = 0)
  #set($Perak6 = 0)
  #set($Perak7 = 0)
  #set($Perak8 = 0) 
  #set($Perak9 = 0)
  #set($Perak10 = 0)
  #set($Perak11 = 0)
  #set($Perak12 = 0) 
  #set($Perak13 = 0)
  #set($Perak14 = 0)
  #set($Perak15 = 0)
  #set($Perak16 = 0)
  #set($Perak17 = 0)
  #set($Perak18 = 0)
  #set($Perak19 = 0)
  #set($Perak20 = 0) 
  #set($Perak21 = 0)
  #set($Perak22 = 0)
  #set($Perak23 = 0)
  #set($Perak24 = 0)
  #set($Perak25 = 0)
  #set($Perak26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "8")
  #set($Perak1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Perak2 = $list.F11)
  #set($Perak4 = $list.F12)
  #set($Perak5 = $list.F13)
  #set($Perak6 = $list.F14)
  #set($Perak7 = $list.F15) 
  #set($Perak8 = $list.F16) 
  #set($Perak9 = $list.F17) 
  #set($Perak10 = $list.F18) 
  #set($Perak11 = $list.F19)  
  #set($Perak12 = $list.F20)
  #set($Perak13 = $list.F21)
  #set($Perak14 = $list.F22)
  
   
  #set($Perak15 = $list.F11+1)
  #set($Perak16 = $list.F12+1)
  #set($Perak17 = $list.F13+1)
  #set($Perak18 = $list.F14+1)
  #set($Perak19 = $list.F15+1) 
  #set($Perak20 = $list.F16+1) 
  #set($Perak21 = $list.F17+1) 
  #set($Perak22 = $list.F18+1) 
  #set($Perak23 = $list.F19+1)  
  #set($Perak24 = $list.F20+1)
  #set($Perak25 = $list.F21+1)
  #set($Perak26 = $list.F22+1)
    
  #end 
  #end




#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Perak2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "8")
#set($C4 = $C4 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Perak4 && $list.HARI_TUNGGU_JPPH >= $Perak15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "8")
#set($D4 = $D4 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Perak16 && $list.ID_NEGERI == "8")
#set($E4 = $E4 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Perak5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "8")
#set($F4 = $F4 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Perak6 && $list.HARI_TUNGGU_JT >= $Perak17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "8")
#set($G4 = $G4 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Perak18 && $list.ID_NEGERI == "8")
#set($H4 = $H4 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Perak7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "8")
#set($M4 = $M4 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Perak8 && $list.HARI_TUNGGU_PBN >= $Perak19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "8")
#set($N4 = $N4 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Perak20 && $list.ID_NEGERI == "8")
#set($O4 = $O4 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Perak9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "8")
#set($P4 = $P4 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Perak10 && $list.HARI_TUNGGU_DHDK >= $Perak21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "8")
#set($Q4 = $Q4 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Perak22 && $list.ID_NEGERI == "8")
#set($R4 = $R4 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Perak11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "8")
#set($S4 = $S4 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Perak12 && $list.HARI_TUNGGU_BAYARAN >= $Perak23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "8")
#set($T4 = $T4 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Perak24 && $list.ID_NEGERI == "8")
#set($U4 = $U4 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Perak13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "8")
#set($V4 = $V4 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Perak14 && $list.HARI_TUNGGU_BORANGK >= $Perak25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "8")
#set($W4 = $W4 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Perak26 && $list.ID_NEGERI == "8")
#set($AB4 = $AB4 + 1)
#end
#end



<table width="100%">
  <tr>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Perak2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C4</strong>
        <input name="C4" type="hidden" id="C4" size="5" maxlength="5" value="$C4" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Perak15 - $Perak4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D4</strong>
        <input name="D4" type="hidden" id="D4" size="5" maxlength="5" value="$D4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Perak16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E4</strong>
        <input name="E4" type="hidden" id="E4" size="5" maxlength="5" value="$E4" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perak9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P4</strong>
        <input name="P4" type="hidden" id="P4" size="5" maxlength="5" value="$P4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perak21 - $Perak10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q4</strong>
        <input name="Q4" type="hidden" id="Q4" size="5" maxlength="5" value="$Q4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perak22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R4</strong>
        <input name="R4" type="hidden" id="R4" size="5" maxlength="5" value="$R4" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perak5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F4</strong>
        <input name="F4" type="hidden" id="F4" size="5" maxlength="5" value="$F4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perak17 - $Perak6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G4</strong>
        <input name="G4" type="hidden" id="G4" size="5" maxlength="5" value="$G4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perak18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H4</strong>
        <input name="H4" type="hidden" id="H4" size="5" maxlength="5" value="$H4" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perak11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S4</strong>
        <input name="S4" type="hidden" id="S4" size="5" maxlength="5" value="$S4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perak23 - $Perak12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T4</strong>
        <input name="T4" type="hidden" id="T4" size="5" maxlength="5" value="$T4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perak24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U4</strong>
        <input name="U4" type="hidden" id="U4" size="5" maxlength="5" value="$U4" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perak7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M4</strong>
        <input name="M4" type="hidden" id="M4" size="5" maxlength="5" value="$M4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perak19 - $Perak8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N4</strong>
        <input name="N4" type="hidden" id="N4" size="5" maxlength="5" value="$N4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perak20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O4</strong>
        <input name="O4" type="hidden" id="O4" size="5" maxlength="5" value="$O4" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perak13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V4</strong>
        <input name="V4" type="hidden" id="V4" size="5" maxlength="5" value="$V4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perak25 - $Perak14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W4</strong>
        <input name="W4" type="hidden" id="W4" size="5" maxlength="5" value="$W4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perak26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB4</strong>
        <input name="AB4" type="hidden" id="AB4" size="5" maxlength="5" value="$AB4" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
</table>
      
    </td>
     <td valign="top"><div align="center" class="style2">
        <strong>$I4</strong>
        <input name="I4" type="hidden" id="I4" size="5" maxlength="5" value="$I4" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J4</strong>
        <input name="J4" type="hidden" id="J4" size="5" maxlength="5" value="$J4" >
      </div></td>
       <td valign="top"><div align="center" class="style2">
         <strong>$Perak1</strong>
        <input name="X4" type="hidden" id="X4" size="5" maxlength="5" value="$Perak1" style="text-align:center" >
      </div> </td>
     <td valign="top"><div align="center" class="style2">
         <div align="center" id="Y4" >     </div>
        <input name="K4" type="hidden" id="K4" size="5" maxlength="5" value="$K4" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L4</strong>
        <input name="L4" type="hidden" id="L4" size="5" maxlength="5" value="$L4" >
      </div></td>
  </tr>
  <tr class="row1">
    <td valign="top"><span class="style1">KELANTAN</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A5</strong>
        <input name="A5" type="hidden" id="A5" size="5" maxlength="5" value="$A5" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B5</strong>
        <input name="B5" type="hidden" id="B5" size="5" maxlength="5" value="$B5" >
        </div></td>
   <td valign="top" colspan="6">
   
     
 #set($Kelantan1 = 0)
  #set($Kelantan2 = 0)
  #set($Kelantan3 = 0)
  #set($Kelantan4 = 0)
  #set($Kelantan5 = 0)
  #set($Kelantan6 = 0)
  #set($Kelantan7 = 0)
  #set($Kelantan8 = 0) 
  #set($Kelantan9 = 0)
  #set($Kelantan10 = 0)
  #set($Kelantan11 = 0)
  #set($Kelantan12 = 0) 
  #set($Kelantan13 = 0)
  #set($Kelantan14 = 0)
  #set($Kelantan15 = 0)
  #set($Kelantan16 = 0)
  #set($Kelantan17 = 0)
  #set($Kelantan18 = 0)
  #set($Kelantan19 = 0)
  #set($Kelantan20 = 0) 
  #set($Kelantan21 = 0)
  #set($Kelantan22 = 0)
  #set($Kelantan23 = 0)
  #set($Kelantan24 = 0)
  #set($Kelantan25 = 0)
  #set($Kelantan26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "3")
  #set($Kelantan1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Kelantan2 = $list.F11)
  #set($Kelantan4 = $list.F12)
  #set($Kelantan5 = $list.F13)
  #set($Kelantan6 = $list.F14)
  #set($Kelantan7 = $list.F15) 
  #set($Kelantan8 = $list.F16) 
  #set($Kelantan9 = $list.F17) 
  #set($Kelantan10 = $list.F18) 
  #set($Kelantan11 = $list.F19)  
  #set($Kelantan12 = $list.F20)
  #set($Kelantan13 = $list.F21)
  #set($Kelantan14 = $list.F22)
  
   
  #set($Kelantan15 = $list.F11+1)
  #set($Kelantan16 = $list.F12+1)
  #set($Kelantan17 = $list.F13+1)
  #set($Kelantan18 = $list.F14+1)
  #set($Kelantan19 = $list.F15+1) 
  #set($Kelantan20 = $list.F16+1) 
  #set($Kelantan21 = $list.F17+1) 
  #set($Kelantan22 = $list.F18+1) 
  #set($Kelantan23 = $list.F19+1)  
  #set($Kelantan24 = $list.F20+1)
  #set($Kelantan25 = $list.F21+1)
  #set($Kelantan26 = $list.F22+1)
    
  #end 
  #end




#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Kelantan2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "3")
#set($C5 = $C5 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Kelantan4 && $list.HARI_TUNGGU_JPPH >= $Kelantan15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "3")
#set($D5 = $D5 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Kelantan16 && $list.ID_NEGERI == "3")
#set($E5 = $E5 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Kelantan5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "3")
#set($F5 = $F5 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Kelantan6 && $list.HARI_TUNGGU_JT >= $Kelantan17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "3")
#set($G5 = $G5 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Kelantan18 && $list.ID_NEGERI == "3")
#set($H5 = $H5 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Kelantan7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "3")
#set($M5 = $M5 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Kelantan8 && $list.HARI_TUNGGU_PBN >= $Kelantan19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "3")
#set($N5 = $N5 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Kelantan20 && $list.ID_NEGERI == "3")
#set($O5 = $O5 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Kelantan9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "3")
#set($P5 = $P5 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Kelantan10 && $list.HARI_TUNGGU_DHDK >= $Kelantan21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "3")
#set($Q5 = $Q5 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Kelantan22 && $list.ID_NEGERI == "3")
#set($R5 = $R5 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Kelantan11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "3")
#set($S5 = $S5 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Kelantan12 && $list.HARI_TUNGGU_BAYARAN >= $Kelantan23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "3")
#set($T5 = $T5 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Kelantan24 && $list.ID_NEGERI == "3")
#set($U5 = $U5 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Kelantan13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "3")
#set($V5 = $V5 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Kelantan14 && $list.HARI_TUNGGU_BORANGK >= $Kelantan25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "3")
#set($W5 = $W5 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Kelantan26 && $list.ID_NEGERI == "3")
#set($AB5 = $AB5 + 1)
#end
#end

    
<table width="100%">
  <tr>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Kelantan2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C5</strong>
        <input name="C5" type="hidden" id="C5" size="5" maxlength="5" value="$C5" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Kelantan15 - $Kelantan4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D5</strong>
        <input name="D5" type="hidden" id="D5" size="5" maxlength="5" value="$D5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Kelantan16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E5</strong>
        <input name="E5" type="hidden" id="E5" size="5" maxlength="5" value="$E5" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kelantan9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P5</strong>
        <input name="P5" type="hidden" id="P5" size="5" maxlength="5" value="$P5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kelantan21 - $Kelantan10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q5</strong>
        <input name="Q5" type="hidden" id="Q5" size="5" maxlength="5" value="$Q5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kelantan22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R5</strong>
        <input name="R5" type="hidden" id="R5" size="5" maxlength="5" value="$R5" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kelantan5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F5</strong>
        <input name="F5" type="hidden" id="F5" size="5" maxlength="5" value="$F5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kelantan17 - $Kelantan6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G5</strong>
        <input name="G5" type="hidden" id="G5" size="5" maxlength="5" value="$G5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kelantan18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H5</strong>
        <input name="H5" type="hidden" id="H5" size="5" maxlength="5" value="$H5" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kelantan11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S5</strong>
        <input name="S5" type="hidden" id="S5" size="5" maxlength="5" value="$S5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kelantan23 - $Kelantan12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T5</strong>
        <input name="T5" type="hidden" id="T5" size="5" maxlength="5" value="$T5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kelantan24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U5</strong>
        <input name="U5" type="hidden" id="U5" size="5" maxlength="5" value="$U5" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kelantan7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M5</strong>
        <input name="M5" type="hidden" id="M5" size="5" maxlength="5" value="$M5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kelantan19 - $Kelantan8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N5</strong>
        <input name="N5" type="hidden" id="N5" size="5" maxlength="5" value="$N5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kelantan20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O5</strong>
        <input name="O5" type="hidden" id="O5" size="5" maxlength="5" value="$O5" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kelantan13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V5</strong>
        <input name="V5" type="hidden" id="V5" size="5" maxlength="5" value="$V5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kelantan25 - $Kelantan14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W5</strong>
        <input name="W5" type="hidden" id="W5" size="5" maxlength="5" value="$W5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kelantan26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB5</strong>
        <input name="AB5" type="hidden" id="AB5" size="5" maxlength="5" value="$AB5" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
</table>
   
   </td>
     <td valign="top"><div align="center" class="style2">
        <strong>$I5</strong>
        <input name="I5" type="hidden" id="I5" size="5" maxlength="5" value="$I5" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J5</strong>
        <input name="J5" type="hidden" id="J5" size="5" maxlength="5" value="$J5" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
       <strong>$Kelantan1</strong>
        <input name="X5" type="hidden" id="X5" size="5" maxlength="5" value="$Kelantan1" style="text-align:center" >
      </div> </td>
     <td valign="top"><div align="center" class="style2">
         <div align="center" id="Y5" >     </div>
        <input name="K5" type="hidden" id="K5" size="5" maxlength="5" value="$K5" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L5</strong>
        <input name="L5" type="hidden" id="L5" size="5" maxlength="5" value="$L5" >
    </div></td>
  </tr>
  <tr class="row2">
    <td valign="top"><span class="style1">TERENGGANU</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A6</strong>
        <input name="A6" type="hidden" id="A6" size="5" maxlength="5" value="$A6" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B6</strong>
        <input name="B6" type="hidden" id="B6" size="5" maxlength="5" value="$B6" >
        </div></td>
     <td valign="top" colspan="6">


   #set($Ganu1 = 0)
  #set($Ganu2 = 0)
  #set($Ganu3 = 0)
  #set($Ganu4 = 0)
  #set($Ganu5 = 0)
  #set($Ganu6 = 0)
  #set($Ganu7 = 0)
  #set($Ganu8 = 0) 
  #set($Ganu9 = 0)
  #set($Ganu10 = 0)
  #set($Ganu11 = 0)
  #set($Ganu12 = 0) 
  #set($Ganu13 = 0)
  #set($Ganu14 = 0)
  #set($Ganu15 = 0)
  #set($Ganu16 = 0)
  #set($Ganu17 = 0)
  #set($Ganu18 = 0)
  #set($Ganu19 = 0)
  #set($Ganu20 = 0) 
  #set($Ganu21 = 0)
  #set($Ganu22 = 0)
  #set($Ganu23 = 0)
  #set($Ganu24 = 0)
  #set($Ganu25 = 0)
  #set($Ganu26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "11")
  #set($Ganu1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Ganu2 = $list.F11)
  #set($Ganu4 = $list.F12)
  #set($Ganu5 = $list.F13)
  #set($Ganu6 = $list.F14)
  #set($Ganu7 = $list.F15) 
  #set($Ganu8 = $list.F16) 
  #set($Ganu9 = $list.F17) 
  #set($Ganu10 = $list.F18) 
  #set($Ganu11 = $list.F19)  
  #set($Ganu12 = $list.F20)
  #set($Ganu13 = $list.F21)
  #set($Ganu14 = $list.F22)
  
   
  #set($Ganu15 = $list.F11+1)
  #set($Ganu16 = $list.F12+1)
  #set($Ganu17 = $list.F13+1)
  #set($Ganu18 = $list.F14+1)
  #set($Ganu19 = $list.F15+1) 
  #set($Ganu20 = $list.F16+1) 
  #set($Ganu21 = $list.F17+1) 
  #set($Ganu22 = $list.F18+1) 
  #set($Ganu23 = $list.F19+1)  
  #set($Ganu24 = $list.F20+1)
  #set($Ganu25 = $list.F21+1)
  #set($Ganu26 = $list.F22+1)
    
  #end 
  #end


  
#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Ganu2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "11")
#set($C6 = $C6 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Ganu4 && $list.HARI_TUNGGU_JPPH >= $Ganu15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "11")
#set($D6 = $D6 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Ganu16 && $list.ID_NEGERI == "11")
#set($E6 = $E6 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Ganu5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "11")
#set($F6 = $F6 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Ganu6 && $list.HARI_TUNGGU_JT >= $Ganu17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "11")
#set($G6 = $G6 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Ganu18 && $list.ID_NEGERI == "11")
#set($H6 = $H6 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Ganu7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "11")
#set($M6 = $M6 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Ganu8 && $list.HARI_TUNGGU_PBN >= $Ganu19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "11")
#set($N6 = $N6 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Ganu20 && $list.ID_NEGERI == "11")
#set($O6 = $O6 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Ganu9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "11")
#set($P6 = $P6 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Ganu10 && $list.HARI_TUNGGU_DHDK >= $Ganu21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "11")
#set($Q6 = $Q6 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Ganu22 && $list.ID_NEGERI == "11")
#set($R6 = $R6 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Ganu11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "11")
#set($S6 = $S6 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Ganu12 && $list.HARI_TUNGGU_BAYARAN >= $Ganu23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "11")
#set($T6 = $T6 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Ganu24 && $list.ID_NEGERI == "11")
#set($U6 = $U6 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Ganu13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "11")
#set($V6 = $V6 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Ganu14 && $list.HARI_TUNGGU_BORANGK >= $Ganu25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "11")
#set($W6 = $W6 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Ganu26 && $list.ID_NEGERI == "11")
#set($AB6 = $AB6 + 1)
#end
#end



<table width="100%">
  <tr>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Ganu2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C6</strong>
        <input name="C6" type="hidden" id="C6" size="5" maxlength="5" value="$C6" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Ganu15 - $Ganu4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D6</strong>
        <input name="D6" type="hidden" id="D6" size="5" maxlength="5" value="$D6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Ganu16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E6</strong>
        <input name="E6" type="hidden" id="E6" size="5" maxlength="5" value="$E6" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Ganu9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P6</strong>
        <input name="P6" type="hidden" id="P6" size="5" maxlength="5" value="$P6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Ganu21 - $Ganu10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q6</strong>
        <input name="Q6" type="hidden" id="Q6" size="5" maxlength="5" value="$Q6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Ganu22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R6</strong>
        <input name="R6" type="hidden" id="R6" size="5" maxlength="5" value="$R6" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Ganu5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F6</strong>
        <input name="F6" type="hidden" id="F6" size="5" maxlength="5" value="$F6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Ganu17 - $Ganu6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G6</strong>
        <input name="G6" type="hidden" id="G6" size="5" maxlength="5" value="$G6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Ganu18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H6</strong>
        <input name="H6" type="hidden" id="H6" size="5" maxlength="5" value="$H6" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Ganu11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S6</strong>
        <input name="S6" type="hidden" id="S6" size="5" maxlength="5" value="$S6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Ganu23 - $Ganu12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T6</strong>
        <input name="T6" type="hidden" id="T6" size="5" maxlength="5" value="$T6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Ganu24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U6</strong>
        <input name="U6" type="hidden" id="U6" size="5" maxlength="5" value="$U6" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Ganu7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M6</strong>
        <input name="M6" type="hidden" id="M6" size="5" maxlength="5" value="$M6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Ganu19 - $Ganu8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N6</strong>
        <input name="N6" type="hidden" id="N6" size="5" maxlength="5" value="$N6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Ganu20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O6</strong>
        <input name="O6" type="hidden" id="O6" size="5" maxlength="5" value="$O6" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Ganu13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V6</strong>
        <input name="V6" type="hidden" id="V6" size="5" maxlength="5" value="$V6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Ganu25 - $Ganu14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W6</strong>
        <input name="W6" type="hidden" id="W6" size="5" maxlength="5" value="$W6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Ganu26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB6</strong>
        <input name="AB6" type="hidden" id="AB6" size="5" maxlength="5" value="$AB6" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
</table>

   </td>
     <td valign="top"><div align="center" class="style2">
        <strong>$I6</strong>
        <input name="I6" type="hidden" id="I6" size="5" maxlength="5" value="$I6" >
    </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J6</strong>
        <input name="J6" type="hidden" id="J6" size="5" maxlength="5" value="$J6" >
      </div></td>
       <td valign="top"><div align="center" class="style2">
         <strong>$Ganu1</strong>
        <input name="X6" type="hidden" id="X6" size="5" maxlength="5" value="$Ganu1" style="text-align:center" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
         <div align="center" id="Y6" >     </div>
        <input name="K6" type="hidden" id="K6" size="5" maxlength="5" value="$K6" >
    </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L6</strong>
        <input name="L6" type="hidden" id="L6" size="5" maxlength="5" value="$L6" >
      </div></td>
  </tr>
  <tr class="row1">
    <td valign="top"><span class="style1">PAHANG</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A7</strong>
        <input name="A7" type="hidden" id="A7" size="5" maxlength="5" value="$A7" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B7</strong>
        <input name="B7" type="hidden" id="B7" size="5" maxlength="5" value="$B7" >
        </div></td>
    <td valign="top" colspan="6">
    
    
    
      
#set($Pahang1 = 0)
  #set($Pahang2 = 0)
  #set($Pahang3 = 0)
  #set($Pahang4 = 0)
  #set($Pahang5 = 0)
  #set($Pahang6 = 0)
  #set($Pahang7 = 0)
  #set($Pahang8 = 0) 
  #set($Pahang9 = 0)
  #set($Pahang10 = 0)
  #set($Pahang11 = 0)
  #set($Pahang12 = 0) 
  #set($Pahang13 = 0)
  #set($Pahang14 = 0)
  #set($Pahang15 = 0)
  #set($Pahang16 = 0)
  #set($Pahang17 = 0)
  #set($Pahang18 = 0)
  #set($Pahang19 = 0)
  #set($Pahang20 = 0) 
  #set($Pahang21 = 0)
  #set($Pahang22 = 0)
  #set($Pahang23 = 0)
  #set($Pahang24 = 0)
  #set($Pahang25 = 0)
  #set($Pahang26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "6")
  #set($Pahang1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Pahang2 = $list.F11)
  #set($Pahang4 = $list.F12)
  #set($Pahang5 = $list.F13)
  #set($Pahang6 = $list.F14)
  #set($Pahang7 = $list.F15) 
  #set($Pahang8 = $list.F16) 
  #set($Pahang9 = $list.F17) 
  #set($Pahang10 = $list.F18) 
  #set($Pahang11 = $list.F19)  
  #set($Pahang12 = $list.F20)
  #set($Pahang13 = $list.F21)
  #set($Pahang14 = $list.F22)
  
   
  #set($Pahang15 = $list.F11+1)
  #set($Pahang16 = $list.F12+1)
  #set($Pahang17 = $list.F13+1)
  #set($Pahang18 = $list.F14+1)
  #set($Pahang19 = $list.F15+1) 
  #set($Pahang20 = $list.F16+1) 
  #set($Pahang21 = $list.F17+1) 
  #set($Pahang22 = $list.F18+1) 
  #set($Pahang23 = $list.F19+1)  
  #set($Pahang24 = $list.F20+1)
  #set($Pahang25 = $list.F21+1)
  #set($Pahang26 = $list.F22+1)
    
  #end 
  #end



#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Pahang2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "6")
#set($C7 = $C7 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Pahang4 && $list.HARI_TUNGGU_JPPH >= $Pahang15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "6")
#set($D7 = $D7 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Pahang16 && $list.ID_NEGERI == "6")
#set($E7 = $E7 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Pahang5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "6")
#set($F7 = $F7 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Pahang6 && $list.HARI_TUNGGU_JT >= $Pahang17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "6")
#set($G7 = $G7 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Pahang18 && $list.ID_NEGERI == "6")
#set($H7 = $H7 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Pahang7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "6")
#set($M7 = $M7 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Pahang8 && $list.HARI_TUNGGU_PBN >= $Pahang19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "6")
#set($N7 = $N7 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Pahang20 && $list.ID_NEGERI == "6")
#set($O7 = $O7 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Pahang9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "6")
#set($P7 = $P7 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Pahang10 && $list.HARI_TUNGGU_DHDK >= $Pahang21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "6")
#set($Q7 = $Q7 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Pahang22 && $list.ID_NEGERI == "6")
#set($R7 = $R7 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Pahang11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "6")
#set($S7 = $S7 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Pahang12 && $list.HARI_TUNGGU_BAYARAN >= $Pahang23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "6")
#set($T7 = $T7 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Pahang24 && $list.ID_NEGERI == "6")
#set($U7 = $U7 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Pahang13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "6")
#set($V7 = $V7 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Pahang14 && $list.HARI_TUNGGU_BORANGK >= $Pahang25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "6")
#set($W7 = $W7 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Pahang26 && $list.ID_NEGERI == "6")
#set($AB7 = $AB7 + 1)
#end
#end


<table width="100%">
  <tr>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Pahang2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C7</strong>
        <input name="C7" type="hidden" id="C7" size="5" maxlength="5" value="$C7" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Pahang15 - $Pahang4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D7</strong>
        <input name="D7" type="hidden" id="D7" size="5" maxlength="5" value="$D7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Pahang16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E7</strong>
        <input name="E7" type="hidden" id="E7" size="5" maxlength="5" value="$E7" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Pahang9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P7</strong>
        <input name="P7" type="hidden" id="P7" size="5" maxlength="5" value="$P7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Pahang21 - $Pahang10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q7</strong>
        <input name="Q7" type="hidden" id="Q7" size="5" maxlength="5" value="$Q7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Pahang22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R7</strong>
        <input name="R7" type="hidden" id="R7" size="5" maxlength="5" value="$R7" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Pahang5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F7</strong>
        <input name="F7" type="hidden" id="F7" size="5" maxlength="5" value="$F7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Pahang17 - $Pahang6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G7</strong>
        <input name="G7" type="hidden" id="G7" size="5" maxlength="5" value="$G7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Pahang18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H7</strong>
        <input name="H7" type="hidden" id="H7" size="5" maxlength="5" value="$H7" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Pahang11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S7</strong>
        <input name="S7" type="hidden" id="S7" size="5" maxlength="5" value="$S7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Pahang23 - $Pahang12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T7</strong>
        <input name="T7" type="hidden" id="T7" size="5" maxlength="5" value="$T7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Pahang24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U7</strong>
        <input name="U7" type="hidden" id="U7" size="5" maxlength="5" value="$U7" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Pahang7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M7</strong>
        <input name="M7" type="hidden" id="M7" size="5" maxlength="5" value="$M7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Pahang19 - $Pahang8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N7</strong>
        <input name="N7" type="hidden" id="N7" size="5" maxlength="5" value="$N7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Pahang20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O7</strong>
        <input name="O7" type="hidden" id="O7" size="5" maxlength="5" value="$O7" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>

            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Pahang13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V7</strong>
        <input name="V7" type="hidden" id="V7" size="5" maxlength="5" value="$V7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Pahang25 - $Pahang14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W7</strong>
        <input name="W7" type="hidden" id="W7" size="5" maxlength="5" value="$W7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Pahang26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB7</strong>
        <input name="AB7" type="hidden" id="AB7" size="5" maxlength="5" value="$AB7" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
</table>
    
    
    
    </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$I7</strong>
        <input name="I7" type="hidden" id="I7" size="5" maxlength="5" value="$I7" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J7</strong>
        <input name="J7" type="hidden" id="J7" size="5" maxlength="5" value="$J7" >
    </div></td>
    
     <td valign="top"><div align="center" class="style2">
       <strong>$Pahang1</strong>
        <input name="X7" type="hidden" id="X7" size="5" maxlength="5" value="$Pahang1"  style="text-align:center">
      </div> </td>
    
    <td valign="top"><div align="center" class="style2">
        <div align="center" id="Y7" >     </div>
        <input name="K7" type="hidden" id="K7" size="5" maxlength="5" value="$K7" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L7</strong>
        <input name="L7" type="hidden" id="L7" size="5" maxlength="5" value="$L7" >
    </div></td>
  </tr>
  <tr class="row2">
    <td valign="top"><span class="style1">SELANGOR</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A8</strong>
        <input name="A8" type="hidden" id="A8" size="5" maxlength="5" value="$A8" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B8</strong>
        <input name="B8" type="hidden" id="B8" size="5" maxlength="5" value="$B8" >
        </div></td>
     <td valign="top" colspan="6">
     
     
       
 #set($Selangor1 = 0)
  #set($Selangor2 = 0)
  #set($Selangor3 = 0)
  #set($Selangor4 = 0)
  #set($Selangor5 = 0)
  #set($Selangor6 = 0)
  #set($Selangor7 = 0)
  #set($Selangor8 = 0) 
  #set($Selangor9 = 0)
  #set($Selangor10 = 0)
  #set($Selangor11 = 0)
  #set($Selangor12 = 0) 
  #set($Selangor13 = 0)
  #set($Selangor14 = 0)
  #set($Selangor15 = 0)
  #set($Selangor16 = 0)
  #set($Selangor17 = 0)
  #set($Selangor18 = 0)
  #set($Selangor19 = 0)
  #set($Selangor20 = 0) 
  #set($Selangor21 = 0)
  #set($Selangor22 = 0)
  #set($Selangor23 = 0)
  #set($Selangor24 = 0)
  #set($Selangor25 = 0)
  #set($Selangor26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "10")
  #set($Selangor1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Selangor2 = $list.F11)
  #set($Selangor4 = $list.F12)
  #set($Selangor5 = $list.F13)
  #set($Selangor6 = $list.F14)
  #set($Selangor7 = $list.F15) 
  #set($Selangor8 = $list.F16) 
  #set($Selangor9 = $list.F17) 
  #set($Selangor10 = $list.F18) 
  #set($Selangor11 = $list.F19)  
  #set($Selangor12 = $list.F20)
  #set($Selangor13 = $list.F21)
  #set($Selangor14 = $list.F22)
  
   
  #set($Selangor15 = $list.F11+1)
  #set($Selangor16 = $list.F12+1)
  #set($Selangor17 = $list.F13+1)
  #set($Selangor18 = $list.F14+1)
  #set($Selangor19 = $list.F15+1) 
  #set($Selangor20 = $list.F16+1) 
  #set($Selangor21 = $list.F17+1) 
  #set($Selangor22 = $list.F18+1) 
  #set($Selangor23 = $list.F19+1)  
  #set($Selangor24 = $list.F20+1)
  #set($Selangor25 = $list.F21+1)
  #set($Selangor26 = $list.F22+1)
    
  #end 
  #end



#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Selangor2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "10")
#set($C8 = $C8 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Selangor4 && $list.HARI_TUNGGU_JPPH >= $Selangor15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "10")
#set($D8 = $D8 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Selangor16 && $list.ID_NEGERI == "10")
#set($E8 = $E8 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Selangor5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "10")
#set($F8 = $F8 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Selangor6 && $list.HARI_TUNGGU_JT >= $Selangor17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "10")
#set($G8 = $G8 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Selangor18 && $list.ID_NEGERI == "10")
#set($H8 = $H8 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Selangor7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "10")
#set($M8 = $M8 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Selangor8 && $list.HARI_TUNGGU_PBN >= $Selangor19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "10")
#set($N8 = $N8 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Selangor20 && $list.ID_NEGERI == "10")
#set($O8 = $O8 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Selangor9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "10")
#set($P8 = $P8 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Selangor10 && $list.HARI_TUNGGU_DHDK >= $Selangor21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "10")
#set($Q8 = $Q8 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Selangor22 && $list.ID_NEGERI == "10")
#set($R8 = $R8 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Selangor11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "10")
#set($S8 = $S8 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Selangor12 && $list.HARI_TUNGGU_BAYARAN >= $Selangor23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "10")
#set($T8 = $T8 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Selangor24 && $list.ID_NEGERI == "10")
#set($U8 = $U8 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Selangor13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "10")
#set($V8 = $V8 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Selangor14 && $list.HARI_TUNGGU_BORANGK >= $Selangor25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "10")
#set($W8 = $W8 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Selangor26 && $list.ID_NEGERI == "10")
#set($AB8 = $AB8 + 1)
#end
#end
<table width="100%">
  <tr>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Selangor2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C8</strong>
        <input name="C8" type="hidden" id="C8" size="5" maxlength="5" value="$C8" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Selangor15 - $Selangor4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D8</strong>
        <input name="D8" type="hidden" id="D8" size="5" maxlength="5" value="$D8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Selangor16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E8</strong>
        <input name="E8" type="hidden" id="E8" size="5" maxlength="5" value="$E8" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top" width="50%">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Selangor9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P8</strong>
        <input name="P8" type="hidden" id="P8" size="5" maxlength="5" value="$P8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Selangor21 - $Selangor10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q8</strong>
        <input name="Q8" type="hidden" id="Q8" size="5" maxlength="5" value="$Q8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Selangor22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R8</strong>
        <input name="R8" type="hidden" id="R8" size="5" maxlength="5" value="$R8" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Selangor5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F8</strong>
        <input name="F8" type="hidden" id="F8" size="5" maxlength="5" value="$F8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Selangor17 - $Selangor6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G8</strong>
        <input name="G8" type="hidden" id="G8" size="5" maxlength="5" value="$G8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Selangor18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H8</strong>
        <input name="H8" type="hidden" id="H8" size="5" maxlength="5" value="$H8" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Selangor11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S8</strong>
        <input name="S8" type="hidden" id="S8" size="5" maxlength="5" value="$S8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Selangor23 - $Selangor12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T8</strong>
        <input name="T8" type="hidden" id="T8" size="5" maxlength="5" value="$T8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Selangor24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U8</strong>
        <input name="U8" type="hidden" id="U8" size="5" maxlength="5" value="$U8" >
      </div>  </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Selangor7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M8</strong>
        <input name="M8" type="hidden" id="M8" size="5" maxlength="5" value="$M8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Selangor19 - $Selangor8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N8</strong>
        <input name="N8" type="hidden" id="N8" size="5" maxlength="5" value="$N8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Selangor20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O8</strong>
        <input name="O8" type="hidden" id="O8" size="5" maxlength="5" value="$O8" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Selangor13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V8</strong>
        <input name="V8" type="hidden" id="V8" size="5" maxlength="5" value="$V8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Selangor25 - $Selangor14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W8</strong>
        <input name="W8" type="hidden" id="W8" size="5" maxlength="5" value="$W8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Selangor26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB8</strong>
        <input name="AB8" type="hidden" id="AB8" size="5" maxlength="5" value="$AB8" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
</table>
     
     
     
     
     </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$I8</strong>
        <input name="I8" type="hidden" id="I8" size="5" maxlength="5" value="$I8" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J8</strong>
        <input name="J8" type="hidden" id="J8" size="5" maxlength="5" value="$J8" >
      </div></td>
      
     <td valign="top"><div align="center" class="style2">
       <strong>$Selangor1</strong>
        <input name="X8" type="hidden" id="X8" size="5" maxlength="5" value="$Selangor1"  style="text-align:center">
      </div> </td>
   <td valign="top"><div align="center" class="style2">
         <div align="center" id="Y8" >     </div>
        <input name="K8" type="hidden" id="K8" size="5" maxlength="5" value="$K8" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L8</strong>
        <input name="L8" type="hidden" id="L8" size="5" maxlength="5" value="$L8" >
      </div></td>
  </tr>
  <tr class="row1">
    <td valign="top"><span class="style1">WP KUALA LUMPUR</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A9</strong>
        <input name="A9" type="hidden" id="A9" size="5" maxlength="5" value="$A9" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B9</strong>
        <input name="B9" type="hidden" id="B9" size="5" maxlength="5" value="$B9" >
        </div></td>
   <td valign="top" colspan="6">
   
   
     
#set($KL1 = 0)
  #set($KL2 = 0)
  #set($KL3 = 0)
  #set($KL4 = 0)
  #set($KL5 = 0)
  #set($KL6 = 0)
  #set($KL7 = 0)
  #set($KL8 = 0) 
  #set($KL9 = 0)
  #set($KL10 = 0)
  #set($KL11 = 0)
  #set($KL12 = 0) 
  #set($KL13 = 0)
  #set($KL14 = 0)
  #set($KL15 = 0)
  #set($KL16 = 0)
  #set($KL17 = 0)
  #set($KL18 = 0)
  #set($KL19 = 0)
  #set($KL20 = 0) 
  #set($KL21 = 0)
  #set($KL22 = 0)
  #set($KL23 = 0)
  #set($KL24 = 0)
  #set($KL25 = 0)
  #set($KL26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "14")
  #set($KL1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($KL2 = $list.F11)
  #set($KL4 = $list.F12)
  #set($KL5 = $list.F13)
  #set($KL6 = $list.F14)
  #set($KL7 = $list.F15) 
  #set($KL8 = $list.F16) 
  #set($KL9 = $list.F17) 
  #set($KL10 = $list.F18) 
  #set($KL11 = $list.F19)  
  #set($KL12 = $list.F20)
  #set($KL13 = $list.F21)
  #set($KL14 = $list.F22)
  
   
  #set($KL15 = $list.F11+1)
  #set($KL16 = $list.F12+1)
  #set($KL17 = $list.F13+1)
  #set($KL18 = $list.F14+1)
  #set($KL19 = $list.F15+1) 
  #set($KL20 = $list.F16+1) 
  #set($KL21 = $list.F17+1) 
  #set($KL22 = $list.F18+1) 
  #set($KL23 = $list.F19+1)  
  #set($KL24 = $list.F20+1)
  #set($KL25 = $list.F21+1)
  #set($KL26 = $list.F22+1)
    
  #end 
  #end




#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $KL2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "14")
#set($C9 = $C9 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $KL4 && $list.HARI_TUNGGU_JPPH >= $KL15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "14")
#set($D9 = $D9 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $KL16 && $list.ID_NEGERI == "14")
#set($E9 = $E9 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $KL5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "14")
#set($F9 = $F9 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $KL6 && $list.HARI_TUNGGU_JT >= $KL17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "14")
#set($G9 = $G9 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $KL18 && $list.ID_NEGERI == "14")
#set($H9 = $H9 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $KL7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "14")
#set($M9 = $M9 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $KL8 && $list.HARI_TUNGGU_PBN >= $KL19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "14")
#set($N9 = $N9 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $KL20 && $list.ID_NEGERI == "14")
#set($O9 = $O9 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $KL9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "14")
#set($P9 = $P9 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $KL10 && $list.HARI_TUNGGU_DHDK >= $KL21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "14")
#set($Q9 = $Q9 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $KL22 && $list.ID_NEGERI == "14")
#set($R9 = $R9 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $KL11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "14")
#set($S9 = $S9 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $KL12 && $list.HARI_TUNGGU_BAYARAN >= $KL23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "14")
#set($T9 = $T9 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $KL24 && $list.ID_NEGERI == "14")
#set($U9 = $U9 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $KL13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "14")
#set($V9 = $V9 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $KL14 && $list.HARI_TUNGGU_BORANGK >= $KL25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "14")
#set($W9 = $W9 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $KL26 && $list.ID_NEGERI == "14")
#set($AB9 = $AB9 + 1)
#end
#end
<table width="100%">
  <tr>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $KL2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C9</strong>
        <input name="C9" type="hidden" id="C9" size="5" maxlength="5" value="$C9" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$KL15 - $KL4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D9</strong>
        <input name="D9" type="hidden" id="D9" size="5" maxlength="5" value="$D9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $KL16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E9</strong>
        <input name="E9" type="hidden" id="E9" size="5" maxlength="5" value="$E9" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $KL9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P9</strong>
        <input name="P9" type="hidden" id="P9" size="5" maxlength="5" value="$P9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$KL21 - $KL10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q9</strong>
        <input name="Q9" type="hidden" id="Q9" size="5" maxlength="5" value="$Q9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $KL22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R9</strong>
        <input name="R9" type="hidden" id="R9" size="5" maxlength="5" value="$R9" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $KL5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F9</strong>
        <input name="F9" type="hidden" id="F9" size="5" maxlength="5" value="$F9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$KL17 - $KL6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G9</strong>
        <input name="G9" type="hidden" id="G9" size="5" maxlength="5" value="$G9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $KL18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H9</strong>
        <input name="H9" type="hidden" id="H9" size="5" maxlength="5" value="$H9" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $KL11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S9</strong>
        <input name="S9" type="hidden" id="S9" size="5" maxlength="5" value="$S9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$KL23 - $KL12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T9</strong>
        <input name="T9" type="hidden" id="T9" size="5" maxlength="5" value="$T9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $KL24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U9</strong>
        <input name="U9" type="hidden" id="U9" size="5" maxlength="5" value="$U9" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $KL7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M9</strong>
        <input name="M9" type="hidden" id="M9" size="5" maxlength="5" value="$M9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$KL19 - $KL8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N9</strong>
        <input name="N9" type="hidden" id="N9" size="5" maxlength="5" value="$N9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $KL20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O9</strong>
        <input name="O9" type="hidden" id="O9" size="5" maxlength="5" value="$O9" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $KL13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V9</strong>
        <input name="V9" type="hidden" id="V9" size="5" maxlength="5" value="$V9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$KL25 - $KL14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W9</strong>
        <input name="W9" type="hidden" id="W9" size="5" maxlength="5" value="$W9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $KL26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB9</strong>
        <input name="AB9" type="hidden" id="AB9" size="5" maxlength="5" value="$AB9" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
</table>
   
   
   
   </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$I9</strong>
        <input name="I9" type="hidden" id="I9" size="5" maxlength="5" value="$I9" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J9</strong>
        <input name="J9" type="hidden" id="J9" size="5" maxlength="5" value="$J9" >
      </div></td>
      
      <td valign="top"><div align="center" class="style2">
        <strong>$KL1</strong>
        <input name="X9" type="hidden" id="X9" size="5" maxlength="5" value="$KL1" style="text-align:center" >
      </div> </td>
      
    <td valign="top"><div align="center" class="style2">
         <div align="center" id="Y9" >     </div>
        <input name="K9" type="hidden" id="K9" size="5" maxlength="5" value="$K9" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L9</strong>
        <input name="L9" type="hidden" id="L9" size="5" maxlength="5" value="$L9" >
      </div></td>
  </tr>
  <tr class="row2">
    <td valign="top"><span class="style1">NEGERI SEMBILAN</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A10</strong>
        <input name="A10" type="hidden" id="A10" size="5" maxlength="5" value="$A10" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B10</strong>
        <input name="B10" type="hidden" id="B10" size="5" maxlength="5" value="$B10" >
        </div></td>
     <td valign="top" colspan="6">
     
     
         #set($N91 = 0)
  #set($N92 = 0)
  #set($N93 = 0)
  #set($N94 = 0)
  #set($N95 = 0)
  #set($N96 = 0)
  #set($N97 = 0)
  #set($N98 = 0) 
  #set($N99 = 0)
  #set($N910 = 0)
  #set($N911 = 0)
  #set($N912 = 0) 
  #set($N913 = 0)
  #set($N914 = 0)
  #set($N915 = 0)
  #set($N916 = 0)
  #set($N917 = 0)
  #set($N918 = 0)
  #set($N919 = 0)
  #set($N920 = 0) 
  #set($N921 = 0)
  #set($N922 = 0)
  #set($N923 = 0)
  #set($N924 = 0)
  #set($N925 = 0)
  #set($N926 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "5")
  #set($N91 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($N92 = $list.F11)
  #set($N94 = $list.F12)
  #set($N95 = $list.F13)
  #set($N96 = $list.F14)
  #set($N97 = $list.F15) 
  #set($N98 = $list.F16) 
  #set($N99 = $list.F17) 
  #set($N910 = $list.F18) 
  #set($N911 = $list.F19)  
  #set($N912 = $list.F20)
  #set($N913 = $list.F21)
  #set($N914 = $list.F22)
  
   
  #set($N915 = $list.F11+1)
  #set($N916 = $list.F12+1)
  #set($N917 = $list.F13+1)
  #set($N918 = $list.F14+1)
  #set($N919 = $list.F15+1) 
  #set($N920 = $list.F16+1) 
  #set($N921 = $list.F17+1) 
  #set($N922 = $list.F18+1) 
  #set($N923 = $list.F19+1)  
  #set($N924 = $list.F20+1)
  #set($N925 = $list.F21+1)
  #set($N926 = $list.F22+1)
    
  #end 
  #end



#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $N92 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "5")
#set($C10 = $C10 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $N94 && $list.HARI_TUNGGU_JPPH >= $N915 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "5")
#set($D10 = $D10 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $N916 && $list.ID_NEGERI == "5")
#set($E10 = $E10 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $N95 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "5")
#set($F10 = $F10 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $N96 && $list.HARI_TUNGGU_JT >= $N917 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "5")
#set($G10 = $G10 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $N918 && $list.ID_NEGERI == "5")
#set($H10 = $H10 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $N97 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "5")
#set($M10 = $M10 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $N98 && $list.HARI_TUNGGU_PBN >= $N919 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "5")
#set($N10 = $N10 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $N920 && $list.ID_NEGERI == "5")
#set($O10 = $O10 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $N99 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "5")
#set($P10 = $P10 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $N910 && $list.HARI_TUNGGU_DHDK >= $N921&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "5")
#set($Q10 = $Q10 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $N922 && $list.ID_NEGERI == "5")
#set($R10 = $R10 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $N911 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "5")
#set($S10 = $S10 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $N912 && $list.HARI_TUNGGU_BAYARAN >= $N923 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "5")
#set($T10 = $T10 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $N924 && $list.ID_NEGERI == "5")
#set($U10 = $U10 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $N913 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "5")
#set($V10 = $V10 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $N914 && $list.HARI_TUNGGU_BORANGK >= $N925 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "5")
#set($W10 = $W10 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $N926 && $list.ID_NEGERI == "5")
#set($AB10 = $AB10 + 1)
#end
#end



<table width="100%">
  <tr>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $N92</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C10</strong>
        <input name="C10" type="hidden" id="C10" size="5" maxlength="5" value="$C10" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$N915 - $N94</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D10</strong>
        <input name="D10" type="hidden" id="D10" size="5" maxlength="5" value="$D10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $N916</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E10</strong>
        <input name="E10" type="hidden" id="E10" size="5" maxlength="5" value="$E10" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $N99</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P10</strong>
        <input name="P10" type="hidden" id="P10" size="5" maxlength="5" value="$P10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$N921 - $N910</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q10</strong>
        <input name="Q10" type="hidden" id="Q10" size="5" maxlength="5" value="$Q10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $N922</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R10</strong>
        <input name="R10" type="hidden" id="R10" size="5" maxlength="5" value="$R10" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $N95</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F10</strong>
        <input name="F10" type="hidden" id="F10" size="5" maxlength="5" value="$F10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$N917 - $N96</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G10</strong>
        <input name="G10" type="hidden" id="G10" size="5" maxlength="5" value="$G10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $N918</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H10</strong>
        <input name="H10" type="hidden" id="H10" size="5" maxlength="5" value="$H10" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $N911</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S10</strong>
        <input name="S10" type="hidden" id="S10" size="5" maxlength="5" value="$S10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$N923 - $N912</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T10</strong>
        <input name="T10" type="hidden" id="T10" size="5" maxlength="5" value="$T10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $N924</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U10</strong>
        <input name="U10" type="hidden" id="U10" size="5" maxlength="5" value="$U10" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $N97</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M10</strong>
        <input name="M10" type="hidden" id="M10" size="5" maxlength="5" value="$M10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$N919 - $N98</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N10</strong>
        <input name="N10" type="hidden" id="N10" size="5" maxlength="5" value="$N10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $N920</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O10</strong>
        <input name="O10" type="hidden" id="O10" size="5" maxlength="5" value="$O10" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $N913</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V10</strong>
        <input name="V10" type="hidden" id="V10" size="5" maxlength="5" value="$V10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$N925 - $N914</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W10</strong>
        <input name="W10" type="hidden" id="W10" size="5" maxlength="5" value="$W10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $N926</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB10</strong>
        <input name="AB10" type="hidden" id="AB10" size="5" maxlength="5" value="$AB10" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
</table>
     
     
     </td>
       <td valign="top"><div align="center" class="style2">
        <strong>$I10</strong>
        <input name="I10" type="hidden" id="I10" size="5" maxlength="5" value="$I10" >
      </div> </td>
      
     <td valign="top"><div align="center" class="style2">
        <strong>$J10</strong>
        <input name="J10" type="hidden" id="J10" size="5" maxlength="5" value="$J10" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$N91</strong>
        <input name="X10" type="hidden" id="X10" size="5" maxlength="5" value="$N91"  style="text-align:center">
      </div></td>
     <td valign="top"><div align="center" class="style2">
        <div align="center" id="Y10" >     </div>
        <input name="K10" type="hidden" id="K10" size="5" maxlength="5" value="$K10" >
      </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L10</strong>
        <input name="L10" type="hidden" id="L10" size="5" maxlength="5" value="$L10" >
      </div></td>
  </tr>
  <tr class="row1">
    <td valign="top"><span class="style1">MELAKA</span></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$A11</strong>
        <input name="A11" type="hidden" id="A11" size="5" maxlength="5" value="$A11" >
    </div></td>
    <td valign="top"><div align="center" class="style2">
        <strong>$B11</strong>
        <input name="B11" type="hidden" id="B11" size="5" maxlength="5" value="$B11" >
        </div></td>
    <td valign="top" colspan="6">
    
        #set($Melaka1 = 0)
  #set($Melaka2 = 0)
  #set($Melaka3 = 0)
  #set($Melaka4 = 0)
  #set($Melaka5 = 0)
  #set($Melaka6 = 0)
  #set($Melaka7 = 0)
  #set($Melaka8 = 0) 
  #set($Melaka9 = 0)
  #set($Melaka10 = 0)
  #set($Melaka11 = 0)
  #set($Melaka12 = 0) 
  #set($Melaka13 = 0)
  #set($Melaka14 = 0)
  #set($Melaka15 = 0)
  #set($Melaka16 = 0)
  #set($Melaka17 = 0)
  #set($Melaka18 = 0)
  #set($Melaka19 = 0)
  #set($Melaka20 = 0) 
  #set($Melaka21 = 0)
  #set($Melaka22 = 0)
  #set($Melaka23 = 0)
  #set($Melaka24 = 0)
  #set($Melaka25 = 0)
  #set($Melaka26 = 0) 
  
  
     
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "4")
  #set($Melaka1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7)
  
  
  #set($Melaka2 = $list.F11)
  #set($Melaka4 = $list.F12)
  #set($Melaka5 = $list.F13)
  #set($Melaka6 = $list.F14)
  #set($Melaka7 = $list.F15) 
  #set($Melaka8 = $list.F16) 
  #set($Melaka9 = $list.F17) 
  #set($Melaka10 = $list.F18) 
  #set($Melaka11 = $list.F19)  
  #set($Melaka12 = $list.F20)
  #set($Melaka13 = $list.F21)
  #set($Melaka14 = $list.F22)
  
   
  #set($Melaka15 = $list.F11+1)
  #set($Melaka16 = $list.F12+1)
  #set($Melaka17 = $list.F13+1)
  #set($Melaka18 = $list.F14+1)
  #set($Melaka19 = $list.F15+1) 
  #set($Melaka20 = $list.F16+1) 
  #set($Melaka21 = $list.F17+1) 
  #set($Melaka22 = $list.F18+1) 
  #set($Melaka23 = $list.F19+1)  
  #set($Melaka24 = $list.F20+1)
  #set($Melaka25 = $list.F21+1)
  #set($Melaka26 = $list.F22+1)
    
  #end 
  #end



    
#foreach($list in $list_KPI_Penarikan_Menunggu_JPPH)
#if($list.HARI_TUNGGU_JPPH <= $Melaka2 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "4")
#set($C11 = $C11 + 1)
#end
#if($list.HARI_TUNGGU_JPPH <= $Melaka4 && $list.HARI_TUNGGU_JPPH >= $Melaka15 && $list.HARI_TUNGGU_JPPH > 0 && $list.ID_NEGERI == "4")
#set($D11 = $D11 + 1)
#end
#if($list.HARI_TUNGGU_JPPH >= $Melaka16 && $list.ID_NEGERI == "4")
#set($E11 = $E11 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_JT)
#if($list.HARI_TUNGGU_JT <= $Melaka5 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "4")
#set($F11 = $F11 + 1)
#end
#if($list.HARI_TUNGGU_JT <= $Melaka6 && $list.HARI_TUNGGU_JT >= $Melaka17 && $list.HARI_TUNGGU_JT > 0 && $list.ID_NEGERI == "4")
#set($G11 = $G11 + 1)
#end
#if($list.HARI_TUNGGU_JT >= $Melaka18 && $list.ID_NEGERI == "4")
#set($H11 = $H11 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_PBN)
#if($list.HARI_TUNGGU_PBN <= $Melaka7 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "4")
#set($M11 = $M11 + 1)
#end
#if($list.HARI_TUNGGU_PBN <= $Melaka8 && $list.HARI_TUNGGU_PBN >= $Melaka19 && $list.HARI_TUNGGU_PBN > 0 && $list.ID_NEGERI == "4")
#set($N11 = $N11 + 1)
#end
#if($list.HARI_TUNGGU_PBN >= $Melaka20 && $list.ID_NEGERI == "4")
#set($O11 = $O11 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_DHDK)
#if($list.HARI_TUNGGU_DHDK <= $Melaka9 && $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "4")
#set($P11 = $P11 + 1)
#end
#if($list.HARI_TUNGGU_DHDK <= $Melaka10 && $list.HARI_TUNGGU_DHDK >= $Melaka21&& $list.HARI_TUNGGU_DHDK > 0 && $list.ID_NEGERI == "4")
#set($Q11 = $Q11 + 1)
#end
#if($list.HARI_TUNGGU_DHDK >= $Melaka22 && $list.ID_NEGERI == "4")
#set($R11 = $R11 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BAYARAN)
#if($list.HARI_TUNGGU_BAYARAN <= $Melaka11 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "4")
#set($S11 = $S11 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN <= $Melaka12 && $list.HARI_TUNGGU_BAYARAN >= $Melaka23 && $list.HARI_TUNGGU_BAYARAN > 0 && $list.ID_NEGERI == "4")
#set($T11 = $T11 + 1)
#end
#if($list.HARI_TUNGGU_BAYARAN >= $Melaka24 && $list.ID_NEGERI == "4")
#set($U11 = $U11 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_BORANGK)
#if($list.HARI_TUNGGU_BORANGK <= $Melaka13 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "4")
#set($V11 = $V11 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK <= $Melaka14 && $list.HARI_TUNGGU_BORANGK >= $Melaka25 && $list.HARI_TUNGGU_BORANGK > 0 && $list.ID_NEGERI == "4")
#set($W11 = $W11 + 1)
#end
#if($list.HARI_TUNGGU_BORANGK >= $Melaka26 && $list.ID_NEGERI == "4")
#set($AB11 = $AB11 + 1)
#end
#end

<table width="100%">
  <tr>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan JPPH dan JPBD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Melaka2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C11</strong>
        <input name="C11" type="hidden" id="C11" size="5" maxlength="5" value="$C11" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Melaka15 - $Melaka4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D11</strong>
        <input name="D11" type="hidden" id="D11" size="5" maxlength="5" value="$D11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Melaka16</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E11</strong>
        <input name="E11" type="hidden" id="E11" size="5" maxlength="5" value="$E11" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top" width="50%"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan DHDK PTG/PTD</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Melaka9</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$P11</strong>
        <input name="P11" type="hidden" id="P11" size="5" maxlength="5" value="$P11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Melaka21 - $Melaka10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$Q11</strong>
        <input name="Q11" type="hidden" id="Q11" size="5" maxlength="5" value="$Q11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Melaka22</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$R11</strong>
        <input name="R11" type="hidden" id="R11" size="5" maxlength="5" value="$R11" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Laporan/Ulasan Jabatan Teknikal (pilihan)</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Melaka5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F11</strong>
        <input name="F11" type="hidden" id="F11" size="5" maxlength="5" value="$F11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Melaka17 - $Melaka6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G11</strong>
        <input name="G11" type="hidden" id="G11" size="5" maxlength="5" value="$G11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Melaka18</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H11</strong>
        <input name="H11" type="hidden" id="H11" size="5" maxlength="5" value="$H11" >
      </div>  </td>
          </tr>
        </table></td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu bayaran pampasan daripada AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Melaka11</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$S11</strong>
        <input name="S11" type="hidden" id="S11" size="5" maxlength="5" value="$S11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Melaka23 - $Melaka12</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$T11</strong>
        <input name="T11" type="hidden" id="T11" size="5" maxlength="5" value="$T11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Melaka24</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$U11</strong>
        <input name="U11" type="hidden" id="U11" size="5" maxlength="5" value="$U11" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Keputusan PBN</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Melaka7</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$M11</strong>
        <input name="M11" type="hidden" id="M11" size="5" maxlength="5" value="$M11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Melaka19 - $Melaka8</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$N11</strong>
        <input name="N11" type="hidden" id="N11" size="5" maxlength="5" value="$N11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Melaka20</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$O11</strong>
        <input name="O11" type="hidden" id="O11" size="5" maxlength="5" value="$O11" >
      </div>  </td>
          </tr>
        </table>
    </td>
    <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Menunggu Endorsan Borang K</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Melaka13</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$V11</strong>
        <input name="V11" type="hidden" id="V11" size="5" maxlength="5" value="$V11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Melaka25 - $Melaka14</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$W11</strong>
        <input name="W11" type="hidden" id="W11" size="5" maxlength="5" value="$W11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Melaka26</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$AB11</strong>
        <input name="AB11" type="hidden" id="AB11" size="5" maxlength="5" value="$AB11" >
      </div>  </td>
          </tr>
        </table></td>
  </tr>
</table>
    
    </td>
     <td valign="top"><div align="center" class="style2">
        <strong>$I11</strong>
        <input name="I11" type="hidden" id="I11" size="5" maxlength="5" value="$I11" >
    </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$J11</strong>
        <input name="J11" type="hidden" id="J11" size="5" maxlength="5" value="$J11" >
      </div></td>
      
      <td valign="top"><div align="center" class="style2">
        <strong>$Melaka1</strong>
        <input name="X11" type="hidden" id="X11" size="5" maxlength="5" value="$Melaka1" style="text-align:center" >
      </div> </td>
      
      
    <td valign="top"><div align="center" class="style2">
         <div align="center" id="Y11" >     </div>
        <input name="K11" type="hidden" id="K11" size="5" maxlength="5" value="$K11" >
    </div> </td>
    <td valign="top"><div align="center" class="style2">
        <strong>$L11</strong>
        <input name="L11" type="hidden" id="L11" size="5" maxlength="5" value="$L11" >
      </div></td>
  </tr>
  <tr  bgcolor="black">
    <td valign="top" colspan="14"></td>
  </tr>
  <tr class="row2">
    <td valign="top"><span class="style1">JUMLAH</span></td>
    <td valign="top">
    <div align="center" id="A12" class="style2" >     </div>    </td>
    <td valign="top"><div align="center" id="B12" class="style2" >      
     </div></td>
  
  
  
  <td colspan="6" >
     <div align="center" id="C12" class="style2" style="display:none" >      
     </div>
     <div align="center" id="D12" class="style2" style="display:none" >      
     </div>
     <div align="center" id="E12" class="style2" style="display:none" >      
     </div>
     <div align="center" id="F12" class="style2" style="display:none" >      
     </div>
     <div align="center" id="G12" class="style2" style="display:none" >      
     </div>
     <div align="center" id="H12" class="style2" style="display:none" >      
     </div></td>
  
  
  
      <td valign="top"><div align="center" id="I12" class="style2" >      
     </div></td>
    <td valign="top"><div align="center" id="J12" class="style2" >      
     </div></td>
      <td valign="top"><div align="center" id="X12" class="style2" >      
     </div></td>
    <td valign="top"><div align="center" id="K12" class="style2" >      
     </div></td>
    <td valign="top"><div align="center" id="L12" class="style2" >      
     </div></td>
  </tr>
  <tr  bgcolor="black">
    <td valign="top" colspan="14"></td>
  </tr>
</table>



</fieldset>

 <script type="text/javascript">
 
 totalX();
 
 function totalX()
 {
 
 

 var A12 = parseInt(document.${formName}.A1.value) + parseInt(document.${formName}.A2.value) + parseInt(document.${formName}.A3.value)
 +parseInt(document.${formName}.A4.value) + parseInt(document.${formName}.A5.value) + parseInt(document.${formName}.A6.value)+parseInt(document.${formName}.A7.value)
 +parseInt(document.${formName}.A8.value)+parseInt(document.${formName}.A9.value)+parseInt(document.${formName}.A10.value)+parseInt(document.${formName}.A11.value);
  
 var B12 = parseInt(document.${formName}.B1.value)+parseInt(document.${formName}.B2.value)+parseInt(document.${formName}.B3.value)
 +parseInt(document.${formName}.B4.value)+parseInt(document.${formName}.B5.value)+parseInt(document.${formName}.B6.value)+parseInt(document.${formName}.B7.value)
 +parseInt(document.${formName}.B8.value)+parseInt(document.${formName}.B9.value)+parseInt(document.${formName}.B10.value)+parseInt(document.${formName}.B11.value);
 
 var C12 = parseInt(document.${formName}.C1.value)+parseInt(document.${formName}.C2.value)+parseInt(document.${formName}.C3.value)
 +parseInt(document.${formName}.C4.value)+parseInt(document.${formName}.C5.value)+parseInt(document.${formName}.C6.value)+parseInt(document.${formName}.C7.value)
 +parseInt(document.${formName}.C8.value)+parseInt(document.${formName}.C9.value)+parseInt(document.${formName}.C10.value)+parseInt(document.${formName}.C11.value);
 
 var D12 = parseInt(document.${formName}.D1.value)+parseInt(document.${formName}.D2.value)+parseInt(document.${formName}.D3.value)
 +parseInt(document.${formName}.D4.value)+parseInt(document.${formName}.D5.value)+parseInt(document.${formName}.D6.value)+parseInt(document.${formName}.D7.value)
 +parseInt(document.${formName}.D8.value)+parseInt(document.${formName}.D9.value)+parseInt(document.${formName}.D10.value)+parseInt(document.${formName}.D11.value);
 
 var E12 = parseInt(document.${formName}.E1.value)+parseInt(document.${formName}.E2.value)+parseInt(document.${formName}.E3.value)
 +parseInt(document.${formName}.E4.value)+parseInt(document.${formName}.E5.value)+parseInt(document.${formName}.E6.value)+parseInt(document.${formName}.E7.value)
 +parseInt(document.${formName}.E8.value)+parseInt(document.${formName}.E9.value)+parseInt(document.${formName}.E10.value)+parseInt(document.${formName}.E11.value);
 
 var F12 = parseInt(document.${formName}.F1.value)+parseInt(document.${formName}.F2.value)+parseInt(document.${formName}.F3.value)
 +parseInt(document.${formName}.F4.value)+parseInt(document.${formName}.F5.value)+parseInt(document.${formName}.F6.value)+parseInt(document.${formName}.F7.value)
 +parseInt(document.${formName}.F8.value)+parseInt(document.${formName}.F9.value)+parseInt(document.${formName}.F10.value)+parseInt(document.${formName}.F11.value);
 
 var G12 = parseInt(document.${formName}.G1.value)+parseInt(document.${formName}.G2.value)+parseInt(document.${formName}.G3.value)
 +parseInt(document.${formName}.G4.value)+parseInt(document.${formName}.G5.value)+parseInt(document.${formName}.G6.value)+parseInt(document.${formName}.G7.value)
 +parseInt(document.${formName}.G8.value)+parseInt(document.${formName}.G9.value)+parseInt(document.${formName}.G10.value)+parseInt(document.${formName}.G11.value);
 
 var H12 = parseInt(document.${formName}.H1.value)+parseInt(document.${formName}.H2.value)+parseInt(document.${formName}.H3.value)
 +parseInt(document.${formName}.H4.value)+parseInt(document.${formName}.H5.value)+parseInt(document.${formName}.H6.value)+parseInt(document.${formName}.H7.value)
 +parseInt(document.${formName}.H8.value)+parseInt(document.${formName}.H9.value)+parseInt(document.${formName}.H10.value)+parseInt(document.${formName}.H11.value);
 
 var I12 = parseInt(document.${formName}.I1.value)+parseInt(document.${formName}.I2.value)+parseInt(document.${formName}.I3.value)
 +parseInt(document.${formName}.I4.value)+parseInt(document.${formName}.I5.value)+parseInt(document.${formName}.I6.value)+parseInt(document.${formName}.I7.value)
 +parseInt(document.${formName}.I8.value)+parseInt(document.${formName}.I9.value)+parseInt(document.${formName}.I10.value)+parseInt(document.${formName}.I11.value);
  
 var J12 = parseInt(document.${formName}.J1.value)+parseInt(document.${formName}.J2.value)+parseInt(document.${formName}.J3.value)
 +parseInt(document.${formName}.J4.value)+parseInt(document.${formName}.J5.value)+parseInt(document.${formName}.J6.value)+parseInt(document.${formName}.J7.value)
 +parseInt(document.${formName}.J8.value)+parseInt(document.${formName}.J9.value)+parseInt(document.${formName}.J10.value)+parseInt(document.${formName}.J11.value);
 
 var K12 = parseInt(document.${formName}.K1.value)+parseInt(document.${formName}.K2.value)+parseInt(document.${formName}.K3.value)
 +parseInt(document.${formName}.K4.value)+parseInt(document.${formName}.K5.value)+parseInt(document.${formName}.K6.value)+parseInt(document.${formName}.K7.value)
 +parseInt(document.${formName}.K8.value)+parseInt(document.${formName}.K9.value)+parseInt(document.${formName}.K10.value)+parseInt(document.${formName}.K11.value);
 
 var L12 = parseInt(document.${formName}.L1.value)+parseInt(document.${formName}.L2.value)+parseInt(document.${formName}.L3.value)
 +parseInt(document.${formName}.L4.value)+parseInt(document.${formName}.L5.value)+parseInt(document.${formName}.L6.value)+parseInt(document.${formName}.L7.value)
 +parseInt(document.${formName}.L8.value)+parseInt(document.${formName}.L9.value)+parseInt(document.${formName}.L10.value)+parseInt(document.${formName}.L11.value);
 


 
 $jquery("#A12").html("<font color='blue'><strong>"+A12+"</strong></font>");
 $jquery("#B12").html("<font color='blue'><strong>"+B12+"</strong></font>");
 $jquery("#C12").html("<font color='blue'><strong>"+C12+"</strong></font>");
 $jquery("#D12").html("<font color='blue'><strong>"+D12+"</strong></font>");
 $jquery("#E12").html("<font color='blue'><strong>"+E12+"</strong></font>");
 $jquery("#F12").html("<font color='blue'><strong>"+F12+"</strong></font>");
 $jquery("#G12").html("<font color='blue'><strong>"+G12+"</strong></font>");
 $jquery("#H12").html("<font color='blue'><strong>"+H12+"</strong></font>");
 $jquery("#I12").html("<font color='blue'><strong>"+I12+"</strong></font>");
 $jquery("#J12").html("<font color='blue'><strong>"+J12+"</strong></font>");
// $jquery("#K12").html("<font color='blue'><strong>"+K12+"</strong></font>");
 $jquery("#L12").html("<font color='blue'><strong>"+L12+"</strong></font>");
 
 if(parseInt(document.${formName}.X1.value)>0 && parseInt(document.${formName}.L1.value)>0)
 {
 var Y1 = (parseInt(document.${formName}.X1.value) / parseInt(document.${formName}.L1.value))*100;
 }
 else
 {
 var Y1 = 0;
 }
 
 if(parseInt(document.${formName}.X2.value)>0 && parseInt(document.${formName}.L2.value)>0)
 {
 var Y2 = (parseInt(document.${formName}.X2.value) / parseInt(document.${formName}.L2.value))*100;
 }
 else
 {
 var Y2 = 0;
 }
 
 if(parseInt(document.${formName}.X3.value)>0 && parseInt(document.${formName}.L3.value)>0)
 {
 var Y3 = (parseInt(document.${formName}.X3.value) / parseInt(document.${formName}.L3.value))*100;
 }
 else
 {
 var Y3 = 0;
 }
 
 if(parseInt(document.${formName}.X4.value)>0 && parseInt(document.${formName}.L4.value)>0)
 {
 var Y4 = (parseInt(document.${formName}.X4.value) / parseInt(document.${formName}.L4.value))*100;
 }
 else
 {
 var Y4 = 0;
 }
 
 if(parseInt(document.${formName}.X5.value)>0 && parseInt(document.${formName}.L5.value)>0)
 {
 var Y5 = (parseInt(document.${formName}.X5.value) / parseInt(document.${formName}.L5.value))*100;
 }
 else
 {
 var Y5 = 0;
 }
 
 if(parseInt(document.${formName}.X6.value)>0 && parseInt(document.${formName}.L6.value)>0)
 {
 var Y6 = (parseInt(document.${formName}.X6.value) / parseInt(document.${formName}.L6.value))*100;
 }
 else
 {
 var Y6 = 0;
 }
 
 if(parseInt(document.${formName}.X7.value)>0 && parseInt(document.${formName}.L7.value)>0)
 {
 var Y7 = (parseInt(document.${formName}.X7.value) / parseInt(document.${formName}.L7.value))*100;
 }
 else
 {
 var Y7 = 0;
 }
 
 if(parseInt(document.${formName}.X8.value)>0 && parseInt(document.${formName}.L8.value)>0)
 {
 var Y8 = (parseInt(document.${formName}.X8.value) / parseInt(document.${formName}.L8.value))*100;
 }
 else
 {
 var Y8 = 0;
 }
 
 if(parseInt(document.${formName}.X9.value)>0 && parseInt(document.${formName}.L9.value)>0)
 {
 var Y9 = (parseInt(document.${formName}.X9.value) / parseInt(document.${formName}.L9.value))*100;
 }
 else
 {
 var Y9 = 0;
 }
 
 if(parseInt(document.${formName}.X10.value)>0 && parseInt(document.${formName}.L10.value)>0)
 {
 var Y10 = (parseInt(document.${formName}.X10.value) / parseInt(document.${formName}.L10.value))*100;
 }
 else
 {
 var Y10 = 0;
 }
 
 if(parseInt(document.${formName}.X11.value)>0 && parseInt(document.${formName}.L11.value)>0)
 {
 var Y11 = (parseInt(document.${formName}.X11.value) / parseInt(document.${formName}.L11.value))*100;
 }
 else
 {
 var Y11 = 0;
 }
 
 
 
 $jquery("#Y1").html("<font color='blue'><strong>"+Y1.toFixed(2)+"</strong></font>");
 $jquery("#Y2").html("<font color='blue'><strong>"+Y2.toFixed(2)+"</strong></font>");
 $jquery("#Y3").html("<font color='blue'><strong>"+Y3.toFixed(2)+"</strong></font>");
 $jquery("#Y4").html("<font color='blue'><strong>"+Y4.toFixed(2)+"</strong></font>");
 $jquery("#Y5").html("<font color='blue'><strong>"+Y5.toFixed(2)+"</strong></font>");
 $jquery("#Y6").html("<font color='blue'><strong>"+Y6.toFixed(2)+"</strong></font>");
 $jquery("#Y7").html("<font color='blue'><strong>"+Y7.toFixed(2)+"</strong></font>");
 $jquery("#Y8").html("<font color='blue'><strong>"+Y8.toFixed(2)+"</strong></font>");
 $jquery("#Y9").html("<font color='blue'><strong>"+Y9.toFixed(2)+"</strong></font>");
 $jquery("#Y10").html("<font color='blue'><strong>"+Y10.toFixed(2)+"</strong></font>");
 $jquery("#Y11").html("<font color='blue'><strong>"+Y11.toFixed(2)+"</strong></font>");
 
 
 
 
 }
 
 
 </script>

