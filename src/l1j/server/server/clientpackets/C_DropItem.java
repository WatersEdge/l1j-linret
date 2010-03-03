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

import l1j.server.server.datatables.IpTable;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.S_Disconnect;
import l1j.server.server.datatables.CastleTable;

import l1j.server.server.ClientThread;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_Disconnect;

public class C_DropItem extends ClientBasePacket {
	private static Logger _log = Logger.getLogger(C_DropItem.class.getName());
	private static final String C_DROP_ITEM = "[C] C_DropItem";
	private void broadcastToAll(String s) {

		L1World.getInstance().broadcastPacketToAll(new S_SystemMessage(s));

	}

	public C_DropItem(byte[] decrypt, ClientThread client)
			throws Exception {
		super(decrypt);
		int x = readH();
		int y = readH();
		int objectId = readD();
		int count = readD();

		L1PcInstance pc = client.getActiveChar();
		
		if (count < 0)
		{
			Account.ban(pc.getAccountName());
			
			IpTable.getInstance().banIp(pc.getNetConnection().getIp());
	
			pc.sendPackets(new S_Disconnect());
			System.out.println("* * * Banned " + pc.getName() + "for dupe exploit (C_DropItem) * * *");
			broadcastToAll("Roses are red.  Violents are blue");
			broadcastToAll("Fok with my server and I KEEL U");
			broadcastToAll(pc.getName() + " is banned. Bye bye!");

			_log.info(pc.getName() + " attempted dupe exploit (C_DropItem).");
			
			return;
		}
		
		if (pc.isGhost()) {
			return;
		}

		L1ItemInstance item = pc.getInventory().getItem(objectId);
		if (item != null) {
			if (!item.getItem().isTradable()) {
				pc.sendPackets(new S_ServerMessage(210, item.getItem()
						.getName()));
				return;
			}

			Object[] petlist = pc.getPetList().values().toArray();
			for (Object petObject : petlist) {
				if (petObject instanceof L1PetInstance) {
					L1PetInstance pet = (L1PetInstance) petObject;
					if (item.getId() == pet.getItemObjId()) {
						pc.sendPackets(new S_ServerMessage(210, item.getItem()
								.getName()));
						return;
					}
				}
			}

			if (item.isEquipped()) {
				pc.sendPackets(new S_ServerMessage(125));
				return;
			}

			pc.getInventory().tradeItem(item, count,
					L1World.getInstance().getInventory(x, y, pc.getMapId()));
			pc.turnOnOffLight();
		}
	}

	@Override
	public String getType() {
		return C_DROP_ITEM;
	}
}
