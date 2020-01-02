package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtphakmilik entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtphakmilik
 * @author MyEclipse Persistence Tools
 */

public class TblhtphakmilikDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtphakmilikDAO.class);
	// property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_LUAS = "idLuas";
	public static final String PEGANGAN_HAKMILIK = "peganganHakmilik";
        public static final String ID_HAKMILIKSPTB = "idHakmiliksptb";
	public static final String NO_HAKMILIK = "noHakmilik";
	public static final String NO_WARTA = "noWarta";
	public static final String NO_LOT = "noLot";
	public static final String LUAS = "luas";
	public static final String NO_SYIT = "noSyit";
	public static final String NO_PELAN = "noPelan";
	public static final String SYARAT = "syarat";
	public static final String SEKATAN = "sekatan";
	public static final String CUKAI = "cukai";
	public static final String FLAG_PELAN = "flagPelan";
	public static final String ULASAN = "ulasan";
	public static final String STATUS_SWASTA = "statusSwasta";
	public static final String TINDAKAN_LANJUT = "tindakanLanjut";
	public static final String ID_SUBKATEGORI = "idSubkategori";
	public static final String LOKASI = "lokasi";
	public static final String CUKAI_TERKINI = "cukaiTerkini";
	public static final String STATUS_RIZAB = "statusRizab";
	public static final String NO_BANGUNAN = "noBangunan";
	public static final String NO_TINGKAT = "noTingkat";
	public static final String NO_PTK = "noPtk";
	public static final String STATUS_TANAH = "statusTanah";
	public static final String LUAS_BERSAMAAN = "luasBersamaan";
	public static final String JENIS_TANAH = "jenisTanah";
	public static final String FLAG_PTP = "flagPtp";
	public static final String ID_KATEGORI = "idKategori";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_LOT = "idLot";
	public static final String ID_JENISHAKMILIK = "idJenishakmilik";
	public static final String ID_RIZAB = "idRizab";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtphakmilik transientInstance) {
		log.debug("saving Tblhtphakmilik instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtphakmilik persistentInstance) {
		log.debug("deleting Tblhtphakmilik instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtphakmilik findById(java.lang.Long id) {
		log.debug("getting Tblhtphakmilik instance with id: " + id);
		try {
			Tblhtphakmilik instance = (Tblhtphakmilik) getSession().get(
					"ekptg.model.entities.Tblhtphakmilik", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtphakmilik instance) {
		log.debug("finding Tblhtphakmilik instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtphakmilik").add(
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
		log.debug("finding Tblhtphakmilik instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtphakmilik as model where model."
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

	public List findByIdLuas(Object idLuas) {
		return findByProperty(ID_LUAS, idLuas);
	}

	public List findByPeganganHakmilik(Object peganganHakmilik) {
		return findByProperty(PEGANGAN_HAKMILIK, peganganHakmilik);
	}

    public List findByIdHakmiliksptb(Object idHakmiliksptb) {
            return findByProperty(ID_HAKMILIKSPTB, idHakmiliksptb);
    }

	public List findByNoHakmilik(Object noHakmilik) {
		return findByProperty(NO_HAKMILIK, noHakmilik);
	}

	public List findByNoWarta(Object noWarta) {
		return findByProperty(NO_WARTA, noWarta);
	}

	public List findByNoLot(Object noLot) {
		return findByProperty(NO_LOT, noLot);
	}

	public List findByLuas(Object luas) {
		return findByProperty(LUAS, luas);
	}

	public List findByNoSyit(Object noSyit) {
		return findByProperty(NO_SYIT, noSyit);
	}

	public List findByNoPelan(Object noPelan) {
		return findByProperty(NO_PELAN, noPelan);
	}

	public List findBySyarat(Object syarat) {
		return findByProperty(SYARAT, syarat);
	}

	public List findBySekatan(Object sekatan) {
		return findByProperty(SEKATAN, sekatan);
	}

	public List findByCukai(Object cukai) {
		return findByProperty(CUKAI, cukai);
	}

	public List findByFlagPelan(Object flagPelan) {
		return findByProperty(FLAG_PELAN, flagPelan);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByStatusSwasta(Object statusSwasta) {
		return findByProperty(STATUS_SWASTA, statusSwasta);
	}

	public List findByTindakanLanjut(Object tindakanLanjut) {
		return findByProperty(TINDAKAN_LANJUT, tindakanLanjut);
	}

	public List findByIdSubkategori(Object idSubkategori) {
		return findByProperty(ID_SUBKATEGORI, idSubkategori);
	}

	public List findByLokasi(Object lokasi) {
		return findByProperty(LOKASI, lokasi);
	}

	public List findByCukaiTerkini(Object cukaiTerkini) {
		return findByProperty(CUKAI_TERKINI, cukaiTerkini);
	}

	public List findByStatusRizab(Object statusRizab) {
		return findByProperty(STATUS_RIZAB, statusRizab);
	}

	public List findByNoBangunan(Object noBangunan) {
		return findByProperty(NO_BANGUNAN, noBangunan);
	}

	public List findByNoTingkat(Object noTingkat) {
		return findByProperty(NO_TINGKAT, noTingkat);
	}

	public List findByNoPtk(Object noPtk) {
		return findByProperty(NO_PTK, noPtk);
	}

	public List findByStatusTanah(Object statusTanah) {
		return findByProperty(STATUS_TANAH, statusTanah);
	}

	public List findByLuasBersamaan(Object luasBersamaan) {
		return findByProperty(LUAS_BERSAMAAN, luasBersamaan);
	}

	public List findByJenisTanah(Object jenisTanah) {
		return findByProperty(JENIS_TANAH, jenisTanah);
	}

	public List findByFlagPtp(Object flagPtp) {
		return findByProperty(FLAG_PTP, flagPtp);
	}

	public List findByIdKategori(Object idKategori) {
		return findByProperty(ID_KATEGORI, idKategori);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdMukim(Object idMukim) {
		return findByProperty(ID_MUKIM, idMukim);
	}

	public List findByIdLot(Object idLot) {
		return findByProperty(ID_LOT, idLot);
	}

	public List findByIdJenishakmilik(Object idJenishakmilik) {
		return findByProperty(ID_JENISHAKMILIK, idJenishakmilik);
	}

	public List findByIdRizab(Object idRizab) {
		return findByProperty(ID_RIZAB, idRizab);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtphakmilik instances");
		try {
			String queryString = "from Tblhtphakmilik";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtphakmilik merge(Tblhtphakmilik detachedInstance) {
		log.debug("merging Tblhtphakmilik instance");
		try {
			Tblhtphakmilik result = (Tblhtphakmilik) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtphakmilik instance) {
		log.debug("attaching dirty Tblhtphakmilik instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtphakmilik instance) {
		log.debug("attaching clean Tblhtphakmilik instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}