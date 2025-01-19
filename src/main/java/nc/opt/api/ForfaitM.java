package nc.opt.api;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "forfait_m") // map la table forfait_m avec l'entité
@Schema(description = "Forfait de la gamme M")
public class ForfaitM {

    @Id
    @Schema(description = "Identifiant du forfait.")
    private String id;
    @Schema(description = "Quantité de données mobiles.")
    private String volumetrie;
    @Schema(description = "Temps d'appel disponible.")
    private String vocal;
    @Schema(description = "Nombre de SMS disponibles.")
    private String sms;
    @Schema(description = "Prix du forfait.")
    private Double prix;
    @Schema(description = "URL de la page du forfait sur le site officiel.")
    private String url;

    public String getId() {
        return id;
    }

    public String getVolumetrie() {
        return volumetrie;
    }

    public String getVocal() {
        return vocal;
    }

    public String getSms() {
        return sms;
    }

    public Double getPrix() {
        return prix;
    }


    public String getUrl() {
        return url;
    }

}
