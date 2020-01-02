--devxxxx---
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js"></script>
<script>var $jquery = jQuery.noConflict();</script>
<script type="text/javascript" src="../../library/js/jsplumb.js"></script>
<style>

.white_content {
  display: none;
  position: absolute;
  top: 0%;
  left: 2%;
  width:1300px; 
  height:620px;
  padding: 5px;
  border: 1px solid black;
  background-color: white;
  z-index: 1002;
  overflow: scroll;
}

</style>

<script>
var warisObj = new Array();
var i = 0;
var wData = '';
var simatiInfo = new Array();
var isDraw = false;
</script>

#if ($wait == "true")
<script>

window.onload = function() {
  document.querySelector('head').innerHTML += '<link rel="stylesheet" href="../../library/css/waris.css" type="text/css"/>';
  generateFaraid();
}
function generateFaraid()  { 
	doAjaxCall${formName}('generate');
}


</script>

#else

<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>
<!--#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())-->

<input name="action" type="hidden" value="$action">
<input name="idPermohonan" type="hidden" value="$idPermohonan" />
<input name="bhgnBaitulMal" type="hidden" value="$bhgnBaitulMal" />
<input name="idSimati" type="hidden" value="$idSimati" />
<div class="black_overlay">
  <table width="100%" border="0">
    <tr>
      <td><fieldset>
      <legend>MAKLUMAT SIMATI</legend>
      
      <table width="100%">
      
        <tr>
          <td width="30%"><div align="right" class="style1">
            <div align="right">Nama</div>
          </div></td>
          <td width="1%"><div align="center" class="style1">
            <div align="right">:</div>
          </div></td>
          <td width="69%">
          $namaSiMati.toUpperCase()
          </td>
        </tr>
        <tr>
          <td><div align="right" class="style1">
            <div align="right">No KP</div>
          </div></td>
          <td><div align="center" class="style1">
            <div align="right">:</div>
          </div></td>
          <td>$!noKp</td>
        </tr>
        <tr>
          <td><div align="right" class="style1">
            <div align="right">Tarikh Mati</div>
          </div></td>
          <td><div align="center" class="style1">
            <div align="right">:</div>
          </div></td>
          <td>$!tarikhMati</td>
        </tr>
      </table>
      </fieldset>
      </td>
    </tr>
    <tr>
      <td><fieldset>
      <legend>MAKLUMAT PEWARIS</legend>
      <table width="100%" border="0">
        <tr class="table_header">
           <td width="10%"><div align="center">NO</div></td>
           <td width="30%"><div align="center">NAMA</div></td>
           <td width="30%"><div align="center">HUBUNGAN</div></td>
           <td width="30%"><div align="center">BAHAGIAN</div></td>
        </tr>
       #set ( $cnt = 0 )
       #foreach ($waris in $Senarai)
  	  
  	  #if ($waris.getBahagianAtas() == 0)
  	  ## prepare on how to skip this data
  	  #end
  	  
	  #set ( $cnt = $cnt + 1 )
	  #set( $i = $velocityCount )
	  #if ( ($i % 2) == 0 )
	      #set( $row = "row2" )
	  #else
	      #set( $row = "row1" )
	  #end
	  
	  #if ($waris.getNama().toUpperCase() == "BAITULMAL")
	  #set ($bhgnBaitulMal = $waris.getBahagianAtas())
	  #end 

        <tr>
           <td width="10%" class="$row">$cnt</td>
           <td width="30%" class="$row">$waris.getNama()</td>
           <td width="30%" class="$row">$waris.getHubungan()</td>
           <td width="30%" class="$row">$waris.getBahagian()</td>
        </tr>
        #end 
      </table>
      </fieldset>
      </td>
    </tr>
    <tr>
      <td>
      <table width="100%" border="0">
        <tr>
          <td><label>
            <div align="center">
           
              <input type="button" name="button" id="button" 
              value="Cetak" onclick="javascript:cetak('$idSimati','$idPermohonan','$!bhgnBaitulMal','$!idPermohonanSimati');return true;" />
            </div>
          </label></td>
        </tr>
      </table></td>
    </tr>
  </table>

       
      <a href="#" onclick="Effect.toggle('toggle_appear', 'appear');resize();return false;">
      [Lihat Pembahagian Terperinci]
      </a><a href="#" id="showDiagramBtn">[Diagram]</a>
      <div id="toggle_appear" style="display:none">
	      <div>
	      <br>
	      $!detailFaraidCalculation
	      <br>
	      Masa Diambil:$!timetaken saat 
	      </div>
      </div>
 
  
 <!-- Dev  -->

