INSERT INTO config.config
values
('fhir-record-api', 'run_mode',  '{"mode" : "test","nhs_mappings": [{ "anonymised": "9999999991","mapped": "1234567899"},{"anonymised": "9999999992","mapped": "9876543211"}]}');

insert into config.config (app_id, config_id, config_data)
select 'fhir-record-api', 'database',
       '{
           "url" : "jdbc:mysql://localhost:3306/compass_v2_pi?useSSL=false",
           "username" : "root",
           "password" : "XXXX"
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