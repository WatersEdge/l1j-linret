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
package l1j.server.server.model.skill;

import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SkillBrave;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillSound;
import static l1j.server.server.model.skill.L1SkillId.*;

public class L1BuffUtil {
	public static void haste(L1PcInstance pc, int timeMillis) {
		pc.setSkillEffect(STATUS_HASTE, timeMillis);

		int objId = pc.getId();
		pc.sendPackets(new S_SkillHaste(objId, 1, timeMillis / 1000));
		pc.broadcastPacket(new S_SkillHaste(objId, 1, 0));
		pc.sendPackets(new S_SkillSound(objId, 191));
		pc.broadcastPacket(new S_SkillSound(objId, 191));
		pc.setMoveSpeed(1);
	}

	public static void brave(L1PcInstance pc, int timeMillis) {
		if (pc.hasSkillEffect(STATUS_ELFBRAVE)) { // GbtÆÍd¡µÈ¢
			pc.killSkillEffectTimer(STATUS_ELFBRAVE);
			pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
			pc.setBraveSpeed(0);
		}
		if (pc.hasSkillEffect(HOLY_WALK)) { // z[[EH[NÆÍd¡µÈ¢
			pc.killSkillEffectTimer(HOLY_WALK);
			pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
			pc.setBraveSpeed(0);
		}
		if (pc.hasSkillEffect(MOVING_ACCELERATION)) { // [rOANZ[VÆÍd¡µÈ¢
			pc.killSkillEffectTimer(MOVING_ACCELERATION);
			pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
			pc.setBraveSpeed(0);
		}
		if (pc.hasSkillEffect(WIND_WALK)) { // EBhEH[NÆÍd¡µÈ¢
			pc.killSkillEffectTimer(WIND_WALK);
			pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
			pc.setBraveSpeed(0);
		}
		if (pc.hasSkillEffect(STATUS_RIBRAVE)) { // OhÌÀÆÍd¡µÈ¢
			pc.killSkillEffectTimer(STATUS_RIBRAVE);
			// XXX OhÌÀÌACRðÁ·û@ªs¾
			pc.setBraveSpeed(0);
		}
		if (pc.hasSkillEffect(BLOODLUST)) { // ubhXgÆÍd¡µÈ¢
			pc.killSkillEffectTimer(BLOODLUST);
			pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
			pc.setBraveSpeed(0);
		}

		pc.setSkillEffect(STATUS_BRAVE, timeMillis);

		int objId = pc.getId();
		pc.sendPackets(new S_SkillBrave(objId, 1, timeMillis / 1000));
		pc.broadcastPacket(new S_SkillBrave(objId, 1, 0));
		pc.sendPackets(new S_SkillSound(objId, 751));
		pc.broadcastPacket(new S_SkillSound(objId, 751));
		pc.setBraveSpeed(1);
	}
}
