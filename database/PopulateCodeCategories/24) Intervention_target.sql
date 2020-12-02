
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 24, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_958401000006100',
'SN_960841000006109',
'SN_960851000006106',
'SN_960861000006108',
'SN_960871000006101',
'SN_960881000006103',
'SN_960891000006100',
'SN_960901000006101',
'SN_960911000006103',
'SN_960921000006106',
'SN_960931000006109',
'SN_960941000006104',
'SN_960951000006102',
'SN_960961000006100',
'SN_960971000006107',
'SN_960981000006105',
'SN_960991000006108',
'SN_961001000006109',
'SN_961011000006107',
'SN_961021000006104',
'SN_961031000006101',
'SN_961041000006106',
'SN_961051000006108',
'SN_961061000006105',
'SN_961071000006103',
'SN_961081000006100',
'SN_961091000006102',
'SN_961101000006108',
'SN_961111000006106',
'SN_961121000006103',
'SN_961131000006100',
'SN_961141000006105',
'SN_961151000006107',
'SN_961161000006109',
'SN_961171000006102',
'SN_961181000006104',
'SN_961191000006101',
'SN_961201000006103',
'SN_961211000006100',
'SN_961221000006108',
'SN_961231000006106',
'SN_961241000006101',
'SN_961251000006104',
'SN_961261000006102',
'SN_961291000006105',
'SN_961301000006106',
'SN_961311000006109',
'SN_961321000006101',
'SN_961331000006103',
'SN_961341000006108',
'SN_961351000006105',
'SN_961361000006107',
'SN_961371000006100',
'SN_961381000006102',
'SN_961391000006104',
'SN_961401000006102',
'SN_961411000006104',
'SN_961421000006107',
'SN_961431000006105',
'SN_961441000006100',
'SN_961451000006103',
'SN_961461000006101',
'SN_961471000006108',
'SN_980761000006100',
'SN_980771000006107',
'SN_980781000006105',
'SN_980791000006108',
'SN_980801000006109',
'SN_980811000006107',
'SN_980821000006104',
'SN_980831000006101',
'SN_980841000006106',
'SN_980851000006108',
'SN_980861000006105',
'SN_980871000006103',
'SN_980881000006100',
'SN_980891000006102',
'SN_980901000006103',
'SN_980911000006100',
'SN_980921000006108'
)

union

SELECT distinct 24, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_958401000006100',
'SN_960841000006109',
'SN_960851000006106',
'SN_960861000006108',
'SN_960871000006101',
'SN_960881000006103',
'SN_960891000006100',
'SN_960901000006101',
'SN_960911000006103',
'SN_960921000006106',
'SN_960931000006109',
'SN_960941000006104',
'SN_960951000006102',
'SN_960961000006100',
'SN_960971000006107',
'SN_960981000006105',
'SN_960991000006108',
'SN_961001000006109',
'SN_961011000006107',
'SN_961021000006104',
'SN_961031000006101',
'SN_961041000006106',
'SN_961051000006108',
'SN_961061000006105',
'SN_961071000006103',
'SN_961081000006100',
'SN_961091000006102',
'SN_961101000006108',
'SN_961111000006106',
'SN_961121000006103',
'SN_961131000006100',
'SN_961141000006105',
'SN_961151000006107',
'SN_961161000006109',
'SN_961171000006102',
'SN_961181000006104',
'SN_961191000006101',
'SN_961201000006103',
'SN_961211000006100',
'SN_961221000006108',
'SN_961231000006106',
'SN_961241000006101',
'SN_961251000006104',
'SN_961261000006102',
'SN_961291000006105',
'SN_961301000006106',
'SN_961311000006109',
'SN_961321000006101',
'SN_961331000006103',
'SN_961341000006108',
'SN_961351000006105',
'SN_961361000006107',
'SN_961371000006100',
'SN_961381000006102',
'SN_961391000006104',
'SN_961401000006102',
'SN_961411000006104',
'SN_961421000006107',
'SN_961431000006105',
'SN_961441000006100',
'SN_961451000006103',
'SN_961461000006101',
'SN_961471000006108',
'SN_980761000006100',
'SN_980771000006107',
'SN_980781000006105',
'SN_980791000006108',
'SN_980801000006109',
'SN_980811000006107',
'SN_980821000006104',
'SN_980831000006101',
'SN_980841000006106',
'SN_980851000006108',
'SN_980861000006105',
'SN_980871000006103',
'SN_980881000006100',
'SN_980891000006102',
'SN_980901000006103',
'SN_980911000006100',
'SN_980921000006108'
);