package gastos.ramperh.com.gastos_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gastos.ramperh.com.gastos_api.dtos.SyncMovimientoRequest;
import gastos.ramperh.com.gastos_api.dtos.Credenciales;
import lombok.extern.slf4j.Slf4j;
import gastos.ramperh.com.gastos_api.services.GastoService;
import gastos.ramperh.com.gastos_api.services.AuthService;
import java.util.Arrays;
import java.util.List;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

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
	public ResponseEntity<String> sincronizar(@RequestBody SyncMovimientoRequest request, @RequestHeader("Authorization") String authHeader) {
		log.info("{}", request);
		try {
			Credenciales credenciales = obtenerCredenciales(authHeader);
			if (!authService.Authentificar(credenciales.getUsuario(), credenciales.getClave())) {
				throw new Exception("Credenciales invalidas.");
			}
			this.gastoService.sincronizar(request);
			return ResponseEntity.ok("abc");
		}
		catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
	}

	private Credenciales obtenerCredenciales(String authHeader) throws Exception {
		if (authHeader == null && !authHeader.startsWith("Basic ")) {
			throw new Exception("Credenciales invalidas.");
		}
		String base64Credentials = authHeader.substring(6);
		byte[] decoded = Base64.getDecoder().decode(base64Credentials);
		String credentials = new String(decoded, StandardCharsets.UTF_8);
		String[] parts = credentials.split(":", 2);
		return new Credenciales(parts[0], parts[1]);
	}
}