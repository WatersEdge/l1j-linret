-- update 40

-- Restored cockatrice eye laser attack
INSERT INTO `skills` VALUES ('12001', 'Cockatrice Laser Attack', '0', '0', '10', '0', '0', '0', '0', '0', 'attack', '3', '50', '5', '5', '0', '0', '0', '64', '0', '6', '0', '0', '0', '0', '19', '1054', '0', '0', '0');
update mobskill set skillid = 12001 where mobid = 45361 and actno = 0;
update skills set name = 'unknown' where  skill_id = 10091;

-- Fix DM names
update ub_settings set ub_name = 'Giran' where ub_id = 1;
update ub_settings set ub_name = 'Werldern' where ub_id = 2;
update ub_settings set ub_name = 'Gludio' where ub_id = 3;
update ub_settings set ub_name = 'TI' where ub_id = 4;
update ub_settings set ub_name = 'SKT' where ub_id = 5;

-- DK Sword shouldn't take dmg
update weapon set canbedmg = 0 where item_id = 58;

-- Fix Dark Elf Apprentice mobskill dmg (was way too high)
update mobskill set leverage = 4 where mobid = 45447 and actno = 0; -- was 15
update mobskill set leverage = 9 where mobid = 45447 and actno = 1; -- was 15
