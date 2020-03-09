
#if($!appear_skrin_info=="simpan")
<div id="info_simpan_berjaya"></div>
<script>
parent.document.getElementById("info_simpan_berjaya").innerHTML="<div class=\"success_online_ppk\"><b><blink>*</blink> Maklumat Berjaya Disimpan</b></div>";
</script>
#elseif($!appear_skrin_info=="simpan_daftar")
<div id="info_simpan_berjaya"></div>
<script>
parent.document.getElementById("info_simpan_berjaya").innerHTML="<div class=\"success_online_ppk\"><b><blink>*</blink> Maklumat Permohonan Asas Berjaya Disimpan</b></div>";
</script>
#elseif($!appear_skrin_info=="kemaskini")
<div id="info_simpan_berjaya"></div>
<script>
parent.document.getElementById("info_simpan_berjaya").innerHTML="<div class=\"success_online_ppk\"><b><blink>*</blink> Maklumat Berjaya Dikemaskini</b></div>";
</script>
#elseif($!appear_skrin_info=="hantar") 
<div id="info_simpan_berjaya"></div>
<script>
parent.document.getElementById("info_simpan_berjaya").innerHTML="<div class=\"success_online_ppk\"><b><blink>*</blink> Maklumat Berjaya Dihantar ke $!namapejabat</b></div>";
</script>
#elseif($!appear_skrin_info=="simpan_pilihan") 
<div id="info_simpan_berjaya"></div>
<script>
parent.document.getElementById("info_simpan_berjaya").innerHTML="<div class=\"success_online_ppk\"><b><blink>*</blink> Pilihan harta berjaya disimpan</b></div>";
</script>

#end
