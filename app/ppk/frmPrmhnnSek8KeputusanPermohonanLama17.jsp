
<style type="text/css">
<!--
.style2 {color: #0000FF}
.style3 {color: #FF0000}
.style4 {
	font-style: italic;
	font-size: 9px;
}
.style5 {font-style: italic; font-size: 9px; color: #0000FF; }
.style6 {font-size: 10px}
.style7 {color: #FF0000; font-size: 9px; }
.style8 {color: #0000FF; font-style: italic;}
.style9 {font-size: 10px; color: #FF0000; }
.style10 {font-size: 9px}
.style11 {color: #000000}
.style21 {font-size: 10px; color: #000000; }
-->
</style>
<body onLoad="setTableA_J('tableReportA','tableReportX');submitForm()" >

 
<form id="f1" name="f1" action="" method="post">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="v_loc" id="v_loc" value="" />





#set ($tarikh_Hantar_BorangB = "")
#set ($tarikh_Terima_BorangC = "")
#set ($tarikh_Hantar_Nilaian = "")
#set ($tarikh_Terima_Nilaian = "")
#set ($jumlah_Hta_TarikhMohon = "0.00")
#set ($jumlah_Ha_TarikhMohon = "0.00")
#set ($jumlah_Harta_Tarikhmohon = "0.00")
<p></p>
#set ($noFail = "")
#set ($statusFail = "")
#set ($negeri = "")
#set ($tarikh_mohon = "")
#set ($daerah = "")
#set ($namasimati = "")
#set ($seksyen = "")
#set ($namapemohon = "")
#set ($namaUnit = "")
#set ($jumHta = "")
#set ($jumHa = "")
#set ($Overall = "")
#set ($jenisborangc = "")
#set ($catatan = "")
#set ($keputusan = "")
#set ($check1 = "")
#set ($check2 = "")
#set ($check3 = "")
#set ($check4 = "")
#set ($check5 = "")
#set ($check8 = "")
#set ($check9 = "")
#set ($check99 = "")

#set ($failawal = "")
#set ($mohonawal = "")

#set ($namapejabat = "")
#set ($alamat1pejabat = "")
#set ($alamat2pejabat = "")
#set ($alamat3pejabat = "")
#set ($poskodpejabat = "")
#set ($notel = "")
#set ($nofax = "")

#set ($id = "")
#set ($namaNegeri = "")
#set ($selected = "")

 #set($negerimahkamah = "")
 #set($daerahmahkamah = "")
 #set($negerimahkamahX = "")
 #set($daerahmahkamahX = "")
 
 #set($negerimahkamahXPindah = "")
 #set($daerahmahkamahXPindah = "")
 
 
 
#set ($tujuanPindah = "") <!-- razman add -->

#foreach ($ListFail in $ViewFail)
<input name="id_Suburusanstatus" type="hidden"  value="$ListFail.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$ListFail.id_Suburusanstatusfail"/>
#end


#foreach ($ListData in $ViewPemohon)


#set($jenis_permohonan = $ListData.jenispermohonan)
#set($idPemohon = $ListData.idPemohon)
#set($idSimati = $ListData.idSimati)
#set($idpermohonan_simati = $ListData.id_Permohonansimati)

#set ($tujuanPindah = $ListData.tujuanPindah)<!-- razman add -->


     <input name="id_taraf_mohon" type="hidden"  value="$ListData.id_taraf_mohon"/>
     <input name="taraf_pemohon" type="hidden"  value="$ListData.taraf_pemohon"/>
     
     #set($id_taraf_mohon = "$ListData.id_taraf_mohon")


    <input name="idFail" type="hidden"  value="$ListData.idFail"/>
    <input name="id_Fail" type="hidden"  value="$ListData.idFail"/>
        #set($idFail = $ListData.idFail)
   <input name="idpermohonansimati" type="hidden"  value="$ListData.id_Permohonansimati"/>
    


    #set($negeritertinggi = $ListData.negeritertinggi)
    #set($daerahtertinggi = $ListData.daerahtertinggi)
    
    #if($daerahtertinggi != 0 && $daerahtertinggi != "")
    #set($daerahpindah = "1")
    #end
    
    <input name="id_daerahpindah" type="hidden"  value="$ListData.daerahtertinggi"/>
    <input name="id_negeripindah" type="hidden"  value="$ListData.negeritertinggi"/>
      
      #set($id_daerahmohon = $ListData.daerahmhn)
    <input name="id_daerahmohon" type="hidden"  value="$ListData.daerahmhn"/>
      

	#set ($idPermohonan = $ListData.idPermohonan)
   
       
	#set ($noFail = $ListData.noFail)
    <input name="noFail" type="hidden"  value="$ListData.noFail"/>
    
	#set ($statusFail = $ListData.keterangan)
    <input name="keterangan" type="hidden"  value="$ListData.keterangan"/> 
       
    #set ($id_Status = $ListData.id_Status)
    <input name="id_Status" type="hidden"  value="$ListData.id_Status"/>   
     
	#set ($negeri = $ListData.namanegeri)
    <input name="namanegeri" type="hidden"  value="$ListData.namanegeri"/>
    
	#set ($tarikh_mohon = $ListData.tarikhMohon)
    <input name="tarikhMohon" type="hidden"  value="$ListData.tarikhMohon"/>
   <input name="tarikh_mohon" type="hidden"  value="$ListData.tarikhMohon"/>
    
	#set ($daerah = $ListData.namadaerah)
    <input name="namadaerah" type="hidden"  value="$ListData.namadaerah"/>
    
	#set ($namasimati = $ListData.namaSimati)
    <input name="namaSimati" type="hidden"  value="$ListData.namaSimati"/>
    
	#set ($seksyen = $ListData.seksyen)
    <input name="seksyen" type="hidden"  value="$ListData.seksyen"/>
    
	#set ($namapemohon = $ListData.namaPemohon)
    <input name="namaPemohon" type="hidden"  value="$ListData.namaPemohon"/>
    
	#set ($namaUnit = $ListData.namaPejabat)
    <input name="namaPejabat" type="hidden"  value="$ListData.namaPejabat"/>
    
	
		#set ($jumHta = $Util.formatDecimal($ListData.jumHtaTarikhMohon))
        #set ($jumHtaX = $ListData.jumHtaTarikhMohon)
        
      
      
      #set ($jumHtaX1 = $Util.formatDecimal($jumHtaX))  
	
    <input name="jumHtaTarikhMohon" type="hidden"  value="$ListData.jumHtaTarikhMohon"/>
    
	
		#set ($jumHa = $Util.formatDecimal($ListData.jumHaTarikhMohon))
         #set ($jumHaX = $ListData.jumHaTarikhMohon)
        #set ($jumHaX1 = $Util.formatDecimal($jumHaX))
        
	
    <input name="jumHaTarikhMohon" type="hidden"  value="$ListData.jumHaTarikhMohon"/>
   
		#set ($Overall =  $Util.formatDecimal($ListData.jumHartaTarikhMohon))
        #set ($OverallX =  $ListData.jumHartaTarikhMohon)
        #set ($OverallX1 =  $Util.formatDecimal($OverallX))
        
        
        #set($Overalldum =  $ListData.jumHartaTarikhMohon)
	
   <input name="jumHartaTarikhMohon" type="hidden"  value="$ListData.jumHartaTarikhMohon"/>
    
	#set ($tarikh_Hantar_BorangB = $ListData.tarikhborangB)
    <input name="tarikhborangB" type="hidden"  value="$ListData.tarikhborangB"/>
    
	#set ($tarikh_Terima_BorangC = $ListData.tarikhborangC)
    <input name="tarikhborangC" type="hidden"  value="$ListData.tarikhborangC"/>
    
	#set ($tarikh_Hantar_Nilaian = $ListData.tarikhhantarnilaian)
    <input name="tarikhhantarnilaian" type="hidden"  value="$ListData.tarikhhantarnilaian"/>
    
	#set ($tarikh_Terima_Nilaian = $ListData.tarikhterimanilaian)
    <input name="tarikhterimanilaian" type="hidden"  value="$ListData.tarikhterimanilaian"/>
    
    
    
	#set ($jenisborangc = $ListData.jenisborangC)
    <input name="jenisborangC" type="hidden"  value="$ListData.jenisborangC"/>
    #set ($jenisborangcX  = $ListData.jenisborangC)
    
    #if ($jenisborangc == "P")
	#set ($check8 = "checked")
   
    #elseif ($jenisborangc == "K")
	#set ($check9 = "checked")
   
    #end
    
	#set ($keputusan = $ListData.keputusanpermohonan)
    <input name="keputusanpermohonan" type="hidden"  value="$ListData.keputusanpermohonan"/>
    
    #set ($catatan = $ListData.catatan)
    <input name="catatan" type="hidden"  value="$ListData.catatan"/>
    
    
    #set($jenis_pejabat = $ListData.jenis_pej)
    
     #set($no_failawal = $ListData.nofailawal)
     #set($pemohon_awal = $ListData.pemohonawal)
     #set($jenis_pej_idd = $ListData.pejabatawal)
    
    
   <input name="negerimahkamah" type="hidden"  value="$ListData.negerimahkamah"/>
   <input name="daerahmahkamah" type="hidden"  value="$ListData.daerahmahkamah"/>
    
    #set($negerimahkamah = $ListData.negerimahkamah)
    #set($daerahmahkamah = $ListData.daerahmahkamah)
    
    
    <!--
    	h.put("id_Daerah_Mahkamah", rs.getString("id_Daerah_Mahkamah")==null?"":rs.getString("id_Daerah_Mahkamah"));
				h.put("id_Negerimahkamah", rs.getString("id_Negerimahkamah")==null?"":rs.getString("id_Negerimahkamah"));
				h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
				
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));				
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));				
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				
				h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
				h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
				
    -->
    
    
    
   
    
#end

#parse("app/ppk/syarat_kemaskini.jsp")


#foreach ($vp in $ViewPemohon)
#set ($k_p = $vp.keputusanpermohonan)
#end
#if($k_p == "")
#foreach ($list_nilai in $list_status_nilai)
#set ($tarikh_Hantar_Nilaian = $list_nilai.TARIKH_MASUK)
#end
#foreach ($list_tunggu in $list_status_tunggu)
#set ($tarikh_Hantar_BorangB = $list_tunggu.TARIKH_MASUK)
#end
#end
 

#if($eve == 8)

#set ($tujuanPindah = $tujuanPindah) <!-- razman add -->

#set ($tarikh_Hantar_BorangB = $tarikh_Hantar_BorangB)
#set ($tarikh_Terima_BorangC = $tarikh_Terima_BorangC)
#set ($tarikh_Hantar_Nilaian = $tarikh_Hantar_Nilaian)
#set ($tarikh_Terima_Nilaian = $tarikh_Terima_Nilaian)

    #set ($noFail = $noFail)   
	#set ($statusFail = $statusFail)
    #set ($id_Status = $id_Status)
	#set ($negeri =$negeri)
	#set ($tarikh_mohon = $tarikh_mohon)   
	#set ($daerah = $daerah)   
	#set ($namasimati = $namasimati)   
	#set ($seksyen = $seksyen)  
	#set ($namapemohon = $namapemohon)   
	#set ($namaUnit = $namaUnit)

#set ($jumHta = $jumHtaX1)
#set ($jumHa = $jumHaX1)
#set ($Overall = $OverallX1)



      #set($failawal = $no_failawal)
       #set($mohonawal = $pemohon_awal)
       #set($jenis_pej_id = $jenis_pej_idd)


#set($jenis_pej = $jenis_pejabat)


#if($jenis_pej == 9 )

#set($checkJ1="checked")

#elseif($jenis_pej == 2)

#set($checkJ2="checked")

#elseif($jenis_pej == 8)
#set($checkJ3="checked")
#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")

#end

 

  #if ($jenisborangcX == "P")
  
  #set ($check8 = "checked") 
  
  #elseif($jenisborangcX == "K")
  
  #set ($check9 = "checked") 
  
  #end
  
 #set($all=$Overalldum)
  
 #if ($keputusan == "151")
 

 #set ($check1 = "checked")  
 #set ($check0 = "checked") 
 

 #elseif ($keputusan == "152")
 
 
 
 #set ($check2 = "checked")   
 #set ($check0 = "checked") 
 
 #elseif ($keputusan == "56")
 
 #set ($check99 = "checked")   
 
#elseif ($keputusan == "50")
 
 #set ($check4 = "checked")  
     
   
 
 #elseif ($keputusan == "53")
 
 #set ($check3 = "checked")
 
 #elseif ($keputusan == "70")
 
 #set ($check5 = "checked")   

     
 #end  
  
  #set($negerimahkamahX = $negerimahkamah)
  #set($daerahmahkamahX = $daerahmahkamah)
  
   #set($negerimahkamahXPindah = $negeritertinggi)
   #set($daerahmahkamahXPindah = $daerahtertinggi)



#end



 
  
  




#if ($EventStatus == 0 && $id_Status != 14 )
 #set($negerimahkamahX = $negerimahkamah)
  #set($daerahmahkamahX = $daerahmahkamah)
  
   #set($negerimahkamahXPindah = $negeritertinggi)
   #set($daerahmahkamahXPindah = $daerahtertinggi)


   #set ($viewcheckJ1 = "")
     #set ($viewcheckJ2 = "")
     #set ($viewcheckJ3 = "")

#if ($jenisborangc == "P")
	#set ($check8 = "checked")
   
#elseif ($jenisborangc == "K")
	#set ($check9 = "checked")
   
#end

  #if ($keputusan == "53")
	#set ($check3 = "checked")
    #set ($check99 = "")
    #set ($check1 = "")
    #set ($check2 = "")
    
   
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
    
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "")
    #set ($setMode99 = "")
        #set ($setMode5 = "")<!-- razman remove disable -->
    #set ($setMode6 = "")
    
   #elseif ($keputusan == "56")
	#set ($check99 = "checked")
    #set ($check1 = "")
    #set ($check3 = "")
    #set ($check2 = "")
    
   
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
    
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "")
    #set ($setMode99 = "")
    #set ($setMode5 = "disabled")
    #set ($setMode6 = "")
    
    #elseif ($keputusan == "151")
	#set ($check0 = "checked")
    #set ($check1 = "checked")
    
    #set ($setMode = "")
    #set ($setMode1 = "")
    
    #if($Overalldum >= 600000)
    
    #if($id_taraf_mohon == "6")
    #set ($setMode2 = "")
    #else
    #set ($setMode2 = "disabled")
    #end
   
    #else
    #set ($setMode2 = "")
    #end
    
    #set ($setMode3 = "")
    #set ($setMode4 = "disabled")
     #set ($setMode99 = "disabled")
    #set ($setMode5 = "disabled")
    #set ($setMode6 = "")
    
    
     #set($failawal = $no_failawal)
       #set($mohonawal = $pemohon_awal)
       #set($jenis_pej_id = $jenis_pej_idd)


#set($jenis_pej = $jenis_pejabat)


#if($jenis_pej == 9 )

#set($checkJ1="checked")

#elseif($jenis_pej == 2)

#set($checkJ2="checked")

#elseif($jenis_pej == 8)
#set($checkJ3="checked")
#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")

#end
    
    
    #elseif ($keputusan == "152")
	#set ($check0 = "checked")
    #set ($check2 = "checked")
    
     #set ($setMode = "")
    #set ($setMode1 = "")
    
  #set($OD = $Overalldum)
    
    #if($OD >= 600000)
    
    #if($id_taraf_mohon == "6")
    #set ($setMode2 = "")
    #else
    #set ($setMode2 = "disabled")
    #end
    #else
    #set ($setMode2 = "")
    #end
   
    
    #set ($setMode3 = "")
    #set ($setMode4 = "disabled")
     #set ($setMode99 = "disabled")
    #set ($setMode5 = "disabled")
    #set ($setMode6 = "")
    
     #set($failawal = $no_failawal)
       #set($mohonawal = $pemohon_awal)
       #set($jenis_pej_id = $jenis_pej_idd)


#set($jenis_pej = $jenis_pejabat)


#if($jenis_pej == 9 )

#set($checkJ1="checked")

#elseif($jenis_pej == 2)

#set($checkJ2="checked")

#elseif($jenis_pej == 8)
#set($checkJ3="checked")
#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")

#end
    
   
   
#elseif ($keputusan == "50")
	#set ($check4 = "checked")
    #set ($check1 = "")
    #set ($check2 = "")
    
     #set ($setMode = "")
    #set ($setMode1 = "disabled")
    
   
    #set ($setMode2 = "disabled")
    
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "disabled")
    #set ($setMode99 = "disabled")
    #set ($setMode5 = "")
    #set ($setMode6 = "")
   
#elseif ($keputusan == "70")

    #if($Overalldum >= 600000)
    
    #set ($check5 = "checked")
    #set ($check1 = "")
    #set ($check2 = "")  
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    
   
    
    #set ($setMode2 = "disabled")
    
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "disabled")
    #set ($setMode99 = "disabled")
    #set ($setMode5 = "") 
    #set ($setMode6 = "")
    
    #else
	#set ($check5 = "checked")
    #set ($check1 = "")
    #set ($check2 = "")  
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    
   
    #set ($setMode2 = "disabled")
     
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "")
    #set ($setMode99 = "")
    #set ($setMode5 = "disabled") 
    #set ($setMode6 = "")
    #end
    
    
   


