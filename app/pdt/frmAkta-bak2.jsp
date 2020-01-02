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
<a name="home">
$Akta_NamaAkta <br>
(ACT $Akta_NoAkta) <br>

<br>
#foreach ($x in $List_Penggal)
$x.NoPenggal - $x.TajukPenggal <br>
#end

<br>

#foreach ($x in $List_Bahagian)
$x.NoBahagian - $x.TajukBahagian <br>
#end

<br>

#foreach ($x in $List_Bab)
<a href="#$x.NoBab">$x.NoBab</a> - $x.TajukBab <br>
#end

<br><br>

####

#set ($tmp = "")

#foreach ($x in $List_Seksyen)
	#if ($tmp == $x.NoBab)

	#else
		#set ($tmp = $x.NoBab)
		<br><a name="$x.NoBab">$x.NoBab </a><br>
	#end
	<a href="#$x.NoSeksyen">$x.NoSeksyen</a> - $x.TajukSeksyen<br>
#end
<br>
#foreach ($x in $List_Jadual)
<a name="$x.NamaJadual"></a>
<a href="#$x.IDJadual">$x.NamaJadual</a><br>
#end

<hr>
<br>Long Title & Preamble<br>
<pre>$Akta_Catatan</pre>
<br>
#foreach ($x in $List_Bab)
<br>$x.NoBab - $x.TajukBab <br>
#set ($list = $akta.getSeksyenLists($x.idAktaBab))
#foreach ($y in $list)
<b><a name="$y.Seksyen"></a>
	<a href="#home">$y.Seksyen</a> - $y.Proviso</b><br>
	#set ($listSub = $akta.getSubSeksyenLists($y.idAktaSeksyen))
	#foreach ($z in $listSub)
	$z.SubSeksyen - $z.Proviso<br><br>
	#end
#end
#end
<br><br>
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



