package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DAO <E>{ // Usando o generics para definir essa classe como 'sem tipo', facilitando o uso dentro de outras classes futuramente.
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static { // bloco estatico: chama o que esta no bloco apenas uma vez, quando a classe for carregada pelo java.
		try {
			emf = Persistence.createEntityManagerFactory("bd_jpa");
		} catch (Exception e) {
			
		}
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO<E> abrirT() {
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecharT() {
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> incluir(E entidade) {
		em.persist(entidade);
		return this;
	}
	
	public DAO<E> incluirAtomico(E entidade) {
		return this.abrirT().incluir(entidade).fecharT();
	}
	
	public E obterPorID(Object id) {
		return em.find(classe, id);
	}
	
	public List<E> obterTodos(){
		return this.obterTodos(10, 0);
	}
	
	public List<E> obterTodos(int qtde, int deslocamento) {
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		return query.getResultList();
	}
	
	public void fechar() {
		em.close();
	}

}
