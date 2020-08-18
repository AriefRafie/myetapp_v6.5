
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
.style3 {font-size: 12px}

 
.style4 {color: #0000FF}
.style10 {color: #000000}
.style12 {font-size: x-small}
.style13 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
-->
</style>
</head>
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

<input name="printOption" type="hidden" id="printOption"/>

 <input type="hidden" name="command" value="">
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


<input name="nowpast" type="hidden" id="nowpast" value="$nowpast"/>
 
  #foreach($PermohonanSebelum in $listGetPermohonanSebelum)
#set ($id_Permohonan_terdahulu = $PermohonanSebelum.id_Permohonan)
#set ($no_subjaket = $PermohonanSebelum.no_subjaket)
#end
<input type="hidden" name="dah_daftar_ke" id="dah_daftar_ke" value="sudah" />
<input name="id_Permohonan_terdahulu" type="hidden"  value="$id_Permohonan_terdahulu"/>
<input name="no_subjaket" type="hidden"  value="$no_subjaket"/>
 
 

#foreach($listFail in $ViewFail)

<input name="id_Suburusanstatus" type="hidden"  value="$listFail.id_Suburusanstatus"/>
<input name="id_Suburusanstatusfail" type="hidden"  value="$listFail.id_Suburusanstatusfail"/>
#end


#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    #set ($status = $list.id_Status)
    #set ($idStatus = $list.id_Status)
    #set ($permohonan_mati = $list.id_Permohonansimati)
    #set ($flag_print_nilaian_harta = $list.FLAG_PRINT_NILAIAN_HARTA)
    
  		    

<input name="id_Permohonansimati" type="hidden"  value="$list.id_Permohonansimati"/>
<input name="idpermohonansimati" type="hidden"  value="$list.id_Permohonansimati"/> 
    
<input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
<input name="idpermohonan_baru" type="hidden" value="$list.idPermohonan" />
    <input name="idPermohonan" type="hidden"  value="$id"/>
     <input name="idpermohonan" type="hidden"  value="$id"/>
<input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
        <input name="idstatus" type="hidden"  value="$list.id_Status"/>
         <input name="id_Fail" type="hidden" value="$list.idFail" />
        
    #set($listnoFail = $list.noFail)
#set($listidnegeri = $list.idnegeri)
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

<td>#parse("app/ppk/maklumat_sek8.jsp")


 #set($md=$listtarikhMohon)
          <input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
                <input type="hidden" name="txdTarikhMohon" id="txdTarikhMohon" value="$listtarikhMohon" />
                    <input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
            <input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/>
             <input type="hidden" name="id_fail" value="$listidFail" readonly="true"/>
</td>
</tr>


  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()" >PERMOHONAN</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(1,0,0,0);HtaamView()">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(2,0,0,0);HAview()" >HARTA ALIH</li>
      #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" id='maklumat_pemohon' >NILAIAN HARTA</li>
      #else
      #if($!hideTabPengesahan == "show")
       <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" > 
       PENGESAHAN PERMOHONAN</li>
       #end
      #end
    </ul>
    <div >
    <div >
     
        <div id="TabbedPanels2">
         
         
        </div>
      </div>
      <div>
        <div id="TabbedPanels4" >
          
        </div>
      </div>
      <div >
 

      </div>
      <div class="TabbedPanelsContent">
  <table width="100%" border="0">
   <tr>
    <td>
        <fieldset >
         #parse("app/ppk/info_berjaya_disimpan.jsp")
         
         
          #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
          <table width="100%" border="0" >
  <tr>
    <td>
    
<fieldset>
<legend>NILAI HARTA TAK ALIH TAMBAHAN</legend>
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
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
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
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
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
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
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
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
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
#end
<tr >
<td  colspan="8" align="left"><span class="style4">TIADA REKOD HARTA TAK ALIH TAMBAHAN</span></td>
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
<td  align="right" width="12%">
#if($sumhta == 0)
<b>
0.00
</b>
#else
<b>
$Util.formatDecimal($sumhta)
</b>
#end
</td>
<td  align="right" width="12%">
#if($sumhtamati == 0)
<b>
0.00
</b>
#else
<b>
$Util.formatDecimal($sumhtamati)
</b>
#end
</td>
</tr>
</table>
</fieldset>
<br>
<fieldset>
<legend>NILAI HARTA ALIH TAMBAHAN</legend>

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
#set ($nilai_tarikhmohon = "")
#end
#if($listha2.nilai_tarikhmati!="")
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

<td align="right"><input name="txtHaNilaiTarikhMohon" type="text" id="txtHaNilaiTarikhMohon" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMohon');validateModal(this,this.value,'$nilai_tarikhmohon');samakan1($cnt)" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMohon')" value="$nilai_tarikhmohon" size="15" maxlength="15" ></td>

<td align="right"><input name="txtHaNilaiTarikhMati" type="text" id="txtHaNilaiTarikhMati" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMati');validateModal(this,this.value,'$nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMati')" value="$nilai_tarikhmati" size="15" maxlength="15" ></td>

#else

<td align="right">
#if($nilai_tarikhmohon>0)
$Util.formatDecimal($nilai_tarikhmohon)
#elseif($nilai_tarikhmohon==0)
0.00
#else
$nilai_tarikhmohon
#end</td>


<td align="right">
#if($nilai_tarikhmati>0)
$Util.formatDecimal($nilai_tarikhmati)
#elseif($nilai_tarikhmati==0)
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
#if($listha2.nilai_tarikhmati!="")
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

<!--
<tr class="table_header">
<td colspan="4"><b>Jumlah Nilai Harta Alih Tambahan (RM) </b></td>
#if($sumjumlah == 0)
<td align="right"><b>0.00</b></td>
#else
<td align="right"><b>$Util.formatDecimal($sumjumlah)</b></td>
#end



#if($sumjumlahmati == 0)
<td align="right"><b>0.00</b></td>
#else
<td align="right"><b>$Util.formatDecimal($sumjumlahmati)</b></td>
#end

</tr>
-->
<tr class="table_header">
<td  width="76%" colspan="4" ><b>Jumlah Nilai Harta Alih (RM)</b></td>
<td  align="right" width="12%">
#if($sumjumlah == 0)
<b>
0.00
</b>
#else
<b>
$Util.formatDecimal($sumjumlah)
</b>
#end
</td>
<td  align="right" width="12%">
#if($sumjumlahmati == 0)
<b>
0.00
</b>
#else
<b>
$Util.formatDecimal($sumjumlahmati)
</b>
#end
</td>
</tr>


</table>
</fieldset>

<br />
<fieldset>
<legend>NILAI HARTA TAK ALIH TERDAHULU</legend>
#set($checkhta = "")
#set($checkhta1 = "")
#set($checkhta2 = "")
#set($checkhta3 = "")




#set($xjumpa_lain = "")
#set($xjumpa_kelompok = "")
#set($xjumpa_beli = "")
#set($xjumpa_alih = "")

#if($sumppkhtadulu.size() > 0)












#set ($cntX = 0)

<fieldset>
<legend>ADA HAKMILIK</legend>
<table width="100%">


#foreach($listhath in $sumppkhtadulu)
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
#set ($sumhtadulu = 0)
#set ($sumhtamatidulu = 0)
#set ($cnt = 0)
#set ($i = 0)

#foreach($listhath in $sumppkhtadulu)







#if($listhath.jenis_hta == "Y")
#set($checkhta = "") 
#foreach($lo in $check_pilihan) 
   
#if($lo.id_hta == $listhath.idhta && $lo.id_permohonansimati == $permohonan_mati)
#if($listhath.flag_pt == "Y" && $listhath.flag_pa == "Y")
#set($checkhta = "papt")
#elseif($listhath.flag_pt == "Y" && $listhath.flag_pa != "Y")
#set($checkhta = "pt")
#elseif($listhath.flag_pt != "Y" && $listhath.flag_pa == "Y")
#set($checkhta = "pa")
#else
#set($checkhta = "")
#end
#end
#end

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

 

#if($checkhta == "papt")
<tr  class="bg_papt" >
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
	
		<td align="right">
        
        
        	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    
        
        
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
        
            <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
    
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>
#elseif($checkhta == "pa")
<tr class="bg_pa"  >
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
	
		<td align="right">
        
        
        	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    
        
        
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
        
            <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
    
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>
#elseif($checkhta == "pt")
<tr class="bg_pt" >
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
	
		<td align="right">
        
        
        	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    
        
        
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
        
            <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
    
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>
#else
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
	
		<td align="right">
        
        
        	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    
        
        
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
        
            <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
    
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>
#end








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
#foreach($listhath in $sumppkhtadulu)
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

#set ($sumhtadulu = 0)
#set ($sumhtamatidulu = 0)
#set ($cnt = 0)
#set ($i = 0)


#foreach($listhath in $sumppkhtadulu)

#if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 1)

#set($checkhta1 = "")  
#foreach($lo in $check_pilihan) 
  
#if($lo.id_hta == $listhath.idhta && $lo.id_permohonansimati == $permohonan_mati)
#if($listhath.flag_pt == "Y" && $listhath.flag_pa == "Y")
#set($checkhta1 = "papt")
#elseif($listhath.flag_pt == "Y" && $listhath.flag_pa != "Y")
#set($checkhta1 = "pt")
#elseif($listhath.flag_pt != "Y" && $listhath.flag_pa == "Y")
#set($checkhta1 = "pa")
#else
#set($checkhta1 = "")
#end
#end
#end

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

   
#if($checkhta1 == "papt")



<tr   class="bg_papt" >
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
    
     
	
<td align="right">
	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
        
         <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>



#elseif($checkhta1 == "pa")


<tr  class="bg_pa" >
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
    
     
	
<td align="right">
	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
        
         <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>


#elseif($checkhta1 == "pt")
<tr  class="bg_pt" >
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
    
     
	
<td align="right">
	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
        
         <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>
#else
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
    
     
	
<td align="right">
	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
        
         <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>
#end





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

#foreach($listhath in $sumppkhtadulu)
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


#set ($sumhtadulu = 0)
#set ($sumhtamatidulu = 0)
#set ($cnt = 0)
#set ($i = 0)



#foreach($listhath in $sumppkhtadulu)

#if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 2)
#set($checkhta2 = "")
#foreach($lo in $check_pilihan)     
#if($lo.id_hta == $listhath.idhta && $lo.id_permohonansimati == $permohonan_mati)
#if($listhath.flag_pt == "Y" && $listhath.flag_pa == "Y")
#set($checkhta2 = "papt")
#elseif($listhath.flag_pt == "Y" && $listhath.flag_pa != "Y")
#set($checkhta2 = "pt")
#elseif($listhath.flag_pt != "Y" && $listhath.flag_pa == "Y")
#set($checkhta2 = "pa")
#else
#set($checkhta2 = "")
#end
#end
#end


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

#if($checkhta2 == "papt")


<tr  class="bg_papt" >
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
    
    
	
	<td align="right">
    
    	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
         <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>

#elseif($checkhta2 == "pa")

<tr  class="bg_pa" >
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
    
    
	
	<td align="right">
    
    	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
         <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>

#elseif($checkhta2 == "pt")

<tr  class="bg_pt">
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
    
    
	
	<td align="right">
    
    	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
         <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>

#else
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
    
    
	
	<td align="right">
    
    	<input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
         <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>
#end


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

#foreach($listhath in $sumppkhtadulu)
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


#set ($sumhtadulu = 0)
#set ($sumhtamatidulu = 0)
#set ($cnt = 0)
#set ($i = 0)

#foreach($listhath in $sumppkhtadulu)

#if($listhath.jenis_hta == "T" && $listhath.ketegori_hta == 3)
#set($checkhta3 = "")  
#foreach($lo in $check_pilihan)   

#if($lo.id_hta == $listhath.idhta && $lo.id_permohonansimati == $permohonan_mati)
#if($listhath.flag_pt == "Y" && $listhath.flag_pa == "Y")
#set($checkhta3 = "papt")
#elseif($listhath.flag_pt == "Y" && $listhath.flag_pa != "Y")
#set($checkhta3 = "pt")
#elseif($listhath.flag_pt != "Y" && $listhath.flag_pa == "Y")
#set($checkhta3 = "pa")
#else
#set($checkhta3 = "")
#end
#end
#end

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


#if($checkhta3 == "papt")

<tr  class="bg_papt" >
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
    
    	
	
	<td align="right">
    <input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
          <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>

#elseif($checkhta3 == "pa")
<tr  class="bg_pa" >
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
    
    	
	
	<td align="right">
    <input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
          <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>
#elseif($checkhta3 == "pt")

<tr   class="bg_pt" >
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
    
    	
	
	<td align="right">
    <input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
          <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>

#else

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
    
    	
	
	<td align="right">
    <input name="txtHtaNilaiTarikhMohon" type="hidden" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon')" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
          <input   name="txtHtaNilaiTarikhMati" type="hidden" id="txtHtaNilaiTarikhMati" onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMati');validateModal(this,this.value,'$listhath.nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMati')" value="$listhath.nilai_tarikhmati" size="15" maxlength="15" >
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
	#else
   

    
	<td align="right">
    #if($listhath.nilai_tarikhmohon>0 && $listhath.nilai_tarikhmohon!="")
    $Util.formatDecimal($listhath.nilai_tarikhmohon)
    #elseif( $listhath.nilai_tarikhmohon==0 && $listhath.nilai_tarikhmohon!="" )
    0.00
    #else
    $listhath.nilai_tarikhmohon
    #end    </td>
    
    
    	<td align="right">
    #if($listhath.nilai_tarikhmati>0 && $listhath.nilai_tarikhmati != "")
    $Util.formatDecimal($listhath.nilai_tarikhmati)
    #elseif($listhath.nilai_tarikhmati == 0 && $listhath.nilai_tarikhmati != "")
     0.00
    #else
   $listhath.nilai_tarikhmati
    #end    </td>
    
	#end</tr>


#end


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
#if($sumppkhtadulu.size() > 0)
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
<tr >
<td  colspan="8" align="left"><span class="style4">TIADA REKOD HARTA TAK ALIH TERDAHULU</span></td>
</tr>

</table>
 #end
 
#set ($sumhtadulu = 0)
#set ($sumhtamatidulu = 0)
 #foreach($listhath in $sumppkhtadulu)

    #if($listhath.nilai_tarikhmohon!="")
	#set ($sumhtadulu = $sumhtadulu + $listhath.nilai_tarikhmohon)
    #end
    
    
    #if($listhath.nilai_tarikhmati!="")
	#set ($sumhtamatidulu = $sumhtamatidulu + $listhath.nilai_tarikhmati)
    #end
    
    #end
 
<table width="100%">



<tr class="table_header">
<td  width="76%" ><b>Jumlah Nilai Harta Tak Alih Terdahulu (RM)</b></td>
<td  align="right" width="12%">
#if($sumhtadulu == 0)
<b>
0.00
</b>
#else
<b>
$Util.formatDecimal($sumhtadulu)
</b>
#end
</td>
<td  align="right" width="12%">
#if($sumhtamatidulu == 0)
<b>
0.00
</b>
#else
<b>
$Util.formatDecimal($sumhtamatidulu)
</b>
#end
</td>
</tr>












</table>
</fieldset>


<br>
<fieldset>
<legend>NILAI HARTA ALIH TERDAHULU</legend>

<table width="100%" >
#if($listHaDulu.size() > 0)
<tr class="table_header">
<td width="2%" >Bil</td>
<td width="46%" >Jenis Harta Alih</td>
<td width="14%" ><div align="center">No Rujukan UPT / No Daftar / <br>
  No Akaun / No Ahli</div></td>
<td width="14%" ><div align="center">No Sijil</div></td>

<td width="12%" ><div align="center">Nilai HA Tarikh Mohon (RM)</div></td>

<td width="12%" ><div align="center">Nilai HA Tarikh Mati (RM)</div></td>
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
#set ($nilai_tarikhmohond = "")
#set ($nilai_tarikhmatid = "")
#set ($sumppkha = "")
#set($checkha = "")



#set ($cnt = 0)

#if($listHaDulu.size() > 0)
#foreach($listha2 in $listHaDulu)
#set($checkha = "")  


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



#foreach($lo in $check_pilihan_ha) 
  
#if($lo.id_ha == $listha2.id_Ha && $lo.id_permohonansimati == $permohonan_mati)
#if($listha2.flag_pt == "Y" && $listha2.flag_pa == "Y")
#set($checkha = "papt")
#elseif($listha2.flag_pt == "Y" && $listha2.flag_pa != "Y")
#set($checkha = "pt")
#elseif($listha2.flag_pt != "Y" && $listha2.flag_pa == "Y")
#set($checkha = "pa")
#else
#set($checkha = "")
#end
#end
#end






#set ($id_had = $listha2.id_Ha)
#set ($jenisd = $listha2.Keterangan)
#set ($no_Perjanjiand = $listha2.noDaftar)
#set ($sijild = $listha2.nosijil)
#if($listha2.nilai_tarikhmohon)
#set ($nilai_tarikhmohond = $listha2.nilai_tarikhmohon)
#end
#if($listha2.nilai_tarikhmati!="")
#set ($nilai_tarikhmatid = $listha2.nilai_tarikhmati)
#end
#set ($cnt = $cnt + 1)


#if($checkha == "papt")
<tr class="bg_papt"  style="text-transform:uppercase;" >
<td class="style10">$cnt</td>
<td class="style10">$jenisd $cat</td> 
<td class="style10">$no_Perjanjiand</td>
<td class="style10">$sijild</td>
#if ($EventStatus == 4)
<input type="hidden" name="idHad" value="$listha2.id_Ha">
<td align="right" class="style10">
<input name="txtHaNilaiTarikhMohon" type="hidden" id="txtHaNilaiTarikhMohon" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMohon');validateModal(this,this.value,'$nilai_tarikhmohond');samakan1($cnt)" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMohon')" value="$nilai_tarikhmohond" size="15" maxlength="15" >
#if($nilai_tarikhmohond>0)
$Util.formatDecimal($nilai_tarikhmohond)
#elseif($nilai_tarikhmohond==0)
0.00
#else
$nilai_tarikhmohond
#end </td>
<td align="right" class="style10">
<input name="txtHaNilaiTarikhMati" type="hidden" id="txtHaNilaiTarikhMati" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMati');validateModal(this,this.value,'$nilai_tarikhmatid');" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMati')" value="$nilai_tarikhmatid" size="15" maxlength="15" >
#if($nilai_tarikhmatid>0)
$Util.formatDecimal($nilai_tarikhmatid)
#elseif($nilai_tarikhmatid==0)
0.00
#else
$nilai_tarikhmatid
#end </td>
#else
<td align="right" class="style10"> #if($nilai_tarikhmohond>0)
$Util.formatDecimal($nilai_tarikhmohond)
#elseif($nilai_tarikhmohond==0)
0.00
#else
$nilai_tarikhmohond
#end</td>
<td align="right" class="style10"> #if($nilai_tarikhmatid>0)
$Util.formatDecimal($nilai_tarikhmatid)
#elseif($nilai_tarikhmatid==0)
0.00
#else
$nilai_tarikhmatid
#end</td>
#end</tr>
#elseif($checkha == "pa")
<tr  class="bg_pa" style="text-transform:uppercase;" >
<td class="style10">$cnt</td>
<td class="style10">$jenisd $cat</td> 
<td class="style10">$no_Perjanjiand</td>
<td class="style10">$sijild</td>
#if ($EventStatus == 4)
<input type="hidden" name="idHad" value="$listha2.id_Ha">
<td align="right" class="style10">
<input name="txtHaNilaiTarikhMohon" type="hidden" id="txtHaNilaiTarikhMohon" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMohon');validateModal(this,this.value,'$nilai_tarikhmohond');samakan1($cnt)" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMohon')" value="$nilai_tarikhmohond" size="15" maxlength="15" >
#if($nilai_tarikhmohond>0)
$Util.formatDecimal($nilai_tarikhmohond)
#elseif($nilai_tarikhmohond==0)
0.00
#else
$nilai_tarikhmohond
#end</td>
<td align="right" class="style10">
<input name="txtHaNilaiTarikhMati" type="hidden" id="txtHaNilaiTarikhMati" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMati');validateModal(this,this.value,'$nilai_tarikhmatid');" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMati')" value="$nilai_tarikhmatid" size="15" maxlength="15" >
#if($nilai_tarikhmatid>0)
$Util.formatDecimal($nilai_tarikhmatid)
#elseif($nilai_tarikhmatid==0)
0.00
#else
$nilai_tarikhmatid
#end</td>
#else
<td align="right" class="style10"> #if($nilai_tarikhmohond>0)
$Util.formatDecimal($nilai_tarikhmohond)
#elseif($nilai_tarikhmohond==0)
0.00
#else
$nilai_tarikhmohond
#end</td>
<td align="right" class="style10"> #if($nilai_tarikhmatid>0)
$Util.formatDecimal($nilai_tarikhmatid)
#elseif($nilai_tarikhmatid==0)
0.00
#else
$nilai_tarikhmatid
#end</td>
#end</tr>
#elseif($checkha=="pt")
<tr  class="bg_pt" style="text-transform:uppercase;" >
<td class="style10">$cnt</td>
<td class="style10">$jenisd $cat</td> 
<td class="style10">$no_Perjanjiand</td>
<td class="style10">$sijild</td>
#if ($EventStatus == 4)
<input type="hidden" name="idHad" value="$listha2.id_Ha">
<td align="right" class="style10">
<input name="txtHaNilaiTarikhMohon" type="hidden" id="txtHaNilaiTarikhMohon" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMohon');validateModal(this,this.value,'$nilai_tarikhmohond');samakan1($cnt)" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMohon')" value="$nilai_tarikhmohond" size="15" maxlength="15" >
#if($nilai_tarikhmohond>0)
$Util.formatDecimal($nilai_tarikhmohond)
#elseif($nilai_tarikhmohond==0)
0.00
#else
$nilai_tarikhmohond
#end</td>
<td align="right" class="style10">
<input name="txtHaNilaiTarikhMati" type="hidden" id="txtHaNilaiTarikhMati" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMati');validateModal(this,this.value,'$nilai_tarikhmatid');" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMati')" value="$nilai_tarikhmatid" size="15" maxlength="15" >
#if($nilai_tarikhmatid>0)
$Util.formatDecimal($nilai_tarikhmatid)
#elseif($nilai_tarikhmatid==0)
0.00
#else
$nilai_tarikhmatid
#end</td>
#else
<td align="right" class="style10"> #if($nilai_tarikhmohond>0)
$Util.formatDecimal($nilai_tarikhmohond)
#elseif($nilai_tarikhmohond==0)
0.00
#else
$nilai_tarikhmohond
#end</td>
<td align="right" class="style10"> #if($nilai_tarikhmatid>0)
$Util.formatDecimal($nilai_tarikhmatid)
#elseif($nilai_tarikhmatid==0)
0.00
#else
$nilai_tarikhmatid
#end</td>
#end</tr>
#else
<tr bgcolor="white" style="text-transform:uppercase;" >
<td class="style10">$cnt</td>
<td class="style10">$jenisd $cat</td> 
<td class="style10">$no_Perjanjiand</td>
<td class="style10">$sijild</td>
#if ($EventStatus == 4)
<input type="hidden" name="idHad" value="$listha2.id_Ha">
<td align="right" class="style10">
<input name="txtHaNilaiTarikhMohon" type="hidden" id="txtHaNilaiTarikhMohon" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMohon');validateModal(this,this.value,'$nilai_tarikhmohond');samakan1($cnt)" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMohon')" value="$nilai_tarikhmohond" size="15" maxlength="15" >
#if($nilai_tarikhmohond>0)
$Util.formatDecimal($nilai_tarikhmohond)
#elseif($nilai_tarikhmohond==0)
0.00
#else
$nilai_tarikhmohond
#end</td>
<td align="right" class="style10">
<input name="txtHaNilaiTarikhMati" type="hidden" id="txtHaNilaiTarikhMati" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMati');validateModal(this,this.value,'$nilai_tarikhmatid');" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMati')" value="$nilai_tarikhmatid" size="15" maxlength="15" >
#if($nilai_tarikhmatid>0)
$Util.formatDecimal($nilai_tarikhmatid)
#elseif($nilai_tarikhmatid==0)
0.00
#else
$nilai_tarikhmatid
#end</td>
#else
<td align="right" class="style10">
#if($nilai_tarikhmohond>0)
$Util.formatDecimal($nilai_tarikhmohond)
#elseif($nilai_tarikhmohond==0)
0.00
#else
$nilai_tarikhmohond
#end</td>
<td align="right" class="style10">
#if($nilai_tarikhmatid>0)
$Util.formatDecimal($nilai_tarikhmatid)
#elseif($nilai_tarikhmatid==0)
0.00
#else
$nilai_tarikhmatid
#end</td>
#end</tr>

