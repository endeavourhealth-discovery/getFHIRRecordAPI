
insert into code_category_values (code_category_id, concept_dbid)
SELECT distinct 33, c.dbid-- , s.id, s.name, p2.id, p2.name, i.id, i.name, p.id, p.name, c.id, c.name
FROM concept c																			-- Source SNOMED concept
WHERE c.id in (
'R3_R3_X79jD',
'R3_R3_X7A9s',
'R3_R3_X7AA3',
'R3_R3_X7AA6',
'R3_R3_X7AAJ',
'R3_R3_X7AAR',
'R3_R3_X7AAc',
'R3_R3_X7AB4',
'R3_R3_X7AB8',
'R3_R3_X7ABC',
'R3_R3_X7ABI',
'R3_R3_X7ABL',
'R3_R3_X7ABO',
'R3_R3_X7ABR',
'R3_R3_X7ADm',
'R3_R3_X7ADn',
'R3_R3_X7ADp',
'R3_R3_X7AE1',
'R3_R3_X7AE4',
'R3_R3_X7AG4',
'R3_R3_X7AGI',
'R3_R3_XaBle',
'SN_838911000000109',
'EMLOC_^ESCTPL828041',
'SN_838941000000105',
'EMLOC_^ESCTPL828043',
'SN_110944006',
'SN_431771004',
'EMLOC_^ESCTAS714563',
'EMLOC_^ESCTCY714564',
'EMLOC_^ESCTPE418854',
'SN_119298005',
'R3_X7ADo',
'EMLOC_^ESCTMI431566',
'SN_119309006',
'EMLOC_^ESCTAR431581',
'SN_119312009',
'SN_472941007',
'SN_472924005',
'SN_16213171000119103',
'SN_16213251000119100',
'SN_472926007',
'SN_472935000',
'SN_472928008',
'SN_16223251000119109',
'SN_16213211000119101',
'SN_16220041000119107',
'SN_472925006',
'SN_472921002',
'SN_472923004',
'SN_472936004',
'SN_472929000',
'SN_16213051000119108',
'SN_472931009',
'SN_473403002',
'EMLOC_^ESCTAR748008',
'EMLOC_^ESCTBA989473',
'EMLOC_^ESCTCA431584',
'EMLOC_^ESCTCE748012',
'EMLOC_^ESCTCE748006',
'EMLOC_^ESCTCH748003',
'EMLOC_^ESCTCH748002',
'EMLOC_^ESCTEN989475',
'EMLOC_^ESCTEN989476',
'EMLOC_^ESCTIN748020',
'EMLOC_^ESCTIN989470',
'EMLOC_^ESCTIN989477',
'EMLOC_^ESCTPE748770',
'EMLOC_^ESCTPE748771',
'EMLOC_^ESCTPE747998',
'EMLOC_^ESCTPE989667',
'EMLOC_^ESCTPU747999',
'EMLOC_^ESCTPU748000',
'EMLOC_^ESCTTE748013',
'EMLOC_^ESCTTU989625',
'EMLOC_^ESCTTU989626',
'EMLOC_^ESCTUM748001',
'EMLOC_^ESCTUM747996',
'EMLOC_^ESCTUM748005',
'SN_119313004',
'EMLOC_^ESCTIM431585',
'SN_119325001',
'SN_396357008',
'SN_16222091000119100',
'SN_396483002',
'SN_396355000',
'SN_396358003',
'SN_309505009',
'SN_309506005',
'SN_396354001',
'SN_16222251000119102',
'SN_396353007',
'SN_258549008',
'SN_396356004',
'SN_309067007',
'SN_309504008',
'R3_X7AAG',
'R3_X7ACh',
'R3_XaBio',
'R3_XaBil',
'R3_XaBt9',
'R3_XaBt8',
'R3_XaBt7',
'EMLOC_^ESCTFR989651',
'EMLOC_^ESCTSK431602',
'EMLOC_^ESCTSK431601',
'EMLOC_^ESCTSK431600',
'EMLOC_^ESCTSK539669',
'EMLOC_^ESCTSK598971',
'EMLOC_^ESCTSK598970',
'EMLOC_^ESCTSK598448',
'EMLOC_^ESCTSK598972',
'EMLOC_^ESCTSK989650',
'EMLOC_^ESCTSP598447',
'EMLOC_^ESCTSP656827',
'EMLOC_^ESCTSP656828',
'EMLOC_^ESCTSP656829',
'EMLOC_^ESCTSP656830',
'EMLOC_^ESCTSP656831',
'EMLOC_^ESCTSP656832',
'EMLOC_^ESCTSP657011',
'EMLOC_^ESCTSP989652',
'EMLOC_^ESCTTI431599',
'SN_119328004',
'R2_4I2A.',
'R3_4I2A.',
'SN_119329007',
'R3_X7AG5',
'EMLOC_EMISNQCO66',
'EMLOC_^ESCTCO431610',
'SN_119335007',
'R3_X7AF6',
'EMLOC_^ESCTCO431619',
'EMLOC_^ESCTSP431620',
'SN_119348006',
'EMLOC_^ESCTSE431644',
'SN_119362004',
'SN_122588000',
'SN_122587005',
'SN_737356002',
'EMLOC_^ESCT1167694',
'EMLOC_^ESCTPL435024',
'EMLOC_^ESCTPL435025',
'EMLOC_^ESCTPL431664',
'SN_119363009',
'EMLOC_^ESCTPL431665',
'SN_119367005',
'SN_258418001',
'R3_X7A9y',
'EMLOC_^ESCTBU539504',
'EMLOC_^ESCTSP431670',
'SN_122561005',
'SN_122590004',
'SN_122583009',
'EMLOC_^ESCTBL434988',
'EMLOC_^ESCTER435018',
'EMLOC_^ESCTSE435028',
'SN_122565001',
'SN_16221251000119108',
'SN_699287008',
'SN_446846006',
'R2_461C.',
'R3_XaCWH',
'EMLOC_^ESCTCA434993',
'EMLOC_^ESCTUR434992',
'EMLOC_^ESCTUR733094',
'EMLOC_^ESCTUR751973',
'EMLOC_^ESCTUR989642',
'SN_122576002',
'SN_122577006',
'EMLOC_^ESCTCE435010',
'EMLOC_^ESCTGE435009',
'SN_122584003',
'EMLOC_^ESCTLE435019',
'EMLOC_^ESCTLE435020',
'SN_122589008',
'EMLOC_^ESCTSE435027',
'EMLOC_^ESCTSE435026',
'SN_122591000',
'EMLOC_^ESCTSE435029',
'SN_122593002',
'EMLOC_^ESCTTI435031',
'SN_122602008',
'EMLOC_^ESCTTI435041',
'SN_122619005',
'EMLOC_^ESCTPL435061',
'EMLOC_^ESCTSP435059',
'EMLOC_^ESCTTH435060',
'EMLOC_^ESCTTH435062',
'SN_122880004',
'SN_699284001',
'EMLOC_^ESCTEA751968',
'EMLOC_^ESCTUR435238',
'SN_127457009',
'SN_309059004',
'SN_122737001',
'SN_122739003',
'SN_16211731000119101',
'SN_122596005',
'SN_122597001',
'SN_122598006',
'SN_122599003',
'SN_16211771000119103',
'SN_373103009',
'SN_16214971000119103',
'SN_373102004',
'SN_122600000',
'SN_122595009',
'SN_16214691000119105',
'SN_309058007',
'SN_373101006',
'SN_122738006',
'SN_309546004',
'SN_397199005',
'SN_309547008',
'R3_X7A9w',
'R3_XaBih',
'R3_XaBig',
'R3_XaBts',
'R3_XaBtr',
'EMLOC_^ESCTBR598436',
'EMLOC_^ESCTBR637642',
'EMLOC_^ESCTBR637643',
'EMLOC_^ESCTBR637640',
'EMLOC_^ESCTFR598437',
'EMLOC_^ESCTLU599012',
'EMLOC_^ESCTSE599013',
'EMLOC_^ESCTSP435033',
'EMLOC_^ESCTSP435034',
'EMLOC_^ESCTSP435035',
'EMLOC_^ESCTSP435036',
'EMLOC_^ESCTSP435037',
'EMLOC_^ESCTSP435038',
'EMLOC_^ESCTSP435202',
'EMLOC_^ESCTSP435203',
'EMLOC_^ESCTSP435204',
'EMLOC_^ESCTSP637641',
'EMLOC_^ESCTSP658321',
'EMLOC_^ESCTSP989523',
'EMLOC_^ESCTSP989527',
'EMLOC_^ESCTSP989436',
'EMLOC_^ESCTSP989437',
'EMLOC_^ESCTTI439872',
'SN_127472008',
'SN_122672009',
'SN_399741007',
'SN_16212171000119109',
'SN_309277003',
'SN_122678008',
'SN_122670001',
'SN_127474009',
'SN_122690005',
'SN_396685001',
'SN_122687004',
'SN_362411000000108',
'SN_122684006',
'SN_397232008',
'SN_122688009',
'SN_122679000',
'SN_309275006',
'SN_128174004',
'SN_122676007',
'SN_122681003',
'SN_399407000',
'SN_438804005',
'SN_362361000000102',
'SN_399440009',
'SN_122685007',
'SN_122673004',
'SN_399532000',
'SN_309497005',
'SN_309274005',
'SN_122680002',
'SN_122668005',
'SN_309272009',
'SN_122675006',
'SN_309276007',
'SN_122683000',
'SN_397235005',
'SN_122686008',
'SN_122682005',
'SN_309270001',
'SN_122669002',
'SN_122689001',
'SN_399624001',
'SN_397234009',
'SN_309498000',
'SN_122677003',
'SN_122674005',
'R3_XaBmz',
'R3_XaBn1',
'R3_XaBn7',
'R3_XaBn9',
'R3_XaBn8',
'R3_XaBn3',
'R3_XaBn5',
'R3_XaBnA',
'R3_XaBt0',
'R3_XaBsz',
'EMLOC_^ESCTBL598684',
'EMLOC_^ESCTCY598685',
'EMLOC_^ESCTEX598686',
'EMLOC_^ESCTFR598687',
'EMLOC_^ESCTKI439900',
'EMLOC_^ESCTKI811567',
'EMLOC_^ESCTKI811564',
'EMLOC_^ESCTNE598678',
'EMLOC_^ESCTPA598960',
'EMLOC_^ESCTSP435125',
'EMLOC_^ESCTSP435126',
'EMLOC_^ESCTSP435127',
'EMLOC_^ESCTSP435129',
'EMLOC_^ESCTSP435130',
'EMLOC_^ESCTSP435131',
'EMLOC_^ESCTSP435148',
'EMLOC_^ESCTSP435149',
'EMLOC_^ESCTSP435140',
'EMLOC_^ESCTSP435141',
'EMLOC_^ESCTSP435142',
'EMLOC_^ESCTSP435143',
'EMLOC_^ESCTSP435144',
'EMLOC_^ESCTSP435145',
'EMLOC_^ESCTSP435146',
'EMLOC_^ESCTSP435147',
'EMLOC_^ESCTSP435132',
'EMLOC_^ESCTSP435133',
'EMLOC_^ESCTSP435134',
'EMLOC_^ESCTSP435135',
'EMLOC_^ESCTSP435136',
'EMLOC_^ESCTSP435137',
'EMLOC_^ESCTSP435138',
'EMLOC_^ESCTSP435139',
'EMLOC_^ESCTSP598677',
'EMLOC_^ESCTSP658374',
'EMLOC_^ESCTSP658376',
'EMLOC_^ESCTSP658377',
'EMLOC_^ESCTSP657357',
'EMLOC_^ESCTSP662760',
'EMLOC_^ESCTSP662234',
'EMLOC_^ESCTSP662288',
'EMLOC_^ESCTSP989443',
'EMLOC_^ESCTTI440508',
'EMLOC_^ESCTTI439896',
'EMLOC_^ESCTTI439899',
'EMLOC_^ESCTTI662574',
'EMLOC_^ESCTTI662434',
'EMLOC_^ESCTTI721430',
'EMLOC_^ESCTTO598959',
'EMLOC_^ESCTUR439897',
'EMLOC_^ESCTUR598681',
'EMLOC_^ESCTUR598688',
'SN_127478007',
'SN_122732007',
'SN_122735009',
'SN_399689008',
'SN_399622002',
'SN_122733002',
'SN_122734008',
'R3_XaBkO',
'R3_XaBkL',
'R3_XaBkM',
'EMLOC_^ESCTEX662681',
'EMLOC_^ESCTRE662680',
'EMLOC_^ESCTSP435195',
'EMLOC_^ESCTSP435196',
'EMLOC_^ESCTSP435197',
'EMLOC_^ESCTSP435198',
'EMLOC_^ESCTSP662678',
'EMLOC_^ESCTSP662571',
'EMLOC_^ESCTTH439905',
'EMLOC_^ESCTTH439906',
'EMLOC_^ESCTTH662679',
'EMLOC_^ESCTTI439904',
'SN_128156008',
'SN_385338007',
'SN_385339004',
'SN_399731009',
'SN_309266009',
'SN_385340002',
'SN_309267000',
'SN_362481000000101',
'SN_438336007',
'SN_399752001',
'R3_XaBmy',
'R3_XaBmv',
'R3_XaBmx',
'EMLOC_^ESCTAN440483',
'EMLOC_^ESCTAN598673',
'EMLOC_^ESCTAN598672',
'EMLOC_^ESCTHA720749',
'EMLOC_^ESCTHA811571',
'EMLOC_^ESCTHE720750',
'EMLOC_^ESCTSP643609',
'EMLOC_^ESCTSP643610',
'EMLOC_^ESCTSP643611',
'EMLOC_^ESCTSP662742',
'EMLOC_^ESCTSP662776',
'EMLOC_^ESCTTI440482',
'SN_128162003',
'R3_XaBkw',
'EMLOC_^ESCTEA440491',
'EMLOC_^ESCTTI440490',
'SN_128164002',
'SN_128161005',
'SN_399603006',
'SN_399512001',
'SN_399735000',
'SN_16212811000119105',
'SN_399443006',
'SN_447403008',
'SN_396928004',
'SN_399499009',
'SN_258425008',
'SN_399451009',
'SN_399559008',
'SN_399467003',
'SN_399640003',
'SN_128160006',
'SN_399619004',
'R3_X7AA7',
'R3_XaBk1',
'EMLOC_^ESCTEY440494',
'EMLOC_^ESCTNA539512',
'EMLOC_^ESCTSP657866',
'EMLOC_^ESCTSP662748',
'EMLOC_^ESCTSP662597',
'EMLOC_^ESCTSP662542',
'EMLOC_^ESCTSP662327',
'EMLOC_^ESCTSP662379',
'EMLOC_^ESCTSP662402',
'EMLOC_^ESCTSP662474',
'EMLOC_^ESCTSP662293',
'EMLOC_^ESCTTI440488',
'EMLOC_^ESCTTI440489',
'EMLOC_^ESCTTI440493',
'EMLOC_^ESCTTI662567',
'EMLOC_^ESCTTI662304',
'EMLOC_^ESCTTI733937',
'EMLOC_^ESCTTI989463',
'SN_128165001',
'SN_122707005',
'SN_399486006',
'SN_122710003',
'SN_127482009',
'SN_399388008',
'SN_122698003',
'SN_122706001',
'SN_309289001',
'SN_16222611000119100',
'SN_122697008',
'SN_122692002',
'SN_309501000',
'SN_397333000',
'SN_397334006',
'SN_765470006',
'SN_122693007',
'SN_128170008',
'SN_309138008',
'SN_122718005',
'SN_16215051000119107',
'SN_122691009',
'SN_397326000',
'SN_127483004',
'SN_122696004',
'SN_397130007',
'SN_399751008',
'SN_765469005',
'SN_122717000',
'SN_122713001',
'SN_309286008',
'SN_127480001',
'SN_122725003',
'SN_397132004',
'SN_122726002',
'SN_122694001',
'SN_122715008',
'SN_122705002',
'SN_309134005',
'SN_122711004',
'SN_397133009',
'SN_122703009',
'SN_16210931000119105',
'SN_122723005',
'SN_309287004',
'SN_122700007',
'SN_127481002',
'SN_122727006',
'SN_122712006',
'SN_397134003',
'SN_397131006',
'SN_396273004',
'SN_122702004',
'SN_122724004',
'SN_397111007',
'SN_397245007',
'SN_384820007',
'SN_122699006',
'SN_309288009',
'SN_122721007',
'SN_128169007',
'SN_122719002',
'SN_309278008',
'SN_122716009',
'SN_122701006',
'SN_128175003',
'SN_122709008',
'SN_309285007',
'SN_447155001',
'SN_397246008',
'SN_127475005',
'SN_122714007',
'SN_309133004',
'SN_258422006',
'SN_369618002',
'SN_128163008',
'SN_122722000',
'SN_122720008',
'SN_399713008',
'R3_X7AA2',
'R3_Xa9oc',
'R3_XaBk9',
'R3_XaBkA',
'R3_XaBk7',
'R3_XaBkD',
'R3_XaBkF',
'R3_XaBnN',
'R3_XaBnM',
'R3_XaBnQ',
'R3_XaBnJ',
'R3_XaBnL',
'R3_XaBnK',
'R3_XaBnR',
'R3_XaBnU',
'R3_XaBnH',
'R3_XaBnG',
'R3_XaBnB',
'R3_XaBnD',
'R3_XaBt4',
'R3_XaBt1',
'EMLOC_^ESCT1203151',
'EMLOC_^ESCT1203150',
'EMLOC_^ESCT1203149',
'EMLOC_^ESCT1203148',
'EMLOC_^ESCTCE439911',
'EMLOC_^ESCTCE598695',
'EMLOC_^ESCTCE598696',
'EMLOC_^ESCTEN435163',
'EMLOC_^ESCTEN435164',
'EMLOC_^ESCTEN598699',
'EMLOC_^ESCTEN598700',
'EMLOC_^ESCTEN989412',
'EMLOC_^ESCTEX598965',
'EMLOC_^ESCTEX598516',
'EMLOC_^ESCTEX598698',
'EMLOC_^ESCTFE598689',
'EMLOC_^ESCTGO539509',
'EMLOC_^ESCTHY598964',
'EMLOC_^ESCTHY598697',
'EMLOC_^ESCTOR598515',
'EMLOC_^ESCTOV439913',
'EMLOC_^ESCTPE440501',
'EMLOC_^ESCTPR440503',
'EMLOC_^ESCTPR598512',
'EMLOC_^ESCTRE598511',
'EMLOC_^ESCTRE598517',
'EMLOC_^ESCTSP435184',
'EMLOC_^ESCTSP435185',
'EMLOC_^ESCTSP435186',
'EMLOC_^ESCTSP435187',
'EMLOC_^ESCTSP435188',
'EMLOC_^ESCTSP435189',
'EMLOC_^ESCTSP435190',
'EMLOC_^ESCTSP435191',
'EMLOC_^ESCTSP435176',
'EMLOC_^ESCTSP435177',
'EMLOC_^ESCTSP435178',
'EMLOC_^ESCTSP435179',
'EMLOC_^ESCTSP435180',
'EMLOC_^ESCTSP435181',
'EMLOC_^ESCTSP435182',
'EMLOC_^ESCTSP435183',
'EMLOC_^ESCTSP435167',
'EMLOC_^ESCTSP435168',
'EMLOC_^ESCTSP435169',
'EMLOC_^ESCTSP435172',
'EMLOC_^ESCTSP435173',
'EMLOC_^ESCTSP435174',
'EMLOC_^ESCTSP435175',
'EMLOC_^ESCTSP435156',
'EMLOC_^ESCTSP435157',
'EMLOC_^ESCTSP435158',
'EMLOC_^ESCTSP435159',
'EMLOC_^ESCTSP435160',
'EMLOC_^ESCTSP435161',
'EMLOC_^ESCTSP435162',
'EMLOC_^ESCTSP435150',
'EMLOC_^ESCTSP435151',
'EMLOC_^ESCTSP435152',
'EMLOC_^ESCTSP435153',
'EMLOC_^ESCTSP435154',
'EMLOC_^ESCTSP435155',
'EMLOC_^ESCTSP598966',
'EMLOC_^ESCTSP642987',
'EMLOC_^ESCTSP656673',
'EMLOC_^ESCTSP658387',
'EMLOC_^ESCTSP658388',
'EMLOC_^ESCTSP658197',
'EMLOC_^ESCTSP658229',
'EMLOC_^ESCTSP658230',
'EMLOC_^ESCTSP658231',
'EMLOC_^ESCTSP658232',
'EMLOC_^ESCTSP658233',
'EMLOC_^ESCTSP662775',
'EMLOC_^ESCTSP662719',
'EMLOC_^ESCTSP662205',
'EMLOC_^ESCTSP658523',
'EMLOC_^ESCTSP658530',
'EMLOC_^ESCTSP658531',
'EMLOC_^ESCTSP989529',
'EMLOC_^ESCTSP989656',
'EMLOC_^ESCTTE439902',
'EMLOC_^ESCTTI440509',
'EMLOC_^ESCTTI440500',
'EMLOC_^ESCTTI440502',
'EMLOC_^ESCTTI440492',
'EMLOC_^ESCTTI440495',
'EMLOC_^ESCTTI439908',
'EMLOC_^ESCTTI439910',
'EMLOC_^ESCTTI439912',
'EMLOC_^ESCTTI439914',
'EMLOC_^ESCTTI439901',
'EMLOC_^ESCTTI632777',
'EMLOC_^ESCTTI662361',
'EMLOC_^ESCTTI733558',
'EMLOC_^ESCTUT439909',
'EMLOC_^ESCTVA440510',
'SN_128166000',
'SN_258424007',
'SN_122625009',
'SN_122624008',
'R3_X7AA5',
'EMLOC_^ESCTHE539511',
'EMLOC_^ESCTSP435067',
'EMLOC_^ESCTSP435068',
'EMLOC_^ESCTTI440496',
'SN_16210971000119108',
'SN_472885008',
'SN_472862007',
'SN_472883001',
'SN_472884007',
'SN_472870002',
'SN_258531008',
'SN_472893008',
'SN_258497007',
'SN_472876008',
'SN_438343001',
'SN_258505006',
'SN_258507003',
'SN_472871003',
'SN_258506007',
'SN_472896000',
'SN_472875007',
'SN_735950000',
'SN_472882006',
'R3_X7ABj',
'R3_X7ABr',
'R3_X7ABs',
'R3_X7ABt',
'R3_X7ACH',
'EMLOC_^ESCT1166169',
'EMLOC_^ESCTAB539604',
'EMLOC_^ESCTLE989414',
'EMLOC_^ESCTSK539614',
'EMLOC_^ESCTSW539616',
'EMLOC_^ESCTSW539615',
'EMLOC_^ESCTSW747917',
'EMLOC_^ESCTSW720760',
'EMLOC_^ESCTSW747960',
'EMLOC_^ESCTSW747964',
'EMLOC_^ESCTSW747949',
'EMLOC_^ESCTSW747945',
'EMLOC_^ESCTSW747946',
'EMLOC_^ESCTSW747947',
'EMLOC_^ESCTSW747948',
'EMLOC_^ESCTSW747934',
'EMLOC_^ESCTSW747935',
'EMLOC_^ESCTSW747927',
'EMLOC_^ESCTSW747928',
'EMLOC_^ESCTSW989413',
'SN_16214131000119104',
'EMLOC_^ESCTFR989504',
'SN_16216331000119107',
'EMLOC_^ESCTCE989574',
'SN_16223531000119100',
'EMLOC_^ESCTLI989672',
'SN_16223571000119102',
'EMLOC_^ESCTPU989673',
'SN_258407001',
'SN_444824001',
'R3_X7A9n',
'EMLOC_^ESCTAB539495',
'EMLOC_^ESCTWA729843',
'SN_258414004',
'R3_X7A9u',
'EMLOC_^ESCTAD539500',
'SN_258417006',
'SN_16211651000119103',
'SN_446304007',
'R3_X7A9x',
'EMLOC_^ESCTBO539503',
'EMLOC_^ESCTEX989434',
'EMLOC_^ESCTTI732240',
'SN_258419009',
'SN_399396003',
'R3_X7A9z',
'EMLOC_^ESCTCU539505',
'EMLOC_^ESCTSP539506',
'EMLOC_^ESCTTR662218',
'SN_258420003',
'R3_X7AA0',
'EMLOC_^ESCTCY539507',
'SN_258423001',
'R3_X7AA4',
'EMLOC_^ESCTHA539510',
'SN_258428005',
'SN_446837000',
'SN_399447007',
'SN_399502008',
'SN_725957005',
'SN_122736005',
'SN_258426009',
'SN_399658009',
'SN_362811000000105',
'SN_258436001',
'SN_399657004',
'R3_X7AAB',
'R3_X7AA8',
'R3_X7AA9',
'R3_X7AAL',
'EMLOC_^ESCTFO787559',
'EMLOC_^ESCTPL435201',
'EMLOC_^ESCTPL435200',
'EMLOC_^ESCTPL539513',
'EMLOC_^ESCTPL539514',
'EMLOC_^ESCTPR539516',
'EMLOC_^ESCTPR539515',
'EMLOC_^ESCTPR811585',
'EMLOC_^ESCTTI435199',
'EMLOC_^ESCTTI662627',
'EMLOC_^ESCTTI733081',
'EMLOC_^ESCTTI811586',
'EMLOC_^ESCTTR662628',
'EMLOC_^ESCTTR662299',
'EMLOC_^ESCTTR662384',
'EMLOC_^ESCTUM539527',
'EMLOC_^ESCTUM539528',
'SN_258435002',
'SN_399614009',
'SN_399732002',
'SN_16210811000119106',
'R3_X7AAK',
'EMLOC_^ESCTDE989408',
'EMLOC_^ESCTDE989407',
'EMLOC_^ESCTSP662743',
'EMLOC_^ESCTSP662744',
'EMLOC_^ESCTSP662559',
'EMLOC_^ESCTSP662560',
'EMLOC_^ESCTTU539524',
'EMLOC_^ESCTTU539523',
'EMLOC_^ESCTTU539526',
'EMLOC_^ESCTTU539525',
'SN_258456000',
'R3_X7AAk',
'EMLOC_^ESCTEM539553',
'SN_258457009',
'SN_119340004',
'R3_X7AAm',
'R3_X7AAw',
'EMLOC_^ESCTFA539554',
'EMLOC_^ESCTFE539555',
'EMLOC_^ESCTME431631',
'EMLOC_^ESCTME431630',
'SN_258484005',
'R3_X7ABQ',
'EMLOC_^ESCTPO539589',
'EMLOC_^ESCTPO539590',
'SN_258487003',
'R3_X7ABX',
'EMLOC_^ESCTFA539593',
'EMLOC_^ESCTFE539594',
'SN_258490009',
'R3_X7ABc',
'EMLOC_^ESCTBI539597',
'SN_258493006',
'R3_X7ABe',
'EMLOC_^ESCTBL539600',
'SN_258494000',
'R3_X7ABf',
'EMLOC_^ESCTPA539601',
'SN_258495004',
'R3_X7ABg',
'EMLOC_^ESCTRE539602',
'SN_258496003',
'R3_X7ABh',
'EMLOC_^ESCTUR539603',
'SN_258502009',
'R3_X7ABo',
'EMLOC_^ESCTPU539609',
'SN_258503004',
'R3_X7ABp',
'EMLOC_^ESCTSK539610',
'SN_258504005',
'R3_X7ABq',
'EMLOC_^ESCTBA539613',
'EMLOC_^ESCTBA539612',
'EMLOC_^ESCTBC539611',
'SN_258508008',
'SN_258517008',
'SN_258516004',
'SN_258514001',
'SN_258521001',
'SN_472874006',
'SN_258512002',
'SN_472878009',
'SN_258513007',
'SN_258511009',
'SN_472861000',
'SN_445369009',
'SN_258510005',
'SN_258509000',
'SN_444787003',
'SN_258520000',
'SN_472899007',
'SN_258518003',
'SN_472887000',
'SN_258515000',
'SN_258519006',
'SN_258523003',
'SN_708317005',
'SN_258522008',
'SN_258524009',
'R2_7E2A5',
'R3_X7ABy',
'R3_X7ABz',
'R3_X7AC0',
'R3_X7AC1',
'R3_X7ABu',
'R3_X7ABv',
'R3_X7ABw',
'R3_X7ABx',
'R3_X7AC6',
'R3_X7AC7',
'R3_X7AC8',
'R3_X7AC9',
'R3_X7AC2',
'R3_X7AC3',
'R3_X7AC4',
'R3_X7AC5',
'R3_X7ACA',
'EMLOC_^ESCTCE539637',
'EMLOC_^ESCTCO539625',
'EMLOC_^ESCTCO763197',
'EMLOC_^ESCTCS539638',
'EMLOC_^ESCTFE539629',
'EMLOC_^ESCTFO539623',
'EMLOC_^ESCTFR539626',
'EMLOC_^ESCTGL539621',
'EMLOC_^ESCTHI539633',
'EMLOC_^ESCTHV539634',
'EMLOC_^ESCTLA747952',
'EMLOC_^ESCTLO539635',
'EMLOC_^ESCTMA539618',
'EMLOC_^ESCTPE539620',
'EMLOC_^ESCTPE539619',
'EMLOC_^ESCTPO763196',
'EMLOC_^ESCTPR539622',
'EMLOC_^ESCTSC539628',
'EMLOC_^ESCTSH539627',
'EMLOC_^ESCTSU539624',
'EMLOC_^ESCTSW539632',
'EMLOC_^ESCTSW747914',
'EMLOC_^ESCTSW747916',
'EMLOC_^ESCTSW729782',
'EMLOC_^ESCTSW730703',
'EMLOC_^ESCTSW747967',
'EMLOC_^ESCTSW747951',
'EMLOC_^ESCTSW747940',
'EMLOC_^ESCTSW747932',
'EMLOC_^ESCTTE747915',
'EMLOC_^ESCTUT747933',
'EMLOC_^ESCTVA539630',
'EMLOC_^ESCTVS539631',
'EMLOC_^ESCTVU539636',
'SN_258525005',
'SN_258526006',
'SN_258527002',
'R2_4JH6.',
'R3_X7ACB',
'R3_X7ACC',
'R3_X7ACD',
'EMLOC_^ESCTPE539640',
'EMLOC_^ESCTPE539639',
'SN_258530009',
'R3_X7ACG',
'EMLOC_^ESCTUR539646',
'EMLOC_^ESCTUS539647',
'SN_258532001',
'SN_258535004',
'SN_258537007',
'SN_431905001',
'SN_258536003',
'R3_X7ACM',
'R3_X7ACI',
'R3_X7ACK',
'R3_X7ACL',
'EMLOC_^ESCTFL539651',
'EMLOC_^ESCTIN539652',
'EMLOC_^ESCTSU539653',
'EMLOC_^ESCTSW539649',
'EMLOC_^ESCTSW714876',
'SN_258554004',
'R3_X7ACw',
'EMLOC_^ESCTFA539673',
'EMLOC_^ESCTFE539674',
'SN_258555003',
'R3_X7ACz',
'EMLOC_^ESCTHO539675',
'SN_258574006',
'SN_699283007',
'R3_X7ADc',
'EMLOC_^ESCTMI539699',
'EMLOC_^ESCTMI751967',
'EMLOC_^ESCTMI751966',
'EMLOC_^ESCTMS539700',
'SN_258590006',
'R3_X7AE5',
'EMLOC_^ESCTAC539720',
'SN_258608003',
'SN_258609006',
'R3_X7AF4',
'R3_X7AF5',
'EMLOC_^ESCTSP539741',
'EMLOC_^ESCTSP539742',
'SN_258610001',
'R3_X7AF7',
'EMLOC_^ESCTSP539743',
'SN_258611002',
'R3_X7AF8',
'EMLOC_^ESCTSP539744',
'SN_258653001',
'R3_X7AG6',
'EMLOC_^ESCTFO539788',
'SN_258655008',
'SN_258658005',
'SN_258657000',
'SN_258656009',
'R3_X7AGA',
'R3_X7AGB',
'R3_X7AG8',
'R3_X7AG9',
'EMLOC_^ESCTCO539791',
'EMLOC_^ESCTSE539793',
'EMLOC_^ESCTSK539794',
'EMLOC_^ESCTWH539792',
'SN_258659002',
'R3_X7AGC',
'EMLOC_^ESCTSH539795',
'SN_258660007',
'R3_X7AGD',
'EMLOC_^ESCTGO539796',
'SN_278020009',
'R3_Xa0au',
'EMLOC_^ESCTSP560761',
'EMLOC_^ESCTSP560762',
'SN_309078004',
'SN_309476009',
'SN_373193000',
'SN_397136001',
'SN_384744003',
'SN_396359006',
'SN_373192005',
'SN_309482007',
'SN_397436009',
'SN_285181000000105',
'SN_309079007',
'SN_399649002',
'SN_446842008',
'SN_399661005',
'R3_XaBiz',
'R3_XaBj0',
'R3_XaBsd',
'R3_XaBsi',
'EMLOC_^ESCTCE598934',
'EMLOC_^ESCTFR598940',
'EMLOC_^ESCTLY598459',
'EMLOC_^ESCTLY598460',
'EMLOC_^ESCTLY656833',
'EMLOC_^ESCTLY637786',
'EMLOC_^ESCTLY637787',
'EMLOC_^ESCTLY642796',
'EMLOC_^ESCTNE598933',
'EMLOC_^ESCTSE808724',
'EMLOC_^ESCTSP658235',
'EMLOC_^ESCTSP662612',
'EMLOC_^ESCTSP662632',
'EMLOC_^ESCTSP658683',
'EMLOC_^ESCTTI733088',
'SN_309121009',
'R3_XaBju',
'EMLOC_^ESCTSY598498',
'EMLOC_^ESCTSY598499',
'SN_309126004',
'SN_309127008',
'SN_16211371000119102',
'R3_XaBk0',
'R3_XaBjz',
'EMLOC_^ESCTAR598504',
'EMLOC_^ESCTRE598505',
'EMLOC_^ESCTSP989426',
'SN_309129006',
'SN_360831000000101',
'R3_XaBk3',
'EMLOC_^ESCTNE598507',
'EMLOC_^ESCTVA811497',
'SN_309150002',
'SN_309153000',
'R3_XaBkW',
'R3_XaBkU',
'EMLOC_^ESCTEX598533',
'EMLOC_^ESCTPA598530',
'SN_309164002',
'SN_258500001',
'SN_697989009',
'SN_472881004',
'SN_258499005',
'SN_472867001',
'SN_472901003',
'SN_445297001',
'SN_1321281000000102',
'SN_461911000124106',
'SN_258501002',
'SN_258529004',
'SN_472888005',
'SN_871810001',
'R2_4I2G.',
'R3_X7ABl',
'R3_X7ABm',
'R3_X7ABn',
'R3_X7ACF',
'R3_XaBks',
'EMLOC_^ESCT1303243',
'EMLOC_^ESCT1303242',
'EMLOC_^ESCTAN750288',
'EMLOC_^ESCTLA747954',
'EMLOC_^ESCTNA539607',
'EMLOC_^ESCTPE539608',
'EMLOC_^ESCTPH747944',
'EMLOC_^ESCTSW730585',
'EMLOC_^ESCTSW747971',
'EMLOC_^ESCTSW747953',
'EMLOC_^ESCTSW747943',
'EMLOC_^ESCTSW747922',
'EMLOC_^ESCTTS539645',
'EMLOC_^ESCTUP598548',
'tpp:Y241e',
'SN_309166000',
'R3_XaBku',
'EMLOC_^ESCTEA598551',
'SN_309495002',
'SN_309496001',
'R3_XaBsx',
'R3_XaBsy',
'EMLOC_^ESCTEX598958',
'EMLOC_^ESCTOM598956',
'EMLOC_^ESCTOM598957',
'SN_362681000000104',
'EMLOC_^ESCTCH811579',
'SN_363328006',
'SN_122643008',
'SN_122632000',
'SN_16210411000119108',
'SN_396804002',
'SN_396480004',
'SN_309484008',
'SN_309227001',
'SN_397053005',
'SN_122656001',
'SN_438351003',
'SN_438659007',
'SN_128168004',
'SN_362941000000102',
'SN_309263001',
'SN_396477000',
'SN_122637006',
'SN_309485009',
'SN_122646000',
'SN_122662006',
'SN_397482001',
'SN_309190004',
'SN_122629003',
'SN_396478005',
'SN_399629006',
'SN_16212091000119109',
'SN_122663001',
'SN_396476009',
'SN_399645008',
'SN_369615004',
'SN_122635003',
'SN_396479002',
'SN_127464006',
'SN_369617007',
'SN_309491006',
'SN_122655002',
'SN_122658000',
'SN_309226005',
'SN_309221000',
'SN_309220004',
'SN_309218002',
'SN_122649007',
'SN_309205005',
'SN_122665008',
'SN_309200000',
'SN_122638001',
'SN_309486005',
'SN_423696009',
'SN_439034006',
'SN_122654003',
'SN_122667000',
'SN_122636002',
'SN_309490007',
'SN_127466008',
'SN_258430007',
'SN_309264007',
'SN_122664007',
'SN_369614000',
'SN_309223002',
'SN_122630008',
'SN_309224008',
'SN_397056002',
'SN_122645001',
'SN_309187005',
'SN_122641005',
'SN_128171007',
'SN_122659008',
'SN_128159001',
'SN_309487001',
'SN_127470000',
'SN_122628006',
'SN_122657005',
'SN_122666009',
'SN_127468009',
'SN_422991009',
'SN_399693002',
'SN_396481000',
'SN_122627001',
'SN_362301000000101',
'SN_122648004',
'SN_362401000000106',
'SN_396807009',
'SN_122639009',
'SN_399541005',
'SN_122647009',
'SN_396805001',
'SN_309489003',
'SN_397483006',
'SN_369616003',
'SN_122661004',
'SN_122633005',
'SN_396806000',
'SN_122650007',
'SN_369611008',
'SN_421615004',
'SN_122642003',
'SN_122631007',
'SN_399484009',
'SN_122660003',
'SN_16212131000119106',
'SN_397055003',
'SN_122651006',
'SN_122640006',
'SN_309186001',
'SN_128172000',
'SN_415563002',
'SN_122634004',
'SN_309488006',
'SN_122652004',
'SN_122653009',
'SN_122644002',
'R3_X7AAD',
'R3_XaBlJ',
'R3_XaBlI',
'R3_XaBln',
'R3_XaBlg',
'R3_XaBlj',
'R3_XaBlv',
'R3_XaBlq',
'R3_XaBlN',
'R3_XaBld',
'R3_XaBlb',
'R3_XaBmD',
'R3_XaBm0',
'R3_XaBm3',
'R3_XaBm2',
'R3_XaBlz',
'R3_XaBm9',
'R3_XaBmC',
'R3_XaBmB',
'R3_XaBm5',
'R3_XaBm8',
'R3_XaBm7',
'R3_XaBmt',
'R3_XaBmu',
'R3_XaBsl',
'R3_XaBsk',
'R3_XaBsn',
'R3_XaBsm',
'R3_XaBst',
'R3_XaBss',
'R3_XaBsp',
'R3_XaBso',
'R3_XaBsr',
'R3_XaBsq',
'EMLOC_^ESCT1182424',
'EMLOC_^ESCTAN598950',
'EMLOC_^ESCTBI598953',
'EMLOC_^ESCTCH598600',
'EMLOC_^ESCTCO440487',
'EMLOC_^ESCTCO598626',
'EMLOC_^ESCTCO598624',
'EMLOC_^ESCTCO598623',
'EMLOC_^ESCTDE598576',
'EMLOC_^ESCTES439884',
'EMLOC_^ESCTEX598943',
'EMLOC_^ESCTEX598945',
'EMLOC_^ESCTEX598579',
'EMLOC_^ESCTEX598601',
'EMLOC_^ESCTEX598669',
'EMLOC_^ESCTGA435084',
'EMLOC_^ESCTGA435083',
'EMLOC_^ESCTGA439888',
'EMLOC_^ESCTGA440505',
'EMLOC_^ESCTGA662581',
'EMLOC_^ESCTGA662580',
'EMLOC_^ESCTHE811566',
'EMLOC_^ESCTIL598618',
'EMLOC_^ESCTLA435095',
'EMLOC_^ESCTLI440499',
'EMLOC_^ESCTLI811560',
'EMLOC_^ESCTOE439883',
'EMLOC_^ESCTOE811592',
'EMLOC_^ESCTOR598575',
'EMLOC_^ESCTPA435122',
'EMLOC_^ESCTPA439893',
'EMLOC_^ESCTPA598952',
'EMLOC_^ESCTPA598949',
'EMLOC_^ESCTPA598942',
'EMLOC_^ESCTPO435097',
'EMLOC_^ESCTPO435080',
'EMLOC_^ESCTRE539518',
'EMLOC_^ESCTRE598670',
'EMLOC_^ESCTRE598668',
'EMLOC_^ESCTRE598580',
'EMLOC_^ESCTRE598595',
'EMLOC_^ESCTRI435103',
'EMLOC_^ESCTSI598627',
'EMLOC_^ESCTSM435089',
'EMLOC_^ESCTSM598620',
'EMLOC_^ESCTSP435124',
'EMLOC_^ESCTSP435115',
'EMLOC_^ESCTSP435116',
'EMLOC_^ESCTSP435117',
'EMLOC_^ESCTSP435118',
'EMLOC_^ESCTSP435119',
'EMLOC_^ESCTSP435120',
'EMLOC_^ESCTSP435121',
'EMLOC_^ESCTSP435123',
'EMLOC_^ESCTSP435106',
'EMLOC_^ESCTSP435107',
'EMLOC_^ESCTSP435108',
'EMLOC_^ESCTSP435109',
'EMLOC_^ESCTSP435110',
'EMLOC_^ESCTSP435111',
'EMLOC_^ESCTSP435113',
'EMLOC_^ESCTSP435114',
'EMLOC_^ESCTSP435093',
'EMLOC_^ESCTSP435096',
'EMLOC_^ESCTSP435098',
'EMLOC_^ESCTSP435099',
'EMLOC_^ESCTSP435100',
'EMLOC_^ESCTSP435101',
'EMLOC_^ESCTSP435104',
'EMLOC_^ESCTSP435105',
'EMLOC_^ESCTSP435081',
'EMLOC_^ESCTSP435082',
'EMLOC_^ESCTSP435085',
'EMLOC_^ESCTSP435086',
'EMLOC_^ESCTSP435087',
'EMLOC_^ESCTSP435090',
'EMLOC_^ESCTSP435091',
'EMLOC_^ESCTSP435092',
'EMLOC_^ESCTSP435072',
'EMLOC_^ESCTSP435073',
'EMLOC_^ESCTSP435074',
'EMLOC_^ESCTSP435075',
'EMLOC_^ESCTSP435076',
'EMLOC_^ESCTSP435077',
'EMLOC_^ESCTSP435078',
'EMLOC_^ESCTSP435079',
'EMLOC_^ESCTSP435070',
'EMLOC_^ESCTSP435071',
'EMLOC_^ESCTSP598944',
'EMLOC_^ESCTSP598946',
'EMLOC_^ESCTSP598951',
'EMLOC_^ESCTSP598602',
'EMLOC_^ESCTSP658108',
'EMLOC_^ESCTSP658110',
'EMLOC_^ESCTSP658111',
'EMLOC_^ESCTSP658112',
'EMLOC_^ESCTSP657602',
'EMLOC_^ESCTSP657603',
'EMLOC_^ESCTSP657604',
'EMLOC_^ESCTSP657601',
'EMLOC_^ESCTSP657004',
'EMLOC_^ESCTSP657005',
'EMLOC_^ESCTSP657006',
'EMLOC_^ESCTSP657007',
'EMLOC_^ESCTSP657008',
'EMLOC_^ESCTSP657009',
'EMLOC_^ESCTSP662579',
'EMLOC_^ESCTSP662582',
'EMLOC_^ESCTSP658756',
'EMLOC_^ESCTSP658757',
'EMLOC_^ESCTSP687954',
'EMLOC_^ESCTSP701217',
'EMLOC_^ESCTSP701218',
'EMLOC_^ESCTSP699967',
'EMLOC_^ESCTSP699968',
'EMLOC_^ESCTSP697499',
'EMLOC_^ESCTSP697500',
'EMLOC_^ESCTSP721235',
'EMLOC_^ESCTSP989441',
'EMLOC_^ESCTSP989442',
'EMLOC_^ESCTSP989397',
'EMLOC_^ESCTSU598947',
'EMLOC_^ESCTSU662359',
'EMLOC_^ESCTTE435102',
'EMLOC_^ESCTTE598621',
'EMLOC_^ESCTTI435094',
'EMLOC_^ESCTTI435112',
'EMLOC_^ESCTTI439881',
'EMLOC_^ESCTTI435088',
'EMLOC_^ESCTTI440498',
'EMLOC_^ESCTTI440504',
'EMLOC_^ESCTTI440506',
'EMLOC_^ESCTTI440486',
'EMLOC_^ESCTTI439882',
'EMLOC_^ESCTTI439887',
'EMLOC_^ESCTTI439890',
'EMLOC_^ESCTTI439892',
'EMLOC_^ESCTTI632773',
'EMLOC_^ESCTTI632774',
'EMLOC_^ESCTTI632775',
'EMLOC_^ESCTTI632776',
'EMLOC_^ESCTTI624260',
'EMLOC_^ESCTTI632769',
'EMLOC_^ESCTTI632770',
'EMLOC_^ESCTTI662606',
'EMLOC_^ESCTTI662448',
'EMLOC_^ESCTTI721764',
'EMLOC_^ESCTTI720775',
'EMLOC_^ESCTTI720776',
'EMLOC_^ESCTTO598948',
'EMLOC_^ESCTTO662689',
'SN_363329003',
'SN_16213851000119101',
'SN_399492000',
'SN_309168004',
'SN_309183009',
'SN_128158009',
'SN_122607002',
'SN_127461003',
'SN_438352005',
'SN_362001000000104',
'SN_122608007',
'SN_309179009',
'SN_16223491000119100',
'SN_127460002',
'SN_122606006',
'SN_128173005',
'SN_122604009',
'SN_309175003',
'SN_440472000',
'SN_384746001',
'SN_128167009',
'SN_122605005',
'SN_309170008',
'R3_XaBlB',
'R3_XaBl6',
'R3_XaBlF',
'R3_XaBl0',
'R3_XaBky',
'EMLOC_^ESCTEX598563',
'EMLOC_^ESCTEX598572',
'EMLOC_^ESCTLA598571',
'EMLOC_^ESCTLO598555',
'EMLOC_^ESCTNA598567',
'EMLOC_^ESCTRE598562',
'EMLOC_^ESCTSP435042',
'EMLOC_^ESCTSP435043',
'EMLOC_^ESCTSP435044',
'EMLOC_^ESCTSP435045',
'EMLOC_^ESCTSP435046',
'EMLOC_^ESCTSP439876',
'EMLOC_^ESCTSP439877',
'EMLOC_^ESCTSP642801',
'EMLOC_^ESCTSP723773',
'EMLOC_^ESCTSP720777',
'EMLOC_^ESCTTI439875',
'EMLOC_^ESCTTI440497',
'EMLOC_^ESCTTI440507',
'EMLOC_^ESCTTI440485',
'EMLOC_^ESCTTI624261',
'EMLOC_^ESCTTI662370',
'EMLOC_^ESCTTI989496',
'EMLOC_^ESCTTI989670',
'EMLOC_^ESCTTR811545',
'EMLOC_^ESCTUP598553',
'SN_365041000000105',
'EMLOC_^ESCTSA811703',
'SN_369613006',
'EMLOC_^ESCTTI632772',
'EMLOC_^ESCTTI632771',
'SN_399542003',
'SN_128157004',
'SN_399728008',
'EMLOC_^ESCTSP662738',
'EMLOC_^ESCTTI440484',
'EMLOC_^ESCTTI662449',
'SN_399572009',
'EMLOC_^ESCTTI662495',
'SN_399672000',
'EMLOC_^ESCTTI662649',
'SN_404643001',
'EMLOC_^ESCTSP670248',
'SN_406101006',
'EMLOC_^ESCTTI672304',
'SN_409821005',
'SN_732222002',
'SN_258575007',
'SN_732211001',
'SN_732220005',
'SN_276833005',
'SN_732217002',
'SN_442043001',
'R3_X7ADd',
'R3_Xa0Dx',
'EMLOC_^ESCT12793768',
'EMLOC_^ESCT24559123',
'EMLOC_^ESCT2H725883',
'EMLOC_^ESCT48793783',
'EMLOC_^ESCT72793786',
'EMLOC_^ESCT8H793777',
'EMLOC_^ESCTEA539703',
'EMLOC_^ESCTEA539701',
'EMLOC_^ESCTEI793778',
'EMLOC_^ESCTEM539702',
'EMLOC_^ESCTFO793784',
'EMLOC_^ESCTSE793787',
'EMLOC_^ESCTTI678493',
'EMLOC_^ESCTTI678495',
'EMLOC_^ESCTTW793769',
'EMLOC_^ESCTUR678494',
'SN_418932006',
'EMLOC_^ESCTOR692916',
'SN_427558003',
'EMLOC_^ESCTSP707570',
'SN_430297000',
'EMLOC_^ESCTCY711778',
'SN_430310001',
'EMLOC_^ESCTCY711797',
'EMLOC_^ESCTSP711796',
'SN_430318008',
'EMLOC_^ESCTCY711812',
'EMLOC_^ESCTUR711811',
'SN_430408004',
'EMLOC_^ESCTCY712022',
'EMLOC_^ESCTPL712023',
'SN_430856003',
'EMLOC_^ESCTTI712771',
'SN_430861001',
'EMLOC_^ESCTGR712780',
'EMLOC_^ESCTMA712779',
'SN_430970004',
'EMLOC_^ESCTCO712923',
'SN_431196006',
'EMLOC_^ESCTTI713270',
'SN_431776009',
'R3_X7ACJ',
'EMLOC_^ESCTSW714573',
'SN_431887008',
'EMLOC_^ESCTSP714837',
'SN_431888003',
'EMLOC_^ESCTSP714838',
'SN_434746001',
'EMLOC_^ESCTSP719686',
'SN_434822004',
'EMLOC_^ESCTSP719794',
'SN_438803004',
'SN_303247002',
'SN_396526009',
'SN_396527000',
'SN_309143001',
'R3_Xa9LQ',
'R3_XaBkK',
'EMLOC_^ESCTAD591589',
'EMLOC_^ESCTAD591588',
'EMLOC_^ESCTEX598522',
'EMLOC_^ESCTRE598523',
'EMLOC_^ESCTRE721429',
'EMLOC_^ESCTSP657079',
'EMLOC_^ESCTSP657080',
'EMLOC_^ESCTSP657081',
'EMLOC_^ESCTTI721428',
'SN_439479000',
'SN_737016007',
'SN_16212971000119107',
'SN_788524009',
'SN_440502004',
'SN_439895009',
'SN_309108002',
'SN_309119004',
'SN_16222771000119104',
'SN_309116006',
'SN_258585008',
'SN_309112008',
'SN_397078009',
'SN_309103006',
'SN_397462000',
'R3_X7ADw',
'R3_XaBjc',
'R3_XaBjl',
'R3_XaBjh',
'R3_XaBjs',
'R3_XaBjp',
'EMLOC_^ESCT1167300',
'EMLOC_^ESCT1167301',
'EMLOC_^ESCT1167299',
'EMLOC_^ESCTBO539714',
'EMLOC_^ESCTEX598485',
'EMLOC_^ESCTEX598489',
'EMLOC_^ESCTEX598493',
'EMLOC_^ESCTEX598496',
'EMLOC_^ESCTEX598481',
'EMLOC_^ESCTSO723834',
'EMLOC_^ESCTSP658146',
'EMLOC_^ESCTSP658722',
'EMLOC_^ESCTSP989467',
'EMLOC_^ESCTTI722362',
'EMLOC_^ESCTTI722924',
'EMLOC_^ESCTUV989658',
'SN_440229008',
'SN_419695002',
'EMLOC_^ESCTEN694140',
'EMLOC_^ESCTEN723420',
'EMLOC_^ESCTSP723419',
'SN_440469007',
'EMLOC_^ESCTTI723768',
'SN_440493002',
'EMLOC_^ESCTGR723813',
'EMLOC_^ESCTGR723815',
'EMLOC_^ESCTGR723814',
'SN_441479001',
'EMLOC_^ESCTFR725054',
'EMLOC_^ESCTUN725055',
'SN_441652008',
'EMLOC_^ESCTFF725317',
'EMLOC_^ESCTFO725316',
'SN_442219007',
'SN_442427000',
'EMLOC_^ESCTPM726136',
'EMLOC_^ESCTPM726411',
'SN_442524009',
'SN_442166002',
'EMLOC_^ESCTAM726066',
'EMLOC_^ESCTAM726553',
'SN_444937002',
'SN_734443003',
'EMLOC_^ESCT1164432',
'EMLOC_^ESCTUR730032',
'EMLOC_^ESCTUR730031',
'SN_445160003',
'SN_472894002',
'SN_258498002',
'R3_X7ABk',
'EMLOC_^ESCTCO539605',
'EMLOC_^ESCTCO747962',
'EMLOC_^ESCTSW730375',
'EMLOC_^ESCTSW747961',
'SN_445364004',
'EMLOC_^ESCTSW730696',
'SN_445372002',
'EMLOC_^ESCTCE730707',
'SN_445383006',
'EMLOC_^ESCTUR730722',
'SN_445742005',
'EMLOC_^ESCTPO731291',
'SN_445743000',
'EMLOC_^ESCTPO731292',
'SN_446130001',
'SN_16221491000119104',
'SN_447488002',
'SN_447589008',
'SN_699285000',
'SN_258576008',
'R3_X7ADf',
'EMLOC_^ESCTSU539704',
'EMLOC_^ESCTSU539705',
'EMLOC_^ESCTSU734094',
'EMLOC_^ESCTUR731954',
'EMLOC_^ESCTUR751969',
'EMLOC_^ESCTUR751970',
'EMLOC_^ESCTUR734282',
'EMLOC_^ESCTUR734283',
'EMLOC_^ESCTVO989647',
'SN_446300003',
'EMLOC_^ESCTUR732235',
'SN_446306009',
'SN_16221371000119107',
'EMLOC_^ESCTUR732242',
'EMLOC_^ESCTUR989643',
'EMLOC_^ESCTUR989645',
'EMLOC_^ESCTUR989644',
'EMLOC_^ESCTUR989646',
'SN_446577002',
'EMLOC_^ESCTSW732662',
'SN_446676001',
'SN_444832009',
'SN_444865001',
'EMLOC_^ESCTEX729853',
'EMLOC_^ESCTEX729910',
'EMLOC_^ESCTEX732805',
'SN_446861007',
'EMLOC_^ESCTCE733120',
'SN_446907008',
'SN_699286004',
'SN_442173007',
'SN_446277003',
'EMLOC_^ESCTUR733194',
'EMLOC_^ESCTUR732200',
'EMLOC_^ESCTUR726074',
'EMLOC_^ESCTUR751971',
'EMLOC_^ESCTUR751972',
'SN_446951004',
'EMLOC_^ESCTSP733252',
'SN_447098004',
'EMLOC_^ESCTSP733475',
'SN_447104008',
'EMLOC_^ESCTUR733480',
'SN_447407009',
'EMLOC_^ESCTSP733941',
'SN_472864008',
'SN_472865009',
'SN_472897009',
'SN_472863002',
'SN_472895001',
'EMLOC_^ESCTSW747918',
'EMLOC_^ESCTSW747963',
'EMLOC_^ESCTSW747965',
'EMLOC_^ESCTSW747919',
'EMLOC_^ESCTSW747920',
'SN_472866005',
'EMLOC_^ESCTSW747921',
'SN_472869003',
'EMLOC_^ESCTPE747926',
'EMLOC_^ESCTSW747925',
'SN_472872005',
'EMLOC_^ESCTSW747929',
'EMLOC_^ESCTSW747930',
'SN_472873000',
'EMLOC_^ESCTSW747931',
'SN_472877004',
'EMLOC_^ESCTES747938',
'EMLOC_^ESCTOE747939',
'EMLOC_^ESCTSW747936',
'EMLOC_^ESCTSW747937',
'SN_472879001',
'EMLOC_^ESCTSW747941',
'SN_472880003',
'SN_472903000',
'EMLOC_^ESCTSW747974',
'EMLOC_^ESCTSW747942',
'SN_472889002',
'SN_258528007',
'R3_X7ACE',
'EMLOC_^ESCTRE539642',
'EMLOC_^ESCTRS539643',
'EMLOC_^ESCTSW747955',
'SN_472891005',
'EMLOC_^ESCTSW747957',
'SN_472898004',
'SN_472892003',
'EMLOC_^ESCTSW747959',
'EMLOC_^ESCTSW747966',
'EMLOC_^ESCTSW747958',
'SN_472900002',
'EMLOC_^ESCTGI747969',
'EMLOC_^ESCTSW747968',
'EMLOC_^ESCTSW747970',
'SN_472902005',
'EMLOC_^ESCTSW747972',
'EMLOC_^ESCTSW747973',
'SN_472904006',
'SN_472868006',
'SN_473399006',
'SN_472886009',
'SN_445367006',
'SN_472890006',
'EMLOC_^ESCTAB747976',
'EMLOC_^ESCTPE747924',
'EMLOC_^ESCTSW730701',
'EMLOC_^ESCTSW747975',
'EMLOC_^ESCTSW748767',
'EMLOC_^ESCTSW747950',
'EMLOC_^ESCTSW747956',
'EMLOC_^ESCTSW747923',
'SN_472922009',
'SN_472940008',
'EMLOC_^ESCTIN747997',
'EMLOC_^ESCTIN748019',
'SN_472932002',
'SN_472920001',
'SN_473407001',
'SN_473409003',
'SN_473410008',
'SN_473413005',
'SN_473408006',
'EMLOC_^ESCTCE748777',
'EMLOC_^ESCTCE748781',
'EMLOC_^ESCTPE748775',
'EMLOC_^ESCTPE748778',
'EMLOC_^ESCTUM748776',
'EMLOC_^ESCTUM747995',
'EMLOC_^ESCTVA748009',
'SN_472944004',
'EMLOC_^ESCTPE748023',
'SN_473400004',
'EMLOC_^ESCTSW748768',
'SN_473405009',
'EMLOC_^ESCTCE748773',
'SN_473414004',
'EMLOC_^ESCTIN748782',
'SN_473416002',
'EMLOC_^ESCTNE748784',
'EMLOC_^ESCTNE748785',
'SN_697988001',
'EMLOC_^ESCTSO750285',
'EMLOC_^ESCTSO750286',
'EMLOC_^ESCTSO750287',
'SN_698276005',
'EMLOC_^ESCTFI750690',
'EMLOC_^ESCTFI750689',
'EMLOC_^ESCTFI750691',
'SN_699874006',
'EMLOC_^ESCTTI752838',
'SN_703432000',
'EMLOC_^ESCTVE757557',
'SN_703475006',
'EMLOC_^ESCTTI757618',
'SN_703691002',
'EMLOC_^ESCTSP758019',
'EMLOC_^ESCTSP758020',
'SN_708048008',
'EMLOC_^ESCTPL762735',
'SN_708049000',
'EMLOC_^ESCTPL762737',
'EMLOC_^ESCTPL762738',
'EMLOC_^ESCTPL762736',
'SN_713791004',
'EMLOC_^ESCTBR770968',
'SN_732226004',
'EMLOC_^ESCT48793794',
'EMLOC_^ESCT48793795',
'EMLOC_^ESCT48793791',
'EMLOC_^ESCT48793793',
'EMLOC_^ESCTFO793792',
'SN_732227008',
'EMLOC_^ESCT24793798',
'EMLOC_^ESCT24793799',
'EMLOC_^ESCT24793800',
'EMLOC_^ESCT24793796',
'EMLOC_^ESCTTW793797',
'SN_732293002',
'EMLOC_^ESCTSA793874',
'EMLOC_^ESCTSA793873',
'SN_733104004',
'EMLOC_^ESCTBU795374',
'EMLOC_^ESCTSW795373',
'SN_733482006',
'EMLOC_^ESCTSE795853',
'EMLOC_^ESCTSE795852',
'SN_734335007',
'SN_733103005',
'EMLOC_^ESCT1164307',
'EMLOC_^ESCTTI795372',
'SN_734336008',
'SN_725372003',
'EMLOC_^ESCT1164308',
'EMLOC_^ESCTAO786800',
'EMLOC_^ESCTAO786799',
'EMLOC_^ESCTAO786798',
'EMLOC_^ESCTTI786801',
'EMLOC_^ESCTTI786797',
'SN_737089009',
'EMLOC_^ESCT1167369'
);