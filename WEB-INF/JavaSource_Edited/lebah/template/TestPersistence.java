package lebah.template;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import etapp.data.PermohonanMobile;

public class TestPersistence {
	
	public static void main(String[] args) {
		EntityManager em;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
			em = emf.createEntityManager();
			String q = "SELECT p FROM PermohonanMobile p WHERE p.pemohon.idMasuk = (SELECT a.id FROM UsersMobile a WHERE a.uLogin = '820731045135')";
			List<PermohonanMobile> list = em.createQuery(q).getResultList();
			
			System.out.println(list.size());
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}


	}
	
}