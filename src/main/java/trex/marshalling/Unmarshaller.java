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

package trex.marshalling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


import trex.common.*;
import trex.common.Consts.AggregateFun;
import trex.common.Consts.CompKind;
import trex.common.Consts.ConstraintOp;
import trex.common.Consts.Op;
import trex.common.Consts.OpTreeType;
import trex.common.Consts.StateType;
import trex.common.Consts.ValType;
import trex.common.Consts.ValRefType;
import trex.packets.*;
import trex.packets.TRexPkt.PacketType;
import trex.packets.TRexPkt.PktType;
import trex.utils.MutableInt;

import static trex.common.Consts.StateType.AGG;
import static trex.common.Consts.ValRefType.RULEPKT;
import static trex.common.Consts.ValRefType.STATIC;




public class Unmarshaller {

	public static PubPkt decodePubPkt(byte[] source) {
		IndexWrapper index = new IndexWrapper();
		return decodePubPkt(source, index);
	}

	public static RulePkt decodeRulePkt(byte[] source) {
		IndexWrapper index = new IndexWrapper();
		return decodeRulePkt(source, index);
	}

	public static SubPkt decodeSubPkt(byte[] source) {
		IndexWrapper index = new IndexWrapper();
		return decodeSubPkt(source, index);
	}

	public static UnPubPkt decodePubRegisterPkt(byte[] source) {
		IndexWrapper index = new IndexWrapper();
		return decodePubRegisterPkt(source, index);
	}
	
	public static AdvPkt decodeAdvPkt(byte[] source) {
		IndexWrapper index = new IndexWrapper();
		return decodeAdvPkt(source, index);
	}
	
	public static JoinPkt decodeJoinPkt(byte[] source) {
		IndexWrapper index = new IndexWrapper();
		return decodeJoinPkt(source, index);
	}

	public static PktType decodePktType(byte[] source) {
		IndexWrapper index = new IndexWrapper();
		return decodePktType(source, index);
	}

	public static int decodeInt(byte[] source) {
		IndexWrapper index = new IndexWrapper();
		return decodeInt(source, index);
	}

	protected static RulePkt decodeRulePkt(byte[] source, IndexWrapper index) {
		/**
		 * RuleId#
		 */
		Integer ruleId = decodeInt(source, index);
		HashMap<Integer, EventPredicate> predicates = decodeEventPredicateMap(source, index);
		HashMap<Integer, ComplexParameter> parameters = decodeParameterMap(source, index);
		HashMap<Integer, TAggregate> aggregates = decodeAggregateMap(source, index);
		HashMap<Integer, Negation> negations = decodeNegationMap(source, index);
		EventTemplate eventTemplate = decodeEventTemplate(source, index);
		Collection<Integer> consuming = decodeIntegerCollection(source, index);
		RulePkt pkt = new RulePkt(eventTemplate);
		pkt.setRuleId(ruleId);
		for (Integer key : predicates.keySet()) {
			pkt.addPredicate(predicates.get(key));
		}
		for (Integer key : parameters.keySet()) {
			pkt.addParameter(parameters.get(key));
		}
		for (Integer key : aggregates.keySet()) {
			pkt.addAggregate(aggregates.get(key));
		}
		for (Integer key : negations.keySet()) {
			pkt.addNegation(negations.get(key));
		}
		for (Integer cons : consuming) {
			pkt.addConsuming(cons);
		}
		return pkt;
	}

	protected static PubPkt decodePubPkt(byte[] source, IndexWrapper index) {
		int eventType = decodeInt(source, index);
		long timeStamp = decodeLong(source, index);
		Collection<Attribute> attributes = decodeAttributeCollection(source, index);
		PubPkt pkt = new PubPkt(eventType, timeStamp);
		for (Attribute att : attributes) {
			pkt.addAttribute(att);
		}
		return pkt;
	}

	protected static SubPkt decodeSubPkt(byte[] source, IndexWrapper index) {
		int eventType = decodeInt(source, index);
		Collection<Constraint> constraints = decodeConstraintCollection(source, index);
		SubPkt pkt = new SubPkt(eventType);
		for (Constraint constr : constraints) {
			pkt.addConstraint(constr);
		}
		return pkt;
	}

