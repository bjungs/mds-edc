# Management API Overview

## Introduction

This walkthrough attempts to be a reference for systems integrators attempting to expose APIs safely to the internet. It is not a comprehensive guide to the EDC, but rather a starting point for understanding how to use the Management API to create and manage assets, policies, and contract definitions.

The EDC implements the [DataSpace Protocol (DSP)](https://github.com/International-Data-Spaces-Association/ids-specification/tree/main/protocol/v0.9.1), as specified by the IDSA. As the DSP uses JSON-LD for all payloads, the EDC Management API reflects this as well, even though it is not a part of the DSP.

## Endpoints

The `MANAGEMENT_URL` specifies the URL of the management API and the prefixes `v3`, `v3.1alpha` and `v4alpha` respect the fact that the endpoints are currently versioned independently of each other.

| Resource | Endpoint | 
|----------|----------|
| Asset | `<MANAGEMENT_URL>/v3/assets` | 
| Policy Definition | `<MANAGEMENT_URL>/v3/policydefinitions` |
| Contract Definition | `<MANAGEMENT_URL>/v3/contractdefinitions` |
| Catalog | `<MANAGEMENT_URL>/v3/catalog` |
| Contract Negotiation | `<MANAGEMENT_URL>/v3/contractnegotiations` |
| Contract Agreement | `<MANAGEMENT_URL>/v3/contractagreements` |
| Transfer Process | `<MANAGEMENT_URL>/v3/transferprocesses` | 
| EDR | `<MANAGEMENT_URL>/v3/edrs` | 
| Agreements Retirement | `<MANAGEMENT_URL>/v3.1alpha/retireagreements` |
| EDP (Experimental) | `<MANAGEMENT_URL>/edp` |


## Brief JSON-LD Introduction

JSON-LD (JSON for Linked Data) is an extension of JSON that introduces a set of principles and mechanisms to serialize RDF-graphs and thus open new opportunities for interoperability. As such, there is a clear separation into identifiable resources (IRIs) and Literals holding primitive data like strings or integers.For developers used to working with JSON, JSON-LD can act in unexpected ways, for example a list with one entry will always unwrap to an object which may cause schema validation to fail on the client side. Please also refer to the [JSON-LD spec](https://json-ld.org/spec/latest/json-ld/) and try it out on the [JSON-LD Playground](https://json-ld.org/playground/).
