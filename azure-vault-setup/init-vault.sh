#!/bin/bash

# Generates the required certificates used by the Connector.
# Put the connector's p12 file here, and name it "mds-connector.p12".
# Make sure you are logged in with azure CLI (az login).

# Exit immediately if a command exits with a non-zero status
set -e

# Generate required files
echo "Generating required files..."

# Generate private key and certificate for transfer proxy
openssl genpkey -algorithm RSA -out private-key.pem
openssl req -new -x509 -key private-key.pem -out cert.pem -days 365 -subj "/CN=transfer-proxy"

# Extract private key and certificate from keystore for DAPS
echo "Extracting private key and certificate from keystore for DAPS..."
openssl pkcs12 -in mds-connector.p12 -nocerts -out daps.key -nodes -passin pass:"F7!R>.A1cP{R!pC[KfmN"
openssl pkcs12 -in mds-connector.p12 -clcerts -nokeys -out daps.cert -passin pass:"F7!R>.A1cP{R!pC[KfmN"

## remove bag attributes
cat daps.key | sed '1,3d' > clean.key
mv clean.key daps.key

cat daps.cert | sed '1,4d' > clean.key
mv clean.key daps.cert

echo "Required files generated and extracted."

echo "Adding secrets to Vault..."
az keyvault secret set --vault-name kv-aks-megabits --name "transfer-proxy-token-signer-private-key" --file "private-key.pem"
az keyvault secret set --vault-name kv-aks-megabits --name "transfer-proxy-token-signer-public-key" --file "cert.pem"
az keyvault secret set --vault-name kv-aks-megabits --name "daps-public-key" --file "daps.cert"
az keyvault secret set --vault-name kv-aks-megabits --name "daps-private-key" --file "daps.key"

echo "Vault initialization complete."
