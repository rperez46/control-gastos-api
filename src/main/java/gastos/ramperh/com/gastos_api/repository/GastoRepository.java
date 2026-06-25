package gastos.ramperh.com.gastos_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gastos.ramperh.com.gastos_api.model.GastoModel;

@Repository
public interface GastoRepository extends MongoRepository<GastoModel, String> { }