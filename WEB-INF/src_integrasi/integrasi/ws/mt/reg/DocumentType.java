/**
 * DocumentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package integrasi.ws.mt.reg;

public class DocumentType  implements java.io.Serializable {
    /* Document Unique Identifier */
    private java.lang.String docID;

    /* The Doc Enclosure No. Doc Must follow
     *         						the sequence (1…. n) For Case Code
     *         						15,16A & 31NCvC, set default value = 1 */
    private java.lang.String docEnlcNo;

    /* Document Type Possible Value: Refer to
     *         						appendix Document Type Case for New
     *         						Filing */
    private java.lang.String docType;

    /* Document Name including extension
     *         						(*.pdf) Refer to 4.1.1 Interface
     *         						Description */
    private java.lang.String docName;

    /* Actual document in PDF format. It will be converted in string
     * in Base64 format. */
    private java.lang.String docContent;

    public DocumentType() {
    }

    public DocumentType(
           java.lang.String docID,
           java.lang.String docEnlcNo,
           java.lang.String docType,
           java.lang.String docName,
           java.lang.String docContent) {
           this.docID = docID;
           this.docEnlcNo = docEnlcNo;
           this.docType = docType;
           this.docName = docName;
           this.docContent = docContent;
    }


    /**
     * Gets the docID value for this DocumentType.
     * 
     * @return docID   * Document Unique Identifier
     */
    public java.lang.String getDocID() {
        return docID;
    }


    /**
     * Sets the docID value for this DocumentType.
     * 
     * @param docID   * Document Unique Identifier
     */
    public void setDocID(java.lang.String docID) {
        this.docID = docID;
    }


    /**
     * Gets the docEnlcNo value for this DocumentType.
     * 
     * @return docEnlcNo   * The Doc Enclosure No. Doc Must follow
     *         						the sequence (1…. n) For Case Code
     *         						15,16A & 31NCvC, set default value = 1
     */
    public java.lang.String getDocEnlcNo() {
        return docEnlcNo;
    }


    /**
     * Sets the docEnlcNo value for this DocumentType.
     * 
     * @param docEnlcNo   * The Doc Enclosure No. Doc Must follow
     *         						the sequence (1…. n) For Case Code
     *         						15,16A & 31NCvC, set default value = 1
     */
    public void setDocEnlcNo(java.lang.String docEnlcNo) {
        this.docEnlcNo = docEnlcNo;
    }


    /**
     * Gets the docType value for this DocumentType.
     * 
     * @return docType   * Document Type Possible Value: Refer to
     *         						appendix Document Type Case for New
     *         						Filing
     */
    public java.lang.String getDocType() {
        return docType;
    }


    /**
     * Sets the docType value for this DocumentType.
     * 
     * @param docType   * Document Type Possible Value: Refer to
     *         						appendix Document Type Case for New
     *         						Filing
     */
    public void setDocType(java.lang.String docType) {
        this.docType = docType;
    }


    /**
     * Gets the docName value for this DocumentType.
     * 
     * @return docName   * Document Name including extension
     *         						(*.pdf) Refer to 4.1.1 Interface
     *         						Description
     */
    public java.lang.String getDocName() {
        return docName;
    }


    /**
     * Sets the docName value for this DocumentType.
     * 
     * @param docName   * Document Name including extension
     *         						(*.pdf) Refer to 4.1.1 Interface
     *         						Description
     */
    public void setDocName(java.lang.String docName) {
        this.docName = docName;
    }


    /**
     * Gets the docContent value for this DocumentType.
     * 
     * @return docContent   * Actual document in PDF format. It will be converted in string
     * in Base64 format.
     */
    public java.lang.String getDocContent() {
        return docContent;
    }


    /**
     * Sets the docContent value for this DocumentType.
     * 
     * @param docContent   * Actual document in PDF format. It will be converted in string
     * in Base64 format.
     */
    public void setDocContent(java.lang.String docContent) {
        this.docContent = docContent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentType)) return false;
        DocumentType other = (DocumentType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.docID==null && other.getDocID()==null) || 
             (this.docID!=null &&
              this.docID.equals(other.getDocID()))) &&
            ((this.docEnlcNo==null && other.getDocEnlcNo()==null) || 
             (this.docEnlcNo!=null &&
              this.docEnlcNo.equals(other.getDocEnlcNo()))) &&
            ((this.docType==null && other.getDocType()==null) || 
             (this.docType!=null &&
              this.docType.equals(other.getDocType()))) &&
            ((this.docName==null && other.getDocName()==null) || 
             (this.docName!=null &&
              this.docName.equals(other.getDocName()))) &&
            ((this.docContent==null && other.getDocContent()==null) || 
             (this.docContent!=null &&
              this.docContent.equals(other.getDocContent())));
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
        if (getDocID() != null) {
            _hashCode += getDocID().hashCode();
        }
        if (getDocEnlcNo() != null) {
            _hashCode += getDocEnlcNo().hashCode();
        }
        if (getDocType() != null) {
            _hashCode += getDocType().hashCode();
        }
        if (getDocName() != null) {
            _hashCode += getDocName().hashCode();
        }
        if (getDocContent() != null) {
            _hashCode += getDocContent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "documentType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docEnlcNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocEnlcNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docContent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docContent"));
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
