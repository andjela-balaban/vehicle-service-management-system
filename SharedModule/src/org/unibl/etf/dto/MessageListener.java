package org.unibl.etf.dto;

public interface MessageListener {
	void onMessage(ChatMessage msg);
    void onMulticast(String rawMsg);
}
