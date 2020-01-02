package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppermohonan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppermohonan
 * @author MyEclipse Persistence Tools
 */

public class TblhtppermohonanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtppermohonanDAO.class);
	// property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_AGENSI = "idAgensi";
	public static final String ID_JENISTANAH = "idJenistanah";
	public static final String ID_PEGAWAI = "idPegawai";
	public static final String NO_RUJUKAN_KJP = "noRujukanKjp";
	public static final String NO_RUJUKAN_LAIN = "noRujukanLain";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtppermohonan transientInstance) {
		log.debug("saving Tblhtppermohonan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppermohonan persistentInstance) {
		log.debug("deleting Tblhtppermohonan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppermohonan findById(java.lang.Long id) {
		log.debug("getting Tblhtppermohonan instance with id: " + id);
		try {
			Tblhtppermohonan instance = (Tblhtppermohonan) getSession().get(
					"ekptg.model.entities.Tblhtppermohonan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppermohonan instance) {
		log.debug("finding Tblhtppermohonan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppermohonan").add(
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
		log.debug("finding Tblhtppermohonan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppermohonan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByIdAgensi(Object idAgensi) {
		return findByProperty(ID_AGENSI, idAgensi);
	}

	public List findByIdJenistanah(Object idJenistanah) {
		return findByProperty(ID_JENISTANAH, idJenistanah);
	}

	public List findByIdPegawai(Object idPegawai) {
		return findByProperty(ID_PEGAWAI, idPegawai);
	}

	public List findByNoRujukanKjp(Object noRujukanKjp) {
		return findByProperty(NO_RUJUKAN_KJP, noRujukanKjp);
	}

	public List findByNoRujukanLain(Object noRujukanLain) {
		return findByProperty(NO_RUJUKAN_LAIN, noRujukanLain);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtppermohonan instances");
		try {
			String queryString = "from Tblhtppermohonan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppermohonan merge(Tblhtppermohonan detachedInstance) {
		log.debug("merging Tblhtppermohonan instance");
		try {
			Tblhtppermohonan result = (Tblhtppermohonan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppermohonan instance) {
		log.debug("attaching dirty Tblhtppermohonan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppermohonan instance) {
		log.debug("attaching clean Tblhtppermohonan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}