package nc.opt.api;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tourism_card")
@Schema(description="Tourism card")
public class TourismCard {
    @Id
    @Schema(description="Identifiant de l'offre tourisme")
    private String id;

    @Schema(description="Prix de l'offre tourisme")
    private Integer prix;

    @Schema(description = "Crédit disponible")
    private Integer credit;

    @Schema(description = "Données mobiles disponibles")
    private String volumetrie;

    @Schema(description = "Durée de validité de l'offre tourisme")
    private String duree_validite;

    @Schema(description = "Url de la page de l'offre tourisme")
    private String url;
    
    public String getId(){
        return id;
    }

    public Integer getPrix(){
        return prix;
    }

    public Integer getCredit(){
        return credit;
    }

    public String getVolumetrie() {
        return volumetrie;
    }

    public String getDuree_validite(){
        return duree_validite;
    }

    public String getUrl(){
        return url;
    }
}
