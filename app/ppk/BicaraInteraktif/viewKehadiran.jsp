
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
		//alert("x");	
	doDivAjaxCall$formname('view_turuthadir','show_turuthadir','ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation());			 	  
	});
</script>
</div>
</fieldset>
</td>
</tr>

<!-- arief add KETIDAKHADIRAN WARIS/OB sewaktu hari Perbicaraan OPEN -->
<tr>
	<td>
	</td>
	<td>
		<fieldset name="listTidakHadir" id="listTidakHadir">
		<legend>Waris / Orang Berkepentingan Tidak Hadir</legend>
		<div id="view_tidakhadir">
 
		<table class="classFade" width="100%" cellspacing="1" cellpadding="3" border="0"> 
		<tbody>
		<tr class="table_header">
		   <td width="5%" valign="top" align="center">Bil.</td>
		   <td valign="top" align="left">Nama</td>
           <td valign="top" align="left">Pengenalan</td>
		   <td valign="top" align="left">Kaitan / Hubungan</td>	
           <td valign="top" align="center">Umur</td>	
           <td valign="top" align="left">Status</td>		  
           <td width="10%" valign="top" align="center">Tindakan</td>
		</tr>
	    
	    <tr id="rowTidakHadir">
			   <!--  <td class="$pr.rowCss" valign="top" align="center">-</td>
               <td class="$pr.rowCss" valign="top" align="left"><input type="text" style="text-transform: uppercase; display: none;" class="fullwidth_input" value="" placeholder="Sila Masukkan Nama..." maxlength="500" id="NAMA_TIDAKHADIR_" name="NAMA_TIDAKHADIR_"></td>
               <td class="$pr.rowCss" valign="top" align="left"><input type="text" style="text-transform: uppercase; display: none;" name="PENGENALAN_TURUTHADIR_" class="fullwidth_input" id="PENGENALAN_TURUTHADIR_" value="" maxlength="20"></td>	
               <td class="$pr.rowCss" valign="top" align="left">
               <datalist id="dataHubungan"><option label="AMANAH RAYA BERHAD" value="AMANAH RAYA BERHAD"></option><option label="ANAK ANGKAT LELAKI" value="ANAK ANGKAT LELAKI"></option><option label="ANAK ANGKAT PEREMPUAN" value="ANAK ANGKAT PEREMPUAN"></option><option label="ANAK LELAKI" value="ANAK LELAKI"></option><option label="ANAK LELAKI BAPA SAUDARA SEBAPA" value="ANAK LELAKI BAPA SAUDARA SEBAPA"></option><option label="ANAK LELAKI BAPA SAUDARA SEIBU SEBAPA" value="ANAK LELAKI BAPA SAUDARA SEIBU SEBAPA"></option><option label="ANAK LELAKI SEBAPA" value="ANAK LELAKI SEBAPA"></option><option label="ANAK LELAKI SEIBU" value="ANAK LELAKI SEIBU"></option><option label="ANAK PEREMPUAN" value="ANAK PEREMPUAN"></option><option label="ANAK PEREMPUAN DARI CUCU LELAKI" value="ANAK PEREMPUAN DARI CUCU LELAKI"></option><option label="ANAK PEREMPUAN SEBAPA" value="ANAK PEREMPUAN SEBAPA"></option><option label="ANAK PEREMPUAN SEIBU" value="ANAK PEREMPUAN SEIBU"></option><option label="ANAK SAUDARA LELAKI SEBAPA" value="ANAK SAUDARA LELAKI SEBAPA"></option><option label="ANAK SAUDARA LELAKI SEIBU SEBAPA" value="ANAK SAUDARA LELAKI SEIBU SEBAPA"></option><option label="ANAK SAUDARA PEREMPUAN SEBAPA" value="ANAK SAUDARA PEREMPUAN SEBAPA"></option><option label="ANAK SAUDARA PEREMPUAN SEIBU SEBAPA" value="ANAK SAUDARA PEREMPUAN SEIBU SEBAPA"></option><option label="ANAK TIRI" value="ANAK TIRI"></option><option label="BAPA" value="BAPA"></option><option label="BAPA SAUDARA SEBAPA" value="BAPA SAUDARA SEBAPA"></option><option label="BAPA SAUDARA SEIBU SEBAPA" value="BAPA SAUDARA SEIBU SEBAPA"></option><option label="BAPA TIRI" value="BAPA TIRI"></option><option label="CUCU LELAKI" value="CUCU LELAKI"></option><option label="CUCU PEREMPUAN DARI ANAK LELAKI" value="CUCU PEREMPUAN DARI ANAK LELAKI"></option><option label="CUCU PEREMPUAN DARI ANAK PEREMPUAN" value="CUCU PEREMPUAN DARI ANAK PEREMPUAN"></option><option label="DATUK" value="DATUK"></option><option label="IBU" value="IBU"></option><option label="IBU TIRI" value="IBU TIRI"></option><option label="ISTERI" value="ISTERI"></option><option label="ISTERI(X)" value="ISTERI(X)"></option><option label="MENANTU" value="MENANTU"></option><option label="NENEK PEREMPUAN SEBELAH BAPA (HINGGA ATAS)" value="NENEK PEREMPUAN SEBELAH BAPA (HINGGA ATAS)"></option><option label="NENEK PEREMPUAN SEBELAH IBU (HINGGA ATAS)" value="NENEK PEREMPUAN SEBELAH IBU (HINGGA ATAS)"></option><option label="PEMBELI" value="PEMBELI"></option><option label="PEMBELI" value="PEMBELI"></option><option label="PETUAN HAMBA SAHAYA" value="PETUAN HAMBA SAHAYA"></option><option label="PETUAN PEREMPUAN HAMBA SAHAYA" value="PETUAN PEREMPUAN HAMBA SAHAYA"></option><option label="SAUDARA LELAKI SEBAPA" value="SAUDARA LELAKI SEBAPA"></option><option label="SAUDARA LELAKI SEIBU" value="SAUDARA LELAKI SEIBU"></option><option label="SAUDARA LELAKI SEIBU SEBAPA" value="SAUDARA LELAKI SEIBU SEBAPA"></option><option label="SAUDARA PEREMPUAN SEBAPA" value="SAUDARA PEREMPUAN SEBAPA"></option><option label="SAUDARA PEREMPUAN SEIBU" value="SAUDARA PEREMPUAN SEIBU"></option><option label="SAUDARA PEREMPUAN SEIBU SEBAPA" value="SAUDARA PEREMPUAN SEIBU SEBAPA"></option><option label="SUAMI" value="SUAMI"></option><option label="TIADA MAKLUMAT" value="TIADA MAKLUMAT"></option></datalist>
               <input type="text" style="text-transform: uppercase; display: none;" name="HUBUNGAN_TURUTHADIR_" class="fullwidth_input" id="HUBUNGAN_TURUTHADIR_" value="" maxlength="100" list="dataHubungan"></td>
               <td class="$pr.rowCss" valign="top" align="center"><input type="text" style="text-transform: uppercase; display: none;" name="UMUR_TURUTHADIR_" class="fullwidth_input" id="UMUR_TURUTHADIR_" value="" maxlength="3" onkeydown="validateNumber(event);"></td>
               <td class="$pr.rowCss" valign="top" align="left">
               <datalist id="dataStatusOB"><option label="DEWASA / WARAS" value="DEWASA / WARAS"></option><option label="TIDAK SEMPURNA AKAL" value="TIDAK SEMPURNA AKAL"></option></datalist>
               <input type="text" style="text-transform: uppercase; display: none;" name="STATUS_TURUTHADIR_" class="fullwidth_input" id="STATUS_TURUTHADIR_" value="" maxlength="100" list="dataStatusOB"></td>	
               <td class="$pr.rowCss" valign="top" align="center">
               <input type="button" id="cmdAddTurutHadir" name="cmdAddTurutHadir" value="Tambah" onclick="simpanTurutHadir('','99191158219','99191200658',getPageLocation(),'','')" style="display: none;">               
               </td>	-->
		</tr>		
	</tbody></table>


</div>
</fieldset>
</td>
</tr>
<!-- arief add KETIDAKHADIRAN WARIS/OB sewaktu hari Perbicaraan CLOSE -->

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