#end



#elseif ($EventStatus == 1 && $id_Status != 14 )

 #set($negerimahkamahX = $negerimahkamah)
  #set($daerahmahkamahX = $daerahmahkamah)
  
   #set($negerimahkamahXPindah = $negeritertinggi)
   #set($daerahmahkamahXPindah = $daerahtertinggi)

    
    #set ($EventStatus = 1)
    #set ($setMode = "disabled")
    #set ($setMode1 = "disabled")
    
  
    #set ($setMode2 = "disabled")
     
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "disabled")
    #set ($setMode99 = "disabled")
    #set ($setMode5 = "disabled")
    #set ($setMode6 = "disabled")
    
     #set ($viewcheckJ1 = "disabled")
     #set ($viewcheckJ2 = "disabled")
     #set ($viewcheckJ3 = "disabled")
    
       
    
    
    #if ($jenisborangc == "P")
	#set ($check8 = "checked")
   
#elseif ($jenisborangc == "K")
	#set ($check9 = "checked")
   
#end
    
    
    #if ($keputusan == "53")
	#set ($check3 = "checked")
      #set ($check99 = "")
    #set ($check1 = "")
    #set ($check2 = "")
   
    
     #elseif ($keputusan == "56")
    #set ($check99 = "checked")
	#set ($check3 = "")
    #set ($check1 = "")
    #set ($check2 = "")
    
    #elseif ($keputusan == "151")
	#set ($check0 = "checked")
    #set ($check1 = "checked")
    
     #set($failawal = $no_failawal)
       #set($mohonawal = $pemohon_awal)
       #set($jenis_pej_id = $jenis_pej_idd)


#set($jenis_pej = $jenis_pejabat)


#if($jenis_pej == 9 )

#set($checkJ1="checked")

#elseif($jenis_pej == 2)

#set($checkJ2="checked")

#elseif($jenis_pej == 8)
#set($checkJ3="checked")
#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")

#end
    
    
    
    
    #elseif ($keputusan == "152")
	#set ($check0 = "checked")
    #set ($check2 = "checked")
    
     #set($failawal = $no_failawal)
       #set($mohonawal = $pemohon_awal)
       #set($jenis_pej_id = $jenis_pej_idd)


#set($jenis_pej = $jenis_pejabat)


#if($jenis_pej == 9 )

#set($checkJ1="checked")

#elseif($jenis_pej == 2)

#set($checkJ2="checked")

#elseif($jenis_pej == 8)
#set($checkJ3="checked")
#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")

#end
   
   
#elseif ($keputusan == "50")
	#set ($check4 = "checked")
    #set ($check1 = "")
    #set ($check2 = "")
   
#elseif ($keputusan == "70")
	#set ($check5 = "checked")
    #set ($check1 = "")
    #set ($check2 = "")   

#end

    
    
    
#elseif ($EventStatus == 1 && $id_Status == 14 )
  #set($negerimahkamahX = $negerimahkamah)
  #set($daerahmahkamahX = $daerahmahkamah)
  
   #set($negerimahkamahXPindah = $negeritertinggi)
   #set($daerahmahkamahXPindah = $daerahtertinggi)


  
	
 #set ($EventStatus = 0)  
 #set($dum=$Overalldum)

    #if($dum >= 600000)   
 
    #set ($check8 = "checked")
    #set  ($check3 = "") 
     #set  ($check99 = "") 
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    
   
    #set ($setMode2 = "disabled")
   
    
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "disabled")
     #set ($setMode99 = "disabled")
    #set ($setMode5 = "")
    #set ($setMode6 = "")
      
    #set  ($check0 = "")   
    #set  ($check1 = "")   
    #set  ($check2 = "") 
    #set  ($check4 = "checked")   
    #set  ($check5 = "")      
	#set ($check9 = "")
    
    #set($catatan = $catatanx)
    
    #else
       
       
    #if ($jenisborangc == "P")
	#set ($check8 = "checked")
   
    #set  ($check3 = "") 
    #set  ($check99 = "")
    
    #set ($setMode = "")
    #set ($setMode1 = "")
    
   
    
    #set ($setMode2 = "")
     
    #set ($setMode3 = "")
    #set ($setMode4 = "disabled")
     #set ($setMode99 = "disabled")
    #set ($setMode5 = "disabled")
    #set ($setMode6 = "disabled")
      
    #set  ($check0 = "checked")   
    #set  ($check1 = "checked")   
    #set  ($check2 = "") 
    #set  ($check4 = "")   
    #set  ($check5 = "")  
    
#elseif ($jenisborangc == "K")
	#set ($check9 = "checked")
    #set  ($check3 = "checked")
    #set  ($check99 = "") 
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
  
    
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "")
     #set ($setMode99 = "")
    #set ($setMode5 = "disabled")
    #set ($setMode6 = "")
      
    #set  ($check0 = "")   
    #set  ($check1 = "")   
    #set  ($check2 = "") 
    #set  ($check4 = "")   
    #set  ($check5 = "")      
	#set ($check9 = "")
    
    
#else
	#set ($check8 = "checked")
    #set ($check9 = "")
    #set  ($check3 = "checked") 
     #set  ($check99 = "") 
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
   
    
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "")
    #set ($setMode99 = "")
    
    #set ($setMode5 = "")<!-- razman buang disable -->
    #set ($setMode6 = "")
      
    #set  ($check0 = "")   
    #set  ($check1 = "")   
    #set  ($check2 = "") 
    #set  ($check4 = "")   
    #set  ($check5 = "")      
	
#end


#end

#end


#foreach($listMaklumat in $listMaklumatMahkamah)
    
    #set($nama_pejabat = $listMaklumat.nama_pejabat)
    #set($alamat1 = $listMaklumat.alamat1)
    #set($alamat2 = $listMaklumat.alamat2)
    #set($alamat3 = $listMaklumat.alamat3)
    #set($poskod = $listMaklumat.poskod)
    #set($no_tel = $listMaklumat.no_tel)
    #set($no_fax = $listMaklumat.no_fax)
    #set($id_Daerah_Mahkamah = $listMaklumat.daerah)
    #set($id_Negerimahkamah = $listMaklumat.negeri)
    
#end


#if($listMaklumatMahkamah.size()>0)

#end


 
 
 


<input type="hidden" name="jumlah" id="jumlah" value="$Overalldum"  />

 <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
  <input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/>
  #if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end

