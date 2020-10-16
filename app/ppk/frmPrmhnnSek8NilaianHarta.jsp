<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<!--
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
-->
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style2 {color: #0000FF}
.style3 {
	font-size: 12px
}
.style4 {
	color: #0000FF
}
-->
</style>
</head>
#set($disability1 = "")
    #if ($cetakNilaiHarta==1)
    	 <body onload="cetakNilaiHarta2('$NO_FAIL')"> 
    #else
    <body onload="submitForm()">
    #end


<form id="form1" name="f1" method="post" action="">
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
  <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
  #parse("app/ppk/paging_sek8.jsp") 
  
  #parse("app/ppk/bil_fail.jsp")
  <table width="100%" border="0">
    <input type="hidden" name="command" value="">
    <input name="printOption" type="hidden" id="printOption"/>
    <input type="hidden" name="mode" value="">
    <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
    <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
    <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
    <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
    <input type="hidden" name="eventStatus">
    <input type="hidden" name="idTemp" value="$id">
    <input type="hidden" name="id" value="$id">
    <input type="hidden" name="id1" value="$id1">
    <input type="hidden" name="id2" value="$id2">
    <input type="hidden" name="id3" value="$id3">
    <input type="hidden" name="idha" value="$idha">
    <input type="hidden" name="bil" value="$jumList">
    <input type="hidden" name="noFail" value="$noFail">

    #foreach($listFail in $ViewFail)
    <input name="id_Suburusanstatus" type="hidden"  value="$listFail.id_Suburusanstatus"/>
    <input name="id_Suburusanstatusfail" type="hidden"  value="$listFail.id_Suburusanstatusfail"/>
    #end
    
    #foreach($checkMaklumatSimati in $maklumatSimati)
    	#set ($CHKTARIKH_MATI = $checkMaklumatSimati.TARIKH_MATI)
    	#set ($CHKTEMPAT_MATI = $checkMaklumatSimati.TEMPAT_MATI)
    	#set ($CHKSEBAB_MATI = $checkMaklumatSimati.SEBAB_MATI)
    	#set ($CHKALAMAT_1 = $checkMaklumatSimati.ALAMAT_1)
    	#set ($CHKPOSKOD = $checkMaklumatSimati.POSKOD)
    	#set ($CHKID_NEGERI = $checkMaklumatSimati.ID_NEGERI)
    	#set ($CHKBANDAR = $checkMaklumatSimati.BANDAR)
    	#set ($CHKJENIS_WARGA = $checkMaklumatSimati.JENIS_WARGA)
    #end
    
    #foreach($checkMaklumatPemohon in $maklumatPemohon)
    	#set ($CHKUMUR = $checkMaklumatPemohon.UMUR)
    	#set ($CHKNO_HP = $checkMaklumatPemohon.NO_HP)
    	#set ($CHKEMEL = $checkMaklumatPemohon.EMEL)
    #end
    
    #set ($strErrorMaklumatSimati = "")
    #set ($strErrorMaklumatPemohon = "")
    #if ($CHKTARIKH_MATI == "")
    	#set ($strErrorMaklumatSimati = "Maklumat Tarikh Mati tidak diisi. ")
    #end
    
    #if ($CHKJENIS_WARGA == "")
    	#set ($strErrorMaklumatSimati = $strErrorMaklumatSimati + "Jenis Kewarganageraan Simati tidak diisi. ")
    #end
    
    #if ($CHKTEMPAT_MATI == "")
    	#set ($strErrorMaklumatSimati = $strErrorMaklumatSimati + "Maklumat Tempat Mati tidak diisi.")
    #end
    
    #if ($CHKSEBAB_MATI == "")
    	#set ($strErrorMaklumatSimati = $strErrorMaklumatSimati + "Maklumat Sebab Kematian tidak diisi. ")
    #end
    
    #if ($CHKALAMAT_1 == "" || $CHKPOSKOD == "" || $CHKID_NEGERI == "" || $CHKBANDAR == "" )
    	#set ($strErrorMaklumatSimati = $strErrorMaklumatSimati + "Alamat Lengkap Simati tidak diisi. ")
    #end
    
    #if ($CHKUMUR == "")
    	#set ($strErrorMaklumatPemohon = $strErrorMaklumatPemohon + "Maklumat Umur Pemohon tidak diisi. ")
    #end
    
    
    
    #foreach($listUbah in $listUbah)
		#set($flagEmail = $listUbah.flag_email_pemohon)
		#set($flagNoTelefonBimbit = $listUbah.flag_notelefonbimbit_pemohon)
	#end
	
	#if ($CHKNO_HP == "" && $flagNoTelefonBimbit != "F" )
    	#set ($strErrorMaklumatPemohon = $strErrorMaklumatPemohon + "Maklumat No. Telefon Pemohon tidak diisi. ")
    #end
    
    #if ($CHKEMEL == "" && $flagEmail != "F")
    	#set ($strErrorMaklumatPemohon = $strErrorMaklumatPemohon + "Maklumat Emel Pemohon tidak diisi. ")
    #end
    
   <input type="hidden" name="txtbilhta" id="txtbilhta" value="$bil_hta"></input>
   <input type="hidden" name="txtbilha" id="txtbilha" value="$bil_ha"></input>
   <input type="hidden" name="txtbilic" id="txtbilic" value="$bil_ic"></input>
    
    #foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    #set ($status = $list.id_Status)
    #set($id_Status = $list.id_Status)
    #set ($flag_print_nilaian_harta = $list.FLAG_PRINT_NILAIAN_HARTA)
    
    
    <input name="id_Permohonansimati" type="hidden"  value="$list.id_Permohonansimati"/>
    <input name="idpermohonansimati" type="hidden"  value="$list.id_Permohonansimati"/>
    #set($permohonan_mati = $list.id_Permohonansimati)
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="flag_print_nilaian_harta" type="hidden"  value="$flag_print_nilaian_harta"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
    <input name="idpermohonan" type="hidden"  value="$id"/>
    <input name="idPemohon" type="hidden"  value="$idPemohon"/>
    <input name="idSimati" type="hidden"  value="$idSimati"/>
    <input name="idtemp" type="hidden"  value="$id"/>
    <input name="idstatus" type="hidden"  value="$list.id_Status"/>
    #set($listnoFail = $list.noFail)
    #set($listidnegeri = $list.idnegeri)
    <input name="listidnegeri" type="hidden"  value="$listidnegeri"/>
    #set($listnamadaerah = $list.namadaerah)
    #set($listnamaPejabat = $list.namaPejabat)
    #set($listketerangan = $list.keterangan)
    #set($listseksyen = $list.seksyen)
    #set($listtarikhMohon = $list.tarikhMohon)
    #set($listnamaSimati = $list.namaSimati )
    #set($listnamaPemohon = $list.namaPemohon)
    #set($listtarikhMohon = $list.tarikhMohon)
    #set($listidSimati = $list.idSimati) 
    #set($listidFail = $list.idFail)  
    
    #end
    <tr>
      <input type="hidden" name="id_Fail" id="id_Fail" value="$listidFail" />
      <td> #parse("app/ppk/maklumat_sek8.jsp")
        
        
        #set($md=$listtarikhMohon)
        <input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
        <input type="hidden" name="txdTarikhMohon" id="txdTarikhMohon" value="$listtarikhMohon" />
        <input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
        <input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/>
      </td>
    </tr>
    <tr>
      <td><div id="TabbedPanels1" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()" >PERMOHONAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(1,0,0,0);HtaamView()">HARTA TAK ALIH</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(2,0,0,0);HAview()" >HARTA ALIH</li>
            #if($!skrin_online != "yes")
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" id="maklumat_pemohon"  >NILAIAN HARTA</li>
            #else
            #if($!hideTabPengesahan == "show")
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" id="maklumat_pemohon" >PENGESAHAN PERMOHONAN</li>
            #end
            #end
          </ul>
          <div >
            <div >
              <div id="TabbedPanels2"> </div>
            </div>
            <div>
              <div id="TabbedPanels4" > </div>
            </div>
            <div > </div>
            <div class="TabbedPanelsContent"> 
            
           
            
            
            #if($!skrin_online != "yes")
              <table width="100%" border="0">
                <tr>
                  <td><fieldset >
                    <table width="100%" border="0" >
                      <tr>
                        <td><fieldset>
                          <legend>NILAI HARTA TAK ALIH</legend>
                          #set($xjumpa_lain = "")
                          #set($xjumpa_kelompok = "")
                          #set($xjumpa_beli = "")
                          #set($xjumpa_alih = "")
                          
                          #if($jumppkhta.size() > 0)
                          
                          #set ($cntX = 0)
                          <fieldset>
                          <legend>ADA HAKMILIK</legend>
                          <table width="100%">
                         
                            #foreach($listhath in $jumppkhta)
                            #if($listhath.jenis_hta == "Y")
                            #set($xjumpa_alih = "yes")
                            #end
                            #end
                            
                            
                            #if($xjumpa_alih != "" )
                            <tr class="table_header">
                              <td width="2%">Bil</td>
                              <td width="18%">Negeri</td>
                              <td width="18%">Daerah</td>
                              <td width="18%">Mukim</td>
                              <td width="10%"><div align="center">No Hakmilik </div></td>
                              <td width="10%"><div align="center">No PT / No Lot</div></td>
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mohon (RM)</div></td>
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mati (RM)</div></td>
                            </tr>
                            #end
                            
                            #set ($sumhta = 0)
                            #set ($sumhtamati = 0)
                            #set ($cnt = 0)
                            #set ($i = 0)
                            
                            
                            #foreach($listhath in $jumppkhta)
                            
                            #if($listhath.jenis_hta == "Y")
                            <!--
no_roh :: $listhath.noroh
alamat1 :: $listhath.alamat1
alamat2 :: $listhath.alamat2
alamat3 :: $listhath.alamat3
poskod :: $listhath.poskod
jenis_penting :: $listhath.jenis_penting
ketegory :: $listhath.ketegori_hta
jenis hta :: $listhath.jenis_hta
kod :: $listhath.kod_hakmilik


#set($xjumpa_lain = "")
#set($xjumpa_kelompok = "")
#set($xjumpa_beli = "")
#set($xjumpa_alih = "")

-->
                            #set ($cnt = $cnt + 1)
                            #set ($cntX = $cntX + 1)
                            <tr bgcolor="white" >
                              <td>$cnt</td>
                              <td>$listhath.nama_Negeri</td>
                              <td>$listhath.nama_Daerah</td>
                              <td>$listhath.nama_Mukim</td>
                              #if($listhath.no_Perjanjian != "" && $listam.kod_hakmilik != "")    
                              #set($Z =  "$listhath.kod_hakmilik${listhath.no_Perjanjian}")    
                              #else
                              #set($Z =  $listhath.no_Perjanjian)
                              #end
                              <td style=" text-transform:uppercase;">$Z</td>
                              <td style=" text-transform:uppercase;">$listhath.no_Pt</td>
                              #if ($EventStatus == 4)
                              <input type="hidden" name="idHta" value="$listhath.idhta">
                              <input type="hidden" name="txtJumlahHtaTarikhMohon" value="$sumhta">
                              <input type="hidden" name="txtJumlahHtaTarikhMati" value="$sumhtamati">
                              
                            
                              <td align="right"><input name="txtHtaNilaiTarikhMohon" type="text" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15"></td>
                              <td align="right"><input   name="txtHtaNilaiTarikhMati" type="text" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" ></td>
                              #else
                              <td align="right"> #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
                                $Util.formatDecimal($listhath.nilai_tarikhmohon)
                                #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
                                0.00
                                #else
                                $listhath.nilai_tarikhmohon
                                #end </td>
                              <td align="right"> #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
                                $Util.formatDecimal($listhath.nilai_tarikhmati)
                                #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
                                0.00
                                #else
                                $listhath.nilai_tarikhmati
                                #end </td>
                              #end</tr>
                            #set($xjumpa_alih = "yes")
                            #end
                            #end
                            
                            
                            #if($xjumpa_alih == "" )
                            <tr bgcolor="white">
                              <td  colspan="9" align="left"><span class="style4">TIADA REKOD HARTA TAK ALIH (ADA HAKMILIK)</span></td>
                            </tr>
                            #end
                          </table>
                          </fieldset>
                          <fieldset>
                          <legend>TIADA HAKMILIK (PERJANJIAN JUAL BELI)</legend>
                          <table width="100%">
                            #foreach($listhath in $jumppkhta)
                            #if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 1)
                            #set($xjumpa_beli = "yes")
                            #end
                            #end
                            
                            
                            #if($xjumpa_beli != "" )
                            <tr class="table_header">
                              <td width="2%">Bil</td>
                              <td width="18%">Negeri</td>
                              <td width="18%">Daerah</td>
                              <td width="18%">Mukim</td>
                              <td width="20%"><div align="left">Alamat Harta </div></td>
                              <!-- <td width="10%"><div align="center">No PT / No Lot</div></td> -->
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mohon (RM)</div></td>
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mati (RM)</div></td>
                            </tr>
                            #end
                            
                            
                            #set ($sumhta = 0)
                            #set ($sumhtamati = 0)
                            #set ($cnt = 0)
                            #set ($i = 0)
                            
                            
                            #foreach($listhath in $jumppkhta)
                            
                            #if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 1)
                            <!--
no_roh :: $listhath.noroh
alamat1 :: $listhath.alamat1
alamat2 :: $listhath.alamat2
alamat3 :: $listhath.alamat3
poskod :: $listhath.poskod
jenis_penting :: $listhath.jenis_penting
ketegory :: $listhath.ketegori_hta
jenis hta :: $listhath.jenis_hta
kod :: $listhath.kod_hakmilik


#set($xjumpa_lain = "")
#set($xjumpa_kelompok = "")
#set($xjumpa_beli = "")
#set($xjumpa_alih = "")

-->
                            #set ($cnt = $cnt + 1)
                            #set ($cntX = $cntX + 1)
                            <tr bgcolor="white" >
                              <td>$cnt</td>
                              <td>$listhath.nama_Negeri</td>
                              <td>$listhath.nama_Daerah</td>
                              <td>$listhath.nama_Mukim</td>
                              #set($al_harta = "") 
                              #set($al_harta = "$listhath.alamat1  $listhath.alamat2  $listhath.alamat3  $listhath.poskod")
                              <td style=" text-transform:uppercase;">$al_harta</td>
                              <!-- <td style=" text-transform:uppercase;">$listhath.no_Pt</td> -->
                              #if ($EventStatus == 4)
                              <input type="hidden" name="idHta" value="$listhath.idhta">
                              <input type="hidden" name="txtJumlahHtaTarikhMohon" value="$sumhta">
                              <input type="hidden" name="txtJumlahHtaTarikhMati" value="$sumhtamati">
                              <td align="right"><input name="txtHtaNilaiTarikhMohon" type="text" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15"></td>
                              <td align="right"><input   name="txtHtaNilaiTarikhMati" type="text" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" ></td>
                              #else
                              <td align="right"> #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
                                $Util.formatDecimal($listhath.nilai_tarikhmohon)
                                #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
                                0.00
                                #else
                                $listhath.nilai_tarikhmohon
                                #end </td>
                              <td align="right"> #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
                                $Util.formatDecimal($listhath.nilai_tarikhmati)
                                #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
                                0.00
                                #else
                                $listhath.nilai_tarikhmati
                                #end </td>
                              #end</tr>
                            #set($xjumpa_beli = "yes")
                            #end
                            #end
                            
                            
                            #if($xjumpa_beli== "" )
                            <tr bgcolor="white">
                              <td  colspan="9" align="left"><span class="style4">TIADA REKOD HARTA TAK ALIH (TIADA HAKMILIK) PERJANJIAN JUAL BELI</span></td>
                            </tr>
                            #end
                          </table>
                          </fieldset>
                          <fieldset>
                          <legend>TIADA HAKMILIK (PEGANGAN DIBAWAH AKTA TANAH)</legend>
                          <table width="100%">
                            #foreach($listhath in $jumppkhta)
                            #if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 2)
                            #set($xjumpa_kelompok = "yes")
                            #end
                            #end
                            
                            
                            #if($xjumpa_kelompok != "" )
                            <tr class="table_header">
                              <td width="2%">Bil</td>
                              <td width="18%">Negeri</td>
                              <td width="18%">Daerah</td>
                              <td width="18%">Mukim</td>
                              <td width="20%"><div align="left">No Roh</div></td>
                              <!-- <td width="10%"><div align="center">No PT / No Lot</div></td> -->
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mohon (RM)</div></td>
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mati (RM)</div></td>
                            </tr>
                            #end
                            
                            #set ($sumhta = 0)
                            #set ($sumhtamati = 0)
                            #set ($cnt = 0)
                            #set ($i = 0)
                            
                            
                            #foreach($listhath in $jumppkhta)
                            
                            #if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 2)
                            <!--
no_roh :: $listhath.noroh
alamat1 :: $listhath.alamat1
alamat2 :: $listhath.alamat2
alamat3 :: $listhath.alamat3
poskod :: $listhath.poskod
jenis_penting :: $listhath.jenis_penting
ketegory :: $listhath.ketegori_hta
jenis hta :: $listhath.jenis_hta
kod :: $listhath.kod_hakmilik


#set($xjumpa_lain = "")
#set($xjumpa_kelompok = "")
#set($xjumpa_beli = "")
#set($xjumpa_alih = "")

-->
                            #set ($cnt = $cnt + 1)
                            #set ($cntX = $cntX + 1)
                            <tr bgcolor="white" >
                              <td>$cnt</td>
                              <td>$listhath.nama_Negeri</td>
                              <td>$listhath.nama_Daerah</td>
                              <td>$listhath.nama_Mukim</td>
                              <td style=" text-transform:uppercase;">$listhath.noroh</td>
                              <!-- <td style=" text-transform:uppercase;">$listhath.no_Pt</td> -->
                              #if ($EventStatus == 4)
                              <input type="hidden" name="idHta" value="$listhath.idhta">
                              <input type="hidden" name="txtJumlahHtaTarikhMohon" value="$sumhta">
                              <input type="hidden" name="txtJumlahHtaTarikhMati" value="$sumhtamati">
                              <td align="right"><input name="txtHtaNilaiTarikhMohon" type="text" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15"></td>
                              <td align="right"><input   name="txtHtaNilaiTarikhMati" type="text" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" ></td>
                              #else
                              <td align="right"> #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
                                $Util.formatDecimal($listhath.nilai_tarikhmohon)
                                #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
                                0.00
                                #else
                                $listhath.nilai_tarikhmohon
                                #end </td>
                              <td align="right"> #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
                                $Util.formatDecimal($listhath.nilai_tarikhmati)
                                #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
                                0.00
                                #else
                                $listhath.nilai_tarikhmati
                                #end </td>
                              #end</tr>
                            #set($xjumpa_kelompok = "yes")
                            #end
                            #end
                            
                            
                            #if($xjumpa_kelompok== "" )
                            <tr bgcolor="white">
                              <td  colspan="9" align="left"><span class="style4">TIADA REKOD HARTA TAK ALIH (TIADA HAKMILIK) PEGANGAN DIBAWAH AKTA TANAH</span></td>
                            </tr>
                            #end
                          </table>
                          </fieldset>
                          <fieldset>
                          <legend>TIADA HAKMILIK (KEPENTINGAN LAIN-LAIN)</legend>
                          <table width="100%">
                            #foreach($listhath in $jumppkhta)
                            #if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 3)
                            #set($xjumpa_lain = "yes")
                            #end
                            #end
                            
                            
                            #if($xjumpa_lain != "" )
                            <tr class="table_header">
                              <td width="2%">Bil</td>
                              <td width="18%">Negeri</td>
                              <td width="18%">Daerah</td>
                              <td width="18%">Mukim</td>
                              <td width="20%"><div align="left">Jenis Kepentingan</div></td>
                              <!-- <td width="10%"><div align="center">No PT / No Lot</div></td> -->
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mohon (RM)</div></td>
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mati (RM)</div></td>
                            </tr>
                            #end
                            
                            
                            #set ($sumhta = 0)
                            #set ($sumhtamati = 0)
                            #set ($cnt = 0)
                            #set ($i = 0)
                            
                            
                            #foreach($listhath in $jumppkhta)
                            
                            #if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 3)
                            <!--
no_roh :: $listhath.noroh
alamat1 :: $listhath.alamat1
alamat2 :: $listhath.alamat2
alamat3 :: $listhath.alamat3
poskod :: $listhath.poskod
jenis_penting :: $listhath.jenis_penting
ketegory :: $listhath.ketegori_hta
jenis hta :: $listhath.jenis_hta
kod :: $listhath.kod_hakmilik


#set($xjumpa_lain = "")
#set($xjumpa_kelompok = "")
#set($xjumpa_beli = "")
#set($xjumpa_alih = "")

-->
                            #set ($cnt = $cnt + 1)
                            #set ($cntX = $cntX + 1)
                            <tr bgcolor="white" >
                              <td>$cnt</td>
                              <td>$listhath.nama_Negeri</td>
                              <td>$listhath.nama_Daerah</td>
                              <td>$listhath.nama_Mukim</td>
                              <td style=" text-transform:uppercase;">$listhath.jenis_penting</td>
                              <!-- <td style=" text-transform:uppercase;">$listhath.no_Pt</td> -->
                              #if ($EventStatus == 4)
                              <input type="hidden" name="idHta" value="$listhath.idhta">
                              <input type="hidden" name="txtJumlahHtaTarikhMohon" value="$sumhta">
                              <input type="hidden" name="txtJumlahHtaTarikhMati" value="$sumhtamati">
                              <td align="right"><input name="txtHtaNilaiTarikhMohon" type="text" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15"></td>
                              <td align="right"><input   name="txtHtaNilaiTarikhMati" type="text" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" ></td>
                              #else
                              <td align="right"> #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
                                $Util.formatDecimal($listhath.nilai_tarikhmohon)
                                #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
                                0.00
                                #else
                                $listhath.nilai_tarikhmohon
                                #end </td>
                              <td align="right"> #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
                                $Util.formatDecimal($listhath.nilai_tarikhmati)
                                #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
                                0.00
                                #else
                                $listhath.nilai_tarikhmati
                                #end </td>
                              #end</tr>
                            #set($xjumpa_lain = "yes")
                            #end
                            #end
                            
                            
                            #if($xjumpa_lain== "" )
                            <tr bgcolor="white">
                              <td  colspan="9" align="left"><span class="style4">TIADA REKOD HARTA TAK ALIH (TIADA HAKMILIK) KEPENTINGAN LAIN-LAIN</span></td>
                            </tr>
                            #end
                          </table>
                          </fieldset>
                          #else
                          <table width="100%">
                            #if($jumppkhta.size() > 0)
                            <tr class="table_header">
                              <td width="2%">Bil</td>
                              <td width="18%">Negeri</td>
                              <td width="18%">Daerah</td>
                              <td width="18%">Mukim</td>
                              <td width="10%"><div align="center">No Hakmilik </div></td>
                              <td width="10%"><div align="center">No PT / No Lot</div></td>
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mohon (RM)</div></td>
                              <td width="12%"><div align="center">Nilai HTA Tarikh Mati (RM)</div></td>
                            </tr>
                            <tr > #end
                              <td  colspan="8" align="left"><span class="style4">TIADA REKOD HARTA TAK ALIH</span></td>
                            </tr>
                          </table>
                          #end
                          
                          #set($sumhta = 0)
                          #set($sumhtamati = 0)
                          #foreach($listhath in $jumppkhta)
                          
                          #if($listhath.nilai_tarikhmohon!="")
                          #set ($sumhta = $sumhta + $listhath.nilai_tarikhmohon)
                          #end
                          
                          #if($listhath.nilai_tarikhmati != "")
                          #set ($sumhtamati = $sumhtamati + $listhath.nilai_tarikhmati)
                          #end
                          
                          #end
                          <table width="100%">
                            <tr class="table_header">
                              <td  width="76%" ><b>Jumlah Nilai Harta Tak Alih (RM)</b></td>
                              <td  align="right" width="12%"> #if($sumhta == 0) <b> 0.00 </b> #else <b> $Util.formatDecimal($sumhta) </b> #end </td>
                              <td  align="right" width="12%"> #if($sumhtamati == 0) <b> 0.00 </b> #else <b> $Util.formatDecimal($sumhtamati) </b> #end </td>
                            </tr>
                          </table>
                          </fieldset>
                          <br />
                          <fieldset>
                          <legend>NILAI HARTA ALIH </legend>
                          <table width="100%" >
                            #if($listHa.size() > 0)
                            <tr class="table_header">
                              <td width="2%">Bil</td>
                              <td width="46%">Jenis Harta Alih</td>
                              <td width="14%"><div align="center">No Rujukan UPT / No Daftar / <br>
                                  No Akaun / No Ahli</div></td>
                              <td width="14%"><div align="center">No Sijil</div></td>
                              <td width="12%"><div align="center">Nilai HA Tarikh Mohon (RM)</div></td>
                              <td width="12%"><div align="center">Nilai HA Tarikh Mati (RM)</div></td>
                            </tr>
                            #end
                            #set ($id_ha = "")
                            #set ($nama_Negeri = "")
                            #set ($nama_Daerah = "")
                            #set ($jenis = "")
                            #set ($no_Perjanjian = "")
                            #set ($sijil = "")
                            #set ($nilai_tarikhmohon = "")
                            #set ($nilai_tarikhmati = "")
                            #set ($sumppkha = "")
                            
                            
                            
                            #set ($cnt = 0)
                            
                            #if($listHa.size() > 0)
                            
                            #foreach($listha2 in $listHa)
                            
                            
                            #set($cat="")
                            #if($listha2.id_Jenisha == 1)
                            #if($listha2.nama_saham != "")
                            #set($cat = "- $listha2.nama_saham ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            #if($listha2.id_Jenisha == 2)
                            #if($listha2.jenama != "")
                            #set($cat = "- $listha2.jenama ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            
                            #if($listha2.id_Jenisha == 3)
                            #if($listha2.jenama != "")
                            #set($cat = "- $listha2.jenama ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            #if($listha2.id_Jenisha == 4)
                            #if($listha2.jenama != "")
                            #set($cat = "- $listha2.jenama ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            #if($listha2.id_Jenisha == 5)
                            #if($listha2.jenama != "")
                            #set($cat = "- $listha2.jenama ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            #if($listha2.id_Jenisha == 6)
                            #if($listha2.jenama != "")
                            #set($cat = "- $listha2.jenama ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            
                            #if($listha2.id_Jenisha == 7)
                            #if($listha2.butiran != "")
                            #set($cat = "- $listha2.butiran ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            #if($listha2.id_Jenisha == 8)
                            #if($listha2.butiran != "")
                            #set($cat = "- $listha2.butiran ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            
                            #if($listha2.id_Jenisha == 9)
                            #if($listha2.butiran != "")
                            #set($cat = "- $listha2.butiran ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            #if($listha2.id_Jenisha == 10)
                            #if($listha2.butiran != "")
                            #set($cat = "- $listha2.butiran ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            #if($listha2.id_Jenisha == 11)
                            #if($listha2.butiran != "")
                            #set($cat = "- $listha2.butiran ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            
                            #if($listha2.id_Jenisha == 12)
                            #if($listha2.butiran != "")
                            #set($cat = "- $listha2.butiran ")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            #if($listha2.id_Jenisha == 98)
                            #if($listha2.nilai_tm != "")
                            #set($nt = $Util.formatDecimal($listha2.nilai_tm) )
                            #set($cat = "- RM $nt")
                            #else
                            #set($cat = "")
                            #end
                            #end
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            #set ($id_ha = $listha2.id_Ha)
                            #set ($jenis = $listha2.Keterangan)
                            #set ($no_Perjanjian = $listha2.noDaftar)
                            #set ($sijil = $listha2.nosijil)
                            
                            #if($listha2.nilai_tarikhmohon!="")
                            #set ($nilai_tarikhmohon = $listha2.nilai_tarikhmohon)
                            #else
                            #set($nilai_tarikhmohon = "")
                            #end
                            #if($listha2.nilai_tarikhmati != "")
                            #set ($nilai_tarikhmati = $listha2.nilai_tarikhmati)
                            #else
                            #set ($nilai_tarikhmati = "")
                            #end
                            #set ($cnt = $cnt + 1)
                            <tr bgcolor="white" style="text-transform:uppercase;" >
                              <td>$cnt</td>
                              <td>$jenis $cat</td>
                              <td>$no_Perjanjian</td>
                              <td>$sijil</td>
                              #if ($EventStatus == 4)
                              <input type="hidden" name="idHa" value="$listha2.id_Ha">
                              <td align="right"><input name="txtHaNilaiTarikhMohon" type="text" id="txtHaNilaiTarikhMohon" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMohon');validateModal(this,this.value,'$nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMohon')" value="$nilai_tarikhmohon" size="15" maxlength="15" ></td>
                              <td align="right"><input name="txtHaNilaiTarikhMati" type="text" id="txtHaNilaiTarikhMati" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMati');validateModal(this,this.value,'$nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMati')" value="$nilai_tarikhmati" size="15" maxlength="15" ></td>
                              #else
                              <td align="right"> #if($nilai_tarikhmohon>0)
                                $Util.formatDecimal($nilai_tarikhmohon)
                                #elseif($nilai_tarikhmohon == 0)
                                0.00
                                #else
                                $nilai_tarikhmohon
                                #end</td>
                              <td align="right"> #if($nilai_tarikhmati>0)
                                $Util.formatDecimal($nilai_tarikhmati)
                                #elseif($nilai_tarikhmati == 0)
                                0.00
                                #else
                                $nilai_tarikhmati
                                #end</td>
                              #end</tr>
                            #end
                            
                            #else
                            <tr>
                              <td colspan="6"><span class="style4">TIADA REKOD HARTA ALIH</span></td>
                            </tr>
                            #end
                            
                            #set ($sumjumlah = 0)
                            #set ($sumjumlahmati = 0)
                            #foreach($listha2 in $listHa)
                            #if($listha2.nilai_tarikhmohon!="")
                            #set ($sumjumlah = $sumjumlah + $listha2.nilai_tarikhmohon)
                            #end
                            #if($listha2.nilai_tarikhmati != "")
                            #set ($sumjumlahmati = $sumjumlahmati + $listha2.nilai_tarikhmati)
                            #end
                            #end
                            <input type="hidden" name="txtJumlahHaTarikhMati" value="$sumjumlahmati">
                            <input type="hidden" name="txtJumlahHaTarikhMohon" value="$sumhta">
                            #set ($overalljumlahmati = 0)
                            #foreach($listOverallmati in $overallmati)
                            #if($listOverallmati.nilaibesarmati!="")
                            #set ($overalljumlahmati = $overalljumlahmati + $listOverallmati.nilaibesarmati)
                            #end
                            #end
                            <input type="hidden" name="txtJumlahBesarTarikhMati" value="$overalljumlahmati">
                            <input type="hidden" name="txtJumlahBesarTarikhMohon" value="$overalljumlah">
                            <tr class="table_header">
                              <td  width="76%" colspan="4" ><b>Jumlah Nilai Harta Alih (RM) </b></td>
                              <td  align="right" width="12%"> #if($sumjumlah == 0 ) <b> 0.00 </b> #else <b> $Util.formatDecimal($sumjumlah) </b> #end </td>
                              <td  align="right" width="12%"> #if($sumjumlahmati == 0  ) <b> 0.00 </b> #else <b> $Util.formatDecimal($sumjumlahmati) </b> #end </td>
                            </tr>
                          </table>
                          </fieldset>
                          <br>
                          <fieldset>
                          <legend>NILAI HARTA KESELURUHAN</legend>
                          <table width="100%">
                            <tr class="table_header">
                              <td width="2%">Bil</td>
                              <td width="74%">Perkara</td>
                              <td width="12%"><div align="center">Jumlah Nilai Harta Tarikh Mohon (RM)</div></td>
                              <td width="12%"><div align="center">Jumlah Nilai Harta Tarikh Mati (RM)</div></td>
                            </tr>
                            <tr class="row2">
                              <td>1 </td>
                              <td><strong>Jumlah Nilai Harta Tak Alih</strong></td>
                              #if ($sumhta == 0)
                              <td align="right">0.00</td>
                              #else
                              <td align="right">$Util.formatDecimal($sumhta)</td>
                              #end
                              
                              
                              #if ($sumhta == 0)
                              <td align="right">0.00</td>
                              #else
                              <td align="right">$Util.formatDecimal($sumhtamati)</td>
                              #end </tr>
                            <tr class="row2">
                              <td>2 </td>
                              <td><strong>Jumlah Nilai Harta Alih </strong></td>
                              #if($sumjumlah == 0)
                              <td align="right">0.00</td>
                              #else
                              <td align="right">$Util.formatDecimal($sumjumlah)</td>
                              #end
                              
                              
                              #if($sumjumlahmati == 0  )
                              <td align="right">0.00</td>
                              #else
                              <td align="right">$Util.formatDecimal($sumjumlahmati)</td>
                              #end </tr>
                            <tr class="table_header">
                              <td colspan="2"><b>Jumlah Nilai Harta Keseluruhan</b></td>
                              #set ($overalljumlah = $sumhta + $sumjumlah)
                              #if ($overalljumlah == 0)
                              <td colspan="1" align="right"><b>0.00</b></td>
                              #else
                              <td colspan="1" align="right"><b>$Util.formatDecimal($overalljumlah)</b></td>
                              #end
                              
                              
                              #set ($overalljumlahmati = $sumhtamati + $sumjumlahmati)
                              #if ($overalljumlahmati == 0)
                              <td colspan="1" align="right"><b>0.00</b></td>
                              #else
                              <td colspan="1" align="right"><b>$Util.formatDecimal($overalljumlahmati)</b></td>
                              #end </tr>
                            <tr>
                            <tr>
                              <td colspan="8">&nbsp;</td>
                            </tr>
                          </table>
                          </fieldset>
                          
                          
                          <p align="center"> 
                          <input type="button" name="cmdCetak" value="Cetak" id="cdmCetak" onclick="javascript:setTable('tableReport')"/>

                          #if($boleh_kemaskini == "yes")
                          #end  
                                        #if ($EventStatus == 4)
                                        <input type="button" name="cmdSimpan" value="Simpan" onClick="setSelected(3,0,0,0);getNilaiHartaSimpan()">
                                        <input type="button" name="cmdBatal" value="Batal" onClick="setSelected(3,0,0,0);getNilaiHartaKemaskiniBatal()">
                                        #else
                                        
                                        
                                            #if (($jumppkhta.size()>0 || $listHa.size()>0))
                                            <input type="button" name="cmdKemaskini1" id="cmdKemaskini1" value="Kemaskini" onClick="setSelected(3,0,0,0);getNilaiHartaKemaskini()">
                                             #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('cmdKemaskini1').style.display = "none";
                                </script>
                                #end   
                                            #end
                                      
                                        
                                        
                                        
                                        #if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
                                            #if (($status == 8 || $status == 9 || $status == 170) )
                                            <!-- <input type="button" name="button" id="button" value="Hantar" onClick="setSelected(3,0,0,0);hantar('$listseksyen','$id','$permohonan_mati','$listtarikhMohon','$listidSimati');" /> -->
                                            #else
                                            #if ($jumppkhta.size()>0 && $sumhta >= 0)
                                            #end
                                        #end    
                                      <!-- Kena check sama ada Maklumat Simati dan Pemohon lengkap diisi -->
                                      	#if ($strErrorMaklumatSimati == "" && $strErrorMaklumatPemohon == "")
                 							<input type="button" name="button" id="button" value="Seterusnya" onClick="hantar_terus('$listseksyen','$id','$permohonan_mati','$listtarikhMohon','$listidSimati')" />
                 						#else
                 						<p align="center"><font color="red">Tidak dapat meneruskan ke proses seterusnya kerana terdapat maklumat berkenaan Simati/Pemohon yang tidak lengkap diisi: $strErrorMaklumatSimati $strErrorMaklumatPemohon</font> </p>
                 						
                 						#end	
                 							
                 							 #if(($pilihpegawai != "") && ($flag_pengesahanPegawai == "") && ($USER_ROLE != "user_ppk") && (($daftarHTA == "1") || ($daftarHA == "1") ))
                                            
                 							<input type="button" name="button" id="button" value="Sahkan" onClick="simpanPengesahan2()" />
                 							#end
                 							
                                           
                                            
                 							

                                        #end
                            #end
                          
                            <input type="hidden" name="idPermohonan"/>
                            <input type="hidden" name="idpermohonansimati"/>
                            <input type="hidden" name="tarikh_mohon" />
                            <input type="hidden" name="idSimati"/>
                            <!--    
	<input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onkeypress="kembali_simati()" onclick="kembali_simati()" />
    -->
                          </p></td>
                      </tr>
                    </table>
                    </fieldset></td>
                </tr>
                #if($USER_ROLE == "user_ppk")
                <tr>
                <td>
                <fieldset><legend><strong>Pilihan Pegawai bagi Pengesahan Permohonan</strong></legend>
               <table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_upload">
                      <tr >
                      
                      <td  valign="top"></td>
                      <td  valign="top"><font color="red">*</font>Pilih Pegawai</td>
                      <td   valign="top">:</td>
                      <td  valign="top">
                     
                      #if(($buttonSimpanDisable=="disabled") || ($pilihpegawai != ""))
                    	#set($disability1 = "disabled")
                      #end
                        <select  class="autoselect" name="pilihpegawai" id="pilihpegawai" $disability1>
                        <option value=""  >SILA PILIH PEGAWAI</option>
                       
                        #foreach($list1 in $listTechTeam_aduan) 
                        
                        <option value="$!list1.userID" #if($!list1.userID==$!pilihpegawai) selected="selected" #end >$list1.user_name $list1.catatan</option>
                        
                        #end 
                        </select>
                       
                      </td>
                    </tr>
                    <!-- 
                    <tr>
                    	 	<td valign="top"></td>
                      		<td valign="top"><font color="red">*</font>Tujuan Pindaan</td>
                      		<td valign="top">:</td>
                      		<td valign="top">
                      		
                      		 <textarea name="txtTujPinda" id="txtTujPinda" cols="50"   rows="4"  placeholder="Sila Masukkan Tujuan Pindaan..."         
         onBlur="check_length(this,'4000','tujPinda_edit_check','tujPinda_num','normal','yes','keterangan');"  
         onKeyup="check_length(this,'4000','tujPinda_edit_check','tujPinda_num','normal','yes','keterangan');" 
         onKeydown="check_length(this,'4000','tujPinda_edit_check','tujPinda_num','normal','yes','keterangan');"                    
           >$!txtTujPinda</textarea>
            <div><span id="tujPinda_num" style="color:blue;" ></span><span> Baki Aksara </span></div>
        
         <div id="tujPinda_edit_check" class="alert_msg" ></div> 
                      		
                      		</td>
                    
                    </tr>
                    
                    <tr>
                    <td valign="top"></td>
                      		<td valign="top"><font color="red">*</font>Tempoh</td>
                      		<td valign="top">:</td>
                      		<td valign="top"> <input name="txtMula" type="text" value="$!txtMula">&nbsp; <a href="javascript:displayDatePicker('txtMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>&nbsp; sehingga <input name="txtAkhir" type="text" value="$!txtAkhir"><a href="javascript:displayDatePicker('txtAkhir',false,'dmy');">&nbsp;<img border="0" src="../img/calendar.gif"/></a></td>
                    </tr>
                     -->
                    <tr>
                    	<td valign="top" colspan=3> &nbsp;</td>
                    	#if(($buttonSimpanDisable=="disabled") || ($pilihpegawai != ""))
                    	<td ><input disabled name="cmdSimpanPeg" id="cmdSimpanPeg" value="Simpan" type="button" onClick="javascript:cmdSimpan_Pegawai()"></td>
                    	#else
                    	<td ><input name="cmdSimpanPeg" id="cmdSimpanPeg" value="Simpan" type="button" onClick="javascript:simpanPengesahan()"></td>
                    	#end
               </table>
               
            
            
               </fieldset>
                </td>
                </tr>
                #end
<tr>
<td>
                 <fieldset  id="tableReport" style="display:none;" >
                          <legend><strong>Senarai Laporan</strong></legend>
                          <table width="100%" border="0" cellspacing="2" cellpadding="2">                           
                            <tr>
                              <td ><a href="#" style="color: #0000FF;" onclick="javascript:cetakNilaiHarta('$NO_FAIL')">Nilaian Harta oleh PPBPP</a></td>
                            </tr>
                          </table>
                          </fieldset>
</td>
</tr>
                <tr>

                  <td> 
                  
                  #if($!skrin_online != "yes")
                    <p align="right">CL - 01 - 83</p>
                    #end 
                    
                    </td>
                </tr>
              </table>
                                         
              #else
             

             
                     <table width="100%" border="0">
   <tr>
    <td>
        <fieldset >
        
    #if ($selDaerah != "0" || $daerahmhn != "")
	#foreach ($data in $selectedPpkAddress)
		#set ($namapejabat = $data.namapejabat)	
	#end
	#end
        
        
         #parse("app/ppk/info_berjaya_disimpan.jsp")

<table width="100%" border="0" >
  <tr>
    <td>
 #if ($idStatus != "150")
 <fieldset>
 <legend>MAKLUMAT PERMOHONAN</legend>
 
#foreach($View in $View_pengesahan_pemohonan)
        #set ($noFail = $View.noFail)
        #set ($no_fail_online = $View.no_fail_online)
        #set ($tarikhMohonOnline = $View.tarikhMohonOnline)
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
    #end


 <table width="100%" border="0" id="maklumat_asas">
      
          
           <tr>
              <td width="5%">&nbsp;</td>
              <td width="20%">Nama Pemohon</td>
              <td width="1%">:</td>
              <td width="74%"><strong>$namaPemohon</strong></td>
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
              <td ><strong>$namaSimati              </strong></td>
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
            
            
             <tr  >
              <td >&nbsp;</td>
              <td >No Rujukan Online</td>
              <td >:</td>
              <td ><strong> <font color="blue">$no_fail_online</font></strong></td>
            </tr>
            
             <tr >
              <td  >&nbsp;</td>
              <td >Tarikh Permohonan Online</td>
              <td >:</td>
              <td ><strong>$tarikhMohonOnline</strong></td>
            </tr>
            
            <tr>
            <td></td>
            <td colspan="3">
         

            </td>
            </tr>
            </table>
 </fieldset>
    
 <br /> 
 
 #end  

<fieldset><legend><strong>SENARAI SEMAKAN</strong></legend>     
<table width="100%" border="0">
	<tr class="row2">
		<td width="3%"></td>
		<td width="92%">Keterangan</td>
		<td width="5%">#</td>
	</tr>  
          		#set ( $checked = "" )
            	#foreach ( $semak in $senaraiSemakan )
                	#set( $i = $velocityCount )
                	#if ( ($i % 2) == 0 )
                    	#set( $row = "row2" )
                	#else
                    	#set( $row = "row1" )
                	#end
	<tr class="$row">
	<td width="3%">
					#if ( $semakclass.isSemakan("$IdPermohonan", "$semak.id" ))
					##if ($semak.lampirans != "")
	                            #set ( $checked = "checked" )
	               	#else
	                           #set ( $checked = "" )
	             	#end
<!-- 	                <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $modeSoc> -->

					#if($!idStatus == "150")
				        <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked />
				    #else
				        <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked disabled/>
				    #end
				    
	</td>
	<td width="92%">$i. $semak.keterangan</td>
	<td width="5%">$semak.lampirans</td>
	</tr>  
	       		#end

</table>
</fieldset>
<br>
<fieldset>
<legend>PENGESAHAN PERMOHONAN</legend>
<!--
<font color="#FF0000" size="2">
<i>* Sila cetak Borang A dan Pengesahan Permohonan untuk dibawa ke Unit Pembahagian Pusaka Kecil.
<br>   * Permohonan akan dibatalkan sekiranya terdapat permohonan lain yang lengkap dihantar ke Unit Pembahagian Pusaka Kecil.    
    </i></font>
<br>
<br>
<br>-->

#if ($idStatus != "150")
#if($kemaskini_pejabat != "yes")
#set($disabledDropdown = "disabled")
#else
#set($disabledDropdown = "")
#end
#end
<table width="100%" border="0">
<tr>
<td width="5%"></td>
<td width="20%" >Negeri</td>
<td width="1%">:</td>
<td width="74%"><strong> #if($disabledDropdown == 'disabled')
#set($nama_negeri="") 
 #foreach($listneg in $senaraiNegeriByPpkUnit) 
                                        #if($negerimhn == $listneg.idnegeri)
                                        
                                        #set($nama_negeri="$listneg.namanegeri")
                                        
                                       
                                        #end
                                        #end <font  style="text-transform:uppercase;">$!nama_negeri</font>
                                        
                                <input type="hidden" size="50" maxlength="46" name="namanegeri" value="$!nama_negeri" readonly class="disabled" style="text-transform:uppercase;">
                  
                                <input type="hidden" id="socNegeriPengesahan"  name="socNegeriPengesahan" value="$!negerimhn" >                   

#else


<select name="socNegeriPengesahan" style="width: 300px;" onChange="getDaerah()" $disabledDropdown>
  
#set ($selIdNegeri = "")
	#if ($selNegeri != "0")
			#set ($selected = "")
			#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
				#if ($listNegeri.idnegeri == $selNegeri)
					#set ($selected = "selected")	
				
  <option value="$listNegeri.idnegeri" $selected>$listNegeri.namanegeri</option>
  
				#end
			#end
			
			
  <option value="0">Sila Pilih</option>
  
			#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
				#set ($selIdNegeri = $selNegeri)	
				
  <option value="$listNegeri.idnegeri">$listNegeri.namanegeri</option>
  
			#end
	#else
			#if ($negerimhn != "")
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
						#if ($listNegeri.idnegeri == $negerimhn)
							#set ($selected = "selected")	

                           
						
  <option value="$listNegeri.idnegeri" $selected>$listNegeri.namanegeri</option>
  
						#end
				#end	
				
  <option value="0">Sila Pilih</option>
  
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
					#set ($selIdNegeri = $selNegeri)	
					
  <option value="$listNegeri.idnegeri">$listNegeri.namanegeri</option>
  
				#end
			#else
		
				
  <option value="0">Sila Pilih</option>
  
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
					#set ($selIdNegeri = $selNegeri)	
					
  <option value="$listNegeri.idnegeri">$listNegeri.namanegeri</option>
  
				#end
			#end
	#end

</select>
#end

<input type="hidden" name="saizdata" value="$!saizData">
</strong></td>
</tr>
<tr>
<td></td>
<td >Daerah</td>
<td>:</td>
<td><strong> #if($disabledDropdown == 'disabled')
#set($nama_daerah="") 
 #foreach($listDaerah in $selectedDaerah) 
                                       
                                        
                                        #if($!daerahmhn == $listDaerah.id)
                                        
                                        #set($nama_daerah="$listDaerah.nama")
                                        
                                       
                                        #end
                                        #end <font  style="text-transform:uppercase;">$!nama_daerah</font>
                                <input type="hidden" size="50" maxlength="46" name="nama_daerah" value="$!nama_daerah" readonly class="disabled" style="text-transform:uppercase;">
                                <input type="hidden" id="socDaerahPengesahan"  name="socDaerahPengesahan" value="$!daerahmhn" >    
                                     

#else

<select name="socDaerahPengesahan" style="width: 300px;" onChange="getAddress()" $disabledDropdown>
  
	#if ($selDaerah != "0")
			#foreach ($listDaerah in $selectedDaerah)
				#if ($listDaerah.id == $selDaerah)
					#set ($selected3 = "selected")
				
  <option value="$listDaerah.id" $selected3>$listDaerah.nama</option>
  
				#end
			#end
			
  <option value="0">Sila Pilih</option>
  
			#foreach ($listDaerah in $selectedDaerah)
				
  <option value="$listDaerah.id">$listDaerah.nama</option>
  
			#end
	#else
			#if ($daerahmhn != "")
					#foreach ($listDaerah in $selectedDaerah)
						#if ($listDaerah.id == $daerahmhn)
                       
						
  <option value="$listDaerah.id" selected>$listDaerah.nama</option>
  
						#end
					#end
					
  <option value="0">Sila Pilih</option>
  
					#foreach ($listDaerah in $selectedDaerah)
						
  <option value="$listDaerah.id">$listDaerah.nama</option>
  
					#end
			#else
					
  <option value="0">Sila Pilih</option>
  
					#foreach ($listDaerah in $selectedDaerah)
						
  <option value="$listDaerah.id">$listDaerah.nama</option>
  
					#end
			#end
	#end

</select>

#end
</strong></td>
</tr>
#if ($selDaerah != "0" || $daerahmhn != "")
	#foreach ($data in $selectedPpkAddress)
		#set ($namapejabat = $data.namapejabat)
		#set ($alamat1 = $data.alamatOne)
		#set ($alamat2 = $data.alamatTwo)
		#set ($alamat3 = $data.alamatThree)
		#set ($poskod = $data.poskod)
		#set ($no_tel = $data.notel)
		#set ($no_fax = $data.nofax)
		#set ($no_tel_samb = $data.notel_sambungan)
	#end
#end
<tr>
<td></td>
<td >Pejabat</td>
<td>:</td>
<td><span style="font-weight: bold">
  <input type="hidden" size="50" maxlength="46" name="namapejabat" value="$!namapejabat" readonly class="disabled" style="text-transform:uppercase;">
  <font  style="text-transform:uppercase;">$!namapejabat</font>
</span></td>
</tr>
<tr>
<td></td>
<td >Alamat</td>
<td>:</td>
<td><span style="font-weight: bold">
  <input type="hidden" size="50" maxlength="46" name="alamat1" value="$!alamat1" readonly class="disabled" style="text-transform:uppercase;">
  <font  style="text-transform:uppercase;">$!alamat1</font>
</span></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td><span style="font-weight: bold">
  <input type="hidden" size="50" maxlength="46" name="alamat2" value="$!alamat2" readonly class="disabled" style="text-transform:uppercase;">
  <font  style="text-transform:uppercase;">$!alamat2</font>
</span></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td><span style="font-weight: bold">
  <input type="hidden" size="50" maxlength="46" name="alamat3" value="$!alamat3" readonly class="disabled" style="text-transform:uppercase;">
  <font  style="text-transform:uppercase;">$!alamat3</font>
</span></td>
</tr>
<tr>
<td></td>
<td >Poskod</td>
<td>:</td>
<td><strong>
  <input type="hidden" size="5" maxlength="5" name="poskod" value="$!poskod" readonly class="disabled" >
  <font  style="text-transform:uppercase;">$!poskod</font>
</strong></td>
</tr>
<tr>
<td></td>
<td >No. Telefon</td>
<td>:</td>
<td><strong>
  <input type="hidden" size="12" maxlength="11" value="$!no_tel" readonly class="disabled" >
  <font  style="text-transform:uppercase;">$!no_tel</font></strong></td>
</tr>
#if ($no_tel_samb != "")
<tr>
<td></td>
<td >No. Telefon (samb)</td>
<td>:</td>
<td><strong>
  <input type="hidden" size="12" maxlength="11" value="$!no_tel_samb" readonly class="disabled" >
  <font  style="text-transform:uppercase;">$!no_tel_samb</font>
</strong></td>
</tr>
#end
#if ($no_fax != "")
<tr>
<td></td>
<td >No. Fax</td>
<td>:</td>
<td><strong>
  <input type="hidden" size="12" maxlength="11" value="$!no_fax" readonly class="disabled" >
  <font  style="text-transform:uppercase;">$!no_fax</font></strong></td>
</tr>
#end

<!--  syafiqah add 140820 -->
<tr>
	<td></td>
	<td>Borang A</td>
	<td>:</td>
	<td>#if($!skrin_deraf == "yes" || $!skrin_kembali == "yes")
			#if ($idStatus == "150")
			<input type="button" name="boranga" id="boranga" size="40" value="Muatnaik Borang A" size="40" onClick="lampiran('$!idSimati','dokumenA')"/>
			#end
		#end
		<br>
		#if($lampirans != "")
			<input type="hidden" name="namaDoc1" value="1" />
		#else
			<input type="hidden" name="namaDoc1" value="0" />
		#end
		$!lampirans
	</td>
</tr>

#foreach($View in $View_pengesahan_pemohonan)
        #set ($namaPemohon = $View.namaPemohon)
        #set ($noKpBaruPemohon1 = $View.noKpBaruPemohon1)
        #set ($noKpBaruPemohon2 = $View.noKpBaruPemohon2)
        #set ($noKpBaruPemohon3 = $View.noKpBaruPemohon3)
        #set ($jenis_pemohon = $View.jenis_pemohon)
        #set ($noKpBaruPemohon = $View.noKpBaruPemohon)
    #end
    <!--  skrin_deraf= $skrin_deraf-->
    #if($!skrin_deraf == "yes" || $!skrin_kembali == "yes")
	<tr><td>
    		<td width="1%" valign="top"></td>
    		#if ($idStatus == "150")
    		<td width="3%" valign="top"><input type="checkbox" name='namecb1' id='namecb1'></td>
    		#else
    		<td width="3%" valign="top"><input type="checkbox" name='namecb1' id='namecb1' checked disabled></td>
    		#end
    		
    	#if ($jenis_pemohon == "2")
      	<td width="89%">Saya $!namaPemohon MyID $!noKpBaruPemohon1 $!noKpBaruPemohon2 $!noKpBaruPemohon3 dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td>
		#elseif($jenis_pemohon == "1")
		<td width="89%">Kami $!namaPemohon dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td>
		
		
	</td>#end</tr>
	#end
  	
<tr>
<td></td>
<td ></td>
<td></td>
<td>
	<label align="left" valign="top"> 
<!-- 	<b><font color="BLUE" size="2"><span class="blink">Sekiranya permohonan telah dihantar, pemohon sudah tidak boleh mengemaskini permohonan. -->
<!-- 	<br> Jika terdapat sebarang maklumat yang perlu ditambah, sila mengemaskini permohonan terlebih dahulu sebelum menghantar permohonan.</span></font></b> -->
<!-- 	</label></td> -->
	<b><font color="BLUE" size="2"><span class="blink">Ambil Perhatian: Sila pastikan maklumat yang diisi pada permohonan adalah TEPAT dan MUKTAMAD. 
	Permohonan yang telah <br>dihantar TIDAK DIBENARKAN untuk dipinda/dikemaskini.</span></font></b>
	</label></td>
</tr>
</table>
</fieldset>

<input name="idStatus" type="hidden" value="$idStatus">
<input type="hidden" name="nopermohonanonline" id="nopermohonanonline" value="$!no_fail_online">

<p align="center">

#if ($idStatus == "150")
	#if($!namapejabat != "")
<!-- 	<input type="button" name="cmdBorangADraf" value="Cetak Draf Borang A" onClick="javascript:cetakBorangADraf()"> -->
	<input type="button" name="cmdBorangA" value="Cetak Borang A" onClick="javascript:cetakBorangA('$id','$!no_fail_online')">
	<input type="button" name="cmdHantar" value="Hantar ke $!namapejabat" onClick="javascript:getUnitPPK('$id','$nopermohonanonline')">
<!--<input type="button" name="cmdKosongkan" value="Kosongkan" onClick="PengesahanView('3','0','0','0')">-->
	
	#else
<!-- 	<input type="button" name="cmdBorangADraf" value="Cetak Draf Borang A" onClick="javascript:cetakBorangADraf()"> -->
	<input type="button" name="cmdBorangA" value="Cetak Borang A" onClick="javascript:cetakBorangA('$id','$!no_fail_online')">
	#end

#else
	#if ($skrin_online_popup == "yes")
		#if ($kemaskini_pejabat != "yes")
		<input type="button" name="cmdHantar" value="Kemaskini" onClick="javascript:kemaskini_pejabat('$id','$nopermohonanonline')">
		#else
		<input type="button" name="cmdHantar" value="Simpan" onClick="javascript:simpan_pejabat('$id','$nopermohonanonline')">
		#end
	
	#end
<!-- 	<input type="button" name="cmdBorangA" value="Cetak Borang A" onClick="javascript:cetakBorangA('$id','$!no_fail_online')"> -->
	<input type="button" name="cmdPengesahanA" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahan()">
	<div align="left">

<table width="100%" border="0">
<tr>
<td width="8%" valign="top">
<font color="red">*</font>Perhatian :
</td>
<td width="92%">
Untuk semakan dan cetakan maklumat permohonan, perisian Acrobat Reader diperlukan.
<br />
Sila Muat Turun perisian Acrobat Reader <a href="http://get.adobe.com/reader/" ><font color="blue"><b>disini</b></font></a> bagi yang tidak mempunyai perisian berkenaan.
</td>
</tr>
</table>
</div>
#end

</p>    </td>
  </tr>
</table>       
  </fieldset>
   </td>
  </tr>
   <tr>
                <td>
              
                </td>
                </tr>  
</table>
             
             
             
              #end </div>
          </div>
        </div></td>
    </tr>
  </table>
  #parse("app/ppk/paging_sek8.jsp")
  #parse("app/ppk/headerppk_script.jsp")
</form>
<script>
<!-- TAB -->
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != "")
	{
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	}
	else
	{
	elmnt.value ="";
	return;
	}
}

