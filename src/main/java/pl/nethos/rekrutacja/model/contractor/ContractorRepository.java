package pl.nethos.rekrutacja.model.contractor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class ContractorRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Contractor> all() {
        return em.createQuery("SELECT c FROM Contractor c", Contractor.class).getResultList();
    }
}
