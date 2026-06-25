package gastos.ramperh.com.gastos_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SyncMovimientoRequest {

	@NotNull
	@JsonProperty("movimiento_id")
	private Long movimientoId;

	@NotNull
	@JsonProperty("gasto_uuid")
	private String gastoUuid;

	@NotNull
	@JsonProperty("action_type")
	private String actionType;

	@NotNull
	@JsonProperty("timestamp")
	private Long timestamp;

	@JsonProperty("gasto")
	private SyncGastoData gasto;
}