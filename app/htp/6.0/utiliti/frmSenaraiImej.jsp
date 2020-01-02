<!--<strong> Senarai Fail Gadaian </strong>
<br><br>-->
<style type="text/css">
<!--

body {
text-align:center;
font-family:serif;
background:#FAF8CC;
}

.style1 {color: #0000FF}

.table_header {
color:#FFF;
background-color:#960;
font-weight:400;
border-style:10px solid #FFF;
}

.module_content,td.row1,.row1 {
background-color:#FAF8CC;
}

.table_row2,.module_content_bg {
background-color:#F2E3E9;
}

td.row2,td.selected,.row2 {
background-color:#ebbc5d;
}

.stylobutton {
width:140px;
}

.stylobutton100 {
width:100px;
}

input.button {
border:0.05em solid;
cursor:pointer;
font-weight:700 !important;
overflow:visible;
width:103px;
padding:0 0.25em;
}
-->
</style>
<table width="100%" height="100%" border="0" cellspacing="2" cellpadding="2">
	
  	<tr valign="top">
  		<td width="20%">
			<fieldset>
				<table width="100%">
					<tr>
						<td>&nbsp;</td>
					</tr>
				#if ($!senaraiImej.size()>0 )

				#if($!senaraiImej.size()>10)		    
			     	<div style="width:100%;height:200;overflow:auto"> 
				#end	        

		        #set ($xx = 0)
		        #foreach ($listlampiran in $senaraiImej)
		        #set ($xx = $xx + 1)	       	
	        		<tr>  
	        			<td > $!xx.
	 	        			<a onClick="javascript:cetakImejSenarai('$listlampiran.idGambar')" 
	        					href="#" style="color:#0000FF">$listlampiran.namaFail
	        				</a>
	        			</td>
	       			</tr>
	       		#end
	       		#if ($xx == 0)
	       			<tr><td>Tiada Maklumat</td></tr>
	       		#end

				#if($!SenaraiImej.size()>10)		    
					</div>
				#end
					        	  
				#end
					
				</table>
			</fieldset>
		</td>
		<td width="80%">
		#if($!modeImej=="view")		    
			<fieldset>
				<table width="100%">
					<tr>
						<td>
							<div align="center">
			      				 <p><a href=# onClick="javascript:cetakImejSenaraiFull('$idGambar')">
			      				 	<img src="../../servlet/ekptg.view.htp.FrmRekodDisplayImej?id=$!idGambar" alt="Imej" border="1" width="300" height="350"/>
			      				 </a></p>
			      			</div>						
			      		</td>
					</tr>
				</table>
			</fieldset>
		#elseif($!modeImej=="viewFull") 	    
			<fieldset>
				<table width="100%">
					<tr>
						<td>
							<div align="center">
			      				 <p><a href=# onClick="javascript:cetakImejSenarai('$idGambar')">
			      				 	<img src="../../servlet/ekptg.view.htp.FrmRekodDisplayImej?id=$!idGambar" alt="Imej" border="1" />
			      				 </a></p>
			      			</div>						
			      		</td>
					</tr>
				</table>
			</fieldset>
		#end
		</td>
	</tr>

</table>
<input type="hidden" name="idHakmilik" value="$idHakmilik" />

<script language="javascript" type="text/javascript">
	
	//photograf = new Image();
	//photograf.src = '../servlet/ekptg.view.htp.FrmRekodDisplayImej?id=$!idGambar');
	//var width = photograf.width;
	//var height = photograf.height;
	//alert(height);
	
	function cetakImejSenarai(id){
		doAjaxCall${formName}("view","idGambar="+id);
	}
	
	function cetakImejSenaraiFull(id){
		doAjaxCall${formName}("viewFull","idGambar="+id);
	}	
</script>