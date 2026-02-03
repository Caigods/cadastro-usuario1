package com.caigods.cadastro_usuario.infrastructure.entitys;

import jakarta.persistence.*;

import lombok.*;

@Getter                    //buscar as informações seguras
@Setter                    //alterar as informações seguras
@AllArgsConstructor        //construtores para acessar a classe
@NoArgsConstructor        //construtores para acessar a classe
@Builder                    //fazer update
@Table(name = "usuario")    //indica que é uma tabela
@Entity                    //informa que a classe é uma entidade do banco de dados

public class Usuario {

    @Id                    //NÃO EXISTE TABELA SEM ID. Identificador únicos de cada campo da tabela
    @GeneratedValue(strategy = GenerationType.AUTO) //o Id é gerado automaticamente ao chegar no repository
    private Integer id;

    @Column(name = "email", unique = true)
    //unique é exatamente ÚNICO. Não se pode cadastrar 2 usuários com o mesmo email
    private String email;

    @Column(name = "nome")
    private String nome;

   /* @Column (name = "telefone")
    private String telefone;
    */

    //@Column(name= "cpf", unique =true)
    //private String cpf;


}