#end







#end

#else
<tr>
<td colspan="6" ><span class="style4">TIADA REKOD HARTA ALIH TERDAHULU</span></td>
</tr>
#end

#set ($sumjumlahd = 0)
#set ($sumjumlahmatid = 0)
#foreach($listha2 in $listHaDulu)
#if($listha2.nilai_tarikhmohon!="")
#set ($sumjumlahd = $sumjumlahd + $listha2.nilai_tarikhmohon)
#end
#if($listha2.nilai_tarikhmati!="")
#set ($sumjumlahmatid = $sumjumlahmatid + $listha2.nilai_tarikhmati)
#end
#end
<input type="hidden" name="txtJumlahHaTarikhMatid" value="$sumjumlahmati">
<input type="hidden" name="txtJumlahHaTarikhMohond" value="$sumhta">


#set ($overalljumlahmati = 0)
#foreach($listOverallmati in $overallmati)
#if($listOverallmati.nilaibesarmati!="")
#set ($overalljumlahmati = $overalljumlahmati + $listOverallmati.nilaibesarmati)
#end
#end
<input type="hidden" name="txtJumlahBesarTarikhMatid" value="$overalljumlahmatid">
<input type="hidden" name="txtJumlahBesarTarikhMohond" value="$overalljumlahd">
<!--

