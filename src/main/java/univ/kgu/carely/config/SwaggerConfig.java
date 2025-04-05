package univ.kgu.carely.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info())
                .components(new Components()
                        .addSecuritySchemes("apiKey",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")
                                        .description("JWT 토큰을 입력하세요 (예: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...)")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("apiKey"))
                .paths(loginPath()); // 로그인 경로 추가
    }

    private Paths loginPath() {
        return new Paths()
                .addPathItem("/api/login", new PathItem()
                        .post(new Operation()
                                .tags(List.of("Authentication"))
                                .summary("로그인")
                                .description("사용자 로그인을 위한 엔드포인트")
                                .operationId("login")
                                .requestBody(new RequestBody()
                                        .content(new Content()
                                                .addMediaType("application/json",
                                                        new MediaType()
                                                                .schema(new Schema<>()
                                                                        .type("object")
                                                                        .addProperties("username",
                                                                                new Schema<>().type("string"))
                                                                        .addProperties("password",
                                                                                new Schema<>().type("string")
                                                                                        .format("password"))
                                                                        .required(List.of("username", "password"))
                                                                )
                                                )
                                        )
                                        .required(true)
                                )
                                .responses(new ApiResponses()
                                        .addApiResponse("200", new ApiResponse()
                                                .description("로그인 성공")
                                                .content(new Content()
                                                        .addMediaType("application/json",
                                                                new MediaType()
                                                                        .schema(new Schema<>()
                                                                                .type("object")
                                                                                .addProperties("token",
                                                                                        new Schema<>().type("string"))
                                                                        )
                                                        )
                                                )
                                        )
                                        .addApiResponse("401", new ApiResponse().description("인증 실패"))
                                )
                        )
                );
    }

    public Info info() {
        return new Info()
                .title("Carely")
                .description("2025년 심화캡스톤디자인 Carely")
                .version("0.0.1");
    }
}
