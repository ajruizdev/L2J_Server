/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.l2j.gameserver.skills.conditions;

import javolution.util.FastList;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.skills.Env;

public class ConditionPlayerClassIdRestriction extends Condition
{
	private final FastList<Integer> _classIds;

	public ConditionPlayerClassIdRestriction(FastList<Integer> classId)
	{
		_classIds = classId;
	}

	@Override
	public boolean testImpl(Env env)
	{
		if (!(env.player instanceof L2PcInstance))
			return false;
		L2PcInstance player = (L2PcInstance)env.player;
		if (!player.isSubClassActive())
			return _classIds.contains(player.getClassId().getId());
		else
			return _classIds.contains(player.getSubClasses().get(player.getClassIndex()).getClassId());
	}
}
