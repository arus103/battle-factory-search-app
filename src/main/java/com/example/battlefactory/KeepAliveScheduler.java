package com.example.battlefactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepAliveScheduler {
	@Value("${RENDER_EXTERNAL_HOSTNAME:}") // 環境変数が設定されていない場合は空文字列
    private String renderExternalHostname;

    private final RestTemplate restTemplate = new RestTemplate();

    // 10分ごとに実行 (ミリ秒単位で指定: 10 * 60 * 1000 = 600000)
    @Scheduled(fixedRate = 600000)
    public void sendKeepAlivePing() {
        if (renderExternalHostname != null && !renderExternalHostname.isEmpty()) {
            String appUrl = "https://" + renderExternalHostname; // HTTPSを使用
            try {
                restTemplate.getForObject(appUrl, String.class);
                System.out.println("Keep-alive ping sent to: " + appUrl);
            } catch (Exception e) {
                System.err.println("Keep-alive ping failed for " + appUrl + ": " + e.getMessage());
            }
        } else {
            System.out.println("RENDER_EXTERNAL_HOSTNAME environment variable not set. Keep-alive ping skipped.");
        }
    }
}
