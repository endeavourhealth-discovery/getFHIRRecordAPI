
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 12, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_1002291000006110',
'SN_1002451000006100',
'SN_1002461000006100',
'SN_1002471000006100',
'SN_1002481000006110',
'SN_1002491000006110',
'SN_1002501000006100',
'SN_1002511000006100',
'SN_1002521000006110',
'SN_1002531000006110',
'SN_1002601000006100',
'SN_1002611000006100',
'SN_1002621000006110',
'SN_1002631000006100',
'SN_1002641000006100',
'SN_1002651000006100',
'SN_1002661000006100',
'SN_1002671000006110',
'SN_1002681000006110',
'SN_1002761000006100',
'SN_1002771000006100',
'SN_1002781000006100',
'SN_1002791000006100',
'SN_1002801000006100',
'SN_1002811000006100',
'SN_1002821000006110',
'SN_1002831000006110',
'SN_1002841000006100',
'SN_1002851000006100',
'SN_1002861000006100',
'SN_1002871000006110',
'SN_1002881000006100',
'SN_1002891000006110',
'SN_1002901000006110',
'SN_1002911000006100',
'SN_1002921000006100',
'SN_1002931000006100',
'SN_1002941000006110',
'SN_1002951000006110',
'SN_1002961000006110',
'SN_1002971000006100',
'SN_1002981000006100',
'SN_1002991000006100',
'SN_1003001000006100',
'SN_1003011000006100',
'SN_1003021000006110',
'SN_1003031000006110',
'SN_1003041000006100',
'SN_1003051000006100',
'SN_1003061000006100',
'SN_1003071000006100',
'SN_1003081000006110',
'SN_1003091000006110',
'SN_1003101000006100',
'SN_1003111000006100',
'SN_1003121000006100',
'SN_1003131000006110',
'SN_1003141000006100',
'SN_1003151000006100',
'SN_1003161000006100',
'SN_1003171000006110',
'SN_1003251000006110',
'SN_1003261000006110',
'SN_1003271000006100',
'SN_1003431000006100',
'SN_1003441000006110',
'SN_1003451000006100',
'SN_1003461000006110',
'SN_1003471000006100',
'SN_1003481000006100',
'SN_1003491000006100',
'SN_1003501000006110',
'SN_1003511000006100',
'SN_1003591000006100',
'SN_1003601000006110',
'SN_1003951000006100',
'SN_1004101000006100',
'SN_1004111000006100',
'SN_1004121000006110',
'SN_1004131000006100',
'SN_1004141000006100',
'SN_1004151000006100',
'SN_1004161000006100',
'SN_1004271000006100',
'SN_1004281000006100',
'SN_1004291000006100',
'SN_1004301000006100',
'SN_1004621000006100',
'SN_1004631000006100',
'SN_1004641000006110',
'SN_1004651000006110',
'SN_1004661000006110',
'SN_1004671000006100',
'SN_1004681000006100',
'SN_1004691000006100',
'SN_1004771000006110',
'SN_1004781000006110',
'SN_1004791000006100',
'SN_1004801000006110',
'SN_1004811000006110',
'SN_1004821000006100',
'SN_1004831000006100',
'SN_1004841000006110',
'SN_1004851000006100',
'SN_1004861000006110',
'SN_1004871000006100',
'SN_1004921000006100',
'SN_1004931000006110',
'SN_1004941000006100',
'SN_1004951000006100',
'SN_1004961000006100',
'SN_1004971000006110',
'SN_1004981000006110',
'SN_1004991000006110',
'SN_1005001000006100',
'SN_1005011000006100',
'SN_1005021000006100',
'SN_1005031000006110',
'SN_1005041000006100',
'SN_1005051000006100',
'SN_1005061000006100',
'SN_1005071000006110',
'SN_1005081000006110',
'SN_1005091000006110',
'SN_1005101000006100',
'SN_1005221000006100',
'SN_1005231000006100',
'SN_1005241000006110',
'SN_1005251000006100',
'SN_1005261000006110',
'SN_1005271000006100',
'SN_1005281000006100',
'SN_1005291000006100',
'SN_1005321000006110',
'SN_1005331000006110',
'SN_1005341000006100',
'SN_1005351000006100',
'SN_1005361000006100',
'SN_1005371000006110',
'SN_1005381000006110',
'SN_1005391000006100',
'SN_1005401000006110',
'SN_1005411000006100',
'SN_1005421000006100',
'SN_1005431000006100',
'SN_1005441000006110',
'SN_1005451000006110',
'SN_1005461000006110',
'SN_1005471000006100',
'SN_1005481000006100',
'SN_1005491000006100',
'SN_1005501000006110',
'SN_1005511000006110',
'SN_1005521000006100',
'SN_1005531000006100',
'SN_1005541000006100',
'SN_1005551000006110',
'SN_1005561000006110',
'SN_1005571000006100',
'SN_1005581000006100',
'SN_1005591000006100',
'SN_1005601000006110',
'SN_1005611000006110',
'SN_1005621000006100',
'SN_1005631000006100',
'SN_1005641000006110',
'SN_1005651000006110',
'SN_1005661000006100',
'SN_1005671000006100',
'SN_1005681000006100',
'SN_1005691000006100',
'SN_1005701000006100',
'SN_1005711000006100',
'SN_1005721000006110',
'SN_1005731000006100',
'SN_1005741000006100',
'SN_1005751000006100',
'SN_1005761000006100',
'SN_1005771000006110',
'SN_1005781000006110',
'SN_1005791000006110',
'SN_1005821000006100',
'SN_1005831000006100',
'SN_1005861000006110',
'SN_1005871000006100',
'SN_1005881000006100',
'SN_1005891000006100',
'SN_1005901000006100',
'SN_1005911000006100',
'SN_1005921000006110',
'SN_1005931000006110',
'SN_1005941000006100',
'SN_1005951000006100',
'SN_1005961000006100',
'SN_1005971000006100',
'SN_1005981000006110',
'SN_1005991000006110',
'SN_1006001000006110',
'SN_1006011000006100',
'SN_1006021000006100',
'SN_1006031000006100',
'SN_1006041000006110',
'SN_1006051000006110',
'SN_1006061000006110',
'SN_1006071000006100',
'SN_1006081000006100',
'SN_1006091000006100',
'SN_1006101000006110',
'SN_1006111000006110',
'SN_1006121000006100',
'SN_1006131000006100',
'SN_1006141000006110',
'SN_1006151000006100',
'SN_1006161000006110',
'SN_1006171000006100',
'SN_1006551000006100',
'SN_1006561000006100',
'SN_1006571000006100',
'SN_1006581000006110',
'SN_1006591000006110',
'SN_1006601000006100',
'SN_1006611000006100',
'SN_1006621000006110',
'SN_1006631000006110',
'SN_1006641000006100',
'SN_1006651000006100',
'SN_1006661000006100',
'SN_1006671000006110',
'SN_1006681000006110',
'SN_1006691000006100',
'SN_1006701000006100',
'SN_1006731000006100',
'SN_1006761000006110',
'SN_1006781000006100',
'SN_1006791000006100',
'SN_1006801000006100',
'SN_1006821000006110',
'SN_1006901000006110',
'SN_1007091000006100',
'SN_1007141000006100',
'SN_1007151000006110',
'SN_1007161000006110',
'SN_1007171000006100',
'SN_1007181000006100',
'SN_1007191000006100',
'SN_1007201000006100',
'SN_1007211000006100',
'SN_1007221000006110',
'SN_1007231000006110',
'SN_1007241000006100',
'SN_1007251000006100',
'SN_1007261000006100',
'SN_1007271000006110',
'SN_1007281000006110',
'SN_1007291000006100',
'SN_1007301000006110',
'SN_1007311000006110',
'SN_1007321000006100',
'SN_1007331000006100',
'SN_1007341000006110',
'SN_1007351000006100',
'SN_1007361000006110',
'SN_1007371000006100',
'SN_1007381000006100',
'SN_1007391000006100',
'SN_1007401000006100',
'SN_1007411000006100',
'SN_1007421000006110',
'SN_1007431000006100',
'SN_1007441000006100',
'SN_1007451000006100',
'SN_1007461000006100',
'SN_1007471000006110',
'SN_1007481000006110',
'SN_1007491000006110',
'SN_1007501000006100',
'SN_1007511000006100',
'SN_1007521000006110',
'SN_1007531000006110',
'SN_1007541000006100',
'SN_1007551000006100',
'SN_1007561000006100',
'SN_1007571000006110',
'SN_1007581000006100',
'SN_1007591000006110',
'SN_1007601000006100',
'SN_1007611000006100',
'SN_1007621000006100',
'SN_1007631000006110',
'SN_1007641000006100',
'SN_1007651000006100',
'SN_1007661000006100',
'SN_1007671000006110',
'SN_1007681000006110',
'SN_1573501000006100',
'SN_1573911000006110',
'SN_1573921000006100',
'SN_1574081000006100',
'SN_1574091000006100',
'SN_1574101000006110',
'SN_1574481000006110',
'SN_1574491000006110',
'SN_1574561000006100',
'SN_1574571000006100',
'SN_1600011000006100',
'SN_1600021000006110',
'SN_1600031000006110',
'SN_1600041000006100',
'SN_1600051000006100',
'SN_1600061000006100',
'SN_1600071000006100',
'SN_1600081000006110',
'SN_1600091000006110',
'SN_1600101000006100',
'SN_1600111000006100',
'SN_1600121000006100',
'SN_1600131000006110',
'SN_1600141000006100',
'SN_1600151000006100',
'SN_1600161000006100',
'SN_1600171000006110',
'SN_1600181000006110',
'SN_1600191000006110',
'SN_1600201000006100',
'SN_1600211000006110',
'SN_1600221000006100',
'SN_1600231000006100',
'SN_1600241000006110',
'SN_1600251000006110',
'SN_1600261000006110',
'SN_1600271000006100',
'SN_1600281000006100',
'SN_1600291000006100',
'SN_1600301000006100',
'SN_1600311000006100',
'SN_1600321000006110',
'SN_1600331000006100',
'SN_1600341000006100',
'SN_1600351000006100',
'SN_1600361000006100',
'SN_1600371000006110',
'SN_1618841000006110',
'SN_1618851000006110',
'SN_1618861000006100',
'SN_1618871000006100',
'SN_1619161000006100',
'SN_1619171000006100',
'SN_1619181000006100',
'SN_1619231000006110',
'SN_1619241000006100',
'SN_1619251000006100',
'SN_1619261000006100',
'SN_1619331000006100',
'SN_1619341000006110',
'SN_1619351000006110',
'SN_1619361000006110',
'SN_1619371000006100',
'SN_1619381000006100',
'SN_1619391000006100',
'SN_1619401000006100',
'SN_1619411000006100',
'SN_1619421000006110',
'SN_1619431000006110',
'SN_1619441000006100',
'SN_1619451000006100',
'SN_1619461000006100',
'SN_1619471000006110',
'SN_1619481000006110',
'SN_1619491000006100',
'SN_1619501000006100',
'SN_1619511000006100',
'SN_1619521000006110',
'SN_1619531000006100',
'SN_1619541000006100',
'SN_1619551000006100',
'SN_1619561000006100',
'SN_1619571000006110',
'SN_1619581000006110',
'SN_1619591000006110',
'SN_1619601000006100',
'SN_1619611000006100',
'SN_1619661000006100',
'SN_1619671000006110',
'SN_1619681000006100',
'SN_1619691000006110',
'SN_1619701000006110',
'SN_1619711000006110',
'SN_1619721000006100',
'SN_1619731000006100',
'SN_1619741000006100',
'SN_1619751000006110',
'SN_1619761000006110',
'SN_1619771000006100',
'SN_1619781000006100',
'SN_1619791000006100',
'SN_1619801000006100',
'SN_1619871000006110',
'SN_1619881000006110',
'SN_1619891000006110',
'SN_1619901000006110',
'SN_1619911000006110',
'SN_1619921000006100',
'SN_1619931000006100',
'SN_1619941000006110',
'SN_1619951000006100',
'SN_1619961000006110',
'SN_1619971000006100',
'SN_1620031000006100',
'SN_1620041000006110',
'SN_1620051000006110',
'SN_1620061000006100',
'SN_1620071000006100',
'SN_1620081000006100',
'SN_1620091000006100',
'SN_1620101000006110',
'SN_1620111000006110',
'SN_1620121000006100',
'SN_1620131000006100',
'SN_1620141000006100',
'SN_1620151000006110',
'SN_1620311000006110',
'SN_1664191000006100',
'SN_1664201000006100',
'SN_1664211000006100',
'SN_1675221000006100',
'SN_1675231000006100',
'SN_1675241000006110',
'SN_1675491000006100',
'SN_1707691000006100',
'SN_1707701000006100',
'SN_1707711000006110',
'SN_1745441000006100',
'SN_281944005',
'SN_281943004',
'SN_1764481000006100',
'SN_60562006',
'SN_1804331000006110',
'SN_1808681000006110',
'SN_1808701000006110',
'SN_1808721000006100',
'SN_1808911000006110',
'SN_1809521000006100',
'SN_1809641000006110',
'SN_1819861000006110',
'SN_1819871000006100',
'SN_284395004',
'SN_1819941000006100',
'SN_1969851000006100',
'SN_1969861000006100',
'SN_1969871000006110',
'SN_1969881000006110',
'SN_1969891000006110',
'SN_1969901000006110',
'SN_1969911000006110',
'SN_1969921000006100',
'SN_1990801000006100'
)

