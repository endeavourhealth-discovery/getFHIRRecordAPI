
/*
this determines how the app runs
mode:   test - anonymises the demographic information and uses the nhs_mappings below to map fake NHS numbers to real ones
        normal - runs in normal fully identifiable mode and ignores the nhs_mappings

useDSM: Determines if the Data Sharing manager is used to restrict organisations the requesting user is allowed to request data from
        Checks for the relevant project associated with the user and returns all the publishers to that project
        Also returns the config name used by the project which sets what database to use for the extract.
        This means we can have multiple clients using the API getting data from different databases and restricted to specific orgs to their project
        If true then all the above checks are made when a request comes in
        If false then it lets all requests get access to all data (use locally or in dev only)

devConfigName:      When running in dev mode or locally, to save the hassle of setting up DSM on your local machine, you can use this override to
                    set the config Name that would normally be obtained from the project. Set this to a global - db_subscriber entry that matches your config
                    eg.  mysql_compass_v2_pi

nhs_mappings:       In test mode above, maps the fake NHS numbers to real NHS numbers
 */
INSERT INTO config.config
values
('fhir-record-api', 'run_mode',
 '{
	"mode": "normal",
	"useDSM": "false",
	"devConfigName": "mysql_compass_v2_pi",
	"nhs_mappings": [
		{
			"anonymised": "9999999991",
			"mapped": "9999999999"
		},
		{
			"anonymised": "9999999999",
			"mapped": "9451944117"
		}
	]
}');

insert into config.config (app_id, config_id, config_data)
select 'fhir-record-api', 'db_credentials',
    '{
	"username": "root",
	"password": "xcxcx"
}';

insert into config.config (app_id, config_id, config_data)
select 'fhir-record-api', 'keycloak',
       '{
            "realm": "endeavour2",
            "realm-public-key": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvi7bZ3cX5wA412sbm0Rk2aAuOEjZuMdrSnRtSDCsebVzu4MLu+HZlbYLt7Mpswnc1/MJnatJE+zoraVhkNNrikKTImp2AraCFgz5cf2Xw2M6yRNSSeLatN8E4k8cMAThD7pKzbvRUOuX8l3ez0TssMNvhzEksEDcqVhb5hRE3AHhmkXHeBtqrwG0S+RpOmp5UWeOLy3Zi9QNAACkOd0a1aE65frW0Wm2QXVHeII1AqKLi99f60ktMwhC36DYlzb6aqTiquixl8/mnkZB0Yh82/7xTbqKzdI+yeCFGdUrkELBmg03bjogf0BaWa7yv4vG6mKPgr5iDkrxLZYd+8z9ZQIDAQAB",
            "auth-server-url": "https://devauth.discoverydataservice.net/auth",
            "ssl-required": "external",
            "resource": "eds-dsa-manager",
            "public-client": true
        }';


insert into config.config (app_id, config_id, config_data)
select 'ex_access_filter', 'keycloak_filter',
       '{
             "auth-server-url": "https://devauth.discoverydataservice.net/",
             "path_prefix": "auth/realms",
             "realm": "endeavour2",
             "path_suffix": "protocol/openid-connect/userinfo"
         }';