	protected static UnPubPkt decodePubRegisterPkt(byte[] source, IndexWrapper index) {
		int eventType = decodeInt(source, index);
		Collection<Attribute> attributes = decodeAttributeCollection(source, index);
		UnPubPkt pkt = new UnPubPkt(eventType);
		for (Attribute attr : attributes) {
			pkt.addAttribute(attr);
		}
		return pkt;
	}
	
	protected static AdvPkt decodeAdvPkt(byte[] source, IndexWrapper index) {
		Collection<Integer> advertisements = decodeIntegerCollection(source, index);
		AdvPkt pkt = new AdvPkt(advertisements);
		return pkt;
	} 

	protected static JoinPkt decodeJoinPkt(byte[] source, IndexWrapper index) {
		long address = decodeLong(source, index);
		int port = decodeInt(source, index);
		return new JoinPkt(address, port);
	} 
	
	protected static Constraint decodeConstraint(byte[] source, IndexWrapper index) {
		String name = decodeString(source, index);
		ConstraintOp op = decodeConstraintOp(source, index);
		ValType type = decodeValType(source, index);
		if (type==ValType.INT) {
			int val = decodeInt(source, index);
			return new Constraint(name, op, val);
		} else if (type==ValType.FLOAT) {
			float val = decodeFloat(source, index);
			return new Constraint(name, op, val);
		} else if (type==ValType.BOOL) {
			boolean val = decodeBoolean(source, index);
			return new Constraint(name, op, val);
		} else {
			String val = decodeString(source, index);
			return new Constraint(name, op, val);
		}
	}

	protected static Attribute decodeAttribute(byte[] source, IndexWrapper index) {
		String name = decodeString(source, index);
		ValType type = decodeValType(source, index);
		if (type==ValType.INT) {
			int val = decodeInt(source, index);
			return new Attribute(name, val);
		} else if (type==ValType.FLOAT) {
			float val = decodeFloat(source, index);
			return new Attribute(name, val);
		} else if (type==ValType.BOOL) {
			boolean val = decodeBoolean(source, index);
			return new Attribute(name, val);
		} else {
			String val = decodeString(source, index);
			return new Attribute(name, val);
		}
	}

	protected static EventPredicate decodeEventPredicate(byte[] source, IndexWrapper index) {
		int eventType = decodeInt(source, index);
		Collection<Constraint> constraints = decodeConstraintCollection(source, index);
		int refersTo = decodeInt(source, index);
		long win = decodeLong(source, index);
		CompKind kind = decodeCompKind(source, index);
		return new EventPredicate(eventType, constraints, refersTo, win, kind);
	}

	protected static ComplexParameter decodeParameter(byte[] source, IndexWrapper index) {
		//TODO
		ConstraintOp op = decodeConstraintOp(source, index);
		StateType sType = decodeStateType(source, index);
		ValType vType = decodeValType(source, index);
		OpTree lT = decodeOpTree(source, index);
		OpTree rT = decodeOpTree(source, index);
		return new ComplexParameter(op, sType, vType, lT, rT);
	}

	protected static Negation decodeNegation(byte[] source, IndexWrapper index) {
		int evType = decodeInt(source, index);
		Collection<Constraint> constraints = decodeConstraintCollection(source, index);
		int lowerId = decodeInt(source, index);
		long lowerTime = decodeLong(source, index);
		int upperId = decodeInt(source, index);
		Negation neg = null;
		if (lowerId>=0) neg = new Negation(evType, lowerId, upperId);
		else neg = new Negation(evType, upperId, lowerTime);
		for (Constraint c : constraints) {
			neg.addConstraint(c);
		}
		return neg;
	}

