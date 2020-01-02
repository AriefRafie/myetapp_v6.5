package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpulasanteknikal entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpulasanteknikal
 * @author MyEclipse Persistence Tools
 */

public class TblhtpulasanteknikalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpulasanteknikalDAO.class);
	// property constants
	public static final String NO_RUJKJT = "noRujkjt";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_AGENSI = "idAgensi";
	public static final String ID_KEMENTERIAN = "idKementerian";
	public static final String ULASAN = "ulasan";
	public static final String STATUS_KEPUTUSAN = "statusKeputusan";
	public static final String ID_PEJABAT = "idPejabat";
	public static final String NAMA_PEGAWAI = "namaPegawai";
	public static final String ID_JNSDOKUMEN = "idJnsdokumen";
	public static final String ID_MASUK = "idMasuk";

	public void save(Tblhtpulasanteknikal transientInstance) {
		log.debug("saving Tblhtpulasanteknikal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpulasanteknikal persistentInstance) {
		log.debug("deleting Tblhtpulasanteknikal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpulasanteknikal findById(java.lang.String id) {
		log.debug("getting Tblhtpulasanteknikal instance with id: " + id);
		try {
			Tblhtpulasanteknikal instance = (Tblhtpulasanteknikal) getSession()
					.get("ekptg.model.entities.Tblhtpulasanteknikal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpulasanteknikal instance) {
		log.debug("finding Tblhtpulasanteknikal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpulasanteknikal").add(
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
		log.debug("finding Tblhtpulasanteknikal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpulasanteknikal as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoRujkjt(Object noRujkjt) {
		return findByProperty(NO_RUJKJT, noRujkjt);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdAgensi(Object idAgensi) {
		return findByProperty(ID_AGENSI, idAgensi);
	}

	public List findByIdKementerian(Object idKementerian) {
		return findByProperty(ID_KEMENTERIAN, idKementerian);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByStatusKeputusan(Object statusKeputusan) {
		return findByProperty(STATUS_KEPUTUSAN, statusKeputusan);
	}

	public List findByIdPejabat(Object idPejabat) {
		return findByProperty(ID_PEJABAT, idPejabat);
	}

	public List findByNamaPegawai(Object namaPegawai) {
		return findByProperty(NAMA_PEGAWAI, namaPegawai);
	}

	public List findByIdJnsdokumen(Object idJnsdokumen) {
		return findByProperty(ID_JNSDOKUMEN, idJnsdokumen);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findAll() {
		log.debug("finding all Tblhtpulasanteknikal instances");
		try {
			String queryString = "from Tblhtpulasanteknikal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpulasanteknikal merge(Tblhtpulasanteknikal detachedInstance) {
		log.debug("merging Tblhtpulasanteknikal instance");
		try {
			Tblhtpulasanteknikal result = (Tblhtpulasanteknikal) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpulasanteknikal instance) {
		log.debug("attaching dirty Tblhtpulasanteknikal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpulasanteknikal instance) {
		log.debug("attaching clean Tblhtpulasanteknikal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}