<div id="diagram" class="white_content" >
<div style="text-align: right;">
	<button id="closeButton">Tutup</button>
</div>

</div>
</div>
	<script>
	var namaSimati = '$namaSiMati';
	var simati = JSON.parse('{' + '"nama": "' + namaSimati + '",' + '"bahagian":' + '"X"' +'}');
	
	simatiInfo.push(simati);
	//console.log(simatiInfo);
	//var simati = JSON.parse('{' + '"nama": "' + namaSimati + '",' + '"bahagian":' + '""' +'}');
	
	
	</script>

	#foreach ($waris in $allWaris)	
		<script>
		wData = '{' + 
			        '"nama":' + '"$waris.nama"' + ',' +
		            '"jantina":' + '"$waris.jantina",' +
		            '"status_hidup":' + '"$waris.Status_Hidup",' +
		            '"hubungan":' + '"$waris.hubungan",' +
		            '"lapisan":' + '"$waris.lapisan",' +
		            '"bahagian":' + '"$waris.bahagian"' +
		         '}'
		warisObj[i++] = JSON.parse(wData); //JSON.parse('{"nama":'+'"$waris.nama"'+'}');
	
	</script>
#end 

<script>

 function drawDiagram(callback) {
	 
	 var i = 0;
		var waris;
		//var al = false, ap = false, cucul = false;
		var drawStatus = [];
		var relationStatus = [];
		var div = document.getElementById('diagram');
		
		div.innerHTML = div.innerHTML + '<div class="reltype" id="simati">Simati</div>';
		insertSimatiInfo("simati", "SIMATI", simatiInfo);
		
		console.log('******');
		console.log(warisObj);
		console.log('******');
		
		// Loop to create waris div
		for (i=0;i<warisObj.length;i++) {
			
			if (warisObj[i].nama === 'BAITULMAL' || warisObj[i].nama ==='BaitulMal') {
				var divId = 'BAITULMAL';
				div.innerHTML = div.innerHTML + '<div class="reltype" id="'+divId+'">XXX</div>';
				drawStatus.push(divId);
				
				var relationText = 'BAITULMAL';
				var resultList = "<tr>" +
	            "<td style=\"font-size: 7pt;\" nowrap>" + warisObj[i].nama + "</td><td width=\"5\"></td>" +
	            "<td style=\"font-size: 7pt;\">" + warisObj[i].bahagian + "</td>";
	        	"</tr>";
				var card = "<div>" +
			        "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"wcard\" border=\"0\" >" +
			        "<tr><td colspan=\"5\" align=\"center\" style=\"border-bottom:1pt solid black;\" nowrap><b>" + relationText +
			        "</b></td></tr>" + resultList + "</table>" +
			        "</div>";
			        jQuery("#" + divId).html(card); 
			} else if (warisObj[i].bahagian ==='$waris.bahagian') {
						console.log(warisObj[i].nama + '-'+'$waris.bahagian');		        
			} else {
				
				var divId = hubunganToDiv(warisObj[i].hubungan,warisObj[i].lapisan);
				
				if (drawStatus.indexOf(divId) === -1) {
					div.innerHTML = div.innerHTML + '<div class="reltype" id="'+divId+'">XXX</div>';
					drawStatus.push(divId);
					var bahagian = '';
					
					if (warisObj[i].status_hidup === '1') {
						bahagian = 'X';
					} else {
						bahagian = warisObj[i].bahagian;
					}
					var itemInfo = JSON.parse('{' + '"nama": "' + warisObj[i].nama + '",' + '"bahagian": "' + bahagian +'"}');
					var warisInfo = [];
					warisInfo.push(itemInfo);
				}	
			}
			
		}
		
		relayout();
		
		// Loop to create connection between waris div
		for (j=0;j<warisObj.length;j++) {
			
			var divId = hubunganToDiv(warisObj[j].hubungan,warisObj[j].lapisan);
			if (relationStatus.indexOf(divId) === -1) {
				setRelation(divId, getDirectRelation(divId, warisObj[j].lapisan));			
			}

		}
		
		var warisInfo = getWarisInfoPerRelation(warisObj);
		
		// Loop to append waris details into waris div
		for (i=0;i<warisInfo.length;i++) {
			var divId = hubunganToDiv(warisInfo[i].rel,warisInfo[i].lapisan);
			insertWarisInfo(divId, warisInfo[i].rel, warisInfo[i].info);
		}
	
	isDraw = true;
	callback(true);
	 
 }
 
 jQuery('#showDiagramBtn').click(function(){
	 jQuery("#diagram").show();
	 if (!isDraw) {
		 drawDiagram(function(callback) {
			 //jsPlumb.draggable(jQuery('.reltype').not('#simati'));
			 jsPlumb.draggable(jQuery('#BAITULMAL'));
			
		 });
		;
	 }
 });
 
 jQuery('#closeButton').click(function(){
	 jQuery("#diagram").hide();
 });
 
 

