
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 20, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_940431000006107',
'SN_940441000006102',
'SN_940451000006100',
'SN_940461000006103',
'SN_940471000006105',
'SN_940481000006108',
'SN_940491000006106',
'SN_940501000006103',
'SN_940511000006100',
'SN_940521000006108',
'SN_940531000006106',
'SN_940541000006101',
'SN_940551000006104',
'SN_940561000006102',
'SN_940571000006109',
'SN_940581000006107',
'SN_940591000006105',
'SN_940601000006102',
'SN_940611000006104',
'SN_940621000006107',
'SN_940631000006105',
'SN_940641000006100',
'SN_940651000006103',
'SN_940661000006101',
'SN_940671000006108',
'SN_940681000006106',
'SN_940691000006109',
'SN_940701000006109',
'SN_940711000006107',
'SN_940721000006104',
'SN_940731000006101',
'SN_940741000006106',
'SN_940751000006108',
'SN_940761000006105',
'SN_940771000006103',
'SN_940781000006100',
'SN_940791000006102',
'SN_987521000006103',
'SN_987531000006100',
'SN_987541000006105',
'SN_987551000006107',
'SN_987561000006109',
'SN_987571000006102'
)

union

SELECT distinct 20, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_940431000006107',
'SN_940441000006102',
'SN_940451000006100',
'SN_940461000006103',
'SN_940471000006105',
'SN_940481000006108',
'SN_940491000006106',
'SN_940501000006103',
'SN_940511000006100',
'SN_940521000006108',
'SN_940531000006106',
'SN_940541000006101',
'SN_940551000006104',
'SN_940561000006102',
'SN_940571000006109',
'SN_940581000006107',
'SN_940591000006105',
'SN_940601000006102',
'SN_940611000006104',
'SN_940621000006107',
'SN_940631000006105',
'SN_940641000006100',
'SN_940651000006103',
'SN_940661000006101',
'SN_940671000006108',
'SN_940681000006106',
'SN_940691000006109',
'SN_940701000006109',
'SN_940711000006107',
'SN_940721000006104',
'SN_940731000006101',
'SN_940741000006106',
'SN_940751000006108',
'SN_940761000006105',
'SN_940771000006103',
'SN_940781000006100',
'SN_940791000006102',
'SN_987521000006103',
'SN_987531000006100',
'SN_987541000006105',
'SN_987551000006107',
'SN_987561000006109',
'SN_987571000006102'
);