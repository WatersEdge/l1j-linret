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

import l1j.server.server.ClientThread;
import l1j.server.server.datatables.NpcActionTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.npc.L1NpcHtml;
import l1j.server.server.model.npc.action.L1NpcAction;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.serverpackets.S_SystemMessage;
// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket, C_NPCTalk

public class C_NPCTalk extends ClientBasePacket {

	private static final String C_NPC_TALK = "[C] C_NPCTalk";
	private static Logger _log = Logger.getLogger(C_NPCTalk.class.getName());

	public C_NPCTalk(byte abyte0[], ClientThread client)
			throws Exception {
		super(abyte0);
		int objid = readD();
		L1Object obj = L1World.getInstance().findObject(objid);
		L1PcInstance pc = client.getActiveChar();
		if (obj != null && pc != null) {
			if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81123) {
				if (pc.getInventory().consumeItem(240102, 100)) {
					L1ItemInstance item = ItemTable.getInstance().createItem(240105);  
					if (item != null) {
						pc.getInventory().storeItem(item);
					}
					pc.sendPackets(new S_SystemMessage("Damn dude!  How many rugrats did you waste to get that?!  Here's a token of our appreciation!")); 			
				} else if (pc.getInventory().consumeItem(240102, 50)) {
					pc.sendPackets(new S_SystemMessage("Duuuude, planned parenthood aint got shit on you!  Here's your reward.")); 			
				} else if (pc.getInventory().consumeItem(240102, 25)) {
					pc.sendPackets(new S_SystemMessage("Sweet, you pwned them kids like red headed step children. Here's some loots.")); 			
				} else if (pc.getInventory().consumeItem(240102, 10)) {
					pc.sendPackets(new S_SystemMessage("You really don't like kids do you?  Here, take this for your trouble.")); 			
				} else if (pc.getInventory().consumeItem(240102, 5)) {
					pc.sendPackets(new S_SystemMessage("Awesome, get rid of them rugrats.  Here's some love.")); 			
				} else if (pc.getInventory().consumeItem(240102, 1)) {
					pc.sendPackets(new S_SystemMessage("Here's a small token of appreciation.")); 								
				} else {
					pc.sendPackets(new S_SystemMessage("You do not have anything I'm interested in.  Help rid aden of these bastard children and money hungry bitches!")); 		
				}
			return;
			}
			L1NpcAction action = NpcActionTable.getInstance().get(pc, obj);
			if (action != null) {
				L1NpcHtml html = action.execute("", pc, obj, new byte[0]);
				if (html != null) {
					pc.sendPackets(new S_NPCTalkReturn(obj.getId(), html));
				}
				return;
			}
			obj.onTalkAction(pc);
		} else {
			if (obj == null && pc != null)
			{
				_log.severe(pc.getName() + " sent an invalid objectid, objid=" + objid);
			}
			else
			{
				_log.severe("Null L1PcInstance in C_RequestNPCTalk.");
			}
		}
	}

	@Override
	public String getType() {
		return C_NPC_TALK;
	}
}
