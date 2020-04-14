
/**
 * ParseExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package integrasi.ws.etanah.melaka_ns.ppk;

public class ParseExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1396513031711L;
    
    private integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.ParseExceptionE faultMessage;

    
        public ParseExceptionException() {
            super("ParseExceptionException");
        }

        public ParseExceptionException(java.lang.String s) {
           super(s);
        }

        public ParseExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ParseExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.ParseExceptionE msg){
       faultMessage = msg;
    }
    
    public integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.ParseExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    