package com.kanhu.embedabble;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String street;
    private String postalCode;
}