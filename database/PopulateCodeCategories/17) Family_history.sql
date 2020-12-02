
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 17, s.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Intermediary (just for info)
JOIN concept p2 ON p2.id IN ('SN_116680003', 'is_equivalent_to')						-- "Is A" and "Equivalent" relationships (self and legacy maps)
JOIN concept_property_object cpo ON cpo.value = t.source AND cpo.property = p2.dbid		-- All related
JOIN concept s ON s.dbid = cpo.dbid														-- Concepts
WHERE c.id in (
'SN_133939006',
'SN_134439009',
'SN_160250007',
'SN_160265008',
'SN_160266009',
'SN_160266009',
'SN_160267000',
'SN_160268005',
'SN_160269002',
'SN_160270001',
'SN_160271002',
'SN_160271002',
'SN_160273004',
'SN_160274005',
'SN_160266009',
'SN_160279000',
'SN_160280002',
'SN_160281003',
'SN_160282005',
'SN_160279000',
'SN_160288009',
'SN_160290005',
'SN_160291009',
'SN_160292002',
'SN_275937001',
'SN_439220002',
'SN_430677003',
'SN_160297008',
'SN_160298003',
'SN_160299006',
'SN_266883004',
'SN_160301004',
'SN_160302006',
'SN_160303001',
'SN_160301004',
'SN_160305008',
'SN_160306009',
'SN_160308005',
'SN_160309002',
'SN_160309002',
'SN_160310007',
'SN_160311006',
'SN_160312004',
'SN_160313009',
'SN_443454007',
'SN_160305008',
'SN_160316001',
'SN_160318000',
'SN_160319008',
'SN_160320002',
'SN_160321003',
'SN_160316001',
'SN_160325007',
'SN_160327004',
'SN_160327004',
'SN_160327004',
'SN_160328009',
'SN_160329001',
'SN_160331005',
'SN_160332003',
'SN_160333008',
'SN_537231000000106',
'SN_160336000',
'SN_160337009',
'SN_160337009',
'SN_160338004',
'SN_160339007',
'SN_160340009',
'SN_160341008',
'SN_160342001',
'SN_160343006',
'SN_160344000',
'SN_160346003',
'SN_160347007',
'SN_160348002',
'SN_160350005',
'SN_160346003',
'SN_160352002',
'SN_160353007',
'SN_160354001',
'SN_160352002',
'SN_160357008',
'SN_160362009',
'SN_160363004',
'SN_160363004',
'SN_160364005',
'SN_266894000',
'SN_160377001',
'SN_160378006',
'SN_160379003',
'SN_266898002',
'SN_160381001',
'SN_160386006',
'SN_160381001',
'SN_160389004',
'SN_160390008',
'SN_160391007',
'SN_160392000',
'SN_160393005',
'SN_160393005',
'SN_160393005',
'SN_160394004',
'SN_160396002',
'SN_160397006',
'SN_160398001',
'SN_160400002',
'SN_160401003',
'SN_160402005',
'SN_160403000',
'SN_160400002',
'SN_160406008',
'SN_160407004',
'SN_160408009',
'SN_160409001',
'SN_160409001',
'SN_160410006',
'SN_160406008',
'SN_160413008',
'SN_160414002',
'SN_443720007',
'SN_266907002',
'SN_160417009',
'SN_160418004',
'SN_160419007',
'SN_160421002',
'SN_160422009',
'SN_160423004',
'SN_160424005',
'SN_160425006',
'SN_160417009',
'SN_160427003',
'SN_160430005',
'SN_160431009',
'SN_160427003',
'SN_160433007',
'SN_160436004',
'SN_160437008',
'SN_160433007',
'SN_160439006',
'SN_160442000',
'SN_160439006',
'SN_160444004',
'SN_160447006',
'SN_160444004',
'SN_160449009',
'SN_160452001',
'SN_160449009',
'SN_160454000',
'SN_160457007',
'SN_160454000',
'SN_57177007',
'SN_160460000',
'SN_160461001',
'SN_160462008',
'SN_160463003',
'SN_160464009',
'SN_160465005',
'SN_160466006',
'SN_160467002',
'SN_57177007',
'SN_160469004',
'SN_160471004',
'SN_160472006',
'SN_160473001',
'SN_160474007',
'SN_160475008',
'SN_160947001',
'SN_160948006',
'SN_160948006',
'SN_160949003',
'SN_160953001',
'SN_248541003',
'SN_105452009',
'SN_266882009',
'SN_266883004',
'SN_266883004',
'SN_266885006',
'SN_266886007',
'SN_266887003',
'SN_266887003',
'SN_266888008',
'SN_266889000',
'SN_266891008',
'SN_266891008',
'SN_266893006',
'SN_266894000',
'SN_266894000',
'SN_266895004',
'SN_266897007',
'SN_266898002',
'SN_266899005',
'SN_266900000',
'SN_266900000',
'SN_266901001',
'SN_266902008',
'SN_266903003',
'SN_266904009',
'SN_266904009',
'SN_266906006',
'SN_266907002',
'SN_266907002',
'SN_266908007',
'SN_57177007',
'SN_266966009',
'SN_266966009',
'SN_266967000',
'SN_275101005',
'SN_266969002',
'SN_266970001',
'SN_266971002',
'SN_266966009',
'SN_297242006',
'SN_763598005',
'SN_275104002',
'SN_275101005',
'SN_275102003',
'SN_275104002',
'SN_275106000',
'SN_275108004',
'SN_275109007',
'SN_275110002',
'SN_275111003',
'SN_275113000',
'SN_275114006',
'SN_275115007',
'SN_275117004',
'SN_275118009',
'SN_275119001',
'SN_275120007',
'SN_275121006',
'SN_275124003',
'SN_275125002',
'SN_275127005',
'SN_275128000',
'SN_275129008',
'SN_275130003',
'SN_275131004',
'SN_275132006',
'SN_275133001',
'SN_275134007',
'SN_275136009',
'SN_275142008',
'SN_275144009',
'SN_275937001',
'SN_275937001',
'SN_275938006',
'SN_275939003',
'SN_275940001',
'SN_281020005',
'SN_444333006',
'SN_281022002',
'SN_444094009',
'SN_444180005',
'SN_281026004',
'SN_289916006',
'SN_289916006',
'SN_297242006',
'SN_297243001',
'SN_300933005',
'SN_160314003',
'SN_310247005',
'SN_310251007',
'SN_430329007',
'SN_720411002',
'SN_720741001',
'SN_720428004',
'SN_429746005',
'SN_429746005',
'SN_160327004',
'SN_313342001',
'SN_313376005',
'SN_315619001',
'SN_315620007',
'SN_315621006',
'SN_315622004',
'SN_315623009',
'SN_315625002',
'SN_315626001',
'SN_315627005',
'SN_275937001',
'SN_429740004',
'SN_430954001',
'SN_428951000000102',
'SN_160292002',
'SN_275937001',
'SN_275937001',
'SN_266970001',
'SN_160324006',
'SN_275104002',
'SN_297239000',
'SN_266894000',
'SN_160377001',
'SN_151181000119106',
'SN_275134007',
'SN_266907002',
'SN_410061000000105',
'SN_281666001',
'SN_160303001',
'SN_410911000000107',
'SN_266888008',
'SN_160316001',
'SN_763598005',
'SN_160381001',
'SN_289916006',
'SN_266904009',
'SN_160279000',
'SN_416471007',
'SN_416471007',
'SN_416471007',
'SN_266893006',
'SN_160346003',
'SN_439750006',
'SN_480541000000100',
'SN_160406008',
'SN_160417009',
'SN_160469004',
'SN_160475008',
'SN_463831000000102',
'SN_266898002',
'SN_416471007',
'SN_416471007',
'SN_160324006',
'SN_266890009',
'SN_443877004',
'SN_160324006',
'SN_160270001',
'SN_160273004',
'SN_160274005',
'SN_429011007',
'SN_275110002',
'SN_428269004',
'SN_275937001',
'SN_275104002',
'SN_160341008',
'SN_160303001',
'SN_160305008',
'SN_160301004',
'SN_275118009',
'SN_266893006',
'SN_439750006',
'SN_160353007',
'SN_390794009',
'SN_390915000',
'SN_390985008',
'SN_391096007',
'SN_391097003',
'SN_391098008',
'SN_394877006',
'SN_394990003',
'SN_395089003',
'SN_395125009',
'SN_160266009',
'SN_275120007',
'SN_401052005',
'SN_401065001',
'SN_401066000',
'SN_401067009',
'SN_401119001',
'SN_401122004',
'SN_407559004',
'SN_408552005',
'SN_408553000',
'SN_408575003',
'SN_412749001',
'SN_412750001',
'SN_412783008',
'SN_412784002',
'SN_275102003',
'SN_275104002',
'SN_297241004',
'SN_415036007',
'SN_414205003',
'SN_415289003',
'SN_416072008',
'SN_416519002',
'SN_416855002',
'SN_417072001',
'SN_297239000',
'SN_297243001',
'SN_427858005',
'SN_429953005',
'SN_430710003',
'SN_431912005',
'SN_430543001',
'SN_430678008',
'SN_430679000',
'SN_160949003',
'SN_266890009',
'SN_275122004',
'SN_275122004',
'SN_275932007',
'SN_266896003',
'SN_275123009',
'SN_275123009',
'SN_275911004',
'SN_281022002',
'SN_198301000000109',
'SN_198381000000104',
'SN_199071000000106',
'SN_199731000000107',
'SN_440661000000106',
'SN_440661000000106',
'SN_160303001',
'SN_160379003',
'SN_438998000',
'SN_160336000',
'SN_297242006',
'SN_429006005',
'SN_427501000000109',
'SN_266897007',
'SN_275937001',
'SN_451531000000107',
'SN_720428004',
'SN_720741001',
'SN_720411002',
'SN_287351000000105',
'SN_160949003',
'SN_293161000000103',
'SN_160252004',
'SN_430778004',
'SN_335271000000100',
'SN_439154009',
'SN_57177007',
'SN_439213009',
'SN_439138006',
'SN_313102001',
'SN_429006005',
'SN_275109007',
'SN_429740004',
'SN_160446002',
'SN_160445003',
'SN_160364005',
'SN_160456003',
'SN_160455004',
'SN_57177007',
'SN_160435000',
'SN_160434001',
'SN_429006005',
'SN_266898002',
'SN_160331005',
'SN_160324006',
'SN_160429000',
'SN_160428008',
'SN_160472006',
'SN_160441007',
'SN_160440008',
'SN_160451008',
'SN_160450009',
'SN_857491000006101',
'SN_857501000006109',
'SN_857511000006107',
'SN_857521000006104',
'SN_857531000006101',
'SN_857541000006106',
'SN_857551000006108',
'SN_857561000006105',
'SN_857581000006100',
'SN_958011000006105',
'SN_958021000006102',
'SN_958031000006104',
'SN_958041000006109',
'SN_958051000006106',
'SN_958061000006108',
'SN_987341000006101',
'SN_494251000000109',
'SN_494281000000103',
'SN_699057005',
'SN_160947001',
'SN_704008007',
'SN_266969002',
'SN_1669601000006100',
'SN_1669611000006100',
'SN_758811000000100',
'SN_758821000000106',
'SN_439724007',
'SN_438825005',
'SN_759351000000104',
'SN_763241000000104',
'SN_1707331000006100',
'SN_1745931000006100',
'SN_1747871000006100',
'SN_1747881000006100',
'SN_430679000',
'SN_1807531000006100',
'SN_821691000000104',
'SN_821691000000104',
'SN_758841000000104',
'SN_1820361000006100',
'SN_1820381000006110',
'SN_1823711000006110',
'SN_1825131000006100',
'SN_429970002',
'SN_430090006',
'SN_430283008',
'SN_1842151000006100',
'SN_1849601000006100',
'SN_1858951000006100',
'SN_1860711000006110',
'SN_1860781000006100',
'SN_430090006',
'SN_429970002',
'SN_297247000',
'SN_429011007',
'SN_429011007',
'SN_430542006',
'SN_430542006',
'SN_433450000',
'SN_430329007',
'SN_1894961000006100',
'SN_902961000000107',
'SN_71271000119107',
'SN_1930231000006110',
'SN_1992661000006110',
'SN_809201000000102',
'SN_858191000000103',
'SN_858671000000107',
'SN_876461000000101',
'SN_700193001',
'SN_877691000000107',
'SN_700191004',
'SN_700190003',
'SN_878401000000104',
'SN_878421000000108',
'SN_878441000000101',
'SN_878461000000100',
'SN_700192006',
'SN_919701000000100',
'SN_919701000000100',
'SN_932441000000109',
'SN_959511000000100',
'SN_966191000000101',
'SN_977651000000105',
'SN_720436008',
'SN_1035381000000110',
'SN_720407008',
'SN_1038261000000100',
'SN_57177007',
'SN_57177007',
'SN_134439009',
'SN_160250007',
'SN_160252004',
'SN_160266009',
'SN_160267000',
'SN_160267000',
'SN_160268005',
'SN_160268005',
'SN_160269002',
'SN_160270001',
'SN_160270001',
'SN_160271002',
'SN_160271002',
'SN_160271002',
'SN_160273004',
'SN_160273004',
'SN_160274005',
'SN_160279000',
'SN_160280002',
'SN_160281003',
'SN_160282005',
'SN_160282005',
'SN_160282005',
'SN_160288009',
'SN_160288009',
'SN_160288009',
'SN_160290005',
'SN_160290005',
'SN_160290005',
'SN_160291009',
'SN_160291009',
'SN_160291009',
'SN_160292002',
'SN_160292002',
'SN_160292002',
'SN_160292002',
'SN_160292002',
'SN_160297008',
'SN_160298003',
'SN_160299006',
'SN_160301004',
'SN_160301004',
'SN_160302006',
'SN_160302006',
'SN_160305008',
'SN_160305008',
'SN_160306009',
'SN_160308005',
'SN_160309002',
'SN_160309002',
'SN_160310007',
'SN_160311006',
'SN_160312004',
'SN_160313009',
'SN_160313009',
'SN_160314003',
'SN_160314003',
'SN_160314003',
'SN_160316001',
'SN_160316001',
'SN_160318000',
'SN_160318000',
'SN_160319008',
'SN_160319008',
'SN_160319008',
'SN_160319008',
'SN_160319008',
'SN_160320002',
'SN_160320002',
'SN_160320002',
'SN_160320002',
'SN_160320002',
'SN_160321003',
'SN_160321003',
'SN_160325007',
'SN_160325007',
'SN_160327004',
'SN_160327004',
'SN_160328009',
'SN_160328009',
'SN_160329001',
'SN_160329001',
'SN_160331005',
'SN_160332003',
'SN_160332003',
'SN_160333008',
'SN_160333008',
'SN_160336000',
'SN_160336000',
'SN_160336000',
'SN_160337009',
'SN_160337009',
'SN_160338004',
'SN_160339007',
'SN_160340009',
'SN_160340009',
'SN_160341008',
'SN_160341008',
'SN_160342001',
'SN_160342001',
'SN_160343006',
'SN_160343006',
'SN_160344000',
'SN_160344000',
'SN_160346003',
'SN_160346003',
'SN_160346003',
'SN_160347007',
'SN_160347007',
'SN_160348002',
'SN_160348002',
'SN_160350005',
'SN_160350005',
'SN_160350005',
'SN_160352002',
'SN_160352002',
'SN_160353007',
'SN_160353007',
'SN_160354001',
'SN_160354001',
'SN_160357008',
'SN_160357008',
'SN_160362009',
'SN_160362009',
'SN_160363004',
'SN_160364005',
'SN_160364005',
'SN_160377001',
'SN_160378006',
'SN_160379003',
'SN_160381001',
'SN_160381001',
'SN_160386006',
'SN_160386006',
'SN_160386006',
'SN_160389004',
'SN_160389004',
'SN_160390008',
'SN_160390008',
'SN_160391007',
'SN_160391007',
'SN_160392000',
'SN_160392000',
'SN_160393005',
'SN_160393005',
'SN_160394004',
'SN_160396002',
'SN_160397006',
'SN_160398001',
'SN_160400002',
'SN_160400002',
'SN_160401003',
'SN_160402005',
'SN_160403000',
'SN_160406008',
'SN_160407004',
'SN_160408009',
'SN_160409001',
'SN_160410006',
'SN_160413008',
'SN_160414002',
'SN_160417009',
'SN_160418004',
'SN_160418004',
'SN_160419007',
'SN_160419007',
'SN_160419007',
'SN_160421002',
'SN_160421002',
'SN_160422009',
'SN_160423004',
'SN_160423004',
'SN_160424005',
'SN_160424005',
'SN_160424005',
'SN_160425006',
'SN_160427003',
'SN_160427003',
'SN_160428008',
'SN_160429000',
'SN_160430005',
'SN_160431009',
'SN_160433007',
'SN_160433007',
'SN_160433007',
'SN_160434001',
'SN_160435000',
'SN_160436004',
'SN_160437008',
'SN_160439006',
'SN_160439006',
'SN_160440008',
'SN_160441007',
'SN_160442000',
'SN_160444004',
'SN_160444004',
'SN_160444004',
'SN_160445003',
'SN_160446002',
'SN_160447006',
'SN_160449009',
'SN_160449009',
'SN_160450009',
'SN_160450009',
'SN_160451008',
'SN_160451008',
'SN_160452001',
'SN_160454000',
'SN_160454000',
'SN_160455004',
'SN_160455004',
'SN_160456003',
'SN_160457007',
'SN_160460000',
'SN_160460000',
'SN_160461001',
'SN_160461001',
'SN_160462008',
'SN_160462008',
'SN_160463003',
'SN_160463003',
'SN_160464009',
'SN_160464009',
'SN_160465005',
'SN_160465005',
'SN_160469004',
'SN_160469004',
'SN_160471004',
'SN_160471004',
'SN_160471004',
'SN_160472006',
'SN_160472006',
'SN_160473001',
'SN_160474007',
'SN_160475008',
'SN_160953001',
'SN_266882009',
'SN_266882009',
'SN_266882009',
'SN_266882009',
'SN_266882009',
'SN_266885006',
'SN_266885006',
'SN_266886007',
'SN_266886007',
'SN_266886007',
'SN_266887003',
'SN_266888008',
'SN_266888008',
'SN_266889000',
'SN_266889000',
'SN_266889000',
'SN_266889000',
'SN_266889000',
'SN_266890009',
'SN_266891008',
'SN_266891008',
'SN_266893006',
'SN_266893006',
'SN_266894000',
'SN_266894000',
'SN_266895004',
'SN_266895004',
'SN_266895004',
'SN_266895004',
'SN_266896003',
'SN_266896003',
'SN_266896003',
'SN_266897007',
'SN_266898002',
'SN_266898002',
'SN_266899005',
'SN_266899005',
'SN_266900000',
'SN_266900000',
'SN_266901001',
'SN_266902008',
'SN_266902008',
'SN_266903003',
'SN_266903003',
'SN_266904009',
'SN_266906006',
'SN_266907002',
'SN_266907002',
'SN_266907002',
'SN_266908007',
'SN_266908007',
'SN_266967000',
'SN_275101005',
'SN_275102003',
'SN_275104002',
'SN_275106000',
'SN_275108004',
'SN_275109007',
'SN_275109007',
'SN_275110002',
'SN_275110002',
'SN_275111003',
'SN_275113000',
'SN_275117004',
'SN_275118009',
'SN_275119001',
'SN_275120007',
'SN_275120007',
'SN_275120007',
'SN_275120007',
'SN_275121006',
'SN_275122004',
'SN_275123009',
'SN_275124003',
'SN_275125002',
'SN_275127005',
'SN_275128000',
'SN_275129008',
'SN_275129008',
'SN_275130003',
'SN_275131004',
'SN_275132006',
'SN_275132006',
'SN_275133001',
'SN_275136009',
'SN_275911004',
'SN_275932007',
'SN_275938006',
'SN_275938006',
'SN_275939003',
'SN_275939003',
'SN_275939003',
'SN_275940001',
'SN_281020005',
'SN_281022002',
'SN_281022002',
'SN_289916006',
'SN_297239000',
'SN_297241004',
'SN_297241004',
'SN_297242006',
'SN_297242006',
'SN_297243001',
'SN_297243001',
'SN_300933005',
'SN_310247005',
'SN_310251007',
'SN_313342001',
'SN_313376005',
'SN_315619001',
'SN_315620007',
'SN_315621006',
'SN_315622004',
'SN_315623009',
'SN_315625002',
'SN_315626001',
'SN_315627005',
'SN_390794009',
'SN_390915000',
'SN_391096007',
'SN_391097003',
'SN_391098008',
'SN_394877006',
'SN_394877006',
'SN_394877006',
'SN_394990003',
'SN_395089003',
'SN_395089003',
'SN_395125009',
'SN_401052005',
'SN_401052005',
'SN_401052005',
'SN_401065001',
'SN_401065001',
'SN_401066000',
'SN_401067009',
'SN_401067009',
'SN_401067009',
'SN_401119001',
'SN_401119001',
'SN_401119001',
'SN_401122004',
'SN_401122004',
'SN_401122004',
'SN_408575003',
'SN_412749001',
'SN_412783008',
'SN_412784002',
'SN_416072008',
'SN_416471007',
'SN_416519002',
'SN_416855002',
'SN_417072001',
'SN_417072001',
'SN_417072001',
'SN_417072001',
'SN_417072001',
'SN_428269004',
'SN_428269004',
'SN_429011007',
'SN_430329007',
'SN_430542006',
'SN_430543001',
'SN_430677003',
'SN_430677003',
'SN_430954001',
'SN_433450000',
'SN_438825005',
'SN_438998000',
'SN_438998000',
'SN_439213009',
'SN_439220002',
'SN_439724007',
'SN_439750006',
'SN_443454007',
'SN_443720007',
'SN_444333006',
'SN_699057005',
'SN_720436008',
'SN_134591000119102',
'SN_198301000000109',
'SN_198381000000104',
'SN_335271000000100',
'SN_537231000000106',
'SN_160436004',
'SN_160452001',
'SN_266888008',
'SN_281666001',
'SN_430329007',
'SN_297242006',
'SN_160292002',
'SN_427501000000109',
'SN_427501000000109',
'SN_427501000000109',
'SN_427501000000109',
'SN_428951000000102',
'SN_297247000',
'SN_160314003',
'SN_429011007',
'SN_763598005',
'SN_429740004',
'SN_440661000000106',
'SN_440661000000106',
'SN_160252004',
'SN_160250007',
'SN_266885006',
'SN_275938006',
'SN_275132006',
'SN_160340009',
'SN_401066000',
'SN_429740004',
'SN_160466006',
'SN_160467002',
'SN_480541000000100',
'SN_451531000000107',
'SN_463831000000102',
'SN_410061000000105',
'SN_410911000000107',
'SN_160466006',
'SN_160467002',
'SN_410911000000107',
'SN_410911000000107',
'SN_297242006'
)

