package com.example.assesmentbackend.model.request;

import com.example.assesmentbackend.validation.TransactionConditional;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TransactionConditional(
        selected = "productCode",
        values = {"9001"},
        required = {
                "ahliWaris"
        })
@TransactionConditional(
        selected = "productCode",
        values = {"9002"},
        required = {
                "pertanggungan.idKapal",
                "pertanggungan.jenisKapal",
                "pertanggungan.konstruksiKapal",
                "pertanggungan.penggunaanKapal",
                "pertanggungan.hargaKapal"
        })
public class TransactionRequest {
    @JsonProperty("product-code")
    @NotNull(message = "product code must not be null")
    @NotEmpty(message = "product code must not be null")
    private String productCode;
    @JsonProperty("tertanggung")
    @Valid
    private TertanggungRequest tertanggung;
    @JsonProperty("pertanggungan")
    @Valid
    private PertanggunganRequest pertanggungan;
    @JsonProperty("ahli-waris")
    @Valid
    private AhliWarisRequest ahliWaris;
    @JsonProperty("jenis-paket")
    @NotNull
    private String jenisPaket;
}
