 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
  <!-- $senaraiLangkah --><br>
 <fieldset> <legend><strong>MAKLUMAT CUKAI TANAH PERSEKUTUAN 2 </strong></legend><br>
<br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Penyata telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Penyata telah berjaya dikemaskini.
            #end            
            </b></font>           
            </td>
        </tr>
    </table>
#end
<fieldset>
<!--<form name="f1" method="post">-->
#parse ("app/htp/frmCukaiInfo.jsp")
<!--</form>-->
</fieldset>
<table><tr><td></td></tr></table>
<fieldset>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
   	<li class="TabbedPanelsTab" tabindex="1" title="Penyata" onclick="setSelected(0);PenyataView()"><strong><font size="1">PENYATA CUKAI</font></strong></li>
    <li class="TabbedPanelsTab" tabindex="1" title="Baucer" onclick="setSelected(1);BaucerView()"><strong><font size="1">BAUCER</font></strong></li>
   </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
    <!--<form name="penyata" method="post">-->
        
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tbody>
    			<tr class="table_header">
                #if ( $idbayaran == 2 )
    			  	<td colspan="7" align="center"><div align="left"></div></td>
                #else
                	<td colspan="7" align="center"><div align="left"><b>NEGERI</b> : $negeri</div></td>
                #end
   			  </tr>
    			<tr class="table_header">
        		  <td width="12%" align="center"><strong>Daerah</strong></td>
   				  <td width="13%" align="center"><strong>Jumlah Hakmilik</strong></td>
   				  <!--
       			  <td width="15%" align="center"><strong>Lebihan (RM)</strong></td>
       			  <td width="14%" align="center"><strong>Denda (RM)</strong></td>
       			  <td width="14%" align="center"><b>Lain-lain <strong>(RM)</strong></b></td>
       			    -->
   			      <td width="16%" align="center"><strong>Cukai Semasa (RM)</strong></td>
   			      
   			  </tr>
                #set ($count = 0)
                #set ($sumCukai2 = 0)
                #set ($sumIdHakmilik2 = 0)
                #foreach ($fail in $Penyata)
                #set ($count = $count+1)
                  #set( $i = $velocityCount )
                  #if ( ($i % 2) != 1 )
                       #set( $row = "row2" )
                  #else
                       #set( $row = "row1" )
                  #end
      			<tr>
                
            
            
        			<td class="$row" height="25"><div align="center">$fail.nama_daerah</div></td>
        			<td class="$row"><div align="center">$fail.sumIdHakmilik</div></td>
        			<!--
        			<td class="$row"><div align="center">$fail.lebihan</div></td>
                    <td class="$row"><div align="center">$fail.sumDenda</div></td>
        			<td class="$row"><div align="center">$fail.sumBayaran_lain</div></td>
        			  -->
      			    <td class="$row"><div align="center">$fail.sumCukai</div></td>
      			   <!--<td class="$row"><div align="center">$fail.sumAmaunBaucer</div></td>-->
      			</tr>
                <input type="hidden" name="idDaerah" value="$fail.idDaerah">
            	
            	#set ($sumCukai2 = $sumCukai2+ $fail.sumCukai)
                #set ($sumIdHakmilik2 = $fail.sumIdHakmilik +$sumIdHakmilik2)
                #end
                
                <input type="hidden" name="sumIdHakmilik" value="$sumIdHakmilik2">
                <input type="hidden" name="sumCukai" value="$sumCukai2" >
               
               	#set ($sumAllHakmilik = 0)
                #foreach ($fail in $Penyata)
                #if ( $fail.sumAllHakmilik == 0 )
					#set ($sumAllHakmilik = 0)
                #else	
                    #set ($sumAllHakmilik = $sumAllHakmilik + $fail.sumAllHakmilik)
				#end
                #end
                
                #set ($sumAllCukai = 0)
                #foreach ($fail in $Penyata)
                #if ( $fail.sumAllCukai == 0 )
					#set ($sumAllCukai = 0)
                #else
					#set ($sumAllCukai = $sumAllCukai + $fail.sumAllCukai)
				#end
                #end
                
                <tr></tr>
                <tr>
        			<td><div align="center"></div></td>
        			<td><div align="center">Jumlah = $sumIdHakmilik2</div></td>
        			<td colspan="2"><div align="center">Jumlah = $Util.formatDecimal($sumCukai2)</div></td>
      			    
      			   <!--<td class="$row"><div align="center">$fail.sumAmaunBaucer</div></td>-->
      			</tr> 
                
                  #if ($count == 0)
                  <tr> 
                    <td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                  </tr>
                  #end    
                <tr>
					<td colspan="7" align="center">
                    	<input class="stylobutton" type="submit" name="cmdKemaskiniPenyata" id="cmdKemaskiniPenyata" value="Sahkan" onclick="KemaskiniPenyata()" >
                    	<input class="stylobutton" type="button" name="cmdSimpanPenyata" id="cmdSimpanPenyata" value="Simpan" onclick="SimpanPenyata()" >
                    	<input class="stylobutton" type="reset" name="cmdBatalPenyata" id="cmdBatalPenyata" $btnName onclick="BatalPenyata()" style="display:none" >
                    	<input class="stylobutton" type="button" name="cmdKembaliPenyata" id="cmdKembaliPenyata" value="Kembali" onClick="KembaliPenyata()">
                    	<input class="stylobutton" type="button" name="cmdSeterusPenyata" id="cmdSeterusPenyata" value="Seterus" style="display:none" >
             			<input class="stylobutton" type="button" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakPenyata('$tahun_cukai','$idNegeri')" />
                     </td>
                </tr>
                <!--<fieldset id="tableReport1" style="display:none;">
					<legend>SENARAI CETAKAN</legend>
					<table width="100%" border="0">
					  <tr>
					    <td><a href="javascript:cetakHakmilik($idHakmilik);" class="style1">Maklumat Fail</a></td>
					  </tr>
					</table>
					</fieldset> -->
                
			</tbody>
		</table>
  
			<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
  			<input type="hidden" name="idFail">
  			<input type="hidden" name="noFail">
            <input type="hidden" name="idPermohonan" value="$idPermohonan">
            <input type="hidden" name="idNegeri" value="$idNegeri">
            <input type="hidden" name="negeri" value="$negeri">
            <input type="hidden" name="nama_daerah" >
            <input type="hidden" name="fail" >
            <input type="hidden" name="tahun_cukai" value="$tahun_cukai">
            <input type="hidden" name="peringkat_bayaran" value="$statusDisplay" >
            <input type="hidden" name="socbayaran" value="$idbayaran" >
  			<input type="hidden" name="command1" value="$command1">
            <input type="hidden" name="pagemode" value="$pagemode">
            <input type="hidden" name="style1" value="$Style1">
  			<input type="hidden" name="style2" value="$Style2">
		<!--</form>-->
    
    </div>
    <div class="TabbedPanelsContent">
    <!--<form name="tbh" method="post">-->
    
	<!--</form>-->
        
		<!--<form name="baucer" method="post">-->
        <table border="0" cellpadding="0" cellspacing="0" width="98%">
			<tbody>
    			<tr>
    				#if ( $idPeringkatbayaran == 0 )
						<input class="stylobutton" name="cmdTambah" value="Tambah Baucer" id="cmdTambah" type="button" disabled="disabled" >
    				#else	
    					<input class="stylobutton" name="cmdTambah" value="Tambah Baucer" id="cmdTambah" type="button" onClick="javascript:TambahBaucer()">
					#end
    			</tr>	
    			<tr class="table_header">
        			<td width="13%" align="center"><strong>Daerah 1</strong></td>
   				  <td width="12%" align="center"><strong>No. Baucer</strong></td>
       			  <td width="17%" align="center"><strong>Tarikh Baucer</strong></td>
       			  <td width="17%" align="center"><strong>No. Cek/FT</strong></td>
       			  <td width="17%" align="center"><b>Tarikh Cek/FT</b></td>
   			      <td width="24%" align="center"><strong>Amaun Cek/FT (RM)</strong></td>
                  <td width="24%" align="center"><strong></strong></td>
	      </tr>
                #set ($count = 0)
      			#foreach ( $fail in $Baucer )
                #set ($count = $count+1)
                  #set( $i = $velocityCount )
                  #if ( ($i % 2) != 1 )
                       #set( $row = "row2" )
                  #else
                       #set( $row = "row1" )
                  #end
      			<tr>
        			<td class="$row" height="25"><div align="center">$fail.nama_daerah</div></td>
        			<td class="$row"><div align="center">$fail.no_baucer</div></td>
        			<td class="$row"><div align="center">$fail.tkh_baucer</div></td>
        			<td class="$row"><div align="center">$fail.no_resit</div></td>
        			<td class="$row"><div align="center">$fail.tkh_resit</div></td>
      			    <td class="$row"><div align="center">$fail.amaun_baucer</div></td>
                    <td class="$row"><div align="center">
                    	<input name="cmdEdit" value="Edit" id="cmdEdit" class="stylobutton" type="button" onClick="javascript:EditBaucer('$fail.id_baucer')">
                    	<input name="cmdCetak" value="Cetak" id="cmdCetak" class="stylobutton" type="button" onClick="javascript:CetakBaucer('$fail.id_baucer')">
                    	</td>
   			    </tr> 
         		 
      			#end
                <input type="hidden" name="idBaucer" >
                  #if ($count == 0)
                  <tr> 
                    <td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                  </tr>
                  #end             
                <tr>
					<td colspan="6" align="center">
                    	<input class="stylobutton" type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="KemaskiniBaucer()" style="display:none">
                        <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="SimpanBaucer()" style="display:none">
                        <input class="stylobutton" type="button" name="cmdSeterus" id="cmdSeterus" value="Seterus" onclick="SeterusBaucer('$idNegeri','$idPeringkatbayaran')">
                        <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="BatalBaucer()" style="display:none">
                        <input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliBaucer()">                    </td>
                </tr>
			</tbody>
		</table>
			<input type="hidden" name="idPermohonan" value="$idPermohonan">
            <input type="hidden" name="peringkat_bayaran" value="$peringkat_bayaran" >
            <input type="hidden" name="socbayaran" value="$peringkat_bayaran" >
            <input type="hidden" name="idPeringkatbayaran" value="$idPeringkatbayaran" >
            <input type="hidden" name="idNegeri" value="$idNegeri">
	  <!--</form>--->
    
   
    </div>
  </div>
