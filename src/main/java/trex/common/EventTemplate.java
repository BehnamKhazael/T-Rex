//
// This file is part of T-Rex, a Complex Event Processing Middleware.
// See http://home.dei.polimi.it/margara
//
// Authors: Alessandro Margara
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

/**
 * @author Behnam Khazael
 */


package trex.common;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Template of the event to be generated. It is used inside a RulePkt.
 */
public class EventTemplate {
	private int eventType;
	private Collection<EventTemplateAttr> attributes;
	private Collection<EventTemplateStaticAttr> staticAttributes;
	
	public EventTemplate(int eventType) {
		this.eventType = eventType;
		this.attributes = new ArrayList<EventTemplateAttr>();
		this.staticAttributes = new ArrayList<EventTemplateStaticAttr>();
	}

	public EventTemplate() {
	}

	public int getEventType() {
		return eventType;
	}

	public Collection<EventTemplateAttr> getAttributes() {
		return attributes;
	}
	
	public Collection<EventTemplateStaticAttr> getStaticAttributes() {
		return staticAttributes;
	}
	
	public void addAttribute(EventTemplateAttr attr) {
		attributes.add(attr);
	}

	public void addAttribute(String name, OpTree value) {
		EventTemplateAttr attribute =
				new EventTemplateAttr(name, value);
		attributes.add(attribute);
	}
	
	public void addStaticAttribute(EventTemplateStaticAttr attr) {
		staticAttributes.add(attr);
	}

	/**
	 * Returns the number of attributes defined in the template
	 */
	public int getAttributesNum(){
		return attributes.size();
	}

	/**
	 * Returns the number of static attributes defined in the template
	 */
	public int getStaticAttributesNum(){
		return staticAttributes.size();
	}

	/**
	 * Copies the name of the attribute with the given index
	 */
	public void getAttributeName(String name, int index)
	{
		((EventTemplateAttr)attributes.toArray()[index]).getName();
	}

	/**
	 * Copies the name of the static attribute with the given index
	 */
	public void getStaticAttributeName(char name, int index){}

	/**
	 * Copies the static attribute with the given index  in pAtt
	 */
	public void getStaticAttribute(Attribute pAtt, int index){}

	/**
	 * Returns the name of the attribute with the given index
	 */
	public String getAttributeName(int index){
		return ((EventTemplateAttr)attributes.toArray()[index]).getName();
	}

	/**
	 * Returns the operation tree for the attribute with the given index
	 */
	public OpTree getAttributeTree(int index){
		return ((EventTemplateAttr)attributes.toArray()[index]).getValue();
	}

	/**
	 * Creates an exact copy (deep copy) of the data structure
	 */
	public EventTemplate dup(){
		EventTemplate dup = new EventTemplate(eventType);
		for (EventTemplateAttr it : attributes) {
			EventTemplateAttr attrDup = it.dup();
			dup.addAttribute(attrDup);
		}
		for (EventTemplateStaticAttr it : staticAttributes) {
			EventTemplateStaticAttr attrDup = it.dup();
			dup.addStaticAttribute(attrDup);
		}
		return dup;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (! (obj instanceof EventTemplate)) return false;
		EventTemplate other = (EventTemplate) obj;
		if (! attributes.containsAll(other.attributes)) return false;
		if (! other.attributes.containsAll(attributes)) return false;
		if (eventType != other.eventType) return false;
		return true;
	}

	@Override
	public String toString(){
		return " Event Template: {" +
				"Event Type (Name!): " + this.getEventType() +
				", Event Template Attributes: {" + this.getAttributes().toString() + "}" +
				", Event Template Static Attributes: {" + this.getStaticAttributes() + "}" +
				"}";
	}
}
