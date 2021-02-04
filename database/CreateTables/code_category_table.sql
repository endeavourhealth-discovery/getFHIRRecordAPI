
drop table if exists code_category;

CREATE TABLE code_category
(
    id    			INT 		  		COMMENT 'unique Id for the category',
    description     VARCHAR(255)        COMMENT 'description of the category',

    PRIMARY KEY code_category_id_pk (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  

drop table if exists code_category_values;

CREATE TABLE code_category_values
(
    code_category_id    INT 		  		COMMENT 'unique Id for the category',
    concept_dbid 		BIGINT         		COMMENT 'Concept DBID that belongs in this category',

    PRIMARY KEY code_category_id_concept_dbid_pk (code_category_id, concept_dbid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX ix_code_category_value_concept_dbid
  ON code_category_values
  (concept_dbid);
  
 -- table holds raw im ids that are used to generate the dbid table
CREATE TABLE code_category_values_raw
(
    code_category_id    INT 		  		COMMENT 'unique Id for the category',
    concept_id		varchar(150)         	COMMENT 'Concept ID that belongs in this category'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  
 alter table code_category_values_raw add index idx_concept_id (concept_id);
alter table code_category_values_raw add index idx_category (code_category_id);