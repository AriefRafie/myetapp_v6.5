<!-- Flag Semakan Online (Tblpptpermohonan)
	null = PT daftar
	1 = Hantar Untuk Semakan
	2 = Telah disemak Penolong Pengarah dan hantar untuk Kelulusan Pengarah
	3 = Telah diluluskan Pengarah

	Jawatan
	24 = Pembantu Tadbir
	9  = Penolong Pengarah
	4  = Pengarah
-->

<!-- Get Portal Role -->
#set($portal_role = "${session.getAttribute('_portal_role')}")

<!-- Refresh value -->
#set($layer1="")
#set($layer2="")
#set($layer3="")
#set($roleAgensi="")

<!-- Layer by id_jawatan -->
<!-- Layer 1 (Pembantu Tadbir)-->
#set($layer1="24")
<!-- Layer 2 (Penolong Pengarah)-->
#set($layer2="9")
<!-- Layer 3 (Pengarah)-->
#set($layer3="4")

<!-- Role Agensi -->
#if($portal_role=="online_kjpagensi")
	#set($roleAgensi="yes")
#else
	#set($roleAgensi="no")
#end



