package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtminitmesyuaratpara entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtminitmesyuaratpara
 * @author MyEclipse Persistence Tools
 */

public class TblpdtminitmesyuaratparaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtminitmesyuaratparaDAO.class);
	// property constants
	public static final String PARA = "para";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
        public static final String PIHAK_BERTINDAK = "pihakBertindak";
        public static final String MAKLUMBALAS = "maklumbalas";

	public void save(Tblpdtminitmesyuaratpara transientInstance) {
		log.debug("saving Tblpdtminitmesyuaratpara instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtminitmesyuaratpara persistentInstance) {
		log.debug("deleting Tblpdtminitmesyuaratpara instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtminitmesyuaratpara findById(java.math.BigDecimal id) {
		log.debug("getting Tblpdtminitmesyuaratpara instance with id: " + id);
		try {
			Tblpdtminitmesyuaratpara instance = (Tblpdtminitmesyuaratpara) getSession()
					.get("ekptg.model.entities.Tblpdtminitmesyuaratpara", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtminitmesyuaratpara instance) {
		log.debug("finding Tblpdtminitmesyuaratpara instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtminitmesyuaratpara").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tblpdtminitmesyuaratpara instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtminitmesyuaratpara as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPara(Object para) {
		return findByProperty(PARA, para);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}
        public List findByPihakBertindak(Object pihakBertindak) {
                return findByProperty(PIHAK_BERTINDAK, pihakBertindak);
        }
        public List findByMaklumbalas(Object maklumbalas) {
                return findByProperty(MAKLUMBALAS, maklumbalas);
        }

	public List findAll() {
		log.debug("finding all Tblpdtminitmesyuaratpara instances");
		try {
			String queryString = "from Tblpdtminitmesyuaratpara";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtminitmesyuaratpara merge(
			Tblpdtminitmesyuaratpara detachedInstance) {
		log.debug("merging Tblpdtminitmesyuaratpara instance");
		try {
			Tblpdtminitmesyuaratpara result = (Tblpdtminitmesyuaratpara) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtminitmesyuaratpara instance) {
		log.debug("attaching dirty Tblpdtminitmesyuaratpara instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtminitmesyuaratpara instance) {
		log.debug("attaching clean Tblpdtminitmesyuaratpara instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}