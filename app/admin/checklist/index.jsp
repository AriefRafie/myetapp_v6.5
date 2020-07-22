#if ( $SimpanStatus == "success" )
    <table style="width:100%">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Fail anda telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Fail anda telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>

 #end

<br>
<fieldset>
<legend>Maklumat Semakan</legend>
<table style="width:100%">

<!--     <tr>
    	<td>
        	<hr size="2" width="100%" align="center" />
        </td>
    </tr> -->
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" title="Maklumat Semakan" onclick="setSelected(0);byDefault()"><strong><font size="1">Maklumat Semakan</font></strong></li>
                <li class="TabbedPanelsTab" title="Semakan vs Skrin" onclick="setSelected(1);byUrusan()"><strong><font size="1">Semakan vs Skrin</font></strong></li>
                <li class="TabbedPanelsTab" title="Semakan vs Jenis Dokumen" onclick="setSelected(2);byStatus()"><strong><font size="1">Semakan vs Jenis Dokumen</font></strong></li>
                <!-- <li class="TabbedPanelsTab" title="Peguam" onclick="setSelected(3);PeguamView()"><strong><font size="1">Peguam Yang Dilantik</font></strong></li> -->
              </ul>
              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent">
                #set ($IdPeguam = "")
                #set ($pNama = "")
                #set ($pAlamat1 = "")
                #set ($pAlamat2 = "")
                #set ($pAlamat3 = "")
                #set ($pPoskod = "")
                #set ($pNoTel = "")
                #set ($pNoFax = "")
                #foreach ( $senarai in $senarais )
                	#set ($idketerangan = $senarai.idkpiketerangan)
                	#set ($keterangan = $senarai.keterangan)
                    #set ($idmasuk = $senarai.idmasuk)
                    #set ($tmasuk = $senarai.tarikhmasuk)
                    #set ($idkemaskini = $senarai.idkemaskini)
                    #set ($tkemaskini = $senarai.tarikhkemaksini)
                #end
                
                #set ($btnNamePeguam = "value='Kosongkan'")
				#if ($IdPeguam != "")
					#set ($btnNamePeguam = "value='Batal'")
				#end
               <!-- Bahagian Keterangan-->
                <table width="100%" >
                  		<tr>
                            			<td width="50%">
                            				<div align="right"><strong>Keterangan :</strong></div>         				
                            			</td>
                            			<td width="50%">
                            				<div align="left"><input type="text" name="txtdesc" id="txtdesc" value="$!txtDesc" /></div>
                            			</td>
                            			
                        </tr>                                     
                      	<tr>
                       		<td colspan="2"><div align="center">
	                       		<input type="button" name="btncari" id="btncari" value="Cari" onclick="byDefault()" class="button">
                          	</div></td>
                  		</tr>      
                          <tr>
                            <td colspan="2"><div align="left">
                            </div></td>
                          </tr>                  		       
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset >
                            	<table width="100%" >
                            	  #if($SenaraiFail.size() != 0 )                          	
                  					<tr>
                            			<td colspan="2">
                  								#parse("app/utils/record_paging.jsp")
                            			</td>
                  					</tr>
                  				#end	
                          			<tr>
                            			<td colspan="2">
                            				<div align="left"> 
                              				<table width="100%">
                              					<tr class="table_header">
								      			    <td width="3%" align="center"><strong>#</strong></td>
								        			<td width="62%" align="center"><strong>Keterangan</strong></td>
								       				<td width="15%" align="center"><strong>Tarikh Kemasukan</strong></td>
								       				<td width="15%" align="center"><strong>Tarikh Kemaskini</strong></td>
								      			    <td width="5%" align="center"></td>
								        		</tr>
                                                #set ($count = 0)
                                                #set ($keterangan = "")
                								#foreach ( $desc in $SenaraiFail )
                                                #set ($count = $count+1)
                                                  #set( $i = $velocityCount )
                                                  #if ( ($i % 2) != 1 )
                                                       #set( $row = "row2" )
                                                  #else
                                                       #set( $row = "row1" )
                                                  #end
                                				
                                				<tr>
                                  				  	<td class="$row" scope="row">$desc.getBil().</td>
                                                  	<td class="$row">$desc.perihal</td>
                                                  	<td class="$row">$desc.tarikhMasukf</td>
                                                  	<td class="$row">$desc.tarikhKemaskinif</td>
                                                  	<td class="$row" align="right">
														<a href="javascript:editKeterangan('$desc.idSemakan','$desc.perihal')"><img src="../img/edit.gif" border="0"></a>
							                        	<a href="javascript:deleteKeterangan('$desc.idSemakan')"><img src="../img/delete.gif" border="0"></a>
							                 		</td>
                                         		</tr>
                                                #end
                                              	#if ($count == 0)
                                              	<tr> 
                                                	<td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                                              	</tr>
                                              	#end
                                        	</table>                                           
                                    	</div></td>
                                	</tr>
								</table>
                              </fieldset>
							</div></td>
                          </tr>                          

                          <tr>
                            <td colspan="2"><div align="left">
                            </div></td>
                          </tr>

  
	<tr>
		<td valign="top" valign="top">
			<table>
				<tr>
					<td>
						Keterangan:
						<br>
						<label>
                  			<textarea name="keterangan" cols="45" rows="3"></textarea>
                		</label>
                	</td>
				</tr>
				<tr>
					<td valign="top" colspan="2">
						<input class="button" name="btnsubmit"  type="button" value="Tambah" onclick="tambahKeterangan()">
						<input class="button" type="reset" value="Batal" >
					</td>
				</tr>				
			</table>
		</td>
	</tr>
                          
                    </table>
                   <input type="hidden" name="idPeguam" value="$IdPeguam">
                  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                	<input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="noFail" value="$NoFail">
                    <input type="hidden" name="style1" value="$Style1">
  					<input type="hidden" name="style2" value="$Style2">
                </div>
                <div class="TabbedPanelsContent">
                
                #set ($btnNamePeguam = "value='Kosongkan'")
				#if ($IdPeguam != "")
					#set ($btnNamePeguam = "value='Batal'")
				#end
				
