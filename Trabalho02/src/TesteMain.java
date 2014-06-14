import br.com.trabalho02.entidade.Diretorio;
import br.com.trabalho02.entidade.Usuario;
import br.com.trabalho02.repository.DiretorioRepository;
import br.com.trabalho02.repository.DiretorioRepositoryImpl;
import br.com.trabalho02.repository.UsuarioRepository;
import br.com.trabalho02.repository.UsuarioRepositoryImpl;


public class TesteMain {

	public static void main(String[] args) {

		Usuario usuario = new Usuario();
		usuario.setLogin("login");
		usuario.setSenha("senha");

		UsuarioRepository repository = new UsuarioRepositoryImpl();
		
		try {
			repository.save(usuario);
			
			Usuario usuario2 = repository.obterPorCampo(Usuario.class, "login", usuario.getLogin());
			System.out.println("Usuario2Id: "+ usuario2.getId());
			System.out.println("Usuario2Login: "+ usuario2.getLogin());
			System.out.println("Usuario2Senha: "+ usuario2.getSenha());
			
			Diretorio dir = new Diretorio();
			dir.setNome("teste");
			
			DiretorioRepository repos = new DiretorioRepositoryImpl();
			repos.save(dir);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
