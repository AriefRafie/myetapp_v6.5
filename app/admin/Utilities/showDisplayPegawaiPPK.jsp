#if ($viewPejabat.ID_SEKSYEN == 2)
 		#if($listPegawaiPPKDisp.size()>0)
		<tr id="div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT">
							<td valign="top" >				
							</td>			
							<td valign="top" ></td>
							<td valign="top" >
							</td>
							<td>
							<div id="div_listaddPegawai$viewPejabat.ID_PEJABAT">
							  <table width="67%" border="0"  >
                                <tr>
                                  <td align="right" width="19%"  valign="top" ></td>
                                  <td align="center"  width="34%"  valign="top" ></td>
                                  <td align="left"  width="34%"  valign="top" ></td>
                                </tr>
	 #foreach($listPegawai in $listPegawaiPPKDisp)
  <tr>
    <td align="left"   valign="top" width="19%" >$listPegawai.BIL. </td>
    <td align="left"   valign="top" width="34%">$listPegawai.NAMA_PEGAWAI</td>
    <td align="left"   valign="top" width="34%">$listPegawai.JAWATAN</td>
    <td align="left"   valign="top" width="13%"><img src="../img/edit.gif" border="0" onclick="doDivAjaxCall$formname('div_listaddPegawai$viewPejabat.ID_PEJABAT','editPegawaiSPPK','ID_PEJABAT=$viewPejabat.ID_PEJABAT&amp;JENISPEJ=$viewPejabat.JENIS_PEJ&amp;ID_UNITPSK=$listPegawai.ID_UNITPSK');" /> <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_listaddPegawai$viewPejabat.ID_PEJABAT','showDisplayPegawaiSPPK','ID_PEJABAT=$viewPejabat.ID_PEJABAT&amp;FLAG_DELETE=Y&amp;ID_UNITPSK=$listPegawai.ID_UNITPSK');}"> <img title="Hapus"  src="../img/delete.gif" border="0" /></a></td>
    <!-- <td align="left"   valign="top" width="8%"><img src="../img/delete.gif" border="0"></td>-->
  </tr>
							    #end
  <tr >
    <td align="left"   valign="top" width="19%"></td>
    <td><input type="button" id="BTNTMBHPEG$viewPejabat.ID_PEJABAT2" name="BTNTMBHPEG$viewPejabat.ID_PEJABAT2" value="Tambah Pegawai" onclick="doDivAjaxCall$formname('div_listaddPegawai$viewPejabat.ID_PEJABAT','tambahPegawaiSPPK','ID_PEJABAT=$viewPejabat.ID_PEJABAT&amp;JENISPEJ=$viewPejabat.JENIS_PEJ&amp;ID_UNITPSK=');" /></td>
    <td >&nbsp;</td>
    <td >&nbsp;</td>
  </tr>
                              </table>
							</div>
							</td>
			</tr> 
			<script>	
			document.getElementById("div_listaddPegawai$viewPejabat.ID_PEJABAT").style.display="";	
			</script>
			#else
			<tr id="div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT" >
			<td valign="top" >				
							</td>			
							<td valign="top" >
							Senarai Pegawai
							</td>
							<td valign="top" >
							:
							</td>
							<td>
							<div id="div_listaddPegawai$viewPejabat.ID_PEJABAT">
								<table width="100%" border="0" >
															
									<tr >
									<td >
									- &nbsp;
									<input type="button" id="BTNTMBHPEG$viewPejabat.ID_PEJABAT" name="BTNTMBHPEG$viewPejabat.ID_PEJABAT" value="Tambah Pegawai" onclick="doDivAjaxCall$formname('div_listaddPegawai$viewPejabat.ID_PEJABAT','tambahPegawaiSPPK','ID_PEJABAT=$viewPejabat.ID_PEJABAT');" />
			</td>
									</tr>					
								</table>
							</div>
							</td>
			</tr>
			#end
	#end