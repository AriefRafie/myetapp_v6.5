<fieldset>



##parse ("app/htp/frmMaklumatPendaftaranPermohonan.jsp")
	#parse ("app/htp/frmPajakanKecilInfo.jsp")			



<fieldset><legend><strong>Syarikat Terlibat</strong></legend>

<table width="100%" border="0">

                #set($jenisPB = "")
                #set($nama = "")
                #set($notel = "")
                #set($nofax = "")
                #set($alamat1 = "")
                #set($alamat2 = "")
                #set($alamat3 = "")
                #set($poskod = "")
                #set($selectNegeri = $socNegeri)
                #set($idPemaju = "")
                #foreach ($maklumatSyarikat in $MaklumatSyarikat)
                		#set($jenisPB = $maklumatSyarikat.jenisPB)
                        #set($nama = $maklumatSyarikat.nama)
                        #set($notel = $maklumatSyarikat.notel)
                        #set($nofax = $maklumatSyarikat.nofax)
                        #set($alamat1 = $maklumatSyarikat.alamat1)
                        #set($alamat2 = $maklumatSyarikat.alamat2)
                        #set($alamat3 = $maklumatSyarikat.alamat3)
                        #set($poskod = $maklumatSyarikat.poskod)
                        #set($selectNegeri = $socNegeri1)
                        #set($idPemaju = $maklumatSyarikat.idPemaju)
                #end
                
                
                

  <tr>
    <td width="22%" height="31%"><div align="right">Jenis/No. PB</div></td>
    <td width="259"><input name="txtjenisPB" type="text" id="txtjenisPB" value="$!jenisPB" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" /></td>
    <td width="16%"><div align="right">Alamat</div></td>
    <td width="31%"><label>
      <input name="txtalamat1" type="text" id="txtalamat1" value="$!alamat1" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();"  />
    </label></td>
  </tr>
  <tr>
  <tr>
    <td><div align="right">Nama</div></td>
    <td><label>
      <input name="txtnama" type="text" id="txtnama" value="$!nama" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
    </label></td>
    <td><div align="right"></div></td>
    <td><label>
      <input name="txtalamat2" type="text" id="txtalamat2" value="$!alamat2" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
    </label></td>
  </tr>
  <tr>
    <td><div align="right">No. Telefon</div></td>
    <td><label>
      <input name="txtnotel" type="text" id="txtnotel" value="$!notel" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
    </label></td>
    <td><div align="right"></div></td>
    <td><label>
      <input name="txtalamat3" type="text" id="txtalamat3" value="$!alamat3" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();"  />
    </label></td>
  </tr>
  <tr>
    <td><div align="right">No. Fax</div>      <label></label></td>
    <td><label>
      <input name="txtnofax" type="text" id="txtnofax" value="$!nofax" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
    </label></td>
    <td><div align="right">Poskod</div></td>
    <td><label><input name="txtposkod" type="text" id="txtposkod" value="$!poskod" $readonly class="$inputTextClass" />
    </label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><div align="right">Negeri</div></td>
    <td><label>$selectNegeri</label></td>
   <!--<input name="txtnegeri" type="text" id="txtnegeri" value="$!selectNegeri" /> -->
  </tr>
  <tr>
    <td>&nbsp;</td>
    ##<td>$!mode</td>
    <td><div align="right"></div></td>
    <td>&nbsp;</td>
  </tr>
</table>
           
</tr>
      
</fieldset>
</fieldset>

</fieldset>

<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_negeri" value="$selectNegeri"/>
<script>



function reset() {
	doAjaxCall${formName}("reset");
	
}



</script>