<div id="TabbedPanels2" class="TabbedPanels">
	<ul class="TabbedPanelsTabGroup">
   		<li class="TabbedPanelsTab" tabindex="0">PERINTAH TERUS</li>
        <li class="TabbedPanelsTab" tabindex="0">PERINTAH KUASA TADBIR</li>
        <li class="TabbedPanelsTab" tabindex="0">PERINTAH LELONG</li>
        <li class="TabbedPanelsTab" tabindex="0">PERINTAH FARAID</li>
        <li class="TabbedPanelsTab" tabindex="0">PERINTAH PEMBAHAGIAN AKTA 1958</li><!-- arief add -->
	</ul>
	<div class="TabbedPanelsContentGroup">
    	<!-- START CONTENT PERINTAH TERUS -->
   		<div class="TabbedPanelsContent">
   			<div id="div_keputusan_perintah_htapt"></div>
       		<table width="100%" border="0" cellspacing="2" cellpadding="2">

            	#if ($flagPopup != 'openHAPT')
		        <!-- START LIST HTAPT -->
            	<tr>
                	<td>
                  		<fieldset>
                   			<legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
	                      	#if($flagAdaHTAPT)

	                      	#if ($SenaraiHTAPT.size() > 10)
                    			<div style="width:100%;height:210;overflow:auto">
                    		#end

                       		<table width="100%" border="0" cellspacing="2" cellpadding="2">
                          		<tr class="table_header">
		                            <td scope="row" width="5%" align="center">BIL</td>
		                            <td width="65%">KETERANGAN HAKMILIK</td>
		                            <td width="15%" align="center">STATUS PEMILIKAN</td>
		                            <td width="15%" align="center">JENIS TANAH</td>
	                          	</tr>

	                          	#set ($listHTAPT = "")
	                          	#foreach ($listHTAPT in $SenaraiHTAPT)
	                          	#if ($listHTAPT.bil == '')
	                          	#set( $row = "row1" )
	                          	#elseif (($listHTAPT.bil % 2) != 0)
	                          	#set( $row = "row1" )
	                          	#else
	                          	#set( $row = "row2" )
	                          	#end

	                          	<tr>
		                            <td class="$row" align="center">$listHTAPT.bil</td>
		                            #if($listHTAPT.idPerintahHTAOBMST == '')
		                            <td class="$row">$listHTAPT.keteranganHakmilik</td>
		                            #else

		                            <td class="$row">
		                            	<a href="javascript:void(0);"
		                            		onClick="paparFmPerintahHTAPT('div_keputusan_perintah_htapt', 'fmKeputusanPerintahHtapt', '$listHTAPT.idPerintahHTAOBMST')">
		                            		<font color="#0000FF">$listHTAPT.keteranganHakmilik</font>
		                            	</a>
		                            </td>

		                            ###<!-- td class="$row"><a href="javascript:paparHTAPT('$listHTAPT.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPT.keteranganHakmilik</font></a></td -->

		                            <td class="$row" align="center">$listHTAPT.kodPB</td>
		                            <td class="$row" align="center">$listHTAPT.jenisTanah</td>
	                            	#end
	                            </tr>
                          		#end
                       		</table>

                       		#if ($SenaraiHTAPT.size() > 10)
                       		</div>
                     		#end

	                      	#else <br>
	                      	&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
	                      	<br>
       						#end
                   		</fieldset>
               		</td>
           		</tr>
                <!-- END LIST HTAPT -->
                #end

                ###if ($flagPopup != 'openHTAPT')
                <!-- START LIST HAPT -->
                <tr>
                	<td>
                		<fieldset>
                      	<legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>

                      	#if($flagAdaHAPT)

                      	#if ($SenaraiHAPT.size() > 10)
                      	<div style="width:100%;height:190;overflow:auto">
                      	#end

                        <table align="center" width="100%">
                          	<tr class="table_header">
                            	<td scope="row" width="5%" align="center">BIL</td>
                            	<td width="35%">JENIS HARTA ALIH</td>
                            	<td width="30%">JENAMA/MODEL/BANK</td>
                            	<td width="30%">NO. PENDAFTARAN/AKAUN</td>
                          	</tr>

                          	#set ($listHAPT = "")
                          	#foreach ($listHAPT in $SenaraiHAPT)
                          	#if ($listHAPT.bil == '')
                          	#set( $row = "row1" )
                          	#elseif (($listHAPT.bil % 2) != 0)
                          	#set( $row = "row1" )
                          	#else
                          	#set( $row = "row2" )
                          	#end

                          	<tr>
                            	<td class="$row" align="center">$listHAPT.bil</td>
                            	#if($listHAPT.idPerintahHAOBMST == '')
                            	<td class="$row">$listHAPT.jenisHA</td>
                            	#else
                            	<td class="$row"><a href="javascript:paparHAPT('$listHAPT.idPerintahHAOBMST')"><font color="#0000FF">$listHAPT.jenisHA.toUpperCase()</font><font color="#000000">$listHAPT.keterangan</font></a></td>
                            	<td class="$row">$listHAPT.jenama.toUpperCase()</td>
                            	<td class="$row">$listHAPT.noDaftar.toUpperCase()</td>
                            	#end
                            </tr>
                          	#end
                        </table>

                        #if ($SenaraiHAPT.size() > 10)
                        </div>
                      	#end

                      	#else <br>
                      	&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      	<br>
                      	#end
                    	</fieldset>
                	</td>
                </tr>
                <!-- END LIST HAPT -->
                ###end

        	</table>
		</div>

            <!-- END CONTENT PERINTAH TERUS -->
            <!-- START CONTENT PERINTAH KUASA TADBIR -->

		<div class="TabbedPanelsContent">
           	<table width="100%" border="0" cellspacing="2" cellpadding="2">

               	###if ($flagPopup != 'openHAPKT')
               	<!-- START LIST HTAPKT -->
               	<tr>
               		<td>
               		<fieldset>
                     		<legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                     		#if($flagAdaHTAPKT)

                     		#if ($SenaraiHTAPKT.size() > 10)
                     		<div style="width:100%;height:190;overflow:auto">
                     		#end

                       	<table align="center" width="100%">
                         		<tr class="table_header">
                           		<td scope="row" width="5%" align="center">BIL</td>
                           		<td width="65%">KETERANGAN HAKMILIK</td>
                           		<td width="15%" align="center">STATUS PEMILIKAN</td>
                           		<td width="15%" align="center">JENIS TANAH</td>
                         		</tr>

                         		#set ($listHTAPKT = "")
                         		#foreach ($listHTAPKT in $SenaraiHTAPKT)
                         		#if ($listHTAPKT.bil == '')
                         		#set( $row = "row1" )
                         		#elseif (($listHTAPKT.bil % 2) != 0)
                         		#set( $row = "row1" )
                         		#else
                         		#set( $row = "row2" )
                         		#end

                         		<tr>
                           		<td class="$row" align="center">$listHTAPKT.bil</td>
                          			#if($listHTAPKT.idPerintahHTAOBMST == '')
                           		<td class="$row">$listHTAPKT.keteranganHakmilik</td>
                           		#else
                           		<td class="$row"><a href="javascript:paparHTAPKT('$listHTAPKT.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPKT.keteranganHakmilik</font><font color="#000000">$listHTAPKT.keterangan</font></a></td>
                           		<td class="$row" align="center">$listHTAPKT.kodPB</td>
                           		<td class="$row" align="center">$listHTAPKT.jenisTanah</td>
                           		#end
                           	</tr>
                         		#end
                       	</table>

                        #if ($SenaraiHTAPKT.size() > 10)
                        </div>
                      	#end

                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                   	</fieldset>
                   </td>
               </tr>
               <!-- END LIST HTAPKT -->
               ###end

               ###if ($flagPopup != 'openHTAPKT')
               <!-- START LIST HAPKT -->
               <tr>
              		<td>
              			<fieldset>
                     	<legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>

                     		#if($flagAdaHAPKT)
                      	<table align="center" width="100%">
	                        <tr class="table_header">
                          		<td scope="row" width="5%" align="center">BIL</td>
	                          	<td width="35%">JENIS HARTA ALIH</td>
	                          	<td width="30%">JENAMA/MODEL/BANK</td>
	                          	<td width="30%">NO. PENDAFTARAN/AKAUN</td>
	                        </tr>

                        	#set ($listHAPKT = "")
                        	#foreach ($listHAPKT in $SenaraiHAPKT)
                        	#if ($listHAPKT.bil == '')
                        	#set( $row = "row1" )
                        	#elseif (($listHAPKT.bil % 2) != 0)
                        	#set( $row = "row1" )
                        	#else
                        	#set( $row = "row2" )
                        	#end

                        	<tr>
	                          	<td class="$row" align="center">$listHAPKT.bil</td>
	                          	#if($listHAPKT.idPerintahHAOBMST == '')
	                          	<td class="$row">$listHAPKT.jenisHA</td>
	                          	#else
	                          	<td class="$row"><a href="javascript:paparHAPKT('$listHAPKT.idPerintahHAOBMST')"><font color="#0000FF">$listHAPKT.jenisHA</font><font color="#000000">$listHAPKT.keterangan</font></a></td>
	                          	<td class="$row">$listHAPKT.jenama</td>
	                          	<td class="$row">$listHAPKT.noDaftar</td>
                          		#end
                          	</tr>
                        	#end
                     		</table>

                      	#else <br>
                      	&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      	<br>
                      	#end
                   	</fieldset>
                   </td>
               </tr>
               <!-- END LIST HAPKT -->
               ###end
             </table>
		</div>
            <!-- END CONTENT PERINTAH KUASA TADBIR -->
            <!-- START CONTENT PERINTAH LELONG -->
        <div class="TabbedPanelsContent">
       		<table width="100%" border="0" cellspacing="2" cellpadding="2">

                ###if ($flagPopup != 'openHAPL')
                <!-- START LIST HTAPL -->
                <tr>
                	<td>
                  		<fieldset>
                      	<legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>

                      	#if($flagAdaHTAPL)

                      	#if ($SenaraiHTAPL.size() > 10)
                      	<div style="width:100%;height:250;overflow:auto">
                      	#end

                   			<table align="center" width="100%">
                          		<tr class="table_header">
                            		<td scope="row" width="5%" align="center">BIL</td>
                            		<td width="40%">KETERANGAN HAKMILIK</td>
                            		<td width="15%">JENIS LELONG</td>
                            		<td width="10%" align="center">TARIKH JUALAN</td>
                            		<td width="15%" align="right">HARGA RIZAB (RM)</td>
                            		<td width="15%" align="right">AMAUN (RM)</td>
                          		</tr>

	                          	#set ($listHTAPL = "")
	                          	#foreach ($listHTAPL in $SenaraiHTAPL)
	                          	#if ($listHTAPL.bil == '')
	                          	#set( $row = "row1" )
	                          	#elseif (($listHTAPL.bil % 2) != 0)
	                          	#set( $row = "row1" )
	                          	#else
	                          	#set( $row = "row2" )
	                          	#end

                          		<tr>
	                            	<td class="$row" align="center">$listHTAPL.bil</td>
	                            	#if($listHTAPL.idPerintahHTAOBMST == '')
	                            	<td class="$row">$listHTAPL.keteranganHakmilik</td>
	                            	#else
	                            	<td class="$row"><a href="javascript:paparHTAPL('$listHTAPL.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPL.keteranganHakmilik</font></a></td>
	                            	<td class="$row">$listHTAPL.jenisLelong</td>
	                            	<td class="$row">$listHTAPL.tarikhJualan</td>
	                            	<td class="$row" align="right">$listHTAPL.hargaRizab</td>
	                            	<td class="$row" align="right">$listHTAPL.amaun</td>
                           			#end
                           		</tr>
                          		#end
                        	</table>

                        	#if ($SenaraiHTAPL.size() > 10)
                        	</div>
                      		#end

                      		#else <br>
	                      	&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
	                      	<br>
                      		#end
                    	</fieldset>
                   	</td>
                </tr>
                <!-- END LIST HTAPL -->
                ###end

               	###if ($flagPopup != 'openHTAPL')
               	<!-- START LIST HAPL -->
               	<tr>
                 	<td>
                 		<fieldset>
                     		<legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>

                   			#if($flagAdaHAPL)

                   			<table align="center" width="100%">
		                        <tr class="table_header">
		                          	<td scope="row" width="5%" align="center">BIL</td>
		                          	<td width="20%">JENIS HARTA ALIH</td>
		                          	<td width="20%">NO. PENDAFTARAN/AKAUN</td>
		                          	<td width="15%">JENIS LELONG</td>
		                          	<td width="10%" align="center">TARIKH JUALAN</td>
		                          	<td width="15%" align="right">HARGA RIZAB (RM)</td>
		                          	<td width="15%" align="right">AMAUN (RM)</td>
		                        </tr>

		                        #set ($listHAPL = "")
		                        #foreach ($listHAPL in $SenaraiHAPL)
		                        #if ($listHAPL.bil == '')
		                        #set( $row = "row1" )
		                        #elseif (($listHAPL.bil % 2) != 0)
		                        #set( $row = "row1" )
		                        #else
		                        #set( $row = "row2" )
		                        #end

                       			<tr>
		                          	<td class="$row" align="center">$listHAPL.bil</td>
		                          	#if($listHAPL.idPerintahHAOBMST == '')
		                          	<td class="$row">$listHAPL.jenisHA</td>
		                          	#else
		                          	<td class="$row"><a href="javascript:paparHAPL('$listHAPL.idPerintahHAOBMST')"><font color="#0000FF">$listHAPL.jenisHA</font><font color="#000000">$listHAPL.keterangan</font></a></td>
		                          	<td class="$row">$listHAPL.noDaftar</td>
		                          	<td class="$row">$listHAPL.jenisLelong</td>
		                          	<td class="$row">$listHAPL.tarikhJualan</td>
		                          	<td class="$row" align="right">$listHAPL.hargaRizab</td>
		                          	<td class="$row" align="right">$listHAPL.amaun</td>
		                          #end
                         			</tr>
                       			#end
                   			</table>

	                      	#else <br>
	                      	&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
	                      	<br>
	                      	#end
                   		</fieldset>
                  	</td>
               	</tr>
               <!-- END LIST HAPL -->
               ###end
             </table>
		</div>
            <!-- END CONTENT PERINTAH LELONG -->
            <!-- START CONTENT PERINTAH FARAID -->
        <div class="TabbedPanelsContent">
           	<table width="100%" border="0" cellspacing="2" cellpadding="2">

               	###if ($flagPopup != 'openHAPF')
               	<!-- START LIST HTAPF -->
               	<tr>
               		<td>
               			<fieldset>
                     		<legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>

                     		#if($flagAdaHTAPF)

	                      	#if ($SenaraiHTAPF.size() > 10)
	                      	<div style="width:100%;height:190;overflow:auto">
	                      	#end

	                        <table align="center" width="100%">
	                          	<tr class="table_header">
	                            	<td scope="row" width="5%" align="center">BIL</td>
	                            	<td width="65%">KETERANGAN HAKMILIK</td>
	                            	<td width="15%" align="center">STATUS PEMILIKAN</td>
	                            	<td width="15%" align="center">JENIS TANAH</td>
	                          	</tr>

	                          	#set ($listHTAPF = "")
	                          	#foreach ($listHTAPF in $SenaraiHTAPF)
	                          	#if ($listHTAPF.bil == '')
	                          	#set( $row = "row1" )
	                          	#elseif (($listHTAPF.bil % 2) != 0)
	                          	#set( $row = "row1" )
	                          	#else
	                          	#set( $row = "row2" )
	                          	#end

	                          	<tr>
	                            	<td class="$row" align="center">$listHTAPF.bil</td>
	                            	#if($listHTAPF.idPerintahHTAOBMST == '')
	                            	<td class="$row">$listHTAPF.keteranganHakmilik</td>
	                            	#else
	                            	<td class="$row"><a href="javascript:paparHTAPF('$listHTAPF.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPF.keteranganHakmilik</font></a></td>
	                            	<td class="$row" align="center">$listHTAPF.kodPB</td>
	                            	<td class="$row" align="center">$listHTAPF.jenisTanah</td>
	                            	#end
	                            </tr>
                         			#end
                       		</table>

	                        #if ($SenaraiHTAPF.size() > 10)
	                        </div>
	                      	#end

	                      	#else <br>
	                      	&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
	                      	<br>
	                      	#end
                   		</fieldset>
                   	</td>
               	</tr>
               <!-- END LIST HTAPF -->
               ###end

               ###if ($flagPopup != 'openHTAPF')
               ##<!-- START LIST HAPF -->
                <tr>
                 		<td>
                  		<fieldset>
                      	<legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>

                     		#if($flagAdaHAPF)

                      	#if ($SenaraiHAPF.size() > 10)
                     		<div style="width:100%;height:190;overflow:auto">
                     		#end

                        <table align="center" width="100%">
                         		<tr class="table_header">
                            	<td scope="row" width="5%" align="center">BIL</td>
	                            <td width="35%">JENIS HARTA ALIH</td>
	                            <td width="30%">JENAMA/MODEL/BANK</td>
	                            <td width="30%">NO. PENDAFTARAN/AKAUN</td>
                          	</tr>

                         		#set ($listHAPF = "")
                         		#foreach ($listHAPF in $SenaraiHAPF)
                         		#if ($listHAPF.bil == '')
                         		#set( $row = "row1" )
                         		#elseif (($listHAPF.bil % 2) != 0)
                         		#set( $row = "row1" )
                         		else
                         		#set( $row = "row2" )
                         		#end

                         		<tr>
                           		<td class="$row" align="center">$listHAPF.bil</td>
                           		#if($listHAPF.idPerintahHAOBMST == '')
                           		<td class="$row">$listHAPF.jenisHA</td>
                           		#else
                           		<td class="$row"><a href="javascript:paparHAPF('$listHAPF.idPerintahHAOBMST')"><font color="#0000FF">$listHAPF.jenisHA.toUpperCase()</font><font color="#000000">$listHAPF.keterangan</font></a></td>
                           		<td class="$row">$listHAPF.jenama.toUpperCase()</td>
                           		<td class="$row">$listHAPF.noDaftar</td>
                           		#end
                          		</tr>
                         		#end
                       	</table>

                       	#if ($SenaraiHAPF.size() > 10)
                       	</div>
                     		#end

                     		#else <br>
                     		&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                     		<br>
                     		#end
                   		</fieldset>
                   	</td>
                </tr>
               <!-- END LIST HAPF -->
               ###end
             </table>
		</div>

            ##<!-- END CONTENT PERINTAH FARAID -->
            ##<!-- arief add open -->
  			##<!-- START CONTENT PERINTAH AKTA 1958 -->

		<div class="TabbedPanelsContent">
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
				<!-- START OPEN POPUP HTAPA1958 -->

               ###if ($flagPopup != 'openHAPA1958')
               <!-- START LIST HTAPA1958 -->
               	<tr>
              		<td>
              			<fieldset>
                  		<legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>

                  		#if($flagAdaHTAPA1958)

                  		#if ($SenaraiHTAPA1958.size() > 10)
                  		<div style="width:100%;height:190;overflow:auto">
                  		#end

                   			<table align="center" width="100%">
	                       		<tr class="table_header">
		                          	<td scope="row" width="5%" align="center">BIL</td>
		                          	<td width="65%">KETERANGAN HAKMILIK</td>
		                          	<td width="15%" align="center">STATUS PEMILIKAN</td>
		                          	<td width="15%" align="center">JENIS TANAH</td>
	                       		</tr>

	                        	#set ($listHTAPA1958 = "")
	                        	#foreach ($listHTAPA1958 in $SenaraiHTAPA1958)
	                        	#if ($listHTAPA1958.bil == '')
	                        	#set( $row = "row1" )
	                        	#elseif (($listHTAPA1958.bil % 2) != 0)
	                        	#set( $row = "row1" )
	                        	#else
	                        	#set( $row = "row2" )
	                        	#end

                      			<tr>
                        			<td class="$row" align="center">$listHTAPA1958.bil</td>
                        			#if($listHTAPA1958.idPerintahHTAOBMST == '')
                        			<td class="$row">$listHTAPA1958.keteranganHakmilik</td>
                        			#else
                        			<td class="$row"><a href="javascript:paparHTAPA1958('$listHTAPA1958.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPF.keteranganHakmilik</font></a></td>
                        			<td class="$row" align="center">$listHTAPA1958.kodPB</td>
                        			<td class="$row" align="center">$listHTAPA1958.jenisTanah</td>
                        			#end
                       			</tr>
                      			#end
                    		</table>

                   		#if ($SenaraiHTAPA1958.size() > 10)
                   		</div>
                  		#end

                  		#else <br>
                  		&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                  		<br>
                  		#end
                		</fieldset>
               		</td>
               	</tr>
               <!-- END LIST HTAPA1958 -->
               ###end

               ###if ($flagPopup != 'openHTAPA1958')
               <!-- START LIST HAPA1958 -->
               	<tr>
               		<td>
               			<fieldset>
                     		<legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>

                     		#if($flagAdaHAPA1958)

                     		#if ($SenaraiHAPA1958.size() > 10)
                 			<div style="width:100%;height:190;overflow:auto">
                     		#end

	                        <table align="center" width="100%">
                          		<tr class="table_header">
		                            <td scope="row" width="5%" align="center">BIL</td>
		                            <td width="35%">JENIS HARTA ALIH</td>
		                            <td width="30%">JENAMA/MODEL/BANK</td>
		                            <td width="30%">NO. PENDAFTARAN/AKAUN</td>
	                          	</tr>

	                          	#set ($listHAPA1958 = "")
                          		#foreach ($listHAPA1958 in $SenaraiHAPFA1958)
		                        #if ($listHAPA1958.bil == '')
		                        #set( $row = "row1" )
		                        #elseif (($listHAPA1958.bil % 2) != 0)
		                        #set( $row = "row1" )
		                        #else
		                        #set( $row = "row2" )
		                        #end

	                          	<tr>
		                            <td class="$row" align="center">$listHAPA1958.bil</td>
		                            #if($listHAPA1958.idPerintahHAOBMST == '')
		                            <td class="$row">$listHAPA1958.jenisHA</td>
		                            #else
		                            <td class="$row"><a href="javascript:paparHAPA1958('$listHAPA1958.idPerintahHAOBMST')"><font color="#0000FF">$listHAPF.jenisHA.toUpperCase()</font><font color="#000000">$listHAPA1958.keterangan</font></a></td>
		                            <td class="$row">$listHAPA1958.jenama.toUpperCase()</td>
		                            <td class="$row">$listHAPA1958.noDaftar</td>
	                            	#end
	                            </tr>
	                          #end
	                        </table>

                        	#if ($SenaraiHAPAKTA1958.size() > 10)
	                        </div>
                     			#end

                     			#else <br>
                      		&nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      		<br>
                   			#end
                   		</fieldset>
                  		</td>
           		</tr>
               <!-- END LIST HAPA1958 -->
               ###end
       		</table>
		</div>
            	<!-- END CONTENT PERINTAH AKTA 1958 -->
	</div>
</div>