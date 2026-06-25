package gastos.ramperh.com.gastos_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gastos.ramperh.com.gastos_api.dtos.SyncMovimientoRequest;
import lombok.extern.slf4j.Slf4j;
import gastos.ramperh.com.gastos_api.services.GastoService;
import gastos.ramperh.com.gastos_api.services.AuthService;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class GastosController {
	private final GastoService gastoService;
	private final AuthService authService;
	public GastosController(GastoService serv, AuthService auth) {
		this.gastoService = serv;
		this.authService = auth;
	}
	@PostMapping("/gastos")
	public ResponseEntity<String> sincronizar(@RequestBody SyncMovimientoRequest request) {
		log.info("{}", request);
		try {
			if (!authService.Authentificar(request.getUsuario(), request.getClave())) {
				throw new Exception("Credenciales invalidas.");
			}
			this.gastoService.sincronizar(request);
			return ResponseEntity.ok("abc");
		}
		catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
	}
}