<br>
<fieldset>
<fieldset id="header_lama" style="display:none">
<legend>MAKLUMAT PERMOHONAN</legend>
<table width="100%">
  <tr>
    <td width="60%" valign="top"><table width="100%">
      <tr>
        <td width="29%"><div align="right">NO FAIL</div></td>
        <td width="1%">:</td>
        <td width="70%"><span class="style2">$noFail.toUpperCase()</span></td>
      </tr>
      <tr>
        <td><div align="right">NEGERI</div></td>
        <td>:</td>
        <td><span class="style2">$negeri.toUpperCase()</span></td>
      </tr>
      <tr>
        <td><div align="right">DAERAH/JAJAHAN</div></td>
        <td>:</td>
        <td><span class="style2">$daerah.toUpperCase()</span></td>
      </tr>
      <tr>
        <td><div align="right">UNIT</div></td>
        <td>:</td>
        <td><span class="style2">$namaUnit.toUpperCase()</span></td>
      </tr>
      <tr>
        <td><div align="right">SEKSYEN</div></td>
        <td>:</td>
        <td><span class="style2">$seksyen</span></td>
      </tr>
    </table></td>
    <td width="40%" valign="top"><table width="100%">
      <tr>
        <td width="29%"><div align="right">STATUS FAIL</div></td>
        <td width="1%">:</td>
        <td width="70%"><span class="style2">$statusFail.toUpperCase()</span></td>
      </tr>
      <tr>
        <td><div align="right">TARIKH MOHON</div></td>
        <td>:</td>
        <td><span class="style2">$tarikh_mohon.toUpperCase()</span>
        <input type="hidden" value="$tarikh_mohon" name="tarikh_permohonan" id="tarikh_permohonan" />
        </td>
      </tr>
      <tr>
        <td><div align="right">NAMA SIMATI</div></td>
        <td>:</td>
        <td><span class="style2">$namasimati.toUpperCase()</span></td>
      </tr>
      <tr>
        <td><div align="right">NAMA PEMOHON</div></td>
        <td>:</td>
        <td><span class="style2">$namapemohon.toUpperCase()</span></td>
      </tr>
      <tr>
        <td><div align="right"></div></td>
        <td>&nbsp;</td>
        <td></td>
      </tr>
    </table></td>
  </tr>
</table>

</fieldset>


#if($!headerppk.size()>0)
#else
<script  type="text/javascript">
if(document.getElementById("header_lama").style.display=="none")
{
document.getElementById("header_lama").style.display="block";
}
</script>
#end


<table width="100%" border="0">
#if($setMode == "disabled") 
#set($setModeR = "readonly") 
#else #set($setModeR = "") 
#set($setMode = "") 
#end 
  <tr>
    <td width="50%" scope="col" valign="top"><fieldset>
    <legend>TARIKH</legend>
    <table width="100%" border="0">
      
      <tr>
      <td width="2%" valign="top">
      <!--#if($setMode != "disabled")
         <font color="red">*</font>
         #end --></td>
        <td width="40%" scope="row"><strong>Hantar Nilaian</strong></td>
        <td width="58%"><input name="txdTarikhHantarNilaian" type="text" class="$setMode" id="txdTarikhHantarNilaian"  value="$tarikh_Hantar_Nilaian" size="11" maxlength="10"  $setModeR onBlur="trans_date3(this.value);checkDateKP()"   onKeyPress="checkDateKP()"  onFocus="checkDateKP()" />
          &nbsp;
          #if ($EventStatus != 1)
          <a href="javascript:displayDatePicker('txdTarikhHantarNilaian',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a> <span class="style5"> dd/mm/yyyy</span></td>
          #end      </tr>
      <tr>
      <td  valign="top">&nbsp;</td>
        <td scope="row"><strong>Terima Nilaian</strong></td>
        <td><input name="txdTarikhTerimaNilaian" type="text" class="$setMode" id="txdTarikhTerimaNilaian"   value="$tarikh_Terima_Nilaian" size="11" maxlength="10" $setModeR  onBlur="trans_date4(this.value)" />
         &nbsp;
		#if ($EventStatus != 1)
		<a href="javascript:displayDatePicker('txdTarikhTerimaNilaian',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a><span class="style5"> dd/mm/yyyy</span>
      	#end   
        </td>
           </tr>
        <tr >
      <td></td>
      <td valign="top" colspan="2"><div id="alert_field" style="display:none"></div>
      <br><br>

      </td>      
      </tr>
      <tr style="display:none">
      <td  >
       <input name="txdTarikhHantarBorangB" type="hidden" class="$setMode" id="txdTarikhHantarBorangB"  value="$tarikh_Hantar_BorangB" size="11" maxlength="10" $setModeR  onBlur="trans_date1(this.value)" /> 
        <input name="txdTarikhTerimaBorangC"  type="hidden" class="$setMode" id="txdTarikhTerimaBorangC"  value="$tarikh_Terima_BorangC" size="11" maxlength="10" $setModeR onBlur="trans_date2(this.value)" />
     
     <label id="divCheckbox" style="visibility: hidden;"   >
        <input type="radio" name="sorKeputusanBorangC" id="sorKeputusanBorangC" value="P" $check8 $setMode onClick="putih();setTableB('tableReportA','tableReportX')"/>
        <input type="radio" name="sorKeputusanBorangC" id="sorKeputusanBorangC" value="K" $check9 $setMode onClick="kuning();setTableA('tableReportA','sorKeputusanBorangC','sorPenentuanBidangKuasa')"/>
        <input name="sorPenentuanBidangKuasa" type="radio" id="sorPenentuanBidangKuasa" $check0  onclick="selectRadio1();setTableA('tableReportA','sorKeputusanBorangC','sorPenentuanBidangKuasa')" value="1" $setMode1 />
        
          <input name="setMode1" id="setMode1" type="hidden" value="$setMode1" >
          
          <input type="radio" name="sorPenentuanBidangKuasaTeruskan" id="sorPenentuanBidangKuasaTeruskan" $check1 value="151" $setMode2 onClick="selectRadio2();setTableA('tableReportA','sorKeputusanBorangC','sorPenentuanBidangKuasa')" />
            <input name="setMode2" id="setMode2" type="hidden" value="$setMode2" >
     
        	<input type="radio" name="sorPenentuanBidangKuasaTeruskan" id="sorPenentuanBidangKuasaTeruskan" $check2 value="152" $setMode3 onClick="selectRadio3();setTableA('tableReportA','sorKeputusanBorangC','sorPenentuanBidangKuasa')" />
            <input name="setMode3" id="setMode3" type="hidden" value="$setMode3" >
            <fieldset id="tableReportA"     style="display:none;" >
            </fieldset>
       </label></td>
      <td colspan="2"></td>
      </tr>
    </table>
    </fieldset></td>
    <td width="50%" scope="col" valign="top">
    
     
 	  #set($nilai_Hta_memohon_XS = 0.00)
	  #foreach ($L1 in $listHTAX)  
      #if($L1.nilai_Hta_memohon!="")
      #set($nilai_Hta_memohon_XS = $nilai_Hta_memohon_XS + $L1.nilai_Hta_memohon)
      #end
      #end
      
      #set($nilai_Hta_memohon_XD = 0.00)
      #foreach ($L2 in $listHTAXdulu)
      #if($L2.nilai_Hta_memohon!="")
      #set($nilai_Hta_memohon_XD = $nilai_Hta_memohon_XD + $L2.nilai_Hta_memohon)
      #end
      #end
      
      #set($nilai_Hta_memohon_S =  0.00)
      #foreach ($L3 in $listHTA)
      #if($L3.nilai_Hta_memohon!="")
      #set($nilai_Hta_memohon_S = $nilai_Hta_memohon_S + $L3.nilai_Hta_memohon) 
      #end    
      #end
      
      #set($nilai_Hta_memohon_D =  0.00)    
      #foreach ($L4 in $listHTAdulu)
      #if($L4.nilai_Hta_memohon!="")
      #set($nilai_Hta_memohon_D = $nilai_Hta_memohon_D + $L4.nilai_Hta_memohon)   
      #end  
      #end
      
      #set($nilai_Ha_memohon_S = 0.00)
      #foreach ($L5 in $listHa)
      #if($L5.nilai_tarikhmohon!="")
      #set($nilai_Ha_memohon_S = $nilai_Ha_memohon_S + $L5.nilai_tarikhmohon)
      #end
      #end
      
      #set($nilai_Ha_memohon_D = 0.00)
      #foreach ($L6 in $listHadulu)
      #if($L6.nilai_tarikhmohon!="")
      #set($nilai_Ha_memohon_D = $nilai_Ha_memohon_D + $L6.nilai_tarikhmohon) 
      #end     
      #end 
            
      #set($harta_terdahulu = $nilai_Hta_memohon_XD + $nilai_Hta_memohon_D + $nilai_Ha_memohon_D)
      #set($harta_terbaru = $nilai_Hta_memohon_XS + $nilai_Hta_memohon_S + $nilai_Ha_memohon_S)
      
      #set($harta_terbaru_takalih_S = $nilai_Hta_memohon_XS + $nilai_Hta_memohon_S)
      #set($harta_terbaru_alih_S = $nilai_Ha_memohon_S)
      
      #set($harta_terdahulu_takalih_D = $nilai_Hta_memohon_XD + $nilai_Hta_memohon_D)
      #set($harta_terdahulu_alih_D = $nilai_Ha_memohon_D)
      
      #set($harta_harta = $harta_terdahulu + $harta_terbaru)
      
     
    <!--
    <fieldset><legend>NILAI KESELURUHAN</legend>
    <table width="100%" border="0">
      <tr>
        <td scope="col" width="50%"><strong>Harta Tak Alih (RM)</strong></td>
        <td scope="col" width="50%"><label>
        
        #if($jumHta  == ".00")
        #set($jumHta = "0.00")
        #end
        
         #if($jumHa == ".00")
        #set($jumHa = "0.00")
        #end
        
         #if($Overall  == ".00")
        #set($Overall = "0.00")
        #end
          <input type="text" name="txtNilaianHTA" id="txtNilaianHTA" style="text-align: right;" value="$jumHta" class="disabled" readonly />
        </label></td>
      </tr>
      <tr>
        <td scope="row"><strong>Harta Alih (RM)</strong></td>
        <td><input type="text" name="txtNilaianHA" id="txtNilaianHA" style="text-align: right;" value="$jumHa" class="disabled" readonly /></td>
      </tr>
      <tr>
        <td height="32" scope="row"><strong>Nilai Keseluruhan (RM)</strong></td>
        <td><input type="text" name="txtJumKeseluruhan" id="txtJumKeseluruhan" style="text-align: right;" value="$Overall" class="disabled" readonly /></td>
      </tr>
    </table>
    
    
    </fieldset>
    -->
     <fieldset><legend>NILAI KESELURUHAN</legend>
  
  	  #if($harta_terbaru_takalih_S > 0)
      #set($harta_terbaru_takalih_SC = $Util.formatDecimal($harta_terbaru_takalih_S))
      #else
      #set($harta_terbaru_takalih_SC = "0.00")
      #end
      
      #if($harta_terbaru_alih_S > 0)
      #set($harta_terbaru_alih_SC = $Util.formatDecimal($harta_terbaru_alih_S))
      #else
      #set($harta_terbaru_alih_SC = "0.00")
      #end
      
      #if($harta_terdahulu_takalih_D > 0)
      #set($harta_terdahulu_takalih_DC = $Util.formatDecimal($harta_terdahulu_takalih_D))
      #else
      #set($harta_terdahulu_takalih_DC = "0.00")
      #end
      
      #if($harta_terdahulu_alih_D > 0)
      #set($harta_terdahulu_alih_DC = $Util.formatDecimal($harta_terdahulu_alih_D))
      #else
      #set($harta_terdahulu_alih_DC = "0.00")
      #end
      
      #if($harta_terdahulu > 0)
      #set($harta_terdahuluC = $Util.formatDecimal($harta_terdahulu))
      #else
      #set($harta_terdahuluC = "0.00")
      #end
      
      #if($harta_terbaru > 0)
      #set($harta_terbaruC = $Util.formatDecimal($harta_terbaru))
      #else
      #set($harta_terbaruC = "0.00")
      #end
      
      #if($harta_harta > 0)
      #set($harta_hartaC = $Util.formatDecimal($harta_harta))
      #else
      #set($harta_hartaC = "0.00")
      #end
     
    
    <table width="100%" border="0">
    <!--
      <tr>
        <td scope="col" width="60%">Harta Tak Alih Terdahulu (RM)</td>
        <td scope="col" width="1%"><div align="center"><strong>:</strong></div></td>
        <td scope="col" width="50%"><div align="right">
          <input type="text" name="txtJumKeseluruhan2" id="txtJumKeseluruhan2" style="text-align: right;" value="$harta_terdahulu_takalih_DC" class="disabled" readonly />
        </div></td>
      </tr>
      <tr>
        <td scope="col">Harta Alih Terdahulu (RM)</td>
        <td scope="col"><div align="center"><strong>:</strong></div></td>
        <td scope="col"><div align="right">
          <input type="text" name="txtJumKeseluruhan3" id="txtJumKeseluruhan3" style="text-align: right;" value="$harta_terdahulu_alih_DC" class="disabled" readonly />
        </div></td>
      </tr>
      -->
      <input type="hidden" name="txtJumKeseluruhan2" id="txtJumKeseluruhan2" style="text-align: right;" value="$harta_terdahulu_takalih_DC" class="disabled" readonly />
      <input type="hidden" name="txtJumKeseluruhan3" id="txtJumKeseluruhan3" style="text-align: right;" value="$harta_terdahulu_alih_DC" class="disabled" readonly />
        
      
      
      <tr>
      
      
      
      
     
     
      
      
        <td scope="col" width="60%" >Harta Tak Alih Tambahan (RM)</td>
        <td scope="col"><div align="center"><strong>:</strong></div></td>
        <td scope="col" width="50%"><label>
        
        
        
        
        
        
         <div align="right">#if($jumHta  == ".00")
           #set($jumHta = "0.00")
           #end
           
           #if($jumHa == ".00")
           #set($jumHa = "0.00")
           #end
           
           #if($Overall  == ".00")
           #set($Overall = "0.00")
           #end
           <!--
          <input type="text" name="txtNilaianHTA" id="txtNilaianHTA" style="text-align: right;" value="$jumHta" class="disabled" readonly />
          -->
           <input type="text" name="txtNilaianHTA" id="txtNilaianHTA" style="text-align: right;" value="$harta_terbaru_takalih_SC" class="disabled" readonly />
          </div>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Harta Alih Tambahan (RM)</td>
        <td><div align="center"><strong>:</strong></div></td>
        <td>
          <div align="right">
            <!--
        <input type="text" name="txtNilaianHA" id="txtNilaianHA" style="text-align: right;" value="$jumHa" class="disabled" readonly />
        -->
            
            <input type="text" name="txtNilaianHA" id="txtNilaianHA" style="text-align: right;" value="$harta_terbaru_alih_SC" class="disabled" readonly />        
            </div></td>
      </tr>
      <tr>
        <td ></td>
        <td ><div align="center"></div></td>
        <td ><div align="right"></div></td>
      </tr>
      
      <tr>
        <td scope="col"><strong>Nilai Harta Terdahulu (RM)</strong></td>
        <td scope="col"><div align="center"><strong>:</strong></div></td>
        <td scope="col">
          <div align="right"><strong>$harta_terdahuluC
              <input type="hidden" name="txtJumKeseluruhan4" id="txtJumKeseluruhan4" style="text-align: right;" value="$harta_terdahuluC" class="disabled" readonly />
            </strong></div></td>
      </tr>
      <tr>
        <td  scope="row"><strong>Nilai Harta Tambahan (RM)</strong></td>
        <td><div align="center"><strong>:</strong></div></td>
        <td>
          <div align="right"><strong>$harta_terbaruC
              <input type="hidden" name="txtJumKeseluruhan5" id="txtJumKeseluruhan5" style="text-align: right;" value="$harta_terbaruC" class="disabled" readonly />
          </strong></div></td>
      </tr>
      <tr>
        <td  scope="row"><strong>Nilai Keseluruhan (RM)</strong></td>
        <td><div align="center"><strong>:</strong></div></td>
        <td>
          <div align="right"><strong>$harta_hartaC</strong>
            <input type="hidden" name="txtJumKeseluruhan" id="txtJumKeseluruhan" style="text-align: right;" value="$harta_hartaC" class="disabled" readonly />
          </div></td>
      </tr>
    </table>
   
    </fieldset>
    
    
    </td>
  </tr>
  <tr>
    <td height="280" colspan="2" scope="col">
    <fieldset>
    <legend><label id="sorKeputusanBorangCX" >PENENTUAN BIDANG KUASA</label></legend>
    
    <table width="100%" border="0">
      <tr>
        <td width="15%" scope="row"></td>
        <td width="1%">&nbsp;</td>
        <td width="84%">
		<table width="70%" border="0">
