package univ.kgu.carely;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO) // 페이징 관련 설정
public class CarelyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarelyApplication.class, args);
	}

}
