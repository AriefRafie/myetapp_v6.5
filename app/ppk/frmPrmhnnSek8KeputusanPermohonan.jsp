<style type="text/css">
<!--
.style2 {color: #0000FF}
.style3 {
	color: #FF0000;
	font-size: 9px;
	font-style: italic;
}
.style4 {
	font-size: 9px;
	font-style: italic;
}
.style5 {color: #FF0000}
.style6 {font-size: 10px}
.style7 {font-size: 10px; color: #FF0000; }
.style8 {font-size: 10px; color: #000000; }
.style9 {color: #000000}
-->
</style>
#set ($namaDoC = "")
#foreach($listSupportingDoc in $ViewSupportingDoc)
#set($namaDoC = $listSupportingDoc.NAMA_DOKUMEN)
#end
<body onLoad="selectRadio2();setTableA_J('tableReportA','tableReportX');submitForm()" >
<form id="f1" name="f1" action="" method="post">


 
<input type="hidden" name="pilih_negeri_ptd" id="pilih_negeri_ptd"  value="$pilih_negeri_ptd" />

<input type="hidden" name="ARB" id="ARB" value="" />
<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="v_loc" id="v_loc" value="" />

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- arief add 5 JUTA -->
#foreach ($Listflag5juta in $flag5juta)
	#set($check5juta = $Listflag5juta.flag_5juta )
#end

#if($check5juta=='T')
	#set($nilaiHartaMaximum = 5000000)
#else
	#set($nilaiHartaMaximum = 2000000)
#end

#set ($tarikh_Hantar_BorangB = "")
#set ($tarikh_Terima_BorangC = "")
#set ($tarikh_Hantar_Nilaian = "")
#set ($tarikh_Terima_Nilaian = "")
#set ($jumlah_Hta_TarikhMohon = "0.00")
#set ($jumlah_Ha_TarikhMohon = "0.00")
#set ($jumlah_Harta_Tarikhmohon = "0.00")
#set ($security_Code_Link = "")
#set ($result_BorangC = "")
#set ($kep_BorangC = "")
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
#set ($negerimahkamah = "")
#set ($daerahmahkamah = "")
#set ($negerimahkamahX = "")
#set ($daerahmahkamahX = "")


#set ($txtNamaKaveat = "")
#set ($txtNoKaveat = "")
#set ($txtNamaFirma = "")
#set ($txtAlamat1Peguam = "")
#set ($txtAlamat2Peguam = "")
#set ($txtAlamat3Peguam = "")
#set ($txtPoskodPeguam = "")
#set ($socNegeriPeguam = "")
#set ($txtBandarPeguam = "")
#set ($txtNomborTelefonPeguam = "")
#set ($txtTarikhKaveat = "")


#set ($tujuanPindah = "") <!-- razman add -->

#foreach ($ListFail in $ViewFail)
<input name="id_Suburusanstatus" type="hidden"  value="$ListFail.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$ListFail.id_Suburusanstatusfail"/>
#end



#set ($catatan_sd = "")
#set ($txtSuratAkuanARB ="")
#foreach ($ListData in $ViewPemohon)

#set ($jenis_permohonan = $ListData.jenispermohonan)
#set ($idPemohon = $ListData.idPemohon)
#set ($idSimati = $ListData.idSimati)
#set ($idpermohonan_simati = $ListData.id_Permohonansimati)
#set ($salinan_arahan = $ListData.salinan_arahan)
#set ($catatan_sd = $ListData.catatan_sd)
#set ($txtSuratAkuanARB = $ListData.txtSuratAkuanARB)
#set ($tujuanPindah = $ListData.tujuanPindah)<!-- razman add -->

#set ($jenisborangC = $ListData.jenisborangC)




     <input name="id_taraf_mohon" type="hidden"  value="$ListData.id_taraf_mohon"/>
     <input name="taraf_pemohon" type="hidden"  value="$ListData.taraf_pemohon"/>
     #set ($id_taraf_mohon = "$ListData.id_taraf_mohon")
   

    <input name="idFail" type="hidden"  value="$ListData.idFail"/>
    #set ($idFail = $ListData.idFail)
    
    
    <input name="id_Fail" type="hidden"  value="$ListData.idFail"/>
    <input name="idpermohonansimati" type="hidden"  value="$ListData.id_Permohonansimati"/>

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
    
    #set ($security_Code_Link = $ListData.sCL)
    <input name="sCL" type="hidden"  value="$ListData.sCL"/>
    
	#set ($result_BorangC = $ListData.res_BrgC)
	<input name="res_BrgC" type="hidden"  value="$ListData.res_BrgC"/>
	
	#set ($kep_BorangC = $ListData.kep_BrgC)
	<input name="kep_BrgC" type="hidden"  value="$ListData.kep_BrgC"/>
        
	#set ($tarikh_Hantar_Nilaian = $ListData.tarikhhantarnilaian)
    <input name="tarikhhantarnilaian" type="hidden"  value="$ListData.tarikhhantarnilaian"/>
    
	#set ($tarikh_Terima_Nilaian = $ListData.tarikhterimanilaian)
    <input name="tarikhterimanilaian" type="hidden"  value="$ListData.tarikhterimanilaian"/>
    
    <input name="tarikh_mohon" type="hidden"  value="$ListData.tarikhMohon"/>
    
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
    
    
    
#set($txtNamaKaveat = $ListData.txtNamaKaveat)
#set($txtNoKaveat = $ListData.txtNoKaveat)
#set($txtNamaFirma = $ListData.txtNamaFirma)
#set($txtAlamat1Peguam =  $ListData.txtAlamat1Peguam)
#set($txtAlamat2Peguam = $ListData.txtAlamat2Peguam)
#set($txtAlamat3Peguam = $ListData.txtAlamat3Peguam)
#set($txtPoskodPeguam = $ListData.txtPoskodPeguam)
#set($socNegeriPeguam = $ListData.socNegeriPeguam)
#set($txtBandarPeguam = $ListData.txtBandarPeguam)
#set($txtNomborTelefonPeguam = $ListData.txtNomborTelefonPeguam)
#set($txtTarikhKaveat = $ListData.txtTarikhKaveat)
    
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

<!-- baru tambah -->
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


#foreach ($ll in $listB)
#set ($tarikh_Hantar_BorangB = $ll.TARIKH_B)
#end


<!--  Edited Baru 28/12/2012  Hazwan  -->
#foreach ($lc in $listC)
#set ($tarikh_Terima_BorangC = $lc.TARIKH_C)
#set ($security_Code_Link = $lc.SECURITY_C)
#set ($result_BorangC = $lc.RESULT_C)
#end
 
#if ($result_BorangC=="WH")
	#set ($kep_BorangC=" - <font color='#FFFFFF'>PUTIH</font>")
#elseif ($result_BorangC=="YL")
	#set ($kep_BorangC=" - <font color='#FFFF00'>KUNING</font>")
#end
 
#end

<!--
::::::::::::::: $keputusan
:::::::::( eve : $eve )
-->


#if($eve == 8)

	#set ($tujuanPindah = $tujuanPindah) <!-- razman add -->
    #set ($tarikh_Hantar_BorangB = $tarikh_Hantar_BorangB)
    #set ($tarikh_Terima_BorangC = $tarikh_Terima_BorangC)
    #set ($security_Code_Link = $security_Code_Link)
	#set ($result_BorangC = $result_BorangC)
	#set ($kep_BorangC = $kep_BorangC)
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
    #elseif($jenis_pej == 99)    
    	#set($checkJ4="checked") 
    #else

    #set($checkJ3="")
    #set($checkJ4="")
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
 
 
 
#elseif ($keputusan == "50")
 
 #set ($check4 = "checked")  
     
   
 
 #elseif ($keputusan == "53")
 
 #set ($check3 = "checked")
 
 #elseif ($keputusan == "70")
 
 #set ($check5 = "checked")   

     
 #end  
  
  #set($negerimahkamahX = $negerimahkamah)
  #set($daerahmahkamahX = $daerahmahkamah)



#end



 


#if ($EventStatus == 0 && $id_Status != 14 )


 #set($negerimahkamahX = $negerimahkamah)
  #set($daerahmahkamahX = $daerahmahkamah)


     #set ($viewcheckJ1 = "")
     #set ($viewcheckJ2 = "")
     #set ($viewcheckJ3 = "")
       #set ($viewcheckJ4 = "")

#if ($jenisborangc == "P")
	#set ($check8 = "checked")
   
#elseif ($jenisborangc == "K")
	#set ($check9 = "checked")
   
#end

  #if ($keputusan == "53")
	#set ($check3 = "checked")
    #set ($check1 = "")
    #set ($check2 = "")
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "")
    #set ($setMode5 = "")<!-- razman remove disable -->
    #set ($setMode6 = "")
    
    
    
    #elseif ($keputusan == "151")
	#set ($check0 = "checked")
    #set ($check1 = "checked")
    
    
    #set ($setMode = "")
    #set ($setMode1 = "")
    
    #if($Overalldum >= $nilaiHartaMaximum)
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

#elseif($jenis_pej == 99)

	#set($checkJ4="checked")

#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")
#set($checkJ4="")

#end
    
    
    #elseif ($keputusan == "152")
	#set ($check0 = "checked")
    #set ($check2 = "checked")
    
     #set ($setMode = "")
    #set ($setMode1 = "")
    
  
    
    #if($Overalldum >= $nilaiHartaMaximum)
    
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

#elseif($jenis_pej == 99)
#set($checkJ3="checked")
#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")
#set($checkJ4="")

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
    #set ($setMode5 = "")
    #set ($setMode6 = "")
   
#elseif ($keputusan == "70")
 <!-- //ubah  -->
    #if($Overalldum >= $nilaiHartaMaximum)
   
    #set ($check5 = "checked")
    #set ($check1 = "")
    #set ($check2 = "")  
    
    #set ($setMode = "")
    
    
    #if($jenisborangc == "P")    
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "disabled")    
    #set ($setMode5 = "") 
    #end
    
    #if($jenisborangc == "K")    
    #set ($setMode1 = "")   
    #set ($setMode2 = "disabled")
    #set ($setMode3 = "")
    #set ($setMode4 = "disabled")
    #set ($setMode5 = "disabled")     
    #end
    
    
   
    
    #set ($setMode6 = "")
    
    #set ($setMode99 = "disabled")
    #else
    #set ($setMode99 = "")
	#set ($check5 = "checked")
    #set ($check1 = "")
    #set ($check2 = "")  
   
    #set ($setMode = "")
    
    #if($jenisborangc == "P")    
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "")    
    #set ($setMode5 = "disabled") 
    #end
    
    #if($jenisborangc == "K")    
    #set ($setMode1 = "")   
    #set ($setMode2 = "")
    #set ($setMode3 = "")
    #set ($setMode4 = "disabled")
    #set ($setMode5 = "disabled")     
    #end
    
    #set ($setMode6 = "")
    #end
    
    
   


#end



#elseif ($EventStatus == 1 && $id_Status != 14 )



 #set($negerimahkamahX = $negerimahkamah)
  #set($daerahmahkamahX = $daerahmahkamah)

    
    #set ($EventStatus = 1)
    #set ($setMode = "disabled")
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "disabled")
    #set ($setMode5 = "disabled")
    #set ($setMode6 = "disabled")
    
     #set ($viewcheckJ1 = "disabled")
     #set ($viewcheckJ2 = "disabled")
     #set ($viewcheckJ3 = "disabled")
     #set ($viewcheckJ4 = "disabled")
    
       
    
    
    #if ($jenisborangc == "P")
	#set ($check8 = "checked")
   
#elseif ($jenisborangc == "K")
	#set ($check9 = "checked")
   
#end
    
    
    #if ($keputusan == "53")
	#set ($check3 = "checked")
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

#elseif($jenis_pej == 99)
#set($checkJ4="checked")
#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")
#set($checkJ4="")

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
#elseif($jenis_pej == 99)
#set($checkJ4="checked")
#else

#set($checkJ3="")
#set($checkJ2="")
#set($checkJ1="")
#set($checkJ4="")

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

<!--
 EventStatus :::::::::::: $EventStatus 
 jenisborangc ::::::::::: $jenisborangc
 -->
	
 #set ($EventStatus = 0)  
 #set($dum=$Overalldum)

    #if($dum >= $nilaiHartaMaximum) 
    
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
        #set($catatan = $catatanx)   
    #else 
          
        #if ($jenisborangc == "P")
       
            #set ($check8 = "checked")   
            #set  ($check3 = "")     
            #set ($setMode = "")
            #set ($setMode1 = "")
            #set ($setMode2 = "")
            #set ($setMode3 = "")
            #set ($setMode4 = "disabled")
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
            #set ($setMode = "")
            #set ($setMode1 = "disabled")
            #set ($setMode2 = "disabled")
            #set ($setMode3 = "disabled")
            #set ($setMode4 = "")
            #set ($setMode5 = "disabled")
            #set ($setMode6 = "")      
            #set  ($check0 = "")   
            #set  ($check1 = "")   
            #set  ($check2 = "") 
            #set  ($check4 = "")   
            #set  ($check5 = "")      
            #set ($check9 = "")   
        #else
     
        
       		#if($!headerppk.TOTAL_HTA == 0 && $!headerppk.TOTAL_HA == 0)
     		
                #set ($check8 = "checked")
                #set ($check9 = "")
                #set ($check3 = "") 
                   
                #set ($setMode = "")
                #set ($setMode1 = "disabled")
                #set ($setMode2 = "disabled")
                #set ($setMode3 = "disabled")
                #set ($setMode4 = "disabled")
                #set ($setMode5 = "disabled")
                #set ($setMode6 = "") 
                     
                #set  ($check0 = "")   
                #set  ($check1 = "")   
                #set  ($check2 = "") 
                #set  ($check4 = "")   
                #set  ($check5 = "checked")
            #else
             	#set ($check8 = "checked")
                #set ($check9 = "")
                #set  ($check3 = "checked")    
                #set ($setMode = "")
                #set ($setMode1 = "disabled")
                #set ($setMode2 = "disabled")
                #set ($setMode3 = "disabled")
                #set ($setMode4 = "")
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

#set($check107 = "")

       #if ($keputusan == "107") <!-- SD -->
       		
       		#set ($check107 = "checked")
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
<fieldset id="header_lama" style="display:none" >

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
        <input type="hidden" value="$tarikh_mohon" name="tarikh_permohonan" id="tarikh_permohonan" /></td>
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

#if($setMode == "disabled") 
#set($setModeR = "readonly") 
#else #set($setModeR = "") 
#set($setMode = "") 
#end 




<table width="100%" border="0">
  <tr>
    <td width="50%" scope="col"><fieldset>
    <legend>TARIKH</legend>
    <table width="100%" border="0">
      <tr>
      <td width="2%">
       #if($setMode != "disabled")
         <font color="red">*</font>
         #end 
      </td>
        <td scope="col" width="40%">
        
        <strong>Hantar Borang B</strong></td>
        <td scope="col" width="58%" colspan="2">
          <input name="txdTarikhHantarBorangB" type="text" class="$setMode"  id="txdTarikhHantarBorangB"  value="$tarikh_Hantar_BorangB" size="11" maxlength="10" $setModeR  onBlur="trans_date1(this.value);checkDateKP()" onKeyPress="checkDateKP()" onFocus="checkDateKP()" /> 
          &nbsp;
          #if ($EventStatus != 1)
          <a href="javascript:displayDatePicker('txdTarikhHantarBorangB',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a>
          #end
        </td>
      </tr>
      <tr>
      <td width="2%">
      </td>
        <td scope="row"><strong>Terima Borang C</strong></td>
        <td><input name="txdTarikhTerimaBorangC"  type="text" class="$setMode" id="txdTarikhTerimaBorangC"  value="$tarikh_Terima_BorangC" size="11" maxlength="10" $setModeR onBlur="trans_date2(this.value)" />
          &nbsp;
          #if ($EventStatus != 1)
          <a href="javascript:displayDatePicker('txdTarikhTerimaBorangC',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a></td>
      	  #end
      	<td><!-- #if ($security_Code_Link!="")<a href="javascript:muatTurunBorangC()"><font color="#0000FF"><b>Muat Turun Borang C</b></font></a>#end --></td>
      </tr>
      
      <tr>
      <td width="2%">
      #if($setMode != "disabled")
         <font color="red">*</font>
         #end
      </td>
        <td scope="row">
        
        <strong>Hantar Nilaian</strong></td>
        <td colspan="2"><input name="txdTarikhHantarNilaian" type="text" class="$setMode" id="txdTarikhHantarNilaian"  value="$tarikh_Hantar_Nilaian" size="11" maxlength="10" $setModeR  onBlur="trans_date3(this.value);checkDateKP()"   onKeyPress="checkDateKP()"  onFocus="checkDateKP()"/>
          &nbsp;
          #if ($EventStatus != 1)
          <a href="javascript:displayDatePicker('txdTarikhHantarNilaian',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a></td>
          #end
      </tr>
      <tr>
      <td width="2%">
      </td>
        <td scope="row"><strong>Terima Nilaian</strong></td>
        <td colspan="2"><input name="txdTarikhTerimaNilaian" type="text" class="$setMode" id="txdTarikhTerimaNilaian"  value="$tarikh_Terima_Nilaian" size="11" maxlength="10" $setModeR  onBlur="trans_date4(this.value)" />
         &nbsp;
		#if ($EventStatus != 1)
		<a href="javascript:displayDatePicker('txdTarikhTerimaNilaian',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a></td>
      	#end
      </tr>
      <tr height="81">
      <td width="2%">
      </td>
      <td valign="top" colspan="3"  align="justify"><div id="alert_field"></div></td>
 		
      </tr>
      <!-- 
      #if ($security_Code_Link!="")
      		<tr><td valign="top" colspan="2" align="center">
      		<a href="javascript:muatTurunBorangC()"><font color="#0000FF"><b>Muat Turun Borang C</b></font></a>
      		<input type="button" name="cmdBrgC" id="cmdBrgC" value="Muat Turun Borang C" onClick="muatTurunBorangC()"/>
      		</td></tr>
            <tr><td valign="top" colspan="2" align="justify"><a href="http://ettap.kehakiman.gov.my/BKM/bkm_frmSearchProbatePublic.aspx?SecurityCode=$!security_Code_Link">Capaian muat turun borang C</a></td></tr>
      #end
       -->
    </table>
    
    </fieldset></td>
    <td width="50%" scope="col"><fieldset>
    <legend>KEPUTUSAN BORANG C$kep_BorangC</legend>
    <table width="100%" border="0">
      <tr>
        <td scope="col"><label>
        <input type="radio" name="sorKeputusanBorangC" id="sorKeputusanBorangC" value="P" $check8 $setMode onClick="putih();setTableB('tableReportA','tableReportX')"/>
        Putih (Tiada Permohonan Terdahulu)
        </label></td>
      </tr>
      <tr>
        <td scope="col"><input type="radio" name="sorKeputusanBorangC" id="sorKeputusanBorangC" value="K" $check9 $setMode onClick="kuning();setTableA('tableReportA','sorKeputusanBorangC','sorPenentuanBidangKuasa')"/>
Kuning (Ada Permohonan Terdahulu / Kaveat)</td>
      </tr>
      <tr>
        <td scope="col">&nbsp;</td>
      </tr>	
    </table>
    </fieldset>
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
    </fieldset></td>
  </tr>
  <!-- tambah textbox hidden -->
  <tr>
  	<td>
  		<input type="hidden" name="txdflag5juta" style="visibility:hidden" value="$flag5juta"/>
    </td>
  </tr>
  <tr>
  	<td>
  		<input type="hidden" name="txdnilaiMax" style="visibility:hidden" value="$nilaiHartaMaximum"/>
    </td>
  </tr>
  <tr>
    <td height="280" colspan="2" scope="col">
    
    #if($setMode == "disabled")
    #set($setModeR = "readonly")
    #else
    #set($setModeR = "")
    #end
    <fieldset>
    <legend>PENENTUAN BIDANG KUASA</legend>
    <table width="100%" border="0">
      <tr>
        <td width="15%" scope="col">&nbsp;</td>
        <td width="1%" scope="col">&nbsp;</td>
        <td width="84%" colspan="2" scope="col"><label>
          <input name="sorPenentuanBidangKuasa" type="radio" id="sorPenentuanBidangKuasa" $check0  onclick="selectRadio1();setTableA('tableReportA','sorKeputusanBorangC','sorPenentuanBidangKuasa')" value="1" $setMode1 />
          
          <input name="setMode1" id="setMode1" type="hidden" value="$setMode1" >
       
        Telah ada permohonan awal / kaveat</label></td>
      </tr>
     <tr>      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td scope="row">&nbsp;</td>
        <td width="3%">&nbsp;        </td>
        <td width="88%">
    
   
        
        
          	<input type="radio" name="sorPenentuanBidangKuasaTeruskan" id="sorPenentuanBidangKuasaTeruskan" $check1 value="151" $setMode2 onClick="selectRadio2();setTableA_X1('tableReportA','sorKeputusanBorangC','sorPenentuanBidangKuasa')" />
           <input name="setMode2" id="setMode2" type="hidden" value="$setMode2" >
           Teruskan
         
        	<input type="radio" name="sorPenentuanBidangKuasaTeruskan" id="sorPenentuanBidangKuasaTeruskan" $check2 value="152" $setMode3 onClick="selectRadio3();setTableA_X1('tableReportA','sorKeputusanBorangC','sorPenentuanBidangKuasa')" />
         <input name="setMode3" id="setMode3" type="hidden" value="$setMode3" >
        Batal</td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td scope="row">&nbsp;</td>
        <td colspan="2">
		<table width="90%" border="0">
  <tr>
    <td> 
  
            <fieldset id="tableReportA" style="display:none"      > 
        
        <legend>MAKLUMAT PERMOHONAN AWAL</legend>
        
        <table width="100%" border="0">
  <tr>
    <td><table width="100%" border="0">
      <tr>
        <td width="1%" >&nbsp;</td>
        <td width="4%" >
        	<label><div align="right">
            	<input type="radio" name="radio_j" id="radio" value="9" $checkJ1 $viewcheckJ1 onClick="setTableA_JENISA('tableReportA',this.value,'sorKeputusanBorangC','nofailawal')" />
				<input type="hidden" name="checkJ1" id="checkJ1" value="$checkJ1"  />
      		</div></label>
        </td>
        <td width="30%">AMANAH RAYA BERHAD</td>
        
      	<td width="1%" id="ptd1">&nbsp;</td>
        <td width="4%" id="ptd2">
        	<label><div align="right">
            	<input type="radio" name="radio_j" id="radio" value="2" $checkJ2 $viewcheckJ2 onClick="setTableA_JENISB('tableReportA',this.value,'sorKeputusanBorangC','nofailawal')" />
         		<input type="hidden" name="checkJ2" id="checkJ2" value="$checkJ2"  />
            </div></label>
        </td>
        <td width="7%" id="ptd3">PTD</td>
        
        <td width="1%" id="mah1">&nbsp;</td>
        <td width="4%"  id="mah2">
        	<label><div align="right">
            	<input type="radio" name="radio_j" id="radio" value="8" $checkJ3 $viewcheckJ3 onClick="setTableA_JENISC('tableReportA',this.value,'sorKeputusanBorangC','nofailawal')" />
           		<input type="hidden" name="checkJ3" id="checkJ3" value="$checkJ3"  />
            </div></label>
        </td>    
        <td width="15%"  id="mah3">MAHKAMAH</td>
        
        <td width="1%">&nbsp;</td>
        <td width="4%" >
        	<label><div align="right" id="kv1" style="display:none">
         		<input type="radio" name="radio_j" id="radio" value="99" $checkJ4 $viewcheckJ4 onClick="setTableA_JENIS_KAV('tableReportA',this.value,'sorKeputusanBorangC','txtNamaKaveat')" />
           		<input type="hidden" name="checkJ4" id="checkJ4" value="$checkJ4"  />
            </div></label>
        </td>
        <td width="30%" ><label  id="kv2" style="display:none">MAHKAMAH (KAVEAT)</label></td>
      
        <!--       
        <td width="1%">&nbsp;</td>
        <td width="4%" id="kv1" style="display:none">
      
          <div align="right">
            <input type="radio" name="radio_j" id="radio" value="99" $checkJ4 $viewcheckJ4 onClick="setTableA_JENIS_KAV('tableReportA',this.value,'sorKeputusanBorangC','txtNamaKaveat')" />
           <input type="hidden" name="checkJ4" id="checkJ4" value="$checkJ4"  />
            </div>
       
        </td>
     
        <td width="30%"  id="kv2" style="display:none">      
        MAHKAMAH (KAVEAT)         
        </td>        
        -->
         
        </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <fieldset id="bukan_kaveat" style="display:">
    <table width="100%" >
      <tr>
        <td width="1%"  valign="top" class="style6 style5" >#if($setMode != "disabled")*#end</td>
        <td width="28%"><div align="right" class="style8">
          <div align="left">#if($setMode != "disabled") No Fail Awal #else
            No Fail Awal 
            #end</div>
        </div></td>
        <td width="1%">:</td>
        <td width="70%"><label>
      
         
          <input name="nofailawal" id="nofailawal" type="text" $setModeR class="$setMode" style="text-transform:uppercase;" size="50"  value="$failawal" onBlur="this.value=this.value.toUpperCase()" />
        </label></td>
      
      </tr>
      <tr>
        <td width="1%"  valign="top" class="style6 style5" >#if($setMode != "disabled")*#end</td>
        <td><div align="right" class="style8">
          <div align="left">#if($setMode != "disabled") Pemohon Awal #else
            Pemohon Awal
            #end</div>
        </div></td>
        <td>:</td>
        <td><label>
         
          <input name="namapemohonawal" id="namapemohonawal" $setModeR class="$setMode" style="text-transform:uppercase;" type="text"  size="50" maxlength="200" value="$mohonawal"  onblur="this.value=this.value.toUpperCase()" />
        </label></td>
      </tr>
        
        
        
  #if($jenis_pej == "2") 
  #if($pilih_negeri_ptd == "yes")
  #else 
  
  #if($jenis_pej_id != "" && $jenis_pej_id != "0")
  
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej_id ) 
          
     
           #set($idneg = $listJ.idnegeri)
           #set($socNegeriWarisSurat =  $listJ.idnegeri)
           
           
           #end
           #end
  
  
  #else
  #set($socNegeriWarisSurat = "")
  #end
  
  #end
  
  
      <tr>
                                   <td valign="top" class="style38 style6 style5"> #if($setMode != "disabled")
                                    <span class="style40">*</span>#end</td>
                                    <td class="style38"><div align="right" class="style51 style6">
                                      <div align="left">#if($readmode != "disabled") Negeri #else <span class="style72">Negeri</span> #end</div>
                                    </div></td>
                                  
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                  
                                    <td>
                                     #if($setMode == "disabled")
                                   
                                   #foreach($listnegpomo in $listnegeri)
                                    
                                    #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)
                                    
                                    #set($negerikodpemoPs=$listnegpomo.kod_Negeri)
                                    #set($negeriketeranganpemoPs=$listnegpomo.nama_Negeri)
                                    
                                    
                                    
                                    #end 
                                    #end
                                   
                                     #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!="0"  )
                                     <input name="ns" value="$negerikodpemoPs - $negeriketeranganpemoPs" size="50" style="text-transform:uppercase;"  $setModeR class="$setMode"  />
                                     #else
                                     <input name="ns" value="" size="50" style="text-transform:uppercase;"  $setModeR class="$setMode"  />
                                     #end
                                    
                                   
                                   #else
                                    
                                    
                                    #foreach($listnegpomo in $listnegeri)                                    
                                    #if($socNegeriWarisSurat==$listnegpomo.id_Negeri)                                    
                                    #set($kod = "$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri")
                                    #end                                     
                                    #end
                                  
                                    #if($socNegeriWarisSurat!="" && $socNegeriWarisSurat!=0 )
                                    <select name="socNegeriWarisSurat" class="autoselect" id="socNegeriWarisSurat"  $readmode onChange="setTableA_JENISB_NEG('tableReportA',2,'txtNilaianHTA','tempatmohonawal')" >

                                      <option value="$socNegeriWarisSurat" style="text-transform:uppercase;" onBlur="uppercase()">$kod</option>
                                      
                                        
                  
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($socNegeriWarisSurat!=$listneg.id_Negeri)
                       
                   #if($listneg.id_Negeri != "99")
                                        
                                      <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                      
                                        #end
                  
                                     
                                  #end    
	                              #end
                                        
                
                                      
                                    </select>
