create table IF NOT EXISTS module (
                                    id uuid not null,
                                    self_ref varchar(255),
                                    name varchar(255),
                                    primary key (id));

create table IF NOT EXISTS project (
                       id uuid not null,
                       supervisor_name varchar(255) not null,
                       created timestamp,
                       creatorid uuid not null,
                       creator_name varchar(255) not null,
                       description varchar(3000),
                       modified timestamp,
                       name varchar(255),
                       status int4,
                       primary key (id));

create table IF NOT EXISTS study_course (
                            id uuid not null,
                            academic_degree int4,
                            self_ref varchar(255),
                            name varchar(255),
                            parent_study_course_id uuid,
                            primary key (id),
                            foreign key (parent_study_course_id) references study_course);

create table IF NOT EXISTS module_courses(
                                           study_course_id uuid not null,
                                           module_id uuid not null,
                                           primary key (study_course_id, module_id),
                                           foreign key (module_id) references module,
                                           foreign key (study_course_id) references study_course);


create table IF NOT EXISTS project_modules (
                               project_id uuid not null,
                               modules_id uuid not null,
                               foreign key (project_id) references project,
                               foreign key (modules_id) references module);




