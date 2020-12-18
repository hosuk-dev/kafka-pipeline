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
public class ExchangeDto {
    private String cpnNo;

    private String exchangeStatus;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime exchangeDt;

    private String useStore;

    @Builder
    private ExchangeDto(String cpnNo, String exchangeStatus, LocalDateTime exchangeDt, String useStore) {
        this.cpnNo = cpnNo;
        this.exchangeStatus = exchangeStatus;
        this.exchangeDt = exchangeDt;
        this.useStore = useStore;
    }

    public static ExchangeDto registExchange(String cpnNo, String exchangeStatus, LocalDateTime exchangeDt, String useStore) {
        return ExchangeDto.builder()
                .cpnNo(cpnNo)
                .exchangeStatus(exchangeStatus)
                .exchangeDt(exchangeDt)
                .useStore(useStore)
                .build();
    }
}
