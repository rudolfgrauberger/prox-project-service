package io.archilab.projektboerse.projectservice.module;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ModuleService {

    private final Logger logger = LoggerFactory.getLogger(ModuleService.class);

    private final ModuleClient moduleClient;
    private final ModuleRepository moduleRepository;
    private final StudyCourseRepository studyCourseRepository;


    public ModuleService(ModuleClient moduleClient, ModuleRepository moduleRepository, StudyCourseRepository studyCourseRepository) {
        this.moduleClient = moduleClient;
        this.moduleRepository = moduleRepository;

        this.studyCourseRepository = studyCourseRepository;
    }
    
    public void importModules() {
    	
    	

        logger.info("Start importing modules");

        Collection<Module> modules = moduleClient.getModules();
        for (Module module : modules) {

        	Set <StudyCourse> list_study_courses = module.getStudyCourses();
        	for (StudyCourse sc : list_study_courses)
        	{
        		Optional<StudyCourse> existingStudyCourseOptional = this.studyCourseRepository.findByExternalStudyCourseID(sc.getExternalStudyCourseID());
        
        		if (existingStudyCourseOptional.isPresent()) {
        			 logger.info("StudyCourse with ID " + sc.getExternalStudyCourseID() + " already exists.");
        			 StudyCourse existingStudyCourse = existingStudyCourseOptional.get();
                     existingStudyCourse.setName(sc.getName());
                     this.studyCourseRepository.save(existingStudyCourse);
        			
        		} else {
        			logger.info("StudyCourse with ID " + sc.getExternalStudyCourseID() + " does not exist yet.");
        			StudyCourse newSC = new StudyCourse();
        			newSC.setAcademicDegree(sc.getAcademicDegree());
        			newSC.setName(sc.getName());
        			newSC.setExternalStudyCourseID(sc.getExternalStudyCourseID());
        			this.studyCourseRepository.save(newSC);
        		}
        	}
        }
        
        for (Module module : modules) {
        	
            Optional<Module> existingModuleOptional = this.moduleRepository.findByExternalModuleID(module.getExternalModuleID());
            if (existingModuleOptional.isPresent()) {
                logger.info("Module with ID " + module.getExternalModuleID() + " already exists.");
                Module existingModule = existingModuleOptional.get();
                existingModule.setName(module.getName());
                this.moduleRepository.save(existingModule);
            } else {
                logger.info("Module with ID " + module.getExternalModuleID() + " does not exist yet.");
               
                Module newMo = new Module();
                newMo.setName(module.getName());
                newMo.setExternalModuleID(module.getExternalModuleID());
                
                this.moduleRepository.save(newMo);
            }
            logger.info("Imported Module: " + module.getExternalModuleID());
        }
        
        for (Module module : modules) {
        	Set<StudyCourse> set_sc= new HashSet();
        	set_sc.addAll(module.getStudyCourses());
        	Module repo_module=this.moduleRepository.findByExternalModuleID(module.getExternalModuleID()).get();
        	
        	for (StudyCourse sc : set_sc) {
        		repo_module.getStudyCourses().add(this.studyCourseRepository.findByExternalStudyCourseID(sc.getExternalStudyCourseID()).get());
        		StudyCourse temp= this.studyCourseRepository.findByExternalStudyCourseID(sc.getExternalStudyCourseID()).get();
        		Set<Module> temp_set = new HashSet<Module>();
        		temp_set.addAll(temp.getModules());
        		temp_set.add(repo_module);
        		temp.setModules(temp_set);
//        		temp.getModules().add(repo_module);
        		this.studyCourseRepository.save(temp);
        	}
        	this.moduleRepository.save(repo_module);
        	
        	
        	Module repo_module22=this.moduleRepository.findByExternalModuleID(module.getExternalModuleID()).get();
        	logger.info(repo_module22.getStudyCourses().toString());

        	
//            Optional<Module> existingModuleOptional = this.moduleRepository.findByExternalModuleID(module.getExternalModuleID());
//            if (existingModuleOptional.isPresent()) {
//                logger.info("Module with ID " + module.getExternalModuleID() + " already exists.");
//                Module existingModule = existingModuleOptional.get();
//                existingModule.setName(module.getName());
//                this.moduleRepository.save(existingModule);
//            } else {
//                logger.info("Module with ID " + module.getExternalModuleID() + " does not exist yet.");
//                this.moduleRepository.save(module);
//                
//            }
            
            logger.info("Imported Module: " + module.getExternalModuleID());
        }

        logger.info("Finished importing " + modules.size() + " modules");
    }
}
