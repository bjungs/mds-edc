# Contract Definition

A contract definition links assets with usage policies.

## Create a Contract Definition

```http
POST /v3/contractdefinitions
Content-Type: application/json

{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "@id": "definition-id",
  "accessPolicyId": "aPolicy",
  "contractPolicyId": "aPolicy",
  "assetsSelector": [
    {
      "operandLeft":"@id",
      "operator":"in",
      "operandRight":"asset-id"
    }
  ],
  "privateProperties": {
    "manualApproval": "false"
  }
}
