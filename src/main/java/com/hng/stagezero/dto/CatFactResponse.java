package com.hng.stagezero.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
public class CatFactResponse {
    private String fact;
    private int length;

}
