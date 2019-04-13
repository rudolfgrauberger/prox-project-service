CREATE TABLE IF NOT EXISTS "project-db".module (
                                   id uuid NOT NULL,
                                   self_ref character varying(255),
                                   name character varying(255)
);

CREATE TABLE IF NOT EXISTS "project-db".module_courses (
                                           study_course_id uuid NOT NULL,
                                           module_id uuid NOT NULL
);

CREATE TABLE IF NOT EXISTS "project-db".project (
                                    id uuid NOT NULL,
                                    created timestamp without time zone,
                                    creatorid uuid,
                                    creator_name character varying(255),
                                    description character varying(3000),
                                    modified timestamp without time zone,
                                    name character varying(255),
                                    status integer,
                                    supervisor_name character varying(255)
);

CREATE TABLE IF NOT EXISTS "project-db".project_modules (
                                            project_id uuid NOT NULL,
                                            modules_id uuid NOT NULL
);

CREATE TABLE IF NOT EXISTS "project-db".study_course (
                                         id uuid NOT NULL,
                                         academic_degree integer,
                                         self_ref character varying(255),
                                         name character varying(255)
);

CREATE TABLE IF NOT EXISTS "project-db".study_course_modules (
                                                 study_course_id uuid NOT NULL,
                                                 modules_id uuid NOT NULL
);
