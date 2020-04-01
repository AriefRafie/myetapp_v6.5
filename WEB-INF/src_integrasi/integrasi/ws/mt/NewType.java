
/**
 * NewType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:58 BST)
 */

            
                package integrasi.ws.mt;
            

            /**
            *  NewType bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class NewType
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = NewType
                Namespace URI = https://eip.kehakiman.gov.my/services/
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for PetitionNo
                        */

                        
                                    protected java.lang.String localPetitionNo ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPetitionNo(){
                               return localPetitionNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PetitionNo
                               */
                               public void setPetitionNo(java.lang.String param){
                            
                                            this.localPetitionNo=param;
                                       

                               }
                            

                        /**
                        * field for DName
                        */

                        
                                    protected java.lang.String localDName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDNameTracker = false ;

                           public boolean isDNameSpecified(){
                               return localDNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDName(){
                               return localDName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DName
                               */
                               public void setDName(java.lang.String param){
                            localDNameTracker = param != null;
                                   
                                            this.localDName=param;
                                       

                               }
                            

                        /**
                        * field for DNameOther
                        */

                        
                                    protected java.lang.String localDNameOther ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDNameOtherTracker = false ;

                           public boolean isDNameOtherSpecified(){
                               return localDNameOtherTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDNameOther(){
                               return localDNameOther;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DNameOther
                               */
                               public void setDNameOther(java.lang.String param){
                            localDNameOtherTracker = param != null;
                                   
                                            this.localDNameOther=param;
                                       

                               }
                            

                        /**
                        * field for DNewIC
                        */

                        
                                    protected java.lang.String localDNewIC ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDNewIC(){
                               return localDNewIC;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DNewIC
                               */
                               public void setDNewIC(java.lang.String param){
                            
                                            this.localDNewIC=param;
                                       

                               }
                            

                        /**
                        * field for DOldIC
                        */

                        
                                    protected java.lang.String localDOldIC ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDOldICTracker = false ;

                           public boolean isDOldICSpecified(){
                               return localDOldICTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDOldIC(){
                               return localDOldIC;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DOldIC
                               */
                               public void setDOldIC(java.lang.String param){
                            localDOldICTracker = param != null;
                                   
                                            this.localDOldIC=param;
                                       

                               }
                            

                        /**
                        * field for DOtherIDType
                        */

                        
                                    protected java.lang.String localDOtherIDType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDOtherIDTypeTracker = false ;

                           public boolean isDOtherIDTypeSpecified(){
                               return localDOtherIDTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDOtherIDType(){
                               return localDOtherIDType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DOtherIDType
                               */
                               public void setDOtherIDType(java.lang.String param){
                            localDOtherIDTypeTracker = true;
                                   
                                            this.localDOtherIDType=param;
                                       

                               }
                            

                        /**
                        * field for DOtherID
                        */

                        
                                    protected java.lang.String localDOtherID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDOtherIDTracker = false ;

                           public boolean isDOtherIDSpecified(){
                               return localDOtherIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDOtherID(){
                               return localDOtherID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DOtherID
                               */
                               public void setDOtherID(java.lang.String param){
                            localDOtherIDTracker = param != null;
                                   
                                            this.localDOtherID=param;
                                       

                               }
                            

                        /**
                        * field for DDate
                        */

                        
                                    protected java.lang.String localDDate ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDDate(){
                               return localDDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DDate
                               */
                               public void setDDate(java.lang.String param){
                            
                                            this.localDDate=param;
                                       

                               }
                            

                        /**
                        * field for CardType
                        */

                        
                                    protected integrasi.ws.mt.CardType_type1 localCardType ;
                                

                           /**
                           * Auto generated getter method
                           * @return integrasi.ws.mt.CardType_type1
                           */
                           public  integrasi.ws.mt.CardType_type1 getCardType(){
                               return localCardType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CardType
                               */
                               public void setCardType(integrasi.ws.mt.CardType_type1 param){
                            
                                            this.localCardType=param;
                                       

                               }
                            

                        /**
                        * field for ApplicationType
                        */

                        
                                    protected integrasi.ws.mt.ApplicationType_type1 localApplicationType ;
                                

                           /**
                           * Auto generated getter method
                           * @return integrasi.ws.mt.ApplicationType_type1
                           */
                           public  integrasi.ws.mt.ApplicationType_type1 getApplicationType(){
                               return localApplicationType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ApplicationType
                               */
                               public void setApplicationType(integrasi.ws.mt.ApplicationType_type1 param){
                            
                                            this.localApplicationType=param;
                                       

                               }
                            

                        /**
                        * field for ApplicationDate
                        */

                        
                                    protected java.lang.String localApplicationDate ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getApplicationDate(){
                               return localApplicationDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ApplicationDate
                               */
                               public void setApplicationDate(java.lang.String param){
                            
                                            this.localApplicationDate=param;
                                       

                               }
                            

                        /**
                        * field for ApplicantName
                        */

                        
                                    protected java.lang.String localApplicantName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localApplicantNameTracker = false ;

                           public boolean isApplicantNameSpecified(){
                               return localApplicantNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getApplicantName(){
                               return localApplicantName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ApplicantName
                               */
                               public void setApplicantName(java.lang.String param){
                            localApplicantNameTracker = param != null;
                                   
                                            this.localApplicantName=param;
                                       

                               }
                            

                        /**
                        * field for ApplicantIC
                        */

                        
                                    protected java.lang.String localApplicantIC ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localApplicantICTracker = false ;

                           public boolean isApplicantICSpecified(){
                               return localApplicantICTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getApplicantIC(){
                               return localApplicantIC;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ApplicantIC
                               */
                               public void setApplicantIC(java.lang.String param){
                            localApplicantICTracker = param != null;
                                   
                                            this.localApplicantIC=param;
                                       

                               }
                            

                        /**
                        * field for ApplicantRelationship
                        */

                        
                                    protected java.lang.String localApplicantRelationship ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localApplicantRelationshipTracker = false ;

                           public boolean isApplicantRelationshipSpecified(){
                               return localApplicantRelationshipTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getApplicantRelationship(){
                               return localApplicantRelationship;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ApplicantRelationship
                               */
                               public void setApplicantRelationship(java.lang.String param){
                            localApplicantRelationshipTracker = param != null;
                                   
                                            this.localApplicantRelationship=param;
                                       

                               }
                            

                        /**
                        * field for ApplicantRelOther
                        */

                        
                                    protected java.lang.String localApplicantRelOther ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localApplicantRelOtherTracker = false ;

                           public boolean isApplicantRelOtherSpecified(){
                               return localApplicantRelOtherTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getApplicantRelOther(){
                               return localApplicantRelOther;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ApplicantRelOther
                               */
                               public void setApplicantRelOther(java.lang.String param){
                            localApplicantRelOtherTracker = param != null;
                                   
                                            this.localApplicantRelOther=param;
                                       

                               }
                            

                        /**
                        * field for BlueCardID
                        */

                        
                                    protected java.lang.String localBlueCardID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBlueCardIDTracker = false ;

                           public boolean isBlueCardIDSpecified(){
                               return localBlueCardIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBlueCardID(){
                               return localBlueCardID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BlueCardID
                               */
                               public void setBlueCardID(java.lang.String param){
                            localBlueCardIDTracker = param != null;
                                   
                                            this.localBlueCardID=param;
                                       

                               }
                            

                        /**
                        * field for GrantDate
                        */

                        
                                    protected java.lang.String localGrantDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localGrantDateTracker = false ;

                           public boolean isGrantDateSpecified(){
                               return localGrantDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getGrantDate(){
                               return localGrantDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param GrantDate
                               */
                               public void setGrantDate(java.lang.String param){
                            localGrantDateTracker = param != null;
                                   
                                            this.localGrantDate=param;
                                       

                               }
                            

                        /**
                        * field for GrantName
                        */

                        
                                    protected java.lang.String localGrantName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localGrantNameTracker = false ;

                           public boolean isGrantNameSpecified(){
                               return localGrantNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getGrantName(){
                               return localGrantName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param GrantName
                               */
                               public void setGrantName(java.lang.String param){
                            localGrantNameTracker = param != null;
                                   
                                            this.localGrantName=param;
                                       

                               }
                            

                        /**
                        * field for OfficeName
                        */

                        
                                    protected java.lang.String localOfficeName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOfficeNameTracker = false ;

                           public boolean isOfficeNameSpecified(){
                               return localOfficeNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOfficeName(){
                               return localOfficeName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OfficeName
                               */
                               public void setOfficeName(java.lang.String param){
                            localOfficeNameTracker = param != null;
                                   
                                            this.localOfficeName=param;
                                       

                               }
                            

                        /**
                        * field for OfficeAddress
                        */

                        
                                    protected java.lang.String localOfficeAddress ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOfficeAddressTracker = false ;

                           public boolean isOfficeAddressSpecified(){
                               return localOfficeAddressTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOfficeAddress(){
                               return localOfficeAddress;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OfficeAddress
                               */
                               public void setOfficeAddress(java.lang.String param){
                            localOfficeAddressTracker = param != null;
                                   
                                            this.localOfficeAddress=param;
                                       

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(this,parentQName));
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"https://eip.kehakiman.gov.my/services/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":NewType",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "NewType",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "";
                                    writeStartElement(null, namespace, "petitionNo", xmlWriter);
                             

                                          if (localPetitionNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("petitionNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPetitionNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localDNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "dName", xmlWriter);
                             

                                          if (localDName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("dName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDNameOtherTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "dNameOther", xmlWriter);
                             

                                          if (localDNameOther==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("dNameOther cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDNameOther);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    namespace = "";
                                    writeStartElement(null, namespace, "dNewIC", xmlWriter);
                             

                                          if (localDNewIC==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("dNewIC cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDNewIC);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localDOldICTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "dOldIC", xmlWriter);
                             

                                          if (localDOldIC==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("dOldIC cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDOldIC);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDOtherIDTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "dOtherIDType", xmlWriter);
                             

                                          if (localDOtherIDType==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDOtherIDType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDOtherIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "dOtherID", xmlWriter);
                             

                                          if (localDOtherID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("dOtherID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDOtherID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    namespace = "";
                                    writeStartElement(null, namespace, "dDate", xmlWriter);
                             

                                          if (localDDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("dDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                            if (localCardType==null){
                                                 throw new org.apache.axis2.databinding.ADBException("cardType cannot be null!!");
                                            }
                                           localCardType.serialize(new javax.xml.namespace.QName("","cardType"),
                                               xmlWriter);
                                        
                                            if (localApplicationType==null){
                                                 throw new org.apache.axis2.databinding.ADBException("applicationType cannot be null!!");
                                            }
                                           localApplicationType.serialize(new javax.xml.namespace.QName("","applicationType"),
                                               xmlWriter);
                                        
                                    namespace = "";
                                    writeStartElement(null, namespace, "applicationDate", xmlWriter);
                             

                                          if (localApplicationDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("applicationDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localApplicationDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localApplicantNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "applicantName", xmlWriter);
                             

                                          if (localApplicantName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("applicantName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localApplicantName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localApplicantICTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "applicantIC", xmlWriter);
                             

                                          if (localApplicantIC==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("applicantIC cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localApplicantIC);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localApplicantRelationshipTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "applicantRelationship", xmlWriter);
                             

                                          if (localApplicantRelationship==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("applicantRelationship cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localApplicantRelationship);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localApplicantRelOtherTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "applicantRelOther", xmlWriter);
                             

                                          if (localApplicantRelOther==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("applicantRelOther cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localApplicantRelOther);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBlueCardIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "blueCardID", xmlWriter);
                             

                                          if (localBlueCardID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("blueCardID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBlueCardID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localGrantDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "grantDate", xmlWriter);
                             

                                          if (localGrantDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("grantDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localGrantDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localGrantNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "grantName", xmlWriter);
                             

                                          if (localGrantName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("grantName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localGrantName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localOfficeNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "officeName", xmlWriter);
                             

                                          if (localOfficeName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("officeName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOfficeName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localOfficeAddressTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "officeAddress", xmlWriter);
                             

                                          if (localOfficeAddress==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("officeAddress cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOfficeAddress);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("https://eip.kehakiman.gov.my/services/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeAttribute(writerPrefix, namespace,attName,attValue);
            } else {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
                xmlWriter.writeAttribute(prefix, namespace,attName,attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace), namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{
        private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static NewType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            NewType object =
                new NewType();

            int event;
            javax.xml.namespace.QName currentQName = null;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                currentQName = reader.getName();
                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"NewType".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (NewType)integrasi.ws.mt.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","petitionNo").equals(reader.getName()) || new javax.xml.namespace.QName("","petitionNo").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"petitionNo" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPetitionNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // 1 - A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","dName").equals(reader.getName()) || new javax.xml.namespace.QName("","dName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"dName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","dNameOther").equals(reader.getName()) || new javax.xml.namespace.QName("","dNameOther").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"dNameOther" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDNameOther(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","dNewIC").equals(reader.getName()) || new javax.xml.namespace.QName("","dNewIC").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"dNewIC" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDNewIC(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // 1 - A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","dOldIC").equals(reader.getName()) || new javax.xml.namespace.QName("","dOldIC").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"dOldIC" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDOldIC(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","dOtherIDType").equals(reader.getName()) || new javax.xml.namespace.QName("","dOtherIDType").equals(reader.getName()) ){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDOtherIDType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","dOtherID").equals(reader.getName()) || new javax.xml.namespace.QName("","dOtherID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"dOtherID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDOtherID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","dDate").equals(reader.getName()) || new javax.xml.namespace.QName("","dDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"dDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // 1 - A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","cardType").equals(reader.getName()) || new javax.xml.namespace.QName("","cardType").equals(reader.getName()) ){
                                
                                                object.setCardType(integrasi.ws.mt.CardType_type1.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // 1 - A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","applicationType").equals(reader.getName()) || new javax.xml.namespace.QName("","applicationType").equals(reader.getName()) ){
                                
                                                object.setApplicationType(integrasi.ws.mt.ApplicationType_type1.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // 1 - A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","applicationDate").equals(reader.getName()) || new javax.xml.namespace.QName("","applicationDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"applicationDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setApplicationDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // 1 - A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","applicantName").equals(reader.getName()) || new javax.xml.namespace.QName("","applicantName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"applicantName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setApplicantName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","applicantIC").equals(reader.getName()) || new javax.xml.namespace.QName("","applicantIC").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"applicantIC" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setApplicantIC(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","applicantRelationship").equals(reader.getName()) || new javax.xml.namespace.QName("","applicantRelationship").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"applicantRelationship" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setApplicantRelationship(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","applicantRelOther").equals(reader.getName()) || new javax.xml.namespace.QName("","applicantRelOther").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"applicantRelOther" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setApplicantRelOther(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","blueCardID").equals(reader.getName()) || new javax.xml.namespace.QName("","blueCardID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"blueCardID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBlueCardID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","grantDate").equals(reader.getName()) || new javax.xml.namespace.QName("","grantDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"grantDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setGrantDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","grantName").equals(reader.getName()) || new javax.xml.namespace.QName("","grantName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"grantName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setGrantName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","officeName").equals(reader.getName()) || new javax.xml.namespace.QName("","officeName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"officeName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOfficeName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","officeAddress").equals(reader.getName()) || new javax.xml.namespace.QName("","officeAddress").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"officeAddress" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOfficeAddress(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // 2 - A start element we are not expecting indicates a trailing invalid property
                                
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    