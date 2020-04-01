/**
 * NewType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class NewType  implements java.io.Serializable {
    /* Petition No */
    private java.lang.String petitionNo;

    /* Deceased Name */
    private java.lang.String dName;

    /* Other Deceased Name */
    private java.lang.String dNameOther;

    /* Deceased New IC No. */
    private java.lang.String dNewIC;

    /* Deceased Old IC */
    private java.lang.String dOldIC;

    /* Deceased Other ID Type (if any) Possible
     *             				Value: 1) PO = Police 2) PP = Passport 3) SO
     *             				= Army 4) OT = Others 5) PDC = Presumption of Death
     * Case No. */
    private java.lang.String dOtherIDType;

    /* Deceased Other ID (if any) */
    private java.lang.String dOtherID;

    /* Death Cert Registration No./Document
     *             				Reference No./Permit No./Any
     *             				Deceased-related Reference No. */
    private java.lang.String dDeathCertNo;

    /* Deceased Date */
    private java.lang.String dDate;

    /* Card Type Possible Value: 1) CB = Caveat 2)
     *             				BC = Borang B */
    private my.gov.kehakiman.eip.services.NewTypeCardType cardType;

    /* Type of application. Possible Value: 1) I =
     *             				Blue Card 2) G = Grant 3) P = Petitioner */
    private my.gov.kehakiman.eip.services.NewTypeApplicationType applicationType;

    /* Application Date */
    private java.lang.String applicationDate;

    /* Applicant Name * Required if
     *             				[applicationType] = �P� */
    private java.lang.String applicantName;

    /* Applicant NRIC No. * Required if
     *             				[applicationType] = �P� */
    private java.lang.String applicantIC;

    /* Applicant relationship Possible Value: 1)
     *             				Children 2) Husband 3) Wife 4) Father 5)
     *             				Mother 6) Sibling 7) Grand Children 8) Great
     *             				Grand Children 9) Others * Required if
     *             				[applicationType] = �P� */
    private java.lang.String applicantRelationship;

    /* Applicant relationship Required if
     *             				[applicantRelationship] = �Others�. *
     *             				Required if [applicationType] = �P� */
    private java.lang.String applicantRelOther;

    /* Borang B / Grant ID * Applicable if
     *             				[applicationType] = �G� and the application
     *             				has been submitted before. * Applicable if
     *             				[applicationType] = �P� and the application
     *             				has been submitted before. */
    private java.lang.String blueCardID;

    /* Grant Date * Only applicable if
     *             				[applicationType] = �G� */
    private java.lang.String grantDate;

    /* Grant Name * Only applicable if
     *             				[applicationType] = �G� */
    private java.lang.String grantName;

    /* eTaPP Office Code. eTapp to provide the
     *             				list. */
    private java.lang.String officeName;

    private java.lang.String officeAddress;

    /* Actual document in PDF format. It will be converted in string
     * in Base64 format. */
    private java.lang.String docContent;

    public NewType() {
    }

    public NewType(
           java.lang.String petitionNo,
           java.lang.String dName,
           java.lang.String dNameOther,
           java.lang.String dNewIC,
           java.lang.String dOldIC,
           java.lang.String dOtherIDType,
           java.lang.String dOtherID,
           java.lang.String dDeathCertNo,
           java.lang.String dDate,
           my.gov.kehakiman.eip.services.NewTypeCardType cardType,
           my.gov.kehakiman.eip.services.NewTypeApplicationType applicationType,
           java.lang.String applicationDate,
           java.lang.String applicantName,
           java.lang.String applicantIC,
           java.lang.String applicantRelationship,
           java.lang.String applicantRelOther,
           java.lang.String blueCardID,
           java.lang.String grantDate,
           java.lang.String grantName,
           java.lang.String officeName,
           java.lang.String officeAddress,
           java.lang.String docContent) {
           this.petitionNo = petitionNo;
           this.dName = dName;
           this.dNameOther = dNameOther;
           this.dNewIC = dNewIC;
           this.dOldIC = dOldIC;
           this.dOtherIDType = dOtherIDType;
           this.dOtherID = dOtherID;
           this.dDeathCertNo = dDeathCertNo;
           this.dDate = dDate;
           this.cardType = cardType;
           this.applicationType = applicationType;
           this.applicationDate = applicationDate;
           this.applicantName = applicantName;
           this.applicantIC = applicantIC;
           this.applicantRelationship = applicantRelationship;
           this.applicantRelOther = applicantRelOther;
           this.blueCardID = blueCardID;
           this.grantDate = grantDate;
           this.grantName = grantName;
           this.officeName = officeName;
           this.officeAddress = officeAddress;
           this.docContent = docContent;
    }


    /**
     * Gets the petitionNo value for this NewType.
     * 
     * @return petitionNo   * Petition No
     */
    public java.lang.String getPetitionNo() {
        return petitionNo;
    }


    /**
     * Sets the petitionNo value for this NewType.
     * 
     * @param petitionNo   * Petition No
     */
    public void setPetitionNo(java.lang.String petitionNo) {
        this.petitionNo = petitionNo;
    }


    /**
     * Gets the dName value for this NewType.
     * 
     * @return dName   * Deceased Name
     */
    public java.lang.String getDName() {
        return dName;
    }


    /**
     * Sets the dName value for this NewType.
     * 
     * @param dName   * Deceased Name
     */
    public void setDName(java.lang.String dName) {
        this.dName = dName;
    }


    /**
     * Gets the dNameOther value for this NewType.
     * 
     * @return dNameOther   * Other Deceased Name
     */
    public java.lang.String getDNameOther() {
        return dNameOther;
    }


    /**
     * Sets the dNameOther value for this NewType.
     * 
     * @param dNameOther   * Other Deceased Name
     */
    public void setDNameOther(java.lang.String dNameOther) {
        this.dNameOther = dNameOther;
    }


    /**
     * Gets the dNewIC value for this NewType.
     * 
     * @return dNewIC   * Deceased New IC No.
     */
    public java.lang.String getDNewIC() {
        return dNewIC;
    }


    /**
     * Sets the dNewIC value for this NewType.
     * 
     * @param dNewIC   * Deceased New IC No.
     */
    public void setDNewIC(java.lang.String dNewIC) {
        this.dNewIC = dNewIC;
    }


    /**
     * Gets the dOldIC value for this NewType.
     * 
     * @return dOldIC   * Deceased Old IC
     */
    public java.lang.String getDOldIC() {
        return dOldIC;
    }


    /**
     * Sets the dOldIC value for this NewType.
     * 
     * @param dOldIC   * Deceased Old IC
     */
    public void setDOldIC(java.lang.String dOldIC) {
        this.dOldIC = dOldIC;
    }


    /**
     * Gets the dOtherIDType value for this NewType.
     * 
     * @return dOtherIDType   * Deceased Other ID Type (if any) Possible
     *             				Value: 1) PO = Police 2) PP = Passport 3) SO
     *             				= Army 4) OT = Others 5) PDC = Presumption of Death
     * Case No.
     */
    public java.lang.String getDOtherIDType() {
        return dOtherIDType;
    }


    /**
     * Sets the dOtherIDType value for this NewType.
     * 
     * @param dOtherIDType   * Deceased Other ID Type (if any) Possible
     *             				Value: 1) PO = Police 2) PP = Passport 3) SO
     *             				= Army 4) OT = Others 5) PDC = Presumption of Death
     * Case No.
     */
    public void setDOtherIDType(java.lang.String dOtherIDType) {
        this.dOtherIDType = dOtherIDType;
    }


    /**
     * Gets the dOtherID value for this NewType.
     * 
     * @return dOtherID   * Deceased Other ID (if any)
     */
    public java.lang.String getDOtherID() {
        return dOtherID;
    }

	public void setdocContent(java.lang.String docContent) {
		this.docContent = docContent;
		
	}

    /**
     * Sets the dOtherID value for this NewType.
     * 
     * @param dOtherID   * Deceased Other ID (if any)
     */
    public void setDOtherID(java.lang.String dOtherID) {
        this.dOtherID = dOtherID;
    }


    /**
     * Gets the dDeathCertNo value for this NewType.
     * 
     * @return dDeathCertNo   * Death Cert Registration No./Document
     *             				Reference No./Permit No./Any
     *             				Deceased-related Reference No.
     */
    public java.lang.String getDDeathCertNo() {
        return dDeathCertNo;
    }


    /**
     * Sets the dDeathCertNo value for this NewType.
     * 
     * @param dDeathCertNo   * Death Cert Registration No./Document
     *             				Reference No./Permit No./Any
     *             				Deceased-related Reference No.
     */
    public void setDDeathCertNo(java.lang.String dDeathCertNo) {
        this.dDeathCertNo = dDeathCertNo;
    }


    /**
     * Gets the dDate value for this NewType.
     * 
     * @return dDate   * Deceased Date
     */
    public java.lang.String getDDate() {
        return dDate;
    }


    /**
     * Sets the dDate value for this NewType.
     * 
     * @param dDate   * Deceased Date
     */
    public void setDDate(java.lang.String dDate) {
        this.dDate = dDate;
    }


    /**
     * Gets the cardType value for this NewType.
     * 
     * @return cardType   * Card Type Possible Value: 1) CB = Caveat 2)
     *             				BC = Borang B
     */
    public my.gov.kehakiman.eip.services.NewTypeCardType getCardType() {
        return cardType;
    }


    /**
     * Sets the cardType value for this NewType.
     * 
     * @param cardType   * Card Type Possible Value: 1) CB = Caveat 2)
     *             				BC = Borang B
     */
    public void setCardType(my.gov.kehakiman.eip.services.NewTypeCardType cardType) {
        this.cardType = cardType;
    }


    /**
     * Gets the applicationType value for this NewType.
     * 
     * @return applicationType   * Type of application. Possible Value: 1) I =
     *             				Blue Card 2) G = Grant 3) P = Petitioner
     */
    public my.gov.kehakiman.eip.services.NewTypeApplicationType getApplicationType() {
        return applicationType;
    }


    /**
     * Sets the applicationType value for this NewType.
     * 
     * @param applicationType   * Type of application. Possible Value: 1) I =
     *             				Blue Card 2) G = Grant 3) P = Petitioner
     */
    public void setApplicationType(my.gov.kehakiman.eip.services.NewTypeApplicationType applicationType) {
        this.applicationType = applicationType;
    }


    /**
     * Gets the applicationDate value for this NewType.
     * 
     * @return applicationDate   * Application Date
     */
    public java.lang.String getApplicationDate() {
        return applicationDate;
    }


    /**
     * Sets the applicationDate value for this NewType.
     * 
     * @param applicationDate   * Application Date
     */
    public void setApplicationDate(java.lang.String applicationDate) {
        this.applicationDate = applicationDate;
    }


    /**
     * Gets the applicantName value for this NewType.
     * 
     * @return applicantName   * Applicant Name * Required if
     *             				[applicationType] = �P�
     */
    public java.lang.String getApplicantName() {
        return applicantName;
    }


    /**
     * Sets the applicantName value for this NewType.
     * 
     * @param applicantName   * Applicant Name * Required if
     *             				[applicationType] = �P�
     */
    public void setApplicantName(java.lang.String applicantName) {
        this.applicantName = applicantName;
    }


    /**
     * Gets the applicantIC value for this NewType.
     * 
     * @return applicantIC   * Applicant NRIC No. * Required if
     *             				[applicationType] = �P�
     */
    public java.lang.String getApplicantIC() {
        return applicantIC;
    }


    /**
     * Sets the applicantIC value for this NewType.
     * 
     * @param applicantIC   * Applicant NRIC No. * Required if
     *             				[applicationType] = �P�
     */
    public void setApplicantIC(java.lang.String applicantIC) {
        this.applicantIC = applicantIC;
    }


    /**
     * Gets the applicantRelationship value for this NewType.
     * 
     * @return applicantRelationship   * Applicant relationship Possible Value: 1)
     *             				Children 2) Husband 3) Wife 4) Father 5)
     *             				Mother 6) Sibling 7) Grand Children 8) Great
     *             				Grand Children 9) Others * Required if
     *             				[applicationType] = �P�
     */
    public java.lang.String getApplicantRelationship() {
        return applicantRelationship;
    }


    /**
     * Sets the applicantRelationship value for this NewType.
     * 
     * @param applicantRelationship   * Applicant relationship Possible Value: 1)
     *             				Children 2) Husband 3) Wife 4) Father 5)
     *             				Mother 6) Sibling 7) Grand Children 8) Great
     *             				Grand Children 9) Others * Required if
     *             				[applicationType] = �P�
     */
    public void setApplicantRelationship(java.lang.String applicantRelationship) {
        this.applicantRelationship = applicantRelationship;
    }


    /**
     * Gets the applicantRelOther value for this NewType.
     * 
     * @return applicantRelOther   * Applicant relationship Required if
     *             				[applicantRelationship] = �Others�. *
     *             				Required if [applicationType] = �P�
     */
    public java.lang.String getApplicantRelOther() {
        return applicantRelOther;
    }


    /**
     * Sets the applicantRelOther value for this NewType.
     * 
     * @param applicantRelOther   * Applicant relationship Required if
     *             				[applicantRelationship] = �Others�. *
     *             				Required if [applicationType] = �P�
     */
    public void setApplicantRelOther(java.lang.String applicantRelOther) {
        this.applicantRelOther = applicantRelOther;
    }


    /**
     * Gets the blueCardID value for this NewType.
     * 
     * @return blueCardID   * Borang B / Grant ID * Applicable if
     *             				[applicationType] = �G� and the application
     *             				has been submitted before. * Applicable if
     *             				[applicationType] = �P� and the application
     *             				has been submitted before.
     */
    public java.lang.String getBlueCardID() {
        return blueCardID;
    }


    /**
     * Sets the blueCardID value for this NewType.
     * 
     * @param blueCardID   * Borang B / Grant ID * Applicable if
     *             				[applicationType] = �G� and the application
     *             				has been submitted before. * Applicable if
     *             				[applicationType] = �P� and the application
     *             				has been submitted before.
     */
    public void setBlueCardID(java.lang.String blueCardID) {
        this.blueCardID = blueCardID;
    }


    /**
     * Gets the grantDate value for this NewType.
     * 
     * @return grantDate   * Grant Date * Only applicable if
     *             				[applicationType] = �G�
     */
    public java.lang.String getGrantDate() {
        return grantDate;
    }


    /**
     * Sets the grantDate value for this NewType.
     * 
     * @param grantDate   * Grant Date * Only applicable if
     *             				[applicationType] = �G�
     */
    public void setGrantDate(java.lang.String grantDate) {
        this.grantDate = grantDate;
    }


    /**
     * Gets the grantName value for this NewType.
     * 
     * @return grantName   * Grant Name * Only applicable if
     *             				[applicationType] = �G�
     */
    public java.lang.String getGrantName() {
        return grantName;
    }


    /**
     * Sets the grantName value for this NewType.
     * 
     * @param grantName   * Grant Name * Only applicable if
     *             				[applicationType] = �G�
     */
    public void setGrantName(java.lang.String grantName) {
        this.grantName = grantName;
    }


    /**
     * Gets the officeName value for this NewType.
     * 
     * @return officeName   * eTaPP Office Code. eTapp to provide the
     *             				list.
     */
    public java.lang.String getOfficeName() {
        return officeName;
    }


    /**
     * Sets the officeName value for this NewType.
     * 
     * @param officeName   * eTaPP Office Code. eTapp to provide the
     *             				list.
     */
    public void setOfficeName(java.lang.String officeName) {
        this.officeName = officeName;
    }


    /**
     * Gets the officeAddress value for this NewType.
     * 
     * @return officeAddress
     */
    public java.lang.String getOfficeAddress() {
        return officeAddress;
    }


    /**
     * Sets the officeAddress value for this NewType.
     * 
     * @param officeAddress
     */
    public void setOfficeAddress(java.lang.String officeAddress) {
        this.officeAddress = officeAddress;
    }


    /**
     * Gets the docContent value for this NewType.
     * 
     * @return docContent   * Actual document in PDF format. It will be converted in string
     * in Base64 format.
     */
    public java.lang.String getDocContent() {
        return docContent;
    }


    /**
     * Sets the docContent value for this NewType.
     * 
     * @param docContent   * Actual document in PDF format. It will be converted in string
     * in Base64 format.
     */
    public void setDocContent(java.lang.String docContent) {
        this.docContent = docContent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NewType)) return false;
        NewType other = (NewType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.petitionNo==null && other.getPetitionNo()==null) || 
             (this.petitionNo!=null &&
              this.petitionNo.equals(other.getPetitionNo()))) &&
            ((this.dName==null && other.getDName()==null) || 
             (this.dName!=null &&
              this.dName.equals(other.getDName()))) &&
            ((this.dNameOther==null && other.getDNameOther()==null) || 
             (this.dNameOther!=null &&
              this.dNameOther.equals(other.getDNameOther()))) &&
            ((this.dNewIC==null && other.getDNewIC()==null) || 
             (this.dNewIC!=null &&
              this.dNewIC.equals(other.getDNewIC()))) &&
            ((this.dOldIC==null && other.getDOldIC()==null) || 
             (this.dOldIC!=null &&
              this.dOldIC.equals(other.getDOldIC()))) &&
            ((this.dOtherIDType==null && other.getDOtherIDType()==null) || 
             (this.dOtherIDType!=null &&
              this.dOtherIDType.equals(other.getDOtherIDType()))) &&
            ((this.dOtherID==null && other.getDOtherID()==null) || 
             (this.dOtherID!=null &&
              this.dOtherID.equals(other.getDOtherID()))) &&
            ((this.dDeathCertNo==null && other.getDDeathCertNo()==null) || 
             (this.dDeathCertNo!=null &&
              this.dDeathCertNo.equals(other.getDDeathCertNo()))) &&
            ((this.dDate==null && other.getDDate()==null) || 
             (this.dDate!=null &&
              this.dDate.equals(other.getDDate()))) &&
            ((this.cardType==null && other.getCardType()==null) || 
             (this.cardType!=null &&
              this.cardType.equals(other.getCardType()))) &&
            ((this.applicationType==null && other.getApplicationType()==null) || 
             (this.applicationType!=null &&
              this.applicationType.equals(other.getApplicationType()))) &&
            ((this.applicationDate==null && other.getApplicationDate()==null) || 
             (this.applicationDate!=null &&
              this.applicationDate.equals(other.getApplicationDate()))) &&
            ((this.applicantName==null && other.getApplicantName()==null) || 
             (this.applicantName!=null &&
              this.applicantName.equals(other.getApplicantName()))) &&
            ((this.applicantIC==null && other.getApplicantIC()==null) || 
             (this.applicantIC!=null &&
              this.applicantIC.equals(other.getApplicantIC()))) &&
            ((this.applicantRelationship==null && other.getApplicantRelationship()==null) || 
             (this.applicantRelationship!=null &&
              this.applicantRelationship.equals(other.getApplicantRelationship()))) &&
            ((this.applicantRelOther==null && other.getApplicantRelOther()==null) || 
             (this.applicantRelOther!=null &&
              this.applicantRelOther.equals(other.getApplicantRelOther()))) &&
            ((this.blueCardID==null && other.getBlueCardID()==null) || 
             (this.blueCardID!=null &&
              this.blueCardID.equals(other.getBlueCardID()))) &&
            ((this.grantDate==null && other.getGrantDate()==null) || 
             (this.grantDate!=null &&
              this.grantDate.equals(other.getGrantDate()))) &&
            ((this.grantName==null && other.getGrantName()==null) || 
             (this.grantName!=null &&
              this.grantName.equals(other.getGrantName()))) &&
            ((this.officeName==null && other.getOfficeName()==null) || 
             (this.officeName!=null &&
              this.officeName.equals(other.getOfficeName()))) &&
            ((this.officeAddress==null && other.getOfficeAddress()==null) || 
             (this.officeAddress!=null &&
              this.officeAddress.equals(other.getOfficeAddress()))) &&
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
        if (getPetitionNo() != null) {
            _hashCode += getPetitionNo().hashCode();
        }
        if (getDName() != null) {
            _hashCode += getDName().hashCode();
        }
        if (getDNameOther() != null) {
            _hashCode += getDNameOther().hashCode();
        }
        if (getDNewIC() != null) {
            _hashCode += getDNewIC().hashCode();
        }
        if (getDOldIC() != null) {
            _hashCode += getDOldIC().hashCode();
        }
        if (getDOtherIDType() != null) {
            _hashCode += getDOtherIDType().hashCode();
        }
        if (getDOtherID() != null) {
            _hashCode += getDOtherID().hashCode();
        }
        if (getDDeathCertNo() != null) {
            _hashCode += getDDeathCertNo().hashCode();
        }
        if (getDDate() != null) {
            _hashCode += getDDate().hashCode();
        }
        if (getCardType() != null) {
            _hashCode += getCardType().hashCode();
        }
        if (getApplicationType() != null) {
            _hashCode += getApplicationType().hashCode();
        }
        if (getApplicationDate() != null) {
            _hashCode += getApplicationDate().hashCode();
        }
        if (getApplicantName() != null) {
            _hashCode += getApplicantName().hashCode();
        }
        if (getApplicantIC() != null) {
            _hashCode += getApplicantIC().hashCode();
        }
        if (getApplicantRelationship() != null) {
            _hashCode += getApplicantRelationship().hashCode();
        }
        if (getApplicantRelOther() != null) {
            _hashCode += getApplicantRelOther().hashCode();
        }
        if (getBlueCardID() != null) {
            _hashCode += getBlueCardID().hashCode();
        }
        if (getGrantDate() != null) {
            _hashCode += getGrantDate().hashCode();
        }
        if (getGrantName() != null) {
            _hashCode += getGrantName().hashCode();
        }
        if (getOfficeName() != null) {
            _hashCode += getOfficeName().hashCode();
        }
        if (getOfficeAddress() != null) {
            _hashCode += getOfficeAddress().hashCode();
        }
        if (getDocContent() != null) {
            _hashCode += getDocContent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NewType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "NewType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("petitionNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "petitionNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DNameOther");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dNameOther"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DNewIC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dNewIC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOldIC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dOldIC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOtherIDType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dOtherIDType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOtherID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dOtherID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DDeathCertNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dDeathCertNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardType"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">NewType>cardType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", ">NewType>applicationType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicantName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicantName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicantIC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicantIC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicantRelationship");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicantRelationship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicantRelOther");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicantRelOther"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blueCardID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blueCardID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grantDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grantDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grantName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grantName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("officeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "officeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("officeAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "officeAddress"));
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
