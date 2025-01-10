package nc.opt.api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "forfait_m") // map la table forfait_m avec l'entité
public class ForfaitM {

    @Id
    private String id;
    private String volumetrie;
    private String vocal;
    private String sms;
    private Double prix;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVolumetrie() {
        return volumetrie;
    }

    public void setVolumetrie(String volumetrie) {
        this.volumetrie = volumetrie;
    }

    public String getVocal() {
        return vocal;
    }

    public void setVocal(String vocal) {
        this.vocal = vocal;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
