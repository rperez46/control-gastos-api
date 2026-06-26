package gastos.ramperh.com.gastos_api.dtos;
import lombok.Data;


@Data
public class Credenciales {
	public String usuario;
	public String clave;
	public Credenciales(String u, String c) {
		usuario = u;
		clave = c;
	}
}