function submitForm() {    
//alert('$val_tab')
if('$!val_tab' != "" && '$!val_tab' != null)
{

   window.location.hash='$!val_tab';
   //goTo('$!val_tab');
   var nextFieldID = '$!val_tab';
   document.getElementById(nextFieldID).focus();
   }
   else
{
window.location.hash='maklumat_pemohon';
//goTo('maklumat_pemohon');
var nextFieldID = 'maklumat_pemohon';
   document.getElementById(nextFieldID).focus();
}
	
} 


function HtaamView() {
	document.f1.action = "";
	document.f1.mode.value = "Htaamview";
	document.f1.command.value = "Htaam";
	document.f1.submit();
}
function HAview() {
	document.f1.action = "";
	document.f1.mode.value = "view_harta_alih";
	document.f1.command.value = "harta_alih";
	document.f1.submit();
}

function NAview() {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "view_nilai_harta";
	document.f1.command.value = "nilai_harta";
	document.f1.submit();
}

function NAview1() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "view_nilai_harta";
	document.f1.command.value = "nilai_harta";
	document.f1.submit();
	}
	else
	{
	return;
	}
}

function PenghutangView() {
	document.f1.action = "";
	document.f1.mode.value = "Penghutangview";
	document.f1.command.value = "Penghutang";
	document.f1.submit();
}
function PemiutangView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemiutangview";
	document.f1.command.value = "Pemiutang";
	document.f1.submit();
}
function SaksiView() {
	document.f1.action = "";
	document.f1.mode.value = "Saksiview";
	document.f1.command.value = "Saksi";
	document.f1.submit();
}

