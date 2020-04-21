/**
 * DataCreateReqType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package integrasi.ws.mt.reg;


/**
 * Decease Info Type
 * Possible Value:
 * 1)	1 = MyKad
 * 2)	2 = Non-Mykad
 */
public class DataCreateReqType  implements java.io.Serializable {
    /* Source Reference Number. */
    private java.lang.String sourceReferenceNo;

    /* Court Location. Possible Value: Refer to
     *                 				appendix Court Location Code */
    private java.lang.String courtLocation;

    /* Jurisdiction Level. Possible Value: 1) 2
     *                 				=High Court 2) 10 = Session Court 3) 5 =
     *                 				Magistrates Court 4) 3 = Court of Appeal
     *                 				5) 11 = Federal Court */
    private java.lang.String jurisdiction;

    /* Division level
     * Possible Value:
     * 1)	3 = Civil
     * 2)	2 = Criminal */
    private java.lang.String division;

    private java.lang.String caseCode;

    /* Alleged value of property involved.
     *                 				Amount with 2 decimal value in Ringgit
     *                 				Malaysia. Format: 999999999.99 */
    private java.lang.String valueInvolved;

    private CauseofactionType[] causeofaction;

    private PartyType[] party;

    /* This section is REQUIRED if Case Code =
     *                 				31NCVC only. */
    private DeceaseInfoType deceaseInfo;

    private DocumentType[] document;

    public DataCreateReqType() {
    }

    public DataCreateReqType(
           java.lang.String sourceReferenceNo,
           java.lang.String courtLocation,
           java.lang.String jurisdiction,
           java.lang.String division,
           java.lang.String caseCode,
           java.lang.String valueInvolved,
           CauseofactionType[] causeofaction,
           PartyType[] party,
           DeceaseInfoType deceaseInfo,
           DocumentType[] document) {
           this.sourceReferenceNo = sourceReferenceNo;
           this.courtLocation = courtLocation;
           this.jurisdiction = jurisdiction;
           this.division = division;
           this.caseCode = caseCode;
           this.valueInvolved = valueInvolved;
           this.causeofaction = causeofaction;
           this.party = party;
           this.deceaseInfo = deceaseInfo;
           this.document = document;
    }


    /**
     * Gets the sourceReferenceNo value for this DataCreateReqType.
     * 
     * @return sourceReferenceNo   * Source Reference Number.
     */
    public java.lang.String getSourceReferenceNo() {
        return sourceReferenceNo;
    }


    /**
     * Sets the sourceReferenceNo value for this DataCreateReqType.
     * 
     * @param sourceReferenceNo   * Source Reference Number.
     */
    public void setSourceReferenceNo(java.lang.String sourceReferenceNo) {
        this.sourceReferenceNo = sourceReferenceNo;
    }


    /**
     * Gets the courtLocation value for this DataCreateReqType.
     * 
     * @return courtLocation   * Court Location. Possible Value: Refer to
     *                 				appendix Court Location Code
     */
    public java.lang.String getCourtLocation() {
        return courtLocation;
    }


    /**
     * Sets the courtLocation value for this DataCreateReqType.
     * 
     * @param courtLocation   * Court Location. Possible Value: Refer to
     *                 				appendix Court Location Code
     */
    public void setCourtLocation(java.lang.String courtLocation) {
        this.courtLocation = courtLocation;
    }


    /**
     * Gets the jurisdiction value for this DataCreateReqType.
     * 
     * @return jurisdiction   * Jurisdiction Level. Possible Value: 1) 2
     *                 				=High Court 2) 10 = Session Court 3) 5 =
     *                 				Magistrates Court 4) 3 = Court of Appeal
     *                 				5) 11 = Federal Court
     */
    public java.lang.String getJurisdiction() {
        return jurisdiction;
    }


    /**
     * Sets the jurisdiction value for this DataCreateReqType.
     * 
     * @param jurisdiction   * Jurisdiction Level. Possible Value: 1) 2
     *                 				=High Court 2) 10 = Session Court 3) 5 =
     *                 				Magistrates Court 4) 3 = Court of Appeal
     *                 				5) 11 = Federal Court
     */
    public void setJurisdiction(java.lang.String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }


    /**
     * Gets the division value for this DataCreateReqType.
     * 
     * @return division   * Division level
     * Possible Value:
     * 1)	3 = Civil
     * 2)	2 = Criminal
     */
    public java.lang.String getDivision() {
        return division;
    }


    /**
     * Sets the division value for this DataCreateReqType.
     * 
     * @param division   * Division level
     * Possible Value:
     * 1)	3 = Civil
     * 2)	2 = Criminal
     */
    public void setDivision(java.lang.String division) {
        this.division = division;
    }


    /**
     * Gets the caseCode value for this DataCreateReqType.
     * 
     * @return caseCode
     */
    public java.lang.String getCaseCode() {
        return caseCode;
    }


    /**
     * Sets the caseCode value for this DataCreateReqType.
     * 
     * @param caseCode
     */
    public void setCaseCode(java.lang.String caseCode) {
        this.caseCode = caseCode;
    }


    /**
     * Gets the valueInvolved value for this DataCreateReqType.
     * 
     * @return valueInvolved   * Alleged value of property involved.
     *                 				Amount with 2 decimal value in Ringgit
     *                 				Malaysia. Format: 999999999.99
     */
    public java.lang.String getValueInvolved() {
        return valueInvolved;
    }


