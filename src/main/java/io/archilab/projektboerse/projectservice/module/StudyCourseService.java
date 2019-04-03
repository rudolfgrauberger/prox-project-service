package io.archilab.projektboerse.projectservice.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class StudyCourseService {

    private final Logger logger = LoggerFactory.getLogger(StudyCourseService.class);

    private final StudyCourseClient studyCourseClient;
    private final ModuleRepository moduleRepository;
    private final StudyCourseRepository studyCourseRepository;


    public StudyCourseService(StudyCourseClient studyCourseClient, ModuleRepository moduleRepository, StudyCourseRepository studyCourseRepository) {
        this.studyCourseClient = studyCourseClient;
        this.moduleRepository = moduleRepository;

        this.studyCourseRepository = studyCourseRepository;
    }

    public void importStudyCourses() {
        logger.info("Start importing Study Courses");

        List<StudyCourse> studyCourses = studyCourseClient.getStudyCourses();
        for (StudyCourse studyCourse : studyCourses) {

            List<Module> newModules = new ArrayList<>();
            List<Module> retrievedModules = studyCourse.getModules();
            for (Module module : retrievedModules)
            {
                Optional<Module> existingModuleOptional = this.moduleRepository.findByExternalModuleID(module.getExternalModuleID());

                if (existingModuleOptional.isPresent()) {
                    logger.info("Module with ID " + module.getExternalModuleID() + " already exists.");
                    Module existingModule = existingModuleOptional.get();
                    existingModule.setName(module.getName());
                    newModules.add(existingModule);
                    this.moduleRepository.save(existingModule);
                } else {
                    logger.info("Module with ID " + module.getExternalModuleID() + " does not exist yet.");
                    newModules.add(module);
                    this.moduleRepository.save(module);
                }
            }

            Optional<StudyCourse> existingStudyCourseOptional = this.studyCourseRepository.findByExternalStudyCourseID(studyCourse.getExternalStudyCourseID());

            if (existingStudyCourseOptional.isPresent()) {
                logger.info("StudyCourse with ID " + studyCourse.getExternalStudyCourseID() + " already exists.");
                StudyCourse existingStudyCourse = existingStudyCourseOptional.get();
                existingStudyCourse.setName(studyCourse.getName());
                existingStudyCourse.setAcademicDegree(studyCourse.getAcademicDegree());
                existingStudyCourse.setStudyDirections(studyCourse.getStudyDirections());
                existingStudyCourse.setModules(newModules);
                this.studyCourseRepository.save(existingStudyCourse);

            } else {
                logger.info("StudyCourse with ID " + studyCourse.getExternalStudyCourseID() + " does not exist yet.");
                studyCourse.setModules(newModules);
                this.studyCourseRepository.save(studyCourse);
            }
        }
    }
}
