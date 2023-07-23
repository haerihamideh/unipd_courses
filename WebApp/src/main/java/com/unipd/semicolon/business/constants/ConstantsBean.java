package com.unipd.semicolon.business.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("constants")
public class ConstantsBean {

  @Value("${spring.datasource.url}")
  private String urlDb;

  @Value("${spring.datasource.username}")
  private String usernameDb;

  @Value("${spring.datasource.password}")
  private String passwordDb;

  public String getUrlDb() {
    return urlDb;
  }

  public String getUsernameDb() {
    return usernameDb;
  }

  public String getPasswordDb() {
    return passwordDb;
  }
}
