package gastos.ramperh.com.gastos_api.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "gastos")
public class GastoModel {
	@Id
	private String gastoUuid;
	private String description;
	private Double amount;
	private LocalDateTime date;
}