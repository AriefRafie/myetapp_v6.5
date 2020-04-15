

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
.style3 {
	font-size: 9px
}
.style36 {
	font-size: 12
}
.style51 {
	color: #0000FF;
	font-size: 9px;
}
-->
</style>
<style type="text/css">
#sddm {
	margin: 0;
	padding: 0;
	z-index: 30
}
#sddm li {
	margin: 0;
	padding: 0;
	list-style: none;
	float: left;
}


#sddm li a {
	display: block;
	color: #FFF;
	text-align: center;
	text-decoration: none
}
#sddm li a:hover {
	background: #E0F2F7;
}
#sddm div {
	position: absolute;
	visibility: hidden;
	margin: 0;
	padding: 0;
	border: 1px solid #5970B2;
	z-index:2;
}
#sddm div a {
	position: relative;
	display: block;
	margin: 0;
	padding: 2.5px 10px;
	width: auto;
	white-space: nowrap;
	text-align: left;
	text-decoration: none;
	background: #EAEBD8;
	color: #2875DE;
	font: 11px arial;
	z-index:2;
}
#sddm div a:hover {
	background: #49A3FF;
	color: #FFFFFF;
}
</style>

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
#set ($jenis_pemohon = "2")
#set ($jenis_pej = "")

#set ($socSaudaraWaris = "")

#end

#set ($namaDoC = "")

#if ($idAlert == "1" || $idAlert == "2")
#set ($myurl = $myurl)
#set ($noFail = $noFail)
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

#foreach($listSupportingDoc in $ViewSupportingDoc)
#set($namaDoC = $listSupportingDoc.NAMA_DOKUMEN)
#end

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
        #set ($tarikhMohon = $View.tarikhMohon)
        #set ($noKpBaru1 = $View.noKpBaru1)
        #set ($noKpBaru2 = $View.noKpBaru2)
        #set ($noKpBaru3 = $View.noKpBaru3)
        #set ($noKpLama = $View.noKpLama)
        #set ($jenisKpMati = $View.jenisKp)
        #set ($noKpLain = $View.noKpLain)
        #set ($tarikhLahirSimati = $View.tarikhLahirSimati)
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
        
          
        #set ($idDaerahx = $View.idDaerah)#if ($View.idDaerah != "")
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






#if ($duplicate == "yes")
<script type="text/javascript">
	//window.location="?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8SenaraiSemakInternal";	
	//alert("Minta maaf. MyID Simati telah wujud.")
	
	
</script>
#end

<body onLoad="submitForm();calcDate();check_kp();check_kp_lama();check_kp_lain();check_pengenalan_simati_1();check_pengenalan_simati_1_onload();check_pengenalan_simati_2_onload();check_pengenalan_simati_3_onload();pilih_taraf();check_kp_pemohon_onload();selectPelbagaiNegara('$negeri','div_mesejpelbagagainegara','tr_pelbagainegara','nama_pelbagainegara');">
<form id="form1" name="f1" method="post" action="">
  <input type="hidden" name="v_tab" id="v_tab" value="" />
  <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
  <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
  <input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="id_Permohonansimati" id="id_Permohonansimati" value="$!id_Permohonansimati" >
  <input type="hidden" name="senaraisemak" id="senaraisemak" value="$!senaraisemak" >
  
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
  <input name="idFail2" type="hidden" value="$IdFail" />
  <input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/>
  #foreach($list in $View)
  #set($noFail = $list.noFail)
  #set($idPemohon = $list.idPemohon)
  #end
  <table width="100%">
    <tr>
      <td><div align="right"> #if($pendaftaran == "")
          #if ($flagFromSenaraiFailSek8 == '' && $flagForView  == '' && $flagFromSenaraiPermohonanSek8 == '' && $pendaftaran == "")<a href="javascript:javascript:Kembali()" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if ($flagFromSenaraiFailSek8 == 'yes')<a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagForView  == 'yes')<a href="javascript:ForView('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style2"  ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style2"  ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3current.png" alt="" border="0" title="Pendaftaran Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>
          #if ($noFail != "")
          	#if (($duplicate != "yes") && ($id_Status != "152"))
          		<a href="javascript:seterusnya_tab()" class="style2"  ><img src="../img/4enable2.png" alt="" border="0" title="Maklumat Permohonan"/></a>
          	#else
          		<img src="../img/4disable2.png" alt="" border="0" title="Maklumat Permohonan"/>#end </span> 
          	
          #else<img src="../img/4disable2.png" alt="" border="0" title="Maklumat Permohonan"/>#end </span> 
          #else
          #if($pendaftaran == "yes") #if ($duplicate != "yes")<a href="javascript:kembalidaftar()" class="style2"  ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#else <img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/>#end #else<a href="javascript:kembalix()" class="style2"  ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/2current.png" alt="" border="0" title="Pendaftaran Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>#if ($noFail != "") #if ($duplicate != "yes")<a href="javascript:seterusnya_tab()" class="style2"  ><img src="../img/3enable2.png" alt="" border="0" title="Maklumat Permohonan"/></a>#else<img src="../img/3disable.png" alt="" border="0" title="Maklumat Permohonan"/>#end #else<img src="../img/3disable.png" alt="" border="0" title="Maklumat Permohonan"/>#end </span> #end </div></td>
    </tr>
  </table>
  #parse("app/ppk/bil_fail.jsp")
  
  
  #if($!headerppk.size()>0)
  #parse("app/ppk/headerppk.jsp") <br>
  #end
  <fieldset>
  <p></p>
  <p>
    <input name="mode1" type="hidden" value="$mode1" />
    <input name="mode2" type="hidden" value="$mode2" />
    <input name="idpermohonan" type="hidden" value="$idPemohonan" />
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
  </p>
  <!--
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td width="35%" height="249" valign="top">
      <fieldset><legend><b>MAKLUMAT PERMOHONAN</b></legend>
        <table width="100%" border="0" height="80px" cellpadding="0" cellspacing="0">
          <tr>
            <td width="35%" >No Fail</td>
            <td width="65%">: <input name="txtNoFail" id="txtNoFail" type="text" value="$noFail" style="width: 195px;" class="disabled" readonly /></td>
          </tr>
          <tr>
            <td scope="row">Daerah <font color="#FF0000"/>*</font></td>
            <td style="width: 195px; text-transform:uppercase;">: <select name="socDaerah" style="width: 195px;" $setmode2/>
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
			<option value="0">Sila Pilih Daerah</option>
			#foreach ($listDaerah in $ListDaerahByUser)
	            #set ($idDaerah = $listDaerah.iddaerah)
                #set ($kodDaerah = $listDaerah.koddaerah)
	            #set ($namaDaerah = $listDaerah.namadaerah)
			<option value="$idDaerah" >$namaDaerah</option>
          
			#end
	 	#else
			<option value="0">Sila Pilih Daerah</option>
			#foreach ($listDaerah in $ListDaerahByUser)
	            #set ($idDaerah = $listDaerah.iddaerah)
	            #set ($namaDaerah = $listDaerah.namadaerah)
                #set ($kodDaerah = $listDaerah.koddaerah)
			<option value="$idDaerah" >$kodDaerah - $namaDaerah </option>
            
            
            
            
			#end
		#end
            </select></td>
         
          </tr>
        
          <tr>
            <td scope="row">Tarikh Mohon</td>
            <td>
            #if ($idAlert == "1" || $idAlert == "2") 
            : 
            <input name="txdTarikhMohon" id="txdTarikhMohon" style="width: 80px;" type="text" $setmode value="$tarikhmohonx" size="11" maxlength="10" />
            #else
            
            
            #if($tarikhmohondaftar!="")
          
            #set($t=$tarikhmohondaftar)
            #end 
            
            #if($tarikhmohondaftar=="")
           
            #set($t=$tarikhMohon)
            #end            
            
            
            :  
            <input name="txdTarikhMohon" id="txdTarikhMohon" style="width: 80px;" type="text" $setmode value="$t" size="11" maxlength="10" readonly/>
            #end
            #if ($EventStatus == "0")
      		<a href="javascript:displayDatePicker('txdTarikhMohon',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
			#end
       	    </td>
          </tr>
        </table>
        </fieldset>
        <fieldset><legend><b>MAKLUMAT SIMATI</b></legend>
            <table width="100%" height="105px" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="35%">No. KP Baru</td>
              <td width="65%">
              #if ($idAlert == "1" || $idAlert == "2") 
              : <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$nokpbaru1x" size="7" maxlength="6" $setmode onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruSimati2')"  onBlur="getAgeByIC(this,this.value,'txtUmurSimati')" />
              -
              <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$nokpbaru2x" size="3" maxlength="2" $setmode onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruSimati3')"/>
              -
              <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3"  style="width: 40px;" type="text" value="$nokpbaru3x" $setmode size="5" maxlength="4"  onblur="jantinaic()" />
              #else
               : <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$noKpBaru1" size="7" maxlength="6" $setmode onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruSimati2')" onBlur="getAgeByIC(this,this.value,'txtUmurSimati')"  />
               -
               <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$noKpBaru2" size="3" maxlength="2" $setmode onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruSimati3')"/>
               -
               <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3"  style="width: 40px;" type="text" value="$noKpBaru3" $setmode size="5" maxlength="4" onblur="jantinaic()" />
                #end
            
              
              <input id="txtUmurSimati" name="txtUmurSimati" type="hidden" value="$umursimati"  />
              <input name="socJantinaSimati" type="hidden" value="$jantinasimati" />   
              
                <input id="txtUmurPemohon" name="txtUmurPemohon" type="hidden" value="$umurpemohon"  />
              <input name="socJantinaPemohon" type="hidden" value="$jantinapemohon" />              
              
              
              
              </td>
            </tr>
            
            <tr>
              <td ><span style="width: 248px;"/>No. KP Lama</span></td>
              <td>
              #if ($idAlert == "1" || $idAlert == "2") 
              : <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLamax" $setmode maxlength="8" size="10" style="width: 120px; text-transform:uppercase;"/>
              #else
              : <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLama" $setmode maxlength="8" size="10" style="width: 120px; text-transform:uppercase;"/>
              #end              </td>
            </tr>
            <tr>
              <td scope="row"><span style="width: 248px;">Lain - lain KP</span></td>
              <td>: <select name="socJenisKPLainSimati" id="socJenisKPLainSimati" $setmode style="width: 100px; text-transform:uppercase;" onchange="kplain(this.value)"/>
            #if ($idAlert == "1" || $idAlert == "2") 
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
                        <option value="$Listkp.id"/>$Listkp.keterangan</option>
                    #end
                #end
            #end
            </select>
            
           
            #if ($idAlert == "1" || $idAlert == "2") 
            #if($noKpLainx=="" || $noKpLainx=="0")
             #if($setmode=="disabled")
             #set($setmod="disabled")
             #end
            #end
          
            
            
            	<input name="txtNoKPLainSimati" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" type="text" value="$noKpLainx" $setmod maxlength="9" />
            #else
            
             #if($setmode=="disabled")
             #set($setmod="disabled")
             #else
                #if($noKpLain=="" || $noKpLain=="0")
             #set($setmod="disabled")
             #else
             #set($setmod="")
             
            #end
            #end
            	<input name="txtNoKPLainSimati" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" type="text" value="$noKpLain" $setmod maxlength="9" />
            #end            </td>
            </tr>    
            <tr>
              <td scope="row"><span style="width: 248px;"/>Nama Simati <font color="#FF0000"/>*</font></span></td>
              <td>
              #if ($idAlert == "1" || $idAlert == "2")
                : <input name="txtNamaSimati" id="txtNamaSimati" style="width: 200px; text-transform:uppercase;" type="text" value="$namaSimatix" $setmode maxlength="200"/>
              #else
              	: <input name="txtNamaSimati" id="txtNamaSimati" style="width: 200px; text-transform:uppercase;" type="text" value="$namaSimati" $setmode maxlength="200"/>
              #end</td>
            </tr>
            <tr>
              <td scope="row"><span style="width: 248px;"/>Tarikh Mati <font color="#FF0000"/>*</font></span></td>
              <td>
              #if ($idAlert == "1" || $idAlert == "2")
              		: <input name="txtTarikhMati" id="txtTarikhMati" type="text" style="width: 80px;" value="$tarikhMatix" size="11" maxlength="10" $setmode readonly/>
              #else
              		: <input name="txtTarikhMati" id="txtTarikhMati" type="text" style="width: 80px;" value="$tarikhMati" size="11" maxlength="10" $setmode readonly/>
              #end
              #if ($flag_no != 3 )
              		<a href="javascript:displayDatePicker('txtTarikhMati',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a>
               #end
               
               </td>
               </tr>
          </table>
        </fieldset>
      <td width="35%" rowspan="2" valign="top">
          <p></p>
          <fieldset><legend><b>MAKLUMAT PEMOHON</b></legend>
        <table width="100%" height="217px" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="30%" scope="col">No. KP Baru</td>
            <td width="70%" scope="col">
            #if ($idAlert == "1" || $idAlert == "2")
                  : <input name="txtNoKPBaruPemohon1" id="txtNoKPBaruPemohon1" style="width: 50px;" type="text" value="$noKpBaruPemohon1x" $setmode size="7" maxlength="6" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruPemohon2')"  onBlur="getAgeByIC(this,this.value,'txtUmurPemohon')" />-<input name="txtNoKPBaruPemohon2" id="txtNoKPBaruPemohon2" style="width: 20px;" type="text" value="$noKpBaruPemohon2x" $setmode size="3" maxlength="2" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruPemohon3')"/>-
                  <input name="txtNoKPBaruPemohon3" id="txtNoKPBaruPemohon3"  style="width: 40px;" type="text" value="$noKpBaruPemohon3x" $setmode size="5" maxlength="4" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruPemohon3')" onblur="jantinaic1()" />
            #else
                : <input name="txtNoKPBaruPemohon1" id="txtNoKPBaruPemohon1" style="width: 50px;" type="text" value="$noKpBaruPemohon1" $setmode size="7" maxlength="6" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruPemohon2')" onBlur="getAgeByIC(this,this.value,'txtUmurPemohon')"  />-<input name="txtNoKPBaruPemohon2" id="txtNoKPBaruPemohon2" style="width: 20px;" type="text" value="$noKpBaruPemohon2" $setmode size="3" maxlength="2" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruPemohon3')"/>-<input name="txtNoKPBaruPemohon3" id="txtNoKPBaruPemohon3"  style="width: 40px;" type="text" value="$noKpBaruPemohon3" $setmode size="5" maxlength="4" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaruPemohon3')" onblur="jantinaic1()" />
            #end          
            </td>
          </tr>
          <tr>
            <td scope="row">No. KP Lama</td>
            <td>
            #if ($idAlert == "1" || $idAlert == "2")
            	: <input name="txtNoKPLamaPemohon" id="txtNoKPLamaPemohon" style="width: 120px; text-transform:uppercase;" type="text" value="$noKpLamaPemohonx" $setmode maxlength="8" size="10"/>
          	#else
            	: <input name="txtNoKPLamaPemohon" id="txtNoKPLamaPemohon" style="width: 120px; text-transform:uppercase;" type="text" value="$noKpLamaPemohon" $setmode maxlength="8" size="10"/>
            #end
            </td>
          </tr>
          <tr>
            <td scope="row">Lain - lain KP</td>
            <td>: <select name="socJenisKPLainPemohon" id="socSeksyen3" class="ppkicselect" style="width: 100px; text-transform:uppercase" $setmode onchange="kplain1(this.value)" />
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
            #if ($idAlert == "1" || $idAlert == "2")
            
            
            #if($noKpLainPemohonx=="" || $noKpLainPemohonx=="0")
             #if($setmode=="disabled")
             #set($setmod1="disabled")
             #end
            #end
            
        
                 <input name="txtNoKPLainPemohon" id="txtNoKPLainPemohon" style="width: 90px; text-transform:uppercase;" type="text" value="$noKpLainPemohonx" $setmod1 maxlength="9" size="9"/>
            #else
               
            
             #if($setmode=="disabled")
             #set($setmod1="disabled")
             #else
                #if($noKpLainPemohon=="" || $noKpLainPemohon=="0")
             #set($setmod1="disabled")
             #else
             #set($setmod1="")
             
            #end
            #end
            
             	 <input name="txtNoKPLainPemohon" id="txtNoKPLainPemohon" style="width: 90px; text-transform:uppercase;" type="text" value="$noKpLainPemohon" $setmod1  maxlength="9" size="9"/>
            #end   
            </td>
          </tr>
          <tr>
            <td  scope="row">Nama Pemohon <font color="#FF0000">*</font></td>
            <td>
            #if ($idAlert == "1" || $idAlert == "2")
            	: <input name="txtNamaPemohon" id="txtNamaPemohon" style="width: 195px; text-transform:uppercase;" type="text" maxlength="70" value="$namaPemohonx" $setmode />
            #else
            	: <input name="txtNamaPemohon" id="txtNamaPemohon" style="width: 195px; text-transform:uppercase;" type="text"  maxlength="70" value="$namaPemohon" $setmode />
            #end 
            </td>
          </tr>
          <tr>
            <td scope="row">Alamat</td>
            <td>
            #if ($idAlert == "1" || $idAlert == "2")
            	: <input name="txtAlamat1" id="txtAlamat1" style="width: 195px; text-transform:uppercase;" type="text" value="$alamat1x"  maxlength="70"$setmode />
          	#else
            	: <input name="txtAlamat1" id="txtAlamat1" style="width: 195px; text-transform:uppercase;" type="text" value="$alamat1" maxlength="70" $setmode />
            #end 
            </td>
          </tr>
          <tr>
            <td scope="row">&nbsp;</td>
            <td>
            #if ($idAlert == "1" || $idAlert == "2")
            	: <input name="txtAlamat2" id="txtAlamat2" style="width: 195px; text-transform:uppercase;" type="text" value="$alamat2x" maxlength="70" $setmode />
            #else
            	: <input name="txtAlamat2" id="txtAlamat2" style="width: 195px; text-transform:uppercase;" type="text" value="$alamat2" maxlength="70" $setmode />
            #end
            </td>
          </tr>
          <tr>
            <td scope="row">&nbsp;</td>
            <td>
            #if ($idAlert == "1" || $idAlert == "2")
            	: <input name="txtAlamat3" id="txtAlamat3" style="width: 195px; text-transform:uppercase;" type="text" value="$alamat3x" maxlength="70" $setmode />
            #else
            	: <input name="txtAlamat3" id="txtAlamat3" style="width: 195px; text-transform:uppercase;" type="text" value="$alamat3" maxlength="70" $setmode />
            #end
            
            </td>
          </tr>
          <tr>
            <td scope="row">Poskod</td>
            <td>
            #if ($idAlert == "1" || $idAlert == "2")
            	: <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskodx" $setmode maxlength="5" size="6" onKeyUp="javascript:validatePoskod(this,this.value)"/>
            #else
             : <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskod" $setmode maxlength="5" size="6" onKeyUp="javascript:validatePoskod(this,this.value)"/>
            #end
            </td>
          </tr>
          <tr>
            <td scope="row">Bandar</td>
            <td>
            #if ($idAlert == "1" || $idAlert == "2")
            	: <input name="txtBandar" id="txtBandar" type="text" style="width: 195px; text-transform:uppercase;" maxlength="70" value="$bandarx" $setmode />
            #else
                : <input name="txtBandar" id="txtBandar" type="text" style="width: 195px; text-transform:uppercase;" maxlength="70" value="$bandar" $setmode />
            #end
            </td>
          </tr>
          <tr>
            <td scope="row">Negeri</td>
            <td style="width: 195px; text-transform:uppercase;">: $selectNegeri</td>
          </tr>
        </table>
      </fieldset>
      
      </td>
    </tr>
