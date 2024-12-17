package com.example.assesmentbackend.service;

import com.example.assesmentbackend.exception.BadRequestException;
import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import com.example.assesmentbackend.model.request.TransactionRequest;
import com.example.assesmentbackend.model.request.UpdateTransactionRequest;
import com.example.assesmentbackend.repo.MLookupRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Component("9001")
@Service
public class MikroRumahkuProductServiceImpl implements ProductService {

    private final MLookupRepository mLookupRepository;

    public MikroRumahkuProductServiceImpl(MLookupRepository mLookupRepository) {
        this.mLookupRepository = mLookupRepository;
    }

    @Override
    public TransaksiPenutupanProduct createTransaction(TransactionRequest transactionRequest) {
        return TransaksiPenutupanProduct
                .builder()
                .productCode(transactionRequest.getProductCode())
                .namaTertanggung(transactionRequest.getTertanggung().getNama())
                .noKtp(transactionRequest.getTertanggung().getNoKtp())
                .email(transactionRequest.getTertanggung().getEmail())
                .nomorTelepon(transactionRequest.getTertanggung().getNoTelp())
                .alamat(transactionRequest.getPertanggungan().getAlamat())
                .namaAhliWaris(transactionRequest.getAhliWaris().getNama())
                .hubunganAhliWaris(lookup("ahli_waris", transactionRequest.getAhliWaris().getHubungan()))
                .informasiKepemilikan(lookup("asmik_info_kepemilikan", transactionRequest.getPertanggungan().getInformasiKepemilikan()))
                .tanggalLahirAhliWaris(transactionRequest.getAhliWaris().getTanggalLahirDate())
                .noTelpAhliWaris(transactionRequest.getAhliWaris().getNomorTelepon())
                .jangkaWaktuAwal(transactionRequest.getPertanggungan().getJangkaWaktuAwalDate(transactionRequest.getProductCode()))
                .jangkaWaktuAkhir(transactionRequest.getPertanggungan().getJangkaWaktuAkhirDate())
                .jenisPaket(transactionRequest.getJenisPaket())
                .build();
    }

    @Override
    public TransaksiPenutupanProduct updateTransaction(TransaksiPenutupanProduct transaksiPenutupanProduct, UpdateTransactionRequest transactionRequest) {
        if (transactionRequest.getProductCode() != null) transaksiPenutupanProduct.setProductCode(transactionRequest.getProductCode());
        if (transactionRequest.getTertanggung().getNama() != null) transaksiPenutupanProduct.setNamaTertanggung(transactionRequest.getTertanggung().getNama());
        if (transactionRequest.getTertanggung().getNoKtp() != null) transaksiPenutupanProduct.setNoKtp(transactionRequest.getTertanggung().getNoKtp());
        if (transactionRequest.getTertanggung().getEmail() != null) transaksiPenutupanProduct.setEmail(transactionRequest.getTertanggung().getEmail());
        if (transactionRequest.getTertanggung().getNoTelp() != null) transaksiPenutupanProduct.setNomorTelepon(transactionRequest.getTertanggung().getNoTelp());
        if (transactionRequest.getPertanggungan().getAlamat() != null) transaksiPenutupanProduct.setAlamat(transactionRequest.getPertanggungan().getAlamat());
        if (transactionRequest.getAhliWaris().getNama() != null) transaksiPenutupanProduct.setNamaAhliWaris(transactionRequest.getAhliWaris().getNama());
        if (transactionRequest.getAhliWaris().getHubungan() != null) transaksiPenutupanProduct.setHubunganAhliWaris(lookup("ahli_waris", transactionRequest.getAhliWaris().getHubungan()));
        if (transactionRequest.getPertanggungan().getInformasiKepemilikan() != null) transaksiPenutupanProduct.setHubunganAhliWaris(lookup("asmik_info_kepemilikan", transactionRequest.getPertanggungan().getInformasiKepemilikan()));
        if (transactionRequest.getAhliWaris().getTanggalLahir() != null) transaksiPenutupanProduct.setTanggalLahirAhliWaris(transactionRequest.getAhliWaris().getTanggalLahirDate());
        if (transactionRequest.getPertanggungan().getJangkaWaktuAwal() != null) transaksiPenutupanProduct.setJangkaWaktuAwal(transactionRequest.getPertanggungan().getJangkaWaktuAwalDate(transaksiPenutupanProduct.getProductCode()));
        if (transactionRequest.getPertanggungan().getJangkaWaktuAkhir() != null) transaksiPenutupanProduct.setJangkaWaktuAkhir(transactionRequest.getPertanggungan().getJangkaWaktuAkhirDate());
        if (transactionRequest.getJenisPaket() != null) transaksiPenutupanProduct.setJenisPaket(transactionRequest.getJenisPaket());
        transaksiPenutupanProduct.setModified_by("MARKETING");
        transaksiPenutupanProduct.setModified_date(new Timestamp(new Date().getTime()));
        return transaksiPenutupanProduct;
    }

    public String lookup(String lookupGroup, String keyOnly) {
        return mLookupRepository
                .findByLookupGroupAndKeyOnly(lookupGroup, keyOnly)
                .orElseThrow(() -> new BadRequestException(lookupGroup+ " tidak ditemukan di lookup table"))
                .getLookupKey();
    }
}
