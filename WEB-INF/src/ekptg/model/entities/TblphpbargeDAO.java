package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpbarge entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpbarge
 * @author MyEclipse Persistence Tools
 */

public class TblphpbargeDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblphpbargeDAO.class);
	// property constants
	public static final String NAMA_BARGE = "namaBarge";
	public static final String ID_JENISBARGE = "idJenisbarge";
	public static final String LOKASI_DIBEKALKAN = "lokasiDibekalkan";
	public static final String NO_PENDAFTARAN = "noPendaftaran";
	public static final String MUATAN = "muatan";
	public static final String ID_MUATAN = "idMuatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpbarge transientInstance) {
		log.debug("saving Tblphpbarge instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpbarge persistentInstance) {
		log.debug("deleting Tblphpbarge instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpbarge findById(java.lang.Long id) {
		log.debug("getting Tblphpbarge instance with id: " + id);
		try {
			Tblphpbarge instance = (Tblphpbarge) getSession().get(
					"ekptg.model.entities.Tblphpbarge", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpbarge instance) {
		log.debug("finding Tblphpbarge instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpbarge").add(
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
		log.debug("finding Tblphpbarge instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblphpbarge as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaBarge(Object namaBarge) {
		return findByProperty(NAMA_BARGE, namaBarge);
	}

	public List findByIdJenisbarge(Object idJenisbarge) {
		return findByProperty(ID_JENISBARGE, idJenisbarge);
	}

	public List findByLokasiDibekalkan(Object lokasiDibekalkan) {
		return findByProperty(LOKASI_DIBEKALKAN, lokasiDibekalkan);
	}

	public List findByNoPendaftaran(Object noPendaftaran) {
		return findByProperty(NO_PENDAFTARAN, noPendaftaran);
	}

	public List findByMuatan(Object muatan) {
		return findByProperty(MUATAN, muatan);
	}

	public List findByIdMuatan(Object idMuatan) {
		return findByProperty(ID_MUATAN, idMuatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpbarge instances");
		try {
			String queryString = "from Tblphpbarge";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpbarge merge(Tblphpbarge detachedInstance) {
		log.debug("merging Tblphpbarge instance");
		try {
			Tblphpbarge result = (Tblphpbarge) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpbarge instance) {
		log.debug("attaching dirty Tblphpbarge instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpbarge instance) {
		log.debug("attaching clean Tblphpbarge instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}