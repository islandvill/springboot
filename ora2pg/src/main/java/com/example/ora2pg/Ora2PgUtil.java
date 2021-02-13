package com.example.ora2pg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ora2PgUtil {
    private static final Logger log = LoggerFactory.getLogger(Ora2PgController.class);
    
    public String getConvertedSqlStmt(String originalSqlStmt) {
        

        File inTempFile = null;
        File outTempFile = null;
        try {
            inTempFile = File.createTempFile("inp_", ".sql");
            outTempFile = File.createTempFile("out_", ".sql");    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String inTempFileNm = inTempFile.getAbsolutePath();
        String outTempFileNm = outTempFile.getAbsolutePath();
        System.out.println("inTempFileNm=[" + inTempFileNm + "]" );
        System.out.println("outTempFileNm=[" + outTempFileNm + "]" );

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(inTempFile));
            writer.write(originalSqlStmt);
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String convertedSqlStmt = "";
        String cmd = "cmd.exe /c ora2pg.bat -t QUERY --estimate_cost --cost_unit_value 10 -i " + inTempFileNm + " -o " + outTempFileNm;
        Runtime run = Runtime.getRuntime();
        Process pr = null;
        try {
            pr = run.exec(cmd);
            pr.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        String line = "";
        try {
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = buf.readLine()) != null) {
                //System.out.println(line);
                log.debug(line);
            }
            BufferedReader reader = new BufferedReader(new FileReader(outTempFile));
            while ((line = reader.readLine()) != null) {
                // convertedSqlStmt += "<br/>" + line;
                convertedSqlStmt += line + "\n";
                log.debug(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inTempFile.delete();
        outTempFile.delete();

        return convertedSqlStmt;
    }   
}