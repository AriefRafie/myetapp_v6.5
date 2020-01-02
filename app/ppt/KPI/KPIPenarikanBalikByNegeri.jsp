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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "9")
  #set($Perlis1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Perlis2 = $list.F5)
  #set($Perlis4 = $list.F6)
  #set($Perlis5 = $list.F7)
  #set($Perlis6 = $list.F8)  
  #set($Perlis7 = $list.F5+1)
  #set($Perlis8 = $list.F6+1)
  #set($Perlis9 = $list.F7+1)
  #set($Perlis10 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <=  $Perlis2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "9")
#set($C1 = $C1 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Perlis4 && $list.HARI_TUNGGU_MMK >= $Perlis7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "9")
#set($D1 = $D1 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Perlis8 && $list.ID_NEGERI == "9")
#set($E1 = $E1 + 1)
#end
#end

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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "2")
  #set($Kedah1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Kedah2 = $list.F5)
  #set($Kedah4 = $list.F6)
  #set($Kedah5 = $list.F7)
  #set($Kedah6 = $list.F8)  
  #set($Kedah7 = $list.F5+1)
  #set($Kedah8 = $list.F6+1)
  #set($Kedah9 = $list.F7+1)
  #set($Kedah10 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $Kedah2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "2")
#set($C2 = $C2 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Kedah4 && $list.HARI_TUNGGU_MMK >= $Kedah7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "2")
#set($D2 = $D2 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Kedah8 && $list.ID_NEGERI == "2")
#set($E2 = $E2 + 1)
#end
#end

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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "7")
  #set($Penang1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Penang2 = $list.F5)
  #set($Penang4 = $list.F6)
  #set($Penang5 = $list.F7)
  #set($Penang6 = $list.F8)  
  #set($Penang7 = $list.F5+1)
  #set($Penang8 = $list.F6+1)
  #set($Penang9 = $list.F7+1)
  #set($Penang10 = $list.F8+1)  
  #end 
  #end


#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $Penang2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "7")
#set($C3 = $C3 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Penang4 && $list.HARI_TUNGGU_MMK >= $Penang7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "7")
#set($D3 = $D3 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Penang8 && $list.ID_NEGERI == "7")
#set($E3 = $E3 + 1)
#end
#end

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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "8")
  #set($Perak1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Perak2 = $list.F5)
  #set($Perak4 = $list.F6)
  #set($Perak5 = $list.F7)
  #set($Perak6 = $list.F8)  
  #set($Perak7 = $list.F5+1)
  #set($Perak8 = $list.F6+1)
  #set($Perak9 = $list.F7+1)
  #set($Perak10 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $Perak2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "8")
#set($C4 = $C4 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Perak3 && $list.HARI_TUNGGU_MMK >= $Perak7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "8")
#set($D4 = $D4 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Perak8 && $list.ID_NEGERI == "8")
#set($E4 = $E4 + 1)
#end
#end


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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "3")
  #set($Kelantan1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Kelantan2 = $list.F5)
  #set($Kelantan4 = $list.F6)
  #set($Kelantan5 = $list.F7)
  #set($Kelantan6 = $list.F8)  
  #set($Kelantan7 = $list.F5+1)
  #set($Kelantan8 = $list.F6+1)
  #set($Kelantan9 = $list.F7+1)
  #set($Kelantan10 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $Kelantan2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "3")
#set($C5 = $C5 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Kelantan4 && $list.HARI_TUNGGU_MMK >= $Kelantan7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "3")
#set($D5 = $D5 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Kelantan8 && $list.ID_NEGERI == "3")
#set($E5 = $E5 + 1)
#end
#end


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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "11")
  #set($Ganu1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Ganu2 = $list.F5)
  #set($Ganu4 = $list.F6)
  #set($Ganu5 = $list.F7)
  #set($Ganu6 = $list.F8)  
  #set($Ganu7 = $list.F5+1)
  #set($Ganu8 = $list.F6+1)
  #set($Ganu9 = $list.F7+1)
  #set($Ganu10 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $Ganu2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "11")
