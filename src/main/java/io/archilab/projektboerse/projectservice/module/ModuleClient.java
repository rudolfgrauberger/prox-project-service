package io.archilab.projektboerse.projectservice.module;

import com.thoughtworks.xstream.converters.basic.UUIDConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@Component
public class ModuleClient {

  @Value("${module.service.url}")
  private String moduleServiceURL;


  public ModuleClient() { }

  public Collection<Module> getModules() {
    Traverson traverson;
    try {
      traverson = new Traverson(new URI(moduleServiceURL), MediaTypes.HAL_JSON);
    } catch (URISyntaxException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }

    List<Module> modules = new ArrayList<>();

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
        module.setId(extractUUIDFromSelfLink(moduleResource.getId())); // TODO better way of setting the id? it wont be set automatically because the module id is not exposed
        modules.add(module);
      }
    }

    return modules;
  }

  private UUID extractUUIDFromSelfLink(Link selfLink) {
    String selfRef = selfLink.getHref();
    String uuid = selfRef.substring(selfRef.length() - 36);
    return UUID.fromString(uuid);
  }
}
