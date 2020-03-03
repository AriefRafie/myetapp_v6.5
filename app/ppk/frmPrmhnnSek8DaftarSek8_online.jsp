
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--bubuh kat sini-->
<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {color: #0000FF}
.style3 {font-size: 9px}
.style36 {font-size: 12}
.style51 {color: #0000FF; font-size: 9px; }
-->
</style>


#if ($duplicate == "yes")

<script type="text/javascript">

		var simati = '$!POPSimati';
		var nofail = '$!POPNofail';
		var pejabat = '$!POPNamaPejabat';
		var daerah = '$!POPDaerahMohon';
		var nofailonline = '$!POPNoOnline';
		var id_status_daftar = '$!id_status_daftar';
		var selesai = "";
		
		if(id_status_daftar == "21")
		{
		selesai = "yes";
		}

		if(nofail != null && nofail != "" && id_status_daftar != "150" && id_status_daftar != "160" && id_status_daftar != "171" && selesai != "yes"){
		/*parent.document.getElementById("papar_daftar_xlepas").innerHTML="<div class=\"warning_online_ppk\"><b>Permohonan untuk simati "+simati+" telah wujud dengan No. fail Permohonan "+nofail+". Permohonan telah dibuat di "+pejabat+"("+daerah+"). Tuan/Puan tidak dapat meneruskan Permohonan Seksyen 8 Online kerana fail awal belum selesai sepenuhnya. Sila berhubung dengan Pejabat Pusaka berkenaan.</b></div>";*/
		/*
			alert("Permohonan untuk simati "+simati+" yang bernombor fail "+nofail+" sudah wujud!. Permohonan telah dibuat di "+pejabat+"("+daerah+"). Pihak Tuan/Puan tidak boleh meneruskan Permohonan Seksyen 8 Online. Sila berhubung dengan Pejabat Pusaka berkenaan. ");*/
			alert("Permohonan untuk simati "+simati+" telah wujud dengan No. fail Permohonan "+nofail+". Permohonan telah dibuat di "+pejabat+"("+daerah+"). Tuan/Puan tidak dapat meneruskan Permohonan Seksyen 8 Online kerana fail awal belum selesai sepenuhnya. Sila berhubung dengan Pejabat Pusaka berkenaan.");			
			
		}else if(nofail != null && nofail != "" && selesai == "yes"){
		alert("Permohonan untuk simati "+simati+" telah wujud dengan No. fail Permohonan "+nofail+". Permohonan telah dibuat di "+pejabat+"("+daerah+").  Tuan/Puan tidak dapat meneruskan Permohonan Seksyen 8 Online. Sila teruskan permohonan melalui Seksyen 17.");
		/*
			alert("Permohonan untuk simati "+simati+" telah wujud dengan No. Permohonan Online "+nofailonline+". Sila semak No. Permohonan Online  di bawah menu Status Permohonan Pusaka dan lengkapkan maklumat permohonan berkenaan.");
			*/
		}
		else if(id_status_daftar == "171"){
		alert("Permohonan untuk simati "+simati+" telah wujud dengan No. Rujukan Online "+nofailonline+". Permohonan telah dihantar ke "+pejabat+"("+daerah+").  Tuan/Puan tidak dapat meneruskan Permohonan Seksyen 8 Online. Sila berhubung dengan Pejabat Pusaka berkenaan.");		
		}	
		else if(id_status_daftar == "160"){
		alert("Permohonan untuk simati "+simati+" telah wujud di Permohonan Online Seksyen 17. Sila semak Maklumat Permohonan di bawah menu Deraf Permohonan PPK dan lengkapkan maklumat permohonan berkenaan.");		
		}	
		else if(id_status_daftar == "150"){
		alert("Permohonan untuk simati "+simati+" telah wujud di Permohonan Online Seksyen 8. Sila semak Maklumat Permohonan di bawah menu Deraf Permohonan PPK dan lengkapkan maklumat permohonan berkenaan.");		
		}
	
</script>


#end


#if ($idAlert == "0")



#set ($idAlert = "")
#set ($getDaerahx = "")
#set ($tarikhmohonx = "")
#set ($nokpbaru1x = "")
#set ($nokpbaru2x = "")
#set ($nokpbaru3x = "")
#set ($noKpLamax = "")
#set ($jeniskpsimatix = "")
#set ($noKpLainx = "")
#set ($namaSimatix = "")
#set ($tarikhMatix = "")
#set ($noKpBaruPemohon1x = "")
#set ($noKpBaruPemohon2x = "")
#set ($noKpBaruPemohon3x = "")
#set ($$noKpLamaPemohonx = "")
#set ($noKpLainPemohonx = "")
#set ($namaPemohonx = "")
#set ($alamat1x = "")
#set ($alamat2x = "")
#set ($alamat3x = "")
#set ($poskodx = "")
#set ($bandarx = "")

#set ($umursimati = "")
#set ($jantinasimati = "")
#set ($umurpemohon = "")
#set ($jantinapemohon = "")

#set($jenisKpPemohon = "0")
#set($jenisKpMati = "0")

#set ($taraf_penting = "")
#set ($no_tel = "")
#set ($nama_pelbagainegara = "")
#set ($no_hp = "")
#set ($emel = "")
#set ($jenis_pemohon = "2")
#set ($jenis_pej = "")
#set ($socSaudaraWaris = "")
#set ($no_fail_online = "")

#end

#if ($idAlert == "1" || $idAlert == "2")

#set ($tarikhmohonx = $tarikhMohonm)
#set ($nokpbaru1x = $nokpbaru1m)
#set ($nokpbaru2x = $nokpbaru2m)
#set ($nokpbaru3x = $nokpbaru3m)
#set ($noKpLamax = $no_kplama_simatim)
#set ($jeniskpsimatix = $socJenisKPLainSimatim)
#set ($noKpLainx = $no_kplain_simatim)
#set ($namaSimatix = $namaSimatim)
#set ($tarikhMatix = $tarikh_simatim)
#set ($noKpBaruPemohon1x = $no_kpbaru_pemohon1m)
#set ($noKpBaruPemohon2x = $no_kpbaru_pemohon2m)
#set ($noKpBaruPemohon3x = $no_kpbaru_pemohon3m)
#set ($noKpLamaPemohonx = $no_kplama_pemohonm)
#set ($jeniskpsipemohonx = $sel_jeniskp_pemohonm)
#set ($noKpLainPemohonx = $no_kplain_pemohonm)
#set ($namaPemohonx = $nama_pemohonm)
#set ($alamat1x = $alamat1m)
#set ($alamat2x = $alamat2m)
#set ($alamat3x = $alamat3m)
#set ($poskodx = $poskodm)
#set ($bandarx = $bandarm)

#set ($umursimati = $umursimati)
#set ($jantinasimati = $jantinasimati)
#set ($umurpemohon = $umurpemohon)
#set ($jantinapemohon = $jantinapemohon)


#set ($taraf_penting = $taraf_penting)
#set ($no_tel = $no_tel)
#set ($nama_pelbagainegara = $nama_pelbagainegara)
#set ($no_hp = $no_hp)
#set ($jenis_pemohon = $jenis_pemohon)
#set ($jenis_pej = $jenis_pej)
#set ($socSaudaraWaris = $socSaudaraWaris)


#end

#set ($id = "")
#set ($idPemohon = "")
#set ($noFail = "")
#set ($no_fail_online = "")


#set ($tarikhDaftar = $tarikhmohon)


        

#set ($tarikhMohon = "")
#set ($noKpBaru1 = "")
#set ($noKpBaru2 = "")
#set ($noKpBaru3 = "")
#set ($noKpLama = "")
#set ($noKpLain = "")
#set ($namaSimati = "")
#set ($tarikhMati = "")
#set ($noKpBaruPemohon1 = "")
#set ($noKpBaruPemohon2 = "")
#set ($noKpBaruPemohon3 = "")
#set ($$noKpLamaPemohon = "")
#set ($noKpLainPemohon = "")
#set ($namaPemohon = "")
#set ($alamat1 = "")
#set ($alamat2 = "")
#set ($alamat3 = "")
#set ($poskod = "")


#set ($bandar = "")

#set ($setmode2 = "")



#if ($idFlag == "2")
    #foreach($View in $View)
    #set($id_Status = $View.id_Status)
  
    #set($id_Permohonansimati = $View.id_Permohonansimati)
        #set ($IdFail = $View.idFail)
        #set ($id = $View.id)
        
        #set ($idPemohon = $View.idPemohon)
        #set ($idPemohonan = $View.idPermohonan)
        
        #set ($umursimati = $View.umursimati)
        #set ($jantinasimati = $View.jantinasimati)
        #set ($umurpemohon = $View.umurpemohon)
        #set ($jantinapemohon = $View.jantinapemohon)
        #set ($idFail = $View.idFail)   
        
        
        #set ($noFail = $View.noFail)
         #set ($no_fail_online = $View.no_fail_online)
        
        
        
        #set ($tarikhMohon = $View.tarikhMohon)
        #set ($noKpBaru1 = $View.noKpBaru1)
        #set ($noKpBaru2 = $View.noKpBaru2)
        #set ($noKpBaru3 = $View.noKpBaru3)
        #set ($noKpLama = $View.noKpLama)
        #set ($jenisKpMati = $View.jenisKp)
        #set ($noKpLain = $View.noKpLain)
        #set ($idSimati = $View.idSimati)
        #set ($namaSimati = $View.namaSimati)
        #set ($tarikhMati = $View.tarikhMati)
        #set ($noKpBaruPemohon1 = $View.noKpBaruPemohon1)
        #set ($noKpBaruPemohon2 = $View.noKpBaruPemohon2)
        #set ($noKpBaruPemohon3 = $View.noKpBaruPemohon3)
        #set ($noKpLamaPemohon = $View.noKpLamaPemohon)
        #set ($noKpLainPemohon = $View.noKpLainPemohon)
        #set ($jenisKpPemohon = $View.jenisKpPemohon)
        #set ($namaPemohon = $View.namaPemohon)
         
        #set ($socSaudaraWaris = $View.socSaudaraWaris)
        
        #set ($taraf_penting = $View.taraf_penting)
         #set ($no_tel = $View.no_tel)
          #set ($nama_pelbagainegara = $View.nama_pelbagainegara)
            #set ($no_hp = $View.no_hp)
          #set ($jenis_pemohon = $View.jenis_pemohon)
      
         
        #set ($alamat1 = $View.alamat1)
        #set ($alamat2 = $View.alamat2)
        #set ($alamat3 = $View.alamat3)
        #set ($poskod = $View.poskod)
        #set ($bandar = $View.bandar)
        
        
        #set ($jenis_pej = $View.jenis_pej)
        
          
        #set ($idDaerahx = $View.idDaerah)
        #if ($View.idDaerah != "")
        	#set ($setmode2 = "disabled")
        #else
        	#set ($setmode2 = "")
        #end
    #end
#end

#set ($flag_no = "")
#set ($setmode = "")

#set ($flag_no = $flagno)
#if ($flag_no == 2 )
	#set ($setmode = "disabled")
#end

<body onLoad="submitForm();calcDate();check_kp();check_kp_lama();check_kp_lain();check_pengenalan_simati_1_onload();check_pengenalan_simati_2_onload();check_pengenalan_simati_3_onload();pilih_taraf();check();check_kp_pemohon_onload();sorok_fieldset('$IdFail');selectPelbagaiNegara('$negeri','div_mesejpelbagagainegara','tr_pelbagainegara','nama_pelbagainegara');checkSumaICsimati('$command');">


<form id="form1" name="f1" method="post" action="">

<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="online_skrin" id="online_skrin" value="yes" />
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
<input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_Permohonansimati" id="id_Permohonansimati" value="$!id_Permohonansimati" >

#foreach($listFail in $ViewFail)
#set($id_SF = $listFail.id_Suburusanstatusfail )
#set($id_S = $listFail.id_Suburusanstatus )
<!--
$listFail.id_Suburusanstatusfail 
$listFail.id_Suburusanstatus
-->
#end

<input name="id_Suburusanstatus" type="hidden"  value="$id_S"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$id_SF"/>
<input name="id_Fail" type="hidden" value="$IdFail" />

<input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/>

#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#end

<table width="100%" style="display:none">
<tr>
<td>

  
<div align="right">
#if($pendaftaran == "")
#if ($flagFromSenaraiFailSek8 == '' && $flagForView  == '' && $flagFromSenaraiPermohonanSek8 == '' && $pendaftaran == "")<a href="javascript:javascript:Kembali()" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if ($flagFromSenaraiFailSek8 == 'yes')<a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagForView  == 'yes')<a href="javascript:ForView('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style2"  ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style2"  ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3current.png" alt="" border="0" title="Pendaftaran Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>#if ($noFail != "")<a href="javascript:seterusnya_tab()" class="style2"  ><img src="../img/4enable2.png" alt="" border="0" title="Maklumat Permohonan"/></a>#else<img src="../img/4disable2.png" alt="" border="0" title="Maklumat Permohonan"/>#end
</span>
#else
#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style2"  ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style2"  ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/2current.png" alt="" border="0" title="Pendaftaran Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>#if ($no_fail_online != "")<a href="javascript:seterusnya_tab()" class="style2"  ><img src="../img/3enable2.png" alt="" border="0" title="Maklumat Permohonan"/></a>#else<img src="../img/3disable.png" alt="" border="0" title="Maklumat Permohonan"/>#end
</span>
#end
</div>
</td>
</tr>
</table>


<fieldset id="tab_Daftar">



            
<p></p>
<p>
  <input name="mode1" type="hidden" value="$mode1" />
  <input name="mode2" type="hidden" value="$mode2" /> 
  <input name="id_Fail" type="hidden" value="$idFail" />
  #foreach($ls in $ListSemakan)
  
  #if($ls.idsemakansenarai=="5")
  #set($buktimati="1")
  #set($sijilmati="$ls.Catatan")
  
  #end
  #if($ls.idsemakansenarai=="6")
  
  #set($buktimati="2")
  
  #set($sijilmati="")
  #end
  #if($ls.idsemakansenarai=="7")
  #set($buktimati="3")
  
  #set($sijilmati="")
  #end
  #if($ls.idsemakansenarai=="8")
  #set($buktimati="4")
  <!-- #set($sijilmati="$ls.Catatan") -->
  #end
  #end
  <input name="buktimati" type="hidden" value="$buktimati" />
  <input name="sijilmati" type="hidden" value="$sijilmati" />
 
<table width="100%" border="0">
  <tr>
    <td width="99%" valign="top" >
    
    <table width="100%" border="0">
      <tr>
        <td valign="top" >
        
         
          <table width="100%" border="0" id="maklumat_asas">
          
           <tr>
            <td></td>
            <td colspan="3">
            #parse("app/ppk/info_berjaya_disimpan.jsp")
            
            </td>
            </tr>
           <tr>
              <td width="2%">&nbsp;</td>
              <td width="17%">Nama Pemohon</td>
              <td width="1%">:</td>
              <td width="80%"><strong>$namaPemohon</strong></td>
            </tr>
            
            <tr>
              <td >&nbsp;</td>
              <td >MyID Pemohon</td>
              <td >:</td>
              <td ><strong> #if($noKpBaruPemohon1!="" && $noKpBaruPemohon2!="" && $noKpBaruPemohon3!="")              
              $noKpBaruPemohon1 - $noKpBaruPemohon2 - $noKpBaruPemohon3 &nbsp;&nbsp;
              #end              
              
              #if($noKpLamaPemohon!="")              
              $noKpLamaPemohon &nbsp;&nbsp;
              #end
              
               #if($noKpLainPemohon!="") 
               
               #if($jenisKpPemohon == 4)
               #set($jkpm_x = "NO PASPORT")
               #elseif($jenisKpPemohon == 5)
               #set($jkpm_x = "NO TENTERA")
               #elseif($jenisKpPemohon == 6)
               #set($jkpm_x = "NO POLIS")
               #elseif($jenisKpPemohon == 13)
               #set($jkpm_x = "LAIN-LAIN")
               #else
               #set($jkpm_x = "")
               #end
                         
               $jkpm_x $noKpLainPemohon
              #end </strong></td>
            </tr>
            
            <tr>
              <td >&nbsp;</td>
              <td >Nama Simati</td>
              <td >:</td>
              <td ><strong>$namaSimati</strong></td>
            </tr>
            
             
            <tr>
              <td >&nbsp;</td>
              <td >MyID Simati</td>
              <td >:</td>
              <td ><strong> #if($noKpBaru1!="" && $noKpBaru2!="" && $noKpBaru3!="")              
              $noKpBaru1 - $noKpBaru2 - $noKpBaru3 &nbsp;&nbsp;
              #end              
              
              #if($noKpLama!="")              
              $noKpLama &nbsp;&nbsp;
              #end
              
               #if($noKpLain!="") 
               
               #if($jenisKpMati == 4)
               #set($jkpm_y = "NO PASPORT")
               #elseif($jenisKp == 5)
               #set($jenisKpMati = "NO TENTERA")
               #elseif($jenisKp == 6)
               #set($jenisKpMati = "NO POLIS")
               #elseif($jenisKpMati == 13)
               #set($jkpm_y = "LAIN-LAIN")
               #else
               #set($jkpm_y = "")
               #end
                         
              $jkpm_y $noKpLain
              #end </strong></td>
            </tr>
            
            
             <tr style="display:none" >
              <td >&nbsp;</td>
              <td >No Rujukan Online</td>
              <td >:</td>
              <td ><strong>$no_fail_online</strong></td>
            </tr>
            
             <tr style="display:none">
              <td  >&nbsp;</td>
              <td >Tarikh Mohon</td>
              <td >:</td>
              <td ><strong>$tarikhMohon</strong></td>
            </tr>
            
            <tr>
            <td></td>
            <td colspan="3">
           <!--   <div align="left">
<font color="red"><b>PERHATIAN :<br/>Sila lengkapkan maklumat permohonan Tuan/Puan dengan menekan butang [Borang A] di sebelah.
Sila gunakan MyID atau No. Rujukan Online untuk mengemaskini permohonan anda.
</b></font></div>
-->


<div id="info_alert"></div>
<script>
parent.document.getElementById("info_alert").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Sila lengkapkan maklumat permohonan Tuan/Puan dengan menekan butang <input type='button' name='cmdSeterusnya' value='Borang A' onClick='seterusnya_tab()'><br><blink>*</blink> Sila gunakan MyID atau No. Rujukan Online untuk mengemaskini permohonan anda.</b></div>";
</script>


            </td>
            </tr>
            </table>
            
          
         
       
        
        <fieldset style="display:none">
          <legend>MAKLUMAT PERMOHONAN</legend>
          <table width="100%" border="0">
           <tr>
              <td width="2%">&nbsp;</td>
              <td width="28%">No Rujukan Online</td>
              <td width="1%">:</td>
              <td width="69%"><input name="txtNoFail" id="txtNoFail" type="text"  onblur="this.value=this.value.toUpperCase()" value="$no_fail_online" style="width: 195px;" class="disabled" readonly="readonly" /></td>
            </tr>
            <tr style="display:none">
              <td >&nbsp;</td>
              <td >No Fail Permohonan</td>
              <td >:</td>
              <td ><input name="txtNoFail" id="txtNoFail" type="text"  onblur="this.value=this.value.toUpperCase()" value="$noFail" style="width: 195px;" class="disabled" readonly="readonly" /></td>
            </tr><tr style="display:none">
            
            
            
              <td valign="top">&nbsp;</td>
            
           
              <td>#if($setmode2 != "disabled") Daerah #else
              Daerah
              #end </td>
              <td>:</td>
               <td style="width: 195px; text-transform:uppercase;">
               
               
               #foreach ($ld in $listdaerah)
               #if($ld.id == $idDaerahx)
               #set($nd = "$ld.kod - $ld.nama")
               #end               
               #end
               
               #if($setmode2 == "disabled")
               #set($setmode2R = "readonly")
                <input name="socDaerah" id="socDaerah" value="$nd" type="text" $setmode2R class="$setmode2" style="width: 195px;"/>
               #else
               #set($setmode2R = "")
               #end
              
              
               <input name="socDaerahinput" id="socDaerahinput" value="$idDaerahx" type="hidden"  />
               
               
               #if($setmode2 != "disabled")
               <select name="socDaerah" style="width: 195px;" $setmode2 />
            #set ($idDaerah = "")
            #set ($namaDaerah = "")
       #if ($idDaerahx != "")
            #foreach ($listDaerah in $ListDaerahByUser)
	            #set ($idDaerah = $listDaerah.iddaerah)
	            #set ($namaDaerah = $listDaerah.namadaerah)
	            	#if ($idDaerah == $idDaerahx)
	            		<option value="$idDaerah" selected>$namaDaerah</option>
	            	#end
			#end
			<option value="0">SILA PILIH DAERAH</option>
			#foreach ($listDaerah in $ListDaerahByUser)
	            #set ($idDaerah = $listDaerah.iddaerah)
                #set ($kodDaerah = $listDaerah.koddaerah)
	            #set ($namaDaerah = $listDaerah.namadaerah)
			<option value="$idDaerah" >$namaDaerah</option>
          
			#end
	 	#else
			<option value="0">SILA PILIH DAERAH</option>
			#foreach ($listDaerah in $ListDaerahByUser)
	            #set ($idDaerah = $listDaerah.iddaerah)
	            #set ($namaDaerah = $listDaerah.namadaerah)
                #set ($kodDaerah = $listDaerah.koddaerah)
			<option value="$idDaerah" >$kodDaerah - $namaDaerah </option>
            
            
            
            
			#end
		#end
            </select></td>
         
            
            #end
            <tr style="display:none">
              <td valign="top">&nbsp;</td>
              <td><p>#if($setmode != "disabled")Tarikh Mohon#else Tarikh Mohon #end</p>                </td>
              <td>:</td>
              <td>
              #if ($idAlert == "1" || $idAlert == "2") 
               #if($setmode == "disabled")
               #set($setmodeR = "readonly")             
               #else
               #set($setmodeR = "")
               #end
                <input name="txdTarikhMohon" id="txdTarikhMohon" style="width: 80px;" type="text" $setmodeR class="$setmode" value="$tarikhmohonx" size="11" maxlength="10" />
                
                 <input name="tdaftar" id="tdaftar"  type="hidden" value="$tarikhmohonx" />
#else
            
            
            #if($tarikhmohondaftar!="")
          
            #set($t=$tarikhmohondaftar)
            #end 
            
            #if($tarikhmohondaftar=="")
           
            #set($t=$tarikhMohon)
            #end            
            
               #if($setmode == "disabled")
               #set($setmodeR = "readonly")             
               #else
               #set($setmodeR = "")
               #end
               
               <input name="tdaftar" id="tdaftar"  type="hidden" value="$t" />
            
               <input name="txdTarikhMohon" id="txdTarikhMohon" style="width: 80px;" type="text" $setmodeR class="$setmode" value="$t" size="11" maxlength="10" onBlur="trans_date2(this.value)" />
          
               
#end
            #if ($EventStatus == "0") <a href="javascript:displayDatePicker('txdTarikhMohon',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> 
            
            #end </td>
            </tr>
            
            
            <tr style="display:none" >
              <td valign="top"></td>
              <td><p>Sasaran Tempoh KPI</p>                </td>
              <td>:</td>
              <td>
            <div id="kpi_ppk" ></div>
             </td>
            </tr>
            
            
            
          </table>
        </fieldset>
        
        
        </td>
      </tr>
       <tr>
      <td>
      
    
      </td>
      </tr>
      <tr>
        <td>
           
        
        <fieldset id="maklumat_pemohon">
      <legend>MAKLUMAT PEMOHON</legend>
      <div id="info_skrin_daftar_sek8"></div>
         <script>
 parent.document.getElementById("info_skrin_daftar_sek8").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Sila daftarkan maklumat Pemohon sebelum ke skrin seterusnya.</b></div>";
 </script>
     <input name="baca" id="baca" type="hidden" value="$setmode" />
     <input nama="tp" id="tp" type="hidden" value="$taraf_penting" />
      <table width="100%" border="0">
     
        <tr >
   
            <td valign="top" width="2%" >#if($setmode != "disabled") <span class="style1">*</span> #end</td>
                                        <td width="28%"><div align="right" class="style38">
                                          <div align="left">Taraf Kepentingan</div>
                                        </div></td>
                  <td width="1%">:</td>
                                        <td width="69%">
                  
                   #if($taraf_penting=="")                                          
                                          #set($taraf_penting="")                                                          
                                          
                                           #set($tarafkePemohonan="")
                                          #set($tarafkePemohonanid="")
                                          #else
                                          #foreach($listtar in $listtaraf)
                                          
                                          #if($taraf_penting==$listtar.id_Tarafkptg)
                                          
                                          #set($tarafkePemohonan="$listtar.kod - $listtar.keterangan")
                                          #set($tarafkePemohonanid="$listtar.id_Tarafkptg")
                                          
                                          
                                          
                                          #end  
                                          
                                          
                                          
                                          #end
                                          #end
                                          
                                          #if($taraf_penting!="" && $taraf_penting!=0 && $taraf_penting!="null" )
                                          #set($dahada="ada")
                                          #else
                                          #set($dahada="Xada")
                                          #end
                                          
                                          <!--
                                          ::::::: ID TARAF :$listpemohon.idTarafkptg   
                                          ::::::: ADA TARAF
                                          -->
                                          
                                        <!--  listPemohonOB size:::: $listPemohonOB.size() -->
                                 
                                          #if( $listPemohonOB.size()>0)
                                          #set($tmpp="ada")
                                          #else
                                          #set($tmpp="Xada")
                                          #end
                                        <!-- listPemohonOB size:::: --> 
                                      
                                        <input name="adaob" type="hidden"  value="$!tmpp" />                                  
                                          
                                          <input name="adataraf" type="hidden"  value="$dahada" />                                         #if($setmode=="disabled")
                                          <input name="textfield2" type="text" id="textfield3" style="text-transform:uppercase;" onBlur="uppercase()" value="$tarafkePemohonan"  size="34" $readmodeR class="$setmode" />
                                      <input name="taraf_penting" id="taraf_penting" type="hidden" style="text-transform:uppercase;"  onblur="uppercase()" value="$taraf_penting"  size="34" readonly="$readmodeR" class="$setmode" />
                                          #else
                                          
                                          #if($taraf_penting!="")
                                          <input type="hidden" name="socTarafKePemohonanpp2" value="$taraf_penting" />
                                              <select name="taraf_penting" id="taraf_penting" class="largeselect;disabled" style="text-transform:uppercase;"  onblur="uppercase()" onChange="pilih_taraf();pilih_amanah();default_amanah()" >
                                                <option value="$taraf_penting" style="text-transform:uppercase;"  onblur="uppercase()">$tarafkePemohonan</option>
                                                                                       
                                          #foreach($listtar in $listtaraf)
                                            #if($taraf_penting!=$listtar.id_Tarafkptg)
                                                <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onBlur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                          #end      
                                          #end  
                                          
                                              </select>
                                          #else
                                          #foreach($listtar in $listtaraf)                                         
                                          #if($listtar.id_Tarafkptg == "1")                                          
                                          #set($taraf="$listtar.kod - $listtar.keterangan")
                                          #set($tarafid="$listtar.id_Tarafkptg")
                                          #end 
                                          #end
                                          
                                          
                                          
                                          
                                          <select name="taraf_penting" class="largeselect" style="text-transform:uppercase;" onBlur="uppercase()" onChange="pilih_taraf();pilih_amanah();default_amanah()" >
                                            <option value="$tarafid" style="text-transform:uppercase;" onBlur="uppercase()">$taraf</option>
                                            
                                            
                                        #foreach($listtar in $listtaraf)
                                        
                                        #if($listtar.id_Tarafkptg != $tarafid) 
                                            <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onBlur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                        #end    
                                        #end
                                       
                                          </select>
                                          #end
                                          
                                          #end                                               </td>
                                          
                                      
          </tr>
          
       <input name="jenis_pej" id="jenis_pej" type="hidden"  value="$!jenis_pej" />
       
       
       <tr id="hubungan" >
                                          <td  valign="top"> #if($setmode != "disabled") <span class="style1">*</span> #end</td>
                                          <td><div align="left" class="style75">Hubungan Dengan Simati</div></td>
                  <td>:</td>
                                          <td>
                                          
                                          
                                          
                                          #if($setmode == "disabled")                                          
                                          #foreach($listsau in $listsaudara)                                          
                                          #if($socSaudaraWaris==$listsau.id_Saudara)                                          
                                          #set($kodsaudaraketerangan=$listsau.keterangan)                                          
                                          #end    
                                          #end
                                          
                                           #if($socSaudaraWaris!="" && $socSaudaraWaris!="0")
                                           <input  name="sau" id"sau" value="$kodsaudaraketerangan"   size="50" style="text-transform:uppercase;" $setmodeR class="$setmode" />
                                          #else
                                           <input  name="sau" id"sau" value="" style="text-transform:uppercase;" size="15" $setmodeR class="$setmode" />
                                          #end
                                          
                                          
                                          #else
                                          
                                          #foreach($listsau in $listsaudara)
                                          
                                          #if($socSaudaraWaris==$listsau.id_Saudara)
                                          
                                          #set($kodsaudara=$listsau.kod)
                                          #set($kodsaudaraketerangan=$listsau.keterangan)
                                          
                                          
                                          
                                          #end    
                                          #end
                                           #if($socSaudaraWaris!="")
                     <select name="socSaudaraWaris" id="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onBlur="text-transform:uppercase;" >
                     <option value="$socSaudaraWaris" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">$kodsaudara - $kodsaudaraketerangan</option>                     #foreach($listsau in $listsaudara)                                 
                     #if($socSaudaraWaris!=$listsau.id_Saudara) 
                     <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                     #end#end
                     </select>
                     #else
                     <select name="socSaudaraWaris" id="socSaudaraWaris" class="largeselect" $readmode style="text-transform:uppercase;" onBlur="text-transform:uppercase;" >
                     <option value="" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Sila Pilih Talian Persaudaraan</option>
                     #foreach($listsau in $listsaudara) 
                     <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                     #end
	                 </select>                                         
                     #end
                     </td>
                     #end </tr>
       
         
          <tr id="amanah" >
    
        
          <td >&nbsp;</td>
          <td >Amanah raya</td>
          <td >:</td>
          <td >      
     
          <select name="jenis_pej1" id="jenis_pej1" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya()" >         
          #if($jenis_pej == "" || $jenis_pej == "0") 
          <option value="">SILA PILIH </option>   
                 
           #else
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                  #foreach($listJ in $listMaklumatMahkamahJ)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '9' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($jenis_pej != "" || $jenis_pej != "0")     
                                  <option value="">SILA PILIH </option>
                                  #end                                       
          </select>          </td>
        </tr>     
 
 
 
 
 <tr id="baitulmal" >
    
        
          <td >&nbsp;</td>
          <td >Baitulmal</td>
          <td >:</td>
          <td >      
     
          <select name="jenis_pej2" id="jenis_pej2" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya()" >         
          #if($!jenis_pej == "" || $!jenis_pej == "0") 
          <option value="">SILA PILIH </option>   
                 
           #else
           #foreach($listJ in $listMaklumatMahkamahJ)
           #if( $listJ.id_Pejabat == $jenis_pej )              
           #set($listJid_Pejabat = $listJ.id_Pejabat)
           #set($listJnama_pejabat=$listJ.nama_pejabat)
           #set($listJdaerah = $listJ.daerah)  
           #set($nama_bandar = $listJ.namabandar)         
           #end
           #end
           <option value="$listJid_Pejabat">$listJnama_pejabat , $nama_bandar </option>           
           #end        
                                
                                
                                
                                  #foreach($listJ in $listMaklumatMahkamahJ)
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '61' )
	                              <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                  <option value="">SILA PILIH </option>
                                  #end                                       
          </select>          </td>
        </tr>
 
 
          <tr id="j_pemohon"  >
            <td >&nbsp;</td>
            <td >Jenis Pemohon</td>
            <td >:</td>
            <td >
            
           #if($setmode == "disabled")
           #if($jenis_pemohon == "1")
           #set($jh = "01-AGENSI")
           #elseif($jenis_pemohon == "2")
            #set($jh = "02-INDIVIDU")
           #else
            #set($jh = "")
           #end
           
           #if($jenis_pemohon != "0" && $jenis_pemohon != "")
   			<input type="text" name="jenis_pemohonx"  id="jenis_pemohonx" value="$jh" size = "34" $setmodeR class="$setmode" >    
          #else
   			<input type="text" name="jenis_pemohonx"  id="jenis_pemohonx" value="" size = "34" $setmodeR class="$setmode" >    
          #end    
           
          <input type="hidden" name="jenis_pemohon"  id="jenis_pemohon" value="$jenis_pemohon" size = "34" >    
          #else
            
            
            <span id="jenis_pemohon_drop"  >
           
            
			<select name="jenis_pemohon"  class="autoselect" $readmode id="jenis_pemohon" style="text-transform:uppercase;" onBlur="uppercase()" onChange="pilih_taraf()" >        
			#if($jenis_pemohon=="1")  
                 <option value="1">01-Agensi</option>
                 <option value="2">02-Individu</option>
              #elseif($jenis_pemohon=="2")
                 <option value="2">02-Individu</option>
                 <option value="1">01-Agensi</option>
           #else 
                   <option value="2">02-Individu</option>
                   <option value="1">01-Agensi</option>
           #end  
           
           </select>
                    </span>
                                    
           <span id="jenis_pemohon_dis">
           <input type="text" name="jenis_pemohon_display"  id="jenis_pemohon_display" readonly class="disabled" >    
           </span>                
            #end
            </td>
          </tr>
          
          
          <tr id="kp1" >
          <td valign="top" width="2%">#if($setmode != "disabled")
             <span class="style1">*</span>
              #end</td>
          <td  >MyID Baru</td>
          <td  >:</td>
          <td  scope="col"> #if ($idAlert == "1" || $idAlert == "2")
            
            <input name="txtNoKPBaruPemohon1" id="txtNoKPBaruPemohon1" style="width: 50px;" type="text" value="$noKpBaruPemohon1x" $setmodeR class="$setmode" size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruPemohon2')"  onblur="getAgeByIC(this,this.value,'txtUmurPemohon')" />
            -
            <input name="txtNoKPBaruPemohon2" id="txtNoKPBaruPemohon2" style="width: 20px;" type="text" value="$noKpBaruPemohon2x" $setmodeR class="$setmode" size="3" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruPemohon3')"/>
            -
            <input name="txtNoKPBaruPemohon3" id="txtNoKPBaruPemohon3"  style="width: 40px;" type="text" value="$noKpBaruPemohon3x" $setmodeR class="$setmode" size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruPemohon3')" onBlur="jantinaic1();kp_baru_pemohon()" />
             <div id="check_kp_p1" style="color:red" ></div>
            #else
            
            <input name="txtNoKPBaruPemohon1" id="txtNoKPBaruPemohon1" style="width: 50px;" type="text" value="$noKpBaruPemohon1" $setmodeR class="$setmode" size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruPemohon2')" onBlur="getAgeByIC(this,this.value,'txtUmurPemohon')"  />
            -
  <input name="txtNoKPBaruPemohon2" id="txtNoKPBaruPemohon2" style="width: 20px;" type="text" value="$noKpBaruPemohon2" $setmodeR class="$setmode" size="3" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruPemohon3')"/>
            -
  <input name="txtNoKPBaruPemohon3" id="txtNoKPBaruPemohon3"  style="width: 40px;" type="text" value="$noKpBaruPemohon3" $setmodeR class="$setmode" size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruPemohon3')" onBlur="jantinaic1();kp_baru_pemohon()" />
   <div id="check_kp_p1" style="color:red" ></div>
            #end 
           
            
            </td>
        </tr>
        <tr  id="kp2">
          <td>&nbsp;</td>
          <td>MyID Lama</td>
          <td>:</td>
          <td> #if ($idAlert == "1" || $idAlert == "2")
            
            <input name="txtNoKPLamaPemohon"  onblur="this.value=this.value.toUpperCase();kp_lama_pemohon()" id="txtNoKPLamaPemohon" style="width: 120px; text-transform:uppercase;" type="text" value="$noKpLamaPemohonx" $setmodeR class="$setmode" maxlength="8" size="10"/>
            <div id="check_kp_p2" style="color:red" ></div>
            #else
            
            <input name="txtNoKPLamaPemohon"  onblur="this.value=this.value.toUpperCase();kp_lama_pemohon()" id="txtNoKPLamaPemohon" style="width: 120px; text-transform:uppercase;" type="text" value="$noKpLamaPemohon" $setmodeR class="$setmode" maxlength="8" size="10"/>
            <div id="check_kp_p2" style="color:red" ></div>
            #end 
              
            
            </td>
        </tr>
       
        <tr id="kp3" >
          <td>&nbsp;</td>
          <td>MyID Lain</td>
          <td>:</td>
          <td>
          #if($setmode == "disabled")
               #set($setmodeR = "readonly") 
               
               #if($jenisKpPemohon == 4)
               #set($jkpm = "NO PASPORT")
               #elseif($jenisKpPemohon == 5)
               #set($jkpm = "NO TENTERA")
               #elseif($jenisKpPemohon == 6)
               #set($jkpm = "NO POLIS")
                #elseif($jenisKpPemohon == "7")
               #set($jkpm = "LAIN-LAIN")
               #else
               #set($jkpm = "")
               #end
                 
               <input  name="socJenisKPLainPemohon" class="$setmode" id="socJenisKPLainPemohon" value="$jkpm" $setmodeR />
             
               
                        
               #else
               #set($setmodeR = "") 
              
          <select name="socJenisKPLainPemohon" id="socSeksyen3" class="ppkicselect" style="width: 110px; text-transform:uppercase" $setmode onChange="kplain1(this.value)"  onblur="kplain1X(this.value)" />
            
            #if ($idAlert == "1" || $idAlert == "2" || $idFlag == "2") 
            #set ($selected = "")
            <option value="0">Sila Pilih KP</option>
            #foreach($Listkp in $listkp)
            #if ($Listkp.id == $jenisKpPemohon) 
            #set ($selected = "selected")
            <option value="$Listkp.id" $selected>$Listkp.keterangan</option>
            #end
  <option value="$Listkp.id" >$Listkp.keterangan</option>
            #end
            #else
            #if ($idFlag == "2")
            #set ($selected = "")
            <option value="0">Sila Pilih KP</option>
            #foreach($Listkp in $listkp)
            #if ($Listkp.id == $jenisKpPemohon)
            #set ($selected = "selected")
            <option value="$Listkp.id" $selected>$Listkp.keterangan</option>
            #end
            #end
            #else
  <option value="0">Sila Pilih KP</option>
            #foreach($Listkp in $listkp)
  <option value="$Listkp.id">$Listkp.keterangan </option>
            #end
            #end
            #end
  </select>
  
  #end
            #if ($idAlert == "1" || $idAlert == "2")
            
            
            #if($jenisKpPemohon=="" || $jenisKpPemohon=="0")
            
            #if($setmode=="disabled")
            
            
            #set($setmod1="disabled")
            #set($setmod1R = "readonly")
            
            #else
             
            #set($setmod1="")
            #set($setmod1R = "disabled")
            
            #end
            
           
            
            
            
             #else
             
              #set($setmod1="")
              #set($setmod1R = "")
            
            
            
            #end
            
         
            
            <input name="txtNoKPLainPemohon" type="text" class="$setmod1" id="txtNoKPLainPemohon" style="width: 90px; text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();kp_lain_pemohon()" value="$noKpLainPemohonx" maxlength="30" $setmod1R/>
            <div id="check_kp_p3" style="color:red" ></div>
            #else
            
            
            #if($setmode=="disabled")
            #set($setmod1="disabled")
            #set($setmod1R = "readonly")          
            #else
            #if($jenisKpPemohon=="" || $jenisKpPemohon=="0")
            #set($setmod1="")
            #set($setmod1R = "disabled")
            #else
            #set($setmod1="")
            #set($setmod1R = "")           
            #end
            #end
            
           <input name="txtNoKPLainPemohon" type="text" class="$setmod1" id="txtNoKPLainPemohon" style="width: 90px; text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();kp_lain_pemohon()" value="$noKpLainPemohon" maxlength="30" $setmod1R/>
           <div id="check_kp_p3" style="color:red" ></div>
            #end 
            
              
            </td>
        </tr>
    
        <tr>
          <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
          <td>#if($setmode != "disabled") Nama Pemohon #else
            Nama Pemohon
            #end </td>
          <td>:</td>
          <td>
          
          
           #if ($idAlert == "1" || $idAlert == "2")
            <span id="txtNamaPemohon_1a">
            <input name="txtNamaPemohon" type="text" class="$setmode" id="txtNamaPemohon" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$namaPemohonx" size="50" maxlength="200" $setmodeR />
            </span>
             <span id="txtNamaPemohon_1b">
             <input name="txtNamaPemohon" type="text" class="disabled" id="txtNamaPemohon" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$namaPemohonx" size="50" maxlength="200" readonly />
            </span>
            
            
            #else
             <span id="txtNamaPemohon_2a" >
             <input name="txtNamaPemohon" type="text" class="$setmode" id="txtNamaPemohon" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$namaPemohon" size="50"  maxlength="200" $setmodeR />
            </span>
             <span id="txtNamaPemohon_2b" >
             <input name="txtNamaPemohon" type="text" class="disabled" id="txtNamaPemohon" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$namaPemohon" size="50"  maxlength="200" readonly />
            </span>
            
            
            #end 
             <span id="add_alamat_raya" > </span>
             
           
            
            </td>
        </tr>
        <tr>
          <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
          <td> 
            Alamat Tetap
            </td>
          <td>:</td>
          <td> #if ($idAlert == "1" || $idAlert == "2")
            <span id="txtAlamat1_1a">
            <input name="txtAlamat1" type="text" class="$setmode" id="txtAlamat1" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat1x" size="50"  maxlength="100" $setmodeR />
            </span>
              <span id="txtAlamat1_1b">
            <input name="txtAlamat1" type="text" class="disabled" id="txtAlamat1" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat1x" size="50"  maxlength="100" readonly />
            </span>
            #else
            <span id="txtAlamat1_2a">
            <input name="txtAlamat1" type="text" class="$setmode" id="txtAlamat1" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat1" size="50" maxlength="100" $setmodeR />
            </span>
             <span id="txtAlamat1_2b">
            <input name="txtAlamat1" type="text" class="disabled" id="txtAlamat1" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat1" size="50" maxlength="100" readonly />
            </span>
            #end </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td> #if ($idAlert == "1" || $idAlert == "2")
            
            <span id="txtAlamat2_1a">
            <input name="txtAlamat2" type="text" class="$setmode" id="txtAlamat2" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat2x" size="50" maxlength="100" $setmodeR />
            </span>
            
            <span id="txtAlamat2_1b">
            <input name="txtAlamat2" type="text" class="disabled" id="txtAlamat2" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat2x" size="50" maxlength="100" readonly />
            </span>           
            
            
            #else
            
            <span id="txtAlamat2_2a">
            <input name="txtAlamat2" type="text" class="$setmode" id="txtAlamat2" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat2" size="50" maxlength="100" $setmodeR />
            </span>
            
             <span id="txtAlamat2_2b">
            <input name="txtAlamat2" type="text" class="disabled" id="txtAlamat2" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat2" size="50" maxlength="100" readonly />
            </span>
            
            
            #end </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td> #if ($idAlert == "1" || $idAlert == "2")
            <span id="txtAlamat3_1a" >
            <input name="txtAlamat3" type="text" class="$setmode" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat3x" size="50" maxlength="100" $setmodeR />
            </span>
             <span id="txtAlamat3_1b" >
            <input name="txtAlamat3" type="text" class="disabled" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat3x" size="50" maxlength="100" readonly />
            </span>
            
            #else
            <span id="txtAlamat3_2a" >
            <input name="txtAlamat3" type="text" class="$setmode" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat3" size="50" maxlength="100" $setmodeR />
            </span>
            <span id="txtAlamat3_2b" >
             <input name="txtAlamat3" type="text" class="disabled" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat3" size="50" maxlength="100" readonly />
            </span>
            
            #end </td>
        </tr>
        <tr>
          <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
          <td> #if($setmode != "disabled") Poskod #else
            Poskod
            #end </td>
          <td>:</td>
          <td> #if ($idAlert == "1" || $idAlert == "2")
            <span id="txtPoskod_1a">
            
            <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskodx" $setmodeR class="$setmode" maxlength="5" size="6" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="javascript:validatePoskod(this,this.value)"/>
            </span>
            
             <span id="txtPoskod_1b" >
             
            <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskodx" readonly class="disabled" maxlength="5" size="6" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="javascript:validatePoskod(this,this.value)"/>
            </span>
            #else
            
            <span id="txtPoskod_2a" >
            
            <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskod" $setmodeR class="$setmode" maxlength="5" size="6" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="javascript:validatePoskod(this,this.value)"/>
            
            </span>
            
            <span id="txtPoskod_2b" >
            
          
            <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskod" readonly class="disabled" maxlength="5" size="6" />
            </span>
            #end </td>
        </tr>
        #if($EventStatus!=3)
        <tr>
          <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
          <td class="style38"><div align="left">#if($setmode != "disabled") Negeri #else
            Negeri
            #end </div></td>
          <td>:</td>
          <td> 
          #if($setmode == "disabled")
          
           #foreach($listnegpomo in $listnegeri)
            
            #if($negeri==$listnegpomo.id_Negeri)
            
            #set($negerikodpemoP=$listnegpomo.kod_Negeri)
            #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
            
            
            
            #end 
            #end
            
            #if($negeri != "" && $negeri != "0")
             <input name="socNegeri"  type="text"  class="$setmode" id="socNegeri" style="width: 265px; text-transform:uppercase;" value="$negerikodpemoP - $negeriketeranganpemoP" maxlength="70" $setmodeR />
            #else
            <input name="socNegeri"  type="text"  class="$setmode" id="socNegeri" style="width: 265px; text-transform:uppercase;" value="" maxlength="70" $setmodeR />
            #end
          
          #else
          
          #foreach($listnegpomo in $listnegeri)
            
            #if($negeri==$listnegpomo.id_Negeri)
            
            #set($negerikodpemoP=$listnegpomo.kod_Negeri)
            #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
            
            
            
            #end 
            #end
            
            #if($disabled=="disabled")
            #set($readmodenegeri="disabled")
            #else
            #set($readmodenegeri="")
            #end
            
           
            
            #if($negeri!="" && $negeri!="0"  )
            
            <span id="socNegeri_1a">
<select name="socNegeri" class="autoselect" $setmode  onchange="getBandar('socBandar');get_bandar_simati()" style="text-transform:uppercase;" onBlur="uppercase()">
                          <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
              <option value="">Sila Pilih Negeri</option>
                    </select>
                    </span>
                        
                        <span id="socNegeri_1b">                        
                          #foreach($listnegpomo in $listnegeri)            
            #if($negeri==$listnegpomo.id_Negeri)            
            #set($negerikodpemoP1=$listnegpomo.kod_Negeri)
            #set($negeriketeranganpemoP1=$listnegpomo.nama_Negeri)           
            #end 
            #end                        
                        <input name="socNegeri_dis"  type="text"  class="disabled" id="socNegeri_dis" style="width: 265px; text-transform:uppercase;" value="$negerikodpemoP1 - $negeriketeranganpemoP1" maxlength="70" readonly />                        
                        <input name="socNegeri"  type="hidden"  class="$setmode" id="socNegeri" style="width: 265px; text-transform:uppercase;" value="$negeri" maxlength="70" $setmodeR />                        
                        </span>
                        
            #else
            <span id="socNegeri_2a" >
            <select name="socNegeri" class="autoselect" $setmode  onchange="getBandar('socBandar');get_bandar_simati()" style="text-transform:uppercase;" onBlur="uppercase()">
              <option value="">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>                    
	                               #end  
            </select>
            </span>
            
            <span id="socNegeri_2b">            
            <input name="socNegeri_dis"  type="text"  class="disabled" id="socNegeri_dis" style="width: 265px; text-transform:uppercase;" value="" maxlength="70" readonly />                        
                        <input name="socNegeri"  type="hidden"  class="$setmode" id="socNegeri" style="width: 265px; text-transform:uppercase;" value="$!negeri" maxlength="70" $setmodeR />           
            </span>
            #end
            
            #end </td>
        </tr>
        
        <tr id="tr_mesej_pelbagainegara">
          <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                    <td valign="top" ></td>
                                    <td>Negara</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' $setmodeR class="$setmode" list = 'datalist'  value="$nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    </td>
        </tr>
          
          
       
        <tr>
          <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
          <td class="style38" ><div align="left">#if($setmode != "disabled") Bandar #else
            Bandar
            #end </div></td>
            
         
            
          <td>:</td>
          <td>
           <div id="check_bandar" ></div>
        
       
          
          #if($setmode == "disabled")
            #foreach($listdaerah in $listBandarbyNegeri)            
            
            #if($daerah==$listdaerah.id)            
            #set($listDaerahbyNegeriK=$listdaerah.kod)
            #set($listDaerahbyNegeriN=$listdaerah.nama) 
            #end 
           
            #end
            
            #if($daerah != "" && $daerah != "0")  
             <input name="socBandar"  type="text"  class="$setmode" id="socBandar" style="width: 265px; text-transform:uppercase;" value="$listDaerahbyNegeriK - $listDaerahbyNegeriN" maxlength="70" $setmodeR />
            #else 
            <input name="socBandar"  type="text"  class="$setmode" id="socBandar" style="width: 265px; text-transform:uppercase;" value="" maxlength="70" $setmodeR />
            #end
            
            
          #else
          
          #foreach($listdaerah in $listBandarbyNegeri)
           
            #if($daerah==$listdaerah.id)
            
            #set($listDaerahbyNegeriK=$listdaerah.kod)
            #set($listDaerahbyNegeriN=$listdaerah.nama)
            
            
            
            #end 
            #end
            
            
            #if($disabled=="disabled")
            #set($readmodedaerah="disabled")
            #end
           
            #if($daerah!="" && $daerah!="0" )
            
          <span id="socBandar_1a">
        
          
  <select name="socBandar" id="socBandar" class="autoselect" $setmode   style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandar()" >
                          <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                  #foreach($listdaerah in $listBandarbyNegeri)                                 
                                  #if($daerah!=$listdaerah.id)
              <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>                                   
                                  #end    
	                              #end 
            </select>
            </span>
            <span id="socBandar_1b">
                <input name="socBandar_dis"  type="text"  class="disabled" id="socBandar_dis" style="width: 265px; text-transform:uppercase;" value="$listDaerahbyNegeriK - $listDaerahbyNegeriN" maxlength="70" readonly />
                <input name="socBandar"  type="hidden"   id="socBandar" style="width: 265px; text-transform:uppercase;" value="$daerah" maxlength="70"  />
            </span>
            #else
          
             <span id="socBandar_2a">
             <select name="socBandar" id="socBandar" class="autoselect" $setmode style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandar()" >
              <option value="">Sila Pilih Bandar</option>              
                                  #foreach($listDaerah in $listBandarbyNegeri)
              <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
	                               #end                                  
            </select>
            </span>
            <span id="socBandar_2b">
                <input name="socBandar_dis"  type="text"  class="disabled" id="socBandar_dis" style="width: 265px; text-transform:uppercase;" value="" maxlength="70" readonly />
                <input name="socBandar"  type="hidden"   id="socBandar" style="width: 265px; text-transform:uppercase;" value="$!daerah" maxlength="70"  />
            </span>
            #end
            #end </td>
        </tr>
        
        
         
       
        
         <tr>
          <td class="style38" valign="top" >&nbsp;</td>
          <td class="style38" >No Tel (R/P)</td>
          <td>:</td>
          <td>
          <span id="no_tel_1a">
        <input name="no_tel" type="text" id="no_tel" style="text-transform:uppercase;" onBlur="uppercase()" value="$no_tel" size="14" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" $setmodeR class="$setmode" />     
        </span>
        
        <span id="no_tel_1b">
         <input name="no_tel" type="text" id="no_tel" style="text-transform:uppercase;" onBlur="uppercase()" value="$no_tel" size="14" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" readonly class="disabled" />  
        </span>
       
             </td>
        </tr>
        
        
        
        
        <tr id="tr_hp">
          <td class="style38" valign="top" >#if($setmode != "disabled") <span class="style1">*</span> #end</td>
          
          <td class="style38" >No. Tel (HP)</td>
          <td>:</td>
          <td>
          
          <span id="no_hp_1a">
        <input name="no_hp" type="text" id="no_hp" style="text-transform:uppercase;" onBlur="uppercase()" value="$no_hp" size="14" onKeyUp="javascript:validateIC(event,this,this.value,'no_hp')" maxlength="14" $setmodeR class="$setmode" />     
        </span>
        
        <span id="no_hp_1b">
         <input name="no_hp" type="text" id="no_hp" style="text-transform:uppercase;" onBlur="uppercase()" value="$no_hp" size="14" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" readonly class="disabled" />  
        </span>
       
             </td>
        </tr>
        
       
       <tr>
				<td class="style38" valign="top" >#if($setmode != "disabled") <span class="style1">*</span> #end</td>			
				<td valign="top" >Emel</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="emel" name="emel" value="$emel">
				</td>
			</tr>
        #end
      </table>
    </fieldset>
    
       <br> 
        <fieldset id="maklumat_simati" >
          <legend>MAKLUMAT SIMATI</legend>
           <div id="info_skrin_daftar_sek8_simati"></div>
         <script>
 parent.document.getElementById("info_skrin_daftar_sek8_simati").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Sila daftarkan maklumat Simati sebelum ke skrin seterusnya.</b></div>";
 </script>
          <table width="100%" border="0">
            
            #if($flagForView == 'yes')
            
            <tr> 
              <td  >&nbsp;</td>
              <td >MyID Baru</td>
              <td >:</td>
              <td > 
              #if ($idAlert == "1" || $idAlert == "2") 
                
              <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$nokpbaru1x" size="7" maxlength="6" readonly   class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati1')"  onblur="qryHowOld();check_kp();check_pengenalan_simati_1()" />
                -
			  <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$nokpbaru2x" size="3" maxlength="2" readonly class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati2')" onBlur="check_kp();check_pengenalan_simati_1()"  />
			    -
			  <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3" style="width: 40px;" type="text" value="$nokpbaru3x" size="5" maxlength="4" readonly class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')" onBlur="jantinaic();check_kp();check_pengenalan_simati_1();kp_baru_pemohon()"  />

                #else
                
                <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$noKpBaru1" size="7" maxlength="6" readonly class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati1')" onBlur="qryHowOld();check_kp();check_pengenalan_simati_1()"  />               
                -
			    <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$noKpBaru2" size="3" maxlength="2" readonly class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati2')"  onBlur="check_kp();check_pengenalan_simati_1()"  />
			                -
			    <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3" style="width: 40px;" type="text" value="$noKpBaru3" size="5" maxlength="4" readonly class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')" onBlur="jantinaic();check_kp();check_pengenalan_simati_1();kp_baru_pemohon()"  />

                #end
               
                 <input type="hidden" name="txdTarikhLahirSimati" id="txdTarikhLahirSimati" value=""  />
                 
				 <input id="txtUmurSimati" name="txtUmurSimati" type="hidden" value="$!umursimati"  />
				 <input name="socJantinaSimati" type="hidden" value="$!jantinasimati" />
				 <input id="txtUmurPemohon" name="txtUmurPemohon" type="hidden" value="$!umurpemohon"  />
				 <input name="socJantinaPemohon" type="hidden" value="$!jantinapemohon" /> 
				     
				 <input name="check_no_kp_baru_simati" id="check_no_kp_baru_simati" type="hidden"  />   
				 <input name="check_no_kp_lama_simati" id="check_no_kp_lama_simati" type="hidden"  />  
				 <input name="check_no_kp_lain_simati" id="check_no_kp_lain_simati" type="hidden"  />  
				 
				 <input name="check_no_kp_baru_pemohon" id="check_no_kp_baru_pemohon" type="hidden"  />   
				 <input name="check_no_kp_lama_pemohon" id="check_no_kp_lama_pemohon" type="hidden"  />  
				 <input name="check_no_kp_lain_pemohon" id="check_no_kp_lain_pemohon" type="hidden"  />  
				 
				 <input name="flag_dup_p1" id="flag_dup_p1"  type="hidden" />
				<input name="flag_dup_p2" id="flag_dup_p2" type="hidden" />
				<input name="flag_dup_p3" id="flag_dup_p3"  type="hidden" />
			      <!-- #if($!skrin_online != "yes" && $!skrin_online_17 != "yes") -->    
			
			    #if ($setmode != "disabled" )
			  <span class="style36"> <a href="http://www.jpn.gov.my" target="_blank" class="style51"> www.jpn.gov.my</a></span>
			  #end 
			  <!-- #end -->
			   </td>
			            </tr>
			            
			            
            <tr>
              <td>&nbsp;</td>
              <td>MyID Lama</td>
              <td>:</td>
              <td> #if ($idAlert == "1" || $idAlert == "2") 
                
                <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLamax"  onblur="this.value=this.value.toUpperCase();check_kp_lama();check_pengenalan_simati_2();kp_lama_pemohon()" readonly class="disabled" maxlength="8" size="15" style="width: 120px; text-transform:uppercase;"/>
             
                #else
                <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLama"  onblur="this.value=this.value.toUpperCase();check_kp_lama();check_pengenalan_simati_2();kp_lama_pemohon()" readonly class="disabled" maxlength="8" size="15" style="width: 120px; text-transform:uppercase;"/>
               
                #end </td>
            </tr>
            
            <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top">MyID Lain</td>
              <td valign="top">:</td>
              <td>
              
               #if($setmode == "disabled")
               #set($setmodeR = "readonly") 
                #else
               #set($setmodeR = "")
               #end
              
              
               #if($flagForView  == 'yes')
               
           
               
               #if($jenisKpMati == 4)
               #set($jkpm = "NO PASPORT")
               #elseif($jenisKpMati == 5)
               #set($jkpm = "NO TENTERA")               
               #elseif($jenisKpMati == 6)
               #set($jkpm = "NO POLIS")
               #elseif($jenisKpMati == "7")
               #set($jkpm = "LAIN-LAIN")               
               #else
               #set($jkpm = "")
               #end
                 
               <input  name="socJenisKPLainSimatiX"  id="socJenisKPLainSimatiX"  value="$jkpm" readonly class="disabled"  />
                 <input  name="socJenisKPLainSimati" id="socJenisKPLainSimati"  value="$jenisKpMati"  type="hidden" readonly class="disabled"  />
             
               
                        
               #else
             
               <select name="socJenisKPLainSimati" id="socJenisKPLainSimati" $setmode style="width: 110px; text-transform:uppercase;" onChange="kplain(this.value)"  onblur="kplainX(this.value)" />
            #if ($idAlert == "1" || $idAlert == "2") 
            #set ($selected = "")
             <option value="0">Sila Pilih KP</option>
                #foreach($Listkp in $listkp)
                     #if ($Listkp.id == $jenisKpMati)
                     	#set ($selected = "selected")
                     <option value="$Listkp.id" $selected/>$Listkp.keterangan</option>
                     #end
                     <option value="$Listkp.id" />$Listkp.keterangan</option>
                #end
            #else
                #if ($idFlag == "2")
                    #foreach($Listkp in $listkp)
                    	#if ($Listkp.id == $jenisKpMati)
                    		#set ($selected = "selected")
                    <option value="$Listkp.id" $selected/>$Listkp.keterangan</option>
                    	#end
                    #end
                    <option value="0" />Sila Pilih KP</option>
                     #foreach($Listkp in $listkp)
                    <option value="$Listkp.id" />$Listkp.keterangan</option>
                    #end
                #else
                	<option value="0" />Sila Pilih KP</option>
                    #foreach($Listkp in $listkp)
                        <option value="$Listkp.id"/>
                        $Listkp.keterangan</option>
                    #end
                #end
            #end

            </select>
            #end
            
            
            
           
            #if ($idAlert == "1" || $idAlert == "2") 
            
            
            
            #if($jenisKpMati=="" || $jenisKpMati=="0")            
            #if($setmode=="disabled")
            #set($setmod="disabled")
            #set($setmodR = "readonly")
            #else             
            #set($setmod="")
            #set($setmodR = "disabled")            
            #end
            #else             
            #set($setmod="")
            #set($setmodR = "")
            #end
            <input name="txtNoKPLainSimati" type="text" class="disabled" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();check_kp_lain();check_pengenalan_simati_3();kp_lain_pemohon()" value="$noKpLainx" readonly />                
           
            #else
            
             #if($setmode=="disabled")
             
             #set($setmod="disabled")
             #set($setmodR = "readonly")
                           
             #else   
                           
             #if($jenisKpMati=="" || $jenisKpMati=="0")
             #set($setmod="")
             #set($setmodR = "disabled")
             #else
             #set($setmod="")
             #set($setmodR = "")             
             #end             
             #end
            
       <input name="txtNoKPLainSimati" type="text" class="disabled" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();check_kp_lain();check_pengenalan_simati_3();kp_lain_pemohon()" value="$noKpLain" readonly />
            #end 
            </td>
            </tr>
            
            #else
            <tr>
              <td valign="top" width="2%">#if($setmode != "disabled")
             <span class="style1">*</span>
              #end</td>
              <td >MyID Baru</td>
              <td >:</td>
              <td > 
              
              #if ($idAlert == "1" || $idAlert == "2") 
                
                <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$nokpbaru1x" size="7" maxlength="6" $setmodeR class="$setmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati2')"  onblur="qryHowOld();check_kp();check_pengenalan_simati_1()" />
                -
  <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$nokpbaru2x" size="3" maxlength="2" $setmodeR class="$setmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')" onBlur="check_kp();check_pengenalan_simati_1()"  />
                -
  <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3"  style="width: 40px;" type="text" value="$nokpbaru3x" $setmodeR class="$setmode" size="5" maxlength="4"  onblur="jantinaic();check_kp();check_pengenalan_simati_1();kp_baru_pemohon()" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  /> <span id="check_kp_1" style="color:red" ><input name='no_kp1' type='hidden' value='' /></span>  
 
  
                #else
                
                <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$noKpBaru1" size="7" maxlength="6" $setmodeR class="$setmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati2')" onBlur="qryHowOld();check_kp();
                )"  />               
                -
  <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$noKpBaru2" size="3" maxlength="2" $setmodeR class="$setmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  onBlur="check_kp();check_pengenalan_simati_1()"  />
                -
  <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3"  style="width: 40px;" type="text" value="$noKpBaru3" $setmodeR class="$setmode" size="5" maxlength="4" onBlur="jantinaic();check_kp();check_pengenalan_simati_1();kp_baru_pemohon()" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  />
  <span id="check_kp_1" style="color:red" ><input name='no_kp1' type='hidden' value='' /></span>
               
             
                #end
               
                  <input type="hidden" name="txdTarikhLahirSimati" id="txdTarikhLahirSimati" value=""  />
                 
 <input id="txtUmurSimati" name="txtUmurSimati" type="hidden" value="$!umursimati"  />
 <input name="socJantinaSimati" type="hidden" value="$!jantinasimati" />
 <input id="txtUmurPemohon" name="txtUmurPemohon" type="hidden" value="$!umurpemohon"  />
 <input name="socJantinaPemohon" type="hidden" value="$!jantinapemohon" /> 
     
 <input name="check_no_kp_baru_simati" id="check_no_kp_baru_simati" type="hidden"  />   
 <input name="check_no_kp_lama_simati" id="check_no_kp_lama_simati" type="hidden"  />  
 <input name="check_no_kp_lain_simati" id="check_no_kp_lain_simati" type="hidden"  />  
 
 <input name="check_no_kp_baru_pemohon" id="check_no_kp_baru_pemohon" type="hidden"  />   
 <input name="check_no_kp_lama_pemohon" id="check_no_kp_lama_pemohon" type="hidden"  />  
 <input name="check_no_kp_lain_pemohon" id="check_no_kp_lain_pemohon" type="hidden"  />  
 
 <input name="flag_dup_p1" id="flag_dup_p1"  type="hidden" />
