package io.archilab.projektboerse.projectservice.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;


@Component
public class ModuleClient {

  private final Logger logger = LoggerFactory.getLogger(ModuleClient.class);


  @Value("${module.service.url}")
  private String moduleServiceURL;
  
  @Autowired
  private final StudyCourseRepository studyCourseRepository;


  public ModuleClient(StudyCourseRepository studyCourseRepository) {
      this.studyCourseRepository = studyCourseRepository;
  }

  private Traverson getTraversonInstance(String url) {
    try {
      return new Traverson(new URI(url), MediaTypes.HAL_JSON);
    } catch (URISyntaxException e) {
      logger.error("Could not init Traverson");
      e.printStackTrace();
      return null;
    }
  }

  public Collection<Module> getModules() {
    List<Module> modules = new ArrayList<>();
    modules.addAll(getFilteredModules("Master Thesis")); // All Master Modules
    modules.addAll(getFilteredModules("Masterarbeit")); // All Master Modules
    modules.addAll(getFilteredModules("Bachelor")); // All Bachelor Modules
    modules.addAll(getFilteredModules("Praxisprojekt")); // All PP Modules
    
    return modules;
  }

  public Collection<Module> getFilteredModules(String moduleName) {
    Traverson traverson = getTraversonInstance(moduleServiceURL);
    if (traverson == null)
      return new ArrayList<>();

    List<Module> modules = new ArrayList<>();

    try {
      int currentPage = 0;
      boolean reachedLastPage = false;

      while (!reachedLastPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", moduleName);
        params.put("page", currentPage);

        final PagedResources<Resource<Module>> pagedModuleResources = traverson
                .follow("search")
                .follow("findByName_NameContainingIgnoreCase")
                .withTemplateParameters(params)
                .toObject(new TypeReferences.PagedResourcesType<Resource<Module>>(){});

        reachedLastPage = (++currentPage >= pagedModuleResources.getMetadata().getTotalPages());

        for (Resource<Module> moduleResource : pagedModuleResources.getContent()) {
          Module module = moduleResource.getContent();
          Link studyCoursesLink = moduleResource.getLink("studyCourses");
          logger.info("LINKS");

          Traverson studyCourseTraverson = getTraversonInstance(studyCoursesLink.getHref());
          if (traverson == null)
            return new ArrayList<>();
          
          final PagedResources<Resource<StudyCourse>> pagedStudyCourseResources = studyCourseTraverson
                  .follow("self")
                  .toObject(new TypeReferences.PagedResourcesType<Resource<StudyCourse>>(){});
          
          for (Resource<StudyCourse> studyCourse : pagedStudyCourseResources.getContent()) {
        	  StudyCourse sc = studyCourse.getContent();
        	  sc.setExternalStudyCourseID(new ExternalStudyCourseID(new URL(studyCourse.getId().getHref())));
        	  logger.info(sc.getExternalStudyCourseID().toString());
        	  module.getStudyCourses().add(sc);
          }
          
          module.setExternalModuleID(new ExternalModuleID(new URL(moduleResource.getId().getHref())));
          modules.add(module);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("Error retrieving modules");
    }
    return modules;
  }
}
