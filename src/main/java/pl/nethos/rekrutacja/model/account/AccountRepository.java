package pl.nethos.rekrutacja.model.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nethos.rekrutacja.model.contractor.Contractor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class AccountRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Account> all() {
        return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    public List<Account> findByContractor(Contractor contractor) {
        return em.createQuery("SELECT a FROM Account a WHERE a.contractorId = :id", Account.class)
                .setParameter("id", contractor.getId())
                .getResultList();
    }

    public void update(Account account) {
        em.createQuery("UPDATE Account SET contractorId = :p1, active = :p2, defaultAccount = :p3" +
                ", virtual = :p4, verificationState = :p5, verificationDate = '" +
                Timestamp.from(account.getVerificationDate().toInstant()).toString() +
                "' WHERE id = :p6")
                .setParameter("p1", account.getContractorId())
                .setParameter("p2", account.getActive())
                .setParameter("p3", account.getDefaultAccount())
                .setParameter("p4", account.getVirtual())
                .setParameter("p5", account.getVerificationState())
                .setParameter("p6", account.getId())
                .executeUpdate();
    }
}
