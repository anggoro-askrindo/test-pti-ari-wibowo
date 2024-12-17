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

@Component("9002")
@Service
public class MikroBahariProductServiceImpl implements ProductService {

    private final MLookupRepository mLookupRepository;

    public MikroBahariProductServiceImpl(MLookupRepository mLookupRepository) {
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
                .nomorIdKapal(transactionRequest.getPertanggungan().getIdKapal())
                .jenisKapal(lookup("jenis_kapal", transactionRequest.getPertanggungan().getJenisKapal()))
                .konstruksiKapal(lookup("MARINE_HULL_CONSTRUCTION", transactionRequest.getPertanggungan().getKonstruksiKapal()))
                .penggunaanKapal(lookup("penggunaan_kapal", transactionRequest.getPertanggungan().getPenggunaanKapal()))
                .hargaKapal(transactionRequest.getPertanggungan().getHargaKapal())
                .jangkaWaktuAwal(transactionRequest.getPertanggungan().getJangkaWaktuAwalDate(transactionRequest.getProductCode()))
                .jangkaWaktuAkhir(transactionRequest.getPertanggungan().getJangkaWaktuAkhirDate())
                .jenisPaket(getJenisPaket(transactionRequest.getPertanggungan().getHargaKapal(), transactionRequest.getJenisPaket()))
                .build();
    }

    @Override
    public TransaksiPenutupanProduct updateTransaction(TransaksiPenutupanProduct transaksiPenutupanProduct, UpdateTransactionRequest transactionRequest) {
        if (transactionRequest.getProductCode() != null) transaksiPenutupanProduct.setProductCode(transactionRequest.getProductCode());
        if (transactionRequest.getTertanggung().getNama() != null) transaksiPenutupanProduct.setNamaTertanggung(transactionRequest.getTertanggung().getNama());
        if (transactionRequest.getTertanggung().getNoKtp() != null) transaksiPenutupanProduct.setNoKtp(transactionRequest.getTertanggung().getNoKtp());
        if (transactionRequest.getTertanggung().getEmail() != null) transaksiPenutupanProduct.setEmail(transactionRequest.getTertanggung().getEmail());
        if (transactionRequest.getTertanggung().getNoTelp() != null) transaksiPenutupanProduct.setNomorTelepon(transactionRequest.getTertanggung().getNoTelp());
        if (transactionRequest.getPertanggungan().getIdKapal() != null) transaksiPenutupanProduct.setNomorIdKapal(transactionRequest.getPertanggungan().getIdKapal());
        if (transactionRequest.getPertanggungan().getJenisKapal() != null) transaksiPenutupanProduct.setJenisKapal(lookup("jenis_kapal", transactionRequest.getPertanggungan().getJenisKapal()));
        if (transactionRequest.getPertanggungan().getKonstruksiKapal() != null) transaksiPenutupanProduct.setKonstruksiKapal(lookup("MARINE_HULL_CONSTRUCTION", transactionRequest.getPertanggungan().getKonstruksiKapal()));
        if (transactionRequest.getPertanggungan().getPenggunaanKapal() != null) transaksiPenutupanProduct.setPenggunaanKapal(lookup("penggunaan_kapal", transactionRequest.getPertanggungan().getPenggunaanKapal()));
        if (transactionRequest.getPertanggungan().getHargaKapal() != null) transaksiPenutupanProduct.setHargaKapal(transactionRequest.getPertanggungan().getHargaKapal());
        if (transactionRequest.getPertanggungan().getJangkaWaktuAwal() != null) transaksiPenutupanProduct.setJangkaWaktuAwal(transactionRequest.getPertanggungan().getJangkaWaktuAwalDate(transaksiPenutupanProduct.getProductCode()));
        if (transactionRequest.getPertanggungan().getJangkaWaktuAkhir() != null) transaksiPenutupanProduct.setJangkaWaktuAwal(transactionRequest.getPertanggungan().getJangkaWaktuAkhirDate());
        if (transactionRequest.getJenisPaket() != null) transaksiPenutupanProduct.setJenisPaket(getJenisPaket(transaksiPenutupanProduct.getHargaKapal(), transactionRequest.getJenisPaket()));
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

    public static String getJenisPaket(int hargaKapal, String jenisPaket) {
        boolean validateJenisPaket;
        if (hargaKapal <= 150000000) {
            validateJenisPaket = jenisPaket.equalsIgnoreCase("Silver");
        } else if (hargaKapal <= 300000000) {
            validateJenisPaket = jenisPaket.equalsIgnoreCase("Silver") || jenisPaket.equalsIgnoreCase("Gold");
        } else {
            validateJenisPaket = jenisPaket.equalsIgnoreCase("Silver") || jenisPaket.equalsIgnoreCase("Gold") || jenisPaket.equalsIgnoreCase("Platinum");
        }
        if (!validateJenisPaket) throw new BadRequestException("Harga kapal "+hargaKapal+" tidak dapat memilih paket "+jenisPaket);
        return jenisPaket;
    }
}
