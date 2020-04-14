
/**
 * EtappHasilPersekutuanServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package integrasi.ws.etanah.melaka_ns.htp;

    /**
     *  EtappHasilPersekutuanServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EtappHasilPersekutuanServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EtappHasilPersekutuanServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EtappHasilPersekutuanServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for maklumatBayaranByHakmilik method
            * override this method for handling normal response from maklumatBayaranByHakmilik operation
            */
           public void receiveResultmaklumatBayaranByHakmilik(
                    integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatBayaranByHakmilikResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from maklumatBayaranByHakmilik operation
           */
            public void receiveErrormaklumatBayaranByHakmilik(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateHakmilikAgensi method
            * override this method for handling normal response from updateHakmilikAgensi operation
            */
           public void receiveResultupdateHakmilikAgensi(
                    integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.UpdateHakmilikAgensiResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateHakmilikAgensi operation
           */
            public void receiveErrorupdateHakmilikAgensi(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for maklumatCukaiByHakmilik method
            * override this method for handling normal response from maklumatCukaiByHakmilik operation
            */
           public void receiveResultmaklumatCukaiByHakmilik(
                    integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatCukaiByHakmilikResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from maklumatCukaiByHakmilik operation
           */
            public void receiveErrormaklumatCukaiByHakmilik(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for maklumatHakmilik method
            * override this method for handling normal response from maklumatHakmilik operation
            */
           public void receiveResultmaklumatHakmilik(
                    integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatHakmilikResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from maklumatHakmilik operation
           */
            public void receiveErrormaklumatHakmilik(java.lang.Exception e) {
            }
                


    }
    