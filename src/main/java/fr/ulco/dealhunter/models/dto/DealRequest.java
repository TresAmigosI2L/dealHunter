package fr.ulco.dealhunter.models.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class DealRequest {
    private Long id;
    private String title;
    private String description;
    private Double originalPrice;
    private Double discountedPrice;
    private String url;
    private String imageUrl;
}
