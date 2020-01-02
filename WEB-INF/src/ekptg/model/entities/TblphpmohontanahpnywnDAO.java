package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpmohontanahpnywn entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpmohontanahpnywn
 * @author MyEclipse Persistence Tools
 */

public class TblphpmohontanahpnywnDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpmohontanahpnywnDAO.class);
	// property constants
	public static final String ID_PERMOHONANPENYEWAAN = "idPermohonanpenyewaan";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_JENISHAKMILIK = "idJenishakmilik";
	public static final String ID_SYARATNYATA = "idSyaratnyata";
	public static final String ID_SEKATAN = "idSekatan";
	public static final String NO_HM = "noHm";
	public static final String ID_LOT = "idLot";
	public static final String NO_LOT = "noLot";
	public static final String LUAS = "luas";
	public static final String ID_UNITLUAS = "idUnitluas";
	public static final String ID_KATEGORI = "idKategori";
	public static final String NO_WARTA = "noWarta";
	public static final String ID_MENTERI = "idMenteri";
	public static final String ID_AGENSI = "idAgensi";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpmohontanahpnywn transientInstance) {
		log.debug("saving Tblphpmohontanahpnywn instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpmohontanahpnywn persistentInstance) {
		log.debug("deleting Tblphpmohontanahpnywn instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpmohontanahpnywn findById(java.lang.Long id) {
		log.debug("getting Tblphpmohontanahpnywn instance with id: " + id);
		try {
			Tblphpmohontanahpnywn instance = (Tblphpmohontanahpnywn) getSession()
					.get("ekptg.model.entities.Tblphpmohontanahpnywn", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpmohontanahpnywn instance) {
		log.debug("finding Tblphpmohontanahpnywn instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpmohontanahpnywn").add(
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
		log.debug("finding Tblphpmohontanahpnywn instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpmohontanahpnywn as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPermohonanpenyewaan(Object idPermohonanpenyewaan) {
		return findByProperty(ID_PERMOHONANPENYEWAAN, idPermohonanpenyewaan);
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdMukim(Object idMukim) {
		return findByProperty(ID_MUKIM, idMukim);
	}

	public List findByIdJenishakmilik(Object idJenishakmilik) {
		return findByProperty(ID_JENISHAKMILIK, idJenishakmilik);
	}

	public List findByIdSyaratnyata(Object idSyaratnyata) {
		return findByProperty(ID_SYARATNYATA, idSyaratnyata);
	}

	public List findByIdSekatan(Object idSekatan) {
		return findByProperty(ID_SEKATAN, idSekatan);
	}

	public List findByNoHm(Object noHm) {
		return findByProperty(NO_HM, noHm);
	}

	public List findByIdLot(Object idLot) {
		return findByProperty(ID_LOT, idLot);
	}

	public List findByNoLot(Object noLot) {
		return findByProperty(NO_LOT, noLot);
	}

	public List findByLuas(Object luas) {
		return findByProperty(LUAS, luas);
	}

	public List findByIdUnitluas(Object idUnitluas) {
		return findByProperty(ID_UNITLUAS, idUnitluas);
	}

	public List findByIdKategori(Object idKategori) {
		return findByProperty(ID_KATEGORI, idKategori);
	}

	public List findByNoWarta(Object noWarta) {
		return findByProperty(NO_WARTA, noWarta);
	}

	public List findByIdMenteri(Object idMenteri) {
		return findByProperty(ID_MENTERI, idMenteri);
	}

	public List findByIdAgensi(Object idAgensi) {
		return findByProperty(ID_AGENSI, idAgensi);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpmohontanahpnywn instances");
		try {
			String queryString = "from Tblphpmohontanahpnywn";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpmohontanahpnywn merge(Tblphpmohontanahpnywn detachedInstance) {
		log.debug("merging Tblphpmohontanahpnywn instance");
		try {
			Tblphpmohontanahpnywn result = (Tblphpmohontanahpnywn) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpmohontanahpnywn instance) {
		log.debug("attaching dirty Tblphpmohontanahpnywn instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpmohontanahpnywn instance) {
		log.debug("attaching clean Tblphpmohontanahpnywn instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}