</table>
              <input type="hidden" name="viewcheckJ1" id="viewcheckJ1" value="$viewcheckJ1"  />
             <input type="hidden" name="viewcheckJ2" id="viewcheckJ2" value="$viewcheckJ2"  />
             <input type="hidden" name="viewcheckJ3" id="viewcheckJ3" value="$viewcheckJ3"  />

        <input type="hidden" name="jenis_pej" id="jenis_pej" value="$jenis_pej" />
        <input type="hidden" name="jenis_pej_id" id="jenis_pej_id" value="$jenis_pej_id" />        </td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td>&nbsp;</td>
        <td><input type="radio" name="sorPenentuanBidangKuasa" id="sorPenentuanBidangKuasa" value="56" $check99 $setMode99 onClick="selectRadio99();setTablePindah('tableReportPindah')" />
       
          <input name="setMode99" id="setMode99" type="hidden" value="$setMode99" >
          Pindah - Kes dipindahkan ke daerah Harta Tertinggi Nilaian          </td>
      </tr>
      
      
      <tr>
        <td scope="row">&nbsp;</td>
        <td>&nbsp;</td>
        <td>
   <div id="tableReportPindah"    style="display:none;"   >
        <table width="70%">
  <tr>
    <td>
   
   <fieldset  >
<legend>MAKLUMAT PINDAH</legend>
<table width="100%">
  <tbody>
    <tr>
      <td valign="top"><span class="style9">#if($setMode != "disabled")*#end</span></td>
      <td width="29%"><div align="left" class="style7 style6 style11">
          <div align="left">#if($setMode != "disabled") Negeri #else
            Negeri
            #end </div>
      </div></td>
      <td width="1%">:</td>
      <td width="70%">
      
       #if($setMode == "disabled")
       
       #foreach ($list in $ListNegeriPindah) 
	     
		      #if ($list.idNegeri == $negerimahkamahXPindah )	 
              #set($listnamaNegeri = $list.namaNegeri)    
              #end
              
        #end
        
     							    #if( $negerimahkamahXPindah != "" && $negerimahkamahXPindah != "0" && $daerahpindah != "")
                                     <input name="np" value="$listnamaNegeri" size="45" style="text-transform:uppercase;"  $setModeR class="$setMode" />
                                     #else
                                     <input name="np" value="" size="34" style="text-transform:uppercase;"  $setModeR class="$setMode"  />
                                     #end
         
         
        #else 
      
       
      <select name="socNegeriPindah" id="socNegeriPindah"  $setMode onChange="getDaerahPindah(this.value,'sorKeputusanBorangCX','socDaerah')" style="text-transform:uppercase;" >
      
     
   
     #if( $negerimahkamahXPindah != "" && $negerimahkamahXPindah != "0" && $daerahpindah != "")
       
      	  #foreach ($list in $ListNegeriPindah) 
	     
		      #if ($list.idNegeri == $negerimahkamahXPindah )	 
              #set($listnamaNegeri = $list.namaNegeri)    
	      		
        <option value="$list.idNegeri" $selected>$list.namaNegeri</option>
        
		  	  #end
	  	  #end
          
          #foreach ($list in $ListNegeriPindah) 
	     
		      #if ($list.idNegeri != $negerimahkamahXPindah && ($list.idNegeri != 15 && $list.idNegeri != 12 && $list.idNegeri != 13 && $list.idNegeri != 17))	     
	      		
        <option value="$list.idNegeri" $selected>$list.namaNegeri</option>
        
		  	  #end
	  	  #end
	  	  
       #else   
         
        <option value="0">Sila Pilih</option>
        
	  	  #foreach ($list in $ListNegeriPindah)
          #if(( $list.idNegeri != 15 && $list.idNegeri != 12 && $list.idNegeri != 13 && $list.idNegeri != 17 ))
	  	  #set ($idnegeri = $list.idNegeri)
	      #set ($namaNegeri = $list.namaNegeri) 
	  	  	
        <option value="$idnegeri">$namaNegeri</option>
           #end
	      #end
   #end   
      </select>
      
      #end      </td>
    </tr>
    
    
    
    <tr>
      <td valign="top"><span class="style9">#if($setMode != "disabled")*#end</span></td>
      <td><div align="left" class="style7 style6 style11">
          <div align="left">#if($setMode != "disabled") Daerah #else
            Daerah
            
            #end</div>
      </div></td>
      <td>:</td>
      <td>
   #if($setMode == "disabled")
       
       
	     
		 #foreach ($listDaerah in $ListDaerahPindah) 
	    
      		#if ($listDaerah.idDaerah == $daerahmahkamahXPindah )
            
            #set($listDaerahnamaDaerah=$listDaerah.namaDaerah)
		      #end
        #end
        
     							    #if($daerahmahkamahXPindah !="" && $daerahmahkamahXPindah != "0" )
                                     <input name="dm" value="$listDaerahnamaDaerah" size="45" style="text-transform:uppercase;"  $setModeR class="$setMode" />
                                     #else
                                     <input name="dm" value="" size="34" style="text-transform:uppercase;"  $setModeR class="$setMode"  />
                                     #end
         
         
        #else
                              <select name="socDaerahPindah" id="socDaerahPindah" onChange="getAddressPindah('sorKeputusanBorangCX','socDaerah')" $setMode style="text-transform:uppercase;" onFocus="check_negeriPindah()">
        #if($daerahmahkamahXPindah != "" && $daerahmahkamahXPindah != "0" && $daerahpindah != "")
      
      #set ($idDaerah = "")
      #set ($namaDaerah = "")
       
      
      #foreach ($listDaerah in $ListDaerahPindah) 
	    
      		#if ($listDaerah.idDaerah == $daerahmahkamahXPindah )
            
            #set($listDaerahnamaDaerah=$listDaerah.namaDaerah)
		      
        <option value="$listDaerah.idDaerah" $selected>$listDaerah.namaDaerah</option>
        
      		#end
	  #end
    
      
       &&  $id_daerahmohon != $daerahmahkamahXPindah
      #foreach ($listDaerah in $ListDaerahPindah) 
	    
      		#if ($listDaerah.idDaerah != $daerahmahkamahXPindah )
		     
        <option value="$listDaerah.idDaerah" $selected>$listDaerah.namaDaerah</option>
        
      		#end
	  #end
      
	  
      #else
        <option value="0">Sila Pilih</option>
        
	  #foreach ($listDaerah in $ListDaerahPindah) 
	      #if ($id_daerahmohon != $listDaerah.idDaerah)
        <option value="$listDaerah.idDaerah" >$listDaerah.namaDaerah</option>
        #end
        
	  #end
 #end     
      </select>
      
      #end      </td>
    </tr>
    
   
	#foreach ($listM in $listMaklumatMahkamahJPindah)
  
    #if($daerahmahkamahXPindah == $listM.iddaerahurus )
		#set ($namapejabat = $listM.nama_pejabat)
		#set ($alamat1pejabat = $listM.alamat1)
		#set ($alamat2pejabat = $listM.alamat2)
		#set ($alamat3pejabat = $listM.alamat3)
		#set ($poskodpejabat = $listM.poskod)
		#set ($notel = $listM.no_tel)
		#set ($nofax = $listM.no_fax)
     #end
	#end

    #if($daerahmahkamahXPindah != "" && $daerahmahkamahXPindah != "0" && $daerahpindah != "")
    <tr>
      <td><span class="style3"></span></td>
      <td><div align="left" class="style7 style6 style11">
          <div align="left">Nama Pejabat JKPTG</div>
      </div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
       #if($namapejabat != "")
       $namapejabat
      #else
      -
      #end
     
          <input type="hidden" name="txtNamaMahkamah" value="$namapejabat" size="50" maxlength="50" disabled>      
          </span></td>
    </tr>
    <tr>
      <td><span class="style3"></span></td>
      <td><div align="left" class="style7 style6 style11">
          <div align="left">Alamat</div>
      </div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
      #if( $alamat1pejabat != "")
      $alamat1pejabat
      #else
      -
      #end
          <input type="hidden" name="txtAlamat1" maxlength="50" size="50" value="$alamat1pejabat" disabled>      
          </span></td>
    </tr>
    <tr>
      <td><span class="style3"></span></td>
      <td><div align="left"><span class="style5"><span class="style8"><span class="style6"><span class="style6"><span class="style11"></span></span></span></span></span></div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
       #if( $alamat2pejabat != "")
     $alamat2pejabat
      #else
      -
      #end
      
          <input type="hidden" name="txtAlamat2" maxlength="50" size="50" value="$alamat2pejabat" disabled>      
          </span></td>
    </tr>
    <tr>
      <td><span class="style3"></span></td>
      <td><div align="left"><span class="style5"><span class="style8"><span class="style6"><span class="style6"><span class="style11"></span></span></span></span></span></div></td>
      <td>:</td>
      <td>
      <span class="style2" style="text-transform:uppercase;">
       #if( $alamat3pejabat != "")
    $alamat3pejabat
      #else
      -
      #end
      
      <input name="txtAlamat3" type="hidden" disabled class="style2" value="$alamat3pejabat" size="50" maxlength="50">  
      </span>          </td>
      </tr>
    <tr>
      <td><span class="style3"></span></td>
      <td><div align="left" class="style7 style6 style11">
          <div align="left">Poskod</div>
      </div></td>
      <td>:</td>
      <td>
      <span class="style2" style="text-transform:uppercase;">
      #if( $poskodpejabat != "")
    $poskodpejabat
      #else
      -
      #end
      
      
          <input type="hidden" name="txtPoskod" maxlength="5" size="5" value="$poskodpejabat" disabled>      
          </span></td>
    </tr>
    <tr>
      <td><span class="style3"></span></td>
      <td><div align="left" class="style7 style6 style11">
          <div align="left">No Telefon</div>
      </div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
      #if($notel != "")
    $notel
      #else
      -
      #end
      
      
          <input type="hidden" name="txtTelefon" maxlength="50" size="15" value="$notel" disabled>      
          </span></td>
    </tr>
    <tr>
      <td><span class="style3"></span></td>
      <td><div align="left" class="style7 style6 style11">
          <div align="left">No Fax</div>
      </div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
      #if($nofax != "")
    $nofax
      #else
      -
      #end
      
          <input type="hidden" name="txtfax" maxlength="50" size="15" value="$nofax" disabled>      
          </span></td>
    </tr>
    <!--
        <tr>
      <td><div align="right"></div></td>
      <td>&nbsp;</td>
      <td><span class="style2">
        <input type="button" name="cmdSimpan" value="Simpan" onClick="Simpan()">
        <input type="button" name="cmdKembali" value="Kembali" onClick="javascript:window.close();">
      </span></td>
    </tr>
    -->
    #end
  <tr>
    <td><span class="style5 style6 style3"><span class="style3 style10">
      </tbody>
      </span></span>
        <div align="left" class="style5 style6 style3"></div>    
    </table>
