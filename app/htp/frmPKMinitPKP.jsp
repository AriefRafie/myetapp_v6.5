                                <table cellspacing="2" cellpadding="3"
                                       border="0" width="100%">
                                        <tr>
                                                <td width="2%" height="35">&nbsp;</td>
                                                <td width="45%" height="35"
                                                    colspan="4">
                                                        <div align="center">
                                                                &nbsp;<strong><u>
                                                                                MINIT
                                                                                BEBAS
                                                                        </u></strong>
                                                        </div></td>
                                                <td width="10%" height="35">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="156">&nbsp;</td>
                                                <td width="45%" height="156"
                                                    colspan="2">&nbsp;</td>
                                                <td width="15%" height="156">&nbsp;</td>
                                                <td width="20%" height="156">
                                                        &nbsp;
                                                        <tr:inputText label="Ruj. Kami :"
                                                                      binding="#{backing_htp_frmSewaanMinit.itFailSeksyen}"
                                                                      id="itFailSeksyen"
                                                                      columns="25"
                                                                      value="#{backing_htp_frmSewaanMinit.failSeksyen}"
                                                                      readOnly="true"/>
                                                        <tr:inputText label="Tarikh : "
                                                                      binding="#{backing_htp_frmSewaanMinit.itNow}"
                                                                      id="itNow"
                                                                      columns="20"
                                                                      readOnly="true"
                                                                      value="#{backing_htp_frmSewaanMinit.now}"/>
                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                        	<tbody>
                                                        		<tr>
	                                                        		<td nowrap>Ruj. Kami : </td>
	                                                        		<td nowrap valign="top">
	                                                        			<div>$permohonanInfo.fail</div>
	                                                        		</td>
                                                        		</tr>
                                                        		<tr>
	                                                        		<td></td>
	                                                        		<td><span></span></td>
                                                        		</tr>
                                                        	</tbody>
                                                        </table>
                                     					<table border="0" cellpadding="0" cellspacing="0">
                                                        	<tbody>
                                                        		<tr>
	                                                        		<td nowrap>Tarikh : </td>
	                                                        		<td nowrap valign="top">
	                                                        			<div>$util.getDateTime($currentdate, "dd/MM/yyyy")</div>
	                                                        		</td>
                                                        		</tr>
                                                        		<tr>
	                                                        		<td></td>
	                                                        		<td><span></span></td>
                                                        		</tr>
                                                        	</tbody>
                                                        </table>                                              
                                                </td>
                                                <td width="10%" height="156">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%" height="2">&nbsp;</td>
                                                <td width="39%" colspan="4"
                                                    height="2">Y. Bhg. Dato',</td>
                                                <td width="5%" height="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%" height="14">&nbsp;</td>
                                                <td width="39%" colspan="4"
                                                    height="14">&nbsp;</td>
                                                <td width="5%" height="14">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%" height="14">&nbsp;</td>
                                                <td width="39%" colspan="4"
                                                    height="14">Dikemukakan enam (6) salinan &quot;Memorandum of Sublease&quot; dan Form L untuk tandatangan Y. Bhg. Dato'.</td>
                                                <td width="5%" height="14">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%" height="14">&nbsp;</td>
                                                <td width="39%" colspan="4"
                                                    height="14">&nbsp;</td>
                                                <td width="5%" height="14">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%" height="14">&nbsp;</td>
                                                <td width="39%" colspan="4"
                                                    height="14">
                                                        <strong><u>
                                                                        Ringkasan
                                                                        Butiran
                                                                        :-
                                                                </u></strong></td>
                                                <td width="5%" height="14">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%" height="14">&nbsp;</td>
                                                <td width="39%" colspan="4"
                                                    height="14">
                                                        <table cellspacing="3"
                                                               cellpadding="2"
                                                               border="0"
                                                               width="100%">
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(i)</td>
                                                                        <td width="35%"
                                                                            colspan="3"><b> $permohonanInfo.tujuan BAGI </b>

                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(ii)</td>
                                                                        <td width="30%"
                                                                            valign="top">KJP</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">$infoagensi.nama_agensi</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(iii)</td>
                                                                        <td width="30%"
                                                                            valign="top">Pemilik Bangunan</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otNamaPemilik}"
                                                                                               id="otNamaPemilik"
                                                                                               value="Sim Tiang Seng"/>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(iv)</td>
                                                                        <td width="30%"
                                                                            valign="top">Harga Penyewa</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">RM 
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otHargaSewa}"
                                                                                               id="otHargaSewa"
                                                                                               value="#{backing_htp_frmSewaanMinit.harga}"/>
                                                                                 sebulan
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(v)</td>
                                                                        <td width="30%"
                                                                            valign="top">Tempoh Penyewaan</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otTarikhStart}"
                                                                                               id="otTarikhStart"
                                                                                               value="#{backing_htp_frmSewaanMinit.tarikhMula}"/>
                                                                                 hingga 
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otTarikhHabis}"
                                                                                               id="otTarikhHabis"
                                                                                               value="#{backing_htp_frmSewaanMinit.tarikhAkhir}"/>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(vi)</td>
                                                                        <td width="30%"
                                                                            valign="top">Keluasan</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otLuas}"
                                                                                               id="otLuas"
                                                                                               value="#{backing_htp_frmSewaanMinit.luas}"/>
                                                                                 
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otUnitLuas}"
                                                                                               id="otUnitLuas"
                                                                                               value="#{backing_htp_frmSewaanMinit.luasUnit}"/>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(vii)</td>
                                                                        <td width="30%"
                                                                            valign="top">Lokasi</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otAlamat}"
                                                                                               id="otAlamat"
                                                                                               value="#{backing_htp_frmSewaanMinit.lokasi}"/>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(viii)</td>
                                                                        <td width="30%"
                                                                            valign="top">Hakmilik</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(ix)</td>
                                                                        <td width="30%"
                                                                            valign="top">Semakan PUU Kementerian</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otSemakKem}"
                                                                                               id="otSemakKem"/>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;(x)</td>
                                                                        <td width="30%"
                                                                            valign="top">Semakan PUU JKPTG</td>
                                                                        <td width="3%"
                                                                            valign="top">-</td>
                                                                        <td width="62%">
                                                                                <tr:outputText binding="#{backing_htp_frmSewaanMinit.otSemakJKPTG}"
                                                                                               id="otSemakJKPTG"/>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="6%"
                                                                            valign="top">&nbsp;</td>
                                                                        <td width="30%"
                                                                            valign="top">&nbsp;</td>
                                                                        <td width="3%"
                                                                            valign="top">&nbsp;</td>
                                                                        <td width="62%">&nbsp;</td>
                                                                </tr>
                                                        </table>
                                                </td>
                                                <td width="5%" height="14">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%" height="14">&nbsp;</td>
                                                <td width="39%" colspan="4"
                                                    height="14">&nbsp;Memorandum Of Sublease dan Form L adalah teratur. Dikemukakan untuk tandatangan Y. Bhg. Dato'.</td>
                                                <td width="5%" height="14">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td width="39%" colspan="4">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td width="39%" colspan="4">Sekian,
                                                                            terima
                                                                            kasih.</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td width="39%" colspan="4">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%" height="54">&nbsp;</td>
                                                <td width="39%" colspan="4"
                                                    height="54">&nbsp;</td>
                                                <td width="5%" height="54">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td width="39%" colspan="4">
                                                        <strong>(&nbsp;HARUN&nbsp;BIN&nbsp;HJ.&nbsp;SALEH )</strong>
                                                </td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td width="39%" colspan="4">KPP(P)</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td width="39%" colspan="4">SHTP</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td width="39%" colspan="4">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                </table>
    