package com.caigods.cadastro_usuario.controler;

import com.caigods.cadastro_usuario.business.UsuarioService;
import com.caigods.cadastro_usuario.infrastructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
/* Metodo construtor
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }*/
    //Mas nesse caso vou utilizar o metodo do Lombok, usando o @RequiredArgsConstructor
    private final UsuarioService usuarioService;
    @PostMapping //Para guardar dados
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email){
        String emailNormal = email.toLowerCase();

        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(emailNormal));

    }
    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email){
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Void> atualizarUsuarioPorEmail(@RequestParam Integer id,
                                                         @RequestBody  Usuario usuario){
        usuarioService.atualizarUsuarioPorId(id,usuario);
        return ResponseEntity.ok().build();
    }
}
