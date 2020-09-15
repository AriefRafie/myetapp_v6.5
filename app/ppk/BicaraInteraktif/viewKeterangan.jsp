
<table class="tableEditor classFade" width="100%"  >
<tr><td colspan="2">

<p>
  <input type="hidden" name="jeniskp" value="$jeniskp">
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionPerintah" type="hidden" id="actionPerintah" value="$actionPerintah"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="selectedTabLower" type="hidden" id="selectedTabLower" value="$selectedTabLower"/>
  <input name="hitButt" type="hidden" id="hitButt" value="$hitButt"/>
  <input name="hitButt2" type="hidden" id="hitButt2" value="$hitButt2"/>
  <input name="anchor" type="hidden" id="anchor"/>
  <input name="jenisPerintah" type="hidden" id="jenisPerintah" value="$jenisPerintah"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPermohonanSimati" type="hidden" id="idPermohonanSimati" value="$idPermohonanSimati"/>
  <input name="idPerintah" type="hidden" id="idPerintah" value="$idPerintah"/>
  <input name="idPerbicaraan" type="hidden" id="idPerbicaraan" value="$idPerbicaraan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="flagAdaHTA" type="hidden" id="flagAdaHTA" value="$flagAdaHTA"/>
  <input name="flagAdaHTATH" type="hidden" id="flagAdaHTATH" value="$flagAdaHTATH"/>
  <input name="flagAdaHA" type="hidden" id="flagAdaHA" value="$flagAdaHA"/>
  <input name="flagAdaHTAPT" type="hidden" id="flagAdaHTAPT" value="$flagAdaHTAPT"/>
  <input name="flagAdaHAPT" type="hidden" id="flagAdaHAPT" value="$flagAdaHAPT"/>
  <input name="flagAdaHTAPKT" type="hidden" id="flagAdaHTAPKT" value="$flagAdaHTAPKT"/>
  <input name="flagAdaHAPKT" type="hidden" id="flagAdaHAPKT" value="$flagAdaHAPKT"/>
  <input name="flagAdaHTAPL" type="hidden" id="flagAdaHTAPL" value="$flagAdaHTAPL"/>
  <input name="flagAdaHAPL" type="hidden" id="flagAdaHAPL" value="$flagAdaHAPL"/>
  <input name="flagAdaHTAPF" type="hidden" id="flagAdaHTAPF" value="$flagAdaHTAPF"/>
  <input name="flagAdaHAPF" type="hidden" id="flagAdaHAPF" value="$flagAdaHAPF"/>
  <input name="flagSelesaiHTA" type="hidden" id="flagSelesaiHTA" value="$flagSelesaiHTA"/>
  <input name="flagSelesaiHA" type="hidden" id="flagSelesaiHA" value="$flagSelesaiHA"/>
  <input name="flagSelesaiHAARB" type="hidden" id="flagSelesaiHAARB" value="$flagSelesaiHAARB"/>
  
  <input name="idHTA" type="hidden" id="idHTA" value="$idHTA"/>
  <input name="idHA" type="hidden" id="idHA" value="$idHA"/>
  <input name="idJenisHA" type="hidden" id="idJenisHA" value="$idJenisHA"/>
  <input name="printOption" type="hidden" id="printOption"/>
  <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
  <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
   <input name="usid" type="hidden" id="usid" value="$usid"/>
   
