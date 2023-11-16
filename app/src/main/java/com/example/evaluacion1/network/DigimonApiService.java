package com.example.evaluacion1.network;

import com.example.evaluacion1.Digimon;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DigimonApiService {
    @GET("digimon")
    Call<List<Digimon>> getDigimons();
}