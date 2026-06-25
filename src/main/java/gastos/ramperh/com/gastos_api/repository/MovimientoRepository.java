package gastos.ramperh.com.gastos_api.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gastos.ramperh.com.gastos_api.model.MovimientoModel;

@Repository
public interface MovimientoRepository extends MongoRepository<MovimientoModel, Long> {}