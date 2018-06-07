package model.event.action;

import model.event.ElementNames;

public class TypeAction {
	public enum ItemEnum implements ElementNames{
		AXE,
		LANCERAVANCE,
		LANCER,
		EPEE,
		EPEEAVANCE,
		EPEEAVANCE2,
		ARC,
		EPEEAVANCE3,
		EPEEBASIQUE,
		EPEEMOYENNE,
		ARCAVANCE,
		ARCBASIQUE,
		MINER,
		EQUIP1,
		EQUIP2,
		EQUIP5;

		@Override
		public String getValue() {
			return this.name();
		}
		
	
		
		
	}
	
	public enum AttackEnum implements ElementNames{
		NOTYETIMPLEMENTED;

		@Override
		public String getValue() {
			return this.name();
		}
	}
	
}
