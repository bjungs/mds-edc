package eu.dataspace.connector.tests;

import org.eclipse.edc.connector.controlplane.test.system.utils.Participant;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.spi.EdcException;
import org.eclipse.edc.spi.security.Vault;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;
import org.eclipse.edc.spi.system.configuration.Config;
import org.eclipse.edc.spi.system.configuration.ConfigFactory;
import org.jetbrains.annotations.NotNull;

import java.security.AsymmetricKey;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

import static java.util.Map.entry;
import static org.eclipse.edc.util.io.Ports.getFreePort;

public class MdsParticipant extends Participant {

    private MdsParticipant() {

    }

    public Config getConfiguration() {
        var map = Map.ofEntries(
                entry("edc.participant.id", id),
                entry("web.http.path", "/api"),
                entry("web.http.port", getFreePort() + ""),
                entry("web.http.control.path", "/control"),
                entry("web.http.control.port", getFreePort() + ""),
                entry("web.http.management.path", controlPlaneManagement.get().getPath()),
                entry("web.http.management.port", controlPlaneManagement.get().getPort() + ""),
                entry("web.http.protocol.path", controlPlaneProtocol.get().getPath()),
                entry("web.http.protocol.port", controlPlaneProtocol.get().getPort() + ""),
                entry("web.http.version.path", "/version"),
                entry("web.http.version.port", getFreePort() + ""),
                entry("web.http.public.path", "/public"),
                entry("web.http.public.port", getFreePort() + ""),
                entry("edc.transfer.proxy.token.verifier.publickey.alias", "public-key-alias"),
                entry("edc.transfer.proxy.token.signer.privatekey.alias", "private-key-alias")
        );

        return ConfigFactory.fromMap(map);
    }

    public ServiceExtension seedVaultKeys() {
        return new SeedVaultKeys();
    }

    public static class Builder extends Participant.Builder<MdsParticipant, Builder> {

        public static Builder newInstance() {
            return new Builder(new MdsParticipant());
        }

        protected Builder(MdsParticipant participant) {
            super(participant);
        }

    }

    private static class SeedVaultKeys implements ServiceExtension {

        @Inject
        private Vault vault;

        @Override
        public void initialize(ServiceExtensionContext context) {
            try {
                var kpg = KeyPairGenerator.getInstance("RSA");
                kpg.initialize(2048);
                var keyPair = kpg.generateKeyPair();

                var privateKey = encode(keyPair.getPrivate());
                var publicKey = encode(keyPair.getPublic());

                vault.storeSecret("private-key-alias", privateKey);
                vault.storeSecret("public-key-alias", publicKey);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private static @NotNull String encode(AsymmetricKey key) {
            var type = switch (key) {
                case PublicKey _ -> "PUBLIC";
                case PrivateKey _ -> "PRIVATE";
                default -> throw new EdcException("not possible");
            };

            return """
            -----BEGIN %s KEY-----
            %s
            -----END %s KEY-----
            """.formatted(type, Base64.getMimeEncoder().encodeToString(key.getEncoded()), type);
        }
    }
}
