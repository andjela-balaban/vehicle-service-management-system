package org.unibl.etf.chat.test;

import org.unibl.etf.dto.ChatMessage;
import org.unibl.etf.dto.MessageListener;

public class ConsoleMessageListener implements MessageListener {
    @Override
    public void onMessage(ChatMessage msg) {
        System.out.println("[UNICAST from " + msg.getSenderId() + "]: " + msg.getContent());
    }

    @Override
    public void onMulticast(String rawMsg) {
        String senderId = rawMsg.split("#")[0];
        String content = rawMsg.split("#")[2];
        System.out.println("[MULTICAST from " + senderId + "] " + content);
    }
}
