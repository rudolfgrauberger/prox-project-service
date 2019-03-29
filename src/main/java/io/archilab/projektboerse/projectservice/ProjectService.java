package io.archilab.projektboerse.projectservice;

import io.archilab.projektboerse.projectservice.module.ModuleService;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;



@SpringBootApplication
@EnableFeignClients
public class ProjectService {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(ProjectService.class, args);
    context.getBean(ModuleService.class).importModules();
  }
}




