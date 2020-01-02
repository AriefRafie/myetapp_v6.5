<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<fieldset>
<p></p>
<fieldset><legend><b>Carian</b>
</legend>
<table width="100%" align="center" border="0">
  <tbody>
    <tr>
      <td width="30%" height="24" scope="row" align="right">No Fail : </td>
      <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!txtnofail" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
      <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="$!txtpemohon" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">Nama Simati : </td>
      <td width="70%"><input name="txtSimati" id="txtSimati" type="text" value="$!txtsimati" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">No.KP Simati : </td>
      <td width="70%" style="text-transform:uppercase;"><select name="jenisIc" style="width: 100px;">
      #set ($selected0 = "")
      #set ($selected1 = "")
      #set ($selected2 = "")
      #set ($selected3 = "")
      #if ($txtjenisic != "")
      		#if ($txtjenisic == 1)
            	#set ($selected1 = "selected")
            #elseif ($txtjenisic == 2)
            	#set ($selected2 = "selected")
            #elseif ($txtjenisic == 3)
            	#set ($selected3 = "selected")
            #else
            	#set ($selected0 = "selected")
            #end
          <option value="1" $selected1 >No. KP Baru</option>
          <option value="2" $selected2 >No. KP Lama</option>
          <option value="3" $selected3 >No. KP Lain</option>
          <option value="0" $selected0>Sila Pilih</option>
      #else
          <option value="0">Sila Pilih</option>
          <option value="1">No. KP Baru</option>
          <option value="2">No. KP Lama</option>
          <option value="3">No. KP Lain</option>
      #end
      </select>&nbsp;&nbsp;<input name="txtIcSimati" id="txtIcSimati" type="text" value="$!txticsimati" size="20" maxlength="14" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td scope="row" height="50px;"></td>
      <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:search_data()">
        <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:cancel()"></td>
    </tr>
  </tbody>
</table>
</fieldset>
<fieldset>
<legend><b>Senarai Fail Pemindahan Bidang Kuasa Eksklusif</b></legend>
<table width="100%" border="0" cellpadding="2" cellspacing="0">
                <tr>
                    <td  align="left">Jumlah Rekod : $!totalRecords</td>
                    <td  align="right">
                        Papar
                        #if ($carix==2)
                        	#set ($changes="doChangesValue")
                        #else
                        	#set ($changes="doChanges")
                        #end
                        <select class="smallselect" name="itemsPerPage" onchange="javascript:$changes()">
                            <option value="10" #if ($itemsPerPage == 10) selected #end>10</option>
                            <option value="20" #if ($itemsPerPage == 20) selected #end>20</option>
                            <option value="30" #if ($itemsPerPage == 30) selected #end>30</option>
                            <option value="40" #if ($itemsPerPage == 40) selected #end>40</option>
                            <option value="50" #if ($itemsPerPage == 50) selected #end>50</option>
                        </select>
                        
                    </td>
                 </tr>
            </table>
<p></p>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
		<td colspan="3" >&nbsp;</td>
	    </tr>
        <tr>
		<td width="90%" colspan="3">
		#if( $isFirstPage )
		    ##<input class="stylobutton"  type="button"  value="<< Previous" disabled>
		    <img style="vertical-align=bottom;" src="../img/paging/page-first-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		#else
		    ##<input class="stylobutton"  type="button"  value="<< Previous" onclick="getprevious('1')">
		   #set ($previous10 = $page - 9)
		   <img title="First Page" style="vertical-align=bottom;cursor:hand;cursor:pointer;" onclick="doChangestoo('1')" src="../img/paging/page-first.gif">
		   #if ($page > 10)
		   	<img title="Previous 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;" onclick="getprevious('$previous10')" src="../img/paging/page-prev.gif">
		   #else
		   	<img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		   #end
		   
		#end
        &nbsp;
        #if ($totalPages > 0)
			#set ($next10 = $page + 9)
			#foreach ( $i in [$page..$totalPages] )
                #if ($i <= $next10)
                    #if ($i == $page)
                    <b>[$i]</b>
                    #else
                    <a href="javascript:doChangestoo('$i')">[$i]</a>
                    #end
                #end
            #end
        #end
        &nbsp;
        #if( $isLastPage )
		    ##<input class="stylobutton"  type="button"  value="Next >>" disabled>
		    <img style="vertical-align=bottom;" src="../img/paging/page-next-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-last-disabled.gif"> 

		#else
		    ##<input class="stylobutton"  type="button"  value="Next >>" onclick="getnext('$page');">
		    <img title="Next 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;" onclick="getnext('$next10');" src="../img/paging/page-next.gif">
		    <img title="Last page:$totalPages" style="vertical-align=bottom;cursor:hand;cursor:pointer;" onclick="doChangestoo('$totalPages')" src="../img/paging/page-last.gif">
		#end
		</td>
		#if ($totalPages > 0)
		<td align="right" nowrap>Mukasurat $page dari $totalPages</td>
		#end
		</td>
	    </tr>
