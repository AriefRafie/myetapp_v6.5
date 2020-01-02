 <p>#set ($IdPihakberkepentingan = "")

                #foreach ( $pemilik in $Pemilik )
                	#set ($IdPihakberkepentingan = $pemilik.IdPihakberkepentingan)
                	#set ($NamaPemaju = $pemilik.Nama)
                    #set ($NoRuj = $pemilik.noRujukan)
                    #set ($Alamat1 = $pemilik.Alamat1)
                    #set ($Alamat2 = $pemilik.Alamat2)
                    #set ($Alamat3 = $pemilik.Alamat3)
                    #set ($Poskod = $pemilik.Poskod)
                    #set ($NoTel = $pemilik.NoTel)
                    #set ($NoFax = $pemilik.NoFax)
                #end

                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end

                #set ($btnNamePemilik = "value='Kosongkan'")
				#if ($IdPihakberkepentingan != "")
					#set ($btnNamePemilik = "value='Batal'")
				#end</p>

<br/>
<fieldset>
<legend><strong>MAKLUMAT TANAH ATAU TANAH &amp; BANGUNAN</strong></legend><div align="left">
                    <table width="100%" border="0">
                        <tr class="table_header">
                            <td scope="col"><strong>Jenis Hakmilik</strong></td>
                          <td scope="col"><strong>No. Hakmilik</strong></td>
                          <td scope="col"><strong>Kod Lot</strong></td>
                          <td scope="col"><strong>No Lot</strong></td>
                          <td scope="col"><strong>Kod Luas</strong></td>
                          <td scope="col"><strong>Luas</strong></td>
                          <td scope="col" style="display:none"><strong>Luas (H)</strong></td>
                          <td scope="col"><strong>No Pelan</strong></td>
                          <td scope="col" style="display:none"><strong>Status</strong></td>
                          <td scope="col"><strong>Tarikh Mula</strong></td>
                          <td scope="col"><strong>Tarikh Luput</strong></td>
                      </tr>
                        #set ($count = 0)
                        #foreach ( $tanah in $Tanah )
                        #set ($count = $count+1)
                          #set( $i = $velocityCount )
                          #if ( ($i % 2) != 1 )
                               #set( $row = "row2" )
                          #else
                               #set( $row = "row1" )
                          #end
                        <tr>
                          <td class="$row" scope="row">$tanah.HakKeterangan</td>
                          <td class="$row"><!-- <a href="javascript:fPM_tambahPemilik('$IdPermohonan','$tanah.IdHakmilikurusan','Maklumat','pemilikview')"> --> $tanah.NoHakmilik <!--</a> --> </td>
                          <td class="$row">$tanah.LotKeterangan</td>
                          <td class="$row">$tanah.NoLot</td>
                          <td class="$row">$tanah.LuasKeterangan</td>
                          <td class="$row">$tanah.Luas</td>
                          <td class="$row" style="display:none">Luas H</td>
                          <td class="$row">$tanah.NoPelan</td>
                          <td class="$row" style="display:none">Status</td>
                          <td class="$row">$tanah.TarikhMula</td>
                          <td class="$row">$tanah.TarikhLuput</td>
                        </tr>
                        #end
                        #if ($count == 0)
                        <tr>
                            <td colspan="11" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                        </tr>
                        #end
                      </table>
                    </div>
                </fieldset>
      			<br/>

<fieldset>
               	   <legend><strong>SENARAI PEMILIK</strong></legend>
                	<div align="left">
                  <table width="100%" border="0">
                        <tr class="table_header">
                            <td width="4%" scope="col"><strong>Bil</strong></td>
                            <td width="47%" scope="col"><strong>No. Hakmilik</strong></td>
                            <td width="49%" scope="col"><strong>Nama Hakmilik</strong></td>
                      </tr>
                        #set ($count = 0)
                        #foreach ( $pemiliklist in $senaraiPemilik )
                        	#set ($NamaPemilik = $pemiliklist.nama)
                			#set ($NoHakMilik = $pemiliklist.noHakmilik)
                            #set ($bil = $pemiliklist.bil)
                        #set ($count = $count+1)
                          #set( $i = $velocityCount )
                          #if ( ($i % 2) != 1 )
                               #set( $row = "row2" )
                          #else
                               #set( $row = "row1" )
                          #end

                          
                        <tr>
                          <td class="$row" scope="row" width="4%">$bil</td>
                          <td class="$row" width="47%"><a href="javascript:fPM_detailsPemilik('$IdPermohonan','$pemiliklist.idHakmilikUrusan','Maklumat','pemilikview')">$NoHakMilik</a></td>
                          <td class="$row" scope="row" width="49%">$NamaPemilik</td>
                        </tr>
                        #end
              
                     
                        
                        #if ($count == 0)
                        <tr>
                            <td colspan="12" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                        </tr>
                        #end
                      </table>
</div>
                </fieldset>
               
        <br/>        
<fieldset>
					<legend><strong>MAKLUMAT PEMILIK</strong></legend><table width="100%" border="0">
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td>No. Hakmilik</td>
              <td>:</td>
              <td>$noHakMilik</td>
            </tr>
            <tr>
              <td width="20%"><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td width="20%"><div align="left">Nama Pemilik</div></td>
              <td width="1%">:</td>
              <td width="59%"><input type="text" name="txtNamaPemaju" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$NamaPemaju" /></td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">Jenis Pengenalan</div></td>
              <td>:</td>
              <td>$selectJenisNoPB</td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">No.</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoRuj" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="80" value="$NoRuj" /></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Alamat</div></td>
              <td>:</td>
              <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat1" /></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td>&nbsp;</td>
              <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat2"/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td>&nbsp;</td>
              <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat3" /></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Poskod</div></td>
              <td>:</td>
              <td><input type="text" name="txtPoskod" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$Poskod"  /></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Negeri</div></td>
              <td>:</td>
              <td>$selectANegeri</td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Daerah</div></td>
              <td>:</td>
              <td>$selectADaerah</td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left">No. Telefon</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$NoTel"/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left">No. Fax</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$NoFax" /></td>
            </tr>

            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"><strong></strong></div></td>
              <td><strong></strong></td>
              <td>&nbsp;</td>
            </tr>
            <tr style="display:$Style3">
              <td colspan="4">
                <div align="center"><strong>
                <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPM_KemaskiniPemilik()">
  &nbsp;&nbsp;
                <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPM_SimpanPemilik()">
  &nbsp;&nbsp;
                <input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fPM_BatalPemilik()">
  &nbsp;&nbsp;
                <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPM_KembaliPemilik()">
              </strong></div></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td><strong></strong></td>
              <td>&nbsp;</td>
            </tr>
    </table>
  </fieldset>
                  
                    <input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
                    <input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">
                    <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
