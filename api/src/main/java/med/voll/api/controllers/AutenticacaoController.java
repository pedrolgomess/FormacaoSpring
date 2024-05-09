package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosUsuario dadosUsuario){
        var token = new UsernamePasswordAuthenticationToken(dadosUsuario.login(), dadosUsuario.senha());
        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();

    }
}
