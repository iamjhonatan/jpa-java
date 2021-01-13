package modelo.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlterarUsuario1 {
	
	public static void main (String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bd_jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, 7L);
		usuario.setNome("Leo");
		usuario.setEmail("leonardo@gmail.com");
		
		em.merge(usuario);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

}
