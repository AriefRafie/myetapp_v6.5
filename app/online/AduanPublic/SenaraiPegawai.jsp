#set($displayTrPegawaiBahagian = "none")
#if($ID_SUMBERTINDAKAN == "16101")
    #set($displayTrPegawaiBahagian = "")
#end
<script>
document.getElementById('tr_SENARAI_PEGAWAIBAHAGIAN_$ID_ADUANPUBLIC').style.display = "$displayTrPegawaiBahagian";
</script>




#if($listPegawaiBahagian.size() > 0)
    #foreach ( $N in $listPegawaiBahagian)		
         $N.USER_NAME<br />
    #end	
    <font color="blue" class="blink">* Emel akan dihantar dan notifikasi permohonan akan dipaparkan pada skrin pegawai diatas untuk tindakan seterusnya!<br /></font>
#else
   <font color="red" class="blink">* Tiada rekod pegawai yang memiliki peranan sistem iaitu 'Wakil Bahagian' bagi Negeri dan Bahagian yang dipilih!<br /></font>
#end
