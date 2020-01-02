<style type="text/css">
<!--
.style18 {font-family: Arial, Helvetica, sans-serif}
.style21 {font-size: 9px}
.style22 {font-size: 9}
.style26 {font-family: Arial, Helvetica, sans-serif; font-size: 9; color: #000000; }
.style27 {color: #000000}
.style29 {font-family: Arial, Helvetica, sans-serif; font-size: 9px; font-weight: bold; }
.style31 {font-family: Arial, Helvetica, sans-serif; font-size: 9; color: #000000; font-weight: bold; }
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

#set($AC1 = 0)
#set($AC2 = 0)
#set($AC3 = 0)
#set($AC4 = 0)
#set($AC5 = 0)
#set($AC6 = 0)
#set($AC7 = 0)
#set($AC8 = 0)
#set($AC9 = 0)
#set($AC10 = 0)
#set($AC11 = 0)

#set($AD1 = 0)
#set($AD2 = 0)
#set($AD3 = 0)
#set($AD4 = 0)
#set($AD5 = 0)
#set($AD6 = 0)
#set($AD7 = 0)
#set($AD8 = 0)
#set($AD9 = 0)
#set($AD10 = 0)
#set($AD11 = 0)

#set($AE1 = 0)
#set($AE2 = 0)
#set($AE3 = 0)
#set($AE4 = 0)
#set($AE5 = 0)
#set($AE6 = 0)
#set($AE7 = 0)
#set($AE8 = 0)
#set($AE9 = 0)
#set($AE10 = 0)
#set($AE11 = 0)

#set($AF1 = 0)
#set($AF2 = 0)
#set($AF3 = 0)
#set($AF4 = 0)
#set($AF5 = 0)
#set($AF6 = 0)
#set($AF7 = 0)
#set($AF8 = 0)
#set($AF9 = 0)
#set($AF10 = 0)
#set($AF11 = 0)

#set($AG1 = 0)
#set($AG2 = 0)
#set($AG3 = 0)
#set($AG4 = 0)
#set($AG5 = 0)
#set($AG6 = 0)
#set($AG7 = 0)
#set($AG8 = 0)
#set($AG9 = 0)
#set($AG10 = 0)
#set($AG11 = 0)

#set($AH1 = 0)
#set($AH2 = 0)
#set($AH3 = 0)
#set($AH4 = 0)
#set($AH5 = 0)
#set($AH6 = 0)
#set($AH7 = 0)
#set($AH8 = 0)
#set($AH9 = 0)
#set($AH10 = 0)
#set($AH11 = 0)


#set($JUM_1 = 0)
#set($JUM_2 = 0)
#set($JUM_3 = 0)
#set($JUM_4 = 0)
#set($JUM_5 = 0)
#set($JUM_6 = 0)
#set($JUM_7 = 0)
#set($JUM_8 = 0)
#set($JUM_9 = 0)
#set($JUM_10 = 0)
#set($JUM_11 = 0)



#foreach($list in $list_KPI_Penarikan_ByNegeri)

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

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "9")
#set($L1 = $L1 + $list.JUMLAH_HARI_SELESAI)
#set($K1 = $K1 + $list.CEKAP_DALAM)
#end
#end

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "2")
#set($L2 = $L2 + $list.JUMLAH_HARI_SELESAI)
#set($K2 = $K2 + $list.CEKAP_DALAM)
#end
#end

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "7")
#set($L3 = $L3 + $list.JUMLAH_HARI_SELESAI)
#set($K3 = $K3 + $list.CEKAP_DALAM)
#end
#end

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "8")
#set($L4 = $L4 + $list.JUMLAH_HARI_SELESAI)
#set($K4 = $K4 + $list.CEKAP_DALAM)
#end
#end

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "3")
#set($L5 = $L5 + $list.JUMLAH_HARI_SELESAI)
#set($K5 = $K5 + $list.CEKAP_DALAM)
#end
#end

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "11")
#set($L6 = $L6 + $list.JUMLAH_HARI_SELESAI)
#set($K6 = $K6 + $list.CEKAP_DALAM)
#end
#end

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "6")
#set($L7 = $L7 + $list.JUMLAH_HARI_SELESAI)
#set($K7 = $K7 + $list.CEKAP_DALAM)
#end
#end



#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "10")
#set($L8 = $L8 + $list.JUMLAH_HARI_SELESAI)
#set($K8 = $K8 + $list.CEKAP_DALAM)
#end
#end


#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "14")
#set($L9 = $L9 + $list.JUMLAH_HARI_SELESAI)
#set($K9 = $K9 + $list.CEKAP_DALAM)
#end
#end

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "5")
#set($L10 = $L10 + $list.JUMLAH_HARI_SELESAI)
#set($K10 = $K10 + $list.CEKAP_DALAM)
#end
#end

#foreach($list in $list_KPI_Kecekapan_ByNegeri)
#if($list.ID_NEGERI == "4")
#set($L11 = $L11 + $list.JUMLAH_HARI_SELESAI)
#set($K11 = $K11 + $list.CEKAP_DALAM)
#end
#end

<table width="100%" id="table_kpi">
      <tr>
        <td width="100%" ><div align="center"><strong>URUSAN 03.05 : RINGKASAN DI PERINGKAT NEGERI  PENGAMBILAN TANAH SEKSYEN 8</strong></div></td>
      </tr>
      <tr>
        <td width="100%" ><div align="center"><strong>TARIKH MULA : $!txdTarikhMula&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TARIKH AKHIR : $!txdTarikhAkhir</strong></div></td>
      </tr>
    </table>
<fieldset>
<legend>MAKLUMAT KPI</legend>

<table width="100%" class="table_headerkpi"  >
<tr >
    <td width="15%" rowspan="3" class="row2  style29" ><strong>NEGERI</strong></td>
    <td width="5%" rowspan="2" colspan="2" class="row2"><div align="center" class="style29" >Jumlah bil. Permohonan diterima &amp; bil. Lot terlibat</div></td>   
    <td width="70%" colspan="24" class="row2"><div align="center" class="style29" >Menunggu Sokongan Agensi Luar</div></td>
    <td width="10%" colspan="5" class="row2"><div align="center" class="style29" >Jumlah Permohonan Telah Di Selesaikan</div></td>
  </tr>
