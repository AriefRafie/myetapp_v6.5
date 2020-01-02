<table border="0" cellpadding="3" cellspacing="2" width="100%">
        <tbody>
        <tr>
		<td height="156" width="2%">&nbsp;</td>
		<td colspan="2" height="156" width="45%">&nbsp;</td>
		<td height="156" width="15%">&nbsp;</td>
		<td height="156" width="20%">
                	<table id="form1:itFailLain__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0">
                        	<tbody>
				<tr>
				<td class="af_inputText_label" nowrap="nowrap">
					Ruj. Tuan : 
					</td>
					<td class="AFContentCell" nowrap="nowrap" valign="top">$permohonanInfo.rujukanlain
						<div id="form1:itFailLain" class="af_inputText_content"></div>
					</td>
				</tr>
				<tr>
				<td></td>
				<td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td>
				</tr>
				</tbody>
			</table>
			<table id="form1:itFailSeksyen__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody>
				<tr>	<td class="af_inputText_label" nowrap="nowrap">Ruj. Kami :$permohonanInfo.fail</td>
					<td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itFailSeksyen" class="af_inputText_content"></div></td>
				</tr>
				<tr>	<td></td>
					<td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td>
				</tr>
			</tbody></table>
			<table id="form1:itNow__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody>
				<tr>	<td class="af_inputText_label" nowrap="nowrap">Tarikh : </td>
					<td nowrap="nowrap" valign="top"><div id="form1:itNow">$util.getDateTime($currentdate, "dd/MM/yyyy")</div></td>
				</tr>
				<tr>	<td></td>
					<td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td>
				</tr>
			</tbody></table>
			
		</td>
                                                <td height="156" width="10%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="151" width="3%">&nbsp;</td>
                                                <td colspan="2" height="151" width="39%">
                                                        <table id="form1:itJawatan__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itJawatan" class="af_inputText_content">
                                                        $infokementerian.jawatan
                                                        </div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                        <table id="form1:itKementerian__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itKementerian" class="af_inputText_content">
                                                        $infokementerian.nama_kementerian
                                                        </div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                        <table id="form1:itAlamat1__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itAlamat1" class="af_inputText_content">
                                                        $infokementerian.alamat1
                                                        </div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                        <table id="form1:itAlamat2__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itAlamat2" class="af_inputText_content">
                                                        $infokementerian.alamat2
                                                        </div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                        <table id="form1:itAlamat3__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itAlamat3" class="af_inputText_content">
                                                        $infokementerian.alamat3
                                                        </div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                        <span id="form1:otPoskod">$infoagensi.poskod</span>
                                                        <span id="form1:otDaerah"></span>
                                                        <table id="form1:itNegeri__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itNegeri" class="af_inputText_content">
                                                        $infonegeri.nama_negeri
                                                        </div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                </td>
                                                <td height="151" width="10%">&nbsp;</td>
                                                <td height="151" width="33%">&nbsp;</td>
                                                <td height="151" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="7" width="3%">&nbsp;</td>
                                                <td colspan="4" height="7" width="39%">
                                                        <table id="form1:inputText3411__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap">u.p :</td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:inputText3411" class="af_inputText_content">En. Muhammad Faizarudin Bin Jamaludin</div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                </td>
                                                <td height="7" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="2" width="3%">&nbsp;</td>
                                                <td colspan="4" height="2" width="39%">&nbsp;</td>
                                                <td height="2" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="2" width="3%">&nbsp;</td>
                                                <td colspan="4" height="2" width="39%">Tuan,</td>
                                                <td height="2" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="14" width="3%">&nbsp;</td>
                                                <td colspan="4" height="14" width="39%">
                                                <b>PERJANJIAN PAJAKAN KECIL BAGI MAKSUD SEWAAN BARU BAGI $permohonanInfo.tujuan<br>
                                                </b>
                                                        
                                                </td>
                                                <td height="14" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="2" width="3%">&nbsp;</td>
                                                <td colspan="4" height="2" width="39%">&nbsp;</td>
                                                <td height="2" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="2" width="3%">&nbsp;</td>
                                                <td colspan="4" height="2" width="39%">
                                                        <span id="form1:outputText1">Dengan hormatnya saya merujuk surat tuan dalam </span>
                                                        <span id="form1:otRujFailLain"></span>
                                                        <span id="form1:outputText2">mengenai perkara diatas.</span>
                                                </td>
                                                <td height="2" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="2" width="3%">&nbsp;</td>
                                                <td colspan="4" height="2" width="39%">&nbsp;</td>
                                                <td height="2" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="2" width="3%">&nbsp;</td>
                                                <td colspan="4" height="2" width="39%">2. Dimaklumkan
                                                               bahawa Perjanjian
                                                               Pajakan Kecil
                                                               telah diterima
                                                               oleh Jabatan ini.
                                                               Walau
                                                               bagaimanapun
                                                               perjanjian
                                                               tersebut tidak
                                                               dapat
                                                               ditandatangani
                                                               oleh Pesuruhjaya
                                                               Tanah Persekutuan
                                                               kerana
                                                               perkara-perkara
                                                               berikut yang
                                                               tidak disertakan:-</td>
                                                <td height="2" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="128" width="3%">&nbsp;</td>
                                                <td colspan="4" height="128" width="39%">
                                                        <table border="0" cellpadding="2" cellspacing="3" width="100%">
                                                                <tbody><tr>
                                                                        <td width="2%">&nbsp;</td>
                                                                        <td align="right" valign="top" width="5%">a)</td>
                                                                        <td width="93%">surat
                                                                                        pengakuan
                                                                                        daripada
                                                                                        Ketua
                                                                                        Jabatan
                                                                                        tuan
                                                                                        menyatakan
                                                                                        perjanjian
                                                                                        yang
                                                                                        digunapakai
                                                                                        adalah
                                                                                        Perjanjian
                                                                                        Seragam;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="2%">&nbsp;</td>
                                                                        <td align="right" valign="top" width="5%">&nbsp;</td>
                                                                        <td width="93%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="2%">&nbsp;</td>
                                                                        <td align="right" valign="top" width="5%">b)</td>
                                                                        <td width="93%">salinan
                                                                                        surat
                                                                                        kelulusan
                                                                                        penyewaan
                                                                                        daripada
                                                                                        Setiausaha
                                                                                        Persekutuan
                                                                                        Sarawak,
                                                                                        dan;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="2%">&nbsp;</td>
                                                                        <td align="right" valign="top" width="5%">&nbsp;</td>
                                                                        <td width="93%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="2%">&nbsp;</td>
                                                                        <td align="right" valign="top" width="5%">c)&nbsp;</td>
                                                                        <td width="93%">tiga
                                                                                        (3)
                                                                                        salinan
                                                                                        borang
                                                                                        Memorandum
                                                                                        of
                                                                                        Sublease
                                                                                        (Form
                                                                                        L}
                                                                                        yang
                                                                                        telah
                                                                                        ditandatangani
                                                                                        oleh
                                                                                        pemilik
                                                                                        bangunan
                                                                                        sekiranya
                                                                                        tempoh
                                                                                        penyewaan
                                                                                        ini
                                                                                        melebihi
                                                                                        tiga
                                                                                        (3)
                                                                                        tahun.</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="2%">&nbsp;</td>
                                                                        <td align="right" valign="top" width="5%">&nbsp;</td>
                                                                        <td width="93%">&nbsp;</td>
                                                                </tr>
                                                        </tbody></table>
                                                </td>
                                                <td height="128" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">3.
                                                                            Bersama-sama
                                                                            ini
                                                                            dikembalikan
                                                                            semula
                                                                            empat
                                                                            (4)
                                                                            salinan
                                                                            Perjanjian
                                                                            tersebut
                                                                            untuk
                                                                            diisi
                                                                            di
                                                                            Lampiran
                                                                            'A'
                                                                            dan
                                                                            sila
                                                                            sertakan
                                                                            pelan
                                                                            tapak
                                                                            bersama-sama
                                                                            dengan
                                                                            bangunan
                                                                            yang
                                                                            hendak
                                                                            disewa
                                                                            bersama
                                                                            perjanjian.
                                                                            Sila
                                                                            kemukakan
                                                                            Perjanjian
                                                                            Pajakan
                                                                            yang
                                                                            lengkap
                                                                            bersama
                                                                            perkara-perkara
                                                                            seperti
                                                                            di
                                                                            para
                                                                            2
                                                                            surat
                                                                            ini
                                                                            untuk
                                                                            ditandatangani
                                                                            oleh
                                                                            Pesuruhjaya
                                                                            Tanah
                                                                            Persekutuan.</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">4.
                                                                            Jabatan
                                                                            ini
                                                                            tidak
                                                                            bertanggungjawab
                                                                            diatas
                                                                            kelewatan
                                                                            menandatangani
                                                                            Perjanjian
                                                                            sekiranya
                                                                            permohonan
                                                                            yang
                                                                            dikemukakan
                                                                            tidak
                                                                            lengkap.
                                                                            Walau
                                                                            bagaimanapun
                                                                            bersama-sama
                                                                            ini
                                                                            juga
                                                                            dikemukakan
                                                                            perkara-perkara
                                                                            yang
                                                                            perlu
                                                                            disertakan
                                                                            semasa
                                                                            mengemukakan
                                                                            Perjanjian
                                                                            Pajakan
                                                                            Kecil
                                                                            ke
                                                                            Jabatan
                                                                            ini
                                                                            bagi
                                                                            mempercepatkan
                                                                            proses
                                                                            menandatangani
                                                                            Perjanjian
                                                                            Pajakan
                                                                            Kecil.</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">Sekian,
                                                                            terima
                                                                            kasih.</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">
                                                        <strong>"BERKHIDMAT
                                                                UNTUK NEGARA"</strong>
                                                </td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">Saya
                                                                            yang
                                                                            menurut
                                                                            perintah,</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td height="54" width="3%">&nbsp;</td>
                                                <td colspan="4" height="54" width="39%">&nbsp;</td>
                                                <td height="54" width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">
                                                        <strong>( HARUN HJ.
                                                                SALLEH )</strong>
                                                </td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">b.p.
                                                                            Ketua
                                                                            Pengarah
                                                                            Tanah
                                                                            dan
                                                                            Galian
                                                                            Persekutuan</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">Jabatan
                                                                            Ketua
                                                                            Pengarah
                                                                            Tanah
                                                                            dan
                                                                            Galian</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">
                                                        <table id="form1:itPegawaiEmail__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap">Email : </td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itPegawaiEmail" class="af_inputText_content">harunsaleh@kptg.gov.my</div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                        <table id="form1:itPegawaiTelefon__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap">Telefon : </td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itPegawaiTelefon" class="af_inputText_content">03-8871 2612</div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                </td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">s.k :</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">
                                                        <table border="0" cellpadding="3" cellspacing="2" width="100%">
                                                                <tbody><tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td colspan="2" width="85%">
                                                                                <table id="form1:itSKJawatan__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itSKJawatan" class="af_inputText_content"></div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                                <table id="form1:itSKKementerian__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itSKKementerian" class="af_inputText_content"></div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                                <table id="form1:itSKAlamat1__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itSKAlamat1" class="af_inputText_content"></div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                                <table id="form1:itSKAlamat2__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itSKAlamat2" class="af_inputText_content"></div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                                <table id="form1:itSKAlamat3__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itSKAlamat3" class="af_inputText_content"></div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                                <span id="form1:otSKPoskod"></span>
                                                                                <span id="form1:otSKDaerah"></span>
                                                                                <table id="form1:itSKNegeri__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:itSKNegeri" class="af_inputText_content"></div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                        </td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td colspan="2" width="15%">
                                                                                <table id="form1:inputText6431__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap">u.p :</td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:inputText6431" class="af_inputText_content">Puan Wan Azilawati Binti Wan Mahmood</div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                        </td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td colspan="2" width="15%">&nbsp;</td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td colspan="2" width="15%">&nbsp;</td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td colspan="2" width="15%">
                                                                                <table id="form1:inputText631__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:inputText631" class="af_inputText_content">Setiausaha Persekutuan Sarawak</div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                                <table id="form1:inputText6311__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:inputText6311" class="af_inputText_content">Pejabat Setiausaha Persekutuan Sarawak</div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                                <table id="form1:inputText6312__xc_" class="af_inputText p_AFReadOnly" summary="" border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="af_inputText_label" nowrap="nowrap"></td><td class="AFContentCell" nowrap="nowrap" valign="top"><div id="form1:inputText6312" class="af_inputText_content">Tingkat 17, Bangunan Sultan Iskandar</div></td></tr><tr><td></td><td class="AFComponentMessageCell"><span class="OraInlineErrorText"></span></td></tr></tbody></table>
                                                                        </td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                        <td width="7%">&nbsp;</td>
                                                                        <td colspan="2" width="15%">&nbsp;</td>
                                                                        <td width="8%">&nbsp;</td>
                                                                </tr>
                                                        </tbody></table>
                                                </td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td colspan="4" width="39%">&nbsp;</td>
                                                <td width="5%">&nbsp;</td>
                                        </tr>
                                </tbody></table>