/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.server.server.serverpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.L1DatabaseFactory;
import l1j.server.server.Opcodes;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.utils.SQLUtil;

public class S_EnchantRanking extends ServerBasePacket {

  private static final String S_EnchantRanking = "[C] S_EnchantRanking";

	private static Logger _log = Logger.getLogger(S_EnchantRanking.class.getName());

 	private byte[] _byte = null;
 	static String[] name;
  static String[] name1;
  static String[] castlename;
  static String[] clanname;
  static String[] leadername;
  static int[] enchantlvl;
  static int[] aden;
  static int[] armor;
  static int[] pkcount;
  static int[] priaden;
  static int[] castleid;
  static int[] hascastle;
  static int[] taxrate;
  static int[] castleaden;
 	public S_EnchantRanking(L1PcInstance pc, int number) {
    name = new String[10];
    name1 = new String[10];
    enchantlvl = new int [10];
    aden = new int [10];
    armor = new int [10];
    pkcount = new int [10];
    priaden = new int [10];
    castlename = new String [10];
    clanname = new String [10];
    leadername = new String [10];
    castleid = new int [10];
    hascastle = new int [10];
    taxrate = new int [10];
    castleaden = new int [10];
  	buildPacket(pc, number);
 	}

	public S_EnchantRanking(L1NpcInstance board) {
		buildPacket(board);
	}

	private void buildPacket(L1NpcInstance board) {
		int count = 0;
		String[][] db = null;
		int[] id = null;
		db = new String[8][3];
		id = new int[8];
		while(count < 8) {
			id[count] = count + 1;
			db[count][0] = "Ranking";
			db[count][1] = "";
			count++;
		}
		db[0][2] = "1. Top Weapons";
		db[1][2] = "2. Top Armors";
		db[2][2] = "3.  ";
		db[3][2] = "4. Top Pk Count";
		db[4][2] = "5.  ";
		db[5][2] = "6.  ";
		db[6][2] = "7.  ";
		db[7][2] = "8.  ";

		writeC(Opcodes.S_OPCODE_BOARD);
		writeD(board.getId());
		writeC(0xFF); // ?
		writeC(0xFF); // ?
		writeC(0xFF); // ?
		writeC(0x7F); // ?
		writeH(8);
		writeH(300);
		for (int i = 0; i < 8; ++i) {
			writeD(id[i]);
			writeS(db[i][0]);
			writeS(db[i][1]);
			writeS(db[i][2]);
		}
  }

