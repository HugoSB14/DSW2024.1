package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidade.Caneta;
import util.JPAUtil;

public class CanetaDao {
	public static void salvar(Caneta caneta) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(caneta);
		em.getTransaction().commit();
		em.close();
	}
	
	 public static void excluir(Caneta caneta) {
	        EntityManager em = JPAUtil.criarEntityManager();
	        em.getTransaction().begin();
	        caneta = em.find(Caneta.class, caneta.getId());
	        em.remove(caneta);
	        em.getTransaction().commit();
	        em.close();
	    }
	 
	 public static List<Caneta> listar() {
			EntityManager em = JPAUtil.criarEntityManager();
			Query q = em.createQuery("select c from Caneta c");
			List<Caneta> resultado = q.getResultList();
			em.close();
			return resultado;
		}
	 
	 public static Map<String, Long> contarCanetasPorCor() {
		    EntityManager em = JPAUtil.criarEntityManager();
		    try {
		        TypedQuery<Object[]> query = em.createQuery(
		            "SELECT c.cor, COUNT(c) FROM Caneta c GROUP BY c.cor", Object[].class);
		        List<Object[]> results = query.getResultList();

		        Map<String, Long> contagens = new HashMap<>();
		        for (Object[] result : results) {
		            contagens.put((String) result[0], (Long) result[1]);
		        }
		        return contagens;
		    } finally {
		        em.close();
		    }
		}
}