function PentingView() {
	document.f1.action = "";
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
	document.f1.submit();
}

function WarisView() {
	document.f1.action = "";
	document.f1.mode.value = "Warisview";
	document.f1.command.value = "Waris";
	document.f1.submit();
}
function SimatiView() {
	document.f1.action = "";
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.submit();
	
}

function PemohonView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
	document.f1.submit();
}
function kembali_simati(){
	
	document.f1.command.value = "kembali_simati";
	document.f1.action = "";
	document.f1.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.f1.tabIdatas.value = tabIdatas;
    document.f1.tabIdtengah.value = tabIdtengah;
    document.f1.tabIdbawah.value = tabIdbawah;	
	document.f1.tabIdtepi.value = tabIdtepi;	
}
function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
document.f1.reset();
document.f1.txtNoKPBaru1Waris.focus();
}
}
<!-- NILAI HARTA -->
function getNilaiHartaKemaskini(){
document.f1.action="";
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="kemaskini_nilai_harta";
	document.f1.eventStatus.value="4";
	
	document.f1.submit();
}
function getNilaiHartaKemaskiniBatal(){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="kemaskini_nilai_harta";
	document.f1.eventStatus.value="4";
	document.f1.action="";
	document.f1.submit();
	}
	else
	{
	return;
	}
}
function getNilaiHartaSimpan(){

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="simpan_nilai_harta";
	document.f1.eventStatus.value="1";
	document.f1.action="";
	document.f1.submit();
	}
	else
	{
	return;
	}
}

