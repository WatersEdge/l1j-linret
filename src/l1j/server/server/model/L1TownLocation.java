/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.server.server.model;

import java.util.Random;

import l1j.server.server.datatables.TownTable;
import l1j.server.server.templates.L1Town;
import l1j.server.server.types.Point;

// Referenced classes of package l1j.server.server.model:
// L1CastleLocation

public class L1TownLocation {
	
	// town_id
	public static final int TOWNID_TALKING_ISLAND = 1;

	public static final int TOWNID_SILVER_KNIGHT_TOWN = 2;

	public static final int TOWNID_GLUDIO = 3;

	public static final int TOWNID_ORCISH_FOREST = 4;

	public static final int TOWNID_WINDAWOOD = 5;

	public static final int TOWNID_KENT = 6;

	public static final int TOWNID_GIRAN = 7;

	public static final int TOWNID_HEINE = 8;

	public static final int TOWNID_WERLDAN = 9;

	public static final int TOWNID_OREN = 10;

	public static final int TOWNID_ELVEN_FOREST = 11;

	public static final int TOWNID_ADEN = 12;

	public static final int TOWNID_SILENT_CAVERN = 13;

	public static final int TOWNID_OUM_DUNGEON = 14;

	public static final int TOWNID_RESISTANCE = 15;

	public static final int TOWNID_PIRATE_ISLAND = 16;

	public static final int TOWNID_RECLUSE_VILLAGE = 17;

	private static final short GETBACK_MAP_TALKING_ISLAND = 0;
	private static final Point[] GETBACK_LOC_TALKING_ISLAND = {
			new Point(32600, 32942), new Point(32574, 32944),
			new Point(32580, 32923), new Point(32557, 32975),
			new Point(32597, 32914), new Point(32580, 32974), };

	private static final short GETBACK_MAP_SILVER_KNIGHT_TOWN = 4;
	private static final Point[] GETBACK_LOC_SILVER_KNIGHT_TOWN = {
			new Point(33071, 33402), new Point(33091, 33396),
			new Point(33085, 33402), new Point(33097, 33366),
			new Point(33110, 33365), new Point(33072, 33392), };

	private static final short GETBACK_MAP_GLUDIO = 4;
	private static final Point[] GETBACK_LOC_GLUDIO = {
			new Point(32601, 32757), new Point(32625, 32809),
			new Point(32611, 32726), new Point(32612, 32781),
			new Point(32605, 32761), new Point(32614, 32739),
			new Point(32612, 32775), };

	private static final short GETBACK_MAP_ORCISH_FOREST = 4;
	private static final Point[] GETBACK_LOC_ORCISH_FOREST = {
			new Point(32750, 32435), new Point(32745, 32447),
			new Point(32738, 32452), new Point(32741, 32436),
			new Point(32749, 32446), };

	private static final short GETBACK_MAP_WINDAWOOD = 4;
	private static final Point[] GETBACK_LOC_WINDAWOOD = {
			new Point(32608, 33178), new Point(32626, 33185),
			new Point(32630, 33179), new Point(32625, 33207),
			new Point(32638, 33203), new Point(32621, 33179), };

	private static final short GETBACK_MAP_KENT = 4;
	private static final Point[] GETBACK_LOC_KENT = { new Point(33048, 32750),
			new Point(33059, 32768), new Point(33047, 32761),
			new Point(33059, 32759), new Point(33051, 32775),
			new Point(33048, 32778), new Point(33064, 32773),
			new Point(33057, 32748), };

	private static final short GETBACK_MAP_GIRAN = 4;
	private static final Point[] GETBACK_LOC_GIRAN = { new Point(33435, 32803),
			new Point(33439, 32817), new Point(33440, 32809),
			new Point(33419, 32810), new Point(33426, 32823),
			new Point(33418, 32818), new Point(33432, 32824), };

