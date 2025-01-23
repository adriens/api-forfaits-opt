package nc.opt.api;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kit_prepaye")
@Schema(description = "Forfait prépayé")
public class KitPrepaye {

    @Id
    @Schema(description = "Identifiant du kit prépayé/recharge liberté.")
    private String id;

    @Schema(description = "Montant de crédit du kit/recharge.")
    private Integer credit;

    @Schema(description = "Prix du kit prépayé/recharge liberté.")
    private Integer prix;

    @Schema(description = "Nombre de SMS inclus dans le kit/recharge.")
    private Integer sms_offert;

    @Schema(description = "Durée de validité du kit/recharge (en jours).")
    private Integer duree_validite;

    @Schema(description = "Url de la page du kit sur le site officiel.")
    private String url;

    public String getId() {
        return id;
    }

    public Integer getCredit() {
        return credit;
    }

    public Integer getPrix() {
        return prix;
    }

    public Integer getSms_offert() {
        return sms_offert;
    }

    public Integer getDuree_validite() {
        return duree_validite;
    }

    public String getUrl() {
        return url;
    }
}
