package com.hosuk.test.producer.job;

import com.hosuk.test.common.dto.SendDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;


@Slf4j
@Component
@RequiredArgsConstructor
public class SendJob {

    private final KafkaTemplate<String, SendDto> sendDtoKafkaTemplate;
    private final Environment env;

    /**
     * 발송결과를 kafka send topic에 저장한다
     */
    @Scheduled(fixedDelay = 1000)
    public void run() {
        String cpnNo = RandomStringUtils.randomAlphanumeric(8);
        SendDto sendDto = SendDto.registSendDto(cpnNo, "trId", LocalDateTime.now(), "발송정보");

        ListenableFuture<SendResult<String, SendDto>> future =
                sendDtoKafkaTemplate.send(env.getProperty("send.topic-name"), sendDto);

        future.addCallback((success) -> sendOnSuccess(success), (failure) -> sendOnFail(failure));

    }

    /**
     * Produce 성공 Callback
     *
     * @param sendResult
     */
    private void sendOnSuccess(SendResult<String, SendDto> sendResult) {
        log.info(sendResult.toString());

    }

    /**
     * Produce 실패 Callback
     *
     * @param ex
     */
    private void sendOnFail(Throwable ex) {
        log.info(ex.toString());
    }

}
