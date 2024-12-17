package com.example.assesmentbackend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.assesmentbackend.exception.BadRequestException;
import com.example.assesmentbackend.model.entity.MLookup;
import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import com.example.assesmentbackend.model.request.AhliWarisRequest;
import com.example.assesmentbackend.model.request.PertanggunganRequest;
import com.example.assesmentbackend.model.request.TertanggungRequest;
import com.example.assesmentbackend.model.request.TransactionRequest;
import com.example.assesmentbackend.repo.MLookupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private MLookupRepository mLookupRepository;

    @InjectMocks
    private MikroBahariProductServiceImpl mikroBahariProductService;


    @BeforeEach
    public void setUp() {
        // Set up transactionRequest with valid data
    }

    @Test
    public void testCreateTransaction_Success() {
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        when(mLookupRepository.findByLookupGroupAndKeyOnly(eq("jenis_kapal"), anyString()))
                .thenReturn(Optional.of(new MLookup("jenis_kapal.1", "jenis_kapal",	"1",	"Kapal Motor",	true,	0L,	"admin", new Date(), null, null)));
        when(mLookupRepository.findByLookupGroupAndKeyOnly(eq("MARINE_HULL_CONSTRUCTION"), anyString()))
                .thenReturn(Optional.of(new MLookup("MARINE_HULL_CONSTRUCTION.ALUM", "MARINE_HULL_CONSTRUCTION",	"ALUMINIUM",	"Aluminium",	true,	0L,	"admin", new Date(), null, null)));
        when(mLookupRepository.findByLookupGroupAndKeyOnly(eq("penggunaan_kapal"), anyString()))
                .thenReturn(Optional.of(new MLookup("penggunaan_kapal.1", "penggunaan_kapal",	"1",	"Penangkapan Ikan",	true,	0L,	"admin", new Date(), null, null)));

        TransactionRequest transactionRequest = TransactionRequest.builder()
                .productCode("9001")
                .tertanggung(new TertanggungRequest("Usman", "1234567890123456", "usman@gmail.com", "628736489310"))
                .pertanggungan(PertanggunganRequest.builder()
                        .jangkaWaktuAwal(LocalDate.now().format(DATE_FORMATTER))
                        .jangkaWaktuAkhir(LocalDate.now().plusMonths(5).format(DATE_FORMATTER))
                        .idKapal("ID1234")
                        .jenisKapal("1")
                        .konstruksiKapal("ALUMINIUM")
                        .penggunaanKapal("1")
                        .hargaKapal(150000000)
                        .build()
                )
                .jenisPaket("Silver")
                .ahliWaris(new AhliWarisRequest("Qodir", "12/11/1979", "62893749239", null))
                .build();

        TransaksiPenutupanProduct result = mikroBahariProductService.createTransaction(transactionRequest);

        assertNotNull(result);
    }
}