</script>  

<!-- end of dev --> 

#end


<script>

// To move up when top rows is not exist
function relayout(){
	var isRow1Exist = false;
	var isRow2Exist = false;
	var isRow3Exist = false;
	var isRow4Exist = false;
	
	var row1 = ['neneksi','datuksi','neneksb','datuksp'];
	var row2 = ['ibu','bapa','bsaudarak','bsaudarab','absaudarak','absaudarab'];
	var row3 = ['simati', 'saudarapk','saudarapb','saudarapi','saudaralk', 'asaudaralk','saudaralb','asaudaralb','saudarali','asaudarali'];
	var row4 = ['ap','al','isteri','isterial','cucul'];
	
	var IDs = [];
	jQuery("#diagram").find(".reltype").each(function(){ 
		IDs.push(this.id); 
	});
	console.log(IDs);
	
	for(var i=0; i<row1.length; i++) {
		if (IDs.indexOf(row1[i]) >= 0) {
			isRow1Exist = true;
		}
	}
	console.log('isRow1Exist:'+isRow1Exist);
	
	for(var i=0; i<row2.length; i++) {
		if (IDs.indexOf(row2[i]) >= 0) {
			isRow2Exist = true;
		}
	}
	console.log('isRow2Exist:'+isRow2Exist);
	
	if (!isRow1Exist && !isRow2Exist) {
		for(var i=0; i<row3.length; i++) {
			if (row3[i].length > 0) {
				var el = jQuery("#"+row3[i]);
				if (el.length>0) {
					var newTop = parseFloat(el.css("top")) - 220;
					console.log(row3[i] + " " + newTop);
					el.css({top: newTop+'px'});
				}
			}	
		}
		for(var i=0; i<row4.length; i++) {
			if (row4[i].length > 0) {
				var el = jQuery("#"+row4[i]);
				if (el.length>0) {
					var newTop = parseFloat(el.css("top")) - 220;
					console.log("row4:"+row4[i] + " " + newTop);
					el.css({top: newTop+'px'});
				}
			}	
		}
	} else if (!isRow2Exist) { // if row2 not exist
		for(var i=0; i<row3.length; i++) {
			if (row3[i].length > 0) {
				var el = jQuery("#"+row3[i]);
				if (el.length>0) {
					var newTop = parseFloat(el.css("top")) - 75;
					console.log(row3[i] + " " + newTop);
					el.css({top: newTop+'px'});
				}
			}	
		}
		for(var i=0; i<row4.length; i++) {
			if (row4[i].length > 0) {
				var el = jQuery("#"+row4[i]);
				if (el.length>0) {
					var newTop = parseFloat(el.css("top")) - 75;
					console.log("row4:"+row4[i] + " " + newTop);
					el.css({top: newTop+'px'});
				}
			}	
		} 
	} else if (!isRow1Exist) { //if row1 not exist
		
		for(var i=0; i<row2.length; i++) {
			if (row2[i].length > 0) {
				var el = jQuery("#"+row2[i]);
				if (el.length>0) {
					var newTop = parseFloat(el.css("top")) - 75;
					console.log(row2[i] + " " + newTop);
					el.css({top: newTop+'px'});
				}
			}	
		}
	
		for(var i=0; i<row3.length; i++) {
			if (row3[i].length > 0) {
				var el = jQuery("#"+row3[i]);
				if (el.length>0) {
					var newTop = parseFloat(el.css("top")) - 75;
					console.log(row3[i] + " " + newTop);
					el.css({top: newTop+'px'});
				}
			}	
		}
		for(var i=0; i<row4.length; i++) {
			if (row4[i].length > 0) {
				var el = jQuery("#"+row4[i]);
				if (el.length>0) {
					var newTop = parseFloat(el.css("top")) - 75;
					console.log("row4:"+row4[i] + " " + newTop);
					el.css({top: newTop+'px'});
				}
			}	
		}
				
	}

}

