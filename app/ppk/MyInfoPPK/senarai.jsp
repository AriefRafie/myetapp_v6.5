
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

<fieldset>
<legend>Senarai</legend>
#if($showPaging == "true" && $totalRecords > 0) <!-- show pagingnation -->
<table border="0" cellspacing="1" cellpadding="3" width="100%"  class="classFade"> 
<tr >
<td  align="left" valign="top" colspan="15" >
#parse("app/RazTemplate/recordPageList.jsp")
</td>
</tr>
</table>
#end

<!-- successInfo -->
#if($command == "carianSenarai" && $actionajax == "")
<div id="displayInfo_$skrinName" class="info"  align="left" style="font-size:120%">
Carian Berjaya. $totalRecords Rekod Dijumpai. 
<script >
$jquery("#displayInfo_$skrinName").show().delay(3000).fadeOut();
</script>
</div>
#end

<!-- lepas ni, sy akan templetekan skrin ini untuk kemudahan semua -->


#if($command == "carianSenarai")
       
<table border="0" cellspacing="1" cellpadding="3" width="100%" class="classFade" > 
<tr class="table_header" >
<td align="center" valign="top" width="5%" >Bil.
</td>
<td align="left" valign="top"  class="columnSort" onclick="sortOnClick($totalRecords,'$command','NO_FAIL','VARCHAR2','$div','$skrinName','');" width="15%">No. Fail
#if($totalRecords > 1)<span class="arrow down" ></span>#end</td>
<td align="left" valign="top"  class="columnSort" onclick="sortOnClick($totalRecords,'$command','NAMA_SIMATI','VARCHAR2','$div','$skrinName','');" width="15%">Nama Simati
#if($totalRecords > 1)<span class="arrow down" ></span>#end</td>
<td align="left" valign="top"  class="columnSort" onclick="sortOnClick($totalRecords,'$command','NAMA_PEMOHON','VARCHAR2','$div','$skrinName','');" width="15%">Nama Pemohon
#if($totalRecords > 1)<span class="arrow down" ></span>#end</td>
<td align="center" valign="top"  class="columnSort" onclick="sortOnClick($totalRecords,'$command','TARIKH_MOHON','DATE','$div','$skrinName','','dd/MM/yyyy');" width="10%">Tarikh Mohon
#if($totalRecords > 1)<span class="arrow down" ></span>#end</td>
<td align="left" valign="top" class="columnSort" onclick="sortOnClick($totalRecords,'$command','STATUS','VARCHAR2','$div','$skrinName','');" width="15%">Status
#if($totalRecords > 1)<span class="arrow down" ></span>#end</td>
<td align="left" valign="top" class="columnSort" onclick="sortOnClick($totalRecords,'$command','PENDAFTAR','VARCHAR2','$div','$skrinName','');" width="15%">Didaftar Oleh
#if($totalRecords > 1)<span class="arrow down" ></span>#end</td>
<!--<td align="center" valign="top" width="10%">Tindakan</td>-->
</tr>


#if($listSenaraiFail.size()>0)
    #foreach($pr in $listSenaraiFail)
    
        <tr>
        <td class="$pr.ROWCSS"  align="center" valign="top" >$pr.BILSORT </td>
        <td class="$pr.ROWCSS"  align="left" valign="top" >        
        <a href="javascript:papar('$pr.ID_PERMOHONAN','$pr.ID_STATUS','$pr.ID_FAIL','$pr.ID_SIMATI','$pr.ID_PERMOHONANSIMATI','$pr.TARIKH_MOHON','$pr.FLAG_JENIS_FAIL','$pr.SEKSYEN')"  class="link">
        $pr.NO_FAIL
        </a>
        </td>
        <td class="$pr.ROWCSS"  align="left" valign="top" >$pr.NAMA_SIMATI</td>
        <td class="$pr.ROWCSS"  align="left" valign="top" >$pr.NAMA_PEMOHON</td>
        <td class="$pr.ROWCSS"  align="center" valign="top" >$pr.TARIKH_MOHON</td>
        <td class="$pr.ROWCSS"  align="left" valign="top" >$pr.STATUS
        #if($pr.PETISYEN_NO != "")
        <a href="javascript:semakBorangC('$pr.NO_FAIL')"  class="link">Semak Borang C</a>
        #end        
        </td>
        <td class="$pr.ROWCSS"  align="left" valign="top" >$pr.PENDAFTAR</td>
       	</tr>
    #end
    
#else
  <tr>
        <td align="left" valign="top" colspan="10" >       
        TIADA REKOD
       
        </td>
  </tr>      
#end

</table>
#else
        	<div class="infoMsg"><i><font color='blue'>Info</font> : Tiada rekod dipaparkan secara 'default'. Bertujuan untuk meningkatkan lagi prestasi sistem.</i></div>
            <div class="infoMsg"><i><font color='blue'>Info</font> : Sila buat carian untuk paparan senarai fail.</i></div>
#end


</fieldset>
<!-- tidak wajib untuk call, untuk semakan prestasi saja -->
#parse("app/RazTemplate/loadingInfo.jsp")