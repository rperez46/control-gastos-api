package gastos.ramperh.com.gastos_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
public class SyncGastoData {

	@JsonProperty("description")
	private String description;

	@NotNull
	@JsonProperty("amount")
	private Double amount;

	@NotNull
	@JsonProperty("date")
	private Long date;
}