<input name="flag_dup_p2" id="flag_dup_p2" type="hidden" />
<input name="flag_dup_p3" id="flag_dup_p3"  type="hidden" />
  
    
 
          
  #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
    #if ($setmode != "disabled" )
  <span class="style36"> <a href="http://www.jpn.gov.my" target="_blank" class="style51"> www.jpn.gov.my</a></span>
  #end 
  
  #end
   </td>
            </tr>
            
            
   
            
            
            
            <tr>
              <td>&nbsp;</td>
              <td>MyID Lama</td>
              <td>:</td>
              <td> #if ($idAlert == "1" || $idAlert == "2") 
                
                <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLamax"  onblur="this.value=this.value.toUpperCase();check_kp_lama();check_pengenalan_simati_2();kp_lama_pemohon()" $setmodeR class="$setmode" maxlength="8" size="15" style="width: 120px; text-transform:uppercase;"/>
                <span id="check_kp_2" style="color:red" ><input name='no_kp2' type='hidden' value='' /></span>
                #else
                <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLama"  onblur="this.value=this.value.toUpperCase();check_kp_lama();check_pengenalan_simati_2();kp_lama_pemohon()" $setmodeR class="$setmode" maxlength="8" size="15" style="width: 120px; text-transform:uppercase;"/>
                <span id="check_kp_2" style="color:red" ><input name='no_kp2' type='hidden' value='' /></span>
                #end </td>
            </tr>
            
            
            <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top">MyID Lain</td>
              <td valign="top">:</td>
              <td>
               #if($setmode == "disabled")
               #set($setmodeR = "readonly") 
               
               #if($jenisKpMati == 4)
               #set($jkpm = "NO PASPORT")
               #elseif($jenisKpMati == 5)
               #set($jkpm = "NO TENTERA")
               #elseif($jenisKpMati == 6)
               #set($jkpm = "NO POLIS")
               #elseif($jenisKpMati == 13)
               #set($jkpm = "LAIN-LAIN")
               #else
               #set($jkpm = "")
               #end
                 
               <input  name="socJenisKPLainSimati" class="$setmode" id="socJenisKPLainSimati"  value="$jkpm" $setmodeR  />
             
               
                        
               #else
               #set($setmodeR = "")
              
               <select name="socJenisKPLainSimati" id="socJenisKPLainSimati" $setmode style="width: 110px; text-transform:uppercase;" onChange="kplain(this.value)"  onblur="kplainX(this.value)" />
            #if ($idAlert == "1" || $idAlert == "2") 
            #set ($selected = "")
             <option value="0">Sila Pilih KP</option>
                #foreach($Listkp in $listkp)
                     #if ($Listkp.id == $jenisKpMati)
                     	#set ($selected = "selected")
                     <option value="$Listkp.id" $selected/>$Listkp.keterangan</option>
                     #end
                     <option value="$Listkp.id" />$Listkp.keterangan</option>
                #end
            #else
                #if ($idFlag == "2")
                    #foreach($Listkp in $listkp)
                    	#if ($Listkp.id == $jenisKpMati)
                    		#set ($selected = "selected")
                    <option value="$Listkp.id" $selected/>$Listkp.keterangan</option>
                    	#end
                    #end
                    <option value="0" />Sila Pilih KP</option>
                     #foreach($Listkp in $listkp)
                    <option value="$Listkp.id" />$Listkp.keterangan</option>
                    #end
                #else
                	<option value="0" />Sila Pilih KP</option>
                    #foreach($Listkp in $listkp)
                        <option value="$Listkp.id"/>
                        $Listkp.keterangan</option>
                    #end
                #end
            #end

            </select>
            #end
            
            
            
           
            #if ($idAlert == "1" || $idAlert == "2") 
            
            
            
            #if($jenisKpMati=="" || $jenisKpMati=="0")            
            #if($setmode=="disabled")
            #set($setmod="disabled")
            #set($setmodR = "readonly")
            #else             
            #set($setmod="")
            #set($setmodR = "disabled")            
            #end
            #else             
            #set($setmod="")
            #set($setmodR = "")
            #end
            
            
            	<input name="txtNoKPLainSimati" type="text" class="$setmod" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();check_kp_lain();check_pengenalan_simati_3();kp_lain_pemohon()" value="$noKpLainx" maxlength="30" $setmodR />
                <span id="check_kp_3" style="color:red" ><input name='no_kp3' type='hidden' value='' /></span>
            #else
            
             #if($setmode=="disabled")
             
             #set($setmod="disabled")
             #set($setmodR = "readonly")
                           
             #else   
                           
             #if($jenisKpMati=="" || $jenisKpMati=="0")
             #set($setmod="")
             #set($setmodR = "disabled")
             #else
             #set($setmod="")
             #set($setmodR = "")             
             #end             
             #end
            
       <input name="txtNoKPLainSimati" type="text" class="$setmod" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();check_kp_lain();check_pengenalan_simati_3();kp_lain_pemohon()" value="$noKpLain" maxlength="30" $setmodR />
            <span id="check_kp_3" style="color:red" ><input name='no_kp3' type='hidden' value='' /></span> 
            #end              </td>
            </tr>
            #end            
            
            <tr>
              <td valign="top" width="2%">#if($setmode != "disabled")
             <span class="style1">*</span>
              #end</td>
              <td width="28%">Nama Simati</td>
              <td width="1%">:</td>
              <td width="69%">
              
              
               #if ($idAlert == "1" || $idAlert == "2")
                
                <input name="txtNamaSimati" type="text" class="$setmode" id="txtNamaSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$namaSimatix" size="50" maxlength="200" $setmodeR/>
                #else
                
                <input name="txtNamaSimati" type="text" class="$setmode" id="txtNamaSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$namaSimati" size="50" maxlength="200" $setmodeR/>
                #end</td>
            </tr>
            
            
            <tr>
              <td valign="top">
              #if($setmode != "disabled")<span class="style1">*</span>#end
              </td>
              <td>
             
              Tarikh Mati
             
              </td>
              <td>:</td>
              <td>
              #if ($idAlert == "1" || $idAlert == "2")
              	 <input name="txtTarikhMati" id="txtTarikhMati" type="text" style="width: 80px;" value="$tarikhMatix" size="11" maxlength="10" $setmodeR class="$setmode" onFocus="qryHowOld()" onBlur="trans_date(this.value);qryHowOld()"  />
              #else
              		<input name="txtTarikhMati" id="txtTarikhMati" type="text" style="width: 80px;" value="$tarikhMati" size="11" maxlength="10" $setmodeR class="$setmode"  onfocus="qryHowOld()" onBlur="trans_date(this.value);qryHowOld()" />
              #end
              #if ($setmode != "disabled" )
              		<a href="javascript:displayDatePicker('txtTarikhMati',false,'dmy');"/>#parse("app/ppk/ppk_calender.jsp")</a>
                    <em><span class="style3 style4 style2">dd/mm/yyyy</span></em>#end               </td>
            </tr>
            #if ($SimpanStatus != "2")
          #end
          
          <tr>
             <td valign="top" width="2%">#if($setmode != "disabled")
             <span class="style1">*</span>
              #end</td>
             <td width="28%"> Muatnaik MyID </td>
             <td width="1%">:</td> 
             <td width="9%">
             <input id="fileupload" name="fileupload" type="file" size="40">
             </td>
             </tr>
             
            <tr>
     <td valign="top" width="2%">#if($setmode != "disabled")
             <span class="style1">*</span>
              #end</td>
     <td width="28%"> Muatnaik Sijil Mati </td>
     <td width="1%">:</td> 
     <td width="9%">
     <input id="fileupload" name="fileupload" type="file" size="40">
     </td>
      </tr>
          </table>
        </fieldset></td>
      </tr>
    </table></td>
    
  
    <td >
    </td>
  </tr>
