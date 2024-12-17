package com.example.assesmentbackend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AhliWarisRequest {
    @JsonProperty("nama")
    private String nama;
    @JsonProperty("tanggal-lahir")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String tanggalLahir;
    @JsonProperty("nomor-telepon")
    private String nomorTelepon;
    @JsonProperty("hubungan")
    private String hubungan;
}
