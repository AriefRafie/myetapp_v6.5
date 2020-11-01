<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
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
		            	<td width="29%" scope="row" align="right">No Hakmilik</td>
		            	<td width="1%">:</td>
		              	<td width="70%"><input name="txtNoHakmilik" id="txtNoHakmilik" type="text" value="$!txtNoHakmilik" size="43" maxlength="50" style="text-transform:uppercase;" >
		            </tr>
		            <tr>
		              <td  width="29%" height="24" scope="row" align="right">No. Rujukan KJP</td>
		              <td width="1%">:</td>
		              <td>
		              	<input name="txtNoRujukanKJP" id="txtNoRujukanKJP" type="text" size="43" value="$!txtNoRujukanKJP" maxlength="100" style="text-transform:uppercase;" />
		             </td>
		            </tr>
 				   <tr>
				    <td width="29%"><div align="right">Keputusan</div></td>
				    <td width="1%">:</td>
				    <td width="70%">
				     <select name="sockeputusan" id="sockeputusan" style="width:200">

                        #if($!idKeputusan == 'S')
							<option value="">SILA PILIH</option>
							<option value="S" selected="selected">SETUJU</option>
							<option value="TS">TIDAK SETUJU</option>

                        #elseif($!idKeputusan == 'TS')
 							<option value="">SILA PILIH</option>
                        	<option value="S" >SETUJU</option>
							<option value="TS" selected="selected" >TIDAK SETUJU</option>

                        #else
   							<option value="">SILA PILIH</option>
                        	<option value="S">SETUJU</option>
							<option value="TS">TIDAK SETUJU</option>

                        #end

                    </select>
				    </td>
				  </tr>
            		<tr>
              			<td width="29%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
					    <td width="70%">
              				<input type="button" class="stylobutton100" name="cmdCari" id="cmdCari" value="Cari" onclick="javascript:carianKJP()">
               				<input type="reset" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" >
                		</td>
            		</tr>
            		<!--<tr>
              			<td scope="row">&nbsp;</td>
              			<td>&nbsp;</td>
            		</tr>  -->

        		</table>
	  		</fieldset>
    	</td>
	</tr>

	<tr>
    	<td>
    		<fieldset><legend><b>SENARAI PERMOHONAN</b></legend>

        		<table align="center" width="100%">
		            <!-- <tr>
		              	<td colspan="6" scope="row">
		        		#parse("app/utils/record_paging.jsp")
		            	</td>
		            </tr> -->
		             <tr class="table_header">
		              <td width="3%" scope="row" align="center">Bil.</td>
		              <td width="15%">No Hakmilik</td>
		              <td width="15%">No. Rujukan KJP</td>
		              <td width="12%" align="center">Tarikh Hantar</td>
		              <td width="12%" align="center">Tarikh Terima</td>
		              <td width="15%"align="center">Keputusan</td>
		              <!--<td align="center">Hapus</td> -->
		             	<td width="43%" align="left">Ulasan</td>
		            </tr>
          			#set ($list = "")
			          #if ($SenaraiUlasanKJP.size() > 0)
			          #foreach ($list in $SenaraiUlasanKJP)
			            #if ($list.bil == '')
			                #set( $row = "row1" )
			            #elseif (($list.bil % 2) != 0)
			                #set( $row = "row1" )
			            #else
			                #set( $row = "row2" )
			            #end
			    	<tr>
			            <td class="$row" align="center" >#if($!list.statusName != 'SETUJU' && $!list.statusName != 'TIDAK SETUJU')<a href="javascript:paparKJP('$list.idUlasanKJP')" class="style1">#end $!list.bil.
			            #if($!list.statusName != 'SETUJU' && $!list.statusName != 'TIDAK SETUJU') </a> #end</td>
			            <td class="$row" >#if($!list.statusName != 'SETUJU' && $!list.statusName != 'TIDAK SETUJU')<a href="javascript:paparKJP('$list.idUlasanKJP')" class="style1">#end
			            $!list.noHakmilik #if($!list.statusName != 'SETUJU' && $!list.statusName != 'TIDAK SETUJU') </a> #end</td>
			            <td class="$row" >$!list.noRujukan</td>
			            <td class="$row" align="center" >$!list.tarikhHantar</td>
			          <td class="$row" align="center" >$!list.tarikhTerima</td>
			          <td align="center" class="$row">$!list.statusName</td>
			          <!-- <td align="center" class="$row"><a href="javascript: HapusKJP('$list.idUlasanKJP')"><img src="../img/delete.gif" border="0"></a></td> -->
			           <td align="left" class="$row">$!list.ulasanKJP</td>
			          </tr>
			          #end
			          #else
			          <tr>
			            <td class="row1">&nbsp;</td>
			            <td class="row1" colspan="5">Tiada Rekod</td>
			            <!-- <td class="row1">&nbsp;</td>
			            <td class="row1">&nbsp;</td>
			            <td width="29%" class="row1">&nbsp;</td>
			            <td width="15%" class="row1">&nbsp;</td> -->
			            </tr>
			          #end
        		</table>

			</fieldset>
		</td>
	</tr>
</table>
<div id="setSessionIdFail_result"></div>
	<input type="hidden" name="actionPajakan" value="$!actionPajakan"/>
	<input type="hidden" name="idFail" value="$!idFail"/>
	<input type="hidden" name="idPermohonan" value="$!idPermohonan">
	<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">
	<input type="hidden" name="mode" value="$!mode"/>
  	<input type="hidden" name="idUlasanKJP" id="idUlasanKJP" value="$idUlasanKJP"/>


<!-- 2018/02/22 -->
#parse("app/htp/utiliti/javascript/javaScriptPajakanTindakan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPenamatan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanBayaran.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPerjanjian.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanMJM.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPermohonan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPaging.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanDaftar.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanIndex.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakan.jsp")
#parse("app/htp/utiliti/javaScriptUmum.jsp")
