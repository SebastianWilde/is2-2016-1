package ucsp.is2.rest;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpEntity;

import ucsp.is2.service.LoginService;
import ucsp.is2.model.Usuario;
import ucsp.is2.repository.UsuarioRepository;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestBody;

class Log{
	public String email;
	public String password;
};

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	UsuarioRepository usuarioRepository;

	 @RequestMapping(value="/login", method=RequestMethod.POST)
	 public Usuario login(@RequestBody Log log)
	 {
	 	return loginService.login(log.email,log.password);
	}

	@RequestMapping(value = "login/{id}")
	public HttpEntity<Usuario> listAll(@PathVariable String email, String password) {
		Usuario usuario = usuarioRepository.findByEmailAndPassword(email,password);
		if (usuario == null) {
			return (HttpEntity<Usuario>) HttpEntity.EMPTY;
		}
		return new HttpEntity<Usuario>(usuario);
	}
}

