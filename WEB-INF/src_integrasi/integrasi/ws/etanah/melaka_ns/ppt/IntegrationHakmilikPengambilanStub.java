
/**
 * IntegrationHakmilikPengambilanStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
        package integrasi.ws.etanah.melaka_ns.ppt;

        

        /*
        *  IntegrationHakmilikPengambilanStub java implementation
        */

        
        public class IntegrationHakmilikPengambilanStub extends org.apache.axis2.client.Stub
        {
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private static int counter = 0;

        private static synchronized java.lang.String getUniqueSuffix(){
            // reset the counter if it is greater than 99999
            if (counter > 99999){
                counter = 0;
            }
            counter = counter + 1; 
            return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
        }

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("IntegrationHakmilikPengambilan" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[6];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.etanah", "insertMaklumatWarta_byObject"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.etanah", "insertHakmilikList_byObject"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[1]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.etanah", "insertDerafMMK_byObject"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[2]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.etanah", "insertMaklumatPengambilan_byObject"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[3]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.etanah", "insertDerafMMKTajuk_byObject"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[4]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.etanah", "insertDokumen_byObject"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[5]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         
              faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertMaklumatWarta_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertMaklumatWarta_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertMaklumatWarta_byObject"),"etanah.ws.IntegrationHakmilikPengambilanStub$IntegrationHakmilikPengambilanException");
           
              faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertHakmilikList_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertHakmilikList_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertHakmilikList_byObject"),"etanah.ws.IntegrationHakmilikPengambilanStub$IntegrationHakmilikPengambilanException");
           
              faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDerafMMK_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDerafMMK_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDerafMMK_byObject"),"etanah.ws.IntegrationHakmilikPengambilanStub$IntegrationHakmilikPengambilanException");
           
              faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertMaklumatPengambilan_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertMaklumatPengambilan_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertMaklumatPengambilan_byObject"),"etanah.ws.IntegrationHakmilikPengambilanStub$IntegrationHakmilikPengambilanException");
           
              faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDerafMMKTajuk_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDerafMMKTajuk_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDerafMMKTajuk_byObject"),"etanah.ws.IntegrationHakmilikPengambilanStub$IntegrationHakmilikPengambilanException");
           
              faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDokumen_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDokumen_byObject"),"etanah.ws.IntegrationHakmilikPengambilanExceptionException");
              faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"), "insertDokumen_byObject"),"etanah.ws.IntegrationHakmilikPengambilanStub$IntegrationHakmilikPengambilanException");
           


    }

    /**
      *Constructor that takes in a configContext
      */

    public IntegrationHakmilikPengambilanStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public IntegrationHakmilikPengambilanStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
    
    }

    /**
     * Default Constructor
     */
    public IntegrationHakmilikPengambilanStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://localhost:8081/Int_eTanah_Server/services/IntegrationHakmilikPengambilan.IntegrationHakmilikPengambilanHttpSoap11Endpoint/" );
                
    }

    /**
     * Default Constructor
     */
    public IntegrationHakmilikPengambilanStub() throws org.apache.axis2.AxisFault {
        
                    this("http://localhost:8081/Int_eTanah_Server/services/IntegrationHakmilikPengambilan.IntegrationHakmilikPengambilanHttpSoap11Endpoint/" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public IntegrationHakmilikPengambilanStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * 
                     * @see etanah.ws.IntegrationHakmilikPengambilan#insertMaklumatWarta_byObject
                     * @param insertMaklumatWarta_byObject6
                    
                     * @throws integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException : 
                     */

                    

                            public  void insertMaklumatWarta_byObject(

                            integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatWarta_byObject insertMaklumatWarta_byObject6)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("urn:insertMaklumatWarta_byObject");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertMaklumatWarta_byObject6,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertMaklumatWarta_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertMaklumatWarta_byObject"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
                return;
            
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertMaklumatWarta_byObject"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertMaklumatWarta_byObject"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertMaklumatWarta_byObject"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException){
                          throw (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see etanah.ws.IntegrationHakmilikPengambilan#startinsertMaklumatWarta_byObject
                    * @param insertMaklumatWarta_byObject6
                
                */
                public  void startinsertMaklumatWarta_byObject(

                 integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatWarta_byObject insertMaklumatWarta_byObject6,

                  final integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("urn:insertMaklumatWarta_byObject");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertMaklumatWarta_byObject6,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertMaklumatWarta_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertMaklumatWarta_byObject"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                            // Nothing to pass as the callback!!!
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see etanah.ws.IntegrationHakmilikPengambilan#insertHakmilikList_byObject
                     * @param insertHakmilikList_byObject8
                    
                     * @throws integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException : 
                     */

                    

                            public  void insertHakmilikList_byObject(

                            integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertHakmilikList_byObject insertHakmilikList_byObject8)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
              _operationClient.getOptions().setAction("urn:insertHakmilikList_byObject");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertHakmilikList_byObject8,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertHakmilikList_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertHakmilikList_byObject"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
                return;
            
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertHakmilikList_byObject"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertHakmilikList_byObject"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertHakmilikList_byObject"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException){
                          throw (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see etanah.ws.IntegrationHakmilikPengambilan#startinsertHakmilikList_byObject
                    * @param insertHakmilikList_byObject8
                
                */
                public  void startinsertHakmilikList_byObject(

                 integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertHakmilikList_byObject insertHakmilikList_byObject8,

                  final integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
             _operationClient.getOptions().setAction("urn:insertHakmilikList_byObject");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertHakmilikList_byObject8,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertHakmilikList_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertHakmilikList_byObject"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                            // Nothing to pass as the callback!!!
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[1].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[1].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see etanah.ws.IntegrationHakmilikPengambilan#insertDerafMMK_byObject
                     * @param insertDerafMMK_byObject10
                    
                     * @throws integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException : 
                     */

                    

                            public  void insertDerafMMK_byObject(

                            integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMK_byObject insertDerafMMK_byObject10)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
              _operationClient.getOptions().setAction("urn:insertDerafMMK_byObject");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertDerafMMK_byObject10,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDerafMMK_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDerafMMK_byObject"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
                return;
            
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDerafMMK_byObject"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDerafMMK_byObject"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDerafMMK_byObject"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException){
                          throw (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see etanah.ws.IntegrationHakmilikPengambilan#startinsertDerafMMK_byObject
                    * @param insertDerafMMK_byObject10
                
                */
                public  void startinsertDerafMMK_byObject(

                 integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMK_byObject insertDerafMMK_byObject10,

                  final integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
             _operationClient.getOptions().setAction("urn:insertDerafMMK_byObject");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertDerafMMK_byObject10,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDerafMMK_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDerafMMK_byObject"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                            // Nothing to pass as the callback!!!
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[2].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[2].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see etanah.ws.IntegrationHakmilikPengambilan#insertMaklumatPengambilan_byObject
                     * @param insertMaklumatPengambilan_byObject12
                    
                     * @throws integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException : 
                     */

                    

                            public  void insertMaklumatPengambilan_byObject(

                            integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatPengambilan_byObject insertMaklumatPengambilan_byObject12)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[3].getName());
              _operationClient.getOptions().setAction("urn:insertMaklumatPengambilan_byObject");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertMaklumatPengambilan_byObject12,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertMaklumatPengambilan_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertMaklumatPengambilan_byObject"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
                return;
            
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertMaklumatPengambilan_byObject"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertMaklumatPengambilan_byObject"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertMaklumatPengambilan_byObject"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException){
                          throw (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see etanah.ws.IntegrationHakmilikPengambilan#startinsertMaklumatPengambilan_byObject
                    * @param insertMaklumatPengambilan_byObject12
                
                */
                public  void startinsertMaklumatPengambilan_byObject(

                 integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatPengambilan_byObject insertMaklumatPengambilan_byObject12,

                  final integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[3].getName());
             _operationClient.getOptions().setAction("urn:insertMaklumatPengambilan_byObject");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertMaklumatPengambilan_byObject12,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertMaklumatPengambilan_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertMaklumatPengambilan_byObject"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                            // Nothing to pass as the callback!!!
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[3].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[3].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see etanah.ws.IntegrationHakmilikPengambilan#insertDerafMMKTajuk_byObject
                     * @param insertDerafMMKTajuk_byObject14
                    
                     * @throws integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException : 
                     */

                    

                            public  void insertDerafMMKTajuk_byObject(

                            integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMKTajuk_byObject insertDerafMMKTajuk_byObject14)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[4].getName());
              _operationClient.getOptions().setAction("urn:insertDerafMMKTajuk_byObject");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertDerafMMKTajuk_byObject14,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDerafMMKTajuk_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDerafMMKTajuk_byObject"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
                return;
            
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDerafMMKTajuk_byObject"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDerafMMKTajuk_byObject"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDerafMMKTajuk_byObject"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException){
                          throw (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see etanah.ws.IntegrationHakmilikPengambilan#startinsertDerafMMKTajuk_byObject
                    * @param insertDerafMMKTajuk_byObject14
                
                */
                public  void startinsertDerafMMKTajuk_byObject(

                 integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMKTajuk_byObject insertDerafMMKTajuk_byObject14,

                  final integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[4].getName());
             _operationClient.getOptions().setAction("urn:insertDerafMMKTajuk_byObject");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertDerafMMKTajuk_byObject14,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDerafMMKTajuk_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDerafMMKTajuk_byObject"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                            // Nothing to pass as the callback!!!
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[4].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[4].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see etanah.ws.IntegrationHakmilikPengambilan#insertDokumen_byObject
                     * @param insertDokumen_byObject16
                    
                     * @throws integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException : 
                     */

                    

                            public  void insertDokumen_byObject(

                            integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDokumen_byObject insertDokumen_byObject16)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[5].getName());
              _operationClient.getOptions().setAction("urn:insertDokumen_byObject");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertDokumen_byObject16,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDokumen_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDokumen_byObject"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
                return;
            
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDokumen_byObject"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDokumen_byObject"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"insertDokumen_byObject"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException){
                          throw (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanExceptionException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see etanah.ws.IntegrationHakmilikPengambilan#startinsertDokumen_byObject
                    * @param insertDokumen_byObject16
                
                */
                public  void startinsertDokumen_byObject(

                 integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDokumen_byObject insertDokumen_byObject16,

                  final integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[5].getName());
             _operationClient.getOptions().setAction("urn:insertDokumen_byObject");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    insertDokumen_byObject16,
                                                    optimizeContent(new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDokumen_byObject")), new javax.xml.namespace.QName("http://ws.etanah",
                                                    "insertDokumen_byObject"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                            // Nothing to pass as the callback!!!
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[5].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[5].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                


       /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
       private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
       return returnMap;
    }

    
    
    private javax.xml.namespace.QName[] opNameArray = null;
    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }
     //http://localhost:8081/Int_eTanah_Server/services/IntegrationHakmilikPengambilan.IntegrationHakmilikPengambilanHttpSoap11Endpoint/
        public static class InsertMaklumatWarta_byObject
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://ws.etanah",
                "insertMaklumatWarta_byObject",
                "ns2");

            

                        /**
                        * field for Tblintpptwarta
                        * This was an Array!
                        */

                        
                                    protected Tblintpptwarta[] localTblintpptwarta ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTblintpptwartaTracker = false ;

                           public boolean isTblintpptwartaSpecified(){
                               return localTblintpptwartaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return Tblintpptwarta[]
                           */
                           public  Tblintpptwarta[] getTblintpptwarta(){
                               return localTblintpptwarta;
                           }

                           
                        


                               
                              /**
                               * validate the array for Tblintpptwarta
                               */
                              protected void validateTblintpptwarta(Tblintpptwarta[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Tblintpptwarta
                              */
                              public void setTblintpptwarta(Tblintpptwarta[] param){
                              
                                   validateTblintpptwarta(param);

                               localTblintpptwartaTracker = true;
                                      
                                      this.localTblintpptwarta=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param Tblintpptwarta
                             */
                             public void addTblintpptwarta(Tblintpptwarta param){
                                   if (localTblintpptwarta == null){
                                   localTblintpptwarta = new Tblintpptwarta[]{};
                                   }

                            
                                 //update the setting tracker
                                localTblintpptwartaTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTblintpptwarta);
                               list.add(param);
                               this.localTblintpptwarta =
                             (Tblintpptwarta[])list.toArray(
                            new Tblintpptwarta[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":insertMaklumatWarta_byObject",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "insertMaklumatWarta_byObject",
                           xmlWriter);
                   }

               
                   }
                if (localTblintpptwartaTracker){
                                       if (localTblintpptwarta!=null){
                                            for (int i = 0;i < localTblintpptwarta.length;i++){
                                                if (localTblintpptwarta[i] != null){
                                                 localTblintpptwarta[i].serialize(new javax.xml.namespace.QName("http://ws.etanah","tblintpptwarta"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://ws.etanah", "tblintpptwarta", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://ws.etanah", "tblintpptwarta", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localTblintpptwartaTracker){
                             if (localTblintpptwarta!=null) {
                                 for (int i = 0;i < localTblintpptwarta.length;i++){

                                    if (localTblintpptwarta[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptwarta"));
                                         elementList.add(localTblintpptwarta[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptwarta"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptwarta"));
                                        elementList.add(localTblintpptwarta);
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static InsertMaklumatWarta_byObject parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            InsertMaklumatWarta_byObject object =
                new InsertMaklumatWarta_byObject();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"insertMaklumatWarta_byObject".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (InsertMaklumatWarta_byObject)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah","tblintpptwarta").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(Tblintpptwarta.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://ws.etanah","tblintpptwarta").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(Tblintpptwarta.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTblintpptwarta((Tblintpptwarta[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                Tblintpptwarta.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class Tblintppthakmilik
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Tblintppthakmilik
                Namespace URI = http://ws.etanah/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Flag_proses
                        */

                        
                                    protected java.lang.String localFlag_proses ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFlag_prosesTracker = false ;

                           public boolean isFlag_prosesSpecified(){
                               return localFlag_prosesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFlag_proses(){
                               return localFlag_proses;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flag_proses
                               */
                               public void setFlag_proses(java.lang.String param){
                            localFlag_prosesTracker = true;
                                   
                                            this.localFlag_proses=param;
                                    

                               }
                            

                        /**
                        * field for Id_fail_myetapp
                        */

                        
                                    protected java.lang.String localId_fail_myetapp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localId_fail_myetappTracker = false ;

                           public boolean isId_fail_myetappSpecified(){
                               return localId_fail_myetappTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId_fail_myetapp(){
                               return localId_fail_myetapp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id_fail_myetapp
                               */
                               public void setId_fail_myetapp(java.lang.String param){
                            localId_fail_myetappTracker = true;
                                   
                                            this.localId_fail_myetapp=param;
                                    

                               }
                            

                        /**
                        * field for Id_hakmilik
                        */

                        
                                    protected java.lang.String localId_hakmilik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localId_hakmilikTracker = false ;

                           public boolean isId_hakmilikSpecified(){
                               return localId_hakmilikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId_hakmilik(){
                               return localId_hakmilik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id_hakmilik
                               */
                               public void setId_hakmilik(java.lang.String param){
                            localId_hakmilikTracker = true;
                                   
                                            this.localId_hakmilik=param;
                                    

                               }
                            

                        /**
                        * field for Id_hakmilik_myetapp
                        */

                        
                                    protected java.lang.String localId_hakmilik_myetapp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localId_hakmilik_myetappTracker = false ;

                           public boolean isId_hakmilik_myetappSpecified(){
                               return localId_hakmilik_myetappTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId_hakmilik_myetapp(){
                               return localId_hakmilik_myetapp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id_hakmilik_myetapp
                               */
                               public void setId_hakmilik_myetapp(java.lang.String param){
                            localId_hakmilik_myetappTracker = true;
                                   
                                            this.localId_hakmilik_myetapp=param;
                                    

                               }
                            

                        /**
                        * field for Id_penarikanbalik_myetapp
                        */

                        
                                    protected java.lang.String localId_penarikanbalik_myetapp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localId_penarikanbalik_myetappTracker = false ;

                           public boolean isId_penarikanbalik_myetappSpecified(){
                               return localId_penarikanbalik_myetappTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId_penarikanbalik_myetapp(){
                               return localId_penarikanbalik_myetapp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id_penarikanbalik_myetapp
                               */
                               public void setId_penarikanbalik_myetapp(java.lang.String param){
                            localId_penarikanbalik_myetappTracker = true;
                                   
                                            this.localId_penarikanbalik_myetapp=param;
                                    

                               }
                            

                        /**
                        * field for Kod_daerah
                        */

                        
                                    protected java.lang.String localKod_daerah ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_daerahTracker = false ;

                           public boolean isKod_daerahSpecified(){
                               return localKod_daerahTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_daerah(){
                               return localKod_daerah;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_daerah
                               */
                               public void setKod_daerah(java.lang.String param){
                            localKod_daerahTracker = true;
                                   
                                            this.localKod_daerah=param;
                                    

                               }
                            

                        /**
                        * field for Kod_luas_ambil
                        */

                        
                                    protected java.lang.String localKod_luas_ambil ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_luas_ambilTracker = false ;

                           public boolean isKod_luas_ambilSpecified(){
                               return localKod_luas_ambilTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_luas_ambil(){
                               return localKod_luas_ambil;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_luas_ambil
                               */
                               public void setKod_luas_ambil(java.lang.String param){
                            localKod_luas_ambilTracker = true;
                                   
                                            this.localKod_luas_ambil=param;
                                    

                               }
                            

                        /**
                        * field for Kod_luas_asal
                        */

                        
                                    protected java.lang.String localKod_luas_asal ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_luas_asalTracker = false ;

                           public boolean isKod_luas_asalSpecified(){
                               return localKod_luas_asalTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_luas_asal(){
                               return localKod_luas_asal;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_luas_asal
                               */
                               public void setKod_luas_asal(java.lang.String param){
                            localKod_luas_asalTracker = true;
                                   
                                            this.localKod_luas_asal=param;
                                    

                               }
                            

                        /**
                        * field for Kod_luas_pa
                        */

                        
                                    protected java.lang.String localKod_luas_pa ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_luas_paTracker = false ;

                           public boolean isKod_luas_paSpecified(){
                               return localKod_luas_paTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_luas_pa(){
                               return localKod_luas_pa;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_luas_pa
                               */
                               public void setKod_luas_pa(java.lang.String param){
                            localKod_luas_paTracker = true;
                                   
                                            this.localKod_luas_pa=param;
                                    

                               }
                            

                        /**
                        * field for Kod_mukim
                        */

                        
                                    protected java.lang.String localKod_mukim ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_mukimTracker = false ;

                           public boolean isKod_mukimSpecified(){
                               return localKod_mukimTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_mukim(){
                               return localKod_mukim;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_mukim
                               */
                               public void setKod_mukim(java.lang.String param){
                            localKod_mukimTracker = true;
                                   
                                            this.localKod_mukim=param;
                                    

                               }
                            

                        /**
                        * field for Kod_negeri
                        */

                        
                                    protected java.lang.String localKod_negeri ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_negeriTracker = false ;

                           public boolean isKod_negeriSpecified(){
                               return localKod_negeriTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_negeri(){
                               return localKod_negeri;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_negeri
                               */
                               public void setKod_negeri(java.lang.String param){
                            localKod_negeriTracker = true;
                                   
                                            this.localKod_negeri=param;
                                    

                               }
                            

                        /**
                        * field for Kod_unit_hakmilik
                        */

                        
                                    protected java.lang.String localKod_unit_hakmilik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_unit_hakmilikTracker = false ;

                           public boolean isKod_unit_hakmilikSpecified(){
                               return localKod_unit_hakmilikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_unit_hakmilik(){
                               return localKod_unit_hakmilik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_unit_hakmilik
                               */
                               public void setKod_unit_hakmilik(java.lang.String param){
                            localKod_unit_hakmilikTracker = true;
                                   
                                            this.localKod_unit_hakmilik=param;
                                    

                               }
                            

                        /**
                        * field for Luas_ambil
                        */

                        
                                    protected java.lang.String localLuas_ambil ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLuas_ambilTracker = false ;

                           public boolean isLuas_ambilSpecified(){
                               return localLuas_ambilTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLuas_ambil(){
                               return localLuas_ambil;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Luas_ambil
                               */
                               public void setLuas_ambil(java.lang.String param){
                            localLuas_ambilTracker = true;
                                   
                                            this.localLuas_ambil=param;
                                    

                               }
                            

                        /**
                        * field for Luas_asal
                        */

                        
                                    protected java.lang.String localLuas_asal ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLuas_asalTracker = false ;

                           public boolean isLuas_asalSpecified(){
                               return localLuas_asalTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLuas_asal(){
                               return localLuas_asal;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Luas_asal
                               */
                               public void setLuas_asal(java.lang.String param){
                            localLuas_asalTracker = true;
                                   
                                            this.localLuas_asal=param;
                                    

                               }
                            

                        /**
                        * field for Luas_pa
                        */

                        
                                    protected java.lang.String localLuas_pa ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLuas_paTracker = false ;

                           public boolean isLuas_paSpecified(){
                               return localLuas_paTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLuas_pa(){
                               return localLuas_pa;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Luas_pa
                               */
                               public void setLuas_pa(java.lang.String param){
                            localLuas_paTracker = true;
                                   
                                            this.localLuas_pa=param;
                                    

                               }
                            

                        /**
                        * field for No_fail_jkptg
                        */

                        
                                    protected java.lang.String localNo_fail_jkptg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_fail_jkptgTracker = false ;

                           public boolean isNo_fail_jkptgSpecified(){
                               return localNo_fail_jkptgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_fail_jkptg(){
                               return localNo_fail_jkptg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_fail_jkptg
                               */
                               public void setNo_fail_jkptg(java.lang.String param){
                            localNo_fail_jkptgTracker = true;
                                   
                                            this.localNo_fail_jkptg=param;
                                    

                               }
                            

                        /**
                        * field for No_hakmilik
                        */

                        
                                    protected java.lang.String localNo_hakmilik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_hakmilikTracker = false ;

                           public boolean isNo_hakmilikSpecified(){
                               return localNo_hakmilikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_hakmilik(){
                               return localNo_hakmilik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_hakmilik
                               */
                               public void setNo_hakmilik(java.lang.String param){
                            localNo_hakmilikTracker = true;
                                   
                                            this.localNo_hakmilik=param;
                                    

                               }
                            

                        /**
                        * field for No_lot
                        */

                        
                                    protected java.lang.String localNo_lot ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_lotTracker = false ;

                           public boolean isNo_lotSpecified(){
                               return localNo_lotTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_lot(){
                               return localNo_lot;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_lot
                               */
                               public void setNo_lot(java.lang.String param){
                            localNo_lotTracker = true;
                                   
                                            this.localNo_lot=param;
                                    

                               }
                            

                        /**
                        * field for No_lot_baru
                        */

                        
                                    protected java.lang.String localNo_lot_baru ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_lot_baruTracker = false ;

                           public boolean isNo_lot_baruSpecified(){
                               return localNo_lot_baruTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_lot_baru(){
                               return localNo_lot_baru;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_lot_baru
                               */
                               public void setNo_lot_baru(java.lang.String param){
                            localNo_lot_baruTracker = true;
                                   
                                            this.localNo_lot_baru=param;
                                    

                               }
                            

                        /**
                        * field for No_pa
                        */

                        
                                    protected java.lang.String localNo_pa ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_paTracker = false ;

                           public boolean isNo_paSpecified(){
                               return localNo_paTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_pa(){
                               return localNo_pa;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_pa
                               */
                               public void setNo_pa(java.lang.String param){
                            localNo_paTracker = true;
                                   
                                            this.localNo_pa=param;
                                    

                               }
                            

                        /**
                        * field for No_pu
                        */

                        
                                    protected java.lang.String localNo_pu ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_puTracker = false ;

                           public boolean isNo_puSpecified(){
                               return localNo_puTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_pu(){
                               return localNo_pu;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_pu
                               */
                               public void setNo_pu(java.lang.String param){
                            localNo_puTracker = true;
                                   
                                            this.localNo_pu=param;
                                    

                               }
                            

                        /**
                        * field for No_rujukan_tarikhbalik
                        */

                        
                                    protected java.lang.String localNo_rujukan_tarikhbalik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_rujukan_tarikhbalikTracker = false ;

                           public boolean isNo_rujukan_tarikhbalikSpecified(){
                               return localNo_rujukan_tarikhbalikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_rujukan_tarikhbalik(){
                               return localNo_rujukan_tarikhbalik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_rujukan_tarikhbalik
                               */
                               public void setNo_rujukan_tarikhbalik(java.lang.String param){
                            localNo_rujukan_tarikhbalikTracker = true;
                                   
                                            this.localNo_rujukan_tarikhbalik=param;
                                    

                               }
                            

                        /**
                        * field for No_syit
                        */

                        
                                    protected java.lang.String localNo_syit ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_syitTracker = false ;

                           public boolean isNo_syitSpecified(){
                               return localNo_syitTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_syit(){
                               return localNo_syit;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_syit
                               */
                               public void setNo_syit(java.lang.String param){
                            localNo_syitTracker = true;
                                   
                                            this.localNo_syit=param;
                                    

                               }
                            

                        /**
                        * field for No_warta
                        */

                        
                                    protected java.lang.String localNo_warta ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_wartaTracker = false ;

                           public boolean isNo_wartaSpecified(){
                               return localNo_wartaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_warta(){
                               return localNo_warta;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_warta
                               */
                               public void setNo_warta(java.lang.String param){
                            localNo_wartaTracker = true;
                                   
                                            this.localNo_warta=param;
                                    

                               }
                            

                        /**
                        * field for No_warta_tarikbalik
                        */

                        
                                    protected java.lang.String localNo_warta_tarikbalik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_warta_tarikbalikTracker = false ;

                           public boolean isNo_warta_tarikbalikSpecified(){
                               return localNo_warta_tarikbalikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_warta_tarikbalik(){
                               return localNo_warta_tarikbalik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_warta_tarikbalik
                               */
                               public void setNo_warta_tarikbalik(java.lang.String param){
                            localNo_warta_tarikbalikTracker = true;
                                   
                                            this.localNo_warta_tarikbalik=param;
                                    

                               }
                            

                        /**
                        * field for Sebab_tarikbalik
                        */

                        
                                    protected java.lang.String localSebab_tarikbalik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSebab_tarikbalikTracker = false ;

                           public boolean isSebab_tarikbalikSpecified(){
                               return localSebab_tarikbalikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSebab_tarikbalik(){
                               return localSebab_tarikbalik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Sebab_tarikbalik
                               */
                               public void setSebab_tarikbalik(java.lang.String param){
                            localSebab_tarikbalikTracker = true;
                                   
                                            this.localSebab_tarikbalik=param;
                                    

                               }
                            

                        /**
                        * field for Status_borangk
                        */

                        
                                    protected java.lang.String localStatus_borangk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localStatus_borangkTracker = false ;

                           public boolean isStatus_borangkSpecified(){
                               return localStatus_borangkTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getStatus_borangk(){
                               return localStatus_borangk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Status_borangk
                               */
                               public void setStatus_borangk(java.lang.String param){
                            localStatus_borangkTracker = true;
                                   
                                            this.localStatus_borangk=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_borangi
                        */

                        
                                    protected java.lang.String localTarikh_borangi ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_borangiTracker = false ;

                           public boolean isTarikh_borangiSpecified(){
                               return localTarikh_borangiTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_borangi(){
                               return localTarikh_borangi;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_borangi
                               */
                               public void setTarikh_borangi(java.lang.String param){
                            localTarikh_borangiTracker = true;
                                   
                                            this.localTarikh_borangi=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_borangk
                        */

                        
                                    protected java.lang.String localTarikh_borangk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_borangkTracker = false ;

                           public boolean isTarikh_borangkSpecified(){
                               return localTarikh_borangkTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_borangk(){
                               return localTarikh_borangk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_borangk
                               */
                               public void setTarikh_borangk(java.lang.String param){
                            localTarikh_borangkTracker = true;
                                   
                                            this.localTarikh_borangk=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_terima_data
                        */

                        
                                    protected java.lang.String localTarikh_terima_data ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_terima_dataTracker = false ;

                           public boolean isTarikh_terima_dataSpecified(){
                               return localTarikh_terima_dataTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_terima_data(){
                               return localTarikh_terima_data;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_terima_data
                               */
                               public void setTarikh_terima_data(java.lang.String param){
                            localTarikh_terima_dataTracker = true;
                                   
                                            this.localTarikh_terima_data=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_warta
                        */

                        
                                    protected java.lang.String localTarikh_warta ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_wartaTracker = false ;

                           public boolean isTarikh_wartaSpecified(){
                               return localTarikh_wartaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_warta(){
                               return localTarikh_warta;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_warta
                               */
                               public void setTarikh_warta(java.lang.String param){
                            localTarikh_wartaTracker = true;
                                   
                                            this.localTarikh_warta=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_warta_tarikbalik
                        */

                        
                                    protected java.lang.String localTarikh_warta_tarikbalik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_warta_tarikbalikTracker = false ;

                           public boolean isTarikh_warta_tarikbalikSpecified(){
                               return localTarikh_warta_tarikbalikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_warta_tarikbalik(){
                               return localTarikh_warta_tarikbalik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_warta_tarikbalik
                               */
                               public void setTarikh_warta_tarikbalik(java.lang.String param){
                            localTarikh_warta_tarikbalikTracker = true;
                                   
                                            this.localTarikh_warta_tarikbalik=param;
                                    

                               }
                            

                        /**
                        * field for Turutan
                        */

                        
                                    protected int localTurutan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTurutanTracker = false ;

                           public boolean isTurutanSpecified(){
                               return localTurutanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getTurutan(){
                               return localTurutan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Turutan
                               */
                               public void setTurutan(int param){
                            localTurutanTracker = true;
                                   
                                            this.localTurutan=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Tblintppthakmilik",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Tblintppthakmilik",
                           xmlWriter);
                   }

               
                   }
                if (localFlag_prosesTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "flag_proses", xmlWriter);
                             

                                          if (localFlag_proses==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFlag_proses);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localId_fail_myetappTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "id_fail_myetapp", xmlWriter);
                             

                                          if (localId_fail_myetapp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId_fail_myetapp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localId_hakmilikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "id_hakmilik", xmlWriter);
                             

                                          if (localId_hakmilik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId_hakmilik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localId_hakmilik_myetappTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "id_hakmilik_myetapp", xmlWriter);
                             

                                          if (localId_hakmilik_myetapp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId_hakmilik_myetapp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localId_penarikanbalik_myetappTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "id_penarikanbalik_myetapp", xmlWriter);
                             

                                          if (localId_penarikanbalik_myetapp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId_penarikanbalik_myetapp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_daerahTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_daerah", xmlWriter);
                             

                                          if (localKod_daerah==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_daerah);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_luas_ambilTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_luas_ambil", xmlWriter);
                             

                                          if (localKod_luas_ambil==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_luas_ambil);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_luas_asalTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_luas_asal", xmlWriter);
                             

                                          if (localKod_luas_asal==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_luas_asal);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_luas_paTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_luas_pa", xmlWriter);
                             

                                          if (localKod_luas_pa==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_luas_pa);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_mukimTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_mukim", xmlWriter);
                             

                                          if (localKod_mukim==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_mukim);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_negeriTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_negeri", xmlWriter);
                             

                                          if (localKod_negeri==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_negeri);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_unit_hakmilikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_unit_hakmilik", xmlWriter);
                             

                                          if (localKod_unit_hakmilik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_unit_hakmilik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLuas_ambilTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "luas_ambil", xmlWriter);
                             

                                          if (localLuas_ambil==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLuas_ambil);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLuas_asalTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "luas_asal", xmlWriter);
                             

                                          if (localLuas_asal==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLuas_asal);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLuas_paTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "luas_pa", xmlWriter);
                             

                                          if (localLuas_pa==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLuas_pa);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_fail_jkptgTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_fail_jkptg", xmlWriter);
                             

                                          if (localNo_fail_jkptg==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_fail_jkptg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_hakmilikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_hakmilik", xmlWriter);
                             

                                          if (localNo_hakmilik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_hakmilik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_lotTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_lot", xmlWriter);
                             

                                          if (localNo_lot==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_lot);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_lot_baruTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_lot_baru", xmlWriter);
                             

                                          if (localNo_lot_baru==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_lot_baru);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_paTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_pa", xmlWriter);
                             

                                          if (localNo_pa==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_pa);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_puTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_pu", xmlWriter);
                             

                                          if (localNo_pu==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_pu);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_rujukan_tarikhbalikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_rujukan_tarikhbalik", xmlWriter);
                             

                                          if (localNo_rujukan_tarikhbalik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_rujukan_tarikhbalik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_syitTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_syit", xmlWriter);
                             

                                          if (localNo_syit==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_syit);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_wartaTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_warta", xmlWriter);
                             

                                          if (localNo_warta==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_warta);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_warta_tarikbalikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_warta_tarikbalik", xmlWriter);
                             

                                          if (localNo_warta_tarikbalik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_warta_tarikbalik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSebab_tarikbalikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "sebab_tarikbalik", xmlWriter);
                             

                                          if (localSebab_tarikbalik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSebab_tarikbalik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localStatus_borangkTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "status_borangk", xmlWriter);
                             

                                          if (localStatus_borangk==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localStatus_borangk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_borangiTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_borangi", xmlWriter);
                             

                                          if (localTarikh_borangi==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_borangi);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_borangkTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_borangk", xmlWriter);
                             

                                          if (localTarikh_borangk==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_borangk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_terima_dataTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_terima_data", xmlWriter);
                             

                                          if (localTarikh_terima_data==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_terima_data);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_wartaTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_warta", xmlWriter);
                             

                                          if (localTarikh_warta==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_warta);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_warta_tarikbalikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_warta_tarikbalik", xmlWriter);
                             

                                          if (localTarikh_warta_tarikbalik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_warta_tarikbalik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTurutanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "turutan", xmlWriter);
                             
                                               if (localTurutan==java.lang.Integer.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localFlag_prosesTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "flag_proses"));
                                 
                                         elementList.add(localFlag_proses==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag_proses));
                                    } if (localId_fail_myetappTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "id_fail_myetapp"));
                                 
                                         elementList.add(localId_fail_myetapp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId_fail_myetapp));
                                    } if (localId_hakmilikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "id_hakmilik"));
                                 
                                         elementList.add(localId_hakmilik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId_hakmilik));
                                    } if (localId_hakmilik_myetappTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "id_hakmilik_myetapp"));
                                 
                                         elementList.add(localId_hakmilik_myetapp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId_hakmilik_myetapp));
                                    } if (localId_penarikanbalik_myetappTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "id_penarikanbalik_myetapp"));
                                 
                                         elementList.add(localId_penarikanbalik_myetapp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId_penarikanbalik_myetapp));
                                    } if (localKod_daerahTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_daerah"));
                                 
                                         elementList.add(localKod_daerah==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_daerah));
                                    } if (localKod_luas_ambilTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_luas_ambil"));
                                 
                                         elementList.add(localKod_luas_ambil==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_luas_ambil));
                                    } if (localKod_luas_asalTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_luas_asal"));
                                 
                                         elementList.add(localKod_luas_asal==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_luas_asal));
                                    } if (localKod_luas_paTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_luas_pa"));
                                 
                                         elementList.add(localKod_luas_pa==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_luas_pa));
                                    } if (localKod_mukimTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_mukim"));
                                 
                                         elementList.add(localKod_mukim==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_mukim));
                                    } if (localKod_negeriTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_negeri"));
                                 
                                         elementList.add(localKod_negeri==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_negeri));
                                    } if (localKod_unit_hakmilikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_unit_hakmilik"));
                                 
                                         elementList.add(localKod_unit_hakmilik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_unit_hakmilik));
                                    } if (localLuas_ambilTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "luas_ambil"));
                                 
                                         elementList.add(localLuas_ambil==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLuas_ambil));
                                    } if (localLuas_asalTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "luas_asal"));
                                 
                                         elementList.add(localLuas_asal==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLuas_asal));
                                    } if (localLuas_paTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "luas_pa"));
                                 
                                         elementList.add(localLuas_pa==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLuas_pa));
                                    } if (localNo_fail_jkptgTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_fail_jkptg"));
                                 
                                         elementList.add(localNo_fail_jkptg==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_fail_jkptg));
                                    } if (localNo_hakmilikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_hakmilik"));
                                 
                                         elementList.add(localNo_hakmilik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_hakmilik));
                                    } if (localNo_lotTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_lot"));
                                 
                                         elementList.add(localNo_lot==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_lot));
                                    } if (localNo_lot_baruTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_lot_baru"));
                                 
                                         elementList.add(localNo_lot_baru==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_lot_baru));
                                    } if (localNo_paTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_pa"));
                                 
                                         elementList.add(localNo_pa==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_pa));
                                    } if (localNo_puTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_pu"));
                                 
                                         elementList.add(localNo_pu==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_pu));
                                    } if (localNo_rujukan_tarikhbalikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_rujukan_tarikhbalik"));
                                 
                                         elementList.add(localNo_rujukan_tarikhbalik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_rujukan_tarikhbalik));
                                    } if (localNo_syitTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_syit"));
                                 
                                         elementList.add(localNo_syit==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_syit));
                                    } if (localNo_wartaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_warta"));
                                 
                                         elementList.add(localNo_warta==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_warta));
                                    } if (localNo_warta_tarikbalikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_warta_tarikbalik"));
                                 
                                         elementList.add(localNo_warta_tarikbalik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_warta_tarikbalik));
                                    } if (localSebab_tarikbalikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "sebab_tarikbalik"));
                                 
                                         elementList.add(localSebab_tarikbalik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSebab_tarikbalik));
                                    } if (localStatus_borangkTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "status_borangk"));
                                 
                                         elementList.add(localStatus_borangk==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStatus_borangk));
                                    } if (localTarikh_borangiTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_borangi"));
                                 
                                         elementList.add(localTarikh_borangi==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_borangi));
                                    } if (localTarikh_borangkTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_borangk"));
                                 
                                         elementList.add(localTarikh_borangk==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_borangk));
                                    } if (localTarikh_terima_dataTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_terima_data"));
                                 
                                         elementList.add(localTarikh_terima_data==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_terima_data));
                                    } if (localTarikh_wartaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_warta"));
                                 
                                         elementList.add(localTarikh_warta==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_warta));
                                    } if (localTarikh_warta_tarikbalikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_warta_tarikbalik"));
                                 
                                         elementList.add(localTarikh_warta_tarikbalik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_warta_tarikbalik));
                                    } if (localTurutanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "turutan"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                            }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Tblintppthakmilik parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Tblintppthakmilik object =
                new Tblintppthakmilik();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Tblintppthakmilik".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Tblintppthakmilik)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","flag_proses").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlag_proses(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","id_fail_myetapp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId_fail_myetapp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","id_hakmilik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId_hakmilik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","id_hakmilik_myetapp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId_hakmilik_myetapp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","id_penarikanbalik_myetapp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId_penarikanbalik_myetapp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_daerah").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_daerah(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_luas_ambil").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_luas_ambil(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_luas_asal").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_luas_asal(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_luas_pa").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_luas_pa(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_mukim").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_mukim(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_negeri").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_negeri(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_unit_hakmilik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_unit_hakmilik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","luas_ambil").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLuas_ambil(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","luas_asal").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLuas_asal(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","luas_pa").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLuas_pa(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_fail_jkptg").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_fail_jkptg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_hakmilik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_hakmilik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_lot").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_lot(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_lot_baru").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_lot_baru(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_pa").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_pa(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_pu").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_pu(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_rujukan_tarikhbalik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_rujukan_tarikhbalik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_syit").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_syit(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_warta").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_warta(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_warta_tarikbalik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_warta_tarikbalik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","sebab_tarikbalik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSebab_tarikbalik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","status_borangk").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setStatus_borangk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_borangi").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_borangi(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_borangk").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_borangk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_terima_data").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_terima_data(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_warta").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_warta(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_warta_tarikbalik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_warta_tarikbalik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","turutan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTurutan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setTurutan(java.lang.Integer.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setTurutan(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class Tblintpptdokumen
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Tblintpptdokumen
                Namespace URI = http://ws.etanah/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Content_upload
                        */

                        
                                    protected javax.activation.DataHandler localContent_upload ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localContent_uploadTracker = false ;

                           public boolean isContent_uploadSpecified(){
                               return localContent_uploadTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return javax.activation.DataHandler
                           */
                           public  javax.activation.DataHandler getContent_upload(){
                               return localContent_upload;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Content_upload
                               */
                               public void setContent_upload(javax.activation.DataHandler param){
                            localContent_uploadTracker = true;
                                   
                                            this.localContent_upload=param;
                                    

                               }
                            

                        /**
                        * field for Flag_proses
                        */

                        
                                    protected java.lang.String localFlag_proses ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFlag_prosesTracker = false ;

                           public boolean isFlag_prosesSpecified(){
                               return localFlag_prosesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFlag_proses(){
                               return localFlag_proses;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flag_proses
                               */
                               public void setFlag_proses(java.lang.String param){
                            localFlag_prosesTracker = true;
                                   
                                            this.localFlag_proses=param;
                                    

                               }
                            

                        /**
                        * field for Id_hakmilik
                        */

                        
                                    protected java.lang.String localId_hakmilik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localId_hakmilikTracker = false ;

                           public boolean isId_hakmilikSpecified(){
                               return localId_hakmilikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId_hakmilik(){
                               return localId_hakmilik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id_hakmilik
                               */
                               public void setId_hakmilik(java.lang.String param){
                            localId_hakmilikTracker = true;
                                   
                                            this.localId_hakmilik=param;
                                    

                               }
                            

                        /**
                        * field for Jenis_fail
                        */

                        
                                    protected java.lang.String localJenis_fail ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localJenis_failTracker = false ;

                           public boolean isJenis_failSpecified(){
                               return localJenis_failTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getJenis_fail(){
                               return localJenis_fail;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Jenis_fail
                               */
                               public void setJenis_fail(java.lang.String param){
                            localJenis_failTracker = true;
                                   
                                            this.localJenis_fail=param;
                                    

                               }
                            

                        /**
                        * field for Jenis_format_fail
                        */

                        
                                    protected java.lang.String localJenis_format_fail ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localJenis_format_failTracker = false ;

                           public boolean isJenis_format_failSpecified(){
                               return localJenis_format_failTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getJenis_format_fail(){
                               return localJenis_format_fail;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Jenis_format_fail
                               */
                               public void setJenis_format_fail(java.lang.String param){
                            localJenis_format_failTracker = true;
                                   
                                            this.localJenis_format_fail=param;
                                    

                               }
                            

                        /**
                        * field for Kategori
                        */

                        
                                    protected java.lang.String localKategori ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKategoriTracker = false ;

                           public boolean isKategoriSpecified(){
                               return localKategoriTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKategori(){
                               return localKategori;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kategori
                               */
                               public void setKategori(java.lang.String param){
                            localKategoriTracker = true;
                                   
                                            this.localKategori=param;
                                    

                               }
                            

                        /**
                        * field for Kod_daerah
                        */

                        
                                    protected java.lang.String localKod_daerah ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_daerahTracker = false ;

                           public boolean isKod_daerahSpecified(){
                               return localKod_daerahTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_daerah(){
                               return localKod_daerah;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_daerah
                               */
                               public void setKod_daerah(java.lang.String param){
                            localKod_daerahTracker = true;
                                   
                                            this.localKod_daerah=param;
                                    

                               }
                            

                        /**
                        * field for Kod_mukim
                        */

                        
                                    protected java.lang.String localKod_mukim ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_mukimTracker = false ;

                           public boolean isKod_mukimSpecified(){
                               return localKod_mukimTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_mukim(){
                               return localKod_mukim;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_mukim
                               */
                               public void setKod_mukim(java.lang.String param){
                            localKod_mukimTracker = true;
                                   
                                            this.localKod_mukim=param;
                                    

                               }
                            

                        /**
                        * field for Kod_negeri
                        */

                        
                                    protected java.lang.String localKod_negeri ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_negeriTracker = false ;

                           public boolean isKod_negeriSpecified(){
                               return localKod_negeriTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_negeri(){
                               return localKod_negeri;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_negeri
                               */
                               public void setKod_negeri(java.lang.String param){
                            localKod_negeriTracker = true;
                                   
                                            this.localKod_negeri=param;
                                    

                               }
                            

                        /**
                        * field for Kod_unit_hakmilik
                        */

                        
                                    protected java.lang.String localKod_unit_hakmilik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_unit_hakmilikTracker = false ;

                           public boolean isKod_unit_hakmilikSpecified(){
                               return localKod_unit_hakmilikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_unit_hakmilik(){
                               return localKod_unit_hakmilik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_unit_hakmilik
                               */
                               public void setKod_unit_hakmilik(java.lang.String param){
                            localKod_unit_hakmilikTracker = true;
                                   
                                            this.localKod_unit_hakmilik=param;
                                    

                               }
                            

                        /**
                        * field for Nama_dokumen
                        */

                        
                                    protected java.lang.String localNama_dokumen ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNama_dokumenTracker = false ;

                           public boolean isNama_dokumenSpecified(){
                               return localNama_dokumenTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNama_dokumen(){
                               return localNama_dokumen;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Nama_dokumen
                               */
                               public void setNama_dokumen(java.lang.String param){
                            localNama_dokumenTracker = true;
                                   
                                            this.localNama_dokumen=param;
                                    

                               }
                            

                        /**
                        * field for No_fail_jkptg
                        */

                        
                                    protected java.lang.String localNo_fail_jkptg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_fail_jkptgTracker = false ;

                           public boolean isNo_fail_jkptgSpecified(){
                               return localNo_fail_jkptgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_fail_jkptg(){
                               return localNo_fail_jkptg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_fail_jkptg
                               */
                               public void setNo_fail_jkptg(java.lang.String param){
                            localNo_fail_jkptgTracker = true;
                                   
                                            this.localNo_fail_jkptg=param;
                                    

                               }
                            

                        /**
                        * field for No_hakmilik
                        */

                        
                                    protected java.lang.String localNo_hakmilik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_hakmilikTracker = false ;

                           public boolean isNo_hakmilikSpecified(){
                               return localNo_hakmilikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_hakmilik(){
                               return localNo_hakmilik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_hakmilik
                               */
                               public void setNo_hakmilik(java.lang.String param){
                            localNo_hakmilikTracker = true;
                                   
                                            this.localNo_hakmilik=param;
                                    

                               }
                            

                        /**
                        * field for No_rujukan_tarikbalik
                        */

                        
                                    protected java.lang.String localNo_rujukan_tarikbalik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_rujukan_tarikbalikTracker = false ;

                           public boolean isNo_rujukan_tarikbalikSpecified(){
                               return localNo_rujukan_tarikbalikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_rujukan_tarikbalik(){
                               return localNo_rujukan_tarikbalik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_rujukan_tarikbalik
                               */
                               public void setNo_rujukan_tarikbalik(java.lang.String param){
                            localNo_rujukan_tarikbalikTracker = true;
                                   
                                            this.localNo_rujukan_tarikbalik=param;
                                    

                               }
                            

                        /**
                        * field for Size
                        */

                        
                                    protected int localSize ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSizeTracker = false ;

                           public boolean isSizeSpecified(){
                               return localSizeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getSize(){
                               return localSize;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Size
                               */
                               public void setSize(int param){
                            
                                       // setting primitive attribute tracker to true
                                       localSizeTracker =
                                       param != java.lang.Integer.MIN_VALUE;
                                   
                                            this.localSize=param;
                                    

                               }
                            

                        /**
                        * field for Tajuk_dokumen
                        */

                        
                                    protected java.lang.String localTajuk_dokumen ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTajuk_dokumenTracker = false ;

                           public boolean isTajuk_dokumenSpecified(){
                               return localTajuk_dokumenTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTajuk_dokumen(){
                               return localTajuk_dokumen;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tajuk_dokumen
                               */
                               public void setTajuk_dokumen(java.lang.String param){
                            localTajuk_dokumenTracker = true;
                                   
                                            this.localTajuk_dokumen=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_terima_data
                        */

                        
                                    protected java.lang.String localTarikh_terima_data ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_terima_dataTracker = false ;

                           public boolean isTarikh_terima_dataSpecified(){
                               return localTarikh_terima_dataTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_terima_data(){
                               return localTarikh_terima_data;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_terima_data
                               */
                               public void setTarikh_terima_data(java.lang.String param){
                            localTarikh_terima_dataTracker = true;
                                   
                                            this.localTarikh_terima_data=param;
                                    

                               }
                            

                        /**
                        * field for Turutan
                        */

                        
                                    protected int localTurutan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTurutanTracker = false ;

                           public boolean isTurutanSpecified(){
                               return localTurutanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getTurutan(){
                               return localTurutan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Turutan
                               */
                               public void setTurutan(int param){
                            localTurutanTracker = true;
                                   
                                            this.localTurutan=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Tblintpptdokumen",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Tblintpptdokumen",
                           xmlWriter);
                   }

               
                   }
                if (localContent_uploadTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "content_upload", xmlWriter);
                             
                                        
                                    if (localContent_upload!=null)  {
                                       try {
                                           org.apache.axiom.util.stax.XMLStreamWriterUtils.writeDataHandler(xmlWriter, localContent_upload, null, true);
                                       } catch (java.io.IOException ex) {
                                           throw new javax.xml.stream.XMLStreamException("Unable to read data handler for content_upload", ex);
                                       }
                                    } else {
                                         
                                             writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                         
                                    }
                                 
                                   xmlWriter.writeEndElement();
                             } if (localFlag_prosesTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "flag_proses", xmlWriter);
                             

                                          if (localFlag_proses==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFlag_proses);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localId_hakmilikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "id_hakmilik", xmlWriter);
                             

                                          if (localId_hakmilik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId_hakmilik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localJenis_failTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "jenis_fail", xmlWriter);
                             

                                          if (localJenis_fail==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localJenis_fail);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localJenis_format_failTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "jenis_format_fail", xmlWriter);
                             

                                          if (localJenis_format_fail==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localJenis_format_fail);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKategoriTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kategori", xmlWriter);
                             

                                          if (localKategori==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKategori);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_daerahTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_daerah", xmlWriter);
                             

                                          if (localKod_daerah==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_daerah);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_mukimTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_mukim", xmlWriter);
                             

                                          if (localKod_mukim==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_mukim);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_negeriTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_negeri", xmlWriter);
                             

                                          if (localKod_negeri==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_negeri);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_unit_hakmilikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_unit_hakmilik", xmlWriter);
                             

                                          if (localKod_unit_hakmilik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_unit_hakmilik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNama_dokumenTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "nama_dokumen", xmlWriter);
                             

                                          if (localNama_dokumen==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNama_dokumen);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_fail_jkptgTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_fail_jkptg", xmlWriter);
                             

                                          if (localNo_fail_jkptg==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_fail_jkptg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_hakmilikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_hakmilik", xmlWriter);
                             

                                          if (localNo_hakmilik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_hakmilik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_rujukan_tarikbalikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_rujukan_tarikbalik", xmlWriter);
                             

                                          if (localNo_rujukan_tarikbalik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_rujukan_tarikbalik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSizeTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "size", xmlWriter);
                             
                                               if (localSize==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("size cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSize));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTajuk_dokumenTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tajuk_dokumen", xmlWriter);
                             

                                          if (localTajuk_dokumen==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTajuk_dokumen);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_terima_dataTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_terima_data", xmlWriter);
                             

                                          if (localTarikh_terima_data==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_terima_data);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTurutanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "turutan", xmlWriter);
                             
                                               if (localTurutan==java.lang.Integer.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localContent_uploadTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                        "content_upload"));
                                
                            elementList.add(localContent_upload);
                        } if (localFlag_prosesTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "flag_proses"));
                                 
                                         elementList.add(localFlag_proses==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag_proses));
                                    } if (localId_hakmilikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "id_hakmilik"));
                                 
                                         elementList.add(localId_hakmilik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId_hakmilik));
                                    } if (localJenis_failTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "jenis_fail"));
                                 
                                         elementList.add(localJenis_fail==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localJenis_fail));
                                    } if (localJenis_format_failTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "jenis_format_fail"));
                                 
                                         elementList.add(localJenis_format_fail==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localJenis_format_fail));
                                    } if (localKategoriTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kategori"));
                                 
                                         elementList.add(localKategori==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKategori));
                                    } if (localKod_daerahTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_daerah"));
                                 
                                         elementList.add(localKod_daerah==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_daerah));
                                    } if (localKod_mukimTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_mukim"));
                                 
                                         elementList.add(localKod_mukim==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_mukim));
                                    } if (localKod_negeriTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_negeri"));
                                 
                                         elementList.add(localKod_negeri==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_negeri));
                                    } if (localKod_unit_hakmilikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_unit_hakmilik"));
                                 
                                         elementList.add(localKod_unit_hakmilik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_unit_hakmilik));
                                    } if (localNama_dokumenTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "nama_dokumen"));
                                 
                                         elementList.add(localNama_dokumen==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNama_dokumen));
                                    } if (localNo_fail_jkptgTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_fail_jkptg"));
                                 
                                         elementList.add(localNo_fail_jkptg==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_fail_jkptg));
                                    } if (localNo_hakmilikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_hakmilik"));
                                 
                                         elementList.add(localNo_hakmilik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_hakmilik));
                                    } if (localNo_rujukan_tarikbalikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_rujukan_tarikbalik"));
                                 
                                         elementList.add(localNo_rujukan_tarikbalik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_rujukan_tarikbalik));
                                    } if (localSizeTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "size"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSize));
                            } if (localTajuk_dokumenTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tajuk_dokumen"));
                                 
                                         elementList.add(localTajuk_dokumen==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTajuk_dokumen));
                                    } if (localTarikh_terima_dataTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_terima_data"));
                                 
                                         elementList.add(localTarikh_terima_data==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_terima_data));
                                    } if (localTurutanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "turutan"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                            }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Tblintpptdokumen parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Tblintpptdokumen object =
                new Tblintpptdokumen();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Tblintpptdokumen".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Tblintpptdokumen)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","content_upload").equals(reader.getName())){
                                
                                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                        if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                             object.setContent_upload(null);
                                             reader.next();
                                        } else {
                                    
                                            object.setContent_upload(org.apache.axiom.util.stax.XMLStreamReaderUtils.getDataHandlerFromElement(reader));
                                    
                                        }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","flag_proses").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlag_proses(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","id_hakmilik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId_hakmilik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","jenis_fail").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setJenis_fail(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","jenis_format_fail").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setJenis_format_fail(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kategori").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKategori(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_daerah").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_daerah(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_mukim").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_mukim(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_negeri").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_negeri(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_unit_hakmilik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_unit_hakmilik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","nama_dokumen").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNama_dokumen(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_fail_jkptg").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_fail_jkptg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_hakmilik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_hakmilik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_rujukan_tarikbalik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_rujukan_tarikbalik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","size").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"size" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSize(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setSize(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tajuk_dokumen").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTajuk_dokumen(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_terima_data").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_terima_data(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","turutan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTurutan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setTurutan(java.lang.Integer.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setTurutan(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class Tblintpptderafmmk
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Tblintpptderafmmk
                Namespace URI = http://ws.etanah/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Flag_jenis_mmk
                        */

                        
                                    protected java.lang.String localFlag_jenis_mmk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFlag_jenis_mmkTracker = false ;

                           public boolean isFlag_jenis_mmkSpecified(){
                               return localFlag_jenis_mmkTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFlag_jenis_mmk(){
                               return localFlag_jenis_mmk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flag_jenis_mmk
                               */
                               public void setFlag_jenis_mmk(java.lang.String param){
                            localFlag_jenis_mmkTracker = true;
                                   
                                            this.localFlag_jenis_mmk=param;
                                    

                               }
                            

                        /**
                        * field for Flag_proses
                        */

                        
                                    protected java.lang.String localFlag_proses ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFlag_prosesTracker = false ;

                           public boolean isFlag_prosesSpecified(){
                               return localFlag_prosesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFlag_proses(){
                               return localFlag_proses;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flag_proses
                               */
                               public void setFlag_proses(java.lang.String param){
                            localFlag_prosesTracker = true;
                                   
                                            this.localFlag_proses=param;
                                    

                               }
                            

                        /**
                        * field for Keterangan_item_mmk
                        */

                        
                                    protected java.lang.String localKeterangan_item_mmk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKeterangan_item_mmkTracker = false ;

                           public boolean isKeterangan_item_mmkSpecified(){
                               return localKeterangan_item_mmkTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKeterangan_item_mmk(){
                               return localKeterangan_item_mmk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Keterangan_item_mmk
                               */
                               public void setKeterangan_item_mmk(java.lang.String param){
                            localKeterangan_item_mmkTracker = true;
                                   
                                            this.localKeterangan_item_mmk=param;
                                    

                               }
                            

                        /**
                        * field for No_fail_jkptg
                        */

                        
                                    protected java.lang.String localNo_fail_jkptg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_fail_jkptgTracker = false ;

                           public boolean isNo_fail_jkptgSpecified(){
                               return localNo_fail_jkptgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_fail_jkptg(){
                               return localNo_fail_jkptg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_fail_jkptg
                               */
                               public void setNo_fail_jkptg(java.lang.String param){
                            localNo_fail_jkptgTracker = true;
                                   
                                            this.localNo_fail_jkptg=param;
                                    

                               }
                            

                        /**
                        * field for No_item_mmk
                        */

                        
                                    protected double localNo_item_mmk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_item_mmkTracker = false ;

                           public boolean isNo_item_mmkSpecified(){
                               return localNo_item_mmkTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getNo_item_mmk(){
                               return localNo_item_mmk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_item_mmk
                               */
                               public void setNo_item_mmk(double param){
                            
                                       // setting primitive attribute tracker to true
                                       localNo_item_mmkTracker =
                                       !java.lang.Double.isNaN(param);
                                   
                                            this.localNo_item_mmk=param;
                                    

                               }
                            

                        /**
                        * field for No_main_item_mmk
                        */

                        
                                    protected double localNo_main_item_mmk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_main_item_mmkTracker = false ;

                           public boolean isNo_main_item_mmkSpecified(){
                               return localNo_main_item_mmkTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getNo_main_item_mmk(){
                               return localNo_main_item_mmk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_main_item_mmk
                               */
                               public void setNo_main_item_mmk(double param){
                            
                                       // setting primitive attribute tracker to true
                                       localNo_main_item_mmkTracker =
                                       !java.lang.Double.isNaN(param);
                                   
                                            this.localNo_main_item_mmk=param;
                                    

                               }
                            

                        /**
                        * field for No_rujukan_tarikbalik
                        */

                        
                                    protected java.lang.String localNo_rujukan_tarikbalik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_rujukan_tarikbalikTracker = false ;

                           public boolean isNo_rujukan_tarikbalikSpecified(){
                               return localNo_rujukan_tarikbalikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_rujukan_tarikbalik(){
                               return localNo_rujukan_tarikbalik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_rujukan_tarikbalik
                               */
                               public void setNo_rujukan_tarikbalik(java.lang.String param){
                            localNo_rujukan_tarikbalikTracker = true;
                                   
                                            this.localNo_rujukan_tarikbalik=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_terima_data
                        */

                        
                                    protected java.lang.String localTarikh_terima_data ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_terima_dataTracker = false ;

                           public boolean isTarikh_terima_dataSpecified(){
                               return localTarikh_terima_dataTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_terima_data(){
                               return localTarikh_terima_data;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_terima_data
                               */
                               public void setTarikh_terima_data(java.lang.String param){
                            localTarikh_terima_dataTracker = true;
                                   
                                            this.localTarikh_terima_data=param;
                                    

                               }
                            

                        /**
                        * field for Turutan
                        */

                        
                                    protected int localTurutan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTurutanTracker = false ;

                           public boolean isTurutanSpecified(){
                               return localTurutanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getTurutan(){
                               return localTurutan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Turutan
                               */
                               public void setTurutan(int param){
                            localTurutanTracker = true;
                                   
                                            this.localTurutan=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Tblintpptderafmmk",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Tblintpptderafmmk",
                           xmlWriter);
                   }

               
                   }
                if (localFlag_jenis_mmkTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "flag_jenis_mmk", xmlWriter);
                             

                                          if (localFlag_jenis_mmk==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFlag_jenis_mmk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFlag_prosesTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "flag_proses", xmlWriter);
                             

                                          if (localFlag_proses==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFlag_proses);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKeterangan_item_mmkTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "keterangan_item_mmk", xmlWriter);
                             

                                          if (localKeterangan_item_mmk==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKeterangan_item_mmk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_fail_jkptgTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_fail_jkptg", xmlWriter);
                             

                                          if (localNo_fail_jkptg==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_fail_jkptg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_item_mmkTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_item_mmk", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localNo_item_mmk)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("no_item_mmk cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_item_mmk));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_main_item_mmkTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_main_item_mmk", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localNo_main_item_mmk)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("no_main_item_mmk cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_main_item_mmk));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_rujukan_tarikbalikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_rujukan_tarikbalik", xmlWriter);
                             

                                          if (localNo_rujukan_tarikbalik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_rujukan_tarikbalik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_terima_dataTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_terima_data", xmlWriter);
                             

                                          if (localTarikh_terima_data==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_terima_data);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTurutanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "turutan", xmlWriter);
                             
                                               if (localTurutan==java.lang.Integer.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localFlag_jenis_mmkTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "flag_jenis_mmk"));
                                 
                                         elementList.add(localFlag_jenis_mmk==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag_jenis_mmk));
                                    } if (localFlag_prosesTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "flag_proses"));
                                 
                                         elementList.add(localFlag_proses==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag_proses));
                                    } if (localKeterangan_item_mmkTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "keterangan_item_mmk"));
                                 
                                         elementList.add(localKeterangan_item_mmk==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKeterangan_item_mmk));
                                    } if (localNo_fail_jkptgTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_fail_jkptg"));
                                 
                                         elementList.add(localNo_fail_jkptg==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_fail_jkptg));
                                    } if (localNo_item_mmkTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_item_mmk"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_item_mmk));
                            } if (localNo_main_item_mmkTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_main_item_mmk"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_main_item_mmk));
                            } if (localNo_rujukan_tarikbalikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_rujukan_tarikbalik"));
                                 
                                         elementList.add(localNo_rujukan_tarikbalik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_rujukan_tarikbalik));
                                    } if (localTarikh_terima_dataTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_terima_data"));
                                 
                                         elementList.add(localTarikh_terima_data==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_terima_data));
                                    } if (localTurutanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "turutan"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                            }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Tblintpptderafmmk parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Tblintpptderafmmk object =
                new Tblintpptderafmmk();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Tblintpptderafmmk".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Tblintpptderafmmk)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","flag_jenis_mmk").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlag_jenis_mmk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","flag_proses").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlag_proses(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","keterangan_item_mmk").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKeterangan_item_mmk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_fail_jkptg").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_fail_jkptg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_item_mmk").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"no_item_mmk" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_item_mmk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setNo_item_mmk(java.lang.Double.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_main_item_mmk").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"no_main_item_mmk" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_main_item_mmk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setNo_main_item_mmk(java.lang.Double.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_rujukan_tarikbalik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_rujukan_tarikbalik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_terima_data").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_terima_data(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","turutan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTurutan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setTurutan(java.lang.Integer.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setTurutan(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class Tblintpptwarta
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Tblintpptwarta
                Namespace URI = http://ws.etanah/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Flag_proses
                        */

                        
                                    protected java.lang.String localFlag_proses ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFlag_prosesTracker = false ;

                           public boolean isFlag_prosesSpecified(){
                               return localFlag_prosesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFlag_proses(){
                               return localFlag_proses;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flag_proses
                               */
                               public void setFlag_proses(java.lang.String param){
                            localFlag_prosesTracker = true;
                                   
                                            this.localFlag_proses=param;
                                    

                               }
                            

                        /**
                        * field for No_fail_jkptg
                        */

                        
                                    protected java.lang.String localNo_fail_jkptg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_fail_jkptgTracker = false ;

                           public boolean isNo_fail_jkptgSpecified(){
                               return localNo_fail_jkptgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_fail_jkptg(){
                               return localNo_fail_jkptg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_fail_jkptg
                               */
                               public void setNo_fail_jkptg(java.lang.String param){
                            localNo_fail_jkptgTracker = true;
                                   
                                            this.localNo_fail_jkptg=param;
                                    

                               }
                            

                        /**
                        * field for No_warta
                        */

                        
                                    protected java.lang.String localNo_warta ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_wartaTracker = false ;

                           public boolean isNo_wartaSpecified(){
                               return localNo_wartaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_warta(){
                               return localNo_warta;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_warta
                               */
                               public void setNo_warta(java.lang.String param){
                            localNo_wartaTracker = true;
                                   
                                            this.localNo_warta=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_terima_data
                        */

                        
                                    protected java.lang.String localTarikh_terima_data ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_terima_dataTracker = false ;

                           public boolean isTarikh_terima_dataSpecified(){
                               return localTarikh_terima_dataTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_terima_data(){
                               return localTarikh_terima_data;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_terima_data
                               */
                               public void setTarikh_terima_data(java.lang.String param){
                            localTarikh_terima_dataTracker = true;
                                   
                                            this.localTarikh_terima_data=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_warta
                        */

                        
                                    protected java.lang.String localTarikh_warta ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_wartaTracker = false ;

                           public boolean isTarikh_wartaSpecified(){
                               return localTarikh_wartaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_warta(){
                               return localTarikh_warta;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_warta
                               */
                               public void setTarikh_warta(java.lang.String param){
                            localTarikh_wartaTracker = true;
                                   
                                            this.localTarikh_warta=param;
                                    

                               }
                            

                        /**
                        * field for Turutan
                        */

                        
                                    protected int localTurutan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTurutanTracker = false ;

                           public boolean isTurutanSpecified(){
                               return localTurutanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getTurutan(){
                               return localTurutan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Turutan
                               */
                               public void setTurutan(int param){
                            localTurutanTracker = true;
                                   
                                            this.localTurutan=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Tblintpptwarta",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Tblintpptwarta",
                           xmlWriter);
                   }

               
                   }
                if (localFlag_prosesTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "flag_proses", xmlWriter);
                             

                                          if (localFlag_proses==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFlag_proses);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_fail_jkptgTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_fail_jkptg", xmlWriter);
                             

                                          if (localNo_fail_jkptg==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_fail_jkptg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_wartaTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_warta", xmlWriter);
                             

                                          if (localNo_warta==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_warta);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_terima_dataTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_terima_data", xmlWriter);
                             

                                          if (localTarikh_terima_data==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_terima_data);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_wartaTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_warta", xmlWriter);
                             

                                          if (localTarikh_warta==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_warta);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTurutanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "turutan", xmlWriter);
                             
                                               if (localTurutan==java.lang.Integer.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localFlag_prosesTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "flag_proses"));
                                 
                                         elementList.add(localFlag_proses==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag_proses));
                                    } if (localNo_fail_jkptgTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_fail_jkptg"));
                                 
                                         elementList.add(localNo_fail_jkptg==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_fail_jkptg));
                                    } if (localNo_wartaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_warta"));
                                 
                                         elementList.add(localNo_warta==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_warta));
                                    } if (localTarikh_terima_dataTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_terima_data"));
                                 
                                         elementList.add(localTarikh_terima_data==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_terima_data));
                                    } if (localTarikh_wartaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_warta"));
                                 
                                         elementList.add(localTarikh_warta==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_warta));
                                    } if (localTurutanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "turutan"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                            }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Tblintpptwarta parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Tblintpptwarta object =
                new Tblintpptwarta();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Tblintpptwarta".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Tblintpptwarta)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","flag_proses").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlag_proses(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_fail_jkptg").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_fail_jkptg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_warta").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_warta(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_terima_data").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_terima_data(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_warta").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_warta(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","turutan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTurutan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setTurutan(java.lang.Integer.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setTurutan(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class InsertDokumen_byObject
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://ws.etanah",
                "insertDokumen_byObject",
                "ns2");

            

                        /**
                        * field for Tblintpptdokumen
                        * This was an Array!
                        */

                        
                                    protected Tblintpptdokumen[] localTblintpptdokumen ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTblintpptdokumenTracker = false ;

                           public boolean isTblintpptdokumenSpecified(){
                               return localTblintpptdokumenTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return Tblintpptdokumen[]
                           */
                           public  Tblintpptdokumen[] getTblintpptdokumen(){
                               return localTblintpptdokumen;
                           }

                           
                        


                               
                              /**
                               * validate the array for Tblintpptdokumen
                               */
                              protected void validateTblintpptdokumen(Tblintpptdokumen[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Tblintpptdokumen
                              */
                              public void setTblintpptdokumen(Tblintpptdokumen[] param){
                              
                                   validateTblintpptdokumen(param);

                               localTblintpptdokumenTracker = true;
                                      
                                      this.localTblintpptdokumen=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param Tblintpptdokumen
                             */
                             public void addTblintpptdokumen(Tblintpptdokumen param){
                                   if (localTblintpptdokumen == null){
                                   localTblintpptdokumen = new Tblintpptdokumen[]{};
                                   }

                            
                                 //update the setting tracker
                                localTblintpptdokumenTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTblintpptdokumen);
                               list.add(param);
                               this.localTblintpptdokumen =
                             (Tblintpptdokumen[])list.toArray(
                            new Tblintpptdokumen[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":insertDokumen_byObject",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "insertDokumen_byObject",
                           xmlWriter);
                   }

               
                   }
                if (localTblintpptdokumenTracker){
                                       if (localTblintpptdokumen!=null){
                                            for (int i = 0;i < localTblintpptdokumen.length;i++){
                                                if (localTblintpptdokumen[i] != null){
                                                 localTblintpptdokumen[i].serialize(new javax.xml.namespace.QName("http://ws.etanah","tblintpptdokumen"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://ws.etanah", "tblintpptdokumen", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://ws.etanah", "tblintpptdokumen", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localTblintpptdokumenTracker){
                             if (localTblintpptdokumen!=null) {
                                 for (int i = 0;i < localTblintpptdokumen.length;i++){

                                    if (localTblintpptdokumen[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptdokumen"));
                                         elementList.add(localTblintpptdokumen[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptdokumen"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptdokumen"));
                                        elementList.add(localTblintpptdokumen);
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static InsertDokumen_byObject parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            InsertDokumen_byObject object =
                new InsertDokumen_byObject();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"insertDokumen_byObject".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (InsertDokumen_byObject)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah","tblintpptdokumen").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(Tblintpptdokumen.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://ws.etanah","tblintpptdokumen").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(Tblintpptdokumen.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTblintpptdokumen((Tblintpptdokumen[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                Tblintpptdokumen.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class IntegrationHakmilikPengambilanException
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://ws.etanah",
                "IntegrationHakmilikPengambilanException",
                "ns2");

            

                        /**
                        * field for IntegrationHakmilikPengambilanException
                        */

                        
                                    protected Exception localIntegrationHakmilikPengambilanException ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIntegrationHakmilikPengambilanExceptionTracker = false ;

                           public boolean isIntegrationHakmilikPengambilanExceptionSpecified(){
                               return localIntegrationHakmilikPengambilanExceptionTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return Exception
                           */
                           public  Exception getIntegrationHakmilikPengambilanException(){
                               return localIntegrationHakmilikPengambilanException;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IntegrationHakmilikPengambilanException
                               */
                               public void setIntegrationHakmilikPengambilanException(Exception param){
                            localIntegrationHakmilikPengambilanExceptionTracker = true;
                                   
                                            this.localIntegrationHakmilikPengambilanException=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":IntegrationHakmilikPengambilanException",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "IntegrationHakmilikPengambilanException",
                           xmlWriter);
                   }

               
                   }
                if (localIntegrationHakmilikPengambilanExceptionTracker){
                                    if (localIntegrationHakmilikPengambilanException==null){

                                        writeStartElement(null, "http://ws.etanah", "IntegrationHakmilikPengambilanException", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localIntegrationHakmilikPengambilanException.serialize(new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException"),
                                        xmlWriter);
                                    }
                                }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localIntegrationHakmilikPengambilanExceptionTracker){
                            elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                      "IntegrationHakmilikPengambilanException"));
                            
                            
                                    elementList.add(localIntegrationHakmilikPengambilanException==null?null:
                                    localIntegrationHakmilikPengambilanException);
                                }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static IntegrationHakmilikPengambilanException parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            IntegrationHakmilikPengambilanException object =
                new IntegrationHakmilikPengambilanException();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                   nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                   if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                         // Skip the element and report the null value.  It cannot have subelements.
                         while (!reader.isEndElement())
                             reader.next();
                         
                                 return null;
                             

                   }
                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"IntegrationHakmilikPengambilanException".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (IntegrationHakmilikPengambilanException)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah","IntegrationHakmilikPengambilanException").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setIntegrationHakmilikPengambilanException(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setIntegrationHakmilikPengambilanException(Exception.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class InsertDerafMMKTajuk_byObject
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://ws.etanah",
                "insertDerafMMKTajuk_byObject",
                "ns2");

            

                        /**
                        * field for Tblintpptderafmmktajuk
                        * This was an Array!
                        */

                        
                                    protected Tblintpptderafmmktajuk[] localTblintpptderafmmktajuk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTblintpptderafmmktajukTracker = false ;

                           public boolean isTblintpptderafmmktajukSpecified(){
                               return localTblintpptderafmmktajukTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return Tblintpptderafmmktajuk[]
                           */
                           public  Tblintpptderafmmktajuk[] getTblintpptderafmmktajuk(){
                               return localTblintpptderafmmktajuk;
                           }

                           
                        


                               
                              /**
                               * validate the array for Tblintpptderafmmktajuk
                               */
                              protected void validateTblintpptderafmmktajuk(Tblintpptderafmmktajuk[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Tblintpptderafmmktajuk
                              */
                              public void setTblintpptderafmmktajuk(Tblintpptderafmmktajuk[] param){
                              
                                   validateTblintpptderafmmktajuk(param);

                               localTblintpptderafmmktajukTracker = true;
                                      
                                      this.localTblintpptderafmmktajuk=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param Tblintpptderafmmktajuk
                             */
                             public void addTblintpptderafmmktajuk(Tblintpptderafmmktajuk param){
                                   if (localTblintpptderafmmktajuk == null){
                                   localTblintpptderafmmktajuk = new Tblintpptderafmmktajuk[]{};
                                   }

                            
                                 //update the setting tracker
                                localTblintpptderafmmktajukTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTblintpptderafmmktajuk);
                               list.add(param);
                               this.localTblintpptderafmmktajuk =
                             (Tblintpptderafmmktajuk[])list.toArray(
                            new Tblintpptderafmmktajuk[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":insertDerafMMKTajuk_byObject",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "insertDerafMMKTajuk_byObject",
                           xmlWriter);
                   }

               
                   }
                if (localTblintpptderafmmktajukTracker){
                                       if (localTblintpptderafmmktajuk!=null){
                                            for (int i = 0;i < localTblintpptderafmmktajuk.length;i++){
                                                if (localTblintpptderafmmktajuk[i] != null){
                                                 localTblintpptderafmmktajuk[i].serialize(new javax.xml.namespace.QName("http://ws.etanah","tblintpptderafmmktajuk"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://ws.etanah", "tblintpptderafmmktajuk", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://ws.etanah", "tblintpptderafmmktajuk", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localTblintpptderafmmktajukTracker){
                             if (localTblintpptderafmmktajuk!=null) {
                                 for (int i = 0;i < localTblintpptderafmmktajuk.length;i++){

                                    if (localTblintpptderafmmktajuk[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptderafmmktajuk"));
                                         elementList.add(localTblintpptderafmmktajuk[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptderafmmktajuk"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptderafmmktajuk"));
                                        elementList.add(localTblintpptderafmmktajuk);
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static InsertDerafMMKTajuk_byObject parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            InsertDerafMMKTajuk_byObject object =
                new InsertDerafMMKTajuk_byObject();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"insertDerafMMKTajuk_byObject".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (InsertDerafMMKTajuk_byObject)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah","tblintpptderafmmktajuk").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(Tblintpptderafmmktajuk.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://ws.etanah","tblintpptderafmmktajuk").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(Tblintpptderafmmktajuk.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTblintpptderafmmktajuk((Tblintpptderafmmktajuk[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                Tblintpptderafmmktajuk.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class Tblintpptderafmmktajuk
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Tblintpptderafmmktajuk
                Namespace URI = http://ws.etanah/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Flag_proses
                        */

                        
                                    protected java.lang.String localFlag_proses ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFlag_prosesTracker = false ;

                           public boolean isFlag_prosesSpecified(){
                               return localFlag_prosesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFlag_proses(){
                               return localFlag_proses;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flag_proses
                               */
                               public void setFlag_proses(java.lang.String param){
                            localFlag_prosesTracker = true;
                                   
                                            this.localFlag_proses=param;
                                    

                               }
                            

                        /**
                        * field for Jenis_waktu_sidang
                        */

                        
                                    protected java.lang.String localJenis_waktu_sidang ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localJenis_waktu_sidangTracker = false ;

                           public boolean isJenis_waktu_sidangSpecified(){
                               return localJenis_waktu_sidangTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getJenis_waktu_sidang(){
                               return localJenis_waktu_sidang;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Jenis_waktu_sidang
                               */
                               public void setJenis_waktu_sidang(java.lang.String param){
                            localJenis_waktu_sidangTracker = true;
                                   
                                            this.localJenis_waktu_sidang=param;
                                    

                               }
                            

                        /**
                        * field for Keterangan_sidang
                        */

                        
                                    protected java.lang.String localKeterangan_sidang ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKeterangan_sidangTracker = false ;

                           public boolean isKeterangan_sidangSpecified(){
                               return localKeterangan_sidangTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKeterangan_sidang(){
                               return localKeterangan_sidang;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Keterangan_sidang
                               */
                               public void setKeterangan_sidang(java.lang.String param){
                            localKeterangan_sidangTracker = true;
                                   
                                            this.localKeterangan_sidang=param;
                                    

                               }
                            

                        /**
                        * field for No_fail_jkptg
                        */

                        
                                    protected java.lang.String localNo_fail_jkptg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_fail_jkptgTracker = false ;

                           public boolean isNo_fail_jkptgSpecified(){
                               return localNo_fail_jkptgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_fail_jkptg(){
                               return localNo_fail_jkptg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_fail_jkptg
                               */
                               public void setNo_fail_jkptg(java.lang.String param){
                            localNo_fail_jkptgTracker = true;
                                   
                                            this.localNo_fail_jkptg=param;
                                    

                               }
                            

                        /**
                        * field for No_rujukan_tarikbalik
                        */

                        
                                    protected java.lang.String localNo_rujukan_tarikbalik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_rujukan_tarikbalikTracker = false ;

                           public boolean isNo_rujukan_tarikbalikSpecified(){
                               return localNo_rujukan_tarikbalikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_rujukan_tarikbalik(){
                               return localNo_rujukan_tarikbalik;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_rujukan_tarikbalik
                               */
                               public void setNo_rujukan_tarikbalik(java.lang.String param){
                            localNo_rujukan_tarikbalikTracker = true;
                                   
                                            this.localNo_rujukan_tarikbalik=param;
                                    

                               }
                            

                        /**
                        * field for Tajuk
                        */

                        
                                    protected java.lang.String localTajuk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTajukTracker = false ;

                           public boolean isTajukSpecified(){
                               return localTajukTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTajuk(){
                               return localTajuk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tajuk
                               */
                               public void setTajuk(java.lang.String param){
                            localTajukTracker = true;
                                   
                                            this.localTajuk=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_sidang
                        */

                        
                                    protected java.lang.String localTarikh_sidang ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_sidangTracker = false ;

                           public boolean isTarikh_sidangSpecified(){
                               return localTarikh_sidangTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_sidang(){
                               return localTarikh_sidang;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_sidang
                               */
                               public void setTarikh_sidang(java.lang.String param){
                            localTarikh_sidangTracker = true;
                                   
                                            this.localTarikh_sidang=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_terima_data
                        */

                        
                                    protected java.lang.String localTarikh_terima_data ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_terima_dataTracker = false ;

                           public boolean isTarikh_terima_dataSpecified(){
                               return localTarikh_terima_dataTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_terima_data(){
                               return localTarikh_terima_data;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_terima_data
                               */
                               public void setTarikh_terima_data(java.lang.String param){
                            localTarikh_terima_dataTracker = true;
                                   
                                            this.localTarikh_terima_data=param;
                                    

                               }
                            

                        /**
                        * field for Tempat_sidang
                        */

                        
                                    protected java.lang.String localTempat_sidang ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTempat_sidangTracker = false ;

                           public boolean isTempat_sidangSpecified(){
                               return localTempat_sidangTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTempat_sidang(){
                               return localTempat_sidang;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tempat_sidang
                               */
                               public void setTempat_sidang(java.lang.String param){
                            localTempat_sidangTracker = true;
                                   
                                            this.localTempat_sidang=param;
                                    

                               }
                            

                        /**
                        * field for Turutan
                        */

                        
                                    protected int localTurutan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTurutanTracker = false ;

                           public boolean isTurutanSpecified(){
                               return localTurutanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getTurutan(){
                               return localTurutan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Turutan
                               */
                               public void setTurutan(int param){
                            localTurutanTracker = true;
                                   
                                            this.localTurutan=param;
                                    

                               }
                            

                        /**
                        * field for Waktu_sidang
                        */

                        
                                    protected java.lang.String localWaktu_sidang ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localWaktu_sidangTracker = false ;

                           public boolean isWaktu_sidangSpecified(){
                               return localWaktu_sidangTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getWaktu_sidang(){
                               return localWaktu_sidang;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Waktu_sidang
                               */
                               public void setWaktu_sidang(java.lang.String param){
                            localWaktu_sidangTracker = true;
                                   
                                            this.localWaktu_sidang=param;
                                    

                               }
                            

                        /**
                        * field for Waktu_sidang_keterangan
                        */

                        
                                    protected java.lang.String localWaktu_sidang_keterangan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localWaktu_sidang_keteranganTracker = false ;

                           public boolean isWaktu_sidang_keteranganSpecified(){
                               return localWaktu_sidang_keteranganTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getWaktu_sidang_keterangan(){
                               return localWaktu_sidang_keterangan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Waktu_sidang_keterangan
                               */
                               public void setWaktu_sidang_keterangan(java.lang.String param){
                            localWaktu_sidang_keteranganTracker = true;
                                   
                                            this.localWaktu_sidang_keterangan=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Tblintpptderafmmktajuk",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Tblintpptderafmmktajuk",
                           xmlWriter);
                   }

               
                   }
                if (localFlag_prosesTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "flag_proses", xmlWriter);
                             

                                          if (localFlag_proses==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFlag_proses);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localJenis_waktu_sidangTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "jenis_waktu_sidang", xmlWriter);
                             

                                          if (localJenis_waktu_sidang==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localJenis_waktu_sidang);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKeterangan_sidangTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "keterangan_sidang", xmlWriter);
                             

                                          if (localKeterangan_sidang==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKeterangan_sidang);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_fail_jkptgTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_fail_jkptg", xmlWriter);
                             

                                          if (localNo_fail_jkptg==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_fail_jkptg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_rujukan_tarikbalikTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_rujukan_tarikbalik", xmlWriter);
                             

                                          if (localNo_rujukan_tarikbalik==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_rujukan_tarikbalik);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTajukTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tajuk", xmlWriter);
                             

                                          if (localTajuk==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTajuk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_sidangTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_sidang", xmlWriter);
                             

                                          if (localTarikh_sidang==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_sidang);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_terima_dataTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_terima_data", xmlWriter);
                             

                                          if (localTarikh_terima_data==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_terima_data);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTempat_sidangTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tempat_sidang", xmlWriter);
                             

                                          if (localTempat_sidang==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTempat_sidang);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTurutanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "turutan", xmlWriter);
                             
                                               if (localTurutan==java.lang.Integer.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localWaktu_sidangTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "waktu_sidang", xmlWriter);
                             

                                          if (localWaktu_sidang==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localWaktu_sidang);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localWaktu_sidang_keteranganTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "waktu_sidang_keterangan", xmlWriter);
                             

                                          if (localWaktu_sidang_keterangan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localWaktu_sidang_keterangan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localFlag_prosesTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "flag_proses"));
                                 
                                         elementList.add(localFlag_proses==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag_proses));
                                    } if (localJenis_waktu_sidangTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "jenis_waktu_sidang"));
                                 
                                         elementList.add(localJenis_waktu_sidang==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localJenis_waktu_sidang));
                                    } if (localKeterangan_sidangTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "keterangan_sidang"));
                                 
                                         elementList.add(localKeterangan_sidang==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKeterangan_sidang));
                                    } if (localNo_fail_jkptgTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_fail_jkptg"));
                                 
                                         elementList.add(localNo_fail_jkptg==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_fail_jkptg));
                                    } if (localNo_rujukan_tarikbalikTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_rujukan_tarikbalik"));
                                 
                                         elementList.add(localNo_rujukan_tarikbalik==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_rujukan_tarikbalik));
                                    } if (localTajukTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tajuk"));
                                 
                                         elementList.add(localTajuk==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTajuk));
                                    } if (localTarikh_sidangTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_sidang"));
                                 
                                         elementList.add(localTarikh_sidang==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_sidang));
                                    } if (localTarikh_terima_dataTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_terima_data"));
                                 
                                         elementList.add(localTarikh_terima_data==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_terima_data));
                                    } if (localTempat_sidangTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tempat_sidang"));
                                 
                                         elementList.add(localTempat_sidang==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTempat_sidang));
                                    } if (localTurutanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "turutan"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                            } if (localWaktu_sidangTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "waktu_sidang"));
                                 
                                         elementList.add(localWaktu_sidang==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWaktu_sidang));
                                    } if (localWaktu_sidang_keteranganTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "waktu_sidang_keterangan"));
                                 
                                         elementList.add(localWaktu_sidang_keterangan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWaktu_sidang_keterangan));
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Tblintpptderafmmktajuk parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Tblintpptderafmmktajuk object =
                new Tblintpptderafmmktajuk();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Tblintpptderafmmktajuk".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Tblintpptderafmmktajuk)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","flag_proses").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlag_proses(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","jenis_waktu_sidang").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setJenis_waktu_sidang(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","keterangan_sidang").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKeterangan_sidang(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_fail_jkptg").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_fail_jkptg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_rujukan_tarikbalik").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_rujukan_tarikbalik(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tajuk").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTajuk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_sidang").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_sidang(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_terima_data").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_terima_data(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tempat_sidang").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTempat_sidang(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","turutan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTurutan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setTurutan(java.lang.Integer.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setTurutan(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","waktu_sidang").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setWaktu_sidang(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","waktu_sidang_keterangan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setWaktu_sidang_keterangan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class InsertMaklumatPengambilan_byObject
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://ws.etanah",
                "insertMaklumatPengambilan_byObject",
                "ns2");

            

                        /**
                        * field for Tblintpptmaklumatpengambilan
                        * This was an Array!
                        */

                        
                                    protected Tblintpptmaklumatpengambilan[] localTblintpptmaklumatpengambilan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTblintpptmaklumatpengambilanTracker = false ;

                           public boolean isTblintpptmaklumatpengambilanSpecified(){
                               return localTblintpptmaklumatpengambilanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return Tblintpptmaklumatpengambilan[]
                           */
                           public  Tblintpptmaklumatpengambilan[] getTblintpptmaklumatpengambilan(){
                               return localTblintpptmaklumatpengambilan;
                           }

                           
                        


                               
                              /**
                               * validate the array for Tblintpptmaklumatpengambilan
                               */
                              protected void validateTblintpptmaklumatpengambilan(Tblintpptmaklumatpengambilan[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Tblintpptmaklumatpengambilan
                              */
                              public void setTblintpptmaklumatpengambilan(Tblintpptmaklumatpengambilan[] param){
                              
                                   validateTblintpptmaklumatpengambilan(param);

                               localTblintpptmaklumatpengambilanTracker = true;
                                      
                                      this.localTblintpptmaklumatpengambilan=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param Tblintpptmaklumatpengambilan
                             */
                             public void addTblintpptmaklumatpengambilan(Tblintpptmaklumatpengambilan param){
                                   if (localTblintpptmaklumatpengambilan == null){
                                   localTblintpptmaklumatpengambilan = new Tblintpptmaklumatpengambilan[]{};
                                   }

                            
                                 //update the setting tracker
                                localTblintpptmaklumatpengambilanTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTblintpptmaklumatpengambilan);
                               list.add(param);
                               this.localTblintpptmaklumatpengambilan =
                             (Tblintpptmaklumatpengambilan[])list.toArray(
                            new Tblintpptmaklumatpengambilan[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":insertMaklumatPengambilan_byObject",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "insertMaklumatPengambilan_byObject",
                           xmlWriter);
                   }

               
                   }
                if (localTblintpptmaklumatpengambilanTracker){
                                       if (localTblintpptmaklumatpengambilan!=null){
                                            for (int i = 0;i < localTblintpptmaklumatpengambilan.length;i++){
                                                if (localTblintpptmaklumatpengambilan[i] != null){
                                                 localTblintpptmaklumatpengambilan[i].serialize(new javax.xml.namespace.QName("http://ws.etanah","tblintpptmaklumatpengambilan"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://ws.etanah", "tblintpptmaklumatpengambilan", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://ws.etanah", "tblintpptmaklumatpengambilan", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localTblintpptmaklumatpengambilanTracker){
                             if (localTblintpptmaklumatpengambilan!=null) {
                                 for (int i = 0;i < localTblintpptmaklumatpengambilan.length;i++){

                                    if (localTblintpptmaklumatpengambilan[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptmaklumatpengambilan"));
                                         elementList.add(localTblintpptmaklumatpengambilan[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptmaklumatpengambilan"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptmaklumatpengambilan"));
                                        elementList.add(localTblintpptmaklumatpengambilan);
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static InsertMaklumatPengambilan_byObject parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            InsertMaklumatPengambilan_byObject object =
                new InsertMaklumatPengambilan_byObject();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"insertMaklumatPengambilan_byObject".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (InsertMaklumatPengambilan_byObject)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah","tblintpptmaklumatpengambilan").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(Tblintpptmaklumatpengambilan.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://ws.etanah","tblintpptmaklumatpengambilan").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(Tblintpptmaklumatpengambilan.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTblintpptmaklumatpengambilan((Tblintpptmaklumatpengambilan[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                Tblintpptmaklumatpengambilan.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://ws.etanah/xsd".equals(namespaceURI) &&
                  "Tblintpptderafmmktajuk".equals(typeName)){
                   
                            return  Tblintpptderafmmktajuk.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://ws.etanah/xsd".equals(namespaceURI) &&
                  "Tblintppthakmilik".equals(typeName)){
                   
                            return  Tblintppthakmilik.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://ws.etanah/xsd".equals(namespaceURI) &&
                  "Tblintpptmaklumatpengambilan".equals(typeName)){
                   
                            return  Tblintpptmaklumatpengambilan.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://ws.etanah/xsd".equals(namespaceURI) &&
                  "Tblintpptwarta".equals(typeName)){
                   
                            return  Tblintpptwarta.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://ws.etanah/xsd".equals(namespaceURI) &&
                  "Tblintpptderafmmk".equals(typeName)){
                   
                            return  Tblintpptderafmmk.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://ws.etanah/xsd".equals(namespaceURI) &&
                  "Tblintpptdokumen".equals(typeName)){
                   
                            return  Tblintpptdokumen.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://ws.etanah".equals(namespaceURI) &&
                  "Exception".equals(typeName)){
                   
                            return  Exception.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    
        public static class InsertHakmilikList_byObject
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://ws.etanah",
                "insertHakmilikList_byObject",
                "ns2");

            

                        /**
                        * field for Tblintppthakmilik
                        * This was an Array!
                        */

                        
                                    protected Tblintppthakmilik[] localTblintppthakmilik ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTblintppthakmilikTracker = false ;

                           public boolean isTblintppthakmilikSpecified(){
                               return localTblintppthakmilikTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return Tblintppthakmilik[]
                           */
                           public  Tblintppthakmilik[] getTblintppthakmilik(){
                               return localTblintppthakmilik;
                           }

                           
                        


                               
                              /**
                               * validate the array for Tblintppthakmilik
                               */
                              protected void validateTblintppthakmilik(Tblintppthakmilik[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Tblintppthakmilik
                              */
                              public void setTblintppthakmilik(Tblintppthakmilik[] param){
                              
                                   validateTblintppthakmilik(param);

                               localTblintppthakmilikTracker = true;
                                      
                                      this.localTblintppthakmilik=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param Tblintppthakmilik
                             */
                             public void addTblintppthakmilik(Tblintppthakmilik param){
                                   if (localTblintppthakmilik == null){
                                   localTblintppthakmilik = new Tblintppthakmilik[]{};
                                   }

                            
                                 //update the setting tracker
                                localTblintppthakmilikTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTblintppthakmilik);
                               list.add(param);
                               this.localTblintppthakmilik =
                             (Tblintppthakmilik[])list.toArray(
                            new Tblintppthakmilik[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":insertHakmilikList_byObject",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "insertHakmilikList_byObject",
                           xmlWriter);
                   }

               
                   }
                if (localTblintppthakmilikTracker){
                                       if (localTblintppthakmilik!=null){
                                            for (int i = 0;i < localTblintppthakmilik.length;i++){
                                                if (localTblintppthakmilik[i] != null){
                                                 localTblintppthakmilik[i].serialize(new javax.xml.namespace.QName("http://ws.etanah","tblintppthakmilik"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://ws.etanah", "tblintppthakmilik", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://ws.etanah", "tblintppthakmilik", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localTblintppthakmilikTracker){
                             if (localTblintppthakmilik!=null) {
                                 for (int i = 0;i < localTblintppthakmilik.length;i++){

                                    if (localTblintppthakmilik[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintppthakmilik"));
                                         elementList.add(localTblintppthakmilik[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintppthakmilik"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintppthakmilik"));
                                        elementList.add(localTblintppthakmilik);
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static InsertHakmilikList_byObject parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            InsertHakmilikList_byObject object =
                new InsertHakmilikList_byObject();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"insertHakmilikList_byObject".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (InsertHakmilikList_byObject)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah","tblintppthakmilik").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(Tblintppthakmilik.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://ws.etanah","tblintppthakmilik").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(Tblintppthakmilik.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTblintppthakmilik((Tblintppthakmilik[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                Tblintppthakmilik.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class InsertDerafMMK_byObject
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://ws.etanah",
                "insertDerafMMK_byObject",
                "ns2");

            

                        /**
                        * field for Tblintpptderafmmk
                        * This was an Array!
                        */

                        
                                    protected Tblintpptderafmmk[] localTblintpptderafmmk ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTblintpptderafmmkTracker = false ;

                           public boolean isTblintpptderafmmkSpecified(){
                               return localTblintpptderafmmkTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return Tblintpptderafmmk[]
                           */
                           public  Tblintpptderafmmk[] getTblintpptderafmmk(){
                               return localTblintpptderafmmk;
                           }

                           
                        


                               
                              /**
                               * validate the array for Tblintpptderafmmk
                               */
                              protected void validateTblintpptderafmmk(Tblintpptderafmmk[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Tblintpptderafmmk
                              */
                              public void setTblintpptderafmmk(Tblintpptderafmmk[] param){
                              
                                   validateTblintpptderafmmk(param);

                               localTblintpptderafmmkTracker = true;
                                      
                                      this.localTblintpptderafmmk=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param Tblintpptderafmmk
                             */
                             public void addTblintpptderafmmk(Tblintpptderafmmk param){
                                   if (localTblintpptderafmmk == null){
                                   localTblintpptderafmmk = new Tblintpptderafmmk[]{};
                                   }

                            
                                 //update the setting tracker
                                localTblintpptderafmmkTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTblintpptderafmmk);
                               list.add(param);
                               this.localTblintpptderafmmk =
                             (Tblintpptderafmmk[])list.toArray(
                            new Tblintpptderafmmk[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":insertDerafMMK_byObject",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "insertDerafMMK_byObject",
                           xmlWriter);
                   }

               
                   }
                if (localTblintpptderafmmkTracker){
                                       if (localTblintpptderafmmk!=null){
                                            for (int i = 0;i < localTblintpptderafmmk.length;i++){
                                                if (localTblintpptderafmmk[i] != null){
                                                 localTblintpptderafmmk[i].serialize(new javax.xml.namespace.QName("http://ws.etanah","tblintpptderafmmk"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://ws.etanah", "tblintpptderafmmk", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://ws.etanah", "tblintpptderafmmk", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localTblintpptderafmmkTracker){
                             if (localTblintpptderafmmk!=null) {
                                 for (int i = 0;i < localTblintpptderafmmk.length;i++){

                                    if (localTblintpptderafmmk[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptderafmmk"));
                                         elementList.add(localTblintpptderafmmk[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptderafmmk"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                          "tblintpptderafmmk"));
                                        elementList.add(localTblintpptderafmmk);
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static InsertDerafMMK_byObject parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            InsertDerafMMK_byObject object =
                new InsertDerafMMK_byObject();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"insertDerafMMK_byObject".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (InsertDerafMMK_byObject)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah","tblintpptderafmmk").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(Tblintpptderafmmk.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://ws.etanah","tblintpptderafmmk").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(Tblintpptderafmmk.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTblintpptderafmmk((Tblintpptderafmmk[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                Tblintpptderafmmk.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class Tblintpptmaklumatpengambilan
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Tblintpptmaklumatpengambilan
                Namespace URI = http://ws.etanah/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Alamat1_kjp
                        */

                        
                                    protected java.lang.String localAlamat1_kjp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAlamat1_kjpTracker = false ;

                           public boolean isAlamat1_kjpSpecified(){
                               return localAlamat1_kjpTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAlamat1_kjp(){
                               return localAlamat1_kjp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Alamat1_kjp
                               */
                               public void setAlamat1_kjp(java.lang.String param){
                            localAlamat1_kjpTracker = true;
                                   
                                            this.localAlamat1_kjp=param;
                                    

                               }
                            

                        /**
                        * field for Alamat2_kjp
                        */

                        
                                    protected java.lang.String localAlamat2_kjp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAlamat2_kjpTracker = false ;

                           public boolean isAlamat2_kjpSpecified(){
                               return localAlamat2_kjpTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAlamat2_kjp(){
                               return localAlamat2_kjp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Alamat2_kjp
                               */
                               public void setAlamat2_kjp(java.lang.String param){
                            localAlamat2_kjpTracker = true;
                                   
                                            this.localAlamat2_kjp=param;
                                    

                               }
                            

                        /**
                        * field for Alamat3_kjp
                        */

                        
                                    protected java.lang.String localAlamat3_kjp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAlamat3_kjpTracker = false ;

                           public boolean isAlamat3_kjpSpecified(){
                               return localAlamat3_kjpTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAlamat3_kjp(){
                               return localAlamat3_kjp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Alamat3_kjp
                               */
                               public void setAlamat3_kjp(java.lang.String param){
                            localAlamat3_kjpTracker = true;
                                   
                                            this.localAlamat3_kjp=param;
                                    

                               }
                            

                        /**
                        * field for Flag_permohonan_segera
                        */

                        
                                    protected java.lang.String localFlag_permohonan_segera ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFlag_permohonan_segeraTracker = false ;

                           public boolean isFlag_permohonan_segeraSpecified(){
                               return localFlag_permohonan_segeraTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFlag_permohonan_segera(){
                               return localFlag_permohonan_segera;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flag_permohonan_segera
                               */
                               public void setFlag_permohonan_segera(java.lang.String param){
                            localFlag_permohonan_segeraTracker = true;
                                   
                                            this.localFlag_permohonan_segera=param;
                                    

                               }
                            

                        /**
                        * field for Flag_proses
                        */

                        
                                    protected java.lang.String localFlag_proses ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFlag_prosesTracker = false ;

                           public boolean isFlag_prosesSpecified(){
                               return localFlag_prosesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFlag_proses(){
                               return localFlag_proses;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flag_proses
                               */
                               public void setFlag_proses(java.lang.String param){
                            localFlag_prosesTracker = true;
                                   
                                            this.localFlag_proses=param;
                                    

                               }
                            

                        /**
                        * field for Id_agensi_myetapp
                        */

                        
                                    protected java.lang.String localId_agensi_myetapp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localId_agensi_myetappTracker = false ;

                           public boolean isId_agensi_myetappSpecified(){
                               return localId_agensi_myetappTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId_agensi_myetapp(){
                               return localId_agensi_myetapp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id_agensi_myetapp
                               */
                               public void setId_agensi_myetapp(java.lang.String param){
                            localId_agensi_myetappTracker = true;
                                   
                                            this.localId_agensi_myetapp=param;
                                    

                               }
                            

                        /**
                        * field for Id_kementerian_myetapp
                        */

                        
                                    protected java.lang.String localId_kementerian_myetapp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localId_kementerian_myetappTracker = false ;

                           public boolean isId_kementerian_myetappSpecified(){
                               return localId_kementerian_myetappTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId_kementerian_myetapp(){
                               return localId_kementerian_myetapp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id_kementerian_myetapp
                               */
                               public void setId_kementerian_myetapp(java.lang.String param){
                            localId_kementerian_myetappTracker = true;
                                   
                                            this.localId_kementerian_myetapp=param;
                                    

                               }
                            

                        /**
                        * field for Jenis_pengambilan
                        */

                        
                                    protected java.lang.String localJenis_pengambilan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localJenis_pengambilanTracker = false ;

                           public boolean isJenis_pengambilanSpecified(){
                               return localJenis_pengambilanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getJenis_pengambilan(){
                               return localJenis_pengambilan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Jenis_pengambilan
                               */
                               public void setJenis_pengambilan(java.lang.String param){
                            localJenis_pengambilanTracker = true;
                                   
                                            this.localJenis_pengambilan=param;
                                    

                               }
                            

                        /**
                        * field for Jenis_pengambilan_segera
                        */

                        
                                    protected java.lang.String localJenis_pengambilan_segera ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localJenis_pengambilan_segeraTracker = false ;

                           public boolean isJenis_pengambilan_segeraSpecified(){
                               return localJenis_pengambilan_segeraTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getJenis_pengambilan_segera(){
                               return localJenis_pengambilan_segera;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Jenis_pengambilan_segera
                               */
                               public void setJenis_pengambilan_segera(java.lang.String param){
                            localJenis_pengambilan_segeraTracker = true;
                                   
                                            this.localJenis_pengambilan_segera=param;
                                    

                               }
                            

                        /**
                        * field for Jenis_projek_pengambilan
                        */

                        
                                    protected java.lang.String localJenis_projek_pengambilan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localJenis_projek_pengambilanTracker = false ;

                           public boolean isJenis_projek_pengambilanSpecified(){
                               return localJenis_projek_pengambilanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getJenis_projek_pengambilan(){
                               return localJenis_projek_pengambilan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Jenis_projek_pengambilan
                               */
                               public void setJenis_projek_pengambilan(java.lang.String param){
                            localJenis_projek_pengambilanTracker = true;
                                   
                                            this.localJenis_projek_pengambilan=param;
                                    

                               }
                            

                        /**
                        * field for KodAgensi
                        */

                        
                                    protected java.lang.String localKodAgensi ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKodAgensiTracker = false ;

                           public boolean isKodAgensiSpecified(){
                               return localKodAgensiTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKodAgensi(){
                               return localKodAgensi;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param KodAgensi
                               */
                               public void setKodAgensi(java.lang.String param){
                            localKodAgensiTracker = true;
                                   
                                            this.localKodAgensi=param;
                                    

                               }
                            

                        /**
                        * field for KodKementerian
                        */

                        
                                    protected java.lang.String localKodKementerian ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKodKementerianTracker = false ;

                           public boolean isKodKementerianSpecified(){
                               return localKodKementerianTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKodKementerian(){
                               return localKodKementerian;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param KodKementerian
                               */
                               public void setKodKementerian(java.lang.String param){
                            localKodKementerianTracker = true;
                                   
                                            this.localKodKementerian=param;
                                    

                               }
                            

                        /**
                        * field for Kod_daerah_pengambilan
                        */

                        
                                    protected java.lang.String localKod_daerah_pengambilan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_daerah_pengambilanTracker = false ;

                           public boolean isKod_daerah_pengambilanSpecified(){
                               return localKod_daerah_pengambilanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_daerah_pengambilan(){
                               return localKod_daerah_pengambilan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_daerah_pengambilan
                               */
                               public void setKod_daerah_pengambilan(java.lang.String param){
                            localKod_daerah_pengambilanTracker = true;
                                   
                                            this.localKod_daerah_pengambilan=param;
                                    

                               }
                            

                        /**
                        * field for Kod_negeri_kjp
                        */

                        
                                    protected java.lang.String localKod_negeri_kjp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_negeri_kjpTracker = false ;

                           public boolean isKod_negeri_kjpSpecified(){
                               return localKod_negeri_kjpTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_negeri_kjp(){
                               return localKod_negeri_kjp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_negeri_kjp
                               */
                               public void setKod_negeri_kjp(java.lang.String param){
                            localKod_negeri_kjpTracker = true;
                                   
                                            this.localKod_negeri_kjp=param;
                                    

                               }
                            

                        /**
                        * field for Kod_negeri_pengambilan
                        */

                        
                                    protected java.lang.String localKod_negeri_pengambilan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKod_negeri_pengambilanTracker = false ;

                           public boolean isKod_negeri_pengambilanSpecified(){
                               return localKod_negeri_pengambilanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKod_negeri_pengambilan(){
                               return localKod_negeri_pengambilan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Kod_negeri_pengambilan
                               */
                               public void setKod_negeri_pengambilan(java.lang.String param){
                            localKod_negeri_pengambilanTracker = true;
                                   
                                            this.localKod_negeri_pengambilan=param;
                                    

                               }
                            

                        /**
                        * field for Nama_agensi
                        */

                        
                                    protected java.lang.String localNama_agensi ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNama_agensiTracker = false ;

                           public boolean isNama_agensiSpecified(){
                               return localNama_agensiTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNama_agensi(){
                               return localNama_agensi;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Nama_agensi
                               */
                               public void setNama_agensi(java.lang.String param){
                            localNama_agensiTracker = true;
                                   
                                            this.localNama_agensi=param;
                                    

                               }
                            

                        /**
                        * field for Nama_daerah_pengambilan
                        */

                        
                                    protected java.lang.String localNama_daerah_pengambilan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNama_daerah_pengambilanTracker = false ;

                           public boolean isNama_daerah_pengambilanSpecified(){
                               return localNama_daerah_pengambilanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNama_daerah_pengambilan(){
                               return localNama_daerah_pengambilan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Nama_daerah_pengambilan
                               */
                               public void setNama_daerah_pengambilan(java.lang.String param){
                            localNama_daerah_pengambilanTracker = true;
                                   
                                            this.localNama_daerah_pengambilan=param;
                                    

                               }
                            

                        /**
                        * field for Nama_kementerian
                        */

                        
                                    protected java.lang.String localNama_kementerian ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNama_kementerianTracker = false ;

                           public boolean isNama_kementerianSpecified(){
                               return localNama_kementerianTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNama_kementerian(){
                               return localNama_kementerian;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Nama_kementerian
                               */
                               public void setNama_kementerian(java.lang.String param){
                            localNama_kementerianTracker = true;
                                   
                                            this.localNama_kementerian=param;
                                    

                               }
                            

                        /**
                        * field for Nama_negeri_pengambilan
                        */

                        
                                    protected java.lang.String localNama_negeri_pengambilan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNama_negeri_pengambilanTracker = false ;

                           public boolean isNama_negeri_pengambilanSpecified(){
                               return localNama_negeri_pengambilanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNama_negeri_pengambilan(){
                               return localNama_negeri_pengambilan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Nama_negeri_pengambilan
                               */
                               public void setNama_negeri_pengambilan(java.lang.String param){
                            localNama_negeri_pengambilanTracker = true;
                                   
                                            this.localNama_negeri_pengambilan=param;
                                    

                               }
                            

                        /**
                        * field for No_fail_jkptg
                        */

                        
                                    protected java.lang.String localNo_fail_jkptg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_fail_jkptgTracker = false ;

                           public boolean isNo_fail_jkptgSpecified(){
                               return localNo_fail_jkptgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_fail_jkptg(){
                               return localNo_fail_jkptg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_fail_jkptg
                               */
                               public void setNo_fail_jkptg(java.lang.String param){
                            localNo_fail_jkptgTracker = true;
                                   
                                            this.localNo_fail_jkptg=param;
                                    

                               }
                            

                        /**
                        * field for No_rujukan_ptd
                        */

                        
                                    protected java.lang.String localNo_rujukan_ptd ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_rujukan_ptdTracker = false ;

                           public boolean isNo_rujukan_ptdSpecified(){
                               return localNo_rujukan_ptdTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_rujukan_ptd(){
                               return localNo_rujukan_ptd;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_rujukan_ptd
                               */
                               public void setNo_rujukan_ptd(java.lang.String param){
                            localNo_rujukan_ptdTracker = true;
                                   
                                            this.localNo_rujukan_ptd=param;
                                    

                               }
                            

                        /**
                        * field for No_rujukan_ptg
                        */

                        
                                    protected java.lang.String localNo_rujukan_ptg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_rujukan_ptgTracker = false ;

                           public boolean isNo_rujukan_ptgSpecified(){
                               return localNo_rujukan_ptgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_rujukan_ptg(){
                               return localNo_rujukan_ptg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_rujukan_ptg
                               */
                               public void setNo_rujukan_ptg(java.lang.String param){
                            localNo_rujukan_ptgTracker = true;
                                   
                                            this.localNo_rujukan_ptg=param;
                                    

                               }
                            

                        /**
                        * field for No_rujukan_surat_kjp
                        */

                        
                                    protected java.lang.String localNo_rujukan_surat_kjp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNo_rujukan_surat_kjpTracker = false ;

                           public boolean isNo_rujukan_surat_kjpSpecified(){
                               return localNo_rujukan_surat_kjpTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNo_rujukan_surat_kjp(){
                               return localNo_rujukan_surat_kjp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param No_rujukan_surat_kjp
                               */
                               public void setNo_rujukan_surat_kjp(java.lang.String param){
                            localNo_rujukan_surat_kjpTracker = true;
                                   
                                            this.localNo_rujukan_surat_kjp=param;
                                    

                               }
                            

                        /**
                        * field for Poskod_kjp
                        */

                        
                                    protected java.lang.String localPoskod_kjp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPoskod_kjpTracker = false ;

                           public boolean isPoskod_kjpSpecified(){
                               return localPoskod_kjpTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPoskod_kjp(){
                               return localPoskod_kjp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Poskod_kjp
                               */
                               public void setPoskod_kjp(java.lang.String param){
                            localPoskod_kjpTracker = true;
                                   
                                            this.localPoskod_kjp=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_permohonan
                        */

                        
                                    protected java.lang.String localTarikh_permohonan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_permohonanTracker = false ;

                           public boolean isTarikh_permohonanSpecified(){
                               return localTarikh_permohonanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_permohonan(){
                               return localTarikh_permohonan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_permohonan
                               */
                               public void setTarikh_permohonan(java.lang.String param){
                            localTarikh_permohonanTracker = true;
                                   
                                            this.localTarikh_permohonan=param;
                                    

                               }
                            

                        /**
                        * field for Tarikh_surat_kjp
                        */

                        
                                    protected java.lang.String localTarikh_surat_kjp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTarikh_surat_kjpTracker = false ;

                           public boolean isTarikh_surat_kjpSpecified(){
                               return localTarikh_surat_kjpTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTarikh_surat_kjp(){
                               return localTarikh_surat_kjp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tarikh_surat_kjp
                               */
                               public void setTarikh_surat_kjp(java.lang.String param){
                            localTarikh_surat_kjpTracker = true;
                                   
                                            this.localTarikh_surat_kjp=param;
                                    

                               }
                            

                        /**
                        * field for Tujuan
                        */

                        
                                    protected java.lang.String localTujuan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTujuanTracker = false ;

                           public boolean isTujuanSpecified(){
                               return localTujuanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTujuan(){
                               return localTujuan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tujuan
                               */
                               public void setTujuan(java.lang.String param){
                            localTujuanTracker = true;
                                   
                                            this.localTujuan=param;
                                    

                               }
                            

                        /**
                        * field for Tujuan_dalam_english
                        */

                        
                                    protected java.lang.String localTujuan_dalam_english ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTujuan_dalam_englishTracker = false ;

                           public boolean isTujuan_dalam_englishSpecified(){
                               return localTujuan_dalam_englishTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTujuan_dalam_english(){
                               return localTujuan_dalam_english;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tujuan_dalam_english
                               */
                               public void setTujuan_dalam_english(java.lang.String param){
                            localTujuan_dalam_englishTracker = true;
                                   
                                            this.localTujuan_dalam_english=param;
                                    

                               }
                            

                        /**
                        * field for Turutan
                        */

                        
                                    protected int localTurutan ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTurutanTracker = false ;

                           public boolean isTurutanSpecified(){
                               return localTurutanTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getTurutan(){
                               return localTurutan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Turutan
                               */
                               public void setTurutan(int param){
                            localTurutanTracker = true;
                                   
                                            this.localTurutan=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Tblintpptmaklumatpengambilan",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Tblintpptmaklumatpengambilan",
                           xmlWriter);
                   }

               
                   }
                if (localAlamat1_kjpTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "alamat1_kjp", xmlWriter);
                             

                                          if (localAlamat1_kjp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAlamat1_kjp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAlamat2_kjpTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "alamat2_kjp", xmlWriter);
                             

                                          if (localAlamat2_kjp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAlamat2_kjp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAlamat3_kjpTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "alamat3_kjp", xmlWriter);
                             

                                          if (localAlamat3_kjp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAlamat3_kjp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFlag_permohonan_segeraTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "flag_permohonan_segera", xmlWriter);
                             

                                          if (localFlag_permohonan_segera==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFlag_permohonan_segera);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFlag_prosesTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "flag_proses", xmlWriter);
                             

                                          if (localFlag_proses==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFlag_proses);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localId_agensi_myetappTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "id_agensi_myetapp", xmlWriter);
                             

                                          if (localId_agensi_myetapp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId_agensi_myetapp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localId_kementerian_myetappTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "id_kementerian_myetapp", xmlWriter);
                             

                                          if (localId_kementerian_myetapp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId_kementerian_myetapp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localJenis_pengambilanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "jenis_pengambilan", xmlWriter);
                             

                                          if (localJenis_pengambilan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localJenis_pengambilan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localJenis_pengambilan_segeraTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "jenis_pengambilan_segera", xmlWriter);
                             

                                          if (localJenis_pengambilan_segera==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localJenis_pengambilan_segera);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localJenis_projek_pengambilanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "jenis_projek_pengambilan", xmlWriter);
                             

                                          if (localJenis_projek_pengambilan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localJenis_projek_pengambilan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKodAgensiTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kodAgensi", xmlWriter);
                             

                                          if (localKodAgensi==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKodAgensi);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKodKementerianTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kodKementerian", xmlWriter);
                             

                                          if (localKodKementerian==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKodKementerian);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_daerah_pengambilanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_daerah_pengambilan", xmlWriter);
                             

                                          if (localKod_daerah_pengambilan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_daerah_pengambilan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_negeri_kjpTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_negeri_kjp", xmlWriter);
                             

                                          if (localKod_negeri_kjp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_negeri_kjp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKod_negeri_pengambilanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "kod_negeri_pengambilan", xmlWriter);
                             

                                          if (localKod_negeri_pengambilan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKod_negeri_pengambilan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNama_agensiTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "nama_agensi", xmlWriter);
                             

                                          if (localNama_agensi==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNama_agensi);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNama_daerah_pengambilanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "nama_daerah_pengambilan", xmlWriter);
                             

                                          if (localNama_daerah_pengambilan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNama_daerah_pengambilan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNama_kementerianTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "nama_kementerian", xmlWriter);
                             

                                          if (localNama_kementerian==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNama_kementerian);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNama_negeri_pengambilanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "nama_negeri_pengambilan", xmlWriter);
                             

                                          if (localNama_negeri_pengambilan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNama_negeri_pengambilan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_fail_jkptgTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_fail_jkptg", xmlWriter);
                             

                                          if (localNo_fail_jkptg==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_fail_jkptg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_rujukan_ptdTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_rujukan_ptd", xmlWriter);
                             

                                          if (localNo_rujukan_ptd==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_rujukan_ptd);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_rujukan_ptgTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_rujukan_ptg", xmlWriter);
                             

                                          if (localNo_rujukan_ptg==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_rujukan_ptg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNo_rujukan_surat_kjpTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "no_rujukan_surat_kjp", xmlWriter);
                             

                                          if (localNo_rujukan_surat_kjp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNo_rujukan_surat_kjp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPoskod_kjpTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "poskod_kjp", xmlWriter);
                             

                                          if (localPoskod_kjp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPoskod_kjp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_permohonanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_permohonan", xmlWriter);
                             

                                          if (localTarikh_permohonan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_permohonan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTarikh_surat_kjpTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tarikh_surat_kjp", xmlWriter);
                             

                                          if (localTarikh_surat_kjp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTarikh_surat_kjp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTujuanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tujuan", xmlWriter);
                             

                                          if (localTujuan==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTujuan);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTujuan_dalam_englishTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "tujuan_dalam_english", xmlWriter);
                             

                                          if (localTujuan_dalam_english==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTujuan_dalam_english);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTurutanTracker){
                                    namespace = "http://ws.etanah/xsd";
                                    writeStartElement(null, namespace, "turutan", xmlWriter);
                             
                                               if (localTurutan==java.lang.Integer.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localAlamat1_kjpTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "alamat1_kjp"));
                                 
                                         elementList.add(localAlamat1_kjp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAlamat1_kjp));
                                    } if (localAlamat2_kjpTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "alamat2_kjp"));
                                 
                                         elementList.add(localAlamat2_kjp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAlamat2_kjp));
                                    } if (localAlamat3_kjpTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "alamat3_kjp"));
                                 
                                         elementList.add(localAlamat3_kjp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAlamat3_kjp));
                                    } if (localFlag_permohonan_segeraTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "flag_permohonan_segera"));
                                 
                                         elementList.add(localFlag_permohonan_segera==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag_permohonan_segera));
                                    } if (localFlag_prosesTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "flag_proses"));
                                 
                                         elementList.add(localFlag_proses==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag_proses));
                                    } if (localId_agensi_myetappTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "id_agensi_myetapp"));
                                 
                                         elementList.add(localId_agensi_myetapp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId_agensi_myetapp));
                                    } if (localId_kementerian_myetappTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "id_kementerian_myetapp"));
                                 
                                         elementList.add(localId_kementerian_myetapp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId_kementerian_myetapp));
                                    } if (localJenis_pengambilanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "jenis_pengambilan"));
                                 
                                         elementList.add(localJenis_pengambilan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localJenis_pengambilan));
                                    } if (localJenis_pengambilan_segeraTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "jenis_pengambilan_segera"));
                                 
                                         elementList.add(localJenis_pengambilan_segera==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localJenis_pengambilan_segera));
                                    } if (localJenis_projek_pengambilanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "jenis_projek_pengambilan"));
                                 
                                         elementList.add(localJenis_projek_pengambilan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localJenis_projek_pengambilan));
                                    } if (localKodAgensiTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kodAgensi"));
                                 
                                         elementList.add(localKodAgensi==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKodAgensi));
                                    } if (localKodKementerianTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kodKementerian"));
                                 
                                         elementList.add(localKodKementerian==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKodKementerian));
                                    } if (localKod_daerah_pengambilanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_daerah_pengambilan"));
                                 
                                         elementList.add(localKod_daerah_pengambilan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_daerah_pengambilan));
                                    } if (localKod_negeri_kjpTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_negeri_kjp"));
                                 
                                         elementList.add(localKod_negeri_kjp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_negeri_kjp));
                                    } if (localKod_negeri_pengambilanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "kod_negeri_pengambilan"));
                                 
                                         elementList.add(localKod_negeri_pengambilan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKod_negeri_pengambilan));
                                    } if (localNama_agensiTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "nama_agensi"));
                                 
                                         elementList.add(localNama_agensi==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNama_agensi));
                                    } if (localNama_daerah_pengambilanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "nama_daerah_pengambilan"));
                                 
                                         elementList.add(localNama_daerah_pengambilan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNama_daerah_pengambilan));
                                    } if (localNama_kementerianTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "nama_kementerian"));
                                 
                                         elementList.add(localNama_kementerian==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNama_kementerian));
                                    } if (localNama_negeri_pengambilanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "nama_negeri_pengambilan"));
                                 
                                         elementList.add(localNama_negeri_pengambilan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNama_negeri_pengambilan));
                                    } if (localNo_fail_jkptgTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_fail_jkptg"));
                                 
                                         elementList.add(localNo_fail_jkptg==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_fail_jkptg));
                                    } if (localNo_rujukan_ptdTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_rujukan_ptd"));
                                 
                                         elementList.add(localNo_rujukan_ptd==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_rujukan_ptd));
                                    } if (localNo_rujukan_ptgTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_rujukan_ptg"));
                                 
                                         elementList.add(localNo_rujukan_ptg==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_rujukan_ptg));
                                    } if (localNo_rujukan_surat_kjpTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "no_rujukan_surat_kjp"));
                                 
                                         elementList.add(localNo_rujukan_surat_kjp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNo_rujukan_surat_kjp));
                                    } if (localPoskod_kjpTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "poskod_kjp"));
                                 
                                         elementList.add(localPoskod_kjp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPoskod_kjp));
                                    } if (localTarikh_permohonanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_permohonan"));
                                 
                                         elementList.add(localTarikh_permohonan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_permohonan));
                                    } if (localTarikh_surat_kjpTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tarikh_surat_kjp"));
                                 
                                         elementList.add(localTarikh_surat_kjp==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTarikh_surat_kjp));
                                    } if (localTujuanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tujuan"));
                                 
                                         elementList.add(localTujuan==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTujuan));
                                    } if (localTujuan_dalam_englishTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "tujuan_dalam_english"));
                                 
                                         elementList.add(localTujuan_dalam_english==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTujuan_dalam_english));
                                    } if (localTurutanTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah/xsd",
                                                                      "turutan"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTurutan));
                            }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Tblintpptmaklumatpengambilan parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Tblintpptmaklumatpengambilan object =
                new Tblintpptmaklumatpengambilan();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Tblintpptmaklumatpengambilan".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Tblintpptmaklumatpengambilan)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","alamat1_kjp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAlamat1_kjp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","alamat2_kjp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAlamat2_kjp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","alamat3_kjp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAlamat3_kjp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","flag_permohonan_segera").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlag_permohonan_segera(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","flag_proses").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlag_proses(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","id_agensi_myetapp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId_agensi_myetapp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","id_kementerian_myetapp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId_kementerian_myetapp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","jenis_pengambilan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setJenis_pengambilan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","jenis_pengambilan_segera").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setJenis_pengambilan_segera(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","jenis_projek_pengambilan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setJenis_projek_pengambilan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kodAgensi").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKodAgensi(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kodKementerian").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKodKementerian(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_daerah_pengambilan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_daerah_pengambilan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_negeri_kjp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_negeri_kjp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","kod_negeri_pengambilan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKod_negeri_pengambilan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","nama_agensi").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNama_agensi(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","nama_daerah_pengambilan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNama_daerah_pengambilan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","nama_kementerian").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNama_kementerian(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","nama_negeri_pengambilan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNama_negeri_pengambilan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_fail_jkptg").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_fail_jkptg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_rujukan_ptd").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_rujukan_ptd(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_rujukan_ptg").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_rujukan_ptg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","no_rujukan_surat_kjp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNo_rujukan_surat_kjp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","poskod_kjp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPoskod_kjp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_permohonan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_permohonan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tarikh_surat_kjp").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTarikh_surat_kjp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tujuan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTujuan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","tujuan_dalam_english").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTujuan_dalam_english(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah/xsd","turutan").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTurutan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setTurutan(java.lang.Integer.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setTurutan(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class Exception
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Exception
                Namespace URI = http://ws.etanah
                Namespace Prefix = ns2
                */
            

                        /**
                        * field for Message
                        */

                        
                                    protected java.lang.String localMessage ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMessageTracker = false ;

                           public boolean isMessageSpecified(){
                               return localMessageTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMessage(){
                               return localMessage;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Message
                               */
                               public void setMessage(java.lang.String param){
                            localMessageTracker = true;
                                   
                                            this.localMessage=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://ws.etanah");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Exception",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Exception",
                           xmlWriter);
                   }

               
                   }
                if (localMessageTracker){
                                    namespace = "http://ws.etanah";
                                    writeStartElement(null, namespace, "Message", xmlWriter);
                             

                                          if (localMessage==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMessage);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://ws.etanah")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localMessageTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://ws.etanah",
                                                                      "Message"));
                                 
                                         elementList.add(localMessage==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMessage));
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Exception parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Exception object =
                new Exception();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Exception".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Exception)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://ws.etanah","Message").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMessage(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
            private  org.apache.axiom.om.OMElement  toOM(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatWarta_byObject param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatWarta_byObject.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertHakmilikList_byObject param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertHakmilikList_byObject.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMK_byObject param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMK_byObject.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatPengambilan_byObject param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatPengambilan_byObject.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMKTajuk_byObject param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMKTajuk_byObject.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDokumen_byObject param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDokumen_byObject.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatWarta_byObject param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatWarta_byObject.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertHakmilikList_byObject param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertHakmilikList_byObject.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMK_byObject param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMK_byObject.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatPengambilan_byObject param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatPengambilan_byObject.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMKTajuk_byObject param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMKTajuk_byObject.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDokumen_byObject param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDokumen_byObject.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatWarta_byObject.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatWarta_byObject.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertHakmilikList_byObject.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertHakmilikList_byObject.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMK_byObject.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMK_byObject.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatPengambilan_byObject.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertMaklumatPengambilan_byObject.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMKTajuk_byObject.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDerafMMKTajuk_byObject.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDokumen_byObject.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.InsertDokumen_byObject.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.class.equals(type)){
                
                           return integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    
   }
   