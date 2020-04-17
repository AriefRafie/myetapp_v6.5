package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpengguna entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpengguna
 * @author MyEclipse Persistence Tools
 */

public class TblpenggunaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpenggunaDAO.class);
	// property constants
	public static final String KOD_PENGGUNA = "kodPengguna";
	public static final String NAMA_PENGGUNA = "namaPengguna";
	public static final String NO_KP = "noKp";
	public static final String KATALALUAN = "katalaluan";
	public static final String KATALALUAN_BANDINGAN = "katalaluanBandingan";
	public static final String KOD_STATUS_PENGGUNA = "kodStatusPengguna";
	public static final String KOD_JENIS_PENGGUNA = "kodJenisPengguna";

	public void save(Tblpengguna transientInstance) {
		log.debug("saving Tblpengguna instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpengguna persistentInstance) {
		log.debug("deleting Tblpengguna instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpengguna findById(java.lang.Long id) {
		log.debug("getting Tblpengguna instance with id: " + id);
		try {
			Tblpengguna instance = (Tblpengguna) getSession().get(
					"ekptg.model.entities.Tblpengguna", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpengguna instance) {
		log.debug("finding Tblpengguna instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpengguna").add(
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
		log.debug("finding Tblpengguna instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblpengguna as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodPengguna(Object kodPengguna) {
		return findByProperty(KOD_PENGGUNA, kodPengguna);
	}

	public List findByNamaPengguna(Object namaPengguna) {
		return findByProperty(NAMA_PENGGUNA, namaPengguna);
	}

	public List findByNoKp(Object noKp) {
		return findByProperty(NO_KP, noKp);
	}

	public List findByKatalaluan(Object katalaluan) {
		return findByProperty(KATALALUAN, katalaluan);
	}

	public List findByKatalaluanBandingan(Object katalaluanBandingan) {
		return findByProperty(KATALALUAN_BANDINGAN, katalaluanBandingan);
	}

	public List findByKodStatusPengguna(Object kodStatusPengguna) {
		return findByProperty(KOD_STATUS_PENGGUNA, kodStatusPengguna);
	}

	public List findByKodJenisPengguna(Object kodJenisPengguna) {
		return findByProperty(KOD_JENIS_PENGGUNA, kodJenisPengguna);
	}

	public List findAll() {
		log.debug("finding all Tblpengguna instances");
		try {
			String queryString = "from Tblpengguna";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpengguna merge(Tblpengguna detachedInstance) {
		log.debug("merging Tblpengguna instance");
		try {
			Tblpengguna result = (Tblpengguna) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpengguna instance) {
		log.debug("attaching dirty Tblpengguna instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpengguna instance) {
		log.debug("attaching clean Tblpengguna instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}