</div>
</fieldset> </fieldset>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1" ,{defaultTab:$selectedTab});
//-->
function TambahBaucer() {
	document.${formName}.action = "";
	document.${formName}.command.value = "tambahBaucer";
	document.${formName}.pagemode.value = "baru";
	document.${formName}.submit();
}
function EditBaucer(idBaucer) {
	document.${formName}.action = "";
	document.${formName}.command.value = "tambahBaucer";
	document.${formName}.pagemode.value = "view";
	document.${formName}.idBaucer.value = idBaucer;
	document.${formName}.submit();
}
function SimpanPenyata(){	
	if(document.${formName}.sumCukai.value == 0){
		alert('Tiada Rekod Untuk Di Simpan');
		//document.${formName}.pagemode.value = "kembali";
		return;
	}	
	if ( !window.confirm("Anda Pasti?") ) return;	
	document.${formName}.pagemode.value = "simpanPenyata";
	document.${formName}.command.value = "cukaiperingkatbayar";
	document.${formName}.action = "";
	document.${formName}.submit(); 
}
function KemaskiniPenyata() {
	if(document.${formName}.sumCukai.value == 0){
		alert('Tiada Rekod Untuk Di Simpan');
		//document.${formName}.pagemode.value = "kembali";
		return;
	}	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.pagemode.value = "simpanPenyata";
	document.${formName}.command.value = "cukaiperingkatbayar";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function KembaliPenyata() {
	document.${formName}.action = "";
	document.${formName}.command.value = "";
	document.${formName}.submit();
}
function SimpanBaucer(){	
	document.${formName}.pagemode.value = "simpanBaucer";
	document.${formName}.command.value = "cukaiperingkatbayar";
	document.${formName}.action = "";
	document.${formName}.submit(); 
}
function SeterusBaucer(idNegeri,idPeringkatbayaran) {
	document.${formName}.action = "";
	document.${formName}.command.value = "cukaiBayaran";
	document.${formName}.idNegeri.value = "idNegeri";
	document.${formName}.idPeringkatbayaran.value = "idPeringkatbayaran";
	document.${formName}.submit();
}
function KembaliBaucer() {
	document.${formName}.action = "";
	document.${formName}.command.value = "";
	document.${formName}.submit();
}
function PenyataView() {
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "penyataview";
	document.${formName}.command.value = "cukaiperingkatbayar";
	document.${formName}.submit();
}
function BaucerView() {
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "baucerview";
	document.${formName}.command.value = "cukaiperingkatbayar";
	document.${formName}.submit();
}
function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;
}

