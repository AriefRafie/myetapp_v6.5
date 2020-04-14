

/**
 * Etapp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:31 BST)
 */

    package integrasi.ws.mt;

    /*
     *  Etapp java interface
     */

    public interface Etapp {
          

        /**
          * Auto generated method signature
          * 
                    * @param submitApplicationRequest0
                
         */

         
                     public integrasi.ws.mt.SubmitApplicationResponse submitApplication(

                        integrasi.ws.mt.SubmitApplicationRequest submitApplicationRequest0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param submitApplicationRequest0
            
          */
        public void startsubmitApplication(

            integrasi.ws.mt.SubmitApplicationRequest submitApplicationRequest0,

            final integrasi.ws.mt.EtappCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    