/*jQuery(window).resize(function(){
	setTimeout(function(){
		jsPlumb.repaintEverything();
	},1000);
}); */

// Get divID target based on source id to draw connection
function getDirectRelation(divId, lapisan){
	if (divId === 'cucul' && lapisan =='2') {
		return 'al'
	} else if (divId === 'isterial' && lapisan =='2') {
		return 'al';
	} else if ((divId === 'saudaralb') && (jQuery('#saudaralk').length)) {
		return 'saudaralk';
	} else if ((divId === 'saudarali') && (jQuery('#saudaralb').length))  {
		return 'saudaralb';
	} else if ((divId === 'saudarapb') && (jQuery('#saudarapk').length)) {
		return 'saudarapk';
	} else if ((divId === 'saudarapi') && (jQuery('#saudarapb').length))  {
		return 'saudarapb';
	} else if ((divId === 'bsaudarak') && (jQuery('#bapa').length))  {
		return 'bapa';
	} else if ((divId === 'bsaudarab') && (jQuery('#bsaudarak').length))  {
		return 'bsaudarak';
	} else if ((divId === 'absaudarak') && (jQuery('#bsaudarak').length))  {
		return 'bsaudarak';
	} else if ((divId === 'absaudarab') && (jQuery('#bsaudarab').length))  {
		return 'bsaudarab';
	} else {
		return 'simati';
	}
}

// Get Direct Relation Name with SIMATI based on div id
function getDiagramRelationByDivId(divId, relationName) {
	if (divId === 'cucul') {
		return 'CUCU LELAKI';
	} else if (divId == 'isterial') {
			return 'MENANTU';
	} else if (divId == 'neneksi') {
		return 'NENEK SEBELAH IBU';
	} else if (divId == 'saudarapk') {
		return 'S.PEREMPUAN (K)';
	} else if (divId == 'saudaralk') {
		return 'S.LELAKI (K)';
	} else if (divId == 'saudaralb') {
		return 'S.LELAKI (SB)';
	} else if (divId == 'saudarali') {
		return 'S.LELAKI (SI)';
	} else if (divId == 'saudarapb') {
		return 'S.PEREMPUAN(SB)';
	} else if (divId == 'saudarapi') {
		return 'S.PEREMPUAN(SI)';
	} else if (divId == 'bsaudarak') {
		return 'BAPA SAUDARA (K)';
	} else if (divId == 'bsaudarab') {
		return 'BAPA SAUDARA (SB)';
	} else if (divId == 'absaudarak') {
		return 'ANAK BAPA SAUDARA (K)';
	} else if (divId == 'absaudarab') {
		return 'ANAK BAPA SAUDARA (SB)';
	} else if (divId == 'neneksb') {
		return 'NENEK SEBELAH BAPA';
	} else if (divId == 'neneksi') {
		return 'NENEK SEBELAH IBU';
	} else {
		return relationName;
	}
}

function getWarisInfoPerRelation(warisObj) {
	// template data - {"rel": "ANAK LELAKI","lapisan":"1", "status_hidup":"1", "info":[]}
	var jenisHubungan = [];
	var i;
	for (i=0;i<warisObj.length;i++) {
		
			if (jenisHubungan.indexOf(warisObj[i].hubungan) === -1) {
				var data = JSON.parse('{"rel": "'+ warisObj[i].hubungan +'", "lapisan": "'+ warisObj[i].lapisan +'", "status_hidup": "'+ warisObj[i].status_hidup +'"}');
				jenisHubungan.push(data);
			}
	}
	//console.log(jenisHubungan);
	var warisInfo = [];
	for (i=0;i<jenisHubungan.length;i++) {
		var info = getWarisByType(jenisHubungan[i].rel, warisObj);
		var itemInfo = '{"rel": "'+ jenisHubungan[i].rel +'","lapisan": "'+ jenisHubungan[i].lapisan +'","status_hidup": "'+ jenisHubungan[i].status_hidup +'", "info": '+ info +'}';
		warisInfo.push(JSON.parse(itemInfo));
	}
	return warisInfo;
}