union

SELECT distinct 17, i.dbid
FROM concept c																			-- Source SNOMED concept
JOIN concept p ON p.id = 'SN_116680003'													-- "Is A" relationship (child of code)
JOIN concept_tct t ON t.target = c.dbid AND t.property = p.dbid							-- All children, grand children, etc
JOIN concept i ON i.dbid = t.source														-- Concepts
WHERE c.id IN (
'SN_133939006',
'SN_134439009',
'SN_160250007',
'SN_160265008',
'SN_160266009',
'SN_160266009',
'SN_160267000',
'SN_160268005',
'SN_160269002',
'SN_160270001',
'SN_160271002',
'SN_160271002',
'SN_160273004',
'SN_160274005',
'SN_160266009',
'SN_160279000',
'SN_160280002',
'SN_160281003',
'SN_160282005',
'SN_160279000',
'SN_160288009',
'SN_160290005',
'SN_160291009',
'SN_160292002',
'SN_275937001',
'SN_439220002',
'SN_430677003',
'SN_160297008',
'SN_160298003',
'SN_160299006',
'SN_266883004',
'SN_160301004',
'SN_160302006',
'SN_160303001',
'SN_160301004',
'SN_160305008',
'SN_160306009',
'SN_160308005',
'SN_160309002',
'SN_160309002',
'SN_160310007',
'SN_160311006',
'SN_160312004',
'SN_160313009',
'SN_443454007',
'SN_160305008',
'SN_160316001',
'SN_160318000',
'SN_160319008',
'SN_160320002',
'SN_160321003',
'SN_160316001',
'SN_160325007',
'SN_160327004',
'SN_160327004',
'SN_160327004',
'SN_160328009',
'SN_160329001',
'SN_160331005',
'SN_160332003',
'SN_160333008',
'SN_537231000000106',
'SN_160336000',
'SN_160337009',
'SN_160337009',
'SN_160338004',
'SN_160339007',
'SN_160340009',
'SN_160341008',
'SN_160342001',
'SN_160343006',
'SN_160344000',
'SN_160346003',
'SN_160347007',
'SN_160348002',
'SN_160350005',
'SN_160346003',
'SN_160352002',
'SN_160353007',
'SN_160354001',
'SN_160352002',
'SN_160357008',
'SN_160362009',
'SN_160363004',
'SN_160363004',
'SN_160364005',
'SN_266894000',
'SN_160377001',
'SN_160378006',
'SN_160379003',
'SN_266898002',
'SN_160381001',
'SN_160386006',
'SN_160381001',
'SN_160389004',
'SN_160390008',
'SN_160391007',
'SN_160392000',
'SN_160393005',
'SN_160393005',
'SN_160393005',
'SN_160394004',
'SN_160396002',
'SN_160397006',
'SN_160398001',
'SN_160400002',
'SN_160401003',
'SN_160402005',
'SN_160403000',
'SN_160400002',
'SN_160406008',
'SN_160407004',
'SN_160408009',
'SN_160409001',
'SN_160409001',
'SN_160410006',
'SN_160406008',
'SN_160413008',
'SN_160414002',
'SN_443720007',
'SN_266907002',
'SN_160417009',
'SN_160418004',
'SN_160419007',
'SN_160421002',
'SN_160422009',
'SN_160423004',
'SN_160424005',
'SN_160425006',
'SN_160417009',
'SN_160427003',
'SN_160430005',
'SN_160431009',
'SN_160427003',
'SN_160433007',
'SN_160436004',
'SN_160437008',
'SN_160433007',
'SN_160439006',
'SN_160442000',
'SN_160439006',
'SN_160444004',
'SN_160447006',
'SN_160444004',
'SN_160449009',
'SN_160452001',
'SN_160449009',
'SN_160454000',
'SN_160457007',
'SN_160454000',
'SN_57177007',
'SN_160460000',
'SN_160461001',
'SN_160462008',
'SN_160463003',
'SN_160464009',
'SN_160465005',
'SN_160466006',
'SN_160467002',
'SN_57177007',
'SN_160469004',
'SN_160471004',
'SN_160472006',
'SN_160473001',
'SN_160474007',
'SN_160475008',
'SN_160947001',
'SN_160948006',
'SN_160948006',
'SN_160949003',
'SN_160953001',
'SN_248541003',
'SN_105452009',
'SN_266882009',
'SN_266883004',
'SN_266883004',
'SN_266885006',
'SN_266886007',
'SN_266887003',
'SN_266887003',
'SN_266888008',
'SN_266889000',
'SN_266891008',
'SN_266891008',
'SN_266893006',
'SN_266894000',
'SN_266894000',
'SN_266895004',
'SN_266897007',
'SN_266898002',
'SN_266899005',
'SN_266900000',
'SN_266900000',
'SN_266901001',
'SN_266902008',
'SN_266903003',
'SN_266904009',
'SN_266904009',
'SN_266906006',
'SN_266907002',
'SN_266907002',
'SN_266908007',
'SN_57177007',
'SN_266966009',
'SN_266966009',
'SN_266967000',
'SN_275101005',
'SN_266969002',
'SN_266970001',
'SN_266971002',
'SN_266966009',
'SN_297242006',
'SN_763598005',
'SN_275104002',
'SN_275101005',
'SN_275102003',
'SN_275104002',
'SN_275106000',
'SN_275108004',
'SN_275109007',
'SN_275110002',
'SN_275111003',
'SN_275113000',
'SN_275114006',
'SN_275115007',
'SN_275117004',
'SN_275118009',
'SN_275119001',
'SN_275120007',
'SN_275121006',
'SN_275124003',
'SN_275125002',
'SN_275127005',
'SN_275128000',
'SN_275129008',
'SN_275130003',
'SN_275131004',
'SN_275132006',
'SN_275133001',
'SN_275134007',
'SN_275136009',
'SN_275142008',
'SN_275144009',
'SN_275937001',
'SN_275937001',
'SN_275938006',
'SN_275939003',
'SN_275940001',
'SN_281020005',
'SN_444333006',
'SN_281022002',
'SN_444094009',
'SN_444180005',
'SN_281026004',
'SN_289916006',
'SN_289916006',
'SN_297242006',
'SN_297243001',
'SN_300933005',
'SN_160314003',
'SN_310247005',
'SN_310251007',
'SN_430329007',
'SN_720411002',
'SN_720741001',
'SN_720428004',
'SN_429746005',
'SN_429746005',
'SN_160327004',
'SN_313342001',
'SN_313376005',
'SN_315619001',
'SN_315620007',
'SN_315621006',
'SN_315622004',
'SN_315623009',
'SN_315625002',
'SN_315626001',
'SN_315627005',
'SN_275937001',
'SN_429740004',
'SN_430954001',
'SN_428951000000102',
'SN_160292002',
'SN_275937001',
'SN_275937001',
'SN_266970001',
'SN_160324006',
'SN_275104002',
'SN_297239000',
'SN_266894000',
'SN_160377001',
'SN_151181000119106',
'SN_275134007',
'SN_266907002',
'SN_410061000000105',
'SN_281666001',
'SN_160303001',
'SN_410911000000107',
'SN_266888008',
'SN_160316001',
'SN_763598005',
'SN_160381001',
'SN_289916006',
'SN_266904009',
'SN_160279000',
'SN_416471007',
'SN_416471007',
'SN_416471007',
'SN_266893006',
'SN_160346003',
'SN_439750006',
'SN_480541000000100',
'SN_160406008',
'SN_160417009',
'SN_160469004',
'SN_160475008',
'SN_463831000000102',
'SN_266898002',
'SN_416471007',
'SN_416471007',
'SN_160324006',
'SN_266890009',
'SN_443877004',
'SN_160324006',
'SN_160270001',
'SN_160273004',
'SN_160274005',
'SN_429011007',
'SN_275110002',
'SN_428269004',
'SN_275937001',
'SN_275104002',
'SN_160341008',
'SN_160303001',
'SN_160305008',
'SN_160301004',
'SN_275118009',
'SN_266893006',
'SN_439750006',
'SN_160353007',
'SN_390794009',
'SN_390915000',
'SN_390985008',
'SN_391096007',
'SN_391097003',
'SN_391098008',
'SN_394877006',
'SN_394990003',
'SN_395089003',
'SN_395125009',
'SN_160266009',
'SN_275120007',
'SN_401052005',
'SN_401065001',
'SN_401066000',
'SN_401067009',
'SN_401119001',
'SN_401122004',
'SN_407559004',
'SN_408552005',
'SN_408553000',
'SN_408575003',
'SN_412749001',
'SN_412750001',
'SN_412783008',
'SN_412784002',
'SN_275102003',
'SN_275104002',
'SN_297241004',
'SN_415036007',
'SN_414205003',
'SN_415289003',
'SN_416072008',
'SN_416519002',
'SN_416855002',
'SN_417072001',
'SN_297239000',
'SN_297243001',
'SN_427858005',
'SN_429953005',
'SN_430710003',
'SN_431912005',
'SN_430543001',
'SN_430678008',
'SN_430679000',
'SN_160949003',
'SN_266890009',
'SN_275122004',
'SN_275122004',
'SN_275932007',
'SN_266896003',
'SN_275123009',
'SN_275123009',
'SN_275911004',
'SN_281022002',
'SN_198301000000109',
'SN_198381000000104',
'SN_199071000000106',
'SN_199731000000107',
'SN_440661000000106',
'SN_440661000000106',
'SN_160303001',
'SN_160379003',
'SN_438998000',
'SN_160336000',
'SN_297242006',
'SN_429006005',
'SN_427501000000109',
'SN_266897007',
'SN_275937001',
'SN_451531000000107',
'SN_720428004',
'SN_720741001',
'SN_720411002',
'SN_287351000000105',
'SN_160949003',
'SN_293161000000103',
'SN_160252004',
'SN_430778004',
'SN_335271000000100',
'SN_439154009',
'SN_57177007',
'SN_439213009',
'SN_439138006',
'SN_313102001',
'SN_429006005',
'SN_275109007',
'SN_429740004',
'SN_160446002',
'SN_160445003',
'SN_160364005',
'SN_160456003',
'SN_160455004',
'SN_57177007',
'SN_160435000',
'SN_160434001',
'SN_429006005',
'SN_266898002',
'SN_160331005',
'SN_160324006',
'SN_160429000',
'SN_160428008',
'SN_160472006',
'SN_160441007',
'SN_160440008',
'SN_160451008',
'SN_160450009',
'SN_857491000006101',
'SN_857501000006109',
'SN_857511000006107',
'SN_857521000006104',
'SN_857531000006101',
'SN_857541000006106',
'SN_857551000006108',
'SN_857561000006105',
'SN_857581000006100',
'SN_958011000006105',
'SN_958021000006102',
'SN_958031000006104',
'SN_958041000006109',
'SN_958051000006106',
'SN_958061000006108',
'SN_987341000006101',
'SN_494251000000109',
'SN_494281000000103',
'SN_699057005',
'SN_160947001',
'SN_704008007',
'SN_266969002',
'SN_1669601000006100',
'SN_1669611000006100',
'SN_758811000000100',
'SN_758821000000106',
'SN_439724007',
'SN_438825005',
'SN_759351000000104',
'SN_763241000000104',
'SN_1707331000006100',
'SN_1745931000006100',
'SN_1747871000006100',
'SN_1747881000006100',
'SN_430679000',
'SN_1807531000006100',
'SN_821691000000104',
'SN_821691000000104',
'SN_758841000000104',
'SN_1820361000006100',
'SN_1820381000006110',
'SN_1823711000006110',
'SN_1825131000006100',
'SN_429970002',
'SN_430090006',
'SN_430283008',
'SN_1842151000006100',
'SN_1849601000006100',
'SN_1858951000006100',
'SN_1860711000006110',
'SN_1860781000006100',
'SN_430090006',
'SN_429970002',
'SN_297247000',
'SN_429011007',
'SN_429011007',
'SN_430542006',
'SN_430542006',
'SN_433450000',
'SN_430329007',
'SN_1894961000006100',
'SN_902961000000107',
'SN_71271000119107',
'SN_1930231000006110',
'SN_1992661000006110',
'SN_809201000000102',
'SN_858191000000103',
'SN_858671000000107',
'SN_876461000000101',
'SN_700193001',
'SN_877691000000107',
'SN_700191004',
'SN_700190003',
'SN_878401000000104',
'SN_878421000000108',
'SN_878441000000101',
'SN_878461000000100',
'SN_700192006',
'SN_919701000000100',
'SN_919701000000100',
'SN_932441000000109',
'SN_959511000000100',
'SN_966191000000101',
'SN_977651000000105',
'SN_720436008',
'SN_1035381000000110',
'SN_720407008',
'SN_1038261000000100',
'SN_57177007',
'SN_57177007',
'SN_134439009',
'SN_160250007',
'SN_160252004',
'SN_160266009',
'SN_160267000',
'SN_160267000',
'SN_160268005',
'SN_160268005',
'SN_160269002',
'SN_160270001',
'SN_160270001',
'SN_160271002',
'SN_160271002',
'SN_160271002',
'SN_160273004',
'SN_160273004',
'SN_160274005',
'SN_160279000',
'SN_160280002',
'SN_160281003',
'SN_160282005',
'SN_160282005',
'SN_160282005',
'SN_160288009',
'SN_160288009',
'SN_160288009',
'SN_160290005',
'SN_160290005',
'SN_160290005',
'SN_160291009',
'SN_160291009',
'SN_160291009',
'SN_160292002',
'SN_160292002',
'SN_160292002',
'SN_160292002',
'SN_160292002',
'SN_160297008',
'SN_160298003',
'SN_160299006',
'SN_160301004',
'SN_160301004',
'SN_160302006',
'SN_160302006',
'SN_160305008',
'SN_160305008',
'SN_160306009',
'SN_160308005',
'SN_160309002',
'SN_160309002',
'SN_160310007',
'SN_160311006',
'SN_160312004',
'SN_160313009',
'SN_160313009',
'SN_160314003',
'SN_160314003',
'SN_160314003',
'SN_160316001',
'SN_160316001',
'SN_160318000',
'SN_160318000',
'SN_160319008',
'SN_160319008',
'SN_160319008',
'SN_160319008',
'SN_160319008',
'SN_160320002',
'SN_160320002',
'SN_160320002',
'SN_160320002',
'SN_160320002',
'SN_160321003',
'SN_160321003',
'SN_160325007',
'SN_160325007',
'SN_160327004',
'SN_160327004',
'SN_160328009',
'SN_160328009',
'SN_160329001',
'SN_160329001',
'SN_160331005',
'SN_160332003',
'SN_160332003',
'SN_160333008',
'SN_160333008',
'SN_160336000',
'SN_160336000',
'SN_160336000',
'SN_160337009',
'SN_160337009',
'SN_160338004',
'SN_160339007',
'SN_160340009',
'SN_160340009',
'SN_160341008',
'SN_160341008',
'SN_160342001',
'SN_160342001',
'SN_160343006',
'SN_160343006',
'SN_160344000',
'SN_160344000',
'SN_160346003',
'SN_160346003',
'SN_160346003',
'SN_160347007',
'SN_160347007',
'SN_160348002',
'SN_160348002',
'SN_160350005',
'SN_160350005',
'SN_160350005',
'SN_160352002',
'SN_160352002',
'SN_160353007',
'SN_160353007',
'SN_160354001',
'SN_160354001',
'SN_160357008',
'SN_160357008',
'SN_160362009',
'SN_160362009',
'SN_160363004',
'SN_160364005',
'SN_160364005',
'SN_160377001',
'SN_160378006',
'SN_160379003',
'SN_160381001',
'SN_160381001',
'SN_160386006',
'SN_160386006',
'SN_160386006',
'SN_160389004',
'SN_160389004',
'SN_160390008',
'SN_160390008',
'SN_160391007',
'SN_160391007',
'SN_160392000',
'SN_160392000',
'SN_160393005',
'SN_160393005',
'SN_160394004',
'SN_160396002',
'SN_160397006',
'SN_160398001',
'SN_160400002',
'SN_160400002',
'SN_160401003',
'SN_160402005',
'SN_160403000',
'SN_160406008',
'SN_160407004',
'SN_160408009',
'SN_160409001',
'SN_160410006',
'SN_160413008',
'SN_160414002',
'SN_160417009',
'SN_160418004',
'SN_160418004',
'SN_160419007',
'SN_160419007',
'SN_160419007',
'SN_160421002',
'SN_160421002',
'SN_160422009',
'SN_160423004',
'SN_160423004',
'SN_160424005',
'SN_160424005',
'SN_160424005',
'SN_160425006',
'SN_160427003',
'SN_160427003',
'SN_160428008',
'SN_160429000',
'SN_160430005',
'SN_160431009',
'SN_160433007',
'SN_160433007',
'SN_160433007',
'SN_160434001',
'SN_160435000',
'SN_160436004',
'SN_160437008',
'SN_160439006',
'SN_160439006',
'SN_160440008',
'SN_160441007',
'SN_160442000',
'SN_160444004',
'SN_160444004',
'SN_160444004',
'SN_160445003',
'SN_160446002',
'SN_160447006',
'SN_160449009',
'SN_160449009',
'SN_160450009',
'SN_160450009',
'SN_160451008',
'SN_160451008',
'SN_160452001',
'SN_160454000',
'SN_160454000',
'SN_160455004',
'SN_160455004',
'SN_160456003',
'SN_160457007',
'SN_160460000',
'SN_160460000',
'SN_160461001',
'SN_160461001',
'SN_160462008',
'SN_160462008',
'SN_160463003',
'SN_160463003',
'SN_160464009',
'SN_160464009',
'SN_160465005',
'SN_160465005',
'SN_160469004',
'SN_160469004',
'SN_160471004',
'SN_160471004',
'SN_160471004',
'SN_160472006',
'SN_160472006',
'SN_160473001',
'SN_160474007',
'SN_160475008',
'SN_160953001',
'SN_266882009',
'SN_266882009',
'SN_266882009',
'SN_266882009',
'SN_266882009',
'SN_266885006',
'SN_266885006',
'SN_266886007',
'SN_266886007',
'SN_266886007',
'SN_266887003',
'SN_266888008',
'SN_266888008',
'SN_266889000',
'SN_266889000',
'SN_266889000',
'SN_266889000',
'SN_266889000',
'SN_266890009',
'SN_266891008',
'SN_266891008',
'SN_266893006',
'SN_266893006',
'SN_266894000',
'SN_266894000',
'SN_266895004',
'SN_266895004',
'SN_266895004',
'SN_266895004',
'SN_266896003',
'SN_266896003',
'SN_266896003',
'SN_266897007',
'SN_266898002',
'SN_266898002',
'SN_266899005',
'SN_266899005',
'SN_266900000',
'SN_266900000',
'SN_266901001',
'SN_266902008',
'SN_266902008',
'SN_266903003',
'SN_266903003',
'SN_266904009',
'SN_266906006',
'SN_266907002',
'SN_266907002',
'SN_266907002',
'SN_266908007',
'SN_266908007',
'SN_266967000',
'SN_275101005',
'SN_275102003',
'SN_275104002',
'SN_275106000',
'SN_275108004',
'SN_275109007',
'SN_275109007',
'SN_275110002',
'SN_275110002',
'SN_275111003',
'SN_275113000',
'SN_275117004',
'SN_275118009',
'SN_275119001',
'SN_275120007',
'SN_275120007',
'SN_275120007',
'SN_275120007',
'SN_275121006',
'SN_275122004',
'SN_275123009',
'SN_275124003',
'SN_275125002',
'SN_275127005',
'SN_275128000',
'SN_275129008',
'SN_275129008',
'SN_275130003',
'SN_275131004',
'SN_275132006',
'SN_275132006',
'SN_275133001',
'SN_275136009',
'SN_275911004',
'SN_275932007',
'SN_275938006',
'SN_275938006',
'SN_275939003',
'SN_275939003',
'SN_275939003',
'SN_275940001',
'SN_281020005',
'SN_281022002',
'SN_281022002',
'SN_289916006',
'SN_297239000',
'SN_297241004',
'SN_297241004',
'SN_297242006',
'SN_297242006',
'SN_297243001',
'SN_297243001',
'SN_300933005',
'SN_310247005',
'SN_310251007',
'SN_313342001',
'SN_313376005',
'SN_315619001',
'SN_315620007',
'SN_315621006',
'SN_315622004',
'SN_315623009',
'SN_315625002',
'SN_315626001',
'SN_315627005',
'SN_390794009',
'SN_390915000',
'SN_391096007',
'SN_391097003',
'SN_391098008',
'SN_394877006',
'SN_394877006',
'SN_394877006',
'SN_394990003',
'SN_395089003',
'SN_395089003',
'SN_395125009',
'SN_401052005',
'SN_401052005',
'SN_401052005',
'SN_401065001',
'SN_401065001',
'SN_401066000',
'SN_401067009',
'SN_401067009',
'SN_401067009',
'SN_401119001',
'SN_401119001',
'SN_401119001',
'SN_401122004',
'SN_401122004',
'SN_401122004',
'SN_408575003',
'SN_412749001',
'SN_412783008',
'SN_412784002',
'SN_416072008',
'SN_416471007',
'SN_416519002',
'SN_416855002',
'SN_417072001',
'SN_417072001',
'SN_417072001',
'SN_417072001',
'SN_417072001',
'SN_428269004',
'SN_428269004',
'SN_429011007',
'SN_430329007',
'SN_430542006',
'SN_430543001',
'SN_430677003',
'SN_430677003',
'SN_430954001',
'SN_433450000',
'SN_438825005',
'SN_438998000',
'SN_438998000',
'SN_439213009',
'SN_439220002',
'SN_439724007',
'SN_439750006',
'SN_443454007',
'SN_443720007',
'SN_444333006',
'SN_699057005',
'SN_720436008',
'SN_134591000119102',
'SN_198301000000109',
'SN_198381000000104',
'SN_335271000000100',
'SN_537231000000106',
'SN_160436004',
'SN_160452001',
'SN_266888008',
'SN_281666001',
'SN_430329007',
'SN_297242006',
'SN_160292002',
'SN_427501000000109',
'SN_427501000000109',
'SN_427501000000109',
'SN_427501000000109',
'SN_428951000000102',
'SN_297247000',
'SN_160314003',
'SN_429011007',
'SN_763598005',
'SN_429740004',
'SN_440661000000106',
'SN_440661000000106',
'SN_160252004',
'SN_160250007',
'SN_266885006',
'SN_275938006',
'SN_275132006',
'SN_160340009',
'SN_401066000',
'SN_429740004',
'SN_160466006',
'SN_160467002',
'SN_480541000000100',
'SN_451531000000107',
'SN_463831000000102',
'SN_410061000000105',
'SN_410911000000107',
'SN_160466006',
'SN_160467002',
'SN_410911000000107',
'SN_410911000000107',
'SN_297242006'
);