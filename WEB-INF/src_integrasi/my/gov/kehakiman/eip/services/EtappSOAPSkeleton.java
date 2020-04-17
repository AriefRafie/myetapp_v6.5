/**
 * EtappSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class EtappSOAPSkeleton implements my.gov.kehakiman.eip.services.Etapp_PortType, org.apache.axis.wsdl.Skeleton {
    private my.gov.kehakiman.eip.services.Etapp_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "submitApplicationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">submitApplicationRequest"), my.gov.kehakiman.eip.services.SubmitApplicationRequest.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("submitApplication", _params, new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "submitApplicationResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">submitApplicationResponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "submitApplication"));
        _oper.setSoapAction("https://eip.kehakiman.gov.my/services/submitApplication");
        _myOperationsList.add(_oper);
        if (_myOperations.get("submitApplication") == null) {
            _myOperations.put("submitApplication", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("submitApplication")).add(_oper);
    }

    public EtappSOAPSkeleton() {
        this.impl = new my.gov.kehakiman.eip.services.EtappSOAPImpl();
    }

    public EtappSOAPSkeleton(my.gov.kehakiman.eip.services.Etapp_PortType impl) {
        this.impl = impl;
    }
    public my.gov.kehakiman.eip.services.SubmitApplicationResponse submitApplication(my.gov.kehakiman.eip.services.SubmitApplicationRequest parameters) throws java.rmi.RemoteException
    {
        my.gov.kehakiman.eip.services.SubmitApplicationResponse ret = impl.submitApplication(parameters);
        return ret;
    }

}