</fieldset>    </td>
  </tr>
</table>
</div>        </td>
        </tr>
      
      
      
      <tr>
        <td scope="row">&nbsp;</td>
        <td>&nbsp;</td>
        <td><label>
          <input type="radio" name="sorPenentuanBidangKuasa" id="sorPenentuanBidangKuasa" value="53" $check3 $setMode4 onClick="selectRadio4()" />
          
          
          
          <input name="setMode4" id="setMode4" type="hidden" value="$setMode4" >
        Permohonan diteruskan</label></td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td>&nbsp;</td>
        <td><input type="radio" name="sorPenentuanBidangKuasa" id="sorPenentuanBidangKuasa" value="50" $check4 $setMode5 onClick="selectRadio5();setTableB('tableReportA','tableReportX')"/>
         
        <input name="setMode5" id="setMode5" type="hidden" value="$setMode5" >
Pindah ke Mahkamah Tinggi</td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td>&nbsp;</td>
        <td>
        




<div id="tableReportX"  style="display:none;"  > 

        <table width="70%">
  <tr>
    <td>
   
   <fieldset  ><legend>MAKLUMAT MAHKAMAH</legend>
<table width="100%">
  <tbody>
  
  <!-- razman add open -->
   <tr>
      <td valign="top"><span  style="color:red"> #if($setMode != "disabled") * #end</span></td>
      <td width="29%"><div align="right">
        <div align="left">#if($setMode != "disabled") Tujuan Pindah Mahkamah #else
          Tujuan Pindah Mahkamah
          #end</div>
      </div></td>
      <td width="1%">:</td>
      <td width="70%">
       #if($setMode == "disabled")
       
       #if($tujuanPindah == "2")
       	Wasiat
       #else
       	Jumlah Harta > RM 600,000
       #end
       
       <input  type="hidden" name="tujuanPindah" id="tujuanPindah" value="$tujuanPindah" >
       
       #else
       
      <!--  :::::::: $Overalldum  -->
       
       #set($selectedTujuan1 = "")
       #set($selectedTujuan2 = "")
       
       #set($setModeTujuan1 = "")
       #set($setModeTujuan2 = "")
       
       
       #if($Overalldum >= 600000)        
    <!--   ----- kurang -->
        #set($setModeTujuan1 = "")
       	#set($setModeTujuan2 = "")      
       #else 
    <!--   ------ lebih -->
       	#set($setModeTujuan1 = "disabled")
       	#set($setModeTujuan2 = "")       
       #end
       
       
       #if($tujuanPindah == "2")
       	 #set($selectedTujuan1 = "")
       	 #set($selectedTujuan2 = "checked")
       #else
       	 #if($Overalldum < 600000)
             #set($selectedTujuan1 = "")
             #set($selectedTujuan2 = "checked")
         #else
             #set($selectedTujuan1 = "checked")
             #set($selectedTujuan2 = "")
         #end
       #end
       
     
      
      <input type="radio" name="tujuanPindah" id="tujuanPindah" $selectedTujuan1 $setModeTujuan1 value="1"   />     
        Jumlah Harta > RM 600,000
      <input type="radio" name="tujuanPindah" id="tujuanPindah" $selectedTujuan2 $setModeTujuan2 value="2"  />
        Wasiat
      
      
      
      #end
      </td>
      </tr>
   <!-- razman add close -->
  
    <tr>
      <td valign="top"><span style="color:red"> #if($setMode != "disabled") * #end</span></td>
      <td width="29%"><div align="right" >
        <div align="left">#if($setMode != "disabled") Negeri #else
          Negeri
          #end</div>
      </div></td>
      <td width="1%">:</td>
      <td width="70%">
      
      
      
       #if($setMode == "disabled")
       
        #foreach ($list in $ListNegeri) 
	     
		      #if ($list.idNegeri == $negerimahkamahX)	 
              #set($listnamaNegeri = $list.namaNegeri)   
              #end
              
        #end
        
     							    #if($negerimahkamahX !="" && $negerimahkamahX != "0" )
                                     <input name="nm" value="$listnamaNegeri" size="45" style="text-transform:uppercase;"  $setModeR class="$setMode" />
                                     #else
                                     <input name="nm" value="" size="34" style="text-transform:uppercase;"  $setModeR class="$setMode"  />
                                     #end
         
         
        #else 
      <select name="socNegeri" $setMode onChange="getDaerah(this.value,'sorKeputusanBorangC','socDaerah')" style="text-transform:uppercase;" >
    #if($negerimahkamahX != "" && $negerimahkamahX != "0")
       
      	  #foreach ($list in $ListNegeri) 
	     
		      #if ($list.idNegeri == $negerimahkamahX)	 
              #set($listnamaNegeri = $list.namaNegeri)    
	      		
        <option value="$list.idNegeri" $selected>$list.namaNegeri</option>
        
		  	  #end
	  	  #end
          
          #foreach ($list in $ListNegeri) 
	     
		      #if ($list.idNegeri != $negerimahkamahX)	     
	      		
        <option value="$list.idNegeri" $selected>$list.namaNegeri</option>
        
		  	  #end
	  	  #end
	  	  
       #else   
         
        <option value="0">Sila Pilih</option>
        
	  	  #foreach ($list in $ListNegeri)
          
	  	  #set ($idnegeri = $list.idNegeri)
	      #set ($namaNegeri = $list.namaNegeri) 
	  	  	
        <option value="$idnegeri">$namaNegeri</option>
        
	      #end
   #end   
      </select>
      #end      </td>
    </tr>
    <tr>
       <td valign="top"><span style="color:red"> #if($setMode != "disabled") * #end</span></td>
      <td><div align="right" >
        <div align="left">#if($setMode != "disabled") Daerah #else
          Daerah
          #end </div>
      </div></td>
      <td>:</td>
      <td>
        #if($setMode == "disabled")
       
       
	     
		#foreach ($listDaerah in $ListDaerah) 
	    
      		#if ($listDaerah.idDaerah == $daerahmahkamahX)            
            #set($listDaerahnamaDaerah=$listDaerah.namaDaerah)
            #end
        
        #end
        
     							    #if($daerahmahkamahX !="" && $daerahmahkamahX != "0" )
                                     <input name="dm" value="$listDaerahnamaDaerah" size="45" style="text-transform:uppercase;"  $setModeR class="$setMode" />
                                     #else
                                     <input name="dm" value="" size="34" style="text-transform:uppercase;"  $setModeR class="$setMode"  />
                                     #end
         
         
        #else 
      
      
      
      <select name="socDaerah" id="socDaerah" onChange="getAddress('sorKeputusanBorangC','socDaerah')" $setMode style="text-transform:uppercase;" onFocus="check_negeri()">
      
        #if($daerahmahkamahX != "" && $daerahmahkamahX != "0")
      
      #set ($idDaerah = "")
      #set ($namaDaerah = "")
      #foreach ($listDaerah in $ListDaerah) 
	    
      		#if ($listDaerah.idDaerah == $daerahmahkamahX)
            
            #set($listDaerahnamaDaerah=$listDaerah.namaDaerah)
		      
        <option value="$listDaerah.idDaerah" $selected>$listDaerah.namaDaerah</option>
        
      		#end
	  #end
      
      #foreach ($listDaerah in $ListDaerah) 
	    
      		#if ($listDaerah.idDaerah != $daerahmahkamahX)
		     
        <option value="$listDaerah.idDaerah" $selected>$listDaerah.namaDaerah</option>
        
      		#end
	  #end
      
	  
      #else
        <option value="0">Sila Pilih</option>
        
	  #foreach ($listDaerah in $ListDaerah) 
	      
        <option value="$listDaerah.idDaerah" >$listDaerah.namaDaerah</option>
        
	  #end
 #end     
      </select>
      #end      </td>
    </tr>
    
   <!-- 193 -->
   
	#foreach ($listM in $listMaklumatMahkamahJ)
   
    #if($negerimahkamahX == $listM.idnegeri && $daerahmahkamahX == $listM.iddaerah && $listM.jenispejabat == 8 && $listM.id_Pejabat != 193 )
    
    
		#set ($namapejabat = $listM.nama_pejabat)
		#set ($alamat1pejabat = $listM.alamat1)
		#set ($alamat2pejabat = $listM.alamat2)
		#set ($alamat3pejabat = $listM.alamat3)
		#set ($poskodpejabat = $listM.poskod)
		#set ($notel = $listM.no_tel)
		#set ($nofax = $listM.no_fax)
        
   
     #end
	#end

    #if($daerahmahkamah != "" && $daerahmahkamah != "0")
    <tr>
      <td>&nbsp;</td>
      <td><div align="right">
        <div align="left">Mahkamah</div>
      </div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
       #if($namapejabat != "")
       $namapejabat
      #else
      -
      #end
     
          <input type="hidden" name="txtNamaMahkamah" value="$namapejabat" size="50" maxlength="50" disabled>      
          </span></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="right" >
        <div align="left">Alamat</div>
      </div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
      #if( $alamat1pejabat != "")
      $alamat1pejabat
      #else
      -
      #end
          <input type="hidden" name="txtAlamat1" maxlength="50" size="50" value="$alamat1pejabat" disabled>      
          </span></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="left"><span class="style6"><span class="style9"></span></span></div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
       #if( $alamat2pejabat != "")
     $alamat2pejabat
      #else
      -
      #end
      
          <input type="hidden" name="txtAlamat2" maxlength="50" size="50" value="$alamat2pejabat" disabled>      
          </span></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="left"><span class="style6"><span class="style9"></span></span></div></td>
      <td>:</td>
      <td>
      <span class="style2" style="text-transform:uppercase;">
       #if( $alamat3pejabat != "")
    $alamat3pejabat
      #else
      -
      #end
      
      <input name="txtAlamat3" type="hidden" disabled class="style2" value="$alamat3pejabat" size="50" maxlength="50">  
      </span>          </td>
      </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="right" >
        <div align="left">Poskod</div>
      </div></td>
      <td>:</td>
      <td>
      <span class="style2" style="text-transform:uppercase;">
      #if( $poskodpejabat != "")
    $poskodpejabat
      #else
      -
      #end
      
      
          <input type="hidden" name="txtPoskod" maxlength="5" size="5" value="$poskodpejabat" disabled>      
          </span></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="right" >
        <div align="left">No Telefon</div>
      </div></td>
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
      #if($notel != "")
    $notel
      #else
      -
      #end
      
      
          <input type="hidden" name="txtTelefon" maxlength="50" size="15" value="$notel" disabled>      
          </span></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="right">
        <div align="left">No Fax</div>
      </div></td>
      
      <td>:</td>
      <td><span class="style2" style="text-transform:uppercase;">
      #if($nofax != "")
    $nofax
      #else
      -
      #end
      
          <input type="hidden" name="txtfax" maxlength="50" size="15" value="$nofax" disabled>      
          </span></td>
    </tr>
    <!--
        <tr>
      <td><div align="right"></div></td>
      <td>&nbsp;</td>
      <td><span class="style2">
        <input type="button" name="cmdSimpan" value="Simpan" onClick="Simpan()">
        <input type="button" name="cmdKembali" value="Kembali" onClick="javascript:window.close();">
      </span></td>
    </tr>
    -->
    #end
  <tr>
    <td><span class="style5"></span>
      </table>
