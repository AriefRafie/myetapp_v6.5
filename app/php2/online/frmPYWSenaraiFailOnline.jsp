<style type="text/css">

.style1 {
	color: #0033FF
}

</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'/>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input type="hidden" name="actionPenyewaan" />
  <input type="hidden" name="idStatus" />
  <input type="hidden" name="idFail" />
  <input type="hidden" name="idPemohon" />
  <input type="hidden" id="namamodul" name="namamodul"  >
  <input type="hidden" id="namatab" name="namatab"  >
  <input type="hidden" name="initiateFlagBuka" id="initiateFlagBuka"/>
</p>

<div id="divMainForm">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
        <legend><b>CARIAN</b></legend>
        <table width="100%" align="center" border="0">
          <tr>
            <td width="30%" height="24" scope="row" align="right">No Permohonan : </td>
            <td width="70%"><input name="findNoFail" id="findNoFail" type="text" value="$!findNoFail" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right">No Hakmilik : </td>
            <td width="70%"><input name="findNoHakmilik" id="findNoHakmilik" type="text" value="$!findNoHakmilik" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right">No Lot : </td>
            <td width="70%"><input name="findNoLot" id="findNoLot" type="text" value="$!findNoLot" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
          	<td>&nbsp</td>
        	<td>
        		<input type="button" name="cari" id="cari" value="Cari" onclick="javascript:carian();" />
  				<input type="button" name="kosongkan" id="kosongkan" value ="Kosongkan" onClick="javascript:findKosongkan();"/>
  				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembali();"/>
            </td>
        </tr>
        </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset> 
      <legend><b>SENARAI PERMOHONAN PENYEWAAN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
      <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Daftar Permohonan Baru" onclick="javascript:daftarBaru()"/></td>
        <tr class="table_header">
          <td scope="row" width="2%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Permohonan</strong></td>
          <td width="8%" align="center"><strong>Tarikh Permohonan</strong></td>
          <td width="30%" align="center"><strong>Tujuan</strong></td>
          <td width="15%"><strong>Status</strong></td>
        </tr>
        
        #set ($list = "")
        #if ($status_PermohonanSewa.size() > 0)
        #foreach ($list in $status_PermohonanSewa)
        
        #if ($list.bil == '')
        	#set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        	#set( $row = "row1" )
        #else 
        	#set( $row = "row2" )
        #end
        
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row">
          <!-- <a id="hoverover" style="cursor:pointer; color:#0000FF" onClick="ShowPopup(this,$list.bil);" title="Klik untuk maklumat lengkap">$list.NO_PERMOHONAN</a>-->
          <a href="javascript:ShowInfo('$list.ID_FAIL')" class="style1" title="Klik untuk maklumat lengkap">$list.NO_PERMOHONAN</a>
          
          </td>
          <td class="$row" align="center">$list.TARIKH_TERIMA</td>
          <td class="$row">$list.PERKARA</td>
          <td class="$row">$list.STATUS</td>
        </tr>
        
        <tr><td colspan="7"><fieldset id="$list.bil" style="visibility:collapse; display:none;" >
 		<table width="100%"  class="$row"  >
 		
 		<tr><td width="50%" valign="top"><fieldset>
        <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
	        <table width="100%" border="0" cellspacing="2" cellpadding="2">
	        #if($list.NO_FAIL!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">No. Fail :</td>
	          <td width="70%">$list.NO_FAIL</td>
	        </tr>
	        #end
			#if($list.NAMA_URUSAN!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Urusan :</td>
	          <td width="70%">$list.NAMA_URUSAN</td>
	        </tr>
	        #end
			#if($list.NAMA_SUBURUSAN!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Suburusan :</td>
	          <td width="70%">GRN $list.NAMA_SUBURUSAN</td>
	        </tr>
	        #end
			#if($list.TARIKH_SURAT!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Tarikh Surat / Borang :</td>
	          <td width="70%">$list.TARIKH_SURAT</td>
	        </tr>
	        #end
			#if($list.NO_RUJ_SURAT!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">No. Rujukan Surat :</td>
	          <td width="70%">$list.NO_RUJ_SURAT</td>
	        </tr>
	        #end
	        
			#if($list.TAJUK_FAIL!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Perkara :</td>
	          <td width="70%">$list.TAJUK_FAIL</td>
	        </tr>
	        #end
			#if($list.STATUS!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Status Fail :</td>
	          <td width="70%">$list.STATUS</td>
	        </tr>
	        #end
			#if($list.FLAG_TEMPOHSEWA!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Tempoh Permohonan Sewa :</td>
	          <td width="70%">
	          #if ($list.FLAG_TEMPOHSEWA == 'B')
	          BULAN KE BULAN
	          #elseif ($list.FLAG_TEMPOHSEWA == 'T')
	          3 TAHUN
	          #elseif ($list.FLAG_TEMPOHSEWA == 'JP')
	          JANGKA PENDEK
	          #end
	          </td>
	        </tr>
	        #end
			#if($list.LUAS_MHNBERSAMAAN!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Luas Permohonan :</td>
	          <td width="70%">0$list.LUAS_MHNBERSAMAAN</td>
	        </tr>
	        #end
	        </table></fieldset>
	    </td></tr>
	    
	    <tr><td width="50%" valign="top"><fieldset>
        <legend><strong>MAKLUMAT TANAH</strong></legend>
	        <table width="100%" border="0" cellspacing="2" cellpadding="2">
	        #if($list.NO_LOT!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">No. Lot :</td>
	          <td width="70%">$list.NO_LOT</td>
	        </tr> 
	        #end
			#if($list.LUAS_LOT!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Luas Lot :</td>
	          <td width="70%">$list.LUAS_LOT</td>
	        </tr>
	        #end
			#if($list.NO_HAKMILIK!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">No. Hakmilik :</td>
	          <td width="70%">$list.NO_HAKMILIK</td>
	        </tr>
	        #end
			#if($list.NO_WARTA!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">No. Warta :</td>
	          <td width="70%">$list.NO_WARTA</td>
	        </tr>
	        #end
			#if($list.TARIKH_WARTA!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Tarikh Warta :</td>
	          <td width="70%">$list.TARIKH_WARTA</td>
	        </tr>
	        #end
			#if($list.NAMA_MUKIM!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Mukim :</td>
	          <td width="70%">$list.NAMA_MUKIM</td>
	        </tr>
	        #end
			#if($list.NAMA_DAERAH!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Daerah :</td>
	          <td width="70%">$list.NAMA_DAERAH</td>
	        </tr>
	        #end
	        #if($list.NAMA_NEGERI!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Negeri :</td>
	          <td width="70%">$list.NAMA_NEGERI</td>
	        </tr>
	        #end
			#if($list.KATEGORI_TANAH!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Kategori Tanah :</td>
	          <td width="70%">$list.KATEGORI_TANAH</td>
	        </tr>
	        #end
			#if($list.SUBKATEGORI_TANAH!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Subkategori Tanah :</td>
	          <td width="70%">$list.SUBKATEGORI_TANAH</td>
	        </tr>
	        #end
			#if($list.SYARAT!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Syarat Nyata :</td>
	          <td width="70%">$list.SYARAT</td>
	        </tr>
	        #end
			#if($list.SEKATAN!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Sekatan Kepentingan :</td>
	          <td width="70%">$list.SEKATAN</td>
	        </tr>
	        #end
			#if($list.KEGUNAAN_TANAH!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Kegunaan Tanah :</td>
	          <td width="70%">$list.KEGUNAAN_TANAH</td>
	        </tr>
	        #end
	        #if($list.NAMA_AGENSI!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Agensi :</td>
	          <td width="70%">$list.NAMA_AGENSI</td>
	        </tr>
	        #end
			#if($list.NAMA_KEMENTERIAN!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Kementerian :</td>
	          <td width="70%">$list.NAMA_KEMENTERIAN</td>
	        </tr>
	        #end
	        </table>
	    </td>
	    </tr>
					
        #if ($list.idStatus == '1610212')
	    <tr>
	    <td><fieldset>
	    <legend><strong>SEBAB BATAL PERMOHONAN</strong></legend>
	      <table width="100%" border="0" cellspacing="2" cellpadding="2">
			#if($list.TARIKH_BATAL!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Tarikh Batal :</td>
	          <td width="70%">$list.TARIKH_BATAL</td>
	        </tr>
	        #end
	        #if($list.CATATAN_BATAL!="") 
	        <tr>
	          <td width="30%" scope="row" align="right">Sebab Batal :</td>
	          <td width="70%">$list.CATATAN_BATAL</td>
	        </tr>
	        #end
	      </table>
	    </fieldset></td>
	    </tr>
		#end
		
		</table>
		</fieldset>
		</td>
		</tr> 
		#end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