<!--Keterangan mengikut urusan -->
                <table width="100%" border="0">
  									<tr>
                            			<td width="30%"><div align="right"><strong>Urusan :</strong></div></td>
                            			<td width="70%">$socurusan</td>
                          			</tr>
   									<tr>
                            			<td width="30%"><div align="right"><strong>Suburusan :</strong></div></td>
                            			<td width="70%">$socsuburusan</td>
                          			</tr>
                           			<tr>
                            			<td><div align="right"><strong>Skrin :</strong></div></td>
                            			<td><input type="text" name="txtskrin" id="txtskrin" value="$!txtSkrin" /> </td>
                          			</tr>
                           			<tr>
                            			<td><div align="right"><strong>Keterangan :</strong></div></td>
                            			<td><input type="text" name="txtdesc" id="txtdesc" value="$!txtDesc" /> </td>
                          			</tr>                          			
                          			<tr>
                            			<td colspan="2"><div align="center">
	                            			<input type="button" name="btncari" id="btncari" value="Cari" onclick="byUrusan()" class="button">
                            			</div></td>
                  					</tr>
                          			<!-- <tr>
                            			<td><div align="right"><strong></strong></div></td>
                            			<td>&nbsp;</td>
                  					</tr>   -->              
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>Pilihan Skrin</legend>
                            	<table width="100%" border="0">

                  					<tr>
                            			<td colspan="2">
                  								#parse("app/utils/record_paging.jsp")
                            			</td>
                  					</tr>
                  					
                           			<tr>
                            			<td colspan="2"><div align="left"><strong></strong>
                              				<table width="100%" border="0">
                                				<tr class="table_header">
                                  					<td scope="col" width="3%"><strong>#</strong></td>
                                 					<td scope="col" width="42%"><strong>Keterangan</strong></td>
                                  					<td scope="col" width="17%"><strong>Urusan/ Suburusan</strong></td>
                                  					<td scope="col" width="10%"><strong>Skrin</strong></td>
                                  					<td scope="col" width="3%"><strong>Seq</strong></td>
                                  					<td scope="col" width="10%"><strong>Tarikh Kemasukan</strong></td>
                                  					<td scope="col" width="10%"><strong>Tarikh Kemaskini</strong></td>
                                  					<td scope="col" width="5%"></td>
                                				</tr>
                                                #set ($count = 0)
                                                #foreach ( $tanah in $SenaraiFail )
                                                ##foreach ( $tanah in $senaraidesc )
                                                #set ($count = $count+1)
                                                  #set( $i = $velocityCount )
                                                  #if ( ($i % 2) != 1 )
                                                       #set( $row = "row2" )
                                                  #else
                                                       #set( $row = "row1" )
                                                  #end
                                				<tr>
                                  				  <td class="$row" scope="row">$tanah.bil.</td>
                                                  <td class="$row">$tanah.keterangan</td>
                                                  <td class="$row">$tanah.urusan</td>
                                                  <td class="$row">$tanah.kod</td>
                                               	  <td class="$row">$tanah.aturan</td>
                                                  <td class="$row">$!tanah.tarikhmasuk</td>
                                                  <td class="$row">$!tanah.tarikhkemaskini</td>
                                                  <td class="$row">
                                                  	<a href="javascript:editKeteranganvs('$tanah.id','$!tanah.idUrusan','$!tanah.idSubUrusan','$tanah.idSemakan','$tanah.aturan','$tanah.kod')"><img src="../img/edit.gif" border="0"></a>
							                        <a href="javascript:deleteKeteranganvs('$tanah.id')"><img src="../img/delete.gif" border="0"></a>
                                                  </td>
                                     
                                				</tr>
                                                #end
                                              	#if ($count == 0)
                                              	<tr> 
                                                	<td colspan="11" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                                              	</tr>
                                              	#end
                                              </table>
                                            </div></td>
                                          </tr>

								</table>
                              </fieldset>
							</div></td>
                          </tr> 
                      <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>Maklumat baru</legend>
                            	<table width="100%" border="0">
  									<tr>
                            			<td width="30%"><div align="right"><strong>Keterangan :</strong></div></td>
                            			<td width="70%">$socdesc</td>
                          			</tr>
  									<!--  
  									<tr>
                            			<td width="30%"><div align="right"><strong>Sasaran Masa :</strong></div></td>
                            			<td width="70%">
                            			    <select class="autoselect" name="socsasaran" >
										        <option value="0">Sila Pilih</option>
										        #set ( $intsasar = 60 )
											#foreach ( $y in [1..$intsasar] )
												#if ( $y == $intsasar )
													#set ( $selected = "selected" )
												#else
													#set ( $selected = "" )
												#end
												<option value="$y" $selected>$y</option>
											#end
										    </select>
										    <select class="autoselect" name="socjsasaran" >
										        <option value="0">Sila Pilih</option>
										        <option value="1">JAM</option>
										        <option value="2">HARI</option>
										    </select>
                            			</td>
                          			</tr>
  									<tr>
                            			<td width="30%"><div align="right"><strong>Status Menunggu :</strong></div></td>
                            			<td width="70%">
                            				<select class="autoselect" name="socgiliran" >
										        <option value="0">Sila Pilih</option>
										        <option value="1">Tidak</option>
										        <option value="2"><5 Hari</option>
										        <option value="3"><7 Hari</option>
										        <option value="4"><30 Hari</option>
										    </select>
                            			</td>
                          			</tr>  -->                       			                          			
  									<tr>
                            			<td width="30%"><div align="right"><strong>Aturan :</strong></div></td>
                            			<td width="70%">
											 
												 #set ( $selected = "" )
												<select class="autoselect" name="socaturan">
													<option value="0" $selected>Sila Pilih</option>
													#set ( $ints = $senaraidesc.size()+1 )
													
													#foreach ( $y in [1..$ints] )
														#if ( $y == $year2 )
															#set ( $selected = "selected" )
														#else
															#set ( $selected = "" )
														#end
														<option value="$y" $selected>$y</option>
													#end
												</select>
                            			</td>
                          			</tr> 
                          			<tr>
                            			<td width="30%"><div align="right"><strong>Pilihan :</strong></div></td>
                            			<td width="70%">
                            				<select class="autoselect" name="socpilihan" >
										        <option value="0">TIDAK</option>
										        <option value="1">YA</option>
										    </select>
                            			</td>
                          			</tr> 
	
                          			<tr>
                            			<td><div align="right"><strong></strong></div></td>
                            			<td>&nbsp;</td>
                  					</tr>

                          		</table>
                              </fieldset>
							</div></td>
                          </tr>                                                    
                          			<tr>
										<td colspan="2"><div align="center">
											<input class="button" name="btnsubmitvs"  type="button" value="Tambah" onclick="tambahKeteranganvs()">
											<input class="button" type="reset" value="Batal" ></div>
										</td>
									</tr>
                    </table>
                    <input type="hidden" name="idPeguam" value="$IdPeguam">
                  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                	<input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="noFail" value="$NoFail">
                    <input type="hidden" name="style1" value="$Style1">
  					<input type="hidden" name="style2" value="$Style2">
                </div>
                <div class="TabbedPanelsContent">            
                
                #set ($btnNamePemilik = "value='Kosongkan'")
				#if ($IdPihakberkepentingan != "")
					#set ($btnNamePemilik = "value='Batal'")
				#end
                <!--<form name="pemilik" method="post"> -->
   
                <table width="99%" border="0">
                	<tr>
                		<td>           			
             				<fieldset><legend>Senarai</legend>					
                			<table width="100%" border="0">
								<tr>
			                		<td width="30%"><div align="right"><strong>Seksyen :</strong></div></td>
			                        <td width="70%">$socSeksyen</td>
								</tr>					
								<tr>
			                		<td width="30%"><div align="right"><strong>Urusan :</strong></div></td>
			                        <td width="70%">$socurusanstatus</td>
								</tr>
								<tr>
			                		<td width="30%"><div align="right"><strong>Sub Urusan :</strong></div></td>
			                        <td width="70%">$socsuburusanstatus</td>
								</tr>            
									<tr>
                            			<td colspan="2"><div align="center">
	                            			<input type="button" name="btncari" id="btncari" value="Cari" onclick="byStatus()" class="button">
                            			</div></td>
                  					</tr>    
		                  		<!-- <tr>
		                            			<td width="30%">
		                            				<div align="right"><strong>Keterangan :</strong></div>         				
		                            			</td>
		                            			<td width="70%">
		                            				<div align="left"><input type="text" name="txtdesc" id="txtdesc" value="$!txtDesc" /></div>
		                            			</td>
		                            			
		                         </tr>-->                          
                  					<tr>
                            			<td colspan="2">
                  								#parse("app/utils/record_paging.jsp")
                            			</td>
                  					</tr>
                  					<tr>
	                            			<td colspan="2"><div align="left"><strong></strong>
	                              				<table width="100%" border="0">
	                                				<tr class="table_header">
	                                  					<td scope="col" width="3%"><strong>#</strong></td>
	                                 					<td scope="col" width="62%"><strong>Keterangan</strong></td>
	                                  					<!--<td scope="col" width="20%"><strong>Status</strong></td>-->
	                                  					<td scope="col" width="30%"><strong>Jenis Dokumen</strong></td>
	                                  					<!-- <td scope="col" width="10%"><strong>Tarikh Kemasukan</strong></td>
	                                  					<td scope="col" width="10%"><strong>Tarikh Kemaskini</strong></td> -->
	                                  					<td scope="col" width="5%"></td>
	                                				</tr>
	                                                #set ($count = 0)
	                                                #foreach ( $tanah in $SenaraiFail )
	                                                ##foreach ( $tanah in $senaraidescstatus )
	                                                #set ($count = $count+1)
	                                                  #set( $i = $velocityCount )
	                                                  #if ( ($i % 2) != 1 )
	                                                       #set( $row = "row2" )
	                                                  #else
	                                                       #set( $row = "row1" )
	                                                  #end
	                                				<tr>
	                                  				  <td class="$row" scope="row">$count.</td>
	                                                  <td class="$row">$!tanah.keterangan</td>
	                                                  <td class="$row">$!tanah.namaDok</td>
	                                                  <!--<td class="$row">$tanah.sasaranmasa $tanah.jenissasaran</td> -->
	                                               <!--    <td class="$row">$!tanah.tarikhmasuk</td>
	                                                  <td class="$row">$!tanah.tarikhkemaskini</td> -->
	                                                  <td class="$row">
	                                                 	#if ($!tanah.id == '')
	                                                  	<a href="javascript:addKeteranganStatus('$tanah.keterangan','$tanah.idSemakan')"><img src="../img/plus.gif" border="0"></a>
	                                          			#end
	                                          			#if ($!tanah.id != '')
	                                                  	
	                                                  	<a href="javascript:editKeteranganStatus('$tanah.keterangan','$tanah.id','$tanah.idSemakan','$tanah.jenis')"><img src="../img/edit.gif" border="0"></a>
								                        #end
								                        <a href="javascript:deleteKeteranganStatus('$tanah.id')"><img src="../img/delete.gif" border="0"></a>
	                                                  </td>                               
	                                				</tr>
	                                                #end
	                                              	#if ($count == 0)
	                                              	<tr> 
	                                                	<td colspan="11" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
	                                              	</tr>
	                                              	#end
	                                              </table>
	                                            </div></td>
	                                          </tr>
	
							
	                	</td>                                     
	                            <tr>
	                            <td width="35%" valign="top"><div align="right"><strong><font color="#FF0000"></font>Keterangan :</strong></div></td>
	                            <td width="65%">
	                            <label>
	                  				<textarea name="keteranganstatus" cols="45" rows="3" >$!desc</textarea>
	                			</label>
	                			</td>
	                          </tr>                                          
	                                          
	                                          
	                            <tr>
	                            <td width="35%"><div align="right"><strong><font color="#FF0000">*</font>Jenis Dokumen :</strong></div></td>
	                            <td width="65%">$senaraistatus</td>
	                          </tr>
	                                 <tr>
	                            <td><div align="right"><strong></strong></div></td>
	                            <td>&nbsp;</td>
	                          </tr>

	                          <tr>
	                            <td>&nbsp;</td>
	                            <td>&nbsp;</td>
	                          </tr>                          
                    		</table>
                    		</fieldset>
                    	
                    		</td>
	                	</tr>
	             		<tr>
	                    	<td><div align="center">
	                            <input type="button" name="btnsubmitstatus" id="btnsubmitstatus" value="Simpan" onclick="simpanStatus()" class="button">
	                            <input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePemilik class="button">
	                    	</div></td>
	                    </tr>
                    </table>
                    <input type="hidden" name="idBebanan" value="$IdBebanan">
                    <input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">
                    <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                    <input type="hidden" name="idFail" value="$IdFail">
                  	<input type="hidden" name="style1" value="$Style1">
  					<input type="hidden" name="style2" value="$Style2">
                </div>
                <div class="TabbedPanelsContent">

                    <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
                    <input type="hidden" name="idPeguam" value="$IdPeguam">
                    <input type="hidden" name="idFail" value="$IdFail">
                  	<input type="hidden" name="pagemode">
                  	<input type="hidden" name="style1" value="$Style1">
  					<input type="hidden" name="style2" value="$Style2">
      				<input type="hidden" name="idkpiketerangan" >
      				<input type="hidden" name="idsemakan" >
                  
                  
                </div>
              </div>
            </div>
         </td>
      </tr>