</table>
#if ($setmode != "disabled" && $no_fail_online == "" )
<table>

 <tr>
    <td>
   
    <span class="perhatian style1 style3"><em>Perhatian</em></span><span class="style3 style50"><em> : Sila masukkan salah satu nombor MyID dan pastikan label bertanda <span class="style1">*</span> diisi.</em></span>    </td>
  </tr>
</table>

#end

<table width="100%" border="0">
  <tr align="center">
    <th scope="col">
   	#if ($EventStatus == 0)
     <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="Simpan()"/>
     <!--
     <input type="button" name="cdmBatal" id="cdmBatal" value="Batal" onClick="Batal()"/>
     <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">
     -->
    
      #end</th>
  </tr>
</table>

<fieldset id="tableReport" style="display:none;" > 
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td ><a href="#" class="style2 " onClick="javascript:cetakDokumen('$IdPermohonan','PPKSemakanSek8')"> Senarai Semak </a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2 " onClick="javascript:cetak('$noFail','$idFail')"> Kulit Fail </a></td>
      </tr>
      <tr>
        <td > <a href="#" class="style2 " onClick="javascript:cetakSuratAkuanTerima('$noFail')">Surat Akuan Terima</a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2 " onClick="javascript:cetakSuratIringanBorangB('$noFail')">Surat Iringan Borang B</a></td>
      </tr>
       <tr>
      <td >
    
      #parse("app/ppk/SuratMintaMaklumat.jsp")
      </td>
      </tr>
      
      #set($NoFail = $noFail)
      
       <tr>
        <td ><a href="#" class="style2 " onClick="javascript:cetakBorangB('$NoFail','$idFail','$flagFromSenaraiFailSek8','$flagForView')">Borang B (Cetakan Secara Portrait)</a></td>
      </tr>
      
        <tr>
        <td ><a href="#" class="style2 " onClick="javascript:cetakBorangB2('$NoFail','$idFail','$flagFromSenaraiFailSek8','$flagForView')">Borang B (Cetakan Secara Landskap)</a></td>
      </tr>
        <tr>
        <td ><a href="#" class="style2 " onClick="javascript:cetakDokumen('$IdPermohonan','RPF')">Rekod Pendaftaran Fail</a></td>
      </tr>      
    </table>
