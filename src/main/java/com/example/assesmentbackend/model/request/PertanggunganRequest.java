package com.example.assesmentbackend.model.request;

import com.example.assesmentbackend.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.example.assesmentbackend.util.DateHelperUtil.*;

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

    public LocalDate getJangkaWaktuAwalDate(String productCode) {
        if (productCode.equals("9001")) {
            boolean validateJangkaWaktuAwal = isBetweenDate("dd/MM/yyyy", jangkaWaktuAwal, 3, 3);
            if (!validateJangkaWaktuAwal) throw new BadRequestException("Tanggal waktu awal harus kurang dari 3 dan lebih dari 3 hari");
            return toLocalDate("dd/MM/yyyy", jangkaWaktuAwal);
        }

        if (productCode.equals("9002")) {
            boolean validateJangkaWaktuAwal = isToday("dd/MM/yyyy", jangkaWaktuAwal);
            if (!validateJangkaWaktuAwal) throw new BadRequestException("Tanggal waktu awal harus hari ini");
            return toLocalDate("dd/MM/yyyy", jangkaWaktuAwal);
        }
        return null;
    }

    public LocalDate getJangkaWaktuAkhirDate() {
        boolean validateDateOneYear = isWithinOneYear("dd/MM/yyyy", jangkaWaktuAkhir);
        if (!validateDateOneYear) throw new BadRequestException("Tanggal waktu akhir harus satu tahun dari jangka waktu awal");
        return toLocalDate("dd/MM/yyyy", jangkaWaktuAkhir);
    }
}
