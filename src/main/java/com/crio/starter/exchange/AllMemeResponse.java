package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.crio.starter.dto.Memes;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllMemeResponse {
    
    private List<Memes> allMemeResponse;

}