</fieldset> 
<!--
<p align="right">CL - 01 - 64</p>
-->
</fieldset>

<table width="100%" style="display:none">
<tr>
<td>

  
<div align="right">
#if($pendaftaran == "")
#if ($flagFromSenaraiFailSek8 == '' && $flagForView  == '' && $flagFromSenaraiPermohonanSek8 == '' && $pendaftaran == "")<a href="javascript:javascript:Kembali()" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if ($flagFromSenaraiFailSek8 == 'yes')<a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagForView  == 'yes')<a href="javascript:ForView('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style2"  ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style2"  ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3current.png" alt="" border="0" title="Pendaftaran Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>#if ($noFail != "")<a href="javascript:seterusnya_tab()" class="style2"  ><img src="../img/4enable2.png" alt="" border="0" title="Maklumat Permohonan"/></a>#else<img src="../img/4disable2.png" alt="" border="0" title="Maklumat Permohonan"/>#end
</span>
#else
#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style2"  ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style2"  ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/2current.png" alt="" border="0" title="Pendaftaran Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>#if ($noFail != "")<a href="javascript:seterusnya_tab()" class="style2"  ><img src="../img/3enable2.png" alt="" border="0" title="Maklumat Permohonan"/></a>#else<img src="../img/3disable.png" alt="" border="0" title="Maklumat Permohonan"/>#end
</span>
#end
</div>
</td>
</tr>
</table>
<input type="hidden" name="command" />
<input type="hidden" name="mode" />
<input type="hidden" name="idAlert" />
<input type="hidden" name="idFlag" />
<input type="hidden" name="flagno" />
<input type="hidden" name="idFail" value="$IdFail"/>
<input type="hidden" name="idtemp" value="$IdPermohonan" />
<input type="hidden" name="idPermohonan" value="$IdPermohonan" />
<input type="hidden" name="id_permohonan" value="$IdPermohonan" />
<input type="hidden" name="negid" value="$NegId" />
<input type="hidden" name="eventStatus" value="$EventStatus" />
<input name="idSimati" type="hidden" value="$IdSimati"/>
<input name="idPemohon" type="hidden" value="$IdPemohon"/>