#set($C6 = $C6 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Ganu4 && $list.HARI_TUNGGU_MMK >= $Ganu7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "11")
#set($D6 = $D6 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Ganu8 && $list.ID_NEGERI == "11")
#set($E6 = $E6 + 1)
#end
#end


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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "6")
  #set($Pahang1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Pahang2 = $list.F5)
  #set($Pahang4 = $list.F6)
  #set($Pahang5 = $list.F7)
  #set($Pahang6 = $list.F8)  
  #set($Pahang7 = $list.F5+1)
  #set($Pahang8 = $list.F6+1)
  #set($Pahang9 = $list.F7+1)
  #set($Pahang10 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $Pahang2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "6")
#set($C7 = $C7 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Pahang4 && $list.HARI_TUNGGU_MMK >= $Pahang7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "6")
#set($D7 = $D7 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Pahang8 && $list.ID_NEGERI == "6")
#set($E7 = $E7 + 1)
#end
#end


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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "10")
  #set($Selangor1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Selangor2 = $list.F5)
  #set($Selangor4 = $list.F6)
  #set($Selangor5 = $list.F7)
  #set($Selangor6 = $list.F8)  
  #set($Selangor7 = $list.F5+1)
  #set($Selangor8 = $list.F6+1)
  #set($Selangor9 = $list.F7+1)
  #set($Selangor10 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $Selangor2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "10")
#set($C8 = $C8 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Selangor4 && $list.HARI_TUNGGU_MMK >= $Selangor7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "10")
#set($D8 = $D8 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Selangor8 && $list.ID_NEGERI == "10")
#set($E8 = $E8 + 1)
#end
#end


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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "14")
  #set($KL1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($KL2 = $list.F5)
  #set($KL4 = $list.F6)
  #set($KL5 = $list.F7)
  #set($KL6 = $list.F8)  
  #set($KL7 = $list.F5+1)
  #set($KL8 = $list.F6+1)
  #set($KL9 = $list.F7+1)
  #set($KL10 = $list.F8+1)  
  #end 
  #end


#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $KL2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "14")
#set($C9 = $C9 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $KL4 && $list.HARI_TUNGGU_MMK >= $KL7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "14")
#set($D9 = $D9 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $KL8 && $list.ID_NEGERI == "14")
#set($E9 = $E9 + 1)
#end
#end


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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "5")
  #set($N91 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($N92 = $list.F5)
  #set($N94 = $list.F6)
  #set($N95 = $list.F7)
  #set($N96 = $list.F8)  
  #set($N97 = $list.F5+1)
  #set($N98 = $list.F6+1)
  #set($N99 = $list.F7+1)
  #set($N910 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $N92 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "5")
#set($C10 = $C10 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $N94 && $list.HARI_TUNGGU_MMK >= $N97 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "5")
#set($D10 = $D10 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $N98 && $list.ID_NEGERI == "5")
#set($E10 = $E10 + 1)
#end
#end




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
      
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "4")
  #set($Melaka1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Melaka2 = $list.F5)
  #set($Melaka4 = $list.F6)
  #set($Melaka5 = $list.F7)
  #set($Melaka6 = $list.F8)  
  #set($Melaka7 = $list.F5+1)
  #set($Melaka8 = $list.F6+1)
  #set($Melaka9 = $list.F7+1)
  #set($Melaka10 = $list.F8+1)  
  #end 
  #end

#foreach($list in $list_KPI_Penarikan_Menunggu_MMK)
#if($list.HARI_TUNGGU_MMK <= $Melaka2 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "4")
#set($C11 = $C11 + 1)
#end
#if($list.HARI_TUNGGU_MMK <= $Melaka4 && $list.HARI_TUNGGU_MMK >= $Melaka7 && $list.HARI_TUNGGU_MMK > 0 && $list.ID_NEGERI == "4")
#set($D11 = $D11 + 1)
#end
#if($list.HARI_TUNGGU_MMK >= $Melaka8 && $list.ID_NEGERI == "4")
#set($E11 = $E11 + 1)
#end
#end



