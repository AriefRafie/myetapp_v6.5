package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujbank entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujbank
 * @author MyEclipse Persistence Tools
 */

public class TblrujbankDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujbankDAO.class);
	// property constants
	public static final String KOD_BANK = "kodBank";
	public static final String NAMA_BANK = "namaBank";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";

	public void save(Tblrujbank transientInstance) {
		log.debug("saving Tblrujbank instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujbank persistentInstance) {
		log.debug("deleting Tblrujbank instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujbank findById(java.math.BigDecimal id) {
		log.debug("getting Tblrujbank instance with id: " + id);
		try {
			Tblrujbank instance = (Tblrujbank) getSession().get(
					"ekptg.model.entities.Tblrujbank", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujbank instance) {
		log.debug("finding Tblrujbank instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujbank").add(
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
		log.debug("finding Tblrujbank instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblrujbank as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodBank(Object kodBank) {
		return findByProperty(KOD_BANK, kodBank);
	}

	public List findByNamaBank(Object namaBank) {
		return findByProperty(NAMA_BANK, namaBank);
	}

	public List findByAlamat1(Object alamat1) {
		return findByProperty(ALAMAT1, alamat1);
	}

	public List findByAlamat2(Object alamat2) {
		return findByProperty(ALAMAT2, alamat2);
	}

	public List findByAlamat3(Object alamat3) {
		return findByProperty(ALAMAT3, alamat3);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findAll() {
		log.debug("finding all Tblrujbank instances");
		try {
			String queryString = "from Tblrujbank";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujbank merge(Tblrujbank detachedInstance) {
		log.debug("merging Tblrujbank instance");
		try {
			Tblrujbank result = (Tblrujbank) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujbank instance) {
		log.debug("attaching dirty Tblrujbank instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujbank instance) {
		log.debug("attaching clean Tblrujbank instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}