/**
 * DataCreateReqTypePartyAgencyPartyCounsel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class DataCreateReqTypePartyAgencyPartyCounsel  implements java.io.Serializable {
    private java.lang.String partyCounselId;

    public DataCreateReqTypePartyAgencyPartyCounsel() {
    }

    public DataCreateReqTypePartyAgencyPartyCounsel(
           java.lang.String partyCounselId) {
           this.partyCounselId = partyCounselId;
    }


    /**
     * Gets the partyCounselId value for this DataCreateReqTypePartyAgencyPartyCounsel.
     * 
     * @return partyCounselId
     */
    public java.lang.String getPartyCounselId() {
        return partyCounselId;
    }


    /**
     * Sets the partyCounselId value for this DataCreateReqTypePartyAgencyPartyCounsel.
     * 
     * @param partyCounselId
     */
    public void setPartyCounselId(java.lang.String partyCounselId) {
        this.partyCounselId = partyCounselId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataCreateReqTypePartyAgencyPartyCounsel)) return false;
        DataCreateReqTypePartyAgencyPartyCounsel other = (DataCreateReqTypePartyAgencyPartyCounsel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.partyCounselId==null && other.getPartyCounselId()==null) || 
             (this.partyCounselId!=null &&
              this.partyCounselId.equals(other.getPartyCounselId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getPartyCounselId() != null) {
            _hashCode += getPartyCounselId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataCreateReqTypePartyAgencyPartyCounsel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">>dataCreateReqType>PartyAgency>PartyCounsel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyCounselId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyCounselId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
