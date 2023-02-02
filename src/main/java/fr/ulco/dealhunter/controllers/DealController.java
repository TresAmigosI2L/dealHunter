package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deals")
public class DealController {
    @Autowired
    private DealService dealService;

    @PostMapping
    public void createDeal(@RequestBody DealRequest dealRequest){
        dealService.createDeal(dealRequest);
    }
}
