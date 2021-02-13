package com.example.ora2pg;

public class Ora2PgModel {
  private long id;
  private String originalSqlStmt;
  private String convertedSqlStmt;

  public long getId() {
    return id;
  }

  public String getConvertedSqlStmt() {
    return convertedSqlStmt;
  }

  public void setConvertedSqlStmt(String convertedSqlStmt) {
    this.convertedSqlStmt = convertedSqlStmt;
  }

  public String getOriginalSqlStmt() {
    return originalSqlStmt;
  }

  public void setOriginalSqlStmt(String originalSqlStmt) {
    this.originalSqlStmt = originalSqlStmt;
  }

  public void setId(long id) {
    this.id = id;
  }
  
 
}