function getWarisByType(relation, warisObj){
	var items = '[';
	var i;
	var bahagian = '';
	// template - '{' + '"nama": "' + warisObj[i].nama + '",' + '"bahagian": "' + bahagian +'"}'
	for (i=0;i<warisObj.length;i++) {
		if (warisObj[i].hubungan === relation) {
			if (warisObj[i].status_hidup === '1') {
				bahagian = 'X';
			} else {
				bahagian = warisObj[i].bahagian;
			}
			var obj = '{' + '"nama": "' + warisObj[i].nama + '",' + '"bahagian": "' + bahagian +'"}';
			items += obj+','; 
		}
	}
	items = items.slice(0, -1);
	items+=']';
	return items;
}

function insertWarisInfo(divId, relationName, infoItem) {
	var resultList = '';
	var bahagian = '';
    for (var i = 0; i < infoItem.length; i++) {
    	bahagian = infoItem[i].bahagian;
    	if (infoItem[i].bahagian === 'X') {
    		bahagian = '<img width="12" height="12" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACcUlEQVR42oVTTWgTQRSe2lRj0qZNNzM7uzv70914FgQFbx71oLfqQW9CQQ9iQa1WSxVEevJQFFFBCRG89KAUpailG9NGE7H2J7Et0qZV0BpT0Cq0jT/4nhgI24gDj30787037/veG0LWL1+8iW1Ny+LwpGyeHefGyRTVWjsDAQXOash/Vq0riWMfVHuuoNmfC5qzirao2l/eq3Y+EdGOI6ZqZCwcNnKycR8D5tWWVFY2L45IyiG0CfDnuJXCsynZ7L/bxK11N0NA/5Jq/4Ryz+wCGl5AlJBNGabfQ8wUNx+QCkzd04jWjtmTVDmN/97gU4Q0jMlGL2BWXjFjAL5rSaaf+KNJvDGyDTjm5xVrpLvKza2EbMxQ0VNU7R9ZbsSgOj9QHAadFq40NEgkw4y2RdVZzjHrvDcYEgayTL+K1Y0ycf1yKNSM+5Pc6P6oOcuupG8nOfj5JJy1Yaoe8AZnqHYJgkvTshlvJ2Rz+cyFthaEU0pSsZuMQ68xQYJq+8uANtBhgpt4c2mU6dc6CGmsTD4ICYoiWnKp2EOeMXEQy5lgZldZsAwTPX9bdqeXNIe81MZk89yi5nxNSGIH6fGHDRDkbV6xXBQM1UbBXlLjRmtF2RXUfHNKiwvCL9yqr6e4t2GY6p0weatpZvRhq14zI1YWzDsvjyV+FKt7zvSuytGum+Hm4JLm/ALOD2HM/FXaWTsg8SOImVGsoXXzEg/R6DS3hgqqvTLLrUGg0PGoSd6H9gL8Wd7yBM/ecMvtC9Et/3pMvjQT3cDvHYC/FbXodzT0cQ/EvUCqDJt31dwMBmW3WdvpRtS9aCPg3w4GebXn/BuZxuY/Lt1PYQAAAABJRU5ErkJggg==">';
    	}
    		resultList = resultList + "<tr>" +
            "<td style=\"font-size:7pt;width:110px;text-overflow: ellipsis; border-bottom:1pt dotted gray; vertical-align: top;\">" + infoItem[i].nama + "</td><td width=\"5\"></td>" +
            "<td style=\"font-size:7pt;border-bottom:1pt dotted gray; vertical-align: top;\" >" + bahagian + "</td>";
        "</tr>";
    }
    var relationText = getDiagramRelationByDivId(divId, relationName);
    var card = "<div>" +
        "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"  style=\"border-collapse:collapse;\" cellpadding=\"0\" cellspacing=\"0\" class=\"wcard\" border=\"0\" >" +
        "<tr><td colspan=\"5\" align=\"center\" style=\"border-bottom:1pt solid black;\" nowrap><b>" + relationText +
        "</b></td></tr>" + resultList + "</table>" +
        "</div>";
        jQuery("#" + divId).html(card);  
}

