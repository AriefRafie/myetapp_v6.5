<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<!--<input name="actionPerletakhakan" type="hidden" value="$actionPerletakhakan" id="actionPerletakhakan" />-->
<input name="idFail" type="hidden" value="$idFail" id="idFail"/>
<input name="idHakmilikurusan" type="hidden" value="$idHakmilikurusan" id="idHakmilikurusan" />
<input name="noHakmilik" type="hidden" value="$noHakmilik" id="noHakmilik" />
<!--<input name="hitButton" type="hidden" value="$hitButton" id="hitButton" />-->
<fieldset>
#parse("app/htp/frmPerletakhakanHak.jsp") 
<fieldset>
<legend>SENARAI HAKMILIK</legend>
<table width="100%" border="0">
  <tr class="table_header">
    <td width="1%">Bil</td>
    <td width="10%">Negeri</td>
    <td width="10%">Jenis Hakmilik</td>
    <td width="10%">No Hakmilk</td>
  </tr>
  #foreach ($list in $Listhakmilik)
   #set( $i = $velocityCount )
   #if ( ($i % 2) != 1 ) 
   #set( $row = "row2" ) 
   #else 
   #set( $row = "row1" ) 
   #end
  <tr class="$row">
    <td  width="1%">$list.bil</td>
    #if ($list.bil == "")
    <td  width="10%">$list.noHakmilik</font></td>
    #else
    <td width="10%"><a href="javascript:viewFailById2('$list.idHakmilikurusan','$list.noHakmilik')"><font color="blue">$list.negeri</font></a></td>
    #end
    <td width="10%">$list.jenisHakmilik</td>
    <td width="10%">$list.noHakmilik</td>
  </tr>
  #end
</table>
</fieldset>



  <fieldset>
    
    <legend>PEMILIK/HAKMILIK BARU</legend>
    <table width="100%" border="0" align="center">
  <tr>
    <td width="20%"><div align="right">Nama Asal</div></td>
    <td width="1%">:</td>
    <td width="20%"><label>
      <input name="txtNamaAsal" type="text" class="$inputTextClass" id="txtNamaAsal" style="text-transform:uppercase;" value="$txtNamaAsal" size="45" $readonly/>
    </label></td>
  </tr>
  <tr>
    <td width="20%"><div align="right">Alamat</div></td>
    <td width="1%">:</td>
    <td width="20%"><label><input name="txtAlamat1" type="text" class="$inputTextClass" id="txtAlamat1" style="text-transform:uppercase;" value="$txtAlamat1" size="45" $readonly/>
    </label></td>
  </tr>
  <tr>
  	<td width="20%"><div align="right"></div></td>
    <td width="1%"></td>
    <td width="20%"><label><input name="txtAlamat2" type="text" class="$inputTextClass" id="txtAlamat2" style="text-transform:uppercase;" value="$txtAlamat2" size="45" $readonly/>
    </label></td>
  </tr>
  <tr>
    <td width="20%"><div align="right"></div></td>
    <td width="1%"></td>
    <td width="20%"><label><input name="txtAlamat3" type="text" class="$inputTextClass" id="txtAlamat3" style="text-transform:uppercase;" value="$txtAlamat3" size="45" $readonly/>
    </label></td>
  </tr>
  <tr>
    <td width="20%"><div align="right">Poskod</div></td>
    <td width="1%">:</td>
    <td width="20%"><input name="txtPoskod" type="text" class="$inputTextClass" id="txtPoskod" value="$txtPoskod"  maxlength="5" $readonly /></td>
  </tr>
  <tr>
    <td width="20%"><div align="right">Negeri</div></td>
    <td width="1%">:</td>
    <td width="20%">$selectNegeri</td>
  </tr>
  <tr>
    <td width="20%"><div align="right">Daerah</div></td>
    <td width="1%">:</td>
    <td width="20%">$selectDaerah</td>
  </tr>
  <tr>
    <td width="20%"><div align="right">Bandar/Mukim/Pekan</div></td>
    <td width="1%">:</td>
    <td width="20%">$selectMukim</td>
  </tr>
  <tr>
    <td width="20%"><div align="right">Nama Baru</div></td>
    <td width="1%">:</td>
    <td width="20%"><label>
    <input name="txtNamaBaru" type="text" class="$inputTextClass" id="txtNamaBaru" style="text-transform:uppercase;" value="$txtNamaBaru" size="45" $readonly/>
    </label></td>
  </tr>
</table>
</fieldset>

 <div align="center">
 
       #if ($mode == 'view')
<input type="button" name="btnUpdate2" id="btnUpdate2" value="Kemaskini" onclick="kemaskini()" />
<input type="button" name="btnExit2" id="btnExit2" value="Kembali" onclick="kembali()" />
       
       #end
       #if($mode == 'update')
   <input id="cmdUpdate" name="cmdUpdate" type="button" value="Simpan" onclick="simpanKemas()"/>
   <input type="button" name="btnExit" id="btnExit" value="Kembali" onclick="kembali()" />
     	
       #end
        #if($mode == 'new')
       <input type="button" name="btnSave" id="btnSave" value="Simpan" onclick="simpanPemilikBaru($idHakmilikurusan)" />
<input type="button" name="btnExit3" id="btnExit3" value="Kembali" onclick="kembali()" />
       
         #end</div>
   
</fieldset> 

<script>
function doChangeNegeri(){
	doAjaxCall${formName}("","actionPerletakhakan=papar&nextAction=doChangeNegeri&hitButton=kemaskini");
}
function doChangeDaerah(){
	doAjaxCall${formName}("","actionPerletakhakan=papar&nextAction=doChangeDaerah&hitButton=kemaskini");
}
function viewFailById2(id,noHakmilik){
	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanHakmilikBaru&actionPerletakhakan=papar&hitButton=paparSemula&idHakmilikurusan="+id+"&noHakmilik="+noHakmilik;
	document.${formName}.submit();
	}
function simpanPemilikBaru(id){
	alert(id);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanHakmilikBaru&actionPerletakhakan=papar&hitButton=simpanBru&idHakmilikurusan="+id;
	document.${formName}.submit();

}
</script>