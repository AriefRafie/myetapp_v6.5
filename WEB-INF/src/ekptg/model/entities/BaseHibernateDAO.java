package ekptg.model.entities;

import org.hibernate.Session;

import ekptg.model.HibernateSessionFactory;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
        Session currentSession;

	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
        public Session getSession2() {
                return HibernateSessionFactory.getSessionFactory().getCurrentSession();

        }
}