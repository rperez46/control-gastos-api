package gastos.ramperh.com.gastos_api.mapper;

import gastos.ramperh.com.gastos_api.model.GastoModel;
import gastos.ramperh.com.gastos_api.model.MovimientoModel;
import gastos.ramperh.com.gastos_api.dtos.SyncGastoData;
import gastos.ramperh.com.gastos_api.dtos.SyncMovimientoRequest;
import java.time.LocalDateTime;

public class MovimientoMapper {

	public static MovimientoModel map(SyncMovimientoRequest dto) {
		if (dto == null) {
			return null;
		}

		MovimientoModel audit = new MovimientoModel();
		audit.setMovimientoId(dto.getMovimientoId());
		audit.setGastoUuid(dto.getGastoUuid());
		audit.setActionType(dto.getActionType());
		audit.setTimestamp(LocalDateTime.ofEpochSecond(dto.getTimestamp(), 0, java.time.ZoneOffset.UTC));
		
		if (dto.getGasto() != null) {
			audit.setGasto(GastoMapper.map(dto.getGasto(), dto.getGastoUuid()));
		}

		return audit;
	}
}