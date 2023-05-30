package com.crio.starter.repositoryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.MemeIdResponse;
import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.AllMemeResponse;
import com.crio.starter.repository.MemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MemeRepositoryServiceImpl implements MemeRepositoryService {

    @Autowired
    private MemeRepository memeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MemeIdResponse createMeme(String name, String url, String caption) {   
        Boolean validation = validate(name, url, caption);

        if (validation == false) {
            return null;
        }

        MemeEntity memeEntity = new MemeEntity(name, url, caption);
        MemeEntity entity = memeRepository.save(memeEntity);
        MemeIdResponse memeIdResponse = modelMapper.map(entity, MemeIdResponse.class);

        return memeIdResponse;
    }

    private Boolean validate(String name, String url, String caption) {

        MemeEntity memeEntity = memeRepository.findFirstByNameAndUrlAndCaption(name, url, caption);

        if (memeEntity != null && name.equals(memeEntity.getName()) && url.equals(memeEntity.getUrl()) && caption.equals(memeEntity.getCaption())) {
            return false;
        }

        return true;
    }

    @Override
    public List<Memes> getAllMemes() {      
        List<MemeEntity> allMemesEntity = memeRepository.findAllByOrderByIdDesc();

        List<Memes> allMemes = new ArrayList<>();

        if (allMemesEntity.size() > 100) {
            for (int i=0; i < 100; i++) {
                allMemes.add(modelMapper.map(allMemesEntity.get(i), Memes.class));
            }
        } else {
            for (MemeEntity entity: allMemesEntity) {
                allMemes.add(modelMapper.map(entity, Memes.class));
            }
        }

        return allMemes;
    }

    @Override
    public List<Memes> getMemeById(String id) {
        Optional<MemeEntity> memeEntity = memeRepository.findById(id);
        List<MemeEntity> memety = new ArrayList<>();
        memeEntity.ifPresent(memety::add);

        if (memety.size() == 0) {
            return null;
        }

        List<Memes> meme = new ArrayList<>();
        meme.add(modelMapper.map(memety.get(0), Memes.class));

        return meme;
    }
    
}
