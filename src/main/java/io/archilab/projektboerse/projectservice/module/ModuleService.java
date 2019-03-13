package io.archilab.projektboerse.projectservice.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModuleService {

    private final Logger logger = LoggerFactory.getLogger(ModuleService.class);

    private final ModuleClient moduleClient;
    private final ModuleRepository moduleRepository;


    public ModuleService(ModuleClient moduleClient, ModuleRepository moduleRepository) {
        this.moduleClient = moduleClient;
        this.moduleRepository = moduleRepository;
    }

    public void importModules() {

        logger.info("Start importing modules");

        Collection<Module> modules = moduleClient.getModules();
        for (Module module : modules) {
            Optional<Module> existingModuleOptional = this.moduleRepository.findByExternalModuleID(module.getExternalModuleID());
            if (existingModuleOptional.isPresent()) {
                logger.info("Module with ID " + module.getExternalModuleID() + " already exists.");
                Module existingModule = existingModuleOptional.get();
                existingModule.setName(module.getName());
                this.moduleRepository.save(existingModule);
            } else {
                logger.info("Module with ID " + module.getExternalModuleID() + " does not exist yet.");
                this.moduleRepository.save(module);
            }
            logger.info("Imported Module: " + module.getExternalModuleID());
        }

        logger.info("Finished importing " + modules.size() + " modules");
    }
}
