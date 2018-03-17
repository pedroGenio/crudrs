CREATE SCHEMA sch_crud;

DROP TABLE IF EXISTS sch_crud.project;

CREATE TABLE sch_crud.project
(
    id serial NOT NULL,
    version bigint NOT NULL DEFAULT 0,
    name character varying(255) NOT NULL,
    description text,
    status character varying(20) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    updated_at timestamp without time zone NULL,
    updated_by bigint NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE sch_crud.project
   ADD CONSTRAINT check_status
   CHECK (status IN ('DRAFT', 'REVIEW', 'PRODUCTION', 'CANCELLED') );

/*
{
"name":"Pedro Elias",
"description":"Description of the Project",
"status":"PRODUCTION"
"createdBy":2,
}
 */