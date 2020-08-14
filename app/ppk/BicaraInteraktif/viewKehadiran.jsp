
#if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
#end


<table width="100%" align="center" border="0" cellspacing="1" cellpadding="3" style="margin-bottom:5px" class="classFade box_shadow">
<tr  >
<td width="2%" >
</td>
<td width="98%" >
<a class="blue" href="javascript:doDivAjaxCall$formname('view_kehadiran','refresh_kehadiran','ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PEMOHON=$ID_PEMOHON');">&nbsp;<u>'Refresh'</u></a>	   
			
<fieldset>
<legend>Senarai Orang Berkepentingan <input type="button" id="cmdSimpanKehadiran" name="cmdSimpanKehadiran" value="Simpan Kehadiran" onClick="doDivAjaxCall$formname('view_kehadiran','simpan_kehadiran','ID_PEMOHON=$ID_PEMOHON&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
</legend>
<div><i><font color='blue'>Info</font> : Sila tanda <font color='blue'>' / '</font> pada kehadiran dan tekan butang <font color='blue'>'Simpan Kehadiran'</font> terlebih dahulu sebelum memasukkan keterangan waris. Ikon keterangan <img title="Keterangan" src="../img/write.gif" width="15" border="0"> akan dipaparkan.</i></div>
<div><i><font color='blue'>Info</font> : Ikon <img title="Keterangan" width="15" src="../img/write.gif" #if($pr.KETERANGAN != "") class="blink" #end border="0"> berkelip adalah menunjukan bahawa ada keterangan yang telah dimasukkan.</i></div>
<div><i><font color='blue'>Info</font> : Skrin ini menyediakan fungsi <font color="green"><b>AUTO SAVE</b></font> selepas 10 saat editor didalam keadaan 'idle' atau menekan 'Tab' untuk ke bahagian lain.</font></i></div>

 

	<table border="0" cellspacing="1" cellpadding="3" width="100%" > 
	#if($listKehadiran.size()>0)
    <!--
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/ppk/BicaraInteraktif/recordPageList.jsp")
		   </td>		     
	</tr>
    -->
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top" width="5%">Bil.</td>
		   <td   align="left" valign="top" width="30%" >Nama</td>
           <td   align="left" valign="top" >Pengenalan</td>
		   <td   align="left" valign="top" >Kaitan / Hubungan</td>	
           <td   align="center" valign="top" >Umur</td>	
           <td   align="left" valign="top" >Status</td>		  
           <td   align="center" valign="top" width="10%">Kehadiran<br />
           <input type="checkbox" id="checkAllkehadiran" name="checkAllkehadiran" title="Pilih Semua" onclick="doCheckAllKehadiran('checkAllkehadiran','checkKehadiran');"/>
           </td>
	</tr>
	#if($listKehadiran.size()>0)	
		#foreach($pr in $listKehadiran)
		<tr  >
			   <td class="$pr.rowCss"  align="center" valign="top" >$pr.BIL </td>
               <td class="$pr.rowCss"  align="left" valign="top" >
              
              
    		   #set($typeKehadiran = "hidden")
               #if($pr.JENIS_OB == "1" && $pr.ID_BIKEHADIRAN != "")
               #set($typeKehadiran = "text")
               #end
               
               #set($nama_hadir = $pr.NAMA)
               #if($pr.ID_BIKEHADIRAN != "")
               #set($nama_hadir = $pr.NAMA_HADIR)
               #end
               
               
              
               
               <input type="$typeKehadiran" style="text-transform:uppercase" name="NAMA_$pr.ID_OBPERMOHONAN" id="NAMA_$pr.ID_OBPERMOHONAN" class="fullwidth_input" value = "$nama_hadir" />          
	           
               #if($typeKehadiran == "text")
               <span class="showWakil" style="display:none" >$nama_hadir</span>  
               #end 
                  
               #if($pr.JENIS_OB == "1" && $pr.NAMA != $pr.NAMA_HADIR && $pr.ID_BIKEHADIRAN != "")
              
               <br />
               wakil kepada
             
               #end
               
               <!--  $pr.UMUR_INT != 0 && $pr.UMUR_INT < 18 -->
               #if( $pr.STATUS_OB == "2" ||  $pr.STATUS_OB == "4")
               
               
               #if($pr.PENJAGA != "") 
               $pr.PENJAGA 
               <br />
               sebagai <b>PENJAGA</b> kepada
               <br />               
               #else
               <i><span class="red">Perhatian </span>: Sila lantik penjaga pada skrin 
                <a href="javascript:papar_header('$mainID.ID_PERMOHONAN','18','','$mainID.ID_PERMOHONANSIMATI','$mainID.TARIKH_MOHON','','$mainID.SEKSYEN','$mainID.ID_SIMATI')" > <span class="blue"><u>'Notis Perbicaraan'</u></span></a>
               </i> 
               <br />
               #end
               
               <span class="blue">$pr.NAMA</span> 
               
               #else
               $pr.NAMA 
               #end
               
               #if($pr.ID_PEMOHON != "" && $pr.ID_PEMOHON != "-")
               <span class="blue"> (Pemohon)</span> 
               #if($pr.KETERANGAN == "")
               <br /><br />
               <div id="disInfoSHT$pr.ID_OBPERMOHONAN" ><i><span class="blue blink">Info</span> : Sebelum memasukkan keterangan pemohon, pastikan semak terlebih dahulu jika ada harta tertingal. Sekiranya ada, sila tambah harta tersebut pada tab harta didalam skrin ini. Harta-harta yang dimasukan akan dipaparkan secara 'default' didalam keterangan pemohon</i></div>
               <script>
			   checkHartaTertingal('disInfoSHT$pr.ID_OBPERMOHONAN');
               </script>
               #end
               
               #end 
               
              
               </td>
               
              
               
               
               <td class="$pr.rowCss"  align="left" valign="top" >
               #set($pengenalan_hadir = $pr.PENGENALAN)
               #if($pr.ID_BIKEHADIRAN != "")
               #set($pengenalan_hadir = $pr.PENGENALAN_HADIR)
               #end
               <input type="$typeKehadiran" style="text-transform:uppercase" class="fullwidth_input" name="PENGENALAN_$pr.ID_OBPERMOHONAN" id="PENGENALAN_$pr.ID_OBPERMOHONAN" value = "$pengenalan_hadir" />
               #if($typeKehadiran == "text")
               <span class="showWakil" style="display:none" >$pengenalan_hadir</span>  
               #end 
               $pr.PENGENALAN
               </td>
               <td class="$pr.rowCss"  align="left" valign="top" >
               $dataHubungan               
               
               
               #set($hubungan_hadir = $pr.HUBUNGAN)
               #if($pr.ID_BIKEHADIRAN != "")
               #set($hubungan_hadir = $pr.HUBUNGAN_HADIR)
               #end
               <input type="$typeKehadiran" class="fullwidth_input" style="text-transform:uppercase" name="HUBUNGAN_$pr.ID_OBPERMOHONAN" id="HUBUNGAN_$pr.ID_OBPERMOHONAN" value = "$hubungan_hadir"   maxlength="100" list="dataHubungan"/>
               #if($typeKehadiran == "text")
               <span class="showWakil" style="display:none" >$hubungan_hadir</span>  
               #end 
               $pr.HUBUNGAN
               </td>	
               <td class="$pr.rowCss"  align="center" valign="top" >
               #set($umur_hadir = $pr.UMUR)
               #if($pr.ID_BIKEHADIRAN != "")
               #set($umur_hadir = $pr.UMUR_HADIR)
               #end
               <input type="$typeKehadiran"  class="fullwidth_input" name="UMUR_$pr.ID_OBPERMOHONAN" id="UMUR_$pr.ID_OBPERMOHONAN" maxlength="3" onkeydown="validateNumber(event);" value = "$umur_hadir" />
               #if($typeKehadiran == "text")
               <span class="showWakil" style="display:none" >$umur_hadir</span>  
               #end 
               $pr.UMUR
               </td>
               <td class="$pr.rowCss"  align="left" valign="top" >
               $dataStatusOB
               #set($status_hadir = $pr.STATUS)
               #if($pr.ID_BIKEHADIRAN != "")
               #set($status_hadir = $pr.STATUS_HADIR)
               #end
               <input type="$typeKehadiran" class="fullwidth_input" name="STATUS_$pr.ID_OBPERMOHONAN" style="text-transform:uppercase" id="STATUS_$pr.ID_OBPERMOHONAN" value = "$status_hadir"  maxlength="100" list="dataStatusOB"  />
               #if($typeKehadiran == "text")
               <span class="showWakil" style="display:none" >$status_hadir</span>  
               #end 
               $pr.STATUS
               </td>	
               <td class="$pr.rowCss"  align="center" valign="top" ><!--$pr.ID_BIKEHADIRAN-->
               <input type="checkbox" id="checkKehadiran" name="checkKehadiran" #if($pr.ID_BIKEHADIRAN != '')checked#end value="$pr.ID_OBPERMOHONAN" onclick="doCheckKehadiran('checkKehadiran', 'checkAllkehadiran');"  />
               <span id="spanIconKeterangan$pr.ID_OBPERMOHONAN" style="display:none">
               <a href="javascript:doDivAjaxCall$formname('tdViewKeterangan$pr.ID_OBPERMOHONAN','showKeterangan','ID_PEMOHON=$pr.ID_PEMOHON&ID_OBPERMOHONAN=$pr.ID_OBPERMOHONAN&ID_OBPERMOHONAN_ASAL=$pr.ID_OBPERMOHONAN_ASAL&ID_BIKEHADIRAN=$pr.ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation()+'&div=tdViewKeterangan$pr.ID_OBPERMOHONAN')">
               
               <img title="Keterangan" src="../img/write.gif" #if($pr.KETERANGAN != "") class="blink" #end border="0">
               
               </a>	   
               </span>
				
                <script type="text/javascript">
				if('$pr.ID_BIKEHADIRAN' != "")
				{
					document.getElementById('spanIconKeterangan$pr.ID_OBPERMOHONAN').style.display = "";
				}				
				</script>
                	
               </td>	
		</tr>
        <tr>
        <td colspan="8" valign="top" id="tdViewKeterangan$pr.ID_OBPERMOHONAN">
        <textarea style="display:none"  id="KETERANGAN_$pr.ID_OBPERMOHONAN" name="KETERANGAN_$pr.ID_OBPERMOHONAN">$pr.KETERANGAN</textarea>
        <textarea style="display:none"  id="NOTA_PEGAWAI_$pr.ID_OBPERMOHONAN" name="NOTA_PEGAWAI_$pr.ID_OBPERMOHONAN">$pr.NOTA_PEGAWAI</textarea>
        </td>
        </tr>		
		#end
        
	#else
	<tr >
		   <td  align="left" valign="top" colspan="10" >Tiada Rekod</td>		     
	</tr>
	#end
	</table>
