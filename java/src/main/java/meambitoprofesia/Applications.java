package meambitoprofesia;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Applications {
    private String id;
    private String link;
    private String title;
    private String status;
    private Date postedAt = new Date();

    public Applications() {

    }

    public Applications(String id, String link, String title, String status) {
        super();
        this.id = id;
        this.link = link;
        this.title = title;
        this.status = status;
    }

    // setters && getters
    @ObjectId
    @JsonProperty("_id")
    public String getId() {
        return id;
    }
    @ObjectId
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    public String getLink() { return this.link; }
    public void setLink(String link) { this.link = link; }

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    public Date getPostedAt() { return postedAt; }
    public void setPostedAt(Date postedAt) { this.postedAt = postedAt; }

}
