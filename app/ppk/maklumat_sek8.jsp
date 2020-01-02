#set($flag_kemaskini_selesai = "yes")

#if($!skrin_online_17 == "yes" and $!id_Status == "160")
#parse("app/ppk/info_paging_online.jsp")
#end

#set($ayat_klik_pengesahan="<br><blink>*</blink> Tab <font color='black'>PENGESAHAN PERMOHONAN</font> Akan Dipaparkan Apabila Maklumat Diatas Telah Lengkap Diisi.")
#set($hideTabPengesahan="")
#if($hideTabPengesahan_simati == "show" && $hideTabPengesahan_pemohon == "show" && $hideTabPengesahan_hta == "show")
#set($hideTabPengesahan="show")
#set($ayat_klik_pengesahan="<br><blink>*</blink> Klik pada Tab <blink><font color='black' style='cursor: pointer;' onClick='setSelected(3,0,0,0);NAview();'>PENGESAHAN PERMOHONAN</font></blink> Apabila Maklumat Diatas Telah Lengkap Diisi.")
#end

#set($hideTabPengesahan_lengkap_simati="<font color='red'><span class='blink'>TIDAK LENGKAP</span></font>")
#set($hideTabPengesahan_lengkap_pemohon="<font color='red'><span class='blink'>TIDAK LENGKAP</span></font>")
#set($hideTabPengesahan_lengkap_hta="<font color='red'><span class='blink'>TIDAK LENGKAP</span></font>")

#if($hideTabPengesahan_simati == "show")
#set($hideTabPengesahan_lengkap_simati="<blink><font color='green'>LENGKAP</font></blink>")
#end

#if($hideTabPengesahan_pemohon == "show")
#set($hideTabPengesahan_lengkap_pemohon="<blink><font color='green'>LENGKAP</font></blink>")
#end

#if($hideTabPengesahan_hta == "show")
#set($hideTabPengesahan_lengkap_hta="<blink><font color='green'>LENGKAP</font></blink>")
#end



#if($!skrin_online_17 == "yes")

#foreach($listxx in $View)
#set($bkp = $listxx.bkp)
#set($lp = $listxx.lp)
#set($bpa = $listxx.bpa)
#set($lpa = $listxx.lpa)
#set($ht = $listxx.ht)
#set($lt = $listxx.lt)
#set($idStatus = $listxx.id_Status)
#set($id_permohonansimati = $listxx.id_Permohonansimati)
#end

#set($hideTabPengesahan = "show")
#set($total_harta_baru = 0)
#set($xikut_syarat = 0)
#set($c1 = 0)
<!--
---------------------------------------------------------------<br />
Tujuan ::<br>
batal pentadbir ::: $bkp<br>
lantik pentadbir ::: $lp<br>
batal amanah ::: $bpa<br>
lantik amanah ::: $lpa<br>
harta tertingal ::: $ht<br>
lain tujuan ::: $lt<br>
-------------------------------------------------------------<br />
harta tak alik ada hakmilik :::: $!listHTA.size()<br />
harta tak alik tiada hakmilik :::: $!listHTAX.size()<br>
harta alik :::: $!listHa.size()<br> 
-->

#set($total_harta_baru = $!listHTA.size()+$!listHTAX.size()+$!listHa.size())

<!--
total harta baru ::: $!total_harta_baru 
-------------------------------------------------------------<br />
harta terdahulu yg pemegang amanah dan pentadbir <br>
(dulu)harta tak alik ada hakmilik :::: $!listHTAdulu.size()<br />
(dulu)harta tak alik tiada hakmilik :::: $!listHTAXdulu.size()<br>
(dulu)harta alih :::: $!listHadulu.size()<br>
----------------------------------------------------------------<br />
-->

#foreach($listam in $listHTAdulu)
      #foreach($lo in $check_pilihan)     
      #if($lo.id_hta == $listam.idhta && $lo.id_permohonansimati == $id_permohonansimati)
      #set($c1 = $c1 + 1)
      #end     
      #end
#end    

