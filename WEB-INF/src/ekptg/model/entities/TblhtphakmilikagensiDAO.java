package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtphakmilikagensi entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtphakmilikagensi
 * @author MyEclipse Persistence Tools
 */

public class TblhtphakmilikagensiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtphakmilikagensiDAO.class);
	// property constants
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String LUAS_ASAL = "luasAsal";
	public static final String LUAS_BARU = "luasBaru";
	public static final String ID_AGENSI = "idAgensi";
	public static final String ID_KEMENTERIAN = "idKementerian";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtphakmilikagensi transientInstance) {
		log.debug("saving Tblhtphakmilikagensi instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtphakmilikagensi persistentInstance) {
		log.debug("deleting Tblhtphakmilikagensi instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtphakmilikagensi findById(java.lang.Long id) {
		log.debug("getting Tblhtphakmilikagensi instance with id: " + id);
		try {
			Tblhtphakmilikagensi instance = (Tblhtphakmilikagensi) getSession()
					.get("ekptg.model.entities.Tblhtphakmilikagensi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtphakmilikagensi instance) {
		log.debug("finding Tblhtphakmilikagensi instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtphakmilikagensi").add(
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
		log.debug("finding Tblhtphakmilikagensi instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtphakmilikagensi as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByLuasAsal(Object luasAsal) {
		return findByProperty(LUAS_ASAL, luasAsal);
	}

	public List findByLuasBaru(Object luasBaru) {
		return findByProperty(LUAS_BARU, luasBaru);
	}

	public List findByIdAgensi(Object idAgensi) {
		return findByProperty(ID_AGENSI, idAgensi);
	}

	public List findByIdKementerian(Object idKementerian) {
		return findByProperty(ID_KEMENTERIAN, idKementerian);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtphakmilikagensi instances");
		try {
			String queryString = "from Tblhtphakmilikagensi";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtphakmilikagensi merge(Tblhtphakmilikagensi detachedInstance) {
		log.debug("merging Tblhtphakmilikagensi instance");
		try {
			Tblhtphakmilikagensi result = (Tblhtphakmilikagensi) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtphakmilikagensi instance) {
		log.debug("attaching dirty Tblhtphakmilikagensi instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtphakmilikagensi instance) {
		log.debug("attaching clean Tblhtphakmilikagensi instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}