package univ.kgu.carely;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@OpenAPIDefinition(
		servers = {
				@Server(url = "/", description = "Default Server url")
		}
)
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO) // 페이징 관련 설정
public class CarelyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarelyApplication.class, args);
	}

}