union

SELECT distinct 12, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_1002291000006110',
'SN_1002451000006100',
'SN_1002461000006100',
'SN_1002471000006100',
'SN_1002481000006110',
'SN_1002491000006110',
'SN_1002501000006100',
'SN_1002511000006100',
'SN_1002521000006110',
'SN_1002531000006110',
'SN_1002601000006100',
'SN_1002611000006100',
'SN_1002621000006110',
'SN_1002631000006100',
'SN_1002641000006100',
'SN_1002651000006100',
'SN_1002661000006100',
'SN_1002671000006110',
'SN_1002681000006110',
'SN_1002761000006100',
'SN_1002771000006100',
'SN_1002781000006100',
'SN_1002791000006100',
'SN_1002801000006100',
'SN_1002811000006100',
'SN_1002821000006110',
'SN_1002831000006110',
'SN_1002841000006100',
'SN_1002851000006100',
'SN_1002861000006100',
'SN_1002871000006110',
'SN_1002881000006100',
'SN_1002891000006110',
'SN_1002901000006110',
'SN_1002911000006100',
'SN_1002921000006100',
'SN_1002931000006100',
'SN_1002941000006110',
'SN_1002951000006110',
'SN_1002961000006110',
'SN_1002971000006100',
'SN_1002981000006100',
'SN_1002991000006100',
'SN_1003001000006100',
'SN_1003011000006100',
'SN_1003021000006110',
'SN_1003031000006110',
'SN_1003041000006100',
'SN_1003051000006100',
'SN_1003061000006100',
'SN_1003071000006100',
'SN_1003081000006110',
'SN_1003091000006110',
'SN_1003101000006100',
'SN_1003111000006100',
'SN_1003121000006100',
'SN_1003131000006110',
'SN_1003141000006100',
'SN_1003151000006100',
'SN_1003161000006100',
'SN_1003171000006110',
'SN_1003251000006110',
'SN_1003261000006110',
'SN_1003271000006100',
'SN_1003431000006100',
'SN_1003441000006110',
'SN_1003451000006100',
'SN_1003461000006110',
'SN_1003471000006100',
'SN_1003481000006100',
'SN_1003491000006100',
'SN_1003501000006110',
'SN_1003511000006100',
'SN_1003591000006100',
'SN_1003601000006110',
'SN_1003951000006100',
'SN_1004101000006100',
'SN_1004111000006100',
'SN_1004121000006110',
'SN_1004131000006100',
'SN_1004141000006100',
'SN_1004151000006100',
'SN_1004161000006100',
'SN_1004271000006100',
'SN_1004281000006100',
'SN_1004291000006100',
'SN_1004301000006100',
'SN_1004621000006100',
'SN_1004631000006100',
'SN_1004641000006110',
'SN_1004651000006110',
'SN_1004661000006110',
'SN_1004671000006100',
'SN_1004681000006100',
'SN_1004691000006100',
'SN_1004771000006110',
'SN_1004781000006110',
'SN_1004791000006100',
'SN_1004801000006110',
'SN_1004811000006110',
'SN_1004821000006100',
'SN_1004831000006100',
'SN_1004841000006110',
'SN_1004851000006100',
'SN_1004861000006110',
'SN_1004871000006100',
'SN_1004921000006100',
'SN_1004931000006110',
'SN_1004941000006100',
'SN_1004951000006100',
'SN_1004961000006100',
'SN_1004971000006110',
'SN_1004981000006110',
'SN_1004991000006110',
'SN_1005001000006100',
'SN_1005011000006100',
'SN_1005021000006100',
'SN_1005031000006110',
'SN_1005041000006100',
'SN_1005051000006100',
'SN_1005061000006100',
'SN_1005071000006110',
'SN_1005081000006110',
'SN_1005091000006110',
'SN_1005101000006100',
'SN_1005221000006100',
'SN_1005231000006100',
'SN_1005241000006110',
'SN_1005251000006100',
'SN_1005261000006110',
'SN_1005271000006100',
'SN_1005281000006100',
'SN_1005291000006100',
'SN_1005321000006110',
'SN_1005331000006110',
'SN_1005341000006100',
'SN_1005351000006100',
'SN_1005361000006100',
'SN_1005371000006110',
'SN_1005381000006110',
'SN_1005391000006100',
'SN_1005401000006110',
'SN_1005411000006100',
'SN_1005421000006100',
'SN_1005431000006100',
'SN_1005441000006110',
'SN_1005451000006110',
'SN_1005461000006110',
'SN_1005471000006100',
'SN_1005481000006100',
'SN_1005491000006100',
'SN_1005501000006110',
'SN_1005511000006110',
'SN_1005521000006100',
'SN_1005531000006100',
'SN_1005541000006100',
'SN_1005551000006110',
'SN_1005561000006110',
'SN_1005571000006100',
'SN_1005581000006100',
'SN_1005591000006100',
'SN_1005601000006110',
'SN_1005611000006110',
'SN_1005621000006100',
'SN_1005631000006100',
'SN_1005641000006110',
'SN_1005651000006110',
'SN_1005661000006100',
'SN_1005671000006100',
'SN_1005681000006100',
'SN_1005691000006100',
'SN_1005701000006100',
'SN_1005711000006100',
'SN_1005721000006110',
'SN_1005731000006100',
'SN_1005741000006100',
'SN_1005751000006100',
'SN_1005761000006100',
'SN_1005771000006110',
'SN_1005781000006110',
'SN_1005791000006110',
'SN_1005821000006100',
'SN_1005831000006100',
'SN_1005861000006110',
'SN_1005871000006100',
'SN_1005881000006100',
'SN_1005891000006100',
'SN_1005901000006100',
'SN_1005911000006100',
'SN_1005921000006110',
'SN_1005931000006110',
'SN_1005941000006100',
'SN_1005951000006100',
'SN_1005961000006100',
'SN_1005971000006100',
'SN_1005981000006110',
'SN_1005991000006110',
'SN_1006001000006110',
'SN_1006011000006100',
'SN_1006021000006100',
'SN_1006031000006100',
'SN_1006041000006110',
'SN_1006051000006110',
'SN_1006061000006110',
'SN_1006071000006100',
'SN_1006081000006100',
'SN_1006091000006100',
'SN_1006101000006110',
'SN_1006111000006110',
'SN_1006121000006100',
'SN_1006131000006100',
'SN_1006141000006110',
'SN_1006151000006100',
'SN_1006161000006110',
'SN_1006171000006100',
'SN_1006551000006100',
'SN_1006561000006100',
'SN_1006571000006100',
'SN_1006581000006110',
'SN_1006591000006110',
'SN_1006601000006100',
'SN_1006611000006100',
'SN_1006621000006110',
'SN_1006631000006110',
'SN_1006641000006100',
'SN_1006651000006100',
'SN_1006661000006100',
'SN_1006671000006110',
'SN_1006681000006110',
'SN_1006691000006100',
'SN_1006701000006100',
'SN_1006731000006100',
'SN_1006761000006110',
'SN_1006781000006100',
'SN_1006791000006100',
'SN_1006801000006100',
'SN_1006821000006110',
'SN_1006901000006110',
'SN_1007091000006100',
'SN_1007141000006100',
'SN_1007151000006110',
'SN_1007161000006110',
'SN_1007171000006100',
'SN_1007181000006100',
'SN_1007191000006100',
'SN_1007201000006100',
'SN_1007211000006100',
'SN_1007221000006110',
'SN_1007231000006110',
'SN_1007241000006100',
'SN_1007251000006100',
'SN_1007261000006100',
'SN_1007271000006110',
'SN_1007281000006110',
'SN_1007291000006100',
'SN_1007301000006110',
'SN_1007311000006110',
'SN_1007321000006100',
'SN_1007331000006100',
'SN_1007341000006110',
'SN_1007351000006100',
'SN_1007361000006110',
'SN_1007371000006100',
'SN_1007381000006100',
'SN_1007391000006100',
'SN_1007401000006100',
'SN_1007411000006100',
'SN_1007421000006110',
'SN_1007431000006100',
'SN_1007441000006100',
'SN_1007451000006100',
'SN_1007461000006100',
'SN_1007471000006110',
'SN_1007481000006110',
'SN_1007491000006110',
'SN_1007501000006100',
'SN_1007511000006100',
'SN_1007521000006110',
'SN_1007531000006110',
'SN_1007541000006100',
'SN_1007551000006100',
'SN_1007561000006100',
'SN_1007571000006110',
'SN_1007581000006100',
'SN_1007591000006110',
'SN_1007601000006100',
'SN_1007611000006100',
'SN_1007621000006100',
'SN_1007631000006110',
'SN_1007641000006100',
'SN_1007651000006100',
'SN_1007661000006100',
'SN_1007671000006110',
'SN_1007681000006110',
'SN_1573501000006100',
'SN_1573911000006110',
'SN_1573921000006100',
'SN_1574081000006100',
'SN_1574091000006100',
'SN_1574101000006110',
'SN_1574481000006110',
'SN_1574491000006110',
'SN_1574561000006100',
'SN_1574571000006100',
'SN_1600011000006100',
'SN_1600021000006110',
'SN_1600031000006110',
'SN_1600041000006100',
'SN_1600051000006100',
'SN_1600061000006100',
'SN_1600071000006100',
'SN_1600081000006110',
'SN_1600091000006110',
'SN_1600101000006100',
'SN_1600111000006100',
'SN_1600121000006100',
'SN_1600131000006110',
'SN_1600141000006100',
'SN_1600151000006100',
'SN_1600161000006100',
'SN_1600171000006110',
'SN_1600181000006110',
'SN_1600191000006110',
'SN_1600201000006100',
'SN_1600211000006110',
'SN_1600221000006100',
'SN_1600231000006100',
'SN_1600241000006110',
'SN_1600251000006110',
'SN_1600261000006110',
'SN_1600271000006100',
'SN_1600281000006100',
'SN_1600291000006100',
'SN_1600301000006100',
'SN_1600311000006100',
'SN_1600321000006110',
'SN_1600331000006100',
'SN_1600341000006100',
'SN_1600351000006100',
'SN_1600361000006100',
'SN_1600371000006110',
'SN_1618841000006110',
'SN_1618851000006110',
'SN_1618861000006100',
'SN_1618871000006100',
'SN_1619161000006100',
'SN_1619171000006100',
'SN_1619181000006100',
'SN_1619231000006110',
'SN_1619241000006100',
'SN_1619251000006100',
'SN_1619261000006100',
'SN_1619331000006100',
'SN_1619341000006110',
'SN_1619351000006110',
'SN_1619361000006110',
'SN_1619371000006100',
'SN_1619381000006100',
'SN_1619391000006100',
'SN_1619401000006100',
'SN_1619411000006100',
'SN_1619421000006110',
'SN_1619431000006110',
'SN_1619441000006100',
'SN_1619451000006100',
'SN_1619461000006100',
'SN_1619471000006110',
'SN_1619481000006110',
'SN_1619491000006100',
'SN_1619501000006100',
'SN_1619511000006100',
'SN_1619521000006110',
'SN_1619531000006100',
'SN_1619541000006100',
'SN_1619551000006100',
'SN_1619561000006100',
'SN_1619571000006110',
'SN_1619581000006110',
'SN_1619591000006110',
'SN_1619601000006100',
'SN_1619611000006100',
'SN_1619661000006100',
'SN_1619671000006110',
'SN_1619681000006100',
'SN_1619691000006110',
'SN_1619701000006110',
'SN_1619711000006110',
'SN_1619721000006100',
'SN_1619731000006100',
'SN_1619741000006100',
'SN_1619751000006110',
'SN_1619761000006110',
'SN_1619771000006100',
'SN_1619781000006100',
'SN_1619791000006100',
'SN_1619801000006100',
'SN_1619871000006110',
'SN_1619881000006110',
'SN_1619891000006110',
'SN_1619901000006110',
'SN_1619911000006110',
'SN_1619921000006100',
'SN_1619931000006100',
'SN_1619941000006110',
'SN_1619951000006100',
'SN_1619961000006110',
'SN_1619971000006100',
'SN_1620031000006100',
'SN_1620041000006110',
'SN_1620051000006110',
'SN_1620061000006100',
'SN_1620071000006100',
'SN_1620081000006100',
'SN_1620091000006100',
'SN_1620101000006110',
'SN_1620111000006110',
'SN_1620121000006100',
'SN_1620131000006100',
'SN_1620141000006100',
'SN_1620151000006110',
'SN_1620311000006110',
'SN_1664191000006100',
'SN_1664201000006100',
'SN_1664211000006100',
'SN_1675221000006100',
'SN_1675231000006100',
'SN_1675241000006110',
'SN_1675491000006100',
'SN_1707691000006100',
'SN_1707701000006100',
'SN_1707711000006110',
'SN_1745441000006100',
'SN_281944005',
'SN_281943004',
'SN_1764481000006100',
'SN_60562006',
'SN_1804331000006110',
'SN_1808681000006110',
'SN_1808701000006110',
'SN_1808721000006100',
'SN_1808911000006110',
'SN_1809521000006100',
'SN_1809641000006110',
'SN_1819861000006110',
'SN_1819871000006100',
'SN_284395004',
'SN_1819941000006100',
'SN_1969851000006100',
'SN_1969861000006100',
'SN_1969871000006110',
'SN_1969881000006110',
'SN_1969891000006110',
'SN_1969901000006110',
'SN_1969911000006110',
'SN_1969921000006100',
'SN_1990801000006100'
);