</table>
-->
  <table width="100%" border="0" id="main_table">
    <tr>
      <td width="50%" valign="top" ><table width="100%" border="0">
          <tr>
            <td valign="top"><fieldset>
              <legend>MAKLUMAT FAIL </legend>
              <table width="100%" border="0">
                <tr>
                  <td width="1%">&nbsp;</td>
                  <td width="30%">No Fail Permohonan</td>
                  <td width="1%">:</td>
                  <td width="64%"><input name="txtNoFail" id="txtNoFail" type="text"  onblur="this.value=this.value.toUpperCase()" value="$noFail" style="width: 195px;" class="disabled" readonly="readonly" /></td>
                </tr>
                <tr>
                  <td valign="top">#if($setmode2 != "disabled") <span class="style1">*</span> #end </td>
                  <td>#if($setmode2 != "disabled") Daerah #else
                    Daerah
                    #end </td>
                  <td>:</td>
                  <td style="width: 195px; text-transform:uppercase;"> #foreach ($ld in $listdaerah)
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
                <tr>
                  <td valign="top">#if($setmode != "disabled")<span class="style1">*</span>#end</td>
                  <td><p>#if($setmode != "disabled")Tarikh Mohon#else Tarikh Mohon #end</p></td>
                  <td>:</td>
                  <td> #if ($idAlert == "1" || $idAlert == "2") 
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
                    #if ($EventStatus == "0") <a href="javascript:displayDatePicker('txdTarikhMohon',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/> </a> #end </td>
                </tr>
                <tr style="display:none">
                  <td valign="top"></td>
                  <td><p>Sasaran Tempoh KPI</p></td>
                  <td>:</td>
                  <td><div id="kpi_ppk" ></div></td>
                </tr>
              </table>
              </fieldset></td>
          </tr>
          <tr>
            <td><fieldset>
              <legend>MAKLUMAT SIMATI</legend>
              <table width="100%" border="0">
                #if($flagForView == 'yes')
                <tr>
                  <td>&nbsp;</td>
                  <td width="30%">MyID Baru</td>
                  <td width="1%">:</td>
                  <td width="65%"> 
                  #if ($idAlert == "1" || $idAlert == "2")
                    <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$nokpbaru1x" size="7" maxlength="6" readonly   class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati2')"  onblur="qryHowOld();check_kp();check_pengenalan_simati_1();calculateTarikhLahirSimati(); "/>
                    -
                    <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$nokpbaru2x" size="3" maxlength="2" readonly class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')" onBlur="check_kp();check_pengenalan_simati_1()"  />
                    -
                    <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3"  style="width: 40px;" type="text" value="$nokpbaru3x" readonly class="disabled" size="5" maxlength="4"  onblur="jantinaic();check_kp();check_pengenalan_simati_1();kp_baru_pemohon();calculateTarikhLahirSimati();" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  />
                    #else
                    <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$noKpBaru1" size="7" maxlength="6" readonly class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati2')" onBlur="qryHowOld();check_kp();check_pengenalan_simati_1();calculateTarikhLahirSimati();"  />
                    -
                    <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$noKpBaru2" size="3" maxlength="2" readonly class="disabled" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  onBlur="check_kp();check_pengenalan_simati_1()"  />
                    -
                    <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3"  style="width: 40px;" type="text" value="$noKpBaru3" readonly class="disabled" size="5" maxlength="4" onBlur="jantinaic();check_kp();check_pengenalan_simati_1();kp_baru_pemohon();calculateTarikhLahirSimati();check_hutang()" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  />
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
                    <!--
    ### 
     <input name="kp1" type="text" value="" />   
     <input name="kp2" type="text" value="" />  
     <input name="kp3" type="text" value="" />        
    #foreach($listKPBARU in $listKPSimati)
    #set ($kpbaru = $listKP.no_Kp_Baru)
    $listKPBARU.no_Kp_Baru
    #end
    -->
                    #if ($setmode != "disabled" ) <span class="style36"> <a href="http://www.jpn.gov.my" target="_blank" class="style51"> www.jpn.gov.my</a></span> #end </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>MyID Lama</td>
                  <td>:</td>
                  <td> #if ($idAlert == "1" || $idAlert == "2")
                    <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLamax"  onblur="this.value=this.value.toUpperCase();check_kp_lama();check_pengenalan_simati_2();kp_lama_pemohon()" readonly class="disabled" maxlength="15" size="15" style="width: 120px; text-transform:uppercase;"/>
                    #else
                    <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLama"  onblur="this.value=this.value.toUpperCase();check_kp_lama();check_pengenalan_simati_2();kp_lama_pemohon()" readonly class="disabled" maxlength="15" size="10" style="width: 120px; text-transform:uppercase;"/>
                    #end </td>
                </tr>
                <!--
            <tr>
              <td>MyID Lain</td>
              <td>:</td>
              <td>
              
               <select name="socJenisKPLainSimati" id="socJenisKPLainSimati"  $setmode style="width: 110px; text-transform:uppercase;" onchange="kplain(this.value)"  onblur="kplainX(this.value)" />
            #if ($idAlert == "1" || $idAlert == "2") 
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
                        <option value="$Listkp.id"/>$Listkp.keterangan</option>
                    #end
                #end
            #end

            </select>
            
           
            #if ($idAlert == "1" || $idAlert == "2") 
            #if($noKpLainx=="" || $noKpLainx=="0")
             #if($setmode=="disabled")
             #set($setmod="disabled")
             #end
            #end
          
            
            
            	<input name="txtNoKPLainSimati"  onblur="this.value=this.value.toUpperCase()" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" type="text" value="$noKpLainx" $setmod maxlength="9" />
            #else
            
             #if($setmode=="disabled")
             #set($setmod="disabled")
             #else
                #if($noKpLain=="" || $noKpLain=="0")
             #set($setmod="disabled")
             #else
             #set($setmod="")
             
            #end
            #end
            	<input name="txtNoKPLainSimati" onblur="this.value=this.value.toUpperCase()" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" type="text" value="$noKpLain" $setmod maxlength="9" />
            #end              </td>
            </tr>
            -->
                <tr>
                  <td valign="top">&nbsp;</td>
                  <td valign="top">MyID Lain</td>
                  <td valign="top">:</td>
                  <td> #if($setmode == "disabled")
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
                    
                    #elseif($jenisKpMati == "13")
                    #set($jkpm = "PERINTAH MAHKAMAH TINGGI")  
                    #elseif($jenisKpMati == "7")
                    #set($jkpm = "LAIN-LAIN")              
                    #else
                    #set($jkpm = "")
                    #end
                    <input  name="socJenisKPLainSimatiX"  id="socJenisKPLainSimatiX"  value="$jkpm" size="12" readonly class="disabled"  />
                    <input  name="socJenisKPLainSimati"  id="socJenisKPLainSimati"  value="$jenisKpMati"  type="hidden" readonly class="disabled"  />
                    #else
                    <select name="socJenisKPLainSimati" id="socJenisKPLainSimati" $setmode style="width: 110px; text-transform:uppercase;" onChange="kplain(this.value)"  onblur="kplainX(this.value)" />
                    
                    #if ($idAlert == "1" || $idAlert == "2") 
                    #set ($selected = "")
                    <option value="0">Sila Pilih KP</option>
                    #foreach($Listkp in $listkp)
                    #if ($Listkp.id == $jenisKpMati)
                    #set ($selected = "selected")
                    <option value="$Listkp.id" $selected/>
                    $Listkp.keterangan
                    </option>
                    #end
                    <option value="$Listkp.id" />
                    $Listkp.keterangan
                    </option>
                    #end
                    #else
                    #if ($idFlag == "2")
                    #foreach($Listkp in $listkp)
                    #if ($Listkp.id == $jenisKpMati)
                    #set ($selected = "selected")
                    <option value="$Listkp.id" $selected/>
                    $Listkp.keterangan
                    </option>
                    #end
                    #end
                    <option value="0" />
                    Sila Pilih KP
                    </option>
                    #foreach($Listkp in $listkp)
                    <option value="$Listkp.id" />
                    $Listkp.keterangan
                    </option>
                    #end
                    #else
                    <option value="0" />
                    Sila Pilih KP
                    </option>
                    #foreach($Listkp in $listkp)
                    <option value="$Listkp.id"/>
                    $Listkp.keterangan
                    </option>
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
                    <input name="txtNoKPLainSimati" id="txtNoKPLainSimati" onBlur="this.value=this.value.toUpperCase();check_kp_lain();check_pengenalan_simati_3();kp_lain_pemohon()" style="width: 97px; text-transform:uppercase;" type="text" value="$noKpLainx" readonly class="disabled" maxlength="25" />
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
                    <input name="txtNoKPLainSimati" onBlur="this.value=this.value.toUpperCase();check_kp_lain();check_pengenalan_simati_3();kp_lain_pemohon()" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" type="text" value="$noKpLain" readonly class="disabled"  maxlength="25" />
                    #end </td>
                </tr>
                <!-- ADD BY PEJE -->
                <tr>
                  <td>&nbsp;</td>
                  <td>Tarikh Lahir Simati</td>
                  <td>:</td>
                  <td>
                  <input type="text" name="tarikhLahirSimati" id="tarikhLahirSimati" value="$tarikhLahirSimati" onBlur="check_date(this);" size="9" readonly class="disabled"/>
                  </td>
                </tr>
                <!-- END ADD HERE BY PEJE -->
                #else
                <tr>
                  <td>&nbsp;</td>
                  <td width="30%">MyID Baru</td>
                  <td width="1%">:</td>
                  <td width="65%"> #if ($idAlert == "1" || $idAlert == "2")
                  
                    <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$nokpbaru1x" size="7" maxlength="6" $setmodeR class="$setmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati2')"  onblur="qryHowOld();check_kp();check_pengenalan_simati_1();calculateTarikhLahirSimati();" />
                    -
                    <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$nokpbaru2x" size="3" maxlength="2" $setmodeR class="$setmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')" onBlur="check_kp();check_pengenalan_simati_1()"  />
                    -
                    <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3"  style="width: 40px;" type="text" value="$nokpbaru3x" $setmodeR class="$setmode" size="5" maxlength="4"  onblur="jantinaic();check_kp();check_pengenalan_simati_1();kp_baru_pemohon();calculateTarikhLahirSimati();" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  />
                     <span id="check_kp_1" style="color:red" ></span> 
                    <div id="check_kp_p1" style="color:red" ></div>
                    #else
                    
                 
                    <input name="txtNoKPBaruSimati1" id="txtNoKPBaruSimati1" style="width: 50px;" type="text" value="$noKpBaru1" size="7" maxlength="6" $setmodeR class="$setmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati2')" onBlur="qryHowOld();check_kp();check_pengenalan_simati_1();calculateTarikhLahirSimati();"  />
                    -
                    <input name="txtNoKPBaruSimati2" id="txtNoKPBaruSimati2" style="width: 20px;" type="text" value="$noKpBaru2" size="3" maxlength="2" $setmodeR class="$setmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  onBlur="check_kp();check_pengenalan_simati_1()"  />
                    -
                    <input name="txtNoKPBaruSimati3" id="txtNoKPBaruSimati3"  style="width: 40px;" type="text" value="$noKpBaru3" $setmodeR class="$setmode" size="5" maxlength="4" onBlur="jantinaic();check_kp();check_pengenalan_simati_1();kp_baru_pemohon();calculateTarikhLahirSimati();" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaruSimati3')"  />
                    <span id="check_kp_1" style="color:red" ></span>  
                    <div id="check_kp_p1" style="color:red" ></div>
                 
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
                    <!--
    ### 
     <input name="kp1" type="text" value="" />   
     <input name="kp2" type="text" value="" />  
     <input name="kp3" type="text" value="" />        
    #foreach($listKPBARU in $listKPSimati)
    #set ($kpbaru = $listKP.no_Kp_Baru)
    $listKPBARU.no_Kp_Baru
    #end
    -->
                    #if ($setmode != "disabled" ) <span class="style36"> <a href="http://www.jpn.gov.my" target="_blank" class="style51"> www.jpn.gov.my</a></span>
                    <span id="check_kp_1" style="color:red" ></span> #end </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>MyID Lama</td>
                  <td>:</td>
                  <td> #if ($idAlert == "1" || $idAlert == "2")
                    <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLamax"  onblur="this.value=this.value.toUpperCase();check_kp_lama();check_pengenalan_simati_2();kp_lama_pemohon()" $setmodeR class="$setmode" maxlength="15" size="15" style="width: 120px; text-transform:uppercase;"/>
                    <span id="check_kp_2" style="color:red" ></span> #else
                    <input name="txtNoKPLamaSimati" id="txtNoKPLamaSimati" type="text" value="$noKpLama"  onblur="this.value=this.value.toUpperCase();check_kp_lama();check_pengenalan_simati_2();kp_lama_pemohon()" $setmodeR class="$setmode" maxlength="15" size="15" style="width: 120px; text-transform:uppercase;"/>
                    <span id="check_kp_2" style="color:red" ></span> #end </td>
                </tr>
                <!--
            <tr>
              <td>MyID Lain</td>
              <td>:</td>
              <td>
              
               <select name="socJenisKPLainSimati" id="socJenisKPLainSimati"  $setmode style="width: 110px; text-transform:uppercase;" onchange="kplain(this.value)"  onblur="kplainX(this.value)" />
            #if ($idAlert == "1" || $idAlert == "2") 
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
                        <option value="$Listkp.id"/>$Listkp.keterangan</option>
                    #end
                #end
            #end

            </select>
            
           
            #if ($idAlert == "1" || $idAlert == "2") 
            #if($noKpLainx=="" || $noKpLainx=="0")
             #if($setmode=="disabled")
             #set($setmod="disabled")
             #end
            #end
          
            
            
            	<input name="txtNoKPLainSimati"  onblur="this.value=this.value.toUpperCase()" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" type="text" value="$noKpLainx" $setmod maxlength="9" />
            #else
            
             #if($setmode=="disabled")
             #set($setmod="disabled")
             #else
                #if($noKpLain=="" || $noKpLain=="0")
             #set($setmod="disabled")
             #else
             #set($setmod="")
             
            #end
            #end
            	<input name="txtNoKPLainSimati" onblur="this.value=this.value.toUpperCase()" id="txtNoKPLainSimati" style="width: 97px; text-transform:uppercase;" type="text" value="$noKpLain" $setmod maxlength="9" />
            #end              </td>
            </tr>
            -->
                <tr>
                  <td valign="top">&nbsp;</td>
                  <td valign="top">MyID Lain</td>
                  <td valign="top">:</td>
                  <td> #if($setmode == "disabled")
                    #set($setmodeR = "readonly") 
                    
                    #if($jenisKpMati == 4)
                    #set($jkpm = "NO PASPORT")
                    #elseif($jenisKpMati == 5)
                    #set($jkpm = "NO TENTERA")
                    #elseif($jenisKpMati == 6)
                    #set($jkpm = "NO POLIS")
                    #elseif($jenisKpMati == 7)
                    #set($jkpm = "LAIN-LAIN")
                    #elseif($jenisKpMati == 13)
                    #set($jkpm = "PERINTAH MAHKAMAH TINGGI")
                    #else
                    #set($jkpm = "")
                    #end
                    <input  name="socJenisKPLainSimati" class="$setmode" id="socJenisKPLainSimati"  value="$jkpm" size="12" $setmodeR  />
                    #else
                    #set($setmodeR = "")
                    <select name="socJenisKPLainSimati" id="socJenisKPLainSimati" $setmode style="width: 150px; text-transform:uppercase;" onChange="kplain(this.value)"  onblur="kplainX(this.value)" />
                    
                    #if ($idAlert == "1" || $idAlert == "2") 
                    #set ($selected = "")
                    <option value="0">Sila Pilih KP</option>
                    #foreach($Listkp in $listkp)
                    #if ($Listkp.id == $jenisKpMati)
                    #set ($selected = "selected")
                    <option value="$Listkp.id" $selected/>
                    $Listkp.keterangan
                    </option>
                    #end
                    <option value="$Listkp.id" />
                    $Listkp.keterangan
                    </option>
                    #end
                    #else
                    #if ($idFlag == "2")
                    #foreach($Listkp in $listkp)
                    #if ($Listkp.id == $jenisKpMati)
                    #set ($selected = "selected")
                    <option value="$Listkp.id" $selected/>
                    $Listkp.keterangan
                    </option>
                    #end
                    #end
                    <option value="0" />
                    Sila Pilih KP
                    </option>
                    #foreach($Listkp in $listkp)
                    <option value="$Listkp.id" />
                    $Listkp.keterangan
                    </option>
                    #end
                    #else
                    <option value="0" />
                    Sila Pilih KP
                    </option>
                    #foreach($Listkp in $listkp)
                    <option value="$Listkp.id"/>
                    $Listkp.keterangan
                    </option>
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
                    <input name="txtNoKPLainSimati" id="txtNoKPLainSimati" onBlur="this.value=this.value.toUpperCase();check_kp_lain();check_pengenalan_simati_3();kp_lain_pemohon()" style="width: 100px; text-transform:uppercase;" type="text" value="$noKpLainx" $setmodR class="$setmod" maxlength="21" />
                    <span id="check_kp_3" style="color:red" ></span> #else
                    
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
                    <input name="txtNoKPLainSimati" onBlur="this.value=this.value.toUpperCase();check_kp_lain();check_pengenalan_simati_3();kp_lain_pemohon()" id="txtNoKPLainSimati" style="width: 100px; text-transform:uppercase;" type="text" value="$noKpLain" $setmodR class="$setmod"  maxlength="21" />
                    <span id="check_kp_3" style="color:red" ></span> #end </td>
                </tr>
                <!-- ADD BY PEJE -->
                <tr>
                  <td>&nbsp;</td>
                  <td>Tarikh Lahir Simati</td>
                  <td>:</td>
                  <td><input type="text" name="tarikhLahirSimati" id="tarikhLahirSimati" value="$!tarikhLahirSimati" onBlur="check_date(this);" size="9" $setmodeR class="$setmode"/>
                    #if ($setmode != "disabled" )
                    <a href="javascript:displayDatePicker('tarikhLahirSimati',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
                    #end
                    </td>
                </tr>
                <!-- END ADD BY PEJE -->
                #end
                <tr>
                  <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
                  <td>#if($setmode == "disabled")
                    Nama Simati
                    #else Nama Simati #end </td>
                  <td>:</td>
                  <td> #if ($idAlert == "1" || $idAlert == "2")
                    <input name="txtNamaSimati" onBlur="this.value=this.value.toUpperCase()" id="txtNamaSimati" style="width: 200px; text-transform:uppercase;" type="text" value="$namaSimatix" $setmodeR class="$setmode" maxlength="200"/>
                    #else
                    <input name="txtNamaSimati" onBlur="this.value=this.value.toUpperCase()" id="txtNamaSimati" style="width: 200px; text-transform:uppercase;" type="text" value="$namaSimati" $setmodeR class="$setmode" maxlength="200"/>
                    #end</td>
                </tr>
                <tr>
                  <td valign="top">#if($setmode!="disabled")<span class="style1">*</span>#end</td>
                  <td>#if($setmode!="disabled") Tarikh Mati #else
                    Tarikh Mati
                    #end </td>
                  <td>:</td>
                  <td> #if ($idAlert == "1" || $idAlert == "2")
                    <input name="txtTarikhMati" id="txtTarikhMati" type="text" style="width: 80px;" value="$tarikhMatix" size="11" maxlength="10" $setmodeR class="$setmode" onFocus="qryHowOld()" onBlur="trans_date(this.value);qryHowOld()"  />
                    #else
                    <input name="txtTarikhMati" id="txtTarikhMati" type="text" style="width: 80px;" value="$tarikhMati" size="11" maxlength="10" $setmodeR class="$setmode"  onfocus="qryHowOld()" onBlur="trans_date(this.value);qryHowOld()" />
                    #end
                    #if ($setmode != "disabled" ) <a href="javascript:displayDatePicker('txtTarikhMati',false,'dmy');"/><img border="0" src="../img/calendar.gif"/></a> <em><span class="style3 style4 style2">dd/mm/yyyy</span></em>#end </td>
                </tr>
                
                
                
                #if ($SimpanStatus != "2")
                #end
              </table>
              </fieldset></td>
          </tr>
          
          #if ($senaraisemak != "senaraisemak")
          
          <tr>
            <td><fieldset>
              <legend>DOKUMEN SOKONGAN</legend>
              <table width="100%" border="0">
                #if ($Errormsg == "Error1")
                <tr>
                <td colspan="4" ><b><font color="red">Sila muatnaik dokumen sokongan terlebih dahulu.</font></b></td>
                </tr>
                #end
                <tr>
                  <td valign="top"><!-- #if($setmode!="disabled") <span class="style1">*</span>#end --></td>
                  <td width="30%">#if($setmode!="disabled") Muatnaik Dokumen Sokongan #else
                    Dokumen Sokongan
                    #end </td>
                  <td width="1%">:</td>
                  <td width="65%"> 
                  #if($setmode!="disabled" &&  $namaDoC != "")
                  <input type="text" disabled value=$!namaDoC> &nbsp;&nbsp; <input name="deleteSuppDoc1" type="button" value="Padam" onclick="deleteSuppDoc()" />
                  #end
                  #if($setmode=="disabled" && $namaDoC != "")
                  <!--  <input name="cetak" type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />
                  <input type="text" disabled value=$!namaDoC> -->	
                  	$!lampirans
                  #end
                  
                  
                  #if($setmode!="disabled" && $namaDoC == "") 
                  <input name="cetak" type="button" value="Tambah Dokumen Sokongan" onclick="uploadSuppDoc('$IdPermohonan','$idSimati')" />
                  <!-- <input id="fileupload" name="fileupload" type="file" size="40" onchange="uploadSuppDoc()">  -->
                  
                  #end
                    </td>
                </tr>
                 </table>
                 </fieldset></td>
            </tr>
            #end <!-- end utk if senarai semak   -->
        </table>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue"><i>(*Sekiranya Simati tidak mempunyai sebarang pengenalan diri, sila masukkan TDK di ruangan MyID Lama Simati.)</i></font> <br/></td>
      <td width="50%" valign="top" ><fieldset>
        <legend>MAKLUMAT PEMOHON</legend>
        <input name="baca" id="baca" type="hidden" value="$setmode" />
        <table width="100%" border="0">
          <tr >
            <input nama="tp" id="tp" type="hidden" value="$taraf_penting" />
            <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
            <td><div align="right" class="style38">
                <div align="left"> Taraf Kepentingan </div>
              </div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td> #if($taraf_penting=="")                                          
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
              <input name="adataraf" type="hidden"  value="$dahada" />
              #if($setmode=="disabled")
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
              
              #end </td>
          </tr>
          <input name="jenis_pej" id="jenis_pej" type="hidden"  value="$!jenis_pej" />
          <tr id="hubungan" >
            <td  valign="top"> #if($setmode != "disabled")<span class="style1">*</span>#end</td>
            <td><div align="left" class="style75">Hubungan Dengan Simati</div></td>
            <td>:</td>
            <td> #if($setmode == "disabled")                                          
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
                <option value="$socSaudaraWaris" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">$kodsaudara - $kodsaudaraketerangan</option>
                
                
                
                
                
                
                                         #foreach($listsau in $listsaudara)                                 
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
              #end </td>
            #end </tr>
          <tr id="amanah" >
            <td >&nbsp;</td>
            <td >Amanah raya</td>
            <td >:</td>
            <td ><select name="jenis_pej1" id="jenis_pej1" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya()" >
                
                
                
                
                
                
                             
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
                                  #if($jenis_pej != $listJ.id_Pejabat && $listJ.jenispejabat == '9' )
	                              
                    
                
                
                
                
                
                <option value="$listJ.id_Pejabat">$listJ.nama_pejabat , $listJ.namabandar </option>
                
                
                
                
                
                
                    
                                  #end 
                                  #end 
                                   #if($!jenis_pej != "" || $!jenis_pej != "0")     
                                  
                    
                
                
                
                
                
                <option value="">SILA PILIH </option>
                
                
                
                
                
                
                    
                                  #end                                       
          
                  
              
              
              
              
              
              </select></td>
          </tr>
          <tr id="baitulmal" >
            <td >&nbsp;</td>
            <td >Baitulmal</td>
            <td >:</td>
            <td ><select name="jenis_pej2" id="jenis_pej2" class="largeselect" $setMode style="text-transform:uppercase;" onChange="alamat_raya()" >
                
                
                
                
                
                
                             
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
          
                  
              
              
              
              
              
              </select></td>
          </tr>
          <tr id="j_pemohon"  >
            <td >&nbsp;</td>
            <td >Jenis Pemohon</td>
            <td >:</td>
            <td > #if($setmode == "disabled")
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
              #else <span id="jenis_pemohon_drop"  >
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
              </span> <span id="jenis_pemohon_dis">
              <input type="text" name="jenis_pemohon_display"  id="jenis_pemohon_display" readonly class="disabled" >
              </span> #end </td>
          </tr>
          <tr id="kp1" >
            <td width="2%" >&nbsp;</td>
            <td width="28%" >MyID Baru</td>
            <td width="1%" >:</td>
            <td width="70%" scope="col"> #if ($idAlert == "1" || $idAlert == "2")
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
              #end </td>
          </tr>
          <tr  id="kp2">
            <td>&nbsp;</td>
            <td>MyID Lama</td>
            <td>:</td>
            <td> #if ($idAlert == "1" || $idAlert == "2")
              <input name="txtNoKPLamaPemohon"  onblur="this.value=this.value.toUpperCase();kp_lama_pemohon()" id="txtNoKPLamaPemohon" style="width: 120px; text-transform:uppercase;" type="text" value="$noKpLamaPemohonx" $setmodeR class="$setmode" maxlength="15" size="10"/>
              <div id="check_kp_p2" style="color:red" ></div>
              #else
              <input name="txtNoKPLamaPemohon"  onblur="this.value=this.value.toUpperCase();kp_lama_pemohon()" id="txtNoKPLamaPemohon" style="width: 120px; text-transform:uppercase;" type="text" value="$noKpLamaPemohon" $setmodeR class="$setmode" maxlength="15" size="10"/>
              <div id="check_kp_p2" style="color:red" ></div>
              #end </td>
          </tr>
          <!--
        <tr>
          <td>MyID Lain</td>
          <td>:</td>
          <td>
          
          <select name="socJenisKPLainPemohon" id="socSeksyen3" class="ppkicselect" style="width: 110px; text-transform:uppercase" $setmode onchange="kplain1(this.value)"  onblur="kplain1X(this.value)" />
            
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
            #if ($idAlert == "1" || $idAlert == "2")
            
            
            #if($noKpLainPemohonx=="" || $noKpLainPemohonx=="0")
            #if($setmode=="disabled")
            #set($setmod1="disabled")
            #end
            #end
            <input name="txtNoKPLainPemohon"  onblur="this.value=this.value.toUpperCase()" id="txtNoKPLainPemohon" style="width: 90px; text-transform:uppercase;" type="text" value="$noKpLainPemohonx" $setmod1 maxlength="9" size="9"/>
            #else
            
            
            #if($setmode=="disabled")
            #set($setmod1="disabled")
            #else
            #if($noKpLainPemohon=="" || $noKpLainPemohon=="0")
            #set($setmod1="disabled")
            #else
            #set($setmod1="")
            
            #end
            #end
            <input name="txtNoKPLainPemohon"  onblur="this.value=this.value.toUpperCase()" id="txtNoKPLainPemohon" style="width: 90px; text-transform:uppercase;" type="text" value="$noKpLainPemohon" $setmod1  maxlength="9" size="9"/>
            #end </td>
        </tr>
        -->
          <tr id="kp3" >
            <td>&nbsp;</td>
            <td>MyID Lain</td>
            <td>:</td>
            <td> #if($setmode == "disabled")
              #set($setmodeR = "readonly") 
              
              #if($jenisKpPemohon == 4)
              #set($jkpm = "NO PASPORT")
              #elseif($jenisKpPemohon == 5)
              #set($jkpm = "NO TENTERA")
              #elseif($jenisKpPemohon == 6)
              #set($jkpm = "NO POLIS")
              #elseif($jenisKpPemohon == "7")
              #set($jkpm = "LAIN-LAIN")
              #elseif($jenisKpPemohon=="13")
              #set($jkpm="Perintah Mahkamah Tinggi")
              #else
              #set($jkpm = "")
              #end
              <input  name="socJenisKPLainPemohon" class="$setmode" id="socJenisKPLainPemohon" value="$jkpm" size="12" $setmodeR />
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
              <input name="txtNoKPLainPemohon" id="txtNoKPLainPemohon" onBlur="this.value=this.value.toUpperCase();kp_lain_pemohon()" style="width: 90px; text-transform:uppercase;" type="text" value="$noKpLainPemohonx" $setmod1R class="$setmod1" maxlength="40" size="40"/>
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
              <input name="txtNoKPLainPemohon" id="txtNoKPLainPemohon" onBlur="this.value=this.value.toUpperCase();kp_lain_pemohon()" style="width: 90px; text-transform:uppercase;" type="text" value="$noKpLainPemohon" $setmod1R class="$setmod1" maxlength="40" size="40"/>
              <div id="check_kp_p3" style="color:red" ></div>
              #end </td>
          </tr>
          <tr>
            <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
            <td>#if($setmode != "disabled") Nama Pemohon #else
              Nama Pemohon
              #end </td>
            <td>:<input name="txtNamaPemohonHidden" type="hidden" class="$setmode" value="$namaPemohonx"></td>
            <td> #if ($idAlert == "1" || $idAlert == "2") <span id="txtNamaPemohon_1a">
              <input name="txtNamaPemohon" type="text" class="$setmode" id="txtNamaPemohon" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$namaPemohonx" size="50" maxlength="100" $setmodeR />
              </span> <span id="txtNamaPemohon_1b">
              <input name="txtNamaPemohon" type="text" class="disabled" id="txtNamaPemohon" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$namaPemohonx" size="50" maxlength="100" readonly />
              </span> #else <span id="txtNamaPemohon_2a" >
              <input name="txtNamaPemohon" type="text" class="$setmode" id="txtNamaPemohon" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$namaPemohon" size="50"  maxlength="100" $setmodeR />
              </span> <span id="txtNamaPemohon_2b" >
              <input name="txtNamaPemohon" type="text" class="disabled" id="txtNamaPemohon" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$namaPemohon" size="50"  maxlength="100" readonly />
              </span> #end <span id="add_alamat_raya" > </span></td>
          </tr>
          <tr>
            <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
            <td> #if($setmode != "disabled") Alamat Tetap #else
              Alamat Tetap
              #end </td>
            <td>:</td>
            <td> #if ($idAlert == "1" || $idAlert == "2") <span id="txtAlamat1_1a">
              <input name="txtAlamat1" type="text" class="$setmode" id="txtAlamat1" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" value="$alamat1x" size="50"  maxlength="100" $setmodeR />
              </span> <span id="txtAlamat1_1b">
              <input name="txtAlamat1" type="text" class="disabled" id="txtAlamat1" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" value="$alamat1x" size="50"  maxlength="100" readonly />
              </span> #else <span id="txtAlamat1_2a">
              <input name="txtAlamat1" type="text" class="$setmode" id="txtAlamat1" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" value="$alamat1" size="50" maxlength="100" $setmodeR />
              </span> <span id="txtAlamat1_2b">
              <input name="txtAlamat1" type="text" class="disabled" id="txtAlamat1" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" value="$alamat1" size="50" maxlength="100" readonly />
              </span> #end </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>:</td>
            <td> #if ($idAlert == "1" || $idAlert == "2") <span id="txtAlamat2_1a">
              <input name="txtAlamat2" type="text" class="$setmode" id="txtAlamat2" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat2x" size="50" maxlength="100" $setmodeR />
              </span> <span id="txtAlamat2_1b">
              <input name="txtAlamat2" type="text" class="disabled" id="txtAlamat2" style=" text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat2x" size="50" maxlength="100" readonly />
              </span> #else <span id="txtAlamat2_2a">
              <input name="txtAlamat2" type="text" class="$setmode" id="txtAlamat2" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat2" size="50" maxlength="100" $setmodeR />
              </span> <span id="txtAlamat2_2b">
              <input name="txtAlamat2" type="text" class="disabled" id="txtAlamat2" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat2" size="50" maxlength="100" readonly />
              </span> #end </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>:</td>
            <td> #if ($idAlert == "1" || $idAlert == "2") <span id="txtAlamat3_1a" >
              <input name="txtAlamat3" type="text" class="$setmode" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat3x" size="50" maxlength="100" $setmodeR />
              </span> <span id="txtAlamat3_1b" >
              <input name="txtAlamat3" type="text" class="disabled" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat3x" size="50" maxlength="100" readonly />
              </span> #else <span id="txtAlamat3_2a" >
              <input name="txtAlamat3" type="text" class="$setmode" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat3" size="50" maxlength="100" $setmodeR />
              </span> <span id="txtAlamat3_2b" >
              <input name="txtAlamat3" type="text" class="disabled" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" value="$alamat3" size="50" maxlength="100" readonly />
              </span> #end </td>
          </tr>
          <tr>
            <td valign="top">#if($setmode != "disabled") <span class="style1">*</span> #end</td>
            <td> #if($setmode != "disabled") Poskod #else
              Poskod
              #end </td>
            <td>:</td>
            <td> #if ($idAlert == "1" || $idAlert == "2") <span id="txtPoskod_1a">
              <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskodx" $setmodeR class="$setmode" maxlength="5" size="6" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="javascript:validatePoskod(this,this.value)"/>
              </span> <span id="txtPoskod_1b" >
              <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskodx" readonly class="disabled" maxlength="5" size="6" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="javascript:validatePoskod(this,this.value)"/>
              </span> #else <span id="txtPoskod_2a" >
              <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskod" $setmodeR class="$setmode" maxlength="5" size="6" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="javascript:validatePoskod(this,this.value)"/>
              </span> <span id="txtPoskod_2b" >
              <input name="txtPoskod" id="txtPoskod" type="text" style="width: 45px;" value="$poskod" readonly class="disabled" maxlength="5" size="6" />
              </span> #end </td>
          </tr>
          <!--
        <tr>
          <td> #if($setmode != "disabled") <span class="style1">Bandar</span> #else
            
            Bandar
            #end </td>
          <td>:</td>
          <td> #if ($idAlert == "1" || $idAlert == "2")
            
            <input name="txtBandar" id="txtBandar" type="text"  onblur="this.value=this.value.toUpperCase()" style="width: 195px; text-transform:uppercase;" maxlength="70" value="$bandarx" $setmode />
            #else
            
            <input name="txtBandar" id="txtBandar" type="text"  onblur="this.value=this.value.toUpperCase()" style="width: 195px; text-transform:uppercase;" maxlength="70" value="$bandar" $setmode />
            #end </td>
        </tr>
        -->
          <!--
        <tr>
          <td> #if($setmode != "disabled") <span class="style1">Negeri</span> #else
            Negeri
            #end </td>
          <td>:</td>
          <td style="width: 195px; text-transform:uppercase;">$selectNegeri</td>
        </tr>
        -->
          #if($EventStatus!=3)
          <tr>
            <td class="style38" valign="top" >#if($setmode != "disabled") <span class="style1">*</span> #end</td>
            <td class="style38"><div align="left">#if($setmode != "disabled") Negeri #else
                Negeri
                #end </div></td>
            <td><div align="right">:</div></td>
            <td> #if($setmode == "disabled")
              
              #foreach($listnegpomo in $listnegeri)
              
              #if($negeri==$listnegpomo.id_Negeri)
              
              #set($negerikodpemoP=$listnegpomo.kod_Negeri)
              #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
              
              
              
              #end 
              #end
              
              #if($negeri != "" && $negeri != "0")
              <input name="socNegeri"  type="text"  class="$setmode" id="socNegeri" style="width: 265px; text-transform:uppercase;" value="$negerikodpemoP - $negeriketeranganpemoP" maxlength="70" $setmodeR />
              #else
              <input name="socNegeri"  type="text"  class="$setmode" id="socNegeri" style="width: 195px; text-transform:uppercase;" value="" maxlength="70" $setmodeR />
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
              
              
              
              #if($negeri!="" && $negeri!="0"  ) <span id="socNegeri_1a">
              <select name="socNegeri" class="autoselect" $setmode  onchange="getBandar('socBandar');get_bandar_simati();"  style="text-transform:uppercase;" onBlur="uppercase()">
                <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                
                
                
                
                
                
                    
                                  #foreach($listnegpomo in $listnegeri)                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
              
                    
                
                
                
                
                
                <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                
                
                
                
                
                
                    
                                  #end    
	                               #end
              
                    
                
                
                
                
                
                <option value="">Sila Pilih Negeri</option>
              </select>
              </span> <span id="socNegeri_1b"> #foreach($listnegpomo in $listnegeri)            
              #if($negeri==$listnegpomo.id_Negeri)            
              #set($negerikodpemoP1=$listnegpomo.kod_Negeri)
              #set($negeriketeranganpemoP1=$listnegpomo.nama_Negeri)           
              #end 
              #end
              <input name="socNegeri_dis"  type="text"  class="disabled" id="socNegeri_dis" style="width: 265px; text-transform:uppercase;" value="$negerikodpemoP1 - $negeriketeranganpemoP1" maxlength="70" readonly />
              <input name="socNegeri"  type="hidden"  class="$setmode" id="socNegeri" style="width: 265px; text-transform:uppercase;" value="$negeri" maxlength="70" $setmodeR />
              </span> #else <span id="socNegeri_2a" >
              <select name="socNegeri" class="autoselect" $setmode  onchange="getBandar('socBandar');get_bandar_simati();check_pengenalan_simati_1();" style="text-transform:uppercase;" onBlur="uppercase()">
                <option value="">Sila Pilih Negeri</option>
                
                
                
                
                
                
                    
                                  #foreach($listnegpomo in $listnegeri)
              
                    
                
                
                
                
                
                <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                
                
                
                
                
                
                                        
	                               #end  
            
                  
              
              
              
              
              
              </select>
              </span> <span id="socNegeri_2b">
              <input name="socNegeri_dis"  type="text"  class="disabled" id="socNegeri_dis" style="width: 265px; text-transform:uppercase;" value="" maxlength="70" readonly />
              <input name="socNegeri"  type="hidden"  class="$setmode" id="socNegeri" style="width: 265px; text-transform:uppercase;" value="$!negeri" maxlength="70" $setmodeR />
              </span> #end
              
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
            <td class="style38" valign="top" >#if($setmode != "disabled") #end</td>
            <td class="style38" ><div align="left">#if($setmode != "disabled") Bandar #else
                Bandar
                #end </div></td>
            <td><div align="right">:</div></td>
            <td><div id="check_bandar" ></div>
              #if($setmode == "disabled")
              #foreach($listdaerah in $listBandarbyNegeri)            
              
              #if($daerah==$listdaerah.id)            
              #set($listDaerahbyNegeriK=$listdaerah.kod)
              #set($listDaerahbyNegeriN=$listdaerah.nama) 
              #end 
              
              #end
              
              #if($daerah != "" && $daerah != "0")
              <input name="socBandar"  type="text"  class="$setmode" id="socNegeri" style="width: 265px; text-transform:uppercase;" value="$listDaerahbyNegeriK - $listDaerahbyNegeriN" maxlength="70" $setmodeR />
              #else
              <input name="socBandar"  type="text"  class="$setmode" id="socNegeri" style="width: 195px; text-transform:uppercase;" value="" maxlength="70" $setmodeR />
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
              
              #if($daerah!="" && $daerah!="0" ) <span id="socBandar_1a">
              <select name="socBandar" id="socBandar" class="autoselect" $setmode   style="text-transform:uppercase;" onBlur="uppercase()"  > 
              <!--onclick="CheckBandar()"  -->
                <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                
                
                
                
                
                
                    
                                  #foreach($listdaerah in $listBandarbyNegeri)                                 
                                  #if($daerah!=$listdaerah.id)
              
                    
                
                
                
                
                
                <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                
                
                
                
                
                
                                                       
                                  #end    
	                              #end 
            
                  
              
              
              
              
              
              </select>
              </span> <span id="socBandar_1b">
              <input name="socBandar_dis"  type="text"  class="disabled" id="socBandar_dis" style="width: 265px; text-transform:uppercase;" value="$listDaerahbyNegeriK - $listDaerahbyNegeriN" maxlength="70" readonly />
              <input name="socBandar"  type="hidden"   id="socBandar" style="width: 265px; text-transform:uppercase;" value="$daerah" maxlength="70"  />
              </span> #else <span id="socBandar_2a">
              <select name="socBandar" id="socBandar" class="autoselect" $setmode style="text-transform:uppercase;" onBlur="uppercase()"  >
              <!-- onclick="CheckBandar()" -->
                <option value="">Sila Pilih Bandar</option>
                
                
                
                
                
                
                                  
                                  #foreach($listDaerah in $listBandarbyNegeri)
              
                    
                
                
                
                
                
                <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                
                
                
                
                
                
                    
	                               #end                                  
            
                  
              
              
              
              
              
              </select>
              </span> <span id="socBandar_2b">
              <input name="socBandar_dis"  type="text"  class="disabled" id="socBandar_dis" style="width: 265px; text-transform:uppercase;" value="" maxlength="70" readonly />
              <input name="socBandar"  type="hidden"   id="socBandar" style="width: 265px; text-transform:uppercase;" value="$!daerah" maxlength="70"  />
              </span> #end
              #end </td>
          </tr>
          
          
          
          
          
          
          
          
          
          
          <tr>
            <td class="style38" valign="top" >&nbsp;</td>
            <td class="style38" >No Tel (R/P)</td>
            <td>&nbsp;</td>
            <td><span id="no_tel_1a">
              <input name="no_tel" type="text" id="no_tel" style="text-transform:uppercase;" onBlur="uppercase()" value="$no_tel" size="14" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" $setmodeR class="$setmode" />
              </span> <span id="no_tel_1b">
              <input name="no_tel" type="text" id="no_tel" style="text-transform:uppercase;" onBlur="uppercase()" value="$no_tel" size="14" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" readonly class="disabled" />
              </span></td>
          </tr>
          <tr id="tr_hp">
            <td class="style38" valign="top" >&nbsp;</td>
            <td class="style38" >No Tel (HP)</td>
            <td>&nbsp;</td>
            <td><span id="no_hp_1a">
              <input name="no_hp" type="text" id="no_hp" style="text-transform:uppercase;" onBlur="uppercase()" value="$no_hp" size="14" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" $setmodeR class="$setmode" />
              </span> <span id="no_hp_1b">
              <input name="no_hp" type="text" id="no_hp" style="text-transform:uppercase;" onBlur="uppercase()" value="$no_hp" size="14" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" readonly class="disabled" />
              </span></td>
          </tr>
          #end
        </table>
        </fieldset></td>
    </tr>
  </table>
  #if ($setmode != "disabled" )
  <table>
    <tr>
      <td><!--
    <span class="style3 style44 style1"><em>Perhatian</em></span><span class="style3 style50"><em> : Sila masukkan salah satu nombor MyID dan pastikan label berwarna <span class="style1">merah</span> diisi.</em></span>
    -->
        <span class="perhatian style1 style3"><em>Perhatian</em></span><span class="style3 style50"><em> : Sila masukkan salah satu nombor MyID dan pastikan label bertanda <span class="style1">*</span> diisi.</em></span></td>
    </tr>
  </table>
  #end
  <table width="100%" border="0">
    <tr align="center">
      <th scope="col"> #if ($EventStatus == 0)
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="Simpan()"/>
        <input type="button" name="cdmBatal" id="cdmBatal" value="Batal" onClick="Batal()"/>
        #if($pendaftaran=="yes")
        <!--
     <input type="button" name="cdmKembali" id="cdmKembali9" value="Kembali" onClick="kembalidaftar()"/>
     -->
        #end
        
        
        #elseif ($EventStatus == 1)
        
        #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
        <!-- <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/> -->
        #end
        
        #parse("app/ppk/syarat_kemaskini.jsp")
        
        #if($boleh_kemaskini == "yes")
        #end
        
        #if($id_Status != "152")
        #if ($duplicate != "yes")
        
		<input type="button" name="cmdKemaskini" id="cmdKemaskini1" value="Kemaskini" onClick="Kemaskini()"/>
		#end
        #end
       
        #if($flag_kemaskini_selesai != "yes")
        
        <script>
	document.getElementById('cmdKemaskini1').style.display = "none";
	</script>
        #end
        <!--
    <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onClick ="seterusnya_tab()"/>
    -->
        #if($pendaftaran!="yes" )
        <!-- && $!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N" -->
        <!-- 
        #if($duplicate=="yes")
        <p align="center"><font color="red" size="1">No kad pengenalan si mati telah wujud. Sila cetak Surat Batal Permohonan untuk membatalkan permohonan ini.</font></p>
        	
        #end -->
        <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
        <!--
     <input type="button" name="cdmKembali" id="cdmKembali9" value="Kembali" onClick="kembalix()"/>
     -->
        <!--
     <input type="button" name="cdmKembali" id="cdmKembali" value="Kembali" onClick="kembalix()"/>
     -->
        #end
        #if($pendaftaran=="yes")
        <!-- 
        #if($duplicate=="yes")
        <p align="center"><font color="red" size="1">No kad pengenalan si mati telah wujud. Sila cetak Surat Batal Permohonan untuk membatalkan permohonan ini.</font></p>
        	
        #end -->
        <input type="button" name="cdmCetak2" id="cdmCetak2" value="Cetak" onClick="javascript:setTable('tableReport')"/>
        <!--
      <input type="button" name="cdmKembali" id="cdmKembali8" value="Kembali" onClick="kembalidaftar()"/>
      -->
        #end
        #end </th>
    </tr>
  </table>
  <fieldset id="tableReport" style="display:none;" >
  <legend><strong>Senarai Laporan</strong></legend>
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
      <td><a href="#" class="style2 " onClick="javascript:cetakDokumen('$IdPermohonan','PPKSemakanSek8')"> Senarai Semak </a></td>
    </tr>
  	#if (($duplicate == "yes") || ($id_Status == "152") || ($id_Status == "9919201"))
	<tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakSuratBatalPermohonan('$noFail')">Surat Batal Permohonan</a></td>
    </tr>
	
  	#else
    
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:cetak('$noFail','$idFail')"> Kulit Fail </a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:cetakDokumen('$IdPermohonan','RPF')"> Rekod Pendaftaran Fail </a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:cetakSuratAkuanTerima('$noFail')">Surat Akuan Terima</a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:cetakSuratIringanBorangB('$noFail')">Surat Iringan Borang B</a></td>
    </tr>
    <tr>
      <td > #parse("app/ppk/SuratMintaMaklumat.jsp") </td>
    </tr>
    #set($NoFail = $noFail)
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:cetakSuratPeringatanMaklumatTambahan('$NoFail')">Surat Peringatan</a></td>
    </tr>
   
  <!-- Edited by Salnizam SDS 10.2.3.1.6 A 
   <tr>
      <td ><a href="#" class="style2 " onClick="javascript:cetakBorangB('$NoFail','$idFail','$flagFromSenaraiFailSek8','$flagForView')">Borang B (Cetakan Secara Portrait)</a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:cetakBorangB2('$NoFail','$idFail','$flagFromSenaraiFailSek8','$flagForView')">Borang B (Cetakan Secara Landskap)</a></td>
    </tr>  -->
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:cetakBorangB2_A4('$NoFail','$idFail','$flagFromSenaraiFailSek8','$flagForView')">Borang B (A4)</a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:semakMTPermohonan()">Hantar Borang B ke Mahkamah Tinggi</a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2 " onClick="javascript:semakBorangC('$NoFail')">Semak Borang C</a></td>
    </tr>
    #end
  </table>
  </fieldset>
  <p align="right">CL - 01 - 64d$duplicate</p>
  </fieldset>
  <table width="100%">
    <tr>
      <td><div align="right"> #if($pendaftaran == "")
          #if ($flagFromSenaraiFailSek8 == '' && $flagForView  == '' && $flagFromSenaraiPermohonanSek8 == '' && $pendaftaran == "")<a href="javascript:javascript:Kembali()" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if ($flagFromSenaraiFailSek8 == 'yes')<a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagForView  == 'yes')<a href="javascript:ForView('$noFail')" class="style2"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style2"  ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style2"  ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3current.png" alt="" border="0" title="Pendaftaran Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>
          #if ($noFail != "")
          #if (($duplicate != "yes") && ($id_Status != "152"))
          <a href="javascript:seterusnya_tab()" class="style2"  >
          <img src="../img/4enable2.png" alt="" border="0" title="Maklumat Permohonan"/></a>
          #else
          <img src="../img/4disable2.png" alt="" border="0" title="Maklumat Permohonan"/>
          #end 
          #else
          
          <img src="../img/4disable2.png" alt="" border="0" title="Maklumat Permohonan"/>#end </span> 
          
          #else
          #if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style2"  ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style2"  ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/2current.png" alt="" border="0" title="Pendaftaran Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>
          #if ($noFail != "")
          #if (($duplicate != "yes")  && ($id_Status != "152"))
          <a href="javascript:seterusnya_tab()" class="style2"  >
          <img src="../img/3enable2.png" alt="" border="0" title="Maklumat Permohonan"/></a>
          #else
          <img src="../img/3disable.png" alt="" border="0" title="Maklumat Permohonan"/>
          #end 
          #else<img src="../img/3disable.png" alt="" border="0" title="Maklumat Permohonan"/>#end 
          </span> #end </div></td>
    </tr>
  </table>
  <input type="hidden" name="command" />
  <input type="hidden" name="frmFrom" />
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


