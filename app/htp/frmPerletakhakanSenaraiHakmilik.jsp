<strong>HAKMILIK PERLETAKHAKAN</strong><br>
<br>
<fieldset>
<form name="f1" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
   <tbody>
   
      <tr>
		<td>
			#parse ("app/htp/frmPerletakhakanInfo.jsp")
		</td>
      </tr>
      
      <tr>
        <td colspan="2"></td>
      </tr>
      
    </tbody>
  </table>
  <input type="hidden" name="command">
</form>
</fieldset>

<fieldset>

<div id="TabbedPanels1" class="TabbedPanels">
	<ul class="TabbedPanelsTabGroup">
    	<li class="TabbedPanelsTab" tabindex="3" title="senaraiHakmilik"><strong><font size="1">Senarai Hakmilik</font></strong></li>
    	<li class="TabbedPanelsTab" tabindex="2" title="Hakmilik"><strong><font size="1">Hakmilik</font></strong></li>
        <li class="TabbedPanelsTab" tabindex="1" title="namaPemilikAsal"><strong><font size="1">Nama Pemilik Asal</font></strong></li>
        <li class="TabbedPanelsTab" tabindex="0" title="UrusanAtasHakmilik"><strong><font size="1">Urusan Atas Hakmilik</font></strong></li>
    </ul>
    
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
    	<form name="tbh" method="post">
  			<input name="add" value="Tambah Hakmilik" type="button" onClick="Tambah()">
    		<input type="hidden" name="command">
		</form>
		<form name="f2" method="post">
 			<table border="1" cellpadding="0" cellspacing="0" width="100%">
    			<tbody>
      				<tr class="table_header">
        				<td align="center"><b>Negeri</b></td>
       					<td align="center"><strong>Daerah</strong></td>
        				<td align="center"><strong>Mukim/Bandar/Pekan</strong></td>
        				<td align="center"><strong>Jenis Hakmilik</strong></td>
        				<td align="center"><b>No. Hakmilik</b></td>
      				</tr>
      				#foreach ( $fail in $Senaraifail )
      				<tr>
        				<td>$fail.negeri</td>
        				<td>$fail.daerah</td>
        				<td>$fail.mukim</td>
        				<td>$fail.jenishakmilik</td>
        				<td>$fail.nohakmilik</td>
      				</tr>
      				#end
    			</tbody>
  			</table>
  			<input type="hidden" name="idFail">
  			<input type="hidden" name="noFail">
  			<input type="hidden" name="command">
		</form>
		</div>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        <div class="TabbedPanelsContent">
        
        </div>
        
        <div class="TabbedPanelsContent">
        #set ($IdPihakberkepentingan = "")
                #set ($IdBebanan = "")
               	#set ($Nama = "")
                #set ($Alamat1 = "")
                #set ($Alamat2 = "")
                #set ($Alamat3 = "")
                #set ($Poskod = "")
                #set ($NoTel = "")
                #set ($NoFax = "")
                #set ($NoPerserahan = "")
                #foreach ( $hakmilik in $Hakmilik )
                	#set ($IdPihakberkepentingan = $hakmilik.IdPihakberkepentingan)
                    #set ($IdBebanan = $hakmilik.IdBebanan)
                	#set ($Nama = $hakmilik.Nama)
                    #set ($Alamat1 = $hakmilik.Alamat1)
                    #set ($Alamat2 = $hakmilik.Alamat2)
                    #set ($Alamat3 = $hakmilik.Alamat3)
                    #set ($Poskod = $hakmilik.Poskod)
                    #set ($NoTel = $hakmilik.NoTel)
                    #set ($NoFax = $hakmilik.NoFax)
                    #set ($NoPerserahan = $hakmilik.NoPerserahan)
                #end
                
                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end
                <form name="hakmilik" method="post">
                <table width="100%" border="0">
                          <tr>
                            <td width="35%"><div align="right"><strong>Nama :</strong></div></td>
                            <td width="65%"><input type="text" name="txtNama" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$Nama" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>Alamat :</strong></div></td>
                            <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat1" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat2" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat3" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>Poskod :</strong></div></td>
                            <td><input type="text" name="txtPoskod" size="15" maxlength="5" value="$Poskod" $mode /></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>Daerah :</strong></div></td>
                            <td>$selectADaerah</td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>Negeri :</strong></div></td>
                            <td>$selectNegeri
                            <input type="hidden" name="idANegeri" value="$idNegeri" /></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>No. Telefon :</strong></div></td>
                            <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$NoTel" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>No. Fax :</strong></div></td>
                            <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$NoFax" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>Gadaian Pendua No. Perserahan :</strong></div></td>
                            <td><input type="text" name="txtNoPerserahan" onkeyup="this.value=this.value.toUpperCase();" size="40" maxlength="50" value="$NoPerserahan" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="KemaskiniHakmilik()">&nbsp;&nbsp;<input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="SimpanHakmilik()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal" onclick="BatalHakmilik()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliHakmilik()"></div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>
                    <input type="hidden" name="idBebanan" value="$IdBebanan">
                    <input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">
                    <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                    <input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="noFail" value="$NoFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
  					<input type="hidden" name="command">
                  <input type="hidden" name="mode">
                  </form>
        </div>
        
        <div class="TabbedPanelsContent">
        
        </div>
	</div>
</div>
</fieldset>

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
//-->
</script>