</fieldset>    </td>
  </tr>
</table>
</div>
        </td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td>&nbsp;</td>
        <td><input type="radio" name="sorPenentuanBidangKuasa" id="sorPenentuanBidangKuasa" $check5 value="70" $setMode6 onClick="selectRadio6();setTableB_TUTUP()" />
        
        <input name="setMode6" id="setMode6" type="hidden" value="$setMode6" >
Batal Permohonan (Lain - lain kes)</td>
      </tr>
      <tr>
        <td valign="top" align="right"><b>Catatan</b></td>
        <td valign="top"><strong>:</strong></td>
        <td><label>
          <textarea name="txtCatatan" id="textarea" cols="100" rows="5" $setModeR class="$setMode" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" >$catatan.toUpperCase()</textarea>
        </label></td>
      </tr>
    </table>
    </fieldset>
    </td>
  </tr>
</table>
  #if($setMode != "disabled") 
                    <table width="100%">
  <tr>
    <td><div align="left" class="style4"><span class="style3">Perhatian</span> : Sila pilih salah satu penentuan bidang kuasa dan pastikan label bertanda <span class="style3">*</span> diisi.</div></td>
  </tr>
</table>
#end
<table width="100%" border="0">
  <tr>
    <td align="center">
    #if ($EventStatus == 1 && $flagFromSenaraiFailSek8 != 'yes')
    
    
    
  #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
<!--
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="getKemaskini()"/>  -->
      #end
      
    #if($boleh_kemaskini == "yes")
    #end 
    <input type="button" name="cmdKemaskini1" id="cmdKemaskini1" value="Kemaskini" onClick="getKemaskini()"/> 
    #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('cmdKemaskini1').style.display = "none";
                                </script>
                                #end 
      
    #if($id_Status == "50")
      <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport')" />
    #end
     #if($id_Status == "152")
      <input type="button" name="button" id="button" value="Cetak" onClick="javascript:cetakSuratBatalPermohonan('$noFail')" />
    #end
    
    #elseif ($EventStatus == 0 || $eve == 8)
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="getSimpan()"/>
    <!--  <input type="reset" name="cmdReset" id="cmdReset" value="Kosongkan"/> -->
      #if($id_Status != "14")
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="getKemaskinibatal()" />
      #end
      #if($id_Status == "14")
      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onClick="edit_item()" />
      #end
      #end    
      #if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView  == '')
      <input type="submit" name="button2" id="button2" value="Kembali" />
      
      #end
       #if ($flagFromSenaraiFailSek8 == 'yes')
         <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$noFail')"/>
         #end
         #if ($flagFromSenaraiPermohonanSek8 == 'yes')
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>
    #end 
     #if ($flagForView  == 'yes')
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$noFail')"/>
    #end
    
    
    <input type="submit" name="button3" id="button3" value="Pendaftaran Permohonan"  onClick="javascript:keNilaian_Harta('$jenis_permohonan','$idPermohonan','$idPemohon','$idSimati','$idpermohonan_simati')" />
    
    <input type="hidden" name="id_permohonan"  value="$idPermohonan"/>   
	<input name="id_status" type="hidden"  value="$id_Status"/> 
    
    
    #if ($EventStatus != 0)
    #if ($id_Status == 70)
    <input type="button" name="cmdKembali" id="cmdKembali" value="Cetak" onClick="javascript:cetakSuratBatalPermohonan_LK('$noFail')"/>
    #end
    #end
    
     
    #if ($EventStatus != 0 && ($id_Status != "53" || $id_Status != "151" || $id_Status == "44" || $id_Status == "173" || $id_Status == "175" || $id_Status == "177" || $id_Status == "18"))     
    <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onClick="kenotis('$seksyen','$id_Status')"/>
    #end
    
    
    
    </td>
  </tr>
	<input type="hidden" name="command"/>
	<input type="hidden" name="idPermohonan"  value="$idPermohonan"/>
	<input type="hidden" name="idKeputusanPermohonan" />
      <input type="hidden" name="idpermohonan" />
</table>
</fieldset>


<fieldset id="tableReport" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    <!--
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetak('$noFail')">Kulit Fail</a></td>
      </tr>
      -->
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakSuratPindahMT('$noFail')">Surat Pindah Mahkamah Tinggi</a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBorangI('$noFail')">Borang I</a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:buktiPenyampaian('$noFail','$idFail')">Bukti Penyampaian (Mahkamah Tinggi)</a></td>
      </tr>
    </table>
</fieldset> 
#parse("app/ppk/headerppk_script.jsp") 
<input type="hidden" name="flag_KP" id="flag_KP" value="yes" />
</form>
</body>
<p align="right">CL - 01 - 84</p>
<script>
checkDateKP();

function checkDateKP() {

	
	
	var d3 = document.f1.txdTarikhHantarNilaian;	
	var cmdSeterusnya = document.f1.cmdSeterusnya;		
	
    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;	
      
    var str3  = document.getElementById("txdTarikhHantarNilaian").value;
    var str5  = document.getElementById("tarikh_permohonan").value;  
	
	
    if(str3!="")
	{
    var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10)-1;
    var yr3   = parseInt(str3.substring(6,10),10);
	var date3 = new Date(yr3, mon3, dt3);//txdTarikhHantarNilaian
	}
    if(str5!="")
	{   
    var dt5   = parseInt(str5.substring(0,2),10);
    var mon5  = parseInt(str5.substring(3,5),10)-1;
    var yr5   = parseInt(str5.substring(6,10),10);
	var date5 = new Date(yr5, mon5, dt5);//txdTarikhTPemrohonan
	}
	
	var string_field_temp = "";
  
    if(str3=="")
	{
	 
	
		if(testIsValidObjectKP(cmdSeterusnya)==false)
		{
		string_field_temp = string_field_temp + "<input type='hidden' id='cmdSeterusnya' name='cmdSeterusnya'/>";
		}
		else
		{
		string_field_temp = string_field_temp + "";
		}	    
		
		
		
		$jquery("#alert_field").html("<font color = 'red'>Fail Permohonan belum cukup tempoh 30 hari daripada tarikh hantar borang B dan proses permohonan tidak dapat diteruskan. Sila pastikan maklumat <b>Tarikh Hantar Nilaian</b> di isi.</font>"+string_field_temp);	
		
		//document.getElementById("cmdSeterusnya").disabled = true;
		
	} 
	else
	{

		$jquery("#alert_field").html("");
		document.getElementById("cmdSeterusnya").disabled = false;
	} 
  
}

function testIsValidObjectKP(objToTest) {

//alert("objToTest:"+objToTest)
if (objToTest == null || objToTest == undefined) {
return false;
}
return true;
}


