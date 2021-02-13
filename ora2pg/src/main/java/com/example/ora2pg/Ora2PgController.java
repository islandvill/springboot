package com.example.ora2pg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class Ora2PgController {
    private static final Logger log = LoggerFactory.getLogger(Ora2PgController.class);

    @GetMapping("/ora2pg")
    public String ora2PgForm(Model model) {
        model.addAttribute("ora2pg", new Ora2PgModel());
        return "ora2pg";
    }



    @PostMapping("/ora2pg")
    public String ora2PgSubmit(@ModelAttribute Ora2PgModel ora2Pg, Model model) {
        // Ora2PgUtil ora2PgUtil = new Ora2PgUtil();
        // ora2Pg.setConvertedSqlStmt(ora2PgUtil.getConvertedSqlStmt(ora2Pg.getOriginalSqlStmt()));

        RestTemplate restTemplate = new RestTemplate();//context.getBean(); //new RestTemplate();
        // Map<String, String> params = new HashMap<String, String>();
        // params.put("originalSqlStmt", ora2Pg.getOriginalSqlStmt());
        // log.info("originalSqlStmt=[" + ora2Pg.getOriginalSqlStmt() + "]");

        // String url = "http://localhost:8080/api/ora2pg";
        // UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
        //     .queryParam("originalSqlStmt", ora2Pg.getOriginalSqlStmt());
        
        // Ora2PgModel ora2PgModel2 = restTemplate.getForObject(, Ora2PgModel.class, params);
        Ora2PgModel ora2PgModel2 = restTemplate.getForObject("http://localhost:8080/api/ora2pg?originalSqlStmt={originalSqlStmt}", Ora2PgModel.class, ora2Pg.getOriginalSqlStmt());
        log.info("convertedSqlStmt=[" + ora2PgModel2.getConvertedSqlStmt() + "]");
        ora2Pg.setConvertedSqlStmt(ora2PgModel2.getConvertedSqlStmt());


        model.addAttribute("ora2pg", ora2Pg);
        return "ora2pg_result";
    }
}