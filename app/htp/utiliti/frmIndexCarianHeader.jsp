<!--frmIndexCarianHeader.jsp-->

<fieldset>
<legend><strong>CARIAN</strong></legend>
<table width="100%" border="0">
  <tr>
    <td width="29%"><div align="right">Negeri</div></td>
    <td width="1%">:</td>
    <td width="70%">$!socNegeri</td>
  </tr>

			      	<tr>
				        <td align="right"><div align="right">
				        	#if ($idNegeri == '13' )
				        		Bahagian
				        	#else
				        		Daerah
				        	#end
				        	</div></td>
				        <td><div align="center">:</div></td>
				        <td>$!socDaerah</td>
			      	</tr>
  
  <tr>
    <td width="29%"><div align="right">
      <div align="right">Bandar/Pekan/Mukim</div>
    </div></td>
    <td width="1%">:</td>
    <td width="70%">$!socMukim</td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Kementerian</div></td>
    <td width="1%">:</td>
    <td width="70%">$!socKementerian</td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Agensi</div></td>
    <td width="1%">:</td>
    <td width="70%">$!socAgensi</td>
  </tr>
    <tr>
    <td width="29%"><div align="right">No. Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoFail" type="text" id="txtNoFail" size="43" value="$!txtNoFail"></td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Tajuk Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtTajukFail" type="text" id="txtTajukFail" size="43" value="$!txtTajukCarian"></td>
  </tr>
 
