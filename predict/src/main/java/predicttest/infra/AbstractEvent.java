package predicttest.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import predicttest.PredictApplication; // PredictApplication으로 변경됨
import predicttest.config.kafka.KafkaProcessor;

import org.springframework.messaging.Message; // Message 임포트
import org.springframework.messaging.support.MessageBuilder; // MessageBuilder 임포트

public abstract class AbstractEvent { 

    String eventType;
    Long timestamp;

    public AbstractEvent() {
        this.setEventType(this.getClass().getSimpleName()); 
        this.timestamp = System.currentTimeMillis(); 
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }

        return json;
    }

    // 이벤트를 Kafka로 발행하는 메서드 (eventType을 헤더로 추가)
    public void publish() {
        org.springframework.cloud.stream.function.StreamBridge streamBridge = 
            PredictApplication.applicationContext.getBean(
                org.springframework.cloud.stream.function.StreamBridge.class
            );

        // MessageBuilder를 사용하여 페이로드와 헤더를 함께 구성
        Message<String> message = MessageBuilder
            .withPayload(this.toJson())
            .setHeader("eventType", this.getEventType()) // <-- eventType을 "eventType" 헤더로 추가
            .build();

        streamBridge.send(
            KafkaProcessor.OUTPUT, // KafkaProcessor에 정의된 OUTPUT 바인딩 이름 사용
            message // Message 객체 전송
        );
        System.out.println("### 이벤트 발행됨 (헤더 포함): " + this.getEventType() + " to " + KafkaProcessor.OUTPUT);
    }
}