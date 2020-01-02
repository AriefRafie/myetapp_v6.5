#if($tabId == "1")
	#set ($Penggal = $session.getAttribute("_portal_moduleVector"))
    #set ($startnoPenggal = $startnoInt.intValue())
	#set ($iPenggal = $startnoPenggal)
	#set ($totalPenggal = $totalInt.intValue())	
#end
#if($tabId == "2")
	#set ($Bahagian = $session.getAttribute("_portal_moduleVector"))
    #set ($startno = $startnoInt.intValue())
	#set ($i = $startno)
	#set ($total = $totalInt.intValue())	

#end
#if($tabId == "3")
	#set ($Bab = $session.getAttribute("_portal_moduleVector"))
    #set ($startno= $startnoInt.intValue())
	#set ($i = $startno)
	#set ($total = $totalInt.intValue())	

#end

<script src="../../../../testing/SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="../../../../testing/SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />	
	<style type="text/css">
	<!--
	.style4 {color: #FF0000}
.style1 {color: #0000FF}
	-->
	</style>
    <style type="text/css">
<!--
.style2 {font-size: smaller}
.style3 {font-size: 11.5px}
.style4 {color: #FF0000}
-->
</style>
	<input name="action" type="hidden" value="$action" />
	<input name="mode" type="hidden" value="$mode" />
    <input name="update" type="hidden" value="$update" />
	<input name="hitbutton" type="hidden" value="$hitbutton" />
	<input name="tabId" type="hidden" id="tabId" value="$tabId"/>
	<input name="idAkta" type="hidden" id="idAkta" value="$idAkta"/>
    <input name="idKeselamatan" type="hidden" id="idKeselamatan" value="$idKeselamatan"/>
	<input name="checked" type="hidden" value="$checked"/>
    <input name="idPenggal" type="hidden" value="$idPenggal"/>
    <input name="idBahagian" type="hidden" value="$idBahagian"/>
    <input name="idBab" type="hidden" value="$idBab"/>
    <input name="idSeksyen" type="hidden" value="$idSeksyen"/>
    <input name="idSubSeksyen" type="hidden" value="$idSubSeksyen"/>
    <input name="idPara" type="hidden" value="$idPara"/>
    <input name="idJadual" type="hidden" value="$idJadual"/>
    <input name="idSubPara" type="hidden" value="$idSubPara"/>
    <input name="idJadualPara" type="hidden" value="$idJadualPara"/>
    <input name="idJadualSubPara" type="hidden" value="$idJadualSubPara"/>
    <input name="idJadualSubSubPara" type="hidden" value="$idJadualSubSubPara"/>
    
	&nbsp;
	<table width="100%" border="1" cellpadding="2">
	
		<tr>
		  <td>
			<div id="TabbedPanels1" class="TabbedPanels">
				<ul class="TabbedPanelsTabGroup">
                #if($action != "tambah")
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(0);"><strong>Akta</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(1);"><strong>Penggal</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(2);"><strong>Bahagian</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(3);"><strong>Bab</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(4);"><strong>Seksyen</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(5);"><strong>Subseksyen</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(6);"><strong>Para</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(7);"><strong>Subpara</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(8);"><strong>Jadual Para</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(9);"><strong>Jadual Subpara</strong></li>
				  <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(10);"><strong>Jadual Sub Subpara</strong></li>
                 #elseif($action == "tambah")
                 <li class="TabbedPanelsTab style3" tabindex="0" onClick="javascript:setSelected(0);"><strong>Akta</strong></li>
                 #end
				</ul>
				<div class="TabbedPanelsContentGroup">
				  <div class="TabbedPanelsContent">
				  <fieldset>
				  <legend><strong>Maklumat Akta</strong></legend>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta1" id="txtNoAkta1" value="$txtNoAkta" $readOnly/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				  <input type="text" name="txtNamaAkta1" id="txtNamaAkta1" value="$txtNamaAkta" $readOnly/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Tarikh Kuatkuasa</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtTarikhKuatkuasa" type="text" id="txtTarikhKuatkuasa" size="10" value="$txtTarikhKuatkuasa" $readOnly/>
				  <a href="javascript:displayDatePicker('txtTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Tarikh Mansuh</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtTarikhMansuh" type="text" id="txtTarikhMansuh" value="$txtTarikhMansuh" size="10" $readOnly/>
				  <a href="javascript:displayDatePicker('txtTarikhMansuh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">No Warta</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" $readOnly/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Tarikh Warta</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtTarikhWarta" type="text" id="txtTarikhWarta" value="$txtTarikhWarta" size="10" $readOnly/>
				  <a href="javascript:displayDatePicker('txtTarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Taraf Keselamatan Akta</td>
				<td scope="row">:</td>
				<td><label>
				  <input type="radio" name="sorTerbuka" id="sorTerbuka" onclick="check_tarafT()" $radioChecked1 $disabled/>
				  Terbuka
				  <input type="radio" name="sorSulit" id="sorSulit" onclick="check_tarafS()" $radioChecked2 $disabled/>
				  Sulit</label></td>
			  </tr>
			  <tr>
				<td scope="row">Seksyen / Urusetia</td>
				<td scope="row">:</td>
				<td><label>
				  $selectSeksyen
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">No Fail</td>
				<td scope="row">:</td>
				<td><label>$selectFail</label></td>
			  </tr>
			  <tr>
				<td scope="row">Catatan</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtCatatan" type="text" id="txtCatatan" value="$txtCatatan" $readOnly/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Lampiran</td>
				<td scope="row">:</td>
				<td><label>
				<input type="file" name="txfLampiran" id="txfLampiran" />
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Tarikh Daftar</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtTarikhDaftar" type="text" id="txtTarikhDaftar" size="10" value =$txtTarikhDaftar readOnly/>
				</label></td>
			  </tr>
			  
			</table>
				</fieldset>
			<fieldset><legend><strong>Senarai Pegawai</strong></legend>
			<p>
			  <label>
			  <input type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" />
			  </label>
			</p>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="4%" scope="row">No</td>
				<td width="96%" scope="row">Nama Pegawai</td>
			  </tr>
			  <tr>
				<th scope="row">&nbsp;</th>
				<th scope="row">&nbsp;</th>
			  </tr>
			</table>
			</fieldset>
			</div>
				  <div class="TabbedPanelsContent">
				  <fieldset>
				  <legend><strong>Maklumat Penggal</strong></legend>
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
						<td width="1%" scope="row">:</td>
						<td width="70%"><label>
						<input type="text" name="txtNoAkta2" id="txtNoAkta2" value="$txtNoAkta" $readOnly2/>
						</label></td>
					  </tr>
					  <tr>
						<td scope="row"><span class="style4">*</span>Nama Akta</td>
						<td scope="row">:</td>
						<td><label>
						<input name="txtNamaAkta2" type="text" id="txtNamaAkta2" value="$txtNamaAkta" $readOnly2//>
						</label></td>
					  </tr>
					  <tr>
						<td scope="row">No Penggal</td>
						<td scope="row">:</td>
						<td><label>
						  <input name="txtNoPenggal" type="text" id="txtNoPenggal" value="$txtNoPenggal" $readOnly/>
						</label></td>
					  </tr>
					  <tr>
						<td scope="row">Tajuk Penggal</td>
						<td scope="row">:</td>
						<td><label>
						  <input name="txtTajukPenggal" type="text" id="txtTajukPenggal" value="$txtTajukPenggal" $readOnly/>
						</label></td>
					  </tr>
                
					</table>
					</fieldset>
					<fieldset><legend><strong>Senarai Penggal</strong></legend>
                    <table width="100%" cellspacing="0" cellpadding="2">
                       <tr>
                         <td colspan="3" align="right"> #if ( $iPenggal >= $Penggal.size())
       					<input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
        				#else
        				<input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" disabled="disabled" align="right" />
        				#end
        				  #if (($iPenggal < $totalPenggal && $Penggal.size() != $totalPenggal))
        				<input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
        				#else
       					 <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" align="right" />
       					#end 
                        </td>
                      </tr>    
                    </table>
					<table width="100%" cellspacing="0" cellpadding="2">
					  <tr class="table_header">
						<td width="4%" scope="row">No</td>
						<td width="25%">No Penggal</td>
						<td width="25%">Tajuk Penggal</td>
					  </tr>
                       #set ($penggal = '')
       		  			 #foreach ($penggal in $Penggal)
   			     			#if ($penggal.no == '') 
  			        			#set ($row = 'row1')
  		         			#elseif ($penggal.no % 2 != 0)
  			        			#set ($row = 'row1')
 		         			#else 
  			        			#set ($row = 'row2')
  		         			#end   
					  <tr>
						<td height="20" class="$row">$penggal.no</td>
                        #if($penggal.no != '')
						<td height="20" class="$row"><a href="javascript:edit_itemPenggal('$penggal.idPenggal')" class="style1">$penggal.noPenggal</a></td>
                        #else
                         <td height="20" class="$row">$penggal.noPenggal</td>
                        #end
                         <td height="20" class="$row">$penggal.tajukPenggal</td>
					  </tr>
                      #end
					</table>
					</fieldset>
				  </div>
				  <div class="TabbedPanelsContent">
				  <fieldset>
				  <legend><strong>Maklumat Bahagian</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta3" id="txtNoAkta3" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta3" type="text" id="txtNamaAkta3" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Penggal</td>
				<td scope="row">:</td>
				<td><label>$selectPenggal </label></td>
			  </tr>
			  <tr>
				<td scope="row">No Bahagian</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtNoBahagian" type="text" id="txtNoBahagian" value="$txtNoBahagian" $readOnly />
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Tajuk Bahagian</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtTajukBahagian" type="text" id="txtTajukBahagian" value="$txtTajukBahagian" $readOnly/>
				</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset><legend><strong>Senarai Bahagian</strong></legend>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="4%" scope="row">No</td>
				<td width="16%">Penggal</td>
				<td width="19%">No Bahagian</td>
				<td width="19%">Tajuk Bahagian</td>
			  </tr>
              #set ($bahagian = '')
       		    #foreach ($bahagian in $Bahagian)
   			    	#if ($bahagian.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($bahagian.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end                 
			  <tr>
				<td height="20" class="$row">$bahagian.no</td>
                 #if($bahagian.no != '')
				<td class="$row"><a href="javascript:edit_itemBahagian('$bahagian.idBahagian')" class="style1">$bahagian.tajukPenggal</a></td>
                 #else
                 <td class="$row">$bahagian.tajukPenggal</td>
                 #end
                 <td class="$row">$bahagian.noBahagian</td>
				 <td class="$row">$bahagian.tajukBahagian</td>
			  </tr>
            #end  
			</table>
			</fieldset>
				  </div>
				   <div class="TabbedPanelsContent">
				   <fieldset>
				   <legend><strong>Maklumat Bab</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta4" id="txtNoAkta4" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta4" type="text" id="txtNamaAkta4" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Penggal</td>
				<td scope="row">:</td>
				<td><label>$selectPenggalB </label></td>
			  </tr>
			  <tr>
				<td scope="row">Bahagian</td>
				<td scope="row">:</td>
				<td><label>
				  $selectBahagian
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">No Bab</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtNoBab" type="text" id="txtNoBab" value="$txtNoBab" $readOnly/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Tajuk Bab</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtTajukBab" type="text" id="txtTajukBab" value="$txtTajukBab" $readOnly />
				</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset>
			<legend><strong>Senarai Bab</strong></legend>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="4%" scope="row">No</td>
				<td width="19%">Penggal</td>
				<td width="13%">Bahagian</td>
				<td width="10%">No Bab</td>
				<td width="24%">Tajuk Bab</td>
			  </tr>
               #set ($bab = '')
       		    #foreach ($bab in $Bab)
   			    	#if ($bab.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($bab.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end    
			  <tr>
				<td height="20" class="$row">$bab.no</td>
                #if($bab.no != '')
				<td class="$row"><a href="javascript:edit_itemBab('$bab.idBab')" class="style1">$bab.tajukPenggal</a></td>
                #else
                <td class="$row">$bab.tajukPenggal</td>
                #end
				<td class="$row">$bab.tajukBahagian</td>
				<td class="$row">$bab.noBab</td>
                <td class="$row">$bab.tajukBab</td>
			  </tr>
              #end
			</table>
			</fieldset>
				  </div>
				   <div class="TabbedPanelsContent">
				   <fieldset>
				   <legend><strong>Maklumat Seksyen</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta5" id="txtNoAkta5" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta5" type="text" id="txtNamaAkta5" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Penggal</td>
				<td scope="row">:</td>
				<td><label>$selectPenggalS</label></td>
			  </tr>
			  <tr>
				<td scope="row">Bahagian</td>
				<td scope="row">:</td>
				<td><label>$selectBahagianS</label></td>
			  </tr>
			  <tr>
				<td scope="row">Bab</td>
				<td scope="row">:</td>
				<td><label>$selectBab</label></td>
			  </tr>
			  <tr>
				<td scope="row">Seksyen</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtSeksyen" type="text" id="txtSeksyen" value="$txtSeksyen" $readOnly/>
				</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset>
			<legend><strong>Senarai Seksyen</strong></legend>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="4%" scope="row">No</td>
				<td width="16%">Penggal</td>
				<td width="16%">Bahagian</td>
				<td width="19%">Bab</td>
				<td width="19%">Seksyen</td>
			  </tr>
              #set ($seksyen = '')
       		    #foreach ($seksyen in $Seksyen)
   			    	#if ($seksyen.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($seksyen.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end    
			  <tr>
				<td scope="row">$seksyen.no</td>
                #if ($seksyen.no!='')
				<td><a href="javascript:edit_itemSeksyen('$seksyen.idSeksyen')" class="style1">$seksyen.tajukPenggal</a></td>
				#else
                <td>$seksyen.tajukPenggal</td>
                #end
                <td>$seksyen.tajukBahagian</td>
				<td>$seksyen.tajukBab</td>
				<td>$seksyen.seksyen</td>
			  </tr>
              #end
			</table>
			</fieldset>
				  </div>
				   <div class="TabbedPanelsContent">
				   <fieldset>
				   <legend><strong>Maklumat Subsekysen</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta6" id="txtNoAkta6" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta6" type="text" id="txtNamaAkta6" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Penggal</td>
				<td scope="row">:</td>
				<td><label>$selectPenggalSS</label></td>
			  </tr>
			  <tr>
				<td scope="row">Bahagian</td>
				<td scope="row">:</td>
				<td><label>$selectBahagianSS</label></td>
			  </tr>
			  <tr>
				<td scope="row">Bab</td>
				<td scope="row">:</td>
				<td><label>$selectBabSS</label></td>
			  </tr>
			  <tr>
				<td scope="row">Seksyen</td>
				<td scope="row">:</td>
				<td><label>$selectSeksyenSS</label></td>
			  </tr>
			  <tr>
				<td scope="row">Subseksyen</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtSubseksyen" type="text" id="txtSubseksyen" value="$txtSubseksyen" $readOnly />
				</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset>
			<legend><strong>Senarai Subseksyen</strong></legend>

			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="4%" scope="row">No</td>
				<td width="16%">Penggal</td>
				<td width="16%">Bahagian</td>
				<td width="19%">Bab</td>
				<td width="19%">Seksyen</td>
				<td width="19%">Subseksyen</td>
			  </tr>
              #set ($subSksyen = '')
       		    #foreach ($subSksyen in $SubSeksyen)
   			    	#if ($subSksyen.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($subSksyen.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end  
			  <tr>
				<td scope="row">$subSksyen.no</td>
                #if($subSksyen.no != '') 
				<td><a href="javascript:edit_itemSubSeksyen('$subSksyen.idSubSeksyen')" class="style1">$subSksyen.tajukPenggal</a></td>
                #else
                <td>$subSksyen.tajukPenggal</td>
                #end
				<td>$subSksyen.tajukBab</td>
				<td>$subSksyen.tajukBahagian</td>
				<td>$subSksyen.seksyen</td>
				<td>$subSksyen.subSeksyen</td>
			  </tr>
              #end
			</table>
			</fieldset>
				  </div>
				   <div class="TabbedPanelsContent">
				   <fieldset>
				   <legend><strong>Maklumat Para</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta7" id="txtNoAkta7" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta7" type="text" id="txtNamaAkta7" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Penggal</td>
				<td scope="row">:</td>
				<td><label>$selectPenggalP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Bahagian</td>
				<td scope="row">:</td>
				<td><label>$selectBahagianP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Bab</td>
				<td scope="row">:</td>
				<td><label>$selectBabP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Seksyen</td>
				<td scope="row">:</td>
				<td><label>$selectSeksyenP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Subseksyen</td>
				<td scope="row">:</td>
				<td><label>$selectSubSeksyen</label></td>
			  </tr>
			  <tr>
				<td scope="row">Para</td>
				<td scope="row">:</td>
				<td scope="row"><label>
				  <input name="txtPara" type="text" id="txtPara" value="$txtPara" $readOnly/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Jadual</td>
				<td scope="row">:</td>
				<td scope="row"><label>
				  <input name="txtJadual" type="text" id="txtJadual" value="$txtJadual" $readOnly/>
				</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset>
			<legend><strong>Senarai Para</strong></legend>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="2%" scope="row">No</td>
				<td width="9%">Penggal</td>
				<td width="9%">Bahagian</td>
				<td width="10%">Bab</td>
				<td width="10%">Seksyen</td>
				<td width="10%">Subseksyen</td>
				<td width="10%">Para</td>
				<td width="15%">Jadual</td>
			  </tr>
              #set ($para = '')
       		    #foreach ($para in $Para)
   			    	#if ($para.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($para.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end 
			  <tr>
				<td height="23" scope="row">$para.no</td>
                #if($para.no!='')
				<td><a href="javascript:edit_itemPara('$para.idPara')" class="style1">$para.tajukPenggal</a></td>
                #else
                <td>$para.tajukPenggal</td>
				#end
                <td>$para.tajukBahagian</td>
				<td>$para.tajukBab</td>
				<td>$para.seksyen</td>
				<td>$para.subSeksyen</td>
				<td>$para.para</td>
				<td>$para.jadual</td>
			  </tr>
              #end
			</table>
			</fieldset>
				  </div>
				   <div class="TabbedPanelsContent">
				   <fieldset>
				   <legend><strong>Maklumat Subpara</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta8" id="txtNoAkta8" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta8" type="text" id="txtNamaAkta8" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Penggal</td>
				<td scope="row">:</td>
				<td><label>$selectPenggalSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Bahagian</td>
				<td scope="row">:</td>
				<td><label>$selectBahagianSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Bab</td>
				<td scope="row">:</td>
				<td><label>$selectBabSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Seksyen</td>
				<td scope="row">:</td>
				<td><label>$selectSeksyenSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Subseksyen</td>
				<td scope="row">:</td>
				<td><label>$selectSubSeksyenSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Para</td>
				<td scope="row">:</td>
				<td scope="row"><label>$selectPara</label></td>
			  </tr>
			  <tr>
			    <td scope="row">Subpara</td>
			    <td scope="row">&nbsp;</td>
			    <td scope="row"><input name="txtSubPara" type="text" id="txtSubPara" value="$txtSubPara" $readOnly/></td>
			    </tr>
			  <tr>
				<td scope="row">Jadual</td>
				<td scope="row">:</td>
				<td scope="row"><label>$selectJadual</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset>
			<legend><strong>Senarai Subpara</strong></legend>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="2%" scope="row">No</td>
				<td width="8%">Penggal</td>
				<td width="8%">Bahagian</td>
				<td width="9%">Bab</td>
				<td width="9%">Seksyen</td>
				<td width="9%">Subseksyen</td>
				<td width="9%">Para</td>
				<td width="9%">Subpara</td>
				<td width="15%">Jadual</td>
			  </tr>
              #set ($subPara = '')
       		    #foreach ($subPara in $SubPara)
   			    	#if ($subPara.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($subPara.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end 
			  <tr>
				<td scope="row">$subPara.no</td>
                 #if($subPara.no!='')
				<td><a href="javascript:edit_itemSubPara('$subPara.idSubPara')" class="style1">$subPara.tajukPenggal</a></td>
                #else
                <td>$subPara.tajukPenggal</td>
                #end
				<td>$subPara.tajukBahagian</td>
				<td>$subPara.tajukBab</td>
				<td>$subPara.seksyen</td>
				<td>$subPara.subSeksyen</td>
				<td>$subPara.para</td>
				<td>$subPara.subPara</td>
				<td>$subPara.jadual</td>
			  </tr>
              #end 
			</table>
			</fieldset>
				  </div>
				   <div class="TabbedPanelsContent">
				   <fieldset>
				   <legend><strong>Maklumat Jadual Para</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta9" id="txtNoAkta9" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta9" type="text" id="txtNamaAkta9" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Jadual</td>
				<td scope="row">:</td>
				<td><label>$selectJadualJP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Nama Jadual</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtNamaJadual" type="text" id="txtNamaJadual" value="$txtNamaJadual" $readOnly />
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Para</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtParaJP" type="text" id="txtParaJP" value="$txtParaJP" $readOnly/>
				</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset>
			<legend><strong>Senarai Jadual Para</strong></legend>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="4%" scope="row">No</td>
				<td width="16%">Jadual</td>
				<td width="16%">Nama Jadual</td>
				<td width="19%">Para</td>
			  </tr>
              #set ($jadualPara = '')
       		    #foreach ($jadualPara in $JadualPara)
   			    	#if ($jadualPara.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($jadualPara.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end               
			  <tr>
				<td scope="row">$jadualPara.no</td>
                #if($jadualPara.no != '')
				<td><a href="javascript:edit_itemJadualPara('$jadualPara.idJadualPara')" class="style1">$jadualPara.noJadual</a></td>
                #else
                <td>$jadualPara.noJadual</td>
                #end
				<td>$jadualPara.namaJadual</td>
				<td>$jadualPara.para</td>
			  </tr>
              #end
			</table>
			</fieldset>
				  </div>
				   <div class="TabbedPanelsContent">
				   <fieldset>
				   <legend><strong>Maklumat Jadual Subpara</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta10" id="txtNoAkta10" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta10" type="text" id="txtNamaAkta10" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Jadual</td>
				<td scope="row">:</td>
				<td><label>$selectJadualJSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Para</td>
				<td scope="row">:</td>
				<td><label>$selectParaJSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Subpara</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtSubParaJSP" type="text" id="txtSubParaJSP" value="$txtSubParaJSP" $readOnly/>
				</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset>
			<legend><strong>Senarai Jadual Subpara</strong></legend>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="4%" scope="row">No</td>
				<td width="16%">Jadual</td>
				<td width="16%">Para</td>
				<td width="19%">Subpara</td>
			  </tr>
              #set ($jadualSubPara = '')
       		    #foreach ($jadualSubPara in $JadualSubPara)
   			    	#if ($jadualSubPara.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($jadualSubPara.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end                 
			  <tr>
              <td scope="row">$jadualSubPara.no</td>
              #if($jadualSubPara.no != '')
			   <td scope="row"><a href="javascript:edit_itemJadualSubPara('$jadualSubPara.idJadualSubPara')" class="style1">$jadualSubPara.noJadual</a></td>
               #else
               <td scope="row">$jadualSubPara.noJadual</td>
               #end
				<td>$jadualSubPara.para</td>
				<td>$jadualSubPara.subPara</td>
			  </tr>
              #end
			</table>
			</fieldset>
				  </div>
				   <div class="TabbedPanelsContent">
				   <fieldset>
				   <legend><strong>Maklumat Jadual Sub Subpara</strong></legend>
				  <table width="100%">
			  <tr>
				<td width="29%" scope="row"><span class="style4">*</span>No Akta</td>
				<td width="1%" scope="row">:</td>
				<td width="70%"><label>
				<input type="text" name="txtNoAkta11" id="txtNoAkta11" value="$txtNoAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row"><span class="style4">*</span>Nama Akta</td>
				<td scope="row">:</td>
				<td><label>
				<input name="txtNamaAkta11" type="text" id="txtNamaAkta11" value="$txtNamaAkta" $readOnly2/>
				</label></td>
			  </tr>
			  <tr>
				<td scope="row">Jadual</td>
				<td scope="row">:</td>
				<td><label>$selectJadualJSSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Para</td>
				<td scope="row">:</td>
				<td><label>$selectParaJSSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Subpara</td>
				<td scope="row">:</td>
				<td><label>$selectSubParaJSSP</label></td>
			  </tr>
			  <tr>
				<td scope="row">Sub Subpara</td>
				<td scope="row">:</td>
				<td><label>
				  <input name="txtSubSubPara" type="text" id="txtSubSubPara" value="$txtSubSubPara" $readOnly/>
				</label></td>
			  </tr>
			</table>
					</fieldset>
			<fieldset>
			<legend><strong>Senarai Jadual Sub Subpara</strong></legend>
			<table width="100%" cellspacing="0" cellpadding="2">
			  <tr class="table_header">
				<td width="3%" scope="row">No</td>
				<td width="17%">Jadual</td>
				<td width="14%">Para</td>
				<td width="17%">Subpara</td>
				<td width="19%">Sub Subpara</td>
			  </tr>
              #set ($jadualSubSubPara = '')
       		    #foreach ($jadualSubSubPara in $JadualSubSubPara)
   			    	#if ($jadualSubSubPara.no == '') 
  			        	#set ($row = 'row1')
  		         	#elseif ($jadualSubSubPara.no % 2 != 0)
  			        	#set ($row = 'row1')
 		         	#else 
  			        	#set ($row = 'row2')
  		       		#end                           
			  <tr>
				<td scope="row">$jadualSubSubPara.no</td>
                #if ($jadualSubSubPara.no != '') 
				<td><a href="javascript:edit_itemJadualSubSubPara('$jadualSubSubPara.idJadualSubSubPara')" class="style1">$jadualSubSubPara.noJadual</a></td>
                #else
                <td>$jadualSubSubPara.noJadual</td>
                #end
				<td>$jadualSubSubPara.para</td>
				<td>$jadualSubSubPara.subPara</td>
				<td>$jadualSubSubPara.subSubPara</td>
			  </tr>
              #end
			</table>
			</fieldset>
				  </div>
				</div>
			</div>
	
		  </td>
		</tr>
		<tr align="center">
		  <td>
	
		   
		   #if ($mode == 'update')
		   <label>
			   <input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanLama()"/>
		   </label>
		 
			#end
			#if($mode == 'view')
		   <label>
			  <input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskini()"/>
		   </label>
		   <label>
			   <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali()"/>
		   </label> 
				  <label>
			   <input type="submit" name="cmdCetak" id="cmdCetak" value="Cetak" />
		   </label>
	
		   #end
           #if ($mode == 'new')
		   <label>
			   <input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanBaru()"/>
		   </label>
			 <label>
			   <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
		   </label>
			#end    	
		  </td>
		</tr>
    </table>
	  <table width="100%" border="0" cellpadding="2">
		<tr>
		<td align="right"><strong>CL-06-07</strong></td>
		</tr>
	  </table>
	
	<script type="text/javascript">
	<!--
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$tabId});
	//-->
	</script>
	
	<script>
	
	// tab Akta
	function check_tarafT(){
		document.${formName}.checked.value = "terbuka";
		document.${formName}.submit();
		document.${formName}.update.value = "click";
	}
	function check_tarafS(){
		document.${formName}.checked.value = "sulit";
		document.${formName}.update.value = "click";
		document.${formName}.submit();
	}
	function setSelected(tabId) {
		document.${formName}.tabId.value = tabId;
		document.${formName}.submit();
	}
	function simpanLama(){
		document.${formName}.action.value = "papar";
		document.${formName}.hitbutton.value = "update";
		document.${formName}.submit();
	}
	function simpanBaru(){
		document.${formName}.action.value = "";
		document.${formName}.hitbutton.value = "simpan";
		document.${formName}.submit();
	}
	function kemaskini(){
		document.${formName}.action.value = "kemaskini";
		document.${formName}.submit();
	}
	function kembali(){
		document.${formName}.action.value = "kembali";
		document.${formName}.submit();
	}
	function batal(){
		document.${formName}.action.value = "batal";
		document.${formName}.submit();
	}
	function edit_itemPenggal(id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idPenggal.value = id;
		document.${formName}.hitbutton.value = "paparPenggal";
		document.${formName}.submit();
	}
	function edit_itemBahagian (id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idBahagian.value = id;
		document.${formName}.hitbutton.value = "paparBahagian";
		document.${formName}.submit();
	}
	function edit_itemBab (id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idBab.value = id;
		document.${formName}.hitbutton.value = "paparBab";
		document.${formName}.submit();	
	}
	function edit_itemSeksyen (id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idSeksyen.value = id;
		document.${formName}.hitbutton.value = "paparSeksyen";
		document.${formName}.submit();	
	}
	function edit_itemSubSeksyen(id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idSubSeksyen.value = id;
		document.${formName}.hitbutton.value = "paparSubSeksyen";
		document.${formName}.submit();
	}
	function edit_itemPara(id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idPara.value = id;
		document.${formName}.hitbutton.value = "paparPara";
		document.${formName}.submit();
	}
	function edit_itemSubPara(id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idSubPara.value = id;
		document.${formName}.hitbutton.value = "paparSubPara";
		document.${formName}.submit();
	}
	function edit_itemJadualPara(id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idJadualPara.value = id;
		document.${formName}.hitbutton.value = "paparJadualPara";
		document.${formName}.submit();
	}
	function edit_itemJadualSubPara(id)
		{
		document.${formName}.action.value = "papar";
		document.${formName}.idJadualSubPara.value = id;
		document.${formName}.hitbutton.value = "paparJadualSubPara";
		document.${formName}.submit();
	}
	function edit_itemJadualSubSubPara(id)
	{
		document.${formName}.action.value = "papar";
		document.${formName}.idJadualSubSubPara.value = id;
		document.${formName}.hitbutton.value = "paparJadualSubSubPara";
		document.${formName}.submit();
	}
	function seterusnya(){    
		document.${formName}.action.value = "papar";
		document.${formName}.hitbutton.value = "next";
		document.${formName}.submit();
	}
	function sebelumnya(){    	
		document.${formName}.action.value = "papar";
		document.${formName}.hitbutton.value = "previous";
		document.${formName}.submit();
	}
	</script>