</p>

      
        <h4>Keterangan Oleh : 
        
       			<!--
        
        	   #if($pr.UMUR_INT != 0 && $pr.UMUR_INT < 18)
               
               
               #if($pr.PENJAGA != "") 
               $pr.PENJAGA 
               <br />
               adalah <b>PENJAGA</b> kepada
               <br />               
               #else
               <i><span class="red">Perhatian </span>: Sila lantik penjaga pada skrin 
                <a href="javascript:papar_header('$mainID.ID_PERMOHONAN','18','','$mainID.ID_PERMOHONANSIMATI','$mainID.TARIKH_MOHON','','$mainID.SEKSYEN','$mainID.ID_SIMATI')" > <span class="blue"><u>'Notis Perbicaraan'</u></span></a>
               </i> 
               <br />
               #end
               
               <span class="blue">$pr.NAMA</span> 
               
               #else
               $pr.NAMA 
               #end
               -->
              
               #if($ID_OBPERMOHONANMINOR != "")
               	#if($LISTPENJAGA != "")
                 <span class="blue">
                	$LISTPENJAGA 
                    </span>
                    
                    sebagai <b>PENJAGA</b> kepada  <span class="blue">
               $NAMA   
        	   </span>
                #else
                	<i><span class="red">Perhatian </span>: Sila lantik penjaga pada skrin 
                        <a href="javascript:papar_header('$mainID.ID_PERMOHONAN','18','','$mainID.ID_PERMOHONANSIMATI','$mainID.TARIKH_MOHON','','$mainID.SEKSYEN','$mainID.ID_SIMATI')" > <span class="blue"><u>'Notis Perbicaraan'</u></span></a>
                       </i> 
                       <br />
                #end    
                          
               #else
               <span class="blue">
               $NAMA        
        	   </span>
               #end
               </h4>
 <form autocomplete="on" action="/app/ppk/BicaraInteraktif/viewKeterangan.jsp"> <!-- arief add bagi tujuan autocomplete bila Pegawai masukkan nama Waris / OB di Keterangan diambil-->
