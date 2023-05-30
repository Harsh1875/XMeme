package com.crio.starter.service;

import java.util.List;
import com.crio.starter.dto.MemeIdResponse;
import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.MemeRequest;

public interface MemeService {
    
    MemeIdResponse createMeme(MemeRequest memeRequest);

    List<Memes> getAllMemes();

    Memes getMemeById(String id);
}