</table>
<table align="center" width="100%"> 
  <tbody>
    <tr class="table_header">
      <td scope="row" width="5%" align="center">Bil</td>
      <td width="25%">No Fail</td>
      <td width="35%">Nama Simati</td>
      <td width="10%" align="center">Tarikh Mohon</td>
      <td width="15%" align="center">Status Fail</td>
      <td width="5%" align="center">Seksyen</td>
    </tr>
   #set ($noFail = "")
   #set ($tarikhDaftar = "")
   #set ($tarikh_Mohon = "")
   #set ($keterangan = "")
   #set ($noFail1 = "")
   #set ($tarikhDaftar1 = "")
   #set ($tarikh_Mohon1 = "")
   #set ($keterangan1 = "")
    #set ($bil = "")
    #if ($carix == "2")
    #set ($cnt = 0)
    	 #if ($CountList1 != "0")
	       #foreach($fail in $senaraitugasan1)
	           #set ($i = $velocityCount)
		        #if (($i % 2) == 0)
		       		#set ($row = "row2")
		        #else
		       		#set ($row = "row1")
		        #end
				   #set ($noFail = $fail.no_Fail)
				   #set ($bil = $fail.bil)
				   #set ($tarikhDaftar = $fail.tarikhDaftar)
				   #set ($tarikh_Mohon = $fail.tarikh_Mohon)
				   #set ($keterangan = $fail.keterangan)
				   #set ($namasimati = $fail.namasimati)
                   #set ($seksyen = $fail.seksyen)
					  
					   #set ($cnt = $cnt + 1)
					   <tr >
				          <td class="$row">$!bil</td>
				          <td class="$row"><a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_simati','$fail.seksyen')" class="style1">$!noFail
				          </a></td>
				          <td class="$row">$!namasimati.toUpperCase()</td>
				          <td class="$row" align="center">$!tarikh_Mohon</td>
				          <td class="$row">$!keterangan</td>
                          <td class="$row" align="center">$!seksyen</td>
				        </tr>
				       
	        #end
	     #else
				        <tr >
				          <td class="$row" colspan="5" >Tiada Rekod </td>
				         </tr>
		#end
   #elseif ($carix == "1")
   		#set ($cnt2 = 0)
   			#if ($CountList != "0")
	         #foreach($fail1 in $senaraitugasan)
	         #set ($i = $velocityCount)
		        #if (($i % 2) == 0)
		       		#set ($row = "row2")
		        #else
		       		#set ($row = "row1")
		        #end
				   #set ($bil = $fail1.bil)
				   #set ($noFail = $fail1.no_Fail)
				   #set ($tarikhDaftar = $fail1.tarikhDaftar)
				   #set ($tarikh_Mohon = $fail1.tarikhmohon)
				   #set ($keterangan = $fail1.keterangan)
				   #set ($namasimati = $fail1.namasimati)
                   #set ($seksyen = $fail1.seksyen)
				   			#if ($!bil == "")
				   			
				   			#else
				   				#set ($cnt2 = $cnt2 + 1)
        <tr >
          <td class="$row">$!bil</td>
          <td class="$row"><a href="javascript:edit_item('$fail1.id_Permohonan','$fail1.id_simati','$fail1.seksyen')" class="style1">$!noFail
          </a></td>
          <td class="$row">$!namasimati.toUpperCase()</td>
          <td class="$row" align="center">$!tarikh_Mohon</td>
          <td class="$row">$!keterangan</td>
          <td class="$row" align="center">$!seksyen</td>
        </tr>
        					#end
   			#end
   			#else
   		 <tr >
          <td class="$row" colspan="5">Tiada Rekod</td>
         </tr>
   			#end
   			
   #end
   
  </tbody>
