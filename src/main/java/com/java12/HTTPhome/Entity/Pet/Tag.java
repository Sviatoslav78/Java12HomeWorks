package com.java12.HTTPhome.Entity.Pet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {
    private int id;
    private String name;
}