function simpanPengesahan(){

	input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {
		document.f1.command.value="nilai_harta";
		document.f1.mode.value="simpanPengesahan";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
		}
		else
		{
		return;
		}
	}
	
function simpanPengesahan2(){

	input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {
		document.f1.command.value="nilai_harta";
		document.f1.mode.value="simpanPengesahan2";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
		}
		else
		{
		return;
		}
	}
	
function getNilaiHartaBatal(){
input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="batal_nilai_harta";
	document.f1.eventStatus.value="1";
	document.f1.action="";
	document.f1.submit();
	}
	else
	{
	return;
	}
}

<!-- HARTA ALIH -->
function getFormHa(){
	document.f1.command.value="harta_alih";
	document.f1.mode.value="tambah_harta";
	document.f1.eventStatus.value="0";
	document.f1.action="";
	document.f1.submit();
}

function getJenisHa(){
	document.f1.command.value="harta_alih";
	document.f1.mode.value="tambah_harta";
	document.f1.eventStatus.value="0";
	document.f1.action="";
	document.f1.submit();
}

function getSimpan(){
	
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.method="post";
		document.f1.command.value="harta_alih";
		document.f1.mode.value="simpan_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
	
		document.f1.submit();
	
	}
}
function edit_hartaalih(id3){
	document.f1.method="post";
	document.f1.command.value="harta_alih";
	document.f1.mode.value="edit_harta";
	document.f1.eventStatus.value="2";
	document.f1.idha.value=id3;
	document.f1.action="";
	document.f1.submit();
}
function getKemaskini(){
	document.f1.command.value="harta_alih";
	document.f1.mode.value="kemaskini_harta";
	document.f1.eventStatus.value="3";
	document.f1.action="";
	document.f1.submit();
}
/*
function hantar(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="nilai_harta";
		document.f1.mode.value="hantar_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
	}
}
*/