	protected static TAggregate decodeAggregate(byte[] source, IndexWrapper index) {
		int evType = decodeInt(source, index);
		Collection<Constraint> constraints = decodeConstraintCollection(source, index);
		int lowerId = decodeInt(source, index);
		long lowerTime = decodeLong(source, index);
		int upperId = decodeInt(source, index);
		AggregateFun fun = decodeAggregateFun(source, index);
		String name = decodeString(source, index);
		TAggregate agg = null;
		if (lowerId>=0) agg = new TAggregate(evType, lowerId, upperId, fun, name);
		else agg = new TAggregate(evType, lowerTime, upperId, fun, name);
		for (Constraint c : constraints) {
			agg.addConstraint(c);
		}
		return agg;
	}

	protected static EventTemplate decodeEventTemplate(byte[] source, IndexWrapper index) {
		int eventType = decodeInt(source, index);
		Collection<EventTemplateAttr> attributes = decodeEventTemplateAttrCollection(source, index);
		EventTemplate eventTemplate = new EventTemplate(eventType);
		for (EventTemplateAttr att : attributes) {
			eventTemplate.addAttribute(att);
		}
		return eventTemplate;
	}

	protected static EventTemplateAttr decodeEventTemplateAttr(byte[] source, IndexWrapper index) {
		String name = decodeString(source, index);
		OpTree value = decodeOpTree(source, index);
		return new EventTemplateAttr(name, value);
	}

	protected static OpTree decodeOpTree(byte[] source, IndexWrapper index) {
		OpTreeType type = decodeOpTreeType(source, index);
		ValType valType = decodeValType(source, index);
		if (type == OpTreeType.LEAF) {
			OpValueReference val = decodeValueReference(source, index);
			return new OpTree(val, valType);
		} else {
			OpTree leftTree = decodeOpTree(source, index);
			OpTree rightTree = decodeOpTree(source, index);
			Op op = decodeOp(source, index);
			return new OpTree(leftTree, rightTree, op, valType);
		}
	}

	protected static RulePktValueReference decodeRulePktReference(byte[] source, IndexWrapper index) {
		int idx = decodeInt(source, index);
		StateType sType = decodeStateType(source, index);
		String name = decodeString(source, index);
		// If name > 0 => it's an agg, but as a parameter,
		// so that we need also an attribute name!
		if (sType == AGG && name.length() == 0) {
			return new RulePktValueReference(idx);
		} else {
			return new RulePktValueReference(idx, sType, name);
		}
	}

	protected static OpValueReference decodeValueReference(byte[] source, IndexWrapper index){
		ValRefType  vrtype = decodeValRefType(source, index);
		if (vrtype == RULEPKT)
			return decodeRulePktReference(source, index);
		else
    /*if (vrtype == STATIC)*/ return decodeStaticValueReference(source, index);
	}

	protected static ValRefType decodeValRefType(byte[] source, IndexWrapper index) {
		ValRefType result;
		if (source[index.get()] == 0)
			result = RULEPKT;
		else
    /*if (source[index]==1)*/ result = STATIC;
		index.inc();
		return result;
	}

	protected static StaticValueReference decodeStaticValueReference(byte[] source, IndexWrapper index){
		ValType type = decodeValType(source, index);
		if (type == ValType.INT)
			return new StaticValueReference(decodeInt(source, index));
		else if (type == ValType.FLOAT)
			return new StaticValueReference(decodeFloat(source, index));
		else if (type == ValType.BOOL)
			return new StaticValueReference(decodeBoolean(source, index));
		else
    /*if (type==STRING)*/ return new StaticValueReference(
					decodeString(source, index));
	}



	protected static boolean decodeBoolean(byte[] source, IndexWrapper index) {
		boolean returnValue = (source[index.get()] == 1);
		index.inc();
		return returnValue;
	}

	protected static int decodeInt(byte[] source, IndexWrapper index) {
		int returnValue = (0xff & source[index.get()+0]) << 24 |
		(0xff & source[index.get()+1]) << 16 |
		(0xff & source[index.get()+2]) << 8  |
		(0xff & source[index.get()+3]) << 0;
		index.inc(4);
		return returnValue;		
	}
	
