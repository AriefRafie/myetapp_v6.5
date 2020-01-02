<style type="text/css">
body
{
	background-color:#FAF8CC;
	font-size: 10pt;
	font-family: arial, sans-serif;
	margin-top: 5%; margin-right: 5%; margin-bottom: 0px; margin-left:5%;
}

.catatan {
width:90%;
}

</style>
<a name="home">
$Akta_NamaAkta ACT $Akta_NoAkta <br>
<div class="catatan">$Akta_Catatan</div>
#foreach ($x in $List_Penggal)
$x.TajukPenggal <br>
#end

<br><br>

#foreach ($x in $List_Bahagian)
$x.NoBahagian - $x.TajukBahagian <br>
#end

<br><br>

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
	<a href="#$x.Seksyen">$x.Seksyen</a> - $x.Proviso<br>
#end
<hr>
<br>Long Title<br>
An Act to regulate the form and contents of hire-purchase agreements and the<br> 
rights and duties of parties to such agreements.

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

