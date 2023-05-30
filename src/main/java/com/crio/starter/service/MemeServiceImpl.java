package com.crio.starter.service;

import java.lang.annotation.Retention;
import java.util.List;
import com.crio.starter.dto.MemeIdResponse;
import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.AllMemeResponse;
import com.crio.starter.exchange.MemeRequest;
import com.crio.starter.repositoryservice.MemeRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeServiceImpl implements MemeService {

    @Autowired
    private MemeRepositoryService memeRepositoryService;

    
    @Override
    public MemeIdResponse createMeme(MemeRequest memeRequest) {  
        return memeRepositoryService.createMeme(memeRequest.getName(), memeRequest.getUrl(), memeRequest.getCaption());
    }

    @Override
    public List<Memes> getAllMemes() {
        List<Memes> allMemes = memeRepositoryService.getAllMemes();
        return allMemes;
    }

    @Override
    public Memes getMemeById(String id) {
        List<Memes> meme = memeRepositoryService.getMemeById(id);
        if (meme == null) {
            return null;
        }
        return meme.get(0);
    }
    
}