<tr class="table_header">
<td colspan="4"><b>Jumlah Nilai Harta Alih Terdahulu (RM) </b></td>
#if($sumjumlahd == 0)
<td align="right"><b>0.00</b></td>
#else
<td align="right"><b>$Util.formatDecimal($sumjumlahd)</b></td>
#end


#if($sumjumlahmatid == 0)
<td align="right"><b>0.00</b></td>
#else
<td align="right"><b>$Util.formatDecimal($sumjumlahmatid)</b></td>
#end

</tr>
-->

<tr class="table_header">
<td  width="76%" colspan="4"  ><b>Jumlah Nilai Harta Alih Terdahulu (RM)</b></td>
<td width="12%"  align="right" >
#if($sumjumlahd == 0)
<b>
0.00</b>
#else
<b>
$Util.formatDecimal($sumjumlahd)</b>
#end</td>
<td width="12%"  align="right" >
#if($sumjumlahmatid == 0)
<b>
0.00</b>
#else
<b>
$Util.formatDecimal($sumjumlahmatid)</b>
#end</td>
</tr>
</table>
</fieldset>

<br>
<fieldset><legend>NILAI HARTA KESELURUHAN</legend>


<table width="100%">
<tr class="table_header">
<td width="2%">Bil</td>
<td width="74%">Perkara</td>
<td width="12%"><div align="center">Jumlah Nilai Harta Tarikh Mohon(RM)</div></td>
<td width="12%"><div align="center">Jumlah Nilai Harta Tarikh Mati(RM)</div></td>
</tr>
<tr class="row2">
<td>1 </td>
<td><strong>Jumlah Nilai Harta Tak Alih (Terdahulu)</strong></td>


