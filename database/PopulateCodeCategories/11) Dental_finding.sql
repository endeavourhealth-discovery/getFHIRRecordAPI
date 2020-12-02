
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 11, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_1002211000006100',
'SN_1002221000006100',
'SN_1002231000006110',
'SN_1002241000006100',
'SN_1002251000006100',
'SN_1002261000006100',
'SN_1002271000006110',
'SN_1002301000006110',
'SN_1002321000006100',
'SN_1002331000006100',
'SN_1002341000006100',
'SN_1002351000006110',
'SN_1002361000006110',
'SN_1002371000006100',
'SN_1002381000006100',
'SN_1002391000006100',
'SN_1002401000006100',
'SN_1002411000006100',
'SN_1002421000006110',
'SN_1002431000006110',
'SN_1002441000006100',
'SN_1002541000006100',
'SN_1002551000006100',
'SN_1004171000006110',
'SN_1004181000006110',
'SN_1004191000006110',
'SN_1004201000006110',
'SN_1004211000006100',
'SN_1004371000006100',
'SN_1004381000006110',
'SN_1004391000006110',
'SN_1004401000006110',
'SN_1004411000006110',
'SN_1004421000006100',
'SN_1004431000006100',
'SN_1004441000006100',
'SN_1004451000006110',
'SN_1004461000006110',
'SN_1004471000006100',
'SN_1004481000006100',
'SN_1004491000006100',
'SN_1004501000006110',
'SN_1004511000006110',
'SN_1004521000006100',
'SN_1004531000006100',
'SN_1004541000006110',
'SN_1004551000006110',
'SN_1004561000006100',
'SN_1004571000006100',
'SN_1004581000006100',
'SN_1004591000006100',
'SN_1004601000006100',
'SN_1004751000006100',
'SN_1004761000006100',
'SN_1004881000006100',
'SN_1004891000006100',
'SN_1005111000006100',
'SN_1005121000006110',
'SN_1005131000006110',
'SN_1005141000006100',
'SN_1005151000006100',
'SN_1005161000006100',
'SN_1005171000006110',
'SN_1005181000006100',
'SN_1005191000006110',
'SN_1005201000006110',
'SN_1005211000006110',
'SN_1573491000006110',
'SN_1573511000006100',
'SN_1573531000006110',
'SN_1573541000006100',
'SN_1573551000006100',
'SN_1573561000006100',
'SN_1573571000006110',
'SN_1573581000006100',
'SN_1573591000006110',
'SN_1573601000006100',
'SN_1573781000006100',
'SN_1573791000006100',
'SN_1573801000006100',
'SN_1573811000006100',
'SN_1573821000006110',
'SN_1573831000006110',
'SN_1573841000006100',
'SN_1573851000006100',
'SN_1573861000006100',
'SN_1573871000006100',
'SN_1573881000006110',
'SN_1573891000006110',
'SN_1573901000006100',
'SN_1573931000006100',
'SN_1573951000006110',
'SN_1573961000006110',
'SN_1573971000006100',
'SN_1573981000006100',
'SN_1573991000006100',
'SN_1574001000006110',
'SN_1574011000006100',
'SN_1574021000006100',
'SN_1574031000006100',
'SN_1574041000006110',
'SN_1574141000006110',
'SN_1574151000006100',
'SN_1574161000006110',
'SN_1574221000006110',
'SN_1574231000006110',
'SN_1574241000006100',
'SN_1574251000006100',
'SN_1574261000006100',
'SN_1574271000006110',
'SN_1574281000006100',
'SN_1574291000006110',
'SN_1574301000006110',
'SN_1574311000006110',
'SN_1574321000006100',
'SN_1574331000006100',
'SN_1574341000006110',
'SN_1574351000006110',
'SN_1574361000006100',
'SN_1574371000006100',
'SN_1574381000006100',
'SN_1574391000006100',
'SN_1574401000006100',
'SN_1574411000006100',
'SN_1574421000006100',
'SN_1574431000006110',
'SN_1574441000006100',
'SN_1599741000006100',
'SN_1599751000006100',
'SN_1599761000006100',
'SN_1599771000006100',
'SN_1599781000006110',
'SN_1599791000006110',
'SN_1599801000006110',
'SN_1599811000006100',
'SN_1599821000006100',
'SN_1599831000006100',
'SN_1599841000006110',
'SN_1599851000006110',
'SN_1599901000006100',
'SN_1599911000006100',
'SN_1599921000006110',
'SN_1599931000006110',
'SN_1599941000006100',
'SN_1599951000006100',
'SN_1599961000006100',
'SN_1599971000006110',
'SN_1599981000006100',
'SN_1599991000006110',
'SN_1600001000006100',
'SN_1600381000006110',
'SN_1600391000006110',
'SN_1600401000006110',
'SN_1600411000006110',
'SN_1600421000006100',
'SN_1600431000006100',
'SN_1600441000006110',
'SN_1600591000006100',
'SN_1618821000006100',
'SN_1618831000006100',
'SN_1619191000006100',
'SN_1619201000006100',
'SN_1619211000006100',
'SN_1619221000006110',
'SN_1619271000006100',
'SN_1620161000006110',
'SN_1620171000006100',
'SN_1620181000006100',
'SN_1620191000006100',
'SN_1620201000006100',
'SN_1620211000006100',
'SN_1620221000006110',
'SN_1620231000006110',
'SN_1620241000006100',
'SN_1620251000006100',
'SN_1620261000006100',
'SN_1620271000006110',
'SN_1620281000006110',
'SN_1620291000006100',
'SN_1663071000006100',
'SN_1663081000006100',
'SN_1663101000006110',
'SN_1664121000006100',
'SN_1664221000006110',
'SN_1664231000006110',
'SN_1664241000006100',
'SN_1664251000006100',
'SN_1664261000006100',
'SN_1664271000006110',
'SN_1664281000006100',
'SN_1664291000006110',
'SN_1664361000006100',
'SN_1672761000006110',
'SN_1672781000006100',
'SN_1675071000006110',
'SN_1675081000006110',
'SN_1675091000006100',
'SN_1675101000006100',
'SN_1675111000006100',
'SN_1675121000006110',
'SN_1675131000006110',
'SN_1675141000006100',
'SN_1675151000006100',
'SN_1675161000006100',
'SN_1675171000006100',
'SN_1675181000006110',
'SN_1675191000006110',
'SN_1675201000006110',
'SN_1675211000006110',
'SN_1675271000006100',
'SN_1675281000006100',
'SN_1675301000006100',
'SN_1675311000006100',
'SN_1675321000006110',
'SN_1675331000006110',
'SN_1707661000006100',
'SN_1707671000006110',
'SN_1707681000006110',
'SN_1708101000006100',
'SN_1726611000006110',
'SN_1726621000006100',
'SN_1726631000006100',
'SN_1726641000006110',
'SN_1726651000006110',
'SN_1726661000006110',
'SN_1745471000006110',
'SN_1745481000006110',
'SN_1746631000006100',
'SN_1746641000006110',
'SN_1746651000006110',
'SN_1746661000006110',
'SN_1746671000006100',
'SN_1746681000006100',
'SN_74024006',
'SN_1771101000006100',
'SN_1771111000006110',
'SN_1802821000006110',
'SN_1802831000006100',
'SN_1802841000006100',
'SN_1802851000006100',
'SN_1802861000006100',
'SN_1802871000006110',
'SN_1802881000006110',
'SN_1802891000006110',
'SN_1802901000006110',
'SN_1802951000006110',
'SN_1802961000006110',
'SN_1802971000006100',
'SN_1802981000006100',
'SN_1802991000006100',
'SN_1803001000006100',
'SN_1803011000006100',
'SN_1803071000006110',
'SN_1803081000006110',
'SN_1803091000006110',
'SN_1803101000006100',
'SN_1803111000006100',
'SN_1803121000006110',
'SN_1803131000006110',
'SN_1803521000006100',
'SN_251292006',
'SN_1809571000006100',
'SN_1809581000006100',
'SN_1809591000006100',
'SN_1818121000006100',
'SN_1818131000006100',
'SN_1818141000006110',
'SN_1818151000006110',
'SN_1818161000006110',
'SN_1818171000006100',
'SN_1818181000006100',
'SN_1818191000006100',
'SN_1818201000006100',
'SN_1818221000006100',
'SN_278655007',
'SN_278680007',
'SN_2010221000006110'
)