#else
<select name="socNegeriWarisSurat" class="autoselect" onChange="setTableA_JENISB_NEG('tableReportA',2,'txtNilaianHTA','tempatmohonawal')"  >
  <option value="" >SILA PILIH NEGERI</option>
  
  
                  
                                  #foreach($listneg in $listnegeri)
                   
                  #if($listneg.id_Negeri != "99")

  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onBlur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
  
  #end
                  
                                    
	                               #end
                                        
                

</select>
#end
#end </td>
                            </tr>
      
      
      #end
      <tr>
        <td width="1%"  valign="top" class="style6 style5" >#if($setMode != "disabled")*#end</td>
        <td><div align="right" class="style8">
          <div align="left">
          #if($setMode != "disabled") Tempat Permohonan Awal #else
          Tempat Permohonan Awal
          #end </div>
          </div></td>
          <td>:</td>
          <td><label>
        
           #if($setMode == "disabled")
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej_id && $listJ.jenispejabat == $jenis_pej ) 
             
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           <!-- #set($listJdaerah = $listJ.daerah) -->
           #set($listJbandar = $listJ.namabandar)
           #end
           #end
             #set($alam = "$listJnama_pejabat, $listJbandar")

           <div align="left" class="style2" style="text-transform:uppercase;" >
           $alam           </div>
           
           #else
 
 
 
          <select name="tempatmohonawal" id="tempatmohonawal" class="autoselect" $setMode style="text-transform:uppercase;" onChange="alamatP(this.value,'txtNilaianHTA','tempatmohonawal');"  onFocus="pilih_pilih()">
       
       
      
          #if($jenis_pej_id == "" || $jenis_pej_id == "0")
          
          <option value="0">SILA PILIH </option>
          
           #else
           
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej_id ) 
             
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)
             #set($nama_bandar = $listJ.namabandar) 
           
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar  </option>
           
           #end
        
       
          
                                   #if($jenis_pej == "2") 
                                   #foreach($listJ in $listMaklumatMahkamahJ)
                                   #if($jenis_pej_id != $listJ.id_Pejabat && $listJ.jenispejabat == $jenis_pej )  
                                   #if($listJ.idnegeri == $socNegeriWarisSurat)
	                               <option value="$listJ.id_Pejabat">$listJ.nama_pejabat, $listJ.namabandar  </option>                                   
                                   #end                                  
                                   #end 
                                   #end
                                   #else                                 
                                   #foreach($listJ in $listMaklumatMahkamahJ)
                                   #if($jenis_pej_id != $listJ.id_Pejabat && $listJ.jenispejabat == $jenis_pej )                                 
	                               <option value="$listJ.id_Pejabat">$listJ.nama_pejabat, $listJ.namabandar</option>
                                   #end                                   
                                   #end                                  
                                   #end 
                                  
          </select>
        #end
        </label></td>
      </tr>
    </table>
 
    
    
    
    </fieldset>    </td>
  </tr>
 
  <!-- listMaklumatMahkamahJ = $listMaklumatMahkamahJ -->
   #foreach($listJD in $listMaklumatMahkamahJ)
    #if($jenis_pej_id == $listJD.id_Pejabat )  
   
   #set($al_1=$listJD.alamat1)
   #set($al_2=$listJD.alamat2)
   #set($al_3=$listJD.alamat3)
   #set($al_pos=$listJD.poskod)
   #set($al_daerah=$listJD.daerah)
   #set($al_bandar=$listJD.namabandar)
   #set($al_idbandar=$listJD.idbandar)
   #set($al_negeri=$listJD.negeri)
   #set($al_idnegeri=$listJD.idnegeri)
   #set($al_iddaerah=$listJD.iddaerah)
   #set($al_tel=$listJD.no_tel)
   #set($al_faks=$listJD.no_fax)
   #end
   
  #end
 
  #if($jenis_pej_id != "0" && $jenis_pej_id != "")
  <tr>
    <td>
    
   
    <table width="100%" id="bukan_alamatkaveat" style="display:">
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="29%"><div align="right" class="style6">
          <div align="left">Alamat Pejabat</div>
        </div></td>
        <td width="1%">:</td>
        
        
        <td width="70%"><div align="left" class="style2" style="text-transform:uppercase;" >
        #if($al_1!="")
        <input type="hidden" name="txtAlamatTerakhir1Penting" id="txtAlamatTerakhir1Penting" value="$al_1">
        $al_1
        #else
        -
        #end
        
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="left"><span class="style6"></span></div></td>
        <td>:</td>
        <td><div align="left" class="style2" style="text-transform:uppercase;" >
        #if($al_2!="")
        <input type="hidden" name="txtAlamatTerakhir2Penting" id="txtAlamatTerakhir2Penting" value="$al_2">
        $al_2
        #else
        -
        #end
        
        
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="left"><span class="style6"></span></div></td>
        <td>:</td>
        <td><div align="left" class="style2" style="text-transform:uppercase;" >
        #if($al_3!="")
        <input type="hidden" name="txtAlamatTerakhir3Penting" id="txtAlamatTerakhir3Penting" value="$al_3">
        $al_3
        #else
        -
        #end
        
        
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="right" class="style6">
          <div align="left">Poskod</div>
        </div></td>
        <td>:</td>
        <td><div align="left" class="style2" style="text-transform:uppercase;" >
         #if($al_pos!="")
         <input type="hidden" name="txtPoskodPenting" id="txtPoskodPenting" value="$al_pos">
       $al_pos
        #else
        -
        #end
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="right" class="style6">
          <div align="left">Bandar</div>
        </div></td>
        <td>:</td>
        <td><div align="left" class="style2" style="text-transform:uppercase;" >
        #if($al_bandar!="") 
         
        
        <input type="hidden" name="txtBandarPenting" id="txtBandarPenting" value="$al_bandar">
        <input type="hidden" name="txtIdBandarPenting" id="txtIdBandarPenting" value="$al_idbandar">
        $al_bandar
       
        #else
        -
        #end
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="right" class="style6">
          <div align="left">Negeri</div>
        </div></td>
        <td>:</td>
        <td><div align="left" class="style2" style="text-transform:uppercase;" >
          #if($al_negeri!="")
          <input type="hidden" name="txtNegeriPenting" id="txtNegeriPenting" value="$al_negeri">
          <input type="hidden" name="txtIdNegeriPenting" id="txtIdNegeriPenting" value="$al_idnegeri">
		$al_negeri
        #else
        -
        #end
        </div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="right" class="style6">
          <div align="left">No Tel</div>
        </div></td>
        <td>:</td>
        <td><div align="left" class="style2" style="text-transform:uppercase;" >
         #if($al_tel!="")
        <input type="hidden" name="txtNoTeleponPenting" id="txtNoTeleponPenting" value="$al_tel">
       $al_tel
        #else
        -
        #end
        
        
        </div></td>
      </tr>
      
            <tr>
        <td>&nbsp;</td>
        <td><div align="right" class="style6">
          <div align="left">No Faks</div>
        </div></td>
        <td>:</td>
        <td><div align="left" class="style2" style="text-transform:uppercase;" >
         #if($al_faks!="")
        <input type="hidden" name="txtNoFaxPenting" id="txtNoFaxPenting" value="$al_faks">
       $al_faks
        #else
        -
        #end
        
        
        </div></td>
      </tr>
      
      #if($check2 != "checked") 
      <tr>
        <td><span class="style5"></span></td>
        <td><div align="right" class="style6 style5 style8">
            <div align="left">Salinan Arahan/Akuan</div>
        </div></td>
        <td>:</td>
        
        #if($salinan_arahan=="1") 
        
        <td><input type="checkbox" name="salinanArahan" id="salinanArahan" $setMode  checked onClick="javascript:buttonkeHartaAlih(this);">Telah diterima pada <input type="text" name="tarikhsuratARB" id="tarikhsuratARB"  value=$txtSuratAkuanARB $setModeR class="$setMode"/>
        &nbsp;#if($setMode != "disabled")<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('tarikhsuratARB',false,'dmy');" />#end
        
        <div id="divbuttonkeHartaAlih" style="display: block">
    <input type="button" id="butonkeHartaAlih" value="Ke skrin Harta Alih" onClick="javascript:jumptoHartaAlih('$jenis_permohonan','$idPermohonan','$idPemohon','$idSimati','$idpermohonan_simati','$al_negeri')"/></div></td>
        #else
        <td><input type="checkbox" name="salinanArahan" id="salinanArahan" $setMode value="1" onclick="checkTarikh()">Telah diterima2</td>
        #end
      </tr>
      
      <tr>
      
      <td colspan="4">
      <div id="tarikhTerimaARB"  style="display:none;" >
      <table>
      <tr>
      		<td><span class="style5"></span></td>
            <td>&nbsp;Tarikh Terima Surat Akuan dari ARB</td>
     		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</td>
      		<td>&nbsp;<input type="text" name="tarikhsuratARB" id="tarikhsuratARB"  $setModeR class="$setMode"/>&nbsp;<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('tarikhsuratARB',false,'dmy');" /></td>
      </tr>
      </table>
      </div>
      </td>
      

      
      </tr>
      
      #end
    </table>    </td>
  </tr>
  
 
  #end
