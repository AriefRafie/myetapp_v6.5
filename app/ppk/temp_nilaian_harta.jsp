  <table width="100%" border="0">
   <tr>
    <td>
        <fieldset >
<table width="100%" border="0" >
  <tr>
    <td>
    
<fieldset>
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
	
	<td align="right"><input name="txtHtaNilaiTarikhMohon" type="text" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon');samakan($cntX)" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15"></td>
    
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
	
	<td align="right"><input name="txtHtaNilaiTarikhMohon" type="text" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon');samakan($cntX)" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15"></td>
    
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
	
	<td align="right"><input name="txtHtaNilaiTarikhMohon" type="text" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon');samakan($cntX)" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15"></td>
    
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
	
	<td align="right"><input name="txtHtaNilaiTarikhMohon" type="text" id="txtHtaNilaiTarikhMohon"  onblur="validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon');validateModal(this,this.value,'$listhath.nilai_tarikhmohon');samakan($cntX)" onkeyup="javascript:validateIC(event,this,this.value,'txtHtaNilaiTarikhMohon')" value="$listhath.nilai_tarikhmohon" size="15" maxlength="15"></td>
    
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
<tr >
#end
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

<td align="right"><input name="txtHaNilaiTarikhMohon" type="text" id="txtHaNilaiTarikhMohon" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMohon');validateModal(this,this.value,'$nilai_tarikhmohon');samakan1($cnt)" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMohon')" value="$nilai_tarikhmohon" size="15" maxlength="15" ></td>
<td align="right"><input name="txtHaNilaiTarikhMati" type="text" id="txtHaNilaiTarikhMati" style=" text-align: right;" onblur="validateIC(event,this,this.value,'txtHaNilaiTarikhMati');validateModal(this,this.value,'$nilai_tarikhmati');" onkeyup="javascript:validateIC(event,this,this.value,'txtHaNilaiTarikhMati')" value="$nilai_tarikhmati" size="15" maxlength="15" ></td>
#else

<td align="right">
#if($nilai_tarikhmohon>0)
$Util.formatDecimal($nilai_tarikhmohon)
#elseif($nilai_tarikhmohon == 0)
0.00
#else
$nilai_tarikhmohon
#end</td>

<td align="right">

#if($nilai_tarikhmati>0)
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


<br>

<fieldset><legend>NILAI HARTA KESELURUHAN</legend>


<table width="100%">
<tr class="table_header">
<td width="2%">Bil</td>
<td width="74%">Perkara</td>
<td width="12%"><div align="center">Jumlah Nilai Harta Tarikh Mohon (RM)</div></td>
<td width="12%"><div align="center">Jumlah Nilai Harta Tarikh Mati (RM)</div></td>
</tr>
<tr class="row2">
<td>1 </td>
<td>
<strong>Jumlah Nilai Harta Tak Alih</strong></td>

#if ($sumhta == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($sumhta)</td>
#end


#if ($sumhta == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($sumhtamati)</td>
#end



</tr>
<tr class="row2">
<td>2 </td>
<td><strong>Jumlah Nilai Harta Alih</strong></td>


#if($sumjumlah == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($sumjumlah)</td>
#end


#if($sumjumlah == 0)
<td align="right">0.00</td>
#else
<td align="right">$Util.formatDecimal($sumjumlahmati)</td>
#end


</tr>

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
#end

</tr>
<tr>
<tr>
<td colspan="8">&nbsp;</td>
</tr>
</table>
</fieldset>

<p align="center">




#if($boleh_kemaskini == "yes")
#if ($EventStatus == 4)
<input type="button" name="cmdSimpan" value="Simpan" onClick="setSelected(3,0,0,0);getNilaiHartaSimpan()">
<input type="button" name="cmdBatal" value="Batal" onClick="setSelected(3,0,0,0);getNilaiHartaKemaskiniBatal()">
#else
	

#if (($jumppkhta.size()>0 || $listHa.size()>0))


<input type="button" name="cmdkemasini" value="Kemaskini" onClick="setSelected(3,0,0,0);getNilaiHartaKemaskini()">

#end




 
  #if (($status == 8 || $status == 9 || $status == 170)  && $jumppkhta.size()>0 && $sumhta >= 0)
  <input type="button" name="button" id="button" value="Hantar" onClick="setSelected(3,0,0,0);hantar('$listseksyen','$id','$permohonan_mati','$listtarikhMohon','$listidSimati');" />
  #else
 #if ($jumppkhta.size()>0 && $sumhta >= 0)
<input type="button" name="button" id="button" value="Seterusnya" onClick="hantar_terus('$listseksyen','$id','$permohonan_mati','$listtarikhMohon','$listidSimati')
" />
#end
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
    </p>
    </td>
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