#if ($sumhta == "0")
#set ($sumhta = 0)
#end
#if ($sumhtadulu == "0")
#set ($sumhtadulu = 0)
#end
#set($jumlah_hta = 0)
#set($jumlah_hta = $sumhtadulu)



#if ($sumhtamati == "0")
#set ($sumhtamati = 0)
#end
#if ($sumhtamatidulu == "0")
#set ($sumhtamatidulu = 0)
#end
#set($jumlah_hta_mati = 0)
#set($jumlah_hta_mati = $sumhtamatidulu)


#if ($jumlah_hta == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($jumlah_hta)</td>
#end


#if ($jumlah_hta_mati == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($jumlah_hta_mati)</td>
#end</tr>
<tr class="row2">
<td>2 </td>
<td><strong>Jumlah Nilai Harta Alih (Terdahulu)</strong></td>
#if ($sumjumlah == "0")
#set ($sumjumlah = 0)
#end
#if ($sumjumlahd == "0")
#set ($sumjumlahd = 0)
#end
#set($ha_total = $sumjumlahd)


#if ($sumjumlahmati == "0")
#set ($sumjumlahmati = 0)
#end
#if ($sumjumlahmatid == "0")
#set ($sumjumlahmatid = 0)
#end
#set($ha_total_mati = $sumjumlahmatid)

