package univ.kgu.carely.domain.health;


import io.swagger.v3.oas.annotations.tags.Tag;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@Tag(name = "상태 API", description = "상태 확인하는 API 입니다.")
public class HealthController {

    private final DataSource dataSource;
    private final WebClient webClient;

    public HealthController(DataSource dataSource, @Qualifier("carelyAI") WebClient webClient) {
        this.dataSource = dataSource;
        this.webClient = webClient;
    }

    @GetMapping("/")
    public ResponseEntity<String> homeCheck() {
        return new ResponseEntity<>("Home Screen", HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/db")
    public ResponseEntity<String> dbCheck() {
        try {
            Connection connection = dataSource.getConnection();
            if (connection != null && !connection.isClosed()) {
                return ResponseEntity.ok("DB Healthy");
            }
        } catch (SQLException e) {
            return ResponseEntity.ok("DB Not Healthy");
        }

        return ResponseEntity.ok("DB Not Healthy");
    }

    @GetMapping("/carely-ai")
    public ResponseEntity<String> carelyAiCheck() {
        Boolean connected = webClient.get()
                .uri("/docs")
                .retrieve()
                .toEntity(String.class)
                .block(Duration.ofSeconds(10))
                .getStatusCode()
                .is2xxSuccessful();
        if (connected != null && connected) {
            return ResponseEntity.ok("Carely_AI connection Healthy");
        }
        return ResponseEntity.ok("Carely_AI connection Not Healthy");
    }
}