#foreach($listamx in $listHTAXdulu)
      #foreach($li in $check_pilihan)     
      #if($li.id_hta == $listamx.idhta && $li.id_permohonansimati == $id_permohonansimati)
      #set($c1 = $c1 + 1)
      #end     
      #end
#end     

#foreach($listha_dulu in $listHadulu)
      #foreach($lh in $check_pilihan_ha)     
      #if($lh.id_ta == $listha_dulu.id_Ha && $lh.id_permohonansimati == $id_permohonansimati)
      #set($c1 = $c1 + 1)
      #end     
      #end
#end                              		

<!--
c1 ::::::::::::::::: $!c1	 <br />	
id_Permohonansimati :::::::::::: $!id_permohonansimati <br />

-------------------------------------------------------------------<br />
-->

#if($!ht == "Y" && $!total_harta_baru == 0)
#set($xikut_syarat = $xikut_syarat + 1)
#end

#if(($!lpa == "Y" || $!bpa == "Y" || $!lp == "Y" || $!bkp == "Y") && $c1 == 0)
#set($xikut_syarat = $xikut_syarat + 1)
#end

#if($!xikut_syarat > 0)
#set($hideTabPengesahan = "no")
#set($ayat_klik_pengesahan_17="<br><blink>*</blink> Tab <font color='black'>PENGESAHAN PERMOHONAN</font> Akan Dipaparkan Apabila Maklumat Diatas Telah Lengkap Diisi.")
#else
#set($ayat_klik_pengesahan_17="<br><blink>*</blink> Klik pada Tab <blink><font color='black' style='cursor: pointer;' onClick='setSelected(3,0,0,0);NAview();'>PENGESAHAN PERMOHONAN</font></blink> Apabila Maklumat Diatas Telah Lengkap Diisi.")
#end





#set($status_tujuan_harta_tertingal="<font color='red' ><span class='blink'>TIDAK LENGKAP</span></font>")
#set($status_tujuan_batal_lantik="<font color='red'><span class='blink'>TIDAK LENGKAP</span></font>")
#set($status_tujuan_lainlain="<blink><font color='green'>LENGKAP</font></blink>")

#if($!ht == "Y" && $!total_harta_baru > 0)
#set($status_tujuan_harta_tertingal="<blink><font color='green'>LENGKAP</font></blink>")
#end

#if(($!lpa == "Y" || $!bpa == "Y" || $!lp == "Y" || $!bkp == "Y") && $c1 > 0)
#set($status_tujuan_batal_lantik="<blink><font color='green'>LENGKAP</font></blink>")
#end



#set($status_tujuan = "")

#if($ht == 'Y')
#set($status_tujuan = $status_tujuan + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<blink>*</blink> Harta tertinggal di permohonan awal [$!status_tujuan_harta_tertingal]")
#end

#if($lp == 'Y' || $bkp == 'Y' || $bpa == 'Y' || $lpa == 'Y')
#set($status_tujuan = $status_tujuan + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<blink>*</blink> Lantik/Batal Pemegang Amanah atau Lantik/Batal Pemegang Surat Kuasa Tadbir [$!status_tujuan_batal_lantik]")
#end

#if($lt == 'Y')
#set($status_tujuan = $status_tujuan + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<blink>*</blink> Lain-lain tujuan [$!status_tujuan_lainlain]")
#end



#end


<input name="bkp" id="bkp" type="hidden" value="$!bkp" />
<input name="lp" id="lp" type="hidden" value="$!lp" />
<input name="bpa" id="bpa" type="hidden" value="$!bpa" />
<input name="lpa" id="lpa" type="hidden" value="$!lpa" />
<input name="ht" id="ht" type="hidden" value="$!ht" />
<input name="lt" id="lt" type="hidden" value="$!lt" />



#set($open_button_online = "yes")

#if($!skrin_online == "yes" and $!id_Status != "150")
#set($open_button_online = "no")
#end

#if($!skrin_online_17 == "yes" and $!id_Status != "160")
#set($open_button_online = "no")
#end

#if($!skrin_online_17 == "yes")
#if($nowpast == "past")
#set($open_button_online = "no")
#end
#end

#if($skrin_online_popup == "yes")
#set($open_button_online = "yes")
#end