<tr >      
    <td width="8.75%" colspan="3" rowspan="2"  class="row2"><div align="center" class="style29" >
   Menunggu Laporan Jabatan Teknikal</div></td>    
    <td width="8.75%" colspan="3" class="row2" rowspan="2"><div align="center" class="style29" >
    Menunggu Laporan JPPH
    </div></td>
    <td width="8.75%" colspan="3" rowspan="2"  class="row2"><div align="center" class="style29" >Menunggu Laporan JPBD</div></td>    
    <td width="8.75%" colspan="3" class="row2" rowspan="2"><div align="center" class="style29" >Menunggu Keputusan MMK/MB/KM/LC (PBN)</div></td>
    <td width="8.75%" colspan="3" rowspan="2"  class="row2"><div align="center" class="style29" >Menunggu selesai endorsan DHDK dari PTG/PTD</div></td>    
    <td width="8.75%" colspan="3" class="row2" rowspan="2"><div align="center" class="style29" >Menunggu selesai penandaan kawasan dari PTG/PTD</div></td>
    <td width="8.75%" colspan="3" rowspan="2"  class="row2"><div align="center" class="style29" >Menunggu Pihak Bekepentingan Terima Bayaran Pampasan</div></td>    
    <td width="8.75%" colspan="3" class="row2" rowspan="2"><div align="center" class="style29" >Menunggu endorsan Borang K dari PTD/Pendaftar</div></td>    
    <td width="2.5%" colspan="2" class="row2"><div align="center" class="style29" >Bilangan</div></td>    
    <td width="2.5%" rowspan="2" class="row2"><div align="center" class="style29" >Sasaran </div></td>   
    <td width="2.5%" rowspan="2" class="row2"><div align="center" class="style29" >Kecekapan dalaman Unit JKPTG (%)</div></td>
    <td width="2.5%" rowspan="2" class="row2"><div align="center" class="style29" >Purata Kitaran Masa (hari)</div></td>
  </tr>
  <tr >
    
    <td  class="row2"><div align="center" class="style29" >#Per</div></td>
    <td  class="row2"><div align="center" class="style29" >#Lot</div></td>   
    <td  class="row2"><div align="center" class="style29" >#Per</div></td>
    <td  class="row2"><div align="center" class="style29" >#Lot</div></td>
  </tr>
  
  
  <tr class="row1">
  <tr class="row1">
    <td>&nbsp;</td>
    <td><span class="style27"></span></td>
    <td><span class="style27"></span></td>
    
  #set($SST1_1 = 0)
  #set($SST2_1 = 0)
  #set($SST3_1 = 0)
  #set($SST4_1 = 0)
  #set($SST5_1 = 0)
  #set($SST6_1 = 0)
  #set($SST7_1 = 0)
  #set($SST8_1 = 0)
  #set($SST9_1 = 0)
  #set($SST10_1 = 0) 
  #set($SST11_1 = 0)
  #set($SST12_1 = 0)
  #set($SST13_1 = 0)
  #set($SST14_1 = 0)
  #set($SST15_1 = 0)
  #set($SST16_1 = 0)  
  
  
  #set($SX1_1 = 0)
  #set($SX2_1 = 0)
  #set($SX3_1 = 0)
  #set($SX4_1 = 0)
  #set($SX5_1 = 0)
  #set($SX6_1 = 0)
  #set($SX7_1 = 0)
  #set($SX8_1 = 0)
  #set($SX9_1 = 0)
  #set($SX10_1 = 0) 
  #set($SX11_1 = 0)
  #set($SX12_1 = 0)
  #set($SX13_1 = 0)
  #set($SX14_1 = 0)
  #set($SX15_1 = 0)
  #set($SX16_1 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "9")
  #set($JUM_1 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_1 = $list.F11)
  #set($SST2_1 = $list.F12)
  #set($SST3_1 = $list.F13)
  #set($SST4_1 = $list.F14)
  #set($SST5_1 = $list.F15)
  #set($SST6_1 = $list.F16)
  #set($SST7_1 = $list.F17)
  #set($SST8_1 = $list.F18)
  #set($SST9_1 = $list.F19)
  #set($SST10_1 = $list.F20) 
  #set($SST11_1 = $list.F21)
  #set($SST12_1 = $list.F22)
  #set($SST13_1 = $list.F23)
  #set($SST14_1 = $list.F24)
  #set($SST15_1 = $list.F25)
  #set($SST16_1 = $list.F26)  
  
  
  #set($SX1_1 = $list.F11+1)
  #set($SX2_1 = $list.F12+1)
  #set($SX3_1 = $list.F13+1)
  #set($SX4_1 = $list.F14+1)
  #set($SX5_1 = $list.F15+1)
  #set($SX6_1 = $list.F16+1)
  #set($SX7_1 = $list.F17+1)
  #set($SX8_1 = $list.F18+1)
  #set($SX9_1 = $list.F19+1)
  #set($SX10_1 = $list.F20+1) 
  #set($SX11_1 = $list.F21+1)
  #set($SX12_1 = $list.F22+1)
  #set($SX13_1 = $list.F23+1)
  #set($SX14_1 = $list.F24+1)
  #set($SX15_1 = $list.F25+1)
  #set($SX16_1 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_1</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_1 - $SST2_1</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_1</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_1</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_1 - $SST4_1</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_1</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_1</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_1 - $SST6_1</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_1</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_1</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_1 - $SST8_1</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_1</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_1</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_1 - $SST10_1</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_1</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_1</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_1 - $SST12_1</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_1</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_1</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_1 - $SST14_1</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_1</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_1</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_1 - $SST16_1</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_1</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row1">
    <td><span class="style31"  >PERLIS</span></td>
    <td><div align="center" class="style26"  >
        $A1
        <input name="A1" type="hidden" id="A1" size="5" maxlength="5" value="$A1" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B1
        <input name="B1" type="hidden" id="B1" size="5" maxlength="5" value="$B1" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C1
        <input name="C1" type="hidden" id="C1" size="5" maxlength="5" value="$C1" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D1
        <input name="D1" type="hidden" id="D1" size="5" maxlength="5" value="$D1" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E1
        <input name="E1" type="hidden" id="E1" size="5" maxlength="5" value="$E1" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F1
        <input name="F1" type="hidden" id="F1" size="5" maxlength="5" value="$F1" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G1
        <input name="G1" type="hidden" id="G1" size="5" maxlength="5" value="$G1" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H1
        <input name="H1" type="hidden" id="H1" size="5" maxlength="5" value="$H1" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M1
        <input name="M1" type="hidden" id="M1" size="5" maxlength="5" value="$M1" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N1
        <input name="N1" type="hidden" id="N1" size="5" maxlength="5" value="$N1" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O1
        <input name="O1" type="hidden" id="O1" size="5" maxlength="5" value="$O1" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P1
        <input name="P1" type="hidden" id="P1" size="5" maxlength="5" value="$P1" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q1
        <input name="Q1" type="hidden" id="Q1" size="5" maxlength="5" value="$Q1" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R1
        <input name="R1" type="hidden" id="R1" size="5" maxlength="5" value="$R1" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S1
        <input name="S1" type="hidden" id="S1" size="5" maxlength="5" value="$S1" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T1
        <input name="T1" type="hidden" id="T1" size="5" maxlength="5" value="$T1" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U1
        <input name="U1" type="hidden" id="U1" size="5" maxlength="5" value="$U1" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V1
        <input name="V1" type="hidden" id="V1" size="5" maxlength="5" value="$V1" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W1
        <input name="W1" type="hidden" id="W1" size="5" maxlength="5" value="$W1" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB1
        <input name="AB1" type="hidden" id="AB1" size="5" maxlength="5" value="$AB1" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC1
        <input name="AC1" type="hidden" id="AC1" size="5" maxlength="5" value="$AC1" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD1
        <input name="AD1" type="hidden" id="AD1" size="5" maxlength="5" value="$AD1" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE1
        <input name="AE1" type="hidden" id="AE1" size="5" maxlength="5" value="$AE1" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF1
        <input name="AF1" type="hidden" id="AF1" size="5" maxlength="5" value="$AF1" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG1
        <input name="AG1" type="hidden" id="AG1" size="5" maxlength="5" value="$AG1" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH1
        <input name="AH1" type="hidden" id="AH1" size="5" maxlength="5" value="$AH1" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I1
        <input name="I1" type="hidden" id="I1" size="5" maxlength="5" value="$I1" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J1
        <input name="J1" type="hidden" id="J1" size="5" maxlength="5" value="$J1" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_1
        <input name="X1" type="hidden" id="X1" size="5" maxlength="5" value="$JUM_1"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y1" >     </div>
        <input name="K1" type="hidden" id="K1" size="5" maxlength="5" value="$K1" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L1
        <input name="L1" type="hidden" id="L1" size="5" maxlength="5" value="$L1" >
      </div></td>
  </tr>
  </tr>
  
  <tr class="row2">
    <td>&nbsp;</td>
    <td><span class="style27"></span></td>
    <td><span class="style27"></span></td>
    
  #set($SST1_2 = 0)
  #set($SST2_2 = 0)
  #set($SST3_2 = 0)
  #set($SST4_2 = 0)
  #set($SST5_2 = 0)
  #set($SST6_2 = 0)
  #set($SST7_2 = 0)
  #set($SST8_2 = 0)
  #set($SST9_2 = 0)
  #set($SST10_2 = 0) 
  #set($SST11_2 = 0)
  #set($SST12_2 = 0)
  #set($SST13_2 = 0)
  #set($SST14_2 = 0)
  #set($SST15_2 = 0)
  #set($SST16_2 = 0)  
  
  
  #set($SX1_2 = 0)
  #set($SX2_2 = 0)
  #set($SX3_2 = 0)
  #set($SX4_2 = 0)
  #set($SX5_2 = 0)
  #set($SX6_2 = 0)
  #set($SX7_2 = 0)
  #set($SX8_2 = 0)
  #set($SX9_2 = 0)
  #set($SX10_2 = 0) 
  #set($SX11_2 = 0)
  #set($SX12_2 = 0)
  #set($SX13_2 = 0)
  #set($SX14_2 = 0)
  #set($SX15_2 = 0)
  #set($SX16_2 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "2")
  #set($JUM_2 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_2 = $list.F11)
  #set($SST2_2 = $list.F12)
  #set($SST3_2 = $list.F13)
  #set($SST4_2 = $list.F14)
  #set($SST5_2 = $list.F15)
  #set($SST6_2 = $list.F16)
  #set($SST7_2 = $list.F17)
  #set($SST8_2 = $list.F18)
  #set($SST9_2 = $list.F19)
  #set($SST10_2 = $list.F20) 
  #set($SST11_2 = $list.F21)
  #set($SST12_2 = $list.F22)
  #set($SST13_2 = $list.F23)
  #set($SST14_2 = $list.F24)
  #set($SST15_2 = $list.F25)
  #set($SST16_2 = $list.F26)  
  
  
  #set($SX1_2 = $list.F11+1)
  #set($SX2_2 = $list.F12+1)
  #set($SX3_2 = $list.F13+1)
  #set($SX4_2 = $list.F14+1)
  #set($SX5_2 = $list.F15+1)
  #set($SX6_2 = $list.F16+1)
  #set($SX7_2 = $list.F17+1)
  #set($SX8_2 = $list.F18+1)
  #set($SX9_2 = $list.F19+1)
  #set($SX10_2 = $list.F20+1) 
  #set($SX11_2 = $list.F21+1)
  #set($SX12_2 = $list.F22+1)
  #set($SX13_2 = $list.F23+1)
  #set($SX14_2 = $list.F24+1)
  #set($SX15_2 = $list.F25+1)
  #set($SX16_2 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_2</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_2 - $SST2_2</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_2</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_2</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_2 - $SST4_2</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_2</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_2</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_2 - $SST6_2</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_2</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_2</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_2 - $SST8_2</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_2</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_2</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_2 - $SST10_2</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_2</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_2</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_2 - $SST12_2</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_2</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_2</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_2 - $SST14_2</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_2</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_2</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_2 - $SST16_2</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_2</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row2">
    <td><span class="style31"  >KEDAH</span></td>
    <td><div align="center" class="style26"  >
        $A2
        <input name="A2" type="hidden" id="A2" size="5" maxlength="5" value="$A2" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B2
        <input name="B2" type="hidden" id="B2" size="5" maxlength="5" value="$B2" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C2
        <input name="C2" type="hidden" id="C2" size="5" maxlength="5" value="$C2" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D2
        <input name="D2" type="hidden" id="D2" size="5" maxlength="5" value="$D2" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E2
        <input name="E2" type="hidden" id="E2" size="5" maxlength="5" value="$E2" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F2
        <input name="F2" type="hidden" id="F2" size="5" maxlength="5" value="$F2" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G2
        <input name="G2" type="hidden" id="G2" size="5" maxlength="5" value="$G2" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H2
        <input name="H2" type="hidden" id="H2" size="5" maxlength="5" value="$H2" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M2
        <input name="M2" type="hidden" id="M2" size="5" maxlength="5" value="$M2" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N2
        <input name="N2" type="hidden" id="N2" size="5" maxlength="5" value="$N2" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O2
        <input name="O2" type="hidden" id="O2" size="5" maxlength="5" value="$O2" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P2
        <input name="P2" type="hidden" id="P2" size="5" maxlength="5" value="$P2" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q2
        <input name="Q2" type="hidden" id="Q2" size="5" maxlength="5" value="$Q2" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R2
        <input name="R2" type="hidden" id="R2" size="5" maxlength="5" value="$R2" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S2
        <input name="S2" type="hidden" id="S2" size="5" maxlength="5" value="$S2" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T2
        <input name="T2" type="hidden" id="T2" size="5" maxlength="5" value="$T2" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U2
        <input name="U2" type="hidden" id="U2" size="5" maxlength="5" value="$U2" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V2
        <input name="V2" type="hidden" id="V2" size="5" maxlength="5" value="$V2" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W2
        <input name="W2" type="hidden" id="W2" size="5" maxlength="5" value="$W2" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB2
        <input name="AB2" type="hidden" id="AB2" size="5" maxlength="5" value="$AB2" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC2
        <input name="AC2" type="hidden" id="AC2" size="5" maxlength="5" value="$AC2" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD2
        <input name="AD2" type="hidden" id="AD2" size="5" maxlength="5" value="$AD2" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE2
        <input name="AE2" type="hidden" id="AE2" size="5" maxlength="5" value="$AE2" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF2
        <input name="AF2" type="hidden" id="AF2" size="5" maxlength="5" value="$AF2" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG2
        <input name="AG2" type="hidden" id="AG2" size="5" maxlength="5" value="$AG2" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH2
        <input name="AH2" type="hidden" id="AH2" size="5" maxlength="5" value="$AH2" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I2
        <input name="I2" type="hidden" id="I2" size="5" maxlength="5" value="$I2" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J2
        <input name="J2" type="hidden" id="J2" size="5" maxlength="5" value="$J2" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_2
        <input name="X2" type="hidden" id="X2" size="5" maxlength="5" value="$JUM_2"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y2" >     </div>
        <input name="K2" type="hidden" id="K2" size="5" maxlength="5" value="$K2" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L2
        <input name="L2" type="hidden" id="L2" size="5" maxlength="5" value="$L2" >
      </div></td>
  </tr>
  
  <tr class="row1">
    <td>&nbsp;</td>
    <td><span class="style27"></span></td>
    <td><span class="style27"></span></td>
    
  #set($SST1_3 = 0)
  #set($SST2_3 = 0)
  #set($SST3_3 = 0)
  #set($SST4_3 = 0)
  #set($SST5_3 = 0)
  #set($SST6_3 = 0)
  #set($SST7_3 = 0)
  #set($SST8_3 = 0)
  #set($SST9_3 = 0)
  #set($SST10_3 = 0) 
  #set($SST11_3 = 0)
  #set($SST12_3 = 0)
  #set($SST13_3 = 0)
  #set($SST14_3 = 0)
  #set($SST15_3 = 0)
  #set($SST16_3 = 0)  
  
  
  #set($SX1_3 = 0)
  #set($SX2_3 = 0)
  #set($SX3_3 = 0)
  #set($SX4_3 = 0)
  #set($SX5_3 = 0)
  #set($SX6_3 = 0)
  #set($SX7_3 = 0)
  #set($SX8_3 = 0)
  #set($SX9_3 = 0)
  #set($SX10_3 = 0) 
  #set($SX11_3 = 0)
  #set($SX12_3 = 0)
  #set($SX13_3 = 0)
  #set($SX14_3 = 0)
  #set($SX15_3 = 0)
  #set($SX16_3 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "7")
  #set($JUM_3 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_3 = $list.F11)
  #set($SST2_3 = $list.F12)
  #set($SST3_3 = $list.F13)
  #set($SST4_3 = $list.F14)
  #set($SST5_3 = $list.F15)
  #set($SST6_3 = $list.F16)
  #set($SST7_3 = $list.F17)
  #set($SST8_3 = $list.F18)
  #set($SST9_3 = $list.F19)
  #set($SST10_3 = $list.F20) 
  #set($SST11_3 = $list.F21)
  #set($SST12_3 = $list.F22)
  #set($SST13_3 = $list.F23)
  #set($SST14_3 = $list.F24)
  #set($SST15_3 = $list.F25)
  #set($SST16_3 = $list.F26)  
  
  
  #set($SX1_3 = $list.F11+1)
  #set($SX2_3 = $list.F12+1)
  #set($SX3_3 = $list.F13+1)
  #set($SX4_3 = $list.F14+1)
  #set($SX5_3 = $list.F15+1)
  #set($SX6_3 = $list.F16+1)
  #set($SX7_3 = $list.F17+1)
  #set($SX8_3 = $list.F18+1)
  #set($SX9_3 = $list.F19+1)
  #set($SX10_3 = $list.F20+1) 
  #set($SX11_3 = $list.F21+1)
  #set($SX12_3 = $list.F22+1)
  #set($SX13_3 = $list.F23+1)
  #set($SX14_3 = $list.F24+1)
  #set($SX15_3 = $list.F25+1)
  #set($SX16_3 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_3</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_3 - $SST2_3</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_3</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_3</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_3 - $SST4_3</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_3</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_3</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_3 - $SST6_3</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_3</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_3</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_3 - $SST8_3</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_3</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_3</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_3 - $SST10_3</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_3</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_3</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_3 - $SST12_3</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_3</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_3</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_3 - $SST14_3</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_3</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_3</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_3 - $SST16_3</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_3</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row1">
    <td><span class="style31"  >PULAU PINANG</span></td>
    <td><div align="center" class="style26"  >
        $A3
        <input name="A3" type="hidden" id="A3" size="5" maxlength="5" value="$A3" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B3
        <input name="B3" type="hidden" id="B3" size="5" maxlength="5" value="$B3" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C3
        <input name="C3" type="hidden" id="C3" size="5" maxlength="5" value="$C3" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D3
        <input name="D3" type="hidden" id="D3" size="5" maxlength="5" value="$D3" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E3
        <input name="E3" type="hidden" id="E3" size="5" maxlength="5" value="$E3" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F3
        <input name="F3" type="hidden" id="F3" size="5" maxlength="5" value="$F3" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G3
        <input name="G3" type="hidden" id="G3" size="5" maxlength="5" value="$G3" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H3
        <input name="H3" type="hidden" id="H3" size="5" maxlength="5" value="$H3" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M3
        <input name="M3" type="hidden" id="M3" size="5" maxlength="5" value="$M3" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N3
        <input name="N3" type="hidden" id="N3" size="5" maxlength="5" value="$N3" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O3
        <input name="O3" type="hidden" id="O3" size="5" maxlength="5" value="$O3" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P3
        <input name="P3" type="hidden" id="P3" size="5" maxlength="5" value="$P3" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q3
        <input name="Q3" type="hidden" id="Q3" size="5" maxlength="5" value="$Q3" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R3
        <input name="R3" type="hidden" id="R3" size="5" maxlength="5" value="$R3" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S3
        <input name="S3" type="hidden" id="S3" size="5" maxlength="5" value="$S3" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T3
        <input name="T3" type="hidden" id="T3" size="5" maxlength="5" value="$T3" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U3
        <input name="U3" type="hidden" id="U3" size="5" maxlength="5" value="$U3" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V3
        <input name="V3" type="hidden" id="V3" size="5" maxlength="5" value="$V3" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W3
        <input name="W3" type="hidden" id="W3" size="5" maxlength="5" value="$W3" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB3
        <input name="AB3" type="hidden" id="AB3" size="5" maxlength="5" value="$AB3" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC3
        <input name="AC3" type="hidden" id="AC3" size="5" maxlength="5" value="$AC3" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD3
        <input name="AD3" type="hidden" id="AD3" size="5" maxlength="5" value="$AD3" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE3
        <input name="AE3" type="hidden" id="AE3" size="5" maxlength="5" value="$AE3" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF3
        <input name="AF3" type="hidden" id="AF3" size="5" maxlength="5" value="$AF3" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG3
        <input name="AG3" type="hidden" id="AG3" size="5" maxlength="5" value="$AG3" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH3
        <input name="AH3" type="hidden" id="AH3" size="5" maxlength="5" value="$AH3" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I3
        <input name="I3" type="hidden" id="I3" size="5" maxlength="5" value="$I3" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J3
        <input name="J3" type="hidden" id="J3" size="5" maxlength="5" value="$J3" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_3
        <input name="X3" type="hidden" id="X3" size="5" maxlength="5" value="$JUM_3"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y3" >     </div>
        <input name="K3" type="hidden" id="K3" size="5" maxlength="5" value="$K3" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L3
        <input name="L3" type="hidden" id="L3" size="5" maxlength="5" value="$L3" >
      </div></td>
  </tr>
  
  <tr class="row2">
    <td>&nbsp;</td>
    <td><span class="style27"></span></td>
    <td><span class="style27"></span></td>
    
  #set($SST1_4 = 0)
  #set($SST2_4 = 0)
  #set($SST3_4 = 0)
  #set($SST4_4 = 0)
  #set($SST5_4 = 0)
  #set($SST6_4 = 0)
  #set($SST7_4 = 0)
  #set($SST8_4 = 0)
  #set($SST9_4 = 0)
  #set($SST10_4 = 0) 
  #set($SST11_4 = 0)
  #set($SST12_4 = 0)
  #set($SST13_4 = 0)
  #set($SST14_4 = 0)
  #set($SST15_4 = 0)
  #set($SST16_4 = 0)  
  
  
  #set($SX1_4 = 0)
  #set($SX2_4 = 0)
  #set($SX3_4 = 0)
  #set($SX4_4 = 0)
  #set($SX5_4 = 0)
  #set($SX6_4 = 0)
  #set($SX7_4 = 0)
  #set($SX8_4 = 0)
  #set($SX9_4 = 0)
  #set($SX10_4 = 0) 
  #set($SX11_4 = 0)
  #set($SX12_4 = 0)
  #set($SX13_4 = 0)
  #set($SX14_4 = 0)
  #set($SX15_4 = 0)
  #set($SX16_4 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "8")
  #set($JUM_4 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_4 = $list.F11)
  #set($SST2_4 = $list.F12)
  #set($SST3_4 = $list.F13)
  #set($SST4_4 = $list.F14)
  #set($SST5_4 = $list.F15)
  #set($SST6_4 = $list.F16)
  #set($SST7_4 = $list.F17)
  #set($SST8_4 = $list.F18)
  #set($SST9_4 = $list.F19)
  #set($SST10_4 = $list.F20) 
  #set($SST11_4 = $list.F21)
  #set($SST12_4 = $list.F22)
  #set($SST13_4 = $list.F23)
  #set($SST14_4 = $list.F24)
  #set($SST15_4 = $list.F25)
  #set($SST16_4 = $list.F26)  
  
  
  #set($SX1_4 = $list.F11+1)
  #set($SX2_4 = $list.F12+1)
  #set($SX3_4 = $list.F13+1)
  #set($SX4_4 = $list.F14+1)
  #set($SX5_4 = $list.F15+1)
  #set($SX6_4 = $list.F16+1)
  #set($SX7_4 = $list.F17+1)
  #set($SX8_4 = $list.F18+1)
  #set($SX9_4 = $list.F19+1)
  #set($SX10_4 = $list.F20+1) 
  #set($SX11_4 = $list.F21+1)
  #set($SX12_4 = $list.F22+1)
  #set($SX13_4 = $list.F23+1)
  #set($SX14_4 = $list.F24+1)
  #set($SX15_4 = $list.F25+1)
  #set($SX16_4 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_4</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_4 - $SST2_4</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_4</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_4</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_4 - $SST4_4</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_4</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_4</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_4 - $SST6_4</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_4</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_4</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_4 - $SST8_4</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_4</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_4</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_4 - $SST10_4</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_4</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_4</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_4 - $SST12_4</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_4</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_4</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_4 - $SST14_4</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_4</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_4</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_4 - $SST16_4</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_4</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row2">
    <td><span class="style31"  >PERAK</span></td>
    <td><div align="center" class="style26"  >
        $A4
        <input name="A4" type="hidden" id="A4" size="5" maxlength="5" value="$A4" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B4
        <input name="B4" type="hidden" id="B4" size="5" maxlength="5" value="$B4" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C4
        <input name="C4" type="hidden" id="C4" size="5" maxlength="5" value="$C4" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D4
        <input name="D4" type="hidden" id="D4" size="5" maxlength="5" value="$D4" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E4
        <input name="E4" type="hidden" id="E4" size="5" maxlength="5" value="$E4" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F4
        <input name="F4" type="hidden" id="F4" size="5" maxlength="5" value="$F4" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G4
        <input name="G4" type="hidden" id="G4" size="5" maxlength="5" value="$G4" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H4
        <input name="H4" type="hidden" id="H4" size="5" maxlength="5" value="$H4" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M4
        <input name="M4" type="hidden" id="M4" size="5" maxlength="5" value="$M4" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N4
        <input name="N4" type="hidden" id="N4" size="5" maxlength="5" value="$N4" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O4
        <input name="O4" type="hidden" id="O4" size="5" maxlength="5" value="$O4" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P4
        <input name="P4" type="hidden" id="P4" size="5" maxlength="5" value="$P4" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q4
        <input name="Q4" type="hidden" id="Q4" size="5" maxlength="5" value="$Q4" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R4
        <input name="R4" type="hidden" id="R4" size="5" maxlength="5" value="$R4" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S4
        <input name="S4" type="hidden" id="S4" size="5" maxlength="5" value="$S4" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T4
        <input name="T4" type="hidden" id="T4" size="5" maxlength="5" value="$T4" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U4
        <input name="U4" type="hidden" id="U4" size="5" maxlength="5" value="$U4" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V4
        <input name="V4" type="hidden" id="V4" size="5" maxlength="5" value="$V4" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W4
        <input name="W4" type="hidden" id="W4" size="5" maxlength="5" value="$W4" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB4
        <input name="AB4" type="hidden" id="AB4" size="5" maxlength="5" value="$AB4" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC4
        <input name="AC4" type="hidden" id="AC4" size="5" maxlength="5" value="$AC4" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD4
        <input name="AD4" type="hidden" id="AD4" size="5" maxlength="5" value="$AD4" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE4
        <input name="AE4" type="hidden" id="AE4" size="5" maxlength="5" value="$AE4" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF4
        <input name="AF4" type="hidden" id="AF4" size="5" maxlength="5" value="$AF4" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG4
        <input name="AG4" type="hidden" id="AG4" size="5" maxlength="5" value="$AG4" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH4
        <input name="AH4" type="hidden" id="AH4" size="5" maxlength="5" value="$AH4" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I4
        <input name="I4" type="hidden" id="I4" size="5" maxlength="5" value="$I4" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J4
        <input name="J4" type="hidden" id="J4" size="5" maxlength="5" value="$J4" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_4
        <input name="X4" type="hidden" id="X4" size="5" maxlength="5" value="$JUM_4"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y4" >     </div>
        <input name="K4" type="hidden" id="K4" size="5" maxlength="5" value="$K4" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L4
        <input name="L4" type="hidden" id="L4" size="5" maxlength="5" value="$L4" >
      </div></td>
  </tr>
  
  
  <tr class="row1">
    <td>&nbsp;</td>
    <td><span class="style27"></span></td>
    <td><span class="style27"></span></td>
    
  #set($SST1_5 = 0)
  #set($SST2_5 = 0)
  #set($SST3_5 = 0)
  #set($SST4_5 = 0)
  #set($SST5_5 = 0)
  #set($SST6_5 = 0)
  #set($SST7_5 = 0)
  #set($SST8_5 = 0)
  #set($SST9_5 = 0)
  #set($SST10_5 = 0) 
  #set($SST11_5 = 0)
  #set($SST12_5 = 0)
  #set($SST13_5 = 0)
  #set($SST14_5 = 0)
  #set($SST15_5 = 0)
  #set($SST16_5 = 0)  
  
  
  #set($SX1_5 = 0)
  #set($SX2_5 = 0)
  #set($SX3_5 = 0)
  #set($SX4_5 = 0)
  #set($SX5_5 = 0)
  #set($SX6_5 = 0)
  #set($SX7_5 = 0)
  #set($SX8_5 = 0)
  #set($SX9_5 = 0)
  #set($SX10_5 = 0) 
  #set($SX11_5 = 0)
  #set($SX12_5 = 0)
  #set($SX13_5 = 0)
  #set($SX14_5 = 0)
  #set($SX15_5 = 0)
  #set($SX16_5 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "3")
  #set($JUM_5 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_5 = $list.F11)
  #set($SST2_5 = $list.F12)
  #set($SST3_5 = $list.F13)
  #set($SST4_5 = $list.F14)
  #set($SST5_5 = $list.F15)
  #set($SST6_5 = $list.F16)
  #set($SST7_5 = $list.F17)
  #set($SST8_5 = $list.F18)
  #set($SST9_5 = $list.F19)
  #set($SST10_5 = $list.F20) 
  #set($SST11_5 = $list.F21)
  #set($SST12_5 = $list.F22)
  #set($SST13_5 = $list.F23)
  #set($SST14_5 = $list.F24)
  #set($SST15_5 = $list.F25)
  #set($SST16_5 = $list.F26)  
  
  
  #set($SX1_5 = $list.F11+1)
  #set($SX2_5 = $list.F12+1)
  #set($SX3_5 = $list.F13+1)
  #set($SX4_5 = $list.F14+1)
  #set($SX5_5 = $list.F15+1)
  #set($SX6_5 = $list.F16+1)
  #set($SX7_5 = $list.F17+1)
  #set($SX8_5 = $list.F18+1)
  #set($SX9_5 = $list.F19+1)
  #set($SX10_5 = $list.F20+1) 
  #set($SX11_5 = $list.F21+1)
  #set($SX12_5 = $list.F22+1)
  #set($SX13_5 = $list.F23+1)
  #set($SX14_5 = $list.F24+1)
  #set($SX15_5 = $list.F25+1)
  #set($SX16_5 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_5</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_5 - $SST2_5</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_5</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_5</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_5 - $SST4_5</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_5</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_5</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_5 - $SST6_5</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_5</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_5</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_5 - $SST8_5</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_5</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_5</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_5 - $SST10_5</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_5</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_5</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_5 - $SST12_5</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_5</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_5</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_5 - $SST14_5</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_5</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_5</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_5 - $SST16_5</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_5</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row1">
    <td><span class="style31"  >KELANTAN</span></td>
    <td><div align="center" class="style26"  >
        $A5
        <input name="A5" type="hidden" id="A5" size="5" maxlength="5" value="$A5" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B5
        <input name="B5" type="hidden" id="B5" size="5" maxlength="5" value="$B5" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C5
        <input name="C5" type="hidden" id="C5" size="5" maxlength="5" value="$C5" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D5
        <input name="D5" type="hidden" id="D5" size="5" maxlength="5" value="$D5" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E5
        <input name="E5" type="hidden" id="E5" size="5" maxlength="5" value="$E5" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F5
        <input name="F5" type="hidden" id="F5" size="5" maxlength="5" value="$F5" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G5
        <input name="G5" type="hidden" id="G5" size="5" maxlength="5" value="$G5" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H5
        <input name="H5" type="hidden" id="H5" size="5" maxlength="5" value="$H5" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M5
        <input name="M5" type="hidden" id="M5" size="5" maxlength="5" value="$M5" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N5
        <input name="N5" type="hidden" id="N5" size="5" maxlength="5" value="$N5" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O5
        <input name="O5" type="hidden" id="O5" size="5" maxlength="5" value="$O5" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P5
        <input name="P5" type="hidden" id="P5" size="5" maxlength="5" value="$P5" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q5
        <input name="Q5" type="hidden" id="Q5" size="5" maxlength="5" value="$Q5" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R5
        <input name="R5" type="hidden" id="R5" size="5" maxlength="5" value="$R5" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S5
        <input name="S5" type="hidden" id="S5" size="5" maxlength="5" value="$S5" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T5
        <input name="T5" type="hidden" id="T5" size="5" maxlength="5" value="$T5" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U5
        <input name="U5" type="hidden" id="U5" size="5" maxlength="5" value="$U5" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V5
        <input name="V5" type="hidden" id="V5" size="5" maxlength="5" value="$V5" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W5
        <input name="W5" type="hidden" id="W5" size="5" maxlength="5" value="$W5" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB5
        <input name="AB5" type="hidden" id="AB5" size="5" maxlength="5" value="$AB5" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC5
        <input name="AC5" type="hidden" id="AC5" size="5" maxlength="5" value="$AC5" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD5
        <input name="AD5" type="hidden" id="AD5" size="5" maxlength="5" value="$AD5" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE5
        <input name="AE5" type="hidden" id="AE5" size="5" maxlength="5" value="$AE5" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF5
        <input name="AF5" type="hidden" id="AF5" size="5" maxlength="5" value="$AF5" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG5
        <input name="AG5" type="hidden" id="AG5" size="5" maxlength="5" value="$AG5" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH5
        <input name="AH5" type="hidden" id="AH5" size="5" maxlength="5" value="$AH5" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I5
        <input name="I5" type="hidden" id="I5" size="5" maxlength="5" value="$I5" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J5
        <input name="J5" type="hidden" id="J5" size="5" maxlength="5" value="$J5" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_5
        <input name="X5" type="hidden" id="X5" size="5" maxlength="5" value="$JUM_5"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y5" >     </div>
        <input name="K5" type="hidden" id="K5" size="5" maxlength="5" value="$K5" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L5
        <input name="L5" type="hidden" id="L5" size="5" maxlength="5" value="$L5" >
      </div></td>
  </tr>
  
  
  <tr class="row2">
    <td>&nbsp;</td>
    <td><span class="style27"></span></td>
    <td><span class="style27"></span></td>
    
  #set($SST1_6 = 0)
  #set($SST2_6 = 0)
  #set($SST3_6 = 0)
  #set($SST4_6 = 0)
  #set($SST5_6 = 0)
  #set($SST6_6 = 0)
  #set($SST7_6 = 0)
  #set($SST8_6 = 0)
  #set($SST9_6 = 0)
  #set($SST10_6 = 0) 
  #set($SST11_6 = 0)
  #set($SST12_6 = 0)
  #set($SST13_6 = 0)
  #set($SST14_6 = 0)
  #set($SST15_6 = 0)
  #set($SST16_6 = 0)  
  
  
  #set($SX1_6 = 0)
  #set($SX2_6 = 0)
  #set($SX3_6 = 0)
  #set($SX4_6 = 0)
  #set($SX5_6 = 0)
  #set($SX6_6 = 0)
  #set($SX7_6 = 0)
  #set($SX8_6 = 0)
  #set($SX9_6 = 0)
  #set($SX10_6 = 0) 
  #set($SX11_6 = 0)
  #set($SX12_6 = 0)
  #set($SX13_6 = 0)
  #set($SX14_6 = 0)
  #set($SX15_6 = 0)
  #set($SX16_6 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "11")
  #set($JUM_6 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_6 = $list.F11)
  #set($SST2_6 = $list.F12)
  #set($SST3_6 = $list.F13)
  #set($SST4_6 = $list.F14)
  #set($SST5_6 = $list.F15)
  #set($SST6_6 = $list.F16)
  #set($SST7_6 = $list.F17)
  #set($SST8_6 = $list.F18)
  #set($SST9_6 = $list.F19)
  #set($SST10_6 = $list.F20) 
  #set($SST11_6 = $list.F21)
  #set($SST12_6 = $list.F22)
  #set($SST13_6 = $list.F23)
  #set($SST14_6 = $list.F24)
  #set($SST15_6 = $list.F25)
  #set($SST16_6 = $list.F26)  
  
  
  #set($SX1_6 = $list.F11+1)
  #set($SX2_6 = $list.F12+1)
  #set($SX3_6 = $list.F13+1)
  #set($SX4_6 = $list.F14+1)
  #set($SX5_6 = $list.F15+1)
  #set($SX6_6 = $list.F16+1)
  #set($SX7_6 = $list.F17+1)
  #set($SX8_6 = $list.F18+1)
  #set($SX9_6 = $list.F19+1)
  #set($SX10_6 = $list.F20+1) 
  #set($SX11_6 = $list.F21+1)
  #set($SX12_6 = $list.F22+1)
  #set($SX13_6 = $list.F23+1)
  #set($SX14_6 = $list.F24+1)
  #set($SX15_6 = $list.F25+1)
  #set($SX16_6 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_6</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_6 - $SST2_6</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_6</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_6</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_6 - $SST4_6</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_6</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_6</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_6 - $SST6_6</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_6</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_6</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_6 - $SST8_6</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_6</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_6</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_6 - $SST10_6</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_6</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_6</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_6 - $SST12_6</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_6</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_6</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_6 - $SST14_6</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_6</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_6</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_6 - $SST16_6</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_6</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row2">
    <td><span class="style31"  >TERENGGANU</span></td>
    <td><div align="center" class="style26"  >
        $A6
        <input name="A6" type="hidden" id="A6" size="5" maxlength="5" value="$A6" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B6
        <input name="B6" type="hidden" id="B6" size="5" maxlength="5" value="$B6" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C6
        <input name="C6" type="hidden" id="C6" size="5" maxlength="5" value="$C6" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D6
        <input name="D6" type="hidden" id="D6" size="5" maxlength="5" value="$D6" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E6
        <input name="E6" type="hidden" id="E6" size="5" maxlength="5" value="$E6" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F6
        <input name="F6" type="hidden" id="F6" size="5" maxlength="5" value="$F6" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G6
        <input name="G6" type="hidden" id="G6" size="5" maxlength="5" value="$G6" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H6
        <input name="H6" type="hidden" id="H6" size="5" maxlength="5" value="$H6" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M6
        <input name="M6" type="hidden" id="M6" size="5" maxlength="5" value="$M6" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N6
        <input name="N6" type="hidden" id="N6" size="5" maxlength="5" value="$N6" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O6
        <input name="O6" type="hidden" id="O6" size="5" maxlength="5" value="$O6" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P6
        <input name="P6" type="hidden" id="P6" size="5" maxlength="5" value="$P6" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q6
        <input name="Q6" type="hidden" id="Q6" size="5" maxlength="5" value="$Q6" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R6
        <input name="R6" type="hidden" id="R6" size="5" maxlength="5" value="$R6" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S6
        <input name="S6" type="hidden" id="S6" size="5" maxlength="5" value="$S6" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T6
        <input name="T6" type="hidden" id="T6" size="5" maxlength="5" value="$T6" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U6
        <input name="U6" type="hidden" id="U6" size="5" maxlength="5" value="$U6" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V6
        <input name="V6" type="hidden" id="V6" size="5" maxlength="5" value="$V6" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W6
        <input name="W6" type="hidden" id="W6" size="5" maxlength="5" value="$W6" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB6
        <input name="AB6" type="hidden" id="AB6" size="5" maxlength="5" value="$AB6" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC6
        <input name="AC6" type="hidden" id="AC6" size="5" maxlength="5" value="$AC6" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD6
        <input name="AD6" type="hidden" id="AD6" size="5" maxlength="5" value="$AD6" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE6
        <input name="AE6" type="hidden" id="AE6" size="5" maxlength="5" value="$AE6" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF6
        <input name="AF6" type="hidden" id="AF6" size="5" maxlength="5" value="$AF6" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG6
        <input name="AG6" type="hidden" id="AG6" size="5" maxlength="5" value="$AG6" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH6
        <input name="AH6" type="hidden" id="AH6" size="5" maxlength="5" value="$AH6" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I6
        <input name="I6" type="hidden" id="I6" size="5" maxlength="5" value="$I6" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J6
        <input name="J6" type="hidden" id="J6" size="5" maxlength="5" value="$J6" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_6
        <input name="X6" type="hidden" id="X6" size="5" maxlength="5" value="$JUM_6"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y6" >     </div>
        <input name="K6" type="hidden" id="K6" size="5" maxlength="5" value="$K6" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L6
        <input name="L6" type="hidden" id="L6" size="5" maxlength="5" value="$L6" >
      </div></td>
  </tr>
  
  
  <tr class="row1">
    <td>&nbsp;</td>
    <td><span class="style26"></span></td>
    <td><span class="style26"></span></td>
    
  #set($SST1_7 = 0)
  #set($SST2_7 = 0)
  #set($SST3_7 = 0)
  #set($SST4_7 = 0)
  #set($SST5_7 = 0)
  #set($SST6_7 = 0)
  #set($SST7_7 = 0)
  #set($SST8_7 = 0)
  #set($SST9_7 = 0)
  #set($SST10_7 = 0) 
  #set($SST11_7 = 0)
  #set($SST12_7 = 0)
  #set($SST13_7 = 0)
  #set($SST14_7 = 0)
  #set($SST15_7 = 0)
  #set($SST16_7 = 0)  
  
  
  #set($SX1_7 = 0)
  #set($SX2_7 = 0)
  #set($SX3_7 = 0)
  #set($SX4_7 = 0)
  #set($SX5_7 = 0)
  #set($SX6_7 = 0)
  #set($SX7_7 = 0)
  #set($SX8_7 = 0)
  #set($SX9_7 = 0)
  #set($SX10_7 = 0) 
  #set($SX11_7 = 0)
  #set($SX12_7 = 0)
  #set($SX13_7 = 0)
  #set($SX14_7 = 0)
  #set($SX15_7 = 0)
  #set($SX16_7 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "6")
  #set($JUM_7 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_7 = $list.F11)
  #set($SST2_7 = $list.F12)
  #set($SST3_7 = $list.F13)
  #set($SST4_7 = $list.F14)
  #set($SST5_7 = $list.F15)
  #set($SST6_7 = $list.F16)
  #set($SST7_7 = $list.F17)
  #set($SST8_7 = $list.F18)
  #set($SST9_7 = $list.F19)
  #set($SST10_7 = $list.F20) 
  #set($SST11_7 = $list.F21)
  #set($SST12_7 = $list.F22)
  #set($SST13_7 = $list.F23)
  #set($SST14_7 = $list.F24)
  #set($SST15_7 = $list.F25)
  #set($SST16_7 = $list.F26)  
  
  
  #set($SX1_7 = $list.F11+1)
  #set($SX2_7 = $list.F12+1)
  #set($SX3_7 = $list.F13+1)
  #set($SX4_7 = $list.F14+1)
  #set($SX5_7 = $list.F15+1)
  #set($SX6_7 = $list.F16+1)
  #set($SX7_7 = $list.F17+1)
  #set($SX8_7 = $list.F18+1)
  #set($SX9_7 = $list.F19+1)
  #set($SX10_7 = $list.F20+1) 
  #set($SX11_7 = $list.F21+1)
  #set($SX12_7 = $list.F22+1)
  #set($SX13_7 = $list.F23+1)
  #set($SX14_7 = $list.F24+1)
  #set($SX15_7 = $list.F25+1)
  #set($SX16_7 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_7</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_7 - $SST2_7</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_7</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_7</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_7 - $SST4_7</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_7</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_7</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_7 - $SST6_7</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_7</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_7</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_7 - $SST8_7</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_7</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_7</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_7 - $SST10_7</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_7</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_7</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_7 - $SST12_7</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_7</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_7</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_7 - $SST14_7</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_7</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_7</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_7 - $SST16_7</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_7</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row1">
    <td><span class="style31"  >PAHANG</span></td>
    <td><div align="center" class="style26"  >
        $A7
        <input name="A7" type="hidden" id="A7" size="5" maxlength="5" value="$A7" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B7
        <input name="B7" type="hidden" id="B7" size="5" maxlength="5" value="$B7" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C7
        <input name="C7" type="hidden" id="C7" size="5" maxlength="5" value="$C7" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D7
        <input name="D7" type="hidden" id="D7" size="5" maxlength="5" value="$D7" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E7
        <input name="E7" type="hidden" id="E7" size="5" maxlength="5" value="$E7" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F7
        <input name="F7" type="hidden" id="F7" size="5" maxlength="5" value="$F7" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G7
        <input name="G7" type="hidden" id="G7" size="5" maxlength="5" value="$G7" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H7
        <input name="H7" type="hidden" id="H7" size="5" maxlength="5" value="$H7" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M7
        <input name="M7" type="hidden" id="M7" size="5" maxlength="5" value="$M7" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N7
        <input name="N7" type="hidden" id="N7" size="5" maxlength="5" value="$N7" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O7
        <input name="O7" type="hidden" id="O7" size="5" maxlength="5" value="$O7" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P7
        <input name="P7" type="hidden" id="P7" size="5" maxlength="5" value="$P7" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q7
        <input name="Q7" type="hidden" id="Q7" size="5" maxlength="5" value="$Q7" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R7
        <input name="R7" type="hidden" id="R7" size="5" maxlength="5" value="$R7" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S7
        <input name="S7" type="hidden" id="S7" size="5" maxlength="5" value="$S7" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T7
        <input name="T7" type="hidden" id="T7" size="5" maxlength="5" value="$T7" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U7
        <input name="U7" type="hidden" id="U7" size="5" maxlength="5" value="$U7" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V7
        <input name="V7" type="hidden" id="V7" size="5" maxlength="5" value="$V7" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W7
        <input name="W7" type="hidden" id="W7" size="5" maxlength="5" value="$W7" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB7
        <input name="AB7" type="hidden" id="AB7" size="5" maxlength="5" value="$AB7" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC7
        <input name="AC7" type="hidden" id="AC7" size="5" maxlength="5" value="$AC7" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD7
        <input name="AD7" type="hidden" id="AD7" size="5" maxlength="5" value="$AD7" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE7
        <input name="AE7" type="hidden" id="AE7" size="5" maxlength="5" value="$AE7" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF7
        <input name="AF7" type="hidden" id="AF7" size="5" maxlength="5" value="$AF7" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG7
        <input name="AG7" type="hidden" id="AG7" size="5" maxlength="5" value="$AG7" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH7
        <input name="AH7" type="hidden" id="AH7" size="5" maxlength="5" value="$AH7" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I7
        <input name="I7" type="hidden" id="I7" size="5" maxlength="5" value="$I7" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J7
        <input name="J7" type="hidden" id="J7" size="5" maxlength="5" value="$J7" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_7
        <input name="X7" type="hidden" id="X7" size="5" maxlength="5" value="$JUM_7"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y7" >     </div>
        <input name="K7" type="hidden" id="K7" size="5" maxlength="5" value="$K7" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L7
        <input name="L7" type="hidden" id="L7" size="5" maxlength="5" value="$L7" >
      </div></td>
  </tr>
  
  
  <tr class="row2">
    <td>&nbsp;</td>
    <td><span class="style27"></span></td>
    <td><span class="style27"></span></td>
    
  #set($SST1_8 = 0)
  #set($SST2_8 = 0)
  #set($SST3_8 = 0)
  #set($SST4_8 = 0)
  #set($SST5_8 = 0)
  #set($SST6_8 = 0)
  #set($SST7_8 = 0)
  #set($SST8_8 = 0)
  #set($SST9_8 = 0)
  #set($SST10_8 = 0) 
  #set($SST11_8 = 0)
  #set($SST12_8 = 0)
  #set($SST13_8 = 0)
  #set($SST14_8 = 0)
  #set($SST15_8 = 0)
  #set($SST16_8 = 0)  
  
  
  #set($SX1_8 = 0)
  #set($SX2_8 = 0)
  #set($SX3_8 = 0)
  #set($SX4_8 = 0)
  #set($SX5_8 = 0)
  #set($SX6_8 = 0)
  #set($SX7_8 = 0)
  #set($SX8_8 = 0)
  #set($SX9_8 = 0)
  #set($SX10_8 = 0) 
  #set($SX11_8 = 0)
  #set($SX12_8 = 0)
  #set($SX13_8 = 0)
  #set($SX14_8 = 0)
  #set($SX15_8 = 0)
  #set($SX16_8 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "10")
  #set($JUM_8 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_8 = $list.F11)
  #set($SST2_8 = $list.F12)
  #set($SST3_8 = $list.F13)
  #set($SST4_8 = $list.F14)
  #set($SST5_8 = $list.F15)
  #set($SST6_8 = $list.F16)
  #set($SST7_8 = $list.F17)
  #set($SST8_8 = $list.F18)
  #set($SST9_8 = $list.F19)
  #set($SST10_8 = $list.F20) 
  #set($SST11_8 = $list.F21)
  #set($SST12_8 = $list.F22)
  #set($SST13_8 = $list.F23)
  #set($SST14_8 = $list.F24)
  #set($SST15_8 = $list.F25)
  #set($SST16_8 = $list.F26)  
  
  
  #set($SX1_8 = $list.F11+1)
  #set($SX2_8 = $list.F12+1)
  #set($SX3_8 = $list.F13+1)
  #set($SX4_8 = $list.F14+1)
  #set($SX5_8 = $list.F15+1)
  #set($SX6_8 = $list.F16+1)
  #set($SX7_8 = $list.F17+1)
  #set($SX8_8 = $list.F18+1)
  #set($SX9_8 = $list.F19+1)
  #set($SX10_8 = $list.F20+1) 
  #set($SX11_8 = $list.F21+1)
  #set($SX12_8 = $list.F22+1)
  #set($SX13_8 = $list.F23+1)
  #set($SX14_8 = $list.F24+1)
  #set($SX15_8 = $list.F25+1)
  #set($SX16_8 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_8</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_8 - $SST2_8</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_8</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_8</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_8 - $SST4_8</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_8</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_8</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_8 - $SST6_8</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_8</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_8</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_8 - $SST8_8</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_8</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_8</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_8 - $SST10_8</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_8</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_8</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_8 - $SST12_8</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_8</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_8</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_8 - $SST14_8</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_8</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_8</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_8 - $SST16_8</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_8</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row2">
    <td><span class="style31"  >SELANGOR</span></td>
    <td><div align="center" class="style26"  >
        $A8
        <input name="A8" type="hidden" id="A8" size="5" maxlength="5" value="$A8" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B8
        <input name="B8" type="hidden" id="B8" size="5" maxlength="5" value="$B8" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C8
        <input name="C8" type="hidden" id="C8" size="8" maxlength="8" value="$C8" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D8
        <input name="D8" type="hidden" id="D8" size="8" maxlength="8" value="$D8" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E8
        <input name="E8" type="hidden" id="E8" size="8" maxlength="8" value="$E8" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F8
        <input name="F8" type="hidden" id="F8" size="8" maxlength="8" value="$F8" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G8
        <input name="G8" type="hidden" id="G8" size="8" maxlength="8" value="$G8" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H8
        <input name="H8" type="hidden" id="H8" size="8" maxlength="8" value="$H8" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M8
        <input name="M8" type="hidden" id="M8" size="8" maxlength="8" value="$M8" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N8
        <input name="N8" type="hidden" id="N8" size="8" maxlength="8" value="$N8" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O8
        <input name="O8" type="hidden" id="O8" size="8" maxlength="8" value="$O8" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P8
        <input name="P8" type="hidden" id="P8" size="8" maxlength="8" value="$P8" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q8
        <input name="Q8" type="hidden" id="Q8" size="8" maxlength="8" value="$Q8" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R8
        <input name="R8" type="hidden" id="R8" size="8" maxlength="8" value="$R8" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S8
        <input name="S8" type="hidden" id="S8" size="8" maxlength="8" value="$S8" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T8
        <input name="T8" type="hidden" id="T8" size="8" maxlength="8" value="$T8" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U8
        <input name="U8" type="hidden" id="U8" size="8" maxlength="8" value="$U8" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V8
        <input name="V8" type="hidden" id="V8" size="8" maxlength="8" value="$V8" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W8
        <input name="W8" type="hidden" id="W8" size="8" maxlength="8" value="$W8" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB8
        <input name="AB8" type="hidden" id="AB8" size="8" maxlength="8" value="$AB8" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC8
        <input name="AC8" type="hidden" id="AC8" size="8" maxlength="8" value="$AC8" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD8
        <input name="AD8" type="hidden" id="AD8" size="8" maxlength="8" value="$AD8" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE8
        <input name="AE8" type="hidden" id="AE8" size="8" maxlength="8" value="$AE8" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF8
        <input name="AF8" type="hidden" id="AF8" size="8" maxlength="8" value="$AF8" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG8
        <input name="AG8" type="hidden" id="AG8" size="8" maxlength="8" value="$AG8" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH8
        <input name="AH8" type="hidden" id="AH8" size="8" maxlength="8" value="$AH8" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I8
        <input name="I8" type="hidden" id="I8" size="5" maxlength="5" value="$I8" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J8
        <input name="J8" type="hidden" id="J8" size="5" maxlength="5" value="$J8" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_8
        <input name="X8" type="hidden" id="X8" size="5" maxlength="5" value="$JUM_8"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y8" >     </div>
        <input name="K8" type="hidden" id="K8" size="5" maxlength="5" value="$K8" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L8
        <input name="L8" type="hidden" id="L8" size="5" maxlength="5" value="$L8" >
      </div></td>
  </tr>
  
  <tr class="row1">
    <td>&nbsp;</td>
    <td><span class="style26"></span></td>
    <td><span class="style26"></span></td>
    
  #set($SST1_9 = 0)
  #set($SST2_9 = 0)
  #set($SST3_9 = 0)
  #set($SST4_9 = 0)
  #set($SST5_9 = 0)
  #set($SST6_9 = 0)
  #set($SST7_9 = 0)
  #set($SST8_9 = 0)
  #set($SST9_9 = 0)
  #set($SST10_9 = 0) 
  #set($SST11_9 = 0)
  #set($SST12_9 = 0)
  #set($SST13_9 = 0)
  #set($SST14_9 = 0)
  #set($SST15_9 = 0)
  #set($SST16_9 = 0)  
  
  
  #set($SX1_9 = 0)
  #set($SX2_9 = 0)
  #set($SX3_9 = 0)
  #set($SX4_9 = 0)
  #set($SX5_9 = 0)
  #set($SX6_9 = 0)
  #set($SX7_9 = 0)
  #set($SX8_9 = 0)
  #set($SX9_9 = 0)
  #set($SX10_9 = 0) 
  #set($SX11_9 = 0)
  #set($SX12_9 = 0)
  #set($SX13_9 = 0)
  #set($SX14_9 = 0)
  #set($SX15_9 = 0)
  #set($SX16_9 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "14")
  #set($JUM_9 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_9 = $list.F11)
  #set($SST2_9 = $list.F12)
  #set($SST3_9 = $list.F13)
  #set($SST4_9 = $list.F14)
  #set($SST5_9 = $list.F15)
  #set($SST6_9 = $list.F16)
  #set($SST7_9 = $list.F17)
  #set($SST8_9 = $list.F18)
  #set($SST9_9 = $list.F19)
  #set($SST10_9 = $list.F20) 
  #set($SST11_9 = $list.F21)
  #set($SST12_9 = $list.F22)
  #set($SST13_9 = $list.F23)
  #set($SST14_9 = $list.F24)
  #set($SST15_9 = $list.F25)
  #set($SST16_9 = $list.F26)  
  
  
  #set($SX1_9 = $list.F11+1)
  #set($SX2_9 = $list.F12+1)
  #set($SX3_9 = $list.F13+1)
  #set($SX4_9 = $list.F14+1)
  #set($SX5_9 = $list.F15+1)
  #set($SX6_9 = $list.F16+1)
  #set($SX7_9 = $list.F17+1)
  #set($SX8_9 = $list.F18+1)
  #set($SX9_9 = $list.F19+1)
  #set($SX10_9 = $list.F20+1) 
  #set($SX11_9 = $list.F21+1)
  #set($SX12_9 = $list.F22+1)
  #set($SX13_9 = $list.F23+1)
  #set($SX14_9 = $list.F24+1)
  #set($SX15_9 = $list.F25+1)
  #set($SX16_9 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_9</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_9 - $SST2_9</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_9</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_9</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_9 - $SST4_9</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_9</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_9</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_9 - $SST6_9</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_9</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_9</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_9 - $SST8_9</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_9</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_9</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_9 - $SST10_9</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_9</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_9</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_9 - $SST12_9</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_9</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_9</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_9 - $SST14_9</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_9</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_9</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_9 - $SST16_9</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_9</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row1">
    <td><span class="style31"  >WP KUALA LUMPUR</span></td>
    <td><div align="center" class="style26"  >
        $A9
        <input name="A9" type="hidden" id="A9" size="5" maxlength="5" value="$A9" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B9
        <input name="B9" type="hidden" id="B9" size="5" maxlength="5" value="$B9" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C9
        <input name="C9" type="hidden" id="C9" size="5" maxlength="5" value="$C9" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D9
        <input name="D9" type="hidden" id="D9" size="5" maxlength="5" value="$D9" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E9
        <input name="E9" type="hidden" id="E9" size="5" maxlength="5" value="$E9" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F9
        <input name="F9" type="hidden" id="F9" size="5" maxlength="5" value="$F9" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G9
        <input name="G9" type="hidden" id="G9" size="5" maxlength="5" value="$G9" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H9
        <input name="H9" type="hidden" id="H9" size="5" maxlength="5" value="$H9" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M9
        <input name="M9" type="hidden" id="M9" size="5" maxlength="5" value="$M9" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N9
        <input name="N9" type="hidden" id="N9" size="5" maxlength="5" value="$N9" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O9
        <input name="O9" type="hidden" id="O9" size="5" maxlength="5" value="$O9" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P9
        <input name="P9" type="hidden" id="P9" size="5" maxlength="5" value="$P9" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q9
        <input name="Q9" type="hidden" id="Q9" size="5" maxlength="5" value="$Q9" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R9
        <input name="R9" type="hidden" id="R9" size="5" maxlength="5" value="$R9" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S9
        <input name="S9" type="hidden" id="S9" size="5" maxlength="5" value="$S9" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T9
        <input name="T9" type="hidden" id="T9" size="5" maxlength="5" value="$T9" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U9
        <input name="U9" type="hidden" id="U9" size="5" maxlength="5" value="$U9" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V9
        <input name="V9" type="hidden" id="V9" size="5" maxlength="5" value="$V9" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W9
        <input name="W9" type="hidden" id="W9" size="5" maxlength="5" value="$W9" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB9
        <input name="AB9" type="hidden" id="AB9" size="5" maxlength="5" value="$AB9" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC9
        <input name="AC9" type="hidden" id="AC9" size="5" maxlength="5" value="$AC9" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD9
        <input name="AD9" type="hidden" id="AD9" size="5" maxlength="5" value="$AD9" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE9
        <input name="AE9" type="hidden" id="AE9" size="5" maxlength="5" value="$AE9" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF9
        <input name="AF9" type="hidden" id="AF9" size="5" maxlength="5" value="$AF9" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG9
        <input name="AG9" type="hidden" id="AG9" size="5" maxlength="5" value="$AG9" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH9
        <input name="AH9" type="hidden" id="AH9" size="5" maxlength="5" value="$AH9" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I9
        <input name="I9" type="hidden" id="I9" size="5" maxlength="5" value="$I9" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J9
        <input name="J9" type="hidden" id="J9" size="5" maxlength="5" value="$J9" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_9
        <input name="X9" type="hidden" id="X9" size="5" maxlength="5" value="$JUM_9"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y9" >     </div>
        <input name="K9" type="hidden" id="K9" size="5" maxlength="5" value="$K9" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L9
        <input name="L9" type="hidden" id="L9" size="5" maxlength="5" value="$L9" >
      </div></td>
  </tr>
  
  <tr class="row2">
    <td>&nbsp;</td>
    <td><span class="style26"></span></td>
    <td><span class="style26"></span></td>
    
  #set($SST1_10 = 0)
  #set($SST2_10 = 0)
  #set($SST3_10 = 0)
  #set($SST4_10 = 0)
  #set($SST5_10 = 0)
  #set($SST6_10 = 0)
  #set($SST7_10 = 0)
  #set($SST8_10 = 0)
  #set($SST9_10 = 0)
  #set($SST10_10 = 0) 
  #set($SST11_10 = 0)
  #set($SST12_10 = 0)
  #set($SST13_10 = 0)
  #set($SST14_10 = 0)
  #set($SST15_10 = 0)
  #set($SST16_10 = 0)  
  
  
  #set($SX1_10 = 0)
  #set($SX2_10 = 0)
  #set($SX3_10 = 0)
  #set($SX4_10 = 0)
  #set($SX5_10 = 0)
  #set($SX6_10 = 0)
  #set($SX7_10 = 0)
  #set($SX8_10 = 0)
  #set($SX9_10 = 0)
  #set($SX10_10 = 0) 
  #set($SX11_10 = 0)
  #set($SX12_10 = 0)
  #set($SX13_10 = 0)
  #set($SX14_10 = 0)
  #set($SX15_10 = 0)
  #set($SX16_10 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "5")
  #set($JUM_10 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_10 = $list.F11)
  #set($SST2_10 = $list.F12)
  #set($SST3_10 = $list.F13)
  #set($SST4_10 = $list.F14)
  #set($SST5_10 = $list.F15)
  #set($SST6_10 = $list.F16)
  #set($SST7_10 = $list.F17)
  #set($SST8_10 = $list.F18)
  #set($SST9_10 = $list.F19)
  #set($SST10_10 = $list.F20) 
  #set($SST11_10 = $list.F21)
  #set($SST12_10 = $list.F22)
  #set($SST13_10 = $list.F23)
  #set($SST14_10 = $list.F24)
  #set($SST15_10 = $list.F25)
  #set($SST16_10 = $list.F26)  
  
  
  #set($SX1_10 = $list.F11+1)
  #set($SX2_10 = $list.F12+1)
  #set($SX3_10 = $list.F13+1)
  #set($SX4_10 = $list.F14+1)
  #set($SX5_10 = $list.F15+1)
  #set($SX6_10 = $list.F16+1)
  #set($SX7_10 = $list.F17+1)
  #set($SX8_10 = $list.F18+1)
  #set($SX9_10 = $list.F19+1)
  #set($SX10_10 = $list.F20+1) 
  #set($SX11_10 = $list.F21+1)
  #set($SX12_10 = $list.F22+1)
  #set($SX13_10 = $list.F23+1)
  #set($SX14_10 = $list.F24+1)
  #set($SX15_10 = $list.F25+1)
  #set($SX16_10 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_10</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_10 - $SST2_10</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_10</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_10</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_10 - $SST4_10</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_10</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_10</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_10 - $SST6_10</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_10</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_10</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_10 - $SST8_10</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_10</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_10</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_10 - $SST10_10</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_10</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_10</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_10 - $SST12_10</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_10</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_10</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_10 - $SST14_10</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_10</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_10</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_10 - $SST16_10</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_10</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row2">
    <td><span class="style31"  >NEGERI SEMBILAN</span></td>
    <td><div align="center" class="style26"  >
        $A10
        <input name="A10" type="hidden" id="A10" size="5" maxlength="5" value="$A10" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B10
        <input name="B10" type="hidden" id="B10" size="5" maxlength="5" value="$B10" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C10
        <input name="C10" type="hidden" id="C10" size="5" maxlength="5" value="$C10" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D10
        <input name="D10" type="hidden" id="D10" size="5" maxlength="5" value="$D10" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E10
        <input name="E10" type="hidden" id="E10" size="5" maxlength="5" value="$E10" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F10
        <input name="F10" type="hidden" id="F10" size="5" maxlength="5" value="$F10" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G10
        <input name="G10" type="hidden" id="G10" size="5" maxlength="5" value="$G10" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H10
        <input name="H10" type="hidden" id="H10" size="5" maxlength="5" value="$H10" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M10
        <input name="M10" type="hidden" id="M10" size="5" maxlength="5" value="$M10" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N10
        <input name="N10" type="hidden" id="N10" size="5" maxlength="5" value="$N10" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O10
        <input name="O10" type="hidden" id="O10" size="5" maxlength="5" value="$O10" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P10
        <input name="P10" type="hidden" id="P10" size="5" maxlength="5" value="$P10" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q10
        <input name="Q10" type="hidden" id="Q10" size="5" maxlength="5" value="$Q10" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R10
        <input name="R10" type="hidden" id="R10" size="5" maxlength="5" value="$R10" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S10
        <input name="S10" type="hidden" id="S10" size="5" maxlength="5" value="$S10" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T10
        <input name="T10" type="hidden" id="T10" size="5" maxlength="5" value="$T10" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U10
        <input name="U10" type="hidden" id="U10" size="5" maxlength="5" value="$U10" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V10
        <input name="V10" type="hidden" id="V10" size="5" maxlength="5" value="$V10" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W10
        <input name="W10" type="hidden" id="W10" size="5" maxlength="5" value="$W10" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB10
        <input name="AB10" type="hidden" id="AB10" size="5" maxlength="5" value="$AB10" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC10
        <input name="AC10" type="hidden" id="AC10" size="5" maxlength="5" value="$AC10" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD10
        <input name="AD10" type="hidden" id="AD10" size="5" maxlength="5" value="$AD10" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE10
        <input name="AE10" type="hidden" id="AE10" size="5" maxlength="5" value="$AE10" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF10
        <input name="AF10" type="hidden" id="AF10" size="5" maxlength="5" value="$AF10" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG10
        <input name="AG10" type="hidden" id="AG10" size="5" maxlength="5" value="$AG10" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH10
        <input name="AH10" type="hidden" id="AH10" size="5" maxlength="5" value="$AH10" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I10
        <input name="I10" type="hidden" id="I10" size="5" maxlength="5" value="$I10" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J10
        <input name="J10" type="hidden" id="J10" size="5" maxlength="5" value="$J10" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_10
        <input name="X10" type="hidden" id="X10" size="5" maxlength="5" value="$JUM_10"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y10" >     </div>
        <input name="K10" type="hidden" id="K10" size="5" maxlength="5" value="$K10" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L10
        <input name="L10" type="hidden" id="L10" size="5" maxlength="5" value="$L10" >
      </div></td>
  </tr>
  
  <tr class="row1">
    <td>&nbsp;</td>
    <td><span class="style26"></span></td>
    <td><span class="style26"></span></td>
    
  #set($SST1_11 = 0)
  #set($SST2_11 = 0)
  #set($SST3_11 = 0)
  #set($SST4_11 = 0)
  #set($SST5_11 = 0)
  #set($SST6_11 = 0)
  #set($SST7_11 = 0)
  #set($SST8_11 = 0)
  #set($SST9_11 = 0)
  #set($SST10_11 = 0) 
  #set($SST11_11 = 0)
  #set($SST12_11 = 0)
  #set($SST13_11 = 0)
  #set($SST14_11 = 0)
  #set($SST15_11 = 0)
  #set($SST16_11 = 0)  
  
  
  #set($SX1_11 = 0)
  #set($SX2_11 = 0)
  #set($SX3_11 = 0)
  #set($SX4_11 = 0)
  #set($SX5_11 = 0)
  #set($SX6_11 = 0)
  #set($SX7_11 = 0)
  #set($SX8_11 = 0)
  #set($SX9_11 = 0)
  #set($SX10_11 = 0) 
  #set($SX11_11 = 0)
  #set($SX12_11 = 0)
  #set($SX13_11 = 0)
  #set($SX14_11 = 0)
  #set($SX15_11 = 0)
  #set($SX16_11 = 0) 
    
  #foreach($list in $senarai_kpisasaran)
  
  #if($list.ID_NEGERI == "4")
  #set($JUM_11 = $list.F1 + $list.F2 + $list.F3 + $list.F4 + $list.F5 + $list.F6 + $list.F7 + $list.F8 + $list.F9 + $list.F10)
  #set($SST1_11 = $list.F11)
  #set($SST2_11 = $list.F12)
  #set($SST3_11 = $list.F13)
  #set($SST4_11 = $list.F14)
  #set($SST5_11 = $list.F15)
  #set($SST6_11 = $list.F16)
  #set($SST7_11 = $list.F17)
  #set($SST8_11 = $list.F18)
  #set($SST9_11 = $list.F19)
  #set($SST10_11 = $list.F20) 
  #set($SST11_11 = $list.F21)
  #set($SST12_11 = $list.F22)
  #set($SST13_11 = $list.F23)
  #set($SST14_11 = $list.F24)
  #set($SST15_11 = $list.F25)
  #set($SST16_11 = $list.F26)  
  
  
  #set($SX1_11 = $list.F11+1)
  #set($SX2_11 = $list.F12+1)
  #set($SX3_11 = $list.F13+1)
  #set($SX4_11 = $list.F14+1)
  #set($SX5_11 = $list.F15+1)
  #set($SX6_11 = $list.F16+1)
  #set($SX7_11 = $list.F17+1)
  #set($SX8_11 = $list.F18+1)
  #set($SX9_11 = $list.F19+1)
  #set($SX10_11 = $list.F20+1) 
  #set($SX11_11 = $list.F21+1)
  #set($SX12_11 = $list.F22+1)
  #set($SX13_11 = $list.F23+1)
  #set($SX14_11 = $list.F24+1)
  #set($SX15_11 = $list.F25+1)
  #set($SX16_11 = $list.F26+1) 
 
  
  #end
  
  #end

      
   
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST1_11</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX1_11 - $SST2_11</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX2_11</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST3_11</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX3_11 - $SST4_11</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX4_11</div></td>
    
     <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST5_11</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX5_11 - $SST6_11</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX6_11</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST7_11</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX7_11 - $SST8_11</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX8_11</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST9_11</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX9_11 - $SST10_11</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX10_11</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST11_11</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX11_11 - $SST12_11</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX12_11</div></td>
    
    <td  bgcolor="#99FFCC" ><div align="center" class="style26" >&lt; $SST13_11</div></td>
    <td  bgcolor="#FFFF99"><div align="center" class="style26" >$SX13_11 - $SST14_11</div></td>
    <td   bgcolor="#FF9999"><div align="center" class="style26" >&gt; $SX14_11</div></td>
    
    <td   bgcolor="#99FFCC"><div align="center" class="style26" >&lt; $SST15_11</div></td>
    <td   bgcolor="#FFFF99"> <div align="center" class="style26" >$SX15_11 - $SST16_11</div></td>
    <td  bgcolor="#FF9999"> <div align="center" class="style26" >&gt; $SX16_11</div></td>
    
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="row1">
    <td><span class="style31"  >MELAKA</span></td>
    <td><div align="center" class="style26"  >
        $A11
        <input name="A11" type="hidden" id="A11" size="5" maxlength="5" value="$A11" >
    </div></td>
    <td><div align="center" class="style26"  >
        $B11
        <input name="B11" type="hidden" id="B11" size="5" maxlength="5" value="$B11" >
        </div></td>
     <td>
    <div align="center" class="style26" >
        $C11
        <input name="C11" type="hidden" id="C11" size="5" maxlength="5" value="$C11" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $D11
        <input name="D11" type="hidden" id="D11" size="5" maxlength="5" value="$D11" >
    </div>    </td>
     <td>
    <div align="center" class="style26" >
        $E11
        <input name="E11" type="hidden" id="E11" size="5" maxlength="5" value="$E11" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $F11
        <input name="F11" type="hidden" id="F11" size="5" maxlength="5" value="$F11" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $G11
        <input name="G11" type="hidden" id="G11" size="5" maxlength="5" value="$G11" >
    </div>    </td>
    <td>
     <div align="center" class="style26" >
        $H11
        <input name="H11" type="hidden" id="H11" size="5" maxlength="5" value="$H11" >
    </div>    </td>
      
      
       
      <td>
    <div align="center" class="style26" >
        $M11
        <input name="M11" type="hidden" id="M11" size="5" maxlength="5" value="$M11" >
    </div>    </td>
    <td>
    <div align="center" class="style26" >
        $N11
        <input name="N11" type="hidden" id="N11" size="5" maxlength="5" value="$N11" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $O11
        <input name="O11" type="hidden" id="O11" size="5" maxlength="5" value="$O11" >
    </div>
    </td>
      
            <td>
     
     <div align="center" class="style26" >
        $P11
        <input name="P11" type="hidden" id="P11" size="5" maxlength="5" value="$P11" >
    </div>
     </td>
    <td>
   
    <div align="center" class="style26" >
        $Q11
        <input name="Q11" type="hidden" id="Q11" size="5" maxlength="5" value="$Q11" >
    </div>
    </td>
    <td>
    
     <div align="center" class="style26" >
        $R11
        <input name="R11" type="hidden" id="R11" size="5" maxlength="5" value="$R11" >
    </div>
    </td>
      
            <td>
     
       <div align="center" class="style26" >
        $S11
        <input name="S11" type="hidden" id="S11" size="5" maxlength="5" value="$S11" >
    </div>
         </td>
    <td>
      <div align="center" class="style26" >
        $T11
        <input name="T11" type="hidden" id="T11" size="5" maxlength="5" value="$T11" >
    </div>
    </td>
    <td>
   <div align="center" class="style26" >
        $U11
        <input name="U11" type="hidden" id="U11" size="5" maxlength="5" value="$U11" >
    </div>
  
    </td>
      
            <td>
     <div align="center" class="style26" >
        $V11
        <input name="V11" type="hidden" id="V11" size="5" maxlength="5" value="$V11" >
    </div>
    
     </td>
    <td>
    
     <div align="center" class="style26" >
        $W11
        <input name="W11" type="hidden" id="W11" size="5" maxlength="5" value="$W11" >
    </div>
    
    </td>
    <td>
   
    <div align="center" class="style26" >
        $AB11
        <input name="AB11" type="hidden" id="AB11" size="5" maxlength="5" value="$AB11" >
    </div>
   
    </td>
      
            <td>
       <div align="center" class="style26" >
        $AC11
        <input name="AC11" type="hidden" id="AC11" size="5" maxlength="5" value="$AC11" >
    </div>
     
         </td>
    <td>
   
   <div align="center" class="style26" >
        $AD11
        <input name="AD11" type="hidden" id="AD11" size="5" maxlength="5" value="$AD11" >
    </div>
    </td>
    <td>
     <div align="center" class="style26" >
        $AE11
        <input name="AE11" type="hidden" id="AE11" size="5" maxlength="5" value="$AE11" >
    </div> 
    <td>
            
             <div align="center" class="style26" >
        $AF11
        <input name="AF11" type="hidden" id="AF11" size="5" maxlength="5" value="$AF11" >
    </div> 
            
    <td>
  <div align="center" class="style26" >
        $AG11
        <input name="AG11" type="hidden" id="AG11" size="5" maxlength="5" value="$AG11" >
    </div> 
 
    </td>
    <td>
    
     <div align="center" class="style26" >
        $AH11
        <input name="AH11" type="hidden" id="AH11" size="5" maxlength="5" value="$AH11" >
    </div> 
    
    </td>
      
      
     <td><div align="center" class="style26" >
        $I11
        <input name="I11" type="hidden" id="I11" size="5" maxlength="5" value="$I11" >
      </div> </td>
    <td><div align="center" class="style26" >
        $J11
        <input name="J11" type="hidden" id="J11" size="5" maxlength="5" value="$J11" >
      </div></td>
      
     <td><div align="center" class="style26" >
       $JUM_11
        <input name="X11" type="hidden" id="X11" size="5" maxlength="5" value="$JUM_11"  style="text-align:center">
      </div> </td>
   <td><div align="center" class="style26" >
         <div align="center" id="Y11" >     </div>
        <input name="K11" type="hidden" id="K11" size="5" maxlength="5" value="$K11" >
      </div> </td>
    <td><div align="center" class="style26" >
        $L11
        <input name="L11" type="hidden" id="L11" size="5" maxlength="5" value="$L11" >
      </div></td>
  </tr>
  
  <tr  bgcolor="black">
    <td colspan="32"></td>
  </tr>
  <tr class="row2">
    <td><span class="style31"  >JUMLAH</span></td>
    <td>
    <div align="center" id="A12"  ><span ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span></span> </div>    </td>
    <td><div align="center" id="B12"  ><span ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span></span>      
     </div></td>
    <td><div align="center" id="C12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
    <td><div align="center" id="D12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
    <td><div align="center" id="E12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
    <td><div align="center" id="F12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
    <td><div align="center" id="G12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
    <td><div align="center" id="H12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
    </div></td>
      <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
    <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
    <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
      
            <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
    <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
      
            <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
    <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
      
            <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
    <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
      
            <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
    <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
      
            <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
    <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td>
     <div align="center" ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span> </div>    </td>
    <td><div align="center" id="I12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
    <td><div align="center" id="J12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
      <td><div align="center" id="X12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
    <td><div align="center" id="K12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
    <td><div align="center" id="L12"  ><span class="style18"><span class="style21"><span class="style22"><span class="style27"></span></span></span></span>      
     </div></td>
  </tr>
  <tr  bgcolor="black">
    <td colspan="32"></td>
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
 


 
 $jquery("#A12").html("<font color='blue'>"+A12+"</font>");
 $jquery("#B12").html("<font color='blue'>"+B12+"</font>");
 $jquery("#C12").html("<font color='blue'>"+C12+"</font>");
 $jquery("#D12").html("<font color='blue'>"+D12+"</font>");
 $jquery("#E12").html("<font color='blue'>"+E12+"</font>");
 $jquery("#F12").html("<font color='blue'>"+F12+"</font>");
 $jquery("#G12").html("<font color='blue'>"+G12+"</font>");
 $jquery("#H12").html("<font color='blue'>"+H12+"</font>");
 $jquery("#I12").html("<font color='blue'>"+I12+"</font>");
 $jquery("#J12").html("<font color='blue'>"+J12+"</font>");
