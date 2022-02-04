package com.exflyer.oddiad.scheduler.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

  @Bean(name = "gson")
  public Gson gson() {
    return  new GsonBuilder().create();
  }

  @Bean(name = "gsonUnderScores")
  public Gson gsonUnderScores() {
    return  new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
  }
}
