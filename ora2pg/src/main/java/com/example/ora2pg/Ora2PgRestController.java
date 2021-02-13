package com.example.ora2pg;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ora2PgRestController {
    private static final Logger log = LoggerFactory.getLogger(Ora2PgController.class);

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/api/ora2pg")
    public Ora2PgModel ora2PgExec(
            @RequestParam(value = "originalSqlStmt", defaultValue = "select sysdate from dual") String originalSqlStmt) {
        log.info("in ora2PgExec, originalSqlStmt=[" + originalSqlStmt + "]");
        Ora2PgModel ora2PgModel = new Ora2PgModel();
        Ora2PgUtil ora2PgUtil = new Ora2PgUtil();
        ora2PgModel.setId(counter.incrementAndGet());
        ora2PgModel.setOriginalSqlStmt(originalSqlStmt);
        ora2PgModel.setConvertedSqlStmt(ora2PgUtil.getConvertedSqlStmt(originalSqlStmt));
        return ora2PgModel;
    }
}