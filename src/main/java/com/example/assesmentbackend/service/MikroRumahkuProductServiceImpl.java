package com.example.assesmentbackend.service;

import com.example.assesmentbackend.exception.BadRequestException;
import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import com.example.assesmentbackend.model.request.TransactionRequest;
import com.example.assesmentbackend.repo.MLookupRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.example.assesmentbackend.util.DateHelperUtil.*;

@Component("9001")
@Service
public class MikroRumahkuProductServiceImpl implements ProductService {

    private final MLookupRepository mLookupRepository;

    public MikroRumahkuProductServiceImpl(MLookupRepository mLookupRepository) {
        this.mLookupRepository = mLookupRepository;
    }

    @Override
    public TransaksiPenutupanProduct createTransaction(TransactionRequest transactionRequest) {
        String hubungan = mLookupRepository
                .findByLookupGroupAndKeyOnly("ahli_waris", transactionRequest.getAhliWaris().getHubungan())
                .orElseThrow(() -> new BadRequestException("Hubungan ahli waris tidak ditemukan di lookup table"))
                .getLookupKey();
        String informasiKepemilikan = mLookupRepository
                .findByLookupGroupAndKeyOnly("asmik_info_kepemilikan", transactionRequest.getPertanggungan().getInformasiKepemilikan())
                .orElseThrow(() -> new BadRequestException("Informasi kepemilikan tidak ditemukan di lookup table"))
                .getLookupKey();
        boolean validateJangkaWaktuAwal = isBetweenDate("dd/MM/yyyy", transactionRequest.getPertanggungan().getJangkaWaktuAwal(), 3, 3);
        if (!validateJangkaWaktuAwal) throw new BadRequestException("Tanggal waktu awal harus kurang dari 3 dan lebih dari 3 hari");
        boolean validateDateOneYear = isWithinOneYear("dd/MM/yyyy", transactionRequest.getPertanggungan().getJangkaWaktuAwal());
        if (!validateDateOneYear) throw new BadRequestException("Tanggal waktu akhir harus satu tahun dari jangka waktu awal");

        return TransaksiPenutupanProduct
                .builder()
                .productCode(transactionRequest.getProductCode())
                .namaTertanggung(transactionRequest.getTertanggung().getNama())
                .noKtp(transactionRequest.getTertanggung().getNoKtp())
                .email(transactionRequest.getTertanggung().getEmail())
                .nomorTelepon(transactionRequest.getTertanggung().getNoTelp())
                .alamat(transactionRequest.getPertanggungan().getAlamat())
                .namaAhliWaris(transactionRequest.getAhliWaris().getNama())
                .hubunganAhliWaris(hubungan)
                .informasiKepemilikan(informasiKepemilikan)
                .tanggalLahirAhliWaris(toLocalDate("dd/MM/yyyy", transactionRequest.getAhliWaris().getTanggalLahir()))
                .noTelpAhliWaris(transactionRequest.getAhliWaris().getNomorTelepon())
                .jangkaWaktuAwal(toLocalDate("dd/MM/yyyy", transactionRequest.getPertanggungan().getJangkaWaktuAwal()))
                .jangkaWaktuAkhir(toLocalDate("dd/MM/yyyy", transactionRequest.getPertanggungan().getJangkaWaktuAkhir()))
                .jenisPaket(transactionRequest.getJenisPaket())
                .build();
    }
}
