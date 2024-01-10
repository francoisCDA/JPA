package heritage.demo.embedded;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Agence  {

    @EmbeddedId
    private AgenceId agenceId;

    private String adresse;


    public Agence() {

    }

    public Agence(AgenceId agenceId, String adresse) {
        this.agenceId = agenceId;
        this.adresse = adresse;
    }

    public AgenceId getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(AgenceId agenceId) {
        this.agenceId = agenceId;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
