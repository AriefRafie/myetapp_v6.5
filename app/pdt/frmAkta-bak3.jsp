<style type="text/css">
body
{
	background-color:#FFFFFF;
	font-size: 12px;
	font-family: arial, sans-serif;
	margin-top: 5%; margin-right: 5%; margin-bottom: 0px; margin-left:5%;
}

.catatan {
width:90%;
}

pre {
	font-size: 12px;
	font-family: arial, sans-serif;
	padding: 0;
	margin: 0;
}
pre code {
	margin: 0 0 0 40px;  /*--Left Margin--*/
	padding: 18px 0;
	display: block;
}

</style>
<!-- START -->
<a name="home"> 
$Akta_NamaAkta <br>
(ACT $Akta_NoAkta) <br>

<!-- CASE 1 -->
<!-- IF PENGGAL EXIST -->
#if ($isPenggalExist)
<br>
	#set ($listPenggal = $akta.getPenggalLists($idAkta))
	#foreach ($x in $listPenggal)
		<b>$x.no - $x.tajuk </b><br>
		<!-- IF BAHAGIAN EXIST -->
		#if ($isBahagianExist)
			<br>
			#set ($listBahagian = $akta.getBahagianLists($x.id))
			#foreach ($y in $listBahagian)
				$y.no - $y.tajuk <br>
				<!-- IF SEKSYEN EXIST -->
				#set ($listSeksyen = $akta.getSeksyenLists("ID_AKTABAHAGIAN",$y.id,"AND ID_AKTABAB IS NULL"))
				#foreach ($s in $listSeksyen)
					<br> 
					$s.no
					#if ($s.tajuk !="")
					- $s.tajuk 
					#elseif ($s.Seksyen !="")
					- $s.Seksyen
					#end 
					<br>
				#end
				<!-- IF BAB EXIST -->
				#if ($isBabExist)
					<br>
					#set ($listBab = $akta.getBabLists($y.id))
					#foreach ($z in $listBab)
						$z.no - $z.tajuk <br>
						<!-- IF SEKSYEN EXIST -->
						#set ($listSeksyen = $akta.getSeksyenLists("ID_AKTABAB",$z.id,""))
						#foreach ($s in $listSeksyen)
							$s.no
							#if ($s.tajuk !="")
							- $s.tajuk 
							#elseif ($s.Seksyen !="")
							- $s.Seksyen
							#end 
							<br>
						#end
					#end
				#end
				<!-- END IF BAB EXIST -->
			#end
		<p>
		#end
		<!-- END IF BAHAGIAN EXIST -->
	#end 

#end


<!-- END IF PENGGAL EXIST -->

<!-- JADUAL -->
#foreach ($x in $List_Jadual)
<a name="$x.NamaJadual"></a>
<a href="#$x.IDJadual">$x.NamaJadual</a><br>
#end

<br><br>
#foreach ($x in $List_Jadual)
<b><a name="$x.IDJadual"></a>
<a href="#$x.NamaJadual">$x.NamaJadual</a>
<br>
	#set ($listLampiran = $akta.getJadualLampiranList($x.IDJadual))
	#foreach ($lampiran in $listLampiran)
		#if ($lampiran.catatan != "")
			<center>$lampiran.catatan</center> <br>
		#end
	<img src="../../servlet/ekptg.view.pdt.JadualImage?id=$lampiran.id"><br>
	#end
	
<!--
<img src="../../servlet/ekptg.view.pdt.JadualImage?id=$x.IDJadual">
-->
<br>
#end




