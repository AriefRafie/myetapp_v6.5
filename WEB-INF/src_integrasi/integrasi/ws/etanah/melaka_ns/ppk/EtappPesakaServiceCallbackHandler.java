
/**
 * EtappPesakaServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package integrasi.ws.etanah.melaka_ns.ppk;

    /**
     *  EtappPesakaServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EtappPesakaServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EtappPesakaServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EtappPesakaServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getHakmilikByCarianRasmi method
            * override this method for handling normal response from getHakmilikByCarianRasmi operation
            */
           public void receiveResultgetHakmilikByCarianRasmi(
                    integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getHakmilikByCarianRasmi operation
           */
            public void receiveErrorgetHakmilikByCarianRasmi(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertMaklumatPerintah method
            * override this method for handling normal response from insertMaklumatPerintah operation
            */
           public void receiveResultinsertMaklumatPerintah(
                    integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.InsertMaklumatPerintahResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertMaklumatPerintah operation
           */
            public void receiveErrorinsertMaklumatPerintah(java.lang.Exception e) {
            }
                


    }
    