</table>




 

  <fieldset id="kaveat" style="display:none" >
   <input type="button" name="buttonTambah1" id="buttonTambah1" value="Tambah" onclick="tambahKaveat('$idPermohonan')"/>
    #set( $ii = 0 )
   #foreach($listKaveatPeguam2 in $listKaveatPeguam)
     #set($ii = $ii+1)
   #end
   
   #if ($ii > 1)
  <table width="100%">
  <tr class="table_header">
  <td>Bil</td>
  <td>Nama Pengkaveat</td>
  <td>No. Kaveat</td>
  <td>Nama Firma</td>
  </tr>
  #foreach($listKaveatPeguam2 in $listKaveatPeguam)
	  						#set( $i = $velocityCount )
					    	#if ( ($i % 2) != 1 )
					       		#set( $row = "row2" )
					    	#else
					       		#set( $row = "row1" )
					    	#end      
	                        <tr bgcolor="white" class="$row">

  <td>$i</td>
  <td>$!listKaveatPeguam2.NAMA_KAVEAT</td>
  <td>$!listKaveatPeguam2.NO_KAVEAT</td>
  <td>$!listKaveatPeguam2.NAMA_FIRMA</td>
  </tr>
  #end
  </table>
  #end
  #if ($ii < 2)
  <table width="100%">
             <tr>
  <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
                <td width="28%"><div align="right" class="style53 style6 style6">
                <div align="left">Nama Pengkaveat</div>
              </div>                </td>
              <td width="1%"><div align="center" class="style38">:</div></td>
              <td width="70%"><label>
                <input name="txtNamaKaveat" type="text" id="txtNamaKaveat" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$txtNamaKaveat" size="50" maxlength="150" $setModeR class="$setMode" />
              </label></td>
            </tr>
            <tr>
               <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
              <td><div align="right" class="style53 style6 style6">
                <div align="left">No. Kaveat</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtNoKaveat" type="text" id="txtNoKaveat" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="34" $setModeR class="$setMode" value="$txtNoKaveat" maxlength="25" />
              </label>
              
              </td>
            </tr>
            <tr>
              <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
              <td width="28%"><div align="right" class="style53 style6 style6">
                <div align="left">Nama Firma</div>
              </div>                </td>
              <td width="1%"><div align="center" class="style38">:</div></td>
              <td width="70%"><label>
                <input name="txtNamaFirma" type="text" id="txtNamaFirma" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="50" $setModeR class="$setMode"  value="$txtNamaFirma"/>
              </label></td>
            </tr>
            
            <tr>
                <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
              <td class="style38"><div align="right" class="style53 style6 style6">
                 <div align="left">Alamat </div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtAlamat1Peguam" type="text" id="txtAlamat1Peguam" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$txtAlamat1Peguam" size="50" maxlength="150"  $setModeR class="$setMode" />
              </label></td>
            </tr>
            <tr>
                <td width="1%" valign="top">&nbsp;</td>
              <td class="style38"><div align="left"><span class="style3"><span class="style52"><span class="style6"><span class="style6"></span></span></span></span></div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtAlamat2Peguam" type="text" id="txtAlamat2Peguam" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$txtAlamat2Peguam" size="50" maxlength="150" $setModeR class="$setMode" />
              </label></td>
            </tr>
            <tr>
              <td class="style51 style6 style5">&nbsp;</td>
              <td class="style38"><div align="left"><span class="style3"><span class="style52"><span class="style6"><span class="style6"></span></span></span></span></div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><input name="txtAlamat3Peguam" type="text" id="txtAlamat3Peguam"   style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$txtAlamat3Peguam" size="50" maxlength="150" $setModeR class="$setMode" /></td>
            </tr>
            <tr>
                <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
              <td><div align="right" class="style53 style6 style6">
                 <div align="left">Poskod</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtPoskodPeguam" type="text" id="txtPoskodPeguam" size="5" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPeguam')" maxlength="5" $setModeR class="$setMode" value="$txtPoskodPeguam"/>
              </label></td>
            </tr>
            <tr>
              <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
            <td width="28%" class="style38"><div align="right" class="style51 style52 style6">
              <div align="left">#if($setMode != "disabled")  Negeri #else <span class="style52">Negeri</span> #end</div>
            </div></td>
            <td width="1%"><div align="center" class="style38">:</div></td>
            <td width="70%">
            
            
            
            #if($setMode == "disabled")
             #if($socNegeriPeguam==""||$socNegeriPeguam==0)
                                      
            #set($negerikodpeguam="")
                                      
            #else
            
            #foreach($listnegpeg in $listnegeri)           
            #if($socNegeriPeguam==$listnegpeg.id_Negeri)
            #set($negerikodpeguam="$listnegpeg.kod_Negeri - $listnegpeg.nama_Negeri")
            #end 
            
            #end 
            #end      
            <input name="socNegeriPeguam" type="text" class="$setMode" id="socNegeriPeguam" style="text-transform:uppercase;" value="$negerikodpeguam" size="50" $setModeR />
            #else
        
            #if($socNegeriPeguam==""||$socNegeriPeguam==0)
                                      
            #set($negerikodpeguam="")
                                      
            #else
           
            #foreach($listnegpeg in $listnegeri)           
            #if($socNegeriPeguam==$listnegpeg.id_Negeri)
           
            #set($negerikodpeguam="$listnegpeg.kod_Negeri - $listnegpeg.nama_Negeri")
            #end 
            
            #end 
            #end      
           
                          
                                  #if(($socNegeriPeguam=="")||($socNegeriPeguam=="0"))
                                  <select name="socNegeriPeguam" class="autoselect" style="text-transform:uppercase;" onBlur="uppercase()" onChange="getBandarTetap(this.value,'txtNilaianHTA','txtBandarPeguam')" $setMode >
                                  <option value="" style="text-transform:uppercase;" onBlur="uppercase()" >Sila Pilih Negeri</option>
                                  #foreach($listneg in $listnegeri)
                                  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onBlur="uppercase()" >$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                  #end
                                  </select>                                  
                                  #else
                                  <select name="socNegeriPeguam" class="autoselect" id="socNegeriPemohon" style="text-transform:uppercase;" onBlur="uppercase()" onChange="getBandarTetap(this.value,'txtNilaianHTA','txtBandarPeguam')" $setMode >
                                  <option value="$socNegeriPeguam" style="text-transform:uppercase;" onBlur="uppercase()" >$negerikodpeguam</option>
                                  #foreach($listneg in $listnegeri)
                                  #if($socNegeriPeguam!=$listneg.id_Negeri)
                                  <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                  #end    
	                              #end
                                  
                                  </select>

                                  #end
                                  
                        #end        </td>
             </tr>
             
              #set($bandartetap = $txtBandarPeguam)
          <tr>
             <td width="1%" valign="top" >&nbsp;</td>
            <td class="style38"><div align="right" class="style51 style52 style6">
              <div align="left">#if($setMode != "disabled")  Bandar #else <span class="style52">Bandar</span> #end</div>
            </div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td><label>             
              #if($setMode == "disabled")
              #if($bandartetap == ""||$bandartetap == 0)                             
              #set($bandarkodpeguam = "")                                      
              #else
              #foreach($listdaerah in $listBandarTetapbyNegeri)                                
              #if($bandartetap == $listdaerah.id) 
              #set($bandarkodpeguam = "$listdaerah.kod - $listdaerah.nama")                               
              
              #end 
              #end
            
            
            #end      
            <input name="txtBandarPeguam" type="text" class="$setMode" id="txtBandarPeguam" style="text-transform:uppercase;" value="$!bandarkodpeguam" size="50" $setModeR />
            #else
            
              #foreach($listdaerah in $listBandarTetapbyNegeri)                                
              #if($bandartetap==$listdaerah.id)                                
              #set($listDaerahbyNegeriK=$listdaerah.kod)
              #set($listDaerahbyNegeriN=$listdaerah.nama)
              #end 
              #end
              
              
              #if($disabled=="disabled")
              #set($setModedaerah="disabled")
              #end
              #if($bandartetap!="" && $bandartetap!="0" )
              <select name="txtBandarPeguam" id="txtBandarPeguam" class="autoselect" $setMode   style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandarTetap()" >
                                      <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                    
                <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                             
                                   
                                  #end    
	                               #end
                                 
              </select>
              #else
              <select name="txtBandarPeguam" id="txtBandarPeguam" class="autoselect" $setMode   style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandarTetap()" >
                <option value="">Sila Pilih Bandar</option>                
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
	                               #end
              </select>
              #end 
              #end
              </label></td>
          </tr>
            <tr>
               <td width="1%" valign="top">&nbsp;</td>
              <td><div align="right" class="style51 style52 style6 style6">
                <div align="left">No Telefon</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtNomborTelefonPeguam" type="text" id="txtNomborTelefonPeguam" onKeyUp="javascript:validateIC(event,this,this.value,'txtNomborTelefonPeguam')" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  size="14" maxlength="14" $setMode value="$txtNomborTelefonPeguam" class="$setMode" />
              </label></td>
            </tr>
             <tr>
               <td width="1%" valign="top">&nbsp;</td>
              <td><div align="right" class="style51 style52 style6 style6">
                <div align="left">Tarikh Kaveat</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtTarikhKaveat" type="text" id="txtTarikhKaveat" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  size="10" maxlength="10" $setMode value="$txtTarikhKaveat" class="$setMode" />
              </label>
                #if ($EventStatus != 1)
          <a href="javascript:displayDatePicker('txtTarikhKaveat',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a>
          #end
              </td>
            </tr>
        </table>
        #end
       
    
    </fieldset>
    
    

        </fieldset>    </td>
  </tr>