<div class="viewKeterangan" class="autocomplete" > <!-- arief add class="autocomplete"  -->
<textarea id="KETERANGAN_$ID_OBPERMOHONAN" name="KETERANGAN_$ID_OBPERMOHONAN"  placeholder="Masukkan Keterangan..." style="width:100%;" spellcheck="false" >$KETERANGAN</textarea>	
<div id="dummyDivResetupKETERANGAN_$ID_OBPERMOHONAN" style="display:none;" >$KETERANGAN</div>     
<div id="timer_KETERANGAN_$ID_OBPERMOHONAN" align="right" ></div>
		<script type="text/javascript">
		
		
		$jquery(document).ready(function () {
			$jquery('#KETERANGAN_$ID_OBPERMOHONAN').wysihtml5({ 
				"font-styles": true, // Font styling, e.g. h1, h2, etc.
				"emphasis": true, // Italics, bold, etc.
				"lists": true, // (Un)ordered lists, e.g. Bullets, Numbers.
				"html": false, // Button which allows you to edit the generated HTML.
				"link": false, // Button to insert a link.
				"image": false, // Button to insert an image.
				"color": true, // Button to change color of font
				"blockquote": true, // Blockquote	
				"size": "small"	
				
				
			});
			
		});	
		//arief add untuk autocomplete nama waris dan nama OB
		function autocomplete(inp, arr) {
		/*the autocomplete function takes two arguments,
		the text field element and an array of possible autocompleted values:*/
			var currentFocus;
			/*execute a function when someone writes in the text field:*/
			inp.addEventListener("input", function(e) {
			var a, b, i, val = this.value;
				/*close any already open lists of autocompleted values*/
			    closeAllLists();
			    if (!val){
			    	return false;
			    }
			    currentFocus = -1;
			    /*create a DIV element that will contain the items (values):*/
			    a = document.createElement("DIV");
			    a.setAttribute("id", this.id + "autocomplete-list");
			    a.setAttribute("class", "autocomplete-items");
			    /*append the DIV element as a child of the autocomplete container:*/
			    this.parentNode.appendChild(a);
			    /*for each item in the array...*/
			    for (i = 0; i < arr.length; i++) {
			    /*check if the item starts with the same letters as the text field value:*/
			    	if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
			        /*create a DIV element for each matching element:*/
			        b = document.createElement("DIV");
			        /*make the matching letters bold:*/
			        b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
			        b.innerHTML += arr[i].substr(val.length);
			        /*insert a input field that will hold the current array item's value:*/
			        b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
			        /*execute a function when someone clicks on the item value (DIV element):*/
			        b.addEventListener("click", function(e) {
			        /*insert the value for the autocomplete text field:*/
			        inp.value = this.getElementsByTagName("input")[0].value;
			        /*close the list of autocompleted values,(or any other open lists of autocompleted values:*/
			        closeAllLists();
			      	});
			       a.appendChild(b);
			       }
			      }
			  });
		/*execute a function presses a key on the keyboard:*/
		inp.addEventListener("keydown", function(e) {
			var x = document.getElementById(this.id + "autocomplete-list");
			if (x) x = x.getElementsByTagName("div");
			if (e.keyCode == 40) {
				/*If the arrow DOWN key is pressed,increase the currentFocus variable:*/
			    currentFocus++;
			    /*and and make the current item more visible:*/
			    addActive(x);
			} else if (e.keyCode == 38) { //up
				/*If the arrow UP key is pressed,decrease the currentFocus variable:*/
			    currentFocus--;
			    /*and and make the current item more visible:*/
			    addActive(x);
			} else if (e.keyCode == 13) {
			    /*If the ENTER key is pressed, prevent the form from being submitted,*/
			    e.preventDefault();
			    if (currentFocus > -1) {
			    /*and simulate a click on the "active" item:*/
			    	if (x) x[currentFocus].click();
			    }
			 }
		});
		function addActive(x) {
			/*a function to classify an item as "active":*/
			if (!x)
				return false;
			/*start by removing the "active" class on all items:*/
			removeActive(x);
			if (currentFocus >= x.length) 
				currentFocus = 0;
			if (currentFocus < 0) 
				currentFocus = (x.length - 1);
			/*add class "autocomplete-active":*/
			 x[currentFocus].classList.add("autocomplete-active");
		}
		function removeActive(x) {
			/*a function to remove the "active" class from all autocomplete items:*/
			for (var i = 0; i < x.length; i++) {
				x[i].classList.remove("autocomplete-active");
			}
		}
		function closeAllLists(elmnt) {
			/*close all autocomplete lists in the document, except the one passed as an argument:*/
			var x = document.getElementsByClassName("autocomplete-items");
			for (var i = 0; i < x.length; i++) {
				if (elmnt != x[i] && elmnt != inp) {
					x[i].parentNode.removeChild(x[i]);
				}
			}
		}
		/*execute a function when someone clicks in the document:*/
		document.addEventListener("click", function (e) {
			closeAllLists(e.target);
		});
		} 
		//var namaWaris = new Array ("SELECT NAMA_OB FROM TBLPPKOB WHERE ID_PERMOHONANSIMATI ="+ ID_PERMOHONANSIMATI); //arief add 26/6/2020
		//ArrayList<HashMap<String, String>> assArray= new ArrayList<HashMap<String, String>>(); //arief add 29/6/2020
		
		
		
		var resizeIframe = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_KETERANGAN_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","FOCUS","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "none";
			document.getElementById('infobuttonKeterangan$ID_OBPERMOHONAN').style.display = "";
			$jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";			
		}
		
		var resizeIframeBlur = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_KETERANGAN_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","BLUR","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "";
			document.getElementById('infobuttonKeterangan$ID_OBPERMOHONAN').style.display = "none";
			$jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";										
		}		
		
		var resizeIframeKeyUp = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_KETERANGAN_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","KEYUP","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "none";			
			document.getElementById('infobuttonKeterangan$ID_OBPERMOHONAN').style.display = "";
			var iframeheight = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height;
			var scrollheight = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight - 1;
			if(parseInt(scrollheight) > parseInt(iframeheight))
			{
				$jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";
			}								
		}
		
		var wordCount = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");			
		}
		
		$jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.on("load", function() {
		  fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";	
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("keyup", resizeIframeKeyUp , false);
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("keydown", resizeIframeKeyUp , false);
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("blur", resizeIframeBlur, false);
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("focus", resizeIframe, false);		  	 
		});	
		
		reAssignFieldEditorContent('KETERANGAN_$ID_OBPERMOHONAN'); 
		
		</script>	
		<!-- arief add autocomplete OPEN-->
		<script>
		autocomplete(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"), namaWaris, namaOB);
		</script>
		<!-- arief add autocomplete CLOSE-->
		<!-- arief try add Nama OB di Keterangan OPEN -->
		<ul style="" class="wysihtml5-toolbar">
		<li><div class="btn-group_OB">
		<a class="btn btn-small" data-wysihtml5-command="function()" title="CTRL+B" tabindex="-1" href="javascript:;" unselectable="on">Khadijah Binti Yeop</a>
		<a class="btn btn-small" data-wysihtml5-command="Khalil Bin Yeop" title="CTRL+B" tabindex="-1" href="javascript:;" unselectable="on">Khalil Bin Yeop</a>
		<a class="btn btn-small" data-wysihtml5-command="Saadah Binti Aziz" title="CTRL+I" tabindex="-1" href="javascript:;" unselectable="on">Saadah Binti Aziz</a>
		<a class="btn btn-small" data-wysihtml5-command="Salasiah Binti Yeop" title="CTRL+U" tabindex="-1" href="javascript:;" unselectable="on">Salasiah Binti Yeop</a>
		<a class="btn btn-small" data-wysihtml5-command="Mohd Hafez Bin Idris" title="CTRL+U" tabindex="-1" href="javascript:;" unselectable="on">Mohd Hafez Bin Idris</a>
		<a class="btn btn-small" data-wysihtml5-command="Mohamad Harif Bin Idrisp" title="CTRL+U" tabindex="-1" href="javascript:;" unselectable="on">Mohamad Harif Bin Idris</a>
		<a class="btn btn-small" data-wysihtml5-command="Noorhafizah Binti Idris" title="CTRL+U" tabindex="-1" href="javascript:;" unselectable="on">Noorhafizah Binti Idris</a>
		</div>
		</li>
		</ul>
		<script>
		$(function(){
			$('input:button[Khadijah Binti Yeop]').click(function() {
        		var getInpVal = $('#btn btn-small').val();
       			$('iframe[name=wysihtml5-sandbox]').contents().find('#KETERANGAN_$ID_OBPERMOHONAN').val(getInpVal);
    		});
		}
		</script>
		<!-- arief try add Nama OB di Keterangan CLOSE -->
		
          <div id="infobuttonKeterangan$ID_OBPERMOHONAN" style="display:none"><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keterangan'</font> akan dipaparkan.</i></div>
</td>
</tr>  
<tr >
<td align="left"  >
</td>
<td align="right">
<div id="wordKETERANGAN_$ID_OBPERMOHONAN"></div>
</td>
</tr>

<tr>
<td align="right" colspan="2" style="border-bottom: 1px  dotted #000;" >

</td>
</tr> 

<tr><td colspan="2">        
        <h4>Nota Pegawai : </h4>
<textarea id="NOTA_PEGAWAI_$ID_OBPERMOHONAN" name="NOTA_PEGAWAI_$ID_OBPERMOHONAN"  placeholder="Masukkan Keterangan..." style="width:100%;" spellcheck="false" >$NOTA_PEGAWAI</textarea>
<div id="dummyDivResetupNOTA_PEGAWAI_$ID_OBPERMOHONAN" style="display:none;" >$NOTA_PEGAWAI</div>    
<div id="timer_NOTA_PEGAWAI_$ID_OBPERMOHONAN" align="right" ></div>
        
		<script type="text/javascript">
		
		$jquery(document).ready(function () {
			$jquery('#NOTA_PEGAWAI_$ID_OBPERMOHONAN').wysihtml5({ 
				"font-styles": true, // Font styling, e.g. h1, h2, etc.
				"emphasis": true, // Italics, bold, etc.
				"lists": true, // (Un)ordered lists, e.g. Bullets, Numbers.
				"html": false, // Button which allows you to edit the generated HTML.
				"link": false, // Button to insert a link.
				"image": false, // Button to insert an image.
				"color": true, // Button to change color of font
				"blockquote": true, // Blockquote	
				"size": "small"				  
			});
			
		});	
		
		var resizeIframe = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");								
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "none";
			document.getElementById('infobuttonNotaPegawai$ID_OBPERMOHONAN').style.display = "";
			$jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";			
		}
		
		var resizeIframeBlur = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_NOTA_PEGAWAI_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","BLUR","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "";
			document.getElementById('infobuttonNotaPegawai$ID_OBPERMOHONAN').style.display = "none";
			$jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";										
		}		
		
		var resizeIframeKeyUp = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_NOTA_PEGAWAI_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","KEYUP","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "none";
			document.getElementById('infobuttonNotaPegawai$ID_OBPERMOHONAN').style.display = "";			
			var iframeheight = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height;
			var scrollheight = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight - 1;
			if(parseInt(scrollheight) > parseInt(iframeheight))
			{
				$jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";
			}								
		}
		
		var wordCount = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");			
		}
		
		$jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.on("load", function() {
		  	
		  fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";	
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("keyup", resizeIframeKeyUp , false);
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("keydown", resizeIframeKeyUp , false);
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("blur", resizeIframeBlur, false);
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("focus", resizeIframe, false);		  	 
		});	
		
		reAssignFieldEditorContent('NOTA_PEGAWAI_$ID_OBPERMOHONAN'); 
		
		</script>
        
        
        
        <div id="infobuttonNotaPegawai$ID_OBPERMOHONAN" style="display:none"><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keterangan'</font> akan dipaparkan.</i></div>