function hantar(seksyen,idPermohonan,idPermohonanSimati,tarikhMohon,idSimati){
	 if('$!headerppk.TOTAL_HTA' == 0 && '$!headerppk.TOTAL_HA' == 0)
     {         

	 
	 input_box = confirm("Maklumat harta simati belum didaftarkan. Jika permohonan  ini diteruskan, permohonan ini akan ditetapkan kepada pilihan 'Batal Permohonan (Lain - lain kes)' pada skrin Keputusan Permohonan. Sila pastikan maklumat catatan dimasukkan. Adakah anda pasti untuk teruskan?");
		if (input_box == true) {
			document.f1.command.value="nilai_harta";
		document.f1.mode.value="hantar_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
			
		}
		
	 }
		else
		{
			
	input_box = confirm("Sila pastikan Borang Nilaian Harta oleh PPBPP telah dicetak terlebih dahulu. Adakah anda pasti untuk teruskan?");
	if (input_box == true) {
		document.f1.command.value="nilai_harta";
		document.f1.mode.value="hantar_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
		
	//	hantar_terus(seksyen,idPermohonan,idPermohonanSimati,tarikhMohon,idSimati)
	}
		}
}


function hantar_terus(seksyen,idPermohonan,idPermohonanSimati,tarikhMohon,idSimati)
{
	
	
	 if('$!headerppk.TOTAL_HTA' == 0 && '$!headerppk.TOTAL_HA' == 0)
     {         

	 
	 input_box = confirm("Maklumat harta simati belum didaftarkan. Jika permohonan  ini diteruskan, permohonan ini akan ditetapkan kepada pilihan 'Batal Permohonan (Lain - lain kes)' pada skrin Keputusan Permohonan. Sila pastikan maklumat catatan dimasukkan. Adakah anda pasti untuk teruskan?");
		if (input_box == true) {
				
			if (seksyen == '8')
			{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
			}
				document.f1.idPermohonan.value = idPermohonan;
				document.f1.idpermohonansimati.value = idPermohonanSimati;
				document.f1.tarikh_mohon.value = tarikhMohon;
				document.f1.idSimati.value = idSimati;
				document.f1.method="POST";
				document.f1.submit();
		}
	 }else
	 {
		 if (seksyen == '8')
			{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
			}
				
				//document.f1.submit();
				input_box = confirm("Adakah anda pasti untuk teruskan?");
				if (input_box == true) {
					document.f1.command.value="nilai_harta";
					document.f1.mode.value="hantar_harta";
					document.f1.eventStatus.value="1";
					//document.f1.action="";
					document.f1.idPermohonan.value = idPermohonan;
					document.f1.idpermohonansimati.value = idPermohonanSimati;
					document.f1.tarikh_mohon.value = tarikhMohon;
					document.f1.idSimati.value = idSimati;
					document.f1.method="POST";
					document.f1.submit();
					
				//	hantar_terus(seksyen,idPermohonan,idPermohonanSimati,tarikhMohon,idSimati)
				}
		 
		 }
		
	    
}