<input name="txtNomborResit1" type="hidden" value="$txtNomborResit1"/>
<input name="txdTarikhByrn1" type="hidden" value="$txdTarikhByrn1"/>
<input name="tahun" type="hidden" value="">
 #parse("app/ppk/headerppk_script.jsp")
</form>
</body>
<script>

//selectPelbagaiNegara('$negeri','div_mesejpelbagagainegara','tr_pelbagainegara','nama_pelbagainegara');

function Batal() {

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method = "POST";
    document.f1.command.value="Kemaskini_daftar_pemohon";
	document.f1.idFlag.value="1";
	document.f1.flagno.value="2";
	document.f1.action = "";
	document.f1.submit();
	}
	else
	{
	return;
	}
}
function kembalix() {
	document.f1.method = "POST";
	document.f1.command.value="papar";
	document.f1.action = "";
	document.f1.submit();
}
function seterusnya_tab() {
	document.f1.method = "POST";
	//document.f1.command.value="seterusnya";
	document.f1.command.value="Simati";
	document.f1.mode.value="Simatiview";
	document.f1.action = "";
	document.f1.submit();
}
function Kemaskini() {
		//document.f1.method = "POST";
		document.f1.command.value="Kemaskini_daftar_pemohon";
		document.f1.action = "";
		document.f1.submit();
}
/*


var now = new Date();
var yesterday = new Date(2005,09,13,00,00,00);
var tomorrow= new Date(2005,09,15,00,00,00);

alert('now : ' + now + '\nyesterday : '  +  yesterday + '\ntomorrow : '+ tomorrow);

alert((yesterday - now)+ '\n' + (tomorrow - now))

if((yesterday  - now)<0){

    alert('date tested as before now');

}



///////////


   var str1  = document.getElementById("Fromdate").value;
   var str2  = document.getElementById("Todate").value;
   var dt1   = parseInt(str1.substring(0,2),10);
   var mon1  = parseInt(str1.substring(3,5),10);
   var yr1   = parseInt(str1.substring(6,10),10);
   var dt2   = parseInt(str2.substring(0,2),10);
   var mon2  = parseInt(str2.substring(3,5),10);
   var yr2   = parseInt(str2.substring(6,10),10);
   var date1 = new Date(yr1, mon1, dt1);
   var date2 = new Date(yr2, mon2, dt2);

   if(date2 < date1)
   {
      alert("To date cannot be greater than from date");
      return false;
   }
   else
   {
      alert("Submitting ...");
      document.form1.submit();
   } 


*/