</table>
</fieldset>

<script type="text/javascript">

//document.${formName}.command.value = "$!submitInternal";
	function byStatus() {
	 	doAjaxCall${formName}("bystatus");
	}

function tambahKeterangan() {

	if ( document.${formName}.btnsubmit.value == "Kemaskini" ) {
		document.${formName}.command.value = "kemaskiniketerangan";
	} else {
		document.${formName}.command.value = "tambahketerangan";
	}
	
	if(document.${formName}.keterangan.value == ""){
		alert('Sila masukkan maklumat Keterangan terlebih dahulu');
		document.${formName}.keterangan.focus();
		return;
	}
	if ( !window.confirm("Adakah anda pasti?") ) return;
		
	document.${formName}.action = "";
	document.${formName}.submit();
}

function editKeterangan(id, desc) {
	document.${formName}.idkpiketerangan.value = id;
	document.${formName}.keterangan.value = desc;
	document.${formName}.btnsubmit.value = "Kemaskini";
}

function deleteKeterangan(id) {
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.command.value = "delete";
	document.${formName}.idkpiketerangan.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

/*---------------------------Keterangan vs Urusan */
/* 	function carian() {
	 	doAjaxCall${formName}("byurusandefault");
	} */
	function byDefault() {
	 	doAjaxCall${formName}("");
	}
	function byUrusan() {
	 	doAjaxCall${formName}("byurusandefault");
	}

function doChangeUrusan() {
	if(document.${formName}.socUrusan.value=="")
		return;
	document.${formName}.pagemode.value = "view";
	doAjaxCall${formName}("byurusandefault");
}

function doChangeSubUrusan() {
	if(document.${formName}.socSuburusan.value=="")
		return;
	document.${formName}.pagemode.value = "bysuburusan";
 	doAjaxCall${formName}("byurusandefault");
}

function doChangeDesc() {
 	document.${formName}.pagemode.value = "view";
	doAjaxCall${formName}("byurusandefault");
}

function tambahKeteranganvs() {

	document.${formName}.command.value = "byurusandefault";
	if ( document.${formName}.btnsubmitvs.value == "Kemaskini" ) {
	 	document.${formName}.pagemode.value = "kemaskini";
	} else {
		//document.${formName}.command.value = "tambahketeranganvs";
		document.${formName}.pagemode.value = "tambahketeranganvs";
	}
	
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih maklumat Sub Urusan terlebih dahulu');
		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.socDesc.value == ""){
		alert('Sila pilih maklumat keterangan Urusan terlebih dahulu');
		document.${formName}.socDesc.focus();
		return;
	}
	if ( !window.confirm("Adakah anda pasti?") ) return;
		
	document.${formName}.action = "";
	document.${formName}.submit();
}

	function editKeteranganvs(id,idUrusan,idSubUrusan,desc,a,kod) {
		//document.${formName}.idkpiketerangan.focus();
		document.${formName}.idkpiketerangan.value = id;
		document.${formName}.socDesc.value = desc;
 		document.${formName}.txtskrin.value = kod;
		document.${formName}.socaturan.value = a;
		document.${formName}.socUrusan.value = idUrusan;
		document.${formName}.socSuburusan.value = idSubUrusan;
		/*document.${formName}.socpilihan.value = pilih;*/
		document.${formName}.btnsubmitvs.value = "Kemaskini"; 
	
	}

