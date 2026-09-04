package com.javanauta.aprendendospring.controller;

//REST controller = create/post no crud
//Receber os dados = porta de entrada do negocio/requisições

import com.javanauta.aprendendospring.business.UsuarioService;
import com.javanauta.aprendendospring.controller.dtos.UsuarioDTO;
import com.javanauta.aprendendospring.infrastructure.entity.Usuario;
import com.javanauta.aprendendospring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")    //Apontar a URI
@RequiredArgsConstructor

public class UsuarioController {

    //Insercao de dependecias da service e mandar pro repository
    private final UsuarioService usuarioService;
    //Insercao da authentcation manager
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    //metodo salvar usuario
    @PostMapping
    //Response entity para retornar uma resposta http padrao REST
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario) { // requstbody passar o objeto usario dentro do body da requisição
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }

    //login
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                usuarioDTO.getSenha())

        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName()); // Sistema de login
    }

    @GetMapping // anotacao metodo get
    public ResponseEntity<Usuario> buscaUsuarioPorEmail(@RequestParam("email") String email) { //
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));

    } // Metodo get pronto buscando o email com o find e optional de exception

    @DeleteMapping("/{email}") // metodo delete com aspas e chaves
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email) {
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build(); // retorna algum erro
    }
}