	private static final short GETBACK_MAP_HEINE = 4;
	private static final Point[] GETBACK_LOC_HEINE = { new Point(33593, 33242),
			new Point(33593, 33248), new Point(33604, 33236),
			new Point(33599, 33236), new Point(33610, 33247),
			new Point(33610, 33241), new Point(33599, 33252),
			new Point(33605, 33252), };

	private static final short GETBACK_MAP_WERLDAN = 4;
	private static final Point[] GETBACK_LOC_WERLDAN = {
			new Point(33702, 32492), new Point(33747, 32508),
			new Point(33696, 32498), new Point(33723, 32512),
			new Point(33710, 32521), new Point(33724, 32488),
			new Point(33693, 32513), };

	private static final short GETBACK_MAP_OREN = 4;
	private static final Point[] GETBACK_LOC_OREN = { new Point(34086, 32280),
			new Point(34037, 32230), new Point(34022, 32254),
			new Point(34021, 32269), new Point(34044, 32290),
			new Point(34049, 32316), new Point(34081, 32249),
			new Point(34074, 32313), new Point(34064, 32230), };

	private static final short GETBACK_MAP_ELVEN_FOREST = 4;
	private static final Point[] GETBACK_LOC_ELVEN_FOREST = {
			new Point(33065, 32358), new Point(33052, 32313),
			new Point(33030, 32342), new Point(33068, 32320),
			new Point(33071, 32314), new Point(33030, 32370),
			new Point(33076, 32324), new Point(33068, 32336), };

	private static final short GETBACK_MAP_ADEN = 4;
	private static final Point[] GETBACK_LOC_ADEN = { new Point(33915, 33114),
			new Point(34061, 33115), new Point(34090, 33168),
			new Point(34011, 33136), new Point(34093, 33117),
			new Point(33959, 33156), new Point(33992, 33120),
			new Point(34047, 33156), };

	private static final short GETBACK_MAP_SILENT_CAVERN = 304;
	private static final Point[] GETBACK_LOC_SILENT_CAVERN = {
			new Point(32856, 32898), new Point(32860, 32916),
			new Point(32868, 32893), new Point(32875, 32903),
			new Point(32855, 32898), };

	private static final short GETBACK_MAP_OUM_DUNGEON = 310;
	private static final Point[] GETBACK_LOC_OUM_DUNGEON = {
			new Point(32818, 32805), new Point(32800, 32798),
			new Point(32815, 32819), new Point(32823, 32811),
			new Point(32817, 32828), };

	private static final short GETBACK_MAP_RESISTANCE = 400;
	private static final Point[] GETBACK_LOC_RESISTANCE = {
			new Point(32570, 32667), new Point(32559, 32678),
			new Point(32564, 32683), new Point(32574, 32661),
			new Point(32576, 32669), new Point(32572, 32662), };

	private static final short GETBACK_MAP_PIRATE_ISLAND = 440;
	private static final Point[] GETBACK_LOC_PIRATE_ISLAND = {
			new Point(32431, 33058), new Point(32407, 33054), };

	private static final short GETBACK_MAP_RECLUSE_VILLAGE = 400;
	private static final Point[] GETBACK_LOC_RECLUSE_VILLAGE = {
			new Point(32599, 32916), new Point(32599, 32923),
			new Point(32603, 32908), new Point(32595, 32908),
			new Point(32591, 32918), };

	private L1TownLocation() {
	}

