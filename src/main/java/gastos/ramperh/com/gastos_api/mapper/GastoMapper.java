package gastos.ramperh.com.gastos_api.mapper;

import gastos.ramperh.com.gastos_api.model.GastoModel;
import gastos.ramperh.com.gastos_api.dtos.SyncGastoData;
import gastos.ramperh.com.gastos_api.dtos.SyncMovimientoRequest;
import java.time.LocalDateTime;

public class GastoMapper {

	public static GastoModel map(SyncGastoData dto, String uuid) {
		if (dto == null) {
			return null;
		}

		GastoModel entity = new GastoModel();
		entity.setGastoUuid(uuid);
		entity.setDescription(dto.getDescription());
		entity.setAmount(dto.getAmount());
		entity.setDate(LocalDateTime.ofEpochSecond(dto.getDate(), 0, java.time.ZoneOffset.UTC));
		
		return entity;
	}

}