#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Perlis5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "9")
#set($F1 = $F1 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Perlis6 && $list.HARI_TUNGGU_BAYAR >= $Perlis9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "9")
#set($G1 = $G1 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Perlis10 && $list.ID_NEGERI == "9")
#set($H1 = $H1 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Kedah5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "2")
#set($F2 = $F2 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Kedah6 && $list.HARI_TUNGGU_BAYAR >= $Kedah9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "2")
#set($G2 = $G2 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Kedah10 && $list.ID_NEGERI == "2")
#set($H2 = $H2 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Penang5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "7")
#set($F3 = $F3 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Penang6 && $list.HARI_TUNGGU_BAYAR >= $Penang9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "7")
#set($G3 = $G3 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Penang10 && $list.ID_NEGERI == "7")
#set($H3 = $H3 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Perak5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "8")
#set($F4 = $F4 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Perak6 && $list.HARI_TUNGGU_BAYAR >= $Perak9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "8")
#set($G4 = $G4 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Perak10 && $list.ID_NEGERI == "8")
#set($H4 = $H4 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Kelantan5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "3")
#set($F5 = $F5 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Kelantan6 && $list.HARI_TUNGGU_BAYAR >= $Kelantan9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "3")
#set($G5 = $G5 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Kelantan10 && $list.ID_NEGERI == "3")
#set($H5 = $H5 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Ganu5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "11")
#set($F6 = $F6 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Ganu6 && $list.HARI_TUNGGU_BAYAR >= $Ganu9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "11")
#set($G6 = $G6 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Ganu10 && $list.ID_NEGERI == "11")
#set($H6 = $H6 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Pahang5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "6")
#set($F7 = $F7 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Pahang6 && $list.HARI_TUNGGU_BAYAR >= $Pahang9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "6")
#set($G7 = $G7 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Pahang10 && $list.ID_NEGERI == "6")
#set($H7 = $H7 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Selangor5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "10")
#set($F8 = $F8 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Selangor6 && $list.HARI_TUNGGU_BAYAR >= $Selangor9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "10")
#set($G8 = $G8 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Selangor10 && $list.ID_NEGERI == "10")
#set($H8 = $H8 + 1)
#end
#end


#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $KL5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "14")
#set($F9 = $F9 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $KL6 && $list.HARI_TUNGGU_BAYAR >= $KL9 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "14")
#set($G9 = $G9 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $KL10 && $list.ID_NEGERI == "14")
#set($H9 = $H9 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $N95 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "5")
#set($F10 = $F10 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $N96 && $list.HARI_TUNGGU_BAYAR >= $N99 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "5")
#set($G10 = $G10 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $N910 && $list.ID_NEGERI == "5")
#set($H10 = $H10 + 1)
#end
#end

#foreach($list in $list_KPI_Penarikan_Menunggu_Bayaran)
#if($list.HARI_TUNGGU_BAYAR <= $Melaka5 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "4")
#set($F11 = $F11 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR <= $Melaka6 && $list.HARI_TUNGGU_BAYAR >= $Melaka8 && $list.HARI_TUNGGU_BAYAR > 0 && $list.ID_NEGERI == "4")
#set($G11 = $G11 + 1)
#end
#if($list.HARI_TUNGGU_BAYAR >= $Melaka10 && $list.ID_NEGERI == "4")
#set($H11 = $H11 + 1)
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
        <td valign="top" width="100%" ><div align="center"><strong>URUSAN 03.05 : RINGKASAN DI PERINGKAT NEGERI PENARIKAN BALIK PENGAMBILAN TANAH</strong></div></td>
      </tr>
      <tr>
        <td valign="top" width="100%" ><div align="center"><strong>TARIKH MULA : $!txdTarikhMula</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>TARIKH AKHIR : $!txdTarikhAkhir</strong></div></td>
      </tr>
    </table>
<fieldset>
<legend>MAKLUMAT KPI</legend>

