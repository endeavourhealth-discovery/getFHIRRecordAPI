
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 33, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_1717041000006110',
'SN_122554006',
'SN_122555007',
'SN_119311002',
'SN_119329007',
'SN_119347001',
'SN_119339001',
'SN_309201001',
'SN_1717121000006100',
'SN_418564007',
'SN_1717141000006100',
'SN_119326000',
'SN_1717161000006110',
'SN_119321005',
'SN_258483004',
'SN_119327009',
'SN_119299002',
'SN_119361006',
'SN_119323008',
'SN_1717231000006110',
'SN_258664003',
'SN_119364003',
'SN_119334006',
'SN_119350003',
'SN_257261003',
'SN_119376003',
'SN_119324002',
'SN_430304001',
'SN_122575003',
'SN_122572000',
'SN_119336008',
'SN_122552005',
'SN_258450006',
'SN_1717371000006100',
'SN_434711009',
'SN_1717391000006100',
'SN_1717401000006100',
'SN_1717411000006100',
'SN_1717421000006110',
'SN_1717431000006100',
'SN_1717441000006100',
'SN_1717451000006100',
'SN_1717461000006100',
'SN_1717471000006110',
'SN_1717481000006110',
'SN_1717491000006110',
'SN_1717501000006100',
'SN_1717511000006100',
'SN_1717521000006110',
'SN_1717531000006110',
'SN_1717541000006100',
'SN_1717551000006100',
'SN_1717561000006100',
'SN_1717571000006110',
'SN_1717581000006100',
'SN_1717591000006110',
'SN_1717601000006100',
'SN_1717611000006100',
'SN_1717621000006100',
'SN_1717631000006110',
'SN_1717641000006100',
'SN_1717651000006100',
'SN_1717661000006100',
'SN_1717671000006110',
'SN_1717681000006110',
'SN_1717691000006110',
'SN_1717701000006110',
'SN_1717711000006100',
'SN_1717721000006100',
'SN_1717731000006100',
'SN_1717741000006110',
'SN_1717751000006110',
'SN_1717761000006110',
'SN_1717771000006100',
'SN_1717781000006100',
'SN_1717791000006100',
'SN_1717801000006100',
'SN_1717811000006100',
'SN_1717821000006110',
'SN_1717831000006110',
'SN_1717841000006100',
'SN_1717851000006100',
'SN_1717861000006100',
'SN_1717871000006100',
'SN_1717881000006110',
'SN_1717891000006110',
'SN_1717901000006100',
'SN_1717911000006110',
'SN_1717921000006100',
'SN_1717931000006100',
'SN_1717941000006110',
'SN_1717951000006110',
'SN_1717961000006110',
'SN_1717971000006100',
'SN_1717981000006100',
'SN_1717991000006100',
'SN_1718001000006110',
'SN_1718011000006110',
'SN_1718021000006100',
'SN_1718031000006100',
'SN_1718041000006100',
'SN_1718051000006110',
'SN_1718061000006110',
'SN_1718071000006100',
'SN_1718081000006100',
'SN_1718091000006100',
'SN_1718101000006110',
'SN_1718111000006100',
'SN_1718121000006100',
'SN_1718131000006100',
'SN_1718141000006110',
'SN_1718151000006110',
'SN_1718161000006110',
'SN_1718171000006100',
'SN_1718181000006100',
'SN_1718191000006100',
'SN_1718201000006100',
'SN_1718211000006100',
'SN_1718221000006110',
'SN_1718231000006100',
'SN_1718241000006100',
'SN_1718251000006100',
'SN_1718261000006100',
'SN_1718271000006110',
'SN_1718281000006110',
'SN_1718291000006110',
'SN_1718301000006100',
'SN_1718311000006110',
'SN_1718321000006100',
'SN_1718331000006100',
'SN_1718341000006110',
'SN_1718351000006110',
'SN_1718361000006110',
'SN_1718371000006100',
'SN_1718381000006100',
'SN_1718391000006100',
'SN_1718401000006100',
'SN_1718411000006100',
'SN_1718421000006110',
'SN_1718431000006110',
'SN_1718441000006100',
'SN_1718451000006100',
'SN_1718461000006100',
'SN_1718471000006110',
'SN_1718481000006100',
'SN_1718491000006110',
'SN_1718501000006100',
'SN_1718511000006100',
'SN_1718521000006100',
'SN_1718531000006110',
'SN_1718541000006100',
'SN_1718551000006100',
'SN_1718561000006100',
'SN_1718571000006110',
'SN_1718581000006110',
'SN_1718591000006110',
'SN_1718601000006100',
'SN_1718611000006100',
'SN_1718621000006110',
'SN_1718631000006110',
'SN_1718641000006100',
'SN_1718651000006100',
'SN_1718661000006100',
'SN_1718671000006100',
'SN_1718681000006110',
'SN_1718691000006110',
'SN_1718701000006110',
'SN_1718711000006110',
'SN_1718721000006100',
'SN_1718731000006100',
'SN_1718741000006110',
'SN_1718751000006100',
'SN_1718761000006110',
'SN_1718771000006100',
'SN_1718781000006100',
'SN_1718791000006100',
'SN_1718801000006100',
'SN_1718811000006100',
'SN_1718821000006110',
'SN_1718831000006110',
'SN_1718841000006100',
'SN_1718851000006100',
'SN_1718861000006100',
'SN_1718871000006110',
'SN_1718881000006110',
'SN_1718891000006100',
'SN_1718901000006110',
'SN_1718911000006110',
'SN_1718921000006100',
'SN_1718931000006100',
'SN_1718941000006110',
'SN_1718951000006110',
'SN_1718961000006100',
'SN_1718971000006100',
'SN_1718981000006100',
'SN_1718991000006100',
'SN_1719001000006100',
'SN_1719011000006100',
'SN_1719021000006110',
'SN_1719031000006100',
'SN_1719041000006100',
'SN_1719051000006100',
'SN_1719061000006100',
'SN_1719071000006110',
'SN_1719081000006110',
'SN_1719091000006110',
'SN_1719101000006100',
'SN_1719111000006100',
'SN_1719121000006110',
'SN_1719131000006110',
'SN_1719141000006100',
'SN_1719151000006100',
'SN_1719161000006100',
'SN_1719171000006110',
'SN_1719181000006110',
'SN_1719191000006100',
'SN_1719201000006110',
'SN_1719211000006110',
'SN_1719221000006100',
'SN_1719231000006100',
'SN_1719241000006100',
'SN_1719251000006110',
'SN_1719261000006110',
'SN_1719271000006100',
'SN_1719281000006100',
'SN_1719291000006100',
'SN_1719301000006100',
'SN_1719311000006100',
'SN_1719321000006100',
'SN_1719331000006110',
'SN_1719341000006100',
'SN_1719351000006100',
'SN_1719361000006100',
'SN_1719371000006110',
'SN_1719381000006110',
'SN_1719391000006110',
'SN_1719401000006110',
'SN_1719411000006110',
'SN_1719421000006100',
'SN_1719431000006100',
'SN_1719441000006110',
'SN_1719451000006110',
'SN_1719461000006100',
'SN_1719471000006100',
'SN_1719481000006100',
'SN_1719491000006100',
'SN_1719501000006100',
'SN_1719511000006110',
'SN_122555007',
'SN_122575003'
)

