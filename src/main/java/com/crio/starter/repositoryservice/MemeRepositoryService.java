package com.crio.starter.repositoryservice;

import java.util.List;
import com.crio.starter.dto.MemeIdResponse;
import com.crio.starter.dto.Memes;

public interface MemeRepositoryService {
    
    MemeIdResponse createMeme(String name, String url, String caption);

    List<Memes> getAllMemes();

    List<Memes> getMemeById(String id);
}
