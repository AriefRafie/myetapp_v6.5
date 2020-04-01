
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:58 BST)
 */

        
            package integrasi.ws.mt;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "https://eip.kehakiman.gov.my/services/".equals(namespaceURI) &&
                  "applicationType_type1".equals(typeName)){
                   
                            return  integrasi.ws.mt.ApplicationType_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "https://eip.kehakiman.gov.my/services/".equals(namespaceURI) &&
                  "NewType1".equals(typeName)){
                   
                            return  integrasi.ws.mt.NewType1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "https://eip.kehakiman.gov.my/services/".equals(namespaceURI) &&
                  "applicationType_type1".equals(typeName)){
                   
                            return  integrasi.ws.mt.ApplicationType_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "https://eip.kehakiman.gov.my/services/".equals(namespaceURI) &&
                  "cardType_type1".equals(typeName)){
                   
                            return  integrasi.ws.mt.CardType_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "https://eip.kehakiman.gov.my/services/".equals(namespaceURI) &&
                  "NewType".equals(typeName)){
                   
                            return  integrasi.ws.mt.NewType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "https://eip.kehakiman.gov.my/services/".equals(namespaceURI) &&
                  "source_type1".equals(typeName)){
                   
                            return  integrasi.ws.mt.Source_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "https://eip.kehakiman.gov.my/services/".equals(namespaceURI) &&
                  "eventName_type1".equals(typeName)){
                   
                            return  integrasi.ws.mt.EventName_type1.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    