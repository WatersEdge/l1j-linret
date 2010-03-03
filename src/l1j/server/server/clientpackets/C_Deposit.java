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

import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.server.Account;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.datatables.IpTable;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.S_Disconnect;

import l1j.server.server.ClientThread;
import l1j.server.server.datatables.CastleTable;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.item.L1ItemId;
import l1j.server.server.templates.L1Castle;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

public class C_Deposit extends ClientBasePacket {

	private static final String C_DEPOSIT = "[C] C_Deposit";
	private static Logger _log = Logger.getLogger(C_Deposit.class
			.getName());

	private void broadcastToAll(String s) {

		L1World.getInstance().broadcastPacketToAll(new S_SystemMessage(s));

	}

	public C_Deposit(byte abyte0[], ClientThread clientthread)
			throws Exception {
		super(abyte0);
		int i = readD();
		int j = readD();

		L1PcInstance player = clientthread.getActiveChar();

		if (j < 0)
		{
			Account.ban(player.getAccountName());

			IpTable.getInstance().banIp(player.getNetConnection().getIp());

			player.sendPackets(new S_Disconnect());
			System.out.println("* * * Banned " + player.getName() + "for dupe exploit (C_Deposit) * * *");
			broadcastToAll("Roses are red.  Violents are blue");
			broadcastToAll("Fok with my server and I KEEL U");
			broadcastToAll(player.getName() + " is banned. Bye bye!");			
			
			return;
		}
		
		if (i == player.getId()) {
			L1Clan clan = L1World.getInstance().getClan(player.getClanname());
			if (clan != null) {
				int castle_id = clan.getCastleId();
				if (castle_id != 0) { 
					L1Castle l1castle = CastleTable.getInstance()
							.getCastleTable(castle_id);
					synchronized (l1castle) {
						int money = l1castle.getPublicMoney();
						if (player.getInventory()
								.consumeItem(L1ItemId.ADENA, j)) {
							money += j;
							l1castle.setPublicMoney(money);
							CastleTable.getInstance().updateCastle(l1castle);
						}
					}
				}
			}
		}
	}

	@Override
	public String getType() {
		return C_DEPOSIT;
	}

}
