/*
*
*
*	Word
*
*	- made by Tricid
*
*
*
*/
package l1j.server.server.model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import l1j.server.server.IdFactory;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
//import l1j.server.server.model.L1PvpDmPinkName;
import l1j.server.server.model.Instance.L1DoorInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1ScarecrowInstance;
import l1j.server.server.model.skill.L1SkillId;
import l1j.server.server.serverpackets.S_PinkName;
import l1j.server.server.serverpackets.S_ChangeName;
import l1j.server.server.model.skill.L1SkillUse;
import l1j.server.server.serverpackets.S_NPCPack;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Npc;

import l1j.server.server.model.Instance.L1PcInstance;

public class L1PvpDm {

	public static final int STATUS_NONE = 0;
	public static final int STATUS_READY = 1;
	public static final int STATUS_PLAYING = 2;

	public int ACTIVE = 0;
	private final ArrayList<L1PcInstance> _members =
			new ArrayList<L1PcInstance>();
	private int _PvpDmStatus = STATUS_NONE;
	private int _winnersCount = 0;
	private int _goalCount = 0;
	private HashMap members = new HashMap(); 
	private static L1PvpDm _instance;

	public static L1PvpDm getInstance() {
		if (_instance == null) {
			_instance = new L1PvpDm();
		}
		return _instance;
	}

	private void readyPvpDm() {
		setPvpDmStatus(STATUS_READY);
		L1PvpDmReadyTimer hhrTimer = new L1PvpDmReadyTimer();
		hhrTimer.begin();
	}

	private void startPvpDm() {
		setPvpDmStatus(STATUS_PLAYING);
		int membersCount = getMembersCount();
		if (membersCount <= 4) {
			setWinnersCount(1);
		} else if (5 >= membersCount && membersCount <= 7) {
			setWinnersCount(2);
		} else if (8 >= membersCount && membersCount <= 10) {
			setWinnersCount(3);
		}
		for (L1PcInstance pc : getMembersArray()) {
			members.put( pc, new String(pc.getName()) );
			Random randomname = new Random();
			pc.setName(Integer.toString(randomname.nextInt(999999)));
			Random random = new Random();
			int rndx = random.nextInt(18);
			int rndy = random.nextInt(18);
			int locx = 32729 + rndx;
			int locy = 32789 + rndy;
			short mapid = 509;
			L1Teleport.teleport(pc, locx, locy, mapid, 5, true);
			//L1PvpDmPinkName.onAction(pc);
			//L1Teleport.teleport(pc, 32739, 32797, (short) 509, 8, true);
			//L1SkillUse l1skilluse = new L1SkillUse();
			//l1skilluse.handleCommands(pc,
					//L1SkillId.CANCELLATION, pc.getId(), pc.getX(), pc.getY(),
					//null, 0, L1SkillUse.TYPE_LOGIN);
			//L1PolyMorph.doPoly(pc, 6284, 300, L1PolyMorph.MORPH_BY_NPC);
		}
	
		/*for (L1Object object : L1World.getInstance().getObject()) {
			if (object instanceof L1DoorInstance) {
				L1DoorInstance door = (L1DoorInstance) object;
				if (door.getMapId() == 513) {
					door.open();
				}
			}
		}*/
	}

	public void endPvpDm() {
		restoreNames();
		setPvpDmStatus(STATUS_NONE);
		setWinnersCount(0);
		setGoalCount(0);
		for (L1PcInstance pc : getMembersArray()) {
			if (pc.getMapId() == 509) {
				//L1SkillUse l1skilluse = new L1SkillUse();
				//l1skilluse.handleCommands(pc,
				//		L1SkillId.CANCELLATION, pc.getId(), pc.getX(),
				//		pc.getY(), null, 0, L1SkillUse.TYPE_LOGIN);
				L1Teleport.teleport(pc, 32616, 32764, (short) 4, 5, true);
				pc.setPinkName(false);
			}
		}

		clearMembers();
		members.clear();
		ACTIVE = 0;
		/*for (L1Object object : L1World.getInstance().getObject()) {
			if (object instanceof L1DoorInstance) {
				L1DoorInstance door = (L1DoorInstance) object;
				if (door.getMapId() == 513) {
					door.close();
				}
			}
		}*/
	}