function insertSimatiInfo(divId, relationName, faraidResults) {
    var resultList = "";
    for (var i = 0; i < faraidResults.length; i++) {
        resultList = resultList + "<tr>" +
            "<td style=\"font-size: 7pt; border-bottom:1pt dotted black;\" nowrap>" + faraidResults[i].nama + "</td><td width=\"5\"></td>" +
            "<td style=\"font-size: 7pt;border-bottom:1pt dotted black;\"><img height=\"12\" width=\"12\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACcUlEQVR42oVTTWgTQRSe2lRj0qZNNzM7uzv70914FgQFbx71oLfqQW9CQQ9iQa1WSxVEevJQFFFBCRG89KAUpailG9NGE7H2J7Et0qZV0BpT0Cq0jT/4nhgI24gDj30787037/veG0LWL1+8iW1Ny+LwpGyeHefGyRTVWjsDAQXOash/Vq0riWMfVHuuoNmfC5qzirao2l/eq3Y+EdGOI6ZqZCwcNnKycR8D5tWWVFY2L45IyiG0CfDnuJXCsynZ7L/bxK11N0NA/5Jq/4Ryz+wCGl5AlJBNGabfQ8wUNx+QCkzd04jWjtmTVDmN/97gU4Q0jMlGL2BWXjFjAL5rSaaf+KNJvDGyDTjm5xVrpLvKza2EbMxQ0VNU7R9ZbsSgOj9QHAadFq40NEgkw4y2RdVZzjHrvDcYEgayTL+K1Y0ycf1yKNSM+5Pc6P6oOcuupG8nOfj5JJy1Yaoe8AZnqHYJgkvTshlvJ2Rz+cyFthaEU0pSsZuMQ68xQYJq+8uANtBhgpt4c2mU6dc6CGmsTD4ICYoiWnKp2EOeMXEQy5lgZldZsAwTPX9bdqeXNIe81MZk89yi5nxNSGIH6fGHDRDkbV6xXBQM1UbBXlLjRmtF2RXUfHNKiwvCL9yqr6e4t2GY6p0weatpZvRhq14zI1YWzDsvjyV+FKt7zvSuytGum+Hm4JLm/ALOD2HM/FXaWTsg8SOImVGsoXXzEg/R6DS3hgqqvTLLrUGg0PGoSd6H9gL8Wd7yBM/ecMvtC9Et/3pMvjQT3cDvHYC/FbXodzT0cQ/EvUCqDJt31dwMBmW3WdvpRtS9aCPg3w4GebXn/BuZxuY/Lt1PYQAAAABJRU5ErkJggg==\"></td>";
        "</tr>";
    }
  
    var card = "<div>" +
        "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"wcard\" border=\"0\" >" +
        "<tr><td colspan=\"5\" align=\"center\" style=\"border-bottom:1pt solid black;\" nowrap><b>" + relationName +
        "</b></td></tr>" + resultList + "</table>" +
        "</div>";
        jQuery("#" + divId).html(card);   
}