</table>
</fieldset>
</fieldset>
	<input type="hidden" name="hitButt" />
	<input type="hidden" name="idpermohonan"/>
    <input type="hidden" name="idSimati" />
    <input type="hidden" name="idFlag" />
    <input type="hidden" name="flagno" />
    <input type="hidden" name="pagemode" >
    <input type="hidden" name="getvalue" >
    <input type="hidden" name="action" >
    <input type="hidden" name="page" >
</form>
<script>
function cancel() {
document.${formName}.reset();
document.${formName}.txtNoFail.value = "";
document.${formName}.txtNoFail.focus();
}
function Tambah() {
	//document.${formName}.hitButt.value = "tambah";
	//document.${formName}.idFlag.value = "1";
	//document.${formName}.flagno.value = "0";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8SenaraiSemak";
	document.${formName}.submit();
}
function search_data(){
	if (document.${formName}.txtNoFail.value == "" && document.${formName}.txtPemohon.value == "" && document.${formName}.txtSimati.value == "" && document.${formName}.txtIcSimati.value == ""){
		alert("Sila masukkan maklumat yang ingin dicari.");
	}
	else {
	document.${formName}.method="post";
	document.${formName}.hitButt.value = "Cari";
	document.${formName}.action.value = "";
	document.${formName}.submit();
	}
}
function cetak() {
	window.print();
}
function doChanges() {
	document.${formName}.method="post";
	document.${formName}.hitButt.value = "doChanges";
	document.${formName}.action.value = "doChangeItemPerPage";
	document.${formName}.submit();
}

function doChangesValue() {
	document.${formName}.method="post";
	document.${formName}.hitButt.value = "doChangesvalue";
	document.${formName}.action.value = "doChangeItemPerPage";
	document.${formName}.submit();
}

function doChangestoo(val) {
	document.${formName}.method="post";
	document.${formName}.hitButt.value = "doChanges";
	document.${formName}.action.value = "getPage";
	document.${formName}.getvalue.value = val;
	document.${formName}.submit();
}

function doChangesCari(val) {
	document.${formName}.method="post";
	document.${formName}.hitButt.value = "doChangesCari";
	document.${formName}.action.value = "getPage";
	document.${formName}.getvalue.value = val;
	document.${formName}.submit();
}

function getnext(val) {
	document.${formName}.method="post";
	document.${formName}.hitButt.value="doChanges";
	document.${formName}.action.value="getNext";
	document.${formName}.page.value = val;
	document.${formName}.submit();
}

function getprevious(val) {
	document.${formName}.method="post";
	document.${formName}.hitButt.value="doChanges";
	document.${formName}.action.value="getPrevious";
	document.${formName}.page.value = val;
	document.${formName}.submit();
}


function edit_item(id,id2,id3){
	if (id3=="8"){
	document.${formName}.hitButt.value = "papar";
	document.${formName}.action.value = "";
	document.${formName}.idpermohonan.value = id;
	document.${formName}.idSimati.value = id2;
	document.${formName}.submit();
	}else{
	document.${formName}.hitButt.value = "papar17";
	document.${formName}.action.value = "";
	document.${formName}.idpermohonan.value = id;
	document.${formName}.idSimati.value = id2;
	document.${formName}.submit();
	}
}
</script>

