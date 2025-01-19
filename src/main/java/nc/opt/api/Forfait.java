package nc.opt.api;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "forfaits") // map la table forfait avec l'entitée
public class Forfait {
    @Id
    @Schema(description = "Identifiant de la gamme du forfait.")
    private String id;
    @Schema(description = "Nom de la gamme du forfait.")
    private String desc;
    @Schema(description = "Description du forfait.")
    private String description;
    @Schema(description = "URL de la page du forfait sur le site Officiel.")
    private String url;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
