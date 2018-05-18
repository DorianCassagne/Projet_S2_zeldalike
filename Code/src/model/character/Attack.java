package map.character;

public abstract class Attack {
	
	private int castTime;
	private int effectTime;
	private GameMap attackMap;
	
	public Attack (int castTime,int effectTime, GameMap attackMap) {
		if(castTime < 0 )
			throw new IllegalArgumentException("CastTime must be higher than 0");
		else if(effectTime < 0)
			throw new IllegalArgumentException("EffectTime must be higher than 0");
		else if(map == null)
			throw new IllegalArgumentException("The map must not be null");
		else {
			this.castTime = castTime;
			this.effectTime = effectTime;
			this.attackMap = attackMap;
		}
	}
}
