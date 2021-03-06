package analiseskillus.com.br.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import analiseskillus.com.br.dto.UsuarioTeste;
import analiseskillus.com.br.model.Usuario;
import analiseskillus.com.br.repository.filter.UsuarioFilter;


public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	
	public Usuario buscarComGrupos(Long codigo);
	
	public List<UsuarioTeste> listarUsuario();

}
