<head>
<title>eTapp Online Portal</title>
<!-- <link href="../portal/style.css" rel="stylesheet" type="text/css" /> -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Pragma" Content="cache">

<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="../library/scriptaculous/prototype.js" ></script>
<script type="text/javascript" src="../library/scriptaculous/scriptaculous.js" ></script>
<script type="text/javascript" src="../library/scriptaculous/modalbox.js" ></script>
<link rel="stylesheet" type="text/css" href="../library/scriptaculous/modalbox.css" />
<!-- auto complete part-->
<script type="text/javascript" src="../library/scriptaculous/effects.js" ></script>
<script type="text/javascript" src="../library/scriptaculous/controls.js" ></script>
<script type="text/javascript" src="../library/scriptaculous/ajax.js" ></script>
<!-- Jquery -->
<script type="text/javascript" src="library/js/jquery-1.8.0.js">
<!-- <script type="text/javascript" src="library/js/jquery-1.3.2.min.js"></script> -->
<script>var $jquery = jQuery.noConflict();</script>
<script type="text/javascript" src="../library/js/jquery.pstrength-min.1.2.js"></script>
<script type="text/javascript" src="../library/js/jquery.captchaRefresh.js"></script>
<script type="text/javascript" src="../library/js/ekptgTools.js" ></script>
<style type="text/css">
<!--
.style1 {font-size: small}
-->
</style>
</head>

<link rel="shortcut icon" href="../favicon.ico" />
<style>
#validEmail
{
	margin-top: 4px;
	margin-left: 9px;
	position: absolute;
	width: 16px;
	height: 16px;
}