</table>
              <input type="hidden" name="viewcheckJ1" id="viewcheckJ1" value="$viewcheckJ1"  />
             <input type="hidden" name="viewcheckJ2" id="viewcheckJ2" value="$viewcheckJ2"  />
             <input type="hidden" name="viewcheckJ3" id="viewcheckJ3" value="$viewcheckJ3"  />
             <input type="hidden" name="viewcheckJ4" id="viewcheckJ4" value="$viewcheckJ4"  />

        <input type="hidden" name="jenis_pej" id="jenis_pej" value="$jenis_pej" />
        <input type="hidden" name="jenis_pej_id" id="jenis_pej_id" value="$jenis_pej_id" />        </td>
    
  		
        #if($jenis_pej == 99)
        <script>
		
	//	alert("sjkshjdshjsdjhsdhjshjhjhjssd")
		
		if(document.getElementById('kaveat') != null)
		{
		document.getElementById('kaveat').style.display="block";
		}
		if(document.getElementById('kv1') != null)
		{
		document.getElementById('kv1').style.display="block";
		}
		if(document.getElementById('kv2') != null)
		{
		document.getElementById('kv2').style.display="block";
		}
		if(document.getElementById('bukan_kaveat') != null)
		{
		document.getElementById('bukan_kaveat').style.display="none";
		}
		if(document.getElementById('bukan_alamatkaveat') != null)
		{
		document.getElementById('bukan_alamatkaveat').style.display="none";	
		}
	    </script>
        #set($checkJ4="checked")  
        #end
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td scope="row">&nbsp;</td>
        <td colspan="2"><label>
          <input type="radio" name="sorPenentuanBidangKuasa" id="sorPenentuanBidangKuasa" value="53" $check3 $setMode4 onClick="selectRadio4()" />
          <input name="setMode4" id="setMode4" type="hidden" value="$setMode4" >
        Permohonan diteruskan</label></td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td scope="row">&nbsp;</td>
        <td colspan="2">
       
       	<!-- razman add $setMode5 -->
        <input type="radio" name="sorPenentuanBidangKuasa" id="sorPenentuanBidangKuasa" value="50" $check4  $setMode5 onClick="selectRadio5();setTableB('tableReportA','tableReportX')"/>
        
        <input name="setMode5" id="setMode5" type="hidden" value="$setMode5" >
