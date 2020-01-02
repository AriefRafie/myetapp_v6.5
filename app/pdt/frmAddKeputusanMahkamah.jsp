<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<!--<div id="mydiv">-->
<input name="action" type="hidden" id="action" value="$action"/>
<input name="mode" type="hidden" value="$mode" />
<input id="idDokumen" name="idDokumen" value="$!idDokumen" hidden="hidden">
<input id="idLampiran" name="idLampiran" value="$!idLampiran" hidden="hidden">
<input id="hitButton" name="hitButton" hidden="hidden">
&nbsp;
  <fieldset>
  <legend><strong>Keputusan Mahkamah</strong></legend>
  <table width="100%" align="center" border="0" cellpadding="2" cellspacing="2" >
    <tr>
      <td width="29%" align="right" scope="row">Skop / Perkara</td>
      <td width="1%" align="center" scope="row">:</td>
      <td width="70%">
	      <label>
	      #if($idLampiran != '')
	      <select id="txtSkop" name="txtSkop">
					<option value="">SILA PILIH</option>
                    
                    #foreach ( $ruj in $list_TBLRUJSKOP )
                    	#set ( $selected_ruj = "" )
                        #if ( $skop == $ruj.ID )
                        #set ( $selected_ruj = "selected" )
                        #end 
                        
                        <option $selected_ruj value ="$ruj.ID">
                        $ruj.KETERANGAN
                        </option>
                     #end
            </select>
                   <!-- <option value="KANUN TANAH NEGERI - PELUPUSAN">KANUN TANAH NEGERI - PELUPUSAN</option>
					<option value="KANUN TANAH NEGERI - PEMBANGUNAN">KANUN TANAH NEGERI - PEMBANGUNAN</option>
					<option value="KANUN TANAH NEGERI - PERCUKAIAN">KANUN TANAH NEGERI - PERCUKAIAN</option>
					<option value="KANUN TANAH NEGERI - URUSNIAGA / BUKAN URUSNIAGA">KANUN TANAH NEGERI - URUSNIAGA / BUKAN URUSNIAGA</option>
					<option value="KANUN TANAH NEGERI - PELBAGAI">KANUN TANAH NEGERI - PELBAGAI</option>
					<option value="PENGAMBILAN TANAH">PENGAMBILAN TANAH</option>
					<option value="PEMILIKAN STRATA">PEMILIKAN STRATA</option>
					<option value="TANAH PERSEKUTUAN">TANAH PERSEKUTUAN</option>
					<option value="PELANTAR BENUA">PELANTAR BENUA</option>
					<option value="PEMBAHAGIAN PESAKA">PEMBAHAGIAN PESAKA</option>
					<option value="PERIZABAN MELAYU DAN TANAH ADAT">PERIZABAN MELAYU DAN TANAH ADAT</option>
					<!--#set ( $selected_ruj = "")
                    #if($skop == 'TANAH ORANG ASLI')
                    #set ( $selected_ruj = "selected")-->
	        <!--<option value="TANAH ORANG ASLI">TANAH ORANG ASLI</option>
           			<!--#end
                    
                    
					<option value="KAWASAN PENEMPATAN BERKELOMPOK">KAWASAN PENEMPATAN BERKELOMPOK</option>
                    
					<option value="TAFSIRAN STATUT">TAFSIRAN STATUT</option>
					<option value="UMUM">UMUM</option>
			</select>-->
			#end
            
			#if($idLampiran == '')
	        <!--<input name="txtSkop" type="text" id="txtSkop" value="$!skop" size="50" maxlength="100"/>-->
            
            <select id="txtSkop" name="txtSkop">
					<option value="">SILA PILIH</option>
                    
                    #foreach ( $ruj in $list_TBLRUJSKOP )
                    	#set ( $selected_ruj = "" )
                        #if ( $skop == $ruj.ID )
                        #set ( $selected_ruj = "selected" )
                        #end 
                        
                        <option $selected_ruj value ="$ruj.ID">
                        $ruj.KETERANGAN
                        </option>
                     #end
            </select>
            
            #end
	      </label>
      </td>
    </tr>
     <tr>
      <td align="right" scope="row"></td>
      <td scope="row"></td>
      <td>
	      <label>
	      #if($idLampiran == '')
	      	 Contohnya : Pilih Pengambilan Tanah
			#end
			#if($idLampiran != '')
	        #end
	      </label>
      </td>
    </tr>
    
    
     <tr>
      <td align="right" scope="row">Nama Kes</td>
      <td scope="row">:</td>
      <td>
	      <label>
	        <input name="txtNamaKes" type="text" id="txtNamaKes" value="$!namaKes" size="50" maxlength="100"/>
	      </label>
      </td>
    </tr>
    <tr>
      <td align="right" scope="row"></td>
      <td scope="row"></td>
      <td>
	      <label>
	     	Contohnya : Pentadbiran Tanah Gombak
	      </label>
      </td>
    </tr>
      <tr>
      <td align="right" scope="row">No. Ruj. Jurnal Undang-undang / Mahkamah</td>
      <td scope="row">:</td>
      <td>
	      <label>
	        <input name="txtCitation" type="text" value="$!rujMahkamah" id="txtCitation" />
	      </label>
      </td>
    </tr>
     <tr>
      <td align="right" scope="row"></td>
      <td scope="row"></td>
      <td>
	      <label>
	     	No. Ruj. Jurnal Undang-undang : Contohnya [2014]7 CLJ 882 atau <br>
            No. Ruj. Jurnal Mahkamah : Contohnya W-01-220-2010 <br>
            (sekiranya bukan bersumberkan jurnal undang-undang)
	      </label>
      </td>
    </tr>
    <tr>
      <td align="right" scope="row">Tahun</td>
      <td scope="row">:</td>
      <td>
	      <label>
	        <input name="tahun" type="text" value="$!tahun" id="tahun" />
	      </label>
      </td>
    </tr>
      <tr>
      <td align="right" scope="row">Muatnaik Lampiran</td>
      <td scope="row">:</td>
      <td>
	      <label>
	        <input id="fileupload" name="fileupload" type="file" size="40" />
	      </label>
      </td>
    </tr>
       #if($idDokumen != '')
    <tr>
      <td align="right" scope="row">Papar Lampiran</td>
      <td scope="row">:</td>
      <td>
	      <label>
	      	<a href = "javascript:paparDokumen('$idLampiran')" class="style1">[Papar]</a>
	      </label>
      </td>
    </tr>
    #end
    <tr>
      <td colspan="2" align="right" scope="row">&nbsp;</td>
      <td><label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
        </label>
          <label>
          <input type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="batal()" />
        </label></td>
    </tr>
  </table>
</fieldset>
<!--</div>
<script>
getDisableFieldDiv('mydiv','$action');
</script>
<script>
function getDisableFieldDiv(div,mode) {
	
	//alert('masuk div == '+ div);
    var elements = document.getElementById(div);
	//alert('elements == '+ elements);
	var inputElements = elements.querySelectorAll("input, select, checkbox, radio, textarea");
	//alert("inputElements == " + inputElements);
    //var inputTypes = ['text', 'select', 'textarea','input'];
	
	if(mode=='view'){
		for (var i = 0; i < inputElements.length; i++) {
			//alert("t == "+inputElements[i].type);
			var elm = inputElements[i];
			//alert("t == "+inputElements[i].type+" elem : "+elm);
			elm.class = 'disabled';
			elm.disabled = 'disabled'; 
						
		}	
	}
	
}
</script>-->