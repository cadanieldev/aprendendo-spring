package com.javanauta.aprendendospring.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")

public class Usuario implements UserDetails { // implementar o jjwt com userDetails na classe Usuario/principal

    @Id // identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerar identifador unico
    private Long id;
    @Column(name = "nome", length = 100) // nome da coluna
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "senha")
    private String senha;
    //Relacionamento onetoMany um usuario para muitos e oneToone um usuario para 1 endereço
    @OneToMany(cascade = CascadeType.ALL) // formato em cascade
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") // nome para cosntar na outra coluna e referenciando a coluna/tabela id
    private List<Endereco> enderecos;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
