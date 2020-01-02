  <html>
    <head>
      <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1252"/>
      <title>frmGadaianPeguamMOC</title>
</head>
                <body>
                #set ($RujKami = "")
                #set ($RujTuan = "")
                #set ($Tarikh = "")
                #set ($NamaPeguam = "")
                #set ($Alamat1A = "")
                #set ($Alamat2A = "")
                #set ($Alamat3A = "")
                #set ($PoskodA = "")
                #set ($DaerahA = "")
                #set ($NegeriA = "")
                #foreach ($head in $Header)
                	#set ($RujKami = $head.noRujukanLain)
                    #set ($RujTuan = $head.noFail)
                    #set ($Tarikh = $head.Tarikh)
                    #set ($NamaPeguam = $head.Nama)
                    #set ($Alamat1A = $head.Alamat1)
                    #set ($Alamat2A = $head.Alamat2)
                    #set ($Alamat3A = $head.Alamat3)
                    #set ($PoskodA = $head.Poskod)
                    #set ($DaerahA = $head.namaDaerah)
                    #set ($NegeriA = $head.namaNegeri)
                #end
                
                #set ($Tajuk = "")
                #set ($Nama = "")
                #set ($Jawatan = "")
                #set ($Kementerian = "")
                #set ($Alamat1B = "")
                #set ($Alamat2B = "")
                #set ($Alamat3B = "")
                #set ($PoskodB = "")
                #set ($NegeriB = "")
                #foreach ($content in $Content)
                	#set ($Tajuk = $content.TajukFail)
                	#set ($Nama = $content.Nama)
                	#set ($Jawatan = $content.Jawatan)
                	#set ($Kementerian = $content.NamaKementerian)
                	#set ($Alamat1B = $content.Alamat1)
                	#set ($Alamat2B = $content.Alamat2)
                	#set ($Alamat3B = $content.Alamat3)
                	#set ($PoskodB = $content.Poskod)
                	#set ($NegeriB = $content.NamaNegeri)
                #end
                
                #set ($NoHakmilik = "")
                #set ($NamaMukim = "")
                #set ($NamaDaerah = "")
                #foreach ($hakmilik in $Hakmilik)
                	#set ($NoHakmilik = $hakmilik.NoHakmilik)
                	#set ($NamaMukim = $hakmilik.NamaMukim)
                	#set ($NamaDaerah = $hakmilik.NamaDaerah)
                #end

                #set ($skNama = "")
                #set ($skAlamat1 = "")
                #set ($skAlamat2 = "")
                #set ($skAlamat3 = "")
                #set ($skPoskod = "")
                #set ($skDaerah = "")
                #set ($skNegeri = "")
                #foreach ($footer in $Footer)
                	#set ($skNama = $footer.Nama)
                	#set ($skAlamat1 = $footer.Alamat1)
                	#set ($skAlamat2 = $footer.Alamat2)
                	#set ($skAlamat3 = $footer.Alamat3)
                	#set ($skPoskod = $footer.Poskod)
                    #set ($skDaerah = $footer.NamaDaerah)
                	#set ($skNegeri = $footer.NamaNegeri)
                #end
                                <table cellspacing="2" cellpadding="3"
                                       border="0" width="100%">
                                        <tr>
                                                <td width="2%" height="156">&nbsp;</td>
                                                <td width="45" height="156">&nbsp;</td>
                                                <td width="25%" height="156">&nbsp;</td>
                                                <td width="34%" height="156"><table width="100%" border="0">
                                                  <tr>
                                                    <td>Rujukan Tuan</td>
                                                    <td>:</td>
                                                    <td>$RujTuan</td>
                                                  </tr>
                                                  <tr>
                                                    <td width="40%">Rujukan Kami</td>
                                                    <td width="5%">:</td>
                                                    <td width="55%">$RujKami</td>
                                                  </tr>
                                                  <tr>
                                                    <td>Tarikh</td>
                                                    <td>:</td>
                                                    <td>$Tarikh</td>
                                                  </tr>
                                    </table>                                                </td>
                                                <td width="1%" height="156">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="31">&nbsp;</td>
                                                <td width="45%"
                                                    height="31">
                                                        <strong><em>BERDAFTAR</em></strong></td>
                                                <td width="25%" height="31">&nbsp;</td>
                                                <td width="34%" height="31">&nbsp;</td>
                                                <td width="1%" height="31">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="151">&nbsp;</td>
                                                <td width="45%" height="151"><table width="100%" border="0">
                                                  <tr>
                                                    <td>$NamaPeguam</td>
                                                  </tr>
                                                  <tr>
                                                    <td>$Alamat1A</td>
                                                  </tr>
                                                  #if ($Alamat2A != "")
                                                  <tr>
                                                    <td>$Alamat2A</td>
                                                  </tr>
                                                  #end
                                                  #if ($Alamat3A != "")
                                                  <tr>
                                                    <td>$Alamat3A</td>
                                                  </tr>
                                                  #end
                                                  <tr>
                                                    <td>$PoskodA &nbsp; $DaerahA</td>
                                                  </tr>
                                                  <tr>
                                                    <td><strong>$NegeriA</strong></td>
                                                  </tr>
                                                </table></td>
                                                <td width="25%" height="151">&nbsp;</td>
                                                <td width="34%" height="151">&nbsp;</td>
                                                <td width="1%" height="151">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="2">&nbsp;</td>
                                                <td width="45%" colspan="3" height="2">&nbsp;</td>
                                                <td width="1%" height="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="2">&nbsp;</td>
                                                <td width="45%" colspan="3" height="2">Tuan,</td>
                                                <td width="1%" height="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="14">&nbsp;</td>
                                                <td width="45%" colspan="3" height="14">
                                                    <table cellspacing="3"
                                                           cellpadding="2"
                                                           border="0"
                                                           width="100%">
                                                            <tr>
                                                               <td>$Tajuk</td>
                                                            </tr>
                                                            <tr>
                                                               <td>
                                                                   <table cellspacing="2" cellpadding="3" width="100%">
                                                                      <tr>
                                                                            <td width="17%">Hakmilik </td>
                                                                            <td width="2%" align="center">:</td>
                                                                            <td width="81%">$NoHakmilik $NamaMukim $NamaDaerah</td>
                                                                      </tr>
                                                                      <tr>
                                                                            <td width="17%">Nama Peminjam </td>
                                                                            <td width="2%" align="center">:</td>
                                                                            <td width="81%">$Nama</td>
                                                                      </tr>
                                                                   </table>                                                               </td>
                                                          </tr>
                                                          <tr>
                                                            <td><hr width="100%" size="2" /></td>
                                                          </tr>
                                                    </table>                                                </td>
                                                <td width="1%" height="14">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="14">&nbsp;</td>
                                                <td width="45%" colspan="3"
                                                    height="14">&nbsp;</td>
                                                <td width="1%" height="14">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="2">&nbsp;</td>
                                                <td width="45%" colspan="3"
                                                    height="2">
                                                        Dengan hormatnya saya
                                                        diarah merujuk kepada perkara
                                                        di atas. Bersama-sama
                                                        ini dikembalikan <em>Memorandum of Change </em>beserta salinan yang telah ditandatangani oleh Persuruhjaya Tanah Persekutuan untuk tindakan pendaftaran.</td>
                                          <td width="1%" height="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                          <td height="2">&nbsp;</td>
                                          <td colspan="3"
                                                    height="2">&nbsp;</td>
                                          <td height="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="2">&nbsp;</td>
                                                <td width="45%" colspan="3"
                                                    height="2">2. &nbsp;&nbsp;Setelah selesai tindakan pendaftaran, sila kembalikan pendua <em>Memorandum of Change</em> serta Hakmilik Asal <strong><u>TERUS</u></strong> ke alamat yang berikut :</td>
                                                <td width="1%" height="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="2">&nbsp;</td>
                                                <td width="45%" colspan="3"
                                                    height="2">
                                                        <table cellspacing="3"
                                                               cellpadding="2"
                                                               border="0"
                                                               width="100%">
                                                                <tr>
                                                                        <td width="5%" height="29">&nbsp;</td>
                                                                        <td width="5%"
                                                                            valign="top">&nbsp;</td>
                                                                        <td width="90%">$Jawatan</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="5%">&nbsp;</td>
                                                                        <td width="5%"
                                                                            valign="top">&nbsp;</td>
                                                                        <td width="90%">$Kementerian</td>
                                                                </tr>
                                                                <!-- <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            valign="top">&nbsp;</td>
                                                                  <td>$Kementerian</td>
                                                                </tr> -->
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            valign="top">&nbsp;</td>
                                                                  <td>$Alamat1B</td>
                                                                </tr>
                                                                #if($Alamat2B != "")
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            valign="top">&nbsp;</td>
                                                                  <td>$Alamat2B</td>
                                                                </tr>
                                                                #end
                                                                #if($Alamat3B != "")
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            valign="top">&nbsp;</td>
                                                                  <td>$Alamat3B</td>
                                                                </tr>
                                                                #end
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            valign="top">&nbsp;</td>
                                                                  <td>$PoskodB $NegeriB</td>
                                                                </tr>
                                                                <!--<tr>
                                                                        <td width="5%">&nbsp;</td>
                                                                        <td width="5%"
                                                                            valign="top">&nbsp;</td>
                                                                        <td width="90%">$NegeriB</td>
                                                                </tr> -->
                                                        </table>                                                </td>
                                                <td width="1%" height="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="2">&nbsp;</td>
                                                <td width="45%" colspan="3"
                                                    height="2">&nbsp;</td>
                                                <td width="1%" height="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">Sekian dimaklumkan,
                                                                            terima
                                                kasih.</td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">&nbsp;</td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3"><strong>&quot;BERKHIDMAT
                                                                UNTUK NEGARA&quot;</strong> </td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3"></td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                          <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">Saya
                                                                            yang
                                                                            menurut
                                                perintah,</td>
                                          <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                          <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">&nbsp;</td>
                                          <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">&nbsp;</td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%" height="25">&nbsp;</td>
                                                <td width="45%" colspan="3"
                                                    height="25">&nbsp;</td>
                                                <td width="1%" height="25">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">
                                                        <strong>( HARUN BIN HJ. SALEH)</strong>                                                </td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">b.p.
                                                                            Pesuruhjaya Tanah
                                                                            Persekutuan</td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">Jabatan
                                                                            Ketua
                                                                            Pengarah
                                                                            Tanah
                                                                            dan
                                                                            Galian (Persekutuan).</td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                          <td>&nbsp;</td>
                                          <td colspan="3">emel : harunsalleh@kptg.gov.my</td>
                                          <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">tel : 03-88712608 / 2638                                                </td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">&nbsp;</td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">s.k :</td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">
                                                        <table cellspacing="2"
                                                               cellpadding="3"
                                                               border="0"
                                                               width="100%">
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td width="85%"
                                                                            colspan="2">$Jawatan                                                                        </td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td width="15%"
                                                                            colspan="2">$Kementerian</td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                <!--<tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td width="15%"
                                                                            colspan="2">$Kementerian</td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr> -->
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td width="15%"
                                                                            colspan="2">$Alamat1B</td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                #if ($Alamat2B != "")
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">$Alamat2B</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                #end
                                                                #if ($Alamat3B != "")
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">$Alamat3B</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                #end
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td width="15%"
                                                                            colspan="2"><strong><u>$PoskodB $NegeriB</u></strong></td>
                                                                  <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                <!-- <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">$NegeriB</td>
                                                                  <td>&nbsp;</td>
                                                                </tr> -->
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">&nbsp;</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">&nbsp;</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">$skNama</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">$skAlamat1</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                #if ($skAlamat2 != "")
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">$skAlamat2</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                #end
                                                                #if ($skAlamat3 != "")
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">$skAlamat3</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                #end
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">$skPoskod $skDaerah</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2"><strong><u>$skNegeri</u></strong></td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                  <td>&nbsp;</td>
                                                                  <td
                                                                            colspan="2">&nbsp;</td>
                                                                  <td>&nbsp;</td>
                                                                </tr>
                                                        </table>                                                </td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="45%" colspan="3">&nbsp;</td>
                                                <td width="1%">&nbsp;</td>
                                        </tr>
                                </table>
</body>
  </html>