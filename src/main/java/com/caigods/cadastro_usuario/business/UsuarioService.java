package com.caigods.cadastro_usuario.business;

//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.caigods.cadastro_usuario.infrastructure.entitys.Usuario;
import com.caigods.cadastro_usuario.infrastructure.repository.UsuarioRepository;

@Service // Obrigatório para indicar para o Spring que essa é a classe de serviço.

public class UsuarioService {
    // Injetar o repository dentro da classe SERVICE, existem 3 metodos, sendo eles
    // do Construtor, via Spring e via Lombok

    // Metodo CONSTRUTOR
    // Vantagem: Permite usar final no atributo (garante que ele não mude), facilita
    // testes e deixa claro o que a classe precisa para funcionar.

    // Injetar o repository dentro da classe SERVICE
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) { //
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {
        repository.saveAndFlush(usuario); // salva e fecha a conexão com o banco de dados.
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email não encontrado"));

    }

    public void deletarUsuarioPorEmail(String email) {
        repository.deleteByEmail(email);
    }

    //Usar o modo save para atualizar nao eh uma boa pratica, se tiver varios campos alem de id, como nome e email por exemplo
    //se atualizar e nao colocar todos novamente, ira perder tudo daquela linha
    //Entao usar esse metodo para verificar se existe algo em outros campos, se existir ele ja pega oq tem.
    public void atualizarUsuarioPorId(Integer id, Usuario usuario) {
        Usuario usuarioEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado."));

        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ?
                        usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ?
                        usuario.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                /*.telefone(usuarioEntity.getTelefone()!= null ?
                        usuario.getTelefone() : usuarioEntity.getTelefone())*/  // apenas um teste
                .build();

        repository.saveAndFlush(usuarioAtualizado);

    }

    /*
     * METODO via Spring
     *
     * //Vantagem: Muito fácil de escrever e deixa o código limpo visualmente.
     * //Desvantagem: É considerada uma má prática em projetos grandes porque
     * dificulta testes unitários e esconde as dependências da classe.
     *
     * @Autowired private UsuarioRepository repository; // Injeção direta no campo
     *
     *
     *
     *
     *
     *
     *
     * Para o metodo via Lombok Vantagem: É o melhor dos dois mundos. Você tem a
     * segurança da injeção por construtor com a limpeza visual do @Autowired.
     *
     *
     * @RequiredArgsConstructor (Antes do public class)
     *
     * "private final UsuarioRepository repository;"
     *
     */

}
