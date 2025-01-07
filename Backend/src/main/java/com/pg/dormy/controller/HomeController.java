package com.pg.dormy.controller;

import com.pg.dormy.DTO.PgDataDTO;
import com.pg.dormy.DTO.PgSearchCriteria;
import com.pg.dormy.entity.PgData;
import com.pg.dormy.service.PgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pg")
public class HomeController {
    @Autowired
    private final PgService pgService;

    public HomeController(PgService pgService) {
        this.pgService = pgService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Secured Endpoint");
    }

//    @GetMapping("/list")
//    public ResponseEntity<Map<String, Object>> getPgDataPaginated(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Page<PgDataDTO> pgDataPage = pgService.getAllPgDataPaginated(page, size);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("data", pgDataPage.getContent());
//        response.put("currentPage", pgDataPage.getNumber());
//        response.put("totalItems", pgDataPage.getTotalElements());
//        response.put("totalPages", pgDataPage.getTotalPages());
//
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getPgDataPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<PgDataDTO> pgDataPage = pgService.getAllPgDataPaginated(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("data", pgDataPage.getContent());
        response.put("currentPage", pgDataPage.getNumber());
        response.put("totalItems", pgDataPage.getTotalElements());
        response.put("totalPages", pgDataPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchPGs(
            @RequestParam(required = false) String pgArea,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Boolean hasSingle,
            @RequestParam(required = false) Boolean hasDouble,
            @RequestParam(required = false) Boolean hasTriple,
            @RequestParam(required = false) Boolean hasQuadruple,
            @RequestParam(required = false) BigDecimal minRent,
            @RequestParam(required = false) BigDecimal maxRent,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {



        PgSearchCriteria criteria = new PgSearchCriteria(
                pgArea, gender, hasSingle, hasDouble,
                hasTriple, hasQuadruple, minRent, maxRent
        );

//        if(hasSingle){
//
//        }

        Page<PgDataDTO> pgDataPage = pgService.searchPGs(pgArea,gender,hasSingle,hasDouble,hasTriple,hasQuadruple,minRent,maxRent, page, size);


//        for (int i = 0; i < pgDataPage.getSize(); i++) {
//
//        }
//        System.out.println(pgDataPage.getContent().size()+"ITS SIZE OF PGDATAPAGE");
        for (int i = 0; i < pgDataPage.getContent().size(); i++) {
            if(hasSingle != null){
                System.out.println("HAS SINGLE SHARING-----------------------------");
                for (int j = 0; j < pgDataPage.getContent().get(i).getRooms().size(); j++) {
                    System.out.println(pgDataPage.getContent().get(i).getRooms().get(j).getRent());
                }
            }else {
                System.out.println("NO SINGLE SHARING----------------------");
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", pgDataPage.getContent());
        response.put("currentPage", pgDataPage.getNumber());
        response.put("totalItems", pgDataPage.getTotalElements());
        response.put("totalPages", pgDataPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getphonenumber")
    public String getOwnerPhone(@RequestParam(required = true)Integer pgId){
        return pgService.getOwnerPhoneNumber(pgId);
    }

}