function checkSumaICsimati(command)
{
	//alert('MASUK checkSumaICsimati');
	if(command == "getBandar")
	{
		check_pengenalan_simati_1();
		check_pengenalan_simati_2();
		check_pengenalan_simati_3();
	}
}

function Simpan() {
//alert("Simpan");
	/*	
	check_pengenalan_simati_1();
	check_pengenalan_simati_2();
	check_pengenalan_simati_3();
	*/
	
	var str1  = document.getElementById("txtTarikhMati").value;
	
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);

	var currentTime = new Date();
	var date1 = new Date(yr1, mon1, dt1);
	
	if (document.f1.taraf_penting.value == "0" || document.f1.taraf_penting.value == "") {
		alert("Sila pilih Taraf Kepentingan");
		document.f1.taraf_penting.focus();
	}
	else if (document.f1.taraf_penting.value == "6" && document.f1.jenis_pej1.value == "") {
		alert("Sila pilih Amanah Raya");
		document.f1.jenis_pej1.focus();
	}
	else if (document.f1.taraf_penting.value == "8" && document.f1.jenis_pej2.value == "") {
		alert("Sila pilih Baitulmal");
		document.f1.jenis_pej2.focus();
	}
	else if (document.f1.taraf_penting.value == "1" && document.f1.socSaudaraWaris.value == "") {
		alert("Sila pilih Talian Persaudaraan Dengan Simati");
		document.f1.socSaudaraWaris.focus();
	}
	else if ((document.f1.txtNoKPBaruPemohon1.value=="" && document.f1.txtNoKPBaruPemohon2.value=="" && document.f1.txtNoKPBaruPemohon3.value=="" && document.f1.txtNoKPLamaPemohon.value=="" && document.f1.txtNoKPLainPemohon.value=="") && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila masukkan salah satu MyID Pemohon");
		document.f1.txtNoKPBaruPemohon1.focus();
	}
	else if ((document.f1.socJenisKPLainPemohon.value!="0" && document.f1.txtNoKPLainPemohon.value=="") && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila masukkan MyID Lain Pemohon");
		document.f1.txtNoKPLainPemohon.focus();
	}
	else if (document.f1.txtNamaPemohon[0] != 'null' && document.f1.txtNamaPemohon[0].value=="" && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8") ) {
		alert("Sila masukkan nama pemohon.");
		document.f1.txtNamaPemohon[0].focus();
	}
	else if (document.f1.txtAlamat1[0].value == "" && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8")){
		alert("Sila masukkan alamat tetap");
		document.f1.txtAlamat1.focus();
	}
	else if (document.f1.txtPoskod[0].value == "" && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8")){
		alert("Sila masukkan poskod");
		document.f1.txtPoskod.focus();
	}
	
	else if (document.f1.socNegeri[0].value == "" && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8")){
		alert("Sila pilih negeri");
		document.f1.socNegeri.focus();
	}
	else if (document.f1.socBandar[0].value == "" && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8")){
		alert("Sila pilih bandar");
		document.f1.socBandar.focus();
	}
	else if (document.f1.txtPoskod[0] != 'null' && document.f1.txtPoskod[0].value != "" && document.f1.txtPoskod[0].value.length < 5 && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8") ) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskod[0].focus();
	}
	else if (document.f1.txtPoskod[1] != 'null' && document.f1.txtPoskod[1].value != "" && document.f1.txtPoskod[1].value.length < 5 && (document.f1.taraf_penting.value == "6" || document.f1.taraf_penting.value == "8") ) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskod[1].focus();
	}
	else if (document.f1.no_hp[0].value == "" && (document.f1.no_hp.value != "6" && document.f1.taraf_penting.value != "8")){
		alert("Sila masukkan No. Tel (HP)");
		document.f1.no_hp.focus();
	}
   /*if (document.f1.no_hp[0].value == "") {
		alert("Sila masukkan No. Tel (HP)");
		document.f1.no_hp[0].focus();
	}*/
	
	else if (document.f1.emel.value == "" ) {
		alert("Sila masukkan Emel");
		document.f1.emel.focus();
	}
	
	
	else if(document.f1.no_kp1.value == 'yes')
	{
		alert("No kp baru simati telah wujud!");	 
	} 
	 else if(document.f1.no_kp2.value == 'yes')
	{
		alert("No kp lama simati telah wujud!");	 
	}
	 else if(document.f1.no_kp3.value == 'yes')
	{
		alert("No kp lain simati telah wujud!");	 
	}
	
	/*
	else if(document.f1.online_skrin.value != "" && document.f1.online_skrin.value == 'yes')
	{
		alert("No kp baru simati telah wujud!");	 
	} 
	 else if(document.f1.txtNoKPLamaSimati != null && document.f1.txtNoKPLamaSimati.value == 'yes')
	{
		alert("No kp lama simati telah wujud!");	 
	}
	 else if(document.f1.txtNoKPLainSimati != null && document.f1.txtNoKPLainSimati.value == 'yes')
	{
		alert("No kp lain simati telah wujud!");	 
	}
	*/
	
	
	else if (isNaN(document.f1.txtNoKPBaruSimati1.value)) {
		alert("Sila masukkan nombor sahaja");
		document.f1.txtNoKPBaruSimati1.focus();
	}
	else if (isNaN(document.f1.txtNoKPBaruSimati2.value)) {
		alert("Sila masukkan nombor sahaja");
		document.f1.txtNoKPBaruSimati2.focus();
	}
	else if (isNaN(document.f1.txtNoKPBaruSimati3.value)) {
		alert("Sila masukkan nombor sahaja");
		document.f1.txtNoKPBaruSimati3.focus();
	}
	else if (document.f1.txtNoKPBaruSimati1.value =="" && document.f1.txtNoKPBaruSimati2.value=="" && document.f1.txtNoKPBaruSimati3.value=="" && document.f1.txtNoKPLamaSimati.value=="" && document.f1.socJenisKPLainSimati.value=="0" && document.f1.txtNoKPLainSimati.value=="") {
		alert("Sila masukkan salah satu MyID Simati");
	}
	
	
	
	else if (document.f1.txtNoKPBaruSimati1.value !="" && document.f1.txtNoKPBaruSimati1.value.length<6) {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati1.focus();
	}
	else if (document.f1.txtNoKPBaruSimati2.value !="" && document.f1.txtNoKPBaruSimati2.value.length<2) {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati2.focus();
	}
	else if (document.f1.txtNoKPBaruSimati3.value !="" && document.f1.txtNoKPBaruSimati3.value.length<4) {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati3.focus();
	}
	else if (document.f1.txtNoKPBaruSimati1.value !="" && document.f1.txtNoKPBaruSimati2.value=="" && document.f1.txtNoKPBaruSimati3.value=="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati2.focus();
	}
	else if (document.f1.txtNoKPBaruSimati1.value !="" && document.f1.txtNoKPBaruSimati2.value!="" && document.f1.txtNoKPBaruSimati3.value=="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati3.focus();
	}
	else if (document.f1.txtNoKPBaruSimati1.value !="" && document.f1.txtNoKPBaruSimati2.value=="" && document.f1.txtNoKPBaruSimati3.value!="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati2.focus();
	}
	else if (document.f1.txtNoKPBaruSimati2.value!="" && document.f1.txtNoKPBaruSimati1.value=="" && document.f1.txtNoKPBaruSimati3.value!="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati1.focus();
	}
	else if (document.f1.txtNoKPBaruSimati2.value!="" && document.f1.txtNoKPBaruSimati1.value=="" &&  document.f1.txtNoKPBaruSimati3.value=="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati1.focus();
	}	
	else if (document.f1.txtNoKPBaruSimati1.value=="" &&  document.f1.txtNoKPBaruSimati2.value=="" && document.f1.txtNoKPBaruSimati3.value!="") {
		alert("Sila masukkan MyID Baru Simati dengan lengkapnya");
		document.f1.txtNoKPBaruSimati1.focus();
	}
	else if(document.f1.txtNoKPLamaSimati.value != "" && document.f1.txtNoKPLamaSimati.value != "TDK" && document.f1.txtNoKPLamaSimati.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Simati");
		document.f1.txtNoKPLamaSimati.focus();	
	}
	else if ((document.f1.socJenisKPLainSimati.value!="0"  && document.f1.socJenisKPLainSimati.value!="") && document.f1.txtNoKPLainSimati.value=="") {
		alert("Sila masukkan MyID Lain Simati");
		document.f1.txtNoKPLainSimati.focus();
	}
	else if (document.f1.txtNoKPLainSimati.value!="" && document.f1.socJenisKPLainSimati.value=="0") {
		alert("Sila pilih jenis MyID Lain Simati");
	}	
	else if (document.f1.txtNamaSimati != 'null' && document.f1.txtNamaSimati.value=="" 
			//&& (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8") 
			) {
		alert("Sila masukkan nama simati");
		document.f1.txtNamaSimati.focus();
	}
 	
	else if (document.f1.txtTarikhMati != 'null' && document.f1.txtTarikhMati.value=="" 
			//&& (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8") 
			) {
		alert("Sila masukkan tarikh mati");
		document.f1.txtTarikhMati.focus();
	}
	else if (date1 > currentTime){
		alert("Sila pastikan tarikh mati tidak melebihi dari tarikh hari ini.");
		document.f1.txtTarikhMati.focus();
	}
//   else if(document.f1.uploadmyid != 'null' && document.f1.uploadmyid.value==""){
//   	  alert('Sila muatnaik MyID simati.');
//       document.f1.uploadmyid.focus(); 
//    }
//   else if(document.f1.uploadsijil != 'null' && document.f1.uploadsijil.value==""){
//    	  alert('Sila muatnaik sijil simati.');
//       document.f1.uploadsijil.focus();
// 	}
	
	else{
		//alert("TING");
		var data = "&idPermohonan="+idPermohonan.value+"&txtNoKPBaruSimati1="+txtNoKPBaruSimati1.value+"&txtNoKPBaruSimati2="+txtNoKPBaruSimati2.value+"&txtNoKPBaruSimati3="+txtNoKPBaruSimati3.value+"&txdTarikhMohon="+txdTarikhMohon.value+"&txtNamaSimati="+txtNamaSimati.value+"&txtNoKPLamaSimati="+txtNoKPLamaSimati.value+"&socJenisKPLainSimati="+socJenisKPLainSimati.value+"&txtNoKPLainSimati="+txtNoKPLainSimati.value;
		
		// "&id_fail_carian="+id_fail+"&txtNoFailSub="+txtNoFailSub.value+"&id_ob_pemohon="+id_ob_pemohon+"&id_permohonansimati_atheader="+id_permohonansimati_atheader+"&sebab="+sebab2;
		
		var data2 = data + "&txtTarikhMati="+txtTarikhMati.value+"&txtNoKPBaruPemohon1="+txtNoKPBaruPemohon1.value+"&txtNoKPBaruPemohon2="+txtNoKPBaruPemohon2.value+"&txtNoKPBaruPemohon3="+txtNoKPBaruPemohon3.value+"&txtNoKPLamaPemohon="+txtNoKPLamaPemohon.value+"&txtNoKPLainPemohon="+txtNoKPLainPemohon.value+"&txtNamaPemohon="+txtNamaPemohon.value+"&txtAlamat1="+txtAlamat1.value; //&socJenisKPLainPemohon="+socJenisKPLainPemohon.value+"
		//alert("TING TONG");
		var data3 = data2+"&txtAlamat2="+txtAlamat2.value+"&txtAlamat3="+txtAlamat3.value+"&negid="+negid.value+"&socDaerah="+socDaerah.value+"&socBandar="+socBandar.value+"&txtPoskod="+txtPoskod.value+"&socNegeri="+socNegeri.value+"&txtUmurSimati="+txtUmurSimati.value+"&socJantinaSimati="+socJantinaSimati.value+"&txtUmurPemohon="+txtUmurPemohon.value+"&socJantinaPemohon="+socJantinaPemohon.value+"&taraf_penting="+taraf_penting.value+"&no_tel="+no_tel.value+"&nama_pelbagainegara="+nama_pelbagainegara.value+"&no_hp="+no_hp.value+"&jenis_pej="+jenis_pej.value+"&jenis_pemohon="+jenis_pemohon.value+"&emel="+emel.value+"&socSaudaraWaris="+socSaudaraWaris.value;
		//alert("TING TING TONG");
		var command = "&command=Simpanx";
		var actionItem = (command+data3);
		alert(actionItem);
		document.f1.enctype = "multipart/form-data";
	  document.f1.encoding = "multipart/form-data";
		//alert(document.f1.idPermohonan.value);
		input_box = confirm("Adakah anda pasti?");
		
		
		if (input_box == true) {
		//document.f1.method = "POST";
		//var actionItem = ("&command=Simpanx");
		//document.f1.command.value="Simpanx";
		document.f1.eventStatus.value="1";
		//document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon"+actionItem; 
		document.f1.action = "?_portal_module=${modul}&ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon"+actionItem;
		//document.f1.action = "";
		document.f1.submit();
		}
	}
}


function kp1()
{

document.f1.kp1.value = document.f1.txtNoKPBaruSimati1.value;
}
function kp2()
{

document.f1.kp2.value = document.f1.txtNoKPBaruSimati2.value;
}
function kp3()
{

document.f1.kp3.value = document.f1.txtNoKPBaruSimati3.value;
}


function kembalidaftar()
{
        document.f1.command.value="kembali_daftar_pemohon";
		document.f1.eventStatus.value="1";
		document.f1.action = "";
		document.f1.submit();
}

function jantinaic()
{
var ch=document.f1.txtNoKPBaruSimati3.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaSimati.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaSimati.value = 1 ;

}

return;
}


function jantinaic1()
{
var ch=document.f1.txtNoKPBaruPemohon3.value.charAt(3);

if(ch%2 == 0)
{
document.f1.socJantinaPemohon.value = 2 ;


}
if(ch%2 != 0)
{
document.f1.socJantinaPemohon.value = 1 ;

}

return;
}



function kplain(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainSimati.disabled = false;
document.f1.txtNoKPLainSimati.value = "";
//document.f1.txtNoKPLainSimati.focus();
return;
}
else
{
document.f1.txtNoKPLainSimati.disabled = true;
document.f1.txtNoKPLainSimati.value = "";
return;
}
}


function kplainX(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainSimati.focus();
return;
}

}





function kplain1(val)
{
if(val!="0" && val!="")
{
document.f1.txtNoKPLainPemohon.disabled = "";
document.f1.txtNoKPLainPemohon.value = "";

document.f1.txtNoKPLainPemohon.focus();




return;
}
else
{
document.f1.txtNoKPLainPemohon.disabled = "disabled";
document.f1.txtNoKPLainPemohon.value = "";
return;
}



}