.style3 {background: #A4BA6F; padding: 2px 7px; font-size: small}

.style2 {
	font-size: 8pt;
	font-style: italic; // tidak menggunakan format italic disebabkan nota2 internal telah menggunakan format italic 
}
</style>
<div id="doRegisterResult">
</div> 
<p></p>

<div id="RegistrationForm">
<form name='f1' id='f1'>
<!--
&nbsp;&nbsp;&nbsp;<a href="/online">Utama</a> >> <a href="">Pendaftaran baru</a> 
-->
<p></p>
<input name="action" type="hidden" value="$action" />

<table width="100%" border="0" cellpadding="2">

	<tr>
    	<td colspan="3">
    		<fieldset>
    		<legend class="style3"><strong> Maklumat Pengguna </strong></legend>
    		<table width="100%" border="0" cellpadding="2">
    		<tr>
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1">Kategori Pengguna</span></td>
		        <td><span class="style1">:</span></td>
	    		<td>
	    			<select id="kategori" name="idKategori" style="width:100%" onchange="toggleDisplay('kategori')">
		                <option value="Individu" selected>Individu</option>
		                <option value="Syarikat">Bukan Individu</option>
		                <!-- <option value="Syarikat">Syarikat</option> -->
		            </select> 
		            <!-- <div>
		          		<input type='radio' id='kategori' value=''><span class="style1">Individu</span>
		          		<input type='radio' id='kategori' value=''><span class="style1">Bukan Individu</span>

					</div>-->
	            </td>
            </tr>
            <tr id="katindividu">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"></span></td>
		        <td><span class="style1">:</span></td>
	    		<td>
	    			<!--  <select id="kategori" name="idKategori" style="width:100%" onchange="toggleDisplay('kategori')">
		                <option value="Individu" selected>Individu</option>
		               <option value="Syarikat">Syarikat</option>
		            </select> -->
		            <div>
		          		<input type='radio' id='individuwarga' name="idwarga" value='warga' ><span class="style1">Warganegara</span>
		          		<input type='radio' id='individux' name="idwarga" value='wargax' onClick="toggleDisplay('individux')"><span class="style1">Bukan Warganegara</span>

					</div>
	            </td>
            </tr>
            <tr id="katxindividu" style="display:none;">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"></span></td>
		        <td><span class="style1">:</span></td>
	    		<td>
	    			  <select id="xsyarikatsub" name="idsyarikat" style="width:100%" onchange="toggleDisplay('xsyarikatsub')">
		              	<option value="-1" selected>Sila Pilih</option>
		               	<option value="MAMP">Badan Berkanun</option>
		               	<option value="ROS">Badan Bukan Kerajaan</option>
		                <option value="PBN">Kerajaan Negeri/ Wilayah Persekutuan</option>
		               	<option value="SKM">Koperasi</option>
		               	<option value="SSM">Syarikat</option>
		               	<option value="LAI">Lain-lain (Nyatakan)</option>
		               	<!-- <option value="PBT">Pihak Berkuasa Tempatan</option> -->
		            			
	            </td>
            </tr>
			<tr id="individux_" style="display:none;">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> No. Pasport</span></td>
		        <td><span class="style1">:</span></td>
                <td>
		        	<input autocomplete="off" TABINDEX="1" name="txtNoKPBaru" id="txtNoKPBaru" value="" maxlength="12" type="hidden" />      
                	<input name="txtNoKPBaru1" id="txtNoKPBaru1" style="width: 50px;" type="text" value="" size="20" maxlength="15"/>               
                <!--  <span class="style1">                
                <font color="blue">(cth:76111-03-5512)</font> </span>
		          <div id="checknokp_result2" style="color:red"></div> --></td>
			</tr>
			<tr id="Individu">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> MyID</span></td>
		        <td><span class="style1">:</span></td>
                <td>
		        <input autocomplete="off" TABINDEX="1" name="txtNoKPBaru" id="txtNoKPBaru" value="" maxlength="12" type="hidden" />
                
                <input name="txtNoKPBaru1" id="txtNoKPBaru1" style="width: 50px;" type="text" value="" size="7" maxlength="6" onKeyUp="validateXIC(event,this,this.value,'txtNoKPBaru2')"   />               
                -
  <input name="txtNoKPBaru2" id="txtNoKPBaru2" style="width: 20px;" type="text" value="" size="3" maxlength="2" onKeyUp="validateXIC(event,this,this.value,'txtNoKPBaru3')"   />
                -
  <input name="txtNoKPBaru3" id="txtNoKPBaru3"  style="width: 40px;" type="text" value=""   size="5" maxlength="4"  onKeyUp="validateXIC(event,this,this.value,'txtNoKPBaru3')"  />
                <span class="style1"> 
                
                <font color="blue">(cth:76111-03-5512)</font> </span>
		          <div id="checknokp_result2" style="color:red"></div></td>
			</tr>
            <tr id="Syarikat" style="display:none;">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> MyCOID</span></td>
		        <td><span class="style1">:</span></td>
                <td>
		        <input autocomplete="off" TABINDEX="1" name="txtNoKPCO" id="txtNoKPCO" value="" maxlength="10" type="text" />
                <span class="style1"> 
                <font color="blue">(cth:123456A)</font> </span>
		        </td>
			</tr>
			<tr id="PBN" style="display:none;">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> No. Rujukan</span></td>
		        <td><span class="style1">:</span></td>
                <td>
		        <input autocomplete="off" TABINDEX="1" name="txtNoKPCO" id="txtNoKPCO" value="" maxlength="10" type="text" />
                <span class="style1"> 
                <font color="blue">(cth:123456A)</font> </span>
		        </td>
			</tr>
			<tr id="MAMP" style="display:none;">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> No. Rujukan</span></td>
		        <td><span class="style1">:</span></td>
                <td>
		        <input autocomplete="off" TABINDEX="1" name="txtNoKPCO" id="txtNoKPCO" value="" maxlength="10" type="text" />
                <span class="style1"> 
                <font color="blue">(cth:123456A)</font> </span>
		        </td>
			</tr>
			<tr id="ROS" style="display:none;">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> No. Rujukan</span></td>
		        <td><span class="style1">:</span></td>
                <td>
		        <input autocomplete="off" TABINDEX="1" name="txtNoKPCO" id="txtNoKPCO" value="" maxlength="10" type="text" />
                <span class="style1"> 
                <font color="blue">(cth:123456A)</font> </span>
		        </td>
			</tr>
			    <tr id="SKM" style="display:none;">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1">Bil. Daftar</span></td>
		        <td><span class="style1">:</span></td>
                <td>
		        <input autocomplete="off" TABINDEX="1" name="txtNoKPCO" id="txtNoKPCO" value="" maxlength="10" type="text" />
                <span class="style1"> 
                <font color="blue">(cth:B-6-XXXX)</font> </span>
		        </td>
			</tr>
			<tr id="PBT" style="display:none;">
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> No. Rujukan</span></td>
		        <td><span class="style1">:</span></td>
                <td>
		        <input autocomplete="off" TABINDEX="1" name="txtNoKPCO" id="txtNoKPCO" value="" maxlength="10" type="text" />
                <span class="style1"> 
                <font color="blue">(cth:MDK-43522206-K)</font> </span>
		        </td>
			</tr>
	
			<tr>
		        <td><span class="style1"><i><font color="#ff0000">* </font></i></span><span class="style1">Nama</span></td>
		        <td><span class="style1">:</span></td>
		        <td><input tabindex="2"  class="nama" type="text" name="nama" size="50" maxlength="50" />        </td>
			</tr>  
            
            <tr>
		        <td><span class="style1"><i><font color="#ff0000">* </font></i></span><span class="style1">Alamat</span></td>
		        <td><span class="style1">:</span></td>
		        <td><input tabindex="3"  class="alamat1" type="text" name="alamat1" size="50" maxlength="50" />        </td>
			</tr>
            <tr>
		        <td>&nbsp;</td>
		        <td><span class="style1">:</span></td>
		        <td><input tabindex="4"  class="alamat2" type="text" name="alamat2" size="50" maxlength="50" />        </td>
			</tr>
            
            <tr>
		        <td width="29%">&nbsp;</td>
		        <td width="1%"><span class="style1">:</span></td>
		        <td width="70%"><input tabindex="5"  class="alamat3" type="text" name="alamat3" size="50" maxlength="50" />
		         </td>
			</tr>    
			           
             <tr>
		        <td width="29%"><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1">Bandar</span></td>
		        <td width="1%"><span class="style1">:</span></td>
		        <td width="70%"><input tabindex="5"  class="poskod" type="text" name="bandar" size="10" maxlength="5" />
		         </td>
			</tr> 
			           
             <tr>
		        <td width="29%"><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1">Poskod</span></td>
		        <td width="1%"><span class="style1">:</span></td>
		        <td width="70%"><input tabindex="5"  class="poskod" type="text" name="poskod" size="10" maxlength="5" />
		         </td>
			</tr>    
             <tr id="negeri">
                <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> Negeri</span></td>
                <td><span class="style1">:</span></td>
                <td>             
      	     	<select id="id_negeri" name="id_negeri" style=" font-family:Verdana, Arial, Helvetica, sans-serif"> 
	      		<option value="">Sila Pilih Negeri</option>
	      		<option value="1">JOHOR</option>
	      		<option value="2">KEDAH</option>
	      		<option value="3">KELANTAN</option>
	      		<option value="4">MELAKA</option>
	      		<option value="5">NEGERI SEMBILAN</option>
	      		<option value="6">PAHANG</option>
	      		<option value="7">PULAU PINANG</option>
	      		<option value="8">PERAK</option>
	      		<option value="9">PERLIS</option>
	      		<option value="10">SELANGOR</option>
	      		<option value="11">TERENGGANU</option>
	      		<option value="12">SABAH</option>
	      		<option value="13">SARAWAK</option>
	      		<option value="14">WILAYAH PERSEKUTUAN KUALA LUMPUR</option>
	      		<option value="15">WILAYAH PERSEKUTUAN LABUAN</option>
	      		<option value="16">WILAYAH PERSEKUTUAN PUTRAJAYA</option>
	      		<option value="17">PELBAGAI NEGERI</option>
	      		<option value="0">TIADA MAKLUMAT</option>
	      		</select>             
   				</td>
			</tr>
             <tr id="negara" style="display:none;">
                <td><span class="style1"><i><font color="#ff0000">*</font> </i></span><span class="style1"> Negara</span></td>
                <td><span class="style1">:</span></td>
                <td>             
      	       		<!--  <select id="id_negara" name="id_negara" style=" font-family:Verdana, Arial, Helvetica, sans-serif"> 
      	  			-->
      	  			<input type = 'text' id = 'id_negara' name = 'id_negara' size='30' maxlength='200'  list = 'datalist'  value=""/>
                                    <datalist id = 'datalist'>
                			<option label="Sila Pilih Negara" value=""></option>
                                   
			<option label="AFGHANISTAN" value="AFGHANISTAN"></option>
                                                                                <option label="ALBANIA" value="ALBANIA"></option>
                                                                                <option label="ALGERIA" value="ALGERIA"></option>
                                                                                <option label="AMERICAN SAMOA" value="AMERICAN SAMOA"></option>
                                                                                <option label="ANDORRA" value="ANDORRA"></option>
                                                                                <option label="ANGOLA" value="ANGOLA"></option>
                                                                                <option label="ANGUILLA" value="ANGUILLA"></option>
                                                                                <option label="ANTARCTICA" value="ANTARCTICA"></option>
                                                                                <option label="ANTIGUA AND BARBUDA" value="ANTIGUA AND BARBUDA"></option>
                                                                                <option label="ARCTIC OCEAN" value="ARCTIC OCEAN"></option>
                                                                                <option label="ARGENTINA" value="ARGENTINA"></option>
                                                                                <option label="ARMENIA" value="ARMENIA"></option>
                                                                                <option label="ARUBA" value="ARUBA"></option>
                                                                                <option label="ASHMORE AND CARTIER ISLANDS" value="ASHMORE AND CARTIER ISLANDS"></option>
                                                                                <option label="ATLANTIC OCEAN" value="ATLANTIC OCEAN"></option>
                                                                                <option label="AUSTRALIA" value="AUSTRALIA"></option>
                                                                                <option label="AUSTRIA" value="AUSTRIA"></option>
                                                                                <option label="AZERBAIJAN" value="AZERBAIJAN"></option>
                                                                                <option label="BAHAMAS" value="BAHAMAS"></option>
                                                                                <option label="BAHRAIN" value="BAHRAIN"></option>
                                                                                <option label="BAKER ISLAND" value="BAKER ISLAND"></option>
                                                                                <option label="BANGLADESH" value="BANGLADESH"></option>
                                                                                <option label="BARBADOS" value="BARBADOS"></option>
                                                                                <option label="BASSAS DA INDIA" value="BASSAS DA INDIA"></option>
                                                                                <option label="BELARUS" value="BELARUS"></option>
                                                                                <option label="BELGIUM" value="BELGIUM"></option>
                                                                                <option label="BELIZE" value="BELIZE"></option>
                                                                                <option label="BENIN" value="BENIN"></option>
                                                                                <option label="BERMUDA" value="BERMUDA"></option>
                                                                                <option label="BHUTAN" value="BHUTAN"></option>
                                                                                <option label="BOLIVIA" value="BOLIVIA"></option>
                                                                                <option label="BOSNIA AND HERZEGOVINA" value="BOSNIA AND HERZEGOVINA"></option>
                                                                                <option label="BOTSWANA" value="BOTSWANA"></option>
                                                                                <option label="BOUVET ISLAND" value="BOUVET ISLAND"></option>
                                                                                <option label="BRAZIL" value="BRAZIL"></option>
                                                                                <option label="BRITISH VIRGIN ISLANDS" value="BRITISH VIRGIN ISLANDS"></option>
                                                                                <option label="BRUNEI" value="BRUNEI"></option>
                                                                                <option label="BULGARIA" value="BULGARIA"></option>
                                                                                <option label="BURKINA FASO" value="BURKINA FASO"></option>
                                                                                <option label="BURUNDI" value="BURUNDI"></option>
                                                                                <option label="CAMBODIA" value="CAMBODIA"></option>
                                                                                <option label="CAMEROON" value="CAMEROON"></option>
                                                                                <option label="CANADA" value="CANADA"></option>
                                                                                <option label="CAPE VERDE" value="CAPE VERDE"></option>
                                                                                <option label="CAYMAN ISLANDS" value="CAYMAN ISLANDS"></option>
                                                                                <option label="CENTRAL AFRICAN REPUBLIC" value="CENTRAL AFRICAN REPUBLIC"></option>
                                                                                <option label="CHAD" value="CHAD"></option>
                                                                                <option label="CHILE" value="CHILE"></option>
                                                                                <option label="CHINA" value="CHINA"></option>
                                                                                <option label="CHRISTMAS ISLAND" value="CHRISTMAS ISLAND"></option>
                                                                                <option label="CLIPPERTON ISLAND" value="CLIPPERTON ISLAND"></option>
                                                                                <option label="COCOS ISLANDS" value="COCOS ISLANDS"></option>
                                                                                <option label="COLOMBIA" value="COLOMBIA"></option>
                                                                                <option label="COMOROS" value="COMOROS"></option>
                                                                                <option label="COOK ISLANDS" value="COOK ISLANDS"></option>
                                                                                <option label="CORAL SEA ISLANDS" value="CORAL SEA ISLANDS"></option>
                                                                                <option label="COSTA RICA" value="COSTA RICA"></option>
                                                                                <option label="COTE D" ivoire'="" value="COTE D"></option>
                                                                                <option label="CROATIA" value="CROATIA"></option>
                                                                                <option label="CUBA" value="CUBA"></option>
                                                                                <option label="CYPRUS" value="CYPRUS"></option>
                                                                                <option label="CZECH REPUBLIC" value="CZECH REPUBLIC"></option>
                                                                                <option label="DEMOCRATIC REPUBLIC OF THE CONGO" value="DEMOCRATIC REPUBLIC OF THE CONGO"></option>
                                                                                <option label="DENMARK" value="DENMARK"></option>
                                                                                <option label="DJIBOUTI" value="DJIBOUTI"></option>
                                                                                <option label="DOMINICA" value="DOMINICA"></option>
                                                                                <option label="DOMINICAN REPUBLIC" value="DOMINICAN REPUBLIC"></option>
                                                                                <option label="EAST TIMOR" value="EAST TIMOR"></option>
                                                                                <option label="ECUADOR" value="ECUADOR"></option>
                                                                                <option label="EGYPT" value="EGYPT"></option>
                                                                                <option label="EL SALVADOR" value="EL SALVADOR"></option>
                                                                                <option label="EQUATORIAL GUINEA" value="EQUATORIAL GUINEA"></option>
                                                                                <option label="ERITREA" value="ERITREA"></option>
                                                                                <option label="ESTONIA" value="ESTONIA"></option>
                                                                                <option label="ETHIOPIA" value="ETHIOPIA"></option>
                                                                                <option label="EUROPA ISLAND" value="EUROPA ISLAND"></option>
                                                                                <option label="FALKLAND ISLANDS (ISLAS MALVINAS)" value="FALKLAND ISLANDS (ISLAS MALVINAS)"></option>
                                                                                <option label="FAROE ISLANDS" value="FAROE ISLANDS"></option>
                                                                                <option label="FIJI" value="FIJI"></option>
                                                                                <option label="FINLAND" value="FINLAND"></option>
                                                                                <option label="FRANCE" value="FRANCE"></option>
                                                                                <option label="FRENCH GUIANA" value="FRENCH GUIANA"></option>
                                                                                <option label="FRENCH POLYNESIA" value="FRENCH POLYNESIA"></option>
                                                                                <option label="FRENCH SOUTHERN AND ANTARCTIC LANDS" value="FRENCH SOUTHERN AND ANTARCTIC LANDS"></option>
                                                                                <option label="GABON" value="GABON"></option>
                                                                                <option label="GAMBIA" value="GAMBIA"></option>
                                                                                <option label="GAZA STRIP" value="GAZA STRIP"></option>
                                                                                <option label="GEORGIA" value="GEORGIA"></option>
                                                                                <option label="GERMANY" value="GERMANY"></option>
                                                                                <option label="GHANA" value="GHANA"></option>
                                                                                <option label="GIBRALTAR" value="GIBRALTAR"></option>
                                                                                <option label="GLORIOSO ISLANDS" value="GLORIOSO ISLANDS"></option>
                                                                                <option label="GREECE" value="GREECE"></option>
                                                                                <option label="GREENLAND" value="GREENLAND"></option>
                                                                                <option label="GRENADA" value="GRENADA"></option>
                                                                                <option label="GUADELOUPE" value="GUADELOUPE"></option>
                                                                                <option label="GUAM" value="GUAM"></option>
                                                                                <option label="GUATEMALA" value="GUATEMALA"></option>
                                                                                <option label="GUERNSEY" value="GUERNSEY"></option>
                                                                                <option label="GUINEA" value="GUINEA"></option>
                                                                                <option label="GUINEA-BISSAU" value="GUINEA-BISSAU"></option>
                                                                                <option label="GUYANA" value="GUYANA"></option>
                                                                                <option label="HAITI" value="HAITI"></option>
                                                                                <option label="HEARD ISLAND AND MCDONALD ISLANDS" value="HEARD ISLAND AND MCDONALD ISLANDS"></option>
                                                                                <option label="HONDURAS" value="HONDURAS"></option>
                                                                                <option label="HONG KONG" value="HONG KONG"></option>
                                                                                <option label="HOWLAND ISLAND" value="HOWLAND ISLAND"></option>
                                                                                <option label="HUNGARY" value="HUNGARY"></option>
                                                                                <option label="ICELAND" value="ICELAND"></option>
                                                                                <option label="INDIA" value="INDIA"></option>
                                                                                <option label="INDIAN OCEAN" value="INDIAN OCEAN"></option>
                                                                                <option label="INDONESIA" value="INDONESIA"></option>
                                                                                <option label="IRAN" value="IRAN"></option>
                                                                                <option label="IRAQ" value="IRAQ"></option>
                                                                                <option label="IRELAND" value="IRELAND"></option>
                                                                                <option label="ISLE OF MAN" value="ISLE OF MAN"></option>
                                                                                <option label="ISRAEL" value="ISRAEL"></option>
                                                                                <option label="ITALY" value="ITALY"></option>
                                                                                <option label="JAMAICA" value="JAMAICA"></option>
                                                                                <option label="JAN MAYEN" value="JAN MAYEN"></option>
                                                                                <option label="JAPAN" value="JAPAN"></option>
                                                                                <option label="JARVIS ISLAND" value="JARVIS ISLAND"></option>
                                                                                <option label="JERSEY" value="JERSEY"></option>
                                                                                <option label="JOHNSTON ATOLL" value="JOHNSTON ATOLL"></option>
                                                                                <option label="JORDAN" value="JORDAN"></option>
                                                                                <option label="JUAN DE NOVA ISLAND" value="JUAN DE NOVA ISLAND"></option>
                                                                                <option label="KAZAKHSTAN" value="KAZAKHSTAN"></option>
                                                                                <option label="KENYA" value="KENYA"></option>
                                                                                <option label="KERGUELEN ARCHIPELAGO" value="KERGUELEN ARCHIPELAGO"></option>
                                                                                <option label="KINGMAN REEF" value="KINGMAN REEF"></option>
                                                                                <option label="KIRIBATI" value="KIRIBATI"></option>
                                                                                <option label="KUWAIT" value="KUWAIT"></option>
                                                                                <option label="KYRGYZSTAN" value="KYRGYZSTAN"></option>
                                                                                <option label="LAOS" value="LAOS"></option>
                                                                                <option label="LATVIA" value="LATVIA"></option>
                                                                                <option label="LEBANON" value="LEBANON"></option>
                                                                                <option label="LESOTHO" value="LESOTHO"></option>
                                                                                <option label="LIBERIA" value="LIBERIA"></option>
                                                                                <option label="LIBYA" value="LIBYA"></option>
                                                                                <option label="LIECHTENSTEIN" value="LIECHTENSTEIN"></option>
                                                                                <option label="LITHUANIA" value="LITHUANIA"></option>
                                                                                <option label="LUXEMBOURG" value="LUXEMBOURG"></option>
                                                                                <option label="MACAU" value="MACAU"></option>
                                                                                <option label="MACEDONIA" value="MACEDONIA"></option>
                                                                                <option label="MADAGASCAR" value="MADAGASCAR"></option>
                                                                                <option label="MALAWI" value="MALAWI"></option>
                                                                                <option label="MALAYSIA" value="MALAYSIA"></option>
                                                                                <option label="MALDIVES" value="MALDIVES"></option>
                                                                                <option label="MALI" value="MALI"></option>
                                                                                <option label="MALTA" value="MALTA"></option>
                                                                                <option label="MARSHALL ISLANDS" value="MARSHALL ISLANDS"></option>
                                                                                <option label="MARTINIQUE" value="MARTINIQUE"></option>
                                                                                <option label="MAURITANIA" value="MAURITANIA"></option>
                                                                                <option label="MAURITIUS" value="MAURITIUS"></option>
                                                                                <option label="MAYOTTE" value="MAYOTTE"></option>
                                                                                <option label="MEXICO" value="MEXICO"></option>
                                                                                <option label="MICRONESIA" value="MICRONESIA"></option>
                                                                                <option label="MIDWAY ISLANDS" value="MIDWAY ISLANDS"></option>
                                                                                <option label="MOLDOVA" value="MOLDOVA"></option>
                                                                                <option label="MONACO" value="MONACO"></option>
                                                                                <option label="MONGOLIA" value="MONGOLIA"></option>
                                                                                <option label="MONTENEGRO" value="MONTENEGRO"></option>
                                                                                <option label="MONTSERRAT" value="MONTSERRAT"></option>
                                                                                <option label="MOROCCO" value="MOROCCO"></option>
                                                                                <option label="MOZAMBIQUE" value="MOZAMBIQUE"></option>
                                                                                <option label="MYANMAR" value="MYANMAR"></option>
                                                                                <option label="NAMIBIA" value="NAMIBIA"></option>
                                                                                <option label="NAURU" value="NAURU"></option>
                                                                                <option label="NAVASSA ISLAND" value="NAVASSA ISLAND"></option>
                                                                                <option label="NEPAL" value="NEPAL"></option>
                                                                                <option label="NETHERLANDS" value="NETHERLANDS"></option>
                                                                                <option label="NETHERLANDS ANTILLES" value="NETHERLANDS ANTILLES"></option>
                                                                                <option label="NEW CALEDONIA" value="NEW CALEDONIA"></option>
                                                                                <option label="NEW ZEALAND" value="NEW ZEALAND"></option>
                                                                                <option label="NICARAGUA" value="NICARAGUA"></option>
                                                                                <option label="NIGER" value="NIGER"></option>
                                                                                <option label="NIGERIA" value="NIGERIA"></option>
                                                                                <option label="NIUE" value="NIUE"></option>
                                                                                <option label="NORFOLK ISLAND" value="NORFOLK ISLAND"></option>
                                                                                <option label="NORTH KOREA" value="NORTH KOREA"></option>
                                                                                <option label="NORTHERN MARIANA ISLANDS" value="NORTHERN MARIANA ISLANDS"></option>
                                                                                <option label="NORWAY" value="NORWAY"></option>
                                                                                <option label="OMAN" value="OMAN"></option>
                                                                                <option label="PACIFIC OCEAN" value="PACIFIC OCEAN"></option>
                                                                                <option label="PAKISTAN" value="PAKISTAN"></option>
                                                                                <option label="PALAU" value="PALAU"></option>
                                                                                <option label="PALMYRA ATOLL" value="PALMYRA ATOLL"></option>
                                                                                <option label="PANAMA" value="PANAMA"></option>
                                                                                <option label="PAPUA NEW GUINEA" value="PAPUA NEW GUINEA"></option>
                                                                                <option label="PARACEL ISLANDS" value="PARACEL ISLANDS"></option>
                                                                                <option label="PARAGUAY" value="PARAGUAY"></option>
                                                                                <option label="PERU" value="PERU"></option>
                                                                                <option label="PHILIPPINES" value="PHILIPPINES"></option>
                                                                                <option label="PITCAIRN ISLANDS" value="PITCAIRN ISLANDS"></option>
                                                                                <option label="POLAND" value="POLAND"></option>
                                                                                <option label="PORTUGAL" value="PORTUGAL"></option>
                                                                                <option label="PUERTO RICO" value="PUERTO RICO"></option>
                                                                                <option label="QATAR" value="QATAR"></option>
                                                                                <option label="REPUBLIC OF THE CONGO" value="REPUBLIC OF THE CONGO"></option>
                                                                                <option label="REUNION" value="REUNION"></option>
                                                                                <option label="ROMANIA" value="ROMANIA"></option>
                                                                                <option label="RUSSIA" value="RUSSIA"></option>
                                                                                <option label="RWANDA" value="RWANDA"></option>
                                                                                <option label="SAINT HELENA" value="SAINT HELENA"></option>
                                                                                <option label="SAINT KITTS AND NEVIS" value="SAINT KITTS AND NEVIS"></option>
                                                                                <option label="SAINT LUCIA" value="SAINT LUCIA"></option>
                                                                                <option label="SAINT PIERRE AND MIQUELON" value="SAINT PIERRE AND MIQUELON"></option>
                                                                                <option label="SAINT VINCENT AND THE GRENADINES" value="SAINT VINCENT AND THE GRENADINES"></option>
                                                                                <option label="SAMOA" value="SAMOA"></option>
                                                                                <option label="SAN MARINO" value="SAN MARINO"></option>
                                                                                <option label="SAO TOME AND PRINCIPE" value="SAO TOME AND PRINCIPE"></option>
                                                                                <option label="SAUDI ARABIA" value="SAUDI ARABIA"></option>
                                                                                <option label="SENEGAL" value="SENEGAL"></option>
                                                                                <option label="SERBIA" value="SERBIA"></option>
                                                                                <option label="SEYCHELLES" value="SEYCHELLES"></option>
                                                                                <option label="SIERRA LEONE" value="SIERRA LEONE"></option>
                                                                                <option label="SINGAPORE" value="SINGAPORE"></option>
                                                                                <option label="SLOVAKIA" value="SLOVAKIA"></option>
                                                                                <option label="SLOVENIA" value="SLOVENIA"></option>
                                                                                <option label="SOLOMON ISLANDS" value="SOLOMON ISLANDS"></option>
                                                                                <option label="SOMALIA" value="SOMALIA"></option>
                                                                                <option label="SOUTH AFRICA" value="SOUTH AFRICA"></option>
                                                                                <option label="SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS" value="SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS"></option>
                                                                                <option label="SOUTH KOREA" value="SOUTH KOREA"></option>
                                                                                <option label="SPAIN" value="SPAIN"></option>
                                                                                <option label="SPRATLY ISLANDS" value="SPRATLY ISLANDS"></option>
                                                                                <option label="SRI LANKA" value="SRI LANKA"></option>
                                                                                <option label="SUDAN" value="SUDAN"></option>
                                                                                <option label="SURINAME" value="SURINAME"></option>
                                                                                <option label="SVALBARD" value="SVALBARD"></option>
                                                                                <option label="SWAZILAND" value="SWAZILAND"></option>
                                                                                <option label="SWEDEN" value="SWEDEN"></option>
                                                                                <option label="SWITZERLAND" value="SWITZERLAND"></option>
                                                                                <option label="SYRIA" value="SYRIA"></option>
                                                                                <option label="TAIWAN" value="TAIWAN"></option>
                                                                                <option label="TAJIKISTAN" value="TAJIKISTAN"></option>
                                                                                <option label="TANZANIA" value="TANZANIA"></option>
                                                                                <option label="THAILAND" value="THAILAND"></option>
                                                                                <option label="TIMOR LESTE" value="TIMOR LESTE"></option>
                                                                                <option label="TOGO" value="TOGO"></option>
                                                                                <option label="TOKELAU" value="TOKELAU"></option>
                                                                                <option label="TONGA" value="TONGA"></option>
                                                                                <option label="TRINIDAD AND TOBAGO" value="TRINIDAD AND TOBAGO"></option>
                                                                                <option label="TROMELIN ISLAND" value="TROMELIN ISLAND"></option>
                                                                                <option label="TUNISIA" value="TUNISIA"></option>
                                                                                <option label="TURKEY" value="TURKEY"></option>
                                                                                <option label="TURKMENISTAN" value="TURKMENISTAN"></option>
                                                                                <option label="TURKS AND CAICOS ISLANDS" value="TURKS AND CAICOS ISLANDS"></option>
                                                                                <option label="TUVALU" value="TUVALU"></option>
                                                                                <option label="USA" value="USA"></option>
                                                                                <option label="UGANDA" value="UGANDA"></option>
                                                                                <option label="UKRAINE" value="UKRAINE"></option>
                                                                                <option label="UNITED ARAB EMIRATES" value="UNITED ARAB EMIRATES"></option>
                                                                                <option label="UNITED KINGDOM" value="UNITED KINGDOM"></option>
                                                                                <option label="URUGUAY" value="URUGUAY"></option>
                                                                                <option label="UZBEKISTAN" value="UZBEKISTAN"></option>
                                                                                <option label="VANUATU" value="VANUATU"></option>
                                                                                <option label="VENEZUELA" value="VENEZUELA"></option>
                                                                                <option label="VIET NAM" value="VIET NAM"></option>
                                                                                <option label="VIRGIN ISLANDS" value="VIRGIN ISLANDS"></option>
                                                                                <option label="WAKE ISLAND" value="WAKE ISLAND"></option>
                                                                                <option label="WALLIS AND FUTUNA" value="WALLIS AND FUTUNA"></option>
                                                                                <option label="WEST BANK" value="WEST BANK"></option>
                                                                                <option label="WESTERN SAHARA" value="WESTERN SAHARA"></option>
                                                                                <option label="YEMEN" value="YEMEN"></option>
                                                                                <option label="YUGOSLAVIA" value="YUGOSLAVIA"></option>
                                                                                <option label="ZAMBIA" value="ZAMBIA"></option>
                                                                                <option label="ZIMBABWE" value="ZIMBABWE"></option><option label="AFGHANISTAN" value="AFGHANISTAN"></option>
                                                                                <option label="ALBANIA" value="ALBANIA"></option>
                                                                                <option label="ALGERIA" value="ALGERIA"></option>
                                                                                <option label="AMERICAN SAMOA" value="AMERICAN SAMOA"></option>
                                                                                <option label="ANDORRA" value="ANDORRA"></option>
                                                                                <option label="ANGOLA" value="ANGOLA"></option>
                                                                                <option label="ANGUILLA" value="ANGUILLA"></option>
                                                                                <option label="ANTARCTICA" value="ANTARCTICA"></option>
                                                                                <option label="ANTIGUA AND BARBUDA" value="ANTIGUA AND BARBUDA"></option>
                                                                                <option label="ARCTIC OCEAN" value="ARCTIC OCEAN"></option>
                                                                                <option label="ARGENTINA" value="ARGENTINA"></option>
                                                                                <option label="ARMENIA" value="ARMENIA"></option>
                                                                                <option label="ARUBA" value="ARUBA"></option>
                                                                                <option label="ASHMORE AND CARTIER ISLANDS" value="ASHMORE AND CARTIER ISLANDS"></option>
                                                                                <option label="ATLANTIC OCEAN" value="ATLANTIC OCEAN"></option>
                                                                                <option label="AUSTRALIA" value="AUSTRALIA"></option>
                                                                                <option label="AUSTRIA" value="AUSTRIA"></option>
                                                                                <option label="AZERBAIJAN" value="AZERBAIJAN"></option>
                                                                                <option label="BAHAMAS" value="BAHAMAS"></option>
                                                                                <option label="BAHRAIN" value="BAHRAIN"></option>
                                                                                <option label="BAKER ISLAND" value="BAKER ISLAND"></option>
                                                                                <option label="BANGLADESH" value="BANGLADESH"></option>
                                                                                <option label="BARBADOS" value="BARBADOS"></option>
                                                                                <option label="BASSAS DA INDIA" value="BASSAS DA INDIA"></option>
                                                                                <option label="BELARUS" value="BELARUS"></option>
                                                                                <option label="BELGIUM" value="BELGIUM"></option>
                                                                                <option label="BELIZE" value="BELIZE"></option>
                                                                                <option label="BENIN" value="BENIN"></option>
                                                                                <option label="BERMUDA" value="BERMUDA"></option>
                                                                                <option label="BHUTAN" value="BHUTAN"></option>
                                                                                <option label="BOLIVIA" value="BOLIVIA"></option>
                                                                                <option label="BOSNIA AND HERZEGOVINA" value="BOSNIA AND HERZEGOVINA"></option>
                                                                                <option label="BOTSWANA" value="BOTSWANA"></option>
                                                                                <option label="BOUVET ISLAND" value="BOUVET ISLAND"></option>
                                                                                <option label="BRAZIL" value="BRAZIL"></option>
                                                                                <option label="BRITISH VIRGIN ISLANDS" value="BRITISH VIRGIN ISLANDS"></option>
                                                                                <option label="BRUNEI" value="BRUNEI"></option>
                                                                                <option label="BULGARIA" value="BULGARIA"></option>
                                                                                <option label="BURKINA FASO" value="BURKINA FASO"></option>
                                                                                <option label="BURUNDI" value="BURUNDI"></option>
                                                                                <option label="CAMBODIA" value="CAMBODIA"></option>
                                                                                <option label="CAMEROON" value="CAMEROON"></option>
                                                                                <option label="CANADA" value="CANADA"></option>
                                                                                <option label="CAPE VERDE" value="CAPE VERDE"></option>
                                                                                <option label="CAYMAN ISLANDS" value="CAYMAN ISLANDS"></option>
                                                                                <option label="CENTRAL AFRICAN REPUBLIC" value="CENTRAL AFRICAN REPUBLIC"></option>
                                                                                <option label="CHAD" value="CHAD"></option>
                                                                                <option label="CHILE" value="CHILE"></option>
                                                                                <option label="CHINA" value="CHINA"></option>
                                                                                <option label="CHRISTMAS ISLAND" value="CHRISTMAS ISLAND"></option>
                                                                                <option label="CLIPPERTON ISLAND" value="CLIPPERTON ISLAND"></option>
                                                                                <option label="COCOS ISLANDS" value="COCOS ISLANDS"></option>
                                                                                <option label="COLOMBIA" value="COLOMBIA"></option>
                                                                                <option label="COMOROS" value="COMOROS"></option>
                                                                                <option label="COOK ISLANDS" value="COOK ISLANDS"></option>
                                                                                <option label="CORAL SEA ISLANDS" value="CORAL SEA ISLANDS"></option>
                                                                                <option label="COSTA RICA" value="COSTA RICA"></option>
                                                                                <option label="COTE D" ivoire'="" value="COTE D"></option>
                                                                                <option label="CROATIA" value="CROATIA"></option>
                                                                                <option label="CUBA" value="CUBA"></option>
                                                                                <option label="CYPRUS" value="CYPRUS"></option>
                                                                                <option label="CZECH REPUBLIC" value="CZECH REPUBLIC"></option>
                                                                                <option label="DEMOCRATIC REPUBLIC OF THE CONGO" value="DEMOCRATIC REPUBLIC OF THE CONGO"></option>
                                                                                <option label="DENMARK" value="DENMARK"></option>
                                                                                <option label="DJIBOUTI" value="DJIBOUTI"></option>
                                                                                <option label="DOMINICA" value="DOMINICA"></option>
                                                                                <option label="DOMINICAN REPUBLIC" value="DOMINICAN REPUBLIC"></option>
                                                                                <option label="EAST TIMOR" value="EAST TIMOR"></option>
                                                                                <option label="ECUADOR" value="ECUADOR"></option>
                                                                                <option label="EGYPT" value="EGYPT"></option>
                                                                                <option label="EL SALVADOR" value="EL SALVADOR"></option>
                                                                                <option label="EQUATORIAL GUINEA" value="EQUATORIAL GUINEA"></option>
                                                                                <option label="ERITREA" value="ERITREA"></option>
                                                                                <option label="ESTONIA" value="ESTONIA"></option>
                                                                                <option label="ETHIOPIA" value="ETHIOPIA"></option>
                                                                                <option label="EUROPA ISLAND" value="EUROPA ISLAND"></option>
                                                                                <option label="FALKLAND ISLANDS (ISLAS MALVINAS)" value="FALKLAND ISLANDS (ISLAS MALVINAS)"></option>
                                                                                <option label="FAROE ISLANDS" value="FAROE ISLANDS"></option>
                                                                                <option label="FIJI" value="FIJI"></option>
                                                                                <option label="FINLAND" value="FINLAND"></option>
                                                                                <option label="FRANCE" value="FRANCE"></option>
                                                                                <option label="FRENCH GUIANA" value="FRENCH GUIANA"></option>
                                                                                <option label="FRENCH POLYNESIA" value="FRENCH POLYNESIA"></option>
                                                                                <option label="FRENCH SOUTHERN AND ANTARCTIC LANDS" value="FRENCH SOUTHERN AND ANTARCTIC LANDS"></option>
                                                                                <option label="GABON" value="GABON"></option>
                                                                                <option label="GAMBIA" value="GAMBIA"></option>
                                                                                <option label="GAZA STRIP" value="GAZA STRIP"></option>
                                                                                <option label="GEORGIA" value="GEORGIA"></option>
                                                                                <option label="GERMANY" value="GERMANY"></option>
                                                                                <option label="GHANA" value="GHANA"></option>
                                                                                <option label="GIBRALTAR" value="GIBRALTAR"></option>
                                                                                <option label="GLORIOSO ISLANDS" value="GLORIOSO ISLANDS"></option>
                                                                                <option label="GREECE" value="GREECE"></option>
                                                                                <option label="GREENLAND" value="GREENLAND"></option>
                                                                                <option label="GRENADA" value="GRENADA"></option>
                                                                                <option label="GUADELOUPE" value="GUADELOUPE"></option>
                                                                                <option label="GUAM" value="GUAM"></option>
                                                                                <option label="GUATEMALA" value="GUATEMALA"></option>
                                                                                <option label="GUERNSEY" value="GUERNSEY"></option>
                                                                                <option label="GUINEA" value="GUINEA"></option>
                                                                                <option label="GUINEA-BISSAU" value="GUINEA-BISSAU"></option>
                                                                                <option label="GUYANA" value="GUYANA"></option>
                                                                                <option label="HAITI" value="HAITI"></option>
                                                                                <option label="HEARD ISLAND AND MCDONALD ISLANDS" value="HEARD ISLAND AND MCDONALD ISLANDS"></option>
                                                                                <option label="HONDURAS" value="HONDURAS"></option>
                                                                                <option label="HONG KONG" value="HONG KONG"></option>
                                                                                <option label="HOWLAND ISLAND" value="HOWLAND ISLAND"></option>
                                                                                <option label="HUNGARY" value="HUNGARY"></option>
                                                                                <option label="ICELAND" value="ICELAND"></option>
                                                                                <option label="INDIA" value="INDIA"></option>
                                                                                <option label="INDIAN OCEAN" value="INDIAN OCEAN"></option>
                                                                                <option label="INDONESIA" value="INDONESIA"></option>
                                                                                <option label="IRAN" value="IRAN"></option>
                                                                                <option label="IRAQ" value="IRAQ"></option>
                                                                                <option label="IRELAND" value="IRELAND"></option>
                                                                                <option label="ISLE OF MAN" value="ISLE OF MAN"></option>
                                                                                <option label="ISRAEL" value="ISRAEL"></option>
                                                                                <option label="ITALY" value="ITALY"></option>
                                                                                <option label="JAMAICA" value="JAMAICA"></option>
                                                                                <option label="JAN MAYEN" value="JAN MAYEN"></option>
                                                                                <option label="JAPAN" value="JAPAN"></option>
                                                                                <option label="JARVIS ISLAND" value="JARVIS ISLAND"></option>
                                                                                <option label="JERSEY" value="JERSEY"></option>
                                                                                <option label="JOHNSTON ATOLL" value="JOHNSTON ATOLL"></option>
                                                                                <option label="JORDAN" value="JORDAN"></option>
                                                                                <option label="JUAN DE NOVA ISLAND" value="JUAN DE NOVA ISLAND"></option>
                                                                                <option label="KAZAKHSTAN" value="KAZAKHSTAN"></option>
                                                                                <option label="KENYA" value="KENYA"></option>
                                                                                <option label="KERGUELEN ARCHIPELAGO" value="KERGUELEN ARCHIPELAGO"></option>
                                                                                <option label="KINGMAN REEF" value="KINGMAN REEF"></option>
                                                                                <option label="KIRIBATI" value="KIRIBATI"></option>
                                                                                <option label="KUWAIT" value="KUWAIT"></option>
                                                                                <option label="KYRGYZSTAN" value="KYRGYZSTAN"></option>
                                                                                <option label="LAOS" value="LAOS"></option>
                                                                                <option label="LATVIA" value="LATVIA"></option>
                                                                                <option label="LEBANON" value="LEBANON"></option>
                                                                                <option label="LESOTHO" value="LESOTHO"></option>
                                                                                <option label="LIBERIA" value="LIBERIA"></option>
                                                                                <option label="LIBYA" value="LIBYA"></option>
                                                                                <option label="LIECHTENSTEIN" value="LIECHTENSTEIN"></option>
                                                                                <option label="LITHUANIA" value="LITHUANIA"></option>
                                                                                <option label="LUXEMBOURG" value="LUXEMBOURG"></option>
                                                                                <option label="MACAU" value="MACAU"></option>
                                                                                <option label="MACEDONIA" value="MACEDONIA"></option>
                                                                                <option label="MADAGASCAR" value="MADAGASCAR"></option>
                                                                                <option label="MALAWI" value="MALAWI"></option>
                                                                                <option label="MALAYSIA" value="MALAYSIA"></option>
                                                                                <option label="MALDIVES" value="MALDIVES"></option>
                                                                                <option label="MALI" value="MALI"></option>
                                                                                <option label="MALTA" value="MALTA"></option>
                                                                                <option label="MARSHALL ISLANDS" value="MARSHALL ISLANDS"></option>
                                                                                <option label="MARTINIQUE" value="MARTINIQUE"></option>
                                                                                <option label="MAURITANIA" value="MAURITANIA"></option>
                                                                                <option label="MAURITIUS" value="MAURITIUS"></option>
                                                                                <option label="MAYOTTE" value="MAYOTTE"></option>
                                                                                <option label="MEXICO" value="MEXICO"></option>
                                                                                <option label="MICRONESIA" value="MICRONESIA"></option>
                                                                                <option label="MIDWAY ISLANDS" value="MIDWAY ISLANDS"></option>
                                                                                <option label="MOLDOVA" value="MOLDOVA"></option>
                                                                                <option label="MONACO" value="MONACO"></option>
                                                                                <option label="MONGOLIA" value="MONGOLIA"></option>
                                                                                <option label="MONTENEGRO" value="MONTENEGRO"></option>
                                                                                <option label="MONTSERRAT" value="MONTSERRAT"></option>
                                                                                <option label="MOROCCO" value="MOROCCO"></option>
                                                                                <option label="MOZAMBIQUE" value="MOZAMBIQUE"></option>
                                                                                <option label="MYANMAR" value="MYANMAR"></option>
                                                                                <option label="NAMIBIA" value="NAMIBIA"></option>
                                                                                <option label="NAURU" value="NAURU"></option>
                                                                                <option label="NAVASSA ISLAND" value="NAVASSA ISLAND"></option>
                                                                                <option label="NEPAL" value="NEPAL"></option>
                                                                                <option label="NETHERLANDS" value="NETHERLANDS"></option>
                                                                                <option label="NETHERLANDS ANTILLES" value="NETHERLANDS ANTILLES"></option>
                                                                                <option label="NEW CALEDONIA" value="NEW CALEDONIA"></option>
                                                                                <option label="NEW ZEALAND" value="NEW ZEALAND"></option>
                                                                                <option label="NICARAGUA" value="NICARAGUA"></option>
                                                                                <option label="NIGER" value="NIGER"></option>
                                                                                <option label="NIGERIA" value="NIGERIA"></option>
                                                                                <option label="NIUE" value="NIUE"></option>
                                                                                <option label="NORFOLK ISLAND" value="NORFOLK ISLAND"></option>
                                                                                <option label="NORTH KOREA" value="NORTH KOREA"></option>
                                                                                <option label="NORTHERN MARIANA ISLANDS" value="NORTHERN MARIANA ISLANDS"></option>
                                                                                <option label="NORWAY" value="NORWAY"></option>
                                                                                <option label="OMAN" value="OMAN"></option>
                                                                                <option label="PACIFIC OCEAN" value="PACIFIC OCEAN"></option>
                                                                                <option label="PAKISTAN" value="PAKISTAN"></option>
                                                                                <option label="PALAU" value="PALAU"></option>
                                                                                <option label="PALMYRA ATOLL" value="PALMYRA ATOLL"></option>
                                                                                <option label="PANAMA" value="PANAMA"></option>
                                                                                <option label="PAPUA NEW GUINEA" value="PAPUA NEW GUINEA"></option>
                                                                                <option label="PARACEL ISLANDS" value="PARACEL ISLANDS"></option>
                                                                                <option label="PARAGUAY" value="PARAGUAY"></option>
                                                                                <option label="PERU" value="PERU"></option>
                                                                                <option label="PHILIPPINES" value="PHILIPPINES"></option>
                                                                                <option label="PITCAIRN ISLANDS" value="PITCAIRN ISLANDS"></option>
                                                                                <option label="POLAND" value="POLAND"></option>
                                                                                <option label="PORTUGAL" value="PORTUGAL"></option>
                                                                                <option label="PUERTO RICO" value="PUERTO RICO"></option>
                                                                                <option label="QATAR" value="QATAR"></option>
                                                                                <option label="REPUBLIC OF THE CONGO" value="REPUBLIC OF THE CONGO"></option>
                                                                                <option label="REUNION" value="REUNION"></option>
                                                                                <option label="ROMANIA" value="ROMANIA"></option>
                                                                                <option label="RUSSIA" value="RUSSIA"></option>
                                                                                <option label="RWANDA" value="RWANDA"></option>
                                                                                <option label="SAINT HELENA" value="SAINT HELENA"></option>
                                                                                <option label="SAINT KITTS AND NEVIS" value="SAINT KITTS AND NEVIS"></option>
                                                                                <option label="SAINT LUCIA" value="SAINT LUCIA"></option>
                                                                                <option label="SAINT PIERRE AND MIQUELON" value="SAINT PIERRE AND MIQUELON"></option>
                                                                                <option label="SAINT VINCENT AND THE GRENADINES" value="SAINT VINCENT AND THE GRENADINES"></option>
                                                                                <option label="SAMOA" value="SAMOA"></option>
                                                                                <option label="SAN MARINO" value="SAN MARINO"></option>
                                                                                <option label="SAO TOME AND PRINCIPE" value="SAO TOME AND PRINCIPE"></option>
                                                                                <option label="SAUDI ARABIA" value="SAUDI ARABIA"></option>
                                                                                <option label="SENEGAL" value="SENEGAL"></option>
                                                                                <option label="SERBIA" value="SERBIA"></option>
                                                                                <option label="SEYCHELLES" value="SEYCHELLES"></option>
                                                                                <option label="SIERRA LEONE" value="SIERRA LEONE"></option>
                                                                                <option label="SINGAPORE" value="SINGAPORE"></option>
                                                                                <option label="SLOVAKIA" value="SLOVAKIA"></option>
                                                                                <option label="SLOVENIA" value="SLOVENIA"></option>
                                                                                <option label="SOLOMON ISLANDS" value="SOLOMON ISLANDS"></option>
                                                                                <option label="SOMALIA" value="SOMALIA"></option>
                                                                                <option label="SOUTH AFRICA" value="SOUTH AFRICA"></option>
                                                                                <option label="SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS" value="SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS"></option>
                                                                                <option label="SOUTH KOREA" value="SOUTH KOREA"></option>
                                                                                <option label="SPAIN" value="SPAIN"></option>
                                                                                <option label="SPRATLY ISLANDS" value="SPRATLY ISLANDS"></option>
                                                                                <option label="SRI LANKA" value="SRI LANKA"></option>
                                                                                <option label="SUDAN" value="SUDAN"></option>
                                                                                <option label="SURINAME" value="SURINAME"></option>
                                                                                <option label="SVALBARD" value="SVALBARD"></option>
                                                                                <option label="SWAZILAND" value="SWAZILAND"></option>
                                                                                <option label="SWEDEN" value="SWEDEN"></option>
                                                                                <option label="SWITZERLAND" value="SWITZERLAND"></option>
                                                                                <option label="SYRIA" value="SYRIA"></option>
                                                                                <option label="TAIWAN" value="TAIWAN"></option>
                                                                                <option label="TAJIKISTAN" value="TAJIKISTAN"></option>
                                                                                <option label="TANZANIA" value="TANZANIA"></option>
                                                                                <option label="THAILAND" value="THAILAND"></option>
                                                                                <option label="TIMOR LESTE" value="TIMOR LESTE"></option>
                                                                                <option label="TOGO" value="TOGO"></option>
                                                                                <option label="TOKELAU" value="TOKELAU"></option>
                                                                                <option label="TONGA" value="TONGA"></option>
                                                                                <option label="TRINIDAD AND TOBAGO" value="TRINIDAD AND TOBAGO"></option>
                                                                                <option label="TROMELIN ISLAND" value="TROMELIN ISLAND"></option>
                                                                                <option label="TUNISIA" value="TUNISIA"></option>
                                                                                <option label="TURKEY" value="TURKEY"></option>
                                                                                <option label="TURKMENISTAN" value="TURKMENISTAN"></option>
                                                                                <option label="TURKS AND CAICOS ISLANDS" value="TURKS AND CAICOS ISLANDS"></option>
                                                                                <option label="TUVALU" value="TUVALU"></option>
                                                                                <option label="USA" value="USA"></option>
                                                                                <option label="UGANDA" value="UGANDA"></option>
                                                                                <option label="UKRAINE" value="UKRAINE"></option>
                                                                                <option label="UNITED ARAB EMIRATES" value="UNITED ARAB EMIRATES"></option>
                                                                                <option label="UNITED KINGDOM" value="UNITED KINGDOM"></option>
                                                                                <option label="URUGUAY" value="URUGUAY"></option>
                                                                                <option label="UZBEKISTAN" value="UZBEKISTAN"></option>
                                                                                <option label="VANUATU" value="VANUATU"></option>
                                                                                <option label="VENEZUELA" value="VENEZUELA"></option>
                                                                                <option label="VIET NAM" value="VIET NAM"></option>
                                                                                <option label="VIRGIN ISLANDS" value="VIRGIN ISLANDS"></option>
                                                                                <option label="WAKE ISLAND" value="WAKE ISLAND"></option>
                                                                                <option label="WALLIS AND FUTUNA" value="WALLIS AND FUTUNA"></option>
                                                                                <option label="WEST BANK" value="WEST BANK"></option>
                                                                                <option label="WESTERN SAHARA" value="WESTERN SAHARA"></option>
                                                                                <option label="YEMEN" value="YEMEN"></option>
                                                                                <option label="YUGOSLAVIA" value="YUGOSLAVIA"></option>
                                                                                <option label="ZAMBIA" value="ZAMBIA"></option>
                                                                                <option label="ZIMBABWE" value="ZIMBABWE"></option>
             		</datalist>
             		<!-- </select> -->             
   				</td>
			</tr>
            <tr>
		        <td width="29%"><span class="style1"><i><font color="#ff0000">*</font> </i></span> <span class="style1">Email</span></td>
		        <td width="1%"><span class="style1">:</span></td>
		        <td width="70%"><input tabindex="6"  class="emel" type="text" name="emel" size="50" maxlength="50" />
		          <span id="validEmail">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		        <div id="checkuser_result" style="color:red"></div>        </td>
			</tr>  
			<tr>
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i></span> <span class="style1">Kata Laluan</span></td>
		        <td><span class="style1">:</span></td>
		        <td style="height:65px;"><input TABINDEX="3" class="password" type=password name="password">        </td>
			</tr>
			<tr>
		        <td><span class="style1"><i><font color="#ff0000">*</font> </i> Sahkan Kata Laluan</span></td>
		        <td><span class="style1">:</span></td>
		        <td><label>
		          <input type="password" TABINDEX="4" name="password2" id="password2" />
		        </label></td>
			</tr>
			<tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td style="height:48px;">
		        <!--
		         <IFRAME 
		         	scrolling="no"  height = "58" width = "160" frameborder="0" 
			 		id="jquery-captcha-refresh" 
			 		src="../servlet/ekptg.view.online.VerificationServlet">
		      	 </IFRAME>
		        	&nbsp<font size=2>[<a TABINDEX="6" href="#" onClick="javascript:doRefreshCaptcha();">refresh</a>]</font>
		        -->
		        <div id="jquery-captcha-refresh">
		        <img src="../servlet/ekptg.view.online.VerificationServlet" alt="" />        </div>
		        <!--
		                &nbsp<font size=2>[<a TABINDEX="6" href="#" onClick="javascript:doRefreshCaptcha();">refresh</a>]</font>
			-->        </td>
			</tr>
			<tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td><label>
		          <input type="text" TABINDEX="5" name="txtCaptChar" id="txtCaptChar"/> <br />
		          <span class="style2"><font color="#ff0000">Sila masukkan 5 digit aksara yang tertera di atas</font></span>
		        </label></td>
			</tr>
			<tr>
		        <td colspan="3"><span class="style2"><font color="#ff0000">Perhatian</font> : 
		         <br>Pastikan label bertanda <font color="#ff0000">*</font> diisi
		         <br>Bagi pilihan antara MyID dan MyCOID sila isi satu sahaja</span>
		        </td>
			</tr>
			<tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
			</tr>
			<tr>
		        <td colspan="2">&nbsp;</td>
		        <td>    
		        <input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onclick="daftar()" />
		        <input type="reset" value="Kosongkan" /></td>
			</tr>
    		</table>
    		</fieldset>
    	</td>
	<!-- 
</tr>
    </table>
    </fieldset>    </td> 
-->
	</tr>
</table>

<input type="hidden" name="command" value="">
</form>
</div>

<script>
	toggleDisplay = function(e){
		if (e=="kategori") {
			element = document.getElementById(e);
	
			if (element.value=='Syarikat') {
				//document.getElementById('Syarikat').style.display='';
				document.getElementById('Individu').style.display='none';
				
				document.getElementById('katindividu').style.display='none';
				document.getElementById('katxindividu').style.display='';
		
			}else if (element.value=='Individu') {
				document.getElementById('Individu').style.display='';
				document.getElementById('Syarikat').style.display='none';
			}
			
	//		 if (element.display=='inline-table') {element.display='none';}
	//		 else {element.display='inline-table';}
	//		 if (imageX=='minus') {document.getElementById('loginTitle').align='center';document.getElementById('loginHeader').valign='top';document.getElementById('loginImage').height='55';document.getElementById('loginImage').width='55';imageX='plus';}
	//		 else {document.getElementById('loginTitle').align='left';document.getElementById('loginHeader').valign='middle';document.getElementById('loginImage').height='28';document.getElementById('loginImage').width='28';imageX='minus';}		
	//	} else {
	//		element = document.getElementById(e).style;
	//		 if (element.display=='block') {element.display='none';}
	//		 else {element.display='block';}
	//		 if (imageY=='minus') {document.getElementById('pengumumanImage').src='1_files/list-add.png';imageY='plus';}
	//		 else {document.getElementById('pengumumanImage').src='1_files/list-remove.png';imageY='minus';}		
 		}else if(e=="individux"){
			alert('bukan warga');
			//if (element.value=='Individu') {
				document.getElementById('individux_').style.display='';
				document.getElementById('Individu').style.display='none';
				document.getElementById('negeri').style.display='none';
				document.getElementById('negara').style.display='';

			//}

			
		}else if(e=="xsyarikatsub"){
			el = document.getElementById(e);
			//alert('X Individu='+el.value);
			
			document.getElementById('individux_').style.display='none';
			if (el.value=='PBN'){
				document.getElementById('PBN').style.display='';
				document.getElementById('MAMP').style.display='none';
				document.getElementById('ROS').style.display='none';
				document.getElementById('SKM').style.display='none';
				document.getElementById('PBT').style.display='none';
				document.getElementById('Syarikat').style.display='none';

			}else if(el.value=='MAMP'){
				document.getElementById('PBN').style.display='none';
				document.getElementById('MAMP').style.display='';
				document.getElementById('ROS').style.display='none';
				document.getElementById('SKM').style.display='none';
				document.getElementById('PBT').style.display='none';
				document.getElementById('Syarikat').style.display='none';
				
			}else if(el.value=='ROS'){
				document.getElementById('PBN').style.display='none';
				document.getElementById('MAMP').style.display='none';
				document.getElementById('ROS').style.display='';			
				document.getElementById('SKM').style.display='none';
				document.getElementById('PBT').style.display='none';
				document.getElementById('Syarikat').style.display='none';
				
			}else if(el.value=='SKM'){
				document.getElementById('PBN').style.display='none';
				document.getElementById('MAMP').style.display='none';
				document.getElementById('ROS').style.display='none';
				document.getElementById('SKM').style.display='';
				document.getElementById('PBT').style.display='none';
				document.getElementById('Syarikat').style.display='none';
				
			}else if(el.value=='PBT'){
				document.getElementById('PBN').style.display='none';
				document.getElementById('MAMP').style.display='none';
				document.getElementById('ROS').style.display='none';
				document.getElementById('SKM').style.display='none';
				document.getElementById('PBT').style.display='';
				document.getElementById('Syarikat').style.display='none';

			}else if(el.value=='SSM'){
				document.getElementById('PBN').style.display='none';
				document.getElementById('MAMP').style.display='none';
				document.getElementById('ROS').style.display='none';
				document.getElementById('SKM').style.display='none';
				document.getElementById('PBT').style.display='none';
				document.getElementById('Syarikat').style.display='';
			}
 		/*		
		option value="PBN" >Agensi Kerajaan Negeri</option>
       	<option value="MAMP">Badan Berkanun</option>
       	<option value="ROS">Badan Bukan Kerajaan</option>
       	<option value="SKM">Koperasi</option>
       	<option value="PBT">Pihak Berkuasa Tempatan</option>
       	<option value="SSM">Syarikat</option>*/
       	
       	}
		
	}

	checkIfNoKpIsExist = function() {
		url = "../servlet/ekptg.view.online.Portal";
		document.f1.command.value = "doCheckNoKp";
		actionName = "doCheckNoKp";
		target = "checknokp_result";
		doAjaxUpdater(document.f1, url, target, actionName);
		
	}

	checkIfUserIsExist = function() {
		url = "../servlet/ekptg.view.online.Portal";
		actionName = "doCheckUsername";
		target = "checkuser_result";
		doAjaxUpdater(document.f1, url, target, actionName);
		
	}

	clearIt = function() {
		url = "../servlet/ekptg.view.online.Portal";
		actionName = "doClearResult";
		target = "checkuser_result";
		doAjaxUpdater(document.f1, url, target, actionName);
		
	}

	pwdStrength = function () {
		$jquery('.password').pstrength();
		//Check Valid Email
		$jquery("#emel").keyup(function(){
			var email = $jquery("#emel").val();
			if(email != 0)
			{
				if(isValidEmailAddress(email))
				{
					$jquery("#validEmail").css({
						"background-image": "url('../portal/validyes.png')"
					});
				} else {
					$jquery("#validEmail").css({
						"background-image": "url('../portal/validno.png')"
					});
				}
			} else {
				$jquery("#validEmail").css({
					"background-image": "none"
				});			
			}		
			
		});
	}

	daftar = function() {
		var dob_code = "";
 		var tt = "";

	 	if (document.f1.idKategori.value == "Individu") { 
			if (document.f1.txtNoKPBaru1.value == "") { 
				alert('Sila pastikan MyID dimasukkan dengan lengkap');
				document.f1.txtNoKPBaru1.focus();
				doRefreshCaptcha();
				return;
			}
		
		if (document.f1.txtNoKPBaru2.value == "") { 
			alert('Sila pastikan MyID dimasukkan dengan lengkap');
			document.f1.txtNoKPBaru2.focus();
			doRefreshCaptcha();
			return;
		}
		
		if (document.f1.txtNoKPBaru3.value == "") { 
			alert('Sila pastikan MyID dimasukkan dengan lengkap');
			document.f1.txtNoKPBaru3.focus();
			doRefreshCaptcha();
			return;
		}
 } else {
		if (document.f1.txtNoKPCO.value == "") { 
			alert('Sila pastikan MyCOID dimasukkan dengan lengkap');
			document.f1.txtNoKPCO.focus();
			doRefreshCaptcha();
			return;
		}
 } 
		
	var dob_code = document.f1.txtNoKPBaru1.value;
if(dob_code.charAt(0)<3)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}
var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""	+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);

	//alert("xx"+dob_code);
	
	if (document.f1.txtNoKPBaru1.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1.focus()
		return;
	}
		
	if (document.f1.nama.value == "") { 
			alert('Sila pastikan nama dimasukkan');
			document.f1.nama.focus();
			return;
	}
	
	if (document.f1.alamat1.value == "") { 
			alert('Sila pastikan alamat dimasukkan');
			document.f1.alamat1.focus();
			return;
	}
	
	if (document.f1.poskod.value == "") { 
			alert('Sila pastikan poskod dimasukkan');
			document.f1.poskod.focus();
			return;
	}
	
	if (!emailCheck(document.f1.emel.value)) {
		alert('Sila periksa alamat email anda');
		document.f1.emel.focus();
		return;
	}
	
	if (document.f1.id_negeri.value == "") { 
			alert('Sila pastikan pilih negeri');
			document.f1.id_negeri.focus();
			return;
	}
	
	
	/* remove this validation.
	if (document.f1.txtNoKPBaru1.value.length < 12) {
		alert('Sila masukkan " No KP " yang betul.');
		document.f1.txtNoKPBaru1.focus();
		return;	
	}
	*/
	var string = document.f1.password.value; 
	
	if (string.length < 6 ) { 
		alert('Sila pastikan kata laluan anda dimasukkan dengan betul');
		document.f1.password.focus();
		doRefreshCaptcha();
		return;
	}	
	if (document.f1.password.value != document.f1.password2.value) {
		alert('Sila pastikan kata laluan adalah sama');
		document.f1.password2.focus();
		doRefreshCaptcha();
		return;
	}
	/*if (document.f1.txtNoKPBaru.value == ""){
		alert('Sila masukkan " MyID " terlebih dahulu.');
		document.f1.txtNoKPBaru.focus();
		return;
	} */

	if (document.f1.idKategori.value == "Individu") {
		document.f1.txtNoKPBaru.value = document.f1.txtNoKPBaru1.value+""+ document.f1.txtNoKPBaru2.value+""+document.f1.txtNoKPBaru3.value;
	} else {
		document.f1.txtNoKPBaru.value = document.f1.txtNoKPCO.value
	}
      
		url = "../servlet/ekptg.view.online.Portal";
		actionName = "doRegister";
		target = "doRegisterResult";
		doAjaxUpdater(document.f1, url, target, actionName);
	
	}

	doEffect = function () {
		new Effect.Highlight('doRegisterResult', {startcolor:'#CEB089',endcolor:'#FFFFFF', restorecolor:'#EFEFEF'});
	}

	doHide = function () {
		$jquery('#RegistrationForm').hide("slow");
	}
	
	doRefreshCaptcha = function () {
		$jquery('#jquery-captcha-refresh').attr("src","../servlet/ekptg.view.online.VerificationServlet"); 
	}
	
	isValidEmailAddress = function (emailAddress) {
		var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
		return pattern.test(emailAddress);
	}

	function emailCheck (emailStr) {
	
	/* The following variable tells the rest of the function whether or not
	to verify that the address ends in a two-letter country or well-known
	TLD.  1 means check it, 0 means don't. */
	
	var checkTLD=1;
	
	/* The following is the list of known TLDs that an e-mail address must end with. */
	
	var knownDomsPat=/^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	
	/* The following pattern is used to check if the entered e-mail address
	fits the user@domain format.  It also is used to separate the username
	from the domain. */
	
	var emailPat=/^(.+)@(.+)$/;
	
	/* The following string represents the pattern for matching all special
	characters.  We don't want to allow special characters in the address. 
	These characters include ( ) < > @ , ; : \ " . [ ] */
	
	var specialChars="\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
	
	/* The following string represents the range of characters allowed in a 
	username or domainname.  It really states which chars aren't allowed.*/
	
	var validChars="\[^\\s" + specialChars + "\]";
	
	/* The following pattern applies if the "user" is a quoted string (in
	which case, there are no rules about which characters are allowed
	and which aren't; anything goes).  E.g. "jiminy cricket"@disney.com
	is a legal e-mail address. */
	
	var quotedUser="(\"[^\"]*\")";
	
	/* The following pattern applies for domains that are IP addresses,
	rather than symbolic names.  E.g. joe@[123.124.233.4] is a legal
	e-mail address. NOTE: The square brackets are required. */
	
	var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
	
	/* The following string represents an atom (basically a series of non-special characters.) */
	
	var atom=validChars + '+';
	
	/* The following string represents one word in the typical username.
	For example, in john.doe@somewhere.com, john and doe are words.
	Basically, a word is either an atom or quoted string. */
	
	var word="(" + atom + "|" + quotedUser + ")";
	
	// The following pattern describes the structure of the user
	
	var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
	
	/* The following pattern describes the structure of a normal symbolic
	domain, as opposed to ipDomainPat, shown above. */
	
	var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");
	
	/* Finally, let's start trying to figure out if the supplied address is valid. */
	
	/* Begin with the coarse pattern to simply break up user@domain into
	different pieces that are easy to analyze. */
	
	var matchArray=emailStr.match(emailPat);
	
	if (matchArray==null) {
	
	/* Too many/few @'s or something; basically, this address doesn't
	even fit the general mould of a valid e-mail address. */
	
	//alert("Email address seems incorrect (check @ and .'s)");
	return false;
	}
	var user=matchArray[1];
	var domain=matchArray[2];
	
	// Start by checking that only basic ASCII characters are in the strings (0-127).
	
	for (i=0; i<user.length; i++) {
	if (user.charCodeAt(i)>127) {
	//alert("Ths username contains invalid characters.");
	return false;
	   }
	}
	for (i=0; i<domain.length; i++) {
	if (domain.charCodeAt(i)>127) {
	//alert("Ths domain name contains invalid characters.");
	return false;
	   }
	}
	
	// See if "user" is valid 
	
	if (user.match(userPat)==null) {
	
	// user is not valid
	
	//alert("The username doesn't seem to be valid.");
	return false;
	}
	
	/* if the e-mail address is at an IP address (as opposed to a symbolic
	host name) make sure the IP address is valid. */
	
	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) {
	
	// this is an IP address
	
	for (var i=1;i<=4;i++) {
	if (IPArray[i]>255) {
	//alert("Destination IP address is invalid!");
	return false;
	   }
	}
	return true;
	}
	
	// Domain is symbolic name.  Check if it's valid.
	 
	var atomPat=new RegExp("^" + atom + "$");
	var domArr=domain.split(".");
	var len=domArr.length;
	for (i=0;i<len;i++) {
	if (domArr[i].search(atomPat)==-1) {
	//alert("The domain name does not seem to be valid.");
	return false;
	   }
	}
	
	/* domain name seems valid, but now make sure that it ends in a
	known top-level domain (like com, edu, gov) or a two-letter word,
	representing country (uk, nl), and that there's a hostname preceding 
	the domain or country. */
	
	if (checkTLD && domArr[domArr.length-1].length!=2 && 
	domArr[domArr.length-1].search(knownDomsPat)==-1) {
	//alert("The address must end in a well-known domain or two letter " + "country.");
	return false;
	}
	
	// Make sure there's a host name preceding the domain.
	
	if (len<2) {
	//alert("This address is missing a hostname!");
	return false;
	}
	
	// If we've gotten this far, everything's valid!
	return true;
	}
	
	
	var dtCh= "/";
	var minYear=1900;
	var maxYear=2100;
	
	function isInteger(s){
		var i;
	    for (i = 0; i < s.length; i++){   
	        // Check that current character is number.
	        var c = s.charAt(i);
	        if (((c < "0") || (c > "9"))) return false;
	    }
	    // All characters are numbers.
	    return true;
	}
	
	function stripCharsInBag(s, bag){
		var i;
	    var returnString = "";
	    // Search through string's characters one by one.
	    // If character is not in bag, append to returnString.
	    for (i = 0; i < s.length; i++){   
	        var c = s.charAt(i);
	        if (bag.indexOf(c) == -1) returnString += c;
	    }
	    return returnString;
	}

	function daysInFebruary (year){
		// February has 29 days in any year evenly divisible by four,
	    // EXCEPT for centurial years which are not also divisible by 400.
	    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
	function DaysArray(n) {
		for (var i = 1; i <= n; i++) {
			this[i] = 31
			if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
			if (i==2) {this[i] = 29}
	   } 
	   return this
	}

	isIc = function(dtStr){
		var daysInMonth = DaysArray(12)
		var pos1=dtStr.indexOf(dtCh)
		var pos2=dtStr.indexOf(dtCh,pos1+1)
		var strDay=dtStr.substring(0,pos1)
		var strMonth=dtStr.substring(pos1+1,pos2)
		var strYear=dtStr.substring(pos2+1)
		strYr=strYear
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
		}
		month=parseInt(strMonth)
		day=parseInt(strDay)
		year=parseInt(strYr)
		if (pos1==-1 || pos2==-1){
			alert("Format MyID seperti ini, cth : 800808-08-0008 ")
			return false
		}
		if (strMonth.length<1 || month<1 || month>12){
			alert("Sila masukkan bulan yang sah pada MyID")
			return false
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
			alert("Sila masukkan hari yang sah pada MyID")
			return false
		}
		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
			alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
			return false
		}
		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
			alert("Sila masukkan MyID yang sah")
			return false
		}
	return true
	}

	validateXIC = function(e,elmnt,content,nextElementID) {	
		//if it is character, then remove it..	
		var keycode;
		if(window.event)keycode = window.event.keyCode;
		else if (e) keycode = e.which;
		else return true;
		
		//alert(keycode);
		
		if((keycode >= 37 && keycode <= 40) || (keycode == 9)) return false;
		if (isNaN(content)) {
			elmnt.value = RemoveNonNumericX(content);
			return;
		}
		//goto next column if maximum length reach	
		
		if (content.length == elmnt.maxLength) 
		{
		//alert("3:"+nextElementID);
		goTo(nextElementID);
		//nextElementID.focus();
		}
		
	}

	function RemoveNonNumericX( strString ){
	      var strValidCharacters = "1234567890";
	      var strReturn = "";
	      var strBuffer = "";
	      var intIndex = 0;
	      // Loop through the string
	      for( intIndex = 0; intIndex < strString.length; intIndex++ )
	      {
	            strBuffer = strString.substr( intIndex, 1 );
	            // Is this a number
	            if( strValidCharacters.indexOf( strBuffer ) > -1 )
	            {
	                  strReturn += strBuffer;
	            }
	      }
	      return strReturn;
	}
	
</script>