	public static int[] getGetBackLoc(int town_id) { // town_id
		Random random = new Random();
		int[] loc = new int[3];

		if (town_id == TOWNID_TALKING_ISLAND) { // TI
			int rnd = random.nextInt(GETBACK_LOC_TALKING_ISLAND.length);
			loc[0] = GETBACK_LOC_TALKING_ISLAND[rnd].getX();
			loc[1] = GETBACK_LOC_TALKING_ISLAND[rnd].getY();
			loc[2] = GETBACK_MAP_TALKING_ISLAND;
		} else if (town_id == TOWNID_SILVER_KNIGHT_TOWN) { // SKT
			int rnd = random.nextInt(GETBACK_LOC_SILVER_KNIGHT_TOWN.length);
			loc[0] = GETBACK_LOC_SILVER_KNIGHT_TOWN[rnd].getX();
			loc[1] = GETBACK_LOC_SILVER_KNIGHT_TOWN[rnd].getY();
			loc[2] = GETBACK_MAP_SILVER_KNIGHT_TOWN;
		} else if (town_id == TOWNID_KENT) { // 
			int rnd = random.nextInt(GETBACK_LOC_KENT.length);
			loc[0] = GETBACK_LOC_KENT[rnd].getX();
			loc[1] = GETBACK_LOC_KENT[rnd].getY();
			loc[2] = GETBACK_MAP_KENT;
		} else if (town_id == TOWNID_GLUDIO) { // 
			int rnd = random.nextInt(GETBACK_LOC_GLUDIO.length);
			loc[0] = GETBACK_LOC_GLUDIO[rnd].getX();
			loc[1] = GETBACK_LOC_GLUDIO[rnd].getY();
			loc[2] = GETBACK_MAP_GLUDIO;
		} else if (town_id == TOWNID_ORCISH_FOREST) { // 
			int rnd = random.nextInt(GETBACK_LOC_ORCISH_FOREST.length);
			loc[0] = GETBACK_LOC_ORCISH_FOREST[rnd].getX();
			loc[1] = GETBACK_LOC_ORCISH_FOREST[rnd].getY();
			loc[2] = GETBACK_MAP_ORCISH_FOREST;
		} else if (town_id == TOWNID_WINDAWOOD) { // 
			int rnd = random.nextInt(GETBACK_LOC_WINDAWOOD.length);
			loc[0] = GETBACK_LOC_WINDAWOOD[rnd].getX();
			loc[1] = GETBACK_LOC_WINDAWOOD[rnd].getY();
			loc[2] = GETBACK_MAP_WINDAWOOD;
		} else if (town_id == TOWNID_GIRAN) { // 
			int rnd = random.nextInt(GETBACK_LOC_GIRAN.length);
			loc[0] = GETBACK_LOC_GIRAN[rnd].getX();
			loc[1] = GETBACK_LOC_GIRAN[rnd].getY();
			loc[2] = GETBACK_MAP_GIRAN;
		} else if (town_id == TOWNID_HEINE) { // 
			int rnd = random.nextInt(GETBACK_LOC_HEINE.length);
			loc[0] = GETBACK_LOC_HEINE[rnd].getX();
			loc[1] = GETBACK_LOC_HEINE[rnd].getY();
			loc[2] = GETBACK_MAP_HEINE;
		} else if (town_id == TOWNID_WERLDAN) { // 
			int rnd = random.nextInt(GETBACK_LOC_WERLDAN.length);
			loc[0] = GETBACK_LOC_WERLDAN[rnd].getX();
			loc[1] = GETBACK_LOC_WERLDAN[rnd].getY();
			loc[2] = GETBACK_MAP_WERLDAN;
		} else if (town_id == TOWNID_OREN) { // 
			int rnd = random.nextInt(GETBACK_LOC_OREN.length);
			loc[0] = GETBACK_LOC_OREN[rnd].getX();
			loc[1] = GETBACK_LOC_OREN[rnd].getY();
			loc[2] = GETBACK_MAP_OREN;
		} else if (town_id == TOWNID_ELVEN_FOREST) { // 
			int rnd = random.nextInt(GETBACK_LOC_ELVEN_FOREST.length);
			loc[0] = GETBACK_LOC_ELVEN_FOREST[rnd].getX();
			loc[1] = GETBACK_LOC_ELVEN_FOREST[rnd].getY();
			loc[2] = GETBACK_MAP_ELVEN_FOREST;
		} else if (town_id == TOWNID_ADEN) { // 
			int rnd = random.nextInt(GETBACK_LOC_ADEN.length);
			loc[0] = GETBACK_LOC_ADEN[rnd].getX();
			loc[1] = GETBACK_LOC_ADEN[rnd].getY();
			loc[2] = GETBACK_MAP_ADEN;
		} else if (town_id == TOWNID_SILENT_CAVERN) { //
			int rnd = random.nextInt(GETBACK_LOC_SILENT_CAVERN.length);
			loc[0] = GETBACK_LOC_SILENT_CAVERN[rnd].getX();
			loc[1] = GETBACK_LOC_SILENT_CAVERN[rnd].getY();
			loc[2] = GETBACK_MAP_SILENT_CAVERN;
		} else if (town_id == TOWNID_OUM_DUNGEON) { 
			int rnd = random.nextInt(GETBACK_LOC_OUM_DUNGEON.length);
			loc[0] = GETBACK_LOC_OUM_DUNGEON[rnd].getX();
			loc[1] = GETBACK_LOC_OUM_DUNGEON[rnd].getY();
			loc[2] = GETBACK_MAP_OUM_DUNGEON;
		} else if (town_id == TOWNID_RESISTANCE) { 
			int rnd = random.nextInt(GETBACK_LOC_RESISTANCE.length);
			loc[0] = GETBACK_LOC_RESISTANCE[rnd].getX();
			loc[1] = GETBACK_LOC_RESISTANCE[rnd].getY();
			loc[2] = GETBACK_MAP_RESISTANCE;
		} else if (town_id == TOWNID_PIRATE_ISLAND) { 
			int rnd = random.nextInt(GETBACK_LOC_PIRATE_ISLAND.length);
			loc[0] = GETBACK_LOC_PIRATE_ISLAND[rnd].getX();
			loc[1] = GETBACK_LOC_PIRATE_ISLAND[rnd].getY();
			loc[2] = GETBACK_MAP_PIRATE_ISLAND;
		} else if (town_id == TOWNID_RECLUSE_VILLAGE) {
			int rnd = random.nextInt(GETBACK_LOC_RECLUSE_VILLAGE.length);
			loc[0] = GETBACK_LOC_RECLUSE_VILLAGE[rnd].getX();
			loc[1] = GETBACK_LOC_RECLUSE_VILLAGE[rnd].getY();
			loc[2] = GETBACK_MAP_RECLUSE_VILLAGE;
		} else { // SKT
			int rnd = random.nextInt(GETBACK_LOC_SILVER_KNIGHT_TOWN.length);
			loc[0] = GETBACK_LOC_SILVER_KNIGHT_TOWN[rnd].getX();
			loc[1] = GETBACK_LOC_SILVER_KNIGHT_TOWN[rnd].getY();
			loc[2] = GETBACK_MAP_SILVER_KNIGHT_TOWN;
		}
		return loc;
	}

