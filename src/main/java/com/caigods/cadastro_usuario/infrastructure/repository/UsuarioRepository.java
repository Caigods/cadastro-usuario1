package com.caigods.cadastro_usuario.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.caigods.cadastro_usuario.infrastructure.entitys.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{  	//Primeiro o nome da tabela no qual o repository se refere, 
																						//e ao tipo do ID gerado na classe entitys.usuario
																						//O JpaRepository já tem muitos métodos prontos como salvar, deletar...
	
// O optional evita o null pointer exception
//Sempre que usar o Optional, é obrigado a criar uma exceção ou uma alternativa caso não encontre o "email"	
Optional<Usuario> findByEmail (String email);


	@Transactional  //Se gerar qualquer erro, ele não pode deletar essa email.
void deleteByEmail(String email);

}
