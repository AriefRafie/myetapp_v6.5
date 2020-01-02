<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
<!--
.pautanms {color: #0000FF}
-->
</style>
<table width="98%" border="0">
	<tr>
		<td>
			

			<input name="idHakmilik" type="hidden" value="$idHakmilik" />
			<fieldset><legend>MAKLUMAT HAKMILIK</legend>
			<table width="100%" border="0">
				
			  <tr>
			    <td>
				#parse("app/htp/rekod/frmPendaftaranHakmilikMaklumatFailReadOnly.jsp")
			    </td>
			  </tr>
  			<tr>
			    <td>
				#parse("app/htp/rekod/frmPendaftaranMaklumatHakmilikReadOnly.jsp")
			    	</td>
			  </tr>
			</table>
			<!-- End of Auto scroll table -->
			</fieldset>

			<fieldset><legend>SENARAI PERGERAKAN</legend>
			<div style="width:100%;height:200;overflow:auto"> 
				<table border="0" width="100%">
			  		<tr>
			    		<td colspan="7">
			            <input type="button" name="Daftar Permohonan Pinjaman Dokumen" value="Daftar Permohonan Pinjaman Dokumen" 
			            onclick="tambahPergerakan()" />
			   			</td>
			  		</tr>
			  <tr class="table_header">
			    <td width="3%">Bil.</td>
			    <td width="47%">Catatan</td>
			    <td width="10%">Tarikh Serahan</td>
			    <!-- <td width="14%">Status</td> -->
			    <td width="30%">Kepada</td>
			    <td width="10%">Tarikh Kembali</td>
			  </tr>
			  #foreach ($senaraiPergerakan in $SenaraiPergerakan)
			  #set( $i = $velocityCount )
			  #if ( ($i % 2) != 1 )
			  #set( $row = "row2")
			  #else
			  #set( $row = "row1")
			  #end
			  <tr class="$row">
			    <td >$senaraiPergerakan.bil</td>
			    #if($senaraiPergerakan.bil !='')
			    <td ><a href="javascript:viewDetailPergerakan('$senaraiPergerakan.idPergerakan')" class="style1 style1">$senaraiPergerakan.keterangan</a></td>
			    #else
			    <td >$senaraiPergerakan.keterangan</td>
			    #end
			    <td >$senaraiPergerakan.tarikh</td>
			    <!--<td width="14%">$senaraiPergerakan.status</td>-->
			    <td >$senaraiPergerakan.kepada</td>
			    <td >$senaraiPergerakan.tarikhKembali</td>
			  </tr>
			 #end

			</table>
			</div> <!-- End of Auto scroll table -->
			</fieldset>

		</td>
	</tr>
			  <tr>
			    <td colspan="7"><div align="center">
			      <input type="button" class="stylobutton100" name="btnBack" id="btnBack" value="Kembali" onclick="kembaliSenarai()"/>
			    </div></td>
			  </tr>	
</table>
<script>
	function tambahPergerakan(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=tambahPergerakanHakmilik";
		document.${formName}.submit();
	}
	function viewDetailPergerakan(id){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=paparPergerakanHakmilikDetail&idPergerakan="+id;
		document.${formName}.submit();
	}
	function kembaliSenarai(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=";
		document.${formName}.submit();
	}
</script>