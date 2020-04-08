
/**
 * IntegrationHakmilikPengambilanExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package integrasi.ws.etanah.melaka_ns.ppt;

public class IntegrationHakmilikPengambilanExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1390797182417L;
    
    private integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException faultMessage;

    
        public IntegrationHakmilikPengambilanExceptionException() {
            super("IntegrationHakmilikPengambilanExceptionException");
        }

        public IntegrationHakmilikPengambilanExceptionException(java.lang.String s) {
           super(s);
        }

        public IntegrationHakmilikPengambilanExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public IntegrationHakmilikPengambilanExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException msg){
       faultMessage = msg;
    }
    
    public integrasi.ws.etanah.melaka_ns.ppt.IntegrationHakmilikPengambilanStub.IntegrationHakmilikPengambilanException getFaultMessage(){
       return faultMessage;
    }
}
    