function kplain1X(val)
{
if(val!="0" && val!="")
{

document.f1.txtNoKPLainPemohon.focus();



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

function isIc(dtStr){
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
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}


function cetak(noFail,idfail) {
    var url = "../servlet/ekptg.report.ppk.KulitFail?NoFail="+noFail+"&idfail="+idfail+"";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}



function cetakSuratAkuanTerima(noFail) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=suratAkuanTerima&flagReport=S";
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratIringanBorangB(noFail) {
var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratIringanBorangB&flagReport=S";
 //   var url = "../servlet/ekptg.report.ppk.SuratIringanBorangB?nofail="+noFail;
    var hWnd = window.open(url,'Cetak','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakMaklumatTambahan(noFail) {
var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratMaklumatTambahan&flagReport=S";
 //   var url = "../servlet/ekptg.report.ppk.SuratIringanBorangB?nofail="+noFail;
    var hWnd = window.open(url,'Cetak','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}





function cetakBorangB(noFail,idfail,flag,flagB) {
    var url = "../servlet/ekptg.report.ppk.BorangB2?nofail="+noFail+"&idfail="+idfail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


if(flag != "yes" && flagB != "yes")
	{
	    document.f1.command.value="getDaftarStatus";
	//	document.f1.mode.value="getHtaamStatus";		
		document.f1.action="";
		document.f1.submit();
		}
	
}



function cetakBorangB2(noFail,idfail,flag,flagB) {
    var url = "../servlet/ekptg.report.ppk.BorangB3?nofail="+noFail+"&idfail="+idfail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


if(flag != "yes" && flagB != "yes")
	{
	    document.f1.command.value="getDaftarStatus";
	//	document.f1.mode.value="getHtaamStatus";		
		document.f1.action="";
		document.f1.submit();
		}
	
}





function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function qryHowOld()
   {
   
   var dob_code = document.f1.txtNoKPBaruSimati1.value;
if(dob_code.charAt(0)<2)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}
	
var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	
	 
	 
	 
var dob_codeX = document.f1.txtTarikhMati.value;

	
     var ttX = dob_codeX;	
	 var dt_dobX   = parseInt(ttX.substring(0,2),10);
     var mon_dobX  = parseInt(ttX.substring(3,5),10)-1;
     var yr_dobX   = parseInt(ttX.substring(6,10),10);
	
	 
	 
	 
     var varAsOfDate = new Date(yr_dobX, mon_dobX, dt_dobX);
	 var varBirthDate = new Date(yr_dob, mon_dob, dt_dob);
	 
	 var year1 = varAsOfDate.getFullYear();
	 var year2 = varBirthDate.getFullYear();
	 

    if(dob_code != "")
	{
	
	if((year1 - year2)>0){
    document.f1.txtUmurSimati.value = year1 - year2 ;
	}
	else
	{
	document.f1.txtUmurSimati.value = 0 ;
	}
	
	}
	else
	{
	
	document.f1.txtUmurSimati.value = "";
	}

   
   }
   
   
   function trans_date(t_d)
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


document.f1.txtTarikhMati.value = trans;

}
else
{
return;
}

}

function CheckBandar()
{
if(document.f1.socNegeri.value == "" || document.f1.socNegeri.value == "0")
{
/*	
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeri.focus();
  return;
  */
	  	
}


}

function getBandar(v_t)
{

        document.f1.method = "POST";
		document.f1.command.value="getBandar";
		document.f1.eventStatus.value="1";
		document.f1.action = "";
		document.f1.v_tab.value = v_t;
		document.f1.submit();

}
/*
function submitForm() {    
  
window.location.hash='$val_tab';
goTo('$val_tab');
	
} */

function submitForm() {    
//alert('$val_tab')
if('$!val_tab' != "" && '$!val_tab' != null)
{

   window.location.hash='$!val_tab';
   var nextFieldID = '$!val_tab';
   document.getElementById(nextFieldID).focus();
   }
   else
{
window.location.hash='tab_Daftar';
//goTo('tab_Daftar');
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
function Kembali(){
	document.f1.method="POST";
	document.f1.command.value="xxx";
	document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
	document.f1.submit();
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


document.f1.txdTarikhMohon.value = trans;

}
else
{
return;
}

}
    
	
function check_kp() 
{
var s = document.f1.txtNoKPBaruSimati1.value + document.f1.txtNoKPBaruSimati2.value + document.f1.txtNoKPBaruSimati3.value;
document.f1.check_no_kp_baru_simati.value = s;


}

function check_kp_lama()
{
document.f1.check_no_kp_lama_simati.value = document.f1.txtNoKPLamaSimati.value;

}

function check_kp_lain()
{
document.f1.check_no_kp_lain_simati.value = document.f1.txtNoKPLainSimati.value;

}


function check_pengenalan_simati_1()
{
	//alert(document.f1.txtNoKPBaruSimati1.value+"-"+document.f1.txtNoKPBaruSimati2.value+"-"+document.f1.txtNoKPBaruSimati3.value);
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_baru";
	target = "check_kp_1";
	doAjaxUpdater(document.f1, url, target, actionName);
}

/* function check_pengenalan_simati_2()
{
alert(document.f1.txtNoKPLamaSimati.value);
if(document.f1.txtNoKPLamaSimati.value != "" || document.f1.txtNoKPLamaSimati.value != "TDK")
	{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lama";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);
	}
} */

function check_pengenalan_simati_2()
{
	//alert(document.f1.txtNoKPLamaSimati.value);
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lama";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);
}

function check_pengenalan_simati_3()
{
	//alert("MyID Lain = "+document.f1.txtNoKPLainSimati.value)
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lain";
	target = "check_kp_3";
	doAjaxUpdater(document.f1, url, target, actionName);
}



function check_pengenalan_simati_1_onload()
{
/*
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_simati_kp_baru_onload";
	target = "check_kp_1";
	doAjaxUpdater(document.f1, url, target, actionName);*/
}


function check_pengenalan_simati_2_onload()
{
if(document.f1.txtNoKPLamaSimati.value != "" && document.f1.txtNoKPLamaSimati.value != "TDK")
{
	/*if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_simati_kp_lama_onload";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);*/
	}
}
function check_pengenalan_simati_3_onload()
{
/*
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_simati_kp_lain_onload";
	target = "check_kp_3";
	doAjaxUpdater(document.f1, url, target, actionName);*/
	
}



function get_bandar_simati()
{
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "getbandar_daftar";
	target = "check_bandar";
	doAjaxUpdater(document.f1, url, target, actionName);
}



function pilih_taraf()
{


//alert(document.f1.taraf_penting.value);

if(document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8" && document.f1.taraf_penting.value != "2" && document.f1.taraf_penting.value != "4")
{


		if(document.f1.taraf_penting.value != "1")
		{
        document.getElementById("hubungan").style.display="none";
		if(document.f1.baca.value != "disabled")
		{
		document.f1.socSaudaraWaris.value = "";	
		}
		}
		else
		{
		document.getElementById("hubungan").style.display="";
		//document.f1.socSaudaraWaris.value = "";	
		}	

		document.getElementById("kp1").style.display="";	
		document.getElementById("kp2").style.display="";	
		document.getElementById("kp3").style.display="";	
		document.getElementById('amanah').style.display="none";
		document.getElementById('baitulmal').style.display="none";
			
		document.f1.jenis_pemohon.value = "2"
		
		if(document.getElementById('jenis_pemohon_drop') != null)
		{document.getElementById("jenis_pemohon_drop").style.display="none";}
		if(document.getElementById('jenis_pemohon_dis') != null)
		{document.getElementById("jenis_pemohon_dis").style.display="";}
		
		
		
		
		
		if(document.f1.jenis_pemohon_display != null)
		{document.f1.jenis_pemohon_display.value = "02-INDIVIDU";}
		
		
		if(document.getElementById('txtNamaPemohon_1a') != null)
		{
		document.getElementById('txtNamaPemohon_1a').style.display="";
		document.getElementById('txtNamaPemohon_1b').style.display="none";
		}		
		if(document.getElementById('txtNamaPemohon_2a') != null)
		{
		document.getElementById('txtNamaPemohon_2a').style.display="";
		document.getElementById('txtNamaPemohon_2b').style.display="none";
	    }
		
		if(document.getElementById('txtAlamat1_1a') != null)
		{
		document.getElementById('txtAlamat1_1a').style.display="";
		document.getElementById('txtAlamat1_1b').style.display="none";
		}		
		if(document.getElementById('txtAlamat1_2a') != null)
		{
		document.getElementById('txtAlamat1_2a').style.display="";
		document.getElementById('txtAlamat1_2b').style.display="none";
	    }
		
		if(document.getElementById('txtAlamat2_1a') != null)
		{
		document.getElementById('txtAlamat2_1a').style.display="";
		document.getElementById('txtAlamat2_1b').style.display="none";
		}		
		if(document.getElementById('txtAlamat2_2a') != null)
		{
		document.getElementById('txtAlamat2_2a').style.display="";
		document.getElementById('txtAlamat2_2b').style.display="none";
	    }
		
		if(document.getElementById('txtAlamat3_1a') != null)
		{
		document.getElementById('txtAlamat3_1a').style.display="";
		document.getElementById('txtAlamat3_1b').style.display="none";
		}		
		if(document.getElementById('txtAlamat3_2a') != null)
		{
		document.getElementById('txtAlamat3_2a').style.display="";
		document.getElementById('txtAlamat3_2b').style.display="none";
	    }
		
		if(document.getElementById('txtPoskod_1a') != null)
		{
		document.getElementById('txtPoskod_1a').style.display="";
		document.getElementById('txtPoskod_1b').style.display="none";
		}		
		if(document.getElementById('txtPoskod_2a') != null)
		{
		document.getElementById('txtPoskod_2a').style.display="";
		document.getElementById('txtPoskod_2b').style.display="none";
	    }
	
	
		if(document.getElementById('socNegeri_1a') != null)
		{
		document.getElementById('socNegeri_1a').style.display="";		
		document.getElementById('socNegeri_1b').style.display="none";
		document.getElementById('socNegeri').disabled=false;
		document.getElementById('socNegeri').readonly=false;
		}		
		if(document.getElementById('socNegeri_2a') != null)
		{
		document.getElementById('socNegeri_2a').style.display="";
		document.getElementById('socNegeri_2b').style.display="none";
		document.getElementById('socNegeri').disabled=false;
		document.getElementById('socNegeri').readonly=false;
	    }
		
		if(document.getElementById('socBandar_1a') != null)
		{
		document.getElementById('socBandar_1a').style.display="";
		document.getElementById('socBandar_1b').style.display="none";
		document.getElementById('socBandar').disabled=false;
		document.getElementById('socBandar').readonly=false;
		}		
		if(document.getElementById('socBandar_2a') != null)
		{
		document.getElementById('socBandar_2a').style.display="";
		document.getElementById('socBandar').disabled=false;
		document.getElementById('socBandar').readonly=false;
		document.getElementById('socBandar_2b').style.display="none";
	    }
		
		if(document.getElementById('no_tel_1a') != null)
		{
		document.getElementById('no_tel_1a').style.display="";
		document.getElementById('no_tel_1b').style.display="none";
		}	
		
		
		if(document.getElementById('no_hp_1a') != null)
		{
		document.getElementById('no_hp_1a').style.display="";
		document.getElementById('no_hp_1b').style.display="none";
		}	
		
		
		document.getElementById('tr_hp').style.display="";
		document.getElementById('tr_pelbagainegara').style.display="";
		document.getElementById('tr_mesej_pelbagainegara').style.display="";

}

else if(document.f1.taraf_penting.value == "8")
{
	
		
		if(document.f1.baca.value == "disabled")
		{
				document.getElementById('amanah').style.display="none";
				document.getElementById('baitulmal').style.display="none";
		}
		else
		{
		document.getElementById('amanah').style.display="none";
		document.getElementById('baitulmal').style.display="";
		}
				
		
		document.getElementById("kp1").style.display="none";	
		document.getElementById("kp2").style.display="none";	
		document.getElementById("kp3").style.display="none";
		
		document.getElementById("hubungan").style.display="none";
		if(document.f1.baca.value != "disabled")
		{
		document.f1.socSaudaraWaris.value = "";	
		}		
	
		document.f1.jenis_pemohon.value = "1"
		
		if(document.getElementById('jenis_pemohon_drop') != null)
		{document.getElementById("jenis_pemohon_drop").style.display="none";}
		if(document.getElementById('jenis_pemohon_dis') != null)
		{document.getElementById("jenis_pemohon_dis").style.display="";}
		
		if(document.f1.jenis_pemohon_display != null)
		{document.f1.jenis_pemohon_display.value = "01-AGENSI"}
		
		if(document.getElementById('txtNamaPemohon_1a') != null)
		{document.getElementById('txtNamaPemohon_1a').style.display="none";		
		document.getElementById('txtNamaPemohon_1b').style.display="";}
		if(document.getElementById('txtNamaPemohon_2a') != null)
		{document.getElementById('txtNamaPemohon_2a').style.display="none";		
		document.getElementById('txtNamaPemohon_2b').style.display="";
	    }
		
		if(document.getElementById('txtAlamat1_1a') != null)
		{
		document.getElementById('txtAlamat1_1a').style.display="none";
		document.getElementById('txtAlamat1_1b').style.display="";
		}		
		if(document.getElementById('txtAlamat1_2a') != null)
		{
		document.getElementById('txtAlamat1_2a').style.display="none";
		document.getElementById('txtAlamat1_2b').style.display="";
	    }
		
		if(document.getElementById('txtAlamat2_1a') != null)
		{
		document.getElementById('txtAlamat2_1a').style.display="none";
		document.getElementById('txtAlamat2_1b').style.display="";
		}		
		if(document.getElementById('txtAlamat2_2a') != null)
		{
		document.getElementById('txtAlamat2_2a').style.display="none";
		document.getElementById('txtAlamat2_2b').style.display="";
	    }
		
		if(document.getElementById('txtAlamat3_1a') != null)
		{
		document.getElementById('txtAlamat3_1a').style.display="none";
		document.getElementById('txtAlamat3_1b').style.display="";
		}		
		if(document.getElementById('txtAlamat3_2a') != null)
		{
		document.getElementById('txtAlamat3_2a').style.display="none";
		document.getElementById('txtAlamat3_2b').style.display="";
	    }
		
		if(document.getElementById('txtPoskod_1a') != null)
		{
		document.getElementById('txtPoskod_1a').style.display="none";
		document.getElementById('txtPoskod_1b').style.display="";
		}		
		if(document.getElementById('txtPoskod_2a') != null)
		{
		document.getElementById('txtPoskod_2a').style.display="none";
		document.getElementById('txtPoskod_2b').style.display="";
	    }
		
		if(document.getElementById('socNegeri_1a') != null)
		{
		document.getElementById('socNegeri_1a').style.display="none";
		document.getElementById('socNegeri_1b').style.display="";
		}		
		if(document.getElementById('socNegeri_2a') != null)
		{
		document.getElementById('socNegeri_2a').style.display="none";
		document.getElementById('socNegeri_2b').style.display="";
	    }
		
		
		if(document.getElementById('socBandar_1a') != null)
		{
		document.getElementById('socBandar_1a').style.display="none";
		document.getElementById('socBandar_1b').style.display="";
		document.f1.socBandar[0].disabled=true;
		}		
		if(document.getElementById('socBandar_2a') != null)
		{
		document.getElementById('socBandar_2a').style.display="none";
		document.getElementById('socBandar_2b').style.display="";
		document.f1.socBandar[0].disabled=true;
	    }
		
		// amanah raya 
		
		
		
		
		if(document.getElementById('no_tel_1a') != null)
		{
		document.getElementById('no_tel_1a').style.display="none";
		document.getElementById('no_tel_1b').style.display="";
		}	
		
		
		document.getElementById('tr_hp').style.display="none";
		
		document.getElementById('tr_pelbagainegara').style.display="none";
		document.getElementById('tr_mesej_pelbagainegara').style.display="none";
		document.getElementById('nama_pelbagainegara').value = "";
		
		if(document.getElementById('no_hp_1a') != null)
		{
		document.getElementById('no_hp_1a').style.display="none";
		document.getElementById('no_hp_1b').style.display="none";
		}	
		

}

else if(document.f1.taraf_penting.value == "2" || document.f1.taraf_penting.value == "4")
{
	document.getElementById("hubungan").style.display="none";
		if(document.f1.baca.value != "disabled")
		{
		document.f1.socSaudaraWaris.value = "";	
		}	
	
		if(document.getElementById('jenis_pemohon_drop') != null)
		{document.getElementById("jenis_pemohon_drop").style.display="";}
		if(document.getElementById('jenis_pemohon_dis') != null)
		{document.getElementById("jenis_pemohon_dis").style.display="none";}		
		
		if(document.f1.jenis_pemohon.value == "2")
		{		
		document.getElementById('amanah').style.display="none";
		document.getElementById('baitulmal').style.display="none";
		document.getElementById("kp1").style.display="";	
		document.getElementById("kp2").style.display="";	
		document.getElementById("kp3").style.display="";
		
		if(document.getElementById('no_hp_1a') != null)
		{
		document.getElementById('no_hp_1a').style.display="";
		document.getElementById('no_hp_1b').style.display="none";
		}		
		document.getElementById('tr_hp').style.display="";	
		
		document.getElementById('tr_pelbagainegara').style.display="";
		document.getElementById('tr_mesej_pelbagainegara').style.display="";
		
		}
		else{
		document.getElementById('amanah').style.display="none";
		document.getElementById('baitulmal').style.display="none";
		document.getElementById("kp1").style.display="none";	
		document.getElementById("kp2").style.display="none";	
		document.getElementById("kp3").style.display="none";
		
		if(document.getElementById('no_hp_1a') != null)
		{
		document.getElementById('no_hp_1a').style.display="none";
		document.getElementById('no_hp_1b').style.display="none";
		}		
		document.getElementById('tr_hp').style.display="none";	
		
		document.getElementById('tr_pelbagainegara').style.display="none";
		document.getElementById('tr_mesej_pelbagainegara').style.display="none";
		document.getElementById('nama_pelbagainegara').value = "";		
		}	
	
		if(document.getElementById('txtNamaPemohon_1a') != null)
		{
		document.getElementById('txtNamaPemohon_1a').style.display="";
		document.getElementById('txtNamaPemohon_1b').style.display="none";
		}
		if(document.getElementById('txtNamaPemohon_2a') != null)
		{
		document.getElementById('txtNamaPemohon_2a').style.display="";
		document.getElementById('txtNamaPemohon_2b').style.display="none";
	    }
		
		if(document.getElementById('txtAlamat1_1a') != null)
		{
		document.getElementById('txtAlamat1_1a').style.display="";
		document.getElementById('txtAlamat1_1b').style.display="none";
		}		
		if(document.getElementById('txtAlamat1_2a') != null)
		{
		document.getElementById('txtAlamat1_2a').style.display="";
		document.getElementById('txtAlamat1_2b').style.display="none";
	    }
		
		if(document.getElementById('txtAlamat2_1a') != null)
		{
		document.getElementById('txtAlamat2_1a').style.display="";
		document.getElementById('txtAlamat2_1b').style.display="none";
		}		
		if(document.getElementById('txtAlamat2_2a') != null)
		{
		document.getElementById('txtAlamat2_2a').style.display="";
		document.getElementById('txtAlamat2_2b').style.display="none";
	    }
		
		if(document.getElementById('txtAlamat3_1a') != null)
		{
		document.getElementById('txtAlamat3_1a').style.display="";
		document.getElementById('txtAlamat3_1b').style.display="none";
		}		
		if(document.getElementById('txtAlamat3_2a') != null)
		{
		document.getElementById('txtAlamat3_2a').style.display="";
		document.getElementById('txtAlamat3_2b').style.display="none";
	    }
		
		if(document.getElementById('txtPoskod_1a') != null)
		{
		document.getElementById('txtPoskod_1a').style.display="";
		document.getElementById('txtPoskod_1b').style.display="none";
		}		
		if(document.getElementById('txtPoskod_2a') != null)
		{
		document.getElementById('txtPoskod_2a').style.display="";
		document.getElementById('txtPoskod_2b').style.display="none";
	    }
		
		
		if(document.getElementById('socNegeri_1a') != null)
		{
		document.getElementById('socNegeri_1a').style.display="";
		document.getElementById('socNegeri_1b').style.display="none";
		}		
		if(document.getElementById('socNegeri_2a') != null)
		{
		document.getElementById('socNegeri_2a').style.display="";
		document.getElementById('socNegeri_2b').style.display="none";
	    }
		
		if(document.getElementById('socBandar_1a') != null)
		{
		document.getElementById('socBandar_1a').style.display="";
		document.getElementById('socBandar_1b').style.display="none";
		}		
		if(document.getElementById('socBandar_2a') != null)
		{
		document.getElementById('socBandar_2a').style.display="";
		document.getElementById('socBandar_2b').style.display="none";
	    }
		
		if(document.getElementById('no_tel_1a') != null)
		{
		document.getElementById('no_tel_1a').style.display="";
		document.getElementById('no_tel_1b').style.display="none";
		}	
		
		
		
	
	
}

else if(document.f1.taraf_penting.value == "6")
{

document.getElementById("hubungan").style.display="none";

if(document.f1.baca.value != "disabled")
		{
		document.f1.socSaudaraWaris.value = "";	
		}	
		


if(document.f1.baca.value == "disabled")
{
		document.getElementById('amanah').style.display="none";
		document.getElementById('baitulmal').style.display="none";
}
else
{

document.getElementById('amanah').style.display="";
document.getElementById('baitulmal').style.display="none";
}


		document.getElementById("kp1").style.display="none";	
		document.getElementById("kp2").style.display="none";	
		document.getElementById("kp3").style.display="none";		
	
		document.f1.jenis_pemohon.value = "1"
		
		if(document.getElementById('jenis_pemohon_drop') != null)
		{document.getElementById("jenis_pemohon_drop").style.display="none";}
		if(document.getElementById('jenis_pemohon_dis') != null)
		{document.getElementById("jenis_pemohon_dis").style.display="";}
		
		if(document.f1.jenis_pemohon_display != null)
		{document.f1.jenis_pemohon_display.value = "01-AGENSI"}
		
		
		
		
		if(document.getElementById('txtNamaPemohon_1a') != null)
		{document.getElementById('txtNamaPemohon_1a').style.display="none";		
		document.getElementById('txtNamaPemohon_1b').style.display="";}
		if(document.getElementById('txtNamaPemohon_2a') != null)
		{document.getElementById('txtNamaPemohon_2a').style.display="none";		
		document.getElementById('txtNamaPemohon_2b').style.display="";
	    }
		
		if(document.getElementById('txtAlamat1_1a') != null)
		{
		document.getElementById('txtAlamat1_1a').style.display="none";
		document.getElementById('txtAlamat1_1b').style.display="";
		}		
		if(document.getElementById('txtAlamat1_2a') != null)
		{
		document.getElementById('txtAlamat1_2a').style.display="none";
		document.getElementById('txtAlamat1_2b').style.display="";
	    }
		
		if(document.getElementById('txtAlamat2_1a') != null)
		{
		document.getElementById('txtAlamat2_1a').style.display="none";
		document.getElementById('txtAlamat2_1b').style.display="";
		}		
		if(document.getElementById('txtAlamat2_2a') != null)
		{
		document.getElementById('txtAlamat2_2a').style.display="none";
		document.getElementById('txtAlamat2_2b').style.display="";
	    }
		
		if(document.getElementById('txtAlamat3_1a') != null)
		{
		document.getElementById('txtAlamat3_1a').style.display="none";
		document.getElementById('txtAlamat3_1b').style.display="";
		}		
		if(document.getElementById('txtAlamat3_2a') != null)
		{
		document.getElementById('txtAlamat3_2a').style.display="none";
		document.getElementById('txtAlamat3_2b').style.display="";
	    }
		
		if(document.getElementById('txtPoskod_1a') != null)
		{
		document.getElementById('txtPoskod_1a').style.display="none";
		document.getElementById('txtPoskod_1b').style.display="";
		}		
		if(document.getElementById('txtPoskod_2a') != null)
		{
		document.getElementById('txtPoskod_2a').style.display="none";
		document.getElementById('txtPoskod_2b').style.display="";
	    }
		
		if(document.getElementById('socNegeri_1a') != null)
		{
		document.getElementById('socNegeri_1a').style.display="none";
		document.getElementById('socNegeri_1b').style.display="";
		}		
		if(document.getElementById('socNegeri_2a') != null)
		{
		document.getElementById('socNegeri_2a').style.display="none";
		document.getElementById('socNegeri_2b').style.display="";
	    }
		
		
		if(document.getElementById('socBandar_1a') != null)
		{
		document.getElementById('socBandar_1a').style.display="none";
		document.getElementById('socBandar_1b').style.display="";
		document.f1.socBandar[0].disabled=true;
		}		
		if(document.getElementById('socBandar_2a') != null)
		{
		document.getElementById('socBandar_2a').style.display="none";
		document.getElementById('socBandar_2b').style.display="";
		document.f1.socBandar[0].disabled=true;
	    }
		
		// amanah raya 
		
		
		
		
		if(document.getElementById('no_tel_1a') != null)
		{
		document.getElementById('no_tel_1a').style.display="none";
		document.getElementById('no_tel_1b').style.display="";
		}	
		
		
		document.getElementById('tr_hp').style.display="none";
		
		document.getElementById('tr_pelbagainegara').style.display="none";
		document.getElementById('tr_mesej_pelbagainegara').style.display="none";
		document.getElementById('nama_pelbagainegara').value = "";
		
		if(document.getElementById('no_hp_1a') != null)
		{
		document.getElementById('no_hp_1a').style.display="none";
		document.getElementById('no_hp_1b').style.display="none";
		}	
		
		
		
	
	  


}
else
{

}



							document.f1.txtNamaPemohon[0].disabled = '';					 		
					 		 document.f1.txtAlamat1[0].disabled = '';				
					 		 document.f1.txtAlamat2[0].disabled = ''; 			
					 		 document.f1.txtAlamat3[0].disabled = ''; 	
					 		 document.f1.txtPoskod[0].disabled = ''; 
							 
							
							 
					 		document.f1.no_tel[0].disabled = ''; 	
							/*
							alert("xxxxxxxxxxxxxxx"+document.f1.socNegeri[0].value);
							 			 		
					 		document.f1.socNegeri[0].disabled = ''; 						
							
					 		document.f1.socBandar[0].disabled = ''; 							 
							*/ 
							 	
							 
							 
}

function pilih_amanah()
{

if(document.f1.taraf_penting.value == "6" || document.f1.taraf_penting.value == "8"){
 		
		document.f1.txtNamaPemohon[1].value = "";		
		document.f1.txtAlamat1[1].value = "";
		document.f1.txtAlamat2[1].value = "";
		document.f1.txtAlamat3[1].value = "";		
		document.f1.txtPoskod[1].value = "";		
		document.f1.socNegeri[1].value = "";
		document.f1.socBandar[1].value = "";			
		document.f1.no_tel[1].value = "";
		
		document.f1.socNegeri_dis.value = "";		
		document.f1.socBandar_dis.value = "";
		
		}
}


function alamat_raya()
{

if(document.f1.taraf_penting.value == '6')
{
document.f1.jenis_pej.value = document.f1.jenis_pej1.value;
}
if(document.f1.taraf_penting.value == '8')
{
document.f1.jenis_pej.value = document.f1.jenis_pej2.value;
}


if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "getalamat_raya";
	target = "add_alamat_raya";
	doAjaxUpdater(document.f1, url, target, actionName);

}



function default_amanah()
{
document.f1.jenis_pej1.value = "";
document.f1.jenis_pej2.value = "";
document.f1.jenis_pej.value = "";
}

function kp_baru_pemohon()
{
var kp1 = document.f1.txtNoKPBaruPemohon1.value;
var kp2 = document.f1.txtNoKPBaruPemohon2.value;
var kp3 = document.f1.txtNoKPBaruPemohon3.value;
var baru = kp1 + kp2 + kp3;
document.f1.check_no_kp_baru_pemohon.value = baru;
//alert("baru betol")
/*
 <input name="check_no_kp_baru_pemohon" id="check_no_kp_baru_pemohon" type="hidden"  />   
 <input name="check_no_kp_lama_pemohon" id="check_no_kp_lama_pemohon" type="hidden"  />  
 <input name="check_no_kp_lain_pemohon" id="check_no_kp_lain_pemohon" type="hidden"  />  
*/
if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_kpbaru_pemohon";
	target = "check_kp_p1";
	doAjaxUpdater(document.f1, url, target, actionName);


//alert("baru betol")


}


function kp_lama_pemohon()
{

var kplama = document.f1.txtNoKPLamaPemohon.value;

document.f1.check_no_kp_lama_pemohon.value = kplama;

if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_kplama_pemohon";
	target = "check_kp_p2";
	doAjaxUpdater(document.f1, url, target, actionName);



//alert("lama betol")
}

function kp_lain_pemohon()
{
//alert("sdjhjsdhjsd")
var kplain = document.f1.txtNoKPLainPemohon.value;


document.f1.check_no_kp_lain_pemohon.value = kplain;
if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_kplain_pemohon";
	target = "check_kp_p3";
	doAjaxUpdater(document.f1, url, target, actionName);


//alert("lain betol")
}

function check_kp_pemohon_onload()
{
//alert("XXXXXXXXXXXXXXXX");
kp_baru_pemohon();
kp_lama_pemohon();
kp_lain_pemohon();

}



function ForView(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.f1.submit();
}


function sorok_fieldset(IdFail)
{
//alert("test1");
if(IdFail!="" && IdFail != 0)
{
//alert("test");
document.getElementById('maklumat_pemohon').style.display="none";
document.getElementById('maklumat_simati').style.display="none";
}
else
{
document.getElementById('maklumat_asas').style.display="none";
}


}


</script>




<script type="text/javascript">
    function AddItem(Text,Value)
    {
        // Create an Option object        

        var opt = document.createElement("option");

        // Add an Option object to Drop Down/List Box
        document.getElementById("DropDownList").options.add(opt);

        // Assign text and value to Option object
        opt.text = Text;
        opt.value = Value;

    }
	
	
	
function calcDate(){

if (document.f1.tdaftar.value != "" ){

var tarikhHantar = document.f1.tdaftar.value;
var days = 165;

var dt1 = parseInt(tarikhHantar.substring(0,2),10) + days;
var mon1 = parseInt(tarikhHantar.substring(3,5),10)-1;
var yr1 = parseInt(tarikhHantar.substring(6,10),10);

var myDate = new Date(yr1, mon1, dt1);

var day = myDate.getDate();
var month = myDate.getMonth()+1;
var year = myDate.getFullYear();

var tarikhJangkaTerima = "";
if(month>=10){
if(day>=10){
tarikhJangkaTerima = day + "/" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;
}
} else {
if(day>=10){
tarikhJangkaTerima = day + "/0" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;
}
}

$jquery("#kpi_ppk").html("<span  style='color:red'>"+tarikhJangkaTerima+"</span>");

} else {
$jquery("#kpi_ppk").html("<span  style='color:red'></span>");
}

//alert("check date");
} 

	function cetakDokumen(id,template) {
	    var url = "../servlet/ekptg.report.frmPaparDokumenByPermohonan?idpermohonan="+id+"&dirfolder=ppk&template="+template;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function check()
	{
	//alert("masuk!");
	}
	
</script>




