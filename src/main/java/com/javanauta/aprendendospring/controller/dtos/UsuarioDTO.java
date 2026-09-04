package com.javanauta.aprendendospring.controller.dtos;


//Classe apenas para transferencia
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String email;
    private String senha;
}
