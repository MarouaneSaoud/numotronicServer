package org.numo.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceDto {
    private Long id;
    private String name;
    // Autres propriétés DTO si nécessaire
}
