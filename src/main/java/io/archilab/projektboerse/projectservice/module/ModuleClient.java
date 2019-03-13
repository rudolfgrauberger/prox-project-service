package io.archilab.projektboerse.projectservice.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@Component
public class ModuleClient {

  private final Logger logger = LoggerFactory.getLogger(ModuleClient.class);


  @Value("${module.service.url}")
  private String moduleServiceURL;


  public ModuleClient() { }

  private Traverson getTraversonInstance() {
    try {
      return new Traverson(new URI(moduleServiceURL), MediaTypes.HAL_JSON);
    } catch (URISyntaxException e) {
      logger.error("Could not init Traverson");
      e.printStackTrace();
      return null;
    }
  }

  public Collection<Module> getModules() {
    Traverson traverson = getTraversonInstance();
    if (traverson == null)
      return new ArrayList<>();

    List<Module> modules = new ArrayList<>();

    try {
      int currentPage = 0;
      boolean reachedLastPage = false;

      while (!reachedLastPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", currentPage);

        final PagedResources<Resource<Module>> pagedModuleResources = traverson
                .follow("modules")
                .withTemplateParameters(params)
                .toObject(new TypeReferences.PagedResourcesType<Resource<Module>>(){});

        reachedLastPage = (++currentPage >= pagedModuleResources.getMetadata().getTotalPages());

        for (Resource<Module> moduleResource : pagedModuleResources.getContent()) {
          Module module = moduleResource.getContent();
          module.setExternalModuleID(new ExternalModuleID(moduleResource.getId().getHref()));
          modules.add(module);
        }
      }
    } catch (Exception e) {
      logger.error("Error retrieving modules");
    }

    return modules;
  }
}
