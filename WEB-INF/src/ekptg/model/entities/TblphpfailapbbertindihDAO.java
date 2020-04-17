package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpfailapbbertindih entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpfailapbbertindih
 * @author MyEclipse Persistence Tools
 */

public class TblphpfailapbbertindihDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpfailapbbertindihDAO.class);
	// property constants
	public static final String BERTINDIH_DENGAN_FAIL = "bertindihDenganFail";
	public static final String NAMA_SYARIKAT_TINDIH = "namaSyarikatTindih";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpfailapbbertindih transientInstance) {
		log.debug("saving Tblphpfailapbbertindih instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpfailapbbertindih persistentInstance) {
		log.debug("deleting Tblphpfailapbbertindih instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpfailapbbertindih findById(java.lang.Long id) {
		log.debug("getting Tblphpfailapbbertindih instance with id: " + id);
		try {
			Tblphpfailapbbertindih instance = (Tblphpfailapbbertindih) getSession()
					.get("ekptg.model.entities.Tblphpfailapbbertindih", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpfailapbbertindih instance) {
		log.debug("finding Tblphpfailapbbertindih instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpfailapbbertindih").add(
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
		log.debug("finding Tblphpfailapbbertindih instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpfailapbbertindih as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBertindihDenganFail(Object bertindihDenganFail) {
		return findByProperty(BERTINDIH_DENGAN_FAIL, bertindihDenganFail);
	}

	public List findByNamaSyarikatTindih(Object namaSyarikatTindih) {
		return findByProperty(NAMA_SYARIKAT_TINDIH, namaSyarikatTindih);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpfailapbbertindih instances");
		try {
			String queryString = "from Tblphpfailapbbertindih";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpfailapbbertindih merge(Tblphpfailapbbertindih detachedInstance) {
		log.debug("merging Tblphpfailapbbertindih instance");
		try {
			Tblphpfailapbbertindih result = (Tblphpfailapbbertindih) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpfailapbbertindih instance) {
		log.debug("attaching dirty Tblphpfailapbbertindih instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpfailapbbertindih instance) {
		log.debug("attaching clean Tblphpfailapbbertindih instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}