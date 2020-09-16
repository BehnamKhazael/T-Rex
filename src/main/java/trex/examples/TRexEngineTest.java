package trex.examples;

import trex.marshalling.Unmarshaller;
import trex.packets.PubPkt;
import trexengine.ResultListener;
import trexengine.TRexEngine;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Behnam Khazael
 */
public class TRexEngineTest {
    static String teslaRule;
    public static void main(String args[]){
        testEngine();
    }
    static void testEngine(){
        TRexEngine engine = new TRexEngine(1);

        //Creating a Rule to submitted to the engine this could potentially happen via the network by marshalling.
        RuleR1 testRule = new RuleR1();

        //Adding the rule to the engine.
        engine.processRulePkt(testRule.buildRule());
        //Create a thread to handle incoming events.
        engine.finalizing();

        //Adding result listener to inform us about the detected complex event (Subscribing)
        ResultListener listener= new ResultListener(testRule.buildSubscription());
        //Addd the listener to the engine.
        engine.addResultListener(listener);

        //Creating some patterns of events toward the engine
        int i = 0;
        //Publications for rule 1. please check the method for more information.
        ArrayList<byte[]> pubPkts= testRule.buildPublication();
        for (int c = 0; i< 100 ; c++) {
            for (byte[] it :
                    pubPkts) {
                PubPkt pubPkt = (PubPkt) Unmarshaller.unmarshal(it);
                System.out.println("Sending Packet #" + i);
                System.out.println(pubPkt.toString());
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pubPkt.setTimeStamp(System.currentTimeMillis());
                engine.processPubPkt(pubPkt);
                i++;
            }
        }

    }
}
