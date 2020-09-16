/**
 * CauseofactionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class CauseofactionType  implements java.io.Serializable {
    /* Unique identifier for each of the cause of Action */
    private java.lang.String causeOfActionRefID;

    /* Cause of Action ID as per Court Value
     * Refer to appendix Cause of Action Code */
    private java.lang.String causeOfActionID;

    /* Cause of Action Description
     * Refer to appendix Cause of Action Code */
    private java.lang.String causeOfActionDesc;

    public CauseofactionType() {
    }

    public CauseofactionType(
           java.lang.String causeOfActionRefID,
           java.lang.String causeOfActionID,
           java.lang.String causeOfActionDesc) {
           this.causeOfActionRefID = causeOfActionRefID;
           this.causeOfActionID = causeOfActionID;
           this.causeOfActionDesc = causeOfActionDesc;
    }


    /**
     * Gets the causeOfActionRefID value for this CauseofactionType.
     * 
     * @return causeOfActionRefID   * Unique identifier for each of the cause of Action
     */
    public java.lang.String getCauseOfActionRefID() {
        return causeOfActionRefID;
    }


    /**
     * Sets the causeOfActionRefID value for this CauseofactionType.
     * 
     * @param causeOfActionRefID   * Unique identifier for each of the cause of Action
     */
    public void setCauseOfActionRefID(java.lang.String causeOfActionRefID) {
        this.causeOfActionRefID = causeOfActionRefID;
    }


    /**
     * Gets the causeOfActionID value for this CauseofactionType.
     * 
     * @return causeOfActionID   * Cause of Action ID as per Court Value
     * Refer to appendix Cause of Action Code
     */
    public java.lang.String getCauseOfActionID() {
        return causeOfActionID;
    }


    /**
     * Sets the causeOfActionID value for this CauseofactionType.
     * 
     * @param causeOfActionID   * Cause of Action ID as per Court Value
     * Refer to appendix Cause of Action Code
     */
    public void setCauseOfActionID(java.lang.String causeOfActionID) {
        this.causeOfActionID = causeOfActionID;
    }


    /**
     * Gets the causeOfActionDesc value for this CauseofactionType.
     * 
     * @return causeOfActionDesc   * Cause of Action Description
     * Refer to appendix Cause of Action Code
     */
    public java.lang.String getCauseOfActionDesc() {
        return causeOfActionDesc;
    }


    /**
     * Sets the causeOfActionDesc value for this CauseofactionType.
     * 
     * @param causeOfActionDesc   * Cause of Action Description
     * Refer to appendix Cause of Action Code
     */
    public void setCauseOfActionDesc(java.lang.String causeOfActionDesc) {
        this.causeOfActionDesc = causeOfActionDesc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CauseofactionType)) return false;
        CauseofactionType other = (CauseofactionType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.causeOfActionRefID==null && other.getCauseOfActionRefID()==null) || 
             (this.causeOfActionRefID!=null &&
              this.causeOfActionRefID.equals(other.getCauseOfActionRefID()))) &&
            ((this.causeOfActionID==null && other.getCauseOfActionID()==null) || 
             (this.causeOfActionID!=null &&
              this.causeOfActionID.equals(other.getCauseOfActionID()))) &&
            ((this.causeOfActionDesc==null && other.getCauseOfActionDesc()==null) || 
             (this.causeOfActionDesc!=null &&
              this.causeOfActionDesc.equals(other.getCauseOfActionDesc())));
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
        if (getCauseOfActionRefID() != null) {
            _hashCode += getCauseOfActionRefID().hashCode();
        }
        if (getCauseOfActionID() != null) {
            _hashCode += getCauseOfActionID().hashCode();
        }
        if (getCauseOfActionDesc() != null) {
            _hashCode += getCauseOfActionDesc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CauseofactionType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "causeofactionType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("causeOfActionRefID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CauseOfActionRefID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("causeOfActionID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CauseOfActionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("causeOfActionDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CauseOfActionDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
