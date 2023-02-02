package fr.ulco.dealhunter.models.dto;

import lombok.NonNull;

public class DealResponse {
    @NonNull
    private String author;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private Double originalPrice;
    @NonNull
    private Double discountedPrice;
    @NonNull
    private String url;
    @NonNull
    private String imageUrl;
}
