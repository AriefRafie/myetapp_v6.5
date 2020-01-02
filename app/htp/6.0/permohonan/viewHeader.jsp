<!--
:::::::: $viewPerbicaraan
-->

<!-- sebab kena folow cara coding lama bukak fail.. sebab tu ada field lain2 tp merujuk pada value yg sama  -->
<!-- tumpang javacript lama bukak fail dari mouse over no fail -->
<input type="hidden" name="idpermohonan" id="idpermohonan" value=""/>
<input type="hidden" name="idPermohonan" id="idPermohonan" value=""/>
<input type="hidden" name="idpermohonansimati" id="idpermohonansimati" value=""/>
<input type="hidden" name="idPermohonanSimati" id="idPermohonanSimati" value=""/>
<input type="hidden" name="tarikh_mohon" id="tarikh_mohon" value=""/>
<input type="hidden" name="idSimati" id="idSimati" value=""/>
<input type="hidden" name="id_Simati" id="id_Simati" value=""/>
<input type="hidden" name="id_permohonan" id="id_permohonan" value=""/>
<input type="hidden" name="id_status" id="id_status" value=""/>
<input type="hidden" name="idStatus" id="idStatus" value=""/>
<!---- ------------------------------------------------------------------------------------>


<div class="viewMaklumatTR" >


<table width="100%" align="center" border="0" cellpadding="2" cellspacing="2" style="margin-bottom:5px">
<tr  >
<td width="2%" >
</td>
<td width="98%" >
<fieldset>
<table width="100%" align="center" border="0" cellspacing="1" cellpadding="2" class="HeaderBI" >
<tr>
<td width="50%" valign="top">
<table width="100%" align="center" border="0" cellspacing="1" cellpadding="2" class="HeaderBI" >
<tr>
<td width="1%"></td><td width="28%"></td><td width="1%"></td><td width="70%"></td>
</tr>
<tr>
<td valign="top" ></td><td valign="top">No. Fail.</td><td valign="top">:</td><td valign="top" class="HeaderFont">
<input type="hidden" id="id_permohonan_headerppk" name="id_permohonan_headerppk" value="$viewPerbicaraan.ID_PERMOHONAN"  />
<input type="hidden" id="id_permohonansimati_headerppk" name="id_permohonansimati_headerppk" value="$viewPerbicaraan.ID_PERMOHONANSIMATI"  />
<input type="hidden" id="seksyen_headerppk" name="seksyen_headerppk" value="$viewPerbicaraan.SEKSYEN"  />


<!--$viewPerbicaraan.NO_FAIL-->
<ul id="sddmheader"  >
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()"><font color="blue">$!viewPerbicaraan.NO_FAIL</font>        
        </a> 
        <div id="m1" 
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()" >           
           #foreach ($list in $listKronologiStatus)
          <a href="javascript:papar_header('$list.idPermohonan','$list.idStatus','$list.bil','$list.idPermohonanSimati','$list.tarikhMohon','$list.flagjenisfail','$list.seksyen','$list.idSimati')" >$list.keterangan</a>
           #end
            
        </div>
    </li>
   </ul>
   <div style="clear:both"></div>
   
   <ul id="bulletAmaran$viewPerbicaraan.ID_PERBICARAAN">   
    #set($adaMasalah = "")
    #if($viewPerbicaraan.TOTAL_PERINTAH > 1)
    #set($adaMasalah = "Y")    
               <li style="color:red" ><div><font color="red" ><strong class="blink">PERHATIAN!</strong> DIDAPATI DATA PERINTAH PADA PERBICARAAN INI BERTINDIH.</font></div></li>
    #end
    #if(($viewPerbicaraan.BIL_BICARA < $viewPerbicaraan.MAX_BIL_BICARA)  
    && $viewPerbicaraan.FLAG_JENIS_KEPUTUSAN != "0" && $viewPerbicaraan.FLAG_JENIS_KEPUTUSAN != "1" && $viewPerbicaraan.FLAG_JENIS_KEPUTUSAN != "2"
    )
    #set($adaMasalah = "Y")    
               <li style="color:red" ><div><font color="red" ><strong class="blink">PERHATIAN!</strong> DIDAPATI TIADA KEPUTUSAN '<strong>TANGGUH</strong>' PADA PERBICARAAN BILANGAN $viewPerbicaraan.BIL_BICARA INI, SEDANGKAN PERBICARAAN DISETERUSNYA TELAH DIDAFTARKAN.</font></div></li>
    #end
    
    #if($adaMasalah == "Y")    
         <li  style="color:red" ><div><font color="red" class="">UNTUK PEMBETULAN, SILA HUBUNGI BPICT/ETAPPSUPPORT!</font></div></li>
        <script>document.getElementById('bulletAmaran$viewPerbicaraan.ID_PERBICARAAN').style.display = "";</script>
    #else    
        <script>document.getElementById('bulletAmaran$viewPerbicaraan.ID_PERBICARAAN').style.display = "none";</script>
    #end  
    
  
    </ul>    
    

    
      </td>
