/**
 * DataCreateRepType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class DataCreateRepType  implements java.io.Serializable {
    /* Source Reference No. */
    private java.lang.String sourceReferenceNo;

    /* Case No. Unique identifier in CMS
     *         						system. */
    private java.lang.String caseNo;

    /* Mention Date */
    private java.lang.String mentionDateTime;

    /* Court Room */
    private java.lang.String courtRoom;

    public DataCreateRepType() {
    }

    public DataCreateRepType(
           java.lang.String sourceReferenceNo,
           java.lang.String caseNo,
           java.lang.String mentionDateTime,
           java.lang.String courtRoom) {
           this.sourceReferenceNo = sourceReferenceNo;
           this.caseNo = caseNo;
           this.mentionDateTime = mentionDateTime;
           this.courtRoom = courtRoom;
    }


    /**
     * Gets the sourceReferenceNo value for this DataCreateRepType.
     * 
     * @return sourceReferenceNo   * Source Reference No.
     */
    public java.lang.String getSourceReferenceNo() {
        return sourceReferenceNo;
    }


    /**
     * Sets the sourceReferenceNo value for this DataCreateRepType.
     * 
     * @param sourceReferenceNo   * Source Reference No.
     */
    public void setSourceReferenceNo(java.lang.String sourceReferenceNo) {
        this.sourceReferenceNo = sourceReferenceNo;
    }


    /**
     * Gets the caseNo value for this DataCreateRepType.
     * 
     * @return caseNo   * Case No. Unique identifier in CMS
     *         						system.
     */
    public java.lang.String getCaseNo() {
        return caseNo;
    }


    /**
     * Sets the caseNo value for this DataCreateRepType.
     * 
     * @param caseNo   * Case No. Unique identifier in CMS
     *         						system.
     */
    public void setCaseNo(java.lang.String caseNo) {
        this.caseNo = caseNo;
    }


    /**
     * Gets the mentionDateTime value for this DataCreateRepType.
     * 
     * @return mentionDateTime   * Mention Date
     */
    public java.lang.String getMentionDateTime() {
        return mentionDateTime;
    }


    /**
     * Sets the mentionDateTime value for this DataCreateRepType.
     * 
     * @param mentionDateTime   * Mention Date
     */
    public void setMentionDateTime(java.lang.String mentionDateTime) {
        this.mentionDateTime = mentionDateTime;
    }


    /**
     * Gets the courtRoom value for this DataCreateRepType.
     * 
     * @return courtRoom   * Court Room
     */
    public java.lang.String getCourtRoom() {
        return courtRoom;
    }


    /**
     * Sets the courtRoom value for this DataCreateRepType.
     * 
     * @param courtRoom   * Court Room
     */
    public void setCourtRoom(java.lang.String courtRoom) {
        this.courtRoom = courtRoom;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataCreateRepType)) return false;
        DataCreateRepType other = (DataCreateRepType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sourceReferenceNo==null && other.getSourceReferenceNo()==null) || 
             (this.sourceReferenceNo!=null &&
              this.sourceReferenceNo.equals(other.getSourceReferenceNo()))) &&
            ((this.caseNo==null && other.getCaseNo()==null) || 
             (this.caseNo!=null &&
              this.caseNo.equals(other.getCaseNo()))) &&
            ((this.mentionDateTime==null && other.getMentionDateTime()==null) || 
             (this.mentionDateTime!=null &&
              this.mentionDateTime.equals(other.getMentionDateTime()))) &&
            ((this.courtRoom==null && other.getCourtRoom()==null) || 
             (this.courtRoom!=null &&
              this.courtRoom.equals(other.getCourtRoom())));
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
        if (getSourceReferenceNo() != null) {
            _hashCode += getSourceReferenceNo().hashCode();
        }
        if (getCaseNo() != null) {
            _hashCode += getCaseNo().hashCode();
        }
        if (getMentionDateTime() != null) {
            _hashCode += getMentionDateTime().hashCode();
        }
        if (getCourtRoom() != null) {
            _hashCode += getCourtRoom().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataCreateRepType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "dataCreateRepType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceReferenceNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceReferenceNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caseNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "caseNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mentionDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mentionDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("courtRoom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "courtRoom"));
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