<table width="100%" class="table_headerkpi"  >
<tr >
    <td valign="top" width="20%" rowspan="3" class="row2" ><strong>NEGERI</strong></td>
    <td valign="top" width="10%" rowspan="2" colspan="2" class="row2"><div align="center"><strong>Jumlah bil. Permohonan diterima &amp; bil. Lot terlibat</strong></div></td>
   
    <td valign="top" colspan="6" rowspan="3"  class="row2" width="30%"><div align="center"><strong>Menunggu Sokongan Agensi Luar</strong></div></td>
       <td valign="top"  colspan="5" class="row2" width="40%"><div align="center"><strong>Jumlah Permohonan Telah Di Selesaikan</strong></div></td>
  </tr>
<tr >
      
    
    
    <td valign="top"  width="10%" colspan="2" class="row2"><div align="center"><strong>Bilangan</strong></div></td>
    
    <td valign="top"  width="10%" rowspan="2" class="row2"><div align="center"><strong>Sasaran </strong></div></td>   
    <td valign="top"  width="10%" rowspan="2" class="row2"><div align="center"><strong>Kecekapan dalaman Unit JKPTG (%)</strong></div></td>
    <td valign="top"  width="10%" rowspan="2" class="row2"><div align="center"><strong>Purata Kitaran Masa (hari)</strong></div></td>
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "9")
  #set($Perlis1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Perlis2 = $list.F5)
  #set($Perlis4 = $list.F6)
  #set($Perlis5 = $list.F7)
  #set($Perlis6 = $list.F8)  
  #set($Perlis7 = $list.F5+1)
  #set($Perlis8 = $list.F6+1)
  #set($Perlis9 = $list.F7+1)
  #set($Perlis10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Perlis2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C1</strong>
        <input name="C1" type="hidden" id="C1" size="5" maxlength="5" value="$C1" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Perlis7 - $Perlis4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D1</strong>
        <input name="D1" type="hidden" id="D1" size="5" maxlength="5" value="$D1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Perlis8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E1</strong>
        <input name="E1" type="hidden" id="E1" size="5" maxlength="5" value="$E1" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perlis5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F1</strong>
        <input name="F1" type="hidden" id="F1" size="5" maxlength="5" value="$F1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perlis9 - $Perlis6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G1</strong>
        <input name="G1" type="hidden" id="G1" size="5" maxlength="5" value="$G1" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perlis10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H1</strong>
        <input name="H1" type="hidden" id="H1" size="5" maxlength="5" value="$H1" >
      </div>  </td>
          </tr>
        </table>
        </td>
      </tr>
    </table></td>
  
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "2")
  #set($Kedah1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Kedah2 = $list.F5)
  #set($Kedah4 = $list.F6)
  #set($Kedah5 = $list.F7)
  #set($Kedah6 = $list.F8)  
  #set($Kedah7 = $list.F5+1)
  #set($Kedah8 = $list.F6+1)
  #set($Kedah9 = $list.F7+1)
  #set($Kedah10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Kedah2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C2</strong>
        <input name="C2" type="hidden" id="C2" size="5" maxlength="5" value="$C2" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Kedah7 - $Kedah4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D2</strong>
        <input name="D2" type="hidden" id="D2" size="5" maxlength="5" value="$D2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Kedah8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E2</strong>
        <input name="E2" type="hidden" id="E2" size="5" maxlength="5" value="$E2" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kedah5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F2</strong>
        <input name="F2" type="hidden" id="F2" size="5" maxlength="5" value="$F2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kedah9 - $Kedah6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G2</strong>
        <input name="G2" type="hidden" id="G2" size="5" maxlength="5" value="$G2" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kedah10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H2</strong>
        <input name="H2" type="hidden" id="H2" size="5" maxlength="5" value="$H2" >
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "7")
  #set($Penang1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Penang2 = $list.F5)
  #set($Penang4 = $list.F6)
  #set($Penang5 = $list.F7)
  #set($Penang6 = $list.F8)  
  #set($Penang7 = $list.F5+1)
  #set($Penang8 = $list.F6+1)
  #set($Penang9 = $list.F7+1)
  #set($Penang10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Penang2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C3</strong>
        <input name="C3" type="hidden" id="C3" size="5" maxlength="5" value="$C3" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Penang7 - $Penang4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D3</strong>
        <input name="D3" type="hidden" id="D3" size="5" maxlength="5" value="$D3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Penang8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E3</strong>
        <input name="E3" type="hidden" id="E3" size="5" maxlength="5" value="$E3" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Penang5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F3</strong>
        <input name="F3" type="hidden" id="F3" size="5" maxlength="5" value="$F3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Penang9 - $Penang6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G3</strong>
        <input name="G3" type="hidden" id="G3" size="5" maxlength="5" value="$G3" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Penang10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H3</strong>
        <input name="H3" type="hidden" id="H3" size="5" maxlength="5" value="$H3" >
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "8")
  #set($Perak1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Perak2 = $list.F5)
  #set($Perak4 = $list.F6)
  #set($Perak5 = $list.F7)
  #set($Perak6 = $list.F8)  
  #set($Perak7 = $list.F5+1)
  #set($Perak8 = $list.F6+1)
  #set($Perak9 = $list.F7+1)
  #set($Perak10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Perak2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C4</strong>
        <input name="C4" type="hidden" id="C4" size="5" maxlength="5" value="$C4" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Perak7 - $Perak4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D4</strong>
        <input name="D4" type="hidden" id="D4" size="5" maxlength="5" value="$D4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Perak8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E4</strong>
        <input name="E4" type="hidden" id="E4" size="5" maxlength="5" value="$E4" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Perak5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F4</strong>
        <input name="F4" type="hidden" id="F4" size="5" maxlength="5" value="$F4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Perak9 - $Perak6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G4</strong>
        <input name="G4" type="hidden" id="G4" size="5" maxlength="5" value="$G4" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Perak10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H4</strong>
        <input name="H4" type="hidden" id="H4" size="5" maxlength="5" value="$H4" >
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "3")
  #set($Kelantan1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Kelantan2 = $list.F5)
  #set($Kelantan4 = $list.F6)
  #set($Kelantan5 = $list.F7)
  #set($Kelantan6 = $list.F8)  
  #set($Kelantan7 = $list.F5+1)
  #set($Kelantan8 = $list.F6+1)
  #set($Kelantan9 = $list.F7+1)
  #set($Kelantan10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Kelantan2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C5</strong>
        <input name="C5" type="hidden" id="C5" size="5" maxlength="5" value="$C5" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Kelantan7 - $Kelantan4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D5</strong>
        <input name="D5" type="hidden" id="D5" size="5" maxlength="5" value="$D5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Kelantan8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E5</strong>
        <input name="E5" type="hidden" id="E5" size="5" maxlength="5" value="$E5" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Kelantan5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F5</strong>
        <input name="F5" type="hidden" id="F5" size="5" maxlength="5" value="$F5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Kelantan9 - $Kelantan6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G5</strong>
        <input name="G5" type="hidden" id="G5" size="5" maxlength="5" value="$G5" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Kelantan10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H5</strong>
        <input name="H5" type="hidden" id="H5" size="5" maxlength="5" value="$H5" >
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "11")
  #set($Ganu1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Ganu2 = $list.F5)
  #set($Ganu4 = $list.F6)
  #set($Ganu5 = $list.F7)
  #set($Ganu6 = $list.F8)  
  #set($Ganu7 = $list.F5+1)
  #set($Ganu8 = $list.F6+1)
  #set($Ganu9 = $list.F7+1)
  #set($Ganu10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Ganu2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C6</strong>
        <input name="C6" type="hidden" id="C6" size="5" maxlength="5" value="$C6" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Ganu7 - $Ganu4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D6</strong>
        <input name="D6" type="hidden" id="D6" size="5" maxlength="5" value="$D6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Ganu8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E6</strong>
        <input name="E6" type="hidden" id="E6" size="5" maxlength="5" value="$E6" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Ganu5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F6</strong>
        <input name="F6" type="hidden" id="F6" size="5" maxlength="5" value="$F6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Ganu9 - $Ganu6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G6</strong>
        <input name="G6" type="hidden" id="G6" size="5" maxlength="5" value="$G6" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Ganu10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H6</strong>
        <input name="H6" type="hidden" id="H6" size="5" maxlength="5" value="$H6" >
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "6")
  #set($Pahang1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Pahang2 = $list.F5)
  #set($Pahang4 = $list.F6)
  #set($Pahang5 = $list.F7)
  #set($Pahang6 = $list.F8)  
  #set($Pahang7 = $list.F5+1)
  #set($Pahang8 = $list.F6+1)
  #set($Pahang9 = $list.F7+1)
  #set($Pahang10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Pahang2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C7</strong>
        <input name="C7" type="hidden" id="C7" size="5" maxlength="5" value="$C7" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Pahang7 - $Pahang4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D7</strong>
        <input name="D7" type="hidden" id="D7" size="5" maxlength="5" value="$D7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Pahang8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E7</strong>
        <input name="E7" type="hidden" id="E7" size="5" maxlength="5" value="$E7" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Pahang5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F7</strong>
        <input name="F7" type="hidden" id="F7" size="5" maxlength="5" value="$F7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Pahang9 - $Pahang6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G7</strong>
        <input name="G7" type="hidden" id="G7" size="5" maxlength="5" value="$G7" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Pahang10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H7</strong>
        <input name="H7" type="hidden" id="H7" size="5" maxlength="5" value="$H7" >
      </div>  </td>
          </tr>
        </table>
        </td>
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "10")
  #set($Selangor1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Selangor2 = $list.F5)
  #set($Selangor4 = $list.F6)
  #set($Selangor5 = $list.F7)
  #set($Selangor6 = $list.F8)  
  #set($Selangor7 = $list.F5+1)
  #set($Selangor8 = $list.F6+1)
  #set($Selangor9 = $list.F7+1)
  #set($Selangor10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Selangor2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C8</strong>
        <input name="C8" type="hidden" id="C8" size="5" maxlength="5" value="$C8" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Selangor7 - $Selangor4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D8</strong>
        <input name="D8" type="hidden" id="D8" size="5" maxlength="5" value="$D8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Selangor8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E8</strong>
        <input name="E8" type="hidden" id="E8" size="5" maxlength="5" value="$E8" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Selangor5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F8</strong>
        <input name="F8" type="hidden" id="F8" size="5" maxlength="5" value="$F8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Selangor9 - $Selangor6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G8</strong>
        <input name="G8" type="hidden" id="G8" size="5" maxlength="5" value="$G8" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Selangor10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H8</strong>
        <input name="H8" type="hidden" id="H8" size="5" maxlength="5" value="$H8" >
      </div>  </td>
          </tr>
        </table>
        </td>
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "14")
  #set($KL1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($KL2 = $list.F5)
  #set($KL4 = $list.F6)
  #set($KL5 = $list.F7)
  #set($KL6 = $list.F8)  
  #set($KL7 = $list.F5+1)
  #set($KL8 = $list.F6+1)
  #set($KL9 = $list.F7+1)
  #set($KL10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $KL2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C9</strong>
        <input name="C9" type="hidden" id="C9" size="5" maxlength="5" value="$C9" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$KL7 - $KL4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D9</strong>
        <input name="D9" type="hidden" id="D9" size="5" maxlength="5" value="$D9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $KL8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E9</strong>
        <input name="E9" type="hidden" id="E9" size="5" maxlength="5" value="$E9" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $KL5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F9</strong>
        <input name="F9" type="hidden" id="F9" size="5" maxlength="5" value="$F9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$KL9 - $KL6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G9</strong>
        <input name="G9" type="hidden" id="G9" size="5" maxlength="5" value="$G9" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $KL10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H9</strong>
        <input name="H9" type="hidden" id="H9" size="5" maxlength="5" value="$H9" >
      </div>  </td>
          </tr>
        </table>
        </td>
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "5")
  #set($N91 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($N92 = $list.F5)
  #set($N94 = $list.F6)
  #set($N95 = $list.F7)
  #set($N96 = $list.F8)  
  #set($N97 = $list.F5+1)
  #set($N98 = $list.F6+1)
  #set($N99 = $list.F7+1)
  #set($N910 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $N92</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C10</strong>
        <input name="C10" type="hidden" id="C10" size="5" maxlength="5" value="$C10" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$N97 - $N94</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D10</strong>
        <input name="D10" type="hidden" id="D10" size="5" maxlength="5" value="$D10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $N98</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E10</strong>
        <input name="E10" type="hidden" id="E10" size="5" maxlength="5" value="$E10" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $N95</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F10</strong>
        <input name="F10" type="hidden" id="F10" size="5" maxlength="5" value="$F10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$N99 - $N96</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G10</strong>
        <input name="G10" type="hidden" id="G10" size="5" maxlength="5" value="$G10" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $N910</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H10</strong>
        <input name="H10" type="hidden" id="H10" size="5" maxlength="5" value="$H10" >
      </div>  </td>
          </tr>
        </table>
        </td>
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
  #foreach($list in $senarai_kpisasaran)  
  #if($list.ID_NEGERI == "4")
  #set($Melaka1 = $list.F1 + $list.F2 + $list.F3 + $list.F4)
  #set($Melaka2 = $list.F5)
  #set($Melaka4 = $list.F6)
  #set($Melaka5 = $list.F7)
  #set($Melaka6 = $list.F8)  
  #set($Melaka7 = $list.F5+1)
  #set($Melaka8 = $list.F6+1)
  #set($Melaka9 = $list.F7+1)
  #set($Melaka10 = $list.F8+1)  
  #end 
  #end

    
    <table width="100%"  >
      <tr>
        <td valign="top"><table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Keputusan MMK/MB/KM/LC</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC" ><div align="center" class="style2"><strong>&lt; $Melaka2</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$C11</strong>
        <input name="C11" type="hidden" id="C11" size="5" maxlength="5" value="$C11" >
      </div>  </td>

          </tr>
          <tr>
           
            <td valign="top"   bgcolor="#FFFF99"><div align="center" class="style2"><strong>$Melaka7 - $Melaka4</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$D11</strong>
        <input name="D11" type="hidden" id="D11" size="5" maxlength="5" value="$D11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"><div align="center" class="style2"><strong>&gt; $Melaka8</strong></div></td>
            <td valign="top" class="row1" ><div align="center" class="style2">
        <strong>$E11</strong>
        <input name="E11" type="hidden" id="E11" size="5" maxlength="5" value="$E11" >
      </div>  </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td valign="top">
        <table width="100%" class="table_headerkpi" >
          <tr>
            <td valign="top" colspan="2" class="row1" ><div align="center" class="style2"><strong>Masih Menunggu Bayaran Pampasan oleh AP</strong></div></td>
          </tr>
          <tr>
            
            <td valign="top" width="70%"  bgcolor="#99FFCC"><div align="center" class="style2"><strong>&lt; $Melaka5</strong></div></td>
            <td valign="top" width="30%" class="row1"><div align="center" class="style2">
        <strong>$F11</strong>
        <input name="F11" type="hidden" id="F11" size="5" maxlength="5" value="$F11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FFFF99"> <div align="center" class="style2"><strong>$Melaka9 - $Melaka6</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$G11</strong>
        <input name="G11" type="hidden" id="G11" size="5" maxlength="5" value="$G11" >
      </div>  </td>
          </tr>
          <tr>
           
            <td valign="top"  bgcolor="#FF9999"> <div align="center" class="style2"><strong>&gt; $Melaka10</strong></div></td>
            <td valign="top" class="row1"><div align="center" class="style2">
        <strong>$H11</strong>
        <input name="H11" type="hidden" id="H11" size="5" maxlength="5" value="$H11" >
      </div>  </td>
          </tr>
        </table>
        </td>
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

