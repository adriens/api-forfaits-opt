package nc.opt.api;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "forfait_bloque")
@Schema(description = "Forfait bloqué")
public class ForfaitBloque {
    @Id
    @Schema(description = "Identifiant du forfait bloqué.")
    private String id;

    @Schema(description = "Prix du forfait bloqué.")
    private Integer prix;

    @Schema(description = "Montant de crédit du kit.")
    private Integer credit;

    @Schema(description = "Nombre de SMS offert dans le forfait.")
    private Integer sms_offert;

    @Schema(description = "Url de la page du forfait sur le site officiel.")
    private String url;

    public String getId() {
        return id;
    }

    public Integer getPrix() {
        return prix;
    }

    public Integer getCredit() {
        return credit;
    }

    public Integer getSms_offert() {
        return sms_offert;
    }
}