#if($ha_total == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($ha_total)</td>
#end
#if($ha_total_mati == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($ha_total_mati)</td>
#end</tr>

<tr class="row2">
  <td >3</td>
  <td  align="left"><strong>Jumlah Nilai Harta Tak Alih (Tambahan)</strong></td>
  
  
  
#if ($sumhta == "0")
#set ($sumhta = 0)
#end
#if ($sumhtadulu == "0")
#set ($sumhtadulu = 0)
#end
#set($jumlah_hta_T = 0)
#set($jumlah_hta_T =  $sumhta)



#if ($sumhtamati == "0")
#set ($sumhtamati = 0)
#end
#if ($sumhtamatidulu == "0")
#set ($sumhtamatidulu = 0)
#end
#set($jumlah_hta_mati_T = 0)
#set($jumlah_hta_mati_T =  $sumhtamati)

#if ($jumlah_hta_T == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($jumlah_hta_T)</td>
#end


#if ($jumlah_hta_mati_T == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($jumlah_hta_mati_T)</td>
#end
 
</tr>
<tr class="row2">
  <td >4</td>
    <td  align="left"><strong>Jumlah Nilai Harta Alih (Tambahan)</strong></td>
 
 #if ($sumjumlah == "0")
#set ($sumjumlah = 0)
#end
#if ($sumjumlahd == "0")
#set ($sumjumlahd = 0)
#end
#set($ha_total_T = $sumjumlah)


