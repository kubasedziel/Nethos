package pl.nethos.rekrutacja.model.account;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_gen")
    @SequenceGenerator(name = "bank_account_gen", sequenceName = "konto_bankowe_seq", allocationSize = 1)
    private long id;

    private long contractorId;

    private String number;

    private int active;

    private int defaultAccount;

    private int virtual;

    private Integer verificationState;

    private Date verificationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContractorId() {
        return contractorId;
    }

    public void setContractorId(long contractorId) {
        this.contractorId = contractorId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(int defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public int getVirtual() {
        return virtual;
    }

    public void setVirtual(int virtual) {
        this.virtual = virtual;
    }

    public Integer getVerificationState() {
        return verificationState;
    }

    public void setVerificationState(Integer verificationState) {
        this.verificationState = verificationState;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    @Override
    public String toString() {
        return "Konto bankowe{" +
                "id=" + id +
                ", id kontrahenta='" + contractorId + '\'' +
                ", numer='" + number + '\'' +
                ", aktywne='" + active + '\'' +
                ", domyślne='" + defaultAccount + '\'' +
                ", wirtualne='" + virtual + '\'' +
                ", stan weryfikacji='" + verificationState + '\'' +
                ", data weryfikacji='" + verificationDate + '\'' +
                '}';
    }
}
