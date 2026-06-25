package gastos.ramperh.com.gastos_api.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "movimientos")
public class MovimientoModel {
	@Id
	private String id;
	private Long movimientoId;
	private String gastoUuid;
	private String actionType;
	private LocalDateTime timestamp;
	private GastoModel gasto;
}