	public void removeRetiredMembers() {
		L1PcInstance[] temp = getMembersArray();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].getMapId() != 509 || temp[i].getCurrentHp() == 0) {
				temp[i].setName((String)members.get(temp[i]));
				temp[i].wideBroadcastPacket(new S_ChangeName(temp[i].getId(),(String) members.get(temp[i])));
				temp[i].sendPackets(new S_ChangeName(temp[i].getId(),(String) members.get(temp[i])));
				removeMember(temp[i]);
			}
		}
	}

	public void sendMessage(int type, String msg) {
		for (L1PcInstance pc : getMembersArray()) {
			pc.sendPackets(new S_ServerMessage(type, msg));
		}
	}

	public void addMember(L1PcInstance pc) {
		if (!_members.contains(pc) && ACTIVE == 1) {
			_members.add(pc);
		}
		if (getMembersCount() == 1 && getPvpDmStatus() == STATUS_NONE && ACTIVE == 1) {
			readyPvpDm();
		}
	}

	public void removeMember(L1PcInstance pc) {
		_members.remove(pc);
	}

	public void clearMembers() {
		_members.clear();
	}
	public void setActive() {
		if (ACTIVE == 0)
		{
			ACTIVE = 1;
		} else {
			ACTIVE = 0;
		}
	}
	public void restoreNames() {
		for (L1PcInstance pc : getMembersArray()) {
			//Integer endxp = (Integer) startxp.get(pc.getName());
			pc.setName((String) members.get(pc));
			pc.wideBroadcastPacket(new S_ChangeName(pc.getId(),(String) members.get(pc)));
			pc.sendPackets(new S_ChangeName(pc.getId(),(String) members.get(pc)));
		}
	}
	public boolean isMember(L1PcInstance pc) {
		return _members.contains(pc);
	}

	public L1PcInstance[] getMembersArray() {
		return _members.toArray(new L1PcInstance[_members.size()]);
	}

	public int getMembersCount() {
		return _members.size();
	}

	private void setPvpDmStatus(int i) {
		_PvpDmStatus = i;
	}

	public int getPvpDmStatus() {
		return _PvpDmStatus;
	}

	private void setWinnersCount(int i) {
		_winnersCount = i;
	}

	public int getWinnersCount() {
		return _winnersCount;
	}

	public void setGoalCount(int i) {
		_goalCount = i;
	}

	public int getGoalCount() {
		return _goalCount;
	}



public class L1PvpDmReadyTimer extends TimerTask {					

	public L1PvpDmReadyTimer() {
	}

	@Override
	public void run() {
		startPvpDm();
		//L1PvpDmTimer hhTimer = new L1PvpDmTimer();
		//hhTimer.begin();
		L1PvpDmTimer PvpDm = new L1PvpDmTimer();
		GeneralThreadPool.getInstance().execute(PvpDm);
	}

	public void begin() {
		Timer timer = new Timer();
		timer.schedule(this, 90000); 
	}

}
/*
public class L1PvpDmTimer extends TimerTask {

	public L1PvpDmTimer() {
	}

	@Override
	public void run() {
		endPvpDm();
		this.cancel();
	}

	public void begin() {
		Timer timer = new Timer();
		timer.schedule(this, 300000); 
	}

}*/

public class L1PvpDmTimer implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 300000; i++) {
				try {
					Thread.sleep(1000);
						//_attacker.sendPackets(new S_PinkName(_attacker.getId(), 300000));
						//_attacker.broadcastPacket(new S_PinkName(_attacker.getId(),
						//	300000));
				} catch (Exception exception) {
					break;
				}
	
				if (getMembersCount() < 2) {
					restoreNames();
					for (L1PcInstance pc : getMembersArray()) {
					L1World.getInstance().broadcastServerMessage(pc.getName() + " won the match!");
					}
					break;
				}
				for (L1PcInstance pc : getMembersArray()) {
					pc.sendPackets(new S_PinkName(pc.getId(), 180));
					pc.wideBroadcastPacket(new S_PinkName(pc.getId(),180));
					if (pc.getMapId() != 509 || pc.getCurrentHp() == 0)
					{
						//pc.setName(members.get(pc));
					}
				}
				/*if (_attacker.getMapId() != 96) {
					_attacker.setPinkName(false);
					break;
				}*/
			removeRetiredMembers();
			}
			endPvpDm();
		}
	}


}
