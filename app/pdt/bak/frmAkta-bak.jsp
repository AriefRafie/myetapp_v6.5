$Akta_NamaAkta ACT $Akta_NoAkta
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
	$x.Seksyen - $x.Proviso<br>
#end
<hr>
<br>Long Title<br>
An Act to regulate the form and contents of hire-purchase agreements and the<br> 
rights and duties of parties to such agreements.

<br><br>

#foreach ($x in $List_Bab)
$x.NoBab - $x.TajukBab <br>
#end

##foreach ($x in $List_SubSeksyen)
##$x.NoBab - $x.TajukBab<br>
##end