#if ($sumjumlahmati == "0")
#set ($sumjumlahmati = 0)
#end
#if ($sumjumlahmatid == "0")
#set ($sumjumlahmatid = 0)
#end
#set($ha_total_mati_T =  $sumjumlahmati)

#if($ha_total_T == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($ha_total_T)</td>
#end
#if($ha_total_mati_T == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($ha_total_mati_T)</td>
#end
 
 
</tr>
<tr class="table_header">
<td colspan="2"><b>Jumlah Nilai Harta Keseluruhan</b></td>



#set ($overalljumlah = $ha_total + $jumlah_hta + $ha_total_T + $jumlah_hta_T)
#set ($overalljumlahmati = $ha_total_mati + $jumlah_hta_mati + $ha_total_mati_T + $jumlah_hta_mati_T)


#if ($overalljumlah == 0)
<td colspan="1" align="right"><b>0.00</b></td>
#else
<td colspan="1" align="right"><b>$Util.formatDecimal($overalljumlah)</b></td>
#end


#if ($overalljumlahmati == 0)
<td colspan="1" align="right"><b>0.00</b></td>
#else
<td colspan="1" align="right"><b>$Util.formatDecimal($overalljumlahmati)</b></td>
#end</tr>
<tr>


#if($checkha != "" || $checkhta != "" || $checkhta1 != "" || $checkhta2 != "" || $checkhta3 != "")