    /**
     * Sets the valueInvolved value for this DataCreateReqType.
     * 
     * @param valueInvolved   * Alleged value of property involved.
     *                 				Amount with 2 decimal value in Ringgit
     *                 				Malaysia. Format: 999999999.99
     */
    public void setValueInvolved(java.lang.String valueInvolved) {
        this.valueInvolved = valueInvolved;
    }


    /**
     * Gets the causeofaction value for this DataCreateReqType.
     * 
     * @return causeofaction
     */
    public CauseofactionType[] getCauseofaction() {
        return causeofaction;
    }


    /**
     * Sets the causeofaction value for this DataCreateReqType.
     * 
     * @param causeofaction
     */
    public void setCauseofaction(CauseofactionType[] causeofaction) {
        this.causeofaction = causeofaction;
    }

    public CauseofactionType getCauseofaction(int i) {
        return this.causeofaction[i];
    }

    public void setCauseofaction(int i, CauseofactionType _value) {
        this.causeofaction[i] = _value;
    }


    /**
     * Gets the party value for this DataCreateReqType.
     * 
     * @return party
     */
    public PartyType[] getParty() {
        return party;
    }


    /**
     * Sets the party value for this DataCreateReqType.
     * 
     * @param party
     */
    public void setParty(PartyType[] party) {
        this.party = party;
    }

    public PartyType getParty(int i) {
        return this.party[i];
    }

    public void setParty(int i, PartyType _value) {
        this.party[i] = _value;
    }


    /**
     * Gets the deceaseInfo value for this DataCreateReqType.
     * 
     * @return deceaseInfo   * This section is REQUIRED if Case Code =
     *                 				31NCVC only.
     */
    public DeceaseInfoType getDeceaseInfo() {
        return deceaseInfo;
    }


    /**
     * Sets the deceaseInfo value for this DataCreateReqType.
     * 
     * @param deceaseInfo   * This section is REQUIRED if Case Code =
     *                 				31NCVC only.
     */
    public void setDeceaseInfo(DeceaseInfoType deceaseInfo) {
        this.deceaseInfo = deceaseInfo;
    }


    /**
     * Gets the document value for this DataCreateReqType.
     * 
     * @return document
     */
    public DocumentType[] getDocument() {
        return document;
    }


    /**
     * Sets the document value for this DataCreateReqType.
     * 
     * @param document
     */
    public void setDocument(DocumentType[] document) {
        this.document = document;
    }

    public DocumentType getDocument(int i) {
        return this.document[i];
    }

    public void setDocument(int i, DocumentType _value) {
        this.document[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataCreateReqType)) return false;
        DataCreateReqType other = (DataCreateReqType) obj;
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
            ((this.courtLocation==null && other.getCourtLocation()==null) || 
             (this.courtLocation!=null &&
              this.courtLocation.equals(other.getCourtLocation()))) &&
            ((this.jurisdiction==null && other.getJurisdiction()==null) || 
             (this.jurisdiction!=null &&
              this.jurisdiction.equals(other.getJurisdiction()))) &&
            ((this.division==null && other.getDivision()==null) || 
             (this.division!=null &&
              this.division.equals(other.getDivision()))) &&
            ((this.caseCode==null && other.getCaseCode()==null) || 
             (this.caseCode!=null &&
              this.caseCode.equals(other.getCaseCode()))) &&
            ((this.valueInvolved==null && other.getValueInvolved()==null) || 
             (this.valueInvolved!=null &&
              this.valueInvolved.equals(other.getValueInvolved()))) &&
            ((this.causeofaction==null && other.getCauseofaction()==null) || 
             (this.causeofaction!=null &&
              java.util.Arrays.equals(this.causeofaction, other.getCauseofaction()))) &&
            ((this.party==null && other.getParty()==null) || 
             (this.party!=null &&
              java.util.Arrays.equals(this.party, other.getParty()))) &&
            ((this.deceaseInfo==null && other.getDeceaseInfo()==null) || 
             (this.deceaseInfo!=null &&
              this.deceaseInfo.equals(other.getDeceaseInfo()))) &&
            ((this.document==null && other.getDocument()==null) || 
             (this.document!=null &&
              java.util.Arrays.equals(this.document, other.getDocument())));
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
        if (getCourtLocation() != null) {
            _hashCode += getCourtLocation().hashCode();
        }
        if (getJurisdiction() != null) {
            _hashCode += getJurisdiction().hashCode();
        }
        if (getDivision() != null) {
            _hashCode += getDivision().hashCode();
        }
        if (getCaseCode() != null) {
            _hashCode += getCaseCode().hashCode();
        }
        if (getValueInvolved() != null) {
            _hashCode += getValueInvolved().hashCode();
        }
        if (getCauseofaction() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCauseofaction());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCauseofaction(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getParty() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParty());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParty(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDeceaseInfo() != null) {
            _hashCode += getDeceaseInfo().hashCode();
        }
        if (getDocument() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocument());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocument(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataCreateReqType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "dataCreateReqType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceReferenceNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceReferenceNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("courtLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "courtLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jurisdiction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jurisdiction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("division");
        elemField.setXmlName(new javax.xml.namespace.QName("", "division"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "caseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueInvolved");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valueInvolved"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("causeofaction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "causeofaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "causeofactionType"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("party");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Party"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "PartyType"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deceaseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "deceaseInfoType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("document");
        elemField.setXmlName(new javax.xml.namespace.QName("", "document"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "documentType"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
