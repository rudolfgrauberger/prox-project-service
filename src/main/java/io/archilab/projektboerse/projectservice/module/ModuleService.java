package io.archilab.projektboerse.projectservice.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModuleService {

    private final Logger logger = LoggerFactory.getLogger(ModuleService.class);

    private final ModuleClient moduleClient; // TODO use spring rest templates
    private final ModuleRepository moduleRepository;


    public ModuleService(ModuleClient moduleClient, ModuleRepository moduleRepository) {
        this.moduleClient = moduleClient;
        this.moduleRepository = moduleRepository;
    }

    public void importModules() {
        logger.info("Start importing modules");

        try {
            for (Module module : moduleClient.getModules()) {
                this.moduleRepository.save(module);
                logger.info("Imported Module: " + module.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error importing modules");
            return;
        }

        logger.info("Finished importing modules: " + moduleRepository.count()); // TODO count = test
    }
}