// get divId based on hubungan
function hubunganToDiv(hubungan, lapis) {
	var divId = '';
	if (hubungan =='ISTERI' && lapis == '1') {
		divId = 'isteri';
	} else if (hubungan =='SUAMI' && lapis == '1') {
		divId = 'suami';
	} else if (hubungan =='MENANTU' && lapis == '1') {
		divId = 'isterial';
	} else if (hubungan ==' - ') {
		divId = 'baitulmal';
	} else if (hubungan =='ANAK LELAKI' && lapis == '1') {
		divId = 'al';
	} else if (hubungan =='ANAK PEREMPUAN' && lapis == '1') {
		divId = 'ap';
	} else if (hubungan =='IBU' && lapis == '1') {
		divId = 'ibu';
	} else if (hubungan =='BAPA' && lapis == '1') {
		divId = 'bapa';
	} else if (hubungan =='BAPA SAUDARA SEIBU SEBAPA' && lapis == '1') {
		divId = 'bsaudarak';
	} else if (hubungan =='BAPA SAUDARA SEBAPA' && lapis == '1') {
		divId = 'bsaudarab';
	} else if (hubungan =='ANAK LELAKI BAPA SAUDARA SEIBU SEBAPA' && lapis == '1') {
		divId = 'absaudarak';
	} else if (hubungan =='ANAK LELAKI BAPA SAUDARA SEBAPA' && lapis == '1') {
		divId = 'absaudarab';
	} else if (hubungan =='DATUK' && lapis == '1') {
		divId = 'datuksi';
	} else if (hubungan =='NENEK PEREMPUAN SEBELAH BAPA (HINGGA ATAS)' && lapis == '1') {
		divId = 'neneksb';
	} else if (hubungan =='NENEK PEREMPUAN SEBELAH IBU (HINGGA ATAS)' && lapis == '1') {
		divId = 'neneksi';
	} else if (hubungan =='SAUDARA PEREMPUAN SEIBU SEBAPA' && lapis == '1') {
		divId = 'saudarapk';
	} else if (hubungan =='SAUDARA PEREMPUAN SEBAPA' && lapis == '1') {
		divId = 'saudarapb';
	} else if (hubungan =='SAUDARA PEREMPUAN SEIBU' && lapis == '1') {
		divId = 'saudarapi';
	} else if (hubungan =='SAUDARA LELAKI SEIBU SEBAPA' && lapis == '1') {
		divId = 'saudaralk';
	} else if (hubungan =='SAUDARA LELAKI SEBAPA' && lapis == '1') {
		divId = 'saudaralb';
	} else if (hubungan =='SAUDARA LELAKI SEIBU' && lapis == '1') {
		divId = 'saudarali';
	} else if (hubungan.startsWith('ANAK LELAKI KEPADA') && lapis == '2') {
		divId = 'cucul';
	} else if (hubungan.startsWith("ISTERI KEPADA") && lapis == '2'){
		divId = "isterial";
	} else {
		divId = "nohubungan";
	}
	return divId;
}

Array.prototype.remove= function(){
    var what, a= arguments, L= a.length, ax;
    while(L && this.length){
        what= a[--L];
        while((ax= this.indexOf(what))!= -1){
            this.splice(ax, 1);
        }
    }
    return this;
}

function setRelation(from, to) {
	var horizontal = ['saudarapk', 'saudarapb', 'saudaralk', 'saudaralb', 'saudarali', 'bsaudarak', 'bsaudarab', 'absaudarak', 'absaudarab'];
	if (jQuery('#bsaudarak').length) {
		horizontal.remove('absaudarak');
	}
	if (jQuery('#bsaudarab').length) {
		horizontal.remove('absaudarab');
	}
	if (horizontal.indexOf(from) === -1) {
		 jsPlumb.connect({
		        source: from,
		        target: to,
		        connector: ["Straight"],
		        endpoint: ["Dot", { radius: 5 }],
		        paintStyle: { stroke: "blue", strokeWidth: 2 },
		        //hoverPaintStyle: { stroke: "red", strokeWidth: 3 },
		        anchor: ["Top", "Bottom"]
		 });
	} else {
		  jsPlumb.connect({
		        source: from,
		        target: to,
		        connector: ["Straight"],
		        endpoint: ["Dot", { radius: 5 }],
		        paintStyle: { stroke: "blue", strokeWidth: 2 },
		        //hoverPaintStyle: { stroke: "red", strokeWidth: 3 },
		        anchor: ["Left", "Right"]
		  });
	}  
}

function resize() {
	window.moveTo(0,0);
	window.resizeTo(screen.width,screen.height);
}


function seterusnya(){    	
	document.${formName}.action.value = "next";
	document.${formName}.submit();
}
function sebelumnya(){    	
	document.${formName}.action.value = "previous";
	document.${formName}.submit();
}
function cetak(idSimati,idPermohonan,bhgnBaitulMal,idPermohonanSimati) {
	
    var url = "../../servlet/ekptg.report.ppk.SuratAkuanFaraid?idSimati="+idSimati+"&idPermohonan="+idPermohonan+"&bhgnBaitulMal="+bhgnBaitulMal+"&idPermohonanSimati="+idPermohonanSimati;
    var hWnd = window.open(url,'Cetak','width=700,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>