<tr>
<td colspan="8">
<fieldset width="60%" >


<table width="60%" border="0" align="right">
 <!-- <tr>
    <td colspan="3"><div align="left">Petunjuk</div></td>
  </tr>
  -->
  <tr>
    <td width="5%" class="bg_papt" >&nbsp;</td>
    <td >:</td>
    <td width="95%" class="style1 style12">Harta yang dipilih untuk pembatalan atau melantik pemegang amanah atau pemegang surat kuasa tadbir</td>
  </tr>
  <tr>
    <td  class="bg_pa" >&nbsp;</td>
    <td>:</td>
    <td><span class="style13">Harta yang <span class="style1 style12">dipilih untuk pembatalan atau melantik</span>  pemegang amanah </span></td>
  </tr>
  <tr>
    <td class="bg_pt" >&nbsp;</td>
    <td>:</td>
    <td><span class="style13">Harta yang <span class="style1 style12">dipilih untuk pembatalan atau melantik</span>   pemegang surat kuasa tadbir</span></td>
  </tr>

</table>
</fieldset>

</td>

</tr>


#end
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
                <input type="button" name="cmdKemaskini1"  id="cmdKemaskini1" value="Kemaskini" onClick="setSelected(3,0,0,0);getNilaiHartaKemaskini()">
                                                #if($flag_kemaskini_selesai != "yes")
                                                <script>
                                                document.getElementById('cmdKemaskini1').style.display = "none";
                                                </script>
                                                #end   
                #end




 
                  #if (($status == 8 || $status == 9 || $status == 170)  && ($jumlah_hta >= 0 || $jumlah_hta_T >= 0) )
                 
                  <input type="button" name="button" id="button" value="Hantar" onClick="setSelected(3,0,0,0);hantar('$listseksyen','$id','$permohonan_mati','$listtarikhMohon','$listidSimati');
                " />
                <!-- hantar_terus(seksyen,idPermohonan,idPermohonanSimati,tarikhMohon,idSimati) -->
                
                #else

                        #if(($jumlah_hta >= 0 || $jumlah_hta_T >= 0))
                         
                         <input type="button" name="button" id="button" value="Seterusnya" onClick="hantar_terus('$listseksyen','$id','$permohonan_mati','$listtarikhMohon','$listidSimati')
                        " />
                        #end
 
 
  				#end
 
  


<!--    
	<input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onkeypress="kembali_simati()" onclick="kembali_simati()" />
    -->
   
   
    #end
    
  
  
     <input type="hidden" name="idPermohonan"/>
  <input type="hidden" name="idpermohonansimati"/>
  <input type="hidden" name="tarikh_mohon" />
  <input type="hidden" name="idSimati"/>
    </p>
    </td>
  </tr>
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
  </table>
          #else
         
         <!--
<font color="#FF0000" size="2">
<i>* Sila cetak Borang P dan Pengesahan Permohonan untuk dibawa ke Unit Pembahagian Pusaka Kecil.
<br>
   * Permohonan akan dibatalkan sekiranya terdapat permohonan lain yang lengkap dihantar ke Unit Pembahagian Pusaka Kecil.    
    </i></font><br>-->

#set ($disabledP = "")
#if ($idStatus == "171")
	#set ($disabledP = "disabled")
#end

#set($disabledDropdown = "disabled")

 #if ($idStatus != "160")
 <fieldset>
 <legend>MAKLUMAT PERMOHONAN</legend>
 
 
#foreach($View in $View_pengesahan_pemohonan_17)
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

 <fieldset><legend>
         PENGESAHAN PERMOHONAN BORANG P</legend>

<table width="100%" border="0">
<tr>
<td width="5%"></td>
<td width="20%" >Negeri</td>
<td width="1%">:</td>
<td width="74%"><strong> #set($nama_negeri="") 
 										#foreach($listneg in $listnegeri) 
                                        #if($negerimhn == $listneg.id_Negeri)                                        
                                        #set($nama_negeri=$listneg.nama_Negeri) 
                                        #end
                                        #end
    <input type="hidden" size="50" maxlength="46" name="namanegeri" value="$!nama_negeri" readonly class="disabled" style="text-transform:uppercase;">
                                         
                                     

    <font  style="text-transform:uppercase;">$!nama_negeri</font>

    <input type="hidden" name="saizdata" value="$!saizData">
</strong></td>
</tr>
<tr>
<td></td>
<td >Daerah</td>
<td>:</td>
<td><strong> #set($nama_daerah="") 
										#foreach($listDaerah in $listdaerah)                                                                              
                                        #if($!daerahmhn == $listDaerah.id)                                        
                                        #set($nama_daerah="$listDaerah.nama")
                                        #end
                                        #end
                          <input type="hidden" size="50" maxlength="46" name="nama_daerah" value="$!nama_daerah" readonly class="disabled" style="text-transform:uppercase;">
                                     
                                        <font  style="text-transform:uppercase;">$!nama_daerah</font>
</strong>
<tr>
<td></td>
<td >Pejabat</td>
<td>:</td>
<td>
  <strong><font  style="text-transform:uppercase;">$!namapejabat</font>
  <input type="hidden" size="50" maxlength="46" name="namapejabat" value="$!namapejabat" readonly class="disabled" style="text-transform:uppercase;">
  </strong></td>
