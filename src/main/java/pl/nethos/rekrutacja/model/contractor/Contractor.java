package pl.nethos.rekrutacja.model.contractor;

import javax.persistence.*;

@Entity
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contractor_gen")
    @SequenceGenerator(name = "contractor_gen", sequenceName = "contractor_seq", allocationSize = 1)
    private long id;

    private String name;

    private String nip;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    @Override
    public String toString() {
        return "Kontrahent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nip='" + nip + '\'' +
                '}';
    }
}
