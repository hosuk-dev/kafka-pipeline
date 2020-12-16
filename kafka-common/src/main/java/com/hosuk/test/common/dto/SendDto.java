package com.hosuk.test.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class SendDto {
    private String cpnNo;

    private String trId;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime sendDt;

    private String sendMsg;

    @Builder
    private SendDto(String cpnNo, String trId, LocalDateTime sendDt, String sendMsg) {
        this.cpnNo = cpnNo;
        this.trId = trId;
        this.sendDt = sendDt;
        this.sendMsg = sendMsg;
    }

    public static SendDto registSendDto(String cpnNo, String trId, LocalDateTime sendDt, String sendMsg) {
        return SendDto.builder()
                .cpnNo(cpnNo)
                .trId(trId)
                .sendDt(sendDt)
                .sendMsg(sendMsg)
                .build();
    }
}
