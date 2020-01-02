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
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">LOG AKTIVITI</li>
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
                      <td >&nbsp;</td>
                      <td >Emel</td>
                      <td>:</td>
                      <td>$!complaint.emailPengadu</td>
                    </tr>
                    <tr>
                      <td >&nbsp;</td>
                      <td>No Telefon</td>
                      <td>:</td>
                      <td>$!complaint.phonePengadu</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Jenis</td>
                      <td>:</td>
                      <td>$!complaint.type.code -  $!complaint.type.description</td>
                    </tr>
                     <tr>
                      <td>&nbsp;</td>
                      <td>Sumber</td>
                      <td>:</td>
                      <td>$!complaint.source.description</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Kandungan</td>
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
  
                </table>
                
                <table width="100%">
                
                	<tr>
					    <td><fieldset>
					      <legend><strong>AGIHAN TUGAS</strong></legend>
					      <table width="100%" border="0" cellspacing="2" cellpadding="2">
					              <tr>
					                <td><table align="center" width="100%">
					                  <tr>
					                      <td colspan="4" scope="row">
					                      
					                      	<input name="cmdDaftarAgih" type="button" value="Agih" #if($complaint.statusPenyelesaian =="SELESAI") onClick="notAllowed()" #else onClick="daftarAgih()" #end/>
					                      </td>
					                    </tr>
					                    <tr class="table_header">
					                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
					                      <td width="15%" align="center"><strong>Tarikh</strong></td>
					                      <td width="15%" align="center"><strong>No Agihan</strong></td>
					                      <td width="10%"><strong>Status</strong></td>
					                       <td width="30%"><strong>Seksyen/Bahagian Bertanggungjawab</strong></td>
					                      <td width="40%"><strong>Arahan</strong></td>
					                    </tr>
					                    #set ($count = 0)
					                    #foreach ( $vectorResponse in $responses)
					                    #set ($count = $count+1)
					                    #set( $i = $velocityCount )
					                    #if ( ($i % 2) != 1 )
					                    #set( $row = "row2" )
					                    #else
					                    #set( $row = "row1" )
					                    #end
					                    <tr>
					                      <td class="$row" align="center">$!count</td>
					                       <td class="$row" align="center">$!vectorResponse.tarikhMasuk</td>
					                      <td class="$row" align="center"><a href="javascript:detailAgihan('$vectorResponse.id')" class="style1 style2">$vectorResponse.id</a></td>
					                      <td class="$row">$!vectorResponse.responseStatus.desc</td>
					                      <td class="$row">$!vectorResponse.tindakan.name</td>
					                      <td class="$row">$!vectorResponse.arahan</td>
					                    </tr>
					                    #end
					                    #if($count == 0)
					                     <tr>
					                     
					                      <td class="$row" colspan="6" align="center">Aduan Masih Belum Diagihkan</td>
					                     
					                    </tr>
					                    #end
					                  </table></td>
					              </tr>
					            </table>
					      </fieldset></td>
					  </tr>
                	
                	
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
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><table align="center" width="100%">
                    <tr class="table_header">
                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                      <td width="15%"><strong>Tarikh</strong></td>
                      <td width="80%"><strong>Aktiviti</strong></td>
                    </tr>
                    #set ($count = 0)
                    #foreach ( $fail in $complaint.logs )
                    #set ($count = $count+1)
                    #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                    #set( $row = "row2" )
                    #else
                    #set( $row = "row1" )
                    #end
                    <tr>
                      <td class="$row" align="center">$!count</td>
                      <td class="$row">$!fail.tarikhMasuk</td>
                      <td class="$row">$!fail.aktiviti</td>
                    </tr>
                    #end
                  </table></td>
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
alert('$!upload_file');
if('$!upload_file' == "yes"){
	window.location.hash='top_upload';	
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>