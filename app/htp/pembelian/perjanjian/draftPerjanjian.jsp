<style type="text/css">
<!--
	.pautanms{color: #0000FF}
-->
</style> <fieldset>
      <legend><strong>DRAF PERJANJIAN</strong></legend>
      <table width="100%" border="0" cellpadding="0">
      	<tr>
      		<td colspan="4">
      			 <input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="tambahDraft()" />
                 <!--<input type="button" class="stylobutton" name="cetakderafmemo" id="cetakderafmemo" value="Cetak" onclick="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianDokumenMemoDerafPKP');" /> -->
               	<input type="button" class="stylobutton100" name="cetakderafmemo" id="cetakderafmemo" value="Cetak" onclick="javascript:senaraiDokumenSurat('tabledokumensurat');" />
      		</td>
      	</tr>
      	<tr class="table_header">
          <td scope="row" width="3%" align="center"><strong>Bil.</strong></td>
          <td width="15%"><strong>Tarikh Terima Draf</strong></td>
          <td width="15%" align=""><strong>Tarikh Hantar Draf</strong></td>
          <td width="42%" ><strong>Ulasan</strong></td>
          <td width="5%" ></td>
        </tr>
        #set ( $cnt = 0 )	
      	 #foreach ($senarai in $drafts)
      	 	#set ( $cnt = $cnt + 1 )
	    	#if ($senarai.bil == '')
	    	#set( $row = "row1" )
	    	#elseif (($senarai.bil % 2) != 0)
	    	#set( $row = "row1" )
	    	#else 
	    	#set( $row = "row2" )
	    	#end
      	 <tr>
          <td class="$row" ><a href="javascript:paparDraft('$senarai.idDrafPerjanjian')" >$cnt.</a></td>
          <td class="$row"><a href="javascript:paparDraft('$senarai.idDrafPerjanjian')" class="pautanms">$!senarai.tarikhTerima</a></td>
          <td class="$row">$!senarai.tarikhHantar</td>
          <td class="$row">$!senarai.ulasan</td>
          <td>
          	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Hapus" onclick="deleteDraft('$senarai.idDrafPerjanjian')" />
          </td>
        </tr>
      	 
      	 
      	 #end
      </table>
 </fieldset>
  <fieldset id="tabledokumensurat" style="display:none;">
<legend>SENARAI DOKUMEN/SURAT</legend>
<table width="100%" border="0">
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianDokumenMemoDerafPKP')" class="pautanms">MEMO KEPADA PKP</a></td>
  </tr>
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianDokumenMemoDerafPKPSenarai')" class="pautanms">MEMO KEPADA PKP (Ulasan Panjang)</a></td>
  </tr>
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianDokumenMemoDerafPUU')" class="pautanms">MEMO KEPADA PUU</a></td>
  </tr>  
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianDokumenMemoDerafPUUSenarai')" class="pautanms">MEMO KEPADA PUU (Ulasan Panjang)</a></td>
  </tr>   
</table>
</fieldset> 
 <!-- 
  <fieldset>
      <legend><strong>SEMAKAN DRAF PERJANJIAN</strong></legend>
      <table>
      	<tr>
      		<td>
      			Tarikh Terima dari KJP
      		</td>
      
      		<td>
      			<input type="text" name="tarikhTerimaKJP" value="" $mode $classDis>
				<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhTerimaKJP',false,'dmy');" />
      		</td>
      	</tr>
      	<tr>
      		<td>
      			Tarikh Hantar ke PKP
      		</td>
      
      		<td>
      			<input type="text" name="tarikhHantarPKP" value="" $mode $classDis>
				<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhHantarPKP',false,'dmy');" />
      		</td>
      	</tr>
      	<tr>
      		<td>
      			Tarikh Terima dari PKP
      		</td>
      
      		<td>
      			<input type="text" name="tarikhTerimaPKP" value="" $mode $classDis>
				<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhTerimaPKP',false,'dmy');" />
      		</td>
      	</tr>
      	<tr>
    
		    	<td align="center" colspan="3">
		    		#if($semakMode == "new")
		    		 <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="" />
                     
		    		#elseif($semakMode == "update")
		    		 <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="" />
                      <
		    		#else
		    		
		    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="" />
                         
		    		#end
		    		
		    	
		    	</td>
		    
	 </tr>
      </table>	
  </fieldset>
   -->