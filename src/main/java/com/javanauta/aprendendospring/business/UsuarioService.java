package com.javanauta.aprendendospring.business;

//Duas anotaçoes service e insercao de depedencias trazer a repository pra dentro da service, utilizar ela chamando

import com.javanauta.aprendendospring.infrastructure.entity.Usuario;
import com.javanauta.aprendendospring.infrastructure.exceptions.ConflictException;
import com.javanauta.aprendendospring.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

//Fzer atraves de um construtor consegue trbalhar melhor nos testes unitarios
@Service
@RequiredArgsConstructor // gera um construtor que inicializa os campos que tem o private final
public class UsuarioService {

//    @Autowired
//    private UsuarioRepository usuarioRepository; // chamando e dando o nome pra ela, geralmente o mesmo nome, porem minusculo

    private final UsuarioRepository usuarioRepository; // final traz a imutação
    private final PasswordEncoder passwordEncoder;

    //metodo de salvar usuario/ create
    public Usuario salvaUsuario(Usuario usuario) {
        try {
            //criar um metodo para verificar isso na repository
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario); //passando o usuario que recebemos como parametro
        } catch (ConflictException e){
            throw new ConflictException("Email já cadastrado! " , e.getCause());

        }
    } // retorno com os dados do usiaro

    //aplicar uma excecao de regra de negocio de conflito

    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("Email já cadastrado! " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado! " + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){ // chamar a funcao na repositorio
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado." + email)); // busca e optional evita de retorno nulo com o else throw, criar um exception notfound
    }


    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

}
