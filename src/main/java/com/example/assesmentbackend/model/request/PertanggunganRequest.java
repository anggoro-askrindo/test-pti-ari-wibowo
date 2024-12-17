package com.example.assesmentbackend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PertanggunganRequest {
    @JsonProperty("jangka-waktu-awal")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String jangkaWaktuAwal;
    @JsonProperty("jangka-waktu-akhir")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String jangkaWaktuAkhir;
    @JsonProperty("informasi-kepemilikan")
    private String informasiKepemilikan;
    @JsonProperty("alamat")
    private String alamat;
    @JsonProperty("id-kapal")
    private String idKapal;
    @JsonProperty("jenis-kapal")
    private String jenisKapal;
    @JsonProperty("konstruksi-kapal")
    private String konstruksiKapal;
    @JsonProperty("penggunaan-kapal")
    private String penggunaanKapal;
    @JsonProperty("harga-kapal")
    private Integer hargaKapal;
}