	protected static float decodeFloat(byte[] source, IndexWrapper index) {
		int intVal = decodeInt(source, index);
		return Float.intBitsToFloat(intVal);
	}

	protected static long decodeLong(byte[] source, IndexWrapper index) {
		long returnValue = (long)(0xff & source[index.get()+0]) << 56 |
		(long)(0xff & source[index.get()+1]) << 48 |
		(long)(0xff & source[index.get()+2]) << 40 |
		(long)(0xff & source[index.get()+3]) << 32	|
		(long)(0xff & source[index.get()+4]) << 24 |
		(long)(0xff & source[index.get()+5]) << 16 |
		(long)(0xff & source[index.get()+6]) << 8	|
		(long)(0xff & source[index.get()+7]) << 0;
		index.inc(8);
		return returnValue;
	}

	protected static String decodeString(byte[] source, IndexWrapper index) {
		int length = decodeInt(source, index);
		char[] charArray = new char[length];
		for (int i=0; i<length; i++) {
			charArray[i] = (char) source[index.get()+i];
		}
		String returnValue = String.valueOf(charArray);
		index.inc(length);
		return returnValue;
	}

	protected static CompKind decodeCompKind(byte[] source, IndexWrapper index) {
		int pos = index.get();
		index.inc();
		if (source[pos]==0) return CompKind.EACH_WITHIN;
		else if (source[pos]==1) return CompKind.FIRST_WITHIN;
		else if (source[pos]==2) return CompKind.LAST_WITHIN;
		else return CompKind.ALL_WITHIN;
	}

	protected static ConstraintOp decodeConstraintOp(byte[] source, IndexWrapper index) {
		int pos = index.get();
		index.inc();
		if (source[pos]==0) return ConstraintOp.EQ;
		else if (source[pos]==1) return ConstraintOp.LT;
		else if (source[pos]==2) return ConstraintOp.GT;
		else if (source[pos]==3) return ConstraintOp.NE;
		else if (source[pos]==4) return ConstraintOp.IN;
		else if (source[pos]==5) return ConstraintOp.LE;
		else if (source[pos]==6) return ConstraintOp.GE;
		else return ConstraintOp.IN;
	}

	protected static StateType decodeStateType(byte[] source, IndexWrapper index) {
		int pos = index.get();
		index.inc();
		if (source[pos]==0) return StateType.STATE;
		else if (source[pos]==1) return StateType.NEG;
		else return AGG;
	}

	protected static AggregateFun decodeAggregateFun(byte[] source, IndexWrapper index) {
		int pos = index.get();
		index.inc();
		if (source[pos]==0) return AggregateFun.NONE;
		else if (source[pos]==1) return AggregateFun.AVG;
		else if (source[pos]==2) return AggregateFun.COUNT;
		else if (source[pos]==3) return AggregateFun.MIN;
		else if (source[pos]==4) return AggregateFun.MAX;
		else return AggregateFun.SUM;
	}

	protected static OpTreeType decodeOpTreeType(byte[] source, IndexWrapper index) {
		int pos = index.get();
		index.inc();
		if (source[pos]==0) return OpTreeType.LEAF;
		else return OpTreeType.INNER;
	}

	protected static Op decodeOp(byte[] source, IndexWrapper index) {
		int pos = index.get();
		index.inc();
		if (source[pos]==0) return Op.ADD;
		else if (source[pos]==1) return Op.SUB;
		else if (source[pos]==2) return Op.MUL;
		else if (source[pos]==3) return Op.DIV;
		else if (source[pos]==4) return Op.AND;
		else return Op.OR;
	}

	protected static PktType decodePktType(byte[] source, IndexWrapper index) {
		int pos = index.get();
		index.inc();
		if (source[pos]==0) return PktType.PUB_PKT;
		else if (source[pos]==1) return PktType.RULE_PKT;
		else if (source[pos]==2) return PktType.SUB_PKT; 
		else if (source[pos]==3) return PktType.ADV_PKT;
		else if (source[pos]==5) return PktType.UNPUB_PKT;
		else return PktType.JOIN_PKT;
	}
	
