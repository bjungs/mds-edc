# Assets

An asset represents a resource that can be shared within the Dataspace, such as a file or a service endpoint.

## Create an Asset

```http
POST /v3/assets
Content-Type: application/json

{
  "@context": {
    "@vocabulary": "https://w3id.org/edc/v0.0.1/ns/",

    "dcat": "http://www.w3.org/ns/dcat#",
    "dct": "http://purl.org/dc/terms/",
    "owl": "http://www.w3.org/2002/07/owl#",
    "mobilitydcatap": "https://w3id.org/mobilitydcat-ap/",
    "mobilitydcatap-theme": "https://w3id.org/mobilitydcat-ap/mobility-theme/", 
    "adms": "http://www.w3.org/ns/adms#",
    "edc": "https://w3id.org/edc/v0.0.1/ns/",
    "skos": "http://www.w3.org/2004/02/skos/core#",
    "rdf": "http://www.w3.org/2000/01/rdf-schema#"
  },
  "@id": "my-asset-id",
  "properties": {
    "dct:title": "My Asset",
    "dct:description": "Lorem Ipsum ...",
    "dct:language": "code/EN",
    "dct:publisher": "https://data-source.my-org/about",
    "dct:license": "https://data-source.my-org/license",
    "dct:rightsHolder": "my-sovereign-legal-name",
    "dct:accessRights": "usage policies and rights",
    "dct:spatial": {
      "skos:prefLabel": "my-geo-location",
      "dct:identifier": ["DE", "DE636"]
    },
    "dct:isReferencedBy": "https://data-source.my-org/references",
    "dct:temporal": {
      "dcat:startDate": "2024-02-01",
      "dcat:endDate": "2024-02-10"
    },
    "dct:accrualPeriodicity": "every month",
    
    "dcat:organization": "Company Name",
    "dcat:keywords": ["some", "keywords"],
    "dcat:mediaType": "application/json",
    "dcat:landingPage": "https://data-source.my-org/docs",
    
    "owl:versionInfo": "1.1",
    
    "mobilitydcatap:mobilityTheme": {
      "mobilitydcatap-theme:data-content-category": "Infrastructure and Logistics",
      "mobilitydcatap-theme:data-content-sub-category": "General Information About Planning Of Routes"
    },
    "mobilitydcatap:mobilityDataStandard": {
      "@id": "my-data-model-001",
      "mobilitydcatap:schema": {
        "dcat:downloadURL": [
          "https://teamabc.departmentxyz.schema/a",
          "https://teamabc.departmentxyz.schema/b"
        ],
        "rdf:Literal": "These reference files are important"
      }
    },
    "mobilitydcatap:transportMode": "my-transport-mode",
    "mobilitydcatap:georeferencingMethod": "my-geo-reference-method",
    
    "adms:sample": ["https://teamabc.departmentxyz.sample/a", "https://teamabc.departmentxyz.sample/b"],
    
    "additionalProperties": {}
  },
  "dataAddress": {
    "@type": "DataAddress",
    "type": "HttpData",
    "baseUrl": "https://example.com"
  }
}
```
