package gastos.ramperh.com.gastos_api.services;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import lombok.Data;

@Data
@Slf4j
@Service
public class AuthService {
	@Value("${usuario}")
	private String usuario;
	@Value("${clave}")
	private String clave;

	// Implementacion temporal.
	public boolean Authentificar(String usuario, String clave) {
		if (this.usuario.equals(usuario) && this.clave.equals(clave)) {
			return true;
		}
		log.info("no");
		return false;
	}
}