 	private void buildPacket(L1PcInstance pc, int number) {
 		String date = time();
 		String title = null;
	  writeC(Opcodes.S_OPCODE_BOARDREAD);
	  writeD(number);
	  writeS("");
	  switch(number) {
	  	case 1:
	  		title = "Top Weapons";
	  		break;
	  	case 2:
	  		title = "Top Armors";
	  		break;
	  	case 3:
	  		title = " ";
	  		break;
	  	case 4:
	  		title = "Top PkCount";
	  		break;
	  	case 5:
	  		title = " ";
	  		break;
	  	case 6:
	  		title = " ";
	  		break;
	  	case 7:
	  		title = " ";
	  		break;
	  	case 8:
	  		title = " ";
	  		break;
	  }
	  writeS(title);
	  writeS(date);
	  switch(pc.getType()) {
	  	case 0:
		break;
	  	case 1:
		break;
	  	case 2:
		break;
	  	case 3:
		break;
	  	case 4:
		break;
	  }
		Rank(pc, number);
	    if(number == 1) {
	        writeS("\n\r" + "  1 " + "+" + enchantlvl[0] + " " + name[0] + "\n\r" + "   Owned by: " + name1[0] +"\n\r" + "  2 " + "+" + enchantlvl[1] + " " + name[1] + "\n\r" + "  Owned by: " + name1[1] + "\n\r" +	  	          "  3 " + "+" + enchantlvl[2]  + " " + name[2] + "\n\r" + "   : " + name1[2] + "\n\r" +	  	          "  4 " + "+" + enchantlvl[3] + " " + name[3] + "\n\r" + "   : " + name1[3] + "\n\r" +	  	          "  5 " + "+" + enchantlvl[4] + " " + name[4] + "\n\r" + "   : " + name1[4] + "\n\r" +	  	          "  6 " + "+" + enchantlvl[5] + " " + name[5] + "\n\r" + "   : " + name1[5] + "\n\r" +	  	          "  7 " + "+" + enchantlvl[6] + " " + name[6] + "\n\r" + "   : " + name1[6] +"\n\r" +	  	          "  8 " + "+" + enchantlvl[7] + " " + name[7] + "\n\r" + "   : " + name1[7] +"\n\r" +	  	          "  9 " + "+" + enchantlvl[8] + " " + name[8] + "\n\r" + "   : " + name1[8] +"\n\r"+	  	          " 10 " + "+" + enchantlvl[9] + " " + name[9] + "\n\r" + "   : " + name1[9] +"\n\r" +	  	                      "      ");
	  } else if(number == 2) { //
	        writeS("\n\r" + "  1 "+ "+" + armor[0] + " " + name[0] + "\n\r" + "   : " + name1[0] +"\n\r" +	  	          "  2 " + "+" + armor[1] + " " + name[1] +"\n\r" + "   : " + name1[1] + "\n\r" +	  	          "  3 " + "+" + armor[2]  + " " + name[2]+ "\n\r" + "   : " + name1[2] + "\n\r" +	  	          "  4 " + "+" + armor[3] + " " + name[3] + "\n\r" + "   : " + name1[3] + "\n\r" +	  	          "  5 " + "+" + armor[4] + " " + name[4] + "\n\r" + "   : " + name1[4] + "\n\r" +	  	          "  6 " + "+" + armor[5] + " " + name[5] + "\n\r" + "   : " + name1[5] + "\n\r" +	  	          "  7 " + "+" + armor[6] + " " + name[6] + "\n\r" + "   : " + name1[6] +"\n\r" +	  	          "  8 " + "+" + armor[7] + " " + name[7] + "\n\r" + "   : " + name1[7] +"\n\r" +	  	          "  9 " + "+" + armor[8] + " " + name[8] + "\n\r" + "   : " + name1[8] +"\n\r"+	  	          " 10 " + "+" + armor[9] + " " + name[9] + "\n\r" + "   : " + name1[9] +"\n\r" +	  	                      "      ");
	  } else if(number == 3) { //
	        //writeS("\n\r" + "  1 "+ "$ " + aden[0] + " \n\r" + "   : " + name[0] + "\n\r" +	  	          "  2 " + "$ " + aden[1] + " \n\r" + "   : " + name[1] + "\n\r" +	  	          "  3 " + "$ " + aden[2] + " \n\r" + "   : " + name[2] + "\n\r" +	  	          "  4 " + "$ " + aden[3] + " \n\r" + "   : " + name[3] + "\n\r" +	  	          "  5 " + "$ " + aden[4] + " \n\r" + "   : " + name[4] + "\n\r" +	  	          "  6 " + "$ " + aden[5] + " \n\r" + "   : " + name[5] + "\n\r" +	  	          "  7 " + "$ " + aden[6] + " \n\r" + "   : " + name[6] + "\n\r" +	  	          "  8 " + "$ " + aden[7] + " \n\r" + "   : " + name[7] + "\n\r" +	  	          "  9 " + "$ " + aden[8] + " \n\r" + "   : " + name[8] + "\n\r" 	  	          " 10 " + "$ " + aden[9] + " \n\r" + "   : " + name[9] + "\n\r" +	  	                      "      " );
	  } else if(number == 4) { //
	        writeS("\n\r" + "  1 "+ pkcount[0] + "  \n\r" + "   : " + name[0] + "\n\r" +	  	          "  2 " + pkcount[1] + "  \n\r" + "   : " + name[1] + "\n\r" +	  	          "  3 " + pkcount[2] + "  \n\r" + "   : " + name[2] + "\n\r" +	  	          "  4 " + pkcount[3] + "  \n\r" + "   : " + name[3] + "\n\r" +	  	          "  5 " + pkcount[4] + "  \n\r" + "   : " + name[4] + "\n\r" +	  	          "  6 " + pkcount[5] + "  \n\r" + "   : " + name[5] + "\n\r" +	  	          "  7 " + pkcount[6] + "  \n\r" + "   : " + name[6] + "\n\r" +	  	          "  8 " + pkcount[7] + "  \n\r" + "   : " + name[7] + "\n\r" +	  	          "  9 " + pkcount[8] + "  \n\r" + "   : " + name[8] + "\n\r" +	  	          " 10 " + pkcount[9] + "  \n\r" + "   : " + name[9] + "\n\r" +	                      "      ");
	  } else if(number == 5) { //
	        writeS("\n\r" + "  1 "+ priaden[0] + " \n\r" + "   : " + name[0] + "\n\r" +	  	          "  2 " + priaden[1] + " \n\r" + "   : " + name[1] + "\n\r" +	  	          "  3 " + priaden[2] + " \n\r" + "   : " + name[2] + "\n\r" +	  	          "  4 " + priaden[3] + " \n\r" + "   : " + name[3] + "\n\r" +	  	          "  5 " + priaden[4] + " \n\r" + "   : " + name[4] + "\n\r" +	  	          "  6 " + priaden[5] + " \n\r" + "   : " + name[5] + "\n\r" +	  	          "  7 " + priaden[6] + " \n\r" + "   : " + name[6] + "\n\r" +	  	          "  8 " + priaden[7] + " \n\r" + "   : " + name[7]+ "\n\r" +	  	          "  9 " + priaden[8] + " \n\r" + "   : " + name[8] + "\n\r" +	  	          " 10 " + priaden[9] + " \n\r" + "   : " + name[9] + "\n\r" +	  	                      "      ");
	  } else if(number == 6) { //
	       // writeS("\n\r" + castlename[0] + " " + clanname[0] + "  \n\r" + leadername[0]+ "  " + taxrate[0] + "%  \n\r" + "  : " + castleaden[0] +" \n\r\n\r" +	  	          castlename[1] + " " + clanname[1] + "  \n\r" + leadername[1] + "  " + taxrate[1] + "%  \n\r" + "  : " + castleaden[1] + " \n\r\n\r" +	  	          castlename[2] + " " + clanname[2] + "  \n\r" + leadername[2] + "  " + taxrate[2] + "%  \n\r" + "  : " + castleaden[2] + " \n\r\n\r" +	  	          castlename[3] + " " + clanname[3] + "  \n\r" + leadername[3] + "  " + taxrate[3] + "%  \n\r" + "  : " + castleaden[3] + " \n\r\n\r" +	  	      //    castlename[4] + " " + clanname[4] + " \n\r" + leadername[4] + " " + taxrate[4] + "%  \n\r" + "  : " + castleaden[4] + " \n\r" +	  	      //    castlename[5] + " " + clanname[5] + " \n\r" + leadername[5] + " " + taxrate[5] + "%  \n\r" + "  : " + castleaden[5] + " \n\r" +	  	      //    castlename[6] + " " + clanname[6] + " \n\r" + leadername[6] + " " + taxrate[6] + "%  \n\r" + "  : " + castleaden[6] + " \n\r" +	  	      //    castlename[7] + " " + clanname[7] + " \n\r" + leadername[7] + " " + taxrate[7] + "%  \n\r" + "  : " + castleaden[7] + " \n\r" +	  	      //    castlename[8] + " " + clanname[8] + " \n\r" + leadername[8] + " " + taxrate[8] + "%  \n\r" + "  : " + castleaden[8] + " \n\r" +	  	      //    castlename[9] + " " + clanname[9] + " \n\r" + leadername[9] + " " + taxrate[9] + "%  \n\r" + "  : " + castleaden[9] + " \n\r" +	  	                      "      ");
	  } else if(number == 7) { //
		        writeS("\n\r" + "  1 "+ "+" + enchantlvl[0] + " " + name[0] + "\n\r" + "   : " + name1[0] +"\n\r" +		  	          "  2 " + "+" + enchantlvl[1] + " " + name[1] +"\n\r" + "   : " + name1[1] + "\n\r" +		  	          "  3 " + "+" + enchantlvl[2]  + " " + name[2]+ "\n\r" + "   : " + name1[2] + "\n\r" +		  	          "  4 " + "+" + enchantlvl[3] + " " + name[3] + "\n\r" + "   : " + name1[3] + "\n\r" +		  	          "  5 " + "+" + enchantlvl[4] + " " + name[4] + "\n\r" + "   : " + name1[4] + "\n\r" +		  	          "  6 " + "+" + enchantlvl[5] + " " + name[5] + "\n\r" + "   : " + name1[5] + "\n\r" +		  	          "  7 " + "+" + enchantlvl[6] + " " + name[6] + "\n\r" + "   : " + name1[6] +"\n\r" +		  	          "  8 " + "+" + enchantlvl[7] + " " + name[7] + "\n\r" + "   : " + name1[7] +"\n\r" +		  	          "  9 " + "+" + enchantlvl[8] + " " + name[8] + "\n\r" + "   : " + name1[8] +"\n\r"+		  	          " 10 " + "+" + enchantlvl[9] + " " + name[9] + "\n\r" + "   : " + name1[9] +"\n\r" +		  	                      "      ");
		  } else if(number == 8) { //
		        writeS("\n\r" + "  1 "+ "+" + armor[0] + " " + name[0] + "\n\r" + "   : " + name1[0] +"\n\r" +		  	          "  2 " + "+" + armor[1] + " " + name[1] +"\n\r" + "   : " + name1[1] + "\n\r" +		  	          "  3 " + "+" + armor[2]  + " " + name[2]+ "\n\r" + "   : " + name1[2] + "\n\r" +		  	          "  4 " + "+" + armor[3] + " " + name[3] + "\n\r" + "   : " + name1[3] + "\n\r" +		  	          "  5 " + "+" + armor[4] + " " + name[4] + "\n\r" + "   : " + name1[4] + "\n\r" +		  	          "  6 " + "+" + armor[5] + " " + name[5] + "\n\r" + "   : " + name1[5] + "\n\r" +		  	          "  7 " + "+" + armor[6] + " " + name[6] + "\n\r" + "   : " + name1[6] +"\n\r" +		  	          "  8 " + "+" + armor[7] + " " + name[7] + "\n\r" + "   : " + name1[7] +"\n\r" +		  	          "  9 " + "+" + armor[8] + " " + name[8] + "\n\r" + "   : " + name1[8] +"\n\r"+		  	          " 10 " + "+" + armor[9] + " " + name[9] + "\n\r" + "   : " + name1[9] +"\n\r" +		  	                      "      ");
		  }
	    /*else {
		  writeS("\n\r" + "  1 " + name[0] + "\n\r" +
				  "  2 " + name[1] + "\n\r" +
				  "  3 " + name[2] + "\n\r" +
				  "  4 " + name[3] + "\n\r" +
				  "  5 " + name[4] + "\n\r" +
				  "  6 " + name[5] + "\n\r" +
				  "  7 " + name[6] + "\n\r" +
				  "  8 " + name[7] + "\n\r" +
				  "  9 " + name[8] + "\n\r" +
				  " 10 " + name[9] + "\n\r" +
		  		  "             ");
		}*/
  }

