/**
 * CivilRegisterCaseSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class CivilRegisterCaseSOAPSkeleton implements my.gov.kehakiman.eip.services.CivilRegisterCase_PortType, org.apache.axis.wsdl.Skeleton {
    private my.gov.kehakiman.eip.services.CivilRegisterCase_PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "civilregistercaserequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">civilregistercaserequest"), my.gov.kehakiman.eip.services.Civilregistercaserequest.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("civilRegisterCase", _params, new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "civilregistercaseresponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">civilregistercaseresponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "CivilRegisterCase"));
       // _oper.setSoapAction("http://www.example.org/SIT_CivilRegisterCase/CivilRegiterCase");
        _oper.setSoapAction("https://eip.kehakiman.gov.my/services/CivilRegiterCase");
        _myOperationsList.add(_oper);
        if (_myOperations.get("civilRegisterCase") == null) {
            _myOperations.put("civilRegisterCase", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("civilRegisterCase")).add(_oper);
    }

    public CivilRegisterCaseSOAPSkeleton() {
        this.impl = new my.gov.kehakiman.eip.services.CivilRegisterCaseSOAPImpl();
    }

    public CivilRegisterCaseSOAPSkeleton(my.gov.kehakiman.eip.services.CivilRegisterCase_PortType impl) {
        this.impl = impl;
    }
    public my.gov.kehakiman.eip.services.Civilregistercaseresponse civilRegisterCase(my.gov.kehakiman.eip.services.Civilregistercaserequest parameters) throws java.rmi.RemoteException
    {
        my.gov.kehakiman.eip.services.Civilregistercaseresponse ret = impl.civilRegisterCase(parameters);
        return ret;
    }

}
