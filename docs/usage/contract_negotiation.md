# Contract Negotiation

Contract negotiation is the process of establishing an agreement between a provider and a consumer for asset usage.

## Initiate Contract Negotiation

```http
POST /v3/contractnegotiations
Content-Type: application/json

{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "@type": "https://w3id.org/edc/v0.0.1/ns/ContractRequest",
  "counterPartyAddress": "https://provider.dataspaces.think-it.io/api/dsp",
  "protocol": "dataspace-protocol-http",
  "policy": {
    "@context": "http://www.w3.org/ns/odrl.jsonld",
    "@type": "odrl:Offer",
    "@id": "offer-id",
    "assigner": "MDSLXXX.XXXXX",
    "odrl:permission": {
        "odrl:action": {
            "@id": "use"
        }
    },
    "odrl:prohibition": [],
    "odrl:obligation": [],
    "target": "asset-id"
  }
}
