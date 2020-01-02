<!--frmMaklumatFailPaparKandungan.jsp-->

<!--   <table width="100%" border="0" cellspacing="2" cellpadding="2"> -->
	<tr>
    	<td>   	
			<fieldset>
			<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>

			  	<table width="100%" border="0" cellspacing="2" cellpadding="2">
				     <tr>
				        <td width="50%" valign="top">       
					        <table width="100%" border="0" cellspacing="2" cellpadding="2">
					          <tr>
					            <td width="36%" align="right">NEGERI</td>
					            <td width="1%">:</td>
					            <td width="63%"><font color="blue">$!lblNamaNegeri</font></td>
					          </tr>
					          <tr>
					            <td width="36%" align="right">DAERAH</td>
					            <td width="1%">:</td>
					            <td width="63%"><font color="blue">$!lblNamaDaerah</font></td>
					          </tr>
					          <tr>
					            <td align="right" valign="top">KEMENTERIAN</td>
					            <td valign="top">:</td>
					            <td><font color="blue">$!lblKementerian</font></td>
					          </tr>
					          <tr>
					            <td align="right">AGENSI</td>
					            <td>:</td>
					             <td><font color="blue">$!lblAgensi</font></td>
					          </tr>
					          <tr>
					            <td align="right">URUSAN</td>
					            <td>:</td>
					             <td><font color="blue">$!lblUrusan</font></td>
					          </tr>
					          <tr>
					            <td align="right">SUB URUSAN</td>
					            <td>:</td>
					             <td><font color="blue">$!lblNamaSubUrusan</font></td>
					          </tr>
					   		<!--
					          <tr>
					            <td align="right">STATUS TANAH</td>
					            <td>:</td>
					             <td><font color="blue">$beanHeader.statusTanah</font></td>
					          </tr>  
					          -->        
					          <tr>
					            <td align="right" valign="top">TAJUK</td>
					            <td valign="top">:</td>
					            <td valign="top"><font color="blue">$!txtTajuk</font></td>
					          </tr>
					        </table>
				        </td>
				        <td width="50%" valign="top">
					        <table width="100%" border="0" cellspacing="2" cellpadding="2">
					          <tr>
					            <td width="37%"  align="right">NO. FAIL SEKSYEN :</td>
					            <td width="63%"><font color="blue">$!noFail</font></td>
					          </tr>
					          <tr>
					            <td align="right">NO. FAIL KJP :</td>
					            <td><font color="blue">$!txtnoFailKJP</font></td>
					          </tr>
					          <tr>
					            <td align="right">TARIKH SURAT KJP :</td>
					            <td><font color="blue">$!txdTarikhSuratKJP</font></td>
					          </tr>
					          <tr>
					            <td align="right">NO. FAIL UPT :</td>
					            <td><font color="blue">$!txtnoFailUPT</font></td>
					          </tr>
					          <tr>
					            <td align="right">NO. FAIL NEGERI :</td>
					            <td><font color="blue">$!txtnoFailNegeri</font></td>
					          </tr>
					          <tr>
					            <td align="right">
				 						#parse ("app/htp/permohonan/utiliti/frmPejabatTanahLabelScript.jsp")
					             :</td>
					            <td><font color="blue">$!txtnoFailPTG</font></td>
					          </tr>
					          	#if($!idNegeriNotis=="13" || $!idNegeriNotis=="12")

								#else
					          
					          <tr>
					            <td align="right">NO. FAIL PTD :</td>
					            <td><font color="blue">$!txtnoFailPTD</font></td>
					          </tr>
								#end
					          <tr >
					            <td align="right">STATUS TANAH :</td>
					            <td><font color="blue">$!lblKeterangan</font></td>
					          </tr>
					          <tr>
					            <td align="right">JENIS FAIL :</td>
					            <td><font color="blue">$!lblTarafKeselamatan</font></td>
					          </tr>
					          <!--
					          <tr>
					            <td align="right">STATUS :</td>
					            <td><font color="blue">$beanHeader.status</font></td>
					          </tr>
					          -->
					        </table>
				
				        </td>
				      </tr>	 
				</table>
			
			</fieldset>		
    	</td>
	</tr>
	
<!--  </table> -->