#if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
<style type="text/css">
<!--
.styleXX {color: #0000FF}
-->
</style>




#if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end

<fieldset id="header_lama" style="display:none">
<legend>MAKLUMAT PERMOHONAN</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top" width="50%"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;" valign="top"><div align="right">No Fail</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top"><div align="center" >:</div></td>
          <td width="64%" style="text-transform:uppercase;" valign="top"><div align="left" class=" styleXX">$listnoFail</div>
            #set($NO_FAIL = $listnoFail)
          </td>
        </tr>
        <tr>
          <td  style="text-transform:uppercase;" valign="top"><div align="right">Negeri</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top"><div align="center" >:</div></td>
           #foreach($listnegori in $listnegeri)      
           #if($listidnegeri==$listnegori.id_Negeri)                              
           #set($kodlistnegori="$listnegori.nama_Negeri")                 
           #end
           #if($listidnegeri=="" || $listidnegeri=="0")
           #set($kodlistnegori="")
           #end
           #end
          <td style="text-transform:uppercase;" valign="top"><div align="left" class=" styleXX">$kodlistnegori</div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top"><div align="right">Daerah / Jajahan</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td style="text-transform:uppercase;" valign="top" ><div align="left"><span class=" styleXX">$listnamadaerah</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Unit</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td style="text-transform:uppercase;" valign="top" ><div align="left"><span class=" styleXX">$listnamaPejabat</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Tempoh Sasaran KPI</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td style="text-transform:uppercase;" valign="top" ><div align="left"><span class=" styleXX"><div id="kpi_ppk" ></div></span></div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="30%" style="text-transform:uppercase;" valign="top" ><div align="right">Status Fail</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td width="69%" style="text-transform:uppercase;" valign="top" ><div align="left"><span class=" styleXX">$listketerangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Seksyen</div></td>
          <td  style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td style="text-transform:uppercase;" valign="top" ><div align="left"><span class=" styleXX">$listseksyen</span>
                   
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Tarikh Mohon</div></td>
           <td width="1%" style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td style="text-transform:uppercase;" valign="top" ><div align="left"><span class=" styleXX">$listtarikhMohon</span> 
          </div>
          <input type="hidden" name="tarikh_mohon_kpi" id="tarikh_mohon_kpi" value="$!listtarikhMohon"  />
          
          
          </td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Nama Pemohon</div></td>
           <td width="1%" style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td style="text-transform:uppercase;" valign="top" ><div align="left"><span class=" styleXX">$listnamaPemohon</span>
                   
          </div></td>
        </tr>
        
        #foreach($list_emel_pemohon in $View)
    	#set ($emel_pemohon = $list_emel_pemohon.emel_pemohon)
    	#end
        
        #if($!emel_pemohon != "")
         <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Emel Pemohon</div></td>
           <td width="1%" style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td ><div align="left"><span class=" styleXX" valign="top"><a href="mailto:$!emel_pemohon"><font color="blue"><u>$!emel_pemohon</u></font></a> </span>
                   
          </div></td>
        </tr>
        
        #end
        
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Nama Simati</div></td>
           <td width="1%" style="text-transform:uppercase;" valign="top" ><div align="center" >:</div></td>
          <td style="text-transform:uppercase;" valign="top" ><span class=" styleXX">$listnamaSimati</span>           </td>
        </tr>
      </table>
    </div></td>
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

<script>

if (document.f1.tarikh_mohon_kpi.value != "" ){

var tarikhHantar = document.f1.tarikh_mohon_kpi.value;

var days = 0;
if('$!listseksyen' == '8')
{
var days = 165;
}
if('$!listseksyen' == '17')
{
var days = 90;
}


var dt1 = parseInt(tarikhHantar.substring(0,2),10) + days;
var mon1 = parseInt(tarikhHantar.substring(3,5),10)-1;
var yr1 = parseInt(tarikhHantar.substring(6,10),10);

var currentTime = new Date();
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

if(currentTime>myDate)
{
$jquery("#kpi_ppk").html("<span  style='color:red'><blink>"+tarikhJangkaTerima+"</blink></span>");
}
else
{
$jquery("#kpi_ppk").html("<span  style='color:red'>"+tarikhJangkaTerima+"</span>");
}

} else {
$jquery("#kpi_ppk").html("<span  style='color:red'></span>");
}

</script>


#else

#if($skrin_online_popup == "yes")
<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<link rel="stylesheet" type="text/css" href="../../css/online.css">

#end






#if($!skrin_online == "yes")
	#if($open_button_online == "yes" && $skrin_online_popup != "yes")
		#if($!masuk_skrin_pengesahan != "yes")
<div id="cetak_lengkapkan_a"></div>
<script>
parent.document.getElementById("cetak_lengkapkan_a").innerHTML="<div class=\"warning_online_ppk\"><table><tr><b><blink>*</blink> Sila Pastikan Maklumat Simati, Pemohon dan Harta Tak Alih Lengkap Diisi.<br><blink>*</blink> Sekurang-kurangnya Satu Harta Tidak Alih Perlu Didaftarkan untuk Meneruskan Urusan Permohonan.<br><blink>*</blink> Status Semasa :<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<blink>*</blink> Maklumat Simati [$hideTabPengesahan_lengkap_simati]<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<blink>*</blink> Maklumat Pemohon [$hideTabPengesahan_lengkap_pemohon]<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<blink>*</blink> Maklumat Harta Tak Alih (Ada Hakmilik atau Tiada Hakmilik) [$hideTabPengesahan_lengkap_hta]$ayat_klik_pengesahan<br><blink>*</blink> Bagi Permohonan yang belum Dihantar ke Unit Pusaka Kecil, Pengguna masih boleh<br> &nbsp;&nbsp;&nbsp;Menyemak atau Mengemaskini Maklumat Permohonon pada Skrin<font color='black'> Deraf Permohonan PPK</font>.</b></table></div>";
</script>
		#else 
	        #if($idStatus == "150")
<div id="cetakan_borang_a"></div>
<script>
parent.document.getElementById("cetakan_borang_a").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Sila Pilih Negeri dan Daerah untuk Penghantaran Permohonan ke Unit Pusaka Kecil yang Dipilih.<br /><blink>*</blink> Sila Pastikan Maklumat Permohonan adalah Lengkap dan Tepat Sebelum Dihantar ke Unit Pusaka Kecil.<br /><blink>*</blink> Permohonan ini akan dibatalkan sekiranya terdapat permohonan lain yang lengkap dihantar ke <br>&nbsp;&nbsp;&nbsp;Unit Pembahagian Pusaka Kecil bagi Simati yang sama.</b></div>";
</script>
			#else
<div id="cetakan_borang_a"></div>
<script>
parent.document.getElementById("cetakan_borang_a").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Permohonan Online telah Berjaya Dihantar ke $!namapejabat.</b></div><br><blink>*</blink> Sila cetak Borang A dan Pengesahan Permohonan untuk dibawa ke Unit Pembahagian Pusaka Kecil.</b></div>";
</script>
			#end    
		#end 
	#else 
<!-- sek 8 online selepas hantar ke pejabat ppk -->
		#if($!skrin_online_popup != "yes")
<div id="cetakan_borang_p"></div>
<script>
parent.document.getElementById("cetakan_borang_p").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Permohonan ini telah dihantar ke Unit Pusaka Kecil.<br><blink>*</blink> Klik pada Tab <blink><font color='black' style='cursor: pointer;' onClick='setSelected(3,0,0,0);NAview();'>PENGESAHAN PERMOHONAN</font></blink> untuk Paparan Maklumat Asas Permohonan.<br><blink>*</blink> Bagi Permohonan yang telah Dihantar ke Unit Pusaka Kecil, Pengguna masih boleh<br> &nbsp;&nbsp;&nbsp;Menyemak Maklumat Permohonon pada Skrin<font color='black'> Status Permohonan Pusaka</font>.</b></div>";
</script>
		#end
	#end
#end






#if($!skrin_online_17 == "yes")
	#if($idStatus == "160")
		#if($!masuk_skrin_pengesahan != "yes")
<div id="header_sek_17_online"></div>
<script>
parent.document.getElementById("header_sek_17_online").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Sila Pastikan Maklumat Borang P Lengkap Diisi Berdasarkan dengan Tujuan Permohonan yang Dipilih.<br><blink>*</blink> Jika Salah Satu Daripada Tujuan Permohonan adalah <font color='black'>Harta tertinggal di permohonan awal</font>,<br> &nbsp;&nbsp;&nbsp;Sila Klik Butang <font color='black'>Tambah</font> pada Skrin Harta Tak Alih atau Harta Alih untuk Penambahan Maklumat Harta.<br><blink>*</blink> Jika Salah Satu Daripada Tujuan Permohonan adalah <font color='black'>Lantik/Batal Pemegang Amanah</font> atau <br> &nbsp;&nbsp;&nbsp;<font color='black'>Lantik/Batal Pemegang Surat Kuasa Tadbir</font>,Sila Klik Butang <font color='black'>Senarai Harta Terdahulu</font> pada Skrin Harta Tak Alih atau<br> &nbsp;&nbsp;&nbspHarta Alih untuk Membuat Pilihan Harta. Setelah Pilihan Dibuat, Sila Klik pada Butang <font color='black'>Simpan Pilihan</font>.<br><blink>*</blink> Tujuan Permohonan :$!status_tujuan $ayat_klik_pengesahan_17<br><blink>*</blink> Bagi Permohonan yang belum Dihantar ke Unit Pusaka Kecil, Pengguna masih boleh<br> &nbsp;&nbsp;&nbsp;Menyemak atau Mengemaskini Maklumat Permohonon pada Skrin<font color='black'> Deraf Permohonan PPK</font>.</b></div>";
</script>
		#else 
	        #if($idStatus == "160")
<div id="cetakan_borang_p"></div>
<script>
parent.document.getElementById("cetakan_borang_p").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Sila Pastikan Maklumat Permohonan adalah Lengkap dan Tepat Sebelum Dihantar ke Unit Pusaka Kecil.<br /><blink>*</blink> Permohonan ini akan dibatalkan sekiranya terdapat permohonan lain yang lengkap dihantar ke <br>&nbsp;&nbsp;&nbsp;Unit Pembahagian Pusaka Kecil bagi Simati yang sama.</b></div>";
</script>
			#else
<div id="cetakan_borang_p"></div>
<script>
parent.document.getElementById("cetakan_borang_p").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Permohonan Online telah Berjaya Dihantar ke $!namapejabat.</b></div><br><blink>*</blink> Sila cetak Borang P dan Pengesahan Permohonan untuk dibawa ke Unit Pembahagian Pusaka Kecil.</b></div>";
</script>
			#end    
		#end 
	#else 
    
<!-- sek 8 online selepas hantar ke pejabat ppk -->
		#if($!skrin_online_popup != "yes")
<div id="cetakan_borang_p"></div>
<script>
parent.document.getElementById("cetakan_borang_p").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Permohonan ini telah dihantar ke Unit Pusaka Kecil.<br><blink>*</blink> Klik pada Tab <blink><font color='black' style='cursor: pointer;' onClick='setSelected(3,0,0,0);NAview();'>PENGESAHAN PERMOHONAN</font></blink> untuk Paparan Maklumat Asas Permohonan.<br><blink>*</blink> Bagi Permohonan yang telah Dihantar ke Unit Pusaka Kecil, Pengguna masih boleh<br> &nbsp;&nbsp;&nbsp;Menyemak Maklumat Permohonon pada Skrin<font color='black'> Status Permohonan Pusaka</font>.</b></div>";
</script>
#set($open_button_online = "no")
		#end
        
    #end
#end

 


#end


<!--

<br /> skrin_online_17 :::::::: $skrin_online_17
<br /> open_button_online ::::::: $open_button_online
<br /> masuk_skrin_pengesahan ::::: $masuk_skrin_pengesahan
<br /> hideTabPengesahan ::::::::: $hideTabPengesahan
<br /> id_Status :::::: $!id_Status
<br /> idStatus ::::::::: $!idStatus
-->

<script>
function ForView(noFail) {

	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.f1.submit();
}
  </script>
