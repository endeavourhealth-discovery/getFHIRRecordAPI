
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 27, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_125681006',
'SN_78061006',
'SN_87915002',
'SN_20295000',
'SN_13184001',
'SN_160504008',
'SN_160504008',
'SN_160505009',
'SN_160506005',
'SN_276125006',
'SN_276126007',
'SN_276127003',
'SN_276128008',
'SN_278836005',
'SN_278837001',
'SN_14012001',
'SN_33553000',
'SN_38070000',
'SN_125681006',
'SN_286051000000109',
'SN_286081000000103',
'SN_286141000000107',
'SN_286171000000101',
'SN_1672431000006100',
'SN_105435005',
'SN_105434009',
'SN_13184001',
'SN_20295000',
'SN_78061006'
)

union

SELECT distinct 27, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_125681006',
'SN_78061006',
'SN_87915002',
'SN_20295000',
'SN_13184001',
'SN_160504008',
'SN_160504008',
'SN_160505009',
'SN_160506005',
'SN_276125006',
'SN_276126007',
'SN_276127003',
'SN_276128008',
'SN_278836005',
'SN_278837001',
'SN_14012001',
'SN_33553000',
'SN_38070000',
'SN_125681006',
'SN_286051000000109',
'SN_286081000000103',
'SN_286141000000107',
'SN_286171000000101',
'SN_1672431000006100',
'SN_105435005',
'SN_105434009',
'SN_13184001',
'SN_20295000',
'SN_78061006'
);