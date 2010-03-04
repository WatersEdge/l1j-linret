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

import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.Opcodes;
import l1j.server.server.model.Instance.L1PcInstance;

// Referenced classes of package l1j.server.server.serverpackets:
// ServerBasePacket

public class S_SkillBuy extends ServerBasePacket {
	private static Logger _log = Logger.getLogger(S_SkillBuy.class.getName());
	private static final String _S_SKILL_BUY = "[S] S_SkillBuy";

	private byte[] _byte = null;

	public S_SkillBuy(int o, L1PcInstance pc) {
		int count = Scount(pc);
		int inCount = 0;
		for (int k = 0; k < count; k++) {
			if (!pc.isSkillMastery((k + 1))) {
				inCount++;
			}
		}

		try {
			writeC(Opcodes.S_OPCODE_SKILLBUY);
			writeD(100);
			writeH(inCount);
			for (int k = 0; k < count; k++) {
				if (!pc.isSkillMastery((k + 1))) {
					writeD(k);
				}
			}
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public int Scount(L1PcInstance pc) {
		int RC = 0;
		switch (pc.getType()) {
		case 0: // Nå
			if (pc.getLevel() > 20 || pc.isGm()) {
				RC = 16;
			} else if (pc.getLevel() > 10) {
				RC = 8;
			}
			break;

		case 1: // iCg
			if (pc.getLevel() >= 50 || pc.isGm()) {
				RC = 8;
			}
			break;

		case 2: // Gt
			if (pc.getLevel() >= 24 || pc.isGm()) {
				RC = 23;
			} else if (pc.getLevel() >= 16) {
				RC = 16;
			} else if (pc.getLevel() >= 8) {
				RC = 8;
			}
			break;

		case 3: // WIZ
			if (pc.getLevel() >= 12 || pc.isGm()) {
				RC = 23;
			} else if (pc.getLevel() >= 8) {
				RC = 16;
			} else if (pc.getLevel() >= 4) {
				RC = 8;
			}
			break;

		case 4: // DE
			if (pc.getLevel() >= 24 || pc.isGm()) {
				RC = 16;
			} else if (pc.getLevel() >= 12) {
				RC = 8;
			}
			break;

		default:
			break;
		}
		return RC;
		/*
		 * for(int i = 0 ; i < RC ; ++i) { if(chk(player, i) == false) { TC++; } }
		 * return TC;
		 */
	}

	/*
	 * public boolean chk(L1PcInstance player, int i) { boolean have = false;
	 * try { Connection connection = null; connection =
	 * L1DatabaseFactory.getInstance().getConnection(); PreparedStatement
	 * preparedstatement = connection.prepareStatement("SELECT * FROM
	 * character_skills WHERE char_obj_id=?"); preparedstatement.setInt(1,
	 * player.get_objectId()); ResultSet resultset =
	 * preparedstatement.executeQuery(); while (resultset.next()) { int b =
	 * resultset.getInt(3); if (i == b) { have = true; } } resultset.close();
	 * preparedstatement.close(); connection.close(); } catch (Exception
	 * exception) { } return have; }
	 */
	@Override
	public byte[] getContent() {
		if (_byte == null) {
			_byte = _bao.toByteArray();
		}
		return _byte;
	}

	@Override
	public String getType() {
		return _S_SKILL_BUY;
	}

}
