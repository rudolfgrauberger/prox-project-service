package io.archilab.projektboerse.projectservice;

import io.archilab.projektboerse.projectservice.module.ModuleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*; 
import java.net.*; 
  


@SpringBootApplication
@EnableFeignClients
public class ProjectService {

  public static void main(String[] args) {
	  
	  
	  
	  
    ConfigurableApplicationContext context = SpringApplication.run(ProjectService.class, args);

    context.getBean(ModuleService.class).importModules();
    
    
    String ipAddress = "login.coalbase.io"; 
    try {
		sendPingRequest(ipAddress);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
  }

  
  public static void sendPingRequest(String ipAddress) 
          throws UnknownHostException, IOException 
{ 
InetAddress geek = InetAddress.getByName(ipAddress); 
System.out.println("Sending Ping Request to " + ipAddress); 
if (geek.isReachable(5000)) 
  System.out.println("Host is reachable"); 
else
  System.out.println("Sorry ! We can't reach to this host"); 
} 
  
}

