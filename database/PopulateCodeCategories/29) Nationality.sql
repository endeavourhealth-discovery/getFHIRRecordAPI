
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 29, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_1564151000006100',
'SN_1564161000006100',
'SN_1564171000006110',
'SN_1564181000006110',
'SN_1564191000006110',
'SN_1564201000006100',
'SN_1564211000006110',
'SN_1564221000006100',
'SN_1564231000006100',
'SN_1564241000006110',
'SN_1564251000006110',
'SN_1564261000006110',
'SN_1564271000006100',
'SN_1564281000006100',
'SN_1564291000006100',
'SN_1564301000006100',
'SN_1564311000006100',
'SN_1564321000006110',
'SN_1564331000006100',
'SN_1564341000006100',
'SN_1564351000006100',
'SN_1564361000006100',
'SN_1564371000006110',
'SN_1564381000006110',
'SN_1564391000006110',
'SN_1564401000006110',
'SN_1564411000006110',
'SN_1564421000006100',
'SN_1564431000006100',
'SN_1564441000006110',
'SN_1564451000006100',
'SN_1564461000006110',
'SN_1564471000006100',
'SN_1564481000006100',
'SN_1564491000006100',
'SN_1564501000006110',
'SN_1564511000006100',
'SN_1564521000006100',
'SN_1564531000006100',
'SN_1564541000006110',
'SN_1564551000006110',
'SN_1564561000006110',
'SN_1564571000006100',
'SN_1564581000006100',
'SN_1564591000006100',
'SN_1564601000006110',
'SN_1564611000006110',
'SN_1564621000006100',
'SN_1564631000006100',
'SN_1564641000006100',
'SN_1564651000006110',
'SN_1564661000006110',
'SN_1564671000006100',
'SN_1564681000006100',
'SN_1564691000006100',
'SN_1564701000006100',
'SN_1564711000006100',
'SN_1564721000006110',
'SN_1564731000006110',
'SN_1564741000006100',
'SN_1564751000006100',
'SN_1564761000006100',
'SN_1564771000006110',
'SN_1564781000006100',
'SN_1564791000006110',
'SN_1564801000006110',
'SN_1564811000006110',
'SN_1564821000006100',
'SN_1564831000006100',
'SN_1564841000006110',
'SN_1564851000006110',
'SN_1564861000006100',
'SN_1564871000006100',
'SN_1564881000006100',
'SN_1564891000006100',
'SN_1564901000006100',
'SN_1564911000006100',
'SN_1564921000006110',
'SN_1564931000006110',
'SN_1564941000006100',
'SN_1564951000006100',
'SN_1564961000006100',
'SN_1564971000006110',
'SN_1564981000006110',
'SN_1564991000006100',
'SN_1565001000006100',
'SN_1565011000006100',
'SN_1565021000006110',
'SN_1565031000006110',
'SN_1565041000006100',
'SN_1565051000006100',
'SN_1565061000006100',
'SN_1565071000006110',
'SN_1565081000006110',
'SN_1565091000006100',
'SN_1565101000006100',
'SN_1565111000006100',
'SN_1565121000006110',
'SN_1565131000006110',
'SN_1565141000006100',
'SN_1565151000006100',
'SN_1565161000006100',
'SN_1565171000006100',
'SN_1565181000006110',
'SN_1565191000006110',
'SN_1565201000006110',
'SN_1565211000006110',
'SN_1565221000006100',
'SN_1565231000006100',
'SN_1565241000006110',
'SN_1565251000006110',
'SN_1565261000006100',
'SN_1565271000006100',
'SN_1565281000006100',
'SN_1565291000006100',
'SN_1565301000006100',
'SN_1565311000006100',
'SN_1565321000006110',
'SN_1565331000006110',
'SN_1565341000006100',
'SN_1565351000006100',
'SN_1565361000006100',
'SN_1565371000006110',
'SN_1565381000006100',
'SN_1565391000006110',
'SN_1565401000006100',
'SN_1565411000006110',
'SN_1565421000006100',
'SN_1565431000006100',
'SN_1565441000006110',
'SN_1565451000006110',
'SN_1565461000006110',
'SN_1565471000006100',
'SN_1565481000006100',
'SN_1565491000006100',
'SN_1565501000006110',
'SN_1565511000006110',
'SN_1565521000006100',
'SN_1565531000006100',
'SN_1565541000006110',
'SN_1565551000006100',
'SN_1565561000006110',
'SN_1565571000006100',
'SN_1565581000006100',
'SN_1565591000006100',
'SN_1565601000006110',
'SN_1565611000006100',
'SN_1565621000006100',
'SN_1565631000006100',
'SN_1565641000006110',
'SN_1565651000006110',
'SN_1565661000006110',
'SN_1565671000006100',
'SN_1565681000006100',
'SN_1565691000006100',
'SN_1565701000006100',
'SN_1565711000006100',
'SN_1565721000006100',
'SN_1565731000006110',
'SN_1565741000006100',
'SN_1565751000006100',
'SN_1565761000006100',
'SN_1565771000006110',
'SN_1565781000006110',
'SN_1565791000006110',
'SN_1565801000006110',
'SN_1565811000006110',
'SN_1565821000006100',
'SN_1565831000006100',
'SN_1565841000006100',
'SN_1565851000006110',
'SN_1565861000006110',
'SN_1565871000006100',
'SN_1565881000006100',
'SN_1565891000006100',
'SN_1565901000006100',
'SN_1565911000006100',
'SN_1565921000006110',
'SN_1565931000006100',
'SN_1565941000006100',
'SN_1565951000006100',
'SN_1565961000006100',
'SN_1565971000006110',
'SN_1565981000006110',
'SN_1565991000006110'
)

