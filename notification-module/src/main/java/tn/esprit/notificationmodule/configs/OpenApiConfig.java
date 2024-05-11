package tn.esprit.notificationmodule.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact (
                        name = "Amine Rom",
                        email = "ramdhaniahmedamine@gmail.com"
                ),
                description = "OpenApi Doc for notification service",
                title = "Notification Api specification",
                version = "0.1",
                termsOfService = "empty as of now !"
        ),
        servers = {
                @Server(
                        description = "Local Dev evnironment url",
                        url = "http://apigateway:8060/notification"
                )
        }
)
public class OpenApiConfig {
}
