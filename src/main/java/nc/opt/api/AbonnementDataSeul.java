package nc.opt.api;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "abonnement_data_seul")
public class AbonnementDataSeul {

    @Id
    @Schema(description = "Identifiant du forfait.")
    private String id;
    @Schema(description = "Quantité de données mobiles.")
    private String volumetrie;
    @Schema(description = "Débit de connexion.")
    private String debit;
    @Schema(description = "Prix du forfait.")
    private Double prix;
    @Schema(description = "URL de la page du forfait sur le site officiel.")
    private String url;
    @Schema(description = "Type de forfait : internet mobile au volume ou internet mobile 4G")
    private String type_forfait;

    public String getId() {
        return id;
    }

    public String getVolumetrie() {
        return volumetrie;
    }

    public String getDebit() {
        return debit;
    }

    public Double getPrix() {
        return prix;
    }

    public String getUrl() {
        return url;
    }
    public String getType_forfait() {
        return type_forfait;
    }
}
