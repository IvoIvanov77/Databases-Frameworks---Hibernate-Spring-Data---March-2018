package app.dtos;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class GameDto {

    private String title;

    private BigDecimal price;

    private BigDecimal size;

    private String trailer;

    private String imageThumbnailURL;

    private String description;

    private Date releaseDate;

    public GameDto() {
    }

    public GameDto(String title, BigDecimal price, BigDecimal size, String trailer,
                   String imageThumbnailURL, String description, Date releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbnailURL = imageThumbnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public GameDto(Map<String, String> fieldValueMap) {
        this.title = fieldValueMap.get("title");
        this.price = new BigDecimal(fieldValueMap.get("price"));
        this.size = new BigDecimal(fieldValueMap.get("size"));;
        this.trailer = fieldValueMap.get("trailer");
        this.imageThumbnailURL = fieldValueMap.get("imageThumbnailURL");
        this.description = fieldValueMap.get("description");
        this.setReleaseDate(fieldValueMap.get("releaseDate"));
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String videoId) {
        this.trailer = videoId;
    }

    public String getImageThumbnailURL() {
        return imageThumbnailURL;
    }

    public void setImageThumbnailURL(String imageThumbnailURL) {
        this.imageThumbnailURL = imageThumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    private void setReleaseDate(String input) {
        if(input == null){
            return;
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.releaseDate = date;
    }
}