// $jquery("#K12").html("<font color='blue'>"+K12+"</font>");
 $jquery("#L12").html("<font color='blue'>"+L12+"</font>");
 
 if(parseInt(document.${formName}.K1.value)>0)
 {
 var Y1 = (parseInt(document.${formName}.X1.value) / parseInt(document.${formName}.K1.value))*100;
 }
 else
 {
 var Y1 = 0;
 }
 
 if(parseInt(document.${formName}.K2.value)>0)
 {
 var Y2 = (parseInt(document.${formName}.X2.value) / parseInt(document.${formName}.K2.value))*100;
 }
 else
 {
 var Y2 = 0;
 }
 
 if(parseInt(document.${formName}.K3.value)>0)
 {
 var Y3 = (parseInt(document.${formName}.X3.value) / parseInt(document.${formName}.K3.value))*100;
 }
 else
 {
 var Y3 = 0;
 }
 
 if(parseInt(document.${formName}.K4.value)>0)
 {
 var Y4 = (parseInt(document.${formName}.X4.value) / parseInt(document.${formName}.K4.value))*100;
 }
 else
 {
 var Y4 = 0;
 }
 
 if(parseInt(document.${formName}.K5.value)>0)
 {
 var Y5 = (parseInt(document.${formName}.X5.value) / parseInt(document.${formName}.K5.value))*100;
 }
 else
 {
 var Y5 = 0;
 }
 
 if(parseInt(document.${formName}.K6.value)>0)
 {
 var Y6 = (parseInt(document.${formName}.X6.value) / parseInt(document.${formName}.K6.value))*100;
 }
 else
 {
 var Y6 = 0;
 }
 
 if(parseInt(document.${formName}.K7.value)>0)
 {
 var Y7 = (parseInt(document.${formName}.X7.value) / parseInt(document.${formName}.K7.value))*100;
 }
 else
 {
 var Y7 = 0;
 }
 
 if(parseInt(document.${formName}.K8.value)>0)
 {
 var Y8 = (parseInt(document.${formName}.X8.value) / parseInt(document.${formName}.K8.value))*100;
 }
 else
 {
 var Y8 = 0;
 }
 
 if(parseInt(document.${formName}.K9.value)>0)
 {
 var Y9 = (parseInt(document.${formName}.X9.value) / parseInt(document.${formName}.K9.value))*100;
 }
 else
 {
 var Y9 = 0;
 }
 
 if(parseInt(document.${formName}.K10.value)>0)
 {
 var Y10 = (parseInt(document.${formName}.X10.value) / parseInt(document.${formName}.K10.value))*100;
 }
 else
 {
 var Y10 = 0;
 }
 
 if(parseInt(document.${formName}.K11.value)>0)
 {
 var Y11 = (parseInt(document.${formName}.X11.value) / parseInt(document.${formName}.K11.value))*100;
 }
 else
 {
 var Y11 = 0;
 }
 
 
 
 $jquery("#Y1").html("<font color='blue'>"+Y1.toFixed(2)+"</font>");
 $jquery("#Y2").html("<font color='blue'>"+Y2.toFixed(2)+"</font>");
 $jquery("#Y3").html("<font color='blue'>"+Y3.toFixed(2)+"</font>");
 $jquery("#Y4").html("<font color='blue'>"+Y4.toFixed(2)+"</font>");
 $jquery("#Y5").html("<font color='blue'>"+Y5.toFixed(2)+"</font>");
 $jquery("#Y6").html("<font color='blue'>"+Y6.toFixed(2)+"</font>");
 $jquery("#Y7").html("<font color='blue'>"+Y7.toFixed(2)+"</font>");
 $jquery("#Y8").html("<font color='blue'>"+Y8.toFixed(2)+"</font>");
 $jquery("#Y9").html("<font color='blue'>"+Y9.toFixed(2)+"</font>");
 $jquery("#Y10").html("<font color='blue'>"+Y10.toFixed(2)+"</font>");
 $jquery("#Y11").html("<font color='blue'>"+Y11.toFixed(2)+"</font>");
 
 
 
 
 }
 
 
 </script>

