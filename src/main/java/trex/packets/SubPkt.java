//
// This file is part of T-Rex, a Complex Event Processing Middleware.
// See http://home.dei.polimi.it/margara
//
// Authors: Alessandro Margara, Francesco Feltrinelli, Daniele Rogora
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see http://www.gnu.org/licenses/.
//

package trex.packets;

import java.util.ArrayList;
import java.util.Collection;

import trex.common.Attribute;
import trex.common.Constraint;
import trex.common.Matcher;


public class SubPkt implements TRexPkt {
	private int eventType;
	private Matcher matcher;
	protected Collection<Constraint> constraints = new ArrayList<Constraint>();
	private float nodeAddress;
	
	public SubPkt(int eventType) {
		this.matcher = null;
		this.eventType = eventType;
		constraints = new ArrayList<Constraint>();
	}
	
	public SubPkt(int eventType, Collection<Constraint> constr) {
		this.matcher = null;
		this.eventType = eventType;
		constraints = new ArrayList<Constraint>(constr);
	}
	
	public SubPkt(int eventType, Collection<Constraint> constr, Matcher pMatcher) {
		this.matcher = pMatcher;
		this.eventType = eventType;
		constraints = new ArrayList<Constraint>(constr);
	}

	public SubPkt(int eventType, Collection<Constraint> constr, Matcher pMatcher, float nodeAddress) {
		this.matcher = pMatcher;
		this.eventType = eventType;
		constraints = new ArrayList<Constraint>(constr);
		this.nodeAddress = nodeAddress;
	}
	
	public SubPkt(SubPkt trexSubPkt){
		this(trexSubPkt.getEventType(), trexSubPkt.getConstraints(), trexSubPkt.getCustomMatcher());
	}
	
	public void setCustomMatcher(Matcher m) {
		this.matcher = m;
	}
	
	protected Matcher getCustomMatcher() {
		return this.matcher;
	}

	public SubPkt(int parEventType, Constraint[] parConstraints,
				   int parConstraintsNum) {
		eventType = parEventType;
		int constraintsNum = parConstraintsNum;
		for (Constraint i :
				parConstraints) {
			constraints.add(i);
		}
	}
	
	/**
	 * This method should be invoked only if the subscription has a custom matcher associated; in this case it is necessary to redo the
	 * post-filtering process already done by the server, in order to link the pubpkt with the subscription to which it was addressed
	 * @param pkt
	 * @return 0 if this wasn't the subscription to which the pubpkt was addressed
	 * 		   -1 if this was the subscription to which the pubpkt was addressed but the custom matcher failed
	 *  		1 if this was the subscription to which the pubpkt was addressed and the custom matcher succeeded
	 */
	public int match(PubPkt pkt) {
		//first I must match event type
		if (this.eventType != pkt.getEventType()) return 0;
		//Then constraints
		Collection<Attribute> attrs = pkt.getAttributes();
		//Here comes a list of switches that handle all the value types and the operators.
		//This is just like the one found in the TRexServer project, in the TRexUtils.cpp file
		for (Constraint constr: this.constraints) {
			for (Attribute at: attrs) {
			   if (constr.getName().equals(at.getName())) {
				   switch (constr.getValType()) {
					   case INT:
						   switch (constr.getOp()) {
						   	case EQ:
						   		if (constr.getIntVal()!=at.getIntVal()) return 0;
						   		
						   	case NE:
						   		if (constr.getIntVal()==at.getIntVal()) return 0;
						   		
						   	case GT:
						   		if (constr.getIntVal()<=at.getIntVal()) return 0;
						   		
						   	case LT:
						   		if (constr.getIntVal()>=at.getIntVal()) return 0;
							
						   	case LE:
						   		if (constr.getIntVal()>at.getIntVal()) return 0;
						   		
							case GE:
						   		if (constr.getIntVal()<at.getIntVal()) return 0;
						   	default:
						   		break;
						   }
						   break;
						   
					   case FLOAT:
						   switch (constr.getOp()) {
						   	case EQ:
						   		if (constr.getFloatVal()!=at.getFloatVal()) return 0;
						   		
						   	case NE:
						   		if (constr.getFloatVal()==at.getFloatVal()) return 0;
						   		
						   	case GT:
						   		if (constr.getFloatVal()<=at.getFloatVal()) return 0;
						   		
						   	case LT:
						   		if (constr.getFloatVal()>=at.getFloatVal()) return 0;
						   		
						   	case LE:
						   		if (constr.getFloatVal()>at.getFloatVal()) return 0;
						   		
						   	case GE:
						   		if (constr.getFloatVal()<at.getFloatVal()) return 0;
						   		
						   	default:
						   		break;
						   }
						   break;
						   
					   case BOOL:
						   switch (constr.getOp()) {
						   	case EQ:
						   		if (constr.getBoolVal()!=at.getBoolVal()) return 0;
						   		
						   	case NE:
						   		if (constr.getBoolVal()==at.getBoolVal()) return 0;
						   		
						   	default:
						   		break;
						   }
						   break;
						   
					   case STRING:
						   switch (constr.getOp()) {
						   	case EQ:
						   		if (!constr.getStringVal().equals(at.getStringVal())) return 0;
						   		
						   	case NE:
						   		if (constr.getStringVal().equals(at.getStringVal())) return 0;
						   		
						   	//Not defined for strings
						   	case GT:
						   		return 0;
						   	
						   	//Not defined for strings	
						   	case LT:
						   		return 0;
						   		
						   	case IN:
						   		//FROM SERVER CODE, TRexUtils.cpp
						   		// The constraint's value should be a substring of the attribute's value:
								// it is a filter specified for published events' attributes
						   		if (at.getStringVal().indexOf(constr.getStringVal()) < 0) return 0;
						   		
						   	default:
						   		break;
						   }
						   break;
						   
					   default:
						   break;
			   }
			   }
			}
		}
		//And finally the custom matcher
//		if (matcher.match(pkt)) return 1;
//		else
		return -1;
	}
	
	public boolean hasCustomMatcher() {
		return (this.matcher!=null);
	}
	
	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public Collection<Constraint> getConstraints() {
		return constraints;
	}

	public void addConstraint(Constraint constraint) {
		constraints.add(constraint);
	}

	public String toString(){

		//// TODO: 1/30/2020
		String a= "";
		for (Constraint b :
				constraints) {
			a = a + (b.toString());
		}
		return "SubscriptionPacket:{" +
				"EventType:{" + this.eventType + "}" +
				",Constraint:{" + a + "}" +
				"}";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (! (obj instanceof SubPkt)) return false;
		SubPkt other = (SubPkt) obj;
		if (eventType != other.eventType) return false;
		if (! constraints.containsAll(other.constraints)) return false;
		if (! other.constraints.containsAll(constraints)) return false;
		return true;
	}
}
