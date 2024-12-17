package com.example.assesmentbackend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.example.assesmentbackend.util.DateHelperUtil.toLocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AhliWarisRequest {
    @JsonProperty("nama")
    @NotNull
    private String nama;
    @JsonProperty("tanggal-lahir")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String tanggalLahir;
    @JsonProperty("nomor-telepon")
    private String nomorTelepon;
    @JsonProperty("hubungan")
    @NotNull
    private String hubungan;

    public LocalDate getTanggalLahirDate() {
        return toLocalDate("dd/MM/yyyy", tanggalLahir);
    }
}