function Batal() {

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method = "POST";
    //document.f1.command.value="Kemaskini_daftar_pemohon";
    document.f1.command.value="Seterusnya";
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
function seterusnya_tab() {/*
	document.f1.method = "POST";
	document.f1.command.value="seterusnya";
	document.f1.action = "";
	document.f1.submit();
	*/
	//razman tambah
	document.f1.command.value = "tab";
	document.f1.action = "$EkptgUtil.getTabID('Seksyen 8',$portal_role)?_portal_module=FrmPrmhnnSek8Internal";
	document.f1.submit();
}
function Kemaskini() {
	//alert("Kemaskini");
		document.f1.method = "POST";
		document.f1.command.value="Kemaskini_daftar_pemohon";
		var idPermohonan = document.f1.idPermohonan.value;
		//document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal?command=Kemaskini_daftar_pemohon&idPermohonan="+idPermohonan;
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



function Simpan() {
//alert(document.f1.jenis_pemohon.value)

    var dt=document.f1.txtTarikhMati
	var dm=document.f1.txdTarikhMohon
	
	var dob_code = document.f1.txtNoKPBaruSimati1.value;
	
	
	if(document.f1.jenis_pemohon.value == "2")
	{var dob_code1 = document.f1.txtNoKPBaruPemohon1.value;}
	
	if(dob_code.charAt(0)<3)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}
	
	if(document.f1.jenis_pemohon.value == "2")
	{
	if((dob_code1.charAt(0)<3))
	{
	 var dum1 = "20";
	}
	else
	{
	var dum1 = "19";
	}
	}
	
	 var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	 var date_dob = new Date(yr_dob, mon_dob, dt_dob);
	 	
	 var negeri_code = document.f1.txtNoKPBaruSimati2.value;
	 if(document.f1.jenis_pemohon.value == "2")
	 {
	  var tt1 = dob_code1.charAt(4)+""+dob_code1.charAt(5)+"/"+dob_code1.charAt(2)+""+dob_code1.charAt(3)+"/"+dum1+dob_code1.charAt(0)+""+dob_code1.charAt(1);	
	 var dt_dob1   = parseInt(tt1.substring(0,2),10);
     var mon_dob1  = parseInt(tt1.substring(3,5),10)-1;
     var yr_dob1   = parseInt(tt1.substring(6,10),10);
	 var date_dob1 = new Date(yr_dob1, mon_dob1, dt_dob1);
	 	
	 var negeri_code1 = document.f1.txtNoKPBaruPemohon2.value;
	}
	
	if (document.f1.txtNoKPBaruSimati1.value == "" && document.f1.txtNoKPBaruSimati2.value == "" && document.f1.txtNoKPBaruSimati3.value == "" && document.f1.senaraisemak.value == "")
	{
		alert("Sila pastikan anda memuatnaik dokumen sokongan.")
	}
	
    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
   var str1  = document.getElementById("txtTarikhMati").value;
   var str2  = document.getElementById("txdTarikhMohon").value;
   
   
   
   var dt1   = parseInt(str1.substring(0,2),10);
   var mon1  = parseInt(str1.substring(3,5),10)-1;
   var yr1   = parseInt(str1.substring(6,10),10);
   
   var dt2   = parseInt(str2.substring(0,2),10);
   var mon2  = parseInt(str2.substring(3,5),10)-1;
   var yr2   = parseInt(str2.substring(6,10),10);
   
   var date1 = new Date(yr1, mon1, dt1);
   var date2 = new Date(yr2, mon2, dt2);
   
   document.f1.tahun.value = parseInt(str2.substring(6,10),10);;
   
  // alert("Q"+document.f1.txtNamaPemohon.value);
  // buang ni untuk demo
	if (document.f1.socDaerah.value=="0") {
		alert("Sila pilih daerah.");
		document.f1.socDaerah.focus();
	}
	
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
	
	
	else if (document.f1.pemohonsimatikp1 != null && document.f1.pemohonsimatikp1.value == "1") {
		alert("No. MyID baru simati dan pemohon adalah sama. \nSila pastikan simati dan pemohon adalah individu yang berbeza");
		document.f1.txtNoKPBaruPemohon3.focus();		
	}
	else if (document.f1.pemohonsimatikp2 != null && document.f1.pemohonsimatikp2.value == "2") {	
		alert("No. MyID lama simati dan pemohon adalah sama. \nSila pastikan simati dan pemohon adalah individu yang berbeza");
		document.f1.txtNoKPLamaPemohon.focus();		
	}
	else if (document.f1.pemohonsimatikp3 != null && document.f1.pemohonsimatikp3.value == "3") {
		alert("No. MyID lain simati dan pemohon adalah sama. \nSila pastikan simati dan pemohon adalah individu yang berbeza");
		document.f1.txtNoKPLainPemohon.focus();		
	}
	
	
	
	
	
	else if (document.f1.txdTarikhMohon.value == "") {
		alert("Sila masukkan tarikh mohon");
		document.f1.txdTarikhMohon.focus();
	}
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
	else if ((document.f1.socJenisKPLainSimati.value!="0"  && document.f1.socJenisKPLainSimati.value!="") && document.f1.txtNoKPLainSimati.value=="") {
		alert("Sila masukkan MyID Lain Simati");
		document.f1.txtNoKPLainSimati.focus();
	}
	else if (document.f1.txtNoKPLainSimati.value!="" && document.f1.socJenisKPLainSimati.value=="0") {
		alert("Sila pilih jenis MyID Lain Simati");
	}
	else if (document.f1.txtNamaSimati.value=="") {
		alert("Sila masukkan nama Simati");
		document.f1.txtNamaSimati.focus();
	} 
	else if (document.f1.txtTarikhMati.value=="") {
		alert("Sila masukkan tarikh mati Simati");
		document.f1.txtTarikhMati.focus();
	}
	
	else if ((isNaN(document.f1.txtNoKPBaruPemohon1.value)) && (document.f1.jenis_pemohon.value == "2" ) ) {
		alert("Sila masukkan nombor sahaja");
		document.f1.txtNoKPBaruPemohon1.focus();
	}
	
	else if ((isNaN(document.f1.txtNoKPBaruPemohon2.value)) && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila masukkan nombor sahaja");
		document.f1.txtNoKPBaruPemohon2.focus();
	}
	else if ((isNaN(document.f1.txtNoKPBaruPemohon3.value)) && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila masukkan nombor sahaja");
		document.f1.txtNoKPBaruPemohon3.focus();
	}
	else if ((document.f1.txtNoKPBaruPemohon1.value !="" && document.f1.txtNoKPBaruPemohon1.value.length<6) && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila masukkan MyID Baru Pemohon dengan lengkapnya");
		document.f1.txtNoKPBaruPemohon1.focus();
	}
	else if ((document.f1.txtNoKPBaruPemohon2.value !="" && document.f1.txtNoKPBaruPemohon2.value.length<2) && (document.f1.jenis_pemohon.value == "2")) {
		alert("Sila masukkan MyID Baru Pemohon dengan lengkapnya");
		document.f1.txtNoKPBaruPemohon2.focus();
	}
	else if ((document.f1.txtNoKPBaruPemohon3.value !="" && document.f1.txtNoKPBaruPemohon3.value.length<4) && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila masukkan MyID Baru Pemohon dengan lengkapnya");
		document.f1.txtNoKPBaruPemohon3.focus();
	}
	
	
	//delete ni dulu untuk DEMO
	//else if ((document.f1.txtNoKPBaruPemohon1.value=="" && document.f1.txtNoKPBaruPemohon2.value=="" && document.f1.txtNoKPBaruPemohon3.value=="" && document.f1.txtNoKPLamaPemohon.value=="" && document.f1.socJenisKPLainPemohon.value=="0" && document.f1.txtNoKPLainPemohon.value=="") && (document.f1.jenis_pemohon.value == "2" )) {
	//	alert("Sila masukkan salah satu MyID Pemohon.");
	//	document.f1.txtNoKPBaruPemohon1.focus();
	//}
	
	
	
	
	else if ((document.f1.txtNoKPBaruPemohon1.value!="" && document.f1.txtNoKPBaruPemohon2.value=="" && document.f1.txtNoKPBaruPemohon3.value=="") && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila lengkapkan MyID Pemohon.");
		document.f1.txtNoKPBaruPemohon2.focus();
	}
	else if ((document.f1.txtNoKPBaruPemohon1.value!="" && document.f1.txtNoKPBaruPemohon2.value!="" && document.f1.txtNoKPBaruPemohon3.value=="") && (document.f1.jenis_pemohon.value == "2") ) {
		alert("Sila lengkapkan MyID Pemohon.");
		document.f1.txtNoKPBaruPemohon3.focus();
	}
	else if ((document.f1.txtNoKPBaruPemohon1.value!="" && document.f1.txtNoKPBaruPemohon2.value=="" && document.f1.txtNoKPBaruPemohon3.value!="") && (document.f1.jenis_pemohon.value == "2" ) ) {
		alert("Sila lengkapkan MyID Pemohon.");
		document.f1.txtNoKPBaruPemohon2.focus();
	}
	else if ((document.f1.txtNoKPBaruPemohon2.value!="" && document.f1.txtNoKPBaruPemohon1.value=="" && document.f1.txtNoKPBaruPemohon3.value!="") && (document.f1.jenis_pemohon.value == "2" ) ) {
		alert("Sila lengkapkan MyID Pemohon.");
		document.f1.txtNoKPBaruPemohon1.focus();
	}
	else if ((document.f1.txtNoKPBaruPemohon2.value!="" && document.f1.txtNoKPBaruPemohon1.value=="" && document.f1.txtNoKPBaruPemohon3.value=="") && (document.f1.jenis_pemohon.value == "2") ) {
		alert("Sila lengkapkan MyID Pemohon.");
		document.f1.txtNoKPBaruPemohon1.focus();
	}
	
	
	
	else if ((document.f1.txtNoKPBaruPemohon3.value!="" && document.f1.txtNoKPBaruPemohon1.value=="" && document.f1.txtNoKPBaruPemohon2.value=="") && (document.f1.jenis_pemohon.value == "2" ) ) {
		alert("Sila lengkapkan MyID Pemohon.");
		document.f1.txtNoKPBaruPemohon1.focus();
	}
	
	
	
	
	else if ((document.f1.socJenisKPLainPemohon.value!="0" && document.f1.txtNoKPLainPemohon.value=="") && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila masukkan MyID Lain Pemohon");
		document.f1.txtNoKPLainPemohon.focus();
	}
	else if ((document.f1.txtNoKPLainPemohon.value!="" && document.f1.socJenisKPLainPemohon.value=="0") && (document.f1.jenis_pemohon.value == "2" )) {
		alert("Sila pilih jenis MyID Lain Simati");
	}
	////
	else if(document.f1.txtNoKPLamaSimati.value != "" && document.f1.txtNoKPLamaSimati.value != "TDK" && document.f1.txtNoKPLamaSimati.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Simati");
		document.f1.txtNoKPLamaSimati.focus();	
	}
	else if(document.f1.txtNoKPLamaPemohon.value != "" && document.f1.txtNoKPLamaPemohon.value.length<7)
	{
		alert("Sila lengkapkan MyID Lama Pemohon");
		document.f1.txtNoKPLamaPemohon.focus();	
	}
	
	
	else if (document.f1.txtNamaPemohon[0] != 'null' && document.f1.txtNamaPemohon[0].value=="" && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8") ) {
		alert("Sila masukkan nama pemohon.");
		document.f1.txtNamaPemohon[0].focus();
	} 
	
	else if (document.f1.txtPoskod[0] != 'null' && document.f1.txtPoskod[0].value != "" && document.f1.txtPoskod[0].value.length < 5 && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8") ) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskod[0].focus();
	}
	else if (document.f1.txtPoskod[1] != 'null' && document.f1.txtPoskod[1].value != "" && document.f1.txtPoskod[1].value.length < 5 && (document.f1.taraf_penting.value == "6" || document.f1.taraf_penting.value == "8") ) {
		alert("Sila masukkan nombor poskod dengan lengkapnya");
		document.f1.txtPoskod[1].focus();
	}
	
	
	
	else if (isDate(dm.value)==false){
		dm.focus()
		return false
	}
	else if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
	

	
	else if (date2 > currentTime){
	 
		alert("Sila pastikan tarikh mohon tidak melebihi dari tarikh hari ini.");
		//document.f1.txdTarikhMohon.value=currentDate;		
		document.f1.txdTarikhMohon.focus();
		return;
	}
	
	
	else if ((document.f1.txtAlamat1[0].value == "" || document.f1.socNegeri[0].value == "" || document.f1.socNegeri[0].value == "0" || document.f1.txtPoskod[0].value == "" ) && (document.f1.taraf_penting.value != "6" && document.f1.taraf_penting.value != "8")) {
	
		alert("Sila masukkan alamat tetap pemohon dengan lengkap");
		document.f1.txtAlamat1[0].focus();
		return;
		
	}
	
	
	
	else if ((document.f1.txtAlamat1[1].value == "" || document.f1.socNegeri[1].value == "" || document.f1.socNegeri[1].value == "0" || document.f1.txtPoskod[1].value == "")&& (document.f1.taraf_penting.value == "6" || document.f1.taraf_penting.value == "8")) {
	
		alert("Sila masukkan alamat tetap pemohon dengan lengkap");
		document.f1.txtAlamat1[1].focus();
		return;
	}
	
	
	
	else if (document.f1.txtNoKPBaruSimati1.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaruSimati1.focus()
		return false
	}
	
	else if (document.f1.jenis_pemohon.value == "2" && document.f1.txtNoKPBaruPemohon1.value != "" && isIc(tt1)==false){
		document.f1.txtNoKPBaruPemohon1.focus()
		return false
	}
	/*
	else if (document.f1.txtNoKPBaruSimati2.value != "" && (negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&

		 negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  negeri_code!="15" && negeri_code!="58" &&
		   negeri_code!="16" && 
		   negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaruSimati2.focus()
	return;
	
	}
	
	else if (document.f1.jenis_pemohon.value == "2"  && document.f1.txtNoKPBaruPemohon2.value != "" &&(negeri_code1!="01" && negeri_code1!="21" && negeri_code1!="22" && negeri_code1!="23" && negeri_code1!="24" && negeri_code1!="02" && negeri_code1!="25" && negeri_code1!="26" && negeri_code1!="27" && negeri_code1!="03" && negeri_code1!="28" && negeri_code1!="29" && negeri_code1!="04" && negeri_code1!="30" && negeri_code1!="05" && negeri_code1!="31" && negeri_code1!="59" && negeri_code1!="06" && negeri_code1!="32" && negeri_code1!="33" && negeri_code1!="07" && negeri_code1!="34" && negeri_code1!="35" && negeri_code1!="08" && negeri_code1!="36" && negeri_code1!="37" && negeri_code1!="38" && negeri_code1!="39"  && negeri_code1!="09" && negeri_code1!="40" && negeri_code1!="10" && negeri_code1!="41" && negeri_code1!="42" && negeri_code1!="43" && negeri_code1!="44" && negeri_code1!="11" && negeri_code1!="45" && negeri_code1!="46" && 
		negeri_code1!="12" && negeri_code1!="47" && negeri_code1!="48" && negeri_code1!="49" &&
		 negeri_code1!="13" && negeri_code1!="50" && negeri_code1!="51" && negeri_code1!="52" && negeri_code1!="53" && 
		 negeri_code1!="14" && negeri_code1!="54" && negeri_code1!="55" && negeri_code1!="56" && negeri_code1!="57" &&
		  negeri_code1!="15" && negeri_code1!="58" &&
		   negeri_code1!="16" && 
		   negeri_code1!="82" && negeri_code1!="71" && negeri_code1!="88" && negeri_code1!="99"))
	{
	alert("Sila masukkan kod negeri yang sah");
	document.f1.txtNoKPBaruPemohon2.focus()
	return;
	
	}
	
	*/
	
   else if(date2 < date1)
   {
      alert("Sila pastikan tarikh mati tidak melebihi dari tarikh mohon.");
	 
      document.f1.txtTarikhMati.focus();
   } 
   /*
   
   else if(document.f1.no_kp1 != null && document.f1.no_kp1.value == 'yes')
   {
      alert("No kp baru simati telah wujud!");	
	/*  
	  url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_baru";
	target = "check_kp_1";
	doAjaxUpdater(document.f1, url, target, actionName);
	return;
*/
	 
  /* } */ 
   
    //else if(document.f1.no_kp2 != null && document.f1.no_kp2.value == 'yes')
  // {
   //   alert("No kp lama simati telah wujud!");
	 
	/* 
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lama";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);
	return;
	*/

  // }
   
    else if(document.f1.no_kp3 != null && document.f1.no_kp3.value == 'yes')
   {
      alert("No kp lain simati telah wujud!");	
	 /* 
	  url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lain";
	target = "check_kp_3";
	doAjaxUpdater(document.f1, url, target, actionName);
	return;	*/  
   }
   
   
  
	else{
	
	
	
		input_box = confirm("Adakah anda pasti ?");
		if (input_box == true) {
			if(document.f1.no_kp1 != null && document.f1.no_kp1.value == 'yes')
				{
				//alert("Test1");
				document.f1.command.value="Simpanx";
				}
			else
				{
				//alert("Test2");
				document.f1.command.value="Simpanx";
				
				}
			
			//tambah
			
			var idPermohonan = document.f1.idpermohonan.value;
			var txtNoKPBaruSimati1 = document.f1.txtNoKPBaruSimati1.value;
			var txtNoKPBaruSimati2 = document.f1.txtNoKPBaruSimati2.value;
			var txtNoKPBaruSimati3 = document.f1.txtNoKPBaruSimati3.value;
			var txtNoKPLamaSimati = document.f1.txtNoKPLamaSimati.value;
			var socJenisKPLainSimati = document.f1.socJenisKPLainSimati.value;
			var txtNoKPLainSimati = document.f1.txtNoKPLainSimati.value;
			var idFail = document.f1.idFail2.value;
			//alert("id_Fail = "+idFail);
			var idPemohon = document.f1.idPemohon.value;
			var idpermohonan = document.f1.idpermohonan.value;
			var txtNoFail = document.f1.txtNoFail.value;
			var txdTarikhMohon = document.f1.txdTarikhMohon.value;
			var idSimati = document.f1.idSimati.value;
			var txtNamaSimati = document.f1.txtNamaSimati.value;
			var txtTarikhMati = document.f1.txtTarikhMati.value;			
			var txtNoKPBaruPemohon1 = document.f1.txtNoKPBaruPemohon1.value;
			var txtNoKPBaruPemohon2 = document.f1.txtNoKPBaruPemohon2.value;
			var txtNoKPBaruPemohon3 = document.f1.txtNoKPBaruPemohon3.value;
			var txtNoKPLamaPemohon = document.f1.txtNoKPLamaPemohon.value;
			var txtNoKPLainPemohon = document.f1.txtNoKPLainPemohon.value;
			var socJenisKPLainPemohon = document.f1.socJenisKPLainPemohon.value;	
			var txtNamaPemohon = document.f1.txtNamaPemohon[0].value;
			
			if(document.f1.txtAlamat1[0].value != "")
			{
				var txtAlamat1 = document.f1.txtAlamat1[0].value;	
			}
			
			if(document.f1.txtAlamat2[0].value != "")
			{
				var txtAlamat2 = document.f1.txtAlamat2[0].value;
			}
			else if (document.f1.txtAlamat2[0].value == "")
			{
					var txtAlamat2 = "";
			}

			
			if(document.f1.txtAlamat3[0].value != "")
			{
				var txtAlamat3 = document.f1.txtAlamat3[0].value;
			}
			else if(document.f1.txtAlamat3[0].value == "")
			{
				var txtAlamat3 = "";
			}
			
			
			if(document.f1.txtPoskod[0].value != "")
			{
				var txtPoskod = document.f1.txtPoskod[0].value;
				
			}
			
			if(document.f1.no_hp[0].value != "")
			{
				var no_hp = document.f1.no_hp[0].value;
			}
			else if(document.f1.no_hp[0].value == "")
			{
				var no_hp = "";
			}
				

			
			if(document.f1.no_tel[0].value != "")
			{
				var no_tel = document.f1.no_tel[0].value;
			}
			else if(document.f1.no_tel[0].value == "")
			{
					var no_tel = "";				
			}
			
			
			if(document.f1.socNegeri[0].value != "")
			{
				var socNegeri = document.f1.socNegeri[0].value;
				
			}
			
			if(document.f1.socBandar[0].value != "")
			{
				var socBandar = document.f1.socBandar[0].value;
				
			}

			//var txtBandar = document.f1.txtBandar.value;
			var socDaerahinput = document.f1.socDaerahinput.value;
			var txtUmurSimati = document.f1.txtUmurSimati.value;
			var socJantinaSimati = document.f1.socJantinaSimati.value;
			var txtUmurPemohon = document.f1.txtUmurPemohon.value;
			var socJantinaPemohon = document.f1.socJantinaPemohon.value; 
			var socSaudaraWaris = document.f1.socSaudaraWaris.value; 
			var taraf_penting = document.f1.taraf_penting.value; 
			var nama_pelbagainegara = document.f1.nama_pelbagainegara.value; 
			var jenis_pej = document.f1.jenis_pej.value
			var jenis_pemohon = document.f1.jenis_pemohon.value; 
			var negid = document.f1.negid.value;   
			var tarikh_daftar = document.f1.txdTarikhMohon.value; 
			var eventStatus = "1";

			
			
			

			
			document.f1.method = "POST";
			document.f1.command.value="Simpanx";
			document.f1.eventStatus.value="1";
			document.f1.action = "";
			document.f1.submit();
			
		
		//"&jenis_pej="+jenis_pej+
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

function cetakSuratPeringatanMaklumatTambahan(noFail) {

var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratPeringatanMaklumatTambahan&flagReport=S";
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

function uploadSuppDoc(id,IdSimati)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppk.SkrinPopupUploadDokumen?&id_Permohonan="+id+"&IdSimati="+IdSimati+"&id_jenisDoc=99201";
	var hWnd = window.open(url,'printuser','width=350,height=200, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
	
	
}

function deleteSuppDoc()
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.method = "POST";
	document.f1.command.value="deleteSuppDoc";
	document.f1.eventStatus.value="1";
	document.f1.action = "";
	document.f1.submit();
	}
	else
		{
		return
		}
	
	
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

	function cetakBorangB2_A4(noFail,idfail,flag,flagB) {
	    //var url = "../servlet/ekptg.report.ppk.BorangB2_A4?nofail="+noFail+"&idfail="+idfail;  
	    // 2017/05/02 Menggunakan satu class laporan   
	    var url = "../servlet/ekptg.report.ppk.PPKBorangB?template=BorangB2_A4&idfail="+idfail;  
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
		/*if(flag != "yes" && flagB != "yes"){
		    document.f1.command.value="getDaftarStatus";
		//	document.f1.mode.value="getHtaamStatus";		
			document.f1.action="";
			document.f1.submit();
		}*/
		
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
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeri.focus();
  return;
	  	
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
if('$val_tab' != "" && '$val_tab' != null)
{

   window.location.hash='$val_tab';
  
   var nextFieldID = '$!val_tab';
   document.getElementById(nextFieldID).focus();
  
}
else
{
	 window.location.hash='main_table';
     document.getElementById('main_table').focus();
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
	document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal";
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

//function check_kp_lama()
//{
//document.f1.check_no_kp_lama_simati.value = document.f1.txtNoKPLamaSimati.value;

//}

function check_kp_lama()
{
	//alert("TEST");
	document.f1.check_no_kp_lama_simati.value = document.f1.txtNoKPLamaSimati.value;
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lama";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);
	return;

} 

function check_kp_lain()
{
document.f1.check_no_kp_lain_simati.value = document.f1.txtNoKPLainSimati.value;

}

function check_alamat()
{

var x = document.getElementById("txtAlamat1").value;


}



function check_pengenalan_simati_1()
{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_baru";
	target = "check_kp_1";
	doAjaxUpdater(document.f1, url, target, actionName);
	return;
}


function check_pengenalan_simati_2()
{
	/*
//alert(document.f1.txtNoKPBaruSimati1.value);
if(document.f1.txtNoKPLamaSimati.value != "" && document.f1.txtNoKPLamaSimati.value != "TDK")
{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lama";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);
	return;
}
*/
}




function check_pengenalan_simati_3()
{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lain";
	target = "check_kp_3";
	doAjaxUpdater(document.f1, url, target, actionName);
	return;
}

function check_hutang()
{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_hutang";
	target = "check_kp_3";
	doAjaxUpdater(document.f1, url, target, actionName);
	return;
}



function check_pengenalan_simati_1_onload()
{
 
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_baru_onload";
	target = "check_kp_1";
	doAjaxUpdater(document.f1, url, target, actionName);
}


function check_pengenalan_simati_2_onload()
{
	/*
if(document.f1.txtNoKPLamaSimati.value != "" && document.f1.txtNoKPLamaSimati.value != "TDK")
{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lama_onload";
	target = "check_kp_2";
	doAjaxUpdater(document.f1, url, target, actionName);
	}
	*/
}
function check_pengenalan_simati_3_onload()
{

	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_simati_kp_lain_onload";
	target = "check_kp_3";
	doAjaxUpdater(document.f1, url, target, actionName);
	
}



function get_bandar_simati()
{
	url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
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

/*
function pilih_taraf()
{


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
		{document.f1.jenis_pemohon_display.value = "02-INDIVIDU"}
		
		
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
		
		
		if(document.getElementById('no_hp_1a') != null)
		{
		document.getElementById('no_hp_1a').style.display="";
		document.getElementById('no_hp_1b').style.display="none";
		}	
		
		
		document.getElementById('tr_hp').style.display="";
	
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
		
		if(document.getElementById('no_hp_1a') != null)
		{
		document.getElementById('no_hp_1a').style.display="none";
		document.getElementById('no_hp_1b').style.display="none";
		}	
		

}

else if(document.f1.taraf_penting.value == "2" || document.f1.taraf_penting.value == "4")
{
	
	
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
		
		}
		else{
		document.getElementById('amanah').style.display="none";
		document.getElementById("kp1").style.display="none";	
		document.getElementById("kp2").style.display="none";	
		document.getElementById("kp3").style.display="none";
		
		if(document.getElementById('no_hp_1a') != null)
		{
		document.getElementById('no_hp_1a').style.display="none";
		document.getElementById('no_hp_1b').style.display="none";
		}		
		document.getElementById('tr_hp').style.display="none";			
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
					 		document.f1.socNegeri[0].disabled = ''; 
					 		 document.f1.socBandar[0].disabled = ''; 

}
*/




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


url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
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
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_kpbaru_pemohon";
	target = "check_kp_p1";
	doAjaxUpdater(document.f1, url, target, actionName);


//alert("baru betol")


}


function kp_lama_pemohon()
{

var kplama = document.f1.txtNoKPLamaPemohon.value;

document.f1.check_no_kp_lama_pemohon.value = kplama;
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
	actionName = "check_kplama_pemohon";
	target = "check_kp_p2";
	doAjaxUpdater(document.f1, url, target, actionName);



//alert("lama betol")
}

function kp_lain_pemohon()
{

var kplain = document.f1.txtNoKPLainPemohon.value;


document.f1.check_no_kp_lain_pemohon.value = kplain;
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
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
var currentTime = new Date();

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

if(currentTime>myDate)
{
$jquery("#kpi_ppk").html("<span  style='color:red'><blink>"+tarikhJangkaTerima+"</blink></span>");
}
else
{
$jquery("#kpi_ppk").html("<span  style='color:red'>"+tarikhJangkaTerima+"</span>");
}

//$jquery("#kpi_ppk").html("<span  style='color:red'>"+tarikhJangkaTerima+"</span>");

} else {
$jquery("#kpi_ppk").html("<span  style='color:red'></span>");
}


} 

	function cetakSuratBatalPermohonan(noFail) {
	 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
	 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonan1&flagReport=S";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function cetakDokumen(id,template) {
	    var url = "../servlet/ekptg.report.frmPaparDokumenByPermohonan?idpermohonan="+id+"&dirfolder=ppk&template="+template;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
//	function semakMTPermohonan() {
//	    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=borangPermohonan";
//		var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
//	    if ((document.window != null) && (!hWnd.opener))
//		hWnd.opener = document.window;
//	    if (hWnd.focus != null) hWnd.focus();
//	}
	
	function myFunctionView(id_permohonan){
		var url = "?_portal_module=FrmPrmhnnSek8Internal&idpermohonan="+id_permohonan+"&command=papar";
		var hWnd = window.open(url,'Cetak','width=1920,height=1080, resizable=no,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function myFunctionBatal(securityToken, reportName, nokp, no_fail){
		//var Alamat1 = document.f1.txdTarikhMohon.value;
		//alert("Alamat1 = " + Alamat1);
		//document.getElementById("socBandar").value
		//if (document.getElementById("txtNamaPemohon").value == "")
		//	{
		//	alert("Ralat! Sila masukkan nama Pemohon terlebih dahulu");
		//	}
		//else if (document.getElementById("txtAlamat1").value == "")
		//	{
		//	alert("Ralat! Sila masukkan alamat tetap Pemohon terlebih dahulu");
		//	}
		//else if (document.getElementById("txtPoskod").value == "")
		//	{
		//	alert("Ralat! Sila masukkan poskod Pemohon terlebih dahulu");
		//	}
		//else if (document.getElementById("socNegeri").value == "")
		//	{
		//	alert("Ralat! Sila masukkan maklumat negeri Pemohon terlebih dahulu");
		//	}
		//else if (document.getElementById("socBandar").value == "")
		//	{
		//	alert("Ralat! Sila masukkan maklumat bandar Pemohon terlebih dahulu");
		//	}
		//else
		//	{
		//alert("TESTTT = "+securityToken+" reportName = " + reportName+ " nokp = " + nokp + "no_fail = "+ no_fail);
		var url = "../x/"+securityToken+"/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?report="+reportName+"&noKP="+nokp+"&noFail="+no_fail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		//	}
	}
	
	function paparLampiran(id_){
		var url = "../servlet/ekptg.view.ppk.util.LampiranByBlob?iDokumen="+id_+"&tablename=simati";
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function doOpen(id) {
		//alert('id : '+id);
	    var url = "../servlet/ekptg.view.ppk.DisplayBuktiKematian?id="+id;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	
	function semakMTPermohonan() {
		
		var id_permohonan  = $jquery('#id_permohonan').val();
		

		var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&idPermohonan="+id_permohonan+"&command=borangPermohonan&frmFrom=frmPrmhnnSek8DaftarSek8";
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
</script>
<!-- ADD BY PEJE -->
<script>
function calculateTarikhLahirSimati(){
	if (document.f1.txtNoKPBaruSimati1.value != ""){
		var currentTime = new Date();
		
		var noKPSimati = document.f1.txtNoKPBaruSimati1.value;		
		if(noKPSimati.length == 6){
			var a = noKPSimati.charAt(0);
			var b = noKPSimati.charAt(1);
			var c = noKPSimati.charAt(2);
			var d = noKPSimati.charAt(3);
			var e = noKPSimati.charAt(4);
			var f = noKPSimati.charAt(5);
			
			var currentYear = currentTime.getFullYear();
			var birthYear = currentYear + "";
			birthYear = birthYear.charAt(0) + "" + birthYear.charAt(1);
			var fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			
			if (fullBirthYear > currentYear ){
				birthYear = birthYear*1 - 1;
				birthYear = birthYear + "";
				fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			}
			document.f1.tarikhLahirSimati.value = e + f + "/" + c + d + "/" + fullBirthYear;
		}
	}	
}



function refreshPage()
{
	

location.reload();	
}
</script>