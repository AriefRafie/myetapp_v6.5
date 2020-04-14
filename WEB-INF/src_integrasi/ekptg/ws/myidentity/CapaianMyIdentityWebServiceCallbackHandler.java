
/**
 * CapaianMyIdentityWebServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */

    package ekptg.ws.myidentity;

    /**
     *  CapaianMyIdentityWebServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CapaianMyIdentityWebServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CapaianMyIdentityWebServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CapaianMyIdentityWebServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getNoKadPengenalan method
            * override this method for handling normal response from getNoKadPengenalan operation
            */
           public void receiveResultgetNoKadPengenalan(
                    ekptg.ws.myidentity.CapaianMyIdentityWebServiceStub.GetNoKadPengenalanResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getNoKadPengenalan operation
           */
            public void receiveErrorgetNoKadPengenalan(java.lang.Exception e) {
            }
                


    }
    