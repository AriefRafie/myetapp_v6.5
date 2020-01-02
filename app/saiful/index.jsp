<style type="text/css">
        @import "../dojo/dijit/themes/tundra/tundra.css";
        @import "../dojo/dojo/resources/dojo.css"
</style>

<script type="text/javascript" src="../dojo/dojo/dojo.js" djConfig="parseOnLoad: true">
</script>
<script type="text/javascript">
       // Load Dojo's code relating to the Button widget
       dojo.require("dijit.form.Button");
       dojo.require("dijit.form.ValidationTextBox");
       dojo.require("dijit.form.Form");
       dojo.require("dijit.layout.TabContainer");
       dojo.require("dijit.layout.ContentPane");

       function sendForm(){

           
           var button = dijit.byId("saveButton");
         
           var objForm = dijit.byId("dojoForm");
         
           dojo.connect(button, "onClick", function(event) {
        	   event.preventDefault();
               event.stopPropagation();
             
               if (objForm.isValid()) {
                  
	               var xhrArgs = {
	                      
	                       url :'/${session.getAttribute("_portal_appname")}/y/saiful.test.DojoTest',
	                       load: function(data) {
	                           dojo.byId("response").innerHTML = "Form posted.";
	                       },
	                       error: function(error) {
	                           //We'll 404 in the demo, but that's okay.  We don't have a 'postIt' service on the
	                           //docs server.
	                           dojo.byId("response").innerHTML = "ERROR WHILE SUBMITTING FORM.";
	                       },
	                       form: 'dojoForm',
	              	};
	               dojo.byId("response").innerHTML = "Form being sent...";
	               var deferred = dojo.xhrGet(xhrArgs);
               }

           });
       }

       dojo.addOnLoad(sendForm);
       
</script>
    <body class="tundra">
    
 <div id="response">
 </div>
<div dojoType="dijit.form.Form" id="dojoForm" jsId="myForm">
<table>
	<tr>
		<td>
			<label for="name" action="postit">
            	Name
            </label>
        
		</td>
		<td>
			<input type="text"  name="nama" value="$!nama" id="nama"  dojoType="dijit.form.ValidationTextBox" required="true"
			 invalidMessage="SILA ISI NAMA" promptMessage="Sila Isi Nama"
			>
		</td>
		<td>
			<label for="phone">
			    Phone number, no spaces
			</label>
		
		</td>
		<td>
			<input type="text" name="phone" id="phone" value="" dojoType="dijit.form.ValidationTextBox"
			regExp="[\w]+" required="true" invalidMessage="Invalid Non-Space Text." promptMessage="Sila No Telefon">
			
		
		</td>
	
	</tr>
	<tr>
	
		<td>
			<button dojoType="dijit.form.Button" id="saveButton" type="button" onclick="sendForm">SIMPAN
			</button>
		</td>
		
	</tr>

	<tr>
		<td>
			 <div dojoType="dijit.layout.TabContainer" style="width: 100%; height: 100%;">
			 
			 		<div dojoType="dijit.layout.ContentPane" title="My first tab" selected="true">
                    Lorem ipsum and all around...
	                </div>
	                <div dojoType="dijit.layout.ContentPane" title="My second tab">
	                    Lorem ipsum and all around - second...
	                </div>
	                <div dojoType="dijit.layout.ContentPane" title="My last tab" closable="true">
	                    Lorem ipsum and all around - last...
	                </div>
			 		
			 		
			 </div>
		</td>
	</tr>

</table>
</div>

</body>