	protected static ValType decodeValType(byte[] source, IndexWrapper index) {
		int pos = index.get();
		index.inc();
		if (source[pos]==0) return ValType.INT;
		else if (source[pos]==1) return ValType.FLOAT;
		else if (source[pos]==2) return ValType.BOOL; 
		else return ValType.STRING;
	}

	protected static HashMap<Integer, EventPredicate> decodeEventPredicateMap(byte[] source, IndexWrapper index) {
		HashMap<Integer, EventPredicate> map = new HashMap<Integer, EventPredicate>();
		int length = decodeInt(source, index);
		for (int i=0; i<length; i++) {
			int key = decodeInt(source, index);
			EventPredicate value = decodeEventPredicate(source, index);
			map.put(key, value);
		}
		return map;
	}

	protected static HashMap<Integer, ComplexParameter> decodeParameterMap(byte[] source, IndexWrapper index) {
		HashMap<Integer, ComplexParameter> map = new HashMap<Integer, ComplexParameter>();
		int length = decodeInt(source, index);
		for (int i=0; i<length; i++) {
			int key = decodeInt(source, index);
			ComplexParameter value = decodeParameter(source, index);
			map.put(key, value);
		}
		return map;
	}

	protected static HashMap<Integer, Negation> decodeNegationMap(byte[] source, IndexWrapper index) {
		HashMap<Integer, Negation> map = new HashMap<Integer, Negation>();
		int length = decodeInt(source, index);
		for (int i=0; i<length; i++) {
			int key = decodeInt(source, index);
			Negation value = decodeNegation(source, index);
			map.put(key, value);
		}
		return map;
	}

	protected static HashMap<Integer, TAggregate> decodeAggregateMap(byte[] source, IndexWrapper index) {
		HashMap<Integer, TAggregate> map = new HashMap<Integer, TAggregate>();
		int length = decodeInt(source, index);
		for (int i=0; i<length; i++) {
			int key = decodeInt(source, index);
			TAggregate value = decodeAggregate(source, index);
			map.put(key, value);
		}
		return map;
	}

	protected static Collection<Integer> decodeIntegerCollection(byte[] source, IndexWrapper index) {
		int length = decodeInt(source, index);
		Collection<Integer> returnColl = new ArrayList<Integer>();
		for (int i=0; i<length; i++) {
			returnColl.add(decodeInt(source, index));
		}
		return returnColl;
	}

	protected static Collection<Constraint> decodeConstraintCollection(byte[] source, IndexWrapper index) {
		int length = decodeInt(source, index);
		Collection<Constraint> returnColl = new ArrayList<Constraint>();
		for (int i=0; i<length; i++) {
			returnColl.add(decodeConstraint(source, index));
		}
		return returnColl;
	}

	protected static Collection<Attribute> decodeAttributeCollection(byte[] source, IndexWrapper index) {
		int length = decodeInt(source, index);
		Collection<Attribute> returnColl = new ArrayList<Attribute>();
		for (int i=0; i<length; i++) {
			returnColl.add(decodeAttribute(source, index));
		}
		return returnColl;
	}

	protected static Collection<EventTemplateAttr> decodeEventTemplateAttrCollection(byte[] source, IndexWrapper index) {
		int length = decodeInt(source, index);
		Collection<EventTemplateAttr> returnColl = new ArrayList<EventTemplateAttr>();
		for (int i=0; i<length; i++) {
			returnColl.add(decodeEventTemplateAttr(source, index));
		}
		return returnColl;
	}

	public static class IndexWrapper {
		private int index;

		public IndexWrapper() {
			index = 0;
		}

		int get() {
			return index;
		}

		public void inc(int val) {
			index+=val;
		}

		void inc() {
			inc(1);
		}
	}
	
	public static PubPkt decodePubPkt(byte[] source, int startIndex) {
		IndexWrapper index = new IndexWrapper();
		index.inc(startIndex);
		PubPkt trexPubPkt= decodePubPkt(source, index);
		return new PubPkt(trexPubPkt);
		//return (PubPkt) trexPubPkt;
	}
	