</tr>
<tr>
<td valign="top" ></td><td valign="top">Status Permohonan</td><td valign="top">:</td><td valign="top" class="HeaderFont">$viewPerbicaraan.STATUS_PERMOHONAN
<input type="hidden" name="ID_STATUSPERMOHONAN" id="ID_STATUSPERMOHONAN" value="$viewPerbicaraan.ID_STATUS"  />

<!--
SEMENTARA
$viewPerbicaraan.ID_STATUS == "21"
||
-->

#set($flagFailSelesai = "")
#if($viewPerbicaraan.ID_STATUS == "21"
|| $viewPerbicaraan.ID_STATUS == "64"
|| $viewPerbicaraan.ID_STATUS == "163" 
|| $viewPerbicaraan.ID_STATUS == "166"
|| $viewPerbicaraan.ID_STATUS == "167" 
|| $viewPerbicaraan.ID_STATUS == "180"
|| $viewPerbicaraan.ID_STATUS == "164"
|| $viewPerbicaraan.ID_STATUS == "165"
|| $viewPerbicaraan.ID_STATUS == "999")
	#set($flagFailSelesai = "Y")
#end
<!-- SEMENTARA -->


<input type="hidden" name="FLAG_FAIL_SELESAI" id="FLAG_FAIL_SELESAI" value="$flagFailSelesai"  />
<input type="hidden" name="id_status_headerppk" id="id_status_headerppk" value="$viewPerbicaraan.ID_STATUS"  />

</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top">Nama Simati</td><td valign="top">:</td><td valign="top" class="HeaderFont">$viewPerbicaraan.NAMA_SIMATI</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top">Nama Pemohon</td><td valign="top">:</td><td valign="top" class="HeaderFont">$viewPerbicaraan.NAMA_PEMOHON</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top">Waktu Bicara</td><td valign="top">:</td><td valign="top" class="HeaderFont">$viewPerbicaraan.TARIKH_BICARA $viewPerbicaraan.MASA_BICARA</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top">Bil. Bicara</td><td valign="top">:</td><td valign="top" class="HeaderFont">$viewPerbicaraan.BIL_BICARA
<!--
#if($viewPerbicaraan.MAX_BIL_BICARA != "")
 (Terkini : $viewPerbicaraan.MAX_BIL_BICARA)
#end
-->
</td>
</tr>


</table>
</td>
<td width="50%" valign="top">
<table width="100%" align="center" border="0" cellspacing="1" cellpadding="2" class="HeaderBI" >
<tr>
<td width="1%"></td><td width="28%"></td><td width="1%"></td><td width="70%"></td>
</tr>
#if($viewPerbicaraan.SEKSYEN == "17")
<tr>
<td valign="top" ></td><td valign="top">Tujuan Permohonan</td><td valign="top">:</td><td valign="top" class="HeaderFont">

#if($viewPerbicaraan.HARTA_TERTINGGAL == "Y")
HARTA TERTINGGAL DI PERMOHONAN AWAL<br />
#end
#if($viewPerbicaraan.LANTIK_PA  == "Y")
LANTIK PEMEGANG AMANAH<br />
#end
#if($viewPerbicaraan.BATAL_PA  == "Y")
BATAL PEMEGANG AMANAH<br />
#end
#if($viewPerbicaraan.LANTIK_KT  == "Y")
LANTIK PEMEGANG SURAT KUASA TADBIR<br />
#end
#if($viewPerbicaraan.BATAL_KT  == "Y")
BATAL PEMEGANG SURAT KUASA TADBIR<br />
#end
#if($viewPerbicaraan.LAIN_TUJUAN  == "Y")
LAIN-LAIN TUJUAN : $viewPerbicaraan.CATATAN_LAIN_TUJUAN<br />
#end

