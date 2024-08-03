package br.com.web1.noticias.controller.authentication;

import br.com.web1.noticias.service.authentication.AuthenticationService;
import br.com.web1.noticias.service.usuario.dto.Usuariodto;
import br.com.web1.noticias.service.usuario.form.UsuarioForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


//    @PostMapping("/login")
//    public ResponseEntity<Void> login(){
//
//
//
//    }

    @PostMapping("/criar-conta")
    public ResponseEntity<Usuariodto> criarConta(@RequestBody  UsuarioForm form){

        var usuario = this.authenticationService.criarConta(form);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

    }
//
//    @PostMapping("recuperar-senha")
//    public ResponseEntity<String> recuperarSenha(){
//
//    }
}