</td></tr>

<tr  >
<td align="left" >

</td>
<td align="right">
<div id="wordNOTA_PEGAWAI_$ID_OBPERMOHONAN"></div>
</td>
</tr>

<tr id="buttonKeterangan$ID_OBPERMOHONAN" >
<td align="left" colspan="2" style="border-top: 1px dotted #000;" >

<input type="button" id="cmdSimpanKeterangan$ID_OBPERMOHONAN" name="cmdSimpanKeterangan$ID_OBPERMOHONAN" value="Simpan Keterangan" onClick="doDivAjaxCall$formname('tdViewKeterangan$ID_OBPERMOHONAN','simpanKeterangan','ID_OBPERMOHONAN=$ID_OBPERMOHONAN&ID_OBPERMOHONAN_ASAL=$ID_OBPERMOHONAN_ASAL&NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanKeterangan$ID_OBPERMOHONAN" name="cmdSimpanKeterangan$ID_OBPERMOHONAN" value="Tutup" onClick="doDivAjaxCall$formname('tdViewKeterangan$ID_OBPERMOHONAN','tutupKeterangan','ID_OBPERMOHONAN=$ID_OBPERMOHONAN&ID_OBPERMOHONAN_ASAL=$ID_OBPERMOHONAN_ASAL&NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanReset$ID_OBPERMOHONAN" name="cmdSimpanReset$ID_OBPERMOHONAN" value="Reset" onClick="doDivAjaxCall$formname('tdViewKeterangan$ID_OBPERMOHONAN','showKeterangan','ID_OBPERMOHONAN=$ID_OBPERMOHONAN&ID_OBPERMOHONAN_ASAL=$ID_OBPERMOHONAN_ASAL&NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanKosong$ID_OBPERMOHONAN" name="cmdSimpanKosong$ID_OBPERMOHONAN" value="Kosongkan" onClick="$jquery('#KETERANGAN_$ID_OBPERMOHONAN').data('wysihtml5').editor.setValue('');$jquery('#NOTA_PEGAWAI_$ID_OBPERMOHONAN').data('wysihtml5').editor.setValue('');$jquery('#KETERANGAN_$ID_OBPERMOHONAN').data('wysihtml5').editor.focus();fckeditor_word_zero(document.getElementById('KETERANGAN_$ID_OBPERMOHONAN'),'wordKETERANGAN_$ID_OBPERMOHONAN');fckeditor_word_zero(document.getElementById('NOTA_PEGAWAI_$ID_OBPERMOHONAN'),'wordNOTA_PEGAWAI_$ID_OBPERMOHONAN');" >
</td>
</tr>

</table>
</div>
</form>

#if($div != "")
 <script>
 $jquery(document).ready(function () {
     //alert('x');
     //divToTop("view_keputusan");
     $jquery('#'+'$div').scrollView();
     //alert('x2');
 });	 
 </script>    
#end

<script>
var flagDisable = document.getElementById("flagDisable").value;
if(flagDisable == "Y")
{
	/*
	disableByClass("wysihtml5-toolbar");
	disableByClass("wysihtml5-sandbox");
	disableInput("buttonKeterangan$ID_OBPERMOHONAN");
	*/
	
	
	hideByClass("wysihtml5-toolbar");
	disableByClass("wysihtml5-sandbox");
	disableInput("buttonKeterangan$ID_OBPERMOHONAN");	
}
</script>