	public static RulePkt decodeRulePkt(byte[] source, int startIndex) {
		IndexWrapper index = new IndexWrapper();
		index.inc(startIndex);
		RulePkt trexRulePkt = decodeRulePkt(source, index);
		//return new RulePkt(trexRulePkt);
		/**
		 * @Behnam:
		 * I changed this part of code because on unmarshalling new instance creation for RulePkt results in lost of
		 * event predicates.
		 */
		return (trexRulePkt);
	}
	
	public static SubPkt decodeSubPkt(byte[] source, int startIndex) {
		IndexWrapper index = new IndexWrapper();
		index.inc(startIndex);
		SubPkt trexSubPkt= decodeSubPkt(source, index);
		return new SubPkt(trexSubPkt);
	}

	public static UnPubPkt decodePubRegisterPkt(byte[] source, int startIndex) {
		IndexWrapper index = new IndexWrapper();
		index.inc(startIndex);
		UnPubPkt trexUnPubPkt = decodePubRegisterPkt(source, index);
		return new UnPubPkt(trexUnPubPkt);
	}
	
	public static UnSubPkt decodeUnSubPkt(byte[] source, int startIndex) {
		// skip SubPkt's type and length
		startIndex += Marshaller.BYTENUM_PKTTYPE + Marshaller.BYTENUM_PKTLENGTH;
		SubPkt subPkt= decodeSubPkt(source, startIndex);
		return new UnSubPkt(subPkt);
	}
	
	public static PingPkt decodePingPkt(byte[] source, int startIndex){
		return new PingPkt();
	}
	
	public static PacketType decodePktType(byte[] source, int startIndex){
		return PacketType.fromValue(source[startIndex]);
	}
	
	public static int decodePktLength(byte[] source, int startIndex){
		IndexWrapper index= new IndexWrapper();
		index.inc(startIndex);
		return decodeInt(source, index);
	}

/**
 * Tries to unmarshal a {@link TRexPkt} from the given (supposed valid) byte array.
 * If the bytes are not enough to decode the packet completely, {@code null}
 * is returned.
 * 
 * @param buffer the bytes' buffer
 * @param offset the index on the given buffer where to start the decoding from;
 * this is a {@link MutableInt} so that the caller at the end can know how many 
 * bytes of the buffer were actually read
 * @return the decoded packet or {@code null} if the bytes were not enough to
 * decode it completely
 */
public static TRexPkt unmarshal(byte[] buffer, MutableInt offset){
	// Try to decode packet type
	if (buffer.length-offset.get() < Marshaller.BYTENUM_PKTTYPE) return null;
	PacketType type = decodePktType(buffer, offset.get());
	offset.add(Marshaller.BYTENUM_PKTTYPE);
	
	// Try to decode packet length
	if (buffer.length-offset.get() < Marshaller.BYTENUM_PKTLENGTH) return null;
	int length = decodePktLength(buffer, offset.get());
	offset.add(Marshaller.BYTENUM_PKTLENGTH);
	
	// Try to decode packet body
	if (buffer.length-offset.get() < length) return null;
	TRexPkt pkt = null;
	switch (type) {
	case PUB_PACKET: 
		pkt= decodePubPkt(buffer, offset.get());
		break;
	case RULE_PACKET:
		pkt= decodeRulePkt(buffer, offset.get());
		break;
	case SUB_PACKET: 
		pkt= decodeSubPkt(buffer, offset.get());
		break;
	case UNPUB_PKT:
		pkt= decodePubRegisterPkt(buffer, offset.get());
		break;
	case UNSUB_PACKET: 
		pkt= decodeUnSubPkt(buffer, offset.get());
		break;
	case PING_PACKET: 
		pkt= decodePingPkt(buffer, offset.get());
		break;
	}
	offset.add(length);
	
	return pkt;
}

/**
 * As {@link #unmarshal(byte[], MutableInt)} with 0 as start index.
 */
public static TRexPkt unmarshal(byte[] buffer){
	return unmarshal(buffer, new MutableInt(0));
}
}
