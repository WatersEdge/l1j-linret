
-- update 43

-- Hidden Valley Cave Spawn
INSERT INTO spawnlist VALUES 
(801500403, "Mutated Alligator", 20, 45026, 0, 32871, 32795, 0, 0, 32768, 32704, 32959, 32895, 5, 20, 20, 86, 0, 0, 0, 0),
(801500404, "Underground Ramia", 10, 45074, 0, 32845, 32792, 0, 0, 32768, 32704, 32888, 32788, 5, 20, 20, 86, 0, 0, 0, 0),
(801500405, "Underground Ramia", 10, 45076, 0, 32845, 32792, 0, 0, 32768, 32704, 32888, 32788, 5, 20, 20, 86, 0, 0, 0, 0),
(801500406, "Polluted Orc Zombie", 10, 45028, 0, 32841, 32832, 0, 0, 32768, 32704, 32898, 32870, 5, 20, 20, 86, 0, 0, 0, 0),
(801500407, "Mutated Lizard", 10, 45057, 0, 32841, 32832, 0, 0, 32768, 32704, 32898, 32870, 5, 20, 20, 86, 0, 0, 0, 0),
(801500408, "Fatigued Ratman", 10, 45052, 0, 32845, 32795, 0, 0, 32768, 32704, 32888, 32788, 5, 20, 20, 86, 0, 0, 0, 0),
(801500409, "Sewer Vakuuk", 5, 45072, 0, 32871, 32795, 0, 0, 32768, 32704, 32959, 32895, 5, 20, 20, 86, 0, 0, 0, 0),
(801500410, "Underground Crabman", 10, 45075, 0, 32871, 32795, 0, 0, 32768, 32704, 32959, 32895, 5, 20, 20, 86, 0, 0, 0, 0),
(801500411, "Degenerated Beholder", 5, 45086, 0, 32934, 32811, 0, 0, 32888, 32788, 32959, 32895, 5, 20, 20, 86, 0, 0, 0, 0),
(801500412, "Cursed Bugbear", 5, 45085, 0, 32901, 32736, 0, 0, 32880, 32704, 32959, 32772, 5, 20, 20, 86, 0, 0, 0, 0);

-- 5MR ring droprate rebalance
update droplist set chance = chance - 500 where itemid = 20303;

-- 10MR ring droprate rebalance on 5xF
update droplist set chance = 400 where itemid = 20280 and mobid in (45506, 45524, 45541);

-- remove all duplicate shop items in sell lists
delete from shop where npc_id = 70014 and item_id = 40089 and order_id = 0;
delete from shop where npc_id = 70079 and item_id = 40079 and order_id = 0;
delete from shop where npc_id = 70079 and item_id = 40089 and order_id = 0;