union

SELECT distinct 29, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_1564151000006100',
'SN_1564161000006100',
'SN_1564171000006110',
'SN_1564181000006110',
'SN_1564191000006110',
'SN_1564201000006100',
'SN_1564211000006110',
'SN_1564221000006100',
'SN_1564231000006100',
'SN_1564241000006110',
'SN_1564251000006110',
'SN_1564261000006110',
'SN_1564271000006100',
'SN_1564281000006100',
'SN_1564291000006100',
'SN_1564301000006100',
'SN_1564311000006100',
'SN_1564321000006110',
'SN_1564331000006100',
'SN_1564341000006100',
'SN_1564351000006100',
'SN_1564361000006100',
'SN_1564371000006110',
'SN_1564381000006110',
'SN_1564391000006110',
'SN_1564401000006110',
'SN_1564411000006110',
'SN_1564421000006100',
'SN_1564431000006100',
'SN_1564441000006110',
'SN_1564451000006100',
'SN_1564461000006110',
'SN_1564471000006100',
'SN_1564481000006100',
'SN_1564491000006100',
'SN_1564501000006110',
'SN_1564511000006100',
'SN_1564521000006100',
'SN_1564531000006100',
'SN_1564541000006110',
'SN_1564551000006110',
'SN_1564561000006110',
'SN_1564571000006100',
'SN_1564581000006100',
'SN_1564591000006100',
'SN_1564601000006110',
'SN_1564611000006110',
'SN_1564621000006100',
'SN_1564631000006100',
'SN_1564641000006100',
'SN_1564651000006110',
'SN_1564661000006110',
'SN_1564671000006100',
'SN_1564681000006100',
'SN_1564691000006100',
'SN_1564701000006100',
'SN_1564711000006100',
'SN_1564721000006110',
'SN_1564731000006110',
'SN_1564741000006100',
'SN_1564751000006100',
'SN_1564761000006100',
'SN_1564771000006110',
'SN_1564781000006100',
'SN_1564791000006110',
'SN_1564801000006110',
'SN_1564811000006110',
'SN_1564821000006100',
'SN_1564831000006100',
'SN_1564841000006110',
'SN_1564851000006110',
'SN_1564861000006100',
'SN_1564871000006100',
'SN_1564881000006100',
'SN_1564891000006100',
'SN_1564901000006100',
'SN_1564911000006100',
'SN_1564921000006110',
'SN_1564931000006110',
'SN_1564941000006100',
'SN_1564951000006100',
'SN_1564961000006100',
'SN_1564971000006110',
'SN_1564981000006110',
'SN_1564991000006100',
'SN_1565001000006100',
'SN_1565011000006100',
'SN_1565021000006110',
'SN_1565031000006110',
'SN_1565041000006100',
'SN_1565051000006100',
'SN_1565061000006100',
'SN_1565071000006110',
'SN_1565081000006110',
'SN_1565091000006100',
'SN_1565101000006100',
'SN_1565111000006100',
'SN_1565121000006110',
'SN_1565131000006110',
'SN_1565141000006100',
'SN_1565151000006100',
'SN_1565161000006100',
'SN_1565171000006100',
'SN_1565181000006110',
'SN_1565191000006110',
'SN_1565201000006110',
'SN_1565211000006110',
'SN_1565221000006100',
'SN_1565231000006100',
'SN_1565241000006110',
'SN_1565251000006110',
'SN_1565261000006100',
'SN_1565271000006100',
'SN_1565281000006100',
'SN_1565291000006100',
'SN_1565301000006100',
'SN_1565311000006100',
'SN_1565321000006110',
'SN_1565331000006110',
'SN_1565341000006100',
'SN_1565351000006100',
'SN_1565361000006100',
'SN_1565371000006110',
'SN_1565381000006100',
'SN_1565391000006110',
'SN_1565401000006100',
'SN_1565411000006110',
'SN_1565421000006100',
'SN_1565431000006100',
'SN_1565441000006110',
'SN_1565451000006110',
'SN_1565461000006110',
'SN_1565471000006100',
'SN_1565481000006100',
'SN_1565491000006100',
'SN_1565501000006110',
'SN_1565511000006110',
'SN_1565521000006100',
'SN_1565531000006100',
'SN_1565541000006110',
'SN_1565551000006100',
'SN_1565561000006110',
'SN_1565571000006100',
'SN_1565581000006100',
'SN_1565591000006100',
'SN_1565601000006110',
'SN_1565611000006100',
'SN_1565621000006100',
'SN_1565631000006100',
'SN_1565641000006110',
'SN_1565651000006110',
'SN_1565661000006110',
'SN_1565671000006100',
'SN_1565681000006100',
'SN_1565691000006100',
'SN_1565701000006100',
'SN_1565711000006100',
'SN_1565721000006100',
'SN_1565731000006110',
'SN_1565741000006100',
'SN_1565751000006100',
'SN_1565761000006100',
'SN_1565771000006110',
'SN_1565781000006110',
'SN_1565791000006110',
'SN_1565801000006110',
'SN_1565811000006110',
'SN_1565821000006100',
'SN_1565831000006100',
'SN_1565841000006100',
'SN_1565851000006110',
'SN_1565861000006110',
'SN_1565871000006100',
'SN_1565881000006100',
'SN_1565891000006100',
'SN_1565901000006100',
'SN_1565911000006100',
'SN_1565921000006110',
'SN_1565931000006100',
'SN_1565941000006100',
'SN_1565951000006100',
'SN_1565961000006100',
'SN_1565971000006110',
'SN_1565981000006110',
'SN_1565991000006110'
);