</td>
</tr>
#end
<tr>
<td valign="top" ></td><td valign="top">Peg. Pengendali</td><td valign="top">:</td><td valign="top" class="HeaderFont">$viewPerbicaraan.PEG_PENGENDALI


#set($openFormTukarPegawai="")
#if($viewPerbicaraan.FLAG_JENIS_KEPUTUSAN != "" || $flagFailSelesai == "Y")
	#set($openFormTukarPegawai="N")    
#end
<br />
<!--
::::::: $viewPerbicaraan
-->
<a class="blue" href="javascript:doDivAjaxCall$formname('view_tukarpegawai','openSkrinTukarPegawai','openFormTukarPegawai=$openFormTukarPegawai&div=view_tukarpegawai&ID_PERMOHONAN=$viewPerbicaraan.ID_PERMOHONAN&FIELD_PK=ID_TUKARPEGAWAI&NAMA_TABLE=TBLPPKTUKARPEGAWAI&ID_FAIL=$viewPerbicaraan.ID_FAIL&ID_SIMATI=$viewPerbicaraan.ID_SIMATI&ID_PERMOHONANSIMATI=$viewPerbicaraan.ID_PERMOHONANSIMATI&ID_PERBICARAAN=$viewPerbicaraan.ID_PERBICARAAN&skrinName=tukarpegawai');"><u><b>>> Skrin Tukar Pegawai</b></u></a>

<div style="margin:5px"><i><font color='blue' class="blink">Info</font> <font color='black'>: Kemudahan untuk menukar pegawai bicara sebelum atau sedang perbicaraan berlangsung.</font></i></div>




<!--
<input type="button" id="cmdTukarPegawai" name="cmdTukarPegawai" value="Skrin Tukar Pegawai" onClick="doDivAjaxCall$formname('divTukarPegawai','openSkrinTukarPegawai','ID_PERMOHONAN=$viewPerbicaraan.ID_PERMOHONAN&ID_PERMOHONANSIMATI=$viewPerbicaraan.ID_PERMOHONANSIMATI&ID_PERBICARAAN=$viewPerbicaraan.ID_PERBICARAAN')" >
-->
</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top">Keputusan Perbicaraan</td><td valign="top">:</td><td valign="top" class="HeaderFont">
<input type="hidden" name="jenis_keputusan" id="jenis_keputusan" value="$viewPerbicaraan.FLAG_JENIS_KEPUTUSAN"  />
#set($flagDisable = "")
#if($viewPerbicaraan.FLAG_JENIS_KEPUTUSAN!="" 
|| $viewPerbicaraan.ID_STATUS != "18" 
|| $adaMasalah == "Y" || $viewPerbicaraan.LEPASTARIKHHARINI == "Y" || $viewPerbicaraan.JAGAAN_MATCH == 0)
	#set($flagDisable = "Y")
#else    
	#if($viewPerbicaraan.PEGAWAIBICARAASLOGIN != "Y") 
    	#set($flagDisable = "Y")
    #end
#end

<!--  SEMENTARA -->

<input type="hidden" name="PEGAWAIBICARAASLOGIN" id="PEGAWAIBICARAASLOGIN" value="$viewPerbicaraan.PEGAWAIBICARAASLOGIN"  />


<input type="hidden" name="flagDisable" id="flagDisable" value="$flagDisable"  />
<input type="hidden" name="adaMasalah" id="adaMasalah" value="$adaMasalah"  />


$viewPerbicaraan.KETERANGAN_KEPUTUSAN
#if($viewPerbicaraan.KETERANGAN_TANGGUH != "")
- $viewPerbicaraan.KETERANGAN_TANGGUH
#elseif($viewPerbicaraan.KETERANGAN_BATAL != "")
- $viewPerbicaraan.KETERANGAN_BATAL
#end