union

SELECT distinct 33, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_1717041000006110',
'SN_122554006',
'SN_122555007',
'SN_119311002',
'SN_119329007',
'SN_119347001',
'SN_119339001',
'SN_309201001',
'SN_1717121000006100',
'SN_418564007',
'SN_1717141000006100',
'SN_119326000',
'SN_1717161000006110',
'SN_119321005',
'SN_258483004',
'SN_119327009',
'SN_119299002',
'SN_119361006',
'SN_119323008',
'SN_1717231000006110',
'SN_258664003',
'SN_119364003',
'SN_119334006',
'SN_119350003',
'SN_257261003',
'SN_119376003',
'SN_119324002',
'SN_430304001',
'SN_122575003',
'SN_122572000',
'SN_119336008',
'SN_122552005',
'SN_258450006',
'SN_1717371000006100',
'SN_434711009',
'SN_1717391000006100',
'SN_1717401000006100',
'SN_1717411000006100',
'SN_1717421000006110',
'SN_1717431000006100',
'SN_1717441000006100',
'SN_1717451000006100',
'SN_1717461000006100',
'SN_1717471000006110',
'SN_1717481000006110',
'SN_1717491000006110',
'SN_1717501000006100',
'SN_1717511000006100',
'SN_1717521000006110',
'SN_1717531000006110',
'SN_1717541000006100',
'SN_1717551000006100',
'SN_1717561000006100',
'SN_1717571000006110',
'SN_1717581000006100',
'SN_1717591000006110',
'SN_1717601000006100',
'SN_1717611000006100',
'SN_1717621000006100',
'SN_1717631000006110',
'SN_1717641000006100',
'SN_1717651000006100',
'SN_1717661000006100',
'SN_1717671000006110',
'SN_1717681000006110',
'SN_1717691000006110',
'SN_1717701000006110',
'SN_1717711000006100',
'SN_1717721000006100',
'SN_1717731000006100',
'SN_1717741000006110',
'SN_1717751000006110',
'SN_1717761000006110',
'SN_1717771000006100',
'SN_1717781000006100',
'SN_1717791000006100',
'SN_1717801000006100',
'SN_1717811000006100',
'SN_1717821000006110',
'SN_1717831000006110',
'SN_1717841000006100',
'SN_1717851000006100',
'SN_1717861000006100',
'SN_1717871000006100',
'SN_1717881000006110',
'SN_1717891000006110',
'SN_1717901000006100',
'SN_1717911000006110',
'SN_1717921000006100',
'SN_1717931000006100',
'SN_1717941000006110',
'SN_1717951000006110',
'SN_1717961000006110',
'SN_1717971000006100',
'SN_1717981000006100',
'SN_1717991000006100',
'SN_1718001000006110',
'SN_1718011000006110',
'SN_1718021000006100',
'SN_1718031000006100',
'SN_1718041000006100',
'SN_1718051000006110',
'SN_1718061000006110',
'SN_1718071000006100',
'SN_1718081000006100',
'SN_1718091000006100',
'SN_1718101000006110',
'SN_1718111000006100',
'SN_1718121000006100',
'SN_1718131000006100',
'SN_1718141000006110',
'SN_1718151000006110',
'SN_1718161000006110',
'SN_1718171000006100',
'SN_1718181000006100',
'SN_1718191000006100',
'SN_1718201000006100',
'SN_1718211000006100',
'SN_1718221000006110',
'SN_1718231000006100',
'SN_1718241000006100',
'SN_1718251000006100',
'SN_1718261000006100',
'SN_1718271000006110',
'SN_1718281000006110',
'SN_1718291000006110',
'SN_1718301000006100',
'SN_1718311000006110',
'SN_1718321000006100',
'SN_1718331000006100',
'SN_1718341000006110',
'SN_1718351000006110',
'SN_1718361000006110',
'SN_1718371000006100',
'SN_1718381000006100',
'SN_1718391000006100',
'SN_1718401000006100',
'SN_1718411000006100',
'SN_1718421000006110',
'SN_1718431000006110',
'SN_1718441000006100',
'SN_1718451000006100',
'SN_1718461000006100',
'SN_1718471000006110',
'SN_1718481000006100',
'SN_1718491000006110',
'SN_1718501000006100',
'SN_1718511000006100',
'SN_1718521000006100',
'SN_1718531000006110',
'SN_1718541000006100',
'SN_1718551000006100',
'SN_1718561000006100',
'SN_1718571000006110',
'SN_1718581000006110',
'SN_1718591000006110',
'SN_1718601000006100',
'SN_1718611000006100',
'SN_1718621000006110',
'SN_1718631000006110',
'SN_1718641000006100',
'SN_1718651000006100',
'SN_1718661000006100',
'SN_1718671000006100',
'SN_1718681000006110',
'SN_1718691000006110',
'SN_1718701000006110',
'SN_1718711000006110',
'SN_1718721000006100',
'SN_1718731000006100',
'SN_1718741000006110',
'SN_1718751000006100',
'SN_1718761000006110',
'SN_1718771000006100',
'SN_1718781000006100',
'SN_1718791000006100',
'SN_1718801000006100',
'SN_1718811000006100',
'SN_1718821000006110',
'SN_1718831000006110',
'SN_1718841000006100',
'SN_1718851000006100',
'SN_1718861000006100',
'SN_1718871000006110',
'SN_1718881000006110',
'SN_1718891000006100',
'SN_1718901000006110',
'SN_1718911000006110',
'SN_1718921000006100',
'SN_1718931000006100',
'SN_1718941000006110',
'SN_1718951000006110',
'SN_1718961000006100',
'SN_1718971000006100',
'SN_1718981000006100',
'SN_1718991000006100',
'SN_1719001000006100',
'SN_1719011000006100',
'SN_1719021000006110',
'SN_1719031000006100',
'SN_1719041000006100',
'SN_1719051000006100',
'SN_1719061000006100',
'SN_1719071000006110',
'SN_1719081000006110',
'SN_1719091000006110',
'SN_1719101000006100',
'SN_1719111000006100',
'SN_1719121000006110',
'SN_1719131000006110',
'SN_1719141000006100',
'SN_1719151000006100',
'SN_1719161000006100',
'SN_1719171000006110',
'SN_1719181000006110',
'SN_1719191000006100',
'SN_1719201000006110',
'SN_1719211000006110',
'SN_1719221000006100',
'SN_1719231000006100',
'SN_1719241000006100',
'SN_1719251000006110',
'SN_1719261000006110',
'SN_1719271000006100',
'SN_1719281000006100',
'SN_1719291000006100',
'SN_1719301000006100',
'SN_1719311000006100',
'SN_1719321000006100',
'SN_1719331000006110',
'SN_1719341000006100',
'SN_1719351000006100',
'SN_1719361000006100',
'SN_1719371000006110',
'SN_1719381000006110',
'SN_1719391000006110',
'SN_1719401000006110',
'SN_1719411000006110',
'SN_1719421000006100',
'SN_1719431000006100',
'SN_1719441000006110',
'SN_1719451000006110',
'SN_1719461000006100',
'SN_1719471000006100',
'SN_1719481000006100',
'SN_1719491000006100',
'SN_1719501000006100',
'SN_1719511000006110',
'SN_122555007',
'SN_122575003'
);