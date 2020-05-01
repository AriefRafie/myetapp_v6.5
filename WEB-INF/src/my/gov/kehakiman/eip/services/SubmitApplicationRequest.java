/**
 * SubmitApplicationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class SubmitApplicationRequest  implements java.io.Serializable {
    /* Fixed Value: “ETP and CMS” */
    private my.gov.kehakiman.eip.services.SubmitApplicationRequestSource source;

    /* This date should be constructed upon invoking this interface.
     * Format: YYYYMMDDhh24mmss */
    private java.lang.String transactionID;

    /* To be given by the service provider. */
    private java.lang.String username;

    /* To be given by the service provider. */
    private java.lang.String password;

    /* Fixed Value: "MyeTaPPApplication" */
    private my.gov.kehakiman.eip.services.SubmitApplicationRequestEventName eventName;

    private my.gov.kehakiman.eip.services.NewType data;

    public SubmitApplicationRequest() {
    }

    public SubmitApplicationRequest(
           my.gov.kehakiman.eip.services.SubmitApplicationRequestSource source,
           java.lang.String transactionID,
           java.lang.String username,
           java.lang.String password,
           my.gov.kehakiman.eip.services.SubmitApplicationRequestEventName eventName,
           my.gov.kehakiman.eip.services.NewType data) {
           this.source = source;
           this.transactionID = transactionID;
           this.username = username;
           this.password = password;
           this.eventName = eventName;
           this.data = data;
    }


    /**
     * Gets the source value for this SubmitApplicationRequest.
     * 
     * @return source   * Fixed Value: “ETP and CMS”
     */
    public my.gov.kehakiman.eip.services.SubmitApplicationRequestSource getSource() {
        return source;
    }


    /**
     * Sets the source value for this SubmitApplicationRequest.
     * 
     * @param source   * Fixed Value: “ETP and CMS”
     */
    public void setSource(my.gov.kehakiman.eip.services.SubmitApplicationRequestSource source) {
        this.source = source;
    }


    /**
     * Gets the transactionID value for this SubmitApplicationRequest.
     * 
     * @return transactionID   * This date should be constructed upon invoking this interface.
     * Format: YYYYMMDDhh24mmss
     */
    public java.lang.String getTransactionID() {
        return transactionID;
    }


    /**
     * Sets the transactionID value for this SubmitApplicationRequest.
     * 
     * @param transactionID   * This date should be constructed upon invoking this interface.
     * Format: YYYYMMDDhh24mmss
     */
    public void setTransactionID(java.lang.String transactionID) {
        this.transactionID = transactionID;
    }


    /**
     * Gets the username value for this SubmitApplicationRequest.
     * 
     * @return username   * To be given by the service provider.
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this SubmitApplicationRequest.
     * 
     * @param username   * To be given by the service provider.
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this SubmitApplicationRequest.
     * 
     * @return password   * To be given by the service provider.
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this SubmitApplicationRequest.
     * 
     * @param password   * To be given by the service provider.
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the eventName value for this SubmitApplicationRequest.
     * 
     * @return eventName   * Fixed Value: "MyeTaPPApplication"
     */
    public my.gov.kehakiman.eip.services.SubmitApplicationRequestEventName getEventName() {
        return eventName;
    }


    /**
     * Sets the eventName value for this SubmitApplicationRequest.
     * 
     * @param eventName   * Fixed Value: "MyeTaPPApplication"
     */
    public void setEventName(my.gov.kehakiman.eip.services.SubmitApplicationRequestEventName eventName) {
        this.eventName = eventName;
    }


    /**
     * Gets the data value for this SubmitApplicationRequest.
     * 
     * @return data
     */
    public my.gov.kehakiman.eip.services.NewType getData() {
        return data;
    }


    /**
     * Sets the data value for this SubmitApplicationRequest.
     * 
     * @param data
     */
    public void setData(my.gov.kehakiman.eip.services.NewType data) {
        this.data = data;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubmitApplicationRequest)) return false;
        SubmitApplicationRequest other = (SubmitApplicationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              this.source.equals(other.getSource()))) &&
            ((this.transactionID==null && other.getTransactionID()==null) || 
             (this.transactionID!=null &&
              this.transactionID.equals(other.getTransactionID()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.eventName==null && other.getEventName()==null) || 
             (this.eventName!=null &&
              this.eventName.equals(other.getEventName()))) &&
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              this.data.equals(other.getData())));
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
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        if (getTransactionID() != null) {
            _hashCode += getTransactionID().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getEventName() != null) {
            _hashCode += getEventName().hashCode();
        }
        if (getData() != null) {
            _hashCode += getData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubmitApplicationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">submitApplicationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("", "source"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">>submitApplicationRequest>source"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eventName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "eventName"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">>submitApplicationRequest>eventName"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "NewType"));
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