function getUnitPPK(idpermohonan,nopermohonanonline) {
	if (document.f1.socNegeriPengesahan.value=="0"){
		alert("Sila pilih Negeri");
		socNegeriPengesahan.focus();
	}
	else if (document.f1.socDaerahPengesahan.value=="0"){
		alert("Sila pilih Daerah");
		socDaerahPengesahan.focus();
	}
	else if (document.f1.txtbilhta.value=="0"){
		alert("Sila muatnaik dokumen bagi setiap Harta Tak Alih.");
	}
	else if (document.f1.txtbilha.value=="0"){
		alert("Sila muatnaik dokumen bagi setiap Harta Alih.");
	}
	else if (document.f1.txtbilic.value=="0"){
		alert("Sila muatnaik dokumen bagi setiap waris.");
	}
	else if(document.f1.namaDoc1.value == "0"){
	    alert('Sila muatnaik Borang A untuk meneruskan permohonan.');
	    document.f1.uploadmyid.focus(); 
	}
	else if(namecb1.checked == false){
    	alert('Sila tanda pada checkbox untuk teruskan permohonan.');
    	return;
 	}
	else{
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) 
		{
			document.f1.method="post";
			document.f1.command.value="nilai_harta";
			document.f1.mode.value="cetak_surat";
			//doAjaxCall${formName}("cetak_surat");
    	document.f1.submit();
		}
	}
}