</tr>
<tr>
<td></td>
<td >Alamat</td>
<td>:</td>
<td>
  <strong><font  style="text-transform:uppercase;">$!alamat1</font>
  <input type="hidden" size="50" maxlength="46" name="alamat1" value="$!alamat1" readonly class="disabled" style="text-transform:uppercase;">
  </strong></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td>
  <strong><font  style="text-transform:uppercase;">$!alamat2</font>
  <input type="hidden" size="50" maxlength="46" name="alamat2" value="$!alamat2" readonly class="disabled" style="text-transform:uppercase;">
  </strong></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td>
  <strong><font  style="text-transform:uppercase;">$!alamat3</font>
  <input type="hidden" size="50" maxlength="46" name="alamat3" value="$!alamat3" readonly class="disabled" style="text-transform:uppercase;">
  </strong></td>
</tr>
<tr>
<td></td>
<td >Poskod</td>
<td>:</td>
<td>
  <strong><font  style="text-transform:uppercase;">$!poskod</font>
  <input type="hidden" size="5" maxlength="5" name="poskod" value="$!poskod" readonly class="disabled" >
  </strong></td>
</tr>
<tr>
<td></td>
<td >No. Telefon</td>
<td>:</td>
<td>
  <strong><font  style="text-transform:uppercase;">$!no_tel</font>
  <input type="hidden" size="12" maxlength="11" value="$!no_tel" readonly class="disabled" >
  </strong></td>
</tr>
#if ($no_tel_samb != "")
<tr>
<td></td>
<td >No. Telefon (samb)</td>
<td>:</td>
<td>
  <strong><font  style="text-transform:uppercase;">$!no_tel_samb</font>
  <input type="hidden" size="12" maxlength="11" value="$!no_tel_samb" readonly class="disabled" >
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

</table>



</fieldset>
                 

<p align="center">

#if ($idStatus == "160" || $idStatus == "21" )
<input type="button" name="cmdBorangPDraf" value="Semak Draf Borang P" onClick="javascript:cetakBorangPDraf('$id','$listidFail','$id_Permohonan_terdahulu')">
<input type="button" name="cmdHantar" value="Hantar ke $!namapejabat" onClick="javascript:pengesahan()"><!--<input type="button" name="cmdKosongkan" value="Kosongkan" onClick="PengesahanView('4','0','0','0')">-->
#end 
#if ($idStatus == "171" )
<input type="button" name="cmdBorangP" value="Cetak Borang P" onClick="javascript:cetakBorangP('$id','$listidFail','$id_Permohonan_terdahulu')">

<div   align="left">
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

         
          #end
  </fieldset>
   </td>
  </tr>
  
   <tr>
                <td>
                 #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                <p align="right">CL - 01 - 83</p>
                #end
                </td>
                </tr>  
</table>

      </div>
    </div>
  </div>    
  </td>
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

function setTable(id){
	
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
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

function pengesahan() {
input_box=confirm("Adakah anda pasti?");
		if (input_box == true) 
		{
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "pengesahan";
	document.f1.command.value = "nilai_harta";
	document.f1.submit();
	}
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

function cetakNilaiHarta(noFail) 
{
	
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="view_nilai_harta";		
	document.f1.action="";
	document.f1.printOption.value = "hantar";
	document.f1.submit();
	
  //  var url = "../servlet/ekptg.report.ppk.NilaianHartaPPSPP?nofail="+noFail;
  //var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=NilaianHartaPPSPP&flagReport=S";
  //  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
  //  if ((document.window != null) && (!hWnd.opener))
//	hWnd.opener = document.window;
  //  if (hWnd.focus != null) hWnd.focus();
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
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="kemaskini_nilai_harta";
	document.f1.eventStatus.value="4";
	document.f1.action="";
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

function hantar(seksyen,idPermohonan,idPermohonanSimati,tarikhMohon,idSimati){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="nilai_harta";
		document.f1.mode.value="hantar_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
		
	//	hantar_terus(seksyen,idPermohonan,idPermohonanSimati,tarikhMohon,idSimati)
	}
}

        

function hantar_terus(seksyen,idPermohonan,idPermohonanSimati,tarikhMohon,idSimati)
{
	
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

function kembaliSenaraiFail(noFail) {
	document.f1.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.f1.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.f1.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.f1.method="POST";
	document.f1.submit();
}
function Kembali(){
	document.f1.method="POST";
	document.f1.command.value="xxx";
	document.f1.action = "";
	document.f1.submit();
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


function cetakBorangP(idpermohonanbaru,idfail,idfaillama) {
    var url="../servlet/ekptg.report.ppk.BorangP?idfaillama="+idfaillama+"&idfailbaru="+idfail+"&idpermohonanbaru="+idpermohonanbaru;
    var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
}

function cetakBorangPDraf(idpermohonanbaru,idfail,idfaillama) {
	var url="../servlet/ekptg.report.ppk.BorangPdraf?idfaillama="+idfaillama+"&idfailbaru="+idfail+"&idpermohonanbaru="+idpermohonanbaru;
    var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
}

function cetak(noFail,idhta) 
{
//alert(document.f1.jpphlepas.value)
if(document.f1.jpphlepas.value == "no")
{

 
  var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratIringanJPPH_TM&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
 
 
}

if(document.f1.jpphlepas.value == "yes")
{	
 
    var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratIringanJPPH&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
     
 }   
}

</script>



</body>
</html>
