//
// This file is part of T-Rex, a Complex Event Processing Middleware.
// See http://home.dei.polimi.it/margara
//
// Authors: Francesco Feltrinelli
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

import trex.packets.TRexPkt;
import trex.utils.CollectionUtils;
import trex.utils.MutableInt;



/**
 * A buffered {@link TRexPkt}'s unmarshaller. This is suitable where the total number
 * of bytes is not known a priori and bytes are given to this decoder little by little,
 * as in network transfers. If you already have all the bytes to be decoded, use
 * {@link TRexPkt Unmarshaller}, which is more efficient.
 * 
 * This uses {@link TRexPkt Unmarshaller}, which is compatible with the encoding
 * used in {@link TRexPkt tMarshaller}. As {@link TRexPkt Marshaller}, this supposes
 * that given bytes are logically valid (there are no byte errors, they belong to a 
 * proper continous stream of {@link TRexPkt}s, and so on): no validity checks are
 * done apart from {@link TRexPkt}'s length and the result would be unpredictable.
 * 
 * @author Francesco Feltrinelli
 *
 */
public class BufferedPacketUnmarshaller {
	
	private byte[] buffer;
	
	public BufferedPacketUnmarshaller(){
		buffer= new byte[0];
	}
	
	/**
	 * Copies the given bytes to internal buffer and tries to decode from all the bytes
	 * in the buffer as many {@link TRexPkt}s as possible. Remaining bytes are left
	 * in the buffer for next time this is called. 
	 * If the bytes are not enough to decode a packet an empty array is returned.
	 * The given byte array is not changed.
	 * 
	 * @param pktBytes the array of bytes from which bytes will be copied
	 * @param start the starting offset in the array of bytes
	 * @param length the number of bytes to be copied from the array
	 * @return the (possibly empty) array of decoded {@link TRexPkt}s
	 * @throws ArrayIndexOutOfBoundsException if an illegal start or length
	 * is specified
	 */
	public TRexPkt[] unmarshal(byte[] pktBytes, int start, int length){
		if (start<0 || start+length>pktBytes.length) 
			throw new ArrayIndexOutOfBoundsException();
		
		buffer= CollectionUtils.concat(buffer, pktBytes, start, length);
		ArrayList<TRexPkt> pkts= new ArrayList<TRexPkt>();
		
		TRexPkt pkt= null;
		MutableInt offset= new MutableInt(0);
		do {
			pkt= Unmarshaller.unmarshal(buffer, offset);
			if (pkt != null){
				pkts.add(pkt);
				// Remove decoded bytes from buffer
				buffer= CollectionUtils.subset(buffer, offset.get(), buffer.length-offset.get());
				offset.setValue(0);
			}
		} while (pkt != null);
		
		return pkts.toArray(new TRexPkt[pkts.size()]);
	}
	
	/**
	 * Equivalent of {@link #unmarshal(byte[], int, int)} where all the bytes of
	 * the given array are taken for decoding.
	 */
	public TRexPkt[] unmarshal(byte[] pktBytes){
		return unmarshal(pktBytes, 0, pktBytes.length);
	}
	
	/**
	 * Clear internal buffer.
	 */
	public void clear(){
		buffer= new byte[0];
	}
}