	private int Rank(L1PcInstance pc, int number) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		pc.getId();
		int i = 0;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			switch(number) {
		/*		case 1:
					pstm = con.prepareStatement("SELECT char_name FROM 
characters WHERE AccessLevel = 0 order by Exp desc limit 10");
					break;
				case 2:
					pstm = con.prepareStatement("SELECT char_name FROM 
characters WHERE Type = 0 And AccessLevel = 0 order by Exp desc limit 10");
					break;
				case 3:
					pstm = con.prepareStatement("SELECT char_name FROM 
characters WHERE Type = 1 And AccessLevel = 0 order by Exp desc limit 10");
					break;
				case 4:
					pstm = con.prepareStatement("SELECT char_name FROM 
characters WHERE Type = 2 And AccessLevel = 0 order by Exp desc limit 10");
					break;
				case 5:
					pstm = con.prepareStatement("SELECT char_name FROM 
characters WHERE Type = 3 And AccessLevel = 0 order by Exp desc limit 10");
					break;
				case 6:
					pstm = con.prepareStatement("SELECT char_name FROM 
characters WHERE Type = 4 And AccessLevel = 0 order by Exp desc limit 10");
					break;
				case 7:
					pstm = con.prepareStatement("SELECT objid FROM 
characters WHERE AccessLevel = 0 order by Exp desc");
					break;*/
		    case 1: //
		        pstm = con.prepareStatement("SELECT enchantlvl, weapon.name, characters.char_name  FROM character_items, weapon, characters WHERE character_items.item_id in(select item_id from weapon) And character_items.char_id in(select objid from characters where AccessLevel = 0) And character_items.item_id=weapon.item_id And character_items.char_id=characters.objid And count = 1 order by character_items.enchantlvl desc limit 10");
		        //		        pstm = con.prepareStatement("SELECT enchantlvl, weapon.name, characters.char_name FROM character_items, weapon, characters WHERE character_items.item_id in(select item_id from weapon) And character_items.char_id in(select objid from characters where AccessLevel = 200) And character_items.item_id=weapon.item_id And character_items.char_id=characters.objid And character_items.is_equipped = 1 order by character_items.enchantlvl desc limit 10");
		        break;		
		    case 2: //
		        pstm = con.prepareStatement("SELECT enchantlvl, armor.name, characters.char_name  FROM character_items, armor, characters WHERE character_items.item_id in(select item_id from armor) And character_items.char_id in(select objid from characters where AccessLevel = 0) And character_items.item_id=armor.item_id And character_items.char_id=characters.objid And count = 1 order by character_items.enchantlvl desc limit 10");
		        break;	
		    case 3:
		    	pstm = con.prepareStatement("SELECT count, characters.char_name FROM character_items, characters WHERE item_id in(select item_id from etcitem) And char_id in(select objid from characters where AccessLevel = 0) And character_items.char_id=characters.objid And item_id = 40308 order by count desc limit 10");
		        break;
		//	        pstm = con.prepareStatement("SELECT enchantlvl, characters.char_name, armor.name  FROM character_items, characters, armor WHERE item_id in(select item_id from armor) And char_id in(select objid from characters where AccessLevel = 200) And char_id=characters.objid And item_id=armor.item_id And count = 1 order by enchantlvl desc limit 10");
		    case 4: //
		        pstm = con.prepareStatement("SELECT PKcount, char_name FROM characters WHERE AccessLevel = 0 order by PKcount desc limit 10");
		        break;		
		    case 5:
		    	pstm = con.prepareStatement("SELECT count, characters.char_name FROM character_items, characters WHERE item_id in(select item_id from etcitem) And char_id in(select objid from characters where AccessLevel = 0) And character_items.char_id=characters.objid And item_id = 41159 order by count desc limit 10");
		        break;
		    case 6:
		    	pstm = con.prepareStatement("SELECT castle.castle_id, castle.name, clan_data.clan_name, clan_data.leader_name, clan_data.hascastle, castle.tax_rate, castle.public_money from clan_data, castle where castle.castle_id=clan_data.hascastle order by castle.public_money desc limit 10");
		    	break;
		    case 7: //
		        pstm = con.prepareStatement("SELECT enchantlvl, weapon.name, characters.char_name  FROM character_items, weapon, characters WHERE character_items.item_id in(select item_id from weapon) And character_items.char_id in(select objid from characters where AccessLevel = 200) And character_items.item_id=weapon.item_id And character_items.char_id=characters.objid And character_items.is_equipped = 1 order by character_items.enchantlvl desc limit 10");
		        //		        pstm = con.prepareStatement("SELECT enchantlvl, weapon.name, characters.char_name FROM character_items, weapon, characters WHERE character_items.item_id in(select item_id from weapon) And character_items.char_id in(select objid from characters where AccessLevel = 200) And character_items.item_id=weapon.item_id And character_items.char_id=characters.objid And character_items.is_equipped = 1 order by character_items.enchantlvl desc limit 10");
		        break;		
		    case 8: //
		        pstm = con.prepareStatement("SELECT enchantlvl, armor.name, characters.char_name  FROM character_items, armor, characters WHERE character_items.item_id in(select item_id from armor) And character_items.char_id in(select objid from characters where AccessLevel = 200) And character_items.item_id=armor.item_id And character_items.char_id=characters.objid And character_items.is_equipped = 1 order by character_items.enchantlvl desc limit 10");
		        break;	

				default:
					pstm = con.prepareStatement("SELECT char_name FROM characters WHERE AccessLevel = 0 order by Exp desc limit 10");
					break;
			}

