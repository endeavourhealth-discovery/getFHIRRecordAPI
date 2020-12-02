
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 14, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_915801000006101',
'SN_915811000006103',
'SN_915821000006106',
'SN_915831000006109',
'SN_915841000006104',
'SN_915851000006102',
'SN_915861000006100',
'SN_915871000006107',
'SN_915881000006105',
'SN_915891000006108',
'SN_915901000006107'
)

union

SELECT distinct 14, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_915801000006101',
'SN_915811000006103',
'SN_915821000006106',
'SN_915831000006109',
'SN_915841000006104',
'SN_915851000006102',
'SN_915861000006100',
'SN_915871000006107',
'SN_915881000006105',
'SN_915891000006108',
'SN_915901000006107'
);
