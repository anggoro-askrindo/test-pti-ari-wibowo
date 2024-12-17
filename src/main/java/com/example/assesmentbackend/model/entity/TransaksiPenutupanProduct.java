package com.example.assesmentbackend.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaksi_penutupan_product")
@Builder
public class TransaksiPenutupanProduct {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "nama_tertanggung", nullable = false, length = 100)
    private String namaTertanggung;

    @Column(name = "no_ktp", nullable = false, length = 16)
    private String noKtp;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "nomor_telepon", nullable = false, length = 30)
    private String nomorTelepon;

    @Column(name = "jangka_waktu_awal", nullable = false)
    private LocalDate jangkaWaktuAwal;

    @Column(name = "jangka_waktu_akhir", nullable = false)
    private LocalDate jangkaWaktuAkhir;

    @Column(name = "informasi_kepemilikan", length = 150)
    private String informasiKepemilikan;

    @Column(name = "alamat_pertanggungan")
    private String alamat;

    @Column(name = "nama_ahli_waris", length = 100)
    private String namaAhliWaris;

    @Column(name = "tanggal_lahir_ahli_waris")
    private LocalDate tanggalLahirAhliWaris;

    @Column(name = "no_telp_ahli_waris", length = 30)
    private String noTelpAhliWaris;

    @Column(name = "hubungan_ahli_waris", length = 150)
    private String hubunganAhliWaris;

    @Column(name = "nomor_id_kapal", length = 30)
    private String nomorIdKapal;

    @Column(name = "jenis_kapal", length = 150)
    private String jenisKapal;

    @Column(name = "konstruksi_kapal", length = 150)
    private String konstruksiKapal;

    @Column(name = "penggunaan_kapal", length = 150)
    private String penggunaanKapal;

    @Column(name = "harga_kapal", length = 150)
    private Integer hargaKapal;

    @Column(name = "jenis_paket", nullable = false, length = 150)
    private String jenisPaket;

    @Column(name = "modified_by")
    private String modified_by;

    @Column(name = "modified_date")
    private Timestamp modified_date;
}
