package gastos.ramperh.com.gastos_api.services;

import gastos.ramperh.com.gastos_api.dtos.SyncMovimientoRequest;
import gastos.ramperh.com.gastos_api.dtos.SyncGastoData;
import gastos.ramperh.com.gastos_api.model.GastoModel;
import gastos.ramperh.com.gastos_api.model.MovimientoModel;
import gastos.ramperh.com.gastos_api.repository.GastoRepository;
import gastos.ramperh.com.gastos_api.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gastos.ramperh.com.gastos_api.mapper.GastoMapper;
import gastos.ramperh.com.gastos_api.mapper.MovimientoMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GastoService {

	private final GastoRepository gastoRepository;
	private final MovimientoRepository auditRepository;

	public GastoService(GastoRepository gastoRepository, MovimientoRepository auditRepository) {
		this.gastoRepository = gastoRepository;
		this.auditRepository = auditRepository;
	}

	@Transactional
	public void sincronizar(SyncMovimientoRequest dto) throws Exception {
		GastoModel gastoEntity = null;
		log.info("{}", "Mapeando el gasto.");
		if (dto.getGasto() != null) {
			gastoEntity = GastoMapper.map(dto.getGasto(), dto.getGastoUuid());
		}

		log.info("{}", "Mapeando el movimiento.");
		MovimientoModel audit = MovimientoMapper.map(dto);
		


		String action = dto.getActionType().toUpperCase();
		log.info("{} {}", "Accion", action);
		switch (action) {
			case "CREATE":
				if (gastoRepository.existsById(gastoEntity.getGastoUuid())) {
					throw new Exception("Cannot CREATE: Record already exists with ID: " + gastoEntity.getGastoUuid());
				}
				gastoRepository.save(gastoEntity);
				break;
			case "EDIT":
				if (!gastoRepository.existsById(gastoEntity.getGastoUuid())) {
					throw new Exception("Cannot EDIT: Record does not exist with ID: " + gastoEntity.getGastoUuid());
				}
				gastoRepository.save(gastoEntity);
				break;
			case "DELETE":
				if (!gastoRepository.existsById(dto.getGastoUuid())) {
					throw new Exception("Cannot DELETE: Record does not exist with ID: " + dto.getGastoUuid());
				}
				gastoRepository.deleteById(dto.getGastoUuid());
				break;

			default:
				throw new Exception("Unsupported action type: " + action);
		}
		auditRepository.save(audit);
	}
}