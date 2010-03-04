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

package l1j.server.server.clientpackets;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.ClientThread;
import l1j.server.server.datatables.CharacterTable;
import l1j.server.server.datatables.ClanTable;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1War;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_CharTitle;
import l1j.server.server.serverpackets.S_ServerMessage;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

public class C_LeaveClan extends ClientBasePacket {

	private static final String C_LEAVE_CLAN = "[C] C_LeaveClan";
	private static Logger _log = Logger.getLogger(C_LeaveClan.class.getName());

	public C_LeaveClan(byte abyte0[], ClientThread clientthread)
			throws Exception {
		super(abyte0);

		L1PcInstance player = clientthread.getActiveChar();
		String player_name = player.getName();
		String clan_name = player.getClanname();
		int clan_id = player.getClanid();
		if (clan_id == 0) {
			return;
		}

		L1Clan clan = L1World.getInstance().getClan(clan_name);
		if (clan != null) {
			String clan_member_name[] = clan.getAllMembers();
			int i;
			if (player.isCrown() && player.getId() == clan.getLeaderId()) { // vXÜ½ÍvZXA©ÂA¿å
				int castleId = clan.getCastleId();
				int houseId = clan.getHouseId();
				if (castleId != 0 || houseId != 0) {
					player.sendPackets(new S_ServerMessage(665)); // \f1éâAWgðLµ½óÔÅ¿ððU·é±ÆÍÅ«Ü¹ñB
					return;
				}
				for (L1War war : L1World.getInstance().getWarList()) {
					if (war.CheckClanInWar(clan_name)) {
						player.sendPackets(new S_ServerMessage(302)); // \f1ðU³¹é±ÆªÅ«Ü¹ñB
						return;
					}
				}

				for (i = 0; i < clan_member_name.length; i++) { // NõÌNîñðNA
					L1PcInstance online_pc = L1World.getInstance().getPlayer(
							clan_member_name[i]);
					if (online_pc != null) {
						online_pc.setClanid(0);
						online_pc.setClanname("");
						online_pc.setClanRank(0);
						online_pc.setTitle("");
						online_pc.sendPackets(new S_CharTitle(online_pc
								.getId(), ""));
						online_pc.broadcastPacket(new S_CharTitle(online_pc
								.getId(), ""));
						online_pc.save(); // DBÉLN^[îñð«Þ
						online_pc.sendPackets(new S_ServerMessage(269,
								player_name, clan_name)); 
					} else { 
						try {
							L1PcInstance offline_pc = CharacterTable
									.getInstance().restoreCharacter(
											clan_member_name[i]);
							offline_pc.setClanid(0);
							offline_pc.setClanname("");
							offline_pc.setClanRank(0);
							offline_pc.setTitle("");
							offline_pc.save(); // DBÉLN^[îñð«Þ
						} catch (Exception e) {
							_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
						}
					}
				}
				String emblem_file = String.valueOf(clan_id);
				File file = new File("emblem/" + emblem_file);
				file.delete();
				ClanTable.getInstance().deleteClan(clan_name);
			} else { 
				L1PcInstance clanMember[] = clan.getOnlineClanMember();
				for (i = 0; i < clanMember.length; i++) {
					clanMember[i].sendPackets(new S_ServerMessage(178,
							player_name, clan_name)); 
				}
				if (clan.getWarehouseUsingChar()
				== player.getId()) {
					clan.setWarehouseUsingChar(0);
				}
				player.setClanid(0);
				player.setClanname("");
				player.setClanRank(0);
				player.setTitle("");
				player.sendPackets(new S_CharTitle(player.getId(), ""));
				player.broadcastPacket(new S_CharTitle(player.getId(), ""));
				player.save(); // DBÉLN^[îñð«Þ
				clan.delMemberName(player_name);
			}
		} else {
			player.setClanid(0);
			player.setClanname("");
			player.setClanRank(0);
			player.setTitle("");
			player.sendPackets(new S_CharTitle(player.getId(), ""));
			player.broadcastPacket(new S_CharTitle(player.getId(), ""));
			player.save(); // DBÉLN^[îñð«Þ
			player
					.sendPackets(new S_ServerMessage(178, player_name,
							clan_name)); 
		}
	}

	@Override
	public String getType() {
		return C_LEAVE_CLAN;
	}

}