union

SELECT distinct 11, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_1002211000006100',
'SN_1002221000006100',
'SN_1002231000006110',
'SN_1002241000006100',
'SN_1002251000006100',
'SN_1002261000006100',
'SN_1002271000006110',
'SN_1002301000006110',
'SN_1002321000006100',
'SN_1002331000006100',
'SN_1002341000006100',
'SN_1002351000006110',
'SN_1002361000006110',
'SN_1002371000006100',
'SN_1002381000006100',
'SN_1002391000006100',
'SN_1002401000006100',
'SN_1002411000006100',
'SN_1002421000006110',
'SN_1002431000006110',
'SN_1002441000006100',
'SN_1002541000006100',
'SN_1002551000006100',
'SN_1004171000006110',
'SN_1004181000006110',
'SN_1004191000006110',
'SN_1004201000006110',
'SN_1004211000006100',
'SN_1004371000006100',
'SN_1004381000006110',
'SN_1004391000006110',
'SN_1004401000006110',
'SN_1004411000006110',
'SN_1004421000006100',
'SN_1004431000006100',
'SN_1004441000006100',
'SN_1004451000006110',
'SN_1004461000006110',
'SN_1004471000006100',
'SN_1004481000006100',
'SN_1004491000006100',
'SN_1004501000006110',
'SN_1004511000006110',
'SN_1004521000006100',
'SN_1004531000006100',
'SN_1004541000006110',
'SN_1004551000006110',
'SN_1004561000006100',
'SN_1004571000006100',
'SN_1004581000006100',
'SN_1004591000006100',
'SN_1004601000006100',
'SN_1004751000006100',
'SN_1004761000006100',
'SN_1004881000006100',
'SN_1004891000006100',
'SN_1005111000006100',
'SN_1005121000006110',
'SN_1005131000006110',
'SN_1005141000006100',
'SN_1005151000006100',
'SN_1005161000006100',
'SN_1005171000006110',
'SN_1005181000006100',
'SN_1005191000006110',
'SN_1005201000006110',
'SN_1005211000006110',
'SN_1573491000006110',
'SN_1573511000006100',
'SN_1573531000006110',
'SN_1573541000006100',
'SN_1573551000006100',
'SN_1573561000006100',
'SN_1573571000006110',
'SN_1573581000006100',
'SN_1573591000006110',
'SN_1573601000006100',
'SN_1573781000006100',
'SN_1573791000006100',
'SN_1573801000006100',
'SN_1573811000006100',
'SN_1573821000006110',
'SN_1573831000006110',
'SN_1573841000006100',
'SN_1573851000006100',
'SN_1573861000006100',
'SN_1573871000006100',
'SN_1573881000006110',
'SN_1573891000006110',
'SN_1573901000006100',
'SN_1573931000006100',
'SN_1573951000006110',
'SN_1573961000006110',
'SN_1573971000006100',
'SN_1573981000006100',
'SN_1573991000006100',
'SN_1574001000006110',
'SN_1574011000006100',
'SN_1574021000006100',
'SN_1574031000006100',
'SN_1574041000006110',
'SN_1574141000006110',
'SN_1574151000006100',
'SN_1574161000006110',
'SN_1574221000006110',
'SN_1574231000006110',
'SN_1574241000006100',
'SN_1574251000006100',
'SN_1574261000006100',
'SN_1574271000006110',
'SN_1574281000006100',
'SN_1574291000006110',
'SN_1574301000006110',
'SN_1574311000006110',
'SN_1574321000006100',
'SN_1574331000006100',
'SN_1574341000006110',
'SN_1574351000006110',
'SN_1574361000006100',
'SN_1574371000006100',
'SN_1574381000006100',
'SN_1574391000006100',
'SN_1574401000006100',
'SN_1574411000006100',
'SN_1574421000006100',
'SN_1574431000006110',
'SN_1574441000006100',
'SN_1599741000006100',
'SN_1599751000006100',
'SN_1599761000006100',
'SN_1599771000006100',
'SN_1599781000006110',
'SN_1599791000006110',
'SN_1599801000006110',
'SN_1599811000006100',
'SN_1599821000006100',
'SN_1599831000006100',
'SN_1599841000006110',
'SN_1599851000006110',
'SN_1599901000006100',
'SN_1599911000006100',
'SN_1599921000006110',
'SN_1599931000006110',
'SN_1599941000006100',
'SN_1599951000006100',
'SN_1599961000006100',
'SN_1599971000006110',
'SN_1599981000006100',
'SN_1599991000006110',
'SN_1600001000006100',
'SN_1600381000006110',
'SN_1600391000006110',
'SN_1600401000006110',
'SN_1600411000006110',
'SN_1600421000006100',
'SN_1600431000006100',
'SN_1600441000006110',
'SN_1600591000006100',
'SN_1618821000006100',
'SN_1618831000006100',
'SN_1619191000006100',
'SN_1619201000006100',
'SN_1619211000006100',
'SN_1619221000006110',
'SN_1619271000006100',
'SN_1620161000006110',
'SN_1620171000006100',
'SN_1620181000006100',
'SN_1620191000006100',
'SN_1620201000006100',
'SN_1620211000006100',
'SN_1620221000006110',
'SN_1620231000006110',
'SN_1620241000006100',
'SN_1620251000006100',
'SN_1620261000006100',
'SN_1620271000006110',
'SN_1620281000006110',
'SN_1620291000006100',
'SN_1663071000006100',
'SN_1663081000006100',
'SN_1663101000006110',
'SN_1664121000006100',
'SN_1664221000006110',
'SN_1664231000006110',
'SN_1664241000006100',
'SN_1664251000006100',
'SN_1664261000006100',
'SN_1664271000006110',
'SN_1664281000006100',
'SN_1664291000006110',
'SN_1664361000006100',
'SN_1672761000006110',
'SN_1672781000006100',
'SN_1675071000006110',
'SN_1675081000006110',
'SN_1675091000006100',
'SN_1675101000006100',
'SN_1675111000006100',
'SN_1675121000006110',
'SN_1675131000006110',
'SN_1675141000006100',
'SN_1675151000006100',
'SN_1675161000006100',
'SN_1675171000006100',
'SN_1675181000006110',
'SN_1675191000006110',
'SN_1675201000006110',
'SN_1675211000006110',
'SN_1675271000006100',
'SN_1675281000006100',
'SN_1675301000006100',
'SN_1675311000006100',
'SN_1675321000006110',
'SN_1675331000006110',
'SN_1707661000006100',
'SN_1707671000006110',
'SN_1707681000006110',
'SN_1708101000006100',
'SN_1726611000006110',
'SN_1726621000006100',
'SN_1726631000006100',
'SN_1726641000006110',
'SN_1726651000006110',
'SN_1726661000006110',
'SN_1745471000006110',
'SN_1745481000006110',
'SN_1746631000006100',
'SN_1746641000006110',
'SN_1746651000006110',
'SN_1746661000006110',
'SN_1746671000006100',
'SN_1746681000006100',
'SN_74024006',
'SN_1771101000006100',
'SN_1771111000006110',
'SN_1802821000006110',
'SN_1802831000006100',
'SN_1802841000006100',
'SN_1802851000006100',
'SN_1802861000006100',
'SN_1802871000006110',
'SN_1802881000006110',
'SN_1802891000006110',
'SN_1802901000006110',
'SN_1802951000006110',
'SN_1802961000006110',
'SN_1802971000006100',
'SN_1802981000006100',
'SN_1802991000006100',
'SN_1803001000006100',
'SN_1803011000006100',
'SN_1803071000006110',
'SN_1803081000006110',
'SN_1803091000006110',
'SN_1803101000006100',
'SN_1803111000006100',
'SN_1803121000006110',
'SN_1803131000006110',
'SN_1803521000006100',
'SN_251292006',
'SN_1809571000006100',
'SN_1809581000006100',
'SN_1809591000006100',
'SN_1818121000006100',
'SN_1818131000006100',
'SN_1818141000006110',
'SN_1818151000006110',
'SN_1818161000006110',
'SN_1818171000006100',
'SN_1818181000006100',
'SN_1818191000006100',
'SN_1818201000006100',
'SN_1818221000006100',
'SN_278655007',
'SN_278680007',
'SN_2010221000006110'
);