</div>

<script>
function ShowPopup(hoveritem,tab)
{
	var hp = document.getElementById(tab);
	//alert("hp.style.display :"+hp.style.display+";hp.style.visibility:"+hp.style.visibility);
	//hp.style.display=="none" &&
    if( hp.style.visibility=="collapse" && hp.style.display=="none"){
        hp.style.display = "block";
        hp.style.visibility = "visible";
    }
    //hp.style.display=="block" &&
    else if( hp.style.visibility=="visible" && hp.style.display=="block"){
        hp.style.display = "none";
        hp.style.visibility = "collapse";
    }
}
function ShowInfo(idFail){
	document.${formName}.idFail.value = idFail;
	document.${formName}.initiateFlagBuka.value = "Y";
	document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan dan Hasil Persekutuan",$portal_role)?_portal_module=ekptg.view.php2.online.FrmPYWOnlineSenaraiFailView";
	document.${formName}.submit();
}
function findKosongkan() {
	document.${formName}.findNoFail.value = "";
	document.${formName}.findNoHakmilik.value = "";
	document.${formName}.findNoLot.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();
}
function carian() {
	document.${formName}.command.value = "cari";
	document.${formName}.submit();
}
/* function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
	document.${formName}.submit();
} */
function kembali() {
	document.${formName}.namamodul.value = "";
	document.${formName}.namatab.value = "";
    document.${formName}.action = "?myrole=online";
    //document.${formName}.namatab.value = "Menu";
    document.${formName}.namamodul.value = "ekptg.view.online.FrmOnlineMenuUtama";
    document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionPenyewaan.value = "daftarBaru";
	document.${formName}.submit();
}
/* if(location=='MYINFO'){
    document.${formName}.action = "?myrole=online";
    document.${formName}.namatab.value = "Menu";
    document.${formName}.namamodul.value = "ekptg.view.admin.UserProfileInternal";
    document.${formName}.submit();
} */
</script>