<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
<!--<strong> Senarai Permohonan </strong>
<br><br>-->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>
			
			<fieldset> <legend>CARIAN</legend>
			  <table border="0" width="100%">
  					<tr>
					    <td width="29%"><div align="right">Nama Pemilik</div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input name="NamaPemohon" type="text" id="NamaPemohon" size="40" maxlength="40" value="$!carian" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>			  	
  					<tr>
					    <td width="29%"><div align="right">No Fail PP</div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input name="noFailKJP" type="text" id="noFailKJP" size="40" maxlength="40" value="$!carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
			      <tr>
			        <td colspan="3">&nbsp;</td>
			      </tr>
			      <tr>
			        <td align="center" colspan="3">
			        	<input type="button" class="stylobutton" name="cari" value="Cari" onclick="javascript:fGSPA_search_data();">
			        	<input type="reset" class="stylobutton" name="reset" value="Kosongkan" >
			        </td>
			      </tr>
			  </table>
			
			</fieldset>
    	</td>
	</tr>

	<tr>
		<td>
							
		<fieldset>
			<legend>SENARAI PERMOHONAN GADAIAN UNTUK FAIL $NoFail</legend>
  			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
        			<td>
   						<!-- <input type="button" class="stylobutton" name="add" value="Tambah" onclick="javascript:fGSPA_Tambah()" /> -->
						<input type="button" class="stylobutton" name="kembali" value="Kembali"  onclick="javascript:fGSPA_Kembali()">       				
        			</td>
        		<tr>       			
		    </table>
				#parse("app/utils/record_paging.jsp")
		    	


		  <table border="0" cellspacing="2" cellpadding="2" width="100%">
		    <tbody>
		      <tr class="table_header">
		        <td width="3%"><b>Bil.</b></td>
        		<td width="20%"><b>No Fail KPKT/PP</b></td>
		        <td width="54%"><b>Nama Pemilik</b></td>
		        <td width="23%"><b>Status</b></td>
		      </tr>
		      #set ($count = 0)
		      ##foreach ( $permohonan in $SenaraiPermohonan )
		      #foreach ( $permohonan in $lists)
		      #set ($count = $count+1)
		      	#set( $i = $velocityCount )
		          #if ( ($i % 2) != 1 )
		               #set( $row = "row2" )
		          #else
		               #set( $row = "row1" )
		          #end
		      <tr>
		        <td class="$row">$permohonan.bil.</td>
		        <td class="$row">$permohonan.noFailLain</td>
		        <td class="$row"><a href="javascript:fGSPA_seterusnya('$permohonan.idPermohonan')" class="pautanms">$permohonan.nama</a></td>
		        <td class="$row">$permohonan.keterangan</td>
		      </tr>
		      #end
		      #if ($count == 0)
		      <tr>
		        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
		      </tr>
		      #end
		    </tbody>
		  </table>
		  <input type="hidden" name="idFail" value="$IdFail">
		  <input type="hidden" name="noFail" value="$NoFail">
		  <input type="hidden" name="namaskrin" value="permohonan">
		
		
		</fieldset>

    	</td>
	</tr>
</table>
