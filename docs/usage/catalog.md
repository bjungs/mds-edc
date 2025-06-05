# Catalog

The catalog endpoint allows consumers to discover available assets.

## Request Catalog

```http
POST /v3/catalog/request
Content-Type: application/json

{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "@type": "CatalogRequest",
  "counterPartyAddress": "https://provider.dataspaces.think-it.io/api/dsp",
  "counterPartyId": "MY_MDS_ID",
  "protocol": "dataspace-protocol-http",
  "additionalScopes": [
  ]
}