</fieldset>

</td>
</tr>
<tr  >
<td >
</td>
<td >
<fieldset>
<legend>Turut Hadir</legend>
<div id="view_turuthadir">

<script> 
	$jquery(document).ready(function () {
		//alert("turuthadir");	
	doDivAjaxCall$formname('view_turuthadir','show_turuthadir','ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation());			 	  
	});
</script>
</div>
</fieldset>
</td>
</tr>

<!-- arief add Saksi OPEN-->
<!----><tr  >
<td >
</td>
<td >
<fieldset>
<legend>Saksi</legend>
<div id="view_saksi">

  <script> 
	$jquery(document).ready(function () {
		//alert("saksi");	
	doDivAjaxCall$formname('view_saksi','show_saksi','ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation());			 	  
	});
</script>
</div>
</fieldset>
</td>
</tr>
<!-- arief add Saksi CLOSE-->

<!-- arief add Tidak Hadir OPEN-->
<tr  >
<td >
</td>
<td >
<fieldset>
<legend>Waris / Orang Berkepentingan Tidak Hadir</legend>
<div id="view_tidakhadir">

  <script> 
	$jquery(document).ready(function () {
		//alert("tidakhadir");	
	doDivAjaxCall$formname('view_tidakhadir','show_tidakhadir','ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation());			 	  
	});
</script>
</div>
</fieldset>
</td>
</tr>
<!-- arief add Tidak Hadir CLOSE-->

</table>

<script>
var flagDisable = document.getElementById("flagDisable").value;

//alert("flagDisable : "+flagDisable);

if(flagDisable == "Y")
{
	disableInput("view_kehadiran");
	showInfoWakil("view_kehadiran")
}
</script>

#if($div != "")
 <script>
 $jquery(document).ready(function () {
     //alert('x');
     //divToTop("view_keputusan");
     $jquery('#'+'$div').scrollView();
     //alert('x2');
 });	 
 </script>    
#end