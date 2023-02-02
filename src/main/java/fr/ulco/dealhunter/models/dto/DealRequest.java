package fr.ulco.dealhunter.models.dto;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DealRequest {
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