	public static int getTownTaxRateByNpcid(int npcid) { // npcid
		int tax_rate = 0;

		int town_id = getTownIdByNpcid(npcid);
		if (town_id >= 1 && town_id <= 10) {
			L1Town town = TownTable.getInstance().getTownTable(town_id);
			tax_rate = town.get_tax_rate() + 2; // 2%
		}
		return tax_rate;
	}

	public static int getTownIdByNpcid(int npcid) { 
		// Aden Castle: Aden throughout the kingdom
		// Kent Castle: Kent, GURUDIN
		// UINDAUDDO Castle: UDDOBEKKU oasis, SHIRUBANAITOTAUN
		// Guillain Castle: Ilan, speaking island
		// Heine Castle: Heine
		// Dwarf Castle: well-done, ivory, ivory village
		// Oak fort: Tue Tamura
		// DIADO fortress: Some war tax

		int town_id = 0;

		switch (npcid) {
		case 70528: // ^E}X^[iTIj
		case 50015: // [JXie|[^[j
		case 70010: // oVi¢¬® ¹ï®j
		case 70011: // DêÇl
		case 70012: // Ziih®j
		case 70014: // phi`¹ï®j
		case 70532: // W\iybg®j
		case 70536: // g[}ibè®j
			town_id = TOWNID_TALKING_ISLAND;
			break;

		case 70799: // ^E}X^[iSKTj
		case 50056: // bgie|[^[j
		case 70073: // Oií®j
		case 70074: // i¹ï®j
		case 70075: // ~_ih®j
			town_id = TOWNID_SILVER_KNIGHT_TOWN;
			break;

		case 70546: // ^E}X^[iKENTj
		case 50020: // X^[ie|[^[j
		case 70018: // C\[Ai¹ï®j
		case 70016: // AfBií®j
		case 70544: // bNiybg®j
			town_id = TOWNID_KENT;
			break;

		case 70567: // ^E}X^[iOj
		case 50024: // XeB[uiOe|[^[j
		case 70019: // AiOh®j
		case 70020: // RiOÃã¨i¤lj
		case 70021: // beiO¹ï®j
		case 70022: // DêÇl
		case 70024: // PeBiOí®j
			town_id = TOWNID_GLUDIO;
			break;

		case 70815: // Îcº^E}X^[
		case 70079: // WN\i¹ï®j
		case 70836: // nXiybg®j
			town_id = TOWNID_ORCISH_FOREST;
			break;

		case 70774: // ^E}X^[iWBj
		case 50054: // gCie|[^[j
		case 70070: // xbTih®j
		case 70071: // AV[iIAVXj
		case 70072: // G~ii¹ï®j
		case 70773: // }[riybg®j
			town_id = TOWNID_WINDAWOOD;
			break;

		case 70594: // ^E}X^[iMj
		case 50036: // EB}ie|[^[j
		case 70026: // fbNin^[j
		case 70028: // _iòi¤lj
		case 70029: // }[KbgiH¿i¤lj
		case 70030: // CA[i¹ï®j
		case 70031: // [ih®j
		case 70032: // o[Wihï®j
		case 70033: // x^i¹ï®j
		case 70038: // Go[giz¤lj
		case 70039: // [i[ií®j
		case 70043: // tBbviç¤lj
		case 70617: // Aiybg®j
		case 70632: // Priybg®j
			town_id = TOWNID_GIRAN;
			break;

		case 70860: // ^E}X^[inClj
		case 50066: // Iie|[^[j
		case 70082: // ubgi¹ï®j
		case 70083: // Voií®j
		case 70084: // G[ih®j
		case 70873: // Giybg®j
			town_id = TOWNID_HEINE;
			break;

		case 70654: // ^E}X^[iEF_j
		case 50039: // X[ie|[^[j
		case 70045: // x[i¹ï®j
		case 70044: // tií®j
		case 70664: // Ruiybg®j
			town_id = TOWNID_WERLDAN;
			break;

		case 70748: // ^E}X^[iI[j
		case 50051: // LEXie|[^[j
		case 70059: // fBRi«vÇ¹ï®j
		case 70060: // _iÛåÌ¸ì@®j
		case 70061: // }hií®j
		case 70062: // oGXiÛåÌ@®j
		case 70063: // rEXi¹ï®j
		case 70065: // GPih®j
		case 70066: // NXgiÛåÌ@®j
		case 70067: // pSiÛåÌ¹ï®j
		case 70068: // tRiÃã¨i¤lj
		case 70749: // }Chiybg®j
			town_id = TOWNID_OREN;
			break;

		case 50044: // VEXie|[^[j
		case 70057: // LTi¹ï®j
		case 70048: // Ii¹ï®j
		case 70052: // Ti¹ï®j
		case 70053: // ViH¿i®j
		case 70049: // [[i|[V®j
		case 70051: // }OXi¹ï®j
		case 70047: // ft}ií®j
		case 70058: // tFKihï®j
		case 70054: // Xrih®j
		case 70055: // GCVkiybgVbvj
		case 70056: // ][hiWvV[^EÃã¨i¤lj
			town_id = TOWNID_ADEN;
			break;

		case 70092: 
		case 70093:
			town_id = TOWNID_OUM_DUNGEON;
			break;

		default:
			break;
		}
		return town_id;
	}
}
