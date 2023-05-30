package com.crio.starter.controller;

import com.crio.starter.dto.MemeIdResponse;
import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.AllMemeResponse;
import com.crio.starter.exchange.MemeRequest;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import com.crio.starter.service.MemeService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsController {

  public static final String Memes_API = "/memes/";

  private final GreetingsService greetingsService;
  private final MemeService memeService;

  @GetMapping("/say-hello")
  public ResponseDto sayHello(@RequestParam String messageId) {
    return greetingsService.getMessage(messageId);
  }


  @PostMapping(Memes_API)
  public ResponseEntity<MemeIdResponse> postMemes(@Valid @RequestBody MemeRequest memeRequest) {
    
    MemeIdResponse memeIdResponse = memeService.createMeme(memeRequest);
    if (memeIdResponse == null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(memeIdResponse);
    }

    return ResponseEntity.ok().body(memeIdResponse);
  }

  /* 
  @GetMapping(Memes_API)
  public ResponseEntity<AllMemeResponse> getMemes() {

    AllMemeResponse allMemeResponse;
    allMemeResponse = memeService.getAllMemes();
    return ResponseEntity.ok().body(allMemeResponse);
  }
  */

  @GetMapping(Memes_API)
  public ResponseEntity<List<Memes>> getMemes() {

    List<Memes> allMemeResponse = memeService.getAllMemes();
    return ResponseEntity.ok().body(allMemeResponse);
  }

  @GetMapping(Memes_API + "{id}")
  public ResponseEntity<Memes> getMemesById(@PathVariable String id) {

    Memes allMemeResponse = memeService.getMemeById(id);
    if (allMemeResponse == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(allMemeResponse);
    }
    return ResponseEntity.ok().body(allMemeResponse);
  }

  /* 
  @GetMapping(Memes_API + "{id}")
  public ResponseEntity<AllMemeResponse> getMemesById(@PathVariable String id) {

    AllMemeResponse allMemeResponse = memeService.getMemeById(id);
    if (allMemeResponse == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(allMemeResponse);
    }
    return ResponseEntity.ok().body(allMemeResponse);
  }
  */
}
