<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT KPI</legend>
      <table width="100%">
        #foreach($beanMaklumatKPIPelepasan in $BeanMaklumatKPIPelepasan) 
        <tr>
          <td colspan="8">&nbsp;</td>
        </tr>
        <tr class="table_headerkpi">
          <td width="25%"><div align="center"><strong>AKTIVITI <BR>
              (ALIRAN PROSES "TO BE")</strong></div></td>
          <td width="10%"><div align="center"><strong>Sasaran masa menyiapkan satu aktiviti</strong></div></td>
          <td width="10%"><div align="center"><strong>Jumlah sebenar bilangan aktiviti dilakukan</strong></div></td>
          <td width="10%"><div align="center"><strong>Jumlah masa sebenar menyiapkan semua aktiviti</strong></div></td>
          <td width="10%"><div align="center"><strong>Purata masa menyiapkan satu aktiviti</strong></div></td>
          <td width="10%"><div align="center"><strong>Efisensi Aktiviti (Kecekapan)</strong></div></td>
          <td colspan="2" ><div align="center"><strong>KEBERKESANAN - (OUTCOME)</strong></div></td>
        </tr>
        <tr class="table_headerkpi">
          <td><div align="center"><strong>A <br>
              </strong><em>Preset</em></div></td>
          <td><div align="center"><strong>B <br>
              </strong><em>Preset</em></div></td>
          <td><div align="center"><strong>C <br>
              </strong><em>(direct capture)</em></div></td>
          <td><div align="center"><strong>D<br>
              </strong><em>(direct capture)</em></div></td>
          <td><div align="center"><strong>E = D/C <br>
              </strong><em>(compute)</em></div></td>
          <td><div align="center"><strong>F = B/E % <br>
              </strong><em>(compute)</em></div></td>
          <td  width="15%"  ><div align="center"><strong>(Permohonan menunggu) :</strong></div></td>
          <td width="10%"><div align="center"><strong>Bilangan<br>
              </strong><em>(direct capture)</em></div></td>
        </tr>
        <tr >
          <td class="row2">Mendaftar Permohonan <strong>SEHINGGA</strong> menghantar serentak surat meminta Ulasan dari KJP &amp; KJT, Laporan Tanah JKPTG(N) dan Nilaian JPPH.</td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.B1</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.C1</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.D1</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.E1</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.F1</strong></div></td>
          <td  rowspan="7" colspan="2" valign="top"><table width="100%" class="table_headerkpi">
              <tr class="row2">
                <td width="60%"><table width="100%">
                    <tr>
                      <td width="15%"  valign="top">1.</td>
                      <td width="85%">Bil. Permohonan Pelepasan</td>
                    </tr>
                  </table></td>
                <td width="40%" ><div align="center"><strong> $beanMaklumatKPIPelepasan.DITERIMA
                    
                    </strong></div></td>
              </tr>
              <tr class="row2">
                <td width="60%"><table width="100%">
                    <tr>
                      <td width="15%"  valign="top">2.</td>
                      <td width="85%">Bil. Permohonan Pelepasan Diluluskan</td>
                    </tr>
                  </table></td>
                <td width="40%" ><div align="center"><strong> $beanMaklumatKPIPelepasan.DILULUSKAN
                      
                    </strong></div></td>
              </tr>
              <tr class="row2">
                <td width="60%"><table width="100%">
                    <tr>
                      <td width="15%"  valign="top">3.</td>
                      <td width="85%">Bil. Permohonan Pelepasan Ditolak</td>
                    </tr>
                  </table></td>
                <td width="40%" ><div align="center"><strong> $beanMaklumatKPIPelepasan.DITOLAK
                      
                </strong></div></td>
              </tr>
              <tr class="row2">
                <td colspan="2"><table width="100%">
                    <tr>
                      <td width="10%"  valign="top">4.</td>
                      <td width="90%">Permohonan masih dalam proses :-</td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td colspan="2"><table width="100%" class="row1">
                    <tr>
                      <td colspan="2"><table width="100%">
                          <tr>
                            <td width="10%"></td>
                            <td width="5%" valign="top">a.</td>
                            <td width="85%">Menunggu Ulasan dari KJP &amp; KJT, Laporan Tanah JKPTG(N) dan Nilaian JPPH.</td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td width="60%"><div align="right">&lt; $beanMaklumatKPIPelepasan.AM1 hari </div></td>
                      <td bgcolor="#33FF99" width="40%" ><div align="center"> <strong>$M1</strong>
                          <input name="M1" type="hidden" id="M1" size="5" maxlength="5" value="$M1" >
                        </div></td>
                    </tr>
                    <tr>
                      <td><div align="right"> $beanMaklumatKPIPelepasan.AM2 - $beanMaklumatKPIPelepasan.AM3 hari </div></td>
                      <td bgcolor="yellow" ><div align="center"> <strong>$M2</strong>
                          <input name="M2" type="hidden" id="M2" size="5" maxlength="5" value="$M2" >
                        </div></td>
                    </tr>
                    <tr>
                      <td><div align="right">&gt; $beanMaklumatKPIPelepasan.AM4 hari </div></td>
                      <td  bgcolor="red"><div align="center"> <strong>$M3</strong>
                          <input name="M3" type="hidden" id="M3" size="5" maxlength="5" value="$M3" >
                        </div></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td colspan="2"><table width="100%" class="row2">
                    <tr>
                      <td colspan="2"><table width="100%">
                          <tr>
                            <td width="10%"></td>
                            <td width="5%"  valign="top">b.</td>
                            <td width="85%">Menunggu Keputusan dari Menteri Kewangan.</td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td width="60%"><div align="right">&lt; $beanMaklumatKPIPelepasan.BM1
                          
                          hari</div></td>
                      <td  bgcolor="#33FF99" width="40%" ><div align="center"> <strong>$N1</strong>
                          <input name="N1" type="hidden" id="N1" size="5" maxlength="5" value="$N1" >
                        </div></td>
                    </tr>
                    <tr>
                      <td><div align="right">$beanMaklumatKPIPelepasan.BM2 - $beanMaklumatKPIPelepasan.BM3 hari </div></td>
                      <td bgcolor="yellow" ><div align="center"> <strong>$N2</strong>
                          <input name="N2" type="hidden" id="N2" size="5" maxlength="5" value="$N2" >
                        </div></td>
                    </tr>
                    <tr>
                      <td><div align="right">&gt; $beanMaklumatKPIPelepasan.BM4 hari </div></td>
                      <td bgcolor="red" ><div align="center"> <strong>$N3</strong>
                          <input name="N3" type="hidden" id="N3" size="5" maxlength="5" value="$N3" >
                        </div></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td colspan="2"><table width="100%" class="row1">
                    <tr>
                      <td colspan="2"><table width="100%">
                          <tr>
                            <td width="10%"></td>
                            <td width="5%" valign="top">c.</td>
                            <td width="85%">Menunggu Perakuan KSU seterusnya Kelulusan Menteri NRE.</td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td width="60%"><div align="right">&lt; $beanMaklumatKPIPelepasan.CM1 hari </div></td>
                      <td bgcolor="#33FF99" width="40%" ><div align="center"> <strong>$M1</strong>
                          <input name="M1" type="hidden" id="M1" size="5" maxlength="5" value="$M1" >
                        </div></td>
                    </tr>
                    <tr>
                      <td><div align="right">  $beanMaklumatKPIPelepasan.CM2 -  $beanMaklumatKPIPelepasan.CM3 hari </div></td>
                      <td bgcolor="yellow" ><div align="center"> <strong>$M2</strong>
                          <input name="M2" type="hidden" id="M2" size="5" maxlength="5" value="$M2" >
                        </div></td>
                    </tr>
                    <tr>
                      <td><div align="right">&gt;  $beanMaklumatKPIPelepasan.CM4 hari </div></td>
                      <td  bgcolor="red"><div align="center"> <strong>$M3</strong>
                          <input name="M3" type="hidden" id="M3" size="5" maxlength="5" value="$M3" >
                        </div></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="6">Menunggu Ulasan dari KJP &amp; KJT, Laporan Tanah JKPTG(N) dan Nilaian JPPH.</td>
        </tr>
        <tr>
          <td class="row2">Terima lengkap Ulasan KJP &amp; KJT, Laporan Tanah JKPTG(N) dan Nilaian JPPH <strong>SEHINGGA</strong> menghantar Kertas Cadangan kepada Menteri Kewangan.</td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.B2</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.C2</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.D2</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.E2</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.F2</strong></div></td>
        </tr>
        <tr>
          <td colspan="6">Menunggu Keputusan dari Menteri Kewangan.</td>
        </tr>
        <tr>
          <td class="row2">Terima Keputusan Menteri Kewangan <strong>SEHINGGA</strong> Minit Ceraian dihantar ke KSU.</td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.B3</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.C3</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.D3</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.E3</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.F3</strong></div></td>
        </tr>
        <tr>
          <td colspan="6">Menunggu Perakuan KSU seterusnya Kelulusan Menteri NRE.</td>
        </tr>
        <tr>
          <td class="row2">Terima Kelulusan Menteri NRE <strong>SEHINGGA</strong> Borang 12A/12B dihantar kpd Pemohon.</td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.B4</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.C4</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.D4</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.E4</strong></div></td>
          <td class="row2"><div align="center"> <strong>$beanMaklumatKPIPelepasan.F4</strong></div></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
