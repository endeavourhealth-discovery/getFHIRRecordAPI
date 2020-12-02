
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 41, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_183666007',
'SN_183667003',
'SN_841471000006109',
'SN_841481000006107',
'SN_841491000006105',
'SN_841501000006102',
'SN_841511000006104',
'SN_841521000006107',
'SN_841531000006105',
'SN_841541000006100',
'SN_841551000006103',
'SN_841561000006101',
'SN_916061000006103',
'SN_513181000000101',
'SN_1851681000006110',
'SN_1851691000006100',
'SN_1879421000006110'
)

union

SELECT distinct 41, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_183666007',
'SN_183667003',
'SN_841471000006109',
'SN_841481000006107',
'SN_841491000006105',
'SN_841501000006102',
'SN_841511000006104',
'SN_841521000006107',
'SN_841531000006105',
'SN_841541000006100',
'SN_841551000006103',
'SN_841561000006101',
'SN_916061000006103',
'SN_513181000000101',
'SN_1851681000006110',
'SN_1851691000006100',
'SN_1879421000006110'
);