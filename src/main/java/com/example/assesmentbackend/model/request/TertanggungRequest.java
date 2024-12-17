package com.example.assesmentbackend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TertanggungRequest {
    @JsonProperty("nama")
    private String nama;
    @JsonProperty("no-ktp")
    @Size(max = 16)
    private String noKtp;
    @JsonProperty("email")
    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;
    @JsonProperty("no-telp")
    private String noTelp;
}