function kemaskini_pejabat(idpermohonan,nopermohonanonline) {

		document.f1.method="post";
		document.f1.command.value="nilai_harta";
		document.f1.mode.value="kemaskini_pejabat";
		document.f1.submit();
	
}

function simpan_pejabat(idpermohonan,nopermohonanonline) {
 if (document.f1.socNegeriPengesahan.value=="0")
	{
		alert("Sila pilih Negeri");
		socNegeriPengesahan.focus();
	}
	else if (document.f1.socDaerahPengesahan.value=="0")
	{
		alert("Sila pilih Daerah");
		socDaerahPengesahan.focus();
	}
	else
	{
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) 
		{
		document.f1.method="post";
		document.f1.command.value="nilai_harta";
		document.f1.mode.value="simpan_pejabat";
		document.f1.submit();
	}
	}
}


function cetakPengesahan() {
    
	
	if('$skrin_online_popup' == "yes")
   {
   var url="../../servlet/ekptg.report.ppk.PengesahanPermohonanOnline?idpermohonan="+document.f1.idpermohonan.value;
   }
   else
   {
   var url="../servlet/ekptg.report.ppk.PengesahanPermohonanOnline?idpermohonan="+document.f1.idpermohonan.value;
   }
	
	
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	
	//refreshPengesahanView();
}

