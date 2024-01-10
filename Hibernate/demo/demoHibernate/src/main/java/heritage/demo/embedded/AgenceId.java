package heritage.demo.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AgenceId implements Serializable {

    private int code;

    private String libelle ;

    public AgenceId() {
    }

    public AgenceId(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