function deleteKeteranganvs(id) {
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.command.value = "byurusandefault";	
	document.${formName}.pagemode.value = "delete";
	document.${formName}.idkpiketerangan.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

	function byStatus() {
	 	doAjaxCall${formName}("bystatus");
	}

function doChangeUrusanStatus() {
	if(document.${formName}.socUrusanStatus.value=="")
		return;
 	document.${formName}.pagemode.value = "viewbyurusanstatus";
	doAjaxCall${formName}("bystatus");
}

function doChangeSubUrusanStatus() {
	if(document.${formName}.socSubUrusanStatus.value=="")
		return;
 	document.${formName}.pagemode.value = "viewbysuburusanstatus";
	doAjaxCall${formName}("bystatus");
}

	function addKeteranganStatus(desc, id) {
		document.${formName}.idkpiketerangan.focus();
		document.${formName}.idkpiketerangan.value = id;
		document.${formName}.keteranganstatus.value = desc;
		
	}

	function simpanStatus() {
	
		document.${formName}.command.value = "bystatus";
		if ( document.${formName}.btnsubmitstatus.value == "Kemaskini" ) {
			document.${formName}.pagemode.value = "kemaskinistatus";
		} else {
			document.${formName}.pagemode.value = "tambahstatus";
		}
				
/* 		if(document.${formName}.socSubUrusanStatus.value == ""){
			alert('Sila pilih maklumat Sub Urusan terlebih dahulu');
			document.${formName}.socSubUrusanStatus.focus();
			return;
		}
		if(document.${formName}.idstatus.value == ""){
			alert('Sila pilih maklumat Status terlebih dahulu');
			document.${formName}.idstatus.focus();
			return;
		} */
		if ( !window.confirm("Adakah anda pasti?") ) return;
			
		document.${formName}.action = "";
		document.${formName}.submit();
	}

	function editKeteranganStatus(desc,id,idsemakan,idc) {
		document.${formName}.idkpiketerangan.value = id;
		document.${formName}.keteranganstatus.value = desc;
		document.${formName}.idstatus.value = idc;
		document.${formName}.idsemakan.value = idsemakan;
	document.${formName}.btnsubmitstatus.value = "Kemaskini";
		
	}

function deleteKeteranganStatus(id) {
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.command.value = "bystatus";
	document.${formName}.pagemode.value = "delete";
	
	document.${formName}.idkpiketerangan.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function setSelected(tabId) {

    document.${formName}.tabId.value = tabId;
}

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>
