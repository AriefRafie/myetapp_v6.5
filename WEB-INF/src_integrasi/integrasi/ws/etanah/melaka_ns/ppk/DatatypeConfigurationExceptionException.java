
/**
 * DatatypeConfigurationExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package integrasi.ws.etanah.melaka_ns.ppk;

public class DatatypeConfigurationExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1396513031699L;
    
    private integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.DatatypeConfigurationExceptionE faultMessage;

    
        public DatatypeConfigurationExceptionException() {
            super("DatatypeConfigurationExceptionException");
        }

        public DatatypeConfigurationExceptionException(java.lang.String s) {
           super(s);
        }

        public DatatypeConfigurationExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public DatatypeConfigurationExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.DatatypeConfigurationExceptionE msg){
       faultMessage = msg;
    }
    
    public integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.DatatypeConfigurationExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    