Pindah ke Mahkamah Tinggi 
<!-- razman add comment(Jumlah Harta &gt; RM 2 juta ) -->
</td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td scope="row">&nbsp;</td>
        <td colspan="2">
        






<div id="tableReportX"  style="display:none;"  > 

        <table width="70%">
  <tr>
    <td>
   
   <fieldset  ><legend>MAKLUMAT MAHKAMAH</legend>
<table width="100%">
  <tbody>
  
  <!-- razman add open -->
   <tr>
      <td valign="top"><span class="style7"> #if($setMode != "disabled") * #end</span></td>
      <td width="29%"><div align="right" class="style8">
        <div align="left">#if($setMode != "disabled") Tujuan Pindah Mahkamah #else
          Tujuan Pindah Mahkamah
          #end</div>
      </div></td>
      <td width="1%">:</td>
      <td width="70%">
       #if($setMode == "disabled")
       
       <!-- arief add 5 JUTA -->
       #if($nilaiHartaMaximum == 5000000)
			#if($tujuanPindah == "2")
       			Wasiat
       		#else
       			Jumlah Harta > RM 5,000,000
       		#end
		#else
			#if($tujuanPindah == "2")
       			Wasiat
       		#else
       			Jumlah Harta > RM 2,000,000
       		#end
		#end
       
       
       
       <input  type="hidden" name="tujuanPindah" id="tujuanPindah" value="$tujuanPindah" >
       
       #else
       
      <!--  :::::::: $Overalldum  -->
       
       #set($selectedTujuan1 = "")
       #set($selectedTujuan2 = "")
       
       #set($setModeTujuan1 = "")
       #set($setModeTujuan2 = "")
       
       
       #if($Overalldum >= $nilaiHartaMaximum)        
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
       	 #if($Overalldum < $nilaiHartaMaximum)
             #set($selectedTujuan1 = "")
             #set($selectedTujuan2 = "checked")
         #else
             #set($selectedTujuan1 = "checked")
             #set($selectedTujuan2 = "")
         #end
       #end
       
    <!-- arief add 5 JUTA -->
    #if($nilaiHartaMaximum == 5000000)
		<input type="radio" name="tujuanPindah" id="tujuanPindah" $selectedTujuan1 $setModeTujuan1 value="1"   />     
        Harta Melebihi 5 Juta
      <input type="radio" name="tujuanPindah" id="tujuanPindah" $selectedTujuan2 $setModeTujuan2 value="2"  />
        Wasiat
	#else
		<input type="radio" name="tujuanPindah" id="tujuanPindah" $selectedTujuan1 $setModeTujuan1 value="1"   />     
        Harta Melebihi 2 Juta
      <input type="radio" name="tujuanPindah" id="tujuanPindah" $selectedTujuan2 $setModeTujuan2 value="2"  />
        Wasiat
	#end
      
      
      
      #end
      </td>
      </tr>
   <!-- razman add close -->
  
    <tr>
      <td valign="top"><span class="style7"> #if($setMode != "disabled") * #end</span></td>
      <td width="29%"><div align="right" class="style8">
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
       <td valign="top"><span class="style7"> #if($setMode != "disabled") * #end</span></td>
      <td><div align="right" class="style8">
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
      <td><div align="right" class="style8">
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
      <td><div align="right" class="style8">
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
      <td><div align="right" class="style8">
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
      <td><div align="right" class="style8">
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
      <td><div align="right" class="style8">
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
</div>        </td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td scope="row">&nbsp;</td>
        <td colspan="2"><input type="radio" name="sorPenentuanBidangKuasa" id="sorPenentuanBidangKuasa" $check5 value="70" $setMode6 onClick="selectRadio6();setTableB_TUTUP()" />
        <input name="setMode6" id="setMode6" type="hidden" value="$setMode6" >
Batal Permohonan (Lain - lain kes)</td>
      </tr>
      <tr>
        <td valign="top" align="right"><b>Catatan</b></td>
        <td valign="top" align="right"><b>: </b></td>
        <td colspan="2"><label>
          <textarea name="txtCatatan" id="textarea" cols="100" rows="5" $setModeR class="$setMode" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" >$catatan.toUpperCase()</textarea>
        </label></td>
      </tr>
    </table>
    </fieldset>
    
    <!-- comment dulu untuk POC
    <fieldset>
    <legend>SUMMARY DISTRIBUTION </legend>
    <table width="100%" border="0">
    <tr>
    <td width="15%" align ="right" scope="col"><b>Jenis Summary Distribution</b></td>
        <td width="1%" scope="col">:</td>
        <td width="84%" colspan="2" scope="col">
        <input type="radio" name="sorPenentuanBidangKuasa" id="sorPenentuanBidangKuasa" value="107" $check107  $setMode4  />Nilai Harta Alih Bernilai RM 600,000 ke bawah
        
        </td>
    </tr>
    <tr>
    <td width="15%" align ="right" scope="col"><b>Catatan</b></td>
        <td width="1%" scope="col">:</td>
        <td width="84%" colspan="2" scope="col">
         <textarea name="txtCatatanSD" $setModeR class="$setMode" id="txtCatatanSD" cols="100" rows="5">$!catatan_sd.toUpperCase()</textarea>
        
        </td>
    </tr>
    </table>
    </fieldset> -->
    
    <fieldset>
    <legend>DOKUMEN SOKONGAN</legend>
    <table width="60%" border="0">
    <tr>
     <td width="25%" align ="right" scope="col"><b>Dokumen Sokongan</b></td>
        <td width="1%" scope="col">:</td>
        <td width="74%" colspan="2" scope="col">
         <input type="text" disabled value=$!namaDoC>&nbsp;
         #if ($namaDoC != '')
         <input type="button" name="cmdUpload" disabled id="cmdUpload" value="Muat naik Dokumen" onclick="uploadSuppDoc('$idPermohonan','$idSimati')"/>&nbsp;
         #else
         <input type="button" name="cmdUpload" id="cmdUpload" value="Muat naik Dokumen" onclick="uploadSuppDoc('$idPermohonan','$idSimati')"/>&nbsp;
         #end
         #if ($namaDoC != '')
         <input name="cetak" type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />&nbsp;
         #end
         
         <!-- <input name="cetak" disabled type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />&nbsp;  -->
         
        
   		 
   		 #if ($namaDoC != '')
   		 <input name="deleteSuppDoc1" type="button" value="Padam Dokumen" onclick="deleteSuppDoc()" />
   		 #end
   		 
   		 <!-- <input name="deleteSuppDoc1" disabled type="button" value="Padam Dokumen" onclick="deleteSuppDoc()" />  -->
   		 
   		 
       
        </td>
    </tr>
    <tr>
    
    </tr>
    
    </table>
    
    </fieldset>
    </td>
  </tr>
</table>
         #if($setMode != "disabled") 
                    <table width="100%">
  <tr>
    <td><div align="left"><span class="style3">Perhatian</span><span class="style4"> : Sila pilih salah satu penentuan bidang kuasa dan pastikan label bertanda <span class="style5">*</span> diisi.</span></div></td>
  </tr>
</table>
#end

<table width="100%" border="0">

	<tr>
    	<td align="center">
