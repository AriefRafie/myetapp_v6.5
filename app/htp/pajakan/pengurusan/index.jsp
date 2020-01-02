<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="pageNow" value='$!pageNow'>
</p>
<table width="100%" border="0" >
	<tr>
		<td>
			&nbsp;
		</td>
    </tr>
	<tr>
		<td>
    		<fieldset><legend><b>CARIAN</b></legend>
	        	<table width="100%" align="center" border="0">
				  	<tr>
		            	<td width="29%" scope="row" align="right">No. Memorandum</td>
		            	<td width="1%">:</td>	            	
		              	<td width="70%"><input name="txtNoMemo" id="txtNoMemo" type="text" value="$!txtNoMemo" size="43" maxlength="50" > 
	           		</tr>
	            	<tr>
		            	<td width="29%" scope="row" align="right">No. Fail Seksyen</td>
		            	<td width="1%">:</td>	            	
		              	<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!txtNoFail" size="43" maxlength="50" > 
		            </tr>		            
            		<tr>
              			<td width="29%" scope="row" align="right">Tarikh</td>
              			<td width="1%">:</td>
              			<td width="70%">
              				<input type="text" name="txdTarikh" id="txdTarikh" value="$!txdTarikh" onblur="check_date(this)" size="11"/>
      						<a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
      					</td>
            		</tr>
            		<tr>
              			<td width="29%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
					    <td width="70%">
              				<input type="button" class="stylobutton100" name="cmdCari" id="cmdCari" value="Cari" onclick="javascript:carian()">
                			<input type="reset" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="javascript:kosongkan()">
                		</td>
            		</tr>
        		</table>
	  		</fieldset>
    	</td>
	</tr>
  
	<tr>
    	<td>
    		<fieldset><legend><b>SENARAI MEMORANDUM JEMAAH MENTERI</b></legend>
        
        		<table align="center" width="100%"> 
		            <tr>
		              <td colspan="5" scope="row">
		              	<input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:daftarBaru()"/>
		              </td>
		            </tr>
		            <tr>
		              	<td colspan="5" scope="row">
		        		#parse("app/utils/record_paging.jsp")
		            	</td>
		            </tr>
		            <tr class="table_header">
				        <td width="3%"><b>Bil.</b></td>
				        <td width="20%"><b>No. Memorandum</b></td>
				        <td width="20%"><b>No Fail Seksyen</b></td>
				        <td width="50%"><b>Tajuk</b></td>
				        <td width="10%"><b>Tarikh</b></td>
				         <td width="10%"><b>Hapus</b></td>
		            </tr>
          		#set ($list = "")
          		#if ($SenaraiFail.size() > 0)
          			#foreach ($list in $SenaraiFail)
			            #if ($list.bil == '')
			                #set( $row = "row1" )
			            #elseif (($list.bil % 2) != 0)
			                #set( $row = "row1" )
			            #else 
			                #set( $row = "row2" )
			            #end
		          	<tr>
		            	<td class="$row" >$list.bil.</td>
		            	<td class="$row"><a href="javascript:papar('$list.id')" class="style1">$list.noMemo</a></td>
		            	<td class="$row" >$list.noFail</td>
		            	<td class="$row" >$list.catatan</td>
		            	<td class="$row">$list.tarikh</td>
		            	<td class="$row"> <a href = "javascript:deleteMemo('$list.id')" style="color: blue">[Hapus]</a></td>
		          	</tr>
          			#end
          		#else
		          	<tr>
		            	<td class="row1" >&nbsp;</td>
		            	<td class="row1" align="left" colspan="4">Tiada Rekod</td>
		          	</tr>
          		#end
        		</table>
			</fieldset>
		</td>
	</tr>
	<tr align="right">
	<td align="right">
		<input type="button" value="Cetak" onclick="javascript:cetak()" >
	</td>
	</tr>
</table>
<!-- 	<input type="hidden" name="idPermohonan" value=""> -->
	<input name="hitButton" type="hidden" id="hitButton" value="$!hitButton"/>
	<input name="id" type="hidden" id="id" value="$!id"/>
<!-- 	<input type="hidden" name="idFail" /> -->
<!-- 	<input type="hidden" name="actionPajakan" value="$!actionPajakan"/> -->
	<input type="hidden" name="mode" />
<!-- 	<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch"> -->


<script>

function cetak() {
	//var reportfile = "htpSenaraiMemorandumJemaahMenteri";
	//var params = new Array();
	//params[0] = "output=pdf";
	//printReport(reportfile,params);
	//'ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegnotisbayar','HTPermohonanTanahNotis5ABelumBayar'
	openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=3',"mjm",'HTPajakanMJM')
}	
	
function openReport(urli,param,laporan,tem){
	var pnegeri = "&ID_NEGERI=-1";
	var pdaerah = "&ID=0";
	var pkem = "&ID_KEMENTERIAN=0";
	var pagen = "&ID_AGENSI=0";
	var ptem = "&template="+tem;
	var pbulanmula = "&BULANTAHUN=0";
	var pbulantamat = "&BULANTAHUNTMT=0";
	var ptahun = "&TAHUN=";
	var punit = "&ID_PEJABAT=0";
	var status ="&STATUS=-1";
	var bulan = "&bulan=00";
	
	var url = "../servlet/"+urli+"?"+param+pnegeri+pdaerah+pkem+pagen+ptem+pbulanmula+pbulantamat+ptahun+punit+status+bulan;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
}

function carian() {
	document.${formName}.hitButton.value = "carian";
	document.${formName}.submit();
}

function daftarBaru() {
	document.${formName}.hitButton.value = "daftarBaru";
	document.${formName}.submit();
}

function papar(id) {
	document.${formName}.id.value = id;
	document.${formName}.hitButton.value = "edit";
	document.${formName}.submit();
}

function deleteMemo(id) {
	if (confirm("Adakah anda pasti?") == true) {
		document.${formName}.id.value = id;
		document.${formName}.hitButton.value = "deleteMemo";
		document.${formName}.submit();
	}
}



</script>