			rs = pstm.executeQuery();
				if(number == 1) { //
				    while(rs.next()){
				     enchantlvl[i] = rs.getInt(1);
				    	name[i] = rs.getString(2);
				    	name1[i] = rs.getString(3);
				      i++;
				    }
			    }else if(number == 2) { //
				    while(rs.next()){
				    	armor[i] = rs.getInt(1);
				    	name[i] = rs.getString(2);
				    	name1[i] = rs.getString(3);
					      i++;
			    }
		    }else if(number == 3) { //
			    while(rs.next()){
				     aden[i] = rs.getInt(1);
				     name[i] = rs.getString(2);
				     i++;
		    } 
		    }else if(number == 4) { //
			    while(rs.next()){
			    	pkcount[i] = rs.getInt(1);
			    	name[i] = rs.getString(2);
				      i++;
		    }
		    }else if(number == 5) { //
			    while(rs.next()){
				     priaden[i] = rs.getInt(1);
				     name[i] = rs.getString(2);
				     i++;
		    } 
		    }else if(number == 6) { //
			    while(rs.next()){
			    	castleid[i] = rs.getInt(1);
			    	castlename[i] = rs.getString(2);
			    	clanname[i] = rs.getString(3);
			    	leadername[i] = rs.getString(4);
			    	hascastle[i] = rs.getInt(5);
			    	taxrate[i] = rs.getInt(6);
			    	castleaden[i] = rs.getInt(7); 
				     i++;
		    } 
		    }else if(number == 7) { //
				    while(rs.next()){
				     enchantlvl[i] = rs.getInt(1);
				    	name[i] = rs.getString(2);
				    	name1[i] = rs.getString(3);
				      i++;

				    }
			    }else if(number == 8) { //
				    while(rs.next()){
				    	armor[i] = rs.getInt(1);
				    	name[i] = rs.getString(2);
				    	name1[i] = rs.getString(3);
					      i++;

			    }
			} else {
				while(rs.next()){
					name[i] = rs.getString(1);
					i++;
				}

				//   5 
				while(i < 10){
					name[i] = ".";
					i++;
				}
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}

		return i;
	}
	private static String time() {
		TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
		Calendar cal = Calendar.getInstance(tz);
		int year = cal.get(Calendar.YEAR) - 2000;
		String year2;
		if (year < 10) {
			year2 = "0" + year;
		} else {
			year2 = Integer.toString(year);
		}
		int Month = cal.get(Calendar.MONTH) + 1;
		String Month2 = null;
		if (Month < 10) {
			Month2 = "0" + Month;
		} else {
			Month2 = Integer.toString(Month);
		}
		int date = cal.get(Calendar.DATE);
		String date2 = null;
		if (date < 10) {
			date2 = "0" + date;
		} else {
			date2 = Integer.toString(date);
		}
		return year2 + "/" + Month2 + "/" + date2;
	}

 	@Override
 	public byte[] getContent() {
  	if (_byte == null) {
   		_byte = getBytes();
  	}
  	return _byte;
 	}

 	public String getType() {
  	return S_EnchantRanking;
 	}

}

