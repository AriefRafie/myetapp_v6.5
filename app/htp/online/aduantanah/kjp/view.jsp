<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="idComplaint" value="$!complaint.id">
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT ADUAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
                   <tr>
                      <td width="1%">&nbsp;</td>
                      <td width="28%">No Aduan</td>
                      <td width="1%">:</td>
                      <td width="70%">$!complaint.id </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Status Aduan</td>
                      <td>:</td>
                      <td>$!complaint.status</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Nama Pengadu</td>
                      <td>:</td>
                      <td>$!complaint.namaPengadu </td>
                    </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>Jawatan</td>
			          <td>:</td>
			          <td>$!complaint.jawatanPengadu</td>
			        </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>Emel</td>
			          <td>:</td>
			          <td>$!complaint.emailPengadu </td>
			        </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>No Telefon</td>
			          <td>:</td>
			          <td>$!complaint.phonePengadu </td>
			        </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>Seksyen</td>
			          <td>:</td>
			          <td>$!complaint.seksyenPengadu</td>
			        </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>Pejabat</td>
			          <td>:</td>
			          <td>$!complaint.pejabatPengadu</td>
			        </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>Negeri</td>
			          <td>:</td>
			          <td>$!complaint.negeriPengadu</td>
			        </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>Daerah</td>
			          <td>:</td>
			          <td>$!complaint.daerahPengadu</td>
			        </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>No. Fail</td>
			          <td>:</td>
			          <td>$!complaint.noFail</td>
			        </tr>
			        <tr>
			          <td>&nbsp;</td>
			          <td>Jenis Aduan</td>
			          <td>:</td>
			          <td>$!complaint.type.code -  $!complaint.type.description </td>
			        </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Keterangan Aduan</td>
                      <td>:</td>
                      <td>$!complaint.catatan </td>
                    </tr>
                    <tr>
                      <td></td>
                      <td valign="top"> Lampiran </td>
                      <td valign="top"> : </td>
                      <td colspan="4"><table>
                          #foreach($lampiran in $complaint.lampiran)
                          <tr>
                            <td><a href="javascript:papar_Lampiran('$lampiran.id')" class="style1">$lampiran.fileName</a> </td>
                          </tr>
                          #end
                        </table></td>
                    </tr>
                    <tr>
                    	<td align="center" colspan="5">

                    	</td>


                    </tr>
                    #foreach ( $tanahs in $tanah )
                    <tr>
		    <td colspan="4" align="left">
				<fieldset>
				  <legend><strong>MAKLUMAT TANAH</strong></legend>
					  <table width="100%" border="0" cellspacing="2" cellpadding="2">
							<tr>
							  <td width="1%">&nbsp;</td>
							  <td width="28%">Negeri</td>
							  <td width="1%">:</td>
							  <td width="70%">$!tanahs.nama_negeritanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Daerah</td>
							  <td>:</td>
							  <td>$!tanahs.nama_daerahtanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Mukim</td>
							  <td>:</td>
							  <td>$!tanahs.nama_mukimtanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Seksyen</td>
							  <td>:</td>
							  <td>$!tanahs.nama_seksyentanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Janis Hakmilik</td>
							  <td>:</td>
							  <td>$!tanahs.nama_hakmilik</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Hakmilik</td>
							  <td>:</td>
							  <td>$!tanahs.NO_HAKMILIK</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Jenis PT/ LOT</td>
							  <td>:</td>
							  <td>$!tanahs.nama_lot</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. PT/ LOT</td>
							  <td>:</td>
							  <td>$!tanahs.NO_LOT</td>
							</tr>
					</table>
				</fieldset>
			</td>
		</tr>
		#end

                </table>

                <table width="100%">
                	<tr>
						<td>
						<FIELDSET id="top_upload">
							<legend>JAWAPAN KEPADA PENGADU</legend>
								<TABLE>
									<TR>
										<TD valign="top">
											Jawapan
										</TD>
										<TD valign="top">
											:
										</TD>
										<TD>
											<textarea rows="10" cols="100" name="catatanSelesai" #if($complaint.statusPenyelesaian =="SELESAI") readonly="readonly" #end>$complaint.catatanSelesai</textarea>
										</TD>
									</TR>
									#if($complaint.statusPenyelesaian !="SELESAI")
										#foreach($lampiran in $complaint.jawapanLampiran)
											<tr>
												<td colspan="2">&nbsp;</td>
												<td><a href="javascript:papar_LampiranJawapan('$!lampiran.id')" class="style1">$lampiran.fileName</a>&nbsp;
												<a href="javascript:deleteLampiran('$!lampiran.id')"><img src="../img/validno.png" height="10" width="10" alt="" border="0"/></a></td>
											</tr>
										#end
									<tr>
										<td valign="top">Lampiran</td>
										<td valign="top">:</td>
										<td><input name="lampiran" type="file" id="txtLampiran" size="50" />
											<input type="button" value="Muatnaik" onclick="javascript:simpanLampiran('$!complaint.id')">
										</td>
									</tr>
									#else
									<tr>
										<td>
											Lampiran
										</td>
										<td>
											:
										</td>
										<td>
											<table border="1">
												#foreach($lampiran in $complaint.jawapanLampiran)
							                         <tr>
							                           <td><a href="javascript:papar_LampiranJawapan('$lampiran.id')" class="style1">$lampiran.fileName</a></td>
							                         </tr>
							                        #end
											</table>
										</td>
									</tr>
									#end
									<TR>
										<TD valign="top" align="center" colspan="5">
											<input type="button" value="KEMBALI" onclick="mainPage()">
											<input type="button" value="HANTAR JAWAPAN & TUTUP ADUAN" #if($complaint.statusPenyelesaian =="SELESAI") onclick="notAllowed()" #else onclick="tutupAduan()" #end>
											<input type="button" value="ADUAN PALSU & TUTUP ADUAN" #if($complaint.statusPenyelesaian =="SELESAI") onclick="notAllowed()" #else onclick="aduanPalsu()" #end>
											<input type="button" value="HANTAR E-MEL" onclick="parent.location='mailto:$!complaint.emailPengadu?subject=ADUAN ONLINE NO $complaint.id'"/>
										</TD>
									</TR>
								</TABLE>
							</FIELDSET>
						</td>
					</tr>
                </table>
          </div>
        </div>
      </div></td>
  </tr>
</table>
<!-- <input type="hidden" name="complaintId" value="$!complaint.id"> -->
<input type="hidden" name="idLampiran" value="">

<iframe id="upload_dokumen" name="upload_dokumen" width="0px" height="0px" style="visibility:hidden"></iframe>
<script type="text/javascript">
//alert('$!upload_file');
if('$!upload_file' == "yes"){
	window.location.hash='top_upload';
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>