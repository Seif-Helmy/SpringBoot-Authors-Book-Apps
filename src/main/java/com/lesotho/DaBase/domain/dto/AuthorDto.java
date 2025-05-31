package com.lesotho.DaBase.domain.dto;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
public class AuthorDto {
    private Long id;

    private String name;

    private Integer age;
}
