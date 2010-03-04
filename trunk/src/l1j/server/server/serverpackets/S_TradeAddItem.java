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

package l1j.server.server.serverpackets;

import l1j.server.server.Opcodes;
import l1j.server.server.model.Instance.L1ItemInstance;

// Referenced classes of package l1j.server.server.serverpackets:
// ServerBasePacket

public class S_TradeAddItem extends ServerBasePacket {

	private static final String S_TRADE_ADD_ITEM = "[S] S_TradeAddItem";

	public S_TradeAddItem(L1ItemInstance item, int count, int type) {
		writeC(Opcodes.S_OPCODE_TRADEADDITEM);
		writeC(type); // 0: The upper level of a window Trade: Trade window Lower Level
		writeH(item.getItem().getGfxId());
		writeS(item.getNumberedViewName(count));
		// 0:j  1:Êí 2:ô¢ 3:¢Óè
		// 128:j&ó 129:&ó 130:ô¢&ó 131:¢Óè&ó
		writeC(item.getBless());
		writeC(0x00);
	}

	@Override
	public byte[] getContent() {
		return getBytes();
	}

	@Override
	public String getType() {
		return S_TRADE_ADD_ITEM;
	}
}