#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
    <input type="button" name="semakBrgC" id="cmdSemakBrgC" value="Semak Borang C" onClick="semakBorangC('$noFail')">
   
   
    <input type="button" name="cmdBorangI" id="cmdBorangI" value="Hantar ke Mahkamah Tinggi (Borang I)" onClick="semakMTBorangI()"/>
    <!-- <input type="button" name="cmdSemakMT" id="cmdSemakMT" value="Hantar ke Mahkamah Tinggi (Borang B)" onClick="semakMTPermohonan()"/> -->
    #if ($EventStatus == 1 )    
    
	    #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
		<!--
	      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="getKemaskini()"/>
	   	-->   
	    #end
    
	    #if($boleh_kemaskini == "yes")
        #end
	    	<input type="button" name="cmdKemaskini1" id="cmdKemaskini1" value="Kemaskini" onClick="getKemaskini()"/>
             #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('cmdKemaskini1').style.display = "none";
                                </script>
                                #end 
	    
    
	    #if($id_Status == "50" || $id_Status == "53")
	      <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport')" />
	    #end
    
		## Rosli comment on 23/02/2011
      	## Tambahan paparan [Cetak] bagi status 18,21,25,41
     	##if($id_Status == "152" || $id_Status == "151" ) 
     	#if($id_Status == "152" || $id_Status == "151" || $id_Status == "18" || $id_Status == "21"|| $id_Status == "25"|| $id_Status == "41")
     
      		#if($id_Status == "151" && $jenis_pej == "2")
      
      		#else     
  			<!--    
  			<input type="button" name="button" id="button" value="Cetak" onClick="javascript:cetakSuratBatalPermohonan('$noFail')" /> 
  			-->
      			<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTableAwal('tableReportAwal')" />
      
      		#end
         
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
    <!-- Hilangkan button di bawah ini sebagaimana mengikut kehendak user
		<input type="submit" name="button3" id="button3" value="Pendaftaran Permohonan"  onClick="javascript:keNilaian_Harta('$jenis_permohonan','$idPermohonan','$idPemohon','$idSimati','$idpermohonan_simati')" />
     -->
    	<input type="hidden" name="id_permohonan"  value="$idPermohonan"/>   
		<input name="id_status" type="hidden"  value="$id_Status"/>  
    
    #if ($EventStatus != 0)
    	#if ($id_Status == 70)
    	<input type="button" name="cmdKembali" id="cmdKembali" value="Cetak" onClick="javascript:cetakSuratBatalPermohonan_LK('$noFail')"/>
    	#end
    #end
	
	<!-- Sebelum ini sekiranya id_Status = 151, button seterusnya tiada. Sekarang ini diadakan button tersebut sebagaimana kehendak user -->
    #if ($EventStatus != 0 && ($id_Status == "151" || $id_Status == "44" || $id_Status == "173" || $id_Status == "175" || $id_Status == "177" || $id_Status != "151" || $id_Status == "18" || $id_Status == "107"))        
    	
    	#if ($mohonawal != "")
    		
    		#if (($salinan_arahan=="1") && ($jenisborangC=="K"))
    			<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onClick="kenotis('$seksyen','$id_Status')"/>
    		#end
    	#else
    		#if( ($id_Status != "50") && ($id_Status != "107"))
    		
    		<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onClick="kenotis('$seksyen','$id_Status')"/>
    		
    		#end
    	#end
    #end
    
    #else
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:history.back()"/>
    <!-- Hilangkan button di bawah ini sebagaimana mengikut kehendak user
    <input type="submit" name="button3" id="button3" value="Pendaftaran Permohonan"  onClick="javascript:keNilaian_Harta('$jenis_permohonan','$idPermohonan','$idPemohon','$idSimati','$idpermohonan_simati')" />
    -->
    #end
    </td>
  </tr>
	<input type="hidden" name="command"/>
    <input type="hidden" name="mode"/>    
	<input type="hidden" name="idPermohonan"  value="$idPermohonan"/>
    <input type="hidden" name="idPemohon" />
    <input type="hidden" name="idSimati" value="$idSimati"/>
    <input type="hidden" name="id_Permohonansimati" value="$idpermohonan_simati"/>
   
   <!--
     document.f1.command.value = "nilai_harta";		
	document.f1.mode.value = "view_nilai_harta";	
	document.f1.idPermohonan.value = idPermohonan;
	document.f1.idPemohon.value = idPemohon;
	document.f1.idSimati.value = idSimati;
	document.f1.id_Permohonansimati.value = id_Permohonansimati;
	-->
	<input type="hidden" name="idKeputusanPermohonan" />
</table>
</fieldset>



<fieldset id="tableReportAwal" style="display:none;">
	<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    ##if($jenis_pej == "9")
    #if($jenis_pej.equals("9"))
      <tr>
        <td ><a href="#" class="style2" onClick="cetakSuratBatalPermohonan_ARB('$noFail')">Surat Arahan/Akuan oleh Amanah Raya Berhad</a></td>
      </tr>
      #end
    ##if($jenis_pej == "8")
    #if($jenis_pej.equals("8"))
      <tr>
        <td ><a href="#" class="style2" onClick="cetakSuratBatalPermohonanMT('$noFail')">Surat Batal Permohonan - MT</a></td>
      </tr>
     #end 
	##if($jenis_pej == "2")
  	#if($jenis_pej.equals("2"))
       <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakSuratBatalPermohonan('$noFail')">Surat Batal Permohonan - PTD</a></td>
      </tr>
      #end
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakSuratKaveat('$noFail')">Surat Kaveat</a></td>
      </tr>
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


        
        #if($keputusan == "151")
        <script>
		
			document.getElementById('ptd1').style.display="none";
			document.getElementById('ptd2').style.display="none";
			document.getElementById('ptd3').style.display="none";
			document.getElementById('mah1').style.display="none";
			document.getElementById('mah2').style.display="none";
			document.getElementById('mah3').style.display="none";
	//	alert("KKK")
		</script>
        
        #else
         <script>
			document.getElementById('ptd1').style.display="";
			document.getElementById('ptd2').style.display="";
			document.getElementById('ptd3').style.display="";
			document.getElementById('mah1').style.display="";
			document.getElementById('mah2').style.display="";
			document.getElementById('mah3').style.display="";
		</script>
        
        #end

<input type="hidden" name="flag_KP" id="flag_KP" value="yes" />
<input type="hidden" name="kep_brgC" value="$kep_BorangC">
<input type="hidden" name="res_brgC" value="$result_BorangC">
<input type="hidden" name="sec_codeLink" value="$security_Code_Link">
</form>
</body>
<p align="right">CL - 01 - 84</p>
<script>
checkDateKP();

