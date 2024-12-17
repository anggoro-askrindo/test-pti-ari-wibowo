package com.example.assesmentbackend.service;

import com.example.assesmentbackend.exception.BadRequestException;
import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import com.example.assesmentbackend.model.request.TransactionRequest;
import com.example.assesmentbackend.repo.MLookupRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.example.assesmentbackend.util.DateHelperUtil.*;

@Component("9002")
@Service
public class MikroBahariProductServiceImpl implements ProductService {

    private final MLookupRepository mLookupRepository;

    public MikroBahariProductServiceImpl(MLookupRepository mLookupRepository) {
        this.mLookupRepository = mLookupRepository;
    }

    @Override
    public TransaksiPenutupanProduct createTransaction(TransactionRequest transactionRequest) {
        String jenisKapal = mLookupRepository
                .findByLookupGroupAndKeyOnly("jenis_kapal", transactionRequest.getPertanggungan().getJenisKapal())
                .orElseThrow(() -> new BadRequestException("Jenis Kapal tidak ditemukan di lookup table"))
                .getLookupKey();
        String konstruksiKapal = mLookupRepository
                .findByLookupGroupAndKeyOnly("MARINE_HULL_CONSTRUCTION", transactionRequest.getPertanggungan().getKonstruksiKapal())
                .orElseThrow(() -> new BadRequestException("Konstruksi kapal tidak ditemukan di lookup table"))
                .getLookupKey();
        String penggunaanKapal = mLookupRepository
                .findByLookupGroupAndKeyOnly("penggunaan_kapal", transactionRequest.getPertanggungan().getPenggunaanKapal())
                .orElseThrow(() -> new BadRequestException("Penggunaan Kapal tidak ditemukan di lookup table"))
                .getLookupKey();
        boolean validateJangkaWaktuAwal = isToday("dd/MM/yyyy", transactionRequest.getPertanggungan().getJangkaWaktuAwal());
        if (!validateJangkaWaktuAwal) throw new BadRequestException("Tanggal waktu awal harus hari ini");
        boolean validateDateOneYear = isWithinOneYear("dd/MM/yyyy", transactionRequest.getPertanggungan().getJangkaWaktuAwal());
        if (!validateDateOneYear)
            throw new BadRequestException("Tanggal waktu akhir harus satu tahun dari jangka waktu awal");
        Boolean validateJenisPaket = validateJenisPaket(transactionRequest.getPertanggungan().getHargaKapal(), transactionRequest.getJenisPaket());
        if (!validateJenisPaket) throw new BadRequestException("Harga kapal "+transactionRequest.getPertanggungan().getHargaKapal()+" tidak dapat memilih paket "+transactionRequest.getJenisPaket());

        return TransaksiPenutupanProduct
                .builder()
                .productCode(transactionRequest.getProductCode())
                .namaTertanggung(transactionRequest.getTertanggung().getNama())
                .noKtp(transactionRequest.getTertanggung().getNoKtp())
                .email(transactionRequest.getTertanggung().getEmail())
                .nomorTelepon(transactionRequest.getTertanggung().getNoTelp())
                .nomorIdKapal(transactionRequest.getPertanggungan().getIdKapal())
                .jenisKapal(jenisKapal)
                .konstruksiKapal(konstruksiKapal)
                .penggunaanKapal(penggunaanKapal)
                .hargaKapal(transactionRequest.getPertanggungan().getHargaKapal())
                .jangkaWaktuAwal(toLocalDate("dd/MM/yyyy", transactionRequest.getPertanggungan().getJangkaWaktuAwal()))
                .jangkaWaktuAkhir(toLocalDate("dd/MM/yyyy", transactionRequest.getPertanggungan().getJangkaWaktuAkhir()))
                .jenisPaket(transactionRequest.getJenisPaket())
                .build();

    }

    public static Boolean validateJenisPaket(int hargaKapal, String jenisPaket) {
        if (hargaKapal <= 150000000) {
            return jenisPaket.equalsIgnoreCase("Silver");
        } else if (hargaKapal <= 300000000) {
            return jenisPaket.equalsIgnoreCase("Silver") || jenisPaket.equalsIgnoreCase("Gold");
        } else {
            return jenisPaket.equalsIgnoreCase("Silver") || jenisPaket.equalsIgnoreCase("Gold") || jenisPaket.equalsIgnoreCase("Platinum");
        }
    }
}
