package org.example.a24_09_camptocamp.model.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieBean {
    private Long id;
    private String title;
    private Integer length;
}