function maklumatTerperinci123(id,modul) {
	//document.${formName}.method = "post";

	//document.${formName}.command.value = "paparKeputusan";
	//document.${formName}.idPermohonan.value = 77;
	//alert("../c/"+id+"?_portal_module="+modul);
	document.${formName}.action = "../c/"+id+"?_portal_module="+modul;
	//document.${formName}.action = "../c/${securityToken}?_portal_module=ekptg.view.htp.FrmCukai";
	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmCukai&command1=";
	document.${formName}.submit();
	
	
	
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakHakmilik(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?idHakmilik="+idhakmilik;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

//rosli 2010/04/19
function cetakPenyata(tahun,idnegeri){
	var url = "../servlet/ekptg.report.htp.cukai.LaporanCukai?TAHUN="+tahun+"&ID_NEGERI="+idnegeri+"&template=HTPCukaiMemoSenarai";
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

//rosli 2010/04/30
function CetakBaucer(idbaucer){
	var url = "../servlet/ekptg.report.htp.SuratHTP?idpermohonan="+idbaucer+"&template=HTPCukaiBaucer";
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

//rosli 2010/04/19
function CetakBayaran(tahun,idnegeri){
	var url = "../servlet/ekptg.report.htp.cukai.LaporanCukai?TAHUN="+tahun+"&ID_NEGERI="+idnegeri+"&template=HTPCukaiMemoSenaraiBaucer";
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

document.${formName}.cmdSimpanPenyata.style.display = document.${formName}.style2.value;
document.${formName}.cmdKemaskiniPenyata.style.display = document.${formName}.style1.value;

</script>