function getKemaskini() {
	document.f1.method="post";
	document.f1.command.value="getKemaskini_keputusanLama";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	document.f1.submit();
}
function getBatal() {
	document.f1.method="post";
	document.f1.command.value="getBack_keputusanLama";
document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	document.f1.submit();
}
function getSimpan() {
	var check_batal = document.f1.sorPenentuanBidangKuasa[2].checked;
var d1 = document.f1.txdTarikhHantarBorangB
var d2 = document.f1.txdTarikhTerimaBorangC
var d3 = document.f1.txdTarikhHantarNilaian
var d4 = document.f1.txdTarikhTerimaNilaian


    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
   var str1  = document.getElementById("txdTarikhHantarBorangB").value;
   var str2  = document.getElementById("txdTarikhTerimaBorangC").value; 
   
   var str3  = document.getElementById("txdTarikhHantarNilaian").value;
   var str4  = document.getElementById("txdTarikhTerimaNilaian").value;  
   
   
   var str5  = document.getElementById("tarikh_permohonan").value;  
   
  
   
   var dt1   = parseInt(str1.substring(0,2),10);
   var mon1  = parseInt(str1.substring(3,5),10)-1;
   var yr1   = parseInt(str1.substring(6,10),10);
   
   var dt2   = parseInt(str2.substring(0,2),10);
   var mon2  = parseInt(str2.substring(3,5),10)-1;
   var yr2   = parseInt(str2.substring(6,10),10);
   
    var dt3   = parseInt(str3.substring(0,2),10);
   var mon3  = parseInt(str3.substring(3,5),10)-1;
   var yr3   = parseInt(str3.substring(6,10),10);
   
   var dt4   = parseInt(str4.substring(0,2),10);
   var mon4  = parseInt(str4.substring(3,5),10)-1;
   var yr4   = parseInt(str4.substring(6,10),10);
   
   var dt5   = parseInt(str5.substring(0,2),10);
   var mon5  = parseInt(str5.substring(3,5),10)-1;
   var yr5   = parseInt(str5.substring(6,10),10);
   
   var date1 = new Date(yr1, mon1, dt1);//txdTarikhHantarBorangB
   var date2 = new Date(yr2, mon2, dt2);//txdTarikhTerimaBorangC
   
   var date3 = new Date(yr3, mon3, dt3);//txdTarikhHantarNilaian
   var date4 = new Date(yr4, mon4, dt4);//txdTarikhTerimaNilaian
   
    var date5 = new Date(yr5, mon5, dt5);//txdTarikhTerimaNilaian
   
   
       if(document.f1.sorPenentuanBidangKuasa[0].checked != true && document.f1.sorPenentuanBidangKuasa[1].checked != true && document.f1.sorPenentuanBidangKuasa[2].checked != true && document.f1.sorPenentuanBidangKuasa[3].checked != true && document.f1.sorPenentuanBidangKuasa[4].checked != true)
	   {
	   alert("Sila pilih penentuan bidang kuasa");
	   return
	   }
	  /*  else if (check_batal != true &&  str3 == ""){
		alert("Sila pastikan tarikh hantar nilaian diisi.");		
		txdTarikhHantarNilaian.focus();
	
	    }*/
/*
	      else if (d1.value != "" && isDate(d1.value)==false){
		d1.focus()
		return false
	}

 else if (d2.value != "" && isDate(d2.value)==false){
		d2.focus()
		return false
	}
	 else if (d3.value != "" && isDate(d3.value)==false){
		d3.focus()
		return false
	}
	 else if (d4.value != "" && isDate(d4.value)==false){
		d4.focus()
		return false
	}
	*/
/*
    else if ( date1 > currentTime){
		alert("Sila pastikan tarikh borang B dihantar tidak melebihi dari tarikh hari ini.");
		//document.f1.txdTarikhHantarBorangB.value="";		
		txdTarikhHantarBorangB.focus();
	}
	else if ( date2  > currentTime){
		alert("Sila pastikan tarikh borang C diterima tidak melebihi dari tarikh hari ini.");
	//	document.f1.txdTarikhTerimaBorangC.value="";		
		txdTarikhTerimaBorangC.focus();
	}*/
	
	//txdTarikhHantarNilaian
//txdTarikhTerimaNilaian
	 else if (str3 != "" &&  date3 > currentTime){
		alert("Sila pastikan tarikh harta dihantar tidak melebihi dari tarikh hari ini.");
		//document.f1.txdTarikhHantarBorangB.value="";		
		txdTarikhHantarNilaian.focus();
	}
	else if (str4 != "" &&  date4 > currentTime){
		alert("Sila pastikan tarikh harta diterima tidak melebihi dari tarikh hari ini.");
		//document.f1.txdTarikhTerimaBorangC.value="";		
		txdTarikhTerimaNilaian.focus();
	}
	/*
	 else if ( date1 < date5){
		alert("Sila pastikan tarikh borang B dihantar melebihi dari tarikh permohonan.");
		//document.f1.txdTarikhHantarBorangB.value="";		
		txdTarikhHantarBorangB.focus();
	}
	else if ( date2  < date5){
		alert("Sila pastikan tarikh borang C diterima melebihi dari tarikh permohonan.");
	//	document.f1.txdTarikhTerimaBorangC.value="";		
		txdTarikhTerimaBorangC.focus();
	}
	*/
	//txdTarikhHantarNilaian
//txdTarikhTerimaNilaian
	 else if (str5 != "" &&  str3 != "" && date3 < date5){
		alert("Sila pastikan tarikh harta dihantar melebihi dari tarikh permohonan.");
		//document.f1.txdTarikhHantarBorangB.value="";		
		txdTarikhHantarNilaian.focus();
	}
	else if (str4 != "" &&  str5 != "" && date4 < date5){
		alert("Sila pastikan tarikh harta diterima melebihi dari tarikh permohonan.");
		//document.f1.txdTarikhTerimaBorangC.value="";		
		txdTarikhTerimaNilaian.focus();
	}
	
	/*
   else if(date2 < date1)
   {
      alert("Sila pastikan tarikh hantar borang B tidak melebihi dari tarikh terima borang C.");
	 // document.f1.txdTarikhHantarBorangB.value="";	
	 // document.f1.txdTarikhTerimaBorangC.value="";		
      document.f1.txdTarikhHantarBorangB.focus();
   } */
   
    else if(str4 != "" &&  str3 != "" && date4 < date3)
   {
      alert("Sila pastikan tarikh hantar harta tidak melebihi dari tarikh terima harta.");
	 // document.f1.txdTarikhHantarBorangB.value="";	
	 // document.f1.txdTarikhTerimaBorangC.value="";		
      document.f1.txdTarikhHantarNilaian.focus();
   }
   
    /*
   
   else if(document.f1.nofailawal.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true )
   {
    alert("Sila pastikan no fail pemohonan awal diisi" );		
    document.f1.nofailawal.focus();
   
   }
   else if(document.f1.namapemohonawal.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true )
   {
    alert("Sila pastikan nama pemohon awal diisi");	 	
    document.f1.namapemohonawal.focus();
   
   }
   else if((document.f1.tempatmohonawal.value == "" || document.f1.tempatmohonawal.value == "0") && document.f1.sorPenentuanBidangKuasa[0].checked == true )
   {
    alert("Sila pastikan tempat permohonan awal diisi");	 	
    document.f1.tempatmohonawal.focus();
   
   }
  */
  else if((document.f1.socNegeri.value == "" || document.f1.socNegeri.value == "0") && document.f1.sorPenentuanBidangKuasa[3].checked == true )
   {
    alert("Sila pilih negeri mahkamah");	 	
    document.f1.socNegeri.focus();
   
   }
  
  
  else if((document.f1.socDaerah.value == "" || document.f1.socDaerah.value == "0") && document.f1.sorPenentuanBidangKuasa[3].checked == true )
   {
    alert("Sila pilih daerah mahkamah");	 	
    document.f1.socDaerah.focus();
   
   }
   
   
    else if((document.f1.socNegeriPindah.value == "" || document.f1.socNegeriPindah.value == "0") && document.f1.sorPenentuanBidangKuasa[1].checked == true )
   {
    alert("Sila pilih negeri pejabat");	 	
    document.f1.socNegeriPindah.focus();
   
   }
  
  
  else if((document.f1.socDaerahPindah.value == "" || document.f1.socDaerahPindah.value == "0") && document.f1.sorPenentuanBidangKuasa[1].checked == true )
   {
    alert("Sila pilih daerah pejabat");	 	
    document.f1.socDaerahPindah.focus();
   
   }
  
  
   
   
   else{

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method="post";
	document.f1.command.value="getSimpan_keputusanLama";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	document.f1.submit();
	}else
	{	
	return;
	}
	}
}
function getMahkamah(idPermohonan,command) {
	var url = "../x/${securityToken}/FrmSenaraiFailKeputusanPermohonanInternal17?idPermohonan="+idPermohonan+"&command="+command;
	var hWnd = window.open(url,'printuser','width=600,height=600, resizable=yes,scrollbars=yes');
	document.f1.sorPenentuanBidangKuasaTeruskan[0].checked == false;
}
function selectRadio1() {

if(document.f1.sorPenentuanBidangKuasa[0].checked == true)
{

if(document.f1.jumlah.value >= 600000)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = true;
}
else
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
}

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;

document.getElementById('tableReportX').style.display="none";//razman add

}






/*
	if (document.f1.sorPenentuanBidangKuasa[0].checked == true) {
		document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = true;
	}
	if (document.f1.sorPenentuanBidangKuasa[1].checked == true) {
		document.f1.sorPenentuanBidangKuasa[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
	}
	if (document.f1.sorPenentuanBidangKuasa[3].checked == true) {
		document.f1.sorPenentuanBidangKuasa[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
	}
	*/
}

function selectRadio2() {

if(document.f1.sorPenentuanBidangKuasaTeruskan[0].checked == true)
{
document.f1.sorPenentuanBidangKuasa[0].checked = true;
//document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;

}
document.getElementById('tableReportX').style.display="none"; //razman add
}

function selectRadio3() {

if(document.f1.sorPenentuanBidangKuasaTeruskan[1].checked == true)
{
document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;


}
document.getElementById('tableReportX').style.display="none";//razman add

}
function selectRadio99() {
if(document.f1.sorPenentuanBidangKuasa[1].checked == true)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;

document.f1.sorPenentuanBidangKuasa[0].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;

document.f1.sorPenentuanBidangKuasa[4].checked = false;


}
document.getElementById('tableReportX').style.display="none";//razman add
}

function selectRadio4() {
if(document.f1.sorPenentuanBidangKuasa[1].checked == true)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;

document.f1.sorPenentuanBidangKuasa[0].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;
}
document.getElementById('tableReportX').style.display="none"; //razman add
document.getElementById('tableReportPindah').style.display="none";

}


function selectRadio5() {

if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;

document.f1.sorPenentuanBidangKuasa[0].checked = false;

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;

}
document.getElementById('tableReportX').style.display="block"; //razman edit add block	

}

function selectRadio6() {

if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;

document.f1.sorPenentuanBidangKuasa[0].checked = false;

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

}

document.getElementById('tableReportX').style.display="none";//razman add

document.f1.jenis_pej.value = "";
document.f1.jenis_pej_id.value = "";


document.f1.tempatmohonawal.value = "";



}

function putih(){


if(document.f1.jumlah.value >= 600000)
{
document.f1.sorKeputusanBorangC[0].checked = true;
document.f1.sorKeputusanBorangC[1].checked = false;


document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = false;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = true;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[4].checked = false;

document.f1.sorPenentuanBidangKuasa[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = true;
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;
document.f1.sorPenentuanBidangKuasa[4].disabled = false;

document.f1.setMode1.value = "disabled";
document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "disabled";
document.f1.setMode4.value = "disabled";
document.f1.setMode99.value = "disabled";
document.f1.setMode5.value = "";
document.f1.setMode6.value = "";


        

//document.f1.txtCatatan.value = "Pindah ke Mahkamah Tinggi kerana nilai harta melebihi 2 juta ringgit";




}
else
{

document.f1.sorKeputusanBorangC[0].checked = true;
document.f1.sorKeputusanBorangC[1].checked = false;


document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = false;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = true;
document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.f1.sorPenentuanBidangKuasa[4].checked = false;

document.f1.sorPenentuanBidangKuasa[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = true;

document.f1.sorPenentuanBidangKuasa[1].disabled = false;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;// razman add false
document.f1.sorPenentuanBidangKuasa[2].disabled = false;
document.f1.sorPenentuanBidangKuasa[4].disabled = false;

document.f1.setMode1.value = "disabled";
document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "disabled";
document.f1.setMode4.value = "";
document.f1.setMode99.value = "";
document.f1.setMode5.value = "";  // razman buang disable
document.f1.setMode6.value = "";

//document.f1.txtCatatan.value = "";

}



}

function kuning(){

if(document.f1.id_taraf_mohon.value != "")
{var idm = document.f1.id_taraf_mohon.value;}
else
{idm="";}

if(document.f1.jumlah.value >= 600000)
{
document.f1.sorKeputusanBorangC[0].checked = false;
document.f1.sorKeputusanBorangC[1].checked = true;


//document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
//document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = true;

if(idm == 6)
{
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = true;
}else
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = true;
}



document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.f1.sorPenentuanBidangKuasa[4].checked = false;


document.f1.sorPenentuanBidangKuasa[0].disabled = false;

if(idm == 6)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
}
else
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
}

//document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
//document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;

document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = false;
document.f1.sorPenentuanBidangKuasa[3].disabled = true;
document.f1.sorPenentuanBidangKuasa[4].disabled = false;

document.f1.setMode1.value = "";

if(idm == 6)
{
document.f1.setMode2.value = "";
}
else
{
document.f1.setMode2.value = "disabled";
}
//document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "";
document.f1.setMode4.value = "disabled";
document.f1.setMode99.value = "disabled";
document.f1.setMode5.value = "disabled";
document.f1.setMode6.value = "";


document.f1.txtCatatan.value = "";





   
  //  #set($catatan="Pindah ke Mahkamah Tinggi kerana nilai harta melebihi 2 juta ringgit")
    
}

