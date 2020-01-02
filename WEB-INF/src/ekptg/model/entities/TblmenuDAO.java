package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblmenu entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblmenu
 * @author MyEclipse Persistence Tools
 */

public class TblmenuDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblmenuDAO.class);
	// property constants
	public static final String ID_MENU_ATAS = "idMenuAtas";
	public static final String NAMA_MENU = "namaMenu";
	public static final String ALAMAT_MENU = "alamatMenu";
	public static final String ALAMAT_IKON = "alamatIkon";
	public static final String ATURAN = "aturan";
	public static final String STATUS = "status";
	public static final String KOD_MENU = "kodMenu";

	public void save(Tblmenu transientInstance) {
		log.debug("saving Tblmenu instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblmenu persistentInstance) {
		log.debug("deleting Tblmenu instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblmenu findById(java.lang.Long id) {
		log.debug("getting Tblmenu instance with id: " + id);
		try {
			Tblmenu instance = (Tblmenu) getSession().get(
					"ekptg.model.entities.Tblmenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblmenu instance) {
		log.debug("finding Tblmenu instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblmenu").add(
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
		log.debug("finding Tblmenu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblmenu as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdMenuAtas(Object idMenuAtas) {
		return findByProperty(ID_MENU_ATAS, idMenuAtas);
	}

	public List findByNamaMenu(Object namaMenu) {
		return findByProperty(NAMA_MENU, namaMenu);
	}

	public List findByAlamatMenu(Object alamatMenu) {
		return findByProperty(ALAMAT_MENU, alamatMenu);
	}

	public List findByAlamatIkon(Object alamatIkon) {
		return findByProperty(ALAMAT_IKON, alamatIkon);
	}

	public List findByAturan(Object aturan) {
		return findByProperty(ATURAN, aturan);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByKodMenu(Object kodMenu) {
		return findByProperty(KOD_MENU, kodMenu);
	}

	public List findAll() {
		log.debug("finding all Tblmenu instances");
		try {
			String queryString = "from Tblmenu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblmenu merge(Tblmenu detachedInstance) {
		log.debug("merging Tblmenu instance");
		try {
			Tblmenu result = (Tblmenu) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblmenu instance) {
		log.debug("attaching dirty Tblmenu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblmenu instance) {
		log.debug("attaching clean Tblmenu instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}