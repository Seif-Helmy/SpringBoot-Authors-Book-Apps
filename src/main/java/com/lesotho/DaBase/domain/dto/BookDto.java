package com.lesotho.DaBase.domain.dto;

import com.lesotho.DaBase.domain.entities.AuthorEntity;
import lombok.*;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String isbn;

    private String title;

    private AuthorDto author;

}
