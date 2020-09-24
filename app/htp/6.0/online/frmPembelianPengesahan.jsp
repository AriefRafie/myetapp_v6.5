<style type="text/css">
<!--
.pautanms {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
#if($!pageMode != 'edit')
	<tr>
		<td>
		##parse('app/htp/pembelian/fail/paging.jsp')
		</td>
    </tr>
#end
	<tr>
		<td>
			<fieldset><legend>MAKLUMAT PENERIMAAN PERMOHONAN</legend>
				<!--<table>	-->
				<table width="100%">
			   		<tr>
						<td width="50%" align="center" valign="top">
							<table width="100%" border="0">
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Negeri</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socNegeri</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Kementerian</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectKementerian</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Agensi</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectAgensi</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Urusan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectSuburusan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan">				
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Status Tanah</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$statusTanah</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Jenis Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socTarafKeselamatan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan">				
			              		</tr>
			              		<tr>
						      		<td width="1%" valign="top"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%" valign="top"><div align="left">Tajuk</div></td>
					        		<td width="1%" valign="top"><div align="center">:</div></td>
					        		<td width="68%">
										<textarea name="txtTajuk" id="txtTajuk" cols="41" rows="5" onkeyup="this.value=this.value.toUpperCase();"  $inputstyleread >$!htpPermohonan.permohonan.getTujuan()</textarea>					        		
					        		</td>					
			              		</tr>		

				       		</table>		
						</td>
        				<td width="50%" align="center" valign="top">
        					<table width="100%" border="0">
        						<tr>
						      		<td width="1%"><span class="labelmandatory">##if($pageMode!="update")*#end
						      		</span></td>
					        		<td width="30%"><div align="left">No. Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<!-- <input type="text" name="txtNoFailSek" size="28" value="$!htpPermohonan.permohonan.pfdFail.noFail" readonly="readonly" $inputstyleread>
					        		-->
					        		<input type="text" name="txtNoFailSek" size="28" value="$!htpPermohonan.permohonan.pfdFail.noFail" $inputstyleread>
					        		</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$!htpPermohonan.getNoRujukanKJP()"  $inputstyleread/></td>							              			
		              			</tr>		
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">*</span></td> -->
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail Lain</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!htpPermohonan.getNoRujukanLain()"  $inputstyleread/></td>							              			
		              			</tr>			              			        			
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">*</span></td> -->
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">Tarikh Surat KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$!htpPermohonan.permohonan.getTarikhSurat()" $inputstyleread >
						                #if($pageMode=="edit")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
						                #elseif($pageMode=="kemas")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
						                #else
						                </td>
						                #end
					        		</td>							              			
		              			</tr>		
			              		<tr>
						      		<td width="1%">
						      			<span class="labelmandatory">#if($pageMode!="update")*#end </span>
						      		</td>
					        		<td width="30%"><div align="left">Tarikh Permohonan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
						        		<td width="68%">
						        		<input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$!htpPermohonan.permohonan.getTarikhTerima()" $inputstyleread/>
						                #if($pageMode=="edit")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
						                #elseif($pageMode=="kemas")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
						                #else
						                #end
					                </td>							              			
		              			</tr>	
				              	
				       		</table>	        				
    					</td>
    				</tr>
				</table>
			</fieldset>
		</td>
    </tr>

	<tr>
		<td>
			<fieldset>
			<legend><strong>SENARAI SEMAK/ DOKUMEN YANG DISERTAKAN</strong></legend>
			<table style="width:100%">
				<tr class="row2">
					<td width="3%"><b>Bil</b></td>
					<td width="75%"><b>Keterangan</b></td>
					<td width="25%"><b>Dokumen</b></td>
			  	</tr> 
		#set ( $checked = "" )
	#if ($senaraiSemak.size() > 0)
		
		#foreach ( $semak in $senaraiSemak )
			#set( $i = $velocityCount )
			#if ( ($i % 2) == 0 )
				#set( $row = "row2" )
			#else
				#set( $row = "row1" )
			#end

        		#if($semak.flag == 'Y')
        			#set($checked = 'checked')
					#set($disabled = 'disabled')
        		#else
        			#set($checked = '')
        		#end			
					        ##if($semak.aturan==1)
				<tr>
						<td class="$row" width="3%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsenaraisemak" $checked /></td>
		          		<td class="$row" width="82%">$i. $semak.keterangan</td>
		          		<td class="$row" width="15%">
		          			$!semak.lampirans
		          		</td>	
				</tr>
        					##end
        		       ##end
 		#end
   	#else
        <tr>
	        <td class="$row" width="3%">&nbsp;</td>
    	    <td class="$row" colspan="2" width="95%">Tiada Rekod</td>
        </tr>
   	#end			
			</table>
			</fieldset>
		</td>
	</tr>

	<tr>
		<td align=center>
			#if($pageMode=="edit")
				<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanFail()">
				<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')">
			#elseif($pageMode=="update")
				<input class="stylobutton100" type="button" onclick="javascript:pembelianTerima('$!idFail');" value="Simpan & Email" />
				<!-- <input class="stylobutton" type="button" onclick="javascript:pembelianTolak($!idFail);" value="Tolak" /> -->
          	#else
          		<input class="stylobutton100" type="button" onclick="javascript:pembelianViewMaklumatOnline('$!htpPermohonan.permohonan.getIdPermohonan()','$!htpPermohonan.getIdHtpPermohonan()');" value="Seterusnya" />
 			#end    
 			<!-- <img src="../img/emel.gif" title="Pemberitahuan melalui emel"> -->
    	</td>
    </tr>

</table>



<input type="hidden" name="socNegeri" value="$!htpPermohonan.fail.getIdNegeri()"/>
<input type="hidden" name="socNegeri" value="$!htpPermohonan.permohonan.getIdKementerian()"/>
<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="pageMode" value="$!pageMode">

	
	<script>
		function cetakImej(id){
			//var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
		    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id;
		    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
		    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener=document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}
/* 	function pembelianTerima(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("pembelianditerima","mode="+mode+"&idfail="+id+"&pagemode=0");
	} */
		function pembelianTerima(id){
			var mode = 'pembelianditerima';
			//doAjaxCall${formName}("pajakanditerima","mode="+mode+"&idfail="+id+"&pagemode=0");
			document.${formName}.command.value = mode;
			//document.${formName}.actionPajakan.value = "";
			document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.online.FrmPermohonanPengesahan&actionPerletakhakan=papar&idfail="+id;
			document.${formName}.submit();
		}
	function onlineAttach_(v1,v2,v3){
		alert('attch');
	}

	</script>

$javaScriptLampiran
	