<!-- OPEN [STANDARD] BOLEH COPY PASTE -->
<fieldset>
<legend>Senarai</legend>

<!-- html untuk senarai fail, jimat da masa programmer -->

$html


#if(($totalRecords == 0 || $totalRecords == ""))
<!-- if ada rekod, tak perlu la display natang ni -->
<div class="infoMsg"><i><font color='blue'>Info</font> : Tiada rekod dipaparkan secara 'default'. Bertujuan untuk meningkatkan lagi prestasi sistem.</i></div>
<div class="infoMsg"><i><font color='blue'>Info</font> : Sila buat carian untuk paparan senarai fail.</i></div>
#end



</fieldset>
<!-- CLOSE [STANDARD] BOLEH COPY PASTE -->
<!-- tidak wajib untuk call, untuk semakan prestasi saja -->
#parse("app/RazTemplate/loadingInfo.jsp")
