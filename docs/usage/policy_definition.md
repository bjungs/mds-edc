# Policy Definition

A policy definition specifies the rules and conditions for accessing an asset.

## MDS Policies

## Create a Policy Definition

```http
POST /v3/policydefinitions
Content-Type: application/json

{
  "@context": {
    "edc": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "@id": "aPolicy",
  "policy": {
    "@context":"http://www.w3.org/ns/odrl.jsonld",
    "@type":"Set",
    "permission":[{
      "action":"use",
      "constraint":[{
        "and":[{
          "leftOperand":"REFERRING_CONNECTOR",
          "operator":"odrl:eq",
          "rightOperand":"MDSLXXX.XXXXX"
        },
        {
          "leftOperand":"POLICY_EVALUATION_TIME",
          "operator":"odrl:lt",
          "rightOperand":"26/06/2025"
        }]
      }]
    }],
    "obligation":[],
    "prohibition":[]
  }
}