function refreshPengesahanView() {
	document.f1.method="post";
	document.f1.command.value="nilai_harta";
		//doAjaxCall${formName}("pengesahan_permohonan");	
	document.f1.action.value="";
	document.f1.submit();
	
	
}

function cetakBorangA() {

   if('$skrin_online_popup' == "yes")
   {
    var url="../../servlet/ekptg.report.ppk.BorangAOnline?idPermohonan="+document.f1.idPermohonan.value;
   }
   else
   {
   var url="../servlet/ekptg.report.ppk.BorangAOnline?idPermohonan="+document.f1.idPermohonan.value;
   }
    var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
}

/* function cetakBorangADraf() {
	var reportfile = "BorangAOnlineDraf_Malay";
	var params = new Array();
	params[0] = "output="+(document.f1.rFormat.value).toLowerCase();
	params[1] = "idPermohonan="+document.f1.idPermohonan.value;
	
	printReport(reportfile,params);
} */

function cetakBorangADraf() {
	//alert("negeri >>> "+document.f1.socNegeriPengesahan.value);
	//alert("daerah >>> "+document.f1.socDaerahPengesahan.value);
	if('$skrin_online_popup' == "yes")
   	{
		var url="../../servlet/ekptg.report.ppk.BorangAOnlineDraf?idPermohonan="+document.f1.idPermohonan.value+"&id_negeri="+document.f1.socNegeriPengesahan.value+"&id_daerah="+document.f1.socDaerahPengesahan.value;
    }
    else
    {
		var url="../servlet/ekptg.report.ppk.BorangAOnlineDraf?idPermohonan="+document.f1.idPermohonan.value+"&id_negeri="+document.f1.socNegeriPengesahan.value+"&id_daerah="+document.f1.socDaerahPengesahan.value;
    }
	//alert('url : '+url);
	var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	/*var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	  */
}

function getDaerah(){
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="selection_daerah";
	//document.f1.tabIdatas.value="4";
	//document.f1.tabIdtengah.value="0";
	//document.f1.tabIdbawah.value="0";
	//document.f1.tabIdtepi.value="0";
	document.f1.action.value="";	
	document.f1.submit();	
}
function getAddress(){
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="ppkAddressView";
	//document.f1.tabIdatas.value="4";
	//document.f1.tabIdtengah.value="0";
	//document.f1.tabIdbawah.value="0";
	//document.f1.tabIdtepi.value="0";
	document.f1.action.value="";	
	document.f1.submit();	
}




function getHapus(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="harta_alih";
		document.f1.mode.value="hapus_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
	}
}
function getUpdate(){
	
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="harta_alih";
		document.f1.mode.value="update_harta";
		document.f1.eventStatus.value="2";
		document.f1.action="";
		document.f1.submit();
	
	}
}
function getBatal(){
		document.f1.command.value="harta_alih";
		document.f1.mode.value="batal_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
}



<!-- SIMATI -->

</script>
<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});


function kembalix() {
	document.f1.method = "POST";
	document.f1.command.value="papar";
	document.f1.action = "";
	document.f1.submit();
}
function kembalidaftar()
{
        document.f1.command.value="kembali_daftar_pemohon";
		document.f1.eventStatus.value="1";
		document.f1.action = "";
		document.f1.submit();
}

function cetakNilaiHarta(noFail) 
{
  	//alert("noFail = "+noFail);
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="view_nilai_harta";		
	document.f1.action="";
	document.f1.printOption.value = "hantar";
	document.f1.submit();
	//cetakNilaiHarta2(noFail);
	
  	//remove yang ni dahulu start
 	//var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=NilaianHartaPPSPP&flagReport=S";
    //var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    //if ((document.window != null) && (!hWnd.opener))
	//hWnd.opener = document.window;
    //if (hWnd.focus != null) hWnd.focus();
    //remove yang ni dahulu end
}

function cetakNilaiHarta2(noFail) 
{   
	//alert("cetakNilaiHarta2");
   	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=NilaianHartaPPSPP&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

function setTable(id){
	//alert("dsdssd")
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
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
	document.f1.action = "";
	document.f1.submit();
}




function samakan_hta(elem){ 
//alert(elem)
alert(document.f1.txtHtaNilaiTarikhMohon[elem].value)
/* 
if(document.f1.ids != null){
//if(document.f1.ids.length )
      var c = 0;
	  
	if(document.f1.ids.length > 1)
	{  
	  for (i = 0; i < document.f1.ids.length; i++)
	  {
      if (document.f1.ids[i].checked == false)
	    {	 
	  	c++
		}
	  } 
	 }
    else
	 {
	 	if (document.f1.ids.checked == false)
	    {	 
	  	c++
		}
	 } 
	  
	   
	  
	  
	  if(c>0)
	  {	  
	  document.f1.all.checked = false;
	  }
	  else
	  {
	  document.f1.all.checked = true;
	  }
	}
	*/
	  
}


function samakan(cnt)
{
//txtHtaNilaiTarikhMohon
//txtHtaNilaiTarikhMati
var index = parseInt(cnt-1)
var lg = document.f1.txtHtaNilaiTarikhMohon.length
//var val = document.f1.txtHtaNilaiTarikhMohon.value

if(lg > 0)
{
//alert(">0")
document.f1.txtHtaNilaiTarikhMati[index].value = document.f1.txtHtaNilaiTarikhMohon[index].value
}
else
{
//alert("0")
document.f1.txtHtaNilaiTarikhMati.value = document.f1.txtHtaNilaiTarikhMohon.value
}


}

function samakan1(cnt)
{



var index = parseInt(cnt-1)
var lg = document.f1.txtHaNilaiTarikhMohon.length
//var val = document.f1.txtHtaNilaiTarikhMohon.value

if(lg > 0)
{
//alert(">0")
document.f1.txtHaNilaiTarikhMati[index].value = document.f1.txtHaNilaiTarikhMohon[index].value
}
else
{
//alert("0")
document.f1.txtHaNilaiTarikhMati.value = document.f1.txtHaNilaiTarikhMohon.value
}


}

function lampiran(idSimati,jenisUpload) {	
	// console.log("syafiqah :"+idPermohonan);
	jenisUpload = "paparboranga";
	var url = "../x/${securityToken}/ekptg.view.ppk.util.FrmUploadDokumen?actionrefresh=dokumenA&actionPopup="+jenisUpload+"&rujukan="+idSimati+"&flagOnline=$!flagOnline";
    url +="&jenisdokumen=99211";
		
	//
    var hWnd = window.open(url,'printuser','width=400,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus(); /**/
    //
    var title = 'Lampiran';
	var w =1024;
	var h = 800;
    var left = (screen.width/2)-(w/2);

}

function semakLampiran(){
  	document.f1.command.value="nilai_harta";
	document.f1.mode.value="ppkAddressView";
	//document.f1.tabIdatas.value="4";
	//document.f1.tabIdtengah.value="0";
	//document.f1.tabIdbawah.value="0";
	//document.f1.tabIdtepi.value="0";
	document.f1.action.value="";	
	document.f1.submit();
}

</script>
</body>
</html>
