package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deals")
public class DealController {
    @Autowired
    private DealService dealService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDeal(@RequestBody DealRequest dealRequest){
        dealService.createDeal(dealRequest);
    }
}