function checkDateKP() {

	
	var d1 = document.f1.txdTarikhHantarBorangB;
	var d3 = document.f1.txdTarikhHantarNilaian;
	
	var cmdSeterusnya = document.f1.cmdSeterusnya;		
	
    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
    var str1  = document.getElementById("txdTarikhHantarBorangB").value;    
    var str3  = document.getElementById("txdTarikhHantarNilaian").value;
    var str5  = document.getElementById("tarikh_permohonan").value;  
	
	if(str1!="")
	{  
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
	var date1 = new Date(yr1, mon1, dt1);//txdTarikhHantarBorangB
	}
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
  
    if(str1=="" || str3=="")
	{
	 
	
		if(testIsValidObjectKP(cmdSeterusnya)==false)
		{
		string_field_temp = string_field_temp + "<input type='hidden' id='cmdSeterusnya' name='cmdSeterusnya'/>";
		}
		else
		{
		string_field_temp = string_field_temp + "";
		}	    
		
		
		
		$jquery("#alert_field").html("<font color = 'red'>Fail Permohonan belum cukup tempoh 30 hari daripada tarikh hantar borang B dan proses permohonan tidak dapat diteruskan. Sila pastikan maklumat <b>Tarikh Hantar Borang B</b> dan <b>Tarikh Hantar Nilaian</b> di isi.</font>"+string_field_temp);	
		//alert("1");
		//alert("2"+document.getElementById("cmdSeterusnya"));
//		document.getElementById('cmdSimpan').style.display="none";
//		document.getElementById('cmdSeterusnya').style.display="none";
		document.getElementById("cmdSeterusnya").disabled = true;
		
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


function uploadSuppDoc(id,IdSimati)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppk.SkrinPopupUploadDokumen?&id_Permohonan="+id+"&IdSimati="+IdSimati+"&id_jenisDoc=99205";
	var hWnd = window.open(url,'printuser','width=350,height=200, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
	
	
}

function getKemaskini() {
	document.f1.method="post";
	document.f1.command.value="getKemaskini_keputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
}
function getKemaskinibatal() {
input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method="post";
	document.f1.command.value="getKemaskini_keputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
	}else
	{return;}
}
function getBatal() {
	document.f1.method="post";
	document.f1.command.value="getBack_keputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
}


function semakMTPermohonan() {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=borangPermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function semakMTBorangI() { 
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=hantarBorangI&dari=keputusanpermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=500, resizable=yes,scrollbars=no');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function tambahKaveat(idPermohonan) {
	
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmTambahKaveat?idFail=$idFail&command=tambahKaveat&dari=keputusanpermohonan&idpermohonan="+idPermohonan;
	var hWnd = window.open(url,'Cetak','width=650,height=320, resizable=yes,scrollbars=no');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hantarBorangI() {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=borangI";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function semakBorangC(x) {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmMTBorangC?noFail="+x+"&command=borangPermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function muatTurunBorangC() {
    var url = "http://ettap.kehakiman.gov.my/BKM/bkm_frmSearchProbatePublic.aspx?SecurityCode=$!security_Code_Link";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function getSimpan() {

//arief add 5 Juta
var check5juta = document.f1.txdflag5juta;
var nilaimax = document.f1.nilaiHartaMaximum;

if (check5juta == "T"){
	nilaimax = 5000000;
}
else{
	nilaimax = 2000000;
}

var check_batal = document.f1.sorPenentuanBidangKuasa[3].checked;

//alert(" check_batal :"+check_batal);

var d1 = document.f1.txdTarikhHantarBorangB;
var d2 = document.f1.txdTarikhTerimaBorangC;
var d3 = document.f1.txdTarikhHantarNilaian;
var d4 = document.f1.txdTarikhTerimaNilaian;


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
    
	//Tambah ARB sebagai OB. Start.
	
    if (document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  != true )
    	{
    	
    	document.f1.ARB.value="YES"
    	}
   
	//Tambah ARB sebagai OB. End.
	
       if(document.f1.sorPenentuanBidangKuasa[0].checked != true && document.f1.sorPenentuanBidangKuasa[1].checked != true && document.f1.sorPenentuanBidangKuasa[2].checked != true && document.f1.sorPenentuanBidangKuasa[3].checked != true && document.f1.sorPenentuanBidangKuasa[4].checked != true )
	   {
	   alert("Sila pilih penentuan bidang kuasa");
	   return
	   }	   
	    else if (check_batal != true && str1 == ""){
		alert("Sila pastikan tarikh borang B diisi.");		
		txdTarikhHantarBorangB.focus();
	    }	   
	   else if (check_batal != true && str3 == ""){
		alert("Sila pastikan tarikh hantar nilaian diisi.");		
		txdTarikhHantarNilaian.focus();
	    }
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


    else if (str1 != "" &&  date1 > currentTime){
		alert("Sila pastikan tarikh borang B dihantar tidak melebihi dari tarikh hari ini.");
		//document.f1.txdTarikhHantarBorangB.value="";		
		txdTarikhHantarBorangB.focus();
	}
	else if (str2 != "" && date2  > currentTime){
		alert("Sila pastikan tarikh borang C diterima tidak melebihi dari tarikh hari ini.");
	//	document.f1.txdTarikhTerimaBorangC.value="";		
		txdTarikhTerimaBorangC.focus();
	}
	
	
	 else if (str3 != "" && date3 > currentTime){
		alert("Sila pastikan tarikh harta dihantar tidak melebihi dari tarikh hari ini.");
		//document.f1.txdTarikhHantarBorangB.value="";		
		txdTarikhHantarNilaian.focus();
	}
	else if (str4 != "" && date4 > currentTime){
		alert("Sila pastikan tarikh harta diterima tidak melebihi dari tarikh hari ini.");
		//document.f1.txdTarikhTerimaBorangC.value="";		
		txdTarikhTerimaNilaian.focus();
	}
	
	 else if (str1 != "" && date1 < date5){
		alert("Sila pastikan tarikh borang B dihantar melebihi dari tarikh permohonan.");
		//document.f1.txdTarikhHantarBorangB.value="";		
		txdTarikhHantarBorangB.focus();
	}
	else if (str2 != "" &&  date2  < date5){
		alert("Sila pastikan tarikh borang C diterima melebihi dari tarikh permohonan.");
	//	document.f1.txdTarikhTerimaBorangC.value="";		
		txdTarikhTerimaBorangC.focus();
	}
	
	//txdTarikhHantarNilaian
//txdTarikhTerimaNilaian
	 else if (str3 != "" && date3 < date5){
		alert("Sila pastikan tarikh harta dihantar melebihi dari tarikh permohonan.");
		//document.f1.txdTarikhHantarBorangB.value="";		
		txdTarikhHantarNilaian.focus();
	}
	else if (str4 != "" && date4 < date5){
		alert("Sila pastikan tarikh harta diterima melebihi dari tarikh permohonan.");
		//document.f1.txdTarikhTerimaBorangC.value="";		
		txdTarikhTerimaNilaian.focus();
	}
	
	
   else if(str2 != "" && str1 != "" && date2 < date1)
   {
      alert("Sila pastikan tarikh hantar borang B tidak melebihi dari tarikh terima borang C.");
	 // document.f1.txdTarikhHantarBorangB.value="";	
	 // document.f1.txdTarikhTerimaBorangC.value="";		
      document.f1.txdTarikhHantarBorangB.focus();
   } 
   
    else if(str4 != "" && str3 != "" && date4 < date3)
   {
      alert("Sila pastikan tarikh hantar harta tidak melebihi dari tarikh terima harta.");
	 // document.f1.txdTarikhHantarBorangB.value="";	
	 // document.f1.txdTarikhTerimaBorangC.value="";		
      document.f1.txdTarikhHantarNilaian.focus();
   }
   
    
   
   else if(document.f1.nofailawal.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  != true )
   {
    alert("Sila pastikan no fail pemohonan awal diisi" );		
    document.f1.nofailawal.focus();
   
   }
   else if(document.f1.namapemohonawal.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  != true  )
   {
    alert("Sila pastikan nama pemohon awal diisi");	 	
    document.f1.namapemohonawal.focus();
   
   }
   else if((document.f1.tempatmohonawal.value == "" || document.f1.tempatmohonawal.value == "0") && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  != true  )
   {
    alert("Sila pastikan tempat permohonan awal diisi");	 	
    document.f1.tempatmohonawal.focus();
   
   }
  
  else if((document.f1.socNegeri.value == "" || document.f1.socNegeri.value == "0") && document.f1.sorPenentuanBidangKuasa[2].checked == true )
   {
    alert("Sila pilih negeri mahkamah");	 	
    document.f1.socNegeri.focus();
   
   }
  
  
  else if((document.f1.socDaerah.value == "" || document.f1.socDaerah.value == "0") && document.f1.sorPenentuanBidangKuasa[2].checked == true )
   {
    alert("Sila pilih daerah mahkamah");	 	
    document.f1.socDaerah.focus();
   
   }
   
   
   
   else if(document.f1.txtNamaKaveat.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan nama pengkaveat diisi" );		
    document.f1.txtNamaKaveat.focus();   
   }
   else if(document.f1.txtNoKaveat.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan no kaveat diisi" );		
    document.f1.txtNoKaveat.focus();   
   }
   else if(document.f1.txtNamaFirma.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan nama firma diisi" );		
    document.f1.txtNamaFirma.focus();   
   }
   else if(document.f1.txtAlamat1Peguam.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan alamat peguam diisi" );		
    document.f1.txtAlamat1Peguam.focus();   
   }
   else if(document.f1.txtPoskodPeguam.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan poskod alamat peguam diisi" );		
    document.f1.txtPoskodPeguam.focus();   
   }
   else if(document.f1.socNegeriPeguam.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan negeri alamat peguam dipilih" );		
    document.f1.socNegeriPeguam.focus();   
   }
  
   
   
   else{

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method="post";
	document.f1.command.value="getSimpan_keputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
	}else
	{	
	return;
	}
	}
	checkDateKP();
}
function getMahkamah(idPermohonan,command) {
	var url = "../x/${securityToken}/FrmSenaraiFailKeputusanPermohonanInternal?idPermohonan="+idPermohonan+"&command="+command;
	var hWnd = window.open(url,'printuser','width=600,height=600, resizable=yes,scrollbars=yes');
	document.f1.sorPenentuanBidangKuasaTeruskan[0].checked == false;
}
function selectRadio1() {

if(document.f1.sorPenentuanBidangKuasa[0].checked == true)
{

if(document.f1.jumlah.value >= nilaimax)
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

//ubah2

// alert("1"+document.f1.sorPenentuanBidangKuasaTeruskan[0].checked)
//alert("2"+document.f1.sorPenentuanBidangKuasaTeruskan[1].checked)


if(document.f1.sorPenentuanBidangKuasaTeruskan[0].checked == true)
{
document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.getElementById('tableReportX').style.display="none"; //razman add
document.getElementById('kv1').style.display="block";
document.getElementById('kv2').style.display="block";
//document.getElementById('kaveat').style.display="block";
//alert("test1")
}
}

function selectRadio3() {

if(document.f1.sorPenentuanBidangKuasaTeruskan[1].checked == true)
{
document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;

document.getElementById('tableReportX').style.display="none";//razman add

}

}

function nktambah() {
	document.getElementById('kaveattambah').style.display="block";
	
}


function selectRadio4() {
if(document.f1.sorPenentuanBidangKuasa[1].checked == true)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;

document.f1.sorPenentuanBidangKuasa[0].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.getElementById('tableReportX').style.display="none"; //razman add
}

}


function selectRadio5() {

	if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
	{
		document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
		document.f1.sorPenentuanBidangKuasa[0].checked = false;
		document.f1.sorPenentuanBidangKuasa[1].checked = false;
		document.f1.sorPenentuanBidangKuasa[3].checked = false;
		document.getElementById('tableReportX').style.display="block"; //razman edit add block
		//alert("1");
	}

}

function selectRadio6() {

if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;

document.f1.sorPenentuanBidangKuasa[0].checked = false;

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.getElementById('tableReportX').style.display="none";//razman add
}


document.f1.jenis_pej.value = "";
document.f1.jenis_pej_id.value = "";


document.f1.tempatmohonawal.value = "";



}

function putih(){

if(document.f1.jumlah.value >= nilaimax)
{
document.f1.sorKeputusanBorangC[0].checked = true;
document.f1.sorKeputusanBorangC[1].checked = false;


document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = false;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = true;
document.f1.sorPenentuanBidangKuasa[3].checked = false;

document.f1.sorPenentuanBidangKuasa[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = false;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;

document.f1.setMode1.value = "disabled";
document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "disabled";
document.f1.setMode4.value = "disabled";
document.f1.setMode5.value = "";
document.f1.setMode6.value = "";


}
else
{

if('$!headerppk.TOTAL_HTA' == 0 && '$!headerppk.TOTAL_HA' == 0)
{ 
document.f1.sorKeputusanBorangC[0].checked = true;
document.f1.sorKeputusanBorangC[1].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = false;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = true;
document.f1.sorPenentuanBidangKuasa[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = true;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;
document.f1.setMode1.value = "disabled";
document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "disabled";
document.f1.setMode4.value = "disabled";
document.f1.setMode5.value = "disabled";
document.f1.setMode6.value = "";
}
else
{
document.f1.sorKeputusanBorangC[0].checked = true;
document.f1.sorKeputusanBorangC[1].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = false;
document.f1.sorPenentuanBidangKuasa[1].checked = true;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.f1.sorPenentuanBidangKuasa[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[1].disabled = false;
document.f1.sorPenentuanBidangKuasa[2].disabled = false; // razman add false
document.f1.sorPenentuanBidangKuasa[3].disabled = false;
document.f1.setMode1.value = "disabled";
document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "disabled";
document.f1.setMode4.value = "";
document.f1.setMode5.value = "";  // razman buang disable
document.f1.setMode6.value = "";
}



}



}

function kuning(){
if(document.f1.id_taraf_mohon.value != "")
{var idm = document.f1.id_taraf_mohon.value;}
else
{idm="";}

//alert("::::: IDM"+idm);

if(document.f1.jumlah.value >= nilaimax)
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


document.f1.sorPenentuanBidangKuasa[0].disabled = false;


//document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
//document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
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
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = true;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;

document.f1.setMode1.value = "";
//document.f1.setMode2.value = "disabled";
if(idm == 6)
{
document.f1.setMode2.value = "";
}
else
{
document.f1.setMode2.value = "disabled";
}
document.f1.setMode3.value = "";
document.f1.setMode4.value = "disabled";
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


document.f1.sorPenentuanBidangKuasa[0].disabled = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = true;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;

document.f1.setMode1.value = "";
document.f1.setMode2.value = "";
document.f1.setMode3.value = "";
document.f1.setMode4.value = "disabled";
document.f1.setMode5.value = "disabled";
document.f1.setMode6.value = "";

//document.f1.txtCatatan.value = "";

}

/*
if(document.f1.jumlah.value > nilaimax)
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
	document.f1.command.value = "paparKeputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	document.f1.submit();
	}
	else
	{
	return;
	}
}

function doOpen(id) {
	//alert('id : '+id);
    var url = "../servlet/ekptg.view.ppk.DisplayBuktiKematian?id="+id+"&jenisDoc=99205";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteSuppDoc()
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.method = "POST";
	document.f1.command.value = "deleteSuppDocMode";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
	}
	else
		{
		return
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



function cetakSuratBatalPermohonan(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonan&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratKaveat(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratKaveat&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratPindahMT(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratPindahMT&flagReport=S";
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

	function buktiPenyampaian(noFail,idfail){	
	    var url = "../servlet/ekptg.report.ppk.BuktiPenyampaianMT?nofail="+noFail+"&idfail="+idfail+"&template=BuktiPenyampaianMT";  
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
	
function setTableAwal(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


function setTableA(id,loc,tab){
//ubah keputusan
//alert("1"+document.f1.sorPenentuanBidangKuasaTeruskan[0].checked)
//alert("2"+document.f1.sorPenentuanBidangKuasaTeruskan[1].checked)
//alert("3"+document.f1.sorKeputusanBorangC[1].checked+""+id)

var jum = document.f1.jumlah.value;
//alert(jum)
//document.f1.setMode1.value = "checked";
if(document.f1.sorKeputusanBorangC[1].checked == true && document.getElementById(id).style.display=="none" )
{

    document.f1.v_loc.value = loc;
	document.f1.v_tab.value = tab;	
	document.f1.setMode1.value = "checked";
		document.getElementById(id).style.display="block";

if(jum >= nilaimax)
{
document.getElementById('tableReportX').style.display="none";
		document.f1.radio_j[2].checked = true;
		document.f1.jenis_pej.value = 8;
		
		document.f1.checkJ1.value = "";
		document.f1.checkJ2.value = "";
		document.f1.checkJ4.value = "";
		document.f1.checkJ3.value = "checked";
}
else{
		document.getElementById('tableReportX').style.display="none";
		document.f1.radio_j[0].checked = true;
		document.f1.jenis_pej.value = 9;
		
		document.f1.checkJ1.value = "checked";
		document.f1.checkJ2.value = "";
		document.f1.checkJ3.value = "";
		document.f1.checkJ4.value = "";
}
		
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value  = "";
			document.f1.viewcheckJ3.value  = "";
			document.f1.viewcheckJ4.value  = "";
			
	
	
		
		
	
document.f1.submit();

/*
if(document.f1.sorPenentuanBidangKuasaTeruskan[1].checked == true)
{

			document.f1.setMode3.value = "checked";
			document.f1.setMode2.value = "";	
				
			document.f1.v_loc.value = loc;
			document.f1.v_tab.value = tab;	
	
			document.getElementById(id).style.display="block";
			document.getElementById('tableReportX').style.display="none";
			document.f1.radio_j[2].checked = true;
			document.f1.jenis_pej.value = 8;
			
			document.f1.checkJ1.value = "";
			document.f1.checkJ2.value = "";
			document.f1.checkJ3.value = "checked";
			document.f1.command.value = "get_jenisPejabat";
			document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value = "";
			document.f1.viewcheckJ3.value = "";				
					
}	
*/
	
}
}




/*
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
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";		
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

*/





//ubah
//baru ni
function setTableA_X1(id,loc,tab){

document.f1.setMode1.value = "checked";
if(document.f1.sorKeputusanBorangC[1].checked == true )
{

//alert(document.f1.v_loc.value)
	document.f1.v_loc.value = loc;
	document.f1.v_tab.value = tab;
	
			document.f1.setMode2.value = "checked";
			document.f1.setMode3.value = "";	
		
			document.getElementById(id).style.display="block";
			document.getElementById('tableReportX').style.display="none";
			document.f1.radio_j[0].checked = true;
			document.f1.jenis_pej.value = 9;
			
			document.f1.checkJ1.value = "checked";
			document.f1.checkJ2.value = "";
			document.f1.checkJ3.value = "";
			document.f1.checkJ4.value = "";
			
			document.f1.command.value = "get_jenisPejabat";
			document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			document.f1.radio_j[3].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value = "";
			document.f1.viewcheckJ3.value = "";	
		//	document.f1.viewcheckJ4.value = "";	
}

if(document.f1.sorPenentuanBidangKuasaTeruskan[1].checked == true)
{

			document.f1.setMode3.value = "checked";
			document.f1.setMode2.value = "";	
				
			document.f1.v_loc.value = loc;
			document.f1.v_tab.value = tab;	
	
			document.getElementById(id).style.display="block";
			document.getElementById('tableReportX').style.display="none";
			document.f1.radio_j[2].checked = true;
			document.f1.jenis_pej.value = 8;
			
			document.f1.checkJ1.value = "";
			document.f1.checkJ2.value = "";
			document.f1.checkJ3.value = "checked";
			document.f1.checkJ4.value = "";
			document.f1.command.value = "get_jenisPejabat";
			document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			document.f1.radio_j[3].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value = "";
			document.f1.viewcheckJ3.value = "";	
			document.f1.viewcheckJ4.value = "";				
					
}	

document.f1.submit();	

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
		document.f1.checkJ4.value = "";
		
		
		
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
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
		document.f1.checkJ4.value = "";
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
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
		document.f1.checkJ2.value = "";
		document.f1.checkJ4.value = "";
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	   document.f1.submit();
	
}

}






function setTableA_J(id,idx){

//alert(document.f1.sorPenentuanBidangKuasa[3].checked)
if(document.f1.sorKeputusanBorangC[1].checked == true && document.getElementById(id).style.display=="none" )
{
//ubah3	

document.getElementById(id).style.display="block";


if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
{
document.getElementById(id).style.display="none";
}
	
}
else if(document.f1.sorKeputusanBorangC[0].checked == true && document.getElementById(idx).style.display=="none" )
{
       //razman add open
	  // alert('x');
	   if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
	   {
		  document.getElementById(idx).style.display="block";	
	   }
	   else
	   {
		  document.getElementById(idx).style.display="none";
	   }
	   //razman add close
	   
	  
	    if(document.f1.jumlah.value >= nilaimax)
		{
		 	document.getElementById(idx).style.display="block";		
			if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
			{
			 document.getElementById(idx).style.display="none";
			}
			document.getElementById(id).style.display="none";
		}
		
		
		if(document.f1.jumlah.value < nilaimax)
		{
	
		//document.getElementById(idx).style.display="none"; //razman comment
		//document.getElementById(id).style.display="none"; //razman comment
		
		}
		
		
}


}
function setTableB_TUTUP()
{
document.getElementById('tableReportA').style.display="none";
document.getElementById('tableReportX').style.display="none";


}

function setTableB(id,idx){
if(document.getElementById(id).style.display=="block" )
{
	
		document.getElementById(id).style.display="none";
	
}

//razman add open
if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
{
  document.getElementById(idx).style.display="block";	
}
else
{
  document.getElementById(idx).style.display="none";
}
//razman add close


if(document.f1.jumlah.value >= nilaimax)
{
	//alert('lebih');
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
	if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
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

}

function alamatP(val,loc,tab)
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.f1.command.value = "get_alamatPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
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


function getDaerah(val,loc,tab){
document.f1.v_loc.value=loc;
document.f1.v_tab.value=tab;

	document.f1.command.value="getMahkamah";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();	
}
function getAddress(loc,tab){
document.f1.v_loc.value=loc;
document.f1.v_tab.value=tab;

	document.f1.command.value="getMahkamahAddress";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();	
}
/*
function submitForm() {    
   // document.val.focus();
	
	window.location.hash='$val_loc';
	
	goTo('$val_tab');

	return false;
	
	
} 
*/

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




function edit_item(){

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {

	document.f1.method="post";
	document.f1.command.value = "paparKeputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
//	document.f1.idPermohonan.value = id;
//	document.f1.idpermohonansimati.value = sm;
//	document.f1.tarikh_mohon.value = tm;
	document.f1.submit();
	}
	else
	{return;}
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

function cetakSuratBatalPermohonanMT(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonan_MT&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratBatalPermohonan_LK(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonanLainKes&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}



function cetakSuratBatalPermohonan_ARB(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratArahanAkuanARB&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function pilih_pilih()
{
if((document.f1.tempatmohonawal.value == "" || document.f1.tempatmohonawal.value == "0") && (document.f1.socNegeriWarisSurat != null && (document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0" )))
{
alert("Sila pilih negeri terlebih dahulu")
document.f1.socNegeriWarisSurat.focus() 
}
}

function setTableA_JENISB_NEG(id,val,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true && document.f1.socNegeriWarisSurat.value != "" )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.f1.radio_j[1].checked = true;
		document.f1.jenis_pej.value = val;
		document.f1.checkJ2.value = "checked";
		document.f1.checkJ1.value = "";
		document.f1.checkJ3.value = "";
		
		document.f1.command.value = "get_jenisPejabat_neg";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	
	document.f1.pilih_negeri_ptd.value = "yes";
	document.f1.submit();
	
}

}



function getBandarTetap(id,loc,tab){
//this.value,'txtNilaianHTA','tempatmohonawal'

if(document.f1.sorKeputusanBorangC[1].checked == true  )
{

document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
	 if(document.getElementById(id) != null)
	 {
		document.getElementById(id).style.display="block";
	}	
	    if(document.getElementById('kaveat') != null)
		{
		document.getElementById('kaveat').style.display="block";
		}
		
		if(document.getElementById('kv1') != null)
		{
		document.getElementById('kv1').style.display="block";
		}
		
		if(document.getElementById('kv2') != null)
		{
		document.getElementById('kv2').style.display="block";
		}
		
		if(document.getElementById('bukan_kaveat') != null)
		{
		document.getElementById('bukan_kaveat').style.display="none";
		}
		
		if(document.getElementById('bukan_alamatkaveat') != null)
		{
		document.getElementById('bukan_alamatkaveat').style.display="none";	
		}
		
		
		
	//	alert("asjsahja")

		
		document.f1.radio_j[3].checked = true;
		document.f1.jenis_pej.value = 99;
		document.f1.checkJ4.value = "checked";
		document.f1.checkJ3.value = "";
		document.f1.checkJ1.value = "";
		document.f1.checkJ2.value = "";
		
		document.f1.command.value = "get_Bandarbaru";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
//	document.f1.idPermohonan.value = id;
	   document.f1.submit();
	
}

}

function CheckBandarTetap()
{
if(document.f1.socNegeriPeguam.value == "" || document.f1.socNegeriPeguam.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPeguam.focus();
  return;
	  	
}


}


function setTableA_JENIS_KAV(id,val,loc,tab){


if(document.f1.sorKeputusanBorangC[1].checked == true  )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.getElementById('kaveat').style.display="block";
		document.getElementById('kv1').style.display="block";
		document.getElementById('kv2').style.display="block";
		document.getElementById('bukan_kaveat').style.display="none";
		if(document.getElementById('bukan_alamatkaveat') != null)
		{
		document.getElementById('bukan_alamatkaveat').style.display="none";	
		}
	//	alert("asjsahja")

		
		document.f1.radio_j[3].checked = true;
		document.f1.jenis_pej.value = val;
		document.f1.checkJ4.value = "checked";
		document.f1.checkJ3.value = "";
		document.f1.checkJ1.value = "";
		document.f1.checkJ2.value = "";
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	   document.f1.submit();
	
}

}

function checkTarikh(){
	
	if(document.f1.salinanArahan.checked == true)
		{
		
		document.getElementById('tarikhTerimaARB').style.display="block";
		document.getElementById('tarikhTerimaARB2').style.display="block";
		document.getElementById('tarikhTerimaARB3').style.display="block";
		document.getElementById('tarikhTerimaARB4').style.display="block";
		
		}
	else
		document.getElementById('tarikhTerimaARB').style.display="none";	
	document.getElementById('tarikhTerimaARB2').style.display="none";
	document.getElementById('tarikhTerimaARB3').style.display="none";
	document.getElementById('tarikhTerimaARB4').style.display="none";
	
}


function ForView(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.f1.submit();
}

function keNilaian_Harta(jenis_permohonan,idPermohonan,idPemohon,idSimati,id_Permohonansimati)
{
if(jenis_permohonan != 3)
{
document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar&idpermohonan="+idPermohonan+"&idPemohon="+idPemohon+"&idSimati="+idSimati+"&id_Permohonansimati="+id_Permohonansimati;	
}


if(jenis_permohonan == 3)
{
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar&idpermohonan="+idPermohonan+"&idPemohon="+idPemohon+"&idSimati="+idSimati+"&id_Permohonansimati="+id_Permohonansimati;	
}

document.f1.method="POST";
document.f1.submit();
}

function buttonkeHartaAlih(salinanArahan)
{
	
		var dvPassport = document.getElementById("divbuttonkeHartaAlih");
        dvPassport.style.display = salinanArahan.checked ? "block" : "none";

        
}

function jumptoHartaAlih(jenis_permohonan,idPermohonan,idPemohon,idSimati,id_Permohonansimati,negeriARB)
{
	document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&idpermohonan="+idPermohonan+"&idPemohon="+idPemohon+"&idSimati="+idSimati+"&id_Permohonansimati="+id_Permohonansimati+"&source=KeputusanPermohonan&negeriARB="+negeriARB+"&jenisHA=98";
	document.f1.command.value="harta_alih";
	document.f1.mode.value="view_harta_alih";
	document.f1.method="POST";
	document.f1.submit();
	/*
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnSek8Internal#maklumat_pemohon";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	*/
}
	
function returnFromMT(){
	document.f1.method="post";
	document.f1.command.value = "paparKeputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
}
</script>