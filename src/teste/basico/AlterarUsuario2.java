package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario2 {
	
	// Objeto em estado gerenciado ainda é atualizado mesmo que o merge não seja feito
	public static void main (String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bd_jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, 7L);
		usuario.setNome("Leo");
		
		//em.merge(usuario);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}
}