</td>
</tr>

#if($viewPerbicaraan.MAX_BIL_BICARA != "")
<tr>
<td valign="top" ></td><td valign="top">Bil. Bicara Terkini</td><td valign="top">:</td><td valign="top" class="HeaderFont">$viewPerbicaraan.MAX_BIL_BICARA</td>
</tr>
#end


#if($listPerbicaraanLain.size() > 1)
<tr>
<td valign="top" ></td><td valign="top">Semak Perbicaraan Lain <br />(Bil. Bicara)</td><td valign="top">:</td><td valign="top" class="HeaderFont">

<select id="selectBicaraLain" name="selectBicaraLain" onchange="selectBicalaLain(this.value);" >
<option value="">SILA PILIH</option>
#foreach($pr in $listPerbicaraanLain)
    #if($viewPerbicaraan.ID_PERBICARAAN != $pr.ID_PERBICARAAN)
    <option value="$pr.ID_PERBICARAAN">$pr.BIL_BICARA</option>
    #end
#end
</select>

</td>
</tr>
#end

<tr style="display:none">
<td valign="top" ></td><td valign="top">Bantahan?</td><td valign="top">:</td><td valign="top" class="HeaderFont">
#if($flagFailSelesai == "" && $flagDisable == "")
<input type="checkbox" name="FLAG_BANTAHAN" id="FLAG_BANTAHAN" value="Y" #if($viewPerbicaraan.FLAG_BANTAHAN == "Y") checked #end onclick="saveBantahan('spanFLAG_BANTAHAN',this,'$viewPerbicaraan.ID_PERBICARAAN');"  />
#else
    #if($viewPerbicaraan.FLAG_BANTAHAN == "Y")
    ADA
    #else
    TIADA
    #end
<input type="hidden" name="FLAG_BANTAHAN" id="FLAG_BANTAHAN" value="$viewPerbicaraan.FLAG_BANTAHAN"   />
#end
<div id="spanFLAG_BANTAHAN"></div>
</td>
</tr>
</table>
</td>
</tr>
</table>


</fieldset>


    
   
#if($viewPerbicaraan.JAGAAN_MATCH == 0)
<div ><font color="red" ><strong class="blink">PERHATIAN!</strong> DIDAPATI DAERAH MOHON UNTUK PERMOHONAN INI BUKAN DIBAWAH DAERAH JAGAAN UNIT ANDA, SILA MEMDAFTAR PERMOHONAN MEMBANTU UPP <a class="red" href="javascript:goToBU()"><u> <b>DISINI</b></u></a></font></div>
#end

#if($viewPerbicaraan.LEPASTARIKHHARINI == "Y")
<div ><font color="red" ><strong class="blink">PERHATIAN!</strong> MAKLUMAT PERBICARAAN HANYA BOLEH DIKEMASKINI PADA HARI PERBICARAAN DAN KEATAS SAHAJA</font></div>
#end

#if($viewPerbicaraan.PEGAWAIBICARAASLOGIN != "Y")
<div ><font color="red" ><strong class="blink">PERHATIAN!</strong> HANYA PEGAWAI BICARA YANG DITUGASKAN BOLEH MENGEMASKINI MAKLUMAT</font></div>
#end
 
<input type="hidden" name="JAGAAN_MATCH" id="JAGAAN_MATCH" value="$viewPerbicaraan.JAGAAN_MATCH"  />
<input type="hidden" name="LEPASTARIKHHARINI" id="LEPASTARIKHHARINI" value="$viewPerbicaraan.LEPASTARIKHHARINI"  />
   
 

#if($viewPerbicaraan.FLAG_JENIS_KEPUTUSAN!="")
<div><i><font color='blue'>Info</font> : Maklumat perbicaraan bagi keputusan perbicaraan berstatus <font color='blue'>SELESAI, TANGGUH</font> atau <font color='red'>BATAL</font> tidak dibenarkan untuk dikemaskini, dihapus atau ditambah.</i></div>
#end

</td>
</tr>
</table>
</div>

<script>
var flagDisable = document.getElementById("flagDisable").value;
if(flagDisable == "Y")
{
	//offIconByClass("buttonHapus");
	disableInput("viewHeader");
}
</script>