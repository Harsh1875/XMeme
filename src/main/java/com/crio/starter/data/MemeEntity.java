package com.crio.starter.data;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "memes")
public class MemeEntity {

    private String id;

    @NonNull
    private String name;

    @NonNull
    private String url;

    @NonNull
    private String caption;

}
