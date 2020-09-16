/**
 * DataCreateReqTypePartyAgency.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class DataCreateReqTypePartyAgency  implements java.io.Serializable {
    private java.lang.String partyAgencyTypeID;

    private java.lang.String partyAgencyName;

    private java.lang.String partyAgencyIDType;

    private java.lang.String partyAgencyIDNo;

    private my.gov.kehakiman.eip.services.DataCreateReqTypePartyAgencyPartyCounsel partyCounsel;

    public DataCreateReqTypePartyAgency() {
    }

    public DataCreateReqTypePartyAgency(
           java.lang.String partyAgencyTypeID,
           java.lang.String partyAgencyName,
           java.lang.String partyAgencyIDType,
           java.lang.String partyAgencyIDNo,
           my.gov.kehakiman.eip.services.DataCreateReqTypePartyAgencyPartyCounsel partyCounsel) {
           this.partyAgencyTypeID = partyAgencyTypeID;
           this.partyAgencyName = partyAgencyName;
           this.partyAgencyIDType = partyAgencyIDType;
           this.partyAgencyIDNo = partyAgencyIDNo;
           this.partyCounsel = partyCounsel;
    }


    /**
     * Gets the partyAgencyTypeID value for this DataCreateReqTypePartyAgency.
     * 
     * @return partyAgencyTypeID
     */
    public java.lang.String getPartyAgencyTypeID() {
        return partyAgencyTypeID;
    }


    /**
     * Sets the partyAgencyTypeID value for this DataCreateReqTypePartyAgency.
     * 
     * @param partyAgencyTypeID
     */
    public void setPartyAgencyTypeID(java.lang.String partyAgencyTypeID) {
        this.partyAgencyTypeID = partyAgencyTypeID;
    }


    /**
     * Gets the partyAgencyName value for this DataCreateReqTypePartyAgency.
     * 
     * @return partyAgencyName
     */
    public java.lang.String getPartyAgencyName() {
        return partyAgencyName;
    }


    /**
     * Sets the partyAgencyName value for this DataCreateReqTypePartyAgency.
     * 
     * @param partyAgencyName
     */
    public void setPartyAgencyName(java.lang.String partyAgencyName) {
        this.partyAgencyName = partyAgencyName;
    }


    /**
     * Gets the partyAgencyIDType value for this DataCreateReqTypePartyAgency.
     * 
     * @return partyAgencyIDType
     */
    public java.lang.String getPartyAgencyIDType() {
        return partyAgencyIDType;
    }


    /**
     * Sets the partyAgencyIDType value for this DataCreateReqTypePartyAgency.
     * 
     * @param partyAgencyIDType
     */
    public void setPartyAgencyIDType(java.lang.String partyAgencyIDType) {
        this.partyAgencyIDType = partyAgencyIDType;
    }


    /**
     * Gets the partyAgencyIDNo value for this DataCreateReqTypePartyAgency.
     * 
     * @return partyAgencyIDNo
     */
    public java.lang.String getPartyAgencyIDNo() {
        return partyAgencyIDNo;
    }


    /**
     * Sets the partyAgencyIDNo value for this DataCreateReqTypePartyAgency.
     * 
     * @param partyAgencyIDNo
     */
    public void setPartyAgencyIDNo(java.lang.String partyAgencyIDNo) {
        this.partyAgencyIDNo = partyAgencyIDNo;
    }


    /**
     * Gets the partyCounsel value for this DataCreateReqTypePartyAgency.
     * 
     * @return partyCounsel
     */
    public my.gov.kehakiman.eip.services.DataCreateReqTypePartyAgencyPartyCounsel getPartyCounsel() {
        return partyCounsel;
    }


    /**
     * Sets the partyCounsel value for this DataCreateReqTypePartyAgency.
     * 
     * @param partyCounsel
     */
    public void setPartyCounsel(my.gov.kehakiman.eip.services.DataCreateReqTypePartyAgencyPartyCounsel partyCounsel) {
        this.partyCounsel = partyCounsel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataCreateReqTypePartyAgency)) return false;
        DataCreateReqTypePartyAgency other = (DataCreateReqTypePartyAgency) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.partyAgencyTypeID==null && other.getPartyAgencyTypeID()==null) || 
             (this.partyAgencyTypeID!=null &&
              this.partyAgencyTypeID.equals(other.getPartyAgencyTypeID()))) &&
            ((this.partyAgencyName==null && other.getPartyAgencyName()==null) || 
             (this.partyAgencyName!=null &&
              this.partyAgencyName.equals(other.getPartyAgencyName()))) &&
            ((this.partyAgencyIDType==null && other.getPartyAgencyIDType()==null) || 
             (this.partyAgencyIDType!=null &&
              this.partyAgencyIDType.equals(other.getPartyAgencyIDType()))) &&
            ((this.partyAgencyIDNo==null && other.getPartyAgencyIDNo()==null) || 
             (this.partyAgencyIDNo!=null &&
              this.partyAgencyIDNo.equals(other.getPartyAgencyIDNo()))) &&
            ((this.partyCounsel==null && other.getPartyCounsel()==null) || 
             (this.partyCounsel!=null &&
              this.partyCounsel.equals(other.getPartyCounsel())));
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
        if (getPartyAgencyTypeID() != null) {
            _hashCode += getPartyAgencyTypeID().hashCode();
        }
        if (getPartyAgencyName() != null) {
            _hashCode += getPartyAgencyName().hashCode();
        }
        if (getPartyAgencyIDType() != null) {
            _hashCode += getPartyAgencyIDType().hashCode();
        }
        if (getPartyAgencyIDNo() != null) {
            _hashCode += getPartyAgencyIDNo().hashCode();
        }
        if (getPartyCounsel() != null) {
            _hashCode += getPartyCounsel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataCreateReqTypePartyAgency.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">dataCreateReqType>PartyAgency"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyAgencyTypeID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyAgencyTypeID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyAgencyName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyAgencyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyAgencyIDType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyAgencyIDType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyAgencyIDNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyAgencyIDNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyCounsel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyCounsel"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">>dataCreateReqType>PartyAgency>PartyCounsel"));
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
