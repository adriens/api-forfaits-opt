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
    @Schema(description = "Identifiant du kit prépayé.")
    private String id;

    @Schema(description = "Montant de crédit du kit.")
    private Integer credit;

    @Schema(description = "Prix du kit prépayé.")
    private Integer prix;

    @Schema(description = "Nombre de SMS inclus dans le kit.")
    private Integer sms_offert;

    @Schema(description = "Durée de validité du kit (en jours).")
    private Integer duree_validite;

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
}
