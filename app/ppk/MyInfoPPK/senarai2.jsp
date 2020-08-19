
<!-- parameter ni depends mengikut keperluan -->
<!-- open hidden field, amik koding asal -->
<input type="hidden" name="idPermohonan" />
<input type="hidden" name="idPermohonanSimati"/>
<input type="hidden" name="idStatus" />
<input type="hidden" name="id_permohonan" />
<input type="hidden" name="id_status" />
<input type="hidden" name="idpermohonan" />
<input type="hidden" name="id_Simati" />
<input type="hidden" name="id_fail" />
<input type="hidden" name="idpermohonansimati" />
<input type="hidden" name="tarikh_mohon" />
<input type="hidden" name="idSimati" />
<input type="hidden" name="flagAdvSearch" value="$flagAdvSearch"/>
<input type="hidden" name="flagFromSenaraiPermohonanSek8"/>
<!-- close hidden field, amik koding asal -->



<!-- OPEN [STANDARD] BOLEH COPY PASTE -->
<fieldset>
<legend>Senarai</legend>
#if($showPaging == "true" && $totalRecords > 0) 
<!-- show pagingnation -->
#parse("app/RazTemplate/recordPageList.jsp")	
#end

<!-- html untuk senarai fail, jimat da masa programmer -->
$htmlSenaraiFail

#if($totalRecords == 0 || $totalRecords == "")
<!-- if ada rekod, tak perlu la display natang ni -->
<div class="infoMsg"><i><font color='blue'>Info</font> : Tiada rekod dipaparkan secara 'default'. Bertujuan untuk meningkatkan lagi prestasi sistem.</i></div>
<div class="infoMsg"><i><font color='blue'>Info</font> : Sila buat carian untuk paparan senarai fail.</i></div>
#end
</fieldset>
<!-- CLOSE [STANDARD] BOLEH COPY PASTE -->


<!-- tidak wajib untuk call, untuk semakan prestasi saja -->
#parse("app/RazTemplate/loadingInfo.jsp")