else
{

document.f1.sorKeputusanBorangC[0].checked = false;
document.f1.sorKeputusanBorangC[1].checked = true;


document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.f1.sorPenentuanBidangKuasa[4].checked = false;


document.f1.sorPenentuanBidangKuasa[0].disabled = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = true;
document.f1.sorPenentuanBidangKuasa[3].disabled = true;
document.f1.sorPenentuanBidangKuasa[4].disabled = false;

document.f1.setMode1.value = "";
document.f1.setMode2.value = "";
document.f1.setMode3.value = "";
document.f1.setMode4.value = "disabled";
document.f1.setMode99.value = "disabled";
document.f1.setMode5.value = "disabled";
document.f1.setMode6.value = "";

//document.f1.txtCatatan.value = "";

}

/*
if(document.f1.jumlah.value > 600000)
{

 #set ($check8 = "checked")
    #set  ($check3 = "") 
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "disabled")
    #set ($setMode5 = "")
    #set ($setMode6 = "")
      
    #set  ($check0 = "")   
    #set  ($check1 = "")   
    #set  ($check2 = "") 
    #set  ($check4 = "checked")   
    #set  ($check5 = "")      
	#set ($check9 = "")
    
    #set($catatan="Pindah ke Mahkamah Tinggi kerana nilai harta melebihi 2 juta ringgit")
    

}
else
{




}
*/
}

function edit_item(){
	input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method="post";
	document.f1.command.value = "paparKeputusanLama";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	//document.f1.idPermohonan.value = id;
	document.f1.submit();
	}
	else
	{
	return;
	}
}


/*
function cetak(noFail) {
    var url = "../servlet/ekptg.report.ppk.test?NoFail="+noFail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}*/

function cetakSuratPindahMT(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratPindahMT&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratBatalPermohonan(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonan&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangI(noFail) {
    //var url = "../servlet/ekptg.report.ppk.BorangI?nofail="+noFail;
	
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=BorangI&flagReport=B";
	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function buktiPenyampaian(noFail,idfail)
{

    var url = "../servlet/ekptg.report.ppk.BuktiPenyampaianMT?nofail="+noFail+"&idfail="+idfail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


}


function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}




function setTableA(id,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true && document.getElementById(id).style.display=="none" )
{
	document.f1.v_loc.value = loc;
	document.f1.v_tab.value = tab;
	
	
		document.getElementById(id).style.display="block";
		document.getElementById('tableReportX').style.display="none";
		document.f1.radio_j[2].checked = true;
		document.f1.jenis_pej.value = 8;
		
		document.f1.checkJ1.value = "";
		document.f1.checkJ2.value = "";
		document.f1.checkJ3.value = "checked";
		document.f1.command.value = "get_jenisPejabatLama";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value  = "";
			document.f1.viewcheckJ3.value  = "";
			
			
		
		
	
document.f1.submit();

  document.getElementById('tableReportPindah').style.display="none";
	
}


}


function setTableA_JENISA(id,val,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true  )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.f1.radio_j[0].checked = true;
		
		document.f1.jenis_pej.value = val;
		
		document.f1.checkJ1.value = "checked";
		
		document.f1.checkJ2.value = "";
		document.f1.checkJ3.value = "";
		
		
		
		
		document.f1.command.value = "get_jenisPejabatLama";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	//document.f1.idPermohonan.value = id;
	    document.f1.submit();
	
}

}


function setTableA_JENISB(id,val,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.f1.radio_j[1].checked = true;
		document.f1.jenis_pej.value = val;
		document.f1.checkJ2.value = "checked";
		document.f1.checkJ1.value = "";
		document.f1.checkJ3.value = "";
		
		document.f1.command.value = "get_jenisPejabatLama";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	//document.f1.idPermohonan.value = id;
	document.f1.submit();
	
}

}
function setTableA_JENISC(id,val,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true  )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.f1.radio_j[2].checked = true;
		document.f1.jenis_pej.value = val;
		document.f1.checkJ3.value = "checked";
		
		document.f1.checkJ1.value = "";
		document.f1.checkJ3.value = "";
		
		document.f1.command.value = "get_jenisPejabatLama";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	//document.f1.idPermohonan.value = id;
	   document.f1.submit();
	
}

}


function setTableA_J(id,idx){
if(document.f1.sorKeputusanBorangC[1].checked == true && document.getElementById(id).style.display=="none" )
{
	
		document.getElementById(id).style.display="block";
		//document.getElementById(idx).style.display="none";
		
		
}
if(document.f1.sorKeputusanBorangC[0].checked == true && document.getElementById(idx).style.display=="none" )
{
        //razman add open
	  // alert('x');
	   if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
	   {
		  document.getElementById(idx).style.display="block";	
	   }
	   else
	   {
		  document.getElementById(idx).style.display="none";
	   }
	   //razman add close
	   
	   
	    if(document.f1.jumlah.value >= 600000)
		{	
		document.getElementById(idx).style.display="block";
		document.getElementById(id).style.display="none";
		}
		
		if(document.f1.jumlah.value < 600000)
		{
	//document.getElementById(idx).style.display="none"; //razman comment
		//document.getElementById(id).style.display="none"; //razman comment	
		
		}
		
		
		
		
}

if(document.f1.sorPenentuanBidangKuasa[1].checked == true && (document.getElementById('tableReportPindah').style.display=="block" || document.getElementById('tableReportPindah').style.display=="none")  )
{

 document.getElementById('tableReportPindah').style.display="block";
 }


}









function setTableB_TUTUP()
{
document.getElementById('tableReportA').style.display="none";
document.getElementById('tableReportX').style.display="none";
document.getElementById('tableReportPindah').style.display="none";


}

function setTableB(id,idx){
if(document.getElementById(id).style.display=="block" )
{
	
		document.getElementById(id).style.display="none";
	
}


//razman add open
if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
{
  document.getElementById(idx).style.display="block";	
}
else
{
  document.getElementById(idx).style.display="none";
}
//razman add close

if(document.f1.jumlah.value >= 600000)
{
document.getElementById(idx).style.display="block";
//razman add open
document.f1.tujuanPindah[0].disabled = false;
document.f1.tujuanPindah[1].disabled = false;
document.f1.tujuanPindah[0].checked = true;
document.f1.socNegeri.value = "0";
document.f1.socDaerah.value = "0";

//razman add close
}
else
{
//alert('kurang');
	//razman add open
	if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
	{
		document.getElementById(idx).style.display="block";
		document.f1.tujuanPindah[0].checked = false;
		document.f1.tujuanPindah[0].disabled = true;
		document.f1.tujuanPindah[1].checked = true;
		document.f1.socNegeri.value = "0";
		document.f1.socDaerah.value = "0";
	}
	else
	{
		document.getElementById(idx).style.display="none";
	}
	//razman add close
}
document.getElementById('tableReportPindah').style.display="none";

}


function setTablePindah(id){
if(document.f1.sorPenentuanBidangKuasa[1].checked == true && document.getElementById(id).style.display=="none" )
{
	
		document.getElementById(id).style.display="block";
	
}
else if (document.f1.sorPenentuanBidangKuasa[1].checked == false && document.getElementById(id).style.display=="block" )
{
document.getElementById(id).style.display="none";

}



}



function alamatP(val,loc,tab)
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.f1.command.value = "get_alamatPejabatLama";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
		document.f1.jenis_pej_id.value = val;
	    //document.f1.idPermohonan.value = id;
	    document.f1.submit();
	
}

function check_negeri()
{
if(document.f1.socNegeri.value == "0" || document.f1.socNegeri.value == "")
{

alert("Sila pilih negeri mahkamah terlebih dahulu")
document.f1.socNegeri.focus()

}
}

function check_negeriPindah()
{
if(document.f1.socNegeriPindah.value == "0" || document.f1.socNegeriPindah.value == "")
{

alert("Sila pilih negeri pindah terlebih dahulu")
document.f1.socNegeriPindah.focus()

}
}


function getDaerah(val,loc,tab){
document.f1.v_loc.value=loc;
document.f1.v_tab.value=tab;

	document.f1.command.value="getMahkamahLama";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	document.f1.submit();	
}

function getDaerahPindah(val,loc,tab){
document.f1.v_loc.value=loc;
document.f1.v_tab.value=tab;

	document.f1.command.value="getMahkamahLamaPindah";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	document.f1.submit();	
}


function getAddress(loc,tab){
document.f1.v_loc.value=loc;
document.f1.v_tab.value=tab;

	document.f1.command.value="getMahkamahAddressLama";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	document.f1.submit();	
}

function getAddressPindah(loc,tab){
document.f1.v_loc.value=loc;
document.f1.v_tab.value=tab;

	document.f1.command.value="getMahkamahAddressLamaPindah";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	document.f1.submit();	
}

/*
function submitForm() {    
   window.location.hash='$val_loc';
	
	goTo('$val_tab');

	return false;
	
} */

function submitForm() {    
//alert('$val_tab')

if('$val_loc' != "" && '$val_loc' != null)
{

   window.location.hash='$val_loc';
   
   }

if('$val_tab' != "" && '$val_tab' != null)
{

  // window.location.hash='$val_tab';
   //goTo('$val_tab');
   var nextFieldID = '$!val_tab';
   document.getElementById(nextFieldID).focus();
   }
	
} 


function kembaliSenaraiFail(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.f1.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.f1.method="POST";
	document.f1.submit();
}

function getKemaskinibatal() {
input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method="post";
	document.f1.command.value="getKemaskini_keputusanLama";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	document.f1.submit();
	}else
	{return;}
}

function edit_item(){

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {

	document.f1.method="post";
	document.f1.command.value = "paparKeputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
//	document.f1.idPermohonan.value = id;
//	document.f1.idpermohonansimati.value = sm;
//	document.f1.tarikh_mohon.value = tm;
	document.f1.submit();
	}
	else
	{return;}
}


 function trans_date1(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhHantarBorangB.value = trans;

}
else
{
return;
}

}


 function trans_date2(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhTerimaBorangC.value = trans;

}
else
{
return;
}

}


 function trans_date3(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhHantarNilaian.value = trans;

}
else
{
return;
}

}


 function trans_date4(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhTerimaNilaian.value = trans;

}
else
{
return;
}

}


var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yg sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yg sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yg sah")
		return false
	}
return true
}

function kenotis(seksyen,idStatus)
{

if (idStatus == '53' || idStatus == '151' )
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0";
			}
				
						
	document.f1.method = "post";
	document.f1.submit();
} 
else if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' )
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis&tabId=0";
			}
				
	document.f1.method = "post";
	document.f1.submit();
} 

else if (idStatus == '18')
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
			}
				
						
	document.f1.method = "post";
	document.f1.submit();
}
else if (idStatus == '50')
{
alert("Permohonan telah dipindahkan ke mahkamah tinggi");
}
else
{
//alert("Status permohonan tidak sah untuk notis perbicaraan");
if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
			}
				
						
	document.f1.method = "post";
	document.f1.submit()
}
	

}


function cetakSuratBatalPermohonan_LK(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonanLainKes&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function ForView(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.f1.submit();
}



function keNilaian_Harta(jenis_permohonan,idPermohonan,idPemohon,idSimati,id_Permohonansimati)
{
document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";	
document.f1.idpermohonan.value = idPermohonan;
document.f1.command.value = "papar";
document.f1.idSimati.value = idSimati;
document.f1.method="POST";
document.f1.submit();
}

	
</script>